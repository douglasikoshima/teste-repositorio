package com.indracompany.catalogo.dao;

import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.datalayer.Regional;
import com.indracompany.catalogo.datalayer.Uf;
import com.indracompany.catalogo.to.RegionalTO;
import com.indracompany.catalogo.to.UfTO;

public class RegionalTOBuilder {

	private static Logger log = Logger.getLogger(RegionalTOBuilder.class);
	public static RegionalTO createTO(Regional regional){
		
		RegionalTO to = new RegionalTO();
		
		to.setIdRegional(regional.getIdRegional());
		to.setNmRegional(regional.getNmRegional());
		if(regional.getUfList() != null && !regional.getUfList().isEmpty()){
			to.setUfs(new LinkedList<UfTO>());
			for(Uf uf:regional.getUfList()){
				UfTO ufTo = new UfTO();
				ufTo.setIdUf((long)uf.getIdUf());
				ufTo.setNmUf(uf.getNmUf());
				to.getUfs().add(ufTo);
			}
		}
		return to;
	}
	
	public static List<RegionalTO> createListTO(List<Regional> list){
		
		if(list!=null && !list.isEmpty()){
			List<RegionalTO> listTO = new LinkedList<RegionalTO>();
			
			for(Regional regional:list){
			
				log.info(regional);
				listTO.add(createTO(regional));
			}
			return listTO;
		}
		return null;
	}
}
