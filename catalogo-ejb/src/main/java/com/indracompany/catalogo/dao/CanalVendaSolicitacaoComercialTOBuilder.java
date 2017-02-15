package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.CanalVenda;
import com.indracompany.catalogo.datalayer.CanalVendaSolicitacaoComercial;
import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.to.CanalVendaSolicitacaoComercialTO;

public class CanalVendaSolicitacaoComercialTOBuilder {
	
	public CanalVendaSolicitacaoComercial createEntity(CanalVendaSolicitacaoComercialTO to){
		
		CanalVendaSolicitacaoComercial ent = new CanalVendaSolicitacaoComercial();
		
		if(to != null){
			if(to.getCanalVendaTO() != null){
				ent.setCanalVenda(new CanalVenda(to.getCanalVendaTO().getIdCanalVenda()));
			}
			ent.setIdCanalVendaSolicitacaoCmrl(to.getIdCanalVendaSolicitacaoComercial());
			ent.setInDisponivel(to.getInDisponivel());
			ent.setSolicitacaoComercial(new SolicitacaoComercial(to.getIdSolicitacaoComercial()));
			ent.setInCriacaoCatalogo(to.getInCriacaoCatalogo());
		}
		
		return ent;
	}

	public CanalVendaSolicitacaoComercialTO createTO(CanalVendaSolicitacaoComercial ent){
		CanalVendaSolicitacaoComercialTO to = new CanalVendaSolicitacaoComercialTO();
		CanalVendaTOBuilder canalVendaTOBuilder = new CanalVendaTOBuilder();
		
		if(ent != null){
			if(ent.getCanalVenda() != null){
				to.setCanalVendaTO(canalVendaTOBuilder.createTO(ent.getCanalVenda()));
			}
			to.setIdCanalVendaSolicitacaoComercial(ent.getIdCanalVendaSolicitacaoCmrl());
			to.setIdSolicitacaoComercial(ent.getSolicitacaoComercial().getIdSolicitacaoComercial());
			to.setInDisponivel(ent.getInDisponivel());
			to.setInCriacaoCatalogo(ent.getInCriacaoCatalogo());
			if(ent.getAreaConcorrenciaList().isEmpty()){
				to.setInPossuiAreaConcorrencia(Boolean.FALSE);
			} else {
				to.setInPossuiAreaConcorrencia(Boolean.TRUE);
			}

			if(ent.getEspServicoPlanoMinutoList().isEmpty()){
				to.setInPossuiPlanoMinuto(Boolean.FALSE);
			} else {
				to.setInPossuiPlanoMinuto(Boolean.TRUE);
			}
		}
		
		return to;
	}
	
	public List<CanalVendaSolicitacaoComercialTO> createTOList(List<CanalVendaSolicitacaoComercial> entList){
		List<CanalVendaSolicitacaoComercialTO> toList = new ArrayList<CanalVendaSolicitacaoComercialTO>(); 
		
		for(CanalVendaSolicitacaoComercial ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<CanalVendaSolicitacaoComercial> createEntityList(List<CanalVendaSolicitacaoComercialTO> toList){
		List<CanalVendaSolicitacaoComercial> entList = new ArrayList<CanalVendaSolicitacaoComercial>();
		
		for(CanalVendaSolicitacaoComercialTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
	
	public List<Long> getIdList(List<CanalVendaSolicitacaoComercialTO> toList){
		
		List<Long> idList = new ArrayList<Long>(); 
		
		for(CanalVendaSolicitacaoComercialTO to : toList){
			idList.add(to.getIdCanalVendaSolicitacaoComercial());
		}
		return idList;
	}	
}
