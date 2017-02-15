package br.com.vivo.fo.ctrls.cliente.ativacaoServico;

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
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.servico.vo.ServicoVODocument;
import br.com.vivo.fo.servico.vo.ServicoVODocument.ServicoVO;
import br.com.vivo.fo.servico.vo.ServicosItemDocument.ServicosItem;
import br.com.vivo.fo.xml.XmlManagerVol;

@Stateless(name = "AtivacaoServicoFacade", mappedName = "AtivacaoServicoFacade")
@Local(AtivacaoServicoFacade.class)
@Session(ejbName = "AtivacaoServicoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AtivacaoServicoFacadeImpl implements AtivacaoServicoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private XmlManagerVol     xmlManager       = new XmlManagerVol();
    private transient Logger  log              = new Logger("clientes");
    private String            serviceName      = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public boolean isAtivo(String idUsuario, String linha, String servico, int idTipoLinha) throws FacadeException {
        try {
            getServico("FO", linha, servico, idTipoLinha);
            StringBuffer xmlIn = new StringBuffer();
            xmlIn.append("<ProxyOperacao>getServicos</ProxyOperacao>");
            xmlIn.append("<ProxyLinha>").append(linha).append("</ProxyLinha>");
            xmlIn.append("<usuario>FO</usuario><idCanal>1</idCanal>");
            xmlIn.append("<xmlns>servico.fo.vivo.com.br/vo</xmlns>");

            String xmlIN = xmlManager.xmlInput(getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_SERVICO), ConstantesCRM.SONE, xmlIn.toString());
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            Object retorno = xmlManager.output(xmlOUT/*
                                                      * clienteTux.GETSERVICE(
                                                      * xmlManager
                                                      * .xmlInput(getTuxProxy
                                                      * (idTipoLinha,
                                                      * ConstantesCRM
                                                      * .OPERACAO_SERVICO), "1",
                                                      * xmlIn.toString()))
                                                      */);

            if (retorno instanceof ServicoVO) {
                ServicoVO servicoVO = (ServicoVO) retorno;
                ServicosItem[] servicoItem = servicoVO.getServicosItemArray();
                for (int i = 0; i < servicoItem.length; i++) {
                    if (servico.equalsIgnoreCase(servicoItem[i].getCodigo())) {
                        return true;
                    }
                }
            } else {
                log.warn("AtivacaoServicoFacadeImpl:isAtivo() - [Serviço não encontrado]");
                throw (new FacadeException("Serviço não encontrado"));
            }
            /*
             * }catch (ControlException e){ log.error(
             * "ControlException - AtivacaoServicoFacadeImpl:isAtivo() - Erro ao invocar o serviço no Tuxedo - ["
             * + e.getMessage() + "]"); throw new
             * FacadeException("Erro ao invocar o serviço no Tuxedo!" +
             * e.getMessage());
             */
        } catch (XmlException ex) {
            log.error("AtivacaoServicoFacadeImpl:getConsultaHexa() - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

        } catch (Exception ex) {
            log.error("Exception - AtivacaoServicoFacadeImpl:isAtivo() - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return false;
    }

    /**
     * @common:operation
     */
    public ServicoVO getServico(String user, String linha, String servico, int idTipoLinha) throws FacadeException, TuxedoException {
        try {
            xmlOUT = ConstantesCRM.SVAZIO;
            StringBuffer xmlIn = new StringBuffer();
            xmlIn.append("<ProxyOperacao>getServicos</ProxyOperacao>");
            xmlIn.append("<ProxyLinha>").append(linha).append("</ProxyLinha>");
            xmlIn.append("<usuario>FO</usuario><idCanal>1</idCanal>");
            xmlIn.append("<xmlns>").append("servico.fo.vivo.com.br/vo").append("</xmlns>");

            // String inService =
            // TuxedoServiceBridge.getXMLRequest(user,"TUXNGINBE",xmlIn.toString());
            String inService = TuxedoServiceBridge.getXMLRequest(user, getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_SERVICO), xmlIn.toString());
            // xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux,
            // "GETSERVICE", inService);
            xmlOUT = tuxedo.callService("TuxConnector", inService);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            if (msgDocRet != null && msgDocRet.getMsg() != null && msgDocRet.getMsg().getMsgHdr() != null) {
                if ("97E".equals(msgDocRet.getMsg().getMsgHdr().getStatusCode().substring(0, 3))) {

                    ServicoVO servicoVOerr = ServicoVO.Factory.newInstance();
                    servicoVOerr.setCodErro(msgDocRet.getMsg().getMsgHdr().getStatusCode());
                    servicoVOerr.setMsgErro("Sistema legado temporariamente indisponível.");
                    return servicoVOerr;

                    // Cliente inexistente no legado
                } else if ("24E".equals(msgDocRet.getMsg().getMsgHdr().getStatusCode().substring(0, 3)) || "46E".equals(msgDocRet.getMsg().getMsgHdr().getStatusCode().substring(0, 3))) {

                    ServicoVO servicoVOerr = ServicoVO.Factory.newInstance();
                    servicoVOerr.setCodErro(msgDocRet.getMsg().getMsgHdr().getStatusCode());
                    servicoVOerr.setMsgErro("Cliente não encontrado no sistema legado.");
                    return servicoVOerr;
                }

            }

            ServicoVO retornoTuxedo = ServicoVODocument.Factory.parse(xmlOUT).getServicoVO();

            return retornoTuxedo;

            /*
             * }catch (ControlException e){ log.error(
             * "ControlException - AtivacaoServicoFacadeImpl:getServico() - Erro ao invocar o serviço no Tuxedo - ["
             * + e.getMessage() + "]"); throw new
             * FacadeException("Erro ao invocar o serviço no Tuxedo! " +
             * e.getMessage(), e);
             */
        } catch (TuxedoServiceCallerException ex) {
            TuxedoException tuxEx = new TuxedoException(ex);
            // Legado indisponivel
            if ("97E".equals(tuxEx.getXmlHeader().getStatusCode().substring(0, 3))) {

                ServicoVO servicoVOerr = ServicoVO.Factory.newInstance();
                servicoVOerr.setCodErro(tuxEx.getXmlHeader().getStatusCode());
                servicoVOerr.setMsgErro("Sistema legado temporariamente indisponível.");
                return servicoVOerr;

                // Cliente inexistente no legado
            } else if ("24E".equals(tuxEx.getXmlHeader().getStatusCode().substring(0, 3)) || "46E".equals(tuxEx.getXmlHeader().getStatusCode().substring(0, 3))) {

                ServicoVO servicoVOerr = ServicoVO.Factory.newInstance();
                servicoVOerr.setCodErro(tuxEx.getXmlHeader().getStatusCode());
                servicoVOerr.setMsgErro("Cliente não encontrado no sistema legado.");
                return servicoVOerr;
            } else {
                log.error("TuxedoException - AtivacaoServicoFacadeImpl:getServico() - [" + tuxEx.getMessage() + "]");
                throw (new FacadeException(tuxEx));
            }
        } catch (Exception ex) {
            log.error("Exception - AtivacaoServicoFacadeImpl:getServico() - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    public String montaString(String nrLinha, String servico) throws FacadeException {
        // ServicosItem servicosItem = getServico(nrLinha,servico);
        // if(servicosItem.getDescricao().equalsIgnoreCase()){
        // }else
        return null;
    }

    /**
     * @common:operation
     */
    public void setServico(String idUsuario, String linha, String servico, String operacao, int idTipoLinha) throws FacadeException {
        try {

            StringBuffer xmlIn = new StringBuffer();
            xmlIn.append("<ProxyOperacao>setServico</ProxyOperacao>");
            xmlIn.append("<ProxyLinha>").append(linha).append("</ProxyLinha>");
            xmlIn.append("<usuario>FO</usuario><idCanal>1</idCanal>");
            xmlIn.append("<xmlns>servico.fo.vivo.com.br/vo</xmlns>");
            xmlIn.append("<operacao>").append(operacao).append("</operacao>");
            xmlIn.append("<servico>").append(servico).append("</servico>");

            xmlManager.xmlInput(getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_SERVICO), "1", xmlIn.toString());

            String xmlIN = xmlManager.xmlInput(getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_SERVICO), "1", xmlIn.toString());
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            xmlManager.output(xmlOUT/*
                                     * clienteTux.GETSERVICE(xmlManager.xmlInput(
                                     * getTuxProxy(idTipoLinha,
                                     * ConstantesCRM.OPERACAO_SERVICO), "1",
                                     * xmlIn.toString()))
                                     */);

        } catch (XmlException ex) {
            log.error("XmlException - AtivacaoServicoFacadeImpl:setServico() - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtivacaoServicoFacadeImpl:setServico", ex));

        } catch (Exception ex) {
            log.error("Exception - AtivacaoServicoFacadeImpl:setServico() - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    /**
     * @common:operation
     */
    public void block(String nrLinha, String motivo, String data, int idTipoLinha) throws FacadeException {
        try {

            StringBuffer xmlIn = new StringBuffer();
            xmlIn.append("<ProxyOperacao>setSuspendeCelular</ProxyOperacao>");
            xmlIn.append("<ProxyLinha>").append(nrLinha).append("</ProxyLinha>");
            xmlIn.append("<usuario>FO</usuario><idCanal>1</idCanal>");
            xmlIn.append("<motivo>").append(motivo).append("</motivo>");
            xmlIn.append("<BOData>").append(data).append("</BOData>");

            String xmlIN = xmlManager.xmlInput(getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_SUSPENDE_CELULAR), "1", xmlIn.toString());
            tuxedo.callService("TuxConnector", xmlIN);

            xmlManager.output(/*
                               * clienteTux.GETSERVICE(xmlManager.xmlInput(
                               * getTuxProxy(idTipoLinha,
                               * ConstantesCRM.OPERACAO_SUSPENDE_CELULAR), "1",
                               * xmlIn.toString()))
                               */"");

            /*
             * }catch(ControlException e){
             * log.error("ControlException - AtivacaoServicoFacadeImpl:block() - ["
             * + e.getMessage() + "]"); throw new
             * FacadeException("Erro ao invocar o serviço no Tuxedo! " +
             * e.getMessage());
             */
        } catch (XmlException ex) {
            log.error("XmlException - AtivacaoServicoFacadeImpl:block() - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: AtivacaoServicoFacadeImpl:block", ex));
        } catch (Exception ex) {
            log.error("Exception - AtivacaoServicoFacadeImpl:block() - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    @SuppressWarnings("unused")
    private String getTuxProxy(int idTipoLinha) {
        switch (idTipoLinha) {
        case 1:
        case 5:
        case 4:
        case 7:// Pos e Pos Chip
            serviceName = "TUXATLYSBE";
            break;
        case 2:
        case 6: // Pre e Pre Chip
            serviceName = "TUXNGINBE";
            break;
        }
        return serviceName;
    }

    /**
     * Novo metodo responsavel por identificar qual o serviço que devera ser
     * chamado no caso da linha CONTROLE
     */
    private String getTuxProxy(int idTipoLinha, int idOperacaoTipoLinhaControle) {
        switch (idTipoLinha) {
        case 1:
        case 5: // Pos e Pos Chip
            serviceName = "TUXATLYSBE";
            break;
        case 2:
        case 6: // Pre e Pre Chip
            serviceName = "TUXNGINBE";
            break;
        case 4:
        case 7: // Controle
            serviceName = getOperacaoControle(idOperacaoTipoLinhaControle);
            break;
        }
        return serviceName;
    }

    private String getOperacaoControle(int idOperacaoTipoLinhaControle) {
        switch (idOperacaoTipoLinhaControle) {
        case ConstantesCRM.OPERACAO_SERVICO:
            serviceName = "TUXATLYSBE";
            break;
        case ConstantesCRM.OPERACAO_CONSUMO:
            serviceName = "TUXATLYSBE";
            break;
        case ConstantesCRM.OPERACAO_DETALHE_SALDO:
            serviceName = "TUXNGINBE";
            break;
        case ConstantesCRM.OPERACAO_ESTIMATIVA_SALDO:
            serviceName = "TUXATLYSBE";
            break;
        case ConstantesCRM.OPERACAO_EXTRATO:
            serviceName = "TUXNGINBE";
            break;
        case ConstantesCRM.OPERACAO_HISTORICO_MOVIMENTO:
            serviceName = "TUXNGINBE";
            break;
        case ConstantesCRM.OPERACAO_PLANO:
            serviceName = "TUXNGINBE";
            break;
        case ConstantesCRM.OPERACAO_TARIFA_REDUZIDA:
            serviceName = "TUXNGINBE";
            break;
        case ConstantesCRM.OPERACAO_CAIXA_POSTAL:
            serviceName = "TUXNGINBE";
            break;
        case ConstantesCRM.OPERACAO_SUSPENDE_CELULAR:
            serviceName = "TUXATLYSBE";
            break;
        case ConstantesCRM.OPERACAO_DETALHE_FATURA:
            serviceName = "TUXNGINBE";
            break;
        }
        return serviceName;
    }

}