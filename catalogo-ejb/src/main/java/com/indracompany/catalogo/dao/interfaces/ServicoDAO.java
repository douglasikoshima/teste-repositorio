package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;
import com.indracompany.catalogo.to.ServicoConfiguracaoScoreTO;

/**
 * @author Luiz Pereira
 *
 */
public interface ServicoDAO {
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 * @throws DAOException
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchServicoAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException;
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 * @throws DAOException
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchServicoConfigAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException;
	
	public void updateValueServicoTO(List<ServicoConfiguracaoScoreTO> servicoConfiguracaoScoreTOList) throws DAOException;
	
	public List<PlanoServicoUfRestricaoTO> searchServicoUfRestricao(PlanoServicoUfRestricaoTO planoServicoUfRestricaoTO) throws DAOException;
	
}
