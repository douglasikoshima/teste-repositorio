package com.indracompany.catalogo.to;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class ContratoMatrizOfertaCriticaTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ContratoMatrizOfertaCriticaTO() {}
	
	public ContratoMatrizOfertaCriticaTO(Integer idContratoMatrizOfertaCritica) {
		this.idContratoMatrizOfertaCritica = idContratoMatrizOfertaCritica;
	}
	
	private Integer idContratoMatrizOfertaCritica;
	private ContratoMatrizOfertaTO contratoMatrizOfertaTO;
	private Integer numeroLinhaErro;
	private MatrizOfertaContratoCriticaTO matrizOfertaContratoCriticaTO;
	
	public Integer getIdContratoMatrizOfertaCritica() {
		return idContratoMatrizOfertaCritica;
	}

	public void setIdContratoMatrizOfertaCritica(
			Integer idContratoMatrizOfertaCritica) {
		this.idContratoMatrizOfertaCritica = idContratoMatrizOfertaCritica;
	}

	public Integer getNumeroLinhaErro() {
		return numeroLinhaErro;
	}

	public void setNumeroLinhaErro(Integer numeroLinhaErro) {
		this.numeroLinhaErro = numeroLinhaErro;
	}

	public ContratoMatrizOfertaTO getContratoMatrizOfertaTO() {
		return contratoMatrizOfertaTO;
	}

	public void setContratoMatrizOfertaTO(
			ContratoMatrizOfertaTO contratoMatrizOfertaTO) {
		this.contratoMatrizOfertaTO = contratoMatrizOfertaTO;
	}

	public MatrizOfertaContratoCriticaTO getMatrizOfertaContratoCriticaTO() {
		return matrizOfertaContratoCriticaTO;
	}

	public void setMatrizOfertaContratoCriticaTO(
			MatrizOfertaContratoCriticaTO matrizOfertaContratoCriticaTO) {
		this.matrizOfertaContratoCriticaTO = matrizOfertaContratoCriticaTO;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idContratoMatrizOfertaCritica: " + this.idContratoMatrizOfertaCritica}, ", ");
	}
}
