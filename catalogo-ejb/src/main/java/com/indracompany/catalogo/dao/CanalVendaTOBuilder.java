package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.CanalVenda;
import com.indracompany.catalogo.to.CanalVendaTO;

public class CanalVendaTOBuilder extends GenericTOBuilder<CanalVenda, CanalVendaTO> {
	
	private EpsTOBuilder epsTOBuilder = new EpsTOBuilder();
	
	@Override
	public CanalVenda createEntity(CanalVendaTO canalVendaTO){
		CanalVenda canalVenda = new CanalVenda();
		if (canalVendaTO != null) {
			canalVenda.setCdCanalVenda(canalVendaTO.getCdCanalVenda());
			canalVenda.setDsCanalVenda(canalVendaTO.getDsCanalVenda());
			canalVenda.setIdCanalVenda(canalVendaTO.getIdCanalVenda());
			canalVenda.setNmCanalVenda(canalVendaTO.getNmCanalVenda());
			canalVenda.setInDisponivel(canalVendaTO.getInDisponivel());
			canalVenda.setInCriacaoCatalogo(canalVendaTO.getInCriacaoCatalogo());
			canalVenda.setEps(epsTOBuilder.createEntity(canalVendaTO.getEpsTO()));
		}
		return canalVenda;
	}
	
	@Override
	public CanalVendaTO createTO(CanalVenda canalVenda){
		CanalVendaTO canalVendaTO = new CanalVendaTO();
		if (canalVenda != null) {
			canalVendaTO.setCdCanalVenda(canalVenda.getCdCanalVenda());
			canalVendaTO.setDsCanalVenda(canalVenda.getDsCanalVenda());
			canalVendaTO.setIdCanalVenda(canalVenda.getIdCanalVenda());
			canalVendaTO.setNmCanalVenda(canalVenda.getNmCanalVenda());
			canalVendaTO.setInDisponivel(canalVenda.getInDisponivel());
			canalVendaTO.setInCriacaoCatalogo(canalVenda.getInCriacaoCatalogo());
			canalVendaTO.setEpsTO(epsTOBuilder.createTO(canalVenda.getEps()));
		}
		return canalVendaTO;
	}
	
	public List<Long> getIdList(List<CanalVendaTO> canalVendaTOList){
		List<Long> idList = new ArrayList<Long>(); 
		for(CanalVendaTO canalVendaTO : canalVendaTOList) {
			idList.add(canalVendaTO.getIdCanalVenda());
		}
		return idList;
	}
	
}