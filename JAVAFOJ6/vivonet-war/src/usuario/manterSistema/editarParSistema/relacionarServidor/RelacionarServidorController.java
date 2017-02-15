package usuario.manterSistema.editarParSistema.relacionarServidor;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterSistema.ManterSistemaFacade;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.RelacionarSistemaServidorVODocument.RelacionarSistemaServidorVO;
import br.com.vivo.fo.usuario.vo.SistemaIDVODocument.SistemaIDVO;
import br.com.vivo.fo.usuario.vo.SistemaServidorExistenteUsuarioVODocument.SistemaServidorExistenteUsuarioVO;
import br.com.vivo.fo.usuario.vo.SistemaServidorRelacionadoUsuarioVODocument.SistemaServidorRelacionadoUsuarioVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class RelacionarServidorController extends AbstractAction {

	private static final long serialVersionUID = 6121717765731739461L;

	@EJB
	private ManterSistemaFacade controlSistema;

	// atributos e objetos
	private RelServidorForm relServidorForm = new RelServidorForm();

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvaRelServidor".equals(mapping.getParameter())) {
			return salvaRelServidor(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarServidor.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		try {
			log.debug("RelacionarServidorController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
			relServidorForm = new RelServidorForm();

			// Recupera o id do sistema
			relServidorForm.setIdSistema(request.getParameter("sistemaId"));

			// instancia o id do sistema
			SistemaIDVO sistemaIdListar = SistemaIDVO.Factory.newInstance();
			sistemaIdListar.setIdSistema(relServidorForm.getIdSistema());

			// Chama o servico para listagem dos servidores
			RelacionarSistemaServidorVO retornoRelacaoServidores = RelacionarSistemaServidorVO.Factory.newInstance();
			retornoRelacaoServidores = controlSistema.listaServidoresRelacionadosSistema(sistemaIdListar, ConstantesCRM.getCurrentUser(request.getSession()));

			// Armazena o resultado nas variaveis do formulario

			// Testa se houve retorno nos servidores existentes
			if (retornoRelacaoServidores.getSistemaServidorExistenteUsuarioVOArray().length > 0) {
				relServidorForm.setServidoresExistentesVO(retornoRelacaoServidores.getSistemaServidorExistenteUsuarioVOArray());
			}

			// Testa se houve retorno nos servidores relacionados
			if (retornoRelacaoServidores.getSistemaServidorRelacionadoUsuarioVOArray().length > 0) {
				relServidorForm.setServidoresRelacionadosVO(retornoRelacaoServidores.getSistemaServidorRelacionadoUsuarioVOArray());
			}

		} catch (Exception e) {
			log.error("RelacionarServidorController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarServidor.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaRelServidor(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelServidorForm form = (RelServidorForm) formParam;

		try {
			log.debug("RelacionarServidorController:salvaRelServidor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

			RelacionarSistemaServidorVO relacionarSistemaServidorVO = RelacionarSistemaServidorVO.Factory.newInstance();
			RelacionarSistemaServidorVO retorno = RelacionarSistemaServidorVO.Factory.newInstance();

			// Percorre o array de ids de servidoresRelacionados e grava no
			// sistemaSalvarServidoresRelacionadosVO
			for (int i = 0; i < form.getServidoresRelacionados().length; i++) {
				SistemaServidorRelacionadoUsuarioVO servidoresRelacionados = SistemaServidorRelacionadoUsuarioVO.Factory.newInstance();
				servidoresRelacionados.setIdServidor(form.servidoresRelacionados[i]);
				relacionarSistemaServidorVO.insertNewSistemaServidorRelacionadoUsuarioVO(i);
				relacionarSistemaServidorVO.setSistemaServidorRelacionadoUsuarioVOArray(i, servidoresRelacionados);
			}

			// Grava o id do Sistema
			relacionarSistemaServidorVO.setIdSistema(relServidorForm.getIdSistema());
			// salva o conjunto de servidores relacionados.
			// recebe como retorno a lista de servidores relacionados e
			// existentes.
			retorno = controlSistema.salvarServidoresRelacionadosSistema(relacionarSistemaServidorVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// atualiza as variaveis do formulario que armazenam essas
			// informacoes.
			if (retorno.getSistemaServidorExistenteUsuarioVOArray().length > 0) {
				relServidorForm.setServidoresExistentesVO(retorno.getSistemaServidorExistenteUsuarioVOArray());
			} else {
				RelacionarSistemaServidorVO res = RelacionarSistemaServidorVO.Factory.newInstance();
				relServidorForm.setServidoresExistentesVO(res.getSistemaServidorExistenteUsuarioVOArray());

			}
			if (retorno.getSistemaServidorRelacionadoUsuarioVOArray().length > 0) {
				relServidorForm.setServidoresRelacionadosVO(retorno.getSistemaServidorRelacionadoUsuarioVOArray());
			} else {
				RelacionarSistemaServidorVO re = RelacionarSistemaServidorVO.Factory.newInstance();
				relServidorForm.setServidoresRelacionadosVO(re.getSistemaServidorRelacionadoUsuarioVOArray());
			}

			request.setAttribute("msgError", ConstantesCRM.SSucesso);

		} catch (Exception e) {

			log.error("RelacionarServidorController:salvaRelServidor" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class RelServidorForm extends ActionForm {

		private static final long serialVersionUID = 802783267842699091L;

		private SistemaServidorRelacionadoUsuarioVO[] servidoresRelacionadosVO;
		private SistemaServidorExistenteUsuarioVO[] servidoresExistentesVO;
		private String[] servidoresRelacionados;
		private String[] servidoresExistentes;
		private String idSistema;

		public RelServidorForm() {
			// //////////////////////////////////
			// Bloco de Inicializacao - Begin //
			// //////////////////////////////////
			idSistema = ConstantesCRM.SVAZIO;
			servidoresExistentesVO = new SistemaServidorExistenteUsuarioVO[0];
			servidoresRelacionadosVO = new SistemaServidorRelacionadoUsuarioVO[0];
			servidoresExistentes = new String[0];
			servidoresRelacionados = new String[0];
			// ////////////////////////////////
			// Bloco de Inicializacao - End //
			// ////////////////////////////////
		}

		public void setIdSistema(String idSistema) {

			this.idSistema = idSistema;
		}

		public String getIdSistema() {

			return this.idSistema;
		}

		public void setServidoresExistentes(String[] servidoresExistentes) {

			this.servidoresExistentes = servidoresExistentes;
		}

		public String[] getServidoresExistentes() {

			return this.servidoresExistentes;
		}

		public void setServidoresRelacionados(String[] servidoresRelacionados) {

			this.servidoresRelacionados = servidoresRelacionados;
		}

		public String[] getServidoresRelacionados() {

			return this.servidoresRelacionados;
		}

		public void setServidoresExistentesVO(SistemaServidorExistenteUsuarioVO[] servidoresExistentesVO) {

			this.servidoresExistentesVO = servidoresExistentesVO;
		}

		public SistemaServidorExistenteUsuarioVO[] getServidoresExistentesVO() {

			return this.servidoresExistentesVO;
		}

		public void setServidoresRelacionadosVO(SistemaServidorRelacionadoUsuarioVO[] servidoresRelacionadosVO) {

			this.servidoresRelacionadosVO = servidoresRelacionadosVO;
		}

		public SistemaServidorRelacionadoUsuarioVO[] getServidoresRelacionadosVO() {

			return this.servidoresRelacionadosVO;
		}
	}

	public RelServidorForm getRelServidorForm() {
		return (this.relServidorForm);
	}

}
