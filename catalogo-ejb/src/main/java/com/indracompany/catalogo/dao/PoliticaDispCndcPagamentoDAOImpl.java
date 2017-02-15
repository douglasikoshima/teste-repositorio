package com.indracompany.catalogo.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.indracompany.catalogo.dao.interfaces.PoliticaDispCndcPagamentoDAO;
import com.indracompany.catalogo.datalayer.Encargo;
import com.indracompany.catalogo.datalayer.PoliticaDispCndcPagamento;
import com.indracompany.catalogo.datalayer.Servico;
import com.indracompany.catalogo.datalayer.SolicitacaoComercial;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.PoliticaDispCndcPagamentoTO;

@Stateless
public class PoliticaDispCndcPagamentoDAOImpl implements PoliticaDispCndcPagamentoDAO{
    
    @PersistenceContext
    private EntityManager em;

	public PoliticaDispCndcPagamentoTO findByIdServico(Integer idServico) throws DAOException {
		try {
            Servico s = em.find(Servico.class, idServico);
            return this.searchVarDispFinanciamento(s);
		} catch(Exception e){
			throw new DAOException(e);
		}
	}

	private PoliticaDispCndcPagamentoTO searchVarDispFinanciamento(Servico s) {
        for(SolicitacaoComercial solicitacaoComercial : s.getSistemaServico().getSolicitacaoComercialList()){
            for(Encargo encargo : solicitacaoComercial.getEncargoList()){
                if(!encargo.getCondicaoPagamentoEncargoList().isEmpty()){
                    if(encargo.getCondicaoPagamentoEncargoList().get(0).getPoliticaDispCndcPagamento() == null){
                        return null;
                    } else {
                        return new PoliticaDispCndcPagamentoTOBuilder().createTO(encargo.getCondicaoPagamentoEncargoList().get(0).getPoliticaDispCndcPagamento());
                    }
                }
            }
        }
        return null;
    }

    public PoliticaDispCndcPagamentoTO findById(PoliticaDispCndcPagamentoTO politicaDispCndcPagamentoTO) throws DAOException {
		PoliticaDispCndcPagamentoTOBuilder builder = new PoliticaDispCndcPagamentoTOBuilder();
		PoliticaDispCndcPagamentoTO result = null;
		
		try {
			result = builder.createTO(em.find(PoliticaDispCndcPagamento.class, politicaDispCndcPagamentoTO.getIdPoliticaDispCndcPagamento()));
		} catch(Exception e){
			throw new DAOException(e);
		}
		
		return result;
	} 
}
