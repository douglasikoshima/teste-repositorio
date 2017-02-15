package br.com.vivo.catalogoPRS.delegate;

import java.util.List;

import br.com.indrasistemas.framework.foundation.servicelocator.ServiceLocator;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;

import com.indracompany.catalogo.ejb.disponibilidade.DisponibilidadeBeanLocal;
import com.indracompany.catalogo.to.AreaConcorrenciaTO;
import com.indracompany.catalogo.to.AreaRegistroTO;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.CidadeTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.LocalidadeTO;
import com.indracompany.catalogo.to.UfTO;

public class DisponibilidadeDelegate {

    public List<EpsTO> listEpsTO() throws CatalogoPRSException {
        try {
            return ((DisponibilidadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(DisponibilidadeBeanLocal.JNDI_NAME)).listEpsTO();
        } catch (Exception e) {
        	throw new CatalogoPRSException(e.getMessage(), e);
        }
    }
    
    public List<UfTO> obterUfTOList() throws CatalogoPRSException {
        try {
            return ((DisponibilidadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(DisponibilidadeBeanLocal.JNDI_NAME)).obterUfTOList();
        } catch (Exception e) {
            throw new CatalogoPRSException(e.getMessage(), e);
        }
    }
    
    public List<CanalVendaTO> searchCanalVendaTO(CanalVendaTO canalVendaTO) throws CatalogoPRSException {
        try {
            return ((DisponibilidadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(DisponibilidadeBeanLocal.JNDI_NAME)).searchCanalVendaTO(canalVendaTO);
        } catch (Exception e) {
        	throw new CatalogoPRSException(e.getMessage(), e);
        }
    }
    
    public List<AreaRegistroTO> searchAreaRegistroTO(AreaRegistroTO areaRegistroTO) throws CatalogoPRSException {
        try {
            return ((DisponibilidadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(DisponibilidadeBeanLocal.JNDI_NAME)).searchAreaRegistroTO(areaRegistroTO);
        } catch (Exception e) {
            throw new CatalogoPRSException(e.getMessage(), e);
        }
    }
    
    public List<AreaRegistroTO> findAreaRegistroByUf(Integer idUf) throws CatalogoPRSException {
        try {
            return ((DisponibilidadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(DisponibilidadeBeanLocal.JNDI_NAME)).findAreaRegistroByUf(idUf);
        } catch (Exception e) {
            throw new CatalogoPRSException(e.getMessage(), e);
        }
    }

    public List<CidadeTO> findCidadeByAreaRegistro(Integer idAreaRegistro) throws CatalogoPRSException {
        try {
            return ((DisponibilidadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(DisponibilidadeBeanLocal.JNDI_NAME)).findCidadeByAreaRegistro(idAreaRegistro);
        } catch (Exception e) {
            throw new CatalogoPRSException(e.getMessage(), e);
        }
    }

    public List<LocalidadeTO> findLocalidadeByIdCidade(Integer idCidade) throws CatalogoPRSException {
        try {
            return ((DisponibilidadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(DisponibilidadeBeanLocal.JNDI_NAME)).findLocalidadeByIdCidade(idCidade);
        } catch (Exception e) {
            throw new CatalogoPRSException(e.getMessage(), e);
        }
    }
    
    public List<AreaConcorrenciaTO> obterAreaConcorrenciaTOList() throws CatalogoPRSException {
        try {
            return ((DisponibilidadeBeanLocal) ServiceLocator.getInstance().getEJBLocal(DisponibilidadeBeanLocal.JNDI_NAME)).obterAreaConcorrenciaTOList();
        } catch (Exception e) {
            throw new CatalogoPRSException(e.getMessage(), e);
        }
    }
    
}