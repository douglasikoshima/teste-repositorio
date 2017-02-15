package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "VALORPOLITICAPROMOCAO_SQ", sequenceName = "CATALOGOPRS_OW.VALORPOLITICAPROMOCAO_SQ", allocationSize = 1)
@Table(name = "VALORPOLITICAPROMOCAO", schema = "CATALOGOPRS_OW")
public class ValorPoliticaPromocao implements Serializable {

    private static final long serialVersionUID = 6307720415982672690L;
    
    public ValorPoliticaPromocao(){}
    public ValorPoliticaPromocao(Integer idPromocao, Long idCanalVenda) {
        this.promocao = new Promocao(idPromocao);
        this.canalVenda = new CanalVenda(idCanalVenda);
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "VALORPOLITICAPROMOCAO_SQ")
    @Column(name = "IDVALORPOLITICAPROMOCAO")
    private Integer idValorPoliticaPromocao;
    
    @ManyToOne
    @JoinColumn(name="IDPROMOCAO")
    private Promocao promocao;
    
    @ManyToOne
    @JoinColumn(name="IDCANALVENDA")
    private CanalVenda canalVenda;

    public CanalVenda getCanalVenda() {
        return canalVenda;
    }

    public void setCanalVenda(CanalVenda canalVenda) {
        this.canalVenda = canalVenda;
    }

    public Integer getIdValorPoliticaPromocao() {
        return idValorPoliticaPromocao;
    }

    public void setIdValorPoliticaPromocao(Integer idValorPoliticaPromocao) {
        this.idValorPoliticaPromocao = idValorPoliticaPromocao;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    
}
