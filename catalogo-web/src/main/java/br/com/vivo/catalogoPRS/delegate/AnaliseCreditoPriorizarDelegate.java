package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.analisecreditopriorizar.AnaliseCreditoPriorizarBeanLocal;
import com.indracompany.catalogo.to.AnalisePriorizarTO;

public class AnaliseCreditoPriorizarDelegate {

    private static Logger log = Logger.getLogger(AnaliseCreditoPriorizarDelegate.class);
    
    public List<AnalisePriorizarTO> pesquisarOferta(Integer idAnaliseCredito, Integer idEps) {
        log.debug("realizando o lookup de [pesquisarOferta]");
        List<AnalisePriorizarTO> analisePriorizarTOList = null;
        try {
            analisePriorizarTOList = ((AnaliseCreditoPriorizarBeanLocal) ServiceLocator.getInstance().getEJBLocal(AnaliseCreditoPriorizarBeanLocal.JNDI_NAME)).pesquisarOferta(idAnaliseCredito, idEps);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [pesquisarOferta]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return analisePriorizarTOList;
    }

    public void gravarPriorizacao(Integer idAnaliseCredito, List<Integer> idOfertafixaCreditoScoreList, Integer idEPS, List<AnalisePriorizarTO> analisePriTOListRemove) {
        log.debug("realizando o lookup de [gravarPriorizacao]");
        try {
            ((AnaliseCreditoPriorizarBeanLocal) ServiceLocator.getInstance().getEJBLocal(AnaliseCreditoPriorizarBeanLocal.JNDI_NAME)).gravarPriorizacao(idAnaliseCredito, idOfertafixaCreditoScoreList, idEPS, analisePriTOListRemove);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [gravarPriorizacao]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
    }
}
