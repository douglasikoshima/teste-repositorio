package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.OfertafixaCreditoScore;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AnaliseCreditoLinhaTO;
import com.indracompany.catalogo.to.AnaliseCreditoTO;

public interface AnaliseCreditoLinhaDAO {

    public List<AnaliseCreditoLinhaTO> search(AnaliseCreditoLinhaTO analiseCreditoLinhaTO, List<AnaliseCreditoTO> analiseCreditoTOListScore) throws DAOException;

    public List<AnaliseCreditoLinhaTO> loadServicoLinha() throws DAOException;

    public List<AnaliseCreditoTO> loadScore() throws DAOException;

    public void gravar(AnaliseCreditoLinhaTO ocs) throws DAOException ;
    
    public void excluir( OfertafixaCreditoScore ocs) throws DAOException;
    
    public OfertafixaCreditoScore find(Integer idOfertaFixa, Integer idAnaliseCredito);
    
    

}
