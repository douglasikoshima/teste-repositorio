package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="CATEGORIASISTEMA", schema = "CATALOGOPRS_OW")
@SequenceGenerator(name = "CATEGORIASISTEMA_SQ", sequenceName = "CATALOGOPRS_OW.CATEGORIASISTEMA_SQ", allocationSize = 1)
public class CategoriaSistema implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public CategoriaSistema() {}
    public CategoriaSistema(Integer idCategoria, Integer idSistema) {
        this.categoria = new Categoria(idCategoria);
        this.cdCodigo = categoria.getCdCategoria();
        this.sistema = new Sistema(idSistema);
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CATEGORIASISTEMA_SQ")
    @Column(name = "IDCATEGORIASISTEMA")
    private Integer idCategoriaSistema;

    @Column(name = "CDCODIGO")
    private String cdCodigo;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
    private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
    private Date dtUltimaAlteracao;

    @Column(name = "NMUSUARIOALTERACAO")
    private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
    private String nmUsuarioCriacao;

    //bi-directional many-to-one association to Categoria
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDCATEGORIA", nullable=false)
    private Categoria categoria;

    //bi-directional many-to-one association to Sistema
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDSISTEMA", nullable=false)
    private Sistema sistema;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getCdCodigo() {
        return cdCodigo;
    }

    public void setCdCodigo(String cdCodigo) {
        this.cdCodigo = cdCodigo;
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

    public Integer getIdCategoriaSistema() {
        return idCategoriaSistema;
    }

    public void setIdCategoriaSistema(Integer idCategoriaSistema) {
        this.idCategoriaSistema = idCategoriaSistema;
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

    public Sistema getSistema() {
        return sistema;
    }

    public void setSistema(Sistema sistema) {
        this.sistema = sistema;
    }
}