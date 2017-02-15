package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;

public interface SolicitacaoComercialDAO {
	
	public ServicoSolicitacaoComercialTO findById(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException;
	
	public List<ServicoSolicitacaoComercialTO> search(ServicoSolicitacaoComercialTO to) throws DAOException;
	
	public void createUpdateDispAreaConcPlMin(ServicoSolicitacaoComercialTO to) throws DAOException;	
	
	public ServicoSolicitacaoComercialTO findCanalVendaBySolicitacaoComercial(ServicoSolicitacaoComercialTO to) throws DAOException;
	
	public ServicoSolicitacaoComercialTO findDispSlctCmrlByIdCnVendaSlct(ServicoSolicitacaoComercialTO to) throws DAOException;	

	public void updateCascadeDisponibilidadeSlctCmrl(ServicoSolicitacaoComercialTO to) throws DAOException;
	
	public void updateCascadeDisponibilidadeSlctCmrlCanalVenda(ServicoSolicitacaoComercialTO to) throws DAOException;
	
	public void updateDisponibilidadePlMinutosAreaConcorrencia(ServicoSolicitacaoComercialTO to) throws DAOException;
	
	public void createUpdateCanalVendaSlctCmrlList(ServicoSolicitacaoComercialTO to) throws DAOException;
	
	public void createUpdate(ServicoSolicitacaoComercialTO to) throws DAOException;
	
	public void saveSolicitacaoOfertaClienteInadimplente(List<ServicoSolicitacaoComercialTO> toList) throws DAOException;
}
