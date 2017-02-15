package br.com.vivo.fo.ctrls.workflow.atendimento;

import javax.ejb.Local;

@Local
public interface AtendimentoFacade {

    public br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoDocVODocument.AtendimentoWorkflowTecnicoDocVO[] obtemAtendimentoWorkflowTecnicoDocVO(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO obtemFormularioVO(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO[] obtemRetornoWFCTIVO(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.FormularioVODocument.FormularioVO obtemValoresDominio(java.lang.String user, java.lang.String idCampo, java.lang.String idTipoLinha, java.lang.String idRegional) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO[] obtemAlertaVO(java.lang.String user, java.lang.String idAtendimento, java.lang.String isSimplificado) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFSubEstadoVODocument.WFSubEstadoVO[] obtemSubEstadoVO(java.lang.String user, br.com.vivo.fo.workflow.vo.WFEstadoVODocument.WFEstadoVO wFEstadoVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO obtemUsuarioVIVO(java.lang.String user, br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO usuarioVIVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void analistaDisponivel(java.lang.String user, br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO usuarioVIVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void obtemProximoProcesso(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO obtemAtendimentoInformacaoVOInBox(java.lang.String user, br.com.vivo.fo.workflow.vo.AtendimentoInBoxPesquisaVODocument.AtendimentoInBoxPesquisaVO atendimentoInBoxPesquisaVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAndamentoObservacaoVODocument.WFAndamentoObservacaoVO obtemComentarioVO(java.lang.String user, java.lang.String idAndamento) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AtendimentoDetalheVODocument.AtendimentoDetalheVO obtemAtendimentoDetalheVO(java.lang.String user, java.lang.String idAtendimento) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO[] obtemCampanhaGrupoVO(java.lang.String user, java.lang.String idGrupo) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO obtemFilaInformacaoVO(java.lang.String user, br.com.vivo.fo.workflow.vo.AtendimentoFilaPesquisaVODocument.AtendimentoFilaPesquisaVO atendimentoFilaPesquisaVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO obtemAtendimentoInformacaoVO(java.lang.String user, br.com.vivo.fo.workflow.vo.AtendimentoFilaPesquisaVODocument.AtendimentoFilaPesquisaVO atendimentoFilaPesquisaVO) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO psqFilaCRI(java.lang.String user, br.com.vivo.fo.workflow.vo.AtendimentoFilaPesquisaVODocument.AtendimentoFilaPesquisaVO atendimentoFilaPesquisaVO) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO[] obtemPrazos(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO[] obtemPrazosCRI(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO obtemAtendimentoInformacaoVOFechamentoMassa(java.lang.String user, java.lang.String idContato, java.lang.String comentario, java.lang.String login, java.lang.String dtSuspeitoFim, java.lang.String dtSuspeitoInicio, br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO usuarioVIVO) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AtendimentoHistoricoVODocument.AtendimentoHistoricoVO[] obtemAtendimentoHistoricoVO(java.lang.String user, java.lang.String idAtendimento, java.lang.String idEstado, java.lang.String idSubEstado, java.lang.String inCRI, java.lang.String inRC) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.ChamadaTelefonicaVODocument.ChamadaTelefonicaVO obtemChamadaTelefonicaVO(java.lang.String user, java.lang.String idPessoaDePara, java.lang.String tipoPessoa, java.lang.String inChamada) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.ChamadaTelefonicaVODocument.ChamadaTelefonicaVO obtemChamadaTelefonicaTdVO(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AtendimentoInformacaoVODocument.AtendimentoInformacaoVO psqFilaResp(java.lang.String user, br.com.vivo.fo.workflow.vo.RWFInBoxPesquisaVODocument.RWFInBoxPesquisaVO pesquisa) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.ListaDadosVODocument.ListaDadosVO obtemLinhasAssociadas(java.lang.String user, java.lang.String idAtendimento, java.lang.String idPessoa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO getWFEstadosVO(java.lang.String user, boolean inPortabilidade) throws java.lang.Exception;

    public br.com.vivo.fo.workflow.vo.AtendimentoHistoricoVODocument.AtendimentoHistoricoVO[] obtemAtendimentoHistoricoVOEx(java.lang.String user, java.lang.String idAtendimento, java.lang.String idEstado, java.lang.String idSubEstado, java.lang.String inCRI, java.lang.String inRC) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO[] obtemAlertaVOEx(java.lang.String user, java.lang.String idAtendimento, java.lang.String isSimplificado) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.ListaDadosVODocument.ListaDadosVO obtemLinhasAssociadasEx(java.lang.String user, java.lang.String idAtendimento, java.lang.String idPessoa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO getWFEstadosVOEx(java.lang.String user, boolean inPortabilidade) throws java.lang.Exception;
}
