package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "DISPONIBILIDADECNDCPAGAMENTO", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "DISPONIBILIDADECNDCPAGTO_SQ", sequenceName = "CATALOGOPRS_OW.DISPONIBILIDADECNDCPAGTO_SQ", allocationSize = 1)
public class DisponibilidadeCndcPagamento implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5153739669552017298L;

	@Id
	@Column(name = "IDDISPONIBILIDADECNDCPAGAMENTO")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "DISPONIBILIDADECNDCPAGTO_SQ")
	private Long idDisponibilidadeCndcPagamento;
	
	@ManyToOne
	@JoinColumn(name = "IDCONDICAOPAGAMENTOENCARGO")
	private CondicaoPagamentoEncargo condicaoPagamentoEncargo;
	
	@ManyToOne
	@JoinColumn(name = "IDESPSERVICOPLANOMINUTO")
	private EspServicoPlanoMinuto espServicoPlanoMinuto;
	
	@ManyToOne
	@JoinColumn(name = "IDAREACONCORRENCIA")
	private AreaConcorrencia areaConcorrencia;
	
	@ManyToOne
	@JoinColumn(name = "IDPOLITICADISPCNDCPAGAMENTO")
	private PoliticaDispCndcPagamento politicaDispCndcPagamento;
	
	public DisponibilidadeCndcPagamento() {
		super();
	}

	public DisponibilidadeCndcPagamento(Long idDisponibilidadeCndcPagamento) {
		super();
		this.idDisponibilidadeCndcPagamento = idDisponibilidadeCndcPagamento;
	}

	public DisponibilidadeCndcPagamento(CondicaoPagamentoEncargo condicaoPagamentoEncargo, PoliticaDispCndcPagamento politicaDispCndcPagamento, EspServicoPlanoMinuto espServicoPlanoMinuto, AreaConcorrencia areaConcorrencia) {
		super();
		this.condicaoPagamentoEncargo = condicaoPagamentoEncargo;
		this.politicaDispCndcPagamento = politicaDispCndcPagamento;
		this.espServicoPlanoMinuto = espServicoPlanoMinuto;
		this.areaConcorrencia = areaConcorrencia;
	}

	public AreaConcorrencia getAreaConcorrencia() {
		return areaConcorrencia;
	}

	public void setAreaConcorrencia(AreaConcorrencia areaConcorrencia) {
		this.areaConcorrencia = areaConcorrencia;
	}

	public CondicaoPagamentoEncargo getCondicaoPagamentoEncargo() {
		return condicaoPagamentoEncargo;
	}

	public void setCondicaoPagamentoEncargo(
			CondicaoPagamentoEncargo condicaoPagamentoEncargo) {
		this.condicaoPagamentoEncargo = condicaoPagamentoEncargo;
	}

	public EspServicoPlanoMinuto getEspServicoPlanoMinuto() {
		return espServicoPlanoMinuto;
	}

	public void setEspServicoPlanoMinuto(EspServicoPlanoMinuto espServicoPlanoMinuto) {
		this.espServicoPlanoMinuto = espServicoPlanoMinuto;
	}

	public Long getIdDisponibilidadeCndcPagamento() {
		return idDisponibilidadeCndcPagamento;
	}

	public void setIdDisponibilidadeCndcPagamento(
			Long idDisponibilidadeCndcPagamento) {
		this.idDisponibilidadeCndcPagamento = idDisponibilidadeCndcPagamento;
	}

	public PoliticaDispCndcPagamento getPoliticaDispCndcPagamento() {
		return politicaDispCndcPagamento;
	}

	public void setPoliticaDispCndcPagamento(
			PoliticaDispCndcPagamento politicaDispCndcPagamento) {
		this.politicaDispCndcPagamento = politicaDispCndcPagamento;
	}


}
