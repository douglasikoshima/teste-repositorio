package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;

import com.indracompany.catalogo.ejb.familia.FamiliaBeanLocal;
import com.indracompany.catalogo.to.FamiliaTO;

public class FamiliaDelegate {
    private static Logger logger = Logger.getLogger(FamiliaDelegate.class);
    
    public List<FamiliaTO> search(FamiliaTO familiaTO) {
        List<FamiliaTO> toList = null;
        try {
            toList = ((FamiliaBeanLocal) ServiceLocator.getInstance().getEJBLocal(FamiliaBeanLocal.JNDI_NAME)).search(familiaTO);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [search]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
        return toList;
    }

    public FamiliaTO findById(Integer id) {
        FamiliaTO to = null;
        try {
            to = ((FamiliaBeanLocal) ServiceLocator.getInstance().getEJBLocal(FamiliaBeanLocal.JNDI_NAME)).findById(id);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [findById]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
        return to;
    }

    public FamiliaTO insertUpdate(FamiliaTO familiaTO) throws CatalogoPRSException {
        try {
        	FamiliaBeanLocal familiaBeanLocal = ((FamiliaBeanLocal) ServiceLocator.getInstance().getEJBLocal(FamiliaBeanLocal.JNDI_NAME));
        	if( familiaBeanLocal.searchByName(familiaTO.getNmFamilia()) != null ) {
        		if (familiaTO.getIdFamilia() == null) {
        			throw new CatalogoPRSException("Essa Fam&iacute;lia j&aacute; existe!");
        		} else {
        			throw new CatalogoPRSException("Nome da Fam&iacute;lia j&aacute; existe");
        		}
        	}
			familiaTO = familiaBeanLocal.insertUpdate(familiaTO);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [insertUpdate]", e);
        } catch (BusinessException e) {
            familiaTO = null;
            logger.error(e);
        }
        return familiaTO;
    }
    
    public void remove(Integer id) throws CatalogoPRSException {
        try {
            FamiliaBeanLocal familiaBeanLocal = ((FamiliaBeanLocal) ServiceLocator.getInstance().getEJBLocal(FamiliaBeanLocal.JNDI_NAME));
            FamiliaTO to = familiaBeanLocal.findById(id);
            if(!to.getInCriacaoCatalogo()) {
                throw new CatalogoPRSException("Esta fam&iacute;lia pertence ao cadastro do legado");
            }
            if(to.getCategoriaTOListSize() > 0) {
                throw new CatalogoPRSException("N&atilde;o &eacute; poss&iacute;vel remover uma Fam&iacute;lia com Categorias");
            }
            familiaBeanLocal.remove(id);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [remove]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
    }
    
    public void changeStatus(Integer id) {
        try {
            ((FamiliaBeanLocal) ServiceLocator.getInstance().getEJBLocal(FamiliaBeanLocal.JNDI_NAME)).changeStatus(id);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [remove]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
    }
}
