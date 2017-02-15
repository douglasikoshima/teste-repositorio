package fidelizacao.Manter.ManterDefDestino;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.manter.ManterDefinicaoFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterAlteraVODocument.FidelizacaoManterAlteraVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterDelVODocument.FidelizacaoManterDelVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterInVODocument.FidelizacaoManterInVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoManterVODocument.FidelizacaoManterVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterDefDestinoController extends AbstractAction {

	@EJB
	private ManterDefinicaoFacade destinoFac;

	public ArrayList lista = new ArrayList();
	public String destino = ConstantesCRM.SVAZIO;
	private static transient Logger log = new Logger("Fidelizacao");

	private boolean editar = false;

	protected global.Global globalApp = new global.Global();

	protected boolean alwaysTrackPreviousPage() {
		return true;
	}

	protected boolean alwaysTrackPreviousAction() {
		return true;
	}

	private String idUsuario;

	/**
	 * Retorna usuário logado no sistema
	 * 
	 * @return String
	 */
	private String getIdUsuario(HttpServletRequest request) {
		// verifica se a variável está populada
		if (this.idUsuario == null) {
			this.idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		}
		// retorna usuário
		return this.idUsuario;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("ListaDefDestino".equals(mapping.getParameter())) {
			return ListaDefDestino(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterDefDestino.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	protected void carregaLista(String texto, HttpServletRequest request) throws Exception {

		lista.clear();
		FidelizacaoListaGeralVO listaVO = null;

		try {

			ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			FidelizacaoManterVO fidelizacaoManterVO = FidelizacaoManterVO.Factory.newInstance();
			fidelizacaoManterVO.setTexto(texto);
			fidelizacaoManterVO.setIdperg(new String(ConstantesCRM.STWO));
			fidelizacaoManterVO.setIdTipoLinha(ConstantesCRM.SONE);

			listaVO = destinoFac.getDescricao(getIdUsuario(request), fidelizacaoManterVO);
			for (int i = 0; i < listaVO.getItemListaVOArray().length; i++) {
				String[] temp = { listaVO.getItemListaVOArray(i).getId(), listaVO.getItemListaVOArray(i).getDescricao() };
				lista.add(temp);

			}
		} catch (Exception e) {
			String s = e.getMessage();
			throw e;
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="sair" path="/fidelizacao/begin.do"
	 * @jpf:forward name="success" path="ManterDefDestino.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="success1" path="erroMatriz.jsp"
	 * @jpf:forward name="success2" path="erro.jsp"
	 */
	protected ActionForward ListaDefDestino(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ListaDefDestinoForm form = (ListaDefDestinoForm) formParam;
		String resposta = null;
		String direcao = ConstantesCRM.SUCCESS;

		try {

			// O USUARIO CLICOU NO BOTÃO EXCLUIR
			if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("excluir")) {

				String[] temp = (String[]) lista.get(Integer.parseInt(request.getParameter("valor")));
				try {
					/*
					 * O Flag 1 é para indicar ao serviço que o id a ser
					 * excluido é o de destino porque para intenção é o mesmo
					 * serviço*
					 */
					FidelizacaoManterDelVO fidelizacaoManterDelVO = FidelizacaoManterDelVO.Factory.newInstance();
					fidelizacaoManterDelVO.setId(temp[0]);
					fidelizacaoManterDelVO.setOperacao(new String(ConstantesCRM.SONE));
					/********
					 * ppaula
					 */
					RetornoVO retornoVO = destinoFac.delDescricao(getIdUsuario(request), fidelizacaoManterDelVO);
					if ("-1".equals(retornoVO.getValor())) {
						form.setInMsgRetorno("true");
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward("success1");
					}
					/******
					 * fim ppaula
					 */

				} catch (Exception e) {
					throw e;
				}
				carregaLista("*", request);

				// O USUARIO CLICOU NO BOTÃO EDITAR
			} else if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("editar")) {

				String temp[] = (String[]) lista.get(Integer.parseInt(request.getParameter("valor")));
				form.setDescricao(temp[1].trim());
				form.setId(temp[0]);
				lista.remove(Integer.parseInt(request.getParameter("valor")));
				editar = true;

				// O USUARIO CLICOU NO BOTÃO SALVAR DO DIV
			} else if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("ok")) {
				if (form.getDescricao() == null || form.getDescricao().equals("")) {
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(direcao);
				}
				// Testa se ja existe a descrição
				for (int i = 0; i < lista.size(); i++) {
					String lAux[] = (String[]) lista.get(i);

					if (lAux[1].equalsIgnoreCase(form.getDescricao().trim())) {
						form.setInMsgRetorno("true");
						carregaLista("*", request);
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward("success2");
					}
				}

				if (editar == true) {

					FidelizacaoManterAlteraVO fidelizacaoManterAlteraVO = FidelizacaoManterAlteraVO.Factory.newInstance();
					String[] dest = new String[form.getDestinos().length];
					dest = form.getDestinos();
					fidelizacaoManterAlteraVO.setId(dest[0]);
					fidelizacaoManterAlteraVO.setDescricao(dest[1]);

					destinoFac.setDescricao(getIdUsuario(request), fidelizacaoManterAlteraVO);
					editar = false;
				} else {
					try {

						FidelizacaoManterInVO fidelizacaoManterInVO = FidelizacaoManterInVO.Factory.newInstance();
						fidelizacaoManterInVO.setIdperg(new String(ConstantesCRM.STWO));
						fidelizacaoManterInVO.setDescricao(form.getDescricao());
						destinoFac.addDescricao(getIdUsuario(request), fidelizacaoManterInVO);
					} catch (Exception e) {
						throw e;
					}
				}
				form.setDescricao(ConstantesCRM.SVAZIO);
				carregaLista("*", request);

				// O USUARIO CLICOU NO BOTÃO CANCELAR
			} else if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("cancelar")) {
				if (editar == true) {
					lista.add(form.getDestinos());
				}
				form.setDescricao(null);

				// O USUARIO CLICOU NO BOTÃO CONSULTAR
			} else if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("consultar")) {
				String texto = "*";
				if (form.getDescricao().length() > 0 || !form.getDescricao().equalsIgnoreCase(ConstantesCRM.SVAZIO)) {
					texto = form.getDescricao();
				}

				carregaLista(form.getDescricao(), request);

			} else if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("sair")) {
				direcao = "sair";
			}

		} catch (TuxedoWarningException twe) {
			log.warn("ManterDefDestinoController:ListaDefDestino(" + twe.getMessage() + ")");
		} catch (Exception e) {
			// Observação: Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(direcao);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class ListaDefDestinoForm extends ActionForm {
		private String inMsgRetorno;

		private String Descricao;

		private String[] destinos;

		private String descricao;

		private String id;

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return this.descricao;
		}

		public void setDestinos(String[] destinos) {
			this.destinos = destinos;
		}

		public String[] getDestinos() {
			this.destinos = new String[2];
			destinos[0] = this.id;
			destinos[1] = this.descricao;
			return this.destinos;
		}

		public void setInMsgRetorno(String inMsgRetorno) {
			this.inMsgRetorno = inMsgRetorno;
		}

		public String getInMsgRetorno() {
			return this.inMsgRetorno;
		}
	}
}
