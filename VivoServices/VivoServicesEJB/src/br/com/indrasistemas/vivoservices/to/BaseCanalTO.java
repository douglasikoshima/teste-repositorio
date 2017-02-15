package br.com.indrasistemas.vivoservices.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public abstract class BaseCanalTO extends BaseTransferObject {

	/**
	 * Atributo que representa o ID do Canal.
	 */
	private Long id;

	/**
	 * Atributo que representa a Descrição do Canal.
	 */
	private String descricao;

	/**
	 * APOIO.SGCANAL
	 */
	private String sigla;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * Access method for the sigla property.
	 *
	 * @return   the current value of the sigla property
	 */
	public java.lang.String getSigla() {
		return sigla;
	}

	/**
	 * Sets the value of the sigla property.
	 *
	 * @param aSigla the new value of the sigla property
	 */
	public void setSigla(java.lang.String aSigla) {
		sigla = aSigla;
	}

}
