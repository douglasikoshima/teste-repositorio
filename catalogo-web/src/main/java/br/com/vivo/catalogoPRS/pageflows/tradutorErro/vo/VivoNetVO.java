package br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo;

import java.io.Serializable;

public class VivoNetVO implements Serializable{

	private static final long serialVersionUID = 1L;

	private String numeroIP;
	private String dtInclusao;
	private String dtUltimaAlteracao;
	private Integer idUsuarioInclusao;
	private Integer idUsuarioAlteracao;
	private Integer cderro;
	private String dserro; 
	
	public String getDtInclusao() {
		return dtInclusao;
	}
	public void setDtInclusao(String dtInclusao) {
		this.dtInclusao = dtInclusao;
	}
	public String getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}
	public void setDtUltimaAlteracao(String dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}
	public Integer getIdUsuarioAlteracao() {
		return idUsuarioAlteracao;
	}
	public void setIdUsuarioAlteracao(Integer idUsuarioAlteracao) {
		this.idUsuarioAlteracao = idUsuarioAlteracao;
	}
	public Integer getIdUsuarioInclusao() {
		return idUsuarioInclusao;
	}
	public void setIdUsuarioInclusao(Integer idUsuarioInclusao) {
		this.idUsuarioInclusao = idUsuarioInclusao;
	}
	public String getNumeroIP() {
		return numeroIP;
	}
	public void setNumeroIP(String numeroIP) {
		this.numeroIP = numeroIP;
	}
	public Integer getCderro() {
		return cderro;
	}
	public void setCderro(Integer cderro) {
		this.cderro = cderro;
	}
	public String getDserro() {
		return dserro;
	}
	public void setDserro(String dserro) {
		this.dserro = dserro;
	}
}
