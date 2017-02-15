package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.TipoEncargo;
import com.indracompany.catalogo.to.TipoEncargoTO;

public class TipoEncargoTOBuilder {
	
	public TipoEncargoTO createTipoEncargoTO(TipoEncargo tipoEncargo) {
		TipoEncargoTO tipoEncargoTO = new TipoEncargoTO();
		if(tipoEncargo != null) {
			tipoEncargoTO.setCdTipoEncargo(tipoEncargo.getCdTipoEncargo());
			tipoEncargoTO.setDsTipoEncargo(tipoEncargo.getDsTipoEncargo());
			tipoEncargoTO.setIdTipoEncargo(tipoEncargo.getIdTipoEncargo());
			tipoEncargoTO.setNmTipoEncargo(tipoEncargo.getNmTipoEncargo());
		}
		return tipoEncargoTO;
	}

	public List<TipoEncargoTO> createTOList(List<TipoEncargo> entityList) {
		List<TipoEncargoTO> toList = new ArrayList<TipoEncargoTO>();
		
		for(TipoEncargo ent : entityList){
			toList.add(createTipoEncargoTO(ent));
		}
		
		return toList;
	}

}
