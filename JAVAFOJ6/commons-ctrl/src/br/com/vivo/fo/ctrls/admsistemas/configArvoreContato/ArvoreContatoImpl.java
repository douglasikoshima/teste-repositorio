package br.com.vivo.fo.ctrls.admsistemas.configArvoreContato;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import noNamespace.MsgDocument;
import noNamespace.MsgHeaderVO;

import org.apache.xmlbeans.XmlException;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreContainerVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreContainerVODocument.AdmArvoreContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreContatoCopiaVODocument.AdmArvoreContatoCopiaVO;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmArvoreParametroVODocument.AdmArvoreParametroVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaRemoverVODocument.AdmContatoFolhaRemoverVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoContatoContainerVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoContatoContainerVODocument.AdmGrupoContatoContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmSelectsContatoFolhaVODocument.AdmSelectsContatoFolhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoArvoreVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmTipoArvoreVODocument.AdmTipoArvoreVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlHeader;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "ArvoreContato", mappedName = "ArvoreContato")
@Local(ArvoreContato.class)
@Session(ejbName = "ArvoreContato", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ArvoreContatoImpl implements ArvoreContato {

	@EJB
	private TuxedoServiceCall tuxedo;

	private static Logger log = new Logger("admsistemas");
	private String xmlIN = ConstantesCRM.SVAZIO;
	private String xmlOUT = ConstantesCRM.SVAZIO;

	/**
	 * @common:operation
	 */
	public AdmSelectsContatoFolhaVO carregaSelectsContatoFolhaVO(AdmContatoFolhaRemoverVO idContato, String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("ArvoreContatoImpl:carregaSelectsContatoFolhaVO(").append(user).append(")").toString());
			xmlIN = idContato.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CttRetRelacao", xmlIN);
			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnectorADM", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			AdmSelectsContatoFolhaVODocument doc = AdmSelectsContatoFolhaVODocument.Factory.parse(xmlOUT);
			AdmSelectsContatoFolhaVO admSelectsContatoFolhaVO = doc.getAdmSelectsContatoFolhaVO();
			return admSelectsContatoFolhaVO;

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreContatoImpl:carregaSelectsContatoFolhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

		} catch (Exception ex) {
			log.error("Exception - ArvoreContatoImpl:carregaSelectsContatoFolhaVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmTipoArvoreVO obterTipoArvore(String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("ArvoreContatoImpl:obterTipoArvore(").append(user).append(")").toString());
			xmlIN = XmlManager.MakeXmlIn(user, "CTTTPARVORE", null);
			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			AdmTipoArvoreVO admTipoArvoreVO = null;
			AdmTipoArvoreVODocument doc = AdmTipoArvoreVODocument.Factory.parse(xmlOUT);
			admTipoArvoreVO = doc.getAdmTipoArvoreVO();
			return admTipoArvoreVO;

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreContatoImpl:obterTipoArvore(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

		} catch (Exception ex) {
			log.error("Exception - ArvoreContatoImpl:carregaArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmArvoreContainerVO carregaArvoreContato(AdmIdContatoVO admIdContatoVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("ArvoreContatoImpl:carregaArvoreContato(").append(user).append(")").toString());
			xmlIN = admIdContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CttListarCont", xmlIN);
			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnectorADM", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			AdmArvoreContainerVO admContatoFolhaVO = null;
			AdmArvoreContainerVODocument doc = AdmArvoreContainerVODocument.Factory.parse(xmlOUT);
			admContatoFolhaVO = doc.getAdmArvoreContainerVO();
			return admContatoFolhaVO;

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreContatoImpl:carregaArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));
		} catch (Exception ex) {
			log.error("Exception - ArvoreContatoImpl:carregaArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void removeItemArvoreContato(AdmContatoFolhaRemoverVO contatoRemover, String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("ArvoreContatoImpl:removeItemArvoreContato(").append(user).append(")").toString());
			xmlIN = contatoRemover.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CttRemove", xmlIN);
			// (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);
			
			return;

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreContatoImpl:removeItemArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreContatoImpl:removeItemArvoreContato", ex));
		} catch (TuxedoWarningException te) {
			log.error("TuxedoWarningException - ArvoreContatoImpl:removeItemArvoreContato(" + user + ") - [" + te.getMessage() + "]");
			throw te;				
		} catch (Exception ex) {
			log.error("Exception - ArvoreContatoImpl:removeItemArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void salvaItemArvoreContato(AdmContatoFolhaVO contatoSalvar, String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("ArvoreContatoImpl:salvaItemArvoreContato(").append(user).append(")").toString());
			xmlIN = contatoSalvar.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CttIncluir", xmlIN);
			// (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);
			
			return;

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreContatoImpl:salvaItemArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreContatoImpl:salvaItemArvoreContato", ex));
		} catch (TuxedoWarningException te) {
			log.error("TuxedoWarningException - ArvoreContatoImpl:removeItemArvoreContato(" + user + ") - [" + te.getMessage() + "]");
			throw te;			
		} catch (Exception ex) {
			log.error("Exception - ArvoreContatoImpl:salvaItemArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void habilitaArvoreContato(AdmContatoFolhaVO contatoFolhaVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("ArvoreContatoImpl:salvaItemArvoreContato(").append(user).append(")").toString());
			xmlIN = contatoFolhaVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CTTATIVAR", xmlIN);
			// (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);
			
			return;
		} catch (XmlException ex) {
			log.error("XmlException - ArvoreContatoImpl:habilitaArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreContatoImpl:habilitaArvoreContato", ex));
		} catch (TuxedoWarningException te) {
			log.error("TuxedoWarningException - ArvoreContatoImpl:habilitaArvoreContato(" + user + ") - [" + te.getMessage() + "]");
			throw te;			
		} catch (Exception ex) {
			log.error("Exception - ArvoreContatoImpl:habilitaArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void copiaArvoreContato(AdmArvoreContatoCopiaVO item, String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("ArvoreContatoImpl:copiaArvoreContato(").append(user).append(")").toString());
			xmlIN = item.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CTTCOPIA", xmlIN);
			// (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnectorADM", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
            MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);
			
			return;

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreContatoImpl:copiaArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreContatoImpl:copiaArvoreContato", ex));
		} catch (TuxedoWarningException te) {
			log.error("TuxedoWarningException - ArvoreContatoImpl:copiaArvoreContato(" + user + ") - [" + te.getMessage() + "]");
			throw te;	
		} catch (Exception ex) {
			log.error("Exception - ArvoreContatoImpl:copiaArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void salvaItemEditadoArvoreContato(AdmContatoFolhaVO contatoEditar, String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("ArvoreContatoImpl:salvaItemEditadoArvoreContato(").append(user).append(")").toString());
			xmlIN = contatoEditar.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CttEditar", xmlIN);
			// (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
            MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);
			
			return;

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreContatoImpl:salvaItemEditadoArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreContatoImpl:salvaItemEditadoArvoreContato", ex));
		} catch (TuxedoWarningException te) {
			log.error("TuxedoWarningException - ArvoreContatoImpl:salvaItemEditadoArvoreContato(" + user + ") - [" + te.getMessage() + "]");
			throw te;	
		} catch (Exception ex) {
			log.error("Exception - ArvoreContatoImpl:salvaItemEditadoArvoreContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmArvoreParametroVO getArvoreParametro(AdmArvoreParametroVO arvoreParametroVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("ArvoreContatoImpl:getArvoreParametro(").append(user).append(")").toString());
			xmlIN = arvoreParametroVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CTTVARIAVEIS", xmlIN);
			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);
			
			AdmArvoreParametroVO admArvoreParametroVO = null;
			AdmArvoreParametroVODocument doc = AdmArvoreParametroVODocument.Factory.parse(xmlOUT);
			admArvoreParametroVO = doc.getAdmArvoreParametroVO();
			return admArvoreParametroVO;

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreContatoImpl:getArvoreParametro(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreContatoImpl:getArvoreParametro", ex));
		} catch (TuxedoWarningException te) {
			log.error("TuxedoWarningException - ArvoreContatoImpl:getArvoreParametro(" + user + ") - [" + te.getMessage() + "]");
			throw te;
		} catch (Exception ex) {
			log.error("Exception - ArvoreContatoImpl:getArvoreParametro(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmArvoreParametroVO getArvoreConsulta(AdmArvoreParametroVO arvoreParametroVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("ArvoreContatoImpl:getArvoreParametro(").append(user).append(")").toString());
			xmlIN = arvoreParametroVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CNSPRIORIZ", xmlIN);
			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);
			
			AdmArvoreParametroVO admArvoreParametroVO = null;
			AdmArvoreParametroVODocument doc = AdmArvoreParametroVODocument.Factory.parse(xmlOUT);
			admArvoreParametroVO = doc.getAdmArvoreParametroVO();
			return admArvoreParametroVO;

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreContatoImpl:getArvoreParametro(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreContatoImpl:getArvoreParametro", ex));

		} catch (Exception ex) {
			log.error("Exception - ArvoreContatoImpl:getArvoreParametro(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmArvoreContainerVO getArvoreNome(AdmIdContatoVO admIdContatoVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("ArvoreContatoImpl:getArvoreNome(").append(user).append(")").toString());
			xmlIN = admIdContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "CTTLISTANOME", xmlIN);
			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			AdmArvoreContainerVO admArvoreContainerVO = AdmArvoreContainerVODocument.Factory.parse(xmlOUT).getAdmArvoreContainerVO();
			return admArvoreContainerVO;

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreContatoImpl:getArvoreNome(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreContatoImpl:getArvoreNome", ex));

		} catch (Exception ex) {
			log.error("Exception - ArvoreContatoImpl:getArvoreNome(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmGrupoContatoContainerVO getListaAssocGrupoContato(String idContato, String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("ArvoreContatoImpl:getListaAssocGrupoContato(").append(user).append(")").toString());
			xmlIN = "<idContato>" + idContato + "</idContato>";
			xmlIN = XmlManager.MakeXmlIn(user, "CTTGRPLISTAR", xmlIN);
			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnectorADM", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			AdmGrupoContatoContainerVO admGrupoContatoContainerVO = AdmGrupoContatoContainerVODocument.Factory.parse(xmlOUT).getAdmGrupoContatoContainerVO();
			return admGrupoContatoContainerVO;

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreContatoImpl:getListaAssocGrupoContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreContatoImpl:getListaAssocGrupoContato", ex));

		} catch (Exception ex) {
			log.error("Exception - ArvoreContatoImpl:getListaAssocGrupoContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void salvaAssocGrupoContato(String selecionados, String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("ArvoreContatoImpl:salvaAssocGrupoContato(").append(user).append(")").toString());
			xmlIN = selecionados;
			xmlIN = XmlManager.MakeXmlIn(user, "CTTGRPASSOC", xmlIN);
			// (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO =  msgDocRet.getMsg().getMsgHdr();
			tratarWarningException(msgHeaderVO);

		} catch (XmlException ex) {
			log.error("XmlException - ArvoreContatoImpl:salvaAssocGrupoContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ArvoreContatoImpl:salvaAssocGrupoContato", ex));
		} catch (TuxedoWarningException te) {
			log.error("TuxedoWarningException - ArvoreContatoImpl:salvaAssocGrupoContato(" + user + ") - [" + te.getMessage() + "]");
			throw te;	
		} catch (Exception ex) {
			log.error("Exception - ArvoreContatoImpl:salvaAssocGrupoContato(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}
	
	private void tratarWarningException(MsgHeaderVO msgHeaderVO) throws TuxedoWarningException {
		
		if(msgHeaderVO != null && msgHeaderVO.getStatusCode().indexOf("W")>-1) {
			XmlHeader xmlHeader = new XmlHeader(msgHeaderVO.getService(),msgHeaderVO.getUser(), "fo", 'W', msgHeaderVO.getStatusCode().substring(0,4), msgHeaderVO.getStatusText());
			throw new TuxedoWarningException(xmlHeader);
		} else if(msgHeaderVO != null && msgHeaderVO.getStatusCode().indexOf("E")>-1) {
			XmlHeader xmlHeader = new XmlHeader(msgHeaderVO.getService(),msgHeaderVO.getUser(), "fo", 'E', msgHeaderVO.getStatusCode().substring(0,4), msgHeaderVO.getStatusText());
			throw new TuxedoWarningException(xmlHeader);
		}

	}
}
