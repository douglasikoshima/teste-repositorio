package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.CanalVendaSolicitacaoComercial;
import com.indracompany.catalogo.datalayer.DisponibilidadeSlctComercial;
import com.indracompany.catalogo.datalayer.PoliticaDispSlctComercial;
import com.indracompany.catalogo.to.DisponibilidadeSlctComercialTO;
import com.indracompany.catalogo.to.EspServicoPlanoMinutoTO;

public class DisponibilidadeSlctComercialTOBuilder {
	
	public DisponibilidadeSlctComercial createEntity(DisponibilidadeSlctComercialTO to){
		AreaConcorrenciaTOBuilder areaConcorrenciaTOBuilder = new AreaConcorrenciaTOBuilder();
		EspServicoPlanoMinutoTOBuilder espServicoPlanoMinutoTOBuilder = new EspServicoPlanoMinutoTOBuilder();
		DisponibilidadeSlctComercial ent = new DisponibilidadeSlctComercial();
		
		if(to != null){
			ent.setIdDisponibilidadeSlctComercial(to.getIdDisponibilidadeSlctComercial());
			if(to.getAreaConcorrenciaTO() != null){
				ent.setAreaConcorrencia(areaConcorrenciaTOBuilder.createEntity(to.getAreaConcorrenciaTO()));
			}
			if(to.getEspServicoPlanoMinutoTO() != null){
				ent.setEspServicoPlanoMinuto(espServicoPlanoMinutoTOBuilder.createEntity(to.getEspServicoPlanoMinutoTO()));
			}
			ent.setCanalVendaSolicitacaoComercial(new CanalVendaSolicitacaoComercial(to.getIdCanalVendaSolicitacaoComercial()));
			ent.setPoliticaDispSlctComercial(new PoliticaDispSlctComercial(to.getIdPoliticaDispSlctComercial()));
		}
		
		return ent;
	}

	public DisponibilidadeSlctComercialTO createTO(DisponibilidadeSlctComercial ent){
		AreaConcorrenciaTOBuilder areaConcorrenciaTOBuilder = new AreaConcorrenciaTOBuilder();		
		DisponibilidadeSlctComercialTO to = new DisponibilidadeSlctComercialTO();
		
		if(ent != null){
			to.setIdDisponibilidadeSlctComercial(ent.getIdDisponibilidadeSlctComercial());
			to.setAreaConcorrenciaTO(areaConcorrenciaTOBuilder.createTO(ent.getAreaConcorrencia()));
			if(ent.getEspServicoPlanoMinuto() != null){
				to.setEspServicoPlanoMinutoTO(new EspServicoPlanoMinutoTO(
						ent.getEspServicoPlanoMinuto().getIdServico()
						,ent.getEspServicoPlanoMinuto().getQtMinutoLivreFMLocal()
						,ent.getEspServicoPlanoMinuto().getQtMinutoLivreFFLocal()
						,ent.getEspServicoPlanoMinuto().getVlMinutoAdicionalFFlocal()
						,ent.getEspServicoPlanoMinuto().getServico().getNmComercial()
				));
			}
			to.setIdCanalVendaSolicitacaoComercial(ent.getCanalVendaSolicitacaoComercial().getIdCanalVendaSolicitacaoCmrl());
			to.setIdPoliticaDispSlctComercial(ent.getPoliticaDispSlctComercial().getIdPoliticaDispSlctComercial());
			to.setInAreaConcorrenciaSolicitacaoComercial(ent.getPoliticaDispSlctComercial().getInAreaConcorrencia());
			to.setInPlanoMinutoSolicitacaoComercial(ent.getPoliticaDispSlctComercial().getInPlanoMinuto());
		}
		
		return to;
	}
	
	public List<DisponibilidadeSlctComercialTO> createTOList(List<DisponibilidadeSlctComercial> entList){
		List<DisponibilidadeSlctComercialTO> toList = new ArrayList<DisponibilidadeSlctComercialTO>(); 
		
		for(DisponibilidadeSlctComercial ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<DisponibilidadeSlctComercial> createEntityList(List<DisponibilidadeSlctComercialTO> toList){
		List<DisponibilidadeSlctComercial> entList = new ArrayList<DisponibilidadeSlctComercial>();
		
		for(DisponibilidadeSlctComercialTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
	
	public List<Long> getIdList(List<DisponibilidadeSlctComercialTO> toList){
		
		List<Long> idList = new ArrayList<Long>(); 
		
		for(DisponibilidadeSlctComercialTO to : toList){
			idList.add(to.getIdDisponibilidadeSlctComercial());
		}
		return idList;
	}	

}
