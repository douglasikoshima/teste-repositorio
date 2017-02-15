package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Caracteristica;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaSistemaProdutoSistemaProduto;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaTecnologiaTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaTipoFrequenciaTipoFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaValorFrequenciaValorFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ValorCaracteristica;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultBuscarListaMultTMCorClassMultimidia;
import br.com.vivo.catalogoPRS.ws.catalogoTecnologia.sn.ResultadoBuscarListaTecnologiaTecnologia;

public class ConsultaModeloForm extends ValidatorActionForm implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1498418028463963883L;

	private Long tipoProduto;

	private Long fabricante;

	private String produto;

	private String tecnologias;
	
	private String caracteristicas;
	
	private List<Caracteristica> listaCaracteristicas;

	private String valoresCaracteristicas;

	private String modelo;

	private String periodoInicio;

	private String periodoFim;
	
	private String nomesTecnologias;
	
	private String nomesCaracteristicasValores;
	
	private Long paginaSolicitada;
	
	private ResultadoBuscarListaTecnologiaTecnologia[] tecnologiasEncontradas;
	
	private String[] tecnologiasSelecionadas;

	private String[] caracteristicasSelecionadas;

	private String[] valoresCaracteristicasSelecionadas;
	
	private String[] idGrupoProdutoSelecionados;
	
	private List modelos;
	
	private Long[] idsModelos;
	
	private String[] hiddenTecnologias;

	private String caminho_link_imagens_modelo;
	
	private ListaTipoFrequenciaTipoFrequencia[] listaTipoFrequencia;
	
	private ListaValorFrequenciaValorFrequencia[] listaValorFrequencia;
	
	private ListaTecnologiaTecnologia[] listaTecnologia;
	
	private Caracteristica[] caracteristicaList;
	
	private ValorCaracteristica[] listaValorCaracteristica;
	
	private ListaSistemaProdutoSistemaProduto[] sistemaProdutoList;
	
	private String nmFabricante;
	
	private ResultBuscarListaMultTMCorClassMultimidia[] multimidiaList;
	
	

	public Long[] getIdsModelos() {
		return idsModelos;
	}

	public void setIdsModelos(Long[] idsModelos) {
		this.idsModelos = idsModelos;
	}

	public ResultadoBuscarListaTecnologiaTecnologia[] getTecnologiasEncontradas() {
		return tecnologiasEncontradas;
	}

	public void setTecnologiasEncontradas(ResultadoBuscarListaTecnologiaTecnologia[] tecnologiasEncontradas) {
		this.tecnologiasEncontradas = tecnologiasEncontradas;
	}

	public String[] getIdGrupoProdutoSelecionados() {
		return idGrupoProdutoSelecionados;
	}

	public void setIdGrupoProdutoSelecionados(String[] idGrupoProdutoSelecionados) {
		this.idGrupoProdutoSelecionados = idGrupoProdutoSelecionados;
	}

	public List getModelos() {
		return modelos;
	}

	public void setModelos(List modelos) {
		this.modelos = modelos;
	}

	
	public List<Caracteristica> getListaCaracteristicas() {
		return listaCaracteristicas;
	}

	public void setListaCaracteristicas(List<Caracteristica> listaCaracteristicas) {
		this.listaCaracteristicas = listaCaracteristicas;
	}

	public String[] getCaracteristicasSelecionadas() {
		return caracteristicasSelecionadas;
	}

	public void setCaracteristicasSelecionadas(String[] caracteristicasSelecionadas) {
		this.caracteristicasSelecionadas = caracteristicasSelecionadas;
	}

	public String[] getValoresCaracteristicasSelecionadas() {
		return valoresCaracteristicasSelecionadas;
	}

	public void setValoresCaracteristicasSelecionadas(String[] valoresCaracteristicasSelecionadas) {
		this.valoresCaracteristicasSelecionadas = valoresCaracteristicasSelecionadas;
	}

	public Long getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(Long tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public Long getFabricante() {
		return fabricante;
	}

	public void setFabricante(Long fabricante) {
		this.fabricante = fabricante;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	public String getCaracteristicas() {
		return caracteristicas;
	}

	public void setCaracteristicas(String caracteristicas) {
		this.caracteristicas = caracteristicas;
	}

	public String getValoresCaracteristicas() {
		return valoresCaracteristicas;
	}

	public void setValoresCaracteristicas(String valoresCaracteristicas) {
		this.valoresCaracteristicas = valoresCaracteristicas;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getPeriodoInicio() {
		return periodoInicio;
	}

	public void setPeriodoInicio(String periodoInicio) {
		this.periodoInicio = periodoInicio;
	}

	public String getPeriodoFim() {
		return periodoFim;
	}

	public void setPeriodoFim(String periodoFim) {
		this.periodoFim = periodoFim;
	}

	public String getNomesTecnologias() {
		return nomesTecnologias;
	}

	public void setNomesTecnologias(String nomesTecnologias) {
		this.nomesTecnologias = nomesTecnologias;
	}

	public String getNomesCaracteristicasValores() {
		return nomesCaracteristicasValores;
	}

	public void setNomesCaracteristicasValores(String nomesCaracteristicasValores) {
		this.nomesCaracteristicasValores = nomesCaracteristicasValores;
	}

	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}

	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}

	public String getTecnologias() {
		return tecnologias;
	}

	public void setTecnologias(String tecnologias) {
		this.tecnologias = tecnologias;
	}

	public String[] getHiddenTecnologias() {
		return hiddenTecnologias;
	}

	public void setHiddenTecnologias(String[] hiddenTecnologias) {
		this.hiddenTecnologias = hiddenTecnologias;
	}

	public String[] getTecnologiasSelecionadas() {
		return tecnologiasSelecionadas;
	}

	public void setTecnologiasSelecionadas(String[] tecnologiasSelecionadas) {
		this.tecnologiasSelecionadas = tecnologiasSelecionadas;
	}

	public String getCaminho_link_imagens_modelo() {
		return caminho_link_imagens_modelo;
	}

	public void setCaminho_link_imagens_modelo(String caminho_link_imagens_modelo) {
		this.caminho_link_imagens_modelo = caminho_link_imagens_modelo;
	}

	public ListaTipoFrequenciaTipoFrequencia[] getListaTipoFrequencia() {
		return listaTipoFrequencia;
	}

	public void setListaTipoFrequencia(ListaTipoFrequenciaTipoFrequencia[] listaTipoFrequencia) {
		this.listaTipoFrequencia = listaTipoFrequencia;
	}

	public ListaValorFrequenciaValorFrequencia[] getListaValorFrequencia() {
		return listaValorFrequencia;
	}

	public void setListaValorFrequencia(ListaValorFrequenciaValorFrequencia[] listaValorFrequencia) {
		this.listaValorFrequencia = listaValorFrequencia;
	}

	public ListaTecnologiaTecnologia[] getListaTecnologia() {
		return listaTecnologia;
	}

	public void setListaTecnologia(ListaTecnologiaTecnologia[] listaTecnologia) {
		this.listaTecnologia = listaTecnologia;
	}

	public Caracteristica[] getCaracteristicaList() {
		return caracteristicaList;
	}

	public void setCaracteristicaList(Caracteristica[] caracteristicaList) {
		this.caracteristicaList = caracteristicaList;
	}

	public ValorCaracteristica[] getListaValorCaracteristica() {
		return listaValorCaracteristica;
	}

	public void setListaValorCaracteristica(ValorCaracteristica[] listaValorCaracteristica) {
		this.listaValorCaracteristica = listaValorCaracteristica;
	}

	public ListaSistemaProdutoSistemaProduto[] getSistemaProdutoList() {
		return sistemaProdutoList;
	}

	public void setSistemaProdutoList(ListaSistemaProdutoSistemaProduto[] sistemaProdutoList) {
		this.sistemaProdutoList = sistemaProdutoList;
	}

	public String getNmFabricante() {
		return nmFabricante;
	}

	public void setNmFabricante(String nmFabricante) {
		this.nmFabricante = nmFabricante;
	}

	public ResultBuscarListaMultTMCorClassMultimidia[] getMultimidiaList() {
		return multimidiaList;
	}

	public void setMultimidiaList(ResultBuscarListaMultTMCorClassMultimidia[] multimidiaList) {
		this.multimidiaList = multimidiaList;
	}
	
	

}
