package br.com.vivo.fo.ctrls.cliente.servico;

import java.io.File;
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
import br.com.vivo.fo.cliente.vo.ReducaoVelocidadeVODocument;
import br.com.vivo.fo.cliente.vo.ReducaoVelocidadeVODocument.ReducaoVelocidadeVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.servico.vo.ServicoVODocument.ServicoVO;
import br.com.vivo.fo.servico.vo.ServicosItemDocument.ServicosItem;
import br.com.vivo.fo.xml.XmlManager;
import br.com.vivo.fo.xml.XmlManagerVol;

@Stateless(name = "ServicoFacade", mappedName = "ServicoFacade")
@Local(ServicoFacade.class)
@Session(ejbName = "ServicoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ServicoFacadeImpl implements ServicoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private XmlManagerVol     xmlManager       = new XmlManagerVol();
    private static Logger     log              = new Logger("clientes");

    // verifica se o beneficio informado esta ativo.
    public boolean isAtivo(String idUsuario, String linha, String servico, int idTipoLinha) throws FacadeException {

        try {
            StringBuffer xmlIn = new StringBuffer();
            xmlIn.append(getCabecalho(servico, linha, idTipoLinha));
            xmlIn.append("<usuario>").append(idUsuario).append("</usuario>");
            
            xmlIn.append(getFimXML());
            Object retorno = xmlManager.output(new File("C:\\testeXML\\ServicoVO.xml"));
            if (retorno instanceof ServicoVO) {
                ServicoVO servicoVO = (ServicoVO) retorno;

                // [Fernando Gomes]
                ServicosItem[] servicoItemVO = servicoVO.getServicosItemArray();

                for (int i = 0; i < servicoItemVO.length; i++) {
                    if (servico.equalsIgnoreCase(servicoItemVO[i].getCodigo())) {
                        return true;
                    }
                }
            }
        } catch (XmlException ex) {
            log.error("XmlException - ServicoFacadeImpl:isAtivo() - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ServicoFacadeImpl:isAtivo", ex));

        } catch (Exception ex) {
            log.error("Exception - ServicoFacadeImpl:isAtivo() - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return false;
    }

    /**
     * @common:operation
     */
    public ReducaoVelocidadeVO getConsultaVelocidade(String user, String nrLinha) throws Exception {

        ReducaoVelocidadeVO reducaoVelocidadeVO = ReducaoVelocidadeVO.Factory.newInstance();

        StringBuffer xmlIn = new StringBuffer();
        xmlIn.append("<ProxyOperacao>getVelocidade</ProxyOperacao>").append("<ProxyLinha>").append(nrLinha).append("</ProxyLinha>").append("<usuario>FO</usuario>").append("<idCanal>1</idCanal>").append("<xmlns>cliente.fo.vivo.com.br/vo</xmlns>");

        String xmlIN = XmlManager.MakeXmlIn(user, "TUXNGINBE", xmlIn.toString());
        // String xmlOUT = (new ControlTuxedoCall()).execute(this, servicoTux,
        // "GETSERVICE", xmlIN);
        String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
        MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
        xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

        reducaoVelocidadeVO = ReducaoVelocidadeVODocument.Factory.parse(xmlOUT).getReducaoVelocidadeVO();

        return reducaoVelocidadeVO;

    }

    public void setServico(String idUsuario, String linha, String servico, String operacao, int idTipoLinha) throws FacadeException {
        try {
            StringBuffer xmlIn = new StringBuffer();
            xmlIn.append(getCabecalho(servico, linha, idTipoLinha));
            xmlIn.append("<usuario>");
            xmlIn.append(idUsuario);
            xmlIn.append("</usuario>");
            xmlIn.append("<operacao>");
            xmlIn.append(operacao);
            xmlIn.append("</operacao>");
            xmlIn.append("<servico>");
            xmlIn.append(servico);
            xmlIn.append("</servico>");
            xmlIn.append(getFimXML());
            xmlManager.output(new File("C:\\testeXML\\ServicoVO.xml"));

        } catch (XmlException ex) {
            log.error("ServicoFacadeImpl:setServico() - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ServicoFacadeImpl:setServico", ex));

        } catch (Exception ex) {
            log.error("ServicoFacadeImpl:setServico() - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
    }

    // monta o cabeçalho do XML
    private String getCabecalho(String operacao, String nrLinha, int idTipoLinha) {

        String serviceName = ConstantesCRM.SVAZIO;
        switch (idTipoLinha) {
        case 1: // Pos
            serviceName = "TUXATLYSBE";
            break;
        case 2: // Pre
            serviceName = "TUXNGINBE";
            break;
        case 4:
        case 7: // Controle
            serviceName = getOperacaoControle(idTipoLinha);
            break;
        }

        StringBuffer cabecalho = new StringBuffer();
        cabecalho.append("<msg>");
        cabecalho.append("<msgHdr>");
        cabecalho.append("<user>").append(ConstantesCRM.STWO).append("</user>");
        cabecalho.append("<service>").append(serviceName).append("</service>");
        cabecalho.append("</msgHdr>");
        cabecalho.append("<msgBody>");
        cabecalho.append("<ProxyOperacao>").append(operacao).append("</ProxyOperacao>");
        cabecalho.append("<ProxyLinha>").append(nrLinha).append("</ProxyLinha>");

        return cabecalho.toString();
    }

    // monta o final do XML
    private String getFimXML() {
        StringBuffer finalXML = new StringBuffer();
        finalXML.append("</msgBody>");
        finalXML.append("</msg>");

        return finalXML.toString();
    }

    private String getOperacaoControle(int idOperacaoTipoLinhaControle) {
        String serviceName = "TUXATLYSBE";
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
