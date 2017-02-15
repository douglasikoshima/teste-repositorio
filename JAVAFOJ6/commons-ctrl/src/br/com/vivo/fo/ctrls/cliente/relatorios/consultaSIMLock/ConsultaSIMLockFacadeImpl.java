package br.com.vivo.fo.ctrls.cliente.relatorios.consultaSIMLock;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.MsgDocument;
import org.apache.xmlbeans.XmlException;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.cliente.vo.RelatorioSimLockVODocument;
import br.com.vivo.fo.cliente.vo.RelatorioSimLockVODocument.RelatorioSimLockVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.relatorios.consultaSIMLock.dbClasses.CamposConsultaSimLock;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ConsultaSIMLockFacade", mappedName = "ConsultaSIMLockFacade")
@Local(ConsultaSIMLockFacade.class)
@Session(ejbName = "ConsultaSIMLockFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ConsultaSIMLockFacadeImpl implements ConsultaSIMLockFacade {
    
    @EJB
    private TuxedoServiceCall tuxedo;

    @EJB
    private ConsultaSIMLockDB consultaSIMLockDB;

    private String            xmlIN;
    private String            xmlOUT;
    private static Logger     log              = new Logger("cliente");

    /**
     * @common:operation
     */
    public RelatorioSimLockVO getTotalRegistros(String user, RelatorioSimLockVO relatorio) throws TuxedoException, FacadeException {

        //RelatorioSimLockVODocument rel = RelatorioSimLockVODocument.Factory.newInstance();
        RelatorioSimLockVO relatorioRetorno = null;
        try {

            xmlIN = relatorio.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "RELSIMLOCK", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            relatorioRetorno = RelatorioSimLockVODocument.Factory.parse(xmlOUT).getRelatorioSimLockVO();

            return relatorioRetorno;

        } catch (XmlException ex) {

            log.error("XmlException - ConsultaSIMLockFacadeImpl:getTotalRegistros(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConsultaSIMLockFacadeImpl:editaGrupo", ex));

        } catch (Exception ex) {

            log.error("Exception - ConsultaSIMLockFacadeImpl:editaGrupo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RelatorioSimLockVO getRelatorioDownloadOld(String user, RelatorioSimLockVO relatorio) throws TuxedoException, FacadeException {
        //RelatorioSimLockVODocument rel = RelatorioSimLockVODocument.Factory.newInstance();
        RelatorioSimLockVO relatorioRetorno = null;
        try {

            xmlIN = relatorio.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "RELSIMLOCK", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            relatorioRetorno = RelatorioSimLockVODocument.Factory.parse(xmlOUT).getRelatorioSimLockVO();

            return relatorioRetorno;

        } catch (XmlException ex) {
            log.error("XmlException - ConsultaSIMLockFacadeImpl:getRelatorioDownload(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: getRelatorioDownload:editaGrupo", ex));

        } catch (Exception ex) {
            log.error("Exception - ConsultaSIMLockFacadeImpl:getRelatorioDownload(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RelatorioSimLockVO getRelatorioDownload(String user, RelatorioSimLockVO relatorio) throws Exception {

        StringBuffer query = new StringBuffer();

        query.append("SELECT ");
        query.append("    to_char(clsl.dtultimaalteracao, 'DD/MM/YYYY HH:MI:SS') as dtultimaalteracao, ");
        query.append("    clsl.imei nrImei, ");
        query.append("    au.nmloginusuario, ");
        query.append("    clsl.ip nrIp, ");
        query.append("    clsl.estadoconsulta, ");
        query.append("    cplh.cdarearegistro || cplh.nrlinha as nrlinha, ");
        query.append("    ctr.nmtiporelacionamento, ");
        query.append("    atd.sgtipodocumento, ");
        query.append("    clsl.nrdocumento, ");
        query.append("    cp.nmpessoa ");
        query.append("FROM ");
        query.append("    apoio.tipodocumento atd, ");
        query.append("    acesso.usuario au, ");
        query.append("    customer.pessoalinhahistorico cplh, ");
        query.append("    customer.pessoa cp, ");
        query.append("    customer.tiporelacionamento ctr, ");
        query.append("    customer.logsimlock clsl ");
        query.append("WHERE clsl.idtipodocumento = atd.idtipodocumento ");
        query.append("    AND clsl.idpessoalinhahistorico = cplh.idpessoalinhahistorico ");
        query.append("    AND clsl.idpessoa = cp.idpessoa ");
        query.append("    AND cplh.idtiporelacionamento = ctr.idtiporelacionamento ");
        query.append("    AND clsl.idusuarioalteracao = au.idpessoausuario ");

        if (relatorio.getFiltro().getNrImei() != null && !ConstantesCRM.SVAZIO.equals(relatorio.getFiltro().getNrImei())) {
            query.append("    AND clsl.imei = '").append(relatorio.getFiltro().getNrImei()).append("' ");
        }
        if (relatorio.getFiltro().getNmLogin() != null && !ConstantesCRM.SVAZIO.equals(relatorio.getFiltro().getNmLogin())) {
            query.append("    AND au.nmloginusuario = '").append(relatorio.getFiltro().getNmLogin()).append("' ");
        }
        if (relatorio.getFiltro().getDtAltIni() != null && !ConstantesCRM.SVAZIO.equals(relatorio.getFiltro().getDtAltIni())) {
            query.append("    AND clsl.dtultimaalteracao ");
            query.append("        between to_date('").append(relatorio.getFiltro().getDtAltIni()).append("', 'DD/MM/YYYY') ");
            query.append("        AND to_date('").append(relatorio.getFiltro().getDtAltFim()).append(" 23:59:59', 'DD/MM/YYYY HH24:MI:SS') ");
        }
        if (relatorio.getFiltro().getNrLinha() != null && !ConstantesCRM.SVAZIO.equals(relatorio.getFiltro().getNrLinha())) {
            query.append("    AND cplh.nrlinha = '").append(relatorio.getFiltro().getNrLinha()).append("' ");
            query.append("    AND cplh.cdarearegistro = '").append(relatorio.getFiltro().getNrArea()).append("' ");
        }
        if (relatorio.getFiltro().getNrDoc() != null && !ConstantesCRM.SVAZIO.equals(relatorio.getFiltro().getNrDoc())) {
            query.append("    AND clsl.nrdocumento = '").append(relatorio.getFiltro().getNrDoc()).append("' ");
        }
        // query.append("ORDER BY clsl.dtultimaalteracao ");

        log.debug("ConsultaSIMLockFacadeImpl:getRelatorioDownload() = " + query.toString());
        CamposConsultaSimLock[] dbResult = consultaSIMLockDB.getRelatorioSimLock(query.toString());
        RelatorioSimLockVO relatorioRetorno = buildTrackingRelatorioSimLock(dbResult);
        query = null;
        dbResult = null;
        return relatorioRetorno;
    }

    private RelatorioSimLockVO buildTrackingRelatorioSimLock(CamposConsultaSimLock[] dbResult) {
        RelatorioSimLockVO relatorioSimLockVO = RelatorioSimLockVO.Factory.newInstance();
        for (int i = 0; i < dbResult.length; i++) {
            RelatorioSimLockVO.ListaRelatorio lista = relatorioSimLockVO.addNewListaRelatorio();
            lista.setDtSolic(dbResult[i].getDtultimaalteracao());
            lista.setImei(dbResult[i].getNrimei());
            lista.setIp(dbResult[i].getNrip());
            lista.setLogin(dbResult[i].getNmloginusuario());
            lista.setNmCli(dbResult[i].getNmpessoa());
            lista.setNrDoc(dbResult[i].getNrdocumento());
            lista.setNrLinha(dbResult[i].getNrlinha());
            lista.setStConsulta(dbResult[i].getEstadoconsulta());
            lista.setTpDoc(dbResult[i].getSgtipodocumento());
            lista.setTpRelac(dbResult[i].getNmtiporelacionamento());
        }
        dbResult = null;
        return relatorioSimLockVO;
    }

    /**
     * @common:operation
     */
    public RelatorioSimLockVO getPaginaRelatorio(String user, RelatorioSimLockVO relatorio) throws TuxedoException, FacadeException {
        //RelatorioSimLockVODocument rel = RelatorioSimLockVODocument.Factory.newInstance();
        RelatorioSimLockVO relatorioRetorno = null;
        try {

            xmlIN = relatorio.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "RELSIMLOCK", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            relatorioRetorno = RelatorioSimLockVODocument.Factory.parse(xmlOUT).getRelatorioSimLockVO();

            return relatorioRetorno;

        } catch (XmlException ex) {
            log.error("XmlException - ConsultaSIMLockFacadeImpl:getPaginaRelatorio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConsultaSIMLockFacadeImpl:getPaginaRelatorio", ex));

        } catch (Exception ex) {
            log.error("Exception - ConsultaSIMLockFacadeImpl:getPaginaRelatorio(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}