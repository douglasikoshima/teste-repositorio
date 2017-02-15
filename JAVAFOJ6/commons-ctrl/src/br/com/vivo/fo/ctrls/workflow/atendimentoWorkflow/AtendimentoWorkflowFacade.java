package br.com.vivo.fo.ctrls.workflow.atendimentoWorkflow;

import javax.ejb.Local;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoVODocument.AtendimentoWorkflowTecnicoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowComumVODocument.AtendimentoWorkflowComumVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTecnicoDocVODocument.AtendimentoWorkflowTecnicoDocVO;
import br.com.vivo.fo.admsistemas.vo.AdmSatisfacaoContainerVODocument.AdmSatisfacaoContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO;
import br.com.vivo.fo.workflow.vo.WFDocumentoTecnicoPesquisaVODocument.WFDocumentoTecnicoPesquisaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoWorkflowTestesVODocument.AtendimentoWorkflowTestesVO;
import br.com.vivo.fo.admsistemas.vo.ArvoreAtendimentoVODocument.ArvoreAtendimentoVO;
import br.com.vivo.fo.workflow.vo.WFAcaoOrdemVendaVODocument.WFAcaoOrdemVendaVO;

@Local
public interface AtendimentoWorkflowFacade {

    public WFAcaoRetornoVO concluirGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO fechamentoGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO listaSuspeitoGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO insistenciaGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO pausaGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException;

    public AtendimentoWorkflowTecnicoVO obtemTiposEstadosDocTec(String user) throws TuxedoException, FacadeException;

    public AtendimentoWorkflowTecnicoDocVO[] obtemAtendimentoWorkflowTecnicoDocVOArray(String user, WFDocumentoTecnicoPesquisaVO wfDocumentoTecnicoPesquisaVO) throws TuxedoException, FacadeException;

    public AtendimentoWorkflowTestesVO obtemTestes(String user, String idAtendimento) throws TuxedoException, FacadeException;

    public void testesGravar(String user, AtendimentoWorkflowTestesVO atendimentoWorkflowTestesVO) throws TuxedoException, FacadeException;

    public AdmSatisfacaoContainerVO obtemPesquisaSatisfacao(String user, String idAtendimento) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO pesquisaSatisfacaoGravar(String user, AdmSatisfacaoContainerVO admSatisfacaoContainerVO) throws TuxedoException, FacadeException;

    public AdmFolhaBaixaVO obtemArvoreBaixaParte(String user, String idAtendimento, String idBaixa, String idContato, String idTipoComunicacao) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO listaEncaminharGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO solucaoTecnicaGravar(String user, AtendimentoWorkflowTestesVO atendimentoWorkflowTestesVO) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO documentoAssociadoFechamento(String user, AtendimentoWorkflowTecnicoVO atdWfTecnicoVO) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO documentoAssociadoAbertura(String user, AtendimentoWorkflowTecnicoVO atdWfTecnicoVO) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO documentoAssociadoAssociar(String user, AtendimentoVO[] atendimentos) throws TuxedoException, FacadeException;

    public AtendimentoWorkflowComumVO obtemAtendimentoWorkflowReaberturaVO(String user, String idAtendimento, String idAtividade, String idTabelaMotivo, String inGrupo) throws TuxedoException, FacadeException;

    public AtendimentoWorkflowComumVO obtemAtendimentoWorkflowComumCancelarVO(String user, String idAtendimento, String idAtividade, String idTabelaMotivo, String inFase) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO cancelarGravar(String user, AtendimentoVO[] atendimentosVO, String idFase) throws TuxedoException, FacadeException;

    public ArvoreAtendimentoVO obtemFormularioArvoreBaixa(String user, String idAtendimento, String idContato) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO listaVoltarGravar(String user, AtendimentoVO[] atendimentosVO, String idFase) throws TuxedoException, FacadeException;

    public AtendimentoWorkflowComumVO obtemAtendimentoWorkflowComumVO(String user, String idAtendimento, String idAtividade, String idTabelaMotivo, String inCRI) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO reaberturaGravar(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO gravaMotivoComentarioRespCliente(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO respostaCliente(String user, AtendimentoVO[] atendimentosVO, String idFase) throws TuxedoException, FacadeException;

    public AtendimentoWorkflowComumVO obtemAtendimentoWorkflowComumInRCVO(String user, String idAtendimento, String idAtividade, String idTabelaMotivo, String inRC) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO listaEncerrarGravar(String user, AtendimentoVO[] atendimentosVO, String idFase, boolean fechMassa) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO insistenciaGravarAbaRelacionamento(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO reaberturaGravarAbaRel(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO cancelarGravarAbaRel(String user, AtendimentoVO[] atendimentosVO, String idFase) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO concluirGravarAbaRel(String user, AtendimentoVO[] atendimentosVO) throws TuxedoException, FacadeException;

    public br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO[] obtemUsuarioVIVO(String user, String idGrupo, String status, String idAtendimento, boolean inMeuCliente) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO cancelarOrdemVenda(String idPessoaUsuario, String dsObservacao, String idBaixa, String dsComentario, String idAtendimento, String idAtividade) throws TuxedoException, FacadeException;

    public WFAcaoOrdemVendaVO carregarOrdemVendaAtualizar(String idPessoaUsuario, String idAtendimento, String idAtividade) throws TuxedoException, FacadeException;

    public WFAcaoRetornoVO atualizarOrdemVenda(String idPessoaUsuario, WFAcaoOrdemVendaVO wFAcaoOrdemVendaVO, String idAtendimento, String idAtividade) throws TuxedoException, FacadeException;
}
