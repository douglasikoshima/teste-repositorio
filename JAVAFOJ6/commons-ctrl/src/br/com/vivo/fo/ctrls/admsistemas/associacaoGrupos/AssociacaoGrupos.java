package br.com.vivo.fo.ctrls.admsistemas.associacaoGrupos;

import javax.ejb.Local;

@Local
public interface AssociacaoGrupos {

    public br.com.vivo.fo.admsistemas.vo.GruposProcessosVODocument.GruposProcessosVO getGruposProcessos(java.lang.String user, java.lang.String contato, java.lang.String acao) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO setGruposAssociados(java.lang.String user, java.lang.String xmlEntrada) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.admsistemas.vo.AssosiacaoGrupoVariaveisVODocument.AssosiacaoGrupoVariaveisVO getAssosiacaoGrupoVariaveisVO(java.lang.String user, java.lang.String contato, java.lang.String grupo, java.lang.String fechamento) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] getWFAcaoVO(java.lang.String user, java.lang.String idContato) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
