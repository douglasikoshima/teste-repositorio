package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.relatoriofixa.RelatorioFixaBeanLocal;
import com.indracompany.catalogo.to.GrupoComercialRelatorioFixaTO;
import com.indracompany.catalogo.to.RelacionamentoRelatorioFixaTO;
import com.indracompany.catalogo.to.SCxENCxPFxGCxPMxACRelatorioFixaTO;
import com.indracompany.catalogo.to.SCxGCxPMxACRelatorioFixaTO;
import com.indracompany.catalogo.to.ServicoRelatorioFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoRelacionamentoTO;
import com.indracompany.catalogo.to.TipoSolicitacaoComercialTO;

public class RelatorioFixaDelegate {

    private static Logger log = Logger.getLogger(RelatorioFixaDelegate.class);

    public List<TipoSolicitacaoComercialTO> findAllTpSolicitacaoComercial() {
        try {
            return ((RelatorioFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(RelatorioFixaBeanLocal.JNDI_NAME)).findAllTpSolicitacaoComercial();
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [findAllTpSolicitacaoComercial]", e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<ServicoRelatorioFixaTO> pesquisarServico(ServicoRelatorioFixaTO servicoRelatorioFixaTO) {
        try {
            return ((RelatorioFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(RelatorioFixaBeanLocal.JNDI_NAME)).pesquisarServico(servicoRelatorioFixaTO);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [pesquisarServico]", e);
        } catch (BusinessException e) {
            log.error(e);
        }
//        } catch (EJBException e) {
//            if (!e.getMessage().contains("timed out")) {
//                throw e;
//            }
//        }
        return null;
    }

    public List<RelacionamentoRelatorioFixaTO> pesquisarRelacionamentoServico(RelacionamentoRelatorioFixaTO relacionamentoRelatorioFixaTO) {
        try {
            return ((RelatorioFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(RelatorioFixaBeanLocal.JNDI_NAME)).pesquisarRelacionamentoServico(relacionamentoRelatorioFixaTO);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [pesquisarRelacionamentoServico]", e);
        } catch (BusinessException e) {
            log.error(e);
        }
//        } catch (EJBException e) {
//            if (!e.getMessage().contains("timed out")) {
//                throw e;
//            }
//        }
        return null;
    }

    public List<SolicitacaoComercialFixaTO> pesquisarSolicitacaoComercial(SolicitacaoComercialFixaTO solicitacaoComercialRelatorioFixaTO) {
        try {
            return ((RelatorioFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(RelatorioFixaBeanLocal.JNDI_NAME))
                        .pesquisarSolicitacaoComercial(solicitacaoComercialRelatorioFixaTO);
        } catch (ServiceLocatorException e) {
            log.error(e.getMessage(), e);
            throw new EJBException("Erro ao realizar o lookup de [pesquisarSolicitacaoComercial]", e);
        } catch (BusinessException e) {
            log.error(e);
        }
//        } catch (EJBException e) {
//            if (!e.getMessage().contains("timed out")) {
//                throw e;
//            }
//        }
        return null;
    }

    public List<TipoRelacionamentoTO> findAllTpRelacionamento() {
        try {
            return ((RelatorioFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(RelatorioFixaBeanLocal.JNDI_NAME)).findAllTpRelacionamento();
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [findAllTpRelacionamento]", e);
        } catch (BusinessException e) {
            log.error(e);
        }
        return null;
    }

    public List<GrupoComercialRelatorioFixaTO> pesquisarGrupoComercial(GrupoComercialRelatorioFixaTO grupoComercialRelatorioFixaTO) {
        try {
            return ((RelatorioFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(RelatorioFixaBeanLocal.JNDI_NAME))
                        .pesquisarGrupoComercial(grupoComercialRelatorioFixaTO);
        } catch (ServiceLocatorException e) {
            log.error(e.getMessage(), e);
            throw new EJBException("Erro ao realizar o lookup de [pesquisarGrupoComercial]", e);
        } catch (BusinessException e) {
            log.error(e);
        }
//        } catch (EJBException e) {
//            if (!e.getMessage().contains("timed out")) {
//                throw e;
//            }
//        }
        return null;
    }

    public List<SCxGCxPMxACRelatorioFixaTO> pesquisarSCxGCxPMxAC(SCxGCxPMxACRelatorioFixaTO sCxGCxPMxACRelatorioFixaTO) {
        try {
            return ((RelatorioFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(RelatorioFixaBeanLocal.JNDI_NAME))
                        .pesquisarSCxGCxPMxAC(sCxGCxPMxACRelatorioFixaTO);
        } catch (ServiceLocatorException e) {
            log.error(e.getMessage(), e);
            throw new EJBException("Erro ao realizar o lookup de [pesquisarSCxGCxPMxAC]", e);
        } catch (BusinessException e) {
            log.error(e);
        }
//        } catch (EJBException e) {
//            if (!e.getMessage().contains("timed out")) {
//                throw e;
//            }
//        }
        return null;
    }
    
    public List<SCxENCxPFxGCxPMxACRelatorioFixaTO> pesquisarSCxENCxPFxGCxPMxAC(SCxENCxPFxGCxPMxACRelatorioFixaTO sCxENCxPFxGCxPMxACRelatorioFixaTO) {
        try {
            return ((RelatorioFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(RelatorioFixaBeanLocal.JNDI_NAME))
            			.pesquisarSCxENCxPFxGCxPMxAC(sCxENCxPFxGCxPMxACRelatorioFixaTO);
        } catch (ServiceLocatorException e) {
            log.error(e.getMessage(), e);
            throw new EJBException("Erro ao realizar o lookup de [pesquisarSCxENCxPFxGCxPMxAC]", e);
        } catch (BusinessException e) {
            log.error(e);
        }
//        } catch (EJBException e) {
//            if (!e.getMessage().contains("timed out")) {
//                throw e;
//            }
//        }
        return null;
    }

    
}
