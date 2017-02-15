package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.ContratoMatrizOferta;
import com.indracompany.catalogo.exception.DAOException;

/**
 * @author Luiz Pereira
 *
 */
public interface ContratoMatrizOfertaDAO {
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em retornar os dados da tabela ContratoMatrizOferta separados por virgula
	 */
	public List<String> exportContratoMatrizOferta() throws DAOException;
	
	
	/**
	 * @throws DAOException
	 * 
	 * Método responsável em remover todos os registros da tabela espelho.
	 */
	public void deleteAll() throws DAOException;
	
	/**
	 * @param contratoMatrizOfertaTO
	 * @throws DAOException
	 * 
	 * Método responsável em criar/alterar um registro na tabela ContratoMatrizOferta
	 */
	public void createUpdateContratoMatrizOferta(ContratoMatrizOferta contratoMatrizOferta) throws DAOException;
	
	/**
	 * @param contratoMatrizOfertaTOList
	 * @throws DAOException
	 * 
	 * Método responsável em criar/alterar vários registros na tabela ContratoMatrizOferta
	 */
	public void createUpdateContratoMatrizOferta(List<ContratoMatrizOferta> contratoMatrizOfertaTOList) throws DAOException;
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em validar todos os registros da tabela espelho.
	 * E gerar criticas a partir de um procedure.
	 */
	public Boolean validarDados() throws DAOException;
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em liberar os dados da tabela espelho para a tabela principal.
	 */
	public Boolean liberarProducao() throws DAOException;
	
	
	/**
	 * @return
	 * @throws DAOException
	 * 
	 * Método responsável em exportar os valores da tabela espelho com suas respectivas criticas.
	 */
	public List<String> exportContratoMatrizOfertaCriticas() throws DAOException;
}
