package br.com.vivo.fo.ctrls.usuario.manterUsuarioSkill;

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
import br.com.vivo.fo.admsistemas.vo.AdmSkillContatoVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmSkillContatoVODocument.AdmSkillContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument;
import br.com.vivo.fo.admsistemas.vo.AdmSkillUsuarioVODocument.AdmSkillUsuarioVO;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.commons.utils.EstruturaSkillTO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@SuppressWarnings("unchecked")
@Stateless(name = "ManterUsuarioSkillFacade", mappedName = "ManterUsuarioSkillFacade")
@Local(ManterUsuarioSkillFacade.class)
@Session(ejbName = "ManterUsuarioSkillFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterUsuarioSkillFacadeImpl implements ManterUsuarioSkillFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	static final long serialVersionUID = 1L;
	private String xmlIN;
	private String xmlOUT;

	private static Logger log = new Logger("usuario");

	/**
	 * @common:operation
	 */
	public AdmSkillUsuarioVO getUsuarioSkill(AdmSkillUsuarioVO admSkillUsuarioVO, String user) throws TuxedoException, FacadeException {

		AdmSkillUsuarioVO admUsuarioVOProv = null;
		try {
			log.debug("ManterUsuarioSkillFacadeImpl:getUsuarioSkill(" + user + ")");
			if (admSkillUsuarioVO.getInOperacao() == null || admSkillUsuarioVO.getInOperacao().equals(ConstantesCRM.SVAZIO)) {// Ricardo
				xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
				xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);

				xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

				MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
				xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

				return AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO();
			} else {
				admSkillUsuarioVO.setPaginaAtual(ConstantesCRM.SONE);
				admSkillUsuarioVO.setRegistrosPPagina("1000");

				xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
				xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);

				xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

				MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
				xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

				admUsuarioVOProv = AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO();
				if (admUsuarioVOProv.getRegistrosPPagina().equals(admSkillUsuarioVO.getRegistrosPPagina())) {
					int j = 1;
					int totalRegistros = 1;
					while (totalRegistros > 0 && totalRegistros <= Integer.parseInt(admUsuarioVOProv.getRegistrosPPagina())) {
						j += 1;
						admSkillUsuarioVO.setPaginaAtual(String.valueOf(j));
						admSkillUsuarioVO.setRegistrosPPagina("1000");
						xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
						xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);

						xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

						msgDocRet = MsgDocument.Factory.parse(xmlOUT);
						xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

						AdmSkillUsuarioVO admUsuarioVOInterno = AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO();

						totalRegistros = Integer.parseInt(admUsuarioVOInterno.getRegistrosPPagina());
						for (int i = 0; i < admUsuarioVOInterno.getUsuarioExistenteVOArray().length; i++) {
							admUsuarioVOProv.insertNewUsuarioExistenteVO(admUsuarioVOProv.getUsuarioExistenteVOArray().length);
							admUsuarioVOProv.getUsuarioExistenteVOArray(admUsuarioVOProv.getUsuarioExistenteVOArray().length - 1).setIdUsuario(admUsuarioVOInterno.getUsuarioExistenteVOArray(i).getIdUsuario());
							admUsuarioVOProv.getUsuarioExistenteVOArray(admUsuarioVOProv.getUsuarioExistenteVOArray().length - 1).setNmUsuario(admUsuarioVOInterno.getUsuarioExistenteVOArray(i).getNmUsuario());
						}
					}
					return admUsuarioVOProv;
				} else {
					return admUsuarioVOProv;
				}
			}

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:getUsuarioSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:getUsuarioSkill", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:getUsuarioSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:getUsuarioSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void setUsuarioSkill(AdmSkillUsuarioVO admSkillUsuarioVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("ManterUsuarioSkillFacadeImpl:setUsuarioSkill(" + user + ")");
			admSkillUsuarioVO.setInOperacao(ConstantesCRM.SONE);
			xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLPUT", xmlIN);

			tuxedo.callService("TuxConnector", xmlIN);

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:setUsuarioSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:setUsuarioSkill", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:setUsuarioSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:setUsuarioSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void excluiUsuarioSkill(AdmSkillUsuarioVO admSkillUsuarioVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("ManterUsuarioSkillFacadeImpl:excluiUsuarioSkill(" + user + ")");

			xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLDEL", xmlIN);

			tuxedo.callService("TuxConnector", xmlIN);

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:excluiUsuarioSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:excluiUsuarioSkill", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:excluiUsuarioSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:excluiUsuarioSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public AdmSkillUsuarioVO pesquisaUsuarioSkill(AdmSkillUsuarioVO admSkillUsuarioVO, String user) throws TuxedoException, FacadeException {
		try {
			log.debug("ManterUsuarioSkillFacadeImpl:pesquisaUsuarioSkill(" + user + ")");

			xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "SKLPESQU", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			return AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO();

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:pesquisaUsuarioSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:pesquisaUsuarioSkill", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:pesquisaUsuarioSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:pesquisaUsuarioSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public EstruturaSkillTO getUsuarioSkillTunado(AdmSkillUsuarioVO admSkillUsuarioVO, String user) throws TuxedoException, FacadeException {
		// AdmSkillUsuarioVO admUsuarioVOProv=null;
		EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();
		try {
			log.debug("ManterUsuarioSkillFacadeImpl:getUsuarioSkillTunado(" + user + ")");

			// //Ricardo
			if (admSkillUsuarioVO.getInOperacao() == null || admSkillUsuarioVO.getInOperacao().equals(ConstantesCRM.SVAZIO)) {// Ricardo
				xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
				xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);

				xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

				MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
				xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

				estruturaSkillTO.setAdmSkillUsuarioVO(AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO());

				return estruturaSkillTO;

			} else {// Ricardo
				admSkillUsuarioVO.setPaginaAtual(ConstantesCRM.SONE);// Ricardo
				admSkillUsuarioVO.setRegistrosPPagina("400");// Ricardo

				xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
				xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

				xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

				MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
				xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

				estruturaSkillTO.setAdmSkillUsuarioVO(AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO());

				if (admSkillUsuarioVO.getInOperacao() != null && (admSkillUsuarioVO.getInOperacao().equals("2"))) {
					estruturaSkillTO.getListaUsuariosExistentes().add(estruturaSkillTO.getAdmSkillUsuarioVO().getUsuarioExistenteVOArray());
					try {
						// if(!estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina().equals(null)){
						if (estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina().equals(admSkillUsuarioVO.getRegistrosPPagina())) {
							int j = 1;
							int totalRegistros = 1;
							while (totalRegistros > 0 && totalRegistros <= Integer.parseInt(estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina())) {
								j += 1;
								admSkillUsuarioVO.setPaginaAtual(String.valueOf(j));// Ricardo
								admSkillUsuarioVO.setRegistrosPPagina("400");// Ricardo
								xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
								xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

								xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
								msgDocRet = MsgDocument.Factory.parse(xmlOUT);
								xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

								AdmSkillUsuarioVO admUsuarioVOInterno = AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO();

								totalRegistros = Integer.parseInt(admUsuarioVOInterno.getRegistrosPPagina());
								estruturaSkillTO.getListaUsuariosExistentes().add(admUsuarioVOInterno.getUsuarioExistenteVOArray());
							}
							return estruturaSkillTO;
						} else {
							return estruturaSkillTO;
						}
						// }else{
						// return estruturaSkillTO;
						// }
					} catch (Exception ex) {
						return estruturaSkillTO;
					}

				} else {
					estruturaSkillTO.getListaUsuarioSkillAssociado().add(estruturaSkillTO.getAdmSkillUsuarioVO().getUsuarioSkillAssociadoVOArray());
					try {
						// if(!estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina().equals(null)){
						if (estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina().equals(admSkillUsuarioVO.getRegistrosPPagina())) {
							int j = 1;
							int totalRegistros = 1;
							while (totalRegistros > 0 && totalRegistros <= Integer.parseInt(estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina())) {
								j += 1;
								admSkillUsuarioVO.setPaginaAtual(String.valueOf(j));// Ricardo
								admSkillUsuarioVO.setRegistrosPPagina("400");// Ricardo
								xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
								xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

								xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
								msgDocRet = MsgDocument.Factory.parse(xmlOUT);
								xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

								AdmSkillUsuarioVO admUsuarioVOInterno = AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO();

								totalRegistros = Integer.parseInt(admUsuarioVOInterno.getRegistrosPPagina());
								estruturaSkillTO.getListaUsuarioSkillAssociado().add(admUsuarioVOInterno.getUsuarioSkillAssociadoVOArray());
							}
							return estruturaSkillTO;
						} else {
							return estruturaSkillTO;
						}
					} catch (Exception ex) {
						return estruturaSkillTO;
					}
					// }else{
					// return estruturaSkillTO;
					// }
				}
			}// Ricardo
			// System.out.println(xmlIN);
			// return
			// AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO();

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:getUsuarioSkillTunado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:getUsuarioSkillTunado", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:getUsuarioSkillTunado(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:getUsuarioSkillTunado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public EstruturaSkillTO getSkillsByIdGrupo(AdmSkillUsuarioVO admSkillUsuarioVO, String user) throws TuxedoException, FacadeException {
		admSkillUsuarioVO.setInOperacao(ConstantesCRM.SONE);
		EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();
		try {
			log.debug("ManterUsuarioSkillFacadeImpl:getSkillsByIdGrupo(" + user + ")");
			xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			estruturaSkillTO.setAdmSkillUsuarioVO(AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO());
			return estruturaSkillTO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:getSkillsByIdGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:getSkillsByIdGrupo", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:getSkillsByIdGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:getSkillsByIdGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public EstruturaSkillTO getListaContatosByIdSkill(AdmSkillContatoVO admSkillContatoVO, String user) throws TuxedoException, FacadeException {
		EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();
		AdmSkillContatoVO admSkillContatoVOTemp;
		try {
			// Lista de Existentes
			admSkillContatoVO.setPaginaAtual(ConstantesCRM.SONE);// Ricardo
			admSkillContatoVO.setRegistrosPPagina("400");// Ricardo
			admSkillContatoVO.setInListaContato(ConstantesCRM.SZERO);

			xmlIN = admSkillContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
			xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			admSkillContatoVOTemp = AdmSkillContatoVODocument.Factory.parse(xmlOUT).getAdmSkillContatoVO();

			estruturaSkillTO.getListaContatosExistentes().add(admSkillContatoVOTemp.getListaContatoDisponivel().getContatoSkillVOArray());

			if (admSkillContatoVOTemp.getRegistrosPPagina().equals(admSkillContatoVO.getRegistrosPPagina())) {
				int j = 1;
				int totalRegistros = 1;
				while (totalRegistros > 0 && totalRegistros <= Integer.parseInt(admSkillContatoVOTemp.getRegistrosPPagina())) {
					j += 1;
					admSkillContatoVO.setPaginaAtual(String.valueOf(j));// Ricardo
					admSkillContatoVO.setRegistrosPPagina("400");// Ricardo
					admSkillContatoVO.setInListaContato(ConstantesCRM.SZERO);
					xmlIN = admSkillContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
					xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

					xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

					msgDocRet = MsgDocument.Factory.parse(xmlOUT);
					xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

					admSkillContatoVOTemp = AdmSkillContatoVODocument.Factory.parse(xmlOUT).getAdmSkillContatoVO();
					totalRegistros = Integer.parseInt(admSkillContatoVOTemp.getRegistrosPPagina());
					estruturaSkillTO.getListaContatosExistentes().add(admSkillContatoVOTemp.getListaContatoDisponivel());
				}
			}

			// Lista de Associados
			admSkillContatoVO.setPaginaAtual(ConstantesCRM.SONE);// Ricardo
			admSkillContatoVO.setRegistrosPPagina("400");// Ricardo
			admSkillContatoVO.setInListaContato(ConstantesCRM.SONE);

			xmlIN = admSkillContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
			xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			admSkillContatoVOTemp = AdmSkillContatoVODocument.Factory.parse(xmlOUT).getAdmSkillContatoVO();

			estruturaSkillTO.getListaContatosAssociados().add(admSkillContatoVOTemp.getListaContatoAssociado().getContatoSkillVOArray());

			if (admSkillContatoVOTemp.getRegistrosPPagina().equals(admSkillContatoVO.getRegistrosPPagina())) {
				int j = 1;
				int totalRegistros = 1;
				while (totalRegistros > 0 && totalRegistros <= Integer.parseInt(admSkillContatoVOTemp.getRegistrosPPagina())) {
					j += 1;
					admSkillContatoVO.setPaginaAtual(String.valueOf(j));// Ricardo
					admSkillContatoVO.setRegistrosPPagina("400");// Ricardo
					admSkillContatoVO.setInListaContato(ConstantesCRM.SONE);
					xmlIN = admSkillContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
					xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

					xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

					msgDocRet = MsgDocument.Factory.parse(xmlOUT);
					xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

					admSkillContatoVOTemp = AdmSkillContatoVODocument.Factory.parse(xmlOUT).getAdmSkillContatoVO();
					totalRegistros = Integer.parseInt(admSkillContatoVOTemp.getRegistrosPPagina());
					estruturaSkillTO.getListaContatosAssociados().add(admSkillContatoVOTemp.getListaContatoAssociado());
				}
			}
			return estruturaSkillTO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:getListaContatosByIdSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:getListaContatosByIdSkill", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:getListaContatosByIdSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:getListaContatosByIdSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public EstruturaSkillTO setListaContatos(AdmSkillContatoVO admSkillContatoVO, String user) throws TuxedoException, FacadeException {
		EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();
		try {
			log.debug("ManterUsuarioSkillFacadeImpl:setListaContatos(" + user + ")");
			xmlIN = admSkillContatoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			estruturaSkillTO.setAdmSkillUsuarioVO(AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO());

			return estruturaSkillTO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:setListaContatos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:setListaContatos", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:setListaContatos(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:setListaContatos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public EstruturaSkillTO getUsuarioSkillExistente(AdmSkillUsuarioVO admSkillUsuarioVO, String user) throws TuxedoException, FacadeException {
		EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();
		try {
			log.debug("ManterUsuarioSkillFacadeImpl:getUsuarioSkillExistente(" + user + ")");

			admSkillUsuarioVO.setPaginaAtual(ConstantesCRM.SONE);// Ricardo
			admSkillUsuarioVO.setRegistrosPPagina("400");// Ricardo
			admSkillUsuarioVO.setInOperacao(ConstantesCRM.STWO);// Para Usuario Existente

			xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
			xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			estruturaSkillTO.setAdmSkillUsuarioVO(AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO());
			estruturaSkillTO.getListaUsuariosExistentes().add(estruturaSkillTO.getAdmSkillUsuarioVO().getUsuarioExistenteVOArray());

			try {
				if (estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina().equals(admSkillUsuarioVO.getRegistrosPPagina())) {
					int j = 1;
					int totalRegistros = 1;
					while (totalRegistros > 0 && totalRegistros <= Integer.parseInt(estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina())) {
						j += 1;
						admSkillUsuarioVO.setPaginaAtual(String.valueOf(j));// Ricardo
						admSkillUsuarioVO.setRegistrosPPagina("400");// Ricardo
						xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
						xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

						xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

						msgDocRet = MsgDocument.Factory.parse(xmlOUT);
						xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

						AdmSkillUsuarioVO admUsuarioVOInterno = AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO();
						totalRegistros = Integer.parseInt(admUsuarioVOInterno.getRegistrosPPagina());
						estruturaSkillTO.getListaUsuariosExistentes().add(admUsuarioVOInterno.getUsuarioExistenteVOArray());
					}
					return estruturaSkillTO;
				} else {
					return estruturaSkillTO;
				}
			} catch (Exception ex) {
				return estruturaSkillTO;
			}

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:getUsuarioSkillExistente(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:getUsuarioSkillExistente", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:getUsuarioSkillExistente(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:getUsuarioSkillExistente(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public EstruturaSkillTO getUsuarioSkillAssociado(AdmSkillUsuarioVO admSkillUsuarioVO, String user, EstruturaSkillTO estruturaSkillTO) throws TuxedoException, FacadeException {
		try {
			log.debug("ManterUsuarioSkillFacadeImpl:getUsuarioSkillAssociado(" + user + ")");

			admSkillUsuarioVO.setPaginaAtual(ConstantesCRM.SONE);// Ricardo
			admSkillUsuarioVO.setRegistrosPPagina("400");// Ricardo
			admSkillUsuarioVO.setInOperacao(ConstantesCRM.STHREE);// Para Usuario Associado

			xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
			xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			estruturaSkillTO.setAdmSkillUsuarioVO(AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO());
			estruturaSkillTO.getListaUsuarioSkillAssociado().add(estruturaSkillTO.getAdmSkillUsuarioVO().getUsuarioSelecionadoVOArray());

			try {
				if (estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina().equals(admSkillUsuarioVO.getRegistrosPPagina())) {
					int j = 1;
					int totalRegistros = 1;
					while (totalRegistros > 0 && totalRegistros <= Integer.parseInt(estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina())) {
						j += 1;
						admSkillUsuarioVO.setPaginaAtual(String.valueOf(j));// Ricardo
						admSkillUsuarioVO.setRegistrosPPagina("400");// Ricardo
						xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
						xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

						xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

						msgDocRet = MsgDocument.Factory.parse(xmlOUT);
						xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

						AdmSkillUsuarioVO admUsuarioVOInterno = AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO();
						totalRegistros = Integer.parseInt(admUsuarioVOInterno.getRegistrosPPagina());
						estruturaSkillTO.getListaUsuarioSkillAssociado().add(admUsuarioVOInterno.getUsuarioSelecionadoVOArray());
					}
					return estruturaSkillTO;
				} else {
					return estruturaSkillTO;
				}
			} catch (Exception ex) {
				return estruturaSkillTO;
			}

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:getUsuarioSkillAssociado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:getUsuarioSkillAssociado", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:getUsuarioSkillAssociado(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:getUsuarioSkillAssociado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public EstruturaSkillTO getContatoSkillExistente(AdmSkillUsuarioVO admSkillUsuarioVO, String user) throws TuxedoException, FacadeException {
		EstruturaSkillTO estruturaSkillTO = new EstruturaSkillTO();
		try {
			log.debug("ManterUsuarioSkillFacadeImpl:getContatoSkillExistente(" + user + ")");

			admSkillUsuarioVO.setPaginaAtual(ConstantesCRM.SONE);
			admSkillUsuarioVO.setRegistrosPPagina("400");
			admSkillUsuarioVO.setInOperacao(ConstantesCRM.SFOUR);

			xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			estruturaSkillTO.setAdmSkillUsuarioVO(AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO());
			estruturaSkillTO.getListaContatosExistentes().add(estruturaSkillTO.getAdmSkillUsuarioVO().getContatoExistenteVOArray());

			try {
				if (estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina().equals(admSkillUsuarioVO.getRegistrosPPagina())) {
					int j = 1;
					int totalRegistros = 1;
					while (totalRegistros > 0 && totalRegistros <= Integer.parseInt(estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina())) {
						j += 1;
						admSkillUsuarioVO.setPaginaAtual(String.valueOf(j));// Ricardo
						admSkillUsuarioVO.setRegistrosPPagina("400");// Ricardo
						xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
						xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

						xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

						msgDocRet = MsgDocument.Factory.parse(xmlOUT);
						xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

						AdmSkillUsuarioVO admUsuarioVOInterno = AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO();
						totalRegistros = Integer.parseInt(admUsuarioVOInterno.getRegistrosPPagina());
						estruturaSkillTO.getListaContatosExistentes().add(admUsuarioVOInterno.getContatoExistenteVOArray());
					}
					return estruturaSkillTO;
				} else {
					return estruturaSkillTO;
				}
			} catch (Exception ex) {
				return estruturaSkillTO;
			}

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:getContatoSkillExistente(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:getContatoSkillExistente", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:getContatoSkillExistente(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:getContatoSkillExistente(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public EstruturaSkillTO getContatoSkillAssociado(AdmSkillUsuarioVO admSkillUsuarioVO, String user, EstruturaSkillTO estruturaSkillTO) throws TuxedoException, FacadeException {

		try {
			log.debug("ManterUsuarioSkillFacadeImpl:getContatoSkillAssociado(" + user + ")");

			admSkillUsuarioVO.setPaginaAtual(ConstantesCRM.SONE);// Ricardo
			admSkillUsuarioVO.setRegistrosPPagina("400");// Ricardo
			admSkillUsuarioVO.setInOperacao(ConstantesCRM.SFIVE);// Para Usuario Associado

			xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
			xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			estruturaSkillTO.setAdmSkillUsuarioVO(AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO());
			estruturaSkillTO.getListaContatosAssociados().add(estruturaSkillTO.getAdmSkillUsuarioVO().getContatoSelecionadoVOArray());

			try {
				if (estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina().equals(admSkillUsuarioVO.getRegistrosPPagina())) {
					int j = 1;
					int totalRegistros = 1;
					while (totalRegistros > 0 && totalRegistros <= Integer.parseInt(estruturaSkillTO.getAdmSkillUsuarioVO().getRegistrosPPagina())) {
						j += 1;
						admSkillUsuarioVO.setPaginaAtual(String.valueOf(j));// Ricardo
						admSkillUsuarioVO.setRegistrosPPagina("400");// Ricardo
						xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);// Ricardo
						xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLGET", xmlIN);// Ricardo

						xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

						msgDocRet = MsgDocument.Factory.parse(xmlOUT);
						xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

						AdmSkillUsuarioVO admUsuarioVOInterno = AdmSkillUsuarioVODocument.Factory.parse(xmlOUT).getAdmSkillUsuarioVO();
						totalRegistros = Integer.parseInt(admUsuarioVOInterno.getRegistrosPPagina());
						estruturaSkillTO.getListaContatosAssociados().add(admUsuarioVOInterno.getContatoSelecionadoVOArray());
					}
					return estruturaSkillTO;
				} else {
					return estruturaSkillTO;
				}
			} catch (Exception ex) {
				return estruturaSkillTO;
			}

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:getContatoSkillAssociado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:getContatoSkillAssociado", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:getContatoSkillAssociado(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:getContatoSkillAssociado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void setContatoSkill(AdmSkillUsuarioVO admSkillUsuarioVO, String user) throws TuxedoException, FacadeException {
		try {

			log.debug("ManterUsuarioSkillFacadeImpl:setContatoSkill(" + user + ")");
			admSkillUsuarioVO.setInOperacao(ConstantesCRM.STWO);
			xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLPUT", xmlIN);

			tuxedo.callService("TuxConnector", xmlIN);

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:setContatoSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:setContatoSkill", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:setContatoSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:setContatoSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void setContatoSkillMult(AdmSkillUsuarioVO admSkillUsuarioVO, String user, String[] contatos) throws TuxedoException, FacadeException {
		try {
			log.debug("ManterUsuarioSkillFacadeImpl:setContatoSkillMult(" + user + ")");
			admSkillUsuarioVO.setInOperacao(ConstantesCRM.STWO);

			int totalizador = 0;
			int totalRegistros = 500;
			int resto = 0;
			int contatoLenght = contatos.length;
			int contador = 0;

			// String[] contatoPaginado;
			if (contatoLenght > 0) {
				totalizador = contatos.length / totalRegistros;
				resto = contatos.length % totalRegistros;
				if (resto > 0) {
					totalizador += 1;
				}
				for (int i = 0; i < totalizador; i++) {
					if (i == 0) {
						admSkillUsuarioVO.setDeleteContato(ConstantesCRM.SONE);
					} else {
						admSkillUsuarioVO.setDeleteContato(ConstantesCRM.SZERO);
					}
					admSkillUsuarioVO.setContatosSelecionadosArray(null);

					for (int j = i * totalRegistros; j < (i * totalRegistros) + totalRegistros; j++) {
						if (contatos.length - 1 >= j) {
							if (contatos[j] != null) {
								admSkillUsuarioVO.addContatosSelecionados(contatos[j]);
								// admSkillUsuarioVO.setContatosSelecionadosArray(contador,contatos[j]);
							}
							contador++;
						}
					}
					contador = 0;

					xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
					xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLPUT", xmlIN);

					tuxedo.callService("TuxConnector", xmlIN);
				}

			} else {
				admSkillUsuarioVO.setDeleteContato(ConstantesCRM.SONE);
				xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
				xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLPUT", xmlIN);

				tuxedo.callService("TuxConnector", xmlIN);
			}

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:setContatoSkillMult(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:setContatoSkillMult", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:setContatoSkillMult(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:setContatoSkillMult(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void setUsuarioSkillMult(AdmSkillUsuarioVO admSkillUsuarioVO, String user, String[] usuarios) throws TuxedoException, FacadeException {
		try {
			log.debug("ManterUsuarioSkillFacadeImpl:setUsuarioSkillMult(" + user + ")");
			admSkillUsuarioVO.setInOperacao(ConstantesCRM.SONE);

			int totalizador = 0;
			int totalRegistros = 500;
			int resto = 0;
			int contatoLenght = usuarios.length;
			int contador = 0;

			// String[] contatoPaginado;
			if (contatoLenght > 0) {
				totalizador = usuarios.length / totalRegistros;
				resto = usuarios.length % totalRegistros;
				if (resto > 0) {
					totalizador += 1;
				}
				for (int i = 0; i < totalizador; i++) {
					if (i == 0) {
						admSkillUsuarioVO.setDeleteUsuario(ConstantesCRM.SONE);
					} else {
						admSkillUsuarioVO.setDeleteUsuario(ConstantesCRM.SZERO);
					}
					admSkillUsuarioVO.setUsuariosSelecionadosArray(null);

					for (int j = i * totalRegistros; j < (i * totalRegistros) + totalRegistros; j++) {
						if (usuarios.length - 1 >= j) {
							if (usuarios[j] != null) {
								admSkillUsuarioVO.addUsuariosSelecionados(usuarios[j]);
								// admSkillUsuarioVO.setContatosSelecionadosArray(contador,contatos[j]);
							}
							contador++;
						}
					}
					contador = 0;

					xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
					xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLPUT", xmlIN);

					tuxedo.callService("TuxConnector", xmlIN);
				}

			} else {
				admSkillUsuarioVO.setDeleteUsuario(ConstantesCRM.SONE);
				xmlIN = admSkillUsuarioVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
				xmlIN = XmlManager.MakeXmlIn(user, "GRPSKLPUT", xmlIN);

				tuxedo.callService("TuxConnector", xmlIN);
			}

		} catch (XmlException ex) {
			log.error("XmlException - ManterUsuarioSkillFacadeImpl:setUsuarioSkillMult(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterUsuarioSkillFacadeImpl:setUsuarioSkillMult", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterUsuarioSkillFacadeImpl:setUsuarioSkillMult(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterUsuarioSkillFacadeImpl:setUsuarioSkillMult(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}
}