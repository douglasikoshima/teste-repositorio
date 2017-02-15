package br.com.indrasistemas.vivoservices.atendimento.gestor.to;

import br.com.indrasistemas.vivoservices.webservice.to.RespostaWSTO;

public class AlertaSaidaTO extends RespostaWSTO {

    private static final long serialVersionUID = -3771115582039348337L;

    private String            codError;
    private String            msgError;
    private String            sgTipoCarteira;
    private String            sgSegmentacao;
    private String            nomeGestor;
    private String            nrLinha;
    private String            emailGestor;
    private String            nomeGerente;
    private String            nrLinhaGr;
    private String            emailGerente;
    private String            nmFantasia;
    private String            razaoSocial;
    private String            operadora;

    public AlertaSaidaTO() {
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }

    public String getCodError() {
        return codError;
    }

    public void setCodError(String codError) {
        this.codError = codError;
    }

    public String getSgTipoCarteira() {
        return sgTipoCarteira;
    }

    public void setSgTipoCarteira(String sgTipoCarteira) {
        this.sgTipoCarteira = sgTipoCarteira;
    }

    public String getSgSegmentacao() {
        return sgSegmentacao;
    }

    public void setSgSegmentacao(String sgSegmentacao) {
        this.sgSegmentacao = sgSegmentacao;
    }

    public String getNomeGestor() {
        return nomeGestor;
    }

    public void setNomeGestor(String nomeGestor) {
        this.nomeGestor = nomeGestor;
    }

    public String getNrLinha() {
        return nrLinha;
    }

    public void setNrLinha(String nrLinha) {
        this.nrLinha = nrLinha;
    }

    public String getEmailGestor() {
        return emailGestor;
    }

    public void setEmailGestor(String emailGestor) {
        this.emailGestor = emailGestor;
    }

    public String getNomeGerente() {
        return nomeGerente;
    }

    public void setNomeGerente(String nomeGerente) {
        this.nomeGerente = nomeGerente;
    }

    public String getNrLinhaGr() {
        return nrLinhaGr;
    }

    public void setNrLinhaGr(String nrLinhaGr) {
        this.nrLinhaGr = nrLinhaGr;
    }

    public String getEmailGerente() {
        return emailGerente;
    }

    public void setEmailGerente(String emailGerente) {
        this.emailGerente = emailGerente;
    }

    public String getNmFantasia() {
        return nmFantasia;
    }

    public void setNmFantasia(String nmFantasia) {
        this.nmFantasia = nmFantasia;
    }

    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getOperadora() {
        return operadora;
    }

    public void setOperadora(String operadora) {
        this.operadora = operadora;
    }

}