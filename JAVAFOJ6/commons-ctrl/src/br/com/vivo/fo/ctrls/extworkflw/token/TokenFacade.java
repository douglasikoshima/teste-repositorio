package br.com.vivo.fo.ctrls.extworkflw.token;

import javax.ejb.Local;

import br.com.vivo.fo.ctrls.extworkflw.token.db.DadosTokenVivo360;

@Local
public interface TokenFacade {
	public DadosTokenVivo360 getDadosToken(String id) throws br.com.vivo.fo.exception.FacadeException;
}
