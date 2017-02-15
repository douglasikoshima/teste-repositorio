package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.ejb.importacaoservicofixa.ImportacaoServicoFixaBeanLocal;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.OfertaVLArqItemTO;
import com.indracompany.catalogo.to.OfertaVLFArqItemTO;
import com.indracompany.catalogo.to.SCEncargoGruComArqItemTO;
import com.indracompany.catalogo.to.ServicoArqItemTO;
import com.indracompany.catalogo.to.ServicoServicoArqItemTO;
import com.indracompany.catalogo.to.SolComGrupoComArqItemTO;
import com.indracompany.catalogo.to.StatusArquivoImportacaoTO;

public class ImportacaoServicoFixaDelegate {
	
	private static Logger logger = Logger.getLogger(ImportacaoServicoFixaDelegate.class);
	
	public Boolean importarServicoServicoArq(String usuario, String nomeArquivo) throws BusinessException {
		try {
			return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).importarServicoServicoArq(usuario, nomeArquivo);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [importarServicoServicoArq]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}		
		return false;
	}
	
	public Boolean importarSolComGrupoComArq(String usuario, String nomeArquivo) throws BusinessException {
		try {
			return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).importarSolComGrupoComArq(usuario, nomeArquivo);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [importarSolComGrupoComArq]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}		
		return null;
	}

	public Boolean importarSCEncargoGruComArq(String usuario, String nomeArquivo) throws BusinessException {
		try {
			return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).importarSCEncargoGruComArq(usuario, nomeArquivo);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [importarSCEncargoGruComArq]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}		
		return null;
	}

	public Boolean importarSCEncargoGCPMACArq(String usuario, String nomeArquivo) throws BusinessException {
		try {
			return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).importarSCEncargoGCPMACArq(usuario, nomeArquivo);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [importarSCEncargoGCPMACArq]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}		
		return null;
	}
	
    public Boolean importarServicoArq(String usuario, String nomeArquivo) throws BusinessException {
	    try {
	        return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).importarServicoArq(usuario, nomeArquivo);
	    } catch (ServiceLocatorException e) {
	        throw new EJBException("Erro ao realizar o lookup de [importarSCEncargoGCPMACArq]", e);
	    } catch (BusinessException e) {
	        logger.error(e);
	    }		
	    return null;
	}
    
    // OFERTA VENDA LINHA
    public Boolean importarServicoOfertaVendaLinha(String usuario, String nomeArquivo) throws BusinessException {
	    try {
	        return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).importarOfertaVendaLinha(usuario, nomeArquivo);
	    } catch (ServiceLocatorException e) {
	        throw new EJBException("Erro ao realizar o lookup de [importarServicoOfertaVendaLinhaOuComplemen]", e);
	    } catch (BusinessException e) {
	        logger.error(e);
	    }		
	    return null;
	}  
    
    // OFERTA VENDA LINHA COMPLEMENTAR
    public Boolean importarServicoOfertaComplementar(String usuario, String nomeArquivo) throws BusinessException {
	    try {
	        return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).importarOfertaComplementar(usuario, nomeArquivo);
	    } catch (ServiceLocatorException e) {
	        throw new EJBException("Erro ao realizar o lookup de [importarServicoOfertaVendaLinhaOuComplemen]", e);
	    } catch (BusinessException e) {
	        logger.error(e);
	    }		
	    return null;
	}    
        
	public List<ImportacaoServicoFixaTO> pesquisarServicoServicoArq(ImportacaoServicoFixaTO to) throws BusinessException {
		try {
			return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).pesquisarServicoServicoArq(to);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [pesquisarServicoServicoArq]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return null;
	}

	public List<ImportacaoServicoFixaTO> pesquisarSolComGrupoComArq(ImportacaoServicoFixaTO to) throws BusinessException {
		try {
			return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).pesquisarSolComGrupoComArq(to);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [pesquisarSolComGrupoComArq]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return null;
	}

	public List<ImportacaoServicoFixaTO> pesquisarSCEncargoGruComArq(ImportacaoServicoFixaTO to) throws BusinessException {
		try {
			return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).pesquisarSCEncargoGruComArq(to);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [pesquisarSCEncargoGruComArq]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return null;
	}

	public List<ImportacaoServicoFixaTO> pesquisarSCEncargoGCPMACArq(ImportacaoServicoFixaTO to) throws BusinessException {
		try {
			return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).pesquisarSCEncargoGCPMACArq(to);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [pesquisarSCEncargoGCPMACArq]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return null;
	}

	public List<ServicoServicoArqItemTO> pesquisarCriticaServicoServicoArq(Integer idArquivo) throws BusinessException {
		try {
			return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).pesquisarCriticaServicoServicoArq(idArquivo);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [pesquisarCriticaServicoServicoArq]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return null;
	}
	
	public List<SolComGrupoComArqItemTO> pesquisarCriticaSolComGrupoComArq(Integer idArquivo) throws BusinessException {
		try {
			return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).pesquisarCriticaSolComGrupoComArq(idArquivo);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [pesquisarCriticaSolComGrupoComArq]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return null;
	}
	
	public List<SCEncargoGruComArqItemTO> pesquisarCriticaSCEncargoGruComArq(Integer idArquivo) throws BusinessException {
		try {
			return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).pesquisarCriticaSCEncargoGruComArq(idArquivo);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [pesquisarCriticaSCEncargoGruComArq]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return null;
	}
	
	public List<SCEncargoGruComArqItemTO> pesquisarCriticaSCEncargoGCPMACArq(Integer idArquivo) throws BusinessException {
		try {
			return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).pesquisarCriticaSCEncargoGCPMACArq(idArquivo);
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [pesquisarCriticaSCEncargoGCPMACArq]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return null;
	}
	
	public List<StatusArquivoImportacaoTO> carregarComboStatusArq() {
		try {
			return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).carregarComboStatusArq();
		} catch (ServiceLocatorException e) {
			throw new EJBException("Erro ao realizar o lookup de [carregarComboStatusArq]", e);
		} catch (BusinessException e) {
			logger.error(e);
		}
		return null;
	}

    public List<ImportacaoServicoFixaTO> pesquisarServicoArq(ImportacaoServicoFixaTO to) {
        try {
            return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).pesquisarServicoArq(to);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [pesquisarServicoServicoArq]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
        return null;
    }

    public List<ServicoArqItemTO> pesquisarCriticaServicoArq(Integer idArquivo) throws BusinessException {
        try {
            return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).pesquisarCriticaServicoArq(idArquivo);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [pesquisarCriticaServicoServicoArq]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
        return null;
    }
    
    public List<ImportacaoServicoFixaTO> pesquisarOfertaVLArq(ImportacaoServicoFixaTO to, String tpImportacao) {
        try {
            return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).pesquisarOfertaVLArq(to, tpImportacao);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [pesquisarOfertaVLArq]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
        return null;
    }
    
    public List<OfertaVLArqItemTO> pesquisarCriticaOfertaVLArq(Integer idArquivo) throws BusinessException {
        try {
            return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).pesquisarCriticaOfertaVLArq(idArquivo);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [pesquisarCriticaOfertaVLArq]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
        return null;
    }
    
    public List<OfertaVLFArqItemTO> pesquisarCriticaOfertaVLFArq(Integer idArquivo) throws BusinessException {
        try {
            return ((ImportacaoServicoFixaBeanLocal) ServiceLocator.getInstance().getEJBLocal(ImportacaoServicoFixaBeanLocal.JNDI_NAME)).pesquisarCriticaOfertaVLFArq(idArquivo);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [pesquisarCriticaOfertaVLArq]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
        return null;
    }     
}
