package br.com.indrasistemas.vivoservices.to;

import java.util.List;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public abstract class BaseOrganizacaoTO extends BaseTransferObject {

	private Long id;

	private String nome;

	private List departamentos;

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
	 * @param aIdOrganizacao the new value of the id property
	 */
	public void setId(Long aIdOrganizacao) {
		id = aIdOrganizacao;
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

	/**
	 * Access method for the departamentos property.
	 * @return   the current value of the departamentos property
	 */
	public List getDepartamentos() {
		return departamentos;
	}

	/**
	 * Sets the value of the departamentos property.
	 * @param aDepartamentos the new value of the departamentos property
	 */
	public void setDepartamentos(List aDepartamentos) {
		departamentos = aDepartamentos;
	}

}
