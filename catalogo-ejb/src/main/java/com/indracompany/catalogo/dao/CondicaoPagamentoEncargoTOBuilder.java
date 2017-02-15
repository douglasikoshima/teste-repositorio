package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.CanalVenda;
import com.indracompany.catalogo.datalayer.CondicaoPagamento;
import com.indracompany.catalogo.datalayer.CondicaoPagamentoEncargo;
import com.indracompany.catalogo.datalayer.Encargo;
import com.indracompany.catalogo.datalayer.PoliticaDispCndcPagamento;
import com.indracompany.catalogo.to.CondicaoPagamentoEncargoTO;

public class CondicaoPagamentoEncargoTOBuilder {
	public CondicaoPagamentoEncargo createEntity(CondicaoPagamentoEncargoTO to){
		CondicaoPagamentoEncargo ent = new CondicaoPagamentoEncargo();
//		CanalVendaTOBuilder canalVendaTOBuilder = new CanalVendaTOBuilder();
//		CondicaoPagamentoTOBuilder condicaoPagamentoTOBuilder = new CondicaoPagamentoTOBuilder();
//		PoliticaDispCndcPagamentoTOBuilder politicaDispCndcPagamentoTOBuilder = new PoliticaDispCndcPagamentoTOBuilder();
		
		
		if(to != null){
			ent.setIdCondicaoPagamentoEncargo(to.getIdCondicaoPagamentoEncargo());
			ent.setVlParcela(to.getVlParcela());
			ent.setDtInicial(to.getDtInicial());
			ent.setDtFinal(to.getDtFinal());
			if(to.getIdEncargo() != null){
				ent.setEncargo(new Encargo(to.getIdEncargo()));
			}
			if(to.getCanalVendaTO() != null){
				ent.setCanalVenda(new CanalVenda(to.getCanalVendaTO().getIdCanalVenda()));
			}
			if(to.getCondicaoPagamentoTO() != null){
				ent.setCondicaoPagamento(new CondicaoPagamento(to.getCondicaoPagamentoTO().getIdCondicaoPagamento()));
			}
			if(to.getPoliticaDispCndcPagamentoTO() != null){
				ent.setPoliticaDispCndcPagamento(new PoliticaDispCndcPagamento(to.getPoliticaDispCndcPagamentoTO().getIdPoliticaDispCndcPagamento()));
			}
			ent.setInDisponivel(to.getInDisponivel());
			ent.setInCriacaoCatalogo(to.getInCriacaoCatalogo());
		}
		
		return ent;
	}

	public CondicaoPagamentoEncargoTO createTO(CondicaoPagamentoEncargo ent){
		CondicaoPagamentoEncargoTO to = new CondicaoPagamentoEncargoTO();
		CanalVendaTOBuilder canalVendaTOBuilder = new CanalVendaTOBuilder();
		CondicaoPagamentoTOBuilder condicaoPagamentoTOBuilder = new CondicaoPagamentoTOBuilder();
		PoliticaDispCndcPagamentoTOBuilder politicaDispCndcPagamentoTOBuilder = new PoliticaDispCndcPagamentoTOBuilder();
		
		if(ent != null){
			to.setIdCondicaoPagamentoEncargo(ent.getIdCondicaoPagamentoEncargo());
			to.setVlParcela(ent.getVlParcela());
			to.setDtInicial(ent.getDtInicial());
			to.setDtFinal(ent.getDtFinal());
			to.setInDisponivel(ent.getInDisponivel());
			to.setInCriacaoCatalogo(ent.getInCriacaoCatalogo());
			if(ent.getEncargo() != null){
				to.setIdEncargo(ent.getEncargo().getIdEncargo());
			}
			if(ent.getCanalVenda() != null){
				to.setCanalVendaTO(canalVendaTOBuilder.createTO(ent.getCanalVenda()));
			}
			if(ent.getCondicaoPagamento() != null){
				to.setCondicaoPagamentoTO(condicaoPagamentoTOBuilder.createCondicaoPagamentoTO(ent.getCondicaoPagamento()));
			}
			if(ent.getPoliticaDispCndcPagamento() != null){
				to.setPoliticaDispCndcPagamentoTO(politicaDispCndcPagamentoTOBuilder.createTO(ent.getPoliticaDispCndcPagamento()));
			}
			if(ent.getDisponibilidadeCndcPagamentoList() != null && !ent.getDisponibilidadeCndcPagamentoList().isEmpty()){
				if(ent.getDisponibilidadeCndcPagamentoList().get(0).getAreaConcorrencia() != null){
					to.setInPossuiAreaConcorrencia(Boolean.TRUE);
				} else {
					to.setInPossuiAreaConcorrencia(Boolean.FALSE);
				}
				if(ent.getDisponibilidadeCndcPagamentoList().get(0).getEspServicoPlanoMinuto() != null){
					to.setInPossuiPlanoMinuto(Boolean.TRUE);
				} else {
					to.setInPossuiPlanoMinuto(Boolean.FALSE);
				}
			}
		}
		
		return to;
	}
	
	public List<CondicaoPagamentoEncargoTO> createTOList(List<CondicaoPagamentoEncargo> entList){
		List<CondicaoPagamentoEncargoTO> toList = new ArrayList<CondicaoPagamentoEncargoTO>(); 
		
		for(CondicaoPagamentoEncargo ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<CondicaoPagamentoEncargo> createEntityList(List<CondicaoPagamentoEncargoTO> toList){
		List<CondicaoPagamentoEncargo> entList = new ArrayList<CondicaoPagamentoEncargo>();
		
		for(CondicaoPagamentoEncargoTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
	
	public List<Long> getIdList(List<CondicaoPagamentoEncargoTO> toList){
		
		List<Long> idList = new ArrayList<Long>(); 
		
		for(CondicaoPagamentoEncargoTO to : toList){
			idList.add(to.getIdCondicaoPagamentoEncargo());
		}
		return idList;
	}	
}
