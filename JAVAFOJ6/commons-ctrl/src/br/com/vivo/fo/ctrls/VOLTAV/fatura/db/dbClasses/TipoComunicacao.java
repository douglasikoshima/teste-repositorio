package br.com.vivo.fo.ctrls.VOLTAV.fatura.db.dbClasses;

import java.io.Serializable;
import java.sql.Date;

public class TipoComunicacao implements Serializable {

    private static final long serialVersionUID = 148787654540L;
    private long idTipoComunicacao;
    private String sgTipoComunicacao;
    private String dsTipoComunicacao;
    private long idUsuarioAlteracao;
    private Date dtUltimaAlteracao;
    private int idFormaRetorno;
    private String sgClassificacao;

    public TipoComunicacao() {
    }

    public long getIdTipoComunicacao() {
        return idTipoComunicacao;
    }

    public void setIdTipoComunicacao(long arg) {
        this.idTipoComunicacao = arg;
    }

    public String getSgTipoComunicacao() {
        return sgTipoComunicacao;
    }

    public void setSgTipoComunicacao(String arg) {
        this.sgTipoComunicacao = arg;
    }

    public String getDsTipoComunicacao() {
        return dsTipoComunicacao;
    }

    public void setDsTipoComunicacao(String arg) {
        this.dsTipoComunicacao = arg;
    }

    public long getIdUsuarioAlteracao() {
        return idUsuarioAlteracao;
    }

    public void setIdUsuarioAlteracao(long arg) {
        this.idUsuarioAlteracao = arg;
    }

    public Date getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public void setDtUltimaAlteracao(Date arg) {
        this.dtUltimaAlteracao = arg;
    }

    public int getIdFormaRetorno() {
        return idFormaRetorno;
    }

    public void setIdFormaRetorno(int arg) {
        this.idFormaRetorno = arg;
    }

    public String getSgClassificacao() {
        return sgClassificacao;
    }

    public void setSgClassificacao(String arg) {
        this.sgClassificacao = arg;
    }
}
