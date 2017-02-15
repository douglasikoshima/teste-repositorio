package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.TipoDocumento;
import com.indracompany.catalogo.to.TipoDocumentoTO;

public class TipoDocumentoTOBuilder {
	
	public TipoDocumento createEntity(TipoDocumentoTO to){
		
		TipoDocumento ent = new TipoDocumento();
		
		if(to != null){
			ent.setIdTipoDocumento(to.getIdTipoDocumento());
			ent.setNmTipoDocumento(to.getNmTipoDocumento());
		}
		
		return ent;
	}

	public TipoDocumentoTO createTO(TipoDocumento ent){
		TipoDocumentoTO to = new TipoDocumentoTO();
		
		if(ent != null){
			to.setIdTipoDocumento(ent.getIdTipoDocumento());
			to.setNmTipoDocumento(ent.getNmTipoDocumento());
		}
		
		return to;
	}
	
	public List<TipoDocumentoTO> createTOList(List<TipoDocumento> entList){
		List<TipoDocumentoTO> toList = new ArrayList<TipoDocumentoTO>(); 
		
		for(TipoDocumento ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<TipoDocumento> createEntityList(List<TipoDocumentoTO> toList){
		List<TipoDocumento> entList = new ArrayList<TipoDocumento>();
		
		for(TipoDocumentoTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
	
	public List<Long> getIdList(List<TipoDocumentoTO> toList){
		
		List<Long> idList = new ArrayList<Long>(); 
		
		for(TipoDocumentoTO to : toList){
			idList.add(to.getIdTipoDocumento());
		}
		return idList;
	}	
}
