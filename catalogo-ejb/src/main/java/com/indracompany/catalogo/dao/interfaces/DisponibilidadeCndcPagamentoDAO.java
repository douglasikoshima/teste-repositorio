package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.DisponibilidadeCndcPagamentoTO;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;

public interface DisponibilidadeCndcPagamentoDAO {
	
	public List<DisponibilidadeCndcPagamentoTO> findByIdCondicaoPagamentoEncargo(DisponibilidadeCndcPagamentoTO to) throws DAOException;
	
	public void createUpdateDispArConcPlMinByIdCndcPgtoEnc(List<DisponibilidadeCndcPagamentoTO> toList) throws DAOException;
	
	public void removeByIdCndcPgtoEncargo(Long idCndcPgtoEncargo) throws DAOException;
	
	public void createConfigForPlMinArConc(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException;
	
	public void createConfigForPlMin(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException;
	
	public void createConfigForArConc(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException;

}
