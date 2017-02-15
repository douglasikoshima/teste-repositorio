package com.indracompany.catalogo.to;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ComplementoLegadoTO implements Serializable {
	
	private Integer idSistemaServico;
    private String cdClasseServicoAdicional;
    private String cdClasseServicoPrincipal;
    private String cdPS;
    private String cdTipoEquipamento;
    
	public String getCdClasseServicoAdicional() {
		return cdClasseServicoAdicional;
	}
	public void setCdClasseServicoAdicional(String cdClasseServicoAdicional) {
		this.cdClasseServicoAdicional = cdClasseServicoAdicional;
	}
	public String getCdClasseServicoPrincipal() {
		return cdClasseServicoPrincipal;
	}
	public void setCdClasseServicoPrincipal(String cdClasseServicoPrincipal) {
		this.cdClasseServicoPrincipal = cdClasseServicoPrincipal;
	}
	public String getCdPS() {
		return cdPS;
	}
	public void setCdPS(String cdPS) {
		this.cdPS = cdPS;
	}
	public String getCdTipoEquipamento() {
		return cdTipoEquipamento;
	}
	public void setCdTipoEquipamento(String cdTipoEquipamento) {
		this.cdTipoEquipamento = cdTipoEquipamento;
	}
	public Integer getIdSistemaServico() {
		return idSistemaServico;
	}
	public void setIdSistemaServico(Integer idSistemaServico) {
		this.idSistemaServico = idSistemaServico;
	}
	
}