package br.com.vivo.fo.ctrls.cliente.relatorios.consultaSIMLock.dbClasses;

import br.com.vivo.fo.constantes.ConstantesCRM;

public class CamposConsultaSimLock {

    private String dtultimaalteracao = ConstantesCRM.SVAZIO;
    private String nrimei = ConstantesCRM.SVAZIO;
    private String nmloginusuario = ConstantesCRM.SVAZIO;
    private String nrip = ConstantesCRM.SVAZIO;
    private String estadoconsulta = ConstantesCRM.SVAZIO;
    private String nrlinha = ConstantesCRM.SVAZIO;
    private String nmtiporelacionamento = ConstantesCRM.SVAZIO;
    private String sgtipodocumento = ConstantesCRM.SVAZIO;
    private String nrdocumento = ConstantesCRM.SVAZIO;
    private String nmpessoa = ConstantesCRM.SVAZIO;

    public String getDtultimaalteracao() {
        return dtultimaalteracao;
    }

    public void setDtultimaalteracao(String dtultimaalteracao) {
        this.dtultimaalteracao = dtultimaalteracao;
    }

    public String getNrimei() {
        return nrimei;
    }

    public void setNrimei(String nrimei) {
        this.nrimei = nrimei;
    }

    public String getNmloginusuario() {
        return nmloginusuario;
    }

    public void setNmloginusuario(String nmloginusuario) {
        this.nmloginusuario = nmloginusuario;
    }

    public String getNrip() {
        return nrip;
    }

    public void setNrip(String nrip) {
        this.nrip = nrip;
    }

    public String getEstadoconsulta() {
        return estadoconsulta;
    }

    public void setEstadoconsulta(String estadoconsulta) {
        this.estadoconsulta = estadoconsulta;
    }

    public String getNrlinha() {
        return nrlinha;
    }

    public void setNrlinha(String nrlinha) {
        this.nrlinha = nrlinha;
    }

    public String getNmtiporelacionamento() {
        return nmtiporelacionamento;
    }

    public void setNmtiporelacionamento(String nmtiporelacionamento) {
        this.nmtiporelacionamento = nmtiporelacionamento;
    }

    public String getSgtipodocumento() {
        return sgtipodocumento;
    }

    public void setSgtipodocumento(String sgtipodocumento) {
        this.sgtipodocumento = sgtipodocumento;
    }

    public String getNrdocumento() {
        return nrdocumento;
    }

    public void setNrdocumento(String nrdocumento) {
        this.nrdocumento = nrdocumento;
    }

    public String getNmpessoa() {
        return nmpessoa;
    }

    public void setNmpessoa(String nmpessoa) {
        this.nmpessoa = nmpessoa;
    }
}
