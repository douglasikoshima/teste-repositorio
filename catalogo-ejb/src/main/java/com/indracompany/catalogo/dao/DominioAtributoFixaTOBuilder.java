package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.DominioAtributoFixa;
import com.indracompany.catalogo.to.DominioAtributoFixaTO;

public class DominioAtributoFixaTOBuilder {

    public DominioAtributoFixaTO createTO(DominioAtributoFixa ent){
		DominioAtributoFixaTO to = new DominioAtributoFixaTO();
		
		if(ent != null){
			to.setIdDominioAtributoFixa(ent.getIdDominioAtributoFixa());
			to.setTxValorDominioAtributoFixa(ent.getTxValorDominioQualificador());
			to.setDsDominioAtributoFixa(ent.getDsDominioQualificador());
		}
		
		return to;
	}
	
	public List<DominioAtributoFixaTO> createTOList(List<DominioAtributoFixa> entList){
		List<DominioAtributoFixaTO> toList = new ArrayList<DominioAtributoFixaTO>(); 
		
		for(DominioAtributoFixa ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
/*
	public List<DominioAtributoFixa> createEntityList(List<DominioAtributoFixaTO> toList){
		List<DominioAtributoFixa> entList = new ArrayList<DominioAtributoFixa>();
		
		for(DominioAtributoFixaTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
*/
	public List<Long> getIdList(List<DominioAtributoFixaTO> toList){
		
		List<Long> idList = new ArrayList<Long>(); 
		
		for(DominioAtributoFixaTO to : toList){
			idList.add(to.getIdDominioAtributoFixa());
		}
		return idList;
	}	

	
}
