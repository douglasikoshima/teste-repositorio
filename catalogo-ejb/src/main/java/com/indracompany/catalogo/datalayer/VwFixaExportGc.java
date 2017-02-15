package com.indracompany.catalogo.datalayer;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="VW_FIXA_EXPORT_GC", schema = "CATALOGOPRS_OW")
public class VwFixaExportGc implements Serializable {

    private static final long serialVersionUID = -1655374327756920367L;
    
    @Id
    @Column(name = "IDCANALVENDA")
    private Long idCanalVenda;
    
    @Column(name = "CDGRUPOCOMERCIAL")
    private String cdGrupoComercial;
    
    @Column(name = "DSGRUPOCOMERCIAL")
    private String dsGrupoComercial;

    public String getCdGrupoComercial() {
        return cdGrupoComercial;
    }

    public void setCdGrupoComercial(String cdGrupoComercial) {
        this.cdGrupoComercial = cdGrupoComercial;
    }

    public String getDsGrupoComercial() {
        return dsGrupoComercial;
    }

    public void setDsGrupoComercial(String dsGrupoComercial) {
        this.dsGrupoComercial = dsGrupoComercial;
    }

    public Long getIdCanalVenda() {
        return idCanalVenda;
    }

    public void setIdCanalVenda(Long idCanalVenda) {
        this.idCanalVenda = idCanalVenda;
    }

}
