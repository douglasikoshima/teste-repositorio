package br.com.vivo.fo.ctrls.VOLTAV.manterRedirecionamento;

import javax.ejb.Local;

@Local
public interface ManterRedirecionamento {

    public br.com.vivo.fo.ctrls.VOLTAV.manterRedirecionamento.db.ManterRedirecionamentoDB.ItemRedirecionamento[] consultarRedirecionamento(java.lang.Short idSistema) throws java.lang.Exception;
}
