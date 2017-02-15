package com.indracompany.catalogo.dao;

import com.indracompany.catalogo.datalayer.TipoServico;
import com.indracompany.catalogo.to.TipoServicoTO;

public class TipoServicoTOBuilder extends GenericTOBuilder<TipoServico, TipoServicoTO> {
	
	@Override
	public TipoServico createEntity(TipoServicoTO to) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TipoServicoTO createTO(TipoServico entity) {
		TipoServicoTO to = null;
        if (entity != null) {
            to = new TipoServicoTO();
            to.setCdTipoServico(entity.getCdTipoServico());
            to.setDscTipoServico(entity.getDscTipoServico());
            to.setIdTipoServico(entity.getIdTipoServico());
            to.setInFixa(entity.getInFixa());
            to.setNmTipoServico(entity.getNmTipoServico());
        }
        return to;
	}
	
}