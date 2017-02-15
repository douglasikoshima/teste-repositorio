package br.com.vivo.fo.ctrls.VOLTAV.manterDaemonDesativacaoComprovanteEmail;


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
import br.com.vivo.fo.cliente.vo.FilaComprovanteEmailVODocument;
import br.com.vivo.fo.cliente.vo.FilaComprovanteEmailVODocument.FilaComprovanteEmailVO;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="manterDaemonDesativacaoComprovanteEmail",mappedName="manterDaemonDesativacaoComprovanteEmail")
@Local(manterDaemonDesativacaoComprovanteEmail.class)
@Session(ejbName = "manterDaemonDesativacaoComprovanteEmail", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class manterDaemonDesativacaoComprovanteEmailImpl implements manterDaemonDesativacaoComprovanteEmail {

	@EJB
	private TuxedoServiceCall tuxedo;
	
    static final long serialVersionUID = 1L;
    private final static transient Logger log = new Logger("vol");
    private String xmlOUT;
    private String xmlIN;

    /**
     * @common:operation
     */
    public FilaComprovanteEmailVO manterDaemon(String xml, String user) throws TuxedoException, FacadeException {
        try {

            log.debug("ManterDaemonFacadeImpl:manterDaemon(" + user + ")");

            xmlIN = xml;
            xmlIN = XmlManager.MakeXmlIn(user, "FILACOMPSVC", xmlIN);

            // Executa chamada ao servico Tuxedo
            //xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            
            FilaComprovanteEmailVO filaPrePagoVO = FilaComprovanteEmailVODocument.Factory.parse(xmlOUT).getFilaComprovanteEmailVO();

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
