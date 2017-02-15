package admsistemas.admArvoreContato.workflow.AssociacaoGrupos;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.ArvoreContatoVODocument.ArvoreContatoVO;
import br.com.vivo.fo.admsistemas.vo.AssosiacaoGrupoVariaveisAssosiadosVODocument;
import br.com.vivo.fo.admsistemas.vo.AssosiacaoGrupoVariaveisDisponiveisVODocument;
import br.com.vivo.fo.admsistemas.vo.AssosiacaoGrupoVariaveisVODocument.AssosiacaoGrupoVariaveisVO;
import br.com.vivo.fo.admsistemas.vo.GrupoProcessoVODocument.GrupoProcessoVO;
import br.com.vivo.fo.admsistemas.vo.GruposAberturaDocument.GruposAbertura;
import br.com.vivo.fo.admsistemas.vo.GruposDisponiveisDocument.GruposDisponiveis;
import br.com.vivo.fo.admsistemas.vo.GruposProcessosVODocument.GruposProcessosVO;
import br.com.vivo.fo.admsistemas.vo.impl.AdmNaturezaVODocumentImpl.AdmNaturezaVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.CarterizacaoVODocumentImpl.CarterizacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.GrupoVODocumentImpl.GrupoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.SegmentacaoVODocumentImpl.SegmentacaoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.TipoClienteVODocumentImpl.TipoClienteVOImpl;
import br.com.vivo.fo.cliente.vo.impl.TipoLinhaVODocumentImpl.TipoLinhaVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;

import com.indracompany.actions.AbstractAction;

public class AssociacaoGruposController extends AbstractAction {

	private static final long serialVersionUID = -444806430276820407L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.associacaoGrupos.AssociacaoGrupos associacaoGruposFacade;

	private String user = ConstantesCRM.SVAZIO;

	private AssociacaoGrupoForm associacaoGrupoForm = null;

	protected global.Global globalApp = new global.Global();

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("carregaVariaveis".equals(mapping.getParameter())) {
			return carregaVariaveis(mapping, form, request, response);
		} else if ("carregaGrupos".equals(mapping.getParameter())) {
			return carregaGrupos(mapping, form, request, response);
		} else if ("salvaAssociacaoGrupo".equals(mapping.getParameter())) {
			return salvaAssociacaoGrupo(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="associacaoGrupo.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AssociacaoGruposController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		AssociacaoGrupoForm form = (AssociacaoGrupoForm) formParam;
		this.associacaoGrupoForm = new AssociacaoGrupoForm();

		this.associacaoGrupoForm.setDsPath(form.getDsPath());
		this.associacaoGrupoForm.setContato(request.getParameter("idContato"));

		user = ConstantesCRM.getCurrentUser(request.getSession());

		this.associacaoGrupoForm.setMensagem(ConstantesCRM.SVAZIO);

		// monta as açoes
		this.associacaoGrupoForm.setWFAcaoVO(this.associacaoGruposFacade.getWFAcaoVO(user, this.associacaoGrupoForm.getContato()));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iObtemRegrasEncaminhamento.jsp"
	 */
	public ActionForward carregaVariaveis(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AssociacaoGruposController:carregaVariaveis" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		AssociacaoGrupoForm form = (AssociacaoGrupoForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());

		associacaoGrupoForm.setMensagem(ConstantesCRM.SVAZIO);
		associacaoGrupoForm.setFechamento(form.getFechamento());
		associacaoGrupoForm.setContato(form.getContato());
		associacaoGrupoForm.setGrupo(form.getGrupo());

		// Obtem a lista de grupos
		associacaoGrupoForm.setAssosiacaoGrupoVariaveisVO(associacaoGruposFacade.getAssosiacaoGrupoVariaveisVO(user, form.getContato(), form.getGrupo(), form.getFechamento()));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iAssociacaoGrupo.jsp"
	 */
	public ActionForward carregaGrupos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AssociacaoGruposController:carregaGrupos" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		AssociacaoGrupoForm form = (AssociacaoGrupoForm) formParam;
		user = ConstantesCRM.getCurrentUser(request.getSession());

		associacaoGrupoForm.setMensagem(ConstantesCRM.SVAZIO);
		associacaoGrupoForm.setFechamento(form.getFechamento());
		associacaoGrupoForm.setContato(form.getContato());

		// busca grupos disponíveis e associados
		associacaoGrupoForm.setGruposProcessos(this.associacaoGruposFacade.getGruposProcessos(user, form.getContato(), form.getFechamento()));

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Resultado.jsp"
	 */
	public ActionForward salvaAssociacaoGrupo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AssociacaoGruposController:salvaAssociacaoGrupo" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		AssociacaoGrupoForm form = (AssociacaoGrupoForm) formParam;
		StringBuffer str = new StringBuffer(ConstantesCRM.SVAZIO);
		str.append("<persiste>" + request.getParameter("persiste") + "</persiste>");
		str.append("<idContato>" + form.getContato() + "</idContato>");
		str.append("<idAtividade>" + form.getFechamento() + "</idAtividade>");
		if (request.getParameter("apagar").equals(ConstantesCRM.SZERO)) {
			str.append("<idGrupo>" + request.getParameter("grupo") + "</idGrupo>");
		} else {
			if (form.getGruposDisponiveis() != null) {
				for (int i = 0; i < form.getGruposDisponiveis().length; i++) {
					str.append("<idGrupo>" + form.getGruposDisponiveis()[i] + "</idGrupo>");
				}
			}
		}
		str.append("<SegmentacaoVO>");
		for (int i = 0; i < form.getSegmentacaoAssociada().length; i++) {
			str.append("<idSegmentacao>" + form.getSegmentacaoAssociada()[i] + "</idSegmentacao>");
		}
		str.append("</SegmentacaoVO>");

		str.append("<AdmNaturezaVO>");
		for (int i = 0; i < form.getNaturezaAssociada().length; i++) {
			str.append("<idNatureza>" + form.getNaturezaAssociada()[i] + "</idNatureza>");
		}
		str.append("</AdmNaturezaVO>");

		str.append("<TipoLinhaVO>");
		for (int i = 0; i < form.getTipoLinhaAssociada().length; i++) {
			str.append("<id>" + form.getTipoLinhaAssociada()[i] + "</id>");
		}
		str.append("</TipoLinhaVO>");

		str.append("<CarterizacaoVO>");
		for (int i = 0; i < form.getCarterizacaoAssociada().length; i++) {
			str.append("<idTipoCarteira>" + form.getCarterizacaoAssociada()[i] + "</idTipoCarteira>");
		}
		str.append("</CarterizacaoVO>");

		str.append("<TipoClienteVO>");
		for (int i = 0; i < form.getTipoClienteAssociada().length; i++) {
			str.append("<codigo>" + form.getTipoClienteAssociada()[i] + "</codigo>");
		}
		str.append("</TipoClienteVO>");

		user = ConstantesCRM.getCurrentUser(request.getSession());
		WFAcaoRetornoVO wfRetAcao = this.associacaoGruposFacade.setGruposAssociados(user, str.toString());
		// WFAcaoRetornoVO.Factory.newInstance();
		/*
		 * wfRetAcao.setMensagem("Registros inseridos"); wfRetAcao.setAcaoExecucao("0");
		 */
		associacaoGrupoForm.setMensagem(wfRetAcao.getMensagem());
		associacaoGrupoForm.setIdRetorno(wfRetAcao.getAcaoExecucao());

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class AssociacaoGrupoForm extends ActionForm {

		private static final long serialVersionUID = -4035644519119877009L;

		private String dsPath;

		private String strMensagem = ConstantesCRM.SVAZIO;

		public void setMensagem(String strMensagem) {
			this.strMensagem = strMensagem;
		}

		public String getMensagem() {
			return this.strMensagem;
		}

		private String idRetorno = ConstantesCRM.SVAZIO;

		public void setIdRetorno(String idRetorno) {
			this.idRetorno = idRetorno;
		}

		public String getIdRetorno() {
			return this.idRetorno;
		}

		private String fechamento = ConstantesCRM.SVAZIO;

		public void setFechamento(String fechamento) {
			this.fechamento = fechamento;
		}

		public String getFechamento() {
			return this.fechamento;
		}

		private String contato = ConstantesCRM.SVAZIO;

		public void setContato(String contato) {
			this.contato = contato;
		}

		public String getContato() {
			return this.contato;
		}

		private String grupo = ConstantesCRM.SVAZIO;

		public void setGrupo(String grupo) {
			this.grupo = grupo;
		}

		public String getGrupo() {
			return this.grupo;
		}

		private br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] WFAcaoVO;

		public void setWFAcaoVO(br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] WFAcaoVO) {
			this.WFAcaoVO = WFAcaoVO;
		}

		public br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO[] getWFAcaoVO() {
			return this.WFAcaoVO;
		}

		private String[] gruposAssociados;

		public String[] getGruposAssociados() {
			return this.gruposAssociados;
		}

		public void setGruposAssociados(String[] strings) {
			this.gruposAssociados = strings;
		}

		private String[] gruposDisponiveis;

		public String[] getGruposDisponiveis() {
			return this.gruposDisponiveis;
		}

		public void setGruposDisponiveis(String[] strings) {
			this.gruposDisponiveis = strings;
		}

		private GruposProcessosVO gruposProcessos;

		public void setGruposProcessos(GruposProcessosVO processosVO) {
			this.gruposProcessos = processosVO;
		}

		public GruposProcessosVO getGruposProcessos() {
			return this.gruposProcessos;
		}

		private String[] carterizacaoAssociada;

		public String[] getCarterizacaoAssociada() {
			return this.carterizacaoAssociada;
		}

		public void setCarterizacaoAssociada(String[] strings) {
			this.carterizacaoAssociada = strings;
		}

		private String[] carterizacaoDisponivel;

		public String[] getCarterizacaoDisponivel() {
			return this.carterizacaoDisponivel;
		}

		public void setCarterizacaoDisponivel(String[] strings) {
			this.carterizacaoDisponivel = strings;
		}

		private String[] naturezaAssociada;

		public String[] getNaturezaAssociada() {
			return this.naturezaAssociada;
		}

		public void setNaturezaAssociada(String[] strings) {
			this.naturezaAssociada = strings;
		}

		private String[] naturezaDisponivel;

		public String[] getNaturezaDisponivel() {
			return this.naturezaDisponivel;
		}

		public void setNaturezaDisponivel(String[] strings) {
			this.naturezaDisponivel = strings;
		}

		private String[] tipoLinhaDisponivel;

		public String[] getTipoLinhaDisponivel() {
			return this.tipoLinhaDisponivel;
		}

		public void setTipoLinhaDisponivel(String[] strings) {
			this.tipoLinhaDisponivel = strings;
		}

		private String[] tipoLinhaAssociada;

		public String[] getTipoLinhaAssociada() {
			return this.tipoLinhaAssociada;
		}

		public void setTipoLinhaAssociada(String[] strings) {
			this.tipoLinhaAssociada = strings;
		}

		private String[] segmentacaoAssociada;

		public String[] getSegmentacaoAssociada() {
			return this.segmentacaoAssociada;
		}

		public void setSegmentacaoAssociada(String[] strings) {
			this.segmentacaoAssociada = strings;
		}

		private String[] segmentacaoDisponivel;

		public String[] getSegmentacaoDisponivel() {
			return this.segmentacaoDisponivel;
		}

		public void setSegmentacaoDisponivel(String[] strings) {
			this.segmentacaoDisponivel = strings;
		}

		private String[] tipoClienteAssociada;

		public String[] getTipoClienteAssociada() {
			return this.tipoClienteAssociada;
		}

		public void setTipoClienteAssociada(String[] strings) {
			this.tipoClienteAssociada = strings;
		}

		private String[] tipoClienteDisponivel;

		public String[] getTipoClienteDisponivel() {
			return this.tipoClienteDisponivel;
		}

		public void setTipoClienteDisponivel(String[] strings) {
			this.tipoClienteDisponivel = strings;
		}

		private AssosiacaoGrupoVariaveisVO assosiacaoGrupoVariaveisVO;

		public AssosiacaoGrupoVariaveisVO getAssosiacaoGrupoVariaveisVO() {
			return this.assosiacaoGrupoVariaveisVO;
		}

		public void setAssosiacaoGrupoVariaveisVO(AssosiacaoGrupoVariaveisVO assosiacaoGrupoVariaveisVO) {
			this.assosiacaoGrupoVariaveisVO = assosiacaoGrupoVariaveisVO;
		}

		public AssociacaoGrupoForm() {

			gruposDisponiveis = new String[0];
			gruposAssociados = new String[0];

			this.gruposProcessos = GruposProcessosVO.Factory.newInstance();

			this.gruposProcessos.setGruposDisponiveis(GruposDisponiveis.Factory.newInstance());
			this.gruposProcessos.getGruposDisponiveis().setGrupoProcessoVO(GrupoProcessoVO.Factory.newInstance());
			this.gruposProcessos.getGruposDisponiveis().getGrupoProcessoVO().setArvoreContatoVO(ArvoreContatoVO.Factory.newInstance());
			this.gruposProcessos.getGruposDisponiveis().getGrupoProcessoVO().setGrupoVOArray(new GrupoVOImpl[0]);

			this.gruposProcessos.setGruposAbertura(GruposAbertura.Factory.newInstance());
			this.gruposProcessos.getGruposAbertura().setGrupoProcessoVO(GrupoProcessoVO.Factory.newInstance());
			this.gruposProcessos.getGruposAbertura().getGrupoProcessoVO().setArvoreContatoVO(ArvoreContatoVO.Factory.newInstance());
			this.gruposProcessos.getGruposAbertura().getGrupoProcessoVO().setGrupoVOArray(new GrupoVOImpl[0]);

			this.tipoClienteDisponivel = new String[0];
			this.tipoClienteAssociada = new String[0];

			this.naturezaDisponivel = new String[0];
			this.naturezaAssociada = new String[0];

			this.carterizacaoDisponivel = new String[0];
			this.carterizacaoAssociada = new String[0];

			this.segmentacaoDisponivel = new String[0];
			this.segmentacaoAssociada = new String[0];

			this.tipoLinhaDisponivel = new String[0];
			this.tipoLinhaAssociada = new String[0];

			this.assosiacaoGrupoVariaveisVO = AssosiacaoGrupoVariaveisVO.Factory.newInstance();

			this.assosiacaoGrupoVariaveisVO.setAssosiacaoGrupoVariaveisAssosiadosVO(AssosiacaoGrupoVariaveisAssosiadosVODocument.AssosiacaoGrupoVariaveisAssosiadosVO.Factory.newInstance());
			this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisAssosiadosVO().setTipoClienteVOArray(new TipoClienteVOImpl[0]);
			this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisAssosiadosVO().setAdmNaturezaVOArray(new AdmNaturezaVOImpl[0]);
			this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisAssosiadosVO().setCarterizacaoVOArray(new CarterizacaoVOImpl[0]);
			this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisAssosiadosVO().setSegmentacaoVOArray(new SegmentacaoVOImpl[0]);
			this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisAssosiadosVO().setTipoLinhaVOArray(new TipoLinhaVOImpl[0]);

			this.assosiacaoGrupoVariaveisVO.setAssosiacaoGrupoVariaveisDisponiveisVO(AssosiacaoGrupoVariaveisDisponiveisVODocument.AssosiacaoGrupoVariaveisDisponiveisVO.Factory.newInstance());
			this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisDisponiveisVO().setTipoClienteVOArray(new TipoClienteVOImpl[0]);
			this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisDisponiveisVO().setAdmNaturezaVOArray(new AdmNaturezaVOImpl[0]);
			this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisDisponiveisVO().setCarterizacaoVOArray(new CarterizacaoVOImpl[0]);
			this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisDisponiveisVO().setSegmentacaoVOArray(new SegmentacaoVOImpl[0]);
			this.assosiacaoGrupoVariaveisVO.getAssosiacaoGrupoVariaveisDisponiveisVO().setTipoLinhaVOArray(new TipoLinhaVOImpl[0]);
		}

		public void setDsPath(String dsPath) {
			this.dsPath = dsPath;
		}

		public String getDsPath() {
			return this.dsPath;
		}
	}

	// Getter para o Form GruposProcessosForm
	public AssociacaoGrupoForm getAssociacaoGrupoForm() {
		return this.associacaoGrupoForm;
	}
}
