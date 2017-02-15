package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.datalayer.TecnologiaTpFrequenciaVl;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.TecnologiaTpFrequenciaVlTO;

public interface TecnologiaTpFrequenciaVlDAO {
	
	public TecnologiaTpFrequenciaVlTO obterTecnologiaTpFrequenciaVlTO(
			Integer idTecnologia, Integer idTipoFrequencia,
			Integer idVlFrequencia) throws DAOException;
	
	public TecnologiaTpFrequenciaVl obterTecnologiaTpFrequenciaVl(
			Integer idTecnologia, Integer idTipoFrequencia,
			Integer idVlFrequencia) throws DAOException;
	
}