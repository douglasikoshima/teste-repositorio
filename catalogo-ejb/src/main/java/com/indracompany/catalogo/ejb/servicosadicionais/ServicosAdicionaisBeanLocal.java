package com.indracompany.catalogo.ejb.servicosadicionais;

import java.util.List;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.ServicosAdicionaisTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;

public interface ServicosAdicionaisBeanLocal {

    public static final String JNDI_NAME = "java:comp/env/ServicosAdicionaisBean";

    List<ServicosAdicionaisTO> search() throws BusinessException;

    List<TipoServicoTO> loadTipoServico() throws BusinessException;

    List<ServicosAdicionaisTO> findServicosByIdTipoServico(ServicosAdicionaisTO servicosAdicionaisTO) throws BusinessException;

    List<SolicitacaoComercialFixaTO> findSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) throws BusinessException;

    List<ServicosAdicionaisTO> recordSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) throws BusinessException;

    List<ServicosAdicionaisTO> deleteSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) throws BusinessException;

    Boolean existsSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) throws BusinessException;

    ServicosAdicionaisTO load(Integer idServicosAdicionais) throws BusinessException;

}
