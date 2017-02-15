package VOLTAV.manterTermoAceite;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import weblogic.logging.NonCatalogLogger;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.VOLTAV.manterTermoAceite.ManterTermoAceite;
import br.com.vivo.fo.ctrls.VOLTAV.manterTermoAceite.db.ManterTermoAceiteDB.ItemMenuTermoAceite;
import com.indracompany.actions.AbstractAction;
import commons.errors.FormError;

public class ManterTermoAceiteController extends AbstractAction {

    private static final long serialVersionUID = -1679868452429047723L;

    protected global.Global                   globalApp       = new global.Global();

    private static transient NonCatalogLogger logger          = new NonCatalogLogger(ManterTermoAceiteController.class.getName());

    FormTermoAceite                           formTermoAceite = null;

    @EJB
    private ManterTermoAceite                 manterTermoAceite;

    public void setFormTermoAceite(FormTermoAceite form) {
        this.formTermoAceite = form;
    }

    public FormTermoAceite getFormTermoAceite() {
        if (this.formTermoAceite == null) {
            this.formTermoAceite = new FormTermoAceite();
        }

        return this.formTermoAceite;
    }

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        } else if ("manterTermoAceite".equals(mapping.getParameter())) {
            return manterTermoAceite(mapping, form, request, response);
        } else if ("buscarTermoPorServico".equals(mapping.getParameter())) {
            return buscarTermoPorServico(mapping, form, request, response);
        } else if ("salvarTermoPorServico".equals(mapping.getParameter())) {
            return salvarTermoPorServico(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    /**
     * This method represents the point of entry into the pageflow
     * @jpf:action
     * @jpf:forward name="success" path="manterTermoAceite.do"
     */
    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action form="formTermoAceite"
     * @jpf:forward name="sucess" path="manterTermoAceite.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward manterTermoAceite(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        FormTermoAceite form = (FormTermoAceite) formParam;
        logger.info("ManterTermoAceiteController:manterTermoAceite:Pesquisar Serviços ");
        try {
            ItemMenuTermoAceite[] listaItemMenu = manterTermoAceite.consultarListaServicos();
            logger.info("ManterTermoAceiteController:manterTermoAceite: Servicos " + listaItemMenu);
            if (listaItemMenu != null && listaItemMenu.length > 0) {
                logger.info("ManterTermoAceiteController:manterTermoAceite: Total Servicos " + listaItemMenu.length);
                getFormTermoAceite().setListaItemMenuTermo(listaItemMenu);
                getFormTermoAceite().setStatusTermo(ConstantesCRM.SVAZIO);
                getFormTermoAceite().setTextoTermoAceite(ConstantesCRM.SVAZIO);
                getFormTermoAceite().setIdServicoSelecionado(ConstantesCRM.SVAZIO);
            }

        } catch (Exception e) {
            logger.error("ManterTermoAceiteController:manterTermoAceite(" + this.getUser(request) + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, request);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action form="formTermoAceite"
     * @jpf:forward name="sucess" path="manterTermoAceite.jsp"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward buscarTermoPorServico(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        FormTermoAceite form = (FormTermoAceite) formParam;
        logger.info("ManterTermoAceiteController:buscarTermoPorServico:Pesquisar Termo ");
        try {
            Short servicoSelecionado = null;
            if (form.getIdServicoSelecionado() != null && form.getIdServicoSelecionado().length() > 0) {
                servicoSelecionado = new Short(form.getIdServicoSelecionado());
            }
            logger.info("Serviço Selecionado " + servicoSelecionado);

            ItemMenuTermoAceite termoAceiteServico = manterTermoAceite.buscarTermoServico(servicoSelecionado);
            if (termoAceiteServico != null) {
                getFormTermoAceite().setStatusTermo(termoAceiteServico.getStatusTermo());
                getFormTermoAceite().setTextoTermoAceite(termoAceiteServico.getTxtTermo());
                getFormTermoAceite().setIdServicoSelecionado(termoAceiteServico.getIdItemMenu());
            }

        } catch (Exception e) {
            logger.error("ManterTermoAceiteController:buscarTermoPorServico(" + this.getUser(request) + ") - [" + e.getMessage() + "]", e);
            FormError formError = globalApp.buildFormError(e, request);
            request.setAttribute(ConstantesCRM.SFORMERROR, formError);
            request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
            return mapping.findForward(ConstantesCRM.SERROR);
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action form="formTermoAceite"
     * @jpf:forward name="sucess" path="buscarTermoPorServico.do"
     * @jpf:forward name="error" path="/error.jsp"
     */
    protected ActionForward salvarTermoPorServico(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        FormTermoAceite form = (FormTermoAceite) formParam;
        logger.info("ManterTermoAceiteController:salvarTermoPorServico:Salvar Termo ");
        try {
            String user = ConstantesCRM.getCurrentUser(request.getSession());
            logger.debug("ManterTermoAceiteController:salvarTermoPorServico[user = " + this.getUser(request) + "]");

            String idItemMenu = form.getIdServicoSelecionado();
            String statusTermo = form.getStatusTermo();
            String textoTermo = form.getTextoTermoAceite();

            if (textoTermo.indexOf("'") > 0) {
                textoTermo = textoTermo.replaceAll("'", "''");
            }

            if (statusTermo.equals(ConstantesCRM.SZERO) && (textoTermo == null || ConstantesCRM.SVAZIO.equals(textoTermo))) {
                textoTermo = " ";
            }

            if (idItemMenu != null && statusTermo != null && textoTermo != null) {
                Short servicoSelecionado = new Short(idItemMenu);
                manterTermoAceite.atualizarTermoServico(servicoSelecionado, statusTermo, textoTermo, this.getUser(request));
                getFormTermoAceite().setStatusTermo(statusTermo);
                getFormTermoAceite().setTextoTermoAceite(textoTermo);
                getFormTermoAceite().setIdServicoSelecionado(idItemMenu);
                request.setAttribute("msgStatus", "Alteração do Termo de Aceite realizada com sucesso.");
            }

        } catch (Exception e) {
            logger.error("ManterTermoAceiteController:salvarTermoPorServico(" + this.getUser(request) + ") - [" + e.getMessage() + "]", e);
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

    public static class FormTermoAceite extends ActionForm {

        private String                textoTermoAceite;
        private String                statusTermo;
        private ItemMenuTermoAceite[] listaItemMenuTermo;
        private String                idServicoSelecionado;

        public String getTextoTermoAceite() {
            return this.textoTermoAceite;
        }

        public void setTextoTermoAceite(String string) {
            this.textoTermoAceite = string;
        }

        public String getStatusTermo() {
            return this.statusTermo;
        }

        public void setStatusTermo(String string) {
            this.statusTermo = string;
        }

        public ItemMenuTermoAceite[] getListaItemMenuTermo() {
            return this.listaItemMenuTermo;
        }

        public void setListaItemMenuTermo(ItemMenuTermoAceite[] lista) {
            this.listaItemMenuTermo = lista;
        }

        public String getIdServicoSelecionado() {
            return this.idServicoSelecionado;
        }

        public void setIdServicoSelecionado(String string) {
            this.idServicoSelecionado = string;
        }

    }

}
