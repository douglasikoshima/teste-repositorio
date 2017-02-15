package br.com.vivo.fo.ctrls.admsistemas.operadorasBxa;

import javax.ejb.Local;

@Local
public interface OperadorasBxa {

    public br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO listaOperadoraApagar(br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO id, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void listaOperadoraEditada(br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO id, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO listaOperadoras(br.com.vivo.fo.admsistemas.vo.AdmIdBaixaVODocument.AdmIdBaixaVO id, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO salvaOperadoras(br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO operadoras, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
