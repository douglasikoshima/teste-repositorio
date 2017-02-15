package cliente.CDevolvida.telaInicial;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.CorrespDevolvidaTelaInicialVODocument.CorrespDevolvidaTelaInicialVO;
import br.com.vivo.fo.cliente.vo.EnderecoBaseVODocument.EnderecoBaseVO;
import br.com.vivo.fo.cliente.vo.PaisVODocument.PaisVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.cliente.vo.TratarCorrespDevolvidaVODocument.TratarCorrespDevolvidaVO;
import br.com.vivo.fo.cliente.vo.TratarCorrespDevolvidaVODocument.TratarCorrespDevolvidaVO.ListaStatus;
import br.com.vivo.fo.cliente.vo.TratarCorrespDevolvidaVODocument.TratarCorrespDevolvidaVO.ListaStatusCorresp;
import br.com.vivo.fo.cliente.vo.UFVODocument.UFVO;
import br.com.vivo.fo.cliente.vo.impl.PaisVODocumentImpl.PaisVOImpl;
import br.com.vivo.fo.cliente.vo.impl.TratarCorrespDevolvidaVODocumentImpl.TratarCorrespDevolvidaVOImpl.ListaStatusCorrespImpl;
import br.com.vivo.fo.cliente.vo.impl.TratarCorrespDevolvidaVODocumentImpl.TratarCorrespDevolvidaVOImpl.ListaStatusImpl;
import br.com.vivo.fo.cliente.vo.impl.UFVODocumentImpl.UFVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import cliente.URLErro;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class tratarCorrespTIController extends AbstractAction {

	private static final long serialVersionUID = 4391423335086097681L;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.correspondenciaDevolvida.CorrespondenciaDevolvida correspDevolvidaFacadeControl;

	private static Logger log = new Logger("clientes");

	private TratarCorrespDevolvidaVO tratarCorrespDevolvida;
	private TratarForm tratarForm;
	private String user = null;

	public TratarForm getTratarForm() {
		return this.tratarForm;
	}

	private CorrespDevolvidaTelaInicialVO correspDevolvida;
	private FiltroForm filtroForm;

	public FiltroForm getFiltroForm() {
		return this.filtroForm;
	}

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
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("DetalhesTratar".equals(mapping.getParameter())) {
			return DetalhesTratar(mapping, form, request, response);
		} else if ("manterDevolvida".equals(mapping.getParameter())) {
			return manterDevolvida(mapping, form, request, response);
		} else if ("salvaDadosTratar".equals(mapping.getParameter())) {
			return salvaDadosTratar(mapping, form, request, response);

		} else if ("limparConsulta".equals(mapping.getParameter())) {
			return limparConsulta(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="manterDevolvida.do"
	 * @jpf:forward name="reloadPesquisa" path="filtroCorrespTI.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="refazPesquisa" path="pesquisar.do?destino=pesquisa"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {

			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:begin(" + user + ") >> INICIALIZADO");

			if (request.getParameter("start") != null) {
				request.setAttribute("start", "true");
			}

			if (request.getAttribute("retornoSalvaDados") != null) {

				log.debug("tratarCorrespController:begin(" + user + ") >> Retorno de dados salvos");

				filtroForm.setInStart("true");

				if (!filtroForm.filtroCorrespondencia.getDtRegistroIni().equals(ConstantesCRM.SVAZIO)) {
					request.setAttribute("dataInicial", filtroForm.filtroCorrespondencia.getDtRegistroIni());
					request.setAttribute("dataFinal", filtroForm.filtroCorrespondencia.getDtRegistroFim());
					request.setAttribute("idStatus", filtroForm.filtroCorrespondencia.getIdStatus());
					request.setAttribute("idTipoRelacionamento", filtroForm.filtroCorrespondencia.getIdTipoRelacionamento());
					log.debug("tratarCorrespController:begin(" + user + ") >> FINALIZADO");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}
				request.removeAttribute("retornoSalvaDados");
				log.debug("tratarCorrespController:begin(" + user + ") >> FINALIZADO");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);

			} else {

				if (this.filtroForm == null) {
					filtroForm = new FiltroForm();
					filtroForm.filtroCorrespondencia.setDtRegistroIni(ConstantesCRM.SVAZIO);
					filtroForm.filtroCorrespondencia.setDtRegistroFim(ConstantesCRM.SVAZIO);
					filtroForm.filtroCorrespondencia.setIdTipoRelacionamento(ConstantesCRM.SVAZIO);
					filtroForm.filtroCorrespondencia.setIdStatus(ConstantesCRM.SONE);
					log.debug("tratarCorrespController:begin(" + user + ") >> FINALIZADO");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				} else {

					if (!this.filtroForm.filtroCorrespondencia.getDtRegistroIni().equals(ConstantesCRM.SVAZIO)) {
						request.setAttribute("dataInicial", filtroForm.filtroCorrespondencia.getDtRegistroIni());
						request.setAttribute("dataFinal", filtroForm.filtroCorrespondencia.getDtRegistroFim());
						request.setAttribute("idStatus", filtroForm.filtroCorrespondencia.getIdStatus());
						request.setAttribute("idTipoRelacionamento", filtroForm.filtroCorrespondencia.getIdTipoRelacionamento());
						log.debug("tratarCorrespController:begin(" + user + ") >> FINALIZADO");
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward(ConstantesCRM.SUCCESS);
					} else {
						log.debug("tratarCorrespController:begin(" + user + ") >> FINALIZADO (reloadPesquisa)");
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward("reloadPesquisa");
					}
				}

			}

		} catch (Exception e) {
			log.error("tratarCorrespController:begin(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="filtroCorrespTI.jsp"
	 * @jpf:forward name="alterar" path="DetalhesTratar.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			FiltroForm form = (FiltroForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:pesquisar(" + user + ") >> INICIALIZADO");

			String destino;

			if (request.getParameter("destino").equalsIgnoreCase("pesquisa")) {

				log.debug("tratarCorrespController:pesquisar(" + user + ") >> Destino: pesquisa");

				CorrespDevolvidaTelaInicialVO.FiltroCorrespDevolvida pesquisaFiltro = CorrespDevolvidaTelaInicialVO.FiltroCorrespDevolvida.Factory.newInstance();
				pesquisaFiltro = form.filtroCorrespondencia;
				pesquisaFiltro.setIdPessoa(filtroForm.getIdPessoa());
				pesquisaFiltro.setIdTipoRelacionamento(form.getIdTipoRelacionamento());

				correspDevolvida = correspDevolvidaFacadeControl.getCorrespDevolvidaTI(user, pesquisaFiltro);
				correspDevolvida.setStatusCorrespondenciaArray(filtroForm.getCorrespDevolvidaVO().getStatusCorrespondenciaArray());
				filtroForm.setCorrespDevolvida(correspDevolvida);
				filtroForm.setFiltroCorrespondencia(form.getFiltroCorrespondencia());

				destino = ConstantesCRM.SUCCESS;

			} else {
				int indice = Integer.parseInt(request.getParameter("idArray"));
				String idCorresp = request.getParameter("idCorresp");
				filtroForm.setId(idCorresp);
				destino = "alterar";
			}

			log.debug("tratarCorrespController:pesquisar(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(destino);

		} catch (Exception e) {
			log.error("tratarCorrespController:pesquisar(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="tratarCorrespTI.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward DetalhesTratar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			TratarForm form = (TratarForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:DetalhesTratar(" + user + ") >> INICIALIZADO");

			tratarForm = new TratarForm();
			tratarForm.tratarCorrespDevolvida.setIdCorrespondenciaDevolvida(filtroForm.getId());
			tratarCorrespDevolvida = TratarCorrespDevolvidaVO.Factory.newInstance();
			tratarCorrespDevolvida = correspDevolvidaFacadeControl.getTratarCorrepDevolvida(user, tratarForm.tratarCorrespDevolvida);
			tratarForm.setTratarCorrespDevolvida(tratarCorrespDevolvida);
			tratarForm.setStatusAtual(tratarCorrespDevolvida.getIdStatusAtual());
			tratarForm.setIdStatus(tratarCorrespDevolvida.getIdStatusAtual());
			tratarForm.setIdPessoa(form.getIdPessoa());

			log.debug("tratarCorrespController:DetalhesTratar(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("tratarCorrespController:DetalhesTratar(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="filtroCorrespTI.jsp"
	 * @jpf:forward name="refazPesquisa" path="pesquisar.do?destino=pesquisa"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward manterDevolvida(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			FiltroForm form = (FiltroForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:manterDevolvida(" + user + ") >> INICIALIZADO");

			String idPessoa = new String();
			String inCliente = new String();

			// filtroForm = new FiltroForm();
			CorrespDevolvidaTelaInicialVO iniciarManter = CorrespDevolvidaTelaInicialVO.Factory.newInstance();
			CorrespDevolvidaTelaInicialVO.FiltroCorrespDevolvida iniciarFiltro = CorrespDevolvidaTelaInicialVO.FiltroCorrespDevolvida.Factory.newInstance();

			correspDevolvida = correspDevolvidaFacadeControl.getCorrespDevolvidaTI(user, iniciarFiltro);
			filtroForm.setCorrespDevolvida(correspDevolvida);

			if (((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento().equals(ConstantesCRM.STWO)) {
				idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
				inCliente = "true";
				log.debug("tratarCorrespController:manterDevolvida(" + user + ") >> Cliente");
			} else if (((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento().equals(ConstantesCRM.SONE)) {
				idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaUsuario();
				inCliente = "false";
				log.debug("tratarCorrespController:manterDevolvida(" + user + ") >> Usuário");
			} else {
				idPessoa = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdPessoaCliente();
			}

			filtroForm.setInCliente(inCliente);
			filtroForm.setIdPessoa(idPessoa);
			filtroForm.setIdTipoRelacionamento(((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS)).getIdTipoRelacionamento());

			if (request.getAttribute("dataInicial") != null) {
				filtroForm.filtroCorrespondencia.setDtRegistroIni(request.getAttribute("dataInicial").toString());
				filtroForm.filtroCorrespondencia.setDtRegistroFim(request.getAttribute("dataFinal").toString());
				filtroForm.filtroCorrespondencia.setIdTipoRelacionamento(request.getAttribute("idTipoRelacionamento").toString());
				filtroForm.filtroCorrespondencia.setIdStatus(request.getAttribute("idStatus").toString());
				request.removeAttribute("dataInicial");
				request.removeAttribute("dataFinal");
				request.removeAttribute("idStatus");
				request.removeAttribute("idTipoRelacionamento");
				log.debug("tratarCorrespController:manterDevolvida(" + user + ") >> FINALIZADO (Refaz Pesquisa)");

				request.setAttribute("filtroForm", filtroForm);

				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("refazPesquisa");

			} else {
				if (request.getAttribute("start") == "true") {
					filtroForm.filtroCorrespondencia.setIdStatus(ConstantesCRM.SONE);
				}
				log.debug("tratarCorrespController:manterDevolvida(" + user + ") >> FINALIZADO (Refaz Pesquisa)");
				request.setAttribute("filtroForm", filtroForm);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("refazPesquisa");
			}

		} catch (Exception e) {
			log.error("tratarCorrespController:manterDevolvida(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
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

	public ActionForward salvaDadosTratar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			TratarForm form = (TratarForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("tratarCorrespController:salvaDadosTratar(" + user + ") >> INICIALIZADO");

			TratarCorrespDevolvidaVO alteraStatus = TratarCorrespDevolvidaVO.Factory.newInstance();
			alteraStatus.setIdCorrespondenciaDevolvida(form.tratarCorrespDevolvida.getIdCorrespondenciaDevolvida());

			// Status
			alteraStatus.addNewListaStatus();
			alteraStatus.addNewListaStatusCorresp();
			alteraStatus.getListaStatusArray(0).setIdStatus(form.getStatusAtual());

			correspDevolvidaFacadeControl.setSalvarTratarCorresp(ConstantesCRM.SOK, user, alteraStatus, "TI");

			request.setAttribute("retornoSalvaDados", "true");

			log.debug("tratarCorrespController:salvaDadosTratar(" + user + ") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {

			log.error("tratarCorrespController:salvaDadosTratar(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, URLErro.getURLErro(request.getSession()));
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="filtroCorrespTI.jsp"
	 */
	public ActionForward limparConsulta(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		FiltroForm form = (FiltroForm) formParam;
		CorrespDevolvidaTelaInicialVO limpa = CorrespDevolvidaTelaInicialVO.Factory.newInstance();
		filtroForm.getCorrespDevolvidaVO().setListaCorrespDevolvidaArray(limpa.getListaCorrespDevolvidaArray());
		filtroForm.getFiltroCorrespondencia().setDtRegistroFim(ConstantesCRM.SVAZIO);
		filtroForm.getFiltroCorrespondencia().setDtRegistroIni(ConstantesCRM.SVAZIO);
		filtroForm.getFiltroCorrespondencia().setIdStatus(ConstantesCRM.SVAZIO);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class FiltroForm extends ActionForm {
		private String inStart;

		private String inCliente = new String();
		private String idTipoRelacionamento;
		private String idPessoa = new String();
		private String chkUsuarios = new String();
		private String chkCliente = new String();
		private String id = new String();
		private String idStatus = new String();
		private CorrespDevolvidaTelaInicialVO.StatusCorrespondencia[] statusCorrespondencia = new CorrespDevolvidaTelaInicialVO.StatusCorrespondencia[0];
		private CorrespDevolvidaTelaInicialVO.FiltroCorrespDevolvida filtroCorrespondencia;
		private CorrespDevolvidaTelaInicialVO.ListaCorrespDevolvida[] listaCorresp = new CorrespDevolvidaTelaInicialVO.ListaCorrespDevolvida[0];
		private CorrespDevolvidaTelaInicialVO correspDevolvidaVO;

		public FiltroForm() {
			correspDevolvidaVO = CorrespDevolvidaTelaInicialVO.Factory.newInstance();
			filtroCorrespondencia = CorrespDevolvidaTelaInicialVO.FiltroCorrespDevolvida.Factory.newInstance();
			correspDevolvidaVO.setStatusCorrespondenciaArray(statusCorrespondencia);
		}

		public CorrespDevolvidaTelaInicialVO getCorrespDevolvidaVO() {
			return this.correspDevolvidaVO;
		}

		public void setCorrespDevolvida(CorrespDevolvidaTelaInicialVO correspDevolvidaVO) {
			this.correspDevolvidaVO = correspDevolvidaVO;
		}

		public void setFiltroCorrespondencia(CorrespDevolvidaTelaInicialVO.FiltroCorrespDevolvida filtroCorrespondencia) {
			this.filtroCorrespondencia = filtroCorrespondencia;
		}

		public CorrespDevolvidaTelaInicialVO.FiltroCorrespDevolvida getFiltroCorrespondencia() {
			return this.filtroCorrespondencia;
		}

		public void setStatusCorrespondencia(CorrespDevolvidaTelaInicialVO.StatusCorrespondencia[] statusCorrespondencia) {
			this.statusCorrespondencia = statusCorrespondencia;
		}

		public CorrespDevolvidaTelaInicialVO.StatusCorrespondencia[] getStatusCorrespondencia() {
			return this.statusCorrespondencia;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return this.id;
		}

		public void setInCliente(String inCliente) {
			this.inCliente = inCliente;
		}

		public String getInCliente() {
			return this.inCliente;
		}

		public void setChkCliente(String chkCliente) {
			this.chkCliente = chkCliente;
		}

		public String getChkCliente() {
			return this.chkCliente;
		}

		public void setChkUsuarios(String chkUsuarios) {
			this.chkUsuarios = chkUsuarios;
		}

		public String getChkUsuarios() {
			return this.chkUsuarios;
		}

		public void setIdPessoa(String idPessoa) {
			this.idPessoa = idPessoa;
		}

		public String getIdPessoa() {
			return this.idPessoa;
		}

		public void setIdTipoRelacionamento(String idTipoRelacionamento) {
			this.idTipoRelacionamento = idTipoRelacionamento;
		}

		public String getIdTipoRelacionamento() {
			return this.idTipoRelacionamento;
		}

		public void setInStart(String inStart) {
			this.inStart = inStart;
		}

		public String getInStart() {
			return this.inStart;
		}
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class TratarForm extends ActionForm {
		private String idTipoRelacionamento;

		private String idPessoa;

		private String statusAtual;

		private String idPaisSelecionado = new String();

		private String idUFSelecionado = new String();

		private String dtStatus = new String();

		private String idStatus = new String();

		private PaisVO[] paisVO = new PaisVOImpl[0];

		private UFVO[] ufVO = new UFVOImpl[0];

		private EnderecoBaseVO enderecoTratar = EnderecoBaseVO.Factory.newInstance();

		private ListaStatusCorresp[] listaStatusCorresp = new ListaStatusCorrespImpl[0];

		private ListaStatus[] listaStatus = new ListaStatusImpl[0];

		private TratarCorrespDevolvidaVO tratarCorrespDevolvida;

		public TratarForm() {
			tratarCorrespDevolvida = TratarCorrespDevolvidaVO.Factory.newInstance();
			tratarCorrespDevolvida.setUFVOArray(ufVO);
			tratarCorrespDevolvida.setPaisVOArray(paisVO);
			enderecoTratar.setPaisVO(PaisVO.Factory.newInstance());
			enderecoTratar.setUFVO(UFVO.Factory.newInstance());
			tratarCorrespDevolvida.setEnderecoBaseVO(enderecoTratar);
			tratarCorrespDevolvida.setListaStatusArray(listaStatus);
			tratarCorrespDevolvida.setListaStatusCorrespArray(listaStatusCorresp);
		}

		public void setTratarCorrespDevolvida(TratarCorrespDevolvidaVO tratarCorrespDevolvida) {
			this.tratarCorrespDevolvida = tratarCorrespDevolvida;
		}

		public TratarCorrespDevolvidaVO getTratarCorrespDevolvida() {
			return this.tratarCorrespDevolvida;
		}

		public void setListaStatus(ListaStatusImpl[] listaStatus) {
			this.listaStatus = listaStatus;
		}

		public ListaStatus[] getListaStatus() {
			return this.listaStatus;
		}

		public void setListaStatusCorresp(ListaStatusCorrespImpl[] listaStatusCorresp) {
			this.listaStatusCorresp = listaStatusCorresp;
		}

		public ListaStatusCorresp[] getListaStatusCorresp() {
			return this.listaStatusCorresp;
		}

		public void setUfVO(UFVOImpl[] ufVO) {
			this.ufVO = ufVO;
		}

		public void setPaisVO(PaisVOImpl[] paisVO) {
			this.paisVO = paisVO;
		}

		public void setIdStatus(String idStatus) {
			this.idStatus = idStatus;
		}

		public String getIdStatus() {
			return this.idStatus;
		}

		public void setStatusAtual(String statusAtual) {
			this.statusAtual = statusAtual;
		}

		public String getStatusAtual() {
			return this.statusAtual;
		}

		public void setIdPessoa(String idPessoa) {
			this.idPessoa = idPessoa;
		}

		public String getIdPessoa() {
			return this.idPessoa;
		}

		public void setIdTipoRelacionamento(String idTipoRelacionamento) {
			this.idTipoRelacionamento = idTipoRelacionamento;
		}

		public String getIdTipoRelacionamento() {
			return this.idTipoRelacionamento;
		}
	}
}