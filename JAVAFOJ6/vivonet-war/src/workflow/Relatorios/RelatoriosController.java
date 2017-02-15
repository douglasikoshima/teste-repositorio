package workflow.Relatorios;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Stack;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import br.com.vivo.fo.admsistemas.vo.AdmArvoreContainerVODocument.AdmArvoreContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoVODocument.AdmContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.configArvoreContato.ArvoreContato;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade;
import br.com.vivo.fo.ctrls.workflow.relatorios.RelatorioFacadeWF;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.AtendimentoArvoreFiltrosVODocument.AtendimentoArvoreFiltrosVO;
import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;
import br.com.vivo.fo.workflow.vo.WFListaArquivosGeradosVODocument.WFListaArquivosGeradosVO;
import br.com.vivo.fo.workflow.vo.WFRFRVODocument.WFRFRVO;
import br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO;
import br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO.ValoresRelatorio;
import br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO.ValoresRelatorio.ValorColuna;
import br.com.vivo.fo.workflow.vo.WFRelatoriosFiltroRegionalVODocument.WFRelatoriosFiltroRegionalVO;
import br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO;
import br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO.FiltroDetalhe;
import br.com.vivo.fo.workflow.vo.WFRelatoriosQuebraVODocument.WFRelatoriosQuebraVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
public class RelatoriosController extends AbstractAction {

	private static final long serialVersionUID = -3122322050632250492L;

	private static Logger log = new Logger("workflow");
	private RelatorioForm relatorioForm = new RelatorioForm();

	private static final int NUM_LINHAS_BLOCO = 100;

	private static HashMap<String, String> hashQuebrasDescricao = new HashMap<String, String>();
	private static HashMap<String, String> hNiveis;
	private static HashMap<String, String> hRel6Titulos;
	private static String menosuno = new String("-1");

	private Stack stNiveisPalitagem;
	private Stack stFiltrosPalitagem;
	private Stack stContatosPalitagem;

	// Inicialização de atributos estáticos

	static {
		// hashmap de nivies do organograma
		hNiveis = new HashMap<String, String>(5);
		hNiveis.put("D", "Diretoria");
		hNiveis.put("A", "Área");
		hNiveis.put("S", "Sessão");
		hNiveis.put("C", "Cargo");
		hNiveis.put("", "\"\"");

		// HashMap de Titulos do Relatorio 6
		hRel6Titulos = new HashMap<String, String>(5);
		hRel6Titulos.put("1", "Tempo Médio Tratamento Representante");
		hRel6Titulos.put("2", "Tempo Médio na Fila");
		hRel6Titulos.put("3", "Tempo Medio em Pausa");
		hRel6Titulos.put("4", "Tempo Médio em Pausa (Grupo)");
		hRel6Titulos.put("5", "Tempo Médio Tratamento Processo");

		// HashMap de nomes de Campos para quebras (Agrupamentos)
		hashQuebrasDescricao = new HashMap<String, String>(16);
		hashQuebrasDescricao.put("nmGrupoOperadora", "Operadora");
		hashQuebrasDescricao.put("UF", "UF");
		hashQuebrasDescricao.put("nmGrupo", "Grupo");
		hashQuebrasDescricao.put("nmLoginUsuarioAtual", "Login Representante");
		hashQuebrasDescricao.put("nmUsuarioAtual", "Nome Representante");
		hashQuebrasDescricao.put("Responsavel", "Gestor");
		hashQuebrasDescricao.put("dsTipoCarteira", "Carteira");
		hashQuebrasDescricao.put("dsSegmentacao", "Segmentação");
		hashQuebrasDescricao.put("idContato", "Contato");
		hashQuebrasDescricao.put("dsFase", "Status");
		hashQuebrasDescricao.put("nmNome", "Nome Repr.");
		hashQuebrasDescricao.put("nmPessoa", "Nome Repr.");
		hashQuebrasDescricao.put("nmLoginUsuario", "Login");
		hashQuebrasDescricao.put("sgStatusUsuario", "Status");
		hashQuebrasDescricao.put("tempoDemoraDias", "Tempo de Demora (días)");
		hashQuebrasDescricao.put("idAtendimento", "Num. Processo");
	}

	@EJB
	private RelatorioFacadeWF relatorioFacade;

	@EJB
	private RegistrarContatoFacade registrarContatoFacade;

	@EJB
	private ArvoreContato controlArvore;

	@EJB
	private TelaInicialFacade telaInicialFacade;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);

		} else if ("obterRegionais".equals(mapping.getParameter())) {
			return obterRegionais(mapping, form, request, response);

		} else if ("obterRepresentantes".equals(mapping.getParameter())) {
			return obterRepresentantes(mapping, form, request, response);

		} else if ("beginTotalGrupoDestinoBKO".equals(mapping.getParameter())) {
			return beginTotalGrupoDestinoBKO(mapping, form, request, response);

		} else if ("gerarTotalGrupoDestinoBKO".equals(mapping.getParameter())) {
			return gerarTotalGrupoDestinoBKO(mapping, form, request, response);

		} else if ("gerarTotalGrupoDestinoBKODownload".equals(mapping.getParameter())) {
			return gerarTotalGrupoDestinoBKODownload(mapping, form, request, response);

		} else if ("beginTotalRepresentanteBKO".equals(mapping.getParameter())) {
			return beginTotalRepresentanteBKO(mapping, form, request, response);

		} else if ("gerarTotalRepresentanteBKO".equals(mapping.getParameter())) {
			return gerarTotalRepresentanteBKO(mapping, form, request, response);

		} else if ("gerarTotalRepresentanteBKODownload".equals(mapping.getParameter())) {
			return gerarTotalRepresentanteBKODownload(mapping, form, request, response);

		} else if ("beginProdRepresentanteBKO".equals(mapping.getParameter())) {
			return beginProdRepresentanteBKO(mapping, form, request, response);

		} else if ("gerarProdRepresentanteBKO".equals(mapping.getParameter())) {
			return gerarProdRepresentanteBKO(mapping, form, request, response);

		} else if ("beginQtdMotivoRepresentante".equals(mapping.getParameter())) {
			return beginQtdMotivoRepresentante(mapping, form, request, response);

		} else if ("gerarQtdMotivoRepresentante".equals(mapping.getParameter())) {
			return gerarQtdMotivoRepresentante(mapping, form, request, response);

		} else if ("beginTempoMedioFila".equals(mapping.getParameter())) {
			return beginTempoMedioFila(mapping, form, request, response);

		} else if ("gerarTempoMedioFila".equals(mapping.getParameter())) {
			return gerarTempoMedioFila(mapping, form, request, response);

		} else if ("gerarTempoMedioFilaDetalhe".equals(mapping.getParameter())) {
			return gerarTempoMedioFilaDetalhe(mapping, form, request, response);

		} else if ("beginQtdPalitagem".equals(mapping.getParameter())) {
			return beginQtdPalitagem(mapping, form, request, response);

		} else if ("gerarQtdPalitagem1".equals(mapping.getParameter())) {
			return gerarQtdPalitagem1(mapping, form, request, response);

		} else if ("gerarQtdPalitagem2".equals(mapping.getParameter())) {
			return gerarQtdPalitagem2(mapping, form, request, response);

		} else if ("gerarQtdPalitagemVoltar".equals(mapping.getParameter())) {
			return gerarQtdPalitagemVoltar(mapping, form, request, response);

		} else if ("gerarQtdPalitagem1Imprimir".equals(mapping.getParameter())) {
			return gerarQtdPalitagem1Imprimir(mapping, form, request, response);

		} else if ("beginAtdEncIncorreto".equals(mapping.getParameter())) {
			return beginAtdEncIncorreto(mapping, form, request, response);

		} else if ("gerarAtdEncIncorreto".equals(mapping.getParameter())) {
			return gerarAtdEncIncorreto(mapping, form, request, response);

		} else if ("obterArvoreContato".equals(mapping.getParameter())) {
			return obterArvoreContato(mapping, form, request, response);

		} else if ("expandeArvoreContato".equals(mapping.getParameter())) {
			return expandeArvoreContato(mapping, form, request, response);

		} else if ("beginRelatorio1CRI".equals(mapping.getParameter())) {
			return beginRelatorio1CRI(mapping, form, request, response);

		} else if ("beginPalitagens".equals(mapping.getParameter())) {
			return beginPalitagens(mapping, form, request, response);

		} else if ("palitagensArquivosGerados".equals(mapping.getParameter())) {
			return palitagensArquivosGerados(mapping, form, request, response);

		} else if ("downloadArquivoPalitagens".equals(mapping.getParameter())) {
			return downloadArquivoPalitagens(mapping, form, request, response);

		} else if ("consultaFiltrosSelecionados".equals(mapping.getParameter())) {
			return consultaFiltrosSelecionados(mapping, form, request, response);

		} else if ("gerarRelatorioPalitagens".equals(mapping.getParameter())) {
			return gerarRelatorioPalitagens(mapping, form, request, response);

		} else if ("montaArvoreParte".equals(mapping.getParameter())) {
			return montaArvoreParte(mapping, form, request, response);

		} else if ("gerarRelatorio1CRI".equals(mapping.getParameter())) {
			return gerarRelatorio1CRI(mapping, form, request, response);

		} else if ("exportarRelatorio1CRI".equals(mapping.getParameter())) {
			return exportarRelatorio1CRI(mapping, form, request, response);

		} else if ("relatorioImprimir".equals(mapping.getParameter())) {
			return relatorioImprimir(mapping, form, request, response);

		} else if ("relatorioExportar".equals(mapping.getParameter())) {
			return relatorioExportar(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="beginAtdEncIncorreto.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:begin.do - Relatorios- Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="obterRegionais.jsp"
	 */
	protected ActionForward obterRegionais(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;

		log.debug("RelatoriosController:obterRegionais.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		if (form.getOperadoraSel().equals(menosuno)) {
			// Se selecionou o grupo em branco, não faz nada
			relatorioForm.getWFRelatoriosFiltrosVO().setWFRelatoriosFiltroRegionalVOArray(new WFRelatoriosFiltroRegionalVO[0]);
		} else {
			// Obtém os sub-estados para o estado selecionado
			relatorioForm.getWFRelatoriosFiltrosVO().setWFRelatoriosFiltroRegionalVOArray(relatorioFacade.obtemRegionais(user, form.getOperadoraSel()));
		}

		log.debug("RelatoriosController:obterRegionais.do - Relatorios- Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="obterRepresentantes.jsp"
	 */
	protected ActionForward obterRepresentantes(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;

		log.debug("RelatoriosController:obterRepresentantes.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		if (form.getGrupoSel().equals(menosuno)) {
			// Se selecionou o grupo em branco, não faz nada
			relatorioForm.getWFRelatoriosFiltrosVO().setWFRFRVOArray(new WFRFRVO[0]);
		} else {
			// Obtém os sub-estados para o estado selecionado
			relatorioForm.getWFRelatoriosFiltrosVO().setWFRFRVOArray(relatorioFacade.obtemRepresentantes(user, form.getGrupoSel()));
		}

		// relatorioForm.setGrupoSel(form.getGrupoSel());
		log.debug("RelatoriosController:obterRepresentantes.do - Relatorios- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="totalGrupoDestBko.jsp"
	 */
	protected ActionForward beginTotalGrupoDestinoBKO(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		// Monta o log da operação se possível

		log.debug("RelatoriosController:beginTotalGrupoDestinoBKO.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		relatorioForm.setWFRelatoriosFiltrosVO(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));
		relatorioForm.setInicioAction("beginTotalGrupoDestinoBKO");
		relatorioForm.setPrazo("V");

		log.debug("RelatoriosController:begin.do - [inicioAction = beginTotalGrupoDestinoBKO" + " prazo = V" + "]");

		log.debug("RelatoriosController:obterRepresentantes.do - Relatorios- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosResultado.jsp"
	 */
	protected ActionForward gerarTotalGrupoDestinoBKO(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;

		log.debug("RelatoriosController:gerarTotalGrupoDestinoBKO.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFRelatoriosFiltrosVO filtro = null;
		String refresh = request.getParameter("refresh");
		int bloco = Integer.parseInt(request.getParameter("bloco") == null ? ConstantesCRM.SZERO : request.getParameter("bloco"));
		if (refresh == null) {
			filtro = WFRelatoriosFiltrosVO.Factory.newInstance();
			filtro.setInTotal(ConstantesCRM.SZERO);

			// 10 linhas por página
			filtro.setQtdLinhasBloco(NUM_LINHAS_BLOCO);
			log.debug("RelatoriosController:gerarTotalGrupoDestinoBKO.do - [qtdLinhasBloco = " + NUM_LINHAS_BLOCO + "]");
			if (!form.getOperadoraSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroOperadoraVO().setIdOperadora(form.getOperadoraSel());
				log.debug("RelatoriosController:gerarTotalGrupoDestinoBKO.do - [idOperadora = " + form.getOperadoraSel() + "]");
			}
			if (!form.getRegionalSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroRegionalVO().setIdRegional(form.getRegionalSel());
				log.debug("RelatoriosController:gerarTotalGrupoDestinoBKO.do - [idRegional = " + form.getRegionalSel() + "]");
			}
			if (!form.getGrupoSel().equals(menosuno)) {
				filtro.addNewWFGrupoVO().setIdGrupo(form.getGrupoSel());
				log.debug("RelatoriosController:gerarTotalGrupoDestinoBKO.do - [idGrupo = " + form.getGrupoSel() + "]");
			}
			if (!form.getCarteiraSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroCarteiraVO().setIdCarteira(form.getCarteiraSel());
				log.debug("RelatoriosController:gerarTotalGrupoDestinoBKO.do - [idCarteira = " + form.getCarteiraSel() + "]");
			}
			if (!form.getSegmentoSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroSegmentoVO().setIdSegmento(form.getSegmentoSel());
				log.debug("RelatoriosController:gerarTotalGrupoDestinoBKO.do - [idSegmento = " + form.getSegmentoSel() + "]");
			}
			if (form.getPrazo() != null) {
				filtro.setPrazo(form.getPrazo());
				log.debug("RelatoriosController:gerarTotalGrupoDestinoBKO.do - [prazo = " + form.getPrazo() + "]");
			}

			if (form.getEstadoSel() != null) {
				// for (int i=0; i<form.getEstadoSel().length; i++) {
				int i = 0;
				do {
					filtro.addNewWFEstadoVO().setIdEstado(form.getEstadoSel()[i]);
					log.debug("RelatoriosController:gerarTotalGrupoDestinoBKO.do - [idEstado = " + form.getEstadoSel()[i] + "]");
					i++;
				} while (i < form.getEstadoSel().length);
			}

			configuraFiltroOrganograma(form, filtro);

			if (form.getQuebra() != null) {
				// for (int i=0; i<form.getQuebra().length; i++) {
				int i = 0;
				do {
					WFRelatoriosQuebraVO quebra = filtro.addNewWFRelatoriosQuebraVO();
					quebra.setIdCampo(form.getQuebra()[i]);
					quebra.setDsCampo(hashQuebrasDescricao.get(form.getQuebra()[i]));
					if (form.getQuebra()[i].equals("nmGrupo")) {
						quebra.setDsCampo("Grupo de Destino");
					}
					i++;
				} while (i < form.getQuebra().length);
			}
			relatorioForm.setFiltro(filtro);
			relatorioForm.setAtualizacao(form.getAtualizacao());

			relatorioForm.setDetalheAction("gerarTotalGrupoDestinoBKODownload");
			relatorioForm.setGerarAction("gerarTotalGrupoDestinoBKO");
			relatorioForm.setDetalheScript("submitDownload(this)");
			relatorioForm.setMostrarFiltros("S");
			relatorioForm.setExportar(false);

			// configura cabeçalhos de relatório
			relatorioForm.setCabecRelatorios(new LinkedHashMap());
			relatorioForm.getCabecRelatorios().put("Prazo", form.getPrazo().equals("V") ? "VIVO" : "ANATEL");
			// relatorioForm.getCabecRelatorios().put("Gestor",hNiveis.get(form.getNivelSel()!=null?form.getNivelSel():""));

		} else {
			filtro = relatorioForm.getFiltro();
			filtro.setInTotal(String.valueOf(Integer.parseInt(filtro.getInTotal()) + bloco));
		}

		WFRelatorioDinamicoVO relatorio = relatorioFacade.gerarTotalGrupoDestinoBKO(user, filtro);
		relatorioForm.setwFRelatorioDinamicoVO(relatorio);
		relatorio.setDsTituloRelatorio("Relatorios FO - Total por Grupo de Destino BKO");
		log.debug("RelatoriosController:gerarTotalGrupoDestinoBKO.do - Relatorios- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosResultado.jsp"
	 */
	protected ActionForward gerarTotalGrupoDestinoBKODownload(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;

		log.debug("RelatoriosController:gerarTotalGrupoDestinoBKODownload.do - Relatorios- Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		WFRelatoriosFiltrosVO filtro = geraFiltroDetalhes(form);
		gerarDownloadRelatorio(filtro, "TotalGrupoDestinoBKO.csv", "Relatorios FO - Total por Grupo de Destino BKO - Detalhes", request, response);

		log.debug("RelatoriosController:gerarTotalGrupoDestinoBKODownload.do - Relatorios- Fim do Metodo]");

		return null;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="totalRepBko.jsp"
	 */
	protected ActionForward beginTotalRepresentanteBKO(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;

		log.debug("RelatoriosController:beginTotalRepresentanteBKO.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		relatorioForm.setWFRelatoriosFiltrosVO(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));

		relatorioForm.setInicioAction("beginTotalRepresentanteBKO");
		relatorioForm.setPrazo("V");

		log.debug("RelatoriosController:beginTotalRepresentanteBKO.do - Relatorios- Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosResultado.jsp"
	 */
	protected ActionForward gerarTotalRepresentanteBKO(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;

		log.debug("RelatoriosController:gerarTotalRepresentanteBKO.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFRelatoriosFiltrosVO filtro = null;
		String refresh = request.getParameter("refresh");
		int bloco = Integer.parseInt(request.getParameter("bloco") == null ? ConstantesCRM.SZERO : request.getParameter("bloco"));

		if (refresh == null) {

			filtro = WFRelatoriosFiltrosVO.Factory.newInstance();
			filtro.setInRepresentante(ConstantesCRM.SONE);
			log.debug("RelatoriosController:gerarTotalRepresentanteBKO.do - [inRepresentante = " + "1" + "]");
			filtro.setInTotal("0");
			log.debug("RelatoriosController:gerarTotalRepresentanteBKO.do - [inTotal = " + "0" + "]");
			// 10 linhas por página
			filtro.setQtdLinhasBloco(NUM_LINHAS_BLOCO);
			log.debug("RelatoriosController:gerarTotalRepresentanteBKO.do - [qtdLinhasBloco = " + NUM_LINHAS_BLOCO + "]");

			if (!form.getOperadoraSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroOperadoraVO().setIdOperadora(form.getOperadoraSel());
				log.debug("RelatoriosController:gerarTotalRepresentanteBKO.do - [idOperadora = " + form.getOperadoraSel() + "]");
			}
			if (!form.getRegionalSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroRegionalVO().setIdRegional(form.getRegionalSel());
				log.debug("RelatoriosController:gerarTotalRepresentanteBKO.do - [idRegional = " + form.getRegionalSel() + "]");
			}
			if (!form.getGrupoSel().equals(menosuno)) {
				filtro.addNewWFGrupoVO().setIdGrupo(form.getGrupoSel());
				log.debug("RelatoriosController:gerarTotalRepresentanteBKO.do - [idGrupo = " + form.getGrupoSel() + "]");
			}
			if (!form.getRepresentanteSel().equals(menosuno)) {
				filtro.addNewWFRFRVO().setId(form.getRepresentanteSel());
				log.debug("RelatoriosController:gerarTotalRepresentanteBKO.do - [idRepresentanteSel = " + form.getRepresentanteSel() + "]");
			}
			if (!form.getCarteiraSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroCarteiraVO().setIdCarteira(form.getCarteiraSel());
				log.debug("RelatoriosController:gerarTotalRepresentanteBKO.do - [idCarteira = " + form.getCarteiraSel() + "]");
			}
			if (!form.getSegmentoSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroSegmentoVO().setIdSegmento(form.getSegmentoSel());
				log.debug("RelatoriosController:gerarTotalRepresentanteBKO.do - [idSegmento = " + form.getSegmentoSel() + "]");
			}
			if (form.getPrazo() != null) {
				filtro.setPrazo(form.getPrazo());
				log.debug("RelatoriosController:gerarTotalRepresentanteBKO.do - [prazo = " + form.getPrazo() + "]");
			}

			if (form.getEstadoSel() != null) {
				// for (int i=0; i<form.getEstadoSel().length; i++) {
				int i = 0;
				do {
					filtro.addNewWFEstadoVO().setIdEstado(form.getEstadoSel()[i]);
					log.debug("RelatoriosController:gerarTotalRepresentanteBKO.do - [idEstado = " + form.getEstadoSel()[i] + "]");
					i++;
				} while (i < form.getEstadoSel().length);
			}

			configuraFiltroOrganograma(form, filtro);

			if (form.getQuebra() != null) {
				// for (int i=0; i<form.getQuebra().length; i++) {
				int i = 0;
				do {
					WFRelatoriosQuebraVO quebra = filtro.addNewWFRelatoriosQuebraVO();
					quebra.setIdCampo(form.getQuebra()[i]);
					quebra.setDsCampo(hashQuebrasDescricao.get(form.getQuebra()[i]));
					if (form.getQuebra()[i].equals("nmGrupo")) {
						quebra.setDsCampo("Grupo de Destino");
					}
					i++;
				} while (i < form.getQuebra().length);
			}

			relatorioForm.setFiltro(filtro);
			relatorioForm.setAtualizacao(form.getAtualizacao());

			relatorioForm.setDetalheAction("gerarTotalRepresentanteBKODownload");
			relatorioForm.setGerarAction("gerarTotalRepresentanteBKO");
			relatorioForm.setDetalheScript("submitDownload(this)");
			relatorioForm.setMostrarFiltros("S");
			relatorioForm.setExportar(false);

			// configura cabeçalhos de relatório
			relatorioForm.setCabecRelatorios(new LinkedHashMap());
			relatorioForm.getCabecRelatorios().put("Prazo", form.getPrazo().equals("V") ? "VIVO" : "ANATEL");
			// relatorioForm.getCabecRelatorios().put("Gestor",hNiveis.get(form.getNivelSel()!=null?form.getNivelSel():""));

		} else {
			filtro = relatorioForm.getFiltro();
			filtro.setInTotal(String.valueOf(Integer.parseInt(filtro.getInTotal()) + bloco));
		}

		WFRelatorioDinamicoVO relatorio = relatorioFacade.gerarTotalRepresentateBKO(user, filtro);
		relatorio.setDsTituloRelatorio("Relatorios FO - Total por Representante BKO");
		relatorioForm.setwFRelatorioDinamicoVO(relatorio);

		log.debug("RelatoriosController:gerarTotalRepresentanteBKO.do - Relatorios- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosResultado.jsp"
	 */
	protected ActionForward gerarTotalRepresentanteBKODownload(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;

		log.debug("RelatoriosController:gerarTotalRepresentanteBKODownload.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFRelatoriosFiltrosVO filtro = geraFiltroDetalhes(form);

		gerarDownloadRelatorio2(filtro, "TotalRepresentanteBKO.csv", "Relatorios FO - Total por Grupo de Destino BKO - Detalhes", request, response);

		log.debug("RelatoriosController:gerarTotalRepresentanteBKODownload.do - Relatorios- Fim do Metodo]");

		return null;
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="prodRepBko.jsp"
	 */
	protected ActionForward beginProdRepresentanteBKO(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;

		log.debug("RelatoriosController:beginProdRepresentanteBKO.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		relatorioForm.setWFRelatoriosFiltrosVO(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));
		relatorioForm.setInicioAction("beginProdRepresentanteBKO");
		relatorioForm.setGerarAction(ConstantesCRM.SVAZIO);
		relatorioForm.setAtualizacao(ConstantesCRM.SZERO);

		log.debug("RelatoriosController:beginProdRepresentanteBKO.do - Relatorios- Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosResultado.jsp"
	 */
	protected ActionForward gerarProdRepresentanteBKO(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;

		log.debug("RelatoriosController:gerarProdRepresentanteBKO.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFRelatoriosFiltrosVO filtro = null;
		String refresh = request.getParameter("refresh");
		int bloco = Integer.parseInt(request.getParameter("bloco") == null ? ConstantesCRM.SZERO : request.getParameter("bloco"));
		if (refresh == null) {

			// guardar esses valores no form para detalhe do relatorio
			relatorioForm.setDtInicio(form.getDtInicio());
			relatorioForm.setDtFim(form.getDtFim());

			filtro = WFRelatoriosFiltrosVO.Factory.newInstance();
			filtro.setDtInicio(form.dtInicio);
			log.debug("RelatoriosController:gerarProdRepresentanteBKO.do - [dtInicio = " + form.dtInicio + "]");
			filtro.setDtFim(form.dtFim);
			log.debug("RelatoriosController:gerarProdRepresentanteBKO.do - [dtFim = " + form.dtFim + "]");
			filtro.setInTotal(ConstantesCRM.SZERO);
			log.debug("RelatoriosController:gerarProdRepresentanteBKO.do - [inTotal = " + "0" + "]");

			// 10 linhas por página
			filtro.setQtdLinhasBloco(NUM_LINHAS_BLOCO);

			log.debug("RelatoriosController:gerarProdRepresentanteBKO.do - [qtdLinhasBloco = " + NUM_LINHAS_BLOCO + "]");

			if (!form.getOperadoraSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroOperadoraVO().setIdOperadora(form.getOperadoraSel());
				log.debug("RelatoriosController:gerarProdRepresentanteBKO.do - [idOperadora = " + form.getOperadoraSel() + "]");
			}
			if (!form.getRegionalSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroRegionalVO().setIdRegional(form.getRegionalSel());
				log.debug("RelatoriosController:gerarProdRepresentanteBKO.do - [idRegional = " + form.getRegionalSel() + "]");
			}
			if (!form.getGrupoSel().equals(menosuno)) {
				filtro.addNewWFGrupoVO().setIdGrupo(form.getGrupoSel());
				log.debug("RelatoriosController:gerarProdRepresentanteBKO.do - [idGrupo = " + form.getGrupoSel() + "]");
			}
			if (form.getEstadoUsuarioSel() != null) {
				int i = 0;
				do {
					filtro.addNewStatusUsuarioVO().setIdStatus(form.getEstadoUsuarioSel()[i]);
					log.debug("RelatoriosController:gerarProdRepresentanteBKO.do - [idStatus = " + form.getEstadoUsuarioSel()[i] + "]");
					i++;
				} while (i < form.getEstadoUsuarioSel().length);
			}

			configuraFiltroOrganograma(form, filtro);

			if (form.getQuebra() != null) {
				// for (int i=0; i<form.getQuebra().length; i++) {
				int i = 0;
				do {
					WFRelatoriosQuebraVO quebra = filtro.addNewWFRelatoriosQuebraVO();
					quebra.setIdCampo(form.getQuebra()[i]);
					quebra.setDsCampo(hashQuebrasDescricao.get(form.getQuebra()[i]));
					i++;
				} while (i < form.getQuebra().length);
			}

			relatorioForm.setFiltro(filtro);
			relatorioForm.setDetalheScript(ConstantesCRM.SVAZIO);
			relatorioForm.setDetalheAction(ConstantesCRM.SVAZIO);
			relatorioForm.setMostrarFiltros("S");
			relatorioForm.setExportar(false);

			relatorioForm.setGerarAction("gerarProdRepresentanteBKO");
			// configura cabeçalhos de relatório
			relatorioForm.setCabecRelatorios(new LinkedHashMap());
			relatorioForm.getCabecRelatorios().put("Data", form.getDtInicio() + " até " + form.getDtFim());
			// relatorioForm.getCabecRelatorios().put("Gestor",hNiveis.get(form.getNivelSel()!=null?form.getNivelSel():""));
		} else {
			filtro = relatorioForm.getFiltro();
			filtro.setInTotal(String.valueOf(Integer.parseInt(filtro.getInTotal()) + bloco));
			filtro.setQtdLinhasBloco(NUM_LINHAS_BLOCO);
		}

		// DESCOMENTAR ESSA LINHA SE QUISER BOTAO PROXIMO||ANTERIOR
		WFRelatorioDinamicoVO relatorio = relatorioFacade.gerarProdRepresentanteBKOComBotao(user, filtro);

		// DESCOMENTAR ESSAS LINHAS CASO SEJA NECESSARIO EXIBIR TODAS AS LINHAS DO RELATORIO DE UMA VEZ
		// WFRelatorioDinamicoVO relatorio = relatorioFacade.gerarProdRepresentanteBKOPaginado(user,filtro);
		// relatorio.setInFin("1");
		// filtro.setInTotal("0");

		relatorio.setDsTituloRelatorio("Relatorios FO - Produção por Representante BKO");
		relatorioForm.setwFRelatorioDinamicoVO(relatorio);

		log.debug("RelatoriosController:gerarProdRepresentanteBKO.do - Relatorios- Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="qtdMotivosRepresentante.jsp"
	 */
	protected ActionForward beginQtdMotivoRepresentante(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;

		log.debug("RelatoriosController:beginQtdMotivoRepresentante.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		relatorioForm.setWFRelatoriosFiltrosVO(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));
		relatorioForm.setInicioAction("beginQtdMotivoRepresentante");
		relatorioForm.setGerarAction(ConstantesCRM.SVAZIO);
		relatorioForm.setAtualizacao(ConstantesCRM.SZERO);
		log.debug("RelatoriosController:beginQtdMotivoRepresentante.do - Relatorios- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosResultado.jsp"
	 */
	protected ActionForward gerarQtdMotivoRepresentante(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:gerarQtdMotivoRepresentante.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFRelatoriosFiltrosVO filtro = null;
		String refresh = request.getParameter("refresh");
		int bloco = Integer.parseInt(request.getParameter("bloco") == null ? ConstantesCRM.SZERO : request.getParameter("bloco"));
		if (refresh == null) {

			// guardar esses valores no form para detalhe do relatorio
			relatorioForm.setDtInicio(form.getDtInicio());
			relatorioForm.setDtFim(form.getDtFim());

			filtro = WFRelatoriosFiltrosVO.Factory.newInstance();
			filtro.setDtInicio(form.dtInicio);
			log.debug("RelatoriosController:gerarQtdMotivoRepresentante.do - [dtInicio = " + form.dtInicio + "]");
			filtro.setDtFim(form.dtFim);
			log.debug("RelatoriosController:gerarQtdMotivoRepresentante.do - [dtFim = " + form.dtFim + "]");
			filtro.setInTotal("0");
			log.debug("RelatoriosController:gerarQtdMotivoRepresentante.do - [inTotal = " + "0" + "]");
			// 10 linhas por página
			filtro.setQtdLinhasBloco(NUM_LINHAS_BLOCO);
			log.debug("RelatoriosController:gerarQtdMotivoRepresentante.do - [qtdLinhasBloco = " + NUM_LINHAS_BLOCO + "]");

			if (!form.getOperadoraSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroOperadoraVO().setIdOperadora(form.getOperadoraSel());
				log.debug("RelatoriosController:gerarQtdMotivoRepresentante.do - [idOperadora = " + form.getOperadoraSel() + "]");
			}
			if (!form.getRegionalSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroRegionalVO().setIdRegional(form.getRegionalSel());
				log.debug("RelatoriosController:gerarQtdMotivoRepresentante.do - [idRegional = " + form.getRegionalSel() + "]");
			}
			if (!form.getGrupoSel().equals(menosuno)) {
				filtro.addNewWFGrupoVO().setIdGrupo(form.getGrupoSel());
				log.debug("RelatoriosController:gerarQtdMotivoRepresentante.do - [idGrupo = " + form.getGrupoSel() + "]");
			}

			if (form.getEstadoUsuarioSel() != null) {
				int i = 0;
				do {
					filtro.addNewStatusUsuarioVO().setIdStatus(form.getEstadoUsuarioSel()[i]);
					log.debug("RelatoriosController:gerarQtdMotivoRepresentante.do - [idStatus = " + form.getEstadoUsuarioSel()[i] + "]");
					i++;
				} while (i < form.getEstadoUsuarioSel().length);
			}
			configuraFiltroOrganograma(form, filtro);

			if (form.getQuebra() != null) {
				// for (int i=0; i<form.getQuebra().length; i++) {
				int i = 0;
				do {
					WFRelatoriosQuebraVO quebra = filtro.addNewWFRelatoriosQuebraVO();
					quebra.setIdCampo(form.getQuebra()[i]);
					quebra.setDsCampo(hashQuebrasDescricao.get(form.getQuebra()[i]));
					i++;
				} while (i < form.getQuebra().length);
			}

			relatorioForm.setFiltro(filtro);
			relatorioForm.setDetalheAction(ConstantesCRM.SVAZIO);
			relatorioForm.setDetalheScript(ConstantesCRM.SVAZIO);
			relatorioForm.setMostrarFiltros("S");
			relatorioForm.setExportar(false);
			relatorioForm.setGerarAction("gerarQtdMotivoRepresentante");
			// configura cabeçalhos de relatório
			relatorioForm.setCabecRelatorios(new LinkedHashMap());
			relatorioForm.getCabecRelatorios().put("Data", form.getDtInicio() + " até " + form.getDtFim());
			relatorioForm.getCabecRelatorios().put("Gestor", hNiveis.get(form.getNivelSel() != null ? form.getNivelSel() : ""));
		} else {
			filtro = relatorioForm.getFiltro();
			filtro.setInTotal(String.valueOf(Integer.parseInt(filtro.getInTotal()) + bloco));
		}

		WFRelatorioDinamicoVO relatorio = relatorioFacade.gerarQtdMotivoRepresentante(user, filtro);
		relatorio.setDsTituloRelatorio("Relatorios FO - Quantidade e Motivos por Representante BKO");
		relatorioForm.setwFRelatorioDinamicoVO(relatorio);
		log.debug("RelatoriosController:gerarQtdMotivoRepresentante.do - Relatorios- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

    /**
     * This method represents the point of entry into the pageflow
     * @jpf:action
     * @jpf:forward name="success" path="tempoMedioAnFila.jsp"
     */
	protected ActionForward beginTempoMedioFila(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:beginTempoMedioFila.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		relatorioForm.setWFRelatoriosFiltrosVO(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));
		relatorioForm.setInicioAction("beginTempoMedioFila");
		relatorioForm.setGerarAction(ConstantesCRM.SVAZIO);
		relatorioForm.setAtualizacao(ConstantesCRM.SZERO);
		log.debug("RelatoriosController:beginTempoMedioFila.do - Relatorios- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosResultado.jsp"
	 */
	protected ActionForward gerarTempoMedioFila(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:gerarTempoMedioFila.do - Relatorios- Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFRelatoriosFiltrosVO filtro = null;
		String refresh = request.getParameter("refresh");
		int bloco = Integer.parseInt(request.getParameter("bloco") == null ? ConstantesCRM.SZERO : request.getParameter("bloco"));

		if (refresh == null) {

			// guardar esses valores no form para detalhe do relatorio
			relatorioForm.setTipoRelatorio(form.getTipoRelatorio());
			log.debug("RelatoriosController:gerarTempoMedioFila.do - [tipoRelatorio = " + relatorioForm.getTipoRelatorio() + "]");
			relatorioForm.setDtInicio(form.getDtInicio());

			relatorioForm.setDtFim(form.getDtFim());

			filtro = WFRelatoriosFiltrosVO.Factory.newInstance();

			filtro.setDtInicio(relatorioForm.getDtInicio());
			log.debug("RelatoriosController:gerarTempoMedioFila.do - [dtInicio = " + relatorioForm.getDtInicio() + "]");
			filtro.setDtFim(relatorioForm.getDtFim());
			log.debug("RelatoriosController:gerarTempoMedioFila.do - [dtFim = " + relatorioForm.getDtFim() + "]");
			filtro.setInTotal("0");
			log.debug("RelatoriosController:gerarTempoMedioFila.do - [inTotal = " + "0" + "]");

			// 10 linhas por página
			filtro.setQtdLinhasBloco(NUM_LINHAS_BLOCO);
			log.debug("RelatoriosController:gerarTempoMedioFila.do - [qtdLinhasBloco = " + NUM_LINHAS_BLOCO + "]");

			if (!form.getOperadoraSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroOperadoraVO().setIdOperadora(form.getOperadoraSel());
				log.debug("RelatoriosController:gerarTempoMedioFila.do - [idOperadora = " + form.getOperadoraSel() + "]");
			}
			if (!form.getRegionalSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroRegionalVO().setIdRegional(form.getRegionalSel());
				log.debug("RelatoriosController:gerarTempoMedioFila.do - [idRegional = " + form.getRegionalSel() + "]");
			}
			if (!form.getGrupoSel().equals(menosuno)) {
				filtro.addNewWFGrupoVO().setIdGrupo(form.getGrupoSel());
				log.debug("RelatoriosController:gerarTempoMedioFila.do - [idGrupo = " + form.getGrupoSel() + "]");
			}

			if (form.getEstadoSel() != null) {
				// for (int i=0; i<form.getEstadoSel().length; i++) {
				int i = 0;
				do {
					filtro.addNewWFEstadoVO().setIdEstado(form.getEstadoSel()[i]);
					log.debug("RelatoriosController:gerarTempoMedioFila.do - [idEstado = " + form.getEstadoSel()[i] + "]");
					i++;
				} while (i < form.getEstadoSel().length);
			}

			configuraFiltroOrganograma(form, filtro);

			if (form.getQuebra() != null) {
				// for (int i=0; i<form.getQuebra().length; i++) {
				int i = 0;
				do {
					if ((form.getQuebra()[i] != null) && (form.getQuebra()[i].length() > 0)) {
						WFRelatoriosQuebraVO quebra = filtro.addNewWFRelatoriosQuebraVO();
						quebra.setIdCampo(form.getQuebra()[i]);
						quebra.setDsCampo(hashQuebrasDescricao.get(form.getQuebra()[i]));
					}
					i++;
				} while (i < form.getQuebra().length);
			}

			if ((form.getTipoRelatorio().equals(ConstantesCRM.SONE)) || (form.getTipoRelatorio().equals(ConstantesCRM.STHREE))) {
				WFRelatoriosQuebraVO quebra = filtro.addNewWFRelatoriosQuebraVO();
				quebra.setIdCampo("nmNome");
				quebra.setDsCampo(hashQuebrasDescricao.get("nmNome"));
				quebra = filtro.addNewWFRelatoriosQuebraVO();
				quebra.setIdCampo("nmLoginUsuario");
				quebra.setDsCampo(hashQuebrasDescricao.get("nmLoginUsuario"));
			}

			else if (form.getTipoRelatorio().equals(ConstantesCRM.STWO)) {
				WFRelatoriosQuebraVO quebra = filtro.addNewWFRelatoriosQuebraVO();
				quebra.setIdCampo("nmPessoa");
				quebra.setDsCampo(hashQuebrasDescricao.get("nmNome"));
				quebra = filtro.addNewWFRelatoriosQuebraVO();
				quebra.setIdCampo("nmLoginUsuario");
				quebra.setDsCampo(hashQuebrasDescricao.get("nmLoginUsuario"));
				quebra = filtro.addNewWFRelatoriosQuebraVO();
				quebra.setIdCampo("tempoDemoraDias");
				quebra.setDsCampo(hashQuebrasDescricao.get("tempoDemoraDias"));

			} else if (form.getTipoRelatorio().equals(ConstantesCRM.SFOUR)) {
				WFRelatoriosQuebraVO quebra = filtro.addNewWFRelatoriosQuebraVO();
				quebra.setIdCampo("nmGrupo");
				quebra.setDsCampo(hashQuebrasDescricao.get("nmGrupo"));

			} else if (form.getTipoRelatorio().equals(ConstantesCRM.SFIVE)) {
				WFRelatoriosQuebraVO quebra = filtro.addNewWFRelatoriosQuebraVO();
				quebra.setIdCampo("idAtendimento");
				quebra.setDsCampo(hashQuebrasDescricao.get("idAtendimento"));
				quebra = filtro.addNewWFRelatoriosQuebraVO();
				quebra.setIdCampo("nmNome");
				quebra.setDsCampo(hashQuebrasDescricao.get("nmNome"));
				quebra = filtro.addNewWFRelatoriosQuebraVO();
				quebra.setIdCampo("nmLoginUsuario");
				quebra.setDsCampo(hashQuebrasDescricao.get("nmLoginUsuario"));
			}

			relatorioForm.setFiltro(filtro);
			relatorioForm.setGerarAction("gerarTempoMedioFila");
			relatorioForm.setDetalheAction("gerarTempoMedioFilaDetalhe");
			relatorioForm.setDetalheScript("submitDetalheQuadro(this)");
			relatorioForm.setMostrarFiltros("S");
			relatorioForm.setExportar(false);

			// configura cabeçalhos de relatório
			relatorioForm.setCabecRelatorios(new LinkedHashMap());
			relatorioForm.getCabecRelatorios().put("Data de Entrada", form.getDtInicio() + " <b>até</b> " + form.getDtFim());
			// relatorioForm.getCabecRelatorios().put("Gestor",hNiveis.get(form.getNivelSel()!=null?form.getNivelSel():""));

		} else {
			filtro = relatorioForm.getFiltro();
			filtro.setInTotal(String.valueOf(Integer.parseInt(filtro.getInTotal()) + bloco));
		}

		WFRelatorioDinamicoVO relatorio = relatorioFacade.gerarTempoMedioFila(user, filtro, relatorioForm.getTipoRelatorio());
		relatorio.setDsTituloRelatorio("Relatorios FO - " + hRel6Titulos.get(relatorioForm.getTipoRelatorio()));
		relatorioForm.setwFRelatorioDinamicoVO(relatorio);
		log.debug("RelatoriosController:gerarTempoMedioFila.do - Relatorios- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosResultadoDetalhes.jsp"
	 */
	protected ActionForward gerarTempoMedioFilaDetalhe(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		// Monta o log da operação se possível
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:gerarTempoMedioFilaDetalhe.do - Relatorios- Inicio do Metodo]");
		WFRelatorioDinamicoVO relatorioTotal = WFRelatorioDinamicoVO.Factory.newInstance();
		;

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFRelatoriosFiltrosVO filtro = geraFiltroDetalhes(form);

		filtro.setDtInicio(relatorioForm.getDtInicio());
		log.debug("RelatoriosController:gerarTempoMedioFilaDetalhe.do - [dtInicio = " + relatorioForm.getDtInicio() + "]");
		filtro.setDtFim(relatorioForm.getDtFim());
		log.debug("RelatoriosController:gerarTempoMedioFilaDetalhe.do - [dtFim = " + relatorioForm.getDtFim() + "]");

		int inFin = 0;
		int i = 0;
		int totalValores = 0;
		WFRelatorioDinamicoVO relatorio;
		do {
			filtro.setInTotal(String.valueOf(i));
			relatorio = relatorioFacade.gerarTempoMedioFilaDetalhe(user, filtro, relatorioForm.getTipoRelatorio());
			if (i == 0) {
				relatorioTotal.setColunasRelatorioArray(relatorio.getColunasRelatorioArray());
			}
			if (relatorio.getValoresRelatorioArray() != null) {
				if (i == 0) {
					relatorioTotal.setValoresRelatorioArray(relatorio.getValoresRelatorioArray());
					totalValores = relatorio.getValoresRelatorioArray().length;
				} else {
					for (int j = 0; j < relatorio.getValoresRelatorioArray().length; j++) {
						relatorioTotal.addNewValoresRelatorio();
						relatorioTotal.setValoresRelatorioArray(totalValores, relatorio.getValoresRelatorioArray(j));
						totalValores++;
					}
				}
			}
			inFin = Integer.parseInt(relatorio.getInFin());
			i++;

		} while (inFin == 0);

		relatorioTotal.setDsTituloRelatorio("Relatorios FO - " + hRel6Titulos.get(relatorioForm.getTipoRelatorio()));

		form.setwFRelatorioDinamicoVO(relatorioTotal);

		log.debug("RelatoriosController:gerarTempoMedioFilaDetalhe.do - Relatorios- Fim do Metodo]");

		request.setAttribute("form", form);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="qtdPalitagem.jsp"
	 */
	protected ActionForward beginQtdPalitagem(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("RelatoriosController:beginQtdPalitagem.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		Calendar cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DATE, -1);
		SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
		relatorioForm.setDtInicio(dtf.format(cal.getTime()));
		log.debug("RelatoriosController:beginQtdPalitagem.do - [dtInicio = " + relatorioForm.getDtInicio() + "]");

		cal = GregorianCalendar.getInstance();
		cal.add(Calendar.DATE, -7);
		relatorioForm.setDtFim(dtf.format(cal.getTime()));
		log.debug("RelatoriosController:beginQtdPalitagem.do - [dtFim = " + relatorioForm.getDtFim() + "]");

		relatorioForm.setWFRelatoriosFiltrosVO(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));
		relatorioForm.setInicioAction("beginQtdPalitagem");
		relatorioForm.setGerarAction(ConstantesCRM.SVAZIO);
		relatorioForm.setAtualizacao(ConstantesCRM.SZERO);
		log.debug("RelatoriosController:beginQtdPalitagem.do - Relatorios- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="qtdPalitagemResultado.jsp"
	 */
	protected ActionForward gerarQtdPalitagem1(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:gerarQtdPalitagem1.do - Relatorios- Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		WFRelatoriosFiltrosVO filtro = null;
		if (request.getParameter("voltar") == null) {
			// guardar esses valores no form para detalhe do relatorio
			if (form.getDtInicio() == null || form.getDtInicio().equals(ConstantesCRM.SVAZIO)) {
				Calendar cal = GregorianCalendar.getInstance();
				cal.add(Calendar.DATE, -1);
				SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
				form.setDtInicio(dtf.format(cal.getTime()));
			}
			if (form.getDtFim() == null || form.getDtFim().equals("")) {
				Calendar cal = GregorianCalendar.getInstance();
				cal.add(Calendar.DATE, -7);
				SimpleDateFormat dtf = new SimpleDateFormat("dd/MM/yyyy");
				form.setDtFim(dtf.format(cal.getTime()));
			}
			relatorioForm.setDtInicio(form.getDtInicio());
			relatorioForm.setDtFim(form.getDtFim());
			relatorioForm.setGrupoSel(form.getGrupoSel());
			relatorioForm.setAlarme(form.getAlarme());
			relatorioForm.setNivel(0);
			filtro = WFRelatoriosFiltrosVO.Factory.newInstance();
			if (!relatorioForm.getGrupoSel().equals(menosuno)) {
				filtro.addNewWFGrupoVO().setIdGrupo(form.getGrupoSel());
				for (int i = 0; i < relatorioForm.getWFRelatoriosFiltrosVO().getWFGrupoVOArray().length; i++) {
					if (relatorioForm.getWFRelatoriosFiltrosVO().getWFGrupoVOArray()[i].getIdGrupo().equals(form.getGrupoSel())) {
						relatorioForm.setDsGrupo(relatorioForm.getWFRelatoriosFiltrosVO().getWFGrupoVOArray()[i].getDsGrupo());
						break;
					}
				}
			} else {
				relatorioForm.setDsGrupo("TODOS");
			}
			filtro.setDtInicio(relatorioForm.getDtInicio());
			log.debug("RelatoriosController:gerarQtdPalitagem1.do - [dtInicio = " + relatorioForm.getDtInicio() + "]");
			filtro.setDtFim(relatorioForm.getDtFim());
			log.debug("RelatoriosController:gerarQtdPalitagem1.do - [dtFim = " + relatorioForm.getDtFim() + "]");
			filtro.setNivel(String.valueOf(relatorioForm.getNivel()));
			log.debug("RelatoriosController:gerarQtdPalitagem1.do - [nivel = " + relatorioForm.getNivel() + "]");
			relatorioForm.setNivel(1);
		} else {
			filtro = (WFRelatoriosFiltrosVO) stFiltrosPalitagem.pop();
		}
		if (filtro.getPaginacao() != null) {
			filtro.getPaginacao().setPageNumber(1);
		} else {
			filtro.addNewPaginacao().setPageNumber(1);
		}

		stNiveisPalitagem = new Stack();
		stContatosPalitagem = new Stack();
		stFiltrosPalitagem = new Stack();
		stFiltrosPalitagem.add(filtro);
		relatorioForm.setNivelSel("TELA INICIAL");

		WFRelatorioDinamicoVO relatorioGeral = WFRelatorioDinamicoVO.Factory.newInstance();
		WFRelatorioDinamicoVO relatorio = relatorioFacade.gerarQtdPalitagem1(user, filtro);

		relatorioGeral.setDsTituloRelatorio(relatorio.getDsTituloRelatorio());
		relatorioGeral.setUltimoNivel(relatorio.getUltimoNivel());

		for (int i = 0; i < relatorio.getColunasRelatorioArray().length; i++) {
			relatorioGeral.addNewColunasRelatorio().set(relatorio.getColunasRelatorioArray(i).copy());
		}
		for (int i = 0; i < relatorio.getValoresRelatorioArray().length; i++) {
			relatorioGeral.addNewValoresRelatorio().set(relatorio.getValoresRelatorioArray(i).copy());
		}
		while (relatorio.getPaginacao().getHasNext() == 1) {
			filtro.getPaginacao().setPageNumber(filtro.getPaginacao().getPageNumber() + 1);
			relatorio = relatorioFacade.gerarQtdPalitagem1(user, filtro);
			for (int i = 0; i < relatorio.getValoresRelatorioArray().length; i++) {
				relatorioGeral.addNewValoresRelatorio().set(relatorio.getValoresRelatorioArray(i).copy());
			}
		}

		relatorioForm.setHtHorariosRelatorios(geraRelatorioPalitagem(relatorioGeral));
		relatorioGeral.setDsTituloRelatorio("Relatorios FO - Quantidade Palitagem");
		relatorioForm.setwFRelatorioDinamicoVO(relatorioGeral);
		relatorioForm.setDetalheAction("gerarQtdPalitagem2");
		relatorioForm.setDetalheScript(ConstantesCRM.SVAZIO);
		relatorioForm.setInicioAction("gerarQtdPalitagem1");
		log.debug("RelatoriosController:gerarQtdPalitagem1.do - Relatorios- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="qtdPalitagemResultado.jsp"
	 */
	protected ActionForward gerarQtdPalitagem2(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:gerarQtdPalitagem2.do - Relatorios- Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		WFRelatoriosFiltrosVO filtro = null;
		if (request.getParameter("voltar") == null) {
			relatorioForm.setNivelSel(request.getParameter("dsFiltro").replaceAll("\\s*$", ""));
			filtro = WFRelatoriosFiltrosVO.Factory.newInstance();
			if (relatorioForm.getNivel() == 1) {
				if (request.getParameter("dsFiltro").replaceAll("\\s*$", "").equals("Total")) {
					relatorioForm.setUf(ConstantesCRM.SVAZIO);
				} else {
					relatorioForm.setUf(request.getParameter("dsFiltro").replaceAll("\\s*$", ""));
				}
				relatorioForm.setIdContatoOrigem(ConstantesCRM.SVAZIO);
			}
			FiltroDetalhe detalhe = filtro.addNewFiltroDetalhe();
			detalhe.setIdColuna("sgUF");
			detalhe.setValor(relatorioForm.getUf());
			detalhe = filtro.addNewFiltroDetalhe();
			detalhe.setIdColuna("idContatoOrigem");
			detalhe.setValor(relatorioForm.getIdContatoOrigem());
			stContatosPalitagem.add(relatorioForm.getIdContatoOrigem());

			if (relatorioForm.getNivel() > 1) {
				detalhe = filtro.addNewFiltroDetalhe();
				detalhe.setIdColuna("idContatoPai");
				if (request.getParameter("idFiltro").replaceAll("\\s*$", "").equals("0")) {
					detalhe.setValor(ConstantesCRM.SVAZIO);
				} else {
					detalhe.setValor(request.getParameter("idFiltro").replaceAll("\\s*$", ""));
					relatorioForm.setIdContatoOrigem(detalhe.getValor());
				}
			}

			filtro.setDtInicio(relatorioForm.getDtInicio());
			log.debug("RelatoriosController:gerarQtdPalitagem2.do - [dtInicio = " + relatorioForm.getDtInicio() + "]");
			filtro.setDtFim(relatorioForm.getDtFim());
			log.debug("RelatoriosController:gerarQtdPalitagem2.do - [dtFim = " + relatorioForm.getDtFim() + "]");
			filtro.setNivel(String.valueOf(relatorioForm.getNivel()));
			log.debug("RelatoriosController:gerarQtdPalitagem2.do - [nivel = " + relatorioForm.getNivel() + "]");
			if (!relatorioForm.getGrupoSel().equals(menosuno)) {
				filtro.addNewWFGrupoVO().setIdGrupo(relatorioForm.getGrupoSel());
				log.debug("RelatoriosController:gerarQtdPalitagem2.do - [idGrupo = " + relatorioForm.getGrupoSel() + "]");
			}
			relatorioForm.setNivel(relatorioForm.getNivel() + 1);
			stNiveisPalitagem.add(relatorioForm.getNivelSel());
		} else {
			String nivel = (String) stNiveisPalitagem.pop();
			filtro = (WFRelatoriosFiltrosVO) stFiltrosPalitagem.pop();
			relatorioForm.setIdContatoOrigem((String) stContatosPalitagem.pop());
		}
		if (filtro.getPaginacao() != null) {
			filtro.getPaginacao().setPageNumber(1);
		} else {
			filtro.addNewPaginacao().setPageNumber(1);
		}
		stFiltrosPalitagem.add(filtro);

		WFRelatorioDinamicoVO relatorioGeral = WFRelatorioDinamicoVO.Factory.newInstance();
		WFRelatorioDinamicoVO relatorio = relatorioFacade.gerarQtdPalitagem1(user, filtro);

		relatorioGeral.setDsTituloRelatorio(relatorio.getDsTituloRelatorio());
		relatorioGeral.setUltimoNivel(relatorio.getUltimoNivel());

		for (int i = 0; i < relatorio.getColunasRelatorioArray().length; i++) {
			relatorioGeral.addNewColunasRelatorio().set(relatorio.getColunasRelatorioArray(i).copy());
		}
		for (int i = 0; i < relatorio.getValoresRelatorioArray().length; i++) {
			relatorioGeral.addNewValoresRelatorio().set(relatorio.getValoresRelatorioArray(i).copy());
		}
		while (relatorio.getPaginacao().getHasNext() == 1) {
			filtro.getPaginacao().setPageNumber(filtro.getPaginacao().getPageNumber() + 1);
			relatorio = relatorioFacade.gerarQtdPalitagem1(user, filtro);
			for (int i = 0; i < relatorio.getValoresRelatorioArray().length; i++) {
				relatorioGeral.addNewValoresRelatorio().set(relatorio.getValoresRelatorioArray(i).copy());
			}
		}

		relatorioForm.setHtHorariosRelatorios(geraRelatorioPalitagem(relatorioGeral));
		relatorioForm.setDetalheAction("gerarQtdPalitagem2");
		relatorioForm.setDetalheScript(ConstantesCRM.SVAZIO);
		relatorioForm.setwFRelatorioDinamicoVO(relatorioGeral);
		relatorioForm.setInicioAction("gerarQtdPalitagem2");
		log.debug("RelatoriosController:gerarQtdPalitagem2.do - Relatorios- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="voltar0" path="beginQtdPalitagem.do"
	 * @jpf:forward name="voltar1" path="gerarQtdPalitagem1.do"
	 * @jpf:forward name="voltar2" path="gerarQtdPalitagem2.do"
	 */
	protected ActionForward gerarQtdPalitagemVoltar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;

		log.debug("RelatoriosController:gerarQtdPalitagemVoltar.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		// desconsiderar o ultimo nivel da pilha, e remover
		// ate o penultimo, que corresponde ao nivel anterior
		// ao nivel atual
		WFRelatoriosFiltrosVO filtro = (WFRelatoriosFiltrosVO) stFiltrosPalitagem.pop();

		String actionName = null;
		if (relatorioForm.getNivel() > 2) {
			actionName = "voltar2";
		} else if (relatorioForm.getNivel() == 2) {
			actionName = "voltar1";
		} else {
			actionName = "voltar0";
		}

		relatorioForm.setNivel(relatorioForm.getNivel() - 1);

		ActionRedirect f = new ActionRedirect(mapping.findForward(actionName));
		f.addParameter("voltar", ConstantesCRM.SONE);

		log.debug("RelatoriosController:gerarQtdPalitagemVoltar.do - Relatorios- Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="qtdPalitagemResultadoImprimir.jsp"
	 */
	protected ActionForward gerarQtdPalitagem1Imprimir(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:gerarQtdPalitagem1Imprimir.do - Relatorios- Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="atdEncIncorreto.jsp"
	 */
	protected ActionForward beginAtdEncIncorreto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:beginAtdEncIncorreto.do - Relatorios- Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		relatorioForm.setWFRelatoriosFiltrosVO(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));
		relatorioForm.setInicioAction("beginAtdEncIncorreto");
		relatorioForm.setGerarAction(ConstantesCRM.SVAZIO);
		relatorioForm.setAtualizacao(ConstantesCRM.SZERO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosResultado.jsp"
	 */
	protected ActionForward gerarAtdEncIncorreto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;

		log.debug("RelatoriosController:gerarAtdEncIncorreto.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFRelatoriosFiltrosVO filtro = null;
		String refresh = request.getParameter("refresh");
		int bloco = Integer.parseInt(request.getParameter("bloco") == null ? ConstantesCRM.SZERO : request.getParameter("bloco"));

		if (refresh == null) {

			// guardar esses valores no form para detalhe do relatorio
			relatorioForm.setDtInicio(form.getDtInicio());
			relatorioForm.setDtFim(form.getDtFim());

			filtro = WFRelatoriosFiltrosVO.Factory.newInstance();

			filtro.setDtInicio(relatorioForm.getDtInicio());
			log.debug("RelatoriosController:gerarAtdEncIncorreto.do - [dtInicio = " + relatorioForm.getDtInicio() + "]");
			filtro.setDtFim(relatorioForm.getDtFim());
			log.debug("RelatoriosController:gerarAtdEncIncorreto.do - [dtFim = " + relatorioForm.getDtFim() + "]");
			filtro.setInTotal(ConstantesCRM.SZERO);
			log.debug("RelatoriosController:gerarAtdEncIncorreto.do - [inTotal = " + filtro.getInTotal() + "]");
			// 10 linhas por página
			filtro.setQtdLinhasBloco(NUM_LINHAS_BLOCO);
			log.debug("RelatoriosController:gerarAtdEncIncorreto.do - [qtdLinhasBloco = " + NUM_LINHAS_BLOCO + "]");

			if (!form.getGrupoSel().equals(menosuno)) {
				filtro.addNewWFGrupoVO().setIdGrupo(form.getGrupoSel());
				log.debug("RelatoriosController:gerarAtdEncIncorreto.do - [idGrupo = " + form.getGrupoSel() + "]");
			}
			if (!form.getContatoSel().equals(ConstantesCRM.SVAZIO)) {
				filtro.setIdContato(form.getContatoSel());
				log.debug("RelatoriosController:gerarAtdEncIncorreto.do - [idContato = " + form.getContatoSel() + "]");
			}

			if (form.getQuebra() != null) {
				// for (int i=0; i<form.getQuebra().length; i++) {
				int i = 0;
				do {
					WFRelatoriosQuebraVO quebra = filtro.addNewWFRelatoriosQuebraVO();
					quebra.setIdCampo(form.getQuebra()[i]);
					quebra.setDsCampo(hashQuebrasDescricao.get(form.getQuebra()[i]));
					i++;
				} while (i < form.getQuebra().length);
			}

			relatorioForm.setFiltro(filtro);
			relatorioForm.setDetalheAction(ConstantesCRM.SVAZIO);
			relatorioForm.setDetalheScript(ConstantesCRM.SVAZIO);
			relatorioForm.setMostrarFiltros("S");
			relatorioForm.setExportar(false);
			relatorioForm.setGerarAction("gerarAtdEncIncorreto");

			// configura cabeçalhos de relatório
			relatorioForm.setCabecRelatorios(new LinkedHashMap());
			relatorioForm.getCabecRelatorios().put("Data de Entrada", form.getDtInicio() + " <b>até</b> " + form.getDtFim());
			WFGrupoVO[] gruposVO = relatorioForm.getWFRelatoriosFiltrosVO().getWFGrupoVOArray();
			for (int i = 0; i < gruposVO.length; i++) {
				if (gruposVO[i].getIdGrupo().equals(form.getGrupoSel())) {
					relatorioForm.getCabecRelatorios().put("Grupo", gruposVO[i].getDsGrupo());
					break;
				}
			}
			relatorioForm.getCabecRelatorios().put("Contato", request.getParameter("textoContato"));
		} else {
			filtro = relatorioForm.getFiltro();
			filtro.setInTotal(String.valueOf(Integer.parseInt(filtro.getInTotal()) + bloco));
		}

		WFRelatorioDinamicoVO relatorio = relatorioFacade.gerarAtdEncIncorretos(user, filtro);
		relatorio.setDsTituloRelatorio("Relatorios FO - Processos Encaminhados Incorreto");
		relatorioForm.setwFRelatorioDinamicoVO(relatorio);
		log.debug("RelatoriosController:gerarAtdEncIncorreto.do - Relatorios- Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iArvoreContato.jsp"
	 */
	protected ActionForward obterArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:obterArvoreContato.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(user, null);

		if (admContatoFolhaVO == null) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		StringBuffer sbScript = new StringBuffer(ConstantesCRM.SVAZIO);
		sbScript.append("if (document.getElementById) {\n\nvar tree = new WebFXTree('");
		sbScript.append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getNmContato()));
		sbScript.append("',\"\",'classic');\n\n");

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
				dblClickScript = "";

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

		relatorioForm.setScriptArvore(sbScript.toString());
		log.debug("RelatoriosController:obterArvoreContato.do - [Arvore de Contato = " + sbScript.toString() + "]");

		log.debug("RelatoriosController:obterArvoreContato.do - Relatorios- Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iExpandeArvoreContato.jsp"
	 */
	protected ActionForward expandeArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:expandeArvoreContato.do - Relatorios- Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		StringBuffer sbScript = new StringBuffer(1024);
		StringBuffer sbNode = new StringBuffer(1024);

		AtendimentoArvoreFiltrosVO atFiltros = AtendimentoArvoreFiltrosVO.Factory.newInstance();
		atFiltros.setIdContato(form.getContatoSel());

		log.debug("RelatoriosController:expandeArvoreContato.do - [idContato = " + form.getContatoSel() + "]");

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

		relatorioForm.setScriptArvore(sbScript.toString());

		log.debug("RelatoriosController:expandeArvoreContato.do - [No Expandido da Arvore de Contato = " + sbScript.toString() + "]");

		log.debug("RelatoriosController:expandeArvoreContato.do - Relatorios- Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatorio1CRI.jsp"
	 */
	protected ActionForward beginRelatorio1CRI(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("RelatoriosController:beginRelatorio1CRI.do - Relatorios- Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		relatorioForm.setWFRelatoriosFiltrosVO(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));
		relatorioForm.setInicioAction("beginRelatorio1CRI");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatorioPalitagens.jsp"
	 */
	protected ActionForward beginPalitagens(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("RelatoriosController:beginPalitagensPrePago");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		relatorioForm.setWFRelatoriosFiltrosVO(relatorioFacade.obtemWFRelatoriosFiltrosVO(user));
		relatorioForm.setInicioAction("beginRelatorio1CRI");
		getRelatorioForm().setWFListaArquivosGeradosVO(WFListaArquivosGeradosVO.Factory.newInstance());

		request.setCharacterEncoding(ConstantesCRM.SISO);
		request.setAttribute("arvore", getArvoreContato(user));
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatorioPalitagensArquivosGerados.jsp"
	 */
	protected ActionForward palitagensArquivosGerados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:palitagensArquivosGerados");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		WFListaArquivosGeradosVO wfListaArquivosGeradosVO = null;
		try {
	        wfListaArquivosGeradosVO = relatorioFacade.getListaRelatoriosPalitagens(user);

        } catch (Exception e) {

        }
		if (wfListaArquivosGeradosVO == null) {
			wfListaArquivosGeradosVO = WFListaArquivosGeradosVO.Factory.newInstance();
		}

		form.setWFListaArquivosGeradosVO(wfListaArquivosGeradosVO);
		getRelatorioForm().setWFListaArquivosGeradosVO(wfListaArquivosGeradosVO);
		request.setAttribute("actionForm", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatorioPalitagensArquivosGerados.jsp"
	 */
	protected ActionForward downloadArquivoPalitagens(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("RelatoriosController:downloadArquivoPalitagens(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
			String strPos = request.getParameter("nrPos");
			if (strPos != null && strPos != ConstantesCRM.SVAZIO) {
				try {
					int nrPos = Integer.parseInt(strPos);
					ParametroVO apoioParam = telaInicialFacade.getParametro(user, "PATH_PREPAGO_RELATORIO_PALITAGEM");
					String nmArquivo = getRelatorioForm().getWFListaArquivosGeradosVO().getListaRelatoriosArray(nrPos).getNmArquivo();
					File arquivo = new File(apoioParam.getDsValorParametro() + File.separator + nmArquivo);
					if (arquivo != null && arquivo.isFile()) {
						response.addHeader("Content-Disposition", "attachment; filename=" + nmArquivo);
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
					}
				} catch (Exception e) {
					log.error("RelatoriosController:downloadArquivoPalitagens( " + user + " )", e);
				}
			}
		} catch (Exception e) {
			log.error("RelatoriosController:downloadArquivoPalitagens()", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="consultaFiltrosCadastrados.jsp"
	 */
	protected ActionForward consultaFiltrosSelecionados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		String nrPos = request.getParameter("nrPos");
		if (nrPos != null && !ConstantesCRM.SVAZIO.equals(nrPos)) {
			int nPos = Integer.parseInt(nrPos);
			request.setAttribute("arvore", getArvoreFiltrosSelecionados(nPos));

		}
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @return String
	 */
	private String getArvoreFiltrosSelecionados(int nrPos) {
		String tree = ConstantesCRM.SVAZIO;
		WFListaArquivosGeradosVO wfListaArquivosGeradosVO = getRelatorioForm().getWFListaArquivosGeradosVO();
		if (wfListaArquivosGeradosVO != null) {
			WFListaArquivosGeradosVO.ListaRelatorios lista = wfListaArquivosGeradosVO.getListaRelatoriosArray(nrPos);
			if (lista != null) {
				if (lista.getDsLogin() != null && !ConstantesCRM.SVAZIO.equals(lista.getDsLogin())) {
					tree += "aux1 = insFld(foldersTree, gFld(\"Login\", \"javascript:undefined\"));\n";
					tree += "\taux2 = insFld(aux1, gFld(\"" + lista.getDsLogin() + "\", \"javascript:undefined\"));\n";
				}
				if (lista.getInQuantitativo() != null && !ConstantesCRM.SVAZIO.equals(lista.getInQuantitativo())) {
					tree += "aux1 = insFld(foldersTree, gFld(\"Quantitativo\", \"javascript:undefined\"));\n";
					tree += "\taux2 = insFld(aux1, gFld(\"" + ("1".equals(lista.getInQuantitativo()) ? "SIM" : "NÃO") + "\", \"javascript:undefined\"));\n";
				}
				if (lista.getIdGrupo() != null && !ConstantesCRM.SVAZIO.equals(lista.getDsGrupo())) {
					tree += "aux1 = insFld(foldersTree, gFld(\"Grupo\", \"javascript:undefined\"));\n";
					tree += "\taux2 = insFld(aux1, gFld(\"" + lista.getDsGrupo() + "\", \"javascript:undefined\"));\n";
				}
				if (lista.getDtInicio() != null && !ConstantesCRM.SVAZIO.equals(lista.getDtInicio())) {
					tree += "aux1 = insFld(foldersTree, gFld(\"Data Inicio\", \"javascript:undefined\"));\n";
					tree += "\taux2 = insFld(aux1, gFld(\"" + lista.getDtInicio() + "\", \"javascript:undefined\"));\n";
				}
				if (lista.getDtFim() != null && !ConstantesCRM.SVAZIO.equals(lista.getDtFim())) {
					tree += "aux1 = insFld(foldersTree, gFld(\"Data Final\", \"javascript:undefined\"));\n";
					tree += "\taux2 = insFld(aux1, gFld(\"" + lista.getDtFim() + "\", \"javascript:undefined\"));\n";
				}
				if (lista.getAdmContatoVOArray() != null && lista.getAdmContatoVOArray().length > 0) {
					tree += "aux1 = insFld(foldersTree, gFld(\"Folhas de Contato\", \"javascript:undefined\"));\n";
					int lstArvore = lista.getAdmContatoVOArray().length;
					for (int i = 0; i < lstArvore; i++) {
						if (lista.getDsGrupo() != null && !ConstantesCRM.SVAZIO.equals(lista.getDsGrupo())) {
							tree += "\taux2 = insFld(aux1, gFld(\"" + lista.getAdmContatoVOArray(i).getNmContato() + "\", \"javascript:undefined\"));\n";
						}
					}
				}
			}
		}
		return tree;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="beginPalitagens.do"
	 */
	protected ActionForward gerarRelatorioPalitagens(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		WFRelatoriosFiltrosVO filtro = WFRelatoriosFiltrosVO.Factory.newInstance();

		filtro.setInQuantitativo(ConstantesCRM.SONE.equals(form.getFlQuantitativo()) ? ConstantesCRM.SONE : ConstantesCRM.SZERO);
		if (!ConstantesCRM.SVAZIO.equals(form.getDsLogin())) {
			filtro.setDsLogin(form.getDsLogin());
		}
		if (!ConstantesCRM.SVAZIO.equals(form.getGrupoSel())) {
			filtro.addNewWFGrupoVO().setIdGrupo(form.getGrupoSel());
		}
		filtro.setDtInicio(form.getDtInicio());
		filtro.setDtFim(form.getDtFim());
		String idFolha, nmFolha;
		for (int i = 0; i < form.getFolhasSelecionadas().length; i++) {
			nmFolha = form.getFolhasSelecionadas()[i].split("\\|")[1];
			idFolha = form.getFolhasSelecionadas()[i].split("\\|")[2];
			filtro.addNewAdmContatoVO().setIdContato(idFolha);
			filtro.getAdmContatoVOArray(i).setNmContato(nmFolha);
		}
		try {
			relatorioFacade.gerarRelatorioPalitagens(user, filtro);
		} catch (Exception e) {
		}

		request.setAttribute("arvore", request.getAttribute("arvore"));
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private String getArvoreContato(String user) {

		StringBuffer script = new StringBuffer();

		try {

			int pageNumber = 0;
			AdmIdContatoVO admidContato = AdmIdContatoVO.Factory.newInstance();
			admidContato.addNewPaginacao().setPageNumber(pageNumber);
			AdmArvoreContainerVO admContatoFolhaVO = controlArvore.carregaArvoreContato(admidContato, user);
			getRelatorioForm().setArvoreContato(admContatoFolhaVO);
			AdmArvoreContainerVO admContatoFolhaVOPaginacao = admContatoFolhaVO;

			while (admContatoFolhaVOPaginacao.getPaginacao().getHasNext() == 1) {
				pageNumber++;
				admidContato.getPaginacao().setPageNumber(pageNumber);
				admContatoFolhaVOPaginacao = controlArvore.carregaArvoreContato(admidContato, user);
				for (int i = 0; i < admContatoFolhaVOPaginacao.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray().length; i++) {
					admContatoFolhaVO.getAdmContatoFolhaVO().addNewAdmContatoFolhaVO().set(admContatoFolhaVOPaginacao.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).copy());
				}
			}

			relatorioForm.setArvoreContato(admContatoFolhaVO);

			StringBuffer node = new StringBuffer();
			String folder = ConstantesCRM.SVAZIO;
			script.append("if (document.getElementById) { var tree = new WebFXTree('").append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVO().getNmContato())).append("',\"Javascript:capturaParametrosContatoRaiz('").append(admContatoFolhaVO.getAdmContatoFolhaVO().getIdContato()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getNmContato())).append("','")
			.append(admContatoFolhaVO.getAdmContatoFolhaVO().getIdContatoPai()).append("','").append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getIdNomeContato()).append("','").append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getDsPath().toString())).append("','").append("');").append("\",'','');");
			for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray().length; i++) {
				boolean inFolha = false;
				if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha().trim().equals(ConstantesCRM.SONE)) {
					folder = "',\"Javascript:capturaParametrosContato('";
				} else {
					folder = "',\"Javascript:expandirNo('";
				}
				if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha().trim().equals(ConstantesCRM.SONE)) {
					inFolha = true;
				}
				if (inFolha) {
					node.append("tmpArvore = new WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append(folder).append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContato()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContatoPai()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdNomeContato()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNrNivel()).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getDsPath().toString())).append("','");
					if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade().trim().equals("1")) {
						node.append("');\",'','/FrontOfficeWeb/resources/images/file.png','/FrontOfficeWeb/resources/images/file.png');");
					} else {
						node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_file.gif','/FrontOfficeWeb/resources/images/image_delete_file.gif');");
					}
				} else {
					node.append("tmpArvore = new WebFXTreeItem('" + StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append(folder).append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContato()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContatoPai()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdNomeContato()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNrNivel()).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getDsPath().toString())).append("','");
					if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade().trim().equals("1")) {
						node.append("');\",'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');");
					} else {
						node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_folder.gif','/FrontOfficeWeb/resources/images/image_delete_folder.gif');");
					}
				}
				script.append(node).append("tree.add(tmpArvore);\n\n");
				// formArvoreContato.setAdmNomeContatoVO(formArvoreContato.getArvoreContato().getAdmNomeContatoVOArray());
			}
			script.append("\ndocument.write(tree);}\n\n");

		} catch (Exception e) {
			script = new StringBuffer("Ocorreu um problema durante carregamento da árvore de contatos.");
		}
		return script.toString();
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iframeArvoreContato.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward montaArvoreParte(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("AdmArvoreContatoController:montaArvoreParte(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		int pageNumber = 0;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		String[] dadosPasta = form.getIdContatoOrigem().split("\\|");

		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);

			AdmIdContatoVO admidContato = AdmIdContatoVO.Factory.newInstance();
			admidContato.addNewPaginacao().setPageNumber(pageNumber);
			admidContato.setIdContato(dadosPasta[1]);
			log.debug("montaArvoreParte(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") idContato = " + form.getIdContatoOrigem());

			AdmArvoreContainerVO admContatoFolhaVO = controlArvore.carregaArvoreContato(admidContato, user);
			AdmArvoreContainerVO admContatoFolhaVOPaginacao = admContatoFolhaVO;

			while (admContatoFolhaVOPaginacao.getPaginacao().getHasNext() == 1) {
				pageNumber++;
				admidContato.getPaginacao().setPageNumber(pageNumber);
				admContatoFolhaVOPaginacao = controlArvore.carregaArvoreContato(admidContato, user);
				for (int i = 0; i < admContatoFolhaVOPaginacao.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray().length; i++) {
					admContatoFolhaVO.getAdmContatoFolhaVO().addNewAdmContatoFolhaVO().set(admContatoFolhaVOPaginacao.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).copy());
				}
			}

			StringBuffer script = new StringBuffer();
			StringBuffer node = new StringBuffer();
			String folder = ConstantesCRM.SVAZIO;

			for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray().length; i++) {

				boolean inFolha = false;

				if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha().trim().equals(ConstantesCRM.SONE)) {
					folder = "',\"Javascript:capturaParametrosContato('";
				} else {
					folder = "',\"Javascript:expandirNo('";
				}

				if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha().trim().equals(ConstantesCRM.SONE)) {
					inFolha = true;
				}

				if (inFolha) {
					node.append("parent.tmpArvore = new parent.WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append(folder).append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContato()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContatoPai()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdNomeContato()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNrNivel()).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getDsPath())).append("','");

					if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade().trim().equals("1")) {
						node.append("');\",'','/FrontOfficeWeb/resources/images/file.png','/FrontOfficeWeb/resources/images/file.png');");
					} else {
						node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_file.gif','/FrontOfficeWeb/resources/images/image_delete_file.gif');");
					}

				} else {

					node.append("parent.tmpArvore = new parent.WebFXTreeItem('").append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append(folder).append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContato()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNmContato())).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdContatoPai()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getIdNomeContato()).append("','").append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getNrNivel()).append("','")
					.append(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInFolha()).append("','").append(StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getDsPath())).append("','");

					if (admContatoFolhaVO.getAdmContatoFolhaVO().getAdmContatoFolhaVOArray(i).getInDisponibilidade().trim().equals("1")) {
						node.append("');\",'','/FrontOfficeWeb/resources/images/foldericon.png','/FrontOfficeWeb/resources/images/openfoldericon.png');");
					} else {
						node.append("');\",'','/FrontOfficeWeb/resources/images/image_delete_folder.gif','/FrontOfficeWeb/resources/images/image_delete_folder.gif');");
					}

				}
				script.append(node).append("parent.tree.getSelected().add(parent.tmpArvore);\n\n");

			}

			script.append("parent.tree.getSelected().setAddEnabled(false);\n\n").append("parent.tree.getSelected().expand()\n\n");

			request.setAttribute("arvore", String.valueOf(script));

		} catch (TuxedoWarningException twe) {
			log.warn("AdmArvoreContatoController:montaArvoreParte" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
		} catch (Exception e) {
			log.error("AdmArvoreContatoController:montaArvoreParte" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);
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
	 * @jpf:forward name="success" path="relatoriosResultado.jsp"
	 */
	protected ActionForward gerarRelatorio1CRI(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:gerarRelatorio1CRI.do - Relatorios- Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFRelatoriosFiltrosVO filtro = null;
		String refresh = request.getParameter("refresh");
		int bloco = Integer.parseInt(request.getParameter("bloco") == null ? ConstantesCRM.SZERO : request.getParameter("bloco"));

		if (refresh == null) {

			relatorioForm.setDtInicio(form.getDtInicio());
			relatorioForm.setDtFim(form.getDtFim());

			filtro = WFRelatoriosFiltrosVO.Factory.newInstance();

			filtro.setDtInicio(relatorioForm.getDtInicio());
			log.debug("RelatoriosController:gerarRelatorio1CRI.do - [dtInicio = " + filtro.getDtFim() + "]");
			filtro.setDtFim(relatorioForm.getDtFim());
			log.debug("RelatoriosController:gerarRelatorio1CRI.do - [dtFim = " + filtro.getDtFim() + "]");
			filtro.setInTotal("0");
			log.debug("RelatoriosController:gerarRelatorio1CRI.do - [inTotal = " + filtro.getInTotal() + "]");

			// 10 linhas por página
			filtro.setQtdLinhasBloco(NUM_LINHAS_BLOCO);

			log.debug("RelatoriosController:gerarRelatorio1CRI.do - [qtdLinhasBloco = " + NUM_LINHAS_BLOCO + "]");

			// configura cabeçalhos de relatório
			relatorioForm.setCabecRelatorios(new LinkedHashMap());
			relatorioForm.getCabecRelatorios().put("Data de Entrada", form.getDtInicio() + " <b>até</b> " + form.getDtFim());

			if (!form.getOperadoraSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroOperadoraVO().setIdOperadora(form.getOperadoraSel());
				log.debug("RelatoriosController:gerarRelatorio1CRI.do - [idOperadora = " + form.getOperadoraSel() + "]");
			}
			if (!form.getRegionalSel().equals(menosuno)) {
				filtro.addNewWFRelatoriosFiltroRegionalVO().setIdRegional(form.getRegionalSel());
				log.debug("RelatoriosController:gerarRelatorio1CRI.do - [idRegional = " + form.getRegionalSel() + "]");
				relatorioForm.getCabecRelatorios().put("Regional", request.getParameter("ufOperadoraText"));
			}
			if (!form.getGrupoSel().equals(menosuno)) {
				filtro.addNewWFGrupoVO().setIdGrupo(form.getGrupoSel());
				log.debug("RelatoriosController:gerarRelatorio1CRI.do - [idGrupo = " + form.getGrupoSel() + "]");
				relatorioForm.getCabecRelatorios().put("Grupo", request.getParameter("grupoText"));
			}
			if (!form.getRepresentanteSel().equals(menosuno)) {
				filtro.addNewWFRFRVO().setId(form.getRepresentanteSel());
				log.debug("RelatoriosController:gerarRelatorio1CRI.do - [id = " + form.getRepresentanteSel() + "]");
				relatorioForm.getCabecRelatorios().put("Representante", request.getParameter("representanteText"));
			}
			if (!form.getPrazo().equals(menosuno)) {
				filtro.setPrazo(form.getPrazo());
				log.debug("RelatoriosController:gerarRelatorio1CRI.do - [prazo = " + form.getPrazo() + "]");
				relatorioForm.getCabecRelatorios().put("Prazo", request.getParameter("prazoText"));
			}

			relatorioForm.setFiltro(filtro);
			relatorioForm.setAtualizacao(form.getAtualizacao());

			relatorioForm.setDetalheAction(ConstantesCRM.SVAZIO);
			relatorioForm.setGerarAction("gerarRelatorio1CRI");
			relatorioForm.setDetalheScript(ConstantesCRM.SVAZIO);
			relatorioForm.setMostrarFiltros("S");
			relatorioForm.setExportar(true);

		} else {
			filtro = relatorioForm.getFiltro();
			filtro.setInTotal(String.valueOf(Integer.parseInt(filtro.getInTotal()) + bloco));
		}

		WFRelatorioDinamicoVO relatorio = relatorioFacade.gerarRelatorio1CRI(user, filtro);
		relatorio.setDsTituloRelatorio("Relatorios FO - CRI - Auditoria Processos com Consultores");
		relatorioForm.setwFRelatorioDinamicoVO(relatorio);

		log.debug("RelatoriosController:gerarRelatorio1CRI.do - Relatorios- Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosResultado.jsp"
	 */
	protected ActionForward exportarRelatorio1CRI(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:exportarRelatorio1CRI.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		WFRelatoriosFiltrosVO filtro = null;

		relatorioForm.setDtInicio(form.getDtInicio());
		relatorioForm.setDtFim(form.getDtFim());

		filtro = WFRelatoriosFiltrosVO.Factory.newInstance();

		filtro.setDtInicio(relatorioForm.getDtInicio());
		log.debug("RelatoriosController:exportarRelatorio1CRI.do - [dtInicio = " + filtro.getDtInicio() + "]");

		filtro.setDtFim(relatorioForm.getDtFim());
		log.debug("RelatoriosController:exportarRelatorio1CRI.do - [dtFim = " + filtro.getDtFim() + "]");

		// configura cabeçalhos de relatório
		relatorioForm.setCabecRelatorios(new LinkedHashMap());
		relatorioForm.getCabecRelatorios().put("Data de Entrada", form.getDtInicio() + " <b>até</b> " + form.getDtFim());

		if (!form.getOperadoraSel().equals(menosuno)) {
			filtro.addNewWFRelatoriosFiltroOperadoraVO().setIdOperadora(form.getOperadoraSel());
			log.debug("RelatoriosController:exportarRelatorio1CRI.do - [idOperadora = " + form.getOperadoraSel() + "]");
		}
		if (!form.getRegionalSel().equals(menosuno)) {
			filtro.addNewWFRelatoriosFiltroRegionalVO().setIdRegional(form.getRegionalSel());
			log.debug("RelatoriosController:exportarRelatorio1CRI.do - [idRegional = " + form.getRegionalSel() + "]");
			relatorioForm.getCabecRelatorios().put("Regional", request.getParameter("ufOperadoraText"));
		}
		if (!form.getGrupoSel().equals(menosuno)) {
			filtro.addNewWFGrupoVO().setIdGrupo(form.getGrupoSel());
			log.debug("RelatoriosController:exportarRelatorio1CRI.do - [idGrupo = " + form.getGrupoSel() + "]");
			relatorioForm.getCabecRelatorios().put("Grupo", request.getParameter("grupoText"));
		}
		if (!form.getRepresentanteSel().equals(menosuno)) {
			filtro.addNewWFRFRVO().setId(form.getRepresentanteSel());
			log.debug("RelatoriosController:exportarRelatorio1CRI.do - [id = " + form.getRepresentanteSel() + "]");
			relatorioForm.getCabecRelatorios().put("Representante", request.getParameter("representanteText"));
		}
		if (!form.getPrazo().equals(menosuno)) {
			filtro.setPrazo(form.getPrazo());
			log.debug("RelatoriosController:exportarRelatorio1CRI.do - [prazo = " + form.getPrazo() + "]");
			relatorioForm.getCabecRelatorios().put("Prazo", request.getParameter("prazoText"));
		}

		relatorioForm.setFiltro(filtro);
		relatorioForm.setAtualizacao(form.getAtualizacao());

		relatorioForm.setDetalheAction(ConstantesCRM.SVAZIO);
		relatorioForm.setGerarAction("gerarRelatorio1CRI");
		relatorioForm.setDetalheScript(ConstantesCRM.SVAZIO);
		relatorioForm.setMostrarFiltros("S");

		// setando a saida direta
		response.addHeader("Content-Disposition", "attachment; filename=RelatorioAuditProcs.csv");
		response.addHeader("Content-Type", "application/force-download");
		response.addHeader("Content-Transfer-Encoding", "binary");
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Expires", ConstantesCRM.SZERO);

		PrintWriter out = response.getWriter();

		int bloco = 0;
		int inFim = 0;
		filtro.setQtdLinhasBloco(250);
		WFRelatorioDinamicoVO relatorio = null;

		StringBuffer relatorioStr = new StringBuffer(32768);
		ValoresRelatorio valoresRelatorio = null;
		ValorColuna valorColuna = null;

		while (inFim == 0) {
			filtro.setInTotal(String.valueOf(bloco++));
			relatorio = relatorioFacade.gerarRelatorio1CRI(user, filtro);
			if (bloco == 1) {
				int totalRegistros = relatorio.getColunasRelatorioArray().length;
				for (int registro = 0; registro < totalRegistros; registro++) {
					relatorioStr.append(relatorio.getColunasRelatorioArray()[registro].getDsColuna()).append(",");
				}
				relatorioStr.append("\n");
			}
			inFim = Integer.parseInt(relatorio.getInFin() == null ? ConstantesCRM.SZERO : relatorio.getInFin());
			int totalRegistros = relatorio.getValoresRelatorioArray().length;
			for (int registro = 0; registro < totalRegistros; registro++) {
				valoresRelatorio = relatorio.getValoresRelatorioArray()[registro];
				int totalColunas = valoresRelatorio.getValorColunaArray().length;
				for (int coluna = 0; coluna < totalColunas; coluna++) {
					valorColuna = valoresRelatorio.getValorColunaArray()[coluna];
					relatorioStr.append(valorColuna.getValor()).append(",");
				}
				relatorioStr.append("\n");
			}
			out.print(relatorioStr);
			relatorioStr.setLength(0);
		}

		log.debug("RelatoriosController:exportarRelatorio1CRI.do - Relatorios- Fim do Metodo]");

		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosResultadoImprimir.jsp"
	 */
	protected ActionForward relatorioImprimir(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:relatorioImprimir.do - Relatorios- Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosResultadoImprimir.jsp"
	 */
	protected ActionForward relatorioExportar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RelatorioForm form = (RelatorioForm) formParam;
		log.debug("RelatoriosController:relatorioExportar.do - Relatorios- Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		// setando a saida direta
		response.addHeader("Content-Disposition", "attachment; filename=RelatorioAuditProcs.csv");
		response.addHeader("Content-Type", "application/force-download");
		response.addHeader("Content-Transfer-Encoding", "binary");
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Expires", ConstantesCRM.SZERO);

		PrintWriter out = response.getWriter();

		int bloco = 0;
		int inFim = 0;

		StringBuffer relatorioStr = new StringBuffer(32768);
		ValoresRelatorio valoresRelatorio = null;
		ValorColuna valorColuna = null;

		WFRelatorioDinamicoVO relatorio = relatorioForm.getwFRelatorioDinamicoVO();
		int totalRegistros = relatorio.getColunasRelatorioArray().length;
		for (int registro = 0; registro < totalRegistros; registro++) {
			relatorioStr.append(relatorio.getColunasRelatorioArray()[registro].getDsColuna()).append(",");
		}
		relatorioStr.append("\n");

		totalRegistros = relatorio.getValoresRelatorioArray().length;
		for (int registro = 0; registro < totalRegistros; registro++) {
			valoresRelatorio = relatorio.getValoresRelatorioArray()[registro];
			int totalColunas = valoresRelatorio.getValorColunaArray().length;
			for (int coluna = 0; coluna < totalColunas; coluna++) {
				valorColuna = valoresRelatorio.getValorColunaArray()[coluna];
				relatorioStr.append(valorColuna.getValor()).append(",");
			}
			relatorioStr.append("\n");
		}
		out.print(relatorioStr);
		relatorioStr.setLength(0);
		log.debug("RelatoriosController:relatorioExportar.do - Relatorios- Fim do Metodo]");
		return null;
	}

	/**************************************************************
	 * Metodos privados
	 */

	private void configuraFiltroOrganograma(RelatorioForm form, WFRelatoriosFiltrosVO filtro) {
		if (form.getNivelSel() == null || form.getNivelSel().length() == 0) {
			return;
		}
		if (form.getNivelSel().equals("D")) {
			if (!form.getDiretoriaSel().equals("-1")) {
				filtro.addNewWFRelatoriosFiltroDiretoriaVO().setIdCargo(form.getDiretoriaSel());
			}
		} else if (form.getNivelSel().equals("A")) {
			if (!form.getAreaSel().equals("-1")) {
				filtro.addNewWFRelatoriosFiltroDiretoriaVO().setIdCargo(form.getAreaSel());
			}
		} else if (form.getNivelSel().equals("S")) {
			if (!form.getSessaoSel().equals("-1")) {
				filtro.addNewWFRelatoriosFiltroDiretoriaVO().setIdCargo(form.getSessaoSel());
			}
		} else if (form.getNivelSel().equals("C")) {
			if (!form.getCargoSel().equals("-1")) {
				filtro.addNewWFRelatoriosFiltroDiretoriaVO().setIdCargo(form.getCargoSel());
			}
		}
	}

	private void gerarDownloadRelatorio(WFRelatoriosFiltrosVO filtro, String filename, String Titulo, HttpServletRequest request, HttpServletResponse response) throws Exception {

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		StringBuffer relatorioStr = new StringBuffer(32768);

		response.addHeader("Content-Disposition", "attachment; filename=" + filename);
		response.addHeader("Content-Type", "application/force-download");
		response.addHeader("Content-Transfer-Encoding", "binary");
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Expires", ConstantesCRM.SZERO);

		PrintWriter out = response.getWriter();

		int bloco = 0;
		int inFin = 0;
		while (inFin == 0) {
			filtro.setInTotal(String.valueOf(bloco++));
			WFRelatorioDinamicoVO relatorio = relatorioFacade.gerarTotalGrupoDestinoBKODownload(user, filtro);
			inFin = Integer.parseInt(relatorio.getInFin());
			int total = relatorio.getCsvArray().length;
			for (int i = 0; i < total; i++) {
				out.println(relatorio.getCsvArray()[i]);
			}
		}
		out.close();
	}

	private void gerarDownloadRelatorio2(WFRelatoriosFiltrosVO filtro, String filename, String Titulo, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		StringBuffer relatorioStr = new StringBuffer(32768);
		response.addHeader("Content-Disposition", "attachment; filename=" + filename);
		response.addHeader("Content-Type", "application/force-download");
		response.addHeader("Content-Transfer-Encoding", "binary");
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Expires", ConstantesCRM.SZERO);

		PrintWriter out = response.getWriter();

		int bloco = 0;
		int inFin = 0;
		while (inFin == 0) {
			filtro.setInTotal(String.valueOf(bloco++));
			WFRelatorioDinamicoVO relatorio = relatorioFacade.gerarTotalRepresentateBKODownload(user, filtro);
			inFin = Integer.parseInt(relatorio.getInFin());
			int total = relatorio.getCsvArray().length;
			for (int i = 0; i < total; i++) {
				out.println(relatorio.getCsvArray()[i]);
			}
		}
		out.close();
	}

	private WFRelatoriosFiltrosVO geraFiltroDetalhes(RelatorioForm form) {
		WFRelatoriosFiltrosVO filtro = (relatorioForm.getFiltro() == null ? WFRelatoriosFiltrosVO.Factory.newInstance() : ((WFRelatoriosFiltrosVO) relatorioForm.getFiltro().copy()));
		for (int i = 0; i < form.colunasDetalhe.length; i++) {
			if (!form.colunasDetalhe[i].equals("nmUsuarioAtual")) {
				FiltroDetalhe detalhe = filtro.addNewFiltroDetalhe();
				detalhe.setIdColuna(form.colunasDetalhe[i]);
				detalhe.setValor(form.valoresDetalhe[i].replaceAll("\\s*$", ""));
			}
		}
		return filtro;
	}

	private LinkedHashMap geraRelatorioPalitagem(WFRelatorioDinamicoVO relatorio) {
		ArrayList lstRelatorios = null;
		LinkedHashMap htHorariosRelatorios = new LinkedHashMap();
		String horario;
		String tmpHorario = ConstantesCRM.SVAZIO;
		for (int i = 0; i < relatorio.getValoresRelatorioArray().length; i++) {
			horario = relatorio.getValoresRelatorioArray(i).getValorColunaArray(0).getValor();
			if (!horario.equals(tmpHorario)) {
				lstRelatorios = new ArrayList();
				htHorariosRelatorios.put(horario, lstRelatorios);
			}
			lstRelatorios.add(relatorio.getValoresRelatorioArray(i));
			tmpHorario = horario;
		}
		return htHorariosRelatorios;
	}

	private LinkedHashMap geraRelatorioPalitagem(LinkedHashMap htHorariosRelatorios, WFRelatorioDinamicoVO relatorio) {
		ArrayList lstRelatorios = null;
		String horario;
		String tmpHorario = ConstantesCRM.SVAZIO;
		for (int i = 0; i < relatorio.getValoresRelatorioArray().length; i++) {
			horario = relatorio.getValoresRelatorioArray()[i].getValorColunaArray()[0].getValor();
			if (!horario.equals(tmpHorario)) {
				lstRelatorios = new ArrayList();
				htHorariosRelatorios.put(horario, lstRelatorios);
			}
			lstRelatorios.add(relatorio.getValoresRelatorioArray()[i]);
			tmpHorario = horario;
		}
		return htHorariosRelatorios;
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class RelatorioForm extends ActionForm {

		private static final long serialVersionUID = -1123250301986932518L;

		private boolean exportar;
		private LinkedHashMap cabecRelatorios;
		private String idContatoOrigem;

		private String nmContatoOrigem;
		private String dsGrupo;
		private String gestor;
		private String[] estadoUsuarioSel;
		private String mostrarFiltros;
		private String scriptArvore;
		private String contatoSel;
		private String gerarAction;
		private String atualizacao;
		private String uf;
		private int nivel;
		private int alarme;
		private LinkedHashMap htHorariosRelatorios;
		private ArrayList lstHorarios;
		private LinkedHashMap lhmRelatorio;
		private String inicioAction;
		private String detalheScript;
		private String tipoRelatorio;
		private String dtFim;
		private String dtInicio;
		private String detalheAction;
		private String[] valoresDetalhe;
		private String[] colunasDetalhe;
		private WFRelatoriosFiltrosVO filtro;
		private WFRelatorioDinamicoVO wFRelatorioDinamicoVO;
		private WFRelatorioDinamicoVO relatorio;
		private String prazo;
		private String[] quebra;
		private String operadoraSel;
		private String regionalSel;
		private String grupoSel;
		private String[] estadoSel;
		private String representanteSel;
		private String carteiraSel;
		private String segmentoSel;
		private String diretoriaSel;
		private String areaSel;
		private String sessaoSel;
		private String cargoSel;
		private String nivelSel;
		private String ufSel;
		private String pageNumber = ConstantesCRM.SVAZIO;
		private String offSet = ConstantesCRM.SVAZIO;
		private String hasNext = ConstantesCRM.SVAZIO;
		private String idFiltro = ConstantesCRM.SVAZIO;
		private String dsFiltro = ConstantesCRM.SVAZIO;
		private WFListaArquivosGeradosVO wfListaArquivosGeradosVO = WFListaArquivosGeradosVO.Factory.newInstance();

		private String flQuantitativo = ConstantesCRM.SVAZIO;
		private String dsLogin = ConstantesCRM.SVAZIO;

		private String[] folhasSelecionadas;
		private AdmArvoreContainerVO arvoreContato;

		private AdmContatoVO[] admContatoVO;

		public void setArvoreContato(AdmArvoreContainerVO arvoreContato) {
			this.arvoreContato = arvoreContato;
		}

		public AdmArvoreContainerVO getArvoreContato() {
			return this.arvoreContato;
		}

		private WFRelatoriosFiltrosVO wfRelatoriosFiltrosVO;

		public String getIdFiltro() {
			return this.idFiltro;
		}

		public void setIdFiltro(String idFiltro) {
			this.idFiltro = idFiltro;
		}

		public String getDsFiltro() {
			return this.dsFiltro;
		}

		public void setDsFiltro(String dsFiltro) {
			this.dsFiltro = dsFiltro;
		}

		public String getOperadoraSel() {
			return this.operadoraSel;
		}

		public void setOperadoraSel(String operadoraSel) {
			this.operadoraSel = operadoraSel;
		}

		public String getDsLogin() {
			return this.dsLogin;
		}

		public void setDsLogin(String dsLogin) {
			this.dsLogin = dsLogin;
		}

		public String getPageNumber() {
			return this.pageNumber;
		}

		public void setPageNumber(String value) {
			this.pageNumber = value;
		}

		public String getHasNext() {
			return this.hasNext;
		}

		public void setHasNext(String value) {
			this.hasNext = value;
		}

		public String getOffSet() {
			return this.offSet;
		}

		public void setOffSet(String value) {
			this.offSet = value;
		}

		public String getRegionalSel() {
			return this.regionalSel;
		}

		public void setRegionalSel(String regionalSel) {
			this.regionalSel = regionalSel;
		}

		public String getGrupoSel() {
			return this.grupoSel;
		}

		public void setGrupoSel(String grupoSel) {
			this.grupoSel = grupoSel;
		}

		public String[] getEstadoSel() {
			return this.estadoSel;
		}

		public void setEstadoSel(String[] estadoSel) {
			this.estadoSel = estadoSel;
		}

		public String[] getFolhasSelecionadas() {
			return this.folhasSelecionadas;
		}

		public void setFolhasSelecionadas(String[] folhasSelecionadas) {
			this.folhasSelecionadas = folhasSelecionadas;
		}

		public AdmContatoVO[] getAdmContatoVO() {
			return this.admContatoVO;
		}

		public void setAdmContatoVO(AdmContatoVO[] admContatoVO) {
			this.admContatoVO = admContatoVO;
		}

		public String getRepresentanteSel() {
			return this.representanteSel;
		}

		public void setRepresentanteSel(String representanteSel) {
			this.representanteSel = representanteSel;
		}

		public String getCarteiraSel() {
			return this.carteiraSel;
		}

		public void setCarteiraSel(String carteiraSel) {
			this.carteiraSel = carteiraSel;
		}

		public String getSegmentoSel() {
			return this.segmentoSel;
		}

		public void setSegmentoSel(String segmentoSel) {
			this.segmentoSel = segmentoSel;
		}

		public String getDiretoriaSel() {
			return this.diretoriaSel;
		}

		public void setDiretoriaSel(String diretoriaSel) {
			this.diretoriaSel = diretoriaSel;
		}

		public String getAreaSel() {
			return this.areaSel;
		}

		public void setAreaSel(String areaSel) {
			this.areaSel = areaSel;
		}

		public String getSessaoSel() {
			return this.sessaoSel;
		}

		public void setSessaoSel(String sessaoSel) {
			this.sessaoSel = sessaoSel;
		}

		public String getCargoSel() {
			return this.cargoSel;
		}

		public void setCargoSel(String cargoSel) {
			this.cargoSel = cargoSel;
		}

		public String getNivelSel() {
			return this.nivelSel;
		}

		public void setNivelSel(String nivelSel) {
			this.nivelSel = nivelSel;
		}

		public String getUfSel() {
			return this.ufSel;
		}

		public void setUfSel(String ufSel) {
			this.ufSel = ufSel;
		}

		public WFRelatoriosFiltrosVO getWFRelatoriosFiltrosVO() {
			return this.wfRelatoriosFiltrosVO;
		}

		public void setWFRelatoriosFiltrosVO(WFRelatoriosFiltrosVO wfRelatoriosFiltrosVO) {
			this.wfRelatoriosFiltrosVO = wfRelatoriosFiltrosVO;
		}

		public WFListaArquivosGeradosVO getWFListaArquivosGeradosVO() {
			return this.wfListaArquivosGeradosVO;
		}

		public void setWFListaArquivosGeradosVO(WFListaArquivosGeradosVO wfListaArquivosGeradosVO) {
			this.wfListaArquivosGeradosVO = wfListaArquivosGeradosVO;
		}

		public void setQuebra(String[] quebra) {
			this.quebra = quebra;
		}

		public String[] getQuebra() {
			if (this.quebra == null || this.quebra.length == 0) {
				this.quebra = new String[1];
			}
			return this.quebra;
		}

		public void setPrazo(String prazo) {
			this.prazo = prazo;
		}

		public String getPrazo() {
			return this.prazo;
		}

		public void setwFRelatorioDinamicoVO(WFRelatorioDinamicoVO wFRelatorioDinamicoVO) {
			this.wFRelatorioDinamicoVO = wFRelatorioDinamicoVO;
		}

		public WFRelatorioDinamicoVO getwFRelatorioDinamicoVO() {
			return this.wFRelatorioDinamicoVO;
		}

		public void setFiltro(WFRelatoriosFiltrosVO filtro) {
			this.filtro = filtro;
		}

		public WFRelatoriosFiltrosVO getFiltro() {
			return this.filtro;
		}

		public void setColunasDetalhe(String[] colunasDetalhe) {
			this.colunasDetalhe = colunasDetalhe;
		}

		public String[] getColunasDetalhe() {
			if (this.colunasDetalhe == null || this.colunasDetalhe.length == 0) {
				this.colunasDetalhe = new String[1];
			}
			return this.colunasDetalhe;
		}

		public void setValoresDetalhe(String[] valoresDetalhe) {
			this.valoresDetalhe = valoresDetalhe;
		}

		public String[] getValoresDetalhe() {
			if (this.valoresDetalhe == null || this.valoresDetalhe.length == 0) {
				this.valoresDetalhe = new String[1];
			}
			return this.valoresDetalhe;
		}

		public void setDetalheAction(String detalheAction) {
			this.detalheAction = detalheAction;
		}

		public String getDetalheAction() {
			return this.detalheAction;
		}

		public void setDtInicio(String dtInicio) {
			this.dtInicio = dtInicio;
		}

		public String getDtInicio() {
			return this.dtInicio;
		}

		public void setDtFim(String dtFim) {
			this.dtFim = dtFim;
		}

		public String getDtFim() {
			return this.dtFim;
		}

		public void setTipoRelatorio(String tipoRelatorio) {
			this.tipoRelatorio = tipoRelatorio;
		}

		public String getTipoRelatorio() {
			return this.tipoRelatorio;
		}

		public void setDetalheScript(String detalheScript) {
			this.detalheScript = detalheScript;
		}

		public String getDetalheScript() {
			return this.detalheScript;
		}

		public void setInicioAction(String inicioAction) {
			this.inicioAction = inicioAction;
		}

		public String getInicioAction() {
			return this.inicioAction;
		}

		public void setHtHorariosRelatorios(LinkedHashMap htHorariosRelatorios) {
			this.htHorariosRelatorios = htHorariosRelatorios;
		}

		public LinkedHashMap getHtHorariosRelatorios() {
			return this.htHorariosRelatorios;
		}

		public void setAlarme(int alarme) {
			this.alarme = alarme;
		}

		public int getAlarme() {
			return this.alarme;
		}

		public void setNivel(int nivel) {
			this.nivel = nivel;
		}

		public int getNivel() {
			return this.nivel;
		}

		public void setUf(String uf) {
			this.uf = uf;
		}

		public String getUf() {
			return this.uf;
		}

		public void setAtualizacao(String atualizacao) {
			this.atualizacao = atualizacao;
		}

		public String getAtualizacao() {
			return this.atualizacao;
		}

		public void setGerarAction(String gerarAction) {
			this.gerarAction = gerarAction;
		}

		public String getGerarAction() {
			return this.gerarAction;
		}

		public void setContatoSel(String contatoSel) {
			this.contatoSel = contatoSel;
		}

		public String getContatoSel() {
			return this.contatoSel;
		}

		public void setScriptArvore(String scriptArvore) {
			this.scriptArvore = scriptArvore;
		}

		public String getScriptArvore() {
			return this.scriptArvore;
		}

		public void setMostrarFiltros(String mostrarFiltros) {
			this.mostrarFiltros = mostrarFiltros;
		}

		public String getMostrarFiltros() {
			return this.mostrarFiltros;
		}

		public void setEstadoUsuarioSel(String[] estadoUsuarioSel) {
			this.estadoUsuarioSel = estadoUsuarioSel;
		}

		public String[] getEstadoUsuarioSel() {
			return this.estadoUsuarioSel;
		}

		public void setGestor(String gestor) {
			this.gestor = gestor;
		}

		public String getGestor() {
			return this.gestor;
		}

		public void setDsGrupo(String dsGrupo) {
			this.dsGrupo = dsGrupo;
		}

		public String getDsGrupo() {
			return this.dsGrupo;
		}

		public void setIdContatoOrigem(String idContatoOrigem) {
			this.idContatoOrigem = idContatoOrigem;
		}

		public String getIdContatoOrigem() {
			return this.idContatoOrigem;
		}

		public void setNmContatoOrigem(String nmContatoOrigem) {
			this.nmContatoOrigem = nmContatoOrigem;
		}

		public String getNmContatoOrigem() {
			return this.nmContatoOrigem;
		}

		public void setFlQuantitativo(String flQuantitativo) {
			this.flQuantitativo = flQuantitativo;
		}

		public String getFlQuantitativo() {
			return this.flQuantitativo;
		}

		public void setCabecRelatorios(LinkedHashMap cabecRelatorios) {
			this.cabecRelatorios = cabecRelatorios;
		}

		public LinkedHashMap getCabecRelatorios() {
			return this.cabecRelatorios;
		}

		public void setExportar(boolean exportar) {
			this.exportar = exportar;
		}

		public boolean isExportar() {
			return this.exportar;
		}
	}

	public RelatorioForm getRelatorioForm() {
		return relatorioForm;
	}

	public Stack getStNiveisPalitagem() {
		return this.stNiveisPalitagem;
	}
}
