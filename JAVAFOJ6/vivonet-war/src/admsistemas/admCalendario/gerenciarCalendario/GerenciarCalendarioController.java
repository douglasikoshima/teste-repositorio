package admsistemas.admCalendario.gerenciarCalendario;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmCalendarioContainerVODocument.AdmCalendarioContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmFeriadoVODocument.AdmFeriadoVO;
import br.com.vivo.fo.admsistemas.vo.MunicipioVODocument.MunicipioVO;
import br.com.vivo.fo.cliente.vo.UFVODocument.UFVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class GerenciarCalendarioController extends AbstractAction {

	private static final long serialVersionUID = -4057054457208791374L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.calendario.CalendarioFacade controlCalendario;

	private FormGerenciamento formGerenciamento = null;

	protected global.Global globalApp = new global.Global();

	private static Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("copiarCalendario".equals(mapping.getParameter())) {
			return copiarCalendario(mapping, form, request, response);
		} else if ("salvarFeriadosMoveis".equals(mapping.getParameter())) {
			return salvarFeriadosMoveis(mapping, form, request, response);
		} else if ("carregarPonte".equals(mapping.getParameter())) {
			return carregarPonte(mapping, form, request, response);
		} else if ("salvarPonte".equals(mapping.getParameter())) {
			return salvarPonte(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="gerenciarFeriado.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (formGerenciamento == null) {
				formGerenciamento = new FormGerenciamento();
			}
			formGerenciamento.setIndicativoExibeCopia(ConstantesCRM.SYES);

		} catch (Exception e) {
			log.error("Exception - GerenciarCalendarioController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
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
	 * @jpf:forward name="success" path="gerenciarFeriado.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward copiarCalendario(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		formGerenciamento.setMsgError(ConstantesCRM.SVAZIO);

		try {
			FormGerenciamento form = (FormGerenciamento) formParam;
			AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();

			if (formGerenciamento == null) {
				formGerenciamento = new FormGerenciamento();
				formGerenciamento.setMsgError(ConstantesCRM.SVAZIO);
			}
			admCalendarioContainerVO.setAnoBase(form.getAnoBase());
			admCalendarioContainerVO.setAnoCopia(form.getAnoCopia());

			formGerenciamento.setAnoBase(form.getAnoBase());
			formGerenciamento.setAnoCopia(form.getAnoCopia());

			formGerenciamento.setAdmCalendarioContainerVO(controlCalendario.copiarCalendario(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
			formGerenciamento.setListaAdmFeriadoVO(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray());
			/*
			 * for(int i=0;i<formGerenciamento.getListaAdmFeriadoVO().length;i++){
			 * formGerenciamento.getAdmCalendarioContainerVO
			 * ().getAdmFeriadoVOArray(i).getDtFeriado().replaceAll(formGerenciamento
			 * .getAdmCalendarioContainerVO().getAnoBase
			 * (),String.valueOf(Integer.parseInt(formGerenciamento.getAdmCalendarioContainerVO().getAnoBase()+1))); }
			 */
			formGerenciamento.setMsgError("Feriados fixos foram automaticamente registrados.");
			formGerenciamento.setIndicativoExibirMoveis(ConstantesCRM.SYES);

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - GerenciarCalendarioController:copiarCalendario(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			// formGerenciamento.setMsgStatus("Falha na cópia do calendário.");
			formGerenciamento.setIndicativoExibirMoveis(ConstantesCRM.SNO);
			formGerenciamento.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));

		} catch (Exception e) {
			log.error("Exception - GerenciarCalendarioController:copiarCalendario(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		// if (request.getSession().getAttribute("formGerenciamentoFeriado")!=null)
		// request.getSession().removeAttribute("formGerenciamentoFeriado");
		// request.getSession().setAttribute("formGerenciamentoFeriado",(FormGerenciamento)formGerenciamento);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="gerenciarFeriado.jsp"
	 * @jpf:forward name="success1" return-to="currentPage"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarFeriadosMoveis(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		formGerenciamento.setMsgError(ConstantesCRM.SVAZIO);
		FormGerenciamento form = (FormGerenciamento) formParam;
		try {

			int tamanho = Integer.parseInt(form.getContador());
			AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();
			AdmFeriadoVO[] admFeriadoVO = new AdmFeriadoVO[tamanho];
			boolean flgChecked = false;

			int incrementa = 0;
			int contador = Integer.parseInt(form.getContador());

			// formGerenciamento.setListaAdmFeriadoVO(((FormGerenciamento)request.getSession().getAttribute("formGerenciamentoFeriado")).getListaAdmFeriadoVO());
			// formGerenciamento.setAdmCalendarioContainerVO(((FormGerenciamento)request.getSession().getAttribute("formGerenciamentoFeriado")).getAdmCalendarioContainerVO());
			// formGerenciamento.setAnoBase(((FormGerenciamento)request.getSession().getAttribute("formGerenciamentoFeriado")).getAnoBase());
			// formGerenciamento.setAnoCopia(((FormGerenciamento)request.getSession().getAttribute("formGerenciamentoFeriado")).getAnoCopia());

			formGerenciamento.setContador(form.getContador());
			formGerenciamento.setContadorPonte(form.getContadorPonte());

			// request.getSession().removeAttribute("formGerenciamentoFeriado");

			for (int i = 0; i < Integer.parseInt(form.getContador()); i++) {
				if (!((request.getParameter("seleciona" + i)) == null)) {
					flgChecked = true;
					admFeriadoVO[incrementa] = AdmFeriadoVO.Factory.newInstance();
					admFeriadoVO[incrementa].setDsFeriado(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getDsFeriado());
					admFeriadoVO[incrementa].setDsTipoFeriado(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getDsTipoFeriado());
					admFeriadoVO[incrementa].setDtFeriado(request.getParameter("dtAbertura" + i));
					admFeriadoVO[incrementa].setIdDsFeriado(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getIdDsFeriado());
					admFeriadoVO[incrementa].setIdFeriado(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getIdFeriado());
					admFeriadoVO[incrementa].setIdTipoFeriado(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getIdTipoFeriado());
					admFeriadoVO[incrementa].setIndMovel(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getIndMovel());
					admFeriadoVO[incrementa].setInRelatorio(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getInRelatorio());
					if (!request.getParameter("dtAbertura" + i).endsWith(formGerenciamento.getAnoCopia())) {
						formGerenciamento.setMsgError("Ano inválido para a cópia de feriado móvel.");
						request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
						return mapping.findForward("success1");
					}
					if (formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getRelacionados().getMunicipioVOArray().length > 0) {
						admFeriadoVO[incrementa].addNewRelacionados();
						for (int j = 0; j < formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getRelacionados().getMunicipioVOArray().length; j++) {
							MunicipioVO municipioVO = MunicipioVO.Factory.newInstance();
							municipioVO.setIdMunicipio(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getRelacionados().getMunicipioVOArray(j).getIdMunicipio());
							admFeriadoVO[incrementa].getRelacionados().insertNewMunicipioVO(j);
							admFeriadoVO[incrementa].getRelacionados().setMunicipioVOArray(j, municipioVO);
						}
					}
					if (formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getRelacionados().getUFVOArray().length > 0) {
						admFeriadoVO[incrementa].addNewRelacionados();
						for (int j = 0; j < formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getRelacionados().getUFVOArray().length; j++) {
							UFVO uFVO = UFVO.Factory.newInstance();
							uFVO.setIdUF(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getRelacionados().getUFVOArray(j).getIdUF());

							admFeriadoVO[incrementa].getRelacionados().insertNewUFVO(j);
							admFeriadoVO[incrementa].getRelacionados().setUFVOArray(j, uFVO);
						}
					}
					admCalendarioContainerVO.insertNewAdmFeriadoVO(incrementa);
					admCalendarioContainerVO.setAdmFeriadoVOArray(incrementa, admFeriadoVO[incrementa]);
					contador++;
					incrementa++;
				}
			}

			formGerenciamento.setAdmCalendarioContainerVO(controlCalendario.salvaFeriadosMoveis(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
			formGerenciamento.setListaAdmFeriadoVO(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray());

			formGerenciamento.setIndicativoExibirMoveis(ConstantesCRM.SNO);
			formGerenciamento.setIndicativoExibeCopia(ConstantesCRM.SNO);
			formGerenciamento.setIndicativoExibirBotoesPonte(ConstantesCRM.SYES);

			if (flgChecked) {
				formGerenciamento.setMsgError("Feriados moveis foram gravados com sucesso.");
			}

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - GerenciarCalendarioController:salvarFeriadosMoveis(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formGerenciamento.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
			// formGerenciamento.setMsgStatus("Falha na tentativa de salvar feriados moveis.");
			formGerenciamento.setIndicativoExibirMoveis(ConstantesCRM.SYES);
			formGerenciamento.setIndicativoExibeCopia(ConstantesCRM.SYES);
			formGerenciamento.setIndicativoExibirBotoesPonte(ConstantesCRM.SNO);

		} catch (Exception e) {
			log.error("Exception - GerenciarCalendarioController:salvarFeriadosMoveis(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		// if (request.getSession().getAttribute("formGerenciamentoFeriado")!=null)
		// request.getSession().removeAttribute("formGerenciamentoFeriado");
		// request.getSession().setAttribute("formGerenciamentoFeriado",(FormGerenciamento)formGerenciamento);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="gerenciarFeriado.jsp"
	 * @jpf:forward name="success1" return-to="currentPage"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward carregarPonte(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		formGerenciamento.setMsgError(ConstantesCRM.SVAZIO);
		FormGerenciamento form = (FormGerenciamento) formParam;
		try {

			AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();

			// formGerenciamento.setListaAdmFeriadoVO(((FormGerenciamento)request.getSession().getAttribute("formGerenciamentoFeriado")).getListaAdmFeriadoVO());
			// formGerenciamento.setAdmCalendarioContainerVO(((FormGerenciamento)request.getSession().getAttribute("formGerenciamentoFeriado")).getAdmCalendarioContainerVO());
			// formGerenciamento.setAnoBase(((FormGerenciamento)request.getSession().getAttribute("formGerenciamentoFeriado")).getAnoBase());
			// formGerenciamento.setAnoCopia(((FormGerenciamento)request.getSession().getAttribute("formGerenciamentoFeriado")).getAnoCopia());

			formGerenciamento.setContador(form.getContador());
			formGerenciamento.setContadorPonte(form.getContadorPonte());

			// request.getSession().removeAttribute("formGerenciamentoFeriado");

			admCalendarioContainerVO.setAnoBase(formGerenciamento.getAnoCopia());
			// admCalendarioContainerVO.setAnoBase(formGerenciamento.getAnoBase());
			admCalendarioContainerVO.setAnoCopia(formGerenciamento.getAnoCopia());

			formGerenciamento.setAdmCalendarioContainerVO(controlCalendario.carregarPonte(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
			formGerenciamento.setListaAdmFeriadoVO(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray());

			formGerenciamento.setIndicativoExibirPontes(ConstantesCRM.SYES);
			formGerenciamento.setIndicativoExibirMoveis(ConstantesCRM.SNO);
			formGerenciamento.setIndicativoExibeCopia(ConstantesCRM.SNO);
			formGerenciamento.setIndicativoExibirBotoesPonte(ConstantesCRM.SNO);
		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - GerenciarCalendarioController:carregarPonte(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formGerenciamento.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
			// formGerenciamento.setMsgStatus("Falha na tentativa de carregar ponte.");
			/*
			 * formGerenciamento.setIndicativoExibirPontes(ConstantesCRM.SNO); formGerenciamento.setIndicativoExibirMoveis(ConstantesCRM.SYES);
			 * formGerenciamento.setIndicativoExibeCopia(ConstantesCRM.SYES);
			 * formGerenciamento.setIndicativoExibirBotoesPonte(ConstantesCRM.SYES);
			 */

		} catch (Exception e) {
			log.error("Exception - GerenciarCalendarioController:carregarPonte(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		// if (request.getSession().getAttribute("formGerenciamentoFeriado")!=null)
		// request.getSession().removeAttribute("formGerenciamentoFeriado");
		// request.getSession().setAttribute("formGerenciamentoFeriado",(FormGerenciamento)formGerenciamento);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="gerenciarFeriado.jsp"
	 * @jpf:forward name="success1" return-to="currentPage"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarPonte(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		formGerenciamento.setMsgError(ConstantesCRM.SVAZIO);
		FormGerenciamento form = (FormGerenciamento) formParam;
		try {

			int tamanho = Integer.parseInt(form.getContadorPonte());
			AdmCalendarioContainerVO admCalendarioContainerVO = AdmCalendarioContainerVO.Factory.newInstance();
			AdmFeriadoVO[] admFeriadoVO = new AdmFeriadoVO[tamanho];

			int incrementa = 0;
			int contador = Integer.parseInt(form.getContadorPonte());
			if (formGerenciamento == null) {
				formGerenciamento = new FormGerenciamento();
				formGerenciamento.setMsgError(ConstantesCRM.SVAZIO);
			}

			formGerenciamento.setContador(form.getContador());
			formGerenciamento.setContadorPonte(form.getContadorPonte());

			// request.getSession().removeAttribute("formGerenciamentoFeriado");

			for (int i = 0; i < Integer.parseInt(form.getContadorPonte()); i++) {
				if (!((request.getParameter("seleciona" + i)) == null)) {
					admFeriadoVO[incrementa] = AdmFeriadoVO.Factory.newInstance();
					admFeriadoVO[incrementa].setDsFeriado(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getDsFeriado());
					admFeriadoVO[incrementa].setDsTipoFeriado(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getDsTipoFeriado());
					admFeriadoVO[incrementa].setDtFeriado(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getDtFeriado());
					admFeriadoVO[incrementa].setIdDsFeriado(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getIdDsFeriado());
					admFeriadoVO[incrementa].setIdFeriado(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getIdFeriado());
					admFeriadoVO[incrementa].setIdMunicipio(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getIdMunicipio());
					admFeriadoVO[incrementa].setIdTipoFeriado(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getIdTipoFeriado());
					admFeriadoVO[incrementa].setIdUf(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getIdUf());
					admFeriadoVO[incrementa].setIndMovel(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getIndMovel());
					admFeriadoVO[incrementa].setNmUF(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getNmUF());
					admFeriadoVO[incrementa].setInRelatorio(formGerenciamento.getAdmCalendarioContainerVO().getAdmFeriadoVOArray(i).getInRelatorio());
					admCalendarioContainerVO.insertNewAdmFeriadoVO(incrementa);
					admCalendarioContainerVO.setAdmFeriadoVOArray(incrementa, admFeriadoVO[incrementa]);
					contador++;
					incrementa++;
				}
			}
			if (incrementa != 0) {
				formGerenciamento.setAdmCalendarioContainerVO(controlCalendario.salvarPonte(admCalendarioContainerVO, ConstantesCRM.getCurrentUser(request.getSession())));
				formGerenciamento.setIndicativoExibirMoveis(ConstantesCRM.SNO);
				formGerenciamento.setIndicativoExibirBotoesPonte(ConstantesCRM.SNO);
				formGerenciamento.setIndicativoExibirPontes(ConstantesCRM.SNO);
				formGerenciamento.setIndicativoExibeCopia(ConstantesCRM.SYES);
				// formGerenciamento.setMsgStatus("Feriados ponte gravados com sucesso");
				formGerenciamento.setMsgError("Feriados ponte gravados com sucesso");

			} else {
				formGerenciamento.setIndicativoExibirPontes(ConstantesCRM.SYES);
				formGerenciamento.setIndicativoExibirMoveis(ConstantesCRM.SNO);
				formGerenciamento.setIndicativoExibeCopia(ConstantesCRM.SNO);
				formGerenciamento.setIndicativoExibirBotoesPonte(ConstantesCRM.SNO);
				// formGerenciamento.setMsgStatus("Nenhum feriado ponte foi selecionado para gravar");
				formGerenciamento.setMsgError("Nenhum feriado ponte foi selecionado para gravar");
			}

		} catch (TuxedoWarningException twe) {
			log.warn("TuxedoWarningException - GerenciarCalendarioController:salvarPonte(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + twe.getMessage() + "]", twe);
			formGerenciamento.setMsgError(twe.getMessage().substring(twe.getMessage().indexOf(']') + 1));
			formGerenciamento.setMsgStatus("Falha na tentativa de salvar ponte.");
			// formGerenciamento.setIndicativoExibirMoveis(ConstantesCRM.SYES);
			// formGerenciamento.setIndicativoExibirBotoesPonte(ConstantesCRM.SYES);
			// formGerenciamento.setIndicativoExibirPontes(ConstantesCRM.SYES);
			// formGerenciamento.setIndicativoExibeCopia(ConstantesCRM.SNO);

		} catch (Exception e) {
			log.error("Exception - GerenciarCalendarioController:salvarPonte(" + ConstantesCRM.getCurrentUser(request.getSession()) + ") - [" + e.getMessage() + "]", e);
			// Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class FormGerenciamento extends ActionForm {

		private static final long serialVersionUID = -4620906422795796882L;

		private String msgError = ConstantesCRM.SVAZIO;
		private String contadorPonte;
		private String indicativoExibeCopia;
		private String contador;
		private String indicativoExibirBotoesPonte;
		private String indicativoExibirPontes;
		private String indicativoExibirMoveis;
		private AdmFeriadoVO[] listaAdmFeriadoVO;
		private AdmCalendarioContainerVO admCalendarioContainerVO;
		private String msgStatus;
		private String anoCopia;
		private String anoBase;

		public FormGerenciamento() {
			listaAdmFeriadoVO = new AdmFeriadoVO[0];
		}

		public void setAnoBase(String anoBase) {
			this.anoBase = anoBase;
		}

		public String getAnoBase() {
			return this.anoBase;
		}

		public void setAnoCopia(String anoCopia) {
			this.anoCopia = anoCopia;
		}

		public String getAnoCopia() {
			return this.anoCopia;
		}

		public void setMsgStatus(String msgStatus) {
			this.msgStatus = msgStatus;
		}

		public String getMsgStatus() {
			return this.msgStatus;
		}

		public void setAdmCalendarioContainerVO(AdmCalendarioContainerVO admCalendarioContainerVO) {
			this.admCalendarioContainerVO = admCalendarioContainerVO;
		}

		public AdmCalendarioContainerVO getAdmCalendarioContainerVO() {
			return this.admCalendarioContainerVO;
		}

		public void setListaAdmFeriadoVO(AdmFeriadoVO[] listaAdmFeriadoVO) {
			this.listaAdmFeriadoVO = listaAdmFeriadoVO;
		}

		public AdmFeriadoVO[] getListaAdmFeriadoVO() {
			return this.listaAdmFeriadoVO;
		}

		public void setIndicativoExibirMoveis(String indicativoExibirMoveis) {
			this.indicativoExibirMoveis = indicativoExibirMoveis;
		}

		public String getIndicativoExibirMoveis() {
			return this.indicativoExibirMoveis;
		}

		public void setIndicativoExibirPontes(String indicativoExibirPontes) {
			this.indicativoExibirPontes = indicativoExibirPontes;
		}

		public String getIndicativoExibirPontes() {
			return this.indicativoExibirPontes;
		}

		public void setIndicativoExibirBotoesPonte(String indicativoExibirBotoesPonte) {
			this.indicativoExibirBotoesPonte = indicativoExibirBotoesPonte;
		}

		public String getIndicativoExibirBotoesPonte() {
			return this.indicativoExibirBotoesPonte;
		}

		public void setContador(String contador) {
			this.contador = contador;
		}

		public String getContador() {
			return this.contador;
		}

		public void setIndicativoExibeCopia(String indicativoExibeCopia) {
			this.indicativoExibeCopia = indicativoExibeCopia;
		}

		public String getIndicativoExibeCopia() {
			return this.indicativoExibeCopia;
		}

		public void setContadorPonte(String contadorPonte) {
			this.contadorPonte = contadorPonte;
		}

		public String getContadorPonte() {
			return this.contadorPonte;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public FormGerenciamento getFormGerenciamento() {
		return this.formGerenciamento;
	}
}