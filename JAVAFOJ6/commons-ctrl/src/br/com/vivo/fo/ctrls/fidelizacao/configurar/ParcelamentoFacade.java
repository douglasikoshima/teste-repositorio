package br.com.vivo.fo.ctrls.fidelizacao.configurar;

import javax.ejb.Local;

@Local
public interface ParcelamentoFacade {

    public java.lang.String gravarParcelamento(java.lang.String user, java.lang.String idTipoPessoa, java.lang.String valorMinimo, java.util.ArrayList parcelas) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
