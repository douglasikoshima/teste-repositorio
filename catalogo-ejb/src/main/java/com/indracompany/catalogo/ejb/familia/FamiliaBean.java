package com.indracompany.catalogo.ejb.familia;

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

import com.indracompany.catalogo.dao.interfaces.FamiliaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.FamiliaTO;

@Stateless(name = "FamiliaBean", mappedName = "FamiliaBean")
@Session(ejbName = "FamiliaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class FamiliaBean implements FamiliaBeanLocal {

    private static Logger logger = Logger.getLogger(FamiliaBean.class);
    
    @EJB
    private FamiliaDAO familiaDAO;
    
    public List<FamiliaTO> search(FamiliaTO familiaTO) throws BusinessException {
        logger.debug(String.format("familiaTO: %s", familiaTO));
        List<FamiliaTO> toList;
        try {
            toList = this.familiaDAO.search(familiaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return toList;
    }

    public FamiliaTO findById(Integer id) throws BusinessException {
        logger.debug(String.format("id: %s", id));
        FamiliaTO to;
        try {
            to = this.familiaDAO.findById(id);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return to;
    }

    public FamiliaTO insertUpdate(FamiliaTO familiaTO) throws BusinessException {
        logger.debug(String.format("familiaTO: %s", familiaTO));
        try {
            familiaTO = this.familiaDAO.insertUpdate(familiaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return familiaTO;
    }
    
    public void remove(Integer id) throws BusinessException {
        logger.debug(String.format("id: %s", id));
        try {
            this.familiaDAO.remove(id);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public void changeStatus(Integer id) throws BusinessException {
        logger.debug(String.format("id: %s", id));
        try {
            this.familiaDAO.changeStatus(id);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public FamiliaTO searchByName(String nmFamilia) throws BusinessException {
        logger.debug(String.format("nmFamilia: %s", nmFamilia));
        try {
            return this.familiaDAO.searchByName(nmFamilia);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
}
