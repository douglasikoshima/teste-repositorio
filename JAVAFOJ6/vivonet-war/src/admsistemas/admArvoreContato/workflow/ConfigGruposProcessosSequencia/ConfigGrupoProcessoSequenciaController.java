package admsistemas.admArvoreContato.workflow.ConfigGruposProcessosSequencia;

import java.math.BigInteger;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.admsistemas.vo.ArvoreContatoVODocument.ArvoreContatoVO;
import br.com.vivo.fo.admsistemas.vo.GrupoProcessoVODocument.GrupoProcessoVO;
import br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO;
import br.com.vivo.fo.admsistemas.vo.GruposAberturaDocument.GruposAbertura;
import br.com.vivo.fo.admsistemas.vo.GruposDisponiveisDocument.GruposDisponiveis;
import br.com.vivo.fo.admsistemas.vo.GruposProcessosVODocument.GruposProcessosVO;
import br.com.vivo.fo.admsistemas.vo.GruposRetornoDocument.GruposRetorno;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoDocument.GruposTratamento;
import br.com.vivo.fo.admsistemas.vo.impl.GrupoVODocumentImpl.GrupoVOImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.workflow.vo.WFAcaoRetornoVODocument.WFAcaoRetornoVO;

import com.indracompany.actions.AbstractAction;

public class ConfigGrupoProcessoSequenciaController extends AbstractAction {

	private static final long serialVersionUID = -1009484676004820304L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.configGruposProcessos.GruposProcessosFacade gruposProcessosFacade;

	private static transient Logger log = new Logger("admsistemas");

	private String user = ConstantesCRM.SVAZIO;

	private GruposProcessosForm gruposProcessosForm = new GruposProcessosForm();

	protected global.Global globalApp = new global.Global();

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("AplicaGruposProcessos".equals(mapping.getParameter())) {
			return AplicaGruposProcessos(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="GruposProcessos.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ConfigGrupoProcessoSequenciaController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		GruposProcessosForm form = (GruposProcessosForm) formParam;
		String node = request.getParameter("idContato");

		log.debug("begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )| idContato = " + node);

		gruposProcessosForm = form;

		if (gruposProcessosForm.getFiltroGrupos() == null) {
			gruposProcessosForm.setFiltroGrupos(ConstantesCRM.SONE);
		}

		user = ConstantesCRM.getCurrentUser(request.getSession());
		if ((node == null) || (node.length() == 0)) {
			if ((gruposProcessosForm.getNodeSelecionado() == null) || (gruposProcessosForm.getNodeSelecionado().length() == 0)) {
				node = ConstantesCRM.SONE;
			} else {
				node = gruposProcessosForm.getNodeSelecionado();
			}
		}
		if ((user == null) || (user.length() == 0)) {
			user = ConstantesCRM.SONE;
		}

		// Definições
		// gruposProcessosForm = new GruposProcessosForm();

		// Processa informações iniciais do formulário
		gruposProcessosForm.setNodeSelecionado(node);
		gruposProcessosForm.setGruposProcessos(gruposProcessosFacade.getGruposProcessos(user, node, gruposProcessosForm.getFiltroGrupos(), "F"));

		// Processa apresentação do formulário
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="Resultado.jsp"
	 */
	public ActionForward AplicaGruposProcessos(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ConfigGrupoProcessoSequenciaController:AplicaGruposProcessos" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		GruposProcessosForm form = (GruposProcessosForm) formParam;
		// processa elementos disponíveis
		/*
		 * nao é necessario enviar os grupos disponivies GrupoVO[] g = new
		 * GrupoVOImpl[form.getGruposDisponiveis().length]; for(int i = 0; i < form.getGruposDisponiveis().length; i++)
		 * { GrupoVO gVO = GrupoVO.Factory.newInstance();
		 * gVO.setCodigo(Integer.parseInt(form.getGruposDisponiveis()[i])); g[i] = gVO; }
		 */

		GrupoVO[] g = new GrupoVOImpl[form.getGruposDisponiveis().length];

		// Processa elementos abertura
		g = new GrupoVOImpl[form.getGruposAbertura().length];
		for (int i = 0; i < form.getGruposAbertura().length; i++) {
			GrupoVO gVO = GrupoVO.Factory.newInstance();
			gVO.setCodigo(Integer.parseInt(form.getGruposAbertura()[i]));
			gVO.setOrdem(i);
			g[i] = gVO;
		}
		form.getGruposProcessos().getGruposAbertura().getGrupoProcessoVO().setGrupoVOArray(g);

		// Processa elementos tratamento
		g = new GrupoVOImpl[form.getGruposTratamento().length];
		for (int i = 0; i < form.getGruposTratamento().length; i++) {
			GrupoVO gVO = GrupoVO.Factory.newInstance();
			gVO.setCodigo(Integer.parseInt(form.getGruposTratamento()[i]));
			gVO.setOrdem(i);
			g[i] = gVO;
		}
		form.getGruposProcessos().getGruposTratamento().getGrupoProcessoVO().setGrupoVOArray(g);

		// Processa elementos retorno
		g = new GrupoVOImpl[form.getGruposRetorno().length];
		for (int i = 0; i < form.getGruposRetorno().length; i++) {
			GrupoVO gVO = GrupoVO.Factory.newInstance();
			gVO.setCodigo(Integer.parseInt(form.getGruposRetorno()[i]));
			gVO.setOrdem(i);
			g[i] = gVO;
		}
		form.getGruposProcessos().getGruposRetorno().getGrupoProcessoVO().setGrupoVOArray(g);

		// Arvore de contato
		ArvoreContatoVO ac = ArvoreContatoVO.Factory.newInstance();
		ac.setCodigo(new BigInteger(form.getNodeSelecionado()));
		form.getGruposProcessos().getGruposDisponiveis().getGrupoProcessoVO().setArvoreContatoVO(ac);

		// atualiza o FormBean
		gruposProcessosForm.setNodeSelecionado(form.getNodeSelecionado());
		gruposProcessosForm = form;
		// Processa informações iniciais do formulário

		user = ConstantesCRM.getCurrentUser(request.getSession());

		// Obtérm dados do controler para operação do formulário
		WFAcaoRetornoVO wfRetAcao = gruposProcessosFacade.setGruposProcessos(user, gruposProcessosForm.getGruposProcessos());

		gruposProcessosForm.setMensagem(wfRetAcao.getMensagem());
		gruposProcessosForm.setIdRetorno(wfRetAcao.getAcaoExecucao());

		// Processa apresentação do formulário
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class GruposProcessosForm extends ActionForm {

		private static final long serialVersionUID = -7331337455538450345L;

		private String dsPath;

		private String filtroGrupos;

		private String[] gruposDisponiveis;
		private String[] gruposAbertura;
		private String[] gruposTratamento;
		private String[] gruposRetorno;
		private String nodeSelecionado;
		private GruposProcessosVO gruposProcessos;

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

		public GruposProcessosForm() {
			gruposDisponiveis = new String[0];
			gruposAbertura = new String[0];
			gruposTratamento = new String[0];
			gruposRetorno = new String[0];

			this.gruposProcessos = GruposProcessosVO.Factory.newInstance();

			this.gruposProcessos.setGruposDisponiveis(GruposDisponiveis.Factory.newInstance());
			this.gruposProcessos.getGruposDisponiveis().setGrupoProcessoVO(GrupoProcessoVO.Factory.newInstance());
			this.gruposProcessos.getGruposDisponiveis().getGrupoProcessoVO().setArvoreContatoVO(ArvoreContatoVO.Factory.newInstance());
			this.gruposProcessos.getGruposDisponiveis().getGrupoProcessoVO().setGrupoVOArray(new GrupoVOImpl[0]);

			this.gruposProcessos.setGruposAbertura(GruposAbertura.Factory.newInstance());
			this.gruposProcessos.getGruposAbertura().setGrupoProcessoVO(GrupoProcessoVO.Factory.newInstance());
			this.gruposProcessos.getGruposAbertura().getGrupoProcessoVO().setArvoreContatoVO(ArvoreContatoVO.Factory.newInstance());
			this.gruposProcessos.getGruposAbertura().getGrupoProcessoVO().setGrupoVOArray(new GrupoVOImpl[0]);

			this.gruposProcessos.setGruposTratamento(GruposTratamento.Factory.newInstance());
			this.gruposProcessos.getGruposTratamento().setGrupoProcessoVO(GrupoProcessoVO.Factory.newInstance());
			this.gruposProcessos.getGruposTratamento().getGrupoProcessoVO().setArvoreContatoVO(ArvoreContatoVO.Factory.newInstance());
			this.gruposProcessos.getGruposTratamento().getGrupoProcessoVO().setGrupoVOArray(new GrupoVOImpl[0]);

			this.gruposProcessos.setGruposRetorno(GruposRetorno.Factory.newInstance());
			this.gruposProcessos.getGruposRetorno().setGrupoProcessoVO(GrupoProcessoVO.Factory.newInstance());
			this.gruposProcessos.getGruposRetorno().getGrupoProcessoVO().setArvoreContatoVO(ArvoreContatoVO.Factory.newInstance());
			this.gruposProcessos.getGruposRetorno().getGrupoProcessoVO().setGrupoVOArray(new GrupoVOImpl[0]);
		}

		public String[] getGruposAbertura() {
			return gruposAbertura;
		}

		public String[] getGruposDisponiveis() {
			return gruposDisponiveis;
		}

		public String getNodeSelecionado() {
			return nodeSelecionado;
		}

		public GruposProcessosVO getGruposProcessos() {
			return gruposProcessos;
		}

		public String[] getGruposRetorno() {
			return gruposRetorno;
		}

		public String[] getGruposTratamento() {
			return gruposTratamento;
		}

		public void setGruposAbertura(String[] strings) {
			gruposAbertura = strings;
		}

		public void setGruposDisponiveis(String[] strings) {
			gruposDisponiveis = strings;
		}

		public void setNodeSelecionado(String string) {
			nodeSelecionado = string;
		}

		public void setGruposProcessos(GruposProcessosVO processosVO) {
			gruposProcessos = processosVO;
		}

		public void setGruposRetorno(String[] strings) {
			gruposRetorno = strings;
		}

		public void setGruposTratamento(String[] strings) {
			gruposTratamento = strings;
		}

		public void setFiltroGrupos(String filtroGrupos) {
			this.filtroGrupos = filtroGrupos;
		}

		public String getFiltroGrupos() {
			return this.filtroGrupos;
		}

		public void setDsPath(String dsPath) {
			this.dsPath = dsPath;
		}

		public String getDsPath() {
			return this.dsPath;
		}
	}

	// Getter para o Form GruposProcessosForm
	public GruposProcessosForm getGruposProcessosForm() {
		return this.gruposProcessosForm;
	}
}
