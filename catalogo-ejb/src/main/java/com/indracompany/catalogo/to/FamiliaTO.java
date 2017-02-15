package com.indracompany.catalogo.to;

import java.io.Serializable;
import java.util.List;

public class FamiliaTO implements Serializable {

    private static final long serialVersionUID = -8616015189754490657L;

    private Integer idFamilia;
    private String cdFamilia;
    private String nmFamilia;
    private String dsFamilia;
    private Boolean inCriacaoCatalogo;
    private Boolean inDisponivel;
    private List<CategoriaTO> categoriaTOList;
    
    public FamiliaTO() {}
    public FamiliaTO(Integer idFamilia) {
        this();
        this.idFamilia = idFamilia;
    }  
    public List<CategoriaTO> getCategoriaTOList() {
        return categoriaTOList;
    }
    public void setCategoriaTOList(List<CategoriaTO> categoriaTOList) {
        this.categoriaTOList = categoriaTOList;
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
    public Boolean getInCriacaoCatalogo() {
        return inCriacaoCatalogo;
    }
    public void setInCriacaoCatalogo(Boolean inCriacaoCatalogo) {
        this.inCriacaoCatalogo = inCriacaoCatalogo;
    }
    public Boolean getInDisponivel() {
        return inDisponivel;
    }
    public void setInDisponivel(Boolean inDisponivel) {
        this.inDisponivel = inDisponivel;
    }
    public String getNmFamilia() {
        return nmFamilia;
    }
    public void setNmFamilia(String nmFamilia) {
        this.nmFamilia = nmFamilia;
    }
    public int getCategoriaTOListSize() {
        return this.categoriaTOList != null ? this.categoriaTOList.size() : 0;
    }
    
    @Override
    public String toString() {
        return String
                .format("FamiliaTO [idFamilia=%s, cdFamilia=%s, nmFamilia=%s, dsFamilia=%s, inCriacaoCatalogo=%s, inDisponivel=%s, categoriaTOList=%s]",
                        idFamilia, cdFamilia, nmFamilia, dsFamilia,
                        inCriacaoCatalogo, inDisponivel, categoriaTOList);
    }
}
