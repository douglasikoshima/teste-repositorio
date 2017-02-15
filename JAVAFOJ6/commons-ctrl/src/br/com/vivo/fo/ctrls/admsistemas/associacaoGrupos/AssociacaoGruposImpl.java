package br.com.vivo.fo.ctrls.admsistemas.associacaoGrupos;

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
import br.com.vivo.fo.admsistemas.vo.AssosiacaoGrupoVariaveisVODocument;
import br.com.vivo.fo.admsistemas.vo.AssosiacaoGrupoVariaveisVODocument.AssosiacaoGrupoVariaveisVO;
import br.com.vivo.fo.admsistemas.vo.GruposProcessosVODocument;
import br.com.vivo.fo.admsistemas.vo.GruposProcessosVODocument.GruposProcessosVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO;

@Stateless(name = "AssociacaoGrupos", mappedName = "AssociacaoGrupos")
@Local(AssociacaoGrupos.class)
@Session(ejbName = "AssociacaoGrupos", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AssociacaoGruposImpl implements AssociacaoGrupos {

	@EJB
	private TuxedoServiceCall tuxedo;

	private static Logger log = new Logger("admsistemas");

	/**
	 * @common:operation
	 */
	public WFAcaoVO[] getWFAcaoVO(String user, String idContato) throws TuxedoException, FacadeException {
		try {
			log.debug("AssociacaoGruposImpl:getWFAcaoVO(" + user + "," + idContato + ")");

			StringBuffer xmlIn = new StringBuffer(ConstantesCRM.SVAZIO);
			xmlIn.append("<idContato>" + idContato + "</idContato>");

			String inService = TuxedoServiceBridge.getXMLRequest(user, "CONSGRPABERT", xmlIn.toString());
			String xmlOut = ConstantesCRM.SVAZIO;// admSistemasTux.GETSERVICE(inService);

			xmlOut = tuxedo.callService("TuxConnector", inService);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

			return AtendimentoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getAtendimentoVO().getWFAcaoVOArray();

		} catch (XmlException ex) {
			log.error("XmlException - AssociacaoGruposImpl:getWFAcaoVO(" + user + ", String idContato) - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AssociacaoGruposImpl:getWFAcaoVO", ex));

		} catch (Exception ex) {
			log.error("Exception - AssociacaoGruposImpl:getWFAcaoVO(" + user + ", String idContato) - [" + ex.getMessage() + "]");
			throw new FacadeException(ex);
		}
	}

	/**
	 * @common:operation
	 */
	public GruposProcessosVO getGruposProcessos(String user, String contato, String acao) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("AssociacaoGruposImpl:getGruposProcessos(").append(user).append(", ").append(contato).append(", ").append(acao).append(")").toString());
			String xmlEntrada = "<acao>" + acao + "</acao><contato>" + contato + "</contato>";

			String inService = TuxedoServiceBridge.getXMLRequest(user,"CNSGRPDSPASS",xmlEntrada);
			String xmlOut = ConstantesCRM.SVAZIO;// admSistemasTux.GETSERVICE(inService);

			xmlOut = tuxedo.callService("TuxConnector", inService);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

			return GruposProcessosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getGruposProcessosVO();

		} catch (XmlException ex) {
			log.error("XmlException - AssociacaoGruposImpl:getGruposProcessos(" + user + ", String contato, String acao) - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada. AssociacaoGruposImpl:getGruposProcessos", ex));

		} catch (Exception ex) {
			log.error("Exception - AssociacaoGruposImpl:getGruposProcessos(" + user + ", String contato, String acao) - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AssosiacaoGrupoVariaveisVO getAssosiacaoGrupoVariaveisVO(String user, String contato, String grupo, String fechamento) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("AssociacaoGruposImpl:getAssosiacaoGrupoVariaveisVO(").append(user).append(", ").append(contato).append(", ").append(grupo).append(", ").append(fechamento).append(")").toString());

			String xmlEntrada = "<grupo>" + grupo + "</grupo><contato>" + contato + "</contato><fechamento>" + fechamento + "</fechamento>";

			String inService = TuxedoServiceBridge.getXMLRequest(user,"CONSGRPABERT",xmlEntrada);
			String xmlOut = ConstantesCRM.SVAZIO;// admSistemasTux.GETSERVICE(inService);

			xmlOut = tuxedo.callService("TuxConnector", inService);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

			AssosiacaoGrupoVariaveisVODocument varDoc = AssosiacaoGrupoVariaveisVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText());
			return varDoc.getAssosiacaoGrupoVariaveisVO();

		} catch (XmlException ex) {
			log.error("XmlException - AssociacaoGruposImpl:getAssosiacaoGrupoVariaveisVO(" + user + ", String contato, String grupo, String fechamento) - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AssociacaoGruposImpl:getAssosiacaoGrupoVariaveisVO", ex));

		} catch (Exception ex) {
			log.error("Exception - AssociacaoGruposImpl:getAssosiacaoGrupoVariaveisVO(" + user + ", String contato, String grupo, String fechamento) - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFAcaoRetornoVO setGruposAssociados(String user, String xmlEntrada) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("AssociacaoGruposImpl:setGruposAssociados(").append(user).append(",").append(xmlEntrada).append(")").toString());

			String inService = TuxedoServiceBridge.getXMLRequest(user,"ATUFLXFASEGRP",xmlEntrada);
			String xmlOut = ConstantesCRM.SVAZIO;// admSistemasTux.GETSERVICE(inService);
			xmlOut = tuxedo.callService("TuxConnector", inService);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

			return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();

		} catch (XmlException ex) {
			log.error("XmlException - AssociacaoGruposImpl:setGruposAssociados(" + user + ", String xmlEntrada) - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: AssociacaoGruposImpl:setGruposAssociados", ex));

		} catch (Exception ex) {
			log.error("Exception - AssociacaoGruposImpl:setGruposAssociados(" + user + ", String xmlEntrada) - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}
}
