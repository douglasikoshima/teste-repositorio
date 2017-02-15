package br.com.vivo.fo.ctrls.VOLTAV.manterDaemon;


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
import br.com.vivo.fo.cliente.vo.FilaPrePagoVODocument;
import br.com.vivo.fo.cliente.vo.FilaPrePagoVODocument.FilaPrePagoVO;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="ManterDaemonFacade",mappedName="ManterDaemonFacade")
@Local(ManterDaemonFacade.class)
@Session(ejbName = "ManterDaemonFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterDaemonFacadeImpl implements ManterDaemonFacade {

	@EJB
	private TuxedoServiceCall tuxedo;
	
    static final long serialVersionUID = 1L;
    private String xmlIN;
    private String xmlOUT;
    private final static transient Logger log = new Logger("vol");

    /**
     * @common:operation
     */
    public FilaPrePagoVO manterDaemon(String xml, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ManterDaemonFacadeImpl:manterDaemon(" + user + ")");

            xmlIN = xml;
            xmlIN = XmlManager.MakeXmlIn(user, "FILAPREPAGO", xmlIN);

            // Executa chamada ao servico Tuxedo
            //xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            FilaPrePagoVO filaPrePagoVO = FilaPrePagoVODocument.Factory.parse(xmlOUT).getFilaPrePagoVO();

            return filaPrePagoVO;

        } catch (XmlException ex) {
            //Monta mensagem de erro
            log.error("XmlException - ManterDaemonFacadeImpl:manterDaemon(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterMenuFacadeImpl:manterDaemon", ex));

        } catch (TuxedoServiceCallerException ex) {
            //Monta mensagem de erro
            log.error("TuxedoException - ManterDaemonFacadeImpl:manterDaemon(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            //Monta mensagem de erro
            log.error("Exception - ManterBannerFacadeImpl:getParametroBuscaBanner(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw (new FacadeException(ex));
        }
    }
}
