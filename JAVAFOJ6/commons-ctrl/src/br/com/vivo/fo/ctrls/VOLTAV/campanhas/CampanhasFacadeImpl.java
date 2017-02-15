package br.com.vivo.fo.ctrls.VOLTAV.campanhas;

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
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.campanha.vo.CampanhaVODocument;
import br.com.vivo.fo.campanha.vo.CampanhaVODocument.CampanhaVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name="CampanhasFacade",mappedName="CampanhasFacade")
@Local(CampanhasFacade.class)
@Session(ejbName = "CampanhasFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CampanhasFacadeImpl implements CampanhasFacade {
	
	@EJB
	private TuxedoServiceCall tuxedo;

    static final long serialVersionUID        = 1L;
    private String xmlIN                      = ConstantesCRM.SVAZIO;
    private String xmlOUT                     = ConstantesCRM.SVAZIO;
    private final static transient Logger log = new Logger("vol");

    public CampanhaVO getCampanhaVO(String user, String serviceName, CampanhaVO filtro) throws TuxedoException, FacadeException {

        try {

            CampanhaVO campanhaVO = CampanhaVO.Factory.newInstance();
            log.debug("CampanhasFacadeImpl:getDetalheCampanhaVO(" + user + ")" );

            xmlIN = filtro.xmlText().replaceAll("vo:",ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, serviceName, xmlIN);
            //xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            
            campanhaVO = CampanhaVODocument.Factory.parse(xmlOUT).getCampanhaVO();

            return campanhaVO;

        } catch(XmlException ex) {
            log.error("XmlException - CampanhasFacadeImpl:getDetalheCampanhaVO(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: CampanhasFacadeImpl:getDetalheCampanhaVO", ex));
        } catch(TuxedoServiceCallerException ex) {
            log.error("TuxedoException - CampanhasFacadeImpl:getDetalheCampanhaVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch(Exception ex) {
            log.error("Exception - CampanhasFacadeImpl:getDetalheCampanhaVO(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void setCampanhaVO(String user, CampanhaVO filtro) throws TuxedoException, FacadeException {

        try {

            CampanhaVO campanhaVO = CampanhaVO.Factory.newInstance();
            log.debug("CampanhasFacadeImpl:setCampanhaVO(" + user + ")" );

            xmlIN = filtro.xmlText().replaceAll("vo:",ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "CAMPANHCRUD", xmlIN);
            
            //xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            
            return;

        } catch(XmlException ex) {
            log.error("XmlException - CampanhasFacadeImpl:setCampanhaVO(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException("Erro na montagem do XML de entrada: CampanhasFacadeImpl:setCampanhaVO", ex));
        } catch(TuxedoServiceCallerException ex) {
            log.error("TuxedoException - CampanhasFacadeImpl:setCampanhaVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch(Exception ex) {
            log.error("Exception - CampanhasFacadeImpl:setCampanhaVO(" + user + ") - [" + ex.getMessage() + "]");
            throw(new FacadeException(ex));
        }
    }

}