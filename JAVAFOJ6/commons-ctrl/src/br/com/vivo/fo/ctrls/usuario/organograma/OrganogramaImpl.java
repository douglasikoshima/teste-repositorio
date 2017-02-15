package br.com.vivo.fo.ctrls.usuario.organograma;

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
import br.com.vivo.fo.usuario.vo.AdmOrgNivelContainerVODocument;
import br.com.vivo.fo.usuario.vo.AdmOrgNivelContainerVODocument.AdmOrgNivelContainerVO;
import br.com.vivo.fo.usuario.vo.AdmOrgOrganizacaoContainerVODocument;
import br.com.vivo.fo.usuario.vo.AdmOrgOrganizacaoContainerVODocument.AdmOrgOrganizacaoContainerVO;
import br.com.vivo.fo.usuario.vo.AtividadeVODocument.AtividadeVO;
import br.com.vivo.fo.usuario.vo.CargoAtividadeRelacionamentoVODocument;
import br.com.vivo.fo.usuario.vo.CargoAtividadeRelacionamentoVODocument.CargoAtividadeRelacionamentoVO;
import br.com.vivo.fo.usuario.vo.CargoVODocument.CargoVO;
import br.com.vivo.fo.usuario.vo.ListaAtividadeVODocument;
import br.com.vivo.fo.usuario.vo.ListaAtividadeVODocument.ListaAtividadeVO;
import br.com.vivo.fo.usuario.vo.ListaCargoVODocument;
import br.com.vivo.fo.usuario.vo.ListaCargoVODocument.ListaCargoVO;
import br.com.vivo.fo.usuario.vo.ListaTipoOrganizacaoVODocument;
import br.com.vivo.fo.usuario.vo.ListaTipoOrganizacaoVODocument.ListaTipoOrganizacaoVO;
import br.com.vivo.fo.usuario.vo.ListaUnidadeVODocument;
import br.com.vivo.fo.usuario.vo.ListaUnidadeVODocument.ListaUnidadeVO;
import br.com.vivo.fo.usuario.vo.NivelCargoRelacionamentoVODocument;
import br.com.vivo.fo.usuario.vo.NivelCargoRelacionamentoVODocument.NivelCargoRelacionamentoVO;
import br.com.vivo.fo.usuario.vo.NivelOrganogramaVODocument.NivelOrganogramaVO;
import br.com.vivo.fo.usuario.vo.OrganizacaoUnidadeRelacionamentoVODocument;
import br.com.vivo.fo.usuario.vo.OrganizacaoUnidadeRelacionamentoVODocument.OrganizacaoUnidadeRelacionamentoVO;
import br.com.vivo.fo.usuario.vo.OrganizacaoVODocument.OrganizacaoVO;
import br.com.vivo.fo.usuario.vo.TipoOrganizacaoVODocument.TipoOrganizacaoVO;
import br.com.vivo.fo.usuario.vo.UnidadeOrganogramaVODocument.UnidadeOrganogramaVO;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="Organograma",mappedName="Organograma")
@Local(Organograma.class)
@Session(ejbName = "Organograma", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class OrganogramaImpl implements Organograma {

	@EJB
	private TuxedoServiceCall tuxedo;

	static final long serialVersionUID = 1L;
	private String xmlIN;
	private String xmlOUT;

	private static Logger log = new Logger("usuario");

	/**
	 * @common:operation
	 */
	public ListaCargoVO getListaCargo(CargoVO cargo, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:getListaCargo(" + user + ")");

			xmlIN = cargo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CRGLISTAPAR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaCargoVO listaCargoVO = ListaCargoVODocument.Factory.parse(xmlOUT).getListaCargoVO();

			return listaCargoVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:getListaCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:getListaCargo", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:getListaCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:getListaCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaCargoVO removeCargo(CargoVO cargo, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:removeCargo(" + user + ")");

			xmlIN = cargo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CRGREMOVE", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaCargoVO listaCargoVO = null;
			listaCargoVO = ListaCargoVODocument.Factory.parse(xmlOUT).getListaCargoVO();

			return listaCargoVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:removeCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:removeCargo", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:removeCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:removeCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}
	}

	/**
	 * @common:operation
	 */
	public ListaCargoVO insertCargo(CargoVO cargo, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:insertCargo(" + user + ")");

			xmlIN = cargo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CRGINSERIR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaCargoVO listaCargoVO = null;
			listaCargoVO = ListaCargoVODocument.Factory.parse(xmlOUT).getListaCargoVO();

			return listaCargoVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:insertCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:insertCargo", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:insertCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:insertCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaCargoVO alteraCargo(CargoVO cargo, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:alteraCargo(" + user + ")");

			xmlIN = cargo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CRGEDITAR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaCargoVO listaCargoVO = null;
			listaCargoVO = ListaCargoVODocument.Factory.parse(xmlOUT).getListaCargoVO();

			return listaCargoVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:alteraCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:alteraCargo", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:alteraCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:alteraCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaAtividadeVO getListaAtividade(AtividadeVO atividade, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:getListaAtividade(" + user + ")");

			xmlIN = atividade.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ATVLISTAPAR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaAtividadeVO listaAtividadeVO = null;
			listaAtividadeVO = ListaAtividadeVODocument.Factory.parse(xmlOUT).getListaAtividadeVO();

			return listaAtividadeVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:getListaAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:getListaAtividade", ex));

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:getListaAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaAtividadeVO removeAtividade(AtividadeVO atividade, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:removeAtividade(" + user + ")");

			xmlIN = atividade.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ATVREMOVE", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaAtividadeVO listaAtividadeVO = null;
			listaAtividadeVO = ListaAtividadeVODocument.Factory.parse(xmlOUT).getListaAtividadeVO();

			return listaAtividadeVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:removeAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:removeAtividade", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:removeAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:removeAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}

	}

	/**
	 * @common:operation
	 */
	public ListaAtividadeVO insertAtividade(AtividadeVO atividade, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:insertAtividade(" + user + ")");

			xmlIN = atividade.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ATVINSERIR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaAtividadeVO listaAtividadeVO = null;
			listaAtividadeVO = ListaAtividadeVODocument.Factory.parse(xmlOUT).getListaAtividadeVO();

			return listaAtividadeVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:insertAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:insertAtividade", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:insertAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:insertAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaAtividadeVO inserirAtividade(AtividadeVO atividade, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:inserirAtividade(" + user + ")");

			xmlIN = atividade.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ATVINSERIR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaAtividadeVO listaAtividadeVO = null;
			listaAtividadeVO = ListaAtividadeVODocument.Factory.parse(xmlOUT).getListaAtividadeVO();

			return listaAtividadeVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:inserirAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:inserirAtividade", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:inserirAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:inserirAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaAtividadeVO alteraAtividade(AtividadeVO atividade, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:alteraAtividade(" + user + ")");

			xmlIN = atividade.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ATVEDITAR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaAtividadeVO listaAtividadeVO = null;
			listaAtividadeVO = ListaAtividadeVODocument.Factory.parse(xmlOUT).getListaAtividadeVO();

			return listaAtividadeVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:alteraAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:alteraAtividade", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:alteraAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:alteraAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaUnidadeVO getListaUnidade(UnidadeOrganogramaVO unidade, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:getListaUnidade(" + user + ")");

			xmlIN = unidade.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "UNDLISTAPAR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaUnidadeVO listaUnidadeVO = null;
			listaUnidadeVO = ListaUnidadeVODocument.Factory.parse(xmlOUT).getListaUnidadeVO();

			return listaUnidadeVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:getListaUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:getListaUnidade", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:getListaUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:getListaUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaUnidadeVO removeUnidade(UnidadeOrganogramaVO unidade, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:removeUnidade(" + user + ")");

			xmlIN = unidade.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "UNDREMOVE", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaUnidadeVO listaUnidadeVO = null;
			listaUnidadeVO = ListaUnidadeVODocument.Factory.parse(xmlOUT).getListaUnidadeVO();

			return listaUnidadeVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:removeUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:removeUnidade", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:removeUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:removeUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}
	}

	/**
	 * @common:operation
	 */
	public ListaUnidadeVO insertUnidade(UnidadeOrganogramaVO unidade, String operacion, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:insertUnidade(" + user + ")");

			xmlIN = unidade.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);

			// Se inclusão.
			if (unidade.getIdUnidade() == null || unidade.getIdUnidade().trim().equals("")) {
				xmlIN = XmlManager.MakeXmlIn(user, "UNDINSERIR", xmlIN);
				// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
				xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			}
			// Se alteração
			else {
				xmlIN = XmlManager.MakeXmlIn(user, "UNDEDITAR", xmlIN);
				// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
				xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			}

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaUnidadeVO listaUnidadeVO = null;
			listaUnidadeVO = ListaUnidadeVODocument.Factory.parse(xmlOUT).getListaUnidadeVO();

			return listaUnidadeVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:insertUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:insertUnidade", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:insertUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:insertUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}
	}

	/**
	 * @common:operation
	 */
	public ListaUnidadeVO editarUnidade(UnidadeOrganogramaVO unidade, String operacion, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:editarUnidade(" + user + ")");

			xmlIN = unidade.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "UNDEDITAR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaUnidadeVO listaUnidadeVO = null;
			listaUnidadeVO = ListaUnidadeVODocument.Factory.parse(xmlOUT).getListaUnidadeVO();

			return listaUnidadeVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:editarUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:editarUnidade", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:editarUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:editarUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaTipoOrganizacaoVO editarTipoOrganizacao(TipoOrganizacaoVO tipoOrganizacao, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:editarTipoOrganizacao(" + user + ")");

			xmlIN = tipoOrganizacao.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "TOEDITAR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaTipoOrganizacaoVO listaTipoOrganizacaoVO = null;
			listaTipoOrganizacaoVO = ListaTipoOrganizacaoVODocument.Factory.parse(xmlOUT).getListaTipoOrganizacaoVO();

			return listaTipoOrganizacaoVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:editarTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:editarTipoOrganizacao", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:editarTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:editarTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaTipoOrganizacaoVO getListaTipoOrganizacao(TipoOrganizacaoVO tipoOrganizacao, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:getListaTipoOrganizacao(" + user + ")");

			tipoOrganizacao.setIdTipoOrganizacao(ConstantesCRM.SVAZIO);
			tipoOrganizacao.setDsTipoOrganizacao(ConstantesCRM.SVAZIO);

			xmlIN = tipoOrganizacao.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "TOLISTAPAR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaTipoOrganizacaoVO listaTipoOrganizacaoVO = null;
			listaTipoOrganizacaoVO = ListaTipoOrganizacaoVODocument.Factory.parse(xmlOUT).getListaTipoOrganizacaoVO();

			return listaTipoOrganizacaoVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:getListaTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:getListaTipoOrganizacao", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:getListaTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:getListaTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaTipoOrganizacaoVO removeTipoOrganizacao(TipoOrganizacaoVO tipoOrganizacao, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:removeTipoOrganizacao(" + user + ")");

			xmlIN = tipoOrganizacao.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "TOREMOVE", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaTipoOrganizacaoVO listaTipoOrganizacaoVO = null;
			listaTipoOrganizacaoVO = ListaTipoOrganizacaoVODocument.Factory.parse(xmlOUT).getListaTipoOrganizacaoVO();

			return listaTipoOrganizacaoVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:removeTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:removeTipoOrganizacao", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:removeTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:removeTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaTipoOrganizacaoVO insertTipoOrganizacao(TipoOrganizacaoVO tipoOrganizacao, String operacion, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:insertTipoOrganizacao(" + user + ")");

			xmlIN = tipoOrganizacao.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "TOINSERIR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaTipoOrganizacaoVO listaTipoOrganizacaoVO = null;
			listaTipoOrganizacaoVO = ListaTipoOrganizacaoVODocument.Factory.parse(xmlOUT).getListaTipoOrganizacaoVO();

			return listaTipoOrganizacaoVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:insertTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:insertTipoOrganizacao", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:insertTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:insertTipoOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public CargoAtividadeRelacionamentoVO listaRelacionarCargoAtividade(CargoVO sistemaRecuperaAgrupamentos, String user) throws TuxedoException, FacadeException {

		try {

			xmlIN = sistemaRecuperaAgrupamentos.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ATVRELACAOCA", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			CargoAtividadeRelacionamentoVO cargoAtividadeRelacionamento = null;
			cargoAtividadeRelacionamento = CargoAtividadeRelacionamentoVODocument.Factory.parse(xmlOUT).getCargoAtividadeRelacionamentoVO();

			return cargoAtividadeRelacionamento;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:listaRelacionarCargoAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:listaRelacionarCargoAtividade", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:listaRelacionarCargoAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:listaRelacionarCargoAtividade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public OrganizacaoUnidadeRelacionamentoVO listaRelacionarOrganizacaoUnidade(OrganizacaoVO sistemaRecuperaAgrupamentos, String user) throws TuxedoException, FacadeException {

		try {

			xmlIN = sistemaRecuperaAgrupamentos.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "UNDRELACAOOU", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			OrganizacaoUnidadeRelacionamentoVO organizacaoUnidadeRelacionamento = null;
			organizacaoUnidadeRelacionamento = OrganizacaoUnidadeRelacionamentoVODocument.Factory.parse(xmlOUT).getOrganizacaoUnidadeRelacionamentoVO();

			return organizacaoUnidadeRelacionamento;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:listaRelacionarOrganizacaoUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:listaRelacionarOrganizacaoUnidade", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:listaRelacionarOrganizacaoUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:listaRelacionarOrganizacaoUnidade(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public NivelCargoRelacionamentoVO listaRelacionarNivelCargo(NivelOrganogramaVO sistemaRecuperaAgrupamentos, String user) throws TuxedoException, FacadeException {

		try {

			xmlIN = sistemaRecuperaAgrupamentos.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CRGRELACAONC", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			NivelCargoRelacionamentoVO nivelCargoRelacionamento = null;
			nivelCargoRelacionamento = NivelCargoRelacionamentoVODocument.Factory.parse(xmlOUT).getNivelCargoRelacionamentoVO();

			return nivelCargoRelacionamento;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:listaRelacionarNivelCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:listaRelacionarNivelCargo", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:listaRelacionarNivelCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:listaRelacionarNivelCargo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public CargoAtividadeRelacionamentoVO salvaCargoAtividadeRelacionados(CargoAtividadeRelacionamentoVO cargoAtividadeVO, String user) throws TuxedoException, FacadeException {

		try {

			xmlIN = cargoAtividadeVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CRGRELACIONACA", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			CargoAtividadeRelacionamentoVO cargoAtividadeRelacionamentoVO = null;
			cargoAtividadeRelacionamentoVO = CargoAtividadeRelacionamentoVODocument.Factory.parse(xmlOUT).getCargoAtividadeRelacionamentoVO();

			return cargoAtividadeRelacionamentoVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:salvaCargoAtividadeRelacionados(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:salvaCargoAtividadeRelacionados", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:salvaCargoAtividadeRelacionados(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:salvaCargoAtividadeRelacionados(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public NivelCargoRelacionamentoVO salvaNivelCargoRelacionados(NivelCargoRelacionamentoVO nivelCargoVO, String user) throws TuxedoException, FacadeException {

		try {

			xmlIN = nivelCargoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "NVLRELACIONANC", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			NivelCargoRelacionamentoVO nivelCargoRelacionamentoVO = null;
			nivelCargoRelacionamentoVO = NivelCargoRelacionamentoVODocument.Factory.parse(xmlOUT).getNivelCargoRelacionamentoVO();

			return nivelCargoRelacionamentoVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:salvaNivelCargoRelacionados(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:salvaNivelCargoRelacionados", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:salvaNivelCargoRelacionados(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:salvaNivelCargoRelacionados(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public OrganizacaoUnidadeRelacionamentoVO salvaOrganizacaoUnidadeRelacionados(OrganizacaoUnidadeRelacionamentoVO organizacaoUnidadeVO, String user) throws TuxedoException, FacadeException {

		try {

			xmlIN = organizacaoUnidadeVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ORGRELACIONAOU", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			OrganizacaoUnidadeRelacionamentoVO organizacaoUnidadeRelacionamentoVO = null;
			organizacaoUnidadeRelacionamentoVO = OrganizacaoUnidadeRelacionamentoVODocument.Factory.parse(xmlOUT).getOrganizacaoUnidadeRelacionamentoVO();

			return organizacaoUnidadeRelacionamentoVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:salvaOrganizacaoUnidadeRelacionados(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:salvaOrganizacaoUnidadeRelacionados", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:salvaOrganizacaoUnidadeRelacionados(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:salvaOrganizacaoUnidadeRelacionados(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public AdmOrgNivelContainerVO cargaArvoreNivel(NivelOrganogramaVO nivelVO, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:cargaArvoreNivel(" + user + ")");

			xmlIN = nivelVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "NVLLISTA", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			AdmOrgNivelContainerVO nivelOrganogramaVO = null;
			nivelOrganogramaVO = AdmOrgNivelContainerVODocument.Factory.parse(xmlOUT).getAdmOrgNivelContainerVO();

			return nivelOrganogramaVO;

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:cargaArvoreNivel(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:cargaArvoreNivel(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public void excluiNivel(AdmOrgNivelContainerVO nivelContainerVO, String user) throws TuxedoException, FacadeException {

		try {

			xmlIN = nivelContainerVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "NVLREMOVE", xmlIN);

			// (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			return;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:excluiNivel(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:excluiNivel", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:excluiNivel(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:excluiNivel(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public void excluiOrganizacao(AdmOrgOrganizacaoContainerVO organizacaoContainerVO, String user) throws TuxedoException, FacadeException {

		try {

			xmlIN = organizacaoContainerVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ORGREMOVE", xmlIN);

			// (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			return;

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:excluiOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:excluiOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public void gravaNivel(AdmOrgNivelContainerVO nivelContainarVO, String user) throws TuxedoException, FacadeException {

		try {

			xmlIN = nivelContainarVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "NVLINSERIR", xmlIN);

			// (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			return;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:gravaNivel(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:gravaNivel", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:gravaNivel(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:gravaNivel(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public void gravaOrganizacao(AdmOrgOrganizacaoContainerVO organizacaoContainarVO, String user) throws TuxedoException, FacadeException {

		try {

			xmlIN = organizacaoContainarVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ORGINSERIR", xmlIN);

			// (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			return;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:gravaOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:gravaOrganizacao", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:gravaOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:gravaOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public void alteraNivel(AdmOrgNivelContainerVO nivelContainarVO, String user) throws TuxedoException, FacadeException {

		try {

			xmlIN = nivelContainarVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "NVLEDITAR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			return;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:alteraNivel(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:alteraNivel", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:alteraNivel(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:alteraNivel(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public void alteraOrganizacao(AdmOrgOrganizacaoContainerVO organizacaoContainarVO, String user) throws TuxedoException, FacadeException {

		try {

			xmlIN = organizacaoContainarVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ORGEDITAR", xmlIN);

			// (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			return;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:alteraOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:alteraOrganizacao", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:alteraOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:alteraOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public AdmOrgOrganizacaoContainerVO cargaArvoreOrganizacao(OrganizacaoVO organizacaoVO, String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:cargaArvoreOrganizacao(" + user + ")");

			xmlIN = organizacaoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "ORGLISTA", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			AdmOrgOrganizacaoContainerVO organizacaoOrganogramaVO = null;
			organizacaoOrganogramaVO = AdmOrgOrganizacaoContainerVODocument.Factory.parse(xmlOUT).getAdmOrgOrganizacaoContainerVO();

			return organizacaoOrganogramaVO;

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:cargaArvoreOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:cargaArvoreOrganizacao(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

	/**
	 * @common:operation
	 */
	public ListaTipoOrganizacaoVO getListaTipoOrganizacaos(String user) throws TuxedoException, FacadeException {

		try {

			log.debug("OrganogramaImpl:getListaTipoOrganizacaos(" + user + ")");

			TipoOrganizacaoVO tipoOrganizacaoVO = TipoOrganizacaoVO.Factory.newInstance();
			tipoOrganizacaoVO.setIdTipoOrganizacao(ConstantesCRM.SVAZIO);
			tipoOrganizacaoVO.setDsTipoOrganizacao(ConstantesCRM.SVAZIO);
			xmlIN = tipoOrganizacaoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "TOLISTAPAR", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, usuarioTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			ListaTipoOrganizacaoVO listaTipoOrganizacaoVO = null;
			listaTipoOrganizacaoVO = ListaTipoOrganizacaoVODocument.Factory.parse(xmlOUT).getListaTipoOrganizacaoVO();

			return listaTipoOrganizacaoVO;

		} catch (XmlException ex) {

			log.error("XmlException - OrganogramaImpl:getListaTipoOrganizacaos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: OrganogramaImpl:getListaTipoOrganizacaos", ex));

		} catch (TuxedoServiceCallerException ex) {

			log.error("TuxedoException - OrganogramaImpl:getListaTipoOrganizacaos(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {

			log.error("Exception - OrganogramaImpl:getListaTipoOrganizacaos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));

		}

	}

}