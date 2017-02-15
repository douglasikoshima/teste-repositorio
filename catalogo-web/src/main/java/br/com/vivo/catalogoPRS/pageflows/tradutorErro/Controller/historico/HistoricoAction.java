package br.com.vivo.catalogoPRS.pageflows.tradutorErro.Controller.historico;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
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
import br.com.vivo.catalogoPRS.pageflows.shared.form.HistoricoForm;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.HistoricoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.HistoricoVO;

import com.framework.exception.DAOException;
import com.framework.exception.NotFoundException;

public class HistoricoAction extends BaseMappingDispatchAction implements Serializable{
	
	private static HistoricoDAO instance = (HistoricoDAO) HistoricoDAO.getInstance();
	private String SUCCESS = "success";
	
	private static final long serialVersionUID = 1L;

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "historico.jsp") })
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		return mapping.findForward(SUCCESS);
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisaHistorico.jsp") })
	public ActionForward listaDados(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws DAOException {
		
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("SQLORD", "DT_SCRIPT");
		params.put("SQLDIR", "ASC");
		
		HistoricoForm formulario = (HistoricoForm)form;

		if (formulario.getLogin() != null && !formulario.getLogin().trim().equals("")) {
			params.put("UPPER(CD_LOGIN_USUARIO) LIKE ", formulario.getLogin().toUpperCase()+"%");
		}

		if (formulario.getDataScript() != null && !formulario.getDataScript().trim().equals("")) {
			if(!formulario.getDataScript().equals("dd/mm/aaaa"))
				params.put("TRUNC(DT_SCRIPT) = to_date('" + formulario.getDataScript()+ "', 'DD/MM/YYYY')", "");
		}
		
		if (formulario.getTabela() != null && !formulario.getTabela().trim().equals("")) {
			params.put("UPPER(TABELA) LIKE ", formulario.getTabela().toUpperCase()+"%");
		}

		try {
			List<HistoricoVO> listaHistoricoVO = (List<HistoricoVO>) instance.findByParams(params);
			formulario.setListaHistoricoVO(listaHistoricoVO);
		} catch (Exception e) {
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao listar o Histórico.");
			this.CatalogoPRSExceptionHandler(ex, HistoricoAction.class.getName(), response);
			return null;
		}		
		
		request.setAttribute("totalRegistros", formulario.getListaHistoricoVO().size());
		//request.setAttribute("totalPagina", formulario.getHistoricoVO().getFooterPages().size());
		
		return mapping.findForward(SUCCESS);
		
	}
	
	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupScript.jsp") })
	public ActionForward popupScript(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws DAOException, NotFoundException {
		
		String parameter = request.getParameter("ds_script");
		
		request.setAttribute("SQL", parameter);
		
		return mapping.findForward(SUCCESS);
		
	}

	//@Jpf.Action()
	public ActionForward exportar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException, DAOException, NotFoundException {
		
		StringBuffer sb = new StringBuffer();
		sb.append("begin\n\n");
		
		HistoricoForm formulario = (HistoricoForm)form;

		for (Long id : formulario.getIds()) {
			
			HistoricoVO historicoVO = (HistoricoVO)instance.findById(id.toString());
			
			if(historicoVO!=null){
				sb.append(historicoVO.getDsScript()+"\n");
			}
		}
		
		sb.append("\nend;");

		response.setContentType("text/plain");
		response.addHeader("Content-Disposition", "attachment;filename=script.sql");
		response.setCharacterEncoding("ISO-8859-1");
		PrintWriter out = response.getWriter();

		out.write(sb.toString());
		out.flush();
		out.close();
		
		return null;
	}
}
