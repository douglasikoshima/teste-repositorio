package com.indracompany.catalogo.dao;


import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.OfertaSAP;
import com.indracompany.catalogo.to.OfertaSAPTO;

public class OfertaSAPTOBuilder {
	
	public OfertaSAP createEntity(OfertaSAPTO to){
		OfertaSAP ent = new OfertaSAP();
		if(to != null){
			ent.setCdOfertaSAP(to.getCdOfertaSAP());
			ent.setDscOfertaSAP(to.getDsOfertaSAP());
			ent.setIdOfertaSAP(to.getIdOfertaSAP());
			ent.setInDisponivel(to.getInDisponivel());
		}
		
		return ent;
	}

	public OfertaSAPTO createTO(OfertaSAP ent){
		OfertaSAPTO to = new OfertaSAPTO();
		
		if(ent != null){
			to.setCdOfertaSAP(ent.getCdOfertaSAP());
			to.setDsOfertaSAP(ent.getDscOfertaSAP());
			to.setIdOfertaSAP(ent.getIdOfertaSAP());
			to.setInDisponivel(ent.getInDisponivel());
		}
		
		return to;
	}
	
	public List<OfertaSAPTO> createTOList(List<OfertaSAP> entList){
		List<OfertaSAPTO> toList = new ArrayList<OfertaSAPTO>(); 
		
		for(OfertaSAP ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<OfertaSAP> createEntityList(List<OfertaSAPTO> toList){
		List<OfertaSAP> entList = new ArrayList<OfertaSAP>();
		
		for(OfertaSAPTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
}