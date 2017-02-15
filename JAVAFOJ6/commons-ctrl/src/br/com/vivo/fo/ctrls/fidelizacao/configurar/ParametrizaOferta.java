package br.com.vivo.fo.ctrls.fidelizacao.configurar;

import javax.ejb.Local;

@Local
public interface ParametrizaOferta {

    public br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO getListas(java.lang.String user, br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO xml) throws br.com.vivo.fo.exception.FacadeException, br.com.vivo.fo.exception.TuxedoException;
}
