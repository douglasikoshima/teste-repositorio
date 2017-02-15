package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="OPERADORA", schema = "CATALOGOPRS_OW")
public class Operadora implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public Operadora() {}
	
	@Id
	@Column(name = "IDOPERADORA")
	private Integer idOperadora;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
	private Date dtUltimaAlteracao;

    @Column(name = "NMOPERADORA")
	private String nmOperadora;

    @Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;

	//bi-directional many-to-one association to Centro
	@OneToMany(mappedBy="operadora")
	private List<Centro> centros;

	//bi-directional many-to-one association to Organizacaovenda
	@OneToMany(mappedBy="operadora")
	private List<OrganizacaoVenda> organizacaoVendaList;

	public List<Centro> getCentros() {
		return centros;
	}

	public void setCentros(List<Centro> centros) {
		this.centros = centros;
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

	public Integer getIdOperadora() {
		return idOperadora;
	}

	public void setIdOperadora(Integer idOperadora) {
		this.idOperadora = idOperadora;
	}

	public String getNmOperadora() {
		return nmOperadora;
	}

	public void setNmOperadora(String nmOperadora) {
		this.nmOperadora = nmOperadora;
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

	public List<OrganizacaoVenda> getOrganizacaoVendaList() {
		return organizacaoVendaList;
	}

	public void setOrganizacaoVendaList(List<OrganizacaoVenda> organizacaoVendaList) {
		this.organizacaoVendaList = organizacaoVendaList;
	}
}