package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.EspServicoPlanoMinuto;
import com.indracompany.catalogo.to.EspServicoPlanoMinutoTO;

public class EspServicoPlanoMinutoTOBuilder {
	
	public EspServicoPlanoMinuto createEntity(EspServicoPlanoMinutoTO to){
		EspServicoPlanoMinuto ent = new EspServicoPlanoMinuto();
		
		if(to != null){
			ent.setIdServico(to.getIdServico());
			ent.setQtMinutoLivreFFLocal(to.getQtMinutoLivreFFLocal());
			ent.setQtMinutoLivreFMLocal(to.getQtMinutoLivreFMLocal());
			ent.setVlMinutoAdicionalFFlocal(to.getVlMinutoAdicionalFFlocal());
		}
		
		return ent;
	}

	public EspServicoPlanoMinutoTO createTO(EspServicoPlanoMinuto ent){
		EspServicoPlanoMinutoTO to = new EspServicoPlanoMinutoTO();
		
		if(ent != null){
			to.setIdServico(ent.getIdServico());
			to.setNmServico(ent.getServico().getNmComercial());
			to.setQtMinutoLivreFFLocal(ent.getQtMinutoLivreFFLocal());
			to.setQtMinutoLivreFMLocal(ent.getQtMinutoLivreFMLocal());
			to.setVlMinutoAdicionalFFlocal(ent.getVlMinutoAdicionalFFlocal());
		}
		
		return to;
	}
	
	public List<EspServicoPlanoMinutoTO> createTOList(List<EspServicoPlanoMinuto> entList){
		List<EspServicoPlanoMinutoTO> toList = new ArrayList<EspServicoPlanoMinutoTO>(); 
		
		for(EspServicoPlanoMinuto ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<EspServicoPlanoMinuto> createEntityList(List<EspServicoPlanoMinutoTO> toList){
		List<EspServicoPlanoMinuto> entList = new ArrayList<EspServicoPlanoMinuto>();
		
		for(EspServicoPlanoMinutoTO to : toList){
			entList.add(createEntity(to));
		}
		
		return entList;
	}
	
	public List<Integer> getIdList(List<EspServicoPlanoMinutoTO> toList){
		
		List<Integer> idList = new ArrayList<Integer>(); 
		
		for(EspServicoPlanoMinutoTO to : toList){
			idList.add(to.getIdServico());
		}
		return idList;
	}

}
