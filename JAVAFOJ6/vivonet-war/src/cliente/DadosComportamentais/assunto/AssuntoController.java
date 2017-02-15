package cliente.DadosComportamentais.assunto;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.AssuntoVODocument.AssuntoVO;
import br.com.vivo.fo.cliente.vo.AssuntosDocument.Assuntos;
import br.com.vivo.fo.cliente.vo.DisponibilidadeVODocument.DisponibilidadeVO;
import br.com.vivo.fo.cliente.vo.DisponibilidadesDocument.Disponibilidades;
import br.com.vivo.fo.cliente.vo.ListaAssuntoVODocument.ListaAssuntoVO;
import br.com.vivo.fo.cliente.vo.impl.AssuntoVODocumentImpl.AssuntoVOImpl;
import br.com.vivo.fo.cliente.vo.impl.DisponibilidadeVODocumentImpl.DisponibilidadeVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class AssuntoController extends AbstractAction {

	private static final long serialVersionUID = 6870193806296880823L;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.dadosComportamentais.DadosComportamentaisFacade dadosComportamentaisFacade;

	private static Logger log = new Logger("clientes");

	// variáveis e objetos
	private String user = null;
	private ListaAssuntoForm listaAssuntoForm = null;

	protected global.Global globalApp = new global.Global();

	protected boolean alwaysTrackPreviousPage() {
		return true;
	}

	protected boolean alwaysTrackPreviousAction() {
		return true;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("incluirAlterarAssunto".equals(mapping.getParameter())) {
			return incluirAlterarAssunto(mapping, form, request, response);
		} else if ("salvarAssunto".equals(mapping.getParameter())) {
			return salvarAssunto(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="assunto.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {

			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("AssuntoController:begin("+user+") >> INICIALIZADO");

			// cria instancia do ActionForm
			listaAssuntoForm = new ListaAssuntoForm();

			DisponibilidadeVO[] disponibilidadeVOArray = new DisponibilidadeVOImpl[2];
			disponibilidadeVOArray[0] = DisponibilidadeVO.Factory.newInstance();
			disponibilidadeVOArray[0].setCodigo(ConstantesCRM.SONE);
			disponibilidadeVOArray[0].setDescricao("SIM");
			disponibilidadeVOArray[1] = DisponibilidadeVO.Factory.newInstance();
			disponibilidadeVOArray[1].setCodigo(ConstantesCRM.SZERO);
			disponibilidadeVOArray[1].setDescricao("NÃO");
			listaAssuntoForm.getListaAssuntoVO().getDisponibilidades().setDisponibilidadeVOArray(disponibilidadeVOArray);

			// obtem a lista de Assuntos e atualiza o ActionForm
			Assuntos assuntos = dadosComportamentaisFacade.getListaAssuntos(user, ConstantesCRM.SVAZIO);

			if (request.getAttribute("inMsg") != null){
				listaAssuntoForm.setInMsgRetorno(request.getAttribute("inMsg").toString());
			}

			listaAssuntoForm.getListaAssuntoVO().setAssuntos(assuntos);
			log.debug("AssuntoController:begin("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			log.error("AssuntoController:begin(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IncluiAlteraAssunto.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluirAlterarAssunto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("AssuntoController:incluirAlterarAssunto("+user+") >> INICIALIZADO");

			String operacao = request.getParameter("operacao");

			if(operacao.equalsIgnoreCase("UPDATE")) {
				log.debug("AssuntoController:incluirAlterarAssunto("+user+") >> Alteracao");
				int index = Integer.parseInt(request.getParameter("index"));
				request.setAttribute("operacao", "UPDATE");
				request.setAttribute("index", String.valueOf(index));
				request.setAttribute("Titulo", "Alterar");

				listaAssuntoForm.setcodigoAssunto(String.valueOf(listaAssuntoForm.getListaAssuntoVO().getAssuntos().getAssuntoVOArray(index).getCodigo()));
				listaAssuntoForm.setAssunto(listaAssuntoForm.getListaAssuntoVO().getAssuntos().getAssuntoVOArray(index).getDescricao());
				listaAssuntoForm.setDisponibilidadeSelecionado(String.valueOf(listaAssuntoForm.getListaAssuntoVO().getAssuntos().getAssuntoVOArray(index).getDisponibilidade()));
				listaAssuntoForm.setDisponibilidadeAssociada(String.valueOf(listaAssuntoForm.getListaAssuntoVO().getAssuntos().getAssuntoVOArray(index).getDisponibilidade()));
			}
			else {
				log.debug("AssuntoController:incluirAlterarAssunto("+user+") >> Inclusao");
				request.setAttribute("operacao", "INSERT");
				request.setAttribute("index", ConstantesCRM.SVAZIO);
				request.setAttribute("Titulo", "Novo");
				listaAssuntoForm.setDisponibilidadeSelecionado(ConstantesCRM.SONE);
				listaAssuntoForm.setDisponibilidadeAssociada(ConstantesCRM.SONE);
				listaAssuntoForm.setAssunto(ConstantesCRM.SVAZIO);
			}

			log.debug("AssuntoController:incluirAlterarAssunto("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("AssuntoController:incluirAlterarAssunto(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/assunto/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="executa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarAssunto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			ListaAssuntoForm form = (ListaAssuntoForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("AssuntoController:salvarAssunto("+user+") >> INICIALIZADO");

			// obtem informações para a gravação e monta objeto
			AssuntoVO assuntoVO = AssuntoVO.Factory.newInstance();
			String resposta = null;

			String desc = form.getAssunto().trim();

			if (desc.length() > 0) {

				// verifica se é uma alteração ou inclusão e atualiza o ActionForm
				if(request.getParameter("operacao").equalsIgnoreCase("UPDATE")) {
					log.debug("AssuntoController:salvarAssunto("+user+") >> Alteracao de dado comportamental");
					// obtem informações para a gravação e monta objeto
					assuntoVO.setCodigo(Integer.parseInt(form.getCodigoAssunto()));
					assuntoVO.setDescricao(desc);
					assuntoVO.setDisponibilidade(form.getDisponibilidadeSelecionado());

					// obtem o indice do cara a ser alterado
					String idx = (request.getParameter("index"));
					listaAssuntoForm.getListaAssuntoVO().getAssuntos().setAssuntoVOArray(Integer.parseInt(idx), assuntoVO);

					resposta = dadosComportamentaisFacade.setAlterarAssunto(user, assuntoVO);
					request.setAttribute("index",idx);
					request.setAttribute("operacao", "UPDATE");
				}
				else if(request.getParameter("operacao").equalsIgnoreCase("INSERT")) {
					log.debug("AssuntoController:salvarAssunto("+user+") >> Inserçao de dado comportamental");
					// Inclui o novo dado comportamental no form
					//AssuntoVO assuntoVOIncluir = listaAssuntoForm.getListaAssuntoVO().getAssuntos().addNewAssuntoVO();
					//assuntoVOIncluir = assuntoVO;

					// efetua gravação do assunto
					// obtem informações para a gravação e monta objeto
					assuntoVO.setDescricao(desc);
					assuntoVO.setDisponibilidade(form.getDisponibilidadeSelecionado());

					resposta = dadosComportamentaisFacade.setAssunto(user, assuntoVO);
					request.setAttribute("operacao", "INSERT");
					request.setAttribute("index", ConstantesCRM.SVAZIO);
				}

				if(resposta.indexOf("DUPLICATE KEY")>0){
					log.debug("AssuntoController:salvarAssunto("+user+") >> FINALIZADO (Duplicate key)");
					listaAssuntoForm.setInMsgRetorno("true");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}else{
					log.debug("AssuntoController:salvarAssunto("+user+") >> FINALIZADO");
					listaAssuntoForm.setInMsgRetorno("false");
					request.setAttribute("inMsg", "false");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}
			}else{
				log.debug("AssuntoController:salvarAssunto("+user+") >> FINALIZADO");
				listaAssuntoForm.setInMsgRetorno("vazio");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}
		} catch (Exception e) {
			log.error("AssuntoController:salvarAssunto(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/assunto/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	public static class ListaAssuntoForm extends ActionForm {

		private static final long serialVersionUID = -3324893125543903335L;

		private String inMsgRetorno = null;
		private String assunto;
		private String disponibilidadeSelecionado = null;
		//guarda a disponibilidade associada ao assunto
		private String disponibilidadeAssociada;
		private String codigoAssunto;
		private ListaAssuntoVO listaAssuntoVO;

		public ListaAssuntoForm() {
			// inicializa conteúdos
			assunto                    = ConstantesCRM.SVAZIO;
			disponibilidadeSelecionado = ConstantesCRM.SVAZIO;

			// inicializa VO
			listaAssuntoVO = ListaAssuntoVO.Factory.newInstance();

			listaAssuntoVO.setAssuntos(Assuntos.Factory.newInstance());
			listaAssuntoVO.getAssuntos().setAssuntoVOArray(new AssuntoVOImpl[0]);

			listaAssuntoVO.setDisponibilidades(Disponibilidades.Factory.newInstance());
			listaAssuntoVO.getDisponibilidades().setDisponibilidadeVOArray(new DisponibilidadeVOImpl[0]);
		}

		public String getAssunto() {
			return assunto;
		}

		public String getCodigoAssunto() {
			return codigoAssunto;
		}

		public String getDisponibilidadeSelecionado() {
			return disponibilidadeSelecionado;
		}

		public String getDisponibilidadeAssociada() {
			return disponibilidadeAssociada;
		}

		public ListaAssuntoVO getListaAssuntoVO() {
			return listaAssuntoVO;
		}

		public void setAssunto(String string) {
			assunto = string;
		}

		public void setcodigoAssunto(String string) {
			codigoAssunto = string;
		}

		public void setDisponibilidadeAssociada(String string) {
			disponibilidadeAssociada = string;
		}

		public void setDisponibilidadeSelecionado(String string) {
			disponibilidadeSelecionado = string;
		}

		public void setListaAssuntoVO(ListaAssuntoVO assuntoVO) {
			listaAssuntoVO = assuntoVO;
		}

		public void setInMsgRetorno(String inMsgRetorno)
		{
			this.inMsgRetorno = inMsgRetorno;
		}

		public String getInMsgRetorno()
		{
			return this.inMsgRetorno;
		}
	}

	public ListaAssuntoForm getListaAssuntoForm() {
		return this.listaAssuntoForm;
	}
}
