package com.indracompany.catalogo.ejb.caracteristica;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.CaracteristicaTO;
import com.indracompany.catalogo.to.PesquisaIdNomeTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Caracteristica com o EJB.  
 */
@Local
public interface CaracteristicaBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/CaracteristicaBean";
	
	/**
	 * @param caracteristicaTO
	 * @return
	 * 
	 * Método responsável em pesquisar uma v.
	 * @throws BusinessException 
	 */
	public List<CaracteristicaTO> searchCaracteristica(CaracteristicaTO caracteristicaTO) throws BusinessException;
	
	/**
	 * @param caracteristicaTO
	 * 
	 * Método responsável em criar ou editar uma Caracteristica.
	 * @throws BusinessException 
	 */
	public CaracteristicaTO createUpdateCaracteristica(CaracteristicaTO caracteristicaTO) throws BusinessException;
	
	/**
	 * @param caracteristicaTO
	 * @return
	 * 
	 * Método responsável em retornar somente uma Caracteristica.
	 */
	public CaracteristicaTO findById(CaracteristicaTO caracteristicaTO) throws BusinessException;
	
	
	/**
	 * @param caracteristicaTO
	 * @throws BusinessException
	 * 
	 * Método responsável em remover uma Caracteristica a partir de um ID
	 */
	public void removeCaracteristica(CaracteristicaTO caracteristicaTO) throws BusinessException;
	
	/**
	 * 
	 * @param pesquisaTO
	 * @throws BusinessException
	 * 
	 * Método responsável em pesquisar características para uma DataTable
	 */
	public void searchCaracteristica(PesquisaIdNomeTO pesquisaTO) throws BusinessException;
	
}
