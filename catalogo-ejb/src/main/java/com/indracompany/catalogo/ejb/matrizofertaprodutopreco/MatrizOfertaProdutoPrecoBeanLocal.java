package com.indracompany.catalogo.ejb.matrizofertaprodutopreco;

import java.util.List;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.AcaoTO;
import com.indracompany.catalogo.to.CanalAtendimentoTO;
import com.indracompany.catalogo.to.CanalTO;
import com.indracompany.catalogo.to.MatrizOfertaProdutoPrecoTO;
import com.indracompany.catalogo.to.OfertaSAPTO;
import com.indracompany.catalogo.to.OrganizacaoVendaTO;

public interface MatrizOfertaProdutoPrecoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/MatrizOfertaProdutoPrecoBean";
	
	public List<MatrizOfertaProdutoPrecoTO> search(MatrizOfertaProdutoPrecoTO matrizOfertaProdutoPrecoTO) throws BusinessException;
	
	public List<AcaoTO> findAllAcaoTO() throws BusinessException;
	
	public List<CanalTO> findAllCanalTO() throws BusinessException;
	
	public List<CanalAtendimentoTO> findAllCanalAtendimentoTO() throws BusinessException;
	
	public List<OfertaSAPTO> findAllOfertaSAPTO() throws BusinessException;
	
	public List<OrganizacaoVendaTO> findAllOrganizacaoVendaTO() throws BusinessException;
	
	public void removePrecoList(List<Long> idList, String userName) throws BusinessException;
}
