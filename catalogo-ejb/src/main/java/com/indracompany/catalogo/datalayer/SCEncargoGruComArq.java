package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;



/**
 * @author equipe Catalogo
 *
 */
@Entity
@SequenceGenerator(name = "SCENCARGOGRUCOMARQ_SQ", sequenceName = "CATALOGOPRS_OW.SCENCARGOGRUCOMARQ_SQ", allocationSize = 1)
@Table(name = "SCENCARGOGRUCOMARQ", schema = "CATALOGOPRS_OW")
public class SCEncargoGruComArq implements Serializable {

	private static final long serialVersionUID = 1L;

	public SCEncargoGruComArq() {}
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCENCARGOGRUCOMARQ_SQ")
	@Column(name = "IDSCENCARGOGRUCOMARQ")
	private Integer idSCEncargoGruComArq;

	//bi-directional many-to-one association to MatrizOfertaStatusImportacao
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="IDMATRIZOFERTASTATUSIMPORTACAO")
	private MatrizOfertaStatusImportacao matrizOfertaStatusImportacao;

	@Column(name = "NMARQUIVO")
	private String nmArquivo;

    @Column(name = "DTIMPORTACAO")
	private Date dtImportacao;
	
	@Column(name = "NMUSUARIOIMPORTACAO")
	private String nmUsuarioImportacao;
	
	@Column(name = "DTPROCESSAMENTO")
	private Date dtProcessamento;
	
	@Column(name = "DSCERRO")
	private String descErro;

	
	public String getDescErro() {
		return descErro;
	}

	public void setDescErro(String descErro) {
		this.descErro = descErro;
	}

	public Date getDtImportacao() {
		return dtImportacao;
	}

	public void setDtImportacao(Date dtImportacao) {
		this.dtImportacao = dtImportacao;
	}

	public Date getDtProcessamento() {
		return dtProcessamento;
	}

	public void setDtProcessamento(Date dtProcessamento) {
		this.dtProcessamento = dtProcessamento;
	}

	public Integer getIdSCEncargoGruComArq() {
		return idSCEncargoGruComArq;
	}

	public void setIdSCEncargoGruComArq(Integer idSCEncargoGruComArq) {
		this.idSCEncargoGruComArq = idSCEncargoGruComArq;
	}

	public String getNmArquivo() {
		return nmArquivo;
	}

	public void setNmArquivo(String nmArquivo) {
		this.nmArquivo = nmArquivo;
	}

	public String getNmUsuarioImportacao() {
		return nmUsuarioImportacao;
	}

	public void setNmUsuarioImportacao(String nmUsuarioImportacao) {
		this.nmUsuarioImportacao = nmUsuarioImportacao;
	}

	public MatrizOfertaStatusImportacao getMatrizOfertaStatusImportacao() {
		return matrizOfertaStatusImportacao;
	}

	public void setMatrizOfertaStatusImportacao(
			MatrizOfertaStatusImportacao matrizOfertaStatusImportacao) {
		this.matrizOfertaStatusImportacao = matrizOfertaStatusImportacao;
	}


}