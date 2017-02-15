package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.Gama;
import com.indracompany.catalogo.to.GamaTO;

public class GamaTOBuilder {
	
	public GamaTO buildTO(Gama entity) {
		GamaTO to = null;
		if (entity != null) {
			to = new GamaTO();
			to.setIdGama(entity.getIdGama());
			to.setNmGama(entity.getNmGama());
			to.setSgGama(entity.getSgGama());
			to.setNmUsuarioCriacao(entity.getNmUsuarioCriacao());
			to.setDtCriacao(entity.getDtCriacao());
			to.setNmUsuarioAlteracao(entity.getNmUsuarioAlteracao());
			to.setDtUltimaAlteracao(entity.getDtUltimaAlteracao());
		}
		return to;
	}
	
	public List<GamaTO> buildTOList(List<Gama> entityList) {
		List<GamaTO> toList = new ArrayList<GamaTO>();
		for (Gama entity : entityList) {
			toList.add(this.buildTO(entity));
		}
		return toList;
	}
	
}