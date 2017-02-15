package com.indracompany.catalogo.to;


public class OfertaSAPTO implements Comparable<OfertaSAPTO> {

	private Integer idOfertaSAP;
	private String cdOfertaSAP;	
	private String dsOfertaSAP;
	private String inDisponivel;
	
	public OfertaSAPTO() {}
	public OfertaSAPTO(Integer idOfertaSAP) {
		super();
		this.idOfertaSAP = idOfertaSAP;
	}
	public OfertaSAPTO(Integer idOfertaSAP, String cdOfertaSAP) {
		super();
		this.idOfertaSAP = idOfertaSAP;
		this.cdOfertaSAP = cdOfertaSAP;
	}
	public String getCdOfertaSAP() {
		return cdOfertaSAP;
	}
	public void setCdOfertaSAP(String cdOfertaSAP) {
		this.cdOfertaSAP = cdOfertaSAP;
	}
	public String getDsOfertaSAP() {
		return dsOfertaSAP;
	}
	public void setDsOfertaSAP(String dsOfertaSAP) {
		this.dsOfertaSAP = dsOfertaSAP;
	}
	public Integer getIdOfertaSAP() {
		return idOfertaSAP;
	}
	public void setIdOfertaSAP(Integer idOfertaSAP) {
		this.idOfertaSAP = idOfertaSAP;
	}
	public String getInDisponivel() {
		return inDisponivel;
	}
	public void setInDisponivel(String inDisponivel) {
		this.inDisponivel = inDisponivel;
	}
	@Override
	public int compareTo(OfertaSAPTO o) {
		return this.getCdOfertaSAP().compareToIgnoreCase(o.getCdOfertaSAP());
	}
}
