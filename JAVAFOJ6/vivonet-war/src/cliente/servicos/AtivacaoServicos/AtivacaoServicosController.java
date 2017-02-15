package cliente.servicos.AtivacaoServicos;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.ativacaoServico.AtivacaoServicoFacade;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

public class AtivacaoServicosController extends AbstractAction {

	private static final long serialVersionUID = 4343034013720750759L;

	@EJB
	private AtivacaoServicoFacade ativacaoServicoFacadeImpl;

	private String userId;
	private String nrLinha;
	private String servico;
	private String paginaAtual;

	private static Logger logger = new Logger("clientes");

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("verificaServico".equals(mapping.getParameter())) {
			return verificaServico(mapping, form, request, response);
		} else if ("setServico".equals(mapping.getParameter())) {
			return setServico(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="inativo" path="AtivaServicoBasico.jsp"
	 * @jpf:forward name="ativo" path="DesativaServicoBasico.jsp"
	 * @jpf:forward name="erro" path="erro.jsp"
	 */
	public ActionForward verificaServico(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws TuxedoException, FacadeException {
		int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
		// String x= servTux.getServicos("lolo");
		if (!checaParametros(request)) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		}
		if (paginaAtual.equalsIgnoreCase("ativar") && ativacaoServicoFacadeImpl.isAtivo(userId, nrLinha, servico, idTipoLinha)) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		} else if (paginaAtual.equalsIgnoreCase("desativar") && !ativacaoServicoFacadeImpl.isAtivo(userId, nrLinha, servico, idTipoLinha)) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		} else if (paginaAtual.equalsIgnoreCase("ativar") && !ativacaoServicoFacadeImpl.isAtivo(userId, nrLinha, servico, idTipoLinha)) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("inativo");
		} else if (paginaAtual.equalsIgnoreCase("desativar") && ativacaoServicoFacadeImpl.isAtivo(userId, nrLinha, servico, idTipoLinha)) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("ativo");
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("erro");
	}

	private boolean checaParametros(HttpServletRequest request) {
		userId = ConstantesCRM.getCurrentUser(request.getSession());
		ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
		nrLinha = parametros.getNrLinha();// ((String)request.getSession().getAttribute("nrCodArea")+request.getSession().getAttribute("nrLinha"));
		servico = request.getParameter("codServico");
		paginaAtual = request.getParameter("paginaAtual");
		if (paginaAtual == null || userId == null || nrLinha == null || servico == null || userId.trim().equalsIgnoreCase("") || nrLinha.trim().equalsIgnoreCase("") || servico.trim().equalsIgnoreCase("") || paginaAtual.trim().equalsIgnoreCase("")) {
			return false;
		}
		return true;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="sucesso.jsp"
	 * @jpf:forward name="erro" path="erro.jsp"
	 */
	public ActionForward setServico(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
		try {
			String operacao = request.getParameter("operacao");
			ativacaoServicoFacadeImpl.setServico(userId, nrLinha, servico, operacao, idTipoLinha);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			logger.error(e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("erro");
		}

	}
}
