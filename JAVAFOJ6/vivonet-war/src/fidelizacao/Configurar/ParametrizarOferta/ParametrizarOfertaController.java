package fidelizacao.Configurar.ParametrizarOferta;

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
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.ListasVO.Lista.Disponivel;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoVODocument.FidelizacaoVO.Manter;

import com.indracompany.actions.AbstractAction;

public class ParametrizarOfertaController extends AbstractAction {

	private static final long serialVersionUID = -2361402844211365959L;

	private ParametrizacaoOfertaForm parametrizacaoOfertaForm;

	protected global.Global globalApp = new global.Global();

	@EJB
	private Fidelizacao fidelizacao;

	public ParametrizacaoOfertaForm getParametrizacaoOfertaForm() {
		if (this.parametrizacaoOfertaForm == null) {
			this.parametrizacaoOfertaForm = new ParametrizacaoOfertaForm();
		}
		return this.parametrizacaoOfertaForm;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("pesquisar".equals(mapping.getParameter())) {
			return pesquisar(mapping, form, request, response);
		} else if ("incluir".equals(mapping.getParameter())) {
			return incluir(mapping, form, request, response);
		} else if ("alterar".equals(mapping.getParameter())) {
			return alterar(mapping, form, request, response);
		} else if ("excluir".equals(mapping.getParameter())) {
			return excluir(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		getParametrizacaoOfertaForm().setSgTipoOferta(ConstantesCRM.SVAZIO);

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.addNewFiltro().addNewCombos().addNmSelect("TPOFERTA");

		FidelizacaoVO listas = fidelizacao.getListas(user, xml);
		listas.addNewTabelas().addNewParamOfertas();
		getParametrizacaoOfertaForm().setFidelizacaoVO(listas);
		getParametrizacaoOfertaForm().setListaOfertas(listas.getListasVO().getListaArray(0).getDisponivel());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="index.jsp"
	 */
	protected ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParametrizacaoOfertaForm form = (ParametrizacaoOfertaForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("PARAMOFERTAS");
		xml.setTpProcesso("PSQ");

		Filtro filtro = xml.addNewFiltro();
		filtro.setSgTpOferta(form.getSgTipoOferta());
		filtro.setDsOferta(form.getDsOferta());

		FidelizacaoVO tabela = fidelizacao.getListas(user, xml);

		getParametrizacaoOfertaForm().setFidelizacaoVO(tabela);
		getParametrizacaoOfertaForm().setSgTipoOferta(form.getSgTipoOferta());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisar.do"
	 */
	protected ActionForward incluir(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParametrizacaoOfertaForm form = (ParametrizacaoOfertaForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("PARAMOFERTAS");
		xml.setTpProcesso("CAD");

		Manter manter = xml.addNewManter();
		manter.setSgOferta(form.getSgTipoOferta());
		manter.setDsOferta(form.getDsOferta());

		FidelizacaoVO conf = fidelizacao.setParam(user, xml);
		if ("1".equals(conf.getCodError())) {
			request.setAttribute("msgError", conf.getMsgError());
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisar.do"
	 */
	protected ActionForward alterar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		ParametrizacaoOfertaForm form = (ParametrizacaoOfertaForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("PARAMOFERTAS");
		xml.setTpProcesso("ALT");

		Manter manter = xml.addNewManter();
		manter.setIdCadastrado(form.getIdOferta());
		manter.setSgOferta(form.getSgTipoOferta());
		manter.setDsOferta(form.getDsOferta());

		FidelizacaoVO conf = fidelizacao.setParam(user, xml);
		if ("1".equals(conf.getCodError())) {
			request.setAttribute("msgError", conf.getMsgError());
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="pesquisar.do"
	 */
	protected ActionForward excluir(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ParametrizacaoOfertaForm form = (ParametrizacaoOfertaForm) formParam;
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		FidelizacaoVO xml = FidelizacaoVO.Factory.newInstance();
		xml.setNmProcesso("PARAMOFERTAS");
		xml.setTpProcesso("EXC");

		Manter manter = xml.addNewManter();
		manter.setIdCadastrado(form.getIdOferta());

		FidelizacaoVO conf = fidelizacao.setParam(user, xml);
		if ("1".equals(conf.getCodError())) {
			request.setAttribute("msgError", conf.getMsgError());
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ParametrizacaoOfertaForm extends ActionForm {

		private static final long serialVersionUID = -6546970689886843683L;

		private Disponivel listaOfertas;
		private String sgTipoOferta = ConstantesCRM.SVAZIO;
		private String dsOferta = ConstantesCRM.SVAZIO;
		private String idOferta = ConstantesCRM.SVAZIO;
		private FidelizacaoVO fidelizacaoVO = FidelizacaoVO.Factory.newInstance();

		public ParametrizacaoOfertaForm() {
		}

		public FidelizacaoVO getFidelizacaoVO() {
			return this.fidelizacaoVO;
		}

		public void setFidelizacaoVO(FidelizacaoVO fidelizacaoVO) {
			this.fidelizacaoVO = fidelizacaoVO;
		}

		public void setListaOfertas(Disponivel listaOfertas) {
			this.listaOfertas = listaOfertas;
		}

		public Disponivel getListaOfertas() {
			return this.listaOfertas;
		}

		public void setSgTipoOferta(String sgTipoOferta) {
			this.sgTipoOferta = sgTipoOferta;
		}

		public String getSgTipoOferta() {
			return this.sgTipoOferta;
		}

		public void setIdOferta(String idOferta) {
			this.idOferta = idOferta;
		}

		public String getIdOferta() {
			return this.idOferta;
		}

		public void setDsOferta(String dsOferta) {
			this.dsOferta = dsOferta;
		}

		public String getDsOferta() {
			return this.dsOferta;
		}
	}

}
