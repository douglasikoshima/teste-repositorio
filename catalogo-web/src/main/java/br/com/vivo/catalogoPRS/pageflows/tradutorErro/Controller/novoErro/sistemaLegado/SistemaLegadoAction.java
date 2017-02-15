package br.com.vivo.catalogoPRS.pageflows.tradutorErro.Controller.novoErro.sistemaLegado;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.framework.coordinator.Transaction;
import com.framework.exception.DAOException;

import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.SistemaLegadoForm;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.HistoricoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.SistemaLegadoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.SistemaLegadoVO;

//@Jpf.Controller()
public class SistemaLegadoAction extends BaseMappingDispatchAction {
	private static final long serialVersionUID = 1L;

	private static SistemaLegadoDAO instance = (SistemaLegadoDAO) SistemaLegadoDAO
			.getInstance();

	private static HistoricoDAO historicoDAO = (HistoricoDAO) HistoricoDAO
			.getInstance();

//	@Control
	//private PlanoServiceControl planoServiceControl;

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "sistemaLegado.jsp") })
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward("success");
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaSistemaLegado.jsp") })
	public ActionForward pesquisarSL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws CatalogoPRSException {
		SistemaLegadoForm sistemaLegadoForm = (SistemaLegadoForm)form;
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("SQLORD", "CD_SISTEMA_LEGADO");
		params.put("SQLDIR", "ASC");

		if (sistemaLegadoForm.getCdSistemaLegado() != null) {
			params.put("CD_SISTEMA_LEGADO = ", sistemaLegadoForm.getCdSistemaLegado().toString());
		}

		if (!sistemaLegadoForm.getDsSistemaLegado().trim().equals("")) {
			params.put("UPPER(DS_SISTEMA_LEGADO) like '"
				+ sistemaLegadoForm.getDsSistemaLegado().replaceAll("\\*", "%").toUpperCase() + "%'", "");
		}

		try {
			List<SistemaLegadoVO> sistemaLegadoVO = (List<SistemaLegadoVO>) instance.findByParams(params);
			sistemaLegadoForm.setListaSistemaLegado(sistemaLegadoVO);
		} catch (Exception e) {
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao listar Sistema Legado.");
			this.CatalogoPRSExceptionHandler(ex, SistemaLegadoAction.class.getName(), response);
			return null;
		}

		//HttpServletRequest request = this.getRequest();
		request.setAttribute("totalRegistros", sistemaLegadoForm.getListaSistemaLegado().size());
		//request.setAttribute("totalPagina", sistemaLegadoForm.getSistemaLegadoFormBean().getFooterPages().size());

		//Forward forward = new Forward("success");
		return mapping.findForward("success");
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupNovo.jsp") })
	public ActionForward abrirNovoSL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//Forward forward = new Forward("success");
		return mapping.findForward("success");
	}

	//@Jpf.Action()
	public ActionForward novoSL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {
		SistemaLegadoForm sistemaLegadoForm = (SistemaLegadoForm)form;
		Transaction tx = new Transaction();
		//HistoricoVO historicoVO = new HistoricoVO();
		SistemaLegadoVO sistemaLegadoVO = new SistemaLegadoVO();
		
		if(sistemaLegadoForm.getCdSistemaLegado() != null && !sistemaLegadoForm.getCdSistemaLegado().equals("")){
			sistemaLegadoVO.setCdSistemaLegado(sistemaLegadoForm.getCdSistemaLegado());
		}
		
		if(sistemaLegadoForm.getDsSistemaLegado() != null && !sistemaLegadoForm.getDsSistemaLegado().equals("")){
			sistemaLegadoVO.setDsSistemaLegado(sistemaLegadoForm.getDsSistemaLegado());
		}
		
		if(sistemaLegadoForm.getNmAplicacaoHeader() != null && !sistemaLegadoForm.getNmAplicacaoHeader().equals("")){
			sistemaLegadoVO.setNmAplicacaoHeader(sistemaLegadoForm.getNmAplicacaoHeader());
		}
		
		try {
			
			tx.beginTrans();

			instance.insert(sistemaLegadoVO, tx);

			//UserCatalogo attribute = (br.com.vivo.catalogoPRS.bo.UserCatalogo) this.getLoggedUser();
			
			//String name = attribute.getUser().getUsername();

			//historicoVO.setLogin(name);
			//historicoVO.setTabela("CSRV_SISTEMA_LEGADO");
			//historicoVO.setDsScript(sistemaLegadoForm.getSistemaLegadoFormBean().getSistemaLegadoVO().getExtraFields()
			//		.getString("SQL"));

			//historicoDAO.insert(historicoVO, tx);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao inserir Sistema Legado.");
			this.CatalogoPRSExceptionHandler(ex, SistemaLegadoAction.class.getName(), response);
			return null;
		}
		
		//ANALISAR
		//request.setAttribute("SQLNOVOSL", historicoVO.getDsScript());
		
		try {
			//HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = response.getWriter();
			out.println("$(bot_hidden).onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupAlterar.jsp") })
	public ActionForward abrirAlterarSL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String cdSL = request.getParameter("cd_sl");
		String dsSL = request.getParameter("ds_sl");
		request.setAttribute("cd_sistema_legado", cdSL);
		request.setAttribute("ds_sistema_legado", dsSL);
		
		return mapping.findForward("success");
	}

	//@Jpf.Action()
	public ActionForward alterarSL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws CatalogoPRSException {
		SistemaLegadoForm sistemaLegadoForm = (SistemaLegadoForm)form;
		Transaction tx = new Transaction();
		//HistoricoVO historicoVO = new HistoricoVO();
		SistemaLegadoVO sistemaLegadoVO = new SistemaLegadoVO();
		
		if(sistemaLegadoForm.getCdSistemaLegado() != null && !sistemaLegadoForm.getCdSistemaLegado().equals("")){
			sistemaLegadoVO.setCdSistemaLegado(sistemaLegadoForm.getCdSistemaLegado());
		}
		
		if(sistemaLegadoForm.getDsSistemaLegado() != null && !sistemaLegadoForm.getDsSistemaLegado().equals("")){
			sistemaLegadoVO.setDsSistemaLegado(sistemaLegadoForm.getDsSistemaLegado());
		}
		
		try {

			tx.beginTrans();

			instance.update(sistemaLegadoVO, tx);

			//UserCatalogo attribute = this.getLoggedUser();

			//String name = attribute.getUser().getUsername();

			//historicoVO.setLogin(name);
			//historicoVO.setTabela("CSRV_SISTEMA_LEGADO");
			//historicoVO.setDsScript(sistemaLegadoForm.getSistemaLegadoFormBean().getSistemaLegadoVO().getExtraFields().getString("SQL"));

			//historicoDAO.insert(historicoVO, tx);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao alterar Sistema Legado.");
			this.CatalogoPRSExceptionHandler(ex, SistemaLegadoAction.class.getName(), response);
			return null;
		}

		//ANALISAR
		//request.setAttribute("SQLALTERASL", historicoVO.getDsScript());
		try {
			//HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = response.getWriter();
			out.println("$(bot_hidden).onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupExcluir.jsp") })
	public ActionForward abrirExcluirSL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws DAOException {

		//HttpServletRequest request = getRequest();

		request.setAttribute("cdSistemaLegado", request.getParameter("cd_sl"));

		//Forward forward = new Forward("success");
		return mapping.findForward("success");
	}

	//@Jpf.Action()
	public ActionForward excluirSL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws CatalogoPRSException {
		SistemaLegadoForm sistemaLegadoForm = (SistemaLegadoForm)form;
		Transaction tx = new Transaction();
		//HistoricoVO historicoVO = new HistoricoVO();
		SistemaLegadoVO sistemaLegadoVO = new SistemaLegadoVO();
		
		if(sistemaLegadoForm.getCdSistemaLegado() != null && !sistemaLegadoForm.getCdSistemaLegado().equals("")){
			sistemaLegadoVO.setCdSistemaLegado(sistemaLegadoForm.getCdSistemaLegado());
		}
		
		try {

			tx.beginTrans();

			instance.delete(sistemaLegadoVO, tx);

			UserCatalogo attribute = this.getLoggedUser();

			//String name = attribute.getUser().getUsername();

			//historicoVO.setLogin(name);
			//historicoVO.setTabela("CSRV_SISTEMA_LEGADO");
			//historicoVO.setDsScript(sistemaLegadoForm.getSistemaLegadoFormBean().getSistemaLegadoVO().getExtraFields()
			//		.getString("SQL"));

			//historicoDAO.insert(historicoVO, tx);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao excluir Sistema Legado.");
			this.CatalogoPRSExceptionHandler(ex, SistemaLegadoAction.class.getName(), response);
			return null;
		}
		
		//ANALISAR
		//request.setAttribute("SQLEXCLUIRSL", historicoVO.getDsScript());
		
		try {
			//HttpServletResponse response = getResponse();
			response.setContentType("text/javascript");
			PrintWriter out = response.getWriter();
			out.println("$(bot_hidden).onclick();");
			out.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "novo", path = "popupScriptNovo.jsp"), @Jpf.Forward(name = "altera", path = "popupScriptAltera.jsp"), @Jpf.Forward(name = "exclui", path = "popupScriptExclui.jsp") })
	public ActionForward abrirPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//HttpServletRequest request = getRequest();
		if(request.getParameter("tipoPopup").equals("novo"))
			return mapping.findForward("novo");
		else if(request.getParameter("tipoPopup").equals("altera"))
			return mapping.findForward("altera");
		else if(request.getParameter("tipoPopup").equals("exclui"))
			return mapping.findForward("exclui");
		
		return null;
	}

	/*@Override
	protected void onCreate() {
		planoServiceControl.setEndpointAddress(WebServicesConfiguration
				.getEndpointAddress(WebServicesConfiguration.PLANO));
	}

	@Override
	protected void onDestroy(HttpSession session) {
	}*/

}
