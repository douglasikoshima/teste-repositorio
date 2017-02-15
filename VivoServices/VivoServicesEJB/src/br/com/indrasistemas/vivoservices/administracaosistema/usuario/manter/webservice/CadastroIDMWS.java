package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice;

import br.com.indrasistemas.framework.service.to.RequestInfoTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice.to.ParametrosWSTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice.to.RelacionaUsuarioWSTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.webservice.to.RespostaWSTO;

public interface CadastroIDMWS {

	public RespostaWSTO cadastrarUsuarioIdm(RequestInfoTO requestInfo, ParametrosWSTO parametros);
	
	public RespostaWSTO relacionarUsuarioIdm(RequestInfoTO requestInfo, RelacionaUsuarioWSTO parametros);
	
}