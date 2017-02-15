package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;


import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.Caracteristica;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaSistemaProdutoSistemaProduto;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaTecnologiaTecnologia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaTipoFrequenciaTipoFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ListaValorFrequenciaValorFrequencia;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModelo;
import br.com.vivo.catalogoPRS.ws.catalogoModelo.sn.ValorCaracteristica;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultBuscarListaMultTMCorClassMultimidia;
import br.com.vivo.catalogoPRS.ws.catalogoMultimidia.sn.ResultadoBuscarListaImagemPorModeloMultimidia;


public class ConsultaRestricaoModeloForm extends ValidatorActionForm implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7338720371012619041L;


	private Integer paginaSolicitada;

	private Long tipoProduto;

	private String produto;

	private String modelo;
	
	private String nmFabricante;
	
	private ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModelo[] resultadoModeloList;
	
	private ResultadoBuscarListaImagemPorModeloMultimidia[] resultadoListImgPorModeloMultimidia;
	
	private Caracteristica[] caracteristicaList;
	
	private ValorCaracteristica[] listaValorCaracteristica;
	
	private ListaSistemaProdutoSistemaProduto[] sistemaProdutoList;
	
	private ResultBuscarListaMultTMCorClassMultimidia[] multimidiaList;
	
	private ListaTipoFrequenciaTipoFrequencia[] listaTipoFrequencia;
	
	private ListaValorFrequenciaValorFrequencia[] listaValorFrequencia;
	
	
	
	private ListaTecnologiaTecnologia[] listaTecnologia;
	
	private String caminho_link_imagens_modelo;
	
	// idsModelos era do form: ExportarFormBean
	private Long[] idsModelos;
	
	public Long[] getIdsModelos() {
		return idsModelos;
	}
	public void setIdsModelos(Long[] idsModelos) {
		this.idsModelos = idsModelos;
	}

	public String getProduto() {
		return produto;
	}

	public void setProduto(String produto) {
		this.produto = produto;
	}

	//@Jpf.ValidatableProperty(displayName = "Tipo de Produto", validateRequired = @Jpf.ValidateRequired(), validateType = @Jpf.ValidateType(type = long.class), validateRange = @Jpf.ValidateRange(maxInt = 9999, minInt = 1))
	public Long getTipoProduto() {
		return tipoProduto;
	}

	public void setTipoProduto(Long tipoProduto) {
		this.tipoProduto = tipoProduto;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public Integer getPaginaSolicitada() {
		return paginaSolicitada;
	}

	public void setPaginaSolicitada(Integer paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModelo[] getResultadoModeloList() {
		return resultadoModeloList;
	}
	public void setResultadoModeloList(
			ResultadoBuscarListaRestricoesModeloListaRestricoesModeloOutListaModeloModelo[] resultadoModeloList) {
		this.resultadoModeloList = resultadoModeloList;
	}
	public ResultadoBuscarListaImagemPorModeloMultimidia[] getResultadoListImgPorModeloMultimidia() {
		return resultadoListImgPorModeloMultimidia;
	}
	public void setResultadoListImgPorModeloMultimidia(
			ResultadoBuscarListaImagemPorModeloMultimidia[] resultadoListImgPorModeloMultimidia) {
		this.resultadoListImgPorModeloMultimidia = resultadoListImgPorModeloMultimidia;
	}
	public Caracteristica[] getCaracteristicaList() {
		return caracteristicaList;
	}
	public void setCaracteristicaList(Caracteristica[] caracteristicaList) {
		this.caracteristicaList = caracteristicaList;
	}
	public ListaTecnologiaTecnologia[] getListaTecnologia() {
		return listaTecnologia;
	}
	public void setListaTecnologia(ListaTecnologiaTecnologia[] listaTecnologia) {
		this.listaTecnologia = listaTecnologia;
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
	public String getCaminho_link_imagens_modelo() {
		return caminho_link_imagens_modelo;
	}
	public void setCaminho_link_imagens_modelo(String caminho_link_imagens_modelo) {
		this.caminho_link_imagens_modelo = caminho_link_imagens_modelo;
	}

	
	
}
