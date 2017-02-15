package br.com.vivo.fo.ctrls.cliente.telaInicial.detalheFatura;

import javax.ejb.Local;

@Local
public interface DetalheFaturaFacade {

    public java.lang.String getAcctImage(java.lang.String ddd, java.lang.String celular, java.lang.String date, java.lang.String idSistOrigem, java.lang.String user) throws java.lang.Exception;

    public br.com.vivo.fo.cliente.vo.LupaFaturamentoPosVODocument.LupaFaturamentoPosVO buscaContaLinha(br.com.vivo.fo.cliente.vo.LupaFaturamentoPosVODocument.LupaFaturamentoPosVO lupaFaturamentoPosVO, java.lang.String idCliente, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaFaturamentoPosVODocument.LupaFaturamentoPosVO buscaDetalhes(java.lang.String idContaSO, java.lang.String dsAcao, java.lang.String user, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.LupaFaturamentoPosVODocument.LupaFaturamentoPosVO getLinhaIntraGrupo(java.lang.String user, java.lang.String idContaSO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ApoioParametroVODocument.ApoioParametroVO getApoioParametro(java.lang.String user, br.com.vivo.fo.cliente.vo.ApoioParametroVODocument.ApoioParametroVO inParametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.util.Collection getPesquisaFiltros(java.lang.String nrConta, java.lang.String dtReferencia, java.lang.String dtInicio, java.lang.String dtTermino, java.lang.String nrLinhaOrigem, java.lang.String nrLinhaDestino, java.lang.String tpOrigemChamada, java.lang.String tpArea, java.lang.String tpDetalheChamada, java.lang.String tpServico, java.lang.String tpDestino, java.lang.String paginaInicial, java.lang.String paginaFinal);

    public int getPesquisaFiltrosQtdRegistros(java.lang.String nrConta, java.lang.String dtReferencia, java.lang.String dtInicio, java.lang.String dtTermino, java.lang.String nrLinhaOrigem, java.lang.String nrLinhaDestino, java.lang.String tpOrigemChamada, java.lang.String tpArea, java.lang.String tpDetalheChamada, java.lang.String tpServico, java.lang.String tpDestino, java.lang.String paginaInicial, java.lang.String paginaFinal);

    public java.util.Collection getMesesCarregadosContaOnline(java.lang.String nrConta);
}
