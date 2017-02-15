package fidelizacao.Manter.ManterChip;

import java.net.InetAddress;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.ListaIdDescricaoVODocument.ListaIdDescricaoVO;
import br.com.vivo.fo.admsistemas.vo.ListaIdDescricaoVODocument.ListaIdDescricaoVO.ItemListaIdDescricaoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.manter.ManterChipFacade;
import br.com.vivo.fo.ctrls.fidelizacao.manter.ManterChipFacadeImpl;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManutChipVODocument.FidelizacaoManutChipVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManutChipVODocument.FidelizacaoManutChipVO.ChipsCadastrados;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;

import com.indracompany.actions.AbstractAction;

public class ManterChipController extends AbstractAction {

	private static final long serialVersionUID = 3414828749589297537L;

	protected global.Global globalApp = new global.Global();
	private ManterChipFormBean manterChipFormBean;
	private String user = ConstantesCRM.SVAZIO;

	private static transient Logger log = new Logger("fidelizacao");

	@EJB
	private ManterChipFacade manterChipFacade = new ManterChipFacadeImpl();
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("incluirChip".equals(mapping.getParameter())) {
			return incluirChip(mapping, form, request, response);
		} else if ("alterarChip".equals(mapping.getParameter())) {
			return alterarChip(mapping, form, request, response);
		} else if ("pesquisarChip".equals(mapping.getParameter())) {
			return pesquisarChip(mapping, form, request, response);
		} else if ("excluirChip".equals(mapping.getParameter())) {
			return excluirChip(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterChipFormBean form = (ManterChipFormBean) formParam;

		user = ConstantesCRM.getCurrentUser(request.getSession());

		manterChipFormBean = new ManterChipFormBean();

		ListaIdDescricaoVO listaDDD = ListaIdDescricaoVO.Factory.newInstance();

		FidelizacaoManutChipVO fidelizacaoManutChipVO = manterChipFacade.getListaDDD(user);
		for (int i = 0; i < fidelizacaoManutChipVO.getListaDDDArray().length; i++) {
			listaDDD.addNewItemListaIdDescricaoVO();
			listaDDD.getItemListaIdDescricaoVOArray(i).setId(fidelizacaoManutChipVO.getListaDDDArray(i).getId());
			listaDDD.getItemListaIdDescricaoVOArray(i).setDescricao(fidelizacaoManutChipVO.getListaDDDArray(i).getNrDDD());
		}

		getManterChipFormBean().setListaDDD(listaDDD.getItemListaIdDescricaoVOArray());

		// FidelizacaoManutChipVO chipsCadastrados =
		// manterChipFacade.getChipsCadastradosPorDDD(user,"");

		// manterChipFormBean.setChipsCadastrados(chipsCadastrados.getChipsCadastradosArray());
		// form.setChipsCadastrados(chipsCadastrados.getChipsCadastradosArray());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward incluirChip(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterChipFormBean form = (ManterChipFormBean) formParam;

		try {

			user = ConstantesCRM.getCurrentUser(request.getSession());

			FidelizacaoManutChipVO fidelizacaoManutChip = FidelizacaoManutChipVO.Factory.newInstance();
			fidelizacaoManutChip.setIdDDD(request.getParameter("idDDD"));
			fidelizacaoManutChip.setCdChipAvulsoSAP(request.getParameter("cdChipAvulso"));
			fidelizacaoManutChip.setCdChipPreProgSAP(request.getParameter("cdChipPreProg"));
			fidelizacaoManutChip.setVlChipAvulsoSAP(request.getParameter("vlChipAvulso"));
			fidelizacaoManutChip.setVlChipPreProgSAP(request.getParameter("vlChipPreProg"));

			String headerIP = request.getHeader("X-Forwarded-For");
			System.out.println("HEADER_IP from X-Forwarded-For = " + headerIP);
			String ip = request.getRemoteAddr();
			System.out.println("IP from RemoteAddr = " + ip);
			InetAddress ipAddr;
			try {
				if (headerIP != null) {
					ipAddr = InetAddress.getByName(headerIP);
					ip = ipAddr.getHostAddress();
				} else {
					ipAddr = InetAddress.getByName(ip);
					ip = ipAddr.getHostAddress();
				}
			} catch (Exception exc) {
			}
			fidelizacaoManutChip.setNrIP(ip);

			RetornoVO retorno = manterChipFacade.incluirAlterarChip(user, fidelizacaoManutChip);

			if (!"INSERIDO".equals(retorno.getDescricao())) {
				request.setAttribute("msgErro", retorno.getDescricao());
			} else {
				request.setAttribute("msgOk", "Chip inserido com sucesso!");
				FidelizacaoManutChipVO chipsCadastrados = manterChipFacade.getChipsCadastradosPorDDD(user, "");

				manterChipFormBean.setChipsCadastrados(chipsCadastrados.getChipsCadastradosArray());
				form.setChipsCadastrados(chipsCadastrados.getChipsCadastradosArray());

			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterChipController.incluirChip " + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgErro", twe.getXmlHeader().getStatusText());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward alterarChip(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ManterChipFormBean form = (ManterChipFormBean) formParam;

		try {

			user = ConstantesCRM.getCurrentUser(request.getSession());

			FidelizacaoManutChipVO fidelizacaoManutChip = FidelizacaoManutChipVO.Factory.newInstance();
			fidelizacaoManutChip.setIdChip(request.getParameter("idChip"));
			fidelizacaoManutChip.setIdDDD(request.getParameter("idDDD"));
			fidelizacaoManutChip.setCdChipAvulsoSAP(request.getParameter("cdChipAvulso"));
			fidelizacaoManutChip.setCdChipPreProgSAP(request.getParameter("cdChipPreProg"));
			fidelizacaoManutChip.setVlChipAvulsoSAP(request.getParameter("vlChipAvulso"));
			fidelizacaoManutChip.setVlChipPreProgSAP(request.getParameter("vlChipPreProg"));

			String headerIP = request.getHeader("X-Forwarded-For");
			System.out.println("HEADER_IP from X-Forwarded-For = " + headerIP);
			String ip = request.getRemoteAddr();
			System.out.println("IP from RemoteAddr = " + ip);
			InetAddress ipAddr;
			try {
				if (headerIP != null) {
					ipAddr = InetAddress.getByName(headerIP);
					ip = ipAddr.getHostAddress();
				} else {
					ipAddr = InetAddress.getByName(ip);
					ip = ipAddr.getHostAddress();
				}
			} catch (Exception exc) {
			}
			fidelizacaoManutChip.setNrIP(ip);

			RetornoVO retorno = manterChipFacade.incluirAlterarChip(user, fidelizacaoManutChip);

			if (!"INSERIDO".equals(retorno.getDescricao())) {
				request.setAttribute("msgErro", retorno.getDescricao());
			} else {
				request.setAttribute("msgOk", "Chip alterado com sucesso!");
				FidelizacaoManutChipVO chipsCadastrados = manterChipFacade.getChipsCadastradosPorDDD(user, "");

				manterChipFormBean.setChipsCadastrados(chipsCadastrados.getChipsCadastradosArray());
				form.setChipsCadastrados(chipsCadastrados.getChipsCadastradosArray());
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (TuxedoWarningException twe) {
			log.warn("ManterChipController.alterarChip" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgErro", twe.getXmlHeader().getStatusText());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward pesquisarChip(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ManterChipFormBean form = (ManterChipFormBean) formParam;

		user = ConstantesCRM.getCurrentUser(request.getSession());

		try {
			FidelizacaoManutChipVO chipsCadastrados = manterChipFacade.getChipsCadastradosPorDDD(user, request.getParameter("ddd"));

			manterChipFormBean.setChipsCadastrados(chipsCadastrados.getChipsCadastradosArray());
			form.setChipsCadastrados(chipsCadastrados.getChipsCadastradosArray());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward excluirChip(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ManterChipFormBean form = (ManterChipFormBean) formParam;

		user = ConstantesCRM.getCurrentUser(request.getSession());

		try {
			FidelizacaoManutChipVO fidelizacaoManutChip = FidelizacaoManutChipVO.Factory.newInstance();
			fidelizacaoManutChip.setIdChip(request.getParameter("idExcluir"));
			RetornoVO retorno = manterChipFacade.excluirChip(user, fidelizacaoManutChip);

			if (!"INSERIDO".equals(retorno.getDescricao())) {
				request.setAttribute("msgErro", retorno.getDescricao());
			} else {
				request.setAttribute("msgOk", "Chip excluído com sucesso!");
				FidelizacaoManutChipVO chipsCadastrados = manterChipFacade.getChipsCadastradosPorDDD(user, "");

				manterChipFormBean.setChipsCadastrados(chipsCadastrados.getChipsCadastradosArray());
				form.setChipsCadastrados(chipsCadastrados.getChipsCadastradosArray());
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterChipController.excluirChip" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgErro", twe.getXmlHeader().getStatusText());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

	}

	public ManterChipFormBean getManterChipFormBean() {
		if (this.manterChipFormBean == null) {
			return new ManterChipFormBean();
		} else {
			return this.manterChipFormBean;
		}
	}

	public static class ManterChipFormBean extends ActionForm {
		private String id;

		private String ddd;

		private String vlChipPre;

		private String vlChipAvulso;

		private String cdChipPre;

		private String cdChipAvulso;

		private br.com.vivo.fo.fidelizacao.vo.FidelizacaoManutChipVODocument.FidelizacaoManutChipVO.ChipsCadastrados[] chipsCadastrados;

		private br.com.vivo.fo.admsistemas.vo.ListaIdDescricaoVODocument.ListaIdDescricaoVO.ItemListaIdDescricaoVO[] listaDDD;

		private FidelizacaoManutChipVO fidelizacaoManutChipVO;

		public void setFidelizacaoManutChipVO(FidelizacaoManutChipVO fidelizacaoManutChipVO) {
			this.fidelizacaoManutChipVO = fidelizacaoManutChipVO;
		}

		public FidelizacaoManutChipVO getFidelizacaoManutChipVO() {
			if (this.fidelizacaoManutChipVO == null) {
				this.fidelizacaoManutChipVO = FidelizacaoManutChipVO.Factory.newInstance();
			}
			return this.fidelizacaoManutChipVO;
		}

		public void setListaDDD(br.com.vivo.fo.admsistemas.vo.ListaIdDescricaoVODocument.ListaIdDescricaoVO.ItemListaIdDescricaoVO[] listaDDD) {
			this.listaDDD = listaDDD;
		}

		public br.com.vivo.fo.admsistemas.vo.ListaIdDescricaoVODocument.ListaIdDescricaoVO.ItemListaIdDescricaoVO[] getListaDDD() {
			if (this.listaDDD == null) {
				this.listaDDD = new ItemListaIdDescricaoVO[0];
			}
			return this.listaDDD;
		}

		public void setChipsCadastrados(br.com.vivo.fo.fidelizacao.vo.FidelizacaoManutChipVODocument.FidelizacaoManutChipVO.ChipsCadastrados[] chipsCadastrados) {
			this.chipsCadastrados = chipsCadastrados;
		}

		public br.com.vivo.fo.fidelizacao.vo.FidelizacaoManutChipVODocument.FidelizacaoManutChipVO.ChipsCadastrados[] getChipsCadastrados() {
			if (this.chipsCadastrados == null || this.chipsCadastrados.length == 0) {
				this.chipsCadastrados = new ChipsCadastrados[0];
			}
			return this.chipsCadastrados;
		}

		public void setCdChipAvulso(String cdChipAvulso) {
			this.cdChipAvulso = cdChipAvulso;
		}

		public String getCdChipAvulso() {
			return this.cdChipAvulso;
		}

		public void setCdChipPre(String cdChipPre) {
			this.cdChipPre = cdChipPre;
		}

		public String getCdChipPre() {
			return this.cdChipPre;
		}

		public void setVlChipAvulso(String vlChipAvulso) {
			this.vlChipAvulso = vlChipAvulso;
		}

		public String getVlChipAvulso() {
			return this.vlChipAvulso;
		}

		public void setVlChipPre(String vlChipPre) {
			this.vlChipPre = vlChipPre;
		}

		public String getVlChipPre() {
			return this.vlChipPre;
		}

		public void setDdd(String ddd) {
			this.ddd = ddd;
		}

		public String getDdd() {
			return this.ddd;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}
	}

}
