package com.indracompany.catalogo.ejb.servicointtecnologia;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.datalayer.ServicoIntTecnologia;
import com.indracompany.catalogo.to.ServicoIntTecnologiaTO;


@Local
public interface ServicoIntTecnologiaBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/ServicoIntTecnologiaBean";
	
	public List<ServicoIntTecnologia> findByIdServicoInteratividade (Integer idServicoInteratividade) throws BusinessException;
	
	public void createUpdateServicoIntTecnologia (ServicoIntTecnologiaTO servicoIntTecnologiaTO) throws BusinessException;
	
	public void removeServIntTecnologiaById (Integer idServicoInteratividade) throws BusinessException;
	
}
