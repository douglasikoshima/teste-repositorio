package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "PROMOCAO_SQ", sequenceName = "CATALOGOPRS_OW.PROMOCAO_SQ", allocationSize = 1)
@Table(name="PROMOCAO", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "Promocao.findByCodigo", query = "select p from Promocao p where p.cdPromocao like :cdPromocao"),
    @NamedQuery(name = "Promocao.findByName", query = "select p from Promocao p where lower(p.nmPromocao) like lower(:nmPromocao)"),
    @NamedQuery(name = "Promocao.findByCodigoExceptId", query = "select p from Promocao p where p.cdPromocao like :cdPromocao and p.idPromocao <> :idPromocao"),
    @NamedQuery(name = "Promocao.findByNameExceptId", query = "select p from Promocao p where lower(p.nmPromocao) like lower(:nmPromocao) and p.idPromocao <> :idPromocao")    
})
public class Promocao implements Serializable {

    public enum InStatusConfiguracao {
        Configurado("C"), Desconfigurado("D");
        private String value;
        private InStatusConfiguracao(String value) {
            this.value = value;
        }
    }
    
    private static final long serialVersionUID = -4127312541010407539L;
    public Promocao(){}
    public Promocao(Integer idPromocao) {
        this.idPromocao = idPromocao;
    }
    
    public Promocao(InStatusConfiguracao inStatusConfiguracao) {
        this.inStatusConfiguracao = inStatusConfiguracao.value;
    }    
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROMOCAO_SQ")
    @Column(name = "IDPROMOCAO", nullable=false)
    private Integer idPromocao;

    @Column(name = "NMPROMOCAO", nullable=false)
    private String nmPromocao;
    
    @Column(name = "DTINICIO", nullable=false)
    private Date dtInicio;
        
    @Column(name = "DTFIM", nullable=true)
    private Date dtFim;
    
    @Column(name = "CDPROMOCAO", nullable=false)
    private String cdPromocao;
    
    @Column(name = "INSTATUSCONFIGURACAO", nullable=true)
    private String inStatusConfiguracao;
    
    @OneToOne(cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private PoliticaDispPromocao politicaDispPromocao;
    
    @OneToMany(mappedBy="promocao", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ComposicaoPromocao> composicaoPromocaoList;

    @OneToMany(mappedBy="promocao", fetch=FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<ValorPoliticaPromocao> valorPoliticaPromocaoList;
    
    public String getCdPromocao() {
        return cdPromocao;
    }

    public void setCdPromocao(String cdPromocao) {
        this.cdPromocao = cdPromocao;
    }

    public Date getDtFim() {
        return dtFim;
    }

    public void setDtFim(Date dtFim) {
        this.dtFim = dtFim;
    }

    public Date getDtInicio() {
        return dtInicio;
    }

    public void setDtInicio(Date dtInicio) {
        this.dtInicio = dtInicio;
    }

    public Integer getIdPromocao() {
        return idPromocao;
    }

    public void setIdPromocao(Integer idPromocao) {
        this.idPromocao = idPromocao;
    }

    public String getNmPromocao() {
        return nmPromocao;
    }

    public void setNmPromocao(String nmPromocao) {
        this.nmPromocao = nmPromocao;
    }

    public PoliticaDispPromocao getPoliticaDispPromocao() {
        return politicaDispPromocao;
    }

    public void setPoliticaDispPromocao(PoliticaDispPromocao politicaDispPromocao) {
        this.politicaDispPromocao = politicaDispPromocao;
    }

    public List<ComposicaoPromocao> getComposicaoPromocaoList() {
        return composicaoPromocaoList;
    }

    public void setComposicaoPromocaoList(
            List<ComposicaoPromocao> composicaoPromocaoList) {
        this.composicaoPromocaoList = composicaoPromocaoList;
    }
    
    public String getInStatusConfiguracao() {
        return inStatusConfiguracao;
    }
    
    public void setInStatusConfiguracao(String inStatusConfiguracao) {
        this.inStatusConfiguracao = inStatusConfiguracao;
    }
    
    public void setInStatusConfiguracao(InStatusConfiguracao inStatusConfiguracao) {
        this.inStatusConfiguracao = inStatusConfiguracao.value;
    }
	public List<ValorPoliticaPromocao> getValorPoliticaPromocaoList() {
		return valorPoliticaPromocaoList;
	}
	public void setValorPoliticaPromocaoList(
			List<ValorPoliticaPromocao> valorPoliticaPromocaoList) {
		this.valorPoliticaPromocaoList = valorPoliticaPromocaoList;
	}
}
