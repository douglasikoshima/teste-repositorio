package br.com.vivo.fo.ctrls.fidelizacao.OfertaRealizada;

public interface OfertaRealizadaFacade {

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getBuscaUrl(java.lang.String user, java.lang.String[] dados) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getMsgEncerramento(java.lang.String user, java.lang.String idUfOperadora, java.lang.String idTpEncerramento) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO finalizaRetencao(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FinalizaRetencaoVODocument.FinalizaRetencaoVO finalizaRetencaoVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setObservacao(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.MensajeEncerraVODocument.MensajeEncerraVO msjEncerraVO) throws br.com.vivo.fo.exception.TuxedoException, org.apache.xmlbeans.XmlException, br.com.vivo.fo.exception.FacadeException;

    public void addVaiPensarCancelar(java.lang.String user, java.lang.String[] dados, char tipoEncerramento, java.lang.String observacao) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO getDiasSuspTemp(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralDescricaoVODocument.FidelizacaoListaGeralDescricaoVO getOfertasDisponiveis(java.lang.String user, java.lang.String[] dados, java.lang.String idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.ListaPlanoVODocument.ListaPlanoVO getPlano(java.lang.String user, java.lang.String idUfOperadora, java.lang.String sgTipoPessoa, java.lang.String idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.ListaBonusVODocument.ListaBonusVO getBonus(java.lang.String user, java.lang.String idUfOperadora, java.lang.String idMatrizOferta, java.lang.String sgTipoPessoa, java.lang.String idSegmentacao, java.lang.String idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.ListaMigracaoVODocument.ListaMigracaoVO getListaMigracao(java.lang.String user, java.lang.String idUfOperadora, java.lang.String sgTipoPessoa, java.lang.String idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO getListaPalitagens(java.lang.String user, br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO atendimentoPesquisaVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
