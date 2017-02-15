package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.classificacaocategoriascore.ClassificacaoCategoriaScoreBeanLocal;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ClassificacaoCategoriaScoreTO;

public class ClassificacaoCategoriaScoreDelegate {
	
	private static Logger logger = Logger.getLogger(ClassificacaoCategoriaScoreDelegate.class);
	
	public ClassificacaoCategoriaScoreTO findById(ClassificacaoCategoriaScoreTO classificacaoCategoriaScoreTO) throws DAOException {
		
		return null;
	}
	
	public List<ClassificacaoCategoriaScoreTO> findAll() {
		
		List<ClassificacaoCategoriaScoreTO> classificacaoCategoriaScoreTOList = null;
		
		try {
			ClassificacaoCategoriaScoreBeanLocal classificacaoCategoriaScoreBeanLocal = (ClassificacaoCategoriaScoreBeanLocal) ServiceLocator.getInstance().getEJBLocal(ClassificacaoCategoriaScoreBeanLocal.JNDI_NAME);
			classificacaoCategoriaScoreTOList = classificacaoCategoriaScoreBeanLocal.findAll();
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [findAll]",e);
		} catch (BusinessException e) {
			logger.error(e); 
		}
		
		return classificacaoCategoriaScoreTOList;
	}
	

}
