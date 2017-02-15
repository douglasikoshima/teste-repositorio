package admsistemas.admArvoreContato.workflow.abaRetorno;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.AdmCanalEntradaVODocument.AdmCanalEntradaVO;
import br.com.vivo.fo.admsistemas.vo.AdmCarteirizacaoVODocument.AdmCarteirizacaoVO;
import br.com.vivo.fo.admsistemas.vo.AdmGrupoVODocument.AdmGrupoVO;
import br.com.vivo.fo.admsistemas.vo.AdmIdContatoVODocument.AdmIdContatoVO;
import br.com.vivo.fo.admsistemas.vo.AdmPessoaVODocument.AdmPessoaVO;
import br.com.vivo.fo.admsistemas.vo.AdmProcedenciaVODocument.AdmProcedenciaVO;
import br.com.vivo.fo.admsistemas.vo.AdmRetornoContainerVODocument.AdmRetornoContainerVO;
import br.com.vivo.fo.admsistemas.vo.AdmSegmentacaoVODocument.AdmSegmentacaoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoClienteVODocument.AdmTipoClienteVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoLinhaVODocument.AdmTipoLinhaVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoRetornoVODocument.AdmTipoRetornoVO;
import br.com.vivo.fo.admsistemas.vo.AdmTipoUfOperadoraVODocument.AdmTipoUfOperadoraVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;

import com.indracompany.actions.AbstractAction;

public class AbaRetornoController extends AbstractAction {

	private static final long serialVersionUID = 183278975445308688L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.retorno.Retorno controlRetorno;

	private FormRetorno formRetorno;

	private String user = ConstantesCRM.SONE;

	private String idContato;

	private static transient Logger log = new Logger("admsistemas");

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("obtemRetorno".equals(mapping.getParameter())) {
			return obtemRetorno(mapping, form, request, response);
		} else if ("salvaRetorno".equals(mapping.getParameter())) {
			return salvaRetorno(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manutRetorno.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AbaRetornoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		user = ConstantesCRM.getCurrentUser(request.getSession());
		if ((user == null) || (user.length() == 0)) {
			user = ConstantesCRM.SONE;
		}

		formRetorno = new FormRetorno();
		if (request.getParameter("idContato") != null) {
			formRetorno.setIdContato(request.getParameter("idContato"));
		} else {
			formRetorno.setIdContato(ConstantesCRM.SONE);
		}

		this.idContato = formRetorno.getIdContato();

		log.debug("AbaRetornoController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " ) | idContato = " + this.idContato);

		AdmIdContatoVO admIdContatoVO = AdmIdContatoVO.Factory.newInstance();
		admIdContatoVO.setIdContato(formRetorno.getIdContato());
		// montaTela(controlRetorno.listaRetorno(admIdContatoVO,user));
		formRetorno.setAdmTipoRetornoVO(controlRetorno.listaTipoRetorno(admIdContatoVO, user).getListaTipoRetorno().getAdmTipoRetornoVOArray());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	protected void montaTela(AdmRetornoContainerVO admRetornoContainerVO) {

		formRetorno.setAdmCarteirizacaoAssociadaVO(admRetornoContainerVO.getAssociado().getAdmCarteirizacaoVOArray());
		formRetorno.setAdmCarteirizacaoExistenteVO(admRetornoContainerVO.getExistente().getAdmCarteirizacaoVOArray());
		formRetorno.setAdmPessoaAssociadaVO(admRetornoContainerVO.getAssociado().getAdmPessoaVOArray());
		formRetorno.setAdmPessoaExistenteVO(admRetornoContainerVO.getExistente().getAdmPessoaVOArray());
		formRetorno.setAdmProcedenciaAssociadaVO(admRetornoContainerVO.getAssociado().getAdmProcedenciaVOArray());
		formRetorno.setAdmProcedenciaExistenteVO(admRetornoContainerVO.getExistente().getAdmProcedenciaVOArray());
		formRetorno.setAdmSegmentacaoAssociadaVO(admRetornoContainerVO.getAssociado().getAdmSegmentacaoVOArray());
		formRetorno.setAdmSegmentacaoExistenteVO(admRetornoContainerVO.getExistente().getAdmSegmentacaoVOArray());
		formRetorno.setAdmTipoClienteAssociadoVO(admRetornoContainerVO.getAssociado().getAdmTipoClienteVOArray());
		formRetorno.setAdmTipoClienteExistenteVO(admRetornoContainerVO.getExistente().getAdmTipoClienteVOArray());
		formRetorno.setAdmTipoLinhaAssociadaVO(admRetornoContainerVO.getAssociado().getAdmTipoLinhaVOArray());
		formRetorno.setAdmTipoLinhaExistenteVO(admRetornoContainerVO.getExistente().getAdmTipoLinhaVOArray());
		formRetorno.setAdmTipoRetornoVO(admRetornoContainerVO.getListaTipoRetorno().getAdmTipoRetornoVOArray());
		formRetorno.setAdmCanalEntradaAssociadoVO(admRetornoContainerVO.getAssociado().getAdmCanalEntradaVOArray());
		formRetorno.setAdmCanalEntradaExistenteVO(admRetornoContainerVO.getExistente().getAdmCanalEntradaVOArray());
		formRetorno.setIdTipoRetornoAtivo(admRetornoContainerVO.getIdTipoRetornoAtivo());
		formRetorno.setAdmGrupoAssociadoVO(admRetornoContainerVO.getAssociado().getAdmGrupoVOArray());
		formRetorno.setAdmGrupoExistenteVO(admRetornoContainerVO.getExistente().getAdmGrupoVOArray());

		formRetorno.setAdmTipoUfOperadoraAssociadaVO(admRetornoContainerVO.getAssociado().getAdmTipoUfOperadoraVOArray());
		formRetorno.setAdmTipoUfOperadoraExistenteVO(admRetornoContainerVO.getExistente().getAdmTipoUfOperadoraVOArray());
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iRetorno.jsp"
	 */
	public ActionForward obtemRetorno(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AbaRetornoController:obtemRetorno" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormRetorno form = (FormRetorno) formParam;
		AdmRetornoContainerVO admRetornoContainerVO = AdmRetornoContainerVO.Factory.newInstance();
		admRetornoContainerVO.setIdTipoRetornoAtivo(form.getIdTipoRetornoAtivo());
		admRetornoContainerVO.setIdContato(this.idContato);
		montaTela(controlRetorno.listaRetorno(admRetornoContainerVO, user));
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="manutRetorno.jsp"
	 */
	public ActionForward salvaRetorno(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AbaRetornoController:salvaRetorno" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		FormRetorno form = (FormRetorno) formParam;
		AdmRetornoContainerVO admRetornoContainerVO = AdmRetornoContainerVO.Factory.newInstance();

		String tipoRetorno = form.getIdTipoRetornoAtivo();

		if (!tipoRetorno.equals(ConstantesCRM.SVAZIO)) {

			AdmPessoaVO[] admPessoaVO = new AdmPessoaVO[form.getArrayAdmPessoaAssociada().length];
			AdmTipoLinhaVO[] admTipoLinhaVO = new AdmTipoLinhaVO[form.getArrayAdmTipoLinhaAssociada().length];
			AdmCanalEntradaVO[] admCanalEntradaVO = new AdmCanalEntradaVO[form.getArrayAdmCanalEntradaAssociado().length];
			AdmProcedenciaVO[] admProcedenciaVO = new AdmProcedenciaVO[form.getArrayAdmProcedenciaAssociada().length];
			AdmTipoClienteVO[] admTipoClienteVO = new AdmTipoClienteVO[form.getArrayAdmTipoClienteAssociado().length];
			AdmGrupoVO[] admGrupoVO = new AdmGrupoVO[form.getArrayAdmGrupoAssociado().length];
			AdmSegmentacaoVO[] admSegmentacaoVO = new AdmSegmentacaoVO[form.getArraySegmentacaoAssociada().length];
			AdmCarteirizacaoVO[] admCarteirizacaoVO = new AdmCarteirizacaoVO[form.getArrayAdmCarteirizacaoAssociada().length];
			AdmTipoUfOperadoraVO[] admTipoUfOperadoraVO = new AdmTipoUfOperadoraVO[form.getArrayAdmTipoUfOperadoraAssociada().length];

			String[] pessoa = form.getArrayAdmPessoaAssociada();
			String[] linha = form.getArrayAdmTipoLinhaAssociada();
			String[] entrada = form.getArrayAdmCanalEntradaAssociado();
			String[] procedencia = form.getArrayAdmProcedenciaAssociada();
			String[] cliente = form.getArrayAdmTipoClienteAssociado();
			String[] grupo = form.getArrayAdmGrupoAssociado();
			String[] segmentacao = form.getArraySegmentacaoAssociada();
			String[] carteira = form.getArrayAdmCarteirizacaoAssociada();
			String[] tipoUf = form.getArrayAdmTipoUfOperadoraAssociada();

			for (int i = 0; i < pessoa.length; i++) {
				admPessoaVO[i] = AdmPessoaVO.Factory.newInstance();
				admPessoaVO[i].setIdPessoa(pessoa[i]);
			}

			for (int i = 0; i < linha.length; i++) {
				admTipoLinhaVO[i] = AdmTipoLinhaVO.Factory.newInstance();
				admTipoLinhaVO[i].setIdTipoLinha(linha[i]);
			}

			for (int i = 0; i < entrada.length; i++) {
				admCanalEntradaVO[i] = AdmCanalEntradaVO.Factory.newInstance();
				admCanalEntradaVO[i].setIdCanalEntrada(entrada[i]);
			}

			for (int i = 0; i < procedencia.length; i++) {
				admProcedenciaVO[i] = AdmProcedenciaVO.Factory.newInstance();
				admProcedenciaVO[i].setIdProcedencia(procedencia[i]);
			}

			for (int i = 0; i < cliente.length; i++) {
				admTipoClienteVO[i] = AdmTipoClienteVO.Factory.newInstance();
				admTipoClienteVO[i].setIdTipoCliente(cliente[i]);
			}

			for (int i = 0; i < grupo.length; i++) {
				admGrupoVO[i] = AdmGrupoVO.Factory.newInstance();
				admGrupoVO[i].setIdGrupo(grupo[i]);
			}

			for (int i = 0; i < segmentacao.length; i++) {
				admSegmentacaoVO[i] = AdmSegmentacaoVO.Factory.newInstance();
				admSegmentacaoVO[i].setIdSegmentacao(segmentacao[i]);
			}

			for (int i = 0; i < carteira.length; i++) {
				admCarteirizacaoVO[i] = AdmCarteirizacaoVO.Factory.newInstance();
				admCarteirizacaoVO[i].setIdCarteirizacao(carteira[i]);
			}

			for (int i = 0; i < tipoUf.length; i++) {
				admTipoUfOperadoraVO[i] = AdmTipoUfOperadoraVO.Factory.newInstance();
				admTipoUfOperadoraVO[i].setIdUfOperadora(tipoUf[i]);
			}

			admRetornoContainerVO.setIdContato(this.idContato);
			admRetornoContainerVO.setIdTipoRetornoAtivo(tipoRetorno);
			admRetornoContainerVO.addNewAssociado();
			admRetornoContainerVO.getAssociado().setAdmCanalEntradaVOArray(admCanalEntradaVO);
			admRetornoContainerVO.getAssociado().setAdmCarteirizacaoVOArray(admCarteirizacaoVO);
			admRetornoContainerVO.getAssociado().setAdmGrupoVOArray(admGrupoVO);
			admRetornoContainerVO.getAssociado().setAdmPessoaVOArray(admPessoaVO);
			admRetornoContainerVO.getAssociado().setAdmProcedenciaVOArray(admProcedenciaVO);
			admRetornoContainerVO.getAssociado().setAdmSegmentacaoVOArray(admSegmentacaoVO);
			admRetornoContainerVO.getAssociado().setAdmTipoClienteVOArray(admTipoClienteVO);
			admRetornoContainerVO.getAssociado().setAdmTipoLinhaVOArray(admTipoLinhaVO);
			admRetornoContainerVO.getAssociado().setAdmTipoUfOperadoraVOArray(admTipoUfOperadoraVO);
		} else {
			admRetornoContainerVO.setIdContato(formRetorno.getIdContato());
			admRetornoContainerVO.setIdTipoRetornoAtivo(tipoRetorno);
		}

		WFAcaoRetornoVO wfRetAcao = controlRetorno.salvaRetorno(admRetornoContainerVO, user);

		request.setAttribute("msgStatus", wfRetAcao.getMensagem());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class FormRetorno extends ActionForm {

		private static final long serialVersionUID = 9149091436933205725L;

		private String indicativo;

		private String[] arrayAdmGrupoExistente;

		private String[] arrayAdmGrupoAssociado;

		private AdmGrupoVO[] admGrupoAssociadoVO;

		private AdmGrupoVO[] admGrupoExistenteVO;

		private AdmCanalEntradaVO[] admCanalEntradaAssociadoVO;

		private AdmCanalEntradaVO[] admCanalEntradaExistenteVO;

		private String[] arrayAdmCanalEntradaAssociado;

		private String[] arrayAdmCanalEntradaExistente;

		private String[] arrayAdmCarteirizacaoExistente;

		private String[] arrayAdmCarteirizacaoAssociada;

		private AdmCarteirizacaoVO[] admCarteirizacaoAssociadaVO;

		private AdmCarteirizacaoVO[] admCarteirizacaoExistenteVO;

		private String[] arraySegmentacaoAssociada;

		private String[] arraySegmentacaoExistente;

		private AdmSegmentacaoVO[] admSegmentacaoAssociadaVO;

		private AdmSegmentacaoVO[] admSegmentacaoExistenteVO;

		private String[] arrayAdmTipoClienteAssociado;

		private String[] arrayAdmTipoClienteExistente;

		private AdmTipoClienteVO[] admTipoClienteAssociadoVO;

		private AdmTipoClienteVO[] admTipoClienteExistenteVO;

		private String[] arrayAdmProcedenciaAssociada;

		private String[] arrayAdmProcedenciaExistente;

		private AdmProcedenciaVO[] admProcedenciaAssociadaVO;

		private AdmProcedenciaVO[] admProcedenciaExistenteVO;

		private String[] arrayAdmTipoLinhaAssociada;

		private String[] arrayAdmTipoLinhaExistente;

		private AdmTipoLinhaVO[] admTipoLinhaAssociadaVO;

		private AdmTipoLinhaVO[] admTipoLinhaExistenteVO;

		private String[] arrayAdmPessoaAssociada;

		private String[] arrayAdmPessoaExistente;

		private AdmPessoaVO[] admPessoaAssociadaVO;

		private AdmPessoaVO[] admPessoaExistenteVO;

		private String[] arrayAdmTipoUfOperadoraAssociada;

		private String[] arrayAdmTipoUfOperadoraExistente;

		private AdmTipoUfOperadoraVO[] admTipoUfOperadoraAssociadaVO;

		private AdmTipoUfOperadoraVO[] admTipoUfOperadoraExistenteVO;

		private String[] arrayAdmTipoRetorno;

		private AdmTipoRetornoVO[] admTipoRetornoVO;

		private String idTipoRetornoAtivo;

		private String idContato;

		/**
		 * Construtor Default
		 */
		public FormRetorno() {
			admCanalEntradaAssociadoVO = new AdmCanalEntradaVO[] { AdmCanalEntradaVO.Factory.newInstance() };
			admCarteirizacaoAssociadaVO = new AdmCarteirizacaoVO[] { AdmCarteirizacaoVO.Factory.newInstance() };
			admGrupoAssociadoVO = new AdmGrupoVO[] { AdmGrupoVO.Factory.newInstance() };
			admPessoaAssociadaVO = new AdmPessoaVO[] { AdmPessoaVO.Factory.newInstance() };
			admProcedenciaAssociadaVO = new AdmProcedenciaVO[] { AdmProcedenciaVO.Factory.newInstance() };
			admSegmentacaoAssociadaVO = new AdmSegmentacaoVO[] { AdmSegmentacaoVO.Factory.newInstance() };
			admTipoClienteAssociadoVO = new AdmTipoClienteVO[] { AdmTipoClienteVO.Factory.newInstance() };
			admTipoLinhaAssociadaVO = new AdmTipoLinhaVO[] { AdmTipoLinhaVO.Factory.newInstance() };
			admTipoUfOperadoraAssociadaVO = new AdmTipoUfOperadoraVO[] { AdmTipoUfOperadoraVO.Factory.newInstance() };

			admCanalEntradaExistenteVO = new AdmCanalEntradaVO[] { AdmCanalEntradaVO.Factory.newInstance() };
			admCarteirizacaoExistenteVO = new AdmCarteirizacaoVO[] { AdmCarteirizacaoVO.Factory.newInstance() };
			admGrupoExistenteVO = new AdmGrupoVO[] { AdmGrupoVO.Factory.newInstance() };
			admPessoaExistenteVO = new AdmPessoaVO[] { AdmPessoaVO.Factory.newInstance() };
			admProcedenciaExistenteVO = new AdmProcedenciaVO[] { AdmProcedenciaVO.Factory.newInstance() };
			admSegmentacaoExistenteVO = new AdmSegmentacaoVO[] { AdmSegmentacaoVO.Factory.newInstance() };
			admTipoClienteExistenteVO = new AdmTipoClienteVO[] { AdmTipoClienteVO.Factory.newInstance() };
			admTipoLinhaExistenteVO = new AdmTipoLinhaVO[] { AdmTipoLinhaVO.Factory.newInstance() };
			// formRetorno.setIdTipoRetornoAtivo(admRetornoContainerVO.getIdTipoRetornoAtivo());
			admTipoUfOperadoraExistenteVO = new AdmTipoUfOperadoraVO[] { AdmTipoUfOperadoraVO.Factory.newInstance() };

		}

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setIdTipoRetornoAtivo(String idTipoRetornoAtivo) {
			this.idTipoRetornoAtivo = idTipoRetornoAtivo;
		}

		public String getIdTipoRetornoAtivo() {
			return this.idTipoRetornoAtivo;
		}

		public void setAdmTipoRetornoVO(AdmTipoRetornoVO[] admTipoRetornoVO) {
			this.admTipoRetornoVO = admTipoRetornoVO;
		}

		public AdmTipoRetornoVO[] getAdmTipoRetornoVO() {
			return this.admTipoRetornoVO;
		}

		public void setArrayAdmTipoRetorno(String[] arrayAdmTipoRetorno) {
			this.arrayAdmTipoRetorno = arrayAdmTipoRetorno;
		}

		public String[] getArrayAdmTipoRetorno() {
			if (this.arrayAdmTipoRetorno == null || this.arrayAdmTipoRetorno.length == 0) {
				this.arrayAdmTipoRetorno = new String[1];
			}
			return this.arrayAdmTipoRetorno;
		}

		public void setArrayAdmTipoUfOperadoraExistente(String[] arrayAdmTipoUfOperadoraExistente) {
			this.arrayAdmTipoUfOperadoraExistente = arrayAdmTipoUfOperadoraExistente;
		}

		public String[] getArrayAdmTipoUfOperadoraExistente() {
			if (this.arrayAdmTipoUfOperadoraExistente == null || this.arrayAdmTipoUfOperadoraExistente.length == 0) {
				this.arrayAdmTipoUfOperadoraExistente = new String[1];
			}
			return this.arrayAdmTipoUfOperadoraExistente;
		}

		public void setArrayAdmTipoUfOperadoraAssociada(String[] arrayAdmTipoUfOperadoraAssociada) {
			this.arrayAdmTipoUfOperadoraAssociada = arrayAdmTipoUfOperadoraAssociada;
		}

		public String[] getArrayAdmTipoUfOperadoraAssociada() {
			if (this.arrayAdmTipoUfOperadoraAssociada == null || this.arrayAdmTipoUfOperadoraAssociada.length == 0) {
				this.arrayAdmTipoUfOperadoraAssociada = new String[1];
			}
			return this.arrayAdmTipoUfOperadoraAssociada;
		}

		public void setAdmPessoaExistenteVO(AdmPessoaVO[] admPessoaExistenteVO) {
			this.admPessoaExistenteVO = admPessoaExistenteVO;
		}

		public AdmPessoaVO[] getAdmPessoaExistenteVO() {
			return this.admPessoaExistenteVO;
		}

		public void setAdmPessoaAssociadaVO(AdmPessoaVO[] admPessoaAssociadaVO) {
			this.admPessoaAssociadaVO = admPessoaAssociadaVO;
		}

		public AdmPessoaVO[] getAdmPessoaAssociadaVO() {
			return this.admPessoaAssociadaVO;
		}

		public void setArrayAdmPessoaExistente(String[] arrayAdmPessoaExistente) {
			this.arrayAdmPessoaExistente = arrayAdmPessoaExistente;
		}

		public String[] getArrayAdmPessoaExistente() {
			if (this.arrayAdmPessoaExistente == null || this.arrayAdmPessoaExistente.length == 0) {
				this.arrayAdmPessoaExistente = new String[1];
			}
			return this.arrayAdmPessoaExistente;
		}

		public void setArrayAdmPessoaAssociada(String[] arrayAdmPessoaAssociada) {
			this.arrayAdmPessoaAssociada = arrayAdmPessoaAssociada;
		}

		public String[] getArrayAdmPessoaAssociada() {
			if (this.arrayAdmPessoaAssociada == null || this.arrayAdmPessoaAssociada.length == 0) {
				this.arrayAdmPessoaAssociada = new String[1];
			}
			return this.arrayAdmPessoaAssociada;
		}

		public void setAdmTipoUfOperadoraExistenteVO(AdmTipoUfOperadoraVO[] admTipoUfOperadoraExistenteVO) {
			this.admTipoUfOperadoraExistenteVO = admTipoUfOperadoraExistenteVO;
		}

		public AdmTipoUfOperadoraVO[] getAdmTipoUfOperadoraExistenteVO() {
			return this.admTipoUfOperadoraExistenteVO;
		}

		public void setAdmTipoUfOperadoraAssociadaVO(AdmTipoUfOperadoraVO[] admTipoUfOperadoraAssociadaVO) {
			this.admTipoUfOperadoraAssociadaVO = admTipoUfOperadoraAssociadaVO;
		}

		public AdmTipoUfOperadoraVO[] getAdmTipoUfOperadoraAssociadaVO() {
			return this.admTipoUfOperadoraAssociadaVO;
		}

		public void setAdmTipoLinhaExistenteVO(AdmTipoLinhaVO[] admTipoLinhaExistenteVO) {
			this.admTipoLinhaExistenteVO = admTipoLinhaExistenteVO;
		}

		public AdmTipoLinhaVO[] getAdmTipoLinhaExistenteVO() {
			return this.admTipoLinhaExistenteVO;
		}

		public void setAdmTipoLinhaAssociadaVO(AdmTipoLinhaVO[] admTipoLinhaAssociadaVO) {
			this.admTipoLinhaAssociadaVO = admTipoLinhaAssociadaVO;
		}

		public AdmTipoLinhaVO[] getAdmTipoLinhaAssociadaVO() {
			return this.admTipoLinhaAssociadaVO;
		}

		public void setArrayAdmTipoLinhaExistente(String[] arrayAdmTipoLinhaExistente) {
			this.arrayAdmTipoLinhaExistente = arrayAdmTipoLinhaExistente;
		}

		public String[] getArrayAdmTipoLinhaExistente() {
			if (this.arrayAdmTipoLinhaExistente == null || this.arrayAdmTipoLinhaExistente.length == 0) {
				this.arrayAdmTipoLinhaExistente = new String[1];
			}

			return this.arrayAdmTipoLinhaExistente;
		}

		public void setArrayAdmTipoLinhaAssociada(String[] arrayAdmTipoLinhaAssociada) {
			this.arrayAdmTipoLinhaAssociada = arrayAdmTipoLinhaAssociada;
		}

		public String[] getArrayAdmTipoLinhaAssociada() {
			if (this.arrayAdmTipoLinhaAssociada == null || this.arrayAdmTipoLinhaAssociada.length == 0) {
				this.arrayAdmTipoLinhaAssociada = new String[1];
			}

			return this.arrayAdmTipoLinhaAssociada;
		}

		public void setAdmProcedenciaExistenteVO(AdmProcedenciaVO[] admProcedenciaExistenteVO) {
			this.admProcedenciaExistenteVO = admProcedenciaExistenteVO;
		}

		public AdmProcedenciaVO[] getAdmProcedenciaExistenteVO() {
			return this.admProcedenciaExistenteVO;
		}

		public void setAdmProcedenciaAssociadaVO(AdmProcedenciaVO[] admProcedenciaAssociadaVO) {
			this.admProcedenciaAssociadaVO = admProcedenciaAssociadaVO;
		}

		public AdmProcedenciaVO[] getAdmProcedenciaAssociadaVO() {
			return this.admProcedenciaAssociadaVO;
		}

		public void setArrayAdmProcedenciaExistente(String[] arrayAdmProcedenciaExistente) {
			this.arrayAdmProcedenciaExistente = arrayAdmProcedenciaExistente;
		}

		public String[] getArrayAdmProcedenciaExistente() {
			if (this.arrayAdmProcedenciaExistente == null || this.arrayAdmProcedenciaExistente.length == 0) {
				this.arrayAdmProcedenciaExistente = new String[1];
			}

			return this.arrayAdmProcedenciaExistente;
		}

		public void setArrayAdmProcedenciaAssociada(String[] arrayAdmProcedenciaAssociada){
			this.arrayAdmProcedenciaAssociada = arrayAdmProcedenciaAssociada;
		}

		public String[] getArrayAdmProcedenciaAssociada() {
			if (this.arrayAdmProcedenciaAssociada == null || this.arrayAdmProcedenciaAssociada.length == 0) {
				this.arrayAdmProcedenciaAssociada = new String[1];
			}

			return this.arrayAdmProcedenciaAssociada;
		}

		public void setAdmTipoClienteExistenteVO(AdmTipoClienteVO[] admTipoClienteExistenteVO) {
			this.admTipoClienteExistenteVO = admTipoClienteExistenteVO;
		}

		public AdmTipoClienteVO[] getAdmTipoClienteExistenteVO() {
			return this.admTipoClienteExistenteVO;
		}

		public void setAdmTipoClienteAssociadoVO(AdmTipoClienteVO[] admTipoClienteAssociadoVO) {
			this.admTipoClienteAssociadoVO = admTipoClienteAssociadoVO;
		}

		public AdmTipoClienteVO[] getAdmTipoClienteAssociadoVO() {
			return this.admTipoClienteAssociadoVO;
		}

		public void setArrayAdmTipoClienteExistente(String[] arrayAdmTipoClienteExistente) {
			this.arrayAdmTipoClienteExistente = arrayAdmTipoClienteExistente;
		}

		public String[] getArrayAdmTipoClienteExistente() {
			if (this.arrayAdmTipoClienteExistente == null || this.arrayAdmTipoClienteExistente.length == 0) {
				this.arrayAdmTipoClienteExistente = new String[1];
			}
			return this.arrayAdmTipoClienteExistente;
		}

		public void setArrayAdmTipoClienteAssociado(String[] arrayAdmTipoClienteAssociado) {
			this.arrayAdmTipoClienteAssociado = arrayAdmTipoClienteAssociado;
		}

		public String[] getArrayAdmTipoClienteAssociado() {
			if (this.arrayAdmTipoClienteAssociado == null || this.arrayAdmTipoClienteAssociado.length == 0) {
				this.arrayAdmTipoClienteAssociado = new String[1];
			}
			return this.arrayAdmTipoClienteAssociado;
		}

		public void setAdmSegmentacaoExistenteVO(AdmSegmentacaoVO[] admSegmentacaoExistenteVO) {
			this.admSegmentacaoExistenteVO = admSegmentacaoExistenteVO;
		}

		public AdmSegmentacaoVO[] getAdmSegmentacaoExistenteVO() {
			return this.admSegmentacaoExistenteVO;
		}

		public void setAdmSegmentacaoAssociadaVO(AdmSegmentacaoVO[] admSegmentacaoAssociadaVO) {
			this.admSegmentacaoAssociadaVO = admSegmentacaoAssociadaVO;
		}

		public AdmSegmentacaoVO[] getAdmSegmentacaoAssociadaVO() {
			return this.admSegmentacaoAssociadaVO;
		}

		public void setArraySegmentacaoExistente(String[] arraySegmentacaoExistente) {
			this.arraySegmentacaoExistente = arraySegmentacaoExistente;
		}

		public String[] getArraySegmentacaoExistente() {
			if (this.arraySegmentacaoExistente == null || this.arraySegmentacaoExistente.length == 0) {
				this.arraySegmentacaoExistente = new String[1];
			}

			return this.arraySegmentacaoExistente;
		}

		public void setArraySegmentacaoAssociada(String[] arraySegmentacaoAssociada) {
			this.arraySegmentacaoAssociada = arraySegmentacaoAssociada;
		}

		public String[] getArraySegmentacaoAssociada() {
			if (this.arraySegmentacaoAssociada == null || this.arraySegmentacaoAssociada.length == 0) {
				this.arraySegmentacaoAssociada = new String[1];
			}

			return this.arraySegmentacaoAssociada;
		}

		public void setAdmCarteirizacaoExistenteVO(AdmCarteirizacaoVO[] admCarteirizacaoExistenteVO) {
			this.admCarteirizacaoExistenteVO = admCarteirizacaoExistenteVO;
		}

		public AdmCarteirizacaoVO[] getAdmCarteirizacaoExistenteVO() {
			return this.admCarteirizacaoExistenteVO;
		}

		public void setAdmCarteirizacaoAssociadaVO(AdmCarteirizacaoVO[] admCarteirizacaoAssociadaVO) {
			this.admCarteirizacaoAssociadaVO = admCarteirizacaoAssociadaVO;
		}

		public AdmCarteirizacaoVO[] getAdmCarteirizacaoAssociadaVO() {
			return this.admCarteirizacaoAssociadaVO;
		}

		public void setArrayAdmCarteirizacaoAssociada(String[] arrayAdmCarteirizacaoAssociada) {
			this.arrayAdmCarteirizacaoAssociada = arrayAdmCarteirizacaoAssociada;
		}

		public String[] getArrayAdmCarteirizacaoAssociada() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayAdmCarteirizacaoAssociada == null || this.arrayAdmCarteirizacaoAssociada.length == 0) {
				this.arrayAdmCarteirizacaoAssociada = new String[1];
			}

			return this.arrayAdmCarteirizacaoAssociada;
		}

		public void setArrayAdmCarteirizacaoExistente(String[] arrayAdmCarteirizacaoExistente) {
			this.arrayAdmCarteirizacaoExistente = arrayAdmCarteirizacaoExistente;
		}

		public String[] getArrayAdmCarteirizacaoExistente() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayAdmCarteirizacaoExistente == null || this.arrayAdmCarteirizacaoExistente.length == 0) {
				this.arrayAdmCarteirizacaoExistente = new String[1];
			}

			return this.arrayAdmCarteirizacaoExistente;
		}

		public void setArrayAdmCanalEntradaExistente(String[] arrayAdmCanalEntradaExistente) {
			this.arrayAdmCanalEntradaExistente = arrayAdmCanalEntradaExistente;
		}

		public String[] getArrayAdmCanalEntradaExistente() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayAdmCanalEntradaExistente == null || this.arrayAdmCanalEntradaExistente.length == 0) {
				this.arrayAdmCanalEntradaExistente = new String[1];
			}

			return this.arrayAdmCanalEntradaExistente;
		}

		public void setArrayAdmCanalEntradaAssociado(String[] arrayAdmCanalEntradaAssociado) {
			this.arrayAdmCanalEntradaAssociado = arrayAdmCanalEntradaAssociado;
		}

		public String[] getArrayAdmCanalEntradaAssociado() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayAdmCanalEntradaAssociado == null || this.arrayAdmCanalEntradaAssociado.length == 0) {
				this.arrayAdmCanalEntradaAssociado = new String[1];
			}

			return this.arrayAdmCanalEntradaAssociado;
		}

		public void setAdmCanalEntradaExistenteVO(AdmCanalEntradaVO[] admCanalEntradaExistenteVO) {
			this.admCanalEntradaExistenteVO = admCanalEntradaExistenteVO;
		}

		public AdmCanalEntradaVO[] getAdmCanalEntradaExistenteVO() {
			return this.admCanalEntradaExistenteVO;
		}

		public void setAdmCanalEntradaAssociadoVO(AdmCanalEntradaVO[] admCanalEntradaAssociadoVO) {
			this.admCanalEntradaAssociadoVO = admCanalEntradaAssociadoVO;
		}

		public AdmCanalEntradaVO[] getAdmCanalEntradaAssociadoVO() {
			return this.admCanalEntradaAssociadoVO;
		}

		public void setAdmGrupoExistenteVO(AdmGrupoVO[] admGrupoExistenteVO) {
			this.admGrupoExistenteVO = admGrupoExistenteVO;
		}

		public AdmGrupoVO[] getAdmGrupoExistenteVO() {
			return this.admGrupoExistenteVO;
		}

		public void setAdmGrupoAssociadoVO(AdmGrupoVO[] admGrupoAssociadoVO) {
			this.admGrupoAssociadoVO = admGrupoAssociadoVO;
		}

		public AdmGrupoVO[] getAdmGrupoAssociadoVO() {
			return this.admGrupoAssociadoVO;
		}

		public void setArrayAdmGrupoAssociado(String[] arrayAdmGrupoAssociado) {
			this.arrayAdmGrupoAssociado = arrayAdmGrupoAssociado;
		}

		public String[] getArrayAdmGrupoAssociado() {
			if (this.arrayAdmGrupoAssociado == null || this.arrayAdmGrupoAssociado.length == 0) {
				this.arrayAdmGrupoAssociado = new String[1];
			}

			return this.arrayAdmGrupoAssociado;
		}

		public void setArrayAdmGrupoExistente(String[] arrayAdmGrupoExistente) {
			this.arrayAdmGrupoExistente = arrayAdmGrupoExistente;
		}

		public String[] getArrayAdmGrupoExistente() {
			// For data binding to be able to post data back, complex types and
			// arrays must be initialized to be non-null.
			if (this.arrayAdmGrupoExistente == null || this.arrayAdmGrupoExistente.length == 0) {
				this.arrayAdmGrupoExistente = new String[1];
			}

			return this.arrayAdmGrupoExistente;
		}

		public void setIndicativo(String indicativo) {
			this.indicativo = indicativo;
		}

		public String getIndicativo() {
			return this.indicativo;
		}
	}

	public FormRetorno getFormRetorno() {
		return this.formRetorno;
	}
}
