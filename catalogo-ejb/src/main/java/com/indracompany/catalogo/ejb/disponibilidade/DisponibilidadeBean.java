package com.indracompany.catalogo.ejb.disponibilidade;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.AreaConcorrenciaDAO;
import com.indracompany.catalogo.dao.interfaces.AreaRegistroDAO;
import com.indracompany.catalogo.dao.interfaces.CanalVendaDAO;
import com.indracompany.catalogo.dao.interfaces.CidadeDAO;
import com.indracompany.catalogo.dao.interfaces.EpsDAO;
import com.indracompany.catalogo.dao.interfaces.LocalidadeDAO;
import com.indracompany.catalogo.dao.interfaces.UfDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AreaConcorrenciaTO;
import com.indracompany.catalogo.to.AreaRegistroTO;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.CidadeTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.LocalidadeTO;
import com.indracompany.catalogo.to.UfTO;

@Stateless(name = "DisponibilidadeBean", mappedName = "DisponibilidadeBean")
@Session(ejbName = "DisponibilidadeBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class DisponibilidadeBean implements DisponibilidadeBeanLocal {

    @EJB private AreaConcorrenciaDAO areaConcorrenciaDAO;
    @EJB private AreaRegistroDAO areaRegistroDAO;
    @EJB private CanalVendaDAO canalVendaDAO;
    @EJB private LocalidadeDAO localidadeDAO;
    @EJB private CidadeDAO cidadeDAO;
    @EJB private EpsDAO epsDAO;
    @EJB private UfDAO ufDAO;
    
    public List<EpsTO> listEpsTO() throws BusinessException {
        try {
            return epsDAO.listEpsTO();
        } catch (DAOException e) {
            throw new BusinessException(e);
        }
    }
    
    public List<UfTO> obterUfTOList() throws BusinessException {
        try {
            return ufDAO.findAll();
        } catch (DAOException e) {
            throw new BusinessException(e);
        }
    }
    
    public List<CanalVendaTO> searchCanalVendaTO(CanalVendaTO canalVendaTO) throws BusinessException {
        try {
            return canalVendaDAO.searchCanalVendaTO(canalVendaTO);
        } catch (DAOException e) {
            throw new BusinessException(e);
        }        
    }
    
    public List<AreaRegistroTO> searchAreaRegistroTO(AreaRegistroTO areaRegistroTO) throws BusinessException {
        try {
            return areaRegistroDAO.searchAreaRegistroTO(areaRegistroTO);
        } catch (DAOException e) {
            throw new BusinessException(e);
        }        
    }
    
    public List<AreaRegistroTO> findAreaRegistroByUf(Integer idUf) throws BusinessException {
        try {
            return areaRegistroDAO.findAreaRegistroByUf(idUf);
        } catch (DAOException e) {
            throw new BusinessException(e);
        }        
    }
    
    public List<CidadeTO> findCidadeByAreaRegistro(Integer idAreaRegistro) throws BusinessException {
        try {
            return cidadeDAO.findCidadeByAreaRegistro(idAreaRegistro);
        } catch (DAOException e) {
            throw new BusinessException(e);
        }        
    }
    
    public List<LocalidadeTO> findLocalidadeByIdCidade(Integer idCidade) throws BusinessException {
        try {
            return localidadeDAO.findLocalidadeByIdCidade(idCidade);
        } catch (DAOException e) {
            throw new BusinessException(e);
        }        
    }
    
    public List<AreaConcorrenciaTO> obterAreaConcorrenciaTOList() throws BusinessException {
        try {
            return areaConcorrenciaDAO.findAll();
        } catch (DAOException e) {
            throw new BusinessException(e);
        }
    }
    
}