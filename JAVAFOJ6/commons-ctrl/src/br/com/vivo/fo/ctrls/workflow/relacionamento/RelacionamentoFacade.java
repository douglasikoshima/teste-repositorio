package br.com.vivo.fo.ctrls.workflow.relacionamento;

import javax.ejb.Local;

@Local
public interface RelacionamentoFacade {

    public br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO pesquisarSubEstado(java.lang.String user, java.lang.String idEstado) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AtendimentoRelacionamentosVODocument.AtendimentoRelacionamentosVO pesquisarRelacionamento(java.lang.String user, br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO atPesquisaVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.AtendimentoPesquisaVODocument.AtendimentoPesquisaVO obterEstados(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
