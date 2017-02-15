package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class CidadeTO implements Serializable {
    
    private Integer idCidade;
    private String nmCidade;
    private String nmUsuarioAlteracao;
    private Date dtUltimaAlteracao;
    private AreaRegistroTO areaRegistroTO;
    private List<LocalidadeTO> localidadeTOList;

    public CidadeTO() {
    }
    
    public CidadeTO(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public CidadeTO(AreaRegistroTO areaRegistroTO) {
        this.areaRegistroTO = areaRegistroTO;
    }

    public AreaRegistroTO getAreaRegistroTO() {
        return areaRegistroTO;
    }
    
    public Date getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }
    
    public Integer getIdCidade() {
        return idCidade;
    }
    
    public String getNmCidade() {
        return nmCidade;
    }
    
    public String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }
    
    public void setAreaRegistroTO(AreaRegistroTO areaRegistroTO) {
        this.areaRegistroTO = areaRegistroTO;
    }
    
    public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }
    
    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }
    
    public void setNmCidade(String nmCidade) {
        this.nmCidade = nmCidade;
    }
    
    public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }

    public List<LocalidadeTO> getLocalidadeTOList() {
        return localidadeTOList;
    }

    public void setLocalidadeTOList(List<LocalidadeTO> localidadeTOList) {
        this.localidadeTOList = localidadeTOList;
    }

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((areaRegistroTO == null) ? 0 : areaRegistroTO.hashCode());
		result = PRIME * result + ((dtUltimaAlteracao == null) ? 0 : dtUltimaAlteracao.hashCode());
		result = PRIME * result + ((idCidade == null) ? 0 : idCidade.hashCode());
		result = PRIME * result + ((nmCidade == null) ? 0 : nmCidade.hashCode());
		result = PRIME * result + ((nmUsuarioAlteracao == null) ? 0 : nmUsuarioAlteracao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CidadeTO other = (CidadeTO) obj;
		if (areaRegistroTO == null) {
			if (other.areaRegistroTO != null)
				return false;
		} else if (!areaRegistroTO.equals(other.areaRegistroTO))
			return false;
		if (dtUltimaAlteracao == null) {
			if (other.dtUltimaAlteracao != null)
				return false;
		} else if (!dtUltimaAlteracao.equals(other.dtUltimaAlteracao))
			return false;
		if (idCidade == null) {
			if (other.idCidade != null)
				return false;
		} else if (!idCidade.equals(other.idCidade))
			return false;
		if (nmCidade == null) {
			if (other.nmCidade != null)
				return false;
		} else if (!nmCidade.equals(other.nmCidade))
			return false;
		if (nmUsuarioAlteracao == null) {
			if (other.nmUsuarioAlteracao != null)
				return false;
		} else if (!nmUsuarioAlteracao.equals(other.nmUsuarioAlteracao))
			return false;
		return true;
	}
    
}