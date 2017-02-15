package br.com.vivo.fo.ctrls.fidelizacao.AgendamentoContato;

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
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralDescricaoVODocument.FidelizacaoListaGeralDescricaoVO;
import br.com.vivo.fo.fidelizacao.vo.ListaHistoricoAgendamentoVODocument;
import br.com.vivo.fo.fidelizacao.vo.ListaHistoricoAgendamentoVODocument.ListaHistoricoAgendamentoVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "AgendamentoContatoFacade", mappedName = "AgendamentoContatoFacade")
@Local(AgendamentoContatoFacade.class)
@Session(ejbName = "AgendamentoContatoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AgendamentoContatoFacadeImpl implements AgendamentoContatoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log              = new Logger("fidelizacao");

    /**
     * @common:operation
     */
    public RetornoVO agendarContato(String user, String[] dados, String[] ofertasReal, String ofertasAceita) throws FacadeException, TuxedoException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        log.debug("AgendamentoContatoFacadeImpl:agendarContatos" + "( " + user + " )");
        try {
            /****************** Modificado por Décio Jr 06/10/2004 ***********************************************/
            /****************** Retirar DDD do numero da linha ****************************************************/
            String newLinha = dados[6];
            if ((newLinha != null) && (!ConstantesCRM.SVAZIO.equals(newLinha)) && (newLinha.length() >= 10)) {
                newLinha = newLinha.substring(2, newLinha.length());
            }
            /****************** Fim Modificado por Decio ***********************************************/

            // MONTAGEM DO BOBY DO XML DE ENTRADA
            xmlIN = "<idPessoaDePara>" + dados[0] + "</idPessoaDePara><idRespostaIntencao>" + dados[1] + "</idRespostaIntencao>";
            xmlIN += "<idRespostaDestino>" + dados[2] + "</idRespostaDestino><idTipoEncerramento>" + dados[3] + "</idTipoEncerramento>";
            xmlIN += "<dsObservacao>" + StringEscapeUtils.escapeXml(dados[4]) + "</dsObservacao><idLinhaTelefonica>" + dados[5] + "</idLinhaTelefonica>";
            /****************** Modificado por Décio Jr 06/10/2004 ***********************************************/
            // xmlIn += "<nrLinha>"+dados[6]+"</nrLinha>";
            xmlIN += "<nrLinha>" + StringEscapeUtils.escapeXml(newLinha) + "</nrLinha>";
            /****************** Fim Modificado por Decio ********************************************************/
            xmlIN += "<nomeContato>" + StringEscapeUtils.escapeXml(dados[7]) + "</nomeContato>";
            xmlIN += "<telefone>" + StringEscapeUtils.escapeXml(dados[8]) + "</telefone>";
            xmlIN += "<dtAgendamento>" + dados[9] + "</dtAgendamento>";
            xmlIN += "<comentario>" + StringEscapeUtils.escapeXml(dados[10]) + "</comentario>";

            /*************** Modificado por Décio JR 01/10/2004 ********************************************************************************/
            /********************** Enviar Ofertas Realizadas e ofertas Aceitas para agendamento ***********************************************/

            /** Ofertas Realizadas **/

            if (ofertasReal != null) {
                if (ofertasReal.length > 0) {
                    xmlIN += "<ofertasRealizadas>";
                    for (int i = 0; i < ofertasReal.length; i++) {
                        xmlIN += "<id>" + ofertasReal[i] + "</id>";
                        xmlIN += "<comentOferta>null</comentOferta>";
                    }
                    xmlIN += "<id>" + ofertasAceita + "</id><comentOferta>null</comentOferta>";
                    xmlIN += "</ofertasRealizadas>";
                } else {
                    // Se não tiver nenhuma oferta realizada, eu gravo a
                    // realizada e aceita com mesmo id
                    xmlIN += "<ofertasRealizadas><id>" + ofertasAceita + "</id><comentOferta>null</comentOferta></ofertasRealizadas>";

                }
            }

            /** Ofertas Aceitas **/
            xmlIN += "<ofertaAceita>";
            xmlIN += "<idOfertaAceita>" + ofertasAceita + "</idOfertaAceita>";
            xmlIN += "<caractOfertasAceitas>";
            xmlIN += "<nome>Caracteristicas</nome><valor>'Não informadas'</valor>";
            xmlIN += "</caractOfertasAceitas>";
            xmlIN += "</ofertaAceita>";

            /*************** Fim Modificação *****************************************************************************************************/

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "FINALRETENCAO", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - AgendamentoContatoFacadeImpl:agendarContatos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AgendamentoContatoFacadeImpl:agendarContatos", ex));

        } catch (Exception ex) {
            log.error("Exception - AgendamentoContatoFacadeImpl:agendarContatos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }

    }

    /**
     * @common:operation
     */
    public RetornoVO agendarContato2(String user, String[] dados, FidelizacaoListaGeralDescricaoVO ofertasReal, String ofertasAceita) throws FacadeException, TuxedoException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        log.debug("AgendamentoContatoFacadeImpl:agendarContatos" + "( " + user + " )");
        try {
            /****************** Modificado por Décio Jr 06/10/2004 ***********************************************/
            /****************** Retirar DDD do numero da linha ****************************************************/
            String newLinha = dados[6];
            if ((newLinha != null) && (!ConstantesCRM.SVAZIO.equals(newLinha)) && (newLinha.length() >= 10)) {
                newLinha = newLinha.substring(2, newLinha.length());
            }
            /****************** Fim Modificado por Decio ***********************************************/
            // MONTAGEM DO BOBY DO XML DE ENTRADA
            xmlIN = "<idPessoaDePara>" + dados[0] + "</idPessoaDePara><idRespostaIntencao>" + dados[1] + "</idRespostaIntencao>";
            xmlIN += "<idRespostaDestino>" + dados[2] + "</idRespostaDestino><idTipoEncerramento>" + dados[3] + "</idTipoEncerramento>";
            xmlIN += "<dsObservacao>" + StringEscapeUtils.escapeXml(dados[4]) + "</dsObservacao><idLinhaTelefonica>" + dados[5] + "</idLinhaTelefonica>";
            /****************** Modificado por Décio Jr 06/10/2004 ***********************************************/
            // xmlIn += "<nrLinha>"+dados[6]+"</nrLinha>";
            xmlIN += "<nrLinha>" + StringEscapeUtils.escapeXml(newLinha) + "</nrLinha>";
            /****************** Fim Modificado por Decio ********************************************************/
            xmlIN += "<nomeContato>" + StringEscapeUtils.escapeXml(dados[7]) + "</nomeContato>";
            xmlIN += "<telefone>" + StringEscapeUtils.escapeXml(dados[8]) + "</telefone>";
            xmlIN += "<dtAgendamento>" + dados[9] + "</dtAgendamento>";
            xmlIN += "<comentario>" + StringEscapeUtils.escapeXml(dados[10]) + "</comentario>";
            xmlIN += "<idGrupo>" + dados[11] + "</idGrupo>";

            /*************** Modificado por Décio JR 01/10/2004 ********************************************************************************/
            /********************** Enviar Ofertas Realizadas e ofertas Aceitas para agendamento ***********************************************/

            /** Ofertas Realizadas **/
            if (ofertasReal != null && ofertasReal.sizeOfItemListaDescricaoVOArray() > 0) {
                xmlIN += "<ofertasRealizadas>";
                for (int i = 0; i < ofertasReal.sizeOfItemListaDescricaoVOArray(); i++) {
                    xmlIN += "<id>" + ofertasReal.getItemListaDescricaoVOArray(i).getId() + "</id>";
                    xmlIN += "<comentOferta>null</comentOferta>";
                }
                xmlIN += "<id>" + ofertasAceita + "</id><comentOferta>null</comentOferta>";
                xmlIN += "</ofertasRealizadas>";
            } else {
                // Se não tiver nenhuma oferta realizada, eu gravo a realizada e
                // aceita com mesmo id
                xmlIN += "<ofertasRealizadas><id>" + ofertasAceita + "</id><comentOferta>null</comentOferta></ofertasRealizadas>";
            }

            /** Ofertas Aceitas **/
            xmlIN += "<ofertaAceita>";
            xmlIN += "<idOfertaAceita>" + ofertasAceita + "</idOfertaAceita>";
            xmlIN += "<caractOfertasAceitas>";
            xmlIN += "<nome>0</nome><valor>'Não informadas'</valor>";
            xmlIN += "</caractOfertasAceitas>";
            xmlIN += "</ofertaAceita>";

            /*************** Fim Modificação *****************************************************************************************************/
            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "FINALRETENCAO", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - AgendamentoContatoFacadeImpl:agendarContatos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AgendamentoContatoFacadeImpl:agendarContatos", ex));

        } catch (Exception ex) {
            log.error("Exception - AgendamentoContatoFacadeImpl:agendarContatos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public ListaHistoricoAgendamentoVO getHistoricoAgendamento(String user, String parametro) throws TuxedoException, FacadeException {
        ListaHistoricoAgendamentoVO lista = null;
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "SELAGCONTATO", "<idLinha>" + parametro + "</idLinha>");

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            lista = ListaHistoricoAgendamentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getListaHistoricoAgendamentoVO();
        
        } catch (TuxedoServiceCallerException tex) {
            log.error("TuxedoException - AgendamentoContatoFacadeImpl:getHistoricoAgendamento(" + user + ", " + parametro + ") - [" + tex.getMessage() + "]");
            throw new TuxedoException(tex);

        } catch (XmlException xex) {
            log.error("XmlException - AgendamentoContatoFacadeImpl:getHistoricoAgendamento(" + user + ", " + parametro + ") - [" + xex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AgendamentoContatoFacadeImpl:getHistoricoAgendamento", xex));

        } catch (Exception ex) {
            log.error("Exception - AgendamentoContatoFacadeImpl:getHistoricoAgendamento(" + user + ", " + parametro + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return lista;
    }
}
