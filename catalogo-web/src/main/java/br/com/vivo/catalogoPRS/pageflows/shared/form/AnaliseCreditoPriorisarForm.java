package br.com.vivo.catalogoPRS.pageflows.shared.form;

import org.apache.struts.validator.ValidatorActionForm;

public class AnaliseCreditoPriorisarForm  extends ValidatorActionForm  implements java.io.Serializable {
	
    private static final long serialVersionUID = 4886312793531975243L;
    
    private String idAnaliseCredito;
    private String idOfertafixaCreditoScoreSelected;
    private String idEps;
    private String select1;
    private String select2;
    
    public String getIdAnaliseCredito() {
        return idAnaliseCredito;
    }

    public void setIdAnaliseCredito(String idAnaliseCredito) {
        this.idAnaliseCredito = idAnaliseCredito;
    }

    public String getIdOfertafixaCreditoScoreSelected() {
        return idOfertafixaCreditoScoreSelected;
    }

    public void setIdOfertafixaCreditoScoreSelected(String idOfertafixaCreditoScoreSelected) {
        this.idOfertafixaCreditoScoreSelected = idOfertafixaCreditoScoreSelected;
    }

	public String getIdEps() {
		return idEps;
	}

	public void setIdEps(String idEps) {
		this.idEps = idEps;
	}

	public String getSelect1() {
		return select1;
	}

	public void setSelect1(String select1) {
		this.select1 = select1;
	}

	public String getSelect2() {
		return select2;
	}

	public void setSelect2(String select2) {
		this.select2 = select2;
	}	

}
