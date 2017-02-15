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
@Table(name = "POLITICADISPSLCTCOMERCIAL", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "POLITICADISPSLCTCOMERCIAL_SQ", sequenceName = "CATALOGOPRS_OW.POLITICADISPSLCTCOMERCIAL_SQ", allocationSize = 1)
public class PoliticaDispSlctComercial implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4478914344826578521L;

	@Id
	@Column(name = "IDPOLITICADISPSLCTCOMERCIAL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLITICADISPSLCTCOMERCIAL_SQ")
	private Long idPoliticaDispSlctComercial;
	
	@Column(name = "INAREACONCORRENCIA")
	private String inAreaConcorrencia;
	
	@Column(name = "INPLANOMINUTO")
	private String inPlanoMinuto;
	
	public PoliticaDispSlctComercial() {
		super();
	}

	public PoliticaDispSlctComercial(Long idPoliticaDispSlctComercial) {
		super();
		this.idPoliticaDispSlctComercial = idPoliticaDispSlctComercial;
	}
	
	public PoliticaDispSlctComercial(String inAreaConcorrencia, String inPlanoMinuto) {
		super();
		this.inAreaConcorrencia = inAreaConcorrencia;
		this.inPlanoMinuto = inPlanoMinuto;
	}

	public Long getIdPoliticaDispSlctComercial() {
		return idPoliticaDispSlctComercial;
	}

	public void setIdPoliticaDispSlctComercial(Long idPoliticaDispSlctComercial) {
		this.idPoliticaDispSlctComercial = idPoliticaDispSlctComercial;
	}

	public String getInAreaConcorrencia() {
		return inAreaConcorrencia;
	}

	public void setInAreaConcorrencia(String inAreaConcorrencia) {
		this.inAreaConcorrencia = inAreaConcorrencia;
	}

	public String getInPlanoMinuto() {
		return inPlanoMinuto;
	}

	public void setInPlanoMinuto(String inPlanoMinuto) {
		this.inPlanoMinuto = inPlanoMinuto;
	}
}
