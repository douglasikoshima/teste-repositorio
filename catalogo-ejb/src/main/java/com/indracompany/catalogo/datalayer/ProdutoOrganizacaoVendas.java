package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Luiz Pereira
 */
@Entity
@Table(name = "PRODUTOORGANIZACAOVENDAS", catalog = "", schema = "CATALOGOPRS_OW", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"IDORGANIZACAOVENDAS", "IDPRODUTO"})})
public class ProdutoOrganizacaoVendas implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public ProdutoOrganizacaoVendas() {}
    
    @Id
    @Column(name = "IDPRODUTOORGANIZACAOVENDAS")
    private Integer idProdutoOrganizacaoVendas;
    
    @Column(name = "DTCRIACAO")
    @Temporal(TemporalType.DATE)
    private Date dtCriacao;
    
    @Column(name = "NMUSUARIOCRIACAO", length = 255)
    private String nmUsuarioCriacao;
    
    @Column(name = "DTULTIMAALTERACAO")
    @Temporal(TemporalType.DATE)
    private Date dtUltimaAlteracao;
    
    @Column(name = "NMUSUARIOALTERACAO", length = 255)
    private String nmUsuarioAlteracao;
    
    @Column(name = "DTINICIAL")
    @Temporal(TemporalType.DATE)
    private Date dtInicial;
    
    @Column(name = "DTFINAL")
    @Temporal(TemporalType.DATE)
    private Date dtFinal;
    
    @Column(name = "INDISPONIVEL", length = 255)
    private String inDisponivel;
    
    @JoinColumn(name = "IDORGANIZACAOVENDAS", referencedColumnName = "IDORGANIZACAOVENDAS", nullable = false)
    @ManyToOne(optional = false)
    private OrganizacaoVenda organizacaoVenda;
    
    @JoinColumn(name = "IDPRODUTO", referencedColumnName = "IDPRODUTO", nullable = false)
    @ManyToOne(optional = false)
    private Produto produto;

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

	public Integer getIdProdutoOrganizacaoVendas() {
		return idProdutoOrganizacaoVendas;
	}

	public void setIdProdutoOrganizacaoVendas(Integer idProdutoOrganizacaoVendas) {
		this.idProdutoOrganizacaoVendas = idProdutoOrganizacaoVendas;
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}
}
