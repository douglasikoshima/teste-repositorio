package com.indracompany.catalogo.to;

public class DisponibilidadeCndcPagamentoTO {

	private Long idDisponibilidadeCndcPagamento;
	private Long idCondicaoPagamentoEncargo;
	private PoliticaDispCndcPagamentoTO politicaDispCndcPagamentoTO;
	private EspServicoPlanoMinutoTO espServicoPlanoMinutoTO;
	private AreaConcorrenciaTO areaConcorrenciaTO;
	
	public DisponibilidadeCndcPagamentoTO() {
		super();
	}
	
	public DisponibilidadeCndcPagamentoTO(Long idDisponibilidadeCndcPagamento, Long idCondicaoPagamentoEncargo) {
		super();
		this.idDisponibilidadeCndcPagamento = idDisponibilidadeCndcPagamento;
		this.idCondicaoPagamentoEncargo = idCondicaoPagamentoEncargo;
	}

	public DisponibilidadeCndcPagamentoTO(Long idCondicaoPagamentoEncargo, EspServicoPlanoMinutoTO espServicoPlanoMinutoTO, AreaConcorrenciaTO areaConcorrenciaTO) {
		super();
		this.idCondicaoPagamentoEncargo = idCondicaoPagamentoEncargo;
		this.espServicoPlanoMinutoTO = espServicoPlanoMinutoTO;
		this.areaConcorrenciaTO = areaConcorrenciaTO;
	}

	public AreaConcorrenciaTO getAreaConcorrenciaTO() {
		return areaConcorrenciaTO;
	}

	public void setAreaConcorrenciaTO(AreaConcorrenciaTO areaConcorrenciaTO) {
		this.areaConcorrenciaTO = areaConcorrenciaTO;
	}

	public EspServicoPlanoMinutoTO getEspServicoPlanoMinutoTO() {
		return espServicoPlanoMinutoTO;
	}

	public void setEspServicoPlanoMinutoTO(
			EspServicoPlanoMinutoTO espServicoPlanoMinutoTO) {
		this.espServicoPlanoMinutoTO = espServicoPlanoMinutoTO;
	}

	public Long getIdCondicaoPagamentoEncargo() {
		return idCondicaoPagamentoEncargo;
	}

	public void setIdCondicaoPagamentoEncargo(Long idCondicaoPagamentoEncargo) {
		this.idCondicaoPagamentoEncargo = idCondicaoPagamentoEncargo;
	}

	public Long getIdDisponibilidadeCndcPagamento() {
		return idDisponibilidadeCndcPagamento;
	}

	public void setIdDisponibilidadeCndcPagamento(
			Long idDisponibilidadeCndcPagamento) {
		this.idDisponibilidadeCndcPagamento = idDisponibilidadeCndcPagamento;
	}

	public PoliticaDispCndcPagamentoTO getPoliticaDispCndcPagamentoTO() {
		return politicaDispCndcPagamentoTO;
	}

	public void setPoliticaDispCndcPagamentoTO(
			PoliticaDispCndcPagamentoTO politicaDispCndcPagamentoTO) {
		this.politicaDispCndcPagamentoTO = politicaDispCndcPagamentoTO;
	}

	
	

}
