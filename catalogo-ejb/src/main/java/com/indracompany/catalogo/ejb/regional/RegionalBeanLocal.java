package com.indracompany.catalogo.ejb.regional;

import java.util.List;

import javax.ejb.Local;

import com.indracompany.catalogo.to.RegionalTO;

@Local
public interface RegionalBeanLocal {

	public static final String JNDI_NAME = "java:comp/env/RegionalBean";
	
	List<RegionalTO> findAll();
}
