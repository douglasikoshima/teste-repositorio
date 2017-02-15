package admsistemas.admArvoreSatisfacao;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmIndicadorSequenciaVODocument.AdmIndicadorSequenciaVO;
import br.com.vivo.fo.admsistemas.vo.AdmListaObjetosSatisfacaoVODocument.AdmListaObjetosSatisfacaoVO;
import br.com.vivo.fo.admsistemas.vo.AdmPerguntaVODocument.AdmPerguntaVO;
import br.com.vivo.fo.admsistemas.vo.AdmQuestionarioContainerVODocument.AdmQuestionarioContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmQuestionarioVODocument.AdmQuestionarioVO;
import br.com.vivo.fo.admsistemas.vo.AdmRespostaVODocument.AdmRespostaVO;
import br.com.vivo.fo.admsistemas.vo.AdmSatisfacaoContainerVODocument.AdmSatisfacaoContainerVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class admArvoreSatisfacaoController extends AbstractAction {

	private static final long serialVersionUID = -7937931465456991963L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.pesquisaSatisfacao.PesquisaSatisfacao controlSatisfacao;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.questionario.Questionario controlQuestionario;

	private FormQuestionario formQuestionario;
	private FormArvoreSatisfacao formArvoreSatisfacao;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("incluirAlterarQuestionario".equals(mapping.getParameter())) {
			return alteraQuestionario(mapping, form, request, response);			
		} else if ("incluirAlterarPergunta".equals(mapping.getParameter())) {
			return incluirAlterarPergunta(mapping, form, request, response);
		} else if ("incluirAlterarResposta".equals(mapping.getParameter())) {
			return incluirAlterarResposta(mapping, form, request, response);
		} else if ("incluirAlterarSalto".equals(mapping.getParameter())) {
			return incluirAlterarSalto(mapping, form, request, response);
		} else if ("gerenciaQuestionariosArvSatisfacao".equals(mapping.getParameter())) {
			return gerenciaQuestionariosArvSatisfacao(mapping, form, request, response);
		} else if ("removeQuestionario".equals(mapping.getParameter())) {
			return removeQuestionario(mapping, form, request, response);
		} else if ("salvarQuestionarioEditado".equals(mapping.getParameter())) {
			return salvarQuestionarioEditado(mapping, form, request, response);
		} else if ("adicionarQuestionario".equals(mapping.getParameter())) {
			return adicionarQuestionario(mapping, form, request, response);
		} else if ("editaParam".equals(mapping.getParameter())) {
			return editaParam(mapping, form, request, response);
		} else if ("salvaPerguntaExistente".equals(mapping.getParameter())) {
			return salvaPerguntaExistente(mapping, form, request, response);
		} else if ("salvaPerguntaNova".equals(mapping.getParameter())) {
			return salvaPerguntaNova(mapping, form, request, response);
		} else if ("salvaRespostaNova".equals(mapping.getParameter())) {
			return salvaRespostaNova(mapping, form, request, response);
		} else if ("excluirPergunta".equals(mapping.getParameter())) {
			return excluirPergunta(mapping, form, request, response);
		} else if ("excluirResposta".equals(mapping.getParameter())) {
			return excluirResposta(mapping, form, request, response);
		} else if ("excluirResposta".equals(mapping.getParameter())) {
			return excluirResposta(mapping, form, request, response);
		} else if ("salvaSalto".equals(mapping.getParameter())) {
			return salvaSalto(mapping, form, request, response);
		} else if ("salvaRespostaExistente".equals(mapping.getParameter())) {
			return salvaRespostaExistente(mapping, form, request, response);
		} else if ("perguntaRespostaUpDown".equals(mapping.getParameter())) {
			return perguntaRespostaUpDown(mapping, form, request, response);
		} else if ("alteraQuestionario".equals(mapping.getParameter())) {
			return alteraQuestionario(mapping, form, request, response);
		} else {
			return begin(mapping, form, request, response);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="gerenciaQuestionariosArvSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			if (formQuestionario == null) {
				formQuestionario = new FormQuestionario();
			}

			if (formQuestionario.getAdmQuestionarioContainerVO() != null && formQuestionario.getAdmQuestionarioContainerVO().getAdmQuestionarioVOArray() != null && formQuestionario.getAdmQuestionarioContainerVO().getAdmQuestionarioVOArray().length > 0) {
				formQuestionario.setAdmQuestionarioContainerVO(formQuestionario.getAdmQuestionarioContainerVO());
				formQuestionario.setListaQuestionarioLength(String.valueOf(formQuestionario.getAdmQuestionarioContainerVO().getAdmQuestionarioVOArray().length));

			} else {
				formQuestionario.setListaQuestionarioLength("-1");
				formQuestionario.setDsQuestionario(ConstantesCRM.SVAZIO);

			}

			formQuestionario.setMsgError(ConstantesCRM.SVAZIO);

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarPergunta.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluirAlterarPergunta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:incluirAlterarPergunta(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		FormArvoreSatisfacao form = (FormArvoreSatisfacao) formParam;
		formArvoreSatisfacao.setArrayListaObjetosArvoreSatisfacao(form.getIdTipoApresentacaoPerguntaFuncao());
		/*
		 * try { formArvoreSatisfacao = (FormArvoreSatisfacao)request.getSession().getAttribute("formArvoreSatisfacao");
		 * 
		 * }catch(Exception e) { //Seta FormBean de Erro. FormError formError = globalApp.buildFormError(e, request);
		 * request.setAttribute(ConstantesCRM.SFORMERROR,formError);
		 * request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this); return
		 * mapping.findForward(ConstantesCRM.SERROR); }
		 */

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarResposta.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluirAlterarResposta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:incluirAlterarResposta(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		/*
		 * try { formArvoreSatisfacao = form;
		 * 
		 * }catch(Exception e) { //Seta FormBean de Erro. FormError formError = globalApp.buildFormError(e, request);
		 * request.setAttribute(ConstantesCRM.SFORMERROR,formError);
		 * request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this); return
		 * mapping.findForward(ConstantesCRM.SERROR); }
		 */

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarSalto.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluirAlterarSalto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:incluirAlterarSalto(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormArvoreSatisfacao form = (FormArvoreSatisfacao) formParam;
			formArvoreSatisfacao.setIdPerguntaSalto(form.getIdPerguntaSaltoFuncao());
			formArvoreSatisfacao.setAtivoSaltoFuncao(form.getIdAtivoPerguntaSaltoFuncao());

			if (form.getIdRespostaFuncao() != null && !form.getIdRespostaFuncao().trim().equals(ConstantesCRM.SVAZIO)) {
				formArvoreSatisfacao.setIdRespostaFuncao(form.getIdRespostaFuncao());
			}

			AdmSatisfacaoContainerVO admSatisfacaoContainerVO = AdmSatisfacaoContainerVO.Factory.newInstance();
			admSatisfacaoContainerVO.setIdQuestionario(form.getIdQuestionario());
			AdmQuestionarioVO admQuestionarioVO = getQuestionarioById(form.getIdQuestionario(), formQuestionario.getAdmQuestionarioContainerVO().getAdmQuestionarioVOArray());
			admSatisfacaoContainerVO.setDsQuestionario(admQuestionarioVO.getDsQuestionario());

			formArvoreSatisfacao.setAdmSatisfacaoContainerVO(controlSatisfacao.listaPerguntaResposta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
			formArvoreSatisfacao.setAdmListaObjetosSatisfacaoVO(formArvoreSatisfacao.getAdmSatisfacaoContainerVO().getAdmListaObjetosSatisfacaoVOArray());

			if (form.getIdTipoPerguntaFuncao() != null) {
				for (int i = 0; i < formArvoreSatisfacao.getAdmSatisfacaoContainerVO().getAdmPerguntaVOArray().length; i++) {
					if (form.getIdTipoPerguntaFuncao().equals(formArvoreSatisfacao.getAdmSatisfacaoContainerVO().getAdmPerguntaVOArray(i).getIdPergunta())) {
						formArvoreSatisfacao.getAdmSatisfacaoContainerVO().removeAdmPerguntaVO(i);
						break;
					}
				}
			}
			formArvoreSatisfacao.setAdmPerguntaVO(formArvoreSatisfacao.getAdmSatisfacaoContainerVO().getAdmPerguntaVOArray());

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:incluirAlterarSalto" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="gerenciaQuestionariosArvSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward gerenciaQuestionariosArvSatisfacao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:gerenciaQuestionariosArvSatisfacao(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormQuestionario form = (FormQuestionario) formParam;
			String filtro = form.getDsQuestionario().trim();
			if (filtro == null) {
				filtro = ConstantesCRM.SVAZIO;
			}

			AdmQuestionarioVO admQuestionarioVO = AdmQuestionarioVO.Factory.newInstance();
			admQuestionarioVO.setDsQuestionario(filtro.trim());

			if (formQuestionario == null) {
				formQuestionario = new FormQuestionario();
			}

			formQuestionario.setMsgError(ConstantesCRM.SVAZIO);
			AdmQuestionarioContainerVO questionario = controlQuestionario.listaQuestionarioPesquisado(admQuestionarioVO, ConstantesCRM.getCurrentUser(request.getSession()));
			montaListaQuestionario(questionario);
			formQuestionario.setListaQuestionarioLength(String.valueOf(questionario.getAdmQuestionarioVOArray().length));

		} catch (TuxedoWarningException twe) {
			log.warn("admArvoreSatisfacaoController:gerenciaQuestionariosArvSatisfacao" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreSatisfacao = (FormArvoreSatisfacao) request.getSession().getAttribute("formArvoreSatisfacao");
			request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));
			formArvoreSatisfacao.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:gerenciaQuestionariosArvSatisfacao" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.getSession().setAttribute("formQuestionario", formQuestionario);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void montaListaQuestionario(AdmQuestionarioContainerVO questionario) {
		formQuestionario.setAdmQuestionarioContainerVO(questionario);
		formQuestionario.setAdmQuestionarioVO(questionario.getAdmQuestionarioVOArray());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="gerenciaQuestionariosArvSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removeQuestionario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("admArvoreSatisfacaoController:removeQuestionario(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			AdmQuestionarioVO admQuestionarioVO = AdmQuestionarioVO.Factory.newInstance();
			admQuestionarioVO.setIdQuestionario(request.getParameter("idQuestionario"));

			if (formQuestionario == null) {
				formQuestionario = new FormQuestionario();
			}

			formQuestionario.setMsgError(ConstantesCRM.SVAZIO);
			controlQuestionario.removerQuestionarioId(admQuestionarioVO, ConstantesCRM.getCurrentUser(request.getSession()));
			AdmQuestionarioContainerVO questionario = controlQuestionario.listaTodosQuestionarios(ConstantesCRM.getCurrentUser(request.getSession()));
			montaListaQuestionario(questionario);
			formQuestionario.setListaQuestionarioLength(String.valueOf(questionario.getAdmQuestionarioVOArray().length));
			formQuestionario.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("admArvoreSatisfacaoController:removeQuestionario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formQuestionario.setMsgError(twe.getXmlHeader().getStatusText());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:removeQuestionario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="gerenciaQuestionariosArvSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarQuestionarioEditado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:salvarQuestionarioEditado(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormQuestionario form = (FormQuestionario) formParam;
			AdmQuestionarioVO admQuestionarioVO = AdmQuestionarioVO.Factory.newInstance();
			if (formQuestionario == null) {
				formQuestionario = new FormQuestionario();
			}

			formQuestionario.setMsgError(ConstantesCRM.SVAZIO);
			if ((form.getDsQuestionario().trim().length() > 0)) {
				admQuestionarioVO.setIdQuestionario(form.getIdQuestionario());
				admQuestionarioVO.setDsQuestionario(form.getDsQuestionario().trim());
				controlQuestionario.salvarQuestionarioEditado(admQuestionarioVO, ConstantesCRM.getCurrentUser(request.getSession()));
			}

			AdmQuestionarioContainerVO questionario = controlQuestionario.listaTodosQuestionarios(ConstantesCRM.getCurrentUser(request.getSession()));
			montaListaQuestionario(questionario);
			formQuestionario.setListaQuestionarioLength(String.valueOf(questionario.getAdmQuestionarioVOArray().length));

			formQuestionario.setDsQuestionario(ConstantesCRM.SVAZIO);
			formQuestionario.setIdQuestionario(ConstantesCRM.SVAZIO);
			formQuestionario.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("admArvoreSatisfacaoController:salvarQuestionarioEditado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formQuestionario.setMsgError(twe.getXmlHeader().getStatusText());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:salvarQuestionarioEditado" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="gerenciaQuestionariosArvSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward adicionarQuestionario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:adicionarQuestionario(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		formQuestionario.setMsgError(ConstantesCRM.SVAZIO);

		try {
			FormQuestionario form = (FormQuestionario) formParam;
			AdmQuestionarioVO admQuestionarioVO = AdmQuestionarioVO.Factory.newInstance();
			admQuestionarioVO.setIdQuestionario(ConstantesCRM.SVAZIO);
			admQuestionarioVO.setDsQuestionario(form.getDsQuestionario().trim());
			if (formQuestionario == null) {
				formQuestionario = new FormQuestionario();
			}

			controlQuestionario.inserirQuestionario(admQuestionarioVO, ConstantesCRM.getCurrentUser(request.getSession()));

			AdmQuestionarioContainerVO questionario = controlQuestionario.listaTodosQuestionarios(ConstantesCRM.getCurrentUser(request.getSession()));
			montaListaQuestionario(questionario);
			formQuestionario.setListaQuestionarioLength(String.valueOf(questionario.getAdmQuestionarioVOArray().length));
			formQuestionario.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("admArvoreSatisfacaoController:adicionarQuestionario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formQuestionario.setMsgError(twe.getXmlHeader().getStatusText());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:adicionarQuestionario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ArvoreSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward editaParam(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:editaParam(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormQuestionario form = (FormQuestionario) formParam;
			if (formArvoreSatisfacao == null) {
				formArvoreSatisfacao = new FormArvoreSatisfacao();
			}

			formArvoreSatisfacao.setMsgError(ConstantesCRM.SVAZIO);

			AdmSatisfacaoContainerVO admSatisfacaoContainerVO = AdmSatisfacaoContainerVO.Factory.newInstance();
			admSatisfacaoContainerVO.setIdQuestionario(form.getIdQuestionario());
			AdmQuestionarioVO admQuestionarioVO = getQuestionarioById(form.getIdQuestionario(), formQuestionario.getAdmQuestionarioContainerVO().getAdmQuestionarioVOArray());
			admSatisfacaoContainerVO.setDsQuestionario(admQuestionarioVO.getDsQuestionario());

			formArvoreSatisfacao.setAdmSatisfacaoContainerVO(controlSatisfacao.listaPerguntaResposta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
			formArvoreSatisfacao.setDsQuestionarioFuncao(admQuestionarioVO.getDsQuestionario());
			formArvoreSatisfacao.getAdmSatisfacaoContainerVO().setDsQuestionario(admQuestionarioVO.getDsQuestionario());
			formArvoreSatisfacao.setIdQuestionario(form.getIdQuestionario());
			formArvoreSatisfacao.setAdmListaObjetosSatisfacaoVO(formArvoreSatisfacao.getAdmSatisfacaoContainerVO().getAdmListaObjetosSatisfacaoVOArray());
			formArvoreSatisfacao.setAdmPerguntaVO(formArvoreSatisfacao.getAdmSatisfacaoContainerVO().getAdmPerguntaVOArray());
			formArvoreSatisfacao.getAdmSatisfacaoContainerVO().setIdQuestionario(formArvoreSatisfacao.getAdmSatisfacaoContainerVO().getIdQuestionario());
			formArvoreSatisfacao.getAdmSatisfacaoContainerVO().setDsQuestionario(admQuestionarioVO.getDsQuestionario());

			request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));

		} catch (TuxedoWarningException twe) {
			log.warn("admArvoreSatisfacaoController:editaParam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreSatisfacao = (FormArvoreSatisfacao) request.getSession().getAttribute("formArvoreSatisfacao");
			request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));
			formArvoreSatisfacao.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:editaParam" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.getSession().setAttribute("formArvoreSatisfacao", formArvoreSatisfacao);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private String montaArvoreSatisfacao(AdmSatisfacaoContainerVO arvore) {

		StringBuffer script = new StringBuffer();

		script.append("if (document.getElementById) {var tree = new WebFXTree(\"").append(StringUtilStatic.escapaComillasJS(arvore.getDsQuestionario())).append("\",\"Javascript:capturaQuestionario('").append(arvore.getIdQuestionario()).append("', '").append(StringUtilStatic.escapaComillasJS2(arvore.getDsQuestionario())).append("');\",'classic');");

		StringBuffer pergunta = new StringBuffer();
		StringBuffer resposta = new StringBuffer();
		String body = ConstantesCRM.SVAZIO;

		if (arvore.getAdmPerguntaVOArray().length == 0) {
			// pergunta="";

			pergunta.append("var " + "tree" + String.valueOf(0)).append(" = new WebFXTreeItem(\"").append(StringUtilStatic.escapaComillasJS(arvore.getDsQuestionario())).append("\",\"Javascript:capturaParametrosPerguntaSatisfacao('").append(arvore.getIdQuestionario()).append("', '").append(StringUtilStatic.escapaComillasJS2(arvore.getDsQuestionario())).append("');\",'','/FrontOfficeWeb/resources/images/icon_perg_indic.gif','/FrontOfficeWeb/resources/images/icon_perg_indic.gif');");

		} else {
			for (int i = 0; i < arvore.getAdmPerguntaVOArray().length; i++) {
				pergunta.append("var ").append("tree").append(String.valueOf(i)).append(" = new WebFXTreeItem(\"").append("P").append(Integer.parseInt(arvore.getAdmPerguntaVOArray(i).getSqApresentacao()) + 1).append(" - ").append(StringUtilStatic.escapaComillasJS(arvore.getAdmPerguntaVOArray(i).getDsPergunta())).append("\",\"Javascript:capturaParametrosPerguntaSatisfacao('").append(arvore.getAdmPerguntaVOArray(i).getIdPergunta()).append("', '")
				.append(StringUtilStatic.escapaComillasJS2(arvore.getAdmPerguntaVOArray(i).getDsPergunta())).append("', '").append(StringUtilStatic.escapaComillasJS2(arvore.getAdmPerguntaVOArray(i).getDsScriptPergunta())).append("', '").append(arvore.getAdmPerguntaVOArray(i).getIdTipoApresentacaoPergunta()).append("', '").append(arvore.getAdmPerguntaVOArray(i).getIdPergunta()).append("', '").append(arvore.getAdmPerguntaVOArray(i).getInDisponibilidade()).append("', '")
				.append(arvore.getAdmPerguntaVOArray(i).getInEncerramento()).append("', '").append(arvore.getAdmPerguntaVOArray(i).getInObrigatoria()).append("', '").append(arvore.getAdmPerguntaVOArray(i).getSqApresentacao()).append("', '").append(arvore.getIdQuestionario()).append("', '").append(StringUtilStatic.escapaComillasJS2(arvore.getDsQuestionario().trim())).append("', '").append(arvore.getAdmPerguntaVOArray(i).getDsTipoApresentacaoPergunta());
				if (arvore.getAdmPerguntaVOArray(i).getInDisponibilidade() != null && arvore.getAdmPerguntaVOArray(i).getInDisponibilidade().trim().equals("1")) {
					pergunta.append("');\",'','/FrontOfficeWeb/resources/images/icon_perg_indic.gif','/FrontOfficeWeb/resources/images/icon_perg_indic.gif');");
				} else {
					pergunta.append("');\",'','/FrontOfficeWeb/resources/images/icon_perg_indic_desabilitada.gif','/FrontOfficeWeb/resources/images/icon_perg_indic_desabilitada.gif');");
				}

				body = body + String.valueOf(pergunta);

				if (arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray().length > 0) {
					for (int j = 0; j < arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray().length; j++) {
						if (arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getAdmSaltoVO() == null) {
							if (!arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getDsResposta().equals("")) {
								resposta.append("var " + "tree" + String.valueOf((j + 1) * 1000)).append(" = new WebFXTreeItem(\"").append(StringUtilStatic.escapaComillasJS(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getDsResposta())).append("\",\"Javascript:setIdTipoPerguntaFuncao('").append(arvore.getAdmPerguntaVOArray(i).getIdPergunta()).append("');").append("capturaParametrosRespostaSatisfacao('").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getIdResposta())
								.append("', '").append(StringUtilStatic.escapaComillasJS2(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getDsResposta())).append("', '").append(StringUtilStatic.escapaComillasJS2(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getDsScriptResposta())).append("', '").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getIdResposta()).append("', '")
								.append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getInDisponibilidade()).append("', '").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getInEncerramento()).append("', '").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getSqApresentacao()).append("', '").append(arvore.getIdQuestionario()).append("', '").append(StringUtilStatic.escapaComillasJS2(arvore.getDsQuestionario().trim()));
								if (arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getInDisponibilidade() != null && arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getInDisponibilidade().trim().equals("1")) {
									resposta.append("');\",'','/FrontOfficeWeb/resources/images/icon_resp_nrml.gif','/FrontOfficeWeb/resources/images/icon_resp_nrml.gif');");
								} else {
									resposta.append("');\",'','/FrontOfficeWeb/resources/images/icon_resp_desabilitado.gif','/FrontOfficeWeb/resources/images/icon_resp_desabilitado.gif');");
								}

								resposta.append("tree").append(String.valueOf(i)).append(".add(").append("tree").append(String.valueOf((j + 1) * 1000)).append(");\n");
							}
						} else // Salto Ativo
							if (arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getAdmSaltoVO().getAtivo().equals("1")) {
								AdmPerguntaVO admPerguntaVO = getPerguntaById(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getAdmSaltoVO().getIdPergunta(), arvore.getAdmPerguntaVOArray());

								resposta.append("var " + "tree" + String.valueOf((j + 1) * 1000)).append(" = new WebFXTreeItem(\"").append(StringUtilStatic.escapaComillasJS(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getDsResposta())).append(" - ").append("P").append(Integer.parseInt(admPerguntaVO.getSqApresentacao()) + 1).append("\",\"Javascript:setIdTipoPerguntaFuncao('").append(arvore.getAdmPerguntaVOArray(i).getIdPergunta()).append("');")
								.append("capturaParametrosRespostaSatisfacaoSalto('").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getIdResposta()).append("', '").append(StringUtilStatic.escapaComillasJS2(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getDsResposta())).append("', '").append(StringUtilStatic.escapaComillasJS2(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getDsScriptResposta())).append("', '")
								.append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getIdResposta()).append("', '").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getInDisponibilidade()).append("', '").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getInEncerramento()).append("', '").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getSqApresentacao()).append("', '")
								.append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getAdmSaltoVO().getAtivo()).append("', '").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getAdmSaltoVO().getIdPergunta()).append("', '").append(arvore.getIdQuestionario()).append("', '").append(StringUtilStatic.escapaComillasJS2(arvore.getDsQuestionario().trim()))
								.append("');\",'','/FrontOfficeWeb/resources/images/icon_resp_salto.gif','/FrontOfficeWeb/resources/images/icon_resp_salto.gif');").append("tree" + String.valueOf(i) + ".add(" + "tree" + String.valueOf((j + 1) * 1000) + ");\n");
							} else // Salto Inativo
								if (arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getAdmSaltoVO().getAtivo().equals("0")) {
									if (!arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getDsResposta().equals("")) {
										resposta.append("var " + "tree" + String.valueOf((j + 1) * 1000)).append(" = new WebFXTreeItem(\"").append(StringUtilStatic.escapaComillasJS(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getDsResposta())).append("\",\"Javascript:setIdTipoPerguntaFuncao('").append(arvore.getAdmPerguntaVOArray(i).getIdPergunta()).append("');").append("capturaParametrosRespostaSatisfacao('").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getIdResposta())
										.append("', '").append(StringUtilStatic.escapaComillasJS2(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getDsResposta())).append("', '").append(StringUtilStatic.escapaComillasJS2(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getDsScriptResposta())).append("', '").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getIdResposta()).append("', '")
										.append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getInDisponibilidade()).append("', '").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getInEncerramento()).append("', '").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getSqApresentacao()).append("', '").append(arvore.getIdQuestionario()).append("', '").append(StringUtilStatic.escapaComillasJS2(arvore.getDsQuestionario().trim())).append("', '")
										.append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getAdmSaltoVO().getAtivo()).append("', '").append(arvore.getAdmPerguntaVOArray(i).getAdmRespostaVOArray(j).getAdmSaltoVO().getIdPergunta()).append("');\",'','/FrontOfficeWeb/resources/images/icon_resp_salto_block.gif','/FrontOfficeWeb/resources/images/icon_resp_salto_block.gif');").append("tree").append(String.valueOf(i)).append(".add(").append("tree").append(String.valueOf((j + 1) * 1000))
										.append(");\n");
									}
								}

						body = body + String.valueOf(resposta);
						resposta = new StringBuffer();
					}
				}

				body = String.valueOf(body) + "tree" + ".add(" + "tree" + String.valueOf(i) + ");\n";
			}
		}

		resposta = null;
		pergunta = null;

		return String.valueOf(script) + body + " document.write(tree);}";

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ArvoreSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaPerguntaExistente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:salvaPerguntaExistente(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormArvoreSatisfacao form = (FormArvoreSatisfacao) formParam;
			AdmSatisfacaoContainerVO admSatisfacaoContainerVO = AdmSatisfacaoContainerVO.Factory.newInstance();

			AdmPerguntaVO admPerguntaVO = AdmPerguntaVO.Factory.newInstance();

			if (formArvoreSatisfacao == null) {
				formArvoreSatisfacao = new FormArvoreSatisfacao();
			}

			formArvoreSatisfacao.setMsgError(ConstantesCRM.SVAZIO);

			if ((form.getDsPerguntaFuncao().trim().length() > 0) && (form.getDsScriptPerguntaFuncao().trim().length() > 0)) {
				admPerguntaVO.setDsPergunta(form.getDsPerguntaFuncao().trim());
				admPerguntaVO.setDsScriptPergunta(form.getDsScriptPerguntaFuncao().trim());
				admPerguntaVO.setInEncerramento(form.getInEncerramentoPerguntaFuncao());
				admPerguntaVO.setInDisponibilidade(form.getInDisponibilidadePerguntaFuncao());
				admPerguntaVO.setInObrigatoria(form.getInObrigatoriaFuncao());
				admPerguntaVO.setIdTipoApresentacaoPergunta(form.getArrayListaObjetosArvoreSatisfacao());
				admPerguntaVO.setIdPergunta(form.getIdTipoPerguntaFuncao());
				admPerguntaVO.setSqApresentacao(ConstantesCRM.SZERO);

				admSatisfacaoContainerVO.insertNewAdmPerguntaVO(0);
				admSatisfacaoContainerVO.setAdmPerguntaVOArray(0, admPerguntaVO);
				admSatisfacaoContainerVO.setDsQuestionario(form.getDsQuestionarioFuncao().trim());
				admSatisfacaoContainerVO.setIdQuestionario(form.getIdQuestionarioFuncao());

				controlSatisfacao.salvaPerguntaExistente(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession()));

				formArvoreSatisfacao.setAdmSatisfacaoContainerVO(controlSatisfacao.listaPerguntaResposta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
				request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));

			}
			formArvoreSatisfacao.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("admArvoreSatisfacaoController:salvaPerguntaExistente" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));
			formArvoreSatisfacao.setMsgError(twe.getXmlHeader().getStatusText());

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:salvaPerguntaExistente" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ArvoreSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaPerguntaNova(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:salvaPerguntaNova(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormArvoreSatisfacao form = (FormArvoreSatisfacao) formParam;
			AdmSatisfacaoContainerVO admSatisfacaoContainerVO = AdmSatisfacaoContainerVO.Factory.newInstance();
			AdmPerguntaVO admPerguntaVO = AdmPerguntaVO.Factory.newInstance();
			if (formArvoreSatisfacao == null) {
				formArvoreSatisfacao = new FormArvoreSatisfacao();
			}

			formArvoreSatisfacao.setMsgError(ConstantesCRM.SVAZIO);

			if ((form.getDsPerguntaFuncao().trim().length() > 0) && (form.getDsScriptPerguntaFuncao().trim().length() > 0)) {
				admPerguntaVO.setDsPergunta(form.getDsPerguntaFuncao().trim());
				admPerguntaVO.setDsScriptPergunta(form.getDsScriptPerguntaFuncao().trim());
				admPerguntaVO.setInEncerramento(form.getInEncerramentoPerguntaFuncao().trim());
				admPerguntaVO.setInDisponibilidade(form.getInDisponibilidadePerguntaFuncao().trim());
				admPerguntaVO.setInObrigatoria(form.getInObrigatoriaFuncao());
				admPerguntaVO.setIdTipoApresentacaoPergunta(form.getArrayListaObjetosArvoreSatisfacao());
				admPerguntaVO.setSqApresentacao(ConstantesCRM.SZERO);

				admSatisfacaoContainerVO.setIdQuestionario(form.getIdQuestionarioFuncao());
				admSatisfacaoContainerVO.setDsQuestionario(form.getDsQuestionarioFuncao().trim());
				admSatisfacaoContainerVO.insertNewAdmPerguntaVO(0);
				admSatisfacaoContainerVO.setAdmPerguntaVOArray(0, admPerguntaVO);

				controlSatisfacao.salvaPergunta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession()));
				formArvoreSatisfacao.setAdmSatisfacaoContainerVO(controlSatisfacao.listaPerguntaResposta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
				request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));

			}
			formArvoreSatisfacao.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("admArvoreSatisfacaoController:salvaPerguntaNova" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreSatisfacao = (FormArvoreSatisfacao) request.getSession().getAttribute("formArvoreSatisfacao");
			request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));
			formArvoreSatisfacao.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:salvaPerguntaNova" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ArvoreSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaRespostaNova(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:salvaRespostaNova(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormArvoreSatisfacao form = (FormArvoreSatisfacao) formParam;
			AdmRespostaVO admRespostaVO = AdmRespostaVO.Factory.newInstance();
			AdmPerguntaVO admPerguntaVO = AdmPerguntaVO.Factory.newInstance();
			AdmSatisfacaoContainerVO admSatisfacaoContainerVO = AdmSatisfacaoContainerVO.Factory.newInstance();

			// formArvoreSatisfacao = new FormArvoreSatisfacao();
			formArvoreSatisfacao.setMsgError(ConstantesCRM.SVAZIO);
			if ((form.getDsRespostaFuncao().trim().length() > 0) && (form.getDsScriptRespostaFuncao().trim().length() > 0)) {
				admRespostaVO.setDsResposta(form.getDsRespostaFuncao().trim());
				admRespostaVO.setDsScriptResposta(form.getDsScriptRespostaFuncao().trim());
				admRespostaVO.setInDisponibilidade(form.getInDisponibilidadeRespostaFuncao());
				admRespostaVO.setInEncerramento(form.getInEncerramentoRespostaFuncao());

				admPerguntaVO.setDsPergunta(form.getDsPerguntaFuncao().trim());
				admPerguntaVO.setDsScriptPergunta(form.getDsScriptPerguntaFuncao().trim());
				admPerguntaVO.setDsTipoApresentacaoPergunta(formArvoreSatisfacao.getDsTipoApresentacaoPerguntaFuncao());
				admPerguntaVO.setIdTipoApresentacaoPergunta(form.getIdTipoApresentacaoPerguntaFuncao());
				admPerguntaVO.setIdPergunta(form.getIdTipoPerguntaFuncao());
				admPerguntaVO.setInDisponibilidade(form.getInDisponibilidadePerguntaFuncao().trim());
				admPerguntaVO.setInEncerramento(form.getInEncerramentoPerguntaFuncao().trim());
				admPerguntaVO.setInObrigatoria(form.getInObrigatoriaFuncao());
				admPerguntaVO.setSqApresentacao(form.getSqApresentacaoPerguntaFuncao().trim());
				admPerguntaVO.insertNewAdmRespostaVO(0);
				admPerguntaVO.setAdmRespostaVOArray(0, admRespostaVO);

				admSatisfacaoContainerVO.setIdQuestionario(form.getIdQuestionarioFuncao());
				admSatisfacaoContainerVO.setDsQuestionario(form.getDsQuestionarioFuncao().trim());
				admSatisfacaoContainerVO.insertNewAdmPerguntaVO(0);
				admSatisfacaoContainerVO.setAdmPerguntaVOArray(0, admPerguntaVO);

				controlSatisfacao.salvaResposta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession()));

				formArvoreSatisfacao.setAdmSatisfacaoContainerVO(controlSatisfacao.listaPerguntaResposta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
				request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));

			}
			formArvoreSatisfacao.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("admArvoreSatisfacaoController:salvaRespostaNova" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreSatisfacao = (FormArvoreSatisfacao) request.getSession().getAttribute("formArvoreSatisfacao");
			request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));
			formArvoreSatisfacao.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:salvaRespostaNova" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ArvoreSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward excluirPergunta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:excluirPergunta(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormArvoreSatisfacao form = (FormArvoreSatisfacao) formParam;
			AdmPerguntaVO admPerguntaVO = AdmPerguntaVO.Factory.newInstance();
			AdmSatisfacaoContainerVO admSatisfacaoContainerVO = AdmSatisfacaoContainerVO.Factory.newInstance();
			if (formArvoreSatisfacao == null) {
				formArvoreSatisfacao = new FormArvoreSatisfacao();
			}

			formArvoreSatisfacao.setMsgError(ConstantesCRM.SVAZIO);
			admPerguntaVO.setDsPergunta(form.getDsPerguntaFuncao().trim());
			admPerguntaVO.setDsScriptPergunta(form.getDsScriptPerguntaFuncao().trim());
			admPerguntaVO.setDsTipoApresentacaoPergunta(form.getDsTipoApresentacaoPerguntaFuncao().trim());
			admPerguntaVO.setIdTipoApresentacaoPergunta(form.getIdTipoApresentacaoPerguntaFuncao());
			admPerguntaVO.setIdPergunta(form.getIdTipoPerguntaFuncao());
			admPerguntaVO.setInDisponibilidade(form.getInDisponibilidadePerguntaFuncao().trim());
			admPerguntaVO.setInEncerramento(form.getInEncerramentoPerguntaFuncao().trim());
			admPerguntaVO.setInObrigatoria(form.getInObrigatoriaFuncao());
			admPerguntaVO.setSqApresentacao(form.getSqApresentacaoPerguntaFuncao().trim());

			admSatisfacaoContainerVO.setIdQuestionario(form.getIdQuestionarioFuncao());
			admSatisfacaoContainerVO.setDsQuestionario(form.getDsQuestionarioFuncao().trim());

			admSatisfacaoContainerVO.insertNewAdmPerguntaVO(0);
			admSatisfacaoContainerVO.setAdmPerguntaVOArray(0, admPerguntaVO);

			controlSatisfacao.excluirPergunta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession()));
			formArvoreSatisfacao.setAdmSatisfacaoContainerVO(controlSatisfacao.listaPerguntaResposta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
			request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));
			formArvoreSatisfacao.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("admArvoreSatisfacaoController:salvaRespostaNova" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreSatisfacao = (FormArvoreSatisfacao) request.getSession().getAttribute("formArvoreSatisfacao");
			request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));
			formArvoreSatisfacao.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:salvaRespostaNova" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ArvoreSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward excluirResposta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:excluirResposta(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormArvoreSatisfacao form = (FormArvoreSatisfacao) formParam;
			AdmRespostaVO admRespostaVO = AdmRespostaVO.Factory.newInstance();
			AdmPerguntaVO admPerguntaVO = AdmPerguntaVO.Factory.newInstance();
			AdmSatisfacaoContainerVO admSatisfacaoContainerVO = AdmSatisfacaoContainerVO.Factory.newInstance();
			if (formArvoreSatisfacao == null) {
				formArvoreSatisfacao = new FormArvoreSatisfacao();
			}

			formArvoreSatisfacao.setMsgError(ConstantesCRM.SVAZIO);
			admRespostaVO.setDsResposta(form.getDsRespostaFuncao().trim());
			admRespostaVO.setDsScriptResposta(form.getDsScriptRespostaFuncao().trim());
			admRespostaVO.setIdResposta(form.getIdRespostaFuncao());
			admRespostaVO.setInDisponibilidade(form.getInDisponibilidadeRespostaFuncao());
			admRespostaVO.setInEncerramento(form.getInEncerramentoRespostaFuncao());
			admRespostaVO.setSqApresentacao(form.getSqApresentacaoRespostaFuncao());

			if (form.getAtivoSaltoFuncao() != null) {
				admRespostaVO.addNewAdmSaltoVO().setAtivo(form.getAtivoSaltoFuncao());

				admRespostaVO.addNewAdmSaltoVO().setIdPergunta(form.getIdPerguntaSaltoFuncao());
			}

			admPerguntaVO.insertNewAdmRespostaVO(0);
			admPerguntaVO.setAdmRespostaVOArray(0, admRespostaVO);
			admSatisfacaoContainerVO.insertNewAdmPerguntaVO(0);
			admSatisfacaoContainerVO.setAdmPerguntaVOArray(0, admPerguntaVO);
			admSatisfacaoContainerVO.setIdQuestionario(form.getIdQuestionario());

			controlSatisfacao.excluirResposta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession()));
			formArvoreSatisfacao.setAdmSatisfacaoContainerVO(controlSatisfacao.listaPerguntaResposta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
			request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));
			formArvoreSatisfacao.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("admArvoreSatisfacaoController:excluirResposta" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreSatisfacao = (FormArvoreSatisfacao) request.getSession().getAttribute("formArvoreSatisfacao");
			request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));
			formArvoreSatisfacao.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:excluirResposta" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ArvoreSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaSalto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:salvaSalto(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		try {
			FormArvoreSatisfacao form = (FormArvoreSatisfacao) formParam;
			AdmRespostaVO admRespostaVO = AdmRespostaVO.Factory.newInstance();
			AdmPerguntaVO admPerguntaVO = AdmPerguntaVO.Factory.newInstance();
			AdmSatisfacaoContainerVO admSatisfacaoContainerVO = AdmSatisfacaoContainerVO.Factory.newInstance();
			if (formArvoreSatisfacao == null) {
				formArvoreSatisfacao = new FormArvoreSatisfacao();
			}

			formArvoreSatisfacao.setMsgError(ConstantesCRM.SVAZIO);

			if ((form.getDsRespostaFuncao().trim().length() > 0) && (form.getDsScriptRespostaFuncao().trim().length() > 0)) {
				//String[] idPergunta = new String[form.getArrayAdmPergunta().length];
				//idPergunta = form.getArrayAdmPergunta();

				admRespostaVO.setDsResposta(form.getDsRespostaFuncao().trim());
				admRespostaVO.setDsScriptResposta(form.getDsScriptRespostaFuncao().trim());
				admRespostaVO.setIdResposta(formArvoreSatisfacao.getIdRespostaFuncao());
				admRespostaVO.setInDisponibilidade(form.getInDisponibilidadeRespostaFuncao());
				admRespostaVO.setInEncerramento(form.getInEncerramentoRespostaFuncao());
				admRespostaVO.setSqApresentacao(form.getSqApresentacaoRespostaFuncao());

				admRespostaVO.addNewAdmSaltoVO();// .setIdPergunta(idPergunta[0]);
				admRespostaVO.getAdmSaltoVO().setAtivo(form.getAtivoSaltoFuncao());
				admRespostaVO.getAdmSaltoVO().setIdPergunta(form.getIdPerguntaSalto());
				// admRespostaVO.setIdResposta(form.getIdRespostaFuncao());

				admPerguntaVO.insertNewAdmRespostaVO(0);
				admPerguntaVO.setAdmRespostaVOArray(0, admRespostaVO);

				admSatisfacaoContainerVO.insertNewAdmPerguntaVO(0);
				admSatisfacaoContainerVO.setAdmPerguntaVOArray(0, admPerguntaVO);
				admSatisfacaoContainerVO.setIdQuestionario(form.getIdQuestionarioFuncao());
				admSatisfacaoContainerVO.setDsQuestionario(form.getDsQuestionarioFuncao().trim());

				controlSatisfacao.salvaSalto(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession()));
				formArvoreSatisfacao.setAdmSatisfacaoContainerVO(controlSatisfacao.listaPerguntaResposta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
				request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));
			}
			formArvoreSatisfacao.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("admArvoreSatisfacaoController:excluirResposta" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreSatisfacao = (FormArvoreSatisfacao) request.getSession().getAttribute("formArvoreSatisfacao");
			request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));
			formArvoreSatisfacao.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:excluirResposta" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
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
	 * @jpf:forward name="success" path="ArvoreSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvaRespostaExistente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("admArvoreSatisfacaoController:salvaRespostaExistente(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		FormArvoreSatisfacao form = (FormArvoreSatisfacao) formParam;
		AdmRespostaVO admRespostaVO = AdmRespostaVO.Factory.newInstance();
		AdmPerguntaVO admPerguntaVO = AdmPerguntaVO.Factory.newInstance();
		AdmSatisfacaoContainerVO admSatisfacaoContainerVO = AdmSatisfacaoContainerVO.Factory.newInstance();
		if (formArvoreSatisfacao == null) {
			formArvoreSatisfacao = new FormArvoreSatisfacao();
		}

		formArvoreSatisfacao.setMsgError(ConstantesCRM.SVAZIO);

		try {
			if ((form.getDsRespostaFuncao().trim().length() > 0) && (form.getDsScriptRespostaFuncao().trim().length() > 0)) {

				admRespostaVO.setDsResposta(form.getDsRespostaFuncao().trim());
				admRespostaVO.setDsScriptResposta(form.getDsScriptRespostaFuncao().trim());
				admRespostaVO.setIdResposta(form.getIdRespostaFuncao());
				admRespostaVO.setInDisponibilidade(form.getInDisponibilidadeRespostaFuncao());
				admRespostaVO.setInEncerramento(form.getInEncerramentoRespostaFuncao());
				admRespostaVO.setSqApresentacao(form.getSqApresentacaoRespostaFuncao());
				admPerguntaVO.insertNewAdmRespostaVO(0);
				admPerguntaVO.setAdmRespostaVOArray(0, admRespostaVO);

				// admPerguntaVO.setIdPergunta(form.getid)

				admSatisfacaoContainerVO.insertNewAdmPerguntaVO(0);
				admSatisfacaoContainerVO.setAdmPerguntaVOArray(0, admPerguntaVO);
				admSatisfacaoContainerVO.setIdQuestionario(form.getIdQuestionarioFuncao());
				admSatisfacaoContainerVO.setDsQuestionario(form.getDsQuestionarioFuncao().trim());

				controlSatisfacao.salvaRespostaExistente(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession()));

				formArvoreSatisfacao.setAdmSatisfacaoContainerVO(controlSatisfacao.listaPerguntaResposta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
				request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));

			}
			formArvoreSatisfacao.setMsgError(ConstantesCRM.SSucesso);
		} catch (TuxedoWarningException twe) {
			log.warn("admArvoreSatisfacaoController:salvaRespostaExistente" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreSatisfacao = (FormArvoreSatisfacao) request.getSession().getAttribute("formArvoreSatisfacao");
			request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));
			formArvoreSatisfacao.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:salvaRespostaExistente" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ArvoreSatisfacao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward perguntaRespostaUpDown(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("admArvoreSatisfacaoController:perguntaRespostaUpDown(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		FormArvoreSatisfacao form = new FormArvoreSatisfacao();

		try {
			form = (FormArvoreSatisfacao) formParam;
			AdmIndicadorSequenciaVO admIndicadorSequenciaVO = AdmIndicadorSequenciaVO.Factory.newInstance();
			if (formArvoreSatisfacao == null) {
				formArvoreSatisfacao = new FormArvoreSatisfacao();
			}

			formArvoreSatisfacao.setMsgError(ConstantesCRM.SVAZIO);

			if (form.getIndPerguntaResposta() != null && form.getIndPerguntaResposta().equalsIgnoreCase("pergunta")) {
				admIndicadorSequenciaVO.setIdPergunta(form.getIdTipoPerguntaFuncao());
				admIndicadorSequenciaVO.setInMoveUp(form.getIndUpDown());
				controlSatisfacao.perguntaUpDown(admIndicadorSequenciaVO, ConstantesCRM.getCurrentUser(request.getSession()));
			} else {
				admIndicadorSequenciaVO.setIdResposta(form.getIdRespostaFuncao());
				admIndicadorSequenciaVO.setInMoveUp(form.getIndUpDown());
				formArvoreSatisfacao = new FormArvoreSatisfacao();
				controlSatisfacao.respostaUpDown(admIndicadorSequenciaVO, ConstantesCRM.getCurrentUser(request.getSession()));
			}

		} catch (TuxedoWarningException twe) {
			log.warn("admArvoreSatisfacaoController:perguntaRespostaUpDown" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());

			formArvoreSatisfacao = (FormArvoreSatisfacao) request.getSession().getAttribute("formArvoreSatisfacao");
			request.setAttribute("arvore", montaArvoreSatisfacao(formArvoreSatisfacao.getAdmSatisfacaoContainerVO()));
			formArvoreSatisfacao.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:perguntaRespostaUpDown" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		AdmSatisfacaoContainerVO admSatisfacaoContainerVO = AdmSatisfacaoContainerVO.Factory.newInstance();
		admSatisfacaoContainerVO.setIdQuestionario(form.getIdQuestionarioFuncao());
		admSatisfacaoContainerVO = controlSatisfacao.listaPerguntaResposta(admSatisfacaoContainerVO, ConstantesCRM.getCurrentUser(request.getSession()));
		request.setAttribute("arvore", montaArvoreSatisfacao(admSatisfacaoContainerVO));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirAlterarQuestionario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward alteraQuestionario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		
		String operacao = (String) request.getParameter("operacao");
		log.debug("admArvoreSatisfacaoController:alteraQuestionario(" + ConstantesCRM.getCurrentUser(request.getSession()) + " : " + operacao + ")");

		formQuestionario.setMsgError(ConstantesCRM.SVAZIO);
		FormQuestionario form = (FormQuestionario) formParam;
		try {
			AdmQuestionarioVO admQuestionarioVO = null;
			if (!"incluir".equalsIgnoreCase(operacao)) {
				if (form.getIdQuestionario() != null) {
					admQuestionarioVO = getQuestionarioById(form.getIdQuestionario(), formQuestionario.getAdmQuestionarioContainerVO().getAdmQuestionarioVOArray());
				}
	
				formQuestionario.setIdQuestionario(form.getIdQuestionario());
				formQuestionario.setDsQuestionario(admQuestionarioVO.getDsQuestionario());
				
			} else {
				formQuestionario.setIdQuestionario(ConstantesCRM.SVAZIO);
				formQuestionario.setDsQuestionario(ConstantesCRM.SVAZIO);
			}
			formQuestionario.setMsgError(ConstantesCRM.SSucesso);

		} catch (Exception e) {
			log.error("admArvoreSatisfacaoController:alteraQuestionario" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	public static class FormQuestionario extends ActionForm {

		private static final long serialVersionUID = 165754225516565861L;

		private String msgError = ConstantesCRM.SVAZIO;
		private String listaQuestionarioLength;
		private String dsQuestionario;
		private String idQuestionario;
		private AdmQuestionarioContainerVO admQuestionarioContainerVO;
		private AdmQuestionarioVO[] admQuestionarioVO;
		private String dsTipoApresentacaoPergunta;
		private String idTipoApresentacaoPergunta;

		public void setIdTipoApresentacaoPergunta(String idTipoApresentacaoPergunta) {
			this.idTipoApresentacaoPergunta = idTipoApresentacaoPergunta;
		}

		public String getIdTipoApresentacaoPergunta() {
			return this.idTipoApresentacaoPergunta;
		}

		public void setDsTipoApresentacaoPergunta(String dsTipoApresentacaoPergunta) {
			this.dsTipoApresentacaoPergunta = dsTipoApresentacaoPergunta;
		}

		public String getDsTipoApresentacaoPergunta() {
			return this.dsTipoApresentacaoPergunta;
		}

		public void setAdmQuestionarioVO(AdmQuestionarioVO[] admQuestionarioVO) {
			this.admQuestionarioVO = admQuestionarioVO;
		}

		public AdmQuestionarioVO[] getAdmQuestionarioVO() {
			return this.admQuestionarioVO;
		}

		public void setAdmQuestionarioContainerVO(AdmQuestionarioContainerVO admQuestionarioContainerVO) {
			this.admQuestionarioContainerVO = admQuestionarioContainerVO;
		}

		public AdmQuestionarioContainerVO getAdmQuestionarioContainerVO() {
			return this.admQuestionarioContainerVO;
		}

		public void setIdQuestionario(String idQuestionario) {
			this.idQuestionario = idQuestionario;
		}

		public String getIdQuestionario() {
			return this.idQuestionario;
		}

		public void setDsQuestionario(String dsQuestionario) {
			this.dsQuestionario = dsQuestionario;
		}

		public String getDsQuestionario() {
			return this.dsQuestionario;
		}

		public void setListaQuestionarioLength(String listaQuestionarioLength) {
			this.listaQuestionarioLength = listaQuestionarioLength;
		}

		public String getListaQuestionarioLength() {
			return this.listaQuestionarioLength;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public FormQuestionario getFormQuestionario() {
		return this.formQuestionario;
	}

	public void setFormQuestionario(FormQuestionario form) {
		this.formQuestionario = form;
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class FormArvoreSatisfacao extends ActionForm {

		private static final long serialVersionUID = 7294896131620666757L;

		private String idPerguntaSalto;
		private String msgError = ConstantesCRM.SVAZIO;
		private String indUpDown;
		private String indPerguntaResposta;
		private String[] arrayAdmPergunta;
		private AdmPerguntaVO[] admPerguntaVO;
		private String idAtivoPerguntaSaltoFuncao;
		private String dsTipoApresentacaoPerguntaFuncao;
		private String arrayListaObjetosArvoreSatisfacao;
		private AdmListaObjetosSatisfacaoVO[] admListaObjetosSatisfacaoVO;
		private String ativoSaltoFuncao;
		private String idPerguntaSaltoFuncao;
		private String inDisponibilidadeRespostaFuncao;
		private String inEncerramentoRespostaFuncao;
		private String sqApresentacaoRespostaFuncao;
		private String dsScriptRespostaFuncao;
		private String dsRespostaFuncao;
		private String idRespostaFuncao;
		private String idTipoPerguntaFuncao;
		private String inObrigatoriaFuncao;
		private String inDisponibilidadePerguntaFuncao;
		private String inEncerramentoPerguntaFuncao;
		private String sqApresentacaoPerguntaFuncao;
		private String dsScriptPerguntaFuncao;
		private String dsPerguntaFuncao;
		private String idTipoApresentacaoPerguntaFuncao;
		private String dsQuestionarioFuncao;
		private String idQuestionarioFuncao;
		private AdmSatisfacaoContainerVO admSatisfacaoContainerVO;
		private String idQuestionario;

		public void setIdQuestionario(String idQuestionario) {
			this.idQuestionario = idQuestionario;
		}

		public String getIdQuestionario() {
			return this.idQuestionario;
		}

		public void setAdmSatisfacaoContainerVO(AdmSatisfacaoContainerVO admSatisfacaoContainerVO) {
			this.admSatisfacaoContainerVO = admSatisfacaoContainerVO;
		}

		public AdmSatisfacaoContainerVO getAdmSatisfacaoContainerVO() {
			return this.admSatisfacaoContainerVO;
		}

		public void setIdQuestionarioFuncao(String idQuestionarioFuncao) {
			this.idQuestionarioFuncao = idQuestionarioFuncao;
		}

		public String getIdQuestionarioFuncao() {
			return this.idQuestionarioFuncao;
		}

		public void setDsQuestionarioFuncao(String dsQuestionarioFuncao) {
			this.dsQuestionarioFuncao = dsQuestionarioFuncao;
		}

		public String getDsQuestionarioFuncao() {
			return this.dsQuestionarioFuncao;
		}

		public void setIdTipoApresentacaoPerguntaFuncao(String idTipoApresentacaoPerguntaFuncao) {
			this.idTipoApresentacaoPerguntaFuncao = idTipoApresentacaoPerguntaFuncao;
		}

		public String getIdTipoApresentacaoPerguntaFuncao() {
			return this.idTipoApresentacaoPerguntaFuncao;
		}

		public void setDsPerguntaFuncao(String dsPerguntaFuncao) {
			this.dsPerguntaFuncao = dsPerguntaFuncao;
		}

		public String getDsPerguntaFuncao() {
			return this.dsPerguntaFuncao;
		}

		public void setDsScriptPerguntaFuncao(String dsScriptPerguntaFuncao) {
			this.dsScriptPerguntaFuncao = dsScriptPerguntaFuncao;
		}

		public String getDsScriptPerguntaFuncao() {
			return this.dsScriptPerguntaFuncao;
		}

		public void setSqApresentacaoPerguntaFuncao(String sqApresentacaoPerguntaFuncao) {
			this.sqApresentacaoPerguntaFuncao = sqApresentacaoPerguntaFuncao;
		}

		public String getSqApresentacaoPerguntaFuncao() {
			return this.sqApresentacaoPerguntaFuncao;
		}

		public void setInEncerramentoPerguntaFuncao(String inEncerramentoPerguntaFuncao) {
			this.inEncerramentoPerguntaFuncao = inEncerramentoPerguntaFuncao;
		}

		public String getInEncerramentoPerguntaFuncao() {
			return this.inEncerramentoPerguntaFuncao;
		}

		public void setInDisponibilidadePerguntaFuncao(String inDisponibilidadePerguntaFuncao) {
			this.inDisponibilidadePerguntaFuncao = inDisponibilidadePerguntaFuncao;
		}

		public String getInDisponibilidadePerguntaFuncao() {
			return this.inDisponibilidadePerguntaFuncao;
		}

		public void setInObrigatoriaFuncao(String inObrigatoriaFuncao) {
			this.inObrigatoriaFuncao = inObrigatoriaFuncao;
		}

		public String getInObrigatoriaFuncao() {
			return this.inObrigatoriaFuncao;
		}

		public void setIdTipoPerguntaFuncao(String idTipoPerguntaFuncao) {
			this.idTipoPerguntaFuncao = idTipoPerguntaFuncao;
		}

		public String getIdTipoPerguntaFuncao() {
			return this.idTipoPerguntaFuncao;
		}

		public void setIdRespostaFuncao(String idRespostaFuncao) {
			this.idRespostaFuncao = idRespostaFuncao;
		}

		public String getIdRespostaFuncao() {
			return this.idRespostaFuncao;
		}

		public void setDsRespostaFuncao(String dsRespostaFuncao) {
			this.dsRespostaFuncao = dsRespostaFuncao;
		}

		public String getDsRespostaFuncao() {
			return this.dsRespostaFuncao;
		}

		public void setDsScriptRespostaFuncao(String dsScriptRespostaFuncao) {
			this.dsScriptRespostaFuncao = dsScriptRespostaFuncao;
		}

		public String getDsScriptRespostaFuncao() {
			return this.dsScriptRespostaFuncao;
		}

		public void setSqApresentacaoRespostaFuncao(String sqApresentacaoRespostaFuncao) {
			this.sqApresentacaoRespostaFuncao = sqApresentacaoRespostaFuncao;
		}

		public String getSqApresentacaoRespostaFuncao() {
			return this.sqApresentacaoRespostaFuncao;
		}

		public void setInEncerramentoRespostaFuncao(String inEncerramentoRespostaFuncao) {
			this.inEncerramentoRespostaFuncao = inEncerramentoRespostaFuncao;
		}

		public String getInEncerramentoRespostaFuncao() {
			return this.inEncerramentoRespostaFuncao;
		}

		public void setInDisponibilidadeRespostaFuncao(String inDisponibilidadeRespostaFuncao) {
			this.inDisponibilidadeRespostaFuncao = inDisponibilidadeRespostaFuncao;
		}

		public String getInDisponibilidadeRespostaFuncao() {
			return this.inDisponibilidadeRespostaFuncao;
		}

		public void setIdPerguntaSaltoFuncao(String idPerguntaSaltoFuncao) {
			this.idPerguntaSaltoFuncao = idPerguntaSaltoFuncao;
		}

		public String getIdPerguntaSaltoFuncao() {
			return this.idPerguntaSaltoFuncao;
		}

		public void setAtivoSaltoFuncao(String ativoSaltoFuncao) {
			this.ativoSaltoFuncao = ativoSaltoFuncao;
		}

		public String getAtivoSaltoFuncao() {
			return this.ativoSaltoFuncao;
		}

		public void setAdmListaObjetosSatisfacaoVO(AdmListaObjetosSatisfacaoVO[] admListaObjetosSatisfacaoVO) {
			this.admListaObjetosSatisfacaoVO = admListaObjetosSatisfacaoVO;
		}

		public AdmListaObjetosSatisfacaoVO[] getAdmListaObjetosSatisfacaoVO() {
			return this.admListaObjetosSatisfacaoVO;
		}

		public void setArrayListaObjetosArvoreSatisfacao(String arrayListaObjetosArvoreSatisfacao) {
			this.arrayListaObjetosArvoreSatisfacao = arrayListaObjetosArvoreSatisfacao;
		}

		public String getArrayListaObjetosArvoreSatisfacao() {
			// if(this.arrayListaObjetosArvoreSatisfacao == null || this.arrayListaObjetosArvoreSatisfacao.length == 0)
			// {
			// this.arrayListaObjetosArvoreSatisfacao = new String[1];
			// }

			return this.arrayListaObjetosArvoreSatisfacao;
		}

		public void setDsTipoApresentacaoPerguntaFuncao(String dsTipoApresentacaoPerguntaFuncao) {
			this.dsTipoApresentacaoPerguntaFuncao = dsTipoApresentacaoPerguntaFuncao;
		}

		public String getDsTipoApresentacaoPerguntaFuncao() {
			return this.dsTipoApresentacaoPerguntaFuncao;
		}

		public void setIdAtivoPerguntaSaltoFuncao(String idAtivoPerguntaSaltoFuncao) {
			this.idAtivoPerguntaSaltoFuncao = idAtivoPerguntaSaltoFuncao;
		}

		public String getIdAtivoPerguntaSaltoFuncao() {
			return this.idAtivoPerguntaSaltoFuncao;
		}

		public void setAdmPerguntaVO(AdmPerguntaVO[] admPerguntaVO) {
			this.admPerguntaVO = admPerguntaVO;
		}

		public AdmPerguntaVO[] getAdmPerguntaVO() {
			return this.admPerguntaVO;
		}

		public void setArrayAdmPergunta(String[] arrayAdmPergunta) {
			this.arrayAdmPergunta = arrayAdmPergunta;
		}

		public String[] getArrayAdmPergunta() {
			if (this.arrayAdmPergunta == null || this.arrayAdmPergunta.length == 0) {
				this.arrayAdmPergunta = new String[1];
			}

			return this.arrayAdmPergunta;
		}

		public void setIndPerguntaResposta(String indPerguntaResposta) {
			this.indPerguntaResposta = indPerguntaResposta;
		}

		public String getIndPerguntaResposta() {
			return this.indPerguntaResposta;
		}

		public void setIndUpDown(String indUpDown) {
			this.indUpDown = indUpDown;
		}

		public String getIndUpDown() {
			return this.indUpDown;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setIdPerguntaSalto(String idPerguntaSalto) {
			this.idPerguntaSalto = idPerguntaSalto;
		}

		public String getIdPerguntaSalto() {
			return this.idPerguntaSalto;
		}
	}

	public FormArvoreSatisfacao getFormArvoreSatisfacao() {
		return this.formArvoreSatisfacao;
	}

	public void setFormArvoreSatisfacao(FormArvoreSatisfacao form) {
		this.formArvoreSatisfacao = form;
	}

	public AdmQuestionarioVO getQuestionarioById(String id, AdmQuestionarioVO[] listaQuestionario) {

		for (int i = 0; i < listaQuestionario.length; i++) {
			if (listaQuestionario[i].getIdQuestionario().equals(id)) {
				return listaQuestionario[i];
			}
		}
		return null;
	}

	public AdmPerguntaVO getPerguntaById(String id, AdmPerguntaVO[] listaPergunta) {

		for (int i = 0; i < listaPergunta.length; i++) {
			if (listaPergunta[i].getIdPergunta().equals(id)) {
				return listaPergunta[i];
			}
		}
		return null;
	}

}