package com.indracompany.catalogo.ejb.analisecreditopriorizar;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.AnaliseCreditoPriorizarDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AnalisePriorizarTO;

@Stateless(name = "AnaliseCreditoPriorizarBean", mappedName = "AnaliseCreditoPriorizarBean")
@Session(ejbName = "AnaliseCreditoPriorizarBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class AnaliseCreditoPriorizarBean implements AnaliseCreditoPriorizarBeanLocal {
    
    @EJB
    private AnaliseCreditoPriorizarDAO analiseCreditoPriorizarDAO;
    
    public List<AnalisePriorizarTO> pesquisarOferta(Integer idAnaliseCredito, Integer idEps) throws BusinessException {
        try {
            return this.analiseCreditoPriorizarDAO.pesquisarOferta(idAnaliseCredito, idEps);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public void gravarPriorizacao(Integer idAnaliseCredito, List<Integer> idOfertafixaCreditoScoreList, Integer idEps, List<AnalisePriorizarTO> analisePriTOListRemove) throws BusinessException {
        try {
            this.analiseCreditoPriorizarDAO.gravarPriorizacao(idAnaliseCredito, idOfertafixaCreditoScoreList, idEps, analisePriTOListRemove);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
}
