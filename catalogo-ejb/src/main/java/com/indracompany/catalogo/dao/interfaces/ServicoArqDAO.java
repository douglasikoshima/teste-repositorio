package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.ServicoArq;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.ServicoArqItemTO;


public interface ServicoArqDAO {

    void merge(ServicoArq servicoArq) throws DAOException;

    List<ImportacaoServicoFixaTO> search(ImportacaoServicoFixaTO to) throws DAOException;

    List<ServicoArqItemTO> searchCritica(Integer idArquivo) throws DAOException;

}
