package br.com.vivo.fo.ctrls.cliente.telaInicial.detalheFatura;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import noNamespace.MsgDocument;

import org.apache.xmlbeans.XmlException;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.cliente.detalheFatura.AreaChamada;
import br.com.vivo.fo.cliente.detalheFatura.Chamada;
import br.com.vivo.fo.cliente.detalheFatura.DestinoChamada;
import br.com.vivo.fo.cliente.detalheFatura.TipoChamada;
import br.com.vivo.fo.cliente.detalheFatura.TipoDetalheChamada;
import br.com.vivo.fo.cliente.detalheFatura.TipoServicoChamada;
import br.com.vivo.fo.cliente.detalheFatura.TipoTarifa;
import br.com.vivo.fo.cliente.vo.ApoioParametroVODocument;
import br.com.vivo.fo.cliente.vo.ApoioParametroVODocument.ApoioParametroVO;
import br.com.vivo.fo.cliente.vo.LupaFaturamentoPosVODocument;
import br.com.vivo.fo.cliente.vo.LupaFaturamentoPosVODocument.LupaFaturamentoPosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.xml.XmlManager;

@SuppressWarnings({"rawtypes","unchecked","unused"})
@Stateless(name = "DetalheFaturaFacade", mappedName = "DetalheFaturaFacade")
@Local(DetalheFaturaFacade.class)
@Session(ejbName = "DetalheFaturaFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class DetalheFaturaFacadeImpl implements DetalheFaturaFacade {

	@EJB
	private TuxedoServiceCall tuxedo;

	private static Logger log = new Logger("clientes");

	/**
	 * @common:operation
	 */
	public LupaFaturamentoPosVO buscaContaLinha(LupaFaturamentoPosVO lupaFaturamentoPosVO, String idCliente, String user) throws TuxedoException, FacadeException {
	    String xmlIN  = ConstantesCRM.SVAZIO;
	    String xmlOUT = ConstantesCRM.SVAZIO;
		try {
		    xmlIN = lupaFaturamentoPosVO.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO);
			xmlIN = xmlIN + "<idPessoa>" + idCliente + "</idPessoa>";

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "LupaFaturamIni", xmlIN));
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);

			lupaFaturamentoPosVO = LupaFaturamentoPosVODocument.Factory.parse(msgDocRet.getMsg().getMsgBody().xmlText()).getLupaFaturamentoPosVO();

		} catch (XmlException ex) {
			log.error("XmlException - DetalheFaturaFacadeImpl:buscaContaLinha(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: DetalheFaturaFacadeImpl:buscaContaLinha", ex));

		} catch (Exception ex) {
			log.error("Exception - DetalheFaturaFacadeImpl:buscaContaLinha(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return lupaFaturamentoPosVO;
	}

	/**
	 * @common:operation
	 */
	public LupaFaturamentoPosVO buscaDetalhes(String idContaSO, String dsAcao, String user, int idTipoLinha) throws TuxedoException, FacadeException {
		LupaFaturamentoPosVODocument lupaFaturamentoPosVO;
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
        String serviceName = ConstantesCRM.SVAZIO;
		try {
			Date data = new Date(System.currentTimeMillis());
			GregorianCalendar calendar = new GregorianCalendar();
			String dia = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
			String mes = String.valueOf(calendar.get(Calendar.MONTH) + 1);
			String ano = String.valueOf(calendar.get(Calendar.YEAR));
			String dataFim = ConstantesCRM.SVAZIO;
			String dataIni = ConstantesCRM.SVAZIO;
			calendar.setTime(data);

			if (Integer.parseInt(dia) < 10) {
				dia = "0" + dia;
			}
			if (Integer.parseInt(mes) < 10) {
				mes = "0" + mes;
			}

			dataFim = ano + "-" + mes + "-" + dia;
			calendar.setLenient(true);

			if (Integer.parseInt(mes) > 3) {
				if ((Integer.parseInt(mes) - 3) < 10) {
					mes = "0" + String.valueOf(Integer.parseInt(mes) - 3);
				} else {
					mes = String.valueOf(Integer.parseInt(mes) - 3);
				}
			} else {
				ano = String.valueOf(Integer.parseInt(ano) - 1);
				if (mes.equals("01")) {
					mes = "10";
				} else if (mes.equals("02")) {
					mes = "11";
				} else {
					mes = "12";
				}
			}

			if (mes.equalsIgnoreCase("02") && ((Integer.parseInt(dia)) >= 29)) {
				dia = "28";
			} else {
				if (dia.equalsIgnoreCase("31") && (mes.equalsIgnoreCase("04") || mes.equalsIgnoreCase("06") || mes.equalsIgnoreCase("09") || mes.equalsIgnoreCase("11"))) {
					dia = "30";
				}
			}
			dataIni = ano + "-" + mes + "-" + dia;

			switch (idTipoLinha) {
    			case 1: // Pos
    			case 5: // Pos GSM
    			case 4: // Pre
    			case 7: // Pre GSM
    				serviceName = "TUXATLYSBE";
    				break;
    			case 2: // Pre
    			case 6: // Pre GSM
    				serviceName = "TUXNGINBE";
    				break;
			}
			
			if (dsAcao.equalsIgnoreCase("getAjustes")) {
				xmlIN = XmlManager.MakeXmlIn(user, serviceName, "<ProxyLinha></ProxyLinha><ProxyOperacao>" + dsAcao + "</ProxyOperacao><xmlns>cliente.fo.vivo.com.br/vo</xmlns><idcontasistemaorigem>" + idContaSO + "</idcontasistemaorigem><dataIni>1990-01-01</dataIni><dataFim>" + dataFim + "</dataFim><usuario>FO</usuario><idCanal>1</idCanal>");
			} else {
				xmlIN = XmlManager.MakeXmlIn(user, serviceName, "<ProxyLinha></ProxyLinha><ProxyOperacao>" + dsAcao + "</ProxyOperacao><xmlns>cliente.fo.vivo.com.br/vo</xmlns><idcontasistemaorigem>" + idContaSO + "</idcontasistemaorigem><dataIni>" + dataIni + "</dataIni><dataFim>" + dataFim + "</dataFim><qtFaturamentos>5</qtFaturamentos><usuario>FO</usuario><idCanal>1</idCanal>");
			}

			// xmlOUT = (new ControlTuxedoCall()).execute(this, clienteTux, "GETSERVICE", xmlIN);
			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			lupaFaturamentoPosVO = LupaFaturamentoPosVODocument.Factory.parse(xmlOUT);
		} catch (XmlException ex) {
			log.error("XmlException - DetalheFaturaFacadeImpl:buscaDetalhes(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: DetalheFaturaFacadeImpl:buscaDetalhes", ex));

		} catch (Exception ex) {
			log.error("Exception - DetalheFaturaFacadeImpl:buscaDetalhes(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return lupaFaturamentoPosVO.getLupaFaturamentoPosVO();
	}

	/**
	 * @common:operation
	 */
	public LupaFaturamentoPosVO getLinhaIntraGrupo(String user, String idContaSO) throws TuxedoException, FacadeException {
		LupaFaturamentoPosVODocument lupaFaturamentoPosVO;
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = "<idcontasistemaorigem>" + idContaSO + "</idcontasistemaorigem>";
			xmlIN = XmlManager.MakeXmlIn(user, "LFINTRAGRUPO", xmlIN);

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			lupaFaturamentoPosVO = LupaFaturamentoPosVODocument.Factory.parse(xmlOUT);

		} catch (XmlException ex) {
			log.error("XmlException - DetalheFaturaFacadeImpl:getLinhaIntraGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: DetalheFaturaFacadeImpl:getLinhaIntraGrupo", ex));

		} catch (Exception ex) {
			log.error("Exception - DetalheFaturaFacadeImpl:getLinhaIntraGrupo(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return lupaFaturamentoPosVO.getLupaFaturamentoPosVO();
	}

	/**
	 * @common:operation
	 */
	public ApoioParametroVO getApoioParametro(String user, ApoioParametroVO inParametro) throws TuxedoException, FacadeException {
		ApoioParametroVODocument apoioParametroVO;
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = XmlManager.MakeXmlIn(user, "GETURLROUTER", inParametro.xmlText().replaceAll("vo:", ConstantesCRM.SVAZIO));

			xmlOUT = tuxedo.callService("TuxConnector", xmlIN);
			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();
			apoioParametroVO = ApoioParametroVODocument.Factory.parse(xmlOUT);

		} catch (XmlException ex) {
			log.error("XmlException - DetalheFaturaFacadeImpl:getApoioParametro(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException("Erro na montagem do XML de entrada: DetalheFaturaFacadeImpl:getApoioParametro", ex));

		} catch (Exception ex) {
			log.error("Exception - DetalheFaturaFacadeImpl:getApoioParametro(" + user + ") - [" + ex.getMessage() + "]");
			throw (new FacadeException(ex));
		}
		return apoioParametroVO.getApoioParametroVO();
	}

	/**
	 * @common:operation
	 */
	public String getAcctImage(String ddd, String celular, String date, String idSistOrigem, String user) throws Exception {
        String xmlIN  = ConstantesCRM.SVAZIO;
        String xmlOUT = ConstantesCRM.SVAZIO;
		try {
			xmlIN = getXmlConta(ddd, celular, idSistOrigem, date);

			xmlOUT = tuxedo.callService("TuxConnector", XmlManager.MakeXmlIn(user, "IMGCONTA", xmlIN));

			MsgDocument msgDocRet = MsgDocument.Factory.parse(xmlOUT);
			xmlOUT = msgDocRet.getMsg().getMsgBody().xmlText();

		} catch (Exception e) {
			log.error("Exception - DetalheFaturaFacadeImpl:getAcctImage(): Erro ao invocar o serviço Tuxedo de Imagem de Conta - [" + e.getMessage() + "]");
			throw e;
		}
		return xmlOUT;
	}

	/**
	 * @common:operation
	 */
	public Collection getPesquisaFiltros(String nrConta, String dtReferencia, String dtInicio, String dtTermino, String nrLinhaOrigem, String nrLinhaDestino, String tpOrigemChamada, String tpArea, String tpDetalheChamada, String tpServico, String tpDestino, String paginaInicial, String paginaFinal) {

		Collection colChamada = new ArrayList();
		int paginaInicio = Integer.parseInt(paginaInicial);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdfData = new SimpleDateFormat("dd-MM-yyyy");
		SimpleDateFormat sdfddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfMask = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdfdtRefencia = new SimpleDateFormat("MM/yyyy");
		SimpleDateFormat sdfdtRefenciaFormat = new SimpleDateFormat("yyyyMM");

		try {
			Connection conn = null;
			PreparedStatement st = null;
			int controleFiltros = 1;

			try {
				conn = getConnection();

				// Monta a particao a ser pesquisada
				String particao = this.getParticao(nrConta);
				log.debug("Particao retornada pela query: " + particao);

				String query = this.getSQLPesquisaFiltros(nrConta, dtReferencia, dtInicio, dtTermino, nrLinhaOrigem, nrLinhaDestino, tpOrigemChamada, tpArea, tpDetalheChamada, tpServico, tpDestino, particao);

				log.debug("Query para pesquisa de filtros: " + query);
				st = conn.prepareStatement(query);
				st.setString(controleFiltros, nrConta);
				controleFiltros++;

				// Pesquisa por intervalo
				if (dtInicio != null && dtTermino != null) {
					st.setString(controleFiltros, sdfddMMyyyy.format(sdfData.parse(dtInicio)));
					controleFiltros++;

					st.setString(controleFiltros, sdfddMMyyyy.format(sdfData.parse(dtTermino)));
					controleFiltros++;
				
				} else {
					st.setString(controleFiltros, dtReferencia);
					controleFiltros++;
				}

				// Filtros da pesquisa
				if (tpOrigemChamada != null && !tpOrigemChamada.equals("T")) {
					st.setString(controleFiltros, tpOrigemChamada);
					controleFiltros++;
				}
				
				if (tpDestino != null && !tpDestino.equals("T")) {
					st.setString(controleFiltros, tpDestino);
					controleFiltros++;
				}
				
				if (tpDetalheChamada != null && !tpDetalheChamada.equals("T")) {
					st.setString(controleFiltros, tpDetalheChamada);
					controleFiltros++;
				}
				
				if (tpServico != null && !tpServico.equals("T")) {
					st.setString(controleFiltros, tpServico);
					controleFiltros++;
				}
				
				if (tpArea != null && !tpArea.equals("T")) {
					st.setString(controleFiltros, tpArea);
					controleFiltros++;
				}

				// Numero Destino / Numero Origem
				if (nrLinhaOrigem != null && !nrLinhaOrigem.equals(ConstantesCRM.SVAZIO)) {
					st.setString(controleFiltros, nrLinhaOrigem);
					controleFiltros++;
				}
				
				if (nrLinhaDestino != null && !nrLinhaDestino.equals(ConstantesCRM.SVAZIO)) {
					st.setString(controleFiltros, nrLinhaDestino);
					controleFiltros++;
				}

				// Sta o ponteiro da paginacao
				st.setString(controleFiltros, paginaInicial);
				controleFiltros++;
				st.setString(controleFiltros, paginaFinal);

				ResultSet rs = st.executeQuery();

				Chamada chamada = null;
				AreaChamada areaChamada = null;
				TipoTarifa tipoTarifa = null;
				TipoServicoChamada tipoServico = null;
				TipoDetalheChamada tipoDetalhe = null;
				TipoChamada tipoChamada = null;
				DestinoChamada destinoChamada = null;
				String horaChamada = null;
				String numeroOrigem = null;
				String dataChamada = null;
				String valorChamada = null;
				String duracaoChamada = null;

				while (rs.next()) {
					chamada = new Chamada();
					horaChamada = rs.getString("HR_CHAMADA");
					dataChamada = rs.getString("DATA_CHAMADA");

					if (dataChamada != null && !dataChamada.equals(ConstantesCRM.SVAZIO)) {
						dataChamada = sdfddMMyyyy.format(sdf.parse(dataChamada));
					}

					chamada.setDataChamada(dataChamada);
					chamada.setHoraChamada(horaChamada != null ? horaChamada.substring(11, 16) : ConstantesCRM.SVAZIO);
					chamada.setNumeroOrigem(rs.getString("NR_NUMERO_ORIGEM"));

					numeroOrigem = rs.getString("NR_NUMERO_DESTINO");
					if (numeroOrigem != null && numeroOrigem.equals(ConstantesCRM.SZERO)) {
						numeroOrigem = ConstantesCRM.SVAZIO;
					}

					chamada.setNumeroDestino(numeroOrigem);

					valorChamada = rs.getString("VL_CHAMADA");
					duracaoChamada = rs.getString("QT_DURACAO_CHAMADA");

					chamada.setValor(valorChamada);
					chamada.setDuracao(duracaoChamada);

					chamada.setValorFormatado(this.formataValor(valorChamada));
					chamada.setDuracaoFormatada(this.formataValor(duracaoChamada));

					tipoTarifa = new TipoTarifa();
					tipoTarifa.setCodigo(new BigInteger(rs.getString("CD_TIPO_TARIFA")));
					tipoTarifa.setDescricao(rs.getString("DS_TIPO_TARIFA"));
					chamada.setTipoTarifa(tipoTarifa);

					areaChamada = new AreaChamada();
					areaChamada.setCodigo(new BigInteger(rs.getString("CD_AREA_SERVICO")));
					areaChamada.setDescricao(rs.getString("DS_AREA_SERVICO"));
					chamada.setAreaChamada(areaChamada);

					tipoServico = new TipoServicoChamada();
					tipoServico.setCodigo(rs.getString("CD_TIPO_SERVICO"));
					tipoServico.setDescricao(rs.getString("DS_TIPO_SERVICO"));
					chamada.setTipoServicoChamada(tipoServico);

					tipoDetalhe = new TipoDetalheChamada();
					tipoDetalhe.setCodigo(new BigInteger(rs.getString("CD_DETALHE_CHAMADA")));
					tipoDetalhe.setDescricao(rs.getString("DS_DETALHE_CHAMADA"));
					chamada.setTipoDetalheChamada(tipoDetalhe);

					tipoChamada = new TipoChamada();
					tipoChamada.setDescricao(rs.getString("IN_TIPO_CHAMADA"));
					chamada.setTipoChamada(tipoChamada);

					destinoChamada = new DestinoChamada();
					destinoChamada.setDescricao(rs.getString("IN_DESTINO_CHAMADA"));
					chamada.setDestinoChamada(destinoChamada);

					chamada.setIdChamada(paginaInicio);
					paginaInicio++;

					colChamada.add(chamada);
				}

				rs.close();
			
			} catch (SQLException e) {
				log.error("SQLException::", e);
			
			} finally {
				try {
					if (st != null) {
						st.close();
					}
					
					conn.close();
					st = null;
					conn = null;
				
				} catch (SQLException e1) {
					log.error("SQLException::ps.close", e1);
				}
			}

		} catch (Exception e) {
			log.error("Exception::", e);
		}
		
		return colChamada;
	}

	/**
	 * @common:operation
	 */
	public int getPesquisaFiltrosQtdRegistros(String nrConta, String dtReferencia, String dtInicio, String dtTermino, String nrLinhaOrigem, String nrLinhaDestino, String tpOrigemChamada, String tpArea, String tpDetalheChamada, String tpServico, String tpDestino, String paginaInicial, String paginaFinal) {

		try {
			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs = null;
			int controleFiltros = 1;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat sdfData = new SimpleDateFormat("dd-MM-yyyy");
			SimpleDateFormat sdfddMMyyyy = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat sdfMask = new SimpleDateFormat("yyyyMMdd");
			SimpleDateFormat sdfdtRefencia = new SimpleDateFormat("MM/yyyy");
			SimpleDateFormat sdfdtRefenciaFormat = new SimpleDateFormat("yyyyMM");

			try {
				conn = getConnection();

				// Monta a particao a ser pesquisada
				String particao = this.getParticao(nrConta);
				log.debug("Particao retornada pela query: " + particao);

				String query = this.getSQLQuantidadeRegistrosPesquisaFiltros(nrConta, dtReferencia, dtInicio, dtTermino, nrLinhaOrigem, nrLinhaDestino, tpOrigemChamada, tpArea, tpDetalheChamada, tpServico, tpDestino, particao);

				log.debug("Query para pesquisa de filtros: " + query);
				st = conn.prepareStatement(query);
				st.setString(controleFiltros, nrConta);
				controleFiltros++;

				// Pesquisa por intervalo
				if (dtInicio != null && dtTermino != null) {
					st.setString(controleFiltros, sdfddMMyyyy.format(sdfData.parse(dtInicio)));
					controleFiltros++;

					st.setString(controleFiltros, sdfddMMyyyy.format(sdfData.parse(dtTermino)));
					controleFiltros++;
				} else {
					st.setString(controleFiltros, dtReferencia);
					controleFiltros++;
				}

				// Filtros da pesquisa
				if (tpOrigemChamada != null && !tpOrigemChamada.equals("T")) {
					st.setString(controleFiltros, tpOrigemChamada);
					controleFiltros++;
				}
				if (tpDestino != null && !tpDestino.equals("T")) {
					st.setString(controleFiltros, tpDestino);
					controleFiltros++;
				}
				if (tpDetalheChamada != null && !tpDetalheChamada.equals("T")) {
					st.setString(controleFiltros, tpDetalheChamada);
					controleFiltros++;
				}
				if (tpServico != null && !tpServico.equals("T")) {
					st.setString(controleFiltros, tpServico);
					controleFiltros++;
				}
				if (tpArea != null && !tpArea.equals("T")) {
					st.setString(controleFiltros, tpArea);
					controleFiltros++;
				}

				// Numero Destino / Numero Origem
				if (nrLinhaOrigem != null && !nrLinhaOrigem.equals(ConstantesCRM.SVAZIO)) {
					st.setString(controleFiltros, nrLinhaOrigem);
					controleFiltros++;
				}
				if (nrLinhaDestino != null && !nrLinhaDestino.equals(ConstantesCRM.SVAZIO)) {
					st.setString(controleFiltros, nrLinhaDestino);
					controleFiltros++;
				}

				rs = st.executeQuery();

				if (rs.next()) {
					return Integer.parseInt(rs.getString("total"));
				} else {
					return 0;
				}
			} catch (SQLException e) {
				log.error("SQLException::", e);
			
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					conn.close();
					st = null;
					conn = null;
				} catch (SQLException e1) {
					log.error("SQLException::ps.close", e1);
				}
			}

		} catch (Exception e) {
			log.error("Exception::", e);
		}
		return 0;
	}

	/**
	 * @common:operation
	 */
	public Collection getMesesCarregadosContaOnline(String nrConta) {

		Collection colRetorno = new ArrayList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat sdfFormat = new SimpleDateFormat("dd/MM/yyyy");

		try {
			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs = null;

			try {
				conn = getConnection();
				String query = this.getSQLMesesCarregadosContaOnline();

				log.debug("Query para pesquisa dos meses disponiveis no conta online: " + query);
				st = conn.prepareStatement(query);
				st.setString(1, nrConta);

				rs = st.executeQuery();

				while (rs.next()) {
					colRetorno.add(sdfFormat.format(sdf.parse(rs.getString("DT_REFERENCIA") + "01")));
				}

			} catch (SQLException e) {
				log.error("SQLException::", e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					conn.close();
					st = null;
					conn = null;
				} catch (SQLException e1) {
					log.error("SQLException::ps.close", e1);
				}
			}

		} catch (Exception e) {
			log.error("Exception::", e);
		}
		return colRetorno;
	}

	private String getParticao(String nrConta) {

		try {
			Connection conn = null;
			PreparedStatement st = null;
			ResultSet rs = null;

			try {
				conn = getConnection();
				String query = this.getSQLParticao();

				log.debug("Query para pesquisa da particao: " + query);
				st = conn.prepareStatement(query);
				st.setString(1, nrConta);

				rs = st.executeQuery();

				if (rs.next()) {
					return rs.getString("CD_PARTICAO");
				}

			} catch (SQLException e) {
				log.error("SQLException::", e);
			} finally {
				try {
					if (rs != null) {
						rs.close();
					}
					if (st != null) {
						st.close();
					}
					conn.close();
					st = null;
					conn = null;
				} catch (SQLException e1) {
					log.error("SQLException::ps.close", e1);
				}
			}

		} catch (Exception e) {
			log.error("Exception::", e);
		}
		return ConstantesCRM.SVAZIO;
	}

	private String getXmlConta(String ddd, String celular, String idConta, String data) {
		StringBuffer buff = new StringBuffer();
		buff.append("<ARG>");
		buff.append("<REG>");
		buff.append("<COD_AREA>" + ddd + "</COD_AREA>");
		buff.append("<NUM_LINE>" + celular + "</NUM_LINE>");
		buff.append("<PLATAFORMA>O</PLATAFORMA>");
		buff.append("<SERVICO>CONTA</SERVICO>");
		buff.append("<OPERACAO>" + "CONTADET" + "</OPERACAO>");
		buff.append("<CANAL>TAV</CANAL>");
		buff.append("<USUARIO>TAV</USUARIO>");
		buff.append("<SENHA>123456</SENHA>");
		buff.append("<ORIGEM>TAV</ORIGEM>");
		buff.append("</REG>");
		buff.append("<DADOS>");
		buff.append("<CONTA>" + idConta + "</CONTA>");
		buff.append("<DATA>" + data + "</DATA>");
		buff.append("<RETORNO>HTML</RETORNO>");
		buff.append("</DADOS>");
		buff.append("</ARG>");
		return buff.toString();
	}

	private String getOperacaoControle(int idOperacaoTipoLinhaControle) {
		String serviceName = "TUXATLYSBE";
		switch (idOperacaoTipoLinhaControle) {
		case ConstantesCRM.OPERACAO_SERVICO:
			serviceName = "TUXATLYSBE";
			break;
		case ConstantesCRM.OPERACAO_CONSUMO:
			serviceName = "TUXATLYSBE";
			break;
		case ConstantesCRM.OPERACAO_DETALHE_SALDO:
			serviceName = "TUXNGINBE";
			break;
		case ConstantesCRM.OPERACAO_ESTIMATIVA_SALDO:
			serviceName = "TUXATLYSBE";
			break;
		case ConstantesCRM.OPERACAO_EXTRATO:
			serviceName = "TUXNGINBE";
			break;
		case ConstantesCRM.OPERACAO_HISTORICO_MOVIMENTO:
			serviceName = "TUXNGINBE";
			break;
		case ConstantesCRM.OPERACAO_PLANO:
			serviceName = "TUXNGINBE";
			break;
		case ConstantesCRM.OPERACAO_TARIFA_REDUZIDA:
			serviceName = "TUXNGINBE";
			break;
		case ConstantesCRM.OPERACAO_CAIXA_POSTAL:
			serviceName = "TUXNGINBE";
			break;
		case ConstantesCRM.OPERACAO_SUSPENDE_CELULAR:
			serviceName = "TUXATLYSBE";
			break;
		case ConstantesCRM.OPERACAO_DETALHE_FATURA:
			serviceName = "TUXNGINBE";
			break;
		}
		return serviceName;
	}

	private Connection getConnection() {
		Context ctx = null;
		Hashtable ht = new Hashtable();
		Connection conn = null;
		try {
			ht.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			ctx = new InitialContext(ht);
			javax.sql.DataSource ds = (javax.sql.DataSource) ctx.lookup("jdbc.contaOnlineDS");
			conn = ds.getConnection();
            while(conn.isClosed()) {
            	conn = ds.getConnection();
            }
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			conn = null;
			log.error("SQLException::", e);
		} catch (NamingException e) {
			conn = null;
			log.error("NamingException::", e);
		} finally {
		}
		return conn;
	}

	private String getSQLPesquisaFiltros(String nrConta, String dtReferencia, String dtInicio, String dtTermino, String nrLinhaOrigem, String nrLinhaDestino, String tpOrigemChamada, String tpArea, String tpDetalheChamada, String tpServico, String tpDestino, String particao) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT *  ");
		sql.append("FROM ( SELECT NOVATABELA.*,  ");
		sql.append("ROWNUM RNUMBER  ");
		sql.append("FROM (  ");
		sql.append("SELECT CHAMADA.NR_CONTA, ");
		sql.append("CHAMADA.DT_REFERENCIA, ");
		sql.append("CHAMADA.DT_CHAMADA DATA_CHAMADA, CHAMADA.HR_CHAMADA, ");
		sql.append("CHAMADA.NR_NUMERO_ORIGEM, NR_NUMERO_DESTINO, ");
		sql.append("CHAMADA.VL_CHAMADA, CHAMADA.QT_DURACAO_CHAMADA, ");
		sql.append("CHAMADA.CD_TIPO_TARIFA,  TARIFA.DS_TIPO_TARIFA, ");
		sql.append("CHAMADA.CD_AREA_SERVICO, AREA.DS_AREA_SERVICO, ");
		sql.append("CHAMADA.CD_TIPO_SERVICO, SERVICO.DS_TIPO_SERVICO, ");
		sql.append("CHAMADA.CD_DETALHE_CHAMADA, DETALHE.DS_DETALHE_CHAMADA, ");
		sql.append("CHAMADA.IN_TIPO_CHAMADA, ");
		sql.append("CHAMADA.IN_DESTINO_CHAMADA ");
		sql.append("FROM (SELECT * FROM COLPJ_CHAMADA PARTITION (P" + particao + ") C "); // YYYYMMDD
		sql.append("WHERE C.NR_CONTA = ? ");

		// Verifica se deve fazer a pesquisa por Mes de Referencia ou por
		// Período
		if (dtInicio != null && dtTermino != null) {
			sql.append("AND TRUNC(DT_CHAMADA) >= TO_DATE(?,'dd/mm/yyyy') ");
			sql.append("AND  TRUNC(DT_CHAMADA) <= TO_DATE(?,'dd/mm/yyyy')) ");
		} else {
			sql.append("AND DT_REFERENCIA = ? )");
		}

		sql.append("CHAMADA, ");
		sql.append("COLPJ_TIPO_TARIFA TARIFA, ");
		sql.append("COLPJ_AREA_SERVICO AREA,  ");
		sql.append("COLPJ_TIPO_SERVICO SERVICO, ");
		sql.append("COLPJ_DETALHE_CHAMADA DETALHE ");
		sql.append("WHERE  ");
		sql.append("CHAMADA.CD_DETALHE_CHAMADA = DETALHE.CD_DETALHE_CHAMADA AND ");
		sql.append("CHAMADA.CD_TIPO_SERVICO = SERVICO.CD_TIPO_SERVICO AND ");
		sql.append("CHAMADA.CD_AREA_SERVICO = AREA.CD_AREA_SERVICO AND ");
		sql.append("CHAMADA.CD_TIPO_TARIFA = TARIFA.CD_TIPO_TARIFA ");

		// Filtros da pesquisa
		if (tpOrigemChamada != null && !tpOrigemChamada.equals("T")) {
			sql.append("AND CHAMADA.IN_TIPO_CHAMADA = ? ");
		}
		if (tpDestino != null && !tpDestino.equals("T")) {
			sql.append("AND CHAMADA.IN_DESTINO_CHAMADA = ? ");
		}
		if (tpDetalheChamada != null && !tpDetalheChamada.equals("T")) {
			sql.append("AND CHAMADA.CD_DETALHE_CHAMADA = ? ");
		}
		if (tpServico != null && !tpServico.equals("T")) {
			sql.append("AND CHAMADA.CD_TIPO_SERVICO = ? ");
		}
		if (tpArea != null && !tpArea.equals("T")) {
			sql.append("AND CHAMADA.CD_AREA_SERVICO = ? ");
		}

		// Numero Destino / Numero Origem
		if (nrLinhaOrigem != null && !nrLinhaOrigem.equals(ConstantesCRM.SVAZIO)) {
			sql.append("AND NR_NUMERO_ORIGEM = ? ");
		}
		if (nrLinhaDestino != null && !nrLinhaDestino.equals(ConstantesCRM.SVAZIO)) {
			sql.append("AND NR_NUMERO_DESTINO = ? ");
		}

		sql.append("ORDER BY HR_CHAMADA ASC) NOVATABELA ) ");
		sql.append("WHERE RNUMBER BETWEEN ? AND ? ");

		return sql.toString();
	}

	private String getSQLQuantidadeRegistrosPesquisaFiltros(String nrConta, String dtReferencia, String dtInicio, String dtTermino, String nrLinhaOrigem, String nrLinhaDestino, String tpOrigemChamada, String tpArea, String tpDetalheChamada, String tpServico, String tpDestino, String particao) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(1) AS TOTAL ");
		sql.append("FROM (SELECT * FROM COLPJ_CHAMADA PARTITION (P" + particao + ") C "); // YYYYMMDD
		sql.append("WHERE C.NR_CONTA = ? ");

		// Verifica se deve fazer a pesquisa por Mes de Referencia ou por Período
		if (dtInicio != null && dtTermino != null) {
			sql.append("AND TRUNC(DT_CHAMADA) >= TO_DATE(?,'dd/mm/yyyy') ");
			sql.append("AND  TRUNC(DT_CHAMADA) <= TO_DATE(?,'dd/mm/yyyy')) ");
		
		} else {
			sql.append("AND DT_REFERENCIA = ? )");
		}

		sql.append("CHAMADA, ");
		sql.append("COLPJ_TIPO_TARIFA TARIFA, ");
		sql.append("COLPJ_AREA_SERVICO AREA,  ");
		sql.append("COLPJ_TIPO_SERVICO SERVICO, ");
		sql.append("COLPJ_DETALHE_CHAMADA DETALHE ");
		sql.append("WHERE  ");
		sql.append("CHAMADA.CD_DETALHE_CHAMADA = DETALHE.CD_DETALHE_CHAMADA AND ");
		sql.append("CHAMADA.CD_TIPO_SERVICO = SERVICO.CD_TIPO_SERVICO AND ");
		sql.append("CHAMADA.CD_AREA_SERVICO = AREA.CD_AREA_SERVICO AND ");
		sql.append("CHAMADA.CD_TIPO_TARIFA = TARIFA.CD_TIPO_TARIFA ");

		// Filtros da pesquisa
		if (tpOrigemChamada != null && !tpOrigemChamada.equals("T")) {
			sql.append("AND CHAMADA.IN_TIPO_CHAMADA = ? ");
		}
		if (tpDestino != null && !tpDestino.equals("T")) {
			sql.append("AND CHAMADA.IN_DESTINO_CHAMADA = ? ");
		}
		if (tpDetalheChamada != null && !tpDetalheChamada.equals("T")) {
			sql.append("AND CHAMADA.CD_DETALHE_CHAMADA = ? ");
		}
		if (tpServico != null && !tpServico.equals("T")) {
			sql.append("AND CHAMADA.CD_TIPO_SERVICO = ? ");
		}
		if (tpArea != null && !tpArea.equals("T")) {
			sql.append("AND CHAMADA.CD_AREA_SERVICO = ? ");
		}

		// Numero Destino / Numero Origem
		if (nrLinhaOrigem != null && !nrLinhaOrigem.equals(ConstantesCRM.SVAZIO)) {
			sql.append("AND NR_NUMERO_ORIGEM = ? ");
		}
		if (nrLinhaDestino != null && !nrLinhaDestino.equals(ConstantesCRM.SVAZIO)) {
			sql.append("AND NR_NUMERO_DESTINO = ? ");
		}

		return sql.toString();
	}

	private String getSQLMesesCarregadosContaOnline() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT DT_REFERENCIA FROM COLPJ_CONSULTA_CONTA WHERE NR_CONTA = ? ");

		return sql.toString();
	}

	private String getSQLParticao() {
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT CD_PARTICAO FROM COLPJ_CHAMADA WHERE NR_CONTA = ? AND ROWNUM = 1");

		return sql.toString();
	}

	public String formataValor(String valor) {
		if (valor == null || valor.equals(ConstantesCRM.SVAZIO) || valor.equals("null")) {
			valor = ConstantesCRM.SZERO;
		}
		DecimalFormatSymbols dSymbols = new DecimalFormatSymbols();
		dSymbols.setDecimalSeparator(',');
		dSymbols.setGroupingSeparator('.');
		// DecimalFormat dFormat = new DecimalFormat("00",dSymbols);
		DecimalFormat dFormat = new DecimalFormat();
		// dFormat.isDecimalSeparatorAlwaysShown();
		// dFormat.applyPattern("###,#00.00;(###,#00.00)");
		return dFormat.format(new Double(valor));
	}
}
