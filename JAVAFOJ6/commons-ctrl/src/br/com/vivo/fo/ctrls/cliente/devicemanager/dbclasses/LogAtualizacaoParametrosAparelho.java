package br.com.vivo.fo.ctrls.cliente.devicemanager.dbclasses;

import java.io.Serializable;
import java.util.Date;

public class LogAtualizacaoParametrosAparelho implements Serializable {

    static final long serialVersionUID = 12554001377781L;

    public LogAtualizacaoParametrosAparelho() {
    }
    private int idLogDeviceManager;
    private long nrLinha;
    private int idUsuarioAlteracao;
    private Date dtUltimaAlteracao;

    public int getIdLogDeviceManager() {
        return this.idLogDeviceManager;
    }

    public void setIdLogDeviceManager(int idLogDeviceManager) {
        this.idLogDeviceManager = idLogDeviceManager;
    }

    public long getNrLinha() {
        return this.nrLinha;
    }

    public void setNrLinha(long nrLinha) {
        this.nrLinha = nrLinha;
    }

    public int getIdUsuarioAlteracao() {
        return this.idUsuarioAlteracao;
    }

    public void setIdUsuarioAlteracao(int idUsuarioAlteracao) {
        this.idUsuarioAlteracao = idUsuarioAlteracao;
    }

    public Date getDtUltimaAlteracao() {
        return this.dtUltimaAlteracao;
    }

    public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
    }
}
