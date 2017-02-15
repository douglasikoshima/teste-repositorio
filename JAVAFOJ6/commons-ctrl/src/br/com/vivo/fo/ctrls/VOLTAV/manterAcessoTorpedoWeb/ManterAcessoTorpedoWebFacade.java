package br.com.vivo.fo.ctrls.VOLTAV.manterAcessoTorpedoWeb;

import javax.ejb.Local;

@Local
public interface ManterAcessoTorpedoWebFacade {

    public void ativarAcessoTorpedoWeb(java.lang.String idAcessoTorpedo) throws java.lang.Exception;

    public void desativarAcessoTorpedoWeb(java.lang.String idAcessoTorpedo) throws java.lang.Exception;

    public br.com.vivo.fo.ctrls.VOLTAV.manterAcessoTorpedoWeb.db.ManterAcessoTorpedoDB.ItemAcessoTorpedo[] consultarAcessoTorpedoWeb() throws java.lang.Exception;
}
