package admsistemas.parametrizacoes.parametrosSistema;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.dbclasses.ApoioParametro;

import com.indracompany.actions.AbstractAction;

public class ParametrosSistemaController extends AbstractAction {

	private static final long serialVersionUID = -6369894646091173306L;

	protected global.Global globalApp = new global.Global();

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.crud.apoioparametro.ApoioParametro apoioParametroControl;

	private ParametrosForm parametrosForm;

	public ParametrosForm getParametrosForm() {
		if (this.parametrosForm == null) {
			this.parametrosForm = new ParametrosForm();
		}
		return this.parametrosForm;
	}

	public void setServicosDeParaForm(ParametrosForm arg) {
		this.parametrosForm = arg;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("buscarParametros".equals(mapping.getParameter())) {
			return buscarParametros(mapping, form, request, response);
		} else if ("salvarParametro".equals(mapping.getParameter())) {
			return salvarParametro(mapping, form, request, response);
		} else{
			return begin(mapping, form, request, response);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) {

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="listaParametros.jsp"
	 */
	public ActionForward buscarParametros(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) {

		try {
			String letraInicial = request.getParameter("letraInicial");
			ApoioParametro[] arrayParametros = apoioParametroControl.buscarParametrosPorLetra(letraInicial);
			getParametrosForm().setListaParametros(arrayParametros);

		} catch (Exception e) {
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward salvarParametro(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) {

		String verbo = ConstantesCRM.SVAZIO;

		try {
			ParametrosForm form = (ParametrosForm) formParam;
			String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());

			long idParametro = form.getParametro().getIdParametro();
			String cdParametro = form.getParametro().getCdParametro();
			String dsParametro = form.getParametro().getDsParametro();
			String vlParametro = form.getParametro().getDsValorParametro();

			verbo = (idParametro == 0) ? "cadastrado" : "alterado";

			ApoioParametro parametro = new ApoioParametro();
			parametro.setIdParametro(idParametro);
			parametro.setCdParametro(cdParametro);
			parametro.setDsParametro(dsParametro);
			parametro.setDsValorParametro(vlParametro);

			apoioParametroControl.salvarParametro(parametro, Long.parseLong(idUsuario));

			request.setAttribute("msgRetorno", "Parâmetro " + verbo + " com sucesso.");
			if (request.getParameter("letraSolicitada") != null && idParametro != 0) {
				request.setAttribute("letraSolicitada", request.getParameter("letraSolicitada"));
			}

		} catch (Exception e) {
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	public static class ParametrosForm extends ActionForm {

		private static final long serialVersionUID = 8171474486791896839L;

		private ApoioParametro parametro;
		private ApoioParametro[] listaParametros;

		public ApoioParametro getParametro() {
			if (this.parametro == null) {
				this.parametro = new ApoioParametro();
			}
			return this.parametro;
		}

		public void setParametro(ApoioParametro arg) {
			this.parametro = arg;
		}

		public ApoioParametro[] getListaParametros() {
			if (this.listaParametros == null) {
				this.listaParametros = new ApoioParametro[0];
			}
			return this.listaParametros;
		}

		public void setListaParametros(ApoioParametro[] arg) {
			this.listaParametros = arg;
		}
	}
}
