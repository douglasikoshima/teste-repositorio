package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="AREAREGISTRO", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "AreaRegistro.findAll", query = "select ar from AreaRegistro ar order by ar.codigoArea"),
    @NamedQuery(name = "AreaRegistro.countAll", query = "select count(ar) from AreaRegistro ar"),
    @NamedQuery(name = "AreaRegistro.findByIdUf", query = "select distinct ar from AreaRegistro ar where ar.uf.idUf = :idUf order by ar.codigoArea"),
    @NamedQuery(name = "AreaRegistro.findByIdOfertafixa", query = "select offar.areaRegistro from OfertafixaFatorAreaRegistro offar where offar.ofertafixa.idOfertafixa = :idOfertafixa order by offar.areaRegistro.codigoArea")
})
public class AreaRegistro implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public AreaRegistro() {}
	
	@Id
	@Column(name = "IDAREAREGISTRO")
	private Integer idarearegistro;

	@Column(name = "CODIGOAREA")
	private String codigoArea;

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

	//bi-directional many-to-one association to Organizacaovenda
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDORGANIZACAOVENDAS")
	private OrganizacaoVenda organizacaoVenda;

	//bi-directional many-to-one association to Uf
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDUF", nullable=false)
	private Uf uf;

	//bi-directional many-to-one association to Arearegistroorgvnd
	@OneToMany(mappedBy="areaRegistro")
	private List<AreaRegistroOrgVnd> areaRegistroOrgVndList;

	//bi-directional many-to-one association to Comboofertaarearegistro
	@OneToMany(mappedBy="areaRegistro")
	private List<ComboOfertaAreaRegistro> comboOfertaAreaRegistroList;

	//bi-directional many-to-one association to Csa
	@OneToMany(mappedBy="areaRegistro")
	private List<CSA> csaList;

    //bi-directional many-to-one association to Csa
    @OneToMany(mappedBy="areaRegistro")
    private List<Cidade> ciadadeList;
    
	public List<AreaRegistroOrgVnd> getAreaRegistroOrgVndList() {
		return areaRegistroOrgVndList;
	}

	public void setAreaRegistroOrgVndList(
			List<AreaRegistroOrgVnd> areaRegistroOrgVndList) {
		this.areaRegistroOrgVndList = areaRegistroOrgVndList;
	}

	public String getCodigoArea() {
		return codigoArea;
	}

	public void setCodigoArea(String codigoArea) {
		this.codigoArea = codigoArea;
	}

	public List<ComboOfertaAreaRegistro> getComboOfertaAreaRegistroList() {
		return comboOfertaAreaRegistroList;
	}

	public void setComboOfertaAreaRegistroList(
			List<ComboOfertaAreaRegistro> comboOfertaAreaRegistroList) {
		this.comboOfertaAreaRegistroList = comboOfertaAreaRegistroList;
	}

	public List<CSA> getCsaList() {
		return csaList;
	}

	public void setCsaList(List<CSA> csaList) {
		this.csaList = csaList;
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

	public Integer getIdarearegistro() {
		return idarearegistro;
	}

	public void setIdarearegistro(Integer idarearegistro) {
		this.idarearegistro = idarearegistro;
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

	public Uf getUf() {
		return uf;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

    public List<Cidade> getCiadadeList() {
        return ciadadeList;
    }

    public void setCiadadeList(List<Cidade> ciadadeList) {
        this.ciadadeList = ciadadeList;
    }
}