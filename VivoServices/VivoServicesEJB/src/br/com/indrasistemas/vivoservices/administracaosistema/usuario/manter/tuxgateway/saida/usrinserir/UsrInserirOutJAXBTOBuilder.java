package br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.tuxgateway.saida.usrinserir;

import javax.xml.bind.JAXBException;

import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.to.RespostaTO;
import br.com.indrasistemas.vivoservices.administracaosistema.usuario.manter.tuxgateway.saida.usrinserir.MsgBodyType.UsuarioLDAPVOType;

public class UsrInserirOutJAXBTOBuilder {

	static public RespostaTO buildUsuarioLDAPTO(Object body) throws JAXBException {

		RespostaTO to = new RespostaTO();
		MsgBodyType out = (MsgBodyType) body;
		if(out!=null){
			UsuarioLDAPVOType corpoOut = out.getUsuarioLDAPVO();
            if(corpoOut!=null && !"".equals(corpoOut)){
            	to.setIdUF(corpoOut.getIdUF());
            	to.setIdUsuario(corpoOut.getIdUsuario());
            	to.setLogin(corpoOut.getLogin());
            	to.setNome(corpoOut.getNome());
            	to.setPrimeiroAcesso(corpoOut.getPrimeiroAcesso());
            	to.setSobrenome(corpoOut.getSobrenome());
            }else{
                to.setCodError("99");
                to.setMsgError("Erro no retorno dos dados do Serviço");
            }
		}else{
            to.setCodError("99");
            to.setMsgError("Erro no retorno dos dados do Serviço");
		}
		return to;
	}
}
