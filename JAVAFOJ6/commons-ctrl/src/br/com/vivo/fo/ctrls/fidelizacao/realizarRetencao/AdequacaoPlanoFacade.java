package br.com.vivo.fo.ctrls.fidelizacao.realizarRetencao;

import javax.ejb.Local;

@Local
public interface AdequacaoPlanoFacade {

    public void setPlano(java.lang.String user, java.lang.String[] param) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO getPlanos(java.lang.String user, java.lang.String idLinha) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;
}
