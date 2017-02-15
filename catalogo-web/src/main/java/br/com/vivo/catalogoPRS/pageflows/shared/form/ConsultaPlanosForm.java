package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.ws.catalogoPlano.sn.ResultadoBuscarPlanoListaPlanoRetornoPlano;
import br.com.vivo.catalogoPRS.ws.catalogoUF.sn.ResultadoBuscarListaUFUF;

public class ConsultaPlanosForm extends ValidatorActionForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private Long   paginaSolicitada;
	private String nomePlano;
	private String codigoPlano;
	private Long   idTipoCliente;
	private Long   idPlataforma;
	private Long   idTecnologia;
	private Long   idSistema;
	private String ufs;
	
	private String tiposCliente;
	private String plataformas;
	private String canais;
	private String segmentos;
	private String carteiras;
	private String DDDs;
	
	private List<ResultadoBuscarListaUFUF> ufsList;
	
	private List<ResultadoBuscarPlanoListaPlanoRetornoPlano> retornoPlanoList;
	
	private List<ResultadoBuscarListaUFUF> ufList;
	
	private Long[] idsPlanos;
	
	private Long   idsVariaveis;
	
	private Long   idPlano;
	private String descricaoPlano;
	
	private String[] ufsSelecionados;
	
	private String[] dddsSelecionados;

	private String[] tiposClienteSelecionados;

	private String[] plataformasSelecionadas;

	private String[] canaisSelecionados;

	private String[] segmentosSelecionados;

	private String[] carteirasSelecionadas;

	private String[] dddSelecionados;
	
	private Map<String, String> mapaDataIncial; 
    private Map<String, String> mapaDataFinal; 

	
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public String getNomePlano() {
		return nomePlano;
	}
	public void setNomePlano(String nomePlano) {
		this.nomePlano = nomePlano;
	}
	public String getCodigoPlano() {
		return codigoPlano;
	}
	public void setCodigoPlano(String codigoPlano) {
		this.codigoPlano = codigoPlano;
	}
	public Long getIdTipoCliente() {
		return idTipoCliente;
	}
	public void setIdTipoCliente(Long idTipoCliente) {
		this.idTipoCliente = idTipoCliente;
	}
	public Long getIdPlataforma() {
		return idPlataforma;
	}
	public void setIdPlataforma(Long idPlataforma) {
		this.idPlataforma = idPlataforma;
	}
	public Long getIdTecnologia() {
		return idTecnologia;
	}
	public void setIdTecnologia(Long idTecnologia) {
		this.idTecnologia = idTecnologia;
	}
	public Long getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(Long idSistema) {
		this.idSistema = idSistema;
	}
	public String getUfs() {
		return ufs;
	}
	public void setUfs(String ufs) {
		this.ufs = ufs;
	}
	public String getTiposCliente() {
		return tiposCliente;
	}
	public void setTiposCliente(String tiposCliente) {
		this.tiposCliente = tiposCliente;
	}
	public String getPlataformas() {
		return plataformas;
	}
	public void setPlataformas(String plataformas) {
		this.plataformas = plataformas;
	}
	public String getCanais() {
		return canais;
	}
	public void setCanais(String canais) {
		this.canais = canais;
	}
	public String getSegmentos() {
		return segmentos;
	}
	public void setSegmentos(String segmentos) {
		this.segmentos = segmentos;
	}
	public String getCarteiras() {
		return carteiras;
	}
	public void setCarteiras(String carteiras) {
		this.carteiras = carteiras;
	}
	public String getDDDs() {
		return DDDs;
	}
	public void setDDDs(String dDDs) {
		DDDs = dDDs;
	}
	public Long[] getIdsPlanos() {
		return idsPlanos;
	}
	public void setIdsPlanos(Long[] idsPlanos) {
		this.idsPlanos = idsPlanos;
	}
	public Long getIdsVariaveis() {
		return idsVariaveis;
	}
	public void setIdsVariaveis(Long idsVariaveis) {
		this.idsVariaveis = idsVariaveis;
	}
	public Long getIdPlano() {
		return idPlano;
	}
	public void setIdPlano(Long idPlano) {
		this.idPlano = idPlano;
	}
	public String getDescricaoPlano() {
		return descricaoPlano;
	}
	public void setDescricaoPlano(String descricaoPlano) {
		this.descricaoPlano = descricaoPlano;
	}
	public String[] getDddsSelecionados() {
		return dddsSelecionados;
	}

	public void setDddsSelecionados(String[] dddsSelecionados) {
		this.dddsSelecionados = dddsSelecionados;
	}

	public String[] getUfsSelecionados() {
		return ufsSelecionados;
	}

	public void setUfsSelecionados(String[] ufsSelecionados) {
		this.ufsSelecionados = ufsSelecionados;
	}

	public String[] getCanaisSelecionados() {
		return canaisSelecionados;
	}

	public void setCanaisSelecionados(String[] canaisSelecionados) {
		this.canaisSelecionados = canaisSelecionados;
	}

	public String[] getCarteirasSelecionadas() {
		return carteirasSelecionadas;
	}

	public void setCarteirasSelecionadas(String[] carteirasSelecionadas) {
		this.carteirasSelecionadas = carteirasSelecionadas;
	}

	public String[] getDddSelecionados() {
		return dddSelecionados;
	}

	public void setDddSelecionados(String[] dddSelecionados) {
		this.dddSelecionados = dddSelecionados;
	}

	public String[] getPlataformasSelecionadas() {
		return plataformasSelecionadas;
	}

	public void setPlataformasSelecionadas(String[] plataformasSelecionadas) {
		this.plataformasSelecionadas = plataformasSelecionadas;
	}

	public String[] getSegmentosSelecionados() {
		return segmentosSelecionados;
	}

	public void setSegmentosSelecionados(String[] segmentosSelecionados) {
		this.segmentosSelecionados = segmentosSelecionados;
	}

	public String[] getTiposClienteSelecionados() {
		return tiposClienteSelecionados;
	}

	public void setTiposClienteSelecionados(String[] tiposClienteSelecionados) {
		this.tiposClienteSelecionados = tiposClienteSelecionados;
	}
	public List<ResultadoBuscarListaUFUF> getUfsList() {
		return ufsList;
	}
	public void setUfsList(List<ResultadoBuscarListaUFUF> ufsList) {
		this.ufsList = ufsList;
	}
	public List<ResultadoBuscarPlanoListaPlanoRetornoPlano> getRetornoPlanoList() {
		return retornoPlanoList;
	}
	public void setRetornoPlanoList(List<ResultadoBuscarPlanoListaPlanoRetornoPlano> retornoPlanoList) {
		this.retornoPlanoList = retornoPlanoList;
	}
	public Map<String, String> getMapaDataIncial() {
		return mapaDataIncial;
	}
	public void setMapaDataIncial(Map<String, String> mapaDataIncial) {
		this.mapaDataIncial = mapaDataIncial;
	}
	public Map<String, String> getMapaDataFinal() {
		return mapaDataFinal;
	}
	public void setMapaDataFinal(Map<String, String> mapaDataFinal) {
		this.mapaDataFinal = mapaDataFinal;
	}
	public List<ResultadoBuscarListaUFUF> getUfList() {
		return ufList;
	}
	public void setUfList(List<ResultadoBuscarListaUFUF> ufList) {
		this.ufList = ufList;
	}
}
