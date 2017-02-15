package br.com.vivo.fo.ctrls.workflow.monitoramento;

import javax.ejb.Local;

@Local
public interface MonitoramentoFacade {

    public br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO obtemGruposParaMonitoramento(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO obtemGruposRC(java.lang.String user, int inFila) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.MonitoramentoResultadoVODocument.MonitoramentoResultadoVO executaPesquisa(java.lang.String user, br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO monitoramentoPesquisaVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO obtemGrupos(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.MonitoramentoPesquisaVODocument.MonitoramentoPesquisaVO getMonitoramentoPesquisaVO(java.lang.String user, boolean inTipoCarteira, boolean inSegmentacao, boolean inAlerta, boolean inTipoPessoa, boolean inClassificadorTipoLinha) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
