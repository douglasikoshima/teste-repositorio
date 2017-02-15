package br.com.vivo.fo.ctrls.fidelizacao.realizarRetencao;

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
import br.com.vivo.fo.commons.utils.geral.ControlXMLExceptionLookup;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.fidelizacao.vo.ListaOfertaExecaoVODocument;
import br.com.vivo.fo.fidelizacao.vo.ListaOfertaExecaoVODocument.ListaOfertaExecaoVO;
import br.com.vivo.fo.fidelizacao.vo.OfertaExcecaoDetalheVODocument;
import br.com.vivo.fo.fidelizacao.vo.OfertaExcecaoDetalheVODocument.OfertaExcecaoDetalheVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.RetornoTuxedo;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@SuppressWarnings("deprecation")
@Stateless(name="ConsultarOfertaExecaoFacade",mappedName="ConsultarOfertaExecaoFacade")
@Local(ConsultarOfertaExecaoFacade.class)
@Session(ejbName = "ConsultarOfertaExecaoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ConsultarOfertaExecaoFacadeImpl implements
		ConsultarOfertaExecaoFacade {
	/**
	 * @common:control
	 */
	@EJB
	private TuxedoServiceCall tuxedo;

	static final long serialVersionUID = 1L;

	private String xmlIn = ConstantesCRM.SVAZIO;
	private String xmlOut = ConstantesCRM.SVAZIO;
	private static Logger log = new Logger("fidelizacao");
	private String[] retTux = new String[0];

	/**
	 * @common:operation
	 */
	public ListaOfertaExecaoVO getLista(String user) throws TuxedoException,
			FacadeException {
		ListaOfertaExecaoVO lista = null;
		xmlIn = ConstantesCRM.SVAZIO;
		xmlOut = ConstantesCRM.SVAZIO;
		try {
			xmlIn = "<getreg>SELEXCECAO</getreg>";
			xmlIn = XmlManager.MakeXmlIn(user, "SELEXCECAO", xmlIn);
			
			try {
				xmlOut = ConstantesCRM.SVAZIO;// fidelizaTux.GETSERVICE(xmlIn);
				xmlOut = tuxedo.callService("TuxConnector", xmlIn);

				MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
				xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();
			} 
			
			catch (Exception ex) {
				xmlOut = ControlXMLExceptionLookup.getXMLString(ex);
			}
			
			retTux = XmlManager.ParseXmlOut(xmlOut);
			RetornoTuxedo.TrataCodigoExecucao(getClass(), retTux);
			
			lista = ListaOfertaExecaoVODocument.Factory.parse(xmlOut).getListaOfertaExecaoVO();
		
			return lista;

		} catch (TuxedoException ex) {
			log.error("TuxedoException - ConsultarOfertaExcecaoFacadeImpl:getLista("
					+ user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		} catch (XmlException ex) {
			log.error("XmlException - ConsultarOfertaExcecaoFacadeImpl:getLista("
					+ user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(
					"Erro na montagem do XML de entrada: ConsultarOfertaExcecaoFacadeImpl:getLista",
					ex));

		} catch (Exception ex) {
			log.error("Exception - ConsultarOfertaExcecaoFacadeImpl:getLista("
					+ user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void setLista(String user, String[] id) throws FacadeException,
			TuxedoException {
		xmlIn = ConstantesCRM.SVAZIO;
		xmlOut = ConstantesCRM.SVAZIO;
		try {
			for (int i = 0; i < id.length; i++) {
				xmlIn += "<id>" + id[i] + "</id>";
			}
			xmlIn = XmlManager.MakeXmlIn(user, "VISTOEXCECAO", xmlIn);
			try {
				xmlOut = ConstantesCRM.SVAZIO;// fidelizaTux.GETSERVICE(xmlIn);
				xmlOut = tuxedo.callService("TuxConnector", xmlIn);
			} catch (Exception ex) {
				xmlOut = ControlXMLExceptionLookup.getXMLString(ex);
			}
			retTux = XmlManager.ParseXmlOut(xmlOut);
			RetornoTuxedo.TrataCodigoExecucao(getClass(), retTux);

		} catch (TuxedoException ex) {
			log.error("TuxedoException - ConsultarOfertaExcecaoFacadeImpl:setLista("
					+ user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		} catch (XmlException ex) {
			log.error("XmlException - ConsultarOfertaExcecaoFacadeImpl:setLista("
					+ user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(
					"Erro na montagem do XML de entrada: ConsultarOfertaExcecaoFacadeImpl:setLista",
					ex));
		} catch (Exception ex) {
			log.error("Exception - ConsultarOfertaExcecaoFacadeImpl:setLista("
					+ user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public OfertaExcecaoDetalheVO getDetalheLista(String user, String dados)
			throws TuxedoException, FacadeException {
		OfertaExcecaoDetalheVO lista = null;
		xmlIn = ConstantesCRM.SVAZIO;
		xmlOut = ConstantesCRM.SVAZIO;
		try {
			xmlIn = "<idOfertaAceita>" + dados + "</idOfertaAceita>";
			xmlIn = XmlManager.MakeXmlIn(user, "SELDETEXCECAO", xmlIn);
			try {
				xmlOut = ConstantesCRM.SVAZIO;// fidelizaTux.GETSERVICE(xmlIn);
				xmlOut = tuxedo.callService("TuxConnector", xmlIn);

				MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
				xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();
			} catch (Exception ex) {
				xmlOut = ControlXMLExceptionLookup.getXMLString(ex);
			}
			
			retTux = XmlManager.ParseXmlOut(xmlOut);
			RetornoTuxedo.TrataCodigoExecucao(getClass(), retTux);
			
			lista = OfertaExcecaoDetalheVODocument.Factory.parse(retTux[4])
					.getOfertaExcecaoDetalheVO();
			
			return lista;

		} catch (TuxedoException ex) {
			log.error("TuxedoException - ConsultarOfertaExcecaoFacadeImpl:getLista("
					+ user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		} catch (XmlException ex) {
			log.error("XmlException - ConsultarOfertaExcecaoFacadeImpl:getLista("
					+ user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(
					"Erro na montagem do XML de entrada: ConsultarOfertaExcecaoFacadeImpl:getLista",
					ex));

		} catch (Exception ex) {
			log.error("Exception - ConsultarOfertaExcecaoFacadeImpl:getLista("
					+ user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}
}
