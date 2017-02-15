package tracking.PrevisaoEntrega;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.tracking.previsaoentrega.PrevisaoEntrega;
import br.com.vivo.fo.tracking.vo.TrackingPrevisaoEntregaVODocument.TrackingPrevisaoEntregaVO;

import com.indracompany.actions.AbstractAction;

public class PrevisaoEntregaController extends AbstractAction {

	private static final long serialVersionUID = -8418776697607926153L;

	@EJB
	private PrevisaoEntrega previsaoEntrega;

	protected global.Global globalApp = new global.Global();

	private TrackingForm trackingForm;

	public TrackingForm getTrackingForm(){
		if(this.trackingForm==null){
			this.trackingForm = new TrackingForm();
		}
		return this.trackingForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("gravar".equals(mapping.getParameter())) {
			return gravar(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		TrackingPrevisaoEntregaVO tracking = null;
		try{
			tracking = TrackingPrevisaoEntregaVO.Factory.newInstance();
			tracking.setTpOperacao("select");
			tracking.addNewFiltro().addNewCombos().addNmSelect("UF");
			tracking.getFiltro().getCombos().addNmSelect("SO");

			tracking = previsaoEntrega.getTRACK(user, tracking);

			for(int i=0;i<tracking.getCombos().getListaArray().length;i++){
				if("UF".equals(tracking.getCombos().getListaArray(i).getNmSelect())){
					getTrackingForm().setComboUF(tracking.getCombos().getListaArray(i));
				}else if("SO".equals(tracking.getCombos().getListaArray(i).getNmSelect())){
					getTrackingForm().setComboSO(tracking.getCombos().getListaArray(i));
				}
			}
		}catch(Exception e){
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){
		TrackingForm form = (TrackingForm)formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		TrackingPrevisaoEntregaVO tracking = null;
		String id = form.getTrackingPEVO().getFiltro().getIdSO();
		String uf = form.getTrackingPEVO().getFiltro().getSgUF();
		try{
			tracking = TrackingPrevisaoEntregaVO.Factory.newInstance();
			tracking.setTpOperacao("pesquisar");
			tracking.addNewFiltro().setIdSO(id);
			tracking.getFiltro().setSgUF(uf);

			tracking = previsaoEntrega.getTRACK(user, tracking);

			getTrackingForm().setTrackingPEVO(tracking);
			getTrackingForm().getTrackingPEVO().getFiltro().setIdSO(id);
			getTrackingForm().getTrackingPEVO().getFiltro().setSgUF(uf);

		}catch(Exception e){
			getTrackingForm().getTrackingPEVO().getFiltro().setIdSO(id);
			getTrackingForm().getTrackingPEVO().getFiltro().setSgUF(uf);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisar.do"
	 */
	protected ActionForward gravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response){
		TrackingForm form = (TrackingForm)formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		TrackingPrevisaoEntregaVO tracking = null;
		try{
			tracking = TrackingPrevisaoEntregaVO.Factory.newInstance();
			tracking.setTpOperacao("gravar");
			tracking.addNewCampos().set(form.getTrackingPEVO().getCampos().copy());

			tracking = previsaoEntrega.setTRACK(user, tracking);

			getTrackingForm().setTrackingPEVO(tracking);
			getTrackingForm().getTrackingPEVO().getFiltro().setIdSO(form.getTrackingPEVO().getCampos().getIdSO());
			getTrackingForm().getTrackingPEVO().getFiltro().setSgUF(form.getTrackingPEVO().getCampos().getSgUF());
		}catch(Exception e){
			getTrackingForm().getTrackingPEVO().getFiltro().setIdSO(form.getTrackingPEVO().getCampos().getIdSO());
			getTrackingForm().getTrackingPEVO().getFiltro().setSgUF(form.getTrackingPEVO().getCampos().getSgUF());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class TrackingForm extends ActionForm {

		private TrackingPrevisaoEntregaVO trackingPrevisaoEntregaVO;
		private TrackingPrevisaoEntregaVO.Combos.Lista comboSO;
		private TrackingPrevisaoEntregaVO.Combos.Lista comboUF;

		public TrackingForm(){
			this.trackingPrevisaoEntregaVO = TrackingPrevisaoEntregaVO.Factory.newInstance();
			this.trackingPrevisaoEntregaVO.addNewFiltro();
		}

		public void setComboSO(TrackingPrevisaoEntregaVO.Combos.Lista comboSO){
			this.comboSO = comboSO;
		}

		public TrackingPrevisaoEntregaVO.Combos.Lista getComboSO(){
			return this.comboSO;
		}

		public void setComboUF(TrackingPrevisaoEntregaVO.Combos.Lista comboUF){
			this.comboUF = comboUF;
		}

		public TrackingPrevisaoEntregaVO.Combos.Lista getComboUF(){
			return this.comboUF;
		}

		public void setTrackingPEVO(TrackingPrevisaoEntregaVO trackingPrevisaoEntregaVO){
			this.trackingPrevisaoEntregaVO = trackingPrevisaoEntregaVO;
		}

		public TrackingPrevisaoEntregaVO getTrackingPEVO(){
			if(this.trackingPrevisaoEntregaVO == null){
				this.trackingPrevisaoEntregaVO = TrackingPrevisaoEntregaVO.Factory.newInstance();
			}
			if(this.trackingPrevisaoEntregaVO.getFiltro()==null){
				this.trackingPrevisaoEntregaVO.addNewFiltro();
			}
			if(this.trackingPrevisaoEntregaVO.getCampos()==null){
				this.trackingPrevisaoEntregaVO.addNewCampos();
			}
			if(this.trackingPrevisaoEntregaVO.getTabela()==null){
				this.trackingPrevisaoEntregaVO.addNewTabela();
			}
			return this.trackingPrevisaoEntregaVO;
		}
	}
}
