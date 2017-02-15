package com.indracompany.catalogo.ejb.analisecreditolinha;

import java.util.List;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.AnaliseCreditoLinhaTO;
import com.indracompany.catalogo.to.AnaliseCreditoTO;

public interface AnaliseCreditoLinhaBeanLocal {

    public static final String JNDI_NAME = "java:comp/env/AnaliseCreditoLinhaBean";

    List<AnaliseCreditoLinhaTO> search(AnaliseCreditoLinhaTO analiseCreditoLinhaTO, List<AnaliseCreditoTO> analiseCreditoTOListScore) throws BusinessException;

    List<AnaliseCreditoLinhaTO> loadServicoLinha() throws BusinessException;

    List<AnaliseCreditoTO> loadScore() throws BusinessException;

    void gravar(AnaliseCreditoLinhaTO analiseCreditoLinhaTO) throws BusinessException;

}
