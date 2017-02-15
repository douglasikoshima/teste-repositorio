package com.indracompany.catalogo.ejb.planoservicoufrestricao;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.PlanoServicoUfRestricaoTO;
import com.indracompany.catalogo.to.UfTO;

@Local
public interface PlanoServicoUfRestricaoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/PlanoServicoUfRestricaoBean";

	public List<PlanoServicoUfRestricaoTO> searchPlano(PlanoServicoUfRestricaoTO to) throws BusinessException;
	
	public List<PlanoServicoUfRestricaoTO> searchServico(PlanoServicoUfRestricaoTO to) throws BusinessException;
	
	public List<UfTO> findAllUf() throws BusinessException;
	
	public void configurarRestricaoPlano(List<PlanoServicoUfRestricaoTO> toList) throws BusinessException;
	
	public void configurarRestricaoServico(List<PlanoServicoUfRestricaoTO> toList) throws BusinessException;

}
