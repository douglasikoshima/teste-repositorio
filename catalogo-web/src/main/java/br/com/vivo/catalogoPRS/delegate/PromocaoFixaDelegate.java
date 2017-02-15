package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.promocaofixa.PromocaoFixaBeanLocal;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.ComposicaoPromocaoTO;
import com.indracompany.catalogo.to.DisponibilidadePromocaoFixaTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.PromocaoTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;
import com.indracompany.catalogo.to.ValorPoliticaPromocaoTO;

public class PromocaoFixaDelegate {

    private static Logger log = Logger.getLogger(PromocaoFixaDelegate.class);

    public List<PromocaoTO> search(PromocaoTO promocaoTO) {
        log.debug(promocaoTO);
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).search(promocaoTO);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [search]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public PromocaoTO reccord(PromocaoTO promocaoTO) {
        log.debug(promocaoTO);
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).reccord(promocaoTO);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [reccord]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<ComposicaoPromocaoTO> findComposicaoByIdPromocao(Integer idPromocao) {
        log.debug(idPromocao);
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).findComposicaoByIdPromocao(idPromocao);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [findComposicaoByIdPromocao]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<TipoServicoTO> findTipoServicoTO() {
        log.debug("todos tipo servicos para promocao");
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).findTipoServicoTO();
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [findTipoServicoTO]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<ServicoFixaTO> findServicoByTipoServico(Integer idTipoServico) {
        log.debug(idTipoServico);
        try {
            List<ServicoFixaTO> findServicoByTipoServico = ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).findServicoByTipoServico(idTipoServico);
            return findServicoByTipoServico;
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [findServicoByTipoServico]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<ServicoFixaTO> findServicoDescontoByIdServico(Integer idServico) {
        log.debug(idServico);
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).findServicoDescontoByIdServico(idServico);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [findServicoDescontoByIdServico]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<SolicitacaoComercialFixaTO> findSolicitacaoComercialByIdServico(Integer idServico) {
        log.debug(idServico);
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).findSolicitacaoComercialByIdServico(idServico);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [findServicoDescontoByIdServico]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public boolean removeComposicaoById(List<ComposicaoPromocaoTO> composicaoPromocaoTOList) {
        log.debug(composicaoPromocaoTOList);
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).removeComposicaoById(composicaoPromocaoTOList);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [removeComposicaoById]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
		return false;
    }

    public boolean persist(List<ComposicaoPromocaoTO> composicaoPromocaoTOList) {
        log.debug(composicaoPromocaoTOList);
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).persist(composicaoPromocaoTOList);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [persist]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return false;
    }

    public List<DisponibilidadePromocaoFixaTO> obterCanalVendaPorPromocao(Integer idPromocao) {
        log.debug(idPromocao);
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).obterCanalVendaPorPromocao(idPromocao);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [persist]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }
    
    public void removeDisponibilidade(List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListPersist, List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListRemove) {
        log.debug(valorPoliticaPromocaoTOListPersist);
        log.debug(valorPoliticaPromocaoTOListRemove);
        try {
            ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).removeDisponibilidade(valorPoliticaPromocaoTOListPersist, valorPoliticaPromocaoTOListRemove);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [persist]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        
    }

    public void persistDisponibilidade(List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListPersist, List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListRemove) {
        log.debug(valorPoliticaPromocaoTOListPersist);
        log.debug(valorPoliticaPromocaoTOListRemove);
        try {
            ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).persistDisponibilidade(valorPoliticaPromocaoTOListPersist, valorPoliticaPromocaoTOListRemove);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [persist]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        
    }

    public void remove(Integer idPromocao) {
        log.debug(idPromocao);
        try {
            ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).remove(idPromocao);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [remove]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
    }

    public PromocaoTO findById(Integer idPromocao) {
        log.debug(idPromocao);
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).findById(idPromocao);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [findById]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public PromocaoTO findByCodigoExceptId(String codigo, Integer idPromocao) {
        log.debug(String.format("codigo:%s idPromocao:%s", codigo, idPromocao));
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).findByCodigoExceptId(codigo, idPromocao);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [findByCodigoExceptId]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public PromocaoTO findByNameExceptId(String nome, Integer idPromocao) {
        log.debug(String.format("nome:%s idPromocao:%s", nome, idPromocao));
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).findByNameExceptId(nome, idPromocao);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [findByNameExceptId]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<PromocaoTO> reload(List<PromocaoTO> promocaoTOList) {
        log.debug(promocaoTOList);
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).reload(promocaoTOList);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [reload]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }
    
    public List<EpsTO> listEpsTO() {
        log.debug("realizando lookup de [listEpsTO]");
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).listEpsTO();
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [listEpsTO]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    } 
    
    public List<CanalVendaTO> searchCanalVendaTO(CanalVendaTO canalVendaTO) {
        log.debug("realizando lookup de [searchCanalVendaTO]");
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).searchCanalVendaTO(canalVendaTO);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [searchCanalVendaTO]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
    
    public CanalVendaTO findCanalVendaTOById(Long idCanalVenda) {
        log.debug("realizando lookup de [findCanalVendaTOById]");
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).findCanalVendaTOById(idCanalVenda);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [findCanalVendaTOById]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    } 
    
    public List<DisponibilidadePromocaoFixaTO> findCanalVendaPorPromocaoById(Integer idCanalVenda) {
        log.debug(idCanalVenda);
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).findCanalVendaPorPromocaoById(idCanalVenda);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [persist]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }    
    
    public List<DisponibilidadePromocaoFixaTO> findCanalVendaPorPromocaoById(Integer idCanalVenda, Integer idPromocao) {
        log.debug(idCanalVenda);
        try {
            return ((PromocaoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(PromocaoFixaBeanLocal.JNDI_NAME)).findCanalVendaPorPromocaoById(idCanalVenda, idPromocao);
        } catch (ServiceLocatorException e) {
            String msg = "Erro ao realizar o lookup de [persist]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }   
}
