package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "FAMILIA_SQ", sequenceName = "CATALOGOPRS_OW.FAMILIA_SQ", allocationSize = 1)
@NamedQuery(name = "Familia.findAll", query = "select f from Familia f where f.inDisponivel = 'S' order by f.nmFamilia " )
@Table(name="FAMILIA", schema = "CATALOGOPRS_OW")
public class Familia implements Serializable {

    private static final long serialVersionUID = 7841080083414783226L;
 
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FAMILIA_SQ")
    @Column(name = "IDFAMILIA", unique=true, nullable=false, precision=22)
    private Integer idFamilia;
    
    @Column(name = "CDFAMILIA", insertable = true, updatable = false)
    private String cdFamilia;
    
    @Column(name = "NMFAMILIA", insertable = true, updatable = true)
    private String nmFamilia;
    
    @Column(name = "DSFAMILIA", insertable = true, updatable = true)
    private String dsFamilia;
    
    @Column(name = "INCRIACAOCATALOGO", insertable = true, updatable = false)
    private String inCriacaoCatalogo;
    
    @Column(name = "INDISPONIVEL", insertable = true, updatable = true)
    private String inDisponivel;
    
    @Column(name = "INALTERACAOCATALOGO", insertable = true, updatable = true)
    private String inAlteracaoCatalogo;    
    
    @OneToMany(mappedBy="familia")
    private List<Categoria> categoriaList;

    public Familia() {}
    
    public Familia(Integer idFamilia) {
        this();
        this.idFamilia = idFamilia;
    }

    public List<Categoria> getCategoriaList() {
        return categoriaList;
    }

    public void setCategoriaList(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    public String getCdFamilia() {
        return cdFamilia;
    }

    public void setCdFamilia(String cdFamilia) {
        this.cdFamilia = cdFamilia;
    }

    public String getDsFamilia() {
        return dsFamilia;
    }

    public void setDsFamilia(String dsFamilia) {
        this.dsFamilia = dsFamilia;
    }

    public Integer getIdFamilia() {
        return idFamilia;
    }

    public void setIdFamilia(Integer idFamilia) {
        this.idFamilia = idFamilia;
    }

    public String getInCriacaoCatalogo() {
        return inCriacaoCatalogo;
    }

    public void setInCriacaoCatalogo(String inCriacaoCatalogo) {
        this.inCriacaoCatalogo = inCriacaoCatalogo;
    }

    public String getInDisponivel() {
        return inDisponivel;
    }

    public void setInDisponivel(String inDisponivel) {
        this.inDisponivel = inDisponivel;
    }

    public String getNmFamilia() {
        return nmFamilia;
    }

    public void setNmFamilia(String nmFamilia) {
        this.nmFamilia = nmFamilia;
    }

    public String getInAlteracaoCatalogo() {
        return inAlteracaoCatalogo;
    }

    public void setInAlteracaoCatalogo(String inAlteracaoCatalogo) {
        this.inAlteracaoCatalogo = inAlteracaoCatalogo;
    }
    
    
}