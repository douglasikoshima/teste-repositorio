package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.indracompany.catalogo.dao.interfaces.SolicitacaoComercialDAO;
import com.indracompany.catalogo.datalayer.CanalVendaSolicitacaoComercial;
import com.indracompany.catalogo.datalayer.DisponibilidadeSlctComercial;
import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CanalVendaSolicitacaoComercialTO;
import com.indracompany.catalogo.to.DisponibilidadeSlctComercialTO;
import com.indracompany.catalogo.to.ServicoSolicitacaoComercialTO;

@Stateless
public class SolicitacaoComercialDAOImpl implements SolicitacaoComercialDAO{

	private static Logger logger = Logger.getLogger(SolicitacaoComercialDAOImpl.class);
	
	@PersistenceContext
	private EntityManager em;
	

	public ServicoSolicitacaoComercialTO findById(ServicoSolicitacaoComercialTO servicoSolicitacaoComercialTO) throws DAOException {
		logger.debug("servicoSolicitacaoComercialTO: " + servicoSolicitacaoComercialTO);
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
			
		try {
			SolicitacaoComercial solicitacaoComercial = em.find(SolicitacaoComercial.class, servicoSolicitacaoComercialTO.getIdSolicitacaoComercial());
			result = new ServicoSolicitacaoComercialTOBuilder().createTO(solicitacaoComercial); 
		} catch (Exception e) {
			throw new DAOException(e);
		}
		
		return result;
	}
	
	@SuppressWarnings("unchecked")
    public List<ServicoSolicitacaoComercialTO> search(ServicoSolicitacaoComercialTO to) throws DAOException {
		logger.debug("servicoSolicitacaoComercialTO: " + to);
		
		StringBuilder queryStr = new StringBuilder();
		List<ServicoSolicitacaoComercialTO> result = new ArrayList<ServicoSolicitacaoComercialTO>(); 
		/*
		try{
			to.getPzMaximoAtendimento().
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
		*/
		try {
			
			queryStr.append(
					"select sc from SolicitacaoComercial sc " +
					"join sc.tipoSolicitacaoComercial tsc " +
					"join sc.sistemaServico ss " +
					"join ss.servico s " +
					"join ss.sistema si " +
					"where s.idServico = :idServico " +
					"and si.idSistema in (:idSistemaList) "
			);
			if(to.getCdSolicitacaoComercial() != null)
				queryStr.append("and sc.cdSolicitacaoComercial = :cdSolicitacaoComercial ");
			if(to.getNmSolicitacaoComercial() != null)
				queryStr.append("and upper(sc.nmSolicitacaoComercial) like upper(:nmSolicitacaoComercial) ");
			if(to.getPzMaximoVigencia() != null)
				queryStr.append("and sc.pzMaximoVigencia <= :pzMaximoVigencia ");
			if(to.getPzMaximoAtendimento() != null)
				queryStr.append("and sc.pzMaximoAtendimento <= :pzMaximoAtendimento ");
			if(to.getInDisponivel() != null)
				queryStr.append("and sc.inDisponivel = :inDisponivel ");
			if(to.getInOfertaClienteInadimplente() != null)
				queryStr.append("and sc.inOfertaClienteInadimplente = :inOfertaClienteInadimplente ");
			if(to.getIdTipoSolicitacaoComercial() != null && to.getIdTipoSolicitacaoComercial() != 0){
				queryStr.append("and tsc.idTipoSolicitacaoComercial = :idTipoSolicitacaoComercial ");
			}

			Query query = em.createQuery(queryStr.toString());
			
			query.setParameter("idServico", to.getIdServico());
			query.setParameter("idSistemaList", to.getIdSistemaList());
			if(to.getCdSolicitacaoComercial() != null)
				query.setParameter("cdSolicitacaoComercial", to.getCdSolicitacaoComercial());
			if(to.getNmSolicitacaoComercial() != null)
				query.setParameter("nmSolicitacaoComercial", "%"+to.getNmSolicitacaoComercial().toUpperCase()+"%");
			if(to.getPzMaximoVigencia() != null)
				query.setParameter("pzMaximoVigencia", to.getPzMaximoVigencia());
			if(to.getPzMaximoAtendimento() != null)
				query.setParameter("pzMaximoAtendimento", to.getPzMaximoAtendimento());
			if(to.getInDisponivel() != null)
				query.setParameter("inDisponivel", to.getInDisponivel());
			if(to.getInOfertaClienteInadimplente() != null)
				query.setParameter("inOfertaClienteInadimplente", to.getInOfertaClienteInadimplente());
			if(to.getIdTipoSolicitacaoComercial() != null && to.getIdTipoSolicitacaoComercial() != 0){
				query.setParameter("idTipoSolicitacaoComercial", to.getIdTipoSolicitacaoComercial());
			}
			result = new ServicoSolicitacaoComercialTOBuilder().createTOList(query.getResultList());
		} catch(Exception e){
			throw new DAOException(e);
		}
		
		return result;
	}
	
	public void updateCascadeDisponibilidadeSlctCmrl(ServicoSolicitacaoComercialTO to) throws DAOException {
		logger.debug("SolicitacaoComercialTO: " + to);

		try {
			SolicitacaoComercial solicitacaoComercial = em.find(SolicitacaoComercial.class, to.getIdSolicitacaoComercial());
			
			solicitacaoComercial.setInDisponivel(to.getInDisponivel());
			for(CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial : solicitacaoComercial.getCanalVendaSolicitacaoComercialList()){
				canalVendaSolicitacaoComercial.setInDisponivel(to.getInDisponivel());
				for(DisponibilidadeSlctComercial disponibilidadeSlctComercial : canalVendaSolicitacaoComercial.getDisponibilidadeSlctComercialList()){
					disponibilidadeSlctComercial.setIdDisponibilidadeSlctComercial(to.getPoliticaDispSlctComercialTO().getIdPoliticaDispSlctComercial());
				}
			}
			
			em.merge(solicitacaoComercial);

		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void updateCascadeDisponibilidadeSlctCmrlCanalVenda(ServicoSolicitacaoComercialTO to) throws DAOException {
		logger.debug("SolicitacaoComercialTO: " + to);

		try {
			SolicitacaoComercial solicitacaoComercial = em.find(SolicitacaoComercial.class, to.getIdSolicitacaoComercial());
			
			//solicitacaoComercial.setInDisponivel(to.getInDisponivel());
			for(CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial : solicitacaoComercial.getCanalVendaSolicitacaoComercialList()){
				canalVendaSolicitacaoComercial.setInDisponivel(to.getInDisponivel());
				for(DisponibilidadeSlctComercial disponibilidadeSlctComercial : canalVendaSolicitacaoComercial.getDisponibilidadeSlctComercialList()){
					disponibilidadeSlctComercial.setIdDisponibilidadeSlctComercial(to.getPoliticaDispSlctComercialTO().getIdPoliticaDispSlctComercial());
				}
			}
			
			em.merge(solicitacaoComercial);

		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	public void updateDisponibilidadePlMinutosAreaConcorrencia(ServicoSolicitacaoComercialTO to) throws DAOException {
		logger.debug("SolicitacaoComercialTO: " + to);

		try {
			SolicitacaoComercial solicitacaoComercial = em.find(SolicitacaoComercial.class, to.getIdSolicitacaoComercial());
			
			//solicitacaoComercial.setInDisponivel(to.getInDisponivel());
			for(CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial : solicitacaoComercial.getCanalVendaSolicitacaoComercialList()){
				//canalVendaSolicitacaoComercial.setInDisponivel(to.getInDisponivel());
				for(DisponibilidadeSlctComercial disponibilidadeSlctComercial : canalVendaSolicitacaoComercial.getDisponibilidadeSlctComercialList()){
					disponibilidadeSlctComercial.setIdDisponibilidadeSlctComercial(to.getPoliticaDispSlctComercialTO().getIdPoliticaDispSlctComercial());
				}
			}
			
			em.merge(solicitacaoComercial);

		} catch (Exception e) {
			throw new DAOException(e);
		}
	}
	
	 
	
	public ServicoSolicitacaoComercialTO findCanalVendaBySolicitacaoComercial(ServicoSolicitacaoComercialTO to) throws DAOException {
		logger.debug("servicoSolicitacaoComercialTO: " + to);
		CanalVendaSolicitacaoComercialTOBuilder canalVendaSolicitacaoComercialTOBuilder = new CanalVendaSolicitacaoComercialTOBuilder();
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
		
		try {
			SolicitacaoComercial solicitacaoComercial = em.find(SolicitacaoComercial.class, to.getIdSolicitacaoComercial());
			result.setCanalVendaSolicitacaoComercialTOList(canalVendaSolicitacaoComercialTOBuilder.createTOList(solicitacaoComercial.getCanalVendaSolicitacaoComercialList()));
			/*
			for(CanalVendaSolicitacaoComercialTO canalVendaSolicitacaoComercialTO : result.getCanalVendaSolicitacaoComercialTOList()){
				if(solicitacaoComercial.getPoliticaDispSlctComercial() == null){
					canalVendaSolicitacaoComercialTO.setInAreaConcorrenciaSolicitacaoComercial("N");
					canalVendaSolicitacaoComercialTO.setInPlanoMinutoSolicitacaoComercial("N");
				} else {
					canalVendaSolicitacaoComercialTO.setInAreaConcorrenciaSolicitacaoComercial(solicitacaoComercial.getPoliticaDispSlctComercial().getInAreaConcorrencia());
					canalVendaSolicitacaoComercialTO.setInPlanoMinutoSolicitacaoComercial(solicitacaoComercial.getPoliticaDispSlctComercial().getInPlanoMinuto());
				}
			}
			*/
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return result;

/*
		StringBuilder queryStr = new StringBuilder();
		ServicoSolicitacaoComercialTO result = null
		
		try {
			
			queryStr.append(
					"select new com.indracompany.catalogo.to.CanalVendaSolicitacaoComercialTO( " +
					"  csc.idCanalVendaSolicitacaoComercial" +
					"  ,new com.indracompany.catalogo.to.CanalVendaTO(" +
					"     c.idCanalVenda, c.cdCanalVenda, c.nmCanalVenda " +
					"	  ,c.dsCanalVenda, c.inDisponivel, c.inCriacaoCatalogo)" +
					"  ,sc.idSolicitacaoComercial " +
					"  ,csc.inDisponivel " +
					"  ,sc.inAreaConcorrenciaSolicitacaoComercial" +
					"from CanalVendaSolicitacaoComercial csc " +
					"join csc.solicitacaoComercial sc " +
					"join sc.canalVenda c " +
					"join politicaDispSlctComercial p" +
					"where sc.idSolicitacaoComercial = :idSolicitacaoComercial"
			);

			Query query = em.createQuery(queryStr.toString());
			
		} catch(Exception e){
			throw new DAOException(e);
		}
		
		return result;		
*/
	}
/*
	public ServicoSolicitacaoComercialTO findBySlctCmrlNotInCanalVendaList(ServicoSolicitacaoComercialTO to) throws DAOException {
		logger.debug("ServicoSolicitacaoComercialTO:" + to);
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
		List<CanalVendaSolicitacaoComercialTO> canalVendaSolicitacaoComercialTOList = to.getCanalVendaSolicitacaoComercialTOList();
		List<Long> idList = new ArrayList<Long>();
		
		idList.add(new Long(0));
		for(CanalVendaSolicitacaoComercialTO canalVendaSolicitacaoComercialTO : canalVendaSolicitacaoComercialTOList){
			idList.add(canalVendaSolicitacaoComercialTO.getCanalVendaTO().getIdCanalVenda());
		}
		
		StringBuffer queryStr = new StringBuffer(
				" select csc from CanalVendaSolicitacaoComercial csc " +
				" join csc.canalVenda c " +
				" where csc.inDisponivel = 'S' "
		);
		
		try {
			if(!canalVendaSolicitacaoComercialTOList.isEmpty()){
				queryStr.append(" and c.idCanalVenda not in (:idList) ");
			}
			queryStr.append(" order by c.nmCanalVenda ");
			
			Query query = em.createQuery(queryStr.toString());
			query.setParameter("idList", idList);
			
			result.setCanalVendaSlctCmrlSelecionavelTOList(new CanalVendaSolicitacaoComercialTOBuilder().createTOList(query.getResultList()));
		} catch(Exception e){
			throw new DAOException(e);
		}
		return result;
	}	
*/
	@SuppressWarnings("unchecked")
    public void createUpdateCanalVendaSlctCmrlList(ServicoSolicitacaoComercialTO to) throws DAOException {
		logger.debug("servicoSolicitacaoComercialTO: " + to);
		StringBuilder queryStr = new StringBuilder();
		List<Long> idCanalVendaList = new ArrayList<Long>();
		
		try {

			idCanalVendaList.add(new Long(0));
			for(CanalVendaSolicitacaoComercialTO canalVendaSolicitacaoComercialTO : to.getCanalVendaSolicitacaoComercialTOList()){
				idCanalVendaList.add(canalVendaSolicitacaoComercialTO.getCanalVendaTO().getIdCanalVenda());
			}
			
			queryStr.append(
					" select csc from CanalVendaSolicitacaoComercial csc " +
					" join csc.solicitacaoComercial sc " +
					" join csc.canalVenda c " +
					" where sc.idSolicitacaoComercial = :idSolicitacaoComercial " +
					" and c.idCanalVenda not in (:idCanalVendaList) "
			);
			
			Query query1 = em.createQuery(queryStr.toString());
			query1.setParameter("idSolicitacaoComercial", to.getIdSolicitacaoComercial());
			query1.setParameter("idCanalVendaList", idCanalVendaList);
			
			List<CanalVendaSolicitacaoComercial> canalVendaSolicitacaoComercialList = (List<CanalVendaSolicitacaoComercial>) query1.getResultList();
			
			for(CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial : canalVendaSolicitacaoComercialList){
				em.remove(canalVendaSolicitacaoComercial);
			}

			for(CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial :  new CanalVendaSolicitacaoComercialTOBuilder().createEntityList(to.getCanalVendaSolicitacaoComercialTOList())){
				canalVendaSolicitacaoComercial.setInCriacaoCatalogo("S");
				em.merge(canalVendaSolicitacaoComercial);
			}

		} catch(Exception e){
			throw new DAOException(e);
		}
	}

	public void createUpdateDispAreaConcPlMin(ServicoSolicitacaoComercialTO to) throws DAOException {
		// TODO Auto-generated method stub
		
	}

	public ServicoSolicitacaoComercialTO findDispSlctCmrlByIdCnVendaSlct(ServicoSolicitacaoComercialTO to) throws DAOException {
		logger.debug("servicoSolicitacaoComercialTO: " + to);
		DisponibilidadeSlctComercialTOBuilder disponibilidadeSlctComercialTOBuilder = new DisponibilidadeSlctComercialTOBuilder();
		ServicoSolicitacaoComercialTO result = new ServicoSolicitacaoComercialTO();
		
		try {
			List<DisponibilidadeSlctComercialTO> disponibilidadeSlctComercialTOList = new ArrayList<DisponibilidadeSlctComercialTO>(); 
			CanalVendaSolicitacaoComercial canalVendaSolicitacaoComercial = em.find(CanalVendaSolicitacaoComercial.class, to.getIdCanalVendaSolicitacaoComercial());
			for(DisponibilidadeSlctComercialTO disponibilidadeSlctComercialTO : disponibilidadeSlctComercialTOBuilder.createTOList(canalVendaSolicitacaoComercial.getDisponibilidadeSlctComercialList())){
				disponibilidadeSlctComercialTOList.add(disponibilidadeSlctComercialTO);
			}
			result.setDisponibilidadeSlctComercialTOList(disponibilidadeSlctComercialTOList);
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return result;	
	}

	public void createUpdate(ServicoSolicitacaoComercialTO to) throws DAOException {
		logger.debug("servicoSolicitacaoComercialTO: " + to);
		
		try {
			SolicitacaoComercial solicitacaoComercial = em.find(SolicitacaoComercial.class, to.getIdSolicitacaoComercial());
			solicitacaoComercial.setInDisponivel(to.getInDisponivel());
			em.merge(solicitacaoComercial);
		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

	public void saveSolicitacaoOfertaClienteInadimplente(List<ServicoSolicitacaoComercialTO> toList) throws DAOException {
		
		try {
			for(ServicoSolicitacaoComercialTO to : toList){
				SolicitacaoComercial solicitacaoComercial = em.find(SolicitacaoComercial.class, to.getIdSolicitacaoComercial());
				solicitacaoComercial.setInOfertaClienteInadimplente(to.getInOfertaClienteInadimplente());
				em.merge(solicitacaoComercial);
			}
		} catch(Exception e){
			throw new DAOException(e);
		}
		
	}
}
