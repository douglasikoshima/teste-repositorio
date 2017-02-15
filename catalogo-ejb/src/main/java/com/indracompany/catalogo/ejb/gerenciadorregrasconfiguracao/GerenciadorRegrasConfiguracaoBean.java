package com.indracompany.catalogo.ejb.gerenciadorregrasconfiguracao;

import java.util.ArrayList;
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

import com.indracompany.catalogo.dao.interfaces.GerenciadorRegraDAO;
import com.indracompany.catalogo.dao.interfaces.IndicadorComercialDAO;
import com.indracompany.catalogo.dao.interfaces.RegraPrioridadeAltaDAO;
import com.indracompany.catalogo.dao.interfaces.TipoDocumentoDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GerenciadorRegrasConfiguracaoTO;
import com.indracompany.catalogo.to.IndicadorComercialDocumentoTO;
import com.indracompany.catalogo.to.RegraPrioridadeAltaTO;
import com.indracompany.catalogo.to.TipoDocumentoTO;

@Stateless(name = "GerenciadorRegrasConfiguracaoBean", mappedName = "GerenciadorRegrasConfiguracaoBean")
@Session(ejbName = "GerenciadorRegrasConfiguracaoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class GerenciadorRegrasConfiguracaoBean implements GerenciadorRegrasConfiguracaoBeanLocal{

	@EJB
	private GerenciadorRegraDAO gerenciadorRegraDAO;
	@EJB
	private RegraPrioridadeAltaDAO regraPrioridadeAltaDAO;
	@EJB
	private IndicadorComercialDAO indicadorComercialDAO;
	@EJB
	private TipoDocumentoDAO tipoDocumentoDAO;
	
	public List<String> buscarNmIndicadorComercialPorTipoFiltro(GerenciadorRegrasConfiguracaoTO gerenciadorRegrasConfiguracaoTO) throws BusinessException {
		List<String> result = new ArrayList<String>();
		
		try {
			result = gerenciadorRegraDAO.buscarNmIndicadorComercial(gerenciadorRegrasConfiguracaoTO.getInTipoFiltro());
		} catch(DAOException e){
			throw new EJBException(e);
		}
		
		return result;
	}
	
	public List<RegraPrioridadeAltaTO> buscarRegraPrioridadeAltaTO() throws BusinessException {
		List<RegraPrioridadeAltaTO> result = new ArrayList<RegraPrioridadeAltaTO>();
		
		try {
			result = regraPrioridadeAltaDAO.buscar();
		} catch(DAOException e){
			throw new EJBException(e);
		}
		
		return result;
	}

	public List<IndicadorComercialDocumentoTO> buscarTodosIndicadoresDocumento() throws BusinessException {
		List<IndicadorComercialDocumentoTO> result = new ArrayList<IndicadorComercialDocumentoTO>();
		
		try {
			result = indicadorComercialDAO.findAllIndicadoresDocumento();
		} catch(Exception e){
			throw new EJBException(e); 
		}
		return result;
	}
	
	public List<TipoDocumentoTO> buscarTodosTiposDocumento() throws BusinessException {
		List<TipoDocumentoTO> result = new ArrayList<TipoDocumentoTO>();
		
		try {
			result = tipoDocumentoDAO.findAll();
		} catch(DAOException e){
			throw new EJBException(e);
		}
		
		return result;
	}

	public IndicadorComercialDocumentoTO buscarTipoDocumentoPorIndicador(IndicadorComercialDocumentoTO indicadorComercialDocumentoTO) throws BusinessException {
		IndicadorComercialDocumentoTO result = new IndicadorComercialDocumentoTO();
		
		try {
			result.setIdIndicadorComercial(indicadorComercialDocumentoTO.getIdIndicadorComercial());
			result.setNmIndicadorComercial(indicadorComercialDocumentoTO.getNmIndicadorComercial());
			result.setTipoDocumentoTOList(indicadorComercialDAO.findDocumentosIndicador(indicadorComercialDocumentoTO));
		} catch(DAOException e){
			throw new EJBException(e);
		}
		
		return result;
	}
	
	public List<GerenciadorRegrasConfiguracaoTO> pesquisar(GerenciadorRegrasConfiguracaoTO gerenciadorRegraConfiguracaoTO) throws BusinessException {
		List<GerenciadorRegrasConfiguracaoTO> result = new ArrayList<GerenciadorRegrasConfiguracaoTO>();
		
		try {
			gerenciadorRegraDAO.pesquisar(gerenciadorRegraConfiguracaoTO);
		} catch(DAOException e){
			throw new EJBException(e);
		}
		return result;
	}

	public List<GerenciadorRegrasConfiguracaoTO> pesquisarPorIdCanalAtendimento(GerenciadorRegrasConfiguracaoTO gerenciadorRegraConfiguracaoTO) throws BusinessException {
		List<GerenciadorRegrasConfiguracaoTO> result = new ArrayList<GerenciadorRegrasConfiguracaoTO>();
		
		try {
			result = gerenciadorRegraDAO.pesquisarPorIdCanalAtendimento(gerenciadorRegraConfiguracaoTO);
		} catch(DAOException e){
			throw new EJBException(e);
		}
		return result;
	}

	public void salvar(GerenciadorRegrasConfiguracaoTO gerenciadorRegrasConfiguracaoTO) throws BusinessException {
		try {
			gerenciadorRegraDAO.salvar(gerenciadorRegrasConfiguracaoTO);
		} catch(DAOException e){
			throw new EJBException(e);
		}
	}
	
	public void salvar(IndicadorComercialDocumentoTO indicadorComercialDocumentoTO) throws BusinessException {
		try {
			indicadorComercialDAO.save(indicadorComercialDocumentoTO);
		} catch(DAOException e){
			throw new EJBException(e);
		}
	}


}
