package br.com.vivo.fo.ctrls.VOLTAV.relatorios;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import noNamespace.MsgDocument;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.iPhone.db.ManterIphoneDB;
import br.com.vivo.fo.ctrls.VOLTAV.iPhone.db.ManterIphoneDB.LinhaRelatorioIphone;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.voltav.vo.VOLTAVOperacaoRecargaVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVOperacaoRecargaVODocument.VOLTAVOperacaoRecargaVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoDiarioVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoDiarioVODocument.VOLTAVRelatorioAcessoDiarioVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoFaixaHorarioVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoFaixaHorarioVODocument.VOLTAVRelatorioAcessoFaixaHorarioVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoNegadoVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessoNegadoVODocument.VOLTAVRelatorioAcessoNegadoVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessosIncidenciaVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAcessosIncidenciaVODocument.VOLTAVRelatorioAcessosIncidenciaVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAdesoesVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioAdesoesVODocument.VOLTAVRelatorioAdesoesVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioFinanceiroVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioFinanceiroVODocument.VOLTAVRelatorioFinanceiroVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioLojasVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioLojasVODocument.VOLTAVRelatorioLojasVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioPrimeiroAcessoVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioPrimeiroAcessoVODocument.VOLTAVRelatorioPrimeiroAcessoVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioServicosDisponveisVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioServicosDisponveisVODocument.VOLTAVRelatorioServicosDisponveisVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioServicosEfetuadosVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioServicosEfetuadosVODocument.VOLTAVRelatorioServicosEfetuadosVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioStatusVendaVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioStatusVendaVODocument.VOLTAVRelatorioStatusVendaVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioTempoPermanenciaVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioTempoPermanenciaVODocument.VOLTAVRelatorioTempoPermanenciaVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioTipoErroVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatorioTipoErroVODocument.VOLTAVRelatorioTipoErroVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatoriosFiltroLojaVODocument.VOLTAVRelatoriosFiltroLojaVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatoriosFiltroTerminalVODocument.VOLTAVRelatoriosFiltroTerminalVO;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatoriosFiltrosVODocument;
import br.com.vivo.fo.voltav.vo.VOLTAVRelatoriosFiltrosVODocument.VOLTAVRelatoriosFiltrosVO;
import br.com.vivo.fo.xml.XmlManager;
import br.com.vivo.fo.xml.XmlManagerVol;
import br.com.vivo.tav.creditos.LSTTIPORECARVODocument;
import br.com.vivo.tav.creditos.LSTTIPORECARVODocument.LSTTIPORECARVO;
import br.com.vivo.vol.acessos.vo.LSTHISTRELACVODocument;
import br.com.vivo.vol.acessos.vo.RELACIONAMENTOSDocument.RELACIONAMENTOS;

@Stateless(name = "RelatoriosFacadeVol", mappedName = "RelatoriosFacadeVol")
@Local(RelatoriosFacadeVol.class)
@Session(ejbName = "RelatoriosFacadeVol", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelatoriosFacadeVolImpl implements RelatoriosFacadeVol {

	@EJB
	private TuxedoServiceCall tuxedo;

	@EJB
	private ManterIphoneDB manterIphoneDB;

	private Logger log = new Logger(ConstantesCRM.SVAZIO);
	private static XmlManagerVol xmlManager = new XmlManagerVol();

	/**
	 * @common:operation
	 */
	public LinhaRelatorioIphone[] consultaRelatorioIphone(String user, String dataInicio, String dataFinal, String nrLinha, String cdAreaRegistro, String idUFOperadora, String idGrupoOperadora, String inAtivadoEnvioMail) throws TuxedoException, FacadeException {
		log.debug("RelatoriosFacadeVolImpl:consultaRelatorioIphone(" + user + ")");

		try {
			if (nrLinha == null || nrLinha.equalsIgnoreCase("-1") || nrLinha.equalsIgnoreCase(ConstantesCRM.SVAZIO)) {
				nrLinha = ConstantesCRM.SZERO;
			}
			if (cdAreaRegistro == null || cdAreaRegistro.equalsIgnoreCase("-1") || cdAreaRegistro.equalsIgnoreCase(ConstantesCRM.SVAZIO)) {
				cdAreaRegistro = ConstantesCRM.SZERO;
			}
			if (idUFOperadora == null || idUFOperadora.equalsIgnoreCase("-1") || idUFOperadora.equalsIgnoreCase(ConstantesCRM.SVAZIO)) {
				idUFOperadora = ConstantesCRM.SZERO;
			}
			if (idGrupoOperadora == null || idGrupoOperadora.equalsIgnoreCase("-1") || idGrupoOperadora.equalsIgnoreCase(ConstantesCRM.SVAZIO)) {
				idGrupoOperadora = ConstantesCRM.SZERO;
			}
			if (inAtivadoEnvioMail == null || inAtivadoEnvioMail.equalsIgnoreCase("-1") || inAtivadoEnvioMail.equalsIgnoreCase(ConstantesCRM.SVAZIO)) {
				inAtivadoEnvioMail = ConstantesCRM.SZERO;
			}

			LinhaRelatorioIphone[] relatorio = manterIphoneDB.getRelatorioIphone(dataInicio, dataFinal, inAtivadoEnvioMail, nrLinha, cdAreaRegistro, idUFOperadora, idGrupoOperadora);

			return relatorio;

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioIphone(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioIphone", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioAcessoDiarioVO consultaRelatorioAcessosDiario(String user, String dataInicio, String dataFinal, String operadora, String regional, String tipoCarteira, String segmentacao, String canal, String tipoLinha, String cdAreaRegistro, String isAgrupado, String idTecnologia, String idLoja, String nrTerminal) throws TuxedoException, FacadeException {

		log.debug("RelatoriosFacadeVolImpl:consultaRelatorioAcessosDiario(" + user + ")");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<operacao>consultarAcessoPorDia</operacao>");
			sb.append("<dtInicial>" + dataInicio + "</dtInicial>");
			sb.append("<dtFinal>" + dataFinal + "</dtFinal>");
			sb.append("<idUFOperadora>" + regional + "</idUFOperadora>");
			sb.append("<idGrupoOperadora>" + operadora + "</idGrupoOperadora>");
			sb.append("<idSegmentacao>" + segmentacao + "</idSegmentacao>");
			sb.append("<idTipoCarteira>" + tipoCarteira + "</idTipoCarteira>");
			/********* Adicionado por Decio 14/03/2005 **************************/
			sb.append("<idCanal>" + canal + "</idCanal>");
			sb.append("<idTipoLinha>" + tipoLinha + "</idTipoLinha>");
			sb.append("<cdLinha>" + cdAreaRegistro + "</cdLinha>");
			sb.append("<isAgrupado>" + isAgrupado + "</isAgrupado>");
			/********* Fim Adicionado por Decio *********************************/
			sb.append("<idTecnologia>" + idTecnologia + "</idTecnologia>");
			sb.append("<idLoja>" + idLoja + "</idLoja>");
			sb.append("<nrTerminal>" + nrTerminal + "</nrTerminal>");

			String xmlEntrada = xmlManager.xmlInput("DADACESSO", user, sb.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlEntrada);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlEntrada);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioAcessoDiarioVODocument vo = VOLTAVRelatorioAcessoDiarioVODocument.Factory.parse(xmlOUT);

			return vo.getVOLTAVRelatorioAcessoDiarioVO();

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRelatorioAcessosDiario(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioAcessosDiario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioAcessosDiario", ex));
		}
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioAcessoFaixaHorarioVO consultaRelatorioAcessosFaixaHorario(String user, String dataInicio, String dataFinal, String operadora, String regional, String tipoCarteira, String segmentacao, String canal, String tipoLinha, String cdAreaRegistro, String isAgrupado, String idTecnologia, String idLoja, String nrTerminal) throws TuxedoException, FacadeException {

		log.warning("RelatoriosFacadeVolImpl:consultaRelatorioAcessosFaixaHorario(" + user + ")");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<operacao>consultarAcessoPorHora</operacao>");
			sb.append("<dtInicial>" + dataInicio + "</dtInicial>");
			sb.append("<dtFinal>" + dataFinal + "</dtFinal>");
			sb.append("<idUFOperadora>" + regional + "</idUFOperadora>");
			sb.append("<idGrupoOperadora>" + operadora + "</idGrupoOperadora>");
			sb.append("<idSegmentacao>" + segmentacao + "</idSegmentacao>");
			sb.append("<idTipoCarteira>" + tipoCarteira + "</idTipoCarteira>");
			/********* Adicionado por Decio 14/03/2005 **************************/
			sb.append("<idCanal>" + canal + "</idCanal>");
			sb.append("<idTipoLinha>" + tipoLinha + "</idTipoLinha>");
			sb.append("<cdLinha>" + cdAreaRegistro + "</cdLinha>");
			sb.append("<isAgrupado>" + isAgrupado + "</isAgrupado>");
			/********* Fim Adicionado por Decio *********************************/
			sb.append("<idTecnologia>" + idTecnologia + "</idTecnologia>");
			sb.append("<idLoja>" + idLoja + "</idLoja>");
			sb.append("<nrTerminal>" + nrTerminal + "</nrTerminal>");

			log.warning("RelatoriosFacadeVolImpl:consultaRelatorioAcessosFaixaHorario() XMLin -> " + sb.toString());

			String xmlIn = xmlManager.xmlInput("DADACESSO", user, sb.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlIn);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			log.warning("RelatoriosFacadeVolImpl:consultaRelatorioAcessosFaixaHorario() xmlOUT -> " + xmlOUT);
			VOLTAVRelatorioAcessoFaixaHorarioVODocument vo = VOLTAVRelatorioAcessoFaixaHorarioVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioAcessoFaixaHorarioVODocument) {
				log.warning("RelatoriosFacadeVolImpl:consultaRelatorioAcessosFaixaHorario() -> IF instanceof");
				return vo.getVOLTAVRelatorioAcessoFaixaHorarioVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRelatorioAcessosFaixaHorario(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioAcessosFaixaHorario(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioAcessosFaixaHorario", ex));
		}

		log.warning("RelatoriosFacadeVolImpl:consultaRelatorioAcessosFaixaHorario() return null");
		return null;
	}

	/**
	 * @common:operation
	 */
	public RELACIONAMENTOS[] consultaRelatorioAcessosCliente(String user, String cdAreaRegistro, String nrLinha, String dataInicio, String dataFinal) throws TuxedoException, FacadeException {

		log.debug("RelatoriosFacadeVolImpl:consultaRelatorioAcessosCliente(" + user + ")");
		try {
			String xmlEntrada = "<cdAreaRegistro>" + cdAreaRegistro + "</cdAreaRegistro><nrLinha>" + nrLinha + "</nrLinha><dtRelacionamentoInicial>" + ((dataInicio != null) ? dataInicio.replaceAll("/", "") : "") + "</dtRelacionamentoInicial><dtRelacionamentoFinal>" + ((dataFinal != null) ? dataFinal.replaceAll("/", "") : "") + "</dtRelacionamentoFinal><qtRelacionamentos></qtRelacionamentos>";
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux,"GETSERVICE",this.xmlManager.xmlInput("LSTHISTRELAC", user, xmlEntrada));
			String xmlOUT = tuxedo.callService("TuxConnector", xmlManager.xmlInput("LSTHISTRELAC", user, xmlEntrada));
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			LSTHISTRELACVODocument vo = LSTHISTRELACVODocument.Factory.parse(xmlOUT);

			if (vo instanceof LSTHISTRELACVODocument) {
				if (vo.getLSTHISTRELACVO() != null) {
					return vo.getLSTHISTRELACVO().getRELACIONAMENTOSArray();
				}
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRelatorioAcessosCliente(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioAcessosCliente(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioAcessosCliente", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioAcessoNegadoVO consultaRelatorioAcessosNegado(String user, String dataInicio, String dataFinal, String operadora, String regional, String tipoCarteira, String segmentacao, String canal, String tipoLinha, String cdAreaRegistro, String isAgrupado, String idTecnologia, String idLoja, String nrTerminal) throws TuxedoException, FacadeException {

		log.debug("RelatoriosFacadeVolImpl:consultaRelatorioAcessosNegado(" + user + ")");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<operacao>consultarAcessoNegado</operacao>");
			sb.append("<dtInicial>" + dataInicio + "</dtInicial>");
			sb.append("<dtFinal>" + dataFinal + "</dtFinal>");
			sb.append("<idUFOperadora>" + regional + "</idUFOperadora>");
			sb.append("<idGrupoOperadora>" + operadora + "</idGrupoOperadora>");
			sb.append("<idSegmentacao>" + segmentacao + "</idSegmentacao>");
			sb.append("<idTipoCarteira>" + tipoCarteira + "</idTipoCarteira>");
			/********* Adicionado por Decio 14/03/2005 **************************/
			sb.append("<idCanal>" + canal + "</idCanal>");
			sb.append("<idTipoLinha>" + tipoLinha + "</idTipoLinha>");
			sb.append("<cdLinha>" + cdAreaRegistro + "</cdLinha>");
			sb.append("<isAgrupado>" + isAgrupado + "</isAgrupado>");
			/********* Fim Adicionado por Decio *********************************/
			sb.append("<idTecnologia>" + idTecnologia + "</idTecnologia>");
			sb.append("<idLoja>" + idLoja + "</idLoja>");
			sb.append("<nrTerminal>" + nrTerminal + "</nrTerminal>");

			String xmlEntrada = xmlManager.xmlInput("DADACESSO", user, sb.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlEntrada);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlEntrada);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioAcessoNegadoVODocument vo = VOLTAVRelatorioAcessoNegadoVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioAcessoNegadoVODocument) {
				return vo.getVOLTAVRelatorioAcessoNegadoVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRelatorioAcessosNegado(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioAcessosNegado(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioAcessosNegado", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioAdesoesVO consultaRelatorioAdesoesBaseClientes(String user, String dataInicio, String dataFinal, String operadora, String regional, String tipoCarteira, String segmentacao, String canal, String tipoLinha, String idTecnologia, String idLoja, String nrTerminal) throws TuxedoException, FacadeException {

		log.debug("RelatoriosFacadeVolImpl:consultaRelatorioAdesoesBaseClientes(" + user + ")");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<operacao>consultarAdesoes</operacao>");
			sb.append("<dtInicial>" + dataInicio + "</dtInicial>");
			sb.append("<dtFinal>" + dataFinal + "</dtFinal>");
			sb.append("<idUFOperadora>" + regional + "</idUFOperadora>");
			sb.append("<idGrupoOperadora>" + operadora + "</idGrupoOperadora>");
			sb.append("<idSegmentacao>" + segmentacao + "</idSegmentacao>");
			sb.append("<idTipoCarteira>" + tipoCarteira + "</idTipoCarteira>");
			sb.append("<idCanal>" + canal + "</idCanal>");
			sb.append("<idTipoLinha>" + tipoLinha + "</idTipoLinha>");
			sb.append("<idTecnologia>" + idTecnologia + "</idTecnologia>");
			sb.append("<idLoja>" + idLoja + "</idLoja>");
			sb.append("<nrTerminal>" + nrTerminal + "</nrTerminal>");

			String xmlEntrada = xmlManager.xmlInput("DADACESSO", user, sb.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlEntrada);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlEntrada);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioAdesoesVODocument vo = VOLTAVRelatorioAdesoesVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioAdesoesVODocument) {
				return vo.getVOLTAVRelatorioAdesoesVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRelatorioAdesoesBaseClientes(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioAdesoesBaseClientes(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioAdesoesBaseClientes", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioServicosEfetuadosVO consultaRelatorioUtilizacaoServicos(String user, String dataInicio, String dataFinal, String operadora, String regional, String tipoCarteira, String segmentacao, String idContato, String canal, String tipoLinha, String cdAreaRegistro, String isAgrupado, String idTecnologia, String operadoraTerminal, String regionalTerminal, String idLoja, String nrTerminal) throws TuxedoException, FacadeException {

		log.debug("RelatoriosFacadeVolImpl:consultaRelatorioUtilizacaoServicos(" + user + ")");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<operacao>consultarServicosEfetuados</operacao>");
			sb.append("<dtInicial>" + dataInicio + "</dtInicial>");
			sb.append("<dtFinal>" + dataFinal + "</dtFinal>");
			sb.append("<idUFOperadora>" + regional + "</idUFOperadora>");
			sb.append("<idGrupoOperadora>" + operadora + "</idGrupoOperadora>");
			sb.append("<idSegmentacao>" + segmentacao + "</idSegmentacao>");
			sb.append("<idTipoCarteira>" + tipoCarteira + "</idTipoCarteira>");
			sb.append("<idContato>" + idContato + "</idContato>");
			sb.append("<idCanal>" + canal + "</idCanal>");
			sb.append("<idTipoLinha>" + tipoLinha + "</idTipoLinha>");
			sb.append("<cdLinha>" + cdAreaRegistro + "</cdLinha>");
			sb.append("<isAgrupado>" + isAgrupado + "</isAgrupado>");
			sb.append("<idTecnologia>" + idTecnologia + "</idTecnologia>");
			sb.append("<idLoja>" + idLoja + "</idLoja>");
			sb.append("<nrTerminal>" + nrTerminal + "</nrTerminal>");
			sb.append("<idGrupoOperadoraTerminal>" + operadoraTerminal + "</idGrupoOperadoraTerminal>");
			sb.append("<idUFOperadoraTerminal>" + regionalTerminal + "</idUFOperadoraTerminal>");

			String xmlEntrada = xmlManager.xmlInput("DADACESSO", user, sb.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlEntrada);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlEntrada);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioServicosEfetuadosVODocument vo = VOLTAVRelatorioServicosEfetuadosVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioServicosEfetuadosVODocument) {
				return vo.getVOLTAVRelatorioServicosEfetuadosVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRelatorioUtilizacaoServicos(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioUtilizacaoServicos(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioUtilizacaoServicos", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioFinanceiroVO consultaRelatorioFinanceiro(String user, String dataInicio, String dataFinal, String operadora, String regional, String idPessoaDePara, String idTipoServico, String idTipoRecarga, String idStatusVenda, String nrTerminal) throws TuxedoException, FacadeException {

		log.debug("RelatoriosFacadeVolImpl:consultaRelatorioFinanceiro(" + user + ")");
		try {
			
			if ("-1".equals(idTipoRecarga)) {
				idTipoRecarga = "null";
			}
			
			StringBuffer sb = new StringBuffer();
			sb.append("<operacao>consultarFinanceiro</operacao>");
			sb.append("<dtInicial>" + dataInicio + "</dtInicial>");
			sb.append("<dtFinal>" + dataFinal + "</dtFinal>");
			sb.append("<idUFOperadora>" + regional + "</idUFOperadora>");
			sb.append("<idGrupoOperadora>" + operadora + "</idGrupoOperadora>");
			sb.append("<idPessoaDePara>" + idPessoaDePara + "</idPessoaDePara>");
			sb.append("<idTipoServico>" + idTipoServico + "</idTipoServico>");
			sb.append("<idTipoRecarga>" + idTipoRecarga + "</idTipoRecarga>");
			sb.append("<idStatusVenda>" + idStatusVenda + "</idStatusVenda>");
			sb.append("<nrTerminal>" + nrTerminal + "</nrTerminal>");

			String xmlEntrada = xmlManager.xmlInput("DADACESSO", user, sb.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlEntrada);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlEntrada);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioFinanceiroVODocument vo = VOLTAVRelatorioFinanceiroVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioFinanceiroVODocument) {
				return vo.getVOLTAVRelatorioFinanceiroVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRelatorioFinanceiro(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioFinanceiro(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioFinanceiro", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVOperacaoRecargaVO consultaRelatorioFinanceiroDetalhadoOperacaoRecarga(String user, String idSitefVenda) throws TuxedoException, FacadeException {

		log.debug("RelatoriosFacadeVolImpl:consultaRelatorioFinanceiroDetalhadoOperacaoRecarga(" + user + ")");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<operacao>carregarOperacaoRecarga</operacao>");
			sb.append("<idSitefVenda>" + idSitefVenda + "</idSitefVenda>");

			String xmlEntrada = xmlManager.xmlInput("DADACESSO", user, sb.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlEntrada);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlEntrada);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVOperacaoRecargaVODocument vo = VOLTAVOperacaoRecargaVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVOperacaoRecargaVODocument) {
				return vo.getVOLTAVOperacaoRecargaVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRelatorioFinanceiroDetalhadoOperacaoRecarga(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioFinanceiroDetalhadoOperacaoRecarga(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioFinanceiroDetalhadoOperacaoRecarga", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioFinanceiroVO consultaRelatorioFinanceiroDetalhado(String user, String dataInicio, String dataFinal, String operadora, String regional, String idPessoaDePara, String idTipoServico, String idTipoRecarga, String idStatusVenda, String nrTerminal, String idStatusSitefVenda, String nrLinha, String cdAreaRegistro) throws TuxedoException, FacadeException {

		log.debug("RelatoriosFacadeVolImpl:consultaRelatorioFinanceiroDetalhado(" + user + ")");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<operacao>consultarFinanceiroDetalhado</operacao>");
			sb.append("<dtInicial>" + dataInicio + "</dtInicial>");
			sb.append("<dtFinal>" + dataFinal + "</dtFinal>");
			sb.append("<idUFOperadora>" + regional + "</idUFOperadora>");
			sb.append("<idGrupoOperadora>" + operadora + "</idGrupoOperadora>");
			sb.append("<idPessoaDePara>" + idPessoaDePara + "</idPessoaDePara>");
			sb.append("<idTipoServico>" + idTipoServico + "</idTipoServico>");
			sb.append("<idTipoRecarga>" + idTipoRecarga + "</idTipoRecarga>");
			sb.append("<idStatusVenda>" + idStatusVenda + "</idStatusVenda>");
			sb.append("<nrTerminal>" + nrTerminal + "</nrTerminal>");
			sb.append("<nrLinha>" + nrLinha + "</nrLinha>");
			sb.append("<cdAreaRegistro>" + cdAreaRegistro + "</cdAreaRegistro>");
			sb.append("<idStatusSitefVenda>" + idStatusSitefVenda + "</idStatusSitefVenda>");

			String xmlEntrada = xmlManager.xmlInput("DADACESSO", user, sb.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlEntrada);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlEntrada);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioFinanceiroVODocument vo = VOLTAVRelatorioFinanceiroVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioFinanceiroVODocument) {
				return vo.getVOLTAVRelatorioFinanceiroVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRelatorioFinanceiro(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioFinanceiro(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioFinanceiro", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioPrimeiroAcessoVO consultaRelatorioPrimeiroAcesso(String user, String dataInicio, String dataFinal, String operadora, String regional, String tipoCarteira, String segmentacao, String canal, String tipoLinha, String cdAreaRegistro, String idTecnologia, String idLoja, String nrTerminal) throws TuxedoException, FacadeException {

		log.debug("RelatoriosFacadeVolImpl:consultaRelatorioPrimeiroAcesso(" + user + ")");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<operacao>consultarPrimeiroAcesso</operacao>");
			sb.append("<dtInicial>" + dataInicio + "</dtInicial>");
			sb.append("<dtFinal>" + dataFinal + "</dtFinal>");
			sb.append("<idUFOperadora>" + regional + "</idUFOperadora>");
			sb.append("<idGrupoOperadora>" + operadora + "</idGrupoOperadora>");
			sb.append("<idSegmentacao>" + segmentacao + "</idSegmentacao>");
			sb.append("<idTipoCarteira>" + tipoCarteira + "</idTipoCarteira>");
			sb.append("<idCanal>" + canal + "</idCanal>");
			sb.append("<idTipoLinha>" + tipoLinha + "</idTipoLinha>");
			sb.append("<cdLinha>" + cdAreaRegistro + "</cdLinha>");
			sb.append("<idTecnologia>" + idTecnologia + "</idTecnologia>");
			sb.append("<idLoja>" + idLoja + "</idLoja>");
			sb.append("<nrTerminal>" + nrTerminal + "</nrTerminal>");

			String xmlEntrada = xmlManager.xmlInput("DADACESSO", user, sb.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlEntrada);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlEntrada);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioPrimeiroAcessoVODocument vo = VOLTAVRelatorioPrimeiroAcessoVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioPrimeiroAcessoVODocument) {
				return vo.getVOLTAVRelatorioPrimeiroAcessoVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRelatorioPrimeiroAcesso(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioPrimeiroAcesso(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioPrimeiroAcesso", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioAcessosIncidenciaVO consultaRelatorioAcessoIncidencia(String user, String dataInicio, String dataFinal, String operadora, String regional, String tipoCarteira, String segmentacao, String canal, String tipoLinha, String idTecnologia, String idLoja, String nrTerminal) throws TuxedoException, FacadeException {

		log.debug("RelatoriosFacadeVolImpl:consultaRelatorioAcessoIncidencia(" + user + ")");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<operacao>consultarAcessoIncidencia</operacao>");
			sb.append("<dtInicial>" + dataInicio + "</dtInicial>");
			sb.append("<dtFinal>" + dataFinal + "</dtFinal>");
			sb.append("<idUFOperadora>" + regional + "</idUFOperadora>");
			sb.append("<idGrupoOperadora>" + operadora + "</idGrupoOperadora>");
			sb.append("<idTipoCarteira>" + tipoCarteira + "</idTipoCarteira>");
			sb.append("<idSegmentacao>" + segmentacao + "</idSegmentacao>");
			sb.append("<idCanal>" + canal + "</idCanal>");
			sb.append("<idTipoLinha>" + tipoLinha + "</idTipoLinha>");
			sb.append("<idTecnologia>" + idTecnologia + "</idTecnologia>");
			sb.append("<idLoja>" + idLoja + "</idLoja>");
			sb.append("<nrTerminal>" + nrTerminal + "</nrTerminal>");

			String xmlEntrada = xmlManager.xmlInput("DADACESSO", user, sb.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlEntrada);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlEntrada);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioAcessosIncidenciaVODocument vo = VOLTAVRelatorioAcessosIncidenciaVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioAcessosIncidenciaVODocument) {
				return vo.getVOLTAVRelatorioAcessosIncidenciaVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRelatorioAcessoIncidencia(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioAcessoIncidencia(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioAcessoIncidencia", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioServicosDisponveisVO consultaServicosDisponiveis(String user, boolean isURA) throws TuxedoException, FacadeException {

		log.debug("RelatoriosFacadeVolImpl:consultaServicosDisponiveis(" + user + ")");
		try {
			String xmlEntrada = "<operacao>consultarServicosDisponiveis</operacao>";
			if (isURA) {
				xmlEntrada += "<idCanal>9</idCanal>";
			}

			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE",this.xmlManager.xmlInput("DADACESSO", user, xmlEntrada));
			String xmlOUT = tuxedo.callService("TuxConnector", xmlManager.xmlInput("DADACESSO", user, xmlEntrada));
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioServicosDisponveisVODocument vo = VOLTAVRelatorioServicosDisponveisVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioServicosDisponveisVODocument) {
				return vo.getVOLTAVRelatorioServicosDisponveisVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaServicosDisponiveis(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaServicosDisponiveis(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaServicosDisponiveis", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public LSTTIPORECARVO consultaRecargasDisponiveis(String user, String idRegional) throws TuxedoException, FacadeException {
		try {
			String xmlEntrada = "<idUFOperadora>" + idRegional + "</idUFOperadora>";
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlManager.xmlInput("LSTTIPORECAR", user, xmlEntrada));
			String xmlOUT = tuxedo.callService("TuxConnector", xmlManager.xmlInput("LSTTIPORECAR", user, xmlEntrada));
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			LSTTIPORECARVODocument vo = LSTTIPORECARVODocument.Factory.parse(xmlOUT);

			if (vo instanceof LSTTIPORECARVODocument) {
				return vo.getLSTTIPORECARVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRecargasDisponiveis(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRecargasDisponiveis(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRecargasDisponiveis", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioLojasVO consultaLojasDisponiveis(String user) throws TuxedoException, FacadeException {
		try {
			String xmlEntrada = "<operacao>consultarLojas</operacao>";
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux,"GETSERVICE",xmlManager.xmlInput("DADACESSO", user, xmlEntrada));
			String xmlOUT = tuxedo.callService("TuxConnector", xmlManager.xmlInput("DADACESSO", user, xmlEntrada));
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioLojasVODocument vo = VOLTAVRelatorioLojasVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioLojasVODocument) {
				return vo.getVOLTAVRelatorioLojasVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaLojasDisponiveis(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaLojasDisponiveis(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaLojasDisponiveis", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioTempoPermanenciaVO consultaRelatorioTempoPermanencia(String user, String dataInicio, String dataFinal, String operadora, String regional, String tipoCarteira, String segmentacao, String canal, String tipoLinha, String idTecnologia, String idLoja, String nrTerminal) throws TuxedoException, FacadeException {

		log.debug("RelatoriosFacadeVolImpl:consultaRelatorioTempoPermanencia(" + user + ")");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<operacao>consultarTempoPermanencia</operacao>");
			sb.append("<dtInicial>" + dataInicio + "</dtInicial>");
			sb.append("<dtFinal>" + dataFinal + "</dtFinal>");
			sb.append("<idUFOperadora>" + regional + "</idUFOperadora>");
			sb.append("<idGrupoOperadora>" + operadora + "</idGrupoOperadora>");
			sb.append("<idTipoCarteira>" + tipoCarteira + "</idTipoCarteira>");
			sb.append("<idSegmentacao>" + segmentacao + "</idSegmentacao>");
			sb.append("<idCanal>" + canal + "</idCanal>");
			sb.append("<idTipoLinha>" + tipoLinha + "</idTipoLinha>");
			sb.append("<idTecnologia>" + idTecnologia + "</idTecnologia>");
			sb.append("<idLoja>" + idLoja + "</idLoja>");
			sb.append("<nrTerminal>" + nrTerminal + "</nrTerminal>");

			String xmlEntrada = xmlManager.xmlInput("DADACESSO", user, sb.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlEntrada);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlEntrada);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioTempoPermanenciaVODocument vo = VOLTAVRelatorioTempoPermanenciaVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioTempoPermanenciaVODocument) {
				return vo.getVOLTAVRelatorioTempoPermanenciaVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRelatorioTempoPermanencia(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioTempoPermanencia(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioTempoPermanencia", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioStatusVendaVO consultaStatusVenda(String user) throws TuxedoException, FacadeException {
		try {
			String xmlEntrada = "<operacao>consultarStatusVenda</operacao>";
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux,"GETSERVICE",xmlManager.xmlInput("DADACESSO", user, xmlEntrada));
			String xmlOUT = tuxedo.callService("TuxConnector", xmlManager.xmlInput("DADACESSO", user, xmlEntrada));
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioStatusVendaVODocument vo = VOLTAVRelatorioStatusVendaVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioStatusVendaVODocument) {
				return vo.getVOLTAVRelatorioStatusVendaVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaStatusVenda(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaStatusVenda(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaStatusVenda", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioTipoErroVO consultaTipoErro(String user) throws TuxedoException, FacadeException {
		try {
			String xmlEntrada = "<operacao>consultarTipoErro</operacao>";
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux,"GETSERVICE",xmlManager.xmlInput("DADACESSO", user, xmlEntrada));
			String xmlOUT = tuxedo.callService("TuxConnector", xmlManager.xmlInput("DADACESSO", user, xmlEntrada));
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioTipoErroVODocument vo = VOLTAVRelatorioTipoErroVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioTipoErroVODocument) {
				return vo.getVOLTAVRelatorioTipoErroVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaTipoErro(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaTipoErro(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaTipoErro", ex));
		}
		return null;
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatoriosFiltroLojaVO[] obtemLojas(String user, String idUFOperadora) throws TuxedoException, FacadeException {
		try {
			StringBuffer sb = new StringBuffer("").append("\tRelatorioFacadeImpl:obtemLojas(").append(user).append(")");
			log.debug(sb.toString());

			sb = new StringBuffer();
			sb.append("<operacao>consultarLojasRelatorio</operacao>");
			sb.append("<idUFOperadora>" + idUFOperadora + "</idUFOperadora>");

			String xmlIn = XmlManager.MakeXmlIn(user, "DADACESSO", sb.toString());
			// String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn.toString());
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);
			xmlOut = MsgDocument.Factory.parse(xmlOut).getMsg().getMsgBody().xmlText();

			VOLTAVRelatoriosFiltrosVO vo = VOLTAVRelatoriosFiltrosVODocument.Factory.parse(xmlOut).getVOLTAVRelatoriosFiltrosVO();

			return vo.getVOLTAVRelatoriosFiltroLojaVOArray();

		} catch (Exception ex) {
			log.error("\t" + this.getClass() + ":obtemLojas(" + user + ") - [" + ex.getMessage() + "]", ex);
			throw (new FacadeException("Erro na montagem do XML de entrada. (XMLIN)"));
		}
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatoriosFiltroTerminalVO[] obtemTerminais(String user, String idLoja) throws TuxedoException, FacadeException {
		try {
			StringBuffer sb = new StringBuffer("").append("\tRelatorioFacadeImpl:obtemTerminais(").append(user).append(")");
			log.debug(sb.toString());

			sb = new StringBuffer();
			sb.append("<operacao>consultarTerminais</operacao>");
			sb.append("<idPessoaDePara>" + idLoja + "</idPessoaDePara>");

			String xmlIn = XmlManager.MakeXmlIn(user, "DADACESSO", sb.toString());
			// String xmlOut = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlIn.toString());
			String xmlOut = tuxedo.callService("TuxConnector", xmlIn);
			xmlOut = MsgDocument.Factory.parse(xmlOut).getMsg().getMsgBody().xmlText();

			VOLTAVRelatoriosFiltrosVO vo = VOLTAVRelatoriosFiltrosVODocument.Factory.parse(xmlOut).getVOLTAVRelatoriosFiltrosVO();

			return vo.getVOLTAVRelatoriosFiltroTerminalVOArray();

		} catch (Exception ex) {
			log.error("\t" + this.getClass() + ":obtemLojas(" + user + ") - [" + ex.getMessage() + "]", ex);
			throw (new FacadeException("Erro na montagem do XML de entrada. (XMLIN)"));
		}
	}

	/**
	 * @common:operation
	 */
	public VOLTAVRelatorioServicosEfetuadosVO consultaRelatorioBloqueioURA(String user, String dataInicio, String dataFinal, String operadora, String regional, String tipoCarteira, String segmentacao, String idContato, String canal, String tipoLinha, String cdAreaRegistro, String isAgrupado, String idTecnologia) throws TuxedoException, FacadeException {
		log.debug("RelatoriosFacadeVolImpl:consultaRelatorioBloqueioURA(" + user + ")");
		try {
			StringBuffer sb = new StringBuffer();
			sb.append("<operacao>consultarServicosEfetuadosURA</operacao>");
			sb.append("<dtInicial>" + dataInicio + "</dtInicial>");
			sb.append("<dtFinal>" + dataFinal + "</dtFinal>");
			sb.append("<idUFOperadora>" + regional + "</idUFOperadora>");
			sb.append("<idGrupoOperadora>" + operadora + "</idGrupoOperadora>");
			sb.append("<idSegmentacao>" + segmentacao + "</idSegmentacao>");
			sb.append("<idTipoCarteira>" + tipoCarteira + "</idTipoCarteira>");
			sb.append("<idContato>" + idContato + "</idContato>");
			sb.append("<idCanal>" + canal + "</idCanal>");
			sb.append("<idTipoLinha>" + tipoLinha + "</idTipoLinha>");
			sb.append("<cdLinha>" + cdAreaRegistro + "</cdLinha>");
			sb.append("<isAgrupado>" + isAgrupado + "</isAgrupado>");
			sb.append("<idTecnologia>" + idTecnologia + "</idTecnologia>");

			String xmlEntrada = xmlManager.xmlInput("DADACESSO", user, sb.toString());
			// String xmlOUT = (new ControlTuxedoCall()).execute(this, volTavTux, "GETSERVICE", xmlEntrada);
			String xmlOUT = tuxedo.callService("TuxConnector", xmlEntrada);
			xmlOUT = MsgDocument.Factory.parse(xmlOUT).getMsg().getMsgBody().xmlText();

			VOLTAVRelatorioServicosEfetuadosVODocument vo = VOLTAVRelatorioServicosEfetuadosVODocument.Factory.parse(xmlOUT);

			if (vo instanceof VOLTAVRelatorioServicosEfetuadosVODocument) {
				return vo.getVOLTAVRelatorioServicosEfetuadosVO();
			}

		} catch (TuxedoServiceCallerException ex) {
			log.error("TuxedoException - RelatoriosFacadeVolImpl:consultaRelatorioBloqueioURA(" + user + ") - [" + ex.getMessage() + "]");
			throw new TuxedoException(ex);

		} catch (Exception ex) {
			log.error("Exception - RelatoriosFacadeVolImpl:consultaRelatorioBloqueioURA(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de saida: RelatoriosFacadeVolImpl:consultaRelatorioBloqueioURA", ex));
		}
		return null;
	}
}