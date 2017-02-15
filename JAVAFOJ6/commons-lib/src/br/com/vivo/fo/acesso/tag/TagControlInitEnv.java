package br.com.vivo.fo.acesso.tag;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.JspException;

import noNamespace.MsgDocument;

import org.apache.xmlbeans.XmlOptions;

import br.com.vivo.fo.commons.utils.businessDelegate.JATMIBusinessDelegate;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoErrorException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.ControleAcessoEnvioVODocument.ControleAcessoEnvioVO;
import br.com.vivo.fo.usuario.vo.RuleVODocument;
import br.com.vivo.fo.usuario.vo.RuleVODocument.RuleVO;
import br.com.vivo.fo.xml.XmlHeader;
import br.com.vivo.fo.xml.XmlManager;

@SuppressWarnings({"rawtypes"})
public class TagControlInitEnv extends javax.servlet.jsp.tagext.TagSupport {

	private static final long serialVersionUID = 8078465670347983274L;

	private transient Logger log = new Logger("start");

	private final String BLOQUEADO = "0";
	private final String EXPIRADA = "2";
	private final String INVALIDA = "3";

	public int doStartTag() throws JspException {

		HttpServletResponse response = (HttpServletResponse) pageContext.getResponse();
		HttpServletRequest request = (HttpServletRequest) pageContext.getRequest();
		String user = ConstantesCRM.SVAZIO;
		String pagina = ConstantesCRM.SVAZIO;

		String xmlRetorno[];
		RuleVO ruleVO;
		XmlOptions xmlOptions = new XmlOptions();
		xmlOptions.setSaveOuter();
		ControleAcessoEnvioVO controleAcessoEnvioVO = ControleAcessoEnvioVO.Factory.newInstance(xmlOptions);
		controleAcessoEnvioVO.setLogin(user);

		// ControlAcesso controleAcesso = new ControlAcesso();
		ArrayList lista = new ArrayList();
		if (ConstantesCRM.getCurrentUser(pageContext.getSession()) == null) {
			try {
				RequestDispatcher rd = request.getRequestDispatcher("/session.jsp");
				try {
					log.debug("TagControlInitEnv: Redirecionando para session.jsp");
					rd.forward(request, response);
					return (SKIP_PAGE);

				} catch (ServletException ex) {
					log.error("TagControlInitEnv: " + ex.getMessage(), ex);
					throw new JspException(ex);
				}

			} catch (java.io.IOException ex) {
				log.error("TagControlInitEnv: " + ex.getMessage(), ex);
				throw new JspException(ex);
			}

		} else {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			pagina = pageContext.getServletConfig().getServletName();
			try {
				HashMap hash = (HashMap) request.getSession().getAttribute(ConstantesCRM.PERFIL_SESSION);
				lista = (ArrayList) hash.get(pagina);
				if (lista == null) {
					lista = new ArrayList();
				}

				String timeSession = (String) request.getSession().getAttribute(ConstantesCRM.PERFIL_USR_MIN);
				long result = comparaDataLogin();

				if (result >= Integer.parseInt(timeSession)) {
					JATMIBusinessDelegate jatmi = new JATMIBusinessDelegate();
					controleAcessoEnvioVO.setInAtivo("1");
					controleAcessoEnvioVO.setSessionId(request.getSession().getId());

					String xmlIN = controleAcessoEnvioVO.xmlText().replaceAll("vo:", "");
					String xmlOUT = jatmi.executeCommnad(user,"UsrAcesso", xmlIN);

					xmlRetorno = XmlManager.ParseXmlOut(xmlOUT);
					ruleVO = RuleVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getRuleVO();

					// Tratamento se o usuario ficou bloqueado
					RequestDispatcher rd = request.getRequestDispatcher("/acessoNegado.jsp");

					if (ruleVO == null || ruleVO.getUs().equals(BLOQUEADO)) {
						request.setAttribute("statusText", "Acesso Negado");
						try {
							log.debug("TagControlInitEnv: Redirecionando para acessoNegado.jsp");
							rd.forward(request, response);
							return SKIP_PAGE;

						} catch (ServletException ex) {
							log.error("TagControlInitEnv: " + ex.getMessage(), ex);
							throw new JspException(ex);
						}

					} else if (ruleVO == null || ruleVO.getUs().equals(EXPIRADA)) {
						pageContext.getRequest().setAttribute("statusText", xmlRetorno[3]);
						try {
							log.debug("TagControlInitEnv: Redirecionando para acessoNegado.jsp");
							rd.forward(request, response);
							return SKIP_PAGE;

						} catch (ServletException ex) {
							log.error("TagControlInitEnv: " + ex.getMessage(), ex);
							throw new JspException(ex);
						}

					} else if (ruleVO == null || ruleVO.getUs().equals(INVALIDA)) {
						request.setAttribute("statusText", xmlRetorno[3]);
						try {
							log.debug("TagControlInitEnv: Redirecionando para acessoNegado.jsp");
							rd.forward(request, response);
							return SKIP_PAGE;

						} catch (ServletException ex) {
							log.error("TagControlInitEnv: " + ex.getMessage(), ex);
							throw new JspException(ex);
						}

					} else {
						request.getSession().setAttribute("dt_login_usr", Calendar.getInstance());
					}
				}

			} catch (Exception ex) {
				XmlHeader header = new XmlHeader("UsrAcesso", user, "00", 'E', "0000", "Acesso não autorizado. [TagEnv]");
				TuxedoErrorException tex = new TuxedoErrorException(header, ex);
				log.error("TagControlInitEnv: " + ex.getMessage(), ex);
				throw new JspException("Acesso não autorizado. [TagEnv]", tex);
			}

			try {
				pageContext.getSession().removeAttribute("lista");

			} catch (Exception ex) {
				log.error("TagControlInitEnv: " + ex.getMessage(), ex);
				throw new JspException(ex);
			}

			pageContext.getSession().setAttribute("lista", lista);
			return EVAL_PAGE;
		}
	}

	public int doEndTag() throws JspException {
		return EVAL_PAGE;
	}

	private long comparaDataLogin() {
		Calendar dtLoginUsr = (Calendar) pageContext.getSession().getAttribute("dt_login_usr");
		Calendar dtAtual = Calendar.getInstance();
		long result = dtAtual.getTimeInMillis() - dtLoginUsr.getTimeInMillis();
		if (result > 0) {
			result = result / 1000 / 60;
		}
		return result;
	}
}
