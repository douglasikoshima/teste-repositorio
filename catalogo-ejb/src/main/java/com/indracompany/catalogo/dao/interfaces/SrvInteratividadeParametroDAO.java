package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.SrvInteratividadeParamTO;

/**
 * @author Luiz Pereira
 *
 */
public interface SrvInteratividadeParametroDAO {
	
	/**
	 * @param srvInteratividadeParametroTO
	 * @throws DAOException
	 */
	public void createUpdateSrvInteratividadeParam(SrvInteratividadeParamTO srvInteratividadeParametroTO) throws DAOException;
	
	/**
	 * @param idServicoInteratividade
	 * @throws DAOException
	 */
	public void removeSrvInteratividadeParamById(Integer idServicoInteratividade) throws DAOException;
	
}
