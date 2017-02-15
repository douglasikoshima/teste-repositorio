package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;

public class CancelarAtdFormBean extends ActionForm {

    private static final long serialVersionUID = -8185630950756031003L;
    private String            idTipoEncerramento;

    public void setIdTipoEncerramento(String idTipoEncerramento) {
        this.idTipoEncerramento = idTipoEncerramento;
    }

    public String getIdTipoEncerramento() {
        return this.idTipoEncerramento;
    }
}
