package br.com.indrasistemas.vivoservices.listapup.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.dao.DAOExceptionBuilder;
import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.listapup.to.LinhaPUPWSTO;
import br.com.indrasistemas.vivoservices.listapup.to.ParametrosLinhaTO;
import br.com.indrasistemas.vivoservices.listapup.to.PermissaoTO;
import br.com.indrasistemas.vivoservices.listapup.webservice.to.ResultadoLinhaPUPTO;

public class ListaPUPDAO extends HibernateBaseDAO {

	private static final Log logger = LogFactory.getLog(ListaPUPDAO.class);

	public ResultadoLinhaPUPTO inserirLinhaPUP(RequestInfoTO requestInfo, Integer cdDDD, Integer nrLinha, String nmCampo, boolean inAtivo) throws DAOException {

		if (logger.isDebugEnabled()) {
			logger.debug("Início de inserção de linha na PUP: nrLinha: " + cdDDD + "" + nrLinha + "; nmCampo: " + nmCampo + "; inAtivo: " + inAtivo);
		}

		ResultadoLinhaPUPTO resultadoLinhaPup = new ResultadoLinhaPUPTO();
		StringBuffer sql = new StringBuffer();

		// Validação de prazo de expiração (180 dias para SMS e 365 para telemarketing e mensagens de voz)
		// Valores buscados da tabela APOIO.PARAMETRO
		String cdParametroExpiracao;
		if (nmCampo.indexOf("SMS") == 0) {
			cdParametroExpiracao = "PUP_PRAZO_EXPIRACAO";
		} else {
			cdParametroExpiracao = "PUP_PRAZO_EXPIRACAO_TLMKT";
		}

		if (inAtivo) {
			sql = new StringBuffer("INSERT INTO linha.permissaolinhapup ")
					.append("   (cdarearegistro, nrlinha, sgpermissaopup, inativo, dtexpiracao, idusuarioalteracao, dtultimaalteracao)   ")
					.append("     VALUES (?, ?, ?, ?, NULL, ?, SYSDATE) ");
		} else {
			
			sql = new StringBuffer("INSERT INTO linha.permissaolinhapup ")
				.append("            	            (cdarearegistro, nrlinha, sgpermissaopup, inativo, ")
	            .append(" 							dtexpiracao, ")
	            .append(" 							idusuarioalteracao, dtultimaalteracao ")
	            .append(" 							) ")
	            .append(" 					 VALUES (?, ?, ?, ?, ")
	            .append(" 				(SELECT TO_DATE (  SYSDATE ")
	            .append("                 + TO_NUMBER ((SELECT dsvalorparametro ")
	            .append("                                 FROM apoio.parametro ")
	            .append("                                 WHERE cdparametro = ")
	            .append("                                             '").append(cdParametroExpiracao).append("') ")
	            .append("                              ) ")
	            .append("                 ) ")
	            .append("    FROM DUAL), ")
	            .append(" ?, SYSDATE ")
	            .append(" ) ");
			
			

		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("(inserirLinhaPUP) query: " + sql.toString());
		}

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, cdDDD.intValue());
			ps.setInt(2, nrLinha.intValue());
			ps.setString(3, nmCampo);
			ps.setInt(4, inAtivo ? 1 : 0);
			ps.setLong(5, requestInfo.getUserId().longValue());
			ps.executeUpdate();

			resultadoLinhaPup.setCdRetorno("00");

		} catch (Exception ex) {
			throw DAOExceptionBuilder.build(ex);
		
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Fim de Inserção de linha na PUP: nrTelefone: " + cdDDD + "" + nrLinha + "; nmCampo: " + nmCampo + "; inAtivo: " + inAtivo);
		}

		return resultadoLinhaPup;
	}

	public ResultadoLinhaPUPTO alterarLinhaPUP(RequestInfoTO requestInfo, Integer cdDDD, Integer nrLinha, String nmCampo, boolean inAtivo) throws DAOException {

		if (logger.isDebugEnabled()) {
			logger.debug("Início de alteração de linha na PUP: nrTelefone: " + cdDDD + "" + nrLinha + "; nmCampo: " + nmCampo + "; inAtivo: " + inAtivo);
		}

		ResultadoLinhaPUPTO resultadoLinhaPup = null;

		// Validação de prazo de expiração (180 dias para SMS e 365 para telemarketing e mensagens de voz)
		// Valores buscados da tabela APOIO.PARAMETRO
		String cdParametroExpiracao;
		if (nmCampo.indexOf("SMS") == 0) {
			cdParametroExpiracao = "PUP_PRAZO_EXPIRACAO";
		
		} else {
			cdParametroExpiracao = "PUP_PRAZO_EXPIRACAO_TLMKT";
		}

		StringBuffer sql = null;

		if (inAtivo) {
			sql = new StringBuffer("UPDATE linha.permissaolinhapup ")
					.append("  SET inativo = ?, ")
					.append("      dtexpiracao = NULL, ")
					.append("      idusuarioalteracao = ?, ")
					.append("      dtultimaalteracao = SYSDATE ")
					.append("WHERE cdarearegistro = ? AND nrlinha = ? AND sgpermissaopup = ? ");
		
		} else {
			sql = new StringBuffer("UPDATE linha.permissaolinhapup ")
					.append("  SET inativo = ?, ")
					.append("      dtexpiracao = ")
					.append("         (SELECT TO_DATE (  SYSDATE ")
					.append("                          + (SELECT dsvalorparametro ")
					.append("                               FROM apoio.parametro ")
					.append("                              WHERE cdparametro = '")
					.append(cdParametroExpiracao)
					.append("') ")
					.append("                         ) ")
					.append("            FROM DUAL), ")
					.append("      idusuarioalteracao = ?, ")
					.append("      dtultimaalteracao = SYSDATE ")
					.append("WHERE cdarearegistro = ? AND nrlinha = ? AND sgpermissaopup = ? ");
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("(alterarLinhaPUP) query: " + sql.toString());
		}

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, inAtivo ? 1 : 0);
			ps.setLong(2, requestInfo.getUserId().longValue());
			ps.setInt(3, cdDDD.intValue());
			ps.setInt(4, nrLinha.intValue());
			ps.setString(5, nmCampo);
			ps.executeQuery();

			resultadoLinhaPup = new ResultadoLinhaPUPTO();

		} catch (Exception ex) {
			throw DAOExceptionBuilder.build(ex);
		
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		if (logger.isDebugEnabled()) {
			logger.debug("Fim de alteração de linha na PUP: nrTelefone: " + cdDDD + "" + nrLinha + "; nmCampo: " + nmCampo + "; inAtivo: " + inAtivo);
		}

		return resultadoLinhaPup;
	}

	/*
	 * Método utilizado para buscar dados de linhas cadastradas na base do
	 * Vivonet (clientes Vivo). Não mais utilizado devido à solicitação da OS
	 * 1016, uma vez que quaisquer linhas poderão ter opções de recebimento de
	 * mensagens (Clientes ou não clientes Vivo).
	 */
	public ParametrosLinhaTO buscarParametrosLinha(LinhaPUPWSTO to)
			throws DAOException {

		ParametrosLinhaTO parametrosLinha = null;

		StringBuffer sql = new StringBuffer("SELECT  idpessoa, ")
        		.append("        idpessoadepara, ")
        		.append("        idlinhatelefonica, ")
        		.append("        idtipolinha, ")
				.append("        nrlinha, ")
				.append("        cdarearegistro, ")
				.append("        idconta, ")
				.append("        cdconta, ")
				.append("        idlinhasistemaorigem, ")
				.append("        idcontasistemaorigem, ")
				.append("        nmpessoa, ")
				.append("        idtipopessoa, ")
				.append("        sgtipocarteira, ")
				.append("        dstipocarteira, ")
				.append("        incorporativo, ")
				.append("        sgtipopessoa, ")
				.append("        dstipopessoa, ")
				.append("        idsegmentacao, ")
				.append("        dssegmentacao, ")
				.append("        idtipocarteira, ")
				.append("        sgtiporelacionamento, ")
				.append("        nmtiporelacionamento, ")
				.append("        idufoperadora ")
				.append("   FROM customer.parametrosessaov01 ")
				.append("  WHERE nrlinha = ? ")
				.append("    AND cdarearegistro =  ? ")
				.append("    AND sgtiporelacionamento = 'C' ");

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, to.getNrTelefone().intValue());
			ps.setInt(2, to.getCdDDD().intValue());

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next() && i == 0) {
				parametrosLinha = new ParametrosLinhaTO();
				parametrosLinha.setIdPessoa(new Integer(rs.getInt(1)));
				parametrosLinha.setIdPessoaDePara(new Integer(rs.getInt(2)));
				parametrosLinha.setIdLinhaTelefonica(new Integer(rs.getInt(3)));
				parametrosLinha.setIdTipoLinha(new Integer(rs.getInt(4)));
				parametrosLinha.setNrLinha(new Integer(rs.getInt(5)));
				parametrosLinha.setCdAreaRegistro(new Integer(rs.getInt(6)));
				parametrosLinha.setIdConta(new Integer(rs.getInt(7)));
				parametrosLinha.setCdConta(new Integer(rs.getInt(8)));
				parametrosLinha.setIdLinhaSistemaOrigem(rs.getString(9));
				parametrosLinha.setIdContaSistemaOrigem(rs.getString(10));
				parametrosLinha.setNmPessoa(rs.getString(11));
				parametrosLinha.setIdTipoPessoa(new Integer(rs.getInt(12)));
				parametrosLinha.setSgTipoCarteira(rs.getString(13));
				parametrosLinha.setDsTipoCarteira(rs.getString(14));
				parametrosLinha.setInCorporativo("1".equals(rs.getString(15)) ? true : false);
				parametrosLinha.setSgTipoPessoa(rs.getString(16));
				parametrosLinha.setDsTipoPessoa(rs.getString(17));
				parametrosLinha.setIdSegmentacao(new Integer(rs.getInt(18)));
				parametrosLinha.setDsSegmentacao(rs.getString(19));
				parametrosLinha.setIdTipoCarteira(new Integer(rs.getInt(20)));
				parametrosLinha.setSgTipoRelacionamento(rs.getString(21));
				parametrosLinha.setNmTipoRelacionamento(rs.getString(22));
				parametrosLinha.setIdUFOperadora(new Integer(rs.getString(23)));

				i++;
			}

		} catch (Exception ex) {
			throw DAOExceptionBuilder.build(ex);
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		return parametrosLinha;
	}

	public ResultadoLinhaPUPTO consultarDescricoesPermissoesPUP()
			throws DAOException {

		ResultadoLinhaPUPTO resultadoLinhaPUPTO = new ResultadoLinhaPUPTO();
		LinhaPUPWSTO linhaPUPWSTO = new LinhaPUPWSTO();

		StringBuffer sql = new StringBuffer("SELECT pp.sgpermissaopup, pp.dspermissaopup FROM linha.permissaopup pp WHERE pp.inativo = 1 ");

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				String sgPermissao = rs.getString(1);
				if ("SMSPARC".equals(sgPermissao)) {
					linhaPUPWSTO.setDsSMSParceiros(rs.getString(2));
				
				} else if ("SMSPROD".equals(sgPermissao)) {
					linhaPUPWSTO.setDsSMSProdutos(rs.getString(2));
				
				} else if ("SMSPROM".equals(sgPermissao)) {
					linhaPUPWSTO.setDsSMSPromocoes(rs.getString(2));
				
				} else if ("SMSPROT".equals(sgPermissao)) {
					linhaPUPWSTO.setDsSMSProtocolos(rs.getString(2));
				
				} else if ("IVRPROM".equals(sgPermissao)) {
					linhaPUPWSTO.setDsIVRPromocoes(rs.getString(2));
				
				} else if ("IVRPROD".equals(sgPermissao)) {
					linhaPUPWSTO.setDsIVRProdutos(rs.getString(2));
				
				} else if ("IVRPARC".equals(sgPermissao)) {
					linhaPUPWSTO.setDsIVRParceiros(rs.getString(2));
				
				} else if ("BLKTEAT".equals(sgPermissao)) {
					linhaPUPWSTO.setDsTelmktMsgVoz(rs.getString(2));
				}
			}
			resultadoLinhaPUPTO.setLinhaPUPWSTO(linhaPUPWSTO);

		} catch (Exception ex) {
			throw DAOExceptionBuilder.build(ex);
		
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		return resultadoLinhaPUPTO;
	}

	public boolean existeBloqueioProcon(Integer cdDDD, Integer nrLinha)
			throws DAOException {
		
		boolean retorno = false;
		
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * ")
		   .append("  FROM linha.permissaolinhapup plp ")
		   .append(" WHERE plp.sgpermissaopup = 'BLKTEAT' ")
		   .append("   AND plp.cdarearegistro = ? ")
		   .append("   AND plp.nrlinha = ? ")
		   .append("   AND plp.inativo = 0 ")
		   .append("   AND idusuarioalteracao = (SELECT dsvalorparametro ")
		   .append("                               FROM apoio.parametro ")
		   .append("                              WHERE cdparametro = 'PUP_USUARIO_PROCON') ");
		
		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;
		
		try {

			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, cdDDD.intValue());
			ps.setInt(2, nrLinha.intValue());

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				retorno = true;
			}

		} catch (Exception ex) {
			throw DAOExceptionBuilder.build(ex);
		
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}
		
		return retorno;

	}

	public ResultadoLinhaPUPTO consultarLinhaPUP(Integer cdDDD,
			Integer nrLinha, boolean inDescricao, String sgReqPermissao,
			String tipoBusca) throws DAOException {

		ResultadoLinhaPUPTO resultadoLinhaPUPTO = new ResultadoLinhaPUPTO();
		LinhaPUPWSTO linhaPUPWSTO = new LinhaPUPWSTO();

		StringBuffer sql = new StringBuffer("SELECT pp.sgpermissaopup, ")
				.append("       (SELECT plp.inativo ")
				.append("          FROM linha.permissaolinhapup plp ")
				.append("         WHERE plp.sgpermissaopup = pp.sgpermissaopup ")
				.append("           AND cdarearegistro = ? AND nrlinha = ?) inativo, ")
				.append("       TO_CHAR ((SELECT plp.dtexpiracao ")
				.append("                   FROM linha.permissaolinhapup plp ")
				.append("                  WHERE plp.sgpermissaopup = pp.sgpermissaopup ")
				.append("                    AND cdarearegistro = ? AND nrlinha = ?), ")
				.append("                'DD/MM/YYYY' ")
				.append("               ) dtexpiracao, ")
				.append("       pp.dspermissaopup ")
				.append("  FROM linha.permissaopup pp ")
				.append(" WHERE pp.inativo = 1 ");

		// Busca por tipo de permissão
		if (sgReqPermissao != null && !"".equals(sgReqPermissao)) {
			sql = new StringBuffer("SELECT sgpermissaopup FROM linha.permissaolinhapup WHERE cdarearegistro = ? AND nrlinha = ? AND sgpermissaopup = ? ");
		
		} else

		// Apenas para verificação de existência da linha na Plataforma Unificada de Permissões.
		if (!inDescricao && sgReqPermissao == null && "GRAVACAO".equals(tipoBusca)) {
			sql = new StringBuffer("SELECT nrlinha FROM linha.permissaolinhapup WHERE cdarearegistro = ? AND nrlinha = ? AND ROWNUM = 1 ");
		}
		
		if (logger.isDebugEnabled()) {
			logger.debug("consultarLinhaPUP - query: " + sql.toString() + " nrTelefone = " + cdDDD + "" + nrLinha + " sgReqPermissao = " + ((sgReqPermissao == null) ? "":sgReqPermissao));
		}

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, cdDDD.intValue());
			ps.setInt(2, nrLinha.intValue());

			if (sgReqPermissao != null && !"".equals(sgReqPermissao)) {
				ps.setString(3, sgReqPermissao);
			
			} else if (sgReqPermissao == null && "CONSULTA".equals(tipoBusca)) {
				ps.setInt(3, cdDDD.intValue());
				ps.setInt(4, nrLinha.intValue());
			}

			ResultSet rs = ps.executeQuery();
			int i = 0;
			while (rs.next()) {

				if ((sgReqPermissao != null && !"".equals(sgReqPermissao)) || (!inDescricao && sgReqPermissao == null && "GRAVACAO".equals(tipoBusca))) {
					resultadoLinhaPUPTO.setLinhaPUPWSTO(new LinhaPUPWSTO());
					return resultadoLinhaPUPTO;
				}

				if (i == 0)
					linhaPUPWSTO = new LinhaPUPWSTO();

				String sgPermissao = rs.getString(1);
				if ("SMSPARC".equals(sgPermissao)) {
					linhaPUPWSTO.setInSMSParceiros(rs.getString(2) == null ? null : new Integer(rs.getInt(2)));
					if (inDescricao) {
						linhaPUPWSTO.setDsSMSParceiros(rs.getString(4));
						linhaPUPWSTO.setDtExpSMSParceiros(rs.getString(3) == null ? "" : rs.getString(3));
					}
				
				} else if ("SMSPROD".equals(sgPermissao)) {
					linhaPUPWSTO.setInSMSProdutos(rs.getString(2) == null ? null : new Integer(rs.getInt(2)));
					if (inDescricao) {
						linhaPUPWSTO.setDsSMSProdutos(rs.getString(4));
						linhaPUPWSTO.setDtExpSMSProdutos(rs.getString(3) == null ? "" : rs.getString(3));
					}
				
				} else if ("SMSPROM".equals(sgPermissao)) {
					linhaPUPWSTO.setInSMSPromocoes(rs.getString(2) == null ? null : new Integer(rs.getInt(2)));
					if (inDescricao) {
						linhaPUPWSTO.setDsSMSPromocoes(rs.getString(4));
						linhaPUPWSTO.setDtExpSMSPromocoes(rs.getString(3) == null ? "" : rs.getString(3));
					}
				
				} else if ("SMSPROT".equals(sgPermissao)) {
					linhaPUPWSTO.setInSMSProtocolos(rs.getString(2) == null ? null : new Integer(rs.getInt(2)));
					if (inDescricao) {
						linhaPUPWSTO.setDsSMSProtocolos(rs.getString(4));
						linhaPUPWSTO.setDtExpSMSProtocolos(rs.getString(3) == null ? "" : rs.getString(3));
					}
				
				} else if ("IVRPROM".equals(sgPermissao)) {
					linhaPUPWSTO.setInIVRPromocoes(rs.getString(2) == null ? null : new Integer(rs.getInt(2)));
					if (inDescricao) {
						linhaPUPWSTO.setDsIVRPromocoes(rs.getString(4));
						linhaPUPWSTO.setDtExpIVRPromocoes(rs.getString(3) == null ? "" : rs.getString(3));
					}
				
				} else if ("IVRPROD".equals(sgPermissao)) {
					linhaPUPWSTO.setInIVRProdutos(rs.getString(2) == null ? null : new Integer(rs.getInt(2)));
					if (inDescricao) {
						linhaPUPWSTO.setDsIVRProdutos(rs.getString(4));
						linhaPUPWSTO.setDtExpIVRProdutos(rs.getString(3) == null ? "" : rs.getString(3));
					}
				
				} else if ("IVRPARC".equals(sgPermissao)) {
					linhaPUPWSTO.setInIVRParceiros(rs.getString(2) == null ? null : new Integer(rs.getInt(2)));
					if (inDescricao) {
						linhaPUPWSTO.setDsIVRParceiros(rs.getString(4));
						linhaPUPWSTO.setDtExpIVRParceiros(rs.getString(3) == null ? "" : rs.getString(3));
					}
				
				} else if ("BLKTEAT".equals(sgPermissao)) {
					linhaPUPWSTO.setInTelmktMsgVoz(rs.getString(2) == null ? null : new Integer(rs.getInt(2)));
					if (inDescricao) {
						linhaPUPWSTO.setDsTelmktMsgVoz(rs.getString(4));
						linhaPUPWSTO.setDtExpTelmktMsgVoz(rs.getString(3) == null ? "" : rs.getString(3));
					}
				}
				i++;
			}

			if (i == 0 && (!inDescricao && sgReqPermissao == null && "GRAVACAO".equals(tipoBusca)) || (i == 0 && sgReqPermissao != null && !"".equals(sgReqPermissao) && "CONSULTA".equals(tipoBusca))) {
				linhaPUPWSTO = null;
			}

			resultadoLinhaPUPTO.setLinhaPUPWSTO(linhaPUPWSTO);

		} catch (Exception ex) {
			throw DAOExceptionBuilder.build(ex);
		
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		return resultadoLinhaPUPTO;
	}

	public void excluirLinhaPUP(Integer cdDDD, Integer nrLinha)
			throws DAOException {

		StringBuffer sql = new StringBuffer();
		sql = new StringBuffer("DELETE FROM linha.permissaolinhapup WHERE cdarearegistro = ? AND nrlinha = ? ");

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, cdDDD.intValue());
			ps.setInt(2, nrLinha.intValue());
			ps.executeUpdate();

			sql = new StringBuffer("DELETE FROM linha.linhapup WHERE cdarearegistro = ? AND nrlinha = ? ");
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, cdDDD.intValue());
			ps.setInt(2, nrLinha.intValue());
			ps.executeUpdate();

		} catch (Exception ex) {
			throw DAOExceptionBuilder.build(ex);
		
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}
		return;
	}

	public boolean verificaLinhaPUP(RequestInfoTO requestInfo, Integer cdDDD, Integer nrLinha) throws DAOException {
			
		StringBuffer sql = new StringBuffer("SELECT * FROM linha.linhapup WHERE cdarearegistro = ? AND nrlinha = ?");
		boolean retorno = false;
		
		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, cdDDD.intValue());
			ps.setInt(2, nrLinha.intValue());

			ResultSet rs = ps.executeQuery();
			//int i = 0;
			while (rs.next()) {
				retorno = true;
			}

		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("verificaLinhaPUP: " + ex.getMessage());
			}
			throw DAOExceptionBuilder.build(ex);
		
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		return retorno;

	}
	
	public void insertLinhaTelefonica(RequestInfoTO requestInfo, Integer cdDDD, Integer nrLinha) throws DAOException {
		
		StringBuffer sql = new StringBuffer("INSERT INTO linha.linhapup (cdarearegistro, nrlinha, dtcadastropup, idusuariocadastropup) VALUES (?, ?, SYSDATE, ?) ");
		
		if (logger.isDebugEnabled()) {
			logger.debug("(insertLinhaTelefonica) query: " + sql.toString());
		}
		
		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, cdDDD.intValue());
			ps.setInt(2, nrLinha.intValue());
			ps.setLong(3, requestInfo.getUserId().longValue());
			ps.executeUpdate();

		} catch (Exception ex) {
			throw DAOExceptionBuilder.build(ex);
		
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}
		return;
	}

	/*
	 * Método responsável pela busca das datas de cadastro da plataforma
	 * unificada de informações e de última alteração realizada em uma linha na
	 * Plataforma Unificada de Permissões.
	 */
	public String[] buscarDataCadastroAlteracaoPUP(Integer cdDDD, Integer nrLinha) throws DAOException {

		String[] dtCadastroPUP = new String[2];
		StringBuffer sql = new StringBuffer("SELECT TO_CHAR (lt.dtcadastropup, 'DD/MM/YYYY') dtcadastropup, ")
				.append("TO_CHAR (plp.dtultimaalteracao, 'DD/MM/YYYY') dtultimaalteracao ")
				.append("FROM linha.permissaolinhapup plp, linha.linhapup lt ")
				.append("WHERE plp.cdarearegistro = lt.cdarearegistro ")
				.append("AND plp.nrlinha = lt.nrlinha ")
				.append("AND plp.cdarearegistro = ? ")
				.append("AND plp.nrlinha = ? ")
				.append("AND ROWNUM = 1 ")
				.append("ORDER BY plp.dtultimaalteracao DESC ");

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			ps.setInt(1, cdDDD.intValue());
			ps.setInt(2, nrLinha.intValue());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				dtCadastroPUP[0] = rs.getString(1);
				dtCadastroPUP[1] = rs.getString(2);
			}

		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("Não foi possível realização de busca de datas. " + ex.getMessage());
			}
		
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}
		return dtCadastroPUP;
	}

	public String consultarSistemaOrigem(Long nrTelefone) throws DAOException {

		String nmSistemaOrigem = "";

		StringBuffer sql = new StringBuffer(
				"SELECT sis.nmsistemaorigem, lin.nrmin ").append(
				"  FROM linha.linhabase lin, ").append(
				"       linha.linhatelefonica lte, ").append(
				"       apoio.sistemaorigem sis, ").append(
				"       apoio.arearegistro ar ").append(
				" WHERE lin.idlinhabase = lte.idlinhabase ").append(
				"   AND sis.idsistemaorigem = lte.idsistemaorigem ").append(
				"   AND lin.idarearegistro = ar.idarearegistro ").append(
				"   AND lin.nrlinha = SUBSTR ('").append(nrTelefone.toString())
				.append("', 3) ")
				.append("   AND ar.cdarearegistro = SUBSTR ('").append(
						nrTelefone.toString()).append("', 1, 2) ");

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			// ps.setString(1, nrTelefone.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				nmSistemaOrigem = rs.getString(1).toUpperCase();
				if (nmSistemaOrigem.indexOf("NGIN") >= 0) {
					nmSistemaOrigem = "NGIN";
				
				} else if (nmSistemaOrigem.indexOf("ATLYS") >= 0) {
					nmSistemaOrigem = "ATLYS";
				}
			}

		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("Não foi possível consulta a sistema origem. " + ex.getMessage());
			}

		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		return nmSistemaOrigem;

	}

	public ParametrosLinhaTO consultarIDUsuarioCanalProcedencia(
			RequestInfoTO requestInfo) throws DAOException {

		ParametrosLinhaTO parametrosLinha = null;

		StringBuffer sql = new StringBuffer("SELECT cdparametro, dsvalorparametro ")
		    .append(" FROM apoio.parametro WHERE cdparametro IN ")
			.append("  ('PUP_").append(requestInfo.getSystemModule()).append("_IDUSUARIO', 'PUP_")
			.append(requestInfo.getSystemModule()).append("_CDPROCEDENCIA', 'PUP_").append(requestInfo.getSystemModule()).append("_CDCANAL') ");

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			String nmParam, vlParam;
			int i = 0;
			while (rs.next()) {

				if (i == 0)
					parametrosLinha = new ParametrosLinhaTO();

				nmParam = rs.getString(1).toUpperCase();
				vlParam = rs.getString(2).toUpperCase();

				if (nmParam.indexOf("PROCEDENCIA") >= 0) {
					parametrosLinha.setIdProcedencia(new Integer(vlParam));
				
				} else if (nmParam.indexOf("CANAL") >= 0) {
					parametrosLinha.setIdCanal(new Integer(vlParam));
				
				} else if (nmParam.indexOf("IDUSUARIO") >= 0) {
					parametrosLinha.setIdUser(new Integer(vlParam));
				}
				i++;
			}

			// Caso não sejam encontrados os parâmetros referentes ao sistema na apoio.parametro, serão buscadas informações de usuário legado genérico.
			if (i == 0) {
				sql = new StringBuffer("SELECT cdparametro, dsvalorparametro ")
						.append("  FROM apoio.parametro ")
						.append(" WHERE cdparametro IN ")
						.append("  ('PUP_LEGADOGENERICO_IDUSUARIO', 'PUP_LEGADOGENERICO_CDPROCEDENCIA', 'PUP_LEGADOGENERICO_CDCANAL') ");

				ps = conn.prepareStatement(sql.toString());
				rs = ps.executeQuery();

				while (rs.next()) {

				    if (i == 0)
						parametrosLinha = new ParametrosLinhaTO();

					nmParam = rs.getString(1).toUpperCase();
					vlParam = rs.getString(2).toUpperCase();

					if (nmParam.indexOf("PROCEDENCIA") >= 0) {
						parametrosLinha.setIdProcedencia(new Integer(vlParam));
					
					} else if (nmParam.indexOf("CANAL") >= 0) {
						parametrosLinha.setIdCanal(new Integer(vlParam));
					
					} else if (nmParam.indexOf("IDUSUARIO") >= 0) {
						parametrosLinha.setIdUser(new Integer(vlParam));
					}
					i++;
				}

			}

		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("Não foi possível consulta de folhas. " + ex.getMessage());
			}

		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}

		return parametrosLinha;
	}

	public List consultarFolhasContatos() throws DAOException {

		List nmPaths = new ArrayList();
		PermissaoTO permissao = new PermissaoTO();

		StringBuffer sql = new StringBuffer(
				"SELECT cdparametro, dsvalorparametro ").append(
				"  FROM apoio.parametro ").append(" WHERE cdparametro IN ")
				.append("          ('PUP_CONTATO_SMSPROT_ACEITO', ").append(
						"           'PUP_CONTATO_SMSPROT_NAO_ACEITO', ")
				.append("           'PUP_CONTATO_SMSPROM_ACEITO', ").append(
						"           'PUP_CONTATO_SMSPROM_NAO_ACEITO', ")
				.append("           'PUP_CONTATO_SMSPARC_ACEITO', ").append(
						"           'PUP_CONTATO_SMSPARC_NAO_ACEITO', ")
				.append("           'PUP_CONTATO_SMSPROD_ACEITO', ").append(
						"           'PUP_CONTATO_SMSPROD_NAO_ACEITO', ")
				.append("           'PUP_CONTATO_BLKTEAT_ACEITO', ").append(
						"           'PUP_CONTATO_BLKTEAT_NAO_ACEITO', ")
				.append("           'PUP_CONTATO_CONSULTA', ").append(
						"           'PUP_CONTATO_CONSCAN', ").append(
						"           'PUP_CONTATO_CONSLEG') ");

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				permissao = new PermissaoTO();
				permissao.setCdPath("PUP_CONTATO_" + rs.getString(1).substring(12));
				permissao.setCdPermissao(rs.getString(1).substring(12, 19));
				permissao.setNmPath(rs.getString(2));
				nmPaths.add(permissao);
			}

		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("Não foi possível consulta de folhas. " + ex.getMessage());
			}
		
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}
		return nmPaths;
	}
}