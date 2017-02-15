package br.com.vivo.fo.ctrls.cliente.prePago;

import javax.ejb.Local;

@Local
public interface ConsultasPrePagoFacade {

    public br.com.vivo.fo.cliente.vo.DetalhesSaldoVODocument.DetalhesSaldoVO getDetalhesSaldo(java.lang.String user, java.lang.String idLinha, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.HistoricoMovimentosVODocument.HistoricoMovimentosVO getHistoricoMovimentos(java.lang.String user, java.lang.String idLinha, java.lang.String dataIni, java.lang.String dataFim, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.PromocoesVODocument.PromocoesVO getPromocoes(java.lang.String user, java.lang.String idLinha, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.FavoritosVODocument.FavoritosVO getFavoritos(java.lang.String user, java.lang.String idLinha, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.HistoricoAtendimentoVODocument.HistoricoAtendimentoVO getHistoricoAtendimento(java.lang.String user, java.lang.String idLinha, java.lang.String dataIni, java.lang.String dataFim, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.DetalheLinhaVODocument.DetalheLinhaVO getDetalheLinha(java.lang.String user, java.lang.String idLinha, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.servico.vo.ServicoVODocument.ServicoVO getServico(java.lang.String user, java.lang.String idLinha, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.cliente.vo.ExtratoVODocument.ExtratoVO getExtrato(java.lang.String user, java.lang.String idLinha, java.lang.String dataIni, java.lang.String dataFim, int idTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
