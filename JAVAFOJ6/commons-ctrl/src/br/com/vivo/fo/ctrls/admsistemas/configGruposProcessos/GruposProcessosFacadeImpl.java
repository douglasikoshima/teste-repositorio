package br.com.vivo.fo.ctrls.admsistemas.configGruposProcessos;

import java.util.ArrayList;
import java.util.HashMap;

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
import br.com.vivo.fo.admsistemas.vo.CarterizacaoVODocument.CarterizacaoVO;
import br.com.vivo.fo.admsistemas.vo.GrupoProcessoVODocument;
import br.com.vivo.fo.admsistemas.vo.GrupoProcessoVODocument.GrupoProcessoVO;
import br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO;
import br.com.vivo.fo.admsistemas.vo.GruposProcessosVODocument;
import br.com.vivo.fo.admsistemas.vo.GruposProcessosVODocument.GruposProcessosVO;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoNiveisVODocument;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoNiveisVODocument.GruposTratamentoNiveisVO;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoNiveisVODocument.GruposTratamentoNiveisVO.GrupoInicio;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoNiveisVODocument.GruposTratamentoNiveisVO.GrupoTratamento.Disponiveis;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoNiveisVODocument.GruposTratamentoNiveisVO.GrupoTratamento.Sequencia;
import br.com.vivo.fo.admsistemas.vo.ProcedenciaVODocument.ProcedenciaVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoGravarVODocument.RegrasEncaminhamentoGravarVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoGravarVODocument.RegrasEncaminhamentoGravarVO.RegionalVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoVODocument;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoVODocument.RegrasEncaminhamentoVO;
import br.com.vivo.fo.admsistemas.vo.SegmentacaoVODocument.SegmentacaoVO;
import br.com.vivo.fo.admsistemas.vo.TipoClienteVODocument.TipoClienteVO;
import br.com.vivo.fo.admsistemas.vo.impl.CarterizacaoVODocumentImpl.CarterizacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.GrupoVODocumentImpl.GrupoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.GruposTratamentoNiveisVODocumentImpl.GruposTratamentoNiveisVOImpl.GrupoTratamentoImpl;
import br.com.vivo.fo.admsistemas.vo.impl.ProcedenciaVODocumentImpl.ProcedenciaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.SegmentacaoVODocumentImpl.SegmentacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.TipoClienteVODocumentImpl.TipoClienteVOImpl;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.cliente.vo.TipoLinhaVODocument.TipoLinhaVO;
import br.com.vivo.fo.cliente.vo.impl.TipoLinhaVODocumentImpl.TipoLinhaVOImpl;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.fidelizacao.vo.TipoPessoaVODocument.TipoPessoaVO;
import br.com.vivo.fo.fidelizacao.vo.impl.TipoPessoaVODocumentImpl.TipoPessoaVOImpl;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.CanalVODocument.CanalVO;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.xml.XmlManager;

@SuppressWarnings({"rawtypes","unchecked"})
@Stateless(name = "GruposProcessosFacade", mappedName = "GruposProcessosFacade")
@Local(GruposProcessosFacade.class)
@Session(ejbName = "GruposProcessosFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class GruposProcessosFacadeImpl implements GruposProcessosFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	private static Logger log = new Logger("admsistemas");
	private String xmlIN = ConstantesCRM.SVAZIO;
	private String xmlOUT = ConstantesCRM.SVAZIO;

	/**
	 * @common:operation
	 */
	public GrupoProcessoVO getAllGruposProcessos(String user) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("GruposProcessosFacadeImpl:getAllGruposProcessos(").append(user).append(")").toString());

			GrupoProcessoVO grupoProcesso = null;

			xmlIN = "<idgrupo/>";
			xmlIN = XmlManager.MakeXmlIn(user, "TuxConsAllGrp", xmlIN);
			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			GrupoProcessoVODocument doc = GrupoProcessoVODocument.Factory.parse(xmlOUT);

			grupoProcesso = doc.getGrupoProcessoVO();

			return grupoProcesso;

		} catch (XmlException ex) {
			log.error("XmlException - GruposProcessosFacadeImpl:getAllGruposProcessos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

		} catch (Exception ex) {
			log.error("Exception - GruposProcessosFacadeImpl:getAllGruposProcessos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public GruposProcessosVO getGruposProcessos(String user, String node, String filtroGrupos, String type) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("GruposProcessosFacadeImpl:getGruposProcessos(").append(user).append(", ").append(node).append(", ").append(type).append(")").toString());

			GruposProcessosVO gruposProcessos = null;

			xmlIN = new StringBuffer("<readPerfProcVO reqnode='").append(node).append("' type='").append(type).append("' />").toString();

			StringBuffer aux = new StringBuffer(ConstantesCRM.SVAZIO);
			if (filtroGrupos != null && !ConstantesCRM.SVAZIO.equals(filtroGrupos.trim())) {
				aux.append("<filtroGrupos>").append(filtroGrupos).append("</filtroGrupos>");
				// xmlIN += "<filtroGrupos>"+filtroGrupos+"</filtroGrupos>";
			}

			xmlIN = new StringBuffer(xmlIN).append(aux).toString();
			xmlIN = XmlManager.MakeXmlIn(user, "readGrpsProcVO", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnectorADM", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			GruposProcessosVODocument doc = GruposProcessosVODocument.Factory.parse(xmlOUT);
			gruposProcessos = doc.getGruposProcessosVO();

			return gruposProcessos;

		} catch (XmlException ex) {
			log.error("XmlException - GruposProcessosFacadeImpl:getGruposProcessos(" + user + ", " + node + ", " + type + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

		} catch (Exception ex) {
			log.error("Exception - GruposProcessosFacadeImpl:getGruposProcessos(" + user + ", " + node + ", " + type + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFAcaoRetornoVO setGruposProcessos(String user, GruposProcessosVO gruposProcessosVO) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("GruposProcessosFacadeImpl:setGruposProcessos(").append(user).append(", ").append(gruposProcessosVO).append(")").toString());

			String inService = TuxedoServiceBridge.getXMLRequest(user, "WRITEGRPSPROC", gruposProcessosVO.xmlText().replaceAll("vo:", ""));
			String xmlOut = ConstantesCRM.SVAZIO;// admSistemasTux.GETSERVICE(inService);
			xmlOut = tuxedo.callService("TuxConnectorADM", inService);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);

			return WFAcaoRetornoVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getWFAcaoRetornoVO();

		} catch (XmlException ex) {
			log.error("XmlException - GruposProcessosFacadeImpl:setGruposProcessos(" + user + ", " + gruposProcessosVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

		} catch (Exception ex) {
			log.error("Exception - GruposProcessosFacadeImpl:setGruposProcessos(" + user + ", " + gruposProcessosVO + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public RegrasEncaminhamentoVO getRegrasEncaminhamento(String user, Integer codGrupo) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("GruposProcessosFacadeImpl:getRegrasEncaminhamento(").append(user).append(", ").append(codGrupo).append(")").toString());

			RegrasEncaminhamentoVO regrasEncaminhamento = null;

			xmlIN = new StringBuffer("<codGrupo>").append(codGrupo).append("</codGrupo>").toString();
			xmlIN = XmlManager.MakeXmlIn(user, "readRegEnc", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			RegrasEncaminhamentoVODocument doc = RegrasEncaminhamentoVODocument.Factory.parse(xmlOUT);
			regrasEncaminhamento = doc.getRegrasEncaminhamentoVO();

			return regrasEncaminhamento;

		} catch (XmlException ex) {
			log.error("XmlException - GruposProcessosFacadeImpl:getRegrasEncaminhamento(" + user + ", " + codGrupo + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

		} catch (Exception ex) {
			log.error("Exception - GruposProcessosFacadeImpl:getRegrasEncaminhamento(" + user + ", " + codGrupo + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFAcaoRetornoVO setRegrasEncaminhamentoValidaSkill(String user, HashMap gravarElementos, String valida) throws FacadeException, TuxedoException {
		try {
			log.debug(new StringBuffer("GruposProcessosFacadeImpl:setRegrasEncaminhamentoValidaSkill(").append(user).append(", ").append(gravarElementos).append(")").toString());

			// Obtem elementos informados para gravação das regras de encaminhamento selecionados
			int codigoGrupo = ((Integer) gravarElementos.get(ConstantesCRM.CT_GrupoVO)).intValue();
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
			regrasEncaminhamentoGravarVO.setValida(valida);
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

			xmlIN = XmlManager.MakeXmlIn(user, "writeRegEnc", regrasEncaminhamentoGravarVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			WFAcaoRetornoVODocument doc = WFAcaoRetornoVODocument.Factory.parse(xmlOUT);
			WFAcaoRetornoVO acaoRetorno = doc.getWFAcaoRetornoVO();

			return acaoRetorno;

		} catch (XmlException ex) {
			log.error("XmlException - GruposProcessosFacadeImpl:setRegrasEncaminhamentoValidaSkill(" + user + ", " + gravarElementos + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

		} catch (Exception ex) {
			log.error("Exception - GruposProcessosFacadeImpl:setRegrasEncaminhamentoValidaSkill(" + user + ", " + gravarElementos + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void setRegrasEncaminhamento(String user, HashMap gravarElementos) throws FacadeException, TuxedoException {
		try {
			log.debug(new StringBuffer("GruposProcessosFacadeImpl:setRegrasEncaminhamento(").append(user).append(", ").append(gravarElementos).append(")").toString());

			// Obtem elementos informados para gravação das regras de encaminhamento selecionados
			int codigoGrupo = ((Integer) gravarElementos.get(ConstantesCRM.CT_GrupoVO)).intValue();
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

			xmlIN = XmlManager.MakeXmlIn(user, "writeRegEnc", regrasEncaminhamentoGravarVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

		} catch (XmlException ex) {
			log.error("XmlException - GruposProcessosFacadeImpl:setRegrasEncaminhamento(" + user + ", " + gravarElementos + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

		} catch (Exception ex) {
			log.error("Exception - GruposProcessosFacadeImpl:setRegrasEncaminhamento(" + user + ", " + gravarElementos + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public GruposTratamentoNiveisVO getGruposTratamentoNiveis(String user, int codigoGrupo) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("GruposProcessosFacadeImpl:getGruposTratamentoNiveis(").append(user).append(", ").append(codigoGrupo).append(")").toString());

			GruposTratamentoNiveisVO gruposTratamentoNiveisVO = null;

			xmlIN = new StringBuffer("<readGrpsTratNvl reqnode=\"").append(codigoGrupo).append("\" />").toString();
			xmlIN = XmlManager.MakeXmlIn(user, "READGRPSTRATN", xmlIN);

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

			GruposTratamentoNiveisVODocument doc = GruposTratamentoNiveisVODocument.Factory.parse(xmlOUT);
			gruposTratamentoNiveisVO = doc.getGruposTratamentoNiveisVO();

			return gruposTratamentoNiveisVO;

		} catch (XmlException ex) {
			log.error("XmlException - GruposProcessosFacadeImpl:getGruposTratamentoNiveis(" + user + ", " + codigoGrupo + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

		} catch (Exception ex) {
			log.error("Exception - GruposProcessosFacadeImpl:getGruposTratamentoNiveis(" + user + ", " + codigoGrupo + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public void setGruposTratamentoNiveis(String user, int nivelSelecionado, HashMap gravarElementos) throws TuxedoException, FacadeException {
		try {
			log.debug(new StringBuffer("GruposProcessosFacadeImpl:setGruposTratamentoNiveis(").append(user).append(", ").append(nivelSelecionado).append(", ").append(gravarElementos).append(")").toString());

			// Definições
			String inicio;
			int posicaoX;
			// int posicaoY;
			ArrayList composicaoDisponivel[] = null;
			ArrayList composicaoSequencia[] = null;

			// Obtem objetos do HashMap
			int grupoInicio = ((Integer) gravarElementos.get("grupoInicio")).intValue();
			String[] disponiveis = (String[]) gravarElementos.get("disponiveis");
			String[] sequencias = (String[]) gravarElementos.get("sequencias");

			// monta estrutura inicial do VO
			GruposTratamentoNiveisVO gtVO = GruposTratamentoNiveisVO.Factory.newInstance();
			gtVO.setGrupoInicio(GrupoInicio.Factory.newInstance());
			gtVO.getGrupoInicio().setGrupoVO(GrupoVO.Factory.newInstance());
			gtVO.setGrupoTratamentoArray(new GrupoTratamentoImpl[0]);

			// Grupo inicial
			GrupoVO grupoVOInicio = GrupoVO.Factory.newInstance();
			grupoVOInicio.setCodigo(grupoInicio);
			gtVO.getGrupoInicio().setGrupoVO(grupoVOInicio);

			// inicializa listas para montagem dos arrays de grupos disponiveis e sequencias
			composicaoDisponivel = new ArrayList[nivelSelecionado];
			composicaoSequencia = new ArrayList[nivelSelecionado];

			// esto sobra
			// for(int i = 0; i < nivelSelecionado; i++) {
			int i = 0;
			if (nivelSelecionado > 0) {
				do {
					composicaoDisponivel[i] = new ArrayList();
					composicaoSequencia[i] = new ArrayList();
					i++;
				} while (i < nivelSelecionado);
			}
			// processa os disponiveis
			inicio = ConstantesCRM.SVAZIO;
			posicaoX = -1;
			// posicaoY = 0;
			// for( int i = 0; i < disponiveis.length; i++ ){
			i = 0;
			if (disponiveis.length > 0) {
				do {
					if (!disponiveis[i].substring(0, 1).equalsIgnoreCase(inicio)) {
						inicio = disponiveis[i].substring(0, 1);
						posicaoX++;
						// posicaoY = 0;
					}
					composicaoDisponivel[posicaoX].add(disponiveis[i].substring(1));
					i++;
				} while (i < disponiveis.length);
			}

			// processa as sequencias
			inicio = ConstantesCRM.SVAZIO;
			posicaoX = -1;
			// posicaoY = 0;
			i = 0;
			// for( int i = 0; i < sequencias.length; i++ ){
			if (sequencias.length > 0) {
				do {
					if (!sequencias[i].substring(0, 1).equalsIgnoreCase(inicio)) {
						inicio = sequencias[i].substring(0, 1);
						posicaoX++;
						// posicaoY = 0;
					}
					composicaoSequencia[posicaoX].add(sequencias[i].substring(1));
					i++;
				} while (i < sequencias.length);
				;
			}

			// monta grupos recebidos no VO
			// for(int i = 0; i < nivelSelecionado; i++) {
			i = 0;
			int j = 0;
			if (nivelSelecionado > 0) {
				do {
					String[] disponiveisNivel = (String[]) composicaoDisponivel[i].toArray(new String[0]);
					String[] sequenciasNivel = (String[]) composicaoSequencia[i].toArray(new String[0]);
					GrupoVO[] disponiveisArray = new GrupoVOImpl[disponiveisNivel.length];
					GrupoVO[] sequenciasArray = new GrupoVOImpl[sequenciasNivel.length];

					j = 0;
					// for(int j = 0; j < disponiveisArray.length; j++) {
					if (disponiveisNivel.length > 0) {
						do {
							disponiveisArray[j] = GrupoVO.Factory.newInstance();
							disponiveisArray[j].setCodigo(Integer.parseInt(disponiveisNivel[j]));
							j++;
						} while (j < disponiveisArray.length);
					}
					j = 0;
					// for(int j = 0; j < sequenciasArray.length; j++) {
					if (sequenciasNivel.length > 0) {
						do {
							sequenciasArray[j] = GrupoVO.Factory.newInstance();
							sequenciasArray[j].setCodigo(Integer.parseInt(sequenciasNivel[j]));
							sequenciasArray[j].setOrdem(j);
							j++;
						} while (j < sequenciasArray.length);
					}
					gtVO.addNewGrupoTratamento();
					gtVO.getGrupoTratamentoArray(i).setNivel(i + 1);
					gtVO.getGrupoTratamentoArray(i).setDisponiveis(Disponiveis.Factory.newInstance());
					gtVO.getGrupoTratamentoArray(i).getDisponiveis().setGrupoVOArray(disponiveisArray);
					gtVO.getGrupoTratamentoArray(i).setSequencia(Sequencia.Factory.newInstance());
					gtVO.getGrupoTratamentoArray(i).getSequencia().setGrupoVOArray(sequenciasArray);
					i++;
				} while (i < nivelSelecionado);
			}

			xmlIN = XmlManager.MakeXmlIn(user, "WRITEGRPSTRAT", gtVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			// xmlOUT = (new ControlTuxedoCall()).execute(this, admSistemasTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

		} catch (XmlException ex) {
			log.error("XmlException - GruposProcessosFacadeImpl:setGruposTratamentoNiveis(" + user + ", " + nivelSelecionado + ", " + gravarElementos + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: ", ex));

		} catch (Exception ex) {
			log.error("Exception - GruposProcessosFacadeImpl:setGruposTratamentoNiveis(" + user + ", " + nivelSelecionado + ", " + gravarElementos + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

}
