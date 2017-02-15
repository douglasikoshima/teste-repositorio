package br.com.vivo.fo.ctrls.fidelizacao.manter;

import javax.ejb.Local;

@Local
public interface AparelhoFacade {

    public br.com.vivo.fo.fidelizacao.vo.ArrayListaGeralVODocument.ArrayListaGeralVO getDadosIniciais(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String dellAparelho(java.lang.String user, java.lang.String id) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public java.lang.String addNovoAparelho(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.AparelhosPesquisaInVODocument.AparelhosPesquisaInVO aparelhoIN) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public java.lang.String editaAparelho(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.AparelhosPesquisaInVODocument.AparelhosPesquisaInVO aparelho) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.ListaAparelhoVODocument.ListaAparelhoVO getAparelho(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.AparelhosManterGetVODocument.AparelhosManterGetVO aparelhoVO) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void setCores(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.AparelhosPesquisaInVODocument.AparelhosPesquisaInVO aparelhoVO) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;
}
