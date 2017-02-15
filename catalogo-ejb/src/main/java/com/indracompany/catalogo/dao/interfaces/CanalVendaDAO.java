package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.CanalVenda;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalVendaTO;

public interface CanalVendaDAO {
	
	public List<CanalVendaTO> findAll() throws DAOException;
	
	public List<CanalVendaTO> findByIdEncargo(Long idEncargo)  throws DAOException;
	
	public List<CanalVendaTO> pesquisar(CanalVendaTO canalVendaTO) throws DAOException;
	
	public List<CanalVendaTO> findAllInIdList(List<CanalVendaTO> canalVendaTOList, String inDisponibilidade) throws DAOException;
	
	public List<CanalVendaTO> findAllNotInIdList(List<Long> idList, String inDisponibilidade) throws DAOException;
	
	public CanalVendaTO createUpdate(CanalVendaTO canalVendaTO) throws DAOException;
	
	public void remover(CanalVendaTO canalVendaTO) throws DAOException;
	
	public CanalVendaTO pesquisarPorNmCanalVenda(CanalVendaTO canalVendaTO) throws DAOException;
	
	public CanalVendaTO pesquisarPorIdCanalVenda(CanalVendaTO canalVendaTO) throws DAOException;
	
	public Boolean possuiAssociacao(CanalVendaTO canalVendaTO) throws DAOException;
	
	public void associarEps(List<Long> canalVendaIdList, Integer idEps) throws DAOException;

	public void desAssociarEps(List<Long> canalVendaIdList) throws DAOException;
	
	public List<CanalVendaTO> findByIdEps(Integer idEps) throws DAOException;
	
	public CanalVenda findById(Long idCanalVenda) throws DAOException;
	
	public List<CanalVendaTO> searchCanalVendaTO(CanalVendaTO canalVendaTO) throws DAOException;
	
}
