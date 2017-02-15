package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.TecnologiaTpFrequencia;
import com.indracompany.catalogo.datalayer.TecnologiaTpFrequenciaVl;
import com.indracompany.catalogo.datalayer.VlFrequencia;
import com.indracompany.catalogo.to.TecnologiaTpFrequenciaTO;
import com.indracompany.catalogo.to.TecnologiaTpFrequenciaVlTO;
import com.indracompany.catalogo.to.VlFrequenciaTO;

/**
 * @author gmuniz
 * 
 */
public class TecnologiaTpFrequenciaVlTOBuilder {
	
	private boolean criarTecnologiaTpFrequencia;
	private boolean criarVlFrequencia;
	
	public TecnologiaTpFrequenciaVlTOBuilder() {
		this(true);
	}
	
	public TecnologiaTpFrequenciaVlTOBuilder(boolean criar) {
		criarTecnologiaTpFrequencia = criar;
		criarVlFrequencia = criar;
	}
	
	/**
	 * @param tecnologiaTpFrequenciaVlTO
	 * @return
	 * 
	 *  Método responsável em transformar um TO em um Entity.
	 */
	public TecnologiaTpFrequenciaVl createTecnologiaTpFrequenciaVl(TecnologiaTpFrequenciaVlTO tecnologiaTpFrequenciaVlTO) {
		TecnologiaTpFrequenciaVl tecnologiaTpFrequenciaVl = null;
		if (tecnologiaTpFrequenciaVlTO != null) {
			
			tecnologiaTpFrequenciaVl = new TecnologiaTpFrequenciaVl();
			
			TecnologiaTpFrequenciaTO tecnologiaTpFrequenciaTO = tecnologiaTpFrequenciaVlTO.getTecnologiaTpFrequenciaTO();
			if (tecnologiaTpFrequenciaTO != null) {
				tecnologiaTpFrequenciaVl.setTecnologiaTpFrequencia(new TecnologiaTpFrequencia(tecnologiaTpFrequenciaTO.getIdTecnologiaTpFrequencia()));
			}
			
			VlFrequenciaTO vlFrequenciaTO = tecnologiaTpFrequenciaVlTO.getVlFrequenciaTO();
			if (vlFrequenciaTO != null) {
				tecnologiaTpFrequenciaVl.setVlFrequencia(new VlFrequencia(vlFrequenciaTO.getIdVlFrequencia()));
			}
		}
		return tecnologiaTpFrequenciaVl;
	}
	
	/**
	 * @param tecnologiaTpFrequenciaVl
	 * @return
	 * 
	 * Método responsável em transformar um Entity em um TO.
	 */
	public TecnologiaTpFrequenciaVlTO createTecnologiaTpFrequenciaVlTO(TecnologiaTpFrequenciaVl tecnologiaTpFrequenciaVl) {
		TecnologiaTpFrequenciaVlTO tecnologiaTpFrequenciaVlTO = null;
		
		if (tecnologiaTpFrequenciaVl != null) {
			tecnologiaTpFrequenciaVlTO = new TecnologiaTpFrequenciaVlTO();
			tecnologiaTpFrequenciaVlTO.setIdTecnologiaTpFrequenciaVl(tecnologiaTpFrequenciaVl.getIdTecnologiaTpFrequenciaVl());
			
			if (criarTecnologiaTpFrequencia) {
				TecnologiaTpFrequenciaTOBuilder tecnologiaTpFrequenciaTOBuilder = new TecnologiaTpFrequenciaTOBuilder();
				TecnologiaTpFrequencia tecnologiaTpFrequencia = tecnologiaTpFrequenciaVl.getTecnologiaTpFrequencia();
				TecnologiaTpFrequenciaTO tecnologiaTpFrequenciaTO = tecnologiaTpFrequenciaTOBuilder.createTecnologiaTpFrequenciaTO(tecnologiaTpFrequencia);
				tecnologiaTpFrequenciaVlTO.setTecnologiaTpFrequenciaTO(tecnologiaTpFrequenciaTO);
			}
			
			if (criarVlFrequencia) {
				VlFrequenciaTOBuilder vlFrequenciaTOBuilder = new VlFrequenciaTOBuilder();
				VlFrequencia vlFrequencia = tecnologiaTpFrequenciaVl.getVlFrequencia();
				VlFrequenciaTO vlFrequenciaTO = vlFrequenciaTOBuilder.createVlFrequenciaTO(vlFrequencia);
				tecnologiaTpFrequenciaVlTO.setVlFrequenciaTO(vlFrequenciaTO);
			}
		}
		
		return tecnologiaTpFrequenciaVlTO;
	}
	
	/**
	 * @param tecnologiaTpFrequenciaVlList
	 * @return
	 * 
	 * Método responsável em transformar uma lista de Entity em uma lista de TO.
	 */
	public List<TecnologiaTpFrequenciaVlTO> createTecnologiaTpFrequenciaVlTOList(List<TecnologiaTpFrequenciaVl> tecnologiaTpFrequenciaVlList) {
		List<TecnologiaTpFrequenciaVlTO> list = new ArrayList<TecnologiaTpFrequenciaVlTO>();
		
		if (tecnologiaTpFrequenciaVlList != null && !tecnologiaTpFrequenciaVlList.isEmpty()) {
			for (TecnologiaTpFrequenciaVl tecnologiaTpFrequenciaVl : tecnologiaTpFrequenciaVlList) {
				list.add(createTecnologiaTpFrequenciaVlTO(tecnologiaTpFrequenciaVl));
			}
		}
		
		return list;
	}
	
	public boolean isCriarTecnologiaTpFrequencia() {
		return criarTecnologiaTpFrequencia;
	}
	
	public void setCriarTecnologiaTpFrequencia(boolean criarTecnologia) {
		this.criarTecnologiaTpFrequencia = criarTecnologia;
	}
	
	public boolean isCriarVlFrequencia() {
		return criarVlFrequencia;
	}
	
	public void setCriarVlFrequencia(boolean criarTipoFrequencia) {
		this.criarVlFrequencia = criarTipoFrequencia;
	}
	
}