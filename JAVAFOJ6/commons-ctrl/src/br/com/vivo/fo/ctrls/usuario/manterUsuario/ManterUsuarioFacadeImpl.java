package br.com.vivo.fo.ctrls.usuario.manterUsuario;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import noNamespace.MsgDocument;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.xmlbeans.XmlException;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.ListaCargoPorNivelVODocument;
import br.com.vivo.fo.usuario.vo.ListaCargoPorNivelVODocument.ListaCargoPorNivelVO;
import br.com.vivo.fo.usuario.vo.ListaUnidadePorOrganizacaoVODocument;
import br.com.vivo.fo.usuario.vo.ListaUnidadePorOrganizacaoVODocument.ListaUnidadePorOrganizacaoVO;
import br.com.vivo.fo.usuario.vo.ListaUsuariosVODocument;
import br.com.vivo.fo.usuario.vo.ListaUsuariosVODocument.ListaUsuariosVO;
import br.com.vivo.fo.usuario.vo.NivelCargoOrgDeptoRelacionamentoVODocument.NivelCargoOrgDeptoRelacionamentoVO;
import br.com.vivo.fo.usuario.vo.NivelCargoOrgVODocument;
import br.com.vivo.fo.usuario.vo.NivelCargoOrgVODocument.NivelCargoOrgVO;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioGrupoSimplVODocument.RelacionarUsuarioGrupoSimplVO;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioGrupoVODocument;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioGrupoVODocument.RelacionarUsuarioGrupoVO;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioOperadoraSimplVODocument.RelacionarUsuarioOperadoraSimplVO;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioOperadoraVODocument;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioOperadoraVODocument.RelacionarUsuarioOperadoraVO;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioPerfilSimplVODocument.RelacionarUsuarioPerfilSimplVO;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioPerfilVODocument;
import br.com.vivo.fo.usuario.vo.RelacionarUsuarioPerfilVODocument.RelacionarUsuarioPerfilVO;
import br.com.vivo.fo.usuario.vo.UsuarioIDVODocument.UsuarioIDVO;
import br.com.vivo.fo.usuario.vo.UsuarioLDAPVODocument;
import br.com.vivo.fo.usuario.vo.UsuarioLDAPVODocument.UsuarioLDAPVO;
import br.com.vivo.fo.usuario.vo.UsuarioSimplVODocument.UsuarioSimplVO;
import br.com.vivo.fo.usuario.vo.UsuarioVODocument.UsuarioVO;
import br.com.vivo.fo.usuario.vo.UsuariosVODocument;
import br.com.vivo.fo.usuario.vo.UsuariosVODocument.UsuariosVO;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name = "ManterUsuarioFacade", mappedName = "ManterUsuarioFacade")
@Local(ManterUsuarioFacade.class)
@Session(ejbName = "ManterUsuarioFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterUsuarioFacadeImpl implements ManterUsuarioFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	static final long serialVersionUID = 1L;
	private UsuariosVO usuariosVO = null;
	private RelacionarUsuarioGrupoVO relacionarUsuarioGrupoVO = null;
	private RelacionarUsuarioPerfilVO relacionarUsuarioPerfilVO = null;
	private RelacionarUsuarioOperadoraVO relacionarUsuarioOperadoraVO = null;

	private String xmlIN;
	private String xmlOUT;

	private static Logger log = new Logger("usuario");

	/**
	 * @common:operation
	 */
	public UsuariosVO listaSelectsUsuario(UsuarioSimplVO usuarioSimplVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("ManterUsuarioFacadeImpl:listaSelectsUsuario(" + user + ")");
			xmlIN = usuarioSimplVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "UsrLista", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			usuariosVO = UsuariosVODocument.Factory.parse(xmlOUT).getUsuariosVO();
			return usuariosVO;
		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioFacadeImpl:listaSelectsUsuario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:listaSelectsUsuario", ex));
		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioFacadeImpl:listaSelectsUsuario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public NivelCargoOrgVO listaOrganograma(NivelCargoOrgVO nivelCargoOrg, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("ManterUsuarioFacadeImpl:listaOrganograma(" + user + ")");

			xmlIN = nivelCargoOrg.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "PSSRELACAOHUP", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			NivelCargoOrgVO nivelCargoOrgVO = null;
			nivelCargoOrgVO = NivelCargoOrgVODocument.Factory.parse(xmlOUT).getNivelCargoOrgVO();

			return nivelCargoOrgVO;

		} catch (XmlException ex) {

			log.error("XmlException - ManterUsuarioFacadeImpl:listaOrganograma(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:listaOrganograma", ex));

		} catch (Exception ex) {

			log.error("Exception - ManterUsuarioFacadeImpl:listaOrganograma(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaCargoPorNivelVO listaCargoPorNivel(ListaCargoPorNivelVO listaCargoPorNivelVO, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("ManterUsuarioFacadeImpl:listaCargoPorNivel(" + user + ")");

			xmlIN = listaCargoPorNivelVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "NVLRELACAONC", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaCargoPorNivelVO listaCargo = null;
			listaCargo = ListaCargoPorNivelVODocument.Factory.parse(xmlOUT).getListaCargoPorNivelVO();

			return listaCargo;

		} catch (XmlException ex) {

			log.error("XmlException - ManterUsuarioFacadeImpl:listaCargoPorNivel(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:listaCargoPorNivel", ex));

		} catch (Exception ex) {

			log.error("Exception - ManterUsuarioFacadeImpl:listaCargoPorNivel(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaUnidadePorOrganizacaoVO listaUnidadePorOrganizacao(ListaUnidadePorOrganizacaoVO listaUnidadePorOrganizacaoVO, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("ManterUsuarioFacadeImpl:listaUnidadePorOrganizacao(" + user + ")");

			xmlIN = listaUnidadePorOrganizacaoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ORGRELACAOOU", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaUnidadePorOrganizacaoVO listaUnidadePorOrganizacao = null;
			listaUnidadePorOrganizacao = ListaUnidadePorOrganizacaoVODocument.Factory.parse(xmlOUT).getListaUnidadePorOrganizacaoVO();

			return listaUnidadePorOrganizacao;

		} catch (XmlException ex) {

			log.error("XmlException - ManterUsuarioFacadeImpl:listaUnidadePorOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:listaUnidadePorOrganizacao", ex));

		} catch (Exception ex) {

			log.error("Exception - ManterUsuarioFacadeImpl:listaUnidadePorOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaUsuariosVO pesquisaUsuarios(UsuarioSimplVO queryUsuario, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("ManterUsuarioFacadeImpl:pesquisaUsuarios(" + user + ")");

			xmlIN = queryUsuario.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "UsrListaPar", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaUsuariosVO listaUsuariosVO = null;
			listaUsuariosVO = ListaUsuariosVODocument.Factory.parse(xmlOUT).getListaUsuariosVO();

			return listaUsuariosVO;

		} catch (Exception ex) {

			log.error("Exception - ManterUsuarioFacadeImpl:pesquisaUsuarios(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public UsuarioLDAPVO salvaUsuario(UsuarioVO usuarioVO, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("ManterUsuarioFacadeImpl:salvaUsuario(" + user + ")");

			xmlIN = usuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "UsrInserir", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			if (xmlOUT.indexOf("08W000") >= 0) {
				int start = xmlOUT.indexOf("<statusText>")+12;
				int end = xmlOUT.indexOf("</statusText>");
				throw new TuxedoWarningException(xmlOUT.substring(start, end));
			} 

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			UsuarioLDAPVO usuarioLDAPVO = null;
			usuarioLDAPVO = UsuarioLDAPVODocument.Factory.parse(xmlOUT).getUsuarioLDAPVO();

			return usuarioLDAPVO;

		} catch (XmlException ex) {

			log.error("XmlException - ManterUsuarioFacadeImpl:salvaUsuario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:salvaUsuario", ex));
			
		} catch (TuxedoWarningException twe) {

			log.error("TuxedoWarningException - ManterUsuarioFacadeImpl:salvaUsuario(" + user + ") - [" + twe.getMessage() + "]");
			throw new TuxedoWarningException(twe.getMessage());

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - ManterUsuarioFacadeImpl:salvaUsuario(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - ManterUsuarioFacadeImpl:salvaUsuario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public void salvaHierarquiaDeptoPessoa(NivelCargoOrgDeptoRelacionamentoVO nivelCargoOrgDeptoRelacionamentoVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("ManterUsuarioFacadeImpl:salvaHierarquiaDeptoPessoa(" + user + ")");
			xmlIN = nivelCargoOrgDeptoRelacionamentoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "PSSRELACIONAHU", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			return;
		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioFacadeImpl:salvaHierarquiaDeptoPessoa(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:salvaHierarquiaDeptoPessoa", ex));
		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioFacadeImpl:salvaHierarquiaDeptoPessoa(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);
		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioFacadeImpl:salvaHierarquiaDeptoPessoa(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void salvarUsuarioEditado(UsuarioVO usuarioVO, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("ManterUsuarioFacadeImpl:salvarUsuarioEditado(" + user + ")");

			xmlIN = usuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "UsrEditar", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			if (xmlOUT.indexOf("08W00") >= 0) {
				int start = xmlOUT.indexOf("<statusText>")+12;
				int end = xmlOUT.indexOf("</statusText>");
				throw new TuxedoWarningException(xmlOUT.substring(start, end));
			}

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return;

		} catch (XmlException ex) {

			log.error("XmlException - ManterUsuarioFacadeImpl:salvarUsuarioEditado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:salvarUsuarioEditado", ex));

		} catch (TuxedoWarningException tex) {

			log.error("TuxedoWarningException - ManterUsuarioFacadeImpl:salvarUsuarioEditado(" + user + ") - [" + tex.getMessage() + "]");
			throw new TuxedoWarningException(tex.getMessage());
			
		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - ManterUsuarioFacadeImpl:salvarUsuarioEditado(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - ManterUsuarioFacadeImpl:salvarUsuarioEditado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public RelacionarUsuarioGrupoVO relacionaUsuarioGrupo(UsuarioIDVO usuarioVO, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("ManterUsuarioFacadeImpl:relacionaUsuarioGrupo(" + user + ")");

			xmlIN = usuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "UsrGrpRelacao", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			relacionarUsuarioGrupoVO = null;
			relacionarUsuarioGrupoVO = RelacionarUsuarioGrupoVODocument.Factory.parse(xmlOUT).getRelacionarUsuarioGrupoVO();

			return relacionarUsuarioGrupoVO;

		} catch (XmlException ex) {

			log.error("XmlException - ManterUsuarioFacadeImpl:relacionaUsuarioGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:relacionaUsuarioGrupo", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - ManterUsuarioFacadeImpl:relacionaUsuarioGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - ManterUsuarioFacadeImpl:relacionaUsuarioGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public RelacionarUsuarioGrupoVO salvaUsuarioGrupoRelacionado(RelacionarUsuarioGrupoSimplVO relacionaUsuarioGrupo, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("ManterUsuarioFacadeImpl:salvaUsuarioGrupoRelacionado(" + user + ")");

			xmlIN = relacionaUsuarioGrupo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "USRGRPRELACIO", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			if (xmlOUT.indexOf("08W0001") >= 0) {
				int start = xmlOUT.indexOf("<statusText>")+12;
				int end = xmlOUT.indexOf("</statusText>");
				throw new TuxedoWarningException(xmlOUT.substring(start, end));
			}

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			relacionarUsuarioGrupoVO = null;
			relacionarUsuarioGrupoVO = RelacionarUsuarioGrupoVODocument.Factory.parse(xmlOUT).getRelacionarUsuarioGrupoVO();

			return relacionarUsuarioGrupoVO;

		} catch (XmlException ex) {

			log.error("XmlException - ManterUsuarioFacadeImpl:salvaUsuarioGrupoRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:salvaUsuarioGrupoRelacionado", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - ManterUsuarioFacadeImpl:salvaUsuarioGrupoRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);
		
		} catch (TuxedoWarningException twex) {
			
			log.error("TuxedoException - ManterUsuarioFacadeImpl:salvaUsuarioGrupoRelacionado(" + user + ") - [" + twex.getMessage() + "]");
			throw new TuxedoWarningException(twex.getMessage());

		} catch (Exception ex) {
			
			log.error("Exception - ManterUsuarioFacadeImpl:salvaUsuarioGrupoRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}
	}

	/**
	 * @common:operation
	 */
	public RelacionarUsuarioPerfilVO relacionaUsuarioPerfil(UsuarioIDVO usuarioVO, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("ManterUsuarioFacadeImpl:relacionaUsuarioPerfil(" + user + ")");

			xmlIN = usuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "UsrPrfRelacao", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			relacionarUsuarioPerfilVO = null;
			relacionarUsuarioPerfilVO = RelacionarUsuarioPerfilVODocument.Factory.parse(xmlOUT).getRelacionarUsuarioPerfilVO();

			return relacionarUsuarioPerfilVO;

		} catch (XmlException ex) {

			log.error("XmlException - ManterUsuarioFacadeImpl:relacionaUsuarioPerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:relacionaUsuarioPerfil", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - ManterUsuarioFacadeImpl:relacionaUsuarioPerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - ManterUsuarioFacadeImpl:relacionaUsuarioPerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public RelacionarUsuarioPerfilVO salvaUsuarioPerfilRelacionado(RelacionarUsuarioPerfilSimplVO relacionaUsuarioPerfil, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("ManterUsuarioFacadeImpl:salvaUsuarioPerfilRelacionado(" + user + ")");

			xmlIN = relacionaUsuarioPerfil.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "USRPRFRELACIO", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			relacionarUsuarioPerfilVO = null;
			relacionarUsuarioPerfilVO = RelacionarUsuarioPerfilVODocument.Factory.parse(xmlOUT).getRelacionarUsuarioPerfilVO();

			return relacionarUsuarioPerfilVO;

		} catch (XmlException ex) {

			log.error("XmlException - ManterUsuarioFacadeImpl:salvaUsuarioPerfilRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:salvaUsuarioPerfilRelacionado", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - ManterUsuarioFacadeImpl:salvaUsuarioPerfilRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - ManterUsuarioFacadeImpl:salvaUsuarioPerfilRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public RelacionarUsuarioOperadoraVO relacionaUsuarioOperadora(UsuarioIDVO usuarioVO, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("ManterUsuarioFacadeImpl:relacionaUsuarioOperadora(" + user + ")");

			xmlIN = usuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "UsrUfoRelacao", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			relacionarUsuarioOperadoraVO = null;
			relacionarUsuarioOperadoraVO = RelacionarUsuarioOperadoraVODocument.Factory.parse(xmlOUT).getRelacionarUsuarioOperadoraVO();

			return relacionarUsuarioOperadoraVO;

		} catch (XmlException ex) {

			log.error("XmlException - ManterUsuarioFacadeImpl:relacionaUsuarioOperadora(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:relacionaUsuarioOperadora", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - ManterUsuarioFacadeImpl:relacionaUsuarioOperadora(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - ManterUsuarioFacadeImpl:relacionaUsuarioOperadora(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public RelacionarUsuarioOperadoraVO salvaUsuarioOperadoraRelacionado(RelacionarUsuarioOperadoraSimplVO relacionaUsuarioOperadoraSimplVO, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("ManterUsuarioFacadeImpl:salvaUsuarioOperadoraRelacionado(" + user + ")");

			xmlIN = relacionaUsuarioOperadoraSimplVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "USRUFORELACIO", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			if(msgDocRet.getMsg().getMsgHdr().getStatusText().equals("Somente usuário DESLIGADO pode ficar sem regionais associadas.")){
				throw (new XmlException(msgDocRet.getMsg().getMsgHdr().getStatusText()));
			}
			RelacionarUsuarioOperadoraVO relacionaUsuarioOperadora = null;
			relacionaUsuarioOperadora = RelacionarUsuarioOperadoraVODocument.Factory.parse(xmlOUT).getRelacionarUsuarioOperadoraVO();

			return relacionaUsuarioOperadora;

		} catch (XmlException ex) {
			if(ex.getMessage().equals("Somente usuário DESLIGADO pode ficar sem regionais associadas.")){
				throw (new TuxedoWarningException(ex.getMessage()));
			}
			log.error("XmlException - ManterUsuarioFacadeImpl:salvaUsuarioOperadoraRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:salvaUsuarioOperadoraRelacionado", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - ManterUsuarioFacadeImpl:salvaUsuarioOperadoraRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - ManterUsuarioFacadeImpl:salvaUsuarioOperadoraRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public void setSupervisor(String user, String idGrupo, String idUsuario, String inSupervisor) throws TuxedoException, FacadeException {

		try {

			log.debug("ManterUsuarioFacadeImpl:setSupervisor(" + user + ")");

			xmlIN = "<idGrupo>" + StringEscapeUtils.escapeXml(idGrupo) + "</idGrupo>" + "<idUsuario>" + StringEscapeUtils.escapeXml(idUsuario) + "</idUsuario>" + "<inSupervisor>" + StringEscapeUtils.escapeXml(inSupervisor) + "</inSupervisor>";

			xmlIN = XmlManager.MakeXmlIn(user, "USRSTATUS", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

		} catch (XmlException ex) {

			log.error("XmlException - ManterUsuarioFacadeImpl:setSupervisor(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioFacadeImpl:setSupervisor", ex));

		} catch (Exception ex) {

			log.error("Exception - ManterUsuarioFacadeImpl:setSupervisor(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

}
