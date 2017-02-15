package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Segmento;
import com.indracompany.catalogo.to.SegmentoTO;

public class SegmentoTOBuilder {
	
	public Segmento createSegmento(SegmentoTO to){
		
		Segmento ent = new Segmento(); 
		
		if(to != null){
			
		}
		
		return ent;
	}

	public SegmentoTO createSegmentoTO(Segmento ent){
		
		SegmentoTO to = new SegmentoTO(); 
		
		if(ent != null){
			to.setIdSegmento(ent.getIdSegmento());
			to.setSgSegmento(ent.getSgSegmento());
			to.setDsSegmento(ent.getDsSegmento());
		}
		
		return to;
	}
	
	public List<SegmentoTO> createSegmentoTOList(List<Segmento> segmentoList){
		List<SegmentoTO> segmentoTOList = new ArrayList<SegmentoTO>(); 
		
		for(Segmento segmento : segmentoList){
			segmentoTOList.add(createSegmentoTO(segmento));
		}
		
		return segmentoTOList;
	}
	
	public List<Long> getIdList(List<SegmentoTO> segmentoTOList){
		
		List<Long> idList = new ArrayList<Long>(); 
		
		for(SegmentoTO segmentoTO : segmentoTOList){
			idList.add(segmentoTO.getIdSegmento());
		}
		return idList;
	}	
}
