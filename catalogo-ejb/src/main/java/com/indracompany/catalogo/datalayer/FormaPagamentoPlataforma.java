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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "FORMAPAGAMENTOPLATAFORMA_SQ", sequenceName = "CATALOGOPRS_OW.FORMAPAGAMENTOPLATAFORMA_SQ", allocationSize = 1)
@Table(name="FORMAPAGAMENTOPLATAFORMA", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "FormaPagamentoPlataforma.searchByFormaPagamentoAndPlataforma", query = "select fpp from FormaPagamentoPlataforma fpp where fpp.formaPagamento.idFormaPagamento = :idFormaPagamento and fpp.plataforma.idPlataforma = :idPlataforma"),
    @NamedQuery(name = "FormaPagamentoPlataforma.searchByFormaPagamento", query = "select fpp from FormaPagamentoPlataforma fpp where fpp.formaPagamento.idFormaPagamento = :idFormaPagamento")
})
public class FormaPagamentoPlataforma implements Serializable {

    private static final long serialVersionUID = 1177667294307053104L;
    
    public FormaPagamentoPlataforma() {}
    
    public FormaPagamentoPlataforma(Integer idFormaPagamento, Integer idPlataforma) {
        this.formaPagamento = new FormaPagamento(idFormaPagamento);
        this.plataforma = new Plataforma(idPlataforma);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FORMAPAGAMENTOPLATAFORMA_SQ")
    @Column(name = "IDFORMAPAGAMENTOPLATAFORMA")
    private Integer idFormaPagamentoPlataforma;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDPLATAFORMA", nullable=false)
    private Plataforma plataforma;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="IDFORMAPAGAMENTO", nullable=false)    
    private FormaPagamento formaPagamento;
    
    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }
    
    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }
    
    public Integer getIdFormaPagamentoPlataforma() {
        return idFormaPagamentoPlataforma;
    }
    
    public void setIdFormaPagamentoPlataforma(Integer idFormaPagamentoPlataforma) {
        this.idFormaPagamentoPlataforma = idFormaPagamentoPlataforma;
    }
    
    public Plataforma getPlataforma() {
        return plataforma;
    }
    
    public void setPlataforma(Plataforma plataforma) {
        this.plataforma = plataforma;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((formaPagamento == null) ? 0 : formaPagamento.getIdFormaPagamento() == null ? 0 : formaPagamento.getIdFormaPagamento().hashCode());
        result = PRIME * result + ((plataforma == null) ? 0 : plataforma.getIdPlataforma() == null ? 0 : plataforma.getIdPlataforma().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        final FormaPagamentoPlataforma other = (FormaPagamentoPlataforma) obj;
        if (formaPagamento == null) {
            if (other.formaPagamento != null)
                return false;
        } else if (formaPagamento.getIdFormaPagamento() == null) {
            if (other.formaPagamento.getIdFormaPagamento() != null)
                return false;            
        } else if (!formaPagamento.getIdFormaPagamento().equals(other.formaPagamento.getIdFormaPagamento()))
            return false;
        if (plataforma == null) {
            if (other.plataforma != null)
                return false;
        } else if (plataforma.getIdPlataforma() == null) {
            if (other.plataforma.getIdPlataforma() != null) 
                return false;
        } else if (!plataforma.getIdPlataforma().equals(other.plataforma.getIdPlataforma()))
            return false;
        return true;
    }
    
    
}
