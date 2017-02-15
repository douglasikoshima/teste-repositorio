package admsistemas.admArvoreContato.workflow.abaQuestionario;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmListaVincularQuestionarioVODocument.AdmListaVincularQuestionarioVO;
import br.com.vivo.fo.admsistemas.vo.AdmQuestionarioVODocument.AdmQuestionarioVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoPessoaVODocument.AdmTipoPessoaVO;
import br.com.vivo.fo.admsistemas.vo.AdmVincularQuestionarioVODocument.AdmVincularQuestionarioVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UFOperadoraUsuarioVODocument.UFOperadoraUsuarioVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class AbaQuestionarioController extends AbstractAction {

	private static final long serialVersionUID = -3158789536275830172L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.questionario.Questionario controlQuestionario;

	protected global.Global globalApp = new global.Global();

	private static  transient Logger log = new Logger("admsistemas");

	private FormQuestionario formQuestionario;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvarQuestionario".equals(mapping.getParameter())) {
			return salvarQuestionario(mapping, form, request, response);
		} else if ("removerQuestionario".equals(mapping.getParameter())) {
			return removerQuestionario(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularQuestionario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response)
	{
		log.debug("AbaQuestionarioController:begin"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormQuestionario form = (FormQuestionario) formParam;
		try
		{
			if(formQuestionario == null) {
				formQuestionario = new FormQuestionario();
			}

			if (request.getParameter("idContato")!=null) {
				formQuestionario.setIdContato(request.getParameter("idContato"));
			} else {
				formQuestionario.setIdContato(form.getIdContato());
			}

			AdmVincularQuestionarioVO vincularQuestionarioVO = AdmVincularQuestionarioVO.Factory.newInstance();
			vincularQuestionarioVO.setIdContato(formQuestionario.getIdContato());

			log.debug("AbaQuestionarioController:begin"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " ) | idContato = " + formQuestionario.getIdContato());

			if(form.getIdQuestionarioAtual() != null && !form.getIdQuestionarioAtual().trim().equals(ConstantesCRM.SVAZIO)) {
				vincularQuestionarioVO.setIdQuestionarioAtual(form.getIdQuestionarioAtual());
			}

			if(form.getIdTipoPessoaAtual() != null && !form.getIdTipoPessoaAtual().trim().equals(ConstantesCRM.SVAZIO)) {
				vincularQuestionarioVO.setIdTipoPessoaAtual(form.getIdTipoPessoaAtual());
			}
			formQuestionario.setIdTipoPessoaAtual(form.getIdTipoPessoaAtual());

			AdmVincularQuestionarioVO admVincularQuestionarioVO =AdmVincularQuestionarioVO.Factory.newInstance();
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			admVincularQuestionarioVO = controlQuestionario.listaQuestionario(vincularQuestionarioVO,user);
			formQuestionario.setIdQuestionarioAtual(form.getIdQuestionarioAtual());

			formQuestionario.setArrayAdmTipoPessoa(new String[0]);
			formQuestionario.setArrayAdmUFOperadoraUsuario(new String[0]);

			montaForm(admVincularQuestionarioVO);


		} catch(TuxedoWarningException twe)
		{
			log.warn("AbaQuestionarioController:begin"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError",twe.getXmlHeader().getStatusText());

		}catch(Exception e)
		{
			log.error("AbaQuestionarioController:begin"+"( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}


		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	// carrega objetos para envio.s
	private void montaForm(AdmVincularQuestionarioVO admVincularQuestionarioVO)
	{

		//carrega combo questionário.
		if(admVincularQuestionarioVO.getQuestionarios() != null
				&& admVincularQuestionarioVO.getQuestionarios().getAdmQuestionarioVOArray().length > 0) {
			formQuestionario.setAdmQuestionarioVO(admVincularQuestionarioVO.getQuestionarios().getAdmQuestionarioVOArray());
		} else {
			formQuestionario.setAdmQuestionarioVO(new AdmQuestionarioVO[0]);
		}

		if(admVincularQuestionarioVO.getTipoPessoa() != null
				&& admVincularQuestionarioVO.getTipoPessoa().getAdmTipoPessoaVOArray().length > 0) {
			formQuestionario.setAdmTipoPessoaVO(admVincularQuestionarioVO.getTipoPessoa().getAdmTipoPessoaVOArray());
		} else {
			formQuestionario.setAdmTipoPessoaVO(new AdmTipoPessoaVO[0]);
		}

		// Seta Lista de dados existentes.
		if(admVincularQuestionarioVO.getExistentes().getUFOperadoraUsuarioVOArray().length > 0) {
			formQuestionario.setAdmUFOperadoraExistente(admVincularQuestionarioVO.getExistentes().getUFOperadoraUsuarioVOArray());
		} else {
			formQuestionario.setAdmUFOperadoraExistente(new UFOperadoraUsuarioVO[0]);
		}

		//if(admVincularQuestionarioVO.getExistentes().getAdmTipoPessoaVOArray().length > 0)
		//    formQuestionario.setAdmTipoPessoaVOExistente(admVincularQuestionarioVO.getExistentes().getAdmTipoPessoaVOArray());
		//else
		//   formQuestionario.setAdmTipoPessoaVOExistente(new AdmTipoPessoaVO[0]);

		//Seta Lista de dados relaconados.
		if(admVincularQuestionarioVO.getRelacionados().getUFOperadoraUsuarioVOArray().length > 0) {
			formQuestionario.setAdmUFOperadoraRelacionado(admVincularQuestionarioVO.getRelacionados().getUFOperadoraUsuarioVOArray());
		} else {
			formQuestionario.setAdmUFOperadoraRelacionado(new UFOperadoraUsuarioVO[0]);
		}

		//if(admVincularQuestionarioVO.getRelacionados().getAdmTipoPessoaVOArray().length > 0)
		//    formQuestionario.setAdmTipoPessoaVORelacionado(admVincularQuestionarioVO.getRelacionados().getAdmTipoPessoaVOArray());
		//else
		//    formQuestionario.setAdmTipoPessoaVORelacionado(new AdmTipoPessoaVO[0]);

		if(admVincularQuestionarioVO.getQuestionariosConfigurarados() != null && admVincularQuestionarioVO.getQuestionariosConfigurarados().getAdmQuestionarioVOArray() != null)
		{
			formQuestionario.setQuestionariosAssociados(admVincularQuestionarioVO.getQuestionariosConfigurarados().getAdmQuestionarioVOArray());
		} else {
			formQuestionario.setQuestionariosAssociados(new AdmQuestionarioVO[0]);
		}

		//carrega IdContato.
		formQuestionario.setIdContato(admVincularQuestionarioVO.getIdContato());
	}
	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarQuestionario(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response)
	{
		log.debug("AbaQuestionarioController:salvarQuestionario"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormQuestionario form = (FormQuestionario) formParam;
		try
		{
			AdmVincularQuestionarioVO admVincularQuestionarioVO = AdmVincularQuestionarioVO.Factory.newInstance();
			admVincularQuestionarioVO.addNewRelacionados();

			UFOperadoraUsuarioVO[] ufOperadoraUsuarioVO = new UFOperadoraUsuarioVO[form.getArrayAdmUFOperadoraUsuario().length];
			String[] operadora = new String[form.getArrayAdmUFOperadoraUsuario().length];
			operadora = form.getArrayAdmUFOperadoraUsuario();
			for(int i =0;i<operadora.length;i++){
				ufOperadoraUsuarioVO[i] = UFOperadoraUsuarioVO.Factory.newInstance();
				ufOperadoraUsuarioVO[i].setIdUFOperadora(operadora[i]);
			}

			//AdmTipoPessoaVO[] admTipoPessoaVO = new AdmTipoPessoaVO[form.getArrayAdmTipoPessoa().length];
			//String[] pessoa = new String[form.getArrayAdmTipoPessoa().length];
			//pessoa = form.getArrayAdmTipoPessoa();
			//for(int i =0;i<pessoa.length;i++){
			//    admTipoPessoaVO[i] = AdmTipoPessoaVO.Factory.newInstance();
			//    admTipoPessoaVO[i].setIdTipoPessoa(pessoa[i]);
			// }
			//admVincularQuestionarioVO.addNewRelacionados().setAdmTipoPessoaVOArray(admTipoPessoaVO);

			admVincularQuestionarioVO.getRelacionados().setUFOperadoraUsuarioVOArray(ufOperadoraUsuarioVO);

			admVincularQuestionarioVO.setIdContato(form.getIdContato());
			admVincularQuestionarioVO.setIdQuestionarioAtual(form.getIdQuestionarioAtual());
			admVincularQuestionarioVO.setIdTipoPessoaAtual(form.getIdTipoPessoaAtual());

			String user = ConstantesCRM.getCurrentUser(request.getSession());
			controlQuestionario.salvaOperadoras(admVincularQuestionarioVO,user);

			formQuestionario.setIdContato(form.getIdContato());

			if( admVincularQuestionarioVO.getRelacionados().getUFOperadoraUsuarioVOArray(0).getIdUFOperadora() == null    ||
					admVincularQuestionarioVO.getRelacionados().getUFOperadoraUsuarioVOArray(0).getIdUFOperadora().equals(ConstantesCRM.SVAZIO) ){
				request.setAttribute("msgError", "Operação concluída com sucesso. As listas sem itens associados terão seu preenchimento obrigatório.");
			} else {
				request.setAttribute("msgError", ConstantesCRM.SSucesso);
			}

		} catch(TuxedoWarningException twe)
		{
			log.warn("AbaQuestionarioController:salvarQuestionario"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			request.setAttribute("msgError",twe.getXmlHeader().getStatusText());

		}catch(Exception e)
		{
			log.error("AbaQuestionarioController:salvarQuestionario"+"( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS) ;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularQuestionario.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward removerQuestionario(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response)
	{
		log.debug("AbaQuestionarioController:removerQuestionario"+"( "+ ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormQuestionario form = (FormQuestionario) formParam;
		try
		{
			String id = request.getParameter("idContato");
			String operadora = request.getParameter("idUFOperadora");
			String linha = request.getParameter("idTipoLinha");
			String pergunta = request.getParameter("idTipoApresentacaoPergunta");

			AdmListaVincularQuestionarioVO admListaVincularQuestionarioVO = AdmListaVincularQuestionarioVO.Factory.newInstance();
			admListaVincularQuestionarioVO.setIdContato(id);
			admListaVincularQuestionarioVO.setIdTipoApresentacaoPergunta(pergunta);
			admListaVincularQuestionarioVO.setIdTipoLinha(linha);
			admListaVincularQuestionarioVO.setIdUFOperadora(operadora);

			//AdmQuestionarioVO questionario = AdmQuestionarioVO.Factory.newInstance();

			AdmVincularQuestionarioVO admVincularQuestionarioVO = AdmVincularQuestionarioVO.Factory.newInstance();
			admVincularQuestionarioVO = null;//controlQuestionario.removerQuestionario(admListaVincularQuestionarioVO,(String)request.getSession().getAttribute(ConstantesCRM.USUARIO_IDENTIFICADOR_SESSION));

			montaForm(admVincularQuestionarioVO);
			request.setAttribute("msgError",ConstantesCRM.SSucesso);

		} catch(Exception e)
		{
			log.error("AbaQuestionarioController:removerQuestionario"+"( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class FormQuestionario extends ActionForm
	{
		private String idTipoPessoaAtual;

		private AdmQuestionarioVO[] questionariosAssociados;

		//private String msgError = ConstantesCRM.SVAZIO;

		private String idQuestionarioAtual;

		private UFOperadoraUsuarioVO[] admUFOperadoraExistente;

		private UFOperadoraUsuarioVO[] admUFOperadoraRelacionado;

		private String[] arrayAdmTipoPessoa;

		private AdmTipoPessoaVO[] admTipoPessoaVO;

		private String[] arrayAdmUFOperadoraUsuario;

		//private UFOperadoraUsuarioVO[] admUFOperadoraUsuarioVO;

		//private String[] arrayAdmTipoLinha;

		//private AdmTipoLinhaVO[] admTipoLinhaVO;

		private String[] arrayAdmQuestionario;

		private String[] arrayAdmListaVincularQuestionario;

		private AdmListaVincularQuestionarioVO[] admListaVincularQuestionarioVO;

		private AdmQuestionarioVO[] admQuestionarioVO;

		private String idContato;

		public void setIdContato(String idContato)
		{
			this.idContato = idContato;
		}

		public String getIdContato()
		{
			return this.idContato;
		}

		public void setAdmQuestionarioVO(AdmQuestionarioVO[] admQuestionarioVO)
		{
			this.admQuestionarioVO = admQuestionarioVO;
		}

		public AdmQuestionarioVO[] getAdmQuestionarioVO()
		{
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize admQuestionarioVO if it is null or length == 0.
			//if(this.admQuestionarioVO == null || this.admQuestionarioVO.length == 0)
			//{
			//    this.admQuestionarioVO = new AdmQuestionarioVO[1];
			//    this.admQuestionarioVO[0] = new AdmQuestionarioVO(?);
			//}

			return this.admQuestionarioVO;
		}

		public void setAdmListaVincularQuestionarioVO(AdmListaVincularQuestionarioVO[] admListaVincularQuestionarioVO)
		{
			this.admListaVincularQuestionarioVO = admListaVincularQuestionarioVO;
		}

		public AdmListaVincularQuestionarioVO[] getAdmListaVincularQuestionarioVO()
		{
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize admListaVincularQuestionarioVO if it is null or length == 0.
			//if(this.admListaVincularQuestionarioVO == null || this.admListaVincularQuestionarioVO.length == 0)
			//{
			//    this.admListaVincularQuestionarioVO = new AdmListaVincularQuestionarioVO[1];
			//    this.admListaVincularQuestionarioVO[0] = new AdmListaVincularQuestionarioVO(?);
			//}

			return this.admListaVincularQuestionarioVO;
		}

		public void setArrayAdmListaVincularQuestionario(String[] arrayAdmListaVincularQuestionario)
		{
			this.arrayAdmListaVincularQuestionario = arrayAdmListaVincularQuestionario;
		}

		public String[] getArrayAdmListaVincularQuestionario()
		{
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if(this.arrayAdmListaVincularQuestionario == null || this.arrayAdmListaVincularQuestionario.length == 0)
			{
				this.arrayAdmListaVincularQuestionario = new String[1];
			}

			return this.arrayAdmListaVincularQuestionario;
		}

		public void setArrayAdmQuestionario(String[] arrayAdmQuestionario)
		{
			this.arrayAdmQuestionario = arrayAdmQuestionario;
		}

		public String[] getArrayAdmQuestionario()
		{
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if(this.arrayAdmQuestionario == null || this.arrayAdmQuestionario.length == 0)
			{
				this.arrayAdmQuestionario = new String[1];
			}

			return this.arrayAdmQuestionario;
		}

		public void setArrayAdmUFOperadoraUsuario(String[] arrayAdmUFOperadoraUsuario)
		{
			this.arrayAdmUFOperadoraUsuario = arrayAdmUFOperadoraUsuario;
		}

		public String[] getArrayAdmUFOperadoraUsuario()
		{
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if(this.arrayAdmUFOperadoraUsuario == null || this.arrayAdmUFOperadoraUsuario.length == 0)
			{
				this.arrayAdmUFOperadoraUsuario = new String[1];
			}

			return this.arrayAdmUFOperadoraUsuario;
		}

		public void setArrayAdmTipoPessoa(String[] arrayAdmTipoPessoa)
		{
			this.arrayAdmTipoPessoa = arrayAdmTipoPessoa;
		}

		public String[] getArrayAdmTipoPessoa()
		{
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if(this.arrayAdmTipoPessoa == null || this.arrayAdmTipoPessoa.length == 0)
			{
				this.arrayAdmTipoPessoa = new String[1];
			}

			return this.arrayAdmTipoPessoa;
		}

		public void setAdmUFOperadoraRelacionado(UFOperadoraUsuarioVO[] admUFOperadoraRelacionado)
		{
			this.admUFOperadoraRelacionado = admUFOperadoraRelacionado;
		}

		public UFOperadoraUsuarioVO[] getAdmUFOperadoraRelacionado()
		{
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize admUFOperadoraRelacionado if it is null or length == 0.
			//if(this.admUFOperadoraRelacionado == null || this.admUFOperadoraRelacionado.length == 0)
			//{
			//    this.admUFOperadoraRelacionado = new UFOperadoraUsuarioVO[1];
			//    this.admUFOperadoraRelacionado[0] = new UFOperadoraUsuarioVO(?);
			//}

			return this.admUFOperadoraRelacionado;
		}

		public void setAdmUFOperadoraExistente(UFOperadoraUsuarioVO[] admUFOperadoraExistente)
		{
			this.admUFOperadoraExistente = admUFOperadoraExistente;
		}

		public UFOperadoraUsuarioVO[] getAdmUFOperadoraExistente()
		{
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize admUFOperadoraExistente if it is null or length == 0.
			//if(this.admUFOperadoraExistente == null || this.admUFOperadoraExistente.length == 0)
			//{
			//    this.admUFOperadoraExistente = new UFOperadoraUsuarioVO[1];
			//    this.admUFOperadoraExistente[0] = new UFOperadoraUsuarioVO(?);
			//}

			return this.admUFOperadoraExistente;
		}

		public void setIdQuestionarioAtual(String idQuestionarioAtual)
		{
			this.idQuestionarioAtual = idQuestionarioAtual;
		}

		public String getIdQuestionarioAtual()
		{
			return this.idQuestionarioAtual;
		}

		public void setQuestionariosAssociados(AdmQuestionarioVO[] questionariosAssociados)
		{
			this.questionariosAssociados = questionariosAssociados;
		}

		public AdmQuestionarioVO[] getQuestionariosAssociados()
		{
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null. This type doesn't have
			// a default constructor, so Workshop cannot initialize it for you.

			// TODO: Initialize questionariosAssociados if it is null or length == 0.
			//if(this.questionariosAssociados == null || this.questionariosAssociados.length == 0)
			//{
			//    this.questionariosAssociados = new AdmQuestionarioVO[1];
			//    this.questionariosAssociados[0] = new AdmQuestionarioVO(?);
			//}

			return this.questionariosAssociados;
		}

		public void setIdTipoPessoaAtual(String idTipoPessoaAtual)
		{
			this.idTipoPessoaAtual = idTipoPessoaAtual;
		}

		public String getIdTipoPessoaAtual()
		{
			return this.idTipoPessoaAtual;
		}

		public void setAdmTipoPessoaVO(AdmTipoPessoaVO[] admTipoPessoaVO)
		{
			this.admTipoPessoaVO = admTipoPessoaVO;
		}

		public AdmTipoPessoaVO[] getAdmTipoPessoaVO()
		{

			if(this.admTipoPessoaVO == null)
			{
				this.admTipoPessoaVO = new AdmTipoPessoaVO[0];
			}

			return this.admTipoPessoaVO;
		}
	}
	public FormQuestionario getFormQuestionario() {
		return this.formQuestionario;
	}

	protected boolean alwaysTrackPreviousPage()
	{
		return true;
	}

	protected boolean alwaysTrackPreviousAction()
	{
		return true;
	}


}
