package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import javax.ejb.EJBException;

import org.apache.log4j.Logger;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocatorException;
import br.com.indrasistemas.framework.service.BusinessException;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;

import com.indracompany.catalogo.ejb.categoria.CategoriaBeanLocal;
import com.indracompany.catalogo.to.CategoriaTO;

public class CategoriaDelegate {
    private static Logger logger = Logger.getLogger(CategoriaDelegate.class);
    
    private static final String PERTENCE_LEGADO = "Essa categoria pertence ao legado";
    private static final String JA_EXISTE = "Essa Categoria j&aacute; existe!";
    private static final String NOME_JA_EXISTE = "Nome da Categoria j&aacute; existe";
    private static final String NAO_REMOVER_COM_SERVICO = "N&atilde;o &eacute; poss&iacute;vel remover a categoria com servi&ccedil;os associados.";
    private static final String NAO_ALTERAR_COM_SERVICO = "N&atilde;o &eacute; poss&iacute;vel alterar a categoria com servi&ccedil;os associados.";
    
    public List<CategoriaTO> search(CategoriaTO categoriaTO) {
        List<CategoriaTO> toList = null;
        try {
            toList = ((CategoriaBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaBeanLocal.JNDI_NAME)).search(categoriaTO);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [search]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
        return toList;
    }

    public CategoriaTO insertUpdate(CategoriaTO categoriaTO) throws CatalogoPRSException {
        try {
            CategoriaBeanLocal categoriaBeanLocal = ((CategoriaBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaBeanLocal.JNDI_NAME));
            if (categoriaBeanLocal.searchByDescription(categoriaTO.getDsCategoria()) != null) {
            	if (categoriaTO.getIdCategoria() == null) {
                    throw new CatalogoPRSException(JA_EXISTE);
            	} else {
                    throw new CatalogoPRSException(NOME_JA_EXISTE);
            	}
            }
            if (categoriaTO.getIdCategoria() != null) {
                if (categoriaBeanLocal.contarServicoAssociado(categoriaTO.getIdCategoria()).longValue() > 0L) {
                    throw new CatalogoPRSException(NAO_ALTERAR_COM_SERVICO);
                }
                    
            }
			categoriaTO = categoriaBeanLocal.insertUpdate(categoriaTO);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [insertUpdate]", e);
        } catch (BusinessException e) {
            categoriaTO = null;
            logger.error(e);
        }
        return categoriaTO;
    }

    public void changeStatus(Integer id) {
        try {
            ((CategoriaBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaBeanLocal.JNDI_NAME)).changeStatus(id);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [changeStatus]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
    }

    public void remove(Integer id) throws CatalogoPRSException {
        try {
            if (!this.criadoCatalogo(id)) {
                throw new CatalogoPRSException(PERTENCE_LEGADO);
            }
            CategoriaBeanLocal categoriaBeanLocal = ((CategoriaBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaBeanLocal.JNDI_NAME));
            if (categoriaBeanLocal.contarServicoAssociado(id).longValue() > 0L) {
                throw new CatalogoPRSException(NAO_REMOVER_COM_SERVICO);
            }
            categoriaBeanLocal.remove(id);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [remove]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }
    }
    
    private boolean criadoCatalogo(Integer id) {
        try {
            return ((CategoriaBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaBeanLocal.JNDI_NAME)).findById(id).getIndCatalogo();
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [findById]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }        
        return false;
    }
    public List<CategoriaTO> listarGrupoServico(Integer indCatalogo) {
        try {
            return ((CategoriaBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaBeanLocal.JNDI_NAME)).listarGrupoServico(indCatalogo);
        } catch (ServiceLocatorException e) {
            throw new EJBException("Erro ao realizar o lookup de [findById]", e);
        } catch (BusinessException e) {
            logger.error(e);
        }        
        return null;
    }
    public CategoriaTO mergeCategoria(CategoriaTO categoriaTO, Integer indCatalogo) throws BusinessException{
    	
    	try {
			return ((CategoriaBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaBeanLocal.JNDI_NAME)).mergeCatalogo(categoriaTO, indCatalogo);
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			 throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		}
    }
    public void removeCatalogoMovel(Integer id) throws BusinessException{
    	
    	try {
			((CategoriaBeanLocal) ServiceLocator.getInstance().getEJBLocal(CategoriaBeanLocal.JNDI_NAME)).removeCatalogoMovel(id);
		} catch (ServiceLocatorException e) {
			// TODO Auto-generated catch block
			 throw new EJBException("Erro ao realizar o lookup de [findById]", e);
		}
    }
}
