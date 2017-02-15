package br.com.vivo.fo.ctrls.fidelizacao.manter;

import javax.ejb.Local;

@Local
public interface RelDescGrupo {

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO getListaGrupoRetencao(java.lang.String user) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO getListasMatrizDesconto(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO grupo) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;

    public void gravarListaDescAssociado(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FidelizacaoRelDescGrupoVODocument.FidelizacaoRelDescGrupoVO matrizDescAssociada) throws br.com.vivo.fo.exception.TuxedoException, br.com.vivo.fo.exception.FacadeException;
}
