package br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Collection;
import java.util.Map;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroComumVO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroPadraoVO;

import com.framework.coordinator.Transaction;
import com.framework.dao.DataAccessObject;
import com.framework.exception.DAOException;
import com.framework.exception.NotFoundException;
import com.framework.util.PagedList;
import com.framework.vo.ValueObject;

/**
 * DAO de ErroPadrao
 */
public class ErroPadraoDAO extends TradutorDAO
{

    /**
     * string dos campos da tabela ErroPadrao
     */
    private static final String CAMPOS =
    "CSRV_ERRO_PADRAO.CD_CLASSIFICACAO, " + 
    "CSRV_ERRO_PADRAO.CD_ERRO_PADRAO, " + 
    "CSRV_ERRO_PADRAO.TX_MENSAGEM_ERRO_PADRAO ";

	/**
     * string com o nome das tabelas (join se existir)
     */
    private static final String TABELAS = " FROM CSRV_ERRO_PADRAO ";

    /**
     * SQL para selecao de registro com um determinado ID
     */
    private static final String SQL_FIND_BY_IDX =
        "SELECT "
            + CAMPOS
			+ TABELAS
            + "WHERE CD_ERRO_PADRAO = ?";

	private static final String SQL_PARAMS = "SELECT " + CAMPOS + TABELAS;

    /**
     * SQL para insercao de registro
     */
    private static final String SQL_INSERT =
        "INSERT INTO CSRV_ERRO_PADRAO ("
            +      "CD_CLASSIFICACAO, " + 
    "CD_ERRO_PADRAO, " + 
    "TX_MENSAGEM_ERRO_PADRAO" + 
            ") VALUES ( 1, ?, ? )";

    /**
     * SQL para atualizacao de registro com um determinado ID
     */
    private static final String SQL_UPDATE =
        "UPDATE CSRV_ERRO_PADRAO "
            + "SET " +
    "TX_MENSAGEM_ERRO_PADRAO = ? "
            + "WHERE CD_ERRO_PADRAO= ? ";
            

    /**
     * SQL para delecao de registro com um determinado ID
     */
    private static final String SQL_DELETE =
        "DELETE FROM CSRV_ERRO_PADRAO WHERE CD_ERRO_PADRAO= ?";

    /**
     * Instancia de ErroPadraoDAO
     */
    private static ErroPadraoDAO instance = new ErroPadraoDAO();

    private ErroPadraoDAO()
    {
    }

    /**
     * Retorna uma instancia de DataAccessObject
     * @return um DataAccessObject
     */
    public static DataAccessObject getInstance()
    {
        return instance;
    }

    /**
     * Procura no BD algum registro que possua o ID especificado
     * @param id - O ID para procurar
     * @return - um VO para o registro desejado, se encontrado
     * @throws com.framework.exception.NotFoundException - se o registro desejado nao for encontrado
     * @throws com.framework.exception.DAOException - se outro erro ocorrer
     */
    public ValueObject findById(String id)
        throws NotFoundException, DAOException
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try
        {
            ErroPadraoVO erroPadraoVO = new ErroPadraoVO(id);

            conn = getConnection();

            pstmt = conn.prepareStatement(SQL_FIND_BY_IDX);

            setInteger(1, pstmt, erroPadraoVO.getCdErroPadrao());

            rs = pstmt.executeQuery();

            if (rs.next())
            {
                erroPadraoVO = (ErroPadraoVO) getValueObject(rs, erroPadraoVO);
            }
            else
            {
                return null;
            }

            return erroPadraoVO;
        }
        catch (Exception e)
        {
        	throw new DAOException("Erro na seleção de ErroPadrao.", e);
        }
        finally {
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
    public Collection findByParams(Map params)
            throws DAOException
    {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Collection erroPadraoVOs = new PagedList();

        try
        {            
            conn = getConnection();

            pstmt = mountSql(conn, SQL_PARAMS, params);
            rs = pstmt.executeQuery();
            
            erroPadraoVOs = getCollection( rs, new ErroPadraoVO(), params );
        }
        catch (Exception e)
        {
            throw new DAOException("Erro na seleção de ErroPadrao.", e, true);
        }
        finally {
			closeConnection(conn, pstmt, rs);
		}

        return erroPadraoVOs;
    }

    /**
     * Insere um registro equivalente ao ValueObject
     * @param vo O ValueObject a ser inserido
     * @param tx A transacao que o metodo esta
     * @throws com.framework.exception.DAOException em caso de erros
     */
    public void insert(ErroPadraoVO erroPadraoVO, Transaction tx)
        throws DAOException
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
        	tx = joinTransaction(tx);
			conn = getConnection(tx);

            pstmt = conn.prepareStatement(SQL_INSERT);

            setInteger( 1, pstmt, erroPadraoVO.getCdErroPadrao());
            setString( 2, pstmt, erroPadraoVO.getTxMensagemErroPadrao());

            pstmt.executeUpdate();

            String sql = "INSERT INTO CSRV_ERRO_PADRAO (CD_CLASSIFICACAO, CD_ERRO_PADRAO, TX_MENSAGEM_ERRO_PADRAO) VALUES ( 1, " + erroPadraoVO.getCdErroPadrao() + ", '" + erroPadraoVO.getTxMensagemErroPadrao() + "');";
            
            erroPadraoVO.getExtraFields().setString("SQL", sql);
            
            tx.commitTrans();

		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro ao Inserir ErroPadrão.", e,
					true);
		}finally {
			closeConnection(null, pstmt, null);
		}
    }

    /**
     * Atualiza o registro referente ao ValueObject
     * @param vo O ValueObject a ser atualizado
     * @param tx A transacao que o metodo esta
     * @throws com.framework.exception.DAOException Em caso de erros
     */
    public void update(ErroComumVO erroComumVO, Transaction tx)
        throws DAOException
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
        	tx = joinTransaction(tx);
			conn = getConnection(tx);

            pstmt = conn.prepareStatement(SQL_UPDATE);

            setString( 1, pstmt, erroComumVO.getMensagem());

            setLong( 2, pstmt, erroComumVO.getCdPadrao());

            pstmt.executeUpdate();
            
            String sql = "UPDATE CSRV_ERRO_PADRAO SET TX_MENSAGEM_ERRO_PADRAO = " + erroComumVO.getMensagem() + " WHERE CD_ERRO_PADRAO = " + erroComumVO.getCdPadrao()+";";

            erroComumVO.getExtraFields().setString("SQL", sql);
            
            tx.commitTrans();

		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro ao Atualizar Mensagem.", e,
					true);
		}finally {
			closeConnection(null, pstmt, null);
		}
    }

    /**
     * Remove o registro referente ao ValueObject
     * @param vo O ValueObject a ser removido
     * @param tx A transacao que o metodo esta
     * @throws com.framework.exception.DAOException em caso de erros
     */
    public void delete(ErroComumVO erroComumVO, Transaction tx)
        throws DAOException
    {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try
        {
        	tx = joinTransaction(tx);
			conn = getConnection(tx);

            pstmt = conn.prepareStatement(SQL_DELETE);

            setLong(1, pstmt, erroComumVO.getCdPadrao());

            pstmt.executeUpdate();
            
            String sql = "DELETE FROM CSRV_ERRO_PADRAO WHERE CD_ERRO_PADRAO = " + erroComumVO.getCdPadrao()+";";

            erroComumVO.getExtraFields().setString("SQL2", sql);

            tx.commitTrans();

		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro ao deletar ErroPadrão.", e,
					true);
		}finally {
			closeConnection(null, pstmt, null);
		}
    }
    
    public synchronized String getNextId(String cdSistemaNegocio) throws DAOException {
		long seq = 1;
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;		

		try {
			conn = getConnection();

			pstmt = conn.prepareStatement("select max(CSRV_ERRO_PADRAO.cd_erro_padrao) + 1 " +
					" from CSRV_ERRO_PADRAO " +
					" where SUBSTR(CSRV_ERRO_PADRAO.cd_erro_padrao,0,length(CSRV_ERRO_PADRAO.cd_erro_padrao)-3) = ?");
			
			pstmt.setString(1, cdSistemaNegocio);
			pstmt.execute();
			rs = pstmt.getResultSet();

			if (rs.next()) {
				seq = rs.getLong(1);
			} else {
				throw new DAOException("Erro em getNextID()");
			}

		} catch (Exception e) {
			throw new DAOException("Erro em getNextID()", e);
		} finally {
			closeConnection(conn, pstmt, rs);
		}

		return "" + seq;
	}
}