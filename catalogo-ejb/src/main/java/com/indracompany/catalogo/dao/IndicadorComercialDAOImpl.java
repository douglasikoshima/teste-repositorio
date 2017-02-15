package com.indracompany.catalogo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.indracompany.catalogo.dao.interfaces.IndicadorComercialDAO;
import com.indracompany.catalogo.datalayer.FiltroDocumento;
import com.indracompany.catalogo.datalayer.IndicadorComercial;
import com.indracompany.catalogo.datalayer.TipoDocumento;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.IndicadorComercialDocumentoTO;
import com.indracompany.catalogo.to.TipoDocumentoTO;

@Stateless
public class IndicadorComercialDAOImpl implements IndicadorComercialDAO {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public List<IndicadorComercialDocumentoTO> findAllIndicadoresDocumento() throws DAOException {
		List<IndicadorComercialDocumentoTO> result = new ArrayList<IndicadorComercialDocumentoTO>();
		try {
			for(IndicadorComercial indicadorComercial: (List<IndicadorComercial>) em.createNamedQuery("IndicadorComercial.findAll").getResultList()){
				IndicadorComercialDocumentoTO indicadorComercialDocumentoTO = new IndicadorComercialDocumentoTO(
					indicadorComercial.getIdIndicadorComercial(),
					indicadorComercial.getNmIndicadorComercial()
				);
				result.add(indicadorComercialDocumentoTO);
			}
			
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return result;
	}
	
	public List<TipoDocumentoTO> findDocumentosIndicador(IndicadorComercialDocumentoTO to) throws DAOException {
		List<TipoDocumentoTO> result = null;
		try {
			result = new TipoDocumentoTOBuilder().createTOList((List<TipoDocumento>) em.find(IndicadorComercial.class, to.getIdIndicadorComercial()).getTipoDocumentoList());  
		} catch (Exception e) {
			throw new DAOException(e);
		}
		return result;
	}
	
	public void save(IndicadorComercialDocumentoTO to) throws DAOException {
		try {
			/*IndicadorComercial indicadorComercial = em.find(IndicadorComercial.class, to.getIdIndicadorComercial());
			indicadorComercial.getTipoDocumentoList().removeAll(indicadorComercial.getTipoDocumentoList());
			indicadorComercial.setTipoDocumentoList(new TipoDocumentoTOBuilder().createEntityList(to.getTipoDocumentoTOList()));
			em.persist(indicadorComercial);
			*/
			List<FiltroDocumento> filtroDocumentoList = em.find(IndicadorComercial.class, to.getIdIndicadorComercial()).getFiltroDocumentoList();
			for(FiltroDocumento filtroDocumento: filtroDocumentoList){
				em.remove(filtroDocumento);
			}
			em.flush();
			if(to.getTipoDocumentoTOList() !=null){
				for(TipoDocumentoTO tipoDocumentoTO: to.getTipoDocumentoTOList()){
					FiltroDocumento filtroDocumento = new FiltroDocumento();
					filtroDocumento.setIndicadorComercial(new IndicadorComercial(to.getIdIndicadorComercial()));
					filtroDocumento.setTipoDocumento(new TipoDocumento(tipoDocumentoTO.getIdTipoDocumento()));
					em.persist(filtroDocumento);
				}
			}

		} catch (Exception e) {
			throw new DAOException(e);
		}
	}

}