package br.com.vivo.fo.ctrls.workflow.notasura;

import javax.ejb.Local;

@Local
public interface NotasURAFacade {

    public br.com.vivo.fo.workflow.vo.WFAtdNotasVODocument.WFAtdNotasVO pesquisarNotasURA(java.lang.String user, br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO notaVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO lerNotaURA(java.lang.String user, br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO notaVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO gravarHistoricoNota(java.lang.String user, br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO notaVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO lerNotasURADetalhes(java.lang.String user, br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO notaVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO gravarNotaURA(java.lang.String user, br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO notaVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAtdTipoNotasVODocument.WFAtdTipoNotasVO getTipoNotas(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO[] getMotivos(java.lang.String user, java.lang.String xml) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
