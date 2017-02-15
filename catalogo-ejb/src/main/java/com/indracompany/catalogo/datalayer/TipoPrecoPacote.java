package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "TIPOPRECOPACOTE_SQ", sequenceName = "CATALOGOPRS_OW.TIPOPRECOPACOTE_SQ", allocationSize = 1)
@Table(name="TIPOPRECOPACOTE", schema = "CATALOGOPRS_OW")
public class TipoPrecoPacote implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7400316198963058422L;
	
	@Id
	@Column(name = "IDTIPOPRECOPACOTE")
	private Long idTipoPrecoPacote;
	
	@Column(name = "CDTIPOPRECOPACOTE")
	private String cdTipoPrecoPacote;
	
	@Column(name = "NMTIPOPRECOPACOTE")
	private String nmTipoPrecoPacote;
	
	@Column(name = "DSTIPOPRECOPACOTE")
	private String dsTipoPrecoPacote;
	
	public String getCdTipoPrecoPacote() {
		return cdTipoPrecoPacote;
	}

	public void setCdTipoPrecoPacote(String cdTipoPrecoPacote) {
		this.cdTipoPrecoPacote = cdTipoPrecoPacote;
	}

	public String getDsTipoPrecoPacote() {
		return dsTipoPrecoPacote;
	}

	public void setDsTipoPrecoPacote(String dsTipoPrecoPacote) {
		this.dsTipoPrecoPacote = dsTipoPrecoPacote;
	}

	public Long getIdTipoPrecoPacote() {
		return idTipoPrecoPacote;
	}

	public void setIdTipoPrecoPacote(Long idTipoPrecoPacote) {
		this.idTipoPrecoPacote = idTipoPrecoPacote;
	}

	public String getNmTipoPrecoPacote() {
		return nmTipoPrecoPacote;
	}

	public void setNmTipoPrecoPacote(String nmTipoPrecoPacote) {
		this.nmTipoPrecoPacote = nmTipoPrecoPacote;
	}
	
	
}
