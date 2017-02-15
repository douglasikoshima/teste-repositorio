package br.com.vivo.fo.ctrls.admsistemas.filtros;

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
import br.com.vivo.fo.admsistemas.vo.AdmFiltrosVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmFiltrosVODocument.AdmFiltrosVO;
import br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "Filtros", mappedName = "Filtros")
@Local(Filtros.class)
@Session(ejbName = "Filtros", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class FiltrosImpl implements Filtros {
    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("admsistemas");
    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public AdmFiltrosVO listaFiltros(AdmIdContatoVO id, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("FiltrosImpl:listaFiltros(" + user + ", " + id + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "CttFtrRelacao", id.xmlText().replaceAll("vo:", ""));

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmFiltrosVO admFiltrosVO = null;
            AdmFiltrosVODocument doc = AdmFiltrosVODocument.Factory.parse(xmlOUT);

            admFiltrosVO = doc.getAdmFiltrosVO();

            return admFiltrosVO;

        } catch (XmlException ex) {
            log.error("XmlException - FiltrosImpl:listaFiltros(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: FiltrosImpl:listaFiltros", ex));

        } catch (Exception ex) {
            log.error("Exception - FiltrosImpl:listaFiltros(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmFiltrosVO salvaFiltros(AdmFiltrosVO filtros, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("FiltrosImpl:salvaFiltros(" + user + ", " + filtros + ")");

            xmlIN = XmlManager.MakeXmlIn(user, "CTTFTRRELACIO", filtros.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmFiltrosVO admFiltrosVO = null;
            AdmFiltrosVODocument doc = AdmFiltrosVODocument.Factory.parse(xmlOUT);

            admFiltrosVO = doc.getAdmFiltrosVO();

            return admFiltrosVO;

        } catch (XmlException ex) {
            log.error("XmlException - FiltrosImpl:salvaFiltros(" + user + ", " + filtros + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: FiltrosImpl:salvaFiltros", ex));

        } catch (Exception ex) {
            log.error("Exception - FiltrosImpl:salvaFiltros(" + user + ", " + filtros + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
