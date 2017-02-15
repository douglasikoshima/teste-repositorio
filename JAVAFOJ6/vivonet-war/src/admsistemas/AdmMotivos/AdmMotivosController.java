package admsistemas.AdmMotivos;

import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoErrorException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO;
import br.com.vivo.fo.workflow.vo.WFManterMotivosVODocument.WFManterMotivosVO;
import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;

import com.indracompany.actions.AbstractAction;

public class AdmMotivosController extends AbstractAction {

	private static final long serialVersionUID = -7211781965689646690L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.motivos.MotivosFacade motivosFacade;

	protected global.Global globalApp = new global.Global();

	private static HashMap<String, String> mapErros = new HashMap<String, String>();

	// bloco de inicializacao estática para mensagens de erro
	static {
		mapErros.put("09W0001", "Descrição de Motivo Duplicado!\\nTente outra descrição.");
		mapErros.put("09W0002", "ID Motivo inválido!\\nEste Motivo pode ter sido removido\\ndurante essa alteração.\\nFaça a Pesquisa novamente!");
	}

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("lerMotivo".equals(mapping.getParameter())) {
			return lerMotivo(mapping, form, request, response);
		} else if ("excluirMotivo".equals(mapping.getParameter())) {
			return excluirMotivo(mapping, form, request, response);
		} else if ("gravarMotivo".equals(mapping.getParameter())) {
			return gravarMotivo(mapping, form, request, response);
		} else if ("lerMotivoAcoes".equals(mapping.getParameter())) {
			return lerMotivoAcoes(mapping, form, request, response);
		} else if ("associarMotivo".equals(mapping.getParameter())) {
			return associarMotivo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AdmMotivosController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PesquisaMotivosForm form = (PesquisaMotivosForm) formParam;
		// String user = ConstantesCRM.getCurrentUser(request.getSession());
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisaResultado.jsp"
	 */
	public ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AdmMotivosController:pesquisar(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PesquisaMotivosForm form = (PesquisaMotivosForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFManterMotivosVO pesquisa = WFManterMotivosVO.Factory.newInstance();
		pesquisa.setOperacao(3);
		WFMotivoVO motivo = pesquisa.addNewWFMotivoVO();
		motivo.setDsMotivo(form.getDsMotivo());
		PesquisaMotivosForm pesquisaMotivosForm = new PesquisaMotivosForm();
		pesquisaMotivosForm.setPesquisaMotivos(motivosFacade.pesquisar(user, pesquisa));
		request.setAttribute("pesquisaMotivosForm", pesquisaMotivosForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluir.jsp"
	 */
	public ActionForward lerMotivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AdmMotivosController:lerMotivo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		MotivoForm motivoForm = (MotivoForm) formParam;
		if (motivoForm.getIdMotivo() == 0) {
			motivoForm.setDsMotivo(ConstantesCRM.SVAZIO);
		}
		request.setAttribute("motivoForm", motivoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="retorno.jsp"
	 */
	public ActionForward excluirMotivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AdmMotivosController:excluirMotivo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		MotivoForm form = (MotivoForm) formParam;
		WFManterMotivosVO manterMotivos = WFManterMotivosVO.Factory.newInstance();
		manterMotivos.setOperacao(2);
		WFMotivoVO motivo = manterMotivos.addNewWFMotivoVO();
		motivo.setIdMotivo(String.valueOf(form.getIdMotivo()));
		MotivoForm motivoForm = new MotivoForm();
		try {
			// chamar facade
			motivosFacade.crudMotivos(user, manterMotivos);
		} catch (TuxedoErrorException e) {
			tratarTuxedoException(motivoForm, e, request);
		}
		request.setAttribute("motivoForm", motivoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="retorno.jsp"
	 */
	public ActionForward gravarMotivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AdmMotivosController:gravarMotivo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		MotivoForm form = (MotivoForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFManterMotivosVO manterMotivos = WFManterMotivosVO.Factory.newInstance();

		if (form.getIdMotivo() == 0) {
			// não tem ID, operação é incluir novo registro...
			manterMotivos.setOperacao(0);
		} else {
			// se possui um ID, está alterando
			manterMotivos.setOperacao(1);
		}

		WFMotivoVO motivo = manterMotivos.addNewWFMotivoVO();
		motivo.setIdMotivo(String.valueOf(form.getIdMotivo()));
		motivo.setDsMotivo(form.getDsMotivo());
		MotivoForm motivoForm = new MotivoForm();
		try {
			// chamar facade
			motivosFacade.crudMotivos(user, manterMotivos);
		} catch (TuxedoWarningException e) {
			tratarTuxedoException(motivoForm, e, request);
		}
		request.setAttribute("motivoForm", motivoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="associar.jsp"
	 */
	public ActionForward lerMotivoAcoes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AdmMotivosController:lerMotivoAcoes(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		MotivoForm form = (MotivoForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFManterMotivosVO manterMotivos = WFManterMotivosVO.Factory.newInstance();
		manterMotivos.setOperacao(0);
		WFMotivoVO motivo = manterMotivos.addNewWFMotivoVO();
		motivo.setIdMotivo(String.valueOf(form.getIdMotivo()));
		// chamar facade
		manterMotivos = motivosFacade.lerMotivoAcoes(user, manterMotivos);
		MotivoForm motivoForm = form;
		motivoForm.setWFManterMotivosVO(manterMotivos);
		request.setAttribute("motivoForm", motivoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="retorno.jsp"
	 */
	public ActionForward associarMotivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AdmMotivosController:associarMotivo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		MotivoForm form = (MotivoForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFManterMotivosVO manterMotivos = WFManterMotivosVO.Factory.newInstance();
		manterMotivos.setOperacao(1);
		WFMotivoVO motivo = manterMotivos.addNewWFMotivoVO();
		motivo.setIdMotivo(String.valueOf(form.getIdMotivo()));
		WFAcaoVO acao = null;
		for (int i = 0; i < form.getAcoesAssoc().length; i++) {
			acao = motivo.addNewWFAcaoVO();
			acao.setIdAtividade(String.valueOf(form.getAcoesAssoc()[i]));
			acao.setDsAtividade(form.getAcoesAssocDS()[i]);
		}
		// chamada do facade/servico
		MotivoForm motivoForm = new MotivoForm();
		try {
			motivosFacade.gravarMotivoAcoes(user, manterMotivos);
		} catch (TuxedoWarningException e) {
			tratarTuxedoException(motivoForm, e, request);
		}
		request.setAttribute("motivoForm", motivoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void tratarTuxedoException(MotivoForm f, TuxedoException e, HttpServletRequest request) throws TuxedoException {
		String erros = (String) mapErros.get(e.getXmlHeader().getStatusCode());
		if (erros == null) {
			throw e;
		} else {
			log.warn("AdmMotivosController:tratarTuxedoException" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + erros);
			f.setErroMsg(erros);
		}
	}

	public static class PesquisaMotivosForm extends ActionForm {

		private static final long serialVersionUID = 3900732425276212496L;
		private WFMotivoVO[] pesquisaMotivos;
		private String dsMotivo;

		public void setDsMotivo(String dsMotivo) {
			this.dsMotivo = dsMotivo;
		}

		public String getDsMotivo() {
			return this.dsMotivo;
		}

		public void setPesquisaMotivos(WFMotivoVO[] pesquisaMotivos) {
			this.pesquisaMotivos = pesquisaMotivos;
		}

		public WFMotivoVO[] getPesquisaMotivos() {
			if (this.pesquisaMotivos == null) {
				return new WFMotivoVO[0];
			} else {
				return this.pesquisaMotivos;
			}
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class MotivoForm extends ActionForm {

		private static final long serialVersionUID = -6753194611718383887L;
		private String[] acoesAssocDS;
		private String erroMsg;
		private int[] acoesAssoc;
		private int[] acoesDisp;
		private WFManterMotivosVO wFManterMotivosVO;
		private String dsMotivo;
		private int idMotivo;

		public void setIdMotivo(int idMotivo) {
			this.idMotivo = idMotivo;
		}

		public int getIdMotivo() {
			return this.idMotivo;
		}

		public void setDsMotivo(String dsMotivo) {
			this.dsMotivo = dsMotivo;
		}

		public String getDsMotivo() {
			return this.dsMotivo;
		}

		public void setWFManterMotivosVO(WFManterMotivosVO wFManterMotivosVO) {
			this.wFManterMotivosVO = wFManterMotivosVO;
		}

		public WFManterMotivosVO getWFManterMotivosVO() {
			return this.wFManterMotivosVO;
		}

		public void setAcoesDisp(int[] acoesDisp) {
			this.acoesDisp = acoesDisp;
		}

		public int[] getAcoesDisp() {
			if (this.acoesDisp == null) {
				return new int[0];
			} else {
				return this.acoesDisp;
			}
		}

		public void setAcoesAssoc(int[] acoesAssoc) {
			this.acoesAssoc = acoesAssoc;
		}

		public int[] getAcoesAssoc() {
			if (this.acoesAssoc == null) {
				return new int[0];
			} else {
				return this.acoesAssoc;
			}
		}

		public void setErroMsg(String erroMsg) {
			this.erroMsg = erroMsg;
		}

		public String getErroMsg() {
			return this.erroMsg;
		}

		public void setAcoesAssocDS(String[] acoesAssocDS) {
			this.acoesAssocDS = acoesAssocDS;
		}

		public String[] getAcoesAssocDS() {
			if (this.acoesAssocDS == null || this.acoesAssocDS.length == 0) {
				this.acoesAssocDS = new String[1];
			}
			return this.acoesAssocDS;
		}
	}
}
