package campanha.HistoricoCampanha;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionRedirect;

import br.com.vivo.fo.campanha.vo.ItemArvoreVODocument.ItemArvoreVO;
import br.com.vivo.fo.campanha.vo.ListaCampanhaHistoricoVODocument.ListaCampanhaHistoricoVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.log.Logger;

import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class HistoricoCampanhaController extends AbstractAction {

	private static final long serialVersionUID = 1204848529581814339L;
	
	private static Logger          log  = new Logger("campanha");

	@EJB
	private br.com.vivo.fo.ctrls.campanha.historico.HistoricoCampanhaFacade historicoFac;

	protected global.Global globalApp = new global.Global();

	private ListaHistCampanhaForm listaHistCampanhaForm = new ListaHistCampanhaForm();

	public ArrayList lHistorico = new ArrayList();

	public ArrayList getlHistorico() {
		if (this.lHistorico == null) {
			this.lHistorico = new ArrayList();
		}
		return this.lHistorico;
	}

	public String getUser(HttpServletRequest request) {
		return ConstantesCRM.getCurrentUser(request.getSession());
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("listaHistCampanha".equals(mapping.getParameter())) {
			return listaHistCampanha(mapping, form, request, response);
		} else if ("getArvoreHistorico".equals(mapping.getParameter())) {
			return getArvoreHistorico(mapping, form, request, response);
		} else if ("consultar".equals(mapping.getParameter())) {
			return consultar(mapping, form, request, response);
		} else if ("voltarArvore".equals(mapping.getParameter())) {
			return voltarArvore(mapping, form, request, response);
		}
		return begin(mapping, form, request, response);
	}

	/**
	 * This method represents the point of entry into the pageflow
	 * @jpf:action
	 * @jpf:forward name="success" path="listaHistCampanha.do"
	 */
	protected ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			/*
			 * PARAMENTROS PASSADOS PELA TELA INICIAL , O BLOCO A SEGUIR DEVERA SER DESCOMENTADADO QUANDO ESTES
			 * PARAMENTROS JA ESTIVEREM DISPONIVEIS.
			 */
			ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
			listaHistCampanhaForm.setIdPessoadePara(parametros.getIdClienteDePara());

		} catch (Exception e) {
			throw new Exception("Código cliente inválido!", e);
		}

		request.setAttribute("valorDiv", "none");
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		ActionRedirect action = new ActionRedirect(mapping.findForward(ConstantesCRM.SUCCESS));
		return action;
	}

	protected void carregaLista(String dtInicio, String dtFim, HttpServletRequest request) throws Exception {
		String user = ConstantesCRM.getCurrentUser(request.getSession());
		try {
			String[] dados = new String[3];
			dados[0] = listaHistCampanhaForm.getIdPessoadePara();
			dados[1] = dtInicio;
			dados[2] = dtFim;

			/*
			 * Tenho que zerar a lista antes de carregar, pois o atributo é declarado no pageFlow, portanto não é zerado
			 * após o 'reloads'.
			 */
			getlHistorico().clear();
			ListaCampanhaHistoricoVO lista = historicoFac.getListaCampanhaHistorico(user, dados);
			for (int i = 0; i < lista.getCampanhaHistoricoArray().length; i++) {
				getlHistorico().add(lista.getCampanhaHistoricoArray()[i]);
			}
		} catch (Exception e) {
			throw e;
		}
	}

	public ListaHistCampanhaForm getListaHistCampanhaForm() {
		return listaHistCampanhaForm;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="HistoricoCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward listaHistCampanha(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

		log.debug("HistoricoCampanhaController:: listaHistCampanha inicio");
		
		ListaHistCampanhaForm form = (ListaHistCampanhaForm) formParam;

		form.setIdPessoadePara(listaHistCampanhaForm.getIdPessoadePara());
		listaHistCampanhaForm = form;

		/*** LISTA NA ENTRADA ***/
		this.consultar(mapping, listaHistCampanhaForm, request, response);

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		// request.setAttribute("lHistorico", lHistorico);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="HistoricoCampanha.jsp"
	 * @jpf:forward name="error" path="/error.jsp"
	 */
	protected ActionForward consultar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		ListaHistCampanhaForm form = (ListaHistCampanhaForm) formParam;
        try {
            /*** SE FOR ENTRADA SE UTILIZA MÊS ANTERIOR ***/
            if( form.getDtFim() == null || form.getDtFim().equals(ConstantesCRM.SVAZIO) ){
                int dia, mes, ano;
                Calendar c = new GregorianCalendar();
                String sDia = ConstantesCRM.SZERO;
                String sMes = ConstantesCRM.SZERO;

                dia = mes = ano = 0;
                ano = c.get(Calendar.YEAR);
                mes = c.get(Calendar.MONTH) + 1;
                dia = c.get(Calendar.DATE);

                sDia = sDia + dia;
                sDia = sDia.substring( (sDia.length() -2), sDia.length());

                sMes = sMes + mes;
                sMes = sMes.substring( (sMes.length() -2), sMes.length());

                form.setDtFim( sDia + "/" + sMes + "/" + ano );
                form.setDtInicio( sDia + "/" + sMes + "/" + (ano-1) );
            }
            //carregaLista(form.getDtInicio(), form.getDtFim());

        } catch(Exception e) {
            FormError formError = globalApp.buildFormError(e, "/FrontOfficeWeb/campanha/HistoricoCampanha/begin.do");
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            return mapping.findForward(ConstantesCRM.SERROR);
        }

		form.setIdPessoadePara(listaHistCampanhaForm.getIdPessoadePara());
		listaHistCampanhaForm = form;

		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="viewScriptHistorico.jsp"
	 */
	protected ActionForward getArvoreHistorico(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try {
			// O StringBuffer DEVE ser inicializado com ""
			// para esta funcionalidade
			StringBuffer script = new StringBuffer(ConstantesCRM.SVAZIO);
			String user = ConstantesCRM.getCurrentUser(request.getSession());
			if ((request.getParameter("idAtendimento") != null) && (!request.getParameter("idAtendimento").equals(""))) {
				ItemArvoreVO itemArvore = historicoFac.getArvoreHistorico(user, request.getParameter("idAtendimento"));
				script.append("if (document.getElementById) {var tree = new WebFXTree('");
				script.append(StringEscapeUtils.escapeJavaScript("Questionário"));
				script.append("', '', '','/FrontOfficeWeb/resources/images/icon_subcamp_close.png', '/FrontOfficeWeb/resources/images/icon_subcamp_open.png');tree.setBehavior('classic');");
				String corpoArvore = VerificaItemMenu(itemArvore, "tree");
				script.append(corpoArvore + "document.write(tree);}");
				listaHistCampanhaForm.setScritpArvore(script.toString());
			}

			request.setAttribute(ConstantesCRM.CT_ARVORE_CAMPANHA, script.toString());

		} catch (Exception e) {
			throw e;
		}
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	private String VerificaItemMenu(ItemArvoreVO itens, String tree) {
		StringBuffer node = new StringBuffer();

		if (itens.getItemArvoreVOArray().length == 0) {
			return ConstantesCRM.SVAZIO;
		} else {
			for (int x = 0; x < itens.getItemArvoreVOArray().length; x++) {
				node.append("var ");
				node.append(tree);
				node.append(String.valueOf(x));
				node.append(" = new WebFXTreeItem('");
				node.append(StringEscapeUtils.escapeJavaScript(TrataDescricao(itens.getItemArvoreVOArray(x).getDescricao(), 50)));
				node.append("', '");
				node.append("'");
				node.append(", '','");
				node.append(getImgNodeClose(Integer.parseInt(itens.getItemArvoreVOArray(x).getTipo()), Integer.parseInt(itens.getItemArvoreVOArray(x).getInFinal()), Integer.parseInt(itens.getItemArvoreVOArray(x).getIdTipoApresentacao())));
				node.append("','");
				node.append(getImgNodeOpen(Integer.parseInt(itens.getItemArvoreVOArray(x).getTipo()), Integer.parseInt(itens.getItemArvoreVOArray(x).getInFinal()), Integer.parseInt(itens.getItemArvoreVOArray(x).getIdTipoApresentacao())));
				node.append("');");
				node.append(VerificaItemMenu(itens.getItemArvoreVOArray(x), tree + String.valueOf(x)));
				// node.append(String.valueOf(x));
				if (itens.getItemArvoreVOArray(x).getDescricao().length() > 80) {
					node.append(tree + String.valueOf(x) + ".setToolTip('" + StringEscapeUtils.escapeJavaScript(TrataDescricao(itens.getItemArvoreVOArray(x).getDescricao(), 50)) + "');\n");
				}
				node.append(tree);
				node.append(".add(");
				node.append(tree);
				node.append(String.valueOf(x));
				node.append(");\n");
			}
		}
		return node.toString();
	}

	private String TrataDescricao(String s, int iTam) {
		String sRetorno = s;

		// iTam eliminado da versão 1.32
		if (s != null) {
			sRetorno = sRetorno.replaceAll("\n", " ");
			sRetorno = sRetorno.replaceAll("'", "\"");
		}
		return sRetorno;
	}

	private String getImgNodeClose(int iTipo, int iFinal, int idApres) {
		String sImgClose = ConstantesCRM.SVAZIO;
		switch (iTipo) {
		/******************************************* PERGUNTA **************************************************************************************************************************************************/
		case 3:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_perg_indic.gif";
			if ((idApres == 5) || (idApres == 3)) {
				sImgClose = "/FrontOfficeWeb/resources/images/icon_perg_texto.gif";
			}
			break;

			/******************************************* RESPOSTA **************************************************************************************************************************************************/
		case 4:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_resp_nrml.gif";
			break;
		}

		return sImgClose;
	}

	private String getImgNodeOpen(int iTipo, int iFinal, int idApres) {
		/******************************************* RESPOSTAS COM DESVIO **************************************************************************************************************************************************/
		String sImgClose = ConstantesCRM.SVAZIO;
		switch (iTipo) {
		/******************************************* PERGUNTA **************************************************************************************************************************************************/
		case 3:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_perg_indic.gif";
			if ((idApres == 5) || (idApres == 3)) {
				sImgClose = "/FrontOfficeWeb/resources/images/icon_perg_texto.gif";
			}
			break;
			/******************************************* RESPOSTA **************************************************************************************************************************************************/
		case 4:
			sImgClose = "/FrontOfficeWeb/resources/images/icon_resp_nrml.gif";
			break;
		}
		return sImgClose;
	}

	/**
	 * @jpf:action
	 * @jpf:forward name="success" path="HistoricoCampanha.jsp"
	 */
	// @jpf:forward name="success" path="listaHistCampanha.do"
	protected ActionForward voltarArvore(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
		// request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		// return mapping.findForward(ConstantesCRM.SUCCESS, listaHistCampanhaForm);
		request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
		return mapping.findForward(ConstantesCRM.SUCCESS);
	}

	public static class ListaHistCampanhaForm extends ActionForm {

		private static final long serialVersionUID = -4300299892583573728L;

		private String historicoId;
		private String dtInicio;
		private String dtFim;
		private String idPessoadePara;
		private String scriptArvore = ConstantesCRM.SVAZIO;
		private ArrayList lHistorico = new ArrayList();

		public String getScriptArvore() {
			return scriptArvore;
		}

		public void setScritpArvore(String scriptArvore) {
			this.scriptArvore = scriptArvore;
		}

		public String getIdPessoadePara() {
			return idPessoadePara;
		}

		public void setIdPessoadePara(String idPessoadePara) {
			this.idPessoadePara = idPessoadePara;
		}

		public void setDtFim(String dtFim) {
			this.dtFim = dtFim;
		}

		public String getDtFim() {
			return this.dtFim;
		}

		public void setDtInicio(String dtInicio) {
			this.dtInicio = dtInicio;
		}

		public String getDtInicio() {
			return this.dtInicio;
		}

		public void setHistoricoId(String historicoId) {
			this.historicoId = historicoId;
		}

		public String getHistoricoId() {
			return this.historicoId;
		}

		public void setlHistorico(ArrayList lHistorico) {
			this.lHistorico = lHistorico;
		}

		public ArrayList getlHistorico() {
			return lHistorico;
		}
	}
}
