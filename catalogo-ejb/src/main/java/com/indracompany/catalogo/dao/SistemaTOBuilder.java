package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Sistema;
import com.indracompany.catalogo.to.SistemaTO;

public class SistemaTOBuilder {
	
	public Sistema createEntity(SistemaTO to){
		
		Sistema ent = new Sistema();
		
		if(to != null){
			ent.setIdSistema(to.getIdSistema());
			ent.setDtCriacao(to.getDtCriacao());
			ent.setDtUltimaAlteracao(to.getDtUltimaAlteracao());
			ent.setNmSistema(to.getNmSistema());
			ent.setNmUsuarioAlteracao(to.getNmUsuarioAlteracao());
			ent.setNmUsuarioCriacao(to.getNmUsuarioCriacao());
		}
		
		return ent;
	}

	public SistemaTO createTO(Sistema ent){
		SistemaTO to = new SistemaTO();
		
		if(ent != null){
			to.setIdSistema(ent.getIdSistema());
			to.setDtCriacao(ent.getDtCriacao());
			to.setDtUltimaAlteracao(ent.getDtUltimaAlteracao());
			to.setNmSistema(ent.getNmSistema());
			to.setNmUsuarioAlteracao(ent.getNmUsuarioAlteracao());
			to.setNmUsuarioCriacao(ent.getNmUsuarioCriacao());
		}
		
		return to;
	}
	
	public List<SistemaTO> createTOList(List<Sistema> entList){
		List<SistemaTO> toList = new ArrayList<SistemaTO>(); 
		
		for(Sistema ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<Sistema> createEntityList(List<SistemaTO> toList){
		List<Sistema> entList = new ArrayList<Sistema>();
		
		for(SistemaTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
	
	public List<Integer> getIdList(List<SistemaTO> toList){
		
		List<Integer> idList = new ArrayList<Integer>(); 
		
		for(SistemaTO to : toList){
			idList.add(to.getIdSistema());
		}
		return idList;
	}	
}
