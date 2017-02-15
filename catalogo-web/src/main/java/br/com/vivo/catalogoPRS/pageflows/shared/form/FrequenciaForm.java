package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaModeloPorValorFrequenciaModeloPorValorFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoListarValorFrequenciaVlfrequencia;

public class FrequenciaForm extends ValidatorActionForm  implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	private String valorFrequencia;
	private Long idEntidade;
	private String justificativa;
	
	private List<ResultadoListarValorFrequenciaVlfrequencia> vlfrequenciaList;
	List<ResultadoBuscarListaModeloPorValorFrequenciaModeloPorValorFrequencia> modeloPorValorFrequenciaList = null;

	public String getValorFrequencia() {
		return valorFrequencia;
	}
	public void setValorFrequencia(String valorFrequencia) {
		this.valorFrequencia = valorFrequencia;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	public Long getIdEntidade() {
		return idEntidade;
	}
	public void setIdEntidade(Long idEntidade) {
		this.idEntidade = idEntidade;
	}
	public List<ResultadoListarValorFrequenciaVlfrequencia> getVlfrequenciaList() {
		return vlfrequenciaList;
	}
	public void setVlfrequenciaList(List<ResultadoListarValorFrequenciaVlfrequencia> vlfrequenciaList) {
		this.vlfrequenciaList = vlfrequenciaList;
	}
	public List<ResultadoBuscarListaModeloPorValorFrequenciaModeloPorValorFrequencia> getModeloPorValorFrequenciaList() {
		return modeloPorValorFrequenciaList;
	}
	public void setModeloPorValorFrequenciaList(
			List<ResultadoBuscarListaModeloPorValorFrequenciaModeloPorValorFrequencia> modeloPorValorFrequenciaList) {
		this.modeloPorValorFrequenciaList = modeloPorValorFrequenciaList;
	}
}
