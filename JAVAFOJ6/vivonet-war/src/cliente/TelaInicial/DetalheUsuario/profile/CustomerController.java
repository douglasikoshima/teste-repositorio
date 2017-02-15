package cliente.TelaInicial.DetalheUsuario.profile;

import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ManterDadosComportamentaisVODocument.ManterDadosComportamentaisVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.dadosComportamentais.DadosComportamentaisFacade;
import cliente.URLErro;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class CustomerController extends AbstractAction {

	private static final long serialVersionUID = 4701303621731911244L;

	@EJB
	private DadosComportamentaisFacade dadosFacadeControl;

	protected global.Global globalApp = new global.Global();

	private ManterDadosForm manterDadosForm;

	private String user = null;
	private String idPessoa = null;

	public ManterDadosForm getManterDadosForm() {
		return this.manterDadosForm;
	}

	public void setManterDadosForm(ManterDadosForm form) {
		this.manterDadosForm = form;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("loadOutrosDados".equals(mapping.getParameter())) {
			return loadOutrosDados(mapping, form, request, response);
		} else if ("salvar".equals(mapping.getParameter())) {
			return salvar(mapping, form, request, response);
		} else if ("loadAtributos".equals(mapping.getParameter())) {
			return loadAtributos(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="dadosComportamentais.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			// user = (String)request.getSession().getAttribute(ConstantesCRM.USUARIO_IDENTIFICADOR_SESSION);
			idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaUsuario();
			if (ConstantesCRM.SVAZIO.equals(idPessoa)) {
				idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
			}
			manterDadosForm = new ManterDadosForm();
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	private String ItensArvore(ManterDadosComportamentaisVO itens) {

		String node = ConstantesCRM.SVAZIO;
		if (itens.getAssuntosArray().length == 0) {
			return ConstantesCRM.SVAZIO;
		} else {
			for (int x = 0; x < itens.getAssuntosArray().length; x++) {
				node = node + "var assunto" + String.valueOf(x) + " = new WebFXTreeItem(\'" + StringUtilStatic.escapaComillasJS(itens.getAssuntosArray(x).getDsAssunto()) + "\');";
				for (int y = 0; y < itens.getAssuntosArray(x).getSubAssuntosArray().length; y++) {
					if (itens.getAssuntosArray(x).getSubAssuntosArray(0).getIdSubAssunto() != null) {
						node = node + " var " + "subassunto" + String.valueOf(y) + " = new WebFXTreeItem(\'" + StringUtilStatic.escapaComillasJS2(itens.getAssuntosArray(x).getSubAssuntosArray(y).getDsSubAssunto()) + "\',\"Javascript:idSubassuntoFun('" + itens.getAssuntosArray(x).getSubAssuntosArray(y).getIdSubAssunto() + "\');\"" + ");";
						node = node + "assunto" + String.valueOf(x) + ".add(" + "subassunto" + String.valueOf(y) + ");";
					}
				}

				node = node + "tree.add(" + "assunto" + String.valueOf(x) + ");";
			}
			node = node + "document.write(tree);";
			return node;
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="arvore.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadOutrosDados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			manterDadosForm = new ManterDadosForm();
			ManterDadosComportamentaisVO manterVO = ManterDadosComportamentaisVO.Factory.newInstance();
			String idGrupo = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdGrupo();
			manterVO = dadosFacadeControl.getManterDadosComportamentais(user, idPessoa, idGrupo);
			manterDadosForm.setArvore(this.ItensArvore(manterVO));
			request.getSession().setAttribute("arvore", manterDadosForm.getArvore());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="loadAtributos.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ManterDadosForm form = (ManterDadosForm) formParam;
			String idSubAssunto = request.getParameter("idSubAssunto");
			ManterDadosComportamentaisVO salvarDadosComportamentaisVO = ManterDadosComportamentaisVO.Factory.newInstance();
			salvarDadosComportamentaisVO.addNewAssuntos();
			salvarDadosComportamentaisVO.getAssuntosArray(0).addNewSubAssuntos();
			salvarDadosComportamentaisVO.getAssuntosArray(0).getSubAssuntosArray(0).setIdSubAssunto(idSubAssunto);
			salvarDadosComportamentaisVO.setIdPessoa(idPessoa);

			// SET´s PARA PREPARO DE VO PARA SALVAR
			for (int cont = 0; cont < form.getIdAtributo().length; cont++) {
				salvarDadosComportamentaisVO.addNewAtributos();
				salvarDadosComportamentaisVO.getAtributosArray(cont).setIdAtributo(form.getIdAtributo()[cont]);
				salvarDadosComportamentaisVO.getAtributosArray(cont).setIdTipoApresentacao(form.getIdTipoApresentacao()[cont]);
				if (form.getIdTipoApresentacao()[cont].equalsIgnoreCase(ConstantesCRM.SONE) || form.getIdTipoApresentacao()[cont].equalsIgnoreCase(ConstantesCRM.SFOUR)) {
					salvarDadosComportamentaisVO.getAtributosArray(cont).addIdValorSelecionado((request.getParameter(form.getIdAtributo()[cont])));
				} else if (form.getIdTipoApresentacao()[cont].equalsIgnoreCase(ConstantesCRM.STHREE) || form.getIdTipoApresentacao()[cont].equalsIgnoreCase(ConstantesCRM.SFIVE)) {
					salvarDadosComportamentaisVO.getAtributosArray(cont).addNewValorLivre();
					if (manterDadosForm.getManterDados().getAtributosArray(cont).getValorLivre() != null && manterDadosForm.getManterDados().getAtributosArray(cont).getValorLivre().getIdValorLivre() != null) {
						salvarDadosComportamentaisVO.getAtributosArray(cont).getValorLivre().setIdValorLivre(manterDadosForm.getManterDados().getAtributosArray(cont).getValorLivre().getIdValorLivre());
					}
					salvarDadosComportamentaisVO.getAtributosArray(cont).getValorLivre().setDsValorLivre(request.getParameter(form.getIdAtributo()[cont]));
				} else if (form.getIdTipoApresentacao()[cont].equalsIgnoreCase(ConstantesCRM.STWO)) {
					for (int j = 0; j < manterDadosForm.getAtributos()[cont].getValoresPossiveisArray().length; j++) {
						salvarDadosComportamentaisVO.getAtributosArray(cont).addIdValorSelecionado(request.getParameter(form.getIdAtributo()[cont] + j));
					}
				} else {
					Map map = request.getParameterMap();
					String[] vlLista = (String[]) map.get(form.getIdAtributo()[cont]);
					if (vlLista != null) {
						for (int z = 0; z < vlLista.length; z++) {
							salvarDadosComportamentaisVO.getAtributosArray(cont).addIdValorSelecionado(vlLista[z]);
						}
					} else {
						salvarDadosComportamentaisVO.getAtributosArray(cont).addIdValorSelecionado(ConstantesCRM.SVAZIO);
					}
				}
			}

			dadosFacadeControl.setSalvarManterDadosComportamentais(user, salvarDadosComportamentaisVO);
			request.setAttribute("idSubAssunto", idSubAssunto);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="perguntas.jsp"
	 * @jpf:forward name="framenulo" path="framenulo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward loadAtributos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ManterDadosForm form = (ManterDadosForm) formParam;
			if (request.getParameter("start") == null) {
				manterDadosForm = new ManterDadosForm();
				ManterDadosComportamentaisVO atributos = ManterDadosComportamentaisVO.Factory.newInstance();

				if (request.getParameter("idSubassunto") != null) {
					form.setIdSubAssunto(request.getParameter("idSubassunto"));
				} else {
					form.setIdSubAssunto(request.getAttribute("idSubAssunto").toString());
				}

				atributos = dadosFacadeControl.getAtributosDadosComportamentais(user, idPessoa, form.getIdSubAssunto(), ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdGrupo());

				if (atributos.getAtributosArray() != null && atributos.getAtributosArray().length == 0) {
					atributos.addNewAtributos();
				}
				if (atributos.getAtributosArray() != null && atributos.getAtributosArray(0).getValoresPossiveisArray().length == 0) {
					atributos.getAtributosArray(0).addNewValoresPossiveis();
				}
				if (atributos.getAtributosArray() != null && atributos.getAtributosArray(0).getValorLivre() == null) {
					atributos.getAtributosArray(0).addNewValorLivre();
				}

				manterDadosForm.setManterDados(atributos);
				manterDadosForm.setAtributos(atributos.getAtributosArray());
				manterDadosForm.setIdSubAssunto(form.getIdSubAssunto());

				if (manterDadosForm.getAtributos().length > 0 && manterDadosForm.getAtributos()[0].getIdAtributo() != null) {
					request.setAttribute("manterDadosForm", manterDadosForm);
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				} else {
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("framenulo");
				}

			} else {
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("framenulo");
			}
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	public static class ManterDadosForm extends ActionForm {

		private static final long serialVersionUID = 8641088623463609954L;

		private String[] idValorPossivel;
		private String[] idTipoApresentacao;
		private String[] idAtributo;
		private ManterDadosComportamentaisVO manterDados;
		private String idSubAssunto;
		private String arvore;
		private ManterDadosComportamentaisVO.Atributos[] atributos;

		public ManterDadosForm() {
			arvore = new String();
			atributos = new ManterDadosComportamentaisVO.Atributos[0];
			idSubAssunto = new String();
			manterDados = ManterDadosComportamentaisVO.Factory.newInstance();
		}

		public void setAtributos(ManterDadosComportamentaisVO.Atributos[] atributos) {
			this.atributos = atributos;
		}

		public ManterDadosComportamentaisVO.Atributos[] getAtributos() {
			return this.atributos;
		}

		public void setArvore(String arvore) {
			this.arvore = arvore;
		}

		public String getArvore() {
			return this.arvore;
		}

		public void setIdSubAssunto(String idSubAssunto) {
			this.idSubAssunto = idSubAssunto;
		}

		public String getIdSubAssunto() {
			return this.idSubAssunto;
		}

		public void setManterDados(ManterDadosComportamentaisVO manterDados) {
			this.manterDados = manterDados;
		}

		public ManterDadosComportamentaisVO getManterDados() {
			return this.manterDados;
		}

		public void setIdAtributo(String[] idAtributo) {
			this.idAtributo = idAtributo;
		}

		public String[] getIdAtributo() {
			return this.idAtributo;
		}

		public void setIdTipoApresentacao(String[] idTipoApresentacao) {
			this.idTipoApresentacao = idTipoApresentacao;
		}

		public String[] getIdTipoApresentacao() {
			return this.idTipoApresentacao;
		}

		public void setIdValorPossivel(String[] idValorPossivel) {
			this.idValorPossivel = idValorPossivel;
		}

		public String[] getIdValorPossivel() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.idValorPossivel == null || this.idValorPossivel.length == 0) {
				this.idValorPossivel = new String[1];
			}

			return this.idValorPossivel;
		}
	}

}
