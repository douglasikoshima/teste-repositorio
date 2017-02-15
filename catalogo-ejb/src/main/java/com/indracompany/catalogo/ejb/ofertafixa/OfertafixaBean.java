package com.indracompany.catalogo.ejb.ofertafixa;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.OfertaFixaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AreaRegistroTO;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.CidadeTO;
import com.indracompany.catalogo.to.OfertafixaComplementarTO;
import com.indracompany.catalogo.to.OfertafixaDisponibilidadeTO;
import com.indracompany.catalogo.to.OfertafixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;

@Stateless(name = "OfertafixaBean", mappedName = "OfertafixaBean")
@Session(ejbName = "OfertafixaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class OfertafixaBean implements OfertafixaBeanLocal {

    @EJB
    private OfertaFixaDAO ofertaFixaDAO;

    public List<ServicoFixaTO> searchServicoLinha() throws BusinessException {
        try {
            return this.ofertaFixaDAO.searchServicoLinha();
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public List<SolicitacaoComercialFixaTO> carregarSolicitacaoComercialPorServicoList(Integer idServicoFixa) throws BusinessException {
        try {
            return this.ofertaFixaDAO.carregarSolicitacaoComercialPorServicoList(idServicoFixa);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public List<ServicoFixaTO> carregarServicoFixaTOPlanoList(Integer idServicoFixa) throws BusinessException {
        try {
            return this.ofertaFixaDAO.carregarServicoFixaTOPlanoList(idServicoFixa);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }

    public List<OfertafixaTO> search(OfertafixaTO ofertafixaTO) throws BusinessException {
        try {
            return this.ofertaFixaDAO.search(ofertafixaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public OfertafixaTO gravar(OfertafixaTO ofertafixaTO, OfertafixaDisponibilidadeTO ofertafixaDisponibilidadeTO) throws BusinessException {
        try {
            return this.ofertaFixaDAO.gravar(ofertafixaTO, ofertafixaDisponibilidadeTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public OfertafixaTO findByCodigoExceptId(String cdOfertafixa, Integer idOfertafixa) throws BusinessException {
        try {
            return this.ofertaFixaDAO.findByCodigoExceptId(cdOfertafixa, idOfertafixa);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public OfertafixaTO findByNameExceptId(String nmOfertafixa, Integer idOfertafixa) throws BusinessException {
        try {
            return this.ofertaFixaDAO.findByNameExceptId(nmOfertafixa, idOfertafixa);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public void excluir(Integer idOfertafixa) throws BusinessException {
        try {
            this.ofertaFixaDAO.excluir(idOfertafixa);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public OfertafixaTO findByIdWithComplementar(Integer idOfertafixa) throws BusinessException {
        try {
            return this.ofertaFixaDAO.findByIdWithComplementar(idOfertafixa);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public List<TipoServicoTO> carregarTipoServicoTOList() throws BusinessException {
        try {
            return this.ofertaFixaDAO.carregarTipoServicoTOList();
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public List<ServicoFixaTO> findServicoByTipoServico(Integer idTipoServico, OfertafixaTO ofertafixaTO) throws BusinessException {
        try {
            return this.ofertaFixaDAO.findServicoByTipoServico(idTipoServico, ofertafixaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public void excluirOfertaComplementar(Integer idOfertafixaComplementar) throws BusinessException {
        try {
            this.ofertaFixaDAO.excluirOfertaComplementar(idOfertafixaComplementar);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public OfertafixaDisponibilidadeTO carregarDisponibilidadeTO (Integer idOfertafixa) throws BusinessException {
        try {
            return ofertaFixaDAO.carregarDisponibilidadeTO(idOfertafixa);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public List<OfertafixaTO> reload(List<OfertafixaTO> ofertafixaTOList) throws BusinessException {
        try {
            return this.ofertaFixaDAO.reload(ofertafixaTOList);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public SolicitacaoComercialFixaTO findSolicitacaoComercialById(Long idSolicitacaoComercial) throws BusinessException {
        try {
            return this.ofertaFixaDAO.findSolicitacaoComercialById(idSolicitacaoComercial);
        } catch (DAOException e) {
            throw new EJBException(e);
        }    	
    }
    
    public CidadeTO findCidadeById(Integer idCidade) throws BusinessException {
    	try {
            return this.ofertaFixaDAO.findCidadeById(idCidade);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public List<OfertafixaComplementarTO> findOfertafixaComplementarTOWithDependentes(Long idSolicitacaoComercial, Integer pzMaximoVigencia) throws BusinessException {
        try {
            return this.ofertaFixaDAO.findOfertafixaComplementarTOWithDependentes(idSolicitacaoComercial, pzMaximoVigencia);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public CanalVendaTO findCanalVendaTOById(Long idCanalVenda) throws BusinessException {
        try {
            return this.ofertaFixaDAO.findCanalVendaTOById(idCanalVenda);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public AreaRegistroTO findAreaRegistroTOById(Integer idAreaRegistro) throws BusinessException {
        try {
            return this.ofertaFixaDAO.findAreaRegistroTOById(idAreaRegistro);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public List<Long> carregarIdCanalVendaListCompativel(Long idSolicitacaoComercial) throws BusinessException {
    	try {
            return this.ofertaFixaDAO.carregarIdCanalVendaListCompativel(idSolicitacaoComercial);
        } catch (DAOException e) {
            throw new EJBException(e);
        }    	
    }
}
