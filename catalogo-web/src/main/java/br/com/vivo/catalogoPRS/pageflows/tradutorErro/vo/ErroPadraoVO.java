package br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo;

import com.framework.vo.ValueObject;

/**
 * VO de ErroPadrao
 */
public class ErroPadraoVO extends ValueObject {
	private static final long serialVersionUID = 1L;

	/**
	 * CdClassificacao
	 */
	private Integer cdClassificacao;

	/**
	 * CdErroPadrao
	 */
	private Integer cdErroPadrao;

	/**
	 * TxMensagemErroPadrao
	 */
	private String txMensagemErroPadrao;

	/**
	 * @param id
	 */
	public ErroPadraoVO(String id) {
		super(id);
	}

	public ErroPadraoVO() {
		super();
	}

	/**
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;

		if ((id != null) && !id.trim().equals("")) {
			setCdErroPadrao(new Integer(id));
		}
	}

	public void setId() {
		this.id = getCdErroPadrao() + "";
	}

	/**
	 * Sets the value for CdClassificacao
	 */
	public void setCdClassificacao(Integer cdClassificacao) {
		this.cdClassificacao = cdClassificacao;
	}

	/**
	 * Gets the value for CdClassificacao
	 */
	public Integer getCdClassificacao() {
		return cdClassificacao;
	}

	/**
	 * Sets the value for CdErroPadrao
	 */
	public void setCdErroPadrao(Integer cdErroPadrao) {
		this.cdErroPadrao = cdErroPadrao;
	}

	/**
	 * Gets the value for CdErroPadrao
	 */
	public Integer getCdErroPadrao() {
		return cdErroPadrao;
	}

	/**
	 * Sets the value for TxMensagemErroPadrao
	 */
	public void setTxMensagemErroPadrao(String txMensagemErroPadrao) {
		this.txMensagemErroPadrao = txMensagemErroPadrao;
	}

	/**
	 * Gets the value for TxMensagemErroPadrao
	 */
	public String getTxMensagemErroPadrao() {
		return txMensagemErroPadrao;
	}

}
