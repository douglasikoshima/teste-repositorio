package com.indracompany.catalogo.ejb.servicointeratividade;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.ServicoInteratividade;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoInteratividadeTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Servico Interatividade com o EJB.  
 */
@Local
public interface ServicoInteratividadeBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/ServicoInteratividadeBean";
	
	/**
	 * @param servicoInteratividadeTO
	 * @return
	 * 
	 * Método responsável em pesquisar um Servico Interatividade.
	 * @throws DAOException 
	 */
	public List<ServicoInteratividadeTO> searchServicoInteratividade(ServicoInteratividadeTO servicoInteratividadeTO) throws BusinessException;
	
	/**
	 * @param servicoInteratividadeTO
	 * 
	 * Método responsável em criar ou editar um Servico Interatividade.
	 * @throws DAOException 
	 */
	public ServicoInteratividadeTO createUpdateServicoInteratividade(ServicoInteratividadeTO servicoInteratividadeTO) throws BusinessException;
	
	/**
	 * @param servicoInteratividadeTO
	 * @return
	 * 
	 * Método responsável em retornar somente um Servico Interatividade.
	 */
	public ServicoInteratividadeTO findById(ServicoInteratividadeTO servicoInteratividadeTO) throws BusinessException;
	
	
	
	public ServicoInteratividade createServicointeratividade (ServicoInteratividade si) throws BusinessException;
	
	public Integer validarServicointeratividade(ServicoInteratividadeTO servicoInteratividadeTO) throws BusinessException;
	
}
