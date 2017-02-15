package br.com.vivo.fo.ctrls.VOLTAV.manterTermoAceite;

import javax.ejb.Local;

@Local
public interface ManterTermoAceite {

    public br.com.vivo.fo.ctrls.VOLTAV.manterTermoAceite.db.ManterTermoAceiteDB.ItemMenuTermoAceite[] consultarListaServicos() throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.VOLTAV.manterTermoAceite.db.ManterTermoAceiteDB.ItemMenuTermoAceite buscarTermoServico(java.lang.Short idItemMenu) throws java.lang.Exception;

    public void atualizarTermoServico(java.lang.Short idItemMenu, java.lang.String statusTermo, java.lang.String textoTermo, java.lang.String idUsuario) throws java.lang.Exception;
}
