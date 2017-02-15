package br.com.vivo.fo.ctrls.campanha.agendaContato;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.MsgDocument;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.xmlbeans.XmlException;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.campanha.vo.ListaAgendamentoCampanhaVODocument;
import br.com.vivo.fo.campanha.vo.ListaAgendamentoCampanhaVODocument.ListaAgendamentoCampanhaVO;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "AgendaContatoCmpFacade", mappedName = "AgendaContatoCmpFacade")
@Local(AgendaContatoCmpFacade.class)
@Session(ejbName = "AgendaContatoCmpFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AgendaContatoCmpFacadeImpl implements AgendaContatoCmpFacade {

    @EJB
    private TuxedoServiceCall       tuxedo;

    private static transient Logger log              = new Logger("campanha");

    /**
     * @common:operation
     */
    public ListaAgendamentoCampanhaVO getAgendaContato(String user, String parametro) throws FacadeException {
        String xmlOut;
        try {
            StringBuffer xmlInBuffer = new StringBuffer();
            xmlInBuffer.append("<idPessoaUsuario>");
            xmlInBuffer.append(StringEscapeUtils.escapeXml(parametro));
            xmlInBuffer.append("</idPessoaUsuario>");

            String xmlIn = XmlManager.MakeXmlIn(user, "GETCMPAGENDA", xmlInBuffer.toString());
            xmlOut = "";// campanhaTux.GETSERVICE(xmlIn);
            xmlOut = tuxedo.callService("TuxConnector", xmlIn);
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

            return ListaAgendamentoCampanhaVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaAgendamentoCampanhaVO();

        } catch (XmlException xex) {
            log.error("XmlException - AgendaContatoCmpFacadeImpl:getAgendaContato(" + user + ", " + parametro + ") - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AgendaContatoCmpFacadeImpl:getAgendaContato", xex));

        } catch (Exception ex) {
            log.error("Exception - AgendaContatoCmpFacadeImpl:getAgendaContato(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void addAgendarContato(String user, String[] dados) throws FacadeException {
        log.debug("AgendaContatoCmpFacadeImpl:addAgendarContato" + "( " + user + " )");
        try {
            StringBuffer xmlInBuffer = new StringBuffer();
            xmlInBuffer.append("<idPessoaUsuario>").append(dados[0]).append("</idPessoaUsuario>");
            xmlInBuffer.append("<dtAgendamento>").append(StringEscapeUtils.escapeXml(dados[1])).append("</dtAgendamento>");
            xmlInBuffer.append("<idAtendimentoCampanha>").append(dados[2]).append("</idAtendimentoCampanha>");
            xmlInBuffer.append("<nrTelefoneContato>").append(StringEscapeUtils.escapeXml(dados[3])).append("</nrTelefoneContato>");
            xmlInBuffer.append("<dsObservacaoAgenda>").append(StringEscapeUtils.escapeXml(dados[4])).append("</dsObservacaoAgenda>");
            xmlInBuffer.append("<nmPessoaContato>").append(StringEscapeUtils.escapeXml(dados[5])).append("</nmPessoaContato>");
            xmlInBuffer.append("<inAgendaCumprida>").append(dados[6]).append("</inAgendaCumprida>");
            xmlInBuffer.append("<idStatus>").append(dados[7]).append("</idStatus>");
            xmlInBuffer.append("<idMotivo>").append(dados[8]).append("</idMotivo>");
            xmlInBuffer.append("<idSubMotivo>").append(dados[9]).append("</idSubMotivo>");
            tuxedo.callService("TuxConnector", xmlInBuffer.toString());

            // TODO
            String xmlIn = XmlManager.MakeXmlIn(user, "INSCMPAG", xmlInBuffer.toString());

            // String xmlOut = (new ControlTuxedoCall()).execute(this, campanhaTux, "GETSERVICE", xmlIn);
            // campanhaTux.GETSERVICE( xmlIn );
            tuxedo.callService("TuxConnector", xmlIn);
            
        } catch (Exception ex) {
            log.error("Exception - AgendaContatoCmpFacadeImpl:addAgendarContato(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
