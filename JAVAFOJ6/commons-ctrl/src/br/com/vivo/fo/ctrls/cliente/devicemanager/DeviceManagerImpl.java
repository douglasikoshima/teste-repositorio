package br.com.vivo.fo.ctrls.cliente.devicemanager;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.devicemanager.dbclasses.LogAtualizacaoParametrosAparelho;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;

@Stateless(name = "DeviceManager", mappedName = "DeviceManager")
@Local(DeviceManager.class)
@Session(ejbName = "DeviceManager", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class DeviceManagerImpl implements DeviceManager {

    static final long       serialVersionUID = 19832510L;

    private static Logger   log              = new Logger("admsistemas");

    @EJB
    private DeviceManagerDB deviceManagerDB;

    /** @common:operation */
    public LogAtualizacaoParametrosAparelho getDataAtualizacaoParametrosApareho(String user, String nrLinha) {

        LogAtualizacaoParametrosAparelho logAtualizacaoParametrosAparelho = null;

        try {
            StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO)
            .append(" SELECT IDLOGDEVICEMANAGER, NRLINHA, IDUSUARIOALTERACAO, DTULTIMAALTERACAO ")
            .append(" FROM LINHA.LOGDEVICEMANAGER ")
            .append(" WHERE IDLOGDEVICEMANAGER = (SELECT MAX(IDLOGDEVICEMANAGER)  FROM LINHA.LOGDEVICEMANAGER WHERE NRLINHA = ").append(nrLinha).append(")");

            log.debug(query.toString());
            logAtualizacaoParametrosAparelho = deviceManagerDB.executeQuery(query.toString());

        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
        }
        return logAtualizacaoParametrosAparelho;
    }

    /**
     * @common:operation
     */
    public void incluiLogDeviceManager(String idUsuario, String nrLinha) throws Exception {

        try {
            StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
            query.append("INSERT INTO LINHA.LOGDEVICEMANAGER (IDLOGDEVICEMANAGER, NRLINHA, IDUSUARIOALTERACAO, DTULTIMAALTERACAO) ")
            .append(" VALUES (LINHA.LOGDEVICEMANAGERSQ.NEXTVAL, ").append(nrLinha).append(", ").append(idUsuario).append(", SYSDATE)");

            deviceManagerDB.executeCommand(query.toString());

            query = null;

        } catch (Exception e) {
            throw (new FacadeException("Erro na inclusão: DeviceManagerImpl:incluiLogDeviceManager", e));
        }
    }

}
