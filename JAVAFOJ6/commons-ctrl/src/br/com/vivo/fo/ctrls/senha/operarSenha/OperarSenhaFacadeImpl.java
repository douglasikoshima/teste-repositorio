package br.com.vivo.fo.ctrls.senha.operarSenha;

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
import br.com.vivo.fo.exception.WarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.senha.vo.SenhaOperarVODocument;
import br.com.vivo.fo.senha.vo.SenhaOperarVODocument.SenhaOperarVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name="OperarSenhaFacade",mappedName="OperarSenhaFacade")
@Local(OperarSenhaFacade.class)
@Session(ejbName = "OperarSenhaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class OperarSenhaFacadeImpl implements OperarSenhaFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	static final long serialVersionUID = 1L;

	private static Logger log = new Logger("senha");

	private transient String xmlIN = ConstantesCRM.SVAZIO;
	private transient String xmlOUT = ConstantesCRM.SVAZIO;

	// private final int RET_POS_SUB_SYSTEM = 0;
	// private final int RET_POS_TYPE = 1;
	// private final int RET_POS_STATUS_CODE = 2;
	// private final int RET_POS_MESSAGE = 3;
	// private final int RET_POS_XML_OUT = 4;
	// private final String RET_TYPE_ERROR = "E";
	// private final String RET_TYPE_WARNING = "W";
	// private final String RET_TYPE_INFORMATION = "I";
	public final String TP_CLIENTE = "2";
	public final String TP_USUARIO = "1";

	/**
	 * @common:operation
	 */
	public void GeraSenha(String idPessoa, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("OperarSenhaFacadeImpl:GeraSenha(" + idPessoa + ", " + user + ")");

			xmlIN = this.getXmlGeraSenha(idPessoa);
			xmlIN = XmlManager.MakeXmlIn(user, "GeraSenha", xmlIN);
			//(new ControlTuxedoCall()).execute(this, senhaTux, "GETSERVICE", xmlIN);
			tuxedo.callService("TuxConnector", xmlIN);

		} catch (XmlException ex) {
			log.error("XmlException - OperarSenhaFacadeImpl:GeraSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OperarSenhaFacadeImpl:GeraSenha", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - OperarSenhaFacadeImpl:GeraSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - OperarSenhaFacadeImpl:GeraSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw new FacadeException("Erro na montagem do XML de entrada: OperarSenhaFacadeImpl:GeraSenha", ex);
		}
	}

	/**
	 * @common:operation
	 */
	public SenhaOperarVO solicitarSenha(String indFrase, String foneArea, String foneNumero, String idPessoa, String idPessoaUsr, String titularidadeSenha, String senha, String idCanal, String motivo, String user) throws TuxedoException, FacadeException, WarningException {
		try {
			log.debug("OperarSenhaFacadeImpl:solicitarSenha(" + indFrase + ", " + foneArea + ", " + foneNumero + ", " + idPessoa + ", " + idPessoaUsr + ", " + titularidadeSenha + ", " + senha + ", " + idCanal + ", " + motivo + ", " + user + ")");

			StringBuffer xmlIN = new StringBuffer();

			if (foneNumero != null && foneNumero.length() > 2) {
				foneNumero = foneNumero.substring(2);
			}

			xmlIN.append("<nrLinha>").append(foneNumero).append("</nrLinha>").append("<cdAreaRegistro>").append(foneArea).append("</cdAreaRegistro>").append("<idTipoRelacionamento>").append(titularidadeSenha).append("</idTipoRelacionamento>").append("<idCanal>1</idCanal>");

			String xml = ConstantesCRM.SVAZIO;

			// xmlIN = this.getXmlReinicializarSenha(indFrase, foneArea, foneNumero, idPessoa, idPessoaUsr,
			// titularidadeSenha, senha, idCanal, motivo);
			xml = XmlManager.MakeXmlIn(user, "SOLICITARSENHA", xmlIN.toString());
			//xmlOUT = (new ControlTuxedoCall()).execute(this, senhaTux, "GETSERVICE", xml);
			xmlOUT = tuxedo.callService("TuxConnector", xml);
			
			// xml = XmlManager.MakeXmlIn(user, "SOLICITARSENHA", xmlIN.toString());
			// String xmlOUT = senhaTux.SOLICITARSENHA(xml);

			//MsgDocument msgDocRet=MsgDocument.Factory.parse(xmlOUT);
			SenhaOperarVO senhaOperarVo = SenhaOperarVODocument.Factory.newInstance().addNewSenhaOperarVO();

			senhaOperarVo.setOperacaoRealizada(true);
			senhaOperarVo.setMensagem("Sua senha será enviada por mensagem de texto (SMS) ao seu celular. Para o recebimento deste SMS é necessário que o seu celular esteja ligado, em uma área de cobertura digital e habilitado para o recebimento de mensagens de texto SMS.");

			return senhaOperarVo;

		} catch (XmlException ex) {
			log.error("XmlException - OperarSenhaFacadeImpl:solicitarSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OperarSenhaFacadeImpl:solicitarSenha", ex));

		} catch (TuxedoServiceCallerException ex) {
			TuxedoException e = new TuxedoException(ex);
			if ("07E0003".equalsIgnoreCase(e.getXmlHeader().getStatusCode())) {
				throw (new WarningException(e.getXmlHeader().getStatusText()));
			}
			log.error("TuxedoException - OperarSenhaFacadeImpl:solicitarSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw (e);

		} catch (Exception ex) {
			log.error("Exception - OperarSenhaFacadeImpl:solicitarSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Serviço indisponível, tente mais tarde.", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public SenhaOperarVO ReiniciaSenha(String indFrase, String foneArea, String foneNumero, String idPessoa, String idPessoaUsr, String titularidadeSenha, String senha, String idCanal, String motivo, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("OperarSenhaFacadeImpl:ReiniciaSenha(" + indFrase + ", " + foneArea + ", " + foneNumero + ", " + idPessoa + ", " + idPessoaUsr + ", " + titularidadeSenha + ", " + senha + ", " + idCanal + ", " + motivo + ", " + user + ")");

			String xmlIN = this.getXmlReinicializarSenha(indFrase, foneArea, foneNumero, idPessoa, idPessoaUsr, titularidadeSenha, senha, idCanal, motivo);
			xmlIN = XmlManager.MakeXmlIn(user, "ReiniciaSenha", xmlIN);
			String xmlOUT = ConstantesCRM.SVAZIO;// senhaTux.GETSERVICE(xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			SenhaOperarVO senhaOperarVo = SenhaOperarVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getSenhaOperarVO();

			senhaOperarVo.setOperacaoRealizada(true);

			return senhaOperarVo;

		} catch (XmlException ex) {
			log.error("XmlException - OperarSenhaFacadeImpl:ReiniciaSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OperarSenhaFacadeImpl:ReiniciaSenha", ex));

		} catch (Exception ex) {
			log.error("Exception - OperarSenhaFacadeImpl:ReiniciaSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OperarSenhaFacadeImpl:ReiniciaSenha", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void AlteraSenha(String foneArea, String foneNumero, String idPessoa, String titularidadeSenha, String senha, String idCanal, String motivo, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("OperarSenhaFacadeImpl:AlteraSenha(" + foneArea + ", " + foneNumero + ", " + idPessoa + ", " + titularidadeSenha + ", " + senha + ", " + idCanal + ", " + motivo + ", " + user + ")");

			xmlIN = this.getXmlAlteraSenha(foneArea, foneNumero, idPessoa, titularidadeSenha, senha, idCanal, motivo);
			xmlIN = XmlManager.MakeXmlIn(user, "AlteraSenha", xmlIN);

			//(new ControlTuxedoCall()).execute(this, senhaTux, "GETSERVICE", xmlIN);
			tuxedo.callService("TuxConnector", xmlIN);

		} catch (XmlException ex) {

			log.error("XmlException - OperarSenhaFacadeImpl:AlteraSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw new FacadeException("Erro na montagem do XML de entrada: OperarSenhaFacadeImpl:AlteraSenha", ex);

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - OperarSenhaFacadeImpl:AlteraSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - OperarSenhaFacadeImpl:AlteraSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw new FacadeException("Erro na montagem do XML de entrada: OperarSenhaFacadeImpl:AlteraSenha", ex);
		}
	}

	/**
	 * @common:operation
	 */
	public String ValidaSenha(String foneArea, String foneNumero, String senha, String idCanal, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("OperarSenhaFacadeImpl:ValidaSenha(" + foneArea + ", " + foneNumero + ", " + senha + ", " + idCanal + ", " + user + ")");

			xmlIN = this.getXmlValidaSenha(foneArea, foneNumero, senha, idCanal);
			xmlIN = XmlManager.MakeXmlIn(user, "ValidaSenha", xmlIN);
			
			//xmlOUT = (new ControlTuxedoCall()).execute(this, senhaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return xmlOUT;

		} catch (XmlException ex) {
			log.error("XmlException - OperarSenhaFacadeImpl:ValidaSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OperarSenhaFacadeImpl:ValidaSenha", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - OperarSenhaFacadeImpl:ValidaSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - OperarSenhaFacadeImpl:ValidaSenha(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OperarSenhaFacadeImpl:ValidaSenha", ex));
		}
	}

	private String getXmlGeraSenha(String idPessoa) {
		StringBuffer xmlIN = new StringBuffer();

		xmlIN.append("");
		xmlIN.append("  <idPessoa>").append(idPessoa).append("</idPessoa>");
		xmlIN.append("");

		return xmlIN.toString();
	}

	private String getXmlReinicializarSenha(String indFrase, String foneArea, String foneNumero, String idPessoa, String idPessoaUsr, String titularidadeSenha, String senha, String idCanal, String motivo) {

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
		xmlIN.append("  <indFrase>").append(indFrase).append("</indFrase>");
		xmlIN.append("  <telefone>").append(fone).append("</telefone>");
		xmlIN.append("  <idPessoa>").append(idPessoa).append("</idPessoa>");
		xmlIN.append("  <idPessoaUsr>").append(idPessoaUsr).append("</idPessoaUsr>");
		xmlIN.append("  <titularidadeSenha>").append(titularidade).append("</titularidadeSenha>");
		xmlIN.append("  <cdSenha>").append(senha).append("</cdSenha>");
		xmlIN.append("  <idCanal>").append(idCanal).append("</idCanal>");
		xmlIN.append("  <obsRegistro>").append(motivo).append("</obsRegistro>");
		xmlIN.append("");

		return xmlIN.toString();
	}

	private String getXmlAlteraSenha(String foneArea, String foneNumero, String idPessoa, String titularidadeSenha, String senha, String idCanal, String motivo) {

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
		xmlIN.append("  <telefone>").append(fone).append("</telefone>");
		xmlIN.append("  <idPessoa>").append(idPessoa).append("</idPessoa>");
		xmlIN.append("  <titularidadeSenha>").append(titularidade).append("</titularidadeSenha>");
		xmlIN.append("  <cdSenha>").append(senha).append("</cdSenha>");
		xmlIN.append("  <idCanal>").append(idCanal).append("</idCanal>");
		xmlIN.append("  <obsRegistro>").append(motivo).append("</obsRegistro>");
		xmlIN.append("");

		return xmlIN.toString();
	}

	private String getXmlValidaSenha(String foneArea, String foneNumero, String senha, String idCanal) {

		StringBuffer xmlIN = new StringBuffer();

		// Quando o fone tem 7 digitos, incluir um 0 na área, ou seja, 0dd+pppssss
		// onde: dd = dois digitos do ddd (ex.:11); ppp = 3 digitos do prefixo e
		// ssss = 4 digitos do sufixo
		// Se o telefone tem 8 digitos, então fica somente 2 digitos da área.
		String fone = ("0" + foneArea + foneNumero);
		fone = fone.substring(fone.length() - 10, fone.length());

		xmlIN.append("");
		xmlIN.append("  <telefone>").append(fone).append("</telefone>");
		xmlIN.append("  <cdSenha>").append(senha).append("</cdSenha>");
		xmlIN.append("  <idCanal>").append(idCanal).append("</idCanal>");
		xmlIN.append("");

		return xmlIN.toString();
	}
}
