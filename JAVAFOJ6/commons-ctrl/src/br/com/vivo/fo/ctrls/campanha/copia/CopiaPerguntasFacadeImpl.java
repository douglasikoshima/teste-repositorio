package br.com.vivo.fo.ctrls.campanha.copia;

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
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "CopiaPerguntasFacade", mappedName = "CopiaPerguntasFacade")
@Local(CopiaPerguntasFacade.class)
@Session(ejbName = "CopiaPerguntasFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class CopiaPerguntasFacadeImpl implements CopiaPerguntasFacade {

    @EJB
    private TuxedoServiceCall             tuxedo;

    private final transient static Logger log = new Logger("campanha");

    /************************************************** ADD COPIA DE PERGUNTAS ****************************************************************************/
    /**
     * @common:operation
     */
    public RetornoVO addCopia(String user, int idCanalOrigem, int idCanalDestino) throws FacadeException, XmlException, TuxedoException {
        try {

            // Monta XML IN de acordo com o serviço a ser executado
            StringBuffer xmlInBuffer = new StringBuffer();
            xmlInBuffer.append("<idCanalCampanha>").append(idCanalOrigem).append("</idCanalCampanha>");
            xmlInBuffer.append("<idCanalCampanhaDest>").append(idCanalDestino).append("</idCanalCampanhaDest>");

            String xmlIN = XmlManager.MakeXmlIn(user, "INSCMPQUEST", xmlInBuffer.toString());

            String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return RetornoVODocument.Factory.parse(xmlOUT).getRetornoVO();

        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - CopiaPerguntasFacadeImpl:addCopia(" + user + ", " + idCanalOrigem + ", " + idCanalDestino + ") - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - CopiaPerguntasFacadeImpl:addCopia(" + user + ", " + idCanalOrigem + ", " + idCanalDestino + ") - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: CopiaPerguntasFacadeImpl:addCopia", xex));

        } catch (Exception ex) {
            log.error("Exception - CopiaPerguntasFacadeImpl:addCopia(" + user + ", " + idCanalOrigem + ", " + idCanalDestino + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
