package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.RegraPrioridadeAltaTO;

public interface RegraPrioridadeAltaDAO {
	
	public List<RegraPrioridadeAltaTO> buscar() throws DAOException;
	
}
