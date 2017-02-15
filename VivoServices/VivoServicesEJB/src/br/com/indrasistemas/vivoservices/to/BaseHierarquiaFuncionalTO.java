package br.com.indrasistemas.vivoservices.to;

import java.util.List;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public abstract class BaseHierarquiaFuncionalTO extends BaseTransferObject {

	private Long id;

	private String nome;

	private List cargos;

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
	 * @param aIdHierarquiaFuncional the new value of the id property
	 */
	public void setId(Long aIdHierarquiaFuncional) {
		id = aIdHierarquiaFuncional;
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
	 * Access method for the cargos property.
	 * @return   the current value of the cargos property
	 */
	public List getCargos() {
		return cargos;
	}

	/**
	 * Sets the value of the cargos property.
	 * @param aCargos the new value of the cargos property
	 */
	public void setCargos(List aCargos) {
		cargos = aCargos;
	}

}
