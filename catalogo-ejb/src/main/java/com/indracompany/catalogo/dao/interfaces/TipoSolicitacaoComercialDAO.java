package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TipoSolicitacaoComercialTO;

public interface TipoSolicitacaoComercialDAO {
	public List<TipoSolicitacaoComercialTO> findAll() throws DAOException;
}
