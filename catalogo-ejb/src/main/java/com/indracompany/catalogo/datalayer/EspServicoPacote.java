package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="ESPSERVICOPACOTE", schema = "CATALOGOPRS_OW")
public class EspServicoPacote implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -816322139080080206L;
	
	@Id
	@Column(name = "IDSERVICO")
	private Integer idServico;
	
	@Column(name = "CDPACOTE")
	private String cdPacote;

	@OneToOne
	@JoinColumn(name = "IDSERVICO", nullable = false)
	private Servico servico;
	
	@ManyToOne
	@JoinColumn(name = "IDTIPOPRECOPACOTE")
	private TipoPrecoPacote tipoPrecoPacote;

	public String getCdPacote() {
		return cdPacote;
	}

	public void setCdPacote(String cdPacote) {
		this.cdPacote = cdPacote;
	}

	public Integer getIdServico() {
		return idServico;
	}

	public void setIdServico(Integer idServico) {
		this.idServico = idServico;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

	public TipoPrecoPacote getTipoPrecoPacote() {
		return tipoPrecoPacote;
	}

	public void setTipoPrecoPacote(TipoPrecoPacote tipoPrecoPacote) {
		this.tipoPrecoPacote = tipoPrecoPacote;
	}


	
}
