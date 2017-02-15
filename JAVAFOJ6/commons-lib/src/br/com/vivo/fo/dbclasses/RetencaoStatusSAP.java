package br.com.vivo.fo.dbclasses;

import java.io.Serializable;
import java.sql.Date;

public class RetencaoStatusSAP implements Serializable {

    static final long serialVersionUID = 200879132204L;

    private long idStatusSAP;
    private long nrPedido;
    private String nmLoginUsuario;
    private Date dtUltimaAlteracao;
    private String dsObservacao;
    private String xml;

    public long getIdStatusSAP() {
        return this.idStatusSAP;
    }

    public void setIdStatusSAP(long arg) {
        this.idStatusSAP = arg;
    }

    public long getNrPedido() {
        return this.nrPedido;
    }

    public void setNrPedido(long arg) {
        this.nrPedido = arg;
    }

    public String getNmLoginUsuario() {
        return this.nmLoginUsuario;
    }

    public void setNmLoginUsuario(String arg) {
        this.nmLoginUsuario = arg;
    }

    public Date getDtUltimaAlteracao() {
        return this.dtUltimaAlteracao;
    }

    public void setDtUltimaAlteracao(Date arg) {
        this.dtUltimaAlteracao = arg;
    }

    public String getDsObservacao() {
        return this.dsObservacao;
    }

    public void setDsObservacao(String arg) {
        this.dsObservacao = arg;
    }

    public String getXml() {
        return this.xml;
    }

    public void setXml(String arg) {
        this.xml = arg;
    }
}