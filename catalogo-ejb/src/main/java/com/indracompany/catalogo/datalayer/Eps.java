package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "EPS_SQ", sequenceName = "CATALOGOPRS_OW.EPS_SQ", allocationSize = 1)
@NamedQueries({
    @NamedQuery(name = "Eps.findAll", query = "select e from Eps e order by e.nmEps"),
    @NamedQuery(name = "Eps.findByCodigo", query = "select e from Eps e where e.idEps = :idEps")
})
@Table(name = "EPS", schema = "CATALOGOPRS_OW")
public class Eps implements Serializable {

    private static final long serialVersionUID = -247188224854567727L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EPS_SQ")   
    @Column(name = "IDEPS")
    private Integer idEps;
    
    @Column(name = "NMEPS")
    private String nmEps;
    
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name="IDGRUPOEPS")
    private GrupoEps grupoEps;
    
    @OneToMany(mappedBy="eps")
    private List<CanalVenda> canalVendaList;
    
    public GrupoEps getGrupoEps() {
        return grupoEps;
    }

    public void setGrupoEps(GrupoEps grupoEps) {
        this.grupoEps = grupoEps;
    }

    public Integer getIdEps() {
        return idEps;
    }

    public void setIdEps(Integer idEps) {
        this.idEps = idEps;
    }

    public String getNmEps() {
        return nmEps;
    }

    public void setNmEps(String nmEps) {
        this.nmEps = nmEps;
    }

    public List<CanalVenda> getCanalVendaList() {
        return canalVendaList;
    }

    public void setCanalVendaList(List<CanalVenda> canalVendaList) {
        this.canalVendaList = canalVendaList;
    }
}
