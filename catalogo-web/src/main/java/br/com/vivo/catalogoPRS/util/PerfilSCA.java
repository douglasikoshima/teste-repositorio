package br.com.vivo.catalogoPRS.util;


public class PerfilSCA implements Comparable<PerfilSCA> {
	
	private Long idPerfilSCA;
	private Long idSistema;
	private String nmPerfilSCA;
	
	
	public Long getIdPerfilSCA() {
		return idPerfilSCA;
	}
	public void setIdPerfilSCA(Long idPerfilSCA) {
		this.idPerfilSCA = idPerfilSCA;
	}
	public String getNmPerfilSCA() {
		return nmPerfilSCA;
	}
	public void setNmPerfilSCA(String nmPerfilSCA) {
		this.nmPerfilSCA = nmPerfilSCA;
	}
	public int compareTo(PerfilSCA perfil) {
		return this.getNmPerfilSCA().compareToIgnoreCase(perfil.getNmPerfilSCA());
	}
	public Long getIdSistema() {
		return idSistema;
	}
	public void setIdSistema(Long idSistema) {
		this.idSistema = idSistema;
	}

	public boolean equals(Object object) {
		boolean isEquals = false;
		PerfilSCA perfilSCA = (PerfilSCA) object;
		if(this.idPerfilSCA.equals(perfilSCA.getIdPerfilSCA())){
			isEquals = true;
		}
		return isEquals;
	}

}
