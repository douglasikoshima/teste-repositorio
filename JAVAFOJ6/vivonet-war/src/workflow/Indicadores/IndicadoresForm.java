package workflow.Indicadores;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.WFIndicadoresPesquisaVODocument.WFIndicadoresPesquisaVO;
import br.com.vivo.fo.workflow.vo.WFIndicadoresVODocument.WFIndicadoresVO;

public class IndicadoresForm extends ActionForm {

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