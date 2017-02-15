package admsistemas.parametrizacoes.vinculoRegional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.workflow.vo.WFRegionalVODocument.WFRegionalVO;

import com.indracompany.actions.AbstractAction;

public class VinculoRegionalController extends AbstractAction {

	private static final long serialVersionUID = -9048429298626093687L;

	private ParametrosForm parametrosForm;

	public ParametrosForm getParametrosForm() {
		if (this.parametrosForm == null) {
			this.parametrosForm = new ParametrosForm();
		}
		return this.parametrosForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("desvinculartodos".equals(mapping.getParameter())) {
			return desvinculartodos(mapping, form, request, response);
		} else if ("gravar".equals(mapping.getParameter())) {
			return gravar(mapping, form, request, response);
		} else if ("filtrar".equals(mapping.getParameter())) {
			return filtrar(mapping, form, request, response);
		} else {
			return begin(mapping, form, request, response);
		}
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		listaRegionais();

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward desvinculartodos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ParametrosForm form = (ParametrosForm) formParam;
		Pesquisar pesquisar = new Pesquisar();
		StringBuffer query = new StringBuffer("DELETE FROM CUSTOMER.VINCULOESTADO WHERE IDUFOPERADORA1 = " + form.getRegionalSelecionada());
		pesquisar.executar(query.toString());

		getParametrosForm().setRegionalSelecionada(ConstantesCRM.SVAZIO);
		getParametrosForm().setRegionaisDisponiveis(null);
		getParametrosForm().setRegionaisSelecionadas(null);

		request.setAttribute("msgRetorno", "Todas regionais disvinculadas com sucesso.");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward gravar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ParametrosForm form = (ParametrosForm) formParam;
		Pesquisar pesquisar = new Pesquisar();
		StringBuffer query = new StringBuffer("DELETE FROM CUSTOMER.VINCULOESTADO WHERE IDUFOPERADORA1 = " + form.getRegionalSelecionada());
		pesquisar.executar(query.toString());

		for (int i = 0; i < form.getSelecionadas().length; i++) {
			StringBuffer insert = new StringBuffer("");
			insert.append("INSERT INTO CUSTOMER.VINCULOESTADO (IDUFOPERADORA1, IDUFOPERADORA2, DTCRIACAO) VALUES (");
			insert.append(form.getRegionalSelecionada());
			insert.append(",");
			insert.append(form.getSelecionadas()[i]);
			insert.append(",");
			insert.append("SYSDATE)");
			pesquisar.executar(insert.toString());
		}

		getParametrosForm().setRegionalSelecionada(ConstantesCRM.SVAZIO);
		getParametrosForm().setRegionaisDisponiveis(null);
		getParametrosForm().setRegionaisSelecionadas(null);

		request.setAttribute("msgRetorno", "Vinculos realizados com sucesso.");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	public ActionForward filtrar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		ParametrosForm form = (ParametrosForm) formParam;
		listaRegionaisDisponiveis(form.getRegionalSelecionada());
		listaRegionaisSelecionadas(form.getRegionalSelecionada());

		getParametrosForm().setRegionalSelecionada(form.getRegionalSelecionada());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private void listaRegionaisDisponiveis(String selecionada) {
		Resultset rsVO = null;
		try {
			StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);

			query.append("SELECT A.SGUF, B.IDUFOPERADORA ");
			query.append(" FROM APOIO.UF A,CUSTOMER.UFOPERADORA B ");
			query.append(" WHERE A.IDUF = B.IDUF ");
			query.append(" AND A.IDUF NOT IN (SELECT IDUFOPERADORA2 FROM CUSTOMER.VINCULOESTADO WHERE IDUFOPERADORA1 = " + selecionada + ")");
			query.append(" ORDER BY A.SGUF");

			Pesquisar pesquisar = new Pesquisar();
			rsVO = pesquisar.executar(query.toString());

			if (rsVO != null && rsVO.getLinhas() != null) {
				WFRegionalVO[] listaRegionais = new WFRegionalVO[rsVO.getLinhas().getLinhaArray().length];
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					listaRegionais[i] = WFRegionalVO.Factory.newInstance();
					listaRegionais[i].setIdRegional(rsVO.getLinhas().getLinhaArray(i).getValorArray(1));
					listaRegionais[i].setDsRegional(rsVO.getLinhas().getLinhaArray(i).getValorArray(0));
				}
				getParametrosForm().setRegionaisDisponiveis(listaRegionais);
			}

		} catch (Exception e) {
		}
	}

	private void listaRegionaisSelecionadas(String selecionada) {
		Resultset rsVO = null;
		try {
			StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);

			query.append("SELECT A.SGUF, B.IDUFOPERADORA ");
			query.append(" FROM APOIO.UF A,CUSTOMER.UFOPERADORA B ");
			query.append(" WHERE A.IDUF = B.IDUF ");
			query.append(" AND A.IDUF IN (SELECT IDUFOPERADORA2 FROM CUSTOMER.VINCULOESTADO WHERE IDUFOPERADORA1 = " + selecionada + ")");
			query.append(" ORDER BY A.SGUF");

			Pesquisar pesquisar = new Pesquisar();
			rsVO = pesquisar.executar(query.toString());

			if (rsVO != null && rsVO.getLinhas() != null) {
				WFRegionalVO[] listaRegionais = new WFRegionalVO[rsVO.getLinhas().getLinhaArray().length];
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					listaRegionais[i] = WFRegionalVO.Factory.newInstance();

					listaRegionais[i].setIdRegional(rsVO.getLinhas().getLinhaArray(i).getValorArray(1));
					listaRegionais[i].setDsRegional(rsVO.getLinhas().getLinhaArray(i).getValorArray(0));
				}
				getParametrosForm().setRegionaisSelecionadas(listaRegionais);
			}

		} catch (Exception e) {
		}
	}

	private void listaRegionais() {
		Resultset rsVO = null;
		try {
			StringBuffer query = new StringBuffer(ConstantesCRM.SVAZIO);
			query.append("SELECT A.SGUF, B.IDUFOPERADORA FROM APOIO.UF A, CUSTOMER.UFOPERADORA B WHERE A.IDUF = B.IDUF ORDER BY A.SGUF ");
			Pesquisar pesquisar = new Pesquisar();
			rsVO = pesquisar.executar(query.toString());

			if (rsVO != null && rsVO.getLinhas() != null) {
				WFRegionalVO[] listaRegionais = new WFRegionalVO[rsVO.getLinhas().getLinhaArray().length];
				for (int i = 0; i < rsVO.getLinhas().getLinhaArray().length; i++) {
					listaRegionais[i] = WFRegionalVO.Factory.newInstance();

					listaRegionais[i].setIdRegional(rsVO.getLinhas().getLinhaArray(i).getValorArray(1));
					listaRegionais[i].setDsRegional(rsVO.getLinhas().getLinhaArray(i).getValorArray(0));
				}
				getParametrosForm().setListaRegionais(listaRegionais);
			}

		} catch (Exception e) {
		}
	}

	public static class ParametrosForm extends ActionForm {

		private static final long serialVersionUID = -3767183533374861525L;

		private WFRegionalVO[] listaRegionais;
		private WFRegionalVO[] regionaisDisponiveis;
		private WFRegionalVO[] regionaisSelecionadas;

		private String regionalSelecionada;
		private String[] selecionadas;
		private String[] disponiveis;

		public String[] getSelecionadas() {
			return selecionadas;
		}

		public void setSelecionadas(String[] selecionadas) {
			this.selecionadas = selecionadas;
		}

		public String[] getDisponiveis() {
			return disponiveis;
		}

		public void setDisponiveis(String[] disponiveis) {
			this.disponiveis = disponiveis;
		}

		public String getRegionalSelecionada() {
			return regionalSelecionada;
		}

		public void setRegionalSelecionada(String regionalSelecionada) {
			this.regionalSelecionada = regionalSelecionada;
		}

		public WFRegionalVO[] getRegionaisDisponiveis() {
			if (this.regionaisDisponiveis == null) {
				this.regionaisDisponiveis = new WFRegionalVO[0];
			}
			return this.regionaisDisponiveis;
		}

		public WFRegionalVO[] getListaRegionais() {
			if (this.listaRegionais == null) {
				this.listaRegionais = new WFRegionalVO[0];
			}
			return this.listaRegionais;
		}

		public WFRegionalVO[] getRegionaisSelecionadas() {
			if (this.regionaisSelecionadas == null) {
				this.regionaisSelecionadas = new WFRegionalVO[0];
			}
			return this.regionaisSelecionadas;
		}

		public void setRegionaisDisponiveis(WFRegionalVO[] arg) {
			this.regionaisDisponiveis = arg;
		}

		public void setListaRegionais(WFRegionalVO[] arg) {
			this.listaRegionais = arg;
		}

		public void setRegionaisSelecionadas(WFRegionalVO[] arg) {
			this.regionaisSelecionadas = arg;
		}

	}

}