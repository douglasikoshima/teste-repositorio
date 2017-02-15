package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice.impl;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.delegate.CadastroIDMBD;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.ParametrosTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RelacionaUsuarioTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RespostaTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice.CadastroIDMWS;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice.to.ParametrosWSTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice.to.RelacionaUsuarioWSTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice.to.RespostaWSTO;

public class CadastroIDMWSImpl implements CadastroIDMWS {

	public CadastroIDMWSImpl() {
	}

	public RespostaWSTO cadastrarUsuarioIdm(RequestInfoTO requestInfo, ParametrosWSTO parametros) {
		RespostaWSTO to = new RespostaWSTO();
		try {
			CadastroIDMBD bd = new CadastroIDMBD();
			ParametrosTO parametrosTO = new ParametrosTO(parametros);
			
			RespostaTO respostaTO = bd.cadastrar(requestInfo, parametrosTO);
			
			to.setIdUsuario(respostaTO.getIdUsuario());
			to.setIdUF(respostaTO.getIdUF());
			to.setLogin(respostaTO.getLogin());
			to.setNome(respostaTO.getNome());
			to.setPrimeiroAcesso(respostaTO.getPrimeiroAcesso());
			to.setSobrenome(respostaTO.getSobrenome());
			to.setCodError(respostaTO.getCodError());
			to.setMsgError(respostaTO.getMsgError());
			to.setStatus(RespostaWSTO.OK);
			to.setReason("");

		} catch (Exception e) {
			to.setStatus(RespostaWSTO.NAO_OK);
			to.setReason(e.getMessage());
		}
		return to;
	}

	public RespostaWSTO relacionarUsuarioIdm(RequestInfoTO requestInfo, RelacionaUsuarioWSTO parametros) {
		RespostaWSTO to = new RespostaWSTO();
		try {
			CadastroIDMBD bd = new CadastroIDMBD();
			RelacionaUsuarioTO parametrosTO = new RelacionaUsuarioTO(parametros);
			parametrosTO.setNmUnidade(parametros.getNmUnidade());
			
			RespostaTO respostaTO = bd.relacionarUsuarioIdm(requestInfo, parametrosTO);
			
			to.setIdUsuario(respostaTO.getIdUsuario());
			to.setIdUF(respostaTO.getIdUF());
			to.setLogin(respostaTO.getLogin());
			to.setNome(respostaTO.getNome());
			to.setPrimeiroAcesso(respostaTO.getPrimeiroAcesso());
			to.setSobrenome(respostaTO.getSobrenome());
			to.setCodError(respostaTO.getCodError());
			to.setMsgError(respostaTO.getMsgError());
			to.setStatus(RespostaWSTO.OK);
			to.setReason("");

		} catch (Exception e) {
			to.setStatus(RespostaWSTO.NAO_OK);
			to.setReason(e.getMessage());
		}
		return to;
	}
}