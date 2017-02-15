package com.indracompany.catalogo.ejb.analisecreditopriorizar;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.AnalisePriorizarTO;

@Local
public interface AnaliseCreditoPriorizarBeanLocal {

    public static final String JNDI_NAME = "java:comp/env/AnaliseCreditoPriorizarBean";

    List<AnalisePriorizarTO> pesquisarOferta(Integer idAnaliseCredito, Integer idEps) throws BusinessException;

    void gravarPriorizacao(Integer idAnaliseCredito, List<Integer> idOfertafixaCreditoScoreList, Integer idEPS, List<AnalisePriorizarTO> analisePriTOListRemove) throws BusinessException;
    
}
