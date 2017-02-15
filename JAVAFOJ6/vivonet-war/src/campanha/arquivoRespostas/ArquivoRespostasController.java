package campanha.arquivoRespostas;

import java.io.PrintWriter;
import java.text.SimpleDateFormat;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.campanha.vo.ApoioVODocument.ApoioVO;
import br.com.vivo.fo.campanha.vo.CampanhaFiltroRelatorioVODocument.CampanhaFiltroRelatorioVO;
import br.com.vivo.fo.campanha.vo.GrupoCampanhaApoioVODocument.GrupoCampanhaApoioVO;
import br.com.vivo.fo.campanha.vo.RelArquivoRespostasVODocument.RelArquivoRespostasVO;
import br.com.vivo.fo.constantes.ConstantesCRM;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ArquivoRespostasController extends AbstractAction {

	private static final long serialVersionUID = -7678283439615153377L;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.manter.ApoioFacade apoioFacade;

	@EJB
	private br.com.vivo.fo.ctrls.campanha.relatorio.RelatorioCampanhaFacade relFacade;

	private static final String SEPARADOR = "\t";
	private static final String ASPAS = "\"";
	private ArquivoForm arquivoForm;
	private FormHeader formHeader;

	protected global.Global globalApp = new global.Global();

	private FormHeader getFormHeader() {
		if (this.formHeader == null) {
			this.formHeader = new FormHeader();
		}
		return this.formHeader;
	}

	public ArquivoForm getArquivoForm() {
		if (this.arquivoForm == null) {
			this.arquivoForm = new ArquivoForm();
		}
		return this.arquivoForm;
	}

	private void loadFormHeader(ArquivoForm form) {
		for (int i = 0; i < getArquivoForm().getListaCampanha().length; i++) {
			/*
			 * código = id
			 */
			if (form.getIdCampanha().equals(String.valueOf(getArquivoForm().getListaCampanha()[i].getCodigo()))) {
				getFormHeader().setSgCampanha((getArquivoForm().getListaCampanha()[i].getSigla()));
				break;
			}
		}

		for (int i = 0; i < getArquivoForm().getListaSubCampanha().length; i++) {
			/*
			 * código = id
			 */
			if (form.getIdSubCampanha().equals(String.valueOf(getArquivoForm().getListaSubCampanha()[i].getCodigo()))) {
				getFormHeader().setNmSubCampanha(getArquivoForm().getListaSubCampanha()[i].getNmSubCampanha());
				break;
			}
		}

		for (int i = 0; i < getArquivoForm().getListaCanalCampanha().length; i++) {
			/*
			 * código = id
			 */
			if (form.getIdCanalCampanha().equals(String.valueOf(getArquivoForm().getListaCanalCampanha()[i].getCodigo()))) {
				getFormHeader().setNmCanal(getArquivoForm().getListaCanalCampanha()[i].getDescricao());
				break;
			}
		}
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("gerarArquivo".equals(mapping.getParameter())) {
			return gerarArquivo(mapping, form, request, response);
		} else if ("carregarSubCampanha".equals(mapping.getParameter())) {
			return carregarSubCampanha(mapping, form, request, response);
		} else if ("carregarCanais".equals(mapping.getParameter())) {
			return carregarCanais(mapping, form, request, response);
		} else if ("carregarVersao".equals(mapping.getParameter())) {
			return carregarVersao(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		try {
			GrupoCampanhaApoioVO campanhaApoio = apoioFacade.getApoioCampanha(user);
			this.arquivoForm = null;
			/*********************** Lista de Campanhas ***************************************************************/
			getArquivoForm().setListaCampanha(campanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/inicio.jsp");
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward gerarArquivo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ArquivoForm form = (ArquivoForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		CampanhaFiltroRelatorioVO filtro = CampanhaFiltroRelatorioVO.Factory.newInstance();
		filtro.setDtFim(form.getDtFim());
		filtro.setDtInicio(form.getDtInicio());
		filtro.setIdCampanha(form.getIdCampanha());
		// filtro.setIdSubCampanha(form.getIdSubCampanha());
		filtro.setIdSubCampanha(form.getVersaoSelecionada());
		filtro.setIdCanalCampanha(form.getIdCanalCampanha());
		filtro.setInDistincao(form.getInDistincao());
		filtro.setIdRelatorio(ConstantesCRM.SSEVEN);

		try {
			RelArquivoRespostasVO retorno = relFacade.getArqResp(user, filtro);

			if (retorno.getDsScriptPerguntaArray().length > 0 && retorno.getLinhaArray().length > 0) {
				StringBuffer arq = new StringBuffer();

				String fileName = ConstantesCRM.SVAZIO;
				java.util.Date hoje = new java.util.Date();
				SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy hh_mm_ss");

				// Carrega loader
				loadFormHeader(form);

				arq.append("Campanha: " + getFormHeader().getSgCampanha());
				arq.append("\n");
				arq.append("Sub Campanha: " + getFormHeader().getNmSubCampanha());
				arq.append("\n");
				arq.append("Canal de Atendimento: " + getFormHeader().getNmCanal());
				arq.append("\n");
				arq.append("Data de Atendimento: de " + form.getDtInicio() + " a " + form.getDtFim());
				arq.append("\n");
				arq.append("Data da Geração do Arquivo: " + formatter.format(hoje).replaceAll("_", ":"));
				arq.append("\n");

				arq.append("Data Atendimento");
				arq.append(SEPARADOR);
				arq.append("Código Área");
				arq.append(SEPARADOR);
				arq.append("Linha");
				arq.append(SEPARADOR);
				arq.append("Tipo Linha");
				arq.append(SEPARADOR);
				arq.append("Segmentação");
				arq.append(SEPARADOR);
				arq.append("Campanha");
				arq.append(SEPARADOR);
				arq.append("Sub Campanha");
				arq.append(SEPARADOR);
				arq.append("Canal");
				arq.append(SEPARADOR);
				arq.append("Status Atendimento");
				arq.append(SEPARADOR);
				arq.append("Motivo");
				arq.append(SEPARADOR);
				arq.append("Sub Motivo");
				arq.append(SEPARADOR);
				arq.append("Status Linha");
				arq.append(SEPARADOR);
				arq.append("Operadora");
				arq.append(SEPARADOR);
				arq.append("Representante");
				arq.append(SEPARADOR);
				arq.append("Login");

				for (int z = 0; z < retorno.getDsScriptPerguntaArray().length; z++) {
					arq.append(ASPAS + retorno.getDsScriptPerguntaArray(z) + ASPAS);
					arq.append(SEPARADOR);
				}

				arq.append("\n");

				for (int i = 0; i < retorno.getLinhaArray().length; i++) {
					arq.append(retorno.getLinhaArray(i).getDtAtendimento());
					arq.append(SEPARADOR);
					arq.append(retorno.getLinhaArray(i).getCodigoArea());
					arq.append(SEPARADOR);
					arq.append(retorno.getLinhaArray(i).getNrTelefone());
					arq.append(SEPARADOR);
					arq.append(retorno.getLinhaArray(i).getTipoLinha());
					arq.append(SEPARADOR);
					arq.append(retorno.getLinhaArray(i).getSegmentecao());
					arq.append(SEPARADOR);
					arq.append(retorno.getLinhaArray(i).getSgCampanha());
					arq.append(SEPARADOR);
					arq.append(retorno.getLinhaArray(i).getNmSubCampanha());
					arq.append(SEPARADOR);
					arq.append(retorno.getLinhaArray(i).getNmCanal());
					arq.append(SEPARADOR);
					arq.append(retorno.getLinhaArray(i).getDsTipoStatusCampanha());
					arq.append(SEPARADOR);
					arq.append(retorno.getLinhaArray(i).getDsTipoMotivoCampanha());
					arq.append(SEPARADOR);
					arq.append(retorno.getLinhaArray(i).getDsTipoSubMotivoCampanha());
					arq.append(SEPARADOR);
					arq.append("ATIVA");
					arq.append(SEPARADOR);
					arq.append("SILVIA");
					arq.append(SEPARADOR);
					arq.append("ADMINFO");
					arq.append(SEPARADOR);
					arq.append("ADMINFO");
					arq.append(SEPARADOR);

					for (int j = 0; j < retorno.getLinhaArray(i).getPerguntasRespostas().getDsScriptRespostaArray().length; j++) {
						arq.append(retorno.getLinhaArray(i).getPerguntasRespostas().getDsScriptRespostaArray(j));
						arq.append(SEPARADOR);
					}
					arq.append("\n");
				}

				fileName = getFormHeader().getSgCampanha() + "_" + getFormHeader().getNmSubCampanha() + "_" + formatter.format(hoje) + ".xls";

				response.addHeader("Content-Disposition", "attachment; filename=" + fileName);
				response.addHeader("Content-Type", "application/force-download");
				response.addHeader("Content-Transfer-Encoding", "binary");
				response.addHeader("Pragma", "no-cache");
				response.addHeader("Expires", ConstantesCRM.SZERO);

				PrintWriter out = response.getWriter();
				out.println(arq.toString());
				out.flush();
				out.close();
				return null;

			} else {
				form.setListaCampanha(getArquivoForm().getListaCampanha());
				form.setListaSubCampanha(getArquivoForm().getListaSubCampanha());
				form.setListaCanalCampanha(getArquivoForm().getListaCanalCampanha());
				form.setListaVersao(getArquivoForm().getListaVersao());
				arquivoForm = form;
				request.setAttribute("msgRetorno", "A Pesquisa Não Retornou Resultado.");
			}

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserSubCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward carregarSubCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ArquivoForm form = (ArquivoForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			GrupoCampanhaApoioVO grupoSubCampanhaApoio = apoioFacade.getApoioSubCampanha(user, form.getIdCampanha());

			form.setListaSubCampanha(grupoSubCampanhaApoio.getSubGrupoApoioVOArray(0).getApoioVOArray());
			form.setListaCampanha(getArquivoForm().getListaCampanha());
			arquivoForm = form;
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserCanais.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward carregarCanais(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ArquivoForm form = (ArquivoForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			GrupoCampanhaApoioVO itens = apoioFacade.getApoioCanalCampanha(user, form.getVersaoSelecionada(), 0);
			form.setListaCanalCampanha(itens.getSubGrupoApoioVOArray(0).getApoioVOArray());

			form.setListaSubCampanha(getArquivoForm().getListaSubCampanha());
			form.setListaCampanha(getArquivoForm().getListaCampanha());
			form.setListaVersao(getArquivoForm().getListaVersao());

			arquivoForm = form;
		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="innerBrowserVersao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward carregarVersao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ArquivoForm form = (ArquivoForm) formParam;

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			GrupoCampanhaApoioVO itens = apoioFacade.getApoioVersao(user, form.getIdSubCampanha());

			form.setListaVersao(itens.getSubGrupoApoioVOArray(0).getApoioVOArray());
			form.setListaSubCampanha(getArquivoForm().getListaSubCampanha());
			form.setListaCampanha(getArquivoForm().getListaCampanha());

			String idVersao = ConstantesCRM.SVAZIO;

			if (itens.getSubGrupoApoioVOArray(0).getApoioVOArray() != null && itens.getSubGrupoApoioVOArray(0).getApoioVOArray().length > 0) {
				idVersao = itens.getSubGrupoApoioVOArray(0).getApoioVOArray()[0].getCodigo();
			}

			GrupoCampanhaApoioVO itensCanais = apoioFacade.getApoioCanalCampanha(user, idVersao, 0);
			form.setListaCanalCampanha(itensCanais.getSubGrupoApoioVOArray(0).getApoioVOArray());

			arquivoForm = form;

		} catch (Exception e) {
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class FormHeader extends ActionForm {

		private static final long serialVersionUID = 1469339623976825763L;

		private String sgCampanha;
		private String nmSubCampanha;
		private String nmCanal;

		public void setNmSubCampanha(String nmSubCampanha) {
			this.nmSubCampanha = nmSubCampanha;
		}

		public String getNmSubCampanha() {
			return this.nmSubCampanha;
		}

		public void setSgCampanha(String sgCampanha) {
			this.sgCampanha = sgCampanha;
		}

		public String getSgCampanha() {
			return this.sgCampanha;
		}

		public void setNmCanal(String nmCanal) {
			this.nmCanal = nmCanal;
		}

		public String getNmCanal() {
			return this.nmCanal;
		}
	}

	public static class ArquivoForm extends ActionForm {

		private static final long serialVersionUID = 4400384119701235556L;

		private String versaoSelecionada;
		private ApoioVO[] listaVersao;
		private String inDistincao = ConstantesCRM.SONE;
		private ApoioVO[] listaCanalCampanha;
		private String idCanalCampanha;
		private String idSubCampanha;
		private String idCampanha;
		private ApoioVO[] listaSubCampanha;
		private String dtFim;
		private String dtInicio;
		private ApoioVO[] listaCampanha;

		public void setListaCampanha(ApoioVO[] listaCampanha) {
			this.listaCampanha = listaCampanha;
		}

		public ApoioVO[] getListaCampanha() {
			if (this.listaCampanha == null) {
				this.listaCampanha = new ApoioVO[0];
			}
			return this.listaCampanha;
		}

		public void setDtInicio(String dtInicio) {
			this.dtInicio = dtInicio;
		}

		public String getDtInicio() {
			return this.dtInicio;
		}

		public void setDtFim(String dtFim) {
			this.dtFim = dtFim;
		}

		public String getDtFim() {
			return this.dtFim;
		}

		public void setListaSubCampanha(ApoioVO[] listaSubCampanha) {
			this.listaSubCampanha = listaSubCampanha;
		}

		public ApoioVO[] getListaSubCampanha() {
			if (this.listaSubCampanha == null) {
				this.listaSubCampanha = new ApoioVO[0];
			}
			return this.listaSubCampanha;
		}

		public void setIdCampanha(String idCampanha) {
			this.idCampanha = idCampanha;
		}

		public String getIdCampanha() {
			return this.idCampanha;
		}

		public void setIdSubCampanha(String idSubCampanha) {
			this.idSubCampanha = idSubCampanha;
		}

		public String getIdSubCampanha() {
			return this.idSubCampanha;
		}

		public void setIdCanalCampanha(String idCanalCampanha) {
			this.idCanalCampanha = idCanalCampanha;
		}

		public String getIdCanalCampanha() {
			return this.idCanalCampanha;
		}

		public void setListaCanalCampanha(ApoioVO[] listaCanalCampanha) {
			this.listaCanalCampanha = listaCanalCampanha;
		}

		public ApoioVO[] getListaCanalCampanha() {
			if (this.listaCanalCampanha == null) {
				this.listaCanalCampanha = new ApoioVO[0];
			}
			return this.listaCanalCampanha;
		}

		public void setInDistincao(String inDistincao) {
			this.inDistincao = inDistincao;
		}

		public String getInDistincao() {
			return this.inDistincao;
		}

		public void setListaVersao(ApoioVO[] listaVersao) {
			this.listaVersao = listaVersao;
		}

		public ApoioVO[] getListaVersao() {
			if (this.listaVersao == null) {
				this.listaVersao = new ApoioVO[0];
			}
			return this.listaVersao;
		}

		public void setVersaoSelecionada(String versaoSelecionada) {
			this.versaoSelecionada = versaoSelecionada;
		}

		public String getVersaoSelecionada() {
			return this.versaoSelecionada;
		}
	}
}
