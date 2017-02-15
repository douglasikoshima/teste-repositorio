package admsistemas.admArvoreContato.workflow.abaDadosBasicosW;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmDadosBasicosWVODocument.AdmDadosBasicosWVO;
import br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoRetornoContatoVODocument.AdmTipoRetornoContatoVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

public class AbaDadosBasicosWController extends AbstractAction {

	private static final long serialVersionUID = 7207347805686661561L;

	public global.Global globalApp = new global.Global();

	private FormDadosBasicosW formDadosBasicosW;

	private String user = ConstantesCRM.SVAZIO;

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvaDadosBasicosW".equals(mapping.getParameter())) {
			return salvaDadosBasicosW(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularDadosBasicosW.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AbaDadosBasicosWController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		formDadosBasicosW = new FormDadosBasicosW();
		FormDadosBasicosW form = (FormDadosBasicosW) formParam;
		// id=request.getParameter("idContato");
		// formDadosBasicosW.setIdContato(id);

		if (request.getParameter("idContato") != null) {
			formDadosBasicosW.setIdContato(request.getParameter("idContato"));
		} else {
			formDadosBasicosW.setIdContato(form.getIdContato());
		}

		AdmIdContatoVO admIdContatoVO = AdmIdContatoVO.Factory.newInstance();

		admIdContatoVO.setIdContato(formDadosBasicosW.getIdContato());

		AdmDadosBasicosWVO admDadosBasicosWVO = AdmDadosBasicosWVO.Factory.newInstance();

		user = ConstantesCRM.getCurrentUser(request.getSession());
		// admDadosBasicosWVO = controlDadosBasicosW.montaDadosBasicosW(admIdContatoVO,user);

		montaDadosBasicosW(admDadosBasicosWVO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void montaDadosBasicosW(AdmDadosBasicosWVO admDadosBasicosWVO) {

		formDadosBasicosW.setQtDiasPrazoContato(admDadosBasicosWVO.getQtDiasPrazoContato());
		formDadosBasicosW.setVlPesoContato(admDadosBasicosWVO.getVlPesoContato());
		formDadosBasicosW.setTipoRetornoContatoVO(admDadosBasicosWVO.getTipoRetornoContato().getAdmTipoRetornoContatoVOArray());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularDadosBasicosW.jsp"
	 */
	public ActionForward salvaDadosBasicosW(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AbaDadosBasicosWController:salvaDadosBasicosW" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormDadosBasicosW form = (FormDadosBasicosW) formParam;
		AdmDadosBasicosWVO admDadosBasicosWVO = AdmDadosBasicosWVO.Factory.newInstance();
		AdmDadosBasicosWVO newAdmDadosBasicosWVO = AdmDadosBasicosWVO.Factory.newInstance();
		AdmTipoRetornoContatoVO[] admTipoRetornoContatoVO = new AdmTipoRetornoContatoVO[form.getArraytipoRetornoContato().length];
		String[] tipoRetornoContatoVO = new String[form.getArraytipoRetornoContato().length];
		tipoRetornoContatoVO = form.getArraytipoRetornoContato();

		for (int i = 0; i < tipoRetornoContatoVO.length; i++) {
			admTipoRetornoContatoVO[i] = AdmTipoRetornoContatoVO.Factory.newInstance();
			admTipoRetornoContatoVO[i].setIdTipoRetornoContato(tipoRetornoContatoVO[i]);
		}

		admDadosBasicosWVO.setIdContato(form.getIdContato());
		admDadosBasicosWVO.setQtDiasPrazoContato(form.getQtDiasPrazoContato());
		admDadosBasicosWVO.addNewTipoRetornoContato().setAdmTipoRetornoContatoVOArray(admTipoRetornoContatoVO);
		admDadosBasicosWVO.setVlPesoContato(form.getVlPesoContato());

		user = ConstantesCRM.getCurrentUser(request.getSession());
		// newAdmDadosBasicosWVO = controlDadosBasicosW.salvaDadosBasicosW(admDadosBasicosWVO,user);

		limpaForm();
		montaDadosBasicosW(newAdmDadosBasicosWVO, form);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void limpaForm() {
		formDadosBasicosW = new FormDadosBasicosW();
	}

	private void montaDadosBasicosW(AdmDadosBasicosWVO admOperadorasVO, FormDadosBasicosW form) {
		formDadosBasicosW.setIdContato(form.getIdContato());
		formDadosBasicosW.setQtDiasPrazoContato(ConstantesCRM.SVAZIO);
		formDadosBasicosW.setVlPesoContato(ConstantesCRM.SVAZIO);
		formDadosBasicosW.setTipoRetornoContatoVO(admOperadorasVO.getTipoRetornoContato().getAdmTipoRetornoContatoVOArray());
	}

	public static class FormDadosBasicosW extends ActionForm {

		private static final long serialVersionUID = -8402158396236331760L;

		private AdmTipoRetornoContatoVO[] tipoRetornoContatoVO;
		private String[] arraytipoRetornoContato;
		private String vlPesoContato;
		private String qtDiasPrazoContato;
		private String idContato;

		public FormDadosBasicosW() {

		}

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setQtDiasPrazoContato(String qtDiasPrazoContato) {
			this.qtDiasPrazoContato = qtDiasPrazoContato;
		}

		public String getQtDiasPrazoContato() {
			return this.qtDiasPrazoContato;
		}

		public void setVlPesoContato(String vlPesoContato) {
			this.vlPesoContato = vlPesoContato;
		}

		public String getVlPesoContato() {
			return this.vlPesoContato;
		}

		public void setArraytipoRetornoContato(String[] arraytipoRetornoContato) {
			this.arraytipoRetornoContato = arraytipoRetornoContato;
		}

		public String[] getArraytipoRetornoContato() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arraytipoRetornoContato == null || this.arraytipoRetornoContato.length == 0) {
				this.arraytipoRetornoContato = new String[1];
			}

			return this.arraytipoRetornoContato;
		}

		public void setTipoRetornoContatoVO(AdmTipoRetornoContatoVO[] tipoRetornoContatoVO) {
			this.tipoRetornoContatoVO = tipoRetornoContatoVO;
		}

		public AdmTipoRetornoContatoVO[] getTipoRetornoContatoVO() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize tipoRetornoContatoVO if it is null.
			// if(this.tipoRetornoContatoVO == null)
			// {
			// this.tipoRetornoContatoVO = new AdmTipoRetornoContatoVO(?);
			// }

			return this.tipoRetornoContatoVO;
		}
	}

	public FormDadosBasicosW getFormDadosBasicosW() {
		return this.formDadosBasicosW;
	}

	public boolean alwaysTrackPreviousPage() {
		return true;
	}

	public boolean alwaysTrackPreviousAction() {
		return true;
	}

}
