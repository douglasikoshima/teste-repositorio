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

import com.indracompany.catalogo.dao.interfaces.ServicoArqDAO;
import com.indracompany.catalogo.datalayer.ServicoArq;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.ServicoArqItemTO;

@Stateless
public class ServicoArqDAOImpl implements ServicoArqDAO {

    private static Logger log = Logger.getLogger(ServicoArqDAOImpl.class);

    @PersistenceContext
    private EntityManager em;

    public void merge(ServicoArq servicoArq) throws DAOException {
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
    public List<ImportacaoServicoFixaTO> search(ImportacaoServicoFixaTO to) throws DAOException {
        log.info("ImportacaoServicoFixaTO: " + to);
        List<ImportacaoServicoFixaTO> importacaoServicoFixaTOList;

        try {
            StringBuilder hql = new StringBuilder("select sa from ServicoArq sa where 1=1 ");
            Map<String, Object> params = new HashMap<String, Object>();
            if (to.getStatusArquivoImportacaoTO() != null) {
                hql.append("and sa.matrizOfertaStatusImportacao.idMatrizOfertaStatusImportacao = :idMatrizOfertaStatusImportacao ");
                params.put("idMatrizOfertaStatusImportacao", to.getStatusArquivoImportacaoTO().getIdStatusArquivoImportacao());
            }
            if (!StringUtils.isBlank(to.getNmArquivo())) {
                hql.append("and lower(sa.nmArquivo) like lower(:nmArquivo) ");
                params.put("nmArquivo", String.format("%s%s%s", "%", to.getNmArquivo(), "%"));
            }
            if (!StringUtils.isBlank(to.getNmUsuarioImportacao())) {
                hql.append("and sa.nmUsuarioImportacao = :nmUsuarioImportacao ");
                params.put("nmUsuarioImportacao", to.getNmUsuarioImportacao());
            }
            if (to.getDtImportacaoInicial() != null) {
                hql.append("and sa.dtImportacao >= :dtImportacaoInicial ");
                params.put("dtImportacaoInicial", to.getDtImportacaoInicial());
            }
            if (to.getDtImportacaoFinal() != null) {
                hql.append("and sa.dtImportacao <= :dtImportacaoFinal ");
                params.put("dtImportacaoFinal", to.getDtImportacaoFinal());
                
            }
            hql.append("order by sa.dtImportacao desc ");
            
            importacaoServicoFixaTOList = new ServicoArqTOBuilder().buildTOList(this.getQuery(hql.toString(), params).getResultList());
        } catch (Exception e) {
            throw new DAOException(e);
        }

        return importacaoServicoFixaTOList;
    }
    
    @SuppressWarnings("unchecked")
    public List<ServicoArqItemTO> searchCritica(Integer idArquivo) throws DAOException {
        
        List<ServicoArqItemTO> servicoArqItemTOList;
        try {
            servicoArqItemTOList = new ServicoArqItemTOBuilder().createTOList(em.createNamedQuery("VwFixaImportWebServico.findByIdArquivo").setParameter("idArquivo", idArquivo).getResultList());
        } catch (Exception e) {
            String msg = String.format("Erro ao consultar criticas para arquivo %s", idArquivo);
            log.error(msg, e);
            throw new DAOException(msg, e);
        }
        return servicoArqItemTOList;
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
}
