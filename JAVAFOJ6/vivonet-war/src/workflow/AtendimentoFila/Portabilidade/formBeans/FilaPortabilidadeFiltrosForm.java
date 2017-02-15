package workflow.AtendimentoFila.Portabilidade.formBeans;

import org.apache.struts.action.ActionForm;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoFilaPesquisaVODocument.AtendimentoFilaPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO;
import br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO;


public class FilaPortabilidadeFiltrosForm extends ActionForm {

	private static final long serialVersionUID = -4890023465356347346L;

	private MonitoramentoPesquisaVO filtrosListas;
	private AtendimentoFilaPesquisaVO filtrosPesquisa;
	private AtendimentoInformacaoVO resultadoPesquisa;

	private String idCarteira;
	private String idSegmento;
	private String idEstado;
	private String idSubestado;
	private String idTipoAlerta;
	private String idRegional;
	private String inPeriodo;
	private String idTipoPessoa;
	private String idTipoLinha;

	private boolean inVoltar = false;

	private WFEstadosVO listaEstadosSubestados;

	private AlertaVO[] listaAlertas;

	public MonitoramentoPesquisaVO getFiltrosListas() {
		if (this.filtrosListas == null) {
			this.filtrosListas = MonitoramentoPesquisaVO.Factory.newInstance();
		}
		return this.filtrosListas;
	}

	public void setFiltrosListas(MonitoramentoPesquisaVO filtrosListas) {
		this.filtrosListas = filtrosListas;
	}

	public AtendimentoFilaPesquisaVO getFiltrosPesquisa() {
		if (this.filtrosPesquisa == null) {
			this.filtrosPesquisa = AtendimentoFilaPesquisaVO.Factory.newInstance();
		}
		return this.filtrosPesquisa;
	}

	public void setFiltrosPesquisa(AtendimentoFilaPesquisaVO filtrosPesquisa) {
		this.filtrosPesquisa = filtrosPesquisa;
	}

	public void setResultadoPesquisa(AtendimentoInformacaoVO resultadoPesquisa) {
		this.resultadoPesquisa = resultadoPesquisa;
	}

	public AtendimentoInformacaoVO getResultadoPesquisa() {
		if (this.resultadoPesquisa == null) {
			this.resultadoPesquisa = AtendimentoInformacaoVO.Factory.newInstance();
		}
		return this.resultadoPesquisa;
	}

	public WFEstadosVO getListaEstadosSubestados() {
		if (this.listaEstadosSubestados == null) {
			this.listaEstadosSubestados = WFEstadosVO.Factory.newInstance();
		}
		return this.listaEstadosSubestados;
	}

	public void setListaEstadosSubestados(WFEstadosVO listaEstadosSubestados) {
		this.listaEstadosSubestados = listaEstadosSubestados;
	}

	public String getIdCarteira() {
		if (this.idCarteira == null) {
			idCarteira = ConstantesCRM.SVAZIO;
		}
		return this.idCarteira;
	}

	public void setIdCarteira(String idCarteira) {
		this.idCarteira = idCarteira;
	}

	public String getIdEstado() {
		if (this.idEstado == null) {
			idEstado = ConstantesCRM.SVAZIO;
		}
		return idEstado;
	}

	public void setIdEstado(String idEstado) {
		this.idEstado = idEstado;
	}

	public String getIdRegional() {
		if (this.idRegional == null) {
			idRegional = ConstantesCRM.SVAZIO;
		}
		return idRegional;
	}

	public void setInPeriodo(String inPeriodo) {
		this.inPeriodo = inPeriodo;
	}

	public String getInPeriodo() {
		if (this.inPeriodo == null) {
			inPeriodo = ConstantesCRM.SVAZIO;
		}
		return idRegional;
	}

	public void setIdRegional(String idRegional) {
		this.idRegional = idRegional;
	}

	public String getIdSegmento() {
		if (this.idSegmento == null) {
			idSegmento = ConstantesCRM.SVAZIO;
		}
		return idSegmento;
	}

	public void setIdSegmento(String idSegmento) {
		this.idSegmento = idSegmento;
	}

	public String getIdTipoPessoa() {
		if (this.idTipoPessoa == null) {
			idTipoPessoa = ConstantesCRM.SVAZIO;
		}
		return idTipoPessoa;
	}

	public void setIdTipoPessoa(String arg1) {
		this.idTipoPessoa = arg1;
	}

	public String getIdTipoLinha() {
		if (this.idTipoLinha == null) {
			idTipoLinha = ConstantesCRM.SVAZIO;
		}
		return idTipoLinha;
	}

	public void setIdTipoLinha(String arg1) {
		this.idTipoLinha = arg1;
	}

	public String getIdSubestado() {
		if (this.idSubestado == null) {
			idSubestado = ConstantesCRM.SVAZIO;
		}
		return idSubestado;
	}

	public void setIdSubestado(String idSubestado) {
		this.idSubestado = idSubestado;
	}

	public String getIdTipoAlerta() {
		if (this.idTipoAlerta == null) {
			idTipoAlerta = ConstantesCRM.SVAZIO;
		}
		return idTipoAlerta;
	}

	public void setIdTipoAlerta(String idTipoAlerta) {
		this.idTipoAlerta = idTipoAlerta;
	}

	public boolean getInVoltar() {
		return inVoltar;
	}

	public void setInVoltar(boolean inVoltar) {
		this.inVoltar = inVoltar;
	}

	public void setListaAlertas(AlertaVO[] arg1) {
		this.listaAlertas = arg1;
	}

	public AlertaVO[] getListaAlertas() {
		return this.listaAlertas;
	}

}