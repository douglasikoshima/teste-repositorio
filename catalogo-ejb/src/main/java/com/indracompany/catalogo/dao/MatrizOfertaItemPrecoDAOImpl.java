package com.indracompany.catalogo.dao;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.MatrizOfertaItemPrecoDAO;
import com.indracompany.catalogo.datalayer.CanalAtendimento;
import com.indracompany.catalogo.datalayer.CanalAtendimentoCanal;
import com.indracompany.catalogo.datalayer.MatrizOfertaItemPreco;
import com.indracompany.catalogo.datalayer.MatrizOfertaItemPrecoHist;
import com.indracompany.catalogo.datalayer.ProdutoMelhorOferta;
import com.indracompany.catalogo.datalayer.ProdutoMenorPreco;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.MatrizOfertaProdutoPrecoTO;

@Stateless
public class MatrizOfertaItemPrecoDAOImpl implements MatrizOfertaItemPrecoDAO {

	private static Logger logger = Logger
			.getLogger(SolicitacaoComercialDAOImpl.class);
	private static String SG_CANAL_ATENDIMENTO_TELEVENDAS = "TLVD";
	private static String SG_CANAL_ATENDIMENTO_LOJA_VIRTUAL = "LJVIRT";
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<MatrizOfertaProdutoPrecoTO> search(MatrizOfertaProdutoPrecoTO to)
			throws DAOException {
		logger.debug("MatrizOfertaProdutoPrecoTO: " + to);
		List<MatrizOfertaProdutoPrecoTO> result = null;
		
		StringBuilder queryStr = new StringBuilder(
			"select mip from MatrizOfertaItemPreco mip "+ 
			"join mip.matrizOfertaItem mi " + 
			"join mi.ofertaSAP os " + 
			"join mi.produto p " + 
			"join p.sistemaProduto sp " + 
			"join mip.organizacaoVenda ov " + 
			"join mip.canal c "	+ 
			"where 1=1 "
		);
		if (to.getProdutoTO().getCdProduto() != null) {
			queryStr.append("and sp.cdCodigo = :cdCodigo ");
		}
		if (to.getProdutoTO().getNmProduto() != null) {
			queryStr.append("and upper(p.nmProduto) like :nmProduto ");
		}
		if (to.getIdOrganizacaoVendasListParam() != null && !to.getIdOrganizacaoVendasListParam().isEmpty()) {
			queryStr.append("and ov.idOrganizacaoVendas in (:idOrganizacaoVendasList) ");
		}
		if (to.getCanalAtendimentoTO().getIdCanalAtendimento() != null) {
			queryStr.append("and c.idCanal = :idCanal ");
			CanalAtendimento canalAtendimento = em.find(CanalAtendimento.class, to.getCanalAtendimentoTO().getIdCanalAtendimento());
			if(canalAtendimento.getSgVivoNet().equals(SG_CANAL_ATENDIMENTO_LOJA_VIRTUAL)){
				queryStr.append(" and mip.escritorioVenda IS NULL ");
			} else if(canalAtendimento.getSgVivoNet().equals(SG_CANAL_ATENDIMENTO_TELEVENDAS)){
				queryStr.append(" and mip.escritorioVenda IS NOT NULL ");
			}
		}
		if (to.getCdAdabas() != null) {
			queryStr.append("and mip.cdAdabas = :cdAdabas ");
		}
		if (to.getIdOfertaSAPListParam() != null && !to.getIdOfertaSAPListParam().isEmpty()) {
			queryStr.append("and os.idOfertaSAP in (:idOfertaSAPList) ");
		}
		try {
			Query query = em.createQuery(queryStr.toString());
			if (to.getProdutoTO().getNmProduto() != null) {
				query.setParameter("nmProduto","%"+ to.getProdutoTO().getNmProduto()+"%");
			}
			if (to.getProdutoTO().getCdProduto() != null) {
				query.setParameter("cdCodigo", to.getProdutoTO().getCdProduto());
			}
			if (to.getIdOrganizacaoVendasListParam() != null && !to.getIdOrganizacaoVendasListParam().isEmpty()) {
				query.setParameter("idOrganizacaoVendasList", to.getIdOrganizacaoVendasListParam());
			}
			if(to.getCanalAtendimentoTO().getIdCanalAtendimento() != null){
				StringBuilder queryStr2 = new StringBuilder(
					"select cac from CanalAtendimentoCanal cac " +
					"join cac.canalAtendimento ca " +
					"where ca.idCanalAtendimento = :idCanalAtendimento " 
				);
				Query query2 = em.createQuery(queryStr2.toString());
				query2.setParameter("idCanalAtendimento", to.getCanalAtendimentoTO().getIdCanalAtendimento());
				Integer idCanalAtendDistr = ((CanalAtendimentoCanal) query2.getResultList().get(0)).getCanal().getIdCanal();
				query.setParameter("idCanal", idCanalAtendDistr);
			}
			if (to.getIdOfertaSAPListParam() != null && !to.getIdOfertaSAPListParam().isEmpty()) {
				query.setParameter("idOfertaSAPList", to.getIdOfertaSAPListParam());
			}
			if (to.getDtInicial() != null) {
				query.setParameter("dtInicioVigencia", to.getDtInicial());
			}
			List<MatrizOfertaItemPreco> matrizOfertaItemPrecoList = query.getResultList();
			result = new MatrizOfertaProdutoPrecoTOBuilder().createTOList(matrizOfertaItemPrecoList);
		} catch (Exception e) {
			throw new DAOException(e);
		}
		Calendar dtFinal = Calendar.getInstance();
		long inc = dtFinal.getTimeInMillis();
		Calendar dtTeste = Calendar.getInstance();
		dtTeste.setTimeInMillis(inc - 1);
		System.out.println(dtTeste);
		
		return result;
	}
	
	public Integer searchCount(MatrizOfertaProdutoPrecoTO to) throws DAOException {
		logger.debug("MatrizOfertaProdutoPrecoTO: " + to);
		Integer result = null;
		
		StringBuilder queryStr = new StringBuilder(
			"select mip.idMatrizOfertaItemPreco from MatrizOfertaItemPreco mip "+ 
			"join mip.matrizOfertaItem mi " + 
			"join mi.ofertaSAP os " + 
			"join mi.produto p " + 
			"join p.sistemaProduto sp " + 
			"join mip.organizacaoVenda ov " + 
			"join mip.canal c "	+ 
			"where 1=1 "
		);
		if (to.getProdutoTO().getCdProduto() != null) {
			queryStr.append("and sp.cdCodigo = :cdCodigo ");
		}
		if (to.getProdutoTO().getNmProduto() != null) {
			queryStr.append("and upper(p.nmProduto) like :nmProduto ");
		}
		if (to.getIdOrganizacaoVendasListParam() != null && !to.getIdOrganizacaoVendasListParam().isEmpty()) {
			queryStr.append("and ov.idOrganizacaoVendas in (:idOrganizacaoVendasList) ");
		}
		if (to.getCanalAtendimentoTO().getIdCanalAtendimento() != null) {
			queryStr.append("and c.idCanal = :idCanal ");
			CanalAtendimento canalAtendimento = em.find(CanalAtendimento.class, to.getCanalAtendimentoTO().getIdCanalAtendimento());
			if(canalAtendimento.getSgVivoNet().equals(SG_CANAL_ATENDIMENTO_LOJA_VIRTUAL)){
				queryStr.append(" and mip.escritorioVenda IS NULL ");
			} else if(canalAtendimento.getSgVivoNet().equals(SG_CANAL_ATENDIMENTO_TELEVENDAS)){
				queryStr.append(" and mip.escritorioVenda IS NOT NULL ");
			}
		}
		if (to.getCdAdabas() != null) {
			queryStr.append("and mip.cdAdabas = :cdAdabas ");
		}
		if (to.getIdOfertaSAPListParam() != null && !to.getIdOfertaSAPListParam().isEmpty()) {
			queryStr.append("and os.idOfertaSAP in (:idOfertaSAPList) ");
		}
		try {
			Query query = em.createQuery(queryStr.toString());
			if (to.getProdutoTO().getNmProduto() != null) {
				query.setParameter("nmProduto","%"+ to.getProdutoTO().getNmProduto()+"%");
			}
			if (to.getProdutoTO().getCdProduto() != null) {
				query.setParameter("cdCodigo", to.getProdutoTO().getCdProduto());
			}
			if (to.getIdOrganizacaoVendasListParam() != null && !to.getIdOrganizacaoVendasListParam().isEmpty()) {
				query.setParameter("idOrganizacaoVendasList", to.getIdOrganizacaoVendasListParam());
			}
			if(to.getCanalAtendimentoTO().getIdCanalAtendimento() != null){
				StringBuilder queryStr2 = new StringBuilder(
					"select cac from CanalAtendimentoCanal cac " +
					"join cac.canalAtendimento ca " +
					"where ca.idCanalAtendimento = :idCanalAtendimento " 
				);
				Query query2 = em.createQuery(queryStr2.toString());
				query2.setParameter("idCanalAtendimento", to.getCanalAtendimentoTO().getIdCanalAtendimento());
				Integer idCanalAtendDistr = ((CanalAtendimentoCanal) query2.getResultList().get(0)).getCanal().getIdCanal();
				query.setParameter("idCanal", idCanalAtendDistr);
			}
			if (to.getIdOfertaSAPListParam() != null && !to.getIdOfertaSAPListParam().isEmpty()) {
				query.setParameter("idOfertaSAPList", to.getIdOfertaSAPListParam());
			}
			if (to.getDtInicial() != null) {
				query.setParameter("dtInicioVigencia", to.getDtInicial());
			}
			System.out.println();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return result;
	}

	public void remove(Long id) throws DAOException {
		try {
			em.remove(em.find(MatrizOfertaItemPreco.class, id));
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	public void removePrecoList(List<Long> idList, String userName) throws DAOException {
		
		Calendar dtAtual = Calendar.getInstance();
		if(idList.isEmpty())
			return;
		StringBuilder queryStr = new StringBuilder(
				"select mip.idMatrizOfertaItemPreco " +
				"from MatrizOfertaItemPreco mip " +
				"where mip.idMatrizOfertaItemPreco in (:idList) " +
				"and mip.dtInicial > :dtAtual "
		);
		
		List<Long> idPrecoFuturoList = em.createQuery(queryStr.toString())
			.setParameter("idList", idList)
			.setParameter("dtAtual", dtAtual)
			.getResultList();
			
		queryStr = new StringBuilder(
				"select mip.idMatrizOfertaItemPreco " +
				"from MatrizOfertaItemPreco mip " +
				"where mip.idMatrizOfertaItemPreco in (:idList) " +
				"and mip.dtInicial <= :dtAtual "
		);
		
		List<Long> idPrecoVigenteList = em.createQuery(queryStr.toString())
			.setParameter("idList", idList)
			.setParameter("dtAtual", dtAtual)
			.getResultList();
		
		MatrizOfertaItemPreco matrizOfertaItemPreco = null;
		MatrizOfertaItemPrecoHist matrizOfertaItemPrecoHist = null;
		for(Long idPreco : idPrecoFuturoList){
			matrizOfertaItemPreco = em.find(MatrizOfertaItemPreco.class, idPreco);
			for(ProdutoMenorPreco produtoMenorPreco : matrizOfertaItemPreco.getProdutoMenorPrecoList()){
				em.remove(produtoMenorPreco);
			}
			for(ProdutoMelhorOferta produtoMelhorOferta : matrizOfertaItemPreco.getProdutoMelhorOfertaList()){
				em.remove(produtoMelhorOferta);
			}
			em.remove(matrizOfertaItemPreco);
			matrizOfertaItemPrecoHist = em.find(MatrizOfertaItemPrecoHist.class, idPreco);
			if(matrizOfertaItemPrecoHist != null)
				em.remove(matrizOfertaItemPrecoHist);
		}

		int inc = 0;
		for(Long idPreco : idPrecoVigenteList){
			matrizOfertaItemPreco = em.find(MatrizOfertaItemPreco.class, idPreco);
			for(ProdutoMenorPreco produtoMenorPreco : matrizOfertaItemPreco.getProdutoMenorPrecoList()){
				em.remove(produtoMenorPreco);
			}
			for(ProdutoMelhorOferta produtoMelhorOferta : matrizOfertaItemPreco.getProdutoMelhorOfertaList()){
				em.remove(produtoMelhorOferta);
			}
			em.remove(matrizOfertaItemPreco);
			matrizOfertaItemPrecoHist = em.find(MatrizOfertaItemPrecoHist.class, idPreco);
			if(matrizOfertaItemPrecoHist != null){
				Calendar dtFinal = Calendar.getInstance();
				dtFinal.add(Calendar.SECOND, inc--);
				matrizOfertaItemPrecoHist.setDtFinal(dtFinal);
				matrizOfertaItemPrecoHist.setNmUsuarioAlteracao(userName);
				matrizOfertaItemPrecoHist.setDtUltimaAlteracao(dtAtual);
				em.merge(matrizOfertaItemPrecoHist);
			}
		}
	}
}
