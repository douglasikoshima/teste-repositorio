package br.com.vivo.fo.ctrls.VOLTAV.manterBanner;


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
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;
import br.com.vivo.tav.banner.VOLTAVManterBannerVODocument;
import br.com.vivo.tav.banner.VOLTAVManterBannerVODocument.VOLTAVManterBannerVO;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="ManterBannerFacade",mappedName="ManterBannerFacade")
@Local(ManterBannerFacade.class)
@Session(ejbName = "ManterBannerFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterBannerFacadeImpl implements ManterBannerFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

    static final long serialVersionUID = 1L;
    private String xmlIN;
    private String xmlOUT;
    private final static transient Logger log = new Logger("vol");

    /**
     * @common:operation
     */
    public VOLTAVManterBannerVO getParametroBuscaBanner(String xml, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ManterBannerFacadeImpl:getParametroBuscaBanner(" + user + ")");

            xmlIN = xml;
            xmlIN = XmlManager.MakeXmlIn(user, "GETPARBANNER", xmlIN);

            // Executa chamada ao servico Tuxedo
            //xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            VOLTAVManterBannerVO voltavManterBannerVO = VOLTAVManterBannerVODocument.Factory.parse(xmlOUT).getVOLTAVManterBannerVO();

            return voltavManterBannerVO;

        } catch (XmlException ex) {
            //Monta mensagem de erro
            log.error("XmlException - ManterBannerFacadeImpl:getParametroBuscaBanner(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterMenuFacadeImpl:getParametroBuscaBanner", ex));

        } catch (TuxedoServiceCallerException ex) {
            //Monta mensagem de erro
            log.error("TuxedoException - ManterBannerFacadeImpl:getParametroBuscaBanner(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            //Monta mensagem de erro
            log.error("Exception - ManterBannerFacadeImpl:getParametroBuscaBanner(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public VOLTAVManterBannerVO getBanner(String xml, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ManterBannerFacadeImpl:getBanner(" + user + ")");

            xmlIN = xml;
            xmlIN = XmlManager.MakeXmlIn(user, "GETBANNER", xmlIN);

            // Executa chamada ao servico Tuxedo
            //xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            VOLTAVManterBannerVO voltavManterBannerVO = VOLTAVManterBannerVODocument.Factory.parse(xmlOUT).getVOLTAVManterBannerVO();

            return voltavManterBannerVO;

        } catch (XmlException ex) {
            //Monta mensagem de erro
            log.error("XmlException - ManterBannerFacadeImpl:getBanner(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterMenuFacadeImpl:getBanner", ex));

        } catch (TuxedoServiceCallerException ex) {
            //Monta mensagem de erro
            log.error("TuxedoException - ManterBannerFacadeImpl:getBanner(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            //Monta mensagem de erro
            log.error("Exception - ManterBannerFacadeImpl:getBanner(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public VOLTAVManterBannerVO pesquisarBanner(String xml, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("ManterBannerFacadeImpl:pesquisaBanner(" + user + ")");

            xmlIN = xml;
            xmlIN = XmlManager.MakeXmlIn(user, "PESQBANNER", xmlIN);

            // Executa chamada ao servico Tuxedo
            //xmlOUT = new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            VOLTAVManterBannerVO voltavManterBannerVO = VOLTAVManterBannerVODocument.Factory.parse(xmlOUT).getVOLTAVManterBannerVO();

            return voltavManterBannerVO;

        } catch (XmlException ex) {
            //Monta mensagem de erro
            log.error("XmlException - ManterBannerFacadeImpl:pesquisaBanner(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterMenuFacadeImpl:pesquisaBanner", ex));

        } catch (TuxedoServiceCallerException ex) {
            //Monta mensagem de erro
            log.error("TuxedoException - ManterBannerFacadeImpl:pesquisaBanner(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            //Monta mensagem de erro
            log.error("Exception - ManterBannerFacadeImpl:getParametroBuscaBanner(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void incluirBanner(String xml, String user) throws TuxedoException, FacadeException {
        try {

            log.debug("ManterBannerFacadeImpl:incluirBanner(" + user + ")");

            xmlIN = xml;
            xmlIN = XmlManager.MakeXmlIn(user, "SETBANNER", xmlIN);

            // Executa chamada ao servico Tuxedo
            //new ControlTuxedoCall().execute(this, volTavTux, "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();


        } catch (XmlException ex) {
            //Monta mensagem de erro
            log.error("XmlException - ManterBannerFacadeImpl:incluirBanner(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterMenuFacadeImpl:incluirBanner", ex));

        } catch (TuxedoServiceCallerException ex) {
            //Monta mensagem de erro
            log.error("TuxedoException - ManterBannerFacadeImpl:incluirBanner(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            //Monta mensagem de erro
            log.error("Exception - ManterBannerFacadeImpl:incluirBanner(" + user + ") - [" + ex.getMessage() + "]");

            //Lança exceção
            throw (new FacadeException(ex));
        }
    }
}
