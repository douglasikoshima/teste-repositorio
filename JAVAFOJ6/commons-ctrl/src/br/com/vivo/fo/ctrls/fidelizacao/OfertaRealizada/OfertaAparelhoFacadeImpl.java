package br.com.vivo.fo.ctrls.fidelizacao.OfertaRealizada;

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
import br.com.vivo.fo.cliente.vo.EnderecoVODocument;
import br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument;
import br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralDescricaoVODocument.FidelizacaoListaGeralDescricaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.OfAparelhoParamVODocument.OfAparelhoParamVO;
import br.com.vivo.fo.fidelizacao.vo.OfertaAparelhoVODocument;
import br.com.vivo.fo.fidelizacao.vo.OfertaAparelhoVODocument.OfertaAparelhoVO;
import br.com.vivo.fo.fidelizacao.vo.RetencaoSAPVODocument.RetencaoSAPVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetencaoRetornoSAPVODocument;
import br.com.vivo.fo.retornotux.vo.RetencaoRetornoSAPVODocument.RetencaoRetornoSAPVO;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "OfertaAparelhoFacade", mappedName = "OfertaAparelhoFacade")
@Local(OfertaAparelhoFacade.class)
@Session(ejbName = "OfertaAparelhoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class OfertaAparelhoFacadeImpl implements OfertaAparelhoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log = new Logger("fidelizacao");

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getPercDesconto(String user, String idMatrizAparelho, String excecao, int idSegmentacao) throws FacadeException, TuxedoException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(
                    user, 
                    "SELDESCONTO",
                    new StringBuffer("<idMatrizAparelho>").append(idMatrizAparelho).append("</idMatrizAparelho>")
                             .append("<excecao>").append(excecao).append("</excecao>")
                             .append("<idSegmentacao>").append(idSegmentacao).append("</idSegmentacao>").toString());

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return FidelizacaoListaGeralVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getFidelizacaoListaGeralVO();
            
        } catch (XmlException ex) {
            log.error("XmlException - OfertaAparelhoFacadeImpl:getPercDesconto(" + user + "," + idMatrizAparelho + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaAparelhoFacadeImpl:getPercDesconto", ex));

        } catch (Exception ex) {
            log.error("Exception - OfertaAparelhoFacadeImpl:getPercDesconto(" + user + "," + idMatrizAparelho + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public OfertaAparelhoVO getAparelho(String user, OfAparelhoParamVO ofAparelhoParam) throws FacadeException, TuxedoException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = ofAparelhoParam.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO).replaceAll("\n", ConstantesCRM.SVAZIO).replaceAll("\r", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "GETOFAPARELHO", xmlIN));

            return OfertaAparelhoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getOfertaAparelhoVO();

        } catch (XmlException ex) {
            log.error("XmlException - OfertaAparelhoFacadeImpl:getAparelho(" + user + "," + ofAparelhoParam.xmlText() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaAparelhoFacadeImpl:getAparelho", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("OfertaAparelhoFacadeImpl:getAparelho(" + user + ",[]) - [" + ex.getMessage() + "]", ex);
            throw (new TuxedoException("Erro na construção do XML de entrada(xmlIn)", ex));

        } catch (Exception ex) {
            log.error("Exception - OfertaAparelhoFacadeImpl:getAparelho(" + user + "," + ofAparelhoParam.xmlText() + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO addAparelho(String user, String[] dados, String[] ofertasReal, String[] ofertasAceita, String[] dadosEntrega, String tipoEncerramento) throws FacadeException, XmlException, TuxedoException {
        log.debug("OfertaAparelhoFacadeImpl:getAparelho" + "( " + user + " )");
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = "<idPessoaDePara>" + dados[0] + "</idPessoaDePara><idRespostaIntencao>" + dados[1] + "</idRespostaIntencao>";
            xmlIN += "<idRespostaDestino>" + dados[2] + "</idRespostaDestino><idTipoEncerramento>" + tipoEncerramento + "</idTipoEncerramento>";
            xmlIN += "<dsObservacao>null</dsObservacao><idLinhaTelefonica>" + dados[4] + "</idLinhaTelefonica>";
            xmlIN += "<nrLinha>" + dados[5] + "</nrLinha>";

            /** Ofertas Realizadas **/
            if (ofertasReal != null) {
                if (ofertasReal.length > 0) {
                    xmlIN += "<ofertasRealizadas>";
                    for (int i = 0; i < ofertasReal.length; i++) {
                        xmlIN += "<id>" + ofertasReal[i] + "</id>";
                        // i++;
                        xmlIN += "<comentOferta>null</comentOferta>";
                    }
                    /***************** Modificado por Decio JR 05/10/2004 ************************************************/
                    /********************
                     * Enviar Oferta aceita é enviada também enviada como oferta
                     * realizada
                     ************/
                    xmlIN += "<id>" + dados[7] + " </id><comentOferta>null</comentOferta>";
                    /********************* Fim Modificação ***************************************************************/
                    xmlIN += "</ofertasRealizadas>";
                } else {
                    xmlIN += "<ofertasRealizadas><id>" + dados[7] + " </id><comentOferta>null</comentOferta></ofertasRealizadas>";
                }
            } else {
                // Se não tiver nenhuma oferta realizada, eu gravo a realizada e
                // aceita com mesmo id
                xmlIN += "<ofertasRealizadas><id>" + dados[7] + " </id><comentOferta>null</comentOferta></ofertasRealizadas>";
            }
            /** Ofertas Aceitas **/
            xmlIN += "<ofertaAceita><idOfertaAceita>" + dados[7] + "</idOfertaAceita>";
            xmlIN += "<caractOfertasAceitas>";

            /** Começa com i = 1 porque a posição 0 está com idOfertaAceita **/
            for (int i = 0; i < ofertasAceita.length; i++) {
                xmlIN += "<nome>" + StringEscapeUtils.escapeXml(ofertasAceita[i]) + "</nome>";
                i++;
                xmlIN += "<valor>'" + StringEscapeUtils.escapeXml(ofertasAceita[i]) + "'</valor>";
            }

            xmlIN += "</caractOfertasAceitas></ofertaAceita>";

            /** Dados de Entrega **/
            xmlIN += "<dadosEntrega><dsEndereco>" + StringEscapeUtils.escapeXml(dadosEntrega[0]) + "</dsEndereco>";
            xmlIN += "<dsContatoAutorizado>" + StringEscapeUtils.escapeXml(dadosEntrega[1]) + "</dsContatoAutorizado>";
            xmlIN += "<dsContatoTelefone>" + StringEscapeUtils.escapeXml(dadosEntrega[2]) + "</dsContatoTelefone>";
            xmlIN += "<comentEntrega>" + StringEscapeUtils.escapeXml(dadosEntrega[3]) + "</comentEntrega>";
            xmlIN += "</dadosEntrega>";

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "FINALRETENCAO", xmlIN));

            return RetornoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - OfertaAparelhoFacadeImpl:addAparelho(" + user + ", " + "[ " + dados[0] + " , " + dados[1] + " ," + dados[2] + " , " + dados[3] + "] ) - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (XmlException ex) {
            log.error("XmlException - OfertaAparelhoFacadeImpl:addAparelho(" + user + ", " + "[ " + dados[0] + " , " + dados[1] + " ," + dados[2] + " , " + dados[3] + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaAparelhoFacadeImpl:addAparelho", ex));

        } catch (Exception ex) {
            log.error("Exception - OfertaAparelhoFacadeImpl:addAparelho(" + user + ", " + "[ " + dados[0] + " , " + dados[1] + " ," + dados[2] + " , " + dados[3] + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getParcelas(String user, String idMatrizAparelho, String idOperacao, String idTipoPessoa) throws FacadeException, TuxedoException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = new StringBuffer("<idMatrizAparelho>").append(idMatrizAparelho).append("</idMatrizAparelho>")
                             .append("<idOperacao>").append(idOperacao).append("</idOperacao>")
                             .append("<idTipoPessoa>").append(idTipoPessoa).append("</idTipoPessoa>").toString();

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "SELPARCELAS", xmlIN));

            return FidelizacaoListaGeralVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getFidelizacaoListaGeralVO();
            
        } catch (XmlException ex) {
            log.error("XmlException - OfertaAparelhoFacadeImpl:getParcelas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaAparelhoFacadeImpl:getParcelas", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("/tOfertaAparelhoFacadeImpl:getParcelas(" + user + "] ) - [" + ex.getMessage() + "]", ex);
            throw (new FacadeException("Erro na construção do XML de entrada(xmlIn)"));

        } catch (Exception ex) {
            log.error("Exception - OfertaAparelhoFacadeImpl:getParcelas(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public EnderecoVO getEndecoPorCEP(String user, String nrCEP) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = "<numCEP>" + nrCEP + "</numCEP>";

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "BuscaDadosCEP", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return EnderecoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getEnderecoVO();

        } catch (XmlException ex) {
            log.error("XmlException - OfertaAparelhoFacadeImpl:getEndecoPorCEP(" + user + ", " + nrCEP + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaAparelhoFacadeImpl:getEndecoPorCEP", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - OfertaAparelhoFacadeImpl:getEndecoPorCEP(" + user + ", " + nrCEP + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - OfertaAparelhoFacadeImpl:getEndecoPorCEP(" + user + ", " + nrCEP + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public PesquisaEnderecoVO getPesquisaEnderecoIni(String user) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "PesquisaEndIni", "");

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return PesquisaEnderecoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getPesquisaEnderecoVO();

        } catch (XmlException ex) {
            log.error("XmlException - OfertaAparelhoFacadeImpl:getPesquisaEnderecoIni(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaAparelhoFacadeImpl:getPesquisaEnderecoIni", ex));

        } catch (Exception ex) {
            log.error("Exception - OfertaAparelhoFacadeImpl:getPesquisaEnderecoIni(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public PesquisaEnderecoVO getPesquisaEnderecoFil(String user, PesquisaEnderecoVO filtroEndereco) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "PesquisaEndFil", filtroEndereco.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            return PesquisaEnderecoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getPesquisaEnderecoVO();

        } catch (XmlException ex) {
            log.error("XmlException - OfertaAparelhoFacadeImpl:getPesquisaEnderecoFil(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaAparelhoFacadeImpl:getPesquisaEnderecoFil", ex));

        } catch (Exception ex) {
            log.error("Exception - OfertaAparelhoFacadeImpl:getPesquisaEnderecoFil(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public FidelizacaoListaGeralVO getRegional(String user) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "GETREGIONAL", "<getreg>GETREGIONAL</getreg>");

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return FidelizacaoListaGeralVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getFidelizacaoListaGeralVO();

        } catch (XmlException ex) {
            log.error("XmlException - OfertaAparelhoFacadeImpl:getRegional(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaAparelhoFacadeImpl:getRegional", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - OfertaAparelhoFacadeImpl:getRegional(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - OfertaAparelhoFacadeImpl:getRegional(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetencaoRetornoSAPVO getComSAP(String user, RetencaoSAPVO rSAPVO) throws TuxedoException, FacadeException {
        RetencaoRetornoSAPVO retorno = RetencaoRetornoSAPVO.Factory.newInstance();
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "COMSAP", rSAPVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            if (xmlOUT == null || ConstantesCRM.SVAZIO.equals(xmlOUT)) {
                retorno.setDescErro("O serviço Tuxedo não retornou nenhuma mensagem.");
            
            } else {
                MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);

                String statusCode = msgDoc.getMsg().getMsgHdr().getStatusCode();
                String statusText = msgDoc.getMsg().getMsgHdr().getStatusText();
                String innerText = msgDoc.getMsg().getMsgBody().xmlText();

                if (statusCode.indexOf("E") > -1 || innerText==null || "".equals(innerText) || "<xml-fragment/>".equals(innerText) ) {
                    retorno.setDescErro(statusText);
               
                } else {
                    retorno = RetencaoRetornoSAPVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getRetencaoRetornoSAPVO();
                }
            }
            return retorno;

        } catch (XmlException ex) {
            log.error("XmlException - OfertaAparelhoFacadeImpl:getComSAP(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaAparelhoFacadeImpl:getComSAP", ex));

        } catch (Exception ex) {
            log.error("Exception - OfertaAparelhoFacadeImpl:getComSAP(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO getDiasSuspTemp(String user) throws TuxedoException, FacadeException {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = XmlManager.MakeXmlIn(user, "SELSUSPTEMP", ConstantesCRM.SVAZIO);

            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (XmlException ex) {
            log.error("XmlException - OfertaAparelhoFacadeImpl:getDiasSuspTemp(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaAparelhoFacadeImpl:getDiasSuspTemp", ex));

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - OfertaAparelhoFacadeImpl:getDiasSuspTemp(" + user + ") - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (Exception ex) {
            log.error("Exception - OfertaAparelhoFacadeImpl:getDiasSuspTemp(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public RetornoVO addAparelho2(String user, String[] dados, FidelizacaoListaGeralDescricaoVO ofertasReal, String[] ofertasAceita, String[] dadosEntrega, String tipoEncerramento) throws FacadeException, XmlException, TuxedoException {
        log.debug("OfertaAparelhoFacadeImpl:getAparelho" + "( " + user + " )");
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        try {
            xmlIN = "<idPessoaDePara>" + dados[0] + "</idPessoaDePara><idRespostaIntencao>" + dados[1] + "</idRespostaIntencao>";
            xmlIN += "<idRespostaDestino>" + dados[2] + "</idRespostaDestino><idTipoEncerramento>" + tipoEncerramento + "</idTipoEncerramento>";
            xmlIN += "<dsObservacao>null</dsObservacao><idLinhaTelefonica>" + dados[4] + "</idLinhaTelefonica>";
            xmlIN += "<nrLinha>" + dados[5] + "</nrLinha>";

            /** Ofertas Realizadas **/
            if (ofertasReal != null) {
                if (ofertasReal.sizeOfItemListaDescricaoVOArray() > 0) {
                    xmlIN += "<ofertasRealizadas>";
                    for (int i = 0; i < ofertasReal.sizeOfItemListaDescricaoVOArray(); i++) {
                        xmlIN += "<id>" + ofertasReal.getItemListaDescricaoVOArray(i).getId() + "</id>";
                        // i++;
                        xmlIN += "<comentOferta>null</comentOferta>";
                    }
                    /***************** Modificado por Decio JR 05/10/2004 ************************************************/
                    /********************
                     * Enviar Oferta aceita é enviada também enviada como oferta
                     * realizada
                     ************/
                    xmlIN += "<id>" + dados[7] + " </id><comentOferta>null</comentOferta>";
                    /********************* Fim Modificação ***************************************************************/
                    xmlIN += "</ofertasRealizadas>";
                } else {
                    xmlIN += "<ofertasRealizadas><id>" + dados[7] + " </id><comentOferta>null</comentOferta></ofertasRealizadas>";
                }

            } else {
                // Se não tiver nenhuma oferta realizada, eu gravo a realizada e
                // aceita com mesmo id
                xmlIN += "<ofertasRealizadas><id>" + dados[7] + " </id><comentOferta>null</comentOferta></ofertasRealizadas>";

            }

            /** Ofertas Aceitas **/
            xmlIN += "<ofertaAceita><idOfertaAceita>" + dados[7] + "</idOfertaAceita>";
            xmlIN += "<caractOfertasAceitas>";

            /** Começa com i = 1 porque a posição 0 está com idOfertaAceita **/
            for (int i = 0; i < ofertasAceita.length; i++) {
                xmlIN += "<nome>" + StringEscapeUtils.escapeXml(ofertasAceita[i]) + "</nome>";
                i++;
                xmlIN += "<valor>'" + StringEscapeUtils.escapeXml(ofertasAceita[i]) + "'</valor>";
            }

            xmlIN += "</caractOfertasAceitas></ofertaAceita>";

            /** Dados de Entrega **/
            xmlIN += "<dadosEntrega><dsEndereco>" + StringEscapeUtils.escapeXml(dadosEntrega[0]) + "</dsEndereco>";
            xmlIN += "<dsContatoAutorizado>" + StringEscapeUtils.escapeXml(dadosEntrega[1]) + "</dsContatoAutorizado>";
            xmlIN += "<dsContatoTelefone>" + StringEscapeUtils.escapeXml(dadosEntrega[2]) + "</dsContatoTelefone>";
            xmlIN += "<comentEntrega>" + StringEscapeUtils.escapeXml(dadosEntrega[3]) + "</comentEntrega>";
            xmlIN += "</dadosEntrega>";

            xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "FINALRETENCAO", xmlIN));

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

            return RetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getRetornoVO();

        } catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - OfertaAparelhoFacadeImpl:addAparelho(" + user + ", " + "[ " + dados[0] + " , " + dados[1] + " ," + dados[2] + " , " + dados[3] + "] ) - [" + ex.getMessage() + "]");
            throw new TuxedoException(ex);

        } catch (XmlException ex) {
            log.error("XmlException - OfertaAparelhoFacadeImpl:addAparelho(" + user + ", " + "[ " + dados[0] + " , " + dados[1] + " ," + dados[2] + " , " + dados[3] + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: OfertaAparelhoFacadeImpl:addAparelho", ex));

        } catch (Exception ex) {
            log.error("Exception - OfertaAparelhoFacadeImpl:addAparelho(" + user + ", " + "[ " + dados[0] + " , " + dados[1] + " ," + dados[2] + " , " + dados[3] + "] ) - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }
}
