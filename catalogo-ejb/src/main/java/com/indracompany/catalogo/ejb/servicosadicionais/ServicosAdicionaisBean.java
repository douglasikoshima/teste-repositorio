package com.indracompany.catalogo.ejb.servicosadicionais;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.log4j.Logger;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.ServicosAdicionaisDAO;
import com.indracompany.catalogo.datalayer.OfertaFixaAdicional;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicosAdicionaisTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;

@Stateless(name = "ServicosAdicionaisBean", mappedName = "ServicosAdicionaisBean")
@Session(ejbName = "ServicosAdicionaisBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServicosAdicionaisBean implements ServicosAdicionaisBeanLocal {

    @EJB
    private ServicosAdicionaisDAO servicosAdicionaisDAO;

    private static Logger log = Logger.getLogger(ServicosAdicionaisBean.class);

    public List<ServicosAdicionaisTO> search() throws BusinessException {
        log.debug("Direcionando para DAO");
        try {
            return this.servicosAdicionaisDAO.search();
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public List<TipoServicoTO> loadTipoServico() throws BusinessException {
        log.debug("Direcionando para DAO");
        try {
            return this.servicosAdicionaisDAO.loadTipoServico();
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public List<ServicosAdicionaisTO> findServicosByIdTipoServico(ServicosAdicionaisTO servicosAdicionaisTO) throws BusinessException {
        log.debug("Direcionando para DAO");
        try {
            return this.servicosAdicionaisDAO.findServicosByIdTipoServico(servicosAdicionaisTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public List<SolicitacaoComercialFixaTO> findSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) throws BusinessException {
        log.debug("Direcionando para DAO");
        try {
            List<SolicitacaoComercialFixaTO> loadSolicitacaoComercialTO = this.servicosAdicionaisDAO.findSolicitacaoComercial(servicosAdicionaisTO);
            return loadSolicitacaoComercialTO;
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public List<ServicosAdicionaisTO> recordSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) throws BusinessException {
        log.debug("Direcionando para DAO");
        try {
            return this.servicosAdicionaisDAO.recordSolicitacaoComercial(this.buildEntity(servicosAdicionaisTO));
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public OfertaFixaAdicional buildEntity(ServicosAdicionaisTO to) {
        log.debug("Preparando entity para a persistencia.");
        OfertaFixaAdicional entity = null;
        if (to != null) {
            if (to.getIdServico() != null) {
                entity = new OfertaFixaAdicional(to.getIdServicosAdicionais(), to.getIdSolicitacaoComercial(), to.getDtInicio(), to.getDtFim(), to
                        .getTempoVigencia());
            } else {
                entity = new OfertaFixaAdicional(to.getIdSolicitacaoComercial(), to.getDtInicio(), to.getDtFim(), to.getTempoVigencia());
            }
        }
        return entity;
    }

    public List<ServicosAdicionaisTO> deleteSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) throws BusinessException {
        log.debug("Direcionando para DAO");
        try {
            return this.servicosAdicionaisDAO.deleteSolicitacaoComercial(servicosAdicionaisTO.getId());
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public Boolean existsSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) throws BusinessException {
        log.debug("Direcionando para DAO");
        try {
            return this.servicosAdicionaisDAO.existsSolicitacaoComercial(servicosAdicionaisTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public ServicosAdicionaisTO load(Integer idServicosAdicionais) throws BusinessException {
        log.debug("Direcionando para DAO");
        try {
            return this.servicosAdicionaisDAO.load(idServicosAdicionais);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
}
