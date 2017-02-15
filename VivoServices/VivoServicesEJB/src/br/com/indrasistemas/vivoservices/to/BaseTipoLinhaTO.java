package br.com.indrasistemas.vivoservices.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public abstract class BaseTipoLinhaTO extends BaseTransferObject {

	private Long id;

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String dsTipoLinha) {
		this.descricao = dsTipoLinha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idTipoLinha) {
		this.id = idTipoLinha;
	}

}
