package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import javax.persistence.*;

import java.util.Date;
import java.util.List;

/**
 * @author Luiz Pereira
 *
 */
@Entity
@Table(name="SISTEMA", schema = "CATALOGOPRS_OW")
public class Sistema implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public Sistema() {}
    
    public Sistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    @Id
    @Column(name = "IDSISTEMA")
    private Integer idSistema;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTCRIACAO")
    private Date dtCriacao;

    @Temporal( TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
    private Date dtUltimaAlteracao;

    @Column(name = "NMSISTEMA")
    private String nmSistema;

    @Column(name = "NMUSUARIOALTERACAO")
    private String nmUsuarioAlteracao;

    @Column(name = "NMUSUARIOCRIACAO")
    private String nmUsuarioCriacao;

    //bi-directional many-to-one association to Acessoplano
    @OneToMany(mappedBy="sistema", fetch = FetchType.LAZY)
    private List<AcessoPlano> acessoPlanoLists;

    //bi-directional many-to-one association to Acessoservico
    @OneToMany(mappedBy="sistema", fetch = FetchType.LAZY)
    private List<AcessoServico> acessoServicoList;

    //bi-directional many-to-one association to Categoriasistema
    @OneToMany(mappedBy="sistema", fetch = FetchType.LAZY)
    private List<CategoriaSistema> categoriaSistemaList;

    //bi-directional many-to-one association to Fabricantesistema
    @OneToMany(mappedBy="sistema", fetch = FetchType.LAZY)
    private List<FabricanteSistema> fabricanteSistemaList;

    //bi-directional many-to-one association to Sistemaplano
    @OneToMany(mappedBy="sistema", fetch = FetchType.LAZY)
    private List<SistemaPlano> sistemaPlanoList;

    //bi-directional many-to-one association to Sistemaproduto
    @OneToMany(mappedBy="sistema", fetch = FetchType.LAZY)
    private List<SistemaProduto> sistemaProdutoList;

    //bi-directional many-to-one association to Sistemaservico
    @OneToMany(mappedBy="sistema", fetch = FetchType.LAZY)
    private List<SistemaServico> sistemaServicoList;

    public List<AcessoPlano> getAcessoPlanoLists() {
        return acessoPlanoLists;
    }

    public void setAcessoPlanoLists(List<AcessoPlano> acessoPlanoLists) {
        this.acessoPlanoLists = acessoPlanoLists;
    }

    public List<AcessoServico> getAcessoServicoList() {
        return acessoServicoList;
    }

    public void setAcessoServicoList(List<AcessoServico> acessoServicoList) {
        this.acessoServicoList = acessoServicoList;
    }

    public List<CategoriaSistema> getCategoriaSistemaList() {
        return categoriaSistemaList;
    }

    public void setCategoriaSistemaList(List<CategoriaSistema> categoriaSistemaList) {
        this.categoriaSistemaList = categoriaSistemaList;
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

    public List<FabricanteSistema> getFabricanteSistemaList() {
        return fabricanteSistemaList;
    }

    public void setFabricanteSistemaList(
            List<FabricanteSistema> fabricanteSistemaList) {
        this.fabricanteSistemaList = fabricanteSistemaList;
    }

    public Integer getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Integer idSistema) {
        this.idSistema = idSistema;
    }

    public String getNmSistema() {
        return nmSistema;
    }

    public void setNmSistema(String nmSistema) {
        this.nmSistema = nmSistema;
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

    public List<SistemaPlano> getSistemaPlanoList() {
        return sistemaPlanoList;
    }

    public void setSistemaPlanoList(List<SistemaPlano> sistemaPlanoList) {
        this.sistemaPlanoList = sistemaPlanoList;
    }

    public List<SistemaProduto> getSistemaProdutoList() {
        return sistemaProdutoList;
    }

    public void setSistemaProdutoList(List<SistemaProduto> sistemaProdutoList) {
        this.sistemaProdutoList = sistemaProdutoList;
    }

    public List<SistemaServico> getSistemaServicoList() {
        return sistemaServicoList;
    }

    public void setSistemaServicoList(List<SistemaServico> sistemaServicoList) {
        this.sistemaServicoList = sistemaServicoList;
    }

}