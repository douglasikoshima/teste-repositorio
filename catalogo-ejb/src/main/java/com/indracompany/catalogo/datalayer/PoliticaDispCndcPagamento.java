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
@Table(name = "POLITICADISPCNDCPAGAMENTO", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "POLITICADISPCNDCPAGAMENTO_SQ", sequenceName = "CATALOGOPRS_OW.POLITICADISPCNDCPAGAMENTO_SQ", allocationSize = 1)
public class PoliticaDispCndcPagamento implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2068418044665607096L;

	@Id
	@Column(name = "IDPOLITICADISPCNDCPAGAMENTO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "POLITICADISPCNDCPAGAMENTO_SQ")
	private Long idPoliticaDispCndcPagamento;
	
	@Column(name = "INAREACONCORRENCIA")
	private String inAreaConcorrencia; 
	
	@Column(name = "INPLANOMINUTO")
	private String inPlanoMinuto;
	
	public PoliticaDispCndcPagamento() {
		super();
	}

	public PoliticaDispCndcPagamento(Long idPoliticaDispCndcPagamento) {
		super();
		this.idPoliticaDispCndcPagamento = idPoliticaDispCndcPagamento;
	}

	public Long getIdPoliticaDispCndcPagamento() {
		return idPoliticaDispCndcPagamento;
	}

	public void setIdPoliticaDispCndcPagamento(Long idPoliticaDispCndcPagamento) {
		this.idPoliticaDispCndcPagamento = idPoliticaDispCndcPagamento;
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
