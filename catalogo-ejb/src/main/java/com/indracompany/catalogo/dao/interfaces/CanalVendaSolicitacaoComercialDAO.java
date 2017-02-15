package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AreaConcorrenciaTO;
import com.indracompany.catalogo.to.CanalVendaSolicitacaoComercialTO;
import com.indracompany.catalogo.to.EspServicoPlanoMinutoTO;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;

public interface CanalVendaSolicitacaoComercialDAO {
	
	public CanalVendaSolicitacaoComercialTO findById(CanalVendaSolicitacaoComercialTO canalVendaSolicitacaoComercialTO) throws DAOException;
	
	public void switchDisponibilidadeCanalVendaSlctCmrl(CanalVendaSolicitacaoComercialTO canalVendaSolicitacaoComercialTO) throws DAOException;
	
	public void updateCascadeDisponibilidadeSlctCmrlCanalVenda(ServicoSolicitacaoComercialTO to) throws DAOException;
	
	public List<AreaConcorrenciaTO> findAreaConcorrenciaListById(Long idCanalVendaSlctCmrl) throws DAOException;
	
	public List<EspServicoPlanoMinutoTO> findPlanoDeMinutosById(Long idCanalVendaSlctCmrl) throws DAOException;
}
