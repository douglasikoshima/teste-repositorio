package admsistemas.AdmFormularios;

import java.io.BufferedOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import workflow.Relacionamento.RelacionamentoController.RelacionamentoForm;
import admsistemas.AdmFormularios.formBeans.RegrasEncaminhamentoForm;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.admsistemas.vo.CarterizacaoVODocument.CarterizacaoVO;
import br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO;
import br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO.FuncionalidadeFrmVO;
import br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO.Lista.Selecionado;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoDisponivelVODocument.RegrasEncaminhamentoDisponivelVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoSelecionadoVODocument.RegrasEncaminhamentoSelecionadoVO.RegionalVO;
import br.com.vivo.fo.admsistemas.vo.SegmentacaoVODocument.SegmentacaoVO;
import br.com.vivo.fo.cliente.ClienteUtils;
import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset.Linhas.Linha;
import br.com.vivo.fo.commons.utils.PassPhrase;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.commons.vo.IDValorClienteVODocument.IDValorClienteVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.fidelizacao.vo.ItDocument.It;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.model.Associacao;
import br.com.vivo.fo.model.Cliente;
import br.com.vivo.fo.model.Contato;
import br.com.vivo.fo.model.Formulario;
import br.com.vivo.fo.model.Funcionalidade;
import br.com.vivo.fo.model.SLA;
import br.com.vivo.fo.workflow.vo.AtendimentoArvoreFiltrosVODocument.AtendimentoArvoreFiltrosVO;
import br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.WFRegionalVODocument.WFRegionalVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({ "unchecked", "rawtypes", "unused", "deprecation" })
public class AdmFormulariosController extends AbstractAction {

	private static final long serialVersionUID = -8826478896738526713L;
	protected global.Global globalApp = new global.Global();
	private final String RAIZ_PRINCIPAL = "PAI";
	private transient Logger log = new Logger("admsistemas");
	private CamposDinamicosForm camposDinamicosForm = null;
	private RegrasEncaminhamentoForm regrasEncaminhamentoForm;
	private Resultset listaCamposFormulario = null;
	private HashMap mapCamposSubFormulario = new HashMap();

	private Set subFormAdded = new HashSet();
	private Associacao formularioFuncionalidade = getFormularioFuncionalidade();

	@EJB
	private br.com.vivo.fo.ctrls.workflow.monitoramento.MonitoramentoFacade monitoramentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.admFormularios.AdmFormularios admFormulariosFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	public RegrasEncaminhamentoForm getRegrasEncaminhamentoForm() {
		if (regrasEncaminhamentoForm == null) {
			regrasEncaminhamentoForm = new RegrasEncaminhamentoForm();
		}
		return regrasEncaminhamentoForm;
	}

	public CamposDinamicosForm getCamposDinamicosForm() {
		if (camposDinamicosForm == null) {
			camposDinamicosForm = new CamposDinamicosForm();
		}
		return camposDinamicosForm;
	}

	public Associacao getFormularioFuncionalidade() {
		if (formularioFuncionalidade == null) {
			formularioFuncionalidade = new Associacao();
		}
		return formularioFuncionalidade;
	}

	private RelacionamentoForm relacionamentoForm;

	public RelacionamentoForm getRelacionamentoForm() {
		if (this.relacionamentoForm == null) {
			this.relacionamentoForm = new RelacionamentoForm();
		}
		return this.relacionamentoForm;
	}

	public void setRelacionamentoForm(RelacionamentoForm form) {
		this.relacionamentoForm = form;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("loadCamposForm".equals(mapping.getParameter())) {
			return loadCamposForm(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("incluirCampo".equals(mapping.getParameter())) {
			return incluirCampo(mapping, form, request, response);
		} else if ("incluirCampoAlt".equals(mapping.getParameter())) {
			return incluirCampoAlt(mapping, form, request, response);
		} else if ("alterarCampo".equals(mapping.getParameter())) {
			return alterarCampo(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("veficaExcluirCampo".equals(mapping.getParameter())) {
			return veficaExcluirCampo(mapping, form, request, response);
		} else if ("excluirCampo".equals(mapping.getParameter())) {
			return excluirCampo(mapping, form, request, response);
		} else if ("loadManFormulario".equals(mapping.getParameter())) {
			return loadManFormulario(mapping, form, request, response);
		} else if ("verificaExcluirForm".equals(mapping.getParameter())) {
			return verificaExcluirForm(mapping, form, request, response);
		} else if ("excluirForm".equals(mapping.getParameter())) {
			return excluirForm(mapping, form, request, response);
		} else if ("atualizaListaSubFormularios".equals(mapping.getParameter())) {
			return atualizaListaSubFormularios(mapping, form, request, response);
		} else if ("visualizaFormulario".equals(mapping.getParameter())) {
			return visualizaFormulario(mapping, form, request, response);
		} else if ("loadCfgFormulario".equals(mapping.getParameter())) {
			return loadCfgFormulario(mapping, form, request, response);
		} else if ("loadCfgFormularioAlterar".equals(mapping.getParameter())) {
			return loadCfgFormularioAlterar(mapping, form, request, response);
		} else if ("loadCamposExistentes".equals(mapping.getParameter())) {
			return loadCamposExistentes(mapping, form, request, response);
		} else if ("loadCamposAssociados".equals(mapping.getParameter())) {
			return loadCamposAssociados(mapping, form, request, response);
		} else if ("atualizarObrigatoriedade".equals(mapping.getParameter())) {
			return atualizarObrigatoriedade(mapping, form, request, response);
		} else if ("removeItemFormulario".equals(mapping.getParameter())) {
			return removeItemFormulario(mapping, form, request, response);
		} else if ("loadCamposFormulario2Add".equals(mapping.getParameter())) {
			return loadCamposFormulario2Add(mapping, form, request, response);
		} else if ("loadCamposSubFormulario2Add".equals(mapping.getParameter())) {
			return loadCamposSubFormulario2Add(mapping, form, request, response);
		} else if ("gravarFormulario".equals(mapping.getParameter())) {
			return gravarFormulario(mapping, form, request, response);
		} else if ("alteraFormulario".equals(mapping.getParameter())) {
			return alteraFormulario(mapping, form, request, response);
		} else if ("loadAssFormulario".equals(mapping.getParameter())) {
			return loadAssFormulario(mapping, form, request, response);
		} else if ("loadFormsDisponiveis".equals(mapping.getParameter())) {
			return loadFormsDisponiveis(mapping, form, request, response);
		} else if ("loadFormsAssociados".equals(mapping.getParameter())) {
			return loadFormsAssociados(mapping, form, request, response);
		} else if ("loadDadosFuncionalidadeFrm".equals(mapping.getParameter())) {
			return loadDadosFuncionalidadeFrm(mapping, form, request, response);
		} else if ("gravarAssFormulario".equals(mapping.getParameter())) {
			return gravarAssFormulario(mapping, form, request, response);
		} else if ("loadManSubFormulario".equals(mapping.getParameter())) {
			return loadManSubFormulario(mapping, form, request, response);
		} else if ("loadConfigCamposSubFormulario".equals(mapping.getParameter())) {
			return loadConfigCamposSubFormulario(mapping, form, request, response);
		} else if ("incluirAlterarSubFormulario".equals(mapping.getParameter())) {
			return incluirAlterarSubFormulario(mapping, form, request, response);
		} else if ("beginFormulaEncaminhamento".equals(mapping.getParameter())) {
			return beginFormulaEncaminhamento(mapping, form, request, response);
		} else if ("checkDuplicidadeFormularEncaminhamento".equals(mapping.getParameter())) {
			return checkDuplicidadeFormularEncaminhamento(mapping, form, request, response);
		} else if ("saveFormulaEncaminhamento".equals(mapping.getParameter())) {
			return saveFormulaEncaminhamento(mapping, form, request, response);
		} else if ("getPesquisa".equals(mapping.getParameter())) {
			return getPesquisa(mapping, form, request, response);
		} else if ("loadSelecionarFormulario".equals(mapping.getParameter())) {
			return loadSelecionarFormulario(mapping, form, request, response);
		} else if ("loadSelecionarCliente".equals(mapping.getParameter())) {
			return loadSelecionarCliente(mapping, form, request, response);
		} else if ("loadSelecionarClienteDessassociado".equals(mapping.getParameter())) {
			return loadSelecionarClienteDessassociado(mapping, form, request, response);			
		} else if ("loadSelecionarPrazo".equals(mapping.getParameter())) {
			return loadSelecionarPrazo(mapping, form, request, response);
		} else if ("gravarFormularioFuncionalidade".equals(mapping.getParameter())) {
			return gravarFormularioFuncionalidade(mapping, form, request, response);
		} else if ("obterArvoreContato".equals(mapping.getParameter())) {
			return obterArvoreContato(mapping, form, request, response);
		} else if ("expandeArvoreContato".equals(mapping.getParameter())) {
			return expandeArvoreContato(mapping, form, request, response);
		} else if ("loadPrazoAtendimentoFormulario".equals(mapping.getParameter())) {
			return loadPrazoAtendimentoFormulario(mapping, form, request, response);
		} else if ("excluirSubFormulario".equals(mapping.getParameter())) {
			return excluirSubFormulario(mapping, form, request, response);
		} else if ("verificarFormularioExclusivo".equals(mapping.getParameter())) {
			return verificarFormularioExclusivo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="camposForm.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="camposForm.jsp"
	 */
	public ActionForward loadCamposForm(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="done" return-action="AdmFormulariosDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="pesquisar.do"
	 * @jpf:ActionForward name="error" path="camposForm.jsp"
	 */
	public ActionForward incluirCampo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ActionRedirect f = new ActionRedirect(ConstantesCRM.SUCCESS);
		String msg = ConstantesCRM.SVAZIO;
		boolean isErro = false;
		String mensagemErro = ConstantesCRM.SVAZIO;
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;

		try {
			String apCampo = ConstantesCRM.SVAZIO;
			if ("T".equals(form.getTpCampo())) {
				apCampo = ConstantesCRM.CT_Text;
			} else if ("S".equals(form.getTpCampo())) {
				apCampo = ConstantesCRM.CT_Select;
			} else if ("SM".equals(form.getTpCampo())) {
				apCampo = "SELECTMULTI";
			} else if ("L".equals(form.getTpCampo())) {
				apCampo = "LABEL";
			} else if ("M".equals(form.getTpCampo())) {
				apCampo = "MEMO";
			} else if ("LP".equals(form.getTpCampo())) {
				apCampo = "SELECTPREENCHIDO";
			}

			String mascCampo = ConstantesCRM.SVAZIO;
			if ("T".equals(form.getTpCampo()) || "LP".equals(form.getTpCampo())) {
				mascCampo = form.getFmtTexto();
			} else {
				mascCampo = "texto";
			}

			StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
			Pesquisar pesquisar = new Pesquisar();

			String idCampo = ConstantesCRM.SVAZIO;
			int inCampo = existField2Insert(form.getNmCampo());

			if (inCampo == 1) {
				msg = "Já existe um campo com o nome informado.";
				f.addParameter("msg", msg);
				response.setStatus(406);
				return f;
			}

			if (inCampo < 0) {
				query = new StringBuffer("select contatoadm.camposq.nextval from dual");
				Resultset rsVO = pesquisar.executar(query.toString());

				if (rsVO != null && rsVO.getLinhas() != null && rsVO.getLinhas().getLinhaArray().length > 0) {
					idCampo = rsVO.getLinhas().getLinhaArray(0).getValorArray(0);
					query = new StringBuffer(ConstantesCRM.SVAZIO);
					query.append("insert into contatoadm.campo ( ");
					query.append("idcampo,nmcampo,idtipodadocampo,idmascaraapresencaocampo,idlayoutapresentacaocampo,nrtamanho,");
					query.append("indisponibilidade,idclassificadorcampo,infiltro,idusuarioalteracao,dtultimaalteracao,inpesquisa,inobrigatorio");
					query.append(",lbcampo) values (").append(idCampo).append(",'").append(form.getNmCampo()).append("', ");
					query.append("(select idtipodadocampo from apoio.tipodadocampo where UPPER(TRIM(sgtipodadocampo)) = UPPER(TRIM('").append(form.getTpCampo()).append("'))),");
					query.append("(select idmascaraapresencaocampo from apoio.mascaraapresentacaocampo where UPPER(TRIM(sgmascaraapresencaocampo)) = UPPER(TRIM('").append(mascCampo).append("'))),");
					query.append("(select idlayoutapresentacaocampo from apoio.layoutapresentacaocampo where UPPER(TRIM(sglayoutapresentacaocampo)) = UPPER(TRIM('").append(apCampo).append("'))),");
					query.append("0,1,(select idclassificadorcampo from contatoadm.campoclassificador where nmclassificadorcampo='VOLE'),");
					query.append("1,1,sysdate,0,1,'").append(form.getLbCampo()).append("')");
					log.debug(query);
					pesquisar = new Pesquisar();
					rsVO = pesquisar.executar(query.toString());

					if (rsVO != null && rsVO.getMsg() != null) {
						log.error("[AdmFormulariosController.incluirCampoAlt] Erro Formulário " + rsVO.getMsg());
						mensagemErro = "Problemas na inclusão dos campos do formulário";
						isErro = true;
					}

				}
			} else if (inCampo == 0) {
				query = new StringBuffer("select c.idcampo, c.indisponibilidade ");
				query.append("from contatoadm.campo c, contatoadm.campoclassificador clc   ");
				query.append("where c.idclassificadorcampo = clc.idclassificadorcampo ");
				query.append("and clc.nmclassificadorcampo = 'VOLE' ");
				query.append("and c.nmcampo = '").append(form.getNmCampo()).append("' ");

				pesquisar = new Pesquisar();
				Resultset rsVO = pesquisar.executar(query.toString());

				if (rsVO != null && rsVO.getLinhas() != null && rsVO.getLinhas().getLinhaArray().length > 0) {
					idCampo = rsVO.getLinhas().getLinhaArray(0).getValorArray(0);
					query = new StringBuffer(ConstantesCRM.SVAZIO);
					query.append("update contatoadm.campo set  ");
					query.append("idtipodadocampo = (select idtipodadocampo from apoio.tipodadocampo where UPPER(TRIM(sgtipodadocampo)) = UPPER(TRIM('").append(form.getTpCampo()).append("'))), ");
					query.append("idmascaraapresencaocampo = (select idmascaraapresencaocampo from apoio.mascaraapresentacaocampo where UPPER(TRIM(sgmascaraapresencaocampo)) = UPPER(TRIM('texto'))), ");
					query.append("idlayoutapresentacaocampo = (select idlayoutapresentacaocampo from apoio.layoutapresentacaocampo where UPPER(TRIM(sglayoutapresentacaocampo)) = UPPER(TRIM('").append(apCampo).append("'))), ");
					query.append("indisponibilidade = 1, ");
					query.append("dtultimaalteracao = sysdate ");
					query.append("where idcampo = ").append(idCampo);
					pesquisar = new Pesquisar();
					rsVO = pesquisar.executar(query.toString());

					if (rsVO != null && rsVO.getMsg() != null) {
						log.error("[AdmFormulariosController.incluirCampoAlt] Erro Formulário " + rsVO.getMsg());
						mensagemErro = "Problemas na inclusão dos campos do formulário";
						isErro = true;
					}
				}
			}

			if ("S".equals(form.getTpCampo()) || "SM".equals(form.getTpCampo()) || "L".equals(form.getTpCampo())) {
				for (int i = 0; i < form.getVlCampoCombo().length; i++) {

					String dsDominio = StringUtils.left(form.getVlCampoCombo()[i], 2000);
					query = new StringBuffer("insert into contatoadm.dominiocampo (idcampo,dsdominio,idusuarioalteracao,dtultimaalteracao) ");
					query.append("values (").append(idCampo).append(", '").append(dsDominio).append("', ").append(i).append(", sysdate) ");
					log.debug("[AdmFormulariosController::incluirCampo] Incluir Dominio " + query);
					Resultset rsVO = pesquisar.executar(query.toString());
					if (rsVO != null && rsVO.getMsg() != null) {
						log.error("[AdmFormulariosController.incluirCampoAlt] Erro Formulário " + rsVO.getMsg());
						mensagemErro = "Problemas na inclusão dos campos do formulário";
						isErro = true;
					}
				}
			}

		} catch (Exception e) {
			log.error("AdmFormulariosController::incluirCampo", e);
			mensagemErro = "Problemas na inclusão dos campos do formulário";
			isErro = true;
		}
		msg = isErro ? mensagemErro : "Campo inserido com sucesso.";
		f.addParameter("msg", msg);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="pesquisar.do"
	 * @jpf:ActionForward name="error" path="camposForm.jsp"
	 */
	public ActionForward incluirCampoAlt(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = mapping.findForward(ConstantesCRM.SUCCESS);
		try {
			CamposDinamicosForm form = (CamposDinamicosForm) formParam;
			String apCampo = ConstantesCRM.SVAZIO;
			String mascCampo = "texto";
			String idCampo = form.getIdCampoAlt();

			if ("T".equals(form.getTpCampoAlt())) {
				apCampo = ConstantesCRM.CT_Text;
			} else if ("S".equals(form.getTpCampoAlt())) {
				apCampo = ConstantesCRM.CT_Select;
			} else if ("SM".equals(form.getTpCampoAlt())) {
				apCampo = "SELECTMULTI";
			} else if ("L".equals(form.getTpCampo())) {
				apCampo = "LABEL";
			} else if ("M".equals(form.getTpCampo())) {
				apCampo = "MEMO";
			} else if ("LP".equals(form.getTpCampoAlt())) {
				apCampo = "SELECTPREENCHIDO";
			}

			StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
			StringBuffer queryDelete = new StringBuffer(ConstantesCRM.SVAZIO);
			Pesquisar pesquisar = new Pesquisar();

			if ("S".equals(form.getTpCampoAlt()) || "SM".equals(form.getTpCampoAlt()) || "L".equals(form.getTpCampoAlt())) {
				queryDelete.append("delete ");
				queryDelete.append("  from contatoadm.dominiocampo ");
				queryDelete.append(" where idcampo = ").append(idCampo);

				log.debug(queryDelete);

				Resultset rsVODelete = pesquisar.executar(queryDelete.toString());

				for (int i = 0; i < form.getVlCampoComboAlt().length; i++) {
					String dsDominio = StringUtils.left(form.getVlCampoComboAlt()[i], 2000);
					query = new StringBuffer("insert into contatoadm.dominiocampo (idcampo,dsdominio,idusuarioalteracao,dtultimaalteracao) ");
					query.append("values (").append(idCampo).append(", '").append(dsDominio).append("',").append(i).append(", sysdate) ");
					log.debug(query);
					Resultset rsVO = pesquisar.executar(query.toString());

					if (rsVO != null && rsVO.getMsg() != null) {
						log.error("[AdmFormulariosController.incluirCampoAlt] Erro Formulário " + rsVODelete.getMsg());
						request.setAttribute("msg", "Problemas na inclusão dos campos do formulário");
						actionForward.setPath(actionForward.getPath() + "?msg=Problemas na inclusão dos campos do formulário");
					}
				}
			}

		} catch (Exception e) {
			log.error("AdmFormulariosController::incluirCampoAlt", e);
			request.setAttribute("msgError", "Problemas na inclusão dos campos do formulário");
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("top.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		return actionForward;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="AlterarCampo.jsp"
	 * @jpf:ActionForward name="error" path="AlterarCampo.jsp"
	 */
	public ActionForward alterarCampo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String idCampo = request.getParameter("idCampo");
		try {
			StringBuffer query = new StringBuffer();
			CamposDinamicosForm form = (CamposDinamicosForm) formParam;
			query.append("select c.nmcampo, c.lbcampo, t.nmtipodadocampo, t.sgtipodadocampo ");
			query.append("  from contatoadm.campo c, ");
			query.append("       apoio.tipodadocampo t ");
			query.append(" where c.idtipodadocampo = t.idtipodadocampo ");
			query.append("   and c.idcampo = ").append(idCampo);

			log.debug(query);

			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = pesquisar.executar(query.toString());

			String nmCampo = ConstantesCRM.SVAZIO;
			String lbCampo = ConstantesCRM.SVAZIO;
			String tpCampo = ConstantesCRM.SVAZIO;
			String apCampo = ConstantesCRM.SVAZIO;

			Campo campo = new Campo();

			if (rsVO != null && rsVO.getLinhas() != null) {
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					nmCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
					lbCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
					tpCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(2);
					apCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(3);
					campo.setIdCampo(idCampo);
					campo.setNmCampo(nmCampo);
					campo.setLbCampo(lbCampo);
					campo.setTpCampo(tpCampo);
					campo.setApCampo(apCampo);
				}
			}

			List listaDominio = loadDominioFromCampo(idCampo, request);
			getCamposDinamicosForm().setListaDominio(listaDominio);

			getCamposDinamicosForm().setCampoFormulario(campo);

			if (request.getParameter("msg") != null) {
				request.setAttribute("msgError", request.getParameter("msg"));
			}
		} catch (Exception e) {
			log.error("AdmFormulariosController::pesquisar", e);
		}

		log.debug("[AdmFormulariosController.alterarCampo] idCampo " + idCampo);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private List loadDominioFromCampo(String idCampo, HttpServletRequest request) {
		List listaDominio = new ArrayList();

		try {
			StringBuffer query = new StringBuffer();

			query.append("select d.dsdominio ");
			query.append("  from contatoadm.dominiocampo d ");
			query.append(" where d.idcampo = ").append(idCampo);
			query.append(" order by idusuarioalteracao ");

			log.debug(query);

			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = pesquisar.executar(query.toString());

			String dsDominio = ConstantesCRM.SVAZIO;

			if (rsVO != null && rsVO.getLinhas() != null) {
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					dsDominio = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
					Dominio dominio = new Dominio();
					dominio.setIdDominio(idCampo);
					dominio.setVlDominio(dsDominio);
					listaDominio.add(dominio);
				}
			}

			if (request.getParameter("msg") != null) {
				request.setAttribute("msgError", request.getParameter("msg"));
			}
		} catch (Exception e) {
			log.error("AdmFormulariosController::pesquisar", e);
		}

		log.debug("[AdmFormulariosController.alterarCampo] idCampo " + idCampo);

		return listaDominio;
	}

	private int existField2Insert(String nome) {
		int iReturn = 0;
		try {
			StringBuffer query = new StringBuffer("select c.idcampo, c.indisponibilidade ");
			query.append("from contatoadm.campo c, contatoadm.campoclassificador clc   ");
			query.append("where c.idclassificadorcampo = clc.idclassificadorcampo ");
			query.append("and clc.nmclassificadorcampo = 'VOLE' ");
			query.append("and c.nmcampo = '").append(nome).append("' ");

			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = pesquisar.executar(query.toString());

			if (rsVO != null && rsVO.getLinhas() != null && rsVO.getLinhas().getLinhaArray().length > 0) {
				String inDisp = rsVO.getLinhas().getLinhaArray(0).getValorArray(1);
				// Se estiver indisponivel, houve exclusão logica
				if (ConstantesCRM.SZERO.equals(inDisp)) {
					iReturn = 0;
				} else {// Se existir o campo e está disponivel
					iReturn = 1;
				}
			} else {// Caso não retorne nenhum valor
				iReturn = -1;
			}

		} catch (Exception e) {
			log.error("AdmFormulariosController::existField2Insert", e);
		}
		return iReturn;
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="ListaCamposExistentes.jsp"
	 */
	public ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try {
			StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
			CamposDinamicosForm form = (CamposDinamicosForm) formParam;
			query.append("select c.idcampo, ");
			query.append("       decode(c.lbcampo, null, c.nmcampo, c.nmcampo || ' / ' || c.lbcampo) nmcampo, ");
			query.append("       l.sglayoutapresentacaocampo ");
			query.append("  from contatoadm.campo c, contatoadm.campoclassificador cc, ");
			query.append("       apoio.layoutapresentacaocampo l ");
			query.append(" where c.idclassificadorcampo = cc.idclassificadorcampo ");
			query.append("   and c.idlayoutapresentacaocampo = l.idlayoutapresentacaocampo ");
			query.append("   and cc.nmclassificadorcampo = 'VOLE' ");
			query.append("   and c.indisponibilidade = 1 ");

			log.debug(query);

			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = pesquisar.executar(query.toString());
			List listaCamposExistentes = new ArrayList();

			if (rsVO != null && rsVO.getLinhas() != null) {
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					String idCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
					String nmCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
					String apCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(2);

					Campo campoExistente = new Campo(idCampo, nmCampo, apCampo);

					listaCamposExistentes.add(campoExistente);
				}
			}

			getCamposDinamicosForm().setListaCamposExistentes(listaCamposExistentes);

			if (request.getParameter("msg") != null) {
				request.setAttribute("msgError", request.getParameter("msg"));
			}
		} catch (Exception e) {
			log.error("AdmFormulariosController::pesquisar", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	public ActionForward veficaExcluirCampo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			CamposDinamicosForm form = (CamposDinamicosForm) formParam;
			String retornoToXML = ConstantesCRM.SVAZIO;
			if (form.getIdCampo() != null && !ConstantesCRM.SVAZIO.equals(form.getIdCampo())) {
				StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
				query.append("select count(idcampo) qtCampos from contatoadm.formularioobjeto where idcampo = ").append(form.getIdCampo());

				log.debug(query);
				Pesquisar pesquisar = new Pesquisar();
				Resultset rsVO = pesquisar.executar(query.toString());

				if (rsVO != null && rsVO.getLinhas() != null) {
					String[] stringsToDelete = new String[1];
					stringsToDelete[0] = " xmlns=\"cliente.fo.vivo.com.br/vo\"";
					retornoToXML = ClienteUtils.getCleanXMLFromXSD(rsVO, stringsToDelete);
				}
			}
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			response.setContentType(ConstantesCRM.SContentType);
			bufferedOutputStream.write(retornoToXML.getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		} catch (Exception e) {
			log.error("AdmFormulariosController::veficaExcluirCampo", e);
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="pesquisar.do"
	 */
	public ActionForward excluirCampo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			CamposDinamicosForm form = (CamposDinamicosForm) formParam;
			StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
			query.append("update contatoadm.campo set indisponibilidade=0 where idcampo = ").append(form.getIdCampo());
			log.debug(query);

			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = pesquisar.executar(query.toString());

			query = new StringBuffer(ConstantesCRM.SVAZIO);
			query.append("delete from contatoadm.dominiocampo where idcampo = ").append(form.getIdCampo());
			log.debug(query);
			rsVO = pesquisar.executar(query.toString());

		} catch (Exception e) {
			log.error("AdmFormulariosController::excluirCampo", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="manFormulario.jsp"
	 */
	public ActionForward loadManFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			getCamposDinamicosForm().setListaFormularios(listaFormularios());
		} catch (Exception e) {
			log.error("AdmFormulariosController::loadManFormulario", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private Resultset listaFormularios() {
		Resultset rsVO = null;
		try {
			StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
			query.append("select f.idformulario,  ");
			query.append("     f.nmformulario,  ");
			query.append("     fc.nmfuncionalidade || ' - ' || nvl(ct.nmpath, ' ')  nmfuncionalidade ");
			query.append("from contatoadm.formulario f,  ");
			query.append("   contatoadm.funcionalidade fc,  ");
			query.append("   contatoadm.funcionalidadefrm fcfrm, ");
			query.append("   contatoadm.contato ct ");
			query.append("where f.idformulario = fcfrm.idformulario (+) ");
			query.append("and  fcfrm.idfuncionalidade = fc.idfuncionalidade (+) ");
			query.append("and  fcfrm.idcontato = ct.idcontato (+) ");

			log.debug(query);

			Pesquisar pesquisar = new Pesquisar();
			rsVO = pesquisar.executar(query.toString());

		} catch (Exception e) {
			rsVO = Resultset.Factory.newInstance();
			rsVO.addNewLinhas();
			log.error("AdmFormulariosController::listaFormularios", e);
		}
		return rsVO;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 */
	public ActionForward verificaExcluirForm(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			CamposDinamicosForm form = (CamposDinamicosForm) formParam;
			String retornoToXML = ConstantesCRM.SVAZIO;
			if (form.getIdFormulario() != null && !ConstantesCRM.SVAZIO.equals(form.getIdFormulario())) {
				StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
				query.append("select count(idformulario) from contatoadm.funcionalidadefrm where idformulario = ").append(form.getIdFormulario());

				log.debug(query);

				Pesquisar pesquisar = new Pesquisar();
				Resultset rsVO = pesquisar.executar(query.toString());
				if (rsVO != null && rsVO.getLinhas() != null) {

					if (rsVO.getLinhas().getLinhaArray().length > 0) {
						String totalCampos = rsVO.getLinhas().getLinhaArray(0).getValorArray(0);
						log.debug("[AdmFormulariosController.verificaExcluirForm] totalCampos" + totalCampos);
						if (ConstantesCRM.SZERO.equals(totalCampos)) {

							query = new StringBuffer(ConstantesCRM.SVAZIO);
							query.append("select count(idformulario)  from contatoadm.formularioencaminhafdrule where idformulario = ").append(form.getIdFormulario());

							log.debug(query);
							pesquisar = new Pesquisar();
							rsVO = pesquisar.executar(query.toString());
						}

						String[] stringsToDelete = new String[1];
						stringsToDelete[0] = " xmlns=\"cliente.fo.vivo.com.br/vo\"";
						retornoToXML = ClienteUtils.getCleanXMLFromXSD(rsVO, stringsToDelete);
					}
				}
			}
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			response.setContentType(ConstantesCRM.SContentType);
			bufferedOutputStream.write(retornoToXML.getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
		} catch (Exception e) {
			log.error("AdmFormulariosController::verificaExcluirForm", e);
		}
		return null;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="ListaFormularios.jsp"
	 */
	public ActionForward excluirForm(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			CamposDinamicosForm form = (CamposDinamicosForm) formParam;
			if (form.getIdFormulario() != null && !ConstantesCRM.SVAZIO.equals(form.getIdFormulario())) {
				Pesquisar pesquisar = new Pesquisar();
				StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);

				query = new StringBuffer(ConstantesCRM.SVAZIO);
				query.append("delete from contatoadm.formularioobjeto where idformulario = ").append(form.getIdFormulario());
				log.debug(query);
				Resultset rsVO = pesquisar.executar(query.toString());

				query = new StringBuffer(ConstantesCRM.SVAZIO);
				query.append("delete from contatoadm.formulario where idformulario = ").append(form.getIdFormulario());
				log.debug(query);
				rsVO = pesquisar.executar(query.toString());

				getCamposDinamicosForm().setListaFormularios(listaFormularios());
			}
		} catch (Exception e) {
			log.error("AdmFormulariosController::excluirForm", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void getListaClassificadorFrm() {
		try {
			StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
			query.append("select idclassificacaofrm, nmclassificacaofrm from apoio.classificacaofrm");

			log.debug(query);

			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = pesquisar.executar(query.toString());

			if (rsVO != null && rsVO.getLinhas() != null) {
				Disponivel classificacaoFrm = Disponivel.Factory.newInstance();
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					It it = classificacaoFrm.addNewIt();
					it.setId(rsVO.getLinhas().getLinhaArray(i).getValorArray(0));
					it.setDs(rsVO.getLinhas().getLinhaArray(i).getValorArray(1));
				}
				getCamposDinamicosForm().setLstClassificacaoFrm(classificacaoFrm);
			}
		} catch (Exception e) {
			log.error("AdmFormulariosController::loadCfgFormulario", e);
		}
	}

	private void getListaSubFormularios(Integer formId) {
		StringBuffer query = new StringBuffer();

		query.append("select s.idsubform ");
		query.append("  from contatoadm.subfrm s ");
		query.append("  join contatoadm.formularioobjeto o ");
		query.append("       on o.idsubform = s.idsubform ");
		query.append(" where o.idformulario = ").append(formId);

		log.debug(query);

		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO = pesquisar.executar(query.toString());

		if (rsVO != null && rsVO.getLinhas() != null) {
			for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
				subFormAdded.add(new Integer(rsVO.getLinhas().getLinhaArray(i).getValorArray(0)));
			}
		}
		getListaSubFormularios();
	}

	private void getListaSubFormularios() {
		try {
			StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
			query.append("select idsubform, nmsubformulario from contatoadm.subfrm ");

			Iterator subFormAddedIterator = subFormAdded.iterator();
			StringBuffer addedIdList = new StringBuffer();

			while (subFormAddedIterator.hasNext()) {
				Integer addedId = (Integer) subFormAddedIterator.next();

				if (addedIdList.length() > 0) {
					addedIdList.append(",");
				}
				addedIdList.append(addedId);
			}

			if (addedIdList.length() > 0) {
				query.append("where idsubform not in(").append(addedIdList.toString()).append(")");
			}

			log.debug(query);

			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = pesquisar.executar(query.toString());

			if (rsVO != null && rsVO.getLinhas() != null) {
				Disponivel subFrm = Disponivel.Factory.newInstance();
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					It it = subFrm.addNewIt();
					it.setId(rsVO.getLinhas().getLinhaArray(i).getValorArray(0));
					it.setDs(rsVO.getLinhas().getLinhaArray(i).getValorArray(1));
				}
				getCamposDinamicosForm().setLstSubFrm(subFrm);
			}
		} catch (Exception e) {
			log.error("AdmFormulariosController::loadCfgFormulario", e);
		}
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="ListaSubFormulario.jsp"
	 */
	public ActionForward atualizaListaSubFormularios(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		if (form.getIdSubForm().trim().length() > 0) {
			subFormAdded.add(new Integer(form.getIdSubForm()));
		}
		getListaSubFormularios();
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="visualizaForm.jsp"
	 */
	public ActionForward visualizaFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			CamposDinamicosForm form = (CamposDinamicosForm) formParam;
			if (form.getIdFormulario() != null && !ConstantesCRM.SVAZIO.equals(form.getIdFormulario())) {
				Pesquisar pesquisar = new Pesquisar();

				StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);

				query.append("select nvl(c.idcampo, sub.idcampo) idcampo, ");
				query.append("       nvl(c.nmcampo, sub.nmcampo) nmcampo, ");
				query.append("       nvl(l.sglayoutapresentacaocampo, sub.sglayoutapresentacaocampo) apcampo, ");
				query.append("       decode(c.idcampo, null, sub.inrequerido, fo.inrequerido) inrequerido, ");
				query.append("       nvl(c.lbcampo, sub.lbcampo) lbcampo, ");
				query.append("       nvl(m.sgmascaraapresencaocampo, sub.sgmascaraapresencaocampo) sgmascaraapresencaocampo ");
				query.append("  from contatoadm.formularioobjeto fo, ");
				query.append("       contatoadm.campo c, ");
				query.append("       apoio.layoutapresentacaocampo l, ");
				query.append("       apoio.mascaraapresentacaocampo m, ");
				query.append("       (select sfrm.idsubform, ");
				query.append("               c.idcampo, ");
				query.append("               c.nmcampo, ");
				query.append("               sfrmc.inrequerido, ");
				query.append("               sfrmc.sqsequenciaapresentacao, ");
				query.append("               l.sglayoutapresentacaocampo, ");
				query.append("               c.lbcampo, ");
				query.append("               m.sgmascaraapresencaocampo ");
				query.append("        from contatoadm.subfrm sfrm, ");
				query.append("             contatoadm.subfrmcampo sfrmc, ");
				query.append("             contatoadm.campo c, ");
				query.append("             apoio.layoutapresentacaocampo l, ");
				query.append("             apoio.tipodadocampo d,  ");
				query.append("             apoio.mascaraapresentacaocampo m ");
				query.append("        where sfrm.idsubform = sfrmc.idsubform ");
				query.append("          and sfrmc.idcampo = c.idcampo ");
				query.append("          and c.idtipodadocampo = d.idtipodadocampo ");
				query.append("          and c.idlayoutapresentacaocampo = l.idlayoutapresentacaocampo ");
				query.append("          and c.idmascaraapresencaocampo = m.idmascaraapresencaocampo) sub ");
				query.append(" where fo.idformulario = ").append(form.getIdFormulario());
				query.append("   and c.idlayoutapresentacaocampo = l.idlayoutapresentacaocampo(+) ");
				query.append("   and c.idmascaraapresencaocampo = m.idmascaraapresencaocampo(+) ");
				query.append("   and fo.idsubform = sub.idsubform(+) ");
				query.append("   and c.idcampo(+) = fo.idcampo ");
				query.append(" order by fo.sqsequenciaapresentacao,sub.sqsequenciaapresentacao ");

				log.debug("[AdmFormulariosController.visualizaFormulario] " + query);
				Resultset rsVO = pesquisar.executar(query.toString());

				if (rsVO != null && rsVO.getLinhas() != null) {
					ArrayList arrayList = new ArrayList();

					for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
						Campo campo = new Campo();
						campo.setNmCampo(rsVO.getLinhas().getLinhaArray(i).getValorArray(1));
						campo.setLayoutApresentacao(rsVO.getLinhas().getLinhaArray(i).getValorArray(2));
						campo.setRequerido(rsVO.getLinhas().getLinhaArray(i).getValorArray(3));
						campo.setMascaraApresentacao(rsVO.getLinhas().getLinhaArray(i).getValorArray(5));

						/*
						 * para efeito de retrocompatibilidade. campos antigos não possuem nome de apresentaçao, então
						 * sera usado o nome do campo
						 */
						if (rsVO.getLinhas().getLinhaArray(i).getValorArray(4) != null && rsVO.getLinhas().getLinhaArray(i).getValorArray(4).trim().length() > 0) {

							campo.setLbCampo(rsVO.getLinhas().getLinhaArray(i).getValorArray(4));
						} else {
							campo.setLbCampo(campo.getNmCampo());
						}

						if (ConstantesCRM.CT_Select.equals(rsVO.getLinhas().getLinhaArray(i).getValorArray(2)) || "SELECTMULTI".equals(rsVO.getLinhas().getLinhaArray(i).getValorArray(2)) || "LABEL".equals(rsVO.getLinhas().getLinhaArray(i).getValorArray(2))) {
							String idCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);

							query = new StringBuffer(ConstantesCRM.SVAZIO);
							query.append("select dsdominio from contatoadm.dominiocampo where idcampo = ").append(idCampo).append(" ORDER BY IDUSUARIOALTERACAO ");

							Resultset rs = pesquisar.executar(query.toString());

							if (rs != null && rs.getLinhas() != null) {
								Dominio[] dominio = new Dominio[rs.getLinhas().getLinhaArray().length];

								for (int j = 0; j < rs.getLinhas().getLinhaArray().length; j++) {
									dominio[j] = new Dominio();
									dominio[j].setVlDominio(rs.getLinhas().getLinhaArray(j).getValorArray(0));
								}

								campo.setDominio(dominio);
							}
						}
						arrayList.add(campo);
					}
					getCamposDinamicosForm().setListaCamposFormulario((Campo[]) arrayList.toArray(new Campo[0]));
				}
			}
		} catch (Exception e) {
			log.error("AdmFormulariosController::visualizaFormulario", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="cfgFormulario.jsp"
	 */
	public ActionForward loadCfgFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		subFormAdded.clear();
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		try {
			camposDinamicosForm = new CamposDinamicosForm();
			getRegrasEncaminhamento(null);

			listaCamposFormulario = Resultset.Factory.newInstance();
			listaCamposFormulario.addNewLinhas();

			getListaClassificadorFrm();
			getListaSubFormularios();

		} catch (Exception e) {
			log.error("AdmFormulariosController::loadCfgFormulario", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void getRegrasEncaminhamento(String idFormulario) {

		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO;
		String idFormularioQuery = (idFormulario != null && !ConstantesCRM.SVAZIO.equals(idFormulario)) ? idFormulario : "NULL";

		// Busca Regras de Encaminhamento (Fórmulas)
		StringBuffer query = new StringBuffer();
		query.append("   SELECT ENCAMINHAFDRULE.IDENCAMINHAFDRULE, ").append("          ENCAMINHAFDRULE.NMFORMULA, ").append("          (SELECT COUNT(1) ").append("             FROM CONTATOADM.FORMULARIOENCAMINHAFDRULE FEFDRULE ").append("            WHERE FEFDRULE.IDENCAMINHAFDRULE = ENCAMINHAFDRULE.IDENCAMINHAFDRULE ").append("              AND FEFDRULE.IDFORMULARIO = ").append(idFormularioQuery).append(") AS ASSOCIADA ").append("     FROM APOIO.ENCAMINHAFDRULE ENCAMINHAFDRULE ")
		.append("    WHERE ROWNUM <= 100 ").append(" ORDER BY ENCAMINHAFDRULE.NMFORMULA ");

		rsVO = pesquisar.executar(query.toString());
		if (rsVO != null && rsVO.getLinhas() != null) {
			int qtItens = rsVO.getLinhas().getLinhaArray().length;
			RegrasEncaminhamentoDisponivelVO[] regras = new RegrasEncaminhamentoDisponivelVO[qtItens];
			for (int i = 0; i < qtItens; i++) {
				regras[i] = RegrasEncaminhamentoDisponivelVO.Factory.newInstance();
				regras[i].setIdRegraEncaminhamento(rsVO.getLinhas().getLinhaArray(i).getValorArray(0));
				regras[i].setNmRegraEncaminhamento(rsVO.getLinhas().getLinhaArray(i).getValorArray(1));
				regras[i].setInAssociada(Integer.parseInt(rsVO.getLinhas().getLinhaArray(i).getValorArray(2)) == 1 ? true : false);
			}
			getCamposDinamicosForm().setRegrasEncaminhamento(regras);
		}
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="cfgFormulario.jsp"
	 */
	public ActionForward loadCfgFormularioAlterar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		subFormAdded.clear();
		StringBuffer query = new StringBuffer();
		log.debug("[AdmFormulariosController.loadCfgFormularioAlterar] Duplicar " + form.getDuplicarFormulario() + " Formulario " + form.getIdFormulario());
		getCamposDinamicosForm().setDuplicarFormulario(form.getDuplicarFormulario());
		try {
			Pesquisar pesquisar = new Pesquisar();

			Resultset rsVO;

			getRegrasEncaminhamento(form.getIdFormulario());

			if (form.getIdFormulario() != null && !ConstantesCRM.SVAZIO.equals(form.getIdFormulario())) {

				query = new StringBuffer(ConstantesCRM.SVAZIO);
				query.append("select idclassificacaofrm, nmformulario from contatoadm.formulario where idformulario = ").append(form.getIdFormulario());

				log.debug(query);

				rsVO = pesquisar.executar(query.toString());

				if (rsVO != null && rsVO.getLinhas() != null) {
					getCamposDinamicosForm().setIdFormulario(form.getIdFormulario());
					getCamposDinamicosForm().setIdClassificador(rsVO.getLinhas().getLinhaArray(0).getValorArray(0));
					getCamposDinamicosForm().setNmFormulario(rsVO.getLinhas().getLinhaArray(0).getValorArray(1));

					query = new StringBuffer(ConstantesCRM.SVAZIO);

					query.append("select c.idcampo, sfrm.idsubform, ");
					query.append("       nvl(c.nmcampo, sfrm.nmsubformulario) nmcampo, ");
					query.append("       nvl(t.nmtipodadocampo, 'Sub-formulário') nmtipodadocampo, ");
					query.append("       fo.inrequerido ");
					query.append("  from contatoadm.formularioobjeto fo ");
					query.append("  left join contatoadm.campo c ");
					query.append("       on fo.idcampo = c.idcampo ");
					query.append("  left join contatoadm.subfrm sfrm ");
					query.append("       on fo.idsubform = sfrm.idsubform ");
					query.append("  left join apoio.tipodadocampo t ");
					query.append("       on c.idtipodadocampo = t.idtipodadocampo ");
					query.append(" where fo.idformulario = ").append(form.getIdFormulario());
					query.append(" order by fo.sqsequenciaapresentacao ");

					rsVO = pesquisar.executar(query.toString());

					listaCamposFormulario = Resultset.Factory.newInstance();
					listaCamposFormulario.addNewLinhas();

					if (rsVO != null && rsVO.getLinhas() != null) {
						int qtItens = rsVO.getLinhas().getLinhaArray().length;
						getCamposDinamicosForm().setQtCamposFrm(String.valueOf(qtItens));

						for (int i = 0; i < qtItens; i++) {
							String idCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
							String idSubForm = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
							String nmCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(2);
							String inRequerido = rsVO.getLinhas().getLinhaArray(i).getValorArray(4);
							String checked = ConstantesCRM.SVAZIO;

							if (ConstantesCRM.SONE.equals(inRequerido)) {
								checked = " checked ";
							}

							String tipoCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(3);

							if (idSubForm != null && idSubForm.trim().length() > 0) {
								checked += " disabled";
							}

							String[] valores = { idCampo, ConstantesCRM.SVAZIO, nmCampo, inRequerido, checked, idSubForm, tipoCampo };
							Linha linha = listaCamposFormulario.getLinhas().addNewLinha();
							linha.setValorArray(valores);
						}
					}

					getListaSubFormularios(new Integer(form.getIdFormulario()));
					getListaClassificadorFrm();
					getCamposDinamicosForm().setAction("A");
				}
			}
		} catch (Exception e) {
			log.error("AdmFormulariosController::loadCfgFormulario", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 */
	public ActionForward loadCamposExistentes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		if (!ConstantesCRM.SVAZIO.equals(form.getTpCampo())) {
			try {
				Linha[] linhas = listaCamposFormulario.getLinhas().getLinhaArray();
				StringBuffer valores = new StringBuffer();

				for (int i = 0; i < linhas.length; i++) {
					if ((linhas[i].getValorArray(0)) != null) {
						if (valores.length() > 0) {
							valores.append(",");
						}

						valores.append(linhas[i].getValorArray(0));
					}
				}

				StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);

				log.debug("[AdmFormulariosController.loadCamposExistentes] IdSubFormulario " + form.getIdSubFormulario());

				/* Obtem campos disponíveis */
				query.append("select idcampo, nmCampo from contatoadm.campo c, apoio.tipodadocampo tdc, contatoadm.campoclassificador cc ");
				query.append("where c.idclassificadorcampo = cc.idclassificadorcampo ");
				query.append("and cc.nmclassificadorcampo='VOLE' and c.indisponibilidade=1 ");
				query.append("and tdc.idtipodadocampo = c.idtipodadocampo ");
				query.append("and tdc.sgtipodadocampo = '").append(form.getTpCampo()).append("' ");

				if (valores.length() > 0) {
					query.append("and c.idcampo not in (").append(valores).append(")");
				}

				log.debug("[AdmFormulariosController.loadCamposExistentes] query " + query);

				Pesquisar pesquisar = new Pesquisar();
				Resultset rsVO = pesquisar.executar(query.toString());

				if (rsVO != null && rsVO.getLinhas() != null) {
					String[] stringsToDelete = new String[1];
					stringsToDelete[0] = " xmlns=\"cliente.fo.vivo.com.br/vo\"";
					String retornoToXML = ClienteUtils.getCleanXMLFromXSD(rsVO, stringsToDelete);

					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
					response.setContentType(ConstantesCRM.SContentType);
					bufferedOutputStream.write(retornoToXML.getBytes(ConstantesCRM.SISO));
					bufferedOutputStream.flush();
					bufferedOutputStream.close();
				}
			} catch (Exception e) {
				log.error("AdmFormulariosController::loadCamposExistentes", e);
			}
		}
		return null;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 */
	public ActionForward loadCamposAssociados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		if (!ConstantesCRM.SVAZIO.equals(form.getIdSubFormulario())) {
			try {

				StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);

				log.debug("[AdmFormulariosController.loadCamposAssociados] IdSubFormulario " + form.getIdSubFormulario());
				/* Obtem campos associados ao sub-fomulário */
				query.append("SELECT C.IDCAMPO, C.NMCAMPO, TDC.NMTIPODADOCAMPO, SC.INREQUERIDO  ");
				query.append("FROM   CONTATOADM.CAMPO C, APOIO.TIPODADOCAMPO TDC,  ");
				query.append("       CONTATOADM.CAMPOCLASSIFICADOR CC, ");
				query.append("       CONTATOADM.SUBFRMCAMPO SC  ");
				query.append("WHERE  C.IDCLASSIFICADORCAMPO = CC.IDCLASSIFICADORCAMPO ");
				query.append("       AND CC.NMCLASSIFICADORCAMPO ='VOLE' ");
				query.append("       AND C.INDISPONIBILIDADE =1 ");
				query.append("       AND TDC.IDTIPODADOCAMPO = C.IDTIPODADOCAMPO ");
				query.append("       AND C.IDCAMPO = SC.IDCAMPO ");
				query.append("       AND SC.IDSUBFORM = ").append(form.getIdSubFormulario()).append(" ");
				query.append("ORDER BY SC.SQSEQUENCIAAPRESENTACAO ");

				// log.debug("[AdmFormulariosController.loadCamposAssociados] query " + query);

				Pesquisar pesquisar = new Pesquisar();
				Resultset rsVO = pesquisar.executar(query.toString());

				if (rsVO != null && rsVO.getLinhas() != null) {
					String[] stringsToDelete = new String[1];
					stringsToDelete[0] = " xmlns=\"cliente.fo.vivo.com.br/vo\"";
					String retornoToXML = ClienteUtils.getCleanXMLFromXSD(rsVO, stringsToDelete);

					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
					response.setContentType(ConstantesCRM.SContentType);
					bufferedOutputStream.write(retornoToXML.getBytes(ConstantesCRM.SISO));
					bufferedOutputStream.flush();
					bufferedOutputStream.close();
				}
			} catch (Exception e) {
				log.error("AdmFormulariosController::loadCamposAssociados", e);
			}
		}
		return null;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="ListaCamposFormulario.jsp"
	 */
	public ActionForward atualizarObrigatoriedade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		Linha[] linhas = listaCamposFormulario.getLinhas().getLinhaArray();
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		for (int i = 0; i < linhas.length; i++) {
			/* sub-formulario não altera obrigatoriedade. */
			/* check está desabilitado. */
			if (linhas[i].getValorArray(0) != null) {
				if (linhas[i].getValorArray(0).equals(form.getIdCampo())) {
					if (linhas[i].getValorArray(3).equals(ConstantesCRM.SZERO)) {
						linhas[i].setValorArray(3, ConstantesCRM.SONE);
						linhas[i].setValorArray(4, "checked");
					} else {
						linhas[i].setValorArray(3, ConstantesCRM.SZERO);
						linhas[i].setValorArray(4, ConstantesCRM.SVAZIO);
					}
				}
			}
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="ListaCamposFormulario.jsp"
	 */
	public ActionForward removeItemFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		if (!ConstantesCRM.SVAZIO.equals(request.getParameter("nPos"))) {
			try {
				int nPos = Integer.parseInt(request.getParameter("nPos"));
				Linha[] linhas = listaCamposFormulario.getLinhas().getLinhaArray();
				Linha linha = linhas[nPos];

				if (isFromSubFrom(linha.getValorArray())) {
					Integer idSubForm = new Integer(linha.getValorArray(5));

					if (subFormAdded.contains(idSubForm)) {
						subFormAdded.remove(idSubForm);
					}

					for (int i = 0; i < linhas.length; i++) {
						if (linhas[i].getValorArray(5) != null) {
							Integer idFromLinhas = new Integer(linhas[i].getValorArray(5));

							if (idFromLinhas.equals(idSubForm)) {
								listaCamposFormulario.getLinhas().removeLinha(nPos);
							}
						}
					}
				} else {
					listaCamposFormulario.getLinhas().removeLinha(nPos);
				}

			} catch (Exception e) {
				log.error("AdmFormulariosController::loadCamposExistentes", e);
			}
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/*
	 * a quinta posição da linha é usada para armazenar o idSubForm. se a posiçao estiver em branca, então não é uma
	 * linha de subform. se a posição estiver com algum valor, então é uma linha de subform.
	 */
	private boolean isFromSubFrom(String[] linha) {
		return (linha[5] != null && linha[5].length() > 0);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="ListaCamposFormulario.jsp"
	 */
	public ActionForward loadCamposFormulario2Add(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String apCampo = ConstantesCRM.SVAZIO;
		String layCampo = ConstantesCRM.SVAZIO;
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;

		if ("T".equals(form.getTpCampo())) {
			apCampo = ConstantesCRM.CT_Text;
			layCampo = "Texto";
		} else if ("S".equals(form.getTpCampo())) {
			apCampo = ConstantesCRM.CT_Select;
			layCampo = "Combo";
		} else if ("SM".equals(form.getTpCampo())) {
			apCampo = "SELECTMULTI";
			layCampo = "Lista seleção múltipla";
		} else if ("L".equals(form.getTpCampo())) {
			apCampo = "LABEL";
			layCampo = "Label";
		} else if ("M".equals(form.getTpCampo())) {
			apCampo = "MEMO";
			layCampo = "Memo";
		}

		if (form.getIdCampo() != null && !ConstantesCRM.SVAZIO.equals(form.getIdCampo())) {

			// terceiro item = 0 (campo não é obrigatório.
			// quarto item = "" (campo não esta checked na página)
			// quinto item = "" (só é setado com algum valor caso o campo seja de um subform.
			String[] valores = { form.getIdCampo(), apCampo, form.getNmCampo(), ConstantesCRM.SZERO, ConstantesCRM.SVAZIO, ConstantesCRM.SVAZIO, layCampo };
			Linha linha = listaCamposFormulario.getLinhas().addNewLinha();
			linha.setValorArray(valores);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="ListaCamposFormulario.jsp"
	 */
	public ActionForward loadCamposSubFormulario2Add(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		Integer idSubForm = new Integer(form.getIdSubForm());
		List campos = loadSubFormData(idSubForm);
		Iterator i = campos.iterator();

		subFormAdded.add(idSubForm);

		while (i.hasNext()) {
			String[] valores = (String[]) i.next();

			Linha linha = listaCamposFormulario.getLinhas().addNewLinha();
			linha.setValorArray(valores);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private List loadSubFormData(Integer idSubForm) {
		List listaSubForm = new ArrayList();
		StringBuffer query = new StringBuffer();

		query.append("select null idcampo, sfrm.idsubform, ");
		query.append("       sfrm.nmsubformulario nmcampo, ");
		query.append("       'Sub-formulário' nmtipodadocampo, ");
		query.append("       '0' inrequerido ");
		query.append("  from contatoadm.subfrm sfrm ");
		query.append(" where sfrm.idsubform = ").append(idSubForm);

		log.debug(query);

		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO = pesquisar.executar(query.toString());

		if (rsVO != null && rsVO.getLinhas() != null) {
			Linha[] linhas = rsVO.getLinhas().getLinhaArray();

			for (int i = 0; i < linhas.length; i++) {
				String idCampo = linhas[i].getValorArray(0);
				String nmCampo = linhas[i].getValorArray(2);
				String tipoCampo = linhas[i].getValorArray(3);
				String inRequerido = linhas[i].getValorArray(4);
				String checked = ConstantesCRM.SVAZIO;

				if (ConstantesCRM.SONE.equals(inRequerido)) {
					checked = " checked ";
				}

				checked += " disabled";

				String[] valores = { idCampo, ConstantesCRM.SVAZIO, nmCampo, inRequerido, checked, idSubForm.toString(), tipoCampo };

				listaSubForm.add(valores);
			}
		}

		return listaSubForm;
	}

	private List loadCamposBySubForm(Integer idSubForm) {
		List listaCampos = new ArrayList();
		StringBuffer query = new StringBuffer();

		query.append("select c.idcampo, l.sglayoutapresentacaocampo, c.nmcampo, ");
		query.append("       s.inrequerido, ");
		query.append("       case s.inrequerido ");
		query.append("            when 0 then 'disabled' else 'checked disabled' ");
		query.append("       end, s.idsubform, ");
		query.append("       t.nmtipodadocampo ");
		query.append("  from contatoadm.subfrmcampo s ");
		query.append("  join contatoadm.campo c ");
		query.append("       on c.idcampo = s.idcampo ");
		query.append("  join apoio.layoutapresentacaocampo l ");
		query.append("       on l.idlayoutapresentacaocampo = c.idlayoutapresentacaocampo ");
		query.append("  join apoio.tipodadocampo t ");
		query.append("       on t.idtipodadocampo = c.idtipodadocampo ");
		query.append(" where s.idsubform = ").append(idSubForm);
		query.append(" order by s.sqsequenciaapresentacao ");

		log.debug(query);

		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO = pesquisar.executar(query.toString());

		if (rsVO != null && rsVO.getLinhas() != null) {
			Linha[] linhas = rsVO.getLinhas().getLinhaArray();

			for (int i = 0; i < linhas.length; i++) {
				listaCampos.add(linhas[i].getValorArray());
			}
		}

		return listaCampos;
	}

	public Resultset getListaCamposFormulario() {
		return listaCamposFormulario;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="cfgFormulario.jsp"
	 * @jpf:ActionForward name="duplicar" path="loadCfgFormularioAlterar.do"
	 * @jpf:ActionForward name="error" path="/error.jsp"
	 */
	public ActionForward gravarFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		String idFormularioCriado = form.getIdFormulario();
		if (form.getNmFormulario() != null && !ConstantesCRM.SVAZIO.equals(form.getNmFormulario())) {

			StringBuffer query = new StringBuffer();
			Pesquisar pesquisar = new Pesquisar();

			/* Não permitir duplicar o nome */
			query.append("SELECT IDFORMULARIO  FROM CONTATOADM.FORMULARIO WHERE NMFORMULARIO = '").append(form.getNmFormulario()).append("' ");

			Resultset rsVOPesquisa = pesquisar.executar(query.toString());
			if (rsVOPesquisa != null && rsVOPesquisa.getLinhas().getLinhaArray().length > 0) {
				request.setAttribute("msgErro", "Formulário já existe, favor inserir outro nome.");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			} else {
				request.removeAttribute("msgErro");
			}

			query = new StringBuffer("SELECT CONTATOADM.FORMULARIOSQ.NEXTVAL  FROM DUAL");
			Resultset rsVO = pesquisar.executar(query.toString());
			if (rsVO != null && rsVO.getLinhas() != null && rsVO.getLinhas().getLinhaArray().length > 0) {
				try {
					String idForm = rsVO.getLinhas().getLinhaArray(0).getValorArray(0);
					String idClassificador = form.getIdClassificador();

					query = new StringBuffer("");
					query.append("INSERT INTO CONTATOADM.FORMULARIO (  IDFORMULARIO, NMFORMULARIO, IDCLASSIFICACAOFRM, IDUSUARIOALTERACAO, DTULTIMAALTERACAO ");
					query.append(") VALUES (  ");
					query.append(idForm).append(",'").append(form.getNmFormulario()).append("',").append(idClassificador).append(", 1, sysdate)");

					log.debug("[AdmFormularioController.gravarFormulario] " + query);

					pesquisar = new Pesquisar();
					rsVO = pesquisar.executar(query.toString());

					if (rsVO != null && rsVO.getMsg() != null) {
						log.error("[AdmFormulariosController.gravarFormulario] Erro Formulário " + rsVO.getMsg());
						throw new Exception("Problemas na inclusão do formulário");
					}

					for (int i = 0; i < listaCamposFormulario.getLinhas().getLinhaArray().length; i++) {
						Linha linha = listaCamposFormulario.getLinhas().getLinhaArray(i);

						String idCampo = linha.getValorArray(0);
						String idSubForm = linha.getValorArray(5);
						String inRequerido = linha.getValorArray(3);

						if (StringUtils.isNotEmpty(idSubForm)) {
							idCampo = "null";
						} else {
							idSubForm = "null";
						}

						query = new StringBuffer(ConstantesCRM.SVAZIO);
						query.append("insert into contatoadm.formularioobjeto (idformulario, idsubform, idcampo, idusuarioalteracao, dtultimaalteracao, sqsequenciaapresentacao, inrequerido) values (").append(idForm).append(",").append(idSubForm).append(",").append(idCampo).append(",1,sysdate,").append(i).append(",").append(inRequerido).append(")");

						log.debug("[AdmFormularioController.gravarFormulario] " + query);

						rsVO = pesquisar.executar(query.toString());

						if (rsVO != null && rsVO.getMsg() != null) {
							log.error("[AdmFormulariosController.gravarFormulario] Erro FormulárioObjeto " + rsVO.getMsg());
							throw new Exception("Problemas na inclusão do campos do formulário");
						}
					}

					idFormularioCriado = idForm;
				} catch (Exception e) {
					request.setAttribute("msgError", "Problemas na inclusão dos campos do formulário");

					// Seta FormBean de Erro.
					FormError formError = globalApp.buildFormError(e, request);
					request.setAttribute(ConstantesCRM.SFORMERROR, formError);
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SERROR);
				}
			}
			listaCamposFormulario = Resultset.Factory.newInstance();
			listaCamposFormulario.addNewLinhas();
			request.setAttribute("msgRetorno", ConstantesCRM.SOK);
		}

		ActionForward actionForward = mapping.findForward(ConstantesCRM.SUCCESS);

		if ("true".equalsIgnoreCase(form.getDuplicarFormulario())) {
			log.debug("[AdmFormularioController.gravarFormulario] idFormularioCriado " + idFormularioCriado);
			getCamposDinamicosForm().setIdFormulario(idFormularioCriado);
			getCamposDinamicosForm().setDuplicarFormulario("false");
			request.setAttribute("camposDinamicosForm", getCamposDinamicosForm());
			actionForward = mapping.findForward("duplicar");
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return actionForward;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="cfgFormulario.jsp"
	 * @jpf:ActionForward name="error" path="/error.jsp"
	 */
	public ActionForward alteraFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		if (form.getIdFormulario() != null && !ConstantesCRM.SVAZIO.equals(form.getIdFormulario())) {
			getCamposDinamicosForm().setIdClassificador(form.getIdClassificador());
			getCamposDinamicosForm().setIdFormulario(form.getIdFormulario());

			try {
				Pesquisar pesquisar = new Pesquisar();
				StringBuffer query = new StringBuffer("update contatoadm.formulario set idclassificacaofrm = ").append(form.getIdClassificador()).append(" where idformulario = ").append(form.getIdFormulario());
				log.debug(query);
				Resultset rsVO = pesquisar.executar(query.toString());

				query = new StringBuffer(ConstantesCRM.SVAZIO);
				query.append("delete from contatoadm.formularioobjeto where idformulario = ").append(form.getIdFormulario());

				log.debug(query);

				rsVO = pesquisar.executar(query.toString());

				if (rsVO != null && rsVO.getMsg() != null) {
					log.error("[AdmFormulariosController.incluirSubFormulario] Erro FormularioObjeto " + rsVO.getMsg());
					throw new Exception("Problemas na exclusão dos campos do formulário");
				}

				String idForm = form.getIdFormulario();

				int qtItens = listaCamposFormulario.getLinhas().getLinhaArray().length;

				for (int i = 0; i < qtItens; i++) {
					Linha linha = listaCamposFormulario.getLinhas().getLinhaArray(i);

					String idCampo = linha.getValorArray(0);
					String idSubForm = linha.getValorArray(5);
					String inRequerido = linha.getValorArray(3);

					if (idSubForm != null && idSubForm.trim().length() > 0) {
						idCampo = "null";
					} else {
						idSubForm = "null";
					}

					query = new StringBuffer(ConstantesCRM.SVAZIO);
					query.append("insert into contatoadm.formularioobjeto (idformulario, idsubform, idcampo, idusuarioalteracao, dtultimaalteracao, sqsequenciaapresentacao, inrequerido) values (").append(idForm).append(",").append(idSubForm).append(",").append(idCampo).append(",1,sysdate,").append(i).append(",").append(inRequerido).append(")");

					rsVO = pesquisar.executar(query.toString());

					if (rsVO != null && rsVO.getMsg() != null) {
						log.error("[AdmFormulariosController.incluirSubFormulario] Erro FormularioObjeto " + rsVO.getMsg());
						throw new Exception("Problemas na inclusão dos campos do formulário");
					}
				}

				getCamposDinamicosForm().setQtCamposFrm(String.valueOf(qtItens));
			} catch (Exception e) {
				request.setAttribute("msgError", "Problemas na alteração do formulário");

				// Seta FormBean de Erro.
				FormError formError = globalApp.buildFormError(e, request);
				request.setAttribute(ConstantesCRM.SFORMERROR, formError);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			}
			request.setAttribute("msgRetorno", ConstantesCRM.SOK);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="assFormulario.jsp"
	 */
	public ActionForward loadAssFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			CamposDinamicosForm form = (CamposDinamicosForm) formParam;
			camposDinamicosForm = new CamposDinamicosForm();

			Pesquisar pesquisar = new Pesquisar();
			StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);

			query = new StringBuffer("select idfuncionalidade, nmfuncionalidade from contatoadm.funcionalidade order by upper(nmfuncionalidade) ");

			log.debug(query);
			Resultset rsVO = pesquisar.executar(query.toString());

			if (rsVO != null && rsVO.getLinhas() != null) {
				Disponivel lista = Disponivel.Factory.newInstance();
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					It it = lista.addNewIt();
					it.setId(rsVO.getLinhas().getLinhaArray(i).getValorArray(0));
					it.setDs(rsVO.getLinhas().getLinhaArray(i).getValorArray(1));
				}
				getCamposDinamicosForm().setLstFuncionalidade(lista);
			}
			/* Buscar lsita Prazo Atendimento */
			getCamposDinamicosForm().setListaPrazoAtendimento(carregarListaPrazoAtendimento());
		} catch (Exception e) {
			log.error("AdmFormulariosController::loadAssFormulario", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 */
	public ActionForward loadFormsDisponiveis(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		if (!ConstantesCRM.SVAZIO.equals(form.getIdFuncionalidade())) {
			try {
				/* Inicicao Objeto Formulario Funcionalidade */
				getFormularioFuncionalidade().setFuncionalidade(new Funcionalidade());
				getFormularioFuncionalidade().getFuncionalidade().setId(new Integer(form.getIdFuncionalidade()));

				StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
				query.append("select f.idformulario, f.nmformulario from contatoadm.formulario f  ");
				query.append("where idformulario not in (select idformulario from contatoadm.funcionalidadefrm ");
				query.append("where idfuncionalidade = ").append(form.getIdFuncionalidade()).append(" ) ");

				log.debug(query);

				Pesquisar pesquisar = new Pesquisar();
				Resultset rsVO = pesquisar.executar(query.toString());

				if (rsVO != null && rsVO.getLinhas() != null) {
					String[] stringsToDelete = new String[1];
					stringsToDelete[0] = " xmlns=\"cliente.fo.vivo.com.br/vo\"";
					String retornoToXML = ClienteUtils.getCleanXMLFromXSD(rsVO, stringsToDelete);

					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
					response.setContentType(ConstantesCRM.SContentType);
					bufferedOutputStream.write(retornoToXML.getBytes(ConstantesCRM.SISO));
					bufferedOutputStream.flush();
					bufferedOutputStream.close();
				}
			} catch (Exception e) {
				log.error("AdmFormulariosController::loadFormsExistentes", e);
			}
		}
		return null;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 */
	public ActionForward loadFormsAssociados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		if (!ConstantesCRM.SVAZIO.equals(form.getIdFuncionalidade())) {
			try {
				StringBuffer query = new StringBuffer();

				query.append("SELECT F.IDFORMULARIO, F.NMFORMULARIO, S.IDSLAFUNCIONALIDADEFRM, S.DESCRICAOSLA,  S.VALORSLA  ");
				query.append("FROM   CONTATOADM.FORMULARIO F, CONTATOADM.FUNCIONALIDADEFRM FFRM, CONTATOADM.SLAFUNCIONALIDADEFRM S ");
				query.append("WHERE  F.IDFORMULARIO = FFRM.IDFORMULARIO ");
				query.append("       AND FFRM.IDFUNCIONALIDADE = ").append(form.getIdFuncionalidade());
				query.append("       AND FFRM.IDSLAFUNCIONALIDADEFRM = S.IDSLAFUNCIONALIDADEFRM ");

				log.debug("[AdmFormulariosController.loadFormsExistentes] query " + query);

				Pesquisar pesquisar = new Pesquisar();
				Resultset rsVO = pesquisar.executar(query.toString());

				if (rsVO != null && rsVO.getLinhas() != null) {

					/* Carregar Formularios Associados a Funcionalidade */
					ArrayList listaFormulario = new ArrayList();
					log.error("[AdmFormulariosController::loadFormsAssociados] Criando Formulários Novamente ");

					for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {

						Integer idFormulario = new Integer(rsVO.getLinhas().getLinhaArray(i).getValorArray(0));
						String nmFormulario = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
						Integer idSla = new Integer(rsVO.getLinhas().getLinhaArray(i).getValorArray(2));
						String nmSla = rsVO.getLinhas().getLinhaArray(i).getValorArray(3);
						String valorSla = rsVO.getLinhas().getLinhaArray(i).getValorArray(4);

						SLA slaFormulario = new SLA(idSla, nmSla);
						Formulario formulario = new Formulario(idFormulario, nmFormulario);

						formulario.setSLA(slaFormulario);
						listaFormulario.add(formulario);
					}
					getFormularioFuncionalidade().setListaFormulario(listaFormulario);

					String[] stringsToDelete = new String[1];
					stringsToDelete[0] = " xmlns=\"cliente.fo.vivo.com.br/vo\"";
					String retornoToXML = ClienteUtils.getCleanXMLFromXSD(rsVO, stringsToDelete);

					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
					response.setContentType(ConstantesCRM.SContentType);
					bufferedOutputStream.write(retornoToXML.getBytes(ConstantesCRM.SISO));
					bufferedOutputStream.flush();
					bufferedOutputStream.close();
				}
			} catch (Exception e) {
				log.error("AdmFormulariosController::loadFormsAssociados", e);
			}
		}
		return null;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 */
	public ActionForward loadDadosFuncionalidadeFrm(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		if (!ConstantesCRM.SVAZIO.equals(form.getIdFuncionalidade())) {
			try {
				StringBuffer query = new StringBuffer("select distinct ffrm.idcontato, ct.nmpath from ");
				query.append("contatoadm.funcionalidadefrm ffrm, ");
				query.append("contatoadm.contato ct ");
				query.append("where ffrm.idfuncionalidade = ").append(form.getIdFuncionalidade());
				query.append("  and ffrm.idcontato = ct.idcontato (+) ");

				log.debug(query);

				Pesquisar pesquisar = new Pesquisar();
				Resultset rsVO = pesquisar.executar(query.toString());

				if (rsVO != null && rsVO.getLinhas() != null) {
					String[] stringsToDelete = new String[1];
					stringsToDelete[0] = " xmlns=\"cliente.fo.vivo.com.br/vo\"";
					String retornoToXML = ClienteUtils.getCleanXMLFromXSD(rsVO, stringsToDelete);

					BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
					response.setContentType(ConstantesCRM.SContentType);
					bufferedOutputStream.write(retornoToXML.getBytes(ConstantesCRM.SISO));
					bufferedOutputStream.flush();
					bufferedOutputStream.close();
				}
			} catch (Exception e) {
				log.error("AdmFormulariosController::loadDadosFuncionalidadeFrm", e);
			}
		}
		return null;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="loadAssFormulario.do"
	 */
	public ActionForward gravarAssFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			CamposDinamicosForm form = (CamposDinamicosForm) formParam;
			if (form.getIdFuncionalidade() != null && !ConstantesCRM.SVAZIO.equals(form.getIdFuncionalidade())) {
				Pesquisar pesquisar = new Pesquisar();
				StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
				query.append("delete from contatoadm.funcionalidadefrm where idfuncionalidade = ").append(form.getIdFuncionalidade());
				log.debug(query);
				Resultset rsVO = pesquisar.executar(query.toString());

				if (form.getFrmSelecionados().length > 0) {
					for (int i = 0; i < form.getFrmSelecionados().length; i++) {
						query = new StringBuffer(ConstantesCRM.SVAZIO);
						query.append("insert into contatoadm.funcionalidadefrm (idfuncionalidade, idformulario, inativo, idcontato, idusuarioalteracao, dtultimaalteracao) ");
						query.append("values (").append(form.getIdFuncionalidade()).append(",").append(form.getFrmSelecionados()[i]).append(",").append(i == 0 ? "1" : "0").append(",").append(form.getIdContato()).append(",1,sysdate) ");
						rsVO = pesquisar.executar(query.toString());
					}
					camposDinamicosForm = new CamposDinamicosForm();
				}
				request.setAttribute("msgRetorno", ConstantesCRM.SOK);
			}
		} catch (Exception e) {
			log.error("AdmFormulariosController::gravarAssFormulario", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="manSubFormulario.jsp"
	 */
	public ActionForward loadManSubFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			camposDinamicosForm = new CamposDinamicosForm();
			getCamposDinamicosForm().setListaSubFormularios(listaSubFormularios());
			listaCamposFormulario = Resultset.Factory.newInstance();
			listaCamposFormulario.addNewLinhas();

		} catch (Exception e) {
			log.error("AdmFormulariosController::loadManSubFormulario", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Busca lista de sub-formulários
	 */
	private Resultset listaSubFormularios() {
		Resultset rsVO = null;
		try {
			StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
			query.append("SELECT IDSUBFORM, NMSUBFORMULARIO FROM CONTATOADM.SUBFRM ORDER BY NMSUBFORMULARIO");

			Pesquisar pesquisar = new Pesquisar();
			rsVO = pesquisar.executar(query.toString());

		} catch (Exception e) {
			rsVO = Resultset.Factory.newInstance();
			rsVO.addNewLinhas();
			log.error("AdmFormulariosController::listaSubFormularios", e);
		}
		return rsVO;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="listaCamposSubFormulario.jsp"
	 * @jpf:ActionForward name="error" path="listaCamposSubFormulario.jsp"
	 */
	public ActionForward loadConfigCamposSubFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		String idSubFormularioSelecionado = request.getParameter("idSubFormularioSelecionado");
		String idCamposSelecionados = request.getParameter("listaCamposSecionados");

		Collection colCamposSubFormulario = new ArrayList();
		HashMap hashOrdemCampos = new HashMap();
		StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);

		log.debug("[AdmFormulariosController.loadConfigCamposSubFormulario] idSubFormularioSelecionado " + idSubFormularioSelecionado + " idCamposSelecionados " + idCamposSelecionados);
		Resultset rsVO = null;
		try {
			if (StringUtils.isEmpty(idCamposSelecionados)) {
				request.setAttribute("errorMsg", "Ao menos um campo deve estar na lista de campos associados.");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SERROR);
			} else {

				/* Obtém a ordem dos campos selecionados definida pelo usuário */
				StringTokenizer listaSelecionadosToken = new StringTokenizer(idCamposSelecionados, ",");

				for (int i = 0; listaSelecionadosToken.hasMoreTokens(); i++) {
					String idCampoSelecionado = listaSelecionadosToken.nextToken().trim();
					hashOrdemCampos.put(idCampoSelecionado, new Integer(i));
				}

				if (!StringUtils.isEmpty(idSubFormularioSelecionado)) {

					/* Busca os campos e caso ele já esteja configurado no fomulário , retorna inrequerido */
					query.append("SELECT C.IDCAMPO, C.NMCAMPO, TDC.NMTIPODADOCAMPO, SC.INREQUERIDO, SC.SQSEQUENCIAAPRESENTACAO ");
					query.append("FROM   CONTATOADM.CAMPO C, APOIO.TIPODADOCAMPO TDC, ");
					query.append("       CONTATOADM.CAMPOCLASSIFICADOR CC, CONTATOADM.SUBFRMCAMPO SC ");
					query.append("WHERE  C.IDCLASSIFICADORCAMPO = CC.IDCLASSIFICADORCAMPO ");
					query.append("       AND CC.NMCLASSIFICADORCAMPO ='VOLE' ");
					query.append("       AND C.INDISPONIBILIDADE = 1 ");
					query.append("       AND TDC.IDTIPODADOCAMPO = C.IDTIPODADOCAMPO ");
					query.append("       AND C.IDCAMPO IN (").append(idCamposSelecionados).append(") ");
					query.append("       AND C.IDCAMPO = SC.IDCAMPO (+) ");
					query.append("       AND ").append(idSubFormularioSelecionado).append(" = SC.IDSUBFORM (+) ");

				} else {
					query.append("SELECT C.IDCAMPO, C.NMCAMPO, TDC.NMTIPODADOCAMPO ");
					query.append("FROM   CONTATOADM.CAMPO C, APOIO.TIPODADOCAMPO TDC, ");
					query.append("       CONTATOADM.CAMPOCLASSIFICADOR CC ");
					query.append("WHERE  C.IDCLASSIFICADORCAMPO = CC.IDCLASSIFICADORCAMPO ");
					query.append("       AND CC.NMCLASSIFICADORCAMPO ='VOLE' ");
					query.append("       AND C.INDISPONIBILIDADE = 1 ");
					query.append("       AND TDC.IDTIPODADOCAMPO = C.IDTIPODADOCAMPO ");
					query.append("       AND C.IDCAMPO IN (").append(idCamposSelecionados).append(") ");
				}
			}

			// log.debug("[AdmFormulariosController.loadConfigCamposSubFormulario] query " + query);

			Pesquisar pesquisar = new Pesquisar();
			rsVO = pesquisar.executar(query.toString());
			if (rsVO != null && rsVO.getLinhas() != null) {

				Object[] listaOrdenadaCampos = new Object[rsVO.getLinhas().getLinhaArray().length];
				/* Inicializa map de campos */
				mapCamposSubFormulario = new HashMap();

				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {

					String idCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
					String nmCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
					String apCampo = rsVO.getLinhas().getLinhaArray(i).getValorArray(2);
					Integer ordemCampo = (Integer) hashOrdemCampos.get(idCampo);

					String inObrigatorio = ConstantesCRM.SZERO;
					if (!StringUtils.isEmpty(idSubFormularioSelecionado)) {
						inObrigatorio = rsVO.getLinhas().getLinhaArray(i).getValorArray(3);
						if (ordemCampo == null) {
							ordemCampo = new Integer(rsVO.getLinhas().getLinhaArray(i).getValorArray(4));
						}
					}

					if ("T".equals(form.getTpCampo())) {
						apCampo = ConstantesCRM.CT_Text;
					} else if ("S".equals(form.getTpCampo())) {
						apCampo = ConstantesCRM.CT_Select;
					} else if ("SM".equals(form.getTpCampo())) {
						apCampo = "SELECTMULTI";
					} else if ("L".equals(form.getTpCampo())) {
						apCampo = "LABEL";
					} else if ("M".equals(form.getTpCampo())) {
						apCampo = "MEMO";
					}

					/* Pouplar map de campos selecionados pelo usuário */
					Campo campo = new Campo(idCampo, nmCampo, apCampo, ordemCampo);
					campo.setRequerido(inObrigatorio);
					mapCamposSubFormulario.put(idCampo, campo);

					log.debug("[AdmFormulariosController.loadConfigCamposSubFormulario] Campo " + campo);

				}

				colCamposSubFormulario = mapCamposSubFormulario.values();
				List listaCamposSubFormulario = new ArrayList(colCamposSubFormulario);
				Collections.sort(listaCamposSubFormulario, new OrdermComparator());

				camposDinamicosForm.setListaCamposSubFormulario(listaCamposSubFormulario);
			}

		} catch (Exception e) {
			log.error("AdmFormulariosController::loadCamposExistentes", e);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="loadManSubFormulario.do"
	 * @jpf:ActionForward name="error" path="/error.jsp"
	 */
	public ActionForward incluirAlterarSubFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;

		log.debug("[AdmFormulariosController.incluirAlterarSubFormulario] Nome SubFormulario " + form.getNomeSubFormulario() + " Ação " + form.getFuncSubFormulario());
		/* Atualiza a obrigatoriedade lista dos campos do sub-formulário */
		String[] camposObrigatorios = form.getCamposObrigatorios();
		log.debug("[AdmFormulariosController.incluirAlterarSubFormulario] camposObrigatorios.length " + camposObrigatorios.length);
		for (int i = 0; i < camposObrigatorios.length; i++) {
			// log.debug("[AdmFormulariosController.incluirAlterarSubFormulario] Campos Obrigatorios [" + i + "] " +
			// camposObrigatorios[i]);
			String idCampoObrigatorio = camposObrigatorios[i];
			Campo campo = (Campo) mapCamposSubFormulario.get(idCampoObrigatorio);
			campo.setRequerido(ConstantesCRM.SONE);
		}

		/* Atualiza a NAO obrigatoriedade lista dos campos do sub-formulário */
		String[] camposNaoObrigatorios = form.getCamposNaoObrigatorios();
		log.debug("[AdmFormulariosController.incluirAlterarSubFormulario] camposNaoObrigatorios.length " + camposNaoObrigatorios.length);
		for (int i = 0; i < camposNaoObrigatorios.length; i++) {
			// log.debug("[AdmFormulariosController.incluirAlterarSubFormulario] Campos Não Obrigatorios [" + i + "] " +
			// camposNaoObrigatorios[i]);
			String idCampoNaoObrigatorio = camposNaoObrigatorios[i];
			Campo campo = (Campo) mapCamposSubFormulario.get(idCampoNaoObrigatorio);
			campo.setRequerido(ConstantesCRM.SZERO);
		}

		try {
			String idSubFormulario = null;
			if ("incluir".equalsIgnoreCase(form.getFuncSubFormulario())) {
				idSubFormulario = incluirSubFormularioSQL(form.getNomeSubFormulario());
			} else {
				idSubFormulario = apagarCamposSubFormularioSQL(form.getSubFrmDisponiveis()[0]);
			}
			if (!StringUtils.isEmpty(idSubFormulario)) {

				for (Iterator it = mapCamposSubFormulario.values().iterator(); it.hasNext();) {
					Campo campo = (Campo) it.next();
					log.debug("[AdmFormulariosController.incluirAlterarSubFormulario] Campo " + campo);
					incluirCamposSubFormularioSQL(idSubFormulario, campo);
				}

				request.setAttribute("msgRetorno", ConstantesCRM.SOK);
			}
		} catch (Exception e) {
			log.error("[AdmFormulariosController.incluirAlterarSubFormulario] Erro ", e);
			request.setAttribute("msgError", "Problemas na inclusão do sub-formulário");

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

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="formulaEncaminhamento.jsp"
	 * @jpf:ActionForward name="error" path="/error.jsp"
	 */
	public ActionForward beginFormulaEncaminhamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RegrasEncaminhamentoForm form = (RegrasEncaminhamentoForm) formParam;
		String destino = ConstantesCRM.SUCCESS;
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		String operacao = request.getParameter("operacao");
		String idFormulario = request.getParameter("idFormulario");
		String idFormulaEncaminhamento = (request.getParameter("idFormulaEncaminhamento") == null) ? ConstantesCRM.SVAZIO : request.getParameter("idFormulaEncaminhamento");

		getRegrasEncaminhamentoForm().getRegraEncaminhamento().setIdRegraEncaminhamento(idFormulaEncaminhamento);
		getRegrasEncaminhamentoForm().setIdFormulario(idFormulario);
		getRegrasEncaminhamentoForm().setDsOperacao(operacao);

		// Chamada a serviço Tuxedo traz lista de Segmentações, Carteiras e Regionais
		MonitoramentoPesquisaVO monitoramentoPesquisaVO = monitoramentoFacade.getMonitoramentoPesquisaVO(idUsuario, true, true, false, false, false);

		// Chamada ao banco de dados traz lista de Destinos
		StringBuffer query = new StringBuffer("SELECT IDDESTPOSSFORMULARIO, NMDESTINOPOSSIVEL FROM APOIO.DESTPOSSFORMULARIO ORDER BY IDDESTPOSSFORMULARIO");

		log.debug(query);

		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO = pesquisar.executar(query.toString());

		if (rsVO != null && rsVO.getLinhas() != null) {
			for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
				monitoramentoPesquisaVO.addNewDestinoVO().setIdDestino(rsVO.getLinhas().getLinhaArray(i).getValorArray(0));
				monitoramentoPesquisaVO.getDestinoVOArray(i).setDsDestino(rsVO.getLinhas().getLinhaArray(i).getValorArray(1));
			}
		}

		getRegrasEncaminhamentoForm().setFiltrosListas(monitoramentoPesquisaVO);

		// Alteração de Fórmula de encaminhamento.
		if (!ConstantesCRM.SVAZIO.equals(idFormulaEncaminhamento)) {

			// Traz nome da Fórmula selecionada
			query = new StringBuffer();
			query.append("SELECT NMFORMULA ").append("  FROM APOIO.ENCAMINHAFDRULE ").append(" WHERE IDENCAMINHAFDRULE = '").append(idFormulaEncaminhamento).append("'");

			rsVO = pesquisar.executar(query.toString());
			if (rsVO != null && rsVO.getLinhas() != null) {
				getRegrasEncaminhamentoForm().getRegraEncaminhamento().setNmRegraEncaminhamento(rsVO.getLinhas().getLinhaArray(0).getValorArray(0));
			}

			// Traz destino selecionado
			query = new StringBuffer();
			query.append("SELECT IDDESTPOSSFORMULARIO ").append("  FROM CONTATOADM.ENCAMINHAFDRULEDESTPOSSIVEL ").append(" WHERE IDENCAMINHAFDRULE = '").append(idFormulaEncaminhamento).append("'");

			rsVO = pesquisar.executar(query.toString());
			if (rsVO != null && rsVO.getLinhas() != null) {
				getRegrasEncaminhamentoForm().getRegraEncaminhamento().addNewDestinoVO();
				getRegrasEncaminhamentoForm().getRegraEncaminhamento().getDestinoVO().setIdDestino(rsVO.getLinhas().getLinhaArray(0).getValorArray(0));
			}

			// Traz lista de Segmentações Associadas
			query = new StringBuffer();
			query.append("SELECT IDSEGMENTACAO ").append("  FROM CONTATOADM.ENCAMINHAFDRULESEGMENTACAO ").append(" WHERE IDENCAMINHAFDRULE = '").append(idFormulaEncaminhamento).append("'");

			rsVO = pesquisar.executar(query.toString());
			if (rsVO != null && rsVO.getLinhas() != null) {
				SegmentacaoVO[] listaSegmentacoes = new SegmentacaoVO[rsVO.getLinhas().getLinhaArray().length];
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					listaSegmentacoes[i] = SegmentacaoVO.Factory.newInstance();
					listaSegmentacoes[i].setIdSegmentacao(Integer.parseInt(rsVO.getLinhas().getLinhaArray(i).getValorArray(0)));
					listaSegmentacoes[i].setDescricao(getDsSegmentacaoById(Integer.parseInt(rsVO.getLinhas().getLinhaArray(i).getValorArray(0))));
				}
				getRegrasEncaminhamentoForm().setListaSegmentacoesSelecionadas(listaSegmentacoes);
			}

			// Traz lista de Carteiras Associadas
			query = new StringBuffer();
			query.append("SELECT IDTIPOCARTEIRA ").append("  FROM CONTATOADM.ENCAMINHAFDRULECARTEIRA ").append(" WHERE IDENCAMINHAFDRULE = '").append(idFormulaEncaminhamento).append("'");

			rsVO = pesquisar.executar(query.toString());
			if (rsVO != null && rsVO.getLinhas() != null) {
				CarterizacaoVO[] listaCarteiras = new CarterizacaoVO[rsVO.getLinhas().getLinhaArray().length];
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					listaCarteiras[i] = CarterizacaoVO.Factory.newInstance();
					listaCarteiras[i].setIdTipoCarteira(Integer.parseInt(rsVO.getLinhas().getLinhaArray(i).getValorArray(0)));
					listaCarteiras[i].setDescricao(getDsCarteiraById(Integer.parseInt(rsVO.getLinhas().getLinhaArray(i).getValorArray(0))));
				}
				getRegrasEncaminhamentoForm().setListaCarteirasSelecionadas(listaCarteiras);
			}

			// Traz lista de Regionais selecionadas
			query = new StringBuffer();
			query.append("SELECT IDUFOPERADORA ").append("  FROM CONTATOADM.ENCAMINHAFDRULEUFOPERADORA ").append(" WHERE IDENCAMINHAFDRULE = '").append(idFormulaEncaminhamento).append("'");

			rsVO = pesquisar.executar(query.toString());
			if (rsVO != null && rsVO.getLinhas() != null) {
				WFRegionalVO[] listaRegionais = new WFRegionalVO[rsVO.getLinhas().getLinhaArray().length];
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					listaRegionais[i] = WFRegionalVO.Factory.newInstance();
					listaRegionais[i].setIdRegional(rsVO.getLinhas().getLinhaArray(i).getValorArray(0));
					listaRegionais[i].setDsRegional(getDsRegionalById(rsVO.getLinhas().getLinhaArray(i).getValorArray(0)));
				}
				getRegrasEncaminhamentoForm().setListaRegionaisSelecionadas(listaRegionais);
			}

		} else {
			getRegrasEncaminhamentoForm().getRegraEncaminhamento().setNmRegraEncaminhamento(ConstantesCRM.SVAZIO);
			getRegrasEncaminhamentoForm().getRegraEncaminhamento().unsetDestinoVO();
			getRegrasEncaminhamentoForm().getRegraEncaminhamento().addNewDestinoVO().setIdDestino(ConstantesCRM.SVAZIO);
			getRegrasEncaminhamentoForm().setListaSegmentacoesSelecionadas(new SegmentacaoVO[0]);
			getRegrasEncaminhamentoForm().setListaCarteirasSelecionadas(new CarterizacaoVO[0]);
			getRegrasEncaminhamentoForm().setListaRegionaisSelecionadas(new WFRegionalVO[0]);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	private String getDsSegmentacaoById(int idSegmentacao) {
		String dsSegmentacao = ConstantesCRM.SVAZIO;
		for (int i = 0; i < getRegrasEncaminhamentoForm().getFiltrosListas().getSegmentacaoVOArray().length; i++) {
			if (getRegrasEncaminhamentoForm().getFiltrosListas().getSegmentacaoVOArray()[i].getIdSegmentacao() == idSegmentacao) {
				dsSegmentacao = getRegrasEncaminhamentoForm().getFiltrosListas().getSegmentacaoVOArray()[i].getDescricao();
				break;
			}
		}
		return dsSegmentacao;
	}

	private String getDsCarteiraById(int idCarteira) {
		String dsCarteira = ConstantesCRM.SVAZIO;
		for (int i = 0; i < getRegrasEncaminhamentoForm().getFiltrosListas().getCarterizacaoVOArray().length; i++) {
			if (getRegrasEncaminhamentoForm().getFiltrosListas().getCarterizacaoVOArray()[i].getIdTipoCarteira() == idCarteira) {
				dsCarteira = getRegrasEncaminhamentoForm().getFiltrosListas().getCarterizacaoVOArray()[i].getDescricao();
				break;
			}
		}
		return dsCarteira;
	}

	private String getDsRegionalById(String idRegional) {
		String dsRegional = ConstantesCRM.SVAZIO;
		for (int i = 0; i < getRegrasEncaminhamentoForm().getFiltrosListas().getWFRegionalVOArray().length; i++) {
			if (idRegional.equals(getRegrasEncaminhamentoForm().getFiltrosListas().getWFRegionalVOArray()[i].getIdRegional())) {
				dsRegional = getRegrasEncaminhamentoForm().getFiltrosListas().getWFRegionalVOArray()[i].getDsRegional();
				break;
			}
		}
		return dsRegional;
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="formulaEncaminhamento.jsp"
	 * @jpf:ActionForward name="error" path="/error.jsp"
	 */
	public ActionForward checkDuplicidadeFormularEncaminhamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		RegrasEncaminhamentoForm form = (RegrasEncaminhamentoForm) formParam;
		String nmFormulaEncaminhamento = request.getParameter("nmFormulaEncaminhamento");
		StringBuffer query = new StringBuffer();
		String xmlOUT = "<formulaExists>";

		query.append("SELECT * ").append("  FROM APOIO.ENCAMINHAFDRULE ").append(" WHERE TRIM(UPPER(NMFORMULA)) = '").append(nmFormulaEncaminhamento.trim().toUpperCase()).append("' ");

		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO = pesquisar.executar(query.toString());

		if (rsVO != null && rsVO.getLinhas() != null && rsVO.getLinhas().getLinhaArray().length > 0) {
			xmlOUT += "true";
		} else {
			xmlOUT += "false";
		}
		xmlOUT += "</formulaExists>";

		response.setContentType(ConstantesCRM.SContentType);
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
		bufferedOutputStream.write(xmlOUT.toString().getBytes(ConstantesCRM.SISO));
		bufferedOutputStream.flush();
		bufferedOutputStream.close();
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="formulaEncaminhamento.jsp"
	 * @jpf:ActionForward name="begin" path="beginFormulaEncaminhamento.do"
	 * @jpf:ActionForward name="error" path="/error.jsp"
	 */
	public ActionForward saveFormulaEncaminhamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		RegrasEncaminhamentoForm form = (RegrasEncaminhamentoForm) formParam;
		String destino = ConstantesCRM.SUCCESS;
		String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		StringBuffer query = new StringBuffer();
		String operacao = ConstantesCRM.SVAZIO.equals(form.getRegraEncaminhamento().getIdRegraEncaminhamento()) ? "inclusao" : request.getParameter("operacao");
		String idFormula = "inclusao".equals(operacao) ? ConstantesCRM.SVAZIO : String.valueOf(form.getRegraEncaminhamento().getIdRegraEncaminhamento() != null ? form.getRegraEncaminhamento().getIdRegraEncaminhamento() : request.getParameter("idFormula"));
		String nmFormula = request.getParameter("nmFormula");
		ActionForward actionForward = mapping.findForward(destino);

		getRegrasEncaminhamentoForm().setDsOperacao(operacao);

		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO = null;

		if ("alteracao".equals(operacao)) {
			form.getRegraEncaminhamento().setSegmentacaoVOArray(new SegmentacaoVO[0]);
			form.getRegraEncaminhamento().setCarterizacaoVOArray(new CarterizacaoVO[0]);
			form.getRegraEncaminhamento().setRegionalVOArray(new RegionalVO[0]);
		}

		if ("alteracao".equals(operacao) || "exclusao".equals(operacao)) {

			// Verifica se exclusão será permitida
			if ("exclusao".equals(operacao)) {
				rsVO = pesquisar.executar("SELECT IDFORMULARIO FROM CONTATOADM.FORMULARIOENCAMINHAFDRULE WHERE IDENCAMINHAFDRULE = '" + idFormula + "'");
				if (rsVO != null && rsVO.getLinhas() != null && rsVO.getLinhas().getLinhaArray().length > 0) {

					boolean permiteExclusao = false;
					if (rsVO.getLinhas().getLinhaArray().length == 1) {
						String idFormularioRelacionado = rsVO.getLinhas().getLinhaArray()[0].getValorArray()[0];
						if (idFormularioRelacionado.equals(request.getParameter("idFormulario"))) {
							permiteExclusao = true;
						}
					}

					if (!permiteExclusao) {
						String out = "<out><permiteExclusao>false</permiteExclusao><qtdeAssociacoes>" + rsVO.getLinhas().getLinhaArray().length + "</qtdeAssociacoes></out>";
						response.setContentType(ConstantesCRM.SContentType);
						BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
						bufferedOutputStream.write(out.toString().getBytes(ConstantesCRM.SISO));
						bufferedOutputStream.flush();
						bufferedOutputStream.close();
						return null;
					}
				}
			}
			rsVO = pesquisar.executar("DELETE FROM CONTATOADM.ENCAMINHAFDRULEDESTPOSSIVEL WHERE IDENCAMINHAFDRULE = '" + idFormula + "'");
			rsVO = pesquisar.executar("DELETE FROM CONTATOADM.ENCAMINHAFDRULESEGMENTACAO  WHERE IDENCAMINHAFDRULE = '" + idFormula + "'");
			rsVO = pesquisar.executar("DELETE FROM CONTATOADM.ENCAMINHAFDRULECARTEIRA     WHERE IDENCAMINHAFDRULE = '" + idFormula + "'");
			rsVO = pesquisar.executar("DELETE FROM CONTATOADM.ENCAMINHAFDRULEUFOPERADORA  WHERE IDENCAMINHAFDRULE = '" + idFormula + "'");
		}

		if ("associacao".equals(operacao) || "desassociacao".equals(operacao) || "exclusao".equals(operacao)) {
			String msgRetorno = ConstantesCRM.SVAZIO;
			if ("associacao".equals(operacao)) {
				query = new StringBuffer();
				query.append("INSERT INTO CONTATOADM.FORMULARIOENCAMINHAFDRULE (IDFORMULARIO, ").append("                                                  IDENCAMINHAFDRULE, ").append("                                                  IDUSUARIOALTERACAO, ").append("                                                  DTULTIMAALTERACAO) ").append("     VALUES ('").append(ConstantesCRM.SVAZIO.equals(form.getIdFormulario()) ? request.getParameter("idFormulario") : form.getIdFormulario())
				.append("', ").append("             '").append(idFormula).append("',").append("             '").append(idUsuario).append("',").append("             SYSDATE)");
				rsVO = pesquisar.executar(query.toString());
				msgRetorno = "A fórmula de encaminhamento " + nmFormula + " foi associada ao formulário com sucesso!";

			} else if ("desassociacao".equals(operacao)) {
				rsVO = pesquisar.executar("DELETE FROM CONTATOADM.FORMULARIOENCAMINHAFDRULE WHERE IDENCAMINHAFDRULE = '" + idFormula + "' AND IDFORMULARIO = '" + request.getParameter("idFormulario") + "'");
				msgRetorno = "A fórmula de encaminhamento " + nmFormula + " foi removida do formulário com sucesso!";

			} else if ("exclusao".equals(operacao)) {
				rsVO = pesquisar.executar("DELETE FROM CONTATOADM.FORMULARIOENCAMINHAFDRULE WHERE IDENCAMINHAFDRULE = '" + idFormula + "'");
				rsVO = pesquisar.executar("DELETE FROM APOIO.ENCAMINHAFDRULE WHERE IDENCAMINHAFDRULE = '" + idFormula + "'");
				msgRetorno = "Fórmula excluída com sucesso!";
			}

			String xmlOUT = "<out><msgRetorno>" + msgRetorno + "</msgRetorno></out>";
			response.setContentType(ConstantesCRM.SContentType);
			BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
			bufferedOutputStream.write(xmlOUT.toString().getBytes(ConstantesCRM.SISO));
			bufferedOutputStream.flush();
			bufferedOutputStream.close();
			return null;
		}

		for (int i = 0; i < form.getSegmentacoesSelecionadas().length; i++) {
			form.getRegraEncaminhamento().addNewSegmentacaoVO().setIdSegmentacao(Integer.parseInt(form.getSegmentacoesSelecionadas()[i]));
		}

		for (int i = 0; i < form.getCarteirasSelecionadas().length; i++) {
			form.getRegraEncaminhamento().addNewCarterizacaoVO().setIdTipoCarteira(Integer.parseInt(form.getCarteirasSelecionadas()[i]));
		}

		for (int i = 0; i < form.getRegionaisSelecionadas().length; i++) {
			form.getRegraEncaminhamento().addNewRegionalVO().setIdRegional(form.getRegionaisSelecionadas()[i]);
		}

		if ("inclusao".equals(operacao)) {
			idFormula = PassPhrase.getNext();
			query.append("INSERT INTO APOIO.ENCAMINHAFDRULE (IDENCAMINHAFDRULE, ").append("                      NMFORMULA, ").append("                      IDUSUARIOALTERACAO, ").append("                      DTULTIMAALTERACAO) ").append("     VALUES ('").append(idFormula).append("', ").append("             '").append(form.getRegraEncaminhamento().getNmRegraEncaminhamento().trim()).append("', ").append("             '").append(idUsuario).append("', ").append("             SYSDATE)");
			log.debug(query);
			rsVO = pesquisar.executar(query.toString());

		} else if ("alteracao".equals(operacao)) {
			query.append("UPDATE APOIO.ENCAMINHAFDRULE ").append("   SET NMFORMULA = '").append(form.getRegraEncaminhamento().getNmRegraEncaminhamento().trim()).append("', ").append("       IDUSUARIOALTERACAO = '").append(idUsuario).append("', ").append("       DTULTIMAALTERACAO = SYSDATE ").append(" WHERE IDENCAMINHAFDRULE = '").append(idFormula).append("'");
			log.debug(query);
			rsVO = pesquisar.executar(query.toString());

		}

		// Se não ocorrer nenhum erro durante gravação de dados
		if (rsVO.getMsg() == null) {
			// Insere relacionamento de destino
			query = new StringBuffer();
			query.append("INSERT INTO CONTATOADM.ENCAMINHAFDRULEDESTPOSSIVEL (IDENCAMINHAFDRULE, ").append("                                                    IDDESTPOSSFORMULARIO, ").append("                                                    IDUSUARIOALTERACAO, ").append("                                                    DTULTIMAALTERACAO) ").append("     VALUES ('").append(idFormula).append("', ").append("             '").append(form.getRegraEncaminhamento().getDestinoVO().getIdDestino())
			.append("', ").append("             '").append(idUsuario).append("', ").append("             SYSDATE) ");

			log.debug(query);
			rsVO = pesquisar.executar(query.toString());

			// Insere relacionamentos de segmentações
			for (int i = 0; i < form.getRegraEncaminhamento().getSegmentacaoVOArray().length; i++) {
				query = new StringBuffer();
				query.append("INSERT INTO CONTATOADM.ENCAMINHAFDRULESEGMENTACAO (IDENCAMINHAFDRULE, ").append("                                                   IDSEGMENTACAO, ").append("                                                   IDUSUARIOALTERACAO, ").append("                                                   DTULTIMAALTERACAO) ").append("     VALUES ('").append(idFormula).append("', ").append("             '")
				.append(form.getRegraEncaminhamento().getSegmentacaoVOArray()[i].getIdSegmentacao()).append("', ").append("             '").append(idUsuario).append("', ").append("             SYSDATE) ");
				log.debug(query);
				rsVO = pesquisar.executar(query.toString());
			}

			// Insere relacionamentos de carteiras
			for (int i = 0; i < form.getRegraEncaminhamento().getCarterizacaoVOArray().length; i++) {
				query = new StringBuffer();
				query.append("INSERT INTO CONTATOADM.ENCAMINHAFDRULECARTEIRA (IDENCAMINHAFDRULE, ").append("                                                IDTIPOCARTEIRA, ").append("                                                IDUSUARIOALTERACAO, ").append("                                                DTULTIMAALTERACAO) ").append("     VALUES ('").append(idFormula).append("', ").append("             '").append(form.getRegraEncaminhamento().getCarterizacaoVOArray()[i].getIdTipoCarteira())
				.append("', ").append("             '").append(idUsuario).append("', ").append("             SYSDATE) ");
				log.debug(query);
				rsVO = pesquisar.executar(query.toString());
			}

			// Insere relacionamentos de regionais
			for (int i = 0; i < form.getRegraEncaminhamento().getRegionalVOArray().length; i++) {
				query = new StringBuffer();
				query.append("INSERT INTO CONTATOADM.ENCAMINHAFDRULEUFOPERADORA (IDENCAMINHAFDRULE, ").append("                                                   IDUFOPERADORA, ").append("                                                   IDUSUARIOALTERACAO, ").append("                                                   DTULTIMAALTERACAO) ").append("     VALUES ('").append(idFormula).append("', ").append("             '")
				.append(form.getRegraEncaminhamento().getRegionalVOArray()[i].getIdRegional()).append("', ").append("             '").append(idUsuario).append("', ").append("             SYSDATE) ");
				log.debug(query);
				rsVO = pesquisar.executar(query.toString());
			}
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("inclusao".equals(operacao)) {
			request.setAttribute("msgStatus", "Fórmula inserida com sucesso!");

		} else if ("alteracao".equals(operacao)) {
			ActionRedirect f = new ActionRedirect("/admsistemas/AdmFormularios/beginFormulaEncaminhamento.do");
			request.setAttribute("msgStatus", "Fórmula alterada com sucesso!");
			f.addParameter("operacao", operacao);
			f.addParameter("idFormulario", form.getIdFormulario());
			f.addParameter("idFormulaEncaminhamento", idFormula);
			actionForward = f;
		}
		return actionForward;
	}

	/**
	 * @description Método responsável por apagar um novo sub-Formulário no Banco
	 * @param String
	 *            - Nome do sub-formulário
	 * @return String - Id do sub-formulário caso não ocorra erro
	 */
	private String incluirSubFormularioSQL(String nmSubFormulario) throws Exception {

		StringBuffer query = new StringBuffer("SELECT CONTATOADM.SUBFRMSQ.NEXTVAL FROM DUAL");
		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO = pesquisar.executar(query.toString());

		if (rsVO != null && rsVO.getMsg() != null) {
			log.error("[AdmFormulariosController.incluirSubFormulario] Erro SubFormulario " + rsVO.getMsg());
			throw new Exception("Problemas na inclusão do sub-formulário");
		}

		if (rsVO != null && rsVO.getLinhas() != null && rsVO.getLinhas().getLinhaArray().length > 0) {
			String idSubFormulario = rsVO.getLinhas().getLinhaArray(0).getValorArray(0);

			query = new StringBuffer();
			query.append("INSERT INTO CONTATOADM.SUBFRM (IDSUBFORM, NMSUBFORMULARIO, IDUSUARIOALTERACAO, DTULTIMAALTERACAO) ");
			query.append("VALUES (").append(idSubFormulario).append(", '").append(nmSubFormulario).append("', 1, SYSDATE) ");

			// log.debug("[AdmFormulariosController.incluirSubFormularioSQL] query " + query);

			Resultset rsVOIns = pesquisar.executar(query.toString());

			if (rsVOIns != null && rsVOIns.getMsg() != null) {
				log.error("[AdmFormulariosController.incluirSubFormulario] Erro SubFormulario " + rsVOIns.getMsg());
				throw new Exception("Problemas na inclusão do sub-formulário");
			}
			return idSubFormulario;
		}
		return null;
	}

	/**
	 * @description Método responsável por apagar todos campos associados ao sub- Formulário no Banco
	 * @param String
	 *            - Id do sub-formulário
	 * @return String - Id do sub-formulário caso não ocorra erro
	 */
	private String apagarCamposSubFormularioSQL(String idSubFormulario) throws Exception {

		log.debug("[AdmFormulariosController.apagarCamposSubFormularioSQL] idSubFormulario " + idSubFormulario);
		StringBuffer query = new StringBuffer();

		query.append("DELETE CONTATOADM.SUBFRMCAMPO WHERE IDSUBFORM = ").append(idSubFormulario);

		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO = pesquisar.executar(query.toString());

		if (rsVO != null && rsVO.getMsg() != null) {
			log.error("[AdmFormulariosController.apagarCamposSubFormularioSQL] Erro SubFormulario " + rsVO.getMsg());
			throw new Exception("Problemas na exclusão de campos do sub-formulário");
		}

		return idSubFormulario;

	}

	/**
	 * @description Método responsável por associar os campos ao sub-Formulário no Banco
	 * @param String
	 *            - Id do sub-formulário
	 * @param String
	 *            - Id do campo
	 * @param String
	 *            - Se ele é obrigatorio
	 * @param int - Ordem de apresentação do campo
	 */
	private void incluirCamposSubFormularioSQL(String idSubFormulario, Campo campo) throws Exception {

		StringBuffer query = new StringBuffer();

		String inRequerido = campo.isRequerido() ? ConstantesCRM.SONE : ConstantesCRM.SZERO;

		query.append("INSERT INTO CONTATOADM.SUBFRMCAMPO (IDSUBFORM, IDCAMPO, SQSEQUENCIAAPRESENTACAO, INREQUERIDO, INPESQUISA, INFILTRO, IDUSUARIOALTERACAO, DTULTIMAALTERACAO ) ");
		query.append("VALUES (").append(idSubFormulario).append(", ").append(campo.getIdCampo()).append(", ").append(campo.getOrderm().toString()).append(", ");
		query.append(inRequerido).append(", 0, 0, 1, SYSDATE ) ");

		// log.debug("[[AdmFormulariosController.incluirCamposSubFormularioSQL] query " + query);

		Pesquisar pesquisar = new Pesquisar();
		Resultset rsVO = pesquisar.executar(query.toString());

		if (rsVO != null && rsVO.getMsg() != null) {
			log.error("[AdmFormulariosController.incluirCamposSubFormularioSQL] Erro Campos SubFormulario " + rsVO.getMsg());
			throw new Exception("Problemas na inclusão de campos do sub-formulário");
		}

	}

	/*
	 * Comparador para ordenão dos campos
	 */
	private class OrdermComparator implements Comparator, Serializable {

		private static final long serialVersionUID = 3129059394138224212L;

		public int compare(Object o1, Object o2) {
			Integer intOrdem1 = ((Campo) o1).getOrderm();
			Integer intOrdem2 = ((Campo) o2).getOrderm();

			return intOrdem1.compareTo(intOrdem2);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="resultadoPesquisa.jsp"
	 */
	public ActionForward getPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		log.debug("[ConsultorRelacionamentoController.getPesquisa] Pesquisar " + form.getTpOperacao() + " CNPJ: " + form.getNrCNPJ() + " CONTATO " + form.getIdContato() + " FOMULARIO " + form.getFrmSelecionados() + " FUNCIONALIDADE " + form.getIdFuncionalidade());

		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());

			ClienteFormularioVO pesquisa = form.getClienteFormularioVO();
			if ("pesquisarEmpresaVO".equalsIgnoreCase(form.getTpOperacao())) {
				pesquisa.setInAssociado(ConstantesCRM.SONE);
				if (pesquisa.isSetFuncionalidadeFrmVO()) {
					pesquisa.unsetFuncionalidadeFrmVO();
				}
				if (pesquisa.isSetClienteVO()) {
					pesquisa.unsetClienteVO();
				}
				pesquisa.addNewClienteVO().setNrCNPJ(form.getNrCNPJ());
			} else if ("pesquisarClienteVOFuncionalidade".equalsIgnoreCase(form.getTpOperacao())) {
				if (pesquisa.isSetFuncionalidadeFrmVO()) {
					pesquisa.unsetFuncionalidadeFrmVO();
				}
				if (pesquisa.isSetClienteVO()) {
					pesquisa.unsetClienteVO();
				}
				FuncionalidadeFrmVO funcionalidadeFrmVO = pesquisa.addNewFuncionalidadeFrmVO();
				funcionalidadeFrmVO.setIdContato(form.getIdContato());
				funcionalidadeFrmVO.setIdFormulario(form.getFrmSelecionados()[0]);
				funcionalidadeFrmVO.setIdFuncionalidade(form.getIdFuncionalidade());
			}

			pesquisa.setTpOperacao(form.getTpOperacao());
			log.debug("[ConsultorRelacionamentoController.getPesquisa]pesquisa " + pesquisa);

			// Aqui faz-se a chamada ao Tuxedo
			pesquisa = admFormulariosFacade.getClienteFormularioVO(user, pesquisa);

			if ("pesquisarEmpresaVO".equalsIgnoreCase(pesquisa.getTpOperacao())) {
				if ("ClienteVO".equals(pesquisa.getListaArray(0).getNmSelect().toString())) {
					getCamposDinamicosForm().setContasDisponiveis(pesquisa.getListaArray(0).getDisponivel());
					getCamposDinamicosForm().setTpOperacao(pesquisa.getTpOperacao());
				}
			} else if ("pesquisarClienteVOFuncionalidade".equalsIgnoreCase(pesquisa.getTpOperacao())) {
				if ("ClienteVO".equals(pesquisa.getListaArray(0).getNmSelect().toString())) {
					getCamposDinamicosForm().setTpOperacao(pesquisa.getTpOperacao());
					popularClientesFormularioFuncionalidade(pesquisa.getListaArray(0).getSelecionado(), form.getFrmSelecionados()[0]);

				}
			}

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			response.setStatus(406, e.toString());
			return null;
		}
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 */
	public ActionForward loadSelecionarFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		log.debug("[AdmFormulariosController.loadSelecionarFormulario] Formulario Selecionado " + form.getIdFormulario() + form.getNmFormulario() + " " + form.getTpOperacao());
		String nmFormularioSelecionado = form.getNmFormulario();
		String idFormularioSelecionado = form.getIdFormulario();
		String tpOperacao = form.getTpOperacao();
		if (StringUtils.isNotEmpty(nmFormularioSelecionado) && StringUtils.isNotEmpty(idFormularioSelecionado)) {
			try {

				/* Inicicao Objeto Formulario */
				Formulario formularioSelecionado = new Formulario(new Integer(idFormularioSelecionado), nmFormularioSelecionado);
				if ("adicionar".equalsIgnoreCase(tpOperacao)) {
					getFormularioFuncionalidade().addFormulario(formularioSelecionado);
				} else {
					getFormularioFuncionalidade().removeFormulario(formularioSelecionado);
				}

			} catch (Exception e) {
				log.error("AdmFormulariosController::loadSelecionarFormulario", e);
			}
		}
		return null;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 */
	public ActionForward loadSelecionarCliente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		log.debug("[AdmFormulariosController.loadSelecionarCliente] Formulario Selecionado " + form.getIdFormulario() + " " + form.getNrCNPJ() + form.getNmEmpresa() + " " + form.getTpOperacao());
		String nmEmpresa = form.getNmEmpresa();
		String nrCNPJ = form.getNrCNPJ();
		String tpOperacao = form.getTpOperacao();
		String idFormulario = form.getIdFormulario();
		if (StringUtils.isNotEmpty(idFormulario) && StringUtils.isNotEmpty(nmEmpresa) && StringUtils.isNotEmpty(nrCNPJ)) {
			try {

				/* Iniciacao Objeto Formulario */
				Formulario formularioEncontrado = getFormularioFuncionalidade().findFormulario(new Integer(idFormulario));

				Cliente clienteSelecionado = new Cliente(nrCNPJ, nmEmpresa);
				if ("adicionar".equalsIgnoreCase(tpOperacao)) {
					if (formularioEncontrado != null) {
						if (formularioEncontrado.findCliente(nrCNPJ) == null) {
							formularioEncontrado.addClienteAssociado(clienteSelecionado);
						}
					}
				} else {
					if (formularioEncontrado != null) {
						formularioEncontrado.removeClienteAssociado(clienteSelecionado);
					}
				}

			} catch (Exception e) {
				log.error("AdmFormulariosController::loadSelecionarCliente", e);
			}
		}
		return null;
	}
	
	
	
	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 */
	public ActionForward loadSelecionarClienteDessassociado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		log.debug("[AdmFormulariosController.loadSelecionarClienteDessassociado] Formulario Selecionado " + form.getIdFormulario() + " " + form.getNrCNPJ() + form.getNmEmpresa() + " " + form.getTpOperacao());
		String nmEmpresa = form.getNmEmpresa();
		String nrCNPJ = form.getNrCNPJ();
		String tpOperacao = form.getTpOperacao();
		String idFormulario = form.getIdFormulario();
		if (StringUtils.isNotEmpty(idFormulario) && StringUtils.isNotEmpty(nmEmpresa) && StringUtils.isNotEmpty(nrCNPJ)) {
			try {

				/* Iniciacao Objeto Formulario */
				Formulario formularioEncontrado = getFormularioFuncionalidade().findFormulario(new Integer(idFormulario));

				Cliente clienteSelecionado = new Cliente(nrCNPJ, nmEmpresa);
				if ("adicionar".equalsIgnoreCase(tpOperacao)) {
					if (formularioEncontrado != null) {
						if (formularioEncontrado.findClienteDessassociado(nrCNPJ) == null) {
							formularioEncontrado.addClienteDessassociado(clienteSelecionado);
						}
					}
				} else {
					if (formularioEncontrado != null) {
						formularioEncontrado.removeClienteDessassociado(clienteSelecionado);
					}
				}

			} catch (Exception e) {
				log.error("AdmFormulariosController::loadSelecionarClienteDessassociado", e);
			}
		}
		return null;
	}	
		
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 */
	public ActionForward loadSelecionarPrazo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		log.debug("[AdmFormulariosController.loadSelecionarCliente] Formulario Selecionado " + form.getIdFormulario() + " " + form.getIdPrazoSelecionado() + " " + form.getNmPrazoSelecionado());
		String idPrazoSelecionado = form.getIdPrazoSelecionado();
		String nmPrazoSelecionado = form.getNmPrazoSelecionado();
		String idFormulario = form.getIdFormulario();
		if (StringUtils.isNotEmpty(idFormulario) && StringUtils.isNotEmpty(nmPrazoSelecionado) && StringUtils.isNotEmpty(idPrazoSelecionado)) {
			try {

				/* Iniciacao Objeto Formulario */
				Formulario formularioEncontrado = getFormularioFuncionalidade().findFormulario(new Integer(idFormulario));
				SLA prazoSelecionado = new SLA(new Integer(idPrazoSelecionado), nmPrazoSelecionado);
				if (formularioEncontrado != null) {
					formularioEncontrado.setSLA(prazoSelecionado);
				}

			} catch (Exception e) {
				log.error("AdmFormulariosController::loadSelecionarCliente", e);
			}
		}
		return null;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 */
	public ActionForward gravarFormularioFuncionalidade(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		log.debug("[AdmFormulariosController.gravarFormularioFuncionalidade] Form " + form.getIdContato());
		String msErro = ConstantesCRM.SVAZIO;
		boolean retornoGravacao = true;
		try {
			if (StringUtils.isNotEmpty(form.getIdContato())) {
				Integer idContato = new Integer(form.getIdContato());
				Contato contato = new Contato(idContato);
				getFormularioFuncionalidade().setContato(contato);
			}

			if (!validarRegrasGravacao(request)) {
				retornoGravacao = false;
				msErro = (String) request.getAttribute("MensagemErro");
			} else {

				Integer idContato = getFormularioFuncionalidade().getContato() != null ? getFormularioFuncionalidade().getContato().getId() : null;
				Integer idFuncionalide = getFormularioFuncionalidade().getFuncionalidade() != null ? getFormularioFuncionalidade().getFuncionalidade().getId() : null;

				ClienteFormularioVO clienteFormulario = ClienteFormularioVO.Factory.newInstance();
				clienteFormulario.setTpOperacao("gravarFormulario");
				if (clienteFormulario.isSetFuncionalidadeFrmVO()) {
					clienteFormulario.unsetFuncionalidadeFrmVO();
				}
				if (clienteFormulario.isSetClienteVO()) {
					clienteFormulario.unsetClienteVO();
				}

				clienteFormulario.addNewFuncionalidadeFrmVO().setIdContato(idContato.toString());
				clienteFormulario.getFuncionalidadeFrmVO().setIdFuncionalidade(idFuncionalide.toString());

				clienteFormulario.addNewLista().addNewSelecionado();
				if (getFormularioFuncionalidade().getListaFormulario().size() <= 0) {
					clienteFormulario.getListaArray(0).getSelecionado().addNewIDValorClienteVO().setId(ConstantesCRM.SVAZIO);
					clienteFormulario.getListaArray(0).getSelecionado().getIDValorClienteVOArray(0).setValor(ConstantesCRM.SVAZIO);
					clienteFormulario.getListaArray(0).getSelecionado().getIDValorClienteVOArray(0).setNrDocumentoFmt(ConstantesCRM.SVAZIO);

				} else {

					int index = 0;
					for (Iterator itForm = getFormularioFuncionalidade().getListaFormulario().iterator(); itForm.hasNext();) {
						Formulario formulario = (Formulario) itForm.next();
						log.debug("[AdmFormulariosController.gravarFormularioFuncionalidade] IDValorClienteID [ " + index + "] ID = [" + formulario + "] VALOR = [" + formulario.getSLA() + "] ");
						String idFormulario = formulario.getId().toString();
						String idSla = formulario.getSLA() != null ? formulario.getSLA().getId().toString() : ConstantesCRM.SONE;

						if (formulario.getListaClientesAssociados().size() > 0) {

							for (Iterator itCliente = formulario.getListaClientesAssociados().iterator(); itCliente.hasNext();) {
								Cliente cliente = (Cliente) itCliente.next();
								String nrDocumento = cliente.getNrCNPJ();
								log.debug("[AdmFormulariosController.gravarFormularioFuncionalidade] IDValorClienteID [ " + index + "] NRDOCUMETO = [" + cliente + "]");

								clienteFormulario.getListaArray(0).getSelecionado().addNewIDValorClienteVO().setId(idFormulario);
								clienteFormulario.getListaArray(0).getSelecionado().getIDValorClienteVOArray(index).setValor(idSla);
								clienteFormulario.getListaArray(0).getSelecionado().getIDValorClienteVOArray(index).setNrDocumentoFmt(nrDocumento);
								clienteFormulario.getListaArray(0).getSelecionado().getIDValorClienteVOArray(index).setIntipoassociacao("A");
								
								index++;
							}

						} else if (formulario.getListaClientesDessassociados().size() > 0) {
							
							for (Iterator itCliente = formulario.getListaClientesDessassociados().iterator(); itCliente.hasNext();) {
								Cliente cliente = (Cliente) itCliente.next();
								String nrDocumento = cliente.getNrCNPJ();
								log.debug("[AdmFormulariosController.gravarFormularioFuncionalidade] IDValorClienteID [ " + index + "] NRDOCUMETO = [" + cliente + "]");

								clienteFormulario.getListaArray(0).getSelecionado().addNewIDValorClienteVO().setId(idFormulario);
								clienteFormulario.getListaArray(0).getSelecionado().getIDValorClienteVOArray(index).setValor(idSla);
								clienteFormulario.getListaArray(0).getSelecionado().getIDValorClienteVOArray(index).setNrDocumentoFmt(nrDocumento);
								clienteFormulario.getListaArray(0).getSelecionado().getIDValorClienteVOArray(index).setIntipoassociacao("D");
								
								index++;
							}						
						} else {
							clienteFormulario.getListaArray(0).getSelecionado().addNewIDValorClienteVO().setId(idFormulario);
							clienteFormulario.getListaArray(0).getSelecionado().getIDValorClienteVOArray(index).setValor(idSla);
							index++;
						}
						
						


					}
				}

				try {
					String user = ConstantesCRM.getCurrentUser(request.getSession());
					ClienteFormularioVO retornoVO = admFormulariosFacade.setClienteFormularioVO(user, clienteFormulario);
					if (retornoVO != null && StringUtils.isNotEmpty(retornoVO.getMsgRetorno())) {
						log.error("[AdmFormulariosController.gravarFormularioFuncionalidade] Retorno " + retornoVO.getMsgRetorno());
						retornoGravacao = false;
						msErro = ConstantesCRM.SVAZIO;
					} else {
						msErro = ConstantesCRM.SSucesso;
						retornoGravacao = true;
					}

				} catch (Exception e) {
					retornoGravacao = false;
					msErro = e.getMessage();
				}

			}

		} catch (Exception e) {
			log.error("AdmFormulariosController::gravarFormularioFuncionalidade", e);
		}

		msErro = StringUtils.isEmpty(msErro) ? "Problemas na inclusao." : msErro;

		String xmlOUT = "<msg><retorno>" + retornoGravacao + "</retorno><cdErro>99</cdErro><msErro>" + msErro + "</msErro></msg>";

		log.debug("[AdmFormulariosController.gravarFormularioFuncionalidade] xmlOUT " + xmlOUT);

		response.setContentType("text/plain;charset=ISO-8859-1");
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
		bufferedOutputStream.write(xmlOUT.getBytes(ConstantesCRM.SISO));
		bufferedOutputStream.flush();
		bufferedOutputStream.close();

		return null;
	}

	private boolean validarRegrasGravacao(HttpServletRequest request) {
		try {
			StringBuffer sb = new StringBuffer();
			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = null;

			Integer idContato = getFormularioFuncionalidade().getContato() != null ? getFormularioFuncionalidade().getContato().getId() : null;
			Integer idFuncionalidade = getFormularioFuncionalidade().getFuncionalidade() != null ? getFormularioFuncionalidade().getFuncionalidade().getId() : null;

		} catch (Exception e) {
			log.error("[AdmFormulariosController.validarRegrasGravacao]", e);
		}

		request.setAttribute("MensagemErro", ConstantesCRM.SVAZIO);
		return true;
	}

	/**
	 * Método responsável por atualizar o objeto FormularioFuncionalidade com a lista de clientes de cada formulário
	 * selecionado
	 * 
	 * @param Selecionado
	 *            - Lista clientes selecionados no banco
	 * @param Formulario
	 *            - Formulário selecionado
	 */
	private void popularClientesFormularioFuncionalidade(Selecionado contasSelecionadas, String idFormularioSelecioando) {

		log.debug("[AdmFormulariosController.popularClientesFormularioFuncionalidade] ContasSelecionadas " + contasSelecionadas + " formularioSelecionado = " + idFormularioSelecioando);
        ArrayList listaClientesSelecionados = new ArrayList();        
        ArrayList listaClientesSelecionadosDessassociados = new ArrayList();

		/* Salvar Lista de clientes associadas ao formulario */
		Formulario formulario = getFormularioFuncionalidade().findFormulario(new Integer(idFormularioSelecioando));
		listaClientesSelecionados = (ArrayList) formulario.getListaClientesAssociados();
		listaClientesSelecionadosDessassociados = (ArrayList) formulario.getListaClientesDessassociados();

		if (contasSelecionadas != null && contasSelecionadas.getIDValorClienteVOArray().length > 0) {

			for (int i = 0; i < contasSelecionadas.getIDValorClienteVOArray().length; i++) {
				IDValorClienteVO idValorClienteVO = contasSelecionadas.getIDValorClienteVOArray(i);
				
				/* Verificar se Cliente já não está na Lista para evitar Duplicaçao */
				Cliente clienteSelecionado = new Cliente(idValorClienteVO.getId(), idValorClienteVO.getValor());
				clienteSelecionado.setNrDocumentoFormatado(idValorClienteVO.getNrDocumentoFmt());
				
				if (idValorClienteVO.getIntipoassociacao().equals("A")){
					int index = listaClientesSelecionados.indexOf(clienteSelecionado);
					log.debug("[AdmFormulariosController.popularClientesFormularioFuncionalidade - CLIENTE SELECIONADOS] index " + index);
					if (index < 0) {
						listaClientesSelecionados.add(clienteSelecionado);
					}
				} else {
                    int index = listaClientesSelecionadosDessassociados.indexOf(clienteSelecionado);
                    log.debug("[AdmFormulariosController.popularClientesFormularioFuncionalidade - CLIENTE DESSASSOCIADOS] index " + index);
                    if (index < 0) {
                       listaClientesSelecionadosDessassociados.add(clienteSelecionado);
                    }    				
				}
			}

			Formulario formularioEncontrado = getFormularioFuncionalidade().findFormulario(new Integer(idFormularioSelecioando));
			formularioEncontrado.setListaClientesAssociados(listaClientesSelecionados);
			formularioEncontrado.setListaClientesDessassociados(listaClientesSelecionadosDessassociados);
		}

        getCamposDinamicosForm().setArrClientesDessassociados(listaClientesSelecionadosDessassociados);
        getCamposDinamicosForm().setArrClientes(listaClientesSelecionados);
	}

	/**
	 * Método reponsável por carrregar a lista de Prazo de Atendimento
	 * 
	 * @return Resultset - Lista dos prazosa de atendimento disponível em banco
	 */
	private Resultset carregarListaPrazoAtendimento() {

		try {
			Pesquisar pesquisar = new Pesquisar();

			/* Obter lista Prazos Atentimento */
			StringBuffer query = new StringBuffer();

			query.append("SELECT IDSLAFUNCIONALIDADEFRM, DESCRICAOSLA, VALORSLA, IDUSUARIOALTERACAO, DTULTIMAALTERACAO ");
			query.append("FROM   CONTATOADM.SLAFUNCIONALIDADEFRM ");
			query.append("ORDER BY VALORSLA ");

			Resultset rsPrazoAtendimentoVO = pesquisar.executar(query.toString());
			if (rsPrazoAtendimentoVO != null && rsPrazoAtendimentoVO.getLinhas() != null) {
				return rsPrazoAtendimentoVO;
			}

		} catch (Exception e) {
			log.error("AdmFormulariosController::loadCamposExistentes", e);
		}

		Resultset rsVO = Resultset.Factory.newInstance();
		rsVO.addNewLinhas();

		return rsVO;
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="iArvoreContato.jsp"
	 */
	public ActionForward obterArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("TelaInicialController:obterArvoreContato(" + user + ") >> INICIALIZADO");
		RelacionamentoForm form = (RelacionamentoForm) formParam;
		AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(user, null);
		if (admContatoFolhaVO == null) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
		StringBuffer sbScript = new StringBuffer(ConstantesCRM.SVAZIO);
		sbScript.append("if (document.getElementById) {\n\nvar tree = new WebFXTree('");
		sbScript.append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getNmContato()));
		sbScript.append("',\"Javascript:selecionaContato('");
		sbScript.append(admContatoFolhaVO.getIdContato());
		sbScript.append("', '");
		sbScript.append("','','" + RAIZ_PRINCIPAL + "');\",'classic');\n\n");
		// sbScript.append("', '");
		// sbScript.append("');\",'classic');\n\n");
		StringBuffer sbNode = new StringBuffer(1024);
		for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray().length; i++) {
			sbNode.setLength(0);
			String idContato = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getIdContato();
			String sInFolha = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha();
			String nmContato = StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getNmContato());
			String dsPath = StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getDsPath());
			String ico1 = ConstantesCRM.SVAZIO;
			String ico2 = ConstantesCRM.SVAZIO;
			String dblClickScript = "Javascript:itemDblClick('" + idContato + "', '" + sInFolha + "', '" + dsPath + "')";
			if (!admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha().equals(ConstantesCRM.SONE)) {
				ico1 = "/FrontOfficeWeb/resources/images/foldericon.png";
				ico2 = "/FrontOfficeWeb/resources/images/openfoldericon.png";
				dblClickScript = ConstantesCRM.SVAZIO;
			}
			sbNode.append("var arvore = new WebFXTreeItem('");
			sbNode.append(nmContato);
			sbNode.append("',\"Javascript:selecionaContato('");
			sbNode.append(idContato);
			sbNode.append("','");
			sbNode.append(sInFolha);
			sbNode.append("','");
			sbNode.append(dsPath);
			sbNode.append("','");
			sbNode.append("');\",'','").append(ico1).append("','").append(ico2).append("','',\"");
			sbNode.append(dblClickScript).append("\");\n");
			sbScript.append(sbNode.toString());
			sbScript.append("tree.add(arvore);\n");
		}
		sbScript.append("document.write(tree);\n}\n");
		// RelacionamentoForm relacionamentoForm = this.relacionamentoForm;
		this.relacionamentoForm = new RelacionamentoForm();
		this.relacionamentoForm.setScriptArvore(sbScript.toString());

		log.debug("TelaInicialController:obterArvoreContato(" + user + ") >> FINALIZADO");
		request.setAttribute("form", form);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="iExpandeArvoreContatoRel.jsp"
	 */
	public ActionForward expandeArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("TelaInicialController:expandeArvoreContato(" + user + ") >> INICIALIZADO");
		RelacionamentoForm form = (RelacionamentoForm) formParam;
		StringBuffer sbScript = new StringBuffer(1024);
		StringBuffer sbNode = new StringBuffer(1024);
		AtendimentoArvoreFiltrosVO atFiltros = AtendimentoArvoreFiltrosVO.Factory.newInstance();
		atFiltros.setIdContato(request.getParameter("IDCONTATO"));
		AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(user, atFiltros);
		for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray().length; i++) {
			sbNode.setLength(0);
			String idContato = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getIdContato();
			String sInFolha = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha();
			String nmContato = StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getNmContato());
			String dsPath = StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getDsPath());
			String ico1 = ConstantesCRM.SVAZIO;
			String ico2 = ConstantesCRM.SVAZIO;
			String dblClickScript = "Javascript:itemDblClick('" + idContato + "', '" + sInFolha + "', '" + dsPath + "')";
			if (!admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha().equals(ConstantesCRM.SONE)) {
				ico1 = "/FrontOfficeWeb/resources/images/foldericon.png";
				ico2 = "/FrontOfficeWeb/resources/images/openfoldericon.png";
				dblClickScript = ConstantesCRM.SVAZIO;
			}
			sbNode.append("var arvore = new parent.WebFXTreeItem('");
			sbNode.append(nmContato);
			sbNode.append("',\"Javascript:selecionaContato('");
			sbNode.append(idContato);
			sbNode.append("','");
			sbNode.append(sInFolha);
			sbNode.append("','");
			sbNode.append(dsPath);
			sbNode.append("','");
			sbNode.append("');\",'','").append(ico1).append("','").append(ico2).append("','',\"");
			sbNode.append(dblClickScript).append("\");");
			sbScript.append(sbNode.toString());
			sbScript.append("parent.tree.getSelected().add(arvore);\n\n");
		}
		sbScript.append("parent.tree.getSelected().setAddEnabled(false);\n\n");
		sbScript.append("parent.tree.getSelected().expand();\n\n");

		this.relacionamentoForm.setScriptArvore(sbScript.toString());
		log.debug("TelaInicialController:expandeArvoreContato(" + user + ") >> FINALIZADO");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 */
	public ActionForward loadPrazoAtendimentoFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		log.debug("[AdmFormulariosController.loadPrazoAtendimentoFormulario] Form " + form.getFrmSelecionados());
		boolean retornoEncontrado = true;
		String xmlOUT = ConstantesCRM.SVAZIO;
		Integer idSlaFormualario = new Integer(0);

		try {
			if (form.getFrmSelecionados() != null && StringUtils.isNotEmpty(form.getFrmSelecionados()[0])) {

				Integer idFormularioSelecionado = new Integer(form.getFrmSelecionados()[0]);
				log.debug("[AdmFormulariosController.loadPrazoAtendimentoFormulario] idFormularioSelecionado " + idFormularioSelecionado);

				Formulario selecionado = getFormularioFuncionalidade().findFormulario(idFormularioSelecionado);

				log.debug("[AdmFormulariosController.loadPrazoAtendimentoFormulario] Formulario " + selecionado);
				idSlaFormualario = selecionado != null && selecionado.getSLA() != null ? selecionado.getSLA().getId() : new Integer(1);
			}

		} catch (Exception e) {
			log.error("AdmFormulariosController::loadPrazoAtendimentoFormulario", e);
		}

		xmlOUT = "<msg><retorno>" + retornoEncontrado + "</retorno><cdErro></cdErro><msErro>" + idSlaFormualario + "</msErro></msg>";

		log.debug("[AdmFormulariosController.loadPrazoAtendimentoFormulario] xmlOUT " + xmlOUT);

		response.setContentType("text/plain;charset=ISO-8859-1");
		BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
		bufferedOutputStream.write(xmlOUT.getBytes(ConstantesCRM.SISO));
		bufferedOutputStream.flush();
		bufferedOutputStream.close();

		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:ActionForward name="success" path="loadManSubFormulario.do"
	 * @jpf:ActionForward name="excluir" path="manSubFormulario.jsp"
	 * @jpf:ActionForward name="error" path="/error.jsp"
	 */
	public ActionForward excluirSubFormulario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		log.debug("[AdmFormulariosController.excluirSubFormulario] Nome SubFormulario " + form.getSubFrmDisponiveis());

		try {
			if (form.getSubFrmDisponiveis() != null) {

				String idSubFormulario = form.getSubFrmDisponiveis()[0];
				/* Verificar se existe dependencia */
				StringBuffer query = new StringBuffer();
				query.append("SELECT IDSUBFORM, IDFORMULARIO FROM CONTATOADM.FORMULARIOOBJETO WHERE IDSUBFORM = ").append(idSubFormulario);

				Pesquisar pesquisar = new Pesquisar();
				Resultset rsVO = pesquisar.executar(query.toString());

				if (rsVO != null && rsVO.getMsg() != null) {
					log.error("[AdmFormulariosController.incluirSubFormulario] Erro SubFormulario " + rsVO.getMsg());
					throw new Exception("Problemas na exclusão do sub-formulário");
				}

				if (rsVO != null && rsVO.getLinhas() != null && rsVO.getLinhas().getLinhaArray().length > 0) {
					String idFormulario = rsVO.getLinhas().getLinhaArray(0).getValorArray(1);
					log.error("[AdmFormulariosController.excluirSubFormulario] Erro Formulario " + idFormulario);
					request.setAttribute("msgError", "Não foi possivél realizar a exclusão pois o sub-formulário selecionado está associado a um formulário");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("excluir");
				}

				/* Exclusao campos associados ao sub-formulário */
				query = new StringBuffer();
				query.append("DELETE FROM CONTATOADM.SUBFRMCAMPO WHERE IDSUBFORM = ").append(idSubFormulario);

				Resultset rsVODelete = pesquisar.executar(query.toString());
				if (rsVODelete != null && rsVODelete.getMsg() != null) {
					log.error("[AdmFormulariosController.excluirSubFormulario] Erro SubFormulario " + rsVODelete.getMsg());
					throw new Exception("Problemas na exclusão campos do sub-formulário");
				}

				/* Exclusao do sub-formulário */
				query = new StringBuffer();
				query.append("DELETE FROM CONTATOADM.SUBFRM WHERE IDSUBFORM = ").append(idSubFormulario);

				rsVODelete = pesquisar.executar(query.toString());
				if (rsVODelete != null && rsVODelete.getMsg() != null) {
					log.error("[AdmFormulariosController.excluirSubFormulario] Erro SubFormulario " + rsVODelete.getMsg());
					throw new Exception("Problemas na exclusão do sub-formulário");
				}

				request.setAttribute("msgRetorno", ConstantesCRM.SOK);
			}

		} catch (Exception e) {
			log.error("[AdmFormulariosController.excluirSubFormulario] Erro ", e);
			request.setAttribute("msgError", "Problemas na exclusão do sub-formulário");
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget("top.frameApp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("error");
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	public ActionForward verificarFormularioExclusivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		CamposDinamicosForm form = (CamposDinamicosForm) formParam;
		log.debug("[ConsultorRelacionamentoController.verificarFormularioExclusivo] IdFormulario " + form.getIdFormulario());

		try {
			Integer idFormulario = new Integer(form.getIdFormulario());
			Formulario selecionado = getFormularioFuncionalidade().findFormulario(idFormulario);
			log.debug("[ConsultorRelacionamentoController.verificarFormularioExclusivo] Clientes " + selecionado.getListaClientesAssociados().size());
			if (selecionado != null && selecionado.getListaClientesAssociados() != null && selecionado.getListaClientesAssociados().size() > 0) {
				response.setStatus(406, "Formulário selecionado não pode ser formulário padrão");
			}
		} catch (Exception e) {
			response.setStatus(406, e.toString());
			return null;
		}
		return null;
	}

	public static class CamposDinamicosForm extends ActionForm {

		private static final long serialVersionUID = 614975488963451736L;

		private Resultset resultset;
		private Resultset listaFormularios;
		private Campo[] listaCamposFormulario;
		private Collection listaCamposSubFormulario;
		private Resultset listaSubFormularios;
		private String[] vlCampoCombo;
		private String[] vlCampoComboAlt;
		private String[] frmDisponiveis;
		private String[] frmSelecionados;
		private String[] subFrmDisponiveis;
		private String[] listaCamposSelecionadosCombo;
		private Resultset listaCamposSelecionados;
		private String[] camposObrigatorios;
		private String[] camposNaoObrigatorios;
		private String vlCampo = ConstantesCRM.SVAZIO;
		private String fmtTexto = ConstantesCRM.SVAZIO;
		private String nmCampo = ConstantesCRM.SVAZIO;
		private String lbCampo = ConstantesCRM.SVAZIO;
		private String tpCampo = ConstantesCRM.SVAZIO;
		private String idCampo = ConstantesCRM.SVAZIO;
		private String tpCampoAlt = ConstantesCRM.SVAZIO;
		private String idCampoAlt = ConstantesCRM.SVAZIO;
		private String idFormulario = ConstantesCRM.SVAZIO;
		private String idClassificador = ConstantesCRM.SVAZIO;
		private String idSubForm = ConstantesCRM.SVAZIO;
		private String idFuncionalidade = ConstantesCRM.SVAZIO;
		private String nmFormulario = ConstantesCRM.SVAZIO;
		private String action = ConstantesCRM.SVAZIO;
		private String qtCamposFrm = ConstantesCRM.SVAZIO;
		private String idContato = ConstantesCRM.SVAZIO;
		private String contato = ConstantesCRM.SVAZIO;
		private String idSubFormulario = ConstantesCRM.SVAZIO;
		private Disponivel lstClassificacaoFrm = Disponivel.Factory.newInstance();
		private Disponivel lstFuncionalidade = Disponivel.Factory.newInstance();
		private Disponivel lstFrmDisponiveis = Disponivel.Factory.newInstance();
		private Disponivel lstSubFrm = Disponivel.Factory.newInstance();
		private Disponivel lstCamposExistentes = Disponivel.Factory.newInstance();
		// private Disponivel lstSubFrmDisponiveis = Disponivel.Factory.newInstance();
		private String funcSubFormulario;
		private String nomeSubFormulario;
		private Resultset listaEmpresasDisponiveis;
		private List listaCamposExistentes;
		private Campo campoFormulario;
		private List listaDominio;
		private RegrasEncaminhamentoDisponivelVO[] regrasEncaminhamento;
		private String[] listaContasSelecionadas;
		private String nrCNPJ;
		private br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO.Lista.Disponivel contasDisponiveis;
		private ClienteFormularioVO clienteFormularioVO;
		private String[] listaContasDisponiveis;
		private String tpOperacao;
		private Resultset listaPrazoAtendimento;
		private String[] listaPrazoAtendimentoSelecionado;
		private Collection listaFormularioFuncionalidade;
		private String[] listaContasDessassociadas;
		public String[] getListaContasDessassociadas() {
			return listaContasDessassociadas;
		}

		public void setListaContasDessassociadas(String[] listaContasDessassociadas) {
			this.listaContasDessassociadas = listaContasDessassociadas;
		}

		
		public CamposDinamicosForm() {
			this.clienteFormularioVO = ClienteFormularioVO.Factory.newInstance();
			this.clienteFormularioVO.addNewFuncionalidadeFrmVO();
			this.clienteFormularioVO.addNewClienteVO();
		}

		private String nmEmpresa;
		private String idPrazoSelecionado;
		private String nmPrazoSelecionado;
		private String duplicarFormulario;
		private ArrayList arrClientes;
		private ArrayList arrClientesDessassociados;
		
		
		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setContato(String contato) {
			this.contato = contato;
		}

		public String getContato() {
			return this.contato;
		}

		public void setQtCamposFrm(String qtCamposFrm) {
			this.qtCamposFrm = qtCamposFrm;
		}

		public String getQtCamposFrm() {
			return this.qtCamposFrm;
		}

		public void setAction(String action) {
			this.action = action;
		}

		public String getAction() {
			return this.action;
		}

		public void setIdCampo(String idCampo) {
			this.idCampo = idCampo;
		}

		public String getIdCampo() {
			return this.idCampo;
		}

		public void setIdCampoAlt(String idCampoAlt) {
			this.idCampoAlt = idCampoAlt;
		}

		public String getIdCampoAlt() {
			return this.idCampoAlt;
		}

		public void setTpCampoAlt(String tpCampoAlt) {
			this.tpCampoAlt = tpCampoAlt;
		}

		public String getTpCampoAlt() {
			return this.tpCampoAlt;
		}

		public void setIdFormulario(String idFormulario) {
			this.idFormulario = idFormulario;
		}

		public String getIdFormulario() {
			return this.idFormulario;
		}

		public void setIdFuncionalidade(String idFuncionalidade) {
			this.idFuncionalidade = idFuncionalidade;
		}

		public String getIdFuncionalidade() {
			return this.idFuncionalidade;
		}

		public void setTpCampo(String tpCampo) {
			this.tpCampo = tpCampo;
		}

		public String getTpCampo() {
			return this.tpCampo;
		}

		public void setNmCampo(String nmCampo) {
			this.nmCampo = nmCampo;
		}

		public String getNmCampo() {
			return this.nmCampo;
		}

		public void setLbCampo(String lbCampo) {
			this.lbCampo = lbCampo;
		}

		public String getLbCampo() {
			return this.lbCampo;
		}

		public void setVlCampo(String vlCampo) {
			this.vlCampo = vlCampo;
		}

		public String getVlCampo() {
			return this.vlCampo;
		}

		public void setFmtTexto(String fmtTexto) {
			this.fmtTexto = fmtTexto;
		}

		public String getFmtTexto() {
			return this.fmtTexto;
		}

		public void setVlCampoCombo(String[] vlCampoCombo) {
			this.vlCampoCombo = vlCampoCombo;
		}

		public String[] getVlCampoCombo() {
			if (this.vlCampoCombo == null || this.vlCampoCombo.length == 0) {
				this.vlCampoCombo = new String[1];
			}
			return this.vlCampoCombo;
		}

		public void setVlCampoComboAlt(String[] vlCampoComboAlt) {
			this.vlCampoComboAlt = vlCampoComboAlt;
		}

		public String[] getVlCampoComboAlt() {
			if (this.vlCampoComboAlt == null || this.vlCampoComboAlt.length == 0) {
				this.vlCampoComboAlt = new String[1];
			}
			return this.vlCampoComboAlt;
		}

		public void setFrmDisponiveis(String[] frmDisponiveis) {
			this.frmDisponiveis = frmDisponiveis;
		}

		public String[] getFrmDisponiveis() {
			if (this.frmDisponiveis == null || this.frmDisponiveis.length == 0) {
				this.frmDisponiveis = new String[0];
			}
			return this.frmDisponiveis;
		}

		public void setFrmSelecionados(String[] frmSelecionados) {
			this.frmSelecionados = frmSelecionados;
		}

		public String[] getFrmSelecionados() {
			if (this.frmSelecionados == null || this.frmSelecionados.length == 0) {
				this.frmSelecionados = new String[0];
			}
			return this.frmSelecionados;
		}

		public void setResultset(Resultset resultset) {
			this.resultset = resultset;
		}

		public Resultset getResultset() {
			return this.resultset;
		}

		public void setListaFormularios(Resultset listaFormularios) {
			this.listaFormularios = listaFormularios;
		}

		public Resultset getListaFormularios() {
			return this.listaFormularios;
		}

		public void setListaCamposFormulario(Campo[] listaCamposFormulario) {
			this.listaCamposFormulario = listaCamposFormulario;
		}

		public Campo[] getListaCamposFormulario() {
			return this.listaCamposFormulario;
		}

		public void setNmFormulario(String nmFormulario) {
			this.nmFormulario = nmFormulario;
		}

		public String getNmFormulario() {
			return this.nmFormulario;
		}

		public void setIdClassificador(String idClassificador) {
			this.idClassificador = idClassificador;
		}

		public String getIdClassificador() {
			return this.idClassificador;
		}

		public void setIdSubForm(String idSubForm) {
			this.idSubForm = idSubForm;
		}

		public String getIdSubForm() {
			return this.idSubForm;
		}

		public void setLstClassificacaoFrm(Disponivel lstClassificacaoFrm) {
			this.lstClassificacaoFrm = lstClassificacaoFrm;
		}

		public Disponivel getLstClassificacaoFrm() {
			return this.lstClassificacaoFrm;
		}

		public void setLstSubFrm(Disponivel lstSubFrm) {
			this.lstSubFrm = lstSubFrm;
		}

		public Disponivel getLstSubFrm() {
			return this.lstSubFrm;
		}

		public void setLstCamposExistentes(Disponivel lstCamposExistentes) {
			this.lstCamposExistentes = lstCamposExistentes;
		}

		public Disponivel getLstCamposExistentes() {
			return this.lstCamposExistentes;
		}

		public void setLstFuncionalidade(Disponivel lstFuncionalidade) {
			this.lstFuncionalidade = lstFuncionalidade;
		}

		public Disponivel getLstFuncionalidade() {
			return this.lstFuncionalidade;
		}

		public void setLstFrmDisponiveis(Disponivel lstFrmDisponiveis) {
			this.lstFrmDisponiveis = lstFrmDisponiveis;
		}

		public Disponivel getLstFrmDisponiveis() {
			return this.lstFrmDisponiveis;
		}

		public String getFuncSubFormulario() {
			return this.funcSubFormulario;
		}

		public void setFuncSubFormulario(String funcSubFormulario) {
			this.funcSubFormulario = funcSubFormulario;
		}

		public String getNomeSubFormulario() {
			return this.nomeSubFormulario;
		}

		public void setNomeSubFormulario(String nomeSubFormulario) {
			this.nomeSubFormulario = nomeSubFormulario;
		}

		public void setSubFrmDisponiveis(String[] subFrmDisponiveis) {
			this.subFrmDisponiveis = subFrmDisponiveis;
		}

		public String[] getSubFrmDisponiveis() {
			if (this.subFrmDisponiveis == null || this.subFrmDisponiveis.length == 0) {
				this.subFrmDisponiveis = new String[0];
			}
			return this.subFrmDisponiveis;
		}

		public void setListaSubFormularios(Resultset listaSubFormularios) {
			this.listaSubFormularios = listaSubFormularios;
		}

		public Resultset getListaSubFormularios() {
			return this.listaSubFormularios;
		}

		public Resultset getListaCamposSelecionados() {
			return this.listaCamposSelecionados;
		}

		public void setListaCamposSelecionados(Resultset listaCamposSelecionados) {
			this.listaCamposSelecionados = listaCamposSelecionados;
		}

		public void setListaCamposSelecionadosCombo(String[] listaCamposSelecionadosCombo) {
			this.listaCamposSelecionadosCombo = listaCamposSelecionadosCombo;
		}

		public String[] getListaCamposSelecionadosCombo() {
			if (this.listaCamposSelecionadosCombo == null || this.listaCamposSelecionadosCombo.length == 0) {
				this.listaCamposSelecionadosCombo = new String[0];
			}
			return this.listaCamposSelecionadosCombo;
		}

		public String getIdSubFormulario() {
			return this.idSubFormulario;
		}

		public void setIdSubFormulario(String idSubFormulario) {
			this.idSubFormulario = idSubFormulario;
		}

		public void setCamposObrigatorios(String[] camposObrigatorios) {
			this.camposObrigatorios = camposObrigatorios;
		}

		public String[] getCamposObrigatorios() {
			if (this.camposObrigatorios == null || this.camposObrigatorios.length == 0) {
				this.camposObrigatorios = new String[0];
			}
			return this.camposObrigatorios;
		}

		public void setCamposNaoObrigatorios(String[] camposNaoObrigatorios) {
			this.camposNaoObrigatorios = camposNaoObrigatorios;
		}

		public String[] getCamposNaoObrigatorios() {
			if (this.camposNaoObrigatorios == null || this.camposNaoObrigatorios.length == 0) {
				this.camposNaoObrigatorios = new String[0];
			}
			return this.camposNaoObrigatorios;
		}

		public void setListaCamposSubFormulario(Collection listaCamposSubFormulario) {
			this.listaCamposSubFormulario = listaCamposSubFormulario;
		}

		public Collection getListaCamposSubFormulario() {
			return this.listaCamposSubFormulario;
		}

		public RegrasEncaminhamentoDisponivelVO[] getRegrasEncaminhamento() {
			if (this.regrasEncaminhamento == null) {
				this.regrasEncaminhamento = new RegrasEncaminhamentoDisponivelVO[0];
			}
			return this.regrasEncaminhamento;
		}

		public void setRegrasEncaminhamento(RegrasEncaminhamentoDisponivelVO[] arg) {
			this.regrasEncaminhamento = arg;
		}

		public void setListaCamposExistentes(List listaCamposExistentes) {
			this.listaCamposExistentes = listaCamposExistentes;
		}

		public List getListaCamposExistentes() {
			return this.listaCamposExistentes;
		}

		public void setCampoFormulario(Campo campo) {
			this.campoFormulario = campo;
		}

		public Campo getCampoFormulario() {
			return this.campoFormulario;
		}

		public void setListaDominio(List listaDominio) {
			this.listaDominio = listaDominio;
		}

		public List getListaDominio() {
			return this.listaDominio;
		}

		public Resultset getListaEmpresasDisponiveis() {
			return this.listaEmpresasDisponiveis;
		}

		public void setListaEmpresasDisponiveis(Resultset listaEmpresasDisponiveis) {
			this.listaEmpresasDisponiveis = listaEmpresasDisponiveis;
		}

		public String getNrCNPJ() {
			return this.nrCNPJ;
		}

		public void setNrCNPJ(String nrCNPJ) {
			this.nrCNPJ = nrCNPJ;
		}

		public ClienteFormularioVO getClienteFormularioVO() {
			if (this.clienteFormularioVO == null) {
				this.clienteFormularioVO = ClienteFormularioVO.Factory.newInstance();
			}
			return this.clienteFormularioVO;
		}

		public void setConsultorRelacionamento(ClienteFormularioVO consultorRelacionamento) {
			this.clienteFormularioVO = consultorRelacionamento;
		}

		public br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO.Lista.Disponivel getContasDisponiveis() {
			if (this.contasDisponiveis == null) {
				this.contasDisponiveis = br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO.Lista.Disponivel.Factory.newInstance();
			}
			return this.contasDisponiveis;
		}

		public void setContasDisponiveis(br.com.vivo.fo.admsistemas.vo.ClienteFormularioVODocument.ClienteFormularioVO.Lista.Disponivel contasDisponiveis) {
			this.contasDisponiveis = contasDisponiveis;
		}

		public String[] getListaContasDisponiveis() {
			if (this.listaContasDisponiveis == null) {
				this.listaContasDisponiveis = new String[0];
			}
			return this.listaContasDisponiveis;
		}

		public void setListaContasDisponiveis(String[] listaContasDisponiveis) {
			this.listaContasDisponiveis = listaContasDisponiveis;
		}

		public String getTpOperacao() {
			return this.tpOperacao;
		}

		public void setTpOperacao(String tpOperacao) {
			this.tpOperacao = tpOperacao;
		}

		public String[] getListaContasSelecionadas() {
			if (this.listaContasSelecionadas == null) {
				this.listaContasSelecionadas = new String[0];
			}
			return this.listaContasSelecionadas;
		}

		public void setListaContasSelecionadas(String[] listaContasSelecionadas) {
			this.listaContasSelecionadas = listaContasSelecionadas;
		}

		public Resultset getListaPrazoAtendimento() {
			return this.listaPrazoAtendimento;
		}

		public void setListaPrazoAtendimento(Resultset listaPrazoAtendimento) {
			this.listaPrazoAtendimento = listaPrazoAtendimento;
		}

		public String[] getListaPrazoAtendimentoSelecionado() {
			if (this.listaPrazoAtendimentoSelecionado == null) {
				this.listaPrazoAtendimentoSelecionado = new String[0];
			}
			return this.listaPrazoAtendimentoSelecionado;
		}

		public void setListaPrazoAtendimentoSelecionado(String[] listaPrazoAtendimentoSelecionado) {
			this.listaPrazoAtendimentoSelecionado = listaPrazoAtendimentoSelecionado;
		}

		public Collection getListaFormularioFuncionalidade() {
			return this.listaFormularioFuncionalidade;
		}

		public void setListaFormularioFuncionalidade(Collection listaFormularioFuncionalidade) {
			this.listaFormularioFuncionalidade = listaFormularioFuncionalidade;
		}

		public String getNmEmpresa() {
			return this.nmEmpresa;
		}

		public void setNmEmpresa(String nmEmpresa) {
			this.nmEmpresa = nmEmpresa;
		}

		public String getIdPrazoSelecionado() {
			return this.idPrazoSelecionado;
		}

		public void setIdPrazoSelecionado(String idPrazoSelecionado) {
			this.idPrazoSelecionado = idPrazoSelecionado;
		}

		public String getNmPrazoSelecionado() {
			return this.nmPrazoSelecionado;
		}

		public void setNmPrazoSelecionado(String nmPrazoSelecionado) {
			this.nmPrazoSelecionado = nmPrazoSelecionado;
		}

		public String getDuplicarFormulario() {
			return this.duplicarFormulario;
		}

		public void setDuplicarFormulario(String duplicarFormulario) {
			this.duplicarFormulario = duplicarFormulario;
		}

		public ArrayList getArrClientes() {
			if (this.arrClientes == null) {
				this.arrClientes = new ArrayList();
			}
			return this.arrClientes;
		}

		public void setArrClientes(ArrayList arrClientes) {
			this.arrClientes = arrClientes;
		}

		
        public ArrayList getArrClientesDessassociados() {
            if (this.arrClientesDessassociados == null) {
                this.arrClientesDessassociados = new ArrayList();
            }
            return this.arrClientesDessassociados;
        }

        public void setArrClientesDessassociados(ArrayList arrClientesDessassociados) {
            this.arrClientesDessassociados = arrClientesDessassociados;
        }     		
		
		
	}
}
