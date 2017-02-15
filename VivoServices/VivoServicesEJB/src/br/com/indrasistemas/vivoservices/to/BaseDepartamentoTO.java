package br.com.indrasistemas.vivoservices.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public abstract class BaseDepartamentoTO extends BaseTransferObject {

	private Long id;

	private String nome;

	/**
	 * Access method for the id property.
	 *
	 * @return   the current value of the id property
	 */
	public java.lang.Long getId() {
		return id;
	}

	/**
	 * Sets the value of the id property.
	 *
	 * @param aIdDepartamento the new value of the id property
	 */
	public void setId(java.lang.Long aIdDepartamento) {
		id = aIdDepartamento;
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

}
