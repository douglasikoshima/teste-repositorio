package admsistemas.parametrizacoes.vinculoRegional;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.workflow.vo.WFRegionalVODocument.WFRegionalVO;

public class ParametrosForm extends ActionForm {

	private static final long serialVersionUID = -3767183533374861525L;

	private WFRegionalVO[] listaRegionais;
	private WFRegionalVO[] regionaisDisponiveis;
	private WFRegionalVO[] regionaisSelecionadas;

	private String regionalSelecionada;
	private String[] selecionadas;
	private String[] disponiveis;

	public String[] getSelecionadas() {
		return selecionadas;
	}

	public void setSelecionadas(String[] selecionadas) {
		this.selecionadas = selecionadas;
	}

	public String[] getDisponiveis() {
		return disponiveis;
	}

	public void setDisponiveis(String[] disponiveis) {
		this.disponiveis = disponiveis;
	}

	public String getRegionalSelecionada() {
		return regionalSelecionada;
	}

	public void setRegionalSelecionada(String regionalSelecionada) {
		this.regionalSelecionada = regionalSelecionada;
	}

	public WFRegionalVO[] getRegionaisDisponiveis() {
		if (this.regionaisDisponiveis == null) {
			this.regionaisDisponiveis = new WFRegionalVO[0];
		}
		return this.regionaisDisponiveis;
	}

	public WFRegionalVO[] getListaRegionais() {
		if (this.listaRegionais == null) {
			this.listaRegionais = new WFRegionalVO[0];
		}
		return this.listaRegionais;
	}

	public WFRegionalVO[] getRegionaisSelecionadas() {
		if (this.regionaisSelecionadas == null) {
			this.regionaisSelecionadas = new WFRegionalVO[0];
		}
		return this.regionaisSelecionadas;
	}

	public void setRegionaisDisponiveis(WFRegionalVO[] arg) {
		this.regionaisDisponiveis = arg;
	}

	public void setListaRegionais(WFRegionalVO[] arg) {
		this.listaRegionais = arg;
	}

	public void setRegionaisSelecionadas(WFRegionalVO[] arg) {
		this.regionaisSelecionadas = arg;
	}

}