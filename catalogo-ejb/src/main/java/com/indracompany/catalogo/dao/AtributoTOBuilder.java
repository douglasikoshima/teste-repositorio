package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Atributo;
import com.indracompany.catalogo.to.AtributoTO;

public class AtributoTOBuilder {
	
	public Atributo createEntity(AtributoTO to){
		Atributo ent = new Atributo();
		
		if(to !=  null){
			ent.setIdAtributo(to.getIdAtributo());
			ent.setDtCriacao(to.getDtCriacao());
			ent.setDtUltimaAlteracao(to.getDtUltimaAlteracao());
			ent.setIndObrigatorio(to.getIndObrigatorio());
			ent.setMascara(to.getMascara());
			ent.setNmUsuarioAlteracao(to.getNmUsuarioAlteracao());
			ent.setNmUsuarioCriacao(to.getNmUsuarioCriacao());
			ent.setSgTipoAtributo(to.getSgTipoAtributo());
			ent.setSvcAttrDesc(to.getSvcAttrDesc());
			ent.setSvcAttrNm(to.getSvcAttrNm());
			ent.setSvcAttrSeq(to.getSvcAttrSeq());
		}
		return ent;
	}
	
	public AtributoTO createTO(Atributo ent){
		AtributoTO to = new AtributoTO();
		
		if(ent != null){
			to.setIdAtributo(ent.getIdAtributo());
			to.setDtCriacao(ent.getDtCriacao());
			to.setDtUltimaAlteracao(ent.getDtUltimaAlteracao());
			to.setIndObrigatorio(ent.getIndObrigatorio());
			to.setMascara(ent.getMascara());
			to.setNmUsuarioAlteracao(ent.getNmUsuarioAlteracao());
			to.setNmUsuarioCriacao(ent.getNmUsuarioCriacao());
			to.setSgTipoAtributo(ent.getSgTipoAtributo());
			to.setSvcAttrDesc(ent.getSvcAttrDesc());
			to.setSvcAttrNm(ent.getSvcAttrNm());
			to.setSvcAttrSeq(ent.getSvcAttrSeq());			
            to.setCdAtributo(ent.getCdAtributo());
            to.setQtMaxImaAtivacao(ent.getQtMaxImaAtivacao());
		}
		
		return to;
	}
	
	public List<AtributoTO> createTOList(List<Atributo> entList){
		List<AtributoTO> toList = new ArrayList<AtributoTO>();
		
		for(Atributo ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}

}
