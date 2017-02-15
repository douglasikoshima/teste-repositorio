package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Tecnologia;
import com.indracompany.catalogo.datalayer.TecnologiaTpFrequencia;
import com.indracompany.catalogo.datalayer.TipoFrequencia;
import com.indracompany.catalogo.to.TecnologiaTO;
import com.indracompany.catalogo.to.TecnologiaTpFrequenciaTO;
import com.indracompany.catalogo.to.TipoFrequenciaTO;

/**
 * @author gmuniz
 * 
 */
public class TecnologiaTpFrequenciaTOBuilder {
	
	private boolean criarTecnologia;
	private boolean criarTipoFrequencia;
	
	public TecnologiaTpFrequenciaTOBuilder() {
		this(true);
	}
	
	public TecnologiaTpFrequenciaTOBuilder(boolean criar) {
		criarTecnologia = criar;
		criarTipoFrequencia = criar;
	}
	
	/**
	 * @param tecnologiaTpFrequenciaTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public TecnologiaTpFrequencia createTecnologiaTpFrequencia(TecnologiaTpFrequenciaTO tecnologiaTpFrequenciaTO) {
		TecnologiaTpFrequencia tecnologiaTpFrequencia = null;
		if (tecnologiaTpFrequenciaTO != null) {
			
			tecnologiaTpFrequencia = new TecnologiaTpFrequencia();
			
			TecnologiaTO tecnologiaTO = tecnologiaTpFrequenciaTO.getTecnologiaTO();
			if (tecnologiaTO != null) {
				tecnologiaTpFrequencia.setTecnologia(new Tecnologia(tecnologiaTO.getIdTecnologia()));
			}
			
			TipoFrequenciaTO tipoFrequenciaTO = tecnologiaTpFrequenciaTO.getTipoFrequenciaTO();
			if (tipoFrequenciaTO != null) {
				tecnologiaTpFrequencia.setTipoFrequencia(new TipoFrequencia(tipoFrequenciaTO.getIdTipoFrequencia()));
			}
		}
		return tecnologiaTpFrequencia;
	}
	
	/**
	 * @param tecnologiaTpFrequencia
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public TecnologiaTpFrequenciaTO createTecnologiaTpFrequenciaTO(TecnologiaTpFrequencia tecnologiaTpFrequencia) {
		TecnologiaTpFrequenciaTO tecnologiaTpFrequenciaTO = null;
		
		if (tecnologiaTpFrequencia != null) {
			tecnologiaTpFrequenciaTO = new TecnologiaTpFrequenciaTO();
			tecnologiaTpFrequenciaTO.setIdTecnologiaTpFrequencia(tecnologiaTpFrequencia.getIdTecnologiaTpFrequencia());
			
			if (criarTecnologia) {
				TecnologiaTOBuilder tecnologiaTOBuilder = new TecnologiaTOBuilder();
				Tecnologia tecnologia = tecnologiaTpFrequencia.getTecnologia();
				TecnologiaTO tecnologiaTO = tecnologiaTOBuilder.createTecnologiaTO(tecnologia);
				tecnologiaTpFrequenciaTO.setTecnologiaTO(tecnologiaTO);
			}
			
			if (criarTipoFrequencia) {
				TipoFrequenciaTOBuilder tipoFrequenciaTOBuilder = new TipoFrequenciaTOBuilder();
				TipoFrequencia tipoFrequencia = tecnologiaTpFrequencia.getTipoFrequencia();
				TipoFrequenciaTO tipoFrequenciaTO = tipoFrequenciaTOBuilder.createTipoFrequenciaTO(tipoFrequencia);
				tecnologiaTpFrequenciaTO.setTipoFrequenciaTO(tipoFrequenciaTO);
			}
		}
		
		return tecnologiaTpFrequenciaTO;
	}
	
	/**
	 * @param tecnologiaTpFrequenciaList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<TecnologiaTpFrequenciaTO> createTecnologiaTpFrequenciaTOList(List<TecnologiaTpFrequencia> tecnologiaTpFrequenciaList) {
		List<TecnologiaTpFrequenciaTO> list = new ArrayList<TecnologiaTpFrequenciaTO>();
		
		if (tecnologiaTpFrequenciaList != null && !tecnologiaTpFrequenciaList.isEmpty()) {
			for (TecnologiaTpFrequencia tecnologiaTpFrequencia : tecnologiaTpFrequenciaList) {
				list.add(createTecnologiaTpFrequenciaTO(tecnologiaTpFrequencia));
			}
		}
		
		return list;
	}
	
	public boolean isCriarTecnologia() {
		return criarTecnologia;
	}
	
	public void setCriarTecnologia(boolean criarTecnologia) {
		this.criarTecnologia = criarTecnologia;
	}
	
	public boolean isCriarTipoFrequencia() {
		return criarTipoFrequencia;
	}
	
	public void setCriarTipoFrequencia(boolean criarTipoFrequencia) {
		this.criarTipoFrequencia = criarTipoFrequencia;
	}
	
}