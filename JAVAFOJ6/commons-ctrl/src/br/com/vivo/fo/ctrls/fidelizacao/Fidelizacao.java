package br.com.vivo.fo.ctrls.fidelizacao;

import javax.ejb.Local;

@Local
public interface Fidelizacao {

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO getListas(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO xml) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO setParam(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO xml) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;
}
