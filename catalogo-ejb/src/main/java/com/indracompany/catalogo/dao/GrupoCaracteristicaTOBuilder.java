package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import com.indracompany.catalogo.datalayer.GrupoCaracteristica;
import com.indracompany.catalogo.to.GrupoCaracteristicaTO;

public class GrupoCaracteristicaTOBuilder {
	
	public GrupoCaracteristica createGrupoCaracteristica(GrupoCaracteristicaTO grupoCaracteristicaTO) {
		GrupoCaracteristica grupoCaracteristica = null;
		
		if (grupoCaracteristicaTO != null) {
			grupoCaracteristica = new GrupoCaracteristica();
			grupoCaracteristica.setDtAlteracao(grupoCaracteristicaTO.getDtAlteracao());
			grupoCaracteristica.setDtCriacao(grupoCaracteristicaTO.getDtCriacao());
			grupoCaracteristica.setIconeGrupoCaracteristica(grupoCaracteristicaTO.getIconeGrupoCaracteristica());
			grupoCaracteristica.setIdGrupoCaracteristica(grupoCaracteristicaTO.getIdGrupoCaracteristica());
			grupoCaracteristica.setNmGrupoCaracteristica(grupoCaracteristicaTO.getNmGrupoCaracteristica());
			grupoCaracteristica.setNmUsuarioAlteracao(grupoCaracteristicaTO.getNmUsuarioAlteracao());
			grupoCaracteristica.setNmUsuarioCriacao(grupoCaracteristicaTO.getNmUsuarioCriacao());
			
		}
		
		return grupoCaracteristica;
	}
	
	public GrupoCaracteristicaTO createGrupoCaracteristicaTO(GrupoCaracteristica grupoCaracteristica) {
		GrupoCaracteristicaTO grupoCaracteristicaTO = null;
		
		if (grupoCaracteristica != null) {
			grupoCaracteristicaTO = new GrupoCaracteristicaTO();
			grupoCaracteristicaTO.setDtAlteracao(grupoCaracteristica.getDtAlteracao());
			grupoCaracteristicaTO.setDtCriacao(grupoCaracteristica.getDtCriacao());
			grupoCaracteristicaTO.setIconeGrupoCaracteristica(grupoCaracteristica.getIconeGrupoCaracteristica());
			grupoCaracteristicaTO.setIdGrupoCaracteristica(grupoCaracteristica.getIdGrupoCaracteristica());
			grupoCaracteristicaTO.setNmGrupoCaracteristica(grupoCaracteristica.getNmGrupoCaracteristica());
			grupoCaracteristicaTO.setNmUsuarioAlteracao(grupoCaracteristica.getNmUsuarioAlteracao());
			grupoCaracteristicaTO.setNmUsuarioCriacao(grupoCaracteristica.getNmUsuarioCriacao());
			grupoCaracteristicaTO.setExistCaracteristicaAssociada((grupoCaracteristica.getCaracteristicaList() != null && grupoCaracteristica.getCaracteristicaList().size() > 0));
			
		}
		
		return grupoCaracteristicaTO;
	}	
	
	public List<GrupoCaracteristicaTO> createGrupoCaracteristicaTOList(List<GrupoCaracteristica> grupoCaracteristicaList) {
		
		List<GrupoCaracteristicaTO> list = new ArrayList<GrupoCaracteristicaTO>();
		
		if (grupoCaracteristicaList != null && grupoCaracteristicaList.size() > 0) {
			for (GrupoCaracteristica grupoCaracteristica : grupoCaracteristicaList) {
				list.add(createGrupoCaracteristicaTO(grupoCaracteristica));
			}
		}
		
		return list;
	}	
}
