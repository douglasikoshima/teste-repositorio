package com.indracompany.catalogo.to;

import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class MatrizOfertaContratoCriticaTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public MatrizOfertaContratoCriticaTO() {}
	
	public MatrizOfertaContratoCriticaTO(Integer idMatrizOfertaArquivoCritica) {
		this.idMatrizOfertaArquivoCritica = idMatrizOfertaArquivoCritica;
	}
	
	private Integer idMatrizOfertaArquivoCritica;
	private String dscCriticaimportacao;

	public String getDscCriticaimportacao() {
		return dscCriticaimportacao;
	}

	public void setDscCriticaimportacao(String dscCriticaimportacao) {
		this.dscCriticaimportacao = dscCriticaimportacao;
	}

	public Integer getIdMatrizOfertaArquivoCritica() {
		return idMatrizOfertaArquivoCritica;
	}

	public void setIdMatrizOfertaArquivoCritica(Integer idMatrizOfertaArquivoCritica) {
		this.idMatrizOfertaArquivoCritica = idMatrizOfertaArquivoCritica;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idMatrizOfertaArquivoCritica: " + this.idMatrizOfertaArquivoCritica, "dscCriticaimportacao: " + this.dscCriticaimportacao}, ", ");
	}
}
