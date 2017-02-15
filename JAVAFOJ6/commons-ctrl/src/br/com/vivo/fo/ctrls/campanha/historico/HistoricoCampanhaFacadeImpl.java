package br.com.vivo.fo.ctrls.campanha.historico;

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
import br.com.vivo.fo.campanha.vo.ItemArvoreVODocument;
import br.com.vivo.fo.campanha.vo.ItemArvoreVODocument.ItemArvoreVO;
import br.com.vivo.fo.campanha.vo.ListaCampanhaHistoricoVODocument;
import br.com.vivo.fo.campanha.vo.ListaCampanhaHistoricoVODocument.ListaCampanhaHistoricoVO;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "HistoricoCampanhaFacade", mappedName = "HistoricoCampanhaFacade")
@Local(HistoricoCampanhaFacade.class)
@Session(ejbName = "HistoricoCampanhaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class HistoricoCampanhaFacadeImpl implements HistoricoCampanhaFacade {

    @EJB
    private TuxedoServiceCall             tuxedo;

    private final transient static Logger log = new Logger("campanha");

    /**
     * @common:operation
     */
    public ListaCampanhaHistoricoVO getListaCampanhaHistorico(String user, String[] dados) throws TuxedoException, FacadeException {
        try {

            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idPessoaDePara>");
            xmlINBuffer.append(dados[0]);
            xmlINBuffer.append("</idPessoaDePara><dtInicio>");
            xmlINBuffer.append(dados[1]);
            xmlINBuffer.append("</dtInicio>");
            xmlINBuffer.append("<dtTermino>");
            xmlINBuffer.append(dados[2]);
            xmlINBuffer.append("</dtTermino>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETCMPHISTORI", xmlINBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return ListaCampanhaHistoricoVODocument.Factory.parse(xmlOUT).getListaCampanhaHistoricoVO();

        } catch (XmlException ex) {
            log.error("XmlException - HistoricoCampanhaFacadeImpl:getListaCampanhaHistorico(" + user + ", " + dados[0] + ", " + dados[1] + "," + dados[2] + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: HistoricoCampanhaFacadeImpl:getListaCampanhaHistorico", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - HistoricoCampanhaFacadeImpl:getListaCampanhaHistorico(" + user + ", " + dados[0] + ", " + dados[1] + "," + dados[2] + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - HistoricoCampanhaFacadeImpl:getListaCampanhaHistorico(" + user + ", " + dados[0] + ", " + dados[1] + "," + dados[2] + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /*************************************** ARVORE CAMPANHA ****************************************************************************/

    /**
     * @common:operation
     */
    public ItemArvoreVO getArvoreHistorico(String user, String idAtendimentoCampanha) throws TuxedoException, FacadeException {
        try {

            StringBuffer xmlINBuffer = new StringBuffer();
            xmlINBuffer.append("<idAtendimentoCampanha>");
            xmlINBuffer.append(idAtendimentoCampanha);
            xmlINBuffer.append("</idAtendimentoCampanha>");

            String xmlIN = XmlManager.MakeXmlIn(user, "GETQUESTRESP", xmlINBuffer.toString());
            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return ItemArvoreVODocument.Factory.parse(xmlOUT).getItemArvoreVO();

        } catch (XmlException ex) {
            log.error("XmlException - HistoricoCampanhaFacadeImpl:getArvoreHistorico(" + user + ", " + idAtendimentoCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: HistoricoCampanhaFacadeImpl:getArvoreHistorico", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - HistoricoCampanhaFacadeImpl:getArvoreHistorico(" + user + ", " + idAtendimentoCampanha + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - HistoricoCampanhaFacadeImpl:getArvoreHistorico(" + user + ", " + idAtendimentoCampanha + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
