package br.com.indrasistemas.vivoservices.atendimento.protocolo.webservice.to;

public class ProtocoloParamWSTO {

    private Integer tpAbertura;
    private Long    idPessoa;
    private Long    nrLinha;
    private String    cdConta;
    private Long    idSistema;
    private Long    nrLinhaSMS;

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

}
