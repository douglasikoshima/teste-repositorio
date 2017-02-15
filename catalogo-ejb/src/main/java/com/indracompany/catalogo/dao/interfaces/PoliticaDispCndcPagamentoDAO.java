package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PoliticaDispCndcPagamentoTO;

public interface PoliticaDispCndcPagamentoDAO {
	
	public PoliticaDispCndcPagamentoTO findByIdServico(Integer idServico) throws DAOException;
	
	public PoliticaDispCndcPagamentoTO findById(PoliticaDispCndcPagamentoTO politicaDispCndcPagamentoTO) throws DAOException;
}
