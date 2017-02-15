package br.com.vivo.fo.ctrls.fidelizacao.realizarRetencao;

import java.util.Date;

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
import br.com.vivo.fo.fidelizacao.vo.DetalheHistoricoRetencaoVODocument;
import br.com.vivo.fo.fidelizacao.vo.DetalheHistoricoRetencaoVODocument.DetalheHistoricoRetencaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.ItemArvoreVODocument;
import br.com.vivo.fo.fidelizacao.vo.ItemArvoreVODocument.ItemArvoreVO;
import br.com.vivo.fo.fidelizacao.vo.ListaDetalheLinhaVODocument;
import br.com.vivo.fo.fidelizacao.vo.ListaDetalheLinhaVODocument.ListaDetalheLinhaVO;
import br.com.vivo.fo.fidelizacao.vo.ListaHistoricoRetencaoVODocument;
import br.com.vivo.fo.fidelizacao.vo.ListaHistoricoRetencaoVODocument.ListaHistoricoRetencaoVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "RalizarRetencaoFacade", mappedName = "RalizarRetencaoFacade")
@Local(RalizarRetencaoFacade.class)
@Session(ejbName = "RalizarRetencaoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RalizarRetencaoFacadeImpl implements RalizarRetencaoFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	private static Logger log = new Logger("fidelizacao");

	/**
	 * @common:operation
	 */
	public ListaDetalheLinhaVO getLinha(String user, String idCliente, String numero, boolean inPortout, String idAtendimento) throws FacadeException, XmlException, TuxedoException {
	    long callServiceTime = 0;
		try {
			callServiceTime = new Date().getTime();
			String xmlIN = XmlManager.MakeXmlIn(user, "GETLINHAS", new StringBuffer("<idCliente>").append(idCliente).append("</idCliente>").append("<numeroLinha>").append(numero).append("</numeroLinha>").append("<inPortout>").append(inPortout ? "1" : "0").append("</inPortout>").append("<idAtendimento>").append(idAtendimento).append("</idAtendimento>").toString());

			String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			return ListaDetalheLinhaVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getListaDetalheLinhaVO();

		} catch (XmlException xex) {
			log.error("XmlException - RalizarRetencaoFacadeImpl:getLinha(" + user + ") - [" + xex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RalizarRetencaoFacadeImpl:getLinha", xex));

		} catch (Exception ex) {
			log.error("Exception - RalizarRetencaoFacadeImpl:getLinha(" + user + ") - [" + ex.getMessage() + "]");
			long exceptionTime = new Date().getTime();
			if ((exceptionTime - callServiceTime) / 1000 >= 30) {
				throw (new FacadeException("[TPTIME] - Sistema Legado Atlys Indisponivel.\nPor favor tentar efetuar retencao mais tarde."));
			} else {
				throw (new FacadeException(ex));
			}
		}
	}

	/**
	 * @common:operation
	 */
	public ListaHistoricoRetencaoVO getHistorico(String user, String idLinha) throws FacadeException, XmlException, TuxedoException {
		try {
			String xmlIN = XmlManager.MakeXmlIn(user, "SELHISTORICO", new StringBuffer("<idLinha>").append(idLinha).append("</idLinha>").toString());

			String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			return ListaHistoricoRetencaoVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getListaHistoricoRetencaoVO();
			
		} catch (TuxedoServiceCallerException tex) {
			log.error("/RalizarRetencaoFacadeImpl:getHistorico(" + user + ") - [" + tex.getMessage() + "]", tex);
			throw new TuxedoException(tex);

		} catch (XmlException xex) {
			log.error("XmlException - RalizarRetencaoFacadeImpl:getHistorico(" + user + ") - [" + xex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RalizarRetencaoFacadeImpl:getHistorico", xex));

		} catch (Exception ex) {
			log.error("Exception - RalizarRetencaoFacadeImpl:getHistorico(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public DetalheHistoricoRetencaoVO getDetalheHistorico(String user, String idCliente) throws FacadeException, XmlException, TuxedoException {
		try {
			String xmlIN = XmlManager.MakeXmlIn(user, "GETDETHIST", new StringBuffer("<idRetencao>").append(idCliente).append("</idRetencao>").toString());

			String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			String msg = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();
			
			return DetalheHistoricoRetencaoVODocument.Factory.parse(msg).getDetalheHistoricoRetencaoVO();

		} catch (TuxedoServiceCallerException tex) {
			log.error("/RalizarRetencaoFacadeImpl:getDetalheHistorico(" + user + ") - [" + tex.getMessage() + "]", tex);
			throw new TuxedoException(tex);

		} catch (XmlException xex) {
			log.error("XmlException - RalizarRetencaoFacadeImpl:getDetalheHistorico(" + user + ") - [" + xex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RalizarRetencaoFacadeImpl:getDetalheHistorico", xex));

		} catch (Exception ex) {
			log.error("Exception - RalizarRetencaoFacadeImpl:getDetalheHistorico(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ItemArvoreVO getArvore(String user) throws FacadeException, XmlException, TuxedoException {
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			String xmlIN = XmlManager.MakeXmlIn(user, "GETARVOFERTA", "<getreg>GETARVOFERTA</getreg>");

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			return ItemArvoreVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getItemArvoreVO();

		} catch (TuxedoServiceCallerException tex) {
			log.error("TuxedoException - RalizarRetencaoFacadeImpl:getArvore(" + user + ") - [" + tex.getMessage() + "]");
			throw new TuxedoException(tex);

		} catch (XmlException xex) {
			log.error("XmlException - RalizarRetencaoFacadeImpl:getArvore(" + user + ") - [" + xex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RalizarRetencaoFacadeImpl:getArvore", xex));

		} catch (Exception ex) {
			log.error("Exception - RalizarRetencaoFacadeImpl:getArvore(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public FidelizacaoListaGeralVO getIntencaoCancelamento(String user, String IdUF, String sgTipoPessoa, String idSegmentacao, String idGrupo, String idTipoLinha) throws FacadeException, TuxedoException {
		try {
			String xmlIN = XmlManager.MakeXmlIn(user, "SELDSTINT", "<idperg>1</idperg><idUfOperadora>" + IdUF + "</idUfOperadora><sgTipoPessoa>" + sgTipoPessoa + "</sgTipoPessoa><idSegmentacao>" + idSegmentacao + "</idSegmentacao><idGrupo>" + idGrupo + "</idGrupo><texto>*</texto><destinoObrigatorio>1</destinoObrigatorio><idTipoLinha>" + idTipoLinha + "</idTipoLinha>");

			String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			return FidelizacaoListaGeralVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getFidelizacaoListaGeralVO();

		} catch (TuxedoServiceCallerException tex) {
			log.error("/RalizarRetencaoFacadeImpl:getIntencaoCancelamento(" + user + ") - [" + tex.getMessage() + "]", tex);
			throw new TuxedoException(tex);

		} catch (XmlException xex) {
			log.error("XmlException - RalizarRetencaoFacadeImpl:getIntencaoCancelamento(" + user + ") - [" + xex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RalizarRetencaoFacadeImpl:getIntencaoCancelamento", xex));

		} catch (Exception ex) {
			log.error("Exception - RalizarRetencaoFacadeImpl:getIntencaoCancelamento(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public FidelizacaoListaGeralVO getLigacaoIndevida(String user) throws FacadeException, XmlException, TuxedoException {
		try {
			String xmlIN = XmlManager.MakeXmlIn(user, "SELTPLIGIND", ConstantesCRM.SVAZIO);

			String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			return FidelizacaoListaGeralVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getFidelizacaoListaGeralVO();

		} catch (XmlException xex) {
			log.error("XmlException - RalizarRetencaoFacadeImpl:getLigacaoIndevida(" + user + ") - [" + xex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RalizarRetencaoFacadeImpl:getLigacaoIndevida", xex));

		} catch (Exception ex) {
			log.error("Exception - RalizarRetencaoFacadeImpl:getLigacaoIndevida(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public FidelizacaoListaGeralVO getDestinosPrevistos(String user, String idUfOperadora, String idIntencao, String sgTipoPessoa, String idSegmentacao, String idGrupo, String idTipoLinha) throws FacadeException, TuxedoException {
		try {
			String xmlIN = XmlManager.MakeXmlIn(user, "SELDSTPREV", new StringBuffer("<idUfOperadora>").append(idUfOperadora).append("</idUfOperadora>").append("<idIntencao>").append(idIntencao).append("</idIntencao>").append("<sgTipoPessoa>").append(sgTipoPessoa).append("</sgTipoPessoa>").append("<idSegmentacao>").append(idSegmentacao).append("</idSegmentacao>").append("<idGrupo>").append(idGrupo).append("</idGrupo>").append("<idTipoLinha>").append(idTipoLinha).append("</idTipoLinha>")
					.toString());

			String xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			return FidelizacaoListaGeralVODocument.Factory.parse(MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText()).getFidelizacaoListaGeralVO();

		} catch (TuxedoServiceCallerException tex) {
			log.error("RalizarRetencaoFacadeImpl:getDestinosPrevistos(" + user + ") - [" + tex.getMessage() + "]", tex);
			throw new TuxedoException(tex);

		} catch (XmlException xex) {
			log.error("XmlException - RalizarRetencaoFacadeImpl:getDestinosPrevistos(" + user + ") - [" + xex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RalizarRetencaoFacadeImpl:getDestinosPrevistos", xex));

		} catch (Exception ex) {
			log.error("Exception - RalizarRetencaoFacadeImpl:getDestinosPrevistos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

}
