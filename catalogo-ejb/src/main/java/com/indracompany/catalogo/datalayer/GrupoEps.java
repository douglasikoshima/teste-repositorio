package com.indracompany.catalogo.datalayer;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@SequenceGenerator(name = "GRUPOEPS_SQ", sequenceName = "CATALOGOPRS_OW.GRUPOEPS_SQ", allocationSize = 1)
@Table(name = "GRUPOEPS", schema = "CATALOGOPRS_OW")
public class GrupoEps implements Serializable {

    private static final long serialVersionUID = -1829908423490091494L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "GRUPOEPS_SQ")   
    @Column(name = "IDGRUPOEPS")
    private Integer idGrupoEps;
    
    @Column(name = "NMGRUPOEPS")
    private String nmGrupoEps;
    
    @OneToMany(mappedBy = "grupoEps")
    private List<Eps> epsList;
    
    public GrupoEps() {
		super();
	}

	public GrupoEps(String nmGrupoEps) {
		super();
		this.nmGrupoEps = nmGrupoEps;
	}

	public Integer getIdGrupoEps() {
        return idGrupoEps;
    }
    
    public void setIdGrupoEps(Integer idGrupoEps) {
        this.idGrupoEps = idGrupoEps;
    }
    
    public String getNmGrupoEps() {
        return nmGrupoEps;
    }
    
    public void setNmGrupoEps(String nmGrupoEps) {
        this.nmGrupoEps = nmGrupoEps;
    }

    public List<Eps> getEpsList() {
        return epsList;
    }

    public void setEpsList(List<Eps> epsList) {
        this.epsList = epsList;
    }
}
