package br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.OracleTypes;

import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.VivoNetVO;

import com.framework.coordinator.Transaction;
import com.framework.dao.DataAccessObject;
import com.framework.exception.DAOException;

public class VivoNetDAO extends VivoNetTradutorDAO {

    private static VivoNetDAO instance = new VivoNetDAO();

    private VivoNetDAO(){
    }

    public static DataAccessObject getInstance()
    {
        return instance;
    }
	
	
	public VivoNetVO insert(VivoNetVO vivoNetVO, Transaction tx) throws DAOException {
		CallableStatement stmt = null;
		Connection conn = getConnection();
		
		VivoNetVO to = new VivoNetVO();
		
		String query = "CALL vol.pkg_ip_parceiro.insert_ip_autenticador(?,?,?)";

		try {
			stmt = conn.prepareCall(query);
			stmt.setString(1, vivoNetVO.getNumeroIP());
			stmt.registerOutParameter(2, OracleTypes.NUMBER);
			stmt.registerOutParameter(3, OracleTypes.VARCHAR);
			stmt.execute();

			to.setCderro(Integer.parseInt(stmt.getObject(2).toString()));
			to.setDserro(stmt.getObject(3).toString());
			
		} catch (Exception e) {
			tx.rollBackTrans();
			throw new DAOException(this, "Erro na inserção do IP.",
					e, true);
		} finally {
			closeConnection(conn, stmt, null);
		}
		return to;
	}

	public List<VivoNetVO> searchAll() throws DAOException {
		CallableStatement stmt = null;
		Connection conn = getConnection();
		ResultSet rset = null;
		
		String query = "CALL vol.pkg_ip_parceiro.consulta_ip_autenticador(?,?,?)";
		
		List<VivoNetVO> vivoNetVO = new ArrayList<VivoNetVO>();

		try {
			stmt = conn.prepareCall(query);
			stmt.registerOutParameter(1, OracleTypes.CURSOR);
			stmt.registerOutParameter(2, OracleTypes.NUMBER);
			stmt.registerOutParameter(3, OracleTypes.VARCHAR);
			
			stmt.execute();
			rset = (ResultSet) stmt.getObject(1);
			
            while (rset.next()) {
            	VivoNetVO to = new VivoNetVO();
                to.setNumeroIP(rset.getString(1));
                to.setDtInclusao(rset.getString(2));
                vivoNetVO.add(to);                      
         }
		} catch (Exception e) {
			throw new DAOException("Erro ao consultar IP.", e, true);
		} finally {
			closeConnection(conn, stmt, rset);
		}
		return vivoNetVO;
	}

	@Override
	protected String getDatabaseLogicalName() {
		// TODO Auto-generated method stub
		return null;
	}

}
