package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class ContratoMatrizOfertaTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ContratoMatrizOfertaTO() {}
	
	public ContratoMatrizOfertaTO(Integer idContratoMatriz) {
		this.idContratoMatriz = idContratoMatriz;
	}
	
	private Integer idContratoMatriz;
	private String codigoPlano;
	private String codigoServico;
	private String siglaCsa;
	private String valorContrato;
	private String nmUsuarioCriacao;
	private Date dtCriacao;
	private Integer numeroLinha;
	
	public String getCodigoPlano() {
		return codigoPlano;
	}

	public void setCodigoPlano(String codigoPlano) {
		this.codigoPlano = codigoPlano;
	}

	public String getCodigoServico() {
		return codigoServico;
	}

	public void setCodigoServico(String codigoServico) {
		this.codigoServico = codigoServico;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Integer getIdContratoMatriz() {
		return idContratoMatriz;
	}

	public void setIdContratoMatriz(Integer idContratoMatriz) {
		this.idContratoMatriz = idContratoMatriz;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public String getSiglaCsa() {
		return siglaCsa;
	}

	public void setSiglaCsa(String siglaCsa) {
		this.siglaCsa = siglaCsa;
	}

	public String getValorContrato() {
		return valorContrato;
	}

	public void setValorContrato(String valorContrato) {
		this.valorContrato = valorContrato;
	}
	
	public Integer getNumeroLinha() {
		return numeroLinha;
	}

	public void setNumeroLinha(Integer numeroLinha) {
		this.numeroLinha = numeroLinha;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idContratoMatriz: " + this.idContratoMatriz, "codigoPlano: " + this.codigoPlano}, ", ");
	}
}
