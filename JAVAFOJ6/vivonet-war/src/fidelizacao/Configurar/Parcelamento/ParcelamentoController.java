package fidelizacao.Configurar.Parcelamento;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.configurar.ParcelamentoFacade;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

@SuppressWarnings({ "rawtypes", "unchecked", "unused" })
public class ParcelamentoController extends AbstractAction {

	private static final long serialVersionUID = -1304440850525947590L;

	private transient Logger log = new Logger("fidelizacao");

	@EJB
	private ParcelamentoFacade parcelamentoFacade;

	protected global.Global globalApp = new global.Global();

	private ParcelaForm parcelaForm = null;

	// Gambi para identificar quem é quem ao buscar o Parametro
	private String idPF = ConstantesCRM.SVAZIO;
	private String idPJ = ConstantesCRM.SVAZIO;

	public ParcelaForm getParcelaForm() {
		if (this.parcelaForm == null) {
			this.parcelaForm = new ParcelaForm();
		}
		return this.parcelaForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("carregarParcelas".equals(mapping.getParameter())) {
			return carregarParcelas(mapping, form, request, response);
		} else if ("gravar".equals(mapping.getParameter())) {
			return gravar(mapping, form, request, response);
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
			Collection optionsTP = new ArrayList();

			// Query para obter os Tipos de Pessoas
			StringBuffer queryTP = new StringBuffer("select idtipopessoa, dstipopessoa||' - '||sgtipopessoa dstipopessoa ");
			queryTP.append("from apoio.tipopessoa where idtipopessoa > 0 ");

			Pesquisar pesquisar = new Pesquisar();
			Resultset rsVO = pesquisar.executar(queryTP.toString());

			if (rsVO != null && rsVO.getLinhas() != null) {
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					String id = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
					String lb = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
					Option option = new Option(id, lb);
					optionsTP.add(option);

					// Como o ID depende do ambiente, e na apoio.parametro não
					// tem relação com o ID
					if (lb.indexOf("PJ") > 0) {
						idPJ = id;
					} else if (lb.indexOf("PF") > 0) {
						idPF = id;
					}
				}
				getParcelaForm().setOptionsTipoPessoa(optionsTP);
			} else {
				getParcelaForm().setOptionsTipoPessoa(new ArrayList());
			}

			getParcelaForm().setIdTipoPessoa(ConstantesCRM.SVAZIO);
			getParcelaForm().setOptionsDisp(new ArrayList());
			getParcelaForm().setOptionsSel(new ArrayList());
			getParcelaForm().setVlParcelaMinima("0,00");

			if (request.getAttribute("msgErro") != null && ((String) request.getAttribute("msgErro")).length() > 0) {
				request.setAttribute("msgErro", request.getAttribute("msgErro"));
			}

		} catch (Exception e) {
			request.setAttribute("msgErro", "Ocorreu um erro ao carregar os dados.");
			log.error("ParcelamentoController::begin::Exception", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward carregarParcelas(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ParcelaForm form = (ParcelaForm) formParam;
		try {
			String idTipoPessoa = request.getParameter("idTipoPessoa");

			if (idTipoPessoa != null && idTipoPessoa.length() > 0) {
				Collection optionsDisp = new ArrayList();
				Collection optionsSel = new ArrayList();

				// Query para obter as Parcelas Disponíveis
				StringBuffer queryDis = new StringBuffer("select idparcela, nrparcelas from retencao.parcela ");
				queryDis.append("where idparcela not in (select idparcela ");
				queryDis.append("from retencao.tipopessoaparcela ");
				queryDis.append("where idtipopessoa = ").append(idTipoPessoa).append(" ) ");
				queryDis.append("order by nrparcelas ");

				Pesquisar pesquisar = new Pesquisar();
				Resultset rsVO = pesquisar.executar(queryDis.toString());

				if (rsVO != null && rsVO.getLinhas() != null) {
					for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
						String id = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
						String lb = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
						Option option = new Option(id, lb);
						optionsDisp.add(option);
					}
					getParcelaForm().setOptionsDisp(optionsDisp);
				} else {
					getParcelaForm().setOptionsDisp(new ArrayList());
				}

				// Query para obter as Parcelas Selecionadas
				StringBuffer querySel = new StringBuffer("select tpp.idparcela, p.nrparcelas ");
				querySel.append("from retencao.tipopessoaparcela tpp, retencao.parcela p ");
				querySel.append("where tpp.idtipopessoa = ").append(idTipoPessoa).append(" ");
				querySel.append("and tpp.idparcela = p.idparcela ");
				querySel.append("order by p.nrparcelas ");

				rsVO = pesquisar.executar(querySel.toString());

				if (rsVO != null && rsVO.getLinhas() != null) {
					for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
						String id = rsVO.getLinhas().getLinhaArray(i).getValorArray(0);
						String lb = rsVO.getLinhas().getLinhaArray(i).getValorArray(1);
						Option option = new Option(id, lb);
						optionsSel.add(option);
					}
					getParcelaForm().setOptionsSel(optionsSel);
				} else {
					getParcelaForm().setOptionsSel(new ArrayList());
				}
				getParcelaForm().setIdTipoPessoa(form.getIdTipoPessoa());

				String valorMinimo = form.getVlParcelaMinima();
				if (idPF.equals(form.getIdTipoPessoa())) {
					valorMinimo = getValorMinimo("PF_VLR_PARC_MIN", request);
				} else {
					valorMinimo = getValorMinimo("PJ_VLR_PARC_MIN", request);
				}
				getParcelaForm().setVlParcelaMinima(valorMinimo);
			} else {
				getParcelaForm().setIdTipoPessoa(ConstantesCRM.SVAZIO);
				getParcelaForm().setOptionsDisp(new ArrayList());
				getParcelaForm().setOptionsSel(new ArrayList());
				getParcelaForm().setVlParcelaMinima("0,00");
			}
		} catch (Exception e) {
			log.error("ParcelamentoController::carregarParcelas::Exception", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="ParcelamentoDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="begin.do"
	 */
	protected ActionForward gravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ParcelaForm form = (ParcelaForm) formParam;
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			if (form.getIdTipoPessoa() != null && form.getIdTipoPessoa().length() > 0) {
				ArrayList arrParcelas = new ArrayList();

				for (int i = 0; i < form.getIdParcelasSel().length; i++) {
					arrParcelas.add(form.getIdParcelasSel()[i]);
				}
				String valor = form.getVlParcelaMinima().replaceAll("[.]", "");
				valor = valor.replaceAll(",", ".");

				String out = parcelamentoFacade.gravarParcelamento(user, form.getIdTipoPessoa(), valor, arrParcelas);

				request.setAttribute("msgErro", ConstantesCRM.SSucesso);
			}
		} catch (Exception e) {
			request.setAttribute("msgErro", "Ocorreu um erro ao gravar as Parcelas");
			log.error("ParcelamentoController::gravar::Exception", e);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private String getValorMinimo(String key, HttpServletRequest request) {
		String valor = ConstantesCRM.SVAZIO;
		try {
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			ParametroVO parametro = GetParametro.getInstace().getParametroVO(user, key);
			valor = parametro.getDsValorParametro();

			DecimalFormat df = new DecimalFormat("###,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
			valor = df.format(new Double(valor));

		} catch (Exception e) {
			log.error("ParcelamentoController::getValorMinimo::Exception", e);
		}
		return valor;
	}

	public static class ParcelaForm extends ActionForm {

		private static final long serialVersionUID = 6204847935259983810L;

		private String idParcelaDisp;
		private String idParcelaSel;
		private String vlParcelaMinima;
		private String[] idParcelasDisp;
		private String[] idParcelasSel;
		private String idTipoPessoa;
		private Collection optionsDisp;
		private Collection optionsSel;
		private Collection optionsTipoPessoa;

		public void setIdTipoPessoa(String idTipoPessoa) {
			this.idTipoPessoa = idTipoPessoa;
		}

		public String getIdTipoPessoa() {
			return this.idTipoPessoa;
		}

		public void setVlParcelaMinima(String vlParcelaMinima) {
			this.vlParcelaMinima = vlParcelaMinima;
		}

		public String getVlParcelaMinima() {
			return this.vlParcelaMinima;
		}

		public void setIdParcelaDisp(String idParcelaDisp) {
			this.idParcelaDisp = idParcelaDisp;
		}

		public String getIdParcelaDisp() {
			return this.idParcelaDisp;
		}

		public void setIdParcelaSel(String idParcelaSel) {
			this.idParcelaSel = idParcelaSel;
		}

		public String getIdParcelaSel() {
			return this.idParcelaSel;
		}

		public void setIdParcelasDisp(String[] idParcelasDisp) {
			this.idParcelasDisp = idParcelasDisp;
		}

		public String[] getIdParcelasDisp() {
			if (this.idParcelasDisp == null || this.idParcelasDisp.length == 0) {
				this.idParcelasDisp = new String[1];
			}
			return this.idParcelasDisp;
		}

		public void setIdParcelasSel(String[] idParcelasSel) {
			this.idParcelasSel = idParcelasSel;
		}

		public String[] getIdParcelasSel() {
			if (this.idParcelasSel == null || this.idParcelasSel.length == 0) {
				this.idParcelasSel = new String[1];
			}
			return this.idParcelasSel;
		}

		public Collection getOptionsDisp() {
			return this.optionsDisp;
		}

		public void setOptionsDisp(Collection value) {
			this.optionsDisp = value;
		}

		public Collection getOptionsSel() {
			return this.optionsSel;
		}

		public void setOptionsSel(Collection value) {
			this.optionsSel = value;
		}

		public Collection getOptionsTipoPessoa() {
			return this.optionsTipoPessoa;
		}

		public void setOptionsTipoPessoa(Collection value) {
			this.optionsTipoPessoa = value;
		}
	}

	public class Option {
		private String id;
		private String name;

		public Option() {
		}

		public Option(String id, String name) {
			this.id = id;
			this.name = name;
		}

		public String getId() {
			return id;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
