package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ClassificacaoCategoriaScoreTO;

public interface ClassificacaoCategoriaScoreDAO {

	public List<ClassificacaoCategoriaScoreTO> findAll() throws DAOException;
}
