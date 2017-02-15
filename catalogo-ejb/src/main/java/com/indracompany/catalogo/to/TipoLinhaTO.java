package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class TipoLinhaTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public TipoLinhaTO() {}
	
	public TipoLinhaTO(Integer idTipoLinha) {
		this.idTipoLinha = idTipoLinha;
	}
	
	public TipoLinhaTO(Integer idTipoLinha, String sgTipoLinha) {
		this.idTipoLinha = idTipoLinha;
		this.sgTipoLinha = sgTipoLinha;
	}
	private Integer idTipoLinha;
	private String dscTipoLinha;
	private Date dtCriacao;
	private String nmUsuarioCriacao;
	private Date dtUltimaAlteracao;
	private String nmUsuarioAlteracao;
	private String sgTipoLinha;
	
	public String getDscTipoLinha() {
		return dscTipoLinha;
	}

	public void setDscTipoLinha(String dscTipoLinha) {
		this.dscTipoLinha = dscTipoLinha;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}

	public Date getDtUltimaAlteracao() {
		return dtUltimaAlteracao;
	}

	public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
		this.dtUltimaAlteracao = dtUltimaAlteracao;
	}

	public Integer getIdTipoLinha() {
		return idTipoLinha;
	}

	public void setIdTipoLinha(Integer idTipoLinha) {
		this.idTipoLinha = idTipoLinha;
	}

	public String getNmUsuarioAlteracao() {
		return nmUsuarioAlteracao;
	}

	public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
		this.nmUsuarioAlteracao = nmUsuarioAlteracao;
	}

	public String getNmUsuarioCriacao() {
		return nmUsuarioCriacao;
	}

	public void setNmUsuarioCriacao(String nmUsuarioCriacao) {
		this.nmUsuarioCriacao = nmUsuarioCriacao;
	}

	public String getSgTipoLinha() {
		return sgTipoLinha;
	}

	public void setSgTipoLinha(String sgTipoLinha) {
		this.sgTipoLinha = sgTipoLinha;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idTipoLinha: " + this.idTipoLinha, "dscTipoLinha: " + this.dscTipoLinha}, ", ");
	}
}
