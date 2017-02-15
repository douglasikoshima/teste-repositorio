package br.com.vivo.catalogoPRS.pageflows.tradutorErro.Controller.novoErro.erroComum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.framework.coordinator.Transaction;
import com.framework.vo.ValueObject;

import br.com.vivo.catalogoPRS.exception.CatalogoPRSException;
import br.com.vivo.catalogoPRS.pageflows.shared.baseFlow.BaseMappingDispatchAction;
import br.com.vivo.catalogoPRS.pageflows.shared.form.ErroComumForm;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ErroLegadoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ErroPadraoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.HistoricoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ServicoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.ServicoNegocioDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.dao.SistemaLegadoDAO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroComumVO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroLegadoVO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ErroPadraoVO;
import br.com.vivo.catalogoPRS.pageflows.tradutorErro.vo.ServicoNegocioVO;

//@Jpf.Controller()
public class ErroComumAction extends BaseMappingDispatchAction {
	private static final long serialVersionUID = 1L;
	private static ServicoNegocioDAO negocioDAO = (ServicoNegocioDAO) ServicoNegocioDAO.getInstance();
	private static SistemaLegadoDAO legadoDAO = (SistemaLegadoDAO) SistemaLegadoDAO.getInstance();

	private static ServicoDAO servicoDAO = (ServicoDAO) ServicoDAO.getInstance();
	private static HistoricoDAO historicoDAO = (HistoricoDAO) HistoricoDAO.getInstance();

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "negocio.jsp") })
	public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws CatalogoPRSException {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("SQLORD", "CSRV_SERVICO_NEGOCIO.CD_SERVICO_NEGOCIO");
		//Collection listaNegocio = null;
		List<ServicoNegocioVO> listaNegocio = null;
		try {
			listaNegocio = (List<ServicoNegocioVO>) negocioDAO.findByParams(param);
		}

		catch (Exception e) {
			//throw new CatalogoPRSException("Erro inesperado ao Listar Servi�o Negocio");
			CatalogoPRSException ex = new CatalogoPRSException("Erro inesperado ao Listar Servi�o Negocio.");
			this.CatalogoPRSExceptionHandler(ex, ErroComumAction.class.getName(), response);
			return null;
			
		}
		//HttpServletRequest request = getRequest();
		request.setAttribute("listaNegocio", listaNegocio);
		//HttpSession session = getSession();
		ErroComumVO erroComumVO = new ErroComumVO();
		request.getSession().setAttribute("erroComum", erroComumVO);

		return mapping.findForward("success");
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "legado.jsp") })
	public ActionForward gravarSN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws CatalogoPRSException {
		ErroComumForm erroComumForm = (ErroComumForm)form;
		//HttpSession session = getSession();
		ErroComumVO erroComumVO = (ErroComumVO) request.getSession().getAttribute("erroComum");
		erroComumVO.setServicoNegocio(erroComumForm.getErroComumVO().getServicoNegocio());

		Map<String, Object> param = new HashMap<String, Object>();

		param.put("SQLORD", "CSRV_SISTEMA_LEGADO.CD_SISTEMA_LEGADO");

		Collection listaLegado = null;

		try {
			listaLegado = legadoDAO.findByParams(param);
		} catch (Exception e) {
			request.getSession().setAttribute("erroComum", null);
			//throw new CatalogoPRSException("Erro inesperado ao Listar Sistema Legado");
			CatalogoPRSException ex = new CatalogoPRSException("Erro inesperado ao Listar Sistema Legado.");
			this.CatalogoPRSExceptionHandler(ex, ErroComumAction.class.getName(), response);
			return null;
		}
		//HttpServletRequest request = getRequest();
		request.setAttribute("listaLegado", listaLegado);
		//Forward forward = new Forward("success");
		return mapping.findForward("success");
	}

	//@Jpf.Action(forwards = {
			//@Jpf.Forward(name = "success", path = "servico.jsp"),
			//@Jpf.Forward(name = "semLegado", path = "erroCadastro.jsp") })
	public ActionForward gravarSL(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)throws CatalogoPRSException {
		ErroComumForm erroCommumForm = (ErroComumForm)form;
		//HttpSession session = getSession();
		ErroComumVO erroComumVO = (ErroComumVO) request.getSession().getAttribute("erroComum");

		if (erroCommumForm.getErroComumVO().getSistemaLegado() != null) {
			erroComumVO.setSistemaLegado(erroCommumForm.getErroComumVO().getSistemaLegado());
		} else {
			erroComumVO.setSistemaLegado(Long.valueOf(0));
			erroComumVO.setServico(null);
			return mapping.findForward("semLegado");
		}

		Map<String, Object> param = new HashMap<String, Object>();
		param.put("SUBSTR(CSRV_SERVICO.CD_SERVICO,0,length(CSRV_SERVICO.CD_SERVICO)-3) = " + erroComumVO.getSistemaLegado(), "");
		param.put("SQLORD", "CSRV_SERVICO.CD_SERVICO");

		Collection listaServico = null;

		try {
			listaServico = servicoDAO.findByParams(param);
		} catch (Exception e) {
			request.getSession().setAttribute("erroComum", null);
			//throw new CatalogoPRSException("Erro inesperado ao Listar Serviço Enablement");
			CatalogoPRSException ex = new CatalogoPRSException("Erro inesperado ao Listar Servi�o Enablement.");
			this.CatalogoPRSExceptionHandler(ex, ErroComumAction.class.getName(), response);
			return null;
			
		}

		//HttpServletRequest request = getRequest();
		request.setAttribute("listaServico", listaServico);
		//Forward forward = new Forward("success");
		return mapping.findForward("success");
	}

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "success", path = "erroCadastro.jsp") })
	public ActionForward gravarSE(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		ErroComumForm erroComumForm = (ErroComumForm)form;
		//HttpSession session = getSession();
		ErroComumVO erroComumVO = (ErroComumVO) request.getSession().getAttribute("erroComum");
		erroComumVO.setServico(erroComumForm.getErroComumVO().getServico());
		//HttpServletRequest request = getRequest();
		request.setAttribute("erroPadrao", erroComumForm.getErroComumVO().getServico() + "999");
		//Forward forward = new Forward("success");
		return mapping.findForward("success");
	}

	//@Jpf.Action()
	public ActionForward gravarErro(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
			throws CatalogoPRSException {
		ErroComumForm erroComumForm = (ErroComumForm)form;
		ErroPadraoDAO erroPadraoDAO = (ErroPadraoDAO) ErroPadraoDAO.getInstance();
		ErroLegadoDAO erroLegadoDAO = (ErroLegadoDAO) ErroLegadoDAO.getInstance();

		//HttpSession session = getSession();
		ErroComumVO erroComumVO = (ErroComumVO) request.getSession().getAttribute("erroComum");
		ErroPadraoVO erroPadraoVO = new ErroPadraoVO();
		ErroLegadoVO erroLegadoVO = new ErroLegadoVO();

		erroPadraoVO.setTxMensagemErroPadrao(erroComumForm.getErroComumVO().getMensagem());

		if (erroComumForm.getErroComumVO().getCdLegadoDef()!= null && erroComumForm.getErroComumVO().getCdLegadoDef().toString().equals("999")) {
			erroPadraoVO.setId(erroComumVO.getServico().intValue() + "999");
		} else if (erroComumForm.getErroComumVO().getCdPadrao() != null && !erroComumForm.getErroComumVO().getCdPadrao().toString().equals("")) {
			try {
				ValueObject object = erroPadraoDAO.findById(erroComumForm.getErroComumVO().getCdPadrao().toString());
				if (object == null) {
					erroPadraoVO.setId(erroComumForm.getErroComumVO().getCdPadrao().toString());
				} else {
					//throw new CatalogoPRSException("Erro j� existente");
					CatalogoPRSException ex = new CatalogoPRSException("Erro j� existente.");
					this.CatalogoPRSExceptionHandler(ex, ErroComumAction.class.getName(), response);
					return null;
				}
			} catch (Exception e) {
				//throw new CatalogoPRSException("Erro ao inserir Erro na Base");
				CatalogoPRSException ex = new CatalogoPRSException("Erro ao inserir Erro na Base.");
				this.CatalogoPRSExceptionHandler(ex, ErroComumAction.class.getName(), response);
				return null;
			}
		} else {
			try {
				erroPadraoVO.setId(erroPadraoDAO.getNextId(erroComumVO.getServicoNegocio().toString()));
			} catch (Exception e) {
				//throw new CatalogoPRSException("Erro ao inserir Erro na Base");
				CatalogoPRSException ex = new CatalogoPRSException("Erro ao inserir Erro na Base.");
				this.CatalogoPRSExceptionHandler(ex, ErroComumAction.class.getName(), response);
				return null;
			}
		}

		Transaction tx = new Transaction();
		//HistoricoVO historicoVO = new HistoricoVO();

		try {

			tx.beginTrans();

			erroPadraoDAO.insert(erroPadraoVO, tx);

			erroLegadoVO.setCdErroPadrao(erroPadraoVO.getCdErroPadrao());

			if(erroComumForm.getErroComumVO().getCdLegadoDef() != null && erroComumForm.getErroComumVO().getCdLegadoDef().toString().equals("999"))
				erroLegadoVO.setCdErroLegado(erroComumForm.getErroComumVO().getCdLegadoDef().toString());
			else if(erroComumForm.getErroComumVO().getCdLegado() != null && !erroComumForm.getErroComumVO().getCdLegado().toString().equals(""))
				erroLegadoVO.setCdErroLegado(erroComumForm.getErroComumVO().getCdLegado().toString());
			else
				erroLegadoVO.setCdErroLegado(erroLegadoVO.getCdErroPadrao().toString());

			if(erroComumVO.getServico()==null)
				erroLegadoVO.setCdServico(0);
			else
				erroLegadoVO.setCdServico(erroComumVO.getServico().intValue());

			erroLegadoVO.setCdSistemaLegado(erroComumVO.getSistemaLegado()
					.intValue());

			erroLegadoDAO.insert(erroLegadoVO, tx);

			//UserCatalogo attribute = (br.com.vivo.catalogoPRS.bo.UserCatalogo) this.getLoggedUser();

		//	String name = attribute.getUser().getUsername();

			/*historicoVO.setLogin(name);
			historicoVO.setTabela("CSRV_ERRO_PADRAO/CSRV_ERRO_LEGADO");
			historicoVO.setDsScript(erroPadraoVO.getExtraFields().getString(
					"SQL")
					+ " \n" + erroLegadoVO.getExtraFields().getString("SQL"));
			historicoDAO.insert(historicoVO, tx);*/

			tx.commitTrans();
		} catch (Exception e) {
			tx.rollBackTrans();
			CatalogoPRSException ex = new CatalogoPRSException("Erro ao inserir Erro na Base.");
			this.CatalogoPRSExceptionHandler(ex, ErroComumAction.class.getName(), response);
			return null;
		}
		finally{
			
			request.getSession().setAttribute("erroComum", null);
			
		}

		//request.getSession().setAttribute("SQLNOVOERRO", "begin\n" + historicoVO.getDsScript()
			//	+ "\nend;");

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

	//@Jpf.Action(forwards = { @Jpf.Forward(name = "novo", path = "popupScriptNovo.jsp") })
	public ActionForward abrirPopup(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		
		//Forward forward = null;
		
		//HttpServletRequest request = getRequest();
		if(request.getParameter("tipoPopup").equals("novo"))
			return mapping.findForward("novo");
	
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