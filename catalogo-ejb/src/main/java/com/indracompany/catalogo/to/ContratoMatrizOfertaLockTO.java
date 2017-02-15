package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang.StringUtils;

/**
 * @author Luiz Pereira
 *
 */
public class ContratoMatrizOfertaLockTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ContratoMatrizOfertaLockTO() {}
	
	public ContratoMatrizOfertaLockTO(Integer idContratoMatrizOfertaLock) {
		this.idContratoMatrizOfertaLock = idContratoMatrizOfertaLock;
	}
	
	private Integer idContratoMatrizOfertaLock;
	private String nmArquivo;
	private String inStatus;
	private Date dtInclusao;
	private String nmUsuarioCriacao;
	private Date dtAlteracao;
	private String nmUsuarioAlteracao;
	private String inLiberar;
	private String inImportacao;
	private String inValidado;
	
	public Date getDtAlteracao() {
		return dtAlteracao;
	}

	public void setDtAlteracao(Date dtAlteracao) {
		this.dtAlteracao = dtAlteracao;
	}

	public Date getDtInclusao() {
		return dtInclusao;
	}

	public void setDtInclusao(Date dtInclusao) {
		this.dtInclusao = dtInclusao;
	}

	public Integer getIdContratoMatrizOfertaLock() {
		return idContratoMatrizOfertaLock;
	}

	public void setIdContratoMatrizOfertaLock(Integer idContratoMatrizOfertaLock) {
		this.idContratoMatrizOfertaLock = idContratoMatrizOfertaLock;
	}

	public String getInStatus() {
		return inStatus;
	}

	public void setInStatus(String inStatus) {
		this.inStatus = inStatus;
	}

	public String getNmArquivo() {
		return nmArquivo;
	}

	public void setNmArquivo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
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

	public String getInImportacao() {
		return inImportacao;
	}

	public void setInImportacao(String inImportacao) {
		this.inImportacao = inImportacao;
	}

	public String getInLiberar() {
		return inLiberar;
	}

	public void setInLiberar(String inLiberar) {
		this.inLiberar = inLiberar;
	}
	
	public String getInValidado() {
		return inValidado;
	}

	public void setInValidado(String inValidado) {
		this.inValidado = inValidado;
	}

	@Override
	public String toString() {
		return StringUtils.join(new String[]{"idContratoMatrizOfertaLock: " + this.idContratoMatrizOfertaLock, "nmArquivo: " + this.nmArquivo}, ", ");
	}
}
