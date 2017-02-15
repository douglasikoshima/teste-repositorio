package br.com.vivo.catalogoPRS.util;

public enum PlataformaEnum {
	PREPAGO(1), POSPAGO(2), CONTROLE(3);
	
	private long idPlataforma;
	
	PlataformaEnum(long idPlataforma){
		this.idPlataforma = idPlataforma;
	}
	
	public boolean equals(long idPlataforma){
		return (this.idPlataforma == idPlataforma);
	}
	
	public boolean equals(Long idPlataforma){
		return (this.idPlataforma == idPlataforma.longValue());
	}
}
