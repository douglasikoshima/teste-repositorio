package com.indracompany.catalogo.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.MatrizOfertaDAO;
import com.indracompany.catalogo.datalayer.MatrizOfertaArquivoCritica;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.MatrizOfertaArquivoCriticaTO;

@Stateless
public class MatrizOfertaDAOImpl implements MatrizOfertaDAO {
    private static Logger log = Logger.getLogger(MatrizOfertaDAOImpl.class);
    
    @PersistenceContext
    private EntityManager em;

    @SuppressWarnings("unchecked")
    public List<MatrizOfertaArquivoCriticaTO> obterMatrizOfertaArquivoCriticaTOPorIdMatrizOfertaArquivoItemList(long idMatrizOfertaArquivoItem) throws DAOException {
        try {
            List<MatrizOfertaArquivoCritica> matrizOfertaArquivoCriticaList = em.createNamedQuery("MatrizOfertaArquivoCritica.obterPorIdMatrizOfertaArquivoItem").setParameter("idMatrizOfertaArquivoItem", idMatrizOfertaArquivoItem).getResultList();
            return new MatrizOfertaArquivoCriticaTOBuilder().buildBasicTOList(matrizOfertaArquivoCriticaList);
        } catch (Exception e) {
            String msg = String.format("Erro ao obter criticas do item com id=%s", idMatrizOfertaArquivoItem);
            log.error(msg, e);
            throw new DAOException(msg,e);
        }
    }

}
