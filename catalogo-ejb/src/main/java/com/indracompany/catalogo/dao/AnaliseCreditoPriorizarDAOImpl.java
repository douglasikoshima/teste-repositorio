package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.AnaliseCreditoPriorizarDAO;
import com.indracompany.catalogo.datalayer.CanalVenda;
import com.indracompany.catalogo.datalayer.Eps;
import com.indracompany.catalogo.datalayer.OfertaFixaCreditoPriorAg;
import com.indracompany.catalogo.datalayer.Ofertafixa;
import com.indracompany.catalogo.datalayer.OfertafixaCreditoPrior;
import com.indracompany.catalogo.datalayer.OfertafixaCreditoScore;
import com.indracompany.catalogo.datalayer.OfertafixaFatorCanalVenda;
import com.indracompany.catalogo.datalayer.OfertafixaFatorEps;
import com.indracompany.catalogo.datalayer.OfertafixaPolitica;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AnalisePriorizarTO;
import com.indracompany.catalogo.to.OfertafixaPoliticaTO;

@Stateless
public class AnaliseCreditoPriorizarDAOImpl implements AnaliseCreditoPriorizarDAO {
    
    private static Logger log = Logger.getLogger(AnaliseCreditoPriorizarDAOImpl.class);

    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<AnalisePriorizarTO> pesquisarOferta(Integer idAnaliseCredito, Integer idEps) throws DAOException {
        log.debug(String.format("idAnaliseCredito: %s, idEps: %s", idAnaliseCredito, idEps));      
        List<AnalisePriorizarTO> analisePriorizarList, analisePriorizarListConf;
        try {
        	
        	StringBuilder hql = new StringBuilder("select distinct o.dsOfertafixa, o.inFWT, o.inConvergenteFibra, ");
            hql.append("o.inConvergenteCobre, o.inSpeedySoloFibra, o.inSpeedySoloCobre, o.inPortab, o.dtInicial, o.dtFinal, ");
            hql.append("ofcs.idOfertafixaCreditoScore, ");
            hql.append("ocpa.cdPrioridade, ");
            hql.append("sln.nmComercial, ");
            hql.append("spc.nmComercial, ");
            hql.append("spl.nmComercial, ");
            hql.append("o.cdOfertafixa ");
            hql.append("from Ofertafixa o ");
            hql.append("inner join o.ofertafixaCreditoScoreList ofcs ");
            hql.append("inner join o.solicitacaoComercialLinha scln ");
            hql.append("inner join scln.sistemaServico ssln ");
            hql.append("inner join ssln.servico sln ");
            hql.append("inner join o.solicitacaoComercialPlano scpl ");
            hql.append("inner join scpl.sistemaServico sspl ");
            hql.append("inner join sspl.servico spl ");
            hql.append("left join o.solicitacaoComercialPreCad scpc ");
            hql.append("left join scpc.sistemaServico sspc ");
            hql.append("left join sspc.servico spc ");
            hql.append("left join ofcs.ofertafixaCreditoPrior ocp  ");
            hql.append("left join ofcs.ofertaFixaCreditoPriorAg ocpa  ");
            hql.append("left join ocp.canalVenda cv  ");
            hql.append("left join ocpa.eps ep ");
            hql.append("where ofcs.analiseCredito.idAnaliseCredito = :idAnaliseCredito ");
            hql.append("and ((ep.idEps = :idEps) or (ep.idEps is null)) ");
            hql.append("and (o.dtFinal >= trunc(sysdate) or o.dtFinal is null) ");
            hql.append("and exists ( ");
            hql.append("  select cvsc.canalVenda from " );
            hql.append("    scln.canalVendaSolicitacaoComercialList cvsc ");
            hql.append("  inner join cvsc.canalVenda cv ");          
            hql.append("  inner join cv.eps eps	" );
            hql.append("  where eps.idEps = :idEps ");
            hql.append("  ) ");
            hql.append("and exists ( ");
            hql.append("  select cvsc.canalVenda from ");
            hql.append("    scpl.canalVendaSolicitacaoComercialList cvsc ");
            hql.append("  inner join cvsc.canalVenda cv ");
            hql.append("  inner join cv.eps eps ");
            hql.append("  where eps.idEps = :idEps ");
            hql.append("  ) ");
            hql.append("order by ocpa.cdPrioridade, o.dsOfertafixa ");
            
        	StringBuilder hql2 = new StringBuilder("select distinct o.dsOfertafixa, o.inFWT, o.inConvergenteFibra, ");
        	hql2.append("o.inConvergenteCobre, o.inSpeedySoloFibra, o.inSpeedySoloCobre, o.inPortab, o.dtInicial, o.dtFinal, ");
        	hql2.append("ofcs.idOfertafixaCreditoScore, ");
        	hql2.append("ocpa.cdPrioridade, ");
        	hql2.append("sln.nmComercial, ");
        	hql2.append("spc.nmComercial, ");
        	hql2.append("spl.nmComercial, ");
        	hql2.append("o.cdOfertafixa ");
        	hql2.append("from Ofertafixa o ");
            hql2.append("inner join o.ofertafixaCreditoScoreList ofcs ");
            hql2.append("inner join o.solicitacaoComercialLinha scln ");
            hql2.append("inner join scln.sistemaServico ssln ");
            hql2.append("inner join ssln.servico sln ");
            hql2.append("inner join o.solicitacaoComercialPlano scpl ");
            hql2.append("inner join scpl.sistemaServico sspl ");
            hql2.append("inner join sspl.servico spl ");
            hql2.append("left join o.solicitacaoComercialPreCad scpc ");
            hql2.append("left join scpc.sistemaServico sspc ");
            hql2.append("left join sspc.servico spc ");
            hql2.append("left join ofcs.ofertafixaCreditoPrior ocp  ");
            hql2.append("left join ofcs.ofertaFixaCreditoPriorAg ocpa  ");
            hql2.append("left join ocp.canalVenda cv  ");
            hql2.append("left join ocpa.eps ep ");
            hql2.append("where ofcs.analiseCredito.idAnaliseCredito = :idAnaliseCredito ");
            hql2.append("and (ep.idEps <> :idEps) ");      
            hql2.append("and (o.dtFinal >= trunc(sysdate) or o.dtFinal is null) ");
            hql2.append("and exists ( ");
            hql2.append("  select cvsc.canalVenda from " );
            hql2.append("    scln.canalVendaSolicitacaoComercialList cvsc ");
            hql2.append("  inner join cvsc.canalVenda cv ");          
            hql2.append("  inner join cv.eps eps	" );
            hql2.append("  where eps.idEps = :idEps ");
            hql2.append("  ) ");
            hql2.append("and exists ( ");
            hql2.append("  select cvsc.canalVenda from ");
            hql2.append("    scpl.canalVendaSolicitacaoComercialList cvsc ");
            hql2.append("  inner join cvsc.canalVenda cv ");
            hql2.append("  inner join cv.eps eps ");
            hql2.append("  where eps.idEps = :idEps ");
            hql2.append("  ) ");
            hql2.append("order by ocpa.cdPrioridade, o.dsOfertafixa ");

            
            
            analisePriorizarList = new AnalisePriorizarTOBuilder().createTOList(em.createQuery(hql.toString()).setParameter("idAnaliseCredito", idAnaliseCredito).setParameter("idEps", idEps).getResultList(),false);
            analisePriorizarListConf = new AnalisePriorizarTOBuilder().createTOList(em.createQuery(hql2.toString()).setParameter("idAnaliseCredito", idAnaliseCredito).setParameter("idEps", idEps).getResultList(),true);

            if(analisePriorizarList != null && analisePriorizarList.size() > 0){
                for(AnalisePriorizarTO analisePriorizarAdd : analisePriorizarListConf){
                	boolean status = true; 
                	for(AnalisePriorizarTO analisePriorizar :analisePriorizarList){
                		if(analisePriorizarAdd.getIdOfertafixaCreditoScore().toString().equals(analisePriorizar.getIdOfertafixaCreditoScore().toString())){
                			status = false;
                			break;
                		}
                	}
                	if(status){
                		analisePriorizarList.add(analisePriorizarAdd);
                	}
                } 
            }

        } catch (Exception e) {
            String msg = String.format("erro pesquisar oferta para idAnaliseCredito %s", idAnaliseCredito);
            log.error(msg, e); 
            throw new DAOException(msg, e);
        }
        log.debug(analisePriorizarList);
        return analisePriorizarList;
    }

    @SuppressWarnings("unchecked")
    public void gravarPriorizacao(final Integer idAnaliseCredito, final List<Integer> idOfertafixaCreditoScoreList, final Integer idEps, List<AnalisePriorizarTO> analisePriTOListRemove) throws DAOException {
        log.debug(String.format("idAnaliseCredito: %s, idOfertafixaCreditoScoreList: %s, idEps: %s ", idAnaliseCredito, idOfertafixaCreditoScoreList, idEps));
        try {
        	List<OfertafixaCreditoScore> entityListRemove = em.createNamedQuery("OfertafixaCreditoScore.findByIdAnaliseCreditoRemove").setParameter("idAnaliseCredito", idAnaliseCredito).setParameter("idEps", idEps).getResultList();
        	
        	List<OfertafixaCreditoScore> entityList = em.createNamedQuery("OfertafixaCreditoScore.findByIdAnaliseCredito").setParameter("idAnaliseCredito", idAnaliseCredito).getResultList();
        	
        	List<CanalVenda> entityListCanalVenda = em.createNamedQuery("CanalVenda.findByIdEps").setParameter("idEps", idEps).getResultList();
            Eps entityListEps = (Eps) em.createNamedQuery("Eps.findByCodigo").setParameter("idEps", idEps).getSingleResult();
                        
            CollectionUtils.forAllDo(entityListRemove, new Closure() {
                public void execute(Object obj) {
                    OfertafixaCreditoScore entity = (OfertafixaCreditoScore) obj;
                    if (entity.getOfertaFixaCreditoPriorAg() != null) {
                    	for(OfertaFixaCreditoPriorAg ofertaFixaCreditoPriorAg : entity.getOfertaFixaCreditoPriorAg()){                    		
                    		em.remove(ofertaFixaCreditoPriorAg);                    		
                    	}
                    	
                    }
                }
            });
            em.flush();
            
            CollectionUtils.forAllDo(entityListRemove, new Closure() {
                public void execute(Object obj) {
                    OfertafixaCreditoScore entity = (OfertafixaCreditoScore) obj;
                    if (entity.getOfertafixaCreditoPrior() != null) {
                    	for(OfertafixaCreditoPrior ofertafixaCreditoPrior : entity.getOfertafixaCreditoPrior()){         
                    		em.remove(ofertafixaCreditoPrior);
                    	}
                    }
                }
            }); 
            em.flush();  

            for(final Integer idOfertafixaCreditoScore : idOfertafixaCreditoScoreList) {
                em.persist(new OfertaFixaCreditoPriorAg((OfertafixaCreditoScore)CollectionUtils.find(entityList, new Predicate() {
                    public boolean evaluate(Object obj) {
                        return ((OfertafixaCreditoScore) obj).getIdOfertafixaCreditoScore().equals(idOfertafixaCreditoScore);
                    }
                }), idOfertafixaCreditoScoreList.indexOf(idOfertafixaCreditoScore),entityListEps));
            }

            for(final CanalVenda canalVenda : entityListCanalVenda)  {
              for(final Integer idOfertafixaCreditoScore : idOfertafixaCreditoScoreList) {
                em.persist(new OfertafixaCreditoPrior((OfertafixaCreditoScore)CollectionUtils.find(entityList, new Predicate() {
                    public boolean evaluate(Object obj) {
                        return ((OfertafixaCreditoScore) obj).getIdOfertafixaCreditoScore().equals(idOfertafixaCreditoScore);
                    }
                }), idOfertafixaCreditoScoreList.indexOf(idOfertafixaCreditoScore),canalVenda));
              }
             }

            if (!analisePriTOListRemove.isEmpty()) {
            	for (int i = 0; i < analisePriTOListRemove.size(); i++) {
            		// Atualiza a politica das ofertas fixas não priorizadas
            		OfertafixaCreditoScore ofertafixaCreditoScore = (OfertafixaCreditoScore) em.createNamedQuery("OfertafixaCreditoScore.findOfertafixaByOfertafixaCreditoScore").setParameter("idofertafixacreditoscore", analisePriTOListRemove.get(i).getIdOfertafixaCreditoScore()).getSingleResult();

            		String inAreaRegistro = "N", inLocalidade = "N", inCanalVenda = "N", inEPS = "N";
            		Integer idOfertafixa = ofertafixaCreditoScore.getOfertafixa().getIdOfertafixa();
            		Ofertafixa ofertafixa = (Ofertafixa) em.createNamedQuery("Ofertafixa.findById").setParameter("idOfertafixa",idOfertafixa).getSingleResult();

            		inAreaRegistro = ofertafixa.getOfertafixaPolitica().getInAreaRegistro();
            		inLocalidade = ofertafixa.getOfertafixaPolitica().getInLocalidade();

            		OfertafixaPolitica op = new OfertafixaPolitica();
            		List<OfertafixaPolitica> opList = (List<OfertafixaPolitica>) em.createNamedQuery("OfertafixaPolitica.findId").setParameter("inAreaRegistro", inAreaRegistro).setParameter("inCanalVenda", inCanalVenda).setParameter("inEPS", inEPS).setParameter("inLocalidade", inLocalidade).getResultList();
            		if (!opList.isEmpty()) {
            			op = opList.get(0);
            			
                		OfertafixaPoliticaTO ofertafixaPoliticaTO = new OfertafixaPoliticaTOBuilder().buildTO(op);
                		ofertafixa.setOfertafixaPolitica(new OfertafixaPolitica(ofertafixaPoliticaTO.getIdOfertafixaPolitica()));

                		ofertafixa = em.merge(ofertafixa);
                		em.flush();

                		// aqui remove    				
                		List<OfertafixaCreditoScore> ocsList = findIdAnaliseCreditoList(idAnaliseCredito, ofertafixa.getIdOfertafixa());

                		if (ocsList.isEmpty()) {
                			//remove
                			remove(ofertafixa);    					
                		}            			
            		}            		
            	}
            }            

            if (!idOfertafixaCreditoScoreList.isEmpty()) {
                for (int i = 0; i < idOfertafixaCreditoScoreList.size(); i++) {
                    //Atualiza a politica das ofertas fixas priorizadas
                    String inAreaRegistro = "N", inLocalidade = "N", inCanalVenda = "S", inEPS = "S";
                	Integer idofertafixacreditoscore = idOfertafixaCreditoScoreList.get(i);
                	
                	
                	OfertafixaCreditoScore ocs = (OfertafixaCreditoScore) em.createNamedQuery("OfertafixaCreditoScore.findOfertafixaByOfertafixaCreditoScore").setParameter("idofertafixacreditoscore", idofertafixacreditoscore).getSingleResult();
                	            		
                	inAreaRegistro = ocs.getOfertafixa().getOfertafixaPolitica().getInAreaRegistro();
                	inLocalidade = ocs.getOfertafixa().getOfertafixaPolitica().getInLocalidade();
                   
                	OfertafixaPolitica op = new OfertafixaPolitica();
                	List<OfertafixaPolitica> opList = (List<OfertafixaPolitica>) em.createNamedQuery("OfertafixaPolitica.findId").setParameter("inAreaRegistro", inAreaRegistro).setParameter("inCanalVenda", inCanalVenda).setParameter("inEPS", inEPS).setParameter("inLocalidade", inLocalidade).getResultList();
                	if (!opList.isEmpty()) {
                		op = opList.get(0);    
                    	OfertafixaPoliticaTO ofertafixaPoliticaTO = new OfertafixaPoliticaTOBuilder().buildTO(op);
                    	
                    	ocs.getOfertafixa().setOfertafixaPolitica(new OfertafixaPolitica(ofertafixaPoliticaTO.getIdOfertafixaPolitica()));
                        
                        em.merge(ocs.getOfertafixa());
                        em.flush();     
                        
                        //aqui INSERT
                        
                        remove(ocs.getOfertafixa());   
                        insert(entityListCanalVenda, ocs.getOfertafixa(), entityListEps);
                	}                	                	            
                }            	
            }
            
        } catch (Exception e) {
            String msg = String.format("erro ao priorizar idOfertafixaCreditoScoreList %s para idAnaliseCredito %s", idOfertafixaCreditoScoreList, idAnaliseCredito);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        log.debug(String.format("priorizacao efetuada com sucesso de idOfertafixaCreditoScoreList %s para idAnaliseCredito %s", idOfertafixaCreditoScoreList, idAnaliseCredito));
    }
    
    @SuppressWarnings("rawtypes")
	public List<OfertafixaCreditoScore> findIdAnaliseCreditoList(Integer idAnaliseCredito, Integer idOfertafixa) {
    	List<OfertafixaCreditoScore> ocsList = new ArrayList<OfertafixaCreditoScore>();
    	
    	StringBuilder hql = new StringBuilder("select ocs from OfertafixaCreditoScore ocs ");
    	hql.append("inner join ocs.ofertaFixaCreditoPriorAg ofcpa ");
    	hql.append("inner join ocs.ofertafixaCreditoPrior ofcp ");
    	hql.append("where ocs.analiseCredito.idAnaliseCredito <> :idAnaliseCredito ");
    	hql.append("and ocs.ofertafixa.idOfertafixa = :idOfertafixa");
    	
    	Query query = em.createQuery(hql.toString()).setParameter("idAnaliseCredito", idAnaliseCredito).setParameter("idOfertafixa", idOfertafixa);
    	List resultList = query.getResultList();
    	for (Object ocs: resultList) {
    		ocsList.add((OfertafixaCreditoScore)ocs);
    	}
    	
    	return ocsList;
    }
    
    public void remove(Ofertafixa ofertafixa) throws DAOException {
		try {
			
			Query query = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.OFERTAFIXAFATORCANALVENDA where IDOFERTAFIXA = :idOfertafixa ");
			query.setParameter("idOfertafixa", ofertafixa.getIdOfertafixa());
			query.executeUpdate();
			
			Query query2 = em.createNativeQuery("DELETE FROM CATALOGOPRS_OW.OFERTAFIXAFATOREPS where IDOFERTAFIXA = :idOfertafixa ");
			query2.setParameter("idOfertafixa", ofertafixa.getIdOfertafixa());
			query2.executeUpdate();			   
				
			
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
    	
    }
    
    public void insert(List<CanalVenda> entityListCanalVenda, Ofertafixa ofertafixa, Eps entityListEps) throws DAOException {
    	
    	try {
    		for(final CanalVenda canalVenda : entityListCanalVenda)  { 
    			em.persist(new OfertafixaFatorCanalVenda(ofertafixa, canalVenda));
    		}
    		    		
    		em.persist(new OfertafixaFatorEps(ofertafixa, entityListEps));
    		
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
    }
}
