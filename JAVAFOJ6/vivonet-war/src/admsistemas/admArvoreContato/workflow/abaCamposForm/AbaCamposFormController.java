package admsistemas.admArvoreContato.workflow.abaCamposForm;

import java.io.BufferedOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Vector;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreGruposCamposDependentesVODocument.AdmArvoreGruposCamposDependentesVO;
import br.com.vivo.fo.admsistemas.vo.AdmCampoVODocument.AdmCampoVO;
import br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioSimplVODocument.AdmCamposFormularioSimplVO;
import br.com.vivo.fo.admsistemas.vo.AdmCamposFormularioVODocument.AdmCamposFormularioVO;
import br.com.vivo.fo.admsistemas.vo.AdmCamposHistoricoVODocument.AdmCamposHistoricoVO;
import br.com.vivo.fo.admsistemas.vo.AdmClassificadorCampoVODocument.AdmClassificadorCampoVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoUFOperadoraVODocument.AdmContatoUFOperadoraVO;
import br.com.vivo.fo.admsistemas.vo.AdmFaseProcessoVODocument.AdmFaseProcessoVO;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposDependentesVODocument.AdmGrupoCamposDependentesVO;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoCamposVODocument.AdmGrupoCamposVO;
import br.com.vivo.fo.admsistemas.vo.AdmGruposCamposDependentesVODocument.AdmGruposCamposDependentesVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoLinhaVODocument.AdmTipoLinhaVO;
import br.com.vivo.fo.admsistemas.vo.CampoObjetoFormularioVODocument.CampoObjetoFormularioVO;
import br.com.vivo.fo.admsistemas.vo.TreeItemVODocument.TreeItemVO;
import br.com.vivo.fo.cliente.vo.AjaxErrorHandlerVODocument.AjaxErrorHandlerVO;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.camposFormulario.CamposFormulario;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UFOperadoraUsuarioVODocument.UFOperadoraUsuarioVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({"rawtypes","unused","unchecked"})
public class AbaCamposFormController extends AbstractAction {

	private static final long serialVersionUID = -5793021428355430660L;

	@EJB
	private CamposFormulario camposFormularioTux;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	public global.Global globalApp = new global.Global();

	private FormFormulario formFormulario = new FormFormulario();

	private boolean flag = true;
	private int idUnico = 1;

	String user = ConstantesCRM.SVAZIO;

	private String idFirstParent = ConstantesCRM.SVAZIO;
	private String idFirstParentExclusao = ConstantesCRM.SVAZIO;
	private TreeItemVO parent = null;
	private int qtdeNiveis = 0;

	private static transient Logger log = new Logger("admsistemas");

	static final String MENSAGEM_GERAL = "Houve um erro durante carregamento dos dados. Tente novamente dentro de alguns instantes ou contate o Helpdesk.";
	static final String CAMPOS = "CAMPOS";

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("excluirCampoGrupo".equals(mapping.getParameter())) {
			return excluirCampoGrupo(mapping, form, request, response);
		} else if ("associarCamposGrupos".equals(mapping.getParameter())) {
			return associarCamposGrupos(mapping, form, request, response);
		} else if ("getCombosClassificadorComponente".equals(mapping.getParameter())) {
			return getCombosClassificadorComponente(mapping, form, request, response);
		} else if ("configurarFormulario".equals(mapping.getParameter())) {
			return configurarFormulario(mapping, form, request, response);
		} else if ("visualizarFormulario".equals(mapping.getParameter())) {
			return visualizarFormulario(mapping, form, request, response);
		} else if ("agruparCampos".equals(mapping.getParameter())) {
			return agruparCampos(mapping, form, request, response);
		} else if ("camposDependentes".equals(mapping.getParameter())) {
			return camposDependentes(mapping, form, request, response);
		} else if ("getLupaCamposDependentes".equals(mapping.getParameter())) {
			return getLupaCamposDependentes(mapping, form, request, response);
		} else if ("getCamposDependentes".equals(mapping.getParameter())) {
			return getCamposDependentes(mapping, form, request, response);
		} else if ("getListaCamposClassificadoresComponentes".equals(mapping.getParameter())) {
			return getListaCamposClassificadoresComponentes(mapping, form, request, response);
		} else if ("salvaCamposForm".equals(mapping.getParameter())) {
			return salvaCamposForm(mapping, form, request, response);
		} else if ("alteraCampos".equals(mapping.getParameter())) {
			return alteraCampos(mapping, form, request, response);
		} else if ("removeCampos".equals(mapping.getParameter())) {
			return removeCampos(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			formFormulario = new FormFormulario();
			formFormulario.setIdContato(request.getParameter("idContato"));
			request.setAttribute("formFormulario", formFormulario);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("AbaCamposFormController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	private String getTipoObjeto(String tipoObjeto) {
		if ("CAMPOS".equals(tipoObjeto)) {
			tipoObjeto = "Campo";
		} else if ("COMPONENTES".equals(tipoObjeto)) {
			tipoObjeto = "Componente";
		}
		return tipoObjeto;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularCamposForm.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward excluirCampoGrupo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		String idCampoObjeto = request.getParameter("idCampoObjeto");
		String idClassificadorComponente = request.getParameter("idClassificadorComponente");
		FormFormulario form = (FormFormulario) formParam;

		CampoObjetoFormularioVO[] campoObjetoFormularioVO = new CampoObjetoFormularioVO[getFormFormulario().getListaAssociadaCamposObjetos().length - 1];
		int contador = 0;
		for (int i = 0; i < campoObjetoFormularioVO.length + 1; i++) {
			if (!getFormFormulario().getListaAssociadaCamposObjetos()[i].getIdClassificadorComponente().equals(idClassificadorComponente) || (getFormFormulario().getListaAssociadaCamposObjetos()[i].getIdClassificadorComponente().equals(idClassificadorComponente) && !getFormFormulario().getListaAssociadaCamposObjetos()[i].getIdCampoObjeto().equals(idCampoObjeto))) {
				campoObjetoFormularioVO[contador] = CampoObjetoFormularioVO.Factory.newInstance();
				campoObjetoFormularioVO[contador].setIdCampoObjeto(getFormFormulario().getListaAssociadaCamposObjetos()[i].getIdCampoObjeto());
				campoObjetoFormularioVO[contador].setNmCampoObjeto(getFormFormulario().getListaAssociadaCamposObjetos()[i].getNmCampoObjeto());
				campoObjetoFormularioVO[contador].setInClassificadorComponente(getFormFormulario().getListaAssociadaCamposObjetos()[i].getInClassificadorComponente());
				campoObjetoFormularioVO[contador].setIdClassificadorComponente(getFormFormulario().getListaAssociadaCamposObjetos()[i].getIdClassificadorComponente());
				campoObjetoFormularioVO[contador].setNmClassificadorComponente(getFormFormulario().getListaAssociadaCamposObjetos()[i].getNmClassificadorComponente());
				contador++;
			}
		}
		getFormFormulario().setListaAssociadaCamposObjetos(campoObjetoFormularioVO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularCamposForm.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward associarCamposGrupos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		FormFormulario form = (FormFormulario) formParam;
		try {

			int contador = 0;
			String[] arraySelectedIndex = request.getParameterValues("arraySelectedIndex");
			getFormFormulario().setInCamposComponentes(form.getInCamposComponentes());
			getFormFormulario().setIdClassificadorCampoAtual(form.getIdClassificadorCampoAtual());
			String[] infoSelects = new String[2];

			UFOperadoraUsuarioVO[] operadoras = new UFOperadoraUsuarioVO[form.getaOperadoraExistentes().length];
			for (int i = 0; i < operadoras.length; i++) {
				infoSelects = form.getaOperadoraExistentes()[i].split("\\|");
				operadoras[i] = UFOperadoraUsuarioVO.Factory.newInstance();
				operadoras[i].setIdUFOperadora(infoSelects[0]);
				operadoras[i].setDsUFOperadora(infoSelects[1]);
			}
			formFormulario.setUfOperadorasExistentes(operadoras);

			operadoras = new UFOperadoraUsuarioVO[form.getaOperadoraSelecionados().length];
			for (int i = 0; i < operadoras.length; i++) {
				infoSelects = form.getaOperadoraSelecionados()[i].split("\\|");
				operadoras[i] = UFOperadoraUsuarioVO.Factory.newInstance();
				operadoras[i].setIdUFOperadora(infoSelects[0]);
				operadoras[i].setDsUFOperadora(infoSelects[1]);
			}
			formFormulario.setUfOperadorasAssociadas(operadoras);

			AdmTipoLinhaVO[] tiposLinha = new AdmTipoLinhaVO[form.getaTipoLinhaExistentes().length];
			for (int i = 0; i < tiposLinha.length; i++) {
				infoSelects = form.getaTipoLinhaExistentes()[i].split("\\|");
				tiposLinha[i] = AdmTipoLinhaVO.Factory.newInstance();
				tiposLinha[i].setIdTipoLinha(infoSelects[0]);
				tiposLinha[i].setDsTipoLinha(infoSelects[1]);
			}
			formFormulario.setTipoLinhasExistentes(tiposLinha);

			tiposLinha = new AdmTipoLinhaVO[form.getaTipoLinhaSelecionados().length];
			for (int i = 0; i < tiposLinha.length; i++) {
				infoSelects = form.getaTipoLinhaSelecionados()[i].split("\\|");
				tiposLinha[i] = AdmTipoLinhaVO.Factory.newInstance();
				tiposLinha[i].setIdTipoLinha(infoSelects[0]);
				tiposLinha[i].setDsTipoLinha(infoSelects[1]);
			}
			formFormulario.setTipoLinhasAssociadas(tiposLinha);

			// Bloco utilizado para atualização do objeto de campos associados.
			if (arraySelectedIndex != null) {
				CampoObjetoFormularioVO[] campoObjetoFormularioVO = new CampoObjetoFormularioVO[getFormFormulario().getListaAssociadaCamposObjetos().length + arraySelectedIndex.length];
				for (int j = 0; j < getFormFormulario().getListaAssociadaCamposObjetos().length; j++) {
					campoObjetoFormularioVO[j] = CampoObjetoFormularioVO.Factory.newInstance();
					campoObjetoFormularioVO[j].setIdCampoObjeto(getFormFormulario().getListaAssociadaCamposObjetos()[j].getIdCampoObjeto());
					campoObjetoFormularioVO[j].setNmCampoObjeto(getFormFormulario().getListaAssociadaCamposObjetos()[j].getNmCampoObjeto());
					campoObjetoFormularioVO[j].setInClassificadorComponente(getTipoObjeto(getFormFormulario().getListaAssociadaCamposObjetos()[j].getInClassificadorComponente()));
					campoObjetoFormularioVO[j].setIdClassificadorComponente(getFormFormulario().getListaAssociadaCamposObjetos()[j].getIdClassificadorComponente());
					campoObjetoFormularioVO[j].setNmClassificadorComponente(getFormFormulario().getListaAssociadaCamposObjetos()[j].getNmClassificadorComponente());
				}

				for (int i = getFormFormulario().getListaAssociadaCamposObjetos().length; i < getFormFormulario().getListaAssociadaCamposObjetos().length + arraySelectedIndex.length; i++) {
					campoObjetoFormularioVO[i] = CampoObjetoFormularioVO.Factory.newInstance();
					campoObjetoFormularioVO[i].setIdCampoObjeto(getFormFormulario().getListaDisponiveisCamposObjetos()[Integer.parseInt(arraySelectedIndex[contador])].getIdCampoObjeto());
					campoObjetoFormularioVO[i].setNmCampoObjeto(getFormFormulario().getListaDisponiveisCamposObjetos()[Integer.parseInt(arraySelectedIndex[contador])].getNmCampoObjeto());
					campoObjetoFormularioVO[i].setInClassificadorComponente(getTipoObjeto(form.getInCamposComponentes()));
					campoObjetoFormularioVO[i].setIdClassificadorComponente(form.getIdClassificadorCampoAtual());
					campoObjetoFormularioVO[i].setNmClassificadorComponente(request.getParameter("nmClassificadorCampoAtual"));
					contador++;
				}
				getFormFormulario().setListaAssociadaCamposObjetos(campoObjetoFormularioVO);
			}

			// Bloco utilizado para remoção de itens do objeto de campos disponiveis.
			CampoObjetoFormularioVO[] itensDisponiveis = new CampoObjetoFormularioVO[getFormFormulario().getListaDisponiveisCamposObjetos().length - arraySelectedIndex.length];
			contador = 0;

			for (int j = 0; j < getFormFormulario().getListaDisponiveisCamposObjetos().length; j++) {

				if (!isInside(j, arraySelectedIndex)) {
					itensDisponiveis[contador] = CampoObjetoFormularioVO.Factory.newInstance();
					itensDisponiveis[contador].setIdCampoObjeto(getFormFormulario().getListaDisponiveisCamposObjetos()[j].getIdCampoObjeto());
					itensDisponiveis[contador].setNmCampoObjeto(getFormFormulario().getListaDisponiveisCamposObjetos()[j].getNmCampoObjeto());
					itensDisponiveis[contador].setInClassificadorComponente(getTipoObjeto(form.getInCamposComponentes()));
					itensDisponiveis[contador].setIdClassificadorComponente(form.getIdClassificadorCampoAtual());
					itensDisponiveis[contador].setNmClassificadorComponente(request.getParameter("nmClassificadorCampoAtual"));
					contador++;
				}
			}

			getFormFormulario().setListaDisponiveisCamposObjetos(itensDisponiveis);

			request.setAttribute("formFormulario", formFormulario);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {

			log.error("AbaCamposFormController:configurarFormulario" + "( " + user + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	private boolean isInside(int idx, String[] array) {
		boolean isAssociado = false;
		for (int i = 0; i < array.length; i++) {
			if (idx == Integer.parseInt(array[i])) {
				isAssociado = true;
				break;
			}
		}
		return isAssociado;
	}

	/*
	 * Método utilizado para verificar se um determinado objeto do tipo CampoObjetoFormularioVO já encontra-se
	 * associado.
	 */
	private boolean isAssociado(CampoObjetoFormularioVO obj) {
		boolean isAssociado = false;
		for (int i = 0; i < getFormFormulario().getListaAssociadaCamposObjetos().length; i++) {
			if (getFormFormulario().getListaAssociadaCamposObjetos()[i] != null && getFormFormulario().getListaAssociadaCamposObjetos()[i].getIdCampoObjeto().equals(obj.getIdCampoObjeto()) && getFormFormulario().getListaAssociadaCamposObjetos()[i].getIdClassificadorComponente().equals(obj.getIdClassificadorComponente())) {
				isAssociado = true;
				break;
			}
		}
		return isAssociado;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularCamposForm.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward getCombosClassificadorComponente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AbaCamposFormController:getCombosClassificadorComponente" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		StringBuffer xmlDados = new StringBuffer();
		FormFormulario form = (FormFormulario) formParam;
		try {

			AdmCamposFormularioVO admCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();
			admCamposFormularioVO.setInOperacao(form.getInOperacao());
			admCamposFormularioVO.setInCamposComponentes(form.getInCamposComponentes());
			admCamposFormularioVO = camposFormularioTux.carregaCamposFormulario(admCamposFormularioVO, user);

			getFormFormulario().setArrayCampoClassificador(admCamposFormularioVO.getAdmClassificadorCamposVO().getAdmClassificadorCampoVOArray());

			xmlDados.append(admCamposFormularioVO.xmlText().replaceAll("vo:", ""));
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
			log.debug("AbaCamposFormController:getCombosClassificadorComponente(" + user + ") >> FINALIZADO");

		} catch (Exception e) {
			AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(MENSAGEM_GERAL);

			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularCamposForm.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward configurarFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AbaCamposFormController:configurarFormulario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormFormulario form = (FormFormulario) formParam;
		if (formFormulario == null || request.getParameter("idFaseProcessoAtual") == null) {
			formFormulario = new FormFormulario();
		}

		AdmCamposFormularioVO admCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();

		try {

			if (request.getParameter("idFaseProcessoAtual") != null) {

				flag = false;

				AdmFaseProcessoVO[] admFaseProcessoVO = getFormFormulario().getArrayFaseProcesso();
				formFormulario = new FormFormulario();
				formFormulario.setArrayFaseProcesso(admFaseProcessoVO);
				formFormulario.setIdContato(request.getParameter("idContato"));
				formFormulario.setIdFaseProcessoAtual(request.getParameter("idFaseProcessoAtual"));
				formFormulario.setInCamposComponentes(form.getInCamposComponentes());

				admCamposFormularioVO.setInOperacao("GETDADOS");
				admCamposFormularioVO.setInCamposComponentes(form.getInCamposComponentes());
				admCamposFormularioVO.setIdFaseProcessoAtual(formFormulario.getIdFaseProcessoAtual());
				admCamposFormularioVO.setIdContato(formFormulario.getIdContato());

			} else {
				admCamposFormularioVO.setInOperacao("GETFASES");
				admCamposFormularioVO.setIdContato(request.getParameter("idContato"));
				admCamposFormularioVO.setInCamposComponentes(CAMPOS);
				formFormulario.setInCamposComponentes(CAMPOS);
				formFormulario.setIdFaseProcessoAtual(ConstantesCRM.SVAZIO);
			}

			if (request.getParameter("idContato") != null) {
				formFormulario.setIdContato(request.getParameter("idContato"));
			} else {
				formFormulario.setIdContato(form.getIdContato());
			}

			if (flag) {
				formFormulario.setIdContato(null);
			}

			admCamposFormularioVO = camposFormularioTux.carregaCamposFormulario(admCamposFormularioVO, user);

			/*
			 * if (flag) populaCombos(admCamposFormularioVO); else
			 */
			populaForm(admCamposFormularioVO);

			formFormulario.setListaFormularioLength("" + formFormulario.getListaAssociada().length);

		} catch (TuxedoWarningException twe) {

			log.warn("AbaCamposFormController:configurarFormulario" + "( " + user + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formFormulario.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {

			log.error("AbaCamposFormController:configurarFormulario" + "( " + user + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("formFormulario", formFormulario);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Método responsável pelo preview de um formulário
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="visualizarFormulario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward visualizarFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AbaCamposFormController:visualizarFormulario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormFormulario form = (FormFormulario) formParam;
		try {

			AtendimentoVO atendimentoVO = AtendimentoVO.Factory.newInstance();
			int qtdeCamposGrupos = 0;

			atendimentoVO = registrarContatoFacade.consultarContato(user, Integer.parseInt(form.getIdContato()), 0, Integer.parseInt(form.getIdFaseProcessoAtual()), getIdTipoLinha(), ConstantesCRM.SVAZIO, getIdUFOperadora(), ConstantesCRM.SVAZIO, 0, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, true);

			// Prepara apresentação de grupos de campos
			if (atendimentoVO.getArvoreAtendimentoVO().getFormularioVO() != null && atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray() != null) {
				getFormFormulario().setArrayGrupoCampos(atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray());
				for (int i = 0; i < atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray().length; i++) {
					qtdeCamposGrupos += atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray(i).getFormularioCampoVOArray().length;
				}
			}

			// verifica se o tamanho dos campos do formulário é menor que um valor default. Se for, seta o valor default
			if (atendimentoVO.getArvoreAtendimentoVO().getFormularioVO() != null) {
				for (int i = 0; i < atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getFormularioCampoVOArray().length; i++) {
					if (atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getFormularioCampoVOArray(i).getTipoCampoVO().getNrTamanho() != null) {
						if (atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getFormularioCampoVOArray(i).getTipoCampoVO().getNrTamanho().equals("0")) {
							atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getFormularioCampoVOArray(i).getTipoCampoVO().setNrTamanho("10");
						}
					}
				}
				if (atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray() != null) {
					for (int i = 0; i < atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray().length; i++) {
						for (int j = 0; j < atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray(i).getFormularioCampoVOArray().length; j++) {
							if (atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray(i).getFormularioCampoVOArray(j).getTipoCampoVO().getNrTamanho() != null) {
								if (atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray(i).getFormularioCampoVOArray(j).getTipoCampoVO().getNrTamanho().equals("0")) {
									atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getAdmGrupoCamposVOArray(i).getFormularioCampoVOArray(j).getTipoCampoVO().setNrTamanho("10");
								}
							}
						}
					}
				}
			}

			getFormFormulario().setAtendimentoVO(atendimentoVO);
			getFormFormulario().setNumeroCampos(atendimentoVO.getArvoreAtendimentoVO().getFormularioVO().getFormularioCampoVOArray().length + qtdeCamposGrupos);

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("AbaCamposFormController:visualizarFormulario" + "( " + user + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	private String getIdTipoLinha() {
		/*
		 * if (getFormFormulario().getTipoLinhasExistentes() != null) { for (int i = 0; i <
		 * getFormFormulario().getTipoLinhasExistentes().length; i++) { if
		 * (!"".equals(getFormFormulario().getTipoLinhasExistentes()[i].getIdTipoLinha())) { return
		 * getFormFormulario().getTipoLinhasExistentes()[i].getIdTipoLinha(); } } }
		 */
		if (getFormFormulario().getTipoLinhasAssociadas() != null) {
			for (int i = 0; i < getFormFormulario().getTipoLinhasAssociadas().length; i++) {
				if (!ConstantesCRM.SVAZIO.equals(getFormFormulario().getTipoLinhasAssociadas()[i].getIdTipoLinha())) {
					return getFormFormulario().getTipoLinhasAssociadas()[i].getIdTipoLinha();
				}
			}
		}
		return ConstantesCRM.SVAZIO;
	}

	private String getIdUFOperadora() {
		/*
		 * if (getFormFormulario().getUfOperadorasExistentes() != null) { for (int i = 0; i <
		 * getFormFormulario().getUfOperadorasExistentes().length; i++) { if
		 * (!"".equals(getFormFormulario().getUfOperadorasExistentes()[i].getIdUFOperadora())) { return
		 * getFormFormulario().getUfOperadorasExistentes()[i].getIdUFOperadora(); } } }
		 */
		if (getFormFormulario().getUfOperadorasAssociadas() != null) {
			for (int i = 0; i < getFormFormulario().getUfOperadorasAssociadas().length; i++) {
				if (!ConstantesCRM.SVAZIO.equals(getFormFormulario().getUfOperadorasAssociadas()[i].getIdUFOperadora())) {
					return getFormFormulario().getUfOperadorasAssociadas()[i].getIdUFOperadora();
				}
			}
		}
		return ConstantesCRM.SVAZIO;
	}

	/**
	 * Método responsável pelo carregamento da aba Agrupar Campos.
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="agruparCampos.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward agruparCampos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		StringBuffer xmlDados = new StringBuffer();
		String forward = ConstantesCRM.SUCCESS;
		log.debug("AbaCamposFormController:agruparCampos" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormFormulario form = (FormFormulario) formParam;
		try {

			AdmCamposFormularioVO admCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();
			admCamposFormularioVO.setInOperacao(form.getInOperacao());
			admCamposFormularioVO.setIdContato(form.getIdContato());
			formFormulario.setIdContato(form.getIdContato());

			if ("NOVO".equals(request.getParameter(ConstantesCRM.SACTION))) {
				admCamposFormularioVO.setNmGrupoCampos(form.getNmGrupoCampos());
				formFormulario.setIdGrupoCampos(ConstantesCRM.SVAZIO);
			} else {
				admCamposFormularioVO.setIdGrupoCampos(form.getIdGrupoCampos());
				formFormulario.setIdGrupoCampos(form.getIdGrupoCampos());
			}

			// Seleção da select list "Classificador"
			if ("AGRUCAMGETCAMPOSDISP".equals(form.getInOperacao())) {

				admCamposFormularioVO.setIdClassificadorCampoAtual(form.getIdClassificadorCampoAtual());
				admCamposFormularioVO.setIdGrupoCampos(form.getIdGrupoCampos());
				admCamposFormularioVO = camposFormularioTux.carregaCamposFormulario(admCamposFormularioVO, user);

				getFormFormulario().setCamposExistentes(admCamposFormularioVO.getDisponiveis().getAdmCampoVOArray());
				getFormFormulario().setIdClassificadorCampoAtual(form.getIdClassificadorCampoAtual());
				getFormFormulario().setIdGrupoCampos(form.getIdGrupoCampos());

			}

			// Seleção da select list "Grupo de Campos"
			else if ("AGRUCAMGETCAMPOSSEL".equals(form.getInOperacao())) {

				admCamposFormularioVO.setIdGrupoCampos(form.getIdGrupoCampos());
				admCamposFormularioVO.setIdClassificadorCampoAtual(form.getIdClassificadorCampoAtual());
				admCamposFormularioVO.setInOperacao(form.getInOperacao());
				admCamposFormularioVO = camposFormularioTux.carregaCamposFormulario(admCamposFormularioVO, user);
				AdmCampoVO[] arrayCampoVO = new AdmCampoVO[0];
				getFormFormulario().setCamposExistentes(arrayCampoVO);
				getFormFormulario().setCamposAssociados(admCamposFormularioVO.getSelecionados().getAdmCampoVOArray());
				getFormFormulario().setIdClassificadorCampoAtual(form.getIdClassificadorCampoAtual());

			}

			// Botão Salvar
			else if ("SETAGRUPARCAMPOS".equals(form.getInOperacao())) {
				boolean inEncontrado = false;
				admCamposFormularioVO.addNewSelecionados().addNewAdmCampoVO();
				ArrayList inclusao = new ArrayList();
				ArrayList exclusao = new ArrayList();
				for (int i = 0; i < form.getaCamposSelecionados().length; i++) {
					inEncontrado = false;
					for (int j = 0; j < getFormFormulario().getCamposAssociados().length; j++) {
						if (form.getaCamposSelecionados()[i].equals(getFormFormulario().getCamposAssociados()[j].getIdCampo())) {
							inEncontrado = true;
							break;
						}
					}
					if (!inEncontrado) {
						inclusao.add(form.getaCamposSelecionados()[i]);
					}
				}

				inEncontrado = false;
				for (int i = 0; i < getFormFormulario().getCamposAssociados().length; i++) {
					inEncontrado = false;
					for (int j = 0; j < form.getaCamposSelecionados().length; j++) {
						if (form.getaCamposSelecionados()[j].equals(getFormFormulario().getCamposAssociados()[i].getIdCampo())) {
							inEncontrado = true;
							break;
						}
					}
					if (!inEncontrado) {
						exclusao.add(getFormFormulario().getCamposAssociados()[i].getIdCampo());
					}
				}

				String stringInclusao = ConstantesCRM.SVAZIO;
				for (int i = 0; i < inclusao.size(); i++) {
					String temp = (String) inclusao.get(i);
					stringInclusao += temp;
					if (i != inclusao.size() - 1) {
						stringInclusao += "|";
					}
				}
				String stringExclusao = ConstantesCRM.SVAZIO;
				for (int i = 0; i < exclusao.size(); i++) {
					String temp = (String) exclusao.get(i);
					stringExclusao += temp;
					if (i != exclusao.size() - 1) {
						stringExclusao += "|";
					}
				}
				admCamposFormularioVO.getSelecionados().getAdmCampoVOArray(0).setIdsInclusao(stringInclusao);
				admCamposFormularioVO.getSelecionados().getAdmCampoVOArray(0).setIdsExclusao(stringExclusao);

				getFormFormulario().setCamposAssociados(admCamposFormularioVO.getSelecionados().getAdmCampoVOArray());
				admCamposFormularioVO = camposFormularioTux.carregaCamposFormulario(admCamposFormularioVO, user);
				request.setAttribute("msg", "Dados gravados com sucesso!");
			}

			// Carregamento inicial
			else {

				admCamposFormularioVO.setInOperacao(form.getInOperacao());
				formFormulario = new FormFormulario();
				formFormulario.setIdContato(form.getIdContato());
				admCamposFormularioVO = camposFormularioTux.carregaCamposFormulario(admCamposFormularioVO, user);

				formFormulario.setArrayGrupoCampos(admCamposFormularioVO.getAdmGruposCamposVO().getAdmGrupoCamposVOArray());
				formFormulario.setArrayGrupoCamposLength(admCamposFormularioVO.getAdmGruposCamposVO().getAdmGrupoCamposVOArray().length);
				formFormulario.setArrayCampoClassificador(admCamposFormularioVO.getAdmClassificadorCamposVO().getAdmClassificadorCampoVOArray());

			}

			// Chamada via Ajax (para popular combo de campos para seleção)
			if ("AGRUCAMGETCAMPOSDISP".equals(form.getInOperacao())) {

				xmlDados.append(admCamposFormularioVO.xmlText().replaceAll("vo:", ""));
				response.setContentType(ConstantesCRM.SContentType);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
				log.debug("AbaCamposFormController:agruparCampos(" + user + ") >> FINALIZADO");
				return null;
			}

		} catch (Exception e) {

			forward = ConstantesCRM.SERROR;
			log.error("AbaCamposFormController:agruparCampos" + "( " + user + " )", e);
			if ("AGRUCAMGETCAMPOSDISP".equals(form.getInOperacao())) {

				AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
				ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
				ajaxErrorHandlerVO.setFriendlyMessage(MENSAGEM_GERAL);

				xmlDados = new StringBuffer();
				xmlDados.append(ajaxErrorHandlerVO.xmlText());

				return null;

			} else {
				FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
				formError.setTarget(ConstantesCRM.FRAMEAPP);
				forward = ConstantesCRM.SERROR;
			}

		}
		if ("AGRUCAMGETCAMPOSDISP".equals(form.getInOperacao())) {
			return null;
		} else {
			request.setAttribute("formFormulario", formFormulario);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
	}


    /**
     * Método responsável pelo carregamento da aba Campos Dependentes.
     * @jpf:action
     * @jpf:forward name="success"          path="camposDependentes.jsp"
     * @jpf:forward name="begin"            path="camposDependentes.do"
     * @jpf:forward name="abaCamposValores" path="camposDependentesCamposValores.jsp"
     * @jpf:forward name="abaRelacionar"    path="camposDependentesRelacionar.jsp"
     * @jpf:forward name="error"            path="/error.jsp"
     */
    protected ActionForward camposDependentes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        String destino = ConstantesCRM.SUCCESS;
        FormFormulario form = (FormFormulario) formParam;

        try {        	
            AdmCamposFormularioVO admCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();            
            user = ConstantesCRM.getCurrentUser(request.getSession());

            //Acesso à aba Campos/Valores
            if ("GETCAMPOSVALORES".equals(form.getInOperacao())) {

                destino = "abaCamposValores";
                formFormulario.setNmGrupoCampos(form.getNmGrupoCampos());
                formFormulario.setIdGrupoCampos(form.getIdGrupoCampos());
                formFormulario.setCamposDependentesExcluidos(null);

                admCamposFormularioVO.setInOperacao(form.getInOperacao());
                admCamposFormularioVO.setIdGrupoCampos(form.getIdGrupoCampos());

                admCamposFormularioVO = camposFormularioTux.getCamposDependentes(admCamposFormularioVO, user);
                formFormulario.setInContatoAssociado(admCamposFormularioVO.getAdmGruposCamposDependentesVO().getInContatoAssociado());

                formFormulario.setArrayGrupoCamposDependentes(admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray());

                if (admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray().length == 0) {
                    AdmGrupoCamposDependentesVO[] arrayCampDep = new AdmGrupoCamposDependentesVO[1];
                    arrayCampDep[0] = AdmGrupoCamposDependentesVO.Factory.newInstance();
                    arrayCampDep[0].addNewAdmCampoVO();
                    formFormulario.setArrayGrupoCamposDependentes(arrayCampDep);
                }
                formFormulario.setQtdeNiveis(Integer.toString(admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray().length));                                
                request.setAttribute("formFormulario", formFormulario);
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(destino);
            }

            // Exclusão de campo dependente - Aba Campos/Valores
            else if ("EXCLUIRCAMPOSVALORES".equals(form.getInOperacao())) {

                AdmGruposCamposDependentesVO grupos = AdmGruposCamposDependentesVO.Factory.newInstance();

                String nmCampos         = ConstantesCRM.SVAZIO;
                String nrNiveis         = ConstantesCRM.SVAZIO;
                String idCampos         = ConstantesCRM.SVAZIO;
                String selectNrNivel    = ConstantesCRM.SVAZIO;
                String camposAssociados = ConstantesCRM.SVAZIO;
                String[] arrayValores   = new String[0];
                int contador            = 0;
                boolean inEncontrado    = false;

                formFormulario.setIdContato(form.getIdContato());
                formFormulario.setIdGrupoCampos(form.getIdGrupoCampos());
                formFormulario.setNmGrupoCampos(form.getNmGrupoCampos());

                formFormulario.setQtdeNiveis(Integer.toString(form.getArrayGrupoCamposLength()-1));

                for(int i=0; i < form.getArrayGrupoCamposLength(); i++){

                    nmCampos         = "nmCampo[" + String.valueOf(i) + "]";
                    nrNiveis         = "selectNrNivel[" + String.valueOf(i) + "]";
                    idCampos         = "idCampo[" + String.valueOf(i) + "]";
                    selectNrNivel    = "selectNrNivel[" + String.valueOf(i) + "]";
                    camposAssociados = "selectCamposAssociados[" + String.valueOf(i) + "]";

                    arrayValores = request.getParameterValues(camposAssociados);

                    if(ConstantesCRM.SVAZIO.equals(formFormulario.getIdxNivelExcluido()) ||
                       Integer.parseInt(form.getIdxNivelExcluido()) < Integer.parseInt(formFormulario.getIdxNivelExcluido())){
                        formFormulario.setIdxNivelExcluido(form.getIdxNivelExcluido());
                    }

                    //Apenas para os campos que não foram excluidos
                    if(i != Integer.parseInt(form.getIdxNivelExcluido())){
                        grupos.addNewAdmGrupoCamposDependentesVO();
                        grupos.getAdmGrupoCamposDependentesVOArray(contador).setNmCampoDependente(request.getParameter(nmCampos));
                        grupos.getAdmGrupoCamposDependentesVOArray(contador).setNrNivel(request.getParameter(nrNiveis));
                        grupos.getAdmGrupoCamposDependentesVOArray(contador).setIdCampoDependente(request.getParameter(idCampos));
                        if(arrayValores != null){
                            for (int j=0; j < arrayValores.length; j++){
                                inEncontrado = false;
                                if (i < formFormulario.getArrayGrupoCamposDependentes().length) {
                                    for (int m = 0; m < formFormulario.getArrayGrupoCamposDependentes()[i].getAdmCampoVOArray().length; m++){
                                        inEncontrado = false;
                                        if (arrayValores[j].equals(formFormulario.getArrayGrupoCamposDependentes()[i].getAdmCampoVOArray(m).getNmCampo())) {
                                            inEncontrado = true;
                                            grupos.getAdmGrupoCamposDependentesVOArray(contador).addNewAdmCampoVO().set(
                                                formFormulario.getArrayGrupoCamposDependentes()[i].getAdmCampoVOArray(m).copy());
                                            break;
                                        }
                                    }
                                }
                                if (!inEncontrado || (!request.getParameter(nmCampos).equals(formFormulario.getArrayGrupoCamposDependentes()[i].getNmCampoDependente())))
                                    grupos.getAdmGrupoCamposDependentesVOArray(contador).addNewAdmCampoVO().setNmCampo(arrayValores[j]);
                            }
                        }
                        contador++;
                    }else{
                        for(int c=0;c<formFormulario.camposExcluidos.length;c++){
                            if(formFormulario.camposExcluidos[c] == null){
                                formFormulario.camposExcluidos[c] = request.getParameter(idCampos);
                                break;
                            }
                        }
                    }
                }
                formFormulario.setArrayGrupoCamposDependentes(grupos.getAdmGrupoCamposDependentesVOArray());
                destino = "abaCamposValores";                
                request.setAttribute("formFormulario", formFormulario);
                request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(destino);
            }

            // Acesso à aba Relacionar
            else if ("GETTREE".equals(form.getInOperacao())) {

                destino = "abaRelacionar";

                admCamposFormularioVO.setInOperacao(form.getInOperacao());
                admCamposFormularioVO.setIdGrupoCampos(form.getIdGrupoCampos());
                admCamposFormularioVO = camposFormularioTux.getCamposDependentes(admCamposFormularioVO, user);
                formFormulario.setArvoreCamposDependentes(admCamposFormularioVO.getAdmArvoreGruposCamposDependentesVO());
                formFormulario.setaCamposExistentes(new String[0]);

                request.setAttribute("arvore", carregaArvore(admCamposFormularioVO.getAdmArvoreGruposCamposDependentesVO(), request));

                if(ConstantesCRM.SZERO.equals(formFormulario.getQtdeNiveis()))
                    formFormulario.setQtdeNiveis("20");

            }

            //Exclusão de um item da árvore
            else if ("EXCLUIRCAMPO".equals(form.getInOperacao())) {

                destino = "abaRelacionar";

                String idItemSelecionado = request.getParameter("idItemSelecionado");
                idFirstParentExclusao    = request.getParameter("idFirstParent");
                String idItemNivel       = request.getParameter("nrNivelItemSelecionado");
                String idItemPai         = request.getParameter("idItemPai");
                String idItemUnico       = request.getParameter("idItemUnico");
                idUnico                  = 1;

                AdmArvoreGruposCamposDependentesVO arvore = AdmArvoreGruposCamposDependentesVO.Factory.newInstance();
                arvore.setIdGrupo(formFormulario.getIdGrupoCampos());
                arvore.setNmGrupo(formFormulario.getNmGrupoCampos());
                arvore.addNewTreeItemVO().set(buildArvore(formFormulario.getArvoreCamposDependentes().getTreeItemVO(), "exclusao", idItemUnico, null).copy());
                formFormulario.setArvoreCamposDependentes(arvore);

                request.setAttribute("arvore", carregaArvore(arvore, request));

                formFormulario.setaCamposExistentes(new String[0]);

            }

            //Associação de um item à árvore
            else if ("ASSOCIARCAMPO".equals(form.getInOperacao())) {

                destino = "abaRelacionar";

                String idUnicoPai = request.getParameter("idItemUnico");
                idUnico           = 1;

                request.setAttribute("parametrosIdUnico",Integer.toString(idUnico));

                AdmArvoreGruposCamposDependentesVO arvore = AdmArvoreGruposCamposDependentesVO.Factory.newInstance();
                if (form.getaCamposExistentes().length == 0) {
                    arvore = formFormulario.getArvoreCamposDependentes();
                } else {
                    arvore.setIdGrupo(formFormulario.getArvoreCamposDependentes().getIdGrupo());
                    arvore.setNmGrupo(formFormulario.getArvoreCamposDependentes().getNmGrupo());
                    arvore.addNewTreeItemVO().set(insertItem(formFormulario.getArvoreCamposDependentes().getTreeItemVO(), form.getaCamposExistentes(), idUnicoPai).copy());
                }
                formFormulario.setArvoreCamposDependentes(arvore);
                request.setAttribute("arvore", carregaArvore(arvore, request));
                formFormulario.setaCamposExistentes(new String[0]);
            }

            //Gravação de dados (Aba Campos/Valores)
            else if ("GRAVARCAMPOSVALORES".equals(form.getInOperacao())) {

                String nmCampos         = ConstantesCRM.SVAZIO;
                String nrNiveis         = ConstantesCRM.SVAZIO;
                String idCampos         = ConstantesCRM.SVAZIO;
                String selectNrNivel    = ConstantesCRM.SVAZIO;
                String camposAssociados = ConstantesCRM.SVAZIO;
                String[] arrayValores   = new String[0];

                admCamposFormularioVO.setIdContato(form.getIdContato());
                admCamposFormularioVO.setIdGrupoCampos(form.getIdGrupoCampos());
                admCamposFormularioVO.setNmGrupoCampos(form.getNmGrupoCampos());
                admCamposFormularioVO.setInOperacao("SETCAMPOSVALORES");
                admCamposFormularioVO.addNewAdmGruposCamposDependentesVO();

                formFormulario.setQtdeNiveis(Integer.toString(form.getArrayGrupoCamposLength()));

                boolean inEncontrado = false;
                ArrayList inclusao = new ArrayList();
                ArrayList exclusao = new ArrayList();

                //Verifica se houve exclusão de campos dependentes
                String camposExcluidos = ConstantesCRM.SVAZIO;
                for(int i=0;i<formFormulario.getCamposExcluidos().length;i++){
                    if(formFormulario.getCamposExcluidos()[i] != null){
                        camposExcluidos += formFormulario.getCamposExcluidos()[i] + "|";
                    }
                }

                if(camposExcluidos.length()>0)
                    admCamposFormularioVO.getAdmGruposCamposDependentesVO().setIdsExclusao(camposExcluidos.substring(0,camposExcluidos.length()-1));

                for(int i=0; i < form.getArrayGrupoCamposLength(); i++){

                    nmCampos         = "nmCampo[" + String.valueOf(i) + "]";
                    nrNiveis         = "selectNrNivel[" + String.valueOf(i) + "]";
                    idCampos         = "idCampo[" + String.valueOf(i) + "]";
                    selectNrNivel    = "selectNrNivel[" + String.valueOf(i) + "]";
                    camposAssociados = "selectCamposAssociados[" + String.valueOf(i) + "]";

                    inclusao = new ArrayList();
                    exclusao = new ArrayList();
                    String stringInclusao = ConstantesCRM.SVAZIO;
                    String stringExclusao = ConstantesCRM.SVAZIO;

                    admCamposFormularioVO.getAdmGruposCamposDependentesVO().addNewAdmGrupoCamposDependentesVO();
                    admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray(i).setNmCampoDependente(request.getParameter(nmCampos));
                    admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray(i).setNrNivel(request.getParameter(nrNiveis));
                    admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray(i).setIdCampoDependente(request.getParameter(idCampos));

                    arrayValores = request.getParameterValues(camposAssociados);

                    if(!ConstantesCRM.SVAZIO.equals(form.getIdGrupoCampos())){
                        //Verifica se o valor foi excluído
                        if (i < formFormulario.getArrayGrupoCamposDependentes().length) {
                            for (int k=0; k < formFormulario.getArrayGrupoCamposDependentes()[i].getAdmCampoVOArray().length; k++){
                                if (arrayValores != null){
                                    if (Arrays.binarySearch(arrayValores, formFormulario.getArrayGrupoCamposDependentes()[i].getAdmCampoVOArray(k).getNmCampo()) < 0)
                                        exclusao.add(formFormulario.getArrayGrupoCamposDependentes()[i].getAdmCampoVOArray(k).getNmCampo());
                                }else
                                    exclusao.add(formFormulario.getArrayGrupoCamposDependentes()[i].getAdmCampoVOArray(k).getNmCampo());
                            }
                        }
                    }

                    if (exclusao.size() > 0)
                        for(int w=0;w<exclusao.size();w++){
                            String temp = (String)exclusao.get(w);
                            stringExclusao += temp;
                            if(w != exclusao.size()-1)
                                stringExclusao += "|";
                        }

                    if (arrayValores != null) {
                        for (int j=0; j < arrayValores.length; j++){
                            inEncontrado = false;
                            if (i < formFormulario.getArrayGrupoCamposDependentes().length) {
                                for (int m = 0; m < formFormulario.getArrayGrupoCamposDependentes()[i].getAdmCampoVOArray().length; m++)
                                    if (form.getInMsgErro().equals(ConstantesCRM.SVAZIO) && arrayValores[j].equals(formFormulario.getArrayGrupoCamposDependentes()[i].getAdmCampoVOArray(m).getNmCampo())) {
                                        inEncontrado = true;
                                        break;
                                    }
                            }
                            if (!inEncontrado || (!request.getParameter(nmCampos).equals(formFormulario.getArrayGrupoCamposDependentes()[i].getNmCampoDependente())))
                                inclusao.add(arrayValores[j]);
                        }

                        if (inclusao.size() > 0)
                            for(int w=0;w<inclusao.size();w++){
                                String temp = (String)inclusao.get(w);
                                stringInclusao += temp;
                                if(w != inclusao.size()-1)
                                    stringInclusao += "|";
                            }
                    }

                    if (exclusao.size() > 0 || inclusao.size() > 0) {
                        admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray(i).addNewAdmCampoVO();
                        if (inclusao.size() > 0)
                            admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray(i).getAdmCampoVOArray(0).setIdsInclusao(stringInclusao);
                        if (exclusao.size() > 0)
                            admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray(i).getAdmCampoVOArray(0).setIdsExclusao(stringExclusao);
                    }
                }

                camposFormularioTux.setCamposDependentes(admCamposFormularioVO, user);
                formFormulario.setInOperacao("GETCAMPOSVALORES");
                destino = "begin";
                request.setAttribute("msg", ConstantesCRM.SSucesso);

            }

            //Gravação de dados (Aba Relacionar)
            else if ("GRAVARARVORE".equals(form.getInOperacao())) {

                destino = "abaRelacionar";

                admCamposFormularioVO.setInOperacao("SETTREE");
                admCamposFormularioVO.setIdContato(form.getIdContato());
                admCamposFormularioVO.addNewAdmArvoreGruposCamposDependentesVO().set(formFormulario.getArvoreCamposDependentes().copy());

                camposFormularioTux.setCamposDependentes(admCamposFormularioVO, user);

                request.setAttribute("arvore", carregaArvore(admCamposFormularioVO.getAdmArvoreGruposCamposDependentesVO(), request));
                request.setAttribute("msg", ConstantesCRM.SSucesso);
            }

            //Carregamento inicial
            else {

                formFormulario = new FormFormulario();

                admCamposFormularioVO.setInOperacao("GETCAMPOSDEPENDENTES");
                admCamposFormularioVO = camposFormularioTux.getCamposDependentes(admCamposFormularioVO, user);

                admCamposFormularioVO.setAdmGruposCamposVO(admCamposFormularioVO.getAdmGruposCamposVO());
                formFormulario.setIdContato(form.getIdContato());
                formFormulario.setInOperacao(admCamposFormularioVO.getInOperacao());

                formFormulario.setArrayGrupoCampos(admCamposFormularioVO.getAdmGruposCamposVO().getAdmGrupoCamposVOArray());
                formFormulario.setArrayGrupoCamposLength(admCamposFormularioVO.getAdmGruposCamposVO().getAdmGrupoCamposVOArray().length);

            }

        } catch(Exception e) {

            // REGEX.
            if(e.getMessage().matches("^.*14E0001.*$")
               || e.getMessage().indexOf("14E0099") > 0){
                log.warn("AdmArvoreContatoController:camposDependentes"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " +e.getMessage().substring(e.getMessage().indexOf(']')+1));
                String msgErr = e.getMessage().substring(e.getMessage().indexOf(']')+1);
                request.setAttribute("msgError",msgErr);
                if(msgErr.indexOf("associado")>0
                   || msgErr.indexOf("cadastrado")>0
                   || msgErr.indexOf("hierarquia")>0){
                    AdmGruposCamposDependentesVO grupos = AdmGruposCamposDependentesVO.Factory.newInstance();
                    String nmCampos         = ConstantesCRM.SVAZIO;
                    String nrNiveis         = ConstantesCRM.SVAZIO;
                    String idCampos         = ConstantesCRM.SVAZIO;
                    String selectNrNivel    = ConstantesCRM.SVAZIO;
                    String camposAssociados = ConstantesCRM.SVAZIO;
                    String inSubFormularios = ConstantesCRM.SVAZIO;
                    String[] arrayValores   = new String[0];
                    int contador            = 0;
                    boolean inEncontrado    = false;
                    formFormulario.setIdContato(form.getIdContato());
                    formFormulario.setIdGrupoCampos(form.getIdGrupoCampos());
                    formFormulario.setNmGrupoCampos(form.getNmGrupoCampos());
                    for(int i=0; i < form.getArrayGrupoCamposLength(); i++){
                        nmCampos         = "nmCampo[" + String.valueOf(i) + "]";
                        nrNiveis         = "selectNrNivel[" + String.valueOf(i) + "]";
                        idCampos         = "idCampo[" + String.valueOf(i) + "]";
                        selectNrNivel    = "selectNrNivel[" + String.valueOf(i) + "]";
                        camposAssociados = "selectCamposAssociados[" + String.valueOf(i) + "]";
                        inSubFormularios = "inSubFormularios[" + String.valueOf(i) + "]";
                        arrayValores = request.getParameterValues(camposAssociados);
                        grupos.addNewAdmGrupoCamposDependentesVO();
                        grupos.getAdmGrupoCamposDependentesVOArray(contador).setNmCampoDependente(request.getParameter(nmCampos));
                        grupos.getAdmGrupoCamposDependentesVOArray(contador).setNrNivel(request.getParameter(nrNiveis));
                        grupos.getAdmGrupoCamposDependentesVOArray(contador).setIdCampoDependente(request.getParameter(idCampos));
                        grupos.getAdmGrupoCamposDependentesVOArray(contador).setInSubFormularios(request.getParameter(inSubFormularios));
                        if(arrayValores != null){
                            for (int j=0; j < arrayValores.length; j++){
                                inEncontrado = false;
                                if(formFormulario.getArrayGrupoCamposDependentes().length > i){
                                    for (int m = 0; m < formFormulario.getArrayGrupoCamposDependentes()[i].getAdmCampoVOArray().length; m++){
                                        inEncontrado = false;
                                        if(msgErr.indexOf("hierarquia")>0){
                                            grupos.getAdmGrupoCamposDependentesVOArray(contador).setAdmCampoVOArray(
                                                formFormulario.getArrayGrupoCamposDependentes()[i].getAdmCampoVOArray()
                                            );
                                            break;
                                        }else{
                                            if (arrayValores[j].equals(formFormulario.getArrayGrupoCamposDependentes()[i].getAdmCampoVOArray(m).getNmCampo())) {
                                                inEncontrado = true;
                                                grupos.getAdmGrupoCamposDependentesVOArray(contador).addNewAdmCampoVO().set(
                                                    formFormulario.getArrayGrupoCamposDependentes()[i].getAdmCampoVOArray(m).copy());
                                                break;
                                            }
                                        }
                                    }
                                }
                                if (msgErr.indexOf("hierarquia")==0 && (!inEncontrado || (!request.getParameter(nmCampos).equals(formFormulario.getArrayGrupoCamposDependentes()[i].getNmCampoDependente()))))
                                    grupos.getAdmGrupoCamposDependentesVOArray(contador).addNewAdmCampoVO().setNmCampo(arrayValores[j]);
                            }
                        }
                        contador++;
                    }
                    formFormulario.setArrayGrupoCamposDependentes(grupos.getAdmGrupoCamposDependentesVOArray());                    
                    request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("abaCamposValores");
                }else{                    
                    request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
                }
            }

            log.error("AbaCamposFormController:camposDependentes"+"( " + user + " )", e);
            FormError formError = globalApp.buildFormError(e, request);
            formError.setTarget(ConstantesCRM.FRAMEAPP);            
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			return mapping.findForward(ConstantesCRM.SERROR);
        }
        
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        request.setAttribute("formFormulario", formFormulario);
        
        if ("begin".equals(destino)) {
        	return this.camposDependentes(mapping, formFormulario, request, response);
        }
        
		return mapping.findForward(destino);
    }	

	/**
	 * Método responsável pela verificação da existência de campo dependente homônimo. Caso exista, lista de valores
	 * deste campo é trazida.
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="camposDependentes.jsp"
	 */
	public ActionForward getLupaCamposDependentes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AbaCamposFormController:getLupaCamposDependentes(" + user + ") >> INICIALIZADO");
		StringBuffer xmlDados = new StringBuffer();
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		FormFormulario form = (FormFormulario) formParam;
		try {

			HashMap hm = new HashMap();
			hm.put("inOperacao", "LUPAGETCAMPODPD");
			hm.put("nmCampo", request.getParameter("nmCampo"));
			hm.put("idGrupoCampos", formFormulario.getIdGrupoCampos());

			AdmGrupoCamposDependentesVO valoresGrupo = camposFormularioTux.getLupaCamposDependentes(hm, user);
			xmlDados.append(valoresGrupo.xmlText().replaceAll("vo:", ""));

		} catch (Exception e) {

			log.error("AbaCamposFormController:getLupaCamposDependentes(" + user + ") - [" + e.getMessage() + "]", e);
			AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(MENSAGEM_GERAL);
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());
		} finally {
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
			log.debug("AbaCamposFormController:getLupaCamposDependentes(" + user + ") >> FINALIZADO");
		}
		return null;
	}

	private String trataStringRetorno(String strParam) {
		if (strParam == null) {
			return null;
		}
		strParam = strParam.replaceAll("'", "&#39;");
		strParam = strParam.replaceAll("\"", "&#34;");
		strParam = strParam.replaceAll("\\\\", "\\\\\\\\");
		strParam = strParam.replaceAll("Ç", "&#199;");
		strParam = strParam.replaceAll("ç", "&#231;");
		return strParam;
	}

	public String getIdFirstParent(TreeItemVO item) throws Exception {
		if (item.getId().equals(item.getIdPai())) {
			idFirstParent = item.getId();
			if (ConstantesCRM.SONE.equals(item.getNivel())) {
				return ConstantesCRM.SVAZIO;
			}
		}
		return idFirstParent;
	}

	private TreeItemVO insertItem(TreeItemVO item, String[] campos, String idUnicoPai) {

		TreeItemVO itemInsercao = TreeItemVO.Factory.newInstance();
		TreeItemVO itemCopia = TreeItemVO.Factory.newInstance();
		String idCampo = ConstantesCRM.SVAZIO;
		String id = ConstantesCRM.SVAZIO;
		String idPai = ConstantesCRM.SVAZIO;
		String dsPai = ConstantesCRM.SVAZIO;
		String dsCampo = ConstantesCRM.SVAZIO;
		String[] dadosCampo = new String[0];
		String nivelInsercao = ConstantesCRM.SVAZIO;

		itemCopia.setDescricao(item.getDescricao());
		itemCopia.setDescricaoPai(item.getDescricaoPai());
		itemCopia.setIdCampo(item.getIdCampo());
		itemCopia.setId(item.getId());
		itemCopia.setIdPai(item.getIdPai());
		itemCopia.setNivel(item.getNivel());
		itemCopia.setIdUnico(Integer.toString(idUnico));
		idUnico++;

		if (item.getTreeItemVOArray().length > 0) {
			for (int i = 0; i < item.getTreeItemVOArray().length; i++) {
				if (idUnicoPai.equals(String.valueOf(item.getTreeItemVOArray(i).getIdUnico()))) {
					nivelInsercao = Integer.toString((Integer.parseInt(item.getTreeItemVOArray(i).getNivel()) + 1));
					for (int j = 0; j < campos.length; j++) {
						dadosCampo = campos[j].split("\\|");
						idCampo = dadosCampo[0];
						dsCampo = dadosCampo[1];
						id = dadosCampo[2];
						idPai = dadosCampo[3];

						if (!verificaDuplicidade(item.getTreeItemVOArray(i), id)) {
							itemInsercao = TreeItemVO.Factory.newInstance();
							itemInsercao.setIdUnico(Integer.toString(idUnico));
							itemInsercao.setId(id);
							itemInsercao.setIdCampo(idCampo);
							itemInsercao.setDescricao(dsCampo);
							itemInsercao.setDescricaoPai(formFormulario.getDescricaoPai());
							itemInsercao.setIdPai(formFormulario.getIdPai());
							itemInsercao.setNivel(nivelInsercao);
							item.getTreeItemVOArray(i).addNewTreeItemVO().set(itemInsercao.copy());
							idUnico++;
						}
					}
					itemCopia.addNewTreeItemVO().set(insertItem(item.getTreeItemVOArray(i), campos, idUnicoPai).copy());

				} else {
					itemCopia.addNewTreeItemVO().set(insertItem(item.getTreeItemVOArray(i), campos, idUnicoPai).copy());
				}
			}
		}
		return itemCopia;
	}

	private TreeItemVO buildArvore(TreeItemVO item, String dsOperacao, String idUnicoItem, TreeItemVO itemInclusao) {

		TreeItemVO itemCopia = TreeItemVO.Factory.newInstance();

		itemCopia.setDescricao(item.getDescricao());
		itemCopia.setDescricaoPai(item.getDescricaoPai());
		itemCopia.setIdPai(item.getIdPai());
		itemCopia.setIdCampo(item.getIdCampo());
		itemCopia.setId(item.getId());
		itemCopia.setNivel(item.getNivel());
		itemCopia.setIdUnico(Integer.toString(idUnico));
		idUnico++;

		if (item.getTreeItemVOArray().length > 0) {
			for (int i = 0; i < item.getTreeItemVOArray().length; i++) {
				if ("exclusao".equals(dsOperacao)) {
					if (!idUnicoItem.equals(String.valueOf(item.getTreeItemVOArray(i).getIdUnico()))) {
						itemCopia.addNewTreeItemVO().set(buildArvore(item.getTreeItemVOArray(i), dsOperacao, idUnicoItem, null).copy());
					}
				} else {
					itemCopia.addNewTreeItemVO().set(buildArvore(item.getTreeItemVOArray(i), dsOperacao, idUnicoItem, null).copy());
				}
			}
		}
		return itemCopia;
	}

	private boolean verificaDuplicidade(TreeItemVO item, String id) {
		boolean retorno = false;
		if (item.getTreeItemVOArray() != null) {
			for (int i = 0; i < item.getTreeItemVOArray().length; i++) {
				if (id.equals(item.getTreeItemVOArray(i).getId())) {
					retorno = true;
					break;
				}
			}
		}
		return retorno;
	}

	public String writeArvore(TreeItemVO itens, String tree) throws Exception {

		String node = ConstantesCRM.SVAZIO;

		TreeItemVO itemNovo = TreeItemVO.Factory.newInstance();

		if (parent == null) {
			parent = itens;
		}

		if (itens.getTreeItemVOArray().length > 0) {

			for (int x = 0; x < itens.getTreeItemVOArray().length; x++) {

				itens.getTreeItemVOArray(x).setIdUnico(Integer.toString(idUnico));
				idUnico++;

				node = node + "\rvar " + tree + String.valueOf(x) + " = new WebFXTreeItem('" + trataStringRetorno(itens.getTreeItemVOArray(x).getDescricao()) + "',\"javascript:getCamposDependentes('" + getIdFirstParent(itens.getTreeItemVOArray(x)) + "','" + itens.getTreeItemVOArray(x).getNivel() + "','" + itens.getTreeItemVOArray(x).getId() + "','" + itens.getTreeItemVOArray(x).getIdUnico() + "','" + itens.getTreeItemVOArray(x).getIdPai() + "','" + itens.getTreeItemVOArray(x).getDescricao()
				+ "');\",'','/FrontOfficeWeb/resources/images/icon_folha" + itens.getTreeItemVOArray(x).getNivel() + ".gif','/FrontOfficeWeb/resources/images/icon_folha" + itens.getTreeItemVOArray(x).getNivel() + ".gif');";

				// if(Integer.parseInt(itens.getTreeItemVOArray(x).getNivel())>qtdeNiveis)qtdeNiveis=Integer.parseInt(itens.getTreeItemVOArray(x).getNivel());
				if (formFormulario.idCampoNiveis[Integer.parseInt(itens.getTreeItemVOArray(x).getNivel()) - 1] == null) {
					formFormulario.idCampoNiveis[Integer.parseInt(itens.getTreeItemVOArray(x).getNivel()) - 1] = itens.getTreeItemVOArray(x).getIdCampo();
				}
				node = node + writeArvore(itens.getTreeItemVOArray(x), tree + String.valueOf(x));
				node = node + "\r" + tree + ".add(" + tree + String.valueOf(x) + ");\r";
			}
		}
		return node;
	}

	/*
	 * Método responsável pela construção do objeto javascript da árvore de relacionamento entre campos dependentes.
	 */
	public String carregaArvore(AdmArvoreGruposCamposDependentesVO obj, HttpServletRequest request) throws Exception {

		request.setCharacterEncoding(ConstantesCRM.SISO);

		StringBuffer script = new StringBuffer();
		StringBuffer node = new StringBuffer();
		String varItem = ConstantesCRM.SVAZIO;
		String varItemPai = "tree";

		script.append("\nif (document.getElementById) {\n\n\tvar " + varItemPai + " = new WebFXTree('").append(StringUtilStatic.escapaComillasJS(obj.getNmGrupo())).append("',\"javascript:void(0);").append("\",'/FrontOfficeWeb/resources/images/folder_dependencia.gif','/FrontOfficeWeb/resources/images/folder_dependencia.gif');\n\n");

		script.append(writeArvore(obj.getTreeItemVO(), "tree"));

		script.append("\ndocument.write(tree);\n");
		script.append("tree.expandAll();\n}\n\n");

		return String.valueOf(script);
	}

	/**
	 * Método responsável pela obtenção de lista de campos existentes em um nível inferior ao item selecionado na árvore
	 * da aba "Relacionar", em Formulário.
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward getCamposDependentes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		StringBuffer xmlDados = new StringBuffer();
		boolean associado = false;
		FormFormulario form = (FormFormulario) formParam;
		try {

			int nrNivel = (Integer.parseInt(request.getParameter("nrNivel")) + 1);
			String idCampo = request.getParameter("idCampo");
			String idUnico = request.getParameter("idUnico");
			String descricaoPai = request.getParameter("descricao");
			formFormulario.setIdPai(idCampo);
			formFormulario.setDescricaoPai(descricaoPai);

			AdmCamposFormularioVO admCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();
			admCamposFormularioVO.setInOperacao("GETCAMPOSNIVEL");
			admCamposFormularioVO.addNewAdmGruposCamposDependentesVO().addNewAdmGrupoCamposDependentesVO().setIdCampoDependente(idCampo);
			admCamposFormularioVO.getAdmGruposCamposDependentesVO().setIdGrupoCamposDependentes(formFormulario.getIdGrupoCampos());
			admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray(0).setNrNivel(Integer.toString(nrNivel));
			admCamposFormularioVO = camposFormularioTux.getCamposDependentes(admCamposFormularioVO, user);
			admCamposFormularioVO.setInOperacao("GETCAMPOSNIVEL");
			admCamposFormularioVO.addNewAdmGruposCamposDependentesVO().addNewAdmGrupoCamposDependentesVO().setIdCampoDependente(idCampo);
			admCamposFormularioVO.getAdmGruposCamposDependentesVO().setIdGrupoCamposDependentes(formFormulario.getIdGrupoCampos());
			admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray(0).setNrNivel(Integer.toString(nrNivel));

			/*
			 * admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray(0).
			 * addNewAdmCampoVO().setIdCampo("1212");
			 * admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray
			 * (0).getAdmCampoVOArray(0).setNmCampo("São Paulo");
			 * admCamposFormularioVO.getAdmGruposCamposDependentesVO()
			 * .getAdmGrupoCamposDependentesVOArray(0).addNewAdmCampoVO().setIdCampo("5467");
			 * admCamposFormularioVO.getAdmGruposCamposDependentesVO
			 * ().getAdmGrupoCamposDependentesVOArray(0).getAdmCampoVOArray(1).setNmCampo("Rio de Janeiro");
			 * admCamposFormularioVO
			 * .getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray(0).addNewAdmCampoVO
			 * ().setIdCampo("34");
			 * admCamposFormularioVO.getAdmGruposCamposDependentesVO().getAdmGrupoCamposDependentesVOArray
			 * (0).getAdmCampoVOArray(2).setNmCampo("Guarulhos");
			 * admCamposFormularioVO.getAdmGruposCamposDependentesVO()
			 * .getAdmGrupoCamposDependentesVOArray(0).setIdCampoDependente("2112");
			 * admCamposFormularioVO.getAdmGruposCamposDependentesVO
			 * ().getAdmGrupoCamposDependentesVOArray(0).setNmCampoDependente("Cidade");
			 */

			xmlDados.append(admCamposFormularioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

		} catch (Exception e) {

			log.error("AbaCamposFormController:getCamposDependentes(" + user + ") - [" + e.getMessage() + "]", e);
			AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(MENSAGEM_GERAL);

			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		} finally {
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
			log.debug("AbaCamposFormController:getCamposDependentes(" + user + ") >> FINALIZADO");
		}
		return null;
	}

	/**
	 * Método responsável pela obtenção de lista de campos existentes para cada classificador ou componente selecionado
	 * na tela "Configurar Formulário", aba Formulário.
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward getListaCamposClassificadoresComponentes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		StringBuffer xmlDados = new StringBuffer();
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AbaCamposFormController:getListaCamposClassificadoresComponentes(" + user + ") >> INICIADO");
		boolean manageTransferencia = false;
		int pageNumber = 1;
		FormFormulario form = (FormFormulario) formParam;
		try {

			AdmCamposFormularioVO admCamposFormularioVOFinal = AdmCamposFormularioVO.Factory.newInstance();
			AdmCamposFormularioVO admCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();
			admCamposFormularioVO.setInOperacao("GETCAMPOS");
			admCamposFormularioVO.addNewPaginacao().setPageNumber(pageNumber);
			admCamposFormularioVO.setInCamposComponentes(form.getInCamposComponentes());
			admCamposFormularioVO.setIdFaseProcessoAtual(formFormulario.getIdFaseProcessoAtual());
			admCamposFormularioVO.setIdContato(formFormulario.getIdContato());
			admCamposFormularioVO.setIdClassificadorCampoAtual(form.getIdClassificadorCampoAtual());

			admCamposFormularioVOFinal = camposFormularioTux.carregaCamposFormulario(admCamposFormularioVO, user);

			/*
			 * if (admCamposFormularioVO.getDisponiveis() != null) {
			 * admCamposFormularioVOFinal.getDisponiveis().setCampoObjetoFormularioVOArray
			 * (admCamposFormularioVO.getDisponiveis().getCampoObjetoFormularioVOArray()); for (int i=0; i <
			 * admCamposFormularioVO.getDisponiveis().getCampoObjetoFormularioVOArray().length; i++) {
			 * admCamposFormularioVOFinal
			 * .getDisponiveis().addNewCampoObjetoFormularioVO().set(admCamposFormularioVO.getDisponiveis
			 * ().getCampoObjetoFormularioVOArray(i).copy()); } } while
			 * (admCamposFormularioVO.getPaginacao().getHasNext() == 1) { pageNumber++; admCamposFormularioVO =
			 * AdmCamposFormularioVO.Factory.newInstance();
			 * admCamposFormularioVO.setInCamposComponentes(formFormulario.getInCamposComponentes());
			 * admCamposFormularioVO.setIdFaseProcessoAtual(formFormulario.getIdFaseProcessoAtual());
			 * admCamposFormularioVO.setIdContato(formFormulario.getIdContato());
			 * admCamposFormularioVO.setIdClassificadorCampoAtual(form.getIdClassificadorCampoAtual());
			 * admCamposFormularioVO.getPaginacao().setPageNumber(pageNumber); admCamposFormularioVO =
			 * camposFormularioTux.carregaCamposFormulario(admCamposFormularioVO, user); for (int i=0; i <
			 * admCamposFormularioVO.getDisponiveis().getCampoObjetoFormularioVOArray().length; i++) {
			 * admCamposFormularioVOFinal.getDisponiveis().addNewCampoObjetoFormularioVO().set(
			 * admCamposFormularioVO.getDisponiveis().getCampoObjetoFormularioVOArray(i).copy()); } }
			 */

			AdmCamposFormularioVO listaDisponiveis = AdmCamposFormularioVO.Factory.newInstance();
			if (admCamposFormularioVOFinal.getDisponiveis().getCampoObjetoFormularioVOArray() != null && admCamposFormularioVOFinal.getDisponiveis().getCampoObjetoFormularioVOArray().length > 0) {

				listaDisponiveis.addNewDisponiveis();
				int contador = 0;
				for (int i = 0; i < admCamposFormularioVOFinal.getDisponiveis().getCampoObjetoFormularioVOArray().length; i++) {
					if (!isAssociado(admCamposFormularioVOFinal.getDisponiveis().getCampoObjetoFormularioVOArray()[i])) {
						listaDisponiveis.getDisponiveis().addNewCampoObjetoFormularioVO();
						listaDisponiveis.getDisponiveis().getCampoObjetoFormularioVOArray(contador).setIdCampoObjeto(admCamposFormularioVOFinal.getDisponiveis().getCampoObjetoFormularioVOArray()[i].getIdCampoObjeto());
						listaDisponiveis.getDisponiveis().getCampoObjetoFormularioVOArray(contador).setNmCampoObjeto(admCamposFormularioVOFinal.getDisponiveis().getCampoObjetoFormularioVOArray()[i].getNmCampoObjeto());
						listaDisponiveis.getDisponiveis().getCampoObjetoFormularioVOArray(contador).setIdClassificadorComponente(admCamposFormularioVOFinal.getDisponiveis().getCampoObjetoFormularioVOArray()[i].getIdClassificadorComponente());
						listaDisponiveis.getDisponiveis().getCampoObjetoFormularioVOArray(contador).setInClassificadorComponente(admCamposFormularioVOFinal.getDisponiveis().getCampoObjetoFormularioVOArray()[i].getInClassificadorComponente());
						contador++;
					} else {
						manageTransferencia = true;
					}
				}
			}

			if (manageTransferencia) {
				getFormFormulario().setListaDisponiveisCamposObjetos(listaDisponiveis.getDisponiveis().getCampoObjetoFormularioVOArray());
				xmlDados.append(listaDisponiveis.getDisponiveis().xmlText().replaceAll("vo:", ""));
			} else {
				getFormFormulario().setListaDisponiveisCamposObjetos(admCamposFormularioVOFinal.getDisponiveis().getCampoObjetoFormularioVOArray());
				xmlDados.append(admCamposFormularioVOFinal.xmlText().replaceAll("vo:", ""));
			}

		} catch (Exception e) {

			log.error("AbaCamposFormController:getListaCamposClassificadoresComponentes(" + user + ") - [" + e.getMessage() + "]", e);

			AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(MENSAGEM_GERAL);

			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());

		} finally {
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
			log.debug("AbaCamposFormController:getListaCamposClassificadoresComponentes(" + user + ") >> FINALIZADO");
		}
		return null;
	}

	public void populaForm(AdmCamposFormularioVO adm) {

		formFormulario.setMsgError(ConstantesCRM.SVAZIO);
		flag = false;

		if (null != adm.getIdContato() && !ConstantesCRM.SVAZIO.equals(adm.getIdContato())) {
			formFormulario.setIdContato(adm.getIdContato());
		}

		formFormulario.setIdClassificadorCampoAtual(adm.getIdClassificadorCampoAtual());
		formFormulario.setIdFaseProcessoAtual(adm.getIdFaseProcessoAtual());

		if (adm.getAdmClassificadorCamposVO() != null) {
			formFormulario.setArrayCampoClassificador(adm.getAdmClassificadorCamposVO().getAdmClassificadorCampoVOArray());
		}

		if (adm.getAdmFaseProcessosVO() != null) {
			formFormulario.setArrayFaseProcesso(adm.getAdmFaseProcessosVO().getAdmFaseProcessoVOArray());
		}

		if (adm.getSelecionados() != null) {
			if (adm.getSelecionados().getUFOperadoraUsuarioVOArray() != null) {
				formFormulario.setUfOperadorasAssociadas(adm.getSelecionados().getUFOperadoraUsuarioVOArray());
			}
			if (adm.getSelecionados().getAdmTipoLinhaVOArray() != null) {
				formFormulario.setTipoLinhasAssociadas(adm.getSelecionados().getAdmTipoLinhaVOArray());
			}
			if (adm.getSelecionados().getAdmCampoVOArray() != null) {
				formFormulario.setCamposAssociados(adm.getSelecionados().getAdmCampoVOArray());
			}
			if (adm.getSelecionados().getCampoObjetoFormularioVOArray() != null) {
				// Tratamento para não permitir duplicidade de elementos
				AdmCamposFormularioVO obj = AdmCamposFormularioVO.Factory.newInstance();
				obj.addNewSelecionados();
				String idCampoObjetoAnterior = ConstantesCRM.SZERO;
				int contador = 0;
				for (int i = 0; i < adm.getSelecionados().getCampoObjetoFormularioVOArray().length; i++) {
					// if
					// (!idCampoObjetoAnterior.equals(adm.getSelecionados().getCampoObjetoFormularioVOArray(i).getIdCampoObjeto()))
					// {
					obj.getSelecionados().addNewCampoObjetoFormularioVO().set(adm.getSelecionados().getCampoObjetoFormularioVOArray(i).copy());
					idCampoObjetoAnterior = adm.getSelecionados().getCampoObjetoFormularioVOArray(i).getIdCampoObjeto();
					// }
				}
				formFormulario.setListaAssociadaCamposObjetos(obj.getSelecionados().getCampoObjetoFormularioVOArray());
			}
		}

		if (adm.getDisponiveis() != null) {
			if (adm.getDisponiveis().getAdmTipoLinhaVOArray() != null) {
				formFormulario.setTipoLinhasExistentes(adm.getDisponiveis().getAdmTipoLinhaVOArray());
			}
			if (adm.getDisponiveis().getAdmCampoVOArray() != null) {
				formFormulario.setCamposExistentes(adm.getDisponiveis().getAdmCampoVOArray());
			}
			if (adm.getDisponiveis().getUFOperadoraUsuarioVOArray() != null) {
				formFormulario.setUfOperadorasExistentes(adm.getDisponiveis().getUFOperadoraUsuarioVOArray());
			}
		}

		if (adm.getListaAssociada() != null) {
			if (adm.getListaAssociada().getAdmCamposHistoricoVOArray() != null) {
				formFormulario.setListaAssociada(adm.getListaAssociada().getAdmCamposHistoricoVOArray());
			}
			if (adm.getListaAssociada().getCampoObjetoFormularioVOArray() != null) {
				formFormulario.setListaAssociadaCamposObjetos(adm.getListaAssociada().getCampoObjetoFormularioVOArray());
			}
		}
	}

	public void populaCombos(AdmCamposFormularioVO adm, HttpServletRequest request) {
		formFormulario.setMsgError("");

		formFormulario.setIdContato(request.getParameter("idContato"));
		formFormulario.setArrayCampoClassificador(adm.getAdmClassificadorCamposVO().getAdmClassificadorCampoVOArray());

		if (adm.getAdmFaseProcessosVO() != null) {
			formFormulario.setArrayFaseProcesso(adm.getAdmFaseProcessosVO().getAdmFaseProcessoVOArray());
		}

		formFormulario.setUfOperadorasAssociadas(adm.addNewSelecionados().getUFOperadoraUsuarioVOArray());
		formFormulario.setUfOperadorasExistentes(adm.addNewDisponiveis().getUFOperadoraUsuarioVOArray());

		formFormulario.setTipoLinhasAssociadas(adm.addNewSelecionados().getAdmTipoLinhaVOArray());
		formFormulario.setTipoLinhasExistentes(adm.addNewDisponiveis().getAdmTipoLinhaVOArray());

		formFormulario.setCamposAssociados(adm.addNewSelecionados().getAdmCampoVOArray());
		formFormulario.setCamposExistentes(adm.addNewDisponiveis().getAdmCampoVOArray());

		flag = false;
	}

	public AdmCamposFormularioVO recuperaCampos(FormFormulario form) {
		formFormulario.setMsgError("");

		AdmCamposFormularioVO admCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();
		admCamposFormularioVO.setIdContato(form.getIdContato());
		admCamposFormularioVO.setIdClassificadorCampoAtual(form.getIdClassificadorCampoAtual());
		admCamposFormularioVO.setIdFaseProcessoAtual(form.getIdFaseProcessoAtual());

		UFOperadoraUsuarioVO[] ufOperadoraUsuarioVO = new UFOperadoraUsuarioVO[form.getaOperadoraSelecionados().length];
		String[] operadoraSelecionada = new String[form.getaOperadoraSelecionados().length];
		operadoraSelecionada = form.getaOperadoraSelecionados();
		for (int i = 0; i < operadoraSelecionada.length; i++) {
			ufOperadoraUsuarioVO[i] = UFOperadoraUsuarioVO.Factory.newInstance();
			ufOperadoraUsuarioVO[i].setIdUFOperadora(operadoraSelecionada[i]);
		}
		admCamposFormularioVO.addNewSelecionados().setUFOperadoraUsuarioVOArray(ufOperadoraUsuarioVO);

		AdmTipoLinhaVO[] admTipoLinhaVO = new AdmTipoLinhaVO[form.getaTipoLinhaSelecionados().length];
		String[] linhaSelecionada = new String[form.getaTipoLinhaSelecionados().length];
		linhaSelecionada = form.getaTipoLinhaSelecionados();
		for (int i = 0; i < linhaSelecionada.length; i++) {
			admTipoLinhaVO[i] = AdmTipoLinhaVO.Factory.newInstance();
			admTipoLinhaVO[i].setIdTipoLinha(linhaSelecionada[i]);
		}
		admCamposFormularioVO.getSelecionados().setAdmTipoLinhaVOArray(admTipoLinhaVO);

		if (getFormFormulario().getListaAssociadaCamposObjetos().length > 0) {
			CampoObjetoFormularioVO[] campoObjetoFormularioVO = new CampoObjetoFormularioVO[getFormFormulario().getListaAssociadaCamposObjetos().length];
			for (int i = 0; i < getFormFormulario().getListaAssociadaCamposObjetos().length; i++) {
				campoObjetoFormularioVO[i] = CampoObjetoFormularioVO.Factory.newInstance();
				campoObjetoFormularioVO[i].setIdCampoObjeto(getFormFormulario().getListaAssociadaCamposObjetos()[i].getIdCampoObjeto());
				campoObjetoFormularioVO[i].setInClassificadorComponente(getFormFormulario().getListaAssociadaCamposObjetos()[i].getInClassificadorComponente());
			}
			admCamposFormularioVO.getSelecionados().setCampoObjetoFormularioVOArray(campoObjetoFormularioVO);
		}

		if (form.getaCamposSelecionados() != null && form.getaCamposSelecionados().length > 0) {
			AdmCampoVO[] admCampoVO = new AdmCampoVO[form.getaCamposSelecionados().length];
			String[] campoSelecionado = new String[form.getaCamposSelecionados().length];
			campoSelecionado = form.getaCamposSelecionados();
			for (int i = 0; i < campoSelecionado.length; i++) {
				admCampoVO[i] = AdmCampoVO.Factory.newInstance();
				admCampoVO[i].setIdCampo(campoSelecionado[i]);
				admCampoVO[i].setNmCampo("");
			}
			admCamposFormularioVO.getSelecionados().setAdmCampoVOArray(admCampoVO);
		}

		return admCamposFormularioVO;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularCamposForm.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaCamposForm(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AbaCamposFormController:salvaCamposForm" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormFormulario form = (FormFormulario) formParam;
		formFormulario.setMsgError("");
		AdmCamposFormularioVO admCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();
		AdmCamposFormularioVO retAdmCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();

		try {

			String user = ConstantesCRM.getCurrentUser(request.getSession());

			admCamposFormularioVO = recuperaCampos(form);
			admCamposFormularioVO.setInOperacao("GRAVAR");
			admCamposFormularioVO.setIdFaseProcessoAtual(form.getIdFaseProcessoAtual());
			admCamposFormularioVO.setIdContato(form.getIdContato());

			admCamposFormularioVO = camposFormularioTux.carregaCamposFormulario(admCamposFormularioVO, user);

			if (admCamposFormularioVO.getMsgRetorno() != null && !ConstantesCRM.SVAZIO.equals(admCamposFormularioVO.getMsgRetorno())) {
				request.setAttribute("msg", admCamposFormularioVO.getMsgRetorno());
			} else {
				request.setAttribute("msg", ConstantesCRM.SSucesso);
			}

		} catch (TuxedoWarningException twe) {
			log.warn("AbaCamposFormController:salvaCamposForm" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formFormulario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
		} catch (Exception e) {
			if (e.getMessage().indexOf("14E0099") > 0) {
				request.setAttribute("msg", e.getMessage().substring(e.getMessage().lastIndexOf("]") + 2, e.getMessage().length()));
				request.setAttribute("formFormulario", formFormulario);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			} else {
				log.error("AbaCamposFormController:salvaCamposForm" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
				FormError formError = globalApp.buildFormError(e, request);
				formError.setTarget(ConstantesCRM.FRAMEAPP);
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}
		}
		request.setAttribute("formFormulario", formFormulario);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularCamposForm.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward alteraCampos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AbaCamposFormController:alteraCampos" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormFormulario form = (FormFormulario) formParam;
		formFormulario.setMsgError("");
		AdmCamposFormularioVO admCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();

		try {
			admCamposFormularioVO.setIdContato(form.getIdContato());
			admCamposFormularioVO.setIdClassificadorCampoAtual(form.getIdClassificadorCampoAtual());
			admCamposFormularioVO.setIdFaseProcessoAtual(form.getIdFaseProcessoAtual());

			String user = ConstantesCRM.getCurrentUser(request.getSession());
			AdmCamposFormularioVO newAdmCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();
			camposFormularioTux.salvaCamposFormulario(admCamposFormularioVO, user);

			admCamposFormularioVO = camposFormularioTux.carregaCamposFormulario(admCamposFormularioVO, user);
			populaForm(admCamposFormularioVO);
			formFormulario.setListaFormularioLength("" + formFormulario.getListaAssociada().length);
			formFormulario.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("AbaCamposFormController:alteraCampos" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formFormulario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("AbaCamposFormController:alteraCampos" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("formFormulario", formFormulario);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularCamposForm.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeCampos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AbaCamposFormController:removeCampos" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormFormulario form = (FormFormulario) formParam;
		formFormulario.setMsgError("");
		AdmCamposFormularioSimplVO admCamposFormularioSimplVO = AdmCamposFormularioSimplVO.Factory.newInstance();

		try {
			admCamposFormularioSimplVO.setIdContato(form.getIdContato());
			admCamposFormularioSimplVO.setIdClassificadorCampoAtual(form.getIdClassificadorCampoAtual());
			admCamposFormularioSimplVO.setIdFaseProcessoAtual(form.getIdFaseProcessoAtual());

			String user = ConstantesCRM.getCurrentUser(request.getSession());
			AdmCamposFormularioVO admCamposFormularioVO = AdmCamposFormularioVO.Factory.newInstance();
			admCamposFormularioVO = camposFormularioTux.removeCamposFormulario(admCamposFormularioSimplVO, user);
			populaForm(admCamposFormularioVO);
			formFormulario.setListaFormularioLength("" + formFormulario.getListaAssociada().length);
			formFormulario.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("AbaCamposFormController:removeCampos" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formFormulario.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("AbaCamposFormController:removeCampos" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("formFormulario", formFormulario);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class FormFormulario extends ActionForm {

		private static final long serialVersionUID = -8432004673343598367L;

		private String idxNivelExcluido;
		private ArrayList camposDependentesExcluidos;
		private AdmArvoreGruposCamposDependentesVO arvoreCamposDependentes;
		private String[] arrayQtdeCamposPorGrupo;
		private String[] arrayNmCamposDependentes;
		private String[] arrayIdCamposDependentes;
		private String inContatoAssociado;
		private String inOperacao;
		private String nmGrupoCampos;
		private int arrayGrupoCamposLength = 0;
		private String idGrupoCampos;
		private String qtdeNiveis;
		private String inCamposComponentes;
		private String msgError = "";
		private String listaFormularioLength;
		private Vector listasso;
		private UFOperadoraUsuarioVO[] ufOperadorasExistentes;
		private UFOperadoraUsuarioVO[] ufOperadorasAssociadas;
		private AdmCamposHistoricoVO[] listaAssociada;
		private CampoObjetoFormularioVO[] listaAssociadaCamposObjetos;
		private CampoObjetoFormularioVO[] listaDisponiveisCamposObjetos;
		private String idContato;
		private String inMsgErro;
		private String idFaseProcessoAtual;
		private String idClassificadorCampoAtual;
		private AdmFaseProcessoVO[] arrayFaseProcesso;
		private AdmGrupoCamposDependentesVO[] arrayGrupoCamposDependentes;
		private AdmGrupoCamposVO[] arrayGrupoCampos;
		private AdmClassificadorCampoVO[] arrayCampoClassificador;
		private AdmContatoUFOperadoraVO[] operadorasExistentes;
		private AdmContatoUFOperadoraVO[] operadorasAssociadas;
		private AdmTipoLinhaVO[] tipoLinhasExistentes;
		private AdmTipoLinhaVO[] tipoLinhasAssociadas;
		private AdmCampoVO[] camposExistentes;
		private AdmCampoVO[] camposAssociados;
		private String[] aCamposSelecionados;
		private String[] aCamposExistentes;
		private String[] aTipoLinhaSelecionados;
		private String[] aTipoLinhaExistentes;
		private String[] aOperadoraSelecionados;
		private String[] aOperadoraExistentes;
		private String[] camposExcluidos;
		private EnderecoVO enderecoVO;
		private int numeroCampos;
		private AtendimentoVO atendimentoVO;
		private ValorCampo[] valorCampo;
		private String[] idCampoNiveis;
		private String idPai;
		private String descricaoPai;
		private AdmGruposCamposDependentesVO admGruposCamposDependentesVO;

		public FormFormulario() {
			arrayFaseProcesso = new AdmFaseProcessoVO[0];
			arrayGrupoCamposDependentes = new AdmGrupoCamposDependentesVO[0];
			arrayGrupoCampos = new AdmGrupoCamposVO[0];
			arrayCampoClassificador = new AdmClassificadorCampoVO[0];
			ufOperadorasExistentes = new UFOperadoraUsuarioVO[0];
			ufOperadorasAssociadas = new UFOperadoraUsuarioVO[0];
			operadorasExistentes = new AdmContatoUFOperadoraVO[0];
			operadorasAssociadas = new AdmContatoUFOperadoraVO[0];
			tipoLinhasExistentes = new AdmTipoLinhaVO[0];
			tipoLinhasAssociadas = new AdmTipoLinhaVO[0];
			camposExistentes = new AdmCampoVO[0];
			camposAssociados = new AdmCampoVO[0];
			listaAssociada = new AdmCamposHistoricoVO[0];
			listaAssociadaCamposObjetos = new CampoObjetoFormularioVO[0];
			listaDisponiveisCamposObjetos = new CampoObjetoFormularioVO[0];
			enderecoVO = EnderecoVO.Factory.newInstance();
			atendimentoVO = AtendimentoVO.Factory.newInstance();
			admGruposCamposDependentesVO = AdmGruposCamposDependentesVO.Factory.newInstance();
			idCampoNiveis = new String[20];
			camposExcluidos = new String[20];
			inContatoAssociado = ConstantesCRM.SZERO;
			idPai = ConstantesCRM.SVAZIO;
			descricaoPai = ConstantesCRM.SVAZIO;
			msgError = ConstantesCRM.SVAZIO;
		}

		public void setaOperadoraExistentes(String[] aOperadoraExistentes) {
			this.aOperadoraExistentes = aOperadoraExistentes;
		}

		public String[] getaOperadoraExistentes() {
			if (this.aOperadoraExistentes == null || this.aOperadoraExistentes.length == 0) {
				this.aOperadoraExistentes = new String[0];
			}
			return this.aOperadoraExistentes;
		}

		public void setaOperadoraSelecionados(String[] aOperadoraSelecionados) {
			this.aOperadoraSelecionados = aOperadoraSelecionados;
		}

		public void setEnderecoVO(EnderecoVO enderecoVO) {
			this.enderecoVO = enderecoVO;
		}

		public EnderecoVO getEnderecoVO() {
			if (this.enderecoVO == null) {
				this.enderecoVO = EnderecoVO.Factory.newInstance();
			}
			return this.enderecoVO;
		}

		public String[] getCamposExcluidos() {
			if (this.camposExcluidos == null || this.camposExcluidos.length == 0) {
				this.camposExcluidos = new String[20];
			}
			return this.camposExcluidos;
		}

		public void setCamposExcluidos(String[] camposExcluidos) {
			this.camposExcluidos = camposExcluidos;
		}

		public String[] getaOperadoraSelecionados() {
			if (this.aOperadoraSelecionados == null || this.aOperadoraSelecionados.length == 0) {
				this.aOperadoraSelecionados = new String[0];
			}
			return this.aOperadoraSelecionados;
		}

		public void setaTipoLinhaExistentes(String[] aTipoLinhaExistentes) {
			this.aTipoLinhaExistentes = aTipoLinhaExistentes;
		}

		public String[] getIdCampoNiveis() {
			if (this.idCampoNiveis == null || this.idCampoNiveis.length == 0) {
				this.idCampoNiveis = new String[20];
			}
			return this.idCampoNiveis;
		}

		public void setIdCampoNiveis(String[] idCampoNiveis) {
			this.idCampoNiveis = idCampoNiveis;
		}

		public String[] getaTipoLinhaExistentes() {
			if (this.aTipoLinhaExistentes == null || this.aTipoLinhaExistentes.length == 0) {
				this.aTipoLinhaExistentes = new String[0];
			}
			return this.aTipoLinhaExistentes;
		}

		public void setQtdeNiveis(String qtdeNiveis) {
			this.qtdeNiveis = qtdeNiveis;
		}

		public String getQtdeNiveis() {
			if (this.qtdeNiveis == null) {
				this.qtdeNiveis = ConstantesCRM.SZERO;
			}
			return this.qtdeNiveis;
		}

		public void setIdPai(String idPai) {
			this.idPai = idPai;
		}

		public String getIdPai() {
			if (this.idPai == null) {
				this.idPai = ConstantesCRM.SZERO;
			}
			return this.idPai;
		}

		public void setInContatoAssociado(String inContatoAssociado) {
			this.inContatoAssociado = inContatoAssociado;
		}

		public String getInContatoAssociado() {
			if (this.inContatoAssociado == null) {
				this.inContatoAssociado = ConstantesCRM.SZERO;
			}
			return this.inContatoAssociado;
		}

		public void setInMsgErro(String inMsgErro) {
			this.inMsgErro = inMsgErro;
		}

		public String getInMsgErro() {
			if (this.inMsgErro == null) {
				this.inMsgErro = ConstantesCRM.SVAZIO;
			}
			return this.inMsgErro;
		}

		public void setDescricaoPai(String descricaoPai) {
			this.descricaoPai = descricaoPai;
		}

		public String getDescricaoPai() {
			if (this.descricaoPai == null) {
				this.descricaoPai = ConstantesCRM.SVAZIO;
			}
			return this.descricaoPai;
		}

		public void setaTipoLinhaSelecionados(String[] aTipoLinhaSelecionados) {
			this.aTipoLinhaSelecionados = aTipoLinhaSelecionados;
		}

		public String[] getaTipoLinhaSelecionados() {
			if (this.aTipoLinhaSelecionados == null || this.aTipoLinhaSelecionados.length == 0) {
				this.aTipoLinhaSelecionados = new String[0];
			}
			return this.aTipoLinhaSelecionados;
		}

		public void setArrayFaseProcesso(AdmFaseProcessoVO[] arrayFaseProcesso) {
			this.arrayFaseProcesso = arrayFaseProcesso;
		}

		public AdmFaseProcessoVO[] getArrayFaseProcesso() {
			return this.arrayFaseProcesso;
		}

		public void setArrayGrupoCamposDependentes(AdmGrupoCamposDependentesVO[] arrayGrupoCamposDependentes) {
			this.arrayGrupoCamposDependentes = arrayGrupoCamposDependentes;
		}

		public AdmGrupoCamposDependentesVO[] getArrayGrupoCamposDependentes() {
			return this.arrayGrupoCamposDependentes;
		}

		public void setArrayGrupoCampos(AdmGrupoCamposVO[] arrayGrupoCampos) {
			this.arrayGrupoCampos = arrayGrupoCampos;
		}

		public AdmGrupoCamposVO[] getArrayGrupoCampos() {
			return this.arrayGrupoCampos;
		}

		public void setaCamposExistentes(String[] aCamposExistentes) {
			this.aCamposExistentes = aCamposExistentes;
		}

		public String[] getaCamposExistentes() {
			if (this.aCamposExistentes == null || this.aCamposExistentes.length == 0) {
				this.aCamposExistentes = new String[0];
			}
			return this.aCamposExistentes;
		}

		public void setaCamposSelecionados(String[] aCamposSelecionados) {
			this.aCamposSelecionados = aCamposSelecionados;
		}

		public String[] getaCamposSelecionados() {
			if (this.aCamposSelecionados == null || this.aCamposSelecionados.length == 0) {
				this.aCamposSelecionados = new String[0];
			}
			return this.aCamposSelecionados;
		}

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setCamposAssociados(AdmCampoVO[] camposAssociados) {
			this.camposAssociados = camposAssociados;
		}

		public AdmCampoVO[] getCamposAssociados() {
			return this.camposAssociados;
		}

		public void setCamposExistentes(AdmCampoVO[] camposExistentes) {
			this.camposExistentes = camposExistentes;
		}

		public AdmCampoVO[] getCamposExistentes() {
			return this.camposExistentes;
		}

		public void setTipoLinhasAssociadas(AdmTipoLinhaVO[] tipoLinhasAssociadas) {
			this.tipoLinhasAssociadas = tipoLinhasAssociadas;
		}

		public AdmTipoLinhaVO[] getTipoLinhasAssociadas() {
			return this.tipoLinhasAssociadas;
		}

		public void setTipoLinhasExistentes(AdmTipoLinhaVO[] tipoLinhasExistentes) {
			this.tipoLinhasExistentes = tipoLinhasExistentes;
		}

		public AdmTipoLinhaVO[] getTipoLinhasExistentes() {
			return this.tipoLinhasExistentes;
		}

		public void setOperadorasAssociadas(AdmContatoUFOperadoraVO[] operadorasAssociadas) {
			this.operadorasAssociadas = operadorasAssociadas;
		}

		public AdmContatoUFOperadoraVO[] getOperadorasAssociadas() {
			return this.operadorasAssociadas;
		}

		public void setOperadorasExistentes(AdmContatoUFOperadoraVO[] operadorasExistentes) {
			this.operadorasExistentes = operadorasExistentes;
		}

		public AdmContatoUFOperadoraVO[] getOperadorasExistentes() {
			return this.operadorasExistentes;
		}

		public void setIdClassificadorCampoAtual(String idClassificadorCampoAtual) {
			this.idClassificadorCampoAtual = idClassificadorCampoAtual;
		}

		public String getIdClassificadorCampoAtual() {
			return this.idClassificadorCampoAtual;
		}

		public void setIdFaseProcessoAtual(String idFaseProcessoAtual) {
			this.idFaseProcessoAtual = idFaseProcessoAtual;
		}

		public String getIdFaseProcessoAtual() {
			return this.idFaseProcessoAtual;
		}

		public void setArrayCampoClassificador(AdmClassificadorCampoVO[] arrayCampoClassificador) {
			this.arrayCampoClassificador = arrayCampoClassificador;
		}

		public AdmClassificadorCampoVO[] getArrayCampoClassificador() {
			return this.arrayCampoClassificador;
		}

		public void setListaAssociada(AdmCamposHistoricoVO[] listaAssociada) {
			this.listaAssociada = listaAssociada;
		}

		public AdmCamposHistoricoVO[] getListaAssociada() {
			return this.listaAssociada;
		}

		public void setListaAssociadaCamposObjetos(CampoObjetoFormularioVO[] listaAssociadaCamposObjetos) {
			this.listaAssociadaCamposObjetos = listaAssociadaCamposObjetos;
		}

		public CampoObjetoFormularioVO[] getListaAssociadaCamposObjetos() {
			if (this.listaAssociadaCamposObjetos == null) {
				this.listaAssociadaCamposObjetos = new CampoObjetoFormularioVO[0];
			}
			return this.listaAssociadaCamposObjetos;
		}

		public void setListaDisponiveisCamposObjetos(CampoObjetoFormularioVO[] listaDisponiveisCamposObjetos) {
			this.listaDisponiveisCamposObjetos = listaDisponiveisCamposObjetos;
		}

		public CampoObjetoFormularioVO[] getListaDisponiveisCamposObjetos() {
			return this.listaDisponiveisCamposObjetos;
		}

		public void setUfOperadorasAssociadas(UFOperadoraUsuarioVO[] ufOperadorasAssociadas) {
			this.ufOperadorasAssociadas = ufOperadorasAssociadas;
		}

		public UFOperadoraUsuarioVO[] getUfOperadorasAssociadas() {
			return this.ufOperadorasAssociadas;
		}

		public void setUfOperadorasExistentes(UFOperadoraUsuarioVO[] ufOperadorasExistentes) {
			this.ufOperadorasExistentes = ufOperadorasExistentes;
		}

		public UFOperadoraUsuarioVO[] getUfOperadorasExistentes() {
			return this.ufOperadorasExistentes;
		}

		public void setListasso(Vector listasso) {
			this.listasso = listasso;
		}

		public Vector getListasso() {
			return this.listasso;
		}

		public void setListaFormularioLength(String listaFormularioLength) {
			this.listaFormularioLength = listaFormularioLength;
		}

		public String getListaFormularioLength() {
			return this.listaFormularioLength;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setInCamposComponentes(String inCamposComponentes) {
			this.inCamposComponentes = inCamposComponentes;
		}

		public String getInCamposComponentes() {
			if (this.inCamposComponentes == null) {
				this.inCamposComponentes = ConstantesCRM.SVAZIO;
			}
			return this.inCamposComponentes;
		}

		public void setIdGrupoCampos(String idGrupoCampos) {
			this.idGrupoCampos = idGrupoCampos;
		}

		public String getIdGrupoCampos() {
			if (this.idGrupoCampos == null) {
				this.idGrupoCampos = ConstantesCRM.SVAZIO;
			}
			return this.idGrupoCampos;
		}

		public void setArrayGrupoCamposLength(int arrayGrupoCamposLength) {
			this.arrayGrupoCamposLength = arrayGrupoCamposLength;
		}

		public int getArrayGrupoCamposLength() {
			return this.arrayGrupoCamposLength;
		}

		public void setNmGrupoCampos(String nmGrupoCampos) {
			this.nmGrupoCampos = nmGrupoCampos;
		}

		public String getNmGrupoCampos() {
			if (this.nmGrupoCampos == null) {
				this.nmGrupoCampos = ConstantesCRM.SVAZIO;
			}
			return this.nmGrupoCampos;
		}

		public void setInOperacao(String inOperacao) {
			this.inOperacao = inOperacao;
		}

		public String getInOperacao() {
			if (this.inOperacao == null) {
				this.inOperacao = ConstantesCRM.SVAZIO;
			}
			return this.inOperacao;
		}

		public int getNumeroCampos() {
			return this.numeroCampos;
		}

		public void setNumeroCampos(int numeroCampos) {
			this.numeroCampos = numeroCampos;
		}

		public AtendimentoVO getAtendimentoVO() {
			return this.atendimentoVO;
		}

		public void setAtendimentoVO(AtendimentoVO atendimentoVO) {
			this.atendimentoVO = atendimentoVO;
		}

		public AdmGruposCamposDependentesVO getAdmGruposCamposDependentesVO() {
			return this.admGruposCamposDependentesVO;
		}

		public void setAdmGruposCamposDependentesVO(AdmGruposCamposDependentesVO admGruposCamposDependentesVO) {
			this.admGruposCamposDependentesVO = admGruposCamposDependentesVO;
		}

		public ValorCampo[] getValorCampo() {
			if (valorCampo == null) {
				valorCampo = new ValorCampo[numeroCampos];
				for (int i = 0; i < valorCampo.length; i++) {
					valorCampo[i] = new ValorCampo();
					valorCampo[i].setValorArray(new String[0]);
				}
			}
			return (this.valorCampo);
		}

		public void setValorCampo(ValorCampo[] valorCampo) {
			this.valorCampo = valorCampo;
		}

		public void setArvoreCamposDependentes(AdmArvoreGruposCamposDependentesVO arvoreCamposDependentes) {
			this.arvoreCamposDependentes = arvoreCamposDependentes;
		}

		public AdmArvoreGruposCamposDependentesVO getArvoreCamposDependentes() {
			return this.arvoreCamposDependentes;
		}

		public void setCamposDependentesExcluidos(ArrayList camposDependentesExcluidos) {
			this.camposDependentesExcluidos = camposDependentesExcluidos;
		}

		public ArrayList getCamposDependentesExcluidos() {
			if (this.camposDependentesExcluidos == null) {
				this.camposDependentesExcluidos = new ArrayList();
			}
			return this.camposDependentesExcluidos;
		}

		public void setIdxNivelExcluido(String idxNivelExcluido) {
			this.idxNivelExcluido = idxNivelExcluido;
		}

		public String getIdxNivelExcluido() {
			if (this.idxNivelExcluido == null) {
				this.idxNivelExcluido = ConstantesCRM.SVAZIO;
			}
			return this.idxNivelExcluido;
		}
	}

	public static class ValorCampo implements Serializable {

		private static final long serialVersionUID = 6667160225314774123L;

		private String valor;
		private String[] valorArray;

		public String getValor() {
			return valor;
		}

		public String[] getValorArray() {
			return valorArray;
		}

		public void setValor(String strings) {
			valor = strings;
		}

		public void setValorArray(String[] string) {
			valorArray = string;
		}
	}

	public FormFormulario getFormFormulario() {
		return this.formFormulario;
	}

}
