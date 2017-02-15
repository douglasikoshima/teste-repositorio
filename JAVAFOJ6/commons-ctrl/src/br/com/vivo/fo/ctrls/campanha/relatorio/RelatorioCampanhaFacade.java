package br.com.vivo.fo.ctrls.campanha.relatorio;

import javax.ejb.Local;

@Local
public interface RelatorioCampanhaFacade {

    public br.com.vivo.fo.campanha.vo.RelatorioPercentualVODocument.RelatorioPercentualVO getRelaPercentual(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaFiltroRelatorioVODocument.CampanhaFiltroRelatorioVO filtroRelatorio) throws java.lang.Exception;

    public br.com.vivo.fo.campanha.vo.RelEfetividadeVODocument.RelEfetividadeVO getRelaEfetividade(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaFiltroRelatorioVODocument.CampanhaFiltroRelatorioVO filtroRelatorio) throws java.lang.Exception;

    public br.com.vivo.fo.campanha.vo.RelatorioRespostasVODocument.RelatorioRespostasVO getRelaRespostas(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaFiltroRelatorioVODocument.CampanhaFiltroRelatorioVO filtroRelatorio) throws java.lang.Exception;

    public br.com.vivo.fo.usuario.vo.UsuariosVODocument.UsuariosVO getUFOperadora(java.lang.String user) throws java.lang.Exception;

    public br.com.vivo.fo.campanha.vo.RelaAgendaAniversarioVODocument.RelaAgendaAniversarioVO getRelaAgendaAniv(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaFiltroRelatorioVODocument.CampanhaFiltroRelatorioVO filtroRelatorio) throws java.lang.Exception;

    public br.com.vivo.fo.campanha.vo.RelArquivoResultadoVODocument.RelArquivoResultadoVO getArqResult(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaFiltroRelatorioVODocument.CampanhaFiltroRelatorioVO filtroRelatorio) throws java.lang.Exception;

    public br.com.vivo.fo.campanha.vo.RelArquivoRespostasVODocument.RelArquivoRespostasVO getArqResp(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaFiltroRelatorioVODocument.CampanhaFiltroRelatorioVO filtroRelatorio) throws java.lang.Exception;

    public br.com.vivo.fo.campanha.vo.AreasRegistroVODocument.AreasRegistroVO getAreaRegistro(java.lang.String user, java.lang.String idUFOperadora) throws java.lang.Exception;

    public br.com.vivo.fo.campanha.vo.CampanhaRelatorioOperadorVODocument.CampanhaRelatorioOperadorVO getRelaOperador(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaFiltroRelatorioVODocument.CampanhaFiltroRelatorioVO filtroRelatorio) throws java.lang.Exception;

    public br.com.vivo.fo.campanha.vo.CampanhaRelatorioVODocument.CampanhaRelatorioVO getRelatorioGere(java.lang.String user, br.com.vivo.fo.campanha.vo.CampanhaFiltroRelatorioVODocument.CampanhaFiltroRelatorioVO filtroRelatorio) throws java.lang.Exception;
}
