package br.com.vivo.fo.ctrls.admsistemas.classificador;

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
import br.com.vivo.fo.admsistemas.vo.AdmClassificadorCampoVODocument.AdmClassificadorCampoVO;
import br.com.vivo.fo.admsistemas.vo.AdmClassificadorCamposVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmClassificadorCamposVODocument.AdmClassificadorCamposVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ClassificadorFacade", mappedName = "ClassificadorFacade")
@Local(ClassificadorFacade.class)
@Session(ejbName = "ClassificadorFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ClassificadorFacadeImpl implements ClassificadorFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("admsistemas");
    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public void removeClassificador(AdmClassificadorCampoVO admClassificadorCampoVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ClassificadorFacadeImpl:removeClassificador(" + user + ")");

            xmlIN = admClassificadorCampoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMCLAREMOVE", xmlIN);

            // new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

        } catch (XmlException ex) {
            log.error("XmlException - ClassificadorFacadeImpl:removeClassificador(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ClassificadorFacadeImpl:removeClassificador", ex));

        } catch (Exception ex) {
            log.error("Exception - ClassificadorFacadeImpl:removeClassificador(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmClassificadorCamposVO listaClassificador(AdmClassificadorCampoVO admClassificadorCampoVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ClassificadorFacadeImpl:listaClassificador(" + user + ")");

            xmlIN = admClassificadorCampoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMCLALISTA", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmClassificadorCamposVODocument.Factory.parse(xmlOUT).getAdmClassificadorCamposVO();

        } catch (XmlException ex) {
            log.error("XmlException - ClassificadorFacadeImpl:listaClassificador(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ClassificadorFacadeImpl:listaClassificador", ex));

        } catch (Exception ex) {
            log.error("Exception - ClassificadorFacadeImpl:listaClassificador(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmClassificadorCamposVO addClassificador(AdmClassificadorCampoVO admClassificadorCampoVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ClassificadorFacadeImpl:addClassificador(" + user + ")");

            xmlIN = admClassificadorCampoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMCLAINSERI", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmClassificadorCamposVODocument.Factory.parse(xmlOUT).getAdmClassificadorCamposVO();

        } catch (XmlException ex) {
            log.error("XmlException - ClassificadorFacadeImpl:addClassificador(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ClassificadorFacadeImpl:addClassificador", ex));

        } catch (Exception ex) {
            log.error("Exception - ClassificadorFacadeImpl:addClassificador(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmClassificadorCamposVO updateClassificador(AdmClassificadorCampoVO admClassificadorCampoVO, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ClassificadorFacadeImpl:updateClassificador(" + user + ")");

            xmlIN = admClassificadorCampoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ADMCLAALTERA", xmlIN);

            // xmlOUT = new ControlTuxedoCall().execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return AdmClassificadorCamposVODocument.Factory.parse(xmlOUT).getAdmClassificadorCamposVO();

        } catch (XmlException ex) {
            log.error("XmlException - ClassificadorFacadeImpl:updateClassificador(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ClassificadorFacadeImpl:updateClassificador", ex));

        } catch (Exception ex) {
            log.error("Exception - ClassificadorFacadeImpl:updateClassificador(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
