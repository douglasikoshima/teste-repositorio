package com.indracompany.catalogo.to;

public class EscritorioVendaTO {
	
	private Long idEscritorioVenda;
	private String nmEscritorioVenda;
	private String sgEscritorioVenda;
	
	public EscritorioVendaTO(){};
	public EscritorioVendaTO(Long idEscritorioVenda) {
		super();
		this.idEscritorioVenda = idEscritorioVenda;
	}
	public Long getIdEscritorioVenda() {
		return idEscritorioVenda;
	}
	public void setIdEscritorioVenda(Long idEscritorioVenda) {
		this.idEscritorioVenda = idEscritorioVenda;
	}
	public String getNmEscritorioVenda() {
		return nmEscritorioVenda;
	}
	public void setNmEscritorioVenda(String nmEscritorioVenda) {
		this.nmEscritorioVenda = nmEscritorioVenda;
	}
	public String getSgEscritorioVenda() {
		return sgEscritorioVenda;
	}
	public void setSgEscritorioVenda(String sgEscritorioVenda) {
		this.sgEscritorioVenda = sgEscritorioVenda;
	}
}