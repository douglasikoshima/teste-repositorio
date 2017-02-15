package cliente.servicos.Bloqueio;

import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.ativacaoServico.AtivacaoServicoFacade;
import br.com.vivo.fo.exception.FacadeException;

import com.indracompany.actions.AbstractAction;

@SuppressWarnings({"rawtypes","unchecked"})
public class BloqueioController extends AbstractAction {

	private static final long serialVersionUID = -3097640004110821164L;

	protected global.Global globalApp = new global.Global();
	
	private BloquearForm bloquearForm = new BloquearForm();

	public BloquearForm getBloquearForm() {
		return bloquearForm;
	}

	public void setBloquearForm(BloquearForm bloquearForm) {
		this.bloquearForm = bloquearForm;
	}

	@EJB
	private AtivacaoServicoFacade ativacaoServicoFacadeImpl;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("bloquear".equals(mapping.getParameter())) {
			return bloquear(mapping, form, request, response);
		} else if ("carregaForm".equals(mapping.getParameter())) {
			return carregaForm(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="msgSucesso.jsp"
	 */
	public ActionForward bloquear(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws FacadeException {
		BloquearForm form = (BloquearForm) formParam;
		int idTipoLinha = Integer.parseInt(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoLinha());
		String nrLinha = request.getParameter("nrLinha");
		ativacaoServicoFacadeImpl.block(nrLinha, form.getMotivo()[0], form.getData() + " " + form.getHora(), idTipoLinha);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="bloquearPage1.jsp"
	 */
	public ActionForward carregaForm(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		BloquearForm form = (BloquearForm) formParam;
		form.setMotivos(new HashMap());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class BloquearForm extends ActionForm {

		private static final long serialVersionUID = 6932072369994029543L;

		private HashMap motivos = new HashMap();
		private String hora;
		private String data;
		private String[] motivo;

		public void setMotivo(String[] motivo) {
			this.motivo = motivo;
		}

		public String[] getMotivo() {
			return this.motivo;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getData() {
			return this.data;
		}

		public void setHora(String hora) {
			this.hora = hora;
		}

		public String getHora() {
			return this.hora;
		}

		public void setMotivos(HashMap motivos) {
			// this.motivos = motivos;
			this.motivos.put("1", "A pedido do cliente");
			this.motivos.put("2", "Inadimplência");
			this.motivos.put("3", "Perda");
			this.motivos.put("4", "Procedimento Interno");
			this.motivos.put("21", "Assistência Técnica");
			this.motivos.put("25", "Roubo/Furto");
			this.motivos.put("66", "Cliente não cadastrado");
		}

		public HashMap getMotivos() {
			return this.motivos;
		}
	}
}
