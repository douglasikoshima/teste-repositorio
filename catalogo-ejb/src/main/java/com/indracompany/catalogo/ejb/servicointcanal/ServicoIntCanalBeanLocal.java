package com.indracompany.catalogo.ejb.servicointcanal;

import java.util.List;
import javax.ejb.Local;

import com.indracompany.catalogo.datalayer.ServicoIntCanal;
import com.indracompany.catalogo.to.ServicoIntCanalTO;

import br.com.indrasistemas.framework.service.BusinessException;

@Local
public interface ServicoIntCanalBeanLocal {
	public static final String JNDI_NAME = "java:comp/env/ServicoIntCanalBean";

	public List<ServicoIntCanal> findById (Integer idServicoIntCanal) throws BusinessException;
	
	public void createUpdateServicoIntCanal (ServicoIntCanalTO servicoIntCanalTO) throws BusinessException;
	
	public void removeSrvIntCanalById(Integer idServicoInteratividade) throws BusinessException;
	
}
