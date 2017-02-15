package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "CIDADE", schema = "CATALOGOPRS_OW")
@NamedQueries({
    @NamedQuery(name = "Cidade.findByIdAreaRegistro", query = "select distinct c from AreaRegistro ar inner join ar.ciadadeList c where ar.idarearegistro = :idAreaRegistro order by c.nmCidade")
})
public class Cidade implements Serializable {

    private static final long serialVersionUID = 8521201043842032776L;
    
    @Id
    @Column(name = "IDCIDADE")
    private Integer idCidade;
    
    @Column(name = "NMCIDADE")
    private String nmCidade;
    
    @Column(name = "NMUSUARIOALTERACAO")
    private String nmUsuarioAlteracao;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "DTULTIMAALTERACAO")
    private Date dtUltimaAlteracao;

    @ManyToOne
    @JoinColumn(name = "IDAREAREGISTRO")    
    private AreaRegistro areaRegistro;
    
    @OneToMany(mappedBy="cidade")
    private List<Localidade> localidadeList;      

    public AreaRegistro getAreaRegistro() {
        return areaRegistro;
    }

    public Date getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public Integer getIdCidade() {
        return idCidade;
    }

    public String getNmCidade() {
        return nmCidade;
    }

    public String getNmUsuarioAlteracao() {
        return nmUsuarioAlteracao;
    }

    public void setAreaRegistro(AreaRegistro areaRegistro) {
        this.areaRegistro = areaRegistro;
    }

    public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }

    public void setIdCidade(Integer idCidade) {
        this.idCidade = idCidade;
    }

    public void setNmCidade(String nmCidade) {
        this.nmCidade = nmCidade;
    }

    public void setNmUsuarioAlteracao(String nmUsuarioAlteracao) {
        this.nmUsuarioAlteracao = nmUsuarioAlteracao;
    }

    public List<Localidade> getLocalidadeList() {
        return localidadeList;
    }

    public void setLocalidadeList(List<Localidade> localidadeList) {
        this.localidadeList = localidadeList;
    }
}
