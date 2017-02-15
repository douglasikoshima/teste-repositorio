package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "TIPOALTA", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "TIPOALTA_SQ", sequenceName = "CATALOGOPRS_OW.TIPOALTA_SQ", allocationSize = 1)
public class TipoAlta implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2779529790778006470L;
	
	@Id
	@Column(name = "IDTIPOALTA")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TIPOALTA_SQ")
	private Long idTipoAlta;
	
	@Column(name = "DSTIPOALTA")
	private String dsTipoAlta;
	
	public String getDsTipoAlta() {
		return dsTipoAlta;
	}
	public void setDsTipoAlta(String dsTipoAlta) {
		this.dsTipoAlta = dsTipoAlta;
	}
	public Long getIdTipoAlta() {
		return idTipoAlta;
	}
	public void setIdTipoAlta(Long idTipoAlta) {
		this.idTipoAlta = idTipoAlta;
	}
}