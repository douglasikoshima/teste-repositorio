package br.com.vivo.catalogoPRS.pageflows.tradutorErro.Controller.errosCadastros;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.catalogoPRS.bo.UserCatalogo;
import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ErroLegadoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ErroPadraoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ServicoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ServicoNegocioDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.SistemaLegadoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.formBean.ErroComumFormBean;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroComumVO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.HistoricoVO;

import com.framework.coordinator.Transaction;
import com.framework.exception.DAOException;

//@Jpf.Controller()
public class ErrosCadastrosAction extends BaseMappingDispatchAction {

	private static ServicoNegocioDAO negocioDAO = (ServicoNegocioDAO) ServicoNegocioDAO.getInstance();

	private static SistemaLegadoDAO legadoDAO = (SistemaLegadoDAO) SistemaLegadoDAO.getInstance();

	private static ServicoDAO servicoDAO = (ServicoDAO) ServicoDAO.getInstance();

	private static ErroLegadoDAO erroLegadoDAO = (ErroLegadoDAO) ErroLegadoDAO.getInstance();

	private static ErroPadraoDAO erroPadraoDAO = (ErroPadraoDAO) ErroPadraoDAO.getInstance();

	//private static HistoricoDAO historicoDAO = (HistoricoDAO) HistoricoDAO.getInstance();


	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "errosCadastrados.jsp") })
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws DAOException {

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("SQLORD", "CSRV_SERVICO_NEGOCIO.CD_SERVICO_NEGOCIO");

		Collection<?> listaNegocio = negocioDAO.findByParams(param);

		param = new HashMap<String, Object>();

		param.put("SQLORD", "CSRV_SISTEMA_LEGADO.CD_SISTEMA_LEGADO");

		Collection<?> listaLegado = legadoDAO.findByParams(param);

		param = new HashMap<String, Object>();

		param.put("SQLORD", "CSRV_SERVICO.CD_SERVICO");

		Collection<?> listaServico = servicoDAO.findByParams(param);

		//HttpServletRequest request = getRequest();

		request.setAttribute("listaNegocio", listaNegocio);

		request.setAttribute("listaLegado", listaLegado);

		request.setAttribute("listaServico", listaServico);

		HttpSession session = request.getSession();

		ErroComumVO erroComumVO = new ErroComumVO();

		session.setAttribute("erroComum", erroComumVO);

		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "resultadoPesquisa.jsp") })
	public ActionForward pesquisarErros(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {

		ErroComumFormBean erroComumForm = (ErroComumFormBean)form;
		
		ErroComumVO erroComumVO = new ErroComumVO();

		if (erroComumForm.getErroComumVO().getServicoNegocio() != null && erroComumForm.getErroComumVO().getServicoNegocio() > 0 ) {
			erroComumVO.setServicoNegocio(erroComumForm.getErroComumVO().getServicoNegocio());
		}

		if (erroComumForm.getErroComumVO().getSistemaLegado() != null && erroComumForm.getErroComumVO().getSistemaLegado() > 0) {
			erroComumVO.setSistemaLegado(erroComumForm.getErroComumVO().getSistemaLegado());
		}

		if (erroComumForm.getErroComumVO().getServico() != null && erroComumForm.getErroComumVO().getServico() > 0) {
			erroComumVO.setServico(erroComumForm.getErroComumVO().getServico());
		}

		if (erroComumForm.getErroComumVO().getCdPadrao() != null && erroComumForm.getErroComumVO().getCdPadrao() > 0) {
			erroComumVO.setCdPadrao((erroComumForm.getErroComumVO().getCdPadrao()));
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("SQLORD", "CSRV_SERVICO_NEGOCIO.CD_SERVICO_NEGOCIO");

		if (erroComumVO.getServicoNegocio() != null && erroComumForm.getErroComumVO().getServicoNegocio() > 0) {
			param.put("CSRV_SERVICO_NEGOCIO.CD_SERVICO_NEGOCIO = "+ erroComumVO.getServicoNegocio(), "");
		}

		if (erroComumVO.getSistemaLegado() != null && erroComumForm.getErroComumVO().getSistemaLegado() > 0) {
			param.put("CSRV_SISTEMA_LEGADO.CD_SISTEMA_LEGADO = "+ erroComumVO.getSistemaLegado(), "");
		}

		if (erroComumVO.getServico() != null && erroComumForm.getErroComumVO().getServico() > 0) {
			param.put("CSRV_SERVICO.CD_SERVICO = " + erroComumVO.getServico(),"");
		}

		if (erroComumVO.getCdPadrao() != null && !erroComumForm.getErroComumVO().getCdPadrao().equals("")) {
			param.put("CSRV_ERRO_PADRAO.CD_ERRO_PADRAO = "+ erroComumVO.getCdPadrao(), "");
		}

		try {
			Collection<?> collection = erroLegadoDAO.findByParams(param);
			erroComumForm.setVoList(collection);
			erroComumForm.setColPesquisarErrosCadastrados(collection);
			
		} catch (Exception e) {			
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao listar Erros Cadastrados");
			this.CatalogoPRSExceptionHandler(ex, ErrosCadastrosAction.class.getName(), response);
			return null;
		}

		request.setAttribute("totalRegistros", erroComumForm.getVoList().getSize());
		request.setAttribute("totalPagina", erroComumForm.getFooterPages().size());

		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupAlterar.jsp") })
	public ActionForward abrirAlterarErro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		String cdPadrao = request.getParameter("cd_padrao");

		String descricao = request.getParameter("descricao");

		request.setAttribute("cd_erro_padrao", cdPadrao);
		request.setAttribute("mensagem", descricao);

		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupExcluir.jsp") })
	public ActionForward abrirExcluirErro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {


		String cdPadrao = request.getParameter("cd_padrao");

		request.setAttribute("cd_erro_padrao", cdPadrao);

		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	//@Jpf.Action()
	public ActionForward alterarErro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {

		ErroComumFormBean erroComumForm = (ErroComumFormBean)form;
		Transaction tx = new Transaction();
		HistoricoVO historicoVO = new HistoricoVO();
		HttpSession session = request.getSession();

		try {

			erroPadraoDAO.update(erroComumForm.getErroComumVO(), tx);

			UserCatalogo attribute = (br.com.vivo.catalogoPRS.bo.UserCatalogo) session.getAttribute("logged_user");

			String name = attribute.getUser().getUsername();

			historicoVO.setLogin(name);
			historicoVO.setTabela("CSRV_ERRO_PADRAO");
			historicoVO.setDsScript(erroComumForm.getErroComumVO().getExtraFields().getString("SQL"));

			//historicoDAO.insert(historicoVO, tx);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();			
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao alterar Mensagem");
			this.CatalogoPRSExceptionHandler(ex, ErrosCadastrosAction.class.getName(), response);
			return null;
		}

		session.setAttribute("SQLALTERARERRO", "begin\n" + historicoVO.getDsScript()+ "\nend;");

		return null;
	}

	//@Jpf.Action()
	public ActionForward excluirErro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {

		ErroComumFormBean erroComumForm = (ErroComumFormBean)form;
		Transaction tx = new Transaction();
		HistoricoVO historicoVO = new HistoricoVO();
		HttpSession session = request.getSession();

		try {

			tx.beginTrans();
			erroLegadoDAO.delete(erroComumForm.getErroComumVO(), tx);
			erroPadraoDAO.delete(erroComumForm.getErroComumVO(), tx);

			UserCatalogo attribute = (br.com.vivo.catalogoPRS.bo.UserCatalogo) session.getAttribute("logged_user");

			String name = attribute.getUser().getUsername();

			historicoVO.setLogin(name);
			historicoVO.setTabela("CSRV_ERRO_PADRAO/CSRV_ERRO_LEGADO");
			historicoVO.setDsScript(erroComumForm.getErroComumVO().getExtraFields().getString("SQL1")
					+ "\n"
					+ erroComumForm.getErroComumVO().getExtraFields().getString("SQL2"));

			//historicoDAO.insert(historicoVO, tx);

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao excluir Erro Cadastrado");
			this.CatalogoPRSExceptionHandler(ex, ErrosCadastrosAction.class.getName(), response);
			return null;
		}

		
		session.setAttribute("SQLEXCLUIRERRO", "begin\n" + historicoVO.getDsScript()
				+ "\nend;");

		return null;
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupMensagem.jsp") })
	public ActionForward abrirMensagem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {


		request.setAttribute("mensagem", request.getParameter("msg"));

		this.cleanHeader(response);
		return mapping.findForward("success");
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "popupServico.jsp") })
	public ActionForward abrirServico(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {


		request.setAttribute("cdServico", request.getParameter("cd_servico"));
		request.setAttribute("dsServico", request.getParameter("ds_servico"));

		//this.cleanHeader(response);
		return mapping.findForward("success");
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "altera", path = "popupScriptAltera.jsp"), @Jpf.Forward(name = "exclui", path = "popupScriptExclui.jsp") })
	public ActionForward abrirPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
				
		if(request.getParameter("tipoPopup").equals("altera"))
			return mapping.findForward("altera");
		else if(request.getParameter("tipoPopup").equals("exclui"))
			return mapping.findForward("exclui");
		
		return null;
	}

}