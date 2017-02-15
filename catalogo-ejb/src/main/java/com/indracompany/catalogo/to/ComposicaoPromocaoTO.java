package com.indracompany.catalogo.to;

import java.io.Serializable;

public class ComposicaoPromocaoTO implements Serializable {

    private static final long serialVersionUID = -1646321787546924760L;
    
    private Integer idComposicao;
    private Integer idServico;
    private String nomeServico;
    private String nomeDesconto;
    private Integer idSolicitacao;
    private String nomeSolicitacao;
    private Integer idPromocao;
    private Integer validadeDesconto;
    
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ComposicaoPromocaoTO)) {
            return false;
        }
        ComposicaoPromocaoTO other = (ComposicaoPromocaoTO) obj;
        if (this.idComposicao == null && other.idComposicao != null || this.idComposicao != null && other.idComposicao == null) {
            return false;
        }
        if (this.idComposicao == other.idComposicao && this.idComposicao != null && other.idComposicao != null) {
            return true;
        }
        if (this.idComposicao == null) {
            if (this.idPromocao != other.idPromocao && (this.idPromocao != null && !this.idPromocao.equals(other.idPromocao) || other.idPromocao != null && !other.idPromocao.equals(this.idPromocao))) {
                return false;
            } 
            if (this.idServico != other.idServico && (this.idServico != null && !this.idServico.equals(other.idServico) || other.idServico != null && !other.idServico.equals(this.idServico))) {
                return false;
            } 
            if (this.idSolicitacao != other.idSolicitacao && (this.idSolicitacao != null && !this.idSolicitacao.equals(other.idSolicitacao) || other.idSolicitacao != null && !other.idSolicitacao.equals(this.idSolicitacao))) {
                return false;
            } 
            return true;
        } else {
            return this.idComposicao.equals(other.idComposicao);
        }
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.idComposicao != null ? this.idComposicao.hashCode() : 0);
        hash = 29 * hash + (this.idServico != null ? this.idServico.hashCode() : 0);
        hash = 29 * hash + (this.idSolicitacao != null ? this.idSolicitacao.hashCode() : 0);
        hash = 29 * hash + (this.idPromocao != null ? this.idPromocao.hashCode() : 0);
        return hash;
    }

    public Integer getIdComposicao() {
        return idComposicao;
    }

    public void setIdComposicao(Integer idComposicao) {
        this.idComposicao = idComposicao;
    }

    public Integer getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(Integer idPromocao) {
        this.idPromocao = idPromocao;
    }

    public Integer getIdServico() {
        return idServico;
    }

    public void setIdServico(Integer idServico) {
        this.idServico = idServico;
    }

    public Integer getIdSolicitacao() {
        return idSolicitacao;
    }

    public void setIdSolicitacao(Integer idSolicitacao) {
        this.idSolicitacao = idSolicitacao;
    }

    public String getNomeDesconto() {
        return nomeDesconto;
    }

    public void setNomeDesconto(String nomeDesconto) {
        this.nomeDesconto = nomeDesconto;
    }

    public String getNomeServico() {
        return nomeServico;
    }

    public void setNomeServico(String nomeServico) {
        this.nomeServico = nomeServico;
    }

    public String getNomeSolicitacao() {
        return nomeSolicitacao;
    }

    public void setNomeSolicitacao(String nomeSolicitacao) {
        this.nomeSolicitacao = nomeSolicitacao;
    }

    public Integer getValidadeDesconto() {
        return validadeDesconto;
    }

    public void setValidadeDesconto(Integer validadeDesconto) {
        this.validadeDesconto = validadeDesconto;
    }
}
