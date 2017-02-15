package br.com.indrasistemas.vivoservices.atendimento.protocolo.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class ProtocoloTO extends BaseTransferObject {

    private static final long serialVersionUID = -1347032197801232386L;

    private Integer           tpAbertura;
    private Long              idPessoa;
    private Long              nrLinha;
    private String            cdConta;
    private Long              idSistema;
    private Long              nrLinhaSMS;
    private Long              nrProtocolo;
    private String            statusConsulta   = "";
    private String            errorCode        = "";
    private String            errorDescription = "";

    public ProtocoloTO() {
        super();
    }

    public Integer getTpAbertura() {
        return tpAbertura;
    }

    public void setTpAbertura(Integer tpAbertura) {
        this.tpAbertura = tpAbertura;
    }

    public Long getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(Long idPessoa) {
        this.idPessoa = idPessoa;
    }

    public Long getNrLinha() {
        return nrLinha;
    }

    public void setNrLinha(Long nrLinha) {
        this.nrLinha = nrLinha;
    }

    public String getCdConta() {
        return cdConta;
    }

    public void setCdConta(String cdConta) {
        this.cdConta = cdConta;
    }

    public Long getIdSistema() {
        return idSistema;
    }

    public void setIdSistema(Long idSistema) {
        this.idSistema = idSistema;
    }

    public Long getNrLinhaSMS() {
        return nrLinhaSMS;
    }

    public void setNrLinhaSMS(Long nrLinhaSMS) {
        this.nrLinhaSMS = nrLinhaSMS;
    }

    public Long getNrProtocolo() {
        return nrProtocolo;
    }

    public void setNrProtocolo(Long nrProtocolo) {
        this.nrProtocolo = nrProtocolo;
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