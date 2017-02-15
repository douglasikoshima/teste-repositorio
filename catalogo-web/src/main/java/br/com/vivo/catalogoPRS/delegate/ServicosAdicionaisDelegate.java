package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.servicosadicionais.ServicosAdicionaisBeanLocal;
import com.indracompany.catalogo.to.ServicosAdicionaisTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;

public class ServicosAdicionaisDelegate {

    private static Logger log = Logger.getLogger(ServicosAdicionaisDelegate.class);

    public List<ServicosAdicionaisTO> search() {
        log.debug("Delegando EJB.");
        try {
            return ((ServicosAdicionaisBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicosAdicionaisBeanLocal.JNDI_NAME))
                    .search();
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [search]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<TipoServicoTO> loadTipoServico() {
        log.debug("Delegate para loadTipoServico.");
        try {
            return ((ServicosAdicionaisBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicosAdicionaisBeanLocal.JNDI_NAME)).loadTipoServico();
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [loadTipoServicoTO]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<ServicosAdicionaisTO> findServicosByIdTipoServico(ServicosAdicionaisTO servicosAdicionaisTO) {
        log.debug("Delegate para findServicosByIdTipoServico.");
        try {
            return ((ServicosAdicionaisBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicosAdicionaisBeanLocal.JNDI_NAME))
                    .findServicosByIdTipoServico(servicosAdicionaisTO);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [findServicosByIdTipoServico]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<SolicitacaoComercialFixaTO> findSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) {
        log.debug("Delegate para loadTipoServico.");
        try {
            return ((ServicosAdicionaisBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicosAdicionaisBeanLocal.JNDI_NAME))
                    .findSolicitacaoComercial(servicosAdicionaisTO);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [loadSolicitacaoComercial]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<ServicosAdicionaisTO> recordSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) {
        log.debug("Delegate para recordSolicitacaoComercial.");
        try {
            return ((ServicosAdicionaisBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicosAdicionaisBeanLocal.JNDI_NAME))
                    .recordSolicitacaoComercial(servicosAdicionaisTO);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [recordSolicitacaoComercial]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<ServicosAdicionaisTO> deleteSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) {
        log.debug("Delegate para deleteSolicitacaoComercial.");
        try {
            return ((ServicosAdicionaisBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicosAdicionaisBeanLocal.JNDI_NAME))
                    .deleteSolicitacaoComercial(servicosAdicionaisTO);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [recordSolicitacaoComercial]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public Boolean existsSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) {
        log.debug("Delegate para existsSolicitacaoComercial.");
        try {
            return ((ServicosAdicionaisBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicosAdicionaisBeanLocal.JNDI_NAME))
                    .existsSolicitacaoComercial(servicosAdicionaisTO);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [existsSolicitacaoComercial]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return false;
    }

    public ServicosAdicionaisTO load(Integer idServicosAdicionais) {
        log.debug("Delegate para load.");
        try {
            return ((ServicosAdicionaisBeanLocal) ServiceLocator.getInstance().getEJBLocal(ServicosAdicionaisBeanLocal.JNDI_NAME))
                    .load(idServicosAdicionais);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [load]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }
}
