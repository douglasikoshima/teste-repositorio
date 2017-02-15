package com.indracompany.catalogo.dao;

import com.indracompany.catalogo.datalayer.ComplementoLegado;
import com.indracompany.catalogo.to.ComplementoLegadoTO;

public class ComplementoLegadoTOBuilder extends GenericTOBuilder<ComplementoLegado, ComplementoLegadoTO> {
	
	@Override
	public ComplementoLegado createEntity(ComplementoLegadoTO to) {
		ComplementoLegado entity = null;
		if (to != null) {
			entity = new ComplementoLegado();
			entity.setIdSistemaServico(to.getIdSistemaServico());
			entity.setCdClasseServicoAdicional(to.getCdClasseServicoAdicional());
			entity.setCdClasseServicoPrincipal(to.getCdClasseServicoPrincipal());
			entity.setCdPS(to.getCdPS());
			entity.setCdTipoEquipamento(to.getCdTipoEquipamento());
		}
		return entity;
	}
	
	@Override
	public ComplementoLegadoTO createTO(ComplementoLegado entity) {
		ComplementoLegadoTO to = null;
		if (entity != null) {
			to = new ComplementoLegadoTO();
			to.setIdSistemaServico(entity.getIdSistemaServico());
			to.setCdClasseServicoAdicional(entity.getCdClasseServicoAdicional());
			to.setCdClasseServicoPrincipal(entity.getCdClasseServicoPrincipal());
			to.setCdPS(entity.getCdPS());
			to.setCdTipoEquipamento(entity.getCdTipoEquipamento());
		}
		return to;
	}
	
}