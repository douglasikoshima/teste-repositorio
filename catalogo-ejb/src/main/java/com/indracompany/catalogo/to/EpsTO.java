package com.indracompany.catalogo.to;

import java.io.Serializable;

@SuppressWarnings("serial")
public class EpsTO implements Serializable {
	
	private Integer idEps;
	private String nmEps;
	
	public EpsTO() {
		super();
	}
	
	public EpsTO(String nmEps) {
		this();
		this.nmEps = nmEps;
	}
	
	public EpsTO(Integer idEps, String nmEps) {
		this(nmEps);
		this.idEps = idEps;
	}
    
    public EpsTO(Integer idEps) {
        this();
        this.idEps = idEps;
    }
	
	public Integer getIdEps() {
		return idEps;
	}
	public void setIdEps(Integer idEps) {
		this.idEps = idEps;
	}
	public String getNmEps() {
		return nmEps;
	}
	public void setNmEps(String nmEps) {
		this.nmEps = nmEps;
	}

	@Override
	public int hashCode() {
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + ((idEps == null) ? 0 : idEps.hashCode());
		result = PRIME * result + ((nmEps == null) ? 0 : nmEps.hashCode());
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
		final EpsTO other = (EpsTO) obj;
		if (idEps == null) {
			if (other.idEps != null)
				return false;
		} else if (!idEps.equals(other.idEps))
			return false;
		if (nmEps == null) {
			if (other.nmEps != null)
				return false;
		} else if (!nmEps.equals(other.nmEps))
			return false;
		return true;
	}
	
}