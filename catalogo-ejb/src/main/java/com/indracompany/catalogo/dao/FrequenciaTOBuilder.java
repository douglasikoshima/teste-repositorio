package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Frequencia;
import com.indracompany.catalogo.to.FrequenciaTO;

/**
 * @author Luiz Pereira
 *
 * Classe responsável em transformar TO em Entity e Entiy em TO.
 */
public class FrequenciaTOBuilder {
	
	/**
	 * @param acaoTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public Frequencia createFrequencia(FrequenciaTO frequenciaTO) {
		
		Frequencia frequencia = null;
		
		if (frequenciaTO != null) {
			frequencia = new Frequencia();
			frequencia.setIdVlFrequencia(frequenciaTO.getIdVlFrequencia());
			frequencia.setVlFrequencia(frequenciaTO.getVlFrequencia());
		}
		return frequencia;
	}
	
	/**
	 * @param acao
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public FrequenciaTO createFrequenciaTO(Frequencia frequencia) {
		
		FrequenciaTO frequenciaTO = null;
		
		if (frequencia != null) {
			frequenciaTO = new FrequenciaTO();
			frequenciaTO.setIdVlFrequencia(frequencia.getIdVlFrequencia());
			frequenciaTO.setVlFrequencia(frequencia.getVlFrequencia());
		}
		
		return frequenciaTO;
	}
	
	/**
	 * @param acaoList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<FrequenciaTO> createFrequenciaTOList(List<Frequencia> frequenciaList) {
		
		List<FrequenciaTO> list = new ArrayList<FrequenciaTO>();
		
		if (frequenciaList != null && frequenciaList.size() > 0) {
			for (Frequencia frequencia : frequenciaList) {
				list.add(createFrequenciaTO(frequencia));
			}
		}
		
		return list;
	}
}
