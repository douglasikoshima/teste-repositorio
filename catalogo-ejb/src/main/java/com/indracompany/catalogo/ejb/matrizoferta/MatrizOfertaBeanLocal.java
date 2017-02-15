package com.indracompany.catalogo.ejb.matrizoferta;

import java.util.List;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.MatrizOfertaArquivoCriticaTO;

public interface MatrizOfertaBeanLocal {
    public static final String JNDI_NAME = "java:comp/env/MatrizOfertaBean";

    List<MatrizOfertaArquivoCriticaTO> obterMatrizOfertaArquivoCriticaTOPorIdMatrizOfertaArquivoItemList(long idMatrizOfertaArquivoItem)  throws BusinessException;
}
