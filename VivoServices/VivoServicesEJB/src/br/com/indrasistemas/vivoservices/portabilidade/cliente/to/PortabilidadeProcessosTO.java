package br.com.indrasistemas.vivoservices.portabilidade.cliente.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class PortabilidadeProcessosTO extends BaseTransferObject {

	public PortabilidadeProcessosTO() {
	}

	private static final long serialVersionUID = -1337032133002338L;

	private Integer cdAreaRegistro;
	private Long nrLinha;
	private String dtJanela;
	private Integer cdOperacao;
	private Integer estadoPortabilidade;
	private Integer cdOperadoraSolicitante;
	private String dsComentarioProcesso;
	private Long nrProcesso;
	private Long nrBilhetePortabilidade;
	private Integer cdProcedencia;

	public Integer getCdAreaRegistro() {
		return cdAreaRegistro;
	}

	public void setCdAreaRegistro(Integer cdAreaRegistro) {
		this.cdAreaRegistro = cdAreaRegistro;
	}

	public Long getNrLinha() {
		return nrLinha;
	}

	public void setNrLinha(Long nrLinha) {
		this.nrLinha = nrLinha;
	}

	public String getDtJanela() {
		return dtJanela;
	}

	public void setDtJanela(String dtJanela) {
		this.dtJanela = dtJanela;
	}

	public Integer getCdOperacao() {
		return cdOperacao;
	}

	public void setCdOperacao(Integer cdOperacao) {
		this.cdOperacao = cdOperacao;
	}

	public Integer getEstadoPortabilidade() {
		return estadoPortabilidade;
	}

	public void setEstadoPortabilidade(Integer estadoPortabilidade) {
		this.estadoPortabilidade = estadoPortabilidade;
	}

	public Integer getCdOperadoraSolicitante() {
		return cdOperadoraSolicitante;
	}

	public void setCdOperadoraSolicitante(Integer cdOperadoraSolicitante) {
		this.cdOperadoraSolicitante = cdOperadoraSolicitante;
	}

	public String getDsComentarioProcesso() {
		return dsComentarioProcesso;
	}

	public void setDsComentarioProcesso(String dsComentarioProcesso) {
		this.dsComentarioProcesso = dsComentarioProcesso;
	}

	public Long getNrProcesso() {
		return nrProcesso;
	}

	public void setNrProcesso(Long nrProcesso) {
		this.nrProcesso = nrProcesso;
	}

	public Long getNrBilhetePortabilidade() {
		return nrBilhetePortabilidade;
	}

	public void setNrBilhetePortabilidade(Long nrBilhetePortabilidade) {
		this.nrBilhetePortabilidade = nrBilhetePortabilidade;
	}

	public Integer getCdProcedencia() {
		return cdProcedencia;
	}

	public void setCdProcedencia(Integer cdProcedencia) {
		this.cdProcedencia = cdProcedencia;
	}

}