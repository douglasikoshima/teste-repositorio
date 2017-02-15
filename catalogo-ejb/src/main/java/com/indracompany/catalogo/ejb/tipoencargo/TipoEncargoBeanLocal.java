package com.indracompany.catalogo.ejb.tipoencargo;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.TipoEncargoTO;

@Local
public interface TipoEncargoBeanLocal {

	public static final String JNDI_NAME = "java:comp/env/TipoEncargo";
	
	public List<TipoEncargoTO> findAll() throws BusinessException;
	
}
