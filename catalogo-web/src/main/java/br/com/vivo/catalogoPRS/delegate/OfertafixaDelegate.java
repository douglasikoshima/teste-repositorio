package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.ofertafixa.OfertafixaBeanLocal;
import com.indracompany.catalogo.to.AreaRegistroTO;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.CidadeTO;
import com.indracompany.catalogo.to.OfertafixaComplementarTO;
import com.indracompany.catalogo.to.OfertafixaDisponibilidadeTO;
import com.indracompany.catalogo.to.OfertafixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;

public class OfertafixaDelegate {

    private static Logger log = Logger.getLogger(OfertafixaDelegate.class);

    public List<ServicoFixaTO> searchServicoLinha() {
        log.debug("realizando o lookup de [searchServicoLinha]");
        List<ServicoFixaTO> servicoLinhaList = null;
        try {
            servicoLinhaList = ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).searchServicoLinha();
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [searchServicoLinha]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return servicoLinhaList;
    }

    public List<SolicitacaoComercialFixaTO> carregarSolicitacaoComercialPorServicoList(Integer idServicoFixa) {
        log.debug("realizando o lookup de [carregarSolicitacaoComercialPorServicoList]");
        List<SolicitacaoComercialFixaTO> solicitacaoComercialNormalList = null;
        try {
            solicitacaoComercialNormalList = ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).carregarSolicitacaoComercialPorServicoList(idServicoFixa);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [carregarSolicitacaoComercialPorServicoList]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return solicitacaoComercialNormalList;
    }

    public List<ServicoFixaTO> carregarServicoFixaTOPlanoList(Integer idServicoFixa) {
        log.debug("realizando o lookup de [carregarServicoPlanoList]");
        List<ServicoFixaTO> servicoFixaTOPlanoList = null;
        try {
            servicoFixaTOPlanoList = ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).carregarServicoFixaTOPlanoList(idServicoFixa);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [carregarServicoPlanoList]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return servicoFixaTOPlanoList;
    }

    public List<OfertafixaTO> search(OfertafixaTO ofertafixaTO) {
        log.debug("realizando lookup de [search]");
        List<OfertafixaTO> ofertafixaTOList = null;
        try {
            ofertafixaTOList = ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).search(ofertafixaTO);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [search]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return ofertafixaTOList;
    }

    public OfertafixaTO gravar(OfertafixaTO ofertafixaTO, OfertafixaDisponibilidadeTO ofertafixaDisponibilidadeTO) {
        log.debug("realizando lookup de [gravar]");
        OfertafixaTO ofertafixaTOResult = null;
        try {
            ofertafixaTOResult = ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).gravar(ofertafixaTO, ofertafixaDisponibilidadeTO);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [gravar]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return ofertafixaTOResult;
    }

    public OfertafixaTO findByCodigoExceptId(String cdOfertafixa, Integer idOfertafixa) {
        log.debug("realizando lookup de [findByCodigoExceptId]");
        OfertafixaTO ofertafixaTO = null;
        try {
            ofertafixaTO = ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).findByCodigoExceptId(cdOfertafixa, idOfertafixa);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [findByCodigoExceptId]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return ofertafixaTO;
    }

    public OfertafixaTO findByNameExceptId(String nmOfertafixa, Integer idOfertafixa) {
        log.debug("realizando lookup de [findByNameExceptId]");
        OfertafixaTO ofertafixaTO = null;
        try {
            ofertafixaTO = ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).findByNameExceptId(nmOfertafixa, idOfertafixa);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [findByNameExceptId]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return ofertafixaTO;
    }

    public void excluir(Integer idOfertafixa) {
        log.debug("realizando lookup de [excluir]");
        try {
            ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).excluir(idOfertafixa);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [excluir]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
    }

    public OfertafixaTO findByIdWithComplementar(Integer idOfertafixa) {
        log.debug("realizando lookup de [findByIdWithComplementar]");
        OfertafixaTO ofertafixaTO = null;
        try {
            ofertafixaTO = ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).findByIdWithComplementar(idOfertafixa);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [findByIdWithComplementar]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return ofertafixaTO;
    }

    public List<TipoServicoTO> carregarTipoServicoTOList() {
        log.debug("realizando lookup de [carregarTipoServicoTOList]");
        List<TipoServicoTO> tipoServicoTOList = null;
        try {
            tipoServicoTOList = ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).carregarTipoServicoTOList();
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [carregarTipoServicoTOList]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return tipoServicoTOList;
    }

    public List<ServicoFixaTO> findServicoByTipoServico(Integer idTipoServico, OfertafixaTO ofertafixaTO) {
        log.debug("realizando lookup de [findServicoByTipoServico]");
        List<ServicoFixaTO> tipoServicoTOList = null;
        try {
            tipoServicoTOList = ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).findServicoByTipoServico(idTipoServico, ofertafixaTO);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [findServicoByTipoServico]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return tipoServicoTOList;
    }

    public void excluirOfertaComplementar(Integer idOfertafixaComplementar) {
        log.debug("realizando lookup de [excluirOfertaComplementar]");
        try {
            ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).excluirOfertaComplementar(idOfertafixaComplementar);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [excluirOfertaComplementar]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
    }

    public OfertafixaDisponibilidadeTO carregarDisponibilidadeTO(Integer idOfertafixa) {
        log.debug("realizando lookup de [carregarDisponibilidadeTO]");
        try {
            return ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).carregarDisponibilidadeTO(idOfertafixa);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [carregarDisponibilidadeTO]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public List<OfertafixaTO> reload(List<OfertafixaTO> ofertafixaTOList) {
        log.debug("realizando lookup de [reload]");
        try {
            return ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).reload(ofertafixaTOList);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [reload]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

	public SolicitacaoComercialFixaTO findSolicitacaoComercialById(Long idSolicitacaoComercial) {
        log.debug("realizando lookup de [findSolicitacaoComercialById]");
        try {
            return ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).findSolicitacaoComercialById(idSolicitacaoComercial);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [findSolicitacaoComercialById]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
	}

	public CidadeTO findCidadeById(Integer idCidade) {
        log.debug("realizando lookup de [findCidadeById]");
        try {
            return ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).findCidadeById(idCidade);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [findCidadeById]";
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
            return ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).findCanalVendaTOById(idCanalVenda);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [findCanalVendaTOById]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public AreaRegistroTO findAreaRegistroTOById(Integer idAreaRegistro) {
        log.debug("realizando lookup de [findAreaRegistroTOById]");
        try {
            return ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).findAreaRegistroTOById(idAreaRegistro);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [findAreaRegistroTOById]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    public List<OfertafixaComplementarTO> findOfertafixaComplementarTOWithDependentes(Long idSolicitacaoComercial, Integer pzMaximoVigencia) {
        log.debug("realizando lookup de [findOfertafixaComplementarTOWithDependentes]");
        try {
            return ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).findOfertafixaComplementarTOWithDependentes(idSolicitacaoComercial, pzMaximoVigencia);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [findOfertafixaComplementarTOWithDependentes]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

	public List<Long> carregarIdCanalVendaListCompativel(Long idSolicitacaoComercial) {
        log.debug("realizando lookup de [carregarIdCanalVendaListCompativel]");
        try {
            return ((OfertafixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(OfertafixaBeanLocal.JNDI_NAME)).carregarIdCanalVendaListCompativel(idSolicitacaoComercial);
        } catch (ServiceLocatorException e) {
            String msg = "erro ao realizar o lookup de [carregarIdCanalVendaListCompativel]";
            log.error(msg, e);
            throw new EJBException(msg, e);
        } catch (BusinessException e) {
            log.error(e.getMessage(), e);
        }
        return null;
	}
}
