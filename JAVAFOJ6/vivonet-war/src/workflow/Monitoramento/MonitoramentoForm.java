package workflow.Monitoramento;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.MonitoramentoResultadoVODocument.MonitoramentoResultadoVO;

public class MonitoramentoForm extends ActionForm {

	private static final long serialVersionUID = -5248105141622802301L;

	private String pesquisar = null;
	private String idGrupo = null;
	private String dtInicio = null;
	private String dtFim = null;
	private MonitoramentoPesquisaVO monitoramentoPesquisaVO = null;
	private MonitoramentoResultadoVO monitoramentoResultadoVO = null;

	private RelatorioVO[] relatorioGeralVO = null;
	private RelatorioVO[] relatorioEstadoTopVO = null;
	private RelatorioVO[] relatorioEstadoBottonVO = null;
	private RelatorioVO[] relatorioAtendenteVO = null;

	public void setPesquisar(String pesquisar) {
		this.pesquisar = pesquisar;
	}

	public String getPesquisar() {
		return this.pesquisar;
	}

	public void setMonitoramentoPesquisaVO(MonitoramentoPesquisaVO monitoramentoPesquisaVO) {
		this.monitoramentoPesquisaVO = monitoramentoPesquisaVO;
	}

	public MonitoramentoPesquisaVO getMonitoramentoPesquisaVO() {
		return this.monitoramentoPesquisaVO;
	}

	public void setMonitoramentoResultadoVO(MonitoramentoResultadoVO monitoramentoResultadoVO) {
		this.monitoramentoResultadoVO = monitoramentoResultadoVO;
	}

	public MonitoramentoResultadoVO getMonitoramentoResultadoVO() {
		return this.monitoramentoResultadoVO;
	}

	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}

	public String getIdGrupo() {
		return this.idGrupo;
	}

	public void setdtInicio(String dtInicio) {
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

	public void setRelatorioGeralVO(RelatorioVO[] relatorioGeralVO) {
		this.relatorioGeralVO = relatorioGeralVO;
	}

	public RelatorioVO[] getRelatorioGeralVO() {
		return this.relatorioGeralVO;
	}

	public void setRelatorioEstadoTopVO(RelatorioVO[] relatorioEstadoTopVO) {
		this.relatorioEstadoTopVO = relatorioEstadoTopVO;
	}

	public RelatorioVO[] getRelatorioEstadoTopVO() {
		return this.relatorioEstadoTopVO;
	}

	public void setRelatorioEstadoBottonVO(RelatorioVO[] relatorioEstadoBottonVO) {
		this.relatorioEstadoBottonVO = relatorioEstadoBottonVO;
	}

	public RelatorioVO[] getRelatorioEstadoBottonVO() {
		return this.relatorioEstadoBottonVO;
	}

	public void setRelatorioAtendenteVO(RelatorioVO[] relatorioAtendenteVO) {
		this.relatorioAtendenteVO = relatorioAtendenteVO;
	}

	public RelatorioVO[] getRelatorioAtendenteVO() {
		return this.relatorioAtendenteVO;
	}
}