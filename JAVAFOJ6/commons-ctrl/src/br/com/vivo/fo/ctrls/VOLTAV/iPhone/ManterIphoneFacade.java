package br.com.vivo.fo.ctrls.VOLTAV.iPhone;

import javax.ejb.Local;

@Local
public interface ManterIphoneFacade {

    public br.com.vivo.fo.ctrls.VOLTAV.iPhone.db.ManterIphoneDB.LinhaIphone[] getLinhasIphone(java.lang.String paginaInicial, java.lang.String paginaFinal, java.lang.String ddd, java.lang.String linha) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.iPhone.db.ManterIphoneDB.LinhaIphone getTotalLinhasIphone(java.lang.String ddd, java.lang.String linha) throws br.com.vivo.fo.exception.FacadeException;

    public void incluirLinha(java.lang.String ddd, java.lang.String linha) throws br.com.vivo.fo.exception.FacadeException;

    public void excluirLinha(java.lang.String ddd, java.lang.String linha) throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.iPhone.db.ManterIphoneDB.LinhaIphone[] getTodasLinhas() throws br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.ctrls.VOLTAV.iPhone.db.ManterIphoneDB.LinhaIphone[] getPesquisarLinhas(java.lang.String ddd, java.lang.String linha) throws br.com.vivo.fo.exception.FacadeException;
}
