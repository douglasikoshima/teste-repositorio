package br.com.indrasistemas.vivoservices.tracking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.Normalizer.Form;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.Normalizer;
import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.vivoservices.tracking.exception.DataInferiorMinimoException;
import br.com.indrasistemas.vivoservices.tracking.exception.DataInvertidaException;
import br.com.indrasistemas.vivoservices.tracking.exception.DocumentoNaoInformadoException;
import br.com.indrasistemas.vivoservices.tracking.exception.NotaFiscalNaoInformadaException;
import br.com.indrasistemas.vivoservices.tracking.exception.PedidoNaoInformadoException;
import br.com.indrasistemas.vivoservices.tracking.facade.ejb.TrackingAparelhosFacadeSession;
import br.com.indrasistemas.vivoservices.tracking.to.DetalhePedidoTO;
import br.com.indrasistemas.vivoservices.tracking.to.EtapaPedidoTO;
import br.com.indrasistemas.vivoservices.tracking.to.OrdemVendaTO;
import br.com.indrasistemas.vivoservices.tracking.to.PedidoTO;
import br.com.indrasistemas.vivoservices.tracking.to.ProdutosNotaFiscalTO;

public class TrackingAparelhosDAO extends HibernateBaseDAO {

	private static final Log logger = LogFactory
			.getLog(TrackingAparelhosFacadeSession.class);

	private Map refStatusEntrega;

	public TrackingAparelhosDAO() {
		refStatusEntrega = new HashMap();

        refStatusEntrega.put("ENTREGACOMINDENIZACAOEFETUADA","ENTREGA REALIZADA");
        refStatusEntrega.put("ENTREGAREALIZADANORMALMENTE","ENTREGA REALIZADA");
        refStatusEntrega.put("INSUCESSO","ENTREGA NÃO REALIZADA");
        refStatusEntrega.put("NAOENTREGUE","ENTREGA NÃO REALIZADA");
        refStatusEntrega.put("PEDIDOCANCELADO","ENTREGA NÃO REALIZADA");
        refStatusEntrega.put("PEDIDOENTREGUE","ENTREGA REALIZADA");
        refStatusEntrega.put("PEDIDONAOENTREGUE","ENTREGA NÃO REALIZADA");
        refStatusEntrega.put("PEDIDONAOENTREGUEDEV","ENTREGA NÃO REALIZADA");
        refStatusEntrega.put("RECUSARETORNADAAODEPOSITO","ENTREGA NÃO REALIZADA");
        refStatusEntrega.put("RECUSADOPELOCLIENTE","ENTREGA NÃO REALIZADA");
        refStatusEntrega.put("SUCESSO","ENTREGA REALIZADA");
	}

	public String removerAcentos(String text) {
	    //return Normalizer.decompose(text, false, 0).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	    return Normalizer.normalize(text, Form.NFC).replaceAll("\\p{InCombiningDiacriticalMarks}+", "");
	}
	
	public List buscarListaPedidos(PedidoTO filtro, Integer first, Integer total)
			throws DAOException, DocumentoNaoInformadoException,
			DataInvertidaException, DataInferiorMinimoException {
		
		if (logger.isDebugEnabled()) {
			logger.debug("buscarListaPedidos - start");
		}

		// -----------------------------------------------------------------------------------------------
		// Não foi possivel aplicar a versão ANSI via HQL abaixo, pois apesar da
		// mesma funcionar corretamente o tempo de execução é muitas vezes maior
		// e por
		// isso inviabilizou a implantação. Estou deixando a versão ANSI
		// documentada
		// em anexo para que se no futuro for possível encontrar um meio de
		// fazê-la
		// executar em tempo razoável podermos implantá-la.
		// -----------------------------------------------------------------------------------------------
		// SELECT DISTINCT
		// NVL(SISTEMAORIGEMPEDIDO.NMSISTEMAORIGEM,NVL(TEMP.NMSISTEMAORIGEM,NVL(ORDEMVENDA2.NMRESPONSAVELORDEMVENDA,SISTEMAORIGEMETP.NMSISTEMAORIGEM)))
		// AS NMSISTEMAORIGEM,
		// UPPER(NVL(PEDIDO2.SGUF,TEMP.SGUF)) AS SGUF,
		// TO_CHAR(NVL(PEDIDO2.DTABERTURAPEDIDO,TEMP.DTABERTURAPEDIDO),'DD/MM/YYYY')
		// AS DTABERTURAPEDIDO,
		// TEMP.IDPEDIDO,
		// TEMP.NRORDEMVENDA,
		// TEMP.NRFORNECIMENTO,
		// TEMP.NRPICKING,
		// TEMP.NRNOTAFISCAL,
		// TEMP.SERIENOTAFISCAL,
		// TEMP.STATUSENTREGA,
		// NVL(PEDIDO2.DTABERTURAPEDIDO,TEMP.DTABERTURAPEDIDO) AS
		// DTABERTURAPEDIDO2
		// FROM
		// (
		// SELECT /*+ INDEX(ORDEMNOTAFISCAL ORDEMNOTAFISCALPK) */
		// ORDEMVENDA.NMRESPONSAVELORDEMVENDA AS NMSISTEMAORIGEM,
		// NVL(PEDIDO.SGUF,NVL(ORDEMVENDA.UFORDEM,NVL(ORDEMVENDAPRODENTREGA.UFORDEM,NVL(ORDEMVENDANOTAFISCAL.UFORDEM,PRODUTOENTREGA.UFNOTAFISCAL))))
		// AS SGUF,
		// NVL(PEDIDO.DTABERTURAPEDIDO,NVL(ORDEMVENDA.DTABERTURAPEDIDO,NVL(ORDEMVENDAPRODENTREGA.DTABERTURAPEDIDO,ORDEMVENDANOTAFISCAL.DTABERTURAPEDIDO)))
		// AS DTABERTURAPEDIDO,
		// NVL(PEDIDO.IDPEDIDO,NVL(ORDEMVENDA.NRPEDIDO,NVL(ORDEMVENDAPRODENTREGA.NRPEDIDO,ORDEMVENDANOTAFISCAL.NRPEDIDO)))
		// AS IDPEDIDO,
		// NVL(ORDEMVENDA.NRORDEMVENDA,NVL(PRODUTOENTREGA.NRORDEMVENDA,ORDEMVENDANOTAFISCAL.NRORDEMVENDA))
		// AS NRORDEMVENDA,
		// NVL(ETAPAPEDIDO.NRFORNECIMENTO,NVL(ORDEMVENDA.NRFORNECIMENTO,NVL(ORDEMVENDAPRODENTREGA.NRFORNECIMENTO,ORDEMVENDANOTAFISCAL.NRFORNECIMENTO)))
		// AS NRFORNECIMENTO,
		// NVL(ETAPAPEDIDO.NRPICKING,NVL(ORDEMVENDA.NRPICKING,NVL(ORDEMVENDAPRODENTREGA.NRPICKING,ORDEMVENDANOTAFISCAL.NRPICKING)))
		// AS NRPICKING,
		// NVL(ETAPAPEDIDO.NRNOTAFISCAL,PRODUTOENTREGA.NRNOTAFISCAL) AS
		// NRNOTAFISCAL,
		// NVL(ETAPAPEDIDO.SERIENOTAFISCAL,ORDEMNOTAFISCAL.SERIENOTAFISCAL) AS
		// SERIENOTAFISCAL,
		// PRODUTOENTREGA.STATUSENTREGA,
		// ETAPAPEDIDO.IDSISTEMAORIGEMPEDIDO,
		// ETAPAPEDIDO.CDCPFCNPJ
		// FROM
		// RETENCAO.ETAPAPEDIDO ETAPAPEDIDO
		// LEFT OUTER JOIN RETENCAO.PEDIDO PEDIDO
		// ON (ETAPAPEDIDO.CDCPFCNPJ = PEDIDO.CDCPFCNPJ
		// AND ETAPAPEDIDO.IDPEDIDO = PEDIDO.IDPEDIDO
		// AND ETAPAPEDIDO.IDSISTEMAORIGEMPEDIDO = PEDIDO.IDSISTEMAORIGEM)
		// LEFT OUTER JOIN RETENCAO.ORDEMVENDA ORDEMVENDA
		// ON (ETAPAPEDIDO.IDORDEMVENDA = ORDEMVENDA.IDORDEMVENDA)
		// LEFT OUTER JOIN RETENCAO.PRODUTOENTREGA PRODUTOENTREGA
		// LEFT OUTER JOIN RETENCAO.ORDEMVENDA ORDEMVENDAPRODENTREGA
		// ON (PRODUTOENTREGA.NRORDEMVENDA = ORDEMVENDAPRODENTREGA.NRORDEMVENDA
		// AND PRODUTOENTREGA.CDCPFCNPJ = ORDEMVENDAPRODENTREGA.CDCPFCNPJ)
		// LEFT OUTER JOIN RETENCAO.ORDEMNOTAFISCAL ORDEMNOTAFISCAL
		// LEFT OUTER JOIN RETENCAO.ORDEMVENDA ORDEMVENDANOTAFISCAL
		// ON (ORDEMNOTAFISCAL.IDORDEMVENDA = ORDEMVENDANOTAFISCAL.IDORDEMVENDA
		// AND ORDEMNOTAFISCAL.CDCPFCNPJ = ORDEMVENDANOTAFISCAL.CDCPFCNPJ)
		// ON (PRODUTOENTREGA.NRNOTAFISCAL = ORDEMNOTAFISCAL.IDNOTAFISCAL
		// AND PRODUTOENTREGA.CDCPFCNPJ = ORDEMNOTAFISCAL.CDCPFCNPJ)
		// ON (ETAPAPEDIDO.IDPRODUTOENTREGA = PRODUTOENTREGA.IDPRODUTOENTREGA)
		// WHERE
		// ETAPAPEDIDO.CDCPFCNPJ = '60886413000147' -- :pOraCdCpfCnpj
		// ) TEMP
		// LEFT OUTER JOIN RETENCAO.PEDIDO PEDIDO2
		// LEFT OUTER JOIN APOIO.SISTEMAORIGEM SISTEMAORIGEMPEDIDO
		// ON (PEDIDO2.IDSISTEMAORIGEM = SISTEMAORIGEMPEDIDO.IDSISTEMAORIGEM)
		// ON ( TEMP.IDPEDIDO = PEDIDO2.IDPEDIDO
		// AND TEMP.CDCPFCNPJ = PEDIDO2.CDCPFCNPJ )
		// LEFT OUTER JOIN RETENCAO.ORDEMVENDA ORDEMVENDA2
		// ON (TEMP.NRORDEMVENDA = ORDEMVENDA2.NRORDEMVENDA
		// AND TEMP.CDCPFCNPJ = ORDEMVENDA2.CDCPFCNPJ)
		// LEFT OUTER JOIN APOIO.SISTEMAORIGEM SISTEMAORIGEMETP
		// ON (TEMP.IDSISTEMAORIGEMPEDIDO = SISTEMAORIGEMETP.IDSISTEMAORIGEM)
		// WHERE
		// TEMP.IDPEDIDO IS NOT NULL
		// ORDER BY
		// NVL(PEDIDO2.DTABERTURAPEDIDO,TEMP.DTABERTURAPEDIDO) DESC,
		// TEMP.IDPEDIDO DESC,
		// TEMP.NRORDEMVENDA DESC,
		// TEMP.NRFORNECIMENTO DESC,
		// TEMP.NRPICKING DESC;
		// 
		// -----------------------------------------------------------------------------------------------
		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;
		
		List listaPedidosTO = new ArrayList();

		try {
			if (filtro.getNrDocumento().equals("")) {
				throw new DocumentoNaoInformadoException(
				"Número do documento não informado.");
			}
			
			if (filtro.getDataInicial() != null && filtro.getDataFinal() != null) {
				if (filtro.getDataInicial().compareTo(filtro.getDataFinal()) > 0) {
					throw new DataInvertidaException(
					"Data final inferior a data inicial");
				}
			}
		
			String sql = new String();
			SimpleDateFormat data = new SimpleDateFormat("yyyyMMdd");
			int start = (first.intValue() * total.intValue()) + 1;
			int end = ((first.intValue() + 1) * total.intValue());
			Integer startI = new Integer(start);
			Integer endI = new Integer(end);

			ResultSet rs;

			// Se não informou uma das datas de pesquisa assume período de 60 dias
			if (filtro.getDataInicial() == null
					|| filtro.getDataFinal() == null) {
				sql = "SELECT SYSDATE-60,SYSDATE FROM DUAL";
				ps = conn.prepareStatement(sql);
				
				// log
				if (logger.isDebugEnabled()) {
					logger.debug("vai executar query="+sql.toString());
				}
				
				rs = ps.executeQuery();
				if (rs.next()) {
					filtro.setDataInicial(rs.getDate(1));
					filtro.setDataFinal(rs.getDate(2));
				}
			}
			else
			{
				sql = "SELECT SYSDATE-180 FROM DUAL";
				ps = conn.prepareStatement(sql);
				
				// log
				if (logger.isDebugEnabled()) {
					logger.debug("vai executar query="+sql.toString());
				}
				
				rs = ps.executeQuery();
	
				if (rs.next()) {
					Date menorData = new Date();
					menorData = rs.getDate(1);
					// Menor data não pode ser inferior a 180 dias de acordo com a
					// documentação
					if (filtro.getDataInicial().compareTo(menorData) < 0) {
						throw new DataInferiorMinimoException(
						"Data inicial inferior a 180 dias");
					}
				}
			}
			
			String dataInicial = data.format(filtro.getDataInicial())
					.toString();
			String dataFinal = data.format(filtro.getDataFinal()).toString();

			// log
			if (logger.isDebugEnabled()) {
				logger.debug("dataInicial="+dataInicial.toString());
				logger.debug("  dataFinal="+dataFinal.toString());
			}

			// =========================================================================
			// Nome da pessoa
			sql = "SELECT DISTINCT NMNOMERAZAOSOCIAL "
				+   "FROM RETENCAO.PEDIDO "
				+  "WHERE CDCPFCNPJ = " + filtro.getNrDocumento().toString()
				+  " AND ROWNUM < 2";

			ps = conn.prepareStatement(sql);

			// log
			if (logger.isDebugEnabled()) {
				logger.debug("vai executar query="+sql.toString());
			}

			rs = ps.executeQuery();

			if (rs.next()) {
				filtro.setNmPessoa(rs.getString(1));
			} else {
				sql = 
					"SELECT "
					+     "PESSOA.NMPESSOA "
					+ "FROM " 
					+     "CUSTOMER.PESSOA PESSOA, "
					+	  "CUSTOMER.DOCUMENTO DOCUMENTO, "
					+     "CUSTOMER.PESSOADOCUMENTO PESSOADOCUMENTO "
					+ "WHERE "
					+ 	  "PESSOA.IDPESSOA = PESSOADOCUMENTO.IDPESSOA "
					+ "AND PESSOADOCUMENTO.IDDOCUMENTO = DOCUMENTO.IDDOCUMENTO "
					+ "AND NVL(PESSOADOCUMENTO.DTEXPIRACAO,SYSDATE+1) > SYSDATE "
					+ "AND DOCUMENTO.NRDOCUMENTO = '" + filtro.getNrDocumento().toString() + "'";
				
				ps = conn.prepareStatement(sql);
				
				// log
				if (logger.isDebugEnabled()) {
					logger.debug("vai executar query="+sql.toString());
				}
				
				rs = ps.executeQuery();
				
				if (rs.next()) {
					filtro.setNmPessoa(rs.getString(1));
				} else {
					filtro.setNmPessoa("*** NOME NÃO ENCONTRADO ***");
				}
			}

			// log
			if (logger.isDebugEnabled()) {
				logger.debug("mPessoa="+filtro.getNmPessoa());
			}
			
			// =========================================================================
			// Query de busca da lista de pedidos
			sql = "SELECT "
					+ "DECODE(SGUF,'SI','SP',SGUF) AS SGUF, "
					+ "DTABERTURAPEDIDO, "
					+ "IDPEDIDO, "
					+ "NRNOTAFISCAL, "
					+ "SERIENOTAFISCAL, "
					// + "UPPER(REPLACE(STATUSENTREGA,' ','')) AS STATUSENTREGA, "
					// + "NRORDEMVENDA, "
					+ "NMSISTEMAORIGEM "
					+ "FROM "
					+ "( "
					+ "SELECT "
					+ "ROWNUM AS NLIN, "
					+ "TEMP3.SGUF, "
					+ "TEMP3.DTABERTURAPEDIDO, "
					+ "TEMP3.IDPEDIDO, "
					+ "TEMP3.NRNOTAFISCAL, "
					+ "TEMP3.SERIENOTAFISCAL, "
					// + "TEMP3.STATUSENTREGA, "
					// + "TEMP3.NRORDEMVENDA, "
					+ "TEMP3.NMSISTEMAORIGEM "
					+ "FROM "
					+ "( "
					+ "SELECT "
					+ "TEMP2.SGUF, "
					+ "TEMP2.DTABERTURAPEDIDO, "
					+ "TEMP2.IDPEDIDO, "
					+ "TEMP2.NRNOTAFISCAL, "
					+ "TEMP2.SERIENOTAFISCAL, "
					// + "TEMP2.STATUSENTREGA, "
					// + "TEMP2.NRORDEMVENDA, "
					+ "TEMP2.NMSISTEMAORIGEM "
					+ "FROM "
					+ "( "
					+ "SELECT  /*+ NO_MERGE(TEMP) INDEX(ORDEMVENDA2 ORDEMVENDAIE1) */ DISTINCT "
					+ "NVL(SISTEMAORIGEMPEDIDO.NMSISTEMAORIGEM,NVL(TEMP.NMSISTEMAORIGEM,NVL(ORDEMVENDA2.NMRESPONSAVELORDEMVENDA,SISTEMAORIGEMETP.NMSISTEMAORIGEM))) AS NMSISTEMAORIGEM, "
					+ "UPPER(NVL(PEDIDO2.SGUF,TEMP.SGUF)) AS SGUF, "
					+ "NVL(PEDIDO2.DTABERTURAPEDIDO,TEMP.DTABERTURAPEDIDO) AS DTABERTURAPEDIDO, "
					+ "TEMP.IDPEDIDO, "
					+ "TEMP.NRNOTAFISCAL, "
					+ "TEMP.SERIENOTAFISCAL "
					// + "TEMP.STATUSENTREGA "
					// + "TEMP.NRORDEMVENDA "
					+ "FROM  "
					+ "( "
					+ "SELECT  /*+ INDEX(ORDEMNOTAFISCAL ORDEMNOTAFISCALIE1) INDEX(ORDEMVENDAPRODENTREGA ORDEMVENDAIE1) */ "
					+ "ORDEMVENDA.NMRESPONSAVELORDEMVENDA AS NMSISTEMAORIGEM, "
					+ "NVL(PEDIDO.SGUF,NVL(ORDEMVENDA.UFORDEM,NVL(ORDEMVENDAPRODENTREGA.UFORDEM,NVL(ORDEMVENDANOTAFISCAL.UFORDEM,PRODUTOENTREGA.UFNOTAFISCAL)))) AS SGUF, "
					+ "NVL(PEDIDO.DTABERTURAPEDIDO,NVL(ORDEMVENDA.DTABERTURAPEDIDO,NVL(ORDEMVENDAPRODENTREGA.DTABERTURAPEDIDO,ORDEMVENDANOTAFISCAL.DTABERTURAPEDIDO))) AS DTABERTURAPEDIDO, "
					+ "NVL(PEDIDO.IDPEDIDO,NVL(ORDEMVENDA.NRPEDIDO,NVL(ORDEMVENDAPRODENTREGA.NRPEDIDO,ORDEMVENDANOTAFISCAL.NRPEDIDO))) AS IDPEDIDO, "
					+ "NVL(ORDEMVENDA.NRORDEMVENDA,NVL(PRODUTOENTREGA.NRORDEMVENDA,ORDEMVENDANOTAFISCAL.NRORDEMVENDA)) AS NRORDEMVENDA, "
					+ "NVL(ETAPAPEDIDO.NRFORNECIMENTO,NVL(ORDEMVENDA.NRFORNECIMENTO,NVL(ORDEMVENDAPRODENTREGA.NRFORNECIMENTO,ORDEMVENDANOTAFISCAL.NRFORNECIMENTO))) AS NRFORNECIMENTO, "
					+ "NVL(ETAPAPEDIDO.NRPICKING,NVL(ORDEMVENDA.NRPICKING,NVL(ORDEMVENDAPRODENTREGA.NRPICKING,ORDEMVENDANOTAFISCAL.NRPICKING))) AS NRPICKING, "
					+ "NVL(ETAPAPEDIDO.NRNOTAFISCAL,PRODUTOENTREGA.NRNOTAFISCAL) AS NRNOTAFISCAL, "
					+ "NVL(ETAPAPEDIDO.SERIENOTAFISCAL,ORDEMNOTAFISCAL.SERIENOTAFISCAL) AS SERIENOTAFISCAL, "
					//+ "DECODE(ETAPAPEDIDO.IDPRODUTOENTREGA,NULL,NULL,ETAPAPEDIDO.DSETAPA) AS STATUSENTREGA, "
					+ "ETAPAPEDIDO.IDSISTEMAORIGEMPEDIDO, "
					+ "ETAPAPEDIDO.CDCPFCNPJ "
					+ "FROM "
					+ "RETENCAO.ETAPAPEDIDO ETAPAPEDIDO, "
					+ "RETENCAO.PEDIDO PEDIDO, "
					+ "RETENCAO.ORDEMVENDA ORDEMVENDA, "
					+ "RETENCAO.ORDEMVENDA ORDEMVENDAPRODENTREGA, "
					+ "RETENCAO.ORDEMVENDA ORDEMVENDANOTAFISCAL, "
					+ "RETENCAO.ORDEMNOTAFISCAL ORDEMNOTAFISCAL, "
					+ "RETENCAO.PRODUTOENTREGA PRODUTOENTREGA "
					+ "WHERE "
					+ "ETAPAPEDIDO.CDCPFCNPJ = :nrDocumento "
					+ "AND ETAPAPEDIDO.CDCPFCNPJ = PEDIDO.CDCPFCNPJ (+) "
					+ "AND ETAPAPEDIDO.IDPEDIDO = PEDIDO.IDPEDIDO (+) "
					+ "AND ETAPAPEDIDO.IDSISTEMAORIGEMPEDIDO = PEDIDO.IDSISTEMAORIGEM (+) "
					+ "AND ETAPAPEDIDO.IDORDEMVENDA = ORDEMVENDA.IDORDEMVENDA (+) "
					+ "AND ETAPAPEDIDO.IDPRODUTOENTREGA = PRODUTOENTREGA.IDPRODUTOENTREGA (+) "
					+ "AND PRODUTOENTREGA.NRORDEMVENDA = ORDEMVENDAPRODENTREGA.NRORDEMVENDA (+) "
					+ "AND PRODUTOENTREGA.CDCPFCNPJ = ORDEMVENDAPRODENTREGA.CDCPFCNPJ (+) "
					+ "AND PRODUTOENTREGA.NRNOTAFISCAL = ORDEMNOTAFISCAL.IDNOTAFISCAL (+) "
					+ "AND PRODUTOENTREGA.CDCPFCNPJ = ORDEMNOTAFISCAL.CDCPFCNPJ (+) "
					+ "AND ORDEMNOTAFISCAL.IDORDEMVENDA = ORDEMVENDANOTAFISCAL.IDORDEMVENDA (+) "
					+ "AND ORDEMNOTAFISCAL.CDCPFCNPJ = ORDEMVENDANOTAFISCAL.CDCPFCNPJ (+) "
					+ ") TEMP, "
					+ "RETENCAO.PEDIDO PEDIDO2, "
					+ "RETENCAO.ORDEMVENDA ORDEMVENDA2, "
					+ "APOIO.SISTEMAORIGEM SISTEMAORIGEMETP, "
					+ "APOIO.SISTEMAORIGEM SISTEMAORIGEMPEDIDO "
					+ "WHERE "
					+ "TEMP.IDPEDIDO IS NOT NULL "
					+ "AND TEMP.IDPEDIDO = PEDIDO2.IDPEDIDO (+) "
					+ "AND TEMP.CDCPFCNPJ = PEDIDO2.CDCPFCNPJ (+) "
					+ "AND TEMP.NRORDEMVENDA = ORDEMVENDA2.NRORDEMVENDA (+) "
					+ "AND TEMP.CDCPFCNPJ = ORDEMVENDA2.CDCPFCNPJ (+) "
					+ "AND TEMP.IDSISTEMAORIGEMPEDIDO = SISTEMAORIGEMETP.IDSISTEMAORIGEM (+) "
					+ "AND PEDIDO2.IDSISTEMAORIGEM = SISTEMAORIGEMPEDIDO.IDSISTEMAORIGEM (+) "
					+ ") TEMP2 "
					+ "WHERE ";
			
			if ( filtro.getIdPedido() != null && !filtro.getIdPedido().equals(new Long("0")) ) {
				if ( !filtro.getIdPedido().toString().equals("") && !filtro.getIdPedido().toString().equals("0")  ) {
					sql += "TEMP2.IDPEDIDO = " + filtro.getIdPedido().toString() + " AND ";
				}
			}

			sql +=    "TRUNC(TEMP2.DTABERTURAPEDIDO) >= TO_DATE(\'"
					+ dataInicial.toString() + "\',\'YYYYMMDD\') "
					+ "AND TRUNC(TEMP2.DTABERTURAPEDIDO) <= TO_DATE(\'"
					+ dataFinal.toString() + "\',\'YYYYMMDD\') " 
					+ "ORDER BY TEMP2.DTABERTURAPEDIDO DESC, TEMP2.IDPEDIDO DESC "
					+ ") TEMP3 " + ") " + "WHERE NLIN >= " + startI.toString()
					+ " AND NLIN <= " + endI.toString();

			ps = conn.prepareStatement(sql);

			ps.setLong(1, filtro.getNrDocumento().longValue());

			// log
			if (logger.isDebugEnabled()) {
				logger.debug("vai executar query="+sql.toString());
			}
			
			rs = ps.executeQuery();

			while (rs.next()) {
				PedidoTO pedidoTO = new PedidoTO();

				pedidoTO.setSgUF(rs.getString(1));
				pedidoTO.setDtAberturaPedido(rs.getDate(2));
				pedidoTO.setIdPedido(new Long(rs.getLong(3)));
				pedidoTO.setDataInicial(filtro.getDataInicial());
				pedidoTO.setDataFinal(filtro.getDataFinal());

				if (rs.getLong(4) != 0) {
					pedidoTO.setNrNotaFiscal(new Long(rs.getLong(4)));
				}
				pedidoTO.setSerieNotaFiscal(rs.getString(5));

				pedidoTO.setNmSistemaOrigem(rs.getString(6));

				// =========================================================================
				// não vai deixar repetir dados do mesmo pedido na lista de saida
				boolean existe = false;

				for (Iterator j = listaPedidosTO.iterator(); j.hasNext();) {
					
					PedidoTO pedidoTOTemp = (PedidoTO) j.next();

					if ( pedidoTOTemp.getIdPedido().equals(pedidoTO.getIdPedido()) &&
						 (pedidoTO.getNrNotaFiscal() == null ||
						  pedidoTOTemp.getNrNotaFiscal().equals(pedidoTO.getNrNotaFiscal())) && 
						 (pedidoTO.getSerieNotaFiscal() == null ||
						 pedidoTOTemp.getSerieNotaFiscal().equals(pedidoTO.getSerieNotaFiscal()))) {
						
						if (pedidoTO.getSgUF() != null) {
							if (!pedidoTO.getSgUF().equals("")) {
								pedidoTOTemp.setSgUF(pedidoTO.getSgUF());
							}
						}

						if (pedidoTO.getDtAberturaPedido() != null) {
							if (!pedidoTO.getDtAberturaPedido().toString()
									.equals("")) {
								pedidoTOTemp.setDtAberturaPedido(pedidoTO
										.getDtAberturaPedido());
							}
						}

						if (pedidoTO.getNrNotaFiscal() != null) {
							if (!pedidoTO.getNrNotaFiscal().equals(
									new Long("0"))) {
								pedidoTOTemp.setNrNotaFiscal(pedidoTO
										.getNrNotaFiscal());
								pedidoTOTemp.setNrNotaFiscal
										(pedidoTO.getNrNotaFiscal());
							}
						}

						if (pedidoTO.getSerieNotaFiscal() != null) {
							if (!pedidoTO.getSerieNotaFiscal().equals("")) {
								pedidoTOTemp.setSerieNotaFiscal(pedidoTO
										.getSerieNotaFiscal());
							}
						}

						if (pedidoTO.getNmSistemaOrigem() != null) {
							if (!pedidoTO.getNmSistemaOrigem().equals("")) {
								pedidoTOTemp.setNmSistemaOrigem(pedidoTO
										.getNmSistemaOrigem());
							}
						}

						existe = true;
					}
				}

				if (!existe) {
					listaPedidosTO.add(pedidoTO);
				}
			}

			// =========================================================================
			// Analisa a lista para determinar quais as mensagens de etapas
			for (Iterator j = listaPedidosTO.iterator(); j.hasNext();) {

				PedidoTO pedidoTOTemp = (PedidoTO) j.next();
				
				// Todos os itens do TO possuem o nome da pessoa do pedido
				pedidoTOTemp.setNmPessoa(filtro.getNmPessoa());

				// Ordem(s) de venda para o pedido
				sql = "SELECT IDORDEMVENDA,NRORDEMVENDA "
					+ "FROM RETENCAO.ORDEMVENDA " 
					+ "WHERE CDCPFCNPJ = "+ filtro.getNrDocumento() + " " 
					+ "AND NRPEDIDO = "+ pedidoTOTemp.getIdPedido();
				
				ps = conn.prepareStatement(sql);

				// log
				if (logger.isDebugEnabled()) {
					logger.debug("vai executar query="+sql.toString());
				}
				
				rs = ps.executeQuery();

				while (rs.next()) {
					OrdemVendaTO ordemVendaTO = new OrdemVendaTO();

					ordemVendaTO.setIdOrdemVenda(new Long(rs.getLong(1)));
					ordemVendaTO.setNrOrdemVenda(new Long(rs.getLong(2)));

					pedidoTOTemp.addItemListaOrdemVenda(ordemVendaTO);
				}

				// =========================================================================
				// Verifica se o pedido tem ordem de venda
				boolean todasOrdensCanceladas = false;
				
				sql = "SELECT COUNT(1)"
			         + " FROM RETENCAO.ORDEMVENDA ORDEMVENDA"
		            + " WHERE ORDEMVENDA.CDCPFCNPJ = " + filtro.getNrDocumento() 
		              + " AND ORDEMVENDA.NRPEDIDO = " + pedidoTOTemp.getIdPedido().toString()
			          + " AND ROWNUM < 2";

				ps = conn.prepareStatement(sql);

				// log
				if (logger.isDebugEnabled()) {
					logger.debug("vai executar query="+sql.toString());
				}

				rs = ps.executeQuery();

				if ( rs.next() ) {
					if ( rs.getInt(1) > 0 ) {
						
						// Se o pedido tem ordem de venda, então verifica se existe ao menos 
						// uma ordem de venda não-cancelada para o pedido.
						todasOrdensCanceladas = true;
						
						sql = "SELECT COUNT(1)"
							+ " FROM RETENCAO.ORDEMVENDA ORDEMVENDA"
							+ " WHERE ORDEMVENDA.CDCPFCNPJ = " + filtro.getNrDocumento() 
							+ " AND ORDEMVENDA.NRPEDIDO = " + pedidoTOTemp.getIdPedido().toString()
							+ " AND ORDEMVENDA.INCANCELADA = 0"
							+ " AND ROWNUM < 2";
						
						ps = conn.prepareStatement(sql);
						
						// log
						if (logger.isDebugEnabled()) {
							logger.debug("vai executar query="+sql.toString());
						}
						
						rs = ps.executeQuery();
						
						if ( rs.next() ) {
							if ( rs.getInt(1) > 0 ) {
								todasOrdensCanceladas = false;
							}
						}
					}
				}

				// =========================================================================
				// Se o pedido não teve as ordens de vendas canceladas, verifica se houve etapa de nota fiscal
				if ( pedidoTOTemp.getNrNotaFiscal() != null
						&& !pedidoTOTemp.getNrNotaFiscal().equals("")
						&& todasOrdensCanceladas == false ) {

					// =========================================================================
					// Obtém o status de entrega da nota fiscal, se existir...
					sql = "SELECT DISTINCT "
						+ "UPPER(NVL(PRODUTOENTREGA.STATUSENTREGA,'PEDIDO ENCAMINHADO PARA ENTREGA')) AS STATUSENTREGA, "
						+ "TO_CHAR(MAX(ETAPAPEDIDO.DTETAPAPEDIDO),'DD/MM/YYYY HH24:MI:SS') AS DTETAPAPEDIDO "
						+ "FROM "
						+ "RETENCAO.ETAPAPEDIDO ETAPAPEDIDO, "
						+ "RETENCAO.PRODUTOENTREGA PRODUTOENTREGA "
						+ "WHERE ETAPAPEDIDO.CDCPFCNPJ = " + filtro.getNrDocumento().toString() + " "
						+ "AND ETAPAPEDIDO.IDPRODUTOENTREGA = PRODUTOENTREGA.IDPRODUTOENTREGA "
						+ "AND PRODUTOENTREGA.CDCPFCNPJ = ETAPAPEDIDO.CDCPFCNPJ "
						+ "AND PRODUTOENTREGA.NRNOTAFISCAL = " + pedidoTOTemp.getNrNotaFiscal().toString()
						+ " GROUP BY UPPER(NVL(PRODUTOENTREGA.STATUSENTREGA,'PEDIDO ENCAMINHADO PARA ENTREGA')) ";
					
					ps = conn.prepareStatement(sql);
					
					// log
					if (logger.isDebugEnabled()) {
						logger.debug("vai executar query="+sql.toString());
					}
					
					rs = ps.executeQuery();
					
					if ( rs.next() ) {
						pedidoTOTemp.setStatusEntrega(rs.getString("STATUSENTREGA"));
					}
					
					//sql = "SELECT DISTINCT SUBSTR(OBSERVACAOETAPAPEDIDO,33+LENGTH('"+pedidoTOTemp.getNrNotaFiscal()+"-"+pedidoTOTemp.getSerieNotaFiscal().toString()+" '),9) AS OBSERVACAOETAPAPEDIDO "
					sql = "SELECT DISTINCT OBSERVACAOETAPAPEDIDO "
							+ "FROM RETENCAO.ETAPAPEDIDO "
							+ "WHERE CDCPFCNPJ = "
							+ filtro.getNrDocumento()
							+ " AND UPPER(OBSERVACAOETAPAPEDIDO) LIKE \'NOTA FISCAL NUMERO%\'"
							+ " AND NRNOTAFISCAL = " + pedidoTOTemp.getNrNotaFiscal();

					ps = conn.prepareStatement(sql);

					// log
					if (logger.isDebugEnabled()) {
						logger.debug("vai executar query="+sql.toString());
					}

					rs = ps.executeQuery();

					if (rs.next()) {

						String textoNF = "FATURADO EM **DATA INVALIDA** NF " + pedidoTOTemp.getNrNotaFiscal().toString();

						if ( rs.getString(1).length() > 7 ) {
							String observacaoEtapaPedido = rs.getString(1);
							
							for ( int i=19+pedidoTOTemp.getNrNotaFiscal().toString().length()+pedidoTOTemp.getSerieNotaFiscal().toString().length();i<observacaoEtapaPedido.length();i++) {
								if ( observacaoEtapaPedido.substring(i, i+13).equals("REALIZADA EM ") ) {
									
									String dia = observacaoEtapaPedido.substring(i+19, i+21);
									String mes = observacaoEtapaPedido.substring(i+17, i+19);
									String ano = observacaoEtapaPedido.substring(i+13, i+17);
									
									textoNF = "FATURADO EM " + observacaoEtapaPedido.substring(i+19, i+21) + "/"
						               + observacaoEtapaPedido.substring(i+17, i+19) + "/" + observacaoEtapaPedido.substring(i+13, i+17)
						               + " NF " + pedidoTOTemp.getNrNotaFiscal().toString() 
						               + "-" + pedidoTOTemp.getSerieNotaFiscal();
									break;
								}
							}
						}

						pedidoTOTemp.setTextoNotaFiscal(textoNF);
					}
					else {
						pedidoTOTemp.setTextoNotaFiscal(pedidoTOTemp.getNrNotaFiscal().toString());
					}
				}

				// log
				if (logger.isDebugEnabled()) {
					logger.debug("texto de nota fiscal="+pedidoTOTemp.getTextoNotaFiscal().toString());
				}

				// =========================================================================
				// Texto de status
				if ( todasOrdensCanceladas == true ) {
					pedidoTOTemp.setStatus("PEDIDO CANCELADO");
				}
				else if ((pedidoTOTemp.getStatusEntrega() != null && !pedidoTOTemp.getStatusEntrega().equals(""))) {
					// se tem status de entrega traduz o status de acordo com a
					// lista de status simplificada.
					String statusEntrega = removerAcentos(pedidoTOTemp.getStatusEntrega());
					if (refStatusEntrega.containsKey(statusEntrega) ) {
						pedidoTOTemp.setStatus(refStatusEntrega.get(statusEntrega).toString());
					}
					else {
						pedidoTOTemp.setStatus("PEDIDO ENCAMINHADO PARA ENTREGA");
					}
				} else if ((pedidoTOTemp.getNrNotaFiscal() != null && !pedidoTOTemp.getNrNotaFiscal().equals(""))) {
					pedidoTOTemp.setStatus("PEDIDO FATURADO NA NF "+ pedidoTOTemp.getNrNotaFiscal().toString());
				} else if (!pedidoTOTemp.getListaOrdemVenda().isEmpty()) {
					pedidoTOTemp.setStatus("EM PROCESSAMENTO");
				} else if (pedidoTOTemp.getNmSistemaOrigem() != null) {
					if (pedidoTOTemp.getNmSistemaOrigem().equals("SAP")) {
						pedidoTOTemp.setStatus("EM PROCESSAMENTO");
					} else {
						pedidoTOTemp.setStatus("EM ANÁLISE");
					}
				} else {
					pedidoTOTemp.setStatus("EM PROCESSAMENTO");
				}
				
				// log
				if (logger.isDebugEnabled()) {
					logger.debug("texto de status="+pedidoTOTemp.getStatus().toString());
				}
			}
		} catch (DocumentoNaoInformadoException ex) {
			throw new DocumentoNaoInformadoException(ex.getMessage());
		} catch (DataInvertidaException ex) {
			throw new DataInvertidaException(ex.getMessage());
		} catch (DataInferiorMinimoException ex) {
			throw new DataInferiorMinimoException(ex.getMessage());
		} catch (Exception ex) {
			throw new DAOException(ex);
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("buscarListaPedidos - end");
		}

		return listaPedidosTO;
	}

	public List buscarDetalhesPedido(DetalhePedidoTO filtro, Integer first,
			Integer total) throws DAOException, DocumentoNaoInformadoException,
			PedidoNaoInformadoException, NotaFiscalNaoInformadaException {
		if (logger.isDebugEnabled()) {
			logger.debug("buscarDetalhesPedido - start");
		}

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			
			if (filtro.getNrDocumento().equals("")) {
				throw new DocumentoNaoInformadoException(
				"Número do documento não informado.");
			}
			
			if (filtro.getIdPedido().equals("")) {
				throw new PedidoNaoInformadoException(
				"Número do pedido não informado.");
			}

			//if (filtro.getNrNotaFiscal().equals("")) {
			//	throw new NotaFiscalNaoInformadaException(
			//	"Número da nota fiscal não informado.");
			//}
			
			// obtém o valor total da notas fiscais para o pedido
			boolean totalParcelasOkay = false;
			String sql = null;
			ResultSet rs = null;
			
			// log
			if (logger.isDebugEnabled()) {
				logger.debug("documento="+filtro.getNrDocumento().longValue());
				logger.debug(" n.fiscal="+filtro.getNrNotaFiscal().longValue());
				logger.debug(" idPedido="+filtro.getIdPedido().longValue());
			}
			
			if ( filtro.getNrNotaFiscal() != null ) {
				if ( !filtro.getNrNotaFiscal().toString().equals("") ) {
					sql = "SELECT "
						+ "TRIM((TO_CHAR(SUM(ORDEMNOTAFISCAL.VLPARCELA),'9999,999,999,990.00MI'))) AS TOTALPARCELAS "
						+ "FROM  "
						+ "RETENCAO.ORDEMNOTAFISCAL ORDEMNOTAFISCAL, "
						+ "RETENCAO.ORDEMVENDA ORDEMVENDA "
						+ "WHERE "
						+ "ORDEMVENDA.CDCPFCNPJ = :pOraCdCpfCnpj  "
						+ "AND ORDEMVENDA.IDORDEMVENDA = ORDEMNOTAFISCAL.IDORDEMVENDA "
						+ "AND ORDEMVENDA.CDCPFCNPJ = ORDEMNOTAFISCAL.CDCPFCNPJ "
						+ "AND ORDEMNOTAFISCAL.IDNOTAFISCAL = :pOraIdNotaFiscal";
					
					ps = conn.prepareStatement(sql);
					
					ps.setLong(1, filtro.getNrDocumento().longValue());
					ps.setLong(2, filtro.getNrNotaFiscal().longValue());
					
					// log
					if (logger.isDebugEnabled()) {
						logger.debug("vai executar query="+sql.toString());
					}
					
					rs = ps.executeQuery();
					
					if (rs.next()) {
						filtro.setValorTotal(rs.getString("TOTALPARCELAS"));
						totalParcelasOkay = true;
					}
				}
			}
			
			if ( totalParcelasOkay == false ) {
				sql = "SELECT "
					+ "TRIM((TO_CHAR(PEDIDO.VLTOTALPEDIDO,'9999,999,999,990.00MI'))) AS VLTOTALPEDIDO, "
					+ "PEDIDO.ENDERECOENTREGA " + "FROM "
					+ "RETENCAO.PEDIDO PEDIDO " + "WHERE "
					+ "PEDIDO.CDCPFCNPJ = :pOraCdCpfCnpj "
					+ "AND PEDIDO.IDPEDIDO = :pOraIdPedido ";
				
				ps = conn.prepareStatement(sql);
				
				ps.setLong(1, filtro.getNrDocumento().longValue());
				ps.setLong(2, filtro.getIdPedido().longValue());
				
				// log
				if (logger.isDebugEnabled()) {
					logger.debug("vai executar query="+sql.toString());
				}
				
				rs = ps.executeQuery();
				
				if (rs.next()) {
					filtro.setValorTotal(rs.getString("VLTOTALPEDIDO"));
					filtro.setEnderecoEntrega(rs.getString("ENDERECOENTREGA"));
				}
			}
			
			if ( filtro.getValorTotal() == null || filtro.getValorTotal().equals("") ) {
				filtro.setValorTotal("0,00");
			}
			
			// Se ainda não obteve o endereço de entrega então busca
			if ( filtro.getEnderecoEntrega() == null || filtro.getEnderecoEntrega().equals("") ) {
				sql = "SELECT PEDIDO.ENDERECOENTREGA FROM "
					+ "RETENCAO.PEDIDO PEDIDO WHERE "
					+ "PEDIDO.CDCPFCNPJ = :pOraCdCpfCnpj "
					+ "AND PEDIDO.IDPEDIDO = :pOraIdPedido ";
				
				ps = conn.prepareStatement(sql);
				
				ps.setLong(1, filtro.getNrDocumento().longValue());
				ps.setLong(2, filtro.getIdPedido().longValue());
				
				// log
				if (logger.isDebugEnabled()) {
					logger.debug("vai executar query="+sql.toString());
				}
				
				rs = ps.executeQuery();
				
				if (rs.next()) {
					filtro.setEnderecoEntrega(rs.getString("ENDERECOENTREGA"));
				}
			}
			
			if ( filtro.getNrNotaFiscal() != null ) {
				if ( !filtro.getNrNotaFiscal().toString().equals("") ) {
					
					// Itens da nota fiscal
					sql = "SELECT "
							+ "ITEMORDEMVENDA.IDITEMORDEM, "
							+ "ITEMORDEMVENDA.CDPRODUTO, "
							+ "ITEMORDEMVENDA.DSITEMORDEM, "
							+ "ITEMORDEMVENDA.QTITEM, "
							+ "RTRIM(LTRIM(TO_CHAR(ITEMORDEMVENDA.VLITEM,'9999,999,999,990.00MI'))) AS VLITEM "
							+ "FROM "
							+ "RETENCAO.ITEMORDEMVENDA ITEMORDEMVENDA, "
							+ "RETENCAO.ORDEMVENDA ORDEMVENDA, "
							+ "RETENCAO.ORDEMNOTAFISCAL ORDEMNOTAFISCAL "
							+ "WHERE ORDEMVENDA.CDCPFCNPJ = :pOraCdCpfCnpj "
							+ "AND ITEMORDEMVENDA.IDORDEMVENDA = ORDEMVENDA.IDORDEMVENDA "
							+ "AND ORDEMNOTAFISCAL.CDCPFCNPJ = ORDEMVENDA.CDCPFCNPJ "
							+ "AND ORDEMVENDA.IDORDEMVENDA = ORDEMNOTAFISCAL.IDORDEMVENDA "
							+ "AND ORDEMNOTAFISCAL.IDNOTAFISCAL = :pOraIdNotaFiscal "
							+ "ORDER BY ITEMORDEMVENDA.IDITEMORDEM";
		
					ps = conn.prepareStatement(sql);
		
					ps.setLong(1, filtro.getNrDocumento().longValue());
					ps.setLong(2, filtro.getNrNotaFiscal().longValue());
		
					// log
					if (logger.isDebugEnabled()) {
						logger.debug("vai executar query="+sql.toString());
					}
					
					rs = ps.executeQuery();
		
					List listaProdutosNotaFiscalTO = new ArrayList();
		
					while (rs.next()) {
						ProdutosNotaFiscalTO produtosNotaFiscalTO = new ProdutosNotaFiscalTO();
		
						produtosNotaFiscalTO
								.setIdItemOrdem(rs.getString("IDITEMORDEM"));
						produtosNotaFiscalTO.setCdProduto(rs.getString("CDPRODUTO"));
						produtosNotaFiscalTO
								.setDsItemOrdem(rs.getString("DSITEMORDEM"));
						produtosNotaFiscalTO.setQtItem(rs.getString("QTITEM"));
						produtosNotaFiscalTO.setVlItem(rs.getString("VLITEM"));
		
						listaProdutosNotaFiscalTO.add(produtosNotaFiscalTO);
					}
		
					filtro.setListaProdutosNotaFiscal(listaProdutosNotaFiscalTO);
				}
			}

			// Etapas
			List listaEtapaPedidoTO = new ArrayList();

			// Busca a primeira data em que o pedido ficou em análise
			sql = "SELECT DISTINCT"
					+ " TO_CHAR(MIN(ETAPAPEDIDO.DTETAPAPEDIDO),'DD/MM/YYYY HH24:MI:SS') AS DTETAPAPEDIDO"
					+ " FROM"
					+ " RETENCAO.ETAPAPEDIDO ETAPAPEDIDO,"
					+ " APOIO.SISTEMAORIGEM SISTEMAORIGEM"
					+ " WHERE ETAPAPEDIDO.CDCPFCNPJ = "
					+ filtro.getNrDocumento().toString()
					+ " AND ETAPAPEDIDO.IDSISTEMAORIGEMETAPA = SISTEMAORIGEM.IDSISTEMAORIGEM"
					+ " AND SISTEMAORIGEM.SGSISTEMAORIGEM <> 'SAP'"
					+ " AND ETAPAPEDIDO.IDPEDIDO = "
					+ filtro.getIdPedido().toString();

			ps = conn.prepareStatement(sql);

			// log
			if (logger.isDebugEnabled()) {
				logger.debug("vai executar query="+sql.toString());
			}
			
			rs = ps.executeQuery();

			if (rs.next()) {

				if ( rs.getString("DTETAPAPEDIDO") != null ) {
					if ( !rs.getString("DTETAPAPEDIDO").equals("")) {
						EtapaPedidoTO etapaPedidoTO = new EtapaPedidoTO();
						
						etapaPedidoTO.setDtEtapa(rs.getString("DTETAPAPEDIDO"));
						etapaPedidoTO.setDsEtapa("EM ANÁLISE");
						
						listaEtapaPedidoTO.add(etapaPedidoTO);
					}
				}
			}

			// Busca a primeira data em que o pedido ficou 'em processamento'
	        sql = "SELECT"
              +      " TO_CHAR(MIN(ETAPAPEDIDO.DTETAPAPEDIDO),'DD/MM/YYYY HH24:MI:SS') AS DTETAPAPEDIDO"
              + " FROM"
              +      " RETENCAO.ETAPAPEDIDO ETAPAPEDIDO,"
              +      " APOIO.SISTEMAORIGEM SISTEMAORIGEM"
              +" WHERE"
              +     " ETAPAPEDIDO.CDCPFCNPJ = " + filtro.getNrDocumento().toString()
              + " AND ETAPAPEDIDO.IDSISTEMAORIGEMETAPA = SISTEMAORIGEM.IDSISTEMAORIGEM"
              + " AND (" 
              +      " ETAPAPEDIDO.IDPEDIDO IN (SELECT PEDIDO.IDPEDIDO FROM RETENCAO.PEDIDO PEDIDO WHERE PEDIDO.CDCPFCNPJ = "+ filtro.getNrDocumento().toString()+" AND PEDIDO.IDPEDIDO="+ filtro.getIdPedido().toString()+")"
              +  " OR  ETAPAPEDIDO.IDORDEMVENDA IN (SELECT ORDEMVENDA.IDORDEMVENDA FROM RETENCAO.ORDEMVENDA ORDEMVENDA WHERE ORDEMVENDA.CDCPFCNPJ = "+ filtro.getNrDocumento().toString()+" AND ORDEMVENDA.NRPEDIDO="+ filtro.getIdPedido().toString()+")"
              +  " OR  ETAPAPEDIDO.IDPRODUTOENTREGA IN (SELECT PRODUTOENTREGA.IDPRODUTOENTREGA FROM RETENCAO.PRODUTOENTREGA PRODUTOENTREGA WHERE PRODUTOENTREGA.CDCPFCNPJ = "+ filtro.getNrDocumento().toString()+" AND PRODUTOENTREGA.NRNOTAFISCAL = "+ filtro.getNrNotaFiscal().toString()+")"
              +      " ) "
              + " AND ETAPAPEDIDO.DSETAPA = 'Ordem de Venda Gerada'";

			ps = conn.prepareStatement(sql);

			// log
			if (logger.isDebugEnabled()) {
				logger.debug("vai executar query="+sql.toString());
			}
			
			rs = ps.executeQuery();

			if (rs.next()) {
				EtapaPedidoTO etapaPedidoTO = new EtapaPedidoTO();

				if ( rs.getString("DTETAPAPEDIDO") != null ) {
					if ( !rs.getString("DTETAPAPEDIDO").equals("")) {
						etapaPedidoTO.setDtEtapa(rs.getString("DTETAPAPEDIDO"));
						etapaPedidoTO.setDsEtapa("EM PROCESSAMENTO");
		
						listaEtapaPedidoTO.add(etapaPedidoTO);
					}
				}
			}

			if ( filtro.getNrNotaFiscal() != null ) {
				if ( !filtro.getNrNotaFiscal().toString().equals("") ) {
					// Busca datas de geração de notas fiscais para o pedido
					sql = "SELECT DISTINCT "
							+ "TO_CHAR(MAX(ETAPAPEDIDO.DTETAPAPEDIDO),'DD/MM/YYYY HH24:MI:SS') AS DTETAPAPEDIDO, "
							+ "ETAPAPEDIDO.NRNOTAFISCAL "
							+ "FROM "
							+     "RETENCAO.ETAPAPEDIDO ETAPAPEDIDO, "
							+     "APOIO.SISTEMAORIGEM SISTEMAORIGEM "
							+ "WHERE "
							+     "ETAPAPEDIDO.CDCPFCNPJ = " + filtro.getNrDocumento().toString()+ " "
							+ "AND ETAPAPEDIDO.IDSISTEMAORIGEMETAPA = SISTEMAORIGEM.IDSISTEMAORIGEM "
							+ "AND SISTEMAORIGEM.SGSISTEMAORIGEM = 'SAP' "
							+ "AND ETAPAPEDIDO.NRNOTAFISCAL = " + filtro.getNrNotaFiscal().toString() + " "
							+ "AND ETAPAPEDIDO.IDORDEMVENDA IN (SELECT ORDEMVENDA.IDORDEMVENDA "
							+ "FROM "
							+     "RETENCAO.ORDEMVENDA ORDEMVENDA "
							+ "WHERE "
							+     "ORDEMVENDA.CDCPFCNPJ= " + filtro.getNrDocumento().toString() + " "
							+ "AND ORDEMVENDA.NRPEDIDO= " + filtro.getIdPedido().toString() + ")" 
							+ "GROUP BY "
							+     "ETAPAPEDIDO.NRNOTAFISCAL ";
		
					ps = conn.prepareStatement(sql);
		
					// log
					if (logger.isDebugEnabled()) {
						logger.debug("vai executar query="+sql.toString());
					}
					
					rs = ps.executeQuery();
		
					while (rs.next()) {
		
						if ( rs.getString("DTETAPAPEDIDO") != null ) {
							if ( !rs.getString("DTETAPAPEDIDO").equals("")) {
								
								EtapaPedidoTO etapaPedidoTO = new EtapaPedidoTO();
								
								etapaPedidoTO.setDtEtapa(rs.getString("DTETAPAPEDIDO"));
								etapaPedidoTO.setDsEtapa("PEDIDO FATURADO NA NF "
										+ rs.getString("NRNOTAFISCAL"));
				
								listaEtapaPedidoTO.add(etapaPedidoTO);
							}
						}
					}
		
					// Lista de Etapas de Entrega (Operador logístico)
			        sql = "SELECT FASEOPERADORLOGISTICO,"
			            +        "STATUSENTREGA,"
			            +        "DTETAPAPEDIDO "
			            + "FROM "
			            + "("
			            +     "SELECT DISTINCT 1 AS FASEOPERADORLOGISTICO,"
			            +                     "'PEDIDO ENCAMINHADO PARA ENTREGA' AS STATUSENTREGA,"
			            +                     "TO_CHAR(MIN(ETAPAPEDIDO.DTETAPAPEDIDO),'DD/MM/YYYY HH24:MI:SS') AS DTETAPAPEDIDO "
			            +                "FROM RETENCAO.ETAPAPEDIDO ETAPAPEDIDO, "
			            +                     "RETENCAO.PRODUTOENTREGA PRODUTOENTREGA "
			            +               "WHERE ETAPAPEDIDO.IDPRODUTOENTREGA = PRODUTOENTREGA.IDPRODUTOENTREGA "
			            +                 "AND ETAPAPEDIDO.CDCPFCNPJ = " + filtro.getNrDocumento().toString() + " "
			            +                 "AND PRODUTOENTREGA.NRNOTAFISCAL = " + filtro.getNrNotaFiscal().toString() + " "
			            + "UNION ALL "
			            +     "SELECT DISTINCT 2 AS FASEOPERADORLOGISTICO, "
			            +                     "UPPER(REPLACE(PRODUTOENTREGA.STATUSENTREGA,' ','')) AS STATUSENTREGA, "
			            +                     "TO_CHAR(MAX(ETAPAPEDIDO.DTETAPAPEDIDO),'DD/MM/YYYY HH24:MI:SS') AS DTETAPAPEDIDO "
			            +                "FROM RETENCAO.ETAPAPEDIDO ETAPAPEDIDO, "
			            +                     "RETENCAO.PRODUTOENTREGA PRODUTOENTREGA "
			            +               "WHERE ETAPAPEDIDO.IDPRODUTOENTREGA = PRODUTOENTREGA.IDPRODUTOENTREGA "
			            +                 "AND ETAPAPEDIDO.CDCPFCNPJ = " + filtro.getNrDocumento().toString() + " "
			            +                 "AND PRODUTOENTREGA.NRNOTAFISCAL = " + filtro.getNrNotaFiscal().toString() + " "
			            +            "GROUP BY PRODUTOENTREGA.STATUSENTREGA "
			            + ") "
			            + "WHERE DTETAPAPEDIDO IS NOT NULL AND STATUSENTREGA IS NOT NULL ";
			        
					ps = conn.prepareStatement(sql);

					// log
					if (logger.isDebugEnabled()) {
						logger.debug("vai executar query="+sql.toString());
					}

					rs = ps.executeQuery();

					while (rs.next()) {
						if ( rs.getString("FASEOPERADORLOGISTICO").equals("1") ) {
							EtapaPedidoTO etapaPedidoTO = new EtapaPedidoTO();
							etapaPedidoTO.setDsEtapa(rs.getString(2));
							etapaPedidoTO.setDtEtapa(rs.getString(3));
							listaEtapaPedidoTO.add(etapaPedidoTO);
						}
						else if ( rs.getString("FASEOPERADORLOGISTICO").equals("2") ) {
							String statusEntrega = removerAcentos(rs.getString(2));
							if ( refStatusEntrega.containsKey(statusEntrega) ) {
								EtapaPedidoTO etapaPedidoTO = new EtapaPedidoTO();
								etapaPedidoTO.setDtEtapa(rs.getString(3));
								etapaPedidoTO.setDsEtapa(refStatusEntrega.get(statusEntrega).toString());
								listaEtapaPedidoTO.add(etapaPedidoTO);
							}
						}
					}		
					filtro.setListaEtapaPedido(listaEtapaPedidoTO);
				}
			}
		} catch (DocumentoNaoInformadoException ex) {
			throw new DocumentoNaoInformadoException(ex.getMessage());
		} catch (PedidoNaoInformadoException ex) {
			throw new PedidoNaoInformadoException(ex.getMessage());
		} catch (Exception ex) {
			throw new DAOException(ex);
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		List listRetorno = new ArrayList();

		listRetorno.add(filtro);

		if (logger.isDebugEnabled()) {
			logger.debug("buscarDetalhesPedido - end");
		}
		
		return listRetorno;
	}
}
