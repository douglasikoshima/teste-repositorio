package workflow.Indicadores;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFIndicadoresPesquisaVODocument.WFIndicadoresPesquisaVO;
import br.com.vivo.fo.workflow.vo.WFIndicadoresVODocument.WFIndicadoresVO;

import com.indracompany.actions.AbstractAction;

public class IndicadoresController extends AbstractAction {

	private static final long serialVersionUID = -4584538106764898896L;

	private static Logger log = new Logger("workflow");

	private IndicadoresForm indicadoresForm = new IndicadoresForm();

	@EJB
	private br.com.vivo.fo.ctrls.workflow.indicadores.IndicadoresFacade indicadoresFacade;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {

		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);

		} else if ("gerarRelatorio".equals(mapping.getParameter())) {
			return gerarRelatorio(mapping, form, request, response);

		} else if ("inicioTipoProcesso".equals(mapping.getParameter())) {
			return inicioTipoProcesso(mapping, form, request, response);

		} else if ("inicioNaturezaCliente".equals(mapping.getParameter())) {
			return inicioNaturezaCliente(mapping, form, request, response);

		} else if ("inicioSegmentCart".equals(mapping.getParameter())) {
			return inicioSegmentCart(mapping, form, request, response);

		} else if ("inicioEstadoLinha".equals(mapping.getParameter())) {
			return inicioEstadoLinha(mapping, form, request, response);

		} else if ("inicioResumo".equals(mapping.getParameter())) {
			return inicioResumo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="Indicadores.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IndicadoresForm form = (IndicadoresForm) formParam;

		log.debug("IndicadoresController:begin.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		if (indicadoresForm.getIndicadoresPesquisaVO() == null) {
			indicadoresForm.setIndicadoresPesquisaVO(indicadoresFacade.obtemWFIndicadoresPesquisaVO(user));
		}
		indicadoresForm.setAtualizacao(5);
		log.debug("IndicadoresController:begin.do - [atualizacao = " + form.getAtualizacao() + "]");
		log.debug("IndicadoresController:begin.do - Fim do Metodo]");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IndicadoresForm form = (IndicadoresForm) formParam;

		log.debug("IndicadoresController:pesquisar.do - Inicio do Metodo]");
		WFIndicadoresPesquisaVO indicadoresFiltro = WFIndicadoresPesquisaVO.Factory.newInstance();

		this.indicadoresForm = form;

		String menosuno = new String("-1");

		if (form.dtInicio != null) {
			indicadoresFiltro.setDtInicio(form.dtInicio);
			log.debug("IndicadoresController:pesquisar.do - [dtInicio = " + form.getDtInicio() + "]");
		}

		if (form.dtFim != null) {
			indicadoresFiltro.setDtFim(form.dtFim);
			log.debug("IndicadoresController:pesquisar.do - [dtFim = " + form.getDtFim() + "]");
		}

		if (!menosuno.equals(form.regionalSel)) {
			indicadoresFiltro.addNewWFRelatoriosFiltroRegionalVO().setIdRegional(form.regionalSel);
			log.debug("IndicadoresController:pesquisar.do - [idRegional = " + form.regionalSel + "]");
		}

		if (!menosuno.equals(form.grupoSel)) {
			indicadoresFiltro.addNewWFGrupoVO().setIdGrupo(form.grupoSel);
			log.debug("IndicadoresController:pesquisar.do - [idGrupo = " + form.grupoSel + "]");
		}

		indicadoresForm.setIndicadoresFiltro(indicadoresFiltro);
		log.debug("IndicadoresController:pesquisar.do - Fim do Metodo]");
		return this.begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatorioIndicadoresTipo.jsp"
	 */
	protected ActionForward gerarRelatorio(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IndicadoresForm form = (IndicadoresForm) formParam;

		log.debug("IndicadoresController:gerarRelatorio.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		indicadoresForm.setIndicadoresVO(indicadoresFacade.obtemWFIndicadoresVO(user, form.tipo, indicadoresForm.getIndicadoresFiltro()));
		log.debug("IndicadoresController:gerarRelatorio.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatorioIndicadoresTipo.jsp"
	 */
	protected ActionForward inicioTipoProcesso(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IndicadoresForm form = (IndicadoresForm) formParam;

		log.debug("IndicadoresController:inicioTipoProcesso.do - Inicio do Metodo]");
		form.setTipo("1");
		log.debug("IndicadoresController:inicioTipoProcesso.do - [tipo = " + form.getTipo() + "]");
		log.debug("IndicadoresController:inicioTipoProcesso.do - Fim do Metodo]");
		return this.gerarRelatorio(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatorioIndicadoresTipo.jsp"
	 */
	protected ActionForward inicioNaturezaCliente(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IndicadoresForm form = (IndicadoresForm) formParam;

		log.debug("IndicadoresController:inicioNaturezaCliente.do - Inicio do Metodo]");
		form.setTipo("2");
		log.debug("IndicadoresController:inicioNaturezaCliente.do - [tipo = " + form.getTipo() + "]");
		log.debug("IndicadoresController:inicioNaturezaCliente.do - Fim do Metodo]");
		return this.gerarRelatorio(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatorioIndicadoresTipo.jsp"
	 */
	protected ActionForward inicioSegmentCart(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IndicadoresForm form = (IndicadoresForm) formParam;

		log.debug("IndicadoresController:inicioSegmentCart.do - Inicio do Metodo]");
		form.setTipo("3");
		log.debug("IndicadoresController:inicioSegmentCart.do - [tipo = " + form.getTipo() + "]");
		log.debug("IndicadoresController:inicioSegmentCart.do - Fim do Metodo]");
		return this.gerarRelatorio(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="relatorioIndicadoresTipo.jsp"
	 */
	protected ActionForward inicioEstadoLinha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		IndicadoresForm form = (IndicadoresForm) formParam;

		log.debug("IndicadoresController:inicioEstadoLinha.do - Inicio do Metodo]");
		form.setTipo("4");
		log.debug("IndicadoresController:inicioEstadoLinha.do - [tipo = " + form.getTipo() + "]");
		log.debug("IndicadoresController:inicioEstadoLinha.do - Fim do Metodo]");
		return this.gerarRelatorio(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IndicadoresResumo.jsp"
	 */
	protected ActionForward inicioResumo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("IndicadoresController:inicioResumo.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		// obter os dados do facade pelo filtro em indicadoresForm.indicadoresFiltro
		indicadoresForm.setIndicadoresVO(indicadoresFacade.gerarResumoAcompanhamento(user));
		log.debug("IndicadoresController:inicioResumo.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class IndicadoresForm extends ActionForm {

		private static final long serialVersionUID = 4205596798416113958L;

		private int atualizacao;
		private String tipo;
		private WFIndicadoresPesquisaVO indicadoresFiltro;
		private WFIndicadoresPesquisaVO indicadoresPesquisaVO;
		private WFIndicadoresVO indicadoresVO;
		private String dtFim;
		private String dtInicio;
		private String grupoSel;
		private String regionalSel;

		public void setRegionalSel(String regionalSel) {
			this.regionalSel = regionalSel;
		}

		public String getRegionalSel() {
			return this.regionalSel;
		}

		public void setGrupoSel(String grupoSel) {
			this.grupoSel = grupoSel;
		}

		public String getGrupoSel() {
			return this.grupoSel;
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

		public void setIndicadoresVO(WFIndicadoresVO indicadoresVO) {
			this.indicadoresVO = indicadoresVO;
		}

		public WFIndicadoresVO getIndicadoresVO() {
			return this.indicadoresVO;
		}

		public void setIndicadoresPesquisaVO(WFIndicadoresPesquisaVO indicadoresPesquisaVO) {
			this.indicadoresPesquisaVO = indicadoresPesquisaVO;
		}

		public WFIndicadoresPesquisaVO getIndicadoresPesquisaVO() {
			return this.indicadoresPesquisaVO;
		}

		public void setIndicadoresFiltro(WFIndicadoresPesquisaVO indicadoresFiltro) {
			this.indicadoresFiltro = indicadoresFiltro;
		}

		public WFIndicadoresPesquisaVO getIndicadoresFiltro() {
			if (this.indicadoresFiltro == null) {
				this.indicadoresFiltro = WFIndicadoresPesquisaVO.Factory.newInstance();
			}
			return this.indicadoresFiltro;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getTipo() {
			return this.tipo;
		}

		public void setAtualizacao(int atualizacao) {
			this.atualizacao = atualizacao;
		}

		public int getAtualizacao() {
			return this.atualizacao;
		}
	}

	public IndicadoresForm getIndicadoresForm() {
		return this.indicadoresForm;
	}
}
