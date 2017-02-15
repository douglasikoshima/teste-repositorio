package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.datalayer.OfertaFixaAdicional;
import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.ServicosAdicionaisTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoServicoTO;

public interface ServicosAdicionaisDAO {

    List<ServicosAdicionaisTO> search() throws DAOException;

    List<TipoServicoTO> loadTipoServico() throws DAOException;

    List<ServicosAdicionaisTO> findServicosByIdTipoServico(ServicosAdicionaisTO servicosAdicionaisTO) throws DAOException;

    List<SolicitacaoComercialFixaTO> findSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) throws DAOException;

    List<ServicosAdicionaisTO> recordSolicitacaoComercial(OfertaFixaAdicional ofertaFixaAdicional) throws DAOException;

    List<ServicosAdicionaisTO> deleteSolicitacaoComercial(Integer idOfertaFixaAdicional) throws DAOException;

    Boolean existsSolicitacaoComercial(ServicosAdicionaisTO servicosAdicionaisTO) throws DAOException;

    ServicosAdicionaisTO load(Integer idServicosAdicionais) throws DAOException;

}
