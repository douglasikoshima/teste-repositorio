package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="PRODUTODISPONIBILIDADEPP", schema = "CATALOGOPRS_OW")
public class ProdutoDisponibilidadePP implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public ProdutoDisponibilidadePP() {}
	
	@Id
	@Column(name = "IDPRODUTODISPONIBILIDADEPP")
	private Integer idProdutoDisponibilidadePP;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAOALTERACAO")
	private Date dtCriacaoAlteracao;

    @Column(name = "NMUSERCRIACAOALTERACAO")
	private String nmUserCriacaoAlteracao;

    @Column(name = "SGACAOMARKETING")
	private String sgAcaoMarketing;

	//bi-directional many-to-one association to Canalatendimento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDCANALATENDIMENTO", nullable=false)
	private CanalAtendimento canalAtendimento;

	//bi-directional many-to-one association to Organizacaovenda
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDORGANIZACAOVENDAS", nullable=false)
	private OrganizacaoVenda organizacaoVenda;

	//bi-directional many-to-one association to Produto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPRODUTO", nullable=false)
	private Produto produto;

	public CanalAtendimento getCanalAtendimento() {
		return canalAtendimento;
	}

	public void setCanalAtendimento(CanalAtendimento canalAtendimento) {
		this.canalAtendimento = canalAtendimento;
	}

	public Date getDtCriacaoAlteracao() {
		return dtCriacaoAlteracao;
	}

	public void setDtCriacaoAlteracao(Date dtCriacaoAlteracao) {
		this.dtCriacaoAlteracao = dtCriacaoAlteracao;
	}

	public Integer getIdProdutoDisponibilidadePP() {
		return idProdutoDisponibilidadePP;
	}

	public void setIdProdutoDisponibilidadePP(Integer idProdutoDisponibilidadePP) {
		this.idProdutoDisponibilidadePP = idProdutoDisponibilidadePP;
	}

	public String getNmUserCriacaoAlteracao() {
		return nmUserCriacaoAlteracao;
	}

	public void setNmUserCriacaoAlteracao(String nmUserCriacaoAlteracao) {
		this.nmUserCriacaoAlteracao = nmUserCriacaoAlteracao;
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

	public String getSgAcaoMarketing() {
		return sgAcaoMarketing;
	}

	public void setSgAcaoMarketing(String sgAcaoMarketing) {
		this.sgAcaoMarketing = sgAcaoMarketing;
	}
}