package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.OfertaVLArq;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ImportacaoServicoFixaTO;
import com.indracompany.catalogo.to.OfertaVLArqItemTO;
import com.indracompany.catalogo.to.OfertaVLFArqItemTO;

public interface OfertaVLArqDAO {
	
	void merge(OfertaVLArq servicoArq) throws DAOException;
	
    List<ImportacaoServicoFixaTO> search(ImportacaoServicoFixaTO to, String tpImportacao) throws DAOException;

    List<OfertaVLArqItemTO> searchCriticaOferta(Integer idArquivo) throws DAOException;
    
    List<OfertaVLFArqItemTO> searchCriticaOfertaComplementar(Integer idArquivo) throws DAOException;

}
