package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="PRECOPRODUTO", schema = "CATALOGOPRS_OW")
public class PrecoProduto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public PrecoProduto() {}
	
	@Id
	@Column(name = "IDPRECOPRODUTO")
	private Integer idPrecoProduto;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTFINAL")
	private Date dtFinal;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTINICIAL")
	private Date dtInicial;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "IDESCRITORIOVENDA")
	private Integer idEscritorioVenda;

    @Column(name = "INDDESBLOQUEADO")
	private String indDesbloqueado;

    @Column(name = "INDISPONIVEL")
	private String inDisponivel;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

    @Column(name = "VALOR")
	private Float valor;

	//bi-directional many-to-one association to Canal
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANAL", nullable=false)
	private Canal canal;

	//bi-directional many-to-one association to Organizacaovenda
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDORGANIZACAOVENDAS", nullable=false)
	private OrganizacaoVenda organizacaoVenda;

	//bi-directional many-to-one association to Plataforma
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLATAFORMA")
	private Plataforma plataforma;

	//bi-directional many-to-one association to Produto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPRODUTO", nullable=false)
	private Produto produto;

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public Date getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(Date dtInicial) {
		this.dtInicial = dtInicial;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdEscritorioVenda() {
		return idEscritorioVenda;
	}

	public void setIdEscritorioVenda(Integer idEscritorioVenda) {
		this.idEscritorioVenda = idEscritorioVenda;
	}

	public Integer getIdPrecoProduto() {
		return idPrecoProduto;
	}

	public void setIdPrecoProduto(Integer idPrecoProduto) {
		this.idPrecoProduto = idPrecoProduto;
	}

	public String getIndDesbloqueado() {
		return indDesbloqueado;
	}

	public void setIndDesbloqueado(String indDesbloqueado) {
		this.indDesbloqueado = indDesbloqueado;
	}

	public String getInDisponivel() {
		return inDisponivel;
	}

	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
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

	public Plataforma getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(Plataforma plataforma) {
		this.plataforma = plataforma;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}
}