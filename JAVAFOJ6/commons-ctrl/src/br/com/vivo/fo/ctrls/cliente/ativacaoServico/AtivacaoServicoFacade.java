package br.com.vivo.fo.ctrls.cliente.ativacaoServico;

import javax.ejb.Local;

@Local
public interface AtivacaoServicoFacade {

    public void setServico(java.lang.String idUsuario, java.lang.String linha, java.lang.String servico, java.lang.String operacao, int idTipoLinha) throws br.com.vivo.fo.exception.FacadeException;

    public void block(java.lang.String nrLinha, java.lang.String motivo, java.lang.String data, int idTipoLinha) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.servico.vo.ServicoVODocument.ServicoVO getServico(java.lang.String user, java.lang.String linha, java.lang.String servico, int idTipoLinha) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public boolean isAtivo(java.lang.String idUsuario, java.lang.String linha, java.lang.String servico, int idTipoLinha) throws br.com.vivo.fo.exception.FacadeException;
}
