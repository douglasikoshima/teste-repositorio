package senha.historicoSenha;

import java.util.ArrayList;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.WarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.senha.vo.SenhaMovimentoVODocument.SenhaMovimentoVO;
import br.com.vivo.fo.senha.vo.SenhaMovimentosVODocument.SenhaMovimentosVO;
import br.com.vivo.fo.senha.vo.SenhaOperarVODocument.SenhaOperarVO;

import com.indracompany.actions.AbstractAction;

public class HistoricoSenhaController extends AbstractAction {

	private static final long serialVersionUID = 7314801918684020755L;
	private static Logger log = new Logger("senha");
	public transient HistoricoConsultarForm historicoConsultarForm;
	// private SenhaMovimentosVO senhaMovimentosVo;
	private final String CD_SENHA = ConstantesCRM.SVAZIO;
	private final String ID_CANAL = ConstantesCRM.SONE;
	public final String TP_CLIENTE = ConstantesCRM.STWO;
	public final String TP_USUARIO = ConstantesCRM.SONE;

	@EJB
	private br.com.vivo.fo.ctrls.senha.operarSenha.OperarSenhaFacade operarSenhaFacade;

	@EJB
	private br.com.vivo.fo.ctrls.senha.historico.ConsultarHistoricoFacade consultarHistoricoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("historicoConsultarAction".equals(mapping.getParameter())) {
			return historicoConsultarAction(mapping, form, request, response);
		} else if ("solicitarSenhaAction".equals(mapping.getParameter())) {
			return solicitarSenhaAction(mapping, form, request, response);
		} else if ("reinicializarFraseAction".equals(mapping.getParameter())) {
			return reinicializarFraseAction(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="historicoConsultarAction.do"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HistoricoConsultarForm form = (HistoricoConsultarForm) formParam;
		log.debug("\t" + this.getClass().getName() + ":begin()");

		ParametrosVO paramVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		form.setIdUsuario(user);
		form.setIdPessoaCliente(paramVO.getIdPessoaCliente());
		form.setIdPessoaUsuario(paramVO.getIdPessoaUsuario());
		form.setTipoRelacionamento(paramVO.getIdTipoRelacionamento());
		form.setFoneArea(paramVO.getNrCodArea());
		form.setFoneNumero(paramVO.getNrLinha());

		SenhaMovimentosVO senhaMovimentosVo = consultarHistoricoFacade.ConsHistorico("0", form.getFoneArea(), form.getFoneNumero(), "", form.getTipoRelacionamento(), form.getIdPessoaCliente(), form.getIdPessoaUsuario(), form.getIdUsuario());

		form.setNmCliente(senhaMovimentosVo.getNmCliente());
		form.setNmUsuario(senhaMovimentosVo.getNmUsuario());
		form.setInBloqueado(senhaMovimentosVo.getInBloqueado());
		// inicializacao fake
		/*
		 * form.setIdPessoaCliente("10"); form.setIdPessoaUsuario("11"); form.setIdUsuario("2");
		 * form.setTipoRelacionamento(this.TP_USUARIO); form.setFoneArea("11"); form.setFoneNumero("12345678");
		 */
		this.historicoConsultarForm = form;

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="historicoSenha.jsp"
	 */
	public ActionForward historicoConsultarAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HistoricoConsultarForm form = (HistoricoConsultarForm) formParam;
		log.debug("\t" + this.getClass().getName() + ":historicoConsultarAction()");

		// Conforme a seleçao do tipo de pessoa (relacionamento) utilizar o idPessoaCliente
		// ou idPessoaUsuario
		String idPessoa;
		if (form.getTipoRelacionamento().equals(this.TP_CLIENTE)) {
			idPessoa = this.historicoConsultarForm.getIdPessoaCliente();
		} else {
			idPessoa = this.historicoConsultarForm.getIdPessoaUsuario();
		}

		SenhaMovimentosVO senhaMovimentosVo = consultarHistoricoFacade.ConsHistorico("1", form.getFoneArea(), form.getFoneNumero(), idPessoa, form.getTipoRelacionamento(), "", "", this.historicoConsultarForm.getIdUsuario());

		if (senhaMovimentosVo.getSenhaMovimentoVOArray() == null) {
			form.setMovimentoVo(new SenhaMovimentoVO[0]);
		} else {
			form.setMovimentoVo(senhaMovimentosVo.getSenhaMovimentoVOArray());
		}

		// Necessário para manter o conteúdo da consulta e outras informaçoes que não possuem
		// campos no form ou campos hidden
		form.setIdPessoaCliente(this.historicoConsultarForm.getIdPessoaCliente());
		form.setIdPessoaUsuario(this.historicoConsultarForm.getIdPessoaUsuario());
		form.setIdUsuario(this.historicoConsultarForm.getIdUsuario());

		// atualiza o formbean
		this.historicoConsultarForm = form;

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="historicoSenha.jsp"
	 */
	public ActionForward solicitarSenhaAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HistoricoConsultarForm form = (HistoricoConsultarForm) formParam;

		log.debug("\t" + this.getClass().getName() + ":solicitarSenhaAction()");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		// Conforme a seleçao do tipo de pessoa (relacionamento) utilizar o idPessoaCliente
		// ou idPessoaUsuario
		String idPessoa;
		if (form.getTipoRelacionamento().equals(this.TP_CLIENTE)) {
			idPessoa = this.historicoConsultarForm.getIdPessoaCliente();
		} else {
			idPessoa = this.historicoConsultarForm.getIdPessoaUsuario();
		}

		SenhaOperarVO senhaOperarVo = null;

		// garante que só 255 caracteres serão gravados
		form.setMotivo(form.getMotivo().length() > 255 ? form.getMotivo().substring(0, 255) : form.getMotivo());

		try {

			senhaOperarVo = operarSenhaFacade.solicitarSenha(ConstantesCRM.SZERO, form.getFoneArea(), form.getFoneNumero(), this.historicoConsultarForm.getIdPessoaCliente(), idPessoa, form.getTipoRelacionamento(), this.CD_SENHA, this.ID_CANAL, form.getMotivo(), this.historicoConsultarForm.getIdUsuario());

		} catch (WarningException we) {

			form.setMessageError(we.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		}

		String foneNumero = form.getFoneNumero();

		if (foneNumero != null && foneNumero.length() > 2) {
			foneNumero = foneNumero.substring(2);
		}

		ParametrosVO paramVO = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));

		if (senhaOperarVo.getOperacaoRealizada()) {
			form.setMessage(senhaOperarVo.getMensagem());

			registrarContatoFacade.registrarContatoSenhaIncrgContato(user, foneNumero, form.getFoneArea(), form.getTipoRelacionamento(), this.ID_CANAL, "ContatoSolicitarSenha");

		} else {
			form.setMessageError(senhaOperarVo.getMensagem());
		}

		// Necessário para manter o conteúdo da consulta e outras informaçoes que não possuem
		// campos no form ou campos hidden
		form.setMovimentoVo(this.getHistoricoConsultarForm().getMovimentoVo());
		form.setIdPessoaCliente(this.getHistoricoConsultarForm().getIdPessoaCliente());
		form.setIdPessoaUsuario(this.getHistoricoConsultarForm().getIdPessoaUsuario());
		form.setIdUsuario(this.historicoConsultarForm.getIdUsuario());

		// atualiza o formbean
		this.historicoConsultarForm = form;

		this.historicoConsultarForm.setMotivo(ConstantesCRM.SVAZIO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="historicoSenha.jsp"
	 */
	public ActionForward reinicializarFraseAction(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		HistoricoConsultarForm form = (HistoricoConsultarForm) formParam;

		log.debug("\t" + this.getClass().getName() + ":reinicializarAction()");

		// Conforme a seleçao do tipo de pessoa (relacionamento) utilizar o idPessoaCliente
		// ou idPessoaUsuario
		String idPessoa;
		if (form.getTipoRelacionamento().equals(this.TP_CLIENTE)) {
			idPessoa = this.historicoConsultarForm.getIdPessoaCliente();
		} else {
			idPessoa = this.historicoConsultarForm.getIdPessoaUsuario();
		}

		// garante que só 255 caracteres serão gravados
		form.setMotivo(form.getMotivo().length() > 255 ? form.getMotivo().substring(0, 255) : form.getMotivo());

		SenhaOperarVO senhaOperarVo = operarSenhaFacade.ReiniciaSenha(ConstantesCRM.SONE, form.getFoneArea(), form.getFoneNumero(), this.historicoConsultarForm.getIdPessoaCliente(), idPessoa, form.getTipoRelacionamento(), this.CD_SENHA, this.ID_CANAL, form.getMotivo(), this.historicoConsultarForm.getIdUsuario());

		if (senhaOperarVo.getOperacaoRealizada()) {
			form.setMessage(senhaOperarVo.getMensagem());
		} else {
			form.setMessageError(senhaOperarVo.getMensagem());
		}

		// Necessário para manter o conteúdo da consulta e outras informaçoes que não possuem
		// campos no form ou campos hidden
		form.setMovimentoVo(this.getHistoricoConsultarForm().getMovimentoVo());
		form.setIdPessoaCliente(this.getHistoricoConsultarForm().getIdPessoaCliente());
		form.setIdPessoaUsuario(this.getHistoricoConsultarForm().getIdPessoaUsuario());
		form.setIdUsuario(this.historicoConsultarForm.getIdUsuario());

		// atualiza o formbean
		this.historicoConsultarForm = form;

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Getter para o FormBean
	 */
	public HistoricoConsultarForm getHistoricoConsultarForm() {
		return this.historicoConsultarForm;
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class HistoricoConsultarForm extends ActionForm {

		private static final long serialVersionUID = -4113021440781491533L;
		private String idUsuario = ConstantesCRM.SVAZIO;
		private String foneArea = ConstantesCRM.SVAZIO;
		private String foneNumero = ConstantesCRM.SVAZIO;
		private String idPessoaCliente = ConstantesCRM.SVAZIO;
		private String idPessoaUsuario = ConstantesCRM.SVAZIO;
		private String TipoRelacionamento = ConstantesCRM.STWO;
		private String nmCliente = ConstantesCRM.SVAZIO;
		private String nmUsuario = ConstantesCRM.SVAZIO;
		private String inBloqueado = ConstantesCRM.SVAZIO;
		private String motivo = ConstantesCRM.SVAZIO;
		private String message = ConstantesCRM.SVAZIO;
		private String messageError = ConstantesCRM.SVAZIO;

		private SenhaMovimentoVO[] movimentoVo;

		public String getFoneFormatado() {
			/*String area = "000" + this.foneArea;
			area = area.substring(area.length() - 3, area.length());

			String fone = "        " + this.foneNumero;
			fone = fone.substring(fone.length() - 8, fone.length());*/
			String nrTelefone = this.foneArea.concat(this.foneNumero);

			return ConstantesCRM.formatPhoneNumber(nrTelefone);
		}

		public void setIdUsuario(String idUsuario) {
			this.idUsuario = idUsuario;
		}

		public String getIdUsuario() {
			return this.idUsuario;
		}

		public void setNmCliente(String nmCliente) {
			this.nmCliente = nmCliente;
		}

		public String getNmCliente() {
			return this.nmCliente;
		}

		public void setInBloqueado(String inBloqueado) {
			this.inBloqueado = inBloqueado;
		}

		public String getInBloqueado() {
			return this.inBloqueado;
		}

		public void setNmUsuario(String nmUsuario) {
			this.nmUsuario = nmUsuario;
		}

		public String getNmUsuario() {
			return this.nmUsuario;
		}

		public void setFoneArea(String foneArea) {
			this.foneArea = foneArea;
		}

		public String getFoneArea() {
			return this.foneArea;
		}

		public void setFoneNumero(String foneNumero) {
			this.foneNumero = foneNumero;
		}

		public String getFoneNumero() {
			return this.foneNumero;
		}

		public void setIdPessoaCliente(String idPessoaCliente) {
			this.idPessoaCliente = idPessoaCliente;
		}

		public String getIdPessoaCliente() {
			return this.idPessoaCliente;
		}

		public void setIdPessoaUsuario(String idPessoaUsuario) {
			this.idPessoaUsuario = idPessoaUsuario;
		}

		public String getIdPessoaUsuario() {
			return this.idPessoaUsuario;
		}

		public void setTipoRelacionamento(String TipoRelacionamento) {
			this.TipoRelacionamento = TipoRelacionamento;
		}

		public String getTipoRelacionamento() {
			return this.TipoRelacionamento;
		}

		public void setMotivo(String motivo) {
			this.motivo = motivo;
		}

		public String getMotivo() {
			return this.motivo;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getMessage() {
			return this.message;
		}

		public void setMessageError(String messageError) {
			this.messageError = messageError;
		}

		public String getMessageError() {
			return this.messageError;
		}

		public void setMovimentoVo(SenhaMovimentoVO[] movimentoVo) {
			this.movimentoVo = movimentoVo;
		}

		public SenhaMovimentoVO[] getMovimentoVo() {
			return this.movimentoVo;
		}

		private ArrayList historicoSenhaArray;

		public void setHistoricoSenhaArray(ArrayList historicoSenhaArrayParam) {
			historicoSenhaArray = historicoSenhaArrayParam;
		}

		public ArrayList getHistoricoSenhaArray() {
			return historicoSenhaArray;
		}

	}

}
