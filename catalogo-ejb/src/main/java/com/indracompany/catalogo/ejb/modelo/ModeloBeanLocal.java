package com.indracompany.catalogo.ejb.modelo;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.FabricanteTO;
import com.indracompany.catalogo.to.GrupoProdutoTO;
import com.indracompany.catalogo.to.PesquisaGrupoProdutoTO;

@Local
public interface ModeloBeanLocal {
	
	public static final String JNDI_NAME = "java:comp/env/ModeloBean";
	
	@SuppressWarnings("rawtypes")
	public Map<String, List> loadStaticFormLists() throws BusinessException;
	
	public List<GrupoProdutoTO> searchModelo(GrupoProdutoTO grupoProdutoTO) throws BusinessException; 
	
	public void searchModelo(PesquisaGrupoProdutoTO pesquisaTO) throws BusinessException;
	
	public void saveModelo(GrupoProdutoTO grupoProdutoTO) throws BusinessException;
	
	public void updateModelo(GrupoProdutoTO grupoProdutoTO) throws BusinessException;
	
	public List<FabricanteTO> findByTipoProduto(GrupoProdutoTO grupoProdutoTO) throws BusinessException;
	
	public void removeModelo(GrupoProdutoTO grupoProdutoTO) throws BusinessException;
	
}