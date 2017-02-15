package br.com.vivo.catalogoPRS.pageflows.shared.form;

import java.io.Serializable;
import java.util.List;

import org.apache.struts.validator.ValidatorActionForm;

import br.com.vivo.catalogoPRS.ws.catalogoProgramaPontos.sn.ResultBuscarPrecoProdutoPPListaProdutoProduto;

public class ParametrizacaoProdutosPPForm extends ValidatorActionForm implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long idTipoProduto;
	private Long idFabricante;
	private Long idModelo;
	private Long idOrgVendas;
	private Long paginaSolicitada;
	
	private String[] idsProdutosPP;
	private String opcaoSelect;
	
	private String[] idsOrgVendas;
	private String[] idsCanaisAtend;
	private String[] sgsAcoes;
	private String[] idsOrgVendasOp;
	private String[] idsCanaisAtendOp;
	private String[] sgsAcoesOp;
	
	private List<ResultBuscarPrecoProdutoPPListaProdutoProduto> produtoLista;
	
	
	public Long getIdTipoProduto() {
		return idTipoProduto;
	}
	public void setIdTipoProduto(Long idTipoProduto) {
		this.idTipoProduto = idTipoProduto;
	}
	public Long getIdFabricante() {
		return idFabricante;
	}
	public void setIdFabricante(Long idFabricante) {
		this.idFabricante = idFabricante;
	}
	public Long getIdModelo() {
		return idModelo;
	}
	public void setIdModelo(Long idModelo) {
		this.idModelo = idModelo;
	}
	public Long getIdOrgVendas() {
		return idOrgVendas;
	}
	public void setIdOrgVendas(Long idOrgVendas) {
		this.idOrgVendas = idOrgVendas;
	}
	public Long getPaginaSolicitada() {
		return paginaSolicitada;
	}
	public void setPaginaSolicitada(Long paginaSolicitada) {
		this.paginaSolicitada = paginaSolicitada;
	}
	public String[] getIdsProdutosPP() {
		return idsProdutosPP;
	}
	public void setIdsProdutosPP(String[] idsProdutosPP) {
		this.idsProdutosPP = idsProdutosPP;
	}
	public String getOpcaoSelect() {
		return opcaoSelect;
	}
	public void setOpcaoSelect(String opcaoSelect) {
		this.opcaoSelect = opcaoSelect;
	}
	public String[] getIdsOrgVendas() {
		return idsOrgVendas;
	}
	public void setIdsOrgVendas(String[] idsOrgVendas) {
		this.idsOrgVendas = idsOrgVendas;
	}
	public String[] getIdsCanaisAtend() {
		return idsCanaisAtend;
	}
	public void setIdsCanaisAtend(String[] idsCanaisAtend) {
		this.idsCanaisAtend = idsCanaisAtend;
	}
	public String[] getSgsAcoes() {
		return sgsAcoes;
	}
	public void setSgsAcoes(String[] sgsAcoes) {
		this.sgsAcoes = sgsAcoes;
	}
	public String[] getIdsOrgVendasOp() {
		return idsOrgVendasOp;
	}
	public void setIdsOrgVendasOp(String[] idsOrgVendasOp) {
		this.idsOrgVendasOp = idsOrgVendasOp;
	}
	public String[] getIdsCanaisAtendOp() {
		return idsCanaisAtendOp;
	}
	public void setIdsCanaisAtendOp(String[] idsCanaisAtendOp) {
		this.idsCanaisAtendOp = idsCanaisAtendOp;
	}
	public String[] getSgsAcoesOp() {
		return sgsAcoesOp;
	}
	public void setSgsAcoesOp(String[] sgsAcoesOp) {
		this.sgsAcoesOp = sgsAcoesOp;
	}
	public List<ResultBuscarPrecoProdutoPPListaProdutoProduto> getProdutoLista() {
		return produtoLista;
	}
	public void setProdutoLista(List<ResultBuscarPrecoProdutoPPListaProdutoProduto> produtoLista) {
		this.produtoLista = produtoLista;
	}
}
