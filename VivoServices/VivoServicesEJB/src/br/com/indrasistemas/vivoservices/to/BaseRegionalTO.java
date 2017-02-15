package br.com.indrasistemas.vivoservices.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public abstract class BaseRegionalTO extends BaseTransferObject {

	private Long id;

	private String nome;

	private BaseUfTO uf;

	/**
	 * Access method for the id property.
	 *
	 * @return   the current value of the id property
	 */
	public Long getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 *
	 * @param aIdUfOperadora the new value of the id property
	 */
	public void setId(Long aIdUfOperadora) {
		id = aIdUfOperadora;
	}

	/**
	 * Access method for the nome property.
	 *
	 * @return   the current value of the nome property
	 */
	public java.lang.String getNome() {
		return nome;
	}

	/**
	 * Sets the value of the nome property.
	 *
	 * @param aNome the new value of the nome property
	 */
	public void setNome(java.lang.String aNome) {
		nome = aNome;
	}

	public BaseUfTO getUf() {
		return uf;
	}

	public void setUf(BaseUfTO uf) {
		this.uf = uf;
	}

}
