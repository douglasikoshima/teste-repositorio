package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.AnalisePriorizarTO;

public interface AnaliseCreditoPriorizarDAO {

    List<AnalisePriorizarTO> pesquisarOferta(Integer idAnaliseCredito, Integer idEps) throws DAOException;

    void gravarPriorizacao(Integer idAnaliseCredito, List<Integer> idOfertafixaCreditoScoreList, Integer idEps, List<AnalisePriorizarTO> analisePriTOListRemove) throws DAOException;

}
