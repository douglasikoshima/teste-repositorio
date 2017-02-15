package br.com.indrasistemas.vivoservices.historico.titulolinha.webservice.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class TitularidadeLinhaWSTO extends RespostaWSTO {

    private static final long serialVersionUID = -3494180018827935907L;

    private Integer cdError = new Integer(0);
    private String dsError = "";
    private String nrTelefone = "";
    private String cdArea = "";

    private ListaTitularidadeLinhaWSTO[] lista;

    public Integer getCdError() {
	return cdError;
    }

    public void setCdError(Integer cdError) {
	this.cdError = cdError;
    }

    public String getDsError() {
	return dsError;
    }

    public void setDsError(String dsError) {
	this.dsError = dsError;
    }

    public String getNrTelefone() {
	return nrTelefone;
    }

    public void setNrTelefone(String nrTelefone) {
	this.nrTelefone = nrTelefone;
    }

    public String getCdArea() {
	return cdArea;
    }

    public void setCdArea(String cdArea) {
	this.cdArea = cdArea;
    }

    public ListaTitularidadeLinhaWSTO[] getLista() {
	return lista;
    }

    public void setLista(ListaTitularidadeLinhaWSTO[] lista) {
	this.lista = lista;
    }

}
