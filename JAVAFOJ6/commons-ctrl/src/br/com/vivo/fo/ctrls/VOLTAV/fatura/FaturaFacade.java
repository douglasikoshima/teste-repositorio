package br.com.vivo.fo.ctrls.VOLTAV.fatura;

import javax.ejb.Local;

@Local
public interface FaturaFacade {

    public boolean isHierarquia(java.lang.String idUsuario, java.lang.String cdConta) throws br.com.vivo.fo.exception.FacadeException;

    public boolean isClienteDadosZap(java.lang.String idUsuario, java.lang.String cdDDD, java.lang.String nrLinha) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.fatura.db.dbClasses.TipoComunicacao[] getListaTiposComunicacao(java.lang.String idUsuario, java.lang.String sgTipoComunicacao) throws br.com.vivo.fo.exception.FacadeException;

    public void desativarFaturaEmailPJ(java.lang.String idUsuario, java.lang.String nrConta) throws java.lang.Exception;

    public void desativarFaturaEmailPF(java.lang.String idUsuario, java.lang.String nrTelefone, boolean isPrePago) throws java.lang.Exception;

    public noNamespace.ServicoEmailBeanDocument.ServicoEmailBean ativaEmail(java.lang.String idUsuario, java.lang.String nrTelefone, java.lang.String dsEmail, java.lang.String dataTarefa, java.lang.Integer idUFOperadora, boolean isPrePago, boolean isPJ, java.lang.String idContaSistemaOrigem) throws java.lang.Exception;

    public noNamespace.ServicoEmailBeanDocument.ServicoEmailBean ativarSomenteAvisoDisponibilidadeEmail(java.lang.String idUsuario, java.lang.String nrTelefone, java.lang.String dsEmail, java.lang.String dataTarefa, java.lang.Integer idUFOperadora, boolean isPrePago, boolean isPJ, java.lang.String nrConta) throws java.lang.Exception;
}
