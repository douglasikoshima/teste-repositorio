package cliente.CDevolvida.status;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ListaStatusCorrespVODocument.ListaStatusCorrespVO;
import br.com.vivo.fo.cliente.vo.StatusCorrespVODocument.StatusCorrespVO;
import br.com.vivo.fo.cliente.vo.UnidadeDisponivelVODocument.UnidadeDisponivelVO;
import br.com.vivo.fo.cliente.vo.UnidadeSelecionadaVODocument.UnidadeSelecionadaVO;
import br.com.vivo.fo.cliente.vo.UnidadeVODocument.UnidadeVO;
import br.com.vivo.fo.cliente.vo.impl.StatusCorrespVODocumentImpl.StatusCorrespVOImpl;
import br.com.vivo.fo.cliente.vo.impl.UnidadeDisponivelVODocumentImpl.UnidadeDisponivelVOImpl;
import br.com.vivo.fo.cliente.vo.impl.UnidadeSelecionadaVODocumentImpl.UnidadeSelecionadaVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class StatusController extends AbstractAction {

	private static final long serialVersionUID = -1371770411602434554L;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.correspondenciaDevolvida.CorrespondenciaDevolvida correspondenciaDevolvida;

	private static Logger log = new Logger("clientes");

	private String user = null;

	private StatusForm statusForm;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("incluialterastatuscorresp".equals(mapping.getParameter())) {
			return incluialterastatuscorresp(mapping, form, request, response);
		} else if ("salvarstatuscorresp".equals(mapping.getParameter())) {
			return salvarstatuscorresp(mapping, form, request, response);
		} else if ("excluirstatuscorresp".equals(mapping.getParameter())) {
			return excluirstatuscorresp(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="Status.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			StatusForm form = (StatusForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("StatusController:begin(" + user + ") >> INICIALIZADO");

			log.debug("TipoCorrespController:begin()");

			// Criar a instancia do actioForm
			statusForm = form;
			if (request.getAttribute("msg") != null) {
				statusForm.setInMsgRetorno(request.getAttribute("msg").toString());
			}

			// obtem a lista de Assuntos e atualiza o ActionForm
			ListaStatusCorrespVO listaStatusCorrespVO = correspondenciaDevolvida.getListaStatusCorrespVO(user);
			statusForm.getListaStatusCorrespVO().setStatusCorrespVOArray(listaStatusCorrespVO.getStatusCorrespVOArray());
			if (request.getAttribute("inMsg") != null) {
				statusForm.setInMsgRetorno(request.getAttribute("inMsg").toString());
			}
			log.debug("StatusController:begin(" + user + ") >> FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("StatusController:begin(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IncluiAlteraStatus.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluialterastatuscorresp(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			StatusForm form = (StatusForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("StatusController:incluialterastatuscorresp(" + user + ") >> INICIALIZADO");

			String operacao = request.getParameter("operacao");

			if (operacao.equalsIgnoreCase("UPDATE")) {
				log.debug("StatusController:incluialterastatuscorresp(" + user + ") >> Alteraçao");

				int index = Integer.parseInt(request.getParameter("index"));
				request.setAttribute("operacao", "UPDATE");
				request.setAttribute("index", String.valueOf(index));
				request.setAttribute("Titulo", "Alterar");
				form.setOperacao("UPDATE");
				form.getListaStatusCorrespVO().setStatusCorrespVOArray(statusForm.getListaStatusCorrespVO().getStatusCorrespVOArray());
				form.setId(statusForm.getListaStatusCorrespVO().getStatusCorrespVOArray()[index].getId());
				form.setSigla(statusForm.getListaStatusCorrespVO().getStatusCorrespVOArray()[index].getSigla());
				form.setDescricao(statusForm.getListaStatusCorrespVO().getStatusCorrespVOArray()[index].getDescricao());
				form.setInDisponibilidade((statusForm.getListaStatusCorrespVO().getStatusCorrespVOArray()[index].getInDisponibilidade()));
				form.setQtDiasValido(statusForm.getListaStatusCorrespVO().getStatusCorrespVOArray()[index].getQtDiasValido());
				form.setIdProximo(statusForm.getListaStatusCorrespVO().getStatusCorrespVOArray()[index].getIdProximo());
				form.setUnidadeVO(correspondenciaDevolvida.getUnidadeDisponivelVO(user));
				form.getUnidadeVO().setUnidadeSelecionadaVOArray(statusForm.getListaStatusCorrespVO().getStatusCorrespVOArray()[index].getUnidadeVO().getUnidadeSelecionadaVOArray());

				statusForm = form;

			} else {
				log.debug("StatusController:incluialterastatuscorresp(" + user + ") >> Inclusao");

				// Preenche o campo UnidadeDisponivel
				statusForm.setUnidadeVO(correspondenciaDevolvida.getUnidadeDisponivelVO(user));

				request.setAttribute("operacao", "INSERT");
				request.setAttribute("index", "");
				request.setAttribute("Titulo", "Novo");

				form.setSigla(ConstantesCRM.SVAZIO);
				form.setDescricao(ConstantesCRM.SVAZIO);
				form.setQtDiasValido(ConstantesCRM.SVAZIO);
				form.setOperacao("INSERT");
			}
			log.debug("StatusController:incluialterastatuscorresp(" + user + ") >> FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("StatusController:incluialterastatuscorresp(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/status/begin.do");
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
	public ActionForward salvarstatuscorresp(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			StatusForm form = (StatusForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("StatusController:salvarstatuscorresp(" + user + ") >> INICIALIZADO");

			StatusCorrespVO[] md = new StatusCorrespVOImpl[1];
			String resposta = null;

			String desc = form.getDescricao().trim();
			String sigla = form.getSigla().trim();

			if (desc.length() > 0 && sigla.length() > 0) {

				md[0] = StatusCorrespVO.Factory.newInstance();
				md[0].setUnidadeVO(UnidadeVO.Factory.newInstance());
				// md[0].setUnidadeVO();
				md[0].setDescricao(desc);
				md[0].setSigla(sigla);
				md[0].setInDisponibilidade(form.getInDisponibilidade());
				md[0].setQtDiasValido(form.getQtDiasValido());
				md[0].setIdProximo(form.getIdProximo());

				String[] unidadesSelecionadas = form.getUnidadesSelecionadas();
				UnidadeSelecionadaVO[] usVO = new UnidadeSelecionadaVO[unidadesSelecionadas.length];

				int x;
				// Monta a lista de unidade selecionadas
				for (x = 0; x < unidadesSelecionadas.length; x++) {
					usVO[x] = UnidadeSelecionadaVO.Factory.newInstance();
					usVO[x].setIdUnidade(Integer.parseInt(unidadesSelecionadas[x]));
				}
				
				// verifica se é uma alteração ou inclusão e atualiza o ActionForm
				//if (request.getParameter("operacao").equalsIgnoreCase("UPDATE")) {
				if (form.getOperacao().equalsIgnoreCase("UPDATE")) {
					// obter informçoes da página de incl/Alt e montar a estrutura para gravacao
					md[0].setInDisponibilidade(form.getInDisponibilidade());
					md[0].setId(form.getId());
					md[0].getUnidadeVO().setUnidadeSelecionadaVOArray(usVO);
					ListaStatusCorrespVO listaStatusCorrespVO = ListaStatusCorrespVO.Factory.newInstance();
					listaStatusCorrespVO.setStatusCorrespVOArray(md);

					// efetua gravação do conteudo
					resposta = correspondenciaDevolvida.setAlterarStatusCorresp(user, listaStatusCorrespVO);
					String index = (request.getParameter("index"));
					request.setAttribute("operacao", "UPDATE");
					request.setAttribute("index", index);

				} else if (form.getOperacao().equalsIgnoreCase("INSERT")) {
					md[0].getUnidadeVO().setUnidadeSelecionadaVOArray(usVO);
					ListaStatusCorrespVO listaStatusCorrespVO = ListaStatusCorrespVO.Factory.newInstance();
					listaStatusCorrespVO.setStatusCorrespVOArray(md);
					// efetua gravação do conteudo
					resposta = correspondenciaDevolvida.setGravarStatusCorresp(user, listaStatusCorrespVO);
					request.setAttribute("operacao", "INSERT");
					request.setAttribute("index", "");
				}

				if (resposta.indexOf("DUPLICATE KEY") > 0) {
					log.debug("StatusController:salvarstatuscorresp(" + user + ") >> FINALIZADO (Duplicate key)");
					statusForm.setInMsgRetorno("true");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("success1");
				} else {
					log.debug("StatusController:salvarstatuscorresp(" + user + ") >> FINALIZADO");
					statusForm.setInMsgRetorno("false");
					request.setAttribute("inMsg", "false");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("success1");
				}
			} else {
				statusForm.setInMsgRetorno("vazio");
				log.debug("StatusController:salvarstatuscorresp(" + user + ") >> FINALIZADO");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("success1");
			}

		} catch (Exception e) {
			log.error("StatusController:salvarstatuscorresp(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/status/begin.do");
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
	public ActionForward excluirstatuscorresp(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			StatusForm form = (StatusForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("StatusController:excluirstatuscorresp(" + user + ") >> INICIALIZADO");

			String resposta = null;

			log.debug("TipoCorrespController:excluirstatuscorresp()");

			String codigo = String.valueOf(statusForm.getListaStatusCorrespVO().getStatusCorrespVOArray(Integer.parseInt(request.getParameter("codigo"))).getId());
			log.debug("StatusController:excluirstatuscorresp(" + user + ") >> codigo: " + codigo);

			resposta = correspondenciaDevolvida.removeStatus(user, codigo);

			if (resposta.indexOf("</mensagem>") > 0 || resposta.indexOf("child record found") > 0) {
				log.debug("StatusController:excluirstatuscorresp(" + user + ") >> Exclusão não permitida");
				form.setInMsgRetorno("naoExclui");

			} else {
				form.setInMsgRetorno("exclui");
			}

			log.debug("StatusController:excluirstatuscorresp(" + user + ") >> FINALIZADO");

			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("StatusController:excluirstatuscorresp(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/status/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	public static class StatusForm extends ActionForm {

		private static final long serialVersionUID = -3046087487970220863L;

		private String inDisponibilidade;
		private String inMsgRetorno = null;
		private String sigla;
		private String descricao;
		private String qtdiasvalido = null;
		private int idproximo;
		private int id;
		private int idanterior;
		private StatusCorrespVO[] statusCorrespVO;
		private UnidadeVO unidadeVO;
		private UnidadeSelecionadaVO[] unidadeSelecionadaVO;
		private UnidadeDisponivelVO[] unidadeDisponivelVO;
		private ListaStatusCorrespVO listaStatusCorrespVO;
		private String[] unidadesDisponiveis;
		private String[] unidadesSelecionadas;
		private UnidadeDisponivelVO[] unidadesDisponiveisAlterar;
		private UnidadeSelecionadaVO[] unidadesSelecionadasAlterar;
		private String operacao;

		public String getOperacao() {
			return operacao;
		}

		public void setOperacao(String operacao) {
			this.operacao = operacao;
		}

		// Default Constructor
		public StatusForm() {
			// inicializa conteúdos
			sigla = ConstantesCRM.SVAZIO;
			descricao = ConstantesCRM.SVAZIO;

			// inicializa VO
			listaStatusCorrespVO = ListaStatusCorrespVO.Factory.newInstance();
			statusCorrespVO = new StatusCorrespVOImpl[1];
			statusCorrespVO[0] = StatusCorrespVO.Factory.newInstance();
			unidadeVO = UnidadeVO.Factory.newInstance();
			unidadeSelecionadaVO = new UnidadeSelecionadaVOImpl[0];
			unidadeDisponivelVO = new UnidadeDisponivelVOImpl[0];

			listaStatusCorrespVO.setStatusCorrespVOArray(statusCorrespVO);
			listaStatusCorrespVO.getStatusCorrespVOArray(0).setUnidadeVO(unidadeVO);
			listaStatusCorrespVO.getStatusCorrespVOArray(0).getUnidadeVO().setUnidadeDisponivelVOArray(unidadeDisponivelVO);
			listaStatusCorrespVO.getStatusCorrespVOArray(0).getUnidadeVO().setUnidadeSelecionadaVOArray(unidadeSelecionadaVO);

			unidadesDisponiveis = new String[0];
			unidadesSelecionadas = new String[0];
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getId() {
			return id;
		}

		public UnidadeSelecionadaVO[] getUnidadesSelecionadasAlterar() {
			return unidadesSelecionadasAlterar;
		}

		public void setUnidadesSelecionadasAlterar(UnidadeSelecionadaVO[] strings) {
			unidadesSelecionadasAlterar = strings;
		}

		public UnidadeDisponivelVO[] getUnidadesDisponiveisAlterar() {
			return unidadesDisponiveisAlterar;
		}

		public void setUnidadesDisponiveisAlterar(UnidadeDisponivelVO[] strings) {
			unidadesDisponiveisAlterar = strings;
		}

		public String[] getUnidadesSelecionadas() {
			return unidadesSelecionadas;
		}

		public void setUnidadesSelecionadas(String[] strings) {
			unidadesSelecionadas = strings;
		}

		public String[] getUnidadesDisponiveis() {
			return unidadesDisponiveis;
		}

		public void setUnidadesDisponiveis(String[] strings) {
			unidadesDisponiveis = strings;
		}

		public UnidadeVO getUnidadeVO() {
			return unidadeVO;
		}

		public void setUnidadeVO(UnidadeVO string) {
			unidadeVO = string;
		}

		public String getSigla() {
			return sigla;
		}

		public void setSigla(String string) {
			sigla = string;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String string) {
			descricao = string;
		}

		public String getQtDiasValido() {
			return qtdiasvalido;
		}

		public void setQtDiasValido(String qtdiasvalido) {
			this.qtdiasvalido = qtdiasvalido;
		}

		public int getIdProximo() {
			return idproximo;
		}

		public void setIdProximo(int idproximo) {
			this.idproximo = idproximo;
		}

		public int getIdAnterior() {
			return idanterior;
		}

		public void setIdAnterior(int idanterior) {
			this.idanterior = idanterior;
		}

		public ListaStatusCorrespVO getListaStatusCorrespVO() {
			return listaStatusCorrespVO;
		}

		public void setListaStatusCorrespVO(ListaStatusCorrespVO string) {
			listaStatusCorrespVO = string;
		}

		public void setTipoCorresp(String string) {
			descricao = string;
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

	public StatusForm getStatusForm() {
		return this.statusForm;
	}

	public void setStatusForm(StatusForm statusForm) {
		this.statusForm = statusForm;
	}

}
