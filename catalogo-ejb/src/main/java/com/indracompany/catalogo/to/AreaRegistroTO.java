package com.indracompany.catalogo.to;

import java.io.Serializable;

@SuppressWarnings("serial")
public class AreaRegistroTO implements Serializable {
    
    private Integer idAreaRegistro;
    private String codigoArea;
    private UfTO ufTO;
    private String checked;
    
    public AreaRegistroTO() {
    }
    
    public AreaRegistroTO(Integer idAreaRegistro) {
        this.idAreaRegistro = idAreaRegistro;
    }    

    public AreaRegistroTO(UfTO ufTO) {
        this.ufTO = ufTO;
    }

    public String getCodigoArea() {
        return codigoArea;
    }
    
    public Integer getIdAreaRegistro() {
        return idAreaRegistro;
    }
    
    public UfTO getUfTO() {
        return ufTO;
    }
    
    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }
    
    public void setIdAreaRegistro(Integer idAreaRegistro) {
        this.idAreaRegistro = idAreaRegistro;
    }
    
    public void setUfTO(UfTO ufTO) {
        this.ufTO = ufTO;
    }

    public String getChecked() {
        return checked != null ? checked : "";
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((checked == null) ? 0 : checked.hashCode());
		result = PRIME * result + ((codigoArea == null) ? 0 : codigoArea.hashCode());
		result = PRIME * result + ((idAreaRegistro == null) ? 0 : idAreaRegistro.hashCode());
		result = PRIME * result + ((ufTO == null) ? 0 : ufTO.hashCode());
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
		final AreaRegistroTO other = (AreaRegistroTO) obj;
		if (checked == null) {
			if (other.checked != null)
				return false;
		} else if (!checked.equals(other.checked))
			return false;
		if (codigoArea == null) {
			if (other.codigoArea != null)
				return false;
		} else if (!codigoArea.equals(other.codigoArea))
			return false;
		if (idAreaRegistro == null) {
			if (other.idAreaRegistro != null)
				return false;
		} else if (!idAreaRegistro.equals(other.idAreaRegistro))
			return false;
		if (ufTO == null) {
			if (other.ufTO != null)
				return false;
		} else if (!ufTO.equals(other.ufTO))
			return false;
		return true;
	}
    
}