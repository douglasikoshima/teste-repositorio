package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.SCEncargoGruComArq;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.SCEncargoGruComArqItemTO;

/**
 * @author equipe Catalogo
 *
 */
public interface SCEncargoGruComArqDAO {
	
	/**
	 * Método responsável em criar/alterar um registro na tabela SCEncargoGruComArq
	 */
	public void createUpdateSCEncargoGruComArq(SCEncargoGruComArq solComGrupoComArq) throws DAOException;

	/**
	 * Método responsável em retornar os dados da tabela SCEncargoGruComArq
	 */
	public List<ImportacaoServicoFixaTO> search(ImportacaoServicoFixaTO to) throws DAOException;

	public List<SCEncargoGruComArqItemTO> searchCritica(Integer idArquivo) throws DAOException;
	
}
