package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.ClassificacaoMultimidia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ClassificacaoMultimidiaTO;

public interface ClassificacaoMultimidiaDAO {
	
	public List<ClassificacaoMultimidiaTO> findAll() throws DAOException;
	
	public ClassificacaoMultimidia findById(Integer idClassificacao) throws DAOException;
	
}