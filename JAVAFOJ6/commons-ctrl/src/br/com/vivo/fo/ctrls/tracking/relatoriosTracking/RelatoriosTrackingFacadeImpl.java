package br.com.vivo.fo.ctrls.tracking.relatoriosTracking;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session;
import weblogic.ejbgen.Session.SessionType;
import br.com.vivo.fo.cliente.vo.TrackingRelatorioConsolidadoVODocument.TrackingRelatorioConsolidadoVO;
import br.com.vivo.fo.cliente.vo.TrackingRelatorioDetalhadoVODocument.TrackingRelatorioDetalhadoVO;
import br.com.vivo.fo.cliente.vo.TrackingRelatoriosFiltrosVODocument.TrackingRelatoriosFiltrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.tracking.relatoriosTracking.dbClasses.TrackingRelatorioConsolidado;
import br.com.vivo.fo.ctrls.tracking.relatoriosTracking.dbClasses.TrackingRelatorioDetalhado;
import br.com.vivo.fo.log.Logger;

/**
 * @editor-info:code-gen control-interface="true"
 */
@Stateless(name="RelatoriosTrackingFacade",mappedName="RelatoriosTrackingFacade")
@Local(RelatoriosTrackingFacade.class)
@Session(ejbName = "RelatoriosTrackingFacade", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, 
		defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class RelatoriosTrackingFacadeImpl implements RelatoriosTrackingFacade {

	static final long serialVersionUID = 18875454541L;

	private transient Logger log = new Logger("clientes");

	@EJB
	private br.com.vivo.fo.ctrls.tracking.relatoriosTracking.RelatoriosTrackingDB relatoriosTrackingDB;

	/**
	 * @common:operation
	 */
	public TrackingRelatorioConsolidadoVO getRelatorioConsolidado(TrackingRelatoriosFiltrosVO filtro) throws Exception {

		StringBuffer query = new StringBuffer();

		query.append(" SELECT status, ");
		query.append(" decode(status, ");
		query.append("   1,'Venda no Canal', ");
		query.append("   2,'OV Criada SAP', ");
		query.append("   3,'OV Bloqueada', ");
		query.append("   4,'OV com Fornecimento Gerado', ");
		query.append("   5,'OV com Picking Gerado', ");
		query.append("   6,'OV em transito', ");
		query.append("   7,'OV c/ info parcial') as dsStatus, ");
		query.append(" SUM(volume1) volume1, SUM(volume2)volume2, SUM(volume3)volume3, SUM(volume4) volume4, SUM(volume5) volume5, SUM(volume6) volume6, SUM(volume7) volume7, SUM(volume8) volume8, MAX(agingstatus) agingstatus, MAX(agingpedido) agingpedido ");
		query.append(" FROM tracking.auxrelatorio1 ");
		query.append(" WHERE ");

		query.append(getCommonWhereClausule(filtro));

		query.append("GROUP BY status ORDER BY status, dsStatus");

		log.debug("RelatoriosTrackingFacadeImpl:getRelatorioConsolidado() = " + query.toString());
		TrackingRelatorioConsolidado[] dbResult = relatoriosTrackingDB.getTrackingRelatorioConsolidado(query.toString());

		TrackingRelatorioConsolidadoVO trackingRelatorioVO = buildTrackingRelatorioConsolidadoVO(dbResult);

		return trackingRelatorioVO;
	}

	/**
	 * @common:operation
	 */
	public TrackingRelatorioDetalhadoVO getRelatorioDetalhado(TrackingRelatoriosFiltrosVO filtro) throws Exception {

		StringBuffer query = new StringBuffer();
		StringBuffer queryCount = new StringBuffer();
		int nrItensPorPagina = filtro.getNrItensPorPagina();
		int nrRegistroAtual = 0;

		int itemInicial = ((filtro.getPaginacao().getPageNumber() - 1) * nrItensPorPagina) + 1;
		int itemFinal = filtro.getPaginacao().getPageNumber() * nrItensPorPagina;

		queryCount.append("SELECT COUNT(nrpedido) FROM TRACKING.AUXRELATORIO2 WHERE ");
		query.append("SELECT * ").append("   FROM (SELECT ROWNUM nrRegistro, a.* ").append("           FROM ( ").append("SELECT TO_CHAR(DTPEDIDO,'DD/MM/YYYY HH24:MI') DTPEDIDO, NRPEDIDO, NRORDEMVENDA, TO_CHAR(DTORDEMVENDA,'DD/MM/YYYY HH24:MI') DTORDEMVENDA, DSSTATUS, ").append("       NMRESPONSAVEL, MOTIVOBLOQUEIO, TO_CHAR(DTBLOQUEIO,'DD/MM/YYYY HH24:MI') DTBLOQUEIO, SGUF, IDCANAL IDCANALVENDA, ").append("       DECODE (IDCANAL, ").append("               30, 'Clientes Especiais', ")
				.append("               40, 'Loja Virtual', ").append("               55, 'Ação Concorrência (Captação)', ").append("               00, 'Revenda', ").append("               05, 'Varejo', ").append("               10, 'Gr Contas', ").append("               15, 'Clientes Nacionais', ").append("               20, 'Dados Corporativos', ").append("               25, 'PEMES', ").append("               35, 'Loja Própria', ").append("               50, 'Funcionários', ")
				.append("               91, 'DETRAF', ").append("               92, 'Co-Billing', ").append("               IDCANAL ").append("              ) DSCANALVENDA, ").append("       QTDAPARELHO, NRFORNECIMENTO, TO_CHAR(DTFORNECIMENTO,'DD/MM/YYYY HH24:MI') DTFORNECIMENTO, NRPICKING, TO_CHAR(DTPICKING,'DD/MM/YYYY HH24:MI') DTPICKING, ").append("       NRNOTAFISCAL, TO_CHAR(DTNOTAFISCAL,'DD/MM/YYYY HH24:MI') DTNOTAFISCAL, TEMPOSTATUS, TEMPODECORRIDO, ")
				.append("       NMRESPONSAVELOV, INTRANSPORTE, TO_CHAR(DTTRANSPORTE,'DD/MM/YYYY HH24:MI') DTTRANSPORTE ").append("  FROM TRACKING.AUXRELATORIO2 ").append(" WHERE ");

		query.append(getCommonWhereClausule(filtro));
		queryCount.append(getCommonWhereClausule(filtro));

		// Relatório detalhado de pedidos em aberto
		if ("RDPA".equals(filtro.getSgTipoRelatorio())) {
			query.append(" AND IDRELATORIO = 2 ");
			queryCount.append(" AND IDRELATORIO = 2 ");
		} else

		// Relatório detalhado para monitorar o operador logístico
		if ("RDOL".equals(filtro.getSgTipoRelatorio())) {
			query.append(" AND IDRELATORIO = 6 ");
			queryCount.append(" AND IDRELATORIO = 6 ");
		}

		query.append(" ORDER BY NRPEDIDO, NRORDEMVENDA, DTSTATUS) ").append(" a) ").append(" WHERE NRREGISTRO BETWEEN ").append(itemInicial).append(" AND ").append(itemFinal);

		log.debug("RelatoriosTrackingFacadeImpl:getRelatorioDetalhado() = " + query.toString());
		
		TrackingRelatorioDetalhado[] queryClass = relatoriosTrackingDB.getTrackingRelatorioDetalhado(query.toString());		
		int qtdeTotalResultados = relatoriosTrackingDB.getTrackingRelatorioDetalhadoCount(queryCount.toString());
		TrackingRelatorioDetalhadoVO trackingRelatorioVO = buildTrackingRelatorioDetalhadoVO(queryClass);
		trackingRelatorioVO.addNewPaginacao().setRecordCount(qtdeTotalResultados);
		trackingRelatorioVO.getPaginacao().setPageNumber(filtro.getPaginacao().getPageNumber());
		if (nrRegistroAtual < qtdeTotalResultados) {
			trackingRelatorioVO.getPaginacao().setHasNext(1);
		} else {
			trackingRelatorioVO.getPaginacao().setHasNext(0);
		}

		return trackingRelatorioVO;

	}

	private TrackingRelatorioConsolidadoVO buildTrackingRelatorioConsolidadoVO(TrackingRelatorioConsolidado[] dbResult) {

		TrackingRelatorioConsolidadoVO trackingRelatorioConsolidado = TrackingRelatorioConsolidadoVO.Factory.newInstance();
		String dsStatus = ConstantesCRM.SVAZIO;

		int totalVolume1 = 0;
		int totalVolume2 = 0;
		int totalVolume3 = 0;
		int totalVolume4 = 0;
		int totalVolume5 = 0;
		int totalVolume6 = 0;
		int totalVolume7 = 0;
		int totalVolume8 = 0;
		int volumeTotal = 0;

		int totalVolume1Consolidado = 0;
		int totalVolume2Consolidado = 0;
		int totalVolume3Consolidado = 0;
		int totalVolume4Consolidado = 0;
		int totalVolume5Consolidado = 0;
		int totalVolume6Consolidado = 0;
		int totalVolume7Consolidado = 0;
		int totalVolume8Consolidado = 0;

		int volumeTotalGeral = 0;

		// Variáveis de porcentagem
		double pctVolume1 = 0;
		double pctVolume2 = 0;
		double pctVolume3 = 0;
		double pctVolume4 = 0;
		double pctVolume5 = 0;
		double pctVolume6 = 0;
		double pctVolume7 = 0;
		double pctVolume8 = 0;

		for (int i = 0; i < dbResult.length; i++) {
			totalVolume1 = 0;
			totalVolume2 = 0;
			totalVolume3 = 0;
			totalVolume4 = 0;
			totalVolume5 = 0;
			totalVolume6 = 0;
			totalVolume7 = 0;
			totalVolume8 = 0;

			trackingRelatorioConsolidado.addNewItemRelatorio();

			trackingRelatorioConsolidado.getItemRelatorioArray(i).setNrStatusPedido(String.valueOf(dbResult[i].getStatus()));
			trackingRelatorioConsolidado.getItemRelatorioArray(i).setDsStatusPedido(dbResult[i].getDsStatus());
			trackingRelatorioConsolidado.getItemRelatorioArray(i).setDsAgingStatus((dbResult[i].getAgingstatus() != 0) ? dbResult[i].getAgingstatus() + " dias" : ConstantesCRM.SZERO);
			trackingRelatorioConsolidado.getItemRelatorioArray(i).setDsAgingPedido(dbResult[i].getAgingpedido() + " dias");
			if (dbResult[i].getStatus() != 7)
				trackingRelatorioConsolidado.getItemRelatorioArray(i).addNewTrackingVolumeVO().setVlVolume(dbResult[i].getVolume1().toBigInteger());
			else
				trackingRelatorioConsolidado.getItemRelatorioArray(i).addNewTrackingVolumeVO().setVlVolume(new BigInteger(ConstantesCRM.SZERO));
			trackingRelatorioConsolidado.getItemRelatorioArray(i).addNewTrackingVolumeVO().setVlVolume(dbResult[i].getVolume2().toBigInteger());
			trackingRelatorioConsolidado.getItemRelatorioArray(i).addNewTrackingVolumeVO().setVlVolume(dbResult[i].getVolume3().toBigInteger());
			trackingRelatorioConsolidado.getItemRelatorioArray(i).addNewTrackingVolumeVO().setVlVolume(dbResult[i].getVolume4().toBigInteger());
			trackingRelatorioConsolidado.getItemRelatorioArray(i).addNewTrackingVolumeVO().setVlVolume(dbResult[i].getVolume5().toBigInteger());
			trackingRelatorioConsolidado.getItemRelatorioArray(i).addNewTrackingVolumeVO().setVlVolume(dbResult[i].getVolume6().toBigInteger());
			trackingRelatorioConsolidado.getItemRelatorioArray(i).addNewTrackingVolumeVO().setVlVolume(dbResult[i].getVolume7().toBigInteger());
			trackingRelatorioConsolidado.getItemRelatorioArray(i).addNewTrackingVolumeVO().setVlVolume(dbResult[i].getVolume8().toBigInteger());

			if (dbResult[i].getStatus() != 7)
				totalVolume1 += dbResult[i].getVolume1().intValue();
			totalVolume2 += dbResult[i].getVolume2().intValue();
			totalVolume3 += dbResult[i].getVolume3().intValue();
			totalVolume4 += dbResult[i].getVolume4().intValue();
			totalVolume5 += dbResult[i].getVolume5().intValue();
			totalVolume6 += dbResult[i].getVolume6().intValue();
			totalVolume7 += dbResult[i].getVolume7().intValue();
			totalVolume8 += dbResult[i].getVolume8().intValue();

			volumeTotal = totalVolume1 + totalVolume2 + totalVolume3 + totalVolume4 + totalVolume5 + totalVolume6 + totalVolume7 + totalVolume8;
			if (dbResult[i].getStatus() == 7)
				volumeTotal += dbResult[i].getVolume1().intValue();

			volumeTotalGeral += volumeTotal;

			trackingRelatorioConsolidado.getItemRelatorioArray(i).setVlVolumeTotal(new BigInteger(String.valueOf(volumeTotal)));

			if (dbResult[i].getStatus() != 7)
				totalVolume1Consolidado += totalVolume1;
			totalVolume2Consolidado += totalVolume2;
			totalVolume3Consolidado += totalVolume3;
			totalVolume4Consolidado += totalVolume4;
			totalVolume5Consolidado += totalVolume5;
			totalVolume6Consolidado += totalVolume6;
			totalVolume7Consolidado += totalVolume7;
			totalVolume8Consolidado += totalVolume8;
		}

		// Valores totais de cada tipo de volume
		trackingRelatorioConsolidado.setVlVolumeTotalGeral(new BigInteger(String.valueOf(volumeTotalGeral)));
		trackingRelatorioConsolidado.addNewResultados();
		trackingRelatorioConsolidado.getResultadosArray(0).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(totalVolume1Consolidado)));
		trackingRelatorioConsolidado.getResultadosArray(0).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(totalVolume2Consolidado)));
		trackingRelatorioConsolidado.getResultadosArray(0).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(totalVolume3Consolidado)));
		trackingRelatorioConsolidado.getResultadosArray(0).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(totalVolume4Consolidado)));
		trackingRelatorioConsolidado.getResultadosArray(0).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(totalVolume5Consolidado)));
		trackingRelatorioConsolidado.getResultadosArray(0).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(totalVolume6Consolidado)));
		trackingRelatorioConsolidado.getResultadosArray(0).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(totalVolume7Consolidado)));
		trackingRelatorioConsolidado.getResultadosArray(0).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(totalVolume8Consolidado)));

		// Cálculo de porcentagens de cada pedido
		for (int i = 0; i < trackingRelatorioConsolidado.getItemRelatorioArray().length; i++) {
			double pct = ((double) trackingRelatorioConsolidado.getItemRelatorioArray(i).getVlVolumeTotal().intValue() / (double) volumeTotalGeral) * 100;
			trackingRelatorioConsolidado.getItemRelatorioArray(i).setVlPorcentagem(new BigInteger(String.valueOf(Math.round(pct))));
		}

		// Cálculo de porcentagens de cada volume
		pctVolume1 = ((double) totalVolume1Consolidado / volumeTotalGeral) * 100;
		pctVolume2 = ((double) totalVolume2Consolidado / volumeTotalGeral) * 100;
		pctVolume3 = ((double) totalVolume3Consolidado / volumeTotalGeral) * 100;
		pctVolume4 = ((double) totalVolume4Consolidado / volumeTotalGeral) * 100;
		pctVolume5 = ((double) totalVolume5Consolidado / volumeTotalGeral) * 100;
		pctVolume6 = ((double) totalVolume6Consolidado / volumeTotalGeral) * 100;
		pctVolume7 = ((double) totalVolume7Consolidado / volumeTotalGeral) * 100;
		pctVolume8 = ((double) totalVolume8Consolidado / volumeTotalGeral) * 100;

		// Envio de valores de porcentagens de cada volume para VO de retorno
		trackingRelatorioConsolidado.addNewResultados();
		trackingRelatorioConsolidado.getResultadosArray(1).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(Math.round(pctVolume1))));
		trackingRelatorioConsolidado.getResultadosArray(1).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(Math.round(pctVolume2))));
		trackingRelatorioConsolidado.getResultadosArray(1).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(Math.round(pctVolume3))));
		trackingRelatorioConsolidado.getResultadosArray(1).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(Math.round(pctVolume4))));
		trackingRelatorioConsolidado.getResultadosArray(1).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(Math.round(pctVolume5))));
		trackingRelatorioConsolidado.getResultadosArray(1).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(Math.round(pctVolume6))));
		trackingRelatorioConsolidado.getResultadosArray(1).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(Math.round(pctVolume7))));
		trackingRelatorioConsolidado.getResultadosArray(1).addNewTrackingVolumeVO().setVlVolume(new BigInteger(String.valueOf(Math.round(pctVolume8))));

		return trackingRelatorioConsolidado;

	}

	private TrackingRelatorioDetalhadoVO buildTrackingRelatorioDetalhadoVO(TrackingRelatorioDetalhado[] dbResult) {

		TrackingRelatorioDetalhadoVO trackingRelatorioDetalhado = TrackingRelatorioDetalhadoVO.Factory.newInstance();
		int contador = 0;
		long nrPedidoAtual = 0;
		long nrOVAtual = 0;

		for (int i = 0; i < dbResult.length; i++) {
			if (dbResult[i] != null) {
				contador = trackingRelatorioDetalhado.getItemRelatorioArray().length;
				if (dbResult[i].getNrPedido() != nrPedidoAtual || (dbResult[i].getNrPedido() == nrPedidoAtual && nrOVAtual != dbResult[i].getNrOrdemVenda() && dbResult[i].getNrOrdemVenda() != 0)) {
					trackingRelatorioDetalhado.addNewItemRelatorio();
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setDtPedido(dbResult[i].getDtPedido());
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setNrPedido((dbResult[i].getNrPedido() == 0) ? ConstantesCRM.SVAZIO : String.valueOf(dbResult[i].getNrPedido()));
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setNrOV((dbResult[i].getNrOrdemVenda() == 0) ? ConstantesCRM.SVAZIO : String.valueOf(dbResult[i].getNrOrdemVenda()));
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setDtOV(dbResult[i].getDtOrdemVenda());
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setDsStatus(dbResult[i].getDsStatus());
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setNmLoginResponsavel(dbResult[i].getNmResponsavel());
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setDsMotivoBloqueio(dbResult[i].getMotivoBloqueio());
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setDtMotivoBloqueio(dbResult[i].getDtMotivoBloqueio());
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setSgUF(dbResult[i].getSgUF());
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setNmCanal(dbResult[i].getIdCanalVenda() + " - " + dbResult[i].getDsCanalVenda());
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setQtAparelhos(String.valueOf(dbResult[i].getQtdAparelho()));
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setQtFornecimento((dbResult[i].getNrFornecimento() == 0) ? ConstantesCRM.SVAZIO : String.valueOf(dbResult[i].getNrFornecimento()));
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setDtFornecimento(dbResult[i].getDtFornecimento());
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setNrPicking((dbResult[i].getNrPicking() == 0) ? ConstantesCRM.SVAZIO : String.valueOf(dbResult[i].getNrPicking()));
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setDtPicking(dbResult[i].getDtPicking());
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setNrNF((dbResult[i].getNrNotaFiscal() == 0) ? ConstantesCRM.SVAZIO : String.valueOf(dbResult[i].getNrNotaFiscal()));
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setDtNF(dbResult[i].getDtNotaFiscal());
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setInTransporte((dbResult[i].getInTransporte() == 1) ? ConstantesCRM.SYES : "Não");
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setDtTransporte(dbResult[i].getDtTransporte());
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setQtTempoStatus((dbResult[i].getTempoStatus() == 0) ? ConstantesCRM.SVAZIO : String.valueOf(dbResult[i].getTempoStatus()));
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setQtTempoDecorrido((dbResult[i].getTempoDecorrido() == 0) ? ConstantesCRM.SVAZIO : String.valueOf(dbResult[i].getTempoDecorrido()));
					trackingRelatorioDetalhado.getItemRelatorioArray(contador).setNmLoginCriacao(dbResult[i].getNmResponsavelOV());
				} else {
					trackingRelatorioDetalhado.getItemRelatorioArray(contador - 1).setDsStatus(trackingRelatorioDetalhado.getItemRelatorioArray(contador - 1).getDsStatus() + "/n" + dbResult[i].getDsStatus());
					trackingRelatorioDetalhado.getItemRelatorioArray(contador - 1).setNmLoginResponsavel(trackingRelatorioDetalhado.getItemRelatorioArray(contador - 1).getNmLoginResponsavel() + "/n" + dbResult[i].getNmResponsavel());
					if (ConstantesCRM.SVAZIO.equals(trackingRelatorioDetalhado.getItemRelatorioArray(contador - 1).getNrNF()) && dbResult[i].getNrNotaFiscal() != 0) {
						trackingRelatorioDetalhado.getItemRelatorioArray(contador - 1).setNrNF(String.valueOf(dbResult[i].getNrNotaFiscal()));
					}
					if (ConstantesCRM.SVAZIO.equals(trackingRelatorioDetalhado.getItemRelatorioArray(contador - 1).getDtNF()) && !ConstantesCRM.SVAZIO.equals(dbResult[i].getDtNotaFiscal())) {
						trackingRelatorioDetalhado.getItemRelatorioArray(contador - 1).setDtNF(dbResult[i].getDtNotaFiscal());
					}
				}
				nrPedidoAtual = dbResult[i].getNrPedido();
				nrOVAtual = dbResult[i].getNrOrdemVenda();
			}
		}

		return trackingRelatorioDetalhado;

	}

	private String getCommonWhereClausule(TrackingRelatoriosFiltrosVO filtro) {

		StringBuffer query = new StringBuffer();
		String idCanalVendaColumn = ("RSPA".equals(filtro.getSgTipoRelatorio())) ? "IDCANALVENDA" : "IDCANAL";

		if (filtro.getRegionais().getSelecionados().getIDValorVOArray().length > 1) {
			query.append(" sguf IN( ");
			for (int i = 0; i < filtro.getRegionais().getSelecionados().getIDValorVOArray().length; i++) {
				query.append("'").append(filtro.getRegionais().getSelecionados().getIDValorVOArray(i).getValor()).append("'");
				if (i < (filtro.getRegionais().getSelecionados().getIDValorVOArray().length - 1)) {
					query.append(",");
				}
			}
			query.append(")");
		} else {
			query.append(" sgUF = '").append(filtro.getRegionais().getSelecionados().getIDValorVOArray(0).getValor()).append("'");
		}

		query.append(" AND ");

		if (filtro.getDsSegmentoArray().length > 1) {
			query.append(" dssegmento IN( ");
			for (int i = 0; i < filtro.getDsSegmentoArray().length; i++) {
				query.append("'").append(filtro.getDsSegmentoArray(i)).append("'");
				if (i < (filtro.getDsSegmentoArray().length - 1)) {
					query.append(",");
				}
			}
			query.append(")");
		} else {
			query.append(" dssegmento = '").append(filtro.getDsSegmentoArray(0)).append("'");
		}

		query.append(" AND ");

		if (filtro.getCanaisVenda().getSelecionados().getIDValorVOArray().length > 1) {
			query.append(idCanalVendaColumn).append(" IN( ");
			for (int i = 0; i < filtro.getCanaisVenda().getSelecionados().getIDValorVOArray().length; i++) {
				query.append("'").append(filtro.getCanaisVenda().getSelecionados().getIDValorVOArray(i).getId()).append("'");
				if (i < (filtro.getCanaisVenda().getSelecionados().getIDValorVOArray().length - 1)) {
					query.append(",");
				}
			}
			query.append(")");
		} else {
			query.append(idCanalVendaColumn).append(" = '").append(filtro.getCanaisVenda().getSelecionados().getIDValorVOArray(0).getId()).append("'");
		}

		query.append(" AND dtrelatorio BETWEEN TO_DATE ('").append(filtro.getDtPeriodoDe()).append(" 00:00:00', 'DD/MM/YYYY HH24:MI:SS') AND TO_DATE ('").append(filtro.getDtPeriodoDe()).append(" 23:59:59', 'DD/MM/YYYY HH24:MI:SS') ");

		return query.toString();

	}

}