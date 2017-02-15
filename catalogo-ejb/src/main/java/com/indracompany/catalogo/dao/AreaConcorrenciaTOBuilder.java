package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.AreaConcorrencia;
import com.indracompany.catalogo.to.AreaConcorrenciaTO;

public class AreaConcorrenciaTOBuilder {
	
	public AreaConcorrencia createEntity(AreaConcorrenciaTO areaConcorrenciaTO){
		
		AreaConcorrencia areaConcorrencia = new AreaConcorrencia(); 
		
		if(areaConcorrenciaTO != null){
			areaConcorrencia.setCdAreaConcorrencia(areaConcorrenciaTO.getCdAreaConcorrencia());
			areaConcorrencia.setDsAreaConcorrencia(areaConcorrenciaTO.getDsAreaConcorrencia());
			areaConcorrencia.setIdAreaConcorrencia(areaConcorrenciaTO.getIdAreaConcorrencia());
			areaConcorrencia.setNmAreaConcorrencia(areaConcorrenciaTO.getNmAreaConcorrencia());
		}
		
		return areaConcorrencia;
	}

	public AreaConcorrenciaTO createTO(AreaConcorrencia areaConcorrencia){
		
		AreaConcorrenciaTO areaConcorrenciaTO = new AreaConcorrenciaTO(); 
		
		if(areaConcorrencia != null){
			areaConcorrenciaTO.setCdAreaConcorrencia(areaConcorrencia.getCdAreaConcorrencia());
			areaConcorrenciaTO.setDsAreaConcorrencia(areaConcorrencia.getDsAreaConcorrencia());
			areaConcorrenciaTO.setIdAreaConcorrencia(areaConcorrencia.getIdAreaConcorrencia());
			areaConcorrenciaTO.setNmAreaConcorrencia(areaConcorrencia.getNmAreaConcorrencia());
		}
		
		return areaConcorrenciaTO;
	}
	
	public List<AreaConcorrenciaTO> createTOList(List<AreaConcorrencia> areaConcorrenciaList){
		
		List<AreaConcorrenciaTO> areaConcorrenciaTOList = new ArrayList<AreaConcorrenciaTO>(); 
		
		for(AreaConcorrencia areaConcorrencia : areaConcorrenciaList){
			areaConcorrenciaTOList.add(createTO(areaConcorrencia));
		}
		
		return areaConcorrenciaTOList;
	}
	
	public List<Long> getIdList(List<AreaConcorrenciaTO> toList){
		List<Long> idList = new ArrayList<Long>(); 
		
		for(AreaConcorrenciaTO to : toList){
			idList.add(to.getIdAreaConcorrencia());
		}
		
		return idList;
	}
	
}