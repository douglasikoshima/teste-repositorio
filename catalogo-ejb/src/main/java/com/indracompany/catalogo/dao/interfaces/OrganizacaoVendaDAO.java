package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.OrganizacaoVendaTO;

public interface OrganizacaoVendaDAO {
	public List<OrganizacaoVendaTO> findAll() throws DAOException;
}
