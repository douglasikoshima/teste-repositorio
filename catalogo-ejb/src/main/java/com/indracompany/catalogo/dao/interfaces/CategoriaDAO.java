package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.CategoriaTO;

public interface CategoriaDAO {

    public List<CategoriaTO> search(CategoriaTO categoriaTO) throws DAOException;

    public CategoriaTO insertUpdate(CategoriaTO categoriaTO)throws DAOException;

    public void changeStatus(Integer id) throws DAOException;

    public void remove(Integer id) throws DAOException;

    public CategoriaTO findById(Integer id) throws DAOException;

	public CategoriaTO searchByDescription(String dsCategoria) throws DAOException;

    public Long contarServicoAssociado(Integer idCategoria) throws DAOException;
    
    public List<CategoriaTO> findAllCategoriaPlano() throws DAOException;
    
    public List<CategoriaTO> findAllCategoriaServicoFixa() throws DAOException;
    
    /**
	 *  Metodo utilizado no Catalogo 
	 *  @author Luciano
	 *  @param indCatalogo Indicador utilizado para definir o valor do filtro em CadegoriaSistema.idSistema
	 *  @return List<CategoriaTO> lista de categorias.
	 *  @exception DAOException Erro padrão. 
	 */
    public List<CategoriaTO> listarGrupoServico(Integer indCatalogo) throws DAOException;
    
    /**
	 *  Metodo utilizado no Catalogo 
	 *  @author Luciano
	 *  @param catalogo Objeto a ser Atualizado ou Inserido na base.
	 *  @param indCatalogo usado em caso de insert para definir qual Sistema estará vinculado (CategoriaSistema)
	 *  @return CategoriaTO objeto atualizado após a insersão/atualização.
	 *  @exception DAOException Erro padrão. 
	 */
    public CategoriaTO mergeCatalogo(CategoriaTO catalogo,Integer indCatalogo) throws DAOException;
    
    /**
	 *  Metodo utilizado no Catalogo 
	 *  @author Luciano
	 *  @param id 
	 *  @exception DAOException Erro padrão. 
	 */
    public void removeCatalogoMovel(Integer id) throws DAOException;
}