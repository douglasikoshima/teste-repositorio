package br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class DesbloqueioGsmTO extends BaseTransferObject {

    public DesbloqueioGsmTO() {
    }

    private static final long serialVersionUID = -1307042197890032386L;

    private String            idPessoa;
    private String            idGrupoAbertura;
    private String            idTipoRelacionamento;
    private String            cdAreaRegistro;
    private String            nrLinha;
    private String            idTipoDocumento;
    private String            nrDocumento;
    private String            nrIP;
    private String            nrIMEI;
    private String            nrSimLock;
    private String            statusConsulta;
    private String            errorCode;
    private String            errorDescription;

    public String getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(String idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getIdGrupoAbertura() {
        return idGrupoAbertura;
    }

    public void setIdGrupoAbertura(String idGrupoAbertura) {
        this.idGrupoAbertura = idGrupoAbertura;
    }

    public String getIdTipoRelacionamento() {
        return idTipoRelacionamento;
    }

    public void setIdTipoRelacionamento(String idTipoRelacionamento) {
        this.idTipoRelacionamento = idTipoRelacionamento;
    }

    public String getCdAreaRegistro() {
        return cdAreaRegistro;
    }

    public void setCdAreaRegistro(String cdAreaRegistro) {
        this.cdAreaRegistro = cdAreaRegistro;
    }

    public String getNrLinha() {
        return nrLinha;
    }

    public void setNrLinha(String nrLinha) {
        this.nrLinha = nrLinha;
    }

    public String getIdTipoDocumento() {
        return idTipoDocumento;
    }

    public void setIdTipoDocumento(String idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }

    public String getNrDocumento() {
        return nrDocumento;
    }

    public void setNrDocumento(String nrDocumento) {
        this.nrDocumento = nrDocumento;
    }

    public String getNrIP() {
        return nrIP;
    }

    public void setNrIP(String nrIP) {
        this.nrIP = nrIP;
    }

    public String getNrIMEI() {
        return nrIMEI;
    }

    public void setNrIMEI(String nrIMEI) {
        this.nrIMEI = nrIMEI;
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
