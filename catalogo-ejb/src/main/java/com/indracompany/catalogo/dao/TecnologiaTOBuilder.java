package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Tecnologia;
import com.indracompany.catalogo.to.TecnologiaTO;

/**
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class TecnologiaTOBuilder {
	
	/**
	 * @param tecnologiaTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public Tecnologia createTecnologia(TecnologiaTO tecnologiaTO) {
		
		Tecnologia tecnologia = null;
		
		if (tecnologiaTO != null) {
			tecnologia = new Tecnologia();
			tecnologia.setDtCriacao(tecnologiaTO.getDtCriacao());
			tecnologia.setDtUltimaAlteracao(tecnologiaTO.getDtUltimaAlteracao());
			tecnologia.setIdTecnologia(tecnologiaTO.getIdTecnologia());
			tecnologia.setNmTecnologia(tecnologiaTO.getNmTecnologia());
			tecnologia.setNmUsuarioAlteracao(tecnologiaTO.getNmUsuarioAlteracao());
			tecnologia.setNmUsuarioCriacao(tecnologiaTO.getNmUsuarioCriacao());
			tecnologia.setSgTecnologia(tecnologiaTO.getSgTecnologia());
		}
		
		return tecnologia;
	}
	
	/**
	 * @param tecnologia
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public TecnologiaTO createTecnologiaTO(Tecnologia tecnologia) {
		
		TecnologiaTO tecnologiaTO = null;
		
		if (tecnologia != null) {
			tecnologiaTO = new TecnologiaTO();
			tecnologiaTO.setDtCriacao(tecnologia.getDtCriacao());
			tecnologiaTO.setDtUltimaAlteracao(tecnologia.getDtUltimaAlteracao());
			tecnologiaTO.setIdTecnologia(tecnologia.getIdTecnologia());
			tecnologiaTO.setNmTecnologia(tecnologia.getNmTecnologia());
			tecnologiaTO.setNmUsuarioAlteracao(tecnologia.getNmUsuarioAlteracao());
			tecnologiaTO.setNmUsuarioCriacao(tecnologia.getNmUsuarioCriacao());
			tecnologiaTO.setSgTecnologia(tecnologia.getSgTecnologia());
			
		}
		
		return tecnologiaTO;
	}
	
	/**
	 * @param tecnologiaList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<TecnologiaTO> createTecnologiaTOList(List<Tecnologia> tecnologiaList) {
		
		List<TecnologiaTO> list = new ArrayList<TecnologiaTO>();
		
		if (tecnologiaList != null && tecnologiaList.size() > 0) {
			for (Tecnologia tecnologia : tecnologiaList) {
				list.add(createTecnologiaTO(tecnologia));
			}
		}
		
		return list;
	}

	/**
	 * @param tecnologiaTOList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de TO em uma lista de Entity.
	 */
	public List<Tecnologia> createTecnologiaList(List<TecnologiaTO> tecnologiaTOList) {
		
		List<Tecnologia> list = new ArrayList<Tecnologia>();
		
		if (tecnologiaTOList != null && tecnologiaTOList.size() > 0) {
			for (TecnologiaTO tecnologiaTO : tecnologiaTOList) {
				list.add(createTecnologia(tecnologiaTO));
			}
		}
		
		return list;
	}
	
	public List<Integer> getIdList(List<TecnologiaTO> tecnologiaTOList) {
		
		List<Integer> idList = new ArrayList<Integer>();
		
		if (tecnologiaTOList != null && !tecnologiaTOList.isEmpty()) {
			for (TecnologiaTO tecnologiaTO : tecnologiaTOList) {
				idList.add(tecnologiaTO.getIdTecnologia());
			}
		}
		
		return idList;
	}
}
