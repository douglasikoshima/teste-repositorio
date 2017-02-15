package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.ValorCaracteristicaTO;

public class CaracteristicaForm extends ValidatorActionForm implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long paginaSolicitada;
	private Boolean cadastrar;
	
	private Integer idCaracteristica;
	private String nmCaracteristicaSearch;
	private Integer idGrupoCaracteristicaSearch;
	private String inDisponivelSearch;
	private String indComparavelSearch;
	private String indExibivelSearch;
	private String inSimuladorSearch;
	
	private Integer idGrupoCaracteristicaCadastro;
	private String nmCaracteristicaCadastro;
	private String descricaoCadastro;
	private String inDisponivelCadastro;
	private String indComparavelCadastro;
	private String indExibivelCadastro;
	private String inSimuladorCadastro;
	private Boolean existGrupoProdutoCaracteristica;
	
	private String existAssociacao;
	
	private String valor;
	private Integer idValorCaracteristica;
	
	private List<ValorCaracteristicaTO> valorCaracteristicaList;
	
	
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public Boolean getCadastrar() {
		return cadastrar;
	}
	public void setCadastrar(Boolean cadastrar) {
		this.cadastrar = cadastrar;
	}
	public Integer getIdCaracteristica() {
		return idCaracteristica;
	}
	public void setIdCaracteristica(Integer idCaracteristica) {
		this.idCaracteristica = idCaracteristica;
	}
	public String getNmCaracteristicaSearch() {
		return nmCaracteristicaSearch;
	}
	public void setNmCaracteristicaSearch(String nmCaracteristicaSearch) {
		this.nmCaracteristicaSearch = nmCaracteristicaSearch;
	}
	public Integer getIdGrupoCaracteristicaSearch() {
		return idGrupoCaracteristicaSearch;
	}
	public void setIdGrupoCaracteristicaSearch(Integer idGrupoCaracteristicaSearch) {
		this.idGrupoCaracteristicaSearch = idGrupoCaracteristicaSearch;
	}
	public String getInDisponivelSearch() {
		return inDisponivelSearch;
	}
	public void setInDisponivelSearch(String inDisponivelSearch) {
		this.inDisponivelSearch = inDisponivelSearch;
	}
	public String getIndComparavelSearch() {
		return indComparavelSearch;
	}
	public void setIndComparavelSearch(String indComparavelSearch) {
		this.indComparavelSearch = indComparavelSearch;
	}
	public String getIndExibivelSearch() {
		return indExibivelSearch;
	}
	public void setIndExibivelSearch(String indExibivelSearch) {
		this.indExibivelSearch = indExibivelSearch;
	}
	public String getInSimuladorSearch() {
		return inSimuladorSearch;
	}
	public void setInSimuladorSearch(String inSimuladorSearch) {
		this.inSimuladorSearch = inSimuladorSearch;
	}
	public Integer getIdGrupoCaracteristicaCadastro() {
		return idGrupoCaracteristicaCadastro;
	}
	public void setIdGrupoCaracteristicaCadastro(Integer idGrupoCaracteristicaCadastro) {
		this.idGrupoCaracteristicaCadastro = idGrupoCaracteristicaCadastro;
	}
	public String getNmCaracteristicaCadastro() {
		return nmCaracteristicaCadastro;
	}
	public void setNmCaracteristicaCadastro(String nmCaracteristicaCadastro) {
		this.nmCaracteristicaCadastro = nmCaracteristicaCadastro;
	}
	public String getDescricaoCadastro() {
		return descricaoCadastro;
	}
	public void setDescricaoCadastro(String descricaoCadastro) {
		this.descricaoCadastro = descricaoCadastro;
	}
	public String getInDisponivelCadastro() {
		return inDisponivelCadastro;
	}
	public void setInDisponivelCadastro(String inDisponivelCadastro) {
		this.inDisponivelCadastro = inDisponivelCadastro;
	}
	public String getIndComparavelCadastro() {
		return indComparavelCadastro;
	}
	public void setIndComparavelCadastro(String indComparavelCadastro) {
		this.indComparavelCadastro = indComparavelCadastro;
	}
	public String getIndExibivelCadastro() {
		return indExibivelCadastro;
	}
	public void setIndExibivelCadastro(String indExibivelCadastro) {
		this.indExibivelCadastro = indExibivelCadastro;
	}
	public String getInSimuladorCadastro() {
		return inSimuladorCadastro;
	}
	public void setInSimuladorCadastro(String inSimuladorCadastro) {
		this.inSimuladorCadastro = inSimuladorCadastro;
	}
	public Boolean getExistGrupoProdutoCaracteristica() {
		return existGrupoProdutoCaracteristica;
	}
	public void setExistGrupoProdutoCaracteristica(Boolean existGrupoProdutoCaracteristica) {
		this.existGrupoProdutoCaracteristica = existGrupoProdutoCaracteristica;
	}
	public String getExistAssociacao() {
		return existAssociacao;
	}
	public void setExistAssociacao(String existAssociacao) {
		this.existAssociacao = existAssociacao;
	}
	public String getValor() {
		return valor;
	}
	public void setValor(String valor) {
		this.valor = valor;
	}
	public Integer getIdValorCaracteristica() {
		return idValorCaracteristica;
	}
	public void setIdValorCaracteristica(Integer idValorCaracteristica) {
		this.idValorCaracteristica = idValorCaracteristica;
	}
	public List<ValorCaracteristicaTO> getValorCaracteristicaList() {
		return valorCaracteristicaList;
	}
	public void setValorCaracteristicaList(List<ValorCaracteristicaTO> valorCaracteristicaList) {
		this.valorCaracteristicaList = valorCaracteristicaList;
	}
}
