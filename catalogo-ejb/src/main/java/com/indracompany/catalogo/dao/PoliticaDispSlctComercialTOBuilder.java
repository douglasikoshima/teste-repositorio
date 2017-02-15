package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.PoliticaDispSlctComercial;
import com.indracompany.catalogo.to.PoliticaDispSlctComercialTO;

public class PoliticaDispSlctComercialTOBuilder {
	public PoliticaDispSlctComercial createEntity(PoliticaDispSlctComercialTO to){
		
		PoliticaDispSlctComercial ent = new PoliticaDispSlctComercial(); 
		
		if(to != null){
			ent.setIdPoliticaDispSlctComercial(to.getIdPoliticaDispSlctComercial());
			ent.setInAreaConcorrencia(to.getInAreaConcorrencia());
			ent.setInPlanoMinuto(to.getInPlanoMinuto());
		}
		
		return ent;
	}

	public PoliticaDispSlctComercialTO createTO(PoliticaDispSlctComercial ent){
		
		PoliticaDispSlctComercialTO to = new PoliticaDispSlctComercialTO(); 
		
		if(ent != null){
			to.setIdPoliticaDispSlctComercial(ent.getIdPoliticaDispSlctComercial());
			to.setInAreaConcorrencia(ent.getInAreaConcorrencia());
			to.setInPlanoMinuto(ent.getInPlanoMinuto());
		}
		
		return to;
	}
	
	public List<PoliticaDispSlctComercialTO> createPoliticaDispSlctComercialTOList(List<PoliticaDispSlctComercial> entList){
		List<PoliticaDispSlctComercialTO> toList = new ArrayList<PoliticaDispSlctComercialTO>(); 
		
		for(PoliticaDispSlctComercial ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<PoliticaDispSlctComercial> createPoliticaDispSlctComercialEntityList(List<PoliticaDispSlctComercialTO> toList){
		List<PoliticaDispSlctComercial> entList = new ArrayList<PoliticaDispSlctComercial>();
		
		for(PoliticaDispSlctComercialTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
}
