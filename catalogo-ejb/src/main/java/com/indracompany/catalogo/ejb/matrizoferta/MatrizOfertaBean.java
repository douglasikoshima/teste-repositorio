package com.indracompany.catalogo.ejb.matrizoferta;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.MatrizOfertaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.MatrizOfertaArquivoCriticaTO;

@Stateless(name = "MatrizOfertaBean", mappedName = "MatrizOfertaBean")
@Session(ejbName = "MatrizOfertaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MatrizOfertaBean implements MatrizOfertaBeanLocal {
    private static Logger log = Logger.getLogger(MatrizOfertaBean.class);

    @EJB
    private MatrizOfertaDAO matrizOfertaDAO;
    
    public List<MatrizOfertaArquivoCriticaTO> obterMatrizOfertaArquivoCriticaTOPorIdMatrizOfertaArquivoItemList(long idMatrizOfertaArquivoItem) throws BusinessException {
        log.debug(String.format("idMatrizOfertaArquivoItem:%s", idMatrizOfertaArquivoItem));
        try {
            return matrizOfertaDAO.obterMatrizOfertaArquivoCriticaTOPorIdMatrizOfertaArquivoItemList(idMatrizOfertaArquivoItem);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

}