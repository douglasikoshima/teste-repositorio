package br.com.vivo.fo.ctrls.admsistemas.crud.apoioparametro;

import javax.ejb.Local;

@Local
public interface ApoioParametro {

    public void salvarParametro(br.com.vivo.fo.dbclasses.ApoioParametro parametro, long idUsuario) throws java.lang.Exception;

    public br.com.vivo.fo.dbclasses.ApoioParametro[] buscarParametrosPorLetra(java.lang.String letraInicial) throws java.lang.Exception;

    public br.com.vivo.fo.dbclasses.ApoioParametro buscarParametro(long idParametro) throws java.lang.Exception;

    public void excluirParametro(long idParametro) throws java.lang.Exception;
}
