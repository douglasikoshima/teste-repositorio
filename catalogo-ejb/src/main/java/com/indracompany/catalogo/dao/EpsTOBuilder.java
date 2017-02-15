package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Eps;
import com.indracompany.catalogo.to.EpsTO;

public class EpsTOBuilder {
	
	public Eps createEntity(EpsTO to){
		
		Eps ent = null;
		
		if(to != null){
			ent = new Eps();
			ent.setIdEps(to.getIdEps());
			ent.setNmEps(to.getNmEps());
		}
		
		return ent;
	}

	public EpsTO createTO(Eps ent){
		EpsTO to = null;
		
		if(ent != null){
			to = new EpsTO();
			to.setIdEps(ent.getIdEps());
			to.setNmEps(ent.getNmEps());
		}
		
		return to;
	}
	
	public List<EpsTO> createTOList(List<Eps> entList){
		List<EpsTO> toList = new ArrayList<EpsTO>(); 
		
		for(Eps ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<Eps> createEntityList(List<EpsTO> toList){
		List<Eps> entList = new ArrayList<Eps>();
		
		for(EpsTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
	
	public List<Integer> getIdList(List<EpsTO> toList){
		
		List<Integer> idList = new ArrayList<Integer>(); 
		
		for(EpsTO to : toList){
			idList.add(to.getIdEps());
		}
		return idList;
	}	
}
