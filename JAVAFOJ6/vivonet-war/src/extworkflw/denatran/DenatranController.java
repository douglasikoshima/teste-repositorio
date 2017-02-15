package extworkflw.denatran;

import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import weblogic.logging.NonCatalogLogger;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO;
import br.com.vivo.fo.constantes.ConstantesCRM;
import br.com.vivo.fo.ctrls.cliente.telaInicial.TelaInicialFacade;
import cliente.TelaInicial.services.IntegradorDenatran;
import com.bea.wlw.netui.pageflow.FormData;
import com.bea.wlw.netui.pageflow.PageFlowController;

public class DenatranController extends PageFlowController {

    private static final long serialVersionUID = -1578889598167704391L;

    protected global.Global                   globalApp;
    private FormDenatran                      formDenatran = null;
    private String                            user         = null;
    private static transient NonCatalogLogger log          = new NonCatalogLogger(DenatranController.class.getName());

    @EJB
    private TelaInicialFacade                 telaInicialFacadeControl;

    
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		if ("begin".equals(mapping.getParameter())) {
			return begin(mapping, form, request, response);
		} else if ("buscar".equals(mapping.getParameter())) {
			return buscar(mapping, form, request, response);
		} 
		return begin(mapping, form, request, response);
	}    
    
    /**
     * This method represents the point of entry into the pageflow
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     * @jpf:forward name="pf" path="pf.jsp"
     */
    protected ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
        if ("PF".equals(parametros.getInTipoPessoa())) {
            return mapping.findForward("pf");
        }
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="error" path="begin.do"
     * @jpf:forward name="success" path="begin.do"
     */
    protected ActionForward buscar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {
        try {
            user = ConstantesCRM.getCurrentUser(request.getSession());

            ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
            String nrLinhaAtual = parametros.getNrLinha();

            String parametro_url_destino = "DENATRAN_URL_DESTINO";
            String parametro_autenticador = "DENATRAN_AUTENTICADOR_HOST";
            String parametro_api_login = "DENATRAN_AUTENTICADOR_USUARIO";
            String parametro_api_senha = "DENATRAN_AUTENTICADOR_SENHA";

            ParametroVO parametroVO = telaInicialFacadeControl.getParametro(user, parametro_autenticador);
            String urlAutenticador = parametroVO.getDsValorParametro();

            parametroVO = telaInicialFacadeControl.getParametro(user, parametro_url_destino);
            String urlDestino = parametroVO.getDsValorParametro();

            parametroVO = telaInicialFacadeControl.getParametro(user, parametro_api_login);
            String apiLogin = parametroVO.getDsValorParametro();

            parametroVO = telaInicialFacadeControl.getParametro(user, parametro_api_senha);
            String apiSenha = parametroVO.getDsValorParametro();

            IntegradorDenatran integrador = new IntegradorDenatran();
            integrador.setApiLogin(apiLogin);
            integrador.setApiSenha(apiSenha);
            integrador.setPlataform(ConstantesCRM.SONE);
            integrador.setSubscriber(nrLinhaAtual);
            integrador.setURL(urlAutenticador);
            integrador.setLoginUsuario("adminfo");
            integrador.setCanalAtendimento("10000");
            integrador.setEnderecoIP("127.0.0.1");
            integrador.setCodigoSessao(ConstantesCRM.SONE);

            String token = integrador.getToken();
            urlDestino += token;

            request.setAttribute("exibir", urlDestino);

            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            log.error("DenatranController.buscar", e);
            this.getRequest().setAttribute("msgStatus", "Erro não foi possivel gerar o token.");
            return mapping.findForward(ConstantesCRM.SERROR);
        }

    }

    public static class FormDenatran extends FormData {

        private static final long serialVersionUID = 4578384022945875476L;
    }

    public void setFormDenatran(FormDenatran f) {
        this.formDenatran = f;
    }

    public FormDenatran getFormDenatran() {
        if (this.formDenatran == null) {
            this.formDenatran = new FormDenatran();
        }
        return this.formDenatran;
    }

}
