package br.com.vivo.fo.ctrls.workflow.monitoramento;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import noNamespace.MsgDocument;
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
import br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument;
import br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO;
import br.com.vivo.fo.workflow.vo.MonitoramentoResultadoVODocument;
import br.com.vivo.fo.workflow.vo.MonitoramentoResultadoVODocument.MonitoramentoResultadoVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "MonitoramentoFacade", mappedName = "MonitoramentoFacade")
@Local(MonitoramentoFacade.class)
@Session(ejbName = "MonitoramentoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class MonitoramentoFacadeImpl implements MonitoramentoFacade {

    @EJB
    private TuxedoServiceCall tuxedo;

    private static Logger     log = new Logger("workflow");

    public MonitoramentoPesquisaVO obtemGrupos(String user) throws TuxedoException, FacadeException {

		try {

			log.debug("MonitoramentoFacadeImpl:obtemGrupos(" + user + ")");
			String xmlIN = "<inRegional>1</inRegional>";

            String xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "WFMONOBTGRU", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return MonitoramentoPesquisaVODocument.Factory.parse(xmlOUT).getMonitoramentoPesquisaVO();

		} catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MonitoramentoFacadeImpl:obtemGrupos(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
            log.error("Exception - MonitoramentoFacadeImpl:obtemGrupos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MonitoramentoFacadeImpl:obtemGrupos", ex));
		}
	}

	/**
	 * @common:operation
	 */
    public MonitoramentoPesquisaVO getMonitoramentoPesquisaVO(String user, boolean inTipoCarteira, boolean inSegmentacao, boolean inAlerta, boolean inTipoPessoa, boolean inClassificadorTipoLinha) throws TuxedoException, FacadeException {

		try {
            log.debug("MonitoramentoFacadeImpl:getMonitoramentoPesquisaVO(" + user + ")");
			String xmlIN = "<inRegional>1</inRegional>";
            if (inTipoCarteira) xmlIN += "<inTipoCarteira>1</inTipoCarteira>";
            if (inSegmentacao) xmlIN += "<inSegmentacao>1</inSegmentacao>";
            if (inAlerta) xmlIN += "<inAlerta>1</inAlerta>";
            if (inTipoPessoa) xmlIN += "<inTipoPessoa>1</inTipoPessoa>";
            if (inClassificadorTipoLinha) xmlIN += "<inClassificadorTipoLinha>1</inClassificadorTipoLinha>";

            String xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "WFMONOBTGRU", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return MonitoramentoPesquisaVODocument.Factory.parse(xmlOUT).getMonitoramentoPesquisaVO();

		} catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MonitoramentoFacadeImpl:getMonitoramentoPesquisaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
            log.error("Exception - MonitoramentoFacadeImpl:obtemGrupos(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MonitoramentoFacadeImpl:obtemGrupos", ex));
		}
	}

    public MonitoramentoPesquisaVO obtemGruposRC(String user, int inFila) throws TuxedoException, FacadeException {
		try {
			log.debug("MonitoramentoFacadeImpl:obtemGruposRC(" + user + ")");

			String xmlIN = "<inRC>1</inRC>";
			xmlIN += "<inFila>" + inFila + "</inFila>";
			xmlIN += "<inRegional>1</inRegional>";

            String xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "WFMONOBTGRU", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return MonitoramentoPesquisaVODocument.Factory.parse(xmlOUT).getMonitoramentoPesquisaVO();

		} catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MonitoramentoFacadeImpl:obtemGruposRC(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
            log.error("Exception - MonitoramentoFacadeImpl:obtemGruposRC(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MonitoramentoFacadeImpl:obtemGruposRC", ex));
		}
	}

	public MonitoramentoResultadoVO executaPesquisa(String user, MonitoramentoPesquisaVO monitoramentoPesquisaVO) throws TuxedoException, FacadeException {
		try {
			log.debug("MonitoramentoFacadeImpl:executaPesquisa(" + user + " , " + monitoramentoPesquisaVO + ")");

            String xmlOUT = ConstantesCRM.SVAZIO;
			int inPagina = 0, maxRegsPagina = 50;
			MonitoramentoResultadoVO mrVOLocal = MonitoramentoResultadoVO.Factory.newInstance();
			MonitoramentoResultadoVO mrVORetorno = MonitoramentoResultadoVO.Factory.newInstance();

            monitoramentoPesquisaVO.setInPagina(String.valueOf(inPagina));

			String xmlIN = monitoramentoPesquisaVO.toString().replaceAll("vo:", ConstantesCRM.SVAZIO);
			
			xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "WFMONOBTGRU", xmlIN));

            MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOUT);

			// Seto o Array recebido
            mrVORetorno = MonitoramentoResultadoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getMonitoramentoResultadoVO();
			mrVOLocal = mrVORetorno;

			// O serviço devolve no máximo 50 registros
			while (mrVOLocal.getMonitoramentoVOArray().length == maxRegsPagina) {

				monitoramentoPesquisaVO.setInPagina("" + (++inPagina));

				xmlIN = monitoramentoPesquisaVO.toString().replaceAll("vo:", ConstantesCRM.SVAZIO);
				
                xmlOUT = ConstantesCRM.SVAZIO;
				xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "WFMONOBTGRU", xmlIN));

				msgDoc = MsgDocument.Factory.parse(xmlOUT);
				xmlOUT = msgDoc.getMsg().getMsgBody().xmlText();

				mrVOLocal = MonitoramentoResultadoVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getMonitoramentoResultadoVO();

				for (int i = 0; i < mrVOLocal.getMonitoramentoVOArray().length; i++) {

					mrVORetorno.insertNewMonitoramentoVO(mrVORetorno.getMonitoramentoVOArray().length);
                    mrVORetorno.getMonitoramentoVOArray(mrVORetorno.getMonitoramentoVOArray().length - 1).setDescricaoEstado(mrVOLocal.getMonitoramentoVOArray(i).getDescricaoEstado());
                    mrVORetorno.getMonitoramentoVOArray(mrVORetorno.getMonitoramentoVOArray().length - 1).setDescricaoGrupo(mrVOLocal.getMonitoramentoVOArray(i).getDescricaoGrupo());
                    mrVORetorno.getMonitoramentoVOArray(mrVORetorno.getMonitoramentoVOArray().length - 1).setDescricaoLogin(mrVOLocal.getMonitoramentoVOArray(i).getDescricaoLogin());
                    mrVORetorno.getMonitoramentoVOArray(mrVORetorno.getMonitoramentoVOArray().length - 1).setQuantidade(mrVOLocal.getMonitoramentoVOArray(i).getQuantidade());
				}
			}

			return mrVORetorno;

		} catch (Exception ex) {
            log.error("Exception - MonitoramentoFacadeImpl:executaPesquisa(" + user + " , " + monitoramentoPesquisaVO + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MonitoramentoFacadeImpl:executaPesquisa", ex));
		}
	}

	/**
	 * Método responsável por iniciar a montagem da tela de monitoramento
	 * @common:operation
	 */
    public MonitoramentoPesquisaVO obtemGruposParaMonitoramento(String user) throws TuxedoException, FacadeException {
		try {
            log.debug("MonitoramentoFacadeImpl:obtemGruposParaMonitoramento(" + user + ")");
			String xmlIN = "<inPesquisa>2</inPesquisa>";

            String xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "WFMONOBTGRU", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

            return MonitoramentoPesquisaVODocument.Factory.parse(xmlOUT).getMonitoramentoPesquisaVO();

		} catch (TuxedoServiceCallerException ex) {
            log.error("TuxedoException - MonitoramentoFacadeImpl:obtemGruposParaMonitoramento(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
            log.error("Exception - MonitoramentoFacadeImpl:obtemGruposParaMonitoramento(" + user + ") - [" + ex.getMessage() + "]");
            throw (new FacadeException("Erro na montagem do XML de entrada: MonitoramentoFacadeImpl:obtemGruposParaMonitoramento", ex));
		}
	}
}
