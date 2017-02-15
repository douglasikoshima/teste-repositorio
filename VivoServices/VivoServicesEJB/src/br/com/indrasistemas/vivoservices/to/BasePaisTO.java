package br.com.indrasistemas.vivoservices.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class BasePaisTO extends BaseTransferObject {

	private Long id;

	private String sigla;

	private String nome;

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
	 * @param aIdPais the new value of the id property
	 */
	public void setId(Long aIdPais) {
		id = aIdPais;
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
