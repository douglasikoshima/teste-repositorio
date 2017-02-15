package com.indracompany.catalogo.dao.interfaces;

import java.util.List;

import com.indracompany.catalogo.exception.DAOException;
import com.indracompany.catalogo.to.GrupoComercialRelatorioFixaTO;
import com.indracompany.catalogo.to.RelacionamentoRelatorioFixaTO;
import com.indracompany.catalogo.to.SCxENCxPFxGCxPMxACRelatorioFixaTO;
import com.indracompany.catalogo.to.SCxGCxPMxACRelatorioFixaTO;
import com.indracompany.catalogo.to.ServicoRelatorioFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoSolicitacaoComercialTO;

public interface RelatorioFixaDAO {

    List<TipoSolicitacaoComercialTO> findAllTpSolicitacaoComercial() throws DAOException;

    List<ServicoRelatorioFixaTO> pesquisarServico(ServicoRelatorioFixaTO servicoRelatorioFixaTO) throws DAOException;

    List<RelacionamentoRelatorioFixaTO> pesquisarRelacionamentoServico(RelacionamentoRelatorioFixaTO relacionamentoRelatorioFixaTO) throws DAOException;

    List<SolicitacaoComercialFixaTO> pesquisarSolicitacaoComercial(SolicitacaoComercialFixaTO solicitacaoComercialRelatorioFixaTO)throws DAOException;

    List<GrupoComercialRelatorioFixaTO> pesquisarGrupoComercial(GrupoComercialRelatorioFixaTO grupoComercialRelatorioFixaTO)throws DAOException;

    List<SCxGCxPMxACRelatorioFixaTO> pesquisarSCxGCxPMxAC(SCxGCxPMxACRelatorioFixaTO cxGCxPMxACRelatorioFixaTO)throws DAOException;
    
    List<SCxENCxPFxGCxPMxACRelatorioFixaTO> pesquisarSCxENCxPFxGCxPMxAC(SCxENCxPFxGCxPMxACRelatorioFixaTO sCxENCxPFxGCxPMxACRelatorioFixaTO) throws DAOException;
    
}
