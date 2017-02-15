package br.com.vivo.fo.ctrls.VOLTAV.quadroAviso;

import javax.ejb.Local;

@Local
public interface QuadroAvisoFacade {

    public br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.UF[] getUFs() throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.Segmento[] getSegmentos() throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.Associado associadoUfSegmentacao(java.lang.String conta) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.Carteira[] carteirasTodas(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.Carteira[] carteirasSegmentacao(java.lang.String segmentacao, java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.Carteira[] carteirasSegmentacaoRegional(java.lang.String uf, java.lang.String segmentacao, java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.Carteira[] carteirasRegional(java.lang.String regional, java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public boolean validaAssociacao(java.lang.String conta, java.lang.String consultor) throws br.com.vivo.fo.exception.FacadeException;

    public void excluirMensagem(java.lang.String id) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.quadroAviso.db.QuadroAvisoDB.Carteira[] carteirasGeral(java.lang.String user) throws br.com.vivo.fo.exception.FacadeException;

    public void inserirMensagem(java.sql.Date dtinicio, java.sql.Date dtfim, java.lang.String cdconta, java.lang.String idconsultor, java.lang.String dsmensagem, java.lang.String idMensagem) throws br.com.vivo.fo.exception.FacadeException;

    public void inserirMensagemLog(java.lang.String cdconta, java.lang.String idMensagem) throws br.com.vivo.fo.exception.FacadeException;

    public void inserirMensagemSegmentacao(java.lang.String segmentacao, java.lang.String idconsultor, java.lang.String idMensagem) throws br.com.vivo.fo.exception.FacadeException;

    public void inserirMensagemUF(java.lang.String uf, java.lang.String idconsultor, java.lang.String idMensagem) throws br.com.vivo.fo.exception.FacadeException;
}
