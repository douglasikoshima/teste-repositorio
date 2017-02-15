package br.com.vivo.fo.ctrls.cliente.devicemanager;

import javax.ejb.Local;

@Local
public interface DeviceManager {

    public void incluiLogDeviceManager(java.lang.String idUsuario, java.lang.String nrLinha) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.cliente.devicemanager.dbclasses.LogAtualizacaoParametrosAparelho getDataAtualizacaoParametrosApareho(java.lang.String user, java.lang.String nrLinha);
}
