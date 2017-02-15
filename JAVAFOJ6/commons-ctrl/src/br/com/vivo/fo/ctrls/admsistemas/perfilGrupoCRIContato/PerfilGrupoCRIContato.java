package br.com.vivo.fo.ctrls.admsistemas.perfilGrupoCRIContato;

import javax.ejb.Local;

@Local
public interface PerfilGrupoCRIContato {

    public br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument.PerfilVariaveisVO getPerfil(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument.PerfilVariaveisVO getGrupo(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument.PerfilVariaveisVO getPerfilGrupo(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO setPerfilGrupoConatato(java.lang.String user, java.lang.String parametro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
