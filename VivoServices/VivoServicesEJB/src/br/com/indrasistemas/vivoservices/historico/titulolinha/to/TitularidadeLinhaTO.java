package br.com.indrasistemas.vivoservices.historico.titulolinha.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class TitularidadeLinhaTO extends BaseTransferObject {

    private static final long serialVersionUID = -3494180018827935907L;

    private String status;
    private Integer cdError = new Integer(0);
    private String dsError;
    private String nrTelefone;
    private String cdArea;
    private boolean linhaAtiva;

    private ListaTitularidadeLinhaTO[] lista = new ListaTitularidadeLinhaTO[0];

    public String getStatus() {
	return status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

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

    public ListaTitularidadeLinhaTO[] getLista() {
	return lista;
    }

    public void setLista(ListaTitularidadeLinhaTO[] lista) {
	this.lista = lista;
    }

	public boolean isLinhaAtiva() {
		return linhaAtiva;
	}

	public void setLinhaAtiva(boolean linhaAtiva) {
		this.linhaAtiva = linhaAtiva;
	}
}
