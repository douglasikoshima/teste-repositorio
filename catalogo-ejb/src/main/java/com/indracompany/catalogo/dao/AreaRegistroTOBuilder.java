package com.indracompany.catalogo.dao;

import com.indracompany.catalogo.datalayer.AreaRegistro;
import com.indracompany.catalogo.to.AreaRegistroTO;

public class AreaRegistroTOBuilder extends GenericTOBuilder<AreaRegistro, AreaRegistroTO> {

	@Override
	public AreaRegistro createEntity(AreaRegistroTO to) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
    public AreaRegistroTO createTO(AreaRegistro entity) {
        AreaRegistroTO to = null;
        if (entity != null) {
            to = new AreaRegistroTO();
            to.setCodigoArea(entity.getCodigoArea());
            to.setIdAreaRegistro(entity.getIdarearegistro());
            to.setUfTO(new UfTOBuilder().createTO(entity.getUf()));
        }
        return to;
    }

}
