package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;

import com.indracompany.catalogo.ejb.servicofixa.ServicoFixaBeanLocal;
import com.indracompany.catalogo.to.RelacionamentoServicoFixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.ServicoServicoTO;
import com.indracompany.catalogo.to.TipoRelacionamentoTO;
import com.indracompany.catalogo.to.TipoServicoTO;

public class ServicoFixaDelegate {

    private static Logger log = Logger.getLogger(ServicoFixaDelegate.class);
    
    public List<ServicoFixaTO> search(ServicoFixaTO servicoFixaTO) {
        log.debug(servicoFixaTO);
        List<ServicoFixaTO> servicoFixaTOList;
        try {
            servicoFixaTOList = ((ServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoFixaBeanLocal.JNDI_NAME)).search(servicoFixaTO);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [search]", e);
        } catch (BusinessException e) {
            servicoFixaTOList = null;
            log.error(e);
        }
        return servicoFixaTOList;
    }

    public String changeStatus(Integer id) {
        log.debug(String.format("id:%s", id));
        try {
            return ((ServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoFixaBeanLocal.JNDI_NAME)).changeStatus(id);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [changeStatus]", e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<RelacionamentoServicoFixaTO> searchRelationship(RelacionamentoServicoFixaTO relacionamentoServicoFixaTO) {
        log.debug(String.format("relacionamentoServicoFixaTO:%s", relacionamentoServicoFixaTO));
        try {
            return ((ServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoFixaBeanLocal.JNDI_NAME)).searchRelationship(relacionamentoServicoFixaTO);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [searchRelationship]", e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<TipoRelacionamentoTO> findTipoRelacionamentoInsertFixa() {
        try {
            return ((ServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoFixaBeanLocal.JNDI_NAME)).findTipoRelacionamentoInsertFixa();
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [findAllTpRelacionamentoServico]", e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<TipoServicoTO> findAllTpServico() {
        try {
            return ((ServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoFixaBeanLocal.JNDI_NAME)).findAllTpServico();
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [findAllTpServico]", e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public String changeStatusRelationship(Integer idRelacionamento) throws CatalogoPRSException {
        try {
            return ((ServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoFixaBeanLocal.JNDI_NAME)).changeStatusRelationship(idRelacionamento);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [searchRelationship]", e);
        } catch (BusinessException e) {
            throw new CatalogoPRSException(e.getMessage());
        }
    }

    public void removeRelationship(Integer idRelacionamento) throws CatalogoPRSException {
        try {
            ((ServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoFixaBeanLocal.JNDI_NAME)).removeRelationship(idRelacionamento);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [removeRelationship]", e);
        } catch (BusinessException e) {
            throw new CatalogoPRSException(e.getMessage());
        }
    }

    public void gravarRelacionamento(List<ServicoServicoTO> servicoServicoTOList) {
        try {
            ((ServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoFixaBeanLocal.JNDI_NAME)).gravarRelacionamento(servicoServicoTOList);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [gravarRelacionamento]", e);
        }
    }

    public Integer findIdSistemaByIdServico(Integer idServico) {
    	Integer result = null;
        try {
        	result = ((ServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicoFixaBeanLocal.JNDI_NAME)).findIdSistemaByIdServico(idServico);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [findIdSistemaByIdServico]", e);
        } catch (BusinessException e) {
            log.error(e.getMessage());
        }
        return result;
    }

    
}
