package br.com.vivo.fo.ctrls.admsistemas.perfilIDM;

import javax.ejb.Local;

@Local
public interface PerfilIDM {

    public br.com.vivo.fo.admsistemas.vo.IDMPerfilVODocument.IDMPerfilVO getDadosPerfilIDM(java.lang.String user, br.com.vivo.fo.admsistemas.vo.IDMPerfilVODocument.IDMPerfilVO xml) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.IDMPerfilVODocument.IDMPerfilVO setDadosPerfilIDM(java.lang.String user, br.com.vivo.fo.admsistemas.vo.IDMPerfilVODocument.IDMPerfilVO xml) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
