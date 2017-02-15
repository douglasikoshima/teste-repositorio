package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.MatrizOfertaProdutoPrecoTO;

public interface MatrizOfertaItemPrecoDAO {
	
	public List<MatrizOfertaProdutoPrecoTO> search(MatrizOfertaProdutoPrecoTO to) throws DAOException;
	
	public Integer searchCount(MatrizOfertaProdutoPrecoTO to) throws DAOException;
	
	public void remove(Long id) throws DAOException;
	
	public void removePrecoList(List<Long> idList, String userName) throws DAOException;
}
