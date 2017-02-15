package br.com.vivo.fo.ctrls.cliente.protocolo;

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
import br.com.vivo.fo.atendimento.vo.AbreProtocoloOutTODocument;
import br.com.vivo.fo.atendimento.vo.AbreProtocoloOutTODocument.AbreProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.AbreProtocoloTODocument.AbreProtocoloTO;
import br.com.vivo.fo.atendimento.vo.AlterProtocoloOutTODocument;
import br.com.vivo.fo.atendimento.vo.AlterProtocoloOutTODocument.AlterProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.FechaProtocoloOutTODocument;
import br.com.vivo.fo.atendimento.vo.FechaProtocoloOutTODocument.FechaProtocoloOutTO;
import br.com.vivo.fo.atendimento.vo.FechaProtocoloTODocument.FechaProtocoloTO;
import br.com.vivo.fo.atendimento.vo.GetDadosProtocoloOutTODocument;
import br.com.vivo.fo.atendimento.vo.GetDadosProtocoloOutTODocument.GetDadosProtocoloOutTO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.cliente.vo.AlterProtocoloTODocument.AlterProtocoloTO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ProtocoloFacade", mappedName = "ProtocoloFacade")
@Local(ProtocoloFacade.class)
@Session(ejbName = "ProtocoloFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ProtocoloFacadeImpl implements ProtocoloFacade {

    private static final long serialVersionUID = 105857520084L;

    @EJB
    private TuxedoServiceCall tuxedo;

    private transient Logger  log              = new Logger("clientes");

    /**
     * @common:operation
     */
    public AlterProtocoloOutTO setAlteracaoProtocolo(String user, AlterProtocoloTO alterProtocoloTO, String operacao, String nrProtocolo, int qtProcessosAbertos, int qtProcessosPendentes) throws TuxedoException, FacadeException {
        AlterProtocoloOutTO alterProtocoloOutTO = AlterProtocoloOutTO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            StringBuffer sB = new StringBuffer();
            sB.append("<nrProtocolo>").append(nrProtocolo).append("</nrProtocolo>");

            /*
             * Valores possíveis para 'operacao' 1) 'alterar' Alterar dados de
             * protocolo 2) 'incrTotAberPend' Incrementar o total de processos
             * abertos e pendentes 3) 'altStatus' Alterar o status do protocolo
             */
            sB.append("<operacao>").append(operacao).append("</operacao>");

            // Os valores 'qtProcessosAbertos' e 'qtProcessosPendentes'
            // são necessários quando a operacao for 'incrTotAberPend'.
            if ("incrTotAberPend".equals(operacao)) {
                sB.append("<incAberto>").append(qtProcessosAbertos).append("</incAberto>");
                sB.append("<incPendente>").append(qtProcessosPendentes).append("</incPendente>");
            }

            xmlIN = sB.toString() + alterProtocoloTO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService(ConstantesCRM.TuxConnector, XmlManager.MakeXmlIn(user, "ATDALTERPROT", xmlIN));

            alterProtocoloOutTO = AlterProtocoloOutTODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getAlterProtocoloOutTO();

        } catch (XmlException ex) {
            log.error("XmlException - ProtocoloFacadeImpl:setAlteracaoProtocolo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ProtocoloFacadeImpl:setAlteracaoProtocolo", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ProtocoloFacadeImpl:setAlteracaoProtocolo(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ProtocoloFacadeImpl:setAlteracaoProtocolo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return alterProtocoloOutTO;
    }

    /**
     * @common:operation
     */
    public AbreProtocoloOutTO setAbreValidaProtocolo(String user, AbreProtocoloTO abreProtocoloTO) throws TuxedoException, FacadeException {
        AbreProtocoloOutTO out = AbreProtocoloOutTO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = abreProtocoloTO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService(ConstantesCRM.TuxConnector, XmlManager.MakeXmlIn(user, "ATDABREPROT", xmlIN));

            out = AbreProtocoloOutTODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getAbreProtocoloOutTO();

        } catch (XmlException ex) {
            log.error("XmlException - ProtocoloFacadeImpl:setAbreValidaProtocolo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ProtocoloFacadeImpl:setAbreValidaProtocolo", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ProtocoloFacadeImpl:setAbreValidaProtocolo(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ProtocoloFacadeImpl:setAbreValidaProtocolo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return out;
    }

    /**
     * @common:operation
     */
    public FechaProtocoloOutTO setFechaProtocolo(String user, FechaProtocoloTO fechaProtocoloTO) throws TuxedoException, FacadeException {
        FechaProtocoloOutTO out = FechaProtocoloOutTO.Factory.newInstance();
        String xmlIN = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = fechaProtocoloTO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService(ConstantesCRM.TuxConnector, XmlManager.MakeXmlIn(user, "ATDFECHAPROT", xmlIN));

            out = FechaProtocoloOutTODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getFechaProtocoloOutTO();

        } catch (XmlException ex) {
            log.error("XmlException - ProtocoloFacadeImpl:setFechaProtocolo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ProtocoloFacadeImpl:setFechaProtocolo", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ProtocoloFacadeImpl:setFechaProtocolo(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ProtocoloFacadeImpl:setFechaProtocolo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return out;
    }

    /**
     * @common:operation
     */
    public GetDadosProtocoloOutTO getDadosProtocolo(String user, String nrTelefone, String nrProtocolo, String idSistema) throws TuxedoException, FacadeException {
        GetDadosProtocoloOutTO out = null;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            StringBuffer xml = new StringBuffer("<GetDadosProtocoloTO>");
            xml.append("<nrProtocolo>").append(nrProtocolo).append("</nrProtocolo>");
            xml.append("<idSistemaOrigem>").append(idSistema).append("</idSistemaOrigem>");
            
            if (nrTelefone != null && !"".equals(nrTelefone)) {
                xml.append("<cdAreaRegistro>").append(nrTelefone.substring(0, 2)).append("</cdAreaRegistro>");
                xml.append("<nrTelefone>").append(nrTelefone.substring(2)).append("</nrTelefone>");
            }
            
            xml.append("</GetDadosProtocoloTO>");

            xmlOUT = tuxedo.callService(ConstantesCRM.TuxConnector, XmlManager.MakeXmlIn(user, "ATDGETDADOPRO", xml.toString()));

            out = GetDadosProtocoloOutTODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getGetDadosProtocoloOutTO();

        } catch (XmlException ex) {
            log.error("XmlException - ProtocoloFacadeImpl:getDadosProtocolo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ProtocoloFacadeImpl:getDadosProtocolo", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ProtocoloFacadeImpl:getDadosProtocolo(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ProtocoloFacadeImpl:getDadosProtocolo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return out;
    }
}