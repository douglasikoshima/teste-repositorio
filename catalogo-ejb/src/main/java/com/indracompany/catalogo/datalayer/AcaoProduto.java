package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "ACAOPRODUTO_SQ", sequenceName = "CATALOGOPRS_OW.ACAOPRODUTO_SQ", allocationSize = 1)
@Table(name = "ACAOPRODUTO", schema = "CATALOGOPRS_OW")
public class AcaoProduto implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public AcaoProduto() {}
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACAOPRODUTO_SQ")
	@Column(name = "IDACAOPRODUTO")
	private Integer idAcaoProduto;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDACAO")
	private Acao acao;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPRODUTO")
	private Produto produto;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANAL")
	private Canal canal;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDORGANIZACAOVENDAS")
	private OrganizacaoVenda organizacaoVenda;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPLATAFORMA")
	private Plataforma plataforma;
	
	@Column(name = "DTCRIACAO")
	@Temporal( TemporalType.DATE)
	private Date dtCriacao;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	public Acao getAcao() {
		return acao;
	}

	public void setAcao(Acao acao) {
		this.acao = acao;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdAcaoProduto() {
		return idAcaoProduto;
	}

	public void setIdAcaoProduto(Integer idAcaoProduto) {
		this.idAcaoProduto = idAcaoProduto;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Canal getCanal() {
		return canal;
	}

	public void setCanal(Canal canal) {
		this.canal = canal;
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
}