package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.VlFrequencia;
import com.indracompany.catalogo.to.VlFrequenciaTO;

public class VlFrequenciaTOBuilder {
	
	public VlFrequencia createVlFrequencia(VlFrequenciaTO vlFrequenciaTO) {
		VlFrequencia vlFrequencia = null;
		if (vlFrequenciaTO != null) {
			vlFrequencia = new VlFrequencia();
			vlFrequencia.setIdVlFrequencia(vlFrequenciaTO.getIdVlFrequencia());
			vlFrequencia.setVlFrequencia(vlFrequenciaTO.getVlFrequencia());
		}
		return vlFrequencia;
	}
	
	public VlFrequenciaTO createVlFrequenciaTO(VlFrequencia vlFrequencia) {
		VlFrequenciaTO vlFrequenciaTO = null;
		if (vlFrequencia != null) {
			vlFrequenciaTO = new VlFrequenciaTO();
			vlFrequenciaTO.setIdVlFrequencia(vlFrequencia.getIdVlFrequencia());
			vlFrequenciaTO.setVlFrequencia(vlFrequencia.getVlFrequencia());
		}
		return vlFrequenciaTO;
	}
	
	public List<VlFrequenciaTO> createVlFrequenciaTOList(List<VlFrequencia> vlFrequenciaList) {
		ArrayList<VlFrequenciaTO> vlFrequenciaTOList = new ArrayList<VlFrequenciaTO>(); 
		if (vlFrequenciaList != null && !vlFrequenciaList.isEmpty()) {
			for (VlFrequencia vlFrequencia : vlFrequenciaList) {
				vlFrequenciaTOList.add(createVlFrequenciaTO(vlFrequencia));
			}
		}
		return vlFrequenciaTOList;
	}
	
}