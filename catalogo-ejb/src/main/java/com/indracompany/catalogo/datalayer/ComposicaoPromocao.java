package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

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

@Entity
@SequenceGenerator(name = "COMPOSICAOPROMOCAO_SQ", sequenceName = "CATALOGOPRS_OW.COMPOSICAOPROMOCAO_SQ", allocationSize = 1)
@Table(name="COMPOSICAOPROMOCAO", schema = "CATALOGOPRS_OW")
public class ComposicaoPromocao implements Serializable {

    private static final long serialVersionUID = 4281226875897642853L;
    
    public ComposicaoPromocao(){}
    public ComposicaoPromocao(Integer idServico, Integer idPromocao, Long idSolicitacaoComercial, Integer pzValidadeDesconto) {
        this.setServico(new Servico(idServico));
        this.setPromocao(new Promocao(idPromocao));
        this.setSolicitacaoComercial(new SolicitacaoComercial(idSolicitacaoComercial));
        this.setPzValidadeDesconto(pzValidadeDesconto);        
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMPOSICAOPROMOCAO_SQ")
    @Column(name = "IDCOMPOSICAOPROMOCAO")
    private Integer idComposicaoPromocao;
    
    @Column(name = "PZVALIDADEDESCONTO")
    private Integer pzValidadeDesconto;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDSERVICO")
    private Servico servico;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDPROMOCAO")    
    private Promocao promocao;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDSOLICITACAOCOMERCIAL")  
    private SolicitacaoComercial solicitacaoComercial;

    public Integer getIdComposicaoPromocao() {
        return idComposicaoPromocao;
    }

    public void setIdComposicaoPromocao(Integer idComposicaoPromocao) {
        this.idComposicaoPromocao = idComposicaoPromocao;
    }

    public Promocao getPromocao() {
        return promocao;
    }

    public void setPromocao(Promocao promocao) {
        this.promocao = promocao;
    }

    public Integer getPzValidadeDesconto() {
        return pzValidadeDesconto;
    }

    public void setPzValidadeDesconto(Integer pzValidadeDesconto) {
        this.pzValidadeDesconto = pzValidadeDesconto;
    }

    public Servico getServico() {
        return servico;
    }

    public void setServico(Servico servico) {
        this.servico = servico;
    }

    public SolicitacaoComercial getSolicitacaoComercial() {
        return solicitacaoComercial;
    }

    public void setSolicitacaoComercial(SolicitacaoComercial solicitacaoComercial) {
        this.solicitacaoComercial = solicitacaoComercial;
    }

}
