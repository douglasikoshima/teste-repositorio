package admsistemas.admArvoreParametro;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroRetornoVODocument.AdmArvoreParametroRetornoVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoParametroVODocument.AdmGrupoParametroVO;
import br.com.vivo.fo.admsistemas.vo.AdmVarAContatoArqGeradosVODocument.AdmVarAContatoArqGeradosVO;
import br.com.vivo.fo.admsistemas.vo.AdmVariavelRetornoVODocument.AdmVariavelRetornoVO;
import br.com.vivo.fo.admsistemas.vo.VariaveisArvoreContatoVODocument.VariaveisArvoreContatoVO;
import br.com.vivo.fo.commons.utils.properties.LoadProperties;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({"unchecked","rawtypes"})
public class AdmArvoreParametroController extends AbstractAction {

	private static final long serialVersionUID = -530746586965412178L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.configArvoreContato.ArvoreContato controlArvoreParametro;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.variaveisArvoreContato.VariaveisArvoreContato variaveisArvoreContatoFacade;

	// Definição constantes chamado de serviço.
	private static final String COMBO = "1";
	// private static final String ARVORE_FILTRADA = "2";
	// private static final String GRID = "3";

	// Para montagem da arvore.
	// private static final String FOLHA = "1";
	// private static final String DISPONIVEL = "1";

	// Para geraçao de arquivo CSV.
	public static final String COLUNA = ",";
	public static final char CONSTANTE_SEPARA = '\n';
	public static final String C_TEXTO = "\"";

	private FormArvoreParametro formArvoreParametro = null;

	public global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("carregaFiltro".equals(mapping.getParameter())) {
			return carregaFiltro(mapping, form, request, response);
		} else if ("exportaRetorno".equals(mapping.getParameter())) {
			return exportaRetorno(mapping, form, request, response);
		} else if ("consultaFiltrosSelecionados".equals(mapping.getParameter())) {
			return consultaFiltrosSelecionados(mapping, form, request, response);
		} else if ("geraListaArquivo".equals(mapping.getParameter())) {
			return geraListaArquivo(mapping, form, request, response);
		} else if ("disponibilizaArquivo".equals(mapping.getParameter())) {
			return disponibilizaArquivo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="carregaFiltro.do"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AdmArvoreParametroController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="filtraArvoreParametro.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregaFiltro(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AdmArvoreParametroController:carregaFiltro(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		FormArvoreParametro form = (FormArvoreParametro) formParam;
		try {
			AdmArvoreParametroVO admContainerParametro = AdmArvoreParametroVO.Factory.newInstance();
			admContainerParametro.addNewDadosAtuais();

			admContainerParametro.setInServico(COMBO);
			admContainerParametro = controlArvoreParametro.getArvoreParametro(admContainerParametro, ConstantesCRM.getCurrentUser(request.getSession()));
			admContainerParametro.setAdmArvoreParametroCombosVO(testNull(admContainerParametro.getAdmArvoreParametroCombosVO()));

			getFormArvoreParametro().setContainerCombos(admContainerParametro.getAdmArvoreParametroCombosVO());

			populaCombos(getFormArvoreParametro().getContainerCombos(), form);

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreParametroController:carregaFiltro" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			getFormArvoreParametro().setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("AdmArvoreParametroController:carregaFiltro" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void populaCombos(AdmArvoreParametroCombosVO total, FormArvoreParametro form) {

		getFormArvoreParametro().setContainerCombosAssoc(AdmArvoreParametroCombosVO.Factory.newInstance());
		getFormArvoreParametro().setContainerCombosExist(AdmArvoreParametroCombosVO.Factory.newInstance());

		// Globais.
		int countAssoc = 0;
		int countExist = 0;
		int i = 0;
		int x = 0;
		String selectedArray[] = null;
		boolean flgExiste = false;

		// *****************************************************************
		// TIPO LINHA
		// *****************************************************************
		selectedArray = form.getIdTipoLinhaAssocArray();
		for (i = 0; i < total.getAdmTipoLinhaSimplVOArray().length; i++) {
			String tipoLinha = (total.getAdmTipoLinhaSimplVOArray(i).getIdTipoLinha() != null) ? total.getAdmTipoLinhaSimplVOArray(i).getIdTipoLinha() : "";

			for (x = 0; x < selectedArray.length; x++) {
				if (tipoLinha.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}
			if (flgExiste) {
				getFormArvoreParametro().getContainerCombosAssoc().addNewAdmTipoLinhaSimplVO();
				getFormArvoreParametro().getContainerCombosAssoc().setAdmTipoLinhaSimplVOArray(countAssoc, total.getAdmTipoLinhaSimplVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreParametro().getContainerCombosExist().addNewAdmTipoLinhaSimplVO();
				getFormArvoreParametro().getContainerCombosExist().setAdmTipoLinhaSimplVOArray(countExist, total.getAdmTipoLinhaSimplVOArray(i));
				++countExist;

			}

		}

		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		// *****************************************************************
		// SEGMENTACAO
		// *****************************************************************
		selectedArray = form.getIdSegmentacaoAssocArray();
		for (i = 0; i < total.getAdmTipoSegmentacaoVOArray().length; i++) {
			String segmentacao = (total.getAdmTipoSegmentacaoVOArray(i).getIdSegmentacao() != null) ? total.getAdmTipoSegmentacaoVOArray(i).getIdSegmentacao() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (segmentacao.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreParametro().getContainerCombosAssoc().addNewAdmTipoSegmentacaoVO();
				getFormArvoreParametro().getContainerCombosAssoc().setAdmTipoSegmentacaoVOArray(countAssoc, total.getAdmTipoSegmentacaoVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreParametro().getContainerCombosExist().addNewAdmTipoSegmentacaoVO();
				getFormArvoreParametro().getContainerCombosExist().setAdmTipoSegmentacaoVOArray(countExist, total.getAdmTipoSegmentacaoVOArray(i));
				++countExist;
			}
		}

		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		// *****************************************************************
		// GRUPO TRATAMENTO
		// *****************************************************************
		selectedArray = form.getIdGrupoTratamentoAssocArray();
		for (i = 0; i < total.getAdmGrupoTratamentoVOArray().length; i++) {
			String gTratamento = (total.getAdmGrupoTratamentoVOArray(i).getIdGrupo() != null) ? total.getAdmGrupoTratamentoVOArray(i).getIdGrupo() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (gTratamento.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreParametro().getContainerCombosAssoc().addNewAdmGrupoTratamentoVO();
				getFormArvoreParametro().getContainerCombosAssoc().setAdmGrupoTratamentoVOArray(countAssoc, total.getAdmGrupoTratamentoVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreParametro().getContainerCombosExist().addNewAdmGrupoTratamentoVO();
				getFormArvoreParametro().getContainerCombosExist().setAdmGrupoTratamentoVOArray(countExist, total.getAdmGrupoTratamentoVOArray(i));
				++countExist;
			}
		}

		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		// ****************************************************************
		// GRUPO ABERTURA.
		// *****************************************************************
		selectedArray = form.getIdGrupoAberturaAssocArray();
		for (i = 0; i < total.getAdmGrupoAberturaVOArray().length; i++) {
			String gAbertura = (total.getAdmGrupoAberturaVOArray(i).getIdGrupo() != null) ? total.getAdmGrupoAberturaVOArray(i).getIdGrupo() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (gAbertura.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreParametro().getContainerCombosAssoc().addNewAdmGrupoAberturaVO();
				getFormArvoreParametro().getContainerCombosAssoc().setAdmGrupoAberturaVOArray(countAssoc, total.getAdmGrupoAberturaVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreParametro().getContainerCombosExist().addNewAdmGrupoAberturaVO();
				getFormArvoreParametro().getContainerCombosExist().setAdmGrupoAberturaVOArray(countExist, total.getAdmGrupoAberturaVOArray(i));
				++countExist;
			}
		}

		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		// ****************************************************************
		// GRUPO RETORNO.
		// *****************************************************************
		selectedArray = form.getIdGrupoRetornoAssocArray();
		for (i = 0; i < total.getAdmGrupoRetornoVOArray().length; i++) {
			String gRetorno = (total.getAdmGrupoRetornoVOArray(i).getIdGrupo() != null) ? total.getAdmGrupoRetornoVOArray(i).getIdGrupo() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (gRetorno.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreParametro().getContainerCombosAssoc().addNewAdmGrupoRetornoVO();
				getFormArvoreParametro().getContainerCombosAssoc().setAdmGrupoRetornoVOArray(countAssoc, total.getAdmGrupoRetornoVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreParametro().getContainerCombosExist().addNewAdmGrupoRetornoVO();
				getFormArvoreParametro().getContainerCombosExist().setAdmGrupoRetornoVOArray(countExist, total.getAdmGrupoRetornoVOArray(i));
				++countExist;
			}
		}

		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		// ****************************************************************
		// CARTEIRIZAÇÃO.
		// *****************************************************************
		selectedArray = form.getIdTipoCarteiraAssocArray();
		for (i = 0; i < total.getAdmTipoCarteiraSimplVOArray().length; i++) {
			String gAbertura = (total.getAdmTipoCarteiraSimplVOArray(i).getIdTipoCarteira() != null) ? total.getAdmTipoCarteiraSimplVOArray(i).getIdTipoCarteira() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (gAbertura.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreParametro().getContainerCombosAssoc().addNewAdmTipoCarteiraSimplVO();
				getFormArvoreParametro().getContainerCombosAssoc().setAdmTipoCarteiraSimplVOArray(countAssoc, total.getAdmTipoCarteiraSimplVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreParametro().getContainerCombosExist().addNewAdmTipoCarteiraSimplVO();
				getFormArvoreParametro().getContainerCombosExist().setAdmTipoCarteiraSimplVOArray(countExist, total.getAdmTipoCarteiraSimplVOArray(i));
				++countExist;
			}
		}

		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		// ****************************************************************
		// TIPO PESSOA(TIPO RELACIONAMENTO).
		// *****************************************************************
		selectedArray = form.getIdTipoRelacionamentoAssocArray();
		for (i = 0; i < total.getAdmTipoRelacionamentoVOArray().length; i++) {
			String tRelacionamento = (total.getAdmTipoRelacionamentoVOArray(i).getIdTipoRelacionamento() != null) ? total.getAdmTipoRelacionamentoVOArray(i).getIdTipoRelacionamento() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (tRelacionamento.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreParametro().getContainerCombosAssoc().addNewAdmTipoRelacionamentoVO();
				getFormArvoreParametro().getContainerCombosAssoc().setAdmTipoRelacionamentoVOArray(countAssoc, total.getAdmTipoRelacionamentoVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreParametro().getContainerCombosExist().addNewAdmTipoRelacionamentoVO();
				getFormArvoreParametro().getContainerCombosExist().setAdmTipoRelacionamentoVOArray(countExist, total.getAdmTipoRelacionamentoVOArray(i));
				++countExist;
			}
		}

		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		// ****************************************************************
		// DISPONIVEL.
		// *****************************************************************
		GenericVO genericSim = new GenericVO();
		genericSim.setCodigo(ConstantesCRM.SONE);
		genericSim.setDescricao(ConstantesCRM.SYES);

		GenericVO genericNao = new GenericVO();
		genericNao.setCodigo(ConstantesCRM.SZERO);
		genericNao.setDescricao("Não");

		ArrayList disponivelExist = new ArrayList();
		ArrayList disponivelAssoc = new ArrayList();

		getFormArvoreParametro().setDisponivelExist(disponivelExist);
		getFormArvoreParametro().setDisponivelAssoc(disponivelAssoc);

		selectedArray = form.getIdDisponivelAssocArray();

		if ((selectedArray.length == 1) && (selectedArray[0] == null)) {
			disponivelExist.add(genericSim);
			disponivelExist.add(genericNao);

			getFormArvoreParametro().setDisponivelExist(disponivelExist);

		} else if (selectedArray.length == 1) {
			if (selectedArray[0] != null && selectedArray[0].equals(ConstantesCRM.SONE)) {
				disponivelExist.add(genericNao);
				disponivelAssoc.add(genericSim);

			} else {
				disponivelAssoc.add(genericNao);
				disponivelExist.add(genericSim);
			}

			getFormArvoreParametro().setDisponivelExist(disponivelExist);
			getFormArvoreParametro().setDisponivelAssoc(disponivelAssoc);

		} else if (selectedArray != null && selectedArray.length == 2) {
			disponivelAssoc.add(genericSim);
			disponivelAssoc.add(genericNao);
			getFormArvoreParametro().setDisponivelAssoc(disponivelAssoc);
		}

		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		// ****************************************************************
		// OPERADORA.
		// *****************************************************************
		selectedArray = form.getIdOperadoraAssocArray();
		for (i = 0; i < total.getAdmUFOperadoraSimplVOArray().length; i++) {
			String operadora = (total.getAdmUFOperadoraSimplVOArray(i).getIdUFOperadora() != null) ? total.getAdmUFOperadoraSimplVOArray(i).getIdUFOperadora() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (operadora.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreParametro().getContainerCombosAssoc().addNewAdmUFOperadoraSimplVO();
				getFormArvoreParametro().getContainerCombosAssoc().setAdmUFOperadoraSimplVOArray(countAssoc, total.getAdmUFOperadoraSimplVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreParametro().getContainerCombosExist().addNewAdmUFOperadoraSimplVO();
				getFormArvoreParametro().getContainerCombosExist().setAdmUFOperadoraSimplVOArray(countExist, total.getAdmUFOperadoraSimplVOArray(i));
				++countExist;
			}
		}

		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		// ****************************************************************
		// CANAL.
		// *****************************************************************
		selectedArray = form.getIdCanalAssocArray();
		for (i = 0; i < total.getAdmCanalVOArray().length; i++) {
			String operadora = (total.getAdmCanalVOArray(i).getIdCanal() != null) ? total.getAdmCanalVOArray(i).getIdCanal() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (operadora.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreParametro().getContainerCombosAssoc().addNewAdmCanalVO();
				getFormArvoreParametro().getContainerCombosAssoc().setAdmCanalVOArray(countAssoc, total.getAdmCanalVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreParametro().getContainerCombosExist().addNewAdmCanalVO();
				getFormArvoreParametro().getContainerCombosExist().setAdmCanalVOArray(countExist, total.getAdmCanalVOArray(i));
				++countExist;
			}
		}

		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		/************
		 * PROCEDÊNCIA
		 ************/
		selectedArray = form.getIdProcedenciaAssocArray();

		for (i = 0; i < total.getAdmProcedenciaVOArray().length; i++) {

			String operadora = (total.getAdmProcedenciaVOArray(i).getIdProcedencia() != null) ? total.getAdmProcedenciaVOArray(i).getIdProcedencia() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (operadora.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreParametro().getContainerCombosAssoc().addNewAdmProcedenciaVO();
				getFormArvoreParametro().getContainerCombosAssoc().setAdmProcedenciaVOArray(countAssoc, total.getAdmProcedenciaVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreParametro().getContainerCombosExist().addNewAdmProcedenciaVO();
				getFormArvoreParametro().getContainerCombosExist().setAdmProcedenciaVOArray(countExist, total.getAdmProcedenciaVOArray(i));
				++countExist;
			}
		}
		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		// ****************************************************************
		// NATUREZA.
		// *****************************************************************
		selectedArray = form.getIdNaturezaAssocArray();
		for (i = 0; i < total.getAdmNaturezaVOArray().length; i++) {
			String operadora = (total.getAdmNaturezaVOArray(i).getIdNatureza() != null) ? total.getAdmNaturezaVOArray(i).getIdNatureza() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (operadora.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreParametro().getContainerCombosAssoc().addNewAdmNaturezaVO();
				getFormArvoreParametro().getContainerCombosAssoc().setAdmNaturezaVOArray(countAssoc, total.getAdmNaturezaVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreParametro().getContainerCombosExist().addNewAdmNaturezaVO();
				getFormArvoreParametro().getContainerCombosExist().setAdmNaturezaVOArray(countExist, total.getAdmNaturezaVOArray(i));
				++countExist;
			}
		}

		countAssoc = 0;
		countExist = 0;
		selectedArray = null;
		flgExiste = false;

		// ****************************************************************
		// FECHAMENTO.
		// *****************************************************************
		selectedArray = form.getIdFechamentoAssocArray();
		for (i = 0; i < total.getAdmTipoFechamentoVOArray().length; i++) {
			String fechamento = (total.getAdmTipoFechamentoVOArray(i).getIdTipoFechamento() != null) ? total.getAdmTipoFechamentoVOArray(i).getIdTipoFechamento() : "";

			for (x = 0; x < selectedArray.length; x++) {

				if (fechamento.equals(selectedArray[x])) {
					flgExiste = true;
					break;
				} else {
					flgExiste = false;
				}
			}

			if (flgExiste) {
				getFormArvoreParametro().getContainerCombosAssoc().addNewAdmTipoFechamentoVO();
				getFormArvoreParametro().getContainerCombosAssoc().setAdmTipoFechamentoVOArray(countAssoc, total.getAdmTipoFechamentoVOArray(i));
				++countAssoc;

			} else {
				getFormArvoreParametro().getContainerCombosExist().addNewAdmTipoFechamentoVO();
				getFormArvoreParametro().getContainerCombosExist().setAdmTipoFechamentoVOArray(countExist, total.getAdmTipoFechamentoVOArray(i));
				++countExist;
			}
		}

	}

	// testa se existem objetos nulos.
	private AdmArvoreParametroCombosVO testNull(AdmArvoreParametroCombosVO admCombos) {
		if (admCombos == null) {
			return null;
		}

		if (admCombos.getAdmGrupoAberturaVOArray() == null) {
			admCombos.addNewAdmGrupoAberturaVO();
		}

		if (admCombos.getAdmGrupoRetornoVOArray() == null) {
			admCombos.addNewAdmGrupoRetornoVO();
		}

		if (admCombos.getAdmGrupoTratamentoVOArray() == null) {
			admCombos.addNewAdmGrupoTratamentoVO();
		}

		if (admCombos.getAdmTipoCarteiraSimplVOArray() == null) {
			admCombos.addNewAdmTipoCarteiraSimplVO();
		}

		if (admCombos.getAdmTipoLinhaSimplVOArray() == null) {
			admCombos.addNewAdmTipoLinhaSimplVO();
		}

		if (admCombos.getAdmTipoRelacionamentoVOArray() == null) {
			admCombos.addNewAdmTipoRelacionamentoVO();
		}

		if (admCombos.getAdmTipoSegmentacaoVOArray() == null) {
			admCombos.addNewAdmTipoSegmentacaoVO();
		}

		if (admCombos.getAdmUFOperadoraSimplVOArray() == null) {
			admCombos.addNewAdmUFOperadoraSimplVO();
		}

		if (admCombos.getAdmCanalVOArray() == null) {
			admCombos.addNewAdmCanalVO();
		}

		if (admCombos.getAdmNaturezaVOArray() == null) {
			admCombos.addNewAdmNaturezaVO();
		}

		if (admCombos.getAdmTipoFechamentoVOArray() == null) {
			admCombos.addNewAdmTipoFechamentoVO();
		}

		return admCombos;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="success" path="filtraArvoreParametro.jsp"
	 */
	public ActionForward exportaRetorno(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			log.debug("AdmArvoreParametroController:exportaRetorno(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
			FormArvoreParametro form = (FormArvoreParametro) formParam;
			StringBuffer strb = new StringBuffer();

			strb.append("login=").append(request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN)).append(CONSTANTE_SEPARA);
			strb.append("consulta=").append(form.getIdContatoGrupoRadio()).append(CONSTANTE_SEPARA);

			VariaveisArvoreContatoVO variaveisArvoreContatoVO = VariaveisArvoreContatoVO.Factory.newInstance();

			// Tipo de Consulta: 0->Contato 1->Grupo 2->ContatoXGrupo
			variaveisArvoreContatoVO.setInTipoConsulta(form.getIdContatoGrupoRadio());

			// Operacao: 0->Lista de Arquivos 1->Insercao 2->Consulta de Filtros
			variaveisArvoreContatoVO.setInOperacao(ConstantesCRM.SONE);

			// COMBO TIPO LINHA (1)
			if (form.getIdTipoLinhaAssocArray() != null && form.getIdTipoLinhaAssocArray().length > 0 && form.getIdTipoLinhaAssocArray()[0] != null) {
				String[] tmpTipoLinha = form.getIdTipoLinhaAssocArray();
				variaveisArvoreContatoVO.addNewTipoLinha();
				for (int i = 0; i < tmpTipoLinha.length; i++) {
					variaveisArvoreContatoVO.getTipoLinha().addId(tmpTipoLinha[i]);
				}
			}

			// COMBO SEGMENTAÇAO (2)
			if (form.getIdSegmentacaoAssocArray() != null && form.getIdSegmentacaoAssocArray().length > 0 && form.getIdSegmentacaoAssocArray()[0] != null) {
				variaveisArvoreContatoVO.addNewSegmentacao();
				String[] tmpSegmento = form.getIdSegmentacaoAssocArray();
				for (int i = 0; i < tmpSegmento.length; i++) {
					variaveisArvoreContatoVO.getSegmentacao().addId(tmpSegmento[i]);
				}
			}

			// COMBO TIPO CLIENTE (TIPO RELACIONAMENTO) (3)
			if (form.getIdTipoRelacionamentoAssocArray() != null && form.getIdTipoRelacionamentoAssocArray().length > 0 && form.getIdTipoRelacionamentoAssocArray()[0] != null) {
				String[] tmpTipoCliente = form.getIdTipoRelacionamentoAssocArray();
				variaveisArvoreContatoVO.addNewTipoCliente();
				for (int i = 0; i < tmpTipoCliente.length; i++) {
					strb.append(tmpTipoCliente[i]);
					variaveisArvoreContatoVO.getTipoCliente().addId(tmpTipoCliente[i]);
				}
			}

			// COMBO TIPO CARTEIRA (4)
			if (form.getIdTipoCarteiraAssocArray() != null && form.getIdTipoCarteiraAssocArray().length > 0 && form.getIdTipoCarteiraAssocArray()[0] != null) {
				String[] tmpTipoCarteira = form.getIdTipoCarteiraAssocArray();
				variaveisArvoreContatoVO.addNewCarteirizacao();
				for (int i = 0; i < tmpTipoCarteira.length; i++) {
					variaveisArvoreContatoVO.getCarteirizacao().addId(tmpTipoCarteira[i]);
				}
			}

			// COMBO OPERADORA (5)
			if (form.getIdOperadoraAssocArray() != null && form.getIdOperadoraAssocArray().length > 0 && form.getIdOperadoraAssocArray()[0] != null) {
				String[] tmpRegional = form.getIdOperadoraAssocArray();
				variaveisArvoreContatoVO.addNewOperadora();
				for (int i = 0; i < tmpRegional.length; i++) {
					variaveisArvoreContatoVO.getOperadora().addId(tmpRegional[i]);
				}
			}

			// COMBO NATUREZA - (TIPO PESSOA) (6)
			if (form.getIdNaturezaAssocArray() != null && form.getIdNaturezaAssocArray().length > 0 && form.getIdNaturezaAssocArray()[0] != null) {
				String[] tmpNatureza = form.getIdNaturezaAssocArray();
				variaveisArvoreContatoVO.addNewNatureza();
				for (int i = 0; i < tmpNatureza.length; i++) {
					variaveisArvoreContatoVO.getNatureza().addId(tmpNatureza[i]);
				}
			}

			// COMBO CANAL (7)
			if (form.getIdCanalAssocArray() != null && form.getIdCanalAssocArray().length > 0 && form.getIdCanalAssocArray()[0] != null) {
				String[] tmpCanal = form.getIdCanalAssocArray();
				variaveisArvoreContatoVO.addNewCanal();
				for (int i = 0; i < tmpCanal.length; i++) {
					variaveisArvoreContatoVO.getCanal().addId(tmpCanal[i]);
				}
			}

			// COMBO FECHAMENTO (8)
			if (form.getIdFechamentoAssocArray() != null && form.getIdFechamentoAssocArray().length > 0 && form.getIdFechamentoAssocArray()[0] != null) {
				String[] tmpFechamento = form.getIdFechamentoAssocArray();
				variaveisArvoreContatoVO.addNewFechamento();
				for (int i = 0; i < tmpFechamento.length; i++) {
					variaveisArvoreContatoVO.getFechamento().addId(tmpFechamento[i]);
				}
			}

			// COMBO GRUPO ABERTURA (9)
			if (form.getIdGrupoAberturaAssocArray() != null && form.getIdGrupoAberturaAssocArray().length > 0 && form.getIdGrupoAberturaAssocArray()[0] != null) {
				String[] tmpGrupoAbertura = form.getIdGrupoAberturaAssocArray();
				variaveisArvoreContatoVO.addNewGrupoAbertura();
				for (int i = 0; i < tmpGrupoAbertura.length; i++) {
					variaveisArvoreContatoVO.getGrupoAbertura().addId(tmpGrupoAbertura[i]);
				}
			}

			// COMBO GRUPO TRATAMENTO (10)
			if (form.getIdGrupoTratamentoAssocArray() != null && form.getIdGrupoTratamentoAssocArray().length > 0 && form.getIdGrupoTratamentoAssocArray()[0] != null) {
				String[] tmpGrupoTratamento = form.getIdGrupoTratamentoAssocArray();
				variaveisArvoreContatoVO.addNewGrupoTratamento();
				for (int i = 0; i < tmpGrupoTratamento.length; i++) {
					variaveisArvoreContatoVO.getGrupoTratamento().addId(tmpGrupoTratamento[i]);
				}
			}

			// COMBO GRUPO RETORNO (11)
			if (form.getIdGrupoRetornoAssocArray() != null && form.getIdGrupoRetornoAssocArray().length > 0 && form.getIdGrupoRetornoAssocArray()[0] != null) {
				String[] tmpGrupoRetorno = form.getIdGrupoRetornoAssocArray();
				variaveisArvoreContatoVO.addNewGrupoRetorno();
				for (int i = 0; i < tmpGrupoRetorno.length; i++) {
					variaveisArvoreContatoVO.getGrupoRetorno().addId(tmpGrupoRetorno[i]);
				}
			}

			// COMBO DISPONIVEL
			if (form.getIdDisponivelAssocArray() != null && form.getIdDisponivelAssocArray().length > 0 && form.getIdDisponivelAssocArray()[0] != null) {
				String[] tmpDisponivel = form.getIdDisponivelAssocArray();
				variaveisArvoreContatoVO.setInDisponivel(tmpDisponivel[0]);
				strb.append(CONSTANTE_SEPARA);
			}

			// COMBO PROCEDENCIA
			if (form.getIdProcedenciaAssocArray() != null && form.getIdProcedenciaAssocArray().length > 0 && form.getIdProcedenciaAssocArray()[0] != null) {
				String[] tmpProcedencia = form.getIdProcedenciaAssocArray();
				variaveisArvoreContatoVO.addNewProcedencia();
				for (int i = 0; i < tmpProcedencia.length; i++) {
					variaveisArvoreContatoVO.getProcedencia().addId(tmpProcedencia[i]);
				}
			}

			variaveisArvoreContatoFacade.exportarVariaveisArvoreContato(variaveisArvoreContatoVO, ConstantesCRM.getCurrentUser(request.getSession()));
			request.setAttribute("msgStatus", "Solicitação gerada com sucesso.");
			request.setAttribute("form", form);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("AdmArvoreParametroController:exportaRetorno" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="consultaFiltrosCadastrados.jsp"
	 * 
	 */
	public ActionForward consultaFiltrosSelecionados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		VariaveisArvoreContatoVO variaveisArvoreContatoVO = VariaveisArvoreContatoVO.Factory.newInstance();
		/*
		 * 0 - Lista de arquivos disponiveis 1 - Inserção de filtros (Combos) 2 - Consulta de filtros (este método)
		 */
		variaveisArvoreContatoVO.setInOperacao(ConstantesCRM.STWO);
		variaveisArvoreContatoVO.setIdArquivo(request.getParameter("idArquivo"));
		variaveisArvoreContatoVO = variaveisArvoreContatoFacade.getFiltrosSelecionados(variaveisArvoreContatoVO, ConstantesCRM.getCurrentUser(request.getSession()));
		request.setAttribute("arvore", getArvoreFiltrosSelecionados(variaveisArvoreContatoVO));

		getFormArvoreParametro().getVariaveisArvoreContatoVO().setInTipoConsulta(variaveisArvoreContatoVO.getInTipoConsulta());

		if (ConstantesCRM.SONE.equals(variaveisArvoreContatoVO.getInDisponivel())) {
			getFormArvoreParametro().getVariaveisArvoreContatoVO().setInDisponivel(ConstantesCRM.SYES);
		} else {
			getFormArvoreParametro().getVariaveisArvoreContatoVO().setInDisponivel("Não");
		}
		request.setAttribute("formArvoreParametro", formArvoreParametro);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @return String
	 */
	private String getArvoreFiltrosSelecionados(VariaveisArvoreContatoVO variaveisArvoreContatoVO) {

		String tree = "";

		if (variaveisArvoreContatoVO.getCanal() != null) {
			tree += "aux1 = insFld(foldersTree, gFld(\"Canal\", \"javascript:undefined\"));\n";
			for (int i = 0; i < variaveisArvoreContatoVO.getCanal().getIdArray().length; i++) {
				tree += "\taux2 = insFld(aux1, gFld(\"" + variaveisArvoreContatoVO.getCanal().getDescricaoArray(i) + "\", \"javascript:undefined\"));\n";
			}
		}
		if (variaveisArvoreContatoVO.getCarteirizacao() != null) {
			tree += "aux1 = insFld(foldersTree, gFld(\"Carteirização\", \"javascript:undefined\"));\n";
			for (int i = 0; i < variaveisArvoreContatoVO.getCarteirizacao().getIdArray().length; i++) {
				tree += "\taux2 = insFld(aux1, gFld(\"" + variaveisArvoreContatoVO.getCarteirizacao().getDescricaoArray(i) + "\", \"javascript:undefined\"));\n";
			}
		}
		if (variaveisArvoreContatoVO.getFechamento() != null) {
			tree += "aux1 = insFld(foldersTree, gFld(\"Fechamento\", \"javascript:undefined\"));\n";
			for (int i = 0; i < variaveisArvoreContatoVO.getFechamento().getIdArray().length; i++) {
				tree += "\taux2 = insFld(aux1, gFld(\"" + variaveisArvoreContatoVO.getFechamento().getDescricaoArray(i) + "\", \"javascript:undefined\"));\n";
			}
		}
		if (variaveisArvoreContatoVO.getGrupoAbertura() != null) {
			tree += "aux1 = insFld(foldersTree, gFld(\"Grupo de Abertura\", \"javascript:undefined\"));\n";
			for (int i = 0; i < variaveisArvoreContatoVO.getGrupoAbertura().getIdArray().length; i++) {
				tree += "\taux2 = insFld(aux1, gFld(\"" + variaveisArvoreContatoVO.getGrupoAbertura().getDescricaoArray(i) + "\", \"javascript:undefined\"));\n";
			}
		}
		if (variaveisArvoreContatoVO.getGrupoRetorno() != null) {
			tree += "aux1 = insFld(foldersTree, gFld(\"Grupo de Retorno\", \"javascript:undefined\"));\n";
			for (int i = 0; i < variaveisArvoreContatoVO.getGrupoRetorno().getIdArray().length; i++) {
				tree += "\taux2 = insFld(aux1, gFld(\"" + variaveisArvoreContatoVO.getGrupoRetorno().getDescricaoArray(i) + "\", \"javascript:undefined\"));\n";
			}
		}
		if (variaveisArvoreContatoVO.getGrupoTratamento() != null) {
			tree += "aux1 = insFld(foldersTree, gFld(\"Grupo de Tratamento\", \"javascript:undefined\"));\n";
			for (int i = 0; i < variaveisArvoreContatoVO.getGrupoTratamento().getIdArray().length; i++) {
				tree += "\taux2 = insFld(aux1, gFld(\"" + variaveisArvoreContatoVO.getGrupoTratamento().getDescricaoArray(i) + "\", \"javascript:undefined\"));\n";
			}
		}
		if (variaveisArvoreContatoVO.getNatureza() != null) {
			tree += "aux1 = insFld(foldersTree, gFld(\"Natureza\", \"javascript:undefined\"));\n";
			for (int i = 0; i < variaveisArvoreContatoVO.getNatureza().getIdArray().length; i++) {
				tree += "\taux2 = insFld(aux1, gFld(\"" + variaveisArvoreContatoVO.getNatureza().getDescricaoArray(i) + "\", \"javascript:undefined\"));\n";
			}
		}
		if (variaveisArvoreContatoVO.getOperadora() != null) {
			tree += "aux1 = insFld(foldersTree, gFld(\"Operadora\", \"javascript:undefined\"));\n";
			for (int i = 0; i < variaveisArvoreContatoVO.getOperadora().getIdArray().length; i++) {
				tree += "\taux2 = insFld(aux1, gFld(\"" + variaveisArvoreContatoVO.getOperadora().getDescricaoArray(i) + "\", \"javascript:undefined\"));\n";
			}
		}
		if (variaveisArvoreContatoVO.getProcedencia() != null) {
			tree += "aux1 = insFld(foldersTree, gFld(\"Procedência\", \"javascript:undefined\"));\n";
			for (int i = 0; i < variaveisArvoreContatoVO.getProcedencia().getIdArray().length; i++) {
				tree += "\taux2 = insFld(aux1, gFld(\"" + variaveisArvoreContatoVO.getProcedencia().getDescricaoArray(i) + "\", \"javascript:undefined\"));\n";
			}
		}
		if (variaveisArvoreContatoVO.getSegmentacao() != null) {
			tree += "aux1 = insFld(foldersTree, gFld(\"Segmentação\", \"javascript:undefined\"));\n";
			for (int i = 0; i < variaveisArvoreContatoVO.getSegmentacao().getIdArray().length; i++) {
				tree += "\taux2 = insFld(aux1, gFld(\"" + variaveisArvoreContatoVO.getSegmentacao().getDescricaoArray(i) + "\", \"javascript:undefined\"));\n";
			}
		}
		if (variaveisArvoreContatoVO.getTipoCliente() != null) {
			tree += "aux1 = insFld(foldersTree, gFld(\"Tipo de Cliente\", \"javascript:undefined\"));\n";
			for (int i = 0; i < variaveisArvoreContatoVO.getTipoCliente().getIdArray().length; i++) {
				tree += "\taux2 = insFld(aux1, gFld(\"" + variaveisArvoreContatoVO.getTipoCliente().getDescricaoArray(i) + "\", \"javascript:undefined\"));\n";
			}
		}
		if (variaveisArvoreContatoVO.getTipoLinha() != null) {
			tree += "aux1 = insFld(foldersTree, gFld(\"Tipo de Linha\", \"javascript:undefined\"));\n";
			for (int i = 0; i < variaveisArvoreContatoVO.getTipoLinha().getIdArray().length; i++) {
				tree += "\taux2 = insFld(aux1, gFld(\"" + variaveisArvoreContatoVO.getTipoLinha().getDescricaoArray(i) + "\", \"javascript:undefined\"));\n";
			}
		}
		return tree;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="listaArquivo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward geraListaArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		String lastDate = ConstantesCRM.SVAZIO;
		log.debug("AdmArvoreParametroController:geraListaArquivo(" + user + ")");
		try {
			VariaveisArvoreContatoVO variaveisArvoreContatoVO = VariaveisArvoreContatoVO.Factory.newInstance();
			/*
			 * 0 - Lista de arquivos disponiveis (este método) 1 - Inserção de filtros (Combos) 2 - Consulta de filtros
			 */
			variaveisArvoreContatoVO.setInOperacao(ConstantesCRM.SZERO);
			AdmVarAContatoArqGeradosVO admVarAContatoArqGeradosVO = variaveisArvoreContatoFacade.getListaArquivosGerados(variaveisArvoreContatoVO, user);
			AdmVarAContatoArqGeradosVO arqGeradosFiltrados = AdmVarAContatoArqGeradosVO.Factory.newInstance();
			int j = 0;

			for (int i = 0; i < admVarAContatoArqGeradosVO.getArquivoGeradoArray().length; i++) {
				if (!lastDate.equals(admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getDtSolicitacao())) {
					arqGeradosFiltrados.addNewArquivoGerado();
					arqGeradosFiltrados.getArquivoGeradoArray(j).setDsPath(admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getDsPath() != null ? admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getDsPath() : ConstantesCRM.SVAZIO);
					arqGeradosFiltrados.getArquivoGeradoArray(j).setDtGeracao(admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getDtGeracao() != null ? admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getDtGeracao() : ConstantesCRM.SVAZIO);
					arqGeradosFiltrados.getArquivoGeradoArray(j).setDtSolicitacao(admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getDtSolicitacao() != null ? admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getDtSolicitacao() : ConstantesCRM.SVAZIO);
					arqGeradosFiltrados.getArquivoGeradoArray(j).setId(admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getId() != null ? admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getId() : ConstantesCRM.SVAZIO);
					arqGeradosFiltrados.getArquivoGeradoArray(j).setNmArquivo(admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getNmArquivo() != null ? admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getNmArquivo() : ConstantesCRM.SVAZIO);
					arqGeradosFiltrados.getArquivoGeradoArray(j).setNmLoginSolicitante(admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getNmLoginSolicitante() != null ? admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getNmLoginSolicitante() : ConstantesCRM.SVAZIO);
					j++;
				}
				lastDate = admVarAContatoArqGeradosVO.getArquivoGeradoArray(i).getDtSolicitacao();
			}

			getFormArvoreParametro().setArquivosGerados(arqGeradosFiltrados.getArquivoGeradoArray());

		} catch (Exception e) {
			log.error("AdmArvoreParametroController:geraListaArquivo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("formArvoreParametro", formArvoreParametro);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="listaArquivo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward disponibilizaArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			log.debug("AdmArvoreParametroController:disponibilizaArquivo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
			FormArvoreParametro form = (FormArvoreParametro) formParam;
			LoadProperties prop = new LoadProperties(request);
			String path = prop.get("TREE_CONT_PATH_REL");
			File arquivo = new File(path + File.separator + form.getDsPathArquivo());

			if (arquivo != null && arquivo.isFile()) {

				response.addHeader("Content-Disposition", "attachment; filename=" + form.getDsPathArquivo());
				response.addHeader("Content-Type", "application/force-download");
				response.addHeader("Content-Transfer-Encoding", "binary");
				response.addHeader("Pragma", "no-cache");
				response.addHeader("Expires", "0");

				// int fSize = (int)arquivo.length();

				FileInputStream fis = new FileInputStream(arquivo);
				PrintWriter pw = response.getWriter();
				int c = -1;
				while ((c = fis.read()) != -1) {
					pw.print((char) c);
				}
				fis.close();
				pw.flush();
				pw = null;
				return null;

			} else {
				request.setAttribute("msgErro", "Arquivo não encontrado.");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}

		} catch (Exception e) {
			log.error("AdmArvoreParametroController:disponibilizaArquivo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	public static byte[] read(File file) throws IOException {
		byte[] content = null;
		int fileLength = (int) file.length();
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);

			BufferedInputStream bufferedInput = new BufferedInputStream(fileInput);
			content = new byte[fileLength];
			bufferedInput.read(content, 0, fileLength);
			bufferedInput.close();
		} finally {
			if (fileInput != null) {
				fileInput.close();
			}
		}
		return content;
	}

	public class GenericVO implements Serializable {

		private static final long serialVersionUID = 8048541816913923052L;

		private String codigo = ConstantesCRM.SVAZIO;
		private String descricao = ConstantesCRM.SVAZIO;

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return this.descricao;
		}

		public void setCodigo(String codigo) {
			this.codigo = codigo;
		}

		public String getCodigo() {
			return this.codigo;
		}

	}

	public static class FormArvoreParametro extends ActionForm {

		private static final long serialVersionUID = -3103820864316521382L;

		private String dsPathArquivo;
		private String idArquivo;
		private br.com.vivo.fo.admsistemas.vo.AdmVarAContatoArqGeradosVODocument.AdmVarAContatoArqGeradosVO.ArquivoGerado[] arquivosGerados;
		// private AdmVarAContatoArqGeradosVO admVarAContatoArqGeradosVO;
		private VariaveisArvoreContatoVO variaveisArvoreContatoVO;
		private String[] idProcedenciaAssocArray;
		private String[] idProcedenciaExistArray;
		private String listaArquivosLength;
		private String arquivoSelecionado;
		// private String path;
		private String[] listaArquivos;
		private String idContatoGrupoRadio;
		private String limpaForm;
		private ArrayList disponivelAssoc;
		private ArrayList disponivelExist;
		private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO containerCombosExist;
		private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO containerCombosAssoc;
		private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisExist;
		private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisAssoc;
		private String[] idTipoRelacionamentoAssocArray;
		private String[] idTipoPessoaAssocArray;
		private String[] idTipoLinhaAssocArray;
		private String[] idTipoCarteiraAssocArray;
		private String[] idSegmentacaoAssocArray;
		private String[] idOperadoraAssocArray;
		private String[] idGrupoTratamentoAssocArray;
		private String[] idGrupoRetornoAssocArray;
		private String[] idGrupoAberturaAssocArray;
		private String[] idFechamentoAssocArray;
		private String[] idDisponivelAssocArray;
		private String[] idCanalAssocArray;
		private String[] idNaturezaAssocArray;
		private String[] idNaturezaExistArray;
		private String[] idCanalExistArray;
		private String[] idTipoRelacionamentoExistArray;
		private String[] idTipoPessoaExistArray;
		private String[] idTipoLinhaExistArray;
		private String[] idTipoCarteiraExistArray;
		private String[] idSegmentacaoExistArray;
		private String[] idOperadoraExistArray;
		private String[] idGrupoTratamentoExistArray;
		private String[] idGrupoRetornoExistArray;
		private String[] idGrupoAberturaExistArray;
		private String[] idFechamentoExistArray;
		private String[] idDisponivelExistArray;
		// private String inProximo = ConstantesCRM.SVAZIO;
		private AdmVariavelRetornoVO[] admVariavelRetorno;
		private AdmGrupoParametroVO[] admArvoreGrupoRetorno;
		private AdmArvoreParametroRetornoVO admArvoreRetorno;
		// private String inRetorno = ConstantesCRM.SVAZIO;
		// private String inVariaveis = ConstantesCRM.SVAZIO;
		// private String inGrupos = ConstantesCRM.SVAZIO;
		// private String[] idContatoArray = new String[0];
		// private String dataFim = ConstantesCRM.SVAZIO;
		// private String dataInicio = ConstantesCRM.SVAZIO;
		// private String idContatoSelecionado = ConstantesCRM.SVAZIO;
		// private String inTudo = ConstantesCRM.SVAZIO;
		// private String inArvore = ConstantesCRM.SVAZIO;
		// private String dsPath = ConstantesCRM.SVAZIO;
		// private String inFolha= ConstantesCRM.SVAZIO;
		// private String inDisponibilidade= ConstantesCRM.SVAZIO;
		// private String idContatoPai= ConstantesCRM.SVAZIO;
		// private String idContato= ConstantesCRM.SVAZIO;
		private br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO containerCombos;
		private String msgError = ConstantesCRM.SVAZIO;

		// private String inServico= ConstantesCRM.SVAZIO;

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}

		public void setContainerCombos(br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO containerCombos) {
			this.containerCombos = containerCombos;
		}

		public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO getContainerCombos() {
			return this.containerCombos;
		}

		public void setAdmArvoreGrupoRetorno(AdmGrupoParametroVO[] admArvoreGrupoRetorno) {
			this.admArvoreGrupoRetorno = admArvoreGrupoRetorno;
		}

		public AdmGrupoParametroVO[] getAdmArvoreGrupoRetorno() {
			return this.admArvoreGrupoRetorno;
		}

		public void setAdmVariavelRetorno(AdmVariavelRetornoVO[] admVariavelRetorno) {
			this.admVariavelRetorno = admVariavelRetorno;
		}

		public AdmVariavelRetornoVO[] getAdmVariavelRetorno() {
			return this.admVariavelRetorno;
		}

		public void setAdmArvoreRetorno(AdmArvoreParametroRetornoVO admArvoreRetorno) {
			this.admArvoreRetorno = admArvoreRetorno;
		}

		public AdmArvoreParametroRetornoVO getAdmArvoreRetorno() {
			return this.admArvoreRetorno;
		}

		public void setIdDisponivelExistArray(String[] idDisponivelExistArray) {
			this.idDisponivelExistArray = idDisponivelExistArray;
		}

		public String[] getIdDisponivelExistArray() {
			if (this.idDisponivelExistArray == null || this.idDisponivelExistArray.length == 0) {
				this.idDisponivelExistArray = new String[1];
			}
			return this.idDisponivelExistArray;
		}

		public void setIdFechamentoExistArray(String[] idFechamentoExistArray) {
			this.idFechamentoExistArray = idFechamentoExistArray;
		}

		public String[] getIdFechamentoExistArray() {
			if (this.idFechamentoExistArray == null || this.idFechamentoExistArray.length == 0) {
				this.idFechamentoExistArray = new String[1];
			}
			return this.idFechamentoExistArray;
		}

		public void setIdGrupoAberturaExistArray(String[] idGrupoAberturaExistArray) {
			this.idGrupoAberturaExistArray = idGrupoAberturaExistArray;
		}

		public String[] getIdGrupoAberturaExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idGrupoAberturaExistArray == null || this.idGrupoAberturaExistArray.length == 0) {
				this.idGrupoAberturaExistArray = new String[1];
			}

			return this.idGrupoAberturaExistArray;
		}

		public void setIdGrupoRetornoExistArray(String[] idGrupoRetornoExistArray) {
			this.idGrupoRetornoExistArray = idGrupoRetornoExistArray;
		}

		public String[] getIdGrupoRetornoExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idGrupoRetornoExistArray == null || this.idGrupoRetornoExistArray.length == 0) {
				this.idGrupoRetornoExistArray = new String[1];
			}

			return this.idGrupoRetornoExistArray;
		}

		public void setIdGrupoTratamentoExistArray(String[] idGrupoTratamentoExistArray) {
			this.idGrupoTratamentoExistArray = idGrupoTratamentoExistArray;
		}

		public String[] getIdGrupoTratamentoExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idGrupoTratamentoExistArray == null || this.idGrupoTratamentoExistArray.length == 0) {
				this.idGrupoTratamentoExistArray = new String[1];
			}

			return this.idGrupoTratamentoExistArray;
		}

		public void setIdOperadoraExistArray(String[] idOperadoraExistArray) {
			this.idOperadoraExistArray = idOperadoraExistArray;
		}

		public String[] getIdOperadoraExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idOperadoraExistArray == null || this.idOperadoraExistArray.length == 0) {
				this.idOperadoraExistArray = new String[1];
			}

			return this.idOperadoraExistArray;
		}

		public void setIdSegmentacaoExistArray(String[] idSegmentacaoExistArray) {
			this.idSegmentacaoExistArray = idSegmentacaoExistArray;
		}

		public String[] getIdSegmentacaoExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idSegmentacaoExistArray == null || this.idSegmentacaoExistArray.length == 0) {
				this.idSegmentacaoExistArray = new String[1];
			}

			return this.idSegmentacaoExistArray;
		}

		public void setIdTipoCarteiraExistArray(String[] idTipoCarteiraExistArray) {
			this.idTipoCarteiraExistArray = idTipoCarteiraExistArray;
		}

		public String[] getIdTipoCarteiraExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idTipoCarteiraExistArray == null || this.idTipoCarteiraExistArray.length == 0) {
				this.idTipoCarteiraExistArray = new String[1];
			}

			return this.idTipoCarteiraExistArray;
		}

		public void setIdTipoLinhaExistArray(String[] idTipoLinhaExistArray) {
			this.idTipoLinhaExistArray = idTipoLinhaExistArray;
		}

		public String[] getIdTipoLinhaExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idTipoLinhaExistArray == null || this.idTipoLinhaExistArray.length == 0) {
				this.idTipoLinhaExistArray = new String[1];
			}

			return this.idTipoLinhaExistArray;
		}

		public void setIdTipoPessoaExistArray(String[] idTipoPessoaExistArray) {
			this.idTipoPessoaExistArray = idTipoPessoaExistArray;
		}

		public String[] getIdTipoPessoaExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idTipoPessoaExistArray == null || this.idTipoPessoaExistArray.length == 0) {
				this.idTipoPessoaExistArray = new String[1];
			}

			return this.idTipoPessoaExistArray;
		}

		public void setIdTipoRelacionamentoExistArray(String[] idTipoRelacionamentoExistArray) {
			this.idTipoRelacionamentoExistArray = idTipoRelacionamentoExistArray;
		}

		public String[] getIdTipoRelacionamentoExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idTipoRelacionamentoExistArray == null || this.idTipoRelacionamentoExistArray.length == 0) {
				this.idTipoRelacionamentoExistArray = new String[1];
			}

			return this.idTipoRelacionamentoExistArray;
		}

		public void setIdCanalExistArray(String[] idCanalExistArray) {
			this.idCanalExistArray = idCanalExistArray;
		}

		public String[] getIdCanalExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idCanalExistArray == null || this.idCanalExistArray.length == 0) {
				this.idCanalExistArray = new String[1];
			}

			return this.idCanalExistArray;
		}

		public void setIdNaturezaExistArray(String[] idNaturezaExistArray) {
			this.idNaturezaExistArray = idNaturezaExistArray;
		}

		public String[] getIdNaturezaExistArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idNaturezaExistArray == null || this.idNaturezaExistArray.length == 0) {
				this.idNaturezaExistArray = new String[1];
			}

			return this.idNaturezaExistArray;
		}

		public void setIdNaturezaAssocArray(String[] idNaturezaAssocArray) {
			this.idNaturezaAssocArray = idNaturezaAssocArray;
		}

		public String[] getIdNaturezaAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idNaturezaAssocArray == null || this.idNaturezaAssocArray.length == 0) {
				this.idNaturezaAssocArray = new String[1];
			}

			return this.idNaturezaAssocArray;
		}

		public void setIdCanalAssocArray(String[] idCanalAssocArray) {
			this.idCanalAssocArray = idCanalAssocArray;
		}

		public String[] getIdCanalAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idCanalAssocArray == null || this.idCanalAssocArray.length == 0) {
				this.idCanalAssocArray = new String[1];
			}

			return this.idCanalAssocArray;
		}

		public void setIdDisponivelAssocArray(String[] idDisponivelAssocArray) {
			this.idDisponivelAssocArray = idDisponivelAssocArray;
		}

		public String[] getIdDisponivelAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idDisponivelAssocArray == null || this.idDisponivelAssocArray.length == 0) {
				this.idDisponivelAssocArray = new String[1];
			}

			return this.idDisponivelAssocArray;
		}

		public void setIdFechamentoAssocArray(String[] idFechamentoAssocArray) {
			this.idFechamentoAssocArray = idFechamentoAssocArray;
		}

		public String[] getIdFechamentoAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idFechamentoAssocArray == null || this.idFechamentoAssocArray.length == 0) {
				this.idFechamentoAssocArray = new String[1];
			}

			return this.idFechamentoAssocArray;
		}

		public void setIdGrupoAberturaAssocArray(String[] idGrupoAberturaAssocArray) {
			this.idGrupoAberturaAssocArray = idGrupoAberturaAssocArray;
		}

		public String[] getIdGrupoAberturaAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idGrupoAberturaAssocArray == null || this.idGrupoAberturaAssocArray.length == 0) {
				this.idGrupoAberturaAssocArray = new String[1];
			}

			return this.idGrupoAberturaAssocArray;
		}

		public void setIdGrupoRetornoAssocArray(String[] idGrupoRetornoAssocArray) {
			this.idGrupoRetornoAssocArray = idGrupoRetornoAssocArray;
		}

		public String[] getIdGrupoRetornoAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idGrupoRetornoAssocArray == null || this.idGrupoRetornoAssocArray.length == 0) {
				this.idGrupoRetornoAssocArray = new String[1];
			}

			return this.idGrupoRetornoAssocArray;
		}

		public void setIdGrupoTratamentoAssocArray(String[] idGrupoTratamentoAssocArray) {
			this.idGrupoTratamentoAssocArray = idGrupoTratamentoAssocArray;
		}

		public String[] getIdGrupoTratamentoAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idGrupoTratamentoAssocArray == null || this.idGrupoTratamentoAssocArray.length == 0) {
				this.idGrupoTratamentoAssocArray = new String[1];
			}

			return this.idGrupoTratamentoAssocArray;
		}

		public void setIdOperadoraAssocArray(String[] idOperadoraAssocArray) {
			this.idOperadoraAssocArray = idOperadoraAssocArray;
		}

		public String[] getIdOperadoraAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idOperadoraAssocArray == null || this.idOperadoraAssocArray.length == 0) {
				this.idOperadoraAssocArray = new String[1];
			}

			return this.idOperadoraAssocArray;
		}

		public void setIdSegmentacaoAssocArray(String[] idSegmentacaoAssocArray) {
			this.idSegmentacaoAssocArray = idSegmentacaoAssocArray;
		}

		public String[] getIdSegmentacaoAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idSegmentacaoAssocArray == null || this.idSegmentacaoAssocArray.length == 0) {
				this.idSegmentacaoAssocArray = new String[1];
			}

			return this.idSegmentacaoAssocArray;
		}

		public void setIdTipoCarteiraAssocArray(String[] idTipoCarteiraAssocArray) {
			this.idTipoCarteiraAssocArray = idTipoCarteiraAssocArray;
		}

		public String[] getIdTipoCarteiraAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idTipoCarteiraAssocArray == null || this.idTipoCarteiraAssocArray.length == 0) {
				this.idTipoCarteiraAssocArray = new String[1];
			}

			return this.idTipoCarteiraAssocArray;
		}

		public void setIdTipoLinhaAssocArray(String[] idTipoLinhaAssocArray) {
			this.idTipoLinhaAssocArray = idTipoLinhaAssocArray;
		}

		public String[] getIdTipoLinhaAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idTipoLinhaAssocArray == null || this.idTipoLinhaAssocArray.length == 0) {
				this.idTipoLinhaAssocArray = new String[1];
			}

			return this.idTipoLinhaAssocArray;
		}

		public void setIdTipoPessoaAssocArray(String[] idTipoPessoaAssocArray) {
			this.idTipoPessoaAssocArray = idTipoPessoaAssocArray;
		}

		public String[] getIdTipoPessoaAssocArray() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idTipoPessoaAssocArray == null || this.idTipoPessoaAssocArray.length == 0) {
				this.idTipoPessoaAssocArray = new String[1];
			}

			return this.idTipoPessoaAssocArray;
		}

		public void setIdTipoRelacionamentoAssocArray(String[] idTipoRelacionamentoAssocArray) {
			this.idTipoRelacionamentoAssocArray = idTipoRelacionamentoAssocArray;
		}

		public String[] getIdTipoRelacionamentoAssocArray() {
			if (this.idTipoRelacionamentoAssocArray == null || this.idTipoRelacionamentoAssocArray.length == 0) {
				this.idTipoRelacionamentoAssocArray = new String[1];
			}

			return this.idTipoRelacionamentoAssocArray;
		}

		public void setDadosAtuaisAssoc(br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisAssoc) {
			this.dadosAtuaisAssoc = dadosAtuaisAssoc;
		}

		public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais getDadosAtuaisAssoc() {
			if (this.dadosAtuaisAssoc == null) {
				this.dadosAtuaisAssoc = DadosAtuais.Factory.newInstance();
			}

			return this.dadosAtuaisAssoc;
		}

		public void setDadosAtuaisExist(br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais dadosAtuaisExist) {
			this.dadosAtuaisExist = dadosAtuaisExist;
		}

		public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO.DadosAtuais getDadosAtuaisExist() {
			if (this.dadosAtuaisExist == null) {
				this.dadosAtuaisExist = DadosAtuais.Factory.newInstance();
			}

			return this.dadosAtuaisExist;
		}

		public void setContainerCombosAssoc(br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO containerCombosAssoc) {
			this.containerCombosAssoc = containerCombosAssoc;
		}

		public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO getContainerCombosAssoc() {
			if (this.containerCombosAssoc == null) {
				this.containerCombosAssoc = AdmArvoreParametroCombosVO.Factory.newInstance();
			}
			return this.containerCombosAssoc;
		}

		public void setContainerCombosExist(br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO containerCombosExist) {
			this.containerCombosExist = containerCombosExist;
		}

		public br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroCombosVODocument.AdmArvoreParametroCombosVO getContainerCombosExist() {
			if (this.containerCombosExist == null) {
				this.containerCombosExist = AdmArvoreParametroCombosVO.Factory.newInstance();
			}
			return this.containerCombosExist;
		}

		public void setDisponivelExist(ArrayList disponivelExist) {
			this.disponivelExist = disponivelExist;
		}

		public ArrayList getDisponivelExist() {
			if (this.disponivelExist == null) {
				this.disponivelExist = new ArrayList();
			}
			return this.disponivelExist;
		}

		public void setDisponivelAssoc(ArrayList disponivelAssoc) {
			this.disponivelAssoc = disponivelAssoc;
		}

		public ArrayList getDisponivelAssoc() {
			if (this.disponivelAssoc == null) {
				this.disponivelAssoc = new ArrayList();
			}
			return this.disponivelAssoc;
		}

		public void setLimpaForm(String limpaForm) {
			this.limpaForm = limpaForm;
		}

		public String getLimpaForm() {
			return this.limpaForm;
		}

		public void setIdContatoGrupoRadio(String idContatoGrupoRadio) {
			this.idContatoGrupoRadio = idContatoGrupoRadio;
		}

		public String getIdContatoGrupoRadio() {
			if (this.idContatoGrupoRadio == null) {
				this.idContatoGrupoRadio = ConstantesCRM.SVAZIO;
			}
			return this.idContatoGrupoRadio;
		}

		public void setListaArquivos(String[] listaArquivos) {
			this.listaArquivos = listaArquivos;
		}

		public String[] getListaArquivos() {
			if (this.listaArquivos == null || this.listaArquivos.length == 0) {
				this.listaArquivos = new String[1];
			}
			return this.listaArquivos;
		}

		public void setArquivoSelecionado(String arquivoSelecionado) {
			this.arquivoSelecionado = arquivoSelecionado;
		}

		public String getArquivoSelecionado() {
			return this.arquivoSelecionado;
		}

		public void setListaArquivosLength(String listaArquivosLength) {
			this.listaArquivosLength = listaArquivosLength;
		}

		public String getListaArquivosLength() {
			return this.listaArquivosLength;
		}

		public void setIdProcedenciaExistArray(String[] idProcedenciaExistArray) {
			this.idProcedenciaExistArray = idProcedenciaExistArray;
		}

		public String[] getIdProcedenciaExistArray() {
			if (this.idProcedenciaExistArray == null || this.idProcedenciaExistArray.length == 0) {
				this.idProcedenciaExistArray = new String[1];
			}
			return this.idProcedenciaExistArray;
		}

		public void setIdProcedenciaAssocArray(String[] idProcedenciaAssocArray) {
			this.idProcedenciaAssocArray = idProcedenciaAssocArray;
		}

		public String[] getIdProcedenciaAssocArray() {
			if (this.idProcedenciaAssocArray == null || this.idProcedenciaAssocArray.length == 0) {
				this.idProcedenciaAssocArray = new String[1];
			}
			return this.idProcedenciaAssocArray;
		}

		public void setVariaveisArvoreContatoVO(VariaveisArvoreContatoVO variaveisArvoreContatoVO) {
			this.variaveisArvoreContatoVO = variaveisArvoreContatoVO;
		}

		public VariaveisArvoreContatoVO getVariaveisArvoreContatoVO() {
			if (this.variaveisArvoreContatoVO == null) {
				this.variaveisArvoreContatoVO = VariaveisArvoreContatoVO.Factory.newInstance();
			}
			return this.variaveisArvoreContatoVO;
		}

		public void setArquivosGerados(br.com.vivo.fo.admsistemas.vo.AdmVarAContatoArqGeradosVODocument.AdmVarAContatoArqGeradosVO.ArquivoGerado[] arquivosGerados) {
			this.arquivosGerados = arquivosGerados;
		}

		public br.com.vivo.fo.admsistemas.vo.AdmVarAContatoArqGeradosVODocument.AdmVarAContatoArqGeradosVO.ArquivoGerado[] getArquivosGerados() {
			return this.arquivosGerados;
		}

		public void setIdArquivo(String idArquivo) {
			this.idArquivo = idArquivo;
		}

		public String getIdArquivo() {
			return this.idArquivo;
		}

		public void setDsPathArquivo(String dsPathArquivo) {
			this.dsPathArquivo = dsPathArquivo;
		}

		public String getDsPathArquivo() {
			return this.dsPathArquivo;
		}
	}

	public FormArvoreParametro getFormArvoreParametro() {
		if (this.formArvoreParametro == null) {
			this.formArvoreParametro = new FormArvoreParametro();
		}
		return this.formArvoreParametro;
	}
}