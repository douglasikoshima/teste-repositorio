package fidelizacao.Configurar.ConfigurarMensagemResultado;

import java.util.ArrayList;
import java.util.HashMap;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.fidelizacao.configurar.MensagemResultadoFacade;
import br.com.vivo.fo.exception.TuxedoWarningException;
import br.com.vivo.fo.fidelizacao.vo.FidelizacaoListaGeralVODocument.FidelizacaoListaGeralVO;
import br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument;
import br.com.vivo.fo.fidelizacao.vo.MensagemResultadoVODocument.MensagemResultadoVO;
import br.com.vivo.fo.fidelizacao.vo.MensajePesquisaInVODocument.MensajePesquisaInVO;
import br.com.vivo.fo.fidelizacao.vo.MensajePesquisaVODocument.MensajePesquisaVO;
import br.com.vivo.fo.log.Logger;
import br.com.vivo.fo.retornotux.vo.RetornoVODocument.RetornoVO;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class ConfigurarMensagemController extends AbstractAction {

	private static final long serialVersionUID = 8684019283299438210L;

	protected global.Global globalApp = new global.Global();

	@EJB
	private MensagemResultadoFacade mensagemFac;

	private HashMap listaRegionais = new HashMap();
	public HashMap listaIntencao = new HashMap();

	public ArrayList lista = new ArrayList();
	public ArrayList teste = new ArrayList();
	private static transient Logger log = new Logger("Fidelizacao");

	private boolean editar = false;
	public ArrayList lMensagem = new ArrayList();

	private ListaMsgEncerramentoForm listaMsgEncerramentoForm = new ListaMsgEncerramentoForm();

	public ListaMsgEncerramentoForm getlistaMsgEncerramentoForm() {
		return listaMsgEncerramentoForm;
	}

	public HashMap getListaRegionais() {
		return this.listaRegionais;
	}

	public HashMap getIntencaoCancelamento() {
		return this.listaIntencao;
	}

	private String idUsuario;

	private String getIdUsuario(HttpServletRequest request) {
		idUsuario = ConstantesCRM.getCurrentUser(request.getSession());
		return idUsuario;

	}

	// Busca as regionais para carregar a drop down de Regional
	public void initListaRegionais(HttpServletRequest request) throws Exception {

		FidelizacaoListaGeralVO temp = mensagemFac.getRegional(getIdUsuario(request));
		for (int i = 0; i < temp.getItemListaVOArray().length; i++) {
			listaRegionais.put(temp.getItemListaVOArray(i).getId(), temp.getItemListaVOArray(i).getDescricao());
		}
		listaMsgEncerramentoForm.setRegionalArray(temp.getItemListaVOArray());
	}

	public void initListaTpEncerramento(HttpServletRequest request) throws Exception {
		// Passo o 1 para Intenção de Cancelamento e 0 * para Todas
		FidelizacaoListaGeralVO temp = mensagemFac.getTpEncerramento(getIdUsuario(request));
		for (int i = 0; i < temp.getItemListaVOArray().length; i++) {
			listaIntencao.put(temp.getItemListaVOArray(i).getId(), temp.getItemListaVOArray(i).getDescricao());
		}
		listaMsgEncerramentoForm.setAcaoRetencionArray(temp.getItemListaVOArray());
		// listaIntencao.put(0 , "Todas");
	}

	private void buscaMensagemResultado(String idRegional, String mensagem, String acaoRetencao, HttpServletRequest request) throws Exception {
		lMensagem.clear();
		String[] dados = { idRegional, mensagem, acaoRetencao };

		if (dados[0] == null || ConstantesCRM.SVAZIO.equals(dados[0])) {
			dados[0] = ConstantesCRM.SZERO;
		}
		if (dados[1] == null || ConstantesCRM.SVAZIO.equals(dados[1])) {
			dados[1] = "*";
		}
		if (dados[2] == null || ConstantesCRM.SVAZIO.equals(dados[2])) {
			dados[2] = ConstantesCRM.SZERO;
		}

		MensajePesquisaVO mensajePesquisaVO = MensajePesquisaVO.Factory.newInstance();
		mensajePesquisaVO.setIdUfOperadora(dados[0]);
		mensajePesquisaVO.setDsMsgEncerramento(dados[1]);
		mensajePesquisaVO.setIdTpEncerramento(dados[2]);
		MensagemResultadoVO[] lista = mensagemFac.getMensagemResultado(getIdUsuario(request), mensajePesquisaVO).getMensagemResultadoVOArray();
		if (lista.length == 0) {
			lista = new MensagemResultadoVO[1];
			MensagemResultadoVO mensagemResultadoVO = MensagemResultadoVO.Factory.newInstance();
			lista[0] = mensagemResultadoVO;
		}
		for (int i = 0; i < lista.length; i++) {
			lMensagem.add(lista[i]);
		}
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("done".equals(mapping.getParameter())) {
			return done(mapping, form, request, response);
		} else if ("ListaMsgEncerramento".equals(mapping.getParameter())) {
			return ListaMsgEncerramento(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ConfigurarMensagem.jsp"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		listaMsgEncerramentoForm = new ListaMsgEncerramentoForm();
		lMensagem = new ArrayList();
		initListaRegionais(request);
		initListaTpEncerramento(request);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		request.setAttribute("lMensagem", lMensagem);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="done" return-action="ConfigurarMensagemDone"
	 */
	public ActionForward done(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		request.setAttribute("lMensagem", lMensagem);
		return mapping.findForward("done");
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="ConfigurarMensagem.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 * @jpf:forward name="success2" path="erro.jsp"
	 */
	protected ActionForward ListaMsgEncerramento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ListaMsgEncerramentoForm form = (ListaMsgEncerramentoForm) formParam;
		try {
			String regional = form.getRegionalSelecionado();
			String atencao = form.getAcaoRetencaoSelecionado();

			if (request.getParameter(ConstantesCRM.SACTION) != null) {
				if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("consultar")) {

					buscaMensagemResultado(form.getRegionalSelecionado(), form.getDescricao(), form.getAcaoRetencaoSelecionado(), request);
					editar = false;
					form.setDescricao(ConstantesCRM.SVAZIO);

				} else if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("salvar")) {
					if (form.getRegionalSelecionado() != null && !form.getRegionalSelecionado().equals(ConstantesCRM.SVAZIO) && form.getAcaoRetencaoSelecionado() != null
							&& !form.getAcaoRetencaoSelecionado().equals(ConstantesCRM.SVAZIO) && form.getDescricao() != null && !form.getDescricao().equals(ConstantesCRM.SVAZIO)) {

						if (editar == false) {
							// String[] mensagem = { form.getIdRegional(),
							// form.getAcaoRetencao() , form.getDescricao()
							// , form.getRegionalSelecionado()};
							String[] mensagem = { form.getRegionalSelecionado(), form.getAcaoRetencaoSelecionado(), form.getDescricao() };

							form.setDescricao(ConstantesCRM.SVAZIO);
							form.setAcaoRetencao(ConstantesCRM.SVAZIO);
							// Testa se ja existe a descrição
							/**
							 * for (int i = 0 ; i< lMensagem.size(); i++){
							 * MensagemResultadoVO lAux =
							 * (MensagemResultadoVO)lMensagem.get(i); if
							 * (lAux.getDescricao
							 * ().equalsIgnoreCase(mensagem[2]) ||
							 * lAux.getIdAcaoRetencao().equals(mensagem[1])||
							 * lAux.getIdRegional().equals(mensagem[0])){
							 * form.setInMsgRetorno("true");
							 * request.setAttribute(
							 * ConstantesCRM.SKEY_REQUEST_PAGEFLOW
							 * , this); return mapping.findForward("success1");
							 * } }
							 **/
							try {

								MensajePesquisaInVO mensajePesquisaInVO = MensajePesquisaInVO.Factory.newInstance();
								mensajePesquisaInVO.setIdUfOperadora(mensagem[0]);
								mensajePesquisaInVO.setIdTipoEncerramento(mensagem[1]);
								mensajePesquisaInVO.setDsMsgEncerramento(mensagem[2]);
								RetornoVO retorno = mensagemFac.addMensagemResultado(getIdUsuario(request), mensajePesquisaInVO);
								int idRetorno = Integer.parseInt(retorno.getValor());
								String sDsRetorno = retorno.getDescricao();
								if ((sDsRetorno != null) && (sDsRetorno.indexOf("EXISTE") >= 0) && (idRetorno > 0)) {
									form.setInMsgRetorno("true");
									request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
									return mapping.findForward("success2");
								}

							} catch (Exception e) {
								throw e;
							}
						} else {
							try {
								MensagemResultadoVO mensagemResultado = MensagemResultadoVO.Factory.newInstance();
								mensagemResultado.setIdAcaoRetencao(atencao);
								mensagemResultado.setIdRegional(regional);
								mensagemResultado.setDescricao(form.getDescricao());
								//int i = Integer.parseInt(request.getParameter("valor"));
								mensagemResultado.setIdMensagemResultado(listaMsgEncerramentoForm.getIdMensagemResultado());

								mensagemFac.setMensagemResultado(getIdUsuario(request), mensagemResultado);
							} catch (Exception e) {
								throw e;
							}
							form.setDescricao(ConstantesCRM.SVAZIO);
							form.setAcaoRetencao(ConstantesCRM.SVAZIO);
							editar = false;
						}
						buscaMensagemResultado(form.getRegionalSelecionado(), form.getDescricao(), form.getAcaoRetencaoSelecionado(), request);
					}
				}
				if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("editar")) {

					int i = Integer.parseInt(request.getParameter("valor"));

					form.setDescricao(((MensagemResultadoVO) lMensagem.get(i)).getDescricao());
					form.setAcaoRetencaoSelecionado(((MensagemResultadoVO) lMensagem.get(i)).getIdAcaoRetencao());
					// form.setAcaoRetencao(((MensagemResultadoVO)lMensagem.get(i)).getDsAcaoRetencao());
					form.setIdMensagemResultado(((MensagemResultadoVO) lMensagem.get(i)).getIdMensagemResultado());
					// form.setRegionalSelecionado(((MensagemResultadoVO)lMensagem.get(i)).getDsRegional());
					lMensagem.remove(i);

					editar = true;

				}
				if (request.getParameter(ConstantesCRM.SACTION).equalsIgnoreCase("excluir")) {
					try {
						mensagemFac.delMensagemEncerramento(getIdUsuario(request), ((MensagemResultadoVO) lMensagem.get(Integer.parseInt(request.getParameter("valor")))).getIdMensagemResultado());
					} catch (Exception e) {
						throw e;
					}

					buscaMensagemResultado(form.getRegionalSelecionado(), form.getDescricao(), form.getAcaoRetencaoSelecionado(), request);
				}
			}
			listaMsgEncerramentoForm.setRegionalSelecionado(form.getRegionalSelecionado());
			listaMsgEncerramentoForm.setAcaoRetencaoSelecionado(form.getAcaoRetencaoSelecionado());
			listaMsgEncerramentoForm.setIdMensagemResultado(form.getIdMensagemResultado());
			form.setAcaoRetencaoSelecionado(atencao);
			form.setRegionalSelecionado(regional);
			
			listaMsgEncerramentoForm.setDescricao(form.getDescricao());

		} catch (TuxedoWarningException twe) {
			log.warn("ConfigurarMensagemController:ListaMsgEncerramento(" + twe.getMessage() + ")");

		} catch (Exception e) {
			// Observação: Monta URL de retorno e desvia para o erro
			FormError formError = globalApp.buildFormError(e, request);
			request.setAttribute(ConstantesCRM.SFORMERROR, formError);
			request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
			return mapping.findForward(ConstantesCRM.SERROR);
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		request.setAttribute("lMensagem", lMensagem);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ListaMsgEncerramentoForm extends ActionForm {

		private static final long serialVersionUID = 4327242828228580729L;

		private br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] acaoRetencionArray;
		private ItemListaVODocument acaoRetencion;
		private br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] regionalArray;
		private ItemListaVODocument regional;
		private String inMsgRetorno;
		private String regionalSelecionado;
		private String acaoRetencaoSelecionado;
		private String idRegional;
		private String dsRegional;
		private String descricao;
		private String acaoRetencao;
		private String idMensagemResultado;
		private MensagemResultadoVO mensagemResultadoVO = null;

		public void setMensagemResultadoVO(MensagemResultadoVO mensagemResultado) {
			this.descricao = mensagemResultado.getDescricao();
			this.idMensagemResultado = mensagemResultado.getIdMensagemResultado();
		}

		public MensagemResultadoVO getMensagemResultadoVO() {
			this.mensagemResultadoVO = MensagemResultadoVO.Factory.newInstance();
			this.mensagemResultadoVO.setDescricao(this.descricao);
			this.mensagemResultadoVO.setIdMensagemResultado(this.idMensagemResultado);

			return this.mensagemResultadoVO;
		}

		public void setIdMensagemResultado(String idMensagemResultado) {
			this.idMensagemResultado = idMensagemResultado;
		}

		public String getIdMensagemResultado() {
			return this.idMensagemResultado;
		}

		public void setAcaoRetencao(String acaoRetencao) {
			this.acaoRetencao = acaoRetencao;
		}

		public String getAcaoRetencao() {
			return this.acaoRetencao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return this.descricao;
		}

		public void setIdRegional(String idRegional) {
			this.idRegional = idRegional;
		}

		public String getIdRegional() {
			return this.idRegional;
		}

		public void setDsRegional(String dsRegional) {
			this.dsRegional = dsRegional;
		}

		public String getDsRegional() {
			return this.dsRegional;
		}

		public void setAcaoRetencaoSelecionado(String acaoRetencaoSelecionado) {
			this.acaoRetencaoSelecionado = acaoRetencaoSelecionado;
		}

		public String getAcaoRetencaoSelecionado() {
			return this.acaoRetencaoSelecionado;
		}

		public void setRegionalSelecionado(String regionalSelecionado) {
			this.regionalSelecionado = regionalSelecionado;
		}

		public String getRegionalSelecionado() {
			return this.regionalSelecionado;
		}

		public void setInMsgRetorno(String inMsgRetorno) {
			this.inMsgRetorno = inMsgRetorno;
		}

		public String getInMsgRetorno() {
			return this.inMsgRetorno;
		}

		public void setRegional(ItemListaVODocument regional) {
			this.regional = regional;
		}

		public ItemListaVODocument getRegional() {
			return this.regional;
		}

		public void setRegionalArray(br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] regionalArray) {
			this.regionalArray = regionalArray;
		}

		public br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] getRegionalArray() {
			return this.regionalArray;
		}

		public void setAcaoRetencion(ItemListaVODocument acaoRetencion) {
			this.acaoRetencion = acaoRetencion;
		}

		public ItemListaVODocument getAcaoRetencion() {
			return this.acaoRetencion;
		}

		public void setAcaoRetencionArray(br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] acaoRetencionArray) {
			this.acaoRetencionArray = acaoRetencionArray;
		}

		public br.com.vivo.fo.fidelizacao.vo.ItemListaVODocument.ItemListaVO[] getAcaoRetencionArray() {
			return this.acaoRetencionArray;
		}
	}
}
