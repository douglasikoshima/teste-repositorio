package br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;

import java.util.Map;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.SistemaLegadoVO;

import com.framework.coordinator.Transaction;
import com.framework.dao.DataAccessObject;
import com.framework.exception.DAOException;
import com.framework.exception.NotFoundException;
import com.framework.util.PagedList;

public class SistemaLegadoDAO extends TradutorDAO {
	private static final String SQL_MAX = "CSRV_SISTEMA_LEGADO";

	private static final String CAMPOS = "CSRV_SISTEMA_LEGADO.CD_SISTEMA_LEGADO, "
			+ "CSRV_SISTEMA_LEGADO.DS_SISTEMA_LEGADO, "
			+ "CSRV_SISTEMA_LEGADO.NM_APLICACAO_HEADER ";

	private static final String TABELAS = " FROM CSRV_SISTEMA_LEGADO ";

	private static final String SQL_FIND_BY_IDX = "SELECT " + CAMPOS + TABELAS
			+ "WHERE CD_SISTEMA_LEGADO = ?";

	private static final String SQL_PARAMS = "SELECT " + CAMPOS + TABELAS;

	private static final String SQL_INSERT = "INSERT INTO CSRV_SISTEMA_LEGADO ("
			+ "CD_SISTEMA_LEGADO, "
			+ "DS_SISTEMA_LEGADO, "
			+ "NM_APLICACAO_HEADER" + ") VALUES ( ?, ?, ?)";

	private static final String SQL_UPDATE = "UPDATE CSRV_SISTEMA_LEGADO "
			+ "SET " + "DS_SISTEMA_LEGADO=?, " + "NM_APLICACAO_HEADER=? "
			+ "WHERE CD_SISTEMA_LEGADO= ? ";

	private static final String SQL_DELETE = "DELETE FROM CSRV_SISTEMA_LEGADO WHERE CD_SISTEMA_LEGADO= ?";

	private static SistemaLegadoDAO instance = new SistemaLegadoDAO();

	private SistemaLegadoDAO() {
	}

	public static DataAccessObject getInstance() {
		return instance;
	}

	/**
	 * Procura no BD algum registro que possua o ID especificado
	 * 
	 * @param id -
	 *            O ID para procurar
	 * @return - um VO para o registro desejado, se encontrado
	 * @throws com.framework.exception.NotFoundException -
	 *             se o registro desejado nao for encontrado
	 * @throws com.framework.exception.DAOException -
	 *             se outro erro ocorrer
	 */
	public SistemaLegadoVO findById(String id) throws NotFoundException,
			DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			SistemaLegadoVO sistemaLegadoVO = new SistemaLegadoVO(id);

			conn = getConnection();

			pstmt = conn.prepareStatement(SQL_FIND_BY_IDX);

			setLong(1, pstmt, sistemaLegadoVO.getCdSistemaLegado());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				sistemaLegadoVO = (SistemaLegadoVO) getValueObject(rs,
						sistemaLegadoVO);
			}
			else
			{
				return null;
			}

			return sistemaLegadoVO;
		} catch (Exception e) {
			throw new DAOException("Erro na seleção de SistemaLegado.", e);
		} finally {
			closeConnection(conn, pstmt, rs);
		}
	}

	public Collection findByParams(Map params) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection sistemaLegado = new PagedList();

		try {
			conn = getConnection();

			pstmt = mountSql(conn, SQL_PARAMS, params);
			rs = pstmt.executeQuery();

			sistemaLegado = getCollection(rs, new SistemaLegadoVO(), params);
		} catch (Exception e) {
			throw new DAOException("Erro na seleção de SistemaLegado.", e, true);
		} finally {
			closeConnection(conn, pstmt, rs);
		}

		return sistemaLegado;
	}

	public void insert(SistemaLegadoVO sistemaLegadoVO, Transaction tx) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			tx = joinTransaction(tx);
			conn = getConnection(tx);

			pstmt = conn.prepareStatement(SQL_INSERT);

			String id = "";

			if (sistemaLegadoVO.getCdSistemaLegado() == null) {
				id = getNextId(SQL_MAX);
				setInteger(1, pstmt, Integer.valueOf(id));
			}
			else {
				if( findById(sistemaLegadoVO.getCdSistemaLegado().toString()) !=null )
				{
					throw new CatalogoPRSException("Codigo de Sistema Legado Existente");
				}
				id = sistemaLegadoVO.getCdSistemaLegado().toString();
			}
			
			setInteger(1, pstmt, Integer.valueOf(id));
			setString(2, pstmt, sistemaLegadoVO.getDsSistemaLegado());
			setString(3, pstmt, sistemaLegadoVO.getNmAplicacaoHeader());

			pstmt.executeUpdate();

			String sql = "INSERT INTO CSRV_SISTEMA_LEGADO (CD_SISTEMA_LEGADO, DS_SISTEMA_LEGADO, NM_APLICACAO_HEADER) VALUES ("
					+ id
					+ ", '"
					+ sistemaLegadoVO.getDsSistemaLegado()
					+ "',' " + sistemaLegadoVO.getNmAplicacaoHeader() + "');";
			sistemaLegadoVO.getExtraFields().setString("SQL", sql);

			tx.commitTrans();

		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro na inserção de SistemaLegado.",
					e, true);
		} finally {
			closeConnection(null, pstmt, null);
		}
	}

	public void update(SistemaLegadoVO sistemaLegadoVO, Transaction tx) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			tx = joinTransaction(tx);
			conn = getConnection(tx);

			pstmt = conn.prepareStatement(SQL_UPDATE);

			setString(1, pstmt, sistemaLegadoVO.getDsSistemaLegado());

			setString(2, pstmt, sistemaLegadoVO.getNmAplicacaoHeader());

			// Chave
			setInteger(3, pstmt, sistemaLegadoVO.getCdSistemaLegado()
					.intValue());

			pstmt.executeUpdate();

			String sql = "UPDATE CSRV_SISTEMA_LEGADO SET DS_SISTEMA_LEGADO = "
					+ sistemaLegadoVO.getDsSistemaLegado() + " , '"
					+ sistemaLegadoVO.getNmAplicacaoHeader()
					+ " WHERE CD_SISTEMA_LEGADO = "
					+ sistemaLegadoVO.getCdSistemaLegado() + ";";

			sistemaLegadoVO.getExtraFields().setString("SQL", sql);

			tx.commitTrans();

		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this,
					"Erro na atualização de SistemaLegado.", e, true);
		} finally {
			closeConnection(null, pstmt, null);
		}
	}

	public void delete(SistemaLegadoVO sistemaLegadoVO, Transaction tx) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			tx = joinTransaction(tx);
			conn = getConnection(tx);

			pstmt = conn.prepareStatement(SQL_DELETE);

			setInteger(1, pstmt, sistemaLegadoVO.getCdSistemaLegado()
					.intValue());

			pstmt.executeUpdate();

			String sql = "DELETE FROM CSRV_SISTEMA_LEGADO WHERE CD_SISTEMA_LEGADO = "
					+ sistemaLegadoVO.getCdSistemaLegado() + ";";

			sistemaLegadoVO.getExtraFields().setString("SQL", sql);

			tx.commitTrans();

		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro na remoção de SistemaLegado.",
					e, true);
		} finally {
			closeConnection(null, pstmt, null);
		}
	}
}
