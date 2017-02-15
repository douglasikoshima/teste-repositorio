package br.com.vivo.catalogoPRS.pageflows.tradutorErro.Controller.novoErro.servicoNegocio;

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

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ServicoNegocioForm;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.HistoricoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ServicoNegocioDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.HistoricoVO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ServicoNegocioVO;

import com.framework.coordinator.Transaction;
import com.framework.exception.DAOException;

//@Jpf.Controller()
public class ServicoNegocioAction extends BaseMappingDispatchAction {
	private static final long serialVersionUID = 1L;
	private static ServicoNegocioDAO instance = (ServicoNegocioDAO) ServicoNegocioDAO.getInstance();
	private static HistoricoDAO historicoDAO = (HistoricoDAO) HistoricoDAO.getInstance();

	private String SUCCESS = "success";

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "servicoNegocio.jsp") })
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaServicoNegocio.jsp") })
	public ActionForward pesquisarSN(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException {

		ServicoNegocioForm servicoNegocioForm =	(ServicoNegocioForm)form;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("SQLORD", "CD_SERVICO_NEGOCIO");
		params.put("SQLDIR", "ASC");

		if (servicoNegocioForm.getCdServicoNegocio() != null && servicoNegocioForm.getCdServicoNegocio() > 0) {
			params.put("CD_SERVICO_NEGOCIO = ", servicoNegocioForm.getCdServicoNegocio().toString());
		}

		if (!servicoNegocioForm.getDsServicoNegocio().trim().equals("")) {
			params.put("UPPER(DS_SERVICO_NEGOCIO) like '"
					+ servicoNegocioForm.getDsServicoNegocio()
							.replaceAll("\\*", "%").toUpperCase() + "%'", "");
		}

		try {
			List <ServicoNegocioVO> listaServicoNegocio = (List<ServicoNegocioVO>) instance.findByParams(params);
			servicoNegocioForm.setListaServicoNegocio(listaServicoNegocio);
		} catch (Exception e) {
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao listar Servi�o de Negocio.");
			this.CatalogoPRSExceptionHandler(ex, ServicoNegocioAction.class.getName(), response);
			return null;
			//throw new CatalogoPRSException("Erro ao listar Serviço de Negocio");
		}

		request.setAttribute("totalRegistros", servicoNegocioForm.getListaServicoNegocio().size());
		//request.setAttribute("totalPagina", servicoNegocioForm.getServicoNegocioFormBean().getFooterPages().size());

		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupNovo.jsp") })
	public ActionForward abrirNovoSN(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	//@Jpf.Action()
	public ActionForward novoSN(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException {

		ServicoNegocioForm servicoNegocioForm =	(ServicoNegocioForm)form;
		ServicoNegocioVO servicoNegocioVO = new ServicoNegocioVO();
		Transaction tx = new Transaction();
		HistoricoVO historicoVO = new HistoricoVO();
		
		if(servicoNegocioForm.getCdServicoNegocio() != null && !servicoNegocioForm.getCdServicoNegocio().equals("")){
			servicoNegocioVO.setCdServicoNegocio(servicoNegocioForm.getCdServicoNegocio());
		}
		
		if(servicoNegocioForm.getDsServicoNegocio() != null && !servicoNegocioForm.getDsServicoNegocio().equals("")){
			servicoNegocioVO.setDsServicoNegocio(servicoNegocioForm.getDsServicoNegocio());
		}
		
		try {

			tx.beginTrans();
			//ServicoNegocioFormBean servicoNegocioFormBean = new ServicoNegocioFormBean();
			//servicoNegocioFormBean.getServicoNegocioVO().setCdServicoNegocio(servicoNegocioForm.getCdServicoNegocio());
			//servicoNegocioFormBean.getServicoNegocioVO().setDsServicoNegocio(servicoNegocioForm.getDsServicoNegocio());
			//servicoNegocioForm.setServicoNegocioFormBean(servicoNegocioFormBean);
			instance.insert(servicoNegocioVO, tx);

			//UserCatalogo attribute = (br.com.vivo.catalogoPRS.bo.UserCatalogo) request.getSession().getAttribute("logged_user");

			//String name = attribute.getUser().getUsername();

			//historicoVO.setLogin(name);
			//historicoVO.setTabela("CSRV_SERVICO_NEGOCIO");
			//historicoVO.setDsScript(servicoNegocioForm.getServicoNegocioFormBean().getServicoNegocioVO().getExtraFields().getString("SQL"));

			//historicoDAO.insert(historicoVO, tx);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao inserir Servi�o de Negocio.");
			this.CatalogoPRSExceptionHandler(ex, ServicoNegocioAction.class.getName(), response);
			return null;
			//throw new CatalogoPRSException("Erro ao inserir Serviço de Negocio");
		}
		
		//request.getSession().setAttribute("SQLNOVOSN", historicoVO.getDsScript());

		try {
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
	public ActionForward abrirAlterarSN(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		String cdSN = request.getParameter("cd_sn");
		String dsSN = request.getParameter("ds_sn");

		request.setAttribute("cd_servico_negocio", cdSN);
		request.setAttribute("ds_servico_negocio", dsSN);

		this.cleanHeader(response);
		return mapping.findForward(SUCCESS);
	}

	//@Jpf.Action()
	public ActionForward alterarSN(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException {

		ServicoNegocioForm servicoNegocioForm =	(ServicoNegocioForm)form;
		ServicoNegocioVO servicoNegocioVO = new ServicoNegocioVO();
		Transaction tx = new Transaction();
		HistoricoVO historicoVO = new HistoricoVO();
		
		if(servicoNegocioForm.getCdServicoNegocio() != null && !servicoNegocioForm.getCdServicoNegocio().equals("")){
			servicoNegocioVO.setCdServicoNegocio(servicoNegocioForm.getCdServicoNegocio());
		}
		
		if(servicoNegocioForm.getDsServicoNegocio() != null && !servicoNegocioForm.getDsServicoNegocio().equals("")){
			servicoNegocioVO.setDsServicoNegocio(servicoNegocioForm.getDsServicoNegocio());
		}
		
		try {

			tx.beginTrans();

			instance.update(servicoNegocioVO, tx);

			//UserCatalogo attribute = (br.com.vivo.catalogoPRS.bo.UserCatalogo) request.getSession().getAttribute("logged_user");

			//String name = attribute.getUser().getUsername();

			//historicoVO.setLogin(name);
			//historicoVO.setTabela("CSRV_SERVICO_NEGOCIO");
			//historicoVO.setDsScript(servicoNegocioForm.getServicoNegocioFormBean().getServicoNegocioVO().getExtraFields().getString("SQL"));

			//historicoDAO.insert(historicoVO, tx);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao alterar Servi�o de Negocio.");
			this.CatalogoPRSExceptionHandler(ex, ServicoNegocioAction.class.getName(), response);
			return null;
			//throw new CatalogoPRSException("Erro ao alterar Serviço de Negocio");
		}

		//request.getSession().setAttribute("SQLALTERASN", historicoVO.getDsScript());
		try {
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
	public ActionForward abrirExcluirSN(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws DAOException {

		request.setAttribute("cdServicoNegocio", request.getParameter("cd_sn"));

		return mapping.findForward(SUCCESS);
	}

	//@Jpf.Action()
	public ActionForward excluirSN(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws CatalogoPRSException {

		ServicoNegocioForm servicoNegocioForm =	(ServicoNegocioForm)form;
		ServicoNegocioVO servicoNegocioVO = new ServicoNegocioVO();
		Transaction tx = new Transaction();
		HistoricoVO historicoVO = new HistoricoVO();

		if(servicoNegocioForm.getCdServicoNegocio() != null && !servicoNegocioForm.getCdServicoNegocio().equals("")){
			servicoNegocioVO.setCdServicoNegocio(servicoNegocioForm.getCdServicoNegocio());
		}
		
		try {

			tx.beginTrans();

			instance.delete(servicoNegocioVO, tx);

			//UserCatalogo attribute = (br.com.vivo.catalogoPRS.bo.UserCatalogo) request.getSession().getAttribute("logged_user");

			//String name = attribute.getUser().getUsername();

			//historicoVO.setLogin(name);
			//historicoVO.setTabela("CSRV_SERVICO_NEGOCIO");
			//historicoVO.setDsScript(servicoNegocioForm.getServicoNegocioFormBean().getServicoNegocioVO().getExtraFields().getString("SQL"));

			//historicoDAO.insert(historicoVO, tx);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao excluir Servi�o de Negocio.");
			this.CatalogoPRSExceptionHandler(ex, ServicoNegocioAction.class.getName(), response);
			return null;
			//throw new CatalogoPRSException("Erro ao excluir Serviço de Negocio");
		}

		//request.getSession().setAttribute("SQLEXCLUIRSN", historicoVO.getDsScript());

		return null;
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "novo", path = "popupScriptNovo.jsp"), @Jpf.Forward(name = "altera", path = "popupScriptAltera.jsp"), @Jpf.Forward(name = "exclui", path = "popupScriptExclui.jsp") })
	public ActionForward abrirPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		
		if(request.getParameter("tipoPopup").equals("novo"))
		    return mapping.findForward("novo");
		else if(request.getParameter("tipoPopup").equals("altera"))
		    return mapping.findForward("altera");
		else if(request.getParameter("tipoPopup").equals("exclui"))
		    return mapping.findForward("exclui");
		
		return null;
	}
}