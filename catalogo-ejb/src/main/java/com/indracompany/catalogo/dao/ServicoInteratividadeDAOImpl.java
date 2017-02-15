
package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ServicoInteratividadeDAO;
import com.indracompany.catalogo.datalayer.ServicoInteratividade;
import com.indracompany.catalogo.datalayer.TipoLinha;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicoInteratividadeTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Servico Interatividade.
 */
@Stateless
public class ServicoInteratividadeDAOImpl implements ServicoInteratividadeDAO {
	
	private static Logger logger = Logger.getLogger(ServicoInteratividadeDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em; 
	
	@SuppressWarnings("unchecked")
	public List<ServicoInteratividadeTO> searchServicoInteratividade(ServicoInteratividadeTO servicoInteratividadeTO) throws DAOException {
		logger.debug("servicoInteratividadeTO: " + servicoInteratividadeTO);
		
		List<ServicoInteratividadeTO> list = null;
		StringBuffer queryStr = new StringBuffer();
		
		try {
		
			queryStr.append("select distinct si from ServicoInteratividade si where si.ativo in ('S', 'N') ");
			
			if (servicoInteratividadeTO != null) {

				if (servicoInteratividadeTO.getFuncionalidadeTO() != null && servicoInteratividadeTO.getFuncionalidadeTO().getCdFuncionalidade() != null && !servicoInteratividadeTO.getFuncionalidadeTO().getCdFuncionalidade().equals("0") && !servicoInteratividadeTO.getFuncionalidadeTO().getCdFuncionalidade().isEmpty()) {
					queryStr.append("and si.funcionalidade.cdFuncionalidade = :cdFuncionalidade ");
				}
		
				if (servicoInteratividadeTO.getNmServico() != null && !servicoInteratividadeTO.getNmServico().equals("")) {
					queryStr.append("and upper(si.nmServico) like upper(:nmServico) ");
				}
				
				if (servicoInteratividadeTO.getUrl() != null && !servicoInteratividadeTO.getUrl().equals("")) {
					queryStr.append("and upper(si.url) like upper(:url) ");
				}
				
				if (servicoInteratividadeTO.getTipoLinhaTO() != null && servicoInteratividadeTO.getTipoLinhaTO().getSgTipoLinha() != null && !servicoInteratividadeTO.getTipoLinhaTO().getSgTipoLinha().equals("0") && !servicoInteratividadeTO.getTipoLinhaTO().getSgTipoLinha().isEmpty()) {
					queryStr.append("and si.tipoLinha.sgTipoLinha = :sgTipoLinha ");
				}
				
				queryStr.append("order by si.idServicoInteratividade");
			}
			
			Query query = em.createQuery(queryStr.toString());
			
			if (servicoInteratividadeTO != null) {
				if (servicoInteratividadeTO.getFuncionalidadeTO() != null && servicoInteratividadeTO.getFuncionalidadeTO().getCdFuncionalidade() != null && !servicoInteratividadeTO.getFuncionalidadeTO().getCdFuncionalidade().equals("0") && !servicoInteratividadeTO.getFuncionalidadeTO().getCdFuncionalidade().isEmpty()) {
					query.setParameter("cdFuncionalidade", servicoInteratividadeTO.getFuncionalidadeTO().getCdFuncionalidade());				
				}
		
				if (servicoInteratividadeTO.getNmServico() != null && !servicoInteratividadeTO.getNmServico().equals("")) {
					query.setParameter("nmServico", "%" + servicoInteratividadeTO.getNmServico() + "%");
				}
				
				if (servicoInteratividadeTO.getUrl() != null && !servicoInteratividadeTO.getUrl().equals("")) {
					query.setParameter("url", "%" + servicoInteratividadeTO.getUrl() + "%");
				}
				if (servicoInteratividadeTO.getTipoLinhaTO() != null && servicoInteratividadeTO.getTipoLinhaTO().getSgTipoLinha() != null  && !servicoInteratividadeTO.getTipoLinhaTO().getSgTipoLinha().equals("0") && !servicoInteratividadeTO.getTipoLinhaTO().getSgTipoLinha().isEmpty()) {
					query.setParameter("sgTipoLinha", servicoInteratividadeTO.getTipoLinhaTO().getSgTipoLinha());					
				}
			}
			ServicoInteratividadeTOBuilder servicoInteratividadeTOBuilder = new ServicoInteratividadeTOBuilder();						
			list = servicoInteratividadeTOBuilder.createServicoInteratividadeTOList(query.getResultList());

		} catch (Exception e) {
			throw new DAOException(e);
		}
			
		return list;
	}
	
	public ServicoInteratividade createServicoInteratividade(ServicoInteratividadeTO servicoInteratividadeTO) {
		ServicoInteratividade servicoInteratividade = new ServicoInteratividade();
		
		if (servicoInteratividadeTO != null) { 
			servicoInteratividade = new ServicoInteratividade();
			
			servicoInteratividade.setAtivo(servicoInteratividadeTO.getAtivo());
			servicoInteratividade.setFuncionalidade(new FuncionalidadeTOBuilder().createFuncionalidade(servicoInteratividadeTO.getFuncionalidadeTO()));
			servicoInteratividade.setNmServico(servicoInteratividadeTO.getNmServico());
			servicoInteratividade.setUrl(servicoInteratividadeTO.getUrl());
			servicoInteratividade.setDtCriacao(servicoInteratividadeTO.getDtCriacao());
			servicoInteratividade.setNmUsuarioCriacao(servicoInteratividadeTO.getNmUsuarioCriacao());

			servicoInteratividade.setTipoLinha(new TipoLinha(new Integer(servicoInteratividadeTO.getTipoLinhaTO().getIdTipoLinha()), servicoInteratividadeTO.getTipoLinhaTO().getSgTipoLinha()));
			
			servicoInteratividade.setIdServicoInteratividade(servicoInteratividadeTO.getIdServicoInteratividade());
			
		}
		
		return servicoInteratividade;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ServicoInteratividadeDAO#createUpdateServicoInteratividade(com.indracompany.catalogo.to.ServicoInteratividadeTO)
	 */
	public ServicoInteratividadeTO createUpdateServicoInteratividade(ServicoInteratividadeTO servicoInteratividadeTO) throws DAOException {
		logger.debug("servicoInteratividadeTO: " + servicoInteratividadeTO);
		
		ServicoInteratividadeTOBuilder servicoInteratividadeTOBuilder = new ServicoInteratividadeTOBuilder();
		
		ServicoInteratividadeTO servicoInteratividadeResultTO = null;
		
		try {			
			// Método responsável em transformar um TO em um Entity.
			ServicoInteratividade si2 = servicoInteratividadeTOBuilder.createServicoInteratividade(servicoInteratividadeTO);
			
			ServicoInteratividade si = em.merge(si2); 			
			
			// Método responsável em transformar um Entity em um TO.
			servicoInteratividadeResultTO = servicoInteratividadeTOBuilder.createServicoInteratividadeTO(si);

		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return servicoInteratividadeResultTO;
	}
	
	
	public ServicoInteratividade createServicointeratividade (ServicoInteratividade si) throws DAOException {
		
		ServicoInteratividade createdSi = new ServicoInteratividade();
		try {
			
			createdSi = em.merge(si);
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return createdSi;
	}
	
	/* (non-Javadoc)
	 * @see com.indracompany.catalogo.dao.interfaces.ServicoInteratividadeDAO#findById(com.indracompany.catalogo.to.ServicoInteratividadeTO)
	 */
	public ServicoInteratividadeTO findById(ServicoInteratividadeTO servicoInteratividadeTO) throws DAOException {
		logger.debug("servicoInteratividadeTO: " + servicoInteratividadeTO);
		
		ServicoInteratividadeTO servicoInteratividadeResultTO = null;
		ServicoInteratividadeTOBuilder servicoInteratividadeTOBuilder = new ServicoInteratividadeTOBuilder();
		
		try {
			
			ServicoInteratividade si = em.find(ServicoInteratividade.class, servicoInteratividadeTO.getIdServicoInteratividade());
			servicoInteratividadeResultTO = servicoInteratividadeTOBuilder.createServicoInteratividadeTO(si);
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return servicoInteratividadeResultTO;
	}
	
	@SuppressWarnings("unchecked")
	public Integer validarServicointeratividade(ServicoInteratividadeTO servicoInteratividadeTO) throws DAOException  { 
		
		List<ServicoInteratividade> siList = new ArrayList<ServicoInteratividade>();
		Integer idServicointeratividade = null;
		String nmServico = servicoInteratividadeTO.getNmServico();
		String cdFuncionalidade = servicoInteratividadeTO.getFuncionalidadeTO().getCdFuncionalidade();
		String url = servicoInteratividadeTO.getUrl();
		String tpRede = servicoInteratividadeTO.getTipoLinhaTO().getSgTipoLinha();
		
		try {
			
			StringBuffer queryStr = new StringBuffer();
			
			queryStr.append("select si from ServicoInteratividade si where si.nmServico = :nmServico and si.funcionalidade.cdFuncionalidade = :cdFuncionalidade and si.url = :url and si.tipoLinha.sgTipoLinha = :sgTipoLinha");
			Query query = em.createQuery(queryStr.toString());


			query.setParameter("nmServico", nmServico);
			query.setParameter("cdFuncionalidade", cdFuncionalidade);
			query.setParameter("url" , url);
			query.setParameter("sgTipoLinha" , tpRede);
			
			siList = query.getResultList();
			
			if (siList != null && !siList.isEmpty()) {
				idServicointeratividade = siList.get(0).getIdServicoInteratividade();				
			}			
			
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return idServicointeratividade;
		
	}
	
	
	
}
