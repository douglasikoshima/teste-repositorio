package com.indracompany.catalogo.ejb.importacaotipomatriz;

import java.io.InputStream;
import java.util.List;

import br.com.indrasistemas.framework.service.BusinessException;



/**
 * @author Luiz Pereira
 */
public interface ImportacaoTipoMatrizContratoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/ImportacaoTipoMatrizContratoBean";
	
	/**
	 * @param inputStream
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em importar os dados de Tipo Matriz Contrato.
	 */
	public Boolean importContratoMatrizOferta(InputStream inputStream, String usuario) throws BusinessException;
	
	
	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em exportar os dados de Matriz Oferta.
	 */
	public List<String> exportContratoMatrizOferta(Boolean trabalho) throws BusinessException;
	
	
	/**
	 * @return
	 * @throws BusinessException
	 * Método responsável em validar todos os registros da tabela espelho e gerar criticas.
	 */
	public Boolean validarDados() throws BusinessException;
	
	
	/**
	 * @return
	 * @throws BusinessException
	 * 
	 * Método responsável em liberar os dados da tabela espelho para a tabela principal.
	 */
	public Boolean liberarProducao() throws BusinessException; 
	
	
	/**
	 * @throws BusinessException
	 * 
	 * Método responsável em remover dados da ultima configuração realizada.
	 */
	public void removerDadosAntigos() throws BusinessException;
}
