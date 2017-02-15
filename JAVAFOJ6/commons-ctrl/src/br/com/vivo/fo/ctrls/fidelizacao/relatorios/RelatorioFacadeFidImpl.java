package br.com.vivo.fo.ctrls.fidelizacao.relatorios;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import noNamespace.MsgDocument;

import org.apache.xmlbeans.XmlObject;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.dbclasses.RetencaoStatusSAP;
import br.com.vivo.fo.fidelizacao.vo.RelaMovimenDiariasVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelaMovimenDiariasVODocument.RelaMovimenDiariasVO;
import br.com.vivo.fo.fidelizacao.vo.RelaOfertasTotMensalVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelaOfertasTotMensalVODocument.RelaOfertasTotMensalVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioAdimplenciaVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelatorioAdimplenciaVODocument.RelatorioAdimplenciaVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioCCContaCorrenteVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelatorioCCContaCorrenteVODocument.RelatorioCCContaCorrenteVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioLigacaoRetencaoVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelatorioLigacaoRetencaoVODocument.RelatorioLigacaoRetencaoVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioNotesLojaVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelatorioNotesLojaVODocument.RelatorioNotesLojaVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioOperadorVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelatorioOperadorVODocument.RelatorioOperadorVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioResDestinoVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelatorioResDestinoVODocument.RelatorioResDestinoVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioReteOfertaVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelatorioReteOfertaVODocument.RelatorioReteOfertaVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosDistincaoVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosDistincaoVODocument.RelatorioRetePlanosDistincaoVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosVODocument.RelatorioRetePlanosVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoDistincaoVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoDistincaoVODocument.RelatorioRetencaoDistincaoVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoInputVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoInputVODocument.RelatorioRetencaoInputVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoVODocument.RelatorioRetencaoVO;
import br.com.vivo.fo.fidelizacao.vo.RelatorioTodasOfertasVODocument;
import br.com.vivo.fo.fidelizacao.vo.RelatorioTodasOfertasVODocument.RelatorioTodasOfertasVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlIn;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "RelatorioFacadeFid", mappedName = "RelatorioFacadeFid")
@Local(RelatorioFacadeFid.class)
@Session(ejbName = "RelatorioFacadeFid", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelatorioFacadeFidImpl implements RelatorioFacadeFid {

	@EJB
	private TuxedoServiceCall tuxedo;

	@EJB
	private br.com.vivo.fo.ctrls.fidelizacao.relatorios.RelatoriosControl relatoriosControl;

	private static Logger log = new Logger("fidelizacao");

	/**
	 * @common:operation
	 */
	public RelatorioRetencaoInputVO carregaDadosCriterio(String user) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
			log.debug("RelatorioFacadeFidImpl:carregaDadosCriterio(" + user + ")");
			XmlIn xmlIn = new XmlIn(user, "GETCRITERIO", "<oi></oi>");

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIn.toString());
			xmlOUT = tuxedo.callService("TuxConnector", xmlIn.toString());

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			XmlObject xmlDoc = new XmlManager(xmlOUT).parseXml(RelatorioRetencaoInputVODocument.type);

			RelatorioRetencaoInputVO vo = ((RelatorioRetencaoInputVODocument) xmlDoc).getRelatorioRetencaoInputVO();
			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:carregaDadosCriterio(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelaMovimenDiariasVO relatorioMovimenDiarias(String user, RelatorioPesquisaVO argvo) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;
		
		    log.debug("RelatorioFacadeFidImpl:relatorioMovimenDiarias(" + user + ")");
			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELRETENCAO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelaMovimenDiariasVODocument doc = RelaMovimenDiariasVODocument.Factory.parse(xmlOUT);

			RelaMovimenDiariasVO vo = doc.getRelaMovimenDiariasVO();

			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relatorioMovimenDiarias(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelatorioLigacaoRetencaoVO relatorioLigacaoRetencao(String user, RelatorioPesquisaVO argvo) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;

		    log.debug("RelatorioFacadeFidImpl:relatorioLigacaoRetencao(" + user + ")");
			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELRETENCAO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelatorioLigacaoRetencaoVODocument doc = RelatorioLigacaoRetencaoVODocument.Factory.parse(xmlOUT);

			RelatorioLigacaoRetencaoVO vo = doc.getRelatorioLigacaoRetencaoVO();

			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relatorioLigacaoRetencao(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelatorioOperadorVO relatorioOperador(String user, RelatorioPesquisaVO argvo) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;

		    log.debug("RelatorioFacadeFidImpl:relatorioOperador(" + user + ")");
			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELRETENCAO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelatorioOperadorVODocument doc = RelatorioOperadorVODocument.Factory.parse(xmlOUT);

			RelatorioOperadorVO vo = doc.getRelatorioOperadorVO();

			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relatorioOperador(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelatorioReteOfertaVO relatorioReteOferta(String user, RelatorioPesquisaVO argvo) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;
			log.debug("RelatorioFacadeFidImpl:relatorioReteOferta(" + user + ")");
			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELRETENCAO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelatorioReteOfertaVODocument doc = RelatorioReteOfertaVODocument.Factory.parse(xmlOUT);

			RelatorioReteOfertaVO vo = doc.getRelatorioReteOfertaVO();
			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relatorioReteOferta(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelatorioRetencaoVO relatorioRetencao(String user, RelatorioPesquisaVO argvo) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;

		    log.debug("RelatorioFacadeFidImpl:relatorioRetencao(" + user + ")");
			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELRETENCAO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelatorioRetencaoVODocument doc = RelatorioRetencaoVODocument.Factory.parse(xmlOUT);

			RelatorioRetencaoVO vo = doc.getRelatorioRetencaoVO();

			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relatorioRetencao(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelatorioRetencaoDistincaoVO relaRetencaoDistincao(String user, RelatorioPesquisaVO argvo) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;

		    log.debug("RelatorioFacadeFidImpl:relaRetencaoDistincao(" + user + ")");
			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELRETENCAO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelatorioRetencaoDistincaoVODocument doc = RelatorioRetencaoDistincaoVODocument.Factory.parse(xmlOUT);

			RelatorioRetencaoDistincaoVO vo = doc.getRelatorioRetencaoDistincaoVO();

			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relaRetencaoDistincao(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelatorioTodasOfertasVO relaTodasOfertas(String user, RelatorioPesquisaVO argvo) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;

		    log.debug("RelatorioFacadeFidImpl:relaTodasOfertas(" + user + ")");
			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELRETENCAO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelatorioTodasOfertasVODocument doc = RelatorioTodasOfertasVODocument.Factory.parse(xmlOUT);

			RelatorioTodasOfertasVO vo = doc.getRelatorioTodasOfertasVO();

			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relaTodasOfertas(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelatorioResDestinoVO relaResDestino(String user, RelatorioPesquisaVO argvo) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;

		    log.debug("RelatorioFacadeFidImpl:relaResDestino(" + user + ")");
			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELRETENCAO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelatorioResDestinoVODocument doc = RelatorioResDestinoVODocument.Factory.parse(xmlOUT);

			RelatorioResDestinoVO vo = doc.getRelatorioResDestinoVO();

			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relaResDestino(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelaOfertasTotMensalVO relaOfertasTotMensal(String user, RelatorioPesquisaVO argvo) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;

		    log.debug("RelatorioFacadeFidImpl:relaOfertasTotMensal(" + user + ")");
			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELRETENCAO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelaOfertasTotMensalVODocument doc = RelaOfertasTotMensalVODocument.Factory.parse(xmlOUT);
			RelaOfertasTotMensalVO vo = doc.getRelaOfertasTotMensalVO();
			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relaOfertasTotMensal(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelatorioRetePlanosVO relaPlanos(String user, RelatorioPesquisaVO argvo) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;

		    log.debug("RelatorioFacadeFidImpl:relaPlanos(" + user + ")");
			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELRETENCAO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelatorioRetePlanosVODocument doc = RelatorioRetePlanosVODocument.Factory.parse(xmlOUT);
			RelatorioRetePlanosVO vo = doc.getRelatorioRetePlanosVO();
			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relaPlanos(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelatorioRetePlanosDistincaoVO relaPlanosDistincao(String user, RelatorioPesquisaVO argvo) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;
			log.debug("RelatorioFacadeFidImpl:relaPlanosDistincao(" + user + ")");
			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELRETENCAO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelatorioRetePlanosDistincaoVODocument doc = RelatorioRetePlanosDistincaoVODocument.Factory.parse(xmlOUT);
			RelatorioRetePlanosDistincaoVO vo = doc.getRelatorioRetePlanosDistincaoVO();
			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relaPlanosDistincao(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelatorioCCContaCorrenteVO relaCCContaCorrente(String user, RelatorioPesquisaVO argvo) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;

		    log.debug("RelatorioFacadeFidImpl:relaCCContaCorrente(" + user + ")");
			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELRETENCAO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelatorioCCContaCorrenteVODocument doc = RelatorioCCContaCorrenteVODocument.Factory.parse(xmlOUT);
			RelatorioCCContaCorrenteVO vo = doc.getRelatorioCCContaCorrenteVO();
			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relaCCContaCorrente(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelatorioNotesLojaVO relaNotesLoja(String user, RelatorioPesquisaVO argvo) throws Exception {
		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;

		    log.debug("RelatorioFacadeFidImpl:relaNotesLoja(" + user + ")");
			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELRETENCAO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelatorioNotesLojaVODocument doc = RelatorioNotesLojaVODocument.Factory.parse(xmlOUT);
			RelatorioNotesLojaVO vo = doc.getRelatorioNotesLojaVO();
			return vo;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relaNotesLoja(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}

	/**
	 * @common:operation
	 */
	public RelatorioAdimplenciaVO relatorioAdimplencia(String user, RelatorioPesquisaVO argvo) throws Exception {

		log.debug("RelatorioFacadeFidImpl:relatorioAdimplencia(" + user + ")");

		try {
		    String xmlOUT = ConstantesCRM.SVAZIO;
		    String xmlIN = ConstantesCRM.SVAZIO;

			xmlIN = argvo.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = XmlManager.MakeXmlIn(user, "RELVOLDIARIO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, fidelizaTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RelatorioAdimplenciaVODocument doc = RelatorioAdimplenciaVODocument.Factory.parse(xmlOUT);

			return doc.getRelatorioAdimplenciaVO();

		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:relatorioAdimplencia(" + user + ") - [" + ex.getMessage() + "]");
			throw ex;
		}

	}

	/**
	 * @common:operation
	 */
	public RetencaoStatusSAP[] buscarStatusSAP(String idUsuario, String nrLinha) throws Exception {

		try {

			log.debug("RelatorioFacadeFidImpl:buscarStatusSAP(" + idUsuario + ")");

			RetencaoStatusSAP[] retencaoStatusSAPArray = new RetencaoStatusSAP[0];

			StringBuffer query = new StringBuffer().append("  SELECT U.NMLOGINUSUARIO, ");
			query.append("         SP.VLIDREFERENCIA NRPEDIDO, ");
			query.append("         SP.DSOBSERVACAO, ");
			query.append("         SP.DTULTIMAALTERACAO, ");
			query.append("         SP.VLLOGXMLIN XML ");
			query.append("    FROM RETENCAO.STATUSSAP SP, ");
			query.append("         ACESSO.USUARIO U ");
			query.append("   WHERE SP.IDUSUARIOALTERACAO = U.IDPESSOAUSUARIO ");
			query.append("     AND VLLOGXMLIN LIKE '%").append(nrLinha).append("%' ");
			query.append("   ORDER BY DTULTIMAALTERACAO DESC ");

			retencaoStatusSAPArray = relatoriosControl.getStatusSAPArray(query.toString());

			return retencaoStatusSAPArray;

		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeFidImpl:buscarStatusSAP(" + idUsuario + ") - [" + ex.getMessage() + "]");
			throw ex;
		}
	}
}
