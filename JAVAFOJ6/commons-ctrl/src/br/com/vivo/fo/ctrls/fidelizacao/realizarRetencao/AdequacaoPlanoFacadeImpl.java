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
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.RetornoTuxedo;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@SuppressWarnings("deprecation")
@Stateless(name="AdequacaoPlanoFacade",mappedName="AdequacaoPlanoFacade")
@Local(AdequacaoPlanoFacade.class)
@Session(ejbName = "AdequacaoPlanoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class AdequacaoPlanoFacadeImpl implements AdequacaoPlanoFacade {
	/**
	 * @common:control
	 */
	@EJB
	private TuxedoServiceCall tuxedo;

	static final long serialVersionUID = 1L;
	private String xmlIn;
	private String xmlOut;
	private String[] retTux = new String[0];
	private static Logger log = new Logger("fidelizacao");

	/**
	 * @common:operation
	 */
	public FidelizacaoListaGeralVO getPlanos(String user, String idLinha) throws FacadeException, TuxedoException {
		FidelizacaoListaGeralVO lista = null;
		xmlIn = ConstantesCRM.SVAZIO;
		xmlOut = ConstantesCRM.SVAZIO;
		try {
			xmlIn = "<idLinha>" + idLinha + "</idlinha>";
			xmlIn = XmlManager.MakeXmlIn(user, "SELPLANO", xmlIn);
			
			// xmlOut = adequacaoPlanoTux.getPlanos(xmlIn);
			xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();
			
			retTux = XmlManager.ParseXmlOut(xmlOut);
			RetornoTuxedo.TrataCodigoExecucao(getClass(), retTux);
			
			lista = FidelizacaoListaGeralVODocument.Factory.parse(xmlOut).getFidelizacaoListaGeralVO();
			return lista;

		} catch (XmlException ex) {
			log.error("XmlException - AdequacaoPlanoFacadeImpl:getLista("
					+ user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(
					"Erro na montagem do XML de entrada: AdequacaoPlanoFacadeImpl:getLista",
					ex));

		} catch (TuxedoException ex) {
			log.error("TuxedoException - AdequacaoPlanoFacadeImpl:getLista("
					+ user + ") - [" + ex.getMessage() + "]");
			throw (ex);

		} catch (Exception ex) {
			log.error("Exception - AdequacaoPlanoFacadeImpl:getLista(" + user
					+ ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void setPlano(String user, String[] param) throws FacadeException,
			TuxedoException {
		xmlIn = ConstantesCRM.SVAZIO;
		xmlOut = ConstantesCRM.SVAZIO;
		try {
			xmlIn = "<idCliente>" + param[0] + "</idCliente><idLinha>"
					+ param[1] + "</idLinha><idPlano>" + param[2]
					+ "</idPlano>";
			xmlIn = XmlManager.MakeXmlIn(user, "UPDPLANO", xmlIn);
			// xmlout = AdequacaoPlanoTuxç.setPlano(xmlIn);
			xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			retTux = XmlManager.ParseXmlOut(xmlOut);
			RetornoTuxedo.TrataCodigoExecucao(getClass(), retTux);

		} catch (XmlException ex) {
			log.error("XmlException - AdequacaoPlanoFacadeImpl:setPlano("
					+ user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(
					"Erro na montagem do XML de entrada: AdequacaoPlanoFacadeImpl:setPlano",
					ex));

		} catch (TuxedoException ex) {
			log.error("TuxedoException - AdequacaoPlanoFacadeImpl:setPlano("
					+ user + ") - [" + ex.getMessage() + "]");
			throw (ex);

		} catch (Exception ex) {
			log.error("Exception - AdequacaoPlanoFacadeImpl:setPlano(" + user
					+ ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}
}
