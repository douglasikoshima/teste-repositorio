package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.OfServicoConfiguracaoScoreTO;

/**
 * @author Luiz Pereira
 *
 */
public interface OfertaServicoDAO {
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 * @throws DAOException
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchOfertaServicoAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException;
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 * @throws DAOException
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchOfertaServicoConfigAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException;
	
	
	public void updateValueOfertaServicoTO( List<OfServicoConfiguracaoScoreTO> ofServicoConfiguracaoTOList ) throws DAOException;
}
