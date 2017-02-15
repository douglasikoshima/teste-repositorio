package fidelizacao.Configurar.ConfigurarMigracao;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.Fidelizacao;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.Filtro;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Selecionado;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.Manter;
import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ConfigurarMigracaoController extends AbstractAction {

	private static final long serialVersionUID = 4003893915351996623L;

	@EJB
	public Fidelizacao fidelizacao;

	protected global.Global globalApp = new global.Global();
	private MigracaoForm migracaoForm = new MigracaoForm();

	public MigracaoForm getMigracaoForm() {
		return this.migracaoForm;
	}

	/**
	 * Formata uma string para decimal de acordo com a localidade atual
	 *
	 * @param valor
	 *            String
	 * @return String
	 */
	public static String formatarDecimal(String valor) {
		// se a variável estiver nula, retorna vazio
		if (valor == null) {
			valor = ConstantesCRM.SZERO;
		}
		DecimalFormat dFormat = new DecimalFormat("###,##0.00;(###,##0.00)", new DecimalFormatSymbols(new Locale("pt", "BR")));
		dFormat.isDecimalSeparatorAlwaysShown();
		return dFormat.format(new Double(valor));
	}

	private String toStringNumber(String valor) {
		if (valor == null) {
			valor = ConstantesCRM.SZERO;
		}
		return valor.replaceAll("\\.", "").replace(',', '.');
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("incluirBegin".equals(mapping.getParameter())) {
			return incluirBegin(mapping, form, request, response);
		} else if ("incluir".equals(mapping.getParameter())) {
			return incluir(mapping, form, request, response);
		} else if ("alterarBegin".equals(mapping.getParameter())) {
			return alterarBegin(mapping, form, request, response);
		} else if ("alterar".equals(mapping.getParameter())) {
			return alterar(mapping, form, request, response);
		} else if ("excluir".equals(mapping.getParameter())) {
			return excluir(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="ConfigurarMigracao.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		getMigracaoForm().setIdMigracao(ConstantesCRM.SVAZIO);
		getMigracaoForm().setIdRegional(ConstantesCRM.SVAZIO);
		getMigracaoForm().setIdTpCliente(ConstantesCRM.SVAZIO);
		getMigracaoForm().setIdTpLinha(ConstantesCRM.SVAZIO);

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.addNewFiltro().addNewCombos().addNmSelect("REGIONAL");
		xml.getFiltro().getCombos().addNmSelect("TPCLIENTE");
		xml.getFiltro().getCombos().addNmSelect("TPLINHA");

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);
		getMigracaoForm().setFidelizacaoVO(listas);

		for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
			if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getMigracaoForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getMigracaoForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getMigracaoForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
			}
		}
		listas.addNewTabelas().addNewMigracao();
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ConfigurarMigracao.jsp"
	 */
	protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MigracaoForm form = (MigracaoForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.addNewFiltro().addNewCombos().addNmSelect("REGIONAL");
		xml.getFiltro().getCombos().addNmSelect("TPCLIENTE");
		xml.getFiltro().getCombos().addNmSelect("TPLINHA");

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);
		getMigracaoForm().setFidelizacaoVO(listas);

		for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
			if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getMigracaoForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getMigracaoForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getMigracaoForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
			}
		}

		xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("CFGMIGRA");
		xml.setTpProcesso("PSQ");

		Filtro filtro = xml.addNewFiltro();
		filtro.setIdRegional(form.getIdRegional());
		filtro.setTpCliente(form.getIdTpCliente());
		filtro.setTpLinha(form.getIdTpLinha());

		FidelizacaoVO tabela = fidelizacao.getListas(user, xml);
		if (tabela.getTabelas() == null) {
			tabela.addNewTabelas().addNewMigracao();
		} else {
			if (tabela.getTabelas().getMigracao() == null) {
				tabela.getTabelas().addNewMigracao();
			}
		}
		getMigracaoForm().setIdRegional(form.getIdRegional());
		getMigracaoForm().setIdTpCliente(form.getIdTpCliente());
		getMigracaoForm().setIdTpLinha(form.getIdTpLinha());
		getMigracaoForm().setFidelizacaoVO(tabela);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="inclusaoAlteracao.jsp"
	 */
	protected ActionForward incluirBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		getMigracaoForm().setIdMigracao(ConstantesCRM.SVAZIO);
		getMigracaoForm().setDsMigra(ConstantesCRM.SVAZIO);
		getMigracaoForm().setVlBonus("0,00");
		getMigracaoForm().setVdMigra(ConstantesCRM.SVAZIO);

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.addNewFiltro().addNewCombos().addNmSelect("REGIONAL");
		xml.getFiltro().getCombos().addNmSelect("TPCLIENTE");
		xml.getFiltro().getCombos().addNmSelect("TPLINHA");

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);
		getMigracaoForm().setFidelizacaoVO(listas);

		for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
			if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getMigracaoForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getMigracaoForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getMigracaoForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
			}
		}
		getMigracaoForm().setListaSelRegional(Selecionado.Factory.newInstance());
		getMigracaoForm().setListaSelClientes(Selecionado.Factory.newInstance());
		getMigracaoForm().setListaSelLinhas(Selecionado.Factory.newInstance());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="inclusaoAlteracao.jsp"
	 */
	protected ActionForward incluir(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MigracaoForm form = (MigracaoForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("CFGMIGRA");
		xml.setTpProcesso("CAD");

		Manter manter = xml.addNewManter();
		manter.setDsMigra(form.getDsMigra());
		manter.setVlBonus(toStringNumber(form.getVlBonus()));
		manter.setVdMigra(form.getVdMigra());

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

		FidelizacaoVO conf = fidelizacao.setParam(user, xml);
		if (ConstantesCRM.SONE.equals(conf.getCodError())) {
			request.setAttribute("msgError", conf.getMsgError());
		} else {
			request.setAttribute("status", ConstantesCRM.SOK);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="inclusaoAlteracao.jsp"
	 */
	protected ActionForward alterarBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MigracaoForm form = (MigracaoForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("CFGMIGRA");
		xml.setTpProcesso("CON");

		Manter manter = xml.addNewManter();
		manter.setIdCadastrado(form.getIdMigracao());

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);
		getMigracaoForm().setFidelizacaoVO(listas);

		getMigracaoForm().setIdMigracao(form.getIdMigracao());
		getMigracaoForm().setDsMigra(listas.getManter().getDsMigra());
		getMigracaoForm().setVlBonus(formatarDecimal(listas.getManter().getVlBonus()));
		getMigracaoForm().setVdMigra(listas.getManter().getVdMigra());

		for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
			if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getMigracaoForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
				getMigracaoForm().setListaSelRegional(listas.getListasVO().getListaArray(i).getSelecionado());
			} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getMigracaoForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());
				getMigracaoForm().setListaSelClientes(listas.getListasVO().getListaArray(i).getSelecionado());
			} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getMigracaoForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
				getMigracaoForm().setListaSelLinhas(listas.getListasVO().getListaArray(i).getSelecionado());
			}
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="inclusaoAlteracao.jsp"
	 */
	protected ActionForward alterar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MigracaoForm form = (MigracaoForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("CFGMIGRA");
		xml.setTpProcesso("ALT");

		Manter manter = xml.addNewManter();
		manter.setIdCadastrado(form.getIdMigracao());
		manter.setDsMigra(form.getDsMigra());
		manter.setVlBonus(toStringNumber(form.getVlBonus()));
		manter.setVdMigra(form.getVdMigra());

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

		FidelizacaoVO conf = fidelizacao.setParam(user, xml);
		if ("1".equals(conf.getCodError())) {
			request.setAttribute("msgError", conf.getMsgError());
		} else {
			request.setAttribute("status", ConstantesCRM.SOK);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisar.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward excluir(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		MigracaoForm form = (MigracaoForm) formParam;
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());

			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.setNmProcesso("CFGMIGRA");
			xml.setTpProcesso("EXC");

			Manter manter = xml.addNewManter();
			manter.setIdCadastrado(form.getIdMigracao());

			FidelizacaoVO conf = fidelizacao.setParam(user, xml);
			if (ConstantesCRM.SONE.equals(conf.getCodError())) {
				request.setAttribute("msgError", conf.getMsgError());
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

	public static class MigracaoForm extends ActionForm {

		private static final long serialVersionUID = 7767656048608616828L;

		private FidelizacaoVO fidelizacaoVO = FidelizacaoVO.Factory.newInstance();
		private Disponivel listaRegional;
		private Disponivel listaClientes;
		private Disponivel listaLinhas;

		private String idMigracao = ConstantesCRM.SVAZIO;
		private String idRegional = ConstantesCRM.SVAZIO;
		private String idTpCliente = ConstantesCRM.SVAZIO;
		private String idTpLinha = ConstantesCRM.SVAZIO;
		private String vlBonus = ConstantesCRM.SVAZIO;
		private String vdMigra = ConstantesCRM.SVAZIO;
		private String dsMigra = ConstantesCRM.SVAZIO;

		private Selecionado listaSelRegional;
		private Selecionado listaSelClientes;
		private Selecionado listaSelLinhas;

		private String[] idSelRegional = new String[0];
		private String[] idSelTpCliente = new String[0];
		private String[] idSelTpLinha = new String[0];

		public FidelizacaoVO getFidelizacaoVO() {
			return this.fidelizacaoVO;
		}

		public void setFidelizacaoVO(FidelizacaoVO fidelizacaoVO) {
			this.fidelizacaoVO = fidelizacaoVO;
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

		public void setIdRegional(String idRegional) {
			this.idRegional = idRegional;
		}

		public String getIdRegional() {
			return this.idRegional;
		}

		public void setIdMigracao(String idMigracao) {
			this.idMigracao = idMigracao;
		}

		public String getIdMigracao() {
			return this.idMigracao;
		}

		public void setIdTpCliente(String idTpCliente) {
			this.idTpCliente = idTpCliente;
		}

		public String getIdTpCliente() {
			return this.idTpCliente;
		}

		public void setIdTpLinha(String idTpLinha) {
			this.idTpLinha = idTpLinha;
		}

		public String getIdTpLinha() {
			return this.idTpLinha;
		}

		public void setDsMigra(String dsMigra) {
			this.dsMigra = dsMigra;
		}

		public String getDsMigra() {
			return this.dsMigra;
		}

		public void setVdMigra(String vdMigra) {
			this.vdMigra = vdMigra;
		}

		public String getVdMigra() {
			return this.vdMigra;
		}

		public void setVlBonus(String valorBonus) {
			this.vlBonus = valorBonus;
		}

		public String getVlBonus() {
			return this.vlBonus;
		}
	}
}
