package admsistemas.admArvoreContato.workflow.RetornoCTI;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIResultadoVODocument.RetornoWFCTIResultadoVO;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO;
import br.com.vivo.fo.workflow.vo.WFGrupoSelecionadoVODocument.WFGrupoSelecionadoVO;
import br.com.vivo.fo.workflow.vo.impl.WFGrupoDisponivelVODocumentImpl.WFGrupoDisponivelVOImpl;
import br.com.vivo.fo.workflow.vo.impl.WFGrupoSelecionadoVODocumentImpl.WFGrupoSelecionadoVOImpl;

import com.indracompany.actions.AbstractAction;

public class RetornoCTIController extends AbstractAction {

	private static final long serialVersionUID = -5886099739590227082L;

	protected global.Global globalApp = new global.Global();

	private static  transient Logger log = new Logger("admsistemas");

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.retornoCTI.RetornoCTI retornoCTI;

	//Formulário para apresentação no jsp
	private RetornoCTIForm retornoCTIForm = new RetornoCTIForm();

	//Metodo para expor para o struts o formulário para utilização das tags
	public RetornoCTIForm getRetornoCTIForm() {
		return this.retornoCTIForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("iniciarMetodo".equals(mapping.getParameter())) {
			return iniciarMetodo(mapping, form, request, response);
		} else if ("carregaGrupos".equals(mapping.getParameter())) {
			return carregaGrupos(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RetornoCTI.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("RetornoCTIController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		RetornoCTIForm form = (RetornoCTIForm) formParam;
		//Obtem a instancia inicial
		this.retornoCTIForm = form;

		//Obtém o usuário logado
		this.retornoCTIForm.setUser(ConstantesCRM.getCurrentUser(request.getSession()));

		//Inicializa
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="pesquisar" path="iResultadoPesquisa.jsp"
	 * @jpf:forward name="novo" path="DetalheRetornoCTI.jsp"
	 * @jpf:forward name="alterar" path="DetalheRetornoCTI.jsp"
	 * @jpf:forward name="excluir" path="iResultadoPesquisa.jsp"
	 * @jpf:forward name="voltar" path="RetornoCTI.jsp"
	 */
	public ActionForward iniciarMetodo(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("RetornoCTIController:iniciarMetodo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		RetornoCTIForm form = (RetornoCTIForm) formParam;
		//Definições
		RetornoWFCTIVO retornoWFCTIVO = RetornoWFCTIVO.Factory.newInstance();

		//Limpa analise de erro
		this.retornoCTIForm.setErrorMessage( null );
		//Verifica operação a executar
		switch ( form.getMethod() ) {
		//Caso seja pesquisa
		case ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_PESQUISAR:
			//Preenche o formulário local com os dados da pesquisa
			retornoCTIForm.setRetornoWFCTIPesquisaVO( form.getRetornoWFCTIPesquisaVO() );

			//Consulta informações
			this.retornoCTIForm.setRetornoWFCTIResultado( retornoCTI.pesquisar(this.retornoCTIForm.getUser(), this.retornoCTIForm.getRetornoWFCTIPesquisaVO()) );

			//Executa tela de pesquisa
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("pesquisar");

			//Caso seja novo registro
		case ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_NOVO:
			//Processa montagem da tela para criação de novo registro
			this.retornoCTIForm.setRetornoWFCTIVO( retornoWFCTIVO );

			//Executa tela para cadastro
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("novo");

			//Caso seja alteração
		case ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_ALTERAR:
			//Processa montagem da tela para alteração
			retornoWFCTIVO.setIdRetornoWFCTI( form.getIdRetorno() );

			//Obtém o elemento para alteração
			retornoWFCTIVO = retornoCTI.consultarRetornoWFCTIVO(this.retornoCTIForm.getUser(), retornoWFCTIVO);

			//Monta o elemento para edição
			this.retornoCTIForm.setRetornoWFCTIVO( retornoWFCTIVO );

			//Executa tela de pesquisa
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("alterar");

			//Caso seja excluir
		case ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_EXCLUIR:
			//Monta o elemento para exclusao
			retornoWFCTIVO.setIdRetornoWFCTI( form.getIdRetorno() );

			//Processa a exclusão do registro solicitado
			retornoCTI.gravarRetornoWFCTIVO( this.retornoCTIForm.getUser(), 2, retornoWFCTIVO);

			//Consulta informações
			this.retornoCTIForm.setRetornoWFCTIResultado( retornoCTI.pesquisar(this.retornoCTIForm.getUser(), this.retornoCTIForm.getRetornoWFCTIPesquisaVO()) );

			//Executa tela de pesquisa
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("pesquisar");

			//Caso seja alteração
		case ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_VOLTAR:
			//Restaura as informações de pesquisa
			form.setRetornoWFCTIPesquisaVO( retornoCTIForm.getRetornoWFCTIPesquisaVO() );

			//Processa montagem da tela no caso de voltar
			//Executa tela de pesquisa
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("voltar");

			//Caso seja gravar novo
		case ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_NOVO_GRAVAR:
			//Trata a entrada
			if( form.getRetornoWFCTIVO().getInPadrao() != null ) {
				form.getRetornoWFCTIVO().setInPadrao( (form.getRetornoWFCTIVO().getInPadrao().equalsIgnoreCase("on") ? ConstantesCRM.SONE : ConstantesCRM.SZERO) );
			} else {
				form.getRetornoWFCTIVO().setInPadrao( ConstantesCRM.SZERO );
			}

			form.getRetornoWFCTIVO().setSgStatus( form.getRetornoWFCTIVO().getSgStatus().substring(0,1) );

			//Limpa os grupos disponíveis e selecionados
			form.getRetornoWFCTIVO().setWFGrupoDisponivelVOArray( new WFGrupoDisponivelVOImpl[0] );
			form.getRetornoWFCTIVO().setWFGrupoSelecionadoVOArray( new WFGrupoSelecionadoVOImpl[0] );

			//Monta os grupos selecionados
			if( (form.getIdGruposSelecionados() != null) && (form.getIdGruposSelecionados().length > 0) ) {
				//Define elemento para montagem
				WFGrupoSelecionadoVO[] aWFGrupoSelecionadoVO = new WFGrupoSelecionadoVO[ form.getIdGruposSelecionados().length ];

				//Monta os selecionados para envio
				for( int x = 0; x < form.getIdGruposSelecionados().length; x++ ) {
					aWFGrupoSelecionadoVO[x] = WFGrupoSelecionadoVO.Factory.newInstance();
					aWFGrupoSelecionadoVO[x].setIdGrupo( form.getIdGruposSelecionados()[x] );
				}

				//Monta os elementos selecionados
				form.getRetornoWFCTIVO().setWFGrupoSelecionadoVOArray( aWFGrupoSelecionadoVO );
			}

			//Verifica os erros enviados pelo tuxedo
			try {
				//Processa a gravação da alteração
				retornoWFCTIVO = retornoCTI.gravarRetornoWFCTIVO( this.retornoCTIForm.getUser(), 0, form.getRetornoWFCTIVO());

				this.retornoCTIForm.setErrorMessage("Campanha "+form.getRetornoWFCTIVO().getDsRetornoWFCTI()+" gravada com Sucesso!");
				this.retornoCTIForm.setRetornoWFCTIVO( form.getRetornoWFCTIVO() );

			} catch (TuxedoWarningException ex) {
				if (ex.getXmlHeader().getError().equals("4096")) {
					this.retornoCTIForm.setErrorMessage("Sigla "+form.getRetornoWFCTIVO().getSgRetornoWFCTI()+" Duplicada! Utilize outra sigla!");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("novo");
				} else {
					throw(ex);
				}
			}

			//Executa tela de pesquisa
			retornoCTIForm.setMethod(ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_VOLTAR);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("voltar");

			//Caso seja gravar novo
		case ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_ALTERAR_GRAVAR:
			//Trata a entrada
			if( form.getRetornoWFCTIVO().getInPadrao() != null ) {
				form.getRetornoWFCTIVO().setInPadrao( (form.getRetornoWFCTIVO().getInPadrao().equalsIgnoreCase("on") ? ConstantesCRM.SONE : ConstantesCRM.SZERO) );
			} else {
				form.getRetornoWFCTIVO().setInPadrao( ConstantesCRM.SZERO );
			}

			form.getRetornoWFCTIVO().setSgStatus( form.getRetornoWFCTIVO().getSgStatus().substring(0,1) );

			//Limpa os grupos disponíveis e selecionados
			form.getRetornoWFCTIVO().setWFGrupoDisponivelVOArray( new WFGrupoDisponivelVOImpl[0] );
			form.getRetornoWFCTIVO().setWFGrupoSelecionadoVOArray( new WFGrupoSelecionadoVOImpl[0] );

			//Monta os grupos selecionados
			if( (form.getIdGruposSelecionados() != null) && (form.getIdGruposSelecionados().length > 0) ) {
				//Define elemento para montagem
				WFGrupoSelecionadoVO[] aWFGrupoSelecionadoVO = new WFGrupoSelecionadoVO[ form.getIdGruposSelecionados().length ];

				//Monta os selecionados para envio
				for( int x = 0; x < form.getIdGruposSelecionados().length; x++ ) {
					aWFGrupoSelecionadoVO[x] = WFGrupoSelecionadoVO.Factory.newInstance();
					aWFGrupoSelecionadoVO[x].setIdGrupo( form.getIdGruposSelecionados()[x] );
				}

				//Monta os elementos selecionados
				form.getRetornoWFCTIVO().setWFGrupoSelecionadoVOArray( aWFGrupoSelecionadoVO );
			}

			//Verifica os erros enviados pelo tuxedo
			try {

				this.retornoCTIForm.setRetornoWFCTIVO( form.getRetornoWFCTIVO() );
				this.retornoCTIForm.setErrorMessage("Campanha alterada!");

				//Processa a gravação da alteração
				retornoWFCTIVO = retornoCTI.gravarRetornoWFCTIVO( this.retornoCTIForm.getUser(), 1, form.getRetornoWFCTIVO());

			} catch (TuxedoWarningException ex) {
				if (ex.getXmlHeader().getError().equals("4096")) {
					this.retornoCTIForm.setErrorMessage("Sigla "+form.getRetornoWFCTIVO().getSgRetornoWFCTI()+" Duplicada! Utilize outra sigla!");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward("alterar");
				} else {
					throw(ex);
				}
			}

			//Executa tela de pesquisa
			retornoCTIForm.setMethod(ConstantesCRM.CT_CADASTRO_ADM_CAMPANHAS_VOLTAR);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward("voltar");

		}

		//Caso não seja definido monta pesquisa
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("pesquisar");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ifrmCarregaGrupos.jsp"
	 */
	public ActionForward carregaGrupos(ActionMapping mapping, ActionForm formParam,HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("RetornoCTIController:iniciarMetodo(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		RetornoCTIForm form = (RetornoCTIForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		RetornoWFCTIVO retornoWFCTIVO = retornoCTIForm.getRetornoWFCTIVO();
		retornoWFCTIVO.setInPadrao(form.getRetornoWFCTIVO().getInPadrao()!=null?ConstantesCRM.SONE:ConstantesCRM.SZERO);

		//Obtém o elemento para alteração
		RetornoWFCTIVO retornoWFCTIVOGrupos = retornoCTI.carregarGrupos(user, retornoWFCTIVO);
		retornoWFCTIVOGrupos.setIdRetornoWFCTI(retornoWFCTIVO.getIdRetornoWFCTI());
		retornoWFCTIVOGrupos.setInPadrao(retornoWFCTIVO.getInPadrao());
		//Monta o elemento para edição
		this.retornoCTIForm.setRetornoWFCTIVO( retornoWFCTIVOGrupos );

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class RetornoCTIForm extends ActionForm {

		private static final long serialVersionUID = -8566912202357754916L;

		private String                  errorMessage           = null;
		private String                  user                   = null;
		private int                     method                 = -1;
		private String                  idRetorno              = null;
		private RetornoWFCTIVO          retornoWFCTIVO         = RetornoWFCTIVO.Factory.newInstance();
		private RetornoWFCTIVO          retornoWFCTIPesquisaVO = RetornoWFCTIVO.Factory.newInstance();
		private RetornoWFCTIResultadoVO retornoWFCTIResultado  = null;
		private String[]                idGruposDisponiveis    = null;
		private String[]                idGruposSelecionados   = null;

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}

		public String getErrorMessage() {
			return this.errorMessage;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getUser() {
			return this.user;
		}

		public void setMethod(int method) {
			this.method = method;
		}

		public int getMethod() {
			return this.method;
		}

		public void setIdRetorno(String idRetorno) {
			this.idRetorno = idRetorno;
		}

		public String getIdRetorno() {
			return this.idRetorno;
		}

		public void setRetornoWFCTIVO(RetornoWFCTIVO retornoWFCTIVO) {
			this.retornoWFCTIVO = retornoWFCTIVO;
		}

		public RetornoWFCTIVO getRetornoWFCTIVO() {
			return this.retornoWFCTIVO;
		}

		public void setRetornoWFCTIPesquisaVO(RetornoWFCTIVO retornoWFCTIPesquisaVO) {
			this.retornoWFCTIPesquisaVO = retornoWFCTIPesquisaVO;
		}

		public RetornoWFCTIVO getRetornoWFCTIPesquisaVO() {
			return this.retornoWFCTIPesquisaVO;
		}

		public void setRetornoWFCTIResultado(RetornoWFCTIResultadoVO retornoWFCTIResultado) {
			this.retornoWFCTIResultado = retornoWFCTIResultado;
		}

		public RetornoWFCTIResultadoVO getRetornoWFCTIResultado() {
			return this.retornoWFCTIResultado;
		}

		public void setIdGruposDisponiveis(String[] idGruposDisponiveis) {
			this.idGruposDisponiveis = idGruposDisponiveis;
		}

		public String[] getIdGruposDisponiveis() {
			return this.idGruposDisponiveis;
		}

		public void setIdGruposSelecionados(String[] idGruposSelecionados) {
			this.idGruposSelecionados = idGruposSelecionados;
		}

		public String[] getIdGruposSelecionados() {
			return this.idGruposSelecionados;
		}
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