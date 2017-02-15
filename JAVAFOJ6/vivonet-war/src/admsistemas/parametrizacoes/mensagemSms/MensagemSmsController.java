package admsistemas.parametrizacoes.mensagemSms;

import java.io.BufferedOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ParametroVODocument.ParametroVO;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.commons.utils.GetParametro;
import br.com.vivo.fo.constantes.ConstantesCRM;
import com.indracompany.actions.AbstractAction;
import commons.errors.AjaxError;

public class MensagemSmsController extends AbstractAction {

    private static final long serialVersionUID = 1727205988734176008L;

    private String            isPre            = "false";
    private String            witchPos         = ConstantesCRM.SVAZIO;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);

        } else if ("selecionado".equals(mapping.getParameter())) {
            return selecionado(mapping, form, request, response);

        } else if ("atualizarMensagemSms".equals(mapping.getParameter())) {
            return atualizarMensagemSms(mapping, form, request, response);
        }

        return begin(mapping, form, request, response);
    }

    public ActionForward begin(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.setAttribute("error", ConstantesCRM.SVAZIO);
        request.setAttribute("isPre", ConstantesCRM.SVAZIO);

        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * Busca mensagem atual no Banco e o escreve na TextArea da página index.
     */
    protected ActionForward selecionado(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("error", ConstantesCRM.SVAZIO);
        request.setAttribute("isPre", ConstantesCRM.SVAZIO);

        MensagemSmsForm form = (MensagemSmsForm) formParam;
        String selecione = form.getSelecionado().toUpperCase();

        String selecionado = "VINCULO_SMS_";

        if ("PRECONTROLE".equals(selecione)) {
            this.isPre = "true";
            request.setAttribute("titulo", "Mensagem SMS para o Pré-pago Controle");
            selecionado = selecionado.concat("CONF_PRECONTROLE");

        } else {
            this.isPre = "false";
            this.witchPos = selecione;

            request.setAttribute("titulo", "Mensagem SMS para o Pós-pago");

            if ("POS".equals(this.witchPos)) {
                selecionado = selecionado.concat("CONF_POS");

            } else if ("POSPENDENTE".equals(this.witchPos)) {
                selecionado = selecionado.concat("PENDENTE_POS");

            } else {
                selecionado = selecionado.concat("CONF24H_POS");
            }
        }

        request.setAttribute("isPre", this.isPre);

        try {
            String idUsuario = ConstantesCRM.getCurrentUser(request.getSession());

            GetParametro gp = GetParametro.getInstace();
            ParametroVO pvo = gp.getParametroVO(idUsuario, selecionado);
            form.setTextArea(pvo.getDsValorParametro());

            request.setAttribute("error", ConstantesCRM.SVAZIO);

            return mapping.findForward(ConstantesCRM.SUCCESS);

        } catch (Exception e) {
            request.setAttribute("error", "Erro: Mensagem não pode ser requisitada, Erro no Banco!");
            return mapping.findForward(ConstantesCRM.SUCCESS);
        }
    }

    /**
     * Atualiza mensagem escrita pelo usuário no Banco VINCULO_SMS_
     * 
     * <PRE ou POS>
     */
    @SuppressWarnings("deprecation")
    protected ActionForward atualizarMensagemSms(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("error", ConstantesCRM.SVAZIO);
        String texto = request.getParameter("texto");
        String selecionado = "VINCULO_SMS_";

        if ("true".equals(isPre)) {
            selecionado = selecionado.concat("CONF_PRECONTROLE");

        } else if ("POS".equals(this.witchPos)) {
            selecionado = selecionado.concat("CONF_POS");

        } else if ("POSPENDENTE".equals(this.witchPos)) {
            selecionado = selecionado.concat("PENDENTE_POS");

        } else {
            selecionado = selecionado.concat("CONF24H_POS");
        }

        try {
            MensagemSmsForm form = (MensagemSmsForm) formParam;
            Pesquisar pesquisar = new Pesquisar();

            StringBuffer query = new StringBuffer();
            query.append("UPDATE APOIO.PARAMETRO ");
            query.append("   SET DTULTIMAALTERACAO = SYSDATE, ");
            query.append("       DSVALORPARAMETRO  = '").append(texto).append("' ");
            query.append(" WHERE CDPARAMETRO = '").append(selecionado).append("' ");

            Resultset rs = pesquisar.executar(query.toString());

            response.setContentType(ConstantesCRM.SContentType);
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());

            if (rs.getMsg() == null) {
                bufferedOutputStream.write("Mensagem atualizada com Sucesso!".getBytes(ConstantesCRM.SISO));
                form.setTextArea(texto);

            } else {
                String erro = "Erro: " + rs.getMsg();
                bufferedOutputStream.write(erro.getBytes(ConstantesCRM.SISO));
            }

            bufferedOutputStream.flush();
            bufferedOutputStream.close();
            return null;

        } catch (Exception e) {
            response.setStatus(AjaxError.STATUS_CODE_ERROR, AjaxError.MSG_ERRO);
            response.setHeader("stackTrace", AjaxError.getStackTrace(e));
            return null;
        }
    }

    public static class MensagemSmsForm extends ActionForm {

        private static final long serialVersionUID = -4778887832212940398L;

        private String            textArea         = ConstantesCRM.SVAZIO;
        private String            selecionado      = ConstantesCRM.SVAZIO;

        public void setTextArea(String textArea) {
            this.textArea = textArea;
        }

        public String getTextArea() {
            return this.textArea;
        }

        public void setSelecionado(String selecionado) {
            this.selecionado = selecionado;
        }

        public String getSelecionado() {
            return this.selecionado;
        }
    }
}
