package br.com.indrasistemas.vivoservices.atendimento.servicos.desbloqueio.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;

public class DesbloqueioDAO extends HibernateBaseDAO {

	private static final Log logger = LogFactory.getLog(HibernateBaseDAO.class);
	
	public void inserirDesbloqueio(String nrTelefone) throws DAOException {	
		
		logger.info("DesbloqueioDAO - Tentativa de Inserir Telefone [" + nrTelefone + "] no limitador diario");
		
		StringBuffer sql = new StringBuffer(
				"insert into linha.limitadordesbloqueio (nrlinha, dtdesbloqueio, idusuarioalteracao) values ('").append(nrTelefone).append("', sysdate, 1)");
		
		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		logger.info("DesbloqueioDAO - Insert Executado:" + sql.toString());
		
		try {
			ps = conn.prepareStatement(sql.toString());
			ps.executeQuery();
		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("Não foi possível inserir a tentativa de desbloqueio  " + ex.getMessage());
			}
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}
	}
	

	public int quantidadeDesbloqueio() throws DAOException {
		
		logger.info("DesbloqueioDAO - Obtendo parametro de quantidade diaria de desbloqueio APOIO.PARAMETRO = DESBLOQUEIO_LIMITADOR");
		
		int parametro = 0;
		StringBuffer sql = new StringBuffer("select dsvalorparametro from apoio.parametro where cdparametro = 'DESBLOQUEIO_LIMITADOR'");

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;

		try {
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {			
				parametro = rs.getInt(1);
			}
			logger.info("DesbloqueioDAO - Parametro de Quantidade de Desbloqueio: " + parametro);
		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("Não foi possível obter o parametro de limitador desbloqueio gsm " + ex.getMessage());
			}
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}
		return parametro;
	}		

	
	public int verificaLimite(String nrTelefone) throws DAOException {
		
		logger.info("DesbloqueioDAO - Verificando limite dirio para o telefone: " + nrTelefone);
		
		int qtde = 0;
		StringBuffer sql = new StringBuffer("select count(1) qtde from linha.limitadordesbloqueio where nrlinha = '");
				     sql.append(nrTelefone).append("' and to_char(dtdesbloqueio, 'yyyymmdd') =  to_char(sysdate, 'yyyymmdd')");

		Connection conn = getJDBCConnection();
		PreparedStatement ps = null;
		
		logger.info("DesbloqueioDAO - Query executada para verificar limite diario: " + sql.toString());
		
		try {
			ps = conn.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {			
				qtde = rs.getInt(1);
			}
			logger.info("DesbloqueioDAO - Quantidade identificada no dia atual: " + qtde);
		} catch (Exception ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("Não foi possível verificar o limite de desbloqueios " + ex.getMessage());
			}
		} finally {
			closePreparedStatement(ps);
			closeConnection(conn);
		}
		return qtde;
	}	
	
}
