package br.com.vivo.fo.acesso;

import java.io.Serializable;
import java.util.ArrayList;

import br.com.vivo.fo.exception.AcessoException;
import br.com.vivo.fo.usuario.vo.ControleAcessoEnvioVODocument.ControleAcessoEnvioVO;
import br.com.vivo.fo.usuario.vo.ControleAcessoRecebimentoVODocument.ControleAcessoRecebimentoVO;

/**
 * Classe responsável por chamar serviços de controle e validação de acesso
 **/
@SuppressWarnings({"rawtypes","unchecked"})
public class ControleAcesso implements Serializable {

	private static final long serialVersionUID = -2627174215857206440L;

    public ArrayList verificaAcesso(String pagina, String user) throws AcessoException, Exception {
		// adicionar essas exceçoes ,AssemblerException,
		// TuxedoException,FacadeException
		ArrayList lista = new ArrayList();
		String[] listaControles;
		// ControleAcessoFacadeImpl controleAcesso = new
		// ControleAcessoFacadeImpl();
		ControleAcessoEnvioVO controleAcessoEnvioVO;
		ControleAcessoRecebimentoVO controleAcessoRecebimentoVO;
		try {
			controleAcessoEnvioVO = ControleAcessoEnvioVO.Factory.newInstance();
			controleAcessoEnvioVO.setLogin(user);
			controleAcessoEnvioVO.setPagina(pagina);
			controleAcessoRecebimentoVO = ControleAcessoRecebimentoVO.Factory.newInstance();

			// controleAcessoRecebimentoVO =
			// controleAcesso.checaAcesso(controleAcessoEnvioVO,user);
			listaControles = controleAcessoRecebimentoVO.getControleArray();
			for (int i = 0; i < listaControles.length; i++) {
				lista.add(i, listaControles[i]);
			}
			return lista;
		} catch (Exception ex) {
			throw (new Exception(ex.getMessage()));
		}
	}

}
