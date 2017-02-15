package com.indracompany.catalogo.ejb.modelorestricao;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.ModeloTipoProdutoCompativelTO;

/**
 * @author Luiz Pereira
 * 
 * Interface responsável em manter contrato localmente 
 * das funcionalidades de Canal com o EJB.  
 */
@Local
public interface AssociacaoModeloRestricaoBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/AssociacaoModeloRestricaoBean";
	 
	public void createUpdateRestricoes(GrupoProdutoTO grupoProdutoTO, Map<Integer, List<ModeloTipoProdutoCompativelTO>> map, String user) throws BusinessException;
	
}
