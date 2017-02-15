package br.com.vivo.fo.ctrls.cliente.prospect;

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
import br.com.vivo.fo.cliente.vo.CadastroProspectVODocument;
import br.com.vivo.fo.cliente.vo.CadastroProspectVODocument.CadastroProspectVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ProspectFacade", mappedName = "ProspectFacade")
@Local(ProspectFacade.class)
@Session(ejbName = "ProspectFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ProspectFacadeImpl implements ProspectFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("clientes");

    static final long         serialVersionUID = 15841665012885L;
    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public CadastroProspectVO getListas(String user, String idTipoPessoa) throws TuxedoException, FacadeException {

        CadastroProspectVO cadastroProspect = CadastroProspectVO.Factory.newInstance();

        try {
            xmlIN = "<idTipoPessoa>" + idTipoPessoa + "</idTipoPessoa>";
            xmlIN = XmlManager.MakeXmlIn(user, "ProspectIni", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            cadastroProspect = CadastroProspectVODocument.Factory.parse(xmlOUT).getCadastroProspectVO();

        } catch (XmlException ex) {
            log.error("XmlException - ProspectFacadeImpl:getListas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ProspectFacadeImpl:getListas", ex));

        } catch (Exception ex) {
            log.error("Exception - ProspectFacadeImpl:getListas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return cadastroProspect;
    }

    /**
     * @common:operation
     */
    public String gravaProspect(CadastroProspectVO cadastroProspectVO, String user) throws TuxedoException, FacadeException {

        try {
            if (cadastroProspectVO != null && cadastroProspectVO.getProspectVO() != null && "null".equalsIgnoreCase(cadastroProspectVO.getProspectVO().getNrLinhaProspect())) {
                cadastroProspectVO.getProspectVO().unsetNrLinhaProspect();
            }

            xmlIN = XmlManager.MakeXmlIn(user, "ProspectIns", cadastroProspectVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));
            // xmlOUT = (new ControlTuxedoCall()).execute( this, clienteTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return xmlOUT;

        } catch (XmlException ex) {
            log.error("XmlException - ProspectFacadeImpl:gravaProspect(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ProspectFacadeImpl:gravaProspect", ex));

        } catch (Exception ex) {
            log.error("Exception - ProspectFacadeImpl:gravaProspect(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
