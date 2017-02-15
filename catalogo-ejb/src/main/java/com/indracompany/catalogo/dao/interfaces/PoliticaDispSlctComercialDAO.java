package com.indracompany.catalogo.dao.interfaces;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PoliticaDispSlctComercialTO;

public interface PoliticaDispSlctComercialDAO {
	
	public PoliticaDispSlctComercialTO findId(PoliticaDispSlctComercialTO politicaDispSlctComercialTO) throws DAOException;	
	
	public PoliticaDispSlctComercialTO findById(PoliticaDispSlctComercialTO politicaDispSlctComercialTO) throws DAOException;
}
