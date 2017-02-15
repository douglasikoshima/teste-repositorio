package br.com.vivo.fo.ctrls.fidelizacao.relatorios;

import javax.ejb.Local;

@Local
public interface RelatorioFacadeFid {

    public br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoVODocument.RelatorioRetencaoVO relatorioRetencao(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelatorioReteOfertaVODocument.RelatorioReteOfertaVO relatorioReteOferta(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelatorioOperadorVODocument.RelatorioOperadorVO relatorioOperador(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelatorioLigacaoRetencaoVODocument.RelatorioLigacaoRetencaoVO relatorioLigacaoRetencao(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelaMovimenDiariasVODocument.RelaMovimenDiariasVO relatorioMovimenDiarias(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoDistincaoVODocument.RelatorioRetencaoDistincaoVO relaRetencaoDistincao(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelatorioTodasOfertasVODocument.RelatorioTodasOfertasVO relaTodasOfertas(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelatorioResDestinoVODocument.RelatorioResDestinoVO relaResDestino(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelaOfertasTotMensalVODocument.RelaOfertasTotMensalVO relaOfertasTotMensal(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosVODocument.RelatorioRetePlanosVO relaPlanos(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelatorioRetencaoInputVODocument.RelatorioRetencaoInputVO carregaDadosCriterio(java.lang.String user) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelatorioRetePlanosDistincaoVODocument.RelatorioRetePlanosDistincaoVO relaPlanosDistincao(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelatorioCCContaCorrenteVODocument.RelatorioCCContaCorrenteVO relaCCContaCorrente(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelatorioNotesLojaVODocument.RelatorioNotesLojaVO relaNotesLoja(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.fidelizacao.vo.RelatorioAdimplenciaVODocument.RelatorioAdimplenciaVO relatorioAdimplencia(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.RelatorioPesquisaVODocument.RelatorioPesquisaVO argvo) throws java.lang.Exception;

    public br.com.vivo.fo.dbclasses.RetencaoStatusSAP[] buscarStatusSAP(java.lang.String idUsuario, java.lang.String nrLinha) throws java.lang.Exception;
}
