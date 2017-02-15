package br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Map;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroComumVO;

import com.framework.dao.DataAccessObject;
import com.framework.exception.DAOException;
import com.framework.exception.NotFoundException;
import com.framework.util.PagedList;
import com.framework.vo.ValueObject;

public class ErroComumDAO extends TradutorDAO {

	private static final String CAMPOS = "CSRV_ERRO_PADRAO.CD_CLASSIFICACAO, "
			+ "CSRV_ERRO_PADRAO.CD_ERRO_PADRAO, "
			+ "CSRV_ERRO_PADRAO.TX_MENSAGEM_ERRO_PADRAO, +"
			+ "CSRV_ERRO_LEGADO.CD_ERRO_LEGADO, "
			+ "CSRV_ERRO_LEGADO.CD_ERRO_PADRAO, "
			+ "CSRV_ERRO_LEGADO.CD_SERVICO, "
			+ "CSRV_ERRO_LEGADO.CD_SISTEMA_LEGADO";

	/**
	 * string com o nome das tabelas (join se existir)
	 */
	private static final String TABELAS = " FROM CSRV_ERRO_PADRAO X, CSRV_ERRO_LEGADO Y ";

	/**
	 * SQL para selecao de registro com um determinado ID
	 */
	private static final String SQL_FIND_BY_IDX = "SELECT " + CAMPOS + TABELAS
			+ "WHERE X.CD_ERRO_PADRAO = ? AND Y.CD_ERRO_PADRAO = ?";

	private static final String SQL_PARAMS = "SELECT " + CAMPOS + TABELAS;

    private static ErroComumDAO instance = new ErroComumDAO();

    private ErroComumDAO()
    {
    }

    public static DataAccessObject getInstance()
    {
        return instance;
    }
	
	
	public ValueObject findById(String id) throws NotFoundException,
			DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			ErroComumVO erroComumVO = new ErroComumVO(id);

			conn = getConnection();

			pstmt = conn.prepareStatement(SQL_FIND_BY_IDX);

			setLong(1, pstmt, erroComumVO.getCdPadrao());
			
			setLong(2, pstmt, erroComumVO.getCdPadrao());
			

			rs = pstmt.executeQuery();

			if (rs.next()) {
				erroComumVO = (ErroComumVO) getValueObject(rs, erroComumVO);
			} else {
				throw new NotFoundException("ErroPadrao inexistente para id = "
						+ erroComumVO.getId());
			}

			return erroComumVO;
		} catch (Exception e) {
			throw new DAOException("Erro na seleção de Erro.", e);
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
	public Collection findByParams(Map params) throws DAOException {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Collection erroPadraoVOs = new PagedList();

		try {
			conn = getConnection();

			pstmt = mountSql(conn, SQL_PARAMS, params);
			rs = pstmt.executeQuery();

			erroPadraoVOs = getCollection(rs, new ErroComumVO(), params);
		} catch (Exception e) {
			throw new DAOException("Erro na seleção de ErroPadrao.", e, true);
		} finally {
			closeConnection(conn, pstmt, rs);
		}

		return erroPadraoVOs;
	}

}
