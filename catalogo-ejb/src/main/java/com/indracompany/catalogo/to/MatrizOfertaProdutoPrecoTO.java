package com.indracompany.catalogo.to;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

public class MatrizOfertaProdutoPrecoTO {
	
	private Long idMatrizOfertaItemPreco;
	private ProdutoTO produtoTO;
	private OrganizacaoVendaTO organizacaoVendaTO;
	private CanalAtendimentoTO canalAtendimentoTO;
	private CanalTO canalTO;
	private EscritorioVendaTO escritorioVendaTO;
	private OfertaSAPTO ofertaSAPTO;
	private AcaoTO acaoTO;
	private String cdAdabas;
	private String nmUsuarioCriacao;
	private String nmUsuarioAlteracao;
	private Long codigoArea;
	private Calendar dtCriacao;
	private Calendar dtUltimaAlteracao;
	private Calendar dtInicial;
	private Calendar dtFinal;
	private BigDecimal valor;
	
	private List<Integer> idOfertaSAPListParam;
	private List<Integer> idOrganizacaoVendasListParam;
	
	
	public AcaoTO getAcaoTO() {
		return acaoTO;
	}
	public void setAcaoTO(AcaoTO acaoTO) {
		this.acaoTO = acaoTO;
	}
	public CanalAtendimentoTO getCanalAtendimentoTO() {
		return canalAtendimentoTO;
	}
	public void setCanalAtendimentoTO(CanalAtendimentoTO canalAtendimentoTO) {
		this.canalAtendimentoTO = canalAtendimentoTO;
	}
	public CanalTO getCanalTO() {
		return canalTO;
	}
	public void setCanalTO(CanalTO canalTO) {
		this.canalTO = canalTO;
	}
	public String getCdAdabas() {
		return cdAdabas;
	}
	public void setCdAdabas(String cdAdabas) {
		this.cdAdabas = cdAdabas;
	}
	public Long getCodigoArea() {
		return codigoArea;
	}
	public void setCodigoArea(Long codigoArea) {
		this.codigoArea = codigoArea;
	}
	public Calendar getDtCriacao() {
		return dtCriacao;
	}
	public void setDtCriacao(Calendar dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	public Calendar getDtFinal() {
		return dtFinal;
	}
	public void setDtFinal(Calendar dtFinal) {
		this.dtFinal = dtFinal;
	}
	public Calendar getDtInicial() {
		return dtInicial;
	}
	public void setDtInicial(Calendar dtInicial) {
		this.dtInicial = dtInicial;
	}
	public Calendar getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}
	public void setDtUltimaAlteracao(Calendar dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}
	public EscritorioVendaTO getEscritorioVendaTO() {
		return escritorioVendaTO;
	}
	public void setEscritorioVendaTO(EscritorioVendaTO escritorioVendaTO) {
		this.escritorioVendaTO = escritorioVendaTO;
	}
	public Long getIdMatrizOfertaItemPreco() {
		return idMatrizOfertaItemPreco;
	}
	public void setIdMatrizOfertaItemPreco(Long idMatrizOfertaItemPreco) {
		this.idMatrizOfertaItemPreco = idMatrizOfertaItemPreco;
	}
	public String getNmUsuarioAlteracao() {
		return nmUsuarioAlteracao;
	}
	public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
	}
	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}
	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}
	public OfertaSAPTO getOfertaSAPTO() {
		return ofertaSAPTO;
	}
	public void setOfertaSAPTO(OfertaSAPTO ofertaSAPTO) {
		this.ofertaSAPTO = ofertaSAPTO;
	}
	public OrganizacaoVendaTO getOrganizacaoVendaTO() {
		return organizacaoVendaTO;
	}
	public void setOrganizacaoVendaTO(OrganizacaoVendaTO organizacaoVendaTO) {
		this.organizacaoVendaTO = organizacaoVendaTO;
	}
	public ProdutoTO getProdutoTO() {
		return produtoTO;
	}
	public void setProdutoTO(ProdutoTO produtoTO) {
		this.produtoTO = produtoTO;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public List<Integer> getIdOfertaSAPListParam() {
		return idOfertaSAPListParam;
	}
	public void setIdOfertaSAPListParam(List<Integer> idOfertaSAPListParam) {
		this.idOfertaSAPListParam = idOfertaSAPListParam;
	}
	public List<Integer> getIdOrganizacaoVendasListParam() {
		return idOrganizacaoVendasListParam;
	}
	public void setIdOrganizacaoVendasListParam(
			List<Integer> idOrganizacaoVendasListParam) {
		this.idOrganizacaoVendasListParam = idOrganizacaoVendasListParam;
	}
}
