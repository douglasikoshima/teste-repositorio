package com.indracompany.catalogo.to;

public class AreaConcorrenciaTO {

	private Long idAreaConcorrencia;
	private String cdAreaConcorrencia;
	private String nmAreaConcorrencia;
	private String dsAreaConcorrencia;

	public AreaConcorrenciaTO() {
		super();
	}

	public AreaConcorrenciaTO(Long idAreaConcorrencia) {
		super();
		this.idAreaConcorrencia = idAreaConcorrencia;
	}
	
	public String getCdAreaConcorrencia() {
		return cdAreaConcorrencia;
	}
	public void setCdAreaConcorrencia(String cdAreaConcorrencia) {
		this.cdAreaConcorrencia = cdAreaConcorrencia;
	}
	public String getDsAreaConcorrencia() {
		return dsAreaConcorrencia;
	}
	public void setDsAreaConcorrencia(String dsAreaConcorrencia) {
		this.dsAreaConcorrencia = dsAreaConcorrencia;
	}
	public Long getIdAreaConcorrencia() {
		return idAreaConcorrencia;
	}
	public void setIdAreaConcorrencia(Long idAreaConcorrencia) {
		this.idAreaConcorrencia = idAreaConcorrencia;
	}
	public String getNmAreaConcorrencia() {
		return nmAreaConcorrencia;
	}
	public void setNmAreaConcorrencia(String nmAreaConcorrencia) {
		this.nmAreaConcorrencia = nmAreaConcorrencia;
	}

	
}
