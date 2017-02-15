package VOLTAV.campanhas;

import java.io.BufferedOutputStream;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import weblogic.logging.NonCatalogLogger;
import br.com.vivo.fo.campanha.vo.CampanhaVODocument;
import br.com.vivo.fo.campanha.vo.CampanhaVODocument.CampanhaVO;
import br.com.vivo.fo.cliente.vo.AjaxErrorHandlerVODocument.AjaxErrorHandlerVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.campanhas.CampanhasFacade;
import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class CampanhasController extends AbstractAction {

	private static final long serialVersionUID = 5330360809339967332L;

	private CampanhasForm                     campanhasForm = null;
	protected global.Global                   globalApp;
	private String                            user = null;
	private static transient NonCatalogLogger log = new NonCatalogLogger(CampanhasController.class.getName());

	@EJB
	private CampanhasFacade campanhasFacade;

	CampanhaVO campanhaQuestionario = CampanhaVO.Factory.newInstance();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisaCampanhas".equals(mapping.getParameter())) {
			return pesquisaCampanhas(mapping, form, request, response);
		} else if ("excluirCampanha".equals(mapping.getParameter())) {
			return excluirCampanha(mapping, form, request, response);
		} else if ("beginCadastroAlteracaoCampanha".equals(mapping.getParameter())) {
			return beginCadastroAlteracaoCampanha(mapping, form, request, response);
		} else if ("salvarCadastroAlteracaoCampanha".equals(mapping.getParameter())) {
			return salvarCadastroAlteracaoCampanha(mapping, form, request, response);
		} else if ("salvarQuestionarioCampanha".equals(mapping.getParameter())) {
			return salvarQuestionarioCampanha(mapping, form, request, response);
		} else if ("callAlterarQuestionario".equals(mapping.getParameter())) {
			return callAlterarQuestionario(mapping, form, request, response);
		} else if ("populaComboCampanha".equals(mapping.getParameter())) {
			return populaComboCampanha(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * Operação responsável pelo envio de informações que preenchem os
	 * seguintes filtros de pesquisa: lista de campanhas, lista de DDD,
	 * lista de segmentações disponíveis e lista de tipos de linhas
	 * disponíveis.
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CampanhasForm form = (CampanhasForm)formParam;
		try {

			user = ConstantesCRM.getCurrentUser(request.getSession());

			CampanhaVO campanhaVO = CampanhaVO.Factory.newInstance();
			campanhaVO.setDsOperacao("BEGIN");
			campanhaVO = campanhasFacade.getCampanhaVO(user, "CAMPFILTRO", campanhaVO);
			campanhaVO = checkCampanhaVO(campanhaVO);

			getCampanhasForm().setCampanhaVO(campanhaVO);

			request.getSession().setAttribute("combo",campanhaVO.getCampanhaVOArray());

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("CampanhaController:uploadArquivo(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * Operação responsável pela obtenção de lista de campanhas que
	 * correspondam aos filtros solicitados pelo usuário. Esses filtros
	 * podem ser uma combinação de DDD, Segmentação e Tipo de Linha ou
	 * o ID da Campanha.
	 * @jpf:action
	 * @jpf:forward name="success" path="searchResults.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	 protected ActionForward pesquisaCampanhas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        CampanhasForm form = (CampanhasForm) formParam;
        try {
            user = ConstantesCRM.getCurrentUser(request.getSession());
            CampanhaVO campanhaVO = CampanhaVO.Factory.newInstance();
            if (ConstantesCRM.SONE.equals(form.getTpPessoa())) {
                campanhaVO.setTpPessoa(ConstantesCRM.SONE);
            } else if (ConstantesCRM.STWO.equals(form.getTpPessoa())) {
                campanhaVO.setTpPessoa(ConstantesCRM.STWO);
            } else {
                campanhaVO.setTpPessoa(ConstantesCRM.SZERO);
            }
            if (!form.getTpCampanha().equals(ConstantesCRM.SZERO)) {
                campanhaVO.setTpCampanha(form.getTpCampanha());
            }
            if (!ConstantesCRM.SVAZIO.equals(form.getIdCampanha()) && !ConstantesCRM.SZERO.equals(form.getIdCampanha())) {
                campanhaVO.setIdCampanha(Integer.parseInt(form.getIdCampanha()));
            } else {
                campanhaVO.addNewSelecionados();
                for (int i = 0; i < form.getSelecionadosDDD().length; i++) {
                    campanhaVO.getSelecionados().addNewDDDVO().setIdDDD(form.getSelecionadosDDD()[i]);
                }
                for (int i = 0; i < form.getSelecionadosTipoLinha().length; i++) {
                    campanhaVO.getSelecionados().addNewTipoLinhaVO().setId(form.getSelecionadosTipoLinha()[i]);
                }
                for (int i = 0; i < form.getSelecionadosSegmentacao().length; i++) {
                    campanhaVO.getSelecionados().addNewSegmentacaoVO().setIdSegmentacao(Integer.parseInt(form.getSelecionadosSegmentacao()[i]));
                }
            }
            campanhaVO = campanhasFacade.getCampanhaVO(user, "LISTACAMPAN", campanhaVO);
            campanhaVO = checkCampanhaVO(campanhaVO);
            getCampanhasForm().setCampanhaVO(campanhaVO);
            request.getSession().removeAttribute("combo");
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        } catch (Exception e) {
            log.error("CampanhasController:uploadArquivo(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, request);
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }

	/**
	 * Operação responsável pela exclusão de uma campanha.
	 * @jpf:action
	 * @jpf:forward name="success" path="searchResults.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward excluirCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CampanhasForm form = (CampanhasForm)formParam;

		AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();

		try {

			user = ConstantesCRM.getCurrentUser(request.getSession());
			CampanhaVO campanhaVO = CampanhaVO.Factory.newInstance();
			campanhaVO.setDsOperacao("EXCLUSAO");

			try {
				campanhaVO.setIdCampanha(Integer.parseInt(form.getIdCampanha()));
				campanhasFacade.setCampanhaVO(user, campanhaVO);

			} catch (Exception e) {
				ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
				ajaxErrorHandlerVO.setFriendlyMessage("Houve um erro durante exclusão da campanha.");
			}

		} catch (Exception e) {
			log.error("CampanhasController:excluirCampanha(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
		} finally {
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(ajaxErrorHandlerVO.xmlText().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
        }
		return null;
	}

	/**
	 * Operação responsável pela obtenção de dados de uma determinada
	 * campanha (Alteração) e para carregamento dos dados da tela
	 * de alteração/inclusão de Campanha Promocional.
	 * @jpf:action
	 * @jpf:forward name="success" path="cadastroAlteracaoCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward beginCadastroAlteracaoCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CampanhasForm form = (CampanhasForm)formParam;

		try {

			user = ConstantesCRM.getCurrentUser(request.getSession());
			CampanhaVO campanhaVO = CampanhaVO.Factory.newInstance();

			campanhaQuestionario.setTxtExplicativo(null);
			campanhaQuestionario.setDsRespostaCorreta(null);
			campanhaQuestionario.setDsPergunta(null);
			campanhaQuestionario.setDsRespostaIncorreta(null);

			campanhaVO.setTpCampanha(ConstantesCRM.SZERO);


			if (!ConstantesCRM.SVAZIO.equals(request.getParameter("idCampanha"))) {
				campanhaVO.setIdCampanha(Integer.parseInt(request.getParameter("idCampanha")));
				campanhaVO = campanhasFacade.getCampanhaVO(user, "DETALCAMPAN", campanhaVO);
				campanhaVO.setInAceite(ConstantesCRM.SONE.equals(campanhaVO.getInAceite())?"on":null);
				campanhaVO.setInExibeCupom(ConstantesCRM.SONE.equals(campanhaVO.getInExibeCupom())?"on":null);

				/* OS 898 */
				campanhaVO.setInPrecedente(ConstantesCRM.SONE.equals(campanhaVO.getInPrecedente())?"on":null);
				campanhaVO.setInExibePremio(ConstantesCRM.SONE.equals(campanhaVO.getInExibePremio())?"on":null);
				campanhaVO.setInExibeQuestionario(ConstantesCRM.SONE.equals(campanhaVO.getInExibeQuestionario())?"on":null);

				/* OS 1000 */
				//campanhaVO.setTpCampanha(ConstantesCRM.SONE.equals(campanhaVO.getTpCampanha())?"on":null);
				//campanhaVO.setCdPalito(ConstantesCRM.SONE.equals(form.getCampanhaVO().getCdPalito())?"on":"");
				//campanhaVO.setDsUrlAutenticador(ConstantesCRM.SONE.equals(form.getCampanhaVO().getDsUrlAutenticador())?"on":"");
				//campanhaVO.setDsUrlFinal(ConstantesCRM.SONE.equals(form.getCampanhaVO().getDsUrlFinal())?"on":"");

			} else {
				campanhaVO = campanhasFacade.getCampanhaVO(user, "CAMPFILTRO", campanhaVO);
			}

			campanhaVO = checkCampanhaVO(campanhaVO);
			getCampanhasForm().setCampanhaVO(campanhaVO);

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("CampanhasController:beginCadastroAlteracaoCampanha(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);

		}
	}

	/**
	 * Operação responsável pela gravação de dados de um cadastro ou
	 * alteração de Campanha.
	 * @jpf:action
	 * @jpf:forward name="success" path="cadastroAlteracaoCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
   protected ActionForward salvarCadastroAlteracaoCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        CampanhasForm form = (CampanhasForm) formParam;
        try {
            user = ConstantesCRM.getCurrentUser(request.getSession());
            String operacao = ConstantesCRM.SVAZIO;
            CampanhaVO campanhaVO = CampanhaVO.Factory.newInstance();
            campanhaVO = form.getCampanhaVO();
            if (!ConstantesCRM.SVAZIO.equals(String.valueOf(form.getCampanhaVO().getIdCampanha())) && !ConstantesCRM.SZERO.equals(String.valueOf(form.getCampanhaVO().getIdCampanha()))) {
                campanhaVO.setIdCampanha(form.getCampanhaVO().getIdCampanha());
                operacao = "Alteração";
                campanhaVO.setDsOperacao("ALTERACAO");
            } else {
                operacao = "Inclusão";
                campanhaVO.setDsOperacao("INCLUSAO");
            }
            if (form.getCampanhaVO().getTpPessoa().equals(ConstantesCRM.SONE)) {
                campanhaVO.setTpPessoa(ConstantesCRM.SONE);
            } else {
                campanhaVO.setTpPessoa(ConstantesCRM.STWO);
            }
            campanhaVO.setInAceite("on".equals(form.getCampanhaVO().getInAceite()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            campanhaVO.setInExibeCupom("on".equals(form.getCampanhaVO().getInExibeCupom()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            /* OS 898 */
            campanhaVO.setInPrecedente("on".equals(form.getCampanhaVO().getInPrecedente()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            campanhaVO.setInExibePremio("on".equals(form.getCampanhaVO().getInExibePremio()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            campanhaVO.setInExibeQuestionario("on".equals(form.getCampanhaVO().getInExibeQuestionario()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
            /* OS 1000 */
            // campanhaVO.setTpCampanha("on".equals(form.getCampanhaVO().getTpCampanha())?ConstantesCRM.SONE:ConstantesCRM.SZERO);
            // campanhaVO.setCdPalito(ConstantesCRM.SONE.equals(form.getCampanhaVO().getCdPalito())?"on":"");
            // campanhaVO.setDsUrlAutenticador(ConstantesCRM.SONE.equals(form.getCampanhaVO().getDsUrlAutenticador())?"on":"");
            // campanhaVO.setDsUrlFinal(ConstantesCRM.SONE.equals(form.getCampanhaVO().getDsUrlFinal())?"on":"");
            campanhaVO.addNewSelecionados();
            for (int i = 0; i < form.getSelecionadosDDD().length; i++) {
                campanhaVO.getSelecionados().addNewDDDVO().setIdDDD(form.getSelecionadosDDD()[i].split("\\|")[0]);
                campanhaVO.getSelecionados().getDDDVOArray(i).setDsDDD(form.getSelecionadosDDD()[i].split("\\|")[1]);
                // Verifica duplicidade
                for (int j = 0; j < getCampanhasForm().getCampanhaVO().getDisponiveis().getDDDVOArray().length; j++) {
                    if (form.getSelecionadosDDD()[i].split("\\|")[0].equals(getCampanhasForm().getCampanhaVO().getDisponiveis().getDDDVOArray(j).getIdDDD())) {
                        getCampanhasForm().getCampanhaVO().getDisponiveis().removeDDDVO(j);
                    }
                }
            }
            for (int i = 0; i < form.getSelecionadosTipoLinha().length; i++) {
                campanhaVO.getSelecionados().addNewTipoLinhaVO().setId(form.getSelecionadosTipoLinha()[i].split("\\|")[0]);
                campanhaVO.getSelecionados().getTipoLinhaVOArray(i).setDescricao(form.getSelecionadosTipoLinha()[i].split("\\|")[1]);
                // Verifica duplicidade
                for (int j = 0; j < getCampanhasForm().getCampanhaVO().getDisponiveis().getTipoLinhaVOArray().length; j++) {
                    if (form.getSelecionadosTipoLinha()[i].split("\\|")[0].equals(getCampanhasForm().getCampanhaVO().getDisponiveis().getTipoLinhaVOArray(j).getId())) {
                        getCampanhasForm().getCampanhaVO().getDisponiveis().removeTipoLinhaVO(j);
                    }
                }
            }
            for (int i = 0; i < form.getSelecionadosSegmentacao().length; i++) {
                campanhaVO.getSelecionados().addNewSegmentacaoVO().setIdSegmentacao(Integer.parseInt(form.getSelecionadosSegmentacao()[i].split("\\|")[0]));
                campanhaVO.getSelecionados().getSegmentacaoVOArray(i).setDescricao(form.getSelecionadosSegmentacao()[i].split("\\|")[1]);
                // Verifica duplicidade
                for (int j = 0; j < getCampanhasForm().getCampanhaVO().getDisponiveis().getSegmentacaoVOArray().length; j++) {
                    if (form.getSelecionadosSegmentacao()[i].split("\\|")[0].equals(String.valueOf(getCampanhasForm().getCampanhaVO().getDisponiveis().getSegmentacaoVOArray(j).getIdSegmentacao()))) {
                        getCampanhasForm().getCampanhaVO().getDisponiveis().removeSegmentacaoVO(j);
                    }
                }
            }
            if ("1".equals(form.getCampanhaVO().getInExibeQuestionario())) {
                if ((getCampanhasForm().getCampanhaVO().getDsPergunta() == null && operacao.equals("Alteração")) || (campanhaQuestionario.getDsPergunta() == null && operacao.equals("Inclusão"))) {
                    String msg = "Favor configurar o questionário associado à campanha através da opção Manter questionário";
                    request.setAttribute("msgErro", msg);
                    CampanhaVO original = getCampanhasForm().getCampanhaVO();
                    getCampanhasForm().setCampanhaVO(campanhaVO);
                    getCampanhasForm().getCampanhaVO().setInAceite(ConstantesCRM.SONE.equals(form.getCampanhaVO().getInAceite()) ? "on" : "");
                    getCampanhasForm().getCampanhaVO().setInExibeCupom(ConstantesCRM.SONE.equals(form.getCampanhaVO().getInExibeCupom()) ? "on" : "");
                    getCampanhasForm().getCampanhaVO().setAdmContatoVOArray(original.getAdmContatoVOArray());
                    getCampanhasForm().getCampanhaVO().setDisponiveis(original.getDisponiveis());
                    getCampanhasForm().getCampanhaVO().setSelecionados(campanhaVO.getSelecionados());
                    /* OS 898 */
                    getCampanhasForm().getCampanhaVO().setInPrecedente(ConstantesCRM.SONE.equals(form.getCampanhaVO().getInPrecedente()) ? "on" : "");
                    getCampanhasForm().getCampanhaVO().setInExibePremio(ConstantesCRM.SONE.equals(form.getCampanhaVO().getInExibePremio()) ? "on" : "");
                    getCampanhasForm().getCampanhaVO().setInExibeQuestionario(ConstantesCRM.SONE.equals(form.getCampanhaVO().getInExibeQuestionario()) ? "on" : "");
                    request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
                    return mapping.findForward(ConstantesCRM.SUCCESS);
                }
                if (campanhaQuestionario.getDsPergunta() != null) {
                    campanhaVO.setDsPergunta(campanhaQuestionario.getDsPergunta());
                    campanhaVO.setTxtExplicativo(campanhaQuestionario.getTxtExplicativo());
                    campanhaVO.setDsRespostaCorreta(campanhaQuestionario.getDsRespostaCorreta());
                    campanhaVO.setDsRespostaIncorreta(campanhaQuestionario.getDsRespostaIncorreta());
                }
            }
            try {
                campanhasFacade.setCampanhaVO(user, campanhaVO);
                if (operacao.equals("ALTERACAO")) {
                    request.setAttribute("msgStatus", "Dados alterados com sucesso!");
                } else {
                    request.setAttribute("msgStatus", "Dados inseridos com sucesso!");
                }
                // request.setAttribute("msgStatus", operacao + " de campanha realizada com sucesso!");
            } catch (Exception e) {
                if (e.getMessage() != null) {
                    String msg = e.getMessage().substring(e.getMessage().lastIndexOf("]") + 1, e.getMessage().length());
                    request.setAttribute("msgErro", msg);
                    CampanhaVO original = getCampanhasForm().getCampanhaVO();
                    getCampanhasForm().setCampanhaVO(campanhaVO);
                    getCampanhasForm().getCampanhaVO().setInAceite(ConstantesCRM.SONE.equals(form.getCampanhaVO().getInAceite()) ? "on" : "");
                    getCampanhasForm().getCampanhaVO().setInExibeCupom(ConstantesCRM.SONE.equals(form.getCampanhaVO().getInExibeCupom()) ? "on" : "");
                    getCampanhasForm().getCampanhaVO().setAdmContatoVOArray(original.getAdmContatoVOArray());
                    getCampanhasForm().getCampanhaVO().setDisponiveis(original.getDisponiveis());
                    getCampanhasForm().getCampanhaVO().setSelecionados(campanhaVO.getSelecionados());
                    /* OS 898 */
                    getCampanhasForm().getCampanhaVO().setInPrecedente(ConstantesCRM.SONE.equals(form.getCampanhaVO().getInPrecedente()) ? "on" : "");
                    getCampanhasForm().getCampanhaVO().setInExibePremio(ConstantesCRM.SONE.equals(form.getCampanhaVO().getInExibePremio()) ? "on" : "");
                    getCampanhasForm().getCampanhaVO().setInExibeQuestionario(ConstantesCRM.SONE.equals(form.getCampanhaVO().getInExibeQuestionario()) ? "on" : "");
                    /* OS 1000 */
                    // getCampanhasForm().getCampanhaVO().setTpCampanha(ConstantesCRM.SONE.equals(form.getCampanhaVO().getTpCampanha())?"on":"");
                    // getCampanhasForm().getCampanhaVO().setCdPalito(ConstantesCRM.SONE.equals(form.getCampanhaVO().getCdPalito())?"on":"");
                    // getCampanhasForm().getCampanhaVO().setDsUrlAutenticador(ConstantesCRM.SONE.equals(form.getCampanhaVO().getDsUrlAutenticador())?"on":"");
                    // getCampanhasForm().getCampanhaVO().setDsUrlFinal(ConstantesCRM.SONE.equals(form.getCampanhaVO().getDsUrlFinal())?"on":"");
                } else {
                    throw e;
                }
            }
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SUCCESS);
        } catch (Exception e) {
            log.error("CampanhasController:salvarCadastroAlteracaoCampanha(" + user + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, request);
            formError.setTarget(ConstantesCRM.FRAMEAPP);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
    }



	/**
	 * Operação responsável pela gravação de dados de um cadastro ou
	 * alteração de Campanha.
	 * @jpf:action
	 * @jpf:forward name="success" path="alterarQuestionario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward salvarQuestionarioCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){
		CampanhasForm form = (CampanhasForm)formParam;


		campanhaQuestionario.setDsPergunta(form.getCampanhaVO().getDsPergunta());
		campanhaQuestionario.setTxtExplicativo(form.getCampanhaVO().getTxtExplicativo());
		campanhaQuestionario.setDsRespostaCorreta(form.getCampanhaVO().getDsRespostaCorreta());
		campanhaQuestionario.setDsRespostaIncorreta(form.getCampanhaVO().getDsRespostaIncorreta());

		request.setAttribute("salvarQuestionario", "true");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}



	/**
	 * Operação responsável pela gravação de dados de um cadastro ou
	 * alteração de Campanha.
	 * @jpf:action
	 * @jpf:forward name="success" path="alterarQuestionario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward callAlterarQuestionario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)
	{
		CampanhasForm form = (CampanhasForm)formParam;

		if(campanhaQuestionario.getDsPergunta() != null || campanhaQuestionario.getTxtExplicativo() != null
				|| campanhaQuestionario.getDsRespostaCorreta() != null || campanhaQuestionario.getDsRespostaIncorreta() != null )
		{

			getCampanhasForm().getCampanhaVO().setDsPergunta(campanhaQuestionario.getDsPergunta());
			getCampanhasForm().getCampanhaVO().setDsRespostaCorreta(campanhaQuestionario.getDsRespostaCorreta());
			getCampanhasForm().getCampanhaVO().setDsRespostaIncorreta(campanhaQuestionario.getDsRespostaIncorreta());
			getCampanhasForm().getCampanhaVO().setTxtExplicativo(campanhaQuestionario.getTxtExplicativo());

		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}



	/**
	 * @jpf:action
	 * @jpf:forward name="success"  path="index.jsp"
	 */
    protected ActionForward populaComboCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        CampanhasForm form = (CampanhasForm) formParam;
        user = ConstantesCRM.getCurrentUser(request.getSession());
        CampanhaVO campanhaVO = CampanhaVO.Factory.newInstance();
        campanhaVO.setTpCampanha(form.getTpCampanha());
        try {
            campanhaVO = campanhasFacade.getCampanhaVO(user, "CAMPFILTRO", campanhaVO);
    		if (form.getTpPessoa().equals(ConstantesCRM.SVAZIO)) {
				getCampanhasForm().setTpPessoa(ConstantesCRM.SZERO);
			} else {
				getCampanhasForm().setTpPessoa(form.getTpPessoa());
			}	            
            getCampanhasForm().setTpCampanha(form.getTpCampanha());
            campanhaVO = checkCampanhaVO(campanhaVO);
            getCampanhasForm().setCampanhaVO(campanhaVO);
            // getCampanhasForm().setCampanhaVO(campanhaVO);
            // getCampanhasForm().setCampanhaCombo(campanhaVO);
            request.getSession().setAttribute("combo", campanhaVO.getCampanhaVOArray());
        } catch (Exception e) {
            log.error("CampanhasController:populaComboCampanha(" + user + ") - [" + e.getMessage() + "]", e);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }



	public static class CampanhasForm extends ActionForm {
		private String dsRespostaIncorreta;

		private String dsRespostaCorreta;

		private String dsPergunta;

		private String txtExplicativo;


		private CampanhaVO campanhaVO;
		private String     idCampanha;
		private String[]   disponiveisDDD;
		private String[]   selecionadosDDD;
		private String[]   disponiveisTipoLinha;
		private String[]   selecionadosTipoLinha;
		private String[]   disponiveisSegmentacao;
		private String[]   selecionadosSegmentacao;
		private String     tpCampanha;
		private String     tpPessoa;
		private String     cdLegado;
		private CampanhaVO campanhaCombo;
		
        public String getCdLegado() {
			return cdLegado;
		}

		public void setCdLegado(String cdLegado) {
			this.cdLegado = cdLegado;
		}		
		
        public String getTpPessoa() {
            return tpPessoa;
        }

        public void setTpPessoa(String tpPessoa) {
            this.tpPessoa = tpPessoa;
        }		

		public CampanhasForm() {
			this.campanhaVO = CampanhaVO.Factory.newInstance();
			this.campanhaVO.setDisponiveis(CampanhaVODocument.CampanhaVO.Disponiveis.Factory.newInstance());
			this.campanhaVO.setSelecionados(CampanhaVODocument.CampanhaVO.Selecionados.Factory.newInstance());

			this.campanhaCombo = CampanhaVO.Factory.newInstance();
			this.campanhaCombo.setDisponiveis(CampanhaVODocument.CampanhaVO.Disponiveis.Factory.newInstance());
			this.campanhaCombo.setSelecionados(CampanhaVODocument.CampanhaVO.Selecionados.Factory.newInstance());
		}

		public CampanhaVO getCampanhaCombo() {
			return this.campanhaCombo;
		}

		public void setCampanhaCombo(CampanhaVO campanhaCombo) {
			this.campanhaCombo = campanhaCombo;
		}

		public String getIdCampanha() {
			if (this.idCampanha == null) {
				this.idCampanha = ConstantesCRM.SVAZIO;
			}
			return this.idCampanha;
		}

		public void setIdCampanha(String idCampanha) {
			this.idCampanha = idCampanha;
		}

		public String[] getDisponiveisDDD() {
			return this.disponiveisDDD;
		}

		public void setDisponiveisDDD(String[] disponiveisDDD) {
			this.disponiveisDDD = disponiveisDDD;
		}

		public String[] getSelecionadosDDD() {
			if (this.selecionadosDDD == null) {
				this.selecionadosDDD = new String[0];
			}
			return this.selecionadosDDD;
		}

		public void setSelecionadosDDD(String[] selecionadosDDD) {
			this.selecionadosDDD = selecionadosDDD;
		}

		public String[] getDisponiveisTipoLinha() {
			return this.disponiveisTipoLinha;
		}

		public void setDisponiveisTipoLinha(String[] disponiveisTipoLinha) {
			this.disponiveisTipoLinha = disponiveisTipoLinha;
		}

		public String[] getSelecionadosTipoLinha() {
			if (this.selecionadosTipoLinha == null) {
				this.selecionadosTipoLinha = new String[0];
			}
			return this.selecionadosTipoLinha;
		}

		public void setSelecionadosTipoLinha(String[] selecionadosTipoLinha) {
			this.selecionadosTipoLinha = selecionadosTipoLinha;
		}

		public String[] getDisponiveisSegmentacao() {
			return this.disponiveisSegmentacao;
		}

		public void setDisponiveisSegmentacao(String[] disponiveisSegmentacao) {
			this.disponiveisSegmentacao = disponiveisSegmentacao;
		}

		public String[] getSelecionadosSegmentacao() {
			if (this.selecionadosSegmentacao == null) {
				this.selecionadosSegmentacao = new String[0];
			}
			return this.selecionadosSegmentacao;
		}

		public void setSelecionadosSegmentacao(String[] selecionadosSegmentacao) {
			this.selecionadosSegmentacao = selecionadosSegmentacao;
		}

		public CampanhaVO getCampanhaVO() {
			return this.campanhaVO;
		}

		public void setCampanhaVO(CampanhaVO campanhaVO) {
			this.campanhaVO = campanhaVO;
		}

		public void setTxtExplicativo(String txtExplicativo)
		{
			this.txtExplicativo = txtExplicativo;
		}

		public String getTxtExplicativo()
		{
			return this.txtExplicativo;
		}

		public void setDsPergunta(String dsPergunta)
		{
			this.dsPergunta = dsPergunta;
		}

		public String getDsPergunta()
		{
			return this.dsPergunta;
		}

		public void setDsRespostaCorreta(String dsRespostaCorreta)
		{
			this.dsRespostaCorreta = dsRespostaCorreta;
		}

		public String getDsRespostaCorreta()
		{
			return this.dsRespostaCorreta;
		}

		public void setDsRespostaIncorreta(String dsRespostaIncorreta)
		{
			this.dsRespostaIncorreta = dsRespostaIncorreta;
		}

		public String getDsRespostaIncorreta()
		{
			return this.dsRespostaIncorreta;
		}

		public String getTpCampanha() {
			return this.tpCampanha;
		}

		public void setTpCampanha(String tpCampanha) {
			this.tpCampanha = tpCampanha;
		}


	}

	public CampanhasForm getCampanhasForm() {
		if(this.campanhasForm == null) {
			this.campanhasForm = new CampanhasForm();
		}
		return this.campanhasForm;
	}

	private CampanhaVO checkCampanhaVO(CampanhaVO campanhaVO){
		if(campanhaVO.getSelecionados()==null){
			campanhaVO.addNewSelecionados();
		}
		return campanhaVO;
	}

}