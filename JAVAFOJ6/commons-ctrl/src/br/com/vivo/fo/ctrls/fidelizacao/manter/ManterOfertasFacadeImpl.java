package br.com.vivo.fo.ctrls.fidelizacao.manter;

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
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.fidelizacao.vo.GrupoApoioVODocument;
import br.com.vivo.fo.fidelizacao.vo.GrupoApoioVODocument.GrupoApoioVO;
import br.com.vivo.fo.fidelizacao.vo.ListaOfertaVODocument;
import br.com.vivo.fo.fidelizacao.vo.ListaOfertaVODocument.ListaOfertaVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ManterOfertasFacade", mappedName = "ManterOfertasFacade")
@Local(ManterOfertasFacade.class)
@Session(ejbName = "ManterOfertasFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterOfertasFacadeImpl implements ManterOfertasFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("fidelizacao");
    private String            xmlIn;
    private String            xmlOut;

    /**
     * @common:operation
     */
    public GrupoApoioVO getDadosIniciais(String user) throws TuxedoException, FacadeException {
        try {
            GrupoApoioVO apoio = null;
            xmlIn = ConstantesCRM.SVAZIO;
            xmlOut = ConstantesCRM.SVAZIO;
            // monta xml que servira de entrada para o servico tuxcedo
            xmlIn = "<getreg>GETAPOIO</getreg>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELOFERTA", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            apoio = GrupoApoioVODocument.Factory.parse(xmlOut).getGrupoApoioVO();

            return apoio;

        } catch (XmlException ex) {
            log.error("XmlException - ManterOfertasFacadeImpl:getDadosIniciais(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterOfertasFacadeImpl:getDadosIniciais", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterOfertasFacadeImpl:getDadosIniciais(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterOfertasFacadeImpl:getDadosIniciais(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public ListaOfertaVO getOfertas(String user, String parametro) throws TuxedoException, FacadeException {
        try {
            log.debug("AparelhoFacadeImpl:getAparleho(" + user + ")");
            ListaOfertaVO lista = null;

            // monta xml que servira de entrada para o servico tuxcedo
            xmlIn = "<texto>" + parametro + "</texto>";
            xmlIn = XmlManager.MakeXmlIn(user, "SELOFERTA", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
            xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

            lista = ListaOfertaVODocument.Factory.parse(xmlOut).getListaOfertaVO();

            return lista;

        } catch (XmlException ex) {
            log.error("XmlException - ManterOfertasFacadeImpl:getOfertas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterOfertasFacadeImpl:getOfertas", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterOfertasFacadeImpl:getOfertas(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - ManterOfertasFacadeImpl:getOfertas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void delOferta(String user, String id) throws FacadeException, TuxedoException {

        try {
            // Monta o log da operação se possível

            log.debug("FidelizacaoListaGeralFacadeImpl:dellBonus(" + user + ", " + id + ")");

            xmlIn = ConstantesCRM.SVAZIO;
            xmlOut = ConstantesCRM.SVAZIO;

            xmlIn = "<id>" + id + "</id>";
            xmlIn = XmlManager.MakeXmlIn(user, "DELOFERTA", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterOfertaFacadeImpl:delOferta(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (XmlException ex) {
            log.error("XmlException - ManterOfertaFacadeImpl:delOferta(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterOfertaFacadeImpl:delOferta", ex));
        } catch (Exception ex) {
            log.error("Exception - ManterOfertaFacadeImpl:delOferta(" + user + ", " + id + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public void setOferta(String user, String[] dados) throws TuxedoException, XmlException, FacadeException {
        try {

            xmlIn = "<id>" + dados[0] + "</id><descricao>" + dados[1] + "</descricao><idtiof>" + dados[2] + "</idtipof>";
            xmlIn = XmlManager.MakeXmlIn(user, "UPDOFERTA", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterOfertaFacadeImpl:setOferta(" + user + ", " + "[ " + dados[0] + "," + dados[1] + dados[2] + "] ) - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (XmlException ex) {
            log.error("XmlException - ManterOfertaFacadeImpl:setOferta(" + user + ", " + "[ " + dados[0] + "," + dados[1] + dados[2] + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterOfertaFacadeImpl:setOferta", ex));
        } catch (Exception ex) {
            log.error("Exception - ManterOfertaFacadeImpl:setOferta(" + user + ", " + "[ " + dados[0] + "," + dados[1] + dados[2] + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public void addOferta(String user, String[] dados) throws FacadeException, XmlException, TuxedoException {
        try {

            xmlIn = "<id>" + dados[0] + "</id><descricao>" + dados[1] + "</descricao>";
            xmlIn = XmlManager.MakeXmlIn(user, "INSOFERTA", xmlIn);

            // xmlOut = (new ControlTuxedoCall()).execute(this, fidelizaTux,
            // "GETSERVICE", xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - ManterOfertaFacadeImpl:addOferta(" + user + ", " + "[ " + dados[0] + " , " + dados[1] + "] ) - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);
        } catch (XmlException ex) {
            log.error("XmlException - ManterOfertaFacadeImpl:addOferta(" + user + ", " + "[ " + dados[0] + " , " + dados[1] + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ManterOfertaFacadeImpl:addOferta", ex));
        } catch (Exception ex) {
            log.error("Exception - ManterOfertaFacadeImpl:addOferta(" + user + ", " + "[ " + dados[0] + " , " + dados[1] + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }
}
