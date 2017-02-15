package fidelizacao.formBeans;

import org.apache.struts.action.ActionForm;

public class SuspensaoTemporariaFormBean extends ActionForm {

    private static final long serialVersionUID = 2047638754752995693L;

    private String            tipoEncerramento;
    private String            validade;
    private String            dtFim;
    private String            dtInicio;
    private boolean           inExcecao        = false;
    private String            data;

    public void setTipoEncerramento(String tipoEncerramento) {
        this.tipoEncerramento = tipoEncerramento;
    }

    public String getTipoEncerramento() {
        return this.tipoEncerramento;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return this.data;
    }

    public void setInExcecao(boolean inExcecao) {
        this.inExcecao = inExcecao;
    }

    public boolean getInExcecao() {
        return this.inExcecao;
    }

    public void setDtInicio(String dtInicio) {
        this.dtInicio = dtInicio;
    }

    public String getDtInicio() {
        return this.dtInicio;
    }

    public void setDtFim(String dtFim) {
        this.dtFim = dtFim;
    }

    public String getDtFim() {
        return this.dtFim;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getValidade() {
        return this.validade;
    }
}
