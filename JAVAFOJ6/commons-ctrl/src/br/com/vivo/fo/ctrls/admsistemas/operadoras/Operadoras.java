package br.com.vivo.fo.ctrls.admsistemas.operadoras;

import javax.ejb.Local;

@Local
public interface Operadoras {

    public br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO listaOperadoraApagar(br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO id, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void listaOperadoraEditada(br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO id, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO listaOperadoras(br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO id, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO salvaOperadoras(br.com.vivo.fo.admsistemas.vo.AdmOperadorasVODocument.AdmOperadorasVO operadoras, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
