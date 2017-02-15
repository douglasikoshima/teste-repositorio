package br.com.vivo.fo.ctrls.admsistemas.perfilCRI;

import javax.ejb.Local;

@Local
public interface PerfilCRI {

    public br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument.PerfilVariaveisVO getPerfil(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument.PerfilVariaveisVO getGrupo(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO excPerfilVariaveis(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument.PerfilVariaveisVO getPerfilVariaveis(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO prazoCRI(java.lang.String user, br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument.PerfilVariaveisVO setPerfilVariaveis(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO setStatusPerfil(java.lang.String user, java.lang.String idPerfil, java.lang.String statusPerfil) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
