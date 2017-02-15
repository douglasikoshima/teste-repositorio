package br.com.indrasistemas.vivoservices.atendimento.retencao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import br.com.indrasistemas.framework.service.dao.HibernateBaseDAO;
import br.com.indrasistemas.framework.service.dao.exceptions.DAOException;
import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.atendimento.retencao.to.RetencaoTO;

public class RetencaoUraDAO extends HibernateBaseDAO {

	private static final Log logger = LogFactory.getLog(RetencaoUraDAO.class);
	
	public RetencaoTO consultarOfertas(RequestInfoTO requestInfo, RetencaoTO in) throws DAOException {
	    StringBuffer sql = new StringBuffer();
	    RetencaoTO to = new RetencaoTO();
	    
        Connection conn = getJDBCConnection();
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(sql.toString());
            
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
            }

        } catch (Exception ex) {
            if (logger.isDebugEnabled()) {
                logger.debug("Não foi possível realização a busca. " + ex.getMessage());
            }
        } finally {
            closePreparedStatement(ps);
            closeConnection(conn);
        }
        return to;
	} 

}