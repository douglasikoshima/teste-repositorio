package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class PlataformaTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public PlataformaTO() {}
	
	public PlataformaTO(Integer idPlataforma) {
		this.idPlataforma = idPlataforma;
	}

	public PlataformaTO(Integer idPlataforma, String nmPlataforma) {
		super();
		this.idPlataforma = idPlataforma;
		this.nmPlataforma = nmPlataforma;
	}
	
	private Integer idPlataforma;
	private Date dtCriacao;
    private Date dtUltimaAlteracao;
	private String nmPlataforma;
	private String nmUsuarioAlteracao;
	private String nmUsuarioCriacao;

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

	public Integer getIdPlataforma() {
		return idPlataforma;
	}

	public void setIdPlataforma(Integer idPlataforma) {
		this.idPlataforma = idPlataforma;
	}

	public String getNmPlataforma() {
		return nmPlataforma;
	}

	public void setNmPlataforma(String nmPlataforma) {
		this.nmPlataforma = nmPlataforma;
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

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idPlataforma: " + this.idPlataforma, "nmPlataforma: " + this.nmPlataforma}, ", ");
	}
}
