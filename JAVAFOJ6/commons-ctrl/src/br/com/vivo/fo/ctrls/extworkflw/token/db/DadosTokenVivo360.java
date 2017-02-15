package br.com.vivo.fo.ctrls.extworkflw.token.db;

import java.io.Serializable;

public class DadosTokenVivo360 implements Serializable {

	private static final long serialVersionUID = 1L;
	private String idTokenVivo360;
	private String nrLinha;
	private String idGrupo;
	private String nrProtocolo;
	private String idUsuarioAlteracao;
	private String dtUltimaAtualizacao;
	private String nmLoginUsuario;
	
	public String getNmLoginUsuario() {
		return nmLoginUsuario;
	}
	public void setNmLoginUsuario(String nmLoginUsuario) {
		this.nmLoginUsuario = nmLoginUsuario;
	}
	public String getIdTokenVivo360() {
		return idTokenVivo360;
	}
	public void setIdTokenVivo360(String idTokenVivo360) {
		this.idTokenVivo360 = idTokenVivo360;
	}
	public String getNrLinha() {
		return nrLinha;
	}
	public void setNrLinha(String nrLinha) {
		this.nrLinha = nrLinha;
	}
	public String getIdGrupo() {
		return idGrupo;
	}
	public void setIdGrupo(String idGrupo) {
		this.idGrupo = idGrupo;
	}
	public String getNrProtocolo() {
		return nrProtocolo;
	}
	public void setNrProtocolo(String nrProtocolo) {
		this.nrProtocolo = nrProtocolo;
	}
	public String getIdUsuarioAlteracao() {
		return idUsuarioAlteracao;
	}
	public void setIdUsuarioAlteracao(String idUsuarioAlteracao) {
		this.idUsuarioAlteracao = idUsuarioAlteracao;
	}
	public String getDtUltimaAtualizacao() {
		return dtUltimaAtualizacao;
	}
	public void setDtUltimaAtualizacao(String dtUltimaAtualizacao) {
		this.dtUltimaAtualizacao = dtUltimaAtualizacao;
	}
}
