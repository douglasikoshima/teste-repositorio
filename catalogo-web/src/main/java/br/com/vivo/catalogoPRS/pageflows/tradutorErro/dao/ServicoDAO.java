package br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ServicoVO;

import com.framework.coordinator.Transaction;
import com.framework.dao.DataAccessObject;
import com.framework.exception.DAOException;
import com.framework.exception.NotFoundException;
import com.framework.util.PagedList;

/**
 * DAO de Servico
 */
public class ServicoDAO extends TradutorDAO {
	/**
	 * SQL para selecao do maximo valor de ID
	 */
	private static final String SQL_MAX = "CSRV_SERVICO";

	/**
	 * string dos campos da tabela Servico
	 */
	private static final String CAMPOS = "CSRV_SERVICO.CD_SERVICO, "
			+ "CSRV_SERVICO.DS_SERVICO, "
			+ "CSRV_SISTEMA_LEGADO.CD_SISTEMA_LEGADO, "
			+ "CSRV_SISTEMA_LEGADO.DS_SISTEMA_LEGADO ";

	/**
	 * string com o nome das tabelas (join se existir)
	 */
	private static final String TABELAS = " FROM CSRV_SERVICO "
			+ "JOIN CSRV_SISTEMA_LEGADO ON "
			+ "SUBSTR(CSRV_SERVICO.CD_SERVICO,0,length(CSRV_SERVICO.CD_SERVICO)-3) = CSRV_SISTEMA_LEGADO.CD_SISTEMA_LEGADO ";

	/**
	 * SQL para selecao de registro com um determinado ID
	 */
	private static final String SQL_FIND_BY_IDX = "SELECT " + CAMPOS + TABELAS
			+ "WHERE CD_SERVICO = ?";

	private static final String SQL_PARAMS = "SELECT " + CAMPOS + TABELAS;

	/**
	 * SQL para insercao de registro
	 */
	private static final String SQL_INSERT = "INSERT INTO CSRV_SERVICO ("
			+ "CD_SERVICO, " + "DS_SERVICO" + ") VALUES ( ?, ? )";

	/**
	 * SQL para atualizacao de registro com um determinado ID
	 */
	private static final String SQL_UPDATE = "UPDATE CSRV_SERVICO " + "SET "
			+ "DS_SERVICO=? " + "WHERE CD_SERVICO= ? ";

	/**
	 * SQL para delecao de registro com um determinado ID
	 */
	private static final String SQL_DELETE = "DELETE FROM CSRV_SERVICO WHERE CD_SERVICO= ?";

	/**
	 * Instancia de ServicoDAO
	 */
	private static ServicoDAO instance = new ServicoDAO();

	private ServicoDAO() {
	}

	/**
	 * Retorna uma instancia de DataAccessObject
	 * 
	 * @return um DataAccessObject
	 */
	public static DataAccessObject getInstance() {
		return instance;
	}

	public ServicoVO findById(String id) throws NotFoundException, DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			ServicoVO servicoVO = new ServicoVO(id);

			conn = getConnection();

			pstmt = conn.prepareStatement(SQL_FIND_BY_IDX);

			setLong(1, pstmt, servicoVO.getCdServico());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				servicoVO = (ServicoVO) getValueObject(rs, servicoVO);
			} else {
				return null;
			}

			return servicoVO;
		} catch (Exception e) {
			throw new DAOException("Erro na procuda de Serviço", e);
		} finally {
			closeConnection(conn, pstmt, rs);
		}
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

	public Collection findByParams(Map params) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection servico = new PagedList();

		try {
			conn = getConnection();

			pstmt = mountSql(conn, SQL_PARAMS, params);
			rs = pstmt.executeQuery();

			servico = getCollection(rs, new ServicoVO(), params);
		} catch (Exception e) {
			throw new DAOException("Erro na seleção de Servico.", e, true);
		} finally {
			closeConnection(conn, pstmt, rs);
		}

		return servico;
	}

	public void insert(ServicoVO servicoVO, Transaction tx)
			throws DAOException, SQLException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			tx = joinTransaction(tx);
			conn = getConnection(tx);

			pstmt = conn.prepareStatement(SQL_INSERT);

			String id = "";

			if (servicoVO.getCdServico() == null || servicoVO.getCdServico() == 0) {
				// id = getNextId(SQL_MAX);
				id = getNextId(SQL_MAX, servicoVO.getSistemaLegado());
				
			} else {

				if (findById(servicoVO.getCdServico().toString()) != null) {

					throw new CatalogoPRSException(
							"Codigo de Serviço Existente");

				}
				id = servicoVO.getCdServico().toString();
			
			}

			setInteger(1, pstmt, Integer.valueOf(id));
			
			setString(2, pstmt, servicoVO.getDsServico());

			pstmt.executeUpdate();

			String sql = "INSERT INTO CSRV_SERVICO (CD_SERVICO, DS_SERVICO) VALUES ("
					+ id + ", '" + servicoVO.getDsServico() + "');";
			servicoVO.getExtraFields().setString("SQL", sql);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro na inserção de Servico.", e,
					true);
		} finally {
			closeConnection(null, pstmt, null);
		}
	}

	public void update(ServicoVO servicoVO, Transaction tx) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			tx = joinTransaction(tx);
			conn = getConnection(tx);

			pstmt = conn.prepareStatement(SQL_UPDATE);

			setString(1, pstmt, servicoVO.getDsServico());

			setInteger(2, pstmt, servicoVO.getCdServico().intValue());

			pstmt.executeUpdate();

			String sql = "UPDATE CSRV_SERVICO SET DS_SERVICO = "
					+ servicoVO.getDsServico() + " WHERE CD_SERVICO = "
					+ servicoVO.getCdServico() + ";";

			servicoVO.getExtraFields().setString("SQL", sql);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro na atualização de Servico.", e,
					true);
		} finally {
			closeConnection(null, pstmt, null);
		}
	}

	public void delete(ServicoVO servicoVO, Transaction tx) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			tx = joinTransaction(tx);
			conn = getConnection(tx);

			pstmt = conn.prepareStatement(SQL_DELETE);

			setInteger(1, pstmt, servicoVO.getCdServico().intValue());

			pstmt.executeUpdate();

			String sql = "DELETE FROM CSRV_SERVICO WHERE CD_SERVICO = "
					+ servicoVO.getCdServico() + ";";

			servicoVO.getExtraFields().setString("SQL", sql);
			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro na remoção de Servico.", e, true);
		} finally {
			closeConnection(null, pstmt, null);
		}
	}

	protected synchronized String getNextId(String SQL_MAX,
			Long cdSistemaLegado) throws DAOException {
		long seq = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = getConnection();

			pstmt = conn
					.prepareStatement("SELECT NVL(MAX(CSRV_SERVICO.CD_SERVICO),"
							+ cdSistemaLegado
							+ "000) + 1 FROM "
							+ SQL_MAX
							+ " WHERE SUBSTR(CSRV_SERVICO.CD_SERVICO,0,length(CSRV_SERVICO.CD_SERVICO)-3) = '"
							+ cdSistemaLegado + "'");
			pstmt.execute();
			rs = pstmt.getResultSet();

			if (rs.next()) {
				seq = rs.getLong(1);
			} else {
				throw new DAOException("Erro em getNextID()");
			}

		} catch (Exception e) {
			throw new DAOException("Erro em getNextID()", e);
		}
		finally
		{
			closeConnection(conn,pstmt, rs);
		}

		return "" + seq;
	}

}