package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.ServicoServicoArqDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoServicoArqTOBuilder;
import com.indracompany.catalogo.datalayer.ServicoServicoArq;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.ServicoServicoArqItemTO;

/**
 * @author equipe Catalogo
 * 
 * Classe responsável em fazer a persistencia no banco de dados de importacao de ServicoServicoArq.
 */
@Stateless
public class ServicoServicoArqDAOImpl implements ServicoServicoArqDAO {
	
	private static Logger logger = Logger.getLogger(ServicoServicoArqDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	public void createUpdateServicoServicoArq(ServicoServicoArq servicoServicoArq) throws DAOException {
		
		try {
			em.persist(servicoServicoArq);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<ImportacaoServicoFixaTO> search(ImportacaoServicoFixaTO to) throws DAOException {
		logger.info("ImportacaoServicoFixaTO: " + to);
		
		StringBuilder queryStr = new StringBuilder();
		List<ImportacaoServicoFixaTO> result = null; 
		
		try {
			
			queryStr.append("select ssa from ServicoServicoArq ssa where 1=1 ");
			if(to.getStatusArquivoImportacaoTO() != null && to.getStatusArquivoImportacaoTO().getIdStatusArquivoImportacao().intValue() != 0)
				queryStr.append("and ssa.matrizOfertaStatusImportacao.idMatrizOfertaStatusImportacao = :idMatrizOfertaStatusImportacao ");
			if(to.getNmArquivo() != null)
				queryStr.append("and upper(ssa.nmArquivo) like :nmArquivo ");			
			if(to.getNmUsuarioImportacao() != null)
				queryStr.append("and ssa.nmUsuarioImportacao = :nmUsuarioImportacao ");			
			if(to.getDtImportacaoInicial() != null)
				queryStr.append("and ssa.dtImportacao >= :dtImportacaoInicial ");
			if(to.getDtImportacaoFinal() != null)
				queryStr.append("and ssa.dtImportacao <= :dtImportacaoFinal ");
			queryStr.append("order by ssa.dtImportacao desc ");
			
			Query query = em.createQuery(queryStr.toString());
			
			if(to.getStatusArquivoImportacaoTO() != null && to.getStatusArquivoImportacaoTO().getIdStatusArquivoImportacao().intValue() != 0)
				query.setParameter("idMatrizOfertaStatusImportacao", to.getStatusArquivoImportacaoTO().getIdStatusArquivoImportacao());
			if(to.getNmArquivo() != null)
				query.setParameter("nmArquivo", "%"+to.getNmArquivo().toUpperCase()+"%");
			if(to.getNmUsuarioImportacao() != null)
				query.setParameter("nmUsuarioImportacao", to.getNmUsuarioImportacao());			
			if(to.getDtImportacaoInicial() != null)
				query.setParameter("dtImportacaoInicial", to.getDtImportacaoInicial());
			if(to.getDtImportacaoFinal() != null)
				query.setParameter("dtImportacaoFinal", to.getDtImportacaoFinal());
			
			result = new ServicoServicoArqTOBuilder().createTOList(query.getResultList());
		} catch(Exception e){
			throw new DAOException(e);
		}
		
		return result;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<ServicoServicoArqItemTO> searchCritica(Integer idArquivo) throws DAOException {

		StringBuffer queryStr = new StringBuffer();		
		queryStr
		.append("SELECT ")
		.append(" IDARQUIVO, IDITEM, ACAO, CDSERVICOMESTRE, SGTIPORELACIONAMENTO, CDSERVICOSUBORDINADO, ERROS ")
		.append("FROM ")
		.append(" CATALOGOPRS_OW.VW_FIXA_IMPORT_WEB_RELSERV V ")
		.append("WHERE ")
		.append(" V.IDARQUIVO = :idArquivo ");
	
		Query query = em.createNativeQuery(queryStr.toString());
		query.setParameter("idArquivo", idArquivo);
		
		List<ServicoServicoArqItemTO> list = new ServicoServicoArqTOBuilder().createTOListNative(query.getResultList());		
		return list;
	}
	
	
	@SuppressWarnings("unchecked")
	public List<String> exportServicoServicoArq() throws DAOException {
		logger.debug("exportContratoMatrizOferta");
		
		List<String> listRetorno = null;
		
		try {
			
			// REALIZADO COM NATIVE QUERY POIS NÃO HÁ CONCATENAÇÃO DE RESULTADO NO JPA
			StringBuffer queryStr = new StringBuffer();
			queryStr.append("SELECT ");
			queryStr.append("  (SELECT CDCODIGO FROM CATALOGOPRS_OW.SISTEMAPLANO SP WHERE SP.IDSISTEMAPLANO = MOTC.IDSISTEMAPLANO) || ';' ||  ");
			queryStr.append("(SELECT CDCODIGO FROM CATALOGOPRS_OW.SISTEMASERVICO SS WHERE SS.IDSISTEMASERVICO = MOTC.IDSISTEMASERVICO) || ';' || ");
			queryStr.append("(SELECT NMCSA FROM CATALOGOPRS_OW.CSA CSA WHERE CSA.IDCSA = MOTC.IDCSA) || ';' || ");
			queryStr.append("MOTC.VALOR AS CONCATENADO ");
			queryStr.append(" FROM ");
			queryStr.append(" CATALOGOPRS_OW.MATRIZOFERTATIPOCONTRATO MOTC ");
			
			Query query = em.createNativeQuery(queryStr.toString());
			listRetorno = query.getResultList();
			
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listRetorno;
	} 

	
	@SuppressWarnings("unchecked")
	public List<String> exportServicoServicoArqCriticas() throws DAOException {
		logger.debug("exportContratoMatrizOfertaCritica");
		
		List<String> listRetorno = null;
		
		try {
			
			StringBuffer queryStr = new StringBuffer(); 
			queryStr.append("SELECT CMO.CODIGOPLANO || ';' || CMO.CODIGOSERVICO || ';' || CMO.SIGLACSA || ';' || CMO.VALORCONTRATO || ';' || MOCC.DSCCRITICAIMPORTACAO AS CONCATENADO ");
			queryStr.append("FROM ");
			queryStr.append("	CATALOGOPRS_OW.CONTRATOMATRIZOFERTA CMO ");
			queryStr.append("   LEFT JOIN CATALOGOPRS_OW.CONTRATOMATRIZOFERTACRITICA CMOC ON (CMO.IDCONTRATOMATRIZ = CMOC.IDCONTRATOMATRIZ) ");
			queryStr.append("   LEFT JOIN CATALOGOPRS_OW.MATRIZOFERTACONTRATOCRITICA MOCC ON (CMOC.IDMATRIZOFERTAARQUIVOCRITICA = MOCC.IDMATRIZOFERTAARQUIVOCRITICA) ");
			queryStr.append("ORDER BY CMO.NUMEROLINHA ");
			
			// REALIZADO COM NATIVE QUERY POIS NÃO HÁ CONCATENAÇÃO DE RESULTADO NO JPA
			Query query = em.createNativeQuery(queryStr.toString());
			listRetorno = query.getResultList();
			
		} catch (NoResultException e) { 
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return listRetorno;
	}
	
	
}
