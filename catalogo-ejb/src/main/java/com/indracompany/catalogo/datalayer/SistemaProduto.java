package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="SISTEMAPRODUTO", schema = "CATALOGOPRS_OW")
public class SistemaProduto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public SistemaProduto() {}
	
	@Id
	@Column(name = "IDSISTEMAPRODUTO")
	private Integer idSistemaProduto;

	@Column(name = "CDCODIGO")
	private String cdCodigo;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Produto
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDPRODUTO", nullable=false)
	private Produto produto;

	//bi-directional many-to-one association to Sistema
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDSISTEMA", nullable=false)
	private Sistema sistema;

	public String getCdCodigo() {
		return cdCodigo;
	}

	public void setCdCodigo(String cdCodigo) {
		this.cdCodigo = cdCodigo;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdSistemaProduto() {
		return idSistemaProduto;
	}

	public void setIdSistemaProduto(Integer idSistemaProduto) {
		this.idSistemaProduto = idSistemaProduto;
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Sistema getSistema() {
		return sistema;
	}

	public void setSistema(Sistema sistema) {
		this.sistema = sistema;
	}
}