package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.EncargoPoliticaPrecificacaoTO;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;

public interface EncargoDAO {

	public List<EncargoPoliticaPrecificacaoTO> searchBySolicitacaoComercial(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException;
	
}
