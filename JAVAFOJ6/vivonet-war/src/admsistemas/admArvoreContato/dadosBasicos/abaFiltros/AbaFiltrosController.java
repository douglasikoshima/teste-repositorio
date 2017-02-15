package admsistemas.admArvoreContato.dadosBasicos.abaFiltros;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmFiltrosVODocument.AdmFiltrosVO;
import br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmSegmentacaoVODocument.AdmSegmentacaoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoCarteiraVODocument.AdmTipoCarteiraVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoLinhaVODocument.AdmTipoLinhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoPessoaVODocument.AdmTipoPessoaVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class AbaFiltrosController extends AbstractAction {

	private static final long serialVersionUID = -6638178950139974123L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.filtros.Filtros controlFiltro;

	FormFiltro formFiltro;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("salvarFiltros".equals(mapping.getParameter())) {
			return salvarFiltros(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularFiltros.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("AbaFiltrosController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		try {
			request.setCharacterEncoding(ConstantesCRM.SISO);

			formFiltro = new FormFiltro();

			// id=request.getParameter("idContato");

			formFiltro.setIdContato(request.getParameter("idContato"));

			AdmIdContatoVO admIdContatoVO = AdmIdContatoVO.Factory.newInstance();
			AdmFiltrosVO admFiltrosVO = AdmFiltrosVO.Factory.newInstance();
			admIdContatoVO.setIdContato(formFiltro.getIdContato());
			admFiltrosVO = controlFiltro.listaFiltros(admIdContatoVO, ConstantesCRM.getCurrentUser(request.getSession()));

			montaFiltro(admFiltrosVO);
			formFiltro.setMsgError(ConstantesCRM.SVAZIO);

		} catch (TuxedoWarningException twe) {
			log.warn("AbaFiltrosController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formFiltro.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AbaFiltrosController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/index.jsp");
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="vincularFiltros.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	public ActionForward salvarFiltros(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AbaFiltrosController:salvarFiltros" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormFiltro form = (FormFiltro) formParam;
		formFiltro.setMsgError(ConstantesCRM.SVAZIO);

		try {

			AdmFiltrosVO admFiltrosVO = AdmFiltrosVO.Factory.newInstance();
			AdmFiltrosVO newAdmFiltrosVO = AdmFiltrosVO.Factory.newInstance();

			AdmSegmentacaoVO[] admSegmentacaoVO = new AdmSegmentacaoVO[form.getArraySegmentacaoAssociada().length];
			AdmTipoCarteiraVO[] admTipoCarteiraVO = new AdmTipoCarteiraVO[form.getArrayTipoCarteiraAssociada().length];
			AdmTipoLinhaVO[] admTipoLinhaVO = new AdmTipoLinhaVO[form.getArrayTipoLinhaAssociada().length];
			AdmTipoPessoaVO[] admTipoPessoaVO = new AdmTipoPessoaVO[form.getArrayTipoPessoaAssociada().length];

			String[] segmentacao = form.getArraySegmentacaoAssociada();
			String[] carteira = form.getArrayTipoCarteiraAssociada();
			String[] linha = form.getArrayTipoLinhaAssociada();
			String[] pessoa = form.getArrayTipoPessoaAssociada();

			for (int i = 0; i < segmentacao.length; i++) {
				admSegmentacaoVO[i] = AdmSegmentacaoVO.Factory.newInstance();
				admSegmentacaoVO[i].setIdSegmentacao(segmentacao[i]);

			}

			for (int j = 0; j < carteira.length; j++) {
				admTipoCarteiraVO[j] = AdmTipoCarteiraVO.Factory.newInstance();
				admTipoCarteiraVO[j].setIdTipoCarteira(carteira[j]);
			}

			for (int k = 0; k < linha.length; k++) {
				admTipoLinhaVO[k] = AdmTipoLinhaVO.Factory.newInstance();
				admTipoLinhaVO[k].setIdTipoLinha(linha[k]);
			}

			for (int l = 0; l < pessoa.length; l++) {
				admTipoPessoaVO[l] = AdmTipoPessoaVO.Factory.newInstance();
				admTipoPessoaVO[l].setIdTipoPessoa(pessoa[l]);
			}

			admFiltrosVO.setIdContato(formFiltro.getIdContato());
			admFiltrosVO.addNewTipoPessoaAssociada().setAdmTipoPessoaVOArray(admTipoPessoaVO);
			admFiltrosVO.addNewTipoCarteiraAssociada().setAdmTipoCarteiraVOArray(admTipoCarteiraVO);
			admFiltrosVO.addNewSegmentacaoAssociada().setAdmSegmentacaoVOArray(admSegmentacaoVO);
			admFiltrosVO.addNewTipoLinhaAssociada().setAdmTipoLinhaVOArray(admTipoLinhaVO);

			newAdmFiltrosVO = controlFiltro.salvaFiltros(admFiltrosVO, ConstantesCRM.getCurrentUser(request.getSession()));

			limpaForm();
			montaFiltro(newAdmFiltrosVO);
			formFiltro.setIdContato(form.getIdContato());
			formFiltro.setMsgError(ConstantesCRM.SSucesso);

		} catch (TuxedoWarningException twe) {
			log.warn("AbaFiltrosController:salvarFiltros" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) --> MENSAGEM --> " + twe.getXmlHeader().getStatusText());
			formFiltro.setMsgError(twe.getXmlHeader().getStatusText());

		} catch (Exception e) {
			log.error("AbaFiltrosController:salvarFiltros" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )", e);

			// Seta FormBean de Erro.
			FormError formError = globalApp.buildFormError(e, request);
			formError.setTarget(ConstantesCRM.FRAMEAPP);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	private void montaFiltro(AdmFiltrosVO admFiltrosVO) {

		formFiltro.setTipoCarteiraAssociadaVO(admFiltrosVO.getTipoCarteiraAssociada().getAdmTipoCarteiraVOArray());
		formFiltro.setTipoCarteiraExistenteVO(admFiltrosVO.getTipoCarteiraExistente().getAdmTipoCarteiraVOArray());
		formFiltro.setTipoLinhaAssociadaVO(admFiltrosVO.getTipoLinhaAssociada().getAdmTipoLinhaVOArray());
		formFiltro.setTipoLinhaExistenteVO(admFiltrosVO.getTipoLinhaExistente().getAdmTipoLinhaVOArray());
		formFiltro.setTipoPessoaAssociadaVO(admFiltrosVO.getTipoPessoaAssociada().getAdmTipoPessoaVOArray());
		formFiltro.setTipoPessoaExistenteVO(admFiltrosVO.getTipoPessoaExistente().getAdmTipoPessoaVOArray());
		formFiltro.setTipoSegmentacaoAssociadaVO(admFiltrosVO.getSegmentacaoAssociada().getAdmSegmentacaoVOArray());
		formFiltro.setTipoSegmentacaoExistenteVO(admFiltrosVO.getSegmentacaoExistente().getAdmSegmentacaoVOArray());
	}

	private void limpaForm() {
		formFiltro = new FormFiltro();
	}

	public static class FormFiltro extends ActionForm {

		private static final long serialVersionUID = 4876867325198999970L;

		private String msgError = ConstantesCRM.SVAZIO;
		private String[] d;
		private String[] c;
		private String[] b;
		private String[] a;
		private AdmTipoPessoaVO[] tipoPessoaExistenteVO;
		private AdmTipoPessoaVO[] tipoPessoaAssociadaVO;
		private AdmTipoLinhaVO[] tipoLinhaExistenteVO;
		private AdmTipoLinhaVO[] tipoLinhaAssociadaVO;
		private AdmTipoCarteiraVO[] tipoCarteiraAssociadaVO;
		private AdmTipoCarteiraVO[] tipoCarteiraExistenteVO;
		private AdmSegmentacaoVO[] tipoSegmentacaoAssociadaVO;
		private AdmSegmentacaoVO[] tipoSegmentacaoExistenteVO;
		private String[] arrayTipoCarteiraAssociada;
		private String[] arraySegmentacaoAssociada;
		private String[] arrayTipoLinhaAssociada;
		private String[] arrayTipoPessoaAssociada;
		private String idContato;

		public FormFiltro() {

			idContato = ConstantesCRM.SVAZIO;
			arrayTipoCarteiraAssociada = new String[0];
			arraySegmentacaoAssociada = new String[0];
			arrayTipoLinhaAssociada = new String[0];
			arrayTipoPessoaAssociada = new String[0];

			tipoPessoaExistenteVO = new AdmTipoPessoaVO[0];
			tipoPessoaAssociadaVO = new AdmTipoPessoaVO[0];
			tipoLinhaExistenteVO = new AdmTipoLinhaVO[0];
			tipoLinhaAssociadaVO = new AdmTipoLinhaVO[0];
			tipoCarteiraAssociadaVO = new AdmTipoCarteiraVO[0];
			tipoCarteiraExistenteVO = new AdmTipoCarteiraVO[0];
			tipoSegmentacaoAssociadaVO = new AdmSegmentacaoVO[0];
			tipoSegmentacaoExistenteVO = new AdmSegmentacaoVO[0];

		}

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setArrayTipoPessoaAssociada(String[] arrayTipoPessoaAssociada) {
			this.arrayTipoPessoaAssociada = arrayTipoPessoaAssociada;
		}

		public String[] getArrayTipoPessoaAssociada() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayTipoPessoaAssociada == null || this.arrayTipoPessoaAssociada.length == 0) {
				this.arrayTipoPessoaAssociada = new String[1];
			}

			return this.arrayTipoPessoaAssociada;
		}

		public void setArrayTipoLinhaAssociada(String[] arrayTipoLinhaAssociada) {
			this.arrayTipoLinhaAssociada = arrayTipoLinhaAssociada;
		}

		public String[] getArrayTipoLinhaAssociada() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayTipoLinhaAssociada == null || this.arrayTipoLinhaAssociada.length == 0) {
				this.arrayTipoLinhaAssociada = new String[1];
			}

			return this.arrayTipoLinhaAssociada;
		}

		public void setArraySegmentacaoAssociada(String[] arraySegmentacaoAssociada) {
			this.arraySegmentacaoAssociada = arraySegmentacaoAssociada;
		}

		public String[] getArraySegmentacaoAssociada() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arraySegmentacaoAssociada == null || this.arraySegmentacaoAssociada.length == 0) {
				this.arraySegmentacaoAssociada = new String[1];
			}

			return this.arraySegmentacaoAssociada;
		}

		public void setArrayTipoCarteiraAssociada(String[] arrayTipoCarteiraAssociada) {
			this.arrayTipoCarteiraAssociada = arrayTipoCarteiraAssociada;
		}

		public String[] getArrayTipoCarteiraAssociada() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayTipoCarteiraAssociada == null || this.arrayTipoCarteiraAssociada.length == 0) {
				this.arrayTipoCarteiraAssociada = new String[1];
			}

			return this.arrayTipoCarteiraAssociada;
		}

		public void setTipoSegmentacaoExistenteVO(AdmSegmentacaoVO[] tipoSegmentacaoExistenteVO) {
			this.tipoSegmentacaoExistenteVO = tipoSegmentacaoExistenteVO;
		}

		public AdmSegmentacaoVO[] getTipoSegmentacaoExistenteVO() {
			return this.tipoSegmentacaoExistenteVO;
		}

		public void setTipoSegmentacaoAssociadaVO(AdmSegmentacaoVO[] tipoSegmentacaoAssociadaVO) {
			this.tipoSegmentacaoAssociadaVO = tipoSegmentacaoAssociadaVO;
		}

		public AdmSegmentacaoVO[] getTipoSegmentacaoAssociadaVO() {
			return this.tipoSegmentacaoAssociadaVO;
		}

		public void setTipoCarteiraExistenteVO(AdmTipoCarteiraVO[] tipoCarteiraExistenteVO) {
			this.tipoCarteiraExistenteVO = tipoCarteiraExistenteVO;
		}

		public AdmTipoCarteiraVO[] getTipoCarteiraExistenteVO() {
			return this.tipoCarteiraExistenteVO;
		}

		public void setTipoCarteiraAssociadaVO(AdmTipoCarteiraVO[] tipoCarteiraAssociadaVO) {
			this.tipoCarteiraAssociadaVO = tipoCarteiraAssociadaVO;
		}

		public AdmTipoCarteiraVO[] getTipoCarteiraAssociadaVO() {
			return this.tipoCarteiraAssociadaVO;
		}

		public void setTipoLinhaAssociadaVO(AdmTipoLinhaVO[] tipoLinhaAssociadaVO) {
			this.tipoLinhaAssociadaVO = tipoLinhaAssociadaVO;
		}

		public AdmTipoLinhaVO[] getTipoLinhaAssociadaVO() {
			return this.tipoLinhaAssociadaVO;
		}

		public void setTipoLinhaExistenteVO(AdmTipoLinhaVO[] tipoLinhaExistenteVO) {
			this.tipoLinhaExistenteVO = tipoLinhaExistenteVO;
		}

		public AdmTipoLinhaVO[] getTipoLinhaExistenteVO() {
			return this.tipoLinhaExistenteVO;
		}

		public void setTipoPessoaAssociadaVO(AdmTipoPessoaVO[] tipoPessoaAssociadaVO) {
			this.tipoPessoaAssociadaVO = tipoPessoaAssociadaVO;
		}

		public AdmTipoPessoaVO[] getTipoPessoaAssociadaVO() {
			return this.tipoPessoaAssociadaVO;
		}

		public void setTipoPessoaExistenteVO(AdmTipoPessoaVO[] tipoPessoaExistenteVO) {
			this.tipoPessoaExistenteVO = tipoPessoaExistenteVO;
		}

		public AdmTipoPessoaVO[] getTipoPessoaExistenteVO() {
			return this.tipoPessoaExistenteVO;
		}

		public void setA(String[] a) {
			this.a = a;
		}

		public String[] getA() {
			if (this.a == null || this.a.length == 0) {
				this.a = new String[1];
			}
			return this.a;
		}

		public void setB(String[] b) {
			this.b = b;
		}

		public String[] getB() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.b == null || this.b.length == 0) {
				this.b = new String[1];
			}

			return this.b;
		}

		public void setC(String[] c) {
			this.c = c;
		}

		public String[] getC() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.c == null || this.c.length == 0) {
				this.c = new String[1];
			}

			return this.c;
		}

		public void setD(String[] d) {
			this.d = d;
		}

		public String[] getD() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.d == null || this.d.length == 0) {
				this.d = new String[1];
			}

			return this.d;
		}

		public void setMsgError(String msgError) {
			this.msgError = msgError;
		}

		public String getMsgError() {
			return this.msgError;
		}
	}

	public FormFiltro getFormFiltro() {
		return this.formFiltro;
	}

}
