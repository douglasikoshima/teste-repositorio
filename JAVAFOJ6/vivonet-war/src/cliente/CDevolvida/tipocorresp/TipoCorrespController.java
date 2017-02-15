package cliente.CDevolvida.tipocorresp;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ListaTipoCorrespVODocument.ListaTipoCorrespVO;
import br.com.vivo.fo.cliente.vo.TipoCorrespondenciaVODocument.TipoCorrespondenciaVO;
import br.com.vivo.fo.cliente.vo.impl.TipoCorrespondenciaVODocumentImpl.TipoCorrespondenciaVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class TipoCorrespController extends AbstractAction {

	private static final long serialVersionUID = 6868456884635020360L;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.correspondenciaDevolvida.CorrespondenciaDevolvida correspondenciaDevolvida;

	private static Logger log = new Logger("clientes");

	// variáveis e objetos
	private String user = null;
	private TipoCorrespForm tipoCorrespForm = null;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("incluialteratipocorresp".equals(mapping.getParameter())) {
			return incluialteratipocorresp(mapping, form, request, response);
		} else if ("salvartipocorresp".equals(mapping.getParameter())) {
			return salvartipocorresp(mapping, form, request, response);
		} else if ("excluirtipocorresp".equals(mapping.getParameter())) {
			return excluirtipocorresp(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="TipoCorresp.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			TipoCorrespForm form = (TipoCorrespForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("TipoCorrespController:begin(" + user + ") >> INICIALIZADO");

			// Criar a instancia do actioForm
			tipoCorrespForm = form;

			if (request.getAttribute("msg") != null) {
				tipoCorrespForm.setInMsgRetorno(request.getAttribute("msg").toString());
			}

			// obtem a lista de Assuntos e atualiza o ActionForm
			ListaTipoCorrespVO listaTipoCorrespVO = correspondenciaDevolvida.getListaTipoCorresp(user);
			tipoCorrespForm.getListaTipoCorrespVO().setTipoCorrespondenciaVOArray(listaTipoCorrespVO.getTipoCorrespondenciaVOArray());

			if (request.getAttribute("inMsg") != null) {
				tipoCorrespForm.setInMsgRetorno(request.getAttribute("inMsg").toString());
			}

			log.debug("TipoCorrespController:begin(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("TipoCorrespController:begin(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IncluiAlteraTipoCorresp.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluialteratipocorresp(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("TipoCorrespController:incluialteratipocorresp(" + user + ") >> INICIALIZADO");

			String operacao = request.getParameter("operacao");

			// parametros para página de alteração
			if (operacao.equalsIgnoreCase("UPDATE")) {
				log.debug("TipoCorrespController:incluialteratipocorresp(" + user + ") >> Alteraçao");
				int index = Integer.parseInt(request.getParameter("index"));
				request.setAttribute("operacao", "UPDATE");
				request.setAttribute("index", String.valueOf(index));
				request.setAttribute("Titulo", "Alterar");

				tipoCorrespForm.setSigla(tipoCorrespForm.getListaTipoCorrespVO().getTipoCorrespondenciaVOArray()[index].getSigla());
				tipoCorrespForm.setInDisponibilidade(tipoCorrespForm.getListaTipoCorrespVO().getTipoCorrespondenciaVOArray()[index].getInDisponibilidade());
				tipoCorrespForm.setDescricao(tipoCorrespForm.getListaTipoCorrespVO().getTipoCorrespondenciaVOArray()[index].getDescricao());
				tipoCorrespForm.setId(tipoCorrespForm.getListaTipoCorrespVO().getTipoCorrespondenciaVOArray()[index].getId());

			} else {
				log.debug("TipoCorrespController:incluialteratipocorresp(" + user + ") >> Inserçao");
				request.setAttribute("operacao", "INSERT");
				request.setAttribute("index", ConstantesCRM.SVAZIO);
				request.setAttribute("Titulo", "Novo");
				tipoCorrespForm.setSigla(ConstantesCRM.SVAZIO);
				tipoCorrespForm.setDescricao(ConstantesCRM.SVAZIO);
			}

			log.debug("TipoCorrespController:incluialteratipocorresp(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("TipoCorrespController:incluialteratipocorresp(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/tipocorresp/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="success1" path="executa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvartipocorresp(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			TipoCorrespForm form = (TipoCorrespForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("TipoCorrespController:salvartipocorresp(" + user + ") >> INICIALIZADO");

			// obter informçoes da página de incl/Alt e montar a estrutura para gravacao
			ListaTipoCorrespVO listaTipoCorrespVO = ListaTipoCorrespVO.Factory.newInstance();
			String resposta = null;

			String desc = form.getDescricao().trim();
			String sigla = form.getSigla().trim();

			if (desc.length() > 0 && sigla.length() > 0) {

				// verifica se é uma alteração ou inclusão e atualiza o ActionForm
				if (request.getParameter("operacao").equalsIgnoreCase("UPDATE")) {

					TipoCorrespondenciaVO[] md = new TipoCorrespondenciaVOImpl[1];
					md[0] = TipoCorrespondenciaVO.Factory.newInstance();

					md[0].setDescricao(desc);
					md[0].setSigla(sigla);
					md[0].setId(form.getId());
					md[0].setInDisponibilidade(form.getInDisponibilidade());

					listaTipoCorrespVO.setTipoCorrespondenciaVOArray(md);

					// efetua gravação do conteudo
					resposta = correspondenciaDevolvida.setAlterarTipoCorresp(user, listaTipoCorrespVO);
					String index = request.getParameter("index");
					request.setAttribute("operacao", "UPDATE");
					request.setAttribute("index", index);
				} else if (request.getParameter("operacao").equalsIgnoreCase("INSERT")) {

					TipoCorrespondenciaVO[] md = new TipoCorrespondenciaVOImpl[1];
					md[0] = TipoCorrespondenciaVO.Factory.newInstance();
					md[0].setDescricao(desc);
					md[0].setSigla(sigla);

					listaTipoCorrespVO.setTipoCorrespondenciaVOArray(md);

					// efetua gravação do conteudo
					resposta = correspondenciaDevolvida.setGravarTipoCorresp(user, listaTipoCorrespVO);
					request.setAttribute("operacao", "INSERT");
					request.setAttribute("index", ConstantesCRM.SVAZIO);
				}

				if (resposta.indexOf("DUPLICATE KEY") > 0) {
					log.debug("TipoCorrespController:salvartipocorresp(" + user + ") >> FINALIZADO (Duplicate key)");
					tipoCorrespForm.setInMsgRetorno("true");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("success1");
				} else {
					log.debug("TipoCorrespController:salvartipocorresp(" + user + ") >> FINALIZADO");
					tipoCorrespForm.setInMsgRetorno("false");
					request.setAttribute("inMsg", "false");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("success1");
				}

			} else {
				log.debug("TipoCorrespController:salvartipocorresp(" + user + ") >> FINALIZADO (inRetorno vazio)");
				tipoCorrespForm.setInMsgRetorno("vazio");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("success1");
			}

		} catch (Exception e) {
			log.error("TipoCorrespController:salvartipocorresp(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/tipocorresp/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward excluirtipocorresp(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {

			String resposta = null;
			TipoCorrespForm form = (TipoCorrespForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("TipoCorrespController:excluirtipocorresp(" + user + ") >> INICIALIZADO");

			String codigo = String.valueOf(tipoCorrespForm.getListaTipoCorrespVO().getTipoCorrespondenciaVOArray(Integer.parseInt(request.getParameter("codigo"))).getId());

			// efetua gravação do conteudo
			resposta = correspondenciaDevolvida.removeTipoCorresp(user, codigo);

			if (resposta.indexOf("</mensagem>") > 0) {
				log.debug("TipoCorrespController:excluirtipocorresp(" + user + ") >> Exclucsao nao permitida");
				form.setInMsgRetorno("naoExclui");
			} else {
				form.setInMsgRetorno("exclui");
			}

			log.debug("TipoCorrespController:excluirtipocorresp(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("TipoCorrespController:excluirtipocorresp(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/tipocorresp/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	public static class TipoCorrespForm extends ActionForm {

		private static final long serialVersionUID = 1555225255596393806L;

		private String inDisponibilidade;
		private String inMsgRetorno = null;
		private String descricao;
		private String sigla;
		private int id;
		private ListaTipoCorrespVO listaTipoCorrespVO;

		// Default Constructor
		public TipoCorrespForm() {
			// inicializa conteúdos
			descricao = ConstantesCRM.SVAZIO;
			sigla = ConstantesCRM.SVAZIO;
			// inicializa VO
			listaTipoCorrespVO = ListaTipoCorrespVO.Factory.newInstance();
			// listaMotivoDevolucaoVO.setMotivoDevolucaoVOArray(new MotivoDevolucaoVOImpl[0]);
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String string) {
			descricao = string;
		}

		public String getSigla() {
			return sigla;
		}

		public void setSigla(String string) {
			sigla = string;
		}

		public ListaTipoCorrespVO getListaTipoCorrespVO() {
			return listaTipoCorrespVO;
		}

		public void setTipoCorresp(String string) {
			descricao = string;
		}

		public void setListaTipoCorrespVO(ListaTipoCorrespVO listaTipoCorrespVO) {
			this.listaTipoCorrespVO = listaTipoCorrespVO;
		}

		public void setInMsgRetorno(String inMsgRetorno) {
			this.inMsgRetorno = inMsgRetorno;
		}

		public String getInMsgRetorno() {
			return this.inMsgRetorno;
		}

		public void setInDisponibilidade(String inDisponibilidade) {
			this.inDisponibilidade = inDisponibilidade;
		}

		public String getInDisponibilidade() {
			return this.inDisponibilidade;
		}
	}

	public TipoCorrespForm getTipoCorrespForm() {
		return this.tipoCorrespForm;
	}

}
