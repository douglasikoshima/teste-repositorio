package com.indracompany.catalogo.to;

import java.io.Serializable;

@SuppressWarnings("serial")
public class LocalidadeTO implements Serializable {
    
    private Long idLocalidade;
    private String cdLocalidade;
    private String nmLocalidade;
    private CidadeTO cidadeTO;
    private String checked;
    
    public LocalidadeTO() {
    }
    
    public LocalidadeTO(Long idLocalidade) {
		this.idLocalidade = idLocalidade;
	}

	public String getCdLocalidade() {
        return cdLocalidade;
    }
    
    public Long getIdLocalidade() {
        return idLocalidade;
    }
    
    public String getNmLocalidade() {
        return nmLocalidade;
    }
    
    public void setCdLocalidade(String cdLocalidade) {
        this.cdLocalidade = cdLocalidade;
    }
    
    public void setIdLocalidade(Long idLocalidade) {
        this.idLocalidade = idLocalidade;
    }
    
    public void setNmLocalidade(String nmLocalidade) {
        this.nmLocalidade = nmLocalidade;
    }

    public CidadeTO getCidadeTO() {
        return cidadeTO;
    }

    public void setCidadeTO(CidadeTO cidadeTO) {
        this.cidadeTO = cidadeTO;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((cdLocalidade == null) ? 0 : cdLocalidade.hashCode());
		result = PRIME * result + ((checked == null) ? 0 : checked.hashCode());
		result = PRIME * result + ((cidadeTO == null) ? 0 : cidadeTO.hashCode());
		result = PRIME * result + ((idLocalidade == null) ? 0 : idLocalidade.hashCode());
		result = PRIME * result + ((nmLocalidade == null) ? 0 : nmLocalidade.hashCode());
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
		final LocalidadeTO other = (LocalidadeTO) obj;
		if (cdLocalidade == null) {
			if (other.cdLocalidade != null)
				return false;
		} else if (!cdLocalidade.equals(other.cdLocalidade))
			return false;
		if (checked == null) {
			if (other.checked != null)
				return false;
		} else if (!checked.equals(other.checked))
			return false;
		if (cidadeTO == null) {
			if (other.cidadeTO != null)
				return false;
		} else if (!cidadeTO.equals(other.cidadeTO))
			return false;
		if (idLocalidade == null) {
			if (other.idLocalidade != null)
				return false;
		} else if (!idLocalidade.equals(other.idLocalidade))
			return false;
		if (nmLocalidade == null) {
			if (other.nmLocalidade != null)
				return false;
		} else if (!nmLocalidade.equals(other.nmLocalidade))
			return false;
		return true;
	}
    
}