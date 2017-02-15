package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategorizacaoAnaliseCreditoTO;
import com.indracompany.catalogo.to.PlanoConfiguracaoScoreTO;
import com.indracompany.catalogo.to.PlanoParametroTO;
import com.indracompany.catalogo.to.PlanoSegmentoTO;
import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;
import com.indracompany.catalogo.to.PlanoTO;

/**
 * @author Luiz Pereira
 *
 */
public interface PlanoDAO {
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 * @throws DAOException
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchPlanoAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException;
	
	/**
	 * @param categorizacaoAnaliseCreditoTO
	 * @return
	 * @throws DAOException
	 */
	public List<CategorizacaoAnaliseCreditoTO> searchPlanoConfigAnaliseCredito(CategorizacaoAnaliseCreditoTO categorizacaoAnaliseCreditoTO) throws DAOException;
	
	public void updateValuePlanoTO(List<PlanoConfiguracaoScoreTO> planoConfiguracaoScoreTOList) throws DAOException;
	
	public List<PlanoServicoUfRestricaoTO> searchPlanoUfRestricao(PlanoServicoUfRestricaoTO planoServicoUfRestricaoTO) throws DAOException;
	
	public List<PlanoTO> search(PlanoTO to) throws DAOException;
	
	public List<PlanoTO> searchPlanoSegmento(PlanoTO to) throws DAOException;
	
	public void savePlanoSegmento(PlanoSegmentoTO to) throws DAOException;
	
	public void disassociatePlanoSegmento(PlanoSegmentoTO to) throws DAOException;
	
	public List<PlanoParametroTO> searchPlanoParametro(PlanoParametroTO to)throws DAOException;
	
	public PlanoParametroTO consultarPlano(Integer idPlano) throws DAOException;
	
	public boolean alterarPlano(PlanoParametroTO ppTO);
}