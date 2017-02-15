package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.dao.MultimidiaTOBuilder;
import com.indracompany.catalogo.datalayer.Multimidia;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.MultimidiaTO;
import com.indracompany.catalogo.to.ParametrizacaoProdutosTO;

public interface MultimidiaDAO {
	
	public List<MultimidiaTO> find(ParametrizacaoProdutosTO pp) throws DAOException;
	
	public List<MultimidiaTO> obterMultimidiaTOList(Integer idGrupoProduto,
			Integer[] idsTiposMultimidia,
			MultimidiaTOBuilder multimidiaTOBuilder) throws DAOException;
	
	public void save(MultimidiaTO multimidiaTO) throws DAOException;
	
	public void save(Multimidia multimidia) throws DAOException;
	
	public void remove(Multimidia multimidia) throws DAOException;
	
}