package admsistemas.admArvoreContato.workflow.GrupoUsuarioRegrasEncaminhamento;

import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.CarterizacaoVODocument.CarterizacaoVO;
import br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO;
import br.com.vivo.fo.admsistemas.vo.ProcedenciaVODocument.ProcedenciaVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoDisponivelVODocument.RegrasEncaminhamentoDisponivelVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoSelecionadoVODocument.RegrasEncaminhamentoSelecionadoVO;
import br.com.vivo.fo.admsistemas.vo.RegrasEncaminhamentoVODocument.RegrasEncaminhamentoVO;
import br.com.vivo.fo.admsistemas.vo.SegmentacaoVODocument.SegmentacaoVO;
import br.com.vivo.fo.admsistemas.vo.TipoClienteVODocument.TipoClienteVO;
import br.com.vivo.fo.admsistemas.vo.impl.CarterizacaoVODocumentImpl.CarterizacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.GrupoVODocumentImpl.GrupoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.ProcedenciaVODocumentImpl.ProcedenciaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.SegmentacaoVODocumentImpl.SegmentacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.TipoClienteVODocumentImpl.TipoClienteVOImpl;
import br.com.vivo.fo.cliente.vo.TipoLinhaVODocument.TipoLinhaVO;
import br.com.vivo.fo.cliente.vo.impl.TipoLinhaVODocumentImpl.TipoLinhaVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.usuario.manterGrupo.ManterGrupoFacade;
import br.com.vivo.fo.fidelizacao.vo.TipoPessoaVODocument.TipoPessoaVO;
import br.com.vivo.fo.fidelizacao.vo.impl.TipoPessoaVODocumentImpl.TipoPessoaVOImpl;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.CanalVODocument.CanalVO;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;
import br.com.vivo.fo.workflow.vo.impl.CanalVODocumentImpl.CanalVOImpl;

import com.indracompany.actions.AbstractAction;

public class GrupoUsuarioRegrasEncaminhamentoController extends AbstractAction {

	private static final long serialVersionUID = 7548731818531223407L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.configGruposProcessos.GruposProcessosFacade gruposProcessosFacade;

	@EJB
	private ManterGrupoFacade controlGrupo;

	protected global.Global globalApp = new global.Global();

	// variáveis e objetos
	private String user = ConstantesCRM.SVAZIO;

	private UsuarioEncaminhamentoForm usuarioEncaminhamentoForm = null;

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("ObtemRegrasEncaminhamento".equals(mapping.getParameter())) {
			return ObtemRegrasEncaminhamento(mapping, form, request, response);
		} else if ("AplicarRegrasEncaminhamento".equals(mapping.getParameter())) {
			return AplicarRegrasEncaminhamento(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RegrasEncaminhamento.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("GrupoUsuarioRegrasEncaminhamentoController:begin(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		UsuarioEncaminhamentoForm form = (UsuarioEncaminhamentoForm) formParam;
		String idContato = (request.getParameter("idContato") != null) ? request.getParameter("idContato") : ConstantesCRM.SVAZIO;
		String flgFrame = (form.getFlgFrame() != null) ? form.getFlgFrame() : (String) request.getAttribute("flgFrame");

		user = ConstantesCRM.getCurrentUser(request.getSession());
		if ((user == null) || (user.length() == 0)) {
			user = ConstantesCRM.SONE;
		}

		// Aponta para o formulário
		usuarioEncaminhamentoForm = form;
		
		log.debug("GrupoUsuarioRegrasEncaminhamentoController:Flgframe(" + flgFrame + ")");
		log.debug("GrupoUsuarioRegrasEncaminhamentoController:idContato(" + idContato + ")");

		if ((form.getFlgFrame() != null && form.getFlgFrame().trim().equals("frame")) && !idContato.equals(ConstantesCRM.SVAZIO)) {
			usuarioEncaminhamentoForm.setAGrupoVO(gruposProcessosFacade.getGruposProcessos(user, idContato, ConstantesCRM.SONE, "T").getGruposTratamento().getGrupoProcessoVO().getGrupoVOArray());
		} else {
			// Obtem a lista de grupos
			usuarioEncaminhamentoForm.setAGrupoVO(gruposProcessosFacade.getAllGruposProcessos(user).getGrupoVOArray());
		}

		form.setFlgFrame(flgFrame);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iObtemRegrasEncaminhamento.jsp"
	 */
	public ActionForward ObtemRegrasEncaminhamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("GrupoUsuarioRegrasEncaminhamentoController:ObtemRegrasEncaminhamento(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		UsuarioEncaminhamentoForm form = (UsuarioEncaminhamentoForm) formParam;
		// Aponta para o formulário
		usuarioEncaminhamentoForm = form;

		user = ConstantesCRM.getCurrentUser(request.getSession());
		// Obtem os elementos selecionados e disponíveis
		Integer codigoGrupo = usuarioEncaminhamentoForm.getCodigoGrupo();
		usuarioEncaminhamentoForm.setRegrasEncaminhamentoVO(gruposProcessosFacade.getRegrasEncaminhamento(user, codigoGrupo));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Resultado.jsp"
	 */
	public ActionForward AplicarRegrasEncaminhamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("GrupoUsuarioRegrasEncaminhamentoController:AplicarRegrasEncaminhamento(" + ConstantesCRM.getCurrentUser(request.getSession()) + ")");
		UsuarioEncaminhamentoForm form = (UsuarioEncaminhamentoForm) formParam;
		// Definições
		HashMap<String, Object> retorno = new HashMap<String, Object>();
		String valida = request.getParameter("valida");

		usuarioEncaminhamentoForm = form;

		// Monta os elementos para montagem no control
		retorno.put(ConstantesCRM.CT_GrupoVO, usuarioEncaminhamentoForm.getCodigoGrupo());
		retorno.put(ConstantesCRM.CT_TipoLinhaVO, usuarioEncaminhamentoForm.getTipoLinhaAssociada());
		retorno.put(ConstantesCRM.CT_SegmentacaoVO, usuarioEncaminhamentoForm.getSegmentacaoAssociada());
		retorno.put(ConstantesCRM.CT_CarterizacaoVO, usuarioEncaminhamentoForm.getCarterizacaoAssociada());
		retorno.put(ConstantesCRM.CT_ProcedenciaVO, usuarioEncaminhamentoForm.getProcedenciaAssociada());
		retorno.put(ConstantesCRM.CT_TipoClienteVO, usuarioEncaminhamentoForm.getTipoClienteAssociada());
		retorno.put(ConstantesCRM.CT_TipoPessoaVO, usuarioEncaminhamentoForm.getTipoPessoaAssociada());
		retorno.put(ConstantesCRM.CT_TipoAberturaVO, usuarioEncaminhamentoForm.getTipoAberturaAssociada());
		retorno.put(ConstantesCRM.CT_CanalVO, usuarioEncaminhamentoForm.getCanalAssociado());
		retorno.put(ConstantesCRM.CT_RegionalVO, usuarioEncaminhamentoForm.getRegionalAssociada());

		user = ConstantesCRM.getCurrentUser(request.getSession());

		// Invoca servico ENCAMINHIST para efeito de log
		controlGrupo.setHistoricoConfigVariaveis(user, (String) request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN), usuarioEncaminhamentoForm.getCodigoGrupo().toString());

		// Processa a criação/atualização
		WFAcaoRetornoVO acaoRetorno = null;

		if (valida != null && valida.equals(ConstantesCRM.SZERO)) {
			acaoRetorno = gruposProcessosFacade.setRegrasEncaminhamentoValidaSkill(user, retorno, ConstantesCRM.SZERO);
		} else {
			acaoRetorno = gruposProcessosFacade.setRegrasEncaminhamentoValidaSkill(user, retorno, ConstantesCRM.SONE);
		}

		usuarioEncaminhamentoForm.setIdRetorno(acaoRetorno.getAcaoExecucao());
		usuarioEncaminhamentoForm.setMensagem(acaoRetorno.getMensagem());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class UsuarioEncaminhamentoForm extends ActionForm {

		private static final long serialVersionUID = -6694750897115547674L;

		private String[] regionalDisponivel;
		private String[] regionalAssociada;
		private String flgFrame;
		private String[] canalAssociado;
		private String[] canalDisponivel;
		private Integer codigoGrupo = null;
		private GrupoVO[] aGrupoVO = null;
		private GrupoVO grupoVOSelecionado = null;
		private String[] tipoLinhaDisponivel;
		private String[] segmentacaoDisponivel;
		private String[] carterizacaoDisponivel;
		private String[] procedenciaDisponivel;
		private String[] tipoLinhaAssociada;
		private String[] segmentacaoAssociada;
		private String[] carterizacaoAssociada;
		private String[] procedenciaAssociada;

		// Novas regras
		private String[] tipoClienteDisponivel;
		private String[] tipoClienteAssociada;
		private String[] tipoPessoaDisponivel;
		private String[] tipoPessoaAssociada;
		private String[] tipoAberturaDisponivel;
		private String[] tipoAberturaAssociada;

		// Para WFacaoRetorno
		private String idRetorno;
		private String mensagem;

		private RegrasEncaminhamentoVO regrasEncaminhamentoVO = null;

		public UsuarioEncaminhamentoForm() {
			tipoLinhaDisponivel = new String[0];
			tipoLinhaAssociada = new String[0];
			segmentacaoDisponivel = new String[0];
			segmentacaoAssociada = new String[0];
			carterizacaoDisponivel = new String[0];
			carterizacaoAssociada = new String[0];
			procedenciaDisponivel = new String[0];
			procedenciaAssociada = new String[0];

			// Novas regras
			tipoClienteDisponivel = new String[0];
			tipoClienteAssociada = new String[0];
			tipoPessoaDisponivel = new String[0];
			tipoPessoaAssociada = new String[0];
			tipoAberturaDisponivel = new String[0];
			tipoAberturaAssociada = new String[0];
			canalAssociado = new String[0];
			canalDisponivel = new String[0];
			regionalAssociada = new String[0];
			regionalDisponivel = new String[0];

			this.grupoVOSelecionado = GrupoVO.Factory.newInstance();

			TipoLinhaVO[] tipoLinha = new TipoLinhaVOImpl[0];
			SegmentacaoVO[] segmentacao = new SegmentacaoVOImpl[0];
			CarterizacaoVO[] carterizacao = new CarterizacaoVOImpl[0];
			ProcedenciaVO[] procedencia = new ProcedenciaVOImpl[0];

			TipoClienteVO[] tipoCliente = new TipoClienteVOImpl[0];
			TipoPessoaVO[] tipoPessoa = new TipoPessoaVOImpl[0];
			GrupoVO[] tipoAbertura = new GrupoVOImpl[0];
			CanalVO[] canal = new CanalVOImpl[0];

			this.regrasEncaminhamentoVO = RegrasEncaminhamentoVO.Factory.newInstance();
			this.regrasEncaminhamentoVO.setRegrasEncaminhamentoDisponivelVO(RegrasEncaminhamentoDisponivelVO.Factory.newInstance());
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setTipoLinhaVOArray(tipoLinha);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setSegmentacaoVOArray(segmentacao);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setCarterizacaoVOArray(carterizacao);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setProcedenciaVOArray(procedencia);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setTipoClienteVOArray(tipoCliente);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setTipoPessoaVOArray(tipoPessoa);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setGrupoVOArray(tipoAbertura);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setCanalVOArray(canal);

			this.regrasEncaminhamentoVO.setRegrasEncaminhamentoSelecionadoVO(RegrasEncaminhamentoSelecionadoVO.Factory.newInstance());
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setTipoLinhaVOArray(tipoLinha);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setSegmentacaoVOArray(segmentacao);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setCarterizacaoVOArray(carterizacao);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setProcedenciaVOArray(procedencia);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setTipoClienteVOArray(tipoCliente);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setTipoPessoaVOArray(tipoPessoa);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setGrupoVOArray(tipoAbertura);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setCanalVOArray(canal);
		}

		public Integer getCodigoGrupo() {
			return this.codigoGrupo;
		}

		public void setCodigoGrupo(Integer codigoGrupo) {
			this.codigoGrupo = codigoGrupo;
		}

		public GrupoVO[] getAGrupoVO() {
			return this.aGrupoVO;
		}

		public void setAGrupoVO(GrupoVO[] grupos) {
			this.aGrupoVO = grupos;
		}

		public GrupoVO getGrupoVOSelecionado() {
			return this.grupoVOSelecionado;
		}

		public void setGrupoVOSelecionado(GrupoVO grupoSelecionado) {
			this.grupoVOSelecionado = grupoSelecionado;
		}

		public RegrasEncaminhamentoVO getRegrasEncaminhamentoVO() {
			return this.regrasEncaminhamentoVO;
		}

		public void setRegrasEncaminhamentoVO(RegrasEncaminhamentoVO nRegrasEncaminhamentoVO) {
			this.regrasEncaminhamentoVO = nRegrasEncaminhamentoVO;
		}

		public String[] getCarterizacaoAssociada() {
			return carterizacaoAssociada;
		}

		public String[] getCarterizacaoDisponivel() {
			return carterizacaoDisponivel;
		}

		public String[] getProcedenciaAssociada() {
			return procedenciaAssociada;
		}

		public String[] getProcedenciaDisponivel() {
			return procedenciaDisponivel;
		}

		public String[] getSegmentacaoAssociada() {
			return segmentacaoAssociada;
		}

		public String[] getSegmentacaoDisponivel() {
			return segmentacaoDisponivel;
		}

		public String[] getTipoLinhaAssociada() {
			return tipoLinhaAssociada;
		}

		public String[] getTipoLinhaDisponivel() {
			return tipoLinhaDisponivel;
		}

		public void setCarterizacaoAssociada(String[] strings) {
			carterizacaoAssociada = strings;
		}

		public void setCarterizacaoDisponivel(String[] strings) {
			carterizacaoDisponivel = strings;
		}

		public void setProcedenciaAssociada(String[] strings) {
			procedenciaAssociada = strings;
		}

		public void setProcedenciaDisponivel(String[] strings) {
			procedenciaDisponivel = strings;
		}

		public void setSegmentacaoAssociada(String[] strings) {
			segmentacaoAssociada = strings;
		}

		public void setSegmentacaoDisponivel(String[] strings) {
			segmentacaoDisponivel = strings;
		}

		public void setTipoLinhaAssociada(String[] strings) {
			tipoLinhaAssociada = strings;
		}

		public void setTipoLinhaDisponivel(String[] strings) {
			tipoLinhaDisponivel = strings;
		}

		public void setTipoClienteDisponivel(String[] tipoClienteDisponivel) {
			this.tipoClienteDisponivel = tipoClienteDisponivel;
		}

		public String[] getTipoClienteDisponivel() {
			return this.tipoClienteDisponivel;
		}

		public void setTipoClienteAssociada(String[] tipoClienteAssociada) {
			this.tipoClienteAssociada = tipoClienteAssociada;
		}

		public String[] getTipoClienteAssociada() {
			return this.tipoClienteAssociada;
		}

		public void setTipoPessoaDisponivel(String[] tipoPessoaDisponivel) {
			this.tipoPessoaDisponivel = tipoPessoaDisponivel;
		}

		public String[] getTipoPessoaDisponivel() {
			return this.tipoPessoaDisponivel;
		}

		public void setTipoPessoaAssociada(String[] tipoPessoaAssociada) {
			this.tipoPessoaAssociada = tipoPessoaAssociada;
		}

		public String[] getTipoPessoaAssociada() {
			return this.tipoPessoaAssociada;
		}

		public void setTipoAberturaDisponivel(String[] tipoAberturaDisponivel) {
			this.tipoAberturaDisponivel = tipoAberturaDisponivel;
		}

		public String[] getTipoAberturaDisponivel() {
			return this.tipoAberturaDisponivel;
		}

		public void setTipoAberturaAssociada(String[] tipoAberturaAssociada) {
			this.tipoAberturaAssociada = tipoAberturaAssociada;
		}

		public String[] getTipoAberturaAssociada() {
			return this.tipoAberturaAssociada;
		}

		public void setCanalDisponivel(String[] canalDisponivel) {
			this.canalDisponivel = canalDisponivel;
		}

		public String[] getCanalDisponivel() {
			return this.canalDisponivel;
		}

		public void setCanalAssociado(String[] canalAssociado) {
			this.canalAssociado = canalAssociado;
		}

		public String[] getCanalAssociado() {
			return this.canalAssociado;
		}

		public void setFlgFrame(String flgFrame) {
			this.flgFrame = flgFrame;
		}

		public String getFlgFrame() {
			return this.flgFrame;
		}

		public void setRegionalAssociada(String[] regionalAssociada) {
			this.regionalAssociada = regionalAssociada;
		}

		public String[] getRegionalAssociada() {
			return this.regionalAssociada;
		}

		public void setRegionalDisponivel(String[] regionalDisponivel) {
			this.regionalDisponivel = regionalDisponivel;
		}

		public String[] getRegionalDisponivel() {
			return this.regionalDisponivel;
		}

		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}

		public String getMensagem() {
			return this.mensagem;
		}

		public void setIdRetorno(String idRetorno) {
			this.idRetorno = idRetorno;
		}

		public String getIdRetorno() {
			return this.idRetorno;
		}
	}

	public UsuarioEncaminhamentoForm getUsuarioEncaminhamentoForm() {
		return this.usuarioEncaminhamentoForm;
	}
}
