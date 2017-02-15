package workflow.AtendimentoInBox;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import workflow.AtendimentoInBoxCRI.AtendimentoInBoxCRIController.AtendimentoInBoxCRIForm;
import workflow.AtendimentoWorkflow.AtendimentoWorkflowController.AtendimentoForm;
import br.com.vivo.fo.admsistemas.vo.AdmContatoFolhaVODocument.AdmContatoFolhaVO;
import br.com.vivo.fo.admsistemas.vo.ArvoreAtendimentoVODocument.ArvoreAtendimentoVO;
import br.com.vivo.fo.commons.utils.StringUtilStatic;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO;
import br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO;
import br.com.vivo.fo.workflow.vo.AtendimentoArvoreFiltrosVODocument.AtendimentoArvoreFiltrosVO;
import br.com.vivo.fo.workflow.vo.AtendimentoVODocument.AtendimentoVO;
import br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO;
import br.com.vivo.fo.workflow.vo.RWFAtendimentosVODocument.RWFAtendimentosVO;
import br.com.vivo.fo.workflow.vo.RWFInBoxPesquisaVODocument.RWFInBoxPesquisaVO;
import br.com.vivo.fo.workflow.vo.RWFInboxUserVODocument.RWFInboxUserVO;
import br.com.vivo.fo.workflow.vo.RetornoWFCTIVODocument.RetornoWFCTIVO;
import br.com.vivo.fo.workflow.vo.WFEstadoVODocument.WFEstadoVO;
import br.com.vivo.fo.workflow.vo.WFEstadosVODocument.WFEstadosVO;
import br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO;
import br.com.vivo.fo.workflow.vo.WFSubEstadoVODocument.WFSubEstadoVO;

import com.indracompany.actions.AbstractAction;

@SuppressWarnings({"rawtypes","unchecked"})
public class AtendimentoInBoxController extends AbstractAction {

	private static final long serialVersionUID = 771383533375319697L;

	private RWFInBoxUserForm rwfInboxUserForm = new RWFInBoxUserForm();
	private RWFInBoxGruposUserForm rwfInBoxGruposUserForm = new RWFInBoxGruposUserForm();
	private RWFAtendimentoForm rwfAtendimentoForm = new RWFAtendimentoForm();
	private RWFInBoxPesquisaForm rwfInboxPesquisaForm = new RWFInBoxPesquisaForm();

	public RWFInBoxUserForm getRwfInboxUserForm() {
		return rwfInboxUserForm;
	}

	public void setRwfInboxUserForm(RWFInBoxUserForm rwfInboxUserForm) {
		this.rwfInboxUserForm = rwfInboxUserForm;
	}

	public RWFInBoxGruposUserForm getRwfInBoxGruposUserForm() {
		if (this.rwfInBoxGruposUserForm == null) {
			this.rwfInBoxGruposUserForm = new RWFInBoxGruposUserForm(); 
		}
		return rwfInBoxGruposUserForm;
	}

	public void setRwfInBoxGruposUserForm(RWFInBoxGruposUserForm rwfInBoxGruposUserForm) {
		this.rwfInBoxGruposUserForm = rwfInBoxGruposUserForm;
	}

	public RWFAtendimentoForm getRwfAtendimentoForm() {
		if (this.rwfAtendimentoForm == null) {
			this.rwfAtendimentoForm = new RWFAtendimentoForm(); 
		}
		return this.rwfAtendimentoForm;
	}

	public void setRwfAtendimentoForm(RWFAtendimentoForm rwfAtendimentoForm) {
		this.rwfAtendimentoForm = rwfAtendimentoForm;
	}

	public RWFInBoxPesquisaForm getRwfInboxPesquisaForm() {
		if (this.rwfInboxPesquisaForm == null) {
			this.rwfInboxPesquisaForm = new RWFInBoxPesquisaForm();
		}
		return this.rwfInboxPesquisaForm;
	}

	public void setRwfInboxPesquisaForm(RWFInBoxPesquisaForm rwfInboxPesquisaForm) {
		this.rwfInboxPesquisaForm = rwfInboxPesquisaForm;
	}

	private AtendimentoInBoxCRIForm atendimentoInBoxCRIForm = new AtendimentoInBoxCRIForm();

	private RWFAtendimentoVO[] vAllAtendimentos = null;
	private RWFAtendimentoVO[] vEnTratamento = null;
	private RWFAtendimentoVO[] vEnPausa = null;

	private static Logger log = new Logger("workinbox");

	protected global.Global globalApp = new global.Global();

	private static final String RAIZ_PRINCIPAL = "PAI";

	@EJB
	private br.com.vivo.fo.ctrls.workflow.RAtendimento.RAtendimento rAtendimentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.atendimento.AtendimentoFacade atendimentoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.registrarContato.RegistrarContatoFacade registrarContatoFacade;

	@EJB
	private br.com.vivo.fo.ctrls.workflow.monitoramento.MonitoramentoFacade manFacade;

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);

		} else if ("RWFUser".equals(mapping.getParameter())) {
			return RWFUser(mapping, form, request, response);

		} else if ("RWFAtendimento".equals(mapping.getParameter())) {
			return RWFAtendimento(mapping, form, request, response);

		} else if ("RWFAlerta".equals(mapping.getParameter())) {
			return RWFAlerta(mapping, form, request, response);

		} else if ("obterArvoreContato".equals(mapping.getParameter())) {
			return obterArvoreContato(mapping, form, request, response);

		} else if ("expandeArvoreContato".equals(mapping.getParameter())) {
			return expandeArvoreContato(mapping, form, request, response);

		} else if ("suspeito".equals(mapping.getParameter())) {
			return suspeito(mapping, form, request, response);

		} else if ("encaminhar".equals(mapping.getParameter())) {
			return encaminhar(mapping, form, request, response);

		} else if ("analistaDisponivel".equals(mapping.getParameter())) {
			return analistaDisponivel(mapping, form, request, response);

		} else if ("RWFGrupos".equals(mapping.getParameter())) {
			return RWFGrupos(mapping, form, request, response);

		} else if ("pesquisarMensagens".equals(mapping.getParameter())) {
			return pesquisarMensagens(mapping, form, request, response);

		} else if ("pesquisarMensagensRC".equals(mapping.getParameter())) {
			return pesquisarMensagensRC(mapping, form, request, response);

		} else if ("loadEstados".equals(mapping.getParameter())) {
			return loadEstados(mapping, form, request, response);

		} else if ("show_atendimentoDetalhe".equals(mapping.getParameter())) {
			return show_atendimentoDetalhe(mapping, form, request, response);

		} else if ("AtendimentoWorkflowDone".equals(mapping.getParameter())) {
			return AtendimentoWorkflowDone(mapping, form, request, response);

		} else {
			return begin(mapping, form, request, response);
		}
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="RWFInBox.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoInBoxController:begin.do - Inicio do Metodo]");
		// user = (String)request.getSession().getAttribute(ConstantesCRM.USUARIO_IDENTIFICADOR_SESSION);
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxController:begin.do - [user = " + user + "]");

		WFGrupoVO[] grupos = manFacade.obtemGrupos(user).getWFGrupoVOArray();
		this.rwfInBoxGruposUserForm.setGruposVO(grupos);
		log.debug("AtendimentoInBoxController:begin.do - [user = " + user + "]");
		request.getSession().removeAttribute("inOrigemFila");
		log.debug("AtendimentoInBoxController:begin.do - Fim do Metodo]");

		ActionForward f = mapping.findForward(ConstantesCRM.SUCCESS);
		if (request.getParameter("voltar") != null && request.getParameter("voltar").equals("1")) {
			request.setAttribute("voltar", ConstantesCRM.SONE);
			rwfInboxUserForm.setInDisponivelProcessosInboxWF(ConstantesCRM.SZERO);
			log.debug("AtendimentoInBoxController:begin.do - [InDisponivelProcessosInboxWF = " + rwfInboxUserForm.getInDisponivelProcessosInboxWF() + "]");
		} else {
			rAtendimentoFacade.analistaDisponivel(user, ConstantesCRM.SZERO, 0);
		}
		log.debug("AtendimentoInBoxController:begin.do - Fim do Metodo]");
		loadEstados(mapping, rwfInboxPesquisaForm, request, response);

		request.setAttribute("rWFInBoxPesquisaForm", getRwfInboxPesquisaForm());
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * Llamada un servicio Tuxedo que recupera los datos del usuario y las campañas.
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="RWFUser.jsp"
	 */
	protected ActionForward RWFUser(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoInBoxController:RWFUser.do - Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxController:RWFUser.do - [user = " + user + "]");

		if (user != null) {
			// T ODO: Llamada al servicio Tuxedo que nos devuelve los datos del usuario y las campañas
			RWFInboxUserVO userVO = rAtendimentoFacade.getUsuarioCampanha(user);

			// Asignacion de los datos devueltos por el servicio al formulario de usuario
			rwfInboxUserForm.setNmLoginUsuario(userVO.getNmLoginUsuario());
			log.debug("AtendimentoInBoxController:RWFUser.do - [nmLoginUsuario = " + rwfInboxUserForm.getNmLoginUsuario() + "]");
			rwfInboxUserForm.setInDisponivelWF(userVO.getInDisponivelWF());
			log.debug("AtendimentoInBoxController:RWFUser.do - [inDisponivelWF = " + rwfInboxUserForm.getInDisponivelWF() + "]");

			if (rwfInboxUserForm.getInDisponivelProcessosInboxWF() == null) {
				rwfInboxUserForm.setInDisponivelProcessosInboxWF(userVO.getInDisponivelWF());
				log.debug("AtendimentoInBoxController:RWFUser.do - [nmLoginUsuario = " + rwfInboxUserForm.getInDisponivelProcessosInboxWF() + "]");
			}

			// T ODO: Miguel ha comentado que va a cambiar el xsd para que en vez
			// de campañas cargue otro objeto para el usuario asi que habria que hacer la llamada adecuada
			// CampanhaVO[] arrayCampanhasVO = userVO.getCampanhaVOArray();
			// rwfInboxUserForm.setCampanhas(userVO.getCampanhaVOArray());
			rwfInboxUserForm.setRetorno(userVO.getRetornoWFCTIVOArray());
		}
		log.debug("AtendimentoInBoxController:RWFUser.do - Fim do Metodo]");
		request.setAttribute("rwfInboxUserForm", rwfInboxUserForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Llamada a dos servicios Tuxedo: uno que llama al siguiente proceso y otro que recupera los atendimentos segun la
	 * pesquisa.
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="RWFAtendimento.jsp"
	 */
	protected ActionForward RWFAtendimento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		RWFInBoxPesquisaForm form = (RWFInBoxPesquisaForm) formParam;

		log.debug("AtendimentoInBoxController:RWFAtendimento.do - Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxController:RWFAtendimento.do - [user = " + user + "]");
		if (user != null) {
			if (rwfInboxUserForm.getInDisponivelWF() == null) {
				// T ODO: Llamada al servicio Tuxedo que nos devuelve los datos del usuario y las campañas
				RWFInboxUserVO userVO = rAtendimentoFacade.getUsuarioCampanha(user);

				// Asignacion de los datos devueltos por el servicio al formulario de usuario
				rwfInboxUserForm.setNmLoginUsuario(userVO.getNmLoginUsuario());
				log.debug("AtendimentoInBoxController:RWFAtendimento.do - [nmLoginUsuario = " + rwfInboxUserForm.getNmLoginUsuario() + "]");
				rwfInboxUserForm.setInDisponivelWF(userVO.getInDisponivelWF());
				log.debug("AtendimentoInBoxController:RWFAtendimento.do - [inDisponivelWF = " + rwfInboxUserForm.getInDisponivelWF() + "]");

				if (rwfInboxUserForm.getInDisponivelProcessosInboxWF() == null) {
					rwfInboxUserForm.setInDisponivelProcessosInboxWF(userVO.getInDisponivelWF());
					log.debug("AtendimentoInBoxController:RWFAtendimento.do - [inDisponivelProcessosInBoxWF = " + rwfInboxUserForm.getInDisponivelProcessosInboxWF() + "]");
				}

				rwfInboxUserForm.setRetorno(userVO.getRetornoWFCTIVOArray());
			}

			if (rwfInboxUserForm.getInDisponivelProcessosInboxWF().equals(ConstantesCRM.SONE)) {
				rAtendimentoFacade.analistaDisponivel(user, ConstantesCRM.SONE, 0);
			}

			// Obtém a lista de ramais para o callcenter
			if (rwfInboxUserForm.getInDisponivelWF().equals(ConstantesCRM.SONE)) {
				rAtendimentoFacade.obtemProximoProcesso(user, "<inCRI>0</inCRI>");
			}

			// guarda os valores atuais de pesquisa
			rwfInboxPesquisaForm = form;

			// Inicializacion de Atualizacao en el formulario de retorno
			rwfAtendimentoForm.setAtualizacao(5);
			log.debug("AtendimentoInBoxController:RWFAtendimento.do - [atualizacao = " + rwfAtendimentoForm.getAtualizacao() + "]");

			RWFInBoxPesquisaVO rwfInBoxPesquisaVO = RWFInBoxPesquisaVO.Factory.newInstance();
			if (request.getParameter("flLimpar") == null) {
				rwfInBoxPesquisaVO = this.construyePesquisaVO(form);
			}

			RWFAtendimentosVO atds = rAtendimentoFacade.getRWFAtendimentoVO(user, rwfInBoxPesquisaVO);

			this.vEnTratamento = atds.getRWFAtendimentoVOArray();

			rwfAtendimentoForm.setNrRegistros(atds.getNrRegistros());
			log.debug("AtendimentoInBoxController:RWFAtendimento.do - [nrRegistros = " + rwfAtendimentoForm.getNrRegistros() + "]");
			rwfAtendimentoForm.setTotalRegistros(atds.getTotalRegistros());
			log.debug("AtendimentoInBoxController:RWFAtendimento.do - [totalRegistros = " + rwfAtendimentoForm.getTotalRegistros() + "]");

			if (atds.getNrRegistros() == 0) {
				this.vEnTratamento = new RWFAtendimentoVO[0];
			}

			rwfAtendimentoForm.setRwfAtendimentoVO(this.vEnTratamento);
			if (request.getParameter("flLimpar") != null) {
				rwfAtendimentoForm.setFlLimpar(request.getParameter("flLimpar").toString());
			} else {
				rwfAtendimentoForm.setFlLimpar(ConstantesCRM.SZERO);
			}

		}
		log.debug("AtendimentoInBoxController:RWFAtendimento.do - Fim do Metodo]");
		request.setAttribute("rwfAtendimentoForm", rwfAtendimentoForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Accion que recupera de Tuxedo las alertas asociadas al atendimento seleccionado.
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="RWFAlerta.jsp"
	 */
	protected ActionForward RWFAlerta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoInBoxController:RWFAlerta.do - Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxController:RWFAlerta.do - [user = " + user + "]");

		RWFAlertaForm rWFAlertaForm = new RWFAlertaForm();

		String idAtendimento = request.getParameter("idAtendimento");
		log.debug("AtendimentoInBoxController:RWFAlerta.do - [idAtendimento = " + idAtendimento + "]");

		if (idAtendimento != null) {
			// T ODO: Peticion a Tux de las alertas asociadas a un usuario y un atendimiento
			AlertaVO[] vAlertas = atendimentoFacade.obtemAlertaVO(user, idAtendimento, ConstantesCRM.SONE);
			// asignacion al formulario de alertas de la peticion realizada a Tux
			rWFAlertaForm.setAlertasVO(vAlertas);

		}
		log.debug("AtendimentoInBoxController:RWFAlerta.do - Fim do Metodo]");
		request.setAttribute("rWFAlertaForm", rWFAlertaForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);

	}

	/**
	 * Obtiene el arvore de contatos.
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="iArvoreContato.jsp"
	 */
	protected ActionForward obterArvoreContato(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoInBoxController:obterArvoreContato.do - Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxController:obterArvoreContato.do - [user = " + user + "]");
		AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(user, null);

		if (admContatoFolhaVO == null) {
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SUCCESS);
		}

		StringBuffer sbScript = new StringBuffer(ConstantesCRM.SVAZIO);
		sbScript.append("if (document.getElementById) {\n\nvar tree = new WebFXTree('");
		sbScript.append(StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getNmContato()));
		sbScript.append("',\"Javascript:selecionaContato('");
		sbScript.append(admContatoFolhaVO.getIdContato());
		// sbScript.append("', '");
		// sbScript.append("');\",'classic');\n\n");
		sbScript.append("', '");
		sbScript.append("','','" + RAIZ_PRINCIPAL + "');\",'classic');\n\n");
		StringBuffer sbNode = new StringBuffer(1024);

		for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray().length; i++) {

			sbNode.setLength(0);

			String idContato = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getIdContato();
			String sInFolha = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha();
			String nmContato = StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getNmContato());
			String dsPath = StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getDsPath());

			String ico1 = ConstantesCRM.SVAZIO;
			String ico2 = ConstantesCRM.SVAZIO;

			String dblClickScript = "Javascript:itemDblClick('" + idContato + "', '" + sInFolha + "', '" + dsPath + "')";

			if (!admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha().equals("1")) {
				ico1 = "/FrontOfficeWeb/resources/images/foldericon.png";
				ico2 = "/FrontOfficeWeb/resources/images/openfoldericon.png";
				dblClickScript = "";

			}

			sbNode.append("var arvore = new WebFXTreeItem('");
			sbNode.append(nmContato);
			sbNode.append("',\"Javascript:selecionaContato('");
			sbNode.append(idContato);
			sbNode.append("','");
			sbNode.append(sInFolha);
			sbNode.append("','");
			sbNode.append(dsPath);
			sbNode.append("','");
			sbNode.append("');\",'','").append(ico1).append("','").append(ico2).append("','',\"");
			sbNode.append(dblClickScript).append("\");\n");

			sbScript.append(sbNode.toString());
			sbScript.append("tree.add(arvore);\n");

		}
		sbScript.append("document.write(tree);\n}\n");

		request.setAttribute("scriptArvore", sbScript.toString());
		log.debug("AtendimentoInBoxController:obterArvoreContato.do - [Script da Arvore de Contato = " + sbScript.toString() + "]");

		// seta nulo no objeto
		log.debug("AtendimentoInBoxController:obterArvoreContato.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Expande el arvore de contatos.
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="iExpandeArvoreContatoInBox.jsp"
	 */
	protected ActionForward expandeArvoreContato(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		ExpandeArvoreContatoForm form = (ExpandeArvoreContatoForm) formParam;

		log.debug("AtendimentoInBoxController:expandeArvoreContato.do - Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());

		log.debug("AtendimentoInBoxController:expandeArvoreContato.do - [user = " + user + "]");

		StringBuffer sbScript = new StringBuffer(1024);
		StringBuffer sbNode = new StringBuffer(1024);

		AtendimentoArvoreFiltrosVO atFiltros = AtendimentoArvoreFiltrosVO.Factory.newInstance();
		atFiltros.setIdContato(form.getIdContato());
		AdmContatoFolhaVO admContatoFolhaVO = registrarContatoFacade.carregaArvoreContato(user, atFiltros);

		for (int i = 0; i < admContatoFolhaVO.getAdmContatoFolhaVOArray().length; i++) {

			sbNode.setLength(0);

			String idContato = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getIdContato();
			String sInFolha = admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha();
			String nmContato = StringUtilStatic.escapaComillasJS(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getNmContato());
			String dsPath = StringUtilStatic.escapaComillasJS2(admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getDsPath());

			String ico1 = ConstantesCRM.SVAZIO;
			String ico2 = ConstantesCRM.SVAZIO;

			String dblClickScript = "Javascript:itemDblClick('" + idContato + "', '" + sInFolha + "', '" + dsPath + "')";

			if (!admContatoFolhaVO.getAdmContatoFolhaVOArray(i).getInFolha().equals(ConstantesCRM.SONE)) {
				ico1 = "/FrontOfficeWeb/resources/images/foldericon.png";
				ico2 = "/FrontOfficeWeb/resources/images/openfoldericon.png";
				dblClickScript = ConstantesCRM.SVAZIO;
			}

			sbNode.append("var arvore = new parent.WebFXTreeItem('");
			sbNode.append(nmContato);
			sbNode.append("',\"Javascript:selecionaContato('");
			sbNode.append(idContato);
			sbNode.append("','");
			sbNode.append(sInFolha);
			sbNode.append("','");
			sbNode.append(dsPath);
			sbNode.append("','");
			sbNode.append("');\",'','").append(ico1).append("','").append(ico2).append("','',\"");
			sbNode.append(dblClickScript).append("\");");

			sbScript.append(sbNode.toString());
			sbScript.append("parent.tree.getSelected().add(arvore);\n\n");

		}

		sbScript.append("parent.tree.getSelected().setAddEnabled(false);\n\n");
		sbScript.append("parent.tree.getSelected().expand();\n\n");

		request.setAttribute("scriptArvore", sbScript.toString());
		log.debug("AtendimentoInBoxController:expandeArvoreContato.do - [Script da Arvore de Contato = " + sbScript.toString() + "]");
		log.debug("AtendimentoInBoxController:expandeArvoreContato.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * Ejecuta la funcion suspeito del WF.
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/AtendimentoWorkflow/listaSuspeito.do"
	 * @jpf:forward name="failure" path="failure.jsp"
	 */
	protected ActionForward suspeito(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

		log.debug("AtendimentoInBoxController:suspeito.do - Inicio do Metodo]");

		ActionForward fwd = null;

		java.util.Map m = request.getParameterMap();

		AtendimentoForm atendimentoForm = this.getAtendimentoForm(m, request);

		atendimentoForm.setIdAtividade(ConstantesCRM.SSIX);
		if (atendimentoForm.getAtendimentosVO() != null && atendimentoForm.getAtendimentosVO().length > 0) {
			request.setAttribute("atendimentoForm", atendimentoForm);
			fwd = mapping.findForward(ConstantesCRM.SUCCESS);
		} else {
			fwd = mapping.findForward("failure");
		}
		log.debug("AtendimentoInBoxController:suspeito.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return fwd;
	}

	/**
	 * Ejecuta la funcion encaminhar del WF.
	 *
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/AtendimentoWorkflow/listaEncaminhar.do"
	 * @jpf:forward name="failure" path="failure.jsp"
	 */
	protected ActionForward encaminhar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoInBoxController:encaminhar.do - Inicio do Metodo]");

		ActionForward fwd = null;

		java.util.Map m = request.getParameterMap();

		AtendimentoForm atendimentoForm = this.getAtendimentoForm(m, request);

		atendimentoForm.setIdAtividade(ConstantesCRM.STHREE);
		log.debug("AtendimentoInBoxController:encaminhar.do - [idAtividade = " + atendimentoForm.getIdAtividade() + "]");
		if (atendimentoForm.getAtendimentosVO() != null && atendimentoForm.getAtendimentosVO().length > 0) {
			request.setAttribute("atendimentoForm", atendimentoForm);
			fwd = mapping.findForward(ConstantesCRM.SUCCESS);
		} else {
			fwd = mapping.findForward("failure");
		}
		log.debug("AtendimentoInBoxController:encaminhar.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return fwd;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RWFIndisponivel.jsp"
	 */
	protected ActionForward analistaDisponivel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoInBoxController:analistaDisponivel.do - Inicio do Metodo]");

		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxController:analistaDisponivel.do - [user = " + user + "]");

		String indisponivel = request.getParameter("disp");
		log.debug("AtendimentoInBoxController:analistaDisponivel.do - [indisponivel = " + indisponivel + "]");

		if ("1".equals(indisponivel)) {
			// T ODO: Se necesita un servicio al que pasandole el user y el indisponivel actualice el valor
			rAtendimentoFacade.analistaDisponivel2(user, indisponivel, 0);

		} else {
			// T ODO: Se necesita un servicio al que pasandole el user y el indisponivel actualice el valor
			rAtendimentoFacade.analistaDisponivel(user, indisponivel, 0);
		}

		rwfInboxUserForm.setInDisponivelProcessosInboxWF(indisponivel);

		log.debug("AtendimentoInBoxController:analistaDisponivel.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="RWFGrupos.jsp"
	 */
	protected ActionForward RWFGrupos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoInBoxController:RWFGrupos.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxController:RWFGrupos.do - [user = " + user + "]");
		if (user != null) {
			WFGrupoVO[] grupos = (manFacade.obtemGrupos(user)).getWFGrupoVOArray();
			rwfInBoxGruposUserForm.setGruposVO(grupos);
		}
		log.debug("AtendimentoInBoxController:RWFGrupos.do - Fim do Metodo]");

		request.setAttribute("rwfInBoxGruposUserForm", rwfInBoxGruposUserForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iMensagens.jsp"
	 */
	protected ActionForward pesquisarMensagens(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoInBoxCRIForm form = (AtendimentoInBoxCRIForm) formParam;

		log.debug("AtendimentoInBoxController:pesquisarMensagens.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxController:pesquisarMensagens.do - [user = " + user + "]");
		// Inicializacion de Atualizacao en el formulario de retorno
		this.atendimentoInBoxCRIForm.setAtualizacao(5);
		log.debug("AtendimentoInBoxController:pesquisarMensagens.do - [atualizacao = " + this.atendimentoInBoxCRIForm.getAtualizacao() + "]");
		RWFInBoxPesquisaVO rwfInBoxPesquisaVO = RWFInBoxPesquisaVO.Factory.newInstance();

		rwfInBoxPesquisaVO.setDtAberturaFim(form.getDtAberturaFim());
		log.debug("AtendimentoInBoxController:pesquisarMensagens.do - [dtAberturaFim = " + rwfInBoxPesquisaVO.getDtAberturaFim() + "]");

		rwfInBoxPesquisaVO.setDtAberturaInicio(form.getDtAberturaInicio());
		log.debug("AtendimentoInBoxController:pesquisarMensagens.do - [dtAberturaInicio = " + rwfInBoxPesquisaVO.getDtAberturaInicio() + "]");

		rwfInBoxPesquisaVO.setIdAtendimento(form.getIdAtendimento());
		log.debug("AtendimentoInBoxController:pesquisarMensagens.do - [idAtendimento = " + rwfInBoxPesquisaVO.getIdAtendimento() + "]");

		rwfInBoxPesquisaVO.setIdContato(form.getIdContato());
		log.debug("AtendimentoInBoxController:pesquisarMensagens.do - [idContato = " + rwfInBoxPesquisaVO.getIdContato() + "]");

		if (form.getNrLinha() != null) {
			rwfInBoxPesquisaVO.setNrLinha(form.getNrLinha().replaceAll("[\\(\\)-]*", ""));
		} else {
			rwfInBoxPesquisaVO.setNrLinha(null);
		}
		log.debug("AtendimentoInBoxController:pesquisarMensagens.do - [user = " + rwfInBoxPesquisaVO.getNrLinha() + "]");

		rwfInBoxPesquisaVO.setTextoContato(form.getTextoContato());
		log.debug("AtendimentoInBoxController:pesquisarMensagens.do - [textoContato = " + rwfInBoxPesquisaVO.getTextoContato() + "]");

		RWFAtendimentosVO atds = rAtendimentoFacade.getMsgInboxCRI(user, rwfInBoxPesquisaVO);

		this.atendimentoInBoxCRIForm.setNrRegistros(atds.getNrRegistros());
		log.debug("AtendimentoInBoxController:pesquisarMensagens.do - [nrRegistros = " + this.atendimentoInBoxCRIForm.getNrRegistros() + "]");

		this.atendimentoInBoxCRIForm.setTotalRegistros(atds.getTotalRegistros());
		log.debug("AtendimentoInBoxController:pesquisarMensagens.do - [totalRegistros = " + this.atendimentoInBoxCRIForm.getTotalRegistros() + "]");
		this.atendimentoInBoxCRIForm.setRwfAtendimentosVO(atds);
		log.debug("AtendimentoInBoxController:pesquisarMensagens.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="iMensagensRC.jsp"
	 */
	protected ActionForward pesquisarMensagensRC(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		AtendimentoInBoxCRIForm form = (AtendimentoInBoxCRIForm) formParam;

		log.debug("AtendimentoInBoxController:pesquisarMensagensRC.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxController:pesquisarMensagensRC.do - [user = " + user + "]");

		// Inicializacion de Atualizacao en el formulario de retorno
		this.atendimentoInBoxCRIForm.setAtualizacao(5);
		log.debug("AtendimentoInBoxController:pesquisarMensagensRC.do - [atualizacao = " + this.atendimentoInBoxCRIForm.getAtualizacao() + "]");

		RWFInBoxPesquisaVO rwfInBoxPesquisaVO = RWFInBoxPesquisaVO.Factory.newInstance();

		rwfInBoxPesquisaVO.setDtAberturaFim(form.getDtAberturaFim());
		log.debug("AtendimentoInBoxController:pesquisarMensagensRC.do - [dtAberturaFim = " + rwfInBoxPesquisaVO.getDtAberturaFim() + "]");

		rwfInBoxPesquisaVO.setDtAberturaInicio(form.getDtAberturaInicio());
		log.debug("AtendimentoInBoxController:pesquisarMensagensRC.do - [dtAberturaInicio = " + rwfInBoxPesquisaVO.getDtAberturaInicio() + "]");

		rwfInBoxPesquisaVO.setIdAtendimento(form.getIdAtendimento());
		log.debug("AtendimentoInBoxController:pesquisarMensagensRC.do - [idAtendimento = " + rwfInBoxPesquisaVO.getIdAtendimento() + "]");

		rwfInBoxPesquisaVO.setIdContato(form.getIdContato());
		log.debug("AtendimentoInBoxController:pesquisarMensagensRC.do - [idContato = " + rwfInBoxPesquisaVO.getIdContato() + "]");

		if (form.getNrLinha() != null) {
			rwfInBoxPesquisaVO.setNrLinha(form.getNrLinha().replaceAll("[\\(\\)-]*", ""));
		} else {
			rwfInBoxPesquisaVO.setNrLinha(null);
		}
		log.debug("AtendimentoInBoxController:pesquisarMensagensRC.do - [nrLinha = " + rwfInBoxPesquisaVO.getNrLinha() + "]");

		rwfInBoxPesquisaVO.setTextoContato(form.getTextoContato());
		log.debug("AtendimentoInBoxController:pesquisarMensagensRC.do - [textoContato = " + rwfInBoxPesquisaVO.getTextoContato() + "]");

		RWFAtendimentosVO atds = rAtendimentoFacade.getMsgInboxRC(user, rwfInBoxPesquisaVO);

		this.atendimentoInBoxCRIForm.setNrRegistros(atds.getNrRegistros());
		log.debug("AtendimentoInBoxController:pesquisarMensagensRC.do - [nrRegistros = " + this.atendimentoInBoxCRIForm.getNrRegistros() + "]");

		this.atendimentoInBoxCRIForm.setTotalRegistros(atds.getTotalRegistros());
		log.debug("AtendimentoInBoxController:pesquisarMensagensRC.do - [totalRegistros = " + this.atendimentoInBoxCRIForm.getTotalRegistros() + "]");

		this.atendimentoInBoxCRIForm.setRwfAtendimentosVO(atds);
		log.debug("AtendimentoInBoxController:pesquisarMensagensRC.do - Fim do Metodo]");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 */
	protected ActionForward loadEstados(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		log.debug("AtendimentoInBoxController:loadEstados.do - Inicio do Metodo]");
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		log.debug("AtendimentoInBoxController:loadEstados.do - [user = " + user + "]");
		this.rwfInboxPesquisaForm.setEstadosVO(atendimentoFacade.getWFEstadosVO(user, false));
		log.debug("AtendimentoInBoxController:loadEstados.do - Fim do Metodo]");
		return null;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="/workflow/AtendimentoDetalhe/AtendimentoDetalheController.jpf"
	 */
	protected ActionForward show_atendimentoDetalhe(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoInBoxController:show_atendimentoDetalhe.do - Inicio do Metodo]");

		ActionRedirect f = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		f.addParameter("idAtendimento", request.getParameter("idAtendimento"));
		f.addParameter("fila", request.getParameter("fila"));
		log.debug("AtendimentoInBoxController:show_atendimentoDetalhe.do - Fim do Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return f;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="voltaWorkflow.jsp"
	 */
	protected ActionForward AtendimentoWorkflowDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		log.debug("AtendimentoInBoxController:AtendimentoWorkflowDone.do - Metodo]");

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class RWFInBoxUserForm extends ActionForm {

		private static final long serialVersionUID = 3625132944246348196L;

		private RetornoWFCTIVO[] retorno;
		private String inDisponivelWF;
		private String nmLoginUsuario;
		private String inDisponivelProcessosInboxWF;

		public void setNmLoginUsuario(String nmLoginUsuario) {
			this.nmLoginUsuario = nmLoginUsuario;
		}

		public String getNmLoginUsuario() {
			return this.nmLoginUsuario;
		}

		public void setInDisponivelProcessosInboxWF(String inDisponivelProcessosInboxWF) {
			this.inDisponivelProcessosInboxWF = inDisponivelProcessosInboxWF;
		}

		public String getInDisponivelProcessosInboxWF() {
			return this.inDisponivelProcessosInboxWF;
		}

		public void setInDisponivelWF(String inDisponivelWF) {
			this.inDisponivelWF = inDisponivelWF;
		}

		public String getInDisponivelWF() {
			return this.inDisponivelWF;
		}

		public void setRetorno(RetornoWFCTIVO[] retorno) {
			this.retorno = retorno;
		}

		public RetornoWFCTIVO[] getRetorno() {
			return this.retorno;
		}
	}

	public static class RWFAlertaForm extends ActionForm {

		private static final long serialVersionUID = -5746588623424452315L;

		private AlertaVO[] alertasVO;

		public void setAlertasVO(AlertaVO[] alertasVO) {
			this.alertasVO = alertasVO;
		}

		public AlertaVO[] getAlertasVO() {
			if (this.alertasVO == null || this.alertasVO.length == 0) {
				this.alertasVO[0] = AlertaVO.Factory.newInstance();
			}
			return this.alertasVO;
		}
	}

	public static class RWFAtendimentoForm extends ActionForm {

		private static final long serialVersionUID = -3303480780708600971L;

		private String flLimpar;
		private String tabPausa;
		private int totalRegistros;
		private int nrRegistros;
		private int atualizacao;
		private RWFAtendimentoVO[] rwfAtendimentoVO;

		public void setRwfAtendimentoVO(RWFAtendimentoVO[] rwfAtendimentoVO) {
			this.rwfAtendimentoVO = rwfAtendimentoVO;
		}

		public RWFAtendimentoVO[] getRwfAtendimentoVO() {
			if (this.rwfAtendimentoVO == null || this.rwfAtendimentoVO.length == 0) {
				this.rwfAtendimentoVO[0] = RWFAtendimentoVO.Factory.newInstance();
			}

			return this.rwfAtendimentoVO;
		}

		public void setAtualizacao(int atualizacao) {
			this.atualizacao = atualizacao;
		}

		public int getAtualizacao() {
			return this.atualizacao;
		}

		public void setNrRegistros(int nrRegistros) {
			this.nrRegistros = nrRegistros;
		}

		public int getNrRegistros() {
			return this.nrRegistros;
		}

		public void setTotalRegistros(int totalRegistros) {
			this.totalRegistros = totalRegistros;
		}

		public int getTotalRegistros() {
			return this.totalRegistros;
		}

		public void setTabPausa(String tabPausa) {
			this.tabPausa = tabPausa;
		}

		public String getTabPausa() {
			return this.tabPausa;
		}

		public void setFlLimpar(String flLimpar) {
			this.flLimpar = flLimpar;
		}

		public String getFlLimpar() {
			if (this.flLimpar == null) {
				this.flLimpar = ConstantesCRM.SZERO;
			}
			return this.flLimpar;
		}
	}

	public static class RWFInBoxPesquisaForm extends ActionForm {

		private static final long serialVersionUID = 255321109817028915L;

		private WFEstadosVO estadosVO;
		private String tabPausa;
		private String textoContato;
		private String idContato;
		private String dtFechamentoFim;
		private String dtFechamentoInicio;
		private String dtAberturaFim;
		private String dtAberturaInicio;
		private String nrLinha;
		private String idAtendimento;
		private String nrProtocolo;
		private String idSubEstado;
		private String idEstado;
		private String idGrupo;
		private int radioButton;
		
		public RWFInBoxPesquisaForm(){}

		public void setRadioButton(int radio) {
			this.radioButton = radio;
		}

		public int getRadioButton() {
			return this.radioButton;
		}

		public void setIdGrupo(String idGrupo) {
			this.idGrupo = idGrupo;
		}

		public String getIdGrupo() {
			return this.idGrupo;
		}

		public void setIdEstado(String idEstado) {
			this.idEstado = idEstado;
		}

		public String getIdEstado() {
			return this.idEstado;
		}

		public void setIdSubEstado(String idSubEstado) {
			this.idSubEstado = idSubEstado;
		}

		public String getIdSubEstado() {
			return this.idSubEstado;
		}

		public void setIdAtendimento(String idAtendimento) {
			this.idAtendimento = idAtendimento;
		}

		public String getIdAtendimento() {
			return this.idAtendimento;
		}

		public void setNrProtocolo(String nrProtocolo) {
			this.nrProtocolo = nrProtocolo;
		}

		public String getNrProtocolo() {
			return this.nrProtocolo;
		}

		public void setNrLinha(String nrLinha) {
			this.nrLinha = nrLinha;
		}

		public String getNrLinha() {
			return this.nrLinha;
		}

		public void setDtAberturaInicio(String dtAberturaInicio) {
			this.dtAberturaInicio = dtAberturaInicio;
		}

		public String getDtAberturaInicio() {
			return this.dtAberturaInicio;
		}

		public void setDtAberturaFim(String dtAberturaFim) {
			this.dtAberturaFim = dtAberturaFim;
		}

		public String getDtAberturaFim() {
			return this.dtAberturaFim;
		}

		public void setDtFechamentoInicio(String dtFechamentoInicio) {
			this.dtFechamentoInicio = dtFechamentoInicio;
		}

		public String getDtFechamentoInicio() {
			return this.dtFechamentoInicio;
		}

		public void setDtFechamentoFim(String dtFechamentoFim) {
			this.dtFechamentoFim = dtFechamentoFim;
		}

		public String getDtFechamentoFim() {
			return this.dtFechamentoFim;
		}

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setTextoContato(String textoContato) {
			this.textoContato = textoContato;
		}

		public String getTextoContato() {
			return this.textoContato;
		}

		public void setTabPausa(String tabPausa) {
			this.tabPausa = tabPausa;
		}

		public String getTabPausa() {
			return this.tabPausa;
		}

		public void setEstadosVO(WFEstadosVO estadosVO) {
			this.estadosVO = estadosVO;
		}

		public WFEstadosVO getEstadosVO() {
			return this.estadosVO;
		}

	}

	public RWFInBoxGruposUserForm getRWFInBoxGruposUserForm() {
		return this.rwfInBoxGruposUserForm;
	}

	public static class RWFInBoxGruposUserForm extends ActionForm {

		private static final long serialVersionUID = -5569498251907789665L;

		private String idGrupo;

		public void setIdGrupo(String idGrupo) {
			this.idGrupo = idGrupo;
		}

		public String getIdGrupo() {
			return this.idGrupo;
		}

		private WFGrupoVO[] gruposVO;

		public void setGruposVO(WFGrupoVO[] gruposVO) {
			this.gruposVO = gruposVO;
		}

		public WFGrupoVO[] getGruposVO() {
			return this.gruposVO;
		}
	}

	/*
	 * Comparador para la ordenacion de objetos RWFAtendimentoVO
	 */
	private class BinarySearchComparator implements Comparator, Serializable {

		private static final long serialVersionUID = 6129169747839312503L;

		public int compare(Object o1, Object o2) {
			String stro2 = o2.toString();
			String strIdAtd = stro2.substring(2);
			//Double intIdAtd = new Double(strIdAtd);
			Double intIdAtdVO = new Double(((RWFAtendimentoVO) o1).getIdAtendimento());
			int value = new Double(strIdAtd).compareTo(intIdAtdVO);
			return value; /* igual */
		}
	}

	/*
	 * Comparador para la ordenacion del array de objetos RWFAtendimentoVO
	 */
	private class SortComparator implements Comparator, Serializable {

		private static final long serialVersionUID = 6676467858894059937L;

		public int compare(Object o1, Object o2) {
			Double intIdAtd1 = new Double(((RWFAtendimentoVO) o1).getIdAtendimento());
			Double intIdAtd2 = new Double(((RWFAtendimentoVO) o2).getIdAtendimento());
			int value = intIdAtd1.compareTo(intIdAtd2);
			/*if (intIdAtd1 > intIdAtd2) {
				return -1;
			} else if (intIdAtd1 < intIdAtd2) {
				return 1;
			}*/
			return value; /* igual */
		}
	}

	/*
	 * Metodo que permite construir un objeto RWFInBoxPesquisaVO a partir de un formulario RWFInBoxPesquisaForm
	 */
	private RWFInBoxPesquisaVO construyePesquisaVO(RWFInBoxPesquisaForm form) {
		RWFInBoxPesquisaVO rwfInBoxPesquisaVO = RWFInBoxPesquisaVO.Factory.newInstance();
		rwfInBoxPesquisaVO.setTabPausa(form.getTabPausa());

		if (form.getTabPausa() != null && form.getTabPausa().equals(ConstantesCRM.STWO)) {

			rwfInBoxPesquisaVO.setDtAberturaFim(form.getDtAberturaFim());
			rwfInBoxPesquisaVO.setDtAberturaInicio(form.getDtAberturaInicio());
			if (form.getDtFechamentoFim() != null && form.getDtFechamentoFim().length() != 0) {
				rwfInBoxPesquisaVO.setDtFechamentoFim(form.getDtFechamentoFim());
			}
			if (form.getDtFechamentoInicio() != null && form.getDtFechamentoInicio().length() != 0) {
				rwfInBoxPesquisaVO.setDtFechamentoInicio(form.getDtFechamentoInicio());
			}
			rwfInBoxPesquisaVO.setIdAtendimento(form.getIdAtendimento());
			rwfInBoxPesquisaVO.setNrProtocolo(form.getNrProtocolo());
			rwfInBoxPesquisaVO.setIdContato(form.getIdContato());
			rwfInBoxPesquisaVO.setIdEstado(form.getIdEstado());
			log.debug("\t\t\t idEstado: " + form.getIdEstado());
			rwfInBoxPesquisaVO.setIdGrupo(form.getIdGrupo());
			rwfInBoxPesquisaVO.setIdSubEstado(form.getIdSubEstado());
			log.debug("\t\t\t idSubEstado: " + form.getIdSubEstado());
			if (form.getNrLinha() != null) {
				rwfInBoxPesquisaVO.setNrLinha(form.getNrLinha().replaceAll("[\\(\\)-]*", ""));
			} else {
				rwfInBoxPesquisaVO.setNrLinha(null);
			}
			rwfInBoxPesquisaVO.setTextoContato(form.getTextoContato());

		}
		return rwfInBoxPesquisaVO;
	}

	/*
	 * Metodo que monta el array con todos los atendimentos
	 */
	private void construyeArrayAtendimentos() {
		int tamEnTrat = 0;
		int tamEnPau = 0;

		if (this.vEnTratamento != null) {
			tamEnTrat = this.vEnTratamento.length;
		}

		if (this.vEnPausa != null) {
			tamEnPau = this.vEnPausa.length;
		}

		int tam = tamEnTrat + tamEnPau;
		this.vAllAtendimentos = new RWFAtendimentoVO[tam];

		for (int i = 0; i < tamEnTrat; i++) {
			this.vAllAtendimentos[i] = this.vEnTratamento[i];
		}

		int k = 0;
		for (int i = tamEnTrat; i < tam; i++) {
			this.vAllAtendimentos[i] = this.vEnPausa[k];
			k++;
		}
	}

	/*
	 * Adaptador: monta el formulario que reciben los jpf de suspeito y encaminhar
	 */
	private AtendimentoForm getAtendimentoForm(java.util.Map m, HttpServletRequest request) throws Exception {
		// cogo los atendimentos del formulario y vuelco los datos que contienen
		// en objetos validos para los AtendimentoVO que va a contener el AtendimentoForm
		int numIds = m.size();
		AtendimentoVO[] atendimentosVO = new AtendimentoVO[numIds];

		if (this.vAllAtendimentos == null) {
			this.construyeArrayAtendimentos();
		}

		Arrays.sort(this.vAllAtendimentos, new SortComparator());

		BinarySearchComparator bsComparator = new BinarySearchComparator();
		java.util.Set s = m.keySet();
		Iterator it = s.iterator();
		int i = 0;
		for (it = s.iterator(); it.hasNext();) {
			String str = (String) it.next();
			log.debug("\t\t\t parametro recuperado de la request: " + str);

			int indice = Arrays.binarySearch(this.vAllAtendimentos, str, bsComparator);
			if (indice >= 0) {
				// rellenamos el AtendimentoForm
				AtendimentoVO atdVO = AtendimentoVO.Factory.newInstance();
				atdVO.setIdAtendimento(this.vAllAtendimentos[indice].getIdAtendimento());
				atdVO.setInResponsavelAbertura(this.vAllAtendimentos[indice].getInResponsavelAbertura());
				atdVO.setInResponsavelAbertura(this.vAllAtendimentos[indice].getInResponsavelAbertura());
				atdVO.setDtAbertura(this.vAllAtendimentos[indice].getDtAbertura());
				atdVO.setDtFechamento(this.vAllAtendimentos[indice].getDtFechamento());
				atdVO.setDtFimPausaAtendimento(this.vAllAtendimentos[indice].getDtFimPausaAtendimento());
				atdVO.setDtParaFechamento(this.vAllAtendimentos[indice].getDtParaFechamento());
				atdVO.setTabPausa(this.vAllAtendimentos[indice].getTabPausa());
				atdVO.setTpOperacao(this.vAllAtendimentos[indice].getTpOperacao());
				atdVO.setTpOperacao(this.vAllAtendimentos[indice].getTpOperacao());
				atdVO.setDtSuspeito(this.vAllAtendimentos[indice].getDtSuspeito());
				atdVO.setIdAtendimentoSuspeito(this.vAllAtendimentos[indice].getIdAtendimentoSuspeito());
				atdVO.setIdAtendimentoBaixaHistorico(this.vAllAtendimentos[indice].getIdAtendimentoBaixaHistorico());
				atdVO.setNmURLDados(this.vAllAtendimentos[indice].getNmURLDados());
				atdVO.setDtSolicitacaoCancelamento(this.vAllAtendimentos[indice].getDtSolicitacaoCancelamento());
				atdVO.setIdAtendimentoOrigem(this.vAllAtendimentos[indice].getIdAtendimentoOrigem());
				atdVO.setNrTelefone(this.vAllAtendimentos[indice].getNrTelefone());

				WFEstadoVO wfEstadoVO = WFEstadoVO.Factory.newInstance();
				wfEstadoVO.setDsEstado(this.vAllAtendimentos[indice].getDsEstado());
				atdVO.setWFEstadoVO(wfEstadoVO);

				WFSubEstadoVO wfSubEstadoVO = WFSubEstadoVO.Factory.newInstance();
				wfSubEstadoVO.setDsSubEstado(this.vAllAtendimentos[indice].getDsSubEstado());
				atdVO.setWFSubEstadoVO(wfSubEstadoVO);

				ArvoreAtendimentoVO arvoreVO = ArvoreAtendimentoVO.Factory.newInstance();
				arvoreVO.setDescricaoCompleta(this.vAllAtendimentos[indice].getDescricaoCompleta());
				atdVO.setArvoreAtendimentoVO(arvoreVO);

				UsuarioVIVO usuarioVIVO = UsuarioVIVO.Factory.newInstance();
				usuarioVIVO.setNmNome(this.vAllAtendimentos[indice].getNmNome());
				atdVO.setUsuarioVIVO(usuarioVIVO);

				AlertaVO[] alertaVO = new AlertaVO[1];
				alertaVO[0] = AlertaVO.Factory.newInstance();
				alertaVO[0].setNmCor(this.vAllAtendimentos[indice].getNmCor() == null ? ConstantesCRM.SVAZIO : this.vAllAtendimentos[indice].getNmCor());
				atdVO.setAlertaVOArray(alertaVO);

				atendimentosVO[i] = atdVO;
				i++;
			}
		}

		// AtendimentoForm: tipo de objeto que recibe el workflow para encaminhar
		AtendimentoForm atdForm = new AtendimentoForm();

		// asignacion del array de atendimentos al AtendimentoForm
		atdForm.setAtendimentosVO(atendimentosVO);

		// asignacion del user al AtendimentoForm
		String user = ConstantesCRM.getCurrentUser(request.getSession());

		// asignacion de la fila
		String fila = "/FrontOfficeWeb/workflow/AtendimentoInBox/begin.do";
		atdForm.setFila(fila);

		// asignacion de los grupos
		WFGrupoVO[] grupos = (manFacade.obtemGrupos(user)).getWFGrupoVOArray();
		atdForm.setWFGruposVO(grupos);

		return atdForm;
	}

	public static class ExpandeArvoreContatoForm extends ActionForm {

		private static final long serialVersionUID = -5594535355727612627L;

		private String scriptArvore;
		private String idContato;

		public void setIdContato(String idContato) {
			this.idContato = idContato;
		}

		public String getIdContato() {
			return this.idContato;
		}

		public void setScriptArvore(String scriptArvore) {
			this.scriptArvore = scriptArvore;
		}

		public String getScriptArvore() {
			return this.scriptArvore;
		}
	}

	public AtendimentoInBoxCRIForm getAtendimentoInBoxCRIForm() {
		return this.atendimentoInBoxCRIForm;
	}

	public void setAtendimentoInBoxCRIForm(AtendimentoInBoxCRIForm atendimentoInBoxCRIForm) {
		this.atendimentoInBoxCRIForm = atendimentoInBoxCRIForm;
	}
}