package br.com.vivo.fo.ctrls.workflow.relatorios;

import java.util.Hashtable;
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
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFListaArquivosGeradosVODocument;
import br.com.vivo.fo.workflow.vo.WFListaArquivosGeradosVODocument.WFListaArquivosGeradosVO;
import br.com.vivo.fo.workflow.vo.WFRFRVODocument.WFRFRVO;
import br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument;
import br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO;
import br.com.vivo.fo.workflow.vo.WFRelatoriosFiltroRegionalVODocument.WFRelatoriosFiltroRegionalVO;
import br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument;
import br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO;
import br.com.vivo.fo.xml.XmlManager;

@Stateless(name = "RelatorioFacadeWF", mappedName = "RelatorioFacadeWF")
@Local(RelatorioFacadeWF.class)
@Session(ejbName = "RelatorioFacadeWF", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelatorioFacadeWFImpl implements RelatorioFacadeWF {

	@EJB
	private TuxedoServiceCall tuxedo;

	static final long serialVersionUID = 1L;
	private static Hashtable<String, String> hashRelatorio6Scripts;
	private static Hashtable<String, String> hashRelatorio6DetalhesScripts;
	private static Logger log = new Logger("workflow");

	/* * @ common:control */
	//@EJB private br.com.vivo.fo.ctrls.workflow.RouterService.Router routerTuxControl;

	/**
	 * @common:operation
	 */
	public WFRelatoriosFiltrosVO obtemWFRelatoriosFiltrosVO(String user) throws FacadeException {
		try {
			StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
			sb.append("RelatorioFacadeImpl:obtemWFRelatoriosFiltrosVO(").append(user).append(")");
			log.debug(sb.toString());
			String xmlIn = ConstantesCRM.SVAZIO;
			String xmlOut = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"ATDLSTCOMTTGR", xmlIn));
			xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "ATDLSTCOMTTGR", xmlIn));

			MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOut);

			return WFRelatoriosFiltrosVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getWFRelatoriosFiltrosVO();

		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:obtemWFRelatoriosFiltrosVO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFRelatoriosFiltroRegionalVO[] obtemRegionais(String user, String idOperadora) throws FacadeException {
		try {
			StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
			sb.append("RelatorioFacadeImpl:obtemRegionais(").append(user).append(")");
			log.debug(sb.toString());
			StringBuffer xmlIn = new StringBuffer();
			xmlIn.append("<idOperadora>").append(idOperadora).append("</idOperadora>");
			String xmlOut = ConstantesCRM.SVAZIO;// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user, "ATDLSTCOMTTGR", xmlIn.toString()));
			xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user, "ATDLSTCOMTTGR", xmlIn.toString()));

			MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOut);
			WFRelatoriosFiltroRegionalVO[] regionais = WFRelatoriosFiltrosVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getWFRelatoriosFiltrosVO().getWFRelatoriosFiltroRegionalVOArray();
			return regionais;
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:obtemRegionais(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RelatorioFacadeImpl:obtemRegionais", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFRFRVO[] obtemRepresentantes(String user, String idGrupo) throws FacadeException {
		try {
			StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
			sb.append("RelatorioFacadeImpl:obtemRepresentantes(").append(user).append(")");
			log.debug(sb.toString());
			StringBuffer xmlIn = new StringBuffer();
			WFRFRVO[] tmpRep = null;
			WFRelatoriosFiltrosVO relatorioFiltros = null;
			WFRelatoriosFiltrosVO tmpRelFiltro = null;
			int bloco = 0;
			int qtdRegBloco = 50;
			int nrInicial = 0;
			int nrFinal = 0;
			int inTotal = 0;
			while (inTotal == 0) {
				nrInicial = qtdRegBloco * bloco + 1;
				nrFinal = nrInicial + qtdRegBloco - 1;
				bloco++;
				xmlIn.append("<idGrupo>").append(idGrupo).append("</idGrupo>");
				xmlIn.append("<nrRegistroInicial>").append(nrInicial).append("</nrRegistroInicial>");
				xmlIn.append("<nrRegistroFinal>").append(nrFinal).append("</nrRegistroFinal>");
				String xmlOut = "";// workflowTux.GETSERVICE(TuxedoServiceBridge.getXMLRequest(user,"ATDLSTCOMTTGR", xmlIn.toString()));
				xmlOut = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(user,"ATDLSTCOMTTGR", xmlIn.toString()));

				xmlIn.setLength(0);
				MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOut);
				tmpRelFiltro = WFRelatoriosFiltrosVODocument.Factory.parse(msgDoc.getMsg().getMsgBody().xmlText()).getWFRelatoriosFiltrosVO();
				inTotal = Integer.parseInt(tmpRelFiltro.getInTotal() == null ? ConstantesCRM.SONE : tmpRelFiltro.getInTotal());
				tmpRep = tmpRelFiltro.getWFRFRVOArray();
				if (relatorioFiltros == null) {
					relatorioFiltros = tmpRelFiltro;
				} else {
					int totUsuarios = tmpRep.length;
					for (int i = 0; i < totUsuarios; i++) {
						WFRFRVO u = relatorioFiltros.addNewWFRFRVO();
						u.set(tmpRep[i]);
					}
				}
			}
			return relatorioFiltros.getWFRFRVOArray();
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:obtemRepresentantes(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarTotalGrupoDestinoBKO(String user, WFRelatoriosFiltrosVO filtro) throws FacadeException {
		try {
			StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
			sb.append("RelatorioFacadeImpl:gerarTotalGrupoDestinoBKO(").append(user).append(")");
			log.debug(sb.toString());
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, "WFATDREL1", xmlIn);
			// String xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

			return WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();
		} catch (XmlException ex) {
			log.error("XmlException - RelatorioFacadeImpl:gerarTotalGrupoDestinoBKO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RelatorioFacadeImpl:gerarTotalGrupoDestinoBKO", ex));
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:gerarTotalGrupoDestinoBKO(" + user + ") - [" + ex.getMessage() + "]");
			throw new FacadeException(ex);
		}
	}

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarTotalGrupoDestinoBKODownload(String user, WFRelatoriosFiltrosVO filtro) throws TuxedoException, FacadeException {
		try {
			StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
			sb.append("RelatorioFacadeImpl:gerarTotalGrupoDestinoBKODownload(").append(user).append(")");
			log.debug(sb.toString());
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, "WFATDREL1DET", xmlIn);
			// String xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

			return WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();
		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatorioFacadeImpl:gerarTotalGrupoDestinoBKODownload(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:gerarTotalGrupoDestinoBKODownload(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RelatorioFacadeImpl:gerarTotalGrupoDestinoBKODownload", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarTotalRepresentateBKO(String user, WFRelatoriosFiltrosVO filtro) throws FacadeException {
		try {
			StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
			sb.append("RelatorioFacadeImpl:gerarTotalRepresentateBKO(").append(user).append(")");
			log.debug(sb.toString());
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, "WFATDREL2", xmlIn);
			// String xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

			return WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();
		} catch (XmlException ex) {
			log.error("XmlException - RelatorioFacadeImpl:gerarTotalRepresentateBKO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RelatorioFacadeImpl:gerarTotalRepresentateBKO", ex));
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:gerarTotalRepresentateBKO(" + user + ") - [" + ex.getMessage() + "]");
			throw new FacadeException(ex);
		}
	}

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarTotalRepresentateBKODownload(String user, WFRelatoriosFiltrosVO filtro) throws TuxedoException, FacadeException {
		try {
			StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
			sb.append("RelatorioFacadeImpl:gerarTotalRepresentateBKODownload(").append(user).append(")");
			log.debug(sb.toString());
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, "WFATDREL2DET", xmlIn);
			// String xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

			return WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();
		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatorioFacadeImpl:gerarTotalRepresentateBKODownload(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:gerarTotalRepresentateBKODownload(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RelatorioFacadeImpl:gerarTotalRepresentateBKODownload", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarProdRepresentanteBKO(String user, WFRelatoriosFiltrosVO filtro) throws TuxedoException, FacadeException {
		try {
			StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
			sb.append("RelatorioFacadeImpl:gerarProdRepresentanteBKO(").append(user).append(")");
			log.debug(sb.toString());
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, "WFATDREL3", xmlIn);
			// String xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

			WFRelatorioDinamicoVO relatorio = WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();
			return relatorio;
		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatorioFacadeImpl:gerarProdRepresentanteBKO(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:gerarProdRepresentanteBKO(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RelatorioFacadeImpl:gerarProdRepresentanteBKO", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarQtdMotivoRepresentante(String user, WFRelatoriosFiltrosVO filtro) throws TuxedoException, FacadeException {
		try {
			StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
			sb.append("RelatorioFacadeImpl:gerarQtdMotivoRepresentante(").append(user).append(")");
			log.debug(sb.toString());
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, "WFATDREL4", xmlIn);
			// String xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

			WFRelatorioDinamicoVO relatorio = WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();
			return relatorio;
		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatorioFacadeImpl:gerarQtdMotivoRepresentante(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:gerarQtdMotivoRepresentante(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RelatorioFacadeImpl:gerarQtdMotivoRepresentante", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarTempoMedioFila(String user, WFRelatoriosFiltrosVO filtro, String tipoRelatorio) throws TuxedoException, FacadeException {
	    WFRelatorioDinamicoVO relatorio = WFRelatorioDinamicoVO.Factory.newInstance();
        long iniTemp=0;
        long fimTemp=0;
        String xmlOut = ConstantesCRM.SVAZIO;
		try {
			StringBuffer sb = new StringBuffer("RelatorioFacadeImpl:gerarTempoMedioFila(").append(user).append(")");
			log.debug(sb.toString());
			if (hashRelatorio6Scripts == null) {
				hashRelatorio6Scripts = new Hashtable<String, String>();
				hashRelatorio6Scripts.put("1", "WFATDREL6A");
				hashRelatorio6Scripts.put("2", "WFATDREL6B");
				hashRelatorio6Scripts.put("3", "WFATDREL6C");
				hashRelatorio6Scripts.put("4", "WFATDREL6D");
				hashRelatorio6Scripts.put("5", "WFATDREL6E");
			}
			String relatSvcName = hashRelatorio6Scripts.get(tipoRelatorio);
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, relatSvcName, xmlIn);
			// String xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			try {
			    iniTemp = System.currentTimeMillis();
	            xmlOut = tuxedo.callService("TuxConnector", xmlIn);

            } catch (TuxedoServiceCallerException e) {
                fimTemp = System.currentTimeMillis() - iniTemp;
                boolean hasTimeout = (fimTemp>17000);
                relatorio = WFRelatorioDinamicoVO.Factory.newInstance();
                if(hasTimeout) {
                    relatorio.setDsTituloRelatorio("Ocorreu um Timeout na chamada ao legado.");
                }else {
                    relatorio.setDsTituloRelatorio("Ocorreu um Erro na chamada ao legado.");
                }
            }

            if(xmlOut==null || "".equals(xmlOut)) {
                fimTemp = System.currentTimeMillis() - iniTemp;
                boolean hasTimeout = (fimTemp>17000);
                if(hasTimeout) {
                    relatorio.setDsTituloRelatorio("Ocorreu um Timeout na chamada ao legado.");
                }else {
                    relatorio.setDsTituloRelatorio("Ocorreu um Erro na chamada ao legado.");
                }
            }else {
                MsgDocument msgDoc = MsgDocument.Factory.parse(xmlOut);
                String statusText = msgDoc.getMsg().getMsgHdr().getStatusText();
                if(msgDoc.getMsg().getMsgBody().xmlText().length()>0 && msgDoc.getMsg().getMsgBody().getInnerBody()!=null) {
                    relatorio = WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();
                }else {
                    relatorio.setDsTituloRelatorio(statusText);
                }
            }

            return relatorio;

		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:gerarTempoMedioFila(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RelatorioFacadeImpl:gerarTempoMedioFila", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarTempoMedioFilaDetalhe(String user, WFRelatoriosFiltrosVO filtro, String tipoRelatorio) throws TuxedoException, FacadeException {
		try {
			StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
			sb.append("RelatorioFacadeImpl:gerarTempoMedioFilaDetalhe(").append(user).append(")");
			log.debug(sb.toString());
			if (hashRelatorio6DetalhesScripts == null) {
				hashRelatorio6DetalhesScripts = new Hashtable<String, String>();
				hashRelatorio6DetalhesScripts.put("1", "WFATDREL6ADET");
				hashRelatorio6DetalhesScripts.put("2", "WFATDREL6BDET");
				hashRelatorio6DetalhesScripts.put("3", "WFATDREL6CDET");
				hashRelatorio6DetalhesScripts.put("4", "WFATDREL6DDET");
				hashRelatorio6DetalhesScripts.put("5", "WFATDREL6EDET");
			}
			String relatSvcName = hashRelatorio6DetalhesScripts.get(tipoRelatorio);
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, relatSvcName, xmlIn);
			// String xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

			WFRelatorioDinamicoVO relatorio = WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();
			return relatorio;
		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatorioFacadeImpl:gerarTempoMedioFilaDetalhe(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:gerarTempoMedioFilaDetalhe(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RelatorioFacadeImpl:gerarTempoMedioFilaDetalhe", ex));
		}
	}

	/*****************************************************************
	 * Relatorio 7 - Quantidade Palitagem
	 */

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarQtdPalitagem1(String user, WFRelatoriosFiltrosVO filtro) throws TuxedoException, FacadeException {
		try {
			StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
			sb.append("RelatorioFacadeImpl:gerarQtdPalitagem1(").append(user).append(")");
			log.debug(sb.toString());
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, "WFATDREL7", xmlIn);
			// String xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

			return WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatorioFacadeImpl:gerarQtdPalitagem1(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:gerarQtdPalitagem1(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: RelatorioFacadeImpl:gerarQtdPalitagem1", ex));
		}
	}

	/*****************************************************************
	 * Relatorio 8 - Quantidade Palitagem
	 */

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarAtdEncIncorretos(String user, WFRelatoriosFiltrosVO filtro) throws TuxedoException, FacadeException {
		try {
			StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
			sb.append("RelatorioFacadeImpl:gerarAtdEncIncorretos(").append(user).append(")");
			log.debug(sb.toString());
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, "WFATDRELNENC", xmlIn);

			// String xmlOut = (new ControlTuxedoCall()).execute(this, workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);
			xmlOut = MsgDocument.Factory.parse(xmlOut).getMsg().getMsgBody().xmlText();

			return WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatorioFacadeImpl:gerarAtdEncIncorretos(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException();

		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:gerarAtdEncIncorretos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/*****************************************************************
	 * Relatorio 8 - Quantidade Palitagem
	 */

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarRelatorio1CRI(String user, WFRelatoriosFiltrosVO filtro) throws TuxedoException, FacadeException {
		try {
			StringBuffer sb = new StringBuffer(ConstantesCRM.SVAZIO);
			sb.append("RelatorioFacadeImpl:gerarRelatorio1CRI(").append(user).append(")");
			log.debug(sb.toString());
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, "RELAUDPROC", xmlIn);
			// String xmlOut = (new ControlTuxedoCall()).execute(this, workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

			return WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();
		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatorioFacadeImpl:gerarRelatorio1CRI(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);
		} catch (Exception ex) {
			log.error("Exception - RelatorioFacadeImpl:gerarRelatorio1CRI(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
	}

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarProdRepresentanteBKOPaginado(String user, WFRelatoriosFiltrosVO filtro) throws TuxedoException, FacadeException {
		int inTotal = 0;
		WFRelatorioDinamicoVO relatorioPaginado = WFRelatorioDinamicoVO.Factory.newInstance();
		try {
			filtro.setInTotal(String.valueOf(inTotal));
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, "WFATDREL3", xmlIn);
			// String xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

			WFRelatorioDinamicoVO relatorio = WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();
			if (relatorio.getInFin().equals(ConstantesCRM.SONE)) {
				return relatorio;
			} else {
				do {
					inTotal += 1;
					filtro.setInTotal(String.valueOf(inTotal));
					// filtro.setQtdLinhasBloco(totalRegistrosPPagina);
					xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
					xmlIn = XmlManager.MakeXmlIn(user, "WFATDREL3", xmlIn);
					// xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
					xmlOut = tuxedo.callService("TuxConnector", xmlIn);

					msgDocRet = MsgDocument.Factory.parse(xmlOut);
					xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

					relatorioPaginado = WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();
					for (int i = 0; i < relatorioPaginado.getValoresRelatorioArray().length; i++) {
						relatorio.insertNewValoresRelatorio(relatorio.getValoresRelatorioArray().length);
						relatorio.getValoresRelatorioArray(relatorio.getValoresRelatorioArray().length - 1).setValorColunaArray(relatorioPaginado.getValoresRelatorioArray(i).getValorColunaArray());
					}
				} while (!relatorioPaginado.getInFin().equals(ConstantesCRM.SONE));
				return relatorio;
			}
		} catch (TuxedoServiceCallerException ex) {
			log.error("RelatorioFacadeImpl:gerarProdRepresentanteBKO(" + user + ") - [" + ex.getMessage() + "]", ex);
			throw new TuxedoException(ex);
		} catch (Exception ex) {
			log.error("RelatorioFacadeImpl:gerarProdRepresentanteBKO(" + user + ") - [" + ex.getMessage() + "]", ex);
			throw (new FacadeException("Erro na montagem do XML de entrada. (XMLIN)"));
		}
	}

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarProdRepresentanteBKOComBotao(String user, WFRelatoriosFiltrosVO filtro) throws TuxedoException, FacadeException {
		try {
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, "WFATDREL3", xmlIn);
			// String xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

			WFRelatorioDinamicoVO relatorio = WFRelatorioDinamicoVODocument.Factory.parse(xmlOut).getWFRelatorioDinamicoVO();
			return relatorio;
		} catch (TuxedoServiceCallerException ex) {
			log.error("RelatorioFacadeImpl:gerarProdRepresentanteBKO(" + user + ") - [" + ex.getMessage() + "]", ex);
			throw new TuxedoException(ex);
		} catch (Exception ex) {
			log.error("RelatorioFacadeImpl:gerarProdRepresentanteBKO(" + user + ") - [" + ex.getMessage() + "]", ex);
			throw (new FacadeException("Erro na montagem do XML de entrada. (XMLIN)"));
		}
	}

	/**
	 * @common:operation
	 */
	public WFRelatorioDinamicoVO gerarRelatorioPalitagens(String user, WFRelatoriosFiltrosVO filtro) throws TuxedoException, FacadeException {
		try {
			String xmlIn = filtro.xmlText().replaceAll("vo[0-9]*:", ConstantesCRM.SVAZIO);
			xmlIn = XmlManager.MakeXmlIn(user, "INSAGCONTATO", xmlIn);
			// String xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOut);
			xmlOut = msgDocRet.getMsg().getMsgBody().xmlText();

			return WFRelatorioDinamicoVO.Factory.newInstance();
		} catch (TuxedoServiceCallerException ex) {
			log.error("RelatorioFacadeImpl:gerarRelatorioPalitagens(" + user + ") - [" + ex.getMessage() + "]", ex);
			throw new TuxedoException(ex);
		} catch (Exception ex) {
			log.error("RelatorioFacadeImpl:gerarRelatorioPalitagens(" + user + ") - [" + ex.getMessage() + "]", ex);
			throw (new FacadeException("Erro na montagem do XML de entrada. (XMLIN)"));
		}
	}

	/**
	 * @common:operation
	 */
	public WFListaArquivosGeradosVO getListaRelatoriosPalitagens(String user) throws TuxedoException, FacadeException {
		try {
			String xmlIn = ConstantesCRM.SVAZIO;
			xmlIn = XmlManager.MakeXmlIn(user, "SELAGCONTATO", ConstantesCRM.SVAZIO);
			// String xmlOut = (new ControlTuxedoCall()).execute(this,workflowTux, "GETSERVICE", xmlIn);
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);

			if(xmlOut==null || ConstantesCRM.SVAZIO.equals(xmlOut)) {
			    new TuxedoException("O servico Tuxedo não retornou nenhum XML.");
			}

			xmlOut = MsgDocument.Factory.parse(xmlOut).getMsg().getMsgBody().xmlText();

			WFListaArquivosGeradosVO relatorio = WFListaArquivosGeradosVODocument.Factory.parse(xmlOut).getWFListaArquivosGeradosVO();

			return relatorio;

		} catch (TuxedoServiceCallerException ex) {
			log.error("RelatorioFacadeImpl:gerarRelatorioPalitagens(" + user + ") - [" + ex.getMessage() + "]", ex);
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("RelatorioFacadeImpl:gerarRelatorioPalitagens(" + user + ") - [" + ex.getMessage() + "]", ex);
			throw (new FacadeException("Erro na montagem do XML de entrada. (XMLIN)"));
		}
	}
}