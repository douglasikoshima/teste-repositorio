package tracking.RelatoriosTracking;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.TrackingRelatorioConsolidadoVODocument.TrackingRelatorioConsolidadoVO;
import br.com.vivo.fo.cliente.vo.TrackingRelatorioDetalhadoVODocument.TrackingRelatorioDetalhadoVO;
import br.com.vivo.fo.cliente.vo.TrackingRelatoriosFiltrosVODocument.TrackingRelatoriosFiltrosVO;
import br.com.vivo.fo.cliente.vo.TrackingRelatoriosFiltrosVODocument.TrackingRelatoriosFiltrosVO.CanaisVenda;
import br.com.vivo.fo.cliente.vo.TrackingRelatoriosFiltrosVODocument.TrackingRelatoriosFiltrosVO.Regionais;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import br.com.vivo.fo.ctrls.tracking.relatoriosTracking.RelatoriosTrackingFacade;
import br.com.vivo.fo.exception.TuxedoErrorException;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class RelatoriosTrackingController extends AbstractAction {

	private static final long serialVersionUID = -3840926730303986005L;

	protected global.Global globalApp = new global.Global();

	private RelatoriosTrackingForm relatoriosTrackingForm;

	private int nrItensPorPagina = 200;

	@EJB
	private RelatoriosTrackingFacade relatoriosTracking;

	@EJB
	private TelaInicialFacade telaInicialFacade;

	public RelatoriosTrackingForm getRelatoriosTrackingForm() {
		if (this.relatoriosTrackingForm == null) {
			this.relatoriosTrackingForm = new RelatoriosTrackingForm();
		}
		return this.relatoriosTrackingForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("getRelatorio".equals(mapping.getParameter())) {
			return getRelatorio(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		RelatoriosTrackingForm form = (RelatoriosTrackingForm)formParam;

		try {

			String user = ConstantesCRM.getCurrentUser(request.getSession());
			this.relatoriosTrackingForm = new RelatoriosTrackingForm();

			try {
				ParametroVO apoioParam = telaInicialFacade.getParametro(user,"TRACKINGREL_PAGINACAO_ITENSPP");
				nrItensPorPagina = Integer.parseInt(apoioParam.getDsValorParametro());
			} catch (TuxedoErrorException e){}

			Regionais regionais = Regionais.Factory.newInstance();
			regionais.addNewDisponiveis();
			regionais.getDisponiveis().addNewIDValorVO().setValor("AC");
			regionais.getDisponiveis().addNewIDValorVO().setValor("AL");
			regionais.getDisponiveis().addNewIDValorVO().setValor("AM");
			regionais.getDisponiveis().addNewIDValorVO().setValor("AP");
			regionais.getDisponiveis().addNewIDValorVO().setValor("BA");
			regionais.getDisponiveis().addNewIDValorVO().setValor("CE");
			regionais.getDisponiveis().addNewIDValorVO().setValor("DF");
			regionais.getDisponiveis().addNewIDValorVO().setValor("ES");
			regionais.getDisponiveis().addNewIDValorVO().setValor("GO");
			regionais.getDisponiveis().addNewIDValorVO().setValor("MA");
			regionais.getDisponiveis().addNewIDValorVO().setValor("MG");
			regionais.getDisponiveis().addNewIDValorVO().setValor("MS");
			regionais.getDisponiveis().addNewIDValorVO().setValor("MT");
			regionais.getDisponiveis().addNewIDValorVO().setValor("PA");
			regionais.getDisponiveis().addNewIDValorVO().setValor("PB");
			regionais.getDisponiveis().addNewIDValorVO().setValor("PE");
			regionais.getDisponiveis().addNewIDValorVO().setValor("PI");
			regionais.getDisponiveis().addNewIDValorVO().setValor("PR");
			regionais.getDisponiveis().addNewIDValorVO().setValor("RJ");
			regionais.getDisponiveis().addNewIDValorVO().setValor("RN");
			regionais.getDisponiveis().addNewIDValorVO().setValor("RO");
			regionais.getDisponiveis().addNewIDValorVO().setValor("RR");
			regionais.getDisponiveis().addNewIDValorVO().setValor("RS");
			regionais.getDisponiveis().addNewIDValorVO().setValor("SC");
			regionais.getDisponiveis().addNewIDValorVO().setValor("SE");
			regionais.getDisponiveis().addNewIDValorVO().setValor("SP");
			regionais.getDisponiveis().addNewIDValorVO().setValor("SI");
			regionais.getDisponiveis().addNewIDValorVO().setValor("TO");

			CanaisVenda canaisVenda = CanaisVenda.Factory.newInstance();
			canaisVenda.addNewDisponiveis();
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("30");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("Clientes Especiais");
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("40");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("Loja Virtual");
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("55");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("Ação Concorrência (Captação)");
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("00");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("Revenda");
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("05");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("Varejo");
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("10");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("Gr Contas");
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("15");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("Clientes Nacionais");
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("20");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("Dados Corporativos");
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("25");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("PEMES");
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("35");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("Loja Própria");
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("50");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("Funcionários");
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("91");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("DETRAF");
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("92");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("Co-Billing");
			canaisVenda.getDisponiveis().addNewIDValorVO();
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setId("45");
			canaisVenda.getDisponiveis().getIDValorVOArray(canaisVenda.getDisponiveis().getIDValorVOArray().length-1).setValor("EMP. GRUPO TELECOM");

			getRelatoriosTrackingForm().getFiltros().setCanaisVenda(canaisVenda);
			getRelatoriosTrackingForm().getFiltros().setRegionais(regionais);
			getRelatoriosTrackingForm().getFiltros().addNewPaginacao().setPageNumber(1);

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch(Exception e) {
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="relatorioConsolidado" path="relatorioConsolidado.jsp"
	 * @jpf:forward name="relatorioDetalhado" path="relatorioDetalhado.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward getRelatorio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		RelatoriosTrackingForm form = (RelatoriosTrackingForm)formParam;

		String destino = ConstantesCRM.SVAZIO;
		TrackingRelatoriosFiltrosVO filtros = TrackingRelatoriosFiltrosVO.Factory.newInstance();
		filtros.setSgTipoRelatorio(form.getTipoRelatorio());

		try {

			if (getRelatoriosTrackingForm().getFiltros().getRegionais().getSelecionados() != null) {

				filtros = getRelatoriosTrackingForm().getFiltros();
				form.setTipoRelatorio(filtros.getSgTipoRelatorio());

			} else {

				// Adiciona Regionais ao filtro
				filtros.addNewRegionais().addNewSelecionados();
				for (int i = 0; i < form.getRegionaisSelecionadas().length; i++) {
					filtros.getRegionais().getSelecionados().addNewIDValorVO().setValor(
							form.getRegionaisSelecionadas()[i]
					);
				}

				// Adiciona Canais de Venda ao filtro
				filtros.addNewCanaisVenda().addNewSelecionados();
				for (int i = 0; i < form.getCanaisVendaSelecionados().length; i++) {
					filtros.getCanaisVenda().getSelecionados().addNewIDValorVO().setId(
							form.getCanaisVendaSelecionados()[i]
					);
				}

				// Adiciona Segmentos ao filtro
				if (form.getInMassivo()) {
					filtros.addDsSegmento("MASSIVO");
				}
				if (form.getInPremium()) {
					filtros.addDsSegmento("PREMIUM");
				}

				// Adiciona Data do filtro
				filtros.setDtPeriodoDe(form.getDtPeriodoDe());

				// Adiciona Página
				filtros.setNrItensPorPagina(nrItensPorPagina);

			}

			if (filtros.getPaginacao() == null) {
				filtros.addNewPaginacao();
			}
			filtros.getPaginacao().setPageNumber(Integer.parseInt(form.getPageNumber()));

			// Relatório detalhado de pedidos em aberto ou
			// Relatório detalhado para monitorar o operador logístico
			if ("RDPA".equals(form.getTipoRelatorio())
					|| "RDOL".equals(form.getTipoRelatorio())) {

				destino = "relatorioDetalhado";

				TrackingRelatorioDetalhadoVO trackingRelatorioDetalhado = relatoriosTracking.getRelatorioDetalhado(filtros);
				getRelatoriosTrackingForm().setRelatorioDetalhado(trackingRelatorioDetalhado);
				request.setAttribute("paginas", getPageNumbers(Integer.parseInt(form.getPageNumber()), trackingRelatorioDetalhado.getPaginacao().getRecordCount()));

			} else

				// Relatório de pedidos em aberto
				if ("RSPA".equals(form.getTipoRelatorio())) {

					destino = "relatorioConsolidado";

					TrackingRelatorioConsolidadoVO trackingRelatorioConsolidado = relatoriosTracking.getRelatorioConsolidado(filtros);
					getRelatoriosTrackingForm().setRelatorioConsolidado(trackingRelatorioConsolidado);

				}

			getRelatoriosTrackingForm().setTipoRelatorio(form.getTipoRelatorio());
			getRelatoriosTrackingForm().setFiltros(filtros);

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch(Exception e) {
			FormError formError = globalApp.buildFormError(e, ConstantesCRM.TELAINCIAL);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	private int[] getPageNumbers(int pN, int qtdeTotal) {
		int qtdeTotalPaginas = (qtdeTotal / nrItensPorPagina == 0) ? 1 : (int) Math.ceil((double)qtdeTotal / (double)nrItensPorPagina);
		int qtdePaginasApresentadas = 10;
		if (String.valueOf(pN).length() > 2) {
			pN = Integer.parseInt(String.valueOf(pN).substring(String.valueOf(pN).length() - 2));
		}
		if (ConstantesCRM.SZERO.equals(String.valueOf(pN).substring(String.valueOf(pN).length() - 1))) {
			pN = pN - (qtdePaginasApresentadas-1);
		}
		while (!String.valueOf(pN).substring(String.valueOf(pN).length()-1,String.valueOf(pN).length()).equals(ConstantesCRM.SZERO)) {
			pN--;
		}
		if (qtdeTotalPaginas - pN < 10) {
			qtdePaginasApresentadas = qtdeTotalPaginas - pN;
		}
		int[] paginas = new int[qtdePaginasApresentadas];
		for (int i = 0; i < paginas.length; i++) {
			paginas[i] = pN + 1;
			pN++;
		}
		return paginas;
	}

	public static class RelatoriosTrackingForm extends ActionForm {

		private TrackingRelatoriosFiltrosVO filtros;
		private TrackingRelatorioDetalhadoVO relatorioDetalhado;
		private TrackingRelatorioConsolidadoVO relatorioConsolidado;
		private String tipoRelatorio;
		private String[] canaisVendaDisponiveis;
		private String[] canaisVendaSelecionados;
		private String[] regionaisDisponiveis;
		private String[] regionaisSelecionadas;
		private boolean inConsolidado;
		private boolean inPremium;
		private boolean inMassivo;
		private String dtPeriodoDe;
		private String dtPeriodoAte;
		private String pageNumber;

		public TrackingRelatoriosFiltrosVO getFiltros() {
			if (this.filtros == null) {
				this.filtros = TrackingRelatoriosFiltrosVO.Factory.newInstance();
			}
			return this.filtros;
		}

		public void setFiltros(TrackingRelatoriosFiltrosVO filtros) {
			this.filtros = filtros;
		}

		public TrackingRelatorioDetalhadoVO getRelatorioDetalhado() {
			if (this.relatorioDetalhado == null) {
				this.relatorioDetalhado = TrackingRelatorioDetalhadoVO.Factory.newInstance();
			}
			return this.relatorioDetalhado;
		}

		public void setRelatorioDetalhado(TrackingRelatorioDetalhadoVO relatorioDetalhado) {
			this.relatorioDetalhado = relatorioDetalhado;
		}

		public TrackingRelatorioConsolidadoVO getRelatorioConsolidado() {
			if (this.relatorioConsolidado == null) {
				this.relatorioConsolidado = TrackingRelatorioConsolidadoVO.Factory.newInstance();
			}
			return this.relatorioConsolidado;
		}

		public void setRelatorioConsolidado(TrackingRelatorioConsolidadoVO relatorioConsolidado) {
			this.relatorioConsolidado = relatorioConsolidado;
		}

		public String[] getCanaisVendaDisponiveis() {
			return this.canaisVendaDisponiveis;
		}

		public void setCanaisVendaDisponiveis(String[] canaisVendaDisponiveis) {
			this.canaisVendaDisponiveis = canaisVendaDisponiveis;
		}

		public String[] getCanaisVendaSelecionados() {
			return this.canaisVendaSelecionados;
		}

		public void setCanaisVendaSelecionados(String[] canaisVendaSelecionados) {
			this.canaisVendaSelecionados = canaisVendaSelecionados;
		}

		public String[] getRegionaisDisponiveis() {
			return this.regionaisDisponiveis;
		}

		public void setRegionaisDisponiveis(String[] regionaisDisponiveis) {
			this.regionaisDisponiveis = regionaisDisponiveis;
		}

		public String[] getRegionaisSelecionadas() {
			return this.regionaisSelecionadas;
		}

		public void setRegionaisSelecionadas(String[] regionaisSelecionadas) {
			this.regionaisSelecionadas = regionaisSelecionadas;
		}

		public boolean getInConsolidado() {
			return this.inConsolidado;
		}

		public void setInConsolidado(boolean inConsolidado) {
			this.inConsolidado = inConsolidado;
		}

		public boolean getInPremium() {
			return this.inPremium;
		}

		public void setInPremium(boolean inPremium) {
			this.inPremium = inPremium;
		}

		public boolean getInMassivo() {
			return this.inMassivo;
		}

		public void setInMassivo(boolean inMassivo) {
			this.inMassivo = inMassivo;
		}

		public String getTipoRelatorio() {
			return this.tipoRelatorio;
		}

		public void setTipoRelatorio(String tipoRelatorio) {
			this.tipoRelatorio = tipoRelatorio;
		}

		public String getDtPeriodoDe() {
			return this.dtPeriodoDe;
		}

		public void setDtPeriodoDe(String dtPeriodoDe) {
			this.dtPeriodoDe = dtPeriodoDe;
		}

		public String getDtPeriodoAte() {
			return this.dtPeriodoAte;
		}

		public void setDtPeriodoAte(String dtPeriodoAte) {
			this.dtPeriodoAte = dtPeriodoAte;
		}

		public String getPageNumber() {
			return this.pageNumber;
		}

		public void setPageNumber(String pageNumber) {
			this.pageNumber = pageNumber;
		}

	}

}
