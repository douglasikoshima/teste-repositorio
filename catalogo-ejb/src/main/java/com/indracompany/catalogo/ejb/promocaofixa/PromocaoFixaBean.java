package com.indracompany.catalogo.ejb.promocaofixa;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.Predicate;
import org.apache.log4j.Logger;

import weblogic.ejbgen.Session;
import weblogic.ejbgen.Constants.Bool;
import weblogic.ejbgen.Session.SessionType;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.dao.interfaces.PromocaoFixaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalVendaTO;
import com.indracompany.catalogo.to.ComposicaoPromocaoTO;
import com.indracompany.catalogo.to.DisponibilidadePromocaoFixaTO;
import com.indracompany.catalogo.to.EpsTO;
import com.indracompany.catalogo.to.PromocaoTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;
import com.indracompany.catalogo.to.ValorPoliticaPromocaoTO;

@Stateless(name = "PromocaoFixaBean", mappedName = "PromocaoFixaBean")
@Session(ejbName = "PromocaoFixaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class PromocaoFixaBean implements PromocaoFixaBeanLocal {

	private static Logger log = Logger.getLogger(PromocaoFixaBean.class);
	
    @EJB
    private PromocaoFixaDAO promocaoFixaDAO;

    
    public List<PromocaoTO> search(PromocaoTO promocaoTO) throws BusinessException {
        try {
            return this.promocaoFixaDAO.search(promocaoTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public PromocaoTO reccord(PromocaoTO promocaoTO) throws BusinessException {
        try {
            return this.promocaoFixaDAO.merge(promocaoTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public List<ComposicaoPromocaoTO> findComposicaoByIdPromocao(Integer idPromocao) throws BusinessException {
        try {
            return this.promocaoFixaDAO.findComposicaoByIdPromocao(idPromocao);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public List<TipoServicoTO> findTipoServicoTO() throws BusinessException {
        try {
            List<TipoServicoTO> findTipoServicoTO = this.promocaoFixaDAO.findTipoServicoTO();
            CollectionUtils.filter(findTipoServicoTO, new Predicate() {
                public boolean evaluate(Object o) {
                    TipoServicoTO tipoServicoTO = (TipoServicoTO) o;
                    return tipoServicoTO.getCdTipoServico().equals("2") || tipoServicoTO.getCdTipoServico().equals("16"); 
                }
            });
            return findTipoServicoTO;
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public List<ServicoFixaTO> findServicoByTipoServico(Integer idTipoServico) throws BusinessException {
        try {
            List<ServicoFixaTO> findServicoByTipoServico = this.promocaoFixaDAO.findServicoByTipoServico(idTipoServico);
            if (findServicoByTipoServico.size() > 0 && findServicoByTipoServico.iterator().next().getCdTipoServico().equals("2")) {
                CollectionUtils.filter(findServicoByTipoServico, new Predicate() {
                    public boolean evaluate(Object o) {
                        ServicoFixaTO s = (ServicoFixaTO) o;
                        return s.getCdCategoria().equals("94");
                    }
                });
            }
            return findServicoByTipoServico;
        } catch (DAOException e) {
            throw new EJBException(e);
        }          
    }
    
    public List<ServicoFixaTO> findServicoDescontoByIdServico(Integer idServico) throws BusinessException {
        try {
            return this.promocaoFixaDAO.findServicoDescontoByIdServico(idServico);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public List<SolicitacaoComercialFixaTO> findSolicitacaoComercialByIdServico(Integer idServico) throws BusinessException {
        try {
            return this.promocaoFixaDAO.findSolicitacaoComercialByIdServico(idServico);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public boolean removeComposicaoById(List<ComposicaoPromocaoTO> composicaoPromocaoTOList) throws BusinessException {
    	boolean dataRemoved = false;
        try {
            for (ComposicaoPromocaoTO promocaoTO : composicaoPromocaoTOList) {
            	log.debug("promocaoFixaDAO.removeComposicaoById(idComposicao: "+promocaoTO.getIdComposicao()+" idServico: "+promocaoTO.getIdServico()+" idSolicitacao: "+promocaoTO.getIdSolicitacao()+" idPromocao: "+promocaoTO.getIdPromocao());
                this.promocaoFixaDAO.removeComposicaoById(promocaoTO.getIdComposicao());
            }
            dataRemoved = true;
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return dataRemoved;
    }
    
    public boolean persist(List<ComposicaoPromocaoTO> composicaoPromocaoTOList) throws BusinessException {
        boolean dataPersisted = false;
        try {
            for (ComposicaoPromocaoTO composicaoPromocaoTO : composicaoPromocaoTOList) {
                if (composicaoPromocaoTO.getIdComposicao() == null) {
                    if (this.promocaoFixaDAO.persist(composicaoPromocaoTO)) {
                        dataPersisted = true;
                    }
                }
            }
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return dataPersisted;
    }
    
    public List<DisponibilidadePromocaoFixaTO> obterCanalVendaPorPromocao(Integer idPromocao) throws BusinessException {
        try {
            return this.promocaoFixaDAO.obterCanalVendaPorPromocao(idPromocao);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public void removeDisponibilidade(List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListPersist, List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListRemove) throws BusinessException {
        try {
        	this.promocaoFixaDAO.removeDisponibilidade(valorPoliticaPromocaoTOListPersist, valorPoliticaPromocaoTOListRemove);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public void persistDisponibilidade(List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListPersist, List<ValorPoliticaPromocaoTO> valorPoliticaPromocaoTOListRemove) throws BusinessException {
        try {
            this.promocaoFixaDAO.persistDisponibilidade(valorPoliticaPromocaoTOListPersist, valorPoliticaPromocaoTOListRemove);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public void remove(Integer idPromocao) throws BusinessException {
        try {
            this.promocaoFixaDAO.remove(idPromocao);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public PromocaoTO findById(Integer idPromocao) throws BusinessException {
        try {
            return this.promocaoFixaDAO.findById(idPromocao);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public PromocaoTO findByCodigoExceptId(String codigo, Integer idPromocao) throws BusinessException {
        try {
            return this.promocaoFixaDAO.findByCodigoExceptId(codigo, idPromocao);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public PromocaoTO findByNameExceptId(String nome, Integer idPromocao) throws BusinessException {
        try {
            return this.promocaoFixaDAO.findByNameExceptId(nome, idPromocao);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public List<PromocaoTO> reload(List<PromocaoTO> promocaoTOList) throws BusinessException {
        try {
            return this.promocaoFixaDAO.reload(promocaoTOList);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public List<EpsTO> listEpsTO() throws BusinessException {
        try {
            return this.promocaoFixaDAO.listEpsTO();
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    } 
    
    public List<CanalVendaTO> searchCanalVendaTO(CanalVendaTO canalVendaTO) throws BusinessException {
        try {
            return this.promocaoFixaDAO.searchCanalVendaTO(canalVendaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public CanalVendaTO findCanalVendaTOById(Long idCanalVenda) throws BusinessException {
        try {
            return this.promocaoFixaDAO.findCanalVendaTOById(idCanalVenda);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public List<DisponibilidadePromocaoFixaTO> findCanalVendaPorPromocaoById(Integer idCanalVenda) throws BusinessException {
        try {
            return this.promocaoFixaDAO.findCanalVendaPorPromocaoById(idCanalVenda);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }
    
    public List<DisponibilidadePromocaoFixaTO> findCanalVendaPorPromocaoById(Integer idCanalVenda, Integer idPromocao) throws BusinessException {
        try {
            return this.promocaoFixaDAO.findCanalVendaPorPromocaoById(idCanalVenda, idPromocao);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }

}
