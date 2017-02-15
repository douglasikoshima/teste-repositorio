package br.com.vivo.fo.ctrls.admsistemas.arquivos.dbClasses;

import java.io.Serializable;

public class RelatorioUpload implements Serializable {

	private static final long serialVersionUID = -751886523743315612L;

	private String nmArquivoOriginal;
	private String nmArquivo;
	private String dtEnvio;
	private String dsArquivoLog;
	private String idAtendimentoAnatelArquivo;
	private int qtTotalRegistros;
	private int qtProcessado;
	private int qtRejeitado;
	private int qtProtocoloCliente;
	private int qtProtocoloNaoCliente;
	private int qtContatoNovo;
	private int qtContatoGenerico;

	public String getNmArquivoOriginal() {
		return nmArquivoOriginal;
	}

	public void setNmArquivoOriginal(String nmArquivoOriginal) {
		this.nmArquivoOriginal = nmArquivoOriginal;
	}

	public String getNmArquivo() {
		return nmArquivo;
	}

	public void setNmArquivo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
	}

	public String getDtEnvio() {
		return this.dtEnvio;
	}

	public void setDtEnvio(String dtEnvio) {
		this.dtEnvio = dtEnvio;
	}

	public String getDsArquivoLog() {
		return this.dsArquivoLog;
	}

	public void setDsArquivoLog(String dsArquivoLog) {
		this.dsArquivoLog = dsArquivoLog;
	}

	public String getIdAtendimentoAnatelArquivo() {
		return idAtendimentoAnatelArquivo;
	}

	public void setIdAtendimentoAnatelArquivo(String idAtendimentoAnatelArquivo) {
		this.idAtendimentoAnatelArquivo = idAtendimentoAnatelArquivo;
	}

	public int getQtTotalRegistros() {
		return qtTotalRegistros;
	}

	public void setQtTotalRegistros(int qtTotalRegistros) {
		this.qtTotalRegistros = qtTotalRegistros;
	}

	public int getQtProcessado() {
		return qtProcessado;
	}

	public void setQtProcessado(int qtProcessado) {
		this.qtProcessado = qtProcessado;
	}

	public int getQtRejeitado() {
		return qtRejeitado;
	}

	public void setQtRejeitado(int qtRejeitado) {
		this.qtRejeitado = qtRejeitado;
	}

	public int getQtProtocoloCliente() {
		return qtProtocoloCliente;
	}

	public void setQtProtocoloCliente(int qtProtocoloCliente) {
		this.qtProtocoloCliente = qtProtocoloCliente;
	}

	public int getQtProtocoloNaoCliente() {
		return qtProtocoloNaoCliente;
	}

	public void setQtProtocoloNaoCliente(int qtProtocoloNaoCliente) {
		this.qtProtocoloNaoCliente = qtProtocoloNaoCliente;
	}

	public int getQtContatoNovo() {
		return qtContatoNovo;
	}

	public void setQtContatoNovo(int qtContatoNovo) {
		this.qtContatoNovo = qtContatoNovo;
	}

	public int getQtContatoGenerico() {
		return qtContatoGenerico;
	}

	public void setQtContatoGenerico(int qtContatoGenerico) {
		this.qtContatoGenerico = qtContatoGenerico;
	}

}
