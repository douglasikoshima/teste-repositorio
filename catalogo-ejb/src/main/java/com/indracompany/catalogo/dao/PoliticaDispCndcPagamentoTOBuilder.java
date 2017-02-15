package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.PoliticaDispCndcPagamento;
import com.indracompany.catalogo.to.PoliticaDispCndcPagamentoTO;

public class PoliticaDispCndcPagamentoTOBuilder {
	public PoliticaDispCndcPagamento createEntity(PoliticaDispCndcPagamentoTO to){
		
		PoliticaDispCndcPagamento ent = new PoliticaDispCndcPagamento(); 
		
		if(to != null){
			ent.setIdPoliticaDispCndcPagamento(to.getIdPoliticaDispCndcPagamento());
			ent.setInAreaConcorrencia(to.getInAreaConcorrencia());
			ent.setInPlanoMinuto(to.getInPlanoMinuto());
		}
		
		return ent;
	}

	public PoliticaDispCndcPagamentoTO createTO(PoliticaDispCndcPagamento ent){
		
		PoliticaDispCndcPagamentoTO to = new PoliticaDispCndcPagamentoTO(); 
		
		if(ent != null){
			to.setIdPoliticaDispCndcPagamento(ent.getIdPoliticaDispCndcPagamento());
			to.setInAreaConcorrencia(ent.getInAreaConcorrencia());
			to.setInPlanoMinuto(ent.getInPlanoMinuto());
		}
		
		return to;
	}
	
	public List<PoliticaDispCndcPagamentoTO> createPoliticaDispCndcPagamentoTOList(List<PoliticaDispCndcPagamento> entList){
		List<PoliticaDispCndcPagamentoTO> toList = new ArrayList<PoliticaDispCndcPagamentoTO>(); 
		
		for(PoliticaDispCndcPagamento ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<PoliticaDispCndcPagamento> createPoliticaDispCndcPagamentoEntityList(List<PoliticaDispCndcPagamentoTO> toList){
		List<PoliticaDispCndcPagamento> entList = new ArrayList<PoliticaDispCndcPagamento>();
		
		for(PoliticaDispCndcPagamentoTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
}
