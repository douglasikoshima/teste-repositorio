package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="POLITICADISPPROMOCAO", schema = "CATALOGOPRS_OW")
public class PoliticaDispPromocao implements Serializable {

    private static final long serialVersionUID = -664621657831988771L;
    
    public PoliticaDispPromocao(){}
    
    public PoliticaDispPromocao(Integer idPromocao, String inCanalVenda) {
        this.idPromocao = idPromocao;
        this.inCanalVenda = inCanalVenda;
    }
    
    @Id
    @Column(name = "IDPROMOCAO")
    private Integer idPromocao;
    
    @OneToOne(mappedBy = "politicaDispPromocao")
    @JoinColumn(name = "idpromocao")  
    private Promocao promocao;
    
    @Column(name = "INCANALVENDA")
    private String inCanalVenda;

    public String getInCanalVenda() {
        return inCanalVenda;
    }

    public void setInCanalVenda(String inCanalVenda) {
        this.inCanalVenda = inCanalVenda;
    }

    public Integer getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(Integer idPromocao) {
        this.idPromocao = idPromocao;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }
}
