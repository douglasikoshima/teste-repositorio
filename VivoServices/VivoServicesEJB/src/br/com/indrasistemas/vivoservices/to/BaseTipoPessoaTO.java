package br.com.indrasistemas.vivoservices.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public abstract class BaseTipoPessoaTO extends BaseTransferObject {

	private Long id;

	private String descricao;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String dsTipoPessoa) {
		this.descricao = dsTipoPessoa;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long idTipoPessoa) {
		this.id = idTipoPessoa;
	}

}
