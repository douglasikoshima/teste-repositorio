package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "MATRIZOFERTAITEMPRECO", schema = "CATALOGOPRS_OW")
public class MatrizOfertaItemPreco implements Serializable {
	private static final long serialVersionUID = 1L;

    public MatrizOfertaItemPreco() {}
    
	@Id
	@Column(name = "IDMATRIZOFERTAITEMPRECO")
	private Long idMatrizOfertaItemPreco;

	@Column(name = "CODIGOAREA")
	private Integer codigoArea;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTINICIAL")
	private Calendar dtInicial;
	
	@Temporal( TemporalType.DATE)
	@Column(name = "DTFINAL")
	private Calendar dtFinal;
	
	@Column(name = "VALOR")
	private BigDecimal valor;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "DTCRIACAO")
	private Calendar dtCriacao;
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;
	
	@Temporal( TemporalType.TIMESTAMP)
	@Column(name = "DTULTIMAALTERACAO")
	private Calendar dtUltimaAlteracao;
	
	@Column(name = "CDADABAS")
	private String cdAdabas;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="matrizOfertaItemPreco")
	private List<ProdutoMenorPreco> produtoMenorPrecoList;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy="matrizOfertaItemPreco")
	private List<ProdutoMelhorOferta> produtoMelhorOfertaList;
	
	//bi-directional many-to-one association to Matrizofertaitem
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMATRIZOFERTAITEM", nullable=false)
	private MatrizOfertaItem matrizOfertaItem;

	//bi-directional many-to-one association to Organizacaovenda
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDORGANIZACAOVENDAS", nullable=false)
	private OrganizacaoVenda organizacaoVenda;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="ACAO")
	private Acao acao;
	
	//bi-directional many-to-one association to Canal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CANALDISTRIBUICAO", nullable=false)
	private Canal canal;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDESCRITORIOVENDA")
	private EscritorioVenda escritorioVenda;

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public String getCdAdabas() {
		return cdAdabas;
	}

	public void setCdAdabas(String cdAdabas) {
		this.cdAdabas = cdAdabas;
	}

	public Integer getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(Integer codigoArea) {
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

	public EscritorioVenda getEscritorioVenda() {
		return escritorioVenda;
	}

	public void setEscritorioVenda(EscritorioVenda escritorioVenda) {
		this.escritorioVenda = escritorioVenda;
	}

	public Long getIdMatrizOfertaItemPreco() {
		return idMatrizOfertaItemPreco;
	}

	public void setIdMatrizOfertaItemPreco(Long idMatrizOfertaItemPreco) {
		this.idMatrizOfertaItemPreco = idMatrizOfertaItemPreco;
	}

	public MatrizOfertaItem getMatrizOfertaItem() {
		return matrizOfertaItem;
	}

	public void setMatrizOfertaItem(MatrizOfertaItem matrizOfertaItem) {
		this.matrizOfertaItem = matrizOfertaItem;
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

	public OrganizacaoVenda getOrganizacaoVenda() {
		return organizacaoVenda;
	}

	public void setOrganizacaoVenda(OrganizacaoVenda organizacaoVenda) {
		this.organizacaoVenda = organizacaoVenda;
	}

	public List<ProdutoMelhorOferta> getProdutoMelhorOfertaList() {
		return produtoMelhorOfertaList;
	}

	public void setProdutoMelhorOfertaList(
			List<ProdutoMelhorOferta> produtoMelhorOfertaList) {
		this.produtoMelhorOfertaList = produtoMelhorOfertaList;
	}

	public List<ProdutoMenorPreco> getProdutoMenorPrecoList() {
		return produtoMenorPrecoList;
	}

	public void setProdutoMenorPrecoList(
			List<ProdutoMenorPreco> produtoMenorPrecoList) {
		this.produtoMenorPrecoList = produtoMenorPrecoList;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	

}