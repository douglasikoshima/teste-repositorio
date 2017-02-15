package com.indracompany.catalogo.ejb.tecnologia;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.Tecnologia;
import com.indracompany.catalogo.to.PesquisaIdNomeTO;
import com.indracompany.catalogo.to.TecnologiaTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Tecnologia com o EJB.  
 */
@Local
public interface TecnologiaBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/TecnologiaBean";
	
	/**
	 * @return
	 * 
	 * Método responsável em obter todas as tecnologias
	 */
	public List<TecnologiaTO> findAll() throws BusinessException;
	
	/**
	 * @param pesquisaTO
	 * 
	 * Método responsável em pesquisar tecnologias/tipos de frequência/valores de frequência
	 * dentro do contexto de uma datatable paginada
	 */
	public void searchTecnologia(PesquisaIdNomeTO pesquisaTO) throws BusinessException;
	
	public Tecnologia findById(Integer idTecnologia) throws BusinessException;
	
}