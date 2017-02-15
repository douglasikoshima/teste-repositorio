package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaModeloPorTecnologiaTipoFrequenciaModeloPorTecnologiaTpFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao;
import br.com.vivo.catalogoPRS.ws.catalogoFrequencia.sn.ResultadoListarValorFrequenciaVlfrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaModeloPorTecnologiaTpFrequenciaVlListaModelosPorIdModeloOutModeloPorIdModelo;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ListaTecnologiaTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaSimplesTecnologia;

public class AssociacaoFrequenciaForm extends ValidatorActionForm  implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long idTipoFrequencia;
	private Long idTecnologia;
	private Long idTecnologiaTipoFrequencia;
	private Long[] idsValoresFrequencias;
	private Long idTecnologiaTipoFrequenciaVl;
	private Long idValorFrequencia;
	private Long[] idsModelos;	
	private Long idEntidade;
	private String justificativa;
	private Long[] frequenciasSelecionadas;
	
	private List<ResultadoBuscarListaTecnologiaSimplesTecnologia> tecnologiaList;
	private List<ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao> tipoFrequenciaCriacaoList;
	private List<ListaTecnologiaTecnologia> tecnologiaTipoFrequenciaList;
	private List<ResultadoListarValorFrequenciaVlfrequencia> todosVlfrequenciaList;
	private List<ResultadoBuscarListaModeloPorTecnologiaTipoFrequenciaModeloPorTecnologiaTpFrequencia> apagarModeloAfetados;
	private List<ResultadoBuscarListaModeloPorTecnologiaTpFrequenciaVlListaModelosPorIdModeloOutModeloPorIdModelo> alterarModeloAfetados;

	public Long getIdTecnologia() {
		return idTecnologia;
	}
	public void setIdTecnologia(Long idTecnologia) {
		this.idTecnologia = idTecnologia;
	}
	public Long getIdTipoFrequencia() {
		return idTipoFrequencia;
	}
	public void setIdTipoFrequencia(Long idTipoFrequencia) {
		this.idTipoFrequencia = idTipoFrequencia;
	}
	public Long getIdTecnologiaTipoFrequencia() {
		return idTecnologiaTipoFrequencia;
	}
	public void setIdTecnologiaTipoFrequencia(Long idTecnologiaTipoFrequencia) {
		this.idTecnologiaTipoFrequencia = idTecnologiaTipoFrequencia;
	}
	public Long[] getIdsValoresFrequencias() {
		return idsValoresFrequencias;
	}
	public void setIdsValoresFrequencias(Long[] idsValoresFrequencias) {
		this.idsValoresFrequencias = idsValoresFrequencias;
	}
	public String getJustificativa() {
		return justificativa;
	}
	public void setJustificativa(String justificativa) {
		this.justificativa = justificativa;
	}
	public Long[] getIdsModelos() {
		return idsModelos;
	}
	public void setIdsModelos(Long[] idsModelos) {
		this.idsModelos = idsModelos;
	}
	public Long getIdTecnologiaTipoFrequenciaVl() {
		return idTecnologiaTipoFrequenciaVl;
	}
	public void setIdTecnologiaTipoFrequenciaVl(Long idTecnologiaTipoFrequenciaVl) {
		this.idTecnologiaTipoFrequenciaVl = idTecnologiaTipoFrequenciaVl;
	}
	public Long getIdValorFrequencia() {
		return idValorFrequencia;
	}
	public void setIdValorFrequencia(Long idValorFrequencia) {
		this.idValorFrequencia = idValorFrequencia;
	}
	public Long getIdEntidade() {
		return idEntidade;
	}
	public void setIdEntidade(Long idEntidade) {
		this.idEntidade = idEntidade;
	}
	public Long[] getFrequenciasSelecionadas() {
		return frequenciasSelecionadas;
	}
	public void setFrequenciasSelecionadas(Long[] frequenciasSelecionadas) {
		this.frequenciasSelecionadas = frequenciasSelecionadas;
	}
	public List<ResultadoBuscarListaTecnologiaSimplesTecnologia> getTecnologiaList() {
		return tecnologiaList;
	}
	public void setTecnologiaList(List<ResultadoBuscarListaTecnologiaSimplesTecnologia> tecnologiaList) {
		this.tecnologiaList = tecnologiaList;
	}
	public List<ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao> getTipoFrequenciaCriacaoList() {
		return tipoFrequenciaCriacaoList;
	}
	public void setTipoFrequenciaCriacaoList(
			List<ResultadoBuscarListaTipoFrequenciaTipoFrequenciaCriacao> tipoFrequenciaCriacaoList) {
		this.tipoFrequenciaCriacaoList = tipoFrequenciaCriacaoList;
	}
	public List<ListaTecnologiaTecnologia> getTecnologiaTipoFrequenciaList() {
		return tecnologiaTipoFrequenciaList;
	}
	public void setTecnologiaTipoFrequenciaList(List<ListaTecnologiaTecnologia> tecnologiaTipoFrequenciaList) {
		this.tecnologiaTipoFrequenciaList = tecnologiaTipoFrequenciaList;
	}
	public List<ResultadoListarValorFrequenciaVlfrequencia> getTodosVlfrequenciaList() {
		return todosVlfrequenciaList;
	}
	public void setTodosVlfrequenciaList(List<ResultadoListarValorFrequenciaVlfrequencia> todosVlfrequenciaList) {
		this.todosVlfrequenciaList = todosVlfrequenciaList;
	}
	public List<ResultadoBuscarListaModeloPorTecnologiaTipoFrequenciaModeloPorTecnologiaTpFrequencia> getApagarModeloAfetados() {
		return apagarModeloAfetados;
	}
	public void setApagarModeloAfetados(
			List<ResultadoBuscarListaModeloPorTecnologiaTipoFrequenciaModeloPorTecnologiaTpFrequencia> apagarModeloAfetados) {
		this.apagarModeloAfetados = apagarModeloAfetados;
	}
	public List<ResultadoBuscarListaModeloPorTecnologiaTpFrequenciaVlListaModelosPorIdModeloOutModeloPorIdModelo> getAlterarModeloAfetados() {
		return alterarModeloAfetados;
	}
	public void setAlterarModeloAfetados(
			List<ResultadoBuscarListaModeloPorTecnologiaTpFrequenciaVlListaModelosPorIdModeloOutModeloPorIdModelo> alterarModeloAfetados) {
		this.alterarModeloAfetados = alterarModeloAfetados;
	}
}
