package br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Map;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.HistoricoVO;

import com.framework.coordinator.Transaction;
import com.framework.dao.DataAccessObject;
import com.framework.exception.DAOException;
import com.framework.exception.NotFoundException;
import com.framework.util.PagedList;
import com.framework.vo.ValueObject;

public class HistoricoDAO extends TradutorDAO {

	private static final String SQL_MAX = "CSRV_HISTORICO";

	/**
	 * string dos campos da tabela Servico
	 */

	private static final String CAMPOS = "CSRV_HISTORICO.CD_HISTORICO," + "CSRV_HISTORICO.CD_LOGIN_USUARIO," + "CSRV_HISTORICO.DT_SCRIPT, " + "CSRV_HISTORICO.NM_TABELA," + "CSRV_HISTORICO.DS_SCRIPT";
	/**
	 * string com o nome das tabelas (join se existir)
	 */
	private static final String TABELAS = " FROM CSRV_HISTORICO ";

	private static final String SQL_PARAMS = "SELECT " + CAMPOS + TABELAS;

	private static final String SQL_BY_ID = "SELECT " + CAMPOS + TABELAS
			+ "WHERE CSRV_HISTORICO.CD_HISTORICO = ?";

	/**
	 * SQL para insercao de registro
	 */
	private static final String SQL_INSERT = "INSERT INTO CSRV_HISTORICO ("
			+ CAMPOS + ") VALUES ( ?, ?, SYSDATE, ?, ? )";

	/**
	 * Instancia de ServicoNegocioDAO
	 */
	private static HistoricoDAO instance = new HistoricoDAO();

	private HistoricoDAO() {
	}

	/**
	 * Retorna uma instancia de DataAccessObject
	 * 
	 * @return um DataAccessObject
	 */
	public static DataAccessObject getInstance() {
		return instance;
	}

	/**
	 * Procura todos os registros
	 * 
	 * @return - um Collection com todos os VOs
	 * @throws DAOException -
	 *             se algum erro ocorrer
	 */
	public Collection findByParams(Map params) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection historicoVOs = new PagedList();

		try {
			conn = getConnection();

			pstmt = mountSql(conn, SQL_PARAMS, params);
			rs = pstmt.executeQuery();

			historicoVOs = getCollection(rs, new HistoricoVO(), params);
		} catch (Exception e) {
			throw new DAOException("Erro na seleção de Historico.", e, true);
		} finally {
			closeConnection(conn, pstmt, rs);
		}

		return historicoVOs;
	}

	public ValueObject findById(String id) throws NotFoundException,
			DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			HistoricoVO historicoVO = new HistoricoVO(id);

			conn = getConnection();

			pstmt = conn.prepareStatement(SQL_BY_ID);

			setLong(1, pstmt, historicoVO.getCdHistorico());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				historicoVO = (HistoricoVO) getValueObject(rs, historicoVO);
			} else
				return null;

			return historicoVO;
		} catch (Exception e) {
			throw new DAOException("Erro na seleção do historico.", e);
		} finally {
			closeConnection(conn, pstmt, rs);
		}
	}

	public void insert(HistoricoVO historicoVO, Transaction tx)
			throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			tx = joinTransaction(tx);
			conn = getConnection(tx);

			String id = getNextId(SQL_MAX);
			if (id.equals("0")) {
				id = "1";
			}
			historicoVO.setCdHistorico(Long.valueOf(id));

			pstmt = conn.prepareStatement(SQL_INSERT);

			setInteger(1, pstmt, historicoVO.getCdHistorico().intValue());
			setString(2, pstmt, historicoVO.getLogin());
			setString(3, pstmt, historicoVO.getTabela());
			setString(4, pstmt, historicoVO.getDsScript());

			pstmt.executeUpdate();
			tx.commitTrans();

		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro na inserção de Historico.", e,
					true);
		} finally {
			closeConnection(null, pstmt, null);
		}
	}
}
