package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="COMPLEMENTOLEGADO", schema = "CATALOGOPRS_OW")
public class ComplementoLegado implements Serializable {

    private static final long serialVersionUID = -5991837321061395333L;
    
    @Id
    @Column(name = "IDSISTEMASERVICO")
    private Integer idSistemaServico;
    
    @Column(name = "CDCLASSESERVICOADICIONAL")
    private String cdClasseServicoAdicional;
    
    @Column(name = "CDCLASSESERVICOPRINCIPAL")
    private String cdClasseServicoPrincipal;
    
    @Column(name = "CDPS")
    private String cdPS;
    
    @Column(name = "CDTIPOEQUIPAMENTO")
    private String cdTipoEquipamento;
    
    @OneToOne
    @JoinColumn(name = "IDSISTEMASERVICO", nullable = false)
    private SistemaServico sistemaServico;

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

	public SistemaServico getSistemaServico() {
		return sistemaServico;
	}

	public void setSistemaServico(SistemaServico sistemaServico) {
		this.sistemaServico = sistemaServico;
	}


}
