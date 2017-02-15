package br.com.vivo.fo.ctrls.admsistemas.perfilGrupoCRIContato;

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
import br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument;
import br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument.PerfilVariaveisVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;


@Stateless(name = "PerfilGrupoCRIContato", mappedName = "PerfilGrupoCRIContato")
@Local(PerfilGrupoCRIContato.class)
@Session(ejbName = "PerfilGrupoCRIContato", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PerfilGrupoCRIContatoImpl implements PerfilGrupoCRIContato {
    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("admsistemas");

    static final long         serialVersionUID = 1L;

    /**
     * @common:operation
     */
    public PerfilVariaveisVO getPerfilGrupo(String user, String parametro) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("PerfilGrupoCRIContatoImpl:getPerfilGrupo(").append(user).append(", ").append(parametro).append(")").toString());

            String inService = TuxedoServiceBridge.getXMLRequest(user, "LSTPERFILGRU", parametro);
            // String xmlOut= "";//admSistemasTux.GETSERVICE(inService);
            String xmlOut = tuxedo.callService("TuxConnectorADM", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return PerfilVariaveisVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getPerfilVariaveisVO();

        } catch (XmlException ex) {
            log.error("XmlException - PerfilGrupoCRIContatoImpl:getPerfilGrupo(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PerfilGrupoCRIContatoImpl:getPerfilGrupo", ex));

        } catch (Exception ex) {
            log.error("Exception - PerfilGrupoCRIContatoImpl:getPerfilGrupo(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public PerfilVariaveisVO getPerfil(String user, String parametro) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("PerfilGrupoCRIContatoImpl:getPerfil(").append(user).append(", ").append(parametro).append(")").toString());

            String inService = TuxedoServiceBridge.getXMLRequest(user, "LSTPERFILASSOC", parametro);
            // String xmlOut= "";//admSistemasTux.GETSERVICE(inService);
            String xmlOut = tuxedo.callService("TuxConnectorADM", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return PerfilVariaveisVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getPerfilVariaveisVO();

        } catch (XmlException ex) {
            log.error("XmlException - PerfilGrupoCRIContatoImpl:getPerfil(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PerfilGrupoCRIContatoImpl:getPerfil", ex));

        } catch (Exception ex) {
            log.error("Exception - PerfilGrupoCRIContatoImpl:getPerfil(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public PerfilVariaveisVO getGrupo(String user, String parametro) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("PerfilGrupoCRIContatoImpl:getGrupo(").append(user).append(", ").append(parametro).append(")").toString());

            String inService = TuxedoServiceBridge.getXMLRequest(user, "LSTGRUPOASSOC", parametro);
            // String xmlOut= "";//admSistemasTux.GETSERVICE(inService);
            String xmlOut = tuxedo.callService("TuxConnectorADM", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return PerfilVariaveisVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getPerfilVariaveisVO();

        } catch (XmlException ex) {
            log.error("XmlException - PerfilGrupoCRIContatoImpl:getGrupo(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PerfilGrupoCRIContatoImpl:getGrupo", ex));

        } catch (Exception ex) {
            log.error("Exception - PerfilGrupoCRIContatoImpl:getGrupo(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoRetornoVO setPerfilGrupoConatato(String user, String parametro) throws TuxedoException, FacadeException {
        try {
            log.debug(new StringBuffer("PerfilGrupoCRIContatoImpl:setPerfilGrupoConatato(").append(user).append(", ").append(parametro).append(")").toString());

            String inService = TuxedoServiceBridge.getXMLRequest(user, "CFGPRFGRUCON", parametro);
            // String xmlOut= "";//admSistemasTux.GETSERVICE(inService);
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - PerfilGrupoCRIContatoImpl:setPerfilGrupoConatato(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PerfilGrupoCRIContatoImpl:setPerfilGrupoConatato", ex));

        } catch (Exception ex) {
            log.error("Exception - PerfilGrupoCRIContatoImpl:setPerfilGrupoConatato(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
