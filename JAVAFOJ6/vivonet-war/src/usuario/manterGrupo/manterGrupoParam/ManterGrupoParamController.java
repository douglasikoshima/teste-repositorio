package usuario.manterGrupo.manterGrupoParam;

import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import br.com.vivo.fo.admsistemas.vo.CarterizacaoVODocument.CarterizacaoVO;
import br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO;
import br.com.vivo.fo.admsistemas.vo.ProcedenciaVODocument.ProcedenciaVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoDisponivelVODocument.RegrasEncaminhamentoDisponivelVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoSelecionadoVODocument.RegrasEncaminhamentoSelecionadoVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoVODocument.RegrasEncaminhamentoVO;
import br.com.vivo.fo.admsistemas.vo.SegmentacaoVODocument.SegmentacaoVO;
import br.com.vivo.fo.admsistemas.vo.TipoClienteVODocument.TipoClienteVO;
import br.com.vivo.fo.admsistemas.vo.impl.CarterizacaoVODocumentImpl.CarterizacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.GrupoVODocumentImpl.GrupoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.ProcedenciaVODocumentImpl.ProcedenciaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.SegmentacaoVODocumentImpl.SegmentacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.TipoClienteVODocumentImpl.TipoClienteVOImpl;
import br.com.vivo.fo.cliente.vo.TipoLinhaVODocument.TipoLinhaVO;
import br.com.vivo.fo.cliente.vo.impl.TipoLinhaVODocumentImpl.TipoLinhaVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterGrupo.ManterGrupoFacade;
import br.com.vivo.fo.ctrls.usuario.manterSistema.ManterSistemaFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.fidelizacao.vo.TipoPessoaVODocument.TipoPessoaVO;
import br.com.vivo.fo.fidelizacao.vo.impl.TipoPessoaVODocumentImpl.TipoPessoaVOImpl;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.CanalUsuarioVODocument.CanalUsuarioVO;
import br.com.vivo.fo.usuario.vo.GrupoCanalRelacionadoVODocument.GrupoCanalRelacionadoVO;
import br.com.vivo.fo.usuario.vo.GrupoCanalRelacionadoVODocument.GrupoCanalRelacionadoVO.CanaisRelacionados;
import br.com.vivo.fo.usuario.vo.GrupoPerfilRelacionadoVODocument.GrupoPerfilRelacionadoVO;
import br.com.vivo.fo.usuario.vo.GrupoPerfilRelacionadoVODocument.GrupoPerfilRelacionadoVO.PerfisRelacionados;
import br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO;
import br.com.vivo.fo.usuario.vo.GruposUsuarioVODocument.GruposUsuarioVO;
import br.com.vivo.fo.usuario.vo.ItemMenuExistentesVODocument.ItemMenuExistentesVO;
import br.com.vivo.fo.usuario.vo.ListaItensMenuVODocument.ListaItensMenuVO;
import br.com.vivo.fo.usuario.vo.ListaSistemaUsuarioVODocument.ListaSistemaUsuarioVO;
import br.com.vivo.fo.usuario.vo.ManterSkillVODocument.ManterSkillVO;
import br.com.vivo.fo.usuario.vo.MenuParamPesquisaItensVODocument.MenuParamPesquisaItensVO;
import br.com.vivo.fo.usuario.vo.PerfilUsuarioVODocument.PerfilUsuarioVO;
import br.com.vivo.fo.usuario.vo.PesquisaSkillVODocument.PesquisaSkillVO;
import br.com.vivo.fo.usuario.vo.RelacionarGrupoCanalVODocument.RelacionarGrupoCanalVO;
import br.com.vivo.fo.usuario.vo.RelacionarGrupoPerfilVODocument.RelacionarGrupoPerfilVO;
import br.com.vivo.fo.usuario.vo.SalvarItensMenuRelacionadosVODocument.SalvarItensMenuRelacionadosVO;
import br.com.vivo.fo.usuario.vo.SistemaIDVODocument.SistemaIDVO;
import br.com.vivo.fo.usuario.vo.SistemaManterUsuarioVODocument.SistemaManterUsuarioVO;
import br.com.vivo.fo.usuario.vo.SubSistemaUsuarioVODocument.SubSistemaUsuarioVO;
import br.com.vivo.fo.usuario.vo.SubSistemasUsuarioVODocument.SubSistemasUsuarioVO;
import br.com.vivo.fo.workflow.vo.CanalVODocument.CanalVO;
import br.com.vivo.fo.workflow.vo.impl.CanalVODocumentImpl.CanalVOImpl;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterGrupoParamController extends AbstractAction {

	private static final long serialVersionUID = -2835925221992619084L;

	@EJB
	private ManterSistemaFacade controlSistema;

	@EJB
	private ManterGrupoFacade controlGrupo;

	private ListaGruposForm lGrpForm = new ListaGruposForm();

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("usuario");

	private String idTipoGrupoPesquisa = ConstantesCRM.SVAZIO;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("listaGrupos".equals(mapping.getParameter())) {
			return listaGrupos(mapping, form, request, response);
		} else if ("editaRelGrupo".equals(mapping.getParameter())) {
			return editaRelGrupo(mapping, form, request, response);
		} else if ("salvaRelCanal".equals(mapping.getParameter())) {
			return salvaRelCanal(mapping, form, request, response);
		} else if ("salvaRelPerfil".equals(mapping.getParameter())) {
			return salvaRelPerfil(mapping, form, request, response);
		} else if ("obtemSubSistemas".equals(mapping.getParameter())) {
			return obtemSubSistemas(mapping, form, request, response);
		} else if ("obtemItemMenu".equals(mapping.getParameter())) {
			return obtemItemMenu(mapping, form, request, response);
		} else if ("salvaItem".equals(mapping.getParameter())) {
			return salvaItem(mapping, form, request, response);
		} else if ("removeGrupo".equals(mapping.getParameter())) {
			return removeGrupo(mapping, form, request, response);
		} else if ("salvaItemPesquisa".equals(mapping.getParameter())) {
			return salvaItemPesquisa(mapping, form, request, response);
		} else if ("salvaItemAlterado".equals(mapping.getParameter())) {
			return salvaItemAlterado(mapping, form, request, response);
		} else if ("salvaEditGrupo".equals(mapping.getParameter())) {
			return salvaEditGrupo(mapping, form, request, response);
		} else if ("relacionarGrupoCanal".equals(mapping.getParameter())) {
			return relacionarGrupoCanal(mapping, form, request, response);
		} else if ("relacionarGrupoPerfil".equals(mapping.getParameter())) {
			return relacionarGrupoPerfil(mapping, form, request, response);
		} else if ("relacionarGrupoItemMenu".equals(mapping.getParameter())) {
			return relacionarGrupoItemMenu(mapping, form, request, response);
		} else if ("limpaForm".equals(mapping.getParameter())) {
			return limpaForm(mapping, form, request, response);
		} else if ("listaSkill".equals(mapping.getParameter())) {
			return listaSkill(mapping, form, request, response);
		} else if ("manutencaoSkill".equals(mapping.getParameter())) {
			return manutencaoSkill(mapping, form, request, response);
		} else if ("removeSkill".equals(mapping.getParameter())) {
			return removeSkill(mapping, form, request, response);
		} else if ("alteraSkill".equals(mapping.getParameter())) {
			return alteraSkill(mapping, form, request, response);
		} else if ("insereSkill".equals(mapping.getParameter())) {
			return insereSkill(mapping, form, request, response);
		} else if ("incluirAlterarSkill".equals(mapping.getParameter())) {
			return incluirAlterarSkill(mapping, form, request, response);
		} else if ("parametrizarSkill".equals(mapping.getParameter())) {
			return parametrizarSkill(mapping, form, request, response);
		} else if ("AplicarRegrasEncaminhamentoSkill".equals(mapping.getParameter())) {
			return AplicarRegrasEncaminhamentoSkill(mapping, form, request, response);
		} else if ("ObtemRegrasEncaminhamento".equals(mapping.getParameter())) {
			return ObtemRegrasEncaminhamento(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarGrupo.jsp"
	 * @jpf:forward name="retornoSkill" path="editaRelGrupo.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String destino = ConstantesCRM.SUCCESS;
		ActionForward forward = mapping.findForward(destino);

		try {
			log.debug("ManterGrupoParamController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

			if (request.getParameter("isMenu") == null || lGrpForm == null) {
				// Cria um atributo Formulario
				lGrpForm = new ListaGruposForm();
				lGrpForm.setIdTipoGrupoPesquisa(ConstantesCRM.SVAZIO);
				lGrpForm.setIdTipoGrupoSelecionado(ConstantesCRM.SVAZIO);
			} else {
				// Grava o resultado da pesquisa nos formularios.
				if (lGrpForm.getArrayGruposVO() != null && lGrpForm.getArrayGruposVO().getGrupoUsuarioVOArray() != null && lGrpForm.getArrayGruposVO().getGrupoUsuarioVOArray().length > 0) {
					lGrpForm.setArrayGrupoLength(String.valueOf(lGrpForm.getArrayGruposVO().getGrupoUsuarioVOArray()));
				}
				lGrpForm.setIdTipoGrupoPesquisa(idTipoGrupoPesquisa);
			}

			GrupoUsuarioVO tipos = GrupoUsuarioVO.Factory.newInstance();
			tipos = controlGrupo.getTiposGrupo(ConstantesCRM.getCurrentUser(request.getSession()));
			lGrpForm.setTiposGrupo(tipos.getTiposGrupoArray());

			lGrpForm.setIdGrupo(ConstantesCRM.SVAZIO);
			lGrpForm.setDsGrupo(ConstantesCRM.SVAZIO);
			lGrpForm.setMsgError(ConstantesCRM.SVAZIO);

		} catch (Exception e) {
			log.error("ManterGrupoParamController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		if (request.getParameter("idGrupo") != null && request.getParameter("isMenu") == null) {
			destino = "retornoSkill";
			ActionRedirect f = new ActionRedirect(destino);
			f.addParameter("grupoId", request.getParameter("idGrupo"));
			f.addParameter("fromSkill",true);
			f.addParameter("abaSource", request.getParameter("abaSource"));
			forward = f;
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return forward;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarGrupo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaGrupos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);
		log.debug("ManterGrupoParamController:listaGrupos" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		try {
			this.idTipoGrupoPesquisa = form.getIdTipoGrupoPesquisa();
			// Pega os dados do Grupo a ser pesquisado
			GrupoUsuarioVO grupoPesquisa = GrupoUsuarioVO.Factory.newInstance();

			grupoPesquisa.setIdGrupo(form.getIdGrupo());
			grupoPesquisa.setDsGrupo(form.getDsGrupo());
			grupoPesquisa.setDsTipoGrupoSelecionado(form.getDsTipoGrupoSelecionado());
			grupoPesquisa.setIdTipoGrupoSelecionado(form.getIdTipoGrupoPesquisa());

			lGrpForm.getGrupoNovoVO().setDsGrupo(form.getDsGrupo());
			lGrpForm.setIdTipoGrupoPesquisa(form.getIdTipoGrupoPesquisa());

			// Busca a lista de grupos que atendem a pesquisa feita
			GruposUsuarioVO arrayGruposVO = controlGrupo.pesquisaGrupo(grupoPesquisa, ConstantesCRM.getCurrentUser(request.getSession()));

			// Grava o resultado da pesquisa nos formularios.
			lGrpForm.setArrayGruposVO(arrayGruposVO);

			// Indica que o array de Grupos nao estah vazio
			lGrpForm.setArrayGrupoLength(Integer.toString(arrayGruposVO.getGrupoUsuarioVOArray().length));

			lGrpForm.setIdTipoGrupoPesquisa(ConstantesCRM.SVAZIO);
			lGrpForm.setIdTipoGrupoSelecionado(ConstantesCRM.SVAZIO);

		} catch (TuxedoWarningException twe) {

			log.warn("ManterGrupoParamController:listaGrupos" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterGrupoParamController:listaGrupos" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="editarRelGrupo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editaRelGrupo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterGrupoParamController:editaRelGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			// Recupero o array de grupos do Form
			GrupoUsuarioVO[] arrayGruposVO;
			GrupoUsuarioVO grupoEdit = null;

			// Para retorno apos gravacao de dados de Skill
			if (request.getParameter("fromSkill") != null) {
				GrupoUsuarioVO grupoPesquisa = GrupoUsuarioVO.Factory.newInstance();
				grupoPesquisa.setIdGrupo(request.getParameter("grupoId"));
				GruposUsuarioVO arrayGruposVORetornoSkill = controlGrupo.pesquisaGrupo(grupoPesquisa, ConstantesCRM.getCurrentUser(request.getSession()));
				arrayGruposVO = arrayGruposVORetornoSkill.getGrupoUsuarioVOArray();
				request.setAttribute("abaSource", request.getParameter("abaSource"));

			} else {
				arrayGruposVO = lGrpForm.getArrayGruposVO().getGrupoUsuarioVOArray();
			}

			grupoEdit = lGrpForm.getGrupoUsuarioVOById(request.getParameter("grupoId"), arrayGruposVO);

			// Joga para o formulario (atributo item Grupo) os dados do grupo a ser editado
			lGrpForm.setItemGrupo(grupoEdit);
			lGrpForm.setIdGrupo(request.getParameter("grupoId"));

			request.setAttribute("grupoId", request.getParameter("grupoId"));
			request.getSession().setAttribute("idGrupo", request.getParameter("grupoId"));

			// Chama o controle e carrega no formulario a lista mais recente
			// de canais relacionados e não relacionados
			RelacionarGrupoCanalVO listaCanais = controlGrupo.listaRelacionarGrupoCanal(grupoEdit, ConstantesCRM.getCurrentUser(request.getSession()));
			this.reloadCanais(listaCanais);

			// Chama o controle e carrega no formulario a lista mais recente
			// de grupos relacionados e não relacionados
			RelacionarGrupoPerfilVO listaPerfis = controlGrupo.listaRelacionarGrupoPerfil(grupoEdit, ConstantesCRM.getCurrentUser(request.getSession()));
			this.reloadPerfis(listaPerfis);

			this.loadSistemas(request);

			lGrpForm.setIdSistema(ConstantesCRM.SZERO);
			lGrpForm.setExibeItem("nao");
			lGrpForm.setExibeSubSistema("nao");

		} catch (TuxedoWarningException twe) {
			lGrpForm.setMsgError(twe.getXmlHeader().getStatusText());
			log.warn("ManterGrupoParamController:editaRelGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + lGrpForm.getMsgError());

		} catch (Exception e) {
			log.error("ManterGrupoParamController:editaRelGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		request.getSession().setAttribute("lGrpForm", lGrpForm);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarGrupoCanal.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaRelCanal(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:salvaRelCanal" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			GrupoCanalRelacionadoVO grupoCanalRelacionadoVO = GrupoCanalRelacionadoVO.Factory.newInstance();
			RelacionarGrupoCanalVO listaCanais = RelacionarGrupoCanalVO.Factory.newInstance();
			int tamanho = form.canaisRelacionados.length;
			CanalUsuarioVO[] listaCanaisRelacionados = new CanalUsuarioVO[tamanho];

			// Percorre o array de ids de canais Relacionados e monta um array de canais
			for (int i = 0; i < tamanho; i++) {
				CanalUsuarioVO canal = CanalUsuarioVO.Factory.newInstance();
				canal.setIdCanal(form.canaisRelacionados[i]);
				listaCanaisRelacionados[i] = canal;
			}

			// Grava no objeto apropriado a lista de canais relacionados e o código do grupo
			grupoCanalRelacionadoVO.setIdGrupo(lGrpForm.getIdGrupo());
			grupoCanalRelacionadoVO.setCanaisRelacionados(CanaisRelacionados.Factory.newInstance());
			grupoCanalRelacionadoVO.getCanaisRelacionados().setCanalUsuarioVOArray(listaCanaisRelacionados);

			// salva o conjunto de canais relacionados.
			listaCanais = controlGrupo.salvaGrupoCanalRelacionado(grupoCanalRelacionadoVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// Chama o controle e carrega no formulario a lista mais recente
			// de canais relacionados e não relacionados
			reloadCanais(listaCanais);
			lGrpForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			lGrpForm.setMsgError(twe.getXmlHeader().getStatusText());
			log.warn("ManterGrupoParamController:salvaRelCanal" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + lGrpForm.getMsgError());

		} catch (Exception e) {
			log.error("ManterGrupoParamController:salvaRelCanal" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarGrupoPerfil.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaRelPerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:salvaRelPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			GrupoPerfilRelacionadoVO grupoPerfilRelacionadoVO = GrupoPerfilRelacionadoVO.Factory.newInstance();
			RelacionarGrupoPerfilVO listaPerfis = RelacionarGrupoPerfilVO.Factory.newInstance();
			int tamanho = form.perfisRelacionados.length;
			PerfilUsuarioVO[] listaPerfisRelacionados = new PerfilUsuarioVO[tamanho];

			// Percorre o array de ids de perfisRelacionadosVO e grava no grupoPerfilRelacionadoVO
			for (int i = 0; i < tamanho; i++) {
				PerfilUsuarioVO perfil = PerfilUsuarioVO.Factory.newInstance();
				perfil.setIdPerfil(form.perfisRelacionados[i]);
				listaPerfisRelacionados[i] = perfil;
			}

			// Grava no objeto apropriado a lista de perfis relacionados e o código do grupo
			grupoPerfilRelacionadoVO.setIdGrupo(lGrpForm.getIdGrupo());
			grupoPerfilRelacionadoVO.setPerfisRelacionados(PerfisRelacionados.Factory.newInstance());
			grupoPerfilRelacionadoVO.getPerfisRelacionados().setPerfilUsuarioVOArray(listaPerfisRelacionados);

			listaPerfis = controlGrupo.salvarGrupoPerfilRelacionado(grupoPerfilRelacionadoVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// Chama o controle e carrega no formulario a lista mais recente
			// de grupos relacionados e não relacionados
			reloadPerfis(listaPerfis);
			lGrpForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			lGrpForm.setMsgError(twe.getXmlHeader().getStatusText());
			log.warn("ManterGrupoParamController:salvaRelPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + lGrpForm.getMsgError());

		} catch (Exception e) {
			log.error("ManterGrupoParamController:salvaRelPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarGrupoItemMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward obtemSubSistemas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;
		log.debug("ManterGrupoParamController:obtemSubSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lGrpForm.setMsgError("");
		try {
			SistemaIDVO sistemaIDVO = SistemaIDVO.Factory.newInstance();
			sistemaIDVO.setIdSistema(form.getIdSistema());
			SubSistemasUsuarioVO subSistemasUsuarioVO = SubSistemasUsuarioVO.Factory.newInstance();
			subSistemasUsuarioVO = controlSistema.listaSubSistemasPorSistema(sistemaIDVO, ConstantesCRM.getCurrentUser(request.getSession()));
			lGrpForm.setListaSubSistemas(subSistemasUsuarioVO.getSubSistemaUsuarioVOArray());
			lGrpForm.setExibeSubSistema("sim");
			lGrpForm.setIdSistema(form.getIdSistema());
			lGrpForm.setIdGrupo(form.getIdGrupo());
			lGrpForm.setExibeItem("nao");

		} catch (TuxedoWarningException twe) {
			lGrpForm.setMsgError(twe.getXmlHeader().getStatusText());
			log.warn("ManterGrupoParamController:obtemSubSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + lGrpForm.getMsgError());

		} catch (Exception e) {
			log.error("ManterGrupoParamController:obtemSubSistemas" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarGrupoItemMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward obtemItemMenu(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:obtemItemMenu" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			MenuParamPesquisaItensVO menuParamPesquisaItensVO = MenuParamPesquisaItensVO.Factory.newInstance();

			menuParamPesquisaItensVO.setIdGrupo(form.getIdGrupo());
			menuParamPesquisaItensVO.setIdSubSistema(form.getIdSubSistema());
			menuParamPesquisaItensVO.setIdUsuario(ConstantesCRM.SZERO);

			ListaItensMenuVO listaItensMenuVO = ListaItensMenuVO.Factory.newInstance();
			listaItensMenuVO = controlSistema.listaItensMenu(menuParamPesquisaItensVO, ConstantesCRM.getCurrentUser(request.getSession()));

			ItemMenuExistentesVO[] itemMenuExistentes = new ItemMenuExistentesVO[listaItensMenuVO.getItensExistentes().getItemMenuExistentesVOArray().length];
			ItemMenuExistentesVO[] itemMenuRelacionados = new ItemMenuExistentesVO[listaItensMenuVO.getItensMenuRelacionados().getItemMenuExistentesVOArray().length];

			itemMenuExistentes = listaItensMenuVO.getItensExistentes().getItemMenuExistentesVOArray();
			itemMenuRelacionados = listaItensMenuVO.getItensMenuRelacionados().getItemMenuExistentesVOArray();

			lGrpForm.setItemMenuExistentesVO(itemMenuRelacionados);
			lGrpForm.setItemMenuRelacionadosVO(itemMenuExistentes);
			lGrpForm.setIdSubSistema(form.getIdSubSistema());
			lGrpForm.setIdSistema(form.getIdSistema());
			lGrpForm.setIdGrupo(form.getIdGrupo());
			lGrpForm.setExibeItem("sim");

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupoParamController:obtemItemMenu" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
			lGrpForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterGrupoParamController:obtemItemMenu" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relacionarGrupoItemMenu.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaItem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:salvaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			String lista[] = form.getArrayItensRelacionados();

			ItemMenuExistentesVO[] itemMenuExistentesVO = new ItemMenuExistentesVO[lista.length];

			for (int i = 0; i < form.getArrayItensRelacionados().length; i++) {
				itemMenuExistentesVO[i] = ItemMenuExistentesVO.Factory.newInstance();
				itemMenuExistentesVO[i].setIdItemMenu(lista[i]);
			}

			SalvarItensMenuRelacionadosVO salvarItensMenuRelacionadosVO = SalvarItensMenuRelacionadosVO.Factory.newInstance();
			salvarItensMenuRelacionadosVO.setIdGrupo(form.getIdGrupo());
			salvarItensMenuRelacionadosVO.setIdSubSistema(form.getIdSubSistema());
			salvarItensMenuRelacionadosVO.setIdUsuario(ConstantesCRM.SZERO);
			salvarItensMenuRelacionadosVO.addNewItemMenuExistentesVO();
			salvarItensMenuRelacionadosVO.setItemMenuExistentesVOArray(itemMenuExistentesVO);
			ListaItensMenuVO listaItensMenuVO = ListaItensMenuVO.Factory.newInstance();
			listaItensMenuVO = controlSistema.salvaItensRelacionados(salvarItensMenuRelacionadosVO, ConstantesCRM.getCurrentUser(request.getSession()));

			ItemMenuExistentesVO[] itemMenuExistentes = new ItemMenuExistentesVO[listaItensMenuVO.getItensExistentes().getItemMenuExistentesVOArray().length];
			ItemMenuExistentesVO[] itemMenuRelacionados = new ItemMenuExistentesVO[listaItensMenuVO.getItensMenuRelacionados().getItemMenuExistentesVOArray().length];

			itemMenuExistentes = listaItensMenuVO.getItensExistentes().getItemMenuExistentesVOArray();
			itemMenuRelacionados = listaItensMenuVO.getItensMenuRelacionados().getItemMenuExistentesVOArray();

			lGrpForm.setItemMenuExistentesVO(itemMenuRelacionados);
			lGrpForm.setItemMenuRelacionadosVO(itemMenuExistentes);
			lGrpForm.setIdSubSistema(form.getIdSubSistema());
			lGrpForm.setIdSistema(form.getIdSistema());
			lGrpForm.setIdGrupo(form.getIdGrupo());
			lGrpForm.setExibeItem("sim");
			lGrpForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupoParamController:salvaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
			lGrpForm.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterGrupoParamController:salvaItem" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarGrupo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeGrupo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:removeGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			GrupoUsuarioVO grupoUsuarioVO = GrupoUsuarioVO.Factory.newInstance();
			grupoUsuarioVO.setIdGrupo(request.getParameter("idGrupo"));
			// grupoUsuarioVO.setDsGrupo(request.getParameter("dsGrupoTemp"));

			controlGrupo.removeGrupo(grupoUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			lGrpForm.setArrayGruposVO(removeGrupoById(form.getIdGrupo(), lGrpForm.getArrayGruposVO()));

			if (lGrpForm.getArrayGruposVO() == null || lGrpForm.getArrayGruposVO().getGrupoUsuarioVOArray().length == 0) {
				lGrpForm.setArrayGrupoLength(ConstantesCRM.SVAZIO);
			} else {
				lGrpForm.setArrayGrupoLength(String.valueOf(lGrpForm.getArrayGruposVO().getGrupoUsuarioVOArray().length));
			}
			// Grava o resultado da pesquisa nos formularios.
			// lGrpForm.setArrayGruposVO(arrayGruposVO);

			// Grava o tamanho do array de grupos
			// lGrpForm.setArrayGrupoLength(Integer.toString(arrayGruposVO.getGrupoUsuarioVOArray().length));
			form.setDsGrupo(ConstantesCRM.SVAZIO);
			form.setIdGrupo(ConstantesCRM.SVAZIO);
			lGrpForm.setIdGrupo(ConstantesCRM.SVAZIO);
			lGrpForm.setDsGrupo(ConstantesCRM.SVAZIO);
			lGrpForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupoParamController:removeGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			// REGEX.
			if (e.getMessage().matches("^.*08E0001.*$")) {
				log.warn("ManterGrupoParamController:removeGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				request.setAttribute("msgError", e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

			log.error("ManterGrupoParamController:removeGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarGrupo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaItemPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:salvaItemPesquisa" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			// Garante que o id estará vazio, pois estou criando um grupo
			GrupoUsuarioVO grupoVO = GrupoUsuarioVO.Factory.newInstance();
			// atribui a descricao criada
			grupoVO.setDsGrupo(form.getDsGrupo());
			// grupoVO.setInCRI(form.getInCRI());
			grupoVO.setIdTipoGrupoSelecionado(form.getIdTipoGrupoSelecionado());

			// cria o novo grupo e retorna a lista de grupos atualizada
			GruposUsuarioVO gruposUsuarioVO = controlGrupo.cadastraGrupo(grupoVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// atualiza o array de grupos
			// Busca a lista de grupos existentes no sistema
			lGrpForm.setArrayGruposVO(gruposUsuarioVO);
			// Grava o tamanho do array de grupos
			lGrpForm.setArrayGrupoLength(Integer.toString(gruposUsuarioVO.getGrupoUsuarioVOArray().length));
			lGrpForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupoParamController:salvaItemPesquisa" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterGrupoParamController:salvaItemPesquisa" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarGrupo.jsp"
	 * @jpf:forward name="successretorno" path="listaGrupos.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaItemAlterado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:salvaItemAlterado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);

		try {
			GrupoUsuarioVO grupoUsuarioVO = GrupoUsuarioVO.Factory.newInstance();
			grupoUsuarioVO.setDsGrupo(form.getDsGrupo());
			grupoUsuarioVO.setIdGrupo(form.getIdAlt());
			// grupoUsuarioVO.setInCRI(form.getInCRI());
			grupoUsuarioVO.setIdTipoGrupoSelecionado(form.getIdTipoGrupoSelecionado());

			// edita o grupo e retorna a lista de grupos atualizada
			GruposUsuarioVO gruposUsuarioVO = controlGrupo.editaGrupo(grupoUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			// atualiza o array de grupos
			// Busca a lista de grupos existentes no sistema
			lGrpForm.setArrayGruposVO(gruposUsuarioVO);

			// Grava o tamanho do array de grupos
			lGrpForm.setArrayGrupoLength(String.valueOf(gruposUsuarioVO.getGrupoUsuarioVOArray().length));

			lGrpForm.setMsgError(ConstantesCRM.SSucesso);

			lGrpForm.setIdTipoGrupoPesquisa(ConstantesCRM.SVAZIO);
			lGrpForm.setIdTipoGrupoSelecionado(ConstantesCRM.SVAZIO);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupoParamController:salvaItemAlterado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("ManterGrupoParamController:salvaItemAlterado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("successretorno");
	}

	/* * * * * * * * * * * * * * * * * *
	 * INICIO Bloco de Funções Reaproveitáveis * * * * * * * * * * * * * * * *
	 */
	protected void reloadPerfis(RelacionarGrupoPerfilVO listaPerfis) throws Exception {

		// Trata o objeto de retorno e carrega as variáveis perfis do formulario
		// if (listaPerfis.getPerfisExistentes().getPerfilUsuarioVOArray().length > 0) {
		lGrpForm.setPerfisExistentesVO(listaPerfis.getPerfisExistentes().getPerfilUsuarioVOArray());
		// }
		// if (listaPerfis.getPerfisRelacionados().getPerfilUsuarioVOArray().length > 0) {
		lGrpForm.setPerfisRelacionadosVO(listaPerfis.getPerfisRelacionados().getPerfilUsuarioVOArray());
		// }
	}

	protected void reloadCanais(RelacionarGrupoCanalVO listaCanais) {

		// Trata o objeto de retorno e carrega as variáveis canais do formulario
		// if (listaCanais.getCanaisExistentes().getCanalUsuarioVOArray().length > 0) {
		lGrpForm.setCanaisExistentesVO(listaCanais.getCanaisExistentes().getCanalUsuarioVOArray());
		// }
		// if (listaCanais.getCanaisRelacionados().getCanalUsuarioVOArray().length > 0 ){
		lGrpForm.setCanaisRelacionadosVO(listaCanais.getCanaisRelacionados().getCanalUsuarioVOArray());
		// }

	}

	protected void loadSistemas(HttpServletRequest request) throws Exception {

		// Retorna a lista de canais relacionados e não relacionados
		SistemaManterUsuarioVO sistemaManterUsuarioVO = SistemaManterUsuarioVO.Factory.newInstance();

		// sem parametros para carga de sistema.
		ListaSistemaUsuarioVO listaSistemaUsuarioVO = ListaSistemaUsuarioVO.Factory.newInstance();
		listaSistemaUsuarioVO = controlSistema.listaSistemas(sistemaManterUsuarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

		SistemaManterUsuarioVO[] sistemaManterUsuarioVOArray = new SistemaManterUsuarioVO[listaSistemaUsuarioVO.getSistemaManterUsuarioVOArray().length];
		sistemaManterUsuarioVOArray = listaSistemaUsuarioVO.getSistemaManterUsuarioVOArray();

		lGrpForm.setListaSistemas(sistemaManterUsuarioVOArray);
		lGrpForm.setExibeItem("nao");
		lGrpForm.setExibeSubSistema("nao");
	}

	/* * * * * * * * * * * * * * * * * *
	 * FIM Bloco de Funções Reaproveitáveis * * * * * * * * * * * * * * * *
	 */

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IncluirAlterarGrupo.jsp"
	 */
	public ActionForward salvaEditGrupo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:salvaEditGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);

		GrupoUsuarioVO tipos = GrupoUsuarioVO.Factory.newInstance();
		tipos = controlGrupo.getTiposGrupo(ConstantesCRM.getCurrentUser(request.getSession()));
		lGrpForm.setTiposGrupo(tipos.getTiposGrupoArray());

		if (request.getParameter("operacao") != null && request.getParameter("operacao").equals("alterar")) {
			// Recupero o array de grupos do Form
			GrupoUsuarioVO[] arrayGruposVO = lGrpForm.getArrayGruposVO().getGrupoUsuarioVOArray();

			// Procura o grupo correspondente ao id recebido
			GrupoUsuarioVO grupoEdit = lGrpForm.getGrupoUsuarioVOById(form.idGrupo, arrayGruposVO);

			lGrpForm.setItemGrupo(grupoEdit);
			lGrpForm.getItemGrupo().setDsGrupo(grupoEdit.getDsGrupo());
			lGrpForm.setDsGrupo(grupoEdit.getDsGrupo());
			lGrpForm.setIdTipoGrupoSelecionado(grupoEdit.getIdTipoGrupoSelecionado());
			lGrpForm.setMsgError(ConstantesCRM.SSucesso);
		}else if (request.getParameter("operacao") != null && "alterar".equals(request.getParameter("operacao"))) {
			//TODO Limpar o objeto de tela
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:forward name="success" path="relacionarGrupoCanal.jsp"
	 * @jpf:action
	 */
	public ActionForward relacionarGrupoCanal(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:forward name="success" path="relacionarGrupoPerfil.jsp"
	 * @jpf:action
	 */
	public ActionForward relacionarGrupoPerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:forward name="success" path="relacionarGrupoItemMenu.jsp"
	 * @jpf:action
	 */
	public ActionForward relacionarGrupoItemMenu(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		lGrpForm.setIdSubSistema(ConstantesCRM.SVAZIO);
		lGrpForm.setIdSistema(ConstantesCRM.SVAZIO);
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarGrupo.jsp"
	 */
	public ActionForward limpaForm(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterGrupoParamController:limpaForm" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lGrpForm = new ListaGruposForm();
		try {
			GrupoUsuarioVO tipos = GrupoUsuarioVO.Factory.newInstance();
			tipos = controlGrupo.getTiposGrupo(ConstantesCRM.getCurrentUser(request.getSession()));
			lGrpForm.setTiposGrupo(tipos.getTiposGrupoArray());
			lGrpForm.setIdTipoGrupoPesquisa(ConstantesCRM.SVAZIO);
			lGrpForm.setIdTipoGrupoSelecionado(ConstantesCRM.SVAZIO);
			lGrpForm.setIdGrupo(ConstantesCRM.SVAZIO);
			lGrpForm.setDsGrupo(ConstantesCRM.SVAZIO);
			lGrpForm.setMsgError(ConstantesCRM.SVAZIO);
		} catch (Exception e) {
			log.error("ManterGrupoParamController:limpaForm" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			// Seta FormBean de Erro.
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
	 * @jpf:forward name="success" path="manutencaoSkill.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward listaSkill(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:listaSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			ManterSkillVO tipos = ManterSkillVO.Factory.newInstance();
			PesquisaSkillVO pesqSkill = PesquisaSkillVO.Factory.newInstance();

			pesqSkill.setIdGrupo(form.getIdGrupo());

			String idGrupo = form.getIdGrupo();
			if (idGrupo == null || idGrupo.length() <= 0) {
				idGrupo = (String) request.getSession().getAttribute("idGrupo");
				pesqSkill.setIdGrupo(idGrupo);
			}

			pesqSkill.setDsGrupoSkill(form.getDsGrupoSkill());
			tipos = controlGrupo.getListaSkill(ConstantesCRM.getCurrentUser(request.getSession()), pesqSkill);
			lGrpForm.setListaSkill(tipos.getSkillArray());
			lGrpForm.setManterSkillVo(tipos);
			if (lGrpForm.getListaSkill() == null || lGrpForm.getListaSkill().length == 0) {
				lGrpForm.setArraySkillLength(ConstantesCRM.SVAZIO);
			} else {
				lGrpForm.setArraySkillLength(String.valueOf(lGrpForm.getListaSkill().length));
			}
			lGrpForm.setDsGrupoSkill(form.getDsGrupoSkill());
			lGrpForm.setIdSkill(ConstantesCRM.SVAZIO);
			lGrpForm.setDsSkill(ConstantesCRM.SVAZIO);
			lGrpForm.setMsgError(ConstantesCRM.SVAZIO);
		} catch (Exception e) {
			log.error("ManterGrupoParamController:listaSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manutencaoSkill.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward manutencaoSkill(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterGrupoParamController:manutencaoSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);
		try {
			lGrpForm.setArraySkillLength("-1");
			lGrpForm.setIdSkill(ConstantesCRM.SVAZIO);
			lGrpForm.setDsSkill(ConstantesCRM.SVAZIO);
			lGrpForm.setDsGrupoSkill(ConstantesCRM.SVAZIO);
			lGrpForm.setMsgError(ConstantesCRM.SVAZIO);

		} catch (Exception e) {
			log.error("ManterGrupoParamController:manutencaoSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manutencaoSkill.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeSkill(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:removeSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);

		boolean flag = false;
		try {
			controlGrupo.removeSkill(ConstantesCRM.getCurrentUser(request.getSession()), form.getIdSkill());
			if (null == lGrpForm.getManterSkillVo()) {
				lGrpForm.setManterSkillVo(ManterSkillVO.Factory.newInstance());
			}
			if (lGrpForm.getManterSkillVo().getSkillArray().length == 0) {
				flag = true;
			}
			lGrpForm.setManterSkillVo(removeSkillById(form.getIdSkill(), lGrpForm.getManterSkillVo()));
			lGrpForm.setListaSkill(lGrpForm.getManterSkillVo().getSkillArray());

			if (flag) {
				lGrpForm.setArraySkillLength("-1");
				flag = false;
			} else if (lGrpForm.getListaSkill() == null || lGrpForm.getListaSkill().length == 0) {
				lGrpForm.setArraySkillLength(ConstantesCRM.SVAZIO);
			} else {
				lGrpForm.setArraySkillLength(String.valueOf(lGrpForm.getListaSkill().length));
			}
			lGrpForm.setIdSkill(ConstantesCRM.SVAZIO);
			lGrpForm.setDsSkill(ConstantesCRM.SVAZIO);
			lGrpForm.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupoParamController:removeSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			// REGEX.
			if (e.getMessage().matches("^.*08E0001.*$")) {
				log.warn("ManterGrupoParamController:removeSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				request.setAttribute("msgError", e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

			log.error("ManterGrupoParamController:removeSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manutencaoSkill.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward alteraSkill(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:alteraSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		ManterSkillVO manterSkillVO = ManterSkillVO.Factory.newInstance();

		try {

			lGrpForm.setMsgError(ConstantesCRM.SVAZIO);
			manterSkillVO.addNewSkill();
			manterSkillVO.getSkillArray(0).setDsSkill(form.getDsSkill());
			manterSkillVO.getSkillArray(0).setIdSkill(form.getIdSkill());
			manterSkillVO.getSkillArray(0).setIdGrupo(ConstantesCRM.SVAZIO);
			manterSkillVO = controlGrupo.inserirAlterarSkill(ConstantesCRM.getCurrentUser(request.getSession()), manterSkillVO);
			lGrpForm.setListaSkill(manterSkillVO.getSkillArray());

			if (lGrpForm.getListaSkill() == null || lGrpForm.getListaSkill().length == 0) {
				lGrpForm.setArraySkillLength(ConstantesCRM.SVAZIO);

			} else {
				lGrpForm.setArraySkillLength(String.valueOf(lGrpForm.getListaSkill().length));
			}

			lGrpForm.setMsgError(ConstantesCRM.SSucesso);
			lGrpForm.setIdSkill(ConstantesCRM.SVAZIO);
			lGrpForm.setDsSkill(ConstantesCRM.SVAZIO);

		} catch (TuxedoWarningException twe) {

			lGrpForm.setMsgError(twe.getXmlHeader().getStatusText());
			lGrpForm.setIdGrupo(form.getIdGrupo());
			lGrpForm.setArraySkillLength("-1");

		} catch (Exception e) {
			// REGEX.
			if (e.getMessage().matches("^.*08E0001.*$")) {
				log.warn("ManterGrupoParamController:alteraSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				lGrpForm.setMsgError(e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

			log.error("ManterGrupoParamController:alteraSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manutencaoSkill.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward insereSkill(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:insereSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		ManterSkillVO manterSkillVO = ManterSkillVO.Factory.newInstance();

		try {

			lGrpForm.setMsgError(ConstantesCRM.SVAZIO);
			manterSkillVO.addNewSkill();
			manterSkillVO.getSkillArray(0).setDsSkill(form.getDsSkill());
			manterSkillVO.getSkillArray(0).setIdSkill(ConstantesCRM.SVAZIO);
			manterSkillVO.getSkillArray(0).setIdGrupo(form.idGrupo);

			String idGrupo = form.getIdGrupo();

			if (idGrupo == null || idGrupo.length() <= 0) {
				idGrupo = (String) request.getSession().getAttribute("idGrupo");
				manterSkillVO.getSkillArray(0).setIdGrupo(idGrupo);
			}

			manterSkillVO = controlGrupo.inserirAlterarSkill(ConstantesCRM.getCurrentUser(request.getSession()), manterSkillVO);
			lGrpForm.setListaSkill(manterSkillVO.getSkillArray());

			if (lGrpForm.getListaSkill() == null || lGrpForm.getListaSkill().length == 0) {
				lGrpForm.setArraySkillLength(ConstantesCRM.SVAZIO);
			} else {
				lGrpForm.setArraySkillLength(String.valueOf(lGrpForm.getListaSkill().length));
			}

			lGrpForm.setMsgError(ConstantesCRM.SSucesso);
			lGrpForm.setIdSkill(ConstantesCRM.SVAZIO);
			lGrpForm.setDsSkill(ConstantesCRM.SVAZIO);

		} catch (TuxedoWarningException twe) {

			lGrpForm.setMsgError(twe.getXmlHeader().getStatusText());
			lGrpForm.setIdGrupo(form.getIdGrupo());
			lGrpForm.setArraySkillLength("-1");

		} catch (Exception e) {

			// REGEX.
			if (e.getMessage().matches("^.*08E0001.*$")) {
				log.warn("ManterGrupoParamController:insereSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				lGrpForm.setMsgError(e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

			log.error("ManterGrupoParamController:insereSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluiAlteraSkill.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluirAlterarSkill(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:incluirAlterarSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (form.getIdSkill() != null && !form.getIdSkill().equals(ConstantesCRM.SVAZIO)) {

			try {

				lGrpForm.setIdSkill(form.getIdSkill());
				ManterSkillVO tipos = ManterSkillVO.Factory.newInstance();
				PesquisaSkillVO pesqSkill = PesquisaSkillVO.Factory.newInstance();
				pesqSkill.setIdGrupo(form.getIdGrupo());

				String idGrupo = form.getIdGrupo();

				if (idGrupo == null || idGrupo.length() <= 0) {
					idGrupo = (String) request.getSession().getAttribute("idGrupo");
					pesqSkill.setIdGrupo(idGrupo);
				}

				pesqSkill.setDsGrupoSkill(form.getDsGrupoSkill());
				tipos = controlGrupo.getListaSkill(ConstantesCRM.getCurrentUser(request.getSession()), pesqSkill);
				lGrpForm.setDsSkill(alteraSkillById(lGrpForm.getIdSkill(), tipos));

			} catch (Exception e) {
				log.error("ManterGrupoParamController:incluirAlterarSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
				FormError formError = globalApp.buildFormError(e, request);
				formError.setTarget(ConstantesCRM.FRAMEAPP);
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}

		} else {
			lGrpForm.setIdSkill(ConstantesCRM.SVAZIO);
			lGrpForm.setDsSkill(ConstantesCRM.SVAZIO);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RegrasEncaminhamento.jsp"
	 */
	public ActionForward parametrizarSkill(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;

		log.debug("ManterGrupoParamController:parametrizarSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		lGrpForm.setMsgError(ConstantesCRM.SVAZIO);

		try {

			lGrpForm.setIdSkill(form.getIdSkill());
			ManterSkillVO tipos = ManterSkillVO.Factory.newInstance();
			PesquisaSkillVO pesqSkill = PesquisaSkillVO.Factory.newInstance();
			lGrpForm.setIdSkill(form.getIdSkill());
			pesqSkill.setIdGrupo(form.getIdGrupo());

			String idGrupo = form.getIdGrupo();

			if (idGrupo == null || idGrupo.length() <= 0) {
				idGrupo = (String) request.getSession().getAttribute("idGrupo");
				pesqSkill.setIdGrupo(idGrupo);
			}

			pesqSkill.setDsGrupoSkill(form.getDsGrupoSkill());
			tipos = controlGrupo.getListaSkill(ConstantesCRM.getCurrentUser(request.getSession()), pesqSkill);
			lGrpForm.setDsSkill(alteraSkillById(lGrpForm.getIdSkill(), tipos));

		} catch (Exception e) {
			log.error("ManterGrupoParamController:parametrizarSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Resultado.jsp"
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public ActionForward AplicarRegrasEncaminhamentoSkill(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ListaGruposForm form = (ListaGruposForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		log.debug("ManterGrupoParamController:AplicarRegrasEncaminhamentoSkill" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		// Definições
		HashMap retorno = new HashMap();

		// Para guardar a busca
		form.getArrayGruposVO().setGrupoUsuarioVOArray(lGrpForm.getArrayGruposVO().getGrupoUsuarioVOArray());
		form.getGrupoNovoVO().setDsGrupo(lGrpForm.getGrupoNovoVO().getDsGrupo());

		// Aponta para o formulário
		lGrpForm = form;

		// Monta os elementos para montagem no control
		retorno.put(ConstantesCRM.CT_GrupoVO, lGrpForm.getIdSkill());
		retorno.put(ConstantesCRM.CT_TipoLinhaVO, lGrpForm.getTipoLinhaAssociada());
		retorno.put(ConstantesCRM.CT_SegmentacaoVO, lGrpForm.getSegmentacaoAssociada());
		retorno.put(ConstantesCRM.CT_CarterizacaoVO, lGrpForm.getCarterizacaoAssociada());
		retorno.put(ConstantesCRM.CT_ProcedenciaVO, lGrpForm.getProcedenciaAssociada());
		retorno.put(ConstantesCRM.CT_TipoClienteVO, lGrpForm.getTipoClienteAssociada());
		retorno.put(ConstantesCRM.CT_TipoPessoaVO, lGrpForm.getTipoPessoaAssociada());
		retorno.put(ConstantesCRM.CT_TipoAberturaVO, lGrpForm.getTipoAberturaAssociada());
		retorno.put(ConstantesCRM.CT_CanalVO, lGrpForm.getCanalAssociado());
		retorno.put(ConstantesCRM.CT_RegionalVO, lGrpForm.getRegionalAssociada());

		// Invoca servico ENCAMINHIST para efeito de log
		controlGrupo.setHistoricoConfigVariaveis(user, (String) request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN), (String) request.getSession().getAttribute("idGrupo"));
		// Processa a criação/atualização
		controlGrupo.setRegrasEncaminhamento(ConstantesCRM.getCurrentUser(request.getSession()), retorno);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iObtemRegrasEncaminhamento.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward ObtemRegrasEncaminhamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ManterGrupoParamController:ObtemRegrasEncaminhamento" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		try {
			lGrpForm.setMsgError(ConstantesCRM.SVAZIO);
			// Obtem os elementos selecionados e disponíveis
			// Integer codigoGrupo = usuarioEncaminhamentoForm.getCodigoGrupo();
			lGrpForm.setRegrasEncaminhamentoVO(controlGrupo.getRegrasEncaminhamento(ConstantesCRM.getCurrentUser(request.getSession()), lGrpForm.getIdSkill()));

		} catch (TuxedoWarningException twe) {
			log.warn("ManterGrupoParamController:ObtemRegrasEncaminhamento" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError", twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			// REGEX.
			if (e.getMessage().matches("^.*08E0001.*$")) {
				log.warn("ManterGrupoParamController:ObtemRegrasEncaminhamento" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM = " + e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				request.setAttribute("msgError", e.getMessage().substring(e.getMessage().indexOf(']') + 1));
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ListaGruposForm extends ActionForm {

		private static final long serialVersionUID = 4649254529457121540L;

		private String dsGrupoSkill;
		private String dsSkill;
		private String idSkill;
		private ManterSkillVO manterSkillVo;
		private String arraySkillLength;
		private br.com.vivo.fo.usuario.vo.ManterSkillVODocument.ManterSkillVO.Skill[] listaSkill;
		private String idTipoGrupoPesquisa;
		private br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO.TiposGrupo[] tiposGrupo;
		private String idTipoGrupoSelecionado;
		private String dsTipoGrupoSelecionado;
		private String msgError = ConstantesCRM.SVAZIO;
		private String arrayGrupoLength;
		private String dsGrupo;
		private String idAlt;
		private String indicativoAlt;
		private String flagDescricao;
		private String[] arrayItensExistentes;
		private String[] arrayItensRelacionados;
		private ItemMenuExistentesVO[] itemMenuRelacionadosVO;
		private ItemMenuExistentesVO[] itemMenuExistentesVO;
		private ItemMenuExistentesVO[] itemMenuRelacionadosArray;
		private ItemMenuExistentesVO[] itemMenuExistentesArray;
		private String idSubSistema;
		private SubSistemaUsuarioVO[] listaSubSistemas;
		private String exibeSubSistema;
		private String exibeItem;
		private String idSistema;
		private SistemaManterUsuarioVO[] listaSistemas;

		private String idGrupo;
		private PerfilUsuarioVO[] perfisRelacionadosVO;
		private PerfilUsuarioVO[] perfisExistentesVO;
		private CanalUsuarioVO[] canaisRelacionadosVO;
		private CanalUsuarioVO[] canaisExistentesVO;
		private GrupoUsuarioVO itemGrupo;
		private GrupoUsuarioVO[] listaItemGrupo;
		private GrupoUsuarioVO grupoNovoVO;
		private GruposUsuarioVO arrayGruposVO;

		private String[] canaisExistentes;
		private String[] canaisRelacionados;
		private String[] perfisExistentes;
		private String[] perfisRelacionados;

		private String[] tipoClienteDisponivel;
		private String[] segmentacaoDisponivel;
		private String[] carterizacaoDisponivel;
		private String[] procedenciaDisponivel;
		private String[] tipoClienteAssociada;
		private String[] segmentacaoAssociada;
		private String[] carterizacaoAssociada;
		private String[] procedenciaAssociada;

		private String[] tipoLinhaDisponivel;
		private String[] tipoLinhaAssociada;
		private String[] tipoPessoaDisponivel;
		private String[] tipoPessoaAssociada;
		private String[] tipoAberturaDisponivel;
		private String[] tipoAberturaAssociada;
		private String[] canalAssociado;
		private String[] canalDisponivel;
		private String[] regionalAssociada;
		private String[] regionalDisponivel;

		private RegrasEncaminhamentoVO regrasEncaminhamentoVO = null;

		public ListaGruposForm() {

			// //////////////////////////////////
			// Bloco de Inicializacao - Begin //
			// //////////////////////////////////

			arrayGruposVO = GruposUsuarioVO.Factory.newInstance();
			arrayGrupoLength = "-1";

			grupoNovoVO = GrupoUsuarioVO.Factory.newInstance();
			itemGrupo = GrupoUsuarioVO.Factory.newInstance();

			canaisExistentesVO = new CanalUsuarioVO[0];
			canaisRelacionadosVO = new CanalUsuarioVO[0];
			perfisExistentesVO = new PerfilUsuarioVO[0];
			perfisRelacionadosVO = new PerfilUsuarioVO[0];

			canaisExistentes = new String[0];
			canaisRelacionados = new String[0];
			perfisExistentes = new String[0];
			perfisRelacionados = new String[0];

			dsGrupo = ConstantesCRM.SVAZIO;
			idAlt = ConstantesCRM.SVAZIO;
			indicativoAlt = ConstantesCRM.SVAZIO;
			flagDescricao = ConstantesCRM.SVAZIO;
			arrayItensExistentes = new String[0];
			arrayItensRelacionados = new String[0];
			itemMenuRelacionadosVO = new ItemMenuExistentesVO[0];
			itemMenuExistentesVO = new ItemMenuExistentesVO[0];
			itemMenuRelacionadosArray = new ItemMenuExistentesVO[0];
			itemMenuExistentesArray = new ItemMenuExistentesVO[0];
			idSubSistema = ConstantesCRM.SVAZIO;
			listaSubSistemas = new SubSistemaUsuarioVO[0];
			exibeSubSistema = ConstantesCRM.SVAZIO;
			listaItemGrupo = new GrupoUsuarioVO[0];
			exibeItem = ConstantesCRM.SVAZIO;
			idSistema = ConstantesCRM.SVAZIO;
			listaSistemas = new SistemaManterUsuarioVO[0];

			tipoLinhaDisponivel = new String[0];
			tipoLinhaAssociada = new String[0];
			segmentacaoDisponivel = new String[0];
			segmentacaoAssociada = new String[0];
			carterizacaoDisponivel = new String[0];
			carterizacaoAssociada = new String[0];
			procedenciaDisponivel = new String[0];
			procedenciaAssociada = new String[0];

			// Novas regras
			tipoClienteDisponivel = new String[0];
			tipoClienteAssociada = new String[0];
			tipoPessoaDisponivel = new String[0];
			tipoPessoaAssociada = new String[0];
			tipoAberturaDisponivel = new String[0];
			tipoAberturaAssociada = new String[0];
			canalAssociado = new String[0];
			canalDisponivel = new String[0];
			regionalAssociada = new String[0];
			regionalDisponivel = new String[0];

			TipoLinhaVO[] tipoLinha = new TipoLinhaVOImpl[0];
			SegmentacaoVO[] segmentacao = new SegmentacaoVOImpl[0];
			CarterizacaoVO[] carterizacao = new CarterizacaoVOImpl[0];
			ProcedenciaVO[] procedencia = new ProcedenciaVOImpl[0];

			TipoClienteVO[] tipoCliente = new TipoClienteVOImpl[0];
			TipoPessoaVO[] tipoPessoa = new TipoPessoaVOImpl[0];
			GrupoVO[] tipoAbertura = new GrupoVOImpl[0];
			CanalVO[] canal = new CanalVOImpl[0];

			this.regrasEncaminhamentoVO = RegrasEncaminhamentoVO.Factory.newInstance();
			this.regrasEncaminhamentoVO.setRegrasEncaminhamentoDisponivelVO(RegrasEncaminhamentoDisponivelVO.Factory.newInstance());
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setTipoLinhaVOArray(tipoLinha);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setSegmentacaoVOArray(segmentacao);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setCarterizacaoVOArray(carterizacao);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setProcedenciaVOArray(procedencia);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setTipoClienteVOArray(tipoCliente);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setTipoPessoaVOArray(tipoPessoa);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setGrupoVOArray(tipoAbertura);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setCanalVOArray(canal);

			this.regrasEncaminhamentoVO.setRegrasEncaminhamentoSelecionadoVO(RegrasEncaminhamentoSelecionadoVO.Factory.newInstance());
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setTipoLinhaVOArray(tipoLinha);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setSegmentacaoVOArray(segmentacao);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setCarterizacaoVOArray(carterizacao);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setProcedenciaVOArray(procedencia);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setTipoClienteVOArray(tipoCliente);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setTipoPessoaVOArray(tipoPessoa);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setGrupoVOArray(tipoAbertura);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setCanalVOArray(canal);
		}

		public RegrasEncaminhamentoVO getRegrasEncaminhamentoVO() {
			return this.regrasEncaminhamentoVO;
		}

		public void setRegrasEncaminhamentoVO(RegrasEncaminhamentoVO nRegrasEncaminhamentoVO) {
			this.regrasEncaminhamentoVO = nRegrasEncaminhamentoVO;
		}

		public String[] getCarterizacaoAssociada() {
			return carterizacaoAssociada;
		}

		public String[] getCarterizacaoDisponivel() {
			return carterizacaoDisponivel;
		}

		public String[] getProcedenciaAssociada() {
			return procedenciaAssociada;
		}

		public String[] getProcedenciaDisponivel() {
			return procedenciaDisponivel;
		}

		public String[] getSegmentacaoAssociada() {
			return segmentacaoAssociada;
		}

		public String[] getSegmentacaoDisponivel() {
			return segmentacaoDisponivel;
		}

		public String[] getTipoLinhaAssociada() {
			return tipoLinhaAssociada;
		}

		public String[] getTipoLinhaDisponivel() {
			return tipoLinhaDisponivel;
		}

		public void setCarterizacaoAssociada(String[] strings) {
			carterizacaoAssociada = strings;
		}

		public void setCarterizacaoDisponivel(String[] strings) {
			carterizacaoDisponivel = strings;
		}

		public void setProcedenciaAssociada(String[] strings) {
			procedenciaAssociada = strings;
		}

		public void setProcedenciaDisponivel(String[] strings) {
			procedenciaDisponivel = strings;
		}

		public void setSegmentacaoAssociada(String[] strings) {
			segmentacaoAssociada = strings;
		}

		public void setSegmentacaoDisponivel(String[] strings) {
			segmentacaoDisponivel = strings;
		}

		public void setTipoLinhaAssociada(String[] strings) {
			tipoLinhaAssociada = strings;
		}

		public void setTipoLinhaDisponivel(String[] strings) {
			tipoLinhaDisponivel = strings;
		}

		public void setTipoClienteDisponivel(String[] tipoClienteDisponivel) {
			this.tipoClienteDisponivel = tipoClienteDisponivel;
		}

		public String[] getTipoClienteDisponivel() {
			return this.tipoClienteDisponivel;
		}

		public void setTipoClienteAssociada(String[] tipoClienteAssociada) {
			this.tipoClienteAssociada = tipoClienteAssociada;
		}

		public String[] getTipoClienteAssociada() {
			return this.tipoClienteAssociada;
		}

		public void setTipoPessoaDisponivel(String[] tipoPessoaDisponivel) {
			this.tipoPessoaDisponivel = tipoPessoaDisponivel;
		}

		public String[] getTipoPessoaDisponivel() {
			return this.tipoPessoaDisponivel;
		}

		public void setTipoPessoaAssociada(String[] tipoPessoaAssociada) {
			this.tipoPessoaAssociada = tipoPessoaAssociada;
		}

		public String[] getTipoPessoaAssociada() {
			return this.tipoPessoaAssociada;
		}

		public void setTipoAberturaDisponivel(String[] tipoAberturaDisponivel) {
			this.tipoAberturaDisponivel = tipoAberturaDisponivel;
		}

		public String[] getTipoAberturaDisponivel() {
			return this.tipoAberturaDisponivel;
		}

		public void setTipoAberturaAssociada(String[] tipoAberturaAssociada) {
			this.tipoAberturaAssociada = tipoAberturaAssociada;
		}

		public String[] getTipoAberturaAssociada() {
			return this.tipoAberturaAssociada;
		}

		public void setCanalDisponivel(String[] canalDisponivel) {
			this.canalDisponivel = canalDisponivel;
		}

		public String[] getCanalDisponivel() {
			return this.canalDisponivel;
		}

		public void setCanalAssociado(String[] canalAssociado) {
			this.canalAssociado = canalAssociado;
		}

		public String[] getCanalAssociado() {
			return this.canalAssociado;
		}

		public void setRegionalAssociada(String[] regionalAssociada) {
			this.regionalAssociada = regionalAssociada;
		}

		public String[] getRegionalAssociada() {
			return this.regionalAssociada;
		}

		public void setRegionalDisponivel(String[] regionalDisponivel) {
			this.regionalDisponivel = regionalDisponivel;
		}

		public String[] getRegionalDisponivel() {
			return this.regionalDisponivel;
		}

		public void setArrayGruposVO(GruposUsuarioVO arrayGruposVO) {
			this.arrayGruposVO = arrayGruposVO;
		}

		public GruposUsuarioVO getArrayGruposVO() {
			return this.arrayGruposVO;
		}

		public void setGrupoNovoVO(GrupoUsuarioVO grupoNovoVO) {
			this.grupoNovoVO = grupoNovoVO;
		}

		public GrupoUsuarioVO getGrupoNovoVO() {
			return this.grupoNovoVO;
		}

		public void setItemGrupo(GrupoUsuarioVO itemGrupo) {
			this.itemGrupo = itemGrupo;
		}

		public GrupoUsuarioVO getItemGrupo() {
			return this.itemGrupo;
		}

		public GrupoUsuarioVO getGrupoUsuarioVOById(String id, GrupoUsuarioVO[] grupos) {
			for (int i = 0; i < grupos.length; i++) {
				if (grupos[i].getIdGrupo().equals(id)) {
					return grupos[i];
				}
			}
			return null;

		}

		public void setCanaisExistentes(String[] canaisExistentes) {
			this.canaisExistentes = canaisExistentes;
		}

		public String[] getCanaisExistentes() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.canaisExistentes == null || this.canaisExistentes.length == 0) {
				this.canaisExistentes = new String[1];
			}
			return this.canaisExistentes;
		}

		public void setCanaisRelacionados(String[] canaisRelacionados) {
			this.canaisRelacionados = canaisRelacionados;
		}

		public String[] getCanaisRelacionados() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.canaisRelacionados == null || this.canaisRelacionados.length == 0) {
				this.canaisRelacionados = new String[1];
			}
			return this.canaisRelacionados;
		}

		public void setPerfisExistentes(String[] perfisExistentes) {
			this.perfisExistentes = perfisExistentes;
		}

		public String[] getPerfisExistentes() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.perfisExistentes == null || this.perfisExistentes.length == 0) {
				this.perfisExistentes = new String[1];
			}
			return this.perfisExistentes;
		}

		public void setPerfisRelacionados(String[] perfisRelacionados) {
			this.perfisRelacionados = perfisRelacionados;
		}

		public String[] getPerfisRelacionados() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.perfisRelacionados == null || this.perfisRelacionados.length == 0) {
				this.perfisRelacionados = new String[1];
			}
			return this.perfisRelacionados;
		}

		public void setCanaisExistentesVO(CanalUsuarioVO[] canaisExistentesVO) {
			this.canaisExistentesVO = canaisExistentesVO;
		}

		public CanalUsuarioVO[] getCanaisExistentesVO() {
			if (this.canaisExistentesVO == null || this.canaisExistentesVO.length == 0) {
				this.canaisExistentesVO = new CanalUsuarioVO[1];
				this.canaisExistentesVO[0] = CanalUsuarioVO.Factory.newInstance();
			}
			return this.canaisExistentesVO;
		}

		public void setCanaisRelacionadosVO(CanalUsuarioVO[] canaisRelacionadosVO) {
			this.canaisRelacionadosVO = canaisRelacionadosVO;
		}

		public CanalUsuarioVO[] getCanaisRelacionadosVO() {
			if (this.canaisRelacionadosVO == null || this.canaisRelacionadosVO.length == 0) {
				this.canaisRelacionadosVO = new CanalUsuarioVO[1];
				this.canaisRelacionadosVO[0] = CanalUsuarioVO.Factory.newInstance();
			}
			return this.canaisRelacionadosVO;
		}

		public void setPerfisExistentesVO(PerfilUsuarioVO[] perfisExistentesVO) {
			this.perfisExistentesVO = perfisExistentesVO;
		}

		public PerfilUsuarioVO[] getPerfisExistentesVO() {
			if (this.perfisExistentesVO == null || this.perfisExistentesVO.length == 0) {
				this.perfisExistentesVO = new PerfilUsuarioVO[1];
				this.perfisExistentesVO[0] = PerfilUsuarioVO.Factory.newInstance();
			}
			return this.perfisExistentesVO;
		}

		public void setPerfisRelacionadosVO(PerfilUsuarioVO[] perfisRelacionadosVO) {
			this.perfisRelacionadosVO = perfisRelacionadosVO;
		}

		public PerfilUsuarioVO[] getPerfisRelacionadosVO() {
			if (this.perfisRelacionadosVO == null || this.perfisRelacionadosVO.length == 0) {
				this.perfisRelacionadosVO = new PerfilUsuarioVO[1];
				this.perfisRelacionadosVO[0] = PerfilUsuarioVO.Factory.newInstance();
			}
			return this.perfisRelacionadosVO;
		}

		public void setIdGrupo(String idGrupo) {
			this.idGrupo = idGrupo;
		}

		public String getIdGrupo() {
			return this.idGrupo;
		}

		public void setListaSistemas(SistemaManterUsuarioVO[] listaSistemas) {
			this.listaSistemas = listaSistemas;
		}

		public SistemaManterUsuarioVO[] getListaSistemas() {
			if (this.listaSistemas == null || this.listaSistemas.length == 0) {
				this.listaSistemas = new SistemaManterUsuarioVO[1];
				this.listaSistemas[0] = SistemaManterUsuarioVO.Factory.newInstance();
			}
			return this.listaSistemas;
		}

		public void setIdSistema(String idSistema) {
			this.idSistema = idSistema;
		}

		public String getIdSistema() {
			return this.idSistema;
		}

		public void setExibeItem(String exibeItem) {
			this.exibeItem = exibeItem;
		}

		public String getExibeItem() {
			return this.exibeItem;
		}

		public void setExibeSubSistema(String exibeSubSistema) {
			this.exibeSubSistema = exibeSubSistema;
		}

		public String getExibeSubSistema() {
			return this.exibeSubSistema;
		}

		public void setListaSubSistemas(SubSistemaUsuarioVO[] listaSubSistemas) {
			this.listaSubSistemas = listaSubSistemas;
		}

		public SubSistemaUsuarioVO[] getListaSubSistemas() {
			if (this.listaSubSistemas == null || this.listaSubSistemas.length == 0) {
				this.listaSubSistemas = new SubSistemaUsuarioVO[1];
				this.listaSubSistemas[0] = SubSistemaUsuarioVO.Factory.newInstance();
			}
			return this.listaSubSistemas;
		}

		public GrupoUsuarioVO[] getListaItemGrupo() {
			if (this.listaItemGrupo == null || this.listaItemGrupo.length == 0) {
				this.listaItemGrupo = new GrupoUsuarioVO[1];
				this.listaItemGrupo[0] = GrupoUsuarioVO.Factory.newInstance();
			}
			return this.listaItemGrupo;
		}

		public void setIdSubSistema(String idSubSistema) {
			this.idSubSistema = idSubSistema;
		}

		public String getIdSubSistema() {
			return this.idSubSistema;
		}

		public void setItemMenuExistentesArray(ItemMenuExistentesVO[] itemMenuExistentesArray) {
			this.itemMenuExistentesArray = itemMenuExistentesArray;
		}

		public ItemMenuExistentesVO[] getItemMenuExistentesArray() {
			if (this.itemMenuExistentesArray == null || this.itemMenuExistentesArray.length == 0) {
				this.itemMenuExistentesArray = new ItemMenuExistentesVO[1];
				this.itemMenuExistentesArray[0] = ItemMenuExistentesVO.Factory.newInstance();
			}
			return this.itemMenuExistentesArray;
		}

		public void setItemMenuRelacionadosArray(ItemMenuExistentesVO[] itemMenuRelacionadosArray) {
			this.itemMenuRelacionadosArray = itemMenuRelacionadosArray;
		}

		public ItemMenuExistentesVO[] getItemMenuRelacionadosArray() {
			if (this.itemMenuRelacionadosArray == null || this.itemMenuRelacionadosArray.length == 0) {
				this.itemMenuRelacionadosArray = new ItemMenuExistentesVO[1];
				this.itemMenuRelacionadosArray[0] = ItemMenuExistentesVO.Factory.newInstance();
			}
			return this.itemMenuRelacionadosArray;
		}

		public void setItemMenuExistentesVO(ItemMenuExistentesVO[] itemMenuExistentesVO) {
			this.itemMenuExistentesVO = itemMenuExistentesVO;
		}

		public ItemMenuExistentesVO[] getItemMenuExistentesVO() {
			if (this.itemMenuExistentesVO == null || this.itemMenuExistentesVO.length == 0) {
				this.itemMenuExistentesVO = new ItemMenuExistentesVO[1];
				this.itemMenuExistentesVO[0] = ItemMenuExistentesVO.Factory.newInstance();
			}
			return this.itemMenuExistentesVO;
		}

		public void setItemMenuRelacionadosVO(ItemMenuExistentesVO[] itemMenuRelacionadosVO) {
			this.itemMenuRelacionadosVO = itemMenuRelacionadosVO;
		}

		public ItemMenuExistentesVO[] getItemMenuRelacionadosVO() {
			if (this.itemMenuRelacionadosVO == null || this.itemMenuRelacionadosVO.length == 0) {
				this.itemMenuRelacionadosVO = new ItemMenuExistentesVO[1];
				this.itemMenuRelacionadosVO[0] = ItemMenuExistentesVO.Factory.newInstance();
			}
			return this.itemMenuRelacionadosVO;
		}

		public void setArrayItensRelacionados(String[] arrayItensRelacionados) {
			this.arrayItensRelacionados = arrayItensRelacionados;
		}

		public String[] getArrayItensRelacionados() {
			if (this.arrayItensRelacionados == null || this.arrayItensRelacionados.length == 0) {
				this.arrayItensRelacionados = new String[1];
			}
			return this.arrayItensRelacionados;
		}

		public void setArrayItensExistentes(String[] arrayItensExistentes) {
			this.arrayItensExistentes = arrayItensExistentes;
		}

		public String[] getArrayItensExistentes() {
			if (this.arrayItensExistentes == null || this.arrayItensExistentes.length == 0) {
				this.arrayItensExistentes = new String[1];
			}
			return this.arrayItensExistentes;
		}

		public void setFlagDescricao(String flagDescricao) {
			this.flagDescricao = flagDescricao;
		}

		public String getFlagDescricao() {
			return this.flagDescricao;
		}

		public void setIndicativoAlt(String indicativoAlt) {
			this.indicativoAlt = indicativoAlt;
		}

		public String getIndicativoAlt() {
			return this.indicativoAlt;
		}

		public void setIdAlt(String idAlt) {
			this.idAlt = idAlt;
		}

		public String getIdAlt() {
			return this.idAlt;
		}

		public void setDsGrupo(String dsGrupo) {
			this.dsGrupo = dsGrupo;
		}

		public String getDsGrupo() {
			return this.dsGrupo;
		}

		public void setArrayGrupoLength(String arrayGrupoLength) {
			this.arrayGrupoLength = arrayGrupoLength;
		}

		public String getArrayGrupoLength() {
			return this.arrayGrupoLength;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setDsTipoGrupoSelecionado(String dsTipoGrupoSelecionado) {
			this.dsTipoGrupoSelecionado = dsTipoGrupoSelecionado;
		}

		public String getDsTipoGrupoSelecionado() {
			return this.dsTipoGrupoSelecionado;
		}

		public void setIdTipoGrupoSelecionado(String idTipoGrupoSelecionado) {
			this.idTipoGrupoSelecionado = idTipoGrupoSelecionado;
		}

		public String getIdTipoGrupoSelecionado() {
			return this.idTipoGrupoSelecionado;
		}

		public void setTiposGrupo(br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO.TiposGrupo[] tiposGrupo) {
			this.tiposGrupo = tiposGrupo;
		}

		public br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO.TiposGrupo[] getTiposGrupo() {
			if (this.tiposGrupo == null) {
				this.tiposGrupo = new br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO.TiposGrupo[0];
			}

			return this.tiposGrupo;
		}

		public void setIdTipoGrupoPesquisa(String idTipoGrupoPesquisa) {
			this.idTipoGrupoPesquisa = idTipoGrupoPesquisa;
		}

		public String getIdTipoGrupoPesquisa() {
			return this.idTipoGrupoPesquisa;
		}

		public void setListaSkill(br.com.vivo.fo.usuario.vo.ManterSkillVODocument.ManterSkillVO.Skill[] listaSkill) {
			this.listaSkill = listaSkill;
		}

		public br.com.vivo.fo.usuario.vo.ManterSkillVODocument.ManterSkillVO.Skill[] getListaSkill() {
			if (this.listaSkill == null) {
				this.listaSkill = new br.com.vivo.fo.usuario.vo.ManterSkillVODocument.ManterSkillVO.Skill[0];
			}

			return this.listaSkill;
		}

		public void setArraySkillLength(String arraySkillLength) {
			this.arraySkillLength = arraySkillLength;
		}

		public String getArraySkillLength() {
			return this.arraySkillLength;
		}

		public void setManterSkillVo(ManterSkillVO manterSkillVo) {
			this.manterSkillVo = manterSkillVo;
		}

		public ManterSkillVO getManterSkillVo() {
			return this.manterSkillVo;
		}

		public void setIdSkill(String idSkill) {
			this.idSkill = idSkill;
		}

		public String getIdSkill() {
			return this.idSkill;
		}

		public void setDsSkill(String dsSkill) {
			this.dsSkill = dsSkill;
		}

		public String getDsSkill() {
			return this.dsSkill;
		}

		public void setDsGrupoSkill(String dsGrupoSkill) {
			this.dsGrupoSkill = dsGrupoSkill;
		}

		public String getDsGrupoSkill() {
			if (this.dsGrupoSkill == null) {
				this.dsGrupoSkill = ConstantesCRM.SVAZIO;
			}
			return this.dsGrupoSkill;
		}
	}

	public ListaGruposForm getListaGruposForm() {
		return (this.lGrpForm);
	}

	private GruposUsuarioVO removeGrupoById(String id, GruposUsuarioVO array) {
		if (id == null || array == null) {
			return null;
		}

		for (int i = 0; i < array.getGrupoUsuarioVOArray().length; i++) {
			if (id.equals(array.getGrupoUsuarioVOArray(i).getIdGrupo())) {
				array.removeGrupoUsuarioVO(i);
				break;
			}
		}
		return array;
	}

	private String alteraSkillById(String id, ManterSkillVO array) {
		String retorno = null;
		if (id == null || array == null) {
			return null;
		}

		for (int i = 0; i < array.getSkillArray().length; i++) {
			if (id.equals(array.getSkillArray(i).getIdSkill())) {
				retorno = array.getSkillArray(i).getDsSkill();
				break;
			}
		}
		return retorno;
	}

	private ManterSkillVO removeSkillById(String id, ManterSkillVO array) {
		if (id == null || array == null) {
			return null;
		}

		for (int i = 0; i < array.getSkillArray().length; i++) {
			if (id.equals(array.getSkillArray(i).getIdSkill())) {
				array.removeSkill(i);
				break;
			}
		}
		return array;
	}
}