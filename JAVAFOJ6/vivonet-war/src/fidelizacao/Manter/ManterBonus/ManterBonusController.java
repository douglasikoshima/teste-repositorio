package fidelizacao.Manter.ManterBonus;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.ctrls.fidelizacao.Fidelizacao;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.Filtro;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Selecionado;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.Manter;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterBonusController extends AbstractAction {

	@EJB
	public Fidelizacao fidelizacao;

	@EJB
	private TelaInicialFacade telaInicialFacade;

	protected global.Global globalApp = new global.Global();

	private BonusForm bonusForm;

	private transient Logger log = new Logger("Fidelizacao");

	private final String PARAMETRO_UNIDADE_OFERTA = "PARAMETRO_UNIDADE_OFERTA";

	/**
	 * Retorna formulário
	 * 
	 * @return ActionManterBonusForm
	 */
	public BonusForm getBonusForm() {
		if (bonusForm == null) {
			bonusForm = new BonusForm();
		}
		return this.bonusForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("incluirBegin".equals(mapping.getParameter())) {
			return incluirBegin(mapping, form, request, response);
		} else if ("alterarBegin".equals(mapping.getParameter())) {
			return alterarBegin(mapping, form, request, response);
		} else if ("incluir".equals(mapping.getParameter())) {
			return incluir(mapping, form, request, response);
		} else if ("alterar".equals(mapping.getParameter())) {
			return alterar(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("excluir".equals(mapping.getParameter())) {
			return excluir(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="manterBonus.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		getBonusForm().setIdRegional(ConstantesCRM.SVAZIO);
		getBonusForm().setIdTpCliente(ConstantesCRM.SVAZIO);
		getBonusForm().setIdTpLinha(ConstantesCRM.SVAZIO);
		getBonusForm().setIdTpBonus(ConstantesCRM.SVAZIO);
		getBonusForm().setIdSegmentacao(ConstantesCRM.SVAZIO);
		getBonusForm().setIdGrpPacote(ConstantesCRM.SVAZIO);
		getBonusForm().setTpOperacao(ConstantesCRM.SVAZIO);
		getBonusForm().setVlBonus(ConstantesCRM.SVAZIO);

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.addNewFiltro().addNewCombos().addNmSelect("REGIONAL");
		xml.getFiltro().getCombos().addNmSelect("TPCLIENTE");
		xml.getFiltro().getCombos().addNmSelect("TPBONUS");
		xml.getFiltro().getCombos().addNmSelect("TPLINHA");
		xml.getFiltro().getCombos().addNmSelect("SEGMENTACAO");
		xml.getFiltro().getCombos().addNmSelect("GRPPACOTE");

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);
		getBonusForm().setFidelizacaoVO(listas);
		for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
			if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPBONUS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaTpBonus(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("SEGMENTACAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaSegmentacao(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("GRPPACOTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaGrpPacote(listas.getListasVO().getListaArray(i).getDisponivel());
			}
		}
		listas.addNewTabelas().addNewBonus();
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="ManterBonusDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="successA" path="inclusaoAlteracao.jsp"
	 * @jpf:forward name="successU" path="inclusaoAlteracaoURA.jsp"
	 */
	protected ActionForward incluirBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		BonusForm form = (BonusForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String success = ConstantesCRM.SVAZIO;
		if (form.getTpOperacao() != null && !ConstantesCRM.SVAZIO.equals(form.getTpOperacao())) {
			if ("A".equals(form.getTpOperacao())) {
				success = "successA";
				getBonusForm().setTpOperacao("A");
			} else if ("U".equals(form.getTpOperacao())) {
				success = "successU";
				getBonusForm().setTpOperacao("U");
			}
		}
		if (!ConstantesCRM.SVAZIO.equals(success)) {
			getBonusForm().setIdBonus(ConstantesCRM.SVAZIO);
			getBonusForm().setIdTpLinha(ConstantesCRM.SVAZIO);
			getBonusForm().setIdTpBonus(ConstantesCRM.SVAZIO);
			getBonusForm().setNmBonus(ConstantesCRM.SVAZIO);
			getBonusForm().setVdBonus(ConstantesCRM.SVAZIO);
			getBonusForm().setIdTpServico(ConstantesCRM.SVAZIO);
			getBonusForm().setCdServico(ConstantesCRM.SVAZIO);
			getBonusForm().setIdGrpPacote(ConstantesCRM.SVAZIO);
			getBonusForm().setVlBonus(ConstantesCRM.SVAZIO);

			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.addNewFiltro().addNewCombos().addNmSelect("REGIONAL");
			xml.getFiltro().getCombos().addNmSelect("TPCLIENTE");
			xml.getFiltro().getCombos().addNmSelect("TPBONUS");
			xml.getFiltro().getCombos().addNmSelect("TPLINHA");
			xml.getFiltro().getCombos().addNmSelect("SEGMENTACAO");
			xml.getFiltro().getCombos().addNmSelect("TPSERVICO");
			xml.getFiltro().getCombos().addNmSelect("GRPPACOTE");

			FidelizacaoVO listas = fidelizacao.getListas(user, xml);
			getBonusForm().setFidelizacaoVO(listas);
			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getBonusForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
				} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getBonusForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());
				} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getBonusForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
				} else if ("TPSERVICO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getBonusForm().setListaServicos(listas.getListasVO().getListaArray(i).getDisponivel());
				} else if ("TPBONUS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getBonusForm().setListaTpBonus(listas.getListasVO().getListaArray(i).getDisponivel());
				} else if ("SEGMENTACAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getBonusForm().setListaSegmentacao(listas.getListasVO().getListaArray(i).getDisponivel());
				} else if ("GRPPACOTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getBonusForm().setListaGrpPacote(listas.getListasVO().getListaArray(i).getDisponivel());
				}
			}
			getBonusForm().setListaSelRegional(Selecionado.Factory.newInstance());
			getBonusForm().setListaSelClientes(Selecionado.Factory.newInstance());
			getBonusForm().setListaSelSegmentacao(Selecionado.Factory.newInstance());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(success);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="successA" path="inclusaoAlteracao.jsp"
	 * @jpf:forward name="successU" path="inclusaoAlteracaoURA.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward incluir(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String success = ConstantesCRM.SVAZIO;
		BonusForm form = (BonusForm) formParam;
		try {
			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.setNmProcesso("MANBONUS");
			xml.setTpProcesso("CAD");

			Manter manter = xml.addNewManter();
			manter.setTpLinha(form.getIdTpLinha());
			manter.setNmBonus(form.getNmBonus());
			manter.setTpServico(form.getIdTpServico());
			manter.setCdServico(form.getCdServico());
			manter.setVdBonus(form.getVdBonus());

			Lista lista = xml.addNewListasVO().addNewLista();
			lista.setNmSelect("REGIONAL");
			Selecionado selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelRegional().length; i++) {
				selecao.addNewIt().setId(form.getIdSelRegional()[i]);
			}

			lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("SEGMENTACAO");
			selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelSegmentacao().length; i++) {
				selecao.addNewIt().setId(form.getIdSelSegmentacao()[i]);
			}

			if (form.getTpOperacao() != null && !ConstantesCRM.SVAZIO.equals(form.getTpOperacao())) {
				if ("A".equals(form.getTpOperacao())) {
					incluirBonusAtd(xml, form, request);
					success = "successA";
				} else if ("U".equals(form.getTpOperacao())) {
					incluirBonusUra(xml, form, request);
					success = "successU";
				}
			}
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(success);
	}

	private void incluirBonusAtd(FidelizacaoVO xml, BonusForm form, HttpServletRequest request) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		xml.getManter().setTpBonus(form.getIdTpBonus());

		Lista lista = xml.getListasVO().addNewLista();
		lista.setNmSelect("TPCLIENTE");
		Selecionado selecao = lista.addNewSelecionado();
		for (int i = 0; i < form.getIdSelTpCliente().length; i++) {
			selecao.addNewIt().setId(form.getIdSelTpCliente()[i]);
		}
		getBonusForm().setTpOperacao("A");
		FidelizacaoVO conf = fidelizacao.setParam(user, xml);
		if (ConstantesCRM.SONE.equals(conf.getCodError())) {
			request.setAttribute("msgError", conf.getMsgError());
		} else {
			request.setAttribute("status", ConstantesCRM.SOK);
		}
	}

	private void incluirBonusUra(FidelizacaoVO xml, BonusForm form, HttpServletRequest request) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		getBonusForm().setTpOperacao("U");

		ParametroVO parametroVO = telaInicialFacade.getParametro(user, PARAMETRO_UNIDADE_OFERTA);

		xml.getManter().setTpBonus(parametroVO.getDsValorParametro());
		xml.getManter().setVlBonus(form.getVlBonus());
		xml.getManter().setIdGrpPacote(form.getIdGrpPacote());
		xml.getManter().setCdServico(form.getCdServico());
		xml.getManter().setTpServico(form.getIdTpServico());

		ManterBonusUraDAO daoURA = new ManterBonusUraDAO();
		FidelizacaoVO out = daoURA.setBonus(user, xml);
		request.setAttribute("status", ConstantesCRM.SOK);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="successA" path="inclusaoAlteracao.jsp"
	 * @jpf:forward name="successU" path="inclusaoAlteracaoURA.jsp"
	 */
	protected ActionForward alterarBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		BonusForm form = (BonusForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String success = ConstantesCRM.SVAZIO;
		if (form.getTpOperacao() != null && !ConstantesCRM.SVAZIO.equals(form.getTpOperacao())) {
			try {
				if ("A".equals(form.getTpOperacao())) {
					success = "successA";
					getBonusForm().setTpOperacao("A");
					alterarBeginAtd(user, form);
				} else if ("U".equals(form.getTpOperacao())) {
					success = "successU";
					getBonusForm().setTpOperacao("U");
					alterarBeginUra(user, Long.parseLong(form.getIdBonus()));
				}
			} catch (Exception e) {
				log.debug("[ManterBonusController.alterarBegin] Error: " + e.getMessage());
			}
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(success);
	}

	private void alterarBeginAtd(String user, BonusForm form) throws Exception {

		log.debug("[ManterBonusController.alterarBeginAtd]User: " + user);
		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("MANBONUS");
		xml.setTpProcesso("CON");
		xml.addNewManter().setIdCadastrado(form.getIdBonus());

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);
		getBonusForm().setFidelizacaoVO(listas);

		getBonusForm().setIdBonus(form.getIdBonus());
		getBonusForm().setIdTpLinha(listas.getManter().getTpLinha());
		getBonusForm().setIdTpBonus(listas.getManter().getTpBonus());
		getBonusForm().setNmBonus(listas.getManter().getNmBonus());
		getBonusForm().setVdBonus(listas.getManter().getVdBonus());
		getBonusForm().setIdTpServico(listas.getManter().getTpServico());
		getBonusForm().setCdServico(listas.getManter().getCdServico());

		for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
			if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
				getBonusForm().setListaSelRegional(listas.getListasVO().getListaArray(i).getSelecionado());
			} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());
				getBonusForm().setListaSelClientes(listas.getListasVO().getListaArray(i).getSelecionado());
			} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPSERVICO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaServicos(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPBONUS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaTpBonus(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("SEGMENTACAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaSegmentacao(listas.getListasVO().getListaArray(i).getDisponivel());
				getBonusForm().setListaSelSegmentacao(listas.getListasVO().getListaArray(i).getSelecionado());
			}
		}
		
		log.debug("[ManterBonusController.alterarBeginAtd]Fim: ");
	}

	private void alterarBeginUra(String user, long idBonus) throws Exception {
		ManterBonusUraDAO daoURA = new ManterBonusUraDAO();
		FidelizacaoVO out = daoURA.getBonus(user, idBonus);

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.addNewFiltro().addNewCombos().addNmSelect("REGIONAL");
		xml.getFiltro().getCombos().addNmSelect("TPLINHA");
		xml.getFiltro().getCombos().addNmSelect("SEGMENTACAO");
		xml.getFiltro().getCombos().addNmSelect("GRPPACOTE");
		xml.getFiltro().getCombos().addNmSelect("TPSERVICO");

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);

		getBonusForm().setFidelizacaoVO(out);

		for (int i = 0; i < out.getListasVO().getListaArray().length; i++) {
			if ("REGIONAL".equals(out.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaSelRegional(out.getListasVO().getListaArray(i).getSelecionado());
			} else if ("SEGMENTACAO".equals(out.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaSelSegmentacao(out.getListasVO().getListaArray(i).getSelecionado());
			}
		}

		for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
			if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPSERVICO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaServicos(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPBONUS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaTpBonus(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("SEGMENTACAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getBonusForm().setListaSegmentacao(listas.getListasVO().getListaArray(i).getDisponivel());
			}
		}

		// Remove os elementos da lista de Disponiveis que já existirem na lista de Selecionados
		Selecionado selRegional = getBonusForm().getListaSelRegional();
		Disponivel disRegional = getBonusForm().getListaRegional();

		for (int i = 0; i < selRegional.getItArray().length; i++) {
			for (int j = 0; j < disRegional.getItArray().length; j++) {
				if (selRegional.getItArray(i).getId().equals(disRegional.getItArray(j).getId())) {
					disRegional.removeIt(j);
					break;
				}
			}
		}

		Selecionado selSegmentacao = getBonusForm().getListaSelSegmentacao();
		Disponivel disSegmentacao = getBonusForm().getListaSegmentacao();

		for (int i = 0; i < selSegmentacao.getItArray().length; i++) {
			for (int j = 0; j < disSegmentacao.getItArray().length; j++) {
				if (selSegmentacao.getItArray(i).getId().equals(disSegmentacao.getItArray(j).getId())) {
					disSegmentacao.removeIt(j);
					break;
				}
			}
		}

		getBonusForm().setIdBonus(out.getManter().getIdCadastrado());
		getBonusForm().setIdTpLinha(out.getManter().getTpLinha());
		getBonusForm().setNmBonus(out.getManter().getNmBonus());
		getBonusForm().setVdBonus(out.getManter().getVdBonus());
		getBonusForm().setIdTpServico(out.getManter().getTpServico());
		getBonusForm().setCdServico(out.getManter().getCdServico());
		getBonusForm().setVlBonus(out.getManter().getVlBonus() != null ? out.getManter().getVlBonus().replaceAll("\\.", ",") : "");
		getBonusForm().setIdGrpPacote(out.getManter().getIdGrpPacote());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="successA" path="inclusaoAlteracao.jsp"
	 * @jpf:forward name="successU" path="inclusaoAlteracaoURA.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward alterar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String success = ConstantesCRM.SVAZIO;
		BonusForm form = (BonusForm) formParam;
		try {
			if (form.getTpOperacao() != null && !ConstantesCRM.SVAZIO.equals(form.getTpOperacao())) {
				FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
				xml.setNmProcesso("MANBONUS");
				xml.setTpProcesso("ALT");

				Manter manter = xml.addNewManter();
				manter.setIdCadastrado(form.getIdBonus());
				manter.setTpLinha(form.getIdTpLinha());
				manter.setNmBonus(form.getNmBonus());
				manter.setVdBonus(form.getVdBonus());
				manter.setTpServico(form.getIdTpServico());
				manter.setCdServico(form.getCdServico());

				Lista lista = xml.addNewListasVO().addNewLista();
				lista.setNmSelect("REGIONAL");
				Selecionado selecao = lista.addNewSelecionado();
				for (int i = 0; i < form.getIdSelRegional().length; i++) {
					selecao.addNewIt().setId(form.getIdSelRegional()[i]);
				}

				lista = xml.getListasVO().addNewLista();
				lista.setNmSelect("SEGMENTACAO");
				selecao = lista.addNewSelecionado();
				for (int i = 0; i < form.getIdSelSegmentacao().length; i++) {
					selecao.addNewIt().setId(form.getIdSelSegmentacao()[i]);
				}

				if ("A".equals(form.getTpOperacao())) {
					success = "successA";
					getBonusForm().setTpOperacao("A");
					alterarAtd(user, xml, form, request);
				} else if ("U".equals(form.getTpOperacao())) {
					success = "successU";
					getBonusForm().setTpOperacao("U");
					alterarUra(user, xml, form, request);
				}
			}
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(success);
	}

	private void alterarAtd(String user, FidelizacaoVO xml, BonusForm form, HttpServletRequest request) throws Exception {

		xml.getManter().setTpBonus(form.getIdTpBonus());

		Lista lista = xml.getListasVO().addNewLista();
		lista.setNmSelect("TPCLIENTE");
		Selecionado selecao = lista.addNewSelecionado();
		for (int i = 0; i < form.getIdSelTpCliente().length; i++) {
			selecao.addNewIt().setId(form.getIdSelTpCliente()[i]);
		}

		FidelizacaoVO conf = fidelizacao.setParam(user, xml);
		if ("1".equals(conf.getCodError())) {
			request.setAttribute("msgError", conf.getMsgError());
		} else {
			request.setAttribute("status", ConstantesCRM.SOK);
		}
	}

	private void alterarUra(String user, FidelizacaoVO xml, BonusForm form, HttpServletRequest request) throws Exception {
		xml.getManter().setVlBonus(form.getVlBonus());
		xml.getManter().setIdGrpPacote(form.getIdGrpPacote());
		xml.getManter().setCdServico(form.getCdServico());

		ManterBonusUraDAO daoURA = new ManterBonusUraDAO();
		FidelizacaoVO out = daoURA.altBonus(user, xml);
		request.setAttribute("status", ConstantesCRM.SOK);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manterBonus.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		BonusForm form = (BonusForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String success = ConstantesCRM.SVAZIO;

		if (form.getTpOperacao() != null && !ConstantesCRM.SVAZIO.equals(form.getTpOperacao())) {
			if ("A".equals(form.getTpOperacao())) {
				success = "successA";
				getBonusForm().setTpOperacao("A");
			} else if ("U".equals(form.getTpOperacao())) {
				success = "successU";
				getBonusForm().setTpOperacao("U");
			}
		}
		if (!ConstantesCRM.SVAZIO.equals(success)) {
			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.addNewFiltro().addNewCombos().addNmSelect("REGIONAL");
			xml.getFiltro().getCombos().addNmSelect("TPCLIENTE");
			xml.getFiltro().getCombos().addNmSelect("TPBONUS");
			xml.getFiltro().getCombos().addNmSelect("TPLINHA");
			xml.getFiltro().getCombos().addNmSelect("SEGMENTACAO");
			xml.getFiltro().getCombos().addNmSelect("GRPPACOTE");

			FidelizacaoVO listas = fidelizacao.getListas(user, xml);
			getBonusForm().setFidelizacaoVO(listas);
			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getBonusForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
				} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getBonusForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());
				} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getBonusForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
				} else if ("TPBONUS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getBonusForm().setListaTpBonus(listas.getListasVO().getListaArray(i).getDisponivel());
				} else if ("SEGMENTACAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getBonusForm().setListaSegmentacao(listas.getListasVO().getListaArray(i).getDisponivel());
				} else if ("GRPPACOTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getBonusForm().setListaGrpPacote(listas.getListasVO().getListaArray(i).getDisponivel());
				}
			}

			xml = FidelizacaoVO.Factory.newInstance();
			xml.setNmProcesso("MANBONUS");
			xml.setTpProcesso("PSQ");

			Filtro filtro = xml.addNewFiltro();
			filtro.setIdRegional(form.getIdRegional());
			filtro.setTpLinha(form.getIdTpLinha());
			filtro.setIdSegmento(form.getIdSegmentacao());

			if ("A".equals(form.getTpOperacao())) {
				pesquisaAtd(user, form, xml);
			} else if ("U".equals(form.getTpOperacao())) {
				pesquisaUra(user, form, xml);
			}

		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void pesquisaAtd(String user, BonusForm form, FidelizacaoVO xml) throws Exception {
		xml.getFiltro().setTpCliente(form.getIdTpCliente());
		xml.getFiltro().setTpBonus(form.getIdTpBonus());

		FidelizacaoVO tabela = fidelizacao.getListas(user, xml);
		if (tabela.getTabelas() == null) {
			tabela.addNewTabelas().addNewBonus();
		} else {
			if (tabela.getTabelas().getBonus() == null) {
				tabela.getTabelas().addNewBonus();
			}
		}
		getBonusForm().setIdRegional(form.getIdRegional());
		getBonusForm().setIdTpCliente(form.getIdTpCliente());
		getBonusForm().setIdTpLinha(form.getIdTpLinha());
		getBonusForm().setIdTpBonus(form.getIdTpBonus());
		getBonusForm().setIdSegmentacao(form.getIdSegmentacao());
		getBonusForm().setFidelizacaoVO(tabela);
	}

	private void pesquisaUra(String user, BonusForm form, FidelizacaoVO xml) throws Exception {
		xml.getFiltro().setIdGrpPacote(form.getIdGrpPacote());
		ManterBonusUraDAO daoURA = new ManterBonusUraDAO();
		FidelizacaoVO out = daoURA.listBonus(user, xml);

		if (out.getTabelas() == null) {
			out.addNewTabelas().addNewBonus();
		} else {
			if (out.getTabelas().getBonus() == null) {
				out.getTabelas().addNewBonus();
			}
		}
		getBonusForm().setIdRegional(form.getIdRegional());
		getBonusForm().setIdTpLinha(form.getIdTpLinha());
		getBonusForm().setIdSegmentacao(form.getIdSegmentacao());
		getBonusForm().setIdGrpPacote(form.getIdGrpPacote());
		getBonusForm().setFidelizacaoVO(out);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisar.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward excluir(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("ManterBonusController:excluirBonus");
		BonusForm form = (BonusForm) formParam;
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			String success = ConstantesCRM.SVAZIO;
			if (form.getTpOperacao() != null && !ConstantesCRM.SVAZIO.equals(form.getTpOperacao())) {
				if ("A".equals(form.getTpOperacao())) {
					success = "successA";
					getBonusForm().setTpOperacao("A");
					FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
					xml.setNmProcesso("MANBONUS");
					xml.setTpProcesso("EXC");

					Manter manter = xml.addNewManter();
					manter.setIdCadastrado(form.getIdBonus());

					FidelizacaoVO conf = fidelizacao.setParam(user, xml);
					if (ConstantesCRM.SONE.equals(conf.getCodError())) {
						request.setAttribute("msgError", conf.getMsgError());
					}
				} else if ("U".equals(form.getTpOperacao())) {
					success = "successU";
					getBonusForm().setTpOperacao("U");
					excluirUra(user, Long.parseLong(form.getIdBonus()));
				}
			}
		} catch (Exception e) {
			log.error("Exception - ManterBonusController:excluirBonus", e);
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void excluirUra(String user, long idBonus) throws Exception {
		ManterBonusUraDAO daoURA = new ManterBonusUraDAO();
		FidelizacaoVO out = daoURA.delBonus(user, idBonus);
	}

	protected boolean alwaysTrackPreviousPage() {
		return true;
	}

	protected boolean alwaysTrackPreviousAction() {
		return true;
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class BonusForm extends ActionForm {
		private FidelizacaoVO fidelizacaoVO = FidelizacaoVO.Factory.newInstance();
		private Disponivel listaRegional;
		private Disponivel listaClientes;
		private Disponivel listaLinhas;
		private Disponivel listaServicos;
		private Disponivel listaTpBonus;
		private Disponivel listaSegmentacao;
		private Disponivel listaGrpPacote;

		private Selecionado listaSelRegional;
		private Selecionado listaSelClientes;
		private Selecionado listaSelSegmentacao;

		private String[] idSelRegional = new String[0];
		private String[] idSelTpCliente = new String[0];
		private String[] idSelSegmentcao = new String[0];

		private String idBonus = ConstantesCRM.SVAZIO;
		private String idRegional = ConstantesCRM.SVAZIO;
		private String idTpLinha = ConstantesCRM.SVAZIO;
		private String idTpCliente = ConstantesCRM.SVAZIO;
		private String idTpServico = ConstantesCRM.SVAZIO;
		private String idTpBonus = ConstantesCRM.SVAZIO;
		private String idSegmentacao = ConstantesCRM.SVAZIO;
		private String idGrpPacote = ConstantesCRM.SVAZIO;
		private String tpOperacao = ConstantesCRM.SVAZIO;

		private String nmBonus = ConstantesCRM.SVAZIO;
		private String cdServico = ConstantesCRM.SVAZIO;
		private String vdBonus = ConstantesCRM.SVAZIO;
		private String vlBonus = ConstantesCRM.SVAZIO;

		public FidelizacaoVO getFidelizacaoVO() {
			return this.fidelizacaoVO;
		}

		public void setFidelizacaoVO(FidelizacaoVO fidelizacaoVO) {
			this.fidelizacaoVO = fidelizacaoVO;
		}

		public void setListaRegional(Disponivel listaRegional) {
			this.listaRegional = listaRegional;
		}

		public Disponivel getListaRegional() {
			return this.listaRegional;
		}

		public void setListaClientes(Disponivel listaClientes) {
			this.listaClientes = listaClientes;
		}

		public Disponivel getListaClientes() {
			return this.listaClientes;
		}

		public void setListaLinhas(Disponivel listaLinhas) {
			this.listaLinhas = listaLinhas;
		}

		public Disponivel getListaLinhas() {
			return this.listaLinhas;
		}

		public void setListaServicos(Disponivel listaServicos) {
			this.listaServicos = listaServicos;
		}

		public Disponivel getListaServicos() {
			return this.listaServicos;
		}

		public void setListaTpBonus(Disponivel listaTpBonus) {
			this.listaTpBonus = listaTpBonus;
		}

		public Disponivel getListaTpBonus() {
			return this.listaTpBonus;
		}

		public void setListaSegmentacao(Disponivel listaSegmentacao) {
			this.listaSegmentacao = listaSegmentacao;
		}

		public Disponivel getListaSegmentacao() {
			return this.listaSegmentacao;
		}

		public void setListaGrpPacote(Disponivel listaGrpPacote) {
			this.listaGrpPacote = listaGrpPacote;
		}

		public Disponivel getListaGrpPacote() {
			return this.listaGrpPacote;
		}

		public void setListaSelRegional(Selecionado listaSelRegional) {
			this.listaSelRegional = listaSelRegional;
		}

		public Selecionado getListaSelRegional() {
			return this.listaSelRegional;
		}

		public void setListaSelClientes(Selecionado listaSelClientes) {
			this.listaSelClientes = listaSelClientes;
		}

		public Selecionado getListaSelClientes() {
			return this.listaSelClientes;
		}

		public void setListaSelSegmentacao(Selecionado listaSelSegmentacao) {
			this.listaSelSegmentacao = listaSelSegmentacao;
		}

		public Selecionado getListaSelSegmentacao() {
			return this.listaSelSegmentacao;
		}

		public void setIdSelRegional(String[] idSelRegional) {
			this.idSelRegional = idSelRegional;
		}

		public String[] getIdSelRegional() {
			return this.idSelRegional;
		}

		public void setIdSelTpCliente(String[] idSelTpCliente) {
			this.idSelTpCliente = idSelTpCliente;
		}

		public String[] getIdSelTpCliente() {
			return this.idSelTpCliente;
		}

		public void setIdSelSegmentacao(String[] idSelSegmentcao) {
			this.idSelSegmentcao = idSelSegmentcao;
		}

		public String[] getIdSelSegmentacao() {
			return this.idSelSegmentcao;
		}

		public void setIdBonus(String idBonus) {
			this.idBonus = idBonus;
		}

		public String getIdBonus() {
			return this.idBonus;
		}

		public void setIdRegional(String idRegional) {
			this.idRegional = idRegional;
		}

		public String getIdRegional() {
			return this.idRegional;
		}

		public void setIdTpCliente(String idTpCliente) {
			this.idTpCliente = idTpCliente;
		}

		public String getIdTpCliente() {
			return this.idTpCliente;
		}

		public void setIdTpServico(String idTpServico) {
			this.idTpServico = idTpServico;
		}

		public String getIdTpServico() {
			return this.idTpServico;
		}

		public void setIdTpLinha(String idTpLinha) {
			this.idTpLinha = idTpLinha;
		}

		public String getIdTpLinha() {
			return this.idTpLinha;
		}

		public void setIdTpBonus(String idTpBonus) {
			this.idTpBonus = idTpBonus;
		}

		public String getIdTpBonus() {
			return this.idTpBonus;
		}

		public void setIdSegmentacao(String idSegmentacao) {
			this.idSegmentacao = idSegmentacao;
		}

		public String getIdSegmentacao() {
			return this.idSegmentacao;
		}

		public void setIdGrpPacote(String idGrpPacote) {
			this.idGrpPacote = idGrpPacote;
		}

		public String getIdGrpPacote() {
			return this.idGrpPacote;
		}

		public void setTpOperacao(String tpOperacao) {
			this.tpOperacao = tpOperacao;
		}

		public String getTpOperacao() {
			return this.tpOperacao;
		}

		public void setNmBonus(String nmBonus) {
			this.nmBonus = nmBonus;
		}

		public String getNmBonus() {
			return this.nmBonus;
		}

		public void setVdBonus(String vdBonus) {
			this.vdBonus = vdBonus;
		}

		public String getVdBonus() {
			return this.vdBonus;
		}

		public void setVlBonus(String vlBonus) {
			this.vlBonus = vlBonus;
		}

		public String getVlBonus() {
			return this.vlBonus;
		}

		public void setCdServico(String cdServico) {
			this.cdServico = cdServico;
		}

		public String getCdServico() {
			return this.cdServico;
		}
	}
}
