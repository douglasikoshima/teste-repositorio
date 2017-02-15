package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.analisecreditolinha.AnaliseCreditoLinhaBeanLocal;
import com.indracompany.catalogo.to.AnaliseCreditoLinhaTO;
import com.indracompany.catalogo.to.AnaliseCreditoTO;

public class AnaliseCreditoLinhaDelegate {

    private static Logger log = Logger.getLogger(AnaliseCreditoLinhaDelegate.class);

    public List<AnaliseCreditoLinhaTO> search(AnaliseCreditoLinhaTO analiseCreditoLinhaTO, List<AnaliseCreditoTO> analiseCreditoTOListScore) {
        log.debug("Delegando EJB.");
        try {
            return ((AnaliseCreditoLinhaBeanLocal) ServiceLocator.getInstance().getEJBLocal(AnaliseCreditoLinhaBeanLocal.JNDI_NAME)).search(
                    analiseCreditoLinhaTO, analiseCreditoTOListScore);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [search]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<AnaliseCreditoLinhaTO> loadServicoLinha() {
        log.debug("Delegando EJB.");
        try {
            return ((AnaliseCreditoLinhaBeanLocal) ServiceLocator.getInstance().getEJBLocal(AnaliseCreditoLinhaBeanLocal.JNDI_NAME))
                    .loadServicoLinha();
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [search]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<AnaliseCreditoTO> loadScore() {
        log.debug("Delegando EJB.");
        try {
            return ((AnaliseCreditoLinhaBeanLocal) ServiceLocator.getInstance().getEJBLocal(AnaliseCreditoLinhaBeanLocal.JNDI_NAME)).loadScore();
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [search]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public void gravar(AnaliseCreditoLinhaTO analiseCreditoLinhaTO) {
        log.debug("Delegando EJB.");
        try {
             ((AnaliseCreditoLinhaBeanLocal) ServiceLocator.getInstance().getEJBLocal(AnaliseCreditoLinhaBeanLocal.JNDI_NAME)).gravar(
                    analiseCreditoLinhaTO);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [search]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
    }

}
