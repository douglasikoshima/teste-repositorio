package com.indracompany.catalogo.to;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * @author equipe Catalogo
 *
 */
public class ServicoServicoArqItemTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ServicoServicoArqItemTO() {}
	
	private Integer idServicoServicoArq;
	private Integer idServicoServicoArqItem;
	private String acao;
	private String cdServicoMestre;
	private String sgTipoRelacionamento;
	private String cdServicoSubordinado;
	private String erros;

	/**
	 * @return the acao
	 */
	public String getAcao() {
		return acao;
	}

	/**
	 * @param acao the acao to set
	 */
	public void setAcao(String acao) {
		this.acao = acao;
	}

	/**
	 * @return the cdServicoMestre
	 */
	public String getCdServicoMestre() {
		return cdServicoMestre;
	}

	/**
	 * @param cdServicoMestre the cdServicoMestre to set
	 */
	public void setCdServicoMestre(String cdServicoMestre) {
		this.cdServicoMestre = cdServicoMestre;
	}

	/**
	 * @return the cdServicoSubordinado
	 */
	public String getCdServicoSubordinado() {
		return cdServicoSubordinado;
	}

	/**
	 * @param cdServicoSubordinado the cdServicoSubordinado to set
	 */
	public void setCdServicoSubordinado(String cdServicoSubordinado) {
		this.cdServicoSubordinado = cdServicoSubordinado;
	}

	/**
	 * @return the erros
	 */
	public String getErros() {
		return erros;
	}
    
    public String getErrosFormatado() {
        return this.getErros().replaceAll("\\|", "<br/>");
    }

	/**
	 * @param erros the erros to set
	 */
	public void setErros(String erros) {
		this.erros = erros;
	}

	/**
	 * @return the idServicoServicoArq
	 */
	public Integer getIdServicoServicoArq() {
		return idServicoServicoArq;
	}

	/**
	 * @param idServicoServicoArq the idServicoServicoArq to set
	 */
	public void setIdServicoServicoArq(Integer idServicoServicoArq) {
		this.idServicoServicoArq = idServicoServicoArq;
	}

	/**
	 * @return the idServicoServicoArqItem
	 */
	public Integer getIdServicoServicoArqItem() {
		return idServicoServicoArqItem;
	}

	/**
	 * @param idServicoServicoArqItem the idServicoServicoArqItem to set
	 */
	public void setIdServicoServicoArqItem(Integer idServicoServicoArqItem) {
		this.idServicoServicoArqItem = idServicoServicoArqItem;
	}

	/**
	 * @return the sgTipoRelacionamento
	 */
	public String getSgTipoRelacionamento() {
		return sgTipoRelacionamento;
	}

	/**
	 * @param sgTipoRelacionamento the sgTipoRelacionamento to set
	 */
	public void setSgTipoRelacionamento(String sgTipoRelacionamento) {
		this.sgTipoRelacionamento = sgTipoRelacionamento;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{
				"idServicoServicoArqItem: " + this.idServicoServicoArqItem, 
				"erros: " + this.erros
				}, ", ");
	}

}