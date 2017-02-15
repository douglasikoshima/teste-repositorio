package com.indracompany.catalogo.to;

import java.io.Serializable;

public class DisponibilidadePromocaoFixaTO implements Serializable {

    private static final long serialVersionUID = -2643132266564000170L;
    
    private Integer idValorPoliticaPromocaoTO;
    private Integer idCanalVenda;
    private Integer cdCanalVenda; 
    private String nmCanalVenda;
    private String nmEps;
    private Integer idEps;
    
    public Integer getIdCanalVenda() {
        return idCanalVenda;
    }
    
    public void setIdCanalVenda(Integer idCanalVenda) {
        this.idCanalVenda = idCanalVenda;
    }

    public Integer getCdCanalVenda() {
		return cdCanalVenda;
	}

	public void setCdCanalVenda(Integer cdCanalVenda) {
		this.cdCanalVenda = cdCanalVenda;
	}

	public Integer getIdValorPoliticaPromocaoTO() {
        return idValorPoliticaPromocaoTO;
    }

    public void setIdValorPoliticaPromocaoTO(Integer idValorPoliticaPromocaoTO) {
        this.idValorPoliticaPromocaoTO = idValorPoliticaPromocaoTO;
    }

    public String getNmCanalVenda() {
        return nmCanalVenda;
    }

    public void setNmCanalVenda(String nmCanalVenda) {
        this.nmCanalVenda = nmCanalVenda;
    }

    public String getNmEps() {
        return nmEps;
    }

    public void setNmEps(String nmEps) {
        this.nmEps = nmEps;
    }

	public Integer getIdEps() {
		return idEps;
	}
	public void setIdEps(Integer idEps) {
		this.idEps = idEps;
	}    
}
