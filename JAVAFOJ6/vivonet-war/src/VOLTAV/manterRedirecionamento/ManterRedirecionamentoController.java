package VOLTAV.manterRedirecionamento;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import weblogic.logging.NonCatalogLogger;
import br.com.vivo.fo.commons.utils.properties.LoadProperties;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.manterRedirecionamento.ManterRedirecionamento;
import br.com.vivo.fo.ctrls.VOLTAV.manterRedirecionamento.db.ManterRedirecionamentoDB.ItemRedirecionamento;
import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterRedirecionamentoController extends AbstractAction {

    private static final long                 serialVersionUID           = 3473798394588794079L;

    protected global.Global                   globalApp                  = new global.Global();

    private static transient NonCatalogLogger logger                     = new NonCatalogLogger(ManterRedirecionamentoController.class.getName());

    FormManterRedirecionamento                formManterRedirecionamento = null;

    @EJB
    private ManterRedirecionamento            manterRedirecionamento;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("consultarRedirecionamento".equals(mapping.getParameter())) {
            return consultarRedirecionamento(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    /**
     * This method represents the point of entry into the pageflow
     * @jpf:action
     * @jpf:forward name="success" path="consultarRedirecionamento.do"
     */
    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    public void setFormManterRedirecionamento(FormManterRedirecionamento form) {
        this.formManterRedirecionamento = form;
    }

    public FormManterRedirecionamento getFormManterRedirecionamento() {
        if (this.formManterRedirecionamento == null) {
            this.formManterRedirecionamento = new FormManterRedirecionamento();
        }

        return this.formManterRedirecionamento;
    }

    /**
     * @jpf:action form="formManterRedirecionamento"
     * @jpf:forward name="sucess" path="manterListaRedirecionamento.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward consultarRedirecionamento(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        FormManterRedirecionamento form = (FormManterRedirecionamento) formParam;
        logger.info("ManterRedirecionamentoController:consultarRedirecionamento");
        try {
            Short canalEscolhido = null;
            if (form.getIdSistema() == null || form.getIdSistema().length() <= 0) {
                getFormManterRedirecionamento().setIdSistema(ConstantesCRM.SNINE);
            }else {
                getFormManterRedirecionamento().setIdSistema(form.getIdSistema());
            }
            canalEscolhido = new Short(getFormManterRedirecionamento().getIdSistema());
            logger.info("ManterRedirecionamentoController:consultarRedirecionamento:Canal Escolhido " + canalEscolhido);

            getFormManterRedirecionamento().setUrlExemplo(getUrlExemplo(getFormManterRedirecionamento().getIdSistema(), request));

            ItemRedirecionamento[] listaItem = manterRedirecionamento.consultarRedirecionamento(canalEscolhido);
            if (listaItem == null || listaItem.length <= 0) {
                getFormManterRedirecionamento().setListaSemDados("true");
                getFormManterRedirecionamento().setListaItemRedirecionamento(listaItem);
            } else {
                logger.info("ManterRedirecionamentoController:consultarRedirecionamento:Total Linhas " + listaItem.length);
                getFormManterRedirecionamento().setListaItemRedirecionamento(listaItem);
            }

        } catch (Exception e) {
            logger.error("ManterRedirecionamentoController:consultarRedirecionamento(" + this.getUser(request) + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, request);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    public String getUser(HttpServletRequest request) {
        String sUser = ConstantesCRM.getCurrentUser(request.getSession());
        NumberFormat nf = new DecimalFormat(ConstantesCRM.SZERO);
        nf.setMinimumIntegerDigits(15);
        long l = Long.valueOf(sUser).longValue();
        sUser = nf.format(l);
        return sUser;
    }

    public String getUrlExemplo(String idSistemaOrigem, HttpServletRequest request) {
        String urlExemplo = ConstantesCRM.SVAZIO;
        try {
            LoadProperties properties = new LoadProperties(request);
            urlExemplo = properties.get("URL_DIREC_SISTEMA_" + idSistemaOrigem);

        } catch (Exception e) {
            logger.error("ManterRedirecionamentoController:getUrlExemplo(" + idSistemaOrigem + ") - [" + e.getMessage() + "]", e);
        }

        return urlExemplo;
    }

    public static class FormManterRedirecionamento extends ActionForm {

        private static final long serialVersionUID = 3384218597785277212L;

        private String                 idSistema;
        private ItemRedirecionamento[] listaItemRedirecionamento;
        private String                 listaSemDados = "false";
        private String                 urlExemplo;

        public String getIdSistema() {
            return this.idSistema;
        }

        public void setIdSistema(String string) {
            this.idSistema = string;
        }

        public ItemRedirecionamento[] getListaItemRedirecionamento() {
            return this.listaItemRedirecionamento;
        }

        public void setListaItemRedirecionamento(ItemRedirecionamento[] lista) {
            this.listaItemRedirecionamento = lista;
        }

        public String getListaSemDados() {
            return this.listaSemDados;
        }

        public void setListaSemDados(String string) {
            this.listaSemDados = string;
        }

        public String getUrlExemplo() {
            return this.urlExemplo;
        }

        public void setUrlExemplo(String string) {
            this.urlExemplo = string;
        }
    }
}
