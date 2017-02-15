package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
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

@Entity
@Table(name = "AREACONCORRENCIA", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "AREACONCORRENCIA_SQ", sequenceName = "CATALOGOPRS_OW.AREACONCORRENCIA_SQ", allocationSize = 1)
@NamedQuery(name = "AreaConcorrencia.findAll", query = "select a from AreaConcorrencia a ")
public class AreaConcorrencia implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4056422922745559925L;

	@Id
	@Column(name = "IDAREACONCORRENCIA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "AREACONCORRENCIA_SQ")
	private Long idAreaConcorrencia;
	
	@Column(name = "CDAREACONCORRENCIA")
	private String cdAreaConcorrencia;
	
	@Column(name = "NMAREACONCORRENCIA")
	private String nmAreaConcorrencia;
	
	@Column(name = "DSAREACONCORRENCIA")
	private String dsAreaConcorrencia;
	
	@OneToMany(mappedBy = "areaConcorrencia")
	private List<DisponibilidadeSlctComercial> disponibilidadeSlctComercialList; 
	
	public String getCdAreaConcorrencia() {
		return cdAreaConcorrencia;
	}

	public void setCdAreaConcorrencia(String cdAreaConcorrencia) {
		this.cdAreaConcorrencia = cdAreaConcorrencia;
	}

	public String getDsAreaConcorrencia() {
		return dsAreaConcorrencia;
	}

	public void setDsAreaConcorrencia(String dsAreaConcorrencia) {
		this.dsAreaConcorrencia = dsAreaConcorrencia;
	}

	public Long getIdAreaConcorrencia() {
		return idAreaConcorrencia;
	}

	public void setIdAreaConcorrencia(Long idAreaConcorrencia) {
		this.idAreaConcorrencia = idAreaConcorrencia;
	}

	public String getNmAreaConcorrencia() {
		return nmAreaConcorrencia;
	}

	public void setNmAreaConcorrencia(String nmAreaConcorrencia) {
		this.nmAreaConcorrencia = nmAreaConcorrencia;
	}

	public List<DisponibilidadeSlctComercial> getDisponibilidadeSlctComercialList() {
		return disponibilidadeSlctComercialList;
	}

	public void setDisponibilidadeSlctComercialList(
			List<DisponibilidadeSlctComercial> disponibilidadeSlctComercialList) {
		this.disponibilidadeSlctComercialList = disponibilidadeSlctComercialList;
	}

}
