package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

import com.indracompany.catalogo.to.GrupoComercialRelatorioFixaTO;

public class GrupoComercialRelatorioFixaForm extends ValidatorActionForm  implements java.io.Serializable{
	
    private static final long serialVersionUID = -850638173362668867L;
    
    private String cdGrupoComercial;
    private String dsGrupoComercial;
    
    public GrupoComercialRelatorioFixaTO buildTO() {
        GrupoComercialRelatorioFixaTO to = new GrupoComercialRelatorioFixaTO(); 
        to.setCdGrupoComercial(this.cdGrupoComercial);
        to.setDsGrupoComercial(this.dsGrupoComercial);
        return to;
    }

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
