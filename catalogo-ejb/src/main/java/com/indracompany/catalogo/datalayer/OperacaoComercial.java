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
@Table(name = "OPERACAOCOMERCIAL", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "OPERACAOCOMERCIAL_SQ", sequenceName = "CATALOGOPRS_OW.OPERACAOCOMERCIAL_SQ", allocationSize = 1)
public class OperacaoComercial implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7180845702334803762L;

	@Id
	@Column(name = "IDOPERACAOCOMERCIAL")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OPERACAOCOMERCIAL_SQ")
	private Long idOperacaoComercial;
	
	@Column(name = "CDOPERACAOCOMERCIAL")
	private String cdOperacaoComercial;

	@Column(name = "NMOPERACAOCOMERCIAL")
	private String nmOperacaoComercial;	
	
	@Column(name = "DSOPERACAOCOMERCIAL")
	private String dsOperacaoComercial;
	
	public String getCdOperacaoComercial() {
		return cdOperacaoComercial;
	}

	public void setCdOperacaoComercial(String cdOperacaoComercial) {
		this.cdOperacaoComercial = cdOperacaoComercial;
	}

	public String getDsOperacaoComercial() {
		return dsOperacaoComercial;
	}

	public void setDsOperacaoComercial(String dsOperacaoComercial) {
		this.dsOperacaoComercial = dsOperacaoComercial;
	}

	public Long getIdOperacaoComercial() {
		return idOperacaoComercial;
	}

	public void setIdOperacaoComercial(Long idOperacaoComercial) {
		this.idOperacaoComercial = idOperacaoComercial;
	}

	public String getNmOperacaoComercial() {
		return nmOperacaoComercial;
	}

	public void setNmOperacaoComercial(String nmOperacaoComercial) {
		this.nmOperacaoComercial = nmOperacaoComercial;
	}

}
