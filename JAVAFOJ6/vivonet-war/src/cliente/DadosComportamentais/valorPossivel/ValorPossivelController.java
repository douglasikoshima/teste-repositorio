package cliente.DadosComportamentais.valorPossivel;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.vo.AssuntosDocument.Assuntos;
import br.com.vivo.fo.cliente.vo.ConteudosDocument.Conteudos;
import br.com.vivo.fo.cliente.vo.DisponibilidadeVODocument.DisponibilidadeVO;
import br.com.vivo.fo.cliente.vo.DisponibilidadesDocument.Disponibilidades;
import br.com.vivo.fo.cliente.vo.ListaConteudoVODocument.ListaConteudoVO;
import br.com.vivo.fo.cliente.vo.ListaValorPossivelVODocument.ListaValorPossivelVO;
import br.com.vivo.fo.cliente.vo.SubAssuntosDocument.SubAssuntos;
import br.com.vivo.fo.cliente.vo.ValorPossivelVODocument.ValorPossivelVO;
import br.com.vivo.fo.cliente.vo.ValoresPossiveisDocument.ValoresPossiveis;
import br.com.vivo.fo.cliente.vo.impl.AssuntoVODocumentImpl.AssuntoVOImpl;
import br.com.vivo.fo.cliente.vo.impl.ConteudoVODocumentImpl.ConteudoVOImpl;
import br.com.vivo.fo.cliente.vo.impl.DisponibilidadeVODocumentImpl.DisponibilidadeVOImpl;
import br.com.vivo.fo.cliente.vo.impl.SubAssuntoVODocumentImpl.SubAssuntoVOImpl;
import br.com.vivo.fo.cliente.vo.impl.ValorPossivelVODocumentImpl.ValorPossivelVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ValorPossivelController extends AbstractAction {

	private static final long serialVersionUID = -7330410563962889228L;

	@EJB
	private br.com.vivo.fo.ctrls.cliente.dadosComportamentais.DadosComportamentaisFacade dadosComportamentaisFacade;

	private static Logger log = new Logger("clientes");

	private String user = null;
	private ListaValorPossivelForm listaValorPossivelForm = null;

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("obterSubAssunto".equals(mapping.getParameter())) {
			return obterSubAssunto(mapping, form, request, response);
		} else if ("obterConteudo".equals(mapping.getParameter())) {
			return obterConteudo(mapping, form, request, response);
		} else if ("pesquisarValorPossivel".equals(mapping.getParameter())) {
			return pesquisarValorPossivel(mapping, form, request, response);
		} else if ("incluirAlterarValorPossivel".equals(mapping.getParameter())) {
			return incluirAlterarValorPossivel(mapping, form, request, response);
		} else if ("salvarValorPossivel".equals(mapping.getParameter())) {
			return salvarValorPossivel(mapping, form, request, response);
		} else if ("atualizarConteudoSelecionado".equals(mapping.getParameter())) {
			return atualizarConteudoSelecionado(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="valorPossivel.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			ListaValorPossivelForm form = (ListaValorPossivelForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ValorPossivelController:begin("+user+") >> INICIALIZADO");

			// instanciar Action Form
			listaValorPossivelForm = new ListaValorPossivelForm();

			// atualiza lista de disponibilidades
			DisponibilidadeVO[] disponibilidadeVOArray = new DisponibilidadeVOImpl[2];
			disponibilidadeVOArray[0] = DisponibilidadeVO.Factory.newInstance();
			disponibilidadeVOArray[0].setCodigo(ConstantesCRM.SONE);
			disponibilidadeVOArray[0].setDescricao("SIM");
			disponibilidadeVOArray[1] = DisponibilidadeVO.Factory.newInstance();
			disponibilidadeVOArray[1].setCodigo(ConstantesCRM.SZERO);
			disponibilidadeVOArray[1].setDescricao("NÃO");
			listaValorPossivelForm.getListaValorPossivelVO().getDisponibilidades().setDisponibilidadeVOArray(disponibilidadeVOArray);

			// obter a lista de Assuntos
			ListaConteudoVO listaConteudoVO = ListaConteudoVO.Factory.newInstance();
			listaConteudoVO = dadosComportamentaisFacade.getListas(user, "3, 5");
			Assuntos assuntos = listaConteudoVO.getAssuntos();

			// atualizar o Action Form
			listaValorPossivelForm.getListaValorPossivelVO().setAssuntos(assuntos);

			listaValorPossivelForm.setConteudoSelecionado(form.getConteudoSelecionado());
			limpListaValoresPossiveis();

			log.debug("ValorPossivelController:begin("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("ValorPossivelController:begin(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="valorPossivel.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward obterSubAssunto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			ListaValorPossivelForm form = (ListaValorPossivelForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ValorPossivelController:obterSubAssunto("+user+") >> INICIALIZADO");

			// obter o codigo do assunto
			int codigoAssunto = Integer.parseInt(form.getAssuntoSelecionado());
			log.debug("ValorPossivelController:obterSubAssunto("+user+") >> codigoAssunto: "+codigoAssunto);

			// Obter a lista de subAssuntos
			SubAssuntos subAssuntos = dadosComportamentaisFacade.getListaSubAssuntos(user, codigoAssunto, "3, 5");

			// atualizar o Action Form
			listaValorPossivelForm.getListaValorPossivelVO().setSubAssuntos(subAssuntos);
			listaValorPossivelForm.setAssuntoSelecionado(form.getAssuntoSelecionado());
			Conteudos conteudos = Conteudos.Factory.newInstance();
			listaValorPossivelForm.getListaValorPossivelVO().setConteudos(conteudos);
			listaValorPossivelForm.setSubAssuntoSelecionado(ConstantesCRM.SVAZIO);
			listaValorPossivelForm.setConteudoSelecionado(ConstantesCRM.SVAZIO);
			//listaValorPossivelForm.setConteudoSelecionado(form.getConteudoSelecionado());
			limpListaValoresPossiveis();

			log.debug("ValorPossivelController:obterSubAssunto("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("ValorPossivelController:obterSubAssunto(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/valorPossivel/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="valorPossivel.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward obterConteudo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			ListaValorPossivelForm form = (ListaValorPossivelForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ValorPossivelController:obterConteudo("+user+") >> INICIALIZADO");

			// obter o codigo do SubAssunto
			int codigoSubAssunto = Integer.parseInt(form.getSubAssuntoSelecionado());
			log.debug("ValorPossivelController:obterConteudo("+user+") >> codigoSubAssunto: "+codigoSubAssunto);

			// obter a lista de Conteudos
			Conteudos conteudos = dadosComportamentaisFacade.getListaConteudos(user, codigoSubAssunto, "3, 5");

			// atualizar o form
			listaValorPossivelForm.getListaValorPossivelVO().setConteudos(conteudos);
			listaValorPossivelForm.setSubAssuntoSelecionado(form.getSubAssuntoSelecionado());
			limpListaValoresPossiveis();

			log.debug("ValorPossivelController:obterConteudo("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("ValorPossivelController:obterConteudo(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/valorPossivel/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisaValorPossivel.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward pesquisarValorPossivel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			ListaValorPossivelForm form = (ListaValorPossivelForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ValorPossivelController:pesquisarValorPossivel("+user+") >> INICIALIZADO");

			// obter o codigo do conteudo
			int codigoConteudo = Integer.parseInt(form.getConteudoSelecionado());

			// obter a lista de valores possiveis
			ValoresPossiveis valoresPossiveis = dadosComportamentaisFacade.findListaValoresPossiveis(user, codigoConteudo);

			// atualizar Action Form
			listaValorPossivelForm.getListaValorPossivelVO().setValoresPossiveis(valoresPossiveis);

			if (request.getAttribute("inMsg") != null){
				log.debug("ValorPossivelController:pesquisarValorPossivel("+user+") >> inMsg: "+request.getAttribute("inMsg"));
				listaValorPossivelForm.setInMsgRetorno(request.getAttribute("inMsg").toString());
			}

			log.debug("ValorPossivelController:pesquisarValorPossivel("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("ValorPossivelController:pesquisarValorPossivel(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/valorPossivel/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="IncluiAlteraValolrPossivel.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward incluirAlterarValorPossivel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ValorPossivelController:incluirAlterarValorPossivel("+user+") >> INICIALIZADO");

			// configurar operacao
			String operacao = request.getParameter("operacao");
			if(operacao.equalsIgnoreCase("UPDATE")) {
				log.debug("ValorPossivelController:incluirAlterarValorPossivel("+user+") >> ALTERACAO");
				int index = Integer.parseInt(request.getParameter("index"));
				request.setAttribute("operacao", "UPDATE");
				request.setAttribute("index", String.valueOf(index));
				request.setAttribute("Titulo", "Alterar");

				listaValorPossivelForm.setValorPossivel(listaValorPossivelForm.getListaValorPossivelVO().getValoresPossiveis().getValorPossivelVOArray(index).getDescricao());
				listaValorPossivelForm.setSequenciaApresentacao(String.valueOf(listaValorPossivelForm.getListaValorPossivelVO().getValoresPossiveis().getValorPossivelVOArray(index).getSequenciaApresentacao()));
				listaValorPossivelForm.setDisponibilidadeSelecionado(String.valueOf(listaValorPossivelForm.getListaValorPossivelVO().getValoresPossiveis().getValorPossivelVOArray(index).getDisponibilidade()));
				listaValorPossivelForm.setCodigoValorPossivel(String.valueOf(listaValorPossivelForm.getListaValorPossivelVO().getValoresPossiveis().getValorPossivelVOArray(index).getCodigo()));
				listaValorPossivelForm.setConteudoSelecionado(String.valueOf(listaValorPossivelForm.getListaValorPossivelVO().getValoresPossiveis().getValorPossivelVOArray(index).getCodigoConteudo()));
			}
			else {
				log.debug("ValorPossivelController:incluirAlterarValorPossivel("+user+") >> INCLUSAO");
				request.setAttribute("operacao", "INSERT");
				request.setAttribute("index", "");
				request.setAttribute("Titulo", "Novo");
				listaValorPossivelForm.setConteudoSelecionado(request.getParameter("conteudo"));
				listaValorPossivelForm.setValorPossivel(ConstantesCRM.SVAZIO);
				listaValorPossivelForm.setSequenciaApresentacao(ConstantesCRM.SVAZIO);
				listaValorPossivelForm.setDisponibilidadeSelecionado(ConstantesCRM.SONE);
			}

			log.debug("ValorPossivelController:incluirAlterarValorPossivel("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("ValorPossivelController:incluirAlterarValorPossivel(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/valorPossivel/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisarValorPossivel.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarValorPossivel(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			ListaValorPossivelForm form  = (ListaValorPossivelForm) formParam;
			user = ConstantesCRM.getCurrentUser(request.getSession());
			log.debug("ValorPossivelController:salvarValorPossivel("+user+") >> INICIALIZADO");

			// obter informçoes da página de incl/Alt e montar a estrutura para gravacao
			ValorPossivelVO valorPossivelVO = ValorPossivelVO.Factory.newInstance();
			String resposta = null;

			String desc = form.getValorPossivel().trim();

			if (desc.length() > 0) {
				// verifica se é uma alteração ou inclusão e atualiza o ActionForm
				if(request.getParameter("operacao").equalsIgnoreCase("UPDATE")) {

					log.debug("ValorPossivelController:salvarValorPossivel("+user+") >> ALTERACAO");

					valorPossivelVO.setCodigo(Integer.parseInt(form.getCodigoValorPossivel()));

					valorPossivelVO.setCodigoConteudo(Integer.parseInt(form.getConteudoSelecionado()));
					valorPossivelVO.setDescricao(desc);
					valorPossivelVO.setSequenciaApresentacao(form.getSequenciaApresentacao());
					valorPossivelVO.setDisponibilidade(Integer.parseInt(form.getDisponibilidadeSelecionado()));

					// gravar valor possivel
					resposta = dadosComportamentaisFacade.setAlterarValorPossivel(user, valorPossivelVO);
					String index = request.getParameter("index");
					request.setAttribute("operacao", "UPDATE");
					request.setAttribute("index", index);
				}
				else if(request.getParameter("operacao").equalsIgnoreCase("INSERT")) {

					log.debug("ValorPossivelController:salvarValorPossivel("+user+") >> INSERCAO");

					valorPossivelVO.setCodigoConteudo(Integer.parseInt(form.getConteudoSelecionado()));
					valorPossivelVO.setDescricao(desc);
					valorPossivelVO.setSequenciaApresentacao(form.getSequenciaApresentacao());
					valorPossivelVO.setDisponibilidade(Integer.parseInt(form.getDisponibilidadeSelecionado()));

					// gravar valor possivel
					resposta = dadosComportamentaisFacade.setGravarValorPossivel(user, valorPossivelVO);
					request.setAttribute("operacao", "INSERT");
					request.setAttribute("index", ConstantesCRM.SVAZIO);
				}

				if(resposta.indexOf("DUPLICATE KEY")>0){
					log.debug("ValorPossivelController:salvarValorPossivel("+user+") >> FINALIZADO (Duplicate Key)");
					listaValorPossivelForm.setInMsgRetorno("true");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}else{
					log.debug("ValorPossivelController:salvarValorPossivel("+user+") >> FINALIZADO");
					listaValorPossivelForm.setInMsgRetorno("false");
					request.setAttribute("inMsg", "false");
					request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
					return mapping.findForward(ConstantesCRM.SUCCESS);
				}

			}else{
				log.debug("ValorPossivelController:salvarValorPossivel("+user+") >> FINALIZADO");
				listaValorPossivelForm.setInMsgRetorno("vazio");
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}
		} catch (Exception e) {
			log.error("ValorPossivelController:salvarValorPossivel(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/valorPossivel/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="resultadoPesquisaValorPossivel.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward atualizarConteudoSelecionado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try{
			ListaValorPossivelForm form = (ListaValorPossivelForm) formParam;
			log.debug("ValorPossivelController:atualizarConteudoSelecionado("+user+") >> INICIALIZADO");

			// atualiza o conteudo selecionado no Action Form
			listaValorPossivelForm.setConteudoSelecionado(form.getConteudoSelecionado());
			limpListaValoresPossiveis();

			log.debug("ValorPossivelController:atualizarConteudoSelecionado("+user+") >> FINALIZADO");
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);

		} catch (Exception e) {
			log.error("ValorPossivelController:atualizarConteudoSelecionado(" + user + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/cliente/DadosComportamentais/valorPossivel/begin.do");
			formError.setTarget(ConstantesCRM.FRAMEAPP);

			request.setAttribute(ConstantesCRM.SFORMERROR,formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
	}

	public static class ListaValorPossivelForm extends ActionForm {

		private static final long serialVersionUID = 5099532938462487455L;
		// conteudos
		private String assuntoSelecionado;
		private String subAssuntoSelecionado;
		private String conteudoSelecionado;
		private String valorPossivel;
		private String codigoValorPossivel;
		private String sequenciaApresentacao;
		private String disponibilidadeSelecionado;
		private String inMsgRetorno = null;

		// Value Object
		ListaValorPossivelVO listaValorPossivelVO = null;

		// Default Constructor
		public ListaValorPossivelForm() {
			// inicializa conteudos
			assuntoSelecionado         = ConstantesCRM.SVAZIO;
			subAssuntoSelecionado      = ConstantesCRM.SVAZIO;
			conteudoSelecionado        = ConstantesCRM.SVAZIO;
			valorPossivel              = ConstantesCRM.SVAZIO;
			sequenciaApresentacao      = ConstantesCRM.SVAZIO;
			disponibilidadeSelecionado = ConstantesCRM.SVAZIO;

			// inicializa VO
			listaValorPossivelVO = ListaValorPossivelVO.Factory.newInstance();

			listaValorPossivelVO.setAssuntos(Assuntos.Factory.newInstance());
			listaValorPossivelVO.getAssuntos().setAssuntoVOArray(new AssuntoVOImpl[0]);

			listaValorPossivelVO.setSubAssuntos(SubAssuntos.Factory.newInstance());
			listaValorPossivelVO.getSubAssuntos().setSubAssuntoVOArray(new SubAssuntoVOImpl[0]);

			listaValorPossivelVO.setConteudos(Conteudos.Factory.newInstance());
			listaValorPossivelVO.getConteudos().setConteudoVOArray(new ConteudoVOImpl[0]);

			listaValorPossivelVO.setDisponibilidades(Disponibilidades.Factory.newInstance());
			listaValorPossivelVO.getDisponibilidades().setDisponibilidadeVOArray(new DisponibilidadeVOImpl[0]);

			listaValorPossivelVO.setValoresPossiveis(ValoresPossiveis.Factory.newInstance());
			listaValorPossivelVO.getValoresPossiveis().setValorPossivelVOArray(new ValorPossivelVOImpl[0]);
		}

		public String getCodigoValorPossivel() {
			return(this.codigoValorPossivel);
		}

		public void setCodigoValorPossivel(String string) {
			this.codigoValorPossivel = string;
		}

		public String getAssuntoSelecionado() {
			return assuntoSelecionado;
		}

		public String getConteudoSelecionado() {
			return conteudoSelecionado;
		}

		public String getDisponibilidadeSelecionado() {
			return disponibilidadeSelecionado;
		}

		public ListaValorPossivelVO getListaValorPossivelVO() {
			return listaValorPossivelVO;
		}

		public String getSequenciaApresentacao() {
			return sequenciaApresentacao;
		}

		public String getSubAssuntoSelecionado() {
			return subAssuntoSelecionado;
		}

		public String getValorPossivel() {
			return valorPossivel;
		}

		public void setAssuntoSelecionado(String string) {
			assuntoSelecionado = string;
		}

		public void setConteudoSelecionado(String string) {
			conteudoSelecionado = string;
		}

		public void setDisponibilidadeSelecionado(String string) {
			disponibilidadeSelecionado = string;
		}

		public void setListaValorPossivelVO(ListaValorPossivelVO possivelVO) {
			listaValorPossivelVO = possivelVO;
		}

		public void setSequenciaApresentacao(String string) {
			sequenciaApresentacao = string;
		}

		public void setSubAssuntoSelecionado(String string) {
			subAssuntoSelecionado = string;
		}

		public void setValorPossivel(String string) {
			valorPossivel = string;
		}

		public void setInMsgRetorno(String inMsgRetorno)
		{
			this.inMsgRetorno = inMsgRetorno;
		}

		public String getInMsgRetorno()
		{
			return this.inMsgRetorno;
		}
	}

	// getter para o Action Form
	public ListaValorPossivelForm getListaValorPossivelForm() {
		return this.listaValorPossivelForm;
	}

	private void limpListaValoresPossiveis() {
		while(listaValorPossivelForm.getListaValorPossivelVO().getValoresPossiveis().sizeOfValorPossivelVOArray() > 0) {
			listaValorPossivelForm.getListaValorPossivelVO().getValoresPossiveis().removeValorPossivelVO(0);
		}
	}
}
