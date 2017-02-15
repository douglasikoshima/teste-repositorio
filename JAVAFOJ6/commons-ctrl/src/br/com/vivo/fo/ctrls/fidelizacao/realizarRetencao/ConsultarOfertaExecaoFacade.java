package br.com.vivo.fo.ctrls.fidelizacao.realizarRetencao;

import javax.ejb.Local;

@Local
public interface ConsultarOfertaExecaoFacade {

    public void setLista(java.lang.String user, java.lang.String[] id) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.OfertaExcecaoDetalheVODocument.OfertaExcecaoDetalheVO getDetalheLista(java.lang.String user, java.lang.String dados) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.ListaOfertaExecaoVODocument.ListaOfertaExecaoVO getLista(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
