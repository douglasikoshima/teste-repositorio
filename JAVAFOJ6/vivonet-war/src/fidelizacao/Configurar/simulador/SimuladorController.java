package fidelizacao.Configurar.simulador;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.Fidelizacao;
import br.com.vivo.fo.ctrls.fidelizacao.OfertaRealizada.OfertaRealizadaFacade;
import br.com.vivo.fo.ctrls.fidelizacao.realizarRetencao.RalizarRetencaoFacade;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralDescricaoVODocument.FidelizacaoListaGeralDescricaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Selecionado;
import br.com.vivo.fo.fidelizacao.vo.ItDocument.It;

import com.indracompany.actions.AbstractAction;

public class SimuladorController extends AbstractAction {

	private static final long serialVersionUID = 2866393724697836535L;

	protected global.Global globalApp = new global.Global();

	@EJB
	private OfertaRealizadaFacade ofertaRealizada;

	@EJB
	public RalizarRetencaoFacade realizarRetencao;

	@EJB
	public Fidelizacao fidelizacao;

	private SimuladorForm simuladorForm = new SimuladorForm();

	private Disponivel dVazio = Disponivel.Factory.newInstance();
	private Selecionado sVazio = Selecionado.Factory.newInstance();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("getIntencao".equals(mapping.getParameter())) {
			return getIntencao(mapping, form, request, response);
		} else if ("getDestinos".equals(mapping.getParameter())) {
			return getDestinos(mapping, form, request, response);
		} else if ("getOfertas".equals(mapping.getParameter())) {
			return getOfertas(mapping, form, request, response);
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
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());

			FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
			xml.setNmProcesso("CFGMTZSCRIPTS");
			xml.setTpProcesso("GETNEWSCRPT");

			getSimuladorForm().setIdSelRegional(ConstantesCRM.SVAZIO);
			getSimuladorForm().setIdSelTpCliente(ConstantesCRM.SVAZIO);
			getSimuladorForm().setIdSelTpLinha(ConstantesCRM.SVAZIO);
			getSimuladorForm().setIdSelGrupos(ConstantesCRM.SVAZIO);
			getSimuladorForm().setIdSelSegmentacao(ConstantesCRM.SVAZIO);
			getSimuladorForm().setIdSelIntencao(ConstantesCRM.SVAZIO);
			getSimuladorForm().setIdSelDestinos(ConstantesCRM.SVAZIO);

			FidelizacaoVO.Filtro.Combos combos = xml.addNewFiltro().addNewCombos();
			combos.addNmSelect("REGIONAL");
			combos.addNmSelect("TPCLIENTE");
			combos.addNmSelect("TPLINHA");
			combos.addNmSelect("GRUPOS");
			combos.addNmSelect("SEGMENTACAO");

			FidelizacaoVO listas = fidelizacao.getListas(user, xml);

			for (int i = 0; i < listas.getListasVO().getListaArray().length; i++) {
				if ("REGIONAL".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getSimuladorForm().setListaRegional(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("TPCLIENTE".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getSimuladorForm().setListaClientes(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("TPLINHA".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getSimuladorForm().setListaLinhas(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("GRUPOS".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getSimuladorForm().setListaGrupos(listas.getListasVO().getListaArray(i).getDisponivel());

				} else if ("SEGMENTACAO".equals(listas.getListasVO().getListaArray(i).getNmSelect())) {
					getSimuladorForm().setListaSegmentacao(listas.getListasVO().getListaArray(i).getDisponivel());
				}
			}

			getSimuladorForm().setListaIntencao(dVazio);
			getSimuladorForm().setListaDestinos(dVazio);
			getSimuladorForm().setListaOfertas(dVazio);

			getSimuladorForm().setListaSelIntencao(sVazio);
			getSimuladorForm().setListaSelDestinos(sVazio);
			getSimuladorForm().setListaSelOfertas(sVazio);
		} catch (Exception e) {
			getSimuladorForm().setListaRegional(dVazio);
			getSimuladorForm().setListaClientes(dVazio);
			getSimuladorForm().setListaLinhas(dVazio);
			getSimuladorForm().setListaGrupos(dVazio);
			getSimuladorForm().setListaSegmentacao(dVazio);
			request.setAttribute("msgError", "begin::" + e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		request.setAttribute("step", "int");
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward getIntencao(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		SimuladorForm form = (SimuladorForm) formParam;
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());

			String idUF = form.getIdSelRegional();
			String sgTP = form.getIdSelTpCliente();
			String idTL = form.getIdSelTpLinha();
			String idGR = form.getIdSelGrupos();
			String idSG = form.getIdSelSegmentacao();

			getSimuladorForm().setIdSelRegional(idUF);
			getSimuladorForm().setIdSelTpCliente(sgTP);
			getSimuladorForm().setIdSelTpLinha(idTL);
			getSimuladorForm().setIdSelGrupos(idGR);
			getSimuladorForm().setIdSelSegmentacao(idSG);

			FidelizacaoListaGeralVO lista = realizarRetencao.getIntencaoCancelamento(user, idUF, sgTP, idSG, idGR, idTL);
			if (lista.getItemListaVOArray().length > 0) {
				Disponivel disp = FidelizacaoVO.ListasVO.Lista.Disponivel.Factory.newInstance();
				for (int i = 0; i < lista.getItemListaVOArray().length; i++) {
					It it = disp.addNewIt();
					it.setId(lista.getItemListaVOArray(i).getId());
					it.setDs(lista.getItemListaVOArray(i).getDescricao());
				}
				getSimuladorForm().setListaIntencao(disp);
				request.setAttribute("step", request.getParameter("step"));

			} else {
				request.setAttribute("step", "int");
				request.setAttribute("msgError", "Para o conjunto de variáveis não há Intenções Cadastradas.");
			}

		} catch (Exception e) {
			request.setAttribute("step", "int");
			getSimuladorForm().setListaIntencao(dVazio);
			request.setAttribute("msgError", "getIntencao::" + e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward getDestinos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		SimuladorForm form = (SimuladorForm) formParam;

		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			String idUF = form.getIdSelRegional();
			String sgTP = form.getIdSelTpCliente();
			String idTL = form.getIdSelTpLinha();
			String idGR = form.getIdSelGrupos();
			String idSG = form.getIdSelSegmentacao();
			String idIN = form.getIdSelIntencao();

			getSimuladorForm().setIdSelRegional(idUF);
			getSimuladorForm().setIdSelTpCliente(sgTP);
			getSimuladorForm().setIdSelTpLinha(idTL);
			getSimuladorForm().setIdSelGrupos(idGR);
			getSimuladorForm().setIdSelSegmentacao(idSG);
			getSimuladorForm().setIdSelIntencao(idIN);

			FidelizacaoListaGeralVO lista = realizarRetencao.getDestinosPrevistos(user, idUF, idIN, sgTP, idSG, idGR, idTL);
			if (lista.getItemListaVOArray().length > 0) {
				Disponivel disp = FidelizacaoVO.ListasVO.Lista.Disponivel.Factory.newInstance();
				for (int i = 0; i < lista.getItemListaVOArray().length; i++) {
					It it = disp.addNewIt();
					it.setId(lista.getItemListaVOArray(i).getId());
					it.setDs(lista.getItemListaVOArray(i).getDescricao());
				}
				request.setAttribute("step", request.getParameter("step"));
				getSimuladorForm().setListaDestinos(disp);

			} else {
				request.setAttribute("step", "dest");
				request.setAttribute("msgError", "Para o conjunto de variáveis não há Destinos Cadastrados.");
			}

		} catch (Exception e) {
			request.setAttribute("step", "dest");
			getSimuladorForm().setListaDestinos(dVazio);
			request.setAttribute("msgError", "getDestinos::" + e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward getOfertas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		SimuladorForm form = (SimuladorForm) formParam;

		try {

			String user = ConstantesCRM.getCurrentUser(request.getSession());

			String idUF = form.getIdSelRegional();
			String sgTP = form.getIdSelTpCliente();
			String idTL = form.getIdSelTpLinha();
			String idGR = form.getIdSelGrupos();
			String idSG = form.getIdSelSegmentacao();
			String idIN = form.getIdSelIntencao();
			String idDT = form.getIdSelDestinos();

			getSimuladorForm().setIdSelRegional(idUF);
			getSimuladorForm().setIdSelTpCliente(sgTP);
			getSimuladorForm().setIdSelTpLinha(idTL);
			getSimuladorForm().setIdSelGrupos(idGR);
			getSimuladorForm().setIdSelSegmentacao(idSG);
			getSimuladorForm().setIdSelIntencao(idIN);
			getSimuladorForm().setIdSelDestinos(idDT);

			String[] dados = { idUF, idSG, sgTP, idIN, idDT, idGR };

			FidelizacaoListaGeralDescricaoVO lista = ofertaRealizada.getOfertasDisponiveis(user, dados, idTL);

			if (lista.getItemListaDescricaoVOArray().length > 0) {
				Disponivel disp = FidelizacaoVO.ListasVO.Lista.Disponivel.Factory.newInstance();
				for (int i = 0; i < lista.getItemListaDescricaoVOArray().length; i++) {
					It it = disp.addNewIt();
					it.setId(lista.getItemListaDescricaoVOArray(i).getId());
					it.setDs(lista.getItemListaDescricaoVOArray(i).getDescricao1());
				}
				request.setAttribute("step", request.getParameter("step"));
				getSimuladorForm().setListaOfertas(disp);

			} else {
				request.setAttribute("step", "oferta");
				request.setAttribute("msgError", "Para o conjunto de variáveis não há Ofertas Cadastradas.");
			}

		} catch (Exception e) {
			request.setAttribute("step", "oferta");
			getSimuladorForm().setListaOfertas(dVazio);
			request.setAttribute("msgError", "begin::" + e.getMessage());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="simuladorDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	public SimuladorForm getSimuladorForm() {
		return simuladorForm;
	}

	public static class SimuladorForm extends ActionForm {

		private static final long serialVersionUID = 1805538196202179573L;

		private FidelizacaoVO fidelizacaoVO = FidelizacaoVO.Factory.newInstance();

		private Disponivel listaRegional;
		private Disponivel listaClientes;
		private Disponivel listaLinhas;
		private Disponivel listaGrupos;
		private Disponivel listaSegmentacao;

		private Disponivel listaIntencao;
		private Disponivel listaDestinos;
		private Disponivel listaOfertas;

		private Selecionado listaSelIntencao;
		private Selecionado listaSelDestinos;
		private Selecionado listaSelOfertas;

		private String idSelRegional = ConstantesCRM.SVAZIO;
		private String idSelTpCliente = ConstantesCRM.SVAZIO;
		private String idSelTpLinha = ConstantesCRM.SVAZIO;
		private String idSelSegmentacao = ConstantesCRM.SVAZIO;
		private String idSelGrupos = ConstantesCRM.SVAZIO;

		private String idSelIntencao = ConstantesCRM.SVAZIO;
		private String idSelDestinos = ConstantesCRM.SVAZIO;
		private String[] idSelOfertas = new String[0];

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

		public void setIdSelIntencao(String idSelIntencao) {
			this.idSelIntencao = idSelIntencao;
		}

		public String getIdSelIntencao() {
			return this.idSelIntencao;
		}

		public void setIdSelRegional(String idSelRegional) {
			this.idSelRegional = idSelRegional;
		}

		public String getIdSelRegional() {
			return this.idSelRegional;
		}

		public void setIdSelTpCliente(String idSelTpCliente) {
			this.idSelTpCliente = idSelTpCliente;
		}

		public String getIdSelTpCliente() {
			return this.idSelTpCliente;
		}

		public void setIdSelTpLinha(String idSelTpLinha) {
			this.idSelTpLinha = idSelTpLinha;
		}

		public String getIdSelTpLinha() {
			return this.idSelTpLinha;
		}

		public void setIdSelDestinos(String idSelDestinos) {
			this.idSelDestinos = idSelDestinos;
		}

		public String getIdSelDestinos() {
			return this.idSelDestinos;
		}

		public void setIdSelSegmentacao(String idSelSegmentacao) {
			this.idSelSegmentacao = idSelSegmentacao;
		}

		public String getIdSelSegmentacao() {
			return this.idSelSegmentacao;
		}

		public void setIdSelGrupos(String idSelGrupos) {
			this.idSelGrupos = idSelGrupos;
		}

		public String getIdSelGrupos() {
			return this.idSelGrupos;
		}

		public void setIdSelOfertas(String[] idSelOfertas) {
			this.idSelOfertas = idSelOfertas;
		}

		public String[] getIdSelOfertas() {
			return this.idSelOfertas;
		}

		public void setListaSelIntencao(Selecionado listaSelIntencao) {
			this.listaSelIntencao = listaSelIntencao;
		}

		public Selecionado getListaSelIntencao() {
			return this.listaSelIntencao;
		}

		public void setListaSelDestinos(Selecionado listaSelDestinos) {
			this.listaSelDestinos = listaSelDestinos;
		}

		public Selecionado getListaSelDestinos() {
			return this.listaSelDestinos;
		}

		public void setListaSelOfertas(Selecionado listaSelOfertas) {
			this.listaSelOfertas = listaSelOfertas;
		}

		public Selecionado getListaSelOfertas() {
			return this.listaSelOfertas;
		}

	}
}
