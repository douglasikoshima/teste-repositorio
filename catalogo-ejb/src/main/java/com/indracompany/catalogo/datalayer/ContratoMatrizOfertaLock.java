package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



/**
 * @author Luiz Pereira
 *
 */
@Entity
@SequenceGenerator(name = "CONTRATOMATRIZOFERTALOCK_SQ", sequenceName = "CATALOGOPRS_OW.CONTRATOMATRIZOFERTALOCK_SQ", allocationSize = 1)
@Table(name = "CONTRATOMATRIZOFERTALOCK", schema = "CATALOGOPRS_OW")
public class ContratoMatrizOfertaLock implements Serializable {

	private static final long serialVersionUID = 1L;

	public ContratoMatrizOfertaLock() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CONTRATOMATRIZOFERTALOCK_SQ")
	@Column(name = "IDCONTRATOMATRIZOFERTALOCK")
	private Integer idContratoMatrizOfertaLock;

	@Column(name = "NMARQUIVO")
	private String nmArquivo;

	@Column(name = "INSTATUS")
	private String inStatus;
	
	@Column(name = "DTINCLUSAO")
	private Date dtInclusao;
	
	@Column(name = "NMUSUARIOCRIACAO")
	private String nmUsuarioCriacao;
	
	@Column(name = "DTALTERACAO")
	private Date dtAlteracao;

	@Column(name = "NMUSUARIOALTERACAO")
	private String nmUsuarioAlteracao;
	
	@Column(name = "INLIBERAR")
	private String inLiberar;
	
	@Column(name = "INIMPORTACAO")
	private String inImportacao;
	
	@Column(name = "INVALIDADO")
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
}