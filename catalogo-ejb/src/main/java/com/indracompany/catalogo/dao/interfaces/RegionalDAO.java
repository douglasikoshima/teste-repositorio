package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.to.RegionalTO;

public interface RegionalDAO {

	List<RegionalTO> findAll();
}
