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
@SequenceGenerator(name = "SERVICOARQ_SQ", sequenceName = "CATALOGOPRS_OW.SERVICOARQ_SQ", allocationSize = 1)
@Table(name = "SERVICOARQ", schema = "CATALOGOPRS_OW")
public class ServicoArq implements Serializable {

    private static final long serialVersionUID = 4336618626981889275L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVICOARQ_SQ")
    @Column(name = "IDSERVICOARQ")
    private Integer idServicoArq;
    
    @Column(name = "NMARQUIVO")
    private String nmArquivo;
    
    @Column(name = "DTIMPORTACAO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtImportacao;
    
    @Column(name = "NMUSUARIOIMPORTACAO")
    private String nmUsuarioImportacao;
    
    @Column(name = "DTPROCESSAMENTO")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dtProcessamento;
    
    @Column(name = "DSCERRO")
    private String dscErro;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IDMATRIZOFERTASTATUSIMPORTACAO")
    private MatrizOfertaStatusImportacao matrizOfertaStatusImportacao;

    public String getDscErro() {
        return dscErro;
    }

    public Date getDtImportacao() {
        return dtImportacao;
    }

    public Date getDtProcessamento() {
        return dtProcessamento;
    }

    public Integer getIdServicoArq() {
        return idServicoArq;
    }

    public MatrizOfertaStatusImportacao getMatrizOfertaStatusImportacao() {
        return matrizOfertaStatusImportacao;
    }

    public String getNmArquivo() {
        return nmArquivo;
    }

    public String getNmUsuarioImportacao() {
        return nmUsuarioImportacao;
    }

    public void setDscErro(String dscErro) {
        this.dscErro = dscErro;
    }

    public void setDtImportacao(Date dtImportacao) {
        this.dtImportacao = dtImportacao;
    }

    public void setDtProcessamento(Date dtProcessamento) {
        this.dtProcessamento = dtProcessamento;
    }

    public void setIdServicoArq(Integer idServicoArq) {
        this.idServicoArq = idServicoArq;
    }

    public void setMatrizOfertaStatusImportacao(MatrizOfertaStatusImportacao matrizOfertaStatusImportacao) {
        this.matrizOfertaStatusImportacao = matrizOfertaStatusImportacao;
    }

    public void setNmArquivo(String nmArquivo) {
        this.nmArquivo = nmArquivo;
    }

    public void setNmUsuarioImportacao(String nmUsuarioImportacao) {
        this.nmUsuarioImportacao = nmUsuarioImportacao;
    }
    
}
