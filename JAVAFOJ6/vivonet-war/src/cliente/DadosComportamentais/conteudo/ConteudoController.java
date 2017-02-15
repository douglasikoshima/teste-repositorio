package cliente.DadosComportamentais.conteudo;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.ArrayCanais;
import br.com.vivo.fo.cliente.vo.ArrayCanais.Canal;
import br.com.vivo.fo.cliente.vo.AssuntosDocument.Assuntos;
import br.com.vivo.fo.cliente.vo.ConteudoVODocument.ConteudoVO;
import br.com.vivo.fo.cliente.vo.DadoComportamentalDocument.DadoComportamental;
import br.com.vivo.fo.cliente.vo.DadosComportamentaisDocument.DadosComportamentais;
import br.com.vivo.fo.cliente.vo.DisponibilidadeVODocument.DisponibilidadeVO;
import br.com.vivo.fo.cliente.vo.DisponibilidadesDocument.Disponibilidades;
import br.com.vivo.fo.cliente.vo.ListaConteudoVODocument.ListaConteudoVO;
import br.com.vivo.fo.cliente.vo.SubAssuntoVODocument.SubAssuntoVO;
import br.com.vivo.fo.cliente.vo.SubAssuntosDocument.SubAssuntos;
import br.com.vivo.fo.cliente.vo.TipoApresentacaoVODocument.TipoApresentacaoVO;
import br.com.vivo.fo.cliente.vo.TiposApresentacaoDocument.TiposApresentacao;
import br.com.vivo.fo.cliente.vo.impl.AssuntoVODocumentImpl.AssuntoVOImpl;
import br.com.vivo.fo.cliente.vo.impl.DadoComportamentalDocumentImpl.DadoComportamentalImpl;
import br.com.vivo.fo.cliente.vo.impl.DisponibilidadeVODocumentImpl.DisponibilidadeVOImpl;
import br.com.vivo.fo.cliente.vo.impl.SubAssuntoVODocumentImpl.SubAssuntoVOImpl;
import br.com.vivo.fo.cliente.vo.impl.TipoApresentacaoVODocumentImpl.TipoApresentacaoVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ConteudoController extends AbstractAction {

	private static final long serialVersionUID = 7278595516661539000L;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.dadosComportamentais.DadosComportamentaisFacade dadosComportamentaisFacade;

	private boolean reload = false;

	private static Logger log = new Logger("clientes");

	// variáveis e objetos
	private String user = null;
	private ListaConteudoForm listaConteudoForm = null;

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
		} else if ("obterSubAssunto".equals(mapping.getParameter())) {
			return obterSubAssunto(mapping, form, request, response);
		} else if ("pesquisarListaConteudo".equals(mapping.getParameter())) {
			return pesquisarListaConteudo(mapping, form, request, response);
		} else if ("incluirAlterarConteudo".equals(mapping.getParameter())) {
			return incluirAlterarConteudo(mapping, form, request, response);
		} else if ("salvarConteudo".equals(mapping.getParameter())) {
			return salvarConteudo(mapping, form, request, response);
		} else if ("limpaListaConteudo".equals(mapping.getParameter())) {
			return limpaListaConteudo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="conteudo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ConteudoController:begin("+user+") >> INICIALIZADO");

			// cria instancia do Action Form
			listaConteudoForm = new ListaConteudoForm();
			listaConteudoForm.setListaConteudoVO(dadosComportamentaisFacade.getListas(user, ConstantesCRM.SVAZIO));

			DisponibilidadeVO[] disponibilidadeVOArray = new DisponibilidadeVOImpl[2];
			disponibilidadeVOArray[0] = DisponibilidadeVO.Factory.newInstance();
			disponibilidadeVOArray[0].setCodigo(ConstantesCRM.SONE);
			disponibilidadeVOArray[0].setDescricao("SIM");
			disponibilidadeVOArray[1] = DisponibilidadeVO.Factory.newInstance();
			disponibilidadeVOArray[1].setCodigo(ConstantesCRM.SZERO);
			disponibilidadeVOArray[1].setDescricao("NÃO");
			listaConteudoForm.getListaConteudoVO().getDisponibilidades().setDisponibilidadeVOArray(disponibilidadeVOArray);

			log.debug("ConteudoController:begin("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("ConteudoController:begin(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="limpaListaConteudo.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward obterSubAssunto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			ListaConteudoForm form = (ListaConteudoForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ConteudoController:obterSubAssunto("+user+") >> INICIALIZADO");

			String strReload = request.getParameter("reload");
			if(null!= strReload) {
				reload =true;
			}
			// obtem a lista de SubAssuntos
			listaConteudoForm.getListaConteudoVO().setSubAssuntos(dadosComportamentaisFacade.getListaSubAssuntos(user, Integer.parseInt(form.getAssuntoSelecionado()), ""));

			// atualiza o que já foi selecionado
			listaConteudoForm.setAssuntoSelecionado(form.getAssuntoSelecionado());

			log.debug("ConteudoController:obterSubAssunto("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("ConteudoController:obterSubAssunto(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/conteudo/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisaConteudo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisarListaConteudo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			ListaConteudoForm form = (ListaConteudoForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ConteudoController:pesquisarListaConteudo("+user+") >> INICIALIZADO");

			// obtem a lista de Dados Comportamentais
			//int codigoAssunto       = Integer.parseInt(form.getAssuntoSelecionado());
			int codigoSubAssunto    = Integer.parseInt(form.getSubAssuntoSelecionado());
			log.debug("ConteudoController:obterSubAssunto("+user+") >> codigoSubAssunto: "+codigoSubAssunto);
			DadosComportamentais dc = dadosComportamentaisFacade.findDadosComportamentais(user,codigoSubAssunto);
			listaConteudoForm.getListaConteudoVO().setDadosComportamentais(dc);
			listaConteudoForm.setSubAssuntoSelecionado(form.getSubAssuntoSelecionado());

			if (request.getAttribute("inMsg") != null){
				log.debug("ConteudoController:obterSubAssunto("+user+") >> inMsgRetorno: "+request.getAttribute("inMsg").toString());
				listaConteudoForm.setInMsgRetorno(request.getAttribute("inMsg").toString());
			}

			log.debug("ConteudoController:pesquisarListaConteudo("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("ConteudoController:pesquisarListaConteudo(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/conteudo/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IncluiAlteraConteudo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluirAlterarConteudo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			ListaConteudoForm form = (ListaConteudoForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ConteudoController:incluirAlterarConteudo("+user+") >> INICIALIZADO");

			// obtem o tipo de operação
			String operacao = request.getParameter("operacao");

			if(operacao.equalsIgnoreCase("UPDATE")) {
				log.debug("ConteudoController:incluirAlterarConteudo("+user+") >> ALTERACAO");
				int index = Integer.parseInt(request.getParameter("index"));
				request.setAttribute("operacao", "UPDATE");
				request.setAttribute("index", request.getParameter("index"));
				request.setAttribute("Titulo", "Alterar");


				listaConteudoForm.setAssuntoSelecionado(form.getAssuntoSelecionado());
				listaConteudoForm.setSubAssuntoSelecionado(form.getSubAssuntoSelecionado());
				listaConteudoForm.setDisponibilidadeAssociada(String.valueOf(listaConteudoForm.getListaConteudoVO().getDadosComportamentais().getDadoComportamentalArray(index).getDisponibilidadeVO().getCodigo()));
				listaConteudoForm.setDisponibilidadeSelecionado(String.valueOf(listaConteudoForm.getListaConteudoVO().getDadosComportamentais().getDadoComportamentalArray(index).getDisponibilidadeVO().getCodigo()));
				listaConteudoForm.setcodigoConteudo(String.valueOf(listaConteudoForm.getListaConteudoVO().getDadosComportamentais().getDadoComportamentalArray(index).getConteudoVO().getCodigo()));
				listaConteudoForm.setConteudo(listaConteudoForm.getListaConteudoVO().getDadosComportamentais().getDadoComportamentalArray(index).getConteudoVO().getDescricao());
				listaConteudoForm.setSequenciaApresentacao(String.valueOf(listaConteudoForm.getListaConteudoVO().getDadosComportamentais().getDadoComportamentalArray(index).getSequenciaApresentacao()));
				listaConteudoForm.setTipoApresentacaoSelecionado(String.valueOf(listaConteudoForm.getListaConteudoVO().getDadosComportamentais().getDadoComportamentalArray(index).getTipoApresentacaoVO().getCodigo()));
				listaConteudoForm.setCanaisSelecionadosVO(listaConteudoForm.getListaConteudoVO().getDadosComportamentais().getDadoComportamentalArray(index).getConteudoVO().getCanais().getCanalArray());

				/*TEMPORÁRIO PARA TESTES
                Canal[] can = new Canal[2];
                can[0] = listaConteudoForm.getListaConteudoVO().getCanais().getCanalArray(0);
                can[1] = listaConteudoForm.getListaConteudoVO().getCanais().getCanalArray(5);
                listaConteudoForm.setCanaisSelecionadosVO(can);*/

			}
			else {
				log.debug("ConteudoController:incluirAlterarConteudo("+user+") >> INSERCAO");
				request.setAttribute("operacao", "INSERT");
				request.setAttribute("index", ConstantesCRM.SVAZIO);
				request.setAttribute("Titulo", "Novo");
				listaConteudoForm.setAssuntoSelecionado(form.getAssuntoSelecionado());
				listaConteudoForm.setSubAssuntoSelecionado(form.getSubAssuntoSelecionado());
				listaConteudoForm.setDisponibilidadeAssociada(ConstantesCRM.SONE);
				listaConteudoForm.setDisponibilidadeSelecionado(ConstantesCRM.SONE);
				listaConteudoForm.setTipoApresentacaoSelecionado(ConstantesCRM.SVAZIO);
				listaConteudoForm.setSequenciaApresentacao(ConstantesCRM.SVAZIO);
				listaConteudoForm.setConteudo(ConstantesCRM.SVAZIO);
				listaConteudoForm.setCanaisSelecionados(new String[0]);
				listaConteudoForm.setCanaisSelecionadosVO(new Canal[0]);
			}

			log.debug("ConteudoController:incluirAlterarConteudo("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("ConteudoController:pesquisarListaConteudo(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/conteudo/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarListaConteudo.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarConteudo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			ListaConteudoForm form = (ListaConteudoForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ConteudoController:salvarConteudo("+user+") >> INICIALIZADO");

			SubAssuntoVO subAssuntoVO = SubAssuntoVO.Factory.newInstance();
			TipoApresentacaoVO tipoApresentacaoVO = TipoApresentacaoVO.Factory.newInstance();
			DisponibilidadeVO disponibilidadeVO = DisponibilidadeVO.Factory.newInstance();
			ConteudoVO conteudoVO = ConteudoVO.Factory.newInstance();
			String resposta = null;

			String desc = form.getConteudo().trim();

			if (desc.length() > 0) {
				// verifica se é uma alteração ou inclusão
				if(request.getParameter("operacao").equalsIgnoreCase("UPDATE")) {
					log.debug("ConteudoController:salvarConteudo("+user+") >> ALTERACAO");
					// monta dado comportamental para gravação
					DadoComportamental dc = DadoComportamental.Factory.newInstance();

					// obtem o indice do item a ser alterado
					int idx = Integer.parseInt(request.getParameter("index"));

					subAssuntoVO.setCodigo(Integer.parseInt(form.getSubAssuntoSelecionado()));
					dc.setSubAssuntoVO(subAssuntoVO);


					tipoApresentacaoVO.setCodigo(Integer.parseInt(form.getTipoApresentacaoSelecionado()));
					dc.setTipoApresentacaoVO(tipoApresentacaoVO);

					disponibilidadeVO.setCodigo(form.getDisponibilidadeSelecionado());
					dc.setDisponibilidadeVO(disponibilidadeVO);

					conteudoVO.setDescricao(desc);
					conteudoVO.setCodigo(Integer.parseInt(form.getCodigoConteudo()));
					dc.setConteudoVO(conteudoVO);

					dc.setSequenciaApresentacao(form.getSequenciaApresentacao());

					if (form.getCanaisSelecionados() != null && form.getCanaisSelecionados().length > 0) {
						Canal[] canal = new Canal[form.getCanaisSelecionados().length];

						for(int i=0; i < form.getCanaisSelecionados().length; i++) {
							canal[i] = Canal.Factory.newInstance();
							canal[i].setIdCanal(form.getCanaisSelecionados()[i]);
						}
						ArrayCanais array = ArrayCanais.Factory.newInstance();
						array.setCanalArray(canal);
						dc.getConteudoVO().setCanais(array);
					}

					listaConteudoForm.getListaConteudoVO().getDadosComportamentais().setDadoComportamentalArray(idx, dc);

					// executa gravação
					resposta = dadosComportamentaisFacade.setAlteraDadoComportamental(user, dc);

					String index = request.getParameter("index");
					request.setAttribute("operacao", "UPDATE");
					request.setAttribute("index", index);
				}
				else if(request.getParameter("operacao").equalsIgnoreCase("INSERT")) {

					log.debug("ConteudoController:salvarConteudo("+user+") >> INSERCAO");

					// Inclui o novo dado comportamental no form
					DadoComportamental dc = listaConteudoForm.getListaConteudoVO().getDadosComportamentais().addNewDadoComportamental();

					subAssuntoVO.setCodigo(Integer.parseInt(form.getSubAssuntoSelecionado()));
					dc.setSubAssuntoVO(subAssuntoVO);

					tipoApresentacaoVO.setCodigo(Integer.parseInt(form.getTipoApresentacaoSelecionado()));
					dc.setTipoApresentacaoVO(tipoApresentacaoVO);

					disponibilidadeVO.setCodigo(form.getDisponibilidadeSelecionado());
					dc.setDisponibilidadeVO(disponibilidadeVO);

					conteudoVO.setDescricao(desc);
					dc.setConteudoVO(conteudoVO);

					dc.setSequenciaApresentacao(form.getSequenciaApresentacao());

					if (form.getCanaisSelecionados() != null && form.getCanaisSelecionados().length > 0) {
						Canal[] canal = new Canal[form.getCanaisSelecionados().length];

						for(int i=0; i < form.getCanaisSelecionados().length; i++) {
							canal[i] = Canal.Factory.newInstance();
							canal[i].setIdCanal(form.getCanaisSelecionados()[i]);
						}
						ArrayCanais array = ArrayCanais.Factory.newInstance();
						array.setCanalArray(canal);
						dc.getConteudoVO().setCanais(array);
					}

					// executa gravação
					resposta = dadosComportamentaisFacade.setGravaDadoComportamental(user, dc);
					request.setAttribute("operacao", "INSERT");
					request.setAttribute("index", ConstantesCRM.SVAZIO);
				}

				if(resposta.indexOf("DUPLICATE KEY")>0){
					log.debug("ConteudoController:salvarConteudo("+user+") >> FINALIZADO (Duplicate key)");
					listaConteudoForm.setInMsgRetorno("true");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}else{
					log.debug("ConteudoController:salvarConteudo("+user+") >> FINALIZADO");
					listaConteudoForm.setInMsgRetorno("false");
					request.setAttribute("inMsg", "false");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}
			}else{
				log.debug("ConteudoController:salvarConteudo("+user+") >> FINALIZADO");
				listaConteudoForm.setInMsgRetorno("vazio");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}
		} catch (Exception e) {
			log.error("ConteudoController:salvarConteudo(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/conteudo/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success1" path="pesquisarListaConteudo.do"
	 * @jpf:forward name="success" path="conteudo.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward limpaListaConteudo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			ListaConteudoForm form = (ListaConteudoForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ConteudoController:limpaListaConteudo("+user+") >> INICIALIZADO");

			// atualiza se algo foi selecionado
			listaConteudoForm.setAssuntoSelecionado(form.getAssuntoSelecionado());
			listaConteudoForm.setSubAssuntoSelecionado(form.getSubAssuntoSelecionado());

			limpListaConteudo();

			if(reload){
				log.debug("ConteudoController:limpaListaConteudo("+user+") >> FINALIZADO (RELOAD)");
				reload=false;
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward("success1");
			}

			log.debug("ConteudoController:limpaListaConteudo("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("ConteudoController:limpaListaConteudo(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/conteudo/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	public static class ListaConteudoForm extends ActionForm {

		private static final long serialVersionUID = 5467647201440907958L;

		private Canal[] canaisSelecionadosVO;
		private String[] canaisSelecionados;
		private String[] canaisDisponiveis;
		// variáveis para recebimento das informacoes da View
		private String assuntoSelecionado;
		private String subAssuntoSelecionado;
		private String conteudo;
		private String codigoConteudo;
		private String tipoApresentacaoSelecionado;
		private String sequenciaApresentacao;
		private String disponibilidadeSelecionado;
		private String inMsgRetorno = null;
		//guarda a disponibilidade associada ao assunto
		private String disponibilidadeAssociada;

		// VO Lista de Conteudo
		private ListaConteudoVO listaConteudoVO;

		/**
		 * Default Constructor
		 */
		public ListaConteudoForm() {
			// inicializa conteudos
			assuntoSelecionado          = ConstantesCRM.SVAZIO;
			subAssuntoSelecionado       = ConstantesCRM.SVAZIO;
			conteudo                    = ConstantesCRM.SVAZIO;
			tipoApresentacaoSelecionado = ConstantesCRM.SVAZIO;
			sequenciaApresentacao       = ConstantesCRM.SVAZIO;
			disponibilidadeSelecionado  = ConstantesCRM.SVAZIO;

			// inicializa VO
			listaConteudoVO = ListaConteudoVO.Factory.newInstance();

			listaConteudoVO.setAssuntos(Assuntos.Factory.newInstance());
			listaConteudoVO.getAssuntos().setAssuntoVOArray(new AssuntoVOImpl[0]);

			listaConteudoVO.setSubAssuntos(SubAssuntos.Factory.newInstance());
			listaConteudoVO.getSubAssuntos().setSubAssuntoVOArray(new SubAssuntoVOImpl[0]);

			listaConteudoVO.setTiposApresentacao(TiposApresentacao.Factory.newInstance());
			listaConteudoVO.getTiposApresentacao().setTipoApresentacaoVOArray(new TipoApresentacaoVOImpl[0]);

			listaConteudoVO.setDisponibilidades(Disponibilidades.Factory.newInstance());
			listaConteudoVO.getDisponibilidades().setDisponibilidadeVOArray(new DisponibilidadeVOImpl[0]);

			listaConteudoVO.setDadosComportamentais(DadosComportamentais.Factory.newInstance());
			listaConteudoVO.getDadosComportamentais().setDadoComportamentalArray(new DadoComportamentalImpl[0]);

			listaConteudoVO.setCanais(ArrayCanais.Factory.newInstance());
			listaConteudoVO.getCanais().setCanalArray(new Canal[0]);

			canaisSelecionadosVO = new Canal[0];
		}

		public String getDisponibilidadeAssociada() {
			return disponibilidadeAssociada;
		}
		public void setDisponibilidadeAssociada(String string) {
			disponibilidadeAssociada = string;
		}
		public String getCodigoConteudo() {
			return codigoConteudo;
		}

		public void setcodigoConteudo(String string) {
			codigoConteudo = string;
		}
		public String getAssuntoSelecionado() {
			return assuntoSelecionado;
		}

		public String getConteudo() {
			return conteudo;
		}

		public String getDisponibilidadeSelecionado() {
			return disponibilidadeSelecionado;
		}

		public ListaConteudoVO getListaConteudoVO() {
			return listaConteudoVO;
		}

		public String getSequenciaApresentacao() {
			return sequenciaApresentacao;
		}

		public String getSubAssuntoSelecionado() {
			return subAssuntoSelecionado;
		}

		public String getTipoApresentacaoSelecionado() {
			return tipoApresentacaoSelecionado;
		}

		public void setAssuntoSelecionado(String string) {
			assuntoSelecionado = string;
		}

		public void setConteudo(String string) {
			conteudo = string;
		}

		public void setDisponibilidadeSelecionado(String string) {
			disponibilidadeSelecionado = string;
		}

		public void setListaConteudoVO(ListaConteudoVO conteudoVO) {
			listaConteudoVO = conteudoVO;
		}

		public void setSequenciaApresentacao(String string) {
			sequenciaApresentacao = string;
		}

		public void setSubAssuntoSelecionado(String string) {
			subAssuntoSelecionado = string;
		}

		public void setTipoApresentacaoSelecionado(String string) {
			tipoApresentacaoSelecionado = string;
		}

		public void setInMsgRetorno(String inMsgRetorno)
		{
			this.inMsgRetorno = inMsgRetorno;
		}

		public String getInMsgRetorno()
		{
			return this.inMsgRetorno;
		}

		public void setCanaisSelecionados(String[] canaisSelecionados)
		{
			this.canaisSelecionados = canaisSelecionados;
		}

		public String[] getCanaisSelecionados()
		{
			return this.canaisSelecionados;
		}

		public void setCanaisDisponiveis(String[] canaisDisponiveis)
		{
			this.canaisDisponiveis = canaisDisponiveis;
		}

		public String[] getCanaisDisponiveis()
		{
			return this.canaisDisponiveis;
		}

		public void setCanaisSelecionadosVO(Canal[] canaisSelecionadosVO)
		{
			this.canaisSelecionadosVO = canaisSelecionadosVO;
		}

		public Canal[] getCanaisSelecionadosVO()
		{
			return this.canaisSelecionadosVO;
		}
	}

	public ListaConteudoForm getListaConteudoForm() {
		return listaConteudoForm;
	}

	private void limpListaConteudo() {
		while(listaConteudoForm.getListaConteudoVO().getDadosComportamentais().sizeOfDadoComportamentalArray() > 0) {
			listaConteudoForm.getListaConteudoVO().getDadosComportamentais().removeDadoComportamental(0);
		}
	}
}
