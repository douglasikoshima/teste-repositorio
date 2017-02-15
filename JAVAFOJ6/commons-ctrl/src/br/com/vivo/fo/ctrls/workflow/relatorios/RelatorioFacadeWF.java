package br.com.vivo.fo.ctrls.workflow.relatorios;

import javax.ejb.Local;

@Local
public interface RelatorioFacadeWF {

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarProdRepresentanteBKO(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarTotalGrupoDestinoBKODownload(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarTotalRepresentateBKODownload(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarProdRepresentanteBKOPaginado(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarProdRepresentanteBKOComBotao(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarRelatorioPalitagens(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFListaArquivosGeradosVODocument.WFListaArquivosGeradosVO getListaRelatoriosPalitagens(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO obtemWFRelatoriosFiltrosVO(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatoriosFiltroRegionalVODocument.WFRelatoriosFiltroRegionalVO[] obtemRegionais(java.lang.String user, java.lang.String idOperadora) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRFRVODocument.WFRFRVO[] obtemRepresentantes(java.lang.String user, java.lang.String idGrupo) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarTotalGrupoDestinoBKO(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarTotalRepresentateBKO(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarQtdMotivoRepresentante(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarTempoMedioFila(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro, java.lang.String tipoRelatorio) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarTempoMedioFilaDetalhe(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro, java.lang.String tipoRelatorio) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarQtdPalitagem1(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarAtdEncIncorretos(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFRelatorioDinamicoVODocument.WFRelatorioDinamicoVO gerarRelatorio1CRI(java.lang.String user, br.com.vivo.fo.workflow.vo.WFRelatoriosFiltrosVODocument.WFRelatoriosFiltrosVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
