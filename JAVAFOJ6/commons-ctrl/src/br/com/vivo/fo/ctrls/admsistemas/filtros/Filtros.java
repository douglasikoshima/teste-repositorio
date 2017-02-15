package br.com.vivo.fo.ctrls.admsistemas.filtros;

import javax.ejb.Local;

@Local
public interface Filtros {

    public br.com.vivo.fo.admsistemas.vo.AdmFiltrosVODocument.AdmFiltrosVO salvaFiltros(br.com.vivo.fo.admsistemas.vo.AdmFiltrosVODocument.AdmFiltrosVO filtros, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AdmFiltrosVODocument.AdmFiltrosVO listaFiltros(br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO id, java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
