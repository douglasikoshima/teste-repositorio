package br.com.vivo.fo.ctrls.admsistemas.motivos;

import javax.ejb.Local;

@Local
public interface MotivosFacade {

    public br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO[] pesquisar(java.lang.String user, br.com.vivo.fo.workflow.vo.WFManterMotivosVODocument.WFManterMotivosVO pesquisa) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void crudMotivos(java.lang.String user, br.com.vivo.fo.workflow.vo.WFManterMotivosVODocument.WFManterMotivosVO manterMotivos) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFManterMotivosVODocument.WFManterMotivosVO lerMotivoAcoes(java.lang.String user, br.com.vivo.fo.workflow.vo.WFManterMotivosVODocument.WFManterMotivosVO manterMotivos) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void gravarMotivoAcoes(java.lang.String user, br.com.vivo.fo.workflow.vo.WFManterMotivosVODocument.WFManterMotivosVO manterMotivos) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
