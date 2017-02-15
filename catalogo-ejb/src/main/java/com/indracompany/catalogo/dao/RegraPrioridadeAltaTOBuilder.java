package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.RegraPrioridadeAlta;
import com.indracompany.catalogo.datalayer.TipoAlta;
import com.indracompany.catalogo.to.RegraPrioridadeAltaTO;

public class RegraPrioridadeAltaTOBuilder {
	
	public RegraPrioridadeAlta createEntity(RegraPrioridadeAltaTO to){
		RegraPrioridadeAlta ent = new RegraPrioridadeAlta();
		
		if(to != null){
			ent.setIdRegraPrioridadeAlta(to.getIdRegraPrioridadeAlta());
			ent.setDsRegraAlta(to.getDsRegraAlta());
			ent.setIdRegraPrioridadeAlta(to.getIdRegraPrioridadeAlta());
			if(to.getIdTipoAlta() != null) {
				TipoAlta tipoAlta = new TipoAlta();
				tipoAlta.setIdTipoAlta(to.getIdTipoAlta());
				tipoAlta.setDsTipoAlta(to.getDsTipoAlta());
			}
		}
		
		return ent;
	}

	public RegraPrioridadeAltaTO createTO(RegraPrioridadeAlta ent){
		RegraPrioridadeAltaTO to = new RegraPrioridadeAltaTO();
		
		if(ent != null){
			to.setIdRegraPrioridadeAlta(ent.getIdRegraPrioridadeAlta());
			to.setCdPrioridade(ent.getCdPrioridade());
			to.setDsRegraAlta(ent.getDsRegraAlta());
			TipoAlta tipoAlta = ent.getTipoAlta();
			if(tipoAlta != null){
				to.setIdTipoAlta(tipoAlta.getIdTipoAlta());
				to.setDsTipoAlta(tipoAlta.getDsTipoAlta());
			}
		}
		
		return to;
	}
	
	public List<RegraPrioridadeAltaTO> createTOList(List<RegraPrioridadeAlta> entList){
		List<RegraPrioridadeAltaTO> toList = new ArrayList<RegraPrioridadeAltaTO>(); 
		
		for(RegraPrioridadeAlta ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<RegraPrioridadeAlta> createEntityList(List<RegraPrioridadeAltaTO> toList){
		List<RegraPrioridadeAlta> entList = new ArrayList<RegraPrioridadeAlta>();
		
		for(RegraPrioridadeAltaTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
	
	public List<Long> getIdList(List<RegraPrioridadeAltaTO> toList){
		
		List<Long> idList = new ArrayList<Long>(); 
		
		for(RegraPrioridadeAltaTO to : toList){
			idList.add(to.getIdRegraPrioridadeAlta());
		}
		return idList;
	}	
}
