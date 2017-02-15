package admsistemas.AdmFormularios.formBeans;

import org.apache.struts.action.ActionForm;
import br.com.vivo.fo.admsistemas.vo.CarterizacaoVODocument.CarterizacaoVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoSelecionadoVODocument.RegrasEncaminhamentoSelecionadoVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoVODocument.RegrasEncaminhamentoVO;
import br.com.vivo.fo.admsistemas.vo.SegmentacaoVODocument.SegmentacaoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.WFRegionalVODocument.WFRegionalVO;

public class RegrasEncaminhamentoForm extends ActionForm {

	private static final long serialVersionUID = -7835721414703448872L;

	private String idFormulario;
	private String dsOperacao;

	private RegrasEncaminhamentoVO listaRegrasEncaminhamento;
	private RegrasEncaminhamentoSelecionadoVO regraEncaminhamento;
	private MonitoramentoPesquisaVO filtrosListas;

	private String[] regionaisSelecionadas;
	private String[] carteirasSelecionadas;
	private String[] segmentacoesSelecionadas;

	private CarterizacaoVO[] listaCarteirasSelecionadas;
	private SegmentacaoVO[] listaSegmentacoesSelecionadas;
	private WFRegionalVO[] listaRegionaisSelecionadas;

	public CarterizacaoVO[] getListaCarteirasSelecionadas() {
		if (this.listaCarteirasSelecionadas == null) {
			this.listaCarteirasSelecionadas = new CarterizacaoVO[0];
		}
		return this.listaCarteirasSelecionadas;
	}

	public void setListaCarteirasSelecionadas(CarterizacaoVO[] arg) {
		this.listaCarteirasSelecionadas = arg;
	}

	public SegmentacaoVO[] getListaSegmentacoesSelecionadas() {
		if (this.listaSegmentacoesSelecionadas == null) {
			this.listaSegmentacoesSelecionadas = new SegmentacaoVO[0];
		}
		return this.listaSegmentacoesSelecionadas;
	}

	public void setListaSegmentacoesSelecionadas(SegmentacaoVO[] arg) {
		this.listaSegmentacoesSelecionadas = arg;
	}

	public WFRegionalVO[] getListaRegionaisSelecionadas() {
		if (this.listaRegionaisSelecionadas == null) {
			this.listaRegionaisSelecionadas = new WFRegionalVO[0];
		}
		return this.listaRegionaisSelecionadas;
	}

	public void setListaRegionaisSelecionadas(WFRegionalVO[] arg) {
		this.listaRegionaisSelecionadas = arg;
	}

	public String[] getRegionaisSelecionadas() {
		if (this.regionaisSelecionadas == null) {
			this.regionaisSelecionadas = new String[0];
		}
		return this.regionaisSelecionadas;
	}

	public void setRegionaisSelecionadas(String[] arg) {
		this.regionaisSelecionadas = arg;
	}

	public String[] getCarteirasSelecionadas() {
		if (this.carteirasSelecionadas == null) {
			this.carteirasSelecionadas = new String[0];
		}
		return this.carteirasSelecionadas;
	}

	public void setCarteirasSelecionadas(String[] arg) {
		this.carteirasSelecionadas = arg;
	}

	public String[] getSegmentacoesSelecionadas() {
		if (this.segmentacoesSelecionadas == null) {
			this.segmentacoesSelecionadas = new String[0];
		}
		return this.segmentacoesSelecionadas;
	}

	public void setSegmentacoesSelecionadas(String[] arg) {
		this.segmentacoesSelecionadas = arg;
	}

	public RegrasEncaminhamentoVO getListaRegrasEncaminhamento() {
		if (this.listaRegrasEncaminhamento == null) {
			this.listaRegrasEncaminhamento = RegrasEncaminhamentoVO.Factory.newInstance();
		}
		return this.listaRegrasEncaminhamento;
	}

	public void setListaRegrasEncaminhamento(RegrasEncaminhamentoVO arg) {
		this.listaRegrasEncaminhamento = arg;
	}

	public RegrasEncaminhamentoSelecionadoVO getRegraEncaminhamento() {
		if (this.regraEncaminhamento == null) {
			this.regraEncaminhamento = RegrasEncaminhamentoSelecionadoVO.Factory.newInstance();
			this.regraEncaminhamento.addNewDestinoVO();
		}
		return this.regraEncaminhamento;
	}

	public void setRegraEncaminhamento(RegrasEncaminhamentoSelecionadoVO arg) {
		this.regraEncaminhamento = arg;
	}

	public MonitoramentoPesquisaVO getFiltrosListas() {
		if (this.filtrosListas == null) {
			this.filtrosListas = MonitoramentoPesquisaVO.Factory.newInstance();
		}
		return this.filtrosListas;
	}

	public void setFiltrosListas(MonitoramentoPesquisaVO filtrosListas) {
		this.filtrosListas = filtrosListas;
	}

	public String getIdFormulario() {
		if (this.idFormulario == null) {
			this.idFormulario = ConstantesCRM.SVAZIO;
		}
		return this.idFormulario;
	}

	public void setIdFormulario(String arg) {
		this.idFormulario = arg;
	}

	public String getDsOperacao() {
		if (this.dsOperacao == null) {
			this.dsOperacao = ConstantesCRM.SVAZIO;
		}
		return this.dsOperacao;
	}

	public void setDsOperacao(String arg) {
		this.dsOperacao = arg;
	}
}