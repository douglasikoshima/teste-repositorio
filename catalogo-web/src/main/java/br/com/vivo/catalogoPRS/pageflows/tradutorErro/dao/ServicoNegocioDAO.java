package br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ServicoNegocioVO;

import com.framework.coordinator.Transaction;
import com.framework.dao.DataAccessObject;
import com.framework.exception.DAOException;
import com.framework.exception.NotFoundException;
import com.framework.util.PagedList;

public class ServicoNegocioDAO extends TradutorDAO {

	private static final String SQL_MAX = "CSRV_SERVICO_NEGOCIO";

	private static final String CAMPOS = "CSRV_SERVICO_NEGOCIO.CD_SERVICO_NEGOCIO, "
			+ "CSRV_SERVICO_NEGOCIO.DS_SERVICO_NEGOCIO ";

	private static final String TABELAS = " FROM CSRV_SERVICO_NEGOCIO ";

	private static final String SQL_FIND_BY_IDX = "SELECT " + CAMPOS + TABELAS
	+ "WHERE CD_SERVICO_NEGOCIO = ?";
	
	private static final String SQL_PARAMS = "SELECT " + CAMPOS + TABELAS;

	private static final String SQL_INSERT = "INSERT INTO CSRV_SERVICO_NEGOCIO ("
			+ "CD_SERVICO_NEGOCIO, "
			+ "DS_SERVICO_NEGOCIO"
			+ ") VALUES ( ?, ? )";

	private static final String SQL_UPDATE = "UPDATE CSRV_SERVICO_NEGOCIO "
			+ "SET " + "DS_SERVICO_NEGOCIO=? " + "WHERE CD_SERVICO_NEGOCIO= ? ";

	private static final String SQL_DELETE = "DELETE FROM CSRV_SERVICO_NEGOCIO WHERE CD_SERVICO_NEGOCIO= ?";

	private static ServicoNegocioDAO instance = new ServicoNegocioDAO();

	private ServicoNegocioDAO() {
	}

	public static DataAccessObject getInstance() {
		return instance;
	}

	public ServicoNegocioVO findById(String id) throws NotFoundException,
			DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			ServicoNegocioVO servicoNegocioVO = new ServicoNegocioVO(id);

			conn = getConnection();

			pstmt = conn.prepareStatement(SQL_FIND_BY_IDX);

			setLong(1, pstmt, servicoNegocioVO.getCdServicoNegocio());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				servicoNegocioVO = (ServicoNegocioVO) getValueObject(rs,
						servicoNegocioVO);
			} else {
				return null;
			}

			return servicoNegocioVO;
		} catch (Exception e) {
			throw new DAOException("Erro na procuda de Servi&ccedil;o de Negocio", e);
		} finally {
			closeConnection(conn, pstmt, rs);
		}
	}

	public Collection findByParams(Map params) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection servicoNegocio = new PagedList();

		try {
			conn = getConnection();

			pstmt = mountSql(conn, SQL_PARAMS, params);
			rs = pstmt.executeQuery();

			servicoNegocio = getCollection(rs, new ServicoNegocioVO(), params);
		} catch (Exception e) {
			throw new DAOException("Erro na sele&ccedil;&atilde;o de Servi&ccedil;o Negocio.", e,
					true);
		} finally {
			closeConnection(conn, pstmt, rs);
		}

		return servicoNegocio;
	}

	public void insert(ServicoNegocioVO servicoNegocioVO, Transaction tx) throws DAOException,
			SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			tx = joinTransaction(tx);
			conn = getConnection(tx);
			
			pstmt = conn.prepareStatement(SQL_INSERT);

			String id = "";

			if (servicoNegocioVO.getCdServicoNegocio() == null) {
				id = getNextId(SQL_MAX);
				setInteger(1, pstmt, Integer.valueOf(id));
			} else {

				if (findById(servicoNegocioVO
						.getCdServicoNegocio().toString())!=null) {
					throw new CatalogoPRSException(
							"Codigo de Servi&ccedil;o de Negocio Existente");
				}
				id = servicoNegocioVO.getCdServicoNegocio().toString();
				setInteger(1, pstmt, Integer.valueOf(id));
			}

			setString(2, pstmt, servicoNegocioVO.getDsServicoNegocio());

			pstmt.executeUpdate();

			String sql = "INSERT INTO CSRV_SERVICO_NEGOCIO (CD_SERVICO_NEGOCIO, DS_SERVICO_NEGOCIO) VALUES ("
					+ id
					+ ", '"
					+ servicoNegocioVO.getDsServicoNegocio()
					+ "');";
			servicoNegocioVO.getExtraFields().setString("SQL", sql);
			
			tx.commitTrans();

		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro na inser&ccedil;&atilde;o de ServicoNegocio.",
					e, true);
		} finally {
			closeConnection(null, pstmt, null);
		}
	}

	public void update(ServicoNegocioVO servicoNegocioVO, Transaction tx) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			tx = joinTransaction(tx);
			conn = getConnection(tx);

			pstmt = conn.prepareStatement(SQL_UPDATE);

			setString(1, pstmt, servicoNegocioVO.getDsServicoNegocio());

			setInteger(2, pstmt, servicoNegocioVO.getCdServicoNegocio()
					.intValue());

			pstmt.executeUpdate();

			String sql = "UPDATE CSRV_SERVICO_NEGOCIO SET DS_SERVICO_NEGOCIO = "
					+ servicoNegocioVO.getDsServicoNegocio()
					+ " WHERE CD_SERVICO_NEGOCIO = "
					+ servicoNegocioVO.getCdServicoNegocio() + ";";

			servicoNegocioVO.getExtraFields().setString("SQL", sql);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro na atualiza&ccedil;&atilde;o de ServicoNegocio.", e, true);
		} finally {
			closeConnection(null, pstmt, null);
		}
	}

	public void delete(ServicoNegocioVO servicoNegocioVO, Transaction tx) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			tx = joinTransaction(tx);
			conn = getConnection(tx);

			pstmt = conn.prepareStatement(SQL_DELETE);

			setInteger(1, pstmt, servicoNegocioVO.getCdServicoNegocio()
					.intValue());

			pstmt.executeUpdate();

			String sql = "DELETE FROM CSRV_SERVICO_NEGOCIO WHERE CD_SERVICO_NEGOCIO = "
					+ servicoNegocioVO.getCdServicoNegocio() + ";";

			servicoNegocioVO.getExtraFields().setString("SQL", sql);
			
			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro na remo&ccedil;&atilde;o de ServicoNegocio.",
					e, true);
		} finally {
			closeConnection(null, pstmt, null);
		}
	}
}