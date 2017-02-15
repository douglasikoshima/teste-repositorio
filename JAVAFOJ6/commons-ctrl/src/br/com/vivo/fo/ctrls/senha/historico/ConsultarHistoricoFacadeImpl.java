package br.com.vivo.fo.ctrls.senha.historico;

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
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.senha.vo.SenhaMovimentosVODocument;
import br.com.vivo.fo.senha.vo.SenhaMovimentosVODocument.SenhaMovimentosVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name="ConsultarHistoricoFacade",mappedName="ConsultarHistoricoFacade")
@Local(ConsultarHistoricoFacade.class)
@Session(ejbName = "ConsultarHistoricoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ConsultarHistoricoFacadeImpl implements ConsultarHistoricoFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	static final long serialVersionUID = 1L;

	private static Logger log = new Logger("senha");

	private transient String xmlIN = ConstantesCRM.SVAZIO;
	private transient String xmlOUT = ConstantesCRM.SVAZIO;

	// private final String TUX_SERVICE = "CustomService";

	public final String TP_CLIENTE = "2";
	public final String TP_USUARIO = "1";

	/**
	 * @common:operation
	 */
	public SenhaMovimentosVO ConsHistorico(String inPesquisa, String foneArea, String foneNumero, String idPessoa, String titularidadeSenha, String idPessoaCli, String idPessoaUsr, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("ConsultarHistoricoFacadeImpl:ConsHistorico(" + foneArea + ", " + foneNumero + ", " + idPessoa + ", " + titularidadeSenha + ", " + user + ")");

			xmlIN = this.getXmlConsultarHistorico(inPesquisa, foneArea, foneNumero, idPessoa, titularidadeSenha, idPessoaCli, idPessoaUsr);
			xmlIN = XmlManager.MakeXmlIn(user, "ConsHistorico", xmlIN);
			// xmlOUT = (new ControlTuxedoCall()).execute(this, senhaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return SenhaMovimentosVODocument.Factory.parse(xmlOUT).getSenhaMovimentosVO();

		} catch (XmlException ex) {
			log.error("XmlException - ConsultarHistoricoFacadeImpl:ConsHistorico(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ConsultarHistoricoFacadeImpl:ConsHistorico", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ConsultarHistoricoFacadeImpl:ConsHistorico(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ConsultarHistoricoFacadeImpl:ConsHistorico(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ConsultarHistoricoFacadeImpl:ConsHistorico", ex));
		}
	}

	private String getXmlConsultarHistorico(String inPesquisa, String foneArea, String foneNumero, String idPessoa, String titularidadeSenha, String idPessoaCli, String idPessoaUsr) {

		StringBuffer xmlIN = new StringBuffer();

		// Quando o fone tem 7 digitos, incluir um 0 na área, ou seja, 0dd+pppssss
		// onde: dd = dois digitos do ddd (ex.:11); ppp = 3 digitos do prefixo e
		// ssss = 4 digitos do sufixo
		// Se o telefone tem 8 digitos, então fica somente 2 digitos da área.
		String fone = ("0" + foneArea + foneNumero);
		fone = fone.substring(fone.length() - 10, fone.length());

		// A URA deverá passar 1 - cliente, 2 - usuário, por isso a conversão.
		String titularidade = titularidadeSenha.equals(this.TP_CLIENTE) ? "C" : "U";

		xmlIN.append("");
		xmlIN.append("  <indPesquisa>").append(inPesquisa).append("</indPesquisa>");
		xmlIN.append("  <telefone>").append(fone).append("</telefone>");
		xmlIN.append("  <idPessoa>").append(idPessoa).append("</idPessoa>");
		xmlIN.append("  <titularidadeSenha>").append(titularidade).append("</titularidadeSenha>");
		xmlIN.append("  <idPessoaCli>").append(idPessoaCli).append("</idPessoaCli>");
		xmlIN.append("  <idPessoaUsr>").append(idPessoaUsr).append("</idPessoaUsr>");
		xmlIN.append("");

		return xmlIN.toString();
	}

}
