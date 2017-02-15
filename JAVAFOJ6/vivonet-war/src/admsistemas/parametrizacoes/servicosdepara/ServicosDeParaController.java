package admsistemas.parametrizacoes.servicosdepara;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import admsistemas.parametrizacoes.servicosdepara.formbeans.ServicosDeParaForm;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.admsistemas.crud.dbclasses.ServicosDePara;

import com.indracompany.actions.AbstractAction;

public class ServicosDeParaController extends AbstractAction {

	private static final long serialVersionUID = 4943779058864324605L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.crud.servicosdepara.ServicosDePara servicosDeParaControl;

	private ServicosDeParaForm servicosDeParaForm;
	public ServicosDeParaForm getServicosDeParaForm() {
		if (this.servicosDeParaForm == null) {
			this.servicosDeParaForm = new ServicosDeParaForm();
		}
		return this.servicosDeParaForm;
	}

	public void setServicosDeParaForm(ServicosDeParaForm servicosDeParaForm) {
		this.servicosDeParaForm = servicosDeParaForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("carregarServico".equals(mapping.getParameter())) {
			return carregarServico(mapping, form, request, response);
		} else if ("salvarServico".equals(mapping.getParameter())) {
			return salvarServico(mapping, form, request, response);
		} else if ("excluirServico".equals(mapping.getParameter())) {
			return excluirServico(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {
		ServicosDePara[] listaServicos = servicosDeParaControl.buscarServicos();
		getServicosDeParaForm().setListaServicos(listaServicos);

		if (request.getParameter("msg") != null) {
			request.setCharacterEncoding(ConstantesCRM.SUTF8);
			request.setAttribute("msg", request.getParameter("msg"));
		}
		response.setCharacterEncoding(ConstantesCRM.SUTF8);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="read.jsp"
	 */
	public ActionForward carregarServico(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {

		ServicosDeParaForm form = (ServicosDeParaForm) formParam;
		if (form.getIdPlano() != 0) {
			ServicosDePara servico = servicosDeParaControl.buscarServico(form.getIdPlano());
			getServicosDeParaForm().setAtivo(servico.getInAtivo() == 1 ? true : false);
			getServicosDeParaForm().setCdPlano(servico.getCdPlano());
			getServicosDeParaForm().setCdPlanoAtlys(servico.getCdPlanoAtlys());
			getServicosDeParaForm().setIdPlano(servico.getIdPlano());
			getServicosDeParaForm().setDsPlano(servico.getDsPlano());
		} else {
			getServicosDeParaForm().setAtivo(false);
			getServicosDeParaForm().setCdPlano(ConstantesCRM.SVAZIO);
			getServicosDeParaForm().setCdPlanoAtlys(ConstantesCRM.SVAZIO);
			getServicosDeParaForm().setIdPlano(0);
			getServicosDeParaForm().setDsPlano(ConstantesCRM.SVAZIO);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="read" path="read.jsp"
	 */
	public ActionForward salvarServico(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {

		ServicosDeParaForm form = (ServicosDeParaForm) formParam;
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		f.addParameter("msg", ConstantesCRM.SSucesso);

		ServicosDePara servico = new ServicosDePara();
		servico.setIdPlano(form.getIdPlano());
		servico.setCdPlano(form.getCdPlano());
		servico.setCdPlanoAtlys(form.getCdPlanoAtlys());
		servico.setDsPlano(form.getDsPlano());
		servico.setInAtivo(form.isAtivo() ? 1 : 0);

		try {
			servicosDeParaControl.salvarServico(servico);
		} catch (Exception e) {
			f.setName("read");
			request.setAttribute("msg", e.getMessage());
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	public ActionForward excluirServico(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {

		ServicosDeParaForm form = (ServicosDeParaForm) formParam;

		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		f.addParameter("msg", ConstantesCRM.SSucesso);

		servicosDeParaControl.excluirServico(form.getIdPlano());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}
}
