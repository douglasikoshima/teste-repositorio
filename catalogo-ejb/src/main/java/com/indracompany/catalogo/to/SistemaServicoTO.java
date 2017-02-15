package com.indracompany.catalogo.to;

public class SistemaServicoTO {
	
	private Long idSistemaServico;
	
	public SistemaServicoTO(Long id){
		idSistemaServico = id;
	}
	
	public Long getIdSistemaServico() {
		return idSistemaServico;
	}

	public void setIdSistemaServico(Long idSistemaServico) {
		this.idSistemaServico = idSistemaServico;
	}
}
