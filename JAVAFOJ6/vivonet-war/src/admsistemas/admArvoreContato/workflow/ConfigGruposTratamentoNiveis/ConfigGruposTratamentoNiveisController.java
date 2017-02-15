package admsistemas.admArvoreContato.workflow.ConfigGruposTratamentoNiveis;

import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import admsistemas.admArvoreContato.workflow.GrupoUsuarioRegrasEncaminhamento.GrupoUsuarioRegrasEncaminhamentoController.UsuarioEncaminhamentoForm;
import br.com.vivo.fo.admsistemas.vo.GrupoVODocument.GrupoVO;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoNiveisVODocument.GruposTratamentoNiveisVO;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoNiveisVODocument.GruposTratamentoNiveisVO.GrupoInicio;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoNiveisVODocument.GruposTratamentoNiveisVO.GrupoTratamento;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoNiveisVODocument.GruposTratamentoNiveisVO.GrupoTratamento.Disponiveis;
import br.com.vivo.fo.admsistemas.vo.GruposTratamentoNiveisVODocument.GruposTratamentoNiveisVO.GrupoTratamento.Sequencia;
import br.com.vivo.fo.admsistemas.vo.impl.GrupoVODocumentImpl.GrupoVOImpl;
import br.com.vivo.fo.admsistemas.vo.impl.GruposTratamentoNiveisVODocumentImpl.GruposTratamentoNiveisVOImpl.GrupoTratamentoImpl;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;

public class ConfigGruposTratamentoNiveisController extends AbstractAction {

	private static final long serialVersionUID = 7592558593979079008L;

	@EJB
	private br.com.vivo.fo.ctrls.admsistemas.configGruposProcessos.GruposProcessosFacade gruposProcessosFacade;

	protected global.Global globalApp = new global.Global();

	private String user = ConstantesCRM.SVAZIO;

	private GruposTratamentoNiveisForm gruposTratamentoNiveisForm = null;

	private static transient Logger log = new Logger("admsistemas");

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("ConsultaGruposTratamentoNiveis".equals(mapping.getParameter())) {
			return ConsultaGruposTratamentoNiveis(mapping, form, request, response);
		} else if ("AlteraNumeroNiveis".equals(mapping.getParameter())) {
			return AlteraNumeroNiveis(mapping, form, request, response);
		} else if ("AplicaGruposTratamentoNiveis".equals(mapping.getParameter())) {
			return AplicaGruposTratamentoNiveis(mapping, form, request, response);
		} else if ("ConsultaGrupoAssociados".equals(mapping.getParameter())) {
			return ConsultaGrupoAssociados(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="GruposTratamentoNiveis.jsp"
	 */
	public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ConfigGruposTratamentoNiveisController:begin" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		// user = request.getSession().getAttribute("UsuarioLogado").toString();
		// String node = request.getParameter("node");
		String node = request.getParameter("idContato");

		request.setAttribute("idContato", request.getParameter("idContato"));

		user = ConstantesCRM.getCurrentUser(request.getSession());
		if ((node == null) || (node.length() == 0)) {
			node = ConstantesCRM.SONE;
		}
		if ((user == null) || (user.length() == 0)) {
			user = ConstantesCRM.SONE;
		}

		// Inicializa o formulário
		gruposTratamentoNiveisForm = new GruposTratamentoNiveisForm();

		// Obtem os grupos em tratamento
		GrupoVO[] grupoVOArray = gruposProcessosFacade.getGruposProcessos(user, node, ConstantesCRM.SONE, "C").getGruposTratamento().getGrupoProcessoVO().getGrupoVOArray();
		gruposTratamentoNiveisForm.setGruposTratamentoArray(grupoVOArray);
		request.getSession().setAttribute("formTratamento", gruposTratamentoNiveisForm);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ifrGruposTratamentoNiveis.jsp"
	 */
	public ActionForward ConsultaGruposTratamentoNiveis(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ConfigGruposTratamentoNiveisController:ConsultaGruposTratamentoNiveis" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (gruposTratamentoNiveisForm == null) {
			gruposTratamentoNiveisForm = (GruposTratamentoNiveisForm) request.getSession().getAttribute("formTratamento");
		}

		// Grupo em tratamento Selecionado
		gruposTratamentoNiveisForm.setGrupoSelecionado(Integer.parseInt(request.getParameter("codigoGrupo")));

		request.setAttribute("idContato", request.getParameter("idContato"));

		user = ConstantesCRM.getCurrentUser(request.getSession());
		// Obtem Grupos em Tratamento por níveis
		GruposTratamentoNiveisVO gtnVO = gruposProcessosFacade.getGruposTratamentoNiveis(user, gruposTratamentoNiveisForm.getGrupoSelecionado());
		gruposTratamentoNiveisForm.setGruposTratamentoNiveisVO(gtnVO);

		int nivelSelecionado = gruposTratamentoNiveisForm.getGruposTratamentoNiveisVO().getGrupoTratamentoArray().length;
		gruposTratamentoNiveisForm.setNivelSelecionado(nivelSelecionado);
		gruposTratamentoNiveisForm.setNumNiveisRetorno(nivelSelecionado);

		request.getSession().setAttribute("formTratamento", gruposTratamentoNiveisForm);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ifrGruposTratamentoNiveis.jsp"
	 */
	public ActionForward AlteraNumeroNiveis(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		GruposTratamentoNiveisForm form = (GruposTratamentoNiveisForm) formParam;
		log.debug("ConfigGruposTratamentoNiveisController:AlteraNumeroNiveis" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		if (gruposTratamentoNiveisForm == null) {
			gruposTratamentoNiveisForm = (GruposTratamentoNiveisForm) request.getSession().getAttribute("formTratamento");
		}

		GrupoVO[] disponiveisArray = new GrupoVOImpl[gruposTratamentoNiveisForm.getGruposTratamentoArray().length - 1];
		GrupoVO[] sequenciasArray = new GrupoVOImpl[0];

		gruposTratamentoNiveisForm.setNivelAnterior(form.getNivelSelecionado());

		// verifica se a alteração de nível foi para maior ou menor
		if (form.getNivelSelecionado() > gruposTratamentoNiveisForm.getNivelSelecionado()) {
			// número de níveis a serem adicionados
			int numNiveisAdicionar = form.getNivelSelecionado() - gruposTratamentoNiveisForm.getNivelSelecionado();

			// em cada iteração do laço, inclui um array de grupos em tratamento para cada nível adicional selecionado
			for (int i = 0; i < numNiveisAdicionar; i++) {
				// inclui grupos em tratamento, excluindo o grupo selecionado
				int j = 0;
				for (int k = 0; k < gruposTratamentoNiveisForm.getGruposTratamentoArray().length; k++) {
					// não inclui o grupo selecionado
					if (gruposTratamentoNiveisForm.getGruposTratamentoArray()[k].getCodigo() == form.getGrupoSelecionado()) {
						continue;
					}

					//
					disponiveisArray[j] = GrupoVO.Factory.newInstance();
					disponiveisArray[j].setCodigo(gruposTratamentoNiveisForm.getGruposTratamentoArray()[k].getCodigo());
					disponiveisArray[j].setDescricao(gruposTratamentoNiveisForm.getGruposTratamentoArray()[k].getDescricao());
					j++;
				}

				gruposTratamentoNiveisForm.getGruposTratamentoNiveisVO().addNewGrupoTratamento();
				GrupoTratamento gt = GrupoTratamento.Factory.newInstance();

				gt.setNivel(i + 1);
				gt.setDisponiveis(Disponiveis.Factory.newInstance());
				gt.getDisponiveis().setGrupoVOArray(disponiveisArray);
				gt.setSequencia(Sequencia.Factory.newInstance());
				gt.getSequencia().setGrupoVOArray(sequenciasArray);

				// adiciona o grupo montado na array de grupos em tratamento
				gruposTratamentoNiveisForm.getGruposTratamentoNiveisVO().setGrupoTratamentoArray(gruposTratamentoNiveisForm.getNivelSelecionado() + i, gt);
			}

			// atualiza variáves de controle do número de níveis
			gruposTratamentoNiveisForm.setNivelSelecionado(form.getNivelSelecionado());
			gruposTratamentoNiveisForm.setNumNiveisRetorno(form.getNivelSelecionado());
		} else {
			// níveis a serem excluídos
			for (int i = gruposTratamentoNiveisForm.getNivelSelecionado() - 1; i >= form.getNivelSelecionado(); i--) {
				gruposTratamentoNiveisForm.getGruposTratamentoNiveisVO().removeGrupoTratamento(i);
			}

			// atualiza variáves de controle do número de níveis
			gruposTratamentoNiveisForm.setNivelSelecionado(form.getNivelSelecionado());
			gruposTratamentoNiveisForm.setNumNiveisRetorno(form.getNivelSelecionado());
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ConsultaGruposTratamentoNiveis.do"
	 */
	public ActionForward AplicaGruposTratamentoNiveis(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		GruposTratamentoNiveisForm form = (GruposTratamentoNiveisForm) formParam;
		log.debug("ConfigGruposTratamentoNiveisController:AplicaGruposTratamentoNiveis" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");

		HashMap<String, Object> gravarElementos = new HashMap<String, Object>();
		// Monta mapa de elementos a serem gravados
		gravarElementos.put("grupoInicio", new Integer(form.getGrupoSelecionado()));
		gravarElementos.put("disponiveis", form.getDisponiveis());
		gravarElementos.put("sequencias", form.getSequencia());

		user = ConstantesCRM.getCurrentUser(request.getSession());
		// Grava
		gruposProcessosFacade.setGruposTratamentoNiveis(user, form.getNivelSelecionado(), gravarElementos);

		// retorna para a página de consulta
		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		f.addParameter("codigoGrupo", new Integer(gruposTratamentoNiveisForm.getGrupoSelecionado()).toString());
		request.setAttribute("msgStatus", ConstantesCRM.SSucesso);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path=
	 *              "/admsistemas/admArvoreContato/workflow/GrupoUsuarioRegrasEncaminhamento/GrupoUsuarioRegrasEncaminhamentoController.jpf"
	 */
	public ActionForward ConsultaGrupoAssociados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("ConfigGruposTratamentoNiveisController:ConsultaGrupoAssociados" + "( " + ConstantesCRM.getCurrentUser(request.getSession()) + " )");
		UsuarioEncaminhamentoForm form = new UsuarioEncaminhamentoForm();
		form.setFlgFrame("frame");
		request.setAttribute("flgFrame", "frame");
		request.setAttribute("form", form);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class GruposTratamentoNiveisForm extends ActionForm {

		private static final long serialVersionUID = 6657636485040917326L;

		private int nivelAnterior;

		private int grupoSelecionado;
		private int nivelSelecionado;
		private int numNiveisRetorno;
		private NivelVO[] niveis;
		private GrupoVO[] gruposTratamentoArray;
		private GruposTratamentoNiveisVO gruposTratamentoNiveisVO;
		private String[] disponiveis;
		private String[] sequencia;

		public GruposTratamentoNiveisForm() {
			// inicializa combo de níveis
			niveis = new NivelVO[10];
			String[] labels = { "Nenhum", "1 Nível", "2 Níveis", "3 Níveis", "4 Níveis", "5 Níveis", "6 Níveis", "7 Níveis", "8 Níveis", "9 Níveis" };
			for (int i = 0; i < 10; i++) {
				niveis[i] = new NivelVO();
				niveis[i].setCodigo(i);
				niveis[i].setDescricao(labels[i]);
			}
			nivelSelecionado = 0;

			// inicializa Objeto GruposTratamentoNiveisVO
			gruposTratamentoNiveisVO = GruposTratamentoNiveisVO.Factory.newInstance();
			gruposTratamentoNiveisVO.setGrupoInicio(GrupoInicio.Factory.newInstance());
			gruposTratamentoNiveisVO.getGrupoInicio().setGrupoVO(GrupoVO.Factory.newInstance());
			gruposTratamentoNiveisVO.setGrupoTratamentoArray(new GrupoTratamentoImpl[0]);

			// inicializa arrays de retorno
			disponiveis = new String[0];
			sequencia = new String[0];
		}

		public GruposTratamentoNiveisVO getGruposTratamentoNiveisVO() {
			return gruposTratamentoNiveisVO;
		}

		public void setGruposTratamentoNiveisVO(GruposTratamentoNiveisVO gruposTratamentoNiveis) {
			gruposTratamentoNiveisVO = gruposTratamentoNiveis;
		}

		public GrupoVO[] getGruposTratamentoArray() {
			return gruposTratamentoArray;
		}

		public void setGruposTratamentoArray(GrupoVO[] grupoArray) {
			gruposTratamentoArray = grupoArray;
		}

		public int getGrupoSelecionado() {
			return grupoSelecionado;
		}

		public NivelVO[] getNiveis() {
			return niveis;
		}

		public int getNivelSelecionado() {
			return nivelSelecionado;
		}

		public void setGrupoSelecionado(int grupo) {
			grupoSelecionado = grupo;
		}

		public void setNiveis(NivelVO[] niveis) {
			this.niveis = niveis;
		}

		public void setNivelSelecionado(int nivel) {
			nivelSelecionado = nivel;
		}

		public String[] getDisponiveis() {
			return disponiveis;
		}

		public void setdisponiveis(String[] disp) {
			disponiveis = disp;
		}

		public String[] getSequencia() {
			return sequencia;
		}

		public void setSequencia(String[] seq) {
			sequencia = seq;
		}

		public int getNumNiveisRetorno() {
			return this.numNiveisRetorno;
		}

		public void setNumNiveisRetorno(int num) {
			this.numNiveisRetorno = num;
		}

		public void setNivelAnterior(int nivelAnterior) {
			this.nivelAnterior = nivelAnterior;
		}

		public int getNivelAnterior() {
			return this.nivelAnterior;
		}
	}

	public GruposTratamentoNiveisForm getGruposTratamentoNiveisForm() {
		return this.gruposTratamentoNiveisForm;
	}

	public static class NivelVO {
		private int codigo;
		private String descricao;

		public int getCodigo() {
			return this.codigo;
		}

		public String getDescricao() {
			return this.descricao;
		}

		public void setCodigo(int codigo) {
			this.codigo = codigo;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}
	}
}
