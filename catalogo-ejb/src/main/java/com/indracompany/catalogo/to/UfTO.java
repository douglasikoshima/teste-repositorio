package com.indracompany.catalogo.to;

import java.io.Serializable;

public class UfTO implements Serializable {

	private static final long serialVersionUID = -654654651L;
	
	private Long idUf;
	private String nmUf;
	
	public UfTO() {
		super();
	}

	public UfTO(Long idUf) {
        this();
	    this.idUf = idUf;
    }

    public Long getIdUf() {
		return idUf;
	}

	public void setIdUf(Long idUf) {
		this.idUf = idUf;
	}

	public String getNmUf() {
		return nmUf;
	}

	public void setNmUf(String nmUf) {
		this.nmUf = nmUf;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((idUf == null) ? 0 : idUf.hashCode());
		result = PRIME * result + ((nmUf == null) ? 0 : nmUf.hashCode());
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
		final UfTO other = (UfTO) obj;
		if (idUf == null) {
			if (other.idUf != null)
				return false;
		} else if (!idUf.equals(other.idUf))
			return false;
		if (nmUf == null) {
			if (other.nmUf != null)
				return false;
		} else if (!nmUf.equals(other.nmUf))
			return false;
		return true;
	}

}
