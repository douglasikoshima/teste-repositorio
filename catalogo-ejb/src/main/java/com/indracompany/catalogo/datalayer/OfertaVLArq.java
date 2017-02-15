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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@SequenceGenerator(name = "OFERTAVLARQ_SQ", sequenceName = "CATALOGOPRS_OW.OFERTAVLARQ_SQ", allocationSize = 1)
@Table(name = "OFERTAVLARQ", schema = "CATALOGOPRS_OW")
public class OfertaVLArq implements Serializable {

	private static final long serialVersionUID = -7701743260715568255L;

	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OFERTAVLARQ_SQ")
    @Column(name = "IDOFERTAVLARQ")
    private Integer idOfertaVLArq;
    
    @Column(name = "NMARQUIVO")
    private String nmArquivo;
    
    @Column(name = "DTIMPORTACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtImportacao;
    
    @Column(name = "NMUSUARIOIMPORTACAO")
    private String nmUsuarioImportacao;
    
    @Column(name = "TPIMPORTACAO")
    private String tpImportacao;
    
    @Column(name = "DTPROCESSAMENTO")
    private Date dtProcessamento;
    
    @Column(name = "DSERRO")
    private String dsErro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDMATRIZOFERTASTATUSIMPORTACAO")
    private MatrizOfertaStatusImportacao matrizOfertaStatusImportacao;
    

	public String getDsErro() {
		return dsErro;
	}

	public void setDsErro(String dsErro) {
		this.dsErro = dsErro;
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

	public Integer getIdOfertaVLArq() {
		return idOfertaVLArq;
	}

	public void setIdOfertaVLArq(Integer idOfertaVLArq) {
		this.idOfertaVLArq = idOfertaVLArq;
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

	public String getTpImportacao() {
		return tpImportacao;
	}

	public void setTpImportacao(String tpImportacao) {
		this.tpImportacao = tpImportacao;
	}

	public MatrizOfertaStatusImportacao getMatrizOfertaStatusImportacao() {
		return matrizOfertaStatusImportacao;
	}

	public void setMatrizOfertaStatusImportacao(
			MatrizOfertaStatusImportacao matrizOfertaStatusImportacao) {
		this.matrizOfertaStatusImportacao = matrizOfertaStatusImportacao;
	}
    
}
