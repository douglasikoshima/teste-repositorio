package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.GerenciadorRegraDAO;
import com.indracompany.catalogo.datalayer.CanalAtendimento;
import com.indracompany.catalogo.datalayer.GerencRegra;
import com.indracompany.catalogo.datalayer.IndicadorComercial;
import com.indracompany.catalogo.datalayer.RegraPrioridadeAlta;
import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GerenciadorRegrasConfiguracaoTO;
import com.indracompany.catalogo.to.IndicadorComercialRegraPrioridadeTO;
import com.indracompany.catalogo.to.RegraPrioridadeAltaTO;

@Stateless
public class GerenciadorRegraDAOImpl implements GerenciadorRegraDAO {

	private static Logger logger = Logger
			.getLogger(GerenciadorRegraDAOImpl.class);

	private static final String BLANK = "";

	private static final Long ZERO = new Long(0);

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<String> buscarNmIndicadorComercial(Long inTipoFiltro)
			throws DAOException {
		List<String> result = new ArrayList<String>();

		try {
			result = em.createQuery(
					"select ic.nmIndicadorComercial from IndicadorComercial ic "
							+ "where ic.inTipoFiltro = :inTipoFiltro "
							+ "order by ic.nmIndicadorComercial ")
					.setParameter("inTipoFiltro", inTipoFiltro).getResultList();
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public void salvar(GerenciadorRegrasConfiguracaoTO to) throws DAOException {
		logger.debug(to.toString());

		try {
			StringBuilder queryStr = new StringBuilder(
					"select gr from GerencRegra gr "
							+ "join gr.servico s "
							+ "join gr.indicadorComercial ic "
							+ "join s.tipoServico ts "
							+ "join gr.canalAtendimento ca "
							+ "where ts.inFixa = 'S' "
							+ "and ts.cdTipoServico = 1 "
							+ "and s.idServico = :idServico "
							+ "and ic.inTipoFiltro = :inTipoFiltro "
							+ "and ca.idCanalAtendimento = :idCanalAtendimento ");
			List<GerencRegra> gerencRegraList = em.createQuery(
					queryStr.toString()).setParameter("idServico",
					to.getIdServico()).setParameter("inTipoFiltro",
					to.getInTipoFiltro()).setParameter("idCanalAtendimento",
					to.getCanalAtendimentoTO().getIdCanalAtendimento())
					.getResultList();
			for (GerencRegra gerencRegra : gerencRegraList) {
				em.remove(gerencRegra);
			}
			em.flush();

			if (to.getIndicadorComercialRegraPrioridadeTOMap() != null) {
				CanalAtendimento canalAtendimento = em.find(
						CanalAtendimento.class, to.getCanalAtendimentoTO()
								.getIdCanalAtendimento());
				Servico servico = em.find(Servico.class, to.getIdServico());
				for (String idIndicadorComercialKey : to
						.getIndicadorComercialRegraPrioridadeTOMap().keySet()) {
					RegraPrioridadeAltaTO regraPrioridadeAltaTO = to
							.getIndicadorComercialRegraPrioridadeTOMap().get(
									idIndicadorComercialKey)
							.getRegraPrioridadeAltaTO();
					if (regraPrioridadeAltaTO != null
							&& !regraPrioridadeAltaTO
									.getIdRegraPrioridadeAlta().equals(ZERO)) {
						GerencRegra gerencRegra = new GerencRegra();
						gerencRegra.setCanalAtendimento(canalAtendimento);
						gerencRegra.setServico(servico);
						gerencRegra.setIndicadorComercial(em.find(
								IndicadorComercial.class,
								to.getIndicadorComercialRegraPrioridadeTOMap()
										.get(idIndicadorComercialKey)
										.getIdIndicadorComercial()));
						gerencRegra.setRegraPrioridadeAlta(em.find(
								RegraPrioridadeAlta.class,
								to.getIndicadorComercialRegraPrioridadeTOMap()
										.get(idIndicadorComercialKey)
										.getRegraPrioridadeAltaTO()
										.getIdRegraPrioridadeAlta()));
						em.persist(gerencRegra);
					}
				}
			}

		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public List<GerenciadorRegrasConfiguracaoTO> pesquisar(
			GerenciadorRegrasConfiguracaoTO to) throws DAOException {
		logger.debug(to.toString());
		List<GerenciadorRegrasConfiguracaoTO> result = new ArrayList<GerenciadorRegrasConfiguracaoTO>();

		try {
			StringBuilder queryStr = new StringBuilder(
					"select gr from GerencRegra gr " + "join gr.servico s "
							+ "join s.tipoServico ts "
							+ "join gr.canalAtendimento ca "
							+ "join gr.regraPrioridadeAlta rpa "
							+ "join gr.indicadorComercial ic "
							+ "where ts.inFixa = 'S' "
							+ "and ts.cdTipoServico = 1 ");
			if (to.getIdServico() != null && !to.getIdServico().equals(ZERO)) {
				queryStr.append("and s.idServico = :idServico ");
			}
			if (to.getNmServico() != null && !to.getNmServico().equals(BLANK)) {
				queryStr.append("and s.nmServico like :nmServico ");
			}
			if (to.getCanalAtendimentoTO() != null
					&& to.getCanalAtendimentoTO().getIdCanalAtendimento() != null
					&& !to.getCanalAtendimentoTO().getIdCanalAtendimento()
							.equals(ZERO)) {
				queryStr
						.append("and ca.idCanalAtendimento :idCanalAtendimento ");
			}
			/*
			 * if (to.getIndicadorComercialRegraPrioridadeTOSet() != null &&
			 * !to.getIndicadorComercialRegraPrioridadeTOSet() .isEmpty()) {
			 * queryStr .append("and ic.idIndicadorComercial in
			 * (:indicadorComercialRegraPrioridadeTOSet) "); }
			 */
			Query query = em.createQuery(queryStr.toString());

			if (to.getIdServico() != null && !to.getIdServico().equals(ZERO)) {
				query.setParameter("idServico", to.getIdServico());
			}
			if (to.getNmServico() != null && !to.getNmServico().equals(BLANK)) {
				query.setParameter("nmServico", "%" + to.getNmServico() + "%");
			}
			if (to.getCanalAtendimentoTO() != null
					&& to.getCanalAtendimentoTO().getIdCanalAtendimento() != null
					&& !to.getCanalAtendimentoTO().getIdCanalAtendimento()
							.equals(ZERO)) {
				query.setParameter("idCanalAtendimento", to
						.getCanalAtendimentoTO().getIdCanalAtendimento());
			}
			/*
			 * if (to.getIndicadorComercialRegraPrioridadeTOSet() != null &&
			 * !to.getIndicadorComercialRegraPrioridadeTOSet() .isEmpty()) {
			 * query.setParameter("indicadorComercialRegraPrioridadeTOSet", to
			 * .getIndicadorComercialRegraPrioridadeTOSet()); }
			 */
		} catch (Exception e) {
			throw new DAOException(e);
		}

		return result;
	}

	@SuppressWarnings("unchecked")
	public List<GerenciadorRegrasConfiguracaoTO> pesquisarPorIdCanalAtendimento(
			GerenciadorRegrasConfiguracaoTO to) throws DAOException {
		logger.debug(to.toString());
		List<GerenciadorRegrasConfiguracaoTO> result = new ArrayList<GerenciadorRegrasConfiguracaoTO>();

		try {
			StringBuilder queryStr = new StringBuilder(
					"select distinct "
							+ "new com.indracompany.catalogo.to.GerenciadorRegrasConfiguracaoTO( "
							+ "ca.idCanalAtendimento "
							+ ",s.idServico "
							+ ",s.nmComercial) "
							+ "from GerencRegra gr "
							+ "join gr.servico s "
							+ "join s.tipoServico ts "
							+ "join gr.canalAtendimento ca "
							+ "join gr.indicadorComercial ic "
							+ "where ts.inFixa = 'S' "
							+ "and ts.cdTipoServico = 1 "
							+ "and ic.inTipoFiltro = :inTipoFiltro "
							+ "and ca.idCanalAtendimento = :idCanalAtendimento ");

			result = em.createQuery(queryStr.toString()).setParameter(
					"inTipoFiltro", to.getInTipoFiltro()).setParameter(
					"idCanalAtendimento",
					to.getCanalAtendimentoTO().getIdCanalAtendimento())
					.getResultList();

			for (GerenciadorRegrasConfiguracaoTO gerenciadorRegrasConfiguracaoTO : result) {
				/*
				 * gerenciadorRegrasConfiguracaoTO.setIndicadorComercialRegraPrioridadeTOSet(
				 * createDominioIndicadorComercialRegraPrioridadeTOSet(
				 * gerenciadorRegrasConfiguracaoTO.getIdServico(),
				 * gerenciadorRegrasConfiguracaoTO.getCanalAtendimentoTO().getIdCanalAtendimento(),
				 * to.getInTipoFiltro()));
				 * result.add(gerenciadorRegrasConfiguracaoTO);
				 */
				gerenciadorRegrasConfiguracaoTO
						.setIndicadorComercialRegraPrioridadeTOMap(createDominioIndicadorComercialRegraPrioridadeTOMap(
								gerenciadorRegrasConfiguracaoTO.getIdServico(),
								gerenciadorRegrasConfiguracaoTO
										.getCanalAtendimentoTO()
										.getIdCanalAtendimento(), to
										.getInTipoFiltro()));
			}

		} catch (Exception e) {
			throw new DAOException(e);
		}

		return result;
	}

	/*
	 * private Set<IndicadorComercialRegraPrioridadeTO>
	 * createDominioIndicadorComercialRegraPrioridadeTOSet( Integer idServico,
	 * Integer idCanalAtendimento, Long inTipoFiltro) { Set<IndicadorComercialRegraPrioridadeTO>
	 * result = new HashSet<IndicadorComercialRegraPrioridadeTO>();
	 * IndicadorComercialRegraPrioridadeTOBuilder indicadorBuilder = new
	 * IndicadorComercialRegraPrioridadeTOBuilder();
	 * 
	 * List<IndicadorComercial> indicadorComercialList = em.createQuery(
	 * "select ic from IndicadorComercial ic " + "where ic.inTipoFiltro =
	 * :inTipoFiltro " + "order by ic.nmIndicadorComercial ")
	 * .setParameter("inTipoFiltro", inTipoFiltro) .getResultList();
	 * 
	 * for (IndicadorComercial indicadorComercial : indicadorComercialList) {
	 * List<GerencRegra> gerenciadorRegraList = em .createQuery( "select gr
	 * from GerencRegra gr " + "join gr.servico s " + "join s.tipoServico ts " +
	 * "join gr.canalAtendimento ca " + "join gr.indicadorComercial ic " +
	 * "where ts.inFixa = 'S' " + "and s.idServico = :idServico " + "and
	 * ca.idCanalAtendimento = :idCanalAtendimento " + "and
	 * ic.idIndicadorComercial = :idIndicadorComercial ")
	 * .setParameter("idIndicadorComercial",
	 * indicadorComercial.getIdIndicadorComercial()) .setParameter("idServico",
	 * idServico).setParameter( "idCanalAtendimento", idCanalAtendimento)
	 * .getResultList(); IndicadorComercialRegraPrioridadeTO
	 * indicadorComercialRegraPrioridadeTO = new
	 * IndicadorComercialRegraPrioridadeTO(); if
	 * (gerenciadorRegraList.isEmpty()) { indicadorComercialRegraPrioridadeTO
	 * .setIdIndicadorComercial(indicadorComercial .getIdIndicadorComercial());
	 * indicadorComercialRegraPrioridadeTO
	 * .setNmIndicadorComercial(indicadorComercial .getNmIndicadorComercial());
	 * indicadorComercialRegraPrioridadeTO
	 * .setInTipoFiltro(indicadorComercial.getInTipoFiltro());
	 * indicadorComercialRegraPrioridadeTO .setRegraPrioridadeAltaTO(null); }
	 * else { indicadorComercialRegraPrioridadeTO = indicadorBuilder
	 * .createIndicadorComercialRegraPrioridadeTO(gerenciadorRegraList .get(0)); }
	 * result.add(indicadorComercialRegraPrioridadeTO); }
	 * 
	 * return result; }
	 */
	@SuppressWarnings("unchecked")
	private Map<String, IndicadorComercialRegraPrioridadeTO> createDominioIndicadorComercialRegraPrioridadeTOMap(
			Integer idServico, Integer idCanalAtendimento, Long inTipoFiltro) {
		Map<String, IndicadorComercialRegraPrioridadeTO> result = new HashMap<String, IndicadorComercialRegraPrioridadeTO>();
		IndicadorComercialRegraPrioridadeTOBuilder indicadorBuilder = new IndicadorComercialRegraPrioridadeTOBuilder();

		List<IndicadorComercial> indicadorComercialList = em.createQuery(
				"select ic from IndicadorComercial ic "
						+ "where ic.inTipoFiltro = :inTipoFiltro "
						+ "order by ic.nmIndicadorComercial ").setParameter(
				"inTipoFiltro", inTipoFiltro).getResultList();

		for (IndicadorComercial indicadorComercial : indicadorComercialList) {
			List<GerencRegra> gerenciadorRegraList = em
					.createQuery(
							"select gr from GerencRegra gr "
									+ "join gr.servico s "
									+ "join s.tipoServico ts "
									+ "join gr.canalAtendimento ca "
									+ "join gr.indicadorComercial ic "
									+ "where ts.inFixa = 'S' "
									+ "and s.idServico = :idServico "
									+ "and ca.idCanalAtendimento = :idCanalAtendimento "
									+ "and ic.idIndicadorComercial = :idIndicadorComercial ")
					.setParameter("idIndicadorComercial",
							indicadorComercial.getIdIndicadorComercial())
					.setParameter("idServico", idServico).setParameter(
							"idCanalAtendimento", idCanalAtendimento)
					.getResultList();
			IndicadorComercialRegraPrioridadeTO indicadorComercialRegraPrioridadeTO = new IndicadorComercialRegraPrioridadeTO();
			if (gerenciadorRegraList.isEmpty()) {
				indicadorComercialRegraPrioridadeTO
						.setIdIndicadorComercial(indicadorComercial
								.getIdIndicadorComercial());
				indicadorComercialRegraPrioridadeTO
						.setNmIndicadorComercial(indicadorComercial
								.getNmIndicadorComercial());
				indicadorComercialRegraPrioridadeTO
						.setInTipoFiltro(indicadorComercial.getInTipoFiltro());
				indicadorComercialRegraPrioridadeTO
						.setRegraPrioridadeAltaTO(null);
			} else {
				indicadorComercialRegraPrioridadeTO = indicadorBuilder
						.createIndicadorComercialRegraPrioridadeTO(gerenciadorRegraList
								.get(0));
			}
			result.put(indicadorComercial.getNmIndicadorComercial(),
					indicadorComercialRegraPrioridadeTO);
		}

		return result;
	}

//	private List<String> createDominioNmIndicadorComercial(Long inTipoFiltro) {
//		List<String> result = new ArrayList<String>();
//
//		List<String> indicadorComercialList = em.createQuery(
//				"select ic.nmIndicadorComercial from IndicadorComercial ic "
//						+ "where ic.inTipoFiltro = :inTipoFiltro "
//						+ "order by ic.nmIndicadorComercial ").setParameter(
//				"inTipoFiltro", inTipoFiltro).getResultList();
//
//		return result;
//	}
}
