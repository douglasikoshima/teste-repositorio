package com.indracompany.catalogo.ejb.importacaoservicofixa;

import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
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

import com.indracompany.catalogo.dao.interfaces.MatrizOfertaStatusImportacaoDAO;
import com.indracompany.catalogo.dao.interfaces.OfertaVLArqDAO;
import com.indracompany.catalogo.dao.interfaces.SCEncargoGCPMACArqDAO;
import com.indracompany.catalogo.dao.interfaces.SCEncargoGruComArqDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoArqDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoServicoArqDAO;
import com.indracompany.catalogo.dao.interfaces.SolComGrupoComArqDAO;
import com.indracompany.catalogo.datalayer.MatrizOfertaStatusImportacao;
import com.indracompany.catalogo.datalayer.OfertaVLArq;
import com.indracompany.catalogo.datalayer.SCEncargoGCPMACArq;
import com.indracompany.catalogo.datalayer.SCEncargoGruComArq;
import com.indracompany.catalogo.datalayer.ServicoArq;
import com.indracompany.catalogo.datalayer.ServicoServicoArq;
import com.indracompany.catalogo.datalayer.SolComGrupoComArq;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.OfertaVLArqItemTO;
import com.indracompany.catalogo.to.OfertaVLFArqItemTO;
import com.indracompany.catalogo.to.SCEncargoGruComArqItemTO;
import com.indracompany.catalogo.to.ServicoArqItemTO;
import com.indracompany.catalogo.to.ServicoServicoArqItemTO;
import com.indracompany.catalogo.to.SolComGrupoComArqItemTO;
import com.indracompany.catalogo.to.StatusArquivoImportacaoTO;



/**
 * @author equipe Catalogo
 *
 */
@Stateless(name = "ImportacaoServicoFixaBean.java", mappedName = "ImportacaoServicoFixaBean.java")
@Session(ejbName = "ImportacaoServicoFixaBean.java", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.SUPPORTS)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.SUPPORTS)
public class ImportacaoServicoFixaBean implements ImportacaoServicoFixaBeanLocal {
	 
	private static Logger logger = Logger.getLogger(ImportacaoServicoFixaBean.class);
	
    public final static String NOME_ARQUIVO_OFERTA_VENDA_LINHA = "OFERTA_VENDA_LINHA";
    public final static String NOME_ARQUIVO_OFERTA_COMPLEMENTAR = "OFERTA_COMPLEMENTAR";
	
	@EJB
	private ServicoServicoArqDAO servicoServicoArqDAO;
	
	@EJB
	private SolComGrupoComArqDAO solComGrupoComArqDAO;

	@EJB
	private SCEncargoGruComArqDAO scEncargoGruComArqDAO;

	@EJB
	private SCEncargoGCPMACArqDAO scEncargoGCPMACArqDAO;
    
    @EJB
    private ServicoArqDAO servicoArqDAO;
    
    @EJB
    private OfertaVLArqDAO ofertaVLArqDAO;

	@EJB
	private MatrizOfertaStatusImportacaoDAO statusImportacaoDAO;

	
	public Boolean importarServicoServicoArq(String usuario, String nomeArquivo) throws BusinessException {
		logger.debug("usuario: " + usuario + ", nomeArquivo: " + nomeArquivo);
		
		Boolean retorno = Boolean.TRUE;
		
		try {		
			ServicoServicoArq item = new ServicoServicoArq();  
			item.setDtImportacao(new Date());
			item.setNmUsuarioImportacao(usuario);
			item.setNmArquivo(nomeArquivo);
			item.setMatrizOfertaStatusImportacao(new MatrizOfertaStatusImportacao(1)); //Pendente de Processamento
			
			servicoServicoArqDAO.createUpdateServicoServicoArq(item);
			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}		
		return retorno;
	}
	
	public Boolean importarSolComGrupoComArq(String usuario, String nomeArquivo) throws BusinessException {
		logger.debug("usuario: " + usuario + ", nomeArquivo: " + nomeArquivo);
		
		Boolean retorno = Boolean.TRUE;
		
		try {
			SolComGrupoComArq item = new SolComGrupoComArq();  
			item.setDtImportacao(new Date());
			item.setNmUsuarioImportacao(usuario);
			item.setNmArquivo(nomeArquivo);
			item.setMatrizOfertaStatusImportacao(new MatrizOfertaStatusImportacao(1)); //Pendente de Processamento
		
			solComGrupoComArqDAO.createUpdateSolComGrupoComArq(item);
			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}		
		return retorno;
	}
	
	public Boolean importarSCEncargoGruComArq(String usuario, String nomeArquivo) throws BusinessException {
		logger.debug("usuario: " + usuario + ", nomeArquivo: " + nomeArquivo);
		
		Boolean retorno = Boolean.TRUE;
		
		try {
			SCEncargoGruComArq item = new SCEncargoGruComArq();  
			item.setDtImportacao(new Date());
			item.setNmUsuarioImportacao(usuario);
			item.setNmArquivo(nomeArquivo);
			item.setMatrizOfertaStatusImportacao(new MatrizOfertaStatusImportacao(1)); //Pendente de Processamento
		
			scEncargoGruComArqDAO.createUpdateSCEncargoGruComArq(item);
			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}		
		return retorno;
	}
	
	public Boolean importarSCEncargoGCPMACArq(String usuario, String nomeArquivo) throws BusinessException {
		logger.debug("usuario: " + usuario + ", nomeArquivo: " + nomeArquivo);
		
		Boolean retorno = Boolean.TRUE;
		
		try {
			SCEncargoGCPMACArq item = new SCEncargoGCPMACArq();  
			item.setDtImportacao(new Date());
			item.setNmUsuarioImportacao(usuario);
			item.setNmArquivo(nomeArquivo);
			item.setMatrizOfertaStatusImportacao(new MatrizOfertaStatusImportacao(1)); //Pendente de Processamento
		
			scEncargoGCPMACArqDAO.createUpdateSCEncargoGCPMACArq(item);
			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}		
		return retorno;
	}

    public Boolean importarServicoArq(String usuario, String nomeArquivo) throws BusinessException {
	    logger.debug("usuario: " + usuario + ", nomeArquivo: " + nomeArquivo);
	    
	    boolean retorno = true;
	    
	    try {
            ServicoArq servicoArq = new ServicoArq();
            servicoArq.setDtImportacao(new Date());
            servicoArq.setNmUsuarioImportacao(usuario);
            servicoArq.setNmArquivo(nomeArquivo);
            servicoArq.setMatrizOfertaStatusImportacao(new MatrizOfertaStatusImportacao(1)); //Pendente de Processamento
	        servicoArqDAO.merge(servicoArq);
	    } catch (DAOException e) {
	        throw new BusinessException(e);
	    }		
	    return retorno;
	}

    public Boolean importarOfertaVendaLinha(String usuario, String nomeArquivo) throws BusinessException {
    	logger.debug("usuario: " + usuario + ", nomeArquivo: " + nomeArquivo);
    	
    	boolean retorno = true;
    	
    	try {
    		OfertaVLArq OfertaVLArq = new OfertaVLArq(); 
    		OfertaVLArq.setDtImportacao(new Date());
    		OfertaVLArq.setNmUsuarioImportacao(usuario);
    		OfertaVLArq.setNmArquivo(nomeArquivo);
    		OfertaVLArq.setTpImportacao("OFERTAVENDALINHA");
			
    		OfertaVLArq.setMatrizOfertaStatusImportacao(new MatrizOfertaStatusImportacao(1)); //Pendente de Processamento				
			ofertaVLArqDAO.merge(OfertaVLArq);
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
    	
    	return retorno;
    }
    
    public Boolean importarOfertaComplementar(String usuario, String nomeArquivo) throws BusinessException {
    	logger.debug("usuario: " + usuario + ", nomeArquivo: " + nomeArquivo);
    	
    	boolean retorno = true;
    	
    	try {
    		OfertaVLArq OfertaVLArq = new OfertaVLArq(); 
    		OfertaVLArq.setDtImportacao(new Date());
    		OfertaVLArq.setNmUsuarioImportacao(usuario);
    		OfertaVLArq.setNmArquivo(nomeArquivo);			
    		OfertaVLArq.setTpImportacao("OFERTACOMPLEMENTAR");
			
    		OfertaVLArq.setMatrizOfertaStatusImportacao(new MatrizOfertaStatusImportacao(1)); //Pendente de Processamento				
			ofertaVLArqDAO.merge(OfertaVLArq);					
		} catch (DAOException e) {
			throw new BusinessException(e);
		}
    	
    	return retorno;
    }    
    
	public List<ImportacaoServicoFixaTO> pesquisarServicoServicoArq(ImportacaoServicoFixaTO to) throws BusinessException {
		logger.debug("ImportacaoServicoFixaTO: " + to);
		
		List<ImportacaoServicoFixaTO> list = null;		
		try {			
			list = servicoServicoArqDAO.search(to);			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}		
		return list;
	}

	public List<ImportacaoServicoFixaTO> pesquisarSolComGrupoComArq(ImportacaoServicoFixaTO to) throws BusinessException {
		logger.debug("ImportacaoServicoFixaTO: " + to);
		
		List<ImportacaoServicoFixaTO> list = null;		
		try {			
			list = solComGrupoComArqDAO.search(to);			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}		
		return list;
	}

	public List<ImportacaoServicoFixaTO> pesquisarSCEncargoGruComArq(ImportacaoServicoFixaTO to) throws BusinessException {
		logger.debug("ImportacaoServicoFixaTO: " + to);
		
		List<ImportacaoServicoFixaTO> list = null;		
		try {			
			list = scEncargoGruComArqDAO.search(to);			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}		
		return list;
	}

	public List<ImportacaoServicoFixaTO> pesquisarSCEncargoGCPMACArq(ImportacaoServicoFixaTO to) throws BusinessException {
		logger.debug("ImportacaoServicoFixaTO: " + to);
		
		List<ImportacaoServicoFixaTO> list = null;		
		try {			
			list = scEncargoGCPMACArqDAO.search(to);			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}		
		return list;
	}

	public List<ServicoServicoArqItemTO> pesquisarCriticaServicoServicoArq(Integer idArquivo) throws BusinessException {
		logger.debug("idArquivo: " + idArquivo);
		
		List<ServicoServicoArqItemTO> list = null;		
		try {			
			list = servicoServicoArqDAO.searchCritica(idArquivo);			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}		
		return list;
	}
	
	public List<SolComGrupoComArqItemTO> pesquisarCriticaSolComGrupoComArq(Integer idArquivo) throws BusinessException {
		logger.debug("idArquivo: " + idArquivo);
		
		List<SolComGrupoComArqItemTO> list = null;		
		try {			
			list = solComGrupoComArqDAO.searchCritica(idArquivo);			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}		
		return list;
	}
	
	public List<SCEncargoGruComArqItemTO> pesquisarCriticaSCEncargoGruComArq(Integer idArquivo) throws BusinessException {
		logger.debug("idArquivo: " + idArquivo);
		
		List<SCEncargoGruComArqItemTO> list = null;		
		try {			
			list = scEncargoGruComArqDAO.searchCritica(idArquivo);			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}		
		return list;
	}
	
	public List<SCEncargoGruComArqItemTO> pesquisarCriticaSCEncargoGCPMACArq(Integer idArquivo) throws BusinessException {
		logger.debug("idArquivo: " + idArquivo);
		
		List<SCEncargoGruComArqItemTO> list = null;		
		try {			
			list = scEncargoGCPMACArqDAO.searchCritica(idArquivo);			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}		
		return list;
	}
	
	public List<StatusArquivoImportacaoTO> carregarComboStatusArq() throws BusinessException {
		
		List<StatusArquivoImportacaoTO> list = null;		
		try {			
			list = statusImportacaoDAO.findAll();			
		} catch (DAOException e) {
			throw new BusinessException(e);
		}		
		return list;
	}

    public List<ImportacaoServicoFixaTO> pesquisarServicoArq(ImportacaoServicoFixaTO to) throws BusinessException {
        logger.debug("ImportacaoServicoFixaTO: " + to);
        
        List<ImportacaoServicoFixaTO> list = null;      
        try {           
            list = servicoArqDAO.search(to);         
        } catch (DAOException e) {
            throw new BusinessException(e);
        }       
        return list;
    }

    public List<ServicoArqItemTO> pesquisarCriticaServicoArq(Integer idArquivo) throws BusinessException {
        logger.debug("idArquivo: " + idArquivo);
        List<ServicoArqItemTO> list;
        try {           
            list = servicoArqDAO.searchCritica(idArquivo);           
        } catch (DAOException e) {
            throw new BusinessException(e);
        }       
        return list;
    }
    
    public List<ImportacaoServicoFixaTO> pesquisarOfertaVLArq(ImportacaoServicoFixaTO to, String tpImportacao) throws BusinessException {
        logger.debug("ImportacaoServicoFixaTO: " + to);
        
        List<ImportacaoServicoFixaTO> list = null;      
        try {           
            list = ofertaVLArqDAO.search(to, tpImportacao);         
        } catch (DAOException e) {
            throw new BusinessException(e);
        }       
        return list;
    }

	public List<OfertaVLArqItemTO> pesquisarCriticaOfertaVLArq(Integer idArquivo) throws BusinessException {
        logger.debug("idArquivo: " + idArquivo);
        List<OfertaVLArqItemTO> list;
        try {           
            list = ofertaVLArqDAO.searchCriticaOferta(idArquivo);           
        } catch (DAOException e) {
            throw new BusinessException(e);
        }       
        return list;
	}    
	
	public List<OfertaVLFArqItemTO> pesquisarCriticaOfertaVLFArq(Integer idArquivo) throws BusinessException {
        logger.debug("idArquivo: " + idArquivo);
        List<OfertaVLFArqItemTO> list;
        try {           
            list = ofertaVLArqDAO.searchCriticaOfertaComplementar(idArquivo);           
        } catch (DAOException e) {
            throw new BusinessException(e);
        }       
        return list;
	} 	
}
