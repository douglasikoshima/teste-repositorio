package com.indracompany.catalogo.ejb.acao;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.AcaoTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Ações com o EJB.  
 */
@Local
public interface AcaoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/AcaoBean";
	
	/**
	 * @param acaoTO
	 * @return
	 * 
	 * Método responsável em pesquisar uma Ação.
	 * @throws BusinessException 
	 */
	public List<AcaoTO> searchAcao(AcaoTO acaoTO) throws BusinessException;
	
	/**
	 * @param acaoTO
	 * 
	 * Método responsável em criar ou editar uma Ação.
	 * @throws BusinessException 
	 */
	public void createUpdateAcao(AcaoTO acaoTO) throws BusinessException;
	
	/**
	 * @param acaoTO
	 * @return
	 * 
	 * Método responsável em retornar somente uma Ação.
	 */
	public AcaoTO findById(AcaoTO acaoTO) throws BusinessException;
	
	/**
	 * @param acaoTO
	 * @throws BusinessException
	 * 
	 * Método responsável em remover uma ação a partir de um ID
	 */
	public void removeAcao(AcaoTO acaoTO) throws BusinessException;
	
	/**
	 * @param idAcao
	 * @param idsProduto
	 * @throws BusinessException
	 */
	public void createAssociacaoAcaoProduto(AcaoTO acaoTO, String[] idsProduto) throws BusinessException;

	/**
	 * @param idAcao
	 * @param idsProduto
	 * @throws BusinessException
	 */
	public void removeAssociacaoAcaoProduto(AcaoTO acaoTO, String[] idsProduto) throws BusinessException;
}
