package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.EncargoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.EncargoPoliticaPrecificacaoTO;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;

@Stateless
public class EncargoDAOImpl implements EncargoDAO{

	private static Logger logger = Logger.getLogger(EncargoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
    public List<EncargoPoliticaPrecificacaoTO> searchBySolicitacaoComercial(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException {
		logger.debug("servicoSolicitacaoComercialTO:" + servicoSolicitacaoComercialTO);
		List<EncargoPoliticaPrecificacaoTO> result = new ArrayList<EncargoPoliticaPrecificacaoTO>();
		EncargoPoliticaPrecificacaoTOBuilder builder = new EncargoPoliticaPrecificacaoTOBuilder();
		StringBuilder queryStr = new StringBuilder();
		
		try {
			queryStr.append(
				" select e from Encargo e " +
				" join e.solicitacaoComercial sc " +
				" join e.tipoEncargo te "
			);
			if(servicoSolicitacaoComercialTO.getNmPacote() != null){
				queryStr.append(
						" join e.valorPoliticaPrecificacao vpp" +
						" join vpp.espServicoPacote esp" +
						" join esp.servico s " +
						" where sc.idSolicitacaoComercial = :idSolicitacaoComercial " +
						" and upper(s.nmComercial) like upper(:nmPacote) "
				);
			} else {
				queryStr.append(" where sc.idSolicitacaoComercial = :idSolicitacaoComercial ");
			}
			if(!StringUtils.isBlank(servicoSolicitacaoComercialTO.getDsEncargo())){
				queryStr.append(" and lower(e.dsEncargo) like lower(:dsEncargo) ");
			}
			if(servicoSolicitacaoComercialTO.getPzGratuidade() != null){
				queryStr.append(" and e.pzGratuidade <= :pzGratuidade ");
			}
			if(servicoSolicitacaoComercialTO.getIdTipoEncargo() != null){
				queryStr.append(" and te.idTipoEncargo = :idTipoEncargo ");
			}
			
			Query query = em.createQuery(queryStr.toString());
			
			query.setParameter("idSolicitacaoComercial", servicoSolicitacaoComercialTO.getIdSolicitacaoComercial());
			if(servicoSolicitacaoComercialTO.getNmPacote() != null){
				query.setParameter("nmPacote", String.format("%s%s%s", "%", servicoSolicitacaoComercialTO.getNmPacote(), "%"));
			}
            if(!StringUtils.isBlank(servicoSolicitacaoComercialTO.getDsEncargo())){
                query.setParameter("dsEncargo", String.format("%s%s%s", "%", servicoSolicitacaoComercialTO.getDsEncargo(), "%"));
            }            
			if(servicoSolicitacaoComercialTO.getPzGratuidade() != null){
				query.setParameter("pzGratuidade", servicoSolicitacaoComercialTO.getPzGratuidade());
			}
			if(servicoSolicitacaoComercialTO.getIdTipoEncargo() != null){
				query.setParameter("idTipoEncargo", servicoSolicitacaoComercialTO.getIdTipoEncargo());
			}

			result = builder.createTOList(query.getResultList());
			
		} catch(Exception e){
			throw new DAOException(e);
		}
		
		return result;
	}
}