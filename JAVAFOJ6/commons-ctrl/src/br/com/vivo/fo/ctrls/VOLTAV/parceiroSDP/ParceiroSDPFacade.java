package br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP;

import javax.ejb.Local;

@Local
public interface ParceiroSDPFacade {

    public br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP.db.ParceiroSDPDB.Parceiro[] getParceiros(java.lang.String nome) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP.db.ParceiroSDPDB.Parceiro[] getTodosParceiros() throws br.com.vivo.fo.exception.FacadeException;

    public void manterParceiroSDP(java.lang.String operacao, java.lang.String user, java.lang.String nome, java.lang.String menu, java.lang.String contato, java.lang.String ip, java.lang.String url) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP.db.ParceiroSDPDB.Parceiro carregaParceiro(java.lang.String id) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.parceiroSDP.db.ParceiroSDPDB.FolhaContato[] getFolhasContatoDisponiveis(java.lang.String id) throws br.com.vivo.fo.exception.FacadeException;

    public boolean verificaDuplicidade(java.lang.String nome) throws br.com.vivo.fo.exception.FacadeException;
}
