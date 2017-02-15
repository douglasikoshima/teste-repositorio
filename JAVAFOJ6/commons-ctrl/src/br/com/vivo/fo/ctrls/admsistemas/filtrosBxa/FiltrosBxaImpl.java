package br.com.vivo.fo.ctrls.admsistemas.filtrosBxa;

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
import br.com.vivo.fo.admsistemas.vo.AdmIdBaixaVODocument.AdmIdBaixaVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "FiltrosBxa", mappedName = "FiltrosBxa")
@Local(FiltrosBxa.class)
@Session(ejbName = "FiltrosBxa", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class FiltrosBxaImpl implements FiltrosBxa {

    @EJB
    private TuxedoServiceCall tuxedo;

    static final long         serialVersionUID = 1L;
    private static Logger     log              = new Logger("admsistemas");
    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public AdmFiltrosVO listaFiltros(AdmIdBaixaVO id, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("FiltrosBxaImpl:listaFiltros(").append(user).append(", ").append(id).append(")").toString());

            xmlIN = XmlManager.MakeXmlIn(user, "BXAFTRRELACAO", id.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            AdmFiltrosVODocument doc = AdmFiltrosVODocument.Factory.parse(xmlOUT);
            AdmFiltrosVO admFiltrosVO = doc.getAdmFiltrosVO();

            return admFiltrosVO;

        } catch (XmlException ex) {
            log.error("XmlException - FiltrosBxaImpl:listaFiltros(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: FiltrosBxaImpl:listaFiltros", ex));

        } catch (Exception ex) {
            log.error("Exception - FiltrosBxaImpl:listaFiltros(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmFiltrosVO salvaFiltros(AdmFiltrosVO filtros, String user) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("salvaFiltros(").append(user).append(", ").append(filtros).append(")").toString());

            xmlIN = XmlManager.MakeXmlIn(user, "BXAFTRRELACIO", filtros.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

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
            log.error("XmlException - FiltrosBxaImpl:salvaFiltros(" + user + ", " + filtros + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: FiltrosBxaImpl:salvaFiltros", ex));

        } catch (Exception ex) {
            log.error("Exception - FiltrosBxaImpl:salvaFiltros(" + user + ", " + filtros + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
