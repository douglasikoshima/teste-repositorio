package com.indracompany.catalogo.ejb.servicointcliente;

import java.util.List;
import javax.ejb.Local;
import br.com.indrasistemas.framework.service.BusinessException;
import com.indracompany.catalogo.datalayer.ServicoIntCliente;
import com.indracompany.catalogo.to.ServicoIntClienteTO;


@Local
public interface ServicoIntClienteBeanLocal {	
	
	public static final String JNDI_NAME = "java:comp/env/ServicoIntClienteBean";
	
	public List<ServicoIntCliente> findByIdServicoInteratividade(Integer idServicoInteratividade) throws BusinessException;

	public void createUpdateServicoIntCliente(ServicoIntClienteTO servicoIntClienteTO) throws BusinessException;
	
	public void removeServIntClienteById (Integer idServicoInteratividade) throws BusinessException;
}
