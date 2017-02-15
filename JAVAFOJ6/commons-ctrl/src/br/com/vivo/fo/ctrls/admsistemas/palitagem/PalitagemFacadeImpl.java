package br.com.vivo.fo.ctrls.admsistemas.palitagem;

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
import br.com.vivo.fo.admsistemas.vo.AdmContatoPalitagemVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmContatoPalitagemVODocument.AdmContatoPalitagemVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "PalitagemFacade", mappedName = "PalitagemFacade")
@Local(PalitagemFacade.class)
@Session(ejbName = "PalitagemFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PalitagemFacadeImpl implements PalitagemFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    static final long         serialVersionUID = 1125545533026L;
    private static Logger     log              = new Logger("clientes");
    String                    xmlIN            = ConstantesCRM.SVAZIO;
    String                    xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public void setAdmContatoPalitagemVO(String idUsuario, AdmContatoPalitagemVO admContatoPalitagemVO) throws FacadeException {

        try {
            xmlIN = admContatoPalitagemVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(idUsuario, "SETPALITAR", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return;

        } catch (XmlException ex) {
            log.error("XmlException - FaturaFacadeImpl:setAdmContatoPalitagemVO() - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ServicoFacadeImpl:isAtivo", ex));
        } catch (Exception ex) {
            log.error("Exception - FaturaFacadeImpl:setAdmContatoPalitagemVO() - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public AdmContatoPalitagemVO getAdmContatoPalitagemVO(String idUsuario, AdmContatoPalitagemVO admContatoPalitagemVO) throws FacadeException {

        try {
            xmlIN = admContatoPalitagemVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(idUsuario, "GETPALITAR", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            AdmContatoPalitagemVO retorno = AdmContatoPalitagemVODocument.Factory.parse(xmlOUT).getAdmContatoPalitagemVO();

            return retorno;

        } catch (XmlException ex) {
            log.error("XmlException - FaturaFacadeImpl:getAdmContatoPalitagemVO() - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ServicoFacadeImpl:isAtivo", ex));
        } catch (Exception ex) {
            log.error("Exception - FaturaFacadeImpl:getAdmContatoPalitagemVO() - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
