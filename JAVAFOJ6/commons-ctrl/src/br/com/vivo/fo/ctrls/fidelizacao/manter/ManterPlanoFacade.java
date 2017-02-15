package br.com.vivo.fo.ctrls.fidelizacao.manter;

import javax.ejb.Local;

@Local
public interface ManterPlanoFacade {

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getRegionais(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.ListaPlanoVODocument.ListaPlanoVO getPlano(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.PlanoPesquisaVODocument.PlanoPesquisaVO planoPVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO setPlano(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.PlanoVODocument.PlanoVO plano) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO delPlano(java.lang.String user, java.lang.String id) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO addPlano(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.PlanoVODocument.PlanoVO planoPesVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
