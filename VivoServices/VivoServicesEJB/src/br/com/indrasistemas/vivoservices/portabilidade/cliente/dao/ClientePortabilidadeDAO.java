package br.com.indrasistemas.vivoservices.portabilidade.cliente.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.indrasistemas.framework.service.dao.DAOExceptionBuilder;
import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;

public class ClientePortabilidadeDAO extends HibernateBaseDAO {

	public int getProcessosLoja(long cdDDD, long nrLinha, int acao) throws DAOException {
		int acaoPort = acao;
		String sql = "select decode(count(1),0,?,101) pog  from customer.pessoaportabilidade where cdarearegistro = ? and nrlinha = ? and idusuarioalteracao = 31 ";
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = getJDBCConnection();
			ps = null;

			ps = conn.prepareStatement(sql);
			ps.setLong(1, acao);
			ps.setLong(2, cdDDD);
			ps.setLong(3, nrLinha);

			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				acaoPort = rs.getInt("pog");
			}
		} catch (Exception ex) {
			throw DAOExceptionBuilder.build(ex);

		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}
		return acaoPort;
	}

}
