package br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Map;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroComumVO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroLegadoVO;

import com.framework.coordinator.Transaction;
import com.framework.dao.DataAccessObject;
import com.framework.exception.DAOException;
import com.framework.exception.NotFoundException;
import com.framework.util.PagedList;
import com.framework.vo.ValueObject;

/**
 * DAO de ErroLegado
 */
public class ErroLegadoDAO extends TradutorDAO {

	/**
	 * string dos campos da tabela ErroLegado
	 */
	private static final String CAMPOS = "CSRV_ERRO_LEGADO.CD_ERRO_LEGADO, "
			+ "CSRV_ERRO_LEGADO.CD_ERRO_PADRAO, "
			+ "CSRV_ERRO_LEGADO.CD_SERVICO, "
			+ "CSRV_ERRO_LEGADO.CD_SISTEMA_LEGADO, "
			+ "CSRV_SERVICO.DS_SERVICO, "
			+ "CSRV_SISTEMA_LEGADO.DS_SISTEMA_LEGADO, "
			+ "CSRV_SERVICO_NEGOCIO.CD_SERVICO_NEGOCIO, "
			+ "CSRV_SERVICO_NEGOCIO.DS_SERVICO_NEGOCIO,"
			+ "CSRV_ERRO_PADRAO.TX_MENSAGEM_ERRO_PADRAO ";

	/**
	 * string com o nome das tabelas (join se existir)
	 */
	private static final String TABELAS = " FROM CSRV_ERRO_LEGADO "
			+ " JOIN CSRV_SERVICO ON CSRV_SERVICO.CD_SERVICO = CSRV_ERRO_LEGADO.CD_SERVICO "
			+ " JOIN CSRV_SISTEMA_LEGADO ON CSRV_SISTEMA_LEGADO.CD_SISTEMA_LEGADO = CSRV_ERRO_LEGADO.CD_SISTEMA_LEGADO"
			+ " JOIN CSRV_ERRO_PADRAO ON csrv_erro_padrao.cd_erro_padrao = csrv_erro_legado.cd_erro_padrao "
			+ " JOIN csrv_servico_negocio ON csrv_servico_negocio.cd_servico_negocio = SUBSTR(csrv_erro_padrao.cd_erro_padrao,0,length(csrv_erro_padrao.cd_erro_padrao)-3)";

	/**
	 * SQL para selecao de registro com um determinado ID
	 */
	private static final String SQL_FIND_BY_IDX = "SELECT " + CAMPOS + TABELAS
			+ "WHERE CD_ERRO_LEGADO = ?";

	private static final String SQL_PARAMS = "SELECT " + CAMPOS + TABELAS;

	/**
	 * SQL para insercao de registro
	 */
	private static final String SQL_INSERT = "INSERT INTO CSRV_ERRO_LEGADO ("
			+ "CD_ERRO_LEGADO, " + "CD_ERRO_PADRAO, " + "CD_SERVICO, "
			+ "CD_SISTEMA_LEGADO" + ") VALUES ( ?, ?, ?, ? )";

	/**
	 * SQL para delecao de registro com um determinado ID
	 */
	private static final String SQL_DELETE = "DELETE FROM CSRV_ERRO_LEGADO WHERE CD_ERRO_PADRAO= ?";

	/**
	 * Instancia de ErroLegadoDAO
	 */
	private static ErroLegadoDAO instance = new ErroLegadoDAO();

	private ErroLegadoDAO() {
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
	public ValueObject findById(String id) throws NotFoundException,
			DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			ErroLegadoVO erroLegadoVO = new ErroLegadoVO(id);

			conn = getConnection();

			pstmt = conn.prepareStatement(SQL_FIND_BY_IDX);

			setString(1, pstmt, erroLegadoVO.getCdErroLegado());

			rs = pstmt.executeQuery();

			if (rs.next()) {
				erroLegadoVO = (ErroLegadoVO) getValueObject(rs, erroLegadoVO);
			} else {
				throw new NotFoundException("ErroLegado inexistente para id = "
						+ erroLegadoVO.getId());
			}

			return erroLegadoVO;
		} catch (Exception e) {
			throw new DAOException("Erro na seleção de ErroLegado.", e);
		} finally {
			closeConnection(conn, pstmt, rs);
		}
	}

	/**
	 * Procura todos os registros
	 * 
	 * @return - um Collection com todos os VOs
	 * @throws DAOException -
	 *             se algum erro ocorrer
	 */
	public Collection<?> findByParams(Map params) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection<?> erroLegadoVOs = new PagedList();

		try {
			conn = getConnection();

			pstmt = mountSql(conn, SQL_PARAMS, params);
			rs = pstmt.executeQuery();

			erroLegadoVOs = getCollection(rs, new ErroLegadoVO(), params);
		} catch (Exception e) {
			throw new DAOException("Erro na seleção de ErroLegado.", e, true);
		} finally {
			closeConnection(conn, pstmt, rs);
		}

		return erroLegadoVOs;
	}

	/**
	 * Insere um registro equivalente ao ValueObject
	 * 
	 * @param vo
	 *            O ValueObject a ser inserido
	 * @param tx
	 *            A transacao que o metodo esta
	 * @throws com.framework.exception.DAOException
	 *             em caso de erros
	 */
	public void insert(ErroLegadoVO erroLegadoVO, Transaction tx) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			tx = joinTransaction(tx);
			conn = getConnection(tx);

			pstmt = conn.prepareStatement(SQL_INSERT);

			setString(1, pstmt, erroLegadoVO.getCdErroLegado());
			setInteger(2, pstmt, erroLegadoVO.getCdErroPadrao());
			setInteger(3, pstmt, erroLegadoVO.getCdServico());
			setInteger(4, pstmt, erroLegadoVO.getCdSistemaLegado());

			pstmt.executeUpdate();

			String sql = "INSERT INTO CSRV_ERRO_LEGADO (CD_ERRO_LEGADO, CD_ERRO_PADRAO, CD_SERVICO, CD_SISTEMA_LEGADO) VALUES ("
					+ erroLegadoVO.getCdErroLegado()
					+ ", "
					+ erroLegadoVO.getCdErroPadrao()
					+ ", "
					+ erroLegadoVO.getCdServico()
					+ ", "
					+ erroLegadoVO.getCdSistemaLegado() + ");";
			erroLegadoVO.getExtraFields().setString("SQL", sql);

			tx.commitTrans();

		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro ao Inserir ErroLegado.", e,
					true);
		}finally {
			closeConnection(null, pstmt, null);
		}
	}

	/**
	 * Remove o registro referente ao ValueObject
	 * 
	 * @param vo
	 *            O ValueObject a ser removido
	 * @param tx
	 *            A transacao que o metodo esta
	 * @throws com.framework.exception.DAOException
	 *             em caso de erros
	 */
	public void delete(ErroComumVO erroComumVO, Transaction tx) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			tx = joinTransaction(tx);
			conn = getConnection(tx);

			pstmt = conn.prepareStatement(SQL_DELETE);

			setLong(1, pstmt, erroComumVO.getCdPadrao());

			pstmt.executeUpdate();
			
			String sql = "DELETE FROM CSRV_ERRO_LEGADO WHERE CD_ERRO_PADRAO = " + erroComumVO.getCdPadrao()+";";

            erroComumVO.getExtraFields().setString("SQL1", sql);

            tx.commitTrans();

		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro ao deletar ErroLegado.", e,
					true);
		}finally {
			closeConnection(null, pstmt, null);
		}
	}
}