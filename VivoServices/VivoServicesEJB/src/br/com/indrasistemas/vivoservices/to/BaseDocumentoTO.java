package br.com.indrasistemas.vivoservices.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public abstract class BaseDocumentoTO extends BaseTransferObject {

	public static final String SIGLA_CLASSIFICACAO_CPF = "CPF";

	public static final String SIGLA_CLASSIFICACAO_RNE = "RNE";

	public static final String SIGLA_CLASSIFICACAO_PASSAPORTE = "PAS";

	private Long id;

	private String numero;

	private BaseTipoDocumentoTO tipoDocumento;

	private BaseUfTO uf;

	private BasePaisTO pais;

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
	 * @param aIdDocumento the new value of the id property
	 */
	public void setId(Long aIdDocumento) {
		id = aIdDocumento;
	}

	/**
	 * Access method for the numero property.
	 *
	 * @return   the current value of the numero property
	 */
	public java.lang.String getNumero() {
		return numero;
	}

	/**
	 * Sets the value of the numero property.
	 *
	 * @param aNumero the new value of the numero property
	 */
	public void setNumero(java.lang.String aNumero) {
		numero = aNumero;
	}

	public BaseTipoDocumentoTO getTipoDocumento() {
		return tipoDocumento;
	}

	public void setTipoDocumento(BaseTipoDocumentoTO tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}

	/**
	 * Access method for the uf property.
	 *
	 * @return   the current value of the uf property
	 */
	public BaseUfTO getUf() {
		return uf;
	}

	/**
	 * Sets the value of the uf property.
	 *
	 * @param aUf the new value of the uf property
	 */
	public void setUf(BaseUfTO aUf) {
		uf = aUf;
	}

	/**
	 * Access method for the pais property.
	 *
	 * @return   the current value of the pais property
	 */
	public BasePaisTO getPais() {
		return pais;
	}

	/**
	 * Sets the value of the pais property.
	 *
	 * @param aPais the new value of the pais property
	 */
	public void setPais(BasePaisTO aPais) {
		pais = aPais;
	}

}
