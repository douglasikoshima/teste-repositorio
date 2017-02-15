package br.com.vivo.fo.ctrls.usuario.acesso;

import javax.ejb.Local;

import br.com.vivo.fo.exception.FacadeException;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.usuario.vo.UsuarioVODocument.UsuarioVO;

@Local
public interface ControleAcessoFacade {

    public UsuarioVO carregaDadosUsuarioSessao(String xmlUsuario) throws TuxedoException, FacadeException;
	public String obterValorSenha(String user, String cdAreaRegistro, String nrLinha, String idCanal, String idTipoRelacionamento) throws TuxedoException, FacadeException;
}
