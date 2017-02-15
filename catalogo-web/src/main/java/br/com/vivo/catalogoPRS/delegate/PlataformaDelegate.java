package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.Plataforma;
import com.indracompany.catalogo.ejb.plataforma.PlataformaBeanLocal;
import com.indracompany.catalogo.to.PlataformaTO;

/**
 * @author Luiz Pereira
 * 
 * Classe Responsável em delegar a função para um EJB,
 * fazendo lookup do mesmo.
 */
public class PlataformaDelegate {
	
	private static Logger logger = Logger.getLogger(PlataformaDelegate.class);
	
	/**
	 * @return
	 */
	public List<PlataformaTO> findAll() {
		
		List<PlataformaTO> plataformaTOList = null;
		
		try {
			PlataformaBeanLocal plataformaBeanLocal = (PlataformaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PlataformaBeanLocal.JNDI_NAME);
			plataformaTOList = plataformaBeanLocal.findAll();
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return plataformaTOList;
	}
	
	/**
	 * @param idPlataformas
	 * @return
	 */
	public List<PlataformaTO> findAllWithExpections(Integer[] idPlataformas) {
		List<PlataformaTO> plataformaTOList = null;
		
		try {
			PlataformaBeanLocal plataformaBeanLocal = (PlataformaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PlataformaBeanLocal.JNDI_NAME);
			plataformaTOList = plataformaBeanLocal.findAllWithExpections(idPlataformas);
			
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAllWithExpections]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		
		return plataformaTOList;
	}

    public List<PlataformaTO> findByIdCanalAtendimento(Integer IdCanalAtendimento) {
        List<PlataformaTO> plataformaTOList = null;
        
        try {
            PlataformaBeanLocal plataformaBeanLocal = (PlataformaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PlataformaBeanLocal.JNDI_NAME);
            plataformaTOList = plataformaBeanLocal.findByIdCanalAtendimento(IdCanalAtendimento);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [findByIdCanalAtendimento]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
        
        return plataformaTOList;
    }
    
    public Plataforma findById(Integer idPlataforma) {
    	Plataforma plataforma = new Plataforma();
    	
    	try {
			PlataformaBeanLocal plataformaBeanLocal = (PlataformaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PlataformaBeanLocal.JNDI_NAME);
			plataforma = plataformaBeanLocal.findById(idPlataforma);
						
		} catch (ServiceLocatorException e) {
			logger.error(e);
			e.printStackTrace();
		} catch (BusinessException e) {
            logger.error(e);
        }
    	
    	return plataforma;
    }
}
