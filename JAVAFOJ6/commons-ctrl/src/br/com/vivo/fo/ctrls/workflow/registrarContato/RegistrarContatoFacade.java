package br.com.vivo.fo.ctrls.workflow.registrarContato;

import javax.ejb.Local;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.admsistemas.vo.AdmFolhaBaixaVODocument.AdmFolhaBaixaVO;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoArvoreFiltrosVODocument.AtendimentoArvoreFiltrosVO;
import br.com.vivo.fo.workflow.vo.ChamadaTelefonicaVODocument.ChamadaTelefonicaVO;
import br.com.vivo.fo.workflow.vo.ContaVODocument.ContaVO;
import br.com.vivo.fo.cliente.vo.ListaTipoComunicacaoVODocument.ListaTipoComunicacaoVO;
import br.com.vivo.fo.cliente.vo.TipoComunicacaoVODocument.TipoComunicacaoVO;
import br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO;
import br.com.vivo.fo.workflow.vo.ListaDadosVODocument.ListaDadosVO;
import br.com.vivo.fo.admsistemas.vo.FormularioCampoVODocument.FormularioCampoVO;
import br.com.vivo.fo.workflow.vo.WFListaContatosVODocument.WFListaContatosVO;
import br.com.vivo.fo.workflow.vo.FechamentoAtendimentoVODocument.FechamentoAtendimentoVO;
import br.com.vivo.fo.workflow.vo.InformeBuscaVODocument.InformeBuscaVO;

@Local
public interface RegistrarContatoFacade {

    public AtendimentoVO registrarAtendimento(String user, AtendimentoVO atendimentoVO) throws TuxedoException, FacadeException;

    public AdmFolhaBaixaVO obtemArvoreBaixa(String user, int idContato, int idTipoComunicacao) throws TuxedoException, FacadeException;

    public AtendimentoVO consultarContato(String user, int idContato, long idPessoaDePara, int idFaseProcesso, String idTipoLinha, String idLinha, String idUFOperadora, String nrTelefone, int indAbertura, String idGrupo, String inTipoPessoa, String idTipoCarteira, String idSegmentacao, String idTipoRelacionamento, String idPessoa, String idConta, boolean inPreview) throws TuxedoException, FacadeException;

    public AdmContatoFolhaVO carregaArvoreContato(String user, AtendimentoArvoreFiltrosVO atendimentoArvoreFiltrosVO) throws TuxedoException, FacadeException;

    public AtendimentoVO verificaProcessosCorrentes(String user, long idChamadaTelefonica) throws FacadeException;

    public ChamadaTelefonicaVO obtemChamadaTelefonica(String user, int idGrauSatisfacao) throws TuxedoException, FacadeException;

    public void enviarProcessosCorrentes(String user, String idChamadaTelefonica, String idGrauSatisfacao) throws FacadeException;

    public void registrarInsistencia(String user, AtendimentoVO atendimentoVO) throws TuxedoException, FacadeException;

    public AtendimentoVO obtemTipoComunicacao(String user, String idPessoaDePara) throws FacadeException;

    public ContaVO obterLinhas(String user, String idConta, String idLinha) throws FacadeException;

    public ListaTipoComunicacaoVO obtemTipoComunicacaoAtendimento(String user) throws FacadeException;

    public TipoComunicacaoVO getIdTipoComunicacaoBySG(String user, String sgTipoComunicacao) throws FacadeException;

    public LupaClienteVO obtemComunicacaoPessoa(String idPessoa, String user) throws FacadeException;

    public AtendimentoVO obtemComunicacao(String idPessoa, String idTipoComunicacao, String user) throws FacadeException;

    public FormularioCampoVO obtemPesquisaFormulario(String user, String textoPesquisa, String idCampo) throws FacadeException;

    public ListaDadosVO getConsultaConta(String user, String tpOperacao, String idPessoa, String nrValor) throws TuxedoException, FacadeException;

    public AtendimentoVO fechamentoProcesso(String user, FechamentoAtendimentoVO fechamentoAtendimentoVO) throws TuxedoException, FacadeException;

    public void excluirProcessoCorrente(String user, String idAtendimento) throws TuxedoException, FacadeException;

    public WFListaContatosVO getListaPesquisaContatos(String user, InformeBuscaVO informe) throws TuxedoException, FacadeException;

    public void registrarSolicitacaoSenha(String user, String idGrupoAbertura, String inResponsavelAbertura, String ddd, String nrLinha, String tpOperacao, String idProcedencia, String idCanal) throws TuxedoException, FacadeException;

    public void registrarContatoSenhaIncrgContato(String user, String nrLinha, String cdAreaRegistro, String idTipoRelacionamento, String idCanal, String cdContato);

    public ListaDadosVO getConsultaContaEx(String user, String tpOperacao, String idPessoa, String nrValor) throws TuxedoException, FacadeException;
}
