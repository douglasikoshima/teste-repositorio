package com.indracompany.catalogo.to;



public class DisponibilidadeSlctComercialTO {

	private Long idDisponibilidadeSlctComercial;
	private Long idCanalVendaSolicitacaoComercial;
	private String inAreaConcorrenciaSolicitacaoComercial;
	private String inPlanoMinutoSolicitacaoComercial;
	private Long idPoliticaDispSlctComercial;
	private EspServicoPlanoMinutoTO espServicoPlanoMinutoTO;
	private AreaConcorrenciaTO areaConcorrenciaTO;
	
	public DisponibilidadeSlctComercialTO() {
		super();
	}

	public DisponibilidadeSlctComercialTO(Long idCanalVendaSolicitacaoComercial, String inAreaConcorrenciaSolicitacaoComercial, String inPlanoMinutoSolicitacaoComercial, EspServicoPlanoMinutoTO espServicoPlanoMinutoTO, AreaConcorrenciaTO areaConcorrenciaTO) {
		super();
		this.idCanalVendaSolicitacaoComercial = idCanalVendaSolicitacaoComercial;
		this.inAreaConcorrenciaSolicitacaoComercial = inAreaConcorrenciaSolicitacaoComercial;
		this.inPlanoMinutoSolicitacaoComercial = inPlanoMinutoSolicitacaoComercial;
		this.espServicoPlanoMinutoTO = espServicoPlanoMinutoTO;
		this.areaConcorrenciaTO = areaConcorrenciaTO;
	}

	//GETTERS AND SETTERS
	
	public Long getIdPoliticaDispSlctComercial() {
		return idPoliticaDispSlctComercial;
	}

	public void setIdPoliticaDispSlctComercial(Long idPoliticaDispSlctComercial) {
		this.idPoliticaDispSlctComercial = idPoliticaDispSlctComercial;
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

	public Long getIdCanalVendaSolicitacaoComercial() {
		return idCanalVendaSolicitacaoComercial;
	}

	public void setIdCanalVendaSolicitacaoComercial(
			Long idCanalVendaSolicitacaoComercial) {
		this.idCanalVendaSolicitacaoComercial = idCanalVendaSolicitacaoComercial;
	}

	public Long getIdDisponibilidadeSlctComercial() {
		return idDisponibilidadeSlctComercial;
	}

	public void setIdDisponibilidadeSlctComercial(
			Long idDisponibilidadeSlctComercial) {
		this.idDisponibilidadeSlctComercial = idDisponibilidadeSlctComercial;
	}

	public String getInAreaConcorrenciaSolicitacaoComercial() {
		return inAreaConcorrenciaSolicitacaoComercial;
	}

	public void setInAreaConcorrenciaSolicitacaoComercial(
			String inAreaConcorrenciaSolicitacaoComercial) {
		this.inAreaConcorrenciaSolicitacaoComercial = inAreaConcorrenciaSolicitacaoComercial;
	}

	public String getInPlanoMinutoSolicitacaoComercial() {
		return inPlanoMinutoSolicitacaoComercial;
	}

	public void setInPlanoMinutoSolicitacaoComercial(
			String inPlanoMinutoSolicitacaoComercial) {
		this.inPlanoMinutoSolicitacaoComercial = inPlanoMinutoSolicitacaoComercial;
	}
	
}
