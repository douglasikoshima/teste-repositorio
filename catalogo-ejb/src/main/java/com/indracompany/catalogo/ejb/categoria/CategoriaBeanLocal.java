package com.indracompany.catalogo.ejb.categoria;

import java.util.List;

import javax.ejb.Local;


import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.CategoriaTO;

@Local
public interface CategoriaBeanLocal {

 
    public static final String JNDI_NAME = "java:comp/env/CategoriaBean";
    
    public List<CategoriaTO> search(CategoriaTO categoriaTO) throws BusinessException;

    public CategoriaTO insertUpdate(CategoriaTO categoriaTO) throws BusinessException;

    public void changeStatus(Integer id) throws BusinessException;

    public void remove(Integer id) throws BusinessException;

    public CategoriaTO findById(Integer id) throws BusinessException;

	public CategoriaTO searchByDescription(String dsCategoria) throws BusinessException;

    public Long contarServicoAssociado(Integer idCategoria) throws BusinessException;
    
    public List<CategoriaTO> listarGrupoServico(Integer indCatalogo) throws BusinessException;
    
    public CategoriaTO mergeCatalogo(CategoriaTO catalogoTO, Integer indCatalogo )throws BusinessException;
    
    public void removeCatalogoMovel(Integer id) throws BusinessException;
}
