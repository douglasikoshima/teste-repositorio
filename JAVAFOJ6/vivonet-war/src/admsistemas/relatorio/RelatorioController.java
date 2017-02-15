package admsistemas.relatorio;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.RelatorioBlindagemVODocument.RelatorioBlindagemVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;

public class RelatorioController extends AbstractAction {

	private static final long serialVersionUID = 5355627381708934513L;

	protected global.Global globalApp = new global.Global();

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.relatorios.blindagem.RelBlindagem relBlindagemFacade;

	private RelatorioForm relatorioForm = new RelatorioForm();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("listarDados".equals(mapping.getParameter())) {
			return listarDados(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response){
		getRelatorioForm().setDtIniBlind(ConstantesCRM.SVAZIO);
		getRelatorioForm().setDtFimBlind(ConstantesCRM.SVAZIO);
		getRelatorioForm().setRelatorioBlindagemVO(RelatorioBlindagemVO.Factory.newInstance());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="relatorioDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response){
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	public RelatorioForm getRelatorioForm(){
		return relatorioForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward listarDados(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response){
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try{
			RelatorioForm form = (RelatorioForm) formParam;
			String dtIniBlind = form.getDtIniBlind();
			String dtFimBlind = form.getDtFimBlind();

			RelatorioBlindagemVO relatorio = relBlindagemFacade.getRelatorioBlindagem(user, dtIniBlind, dtFimBlind);
			getRelatorioForm().setDtIniBlind(dtIniBlind);
			getRelatorioForm().setDtFimBlind(dtFimBlind);
			getRelatorioForm().setRelatorioBlindagemVO(relatorio);

		}catch(Exception e){
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class RelatorioForm extends ActionForm{

		private static final long serialVersionUID = -826184522150274390L;

		private String dtFimBlind = ConstantesCRM.SVAZIO;
		private String dtIniBlind = ConstantesCRM.SVAZIO;
		private RelatorioBlindagemVO relatorioBlindagemVO = RelatorioBlindagemVO.Factory.newInstance();

		public void setDtIniBlind(String dtIniBlind){
			this.dtIniBlind = dtIniBlind;
		}

		public String getDtIniBlind(){
			return this.dtIniBlind;
		}

		public void setDtFimBlind(String dtFimBlind){
			this.dtFimBlind = dtFimBlind;
		}

		public String getDtFimBlind(){
			return this.dtFimBlind;
		}

		public RelatorioBlindagemVO getRelatorioBlindagemVO(){
			return this.relatorioBlindagemVO;
		}

		public void setRelatorioBlindagemVO(RelatorioBlindagemVO relatorioBlindagemVO){
			this.relatorioBlindagemVO = relatorioBlindagemVO;
		}
	}
}