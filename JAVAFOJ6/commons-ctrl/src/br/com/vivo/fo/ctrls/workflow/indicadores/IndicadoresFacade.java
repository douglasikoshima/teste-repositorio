package br.com.vivo.fo.ctrls.workflow.indicadores;

import javax.ejb.Local;

@Local
public interface IndicadoresFacade {

    public br.com.vivo.fo.workflow.vo.WFIndicadoresPesquisaVODocument.WFIndicadoresPesquisaVO obtemWFIndicadoresPesquisaVO(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFIndicadoresVODocument.WFIndicadoresVO obtemWFIndicadoresVO(java.lang.String user, java.lang.String tipo, br.com.vivo.fo.workflow.vo.WFIndicadoresPesquisaVODocument.WFIndicadoresPesquisaVO filtro) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFIndicadoresVODocument.WFIndicadoresVO gerarResumoAcompanhamento(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
