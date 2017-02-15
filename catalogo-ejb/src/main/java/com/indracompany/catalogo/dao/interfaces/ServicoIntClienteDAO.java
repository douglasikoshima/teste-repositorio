package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.ServicoIntCliente;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoIntClienteTO;

public interface ServicoIntClienteDAO {
	
	public List<ServicoIntCliente> findByIdServicoInteratividade (Integer idServicoInteratividade) throws DAOException;
	
	public void createUpdateServicoIntCliente(ServicoIntClienteTO servicoIntClienteTO) throws DAOException;
	
	public void removeServIntClienteById (Integer idServicoInteratividade) throws DAOException;

}
