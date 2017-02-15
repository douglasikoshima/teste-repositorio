package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "VLFREQUENCIA_SQ", sequenceName = "CATALOGOPRS_OW.VLFREQUENCIA_SQ", allocationSize = 1)
@NamedQuery(name = "Frequencia.findAll", query = "SELECT f FROM Frequencia f ")
@Table(name = "VLFREQUENCIA", schema = "CATALOGOPRS_OW")
public class Frequencia implements Serializable {
	
	private static final long serialVersionUID = 1L;

    public Frequencia() {}
    
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VLFREQUENCIA_SQ")
	@Column(name = "IDVLFREQUENCIA")
	private Integer idVlFrequencia;
	
	@Column(name = "VLFREQUENCIA")
	private Integer vlFrequencia;

	public Integer getIdVlFrequencia() {
		return idVlFrequencia;
	}

	public void setIdVlFrequencia(Integer idVlFrequencia) {
		this.idVlFrequencia = idVlFrequencia;
	}

	public Integer getVlFrequencia() {
		return vlFrequencia;
	}

	public void setVlFrequencia(Integer vlFrequencia) {
		this.vlFrequencia = vlFrequencia;
	}
}