package com.indracompany.catalogo.to;

import java.io.Serializable;

public class GrupoComercialRelatorioFixaTO implements Serializable {

    private static final long serialVersionUID = 4830757184573073789L;

    private String cdGrupoComercial;
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
}
