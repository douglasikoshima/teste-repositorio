package campanha.AgendaContato;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.ListaAgendamentoCampanhaVODocument.ListaAgendamentoCampanhaVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;

@SuppressWarnings({"rawtypes","unchecked"})
public class AgendaContatoController extends AbstractAction {

	private static final long serialVersionUID = -4921195581662119775L;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.agendaContato.AgendaContatoCmpFacade agendaContatoFac;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.atendimento.ListaAtendimentoCampanhaFacade listaAtendimentoCampanhaFac;

	protected global.Global globalApp = new global.Global();

	public ArrayList lAgendamento = new ArrayList();
	private String user = ConstantesCRM.SVAZIO;
	public String idLinha = null;
	public String idPessoaUsuario = null;

	private AgendaContatoForm agendaContatoForm = new AgendaContatoForm();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("agendaContato".equals(mapping.getParameter())) {
			return agendaContato(mapping, form, request, response);
		} else if ("Done".equals(mapping.getParameter())) {
			return Done(mapping, form, request, response);
		} else if ("voltar".equals(mapping.getParameter())) {
			return voltar(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="AgendaContato.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		user = ConstantesCRM.getCurrentUser(request.getSession());
		String idUsuarioSessao = (String) request.getSession().getAttribute("usuarioIdSession");

		// Limpo o formulario
		agendaContatoForm.setData(ConstantesCRM.SVAZIO);
		agendaContatoForm.setDsObservacao(ConstantesCRM.SVAZIO);
		agendaContatoForm.setHoras(ConstantesCRM.SVAZIO);
		agendaContatoForm.setMinutos(ConstantesCRM.SVAZIO);
		agendaContatoForm.setNomeContato(ConstantesCRM.SVAZIO);
		agendaContatoForm.setTelefoneContato(ConstantesCRM.SVAZIO);
		agendaContatoForm.setStatus(request.getParameter("status"));
		agendaContatoForm.setMotivo(request.getParameter("motivo"));
		agendaContatoForm.setSubMotivo(request.getParameter("sub"));

		listaAtendimentoCampanhaFac.setQuestionarioStatus(user, (String) request.getSession().getAttribute(ConstantesCRM.CT_QU_IDENTIFICADOR_ATENDIMENTO), agendaContatoForm.getStatus(), agendaContatoForm.getMotivo(), agendaContatoForm.getSubMotivo(), (String) request.getSession().getAttribute("idSubCampanhaHistorico"));

		carregaAgendaContato(idUsuarioSessao);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public void carregaAgendaContato(String idUsuarioSessao) throws Exception {
		lAgendamento.clear();
		// ListaAgendamentoCampanhaVO lista = agendaContatoFac.getAgendaContato(user ,
		// (String)request.getSession().getAttribute("idListaConteudo"));
		ListaAgendamentoCampanhaVO lista = agendaContatoFac.getAgendaContato(user, idUsuarioSessao);

		for (int i = 0; i < lista.getAgendamentoCampanhaVOArray().length; i++) {
			lAgendamento.add(lista.getAgendamentoCampanhaVOArray()[i]);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="AgendaContato.jsp"
	 * @jpf:forward name="salvarAtendimento" path="../ExecutarCampanha/fechaFrame.html"
	 * @jpf:forward name="salvarTelaInicial" path="../ExecutarCampanha/telaInicial.do"
	 * @jpf:forward name="incioAgendamento" path="begin.do"
	 */
	protected ActionForward agendaContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AgendaContatoForm form = (AgendaContatoForm) formParam;

		String destino = ConstantesCRM.SUCCESS;

		form.setStatus(agendaContatoForm.getStatus());
		form.setMotivo(agendaContatoForm.getMotivo());
		form.setSubMotivo(agendaContatoForm.getSubMotivo());

		agendaContatoForm = form;

		if (request.getParameter(ConstantesCRM.SACTION) != null && request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("ok")) {
			String[] dados = new String[10];

			dados[0] = user; // (String)request.getSession().getAttribute(ConstantesCRM.CT_QU_IDENTIFICADOR_ATENDIMENTO);
			dados[1] = form.getData() + " " + form.getHoras() + ":" + form.getMinutos();
			dados[2] = (String) request.getSession().getAttribute(ConstantesCRM.CT_QU_IDENTIFICADOR_ATENDIMENTO);
			dados[3] = form.getTelefoneContato();
			dados[4] = form.getDsObservacao();
			dados[5] = form.getNomeContato();
			dados[6] = ConstantesCRM.SZERO;
			dados[7] = form.getStatus();
			dados[8] = form.getMotivo();
			dados[9] = form.getSubMotivo();

			agendaContatoFac.addAgendarContato(user, dados);

			if (ConstantesCRM.SONE.equals(request.getSession().getAttribute(ConstantesCRM.CT_AT_OPERACAO_EXECUTAR_CAMPANHA))) {
				destino = "salvarTelaInicial"; // Volta para o inicio da tela inicial
			} else {
				destino = "salvarAtendimento"; // Volta para o inicio de atendimento de campanha
			}

			/*
			 * Removo as variaveis de sessão
			 */
			request.getSession().removeAttribute(ConstantesCRM.CT_QU_IDENTIFICADOR_ATENDIMENTO);
			request.getSession().removeAttribute("idCanalCampanha");
			request.getSession().removeAttribute("idSubCampanhaHistorico");
			request.getSession().removeAttribute("numeroLinha");
			request.getSession().removeAttribute("propietarioLinha");
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(destino);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="AgendarContatoDone"
	 */
	protected ActionForward Done(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="voltar" path="/campanha/ExecutarCampanha/QuestionarioDone.do"
	 */
	protected ActionForward voltar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("voltar");
	}

	public static class AgendaContatoForm extends ActionForm {

		private static final long serialVersionUID = 2900220122480244603L;

		private String dsObservacao;
		// private String comentario;
		private String minutos;
		private String horas;
		// private String hora;
		private String data;
		private String telefoneContato;
		private String nomeContato;
		private String status;
		private String motivo;
		private String subMotivo;

		public void setStatus(String status) {
			this.status = status;
		}

		public String getStatus() {
			return this.status;
		}

		public void setMotivo(String motivo) {
			this.motivo = motivo;
		}

		public String getMotivo() {
			return this.motivo;
		}

		public void setSubMotivo(String subMotivo) {
			this.subMotivo = subMotivo;
		}

		public String getSubMotivo() {
			return this.subMotivo;
		}

		public void setNomeContato(String nomeContato) {
			this.nomeContato = nomeContato;
		}

		public String getNomeContato() {
			return this.nomeContato;
		}

		public void setTelefoneContato(String telefoneContato) {
			this.telefoneContato = telefoneContato;
		}

		public String getTelefoneContato() {
			return this.telefoneContato;
		}

		public void setData(String data) {
			this.data = data;
		}

		public String getData() {
			return this.data;
		}

		public void setHoras(String horas) {
			this.horas = horas;
		}

		public String getHoras() {
			return this.horas;
		}

		public void setMinutos(String minutos) {
			this.minutos = minutos;
		}

		public String getMinutos() {
			return this.minutos;
		}

		public void setDsObservacao(String dsObservacao) {
			this.dsObservacao = dsObservacao;
		}

		public String getDsObservacao() {
			return this.dsObservacao;
		}
	}
}
