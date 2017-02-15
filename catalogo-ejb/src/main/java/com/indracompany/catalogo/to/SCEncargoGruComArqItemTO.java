package com.indracompany.catalogo.to;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * @author equipe Catalogo
 *
 */
public class SCEncargoGruComArqItemTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public SCEncargoGruComArqItemTO() {}
	
	private Integer idSCEncargoGruComArq;
	private Integer idSCEncargoGruComArqItem;
	private String acao;
	private String cdSolicitacaoComercial;
	private String cdEncargo;
	private String cdPlanoFinanciamento;
	private String cdGrupoComercial;
	private String cdPlanoMinutos;
	private String cdAreaConcorrencia;
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
	 * @return the cdAreaConcorrencia
	 */
	public String getCdAreaConcorrencia() {
		return cdAreaConcorrencia;
	}

	/**
	 * @param cdAreaConcorrencia the cdAreaConcorrencia to set
	 */
	public void setCdAreaConcorrencia(String cdAreaConcorrencia) {
		this.cdAreaConcorrencia = cdAreaConcorrencia;
	}

	/**
	 * @return the cdGrupoComercial
	 */
	public String getCdGrupoComercial() {
		return cdGrupoComercial;
	}

	/**
	 * @param cdGrupoComercial the cdGrupoComercial to set
	 */
	public void setCdGrupoComercial(String cdGrupoComercial) {
		this.cdGrupoComercial = cdGrupoComercial;
	}

	/**
	 * @return the cdPlanoMinutos
	 */
	public String getCdPlanoMinutos() {
		return cdPlanoMinutos;
	}

	/**
	 * @param cdPlanoMinutos the cdPlanoMinutos to set
	 */
	public void setCdPlanoMinutos(String cdPlanoMinutos) {
		this.cdPlanoMinutos = cdPlanoMinutos;
	}

	/**
	 * @return the cdSolicitacaoComercial
	 */
	public String getCdSolicitacaoComercial() {
		return cdSolicitacaoComercial;
	}

	/**
	 * @param cdSolicitacaoComercial the cdSolicitacaoComercial to set
	 */
	public void setCdSolicitacaoComercial(String cdSolicitacaoComercial) {
		this.cdSolicitacaoComercial = cdSolicitacaoComercial;
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
	 * @return the idSCEncargoGruComArq
	 */
	public Integer getIdSCEncargoGruComArq() {
		return idSCEncargoGruComArq;
	}

	/**
	 * @param idSCEncargoGruComArq the idSCEncargoGruComArq to set
	 */
	public void setIdSCEncargoGruComArq(Integer idSCEncargoGruComArq) {
		this.idSCEncargoGruComArq = idSCEncargoGruComArq;
	}

	/**
	 * @return the idSCEncargoGruComArqItem
	 */
	public Integer getIdSCEncargoGruComArqItem() {
		return idSCEncargoGruComArqItem;
	}

	/**
	 * @param idSCEncargoGruComArqItem the idSCEncargoGruComArqItem to set
	 */
	public void setIdSCEncargoGruComArqItem(Integer idSCEncargoGruComArqItem) {
		this.idSCEncargoGruComArqItem = idSCEncargoGruComArqItem;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{
				"idSCEncargoGruComArqItem: " + this.idSCEncargoGruComArqItem, 
				"erros: " + this.erros
				}, ", ");
	}

	/**
	 * @return the cdEncargo
	 */
	public String getCdEncargo() {
		return cdEncargo;
	}

	/**
	 * @param cdEncargo the cdEncargo to set
	 */
	public void setCdEncargo(String cdEncargo) {
		this.cdEncargo = cdEncargo;
	}

	/**
	 * @return the cdPlanoFinanciamento
	 */
	public String getCdPlanoFinanciamento() {
		return cdPlanoFinanciamento;
	}

	/**
	 * @param cdPlanoFinanciamento the cdPlanoFinanciamento to set
	 */
	public void setCdPlanoFinanciamento(String cdPlanoFinanciamento) {
		this.cdPlanoFinanciamento = cdPlanoFinanciamento;
	}

}