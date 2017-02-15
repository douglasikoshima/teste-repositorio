package br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.webservice.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class ResultadoDesbloqueioWSTO extends RespostaWSTO {

    private static final long serialVersionUID = 7803341372229514866L;

    private String            nrSimLock;
    private String            statusConsulta;
    private String            errorCode;
    private String            errorDescription;

    public ResultadoDesbloqueioWSTO() {
    }

    public String getNrSimLock() {
        return nrSimLock;
    }

    public void setNrSimLock(String nrSimLock) {
        this.nrSimLock = nrSimLock;
    }

    public String getStatusConsulta() {
        return statusConsulta;
    }

    public void setStatusConsulta(String statusConsulta) {
        this.statusConsulta = statusConsulta;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDescription() {
        return errorDescription;
    }

    public void setErrorDescription(String errorDescription) {
        this.errorDescription = errorDescription;
    }

}