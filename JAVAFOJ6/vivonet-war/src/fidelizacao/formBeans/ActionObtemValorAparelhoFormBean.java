package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;

public class ActionObtemValorAparelhoFormBean extends ActionForm {

    private static final long serialVersionUID = -676884715764205517L;

    private String[]          parcelas         = new String[0];
    private float             valorParcela;
    private String            parcelaSelecionada;
    private float             valorComDesconto;

    public void setValorComDesconto(float valorComDesconto) {
        this.valorComDesconto = valorComDesconto;
    }

    public float getValorComDesconto() {
        return this.valorComDesconto;
    }

    public void setParcelaSelecionada(String parcelaSelecionada) {
        this.parcelaSelecionada = parcelaSelecionada;
    }

    public String getParcelaSelecionada() {
        return this.parcelaSelecionada;
    }

    public void setValorParcela(float valorParcela) {
        this.valorParcela = valorParcela;
    }

    public float getValorParcela() {
        return this.valorParcela;
    }

    public void setParcelas(String[] parcelas) {
        this.parcelas = parcelas;
    }

    public String[] getParcelas() {
        return this.parcelas;
    }

}
