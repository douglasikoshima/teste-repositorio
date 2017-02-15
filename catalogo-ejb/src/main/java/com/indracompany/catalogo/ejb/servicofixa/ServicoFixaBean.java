package com.indracompany.catalogo.ejb.servicofixa;

import java.util.ArrayList;
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

import com.indracompany.catalogo.dao.interfaces.ServicoFixaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.RelacionamentoServicoFixaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.ServicoServicoTO;
import com.indracompany.catalogo.to.TipoRelacionamentoTO;
import com.indracompany.catalogo.to.TipoServicoTO;

@Stateless(name = "ServicoFixaBean", mappedName = "ServicoFixaBean")
@Session(ejbName = "ServicoFixaBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ServicoFixaBean implements ServicoFixaBeanLocal {

    private static Logger log = Logger.getLogger(ServicoFixaBean.class);
    
    @EJB
    private ServicoFixaDAO servicoFixaDAO;
    
    public List<ServicoFixaTO> search(ServicoFixaTO servicoFixaTO) throws BusinessException {
        log.debug(servicoFixaTO);
        
        List<ServicoFixaTO> servicoFixaTOList;
        try {
            servicoFixaTOList = this.servicoFixaDAO.search(servicoFixaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return servicoFixaTOList;
    }

    public String changeStatus(Integer id) throws BusinessException {
        log.debug(String.format("id:%s", id));
        try {
            return this.servicoFixaDAO.changeStatus(id);
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }

    public List<RelacionamentoServicoFixaTO> searchRelationship(RelacionamentoServicoFixaTO relacionamentoServicoFixaTO) throws BusinessException {
        log.debug(relacionamentoServicoFixaTO);
        
        List<RelacionamentoServicoFixaTO> relacionamentoServicoFixaTOList;
        try {
            relacionamentoServicoFixaTOList = this.servicoFixaDAO.searchRelationship(relacionamentoServicoFixaTO);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return relacionamentoServicoFixaTOList;
    }
    
    @SuppressWarnings("unchecked")
    public List<TipoRelacionamentoTO> findTipoRelacionamentoInsertFixa() throws BusinessException{
        List<TipoRelacionamentoTO> tipoRelacionamentoTOList = new ArrayList<TipoRelacionamentoTO>();
        try {
            List<TipoRelacionamentoTO> tipoRelacionamentoTOFullList = this.servicoFixaDAO.findAllTpRelacionamento();
            tipoRelacionamentoTOList.addAll(CollectionUtils.select(tipoRelacionamentoTOFullList, new Predicate() {
                public boolean evaluate(Object o) {
                    TipoRelacionamentoTO to = (TipoRelacionamentoTO) o;
                    return TipoRelacionamentoTO.Sigla.OA.toString().equals(to.getSgTipoRelacionamento()) 
                        || TipoRelacionamentoTO.Sigla.IN.toString().equals(to.getSgTipoRelacionamento())
                        || TipoRelacionamentoTO.Sigla.DC.toString().equals(to.getSgTipoRelacionamento())
                        || TipoRelacionamentoTO.Sigla.AG.toString().equals(to.getSgTipoRelacionamento())
                        || TipoRelacionamentoTO.Sigla.DE.toString().equals(to.getSgTipoRelacionamento())
                        || TipoRelacionamentoTO.Sigla.DA.toString().equals(to.getSgTipoRelacionamento());
                }
            }));
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return tipoRelacionamentoTOList;        
    }
    
    
    
    public List<TipoServicoTO> findAllTpServico() throws BusinessException {
        List<TipoServicoTO> tipoServicoTOList;
        try {
            tipoServicoTOList = this.servicoFixaDAO.findAllTpServico();
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return tipoServicoTOList;   
    }
    
    public String changeStatusRelationship(Integer idRelacionamento) throws BusinessException {
        String newStatus;
        try {
            RelacionamentoServicoFixaTO to = this.servicoFixaDAO.findRelationshipById(idRelacionamento);
            if (to.getInDisponivel()) {
            String sigla = to.getTipoRelacionamentoTO().getSgTipoRelacionamento();
            if (sigla.equals(TipoRelacionamentoTO.Sigla.DA.toString())
                    || sigla.equals(TipoRelacionamentoTO.Sigla.AG.toString())
                  ) {
//                  s� pode inativar se sobrar pelo menos um ativo, tipo highlander
                Long count = this.servicoFixaDAO.countRelationshipByServiceAndType(to.getIdServico1(), to.getTipoRelacionamentoTO().getIdTipoRelacionamento());
                if (count.longValue() > 0L) {
                    newStatus = this.servicoFixaDAO.changeStatusRelationship(idRelacionamento);
                } else {
                    throw new BusinessException("Essa configura&ccedil;&atilde;o n&atilde;o pode ser desativada.");
                }
            } else if (sigla.equals(TipoRelacionamentoTO.Sigla.OA.toString())
                    || sigla.equals(TipoRelacionamentoTO.Sigla.CL.toString())
                    || sigla.equals(TipoRelacionamentoTO.Sigla.DC.toString())) {
                newStatus = this.servicoFixaDAO.changeStatusRelationship(idRelacionamento);
            } else if (sigla.equals(TipoRelacionamentoTO.Sigla.IN.toString())) {
                if (to.getInCriacaoCatalogo()) {
                    newStatus = this.servicoFixaDAO.changeStatusRelationship(idRelacionamento);
                } else {
                    throw new BusinessException("Essa configura&ccedil;&atilde;o n&atilde;o pode ser desativada.");
                }
            } else {
                throw new BusinessException("Essa configura&ccedil;&atilde;o n&atilde;o pode ser desativada.");
            }
            } else {
                newStatus = this.servicoFixaDAO.changeStatusRelationship(idRelacionamento);
            }
        } catch (DAOException e) {
            throw new EJBException(e);
        }
        return newStatus;
    }
    
    public void removeRelationship(Integer idRelacionamento) throws BusinessException {
        try {
            this.servicoFixaDAO.removeRelationship(idRelacionamento);
        } catch (DAOException e) {
            throw new EJBException(e);
        }
    }
    
    public void gravarRelacionamento(List<ServicoServicoTO> servicoServicoTOList) {
        try {
            for (ServicoServicoTO servicoServicoTO : servicoServicoTOList) {
                this.servicoFixaDAO.gravarRelacionamento(servicoServicoTO);
            }
        } catch (DAOException e) {
            throw new EJBException(e);
        }        
    }

	public Integer findIdSistemaByIdServico(Integer idServico) throws BusinessException {
		Integer result = null;
		try {
			result =  servicoFixaDAO.findSistemaByIdServico((int)idServico).getIdSistema();
		} catch(Exception e) {
			throw new EJBException(e);
		}
		return result;
	}
}
