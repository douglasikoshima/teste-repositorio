package cliente.CDevolvida.motivodevolucao;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ListaMotivoDevolucaoVODocument.ListaMotivoDevolucaoVO;
import br.com.vivo.fo.cliente.vo.MotivoDevolucaoVODocument.MotivoDevolucaoVO;
import br.com.vivo.fo.cliente.vo.impl.MotivoDevolucaoVODocumentImpl.MotivoDevolucaoVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class MotivoDevolucaoController extends AbstractAction {

	private static final long serialVersionUID = 6991079666528984578L;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.correspondenciaDevolvida.CorrespondenciaDevolvida correspondenciaDevolvida;

	// variáveis e objetos
	private String user = null;
	private MotivoDevolucaoForm motivoDevolucaoForm = null;

	private static Logger log = new Logger("clientes");

	protected global.Global globalApp = new global.Global();

	protected boolean alwaysTrackPreviousPage()
	{
		return true;
	}

	protected boolean alwaysTrackPreviousAction()
	{
		return true;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("incluialteramotivodevolucao".equals(mapping.getParameter())) {
			return incluialteramotivodevolucao(mapping, form, request, response);
		} else if ("salvarmotivodevolucao".equals(mapping.getParameter())) {
			return salvarmotivodevolucao(mapping, form, request, response);
		} else if ("excluirmotivodevolucao".equals(mapping.getParameter())) {
			return excluirmotivodevolucao(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="MotivoDevolucao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			MotivoDevolucaoForm form = (MotivoDevolucaoForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("MotivoDevolucaoController:begin("+user+") >> INICIALIZADO");

			//Criar a instancia do actioForm
			motivoDevolucaoForm = form;
			if(request.getAttribute("msg")!=null){
				log.debug("MotivoDevolucaoController:begin("+user+") >> Mensagem de retorno: "+request.getAttribute("msg").toString());
				motivoDevolucaoForm.setInMsgRetorno(request.getAttribute("msg").toString());
			}

			// obtem a lista de Assuntos e atualiza o ActionForm
			ListaMotivoDevolucaoVO listaMotivoDevolucaoVO = correspondenciaDevolvida.getListaMotivoDevolucao(user);
			motivoDevolucaoForm.getListaMotivoDevolucaoVO().setMotivoDevolucaoVOArray(listaMotivoDevolucaoVO.getMotivoDevolucaoVOArray());
			if (request.getAttribute("inMsg") != null){
				motivoDevolucaoForm.setInMsgRetorno(request.getAttribute("inMsg").toString());
			}
			log.debug("MotivoDevolucaoController:begin("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			log.error("MotivoDevolucaoController:begin(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IncluiAlteraMotivoDevolucao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluialteramotivodevolucao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			MotivoDevolucaoForm form = (MotivoDevolucaoForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("MotivoDevolucaoController:incluialteramotivodevolucao("+user+") >> INICIALIZADO");

			// obtem o tipo de operação
			String operacao = request.getParameter("operacao");

			// parametros para página de alteração
			if(operacao.equalsIgnoreCase("UPDATE")) {
				int index = Integer.parseInt(request.getParameter("index"));
				request.setAttribute("operacao", "UPDATE");
				request.setAttribute("index", String.valueOf(index));
				request.setAttribute("Titulo", "Alterar");


				motivoDevolucaoForm.setSigla(motivoDevolucaoForm.getListaMotivoDevolucaoVO().getMotivoDevolucaoVOArray()[index].getSigla());
				motivoDevolucaoForm.setDescricao(motivoDevolucaoForm.getListaMotivoDevolucaoVO().getMotivoDevolucaoVOArray()[index].getDescricao());
				motivoDevolucaoForm.setId(motivoDevolucaoForm.getListaMotivoDevolucaoVO().getMotivoDevolucaoVOArray()[index].getId());
				motivoDevolucaoForm.setInDisponibilidade(motivoDevolucaoForm.getListaMotivoDevolucaoVO().getMotivoDevolucaoVOArray()[index].getInDisponibilidade());

			}
			else {
				request.setAttribute("operacao", "INSERT");
				request.setAttribute("index", ConstantesCRM.SVAZIO);
				request.setAttribute("Titulo", "Novo");
				motivoDevolucaoForm.setSigla(ConstantesCRM.SVAZIO);
				motivoDevolucaoForm.setDescricao(ConstantesCRM.SVAZIO);
			}
		} catch (Exception e) {
			log.error("MotivoDevolucaoController:incluialteramotivodevolucao(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/motivodevolucao/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		log.debug("MotivoDevolucaoController:incluialteramotivodevolucao("+user+") >> FINALIZADO");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="success1" path="executa.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarmotivodevolucao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		try{
			MotivoDevolucaoForm form = (MotivoDevolucaoForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("MotivoDevolucaoController:salvarmotivodevolucao("+user+") >> INICIALIZADO");

			ListaMotivoDevolucaoVO listaMotivoDevolucaoVO = ListaMotivoDevolucaoVO.Factory.newInstance();
			String resposta = null;

			String desc = form.getDescricao().trim();
			String sigla = form.getSigla().trim();

			if (desc.length() > 0 && sigla.length() > 0) {

				// verifica se é uma alteração ou inclusão e atualiza o ActionForm
				if(request.getParameter("operacao").equalsIgnoreCase("UPDATE")) {

					log.debug("MotivoDevolucaoController:salvarmotivodevolucao("+user+") >> Alteraçao");

					// obter informçoes da página de incl/Alt e montar a estrutura para gravacao
					MotivoDevolucaoVO[] md = new MotivoDevolucaoVOImpl[1];
					md[0] = MotivoDevolucaoVO.Factory.newInstance();
					md[0].setDescricao(desc);
					md[0].setSigla(sigla);
					md[0].setId(form.getId());
					md[0].setInDisponibilidade(form.getInDisponibilidade());

					listaMotivoDevolucaoVO.setMotivoDevolucaoVOArray(md);
					// efetua gravação do conteudo
					resposta = correspondenciaDevolvida.setAlterarMotivoDevolucao(user, listaMotivoDevolucaoVO);
					String index = request.getParameter("index");
					request.setAttribute("operacao", "UPDATE");
					request.setAttribute("index", index);
				}else if(request.getParameter("operacao").equalsIgnoreCase("INSERT")) {

					log.debug("MotivoDevolucaoController:salvarmotivodevolucao("+user+") >> Inclusao");

					//Colocar aqui a estrutura para gravar
					MotivoDevolucaoVO[] md = new MotivoDevolucaoVOImpl[1];
					md[0] = MotivoDevolucaoVO.Factory.newInstance();
					md[0].setDescricao(desc);
					md[0].setSigla(sigla);


					listaMotivoDevolucaoVO.setMotivoDevolucaoVOArray(md);

					// efetua gravação do conteudo
					resposta = correspondenciaDevolvida.setGravarMotivoDevolucao(user, listaMotivoDevolucaoVO);
					request.setAttribute("operacao", "INSERT");
					request.setAttribute("index", ConstantesCRM.SVAZIO);
				}

				if(resposta.indexOf("DUPLICATE KEY")>0){
					motivoDevolucaoForm.setInMsgRetorno("true");
					log.debug("MotivoDevolucaoController:salvarmotivodevolucao("+user+") >> FINALIZADO (Duplicate Key)");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("success1");
				}else{
					log.debug("MotivoDevolucaoController:salvarmotivodevolucao("+user+") >> FINALIZADO");
					motivoDevolucaoForm.setInMsgRetorno("false");
					request.setAttribute("inMsg", "false");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("success1");
				}

			}else{
				log.debug("MotivoDevolucaoController:salvarmotivodevolucao("+user+") >> INICIALIZADO");
				motivoDevolucaoForm.setInMsgRetorno("vazio");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("success1");
			}
		} catch (Exception e) {
			log.error("MotivoDevolucaoController:salvarmotivodevolucao(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/motivodevolucao/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward excluirmotivodevolucao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		MotivoDevolucaoForm form = (MotivoDevolucaoForm) formParam;

		try {

			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("MotivoDevolucaoController:excluirmotivodevolucao("+user+") >> INICIALIZADO");

			String resposta = null;
			log.debug("MotivoDevolucaoController:excluirmotivodevolucao()");

			String codigo = String.valueOf(motivoDevolucaoForm.getListaMotivoDevolucaoVO().getMotivoDevolucaoVOArray(Integer.parseInt(request.getParameter("codigo"))).getId());

			resposta = correspondenciaDevolvida.removeMotivo(user, codigo);

			if (resposta.indexOf("</mensagem>")>0){
				log.debug("MotivoDevolucaoController:excluirmotivodevolucao("+user+") >> Exclusao nao permitida");
				form.setInMsgRetorno("naoExclui");
			}else{
				log.debug("MotivoDevolucaoController:excluirmotivodevolucao("+user+") >> Exclusao Permitida");
				form.setInMsgRetorno("exclui");
			}
			log.debug("MotivoDevolucaoController:excluirmotivodevolucao("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} catch (Exception e) {
			log.error("MotivoDevolucaoController:excluirmotivodevolucao(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/CDevolvida/motivodevolucao/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class MotivoDevolucaoForm extends ActionForm{
		private String inDisponibilidade;


		private String inMsgRetorno = null;
		private String descricao;
		private String sigla;
		private int id;
		private ListaMotivoDevolucaoVO listaMotivoDevolucaoVO;

		// Default Constructor
		public MotivoDevolucaoForm() {
			// inicializa conteúdos
			descricao = ConstantesCRM.SVAZIO;
			sigla     = ConstantesCRM.SVAZIO;

			// inicializa VO
			listaMotivoDevolucaoVO = ListaMotivoDevolucaoVO.Factory.newInstance();
			listaMotivoDevolucaoVO.setMotivoDevolucaoVOArray(new MotivoDevolucaoVOImpl[0]);
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

		public ListaMotivoDevolucaoVO getListaMotivoDevolucaoVO() {
			return listaMotivoDevolucaoVO;
		}

		public void setMotivoDevolucao(String string) {
			descricao = string;
		}

		public void setListaMotivoDevolucaoVO(ListaMotivoDevolucaoVO listaMotivoDevolucaoVO) {
			this.listaMotivoDevolucaoVO = listaMotivoDevolucaoVO;
		}

		public void setInMsgRetorno(String inMsgRetorno)
		{
			this.inMsgRetorno = inMsgRetorno;
		}

		public String getInMsgRetorno()
		{
			return this.inMsgRetorno;
		}

		public void setInDisponibilidade(String inDisponibilidade)
		{
			this.inDisponibilidade = inDisponibilidade;
		}

		public String getInDisponibilidade()
		{
			return this.inDisponibilidade;
		}
	}

	public MotivoDevolucaoForm getMotivoDevolucaoForm() {
		return this.motivoDevolucaoForm;
	}

}
