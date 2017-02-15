package cliente.DadosComportamentais.subAssunto;

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
import br.com.vivo.fo.cliente.vo.ListaConteudoVODocument.ListaConteudoVO;
import br.com.vivo.fo.cliente.vo.ListaSubAssuntoVODocument.ListaSubAssuntoVO;
import br.com.vivo.fo.cliente.vo.SubAssuntoGravacaoVODocument.SubAssuntoGravacaoVO;
import br.com.vivo.fo.cliente.vo.SubAssuntoVODocument.SubAssuntoVO;
import br.com.vivo.fo.cliente.vo.SubAssuntosDocument.SubAssuntos;
import br.com.vivo.fo.cliente.vo.impl.AssuntoVODocumentImpl.AssuntoVOImpl;
import br.com.vivo.fo.cliente.vo.impl.DisponibilidadeVODocumentImpl.DisponibilidadeVOImpl;
import br.com.vivo.fo.cliente.vo.impl.SubAssuntoVODocumentImpl.SubAssuntoVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class SubAssuntoController extends AbstractAction {

	private static final long serialVersionUID = 2259211944751474830L;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.dadosComportamentais.DadosComportamentaisFacade dadosComportamentaisFacade;

	private static Logger log = new Logger("clientes");

	// variáveis e objetos
	private String user = null;
	private ListaSubAssuntoForm listaSubAssuntoForm = null;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("incluirAlterarSubAssunto".equals(mapping.getParameter())) {
			return incluirAlterarSubAssunto(mapping, form, request, response);
		} else if ("pesquisarSubAssunto".equals(mapping.getParameter())) {
			return pesquisarSubAssunto(mapping, form, request, response);
		} else if ("salvarSubAssunto".equals(mapping.getParameter())) {
			return salvarSubAssunto(mapping, form, request, response);
		} else if ("atualizar".equals(mapping.getParameter())) {
			return atualizar(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="subassunto.jsp"
	 * @jpf:forward name="success1" path="pesquisarSubAssunto.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {

			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("SubAssuntoController:begin(" + user + ") >> INICIALIZADO");

			// cria a instância do Action Form
			listaSubAssuntoForm = new ListaSubAssuntoForm();

			// atualiza Disponibilizades
			DisponibilidadeVO[] disponibilidadeVOArray = new DisponibilidadeVOImpl[2];
			disponibilidadeVOArray[0] = DisponibilidadeVO.Factory.newInstance();
			disponibilidadeVOArray[0].setCodigo(ConstantesCRM.SONE);
			disponibilidadeVOArray[0].setDescricao("SIM");
			disponibilidadeVOArray[1] = DisponibilidadeVO.Factory.newInstance();
			disponibilidadeVOArray[1].setCodigo(ConstantesCRM.SZERO);
			disponibilidadeVOArray[1].setDescricao("NÃO");
			listaSubAssuntoForm.getListaSubAssuntoVO().getDisponibilidades().setDisponibilidadeVOArray(disponibilidadeVOArray);

			// Obtem lista de assuntos e atualizar o Action Form
			ListaConteudoVO listaConteudoVO = ListaConteudoVO.Factory.newInstance();
			listaConteudoVO = dadosComportamentaisFacade.getListas(user, ConstantesCRM.SVAZIO);
			listaSubAssuntoForm.getListaSubAssuntoVO().setAssuntos(listaConteudoVO.getAssuntos());

			if (null != request.getParameter("pesquisa")) {
				listaSubAssuntoForm.setAssuntoSelecionado(request.getParameter("pesquisa"));
				request.getSession().setAttribute("refazPesquisa", "true");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("success1");
			}

			log.debug("SubAssuntoController:begin(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("SubAssuntoController:begin(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IncluiAlteraSubAssunto.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluirAlterarSubAssunto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("SubAssuntoController:incluirAlterarSubAssunto(" + user + ") >> INICIALIZADO");

			// obtem o tipo de operação
			String operacao = request.getParameter("operacao");

			// parametros para página de alteração
			if (operacao.equalsIgnoreCase("UPDATE")) {
				log.debug("SubAssuntoController:incluirAlterarSubAssunto(" + user + ") >> ALTERACAO");
				String index = request.getParameter("index");
				request.setAttribute("operacao", "UPDATE");
				request.setAttribute("index", index);
				request.setAttribute("Titulo", "Alterar");

				listaSubAssuntoForm.setSubAssunto(listaSubAssuntoForm.getListaSubAssuntoVO().getSubAssuntos().getSubAssuntoVOArray(Integer.parseInt(index)).getDescricao());
				listaSubAssuntoForm.setSequenciaApresentacao(listaSubAssuntoForm.getListaSubAssuntoVO().getSubAssuntos().getSubAssuntoVOArray(Integer.parseInt(index)).getSequenciaApresentacao());
				listaSubAssuntoForm.setDisponibilidadeSelecionado(Integer.toString(listaSubAssuntoForm.getListaSubAssuntoVO().getSubAssuntos().getSubAssuntoVOArray(Integer.parseInt(index)).getIndisponibilidade()));
				listaSubAssuntoForm.setDisponibilidadeAssociada(String.valueOf(listaSubAssuntoForm.getListaSubAssuntoVO().getSubAssuntos().getSubAssuntoVOArray(Integer.parseInt(index)).getIndisponibilidade()));
				listaSubAssuntoForm.setCodigoSubAssunto(String.valueOf(listaSubAssuntoForm.getListaSubAssuntoVO().getSubAssuntos().getSubAssuntoVOArray(Integer.parseInt(index)).getCodigo()));

			} else {

				log.debug("SubAssuntoController:incluirAlterarSubAssunto(" + user + ") >> INCLUSAO");
				request.setAttribute("operacao", "INSERT");
				request.setAttribute("index", "");
				request.setAttribute("Titulo", "Novo");
				listaSubAssuntoForm.setSubAssunto(ConstantesCRM.SVAZIO);
				listaSubAssuntoForm.setSequenciaApresentacao(ConstantesCRM.SVAZIO);
				listaSubAssuntoForm.setDisponibilidadeAssociada(ConstantesCRM.SONE);
				listaSubAssuntoForm.setDisponibilidadeSelecionado(ConstantesCRM.SONE);
			}

			log.debug("SubAssuntoController:begin(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("SubAssuntoController:begin(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/subAssunto/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisaSubAssunto.jsp"
	 * @jpf:forward name="success1" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisarSubAssunto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ListaSubAssuntoForm form = (ListaSubAssuntoForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("SubAssuntoController:pesquisarSubAssunto(" + user + ") >> INICIALIZADO");

			// obter o código do Assunto
			int codigoAssunto = Integer.parseInt(form.getAssuntoSelecionado());
			log.debug("SubAssuntoController:pesquisarSubAssunto(" + user + ") >> codigoAssunto: " + codigoAssunto);

			// Obtem a lista de SubAssuntos
			SubAssuntos subAssuntos = dadosComportamentaisFacade.getListaSubAssuntos(user, codigoAssunto, ConstantesCRM.SVAZIO);

			// atualizar o Action Form
			listaSubAssuntoForm.getListaSubAssuntoVO().setSubAssuntos(subAssuntos);
			listaSubAssuntoForm.setAssuntoSelecionado(form.getAssuntoSelecionado());
			listaSubAssuntoForm.setDisponibilidadeSelecionado(form.getDisponibilidadeSelecionado());

			if (request.getAttribute("inMsg") != null) {
				listaSubAssuntoForm.setInMsgRetorno(request.getAttribute("inMsg").toString());
			}

			log.debug("SubAssuntoController:pesquisarSubAssunto(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("SubAssuntoController:begin(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/subAssunto/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarSubAssunto.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarSubAssunto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ListaSubAssuntoForm form = (ListaSubAssuntoForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("SubAssuntoController:salvarSubAssunto(" + user + ") >> INICIALIZADO");

			// obtem informações para a gravação e monta objeto
			SubAssuntoGravacaoVO subAssuntoGravacaoVO = SubAssuntoGravacaoVO.Factory.newInstance();
			String resposta = null;

			String desc = form.getSubAssunto().trim();

			if (desc.length() > 0) {
				// verifica se é uma alteração ou inclusão e atualiza o ActionForm
				if (request.getParameter("operacao").equalsIgnoreCase("UPDATE")) {
					log.debug("SubAssuntoController:salvarSubAssunto(" + user + ") >> ALTERACAO");
					// monta estrutura para gravacao e obtem informações da pagina de inclusão / Alteracao
					subAssuntoGravacaoVO.setAssuntoVO(AssuntoVO.Factory.newInstance());
					subAssuntoGravacaoVO.getAssuntoVO().setCodigo(Integer.parseInt(form.getAssuntoSelecionado()));

					subAssuntoGravacaoVO.setSubAssuntoVO(SubAssuntoVO.Factory.newInstance());
					subAssuntoGravacaoVO.getSubAssuntoVO().setDescricao(desc);
					subAssuntoGravacaoVO.getSubAssuntoVO().setSequenciaApresentacao(form.getSequenciaApresentacao());
					subAssuntoGravacaoVO.getSubAssuntoVO().setIndisponibilidade(Integer.parseInt(form.getDisponibilidadeSelecionado()));
					subAssuntoGravacaoVO.getSubAssuntoVO().setCodigo(Integer.parseInt(form.getCodigoSubAssunto()));

					// efetua chamada ao Tuxedo para gravação do SubAssunto
					resposta = dadosComportamentaisFacade.setAlteraSubAssunto(user, subAssuntoGravacaoVO);
					String index = request.getParameter("index");
					request.setAttribute("operacao", "UPDATE");
					request.setAttribute("index", index);
				} else if (request.getParameter("operacao").equalsIgnoreCase("INSERT")) {
					log.debug("SubAssuntoController:salvarSubAssunto(" + user + ") >> INSERCAO");
					// obtem informações para a gravação e monta objeto
					subAssuntoGravacaoVO.setAssuntoVO(AssuntoVO.Factory.newInstance());
					subAssuntoGravacaoVO.getAssuntoVO().setCodigo(Integer.parseInt(form.getAssuntoSelecionado()));

					subAssuntoGravacaoVO.setSubAssuntoVO(SubAssuntoVO.Factory.newInstance());
					subAssuntoGravacaoVO.getSubAssuntoVO().setDescricao(desc);
					subAssuntoGravacaoVO.getSubAssuntoVO().setSequenciaApresentacao(form.getSequenciaApresentacao());
					subAssuntoGravacaoVO.getSubAssuntoVO().setIndisponibilidade(Integer.parseInt(form.getDisponibilidadeSelecionado()));
					// subAssuntoGravacaoVO.getSubAssuntoVO().setCodigo(Integer.parseInt(form.getCodigoSubAssunto()));

					// efetua chamada ao Tuxedo para gravação do SubAssunto
					resposta = dadosComportamentaisFacade.setGravaSubAssunto(user, subAssuntoGravacaoVO);
					request.setAttribute("operacao", "INSERT");
					request.setAttribute("index", ConstantesCRM.SVAZIO);
				}

				if (resposta.indexOf("DUPLICATE KEY") > 0) {
					log.debug("SubAssuntoController:salvarSubAssunto(" + user + ") >> FINALIZADO (Duplicate key)");
					listaSubAssuntoForm.setInMsgRetorno("true");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				} else {
					log.debug("SubAssuntoController:salvarSubAssunto(" + user + ") >> FINALIZADO");
					listaSubAssuntoForm.setInMsgRetorno("false");
					request.setAttribute("inMsg", "false");
					request.setAttribute("pesquisar", form.getAssuntoSelecionado());
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}
			} else {
				log.debug("SubAssuntoController:salvarSubAssunto(" + user + ") >> FINALIZADO");
				listaSubAssuntoForm.setInMsgRetorno("vazio");
				log.debug("SubAssuntoController:salvarSubAssunto(" + user + ") >> INICIALIZADO");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}
		} catch (Exception e) {
			log.error("SubAssuntoController:salvarSubAssunto(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/subAssunto/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisaSubAssunto.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward atualizar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ListaSubAssuntoForm form = (ListaSubAssuntoForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("SubAssuntoController:atualizar(" + user + ") >> INICIALIZADO");

			listaSubAssuntoForm.setAssuntoSelecionado(form.getAssuntoSelecionado());
			log.debug("SubAssuntoController:atualizar(" + user + ") >> assuntoSelecionado: " + form.getAssuntoSelecionado());
			limpaSubassunto();

			log.debug("SubAssuntoController:atualizar(" + user + ") >> INICIALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("SubAssuntoController:atualizar(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/subAssunto/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	public static class ListaSubAssuntoForm extends ActionForm {

		private static final long serialVersionUID = -6443426267159054119L;
		// retornos
		private String assuntoSelecionado;
		private String subAssunto;
		private String codigoSubAssunto;
		private String sequenciaApresentacao;
		private String disponibilidadeSelecionado;
		private String inMsgRetorno = null;

		// guarda a disponibilidade associada ao assunto
		private String disponibilidadeAssociada;

		// VO
		private ListaSubAssuntoVO listaSubAssuntoVO;

		// default constructor
		public ListaSubAssuntoForm() {
			// inicializa conteudos
			assuntoSelecionado = ConstantesCRM.SVAZIO;
			subAssunto = ConstantesCRM.SVAZIO;
			sequenciaApresentacao = ConstantesCRM.SVAZIO;
			disponibilidadeSelecionado = ConstantesCRM.SVAZIO;

			// inicializa VO
			listaSubAssuntoVO = ListaSubAssuntoVO.Factory.newInstance();

			listaSubAssuntoVO.setAssuntos(Assuntos.Factory.newInstance());
			listaSubAssuntoVO.getAssuntos().setAssuntoVOArray(new AssuntoVOImpl[0]);

			listaSubAssuntoVO.setSubAssuntos(SubAssuntos.Factory.newInstance());
			listaSubAssuntoVO.getSubAssuntos().setSubAssuntoVOArray(new SubAssuntoVOImpl[0]);

			listaSubAssuntoVO.setDisponibilidades(Disponibilidades.Factory.newInstance());
			listaSubAssuntoVO.getDisponibilidades().setDisponibilidadeVOArray(new DisponibilidadeVOImpl[0]);
		}

		public String getCodigoSubAssunto() {
			return (this.codigoSubAssunto);
		}

		public void setCodigoSubAssunto(String string) {
			this.codigoSubAssunto = string;
		}

		public String getAssuntoSelecionado() {
			return assuntoSelecionado;
		}

		public String getDisponibilidadeSelecionado() {
			return disponibilidadeSelecionado;
		}

		public String getDisponibilidadeAssociada() {
			return disponibilidadeAssociada;
		}

		public ListaSubAssuntoVO getListaSubAssuntoVO() {
			return listaSubAssuntoVO;
		}

		public String getSequenciaApresentacao() {
			return sequenciaApresentacao;
		}

		public String getSubAssunto() {
			return subAssunto;
		}

		public void setAssuntoSelecionado(String string) {
			assuntoSelecionado = string;
		}

		public void setDisponibilidadeSelecionado(String string) {
			disponibilidadeSelecionado = string;
		}

		public void setDisponibilidadeAssociada(String string) {
			disponibilidadeAssociada = string;
		}

		public void setListaSubAssuntoVO(ListaSubAssuntoVO assuntoVO) {
			listaSubAssuntoVO = assuntoVO;
		}

		public void setSequenciaApresentacao(String string) {
			sequenciaApresentacao = string;
		}

		public void setSubAssunto(String string) {
			subAssunto = string;
		}

		public void setInMsgRetorno(String inMsgRetorno) {
			this.inMsgRetorno = inMsgRetorno;
		}

		public String getInMsgRetorno() {
			return this.inMsgRetorno;
		}
	}

	// getter para o Action Form
	public ListaSubAssuntoForm getListaSubAssuntoForm() {
		return this.listaSubAssuntoForm;
	}

	public void limpaSubassunto() {
		while (listaSubAssuntoForm.getListaSubAssuntoVO().getSubAssuntos().sizeOfSubAssuntoVOArray() > 0) {
			listaSubAssuntoForm.getListaSubAssuntoVO().getSubAssuntos().removeSubAssuntoVO(0);
		}
	}
}
