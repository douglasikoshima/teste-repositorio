package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.indracompany.catalogo.dao.interfaces.PoliticaDispSlctComercialDAO;
import com.indracompany.catalogo.datalayer.PoliticaDispSlctComercial;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PoliticaDispSlctComercialTO;

@Stateless
public class PoliticaDispSlctComercialDAOImpl implements PoliticaDispSlctComercialDAO{
    
    @PersistenceContext
    private EntityManager em; 
	
	public PoliticaDispSlctComercialTO findId(PoliticaDispSlctComercialTO politicaDispSlctComercialTO) throws DAOException {
		StringBuilder queryStr = new StringBuilder();
		PoliticaDispSlctComercialTO result = new PoliticaDispSlctComercialTO();
		PoliticaDispSlctComercialTOBuilder builder = new PoliticaDispSlctComercialTOBuilder();
		
		try {
			queryStr.append(" select p ");
			queryStr.append(" from PoliticaDispSlctComercial p ");
			queryStr.append(" where p.inAreaConcorrencia = :inAreaConcorrencia ");
			queryStr.append(" and p.inPlanoMinuto = :inPlanoMinuto ");
			
			Query query = em.createQuery(queryStr.toString());
			
			if(politicaDispSlctComercialTO.getInAreaConcorrencia().equalsIgnoreCase("N") && politicaDispSlctComercialTO.getInPlanoMinuto().equalsIgnoreCase("N")){
				result.setInAreaConcorrencia(politicaDispSlctComercialTO.getInAreaConcorrencia());
				result.setInPlanoMinuto(politicaDispSlctComercialTO.getInAreaConcorrencia());
				result.setIdPoliticaDispSlctComercial(null);
			} else {
				query.setParameter("inAreaConcorrencia", politicaDispSlctComercialTO.getInAreaConcorrencia());
				query.setParameter("inPlanoMinuto", politicaDispSlctComercialTO.getInPlanoMinuto());
				
				result = builder.createTO((PoliticaDispSlctComercial) query.getResultList().get(0));
			}
		} catch(Exception e){
			throw new DAOException(e);
		}
		
		return result;
	}

	public PoliticaDispSlctComercialTO findById(PoliticaDispSlctComercialTO politicaDispSlctComercialTO) throws DAOException {
		PoliticaDispSlctComercialTOBuilder builder = new PoliticaDispSlctComercialTOBuilder();
		PoliticaDispSlctComercialTO result = null;
		
		try {
			result = builder.createTO(em.find(PoliticaDispSlctComercial.class, politicaDispSlctComercialTO.getIdPoliticaDispSlctComercial()));
		} catch(Exception e){
			throw new DAOException(e);
		}
		
		return result;
	}

}
