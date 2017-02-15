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

import com.indracompany.catalogo.dao.interfaces.OfertaVLArqDAO;
import com.indracompany.catalogo.datalayer.OfertaVLArq;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.OfertaVLArqItemTO;
import com.indracompany.catalogo.to.OfertaVLFArqItemTO;

@Stateless
public class OfertaVLArqDAOImpl implements OfertaVLArqDAO{
	
    private static Logger log = Logger.getLogger(OfertaVLArqDAOImpl.class);

    @PersistenceContext
    private EntityManager em;	

    public void merge(OfertaVLArq servicoArq) throws DAOException {
        log.debug(servicoArq);
        try {
            em.merge(servicoArq);
        } catch (Exception e) {
            String msg = String.format("Erro ao persistir dados para o arquivo %s", servicoArq.getNmArquivo());
            log.error(msg, e);
            throw new DAOException(msg);
        }
    }

    @SuppressWarnings("unchecked")
	public List<ImportacaoServicoFixaTO> search(ImportacaoServicoFixaTO to, String tpImportacao) throws DAOException {
        log.info("ImportacaoServicoFixaTO: " + to);
        List<ImportacaoServicoFixaTO> importacaoServicoFixaTOList;

        try {
            StringBuilder hql = new StringBuilder("select ovla from OfertaVLArq ovla where 1=1 ");
            Map<String, Object> params = new HashMap<String, Object>();
            
            if (tpImportacao.toString() != null) {
                hql.append("and ovla.tpImportacao = :tpImportacao ");
                params.put("tpImportacao", tpImportacao.toString());
            }
            
            if (to.getStatusArquivoImportacaoTO() != null) {
                hql.append("and ovla.matrizOfertaStatusImportacao.idMatrizOfertaStatusImportacao = :idMatrizOfertaStatusImportacao ");
                params.put("idMatrizOfertaStatusImportacao", to.getStatusArquivoImportacaoTO().getIdStatusArquivoImportacao());
            }
            if (!StringUtils.isBlank(to.getNmArquivo())) {
                hql.append("and lower(ovla.nmArquivo) like lower(:nmArquivo) ");
                params.put("nmArquivo", String.format("%s%s%s", "%", to.getNmArquivo(), "%"));
            }
            if (!StringUtils.isBlank(to.getNmUsuarioImportacao())) {
                hql.append("and ovla.nmUsuarioImportacao = :nmUsuarioImportacao ");
                params.put("nmUsuarioImportacao", to.getNmUsuarioImportacao());
            }
            if (to.getDtImportacaoInicial() != null) {
                hql.append("and ovla.dtImportacao >= :dtImportacaoInicial ");
                params.put("dtImportacaoInicial", to.getDtImportacaoInicial());
            }
            if (to.getDtImportacaoFinal() != null) {
                hql.append("and ovla.dtImportacao <= :dtImportacaoFinal ");
                params.put("dtImportacaoFinal", to.getDtImportacaoFinal());
                
            }
            hql.append("order by ovla.dtImportacao desc ");
            
            importacaoServicoFixaTOList = new OfertaVLArqTOBuilder().buildTOList(this.getQuery(hql.toString(), params).getResultList());
        } catch (Exception e) {
            throw new DAOException(e);
        }

        return importacaoServicoFixaTOList;
	}
	
    private Query getQuery(String hql, Map<String, Object> params) {
        Query query = em.createQuery(hql);
        for (String param : params.keySet()) {
            Object paramValue = params.get(param);
            query.setParameter(param, params.get(param));
            log.debug(String.format("%s=%s", param, paramValue));
        }
        return query;
    }

    @SuppressWarnings("unchecked")
    public List<OfertaVLArqItemTO> searchCriticaOferta(Integer idArquivo) throws DAOException {
        
        List<OfertaVLArqItemTO> ofertaVLArqItemTOList;
        try {
        	ofertaVLArqItemTOList = new OfertaVLArqItemTOBuilder().createTOList(em.createNamedQuery("VwFixaImportWebOferta.findByIdArquivo").setParameter("idArquivo", idArquivo).getResultList());
        } catch (Exception e) {
            String msg = String.format("Erro ao consultar criticas para arquivo %s", idArquivo);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        return ofertaVLArqItemTOList;
    }
    
    @SuppressWarnings("unchecked")
    public List<OfertaVLFArqItemTO> searchCriticaOfertaComplementar(Integer idArquivo) throws DAOException {
        
        List<OfertaVLFArqItemTO> ofertaVLFArqItemTOList;
        try {
        	ofertaVLFArqItemTOList = new OfertaVLFArqItemTOBuilder().createTOList(em.createNamedQuery("VwFixaImportWebOfertaComp.findByIdArquivo").setParameter("idArquivo", idArquivo).getResultList());
        } catch (Exception e) {
            String msg = String.format("Erro ao consultar criticas para arquivo %s", idArquivo);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        return ofertaVLFArqItemTOList;
    }	
    

}
