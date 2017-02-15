package br.com.vivo.fo.ctrls.usuario.manterGrupo;

import java.util.HashMap;

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
import br.com.vivo.fo.admsistemas.vo.CarterizacaoVODocument.CarterizacaoVO;
import br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO;
import br.com.vivo.fo.admsistemas.vo.ProcedenciaVODocument.ProcedenciaVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoGravarVODocument.RegrasEncaminhamentoGravarVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoGravarVODocument.RegrasEncaminhamentoGravarVO.RegionalVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoVODocument;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoVODocument.RegrasEncaminhamentoVO;
import br.com.vivo.fo.admsistemas.vo.SegmentacaoVODocument.SegmentacaoVO;
import br.com.vivo.fo.admsistemas.vo.TipoClienteVODocument.TipoClienteVO;
import br.com.vivo.fo.admsistemas.vo.impl.CarterizacaoVODocumentImpl.CarterizacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.GrupoVODocumentImpl.GrupoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.ProcedenciaVODocumentImpl.ProcedenciaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.SegmentacaoVODocumentImpl.SegmentacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.TipoClienteVODocumentImpl.TipoClienteVOImpl;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.cliente.vo.TipoLinhaVODocument.TipoLinhaVO;
import br.com.vivo.fo.cliente.vo.impl.TipoLinhaVODocumentImpl.TipoLinhaVOImpl;
import br.com.vivo.fo.commons.utils.geral.ControlXMLExceptionLookup;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.fidelizacao.vo.TipoPessoaVODocument.TipoPessoaVO;
import br.com.vivo.fo.fidelizacao.vo.impl.TipoPessoaVODocumentImpl.TipoPessoaVOImpl;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.GrupamentosUsuarioVODocument;
import br.com.vivo.fo.usuario.vo.GrupamentosUsuarioVODocument.GrupamentosUsuarioVO;
import br.com.vivo.fo.usuario.vo.GrupoCanalRelacionadoVODocument.GrupoCanalRelacionadoVO;
import br.com.vivo.fo.usuario.vo.GrupoPerfilRelacionadoVODocument.GrupoPerfilRelacionadoVO;
import br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument;
import br.com.vivo.fo.usuario.vo.GrupoUsuarioVODocument.GrupoUsuarioVO;
import br.com.vivo.fo.usuario.vo.GruposUsuarioVODocument;
import br.com.vivo.fo.usuario.vo.GruposUsuarioVODocument.GruposUsuarioVO;
import br.com.vivo.fo.usuario.vo.ManterSkillVODocument;
import br.com.vivo.fo.usuario.vo.ManterSkillVODocument.ManterSkillVO;
import br.com.vivo.fo.usuario.vo.PerfilGrupamentoUsuarioVODocument.PerfilGrupamentoUsuarioVO;
import br.com.vivo.fo.usuario.vo.PerfilUnidadeUsuarioVODocument;
import br.com.vivo.fo.usuario.vo.PerfilUnidadeUsuarioVODocument.PerfilUnidadeUsuarioVO;
import br.com.vivo.fo.usuario.vo.PerfilUsuarioVODocument.PerfilUsuarioVO;
import br.com.vivo.fo.usuario.vo.PerfisUsuarioVODocument;
import br.com.vivo.fo.usuario.vo.PerfisUsuarioVODocument.PerfisUsuarioVO;
import br.com.vivo.fo.usuario.vo.PesquisaSkillVODocument.PesquisaSkillVO;
import br.com.vivo.fo.usuario.vo.RelacionarGrupoCanalVODocument;
import br.com.vivo.fo.usuario.vo.RelacionarGrupoCanalVODocument.RelacionarGrupoCanalVO;
import br.com.vivo.fo.usuario.vo.RelacionarGrupoPerfilVODocument;
import br.com.vivo.fo.usuario.vo.RelacionarGrupoPerfilVODocument.RelacionarGrupoPerfilVO;
import br.com.vivo.fo.usuario.vo.SistemasUsuarioVODocument;
import br.com.vivo.fo.usuario.vo.SistemasUsuarioVODocument.SistemasUsuarioVO;
import br.com.vivo.fo.workflow.vo.CanalVODocument.CanalVO;
import br.com.vivo.fo.xml.RetornoTuxedo;
import br.com.vivo.fo.xml.XmlHeader;
import br.com.vivo.fo.xml.XmlManager;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="ManterGrupoFacade",mappedName="ManterGrupoFacade")
@Local(ManterGrupoFacade.class)
@Session(ejbName = "ManterGrupoFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class ManterGrupoFacadeImpl implements ManterGrupoFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	static final long serialVersionUID = 1L;
	private String xmlIN;
	private String xmlOUT;
	private String[] retTux = new String[0];
	private GruposUsuarioVO gruposVO = null;
	private PerfisUsuarioVO perfisUsuarioVO = null;
	private SistemasUsuarioVO sistemasUsuarioVO = null;
	private GrupamentosUsuarioVO grupamentosUsuarioVO = null;

	private static Logger log = new Logger("usuario");

	/**
	 * @common:operation
	 */
	public GruposUsuarioVO cadastraGrupo(GrupoUsuarioVO grupoVO, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = grupoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GrpInserir", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

			gruposVO = GruposUsuarioVODocument.Factory.parse(xmlOUT).getGruposUsuarioVO();

			return gruposVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:cadastraGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:cadastraGrupo", ex));

		 } catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:cadastraGrupo(" + user + ") - [" + te.getMessage() + "]");
	         throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:cadastraGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public GruposUsuarioVO editaGrupo(GrupoUsuarioVO grupoVOedita, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = grupoVOedita.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GrpEditar", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

			gruposVO = GruposUsuarioVODocument.Factory.parse(xmlOUT).getGruposUsuarioVO();

			return gruposVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:editaGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:editaGrupo", ex));

		} catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:editaGrupo(" + user + ") - [" + te.getMessage() + "]");
	        throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:editaGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public GruposUsuarioVO listaGrupo(String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = ConstantesCRM.SVAZIO;
			xmlIN = XmlManager.MakeXmlIn(user, "GrpLista", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

			gruposVO = GruposUsuarioVODocument.Factory.parse(xmlOUT).getGruposUsuarioVO();

			return gruposVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:listaGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:listaGrupo", ex));
			
		} catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterGrupoFacadeImpl:editaGrupo(" + user + ") - [" + te.getMessage() + "]");
            throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:listaGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public GruposUsuarioVO removeGrupo(GrupoUsuarioVO grupoVOremove, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = grupoVOremove.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GrpRemover", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

			gruposVO = GruposUsuarioVODocument.Factory.parse(xmlOUT).getGruposUsuarioVO();

			return gruposVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:removeGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:removeGrupo", ex));

		} catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterGrupoFacadeImpl:removeGrupo(" + user + ") - [" + te.getMessage() + "]");
            throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:removeGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public GruposUsuarioVO pesquisaGrupo(GrupoUsuarioVO grupoVOpesquisa, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = grupoVOpesquisa.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GrpListaPar", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

			gruposVO = GruposUsuarioVO.Factory.newInstance();
			gruposVO = GruposUsuarioVODocument.Factory.parse(xmlOUT).getGruposUsuarioVO();

			return gruposVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:pesquisaGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:pesquisaGrupo", ex));

		} catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterGrupoFacadeImpl:pesquisaGrupo(" + user + ") - [" + te.getMessage() + "]");
            throw te;
            
		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:pesquisaGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public RelacionarGrupoCanalVO listaRelacionarGrupoCanal(GrupoUsuarioVO grupoRelacionarCanal, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = grupoRelacionarCanal.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GrpCnlRelacao", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

			RelacionarGrupoCanalVO relacionarGrupoCanalVO = null;
			relacionarGrupoCanalVO = RelacionarGrupoCanalVODocument.Factory.parse(xmlOUT).getRelacionarGrupoCanalVO();

			return relacionarGrupoCanalVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:listaRelacionarGrupoCanal(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:listaRelacionarGrupoCanal", ex));

		} catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterGrupoFacadeImpl:listaRelacionarGrupoCanal(" + user + ") - [" + te.getMessage() + "]");
            throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:listaRelacionarGrupoCanal(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public RelacionarGrupoPerfilVO listaRelacionarGrupoPerfil(GrupoUsuarioVO grupoRelacionarPerfil, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = grupoRelacionarPerfil.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GrpPrfRelacao", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

			RelacionarGrupoPerfilVO relacionarGrupoPerfilVO = null;
			relacionarGrupoPerfilVO = RelacionarGrupoPerfilVODocument.Factory.parse(xmlOUT).getRelacionarGrupoPerfilVO();

			return relacionarGrupoPerfilVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:listaRelacionarGrupoPerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:listaRelacionarGrupoPerfil", ex));

		} catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterGrupoFacadeImpl:listaRelacionarGrupoPerfil(" + user + ") - [" + te.getMessage() + "]");
            throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:listaRelacionarGrupoPerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public RelacionarGrupoCanalVO salvaGrupoCanalRelacionado(GrupoCanalRelacionadoVO grupoCanalRelacionado, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = grupoCanalRelacionado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GRPCNLRELACIO", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

			RelacionarGrupoCanalVO relacionarGrupoCanalVO = null;
			relacionarGrupoCanalVO = RelacionarGrupoCanalVODocument.Factory.parse(xmlOUT).getRelacionarGrupoCanalVO();

			return relacionarGrupoCanalVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:salvaGrupoCanalRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:salvaGrupoCanalRelacionado", ex));
		
		} catch (TuxedoWarningException te) {
            log.error("TuxedoWarningException - ManterGrupoFacadeImpl:salvaGrupoCanalRelacionado(" + user + ") - [" + te.getMessage() + "]");
            throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:salvaGrupoCanalRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public RelacionarGrupoPerfilVO salvarGrupoPerfilRelacionado(GrupoPerfilRelacionadoVO grupoPerfilRelacionado, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = grupoPerfilRelacionado.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "GRPPRFRELACIO", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);


			RelacionarGrupoPerfilVO relacionarGrupoPerfilVO = null;
			relacionarGrupoPerfilVO = RelacionarGrupoPerfilVODocument.Factory.parse(xmlOUT).getRelacionarGrupoPerfilVO();

			return relacionarGrupoPerfilVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:salvarGrupoPerfilRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:salvarGrupoPerfilRelacionado", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterGrupoFacadeImpl:salvarGrupoPerfilRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:salvarGrupoPerfilRelacionado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public PerfisUsuarioVO cadastraPerfil(PerfilUsuarioVO perfilVO, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = perfilVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "PrfInserir", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

			perfisUsuarioVO = null;

			perfisUsuarioVO = PerfisUsuarioVODocument.Factory.parse(xmlOUT).getPerfisUsuarioVO();

			return perfisUsuarioVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:cadastraPerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:cadastraPerfil", ex));

		} catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:cadastraPerfil(" + user + ") - [" + te.getMessage() + "]");
	        throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:cadastraPerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public PerfisUsuarioVO editaPerfil(PerfilUsuarioVO perfilVOedita, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = perfilVOedita.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "PrfEditar", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

			perfisUsuarioVO = null;
			perfisUsuarioVO = PerfisUsuarioVODocument.Factory.parse(xmlOUT).getPerfisUsuarioVO();

			return perfisUsuarioVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:editaPerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:editaPerfil", ex));

		} catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:editaPerfil(" + user + ") - [" + te.getMessage() + "]");
	        throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:editaPerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public PerfisUsuarioVO listaPerfil(String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = ConstantesCRM.SVAZIO;
			xmlIN = XmlManager.MakeXmlIn(user, "PrfLista", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);

			perfisUsuarioVO = null;
			perfisUsuarioVO = PerfisUsuarioVODocument.Factory.parse(xmlOUT).getPerfisUsuarioVO();

			return perfisUsuarioVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:listaPerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:listaPerfil", ex));

		} catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:listaPerfil(" + user + ") - [" + te.getMessage() + "]");
	        throw te;


		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:listaPerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public PerfisUsuarioVO removePerfil(PerfilUsuarioVO perfilVOremove, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = perfilVOremove.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "PrfRemover", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);


			perfisUsuarioVO = null;
			perfisUsuarioVO = PerfisUsuarioVODocument.Factory.parse(xmlOUT).getPerfisUsuarioVO();

			return perfisUsuarioVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:removePerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:removePerfil", ex));
		
		} catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:removePerfil(" + user + ") - [" + te.getMessage() + "]");
	        throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:removePerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public PerfisUsuarioVO pesquisaPerfil(PerfilUsuarioVO perfilVOpesquisa, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = perfilVOpesquisa.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "PrfListaPar", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);


			perfisUsuarioVO = null;
			perfisUsuarioVO = PerfisUsuarioVODocument.Factory.parse(xmlOUT).getPerfisUsuarioVO();

			return perfisUsuarioVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:pesquisaPerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:pesquisaPerfil", ex));

		} catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:pesquisaPerfil(" + user + ") - [" + te.getMessage() + "]");
	        throw te;
	        
		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:pesquisaPerfil(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public SistemasUsuarioVO listaSistemas(String user) throws TuxedoException, FacadeException {

		try {
			xmlIN = "<sgSistemas></sgSistemas>";
			xmlIN = XmlManager.MakeXmlIn(user, "SisLista", xmlIN);

			try {
				xmlOUT = "";// usuarioTux.GETSERVICE(xmlIN);
				xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			} catch (Exception ex) {
				xmlOUT = ControlXMLExceptionLookup.getXMLString(ex);
			}
			
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			retTux = XmlManager.ParseXmlOut(xmlOUT);

			RetornoTuxedo.TrataCodigoExecucao(getClass(), retTux);

			sistemasUsuarioVO = null;
			sistemasUsuarioVO = SistemasUsuarioVODocument.Factory.parse(retTux[4]).getSistemasUsuarioVO();

			return sistemasUsuarioVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:listaSistemas(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:listaSistemas", ex));

		} catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:listaSistemas(" + user + ") - [" + te.getMessage() + "]");
	        throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:listaSistemas(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public GrupamentosUsuarioVO salvaPerfilGrupamentoRelacionados(PerfilGrupamentoUsuarioVO perfilGrupamentoVO, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = perfilGrupamentoVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "PRFGPTRELACIO", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);


			grupamentosUsuarioVO = null;
			grupamentosUsuarioVO = GrupamentosUsuarioVODocument.Factory.parse(xmlOUT).getGrupamentosUsuarioVO();

			return grupamentosUsuarioVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:salvaPerfilGrupamentoRelacionados(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:salvaPerfilGrupamentoRelacionados", ex));

		} catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:salvaPerfilGrupamentoRelacionados(" + user + ") - [" + te.getMessage() + "]");
	        throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:salvaPerfilGrupamentoRelacionados(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public PerfilUnidadeUsuarioVO salvaPerfilUnidadesRelacionadas(PerfilUnidadeUsuarioVO perfilUnidadeVO, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = perfilUnidadeVO.xmlText();
			xmlIN = xmlIN.replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "PRFUNIRELACIO", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);


			PerfilUnidadeUsuarioVO perfilUnidadeUsuarioVO = null;
			perfilUnidadeUsuarioVO = PerfilUnidadeUsuarioVODocument.Factory.parse(xmlOUT).getPerfilUnidadeUsuarioVO();

			return perfilUnidadeUsuarioVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:salvaPerfilUnidadesRelacionadas(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:salvaPerfilUnidadesRelacionadas", ex));

		} catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:salvaPerfilUnidadesRelacionadas(" + user + ") - [" + te.getMessage() + "]");
	        throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:salvaPerfilUnidadesRelacionadas(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public GrupamentosUsuarioVO listaRelacionarPerfilGrupamento(PerfilUsuarioVO sistemaRecuperaAgrupamentos, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = sistemaRecuperaAgrupamentos.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "PrfGptRelacao", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);


			GrupamentosUsuarioVO grupamentosUsuarioVO = null;
			grupamentosUsuarioVO = GrupamentosUsuarioVODocument.Factory.parse(xmlOUT).getGrupamentosUsuarioVO();

			return grupamentosUsuarioVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:listaRelacionarPerfilGrupamento(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:listaRelacionarPerfilGrupamento", ex));

		} catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:listaRelacionarPerfilGrupamento(" + user + ") - [" + te.getMessage() + "]");
	        throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:listaRelacionarPerfilGrupamento(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public PerfilUnidadeUsuarioVO listaRelacionarPerfilUnidades(PerfilUnidadeUsuarioVO listaUnidades, String user) throws TuxedoException, FacadeException {
		try {
			xmlIN = listaUnidades.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "PrfUniRelacao", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);


			PerfilUnidadeUsuarioVO perfilUnidadeUsuarioVO = null;
			perfilUnidadeUsuarioVO = PerfilUnidadeUsuarioVODocument.Factory.parse(xmlOUT).getPerfilUnidadeUsuarioVO();

			return perfilUnidadeUsuarioVO;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:listaRelacionarPerfilUnidades(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:listaRelacionarPerfilUnidades", ex));

		} catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:listaRelacionarPerfilUnidades(" + user + ") - [" + te.getMessage() + "]");
	        throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:listaRelacionarPerfilUnidades(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public GrupoUsuarioVO getTiposGrupo(String user) throws TuxedoException, FacadeException {
		try {
			GrupoUsuarioVODocument tipo = GrupoUsuarioVODocument.Factory.newInstance();

			xmlIN = "<inTipo>1</inTipo>";
			xmlIN = XmlManager.MakeXmlIn(user, "GrpListaPar", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			tipo = GrupoUsuarioVODocument.Factory.parse(xmlOUT);

			return tipo.getGrupoUsuarioVO();

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:getTiposGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:getTiposGrupo", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterGrupoFacadeImpl:getTiposGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:getTiposGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ManterSkillVO getListaSkill(String user, PesquisaSkillVO pesquisaSkill) throws TuxedoException, FacadeException {
		try {
			ManterSkillVODocument tipo = ManterSkillVODocument.Factory.newInstance();
			xmlIN = pesquisaSkill.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "SKLLISTAR", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			tipo = ManterSkillVODocument.Factory.parse(xmlOUT);

			return (tipo.getManterSkillVO());

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:getListaSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:getListaSkill", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterGrupoFacadeImpl:getListaSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:getListaSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void removeSkill(String user, String idSkill) throws TuxedoException, FacadeException {
		try {
			xmlIN = "<idSkill>" + idSkill + "</idSkill>";
			xmlIN = XmlManager.MakeXmlIn(user, "SKLEXCLUIR", xmlIN);

			tuxedo.callService("TuxConnector", xmlIN);

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:removeSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:removeSkill", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterGrupoFacadeImpl:removeSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:removeSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void setHistoricoConfigVariaveis(String user, String dsLogin, String idGrupo) throws TuxedoException, FacadeException {
		try {
			xmlIN = new StringBuffer("<HistoricoConfigVariaveis>").append("<dsLogin>").append(dsLogin).append("</dsLogin>").append("<idGrupo>").append(idGrupo).append("</idGrupo>").append("</HistoricoConfigVariaveis>").toString();
			xmlIN = XmlManager.MakeXmlIn(user, "ENCAMINHIST", xmlIN);

			tuxedo.callService("TuxConnector", xmlIN);

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:setHistoricoConfigVariaveis(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:removeSkill", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterGrupoFacadeImpl:setHistoricoConfigVariaveis(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:setHistoricoConfigVariaveis(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public ManterSkillVO inserirAlterarSkill(String user, ManterSkillVO manterSkillVO) throws TuxedoException, FacadeException {
		try {
			ManterSkillVODocument tipo = ManterSkillVODocument.Factory.newInstance();

			xmlIN = manterSkillVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "SKLINSALT", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);


			tipo = ManterSkillVODocument.Factory.parse(xmlOUT);

			return tipo.getManterSkillVO();

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:inserirAlterarSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:inserirAlterarSkill", ex));

		} catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:inserirAlterarSkill(" + user + ") - [" + te.getMessage() + "]");
	        throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:inserirAlterarSkill(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public RegrasEncaminhamentoVO getRegrasEncaminhamento(String user, String idSkill) throws TuxedoException, FacadeException {
		try {
			RegrasEncaminhamentoVO regrasEncaminhamento = null;

			xmlIN = "<idSkill>" + idSkill + "</idSkill>";
			xmlIN = XmlManager.MakeXmlIn(user, "SKLLSTREGRAS", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			
			
			MsgHeaderVO msgHeaderVO = msgDocRet.getMsg().getMsgHdr();
            tratarWarningException(msgHeaderVO);


			regrasEncaminhamento = null;
			regrasEncaminhamento = RegrasEncaminhamentoVODocument.Factory.parse(xmlOUT).getRegrasEncaminhamentoVO();

			return regrasEncaminhamento;

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:getRegrasEncaminhamento(" + user + ", " + idSkill + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:getRegrasEncaminhamento", ex));

		} catch (TuxedoWarningException te) {
	        log.error("TuxedoWarningException - ManterGrupoFacadeImpl:getRegrasEncaminhamento(" + user + ") - [" + te.getMessage() + "]");
	        throw te;

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:getRegrasEncaminhamento(" + user + ", " + idSkill + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	@SuppressWarnings("rawtypes")
	public void setRegrasEncaminhamento(String user, HashMap gravarElementos) throws FacadeException, TuxedoException {
		try {
			log.debug(new StringBuffer("ManterGrupoFacadeImpl:setRegrasEncaminhamento(").append(user).append(", ").append(gravarElementos).append(")").toString());

			// Obtem elementos informados para gravação das regras de
			// encaminhamento selecionados

			int codigoGrupo = Integer.parseInt((String) gravarElementos.get(ConstantesCRM.CT_GrupoVO));
			String tipoLinha[] = (String[]) gravarElementos.get(ConstantesCRM.CT_TipoLinhaVO);
			String segmentacao[] = (String[]) gravarElementos.get(ConstantesCRM.CT_SegmentacaoVO);
			String carterizacao[] = (String[]) gravarElementos.get(ConstantesCRM.CT_CarterizacaoVO);
			String procedencia[] = (String[]) gravarElementos.get(ConstantesCRM.CT_ProcedenciaVO);

			String tipoCliente[] = (String[]) gravarElementos.get(ConstantesCRM.CT_TipoClienteVO);
			String tipoPessoa[] = (String[]) gravarElementos.get(ConstantesCRM.CT_TipoPessoaVO);
			String tipoAbertura[] = (String[]) gravarElementos.get(ConstantesCRM.CT_TipoAberturaVO);
			String canal[] = (String[]) gravarElementos.get(ConstantesCRM.CT_CanalVO);
			String regional[] = (String[]) gravarElementos.get(ConstantesCRM.CT_RegionalVO);

			// Instancia Objetos
			int x;
			GrupoVO grupoVO = GrupoVO.Factory.newInstance();
			TipoLinhaVO tipoLinhaVO[] = new TipoLinhaVOImpl[tipoLinha.length];
			SegmentacaoVO segmentacaoVO[] = new SegmentacaoVOImpl[segmentacao.length];
			CarterizacaoVO carterizacaoVO[] = new CarterizacaoVOImpl[carterizacao.length];
			ProcedenciaVO procedenciaVO[] = new ProcedenciaVOImpl[procedencia.length];

			TipoClienteVO tipoClienteVO[] = new TipoClienteVOImpl[tipoCliente.length];
			TipoPessoaVO tipoPessoaVO[] = new TipoPessoaVOImpl[tipoPessoa.length];
			GrupoVO tipoAberturaVO[] = new GrupoVOImpl[tipoAbertura.length];
			CanalVO canalVO[] = new CanalVO[canal.length];
			RegionalVO regionalVO[] = new RegionalVO[regional.length];

			// Monta o grupo
			grupoVO.setCodigo(codigoGrupo);

			// Monta a lista de tipo carteira
			for (x = 0; x < tipoLinha.length; x++) {
				tipoLinhaVO[x] = TipoLinhaVO.Factory.newInstance();
				tipoLinhaVO[x].setId(tipoLinha[x]);
			}

			// Monta a lista de segmentacao
			for (x = 0; x < segmentacao.length; x++) {
				segmentacaoVO[x] = SegmentacaoVO.Factory.newInstance();
				segmentacaoVO[x].setCodigo(Integer.parseInt(segmentacao[x]));
			}

			// Monta a lista de carterização
			for (x = 0; x < carterizacao.length; x++) {
				carterizacaoVO[x] = CarterizacaoVO.Factory.newInstance();
				carterizacaoVO[x].setCodigo(Integer.parseInt(carterizacao[x]));
			}

			// Monta a lista de procedencia
			for (x = 0; x < procedencia.length; x++) {
				procedenciaVO[x] = ProcedenciaVO.Factory.newInstance();
				procedenciaVO[x].setCodigo(Integer.parseInt(procedencia[x]));
			}

			// Monta a lista de tipo cliente
			for (x = 0; x < tipoCliente.length; x++) {
				tipoClienteVO[x] = TipoClienteVO.Factory.newInstance();
				tipoClienteVO[x].setCodigo(Integer.parseInt(tipoCliente[x]));
			}

			// Monta a lista de tipo pessoa
			for (x = 0; x < tipoPessoaVO.length; x++) {
				tipoPessoaVO[x] = TipoPessoaVO.Factory.newInstance();
				tipoPessoaVO[x].setIdtipopessoaArray(new int[] { Integer.parseInt(tipoPessoa[x]) });
			}

			// Monta a lista de grupos de abertura
			for (x = 0; x < tipoAbertura.length; x++) {
				tipoAberturaVO[x] = GrupoVO.Factory.newInstance();
				tipoAberturaVO[x].setCodigo(Integer.parseInt(tipoAbertura[x]));
			}

			// Monta a lista de canais
			for (x = 0; x < canal.length; x++) {
				canalVO[x] = CanalVO.Factory.newInstance();
				canalVO[x].setIdCanal(Integer.parseInt(canal[x]));
			}

			// Monta a lista de regionais
			for (x = 0; x < regional.length; x++) {
				regionalVO[x] = RegionalVO.Factory.newInstance();
				regionalVO[x].setIdRegional(regional[x]);
			}

			// Monta o vo para envio ao tuxedo
			RegrasEncaminhamentoGravarVO regrasEncaminhamentoGravarVO = RegrasEncaminhamentoGravarVO.Factory.newInstance();
			regrasEncaminhamentoGravarVO.setCodigoGrupo(codigoGrupo);
			regrasEncaminhamentoGravarVO.setTipoLinhaVOArray(tipoLinhaVO);
			regrasEncaminhamentoGravarVO.setSegmentacaoVOArray(segmentacaoVO);
			regrasEncaminhamentoGravarVO.setCarterizacaoVOArray(carterizacaoVO);
			regrasEncaminhamentoGravarVO.setProcedenciaVOArray(procedenciaVO);

			regrasEncaminhamentoGravarVO.setTipoClienteVOArray(tipoClienteVO);
			regrasEncaminhamentoGravarVO.setTipoPessoaVOArray(tipoPessoaVO);
			regrasEncaminhamentoGravarVO.setGrupoVOArray(tipoAberturaVO);
			regrasEncaminhamentoGravarVO.setCanalVOArray(canalVO);
			regrasEncaminhamentoGravarVO.setRegionalVOArray(regionalVO);

			// Monta XML IN de acordo com o serviço a ser executado
			xmlIN = XmlManager.MakeXmlIn(user, "SKLINSREGRAS", regrasEncaminhamentoGravarVO.xmlText().replaceAll("vo:", ""));

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

		} catch (XmlException ex) {
			log.error("XmlException - ManterGrupoFacadeImpl:setRegrasEncaminhamento(" + user + ", " + gravarElementos + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ManterGrupoFacadeImpl:setRegrasEncaminhamento", ex));

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - ManterGrupoFacadeImpl:setRegrasEncaminhamento(" + user + ", " + gravarElementos + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - ManterGrupoFacadeImpl:setRegrasEncaminhamento(" + user + ", " + gravarElementos + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}
	
	 private void tratarWarningException(MsgHeaderVO msgHeaderVO) throws TuxedoWarningException {

	        if (msgHeaderVO != null && msgHeaderVO.getStatusCode().indexOf("W") > -1) {
	            XmlHeader xmlHeader = new XmlHeader(msgHeaderVO.getService(), msgHeaderVO.getUser(), "fo", 'W', msgHeaderVO.getStatusCode().substring(0, 4), msgHeaderVO.getStatusText());
	            throw new TuxedoWarningException(xmlHeader);
	        } else if (msgHeaderVO != null && msgHeaderVO.getStatusCode().indexOf("E") > -1) {
	            XmlHeader xmlHeader = new XmlHeader(msgHeaderVO.getService(), msgHeaderVO.getUser(), "fo", 'E', msgHeaderVO.getStatusCode().substring(0, 4), msgHeaderVO.getStatusText());
	            throw new TuxedoWarningException(xmlHeader);
	        }

	    }
}
