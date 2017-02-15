package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.SolComGrupoComArq;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.SolComGrupoComArqItemTO;

/**
 * @author equipe Catalogo
 *
 */
public interface SolComGrupoComArqDAO {
	
	/**
	 * Método responsável em criar/alterar um registro na tabela SolComGrupoComArq
	 */
	public void createUpdateSolComGrupoComArq(SolComGrupoComArq solComGrupoComArq) throws DAOException;

	/**
	 * Método responsável em retornar os dados da tabela SolComGrupoComArq
	 */
	public List<ImportacaoServicoFixaTO> search(ImportacaoServicoFixaTO to) throws DAOException;

	public List<SolComGrupoComArqItemTO> searchCritica(Integer idArquivo) throws DAOException;
	
	/**
	 * Método responsável em retornar os dados da tabela ContratoMatrizOferta separados por virgula
	 */
	public List<String> exportServicoServicoArq() throws DAOException;
	
	
	/**
	 * Método responsável em exportar os valores da tabela espelho com suas respectivas criticas.
	 */
	public List<String> exportServicoServicoArqCriticas() throws DAOException;
}
