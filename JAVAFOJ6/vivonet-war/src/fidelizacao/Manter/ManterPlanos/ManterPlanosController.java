package fidelizacao.Manter.ManterPlanos;

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
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterPlanosController extends AbstractAction {

	private static final long serialVersionUID = -1608706470828761492L;

	@EJB
	public Fidelizacao fidelizacao;

	private transient Logger log = new Logger("Fidelizacao");
	protected global.Global globalApp = new global.Global();
	private PlanosForm planosForm;

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

	public PlanosForm getPlanosForm() {
		if (this.planosForm == null) {
			this.planosForm = new PlanosForm();
		}
		return this.planosForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterPlanos.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		getPlanosForm().setIdRegional(ConstantesCRM.SVAZIO);
		getPlanosForm().setIdTpCliente(ConstantesCRM.SVAZIO);
		getPlanosForm().setIdTpLinha(ConstantesCRM.SVAZIO);

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.addNewFiltro().addNewCombos().addNmSelect("REGIONAL");
		xml.getFiltro().getCombos().addNmSelect("TPCLIENTE");
		xml.getFiltro().getCombos().addNmSelect("TPLINHA");

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);
		getPlanosForm().setFidelizacaoVO(listas);
		for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
			if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
			}
		}
		listas.addNewTabelas().addNewPlanos();
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ManterPlanos.jsp"
	 */
	protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlanosForm form = (PlanosForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.addNewFiltro().addNewCombos().addNmSelect("REGIONAL");
		xml.getFiltro().getCombos().addNmSelect("TPCLIENTE");
		xml.getFiltro().getCombos().addNmSelect("TPLINHA");

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);
		getPlanosForm().setFidelizacaoVO(listas);
		for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
			if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
			}
		}

		xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("MANPLANOS");
		xml.setTpProcesso("PSQ");

		Filtro filtro = xml.addNewFiltro();
		filtro.setIdRegional(form.getIdRegional());
		filtro.setTpCliente(form.getIdTpCliente());
		filtro.setTpLinha(form.getIdTpLinha());

		FidelizacaoVO tabela = fidelizacao.getListas(user, xml);
		if (tabela.getTabelas() == null) {
			tabela.addNewTabelas().addNewPlanos();
		} else {
			if (tabela.getTabelas().getPlanos() == null) {
				tabela.getTabelas().addNewPlanos();
			}
		}
		getPlanosForm().setIdRegional(form.getIdRegional());
		getPlanosForm().setIdTpCliente(form.getIdTpCliente());
		getPlanosForm().setIdTpLinha(form.getIdTpLinha());
		getPlanosForm().setFidelizacaoVO(tabela);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="inclusaoAlteracao.jsp"
	 */
	protected ActionForward incluirBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlanosForm form = (PlanosForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.addNewFiltro().addNewCombos().addNmSelect("REGIONAL");
		xml.getFiltro().getCombos().addNmSelect("TPCLIENTE");
		xml.getFiltro().getCombos().addNmSelect("TPLINHA");
		xml.getFiltro().getCombos().addNmSelect("TPSERVICO");

		getPlanosForm().setIdPlano(ConstantesCRM.SVAZIO);
		getPlanosForm().setIdTpLinha(ConstantesCRM.SVAZIO);
		getPlanosForm().setDsPlano(ConstantesCRM.SVAZIO);
		getPlanosForm().setIdTpServico(ConstantesCRM.SVAZIO);
		getPlanosForm().setCdServico(ConstantesCRM.SVAZIO);

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);
		getPlanosForm().setFidelizacaoVO(listas);
		for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
			if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPSERVICO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaServicos(listas.getListasVO().getListaArray(i).getDisponivel());
			}
		}
		getPlanosForm().setListaSelRegional(Selecionado.Factory.newInstance());
		getPlanosForm().setListaSelClientes(Selecionado.Factory.newInstance());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="inclusaoAlteracao.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward incluir(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlanosForm form = (PlanosForm) formParam;
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());

			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.setNmProcesso("MANPLANOS");
			xml.setTpProcesso("CAD");

			Manter manter = xml.addNewManter();
			manter.setTpLinha(form.getIdTpLinha());
			manter.setDsPlano(form.getDsPlano());
			manter.setTpServico(form.getIdTpServico());
			manter.setCdServico(form.getCdServico());

			Lista lista = xml.addNewListasVO().addNewLista();
			lista.setNmSelect("REGIONAL");
			Selecionado selecionado = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelRegional().length; i++) {
				selecionado.addNewIt().setId(form.getIdSelRegional()[i]);
			}

			lista = xml.getListasVO().addNewLista();
			lista.setNmSelect("TPCLIENTE");
			selecionado = lista.addNewSelecionado();
			for (int i = 0; i < form.getIdSelTpCliente().length; i++) {
				selecionado.addNewIt().setId(form.getIdSelTpCliente()[i]);
			}

			FidelizacaoVO conf = fidelizacao.setParam(user, xml);
			if (ConstantesCRM.SONE.equals(conf.getCodError())) {
				request.setAttribute("msgError", conf.getMsgError());
			} else {
				request.setAttribute("status", ConstantesCRM.SOK);
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
	 * @jpf:forward name="success" path="inclusaoAlteracao.jsp"
	 */
	protected ActionForward alterarBegin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlanosForm form = (PlanosForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("MANPLANOS");
		xml.setTpProcesso("CON");
		xml.addNewManter().setIdCadastrado(form.getIdPlano());

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);
		getPlanosForm().setFidelizacaoVO(listas);

		if (listas.getManter() == null) {
			listas.addNewManter();
		}

		getPlanosForm().setIdPlano(form.getIdPlano());
		getPlanosForm().setIdTpLinha(listas.getManter().getTpLinha());
		getPlanosForm().setDsPlano(listas.getManter().getDsPlano());
		getPlanosForm().setIdTpServico(listas.getManter().getTpServico());
		getPlanosForm().setCdServico(listas.getManter().getCdServico());

		if (listas.getListasVO() == null) {
			listas.addNewListasVO();
		} else if (listas.getListasVO().getListaArray() == null) {
			listas.getListasVO().addNewLista();
		}
		for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
			if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());
				getPlanosForm().setListaSelRegional(listas.getListasVO().getListaArray(i).getSelecionado());
			} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());
				getPlanosForm().setListaSelClientes(listas.getListasVO().getListaArray(i).getSelecionado());
			} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());
			} else if ("TPSERVICO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
				getPlanosForm().setListaServicos(listas.getListasVO().getListaArray(i).getDisponivel());
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
		PlanosForm form = (PlanosForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("MANPLANOS");
		xml.setTpProcesso("ALT");

		Manter manter = xml.addNewManter();

		manter.setIdCadastrado(form.getIdPlano());
		manter.setTpLinha(form.getIdTpLinha());
		manter.setDsPlano(form.getDsPlano());
		manter.setTpServico(form.getIdTpServico());
		manter.setCdServico(form.getCdServico());

		Lista lista = xml.addNewListasVO().addNewLista();
		lista.setNmSelect("REGIONAL");
		Selecionado selecionado = lista.addNewSelecionado();
		for (int i = 0; i < form.getIdSelRegional().length; i++) {
			selecionado.addNewIt().setId(form.getIdSelRegional()[i]);
		}

		lista = xml.getListasVO().addNewLista();
		lista.setNmSelect("TPCLIENTE");
		selecionado = lista.addNewSelecionado();
		for (int i = 0; i < form.getIdSelTpCliente().length; i++) {
			selecionado.addNewIt().setId(form.getIdSelTpCliente()[i]);
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
	 * @jpf:forward name="success" path="pesquisar.do"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward excluir(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		PlanosForm form = (PlanosForm) formParam;
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());

			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.setNmProcesso("MANPLANOS");
			xml.setTpProcesso("EXC");

			Manter manter = xml.addNewManter();
			manter.setIdCadastrado(form.getIdPlano());

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

	protected boolean alwaysTrackPreviousAction() {
		return true;
	}

	public static class PlanosForm extends ActionForm {
		private FidelizacaoVO fidelizacaoVO = FidelizacaoVO.Factory.newInstance();
		private Disponivel listaRegional;
		private Disponivel listaClientes;
		private Disponivel listaLinhas;
		private Disponivel listaServicos;

		private String idPlano = ConstantesCRM.SVAZIO;
		private String idRegional = ConstantesCRM.SVAZIO;
		private String idTpLinha = ConstantesCRM.SVAZIO;
		private String idTpCliente = ConstantesCRM.SVAZIO;
		private String idTpServico = ConstantesCRM.SVAZIO;

		private String dsPlano = ConstantesCRM.SVAZIO;
		private String cdServico = ConstantesCRM.SVAZIO;

		private Selecionado listaSelRegional;
		private Selecionado listaSelClientes;

		private String[] idSelRegional = new String[0];
		private String[] idSelTpCliente = new String[0];

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

		public void setListaServicos(Disponivel listaServicos) {
			this.listaServicos = listaServicos;
		}

		public Disponivel getListaServicos() {
			return this.listaServicos;
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

		public void setIdRegional(String idRegional) {
			this.idRegional = idRegional;
		}

		public String getIdRegional() {
			return this.idRegional;
		}

		public void setIdPlano(String idPlano) {
			this.idPlano = idPlano;
		}

		public String getIdPlano() {
			return this.idPlano;
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

		public void setIdTpServico(String idTpServico) {
			this.idTpServico = idTpServico;
		}

		public String getIdTpServico() {
			return this.idTpServico;
		}

		public void setDsPlano(String dsPlano) {
			this.dsPlano = dsPlano;
		}

		public String getDsPlano() {
			return this.dsPlano;
		}

		public void setCdServico(String cdServico) {
			this.cdServico = cdServico;
		}

		public String getCdServico() {
			return this.cdServico;
		}
	}
}
