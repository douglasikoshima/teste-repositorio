package br.com.indrasistemas.vivoservices.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public abstract class BasePessoaTO extends BaseTransferObject {

	private Long idPessoa;
	private String nome;
	private String nomeMeio;
	private String sobrenome;

	/**
	 * Access method for the idPessoa property.
	 *
	 * @return   the current value of the idPessoa property
	 */
	public Long getIdPessoa() {
		return idPessoa;
	}

	/**
	 * Sets the value of the idPessoa property.
	 *
	 * @param aIdPessoa the new value of the idPessoa property
	 */
	public void setIdPessoa(Long aIdPessoa) {
		idPessoa = aIdPessoa;
	}

	/**
	 * Access method for the nome property.
	 *
	 * @return   the current value of the nome property
	 */
	public java.lang.String getNome() {
		return nome;
	}

	public String getNomeMeio() {
		return nomeMeio;
	}

	public void setNomeMeio(String nomeMeio) {
		this.nomeMeio = nomeMeio;
	}

	public String getSobrenome() {
		return sobrenome;
	}

	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
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
