package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.FormaPagamentoDAO;
import com.indracompany.catalogo.datalayer.FormaPagamento;
import com.indracompany.catalogo.datalayer.FormaPagamentoPlataforma;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FormaPagamentoTO;
import com.indracompany.catalogo.to.PlataformaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Forma Pagamento.
 */
@Stateless
public class FormaPagamentoDAOImpl implements FormaPagamentoDAO {
	
	private static Logger logger = Logger.getLogger(FormaPagamentoDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;

	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FormaPagamentoDAO#findAll(com.indracompany.catalogo.to.FormaPagamentoTO)
	 */
	@SuppressWarnings("unchecked")
	public List<FormaPagamentoTO> findAll() throws DAOException {
		logger.debug("findAll");
		
		List<FormaPagamentoTO> formaPagamentoTOList = null;
		FormaPagamentoTOBuilder formaPagamentoTOBuilder = new FormaPagamentoTOBuilder();
		
		try {
			
			Query query = em.createNamedQuery("FormaPagamento.findAll");
			formaPagamentoTOList = formaPagamentoTOBuilder.createFormaPagamentoTOList(query.getResultList());
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findAll]", e);
		}
		
		return formaPagamentoTOList;
	} 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FormaPagamentoDAO#searchFormaPagamento(com.indracompany.catalogo.to.FormaPagamentoTO)
	 */
	@SuppressWarnings("unchecked")
	public List<FormaPagamentoTO> searchFormaPagamento(FormaPagamentoTO formaPagamentoTO) throws DAOException {
		logger.debug("searchFormaPagamento" + formaPagamentoTO);
		
		List<FormaPagamentoTO> formaPagamentoTOList = null;
		FormaPagamentoTOBuilder formaPagamentoTOBuilder = new FormaPagamentoTOBuilder();
		
		try {
			
			StringBuffer queryStr = new StringBuffer();
			queryStr.append("select fp from FormaPagamento fp ");
			queryStr.append(" inner join fp.formaPagtoCanalParam fpcp ");
			queryStr.append(" inner join fp.formaPagtoCanalParam.formaPagtoCanalAtndParam fpcap ");
			
			if (formaPagamentoTO.getFormaPagamentoBandeiraTOList() != null) {
				queryStr.append(" inner join fp.formaPagamentoBandeiraList fpb ");
			}
			queryStr.append(" where fp.dtCriacao is not null ");
			queryStr.append(" and fp.idFormaPagamento = fpcp.formaPagamento.idFormaPagamento");
			queryStr.append(" and fpcp.idFormaPagtoCanalParam = fpcap.formaPagtoCanalParam.idFormaPagtoCanalParam");
			queryStr.append(" and ((fpcp.canal.idCanal = 40 and fpcap.canalAtendimento.idCanalAtendimento = 23611)"); // LOJA VIRTUAL - TELEVENDAS
			queryStr.append(" or (fpcp.canal.idCanal = 35 and fpcap.canalAtendimento.idCanalAtendimento = 2))"); // LOJAS PRÓPRIAS - LOJAS
			
			if (formaPagamentoTO.getIdFormaPagamento() != null && !formaPagamentoTO.getIdFormaPagamento().equals(0)) {
				queryStr.append(" and fp.idFormaPagamento = :idFormaPagamento");
			}
			
			if (formaPagamentoTO.getFormaPagamentoBandeiraTOList() != null) {
				queryStr.append(" and fp.idFormaPagamento = fpb.formaPagamento.idFormaPagamento");
				queryStr.append(" and fpb.bandeira.idBandeira = :idBandeira ");
			}
			
			if (formaPagamentoTO.getMeioPagamentoTO() != null && formaPagamentoTO.getMeioPagamentoTO().getSgMeioPagamento() != null && !formaPagamentoTO.getMeioPagamentoTO().getSgMeioPagamento().equals("")) {
				queryStr.append(" and fp.meioPagamento.sgMeioPagamento = :sgMeioPagamento");
			}
            
			Query query = em.createQuery(queryStr.toString());
			
			if (formaPagamentoTO.getIdFormaPagamento() != null && !formaPagamentoTO.getIdFormaPagamento().equals(0)) {
				query.setParameter("idFormaPagamento", formaPagamentoTO.getIdFormaPagamento());
			}
			
			if (formaPagamentoTO.getFormaPagamentoBandeiraTOList() != null) {
				query.setParameter("idBandeira", formaPagamentoTO.getFormaPagamentoBandeiraTOList().get(0).getBandeiraTO().getIdBandeira());
			}
			
			if (formaPagamentoTO.getMeioPagamentoTO() != null && formaPagamentoTO.getMeioPagamentoTO().getSgMeioPagamento() != null && !formaPagamentoTO.getMeioPagamentoTO().getSgMeioPagamento().equals("")) {
				query.setParameter("sgMeioPagamento", formaPagamentoTO.getMeioPagamentoTO().getSgMeioPagamento());
			}
			
			formaPagamentoTOList = formaPagamentoTOBuilder.createFormaPagamentoTOList(query.getResultList());
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [searchFormaPagamento]", e);
		}
		
		return formaPagamentoTOList;
	} 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FormaPagamentoDAO#findById(com.indracompany.catalogo.to.FormaPagamentoTO)
	 */
	public FormaPagamentoTO findById(FormaPagamentoTO formaPagamentoTO) throws DAOException {
		logger.debug("findById: " + formaPagamentoTO);
		
		FormaPagamentoTO formaPagamentoResultTO = null;
		FormaPagamentoTOBuilder formaPagamentoTOBuilder = new FormaPagamentoTOBuilder();
		
		try {
			
			formaPagamentoResultTO = formaPagamentoTOBuilder.createFormaPagamentoTO(em.find(FormaPagamento.class, formaPagamentoTO.getIdFormaPagamento()));
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
		
		return formaPagamentoResultTO;
	} 
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FormaPagamentoDAO#createUpdateFormaPagamento(com.indracompany.catalogo.to.FormaPagamentoTO)
	 */
	@SuppressWarnings("unchecked")
    public FormaPagamentoTO createUpdateFormaPagamento(FormaPagamentoTO formaPagamentoTO) throws DAOException {
		logger.debug("formaPagamentoTO: " + formaPagamentoTO);
		
		FormaPagamentoTOBuilder formaPagamentoTOBuilder = new FormaPagamentoTOBuilder();
		FormaPagamentoTO formaPagamentoResultTO = null;
			
		try {
            Integer idFormaPagamento = em.merge(formaPagamentoTOBuilder.createFormaPagamento(formaPagamentoTO)).getIdFormaPagamento();
            List<FormaPagamentoPlataforma> fppList = new ArrayList<FormaPagamentoPlataforma>();
            for(PlataformaTO to : formaPagamentoTO.getPlataformaTOList()) {
                Query qry = em.createNamedQuery("FormaPagamentoPlataforma.searchByFormaPagamentoAndPlataforma");
                /*lista para saber o que nao sera deletado do banco*/
                try {
                    /*localiza se o registro ja existe para nao inserir duplicado e dar erro de unique constraint*/
                    fppList.add((FormaPagamentoPlataforma) qry.setParameter("idFormaPagamento", idFormaPagamento).setParameter("idPlataforma", to.getIdPlataforma()).getSingleResult());
                } catch (NoResultException e) {
                    /* se nao foi localizado nenhum resultado, insere no banco de dados o novo relacionamento*/
                    fppList.add(em.merge(new FormaPagamentoPlataforma(idFormaPagamento, to.getIdPlataforma())));
                }
            }
            /*atualiza os dados no banco de dados dentro da transacao*/
            em.flush();
            /*procura todas as ocorrencias no banco para poder remover o que foi desmarcado na tela*/
            List<FormaPagamentoPlataforma> fppListRemove = em.createNamedQuery("FormaPagamentoPlataforma.searchByFormaPagamento").setParameter("idFormaPagamento", idFormaPagamento).getResultList();
            for (FormaPagamentoPlataforma fpp : fppList) {
                /*remove o objeto da lista que sera utilizada para o delete no banco de dados*/
                fppListRemove.remove(fpp);
            }
            for (FormaPagamentoPlataforma fpp : fppListRemove) {
                /*percorre cada elemento da lista ja filtrada para delecao no banco de dados*/
                em.remove(fpp);
            }
            /*atualiza os dados no banco de dados dentro da transacao*/
            em.flush();
            formaPagamentoResultTO = formaPagamentoTOBuilder.createFormaPagamentoTO(em.find(FormaPagamento.class, idFormaPagamento));
            
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return formaPagamentoResultTO;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FormaPagamentoDAO#removeFormaPagamento(com.indracompany.catalogo.to.FormaPagamentoTO)
	 */
	public void removeFormaPagamento(FormaPagamentoTO formaPagamentoTO) throws DAOException {
		logger.debug("formaPagamentoTO: " + formaPagamentoTO);
		
		try {
			em.remove(em.find(FormaPagamento.class, formaPagamentoTO.getIdFormaPagamento()));
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FormaPagamentoDAO#removeFormaPagtoCanalParam(com.indracompany.catalogo.to.FormaPagamentoTO)
	 */
	public void removeFormaPagtoCanalParam(FormaPagamentoTO formaPagamentoTO) throws DAOException {
		logger.debug("formaPagamentoTO: " + formaPagamentoTO);
		
		try {
			Query query = em.createNativeQuery("delete from CATALOGOPRS_OW.FORMAPAGTOCANALPARAM WHERE IDFORMAPAGAMENTO = :idFormaPagamento ");
			query.setParameter("idFormaPagamento", formaPagamentoTO.getIdFormaPagamento());
			query.executeUpdate();
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [removeFormaPagtoCanalParam]", e);
		}
		
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.FormaPagamentoDAO#removeFormaPagtoCanalParam(com.indracompany.catalogo.to.FormaPagtoCanalAtndParamTO)
	 */
	public void removeFormaPagtoCanalAtndParam(FormaPagamentoTO formaPagamentoTO) throws DAOException {
		logger.debug("formaPagamentoTO: " + formaPagamentoTO);
		
		try {
			Query query = em.createNativeQuery("delete from CATALOGOPRS_OW.FORMAPAGTOCANALATNDPARAM WHERE IDFORMAPAGTOCANALPARAM IN (SELECT IDFORMAPAGTOCANALPARAM FROM CATALOGOPRS_OW.FORMAPAGTOCANALPARAM WHERE IDFORMAPAGAMENTO = :idFormaPagamento)");
			query.setParameter("idFormaPagamento", formaPagamentoTO.getIdFormaPagamento());
			query.executeUpdate();
			
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [removeFormaPagtoCanalAtndParam]", e);
		}
		
	}
}
