package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.SCEncargoGruComArqDAO;
import com.indracompany.catalogo.dao.interfaces.SCEncargoGruComArqTOBuilder;
import com.indracompany.catalogo.datalayer.SCEncargoGruComArq;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.SCEncargoGruComArqItemTO;

/**
 * @author equipe Catalogo
 * 
 * Classe responsável em fazer a persistencia no banco de dados de importacao de SCEncargoGruComArq.
 */
@Stateless
public class SCEncargoGruComArqDAOImpl implements SCEncargoGruComArqDAO {
	
	private static Logger logger = Logger.getLogger(SCEncargoGruComArqDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	
	
	public void createUpdateSCEncargoGruComArq(SCEncargoGruComArq arq) throws DAOException {
		logger.debug("SCEncargoGruComArq: " + arq);
		
		try {
			em.persist(arq);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	

	@SuppressWarnings("unchecked")
	public List<ImportacaoServicoFixaTO> search(ImportacaoServicoFixaTO to) throws DAOException {
		logger.debug("ImportacaoServicoFixaTO: " + to);
		
		StringBuilder queryStr = new StringBuilder();
		List<ImportacaoServicoFixaTO> result = null; 
		
		try {
			
			queryStr.append("select ssa from SCEncargoGruComArq ssa where 1=1 ");
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
			
			result = new SCEncargoGruComArqTOBuilder().createTOList(query.getResultList());
		} catch(Exception e){
			throw new DAOException(e);
		}
		
		return result;
	}
	

	@SuppressWarnings("unchecked")
	public List<SCEncargoGruComArqItemTO> searchCritica(Integer idArquivo) throws DAOException {

		StringBuffer queryStr = new StringBuffer();		
		queryStr
		.append("SELECT ")
		.append(" IDARQUIVO, IDITEM, ACAO, CDSOLICITACAOCOMERCIAL, CDENCARGO, CDPLANOFINANCIAMENTO, CDGRUPOCOMERCIAL, ERROS ")
		.append("FROM ")
		.append(" CATALOGOPRS_OW.VW_FIXA_IMPORT_WEB_SCENC V ")
		.append("WHERE ")
		.append(" V.IDARQUIVO = :idArquivo ");
	
		Query query = em.createNativeQuery(queryStr.toString());
		query.setParameter("idArquivo", idArquivo);
		
		List<SCEncargoGruComArqItemTO> list = new SCEncargoGruComArqTOBuilder().createTOListNative(query.getResultList());		
		return list;
	}
		
}
