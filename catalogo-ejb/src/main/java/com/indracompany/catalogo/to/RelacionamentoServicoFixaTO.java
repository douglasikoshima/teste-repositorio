package com.indracompany.catalogo.to;

import java.io.Serializable;

public class RelacionamentoServicoFixaTO implements Serializable {

    private static final long serialVersionUID = -3508560261204650107L;
    
    private String cdServico;
    private String nmServicoSistemaOrigem;
    private String nmServicoCatalogo;
    private Boolean inDisponivel;
    private TipoRelacionamentoTO tipoRelacionamentoTO;
    private Integer idRelacionamento;
    
    private Integer idServicoSearch;

    public Boolean inCriacaoCatalogo;
    public Integer idServico1;
    public Integer idSistema1;
    
    public Integer getIdSistema1() {
		return idSistema1;
	}

	public void setIdSistema1(Integer idSistema1) {
		this.idSistema1 = idSistema1;
	}

	public Integer getIdRelacionamento() {
        return idRelacionamento;
    }

    public void setIdRelacionamento(Integer idRelacionamento) {
        this.idRelacionamento = idRelacionamento;
    }

    public String getCdServico() {
        return cdServico;
    }
    
    public void setCdServico(String cdServico) {
        this.cdServico = cdServico;
    }
    
    public Boolean getInDisponivel() {
        return inDisponivel;
    }
    
    public void setInDisponivel(Boolean inDisponivel) {
        this.inDisponivel = inDisponivel;
    }
    
    public String getNmServicoCatalogo() {
        return nmServicoCatalogo;
    }
    
    public void setNmServicoCatalogo(String nmServicoCatalogo) {
        this.nmServicoCatalogo = nmServicoCatalogo;
    }
    
    public String getNmServicoSistemaOrigem() {
        return nmServicoSistemaOrigem;
    }
    
    public void setNmServicoSistemaOrigem(String nmServicoSistemaOrigem) {
        this.nmServicoSistemaOrigem = nmServicoSistemaOrigem;
    }
    
    public TipoRelacionamentoTO getTipoRelacionamentoTO() {
        return this.tipoRelacionamentoTO;
    }

    public void setTipoRelacionamentoTO(TipoRelacionamentoTO tipoRelacionamentoTO) {
        this.tipoRelacionamentoTO = tipoRelacionamentoTO;
    }

    public Integer getIdServicoSearch() {
        return idServicoSearch;
    }

    public void setIdServicoSearch(Integer idServicoSearch) {
        this.idServicoSearch = idServicoSearch;
    }
    
    public Boolean getInCriacaoCatalogo() {
        return inCriacaoCatalogo;
    }

    public void setInCriacaoCatalogo(Boolean inCriacaoCatalogo) {
        this.inCriacaoCatalogo = inCriacaoCatalogo;
    }
    
    public Integer getIdServico1() {
        return idServico1;
    }

    public void setIdServico1(Integer idServico1) {
        this.idServico1 = idServico1;
    }
}
