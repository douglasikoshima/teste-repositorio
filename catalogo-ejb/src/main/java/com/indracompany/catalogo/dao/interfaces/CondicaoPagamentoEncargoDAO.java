package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CondicaoPagamentoEncargoTO;

public interface CondicaoPagamentoEncargoDAO {

	public CondicaoPagamentoEncargoTO findById(CondicaoPagamentoEncargoTO to) throws DAOException;
	
	public List<CondicaoPagamentoEncargoTO> search(CondicaoPagamentoEncargoTO to) throws DAOException;
	
	public void updateCascadeDisponibilidadeCndcPgtoEncargo(CondicaoPagamentoEncargoTO to) throws DAOException;
	
	public void createUpdate(CondicaoPagamentoEncargoTO to) throws DAOException;
	
	public Boolean existAssociation(CondicaoPagamentoEncargoTO to) throws DAOException;
}
