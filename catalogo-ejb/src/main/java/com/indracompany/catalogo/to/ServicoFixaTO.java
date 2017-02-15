package com.indracompany.catalogo.to;

import java.io.Serializable;

public class ServicoFixaTO implements Serializable {

    private static final long serialVersionUID = -2575377459991897473L;

    private Integer idServico;
    private Integer idSistema;
    private String nmSistema;
    private String cdServico;
    private String cdPS;
    private String nomeDoServicoOrigem;
    private String nmComercial;
    private Boolean inDisponivel;
    private Integer idTipoServico;
    private String nmTipoServico;
    private String cdCategoria;
    private String nmCategoria;
    private String cdTipoServico;
    private FamiliaTO familiaTO;
    private TecnologiaTO tecnologiaTO;
    private SegmentoTO segmentoTO;
    
    public ServicoFixaTO(){}
    public ServicoFixaTO(int idServico) {
        this.idServico = idServico;
    }
    
    public String getCdPS() {
        return cdPS;
    }

    public void setCdPS(String cdPS) {
        this.cdPS = cdPS;
    }

    public Integer getIdTipoServico() {
        return idTipoServico;
    }

    public void setIdTipoServico(Integer idTipoServico) {
        this.idTipoServico = idTipoServico;
    }

    public String getNmTipoServico() {
        return nmTipoServico;
    }

    public void setNmTipoServico(String nmTipoServico) {
        this.nmTipoServico = nmTipoServico;
    }

    public Integer getIdServico() {
        return idServico;
    }
    
    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }    
    
    public Integer getIdSistema() {
        return idSistema;
    }
    
    public void setIdSistema(Integer idSistema) {
        this.idSistema = idSistema;
    }
    
    public String getNmSistema() {
        return nmSistema;
    }
    
    public void setNmSistema(String nmSistema) {
        this.nmSistema = nmSistema;
    }
    
    public String getCdServico() {
        return cdServico;
    }
    
    public void setCdServico(String cdServico) {
        this.cdServico = cdServico;
    }
    
    public String getNomeDoServicoOrigem() {
        return nomeDoServicoOrigem;
    }
    
    public void setNomeDoServicoOrigem(String nomeDoServicoOrigem) {
        this.nomeDoServicoOrigem = nomeDoServicoOrigem;
    }
    
    public String getNmComercial() {
        return nmComercial;
    }
    
    public void setNmComercial(String nmComercial) {
        this.nmComercial = nmComercial;
    }
    
    public Boolean getInDisponivel() {
        return inDisponivel;
    }
    
    public void setInDisponivel(Boolean inDisponivel) {
        this.inDisponivel = inDisponivel;
    }
    
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;            
        }
        final ServicoFixaTO other = (ServicoFixaTO) obj;
        if (this.idServico != other.idServico && (this.idServico == null || !this.idServico.equals(other.idServico))) {
            return false;
        }
        return true;
    }
    @Override
    public int hashCode() {
        return this.idServico != null ? idServico.hashCode() : 0;
    }
    @Override
    public String toString() {
        return String.format("idSistema=%s, cdServico=%s, cdPS=%s, nomeDoServicoOrigem=%s, nmComercial=%s, inDisponivel=%s", this.idSistema, this.cdServico, this.cdPS, this.nomeDoServicoOrigem, this.nmComercial, this.inDisponivel);
    }
    public String getCdCategoria() {
        return cdCategoria;
    }
    public void setCdCategoria(String cdCategoria) {
        this.cdCategoria = cdCategoria;
    }
    public String getCdTipoServico() {
        return cdTipoServico;
    }
    public void setCdTipoServico(String cdTipoServico) {
        this.cdTipoServico = cdTipoServico;
    }
	public FamiliaTO getFamiliaTO() {
		return familiaTO;
	}
	public void setFamiliaTO(FamiliaTO familiaTO) {
		this.familiaTO = familiaTO;
	}
	public String getNmCategoria() {
		return nmCategoria;
	}
	public void setNmCategoria(String nmCategoria) {
		this.nmCategoria = nmCategoria;
	}
	public SegmentoTO getSegmentoTO() {
		return segmentoTO;
	}
	public void setSegmentoTO(SegmentoTO segmentoTO) {
		this.segmentoTO = segmentoTO;
	}
	public TecnologiaTO getTecnologiaTO() {
		return tecnologiaTO;
	}
	public void setTecnologiaTO(TecnologiaTO tecnologiaTO) {
		this.tecnologiaTO = tecnologiaTO;
	}
}
