package fidelizacao.Relatorio;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.StringTokenizer;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.campanha.relatorio.RelatorioCampanhaFacadeImpl;
import br.com.vivo.fo.ctrls.fidelizacao.manter.ManterPlanoFacade;
import br.com.vivo.fo.ctrls.fidelizacao.relatorios.RelatorioFacadeFid;
import br.com.vivo.fo.fidelizacao.vo.ClassificacaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoGrupoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoTipoClienteVO;
import br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO;
import br.com.vivo.fo.fidelizacao.vo.OfertaFidelizacaoVO;
import br.com.vivo.fo.fidelizacao.vo.PlanoPesquisaVODocument.PlanoPesquisaVO;
import br.com.vivo.fo.fidelizacao.vo.PlanoVODocument.PlanoVO;
import br.com.vivo.fo.fidelizacao.vo.RelaMovimenDiariasVODocument.RelaMovimenDiariasVO;
import br.com.vivo.fo.fidelizacao.vo.RelaMovimenDiariasVODocument.RelaMovimenDiariasVO.LinhaMovimen;
import br.com.vivo.fo.fidelizacao.vo.RelaOfertasTotMensalVODocument.RelaOfertasTotMensalVO;
import br.com.vivo.fo.fidelizacao.vo.RelaOfertasTotMensalVODocument.RelaOfertasTotMensalVO.LinhaMensal;
import br.com.vivo.fo.fidelizacao.vo.RelatorioAdimplenciaVODocument.RelatorioAdimplenciaVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioCCContaCorrenteVODocument.RelatorioCCContaCorrenteVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioLigacaoRetencaoVODocument.RelatorioLigacaoRetencaoVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioLigacaoRetencaoVODocument.RelatorioLigacaoRetencaoVO.LinhasLigacao;
import br.com.vivo.fo.fidelizacao.vo.RelatorioNotesLojaVODocument.RelatorioNotesLojaVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioOperadorVODocument.RelatorioOperadorVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioOperadorVODocument.RelatorioOperadorVO.LinhaOperador;
import br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioResDestinoVODocument.RelatorioResDestinoVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioReteOfertaVODocument.RelatorioReteOfertaVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioReteOfertaVODocument.RelatorioReteOfertaVO.LinhaOferta;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosDistincaoVODocument.RelatorioRetePlanosDistincaoVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosVODocument.RelatorioRetePlanosVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoDistincaoVODocument.RelatorioRetencaoDistincaoVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoDistincaoVODocument.RelatorioRetencaoDistincaoVO.LinhaRetencaoDistincao;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoInputVODocument.RelatorioRetencaoInputVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoVODocument.RelatorioRetencaoVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoVODocument.RelatorioRetencaoVO.LinhaRetencao;
import br.com.vivo.fo.fidelizacao.vo.RelatorioTodasOfertasVODocument.RelatorioTodasOfertasVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UsuariosVODocument.UsuariosVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class RelatorioController extends AbstractAction {

	private static final long serialVersionUID = -3344347076142584148L;

	@EJB
	private RelatorioFacadeFid myControl;

	@EJB
	private ManterPlanoFacade manterPlanosFac;

	@EJB
	private RelatorioCampanhaFacadeImpl relatorioFacade;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("Fidelizacao");

	private FormDadosPesquisa formDadosPesquisa;
	private FormRelatorioRetencao formRelatorioRetencao;
	private FormLigacaoRetencao formLigacaoRetencao;
	private FormOperador formOperador;
	private FormMovimenDiarias formMovimenDiarias;
	private FormOferta formOferta;
	private FormReteDistincao formReteDistincao;
	private FormTodasOfertas formTodasOfertas;
	private FormResultadoDestino formResultadoDestino;
	private FormRelatorioDistDest formRelatorioDistDest;
	private FormRelaTotMensal formRelaTotMensal;
	private FormRelaPlanos formRelaPlanos;
	private FormRelaPlanosDist formRelaPlanosDist;
	private FormRelaCtaCte formRelaCtaCte;
	private FormNotesLoja formNotesLoja;
	private FormAdimplencia formAdimplencia;

	public String user;

	private final String SEPARADOR_CSV = "|";

	private RelatorioResDestinoVO relatorioDestino;

	public RelatorioResDestinoVO getRelatorioDestino() {
		if (relatorioDestino == null) {
			relatorioDestino = RelatorioResDestinoVO.Factory.newInstance();
		}
		return relatorioDestino;
	}

	private RelaMovimenDiariasVO relatorioMDiaria;

	public RelaMovimenDiariasVO getRelatorioMDiaria() {
		if (relatorioMDiaria == null) {
			relatorioMDiaria = RelaMovimenDiariasVO.Factory.newInstance();
		}
		return relatorioMDiaria;
	}

	private RelatorioTodasOfertasVO relatorioTOfertas;

	public RelatorioTodasOfertasVO getRelatorioTOfertas() {
		if (relatorioTOfertas == null) {
			relatorioTOfertas = RelatorioTodasOfertasVO.Factory.newInstance();
		}
		return relatorioTOfertas;
	}

	private RelatorioOperadorVO relatorioOperador;

	public RelatorioOperadorVO getRelatorioOperador() {
		if (relatorioOperador == null) {
			relatorioOperador = RelatorioOperadorVO.Factory.newInstance();
		}
		return relatorioOperador;
	}

	private RelatorioRetencaoVO relatorioRetencao;

	public RelatorioRetencaoVO getRelatorioRetencao() {
		if (relatorioRetencao == null) {
			relatorioRetencao = RelatorioRetencaoVO.Factory.newInstance();
		}
		return relatorioRetencao;
	}

	private RelatorioReteOfertaVO relatorioOferta;

	public RelatorioReteOfertaVO getRelatorioOferta() {
		if (relatorioOferta == null) {
			relatorioOferta = RelatorioReteOfertaVO.Factory.newInstance();
		}
		return relatorioOferta;
	}

	private RelatorioRetencaoDistincaoVO relatorioRetencaoDistincao;

	public RelatorioRetencaoDistincaoVO getRelatorioRetencaoDistincao() {
		if (relatorioRetencaoDistincao == null) {
			relatorioRetencaoDistincao = RelatorioRetencaoDistincaoVO.Factory.newInstance();
		}
		return relatorioRetencaoDistincao;
	}

	private RelatorioLigacaoRetencaoVO relatorioLigacaoRetencao;

	public RelatorioLigacaoRetencaoVO getRelatorioLigacaoRetencao() {
		if (relatorioLigacaoRetencao == null) {
			relatorioLigacaoRetencao = RelatorioLigacaoRetencaoVO.Factory.newInstance();
		}
		return relatorioLigacaoRetencao;
	}

	private RelatorioResDestinoVO relatorioResDestino;

	public RelatorioResDestinoVO getResDestino() {
		if (relatorioResDestino == null) {
			relatorioResDestino = RelatorioResDestinoVO.Factory.newInstance();
		}
		return relatorioResDestino;
	}

	private RelatorioCCContaCorrenteVO relatorioCC;

	public RelatorioCCContaCorrenteVO getRelatorioCC() {
		if (relatorioCC == null) {
			relatorioCC = RelatorioCCContaCorrenteVO.Factory.newInstance();
		}
		return relatorioCC;
	}

	private RelaOfertasTotMensalVO relatorioOfertasTotMensal;

	public RelaOfertasTotMensalVO getRelatorioOfertasTotMensal() {
		if (relatorioOfertasTotMensal == null) {
			relatorioOfertasTotMensal = RelaOfertasTotMensalVO.Factory.newInstance();
		}
		return relatorioOfertasTotMensal;
	}

	private RelatorioRetePlanosVO relatorioRetePlanos;

	public RelatorioRetePlanosVO getRelatorioRetePlanos() {
		if (relatorioRetePlanos == null) {
			relatorioRetePlanos = RelatorioRetePlanosVO.Factory.newInstance();
		}
		return relatorioRetePlanos;
	}

	private RelatorioNotesLojaVO relatorioNotesLoja;

	public RelatorioNotesLojaVO getRelatorioNotesLoja() {
		if (relatorioNotesLoja == null) {
			relatorioNotesLoja = RelatorioNotesLojaVO.Factory.newInstance();
		}
		return relatorioNotesLoja;
	}

	private RelatorioRetePlanosDistincaoVO relatorioRetePlanosDist;

	public RelatorioRetePlanosDistincaoVO getRetePlanosDist() {
		if (relatorioRetePlanosDist == null) {
			relatorioRetePlanosDist = RelatorioRetePlanosDistincaoVO.Factory.newInstance();
		}
		return relatorioRetePlanosDist;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("gerarRelatorio".equals(mapping.getParameter())) {
			return gerarRelatorio(mapping, form, request, response);
		} else if ("sair".equals(mapping.getParameter())) {
			return sair(mapping, form, request, response);
		} else if ("gerarArquivo".equals(mapping.getParameter())) {
			return gerarArquivo(mapping, form, request, response);
		} else if ("carregarPlanos".equals(mapping.getParameter())) {
			return carregarPlanos(mapping, form, request, response);
		} else if ("printDestino".equals(mapping.getParameter())) {
			return printDestino(mapping, form, request, response);
		} else if ("printMDiaria".equals(mapping.getParameter())) {
			return printMDiaria(mapping, form, request, response);
		} else if ("printTOfertas".equals(mapping.getParameter())) {
			return printTOfertas(mapping, form, request, response);
		} else if ("printRetencao".equals(mapping.getParameter())) {
			return printRetencao(mapping, form, request, response);
		} else if ("printRetencaoOferta".equals(mapping.getParameter())) {
			return printRetencaoOferta(mapping, form, request, response);
		} else if ("printRetencaoDistincao".equals(mapping.getParameter())) {
			return printRetencaoDistincao(mapping, form, request, response);
		} else if ("printLigacaoRetencao".equals(mapping.getParameter())) {
			return printLigacaoRetencao(mapping, form, request, response);
		} else if ("printDestinoDistincao".equals(mapping.getParameter())) {
			return printDestinoDistincao(mapping, form, request, response);
		} else if ("printCtaCte".equals(mapping.getParameter())) {
			return printCtaCte(mapping, form, request, response);
		} else if ("printOfertaTotalMensal".equals(mapping.getParameter())) {
			return printOfertaTotalMensal(mapping, form, request, response);
		} else if ("printRetencaoPorPlanos".equals(mapping.getParameter())) {
			return printRetencaoPorPlanos(mapping, form, request, response);
		} else if ("printOperador".equals(mapping.getParameter())) {
			return printOperador(mapping, form, request, response);
		} else if ("printNotesLoja".equals(mapping.getParameter())) {
			return printNotesLoja(mapping, form, request, response);
		} else if ("printPlanos".equals(mapping.getParameter())) {
			return printPlanos(mapping, form, request, response);
		} else if ("printPlanosDistincao".equals(mapping.getParameter())) {
			return printPlanosDistincao(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="begin"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		RelatorioRetencaoInputVO vo = null;
		if (formDadosPesquisa == null) {
			formDadosPesquisa = new FormDadosPesquisa();
		}
		user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			vo = myControl.carregaDadosCriterio(user);
			formDadosPesquisa.setDados(vo);
			formDadosPesquisa.setClassificacaoVO(vo.getClassificacao().getClassificacaoVOArray());
			formDadosPesquisa.setFidelizacaoGrupoVO(vo.getGrupo().getFidelizacaoGrupoVOArray());
			formDadosPesquisa.setFidelizacaoTipoClienteVO(vo.getTipoCliente().getFidelizacaoTipoClienteVOArray());
			formDadosPesquisa.setOfertaFidelizacaoVO(vo.getOferta().getOfertaFidelizacaoVOArray());
			// formDadosPesquisa.setOperadoraVO(vo.getOperadora().getOperadoraVOArray());
			// Combo UF Operador incidencia 825
			UsuariosVO ufOperadora = UsuariosVO.Factory.newInstance();
			ufOperadora = relatorioFacade.getUFOperadora(user);
			formDadosPesquisa.setOperadoraVO(manterPlanosFac.getRegionais(user).getItemListaVOArray());
			if (request.getAttribute("msgErro") != null) {
				request.removeAttribute("msgErro");
			}

			// listaGrupo =
			// matrizOfertaFac.getDadosIniciais(user).getFidelizacaoListaGeralVOArray(3);

			// Combo Grupo incidencia

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * retorna o VO para fazer a pesquisa do relatório
	 */
	private RelatorioPesquisaVO getRelatorioPesquisaVO(FormDadosPesquisa form) {
		getFormDadosPesquisa().setIdRelatorio(form.getIdRelatorio());
		RelatorioPesquisaVO relatorioPesquisaVO = RelatorioPesquisaVO.Factory.newInstance();
		relatorioPesquisaVO.setClassificacao(form.getClassificacao());
		relatorioPesquisaVO.setCustoPonto(form.getCustoPonto());
		relatorioPesquisaVO.setDataFim(form.getDataFim());
		relatorioPesquisaVO.setDataInicio(form.getDataInicio());
		relatorioPesquisaVO.setGrupo(form.getGrupo());
		relatorioPesquisaVO.setIdRelatorio(form.getIdRelatorio());
		relatorioPesquisaVO.setOferta(form.getOferta());
		relatorioPesquisaVO.setOperadoraArray(form.getOperadora());
		relatorioPesquisaVO.setIdsOperadoras(getIdsOperadoras(form.getOperadora()));
		relatorioPesquisaVO.setTipoCliente(form.getTipoCliente());
		relatorioPesquisaVO.setOperador(form.getOperador());
		relatorioPesquisaVO.setPlano(form.getPlano());
		return relatorioPesquisaVO;
	}

	private String getIdsOperadoras(String[] ids) {
		StringBuffer retorno = new StringBuffer();

		if (ids != null && ids.length > 0) {
			for (int i = 0; i < ids.length; i++) {
				retorno.append(ids[i]);
				if (i < (ids.length - 1)) {
					retorno.append(",");
				}
			}
		}
		return retorno.toString();
	}

	/**
	 * retorna o VO para fazer a pesquisa do relatório
	 */
	private Boolean validaFecha(FormDadosPesquisa form, HttpServletRequest request) {

		String dataIniSt = form.getDataInicio();
		String dataFimSt = form.getDataFim();
		Date dataIni = new Date();
		Date dataFim = new Date();
		StringTokenizer stIni = new StringTokenizer(dataIniSt, "/");
		StringTokenizer stFim = new StringTokenizer(dataFimSt, "/");

		// Data Início.
		dataIni.setHours(0);
		dataIni.setMinutes(0);
		dataIni.setSeconds(0);
		dataIni.setDate(Integer.parseInt(stIni.nextToken()));
		dataIni.setMonth(Integer.parseInt(stIni.nextToken()) - 1);
		dataIni.setYear(Integer.parseInt(stIni.nextToken()) - 1900);

		// Data Fim.
		dataFim.setHours(0);
		dataFim.setMinutes(0);
		dataFim.setSeconds(0);
		dataFim.setDate(Integer.parseInt(stFim.nextToken()));
		dataFim.setMonth(Integer.parseInt(stFim.nextToken()) - 1);
		dataFim.setYear(Integer.parseInt(stFim.nextToken()) - 1900);

		// Compara as datas.
		if (dataIni.getTime() > dataFim.getTime()) {
			// data inicial maior que data final.
			// this.setTemValData(true);
			// this.setValData("Data inicial não deve ser maior que a data final!");
			// request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW,
			// this);
			// return mapping.findForward("inicio");
			request.setAttribute("errorFecha", "Data inicial não deve ser maior que a data final!");
			return Boolean.FALSE;
		} else if ((dataFim.getTime() - dataIni.getTime()) / 1000 > 2678400) {
			// data final mais que 31 dias maior do que a data inicial.
			// this.setTemValData(true);
			// this.setValData("Data final deve ser no máximo 31 dias maior que data inicial!");
			// request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW,
			// this);
			// return mapping.findForward("inicio");
			request.setAttribute("errorFecha", "Data final deve ser no máximo 31 dias maior que data inicial!");
			return Boolean.FALSE;
		}
		// request.setAttribute("errorFecha","");
		return Boolean.TRUE;

	}

	/**
	 * Converte String nula para String vazia
	 **/
	private String converteNulo(String value) {
		return value != null ? value : ConstantesCRM.SVAZIO;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="inicio" path="index.jsp"
	 * @jpf:forward name="relatorioRetencao" path="relatorioRetencao.jsp"
	 * @jpf:forward name="relatorioLigRetencao"
	 *              path="relatorioLigacaoRetencao.jsp"
	 * @jpf:forward name="relatorioOperador" path="RelatorioOperador.jsp"
	 * @jpf:forward name="relatorioMovimenDiarias"
	 *              path="relatorioMovimenDiarias.jsp"
	 * @jpf:forward name="relatorioOferta" path="relatorioOferta.jsp"
	 * @jpf:forward name="relatorioDestino" path="relatorioDestino.jsp"
	 * @jpf:forward name="relatorioDistincao" path="relatorioDistincao.jsp"
	 * @jpf:forward name="relatorioDistDest" path="relatorioDistDest.jsp"
	 * @jpf:forward name="relaTodasOfertas" path="relatorioTodasOfertas.jsp"
	 * @jpf:forward name="relatorioPlanos" path="relatorioPlanos.jsp"
	 * @jpf:forward name="relatorioTotMensal" path="relatorioTotMensal.jsp"
	 * @jpf:forward name="relatorioPlanosDist" path="relatorioPlanosDist.jsp"
	 * @jpf:forward name="relatorioCtaCte" path="relatorioCtaCte.jsp"
	 * @jpf:forward name="relatorioNotesLoja" path="relatorioNotesLoja.jsp"
	 * @jpf:forward name="relatorioAdimplencia" path="relatorioAdimplencia.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:validation-error-forward name="failure" path="index.jsp"
	 * 
	 */
	protected ActionForward gerarRelatorio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		FormDadosPesquisa form = (FormDadosPesquisa) formParam;
		ActionRedirect forward = new ActionRedirect(mapping.findForward("inicio"));

		try {
			RelatorioPesquisaVO relatorioPesquisaVO = getRelatorioPesquisaVO(form);
			formDadosPesquisa.setMsgErro(new String(ConstantesCRM.SVAZIO));
			// Define qual relatório
			montaDescricoes(form);
			// formDadosPesquisa = form;
			formDadosPesquisa.setDataFim(form.getDataFim());
			formDadosPesquisa.setDataInicio(form.getDataInicio());
			formDadosPesquisa.setOperador(form.getOperador());
			Boolean validaf = validaFecha(form, request);

			if (!validaf.booleanValue()) {
				formDadosPesquisa.setClassificacao(form.getClassificacao());
				formDadosPesquisa.setOperadora(form.getOperadora());
				formDadosPesquisa.setTipoCliente(form.getTipoCliente());
				formDadosPesquisa.setOferta(form.getOferta());
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("inicio");
			} else {
				formDadosPesquisa.setClassificacao(new String(ConstantesCRM.SVAZIO));
				formDadosPesquisa.setOperadora(new String[0]);
				formDadosPesquisa.setTipoCliente(new String(ConstantesCRM.SVAZIO));
				formDadosPesquisa.setOferta(new String(ConstantesCRM.SVAZIO));
			}

			// RETENÇÃO E RETENÇÃO COM DISTINÇÃO

			if (form.getIdRelatorio().equals(ConstantesCRM.SONE) || form.getIdRelatorio().equals("10")) {
				// relatorioPesquisaVO.setIdRelatorio("1");
				RelatorioRetencaoVO relatorioRetencaoVO = this.myControl.relatorioRetencao(user, relatorioPesquisaVO);
				relatorioRetencao = relatorioRetencaoVO;
				formRelatorioRetencao = new FormRelatorioRetencao();
				formRelatorioRetencao.setLinhaRetencao(relatorioRetencaoVO.getLinhaRetencaoArray());
				forward = new ActionRedirect(mapping.findForward(form.getIdRelatorio().equals(ConstantesCRM.SONE) ? "relatorioRetencao" : "relatorioDistincao"));
				forward.addParameter("totalReg", relatorioRetencaoVO.getTotal());

				if (relatorioRetencaoVO.getTotal() == null || relatorioRetencaoVO.getTotal().equals(ConstantesCRM.SZERO) || relatorioRetencaoVO.getTotal().equals(ConstantesCRM.SVAZIO)) {
					formDadosPesquisa.setMsgErro("A Pesquisa Não Retornou Resultado");
					// rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
					request.setAttribute("msgErro", "A Pesquisa Não Retornou Resultado");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inicio");
				}

				return forward;
			} else if (form.getIdRelatorio().equals(ConstantesCRM.SNINE)) {
				RelatorioLigacaoRetencaoVO relatorio = this.myControl.relatorioLigacaoRetencao(user, relatorioPesquisaVO);
				relatorioLigacaoRetencao = relatorio;
				formLigacaoRetencao = new FormLigacaoRetencao();
				formLigacaoRetencao.setLinhaLigacao(relatorio.getLinhasLigacaoArray());
				forward = new ActionRedirect(mapping.findForward("relatorioLigRetencao"));
				forward.addParameter("totalReg", String.valueOf(formLigacaoRetencao.getLinhaLigacao().length));
				forward.addParameter("totalReg", relatorio.getTotal());
				if (relatorio.getTotal() == null || relatorio.getTotal().equals("0") || relatorio.getTotal().equals(ConstantesCRM.SVAZIO)) {
					formDadosPesquisa.setMsgErro("A Pesquisa Não Retornou Resultado");
					// rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inicio");
				}
				return forward;
			} else if (form.getIdRelatorio().equals(ConstantesCRM.SEIGHT)) {
				RelatorioOperadorVO relatorio = this.myControl.relatorioOperador(user, relatorioPesquisaVO);
				relatorioOperador = relatorio;
				formOperador = new FormOperador();
				formOperador.setLinhaOperador(relatorio.getLinhaOperadorArray());
				forward = new ActionRedirect(mapping.findForward("relatorioOperador"));
				forward.addParameter("totalReg", relatorio.getTotal());
				if (relatorio.getTotal() == null || relatorio.getTotal().equals(ConstantesCRM.SZERO) || relatorio.getTotal().equals(ConstantesCRM.SVAZIO)) {
					formDadosPesquisa.setMsgErro("A Pesquisa Não Retornou Resultado");
					// rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inicio");
				}
				return forward;
			} else if (form.getIdRelatorio().equals(ConstantesCRM.STWO)) { // MOVIMENTACAO
				// DIARIA
				RelaMovimenDiariasVO relatorio = this.myControl.relatorioMovimenDiarias(user, relatorioPesquisaVO);
				relatorioMDiaria = relatorio;
				formMovimenDiarias = new FormMovimenDiarias();
				formMovimenDiarias.setLinhaMovimen(relatorio.getLinhaMovimenArray());
				forward = new ActionRedirect(mapping.findForward("relatorioMovimenDiarias"));
				forward.addParameter("totalReg", relatorio.getTotal());
				if (relatorio.getTotal() == null || relatorio.getTotal().equals(ConstantesCRM.SZERO) || relatorio.getTotal().equals(ConstantesCRM.SVAZIO)) {
					formDadosPesquisa.setMsgErro("A Pesquisa Não Retornou Resultado");
					// rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inicio");
				}
				return forward;
			} else if (form.getIdRelatorio().equals(ConstantesCRM.SFIVE)) { // RETENCAO POR
				// OFERTAS
				RelatorioReteOfertaVO relatorio = this.myControl.relatorioReteOferta(user, relatorioPesquisaVO);
				relatorioOferta = relatorio;
				formOferta = new FormOferta();
				formOferta.setLinhaOferta(relatorio.getLinhaOfertaArray());
				forward = new ActionRedirect(mapping.findForward("relatorioOferta"));
				forward.addParameter("totalReg", relatorio.getTotal());
				if (relatorio.getTotal() == null || relatorio.getTotal().equals(ConstantesCRM.SZERO) || relatorio.getTotal().equals(ConstantesCRM.SVAZIO)) {
					formDadosPesquisa.setMsgErro("A Pesquisa Não Retornou Resultado");
					// rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inicio");
				} else {
					calcularRelatorioRetencaoPorOfertas(relatorio);
					relatorioOferta = relatorio;
				}
				return forward;
			} else if (form.getIdRelatorio().equals("10")) {
				// Retencao com Distincao
				RelatorioRetencaoDistincaoVO retencaoDist = RelatorioRetencaoDistincaoVO.Factory.newInstance();
				retencaoDist = this.myControl.relaRetencaoDistincao(user, relatorioPesquisaVO);
				relatorioRetencaoDistincao = retencaoDist;
				formReteDistincao = new FormReteDistincao();
				formReteDistincao.setLinhaRetencaoDistincao(retencaoDist.getLinhaRetencaoDistincaoArray());
				forward = new ActionRedirect(mapping.findForward("relatorioDistincao"));
				forward.addParameter("totalReg", retencaoDist.getTotal());
				if (retencaoDist.getTotal() == null || retencaoDist.getTotal().equals(ConstantesCRM.SZERO) || retencaoDist.getTotal().equals(ConstantesCRM.SVAZIO)) {
					formDadosPesquisa.setMsgErro("A Pesquisa Não Retornou Resultado");
					// rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inicio");
				}
				return forward;
			} else if (form.getIdRelatorio().equals(ConstantesCRM.STHREE)) { // OFERTAS
				// Retencao Todas Ofertas
				RelatorioTodasOfertasVO retencaoTodasOfe = RelatorioTodasOfertasVO.Factory.newInstance();
				retencaoTodasOfe = this.myControl.relaTodasOfertas(user, relatorioPesquisaVO);
				relatorioTOfertas = retencaoTodasOfe;
				formTodasOfertas = new FormTodasOfertas();
				formTodasOfertas.setLinhaOferta(retencaoTodasOfe.getLinhaOfertaArray());
				forward = new ActionRedirect(mapping.findForward("relaTodasOfertas"));
				forward.addParameter("totalReg", retencaoTodasOfe.getTotal());
				if (retencaoTodasOfe.getTotal() == null || retencaoTodasOfe.getTotal().equals(ConstantesCRM.SZERO) || retencaoTodasOfe.getTotal().equals(ConstantesCRM.SVAZIO)) {
					formDadosPesquisa.setMsgErro("A Pesquisa Não Retornou Resultado");
					// rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
					request.setAttribute("msgErro", request.getParameter("A Pesquisa Não Retornou Resultado"));
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inicio");
				} else {
					calcularRelatorioOfertas(retencaoTodasOfe);
					relatorioTOfertas = retencaoTodasOfe;
				}
				return forward;
			} else if (form.getIdRelatorio().equals(ConstantesCRM.SFOUR)) { // RESULTADO DESTINO
				// Retencao Destino
				RelatorioResDestinoVO resDestino = RelatorioResDestinoVO.Factory.newInstance();
				resDestino = this.myControl.relaResDestino(user, relatorioPesquisaVO);
				relatorioDestino = resDestino;
				formResultadoDestino = new FormResultadoDestino();
				formResultadoDestino.setLinhaDestino(resDestino.getLinhaDestinoArray());
				forward = new ActionRedirect(mapping.findForward("relatorioDestino"));
				forward.addParameter("totalReg", resDestino.getTotal());
				if (resDestino.getTotal() == null || resDestino.getTotal().equals(ConstantesCRM.SZERO) || resDestino.getTotal().equals(ConstantesCRM.SVAZIO)) {
					formDadosPesquisa.setMsgErro("A Pesquisa Não Retornou Resultado");
					// rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inicio");
				}
				return forward;
			}
			if (form.getIdRelatorio().equals(ConstantesCRM.SSIX)) { // RESULTADO DESTINO COM
				// DISTINÇÃO
				// Retencao Destino
				RelatorioResDestinoVO resDestino = RelatorioResDestinoVO.Factory.newInstance();
				// relatorioPesquisaVO.setIdRelatorio("4");
				resDestino = this.myControl.relaResDestino(user, relatorioPesquisaVO);
				relatorioResDestino = resDestino;
				formRelatorioDistDest = new FormRelatorioDistDest();
				formRelatorioDistDest.setLinhaDestino(resDestino.getLinhaDestinoArray());
				forward = new ActionRedirect(mapping.findForward("relatorioDistDest"));
				forward.addParameter("totalReg", resDestino.getTotal());
				if (resDestino.getTotal() == null || resDestino.getTotal().equals(ConstantesCRM.SZERO) || resDestino.getTotal().equals(ConstantesCRM.SVAZIO)) {
					formDadosPesquisa.setMsgErro("A Pesquisa Não Retornou Resultado");
					// rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inicio");
				}
				return forward;
			}
			if (form.getIdRelatorio().equals("13")) {
				// Retencao Destino
				RelaOfertasTotMensalVO relaTotMensal = RelaOfertasTotMensalVO.Factory.newInstance();
				relaTotMensal = this.myControl.relaOfertasTotMensal(user, relatorioPesquisaVO);
				relatorioOfertasTotMensal = relaTotMensal;

				formRelaTotMensal = new FormRelaTotMensal();
				formRelaTotMensal.setLinhaMensal(relaTotMensal.getLinhaMensalArray());
				forward = new ActionRedirect(mapping.findForward("relatorioTotMensal"));
				forward.addParameter("totalReg", relaTotMensal.getTotal());
				if (relaTotMensal.getTotal() == null || relaTotMensal.getTotal().equals(ConstantesCRM.SZERO) || relaTotMensal.getTotal().equals(ConstantesCRM.SVAZIO)) {
					formDadosPesquisa.setMsgErro("A Pesquisa Não Retornou Resultado");
					// rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inicio");
				}
				return forward;
			}

			if (form.getIdRelatorio().equals("11") || form.getIdRelatorio().equals("12")) {
				RelatorioRetePlanosVO relaPlanos;
				RelatorioRetePlanosDistincaoVO relaPlanosDist;

				if (formDadosPesquisa.getPlanoVO() != null) {
					relaPlanos = RelatorioRetePlanosVO.Factory.newInstance();

					PlanoVO[] planoVO = new PlanoVO[formDadosPesquisa.getPlanoVO().length];
					planoVO = formDadosPesquisa.getPlanoVO();

					for (int a = 0; a < formDadosPesquisa.getPlanoVO().length; a++) {
						if (planoVO[a].getId().equals(form.getPlano())) {

							// planoVO[0].getDescricao()
							request.setAttribute("dsPlano", planoVO[a].getDescricao());
							a = a + formDadosPesquisa.getPlanoVO().length;
						}
					}
				} else {
					request.setAttribute("dsPlano", ConstantesCRM.SVAZIO);
				}

				// if( form.getIdRelatorio().equals("11") ){
				// Retencao Planos
				// relatorioPesquisaVO.setIdRelatorio("11");
				relaPlanos = this.myControl.relaPlanos(user, relatorioPesquisaVO);
				relatorioRetePlanos = relaPlanos;

				formRelaPlanos = new FormRelaPlanos();
				formRelaPlanos.setLinhaPlanos(relaPlanos.getLinhaPlanosArray());
				forward = new ActionRedirect(mapping.findForward(form.getIdRelatorio().equals("11") ? "relatorioPlanos" : "relatorioPlanosDist"));

				if (relaPlanos.getTotal() == null || relaPlanos.getTotal().equals(ConstantesCRM.SZERO) || relaPlanos.getTotal().equals(ConstantesCRM.SVAZIO)) {
					formDadosPesquisa.setMsgErro("A Pesquisa Não Retornou Resultado");
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inicio");
				} else {
					calcularRelatorioPlanos(relaPlanos);
					relatorioRetePlanos = relaPlanos;
				}
				forward.addParameter("totalReg", relaPlanos.getTotal());
				return forward;

				/*
				 * }else{ // Retencao Planos Distincao relaPlanosDist =
				 * this.myControl.relaPlanosDistincao(user,relatorioPesquisaVO);
				 * relatorioRetePlanosDist = relaPlanosDist; formRelaPlanosDist
				 * = new FormRelaPlanosDist();
				 * formRelaPlanosDist.setLinhaPlanosDistincao
				 * (relaPlanosDist.getLinhaPlanosDistincaoArray()); forward =
				 * new ActionForward("relatorioPlanosDist");
				 * forward.addParameter("totalReg", relaPlanosDist.getTotal());
				 * if(relaPlanosDist.getTotal().equals("0")){
				 * formDadosPesquisa.setMsgErro
				 * ("A Pesquisa Não Retornou Resultado");
				 * request.setAttribute("idRelatorio",
				 * request.getParameter("idRelatorio")); return
				 * mapping.findForward("inicio"); } return forward; }
				 */
			}
			if (form.getIdRelatorio().equals(ConstantesCRM.SSEVEN)) {
				// Retencao Destino
				RelatorioCCContaCorrenteVO relaCtaCte = RelatorioCCContaCorrenteVO.Factory.newInstance();
				relaCtaCte = this.myControl.relaCCContaCorrente(user, relatorioPesquisaVO);

				for (int i = 0; i < relaCtaCte.getLinhaCCContaCorrenteArray().length; i++) {
					relaCtaCte.getLinhaCCContaCorrenteArray(i).setBonusTotal("R$ " + relaCtaCte.getLinhaCCContaCorrenteArray(i).getBonusTotal());
				}

				relatorioCC = relaCtaCte;
				formRelaCtaCte = new FormRelaCtaCte();
				formRelaCtaCte.setLinhaCCContaCorrente(relaCtaCte.getLinhaCCContaCorrenteArray());
				forward = new ActionRedirect(mapping.findForward("relatorioCtaCte"));
				forward.addParameter("totalReg", relaCtaCte.getTotal());
				if (relaCtaCte.getTotal() == null || relaCtaCte.getTotal().equals(ConstantesCRM.SZERO) || relaCtaCte.getTotal().equals(ConstantesCRM.SVAZIO)) {
					formDadosPesquisa.setMsgErro("A Pesquisa Não Retornou Resultado");
					// rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inicio");
				}
				return forward;
			}

			// 15 Notes Loja
			if (form.getIdRelatorio().equals("15")) {
				// Retencao Destino
				RelatorioNotesLojaVO relaNotesLoja = RelatorioNotesLojaVO.Factory.newInstance();
				relaNotesLoja = this.myControl.relaNotesLoja(user, relatorioPesquisaVO);
				relatorioNotesLoja = relaNotesLoja;
				formNotesLoja = new FormNotesLoja();
				formNotesLoja.setLinhaNotesLoja(relaNotesLoja.getLinhaNotesLojaArray());
				forward = new ActionRedirect(mapping.findForward("relatorioNotesLoja"));
				forward.addParameter("totalReg", relaNotesLoja.getTotal());
				if (relaNotesLoja.getTotal() == null || relaNotesLoja.getTotal().equals(ConstantesCRM.SZERO) || relaNotesLoja.getTotal().equals(ConstantesCRM.SVAZIO)) {
					formDadosPesquisa.setMsgErro("A Pesquisa Não Retornou Resultado");
					// rela = ConstantesCRM.SUCCESS;
					request.setAttribute("idRelatorio", request.getParameter("idRelatorio"));
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("inicio");
				}

				return forward;
			}

			// 16 Relatório Quantitativo de Análise de Adimplência
			if ("16".equals(form.getIdRelatorio())) {

				RelatorioAdimplenciaVO relatorioAdimplencia = RelatorioAdimplenciaVO.Factory.newInstance();
				relatorioAdimplencia = this.myControl.relatorioAdimplencia(user, relatorioPesquisaVO);
				// relatorioAdimplencia.setTotalAdimplentes("200");
				// relatorioAdimplencia.setTotalInadimplentes("400");
				// relatorioAdimplencia.setTotalConsultas("600");

				formAdimplencia = new FormAdimplencia();
				formAdimplencia.setTotalAdimplentes(relatorioAdimplencia.getTotalAdimplentes());
				formAdimplencia.setTotalInadimplentes(relatorioAdimplencia.getTotalInadimplentes());
				formAdimplencia.setTotalConsultas(relatorioAdimplencia.getTotalConsultas());

				forward = new ActionRedirect(mapping.findForward("relatorioAdimplencia"));
				return forward;
			}

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("inicio");
	}

	private void montaDescricoes(FormDadosPesquisa form) {
		/*
		 * dsClassificacao dsOperadora dsTipoCliente dsOferta
		 */
		int i;
		// getClassificacao
		if (form.getClassificacao() != null) {
			formDadosPesquisa.setDsClassificacao(ConstantesCRM.SVAZIO);
			for (i = 0; i < formDadosPesquisa.getClassificacaoVO().length; i++) {
				if (formDadosPesquisa.getClassificacaoVO()[i].getId().equals(form.getClassificacao())) {
					formDadosPesquisa.setDsClassificacao(formDadosPesquisa.getClassificacaoVO()[i].getDescricao());
					break;
				}
			}
		}
		// getOperadora
		if (form.getOperadora() != null) {
			formDadosPesquisa.setDsOperadora(new String[form.getOperadora().length]);
			for (i = 0; i < form.getOperadora().length; i++) {
				for (int j = 0; j < formDadosPesquisa.getOperadoraVO().length; j++) {
					if (formDadosPesquisa.getOperadoraVO()[j].getId().equals(form.getOperadora()[i])) {
						formDadosPesquisa.getDsOperadora()[i] = formDadosPesquisa.getOperadoraVO()[j].getDescricao();
						break;
					}
				}
			}
		} else if (form.getOperadora() == null || form.getOperadora().length == 0) {
			formDadosPesquisa.setDsOperadora(new String[0]);
		}
		// getOferta
		if (form.getOferta() != null) {
			formDadosPesquisa.setDsOferta(ConstantesCRM.SVAZIO);
			for (i = 0; i < formDadosPesquisa.getOfertaFidelizacaoVO().length; i++) {
				if (formDadosPesquisa.getOfertaFidelizacaoVO()[i].getId().equals(form.getOferta())) {
					formDadosPesquisa.setDsOferta(formDadosPesquisa.getOfertaFidelizacaoVO()[i].getDescricao());
					break;
				}
			}
		}
		// getTipoCliente
		if (form.getTipoCliente() != null) {
			formDadosPesquisa.setDsTipoCliente(ConstantesCRM.SVAZIO);
			for (i = 0; i < formDadosPesquisa.getFidelizacaoTipoClienteVO().length; i++) {
				if (formDadosPesquisa.getFidelizacaoTipoClienteVO()[i].getId().equals(form.getTipoCliente())) {
					formDadosPesquisa.setDsTipoCliente(formDadosPesquisa.getFidelizacaoTipoClienteVO()[i].getDescricao());
					break;
				}
			}
		}
		// getGrupo
		if (form.getGrupo() != null) {
			formDadosPesquisa.setDsGrupo(ConstantesCRM.SVAZIO);
			for (i = 0; i < formDadosPesquisa.getFidelizacaoGrupoVO().length; i++) {
				if (formDadosPesquisa.getFidelizacaoGrupoVO()[i].getId().equals(form.getGrupo())) {
					formDadosPesquisa.setDsGrupo(formDadosPesquisa.getFidelizacaoGrupoVO()[i].getDescricao());
					break;
				}
			}
		}
	}

	/**
	 * Calcula total e total geral.
	 * 
	 * @param retencaoPorOfertas
	 *            RelatorioReteOfertaVO
	 */
	private void calcularRelatorioRetencaoPorOfertas(RelatorioReteOfertaVO retencaoPorOfertas) {
		int countSubTotal = 0;
		int countSubTotal2 = 0;
		int totalAparelho = 0;

		ArrayList subTotal = new ArrayList();

		for (int i = 0; i < retencaoPorOfertas.getLinhaOfertaArray().length; i++) {
			if (retencaoPorOfertas.getLinhaOfertaArray(i).getIntCancelamiento() != null && retencaoPorOfertas.getLinhaOfertaArray(i).getIntCancelamiento().trim().equalsIgnoreCase("total")) {
				subTotal.add(countSubTotal++, retencaoPorOfertas.getLinhaOfertaArray(i).getQtdintencao());
			}
		}

		for (int i = 0; i < retencaoPorOfertas.getLinhaOfertaArray().length; i++) {

			if (retencaoPorOfertas.getLinhaOfertaArray(i).getIntCancelamiento() != null && (retencaoPorOfertas.getLinhaOfertaArray(i).getIntCancelamiento().trim().equalsIgnoreCase("total"))) {
				retencaoPorOfertas.getLinhaOfertaArray(i).setPorcOfertaFiltro(
						calcularPorcentagem(retencaoPorOfertas.getLinhaOfertaArray(i).getOfertaFiltro(), retencaoPorOfertas.getLinhaOfertaArray(i).getQtdoferta()));
				totalAparelho += (new Integer(retencaoPorOfertas.getLinhaOfertaArray(i).getOfertaFiltro())).intValue();
				countSubTotal2++;
				continue;
			}

			if (retencaoPorOfertas.getLinhaOfertaArray(i).getIntCancelamiento() != null && retencaoPorOfertas.getLinhaOfertaArray(i).getIntCancelamiento().trim().equalsIgnoreCase("total geral")) {
				retencaoPorOfertas.getLinhaOfertaArray(i).setPorcoferta(
						calcularPorcentagem(retencaoPorOfertas.getLinhaOfertaArray(i).getQtdoferta(), retencaoPorOfertas.getLinhaOfertaArray(i).getQtdintencao()));
				retencaoPorOfertas.getLinhaOfertaArray(i).setOfertaFiltro(ConstantesCRM.SVAZIO + totalAparelho);
				countSubTotal2++;
				continue;
			}
			retencaoPorOfertas.getLinhaOfertaArray(i).setPorcintencao(calcularPorcentagem(retencaoPorOfertas.getLinhaOfertaArray(i).getQtdintencao(), subTotal.get(countSubTotal2).toString()));

		}

	}

	/**
	 * Calcula totais da coluna Qtde Retenção para o relatório de ofertas
	 * 
	 * @param retencaoTodasOfe
	 *            RelatorioTodasOfertasVO
	 */
	private void calcularRelatorioOfertas(RelatorioTodasOfertasVO retencaoTodasOfe) {
		int countSubTotal = 0;
		int countSubTotal2 = 0;

		ArrayList subTotal = new ArrayList();

		for (int i = 0; i < retencaoTodasOfe.getLinhaOfertaArray().length; i++) {
			if (retencaoTodasOfe.getLinhaOfertaArray(i).getIntCancelamiento() != null && retencaoTodasOfe.getLinhaOfertaArray(i).getIntCancelamiento().trim().equalsIgnoreCase("total")) {
				subTotal.add(countSubTotal++, retencaoTodasOfe.getLinhaOfertaArray(i).getQtdintencao());
			}
		}

		for (int i = 0; i < retencaoTodasOfe.getLinhaOfertaArray().length; i++) {
			if (retencaoTodasOfe.getLinhaOfertaArray(i).getIntCancelamiento() != null
					&& (retencaoTodasOfe.getLinhaOfertaArray(i).getIntCancelamiento().trim().equalsIgnoreCase("total") || retencaoTodasOfe.getLinhaOfertaArray(i).getIntCancelamiento().trim()
							.equalsIgnoreCase("total geral"))) {
				countSubTotal2++;
				continue;
			}
			retencaoTodasOfe.getLinhaOfertaArray(i).setPorcintencao(calcularPorcentagem(retencaoTodasOfe.getLinhaOfertaArray(i).getQtdintencao(), subTotal.get(countSubTotal2).toString()));

		}
	}

	/**
	 * Calcula totais da coluna Qtde Retenção para o relatório de ofertas
	 * 
	 * @param retencaoTodasOfe
	 *            RelatorioTodasOfertasVO
	 */
	private void calcularRelatorioPlanos(RelatorioRetePlanosVO relatorioPlanos) {
		int countSubTotal = 0;
		int countSubTotal2 = 0;
		String totalGeral = ConstantesCRM.SVAZIO;

		ArrayList subTotal = new ArrayList();

		for (int i = 0; i < relatorioPlanos.getLinhaPlanosArray().length; i++) {
			// se total, obtem subtotais
			if (relatorioPlanos.getLinhaPlanosArray(i).getIntCancelamento() != null && relatorioPlanos.getLinhaPlanosArray(i).getIntCancelamento().trim().equalsIgnoreCase("total")) {
				subTotal.add(countSubTotal++, relatorioPlanos.getLinhaPlanosArray(i).getQtdIntencao());
			}
			// se total geral, obtem total geral
			if (relatorioPlanos.getLinhaPlanosArray(i).getIntCancelamento() != null && relatorioPlanos.getLinhaPlanosArray(i).getIntCancelamento().trim().equalsIgnoreCase("total geral")) {
				totalGeral = relatorioPlanos.getLinhaPlanosArray(i).getQtdIntencao();
			}
		}

		for (int i = 0; i < relatorioPlanos.getLinhaPlanosArray().length; i++) {
			// se total geral, porcentagem intenções igual a 100%
			if (relatorioPlanos.getLinhaPlanosArray(i).getIntCancelamento() != null && relatorioPlanos.getLinhaPlanosArray(i).getIntCancelamento().trim().equalsIgnoreCase("total geral")) {
				relatorioPlanos.getLinhaPlanosArray(i).setPercIntencao("100%");
				// se total
			} else if (relatorioPlanos.getLinhaPlanosArray(i).getIntCancelamento() != null && relatorioPlanos.getLinhaPlanosArray(i).getIntCancelamento().trim().equalsIgnoreCase("total")) {
				// calcula porcentagem total referente ao total geral
				relatorioPlanos.getLinhaPlanosArray(i).setPercIntencao(calcularPorcentagem(relatorioPlanos.getLinhaPlanosArray(i).getQtdIntencao(), totalGeral));
				countSubTotal2++;
			} else {
				// calcula porcentagem intencoes referente ao sub total das
				// intencoes
				relatorioPlanos.getLinhaPlanosArray(i).setPercIntencao(calcularPorcentagem(relatorioPlanos.getLinhaPlanosArray(i).getQtdIntencao(), subTotal.get(countSubTotal2).toString()));
			}
			// calcula porcentagem adequacao de planos referente quantidade
			// intencoes
			relatorioPlanos.getLinhaPlanosArray(i).setPercAdeq(calcularPorcentagem(relatorioPlanos.getLinhaPlanosArray(i).getQtdAdeq(), relatorioPlanos.getLinhaPlanosArray(i).getQtdIntencao()));

			// calcula porcentagens dos planos dinâmicos
			if (relatorioPlanos.getLinhaPlanosArray(i).getValoresColunas() != null) {
				// se valor == null, entao colocar o calculo da porcentagem
				// nesse atributo
				for (int y = 0; y < relatorioPlanos.getLinhaPlanosArray(i).getValoresColunas().getValorArray().length; y++) {
					if (relatorioPlanos.getLinhaPlanosArray(i).getValoresColunas().getValorArray(y) == null || relatorioPlanos.getLinhaPlanosArray(i).getValoresColunas().getValorArray(y).equals("")) {
						relatorioPlanos
								.getLinhaPlanosArray(i)
								.getValoresColunas()
								.setValorArray(y,
										calcularPorcentagem(relatorioPlanos.getLinhaPlanosArray(i).getValoresColunas().getValorArray(y - 1), relatorioPlanos.getLinhaPlanosArray(i).getQtdAdeq()));
					}
				}
			}

		}
	}

	/**
	 * Calcula porcentagem do primeiro paramentro 'valor' referente ao segundo
	 * parametro 'total'
	 * 
	 * @param valor
	 *            String
	 * @param total
	 *            String
	 */
	private String calcularPorcentagem(String valor, String total) {

		if (valor.equals(ConstantesCRM.SZERO) || total.equals(ConstantesCRM.SZERO)) {
			return "0.00%";
		} else {

			DecimalFormatSymbols dSymbols = new DecimalFormatSymbols();
			dSymbols.setDecimalSeparator('.');

			DecimalFormat dFormat = new DecimalFormat("00", dSymbols);

			dFormat.isDecimalSeparatorAlwaysShown();
			dFormat.applyPattern("##0.00;(##0.00)");

			return dFormat.format(new Float((Float.parseFloat(valor) * 100) / Float.parseFloat(total))) + "%";
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/index.jsp"
	 */
	protected ActionForward sair(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)

	{
		FormDadosPesquisa form = (FormDadosPesquisa) formParam;

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public FormDadosPesquisa getFormDadosPesquisa() {
		return this.formDadosPesquisa;
	}

	public FormRelatorioRetencao getFormRelatorioPesquisa() {
		return this.formRelatorioRetencao;
	}

	public FormTodasOfertas getFormTodasOfertas() {
		return this.formTodasOfertas;
	}

	public FormRelaTotMensal getFormRelaTotMensal() {
		return this.formRelaTotMensal;
	}

	public FormLigacaoRetencao getFormLigacaoRetencao() {
		return this.formLigacaoRetencao;
	}

	public FormOperador getFormOperador() {
		return this.formOperador;
	}

	public FormRelatorioDistDest getFormRelatorioDistDest() {
		return this.formRelatorioDistDest;
	}

	public FormResultadoDestino getFormResultadoDestino() {
		return this.formResultadoDestino;
	}

	public FormMovimenDiarias getFormMovimenDiarias() {
		return this.formMovimenDiarias;
	}

	public FormOferta getFormOferta() {
		return this.formOferta;
	}

	public FormReteDistincao getFormReteDistincao() {
		return this.formReteDistincao;
	}

	public FormRelaPlanos getFormRelaPlanos() {
		return this.formRelaPlanos;
	}

	public FormRelaPlanosDist getFormRelaPlanosDist() {
		return this.formRelaPlanosDist;
	}

	public FormRelaCtaCte getFormRelaCtaCte() {
		return this.formRelaCtaCte;
	}

	public FormNotesLoja getFormNotesLoja() {
		return this.formNotesLoja;
	}

	public FormAdimplencia getFormAdimplencia() {
		return this.formAdimplencia;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="begin" path="begin.do"
	 */
	protected ActionForward gerarArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		StringBuffer relStrBuffer = null;
		int i;

		if (request.getParameter("idRelatorio") == null) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("begin");
		}

		try {
			if (request.getParameter("idRelatorio").equals(ConstantesCRM.SONE)) // RETENCAO
			{

				// -formRelatorioRetencao.setLinhaRetencao(relatorioRetencaoVO.getLinhaRetencaoArray());
				relStrBuffer = new StringBuffer();

				LinhaRetencao[] itens = formRelatorioRetencao.getLinhaRetencao();

				relStrBuffer.append(new String("Intenção do Cancelamento"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Intenções"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Intenções"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Destino Previsto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Retenção"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Retenção"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Migração"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Migração"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Cancelado"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Cancelado"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Oferta"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Oferta"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Suspensão"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Suspensão"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Adequação de Plano"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Adequação de Plano"));
				relStrBuffer.append("\n");

				if (itens != null) {
					for (i = 0; i < itens.length; i++) {
						relStrBuffer.append(converteNulo(itens[i].getIntCancelamiento()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdintencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcintencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getDestinoprevisto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdretencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcretencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdmigracao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcmigracao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdcancelado()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorccancelado()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdoferta()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcoferta()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdsuspensao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcsuspensao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdadequaplano()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcadequaplano()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append("\n");
					}
				}
			} else if (request.getParameter("idRelatorio").equals(ConstantesCRM.SNINE)) {

				// -formLigacaoRetencao.setLinhaLigacao(relatorio.getLinhasLigacaoArray());
				relStrBuffer = new StringBuffer();

				LinhasLigacao[] itens = formLigacaoRetencao.getLinhaLigacao();
				relStrBuffer.append(new String("Resultado"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Ocorrência"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Porcentagem"));
				relStrBuffer.append("\n");
				if (itens != null) {
					for (i = 0; i < itens.length; i++) {
						relStrBuffer.append(converteNulo(itens[i].getResultado()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getOcurrencia()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPortecta()));
						relStrBuffer.append("\n");
					}
				}

			} else if (request.getParameter("idRelatorio").equals(ConstantesCRM.SEIGHT)) {

				// -formOperador.setLinhaOperador(relatorio.getLinhaOperadorArray());
				relStrBuffer = new StringBuffer();

				LinhaOperador[] itens = formOperador.getLinhaOperador();
				relStrBuffer.append(new String("Operador"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Soma"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Renções"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Migração"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Cancelado"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Oferta"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Suspensão"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Adequaçã de Plano"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Percentual"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Resultado"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Quantidade"));
				relStrBuffer.append("\n");

				if (itens != null) {
					for (i = 0; i < itens.length; i++) {
						relStrBuffer.append(converteNulo(itens[i].getOperador()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getSoma()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcretencoes()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcmigracao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorccancelado()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcoferta()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcsuspensao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcadequacaoplano()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPercentual()));
						relStrBuffer.append(SEPARADOR_CSV);
						if (itens[i].getResultado() != null) {
							for (int r = 0; r < itens[i].getResultado().getDescricaoArray().length; r++) {
								if (r > 0) {
									relStrBuffer.append("\n");
									relStrBuffer.append(SEPARADOR_CSV);
									relStrBuffer.append(SEPARADOR_CSV);
									relStrBuffer.append(SEPARADOR_CSV);
									relStrBuffer.append(SEPARADOR_CSV);
									relStrBuffer.append(SEPARADOR_CSV);
									relStrBuffer.append(SEPARADOR_CSV);
									relStrBuffer.append(SEPARADOR_CSV);
									relStrBuffer.append(SEPARADOR_CSV);
									relStrBuffer.append(SEPARADOR_CSV);
								}
								relStrBuffer.append(converteNulo(itens[i].getResultado().getDescricaoArray(r)));
								relStrBuffer.append(SEPARADOR_CSV);
								relStrBuffer.append(converteNulo(itens[i].getResultado().getQuantidadeArray(r)));
							}
						}
						relStrBuffer.append("\n");
					}
				}
			} else if (request.getParameter("idRelatorio").equals(ConstantesCRM.STWO)) {

				// --formMovimenDiarias.setLinhaMovimen(relatorio.getLinhaMovimenArray());
				relStrBuffer = new StringBuffer();

				LinhaMovimen[] itens = formMovimenDiarias.getLinhaMovimen();
				relStrBuffer.append(new String("Data"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Data Agendamento"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Segmentação"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Telefone"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Loja/Delivery"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Loja"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Preço de Venda"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Ordem de Venda"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Aparelho"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Percentual de Desconto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Desconto Absoluto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Forma de Pagamento"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Parcelas"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Operador"));
				relStrBuffer.append("\n");
				if (itens != null) {
					for (i = 0; i < itens.length; i++) {
						relStrBuffer.append(converteNulo(itens[i].getDataPlanilha()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getDataAgendamento()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getSegmentacao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getFone()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getLojaDellivery()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getLoja()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPrecoVenda()).replace(',', '.'));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getOrdemVenda()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getAparelho()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPercentualDesconto()).replace(',', '.'));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getDataContato()).replace(',', '.'));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getFormaPago()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getParcelas()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getOperador()));
						relStrBuffer.append("\n");
					}
				}

			} else if (request.getParameter("idRelatorio").equals(ConstantesCRM.SFIVE)) {
				relStrBuffer = new StringBuffer();

				LinhaOferta[] itens = formOferta.getLinhaOferta();
				relStrBuffer.append(new String("Intenção do Cancelamento"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Intenções"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Intenções"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Destino Previsto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Ofertas"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Ofertas"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String(getFormDadosPesquisa().getDsOferta()));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% " + getFormDadosPesquisa().getDsOferta()));
				relStrBuffer.append("\n");
				if (itens != null) {
					for (i = 0; i < itens.length; i++) {
						relStrBuffer.append(converteNulo(itens[i].getIntCancelamiento()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdintencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcintencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getDestinoprevisto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdoferta()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcoferta()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getOfertaFiltro()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcOfertaFiltro()));
						relStrBuffer.append("\n");
					}
				}
			} else if (request.getParameter("idRelatorio").equals("10")) {
				relStrBuffer = new StringBuffer();

				LinhaRetencaoDistincao[] itens = formReteDistincao.getLinhaRetencaoDistincao();
				relStrBuffer.append(new String("Intenção do Cancelamento"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Intenções"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Intenções"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Destino Previsto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Destino Previsto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Destino Previsto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd No Retido"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% No Retido"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Retido"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Porc Retido"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Agendou"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Porc Agendou"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Ligacão Indevida"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Ligacões Indevida"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Vai Pensar"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Vai Pensar"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Oferta"));
				relStrBuffer.append("\n");

				if (itens != null) {
					for (i = 0; i < itens.length; i++) {
						relStrBuffer.append(converteNulo(itens[i].getIntCancelamiento()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdintencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcintencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getDestinoprevisto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtddestinoprevisto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcdestinoprevisto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdnoretido()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcnoretido()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdretido()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcretido()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdagendou()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcagendou()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdligindevida()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcligindevida()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdvaipensar()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcvaipensar()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdoferta()));
						relStrBuffer.append("\n");
					}
				}
			} else if (request.getParameter("idRelatorio").equals(ConstantesCRM.STHREE)) {
				relStrBuffer = new StringBuffer();

				br.com.vivo.fo.fidelizacao.vo.RelatorioTodasOfertasVODocument.RelatorioTodasOfertasVO.LinhaOferta[] itens = formTodasOfertas.getLinhaOferta();
				relStrBuffer.append(new String("Intenção do Cancelamento"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Intenções"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Intenções"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Destino Previsto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Retencao"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Retencao"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Migração"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Migração"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Bônus"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Bônus"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Pontos"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Pontos"));
				relStrBuffer.append("\n");
				if (itens != null) {
					for (i = 0; i < itens.length; i++) {
						relStrBuffer.append(converteNulo(itens[i].getIntCancelamiento()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdintencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcintencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getDestinoprevisto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdretencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcretencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdmigracao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcmigracao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdbonus()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcbonus()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdpronto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcpronto()));
						relStrBuffer.append("\n");

					}
				}
			} else if (request.getParameter("idRelatorio").equals(ConstantesCRM.SFOUR) || request.getParameter("idRelatorio").equals(ConstantesCRM.SSIX)) {
				relStrBuffer = new StringBuffer();
				br.com.vivo.fo.fidelizacao.vo.RelatorioResDestinoVODocument.RelatorioResDestinoVO.LinhaDestino[] itens;
				relStrBuffer.append(new String("Destino Previsto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Total"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Total"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Retencão"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Retencão"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Migracao"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Migracao"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Cancelado"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Cancelado"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Aparelhos"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Aparelhos"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Suspensao"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Suspensao"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Adequação Plano"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Adequação Plano"));
				relStrBuffer.append("\n");
				if (request.getParameter("idRelatorio").equals("4")) {
					itens = formResultadoDestino.getLinhaDestino();
				} else {
					itens = formRelatorioDistDest.getLinhaDestino();
				}

				if (itens != null) {
					for (i = 0; i < itens.length; i++) {
						relStrBuffer.append(converteNulo(itens[i].getDestinoprevisto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtddestinp()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorctotal()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdretencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcretencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdmigracao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcmigracao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdcancelado()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorccancelado()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcoferta()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcbonus()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdsuspensaoTemp()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcsuspensaoTemp()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdadequacao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcadequacao()));
						relStrBuffer.append("\n");
					}
				}
			} else if (request.getParameter("idRelatorio").equals("13")) {
				relStrBuffer = new StringBuffer();
				br.com.vivo.fo.fidelizacao.vo.RelaOfertasTotMensalVODocument.RelaOfertasTotMensalVO.LinhaMensal[] itens;
				relStrBuffer.append(new String("Cliente"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Situação"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Desconto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Val Desconto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Bonus"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Valor Bonus"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Migracao"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Valor Migracao"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Ponto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Valor Ponto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Geral"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Valor Geral"));
				relStrBuffer.append("\n");
				itens = formRelaTotMensal.getLinhaMensal();

				if (itens != null) {
					for (i = 0; i < itens.length; i++) {
						relStrBuffer.append(converteNulo(itens[i].getCliente()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getSituacao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdDesconto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getValDesconto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdBonus()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getValBonus()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdMigracao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getValMigracao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdPonto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getValPonto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdGeral()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getValGeral()));
						relStrBuffer.append("\n");
					}
				}
			} else if (request.getParameter("idRelatorio").equals("11")) {
				relStrBuffer = new StringBuffer();
				br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosVODocument.RelatorioRetePlanosVO.LinhaPlanos[] itens;
				relStrBuffer.append(new String("Intenção Cancelamento"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Intencões"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Intencões"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Destino Previsto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Adequação de Plano"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Adequação de Plano"));
				if (getRelatorioRetePlanos().getColunas() != null) {
					for (i = 0; i < getRelatorioRetePlanos().getColunas().getNomeArray().length; i++) {
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(getRelatorioRetePlanos().getColunas().getNomeArray(i).toString());
					}
				}
				relStrBuffer.append("\n");
				itens = formRelaPlanos.getLinhaPlanos();

				if (itens != null) {
					for (i = 0; i < itens.length; i++) {
						relStrBuffer.append(converteNulo(itens[i].getIntCancelamento()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdIntencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPercIntencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getDestinoPrevisto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdAdeq()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPercAdeq()));
						if (getRelatorioRetePlanos().getColunas() != null) {
							for (int v = 0; v < itens[i].getValoresColunas().getValorArray().length; v++) {
								relStrBuffer.append(SEPARADOR_CSV);
								relStrBuffer.append(converteNulo(itens[i].getValoresColunas().getValorArray(v).toString()));
							}
						}
						// relStrBuffer.append(itens[i].getPorcadequacao());
						// relStrBuffer.append(SEPARADOR_CSV);
						// relStrBuffer.append(itens[i].getPlano());
						// relStrBuffer.append(SEPARADOR_CSV);
						// relStrBuffer.append(itens[i].getPorcplano());
						relStrBuffer.append("\n");
					}
				}
			} else if (request.getParameter("idRelatorio").equals("12")) {
				relStrBuffer = new StringBuffer();
				br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosDistincaoVODocument.RelatorioRetePlanosDistincaoVO.LinhaPlanosDistincao[] itens;
				relStrBuffer.append(new String("Intenção do Cancelamento"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Intencões"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Intencões"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Destino Previsto"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Adequação de Plano"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("% Adequação de Plano"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Desc Plano"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Desc2 Plano"));
				relStrBuffer.append("\n");
				itens = formRelaPlanosDist.getLinhaPlanosDistincao();

				if (itens != null) {
					for (i = 0; i < itens.length; i++) {
						relStrBuffer.append(converteNulo(itens[i].getIntCancelamento()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdintencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcintencao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getDestinoprevisto()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdadequacao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcadequacao()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPlano()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPorcplano()));
						relStrBuffer.append("\n");
					}
				}
			}

			else if (request.getParameter("idRelatorio").equals(ConstantesCRM.SSEVEN)) {
				relStrBuffer = new StringBuffer();
				br.com.vivo.fo.fidelizacao.vo.RelatorioCCContaCorrenteVODocument.RelatorioCCContaCorrenteVO.LinhaCCContaCorrente[] itens;
				relStrBuffer.append(new String("Data"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Cliente"));
				relStrBuffer.append(SEPARADOR_CSV);
				// relStrBuffer.append(new String("Area"));
				// relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Linha"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Bonus"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Bonus Total"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Qtd Parc"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Pessoa"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Segmentação"));
				relStrBuffer.append("\n");

				itens = formRelaCtaCte.getLinhaCCContaCorrente();

				if (itens != null) {
					for (i = 0; i < itens.length; i++) {
						relStrBuffer.append(converteNulo(itens[i].getData()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getCliente()));
						relStrBuffer.append(SEPARADOR_CSV);
						// relStrBuffer.append(itens[i].getArea());
						// relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getLinha()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getBonus()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(itens[i].getBonusTotal().replace(',', '.'));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getQtdParc()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPessoa()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getClassificacao()));
						relStrBuffer.append("\n");
					}
				}
			}

			else if (request.getParameter("idRelatorio").equals("15")) {
				relStrBuffer = new StringBuffer();
				br.com.vivo.fo.fidelizacao.vo.RelatorioNotesLojaVODocument.RelatorioNotesLojaVO.LinhaNotesLoja[] itens;
				relStrBuffer.append(new String("Data"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Cliente"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Linha"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Agendamento"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Loja"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Aparelho"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Preço Venda"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Desc"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Preço Final"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Nº Vezes"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Env Por"));
				relStrBuffer.append(SEPARADOR_CSV);
				relStrBuffer.append(new String("Segmentação"));
				relStrBuffer.append("\n");
				itens = formNotesLoja.getLinhaNotesLoja();

				if (itens != null) {
					for (i = 0; i < itens.length; i++) {
						relStrBuffer.append(converteNulo(itens[i].getData()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getCliente()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getLinha()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getAgendamento()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getLoja()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getAparelho()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPrecoVenda().replace(',', '.')));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getDesc()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getPrecoFinal().replace(',', '.')));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getNroVezes()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getEnvPor()));
						relStrBuffer.append(SEPARADOR_CSV);
						relStrBuffer.append(converteNulo(itens[i].getClassificacao()));
						relStrBuffer.append("\n");
					}
				}
			}

			if (relStrBuffer != null && relStrBuffer.toString().length() > 0) {
				response.addHeader("Content-Disposition", "attachment; filename=relatorioGeren.csv");
				response.addHeader("Content-Type", "application/force-download; charset=ISO-8859-1");
				response.addHeader("Content-Transfer-Encoding", "binary");
				response.addHeader("Pragma", "no-cache");
				response.addHeader("Expires", "0");

				PrintWriter out = response.getWriter();
				out.println(relStrBuffer.toString());
				out.flush();
				out.close();
				return null;
			}
		} catch (IOException e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("begin");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iFrameCarrega.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward carregarPlanos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		FormDadosPesquisa form = (FormDadosPesquisa) formParam;
		try {
			PlanoPesquisaVO planoPesquisaVO = PlanoPesquisaVO.Factory.newInstance();
			// planoPesquisaVO.setIdufop(form.getOperadora());
			planoPesquisaVO.setTexto("");
			formDadosPesquisa.setPlanoVO(manterPlanosFac.getPlano(user, planoPesquisaVO).getPlanoVOArray());
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class FormDadosPesquisa extends ActionForm {

		private static final long serialVersionUID = -8484157355686786435L;

		private String msgErro;
		private String plano;
		private String dsOperador;
		private String dsOferta = ConstantesCRM.SVAZIO;
		private String[] dsOperadora;
		private String dsTipoCliente = ConstantesCRM.SVAZIO;
		// private String dsOperador = ConstantesCRM.SVAZIO;
		private String dsClassificacao = ConstantesCRM.SVAZIO;
		private String dsGrupo = ConstantesCRM.SVAZIO;
		// private boolean temValData;
		// private String valData;
		private String operador;
		private String dataInicio;
		private String dataFim;
		private String periodoInicio;
		private String periodoFim;
		private ItemListaVO[] operadoraVO;
		private PlanoVO[] planoVO;
		private OfertaFidelizacaoVO[] ofertaFidelizacaoVO;
		private FidelizacaoGrupoVO[] fidelizacaoGrupoVO;
		private String[] operadora;
		private String tipoCliente;
		private String oferta;
		private String custoPonto;
		private String grupo;
		private String classificacao;
		private String periodoFin;
		private String periodoInic;
		private String idRelatorio;
		private ClassificacaoVO[] classificacaoVO;
		private FidelizacaoTipoClienteVO[] fidelizacaoTipoClienteVO;
		private br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoInputVODocument.RelatorioRetencaoInputVO dados;

		public void setClassificacaoVO(ClassificacaoVO[] classificacaoVO) {
			this.classificacaoVO = classificacaoVO;
		}

		public ClassificacaoVO[] getClassificacaoVO() {
			return this.classificacaoVO;
		}

		public void setDados(br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoInputVODocument.RelatorioRetencaoInputVO dados) {
			this.dados = dados;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoInputVODocument.RelatorioRetencaoInputVO getDados() {
			return this.dados;
		}

		public void setIdRelatorio(String idRelatorio) {
			this.idRelatorio = idRelatorio;
		}

		public String getIdRelatorio() {
			return this.idRelatorio;
		}

		public void setClassificacao(String classificacao) {
			this.classificacao = classificacao;
		}

		public String getClassificacao() {
			return this.classificacao;
		}

		public void setGrupo(String grupo) {
			this.grupo = grupo;
		}

		public String getGrupo() {
			return this.grupo;
		}

		public void setCustoPonto(String custoPonto) {
			this.custoPonto = custoPonto;
		}

		public String getCustoPonto() {
			return this.custoPonto;
		}

		public void setOferta(String oferta) {
			this.oferta = oferta;
		}

		public String getOferta() {
			return this.oferta;
		}

		public void setTipoCliente(String tipoCliente) {
			this.tipoCliente = tipoCliente;
		}

		public String getTipoCliente() {
			return this.tipoCliente;
		}

		public void setOperadora(String[] operadora) {
			this.operadora = operadora;
		}

		public String[] getOperadora() {
			return this.operadora;
		}

		public void setFidelizacaoTipoClienteVO(FidelizacaoTipoClienteVO[] fidelizacaoTipoClienteVO) {
			this.fidelizacaoTipoClienteVO = fidelizacaoTipoClienteVO;
		}

		public FidelizacaoTipoClienteVO[] getFidelizacaoTipoClienteVO() {
			return this.fidelizacaoTipoClienteVO;
		}

		public void setFidelizacaoGrupoVO(FidelizacaoGrupoVO[] fidelizacaoGrupoVO) {
			this.fidelizacaoGrupoVO = fidelizacaoGrupoVO;
		}

		public FidelizacaoGrupoVO[] getFidelizacaoGrupoVO() {
			return this.fidelizacaoGrupoVO;
		}

		public void setOfertaFidelizacaoVO(OfertaFidelizacaoVO[] ofertaFidelizacaoVO) {
			this.ofertaFidelizacaoVO = ofertaFidelizacaoVO;
		}

		public OfertaFidelizacaoVO[] getOfertaFidelizacaoVO() {
			return this.ofertaFidelizacaoVO;
		}

		public void setPlanoVO(PlanoVO[] planoVO) {
			this.planoVO = planoVO;
		}

		public PlanoVO[] getPlanoVO() {
			return this.planoVO;
		}

		public void setOperadoraVO(ItemListaVO[] operadoraVO) {
			this.operadoraVO = operadoraVO;
		}

		public ItemListaVO[] getOperadoraVO() {
			return this.operadoraVO;

		}

		public void setDataFim(String dataFim) {
			this.dataFim = dataFim;
		}

		public String getDataFim() {
			return this.dataFim;
		}

		public void setDataInicio(String dataInicio) {
			this.dataInicio = dataInicio;
		}

		public String getDataInicio() {
			return this.dataInicio;
		}

		public void setOperador(String operador) {
			this.operador = operador;
		}

		public String getOperador() {
			return this.operador;
		}

		/*
		 * public void setValData(String valData) { this.valData = valData; }
		 * 
		 * public String getValData() { return this.valData; }
		 * 
		 * public void setTemValData(boolean temValData) { this.temValData =
		 * temValData; }
		 * 
		 * public boolean isTemValData() { return this.temValData; }
		 */

		public void setDsClassificacao(String dsClassificacao) {
			this.dsClassificacao = dsClassificacao;
		}

		public String getDsClassificacao() {
			return this.dsClassificacao;
		}

		public void setDsGrupo(String dsGrupo) {
			this.dsGrupo = dsGrupo;
		}

		public String getDsGrupo() {
			return this.dsGrupo;
		}

		public void setDsTipoCliente(String dsTipoCliente) {
			this.dsTipoCliente = dsTipoCliente;
		}

		public String getDsTipoCliente() {
			return this.dsTipoCliente;
		}

		public void setDsOperadora(String[] dsOperadora) {
			this.dsOperadora = dsOperadora;
		}

		public String[] getDsOperadora() {
			return this.dsOperadora;
		}

		public void setDsOferta(String dsOferta) {
			this.dsOferta = dsOferta;
		}

		public String getDsOferta() {
			return this.dsOferta;
		}

		public void setPlano(String plano) {
			this.plano = plano;
		}

		public String getPlano() {
			return this.plano;
		}

		public void setMsgErro(String msgErro) {
			this.msgErro = msgErro;
		}

		public String getMsgErro() {
			return this.msgErro;
		}
	}

	public static class FormRelatorioRetencao extends ActionForm {
		private static final long serialVersionUID = -1915995459903931349L;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoVODocument.RelatorioRetencaoVO.LinhaRetencao[] linhaRetencao;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoVODocument.RelatorioRetencaoVO dados;

		public void setLinhaRetencao(br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoVODocument.RelatorioRetencaoVO.LinhaRetencao[] linhaRetencao) {
			this.linhaRetencao = linhaRetencao;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoVODocument.RelatorioRetencaoVO.LinhaRetencao[] getLinhaRetencao() {
			return this.linhaRetencao;
		}
	}

	public static class FormLigacaoRetencao extends ActionForm {

		private static final long serialVersionUID = -5792150196192509729L;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioLigacaoRetencaoVODocument.RelatorioLigacaoRetencaoVO.LinhasLigacao[] linhaLigacao;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioLigacaoRetencaoVODocument.RelatorioLigacaoRetencaoVO.LinhasLigacao[] linhaRetencao;

		public void setLinhaLigacao(br.com.vivo.fo.fidelizacao.vo.RelatorioLigacaoRetencaoVODocument.RelatorioLigacaoRetencaoVO.LinhasLigacao[] linhaLigacao) {
			this.linhaLigacao = linhaLigacao;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelatorioLigacaoRetencaoVODocument.RelatorioLigacaoRetencaoVO.LinhasLigacao[] getLinhaLigacao() {
			return this.linhaLigacao;
		}
	}

	public static class FormOperador extends ActionForm {

		private static final long serialVersionUID = -7498785727995109319L;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioOperadorVODocument.RelatorioOperadorVO.LinhaOperador[] linhaOperador;

		public void setLinhaOperador(br.com.vivo.fo.fidelizacao.vo.RelatorioOperadorVODocument.RelatorioOperadorVO.LinhaOperador[] linhaOperador) {
			this.linhaOperador = linhaOperador;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelatorioOperadorVODocument.RelatorioOperadorVO.LinhaOperador[] getLinhaOperador() {
			return this.linhaOperador;
		}
	}

	public static class FormMovimenDiarias extends ActionForm {

		private static final long serialVersionUID = -5684295016301228728L;

		private br.com.vivo.fo.fidelizacao.vo.RelaMovimenDiariasVODocument.RelaMovimenDiariasVO.LinhaMovimen[] linhaMovimen;

		private String a;

		public void setLinhaMovimen(br.com.vivo.fo.fidelizacao.vo.RelaMovimenDiariasVODocument.RelaMovimenDiariasVO.LinhaMovimen[] linhaMovimen) {
			this.linhaMovimen = linhaMovimen;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelaMovimenDiariasVODocument.RelaMovimenDiariasVO.LinhaMovimen[] getLinhaMovimen() {
			return this.linhaMovimen;
		}
	}

	public static class FormOferta extends ActionForm {

		private static final long serialVersionUID = 9079761690923072716L;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioReteOfertaVODocument.RelatorioReteOfertaVO.LinhaOferta[] linhaOferta;

		public void setLinhaOferta(br.com.vivo.fo.fidelizacao.vo.RelatorioReteOfertaVODocument.RelatorioReteOfertaVO.LinhaOferta[] linhaOferta) {
			this.linhaOferta = linhaOferta;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelatorioReteOfertaVODocument.RelatorioReteOfertaVO.LinhaOferta[] getLinhaOferta() {
			return this.linhaOferta;
		}
	}

	public static class FormReteDistincao extends ActionForm {

		private static final long serialVersionUID = 5768018159098877596L;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoDistincaoVODocument.RelatorioRetencaoDistincaoVO.LinhaRetencaoDistincao[] linhaRetencaoDistincao;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoDistincaoVODocument.RelatorioRetencaoDistincaoVO.LinhaRetencaoDistincao[] linhaDistincao;

		public void setLinhaRetencaoDistincao(br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoDistincaoVODocument.RelatorioRetencaoDistincaoVO.LinhaRetencaoDistincao[] linhaRetencaoDistincao) {
			this.linhaRetencaoDistincao = linhaRetencaoDistincao;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoDistincaoVODocument.RelatorioRetencaoDistincaoVO.LinhaRetencaoDistincao[] getLinhaRetencaoDistincao() {
			return this.linhaRetencaoDistincao;
		}
	}

	public static class FormTodasOfertas extends ActionForm {
		private static final long serialVersionUID = 4220103361705981994L;
		private br.com.vivo.fo.fidelizacao.vo.RelatorioTodasOfertasVODocument.RelatorioTodasOfertasVO.LinhaOferta[] linhaOferta;

		public void setLinhaOferta(br.com.vivo.fo.fidelizacao.vo.RelatorioTodasOfertasVODocument.RelatorioTodasOfertasVO.LinhaOferta[] linhaOferta) {
			this.linhaOferta = linhaOferta;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelatorioTodasOfertasVODocument.RelatorioTodasOfertasVO.LinhaOferta[] getLinhaOferta() {
			return this.linhaOferta;
		}
	}

	public static class FormResultadoDestino extends ActionForm {

		private static final long serialVersionUID = 502521689349496517L;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioResDestinoVODocument.RelatorioResDestinoVO.LinhaDestino[] linhaDestino;

		public void setLinhaDestino(br.com.vivo.fo.fidelizacao.vo.RelatorioResDestinoVODocument.RelatorioResDestinoVO.LinhaDestino[] linhaDestino) {
			this.linhaDestino = linhaDestino;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelatorioResDestinoVODocument.RelatorioResDestinoVO.LinhaDestino[] getLinhaDestino() {
			return this.linhaDestino;
		}
	}

	public static class FormRelatorioDistDest extends ActionForm {

		private static final long serialVersionUID = 3586767930321934298L;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioResDestinoVODocument.RelatorioResDestinoVO.LinhaDestino[] linhaDestino;
		private br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoDistincaoVODocument.RelatorioRetencaoDistincaoVO.LinhaRetencaoDistincao[] linhaRetencaoDistincao;

		public void setLinhaDestino(br.com.vivo.fo.fidelizacao.vo.RelatorioResDestinoVODocument.RelatorioResDestinoVO.LinhaDestino[] linhaDestino) {
			this.linhaDestino = linhaDestino;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelatorioResDestinoVODocument.RelatorioResDestinoVO.LinhaDestino[] getLinhaDestino() {
			return this.linhaDestino;
		}
	}

	public static class FormRelaTotMensal extends ActionForm {

		private static final long serialVersionUID = -8381635864410161327L;

		private LinhaMensal[] linhaMensal;

		public void setLinhaMensal(LinhaMensal[] linhaMensal) {
			this.linhaMensal = linhaMensal;
		}

		public LinhaMensal[] getLinhaMensal() {
			return this.linhaMensal;
		}
	}

	public static class FormRelaPlanos extends ActionForm {

		private static final long serialVersionUID = -7870982333044191505L;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosVODocument.RelatorioRetePlanosVO.LinhaPlanos[] linhaPlanos;

		public void setLinhaPlanos(br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosVODocument.RelatorioRetePlanosVO.LinhaPlanos[] linhaPlanos) {
			this.linhaPlanos = linhaPlanos;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosVODocument.RelatorioRetePlanosVO.LinhaPlanos[] getLinhaPlanos() {
			return this.linhaPlanos;
		}
	}

	public static class FormRelaPlanosDist extends ActionForm {

		private static final long serialVersionUID = -3347312981306013491L;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosDistincaoVODocument.RelatorioRetePlanosDistincaoVO.LinhaPlanosDistincao[] linhaPlanosDistincao;

		public void setLinhaPlanosDistincao(br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosDistincaoVODocument.RelatorioRetePlanosDistincaoVO.LinhaPlanosDistincao[] linhaPlanosDistincao) {
			this.linhaPlanosDistincao = linhaPlanosDistincao;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosDistincaoVODocument.RelatorioRetePlanosDistincaoVO.LinhaPlanosDistincao[] getLinhaPlanosDistincao() {
			return this.linhaPlanosDistincao;
		}
	}

	public static class FormRelaCtaCte extends ActionForm {

		private static final long serialVersionUID = 1677086602531097264L;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioCCContaCorrenteVODocument.RelatorioCCContaCorrenteVO.LinhaCCContaCorrente[] linhaCCContaCorrente;

		public void setLinhaCCContaCorrente(br.com.vivo.fo.fidelizacao.vo.RelatorioCCContaCorrenteVODocument.RelatorioCCContaCorrenteVO.LinhaCCContaCorrente[] linhaCCContaCorrente) {
			this.linhaCCContaCorrente = linhaCCContaCorrente;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelatorioCCContaCorrenteVODocument.RelatorioCCContaCorrenteVO.LinhaCCContaCorrente[] getLinhaCCContaCorrente() {
			return this.linhaCCContaCorrente;
		}
	}

	public static class FormNotesLoja extends ActionForm {

		private static final long serialVersionUID = -8264233452326503531L;

		private br.com.vivo.fo.fidelizacao.vo.RelatorioNotesLojaVODocument.RelatorioNotesLojaVO.LinhaNotesLoja[] linhaNotesLoja;

		public void setLinhaNotesLoja(br.com.vivo.fo.fidelizacao.vo.RelatorioNotesLojaVODocument.RelatorioNotesLojaVO.LinhaNotesLoja[] linhaNotesLoja) {
			this.linhaNotesLoja = linhaNotesLoja;
		}

		public br.com.vivo.fo.fidelizacao.vo.RelatorioNotesLojaVODocument.RelatorioNotesLojaVO.LinhaNotesLoja[] getLinhaNotesLoja() {
			return this.linhaNotesLoja;
		}
	}

	private int dividir(int valor, int divisor) {

		return valor <= divisor ? 1 : valor % divisor == 0 ? valor / divisor : (valor / divisor) + 1;
	}

	private final String TABLE = "<table cellpadding='0' cellspacing='0'  style='border-collapse: collapse;border: 1px solid #F5F5F5;' width='650'>";
	private final String TD_HEADER = "<tr><td bgcolor='#545454' align='center' style='border: 1px solid #D4D7DE;padding-top: 2px;padding-bottom: 2px;padding-left: 5px;padding-right: 5px;'><b><font color='white' size='1'>";
	private final String TD_DADOS = "<td style='background-color: #ffffff;border: 1px solid #F5F5F5;padding-top: 2px;padding-bottom: 2px;padding-left: 5px;padding-right: 5px;'>";
	private final String TABLE_FECHA = "</table>";
	private final String TR_FECHA = "</tr>";
	private final String TD_FECHA = "</td>";

	private final String TR_SEPARA = "<tr heigth='20'><td colspan=15>&nbsp;</td></tr>";

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printDestino(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getRelatorioDestino().getLinhaDestinoArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[15];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Destino Previsto</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Qtd Total</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("% Total</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("Qtd Retencão</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("% Retencão</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("Qtd Migracao</font></b></td>");
			dadosRelatorio[6].append(TD_HEADER).append("% Migracao</font></b></td>");
			dadosRelatorio[7].append(TD_HEADER).append("Qtd Cancelado</font></b></td>");
			dadosRelatorio[8].append(TD_HEADER).append("% Cancelado</font></b></td>");
			dadosRelatorio[9].append(TD_HEADER).append("Qtd Aparelhos</font></b></td>");
			dadosRelatorio[10].append(TD_HEADER).append("% Aparelhos</font></b></td>");
			dadosRelatorio[11].append(TD_HEADER).append("Qtd Suspensao</font></b></td>");
			dadosRelatorio[12].append(TD_HEADER).append("% Suspensao</b></td>");
			dadosRelatorio[13].append(TD_HEADER).append("Qtd Adequação Plano</font></b></td>");
			dadosRelatorio[14].append(TD_HEADER).append("% Adequação Plano</font></b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRelatorioDestino().getLinhaDestinoArray().length; dR++) {
				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getDestinoprevisto())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getQtddestinp())).append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getPorctotal())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getQtdretencao())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getPorcretencao())).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getQtdmigracao())).append(TD_FECHA);
				dadosRelatorio[6].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getPorcmigracao())).append(TD_FECHA);
				dadosRelatorio[7].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getQtdcancelado())).append(TD_FECHA);
				dadosRelatorio[8].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getPorccancelado())).append(TD_FECHA);
				dadosRelatorio[9].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getPorcoferta())).append(TD_FECHA);
				dadosRelatorio[10].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getPorcbonus())).append(TD_FECHA);
				dadosRelatorio[11].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getQtdsuspensaoTemp())).append(TD_FECHA);
				dadosRelatorio[12].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getPorcsuspensaoTemp())).append(TD_FECHA);
				dadosRelatorio[13].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getQtdadequacao())).append(TD_FECHA);
				dadosRelatorio[14].append(TD_DADOS).append(converteNulo(getRelatorioDestino().getLinhaDestinoArray(dR).getPorcadequacao())).append(TD_FECHA);
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);
		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printMDiaria(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getRelatorioMDiaria().getLinhaMovimenArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[14];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Data</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Data Agendamento</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("Telefone</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("Segmentaçao</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("Loja/Delivery</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("Loja</font></b></td>");
			dadosRelatorio[6].append(TD_HEADER).append("Preco de Venda</font></b></td>");
			dadosRelatorio[7].append(TD_HEADER).append("Ordem de Venda</font></b></td>");
			dadosRelatorio[8].append(TD_HEADER).append("Aparelho</font></b></td>");
			dadosRelatorio[9].append(TD_HEADER).append("Percentual de Desconto</font></b></td>");
			dadosRelatorio[10].append(TD_HEADER).append("Desconto Absoluto</font></b></td>");
			dadosRelatorio[11].append(TD_HEADER).append("Forma de Pagamento</font></b></td>");
			dadosRelatorio[12].append(TD_HEADER).append("Parcelas</font></b></td>");
			// dadosRelatorio[13].append(TD_HEADER).append("Data Contato</font></b></td>");
			// dadosRelatorio[14].append(TD_HEADER).append("Operador</b></td>");
			dadosRelatorio[13].append(TD_HEADER).append("Operador</b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRelatorioMDiaria().getLinhaMovimenArray().length; dR++) {
				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getDataPlanilha())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getDataAgendamento())).append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getFone())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getSegmentacao())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getLojaDellivery())).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getLoja())).append(TD_FECHA);
				dadosRelatorio[6].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getPrecoVenda())).append(TD_FECHA);
				dadosRelatorio[7].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getOrdemVenda())).append(TD_FECHA);
				dadosRelatorio[8].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getAparelho())).append(TD_FECHA);
				dadosRelatorio[9].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getPercentualDesconto())).append(TD_FECHA);
				dadosRelatorio[10].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getDataContato())).append(TD_FECHA);
				dadosRelatorio[11].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getFormaPago())).append(TD_FECHA);
				dadosRelatorio[12].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getParcelas())).append(TD_FECHA);
				// dadosRelatorio[13].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getDataContato()))
				// .append(TD_FECHA);
				// dadosRelatorio[14].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getOperador()))
				// .append(TD_FECHA);
				dadosRelatorio[13].append(TD_DADOS).append(converteNulo(getRelatorioMDiaria().getLinhaMovimenArray(dR).getOperador())).append(TD_FECHA);
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);
		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printTOfertas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getRelatorioTOfertas().getLinhaOfertaArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[12];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Intenção do Cancelamento</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Qtd Intenções</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("% Intenções</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("Destino Previsto</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("Qtd Retencao</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("% Retencao</font></b></td>");
			dadosRelatorio[6].append(TD_HEADER).append("Qtd Migração</font></b></td>");
			dadosRelatorio[7].append(TD_HEADER).append("% Migração</font></b></td>");
			dadosRelatorio[8].append(TD_HEADER).append("Qtd Bônus</font></b></td>");
			dadosRelatorio[9].append(TD_HEADER).append("% Bônus</font></b></td>");
			dadosRelatorio[10].append(TD_HEADER).append("Qtd Pontos</font></b></td>");
			dadosRelatorio[11].append(TD_HEADER).append("% Pontos</font></b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRelatorioTOfertas().getLinhaOfertaArray().length; dR++) {

				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRelatorioTOfertas().getLinhaOfertaArray(dR).getIntCancelamiento())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRelatorioTOfertas().getLinhaOfertaArray(dR).getQtdintencao())).append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRelatorioTOfertas().getLinhaOfertaArray(dR).getPorcintencao())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getRelatorioTOfertas().getLinhaOfertaArray(dR).getDestinoprevisto())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(converteNulo(getRelatorioTOfertas().getLinhaOfertaArray(dR).getQtdretencao())).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getRelatorioTOfertas().getLinhaOfertaArray(dR).getPorcretencao())).append(TD_FECHA);
				dadosRelatorio[6].append(TD_DADOS).append(converteNulo(getRelatorioTOfertas().getLinhaOfertaArray(dR).getQtdmigracao())).append(TD_FECHA);
				dadosRelatorio[7].append(TD_DADOS).append(converteNulo(getRelatorioTOfertas().getLinhaOfertaArray(dR).getPorcmigracao())).append(TD_FECHA);
				dadosRelatorio[8].append(TD_DADOS).append(converteNulo(getRelatorioTOfertas().getLinhaOfertaArray(dR).getQtdbonus())).append(TD_FECHA);
				dadosRelatorio[9].append(TD_DADOS).append(converteNulo(getRelatorioTOfertas().getLinhaOfertaArray(dR).getPorcbonus())).append(TD_FECHA);
				dadosRelatorio[10].append(TD_DADOS).append(converteNulo(getRelatorioTOfertas().getLinhaOfertaArray(dR).getQtdpronto())).append(TD_FECHA);
				dadosRelatorio[11].append(TD_DADOS).append(converteNulo(getRelatorioTOfertas().getLinhaOfertaArray(dR).getPorcpronto())).append(TD_FECHA);
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);
		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printRetencao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getRelatorioRetencao().getLinhaRetencaoArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[16];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Intenção do Cancelamento</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Qtd Intenções</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("% Intenções</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("Destino Previsto</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("Qtd Retenção</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("% Retenção</font></b></td>");
			dadosRelatorio[6].append(TD_HEADER).append("Qtd Migração</font></b></td>");
			dadosRelatorio[7].append(TD_HEADER).append("% Migração</font></b></td>");
			dadosRelatorio[8].append(TD_HEADER).append("Qtd Cancelado</font></b></td>");
			dadosRelatorio[9].append(TD_HEADER).append("% Cancelado</font></b></td>");
			dadosRelatorio[10].append(TD_HEADER).append("Qtd Aparelhos</font></b></td>");
			dadosRelatorio[11].append(TD_HEADER).append("% Aparelhos</font></b></td>");
			dadosRelatorio[12].append(TD_HEADER).append("Qtd Suspensão</font></b></td>");
			dadosRelatorio[13].append(TD_HEADER).append("% Suspensão</font></b></td>");
			dadosRelatorio[14].append(TD_HEADER).append("Qtd Adequação de Plano</font></b></td>");
			dadosRelatorio[15].append(TD_HEADER).append("% Adequação de Plano</font></b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRelatorioRetencao().getLinhaRetencaoArray().length; dR++) {

				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getIntCancelamiento())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getQtdintencao())).append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getPorcintencao())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getDestinoprevisto())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getQtdretencao())).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getPorcretencao())).append(TD_FECHA);
				dadosRelatorio[6].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getQtdmigracao())).append(TD_FECHA);
				dadosRelatorio[7].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getPorcmigracao())).append(TD_FECHA);
				dadosRelatorio[8].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getQtdcancelado())).append(TD_FECHA);
				dadosRelatorio[9].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getPorccancelado())).append(TD_FECHA);
				dadosRelatorio[10].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getQtdoferta())).append(TD_FECHA);
				dadosRelatorio[11].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getPorcoferta())).append(TD_FECHA);
				dadosRelatorio[12].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getQtdsuspensao())).append(TD_FECHA);
				dadosRelatorio[13].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getPorcsuspensao())).append(TD_FECHA);
				dadosRelatorio[14].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getQtdadequaplano())).append(TD_FECHA);
				dadosRelatorio[15].append(TD_DADOS).append(converteNulo(getRelatorioRetencao().getLinhaRetencaoArray(dR).getPorcadequaplano())).append(TD_FECHA);
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);

		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printRetencaoOferta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getRelatorioOferta().getLinhaOfertaArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[8];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Intenção do Cancelamento</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Qtd Intenções</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("% Intenções</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("Destino Previsto</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("Ofertas</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("% Ofertas</font></b></td>");
			dadosRelatorio[6].append(TD_HEADER).append(getFormDadosPesquisa().getDsOferta() + "</font></b></td>");
			dadosRelatorio[7].append(TD_HEADER).append("% " + getFormDadosPesquisa().getDsOferta() + "</font></b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRelatorioOferta().getLinhaOfertaArray().length; dR++) {

				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRelatorioOferta().getLinhaOfertaArray(dR).getIntCancelamiento())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRelatorioOferta().getLinhaOfertaArray(dR).getQtdintencao())).append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRelatorioOferta().getLinhaOfertaArray(dR).getPorcintencao())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getRelatorioOferta().getLinhaOfertaArray(dR).getDestinoprevisto())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(converteNulo(getRelatorioOferta().getLinhaOfertaArray(dR).getQtdoferta())).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getRelatorioOferta().getLinhaOfertaArray(dR).getPorcoferta())).append(TD_FECHA);
				dadosRelatorio[6].append(TD_DADOS).append(converteNulo(getRelatorioOferta().getLinhaOfertaArray(dR).getOfertaFiltro())).append(TD_FECHA);
				dadosRelatorio[7].append(TD_DADOS).append(converteNulo(getRelatorioOferta().getLinhaOfertaArray(dR).getPorcOfertaFiltro())).append(TD_FECHA);

			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);

		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printRetencaoDistincao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[17];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Intenção do Cancelamento</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Qtd Intenções</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("% Intenções</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("Destino Previsto</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("Qtd Destino Previsto</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("% Destino Previsto</font></b></td>");
			dadosRelatorio[6].append(TD_HEADER).append("Qtd No Retido</font></b></td>");
			dadosRelatorio[7].append(TD_HEADER).append("% No Retido</font></b></td>");
			dadosRelatorio[8].append(TD_HEADER).append("Qtd Retido</font></b></td>");
			dadosRelatorio[9].append(TD_HEADER).append("% Retido</font></b></td>");
			dadosRelatorio[10].append(TD_HEADER).append("Qtd Agendou</font></b></td>");
			dadosRelatorio[11].append(TD_HEADER).append("% Agendou</font></b></td>");
			dadosRelatorio[12].append(TD_HEADER).append("Qtd Ligacão Indevida</font></b></td>");
			dadosRelatorio[13].append(TD_HEADER).append("% Ligacões Indevida</font></b></td>");
			dadosRelatorio[14].append(TD_HEADER).append("Qtd Vai Pensar</font></b></td>");
			dadosRelatorio[15].append(TD_HEADER).append("% Vai Pensar</font></b></td>");
			dadosRelatorio[16].append(TD_HEADER).append("Qtd Oferta</font></b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray().length; dR++) {

				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getIntCancelamiento())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getQtdintencao())).append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getPorcintencao())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getDestinoprevisto())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getQtddestinoprevisto())).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getPorcdestinoprevisto())).append(TD_FECHA);
				dadosRelatorio[6].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getQtdnoretido())).append(TD_FECHA);
				dadosRelatorio[7].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getPorcnoretido())).append(TD_FECHA);
				dadosRelatorio[8].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getQtdretido())).append(TD_FECHA);
				dadosRelatorio[9].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getPorcretido())).append(TD_FECHA);
				dadosRelatorio[10].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getQtdagendou())).append(TD_FECHA);
				dadosRelatorio[11].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getPorcagendou())).append(TD_FECHA);
				dadosRelatorio[12].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getQtdligindevida())).append(TD_FECHA);
				dadosRelatorio[13].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getPorcligindevida())).append(TD_FECHA);
				dadosRelatorio[14].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getQtdvaipensar())).append(TD_FECHA);
				dadosRelatorio[15].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getPorcvaipensar())).append(TD_FECHA);
				dadosRelatorio[16].append(TD_DADOS).append(converteNulo(getRelatorioRetencaoDistincao().getLinhaRetencaoDistincaoArray(dR).getQtdoferta())).append(TD_FECHA);
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);

		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printLigacaoRetencao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getRelatorioLigacaoRetencao().getLinhasLigacaoArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[3];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Resultado</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Ocorrência</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("Porcentagem</font></b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRelatorioLigacaoRetencao().getLinhasLigacaoArray().length; dR++) {

				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRelatorioLigacaoRetencao().getLinhasLigacaoArray(dR).getResultado())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRelatorioLigacaoRetencao().getLinhasLigacaoArray(dR).getOcurrencia())).append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRelatorioLigacaoRetencao().getLinhasLigacaoArray(dR).getPortecta())).append(TD_FECHA);
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);
		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printDestinoDistincao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getResDestino().getLinhaDestinoArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[15];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Destino Previsto</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Qtd Total</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("% Total</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("Qtd Retencão</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("% Retencão</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("Qtd Migracao</font></b></td>");
			dadosRelatorio[6].append(TD_HEADER).append("% Migracao</font></b></td>");
			dadosRelatorio[7].append(TD_HEADER).append("Qtd Cancelado</font></b></td>");
			dadosRelatorio[8].append(TD_HEADER).append("% Cancelado</font></b></td>");
			dadosRelatorio[9].append(TD_HEADER).append("Qtd Aparelhos</font></b></td>");
			dadosRelatorio[10].append(TD_HEADER).append("% Aparelhos</font></b></td>");
			dadosRelatorio[11].append(TD_HEADER).append("Qtd Suspensao</font></b></td>");
			dadosRelatorio[12].append(TD_HEADER).append("% Suspensao</font></b></td>");
			dadosRelatorio[13].append(TD_HEADER).append("Qtd Adequação Plano</font></b></td>");
			dadosRelatorio[14].append(TD_HEADER).append("% Adequação Plano</font></b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getResDestino().getLinhaDestinoArray().length; dR++) {

				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getDestinoprevisto())).append(TD_FECHA);
				/*** verificar campo ***/
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getQtddestinp())).append(TD_FECHA);

				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getPorctotal())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getQtdretencao())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getPorcretencao())).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getQtdmigracao())).append(TD_FECHA);
				dadosRelatorio[6].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getPorcmigracao())).append(TD_FECHA);
				dadosRelatorio[7].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getQtdcancelado())).append(TD_FECHA);
				dadosRelatorio[8].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getPorccancelado())).append(TD_FECHA);
				/*** verificar campo ***/
				dadosRelatorio[9].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getPorcoferta())).append(TD_FECHA);

				dadosRelatorio[10].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getPorcbonus())).append(TD_FECHA);
				dadosRelatorio[11].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getQtdsuspensaoTemp())).append(TD_FECHA);
				dadosRelatorio[12].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getPorcsuspensaoTemp())).append(TD_FECHA);
				dadosRelatorio[13].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getQtdadequacao())).append(TD_FECHA);
				dadosRelatorio[14].append(TD_DADOS).append(converteNulo(getResDestino().getLinhaDestinoArray(dR).getPorcadequacao())).append(TD_FECHA);
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);
		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printCtaCte(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getRelatorioCC().getLinhaCCContaCorrenteArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[8];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Data</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Cliente</font></b></td>");
			// dadosRelatorio[2]
			// .append(TD_HEADER).append("Area</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("Linha</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("Bonus</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("Bonus Total</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("Qtd Parc</font></b></td>");
			dadosRelatorio[6].append(TD_HEADER).append("Pessoa</font></b></td>");
			dadosRelatorio[7].append(TD_HEADER).append("Segmentação</font></b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRelatorioCC().getLinhaCCContaCorrenteArray().length; dR++) {

				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRelatorioCC().getLinhaCCContaCorrenteArray(dR).getData())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRelatorioCC().getLinhaCCContaCorrenteArray(dR).getCliente())).append(TD_FECHA);
				// dadosRelatorio[2]
				// .append(TD_DADOS).append(converteNulo(getRelatorioCC().getLinhaCCContaCorrenteArray(dR).getArea()))
				// .append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRelatorioCC().getLinhaCCContaCorrenteArray(dR).getLinha())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getRelatorioCC().getLinhaCCContaCorrenteArray(dR).getBonus())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(getRelatorioCC().getLinhaCCContaCorrenteArray(dR).getBonusTotal()).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getRelatorioCC().getLinhaCCContaCorrenteArray(dR).getQtdParc())).append(TD_FECHA);
				dadosRelatorio[6].append(TD_DADOS).append(converteNulo(getRelatorioCC().getLinhaCCContaCorrenteArray(dR).getPessoa())).append(TD_FECHA);
				dadosRelatorio[7].append(TD_DADOS).append(converteNulo(getRelatorioCC().getLinhaCCContaCorrenteArray(dR).getClassificacao())).append(TD_FECHA);
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);
		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printOfertaTotalMensal(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getRelatorioOfertasTotMensal().getLinhaMensalArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[12];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Cliente</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Situação</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("Qtd Desconto</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("Val Desconto</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("Qtd Bonus</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("Valor Bonus</font></b></td>");
			dadosRelatorio[6].append(TD_HEADER).append("Qtd Migracao</font></b></td>");
			dadosRelatorio[7].append(TD_HEADER).append("Valor Migracao</font></b></td>");
			dadosRelatorio[8].append(TD_HEADER).append("Qtd Ponto</font></b></td>");
			dadosRelatorio[9].append(TD_HEADER).append("Valor Ponto</font></b></td>");
			dadosRelatorio[10].append(TD_HEADER).append("Qtd Geral</font></b></td>");
			dadosRelatorio[11].append(TD_HEADER).append("Valor Geral</font></b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRelatorioOfertasTotMensal().getLinhaMensalArray().length; dR++) {

				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRelatorioOfertasTotMensal().getLinhaMensalArray(dR).getCliente())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRelatorioOfertasTotMensal().getLinhaMensalArray(dR).getSituacao())).append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRelatorioOfertasTotMensal().getLinhaMensalArray(dR).getQtdDesconto())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getRelatorioOfertasTotMensal().getLinhaMensalArray(dR).getValDesconto())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(converteNulo(getRelatorioOfertasTotMensal().getLinhaMensalArray(dR).getQtdBonus())).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getRelatorioOfertasTotMensal().getLinhaMensalArray(dR).getValBonus())).append(TD_FECHA);
				dadosRelatorio[6].append(TD_DADOS).append(converteNulo(getRelatorioOfertasTotMensal().getLinhaMensalArray(dR).getQtdMigracao())).append(TD_FECHA);
				dadosRelatorio[7].append(TD_DADOS).append(converteNulo(getRelatorioOfertasTotMensal().getLinhaMensalArray(dR).getValMigracao())).append(TD_FECHA);
				dadosRelatorio[8].append(TD_DADOS).append(converteNulo(getRelatorioOfertasTotMensal().getLinhaMensalArray(dR).getQtdPonto())).append(TD_FECHA);
				dadosRelatorio[9].append(TD_DADOS).append(converteNulo(getRelatorioOfertasTotMensal().getLinhaMensalArray(dR).getValPonto())).append(TD_FECHA);
				dadosRelatorio[10].append(TD_DADOS).append(converteNulo(getRelatorioOfertasTotMensal().getLinhaMensalArray(dR).getQtdGeral())).append(TD_FECHA);
				dadosRelatorio[11].append(TD_DADOS).append(converteNulo(getRelatorioOfertasTotMensal().getLinhaMensalArray(dR).getValGeral())).append(TD_FECHA);
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);
		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printRetencaoPorPlanos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;
		int auxCont = getRelatorioRetePlanos().getColunas() != null ? getRelatorioRetePlanos().getColunas().getNomeArray().length : 0;

		nroRelatorios = dividir(getRelatorioRetePlanos().getLinhaPlanosArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[6 + auxCont];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Intenção Cancelamento</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Qtd Intencões</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("% Intenções</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("Destino Previsto</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("Qtd Adequação de Plano</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("% Adequação de Plano</font></b></td>");
			for (int c = 0; c < auxCont; c++) {
				dadosRelatorio[6 + c].append(TD_HEADER).append(getRelatorioRetePlanos().getColunas().getNomeArray(c).toString() + "</font></b></td>");
			}

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRelatorioRetePlanos().getLinhaPlanosArray().length; dR++) {

				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRelatorioRetePlanos().getLinhaPlanosArray(dR).getIntCancelamento())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRelatorioRetePlanos().getLinhaPlanosArray(dR).getQtdIntencao())).append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRelatorioRetePlanos().getLinhaPlanosArray(dR).getPercIntencao())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getRelatorioRetePlanos().getLinhaPlanosArray(dR).getDestinoPrevisto())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(converteNulo(getRelatorioRetePlanos().getLinhaPlanosArray(dR).getQtdAdeq())).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getRelatorioRetePlanos().getLinhaPlanosArray(dR).getPercAdeq())).append(TD_FECHA);
				for (int v = 0; v < auxCont; v++) {
					dadosRelatorio[6 + v].append(TD_DADOS).append(converteNulo(getRelatorioRetePlanos().getLinhaPlanosArray(dR).getValoresColunas().getValorArray(v))).append(TD_FECHA);
				}
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);
		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printOperador(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getRelatorioOperador().getLinhaOperadorArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[11];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Operador</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Soma</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("% Renteções</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("% Migração</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("% Cancelado</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("% Oferta</font></b></td>");
			dadosRelatorio[6].append(TD_HEADER).append("% Suspensão</font></b></td>");
			dadosRelatorio[7].append(TD_HEADER).append("% Adequação de Plano</font></b></td>");
			dadosRelatorio[8].append(TD_HEADER).append("% Percentual</font></b></td>");
			dadosRelatorio[9].append(TD_HEADER).append("Resultado</font></b></td>");
			dadosRelatorio[10].append(TD_HEADER).append("Quantidade</font></b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRelatorioOperador().getLinhaOperadorArray().length; dR++) {

				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRelatorioOperador().getLinhaOperadorArray(dR).getOperador())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRelatorioOperador().getLinhaOperadorArray(dR).getSoma())).append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRelatorioOperador().getLinhaOperadorArray(dR).getPorcretencoes())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getRelatorioOperador().getLinhaOperadorArray(dR).getPorcmigracao())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(converteNulo(getRelatorioOperador().getLinhaOperadorArray(dR).getPorccancelado())).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getRelatorioOperador().getLinhaOperadorArray(dR).getPorcoferta())).append(TD_FECHA);
				dadosRelatorio[6].append(TD_DADOS).append(converteNulo(getRelatorioOperador().getLinhaOperadorArray(dR).getPorcsuspensao())).append(TD_FECHA);
				dadosRelatorio[7].append(TD_DADOS).append(converteNulo(getRelatorioOperador().getLinhaOperadorArray(dR).getPorcadequacaoplano())).append(TD_FECHA);
				dadosRelatorio[8].append(TD_DADOS).append(converteNulo(getRelatorioOperador().getLinhaOperadorArray(dR).getPercentual())).append(TD_FECHA);
				if (getRelatorioOperador().getLinhaOperadorArray(dR).getResultado() != null) {
					dadosRelatorio[9].append(TD_DADOS);
					for (int j = 0; j < getRelatorioOperador().getLinhaOperadorArray(dR).getResultado().getDescricaoArray().length; j++) {
						dadosRelatorio[9].append(converteNulo(getRelatorioOperador().getLinhaOperadorArray(dR).getResultado().getDescricaoArray(j))).append("<br>");
					}
					dadosRelatorio[9].append(TD_FECHA);
					dadosRelatorio[10].append(TD_DADOS);
					for (int j = 0; j < getRelatorioOperador().getLinhaOperadorArray(dR).getResultado().getQuantidadeArray().length; j++) {
						dadosRelatorio[10].append(converteNulo(getRelatorioOperador().getLinhaOperadorArray(dR).getResultado().getQuantidadeArray(j))).append("<br>");
					}
					dadosRelatorio[10].append(TD_FECHA);
				}
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);
		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printNotesLoja(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getRelatorioNotesLoja().getLinhaNotesLojaArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[12];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Data</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Cliente</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("Linha</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("Agendamento</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("Loja</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("Aparelho</font></b></td>");
			dadosRelatorio[6].append(TD_HEADER).append("Preço Venda</font></b></td>");
			dadosRelatorio[7].append(TD_HEADER).append("Desc</font></b></td>");
			dadosRelatorio[8].append(TD_HEADER).append("Preço Final</font></b></td>");
			dadosRelatorio[9].append(TD_HEADER).append("Nº Vezes</font></b></td>");
			dadosRelatorio[10].append(TD_HEADER).append("Env Por</font></b></td>");
			dadosRelatorio[11].append(TD_HEADER).append("Segmentação</font></b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRelatorioNotesLoja().getLinhaNotesLojaArray().length; dR++) {

				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getData())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getCliente())).append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getLinha())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getAgendamento())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getLoja())).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getAparelho())).append(TD_FECHA);
				dadosRelatorio[6].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getPrecoVenda())).append(TD_FECHA);
				dadosRelatorio[7].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getDesc())).append(TD_FECHA);
				dadosRelatorio[8].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getPrecoFinal())).append(TD_FECHA);
				dadosRelatorio[9].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getNroVezes())).append(TD_FECHA);
				dadosRelatorio[10].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getEnvPor())).append(TD_FECHA);
				dadosRelatorio[11].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getClassificacao())).append(TD_FECHA);
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);
		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printPlanos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getRetePlanosDist().getLinhaPlanosDistincaoArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[8];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		request.getAttribute("dsPlano");

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Intenção do Cancelamento</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Qtd Intencões</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("% Intencões</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("Destino Previsto</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("Qtd Adequação de Plano</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("% Adequação de Plano</font></b></td>");
			dadosRelatorio[6].append(TD_HEADER).append("Qtd</font></b></td>");
			dadosRelatorio[7].append(TD_HEADER).append("% </font></b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRetePlanosDist().getLinhaPlanosDistincaoArray().length; dR++) {

				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRetePlanosDist().getLinhaPlanosDistincaoArray(dR).getIntCancelamento())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRetePlanosDist().getLinhaPlanosDistincaoArray(dR).getQtdintencao())).append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRetePlanosDist().getLinhaPlanosDistincaoArray(dR).getPorcintencao())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getRetePlanosDist().getLinhaPlanosDistincaoArray(dR).getDestinoprevisto())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(converteNulo(getRetePlanosDist().getLinhaPlanosDistincaoArray(dR).getQtdadequacao())).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getRetePlanosDist().getLinhaPlanosDistincaoArray(dR).getPorcadequacao())).append(TD_FECHA);
				dadosRelatorio[6].append(TD_DADOS).append(converteNulo(getRetePlanosDist().getLinhaPlanosDistincaoArray(dR).getPlano())).append(TD_FECHA);
				dadosRelatorio[7].append(TD_DADOS).append(converteNulo(getRetePlanosDist().getLinhaPlanosDistincaoArray(dR).getPorcplano())).append(TD_FECHA);
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);
		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="relatoriosImpressao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward printPlanosDistincao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		int nroRelatorios;

		nroRelatorios = dividir(getRelatorioNotesLoja().getLinhaNotesLojaArray().length, 5);

		StringBuffer dadosRelatorio[] = new StringBuffer[12];
		StringBuffer printRelatorio = new StringBuffer();

		for (int i = 0; i < dadosRelatorio.length; i++) {
			dadosRelatorio[i] = new StringBuffer();
		}

		printRelatorio.append(TABLE);

		for (int nR = 1; nR <= nroRelatorios; nR++) {

			dadosRelatorio[0].append(TD_HEADER).append("Data</font></b></td>");
			dadosRelatorio[1].append(TD_HEADER).append("Cliente</font></b></td>");
			dadosRelatorio[2].append(TD_HEADER).append("Linha</font></b></td>");
			dadosRelatorio[3].append(TD_HEADER).append("Agendamento</font></b></td>");
			dadosRelatorio[4].append(TD_HEADER).append("Loja</font></b></td>");
			dadosRelatorio[5].append(TD_HEADER).append("Aparelho</font></b></td>");
			dadosRelatorio[6].append(TD_HEADER).append("Preço Venda</font></b></td>");
			dadosRelatorio[7].append(TD_HEADER).append("Desc</font></b></td>");
			dadosRelatorio[8].append(TD_HEADER).append("Preço Final</font></b></td>");
			dadosRelatorio[9].append(TD_HEADER).append("Nº Vezes</font></b></td>");
			dadosRelatorio[10].append(TD_HEADER).append("Env Por</font></b></td>");
			dadosRelatorio[11].append(TD_HEADER).append("Segmentação</font></b></td>");

			for (int dR = ((nR * 5)) - 5; dR < (nR * 5) && dR < getRelatorioNotesLoja().getLinhaNotesLojaArray().length; dR++) {

				dadosRelatorio[0].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getData())).append(TD_FECHA);
				dadosRelatorio[1].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getCliente())).append(TD_FECHA);
				dadosRelatorio[2].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getLinha())).append(TD_FECHA);
				dadosRelatorio[3].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getAgendamento())).append(TD_FECHA);
				dadosRelatorio[4].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getLoja())).append(TD_FECHA);
				dadosRelatorio[5].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getAparelho())).append(TD_FECHA);
				dadosRelatorio[6].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getPrecoVenda())).append(TD_FECHA);
				dadosRelatorio[7].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getDesc())).append(TD_FECHA);
				dadosRelatorio[8].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getPrecoFinal())).append(TD_FECHA);
				dadosRelatorio[9].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getNroVezes())).append(TD_FECHA);
				dadosRelatorio[10].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getEnvPor())).append(TD_FECHA);
				dadosRelatorio[11].append(TD_DADOS).append(converteNulo(getRelatorioNotesLoja().getLinhaNotesLojaArray(dR).getClassificacao())).append(TD_FECHA);
			}

			for (int i = 0; i < dadosRelatorio.length; i++) {
				dadosRelatorio[i].append(TR_FECHA);
				printRelatorio.append(dadosRelatorio[i].toString());
				dadosRelatorio[i].delete(0, dadosRelatorio[i].length());
			}

			printRelatorio.append(TR_SEPARA);
		}

		printRelatorio.append(TABLE_FECHA);

		getFormRelatorios().setRelatorio(printRelatorio.toString());
		getFormRelatorios().setTotalReg(request.getParameter("totalReg"));
		getFormRelatorios().setNome(request.getParameter("nome"));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class FormRelatorios extends ActionForm {
		private String nome;
		private String relatorio;
		private String totalReg;

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getNome() {
			return this.nome;
		}

		public void setRelatorio(String relatorio) {
			this.relatorio = relatorio;
		}

		public String getRelatorio() {
			return this.relatorio;
		}

		public void setTotalReg(String totalReg) {
			this.totalReg = totalReg;
		}

		public String getTotalReg() {
			return this.totalReg;
		}
	}

	private FormRelatorios formRelatorios;

	public FormRelatorios getFormRelatorios() {
		if (formRelatorios == null) {
			formRelatorios = new FormRelatorios();
		}
		return formRelatorios;
	}

	/**
	 * Formata uma string para decimal de acordo com a localidade atual
	 * 
	 * @param valor
	 *            String
	 * @param tipo
	 *            int 0 = nacional; 1 = internacional
	 * @return String
	 */
	private String formatarDecimal(String valor, int tipo) {
		String pattern = "###,##0.00";
		char sepDecimal = ',';
		char sepGrupo = '.';

		// se a variável estiver nula, retorna vazio
		if (valor == null || "".equals(valor)) {
			valor = ConstantesCRM.SZERO;
		}

		// verifica o tipo de formataçao
		if (tipo == 1) {
			pattern = "##0.00";
			sepDecimal = '.';
			sepGrupo = ',';
			valor = valor.replace('.', 'X').replaceAll("X", "").replace(',', '.');
		}

		DecimalFormatSymbols dSymbols = new DecimalFormatSymbols();
		dSymbols.setDecimalSeparator(sepDecimal);
		dSymbols.setGroupingSeparator(sepGrupo);

		DecimalFormat dFormat = new DecimalFormat("00", dSymbols);

		dFormat.isDecimalSeparatorAlwaysShown();
		dFormat.applyPattern(pattern + ";(" + pattern + ")");
		return dFormat.format(new Double(valor));
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class FormAdimplencia extends ActionForm {
		private String totalConsultas;

		private String totalInadimplentes;

		private String totalAdimplentes;

		public void setTotalAdimplentes(String totalAdimplentes) {
			this.totalAdimplentes = totalAdimplentes;
		}

		public String getTotalAdimplentes() {
			return this.totalAdimplentes;
		}

		public void setTotalInadimplentes(String totalInadimplentes) {
			this.totalInadimplentes = totalInadimplentes;
		}

		public String getTotalInadimplentes() {
			return this.totalInadimplentes;
		}

		public void setTotalConsultas(String totalConsultas) {
			this.totalConsultas = totalConsultas;
		}

		public String getTotalConsultas() {
			return this.totalConsultas;
		}
	}
}
