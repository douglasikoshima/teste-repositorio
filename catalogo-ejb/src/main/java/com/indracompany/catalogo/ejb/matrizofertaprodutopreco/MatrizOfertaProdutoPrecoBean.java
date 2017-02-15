package com.indracompany.catalogo.ejb.matrizofertaprodutopreco;

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

import com.indracompany.catalogo.dao.interfaces.AcaoDAO;
import com.indracompany.catalogo.dao.interfaces.CanalAtendimentoDAO;
import com.indracompany.catalogo.dao.interfaces.CanalDAO;
import com.indracompany.catalogo.dao.interfaces.MatrizOfertaItemPrecoDAO;
import com.indracompany.catalogo.dao.interfaces.OfertaSAPDAO;
import com.indracompany.catalogo.dao.interfaces.OrganizacaoVendaDAO;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AcaoTO;
import com.indracompany.catalogo.to.CanalAtendimentoTO;
import com.indracompany.catalogo.to.CanalTO;
import com.indracompany.catalogo.to.MatrizOfertaProdutoPrecoTO;
import com.indracompany.catalogo.to.OfertaSAPTO;
import com.indracompany.catalogo.to.OrganizacaoVendaTO;

@Stateless(name = "MatrizOfertaProdutoBean", mappedName = "MatrizOfertaProdutoBean")
@Session(ejbName = "MatrizOfertaProdutoBean", enableCallByReference = Bool.TRUE, type = SessionType.STATELESS, defaultTransaction = weblogic.ejbgen.Constants.TransactionAttribute.REQUIRED)
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MatrizOfertaProdutoPrecoBean implements MatrizOfertaProdutoPrecoBeanLocal{
	
	@EJB
	private AcaoDAO acaoDAO;
	@EJB
	private CanalDAO canalDAO;
	@EJB
	private CanalAtendimentoDAO canalAtendimentoDAO;
	@EJB
	private OfertaSAPDAO ofertaSAPDAO;
	@EJB
	private MatrizOfertaItemPrecoDAO matrizOfertaItemPrecoDAO;
	@EJB
	private OrganizacaoVendaDAO organizacaoVendaDAO;
	
	public List<MatrizOfertaProdutoPrecoTO> search(MatrizOfertaProdutoPrecoTO matrizOfertaProdutoPrecoTO) throws BusinessException {

		List<MatrizOfertaProdutoPrecoTO> result = new ArrayList<MatrizOfertaProdutoPrecoTO>();
		List<List<Integer>> grupoIdOfertaSAP = splitIntegerList(matrizOfertaProdutoPrecoTO.getIdOfertaSAPListParam(), 900);
		try {
			for(List<Integer> idOfertaSAPList : grupoIdOfertaSAP){
				matrizOfertaProdutoPrecoTO.setIdOfertaSAPListParam(idOfertaSAPList);
				result.addAll(matrizOfertaItemPrecoDAO.search(matrizOfertaProdutoPrecoTO));
			}
		} catch(DAOException e) {
			throw new BusinessException(e);
		}
		return result;
	}
	
	public List<AcaoTO> findAllAcaoTO() {
		
		try {
			return acaoDAO.findAll();
		} catch (DAOException e){
			throw new EJBException(e);
		}
	}

	public List<CanalTO> findAllCanalTO() {
		try {
			return canalDAO.findAll();
		} catch (DAOException e){
			throw new EJBException(e);
		}
	}

	public List<CanalAtendimentoTO> findAllCanalAtendimentoTO() {
		try {
			return canalAtendimentoDAO.findAll();
		} catch (DAOException e){
			throw new EJBException(e);
		}
	}

	public List<OfertaSAPTO> findAllOfertaSAPTO() {
		try {
			return ofertaSAPDAO.findAll();
		} catch (DAOException e){
			throw new EJBException(e);
		}
	}

	public void removePrecoList(List<Long> idList, String userName) throws BusinessException {
		try {
			for(List<Long> idList2 : splitLongList(idList, 900)){
				matrizOfertaItemPrecoDAO.removePrecoList(idList2, userName);
			}
		} catch (DAOException e){
			throw new EJBException(e);
		}
	}

	public List<OrganizacaoVendaTO> findAllOrganizacaoVendaTO() throws BusinessException {
		try {
			return organizacaoVendaDAO.findAll();
		} catch (DAOException e){
			throw new EJBException(e);
		}
	}
	

	private List<List<Integer>> splitIntegerList(List<Integer> list, int maxSize) throws BusinessException {
		if(maxSize <= 0){
			maxSize = 100;
		}
		int li = 0;
		int tamList = list.size();
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		int qtLists = list.size()/maxSize;
		for(int i = 0;i <= qtLists;	 i++){
			result.add(list.subList(li, li+maxSize < tamList ? li+maxSize : tamList));
			li=li+maxSize;
		}
		return result;
	}

	private List<List<Long>> splitLongList(List<Long> list, int maxSize) throws BusinessException {
		if(maxSize <= 0){
			maxSize = 100;
		}
		int li = 0;
		int tamList = list.size();
		List<List<Long>> result = new ArrayList<List<Long>>();
		int qtLists = list.size()/maxSize;
		for(int i = 0;i <= qtLists; i++){
			result.add(list.subList(li, li+maxSize < tamList ? li+maxSize : tamList));
			li=li+maxSize;
		}
		return result;
	}
}