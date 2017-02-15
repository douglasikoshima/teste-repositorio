package com.indracompany.catalogo.ejb.relatoriofixa;

import java.util.List;

import javax.ejb.Local;

import br.com.indrasistemas.framework.service.BusinessException;

import com.indracompany.catalogo.to.GrupoComercialRelatorioFixaTO;
import com.indracompany.catalogo.to.RelacionamentoRelatorioFixaTO;
import com.indracompany.catalogo.to.SCxENCxPFxGCxPMxACRelatorioFixaTO;
import com.indracompany.catalogo.to.SCxGCxPMxACRelatorioFixaTO;
import com.indracompany.catalogo.to.ServicoRelatorioFixaTO;
import com.indracompany.catalogo.to.SolicitacaoComercialFixaTO;
import com.indracompany.catalogo.to.TipoRelacionamentoTO;
import com.indracompany.catalogo.to.TipoSolicitacaoComercialTO;

@Local
public interface RelatorioFixaBeanLocal {
    
    public static final String JNDI_NAME = "java:comp/env/RelatorioFixaBean";

    List<TipoSolicitacaoComercialTO> findAllTpSolicitacaoComercial() throws BusinessException;

    List<ServicoRelatorioFixaTO> pesquisarServico(ServicoRelatorioFixaTO servicoRelatorioFixaTO) throws BusinessException;

    List<RelacionamentoRelatorioFixaTO> pesquisarRelacionamentoServico(RelacionamentoRelatorioFixaTO relacionamentoRelatorioFixaTO) throws BusinessException;

    List<SolicitacaoComercialFixaTO> pesquisarSolicitacaoComercial(SolicitacaoComercialFixaTO solicitacaoComercialRelatorioFixaTO) throws BusinessException;

    List<TipoRelacionamentoTO> findAllTpRelacionamento() throws BusinessException;

    List<GrupoComercialRelatorioFixaTO> pesquisarGrupoComercial(GrupoComercialRelatorioFixaTO grupoComercialRelatorioFixaTO) throws BusinessException;

    List<SCxGCxPMxACRelatorioFixaTO> pesquisarSCxGCxPMxAC(SCxGCxPMxACRelatorioFixaTO sCxGCxPMxACRelatorioFixaTO) throws BusinessException;

    List<SCxENCxPFxGCxPMxACRelatorioFixaTO> pesquisarSCxENCxPFxGCxPMxAC(SCxENCxPFxGCxPMxACRelatorioFixaTO sCxENCxPFxGCxPMxACRelatorioFixaTO) throws BusinessException;

}
