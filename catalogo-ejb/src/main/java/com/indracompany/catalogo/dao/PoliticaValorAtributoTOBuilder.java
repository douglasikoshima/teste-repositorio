package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.PoliticaValorAtributo;
import com.indracompany.catalogo.to.PoliticaValorAtributoTO;

public class PoliticaValorAtributoTOBuilder {

    public PoliticaValorAtributoTO createTO(PoliticaValorAtributo ent){
		PoliticaValorAtributoTO to = new PoliticaValorAtributoTO();
		AtributoTOBuilder atributoTOBuilder = new AtributoTOBuilder();
		DominioAtributoFixaTOBuilder dominioAtributoFixaTOBuilder = new DominioAtributoFixaTOBuilder();
		
		if(ent != null){
			to.setIdPoliticaValorAtributoTO(ent.getIdPoliticaValorAtributo());
			if(ent.getDominioAtributoFixa() != null){
				to.setDominioAtributoFixaTO(dominioAtributoFixaTOBuilder.createTO(ent.getDominioAtributoFixa()));
			}
			if(ent.getAtributo() != null){
				to.setAtributoTO(atributoTOBuilder.createTO(ent.getAtributo()));
			}
		}
		
		return to;
	}
	
	public List<PoliticaValorAtributoTO> createTOList(List<PoliticaValorAtributo> entList){
		List<PoliticaValorAtributoTO> toList = new ArrayList<PoliticaValorAtributoTO>(); 
		
		for(PoliticaValorAtributo ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
/*
	public List<PoliticaValorAtributo> createEntityList(List<PoliticaValorAtributoTO> toList){
		List<PoliticaValorAtributo> entList = new ArrayList<PoliticaValorAtributo>();
		
		for(PoliticaValorAtributoTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
*/
}