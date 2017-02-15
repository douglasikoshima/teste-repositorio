package fidelizacao.Configurar.CadastrarDescontos;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.manter.DescontoFacade;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoCadastroDescontoVODocument.FidelizacaoCadastroDescontoVO;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoCadastroDescontoVODocument.FidelizacaoCadastroDescontoVO.ListaCadastroDesconto;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoCadastroDescontoVODocument.FidelizacaoCadastroDescontoVO.ListaMatriz;

import com.indracompany.actions.AbstractAction;

public class CadastrarDescontosController extends AbstractAction {

	private static final long serialVersionUID = 6508076034080081352L;

	protected global.Global globalApp = new global.Global();
	private String user = ConstantesCRM.SVAZIO;
	private DescontoForm descontoForm = new DescontoForm();

	@EJB
	private DescontoFacade descontoFacade;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("carregaListaAparelhos".equals(mapping.getParameter())) {
			return carregaListaAparelhos(mapping, form, request, response);
		} else if ("gravarMatrizDesconto".equals(mapping.getParameter())) {
			return gravarMatrizDesconto(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		descontoForm = new DescontoForm();
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());

			FidelizacaoCadastroDescontoVO f = descontoFacade.getListaMatriz(user);
			if (f != null) {
				getDescontoForm().setListaMatriz(f.getListaMatrizArray());
			}

			/* REMOVER ESSE TRECHO APOS TESTE FAKE */
			// ListaMatriz[] lista = new ListaMatriz[2];
			// lista[0]=ListaMatriz.Factory.newInstance();
			// lista[0].setIdMatriz("1");
			// lista[0].setDsMatriz("Matriz 1");
			// lista[1]=ListaMatriz.Factory.newInstance();
			// lista[1].setIdMatriz("2");
			// lista[1].setDsMatriz("Matriz 2");

			// getDescontoForm().setListaMatriz(lista);
			/* FIM TRECHO FAKE */
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public DescontoForm getDescontoForm() {
		return descontoForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward carregaListaAparelhos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		DescontoForm form = (DescontoForm) formParam;
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());

			FidelizacaoCadastroDescontoVO f = FidelizacaoCadastroDescontoVO.Factory.newInstance();
			f.setIdMatriz(form.getIdMatriz());

			f = descontoFacade.buscarListaPorMatriz(user, f);

			ListaCadastroDesconto[] lista = f.getListaCadastroDescontoArray();
			getDescontoForm().setIdMatriz(form.getIdMatriz());
			getDescontoForm().setListaDesconto(lista);

			getDescontoForm().setNmUsuario(f.getNmUsuario());
			getDescontoForm().setDtAlteracao(f.getDtAlteracao());
			getDescontoForm().setNmMatriz(f.getDsMatriz());

			/* REMOVER ESSE TRECHO APOS TESTE FAKE */
			// ListaCadastroDesconto[] lista = new ListaCadastroDesconto[2];
			// lista[0]=ListaCadastroDesconto.Factory.newInstance();
			// lista[0].setIdDesconto("10");
			// lista[0].setIdAparelho("1");
			// lista[0].setDsAparelho("Sony");
			// lista[0].setSafira("100");
			// lista[0].setDiamante2("40");
			// lista[0].setEsmeralda3("10");

			// lista[1]=ListaCadastroDesconto.Factory.newInstance();
			// lista[1].setIdDesconto("11");
			// lista[1].setIdAparelho("2");
			// lista[1].setDsAparelho("Samsung");
			// lista[1].setSafira("25");
			// lista[1].setDiamante2("20");
			// lista[1].setEsmeralda3("15");

			// getDescontoForm().setIdMatriz(form.getIdMatriz());
			// getDescontoForm().setListaDesconto(lista);

			// getDescontoForm().setNmUsuario("c_rbredes");
			// getDescontoForm().setDtAlteracao("18/12/2008");
			// getDescontoForm().setNmMatriz("Matriz Teste");
			/* FIM TRECHO FAKE */

		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward gravarMatrizDesconto(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		DescontoForm form = (DescontoForm) formParam;
		try {
			user = ConstantesCRM.getCurrentUser(request.getSession());

			FidelizacaoCadastroDescontoVO f = FidelizacaoCadastroDescontoVO.Factory.newInstance();
			FidelizacaoCadastroDescontoVO ret = null;
			ListaCadastroDesconto l = ListaCadastroDesconto.Factory.newInstance();

			f.setIdMatriz(form.getIdMatriz());
			f.setDsMatriz(form.getNmMatriz());

			l.setIdDesconto(form.getIdMatrizDesconto());
			l.setIdAparelho(form.getIdAparelhoIncluirEditar());
			if (form.gets1() != null && !form.gets1().equals(ConstantesCRM.SVAZIO)) {
				l.setSafira(form.gets1());
			}
			if (form.gete1() != null && !form.gete1().equals(ConstantesCRM.SVAZIO)) {
				l.setEsmeralda1(form.gete1());
			}
			if (form.gete2() != null && !form.gete2().equals(ConstantesCRM.SVAZIO)) {
				l.setEsmeralda2(form.gete2());
			}
			if (form.gete3() != null && !form.gete3().equals(ConstantesCRM.SVAZIO)) {
				l.setEsmeralda3(form.gete3());
			}
			if (form.getr1() != null && !form.getr1().equals(ConstantesCRM.SVAZIO)) {
				l.setRubi1(form.getr1());
			}
			if (form.getr2() != null && !form.getr2().equals(ConstantesCRM.SVAZIO)) {
				l.setRubi2(form.getr2());
			}
			if (form.getr3() != null && !form.getr3().equals(ConstantesCRM.SVAZIO)) {
				l.setRubi3(form.getr3());
			}
			if (form.getd1() != null && !form.getd1().equals(ConstantesCRM.SVAZIO)) {
				l.setDiamante1(form.getd1());
			}
			if (form.getd2() != null && !form.getd2().equals(ConstantesCRM.SVAZIO)) {
				l.setDiamante2(form.getd2());
			}
			if (form.getd3() != null && !form.getd3().equals(ConstantesCRM.SVAZIO)) {
				l.setDiamante3(form.getd3());
			}
			if (form.getNc() != null && !form.getNc().equals(ConstantesCRM.SVAZIO)) {
				l.setNaoClassificado(form.getNc());
			}

			f.addNewListaCadastroDesconto();
			f.setListaCadastroDescontoArray(0, l);

			ret = descontoFacade.gravarMatriz(user, f);

			if (ret != null) {
				getDescontoForm().setIdMatriz(ret.getIdMatriz());
				// getDescontoForm().setNmMatriz(form.getNmMatriz());

				ret = descontoFacade.buscarListaPorMatriz(user, ret);

				ListaCadastroDesconto[] lista = ret.getListaCadastroDescontoArray();
				getDescontoForm().setIdMatriz(form.getIdMatriz());
				getDescontoForm().setListaDesconto(lista);

				getDescontoForm().setNmUsuario(f.getNmUsuario());
				getDescontoForm().setDtAlteracao(f.getDtAlteracao());
				getDescontoForm().setNmMatriz(f.getDsMatriz());
			}

			try {
				user = ConstantesCRM.getCurrentUser(request.getSession());

				FidelizacaoCadastroDescontoVO fi = descontoFacade.getListaMatriz(user);
				if (fi != null) {
					getDescontoForm().setListaMatriz(fi.getListaMatrizArray());
				}
			} catch (Exception e) {
				request.setAttribute("msgErro", e.getMessage());
				request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
				return mapping.findForward(ConstantesCRM.SUCCESS);
			}
			/* REMOVER ESSE PEDAÇO FAKE */
			/*
			 * user = ConstantesCRM.getCurrentUser(request.getSession());
			 * 
			 * FidelizacaoCadastroDescontoVO f =
			 * FidelizacaoCadastroDescontoVO.Factory.newInstance();
			 * FidelizacaoCadastroDescontoVO ret =null; ListaCadastroDesconto l
			 * = ListaCadastroDesconto.Factory.newInstance();
			 * 
			 * f.setIdMatriz(form.getIdMatriz());
			 * f.setDsMatriz(form.getNmMatriz());
			 * 
			 * l.setIdDesconto(form.getIdMatrizDesconto());
			 * l.setIdAparelho(form.getIdAparelhoIncluirEditar());
			 * l.setSafira(form.gets1()); l.setEsmeralda1(form.gete1());
			 * l.setEsmeralda2(form.gete2()); l.setEsmeralda3(form.gete3());
			 * l.setRubi1(form.getr1()); l.setRubi2(form.getr2());
			 * l.setRubi3(form.getr3()); l.setDiamante1(form.getd1());
			 * l.setDiamante2(form.getd2()); l.setDiamante3(form.getd3());
			 * l.setNaoClassificado(form.getNc());
			 * 
			 * f.addNewListaCadastroDesconto();
			 * f.setListaCadastroDescontoArray(0,l);
			 * 
			 * ListaCadastroDesconto[] lista = new ListaCadastroDesconto[2];
			 * lista[0]=ListaCadastroDesconto.Factory.newInstance();
			 * lista[0].setIdDesconto("10"); lista[0].setIdAparelho("1");
			 * lista[0].setDsAparelho("Sony"); lista[0].setSafira("100");
			 * lista[0].setDiamante2("40"); lista[0].setEsmeralda3("10");
			 * 
			 * lista[1]=ListaCadastroDesconto.Factory.newInstance();
			 * lista[1].setIdDesconto("11"); lista[1].setIdAparelho("2");
			 * lista[1].setDsAparelho("Samsung"); lista[1].setSafira("25");
			 * lista[1].setDiamante2("20"); lista[1].setEsmeralda3("15");
			 * 
			 * getDescontoForm().setIdMatriz(form.getIdMatriz());
			 * getDescontoForm().setListaDesconto(lista);
			 * 
			 * getDescontoForm().setNmUsuario("c_rbredes");
			 * getDescontoForm().setDtAlteracao("18/12/2008");
			 * getDescontoForm().setNmMatriz("Matriz Teste");
			 */
			/* FIM FAKE */
		} catch (Exception e) {
			request.setAttribute("msgErro", e.getMessage());
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class DescontoForm extends ActionForm {

		private static final long serialVersionUID = -7872208356758806695L;

		private String idMatrizDesconto;
		private String idAparelhoIncluirEditar;
		private ListaCadastroDesconto[] listaDesconto;
		private ListaMatriz[] listaMatriz;
		// não classificado
		private String nc = ConstantesCRM.SVAZIO;
		// diamante
		private String d3 = ConstantesCRM.SVAZIO;
		private String d2 = ConstantesCRM.SVAZIO;
		private String d1 = ConstantesCRM.SVAZIO;
		// rubi
		private String r3 = ConstantesCRM.SVAZIO;
		private String r2 = ConstantesCRM.SVAZIO;
		private String r1 = ConstantesCRM.SVAZIO;
		// esmeralda
		private String e3 = ConstantesCRM.SVAZIO;
		private String e2 = ConstantesCRM.SVAZIO;
		private String e1 = ConstantesCRM.SVAZIO;
		// safira
		private String s1 = ConstantesCRM.SVAZIO;
		private String dtAlteracao = ConstantesCRM.SVAZIO;
		private String nmUsuario = ConstantesCRM.SVAZIO;
		private String nmMatriz = ConstantesCRM.SVAZIO;
		private String idMatriz = ConstantesCRM.SVAZIO;

		public void setIdMatriz(String idMatriz) {
			this.idMatriz = idMatriz;
		}

		public String getIdMatriz() {
			return this.idMatriz;
		}

		public void setNmMatriz(String nmMatriz) {
			this.nmMatriz = nmMatriz;
		}

		public String getNmMatriz() {
			return this.nmMatriz;
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

		public void sets1(String s1) {
			this.s1 = s1;
		}

		public String gets1() {
			return this.s1;
		}

		public void sete1(String e1) {
			this.e1 = e1;
		}

		public String gete1() {
			return this.e1;
		}

		public void sete2(String e2) {
			this.e2 = e2;
		}

		public String gete2() {
			return this.e2;
		}

		public void sete3(String e3) {
			this.e3 = e3;
		}

		public String gete3() {
			return this.e3;
		}

		public void setr1(String r1) {
			this.r1 = r1;
		}

		public String getr1() {
			return this.r1;
		}

		public void setr2(String r2) {
			this.r2 = r2;
		}

		public String getr2() {
			return this.r2;
		}

		public void setr3(String r3) {
			this.r3 = r3;
		}

		public String getr3() {
			return this.r3;
		}

		public void setd1(String d1) {
			this.d1 = d1;
		}

		public String getd1() {
			return this.d1;
		}

		public void setd2(String d2) {
			this.d2 = d2;
		}

		public String getd2() {
			return this.d2;
		}

		public void setd3(String d3) {
			this.d3 = d3;
		}

		public String getd3() {
			return this.d3;
		}

		public void setNc(String nc) {
			this.nc = nc;
		}

		public String getNc() {
			return this.nc;
		}

		public void setListaMatriz(ListaMatriz[] listaMatriz) {
			this.listaMatriz = listaMatriz;
		}

		public ListaMatriz[] getListaMatriz() {

			if (this.listaMatriz == null) {
				this.listaMatriz = new ListaMatriz[0];
			}

			return this.listaMatriz;
		}

		public void setListaDesconto(ListaCadastroDesconto[] listaDesconto) {
			this.listaDesconto = listaDesconto;
		}

		public ListaCadastroDesconto[] getListaDesconto() {

			if (this.listaDesconto == null || this.listaDesconto.length == 0) {
				this.listaDesconto = new ListaCadastroDesconto[0];
			}

			return this.listaDesconto;
		}

		public void setIdAparelhoIncluirEditar(String idAparelhoIncluirEditar) {
			this.idAparelhoIncluirEditar = idAparelhoIncluirEditar;
		}

		public String getIdAparelhoIncluirEditar() {
			return this.idAparelhoIncluirEditar;
		}

		public void setIdMatrizDesconto(String idMatrizDesconto) {
			this.idMatrizDesconto = idMatrizDesconto;
		}

		public String getIdMatrizDesconto() {
			return this.idMatrizDesconto;
		}
	}
}
