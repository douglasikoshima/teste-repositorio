package com.indracompany.catalogo.dao;

import com.indracompany.catalogo.datalayer.Uf;
import com.indracompany.catalogo.to.UfTO;

public class UfTOBuilder extends GenericTOBuilder<Uf, UfTO> {
	
	@Override
	public UfTO createTO(Uf uf) {
		UfTO ufTO = new UfTO();
		ufTO.setIdUf(uf.getIdUf().longValue());
		ufTO.setNmUf(uf.getNmUf());
		return ufTO;
	}

	@Override
	public Uf createEntity(UfTO to) {
		// TODO Auto-generated method stub
		return null;
	}

}
