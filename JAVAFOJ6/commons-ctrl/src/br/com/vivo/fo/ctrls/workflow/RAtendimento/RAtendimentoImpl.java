package br.com.vivo.fo.ctrls.workflow.RAtendimento;

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
import br.com.vivo.fo.admsistemas.vo.EncerramentoVODocument;
import br.com.vivo.fo.admsistemas.vo.EncerramentoVODocument.EncerramentoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioProcessoVODocument;
import br.com.vivo.fo.admsistemas.vo.FormularioProcessoVODocument.FormularioProcessoVO;
import br.com.vivo.fo.admsistemas.vo.FormularioVODocument;
import br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.cliente.vo.GrupoCRIVODocument;
import br.com.vivo.fo.cliente.vo.GrupoCRIVODocument.GrupoCRIVO;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument;
import br.com.vivo.fo.workflow.vo.FaseVODocument.FaseVO;
import br.com.vivo.fo.workflow.vo.PessoaVODocument;
import br.com.vivo.fo.workflow.vo.PessoaVODocument.PessoaVO;
import br.com.vivo.fo.workflow.vo.RDContatoVODocument;
import br.com.vivo.fo.workflow.vo.RDContatoVODocument.RDContatoVO;
import br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument;
import br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO;
import br.com.vivo.fo.workflow.vo.RWFAtendimentosVODocument;
import br.com.vivo.fo.workflow.vo.RWFAtendimentosVODocument.RWFAtendimentosVO;
import br.com.vivo.fo.workflow.vo.RWFInBoxPesquisaVODocument.RWFInBoxPesquisaVO;
import br.com.vivo.fo.workflow.vo.RWFInboxUserVODocument;
import br.com.vivo.fo.workflow.vo.RWFInboxUserVODocument.RWFInboxUserVO;
import br.com.vivo.fo.workflow.vo.StringComumVODocument;
import br.com.vivo.fo.workflow.vo.StringComumVODocument.StringComumVO;
import br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO;
import br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO;
import br.com.vivo.fo.workflow.vo.WFAtdNotasVODocument;
import br.com.vivo.fo.workflow.vo.WFAtdNotasVODocument.WFAtdNotasVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "RAtendimento", mappedName = "RAtendimento")
@Local(RAtendimento.class)
@Session(ejbName = "RAtendimento", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RAtendimentoImpl implements RAtendimento {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log = new Logger("workflow");

    /**
     * @common:operation
     */
    public RWFInboxUserVO getUsuarioCampanha(String user) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getUsuarioCampanha(" + user + ")");

            String inService = TuxedoServiceBridge.getXMLRequest(user, "CNSINFUSU", ConstantesCRM.SVAZIO);
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return RWFInboxUserVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRWFInboxUserVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getUsuarioCampanha(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getUsuarioCampanha", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getUsuarioCampanha(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public RWFAtendimentosVO getRWFAtendimentoVO(String user, RWFInBoxPesquisaVO pesquisa) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getUsuarioCampanha(" + user + ")");

            StringBuffer xmlIn = new StringBuffer(pesquisa.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO));
            String inService = TuxedoServiceBridge.getXMLRequest(user, "PSQINBOX", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return RWFAtendimentosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRWFAtendimentosVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getRWFAtendimentoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getRWFAtendimentoVO", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getRWFAtendimentoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public RWFAtendimentosVO getMsgInboxCRI(String user, RWFInBoxPesquisaVO pesquisa) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getInboxCRI(" + user + ")");

            StringBuffer xmlIn = new StringBuffer(pesquisa.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO));
            String inService = TuxedoServiceBridge.getXMLRequest(user, "PSQMSGCRI", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return RWFAtendimentosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRWFAtendimentosVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getInboxCRI(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getInboxCRI", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getInboxCRI(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public WFAtdNotasVO getWFAtdNotasVO(String user, WFAtdNotaVO pesquisa) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getWFAtdNotasVO(" + user + ")");

            StringBuffer xmlIn = new StringBuffer(pesquisa.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO));
            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDPSQNOTAS", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAtdNotasVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAtdNotasVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getWFAtdNotasVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getWFAtdNotasVO", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getWFAtdNotasVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public WFAtdNotasVO getWFMotivosVO(String user, String pesquisa) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getWFMotivosVO(" + user + ")");

            StringBuffer xmlIn = new StringBuffer(pesquisa);
            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDGETMOTIVOS", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAtdNotasVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAtdNotasVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getWFMotivosVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getWFMotivosVO", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getWFMotivosVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public WFAtdNotasVO getOperacaoNota(String user) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getOperacaoNota(" + user + ")");

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDOPERNOTA", ConstantesCRM.SVAZIO);
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return WFAtdNotasVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAtdNotasVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getOperacaoNota(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getOperacaoNota", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getOperacaoNota(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public RWFAtendimentosVO getInboxCRI(String user, RWFInBoxPesquisaVO pesquisa) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getInboxCRI(" + user + ")");

            StringBuffer xmlIn = new StringBuffer(pesquisa.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO));
            String inService = TuxedoServiceBridge.getXMLRequest(user, "PSQPROCCRI", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return RWFAtendimentosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRWFAtendimentosVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getInboxCRI(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getInboxCRI", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getInboxCRI(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public RDContatoVO getDadosContato(String user, String idAtendimento) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getDadosContato(" + user + ")");

            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETDDCTO", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return RDContatoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRDContatoVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getDadosContato(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getDadosContato", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getDadosContato(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public RDContatoVO getDadosContatoEx(String user, String idAtendimento) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getDadosContatoEx(" + user + ")");

            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETDDCTOEX", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return RDContatoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRDContatoVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getDadosContatoEx(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getDadosContatoEx", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getDadosContatoEx(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public StringComumVO getComentarioHistorico(String user, String idAndamento) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getComentarioHistorico(" + user + ")");

            StringBuffer xmlIn = new StringBuffer("<idAndamento>").append(idAndamento).append("</idAndamento>");
            String inService = TuxedoServiceBridge.getXMLRequest(user, "ANDAOBSERVACAO", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return StringComumVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getStringComumVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getComentarioHistorico(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getComentarioHistorico", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getComentarioHistorico(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public StringComumVO getDescricaoArvoreAtendimento(String user, String idAtendimento) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getDescricaoArvoreAtendimento(" + user + ")");

            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETDSARV", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return StringComumVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getStringComumVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getDescricaoArvoreAtendimento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getDescricaoArvoreAtendimento", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getDescricaoArvoreAtendimento(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public StringComumVO getDescricaoArvoreAtendimentoEx(String user, String idAtendimento) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getDescricaoArvoreAtendimentoEx(" + user + ")");

            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETDSARVEX", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return StringComumVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getStringComumVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getDescricaoArvoreAtendimentoEx(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getDescricaoArvoreAtendimentoEx", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getDescricaoArvoreAtendimentoEx(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public PessoaVO getDadosPessoa(String user, String idAtendimento) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getDadosPessoa(" + user + ")");

            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            String xmlIN = XmlManager.MakeXmlIn(user, "ATDDETPESSOA", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return PessoaVODocument.Factory.parse(xmlOut).getPessoaVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getDadosPessoa(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getDadosPessoa", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getDadosPessoa(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public PessoaVO getDadosPessoaEx(String user, String idAtendimento) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getDadosPessoaEx(" + user + ")");

            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            String xmlIN = XmlManager.MakeXmlIn(user, "ATDDETPESSOAEX", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return PessoaVODocument.Factory.parse(xmlOut).getPessoaVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getDadosPessoaEx(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getDadosPessoaEx", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getDadosPessoaEx(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public FaseVO[] getFasesNiveisAtendimento(String user, String idAtendimento) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getFasesNiveisAtendimento(" + user + ")");

            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETFSSEN", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return AtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoVO().getFaseVOArray();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getFasesNiveisAtendimento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getFasesNiveisAtendimento", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getFasesNiveisAtendimento(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public FaseVO[] getFasesNiveisAtendimentoEx(String user, String idAtendimento) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getFasesNiveisAtendimentoEx(" + user + ")");

            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETFSSENEX", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return AtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoVO().getFaseVOArray();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getFasesNiveisAtendimentoEx(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getFasesNiveisAtendimentoEx", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getFasesNiveisAtendimentoEx(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoVO[] getWFAcaoAbaRelacionamentoVO(String user, String idAtendimento, String idGrupo, String inCRI, String inRC, String inSupervisor) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getWFAcaoVO(" + user + ")");

            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            if ((idGrupo != null) && (!(idGrupo.equals(ConstantesCRM.SVAZIO)))) {
                xmlIn.append("<idGrupo>").append(idGrupo).append("</idGrupo>");
            }

            xmlIn.append("<inCRI>").append(inCRI).append("</inCRI>");
            xmlIn.append("<inRC>").append(inRC).append("</inRC>");
            xmlIn.append("<inFO>0</inFO>");

            if ((inRC != null && (inRC.equals(ConstantesCRM.SZERO) || inRC.equals(ConstantesCRM.SONE))) && (inCRI != null && inCRI.equals(ConstantesCRM.SZERO)) && (inSupervisor != null && inSupervisor.equals(ConstantesCRM.SZERO))) {
            } else {
                xmlIn.append("<inSupervisor>").append(inSupervisor).append("</inSupervisor>");
            }

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETACAO", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return AtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoVO().getWFAcaoVOArray();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getWFAcaoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getWFAcaoVO", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getWFAcaoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoVO[] getWFAcaoAbaRelacionamentoVOEx(String user, String idAtendimento, String idGrupo, String inCRI, String inRC, String inSupervisor) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getWFAcaoAbaRelacionamentoVOEx(" + user + ")");

            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            if ((idGrupo != null) && (!(idGrupo.equals(ConstantesCRM.SVAZIO)))) {
                xmlIn.append("<idGrupo>").append(idGrupo).append("</idGrupo>");
            }

            xmlIn.append("<inCRI>").append(inCRI).append("</inCRI>");
            xmlIn.append("<inRC>").append(inRC).append("</inRC>");
            xmlIn.append("<inFO>0</inFO>");

            if ((inRC != null && (inRC.equals(ConstantesCRM.SZERO) || inRC.equals(ConstantesCRM.SONE))) && (inCRI != null && inCRI.equals(ConstantesCRM.SZERO)) && (inSupervisor != null && inSupervisor.equals(ConstantesCRM.SZERO))) {
            } else {
                xmlIn.append("<inSupervisor>").append(inSupervisor).append("</inSupervisor>");
            }

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETACAOEX", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return AtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoVO().getWFAcaoVOArray();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getWFAcaoAbaRelacionamentoVOEx(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getWFAcaoAbaRelacionamentoVOEx", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getWFAcaoAbaRelacionamentoVOEx(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoVO[] getWFAcaoVO(String user, String idAtendimento, String idGrupo, String inCRI, String inRC, String inSupervisor) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getWFAcaoVO(" + user + ")");

            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            if ((idGrupo != null) && (!(idGrupo.equals(ConstantesCRM.SVAZIO)))) {
                xmlIn.append("<idGrupo>").append(idGrupo).append("</idGrupo>");
            }

            xmlIn.append("<inCRI>").append(inCRI).append("</inCRI>");
            xmlIn.append("<inRC>").append(inRC).append("</inRC>");

            if ((inRC != null && (inRC.equals(ConstantesCRM.SZERO) || inRC.equals(ConstantesCRM.SONE))) && (inCRI != null && inCRI.equals(ConstantesCRM.SZERO)) && (inSupervisor != null && inSupervisor.equals(ConstantesCRM.SZERO))) {
            } else {
                xmlIn.append("<inSupervisor>").append(inSupervisor).append("</inSupervisor>");
            }

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETACAO", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return AtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoVO().getWFAcaoVOArray();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getWFAcaoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getWFAcaoVO", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getWFAcaoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public WFAcaoVO[] getWFAcaoVOEx(String user, String idAtendimento, String idGrupo, String inCRI, String inRC, String inSupervisor) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getWFAcaoVOEx(" + user + ")");
            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");

            if ((idGrupo != null) && (!(idGrupo.equals(ConstantesCRM.SVAZIO)))) {
                xmlIn.append("<idGrupo>").append(idGrupo).append("</idGrupo>");
            }

            xmlIn.append("<inCRI>").append(inCRI).append("</inCRI>");
            xmlIn.append("<inRC>").append(inRC).append("</inRC>");

            if ((inRC != null && (inRC.equals(ConstantesCRM.SZERO) || inRC.equals(ConstantesCRM.SONE))) && (inCRI != null && inCRI.equals(ConstantesCRM.SZERO)) && (inSupervisor != null && inSupervisor.equals(ConstantesCRM.SZERO))) {
            } else {
                xmlIn.append("<inSupervisor>").append(inSupervisor).append("</inSupervisor>");
            }

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETACAOEX", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return AtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoVO().getWFAcaoVOArray();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getWFAcaoVOEx(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getWFAcaoVOEx", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getWFAcaoVOEx(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public FormularioVO getFormularioVO(String user, String idAtendimento) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getFormularioVO(" + user + ")");
            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETFRARV", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return FormularioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getFormularioVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getFormularioVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getFormularioVO", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getFormularioVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public FormularioVO getFormularioVOEx(String user, String idAtendimento) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getFormularioVOEx(" + user + ")");
            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETFRARVEX", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return FormularioVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getFormularioVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getFormularioVOEx(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getFormularioVOEx", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getFormularioVOEx(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public FormularioProcessoVO getFormularioProcessoVO(String user, String idAtendimento) throws TuxedoException, FacadeException {
        try {
            log.debug("RAtendimentoImpl:getFormularioProcessoVO(" + user + ")");
            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETFRARV", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return FormularioProcessoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getFormularioProcessoVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getFormularioProcessoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getFormularioProcessoVO", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getFormularioProcessoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public FormularioProcessoVO getFormularioProcessoVOEx(String user, String idAtendimento) throws TuxedoException, FacadeException {
        try {
            log.debug("RAtendimentoImpl:getFormularioProcessoVOEx(" + user + ")");
            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETFRARVEX", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return FormularioProcessoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getFormularioProcessoVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getFormularioProcessoVOEx(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getFormularioProcessoVOEx", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getFormularioProcessoVOEx(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public RWFAtendimentoVO getDetalheAtendimento(String user, String idAtendimento) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getDetalheAtendimento(" + user + ")");
            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            xmlIn.append("<inSupervisor>1</inSupervisor>");

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETATEND", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return RWFAtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRWFAtendimentoVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getDetalheAtendimento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getDetalheAtendimento", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getDetalheAtendimento(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public RWFAtendimentoVO getDetalheAtendimentoEx(String user, String idAtendimento, boolean inHistoricoMG) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getDetalheAtendimentoEx(" + user + ")");
            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");
            xmlIn.append("<inSupervisor>1</inSupervisor>");
            xmlIn.append("<inHistoricoMG>" + inHistoricoMG + "</inHistoricoMG>");

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETATENDEX", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return RWFAtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRWFAtendimentoVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getDetalheAtendimentoEx(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getDetalheAtendimentoEx", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getDetalheAtendimentoEx(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public EncerramentoVO getEncerramentoVO(String user, String idAtendimento) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getEncerramentoVO(" + user + ")");
            StringBuffer xmlIn = new StringBuffer("<idAtendimento>").append(idAtendimento).append("</idAtendimento>");

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDDETDDENC", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return EncerramentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getEncerramentoVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getEncerramentoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getEncerramentoVO", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getEncerramentoVO(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public void analistaDisponivel(String user, String inDisponvivel, int tipoInBox) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:analistaDisponivel(" + user + ")");
            StringBuffer xmlIn = new StringBuffer("<inDisponivelWF>").append(inDisponvivel).append("</inDisponivelWF>");
            switch (tipoInBox) {
            case 0:
                break;
            case 1:
                xmlIn.append("<inCRI>1</inCRI>");
                break;
            case 2:
                xmlIn.append("<inRC>1</inRC>");
                break;
            }

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDINBOXDISP", xmlIn.toString());
            tuxedo.callService("TuxConnector", inService);

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:analistaDisponivel(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:analistaDisponivel", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:analistaDisponivel(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }

    }

    /**
     * @common:operation
     */
    public void analistaDisponivel2(String user, String inDisponvivel, int tipoInBox) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:analistaDisponivel2(" + user + ")");

            StringBuffer xmlIn = new StringBuffer("<inDisponivelWF>").append(inDisponvivel).append("</inDisponivelWF>");
            switch (tipoInBox) {
            case 0:
                break;
            case 1:
                xmlIn.append("<inCRI>1</inCRI>");
                break;
            case 2:
                xmlIn.append("<inRC>1</inRC>");
                break;
            }

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDINBOXDISPO", xmlIn.toString());
            tuxedo.callService("TuxConnector", inService);

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:analistaDisponivel2(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:analistaDisponivel2", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:analistaDisponivel2(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public void analistaDisponivelRC(String user, String inDisponvivel) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:analistaDisponivelRC(" + user + ")");
            StringBuffer xmlIn = new StringBuffer("<inDisponivelWF>").append(inDisponvivel).append("</inDisponivelWF>").append("<inRC>1</inRC>");

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDINBOXDISP", xmlIn.toString());
            tuxedo.callService("TuxConnector", inService);

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:analistaDisponivelRC(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:analistaDisponivelRC", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:analistaDisponivelRC(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public void analistaDisponivelRC2(String user, String inDisponvivel) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:analistaDisponivelRC2(" + user + ")");
            StringBuffer xmlIn = new StringBuffer("<inDisponivelWF>").append(inDisponvivel).append("</inDisponivelWF>").append("<inRC>1</inRC>");

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDINBOXDISPO", xmlIn.toString());
            tuxedo.callService("TuxConnector", inService);

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:analistaDisponivelRC2(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:analistaDisponivelRC2", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:analistaDisponivelRC2(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public void obtemProximoProcesso(String user, String parametro) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:obtemProximoProcesso(" + user + ")");

            String inService = TuxedoServiceBridge.getXMLRequest(user, "ATDATTRUSUSEM", parametro);
            tuxedo.callService("TuxConnector", inService);

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:obtemProximoProcesso(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:obtemProximoProcesso", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:obtemProximoProcesso(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public RWFAtendimentosVO getInboxRC(String user, RWFInBoxPesquisaVO pesquisa) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getInboxRC(" + user + ")");
            StringBuffer xmlIn = new StringBuffer();
            xmlIn.append(pesquisa.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO));

            String inService = TuxedoServiceBridge.getXMLRequest(user, "PSQINBOXRC", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return RWFAtendimentosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRWFAtendimentosVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getInboxRC(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getInboxRC", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getInboxRC(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public RWFAtendimentosVO getMsgInboxRC(String user, RWFInBoxPesquisaVO pesquisa) throws FacadeException {
        try {
            log.debug("RAtendimentoImpl:getMsgInboxRC(" + user + ")");
            StringBuffer xmlIn = new StringBuffer();
            xmlIn.append(pesquisa.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO));

            String inService = TuxedoServiceBridge.getXMLRequest(user, "PSQMSGRC", xmlIn.toString());
            String xmlOut = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            return RWFAtendimentosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRWFAtendimentosVO();

        } catch (XmlException ex) {
            log.error("XmlException - RAtendimentoImpl:getMsgInboxRC(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: RAtendimentoImpl:getMsgInboxRC", ex));

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getMsgInboxRC(" + user + ") - [" + ex.getMessage() + "]");
            throw new FacadeException(ex);
        }
    }

    /**
     * @common:operation
     */
    public GrupoCRIVO getPesquisaGruposCRI(String user, GrupoCRIVO filtro) throws Exception {
        try {
            GrupoCRIVO envio = GrupoCRIVO.Factory.newInstance();
            envio.setPesquisa(filtro.getPesquisa());

            String xmlIn = envio.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
            xmlIn = XmlManager.MakeXmlIn(user, "PSQPROCCRI", xmlIn);
            String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            return GrupoCRIVODocument.Factory.parse(xmlOut).getGrupoCRIVO();

        } catch (Exception ex) {
            log.error("Exception - RAtendimentoImpl:getPesquisaGruposCRI(" + user + ") - [" + ex.getMessage() + "]");
            throw (new Exception(ex.getMessage()));
        }
    }
}
