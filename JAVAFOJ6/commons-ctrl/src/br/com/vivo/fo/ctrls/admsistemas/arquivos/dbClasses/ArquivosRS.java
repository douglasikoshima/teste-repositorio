package br.com.vivo.fo.ctrls.admsistemas.arquivos.dbClasses;

import java.io.Serializable;

public class ArquivosRS implements Serializable {

	private static final long serialVersionUID = -6901660087613350727L;

	private String dtInclusao;
	private String nmLoginUsuario;
	private String dtProcessamento;
	private String dsErroProcessamento;
	private int qtRegistrosProcessados;
	private int qtRegistrosDescartados;
	private String nmArquivo;
	private String nmArquivoOriginal;
	private String idAtendimentoAnatelArquivo;

	public ArquivosRS() {
	}

	public String getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(String dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public String getNmLoginUsuario() {
		return nmLoginUsuario;
	}

	public void setNmLoginUsuario(String nmLoginUsuario) {
		this.nmLoginUsuario = nmLoginUsuario;
	}

	public String getDsErroProcessamento() {
		return dsErroProcessamento;
	}

	public void setDsErroProcessamento(String dsErroProcessamento) {
		this.dsErroProcessamento = dsErroProcessamento;
	}

	public String getNmArquivo() {
		return nmArquivo;
	}

	public void setNmArquivo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
	}

	public String getDtProcessamento() {
		return dtProcessamento;
	}

	public void setDtProcessamento(String dtProcessamento) {
		this.dtProcessamento = dtProcessamento;
	}

	public int getQtRegistrosProcessados() {
		return qtRegistrosProcessados;
	}

	public void setQtRegistrosProcessados(int qtRegistrosProcessados) {
		this.qtRegistrosProcessados = qtRegistrosProcessados;
	}

	public int getQtRegistrosDescartados() {
		return qtRegistrosDescartados;
	}

	public void setQtRegistrosDescartados(int qtRegistrosDescartados) {
		this.qtRegistrosDescartados = qtRegistrosDescartados;
	}

	public String getNmArquivoOriginal() {
		return this.nmArquivoOriginal;
	}

	public void setNmArquivoOriginal(String nmArquivoOriginal) {
		this.nmArquivoOriginal = nmArquivoOriginal;
		;
	}

	public String getIdAtendimentoAnatelArquivo() {
		return this.idAtendimentoAnatelArquivo;
	}

	public void setIdAtendimentoAnatelArquivo(String idAtendimentoAnatelArquivo) {
		this.idAtendimentoAnatelArquivo = idAtendimentoAnatelArquivo;
	}

}
