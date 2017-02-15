package com.indracompany.catalogo.to;


public class PoliticaDispSlctComercialTO {
	
	private Long idPoliticaDispSlctComercial;
	
	private String inAreaConcorrencia;
	
	private String inPlanoMinuto;
	
	public PoliticaDispSlctComercialTO() {
		super();
	}
	
	public PoliticaDispSlctComercialTO(Long idPoliticaDispSlctComercial) {
		super();
		this.idPoliticaDispSlctComercial = idPoliticaDispSlctComercial;
	}

	public PoliticaDispSlctComercialTO(String inAreaConcorrencia, String inPlanoMinuto) {
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
