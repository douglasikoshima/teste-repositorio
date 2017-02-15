package com.indracompany.catalogo.ejb.detalheservicofixa;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.DetalheServicoFixaTO;

@Local
public interface DetalheServicoFixaBeanLocal {

	public static final String JNDI_NAME = "java:comp/env/DetalheServicoFixaBean";
	
	public DetalheServicoFixaTO findDetalheServicoFixaById(DetalheServicoFixaTO detalheServicoTO) throws BusinessException;
	
	public void updateDetalheServicoFixaTO(DetalheServicoFixaTO detalheServicoFixaTO) throws BusinessException;	
	
}
