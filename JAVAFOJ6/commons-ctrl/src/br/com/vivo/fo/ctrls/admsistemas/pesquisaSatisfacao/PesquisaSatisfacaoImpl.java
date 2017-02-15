package br.com.vivo.fo.ctrls.admsistemas.pesquisaSatisfacao;

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
import br.com.vivo.fo.admsistemas.vo.AdmIndicadorSequenciaVODocument.AdmIndicadorSequenciaVO;
import br.com.vivo.fo.admsistemas.vo.AdmSatisfacaoContainerVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmSatisfacaoContainerVODocument.AdmSatisfacaoContainerVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "PesquisaSatisfacao", mappedName = "PesquisaSatisfacao")
@Local(PesquisaSatisfacao.class)
@Session(ejbName = "PesquisaSatisfacao", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class PesquisaSatisfacaoImpl implements PesquisaSatisfacao {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("admsistemas");
    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public AdmSatisfacaoContainerVO listaPerguntaResposta(AdmSatisfacaoContainerVO dados, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("PesquisaSatisfacaoImpl:listaPerguntaResposta(" + user + ")");

            xmlIN = dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "PerListar", xmlIN);

            // xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

            AdmSatisfacaoContainerVODocument admSatisfacaoContainerVODocument = AdmSatisfacaoContainerVODocument.Factory.parse(xmlOUT);

            return admSatisfacaoContainerVODocument.getAdmSatisfacaoContainerVO();

        } catch (XmlException ex) {
            log.error("XmlException - PesquisaSatisfacaoImpl:listaPerguntaResposta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PesquisaSatisfacaoImpl:listaPerguntaResposta", ex));

        } catch (Exception ex) {
            log.error("Exception - PesquisaSatisfacaoImpl:listaPerguntaResposta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void salvaPergunta(AdmSatisfacaoContainerVO dados, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("PesquisaSatisfacaoImpl:salvaPergunta(" + user + ")");

            xmlIN = dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "PerIncluir", xmlIN);

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - PesquisaSatisfacaoImpl:salvaPergunta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PesquisaSatisfacaoImpl:salvaPergunta", ex));

        } catch (Exception ex) {
            log.error("Exception - PesquisaSatisfacaoImpl:salvaPergunta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void salvaResposta(AdmSatisfacaoContainerVO dados, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("PesquisaSatisfacaoImpl:salvaResposta(" + user + ")");

            xmlIN = dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ResIncluir", xmlIN);

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - PesquisaSatisfacaoImpl:salvaResposta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PesquisaSatisfacaoImpl:salvaResposta", ex));

        } catch (Exception ex) {
            log.error("Exception - PesquisaSatisfacaoImpl:salvaResposta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void excluirPergunta(AdmSatisfacaoContainerVO dados, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("PesquisaSatisfacaoImpl:excluirPergunta(" + user + ")");

            xmlIN = dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "PerRemove", xmlIN);

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - PesquisaSatisfacaoImpl:excluirPergunta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PesquisaSatisfacaoImpl:excluirPergunta", ex));

        } catch (Exception ex) {
            log.error("Exception - PesquisaSatisfacaoImpl:excluirPergunta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void excluirResposta(AdmSatisfacaoContainerVO dados, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("PesquisaSatisfacaoImpl:excluirResposta(" + user + ")");

            xmlIN = dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ResRemove", xmlIN);

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - PesquisaSatisfacaoImpl:excluirResposta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PesquisaSatisfacaoImpl:excluirResposta", ex));

        } catch (Exception ex) {
            log.error("Exception - PesquisaSatisfacaoImpl:excluirResposta(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void salvaPerguntaExistente(AdmSatisfacaoContainerVO dados, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("PesquisaSatisfacaoImpl:salvaPerguntaExistente(" + user + ")");

            xmlIN = dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "PerEditar", xmlIN);

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - PesquisaSatisfacaoImpl:salvaPerguntaExistente(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PesquisaSatisfacaoImpl:salvaPerguntaExistente", ex));

        } catch (Exception ex) {
            log.error("Exception - PesquisaSatisfacaoImpl:salvaPerguntaExistente(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void salvaRespostaExistente(AdmSatisfacaoContainerVO dados, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("PesquisaSatisfacaoImpl:salvaRespostaExistente(" + user + ")");

            xmlIN = dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ResEditar", xmlIN);

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - PesquisaSatisfacaoImpl:salvaRespostaExistente(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PesquisaSatisfacaoImpl:salvaRespostaExistente", ex));

        } catch (Exception ex) {
            log.error("Exception - PesquisaSatisfacaoImpl:salvaRespostaExistente(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void salvaSalto(AdmSatisfacaoContainerVO dados, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("PesquisaSatisfacaoImpl:salvaSalto(" + user + ")");

            xmlIN = dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "ResEditar", xmlIN);

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - PesquisaSatisfacaoImpl:salvaSalto(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PesquisaSatisfacaoImpl:salvaSalto", ex));

        } catch (Exception ex) {
            log.error("Exception - PesquisaSatisfacaoImpl:salvaSalto(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void perguntaUpDown(AdmIndicadorSequenciaVO dados, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("PesquisaSatisfacaoImpl:perguntaUpDown(" + user + ")");

            xmlIN = dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "PERMOVER", xmlIN);

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - PesquisaSatisfacaoImpl:perguntaUpDown(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PesquisaSatisfacaoImpl:perguntaUpDown", ex));

        } catch (Exception ex) {
            log.error("Exception - PesquisaSatisfacaoImpl:perguntaUpDown(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void respostaUpDown(AdmIndicadorSequenciaVO dados, String user) throws TuxedoException, FacadeException {
        try {
            log.debug("PesquisaSatisfacaoImpl:respostaUpDown(" + user + ")");

            xmlIN = dados.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
            xmlIN = XmlManager.MakeXmlIn(user, "RESMOVER", xmlIN);

            // (new ControlTuxedoCall()).execute(this, admSistemasTux,
            // "GETSERVICE", xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return;

        } catch (XmlException ex) {
            log.error("XmlException - PesquisaSatisfacaoImpl:respostaUpDown(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: PesquisaSatisfacaoImpl:respostaUpDown", ex));

        } catch (Exception ex) {
            log.error("Exception - PesquisaSatisfacaoImpl:respostaUpDown(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
