package com.indracompany.catalogo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.CategoriaDAO;
import com.indracompany.catalogo.datalayer.Categoria;
import com.indracompany.catalogo.datalayer.CategoriaSistema;
import com.indracompany.catalogo.datalayer.Familia;
import com.indracompany.catalogo.datalayer.Sistema;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategoriaTO;

@Stateless
public class CategoriaDAOImpl implements CategoriaDAO {

	private static Logger logger = Logger.getLogger(CategoriaDAO.class);
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<CategoriaTO> search(CategoriaTO categoriaTO) throws DAOException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select c from Categoria c where c.inFixa like 'S' ");
		if (categoriaTO.getIdCategoria() != null) {
			params.put("idCategoria", categoriaTO.getIdCategoria());
			hql.append("and c.idCategoria = :idCategoria ");
		}
		if (categoriaTO.getFamiliaTO() != null && categoriaTO.getFamiliaTO().getIdFamilia() != null) {
			params.put("idFamilia", categoriaTO.getFamiliaTO().getIdFamilia());
			hql.append("and c.familia.idFamilia = :idFamilia ");
		}

		if (!StringUtils.isBlank(categoriaTO.getDsCategoria())) {
			params.put("dsCategoria", String.format("%s%s%s", "%", categoriaTO.getDsCategoria(), "%"));
			hql.append("and lower(c.dsCategoria) like lower(:dsCategoria) ");
		}

		if (categoriaTO.getIndCatalogo() != null) {
			params.put("indCatalogo", categoriaTO.getIndCatalogo() ? "S" : "N");
			hql.append("and c.indCatalogo = :indCatalogo ");
		}

		if (categoriaTO.getInDisponivel() != null) {
			params.put("inDisponivel", categoriaTO.getInDisponivel() ? "S" : "N");
			hql.append("and c.inDisponivel = :inDisponivel ");
		}

		try {
			return new CategoriaTOBuilder().criarTOListComFamilia((this.getQuery(hql.toString(), params).getResultList()));
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [search]", e);
		}
	}

	private Query getQuery(String hql, Map<String, Object> params) {
		Query query = em.createQuery(hql);
		for (String param : params.keySet()) {
			query.setParameter(param, params.get(param));
		}
		return query;
	}

	public CategoriaTO insertUpdate(CategoriaTO categoriaTO) throws DAOException {
		try {
			Categoria categoria;
			if (categoriaTO.getIdCategoria() != null) {
				categoria = em.find(Categoria.class, categoriaTO.getIdCategoria());
				this.setValues(categoriaTO, categoria);
				categoria.setDtUltimaAlteracao(new Date());
				return new CategoriaTOBuilder().criarTOComFamilia(em.merge(categoria));
			} else {
				categoria = new Categoria(((BigDecimal)em.createNativeQuery("select CATALOGOPRS_OW.CATEGORIA_SQ.NEXTVAL FROM DUAL").getResultList().get(0)).intValue());
				categoria.setFamilia(new Familia(categoriaTO.getFamiliaTO().getIdFamilia()));
				categoria.setIndCatalogo("S");
				categoria.setInDisponivel("S");
				categoria.setInFixa("S");

				this.setValues(categoriaTO, categoria);
				categoria.setDtCriacao(new Date());
				categoria = em.merge(categoria);

				Integer[] idSistemaArray = {8,9};
				for (Integer idSistema : idSistemaArray) {
					CategoriaSistema categoriaSistema = new CategoriaSistema(categoria.getIdCategoria(), idSistema);
					categoriaSistema.setDtCriacao(new Date());
					categoria.addCategoriaSistema(categoriaSistema);
				}

				return new CategoriaTOBuilder().criarTOComFamilia(em.merge(categoria));
			}
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [insertUpdate]", e);
		}
	}

	private void setValues(CategoriaTO categoriaTO, Categoria categoria) {
		categoria.setNmCategoria(categoriaTO.getNmCategoria());
		categoria.setDsCategoria(categoriaTO.getDsCategoria());
		categoria.setInAlteracaoCatalogo("S");
	}

	public void changeStatus(Integer id) throws DAOException {
		try {
			Categoria categoria = em.find(Categoria.class, id);
			categoria.setInDisponivel(categoria.getInDisponivel().equalsIgnoreCase("S") ? "N" : "S");
			em.merge(categoria);
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [changeStatus]", e);
		}
	}

	public void remove(Integer id) throws DAOException {
		try {
			em.remove(em.find(Categoria.class, id));
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [changeStatus]", e);
		}
	}

	public CategoriaTO findById(Integer id) throws DAOException {
		try {
			return new CategoriaTOBuilder().criarTO(em.find(Categoria.class, id));
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [findById]", e);
		}
	}

	@SuppressWarnings("unchecked")
	public CategoriaTO searchByDescription(String dsCategoria) throws DAOException {
		Map<String, Object> params = new HashMap<String, Object>();
		StringBuilder hql = new StringBuilder("select c from Categoria c where c.inFixa like 'S' ");
		if (!StringUtils.isBlank(dsCategoria)) {
			params.put("dsCategoria", dsCategoria);
			hql.append("and lower(c.dsCategoria) like lower(:dsCategoria) ");
		}
		try {
			List<CategoriaTO> toList = new CategoriaTOBuilder().criarTOList((this.getQuery(hql.toString(), params).getResultList()));
			if (toList.size() > 0) {
				return toList.iterator().next(); 
			} else {
				return null;
			}
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [searchByName]", e);
		}
	}

	public Long contarServicoAssociado(Integer idCategoria) throws DAOException {
		Long count;
		try {
			count = (Long) em.createQuery(
					"select count(s) from Servico s " +
			"where s.categoria.idCategoria = :idCategoria ")
			.setParameter("idCategoria", idCategoria)
			.getSingleResult();
		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [contarServicoAssociado]",e);
		}
		return count;
	}

	@SuppressWarnings("unchecked")
	public List<CategoriaTO> findAllCategoriaPlano() throws DAOException {
		List<CategoriaTO> result = new ArrayList<CategoriaTO>();

		try {
			StringBuilder queryStr = new StringBuilder(
					"select distinct c " +
					"from Categoria c " +
					"join c.planoList pl " +
					"where c.inFixa = 'N' " +
					"and c.inDisponivel = 'S' " +
					"order by c.nmCategoria "
			);
			result = new CategoriaTOBuilder().criarTOList(em.createQuery(queryStr.toString()).getResultList());
		} catch(Exception e){
			throw new DAOException("Erro ao executar o DAO [findAllCategoriaPlano]",e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public List<CategoriaTO> findAllCategoriaServicoFixa() throws DAOException {
		Set<CategoriaTO> categoriaTOSet = new TreeSet<CategoriaTO>();
		List<CategoriaTO> result = new ArrayList<CategoriaTO>();
		CategoriaTOBuilder categoriaTOBuilder = new CategoriaTOBuilder();

		try {
			StringBuilder queryStr = new StringBuilder(
					"select distinct c " +
					"from Categoria c " +
					"join c.servicoList s " +
					"where c.inFixa = 'S' " +
					"order by c.nmCategoria "
			);
			categoriaTOSet = categoriaTOBuilder.criarTOSet(em.createQuery(queryStr.toString()).getResultList());
			queryStr = new StringBuilder(
					"select distinct c " +
					"from Categoria c " +
					"join c.servicoList1 s " +
					"where c.inFixa = 'S' " +
					"order by c.nmCategoria "
			);
			categoriaTOSet.addAll(categoriaTOBuilder.criarTOSet(em.createQuery(queryStr.toString()).getResultList()));
			queryStr = new StringBuilder(
					"select distinct c " +
					"from Categoria c " +
					"join c.servicoList2 s " +
					"where c.inFixa = 'S' " +
					"order by c.nmCategoria "
			);
			categoriaTOSet.addAll(categoriaTOBuilder.criarTOSet(em.createQuery(queryStr.toString()).getResultList()));
			result.addAll(categoriaTOSet);
		} catch(Exception e){
			throw new DAOException("Erro ao executar o DAO [findAllCategoriaServicoFixa]",e);
		}
		return result;
	}


	@SuppressWarnings("unchecked")
	public List<CategoriaTO> listarGrupoServico(Integer indCatalogo) throws DAOException {

		try{
			String hql = new String(" select c from Categoria c " +
					" inner join c.categoriaSistemaList cs " +
			" where c.inFixa = 'N' and ");
			if(indCatalogo.equals(1)){

				hql += " cs.sistema.idSistema = 5 ";
			}else{

				hql += " cs.sistema.idSistema = 3 ";
			}
			return(new CategoriaTOBuilder()).criarTOList( em.createQuery(hql).getResultList());
		}catch (NullPointerException npe){
			
			throw new DAOException("PARAMETRO NULO");
		}catch (Exception e){
			
			e.printStackTrace();
			throw new DAOException("ERRO INESPERADO");
		}
	}

	@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
	public CategoriaTO mergeCatalogo(CategoriaTO categoriaTO,Integer indCatalogo) throws DAOException {

		try{
			Categoria categoria = null;			
			if(categoriaTO.getIdCategoria()>0){
				
				categoria = em.find(Categoria.class,categoriaTO.getIdCategoria());
				categoria.setNmUsuarioAlteracao(categoriaTO.getNmUsuarioAlteracao());
				categoria.setDtCriacao(new Date());
			}else{
				
				categoria = new Categoria();
				categoria.setIdCategoria(
						((BigDecimal)em.createNativeQuery("select CATALOGOPRS_OW.CATEGORIA_SQ.NEXTVAL FROM DUAL").getResultList().get(0)).intValue());
				categoria.setCdCategoria(String.valueOf(categoria.getIdCategoria()));
				categoria.setDsCategoria(categoriaTO.getNmCategoria());
				categoria.setIndCatalogo("S");
				categoria.setNmuUsuarioCriacao(categoriaTO.getNmUsuarioAlteracao());
				categoria.setDtUltimaAlteracao(new Date());
				Sistema sistema = em.find(Sistema.class, (indCatalogo == 1?5:3));
				categoria.setCategoriaSistemaList(new LinkedList());
				CategoriaSistema cs = new CategoriaSistema();
				cs.setCategoria(categoria);
				cs.setCdCodigo(categoria.getCdCategoria());
				cs.setDtCriacao(new Date());
				cs.setNmUsuarioCriacao(categoria.getNmuUsuarioCriacao());
				cs.setSistema(sistema);
				categoria.getCategoriaSistemaList().add(cs);
			}
			if(categoria == null){
				
				throw new DAOException("OBJETO NAO ENCONTRADO");
			}
			categoria.setInAlteracaoCatalogo("S");
			categoria.setInFixa("N");
			categoria.setNmCategoria(categoriaTO.getNmCategoria());
			categoria.setInDisponivel((categoriaTO.getInDisponivel()?"S":"N"));
			em.persist(categoria);
			return (new CategoriaTOBuilder()).criarTO(categoria);
		}catch (Exception e){
			
			e.printStackTrace();
			throw new DAOException("ERRO INESPERADO");
		}
	}
	
	public void removeCatalogoMovel(Integer id) throws DAOException {
		try {
			String exceptionSTR = "ERRO";
			System.out.println(exceptionSTR);
			int resultado = ((BigDecimal)em.createNativeQuery(" SELECT COUNT(IDCATEGORIALEGADO) FROM SERVICO WHERE IDCATEGORIALEGADO = :id ").setParameter("id", id).getSingleResult()).intValue();
			logger.debug(resultado);
			if(resultado > 0){
				exceptionSTR = exceptionSTR + " CATEGORIALEGADO";
			}
			em.clear();
			resultado = ((BigDecimal)em.createNativeQuery(" SELECT COUNT(IDCATEGORIACATALOGO) FROM SERVICO WHERE IDCATEGORIACATALOGO = :id ").setParameter("id", id).getSingleResult()).intValue();
			logger.debug(resultado);
			if(resultado >0){
				exceptionSTR = exceptionSTR + " CATEGORIACATALOGO";
			}
			System.out.println(exceptionSTR);
			if(!exceptionSTR.equals("ERRO")){
				
				throw new DAOException(exceptionSTR);
			}
			em.remove(em.find(Categoria.class, id));
		} catch (DAOException e) {
			
			e.printStackTrace();
			throw new DAOException(e.getMessage(),e);
		
		}catch (Exception e) {
			
			e.printStackTrace();
			throw new DAOException("ERRO INESPERADO",e);
		}
	}
}