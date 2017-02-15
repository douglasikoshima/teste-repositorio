package br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo;

import com.framework.vo.ValueObject;

public class ErroComumVO extends ValueObject{

	private static final long serialVersionUID = 1L;

	private Long servicoNegocio;
	private Long sistemaLegado;
	private Long servico;
	private Long cdLegado;
	private Long cdPadrao;
	private Long cdLegadoDef;
	private Long cdPadraoDef;
	private Long cdLegadoDis;
	private Long cdPadraoDis;
	private String mensagem;
	private Long classificacao;
	
	public Long getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(Long classificacao) {
		this.classificacao = classificacao;
	}
	public ErroComumVO() {
		super();
	}
	public ErroComumVO(String id) {
		super(id);
	}
	public Long getCdLegado() {
		return cdLegado;
	}
	public void setCdLegado(Long cdLegado) {
		this.cdLegado = cdLegado;
	}
	public Long getCdPadrao() {
		return cdPadrao;
	}
	public void setCdPadrao(Long cdPadrao) {
		this.cdPadrao = cdPadrao;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
	public Long getServico() {
		return servico;
	}
	public void setServico(Long servico) {
		this.servico = servico;
	}
	public Long getServicoNegocio() {
		return servicoNegocio;
	}
	public void setServicoNegocio(Long servicoNegocio) {
		this.servicoNegocio = servicoNegocio;
	}
	public Long getSistemaLegado() {
		return sistemaLegado;
	}
	public void setSistemaLegado(Long sistemaLegado) {
		this.sistemaLegado = sistemaLegado;
	}
	@Override
	public void setId() {}
	@Override
	public void setId(String id) {}
	public Long getCdLegadoDef() {
		return cdLegadoDef;
	}
	public void setCdLegadoDef(Long cdLegadoDef) {
		this.cdLegadoDef = cdLegadoDef;
	}
	public Long getCdLegadoDis() {
		return cdLegadoDis;
	}
	public void setCdLegadoDis(Long cdLegadoDis) {
		this.cdLegadoDis = cdLegadoDis;
	}
	public Long getCdPadraoDef() {
		return cdPadraoDef;
	}
	public void setCdPadraoDef(Long cdPadraoDef) {
		this.cdPadraoDef = cdPadraoDef;
	}
	public Long getCdPadraoDis() {
		return cdPadraoDis;
	}
	public void setCdPadraoDis(Long cdPadraoDis) {
		this.cdPadraoDis = cdPadraoDis;
	}

}
