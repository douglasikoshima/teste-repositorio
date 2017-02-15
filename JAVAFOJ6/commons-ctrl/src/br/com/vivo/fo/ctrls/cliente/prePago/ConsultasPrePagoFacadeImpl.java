package br.com.vivo.fo.ctrls.cliente.prePago;

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
import br.com.vivo.fo.cliente.vo.DetalheLinhaVODocument;
import br.com.vivo.fo.cliente.vo.DetalheLinhaVODocument.DetalheLinhaVO;
import br.com.vivo.fo.cliente.vo.DetalhesSaldoVODocument;
import br.com.vivo.fo.cliente.vo.DetalhesSaldoVODocument.DetalhesSaldoVO;
import br.com.vivo.fo.cliente.vo.ExtratoVODocument;
import br.com.vivo.fo.cliente.vo.ExtratoVODocument.ExtratoVO;
import br.com.vivo.fo.cliente.vo.FavoritosVODocument;
import br.com.vivo.fo.cliente.vo.FavoritosVODocument.FavoritosVO;
import br.com.vivo.fo.cliente.vo.HistoricoAtendimentoVODocument;
import br.com.vivo.fo.cliente.vo.HistoricoAtendimentoVODocument.HistoricoAtendimentoVO;
import br.com.vivo.fo.cliente.vo.HistoricoMovimentosVODocument;
import br.com.vivo.fo.cliente.vo.HistoricoMovimentosVODocument.HistoricoMovimentosVO;
import br.com.vivo.fo.cliente.vo.PromocoesVODocument;
import br.com.vivo.fo.cliente.vo.PromocoesVODocument.PromocoesVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.servico.vo.ServicoVODocument;
import br.com.vivo.fo.servico.vo.ServicoVODocument.ServicoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ConsultasPrePagoFacade", mappedName = "ConsultasPrePagoFacade")
@Local(ConsultasPrePagoFacade.class)
@Session(ejbName = "ConsultasPrePagoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ConsultasPrePagoFacadeImpl implements ConsultasPrePagoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private transient Logger  log              = new Logger("clientes");

    private String            xmlIN            = ConstantesCRM.SVAZIO;
    private String            xmlOUT           = ConstantesCRM.SVAZIO;
    private String            serviceName      = ConstantesCRM.SVAZIO;

    /**
     * @common:operation
     */
    public DetalhesSaldoVO getDetalhesSaldo(String user, String idLinha, int idTipoLinha) throws TuxedoException, FacadeException {
        DetalhesSaldoVO detalhesSaldoVO = DetalhesSaldoVO.Factory.newInstance();
        try {
            xmlIN = "<ProxyOperacao>getDetalhesSaldo</ProxyOperacao>";
            xmlIN += "<ProxyLinha>" + idLinha + "</ProxyLinha>";
            xmlIN += "<usuario>FO</usuario><idCanal>1</idCanal>";
            xmlIN += "<xmlns>cliente.fo.vivo.com.br/vo</xmlns>";

            xmlIN = XmlManager.MakeXmlIn(user, getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_DETALHE_SALDO), xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            xmlOUT = replaceXmlns(xmlOUT, "tuxproxy.vivo.com.br/vo", "cliente.fo.vivo.com.br/vo");
            

            detalhesSaldoVO = DetalhesSaldoVODocument.Factory.parse(xmlOUT).getDetalhesSaldoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ConsultasPrePagoFacadeImpl:getDetalhesSaldo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConsultasPrePagoFacadeImpl:getDetalhesSaldo", ex));

        } catch (Exception ex) {
            log.error("Exception - ConsultasPrePagoFacadeImpl:getDetalhesSaldo(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return detalhesSaldoVO;
    }

    /**
     * @common:operation
     */
    public HistoricoMovimentosVO getHistoricoMovimentos(String user, String idLinha, String dataIni, String dataFim, int idTipoLinha) throws TuxedoException, FacadeException {
        HistoricoMovimentosVO historicoMovVO = HistoricoMovimentosVO.Factory.newInstance();
        try {
            xmlIN = "<ProxyOperacao>getHistoricoMovimentos</ProxyOperacao>";
            xmlIN += "<ProxyLinha>" + idLinha + "</ProxyLinha>";
            xmlIN += "<usuario>FO</usuario><idCanal>1</idCanal>";
            xmlIN += "<dataIni>" + dataIni + "</dataIni>";
            xmlIN += "<dataFim>" + dataFim + "</dataFim>";
            xmlIN += "<xmlns>cliente.fo.vivo.com.br/vo</xmlns>";

            xmlIN = XmlManager.MakeXmlIn(user, getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_HISTORICO_MOVIMENTO), xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            xmlOUT = replaceXmlns(xmlOUT, "tuxproxy.vivo.com.br/vo", "cliente.fo.vivo.com.br/vo");

            historicoMovVO = HistoricoMovimentosVODocument.Factory.parse(xmlOUT).getHistoricoMovimentosVO();

        } catch (XmlException ex) {
            log.error("XmlException - ConsultasPrePagoFacadeImpl:getHistoricoMovimentos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConsultasPrePagoFacadeImpl:getHistoricoMovimentos", ex));

        } catch (Exception ex) {
            log.error("Exception - ConsultasPrePagoFacadeImpl:getHistoricoMovimentos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return historicoMovVO;
    }

    /**
     * @common:operation
     */
    public PromocoesVO getPromocoes(String user, String idLinha, int idTipoLinha) throws TuxedoException, FacadeException {
        PromocoesVO promocoesVO = PromocoesVO.Factory.newInstance();
        try {
            xmlIN = "<ProxyOperacao>getPromocoes</ProxyOperacao>";
            xmlIN += "<ProxyLinha>" + idLinha + "</ProxyLinha>";
            xmlIN += "<usuario>FO</usuario><idCanal>1</idCanal>";
            xmlIN += "<xmlns>cliente.fo.vivo.com.br/vo</xmlns>";
         
            xmlIN = XmlManager.MakeXmlIn(user, getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_PROMOCOES), xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
           
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            xmlOUT = replaceXmlns(xmlOUT, "tuxproxy.vivo.com.br/vo", "cliente.fo.vivo.com.br/vo");

            promocoesVO = PromocoesVODocument.Factory.parse(xmlOUT).getPromocoesVO();

        } catch (XmlException ex) {
            log.error("XmlException - ConsultasPrePagoFacadeImpl:getPromocoes(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConsultasPrePagoFacadeImpl:getPromocoes", ex));

        } catch (Exception ex) {
            log.error("Exception - ConsultasPrePagoFacadeImpl:getPromocoes(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return promocoesVO;
    }

    /**
     * @common:operation
     */
    public FavoritosVO getFavoritos(String user, String idLinha, int idTipoLinha) throws TuxedoException, FacadeException {
        FavoritosVO favoritosVO = FavoritosVO.Factory.newInstance();
        try {
            xmlIN = "<ProxyOperacao>getFavoritos</ProxyOperacao>";
            xmlIN += "<ProxyLinha>" + idLinha + "</ProxyLinha>";
            xmlIN += "<usuario>FO</usuario><idCanal>1</idCanal>";
            xmlIN += "<xmlns>cliente.fo.vivo.com.br/vo</xmlns>";

            xmlIN = XmlManager.MakeXmlIn(user, getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_FAVORITOS), xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            xmlOUT = replaceXmlns(xmlOUT, "tuxproxy.vivo.com.br/vo", "cliente.fo.vivo.com.br/vo");

            favoritosVO = FavoritosVODocument.Factory.parse(xmlOUT).getFavoritosVO();

        } catch (XmlException ex) {
            log.error("XmlException - ConsultasPrePagoFacadeImpl:getFavoritos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConsultasPrePagoFacadeImpl:getFavoritos", ex));

        } catch (Exception ex) {
            log.error("Exception - ConsultasPrePagoFacadeImpl:getFavoritos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return favoritosVO;
    }

    /**
     * @common:operation
     */
    public HistoricoAtendimentoVO getHistoricoAtendimento(String user, String idLinha, String dataIni, String dataFim, int idTipoLinha) throws TuxedoException, FacadeException {
        HistoricoAtendimentoVO histAtendVO = HistoricoAtendimentoVO.Factory.newInstance();
        try {
            xmlIN = "<ProxyOperacao>getHistoricoAtendimento</ProxyOperacao>";
            xmlIN += "<ProxyLinha>" + idLinha + "</Proxy,Linha>";
            xmlIN += "<usuario>FO</usuario><idCanal>1</idCanal>";
            xmlIN += "<idcontasistemaorigem>2</idcontasistemaorigem>";
            xmlIN += "<dataIni>" + dataIni + "</dataIni>";
            xmlIN += "<dataFim>" + dataFim + "</dataFim>";
            xmlIN += "<xmlns>cliente.fo.vivo.com.br/vo</xmlns>";
 
            xmlIN = XmlManager.MakeXmlIn(user, getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_HISTORICO_ATENDIMENTO), xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            xmlOUT = replaceXmlns(xmlOUT, "tuxproxy.vivo.com.br/vo", "cliente.fo.vivo.com.br/vo");

            histAtendVO = HistoricoAtendimentoVODocument.Factory.parse(xmlOUT).getHistoricoAtendimentoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ConsultasPrePagoFacadeImpl:getHistoricoAtendimento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConsultasPrePagoFacadeImpl:getHistoricoAtendimento", ex));

        } catch (Exception ex) {
            log.error("Exception - ConsultasPrePagoFacadeImpl:getHistoricoAtendimento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return histAtendVO;
    }

    /**
     * @common:operation
     */
    public DetalheLinhaVO getDetalheLinha(String user, String idLinha, int idTipoLinha) throws TuxedoException, FacadeException {
        DetalheLinhaVO detalheLinhaVO = DetalheLinhaVO.Factory.newInstance();
        try {
            xmlIN = "<ProxyOperacao>getDetalheLinha</ProxyOperacao>";
            xmlIN += "<ProxyLinha>" + idLinha + "</ProxyLinha>";
            xmlIN += "<usuario>FO</usuario><idCanal>1</idCanal>";
            xmlIN += "<xmlns>cliente.fo.vivo.com.br/vo</xmlns>";

            xmlIN = XmlManager.MakeXmlIn(user, getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_DETALHE_LINHA), xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
            
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            xmlOUT = replaceXmlns(xmlOUT, "tuxproxy.vivo.com.br/vo", "cliente.fo.vivo.com.br/vo");

            detalheLinhaVO = DetalheLinhaVODocument.Factory.parse(xmlOUT).getDetalheLinhaVO();

        } catch (XmlException ex) {
            log.error("XmlException - ConsultasPrePagoFacadeImpl:getDetalheLinha(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConsultasPrePagoFacadeImpl:getDetalheLinha", ex));

        } catch (Exception ex) {
            log.error("Exception - ConsultasPrePagoFacadeImpl:getDetalheLinha(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return detalheLinhaVO;
    }

    /**
     * @common:operation
     */
    public ServicoVO getServico(String user, String idLinha, int idTipoLinha) throws TuxedoException, FacadeException {
        ServicoVO servicoVO = ServicoVO.Factory.newInstance();
        try {
            xmlIN = "<ProxyOperacao>getServicos</ProxyOperacao>";
            xmlIN += "<ProxyLinha>" + idLinha + "</ProxyLinha>";
            xmlIN += "<usuario>FO</usuario><idCanal>1</idCanal>";
            xmlIN += "<xmlns>servico.fo.vivo.com.br/vo</xmlns>";

            xmlIN = XmlManager.MakeXmlIn(user, getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_SERVICO), xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
           
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            xmlOUT = replaceXmlns(xmlOUT, "tuxproxy.vivo.com.br/vo", "cliente.fo.vivo.com.br/vo");

            servicoVO = ServicoVODocument.Factory.parse(xmlOUT).getServicoVO();

        } catch (XmlException ex) {
            log.error("XmlException - ConsultasPrePagoFacadeImpl:getServico(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConsultasPrePagoFacadeImpl:getServico", ex));

        } catch (Exception ex) {
            log.error("Exception - ConsultasPrePagoFacadeImpl:getServico(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return servicoVO;
    }

    /**
     * @common:operation
     */
    public ExtratoVO getExtrato(String user, String idLinha, String dataIni, String dataFim, int idTipoLinha) throws TuxedoException, FacadeException {
        ExtratoVO extratoVO = ExtratoVO.Factory.newInstance();
        try {
            xmlIN = "<ProxyOperacao>getExtrato</ProxyOperacao>";
            xmlIN += "<ProxyLinha>" + idLinha + "</ProxyLinha>";
            xmlIN += "<usuario>FO</usuario><idCanal>1</idCanal>";
            xmlIN += "<dataIni>" + dataIni + "</dataIni>";
            xmlIN += "<dataFim>" + dataFim + "</dataFim>";
            xmlIN += "<xmlns>cliente.fo.vivo.com.br/vo</xmlns>";
      
            xmlIN = XmlManager.MakeXmlIn(user, getTuxProxy(idTipoLinha, ConstantesCRM.OPERACAO_EXTRATO), xmlIN);
            xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
                
            MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
            xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
            xmlOUT = replaceXmlns(xmlOUT, "tuxproxy.vivo.com.br/vo", "cliente.fo.vivo.com.br/vo");

            if (xmlOUT != null && (!"".equals(xmlOUT)&& !"<xml-fragment/>".equals(xmlOUT))) {
                extratoVO = ExtratoVODocument.Factory.parse(xmlOUT).getExtratoVO();
            }else{
                throw new FacadeException(msgDocRet.getMsg().getMsgHdr().getStatusText());
            }
            
        } catch (XmlException ex) {
            log.error("XmlException - ConsultasPrePagoFacadeImpl:getExtrato(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: ConsultasPrePagoFacadeImpl:getExtrato", ex));

        } catch (Exception ex) {
            log.error("Exception - ConsultasPrePagoFacadeImpl:getExtrato(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException(ex));
        }
        return extratoVO;
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
     * Novo metodo responsavel por identificar qual o serviço que devera ser chamado
     * no caso da linha CONTROLE
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
    
    private String replaceXmlns(String xml, String de, String para) {
    	
    	try {
    		 if (!ConstantesCRM.SVAZIO.equals(xml) &&  xml.indexOf(de) > 0) {
    			 xml = xml.replaceAll(de,para);
    		 }
    	} catch (Exception e) {
    		log.error("[ConsultaPrePagoFacadeImpl.replaceXmlns] Erro ", e);
    	}
    	
    	return xml;
    }
    
   
	
  
}
