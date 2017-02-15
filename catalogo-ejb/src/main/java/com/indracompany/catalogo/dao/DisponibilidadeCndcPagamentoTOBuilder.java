package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.CondicaoPagamentoEncargo;
import com.indracompany.catalogo.datalayer.DisponibilidadeCndcPagamento;
import com.indracompany.catalogo.to.DisponibilidadeCndcPagamentoTO;

public class DisponibilidadeCndcPagamentoTOBuilder {
	
	public DisponibilidadeCndcPagamento createEntity(DisponibilidadeCndcPagamentoTO to){
		AreaConcorrenciaTOBuilder areaConcorrenciaTOBuilder = new AreaConcorrenciaTOBuilder();
		EspServicoPlanoMinutoTOBuilder espServicoPlanoMinutoTOBuilder = new EspServicoPlanoMinutoTOBuilder();
		PoliticaDispCndcPagamentoTOBuilder politicaDispCndcPagamentoTOBuilder = new PoliticaDispCndcPagamentoTOBuilder();
		DisponibilidadeCndcPagamento ent = new DisponibilidadeCndcPagamento();
		
		if(to != null){
			ent.setIdDisponibilidadeCndcPagamento(to.getIdDisponibilidadeCndcPagamento());
			ent.setAreaConcorrencia(areaConcorrenciaTOBuilder.createEntity(to.getAreaConcorrenciaTO()));
			ent.setEspServicoPlanoMinuto(espServicoPlanoMinutoTOBuilder.createEntity(to.getEspServicoPlanoMinutoTO()));
			ent.setCondicaoPagamentoEncargo(new CondicaoPagamentoEncargo(to.getIdCondicaoPagamentoEncargo()));
			ent.setPoliticaDispCndcPagamento(politicaDispCndcPagamentoTOBuilder.createEntity(to.getPoliticaDispCndcPagamentoTO()));
		}
		
		return ent;
	}

	public DisponibilidadeCndcPagamentoTO createTO(DisponibilidadeCndcPagamento ent){
		AreaConcorrenciaTOBuilder areaConcorrenciaTOBuilder = new AreaConcorrenciaTOBuilder();
		EspServicoPlanoMinutoTOBuilder espServicoPlanoMinutoTOBuilder = new EspServicoPlanoMinutoTOBuilder();
		PoliticaDispCndcPagamentoTOBuilder politicaDispCndcPagamentoTOBuilder = new PoliticaDispCndcPagamentoTOBuilder();
		DisponibilidadeCndcPagamentoTO to = new DisponibilidadeCndcPagamentoTO();
		
		if(ent != null){
			to.setIdDisponibilidadeCndcPagamento(ent.getIdDisponibilidadeCndcPagamento());
			to.setAreaConcorrenciaTO(areaConcorrenciaTOBuilder.createTO(ent.getAreaConcorrencia()));
			to.setEspServicoPlanoMinutoTO(espServicoPlanoMinutoTOBuilder.createTO(ent.getEspServicoPlanoMinuto()));
			to.setIdCondicaoPagamentoEncargo(ent.getCondicaoPagamentoEncargo().getIdCondicaoPagamentoEncargo());
			to.setPoliticaDispCndcPagamentoTO(politicaDispCndcPagamentoTOBuilder.createTO(ent.getPoliticaDispCndcPagamento()));
		}
		
		return to;
	}
	
	public List<DisponibilidadeCndcPagamentoTO> createTOList(List<DisponibilidadeCndcPagamento> entList){
		List<DisponibilidadeCndcPagamentoTO> toList = new ArrayList<DisponibilidadeCndcPagamentoTO>(); 
		
		for(DisponibilidadeCndcPagamento ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<DisponibilidadeCndcPagamento> createEntityList(List<DisponibilidadeCndcPagamentoTO> toList){
		List<DisponibilidadeCndcPagamento> entList = new ArrayList<DisponibilidadeCndcPagamento>();
		
		for(DisponibilidadeCndcPagamentoTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
	
	public List<Long> getIdList(List<DisponibilidadeCndcPagamentoTO> toList){
		
		List<Long> idList = new ArrayList<Long>(); 
		
		for(DisponibilidadeCndcPagamentoTO to : toList){
			idList.add(to.getIdDisponibilidadeCndcPagamento());
		}
		return idList;
	}	

}
