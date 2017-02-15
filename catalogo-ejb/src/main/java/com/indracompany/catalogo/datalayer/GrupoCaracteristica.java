package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@NamedQuery(name = "GrupoCaracteristica.findAll", query = "SELECT gp FROM GrupoCaracteristica gp order by gp.nmGrupoCaracteristica")
@SequenceGenerator(name = "GRUPOCARACTERISTICA_SQ", sequenceName = "CATALOGOPRS_OW.GRUPOCARACTERISTICA_SQ", allocationSize = 1)
@Table(name = "GRUPOCARACTERISTICA", schema = "CATALOGOPRS_OW")
public class GrupoCaracteristica implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public GrupoCaracteristica() {		
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPOCARACTERISTICA_SQ")   
	@Column(name = "IDGRUPOCARACTERISTICA")
	private Integer idGrupoCaracteristica;	
	
	@Column(name = "NMGRUPOCARACTERISTICA")
	private String nmGrupoCaracteristica;
	
	@Column(name = "ICONEGRUPOCARACTERISTICA")
	private String iconeGrupoCaracteristica;	
	
    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
	private Date dtCriacao;	
    
    @Temporal( TemporalType.DATE)
    @Column(name = "DTALTERACAO")
	private Date dtAlteracao;	
    
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao; 
	
	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;
	
	@OneToMany(mappedBy="grupoCaracteristica")
	private List<Caracteristica> caracteristicaList;
	
	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public String getIconeGrupoCaracteristica() {
		return iconeGrupoCaracteristica;
	}

	public void setIconeGrupoCaracteristica(String iconeGrupoCaracteristica) {
		this.iconeGrupoCaracteristica = iconeGrupoCaracteristica;
	}

	public Integer getIdGrupoCaracteristica() {
		return idGrupoCaracteristica;
	}

	public void setIdGrupoCaracteristica(Integer idGrupoCaracteristica) {
		this.idGrupoCaracteristica = idGrupoCaracteristica;
	}

	public String getNmGrupoCaracteristica() {
		return nmGrupoCaracteristica;
	}

	public void setNmGrupoCaracteristica(String nmGrupoCaracteristica) {
		this.nmGrupoCaracteristica = nmGrupoCaracteristica;
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

	public List<Caracteristica> getCaracteristicaList() {
		return caracteristicaList;
	}
	
	public void setCaracteristicaList(List<Caracteristica> caracteristicaList) {
		this.caracteristicaList = caracteristicaList;
	}
}
