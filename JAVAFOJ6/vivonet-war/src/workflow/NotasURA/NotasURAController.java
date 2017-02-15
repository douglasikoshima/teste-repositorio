package workflow.NotasURA;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO;
import br.com.vivo.fo.workflow.vo.WFAtdNotaHistVODocument.WFAtdNotaHistVO;
import br.com.vivo.fo.workflow.vo.WFAtdNotaVODocument.WFAtdNotaVO;
import br.com.vivo.fo.workflow.vo.WFAtdNotasVODocument.WFAtdNotasVO;
import br.com.vivo.fo.workflow.vo.WFAtdTipoNotaVODocument.WFAtdTipoNotaVO;
import br.com.vivo.fo.workflow.vo.WFAtdTipoNotasVODocument.WFAtdTipoNotasVO;
import br.com.vivo.fo.workflow.vo.WFMotivoVODocument.WFMotivoVO;

import com.indracompany.actions.AbstractAction;

public class NotasURAController extends AbstractAction {

	private static final long serialVersionUID = 1376733588134420987L;

	private static Logger log = new Logger("workflow");

	protected global.Global globalApp = new global.Global();

	@EJB
	private br.com.vivo.fo.ctrls.workflow.notasura.NotasURAFacade notasURAFacade;

	private NotasForm notasForm = new NotasForm();

	public NotasForm getNotasForm(){
		return this.notasForm;
	}

	public void setNotasForm(NotasForm notasForm){
		this.notasForm = notasForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {

		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar (mapping, form, request, response);

		} else if ("editarNotaURA".equals(mapping.getParameter())) {
			return editarNotaURA (mapping, form, request, response);

		} else if ("gravarNotaURA".equals(mapping.getParameter())) {
			return gravarNotaURA (mapping, form, request, response);

		} else if ("editarComentarioNotaURA".equals(mapping.getParameter())) {
			return editarComentarioNotaURA (mapping, form, request, response);

		} else if ("lerNotasURADetalhe".equals(mapping.getParameter())) {
			return lerNotasURADetalhe (mapping, form, request, response);

		} else if ("lerNotasURAHistorico".equals(mapping.getParameter())) {
			return lerNotasURAHistorico (mapping, form, request, response);

		} else if ("acaoNotasURA".equals(mapping.getParameter())) {
			return acaoNotasURA (mapping, form, request, response);

		} else if ("pesquisarLinhaProcesso".equals(mapping.getParameter())) {
			return pesquisarLinhaProcesso (mapping, form, request, response);

		} else if ("incluirOperacaoNota".equals(mapping.getParameter())) {
			return incluirOperacaoNota (mapping, form, request, response);

		} else if ("iframeOperacaoNota".equals(mapping.getParameter())) {
			return iframeOperacaoNota (mapping, form, request, response);

		} else if ("voltarInbox".equals(mapping.getParameter())) {
			return voltarInbox (mapping, form, request, response);

		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		NotasForm form = (NotasForm) formParam;

		log.debug("NotasURAControllerController:begin.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		NotasForm notasForm = new NotasForm();
		log.debug("NotasURAControllerController:begin.do - Fim do Metodo]");
		request.setAttribute("notasForm", notasForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisa.jsp"
	 */
	protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		NotasForm form = (NotasForm) formParam;

		log.debug("NotasURAControllerController:pesquisar.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		NotasForm notasForm = new NotasForm();
		notasForm.setNotasVO(notasURAFacade.pesquisarNotasURA(user,form.getNotaVO()) );
		log.debug("NotasURAControllerController:pesquisar.do - Fim do Metodo]");
		request.setAttribute("notasForm", notasForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirNotaURA.jsp"
	 * @jpf:forward name="alterar" path="editarNotaURA.jsp"
	 */
	protected ActionForward editarNotaURA(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		NotasForm form = (NotasForm) formParam;

		log.debug("NotasURAControllerController:editarNotaURA.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		WFAtdTipoNotasVO tipoNotasVO = this.notasURAFacade.getTipoNotas(user);
		NotasForm notasForm1 = new NotasForm();
		notasForm1.setTipoNotasVO(tipoNotasVO.getWFAtdTipoNotaVOArray());
		if (form.getIdAtendimentoNota() == null ||
				form.getIdAtendimentoNota().equals("") || form.getIdAtendimentoNota().equals("0")) {
			// adicionando...
			notasForm1.setNotaVO(WFAtdNotaVO.Factory.newInstance());
			log.debug("NotasURAControllerController:editarNotaURA.do - Fim do Metodo]");
			request.setAttribute("notasForm1", notasForm1);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		} else {
			// alterando...
			WFAtdNotaVO notaVO = WFAtdNotaVO.Factory.newInstance();
			notaVO.setIdAtendimentoNota(form.getIdAtendimentoNota());
			form.setNotaVO(notaVO);
			notasForm1.setNotaVO(notasURAFacade.lerNotaURA(user,form.getNotaVO()));
			log.debug("NotasURAControllerController:editarNotaURA.do - Fim do Metodo]");
			request.setAttribute("notasForm1", notasForm1);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("alterar");
		}

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="retornoAcao.jsp"
	 */
	protected ActionForward gravarNotaURA(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		NotasForm form = (NotasForm) formParam;

		log.debug("NotasURAControllerController:gravarNotaURA.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		form.getNotaVO().setIdUsuario(user);
		WFAcaoRetornoVO acaoRetornoVO = this.notasURAFacade.gravarNotaURA(user,form.getNotaVO());
		this.getNotasForm().setAcaoRetornoVO(acaoRetornoVO);
		log.debug("NotasURAControllerController:gravarNotaURA.do - Fim do Metodo]");
		request.setAttribute("notasForm", notasForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="retornoAcaoComentario.jsp"
	 */
	protected ActionForward editarComentarioNotaURA(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		NotasForm form = (NotasForm) formParam;

		log.debug("NotasURAControllerController:editarComentarioNotaURA.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		StringBuffer comentario = new StringBuffer(form.getNotaVO().getComentario());
		form.getNotaVO().setIdUsuario(user);
		form.getNotaVO().setComentario(comentario.toString());
		WFAcaoRetornoVO acaoRetornoVO = this.notasURAFacade.gravarNotaURA(user,form.getNotaVO());
		this.getNotasForm().setAcaoRetornoVO(acaoRetornoVO);
		log.debug("NotasURAControllerController:editarComentarioNotaURA.do - Fim do Metodo]");
		request.setAttribute("notasForm", notasForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="detalhesNotaURA.jsp"
	 * @jpf:forward name="historico" path="historicoNotasURA.jsp"
	 */
	protected ActionForward lerNotasURADetalhe(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception{
		NotasForm form = (NotasForm) formParam;

		log.debug("NotasURAControllerController:lerNotasURADetalhe.do - Inicio do Metodo]");
		String motivoSel = request.getParameter("motivoSel");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		form.getNotaVO().setIdAtendimentoNota(form.getIdAtendimentoNota());
		WFAtdNotaVO nota = this.notasURAFacade.lerNotasURADetalhes(user,form.getNotaVO());
		this.notasForm = new NotasForm();
		this.notasForm.setIdAtendimentoNota(nota.getIdAtendimentoNota());
		this.notasForm.setMotivoSel(motivoSel);
		this.notasForm.setNotaVO(nota);
		this.notasForm.setAcaoVO(nota.getWFAcaoVOArray());
		this.notasForm.setNotaHistVO(nota.getWFAtdNotaHistVOArray());
		this.notasForm.setMotivoVO( this.notasURAFacade.getMotivos(user,"<idTabelaMotivo>50</idTabelaMotivo>"));
		if("1".equals(request.getParameter("tela"))){
			log.debug("NotasURAControllerController:lerNotasURADetalhe.do - Fim do Metodo]");
			request.setAttribute("notasForm", notasForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("historico");
		}
		else{
			log.debug("NotasURAControllerController:lerNotasURADetalhe.do - Fim do Metodo]");
			request.setAttribute("notasForm", notasForm);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="historicoNotasURA.jsp"
	 */
	protected ActionForward lerNotasURAHistorico(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		NotasForm form = (NotasForm) formParam;

		log.debug("NotasURAControllerController:lerNotasURAHistorico.do - Metodo]");
		request.setAttribute("notasForm", notasForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="retornoAcao.jsp"
	 */
	protected ActionForward acaoNotasURA(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		log.debug("NotasURAControllerController:acaoNotasURA.do - Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="escolherProcesso.jsp"
	 */
	protected ActionForward pesquisarLinhaProcesso(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response)
	{
		NotasForm form = (NotasForm) formParam;

		log.debug("NotasURAControllerController:pesquisarLinhaProcesso.do - Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="erro" path="incluirOperacaoNota.jsp"
	 * @jpf:forward name="success" path="retornoOperacaoNota.jsp"
	 */
	protected ActionForward incluirOperacaoNota(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		NotasForm form = (NotasForm) formParam;

		log.debug("NotasURAControllerController:incluirOperacaoNota.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		WFAtdNotaVO nota = this.getNotasForm().getNotaVO();
		WFAtdNotaHistVO[] notaHistVO = new WFAtdNotaHistVO[1];
		notaHistVO[0] = WFAtdNotaHistVO.Factory.newInstance();
		notaHistVO[0].setDsObservacao(form.getDsObservacao());
		log.debug("NotasURAControllerController:incluirOperacaoNota.do - [dsObservacao = " + form.getDsObservacao() + "]");
		notaHistVO[0].setIdMotivo(form.getMotivoSel());
		log.debug("NotasURAControllerController:incluirOperacaoNota.do - [motivosSel = " + form.getMotivoSel() + "]");
		nota.setIdOperacao(form.getAcaoSel());
		log.debug("NotasURAControllerController:incluirOperacaoNota.do - [acaoSel = " + form.getAcaoSel() + "]");
		nota.setWFAtdNotaHistVOArray(notaHistVO);
		WFAcaoRetornoVO acaoRetornoVO = this.notasURAFacade.gravarHistoricoNota(user,nota);
		this.getNotasForm().setAcaoRetornoVO(acaoRetornoVO);
		log.debug("NotasURAControllerController:incluirOperacaoNota.do - Fim do Metodo]");
		request.setAttribute("notasForm", notasForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="incluirOperacaoNota.jsp"
	 */
	protected ActionForward iframeOperacaoNota(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		NotasForm form = (NotasForm) formParam;

		log.debug("NotasURAControllerController:iframeOperacaoNota.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		this.notasForm.setMotivoVO( this.notasURAFacade.getMotivos(user,"<idTabelaMotivo>50</idTabelaMotivo>"));
		this.notasForm.setAcaoLabel(form.getAcaoLabel());
		log.debug("NotasURAControllerController:incluirOperacaoNota.do - [acaoLabel = " + form.getAcaoLabel() + "]");
		this.notasForm.setAcaoSel(form.getAcaoSel());
		log.debug("NotasURAControllerController:incluirOperacaoNota.do - [acaoSel = " + form.getAcaoSel() + "]");
		log.debug("NotasURAControllerController:iframeOperacaoNota.do - Fim do Metodo]");
		request.setAttribute("notasForm", notasForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" return-action="AtendimentoDetalheDone"
	 */
	protected ActionForward voltarInbox(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
	{
		log.debug("NotasURAControllerController:voltarInbox.do - Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * FormData get and set methods may be overwritten by the Form Bean editor.
	 */
	public static class NotasForm extends ActionForm {
		private String comentarioNovo;

		private WFAtdTipoNotaVO[] tipoNotasVO;

		private String acaoLabel;

		private String dsObservacao;

		private String motivoSel;

		private String motivoAcao;

		private WFMotivoVO[] motivoVO;

		private String idAtendimentoNota;

		private String acaoSel;
		private WFAtdNotaVO notaVO;
		private WFAtdNotasVO notasVO;
		private WFAtdNotaHistVO[] notaHistVO;
		private WFAcaoVO[] acaoVO;
		private WFAcaoRetornoVO acaoRetornoVO;

		private int idNota;

		public void setNotaVO(WFAtdNotaVO notaVO) {
			this.notaVO = notaVO;
		}

		public WFAtdNotaHistVO[] getNotaHistVO(){
			return this.notaHistVO;
		}

		public WFAcaoVO[] getAcaoVO(){
			return this.acaoVO;
		}

		public void setAcaoVO(WFAcaoVO[] acaoVO){
			this.acaoVO = acaoVO;
		}



		public WFAcaoRetornoVO getAcaoRetornoVO(){
			return this.acaoRetornoVO;
		}

		public void setAcaoRetornoVO(WFAcaoRetornoVO acaoRetornoVO){
			this.acaoRetornoVO = acaoRetornoVO;
		}

		public void setNotaHistVO(WFAtdNotaHistVO[] notaHistVO){
			this.notaHistVO = notaHistVO;
		}

		public WFAtdNotaVO getNotaVO() {
			if (this.notaVO==null) {
				this.notaVO=WFAtdNotaVO.Factory.newInstance();
			}
			return this.notaVO;
		}

		public void setNotasVO(WFAtdNotasVO notasVO) {
			this.notasVO = notasVO;
		}

		public WFAtdNotasVO getNotasVO() {
			if (this.notasVO==null) {
				this.notasVO=WFAtdNotasVO.Factory.newInstance();
			}
			return this.notasVO;
		}

		public void setIdNota(int idNota) {
			this.idNota = idNota;
		}

		public int getIdNota() {
			return this.idNota;
		}


		public void setAcaoSel(String acaoSel)
		{
			this.acaoSel = acaoSel;
		}

		public String getAcaoSel()
		{
			return this.acaoSel;
		}

		public void setIdAtendimentoNota(String idAtendimentoNota)
		{
			this.idAtendimentoNota = idAtendimentoNota;
		}

		public String getIdAtendimentoNota()
		{
			return this.idAtendimentoNota;
		}

		public void setMotivoVO(WFMotivoVO[] motivoVO)
		{
			this.motivoVO = motivoVO;
		}

		public WFMotivoVO[] getMotivoVO()
		{
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize motivoVO if it is null or length == 0.
			//if(this.motivoVO == null || this.motivoVO.length == 0)
			//{
			//    this.motivoVO = new WFMotivoVO[1];
			//    this.motivoVO[0] = new WFMotivoVO(?);
			//}

			return this.motivoVO;
		}

		public void setMotivoSel(String motivoSel)
		{
			this.motivoSel = motivoSel;
		}

		public String getMotivoSel()
		{
			return this.motivoSel;
		}

		public void setDsObservacao(String dsObservacao)
		{
			this.dsObservacao = dsObservacao;
		}

		public String getDsObservacao()
		{
			return this.dsObservacao;
		}

		public void setAcaoLabel(String acaoLabel)
		{
			this.acaoLabel = acaoLabel;
		}

		public String getAcaoLabel()
		{
			return this.acaoLabel;
		}

		public void setTipoNotasVO(WFAtdTipoNotaVO[] tipoNotasVO)
		{
			this.tipoNotasVO = tipoNotasVO;
		}

		public WFAtdTipoNotaVO[] getTipoNotasVO()
		{
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize tipoNotasVO if it is null.
			//if(this.tipoNotasVO == null)
			//{
			//    this.tipoNotasVO = new WFAtdTipoNotasVO(?);
			//}

			return this.tipoNotasVO;
		}

		public void setComentarioNovo(String comentarioNovo)
		{
			this.comentarioNovo = comentarioNovo;
		}

		public String getComentarioNovo()
		{
			return this.comentarioNovo;
		}
	}
}
