package br.com.vivo.catalogoPRS.pageflows.tradutorErro.Controller.novoErro.servico;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.framework.coordinator.Transaction;
import com.framework.exception.DAOException;


import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ServicoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.SistemaLegadoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.formBean.ServicoFormBean;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.HistoricoVO;

//@Jpf.Controller()
public class ServicoEnablementAction extends BaseMappingDispatchAction {

	private static ServicoDAO instance = (ServicoDAO) ServicoDAO.getInstance();

	//private static HistoricoDAO historicoDAO = (HistoricoDAO) HistoricoDAO.getInstance();

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "enablement.jsp") })
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException{

		SistemaLegadoDAO sistemaLegadoDAO = (SistemaLegadoDAO) SistemaLegadoDAO.getInstance();

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("SQLORD", "CSRV_SISTEMA_LEGADO.CD_SISTEMA_LEGADO");

		Collection<?> collection = null;
		
		try{
			collection = sistemaLegadoDAO.findByParams(param);
		}
		catch(Exception e) {
			CatalogoPRSException ex = new CatalogoPRSException("Erro inesperado ao acessar o Servi&ccedil;o Enablement");
			this.CatalogoPRSExceptionHandler(ex, ServicoEnablementAction.class.getName(), response);
			return null;
		}

		request.setAttribute("sistemaLegado", collection);
		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaEnablement.jsp") })
	public ActionForward pesquisarEN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)	throws CatalogoPRSException {

		ServicoFormBean formulario = (ServicoFormBean)form;
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("SQLORD", "CD_SERVICO");
		params.put("SQLDIR", "ASC");

		System.out.println(formulario.getServicoVO().getSistemaLegado());
		if (formulario.getServicoVO().getSistemaLegado() != null && formulario.getServicoVO().getSistemaLegado() > 0) {
			params.put("SUBSTR(CSRV_SERVICO.CD_SERVICO,0,length(CSRV_SERVICO.CD_SERVICO)-3) = "	+ formulario.getServicoVO().getSistemaLegado(), "");
		}

		if (formulario.getServicoVO().getCdServico() != null && formulario.getServicoVO().getCdServico() > 0) {
			params.put("CD_SERVICO = ", formulario.getServicoVO().getCdServico().toString());
		}

		if (!formulario.getServicoVO().getDsServico().trim().equals("")) {
			params.put("UPPER(DS_SERVICO) like '" + formulario.getServicoVO().getDsServico().replaceAll("\\*", "%").toUpperCase() + "%'", "");
		}

		try {
			Collection<?> collection = instance.findByParams(params);
			formulario.setVoList(collection);
			formulario.setCollectionPesquisarEN(collection);
		} catch (Exception e) {
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao listar Servi&ccedil;o Enablement");
			this.CatalogoPRSExceptionHandler(ex, ServicoEnablementAction.class.getName(), response);
			return null;
		}

		request.setAttribute("totalRegistros", formulario.getVoList().getSize());
		request.setAttribute("totalPagina", formulario.getFooterPages().size());

		return mapping.findForward("success");
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupNovo.jsp") })
	public ActionForward abrirNovoEN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {

		SistemaLegadoDAO sistemaLegadoDAO = (SistemaLegadoDAO) SistemaLegadoDAO.getInstance();

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("SQLORD", "CSRV_SISTEMA_LEGADO.CD_SISTEMA_LEGADO");

		Collection<?> collection = null;
		
		try{
			collection = sistemaLegadoDAO.findByParams(param);
		} catch (Exception e) {
			CatalogoPRSException ex = new CatalogoPRSException("Erro inesperado ao tentar criar novo Servi&ccedil;o Enablement");
			this.CatalogoPRSExceptionHandler(ex, ServicoEnablementAction.class.getName(), response);
			return null;
		}

		request.setAttribute("sistemaLegado", collection);
		this.cleanHeader(response);
		return mapping.findForward("success");

	}

	//@Jpf.Action()
	public ActionForward novoEN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {

		ServicoFormBean formulario = (ServicoFormBean)form;
		Transaction tx = new Transaction();
		//HistoricoVO historicoVO = new HistoricoVO();
		//HttpSession session = request.getSession();
		
		try {

			tx.beginTrans();

			instance.insert(formulario.getServicoVO(), tx);

			//UserCatalogo attribute = (br.com.vivo.catalogoPRS.bo.UserCatalogo) session.getAttribute("logged_user");

			//String name = attribute.getUser().getUsername();

			//historicoVO.setLogin(name);
			//historicoVO.setTabela("CSRV_SERVICO");
			//historicoVO.setDsScript(formulario.getServicoVO().getExtraFields().getString("SQL"));

			//historicoDAO.insert(historicoVO, tx);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao inserir Servi&ccedil;o Enablement");
			this.CatalogoPRSExceptionHandler(ex, ServicoEnablementAction.class.getName(), response);
			return null;
		}

		//session.setAttribute("SQLNOVOSE", historicoVO.getDsScript());
		
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
	public ActionForward abrirAlterarEN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		String cdEN = request.getParameter("cd_en");

		String dsEN = request.getParameter("ds_en");

		request.setAttribute("cd_servico", cdEN);
		request.setAttribute("ds_servico", dsEN);

		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	//@Jpf.Action()
	public ActionForward alterarEN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {

		ServicoFormBean formulario = (ServicoFormBean)form;
		Transaction tx = new Transaction();
		HistoricoVO historicoVO = new HistoricoVO();
		HttpSession session = request.getSession();

		try {

			tx.beginTrans();

			instance.update(formulario.getServicoVO(), tx);

			//UserCatalogo attribute = (br.com.vivo.catalogoPRS.bo.UserCatalogo) session.getAttribute("logged_user");

			//String name = attribute.getUser().getUsername();

			//historicoVO.setLogin(name);
			//historicoVO.setTabela("CSRV_SERVICO");
			//historicoVO.setDsScript(formulario.getServicoVO().getExtraFields().getString("SQL"));

			//historicoDAO.insert(historicoVO, tx);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();			
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao alterar Servi&ccedil;o Enablement");
			this.CatalogoPRSExceptionHandler(ex, ServicoEnablementAction.class.getName(), response);
			return null;						
		}

		session.setAttribute("SQLALTERASE", historicoVO.getDsScript());
		
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
	public ActionForward abrirExcluirEN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws DAOException {

		request.setAttribute("cdServico", request.getParameter("cd_en"));

		return mapping.findForward("success");
	}

	//@Jpf.Action()
	public ActionForward excluirEN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {

		ServicoFormBean formulario = (ServicoFormBean)form;
		Transaction tx = new Transaction();
		HistoricoVO historicoVO = new HistoricoVO();
		HttpSession session = request.getSession();
		
		try {

			tx.beginTrans();

			instance.delete(formulario.getServicoVO(), tx);

			//UserCatalogo attribute = (br.com.vivo.catalogoPRS.bo.UserCatalogo) session.getAttribute("logged_user");

			//String name = attribute.getUser().getUsername();

			//historicoVO.setLogin(name);
			//historicoVO.setTabela("CSRV_SERVICO");
			//historicoVO.setDsScript(formulario.getServicoVO().getExtraFields().getString("SQL"));

			//historicoDAO.insert(historicoVO, tx);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao deletar Servi&ccedil;o Enablement, Tabelas relacionadas");
			this.CatalogoPRSExceptionHandler(ex, ServicoEnablementAction.class.getName(), response);
			return null;
			
			
		}

		session.setAttribute("SQLEXCLUIRSE", historicoVO.getDsScript());
		
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

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "novo", path = "popupScriptNovo.jsp"), @Jpf.Forward(name = "altera", path = "popupScriptAltera.jsp"), @Jpf.Forward(name = "exclui", path = "popupScriptExclui.jsp") })
	public ActionForward abrirPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		
		if(request.getParameter("tipoPopup").equals("novo"))
			 return mapping.findForward("novo");
		else if(request.getParameter("tipoPopup").equals("altera"))
			return mapping.findForward("altera");
		else if(request.getParameter("tipoPopup").equals("exclui"))
			return mapping.findForward("exclui");
				
		return null;
	}

}