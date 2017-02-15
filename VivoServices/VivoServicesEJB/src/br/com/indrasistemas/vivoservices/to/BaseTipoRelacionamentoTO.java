package br.com.indrasistemas.vivoservices.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public abstract class BaseTipoRelacionamentoTO extends BaseTransferObject {

	private Long id;

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String dsTipoRelacionamento) {
		this.descricao = dsTipoRelacionamento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idTipoRelacionamento) {
		this.id = idTipoRelacionamento;
	}

}
