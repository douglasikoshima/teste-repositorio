package com.indracompany.catalogo.ejb.servicointplataforma;

import java.util.List;
import javax.ejb.Local;
import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.ServicoIntPlataforma;
import com.indracompany.catalogo.to.ServicoIntPlataformaTO;


@Local
public interface ServicoIntPlataformaBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/ServicoIntPlataformaBean";
	
	public List<ServicoIntPlataforma> findByIdServicoInteratividade (Integer idServicoInteratividade ) throws BusinessException;
	
	public void createUpdateServicoIntPlataforma (ServicoIntPlataformaTO servicoIntPlataformaTO) throws BusinessException;
	
	public void removeSrvIntPlataformaById (Integer idServicoInteratividade) throws BusinessException;
 
}
