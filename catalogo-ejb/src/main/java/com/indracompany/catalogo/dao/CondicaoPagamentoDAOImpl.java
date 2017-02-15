package com.indracompany.catalogo.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.CondicaoPagamentoDAO;
import com.indracompany.catalogo.datalayer.CondicaoPagamento;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CondicaoPagamentoTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Condição Pagamento.
 */
@Stateless
public class CondicaoPagamentoDAOImpl implements CondicaoPagamentoDAO {
	
	private static Logger logger = Logger.getLogger(CondicaoPagamentoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CondicaoPagamentoDAO#createUpdateCondicaoPagamento(com.indracompany.catalogo.to.CondicaoPagamentoTO)
	 */
	public CondicaoPagamentoTO createUpdateCondicaoPagamento(CondicaoPagamentoTO condicaoPagamentoTO) throws DAOException {
		logger.debug("condicaoPagamentoTO: " + condicaoPagamentoTO);
		
		CondicaoPagamentoTOBuilder condicaoPagamentoTOBuilder = new CondicaoPagamentoTOBuilder();
		CondicaoPagamentoTO condicaoPagamentoResultTO = null;
		try {
            if (StringUtils.isBlank(condicaoPagamentoTO.getCdCondicaoPagamento())) {
                condicaoPagamentoTO.setCdCondicaoPagamento(em.createNativeQuery("select CATALOGOPRS_OW.CONDICAOPAGAMENTOFIXA_SQ.NEXTVAL FROM DUAL").getResultList().get(0).toString());
            }
			CondicaoPagamento condicaoPagamento = condicaoPagamentoTOBuilder.createCondicaoPagamento(condicaoPagamentoTO);
            condicaoPagamento.setInAlteracaoCatalogo("S");
            condicaoPagamentoResultTO = condicaoPagamentoTOBuilder.createCondicaoPagamentoTO(em.merge(condicaoPagamento));

		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [createUpdateCondicaoPagamento]", e);
		}
		
		return condicaoPagamentoResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.CondicaoPagamentoDAO#removeCondicaoPagamento(com.indracompany.catalogo.to.CondicaoPagamentoTO)
	 */
	public void removeCondicaoPagamento(CondicaoPagamentoTO condicaoPagamentoTO) throws DAOException {
		logger.debug("condicaoPagamentoTO: " + condicaoPagamentoTO);
		
		try {
			Query query = em.createNativeQuery("delete from CATALOGOPRS_OW.CONDICAOPAGAMENTO WHERE IDFORMAPAGAMENTO = :idFormaPagamento ");
			query.setParameter("idFormaPagamento", condicaoPagamentoTO.getFormaPagamentoTO().getIdFormaPagamento());
			query.executeUpdate();
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [removeCondicaoPagamento]", e);
		}
	}
	
	public void removeTipoProdutoCondPagto(CondicaoPagamentoTO condicaoPagamentoTO) throws DAOException {
		logger.debug("condicaoPagamentoTO: " + condicaoPagamentoTO);
		
		try {
			Query query = em.createNativeQuery("delete from CATALOGOPRS_OW.TIPOPRODUTOCONDPAGTO WHERE IDCONDICAOPAGAMENTO in (SELECT IDCONDICAOPAGAMENTO FROM CATALOGOPRS_OW.CONDICAOPAGAMENTO WHERE IDFORMAPAGAMENTO = :idFormaPagamento ) ");
			query.setParameter("idFormaPagamento", condicaoPagamentoTO.getFormaPagamentoTO().getIdFormaPagamento());
			query.executeUpdate();
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [removeCondicaoPagamento]", e);
		}
	}

	public CondicaoPagamentoTO findById(Integer id) throws DAOException {
		logger.debug(String.format("id: %s", id));
		try {
			return new CondicaoPagamentoTOBuilder().createCondicaoPagamentoTO(em.find(CondicaoPagamento.class, id));
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]",e);
		}
	}

	public void remove(Integer id) throws DAOException {
		try {
			em.remove(em.find(CondicaoPagamento.class, id));
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [remove]",e);
		}
	}

	/**
	 * Metodo utilizado pela tela de cadastro de financiamento <br><b>MUITA ATENÇÃO AO ALTERAR</b>
	 */
	@SuppressWarnings("unchecked")
	public List<CondicaoPagamentoTO> search(CondicaoPagamentoTO condicaoPagamentoTO) throws DAOException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select cp from CondicaoPagamento cp where cp.inFixa = 'S' ");
		if (!StringUtils.isBlank(condicaoPagamentoTO.getNmCondicaoPagamento())) {
			params.put("nmCondicaoPagamento", String.format("%s%s%s", "%", condicaoPagamentoTO.getNmCondicaoPagamento(), "%"));
			hql.append("and lower(cp.nmCondicaoPagamento) like lower(:nmCondicaoPagamento) ");
		}
		if (condicaoPagamentoTO.getQtParcelas() != null) {
			params.put("qtParcelas", condicaoPagamentoTO.getQtParcelas());
			hql.append("and cp.qtParcelas = :qtParcelas ");
		}
		
		if (condicaoPagamentoTO.getInDisponivel() != null) {
			params.put("inDisponivel", condicaoPagamentoTO.getInDisponivel() ? "S" : "N");
			hql.append("and cp.inDisponivel = :inDisponivel ");
		}
		
		if (condicaoPagamentoTO.getInCriacaoCatalogo() != null) {
			params.put("inCriacaoCatalogo", condicaoPagamentoTO.getInCriacaoCatalogo() ? "S" : "N");
			hql.append("and cp.inCriacaoCatalogo = :inCriacaoCatalogo ");
		}
		
		if (!StringUtils.isBlank(condicaoPagamentoTO.getCdCondicaoPagamento())) {
			params.put("cdCondicaoPagamento", condicaoPagamentoTO.getCdCondicaoPagamento());
			hql.append("and cp.cdCondicaoPagamento like :cdCondicaoPagamento ");
		}
		if (condicaoPagamentoTO.getTxJuroParcela() != null) {
			params.put("txJuroParcela", condicaoPagamentoTO.getTxJuroParcela());
			hql.append("and cp.txJuroParcela = :txJuroParcela ");				
		}
		
		try {
			return new CondicaoPagamentoTOBuilder().createCondicaoPagamentoTOList(this.getQuery(hql.toString(), params).getResultList());
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [search]",e);
		}
	}
	
	private Query getQuery(String hql, Map<String, Object> params) {
		Query query = em.createQuery(hql);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return query;
	}
    
    @SuppressWarnings("unchecked")
    public CondicaoPagamentoTO findByName(String nmCondicaoPagamento) throws DAOException{
        Map<String, Object> params = new HashMap<String, Object>();
        StringBuilder hql = new StringBuilder("select c from CondicaoPagamento c where c.inFixa = 'S' ");
        if (!StringUtils.isBlank(nmCondicaoPagamento)) {
            params.put("nmCondicaoPagamento", nmCondicaoPagamento);
            hql.append("and lower(c.nmCondicaoPagamento) like lower(:nmCondicaoPagamento) ");
        }
        try {
            List<CondicaoPagamentoTO> toList = new CondicaoPagamentoTOBuilder().createCondicaoPagamentoTOList((this.getQuery(hql.toString(), params).getResultList()));
            if (toList.size() > 0) {
                return toList.iterator().next(); 
            } else {
                return null;
            }
        } catch (Exception e) {
            throw new DAOException("Erro ao executar o DAO [findByName]", e);
        }
    }
}
