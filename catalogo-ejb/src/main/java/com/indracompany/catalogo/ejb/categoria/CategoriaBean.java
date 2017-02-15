package com.indracompany.catalogo.ejb.categoria;

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

import com.indracompany.catalogo.dao.interfaces.CategoriaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategoriaTO;

@Stateless(name = "CategoriaBean", mappedName = "CategoriaBean")
@Session(ejbName = "CategoriaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CategoriaBean implements CategoriaBeanLocal {

    private static Logger logger = Logger.getLogger(CategoriaBean.class);
    
    @EJB
    private CategoriaDAO categoriaDAO;
    
    public List<CategoriaTO> search(CategoriaTO categoriaTO) throws BusinessException {
        logger.debug(String.format("categoriaTO: %s", categoriaTO));
        List<CategoriaTO> toList;
        try {
            toList = this.categoriaDAO.search(categoriaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return toList;
    }

    public CategoriaTO insertUpdate(CategoriaTO categoriaTO) throws BusinessException {
        logger.debug(String.format("categoriaTO: %s", categoriaTO));
        try {
            categoriaTO = this.categoriaDAO.insertUpdate(categoriaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return categoriaTO;
    }

    public void changeStatus(Integer id) throws BusinessException {
        logger.debug(String.format("id: %s", id));
        try {
            this.categoriaDAO.changeStatus(id);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public void remove(Integer id) throws BusinessException{
        logger.debug(String.format("id: %s", id));
        try {
            this.categoriaDAO.remove(id);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public CategoriaTO findById(Integer id) throws BusinessException {
        logger.debug(String.format("id: %s", id));
        try {
            return this.categoriaDAO.findById(id);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

	public CategoriaTO searchByDescription(String dsCategoria) throws BusinessException {
		logger.debug(String.format("Description: %s", dsCategoria));
		try {
			return this.categoriaDAO.searchByDescription(dsCategoria);
		} catch (DAOException e) {
			throw new EJBException(e);
		}
	}
    
    public Long contarServicoAssociado(Integer idCategoria) throws BusinessException {
        logger.debug(String.format("idCategoria: %s", idCategoria));
        try {
            return this.categoriaDAO.contarServicoAssociado(idCategoria);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }

	public List<CategoriaTO> listarGrupoServico(Integer indCatalogo) throws BusinessException {
		// TODO Auto-generated method stub
		logger.debug(String.format("indCatalogo: %s", indCatalogo));
        try {
            return this.categoriaDAO.listarGrupoServico(indCatalogo);
        } catch (DAOException e) {
            throw new EJBException(e);
        }  
	}

	public CategoriaTO mergeCatalogo(CategoriaTO catalogoTO, Integer indCatalogo) throws BusinessException {

		try{
			logger.debug("begin - mergeCatalogo");
			logger.debug(catalogoTO+" "+indCatalogo);
			logger.debug("end - mergeCatalogo");
			return this.categoriaDAO.mergeCatalogo(catalogoTO, indCatalogo);
		}catch (DAOException e) {
            throw new EJBException(e);
        }  
		
	}
    
	public void removeCatalogoMovel(Integer id) throws BusinessException {
		
		try{
			logger.debug("begin - removeCatalogoMovel");
			logger.debug(id);
			logger.debug("end - removeCatalogoMovel");
			this.categoriaDAO.removeCatalogoMovel(id);
		}catch (Exception e) {
            throw new EJBException(e);
        }  
	}
}
