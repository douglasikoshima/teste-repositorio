package fidelizacao.Configurar.ConfigurarMatrizScript;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.Fidelizacao;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Selecionado;

import com.indracompany.actions.AbstractAction;

public class ConfigurarMatrizScriptController extends AbstractAction {

	private static final long serialVersionUID = 208302227493797457L;

	@EJB
	public Fidelizacao fidelizacao;

	private ScriptForm scriptForm = new ScriptForm();
	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("alterarBegin".equals(mapping.getParameter())) {
			return alterarBegin(mapping, form, request, response);
		} else if ("alterar".equals(mapping.getParameter())) {
			return alterar(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * 
	 * @jpf:action
	 * @jpf:forward name="success" path="matrizScript.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		getScriptForm().setIdIntencao(ConstantesCRM.SVAZIO);
		getScriptForm().setIdScript(ConstantesCRM.SVAZIO);

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.addNewFiltro().addNewCombos().addNmSelect("INTENCAO");

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);
		getScriptForm().setFidelizacaoVO(listas);
		for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
			if ("INTENCAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getScriptForm().setListaIntencao(listas.getListasVO().getListaArray(i).getDisponivel());
			}
		}
		getScriptForm().setListaRegional(Disponivel.Factory.newInstance());
		getScriptForm().setListaSelRegional(Selecionado.Factory.newInstance());
		getScriptForm().setListaClientes(Disponivel.Factory.newInstance());
		getScriptForm().setListaSelClientes(Selecionado.Factory.newInstance());
		getScriptForm().setListaLinhas(Disponivel.Factory.newInstance());
		getScriptForm().setListaSelLinhas(Selecionado.Factory.newInstance());
		getScriptForm().setListaDestinos(Disponivel.Factory.newInstance());
		getScriptForm().setListaSelDestinos(Selecionado.Factory.newInstance());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="matrizScript.jsp"
	 */
	protected ActionForward alterarBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ScriptForm form = (ScriptForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("CFGSCRIPT");
		xml.setTpProcesso("CON");

		xml.addNewManter().setIdIntencao(form.getIdIntencao());

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);

		getScriptForm().setIdIntencao(xml.getManter().getIdIntencao());
		getScriptForm().setIdScript(xml.getManter().getIdCadastrado());

		getScriptForm().setFidelizacaoVO(listas);
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
			} else if ("DESTINO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getScriptForm().setListaDestinos(listas.getListasVO().getListaArray(i).getDisponivel());
				getScriptForm().setListaSelDestinos(listas.getListasVO().getListaArray(i).getSelecionado());
			} else if ("INTENCAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getScriptForm().setListaIntencao(listas.getListasVO().getListaArray(i).getDisponivel());
			}
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="alterarBegin.do"
	 */
	protected ActionForward alterar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ScriptForm form = (ScriptForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("CFGSCRIPT");
		xml.setTpProcesso("ALT");

		xml.addNewManter().setIdIntencao(form.getIdIntencao());

		Lista lista = xml.addNewListasVO().addNewLista();
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
		lista.setNmSelect("DESTINO");
		selecao = lista.addNewSelecionado();
		for (int i = 0; i < form.getIdSelDestinos().length; i++) {
			selecao.addNewIt().setId(form.getIdSelDestinos()[i]);
		}

		FidelizacaoVO conf = fidelizacao.setParam(user, xml);
		if (!"1".equals(conf.getCodError())) {
			request.setAttribute("msgError", "A Matriz será processada em alguns minutos.");
		}
		/*
		 * if("1".equals(conf.getCodError())){
		 * request.setAttribute("msgError",conf.getMsgError()); }
		 */
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public ScriptForm getScriptForm() {
		return scriptForm;
	}

	public static class ScriptForm extends ActionForm {

		private static final long serialVersionUID = 8744842763591916314L;

		private FidelizacaoVO fidelizacaoVO = FidelizacaoVO.Factory.newInstance();
		private Disponivel listaIntencao;
		private Disponivel listaRegional;
		private Disponivel listaClientes;
		private Disponivel listaLinhas;
		private Disponivel listaDestinos;

		private String idScript = ConstantesCRM.SVAZIO;
		private String idIntencao = ConstantesCRM.SVAZIO;

		private Selecionado listaSelRegional;
		private Selecionado listaSelClientes;
		private Selecionado listaSelLinhas;
		private Selecionado listaSelDestinos;

		private String[] idSelRegional = new String[0];
		private String[] idSelTpCliente = new String[0];
		private String[] idSelTpLinha = new String[0];
		private String[] idSelDestinos = new String[0];

		public FidelizacaoVO getFidelizacaoVO() {
			return this.fidelizacaoVO;
		}

		public void setFidelizacaoVO(FidelizacaoVO fidelizacaoVO) {
			this.fidelizacaoVO = fidelizacaoVO;
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

	}
}
