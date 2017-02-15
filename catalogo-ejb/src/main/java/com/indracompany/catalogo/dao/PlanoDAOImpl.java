package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.PlanoDAO;
import com.indracompany.catalogo.datalayer.Plano;
import com.indracompany.catalogo.datalayer.PlanoSegmento;
import com.indracompany.catalogo.datalayer.Segmento;
import com.indracompany.catalogo.datalayer.TipoPlano;
import com.indracompany.catalogo.datalayer.Uf;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.PlanoConfiguracaoScoreTO;
import com.indracompany.catalogo.to.PlanoParametroTO;
import com.indracompany.catalogo.to.PlanoSegmentoTO;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;
import com.indracompany.catalogo.to.PlanoTO;
import com.indracompany.catalogo.to.TipoPlanoTO;
import com.indracompany.catalogo.to.UfTO;

/**
 * @author Luiz Pereira
 * 
 * Classe responsável em fazer a persistencia no banco de dados de Plano.
 */
@Stateless
public class PlanoDAOImpl implements PlanoDAO {

	private static Logger logger = Logger.getLogger(PlanoDAOImpl.class);

	@PersistenceContext
	private EntityManager em;
	
	private Integer IDPLATAFORMAPREPAGO = 1;

	@SuppressWarnings("unchecked")
	public List<CategorizacaoAnaliseCreditoTO> searchPlanoConfigAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException {

		StringBuffer queryStr = new StringBuffer();
		queryStr.append("select ");
		queryStr.append(" new com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO( p.idPlano, p.nmComercial, cs.nmCategoriaScore, cs.idCategoriaScore, trunc( avg(nvl(pcsa.valorAssinaturaMensal, 0)), 2 ), pcs.idPlanoCategoriaScore, sp.cdCodigo )");
		queryStr.append(" from ");
		queryStr.append(" Plano as p ");
		queryStr.append(" inner join p.sistemaPlano as sp ");
		queryStr.append(" inner join p.planoCategoriaScore as pcs ");
		queryStr.append(" inner join pcs.categoriaScore as cs ");
		queryStr.append(" left outer join p.planoCSAList as pcsa ");
		queryStr.append(" where cs.idCategoriaScore = :idCategoriaScore ");
		
		if (categorizacaoAnaliseCreditoTO.getId() != null) {
			queryStr.append(" and p.idPlano = :idPlano ");
		}

		if (categorizacaoAnaliseCreditoTO.getNome() != null && !categorizacaoAnaliseCreditoTO.getNome().equals("")) {
			queryStr.append(" and upper(p.nmComercial) like upper(:nmComercial) ");
		}

		if (categorizacaoAnaliseCreditoTO.getIdAnaliseCredito() != null) {
			queryStr.append(" and pcs.idPlanoCategoriaScore in (" +
					"select pcat.idPlanoCategoriaScore from PlanoConfiguracaoScore plnConf " +
					" inner join plnConf.analiseCredito anl" +
					" inner join plnConf.planoCategoriaScore pcat " +
					" inner join plnConf.cabecalhoAnaliseCredito cab " +
					" inner join pcat.categoriaScore cats " +
					" where cab.idCabecalhoAnaliseCredito = :idCabecalhoAnaliseCredito " +
					" and  cats.idCategoriaScore = :idCategoriaScore " +
			" and anl.idAnaliseCredito = :idAnaliseCredito) ");
		}

		queryStr.append(" group by p.idPlano, p.nmComercial, cs.nmCategoriaScore, cs.idCategoriaScore, pcs.idPlanoCategoriaScore, sp.cdCodigo");

		if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null || categorizacaoAnaliseCreditoTO.getPrecoAte() != null) {

			if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null && categorizacaoAnaliseCreditoTO.getPrecoAte() != null) {
				queryStr.append(" having trunc( avg(nvl(pcsa.valorAssinaturaMensal, 0)), 2 ) between :precoDe and :precoAte ");				
			} else {
				if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null ) {
					queryStr.append(" having trunc( avg(nvl(pcsa.valorAssinaturaMensal, 0)), 2 ) >= :precoDe ");	
				}
				if (categorizacaoAnaliseCreditoTO.getPrecoAte() != null ) {
					queryStr.append(" having trunc( avg(nvl(pcsa.valorAssinaturaMensal, 0)), 2 ) <= :precoAte ");
				}
			}

		}

		queryStr.append(" order by p.nmComercial ");

		Query query = em.createQuery(queryStr.toString());
		
		query.setParameter("idCategoriaScore", categorizacaoAnaliseCreditoTO.getIdCategoria());

		if (categorizacaoAnaliseCreditoTO.getId() != null) {
			query.setParameter("idPlano", categorizacaoAnaliseCreditoTO.getId());
		}

		if (categorizacaoAnaliseCreditoTO.getNome() != null && !categorizacaoAnaliseCreditoTO.getNome().equals("")) {
			query.setParameter("nmComercial", "%" + categorizacaoAnaliseCreditoTO.getNome().trim() + "%");
		}

		if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null || categorizacaoAnaliseCreditoTO.getPrecoAte() != null) {

			if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null && categorizacaoAnaliseCreditoTO.getPrecoAte() != null) {
				query.setParameter("precoDe", categorizacaoAnaliseCreditoTO.getPrecoDe());
				query.setParameter("precoAte", categorizacaoAnaliseCreditoTO.getPrecoAte());
			} else {
				if (categorizacaoAnaliseCreditoTO.getPrecoDe() != null ) {
					query.setParameter("precoDe", categorizacaoAnaliseCreditoTO.getPrecoDe());	
				}
				if (categorizacaoAnaliseCreditoTO.getPrecoAte() != null ) {
					query.setParameter("precoAte", categorizacaoAnaliseCreditoTO.getPrecoAte());
				}
			}

		}

		if (categorizacaoAnaliseCreditoTO.getIdAnaliseCredito() != null) {
			query.setParameter("idCabecalhoAnaliseCredito", categorizacaoAnaliseCreditoTO.getIdCabecalhoAnaliseCredito());
			query.setParameter("idAnaliseCredito", categorizacaoAnaliseCreditoTO.getIdAnaliseCredito());
		}

		List<CategorizacaoAnaliseCreditoTO> list = query.getResultList();
		return list;

	}

	@SuppressWarnings("unchecked")
	public List<CategorizacaoAnaliseCreditoTO> searchPlanoAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException {

		StringBuffer queryStr = new StringBuffer();
		queryStr.append("select distinct ");
		queryStr.append(" new com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO(p, pp.plataforma.idPlataforma, pp.plataforma.nmPlataforma, sp )");
		queryStr.append(" from ");
		queryStr.append(" PlanoPlataforma pp ");
		queryStr.append(" inner join pp.plano p ");
		queryStr.append(" inner join p.sistemaPlano sp ");

		if (categorizacaoAnaliseCreditoTO.getIdCategoria() != null) {
			queryStr.append(" inner join p.planoCategoriaScore pcs ");
		}

		queryStr.append(" where p.inDisponivel = 'S' ");

		if (categorizacaoAnaliseCreditoTO.getIdCategoria() != null) {
			queryStr.append(" and pcs.categoriaScore.idCategoriaScore = :idCategoriaScore ");
		}

		if (categorizacaoAnaliseCreditoTO.getIdPlataformas() != null && categorizacaoAnaliseCreditoTO.getIdPlataformas().length > 0) {
			queryStr.append(" and pp.plataforma.idPlataforma in (:idPlataforma1, :idPlataforma2, :idPlataforma3)");
		}

		if (categorizacaoAnaliseCreditoTO.getNome() != null && !categorizacaoAnaliseCreditoTO.getNome().equals("")) {
			queryStr.append(" and upper(p.nmComercial) like upper(:nmComercial) ");
		}

		queryStr.append(" order by p.nmComercial ");

		Query query = em.createQuery(queryStr.toString());

		if (categorizacaoAnaliseCreditoTO.getIdCategoria() != null) {
			query.setParameter("idCategoriaScore", categorizacaoAnaliseCreditoTO.getIdCategoria());
		}

		int contador = 0;

		if (categorizacaoAnaliseCreditoTO.getIdPlataformas() != null && categorizacaoAnaliseCreditoTO.getIdPlataformas().length > 0) {
			query.setParameter("idPlataforma1", 0);
			query.setParameter("idPlataforma2", 0);
			query.setParameter("idPlataforma3", 0);

			for (Integer idPlataforma : categorizacaoAnaliseCreditoTO.getIdPlataformas()) {
				if (contador == 0) {
					query.setParameter("idPlataforma1", idPlataforma);
				} else if (contador == 1) {
					query.setParameter("idPlataforma2", idPlataforma);
				} else {
					query.setParameter("idPlataforma3", idPlataforma);
				}
				contador++;
			}
		}

		if (categorizacaoAnaliseCreditoTO.getNome() != null && !categorizacaoAnaliseCreditoTO.getNome().equals("")) {
			query.setParameter("nmComercial", "%" + categorizacaoAnaliseCreditoTO.getNome() + "%");
		}

		List<CategorizacaoAnaliseCreditoTO> list = query.getResultList();

		return list;
	} 

	public void updatePlanoAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException {


	}

	public void updateValuePlanoTO(List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreTOList) throws DAOException {
		CategorizacaoAnaliseCreditoTO parametro = new CategorizacaoAnaliseCreditoTO();
		Map<String, Float> valoresMap = new HashMap<String, Float>();
		for (PlanoConfiguracaoScoreTO scoreTO : planoConfiguracaoScoreTOList) {
			Integer idCategoria = scoreTO.getPlanoCategoriaScoreTO().getCategoriaScoreTO().getIdCategoriaScore();
			Integer id = scoreTO.getPlanoCategoriaScoreTO().getIdPlano();
			String key = idCategoria + "|" + id;
			Float valor = valoresMap.get(key);
			if (valor == null) {
				parametro.setIdCategoria(idCategoria);
				parametro.setId(id);
				List<CategorizacaoAnaliseCreditoTO> list = searchPlanoConfigAnaliseCredito(parametro);
				valor = list.get(0).getValor();
				valoresMap.put(key, valor);
			}
			scoreTO.getPlanoCategoriaScoreTO().setVlPlano(valor);
		}
	}

	@SuppressWarnings("unchecked")
	public List<PlanoServicoUfRestricaoTO> searchPlanoUfRestricao(PlanoServicoUfRestricaoTO planoServicoUfRestricaoTO) throws DAOException {

		logger.debug(">> searchPlanoUfRestricao()");

		List<PlanoServicoUfRestricaoTO> list;
		PlanoTOBuilder builder = new PlanoTOBuilder();

		try {

			StringBuffer queryStr = new StringBuffer();
			queryStr.append(" select ");
			queryStr.append(" p ");
			queryStr.append(" from ");
			queryStr.append(" Plano p ");
			queryStr.append(" inner join p.sistemaPlano sp ");
			queryStr.append(" where 1 = 1 ");

			if (planoServicoUfRestricaoTO.getNome() != null && !planoServicoUfRestricaoTO.getNome().trim().equals("")) {
				queryStr.append(" and upper(p.nmComercial) like upper(:nmComercial) ");
			}

			if (planoServicoUfRestricaoTO.getCodigo() != null && !planoServicoUfRestricaoTO.getCodigo().trim().equals("")) {
				queryStr.append(" and upper(sp.cdCodigo) like upper(:cdCodigo) ");
			}

			if (planoServicoUfRestricaoTO.getIn4g() != null && !planoServicoUfRestricaoTO.getIn4g().trim().equals("")) {
				queryStr.append(" and upper(p.in4g) = upper(:in4g) ");
			}

			queryStr.append(" order by p.nmComercial ");

			Query query = em.createQuery(queryStr.toString());

			if (planoServicoUfRestricaoTO.getNome() != null && !planoServicoUfRestricaoTO.getNome().trim().equals("")) {
				query.setParameter("nmComercial", "%" + planoServicoUfRestricaoTO.getNome().trim() + "%");
			}

			if (planoServicoUfRestricaoTO.getCodigo() != null && !planoServicoUfRestricaoTO.getCodigo().trim().equals("")) {
				query.setParameter("cdCodigo", "%" + planoServicoUfRestricaoTO.getCodigo().trim() + "%");
			}

			if (planoServicoUfRestricaoTO.getIn4g() != null && !planoServicoUfRestricaoTO.getIn4g().trim().equals("")) {
				query.setParameter("in4g", planoServicoUfRestricaoTO.getIn4g());
			}

			list = builder.createPlanoServicoUfRestricaoTOList(query.getResultList());

		} catch (Exception e) {
			throw new DAOException("Erro ao executar o DAO [searchPlanoUfRestricao]", e);
		}

		logger.debug("<< searchPlanoUfRestricao()");

		return list;

	}

	@SuppressWarnings("unchecked")
	public List<PlanoTO> search(PlanoTO to) throws DAOException {
		List<PlanoTO> result = new ArrayList<PlanoTO>();

		try {
			StringBuilder queryStr = null;
			
			if(to.getPlataformaTO().getIdPlataforma().intValue() == IDPLATAFORMAPREPAGO){
				queryStr = new StringBuilder(
						"select pl " +
						"from Plano pl " +
						"join pl.plataforma pf " +
						"join pl.sistemaPlano spl " +
						"where pl.inDisponivel = 'S' "
				);
			  }else{
				   queryStr = new StringBuilder(
							"select pl " +
							"from Plano pl " +
							"join pl.sistemaPlano spl " +
							"join pl.planoPlataformaList plpf " +
							"join plpf.plataforma pf " +
							"where pl.inDisponivel = 'S' "
					);				
			}
			
			if(to.getPlataformaTO() != null){
				queryStr.append("and pf.idPlataforma = :idPlataforma ");
			}
			if(to.getSgTitularidade() != null){
				queryStr.append(" and pl.indTitDep = :sgTitularidade ");
			}
			if(to.getCdPlano() != null){
				queryStr.append(" and spl.cdCodigo = :cdCodigo ");
			}
			if(to.getNmComercial() != null){
				queryStr.append(" and upper(pl.nmComercial) like :nmComercial ");
			}

			queryStr.append("order by pl.nmComercial ");

			Query query = em.createQuery(queryStr.toString());

			if(to.getPlataformaTO() != null){
				query.setParameter("idPlataforma", to.getPlataformaTO().getIdPlataforma());
			}
			if(to.getSgTitularidade() != null){
				query.setParameter("sgTitularidade", to.getSgTitularidade());
			}
			if(to.getCdPlano() != null){
				query.setParameter("cdCodigo", to.getCdPlano().trim());
			}
			if(to.getNmComercial() != null){
				query.setParameter("nmComercial",("%"+to.getNmComercial().trim().toUpperCase()+"%"));
			}

			result = new PlanoTOBuilder().creatoPlanoTOList(query.getResultList());
		} catch(Exception e){
			throw new DAOException(e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public void savePlanoSegmento(PlanoSegmentoTO to) throws DAOException {

		try {
			StringBuilder queryStr = new StringBuilder(
					"select ps " +
					"from PlanoSegmento ps " +
					"join ps.segmento s " +
					"join ps.plano pl " +
					"where pl.idPlano in (:idPlanoList) "
			);
			List<Integer> idPlanoList = new ArrayList<Integer>();
			for(PlanoTO planoTO : to.getPlanoTOList()){
				idPlanoList.add(planoTO.getIdPlano());
			}
			List<PlanoSegmento> planoSegmentoRemoveList = em.createQuery(queryStr.toString()).setParameter("idPlanoList", idPlanoList).getResultList();
			for(PlanoSegmento planoSegmento: planoSegmentoRemoveList){
				em.remove(planoSegmento);
			}
			em.flush();

			for(PlanoTO planoTO: to.getPlanoTOList()){
				PlanoSegmento planoSegmento = new PlanoSegmento(
						new Plano(planoTO.getIdPlano())
						,new Segmento(to.getSegmentoTO().getIdSegmento())
						,to.getInInfancia()
						,to.getNmUsuarioCriacao()
						,Calendar.getInstance()
						,"N"
				);
				em.merge(planoSegmento);
			}

		} catch(Exception e) {
			throw new DAOException("Erro ao executar [savePlanoSegmento]",e);
		}
	}

	public void disassociatePlanoSegmento(PlanoSegmentoTO to) throws DAOException{
		try {
			List<Integer> idPlanoList = new ArrayList<Integer>();
			for(PlanoTO planoTO: to.getPlanoTOList()){
				idPlanoList.add(planoTO.getIdPlano());
			}
			StringBuilder queryStr = new StringBuilder(
					"select ps " +
					"from PlanoSegmento ps " +
					"join ps.plano pl " +
					"where pl.idPlano in (:idPlanoList) "
			);
			@SuppressWarnings("unchecked")
			List<PlanoSegmento> planoSegmentoRemoveList = em.createQuery(queryStr.toString()).setParameter("idPlanoList", idPlanoList).getResultList();
			for(PlanoSegmento planoSegmento: planoSegmentoRemoveList){
				em.remove(planoSegmento);
			}
		} catch(Exception e) {
			throw new DAOException("Erro ao executar [savePlanoSegmento]",e);
		}
	}
	@SuppressWarnings("unchecked")
	public List<PlanoParametroTO> searchPlanoParametro(PlanoParametroTO to) throws DAOException {

		logger.debug(">>> inicio searchPlanoParametro");
		if(to.getIdPlataforma() == null){
			throw new DAOException("idPlataforma nulo");
		}
		if(to.getRegistrosPorPagina() == null){
			throw new DAOException("registrosPorPagina nulo");
		}
		Map<Integer,String> ufsMap = null;
		StringBuilder sbQuery = new StringBuilder();
		StringBuilder sbParametros = new StringBuilder();
		List<String> sbCriteria = new LinkedList<String>();
		Map<String,Object> parametros = new HashMap<String,Object>();
		sbParametros.append(" DISTINCT plano.idPlano, plano.nmComercial, sistemaPlano.cdCodigo ");
		sbParametros.append(", plano.ativaWifi, plano.inDisponivel, plano.indTitDep ");
		sbQuery.append(" select PARAMETROS from Plano plano ");
		sbQuery.append(" inner join plano.sistemaPlano sistemaPlano ");
		sbQuery.append(" inner join sistemaPlano.sistema sistema");
		if(to.getIdPlataforma().equals(1)){ // prepago  :) repare que o relacionamento muda de acordo com a plataforma

			sbQuery.append(" inner join plano.plataforma  plataforma ");
			sbCriteria.add(" plataforma.idPlataforma = :idPlataforma ");
			parametros.put("idPlataforma", to.getIdPlataforma());
			sbCriteria.add(" sistema.idSistema = :idSistema ");
			parametros.put("idSistema",3);
			if( to.getUFs()!= null && !to.getUFs().isEmpty() ){

				sbQuery.append(" inner join plano.regional regional ");
				sbQuery.append(" inner join regional.ufList listaUF ");
				sbCriteria.add(" listaUF.idUf in (:idUfs) ");
				List<Integer> idUfs = new LinkedList<Integer>();
				Iterator<UfTO> iterator = to.getUFs().iterator();
				while (iterator.hasNext()){

					idUfs.add(iterator.next().getIdUf().intValue());
				}
				parametros.put("idUfs",idUfs);
			}
		}else{ // !prepago {:O  � tudo uma questao de relacionamento.

			sbQuery.append(" inner join plano.planoPlataformaList  plataformaList ");
			sbCriteria.add(" plataformaList.plataforma.idPlataforma = :idPlataforma ");
			parametros.put("idPlataforma", to.getIdPlataforma());
			sbCriteria.add(" sistema.idSistema = :idSistema ");
			parametros.put("idSistema",2);
			if( to.getUFs()!= null && !to.getUFs().isEmpty() ){ 
				/*
				 * Esse � melhor que o relacionamento do if anterior!
				 * Para descobrir os estados de um plano devesse consultar o nome da CSA 
				 * quando csa.idarearegistro == null ou por csa > arearegistro > uf
				 * quando csa.idareagegistro != null
				 */
				sbQuery.append(" inner join plano.planoCSAList planoCSAList ");
				sbQuery.append(" left outer join planoCSAList.csa csa ");
				sbQuery.append(" left outer join csa.areaRegistro areaRegistro");
				sbQuery.append(" left outer join areaRegistro.uf uf");
				sbCriteria.add(" ( uf.idUf in (:idUf) or csa.nmCSA in (:nmCSA) ) ");

				List<Integer> idUfs = new LinkedList<Integer>();
				List<String> nmUfs = new LinkedList<String>();
				Iterator<UfTO> iterator = to.getUFs().iterator();
				ufsMap = recuperarMapUf();
				System.out.println(ufsMap);
				while (iterator.hasNext()){

					UfTO uf = iterator.next();
					idUfs.add(uf.getIdUf().intValue());
					nmUfs.add(ufsMap.get(uf.getIdUf().intValue()));
				}
				parametros.put("idUf", idUfs);
				parametros.put("nmCSA", nmUfs);
				System.out.println();
			}
		}
		if(to.getNomeComercial()!= null){

			sbCriteria.add(" plano.nmComercial like upper( :nmComercial ) ");
			parametros.put("nmComercial", "%"+to.getNomeComercial()+"%");
		}
		if(to.getNomeTecnico()!= null){

			sbCriteria.add(" sistemaPlano.cdCodigo like upper( :nmTecnico ) ");
			parametros.put("nmTecnico", "%"+to.getNomeTecnico()+"%");
		}
		if (to.getClassificacao() != null && !to.getClassificacao().equals("N")) {
			String classifCriterio = " ( plano.indTitDep like upper( :indTitDep ) ";
			if (to.getClassificacao().equals("A")) {
				classifCriterio = classifCriterio + " or plano.indTitDep is null ";
			}
			classifCriterio = classifCriterio + " ) ";
			sbCriteria.add(classifCriterio);
			parametros.put("indTitDep", "" + to.getClassificacao() + "");
		}

		sbQuery.append(" where ");
		@SuppressWarnings("rawtypes")
		Iterator iterator = sbCriteria.iterator();
		while(iterator.hasNext()){

			sbQuery.append(iterator.next());
			if(iterator.hasNext()){

				sbQuery.append(" and ");
			}
		}
		Query queryCount = em.createQuery(sbQuery.toString().replace("PARAMETROS", " count( DISTINCT plano.idPlano ) "));
		for(Entry<String,Object> entry:parametros.entrySet()){

			queryCount.setParameter(entry.getKey(), entry.getValue());
		}
		Long registros = (Long)queryCount.getResultList().get(0);
		if(to.getNomeCampoOrdenacao() != null){

			sbQuery.append(" order by ");
			sbQuery.append(to.getNomeCampoOrdenacao());
			sbQuery.append(to.getOrdemCrescente()?" asc ":" desc ");
		}
		Query query = em.createQuery(sbQuery.toString().replace("PARAMETROS", sbParametros.toString())).setFirstResult(to.getRegistroAtual()).setMaxResults(to.getRegistrosPorPagina());
		for(Entry<String,Object> entry:parametros.entrySet()){

			query.setParameter(entry.getKey(), entry.getValue());
		}
		List<Object[]> results = (List<Object[]>) query.getResultList();
		List<PlanoParametroTO> planos = new LinkedList<PlanoParametroTO>();
		for(Object[] objs:results){

			PlanoParametroTO pp = new PlanoParametroTO();
			pp.setIdPlano((Integer)objs[0]);
			pp.setNomeComercial((String)(objs[1]!=null?objs[1]:""));
			pp.setNomeTecnico((String)(objs[2]!=null?objs[2]:""));
			pp.setWifiAtivo((((String)(objs[3]!=null?objs[3]:""))).equalsIgnoreCase("S"));
			pp.setDisponivel((((String)(objs[4]!=null?objs[4]:""))).equalsIgnoreCase("S"));
			pp.setClassificacao((String)(objs[5]==null?"A":objs[5]));
			if (planos.isEmpty()){

				pp.setRegistroAtual(to.getRegistroAtual());
				pp.setRegistrosPorPagina(to.getRegistrosPorPagina());
				pp.setTotalRegistros(registros.intValue());
			}
			pp.setUFs(recuperarUfs(pp.getIdPlano(),to.getIdPlataforma(), ufsMap));
			planos.add(pp);
		}
		return planos;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public PlanoParametroTO consultarPlano(Integer idPlano) throws DAOException{

		if(idPlano == null){

			throw new DAOException("idPlano nulo"); 
		}
		PlanoParametroTO plano = new PlanoParametroTO();
		StringBuilder sbQuery = new StringBuilder();
		sbQuery.append(" select plano.idPlano, plano.nmComercial, sistemaPlano.cdCodigo, ");
		sbQuery.append(" plano.ativaWifi, plano.inDisponivel, plano.qtdeMaxDependLegado, ");
		sbQuery.append(" plano.inDisponivelLegado, plano.inDisponivel, plataforma.idPlataforma, ");
		sbQuery.append(" plano.dtUltimaAlteracao, plano.nmUsuarioAlteracao, tipoPlano.idTipoPlano, ");
		sbQuery.append(" plano.indTitDep ");
		sbQuery.append(" from Plano plano ");
		sbQuery.append(" left join plano.sistemaPlano sistemaPlano ");
		sbQuery.append(" left join plano.tipoPlano tipoPlano ");
		sbQuery.append(" left join plano.plataforma  plataforma ");
		sbQuery.append(" where plano.idPlano = :idPlano ");
		System.out.println(em.createQuery(sbQuery.toString()).setParameter("idPlano", idPlano).getResultList());
		Object[] result = (Object[]) em.createQuery(sbQuery.toString()).setParameter("idPlano", idPlano).getResultList().get(0);
		if(result != null){

			plano.setIdPlano((Integer)(result[0]==null?0:result[0]));
			plano.setNomeComercial((String)(result[1]==null?"":result[1]));
			plano.setNomeTecnico((String)(result[2]==null?"":result[2]));
			plano.setWifiAtivo(((String)(result[3]==null?"":result[3])).equalsIgnoreCase("S"));
			plano.setDisponivel(((String)(result[4]==null?"":result[4])).equalsIgnoreCase("S"));
			plano.setMaximoDependenteLegado((Integer)(result[5]==null?0:result[5]));
			plano.setDisponivelLegado(((String)(result[6]==null?"":result[6])).equalsIgnoreCase("S"));
			plano.setDisponivel(((String)(result[7]==null?"":result[7])).equalsIgnoreCase("S"));
			plano.setDataUltimaAlteracao((Date)result[9]);
			plano.setNomeUsuarioAlteracao((String)(result[10]==null?"":result[10]));
			plano.setTipoPlano((Integer)(result[11]==null?0:result[11]));
			plano.setClassificacao((String)(result[12]==null?"A":result[12]));
			if(result[8]!=null){

				sbQuery = new StringBuilder();
				sbQuery.append(" select tipoPlano.idTipoPlano, tipoPlano.nmTipoPlano ");
				sbQuery.append(" from TipoPlano tipoPlano ");
				sbQuery.append(" inner join tipoPlano.plataforma plataforma ");
				sbQuery.append(" where plataforma.idPlataforma = :idPlataforma ");
				List<Object[]> results = (List<Object[]>) em.createQuery(sbQuery.toString()).setParameter("idPlataforma", result[8]).getResultList();
				if(results != null){

					plano.setTipoPlanos(new LinkedList());

					for(Object[] obj: results){

						TipoPlanoTO tipoPlano = new TipoPlanoTO();
						tipoPlano.setIdTipoPlano((Integer)obj[0]);
						tipoPlano.setNmTipoPlano((String)obj[1]);
						plano.getTipoPlanos().add(tipoPlano);
					}
				}
			}else {

				sbQuery = new StringBuilder();
				plano.setTipoPlanos(new LinkedList());
				Set<Integer> ids = new HashSet<Integer>();
				sbQuery.append(" select plataforma.idPlataforma ");
				sbQuery.append(" from PlanoPlataforma planoPlataforma ");
				sbQuery.append(" inner join planoPlataforma.plano plano ");
				sbQuery.append(" inner join planoPlataforma.plataforma plataforma ");
				sbQuery.append(" where plano.idPlano = :idPlano ");
				List<Integer> idsPlataforma = (List<Integer>) em.createQuery(sbQuery.toString()).setParameter("idPlano", result[0]).getResultList();
				for(Integer idPlataforma: idsPlataforma){

					sbQuery = new StringBuilder();
					sbQuery.append(" select tipoPlano.idTipoPlano, tipoPlano.nmTipoPlano ");
					sbQuery.append(" from TipoPlano tipoPlano ");
					sbQuery.append(" inner join tipoPlano.plataforma plataforma ");
					sbQuery.append(" where plataforma.idPlataforma = :idPlataforma ");
					List<Object[]> results = (List<Object[]>) em.createQuery(sbQuery.toString()).setParameter("idPlataforma", idPlataforma).getResultList();
					if(results != null){

						for(Object[] obj: results){

							if(!ids.contains((Integer)obj[0])){

								ids.add(idPlataforma);
								TipoPlanoTO tipoPlano = new TipoPlanoTO();
								tipoPlano.setIdTipoPlano((Integer)obj[0]);
								tipoPlano.setNmTipoPlano((String)obj[1]);
								plano.getTipoPlanos().add(tipoPlano);
							}
						}
					}

				}

			}
		}
		return plano;
	}
	public boolean alterarPlano(PlanoParametroTO ppTO){
		
		Plano plano = em.find(Plano.class, ppTO.getIdPlano());
		TipoPlano tp = em.find(TipoPlano.class, ppTO.getTipoPlano());
		plano.setTipoPlano(tp);
		plano.setAtivaWifi(ppTO.getWifiAtivo()?"S":"N");
		plano.setInDisponivel(ppTO.getDisponivel()?"S":"N");
		plano.setDtUltimaAlteracao(ppTO.getDataUltimaAlteracao());
		plano.setNmUsuarioAlteracao(ppTO.getNomeUsuarioAlteracao());
		em.merge(plano);
		return true;
	}
	private Map<Integer,String> recuperarMapUf(){

		@SuppressWarnings("unchecked")
		List<Uf> ufs = em.createNamedQuery("Uf.findAll").getResultList();
		Map<Integer,String> ufsMap = new HashMap<Integer,String>();
		for (Uf uf: ufs){

			ufsMap.put(uf.getIdUf(), uf.getNmUf());
		}
		return ufsMap;
	}
	@SuppressWarnings("unchecked")
	private List<UfTO> recuperarUfs(Integer idPlano, Integer idPlataforma, Map<Integer,String> mapUfs){

		Set<Integer> idUfs = new HashSet<Integer>();
		@SuppressWarnings("rawtypes")
		List<UfTO> ufsTO = new LinkedList(); 
		List<Object> results = null;
		if(mapUfs == null){

			mapUfs = recuperarMapUf();
		}
		if(idPlataforma.intValue() == IDPLATAFORMAPREPAGO){

			StringBuilder sbQuery = new StringBuilder();
			sbQuery.append(" select distinct uf.idUf from Uf uf ");
			sbQuery.append(" inner join uf.regional regional ");
			sbQuery.append(" inner join regional.planoList planoList ");
			sbQuery.append(" where planoList.idPlano = :idPlano");
			results = (List<Object>) em.createQuery(sbQuery.toString()).setParameter("idPlano", idPlano).getResultList();
			for(Object obj: results){
				idUfs.add((Integer)obj);
			}
		}else{
			StringBuilder sbQuery = new StringBuilder();
			sbQuery.append(" select distinct uf.idUf from Uf uf ");
			sbQuery.append(" inner join uf.areaRegistroList areaRegistroList ");
			sbQuery.append(" inner join areaRegistroList.csaList csaList ");
			sbQuery.append(" inner join csaList.planoCSAList planoCSAList ");
			sbQuery.append(" inner join planoCSAList.plano plano ");
			sbQuery.append(" where plano.idPlano = :idPlano ");
			results = (List<Object>) em.createQuery(sbQuery.toString()).setParameter("idPlano", idPlano).getResultList();
			for(Object obj: results){
				idUfs.add((Integer)obj);
			}
			sbQuery = new StringBuilder();
			sbQuery.append(" select distinct csa.nmCSA from CSA csa ");
			sbQuery.append(" inner join csa.planoCSAList planoCSAList ");
			sbQuery.append(" inner join planoCSAList.plano plano ");
			sbQuery.append(" where plano.idPlano = :idPlano ");
			results = (List<Object>) em.createQuery(sbQuery.toString()).setParameter("idPlano", idPlano).getResultList();
			for(Object obj: results){

				for(Map.Entry<Integer, String> entry: mapUfs.entrySet()){

					if(entry.getValue().equals((String)obj)){

						idUfs.add(entry.getKey());
					}
				}
			}
		}
		for(Integer id: idUfs){

			UfTO ufTO = new UfTO(id.longValue());
			ufTO.setNmUf(mapUfs.get(id));
			ufsTO.add(ufTO);
		}
		return ufsTO;
	}

	@SuppressWarnings("unchecked")
	public List<PlanoTO> searchPlanoSegmento(PlanoTO to) throws DAOException {
		List<PlanoTO> result = new ArrayList<PlanoTO>();
		Map<String, Object> params = new HashMap<String, Object>();
		
		
		try {
			StringBuilder queryStr = null;
			
			if(to.getPlataformaTO().getIdPlataforma().intValue() == IDPLATAFORMAPREPAGO){
				queryStr = new StringBuilder(
						"select pl " +
						"from Plano pl " +
						"join pl.plataforma pf " +
						"join pl.sistemaPlano spl " +
						"left join pl.categoria c "
				);	
			  }else{
				   queryStr = new StringBuilder(
						   "select pl " +
						   "from Plano pl " +
						   "join pl.sistemaPlano spl " +
						   "join pl.planoPlataformaList plpf " +
						   "join plpf.plataforma pf " +
						   "left join pl.categoria c "
				);				
			}
			
			if(to.getSegmentoTO() != null){
				switch (to.getSegmentoTO().getTipoPesquisaSegmento()){
					case SEGMENTO:
						queryStr.append(
//							"join pl.segmentoList s "
							"join pl.planoSegmento ps " +	
							"join ps.segmento s " +
							"where pl.inDisponivel = 'S' " +
							"and s.idSegmento = :idSegmento "
						);
						params.put("idSegmento", to.getSegmentoTO().getIdSegmento());
					break;
					case SEGMENTADOS:
						queryStr.append(
//							"join pl.segmentoList s " +
							"join pl.planoSegmento ps " +	
							"join ps.segmento s " +
							"where pl.inDisponivel = 'S' "
						);
					break;
					case NAO_SEGMENTADOS:
						queryStr.append(
//							"left join pl.segmentoList s " +
							"left join pl.planoSegmento ps " +	
							"left join ps.segmento s " +
							"where pl.inDisponivel = 'S' " +
							"and s.idSegmento is null "
						);
					break;
				}
			} else {
				queryStr.append("where pl.inDisponivel = 'S' ");
			}
			if(to.getSegmentoTO() != null && to.getSegmentoTO().getInInfancia() != null){
				queryStr.append(" and ps.inInfancia = :inInfancia ");
				params.put("inInfancia", to.getSegmentoTO().getInInfancia());
			}
			if(to.getPlataformaTO() != null){
				queryStr.append("and pf.idPlataforma = :idPlataforma ");
				params.put("idPlataforma", to.getPlataformaTO().getIdPlataforma());
			}
			if(to.getCategoriaTO() != null){
				queryStr.append(" and c.idCategoria like :idCategoria");
				params.put("idCategoria", to.getCategoriaTO().getIdCategoria());
			}
			if(to.getSgTitularidade() != null){
				queryStr.append(" and pl.indTitDep = :sgTitularidade ");
				params.put("sgTitularidade", to.getSgTitularidade());
			}
			if(to.getCdPlano() != null){
				queryStr.append(" and spl.cdCodigo = :cdCodigo ");
				params.put("cdCodigo", to.getCdPlano());
			}
			if(to.getNmComercial() != null){
				queryStr.append(" and upper(pl.nmComercial) like :nmComercial ");
				params.put("nmComercial", "%"+to.getNmComercial().trim().toUpperCase()+"%");
			}

			Query query = getQuery(queryStr.toString(), params);
			List<Plano> planoList = query.getResultList();
			result = new PlanoTOBuilder().createPlanoSegmentoTOList(planoList);
			
		} catch(Exception e){
			throw new DAOException(e);
		} 	
		return result;
	}

	private Query getQuery(String queryStr, Map<String, Object> params){
		Query query = em.createQuery(queryStr);
		
		for(String param: params.keySet()){
			query.setParameter(param, params.get(param));
		}
		
		return query;
	}
}