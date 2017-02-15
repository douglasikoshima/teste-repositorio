package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.MatrizOfertaArquivoCriticaTO;

public interface MatrizOfertaDAO {

    List<MatrizOfertaArquivoCriticaTO> obterMatrizOfertaArquivoCriticaTOPorIdMatrizOfertaArquivoItemList(long idMatrizOfertaArquivoItem) throws DAOException;

}
