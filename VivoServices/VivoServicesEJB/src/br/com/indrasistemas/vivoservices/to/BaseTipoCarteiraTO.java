package br.com.indrasistemas.vivoservices.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public abstract class BaseTipoCarteiraTO extends BaseTransferObject {

	private Long id;

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String dsTipoCarteira) {
		this.descricao = dsTipoCarteira;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idTipoCarteira) {
		this.id = idTipoCarteira;
	}

}
