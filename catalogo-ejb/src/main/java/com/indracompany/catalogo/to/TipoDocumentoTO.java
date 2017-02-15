package com.indracompany.catalogo.to;


public class TipoDocumentoTO {
	
	private Long idTipoDocumento;
	private String nmTipoDocumento;
	
	public TipoDocumentoTO() {
		super();
	}
	public TipoDocumentoTO(Long idTipoDocumento) {
		super();
		this.idTipoDocumento = idTipoDocumento;
	}
	public Long getIdTipoDocumento() {
		return idTipoDocumento;
	}
	public void setIdTipoDocumento(Long idTipoDocumento) {
		this.idTipoDocumento = idTipoDocumento;
	}
	public String getNmTipoDocumento() {
		return nmTipoDocumento;
	}
	public void setNmTipoDocumento(String nmTipoDocumento) {
		this.nmTipoDocumento = nmTipoDocumento;
	}
}
