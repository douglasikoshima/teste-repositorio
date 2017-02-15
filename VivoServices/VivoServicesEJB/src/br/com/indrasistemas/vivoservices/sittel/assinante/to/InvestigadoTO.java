package br.com.indrasistemas.vivoservices.sittel.assinante.to;

import br.com.indrasistemas.framework.service.BaseTransferObject;

public class InvestigadoTO extends BaseTransferObject {

    private static final long serialVersionUID = -2128919053042475264L;

    private String            cpf;
    private String            cnpj;
    private String            documentoAssinante;
    private String            nomeAssinante;
    private String            numeroTerminalAssinante;
    private String            imei;
    private String            cgiErb;
    private String            enderecoIp;
    private String            numeroPortaIp;
    private String            inicioPeriodoQuebra;
    private String            fimPeriodoQuebra;

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDocumentoAssinante() {
        return documentoAssinante;
    }

    public void setDocumentoAssinante(String documentoAssinante) {
        this.documentoAssinante = documentoAssinante;
    }

    public String getNomeAssinante() {
        return nomeAssinante;
    }

    public void setNomeAssinante(String nomeAssinante) {
        this.nomeAssinante = nomeAssinante;
    }

    public String getNumeroTerminalAssinante() {
        return numeroTerminalAssinante;
    }

    public void setNumeroTerminalAssinante(String numeroTerminalAssinante) {
        this.numeroTerminalAssinante = numeroTerminalAssinante;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getCgiErb() {
        return cgiErb;
    }

    public void setCgiErb(String cgiErb) {
        this.cgiErb = cgiErb;
    }

    public String getEnderecoIp() {
        return enderecoIp;
    }

    public void setEnderecoIp(String enderecoIp) {
        this.enderecoIp = enderecoIp;
    }

    public String getNumeroPortaIp() {
        return numeroPortaIp;
    }

    public void setNumeroPortaIp(String numeroPortaIp) {
        this.numeroPortaIp = numeroPortaIp;
    }

    public String getInicioPeriodoQuebra() {
        return inicioPeriodoQuebra;
    }

    public void setInicioPeriodoQuebra(String inicioPeriodoQuebra) {
        this.inicioPeriodoQuebra = inicioPeriodoQuebra;
    }

    public String getFimPeriodoQuebra() {
        return fimPeriodoQuebra;
    }

    public void setFimPeriodoQuebra(String fimPeriodoQuebra) {
        this.fimPeriodoQuebra = fimPeriodoQuebra;
    }

    @Override
    public String toString() {
        return "TipoInvestigadoTO [cpf=" + cpf + ", cnpj=" + cnpj + "]";
    }

}
