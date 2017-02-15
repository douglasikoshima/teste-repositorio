package br.com.vivo.fo.ctrls.cliente.telaInicial;

import javax.ejb.Local;

@Local
public interface TelaInicialFacade {

    public void setExcluirEndereco(java.lang.String user, br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO enderecoExcluido) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setExcluirComunicacao(java.lang.String user, br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO comunicacaoExcluida) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setSalvarAlterarEndereco(java.lang.String user, br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO enderecoAlterado) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setSalvarNovoEndereco(java.lang.String user, br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO enderecoIncluido) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setSalvarNovaComunicacao(java.lang.String user, br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO comunicacaoIncluida) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String setSalvarAlterarComunicacao(java.lang.String user, br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO comunicacaoAlterada, java.lang.String idPessoa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.UsuarioVODocument.UsuarioVO getDADUSUARIOTI(java.lang.String user, java.lang.String nrLinha) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.TIClienteDocument.TICliente getDADCLIENTETI(java.lang.String user, java.lang.String nrLinha) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.TIFaturamentoDocument.TIFaturamento getDADFACTURATI(java.lang.String user, java.lang.String nrLinha) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.TILinhaDocument.TILinha getDADLINHATI(java.lang.String user, java.lang.String nrLinha) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.HistoricoAtendimentoVODocument.HistoricoAtendimentoVO getAbaServicos(java.lang.String user, java.lang.String idContaSO, java.lang.String idLinhaSO, java.lang.String nrLinha, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.HistoricoServicosVODocument.HistoricoServicosVO getHistoricoServicos(java.lang.String user, br.com.vivo.fo.cliente.vo.HistoricoServicosVODocument.HistoricoServicosVO filtro, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.TelaInicialVODocument.TelaInicialVO getTelaInicial(java.lang.String user, java.lang.String idLinhaTelefonica) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
    
    public br.com.vivo.fo.cliente.vo.TelaInicialVODocument.TelaInicialVO getTelaInicialById(java.lang.String user, java.lang.String idLinhaTelefonica) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ConvergenciaVODocument.ConvergenciaVO getLinhasConvergentes(String user, br.com.vivo.fo.cliente.vo.ConvergenciaVODocument.ConvergenciaVO convergenciaVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO getLupaClienteVO(java.lang.String user, java.lang.String idPessoa, java.lang.String nrLinha, java.lang.String idContaSistemaOrigem, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO getLupaLinhaVO(br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO.FiltroLinhaVO filtroLinhaVO, java.lang.String user, java.lang.String nrLinha, java.lang.String idLinhaTelefonica, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO obterLinhaVO(br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO.FiltroLinhaVO filtroLinhaVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO consultaLinhaVO(br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO.FiltroLinhaVO filtroLinhaVO, java.lang.String user, java.lang.String nrLinhaFiltro, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void salvarLinhaVO(br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO.FiltroLinhaVO filtroLinhaVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaCarteirizacaoPFVODocument.LupaCarteirizacaoPFVO getLupaCarteirizacaoPFVO(java.lang.String user, java.lang.String idPessoa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaCarteirizacaoPJVODocument.LupaCarteirizacaoPJVO getLupaCarteirizacaoPJVO(java.lang.String user, java.lang.String idPessoa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaSegmentacaoVODocument.LupaSegmentacaoVO getLupaSegmentacaoVO(java.lang.String user, java.lang.String idPessoa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PesquisaClienteVODocument.PesquisaClienteVO getClientes(java.lang.String pesquisa, java.lang.String valor) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.CanaisExistentesVODocument.CanaisExistentesVO getCanaisExistentesVO(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO getCanaisRelacionadosVO(br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO canaisRelacionadosVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.MeioContatoVODocument.MeioContatoVO getMeioContatoVO(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO getComunicacoesRecusadasVO(br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO recusadasVO, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void salvarCanaisRelacionados(br.com.vivo.fo.usuario.vo.CanaisRelacionadosVODocument.CanaisRelacionadosVO canaisRelacionados, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void salvarComunicacoesRecusadas(br.com.vivo.fo.cliente.vo.ComunicacoesRecusadasVODocument.ComunicacoesRecusadasVO recusadas, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.CarregarNovoEnderecoVODocument.CarregarNovoEnderecoVO getIncluirEndereco(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO getContato(java.lang.String user, java.lang.String idPessoa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO getEndereco(java.lang.String user, java.lang.String idPessoa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ListaTipoComunicacaoVODocument.ListaTipoComunicacaoVO getIncluirComunicacao(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO getEndecoPorCEP(java.lang.String user, java.lang.String nrCEP) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PesquisaTIVODocument.PesquisaTIVO getPesquisaTILinhas(java.lang.String user, java.lang.String idPessoa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO getParametrosVO(java.lang.String user, java.lang.String idLinhaTelefonica, java.lang.String idTipoRelacionamento) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.TIFaturamentoDocument.TIFaturamento getFaturamento(java.lang.String user, java.lang.String idContaSO, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO getPesquisaEnderecoIni(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO getPesquisaEnderecoFil(java.lang.String user, br.com.vivo.fo.cliente.vo.PesquisaEnderecoVODocument.PesquisaEnderecoVO filtroEndereco) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.EnderecoVODocument.EnderecoVO getEnderecoEscolhido(java.lang.String user, java.lang.String idEndereco) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaClienteVODocument.LupaClienteVO getPontos(java.lang.String user, java.lang.String nrLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.TelaInicialVODocument.TelaInicialVO getTelaInicialProspect(java.lang.String user, java.lang.String idProspect) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LinhasPorIdVODocument.LinhasPorIdVO getLinhasPorId(java.lang.String user, br.com.vivo.fo.cliente.vo.LinhasPorIdVODocument.LinhasPorIdVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PesquisaTIVODocument.PesquisaTIVO getPesquisaTIClientes(java.lang.String user, java.lang.String pesquisa, java.lang.String pagina, java.lang.String valor, java.lang.String nmMeio, java.lang.String nmSobreNome, java.lang.String inPrePago) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.DadosChipVODocument.DadosChipVO getDadosChip(java.lang.String user, br.com.vivo.fo.cliente.vo.DadosChipVODocument.DadosChipVO dadosChipVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.DesbloqueioGsmVODocument.DesbloqueioGsmVO getSimlockAparelho(java.lang.String user, br.com.vivo.fo.cliente.vo.DesbloqueioGsmVODocument.DesbloqueioGsmVO desbloqueioVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PesquisaTIVODocument.PesquisaTIVO getPesquisaTILinhasContaPaginada(java.lang.String user, java.lang.String nrConta, java.lang.String pagina) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PesquisaTIVODocument.PesquisaTIVO getPesquisaTILinhasConta(java.lang.String user, java.lang.String nrConta) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PesquisaTIVODocument.PesquisaTIVO getPesquisaTILinhasPaginada(java.lang.String user, java.lang.String idPessoa, java.lang.String pagina) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.AbaServicosFiltroVODocument.AbaServicosFiltroVO getAbaServicosFiltro(java.lang.String user, java.lang.String nrConta, java.lang.String nrLinha, java.lang.String idPessoa, java.lang.String idTipoRelacionamento) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.TrackingAparelhosVODocument.TrackingAparelhosVO getAbaTrackingComparar(java.lang.String user, java.lang.String nrDocumento, java.lang.String numPedido, java.lang.String idSistemaOrigem, java.lang.String nrOrdemVenda, java.lang.String nrFornecimento, java.lang.String nrPicking, java.lang.String nrNotaFiscal) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setComentarioTracking(java.lang.String user, br.com.vivo.fo.workflow.vo.WFExecucaoDocument.WFExecucao wFExecucao) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaLinhaAbasGSMVODocument.LupaLinhaAbasGSMVO getLupaLinhaAbasGSM(java.lang.String user, java.lang.String tpOperacao, java.lang.String idLinha, java.lang.String nrLinha, java.lang.String nrIP, java.lang.String parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaLinhaVODocument.LupaLinhaVO consultaLinhaVOPorIdProcesso(java.lang.String user, java.lang.String idProcesso) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO getParametro(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.TrackingAparelhosVODocument.TrackingAparelhosVO getAbaTrackingDetalhe(java.lang.String user, java.lang.String nrDocumento, java.lang.String numPedido, java.lang.String idSistemaOrigem, java.lang.String nmSistemaOrigem, java.lang.String nrOrdemVenda, java.lang.String nrFornecimento, java.lang.String nrPicking, java.lang.String nrNotaFiscal, java.lang.String sgUF) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setAparelhoImei(java.lang.String user, java.lang.String marca, java.lang.String modelo, java.lang.String imei, java.lang.String idLinhaTelefonica, java.lang.String nmLoja, java.lang.String nrProtocolo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO inibeMsgPontos(java.lang.String user, java.lang.String nrLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ReloadLinhaVODocument.ReloadLinhaVO getLinhaPesquisada(java.lang.String user, java.lang.String idLinha, java.lang.String idClienteDePara) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PortabilidadeVODocument.PortabilidadeVO getPortabilidadeVO(java.lang.String idUsuario, br.com.vivo.fo.cliente.vo.PortabilidadeVODocument.PortabilidadeVO portabilidadeVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.TrackingAparelhosVODocument.TrackingAparelhosVO getAbaTrackingLista(java.lang.String user, java.lang.String idPessoaCliente, java.lang.String pageNumber, java.lang.String nrDocumento, java.lang.String dsTipoDocumento) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
