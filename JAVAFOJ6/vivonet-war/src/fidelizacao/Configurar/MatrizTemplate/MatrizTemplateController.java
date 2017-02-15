package fidelizacao.Configurar.MatrizTemplate;

import java.io.BufferedOutputStream;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.xmlbeans.XmlObject;

import br.com.vivo.fo.cliente.vo.AjaxErrorHandlerVODocument.AjaxErrorHandlerVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.Fidelizacao;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Selecionado;
import com.indracompany.actions.AbstractAction;

public class MatrizTemplateController extends AbstractAction {

	private static final long serialVersionUID = 3988677091933435380L;

	protected global.Global globalApp = new global.Global();

	@EJB
	public Fidelizacao fidelizacao;

	private ScriptForm scriptForm = new ScriptForm();
	// private transient Logger log = new Logger("Fidelizacao");

	private Disponivel dVazio = Disponivel.Factory.newInstance();
	private Selecionado sVazio = Selecionado.Factory.newInstance();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("loadScript".equals(mapping.getParameter())) {
			return loadScript(mapping, form, request, response);
		} else if ("habilitarMatriz".equals(mapping.getParameter())) {
			return habilitarMatriz(mapping, form, request, response);
		} else if ("gravarMatriz".equals(mapping.getParameter())) {
			return gravarMatriz(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			getScriptForm().setIdScript(ConstantesCRM.SVAZIO);
			getScriptForm().setNmMatriz(ConstantesCRM.SVAZIO);
			getScriptForm().setInHabilitado(ConstantesCRM.SVAZIO);
			getScriptForm().setNmUsuario(ConstantesCRM.SVAZIO);
			getScriptForm().setDtAlteracao(ConstantesCRM.SVAZIO);

			limparCombosDisponiveis();
			limparCombosSelecionados();

			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.setNmProcesso("CFGMTZSCRIPTS");
			xml.setTpProcesso("GETSCRPT");

			FidelizacaoVO listas = fidelizacao.getListas(user, xml);

			getScriptForm().setFidelizacaoVO(listas);
			if (listas.getListasVO() != null && listas.getListasVO().sizeOfListaArray() > 0) {
				for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
					if ("LSTSCRIPT".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
						getScriptForm().setListaScript(listas.getListasVO().getListaArray(i).getDisponivel());
					}
				}
			} else {
				getScriptForm().setListaScript(dVazio);
			}

		} catch (Exception e) {
			request.setAttribute("msgError", "begin::" + e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="MatrizTemplateDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	public ScriptForm getScriptForm() {
		return scriptForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward loadScript(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		ScriptForm form = (ScriptForm) formParam;
		try {
			String idMatriz = form.getIdScript();

			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.setNmProcesso("CFGMTZSCRIPTS");

			if ("N".equals(idMatriz)) {
				xml.setTpProcesso("GETNEWSCRPT");
				processaNovaMatriz(user, xml, request);
				getScriptForm().setIdScript(idMatriz);
			} else {
				xml.setTpProcesso("SELSCRPT");
				xml.addNewManter().setIdCadastrado(idMatriz);
				processaMatrizExistente(user, xml, idMatriz, request);
			}
		} catch (Exception e) {
			limparCombosDisponiveis();
			limparCombosSelecionados();
			request.setAttribute("msgError", "loadScript::" + e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void processaNovaMatriz(String user, FidelizacaoVO xml, HttpServletRequest request) {
		try {
			FidelizacaoVO.Filtro.Combos combos = xml.addNewFiltro().addNewCombos();
			combos.addNmSelect("REGIONAL");
			combos.addNmSelect("TPCLIENTE");
			combos.addNmSelect("TPLINHA");
			combos.addNmSelect("GRUPOS");
			combos.addNmSelect("SEGMENTACAO");

			FidelizacaoVO listas = fidelizacao.getListas(user, xml);

			getScriptForm().setFidelizacaoVO(listas);
			getScriptForm().setInHabilitado(ConstantesCRM.SVAZIO);
			getScriptForm().setNmMatriz(ConstantesCRM.SVAZIO);
			getScriptForm().setNmUsuario(ConstantesCRM.SVAZIO);
			getScriptForm().setDtAlteracao(ConstantesCRM.SVAZIO);
			limparCombosSelecionados();

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("GRUPOS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaGrupos(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("SEGMENTACAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaSegmentacao(listas.getListasVO().getListaArray(i).getDisponivel());
				}
			}

			xml.getFiltro().unsetCombos();
			combos = xml.getFiltro().addNewCombos();
			combos.addNmSelect("INTENCAO");

			listas = fidelizacao.getListas(user, xml);
			if (listas.getListasVO() != null && listas.getListasVO().getListaArray().length > 0) {
				XmlObject xmlObject = listas.getListasVO().getListaArray(0).copy();
				if (xmlObject != null) {
					getScriptForm().getFidelizacaoVO().getListasVO().addNewLista().set(xmlObject);
				}
			}

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("INTENCAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaIntencao(listas.getListasVO().getListaArray(i).getDisponivel());
				}
			}

			xml.getFiltro().unsetCombos();
			combos = xml.getFiltro().addNewCombos();
			combos.addNmSelect("DESTINO");

			listas = fidelizacao.getListas(user, xml);
			if (listas.getListasVO() != null && listas.getListasVO().getListaArray().length > 0) {
				XmlObject xmlObject = listas.getListasVO().getListaArray(0).copy();
				if (xmlObject != null) {
					getScriptForm().getFidelizacaoVO().getListasVO().addNewLista().set(xmlObject);
				}
			}

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("DESTINO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaDestinos(listas.getListasVO().getListaArray(i).getDisponivel());
				}
			}

			xml.getFiltro().unsetCombos();
			combos = xml.getFiltro().addNewCombos();
			combos.addNmSelect("OFERTAS");

			listas = fidelizacao.getListas(user, xml);
			if (listas.getListasVO() != null && listas.getListasVO().getListaArray().length > 0) {
				XmlObject xmlObject = listas.getListasVO().getListaArray(0).copy();
				if (xmlObject != null) {
					getScriptForm().getFidelizacaoVO().getListasVO().addNewLista().set(xmlObject);
				}
			}

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("OFERTAS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaOfertas(listas.getListasVO().getListaArray(i).getDisponivel());
				}
			}
		} catch (Exception e) {
			request.setAttribute("msgError", "processaNovaMatriz::" + e.getMessage());
		}
	}

	private void processaMatrizExistente(String user, FidelizacaoVO xml, String idMatriz, HttpServletRequest request) {
		try {
			FidelizacaoVO.Filtro.Combos combos = xml.addNewFiltro().addNewCombos();
			combos.addNmSelect("REGIONAL");
			combos.addNmSelect("TPCLIENTE");
			combos.addNmSelect("TPLINHA");

			FidelizacaoVO listas = fidelizacao.getListas(user, xml);

			getScriptForm().setFidelizacaoVO(listas);
			getScriptForm().setIdScript(idMatriz);
			getScriptForm().setInHabilitado(listas.getManter().getInHabilitado());
			getScriptForm().setNmMatriz(listas.getManter().getNmCadastrado());
			getScriptForm().setNmUsuario(listas.getManter().getNmUsuario());
			getScriptForm().setDtAlteracao(listas.getManter().getDtAlteracao());

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
					getScriptForm().setListaSelRegional(listas.getListasVO().getListaArray(i).getSelecionado());

				} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());
					getScriptForm().setListaSelClientes(listas.getListasVO().getListaArray(i).getSelecionado());

				} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
					getScriptForm().setListaSelLinhas(listas.getListasVO().getListaArray(i).getSelecionado());
				}
			}

			xml.getFiltro().unsetCombos();
			combos = xml.getFiltro().addNewCombos();
			combos.addNmSelect("GRUPOS");

			listas = fidelizacao.getListas(user, xml);
			if (listas.getListasVO() != null && listas.getListasVO().getListaArray().length > 0) {
				XmlObject xmlObject = listas.getListasVO().getListaArray(0).copy();
				if (xmlObject != null) {
					getScriptForm().getFidelizacaoVO().getListasVO().addNewLista().set(xmlObject);
				}
			}

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("GRUPOS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaGrupos(listas.getListasVO().getListaArray(i).getDisponivel());
					getScriptForm().setListaSelGrupos(listas.getListasVO().getListaArray(i).getSelecionado());

				}
			}

			xml.getFiltro().unsetCombos();
			combos = xml.getFiltro().addNewCombos();
			combos.addNmSelect("SEGMENTACAO");

			listas = fidelizacao.getListas(user, xml);
			if (listas.getListasVO() != null && listas.getListasVO().getListaArray().length > 0) {
				XmlObject xmlObject = listas.getListasVO().getListaArray(0).copy();
				if (xmlObject != null) {
					getScriptForm().getFidelizacaoVO().getListasVO().addNewLista().set(xmlObject);
				}
			}

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("SEGMENTACAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaSegmentacao(listas.getListasVO().getListaArray(i).getDisponivel());
					getScriptForm().setListaSelSegmentacao(listas.getListasVO().getListaArray(i).getSelecionado());
				}
			}

			xml.getFiltro().unsetCombos();
			combos = xml.getFiltro().addNewCombos();
			combos.addNmSelect("INTENCAO");

			listas = fidelizacao.getListas(user, xml);
			if (listas.getListasVO() != null && listas.getListasVO().getListaArray().length > 0) {
				XmlObject xmlObject = listas.getListasVO().getListaArray(0).copy();
				if (xmlObject != null) {
					getScriptForm().getFidelizacaoVO().getListasVO().addNewLista().set(xmlObject);
				}
			}

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("INTENCAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaIntencao(listas.getListasVO().getListaArray(i).getDisponivel());
					getScriptForm().setListaSelIntencao(listas.getListasVO().getListaArray(i).getSelecionado());
				}
			}

			xml.getFiltro().unsetCombos();
			combos = xml.getFiltro().addNewCombos();
			combos.addNmSelect("DESTINO");

			listas = fidelizacao.getListas(user, xml);
			if (listas.getListasVO() != null && listas.getListasVO().getListaArray().length > 0) {
				XmlObject xmlObject = listas.getListasVO().getListaArray(0).copy();
				if (xmlObject != null) {
					getScriptForm().getFidelizacaoVO().getListasVO().addNewLista().set(xmlObject);
				}
			}

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("DESTINO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaDestinos(listas.getListasVO().getListaArray(i).getDisponivel());
					getScriptForm().setListaSelDestinos(listas.getListasVO().getListaArray(i).getSelecionado());

				}
			}

			xml.getFiltro().unsetCombos();
			combos = xml.getFiltro().addNewCombos();
			combos.addNmSelect("OFERTAS");

			listas = fidelizacao.getListas(user, xml);
			if (listas.getListasVO() != null && listas.getListasVO().getListaArray().length > 0) {
				XmlObject xmlObject = listas.getListasVO().getListaArray(0).copy();
				if (xmlObject != null) {
					getScriptForm().getFidelizacaoVO().getListasVO().addNewLista().set(xmlObject);
				}
			}

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("OFERTAS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getScriptForm().setListaOfertas(listas.getListasVO().getListaArray(i).getDisponivel());
					getScriptForm().setListaSelOfertas(listas.getListasVO().getListaArray(i).getSelecionado());
				}
			}
		} catch (Exception e) {
			limparCombos();
			request.setAttribute("msgError", "processaMatrizExistente::" + e.getMessage());
		}
	}

	private void limparCombos() {
		if (getScriptForm().getListaScript() == null) {
			getScriptForm().setListaScript(dVazio);
		}
		limparCombosDisponiveis();
		limparCombosSelecionados();
	}

	private void limparCombosDisponiveis() {
		getScriptForm().setListaRegional(dVazio);
		getScriptForm().setListaClientes(dVazio);
		getScriptForm().setListaLinhas(dVazio);
		getScriptForm().setListaGrupos(dVazio);
		getScriptForm().setListaSegmentacao(dVazio);
		getScriptForm().setListaIntencao(dVazio);
		getScriptForm().setListaDestinos(dVazio);
		getScriptForm().setListaOfertas(dVazio);
	}

	private void limparCombosSelecionados() {
		getScriptForm().setListaSelRegional(sVazio);
		getScriptForm().setListaSelClientes(sVazio);
		getScriptForm().setListaSelLinhas(sVazio);
		getScriptForm().setListaSelGrupos(sVazio);
		getScriptForm().setListaSelSegmentacao(sVazio);
		getScriptForm().setListaSelIntencao(sVazio);
		getScriptForm().setListaSelDestinos(sVazio);
		getScriptForm().setListaSelOfertas(sVazio);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward habilitarMatriz(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		StringBuffer xmlDados = new StringBuffer();
		ScriptForm form = (ScriptForm) formParam;
		try {
			String idMatriz = form.getIdScript();
			String inHabilitado = form.getInHabilitado();

			if ((idMatriz != null && !ConstantesCRM.SVAZIO.equals(idMatriz)) && (inHabilitado != null && !ConstantesCRM.SVAZIO.equals(inHabilitado))) {
				FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
				xml.setNmProcesso("CFGMTZSCRIPTS");
				xml.setTpProcesso("HABSELSCRPT");
				xml.addNewManter().setIdCadastrado(idMatriz);
				xml.getManter().setInHabilitado(inHabilitado);

				fidelizacao.setParam(user, xml);

				AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
				ajaxErrorHandlerVO.setExceptionMessage(ConstantesCRM.SVAZIO);
				ajaxErrorHandlerVO.setFriendlyMessage("Operação realizada com sucesso");
				ajaxErrorHandlerVO.setSeverity(0);
				xmlDados.append(ajaxErrorHandlerVO.xmlText());
			} else {
				throw new Exception("É necessário informar o Id da Matriz");
			}
		} catch (Exception e) {
			request.setAttribute("msgError", "habilitarMatriz::" + e.getMessage());
			AjaxErrorHandlerVO ajaxErrorHandlerVO = AjaxErrorHandlerVO.Factory.newInstance();
			ajaxErrorHandlerVO.setExceptionMessage(e.getMessage());
			ajaxErrorHandlerVO.setFriendlyMessage(e.getMessage());
			ajaxErrorHandlerVO.setSeverity(1);
			xmlDados = new StringBuffer();
			xmlDados.append(ajaxErrorHandlerVO.xmlText());
		} finally {
			try {
				response.setContentType(ConstantesCRM.SContentType);
				BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
				bufferedOutputStream.write(xmlDados.toString().getBytes(ConstantesCRM.SISO));
				bufferedOutputStream.flush();
				bufferedOutputStream.close();
			} catch (Exception e) {
			}
		}
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	protected ActionForward gravarMatriz(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ScriptForm form = (ScriptForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			String idMatriz = form.getIdScript();
			String nmMatriz = form.getNmMatriz();

			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.setNmProcesso("CFGMTZSCRIPTS");

			if ("N".equals(idMatriz)) {
				xml.addNewManter().setIdCadastrado(ConstantesCRM.SVAZIO);
				xml.setTpProcesso("INC");
			} else {
				xml.addNewManter().setIdCadastrado(idMatriz);
				xml.setTpProcesso("ALT");
			}
			xml.getManter().setInHabilitado(ConstantesCRM.SZERO);
			xml.getManter().setNmCadastrado(nmMatriz);

			FidelizacaoVO.ListasVO.Lista lista = xml.addNewListasVO().addNewLista();
			lista.setNmSelect("REGIONAL");
			Selecionado selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelRegional().length; i++) {
				selecao.addNewIt().setId(form.getIdSelRegional()[i]);
			}

			lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("TPCLIENTE");
			selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelTpCliente().length; i++) {
				selecao.addNewIt().setId(form.getIdSelTpCliente()[i]);
			}

			lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("TPLINHA");
			selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelTpLinha().length; i++) {
				selecao.addNewIt().setId(form.getIdSelTpLinha()[i]);
			}

			lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("GRUPOS");
			selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelGrupos().length; i++) {
				selecao.addNewIt().setId(form.getIdSelGrupos()[i]);
			}

			lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("SEGMENTACAO");
			selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelSegmentacao().length; i++) {
				selecao.addNewIt().setId(form.getIdSelSegmentacao()[i]);
			}

			lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("INTENCAO");
			selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelIntencao().length; i++) {
				selecao.addNewIt().setId(form.getIdSelIntencao()[i]);
			}

			lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("DESTINO");
			selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelDestinos().length; i++) {
				selecao.addNewIt().setId(form.getIdSelDestinos()[i]);
			}

			lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("OFERTAS");
			selecao = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelOfertas().length; i++) {
				selecao.addNewIt().setId(form.getIdSelOfertas()[i]);
			}
			// getScriptForm().setFidelizacaoVO(xml);

			FidelizacaoVO listas = fidelizacao.setParam(user, xml);

			if (listas != null && listas.getCodError() != null && ConstantesCRM.SONE.equals(listas.getCodError())) {
				request.setAttribute("msgError", listas.getMsgError());
			} else {
				request.setAttribute("msgError", "Operação realizada com sucesso");
			}
		} catch (Exception e) {
			request.setAttribute("msgError", "gravarMatriz::" + e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ScriptForm extends ActionForm {

		private static final long serialVersionUID = -8443842318300407634L;

		private FidelizacaoVO fidelizacaoVO = FidelizacaoVO.Factory.newInstance();

		private String idScript = ConstantesCRM.SVAZIO;
		private String idIntencao = ConstantesCRM.SVAZIO;
		private String nmMatriz = ConstantesCRM.SVAZIO;
		private String dtAlteracao = ConstantesCRM.SVAZIO;
		private String nmUsuario = ConstantesCRM.SVAZIO;
		private String inHabilitado = ConstantesCRM.SVAZIO;

		private Disponivel listaScript;
		private Disponivel listaIntencao;
		private Disponivel listaRegional;
		private Disponivel listaClientes;
		private Disponivel listaLinhas;
		private Disponivel listaDestinos;
		private Disponivel listaSegmentacao;
		private Disponivel listaGrupos;
		private Disponivel listaOfertas;

		private Selecionado listaSelIntencao;
		private Selecionado listaSelRegional;
		private Selecionado listaSelClientes;
		private Selecionado listaSelLinhas;
		private Selecionado listaSelDestinos;
		private Selecionado listaSelSegmentacao;
		private Selecionado listaSelGrupos;
		private Selecionado listaSelOfertas;

		private String[] idSelIntencao = new String[0];
		private String[] idSelRegional = new String[0];
		private String[] idSelTpCliente = new String[0];
		private String[] idSelTpLinha = new String[0];
		private String[] idSelDestinos = new String[0];
		private String[] idSelSegmentcao = new String[0];
		private String[] idSelGrupos = new String[0];
		private String[] idSelOfertas = new String[0];

		public FidelizacaoVO getFidelizacaoVO() {
			return this.fidelizacaoVO;
		}

		public void setFidelizacaoVO(FidelizacaoVO fidelizacaoVO) {
			this.fidelizacaoVO = fidelizacaoVO;
		}

		// Campos Listas Disponiveis

		public void setListaScript(Disponivel listaScript) {
			this.listaScript = listaScript;
		}

		public Disponivel getListaScript() {
			return this.listaScript;
		}

		public void setListaIntencao(Disponivel listaIntencao) {
			this.listaIntencao = listaIntencao;
		}

		public Disponivel getListaIntencao() {
			return this.listaIntencao;
		}

		public void setListaRegional(Disponivel listaRegional) {
			this.listaRegional = listaRegional;
		}

		public Disponivel getListaRegional() {
			return this.listaRegional;
		}

		public void setListaClientes(Disponivel listaClientes) {
			this.listaClientes = listaClientes;
		}

		public Disponivel getListaClientes() {
			return this.listaClientes;
		}

		public void setListaLinhas(Disponivel listaLinhas) {
			this.listaLinhas = listaLinhas;
		}

		public Disponivel getListaLinhas() {
			return this.listaLinhas;
		}

		public void setListaDestinos(Disponivel listaDestinos) {
			this.listaDestinos = listaDestinos;
		}

		public Disponivel getListaDestinos() {
			return this.listaDestinos;
		}

		public void setListaSegmentacao(Disponivel listaSegmentacao) {
			this.listaSegmentacao = listaSegmentacao;
		}

		public Disponivel getListaSegmentacao() {
			return this.listaSegmentacao;
		}

		public void setListaGrupos(Disponivel listaGrupos) {
			this.listaGrupos = listaGrupos;
		}

		public Disponivel getListaGrupos() {
			return this.listaGrupos;
		}

		public void setListaOfertas(Disponivel listaOfertas) {
			this.listaOfertas = listaOfertas;
		}

		public Disponivel getListaOfertas() {
			return this.listaOfertas;
		}

		// Campos Listas Selecionadas

		public void setListaSelIntencao(Selecionado listaSelIntencao) {
			this.listaSelIntencao = listaSelIntencao;
		}

		public Selecionado getListaSelIntencao() {
			return this.listaSelIntencao;
		}

		public void setListaSelRegional(Selecionado listaSelRegional) {
			this.listaSelRegional = listaSelRegional;
		}

		public Selecionado getListaSelRegional() {
			return this.listaSelRegional;
		}

		public void setListaSelClientes(Selecionado listaSelClientes) {
			this.listaSelClientes = listaSelClientes;
		}

		public Selecionado getListaSelClientes() {
			return this.listaSelClientes;
		}

		public void setListaSelLinhas(Selecionado listaSelLinhas) {
			this.listaSelLinhas = listaSelLinhas;
		}

		public Selecionado getListaSelLinhas() {
			return this.listaSelLinhas;
		}

		public void setListaSelDestinos(Selecionado listaSelDestinos) {
			this.listaSelDestinos = listaSelDestinos;
		}

		public Selecionado getListaSelDestinos() {
			return this.listaSelDestinos;
		}

		public void setListaSelSegmentacao(Selecionado listaSelSegmentacao) {
			this.listaSelSegmentacao = listaSelSegmentacao;
		}

		public Selecionado getListaSelSegmentacao() {
			return this.listaSelSegmentacao;
		}

		public void setListaSelGrupos(Selecionado listaSelGrupos) {
			this.listaSelGrupos = listaSelGrupos;
		}

		public Selecionado getListaSelGrupos() {
			return this.listaSelGrupos;
		}

		public void setListaSelOfertas(Selecionado listaSelOfertas) {
			this.listaSelOfertas = listaSelOfertas;
		}

		public Selecionado getListaSelOfertas() {
			return this.listaSelOfertas;
		}

		// Campos ID Listas

		public void setIdSelIntencao(String[] idSelIntencao) {
			this.idSelIntencao = idSelIntencao;
		}

		public String[] getIdSelIntencao() {
			return this.idSelIntencao;
		}

		public void setIdSelRegional(String[] idSelRegional) {
			this.idSelRegional = idSelRegional;
		}

		public String[] getIdSelRegional() {
			return this.idSelRegional;
		}

		public void setIdSelTpCliente(String[] idSelTpCliente) {
			this.idSelTpCliente = idSelTpCliente;
		}

		public String[] getIdSelTpCliente() {
			return this.idSelTpCliente;
		}

		public void setIdSelTpLinha(String[] idSelTpLinha) {
			this.idSelTpLinha = idSelTpLinha;
		}

		public String[] getIdSelTpLinha() {
			return this.idSelTpLinha;
		}

		public void setIdSelDestinos(String[] idSelDestinos) {
			this.idSelDestinos = idSelDestinos;
		}

		public String[] getIdSelDestinos() {
			return this.idSelDestinos;
		}

		public void setIdSelSegmentacao(String[] idSelSegmentcao) {
			this.idSelSegmentcao = idSelSegmentcao;
		}

		public String[] getIdSelSegmentacao() {
			return this.idSelSegmentcao;
		}

		public void setIdSelGrupos(String[] idSelGrupos) {
			this.idSelGrupos = idSelGrupos;
		}

		public String[] getIdSelGrupos() {
			return this.idSelGrupos;
		}

		public void setIdSelOfertas(String[] idSelOfertas) {
			this.idSelOfertas = idSelOfertas;
		}

		public String[] getIdSelOfertas() {
			return this.idSelOfertas;
		}

		// Campos Identificadores

		public void setIdScript(String idScript) {
			this.idScript = idScript;
		}

		public String getIdScript() {
			return this.idScript;
		}

		public void setIdIntencao(String idIntencao) {
			this.idIntencao = idIntencao;
		}

		public String getIdIntencao() {
			return this.idIntencao;
		}

		public void setNmMatriz(String nmMatriz) {
			this.nmMatriz = nmMatriz;
		}

		public String getNmMatriz() {
			return this.nmMatriz;
		}

		public void setInHabilitado(String inHabilitado) {
			this.inHabilitado = inHabilitado;
		}

		public String getInHabilitado() {
			return this.inHabilitado;
		}

		public void setNmUsuario(String nmUsuario) {
			this.nmUsuario = nmUsuario;
		}

		public String getNmUsuario() {
			return this.nmUsuario;
		}

		public void setDtAlteracao(String dtAlteracao) {
			this.dtAlteracao = dtAlteracao;
		}

		public String getDtAlteracao() {
			return this.dtAlteracao;
		}
	}
}
