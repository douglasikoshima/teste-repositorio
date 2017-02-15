package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.ServicoInteratividade;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoInteratividadeTO;

/**
 * @author Luiz Pereira
 *
 */
public interface ServicoInteratividadeDAO {
	
	/**
	 * @param servicoInteratividadeTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em pesquisar na base um ou mais Servico Interatividade.
	 */
	public List<ServicoInteratividadeTO> searchServicoInteratividade(ServicoInteratividadeTO servicoInteratividadeTO) throws DAOException;
		
	/**
	 * @param servicoInteratividadeTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em criar/editar um Servico Interatividade na base.
	 */
	public ServicoInteratividadeTO createUpdateServicoInteratividade(ServicoInteratividadeTO servicoInteratividadeTO) throws DAOException;
 	
	/**
	 * @param servicoInteratividadeTO
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em buscar um Servico Interatividade na base.
	 */
	public ServicoInteratividadeTO findById(ServicoInteratividadeTO servicoInteratividadeTO) throws DAOException;
	
	
	public ServicoInteratividade createServicointeratividade(ServicoInteratividade si) throws DAOException;
	
	
	public Integer validarServicointeratividade(ServicoInteratividadeTO servicoInteratividadeTO) throws DAOException;

}
