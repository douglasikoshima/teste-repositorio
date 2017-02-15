package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.OrganizacaoVenda;
import com.indracompany.catalogo.to.OrganizacaoVendaTO;

public class OrganizacaoVendaTOBuilder {
	
	public OrganizacaoVendaTO createTO(OrganizacaoVenda ent){
		OrganizacaoVendaTO to = new OrganizacaoVendaTO();
		
		if(ent != null){
			to.setIdOrganizacaoVendas(ent.getIdOrganizacaoVendas());
			to.setSgOrganizacaoVendas(ent.getSgOrganizacaoVendas());
		}
		
		return to;
	}
	
	public List<OrganizacaoVendaTO> createTOList(List<OrganizacaoVenda> entList){
		List<OrganizacaoVendaTO> toList = new ArrayList<OrganizacaoVendaTO>(); 
		
		for(OrganizacaoVenda ent : entList){
			toList.add(createTO(ent));
		}
		
		return toList;
	}
	
	public List<Integer> getIdList(List<OrganizacaoVendaTO> toList){
		
		List<Integer> idList = new ArrayList<Integer>(); 
		
		for(OrganizacaoVendaTO to : toList){
			idList.add(to.getIdOrganizacaoVendas());
		}
		return idList;
	}	

}