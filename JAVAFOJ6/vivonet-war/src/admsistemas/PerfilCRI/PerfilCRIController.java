package admsistemas.PerfilCRI;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.PerfilDocument.Perfil;
import br.com.vivo.fo.admsistemas.vo.PerfilVariaveisAssociadasVODocument.PerfilVariaveisAssociadasVO;
import br.com.vivo.fo.admsistemas.vo.PerfilVariaveisDisponiveisVODocument.PerfilVariaveisDisponiveisVO;
import br.com.vivo.fo.admsistemas.vo.PerfilVariaveisVODocument.PerfilVariaveisVO;
import br.com.vivo.fo.admsistemas.vo.impl.AdmGrupoAberturaVODocumentImpl.AdmGrupoAberturaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.AdmNaturezaVODocumentImpl.AdmNaturezaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.AdmUFOperadoraSimplVODocumentImpl.AdmUFOperadoraSimplVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.CarterizacaoVODocumentImpl.CarterizacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.PerfilDocumentImpl.PerfilImpl;
import br.com.vivo.fo.admsistemas.vo.impl.ProcedenciaVODocumentImpl.ProcedenciaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.SegmentacaoVODocumentImpl.SegmentacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.TipoClienteVODocumentImpl.TipoClienteVOImpl;
import br.com.vivo.fo.cliente.vo.impl.TipoLinhaVODocumentImpl.TipoLinhaVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoException;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.workflow.vo.impl.CanalVODocumentImpl.CanalVOImpl;

import com.indracompany.actions.AbstractAction;

public class PerfilCRIController extends AbstractAction {

	private static final long serialVersionUID = -6270315807058709033L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.perfilCRI.PerfilCRI perfilCRIFacade;

	private String user = ConstantesCRM.SVAZIO;
	private PerfilForm perfilForm = null;

	private static final String QTD_LINHAS_PAGINA = "25";
	private static final String INCLUSAO_PERFIL = "1";
	private static final String INCLUSAO_VARAVEIS = "2";
	private static final String ALTERACAO_PERFIL = "3";
	private static final String TELA_MANTER_PERFIL = "1";
	private static final String TELA_MANTER_VARIAVEIS = "2";

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisaPerfil".equals(mapping.getParameter())) {
			return pesquisaPerfil(mapping, form, request, response);
		} else if ("carregaPerfil".equals(mapping.getParameter())) {
			return carregaPerfil(mapping, form, request, response);
		} else if ("excluiPerfil".equals(mapping.getParameter())) {
			return excluiPerfil(mapping, form, request, response);
		} else if ("incluirPerfil".equals(mapping.getParameter())) {
			return incluirPerfil(mapping, form, request, response);
		} else if ("limpaPesquisa".equals(mapping.getParameter())) {
			return limpaPesquisa(mapping, form, request, response);
		} else if ("carregaInlcui".equals(mapping.getParameter())) {
			return carregaInlcui(mapping, form, request, response);
		} else if ("carregaAltera".equals(mapping.getParameter())) {
			return carregaAltera(mapping, form, request, response);
		} else if ("editaRelPerfil".equals(mapping.getParameter())) {
			return editaRelPerfil(mapping, form, request, response);
		} else if ("statusPerfil".equals(mapping.getParameter())) {
			return statusPerfil(mapping, form, request, response);
		} else if ("voltaPesquisa".equals(mapping.getParameter())) {
			return voltaPesquisa(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="administracaoCRI.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("PerfilCRIController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		user = ConstantesCRM.getCurrentUser(request.getSession());
		this.perfilForm = new PerfilForm();
		this.perfilForm.setIdRetorno(ConstantesCRM.SVAZIO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="administracaoCRI.jsp" name="pesquisaPerfil"
	 */
	public ActionForward pesquisaPerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("PerfilCRIController:pesquisaPerfil(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PerfilForm form = (PerfilForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());

		String bloco = (form.getBloco() == null || form.getBloco().equals("")) ? "0" : form.getBloco();

		String inTotal = "<inTotal>" + Integer.parseInt(bloco) + "</inTotal>";
		String qtdLinhasBloco = "<qtdLinhasBloco>" + QTD_LINHAS_PAGINA + "</qtdLinhasBloco>";
		this.perfilForm.setIdRetorno("");
		this.perfilForm.setNmPerfil(form.getNmPerfil());
		this.perfilForm.setPerfilVariaveisVO(perfilCRIFacade.getPerfil(user, "<nmPerfil>" + form.getNmPerfil() + "</nmPerfil>" + inTotal + qtdLinhasBloco));
		this.perfilForm.setArrayLength(String.valueOf(this.perfilForm.getPerfilVariaveisVO().getPerfilArray().length));
		this.perfilForm.setInFim(this.perfilForm.getPerfilVariaveisVO().getInFim());
		this.perfilForm.setBloco(form.getBloco());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("pesquisaPerfil");
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="relacionarParametros.jsp" name="success"
	 */
	public ActionForward carregaPerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("PerfilCRIController:carregaPerfil(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		user = ConstantesCRM.getCurrentUser(request.getSession());

		this.perfilForm.setIdRetorno(ConstantesCRM.SVAZIO);
		PerfilVariaveisVO perfilVariaveisVO = perfilCRIFacade.getPerfilVariaveis(user, "<idPerfil>" + this.perfilForm.getIdPerfil() + "</idPerfil>");

		this.perfilForm.getPerfilVariaveisVO().setPerfilVariaveisAssociadasVO(perfilVariaveisVO.getPerfilVariaveisAssociadasVO());
		this.perfilForm.getPerfilVariaveisVO().setPerfilVariaveisDisponiveisVO(perfilVariaveisVO.getPerfilVariaveisDisponiveisVO());

		Perfil perfil = getPerfilArray(this.perfilForm.getPerfilVariaveisVO().getPerfilArray(), this.perfilForm.getIdPerfil());

		this.perfilForm.setNmPerfil(perfil.getNmPerfil());
		this.perfilForm.setIdPerfil(perfil.getIdPerfil());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="administracaoCRI.jsp" name="success"
	 */
	public ActionForward excluiPerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("PerfilCRIController:excluiPerfil(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PerfilForm form = (PerfilForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());

		String bloco = (form.getBloco() == null || form.getBloco().equals("") ? "0" : form.getBloco());
		String inTotal = "<inTotal>" + Integer.parseInt(bloco) + "</inTotal>";
		String qtdLinhasBloco = "<qtdLinhasBloco>" + QTD_LINHAS_PAGINA + "</qtdLinhasBloco>";

		WFAcaoRetornoVO wfRetAcao = this.perfilCRIFacade.excPerfilVariaveis(user, "<idPerfil>" + form.getIdPerfil() + "</idPerfil>");

		request.setAttribute("msgStatus", wfRetAcao.getMensagem());

		this.perfilForm.setIdRetorno(wfRetAcao.getAcaoExecucao());

		this.perfilForm.setNmPerfil(form.getNmPerfil());
		this.perfilForm.setPerfilVariaveisVO(perfilCRIFacade.getPerfil(user, "<nmPerfil>" + form.getNmPerfil() + "</nmPerfil>" + inTotal + qtdLinhasBloco));
		this.perfilForm.setInFim(this.perfilForm.getPerfilVariaveisVO().getInFim());
		this.perfilForm.setBloco(form.getBloco());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="administracaoCRI.jsp" name="manter_perfil"
	 * @jpf:forward path="Resultado.jsp" name="manter_var"
	 */
	public ActionForward incluirPerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("PerfilCRIController:incluirPerfil(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PerfilForm form = (PerfilForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());

		StringBuffer str = new StringBuffer(ConstantesCRM.SVAZIO);
		try {
			// Vindo da tela de Manutençao do Perfil.
			if (TELA_MANTER_PERFIL.equals(form.getInAcao())) {
				str.append("<PerfilVariaveisVO>");
				str.append("<Perfil>");

				if (form.getIdPerfil() != null && !form.getIdPerfil().equals("")) {
					str.append("<idPerfil>" + form.getIdPerfil() + "</idPerfil>");
					str.append("<inOperacao>" + ALTERACAO_PERFIL + "</inOperacao>");

				} else {
					str.append("<inOperacao>" + INCLUSAO_PERFIL + "</inOperacao>");
				}

				str.append("<nmPerfil>" + form.getNmPerfil() + "</nmPerfil>");

				str.append("</Perfil>");
				str.append("</PerfilVariaveisVO>");
				PerfilVariaveisVO perfilVariaveisVO = this.perfilCRIFacade.setPerfilVariaveis(user, str.toString());
				if (null != perfilVariaveisVO.getPerfilArray(0)) {
					// perfilVariaveisVO.getPerfilArray(0).setHabilitado("0");
					this.perfilForm.getPerfilVariaveisVO().setPerfilArray(perfilVariaveisVO.getPerfilArray());
				}
				this.perfilForm.setArrayLength(String.valueOf(this.perfilForm.getPerfilVariaveisVO().getPerfilArray().length));
				request.setAttribute("msgStatus", ConstantesCRM.SSucesso);

			} else if (TELA_MANTER_VARIAVEIS.equals(form.getInAcao())) {
				str.append("<idPerfil>" + form.getIdPerfil() + "</idPerfil>");
				str.append("<inOperacao>" + INCLUSAO_VARAVEIS + "</inOperacao>");

				// Tipo Linha
				str.append("<TipoLinhaVO>");
				for (int i = 0; i < form.getTipoLinhaAssociada().length; i++) {
					str.append("<id>" + form.getTipoLinhaAssociada()[i] + "</id>");
				}
				str.append("</TipoLinhaVO>");

				// Segmentaçao
				str.append("<SegmentacaoVO>");
				for (int i = 0; i < form.getSegmentacaoAssociada().length; i++) {
					str.append("<idSegmentacao>" + form.getSegmentacaoAssociada()[i] + "</idSegmentacao>");
				}
				str.append("</SegmentacaoVO>");

				// Carteira
				str.append("<CarterizacaoVO>");
				for (int i = 0; i < form.getCarterizacaoAssociada().length; i++) {
					str.append("<idTipoCarteira>" + form.getCarterizacaoAssociada()[i] + "</idTipoCarteira>");
				}
				str.append("</CarterizacaoVO>");

				// Procedencia
				str.append("<ProcedenciaVO>");
				for (int i = 0; i < form.getProcedenciaAssociada().length; i++) {
					str.append("<idProcedencia>" + form.getProcedenciaAssociada()[i] + "</idProcedencia>");
				}
				str.append("</ProcedenciaVO>");

				// Natureza Associada
				str.append("<AdmNaturezaVO>");
				for (int i = 0; i < form.getNaturezaAssociada().length; i++) {
					str.append("<idNatureza>" + form.getNaturezaAssociada()[i] + "</idNatureza>");
				}
				str.append("</AdmNaturezaVO>");

				// Tipo Cliente
				str.append("<TipoClienteVO>");
				for (int i = 0; i < form.getTipoClienteAssociada().length; i++) {
					str.append("<codigo>" + form.getTipoClienteAssociada()[i] + "</codigo>");
				}
				str.append("</TipoClienteVO>");

				// Canal
				str.append("<CanalVO>");
				for (int i = 0; i < form.getCanalAssociada().length; i++) {
					str.append("<idCanal>" + form.getCanalAssociada()[i] + "</idCanal>");
				}
				str.append("</CanalVO>");

				// Regional
				str.append("<AdmUFOperadoraVO>");
				for (int i = 0; i < form.getRegionalAssociada().length; i++) {
					str.append("<idUFOperadora>" + form.getRegionalAssociada()[i] + "</idUFOperadora>");
				}
				str.append("</AdmUFOperadoraVO>");

				// Grupo
				str.append("<AdmGrupoAberturaVO>");
				for (int i = 0; i < form.getGrupoAberturaAssociada().length; i++) {
					str.append("<idGrupo>" + form.getGrupoAberturaAssociada()[i] + "</idGrupo>");
				}
				str.append("</AdmGrupoAberturaVO>");

				perfilCRIFacade.setPerfilVariaveis(user, str.toString());
				request.setAttribute("msgStatus", ConstantesCRM.SSucesso);
			}
			this.perfilForm.setNmPerfil(ConstantesCRM.SVAZIO);
			this.perfilForm.setIdPerfil(ConstantesCRM.SVAZIO);

		} catch (TuxedoException te) {
			log.warn("PerfilCRIController:incluirPerfil" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + te.getXmlHeader().getStatusText());
			request.setAttribute("msgStatus", te.getXmlHeader().getStatusText());
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		if (TELA_MANTER_PERFIL.equals(form.getInAcao())) {
			return mapping.findForward("manter_perfil");
		} else if (TELA_MANTER_VARIAVEIS.equals(form.getInAcao())) {
			return mapping.findForward("manter_var");
		}
		return mapping.findForward("manter_perfil");
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="administracaoCRI.jsp" name="success"
	 */
	public ActionForward limpaPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("PerfilCRIController:limpaPesquisa(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		perfilForm.setNmPerfil(ConstantesCRM.SVAZIO);
		perfilForm.setIdPerfil(ConstantesCRM.SVAZIO);

		Perfil[] p = new Perfil[0];
		perfilForm.getPerfilVariaveisVO().setPerfilArray(p);
		perfilForm.setArrayLength(ConstantesCRM.SVAZIO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="incluiAlteraPerfil.jsp" name="success"
	 */
	public ActionForward carregaInlcui(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("PerfilCRIController:carregaInlcui(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		getPerfilForm().setIdPerfil(ConstantesCRM.SVAZIO);
		getPerfilForm().setNmPerfil(ConstantesCRM.SVAZIO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="incluiAlteraPerfil.jsp" name="success"
	 */
	public ActionForward carregaAltera(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("PerfilCRIController:carregaAltera(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PerfilForm form = (PerfilForm) formParam;
		Perfil perfil = getPerfilArray(perfilForm.getPerfilVariaveisVO().getPerfilArray(), form.getIdPerfil());

		getPerfilForm().setIdPerfil(perfil.getIdPerfil());
		getPerfilForm().setNmPerfil(perfil.getNmPerfil());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="editarRelPerfil.jsp" name="success"
	 */
	public ActionForward editaRelPerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("PerfilCRIController:editaRelPerfil(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PerfilForm form = (PerfilForm) formParam;
		Perfil perfil = getPerfilArray(this.getPerfilForm().getPerfilVariaveisVO().getPerfilArray(), form.getIdPerfil());
		// this.perfilForm.setPerfilVariaveisVO(perfilCRIFacade.getPerfilVariaveis(user,"<idPerfil>"+perfil.getIdPerfil()+"</idPerfil>"));

		this.getPerfilForm().setIdPerfil(perfil.getIdPerfil());
		this.getPerfilForm().setNmPerfil(perfil.getNmPerfil());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="pesquisaPerfil.do" name="success"
	 */
	public ActionForward statusPerfil(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("PerfilCRIController:statusPerfil(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		PerfilForm form = (PerfilForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());

		WFAcaoRetornoVO wfRetAcao = perfilCRIFacade.setStatusPerfil(user, form.getIdPerfil(), form.getInAcao());
		request.setAttribute("msgStatus", wfRetAcao.getMensagem());
		this.perfilForm.setIdRetorno(wfRetAcao.getAcaoExecucao());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward path="administracaoCRI.jsp" name="success"
	 */
	public ActionForward voltaPesquisa(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("PerfilCRIController:voltaPesquisa(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");

		this.perfilForm.setIdPerfil(ConstantesCRM.SVAZIO);
		this.perfilForm.setNmPerfil(ConstantesCRM.SVAZIO);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class PerfilForm extends ActionForm {
		private static final long serialVersionUID = 7078717354078906093L;

		private String inAcao;
		private String arrayLength;
		private String strMensagem = ConstantesCRM.SVAZIO;

		public void setMensagem(String strMensagem) {
			this.strMensagem = strMensagem;
		}

		public String getMensagem() {
			return this.strMensagem;
		}

		private String bloco = ConstantesCRM.SVAZIO;

		public void setBloco(String bloco) {
			this.bloco = bloco;
		}

		public String getBloco() {
			return this.bloco;
		}

		// Atributo de pesquisa
		private String inTotal = ConstantesCRM.SVAZIO;

		public void setInTotal(String inTotal) {
			this.inTotal = inTotal;
		}

		public String getInTotal() {
			return this.inTotal;
		}

		// Atributo de pesquisa
		private String inFim = ConstantesCRM.SVAZIO;

		public void setInFim(String inFim) {
			this.inFim = inFim;
		}

		public String getInFim() {
			return this.inFim;
		}

		// Id de Retorno
		private String idRetorno = ConstantesCRM.SVAZIO;

		public void setIdRetorno(String idRetorno) {
			this.idRetorno = idRetorno;
		}

		public String getIdRetorno() {
			return this.idRetorno;
		}

		// nome Perfil
		private String nmPerfil = ConstantesCRM.SVAZIO;

		public void setNmPerfil(String nmPerfil) {
			this.nmPerfil = nmPerfil;
		}

		public String getNmPerfil() {
			return this.nmPerfil;
		}

		// id Perfil
		private String idPerfil = ConstantesCRM.SVAZIO;

		public void setIdPerfil(String idPerfil) {
			this.idPerfil = idPerfil;
		}

		public String getIdPerfil() {
			return this.idPerfil;
		}

		// Tipo Linha
		private String[] tipoLinhaDisponivel;

		public String[] getTipoLinhaDisponivel() {
			return this.tipoLinhaDisponivel;
		}

		public void setTipoLinhaDisponivel(String[] strings) {
			this.tipoLinhaDisponivel = strings;
		}

		private String[] tipoLinhaAssociada;

		public String[] getTipoLinhaAssociada() {
			return this.tipoLinhaAssociada;
		}

		public void setTipoLinhaAssociada(String[] strings) {
			this.tipoLinhaAssociada = strings;
		}

		// Segmento
		private String[] segmentacaoAssociada;

		public String[] getSegmentacaoAssociada() {
			return this.segmentacaoAssociada;
		}

		public void setSegmentacaoAssociada(String[] strings) {
			this.segmentacaoAssociada = strings;
		}

		private String[] segmentacaoDisponivel;

		public String[] getSegmentacaoDisponivel() {
			return this.segmentacaoDisponivel;
		}

		public void setSegmentacaoDisponivel(String[] strings) {
			this.segmentacaoDisponivel = strings;
		}

		// Carteira
		private String[] carterizacaoAssociada;

		public String[] getCarterizacaoAssociada() {
			return this.carterizacaoAssociada;
		}

		public void setCarterizacaoAssociada(String[] strings) {
			this.carterizacaoAssociada = strings;
		}

		private String[] carterizacaoDisponivel;

		public String[] getCarterizacaoDisponivel() {
			return this.carterizacaoDisponivel;
		}

		public void setCarterizacaoDisponivel(String[] strings) {
			this.carterizacaoDisponivel = strings;
		}

		// Procedencia
		private String[] procedenciaAssociada;

		public String[] getProcedenciaAssociada() {
			return this.procedenciaAssociada;
		}

		public void setProcedenciaAssociada(String[] strings) {
			this.procedenciaAssociada = strings;
		}

		private String[] procedenciaDisponivel;

		public String[] getProcedenciaDisponivel() {
			return this.procedenciaDisponivel;
		}

		public void setProcedenciaDisponivel(String[] strings) {
			this.procedenciaDisponivel = strings;
		}

		// Natureza
		private String[] naturezaAssociada;

		public String[] getNaturezaAssociada() {
			return this.naturezaAssociada;
		}

		public void setNaturezaAssociada(String[] strings) {
			this.naturezaAssociada = strings;
		}

		private String[] naturezaDisponivel;

		public String[] getNaturezaDisponivel() {
			return this.naturezaDisponivel;
		}

		public void setNaturezaDisponivel(String[] strings) {
			this.naturezaDisponivel = strings;
		}

		// Tipo Cliente
		private String[] tipoClienteAssociada;

		public String[] getTipoClienteAssociada() {
			return this.tipoClienteAssociada;
		}

		public void setTipoClienteAssociada(String[] strings) {
			this.tipoClienteAssociada = strings;
		}

		private String[] tipoClienteDisponivel;

		public String[] getTipoClienteDisponivel() {
			return this.tipoClienteDisponivel;
		}

		public void setTipoClienteDisponivel(String[] strings) {
			this.tipoClienteDisponivel = strings;
		}

		// Grupo Abertura
		private String[] grupoAberturaAssociada;

		public String[] getGrupoAberturaAssociada() {
			return this.grupoAberturaAssociada;
		}

		public void setGrupoAberturaAssociada(String[] strings) {
			this.grupoAberturaAssociada = strings;
		}

		private String[] grupoAberturaDisponivel;

		public String[] getGrupoAberturaDisponivel() {
			return this.grupoAberturaDisponivel;
		}

		public void setGrupoAberturaDisponivel(String[] strings) {
			this.grupoAberturaDisponivel = strings;
		}

		// Canal
		private String[] canalAssociada;

		public String[] getCanalAssociada() {
			return this.canalAssociada;
		}

		public void setCanalAssociada(String[] strings) {
			this.canalAssociada = strings;
		}

		private String[] canalDisponivel;

		public String[] getCanalDisponivel() {
			return this.canalDisponivel;
		}

		public void setCanalDisponivel(String[] strings) {
			this.canalDisponivel = strings;
		}

		// Regional
		private String[] regionalAssociada;

		public String[] getRegionalAssociada() {
			return this.regionalAssociada;
		}

		public void setRegionalAssociada(String[] strings) {
			this.regionalAssociada = strings;
		}

		private String[] regionalDisponivel;

		public String[] getRegionalDisponivel() {
			return this.regionalDisponivel;
		}

		public void setRegionalDisponivel(String[] strings) {
			this.regionalDisponivel = strings;
		}

		// Perfil Variaveis
		private PerfilVariaveisVO perfilVariaveisVO;

		public PerfilVariaveisVO getPerfilVariaveisVO() {

			return this.perfilVariaveisVO;
		}

		public void setPerfilVariaveisVO(PerfilVariaveisVO perfilVariaveisVO) {
			this.perfilVariaveisVO = perfilVariaveisVO;
		}

		public PerfilForm() {

			this.perfilVariaveisVO = PerfilVariaveisVO.Factory.newInstance();

			this.perfilVariaveisVO.setPerfilArray(new PerfilImpl[0]);

			this.perfilVariaveisVO.setPerfilVariaveisDisponiveisVO(PerfilVariaveisDisponiveisVO.Factory.newInstance());
			this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setAdmNaturezaVOArray(new AdmNaturezaVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setAdmGrupoAberturaVOArray(new AdmGrupoAberturaVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setAdmUFOperadoraSimplVOArray(new AdmUFOperadoraSimplVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setCanalVOArray(new CanalVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setCarterizacaoVOArray(new CarterizacaoVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setProcedenciaVOArray(new ProcedenciaVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setSegmentacaoVOArray(new SegmentacaoVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setTipoClienteVOArray(new TipoClienteVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisDisponiveisVO().setTipoLinhaVOArray(new TipoLinhaVOImpl[0]);

			this.perfilVariaveisVO.setPerfilVariaveisAssociadasVO(PerfilVariaveisAssociadasVO.Factory.newInstance());
			this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setAdmNaturezaVOArray(new AdmNaturezaVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setAdmGrupoAberturaVOArray(new AdmGrupoAberturaVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setAdmUFOperadoraSimplVOArray(new AdmUFOperadoraSimplVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setCanalVOArray(new CanalVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setCarterizacaoVOArray(new CarterizacaoVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setProcedenciaVOArray(new ProcedenciaVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setSegmentacaoVOArray(new SegmentacaoVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setTipoClienteVOArray(new TipoClienteVOImpl[0]);
			this.perfilVariaveisVO.getPerfilVariaveisAssociadasVO().setTipoLinhaVOArray(new TipoLinhaVOImpl[0]);

			this.tipoLinhaDisponivel = new String[0];
			this.tipoLinhaAssociada = new String[0];

			this.segmentacaoDisponivel = new String[0];
			this.segmentacaoAssociada = new String[0];

			this.carterizacaoDisponivel = new String[0];
			this.carterizacaoAssociada = new String[0];

			this.procedenciaDisponivel = new String[0];
			this.procedenciaAssociada = new String[0];

			this.naturezaDisponivel = new String[0];
			this.naturezaAssociada = new String[0];

			this.tipoClienteDisponivel = new String[0];
			this.tipoClienteAssociada = new String[0];

			this.grupoAberturaDisponivel = new String[0];
			this.grupoAberturaAssociada = new String[0];

			this.canalDisponivel = new String[0];
			this.canalAssociada = new String[0];

			this.regionalDisponivel = new String[0];
			this.regionalAssociada = new String[0];
		}

		public void setArrayLength(String arrayLength) {
			this.arrayLength = arrayLength;
		}

		public String getArrayLength() {
			if (this.arrayLength == null) {
				this.arrayLength = ConstantesCRM.SVAZIO;
			}
			return this.arrayLength;
		}

		public void setInAcao(String inAcao) {
			this.inAcao = inAcao;
		}

		public String getInAcao() {
			if (this.inAcao == null) {
				this.inAcao = ConstantesCRM.SVAZIO;
			}

			return this.inAcao;
		}
	}

	// Getter para o Form perfilVariaveisVOForm
	public PerfilForm getPerfilForm() {

		if (this.perfilForm == null) {
			this.perfilForm = new PerfilForm();
		}

		return this.perfilForm;
	}

	private Perfil getPerfilArray(Perfil[] perfilParam, String id) {
		if (perfilParam == null || id == null) {
			return null;
		}

		for (int i = 0; i < perfilParam.length; i++) {
			if (id.equals(perfilParam[i].getIdPerfil())) {
				return perfilParam[i];
			}
		}

		return null;
	}
}
