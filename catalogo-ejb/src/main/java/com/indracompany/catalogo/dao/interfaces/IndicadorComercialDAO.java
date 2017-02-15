package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.IndicadorComercialDocumentoTO;
import com.indracompany.catalogo.to.TipoDocumentoTO;

public interface IndicadorComercialDAO {
	
	public List<IndicadorComercialDocumentoTO> findAllIndicadoresDocumento() throws DAOException;
	
	public List<TipoDocumentoTO> findDocumentosIndicador(IndicadorComercialDocumentoTO to) throws DAOException;
	
	public void save(IndicadorComercialDocumentoTO to) throws DAOException;
	
}
