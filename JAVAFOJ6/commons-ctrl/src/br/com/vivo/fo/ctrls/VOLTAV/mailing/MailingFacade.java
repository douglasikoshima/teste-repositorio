package br.com.vivo.fo.ctrls.VOLTAV.mailing;

import javax.ejb.Local;

@Local
public interface MailingFacade {

    public void excluirMailing(java.lang.String idMailingBanner) throws br.com.vivo.fo.exception.FacadeException;

    public void incluirLinha(java.lang.String ddd, java.lang.String linha, java.lang.String idMailingBanner) throws br.com.vivo.fo.exception.FacadeException;

    public void excluirLinha(java.lang.String ddd, java.lang.String linha, java.lang.String idMailingBanner) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.mailing.db.Mailing.MailingLinha[] pesquisarPorDDD(java.lang.String idMailing, java.lang.String ddd) throws br.com.vivo.fo.exception.FacadeException;

    public void alterarMailing(java.lang.String idMailingBanner, java.lang.String dataInicio, java.lang.String dataFim) throws br.com.vivo.fo.exception.FacadeException;

    public java.lang.String incluirMailing(java.lang.String nome, java.lang.String dataInicio, java.lang.String dataFim, java.lang.String idAreaBanner) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.mailing.db.Mailing.MailingLinha[] pesquisarPorLinha(java.lang.String idMailing, java.lang.String linha) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.mailing.db.Mailing.MailingLinha[] pesquisarLinhasPorMailing(java.lang.String idMailing) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.mailing.db.Mailing.AreaBanner[] listarAreaBanner() throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.mailing.db.Mailing.MailingBanner[] listarMailing() throws br.com.vivo.fo.exception.FacadeException;

    public void incluirArquivo(java.lang.String cdParametro, java.lang.String idMailingBanner, java.lang.String idUser) throws br.com.vivo.fo.exception.FacadeException;
}
