package com.indracompany.catalogo.ejb.segmentoplanoservico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.indracompany.catalogo.dao.interfaces.CategoriaDAO;
import com.indracompany.catalogo.dao.interfaces.FamiliaDAO;
import com.indracompany.catalogo.dao.interfaces.PlanoDAO;
import com.indracompany.catalogo.dao.interfaces.PlataformaDAO;
import com.indracompany.catalogo.dao.interfaces.SegmentoDAO;
import com.indracompany.catalogo.dao.interfaces.ServicoFixaDAO;
import com.indracompany.catalogo.dao.interfaces.TecnologiaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PlanoSegmentoTO;
import com.indracompany.catalogo.to.PlanoTO;
import com.indracompany.catalogo.to.PlataformaTO;
import com.indracompany.catalogo.to.ServicoFixaTO;
import com.indracompany.catalogo.to.ServicoSegmentoTO;

@Stateless(name = "SegmentoPlanoServicoBean", mappedName = "SegmentoPlanoServicoBean")
@Session(ejbName = "SegmentoPlanoServicoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.NOT_SUPPORTED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
public class SegmentoPlanoServicoBean implements SegmentoPlanoServicoBeanLocal{

	@EJB
	private PlanoDAO planoDAO;
	@EJB
	private ServicoFixaDAO servicoFixaDAO;
	@EJB
	private FamiliaDAO familiaDAO; 
	@EJB
	private CategoriaDAO categoriaDAO;
	@EJB
	private SegmentoDAO segmentoDAO;
	@EJB 
	private PlataformaDAO plataformaDAO;
	@EJB
	private TecnologiaDAO tecnologiaDAO;
	
	public List<PlanoTO> searchPlano(PlanoTO planoTO) throws BusinessException {
		List<PlanoTO> result = new ArrayList<PlanoTO>();
		
		try {
			result = planoDAO.searchPlanoSegmento(planoTO);
			PlataformaTO plataformaTO = plataformaDAO.findByIdPlataforma(planoTO.getPlataformaTO().getIdPlataforma());
			for(PlanoTO to : result){
				to.setPlataformaTO(new PlataformaTO(plataformaTO.getIdPlataforma(), plataformaTO.getNmPlataforma()));
			}
		} catch(DAOException e){
			throw new EJBException("Erro ao executar [searchPlano]",e);
		}
		
		return result;
	}

	@SuppressWarnings("rawtypes")
	public Map<String, List> loadCombos() throws BusinessException {

		Map<String, List> result = new HashMap<String, List>();
		try {
			result.put("plataformaTOList", plataformaDAO.findAll());
			result.put("categoriaPlanoTOList", categoriaDAO.findAllCategoriaPlano());
			result.put("segmentoTOList", segmentoDAO.findAll());
			result.put("tipoServicoTOList", servicoFixaDAO.findAllTpServico());
			result.put("tecnologiaTOList", tecnologiaDAO.findAllTecnologiaServico("S"));
			result.put("familiaTOList", familiaDAO.findAll());
			result.put("categoriaServicoFixaTOList", categoriaDAO.findAllCategoriaServicoFixa());
			result.put("categoriaPlanoTOList", categoriaDAO.findAllCategoriaPlano());
		} catch(DAOException e){
			throw new EJBException("Erro ao executar [loadCombos]",e);
		}
		
		return result;
	}

	public void savePlanoSegmento(PlanoSegmentoTO planoSegmentoTO) throws BusinessException {

		try {
			planoDAO.savePlanoSegmento(planoSegmentoTO);
		} catch(DAOException e){
			throw new EJBException("Erro ao executar [savePlanoSegmento]",e);
		}
	}
	
	public void disassociatePlanoSegmento(PlanoSegmentoTO planoSegmentoTO) throws BusinessException {
		
		try {
			planoDAO.disassociatePlanoSegmento(planoSegmentoTO);
		} catch (DAOException e) {
			throw new EJBException("Erro ao executar [disassociatePlanoSegmento]",e);
		}
	}

	public List<ServicoFixaTO> searchServicoFixa(ServicoFixaTO servicoFixaTO) throws BusinessException {
		List<ServicoFixaTO> result = new ArrayList<ServicoFixaTO>();
		
		try {
			result = servicoFixaDAO.searchServicoFixaSegmento(servicoFixaTO);
		} catch(DAOException e){
			throw new EJBException("Erro ao executar [searchServicoFixa]",e);
		}
		
		return result;
	}

	public void saveServicoSegmento(ServicoSegmentoTO servicoSegmentoTO) throws BusinessException {
		try {
			servicoFixaDAO.saveServicoSegmento(servicoSegmentoTO);
		} catch(Exception e){
			throw new EJBException("Erro ao executar [saveServicoSegmento]",e);
		}
	}

	public void disassociateServicoSegmento(ServicoSegmentoTO servicoSegmentoTO) throws BusinessException {
		try {
			servicoFixaDAO.disassociateServicoSegmento(servicoSegmentoTO);
		} catch(Exception e){
			throw new EJBException("Erro ao executar [disassociateServicoSegmento]",e);
		}
	}
}
