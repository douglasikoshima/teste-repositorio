package admsistemas.GrupoUsuarioRegrasEncaminhamento;

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
import br.com.vivo.fo.admsistemas.vo.impl.ProcedenciaVODocumentImpl.ProcedenciaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.SegmentacaoVODocumentImpl.SegmentacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.TipoClienteVODocumentImpl.TipoClienteVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

public class GrupoUsuarioRegrasEncaminhamentoController extends AbstractAction {

	private static final long serialVersionUID = 3449673464477444745L;

	private static transient Logger log = new Logger("admsistemas");

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.configGruposProcessos.GruposProcessosFacade gruposProcessosFacade;

	protected global.Global globalApp = new global.Global();

	private transient String user = ConstantesCRM.SVAZIO;

	private UsuarioEncaminhamentoForm usuarioEncaminhamentoForm = null;

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
		// user = request.getSession().getAttribute("UsuarioLogado").toString();
		user = "";

		// Aponta para o formulário
		usuarioEncaminhamentoForm = form;

		// Obtem a lista de grupos
		usuarioEncaminhamentoForm.setAGrupoVO(gruposProcessosFacade.getAllGruposProcessos(user).getGrupoVOArray());

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

		// Aponta para o formulário
		usuarioEncaminhamentoForm = form;

		// Monta os elementos para montagem no control
		retorno.put(ConstantesCRM.CT_GrupoVO, usuarioEncaminhamentoForm.getCodigoGrupo());
		retorno.put(ConstantesCRM.CT_TipoClienteVO, usuarioEncaminhamentoForm.getTipoClienteAssociada());
		retorno.put(ConstantesCRM.CT_SegmentacaoVO, usuarioEncaminhamentoForm.getSegmentacaoAssociada());
		retorno.put(ConstantesCRM.CT_CarterizacaoVO, usuarioEncaminhamentoForm.getCarterizacaoAssociada());
		retorno.put(ConstantesCRM.CT_ProcedenciaVO, usuarioEncaminhamentoForm.getProcedenciaAssociada());

		// Processa a criação/atualização
		gruposProcessosFacade.setRegrasEncaminhamento(user, retorno);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class UsuarioEncaminhamentoForm extends ActionForm {

		private static final long serialVersionUID = 8547528872193687974L;

		private Integer codigoGrupo = null;
		private GrupoVO[] aGrupoVO = null;
		private GrupoVO grupoVOSelecionado = null;
		private String[] tipoClienteDisponivel;
		private String[] segmentacaoDisponivel;
		private String[] carterizacaoDisponivel;
		private String[] procedenciaDisponivel;
		private String[] tipoClienteAssociada;
		private String[] segmentacaoAssociada;
		private String[] carterizacaoAssociada;
		private String[] procedenciaAssociada;
		private RegrasEncaminhamentoVO regrasEncaminhamentoVO = null;

		/**
		 * Construtor Default
		 */
		public UsuarioEncaminhamentoForm() {
			tipoClienteDisponivel = new String[0];
			segmentacaoDisponivel = new String[0];
			carterizacaoDisponivel = new String[0];
			procedenciaDisponivel = new String[0];
			tipoClienteAssociada = new String[0];
			segmentacaoAssociada = new String[0];
			carterizacaoAssociada = new String[0];
			procedenciaAssociada = new String[0];

			this.grupoVOSelecionado = GrupoVO.Factory.newInstance();

			TipoClienteVO[] tipoCliente = new TipoClienteVOImpl[0];
			SegmentacaoVO[] segmentacao = new SegmentacaoVOImpl[0];
			CarterizacaoVO[] carterizacao = new CarterizacaoVOImpl[0];
			ProcedenciaVO[] procedencia = new ProcedenciaVOImpl[0];

			this.regrasEncaminhamentoVO = RegrasEncaminhamentoVO.Factory.newInstance();
			this.regrasEncaminhamentoVO.setRegrasEncaminhamentoDisponivelVO(RegrasEncaminhamentoDisponivelVO.Factory.newInstance());
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setTipoClienteVOArray(tipoCliente);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setSegmentacaoVOArray(segmentacao);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setCarterizacaoVOArray(carterizacao);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoDisponivelVO().setProcedenciaVOArray(procedencia);

			this.regrasEncaminhamentoVO.setRegrasEncaminhamentoSelecionadoVO(RegrasEncaminhamentoSelecionadoVO.Factory.newInstance());
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setTipoClienteVOArray(tipoCliente);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setSegmentacaoVOArray(segmentacao);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setCarterizacaoVOArray(carterizacao);
			this.regrasEncaminhamentoVO.getRegrasEncaminhamentoSelecionadoVO().setProcedenciaVOArray(procedencia);
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

		public String[] getTipoClienteAssociada() {
			return tipoClienteAssociada;
		}

		public String[] getTipoClienteDisponivel() {
			return tipoClienteDisponivel;
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

		public void setTipoClienteAssociada(String[] strings) {
			tipoClienteAssociada = strings;
		}

		public void setTipoClienteDisponivel(String[] strings) {
			tipoClienteDisponivel = strings;
		}

	}

	/**
	 * Getter para o Form UsuarioEncaminhamentoForm
	 */
	public UsuarioEncaminhamentoForm getUsuarioEncaminhamentoForm() {
		return this.usuarioEncaminhamentoForm;
	}
}
