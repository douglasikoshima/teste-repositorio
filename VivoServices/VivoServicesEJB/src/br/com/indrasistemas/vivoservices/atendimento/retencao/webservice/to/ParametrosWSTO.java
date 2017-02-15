package br.com.indrasistemas.vivoservices.atendimento.retencao.webservice.to;

public class ParametrosWSTO {

    private String nrOperacao;
    private String nrTelefone;
    private String nrTipo;
    private String cdOferta;

    public String getNrOperacao() {
        return nrOperacao;
    }

    public void setNrOperacao(String nrOperacao) {
        this.nrOperacao = nrOperacao;
    }

    public String getNrTelefone() {
        return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
        this.nrTelefone = nrTelefone;
    }

    public String getNrTipo() {
        return nrTipo;
    }

    public void setNrTipo(String nrTipo) {
        this.nrTipo = nrTipo;
    }

    public String getCdOferta() {
        return cdOferta;
    }

    public void setCdOferta(String cdOferta) {
        this.cdOferta = cdOferta;
    }

}
