package com.indracompany.catalogo.to;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * @author equipe Catalogo
 *
 */
public class SolComGrupoComArqItemTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public SolComGrupoComArqItemTO() {}
	
	private Integer idSolComGrupoComArq;
	private Integer idSolComGrupoComArqItem;
	private String acao;
	private String cdSolicitacaoComercial;
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
	 * @return the idSolComGrupoComArq
	 */
	public Integer getIdSolComGrupoComArq() {
		return idSolComGrupoComArq;
	}

	/**
	 * @param idSolComGrupoComArq the idSolComGrupoComArq to set
	 */
	public void setIdSolComGrupoComArq(Integer idSolComGrupoComArq) {
		this.idSolComGrupoComArq = idSolComGrupoComArq;
	}

	/**
	 * @return the idSolComGrupoComArqItem
	 */
	public Integer getIdSolComGrupoComArqItem() {
		return idSolComGrupoComArqItem;
	}

	/**
	 * @param idSolComGrupoComArqItem the idSolComGrupoComArqItem to set
	 */
	public void setIdSolComGrupoComArqItem(Integer idSolComGrupoComArqItem) {
		this.idSolComGrupoComArqItem = idSolComGrupoComArqItem;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{
				"idSolComGrupoComArqItem: " + this.idSolComGrupoComArqItem, 
				"erros: " + this.erros
				}, ", ");
	}

}