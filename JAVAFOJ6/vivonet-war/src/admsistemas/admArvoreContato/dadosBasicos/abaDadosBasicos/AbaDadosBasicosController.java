package admsistemas.admArvoreContato.dadosBasicos.abaDadosBasicos;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmDadosBasicosVODocument.AdmDadosBasicosVO;
import br.com.vivo.fo.admsistemas.vo.AdmDadosBasicosWVODocument.AdmDadosBasicosWVO;
import br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmMensagemAvisoVODocument.AdmMensagemAvisoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoFechamentoVODocument.AdmTipoFechamentoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoRetornoContatoVODocument.AdmTipoRetornoContatoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class AbaDadosBasicosController extends AbstractAction {

	private static final long serialVersionUID = -2057711969044492270L;

	private DadosBasicosForm dadosBasicosForm;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvaDados".equals(mapping.getParameter())) {
			return salvaDados(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularDadosBasicos.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AbaDadosBasicosController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);
			dadosBasicosForm = new DadosBasicosForm();

			// nova instancia.
			AdmIdContatoVO idContatoVO = AdmIdContatoVO.Factory.newInstance();

			// Grava o id da folha de contato
			idContatoVO.setIdContato(request.getParameter("idContatoFolha"));

			// Grava o id da folha de contato
			dadosBasicosForm.setIdContato(request.getParameter("idContatoFolha"));
			dadosBasicosForm.setIdContato(idContatoVO.getIdContato());
			idContatoVO.setIdContato(dadosBasicosForm.getIdContato());

			AdmDadosBasicosWVO admDadosBasicosWVO = AdmDadosBasicosWVO.Factory.newInstance();

			// AdmIdContatoVO admIdContatoVO = AdmIdContatoVO.Factory.newInstance();
			// admDadosBasicosWVO =
			// controlDadosBasicosWF.montaDadosBasicosW(admIdContatoVO,ConstantesCRM.getCurrentUser(request.getSession()));

			montaDadosBasicos(admDadosBasicosWVO);

		} catch (Exception e) {
			log.error("AbaDadosBasicosController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void montaDadosBasicos(AdmDadosBasicosWVO admDadosBasicosWVO) {
		dadosBasicosForm.setIdContato(dadosBasicosForm.getIdContato());
		dadosBasicosForm.setQtDiasPrazoContato(admDadosBasicosWVO.getQtDiasPrazoContato());
		dadosBasicosForm.setVlPesoContato(admDadosBasicosWVO.getVlPesoContato());
		dadosBasicosForm.setTipoRetornoContatoVO(admDadosBasicosWVO.getTipoRetornoContato().getAdmTipoRetornoContatoVOArray());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularDadosBasicos.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaDados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AbaDadosBasicosController:salvaDados" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		DadosBasicosForm formDadosBasicos = (DadosBasicosForm) formParam;
		dadosBasicosForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			AdmDadosBasicosVO admDadosBasicosVO = AdmDadosBasicosVO.Factory.newInstance();
			admDadosBasicosVO.setIdMensagemAviso(formDadosBasicos.getMensagem());
			admDadosBasicosVO.setInProcessoTecnico(formDadosBasicos.getProcessoTec());
			// admDadosBasicosVO.setIdFechamentoImediato(formDadosBasicos.getFechamento());
			// admDadosBasicosVO.setQtDiasPrazoContato(formDadosBasicos.getQtDiasPrazoContato());
			// admDadosBasicosVO.setVlPesoContato(formDadosBasicos.getVlPesoContato());

			AdmTipoRetornoContatoVO[] admTipoRetornoContatoVO = new AdmTipoRetornoContatoVO[dadosBasicosForm.getArraytipoRetornoContato().length];
			String[] tipoRetornoContatoVO = new String[dadosBasicosForm.getArraytipoRetornoContato().length];
			tipoRetornoContatoVO = dadosBasicosForm.getArraytipoRetornoContato();

			for (int i = 0; i < tipoRetornoContatoVO.length; i++) {
				admTipoRetornoContatoVO[i] = AdmTipoRetornoContatoVO.Factory.newInstance();
				admTipoRetornoContatoVO[i].setIdTipoRetornoContato(tipoRetornoContatoVO[i]);
			}

			// admDadosBasicosVO.addNewTipoRetornoContato().setAdmTipoRetornoContatoVOArray(admTipoRetornoContatoVO);

			// admDadosBasicosVO =
			// controlDadosBasicos.salvaConfigDadosBasicos(admDadosBasicosVO,ConstantesCRM.getCurrentUser(request.getSession())
			// );

			// Caso tenham sido retornados dados, atualiza esses dados no formulário
			// atualizaDadosForm(admDadosBasicosVO);

			/*
			 * }catch(TuxedoWarningException twe) { log.warn("AbaDadosBasicosController:salvaDados"+"( "+
			 * ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " +
			 * twe.getXmlHeader().getStatusText()); dadosBasicosForm.setMsgError(twe.getXmlHeader().getStatusText());
			 */
		} catch (Exception e) {
			log.error("AbaDadosBasicosController:salvaDados" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("top.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class DadosBasicosForm extends ActionForm {

		private static final long serialVersionUID = 9127882026787631485L;

		private String msgError = ConstantesCRM.SVAZIO;
		private AdmTipoRetornoContatoVO[] tipoRetornoContatoVO;
		private String vlPesoContato;
		private String qtDiasPrazoContato;
		private String[] arraytipoRetornoContato;
		private AdmMensagemAvisoVO[] listaMensagem;
		private AdmTipoFechamentoVO[] listaFechamento;
		private String mensagem;
		private String processoTec;
		private String fechamento;
		private String idContato;

		public DadosBasicosForm() {
			idContato = ConstantesCRM.SVAZIO;
			fechamento = ConstantesCRM.SVAZIO;
			processoTec = ConstantesCRM.SVAZIO;
			mensagem = ConstantesCRM.SVAZIO;
			listaFechamento = new AdmTipoFechamentoVO[0];
			listaMensagem = new AdmMensagemAvisoVO[0];
		}

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setFechamento(String fechamento) {
			this.fechamento = fechamento;
		}

		public String getFechamento() {
			return this.fechamento;
		}

		public void setProcessoTec(String processoTec) {
			this.processoTec = processoTec;
		}

		public String getProcessoTec() {
			return this.processoTec;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}

		public String getMensagem() {
			return this.mensagem;
		}

		public void setListaFechamento(AdmTipoFechamentoVO[] listaFechamento) {
			this.listaFechamento = listaFechamento;
		}

		public AdmTipoFechamentoVO[] getListaFechamento() {
			return this.listaFechamento;
		}

		public void setListaMensagem(AdmMensagemAvisoVO[] listaMensagem) {
			this.listaMensagem = listaMensagem;
		}

		public AdmMensagemAvisoVO[] getListaMensagem() {
			return this.listaMensagem;
		}

		public void setArraytipoRetornoContato(String[] arraytipoRetornoContato) {
			this.arraytipoRetornoContato = arraytipoRetornoContato;
		}

		public String[] getArraytipoRetornoContato() {
			if (this.arraytipoRetornoContato == null || this.arraytipoRetornoContato.length == 0) {
				this.arraytipoRetornoContato = new String[1];
			}
			return this.arraytipoRetornoContato;
		}

		public void setQtDiasPrazoContato(String qtDiasPrazoContato) {
			this.qtDiasPrazoContato = qtDiasPrazoContato;
		}

		public String getQtDiasPrazoContato() {
			return this.qtDiasPrazoContato;
		}

		public void setVlPesoContato(String vlPesoContato) {
			this.vlPesoContato = vlPesoContato;
		}

		public String getVlPesoContato() {
			return this.vlPesoContato;
		}

		public void setTipoRetornoContatoVO(AdmTipoRetornoContatoVO[] tipoRetornoContatoVO) {
			this.tipoRetornoContatoVO = tipoRetornoContatoVO;
		}

		public AdmTipoRetornoContatoVO[] getTipoRetornoContatoVO() {
			return this.tipoRetornoContatoVO;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public DadosBasicosForm getDadosBasicosForm() {
		return this.dadosBasicosForm;
	}
}