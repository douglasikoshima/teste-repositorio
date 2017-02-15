package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.DisponibilidadeSlctComercialTO;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;

public interface DisponibilidadeSlctComercialDAO {
	
	public void createUpdate(DisponibilidadeSlctComercialTO disponibilidadeSlctComercialTO) throws DAOException;

	public void createUpdateByIdCanalVendaSlctCmrl(List<DisponibilidadeSlctComercialTO> disponibilidadeSlctComercialTOList) throws DAOException;
	
	public void removeByIdCanalVendaSlctCmrl(Long idCanalVendaSolicitacaoCmrl) throws DAOException;
	
	public void createConfigForPlMinArConc(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException;
	
	public void createConfigForPlMin(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException;
	
	public void createConfigForArConc(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException;
	
}
