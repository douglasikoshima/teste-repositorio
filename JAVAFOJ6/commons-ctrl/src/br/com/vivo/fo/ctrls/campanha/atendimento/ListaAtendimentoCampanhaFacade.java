package br.com.vivo.fo.ctrls.campanha.atendimento;

import javax.ejb.Local;

@Local
public interface ListaAtendimentoCampanhaFacade {

    public br.com.vivo.fo.campanha.vo.ListaAtendimentoCampanhaVODocument.ListaAtendimentoCampanhaVO getListaPriorizacaoCampanha(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.ListaAtendimentoCampanhaVODocument.ListaAtendimentoCampanhaVO getListaAtendimentoCampanha(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.ListaAtendimentoCampanhaVODocument.ListaAtendimentoCampanhaVO getListaAtendimentoCampanhaLinha(java.lang.String user, java.lang.String linha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.campanha.vo.ContatoAtendimentoCampanhaVODocument.ContatoAtendimentoCampanhaVO getContatoAtendimentoCampanha(java.lang.String user, java.lang.String idCanalCampanha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setQuestionarioStatus(java.lang.String user, java.lang.String idAtendimentoCampanha, java.lang.String idTipoStatusCampanha, java.lang.String idTipoMotivoCampanha, java.lang.String idTipoSubMotivoCampanha, java.lang.String idSubCampanhaHistorico) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
