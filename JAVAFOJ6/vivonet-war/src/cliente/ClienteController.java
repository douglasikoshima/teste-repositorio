package cliente;

import java.io.BufferedOutputStream;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.xmlbeans.XmlException;
import br.com.vivo.fo.atmi.TuxedoServiceCall;
import br.com.vivo.fo.atmi.TuxedoServiceCallerException;
import br.com.vivo.fo.cliente.Pesquisar;
import br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset;
import br.com.vivo.fo.commons.utils.geral.TuxedoServiceBridge;
import br.com.vivo.fo.constantes.ConstantesCRM;
import com.bea.document.DocumentException;
import com.bea.document.DocumentFactory;
import com.bea.document.DocumentParseException;
import com.bea.document.IDocument;
import com.indracompany.actions.AbstractAction;

public class ClienteController extends AbstractAction {

    private static final long serialVersionUID = 1363010986593976416L;

    @EJB
    private TuxedoServiceCall tuxedo;

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if ("begin".equals(mapping.getParameter())) {
            return begin(mapping, form, request, response);
        
        } else if ("AssociarGestorDone".equals(mapping.getParameter())) {
            return AssociarGestorDone(mapping, form, request, response);
        
        } else if ("CDevolvidaDone".equals(mapping.getParameter())) {
            return CDevolvidaDone(mapping, form, request, response);
        
        } else if ("ConsultaHexaDone".equals(mapping.getParameter())) {
            return ConsultaHexaDone(mapping, form, request, response);
        
        } else if ("ProspectsDone".equals(mapping.getParameter())) {
            return ProspectsDone(mapping, form, request, response);
        
        } else if ("pesquisar".equals(mapping.getParameter())) {
            return pesquisar(mapping, form, request, response);
        
        } else if ("pesquisarTuxedo".equals(mapping.getParameter())) {
            return pesquisarTuxedo(mapping, form, request, response);
        
        } else if ("valorSenha".equals(mapping.getParameter())) {
            return valorSenha(mapping, form, request, response);
        }
        return begin(mapping, form, request, response);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="begin.do"
     */
    public ActionForward valorSenha(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String ddd = request.getParameter("ddd");
        String telefone = request.getParameter("fone");
        String idTipoRelacionamento = request.getParameter("tipoRel");
        String idCanal = ConstantesCRM.SVAZIO;
        String novaSenha = ConstantesCRM.SVAZIO;

        StringBuffer sb = new StringBuffer();

        sb.append("<cdAreaRegistro>").append(ddd).append("</cdAreaRegistro>");
        sb.append("<nrLinha>").append(telefone).append("</nrLinha>");
        sb.append("<idCanal>").append(idCanal).append("</idCanal>");
        sb.append("<idTipoRelacionamento>").append(idTipoRelacionamento).append("</idTipoRelacionamento>");
        sb.append("<operacao>obterSenha</operacao>");

        String xmlOUT = "";

        try {
            xmlOUT = tuxedo.callService("TuxConnector", TuxedoServiceBridge.getXMLRequest(ConstantesCRM.SONE, "SOLICITARSENHA", sb.toString()));
            IDocument doc = DocumentFactory.createDocument(xmlOUT);
            novaSenha = doc.getStringFrom("/msg/msgBody");

        } catch (TuxedoServiceCallerException e) {
            e.printStackTrace();
        
        } catch (XmlException e) {
            e.printStackTrace();
        
        } catch (DocumentParseException e) {
            e.printStackTrace();
        
        } catch (DocumentException e) {
            e.printStackTrace();
        }

        request.setAttribute("valorSenha", novaSenha);

        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    public ActionForward begin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String retorno = ConstantesCRM.SERROR;
        String user = ConstantesCRM.getCurrentUser(request.getSession());
        if(ConstantesCRM.SONE.equals(user)){
            if (request.getParameter("SQL") != null) {
                request.getSession().setAttribute("SQL", ConstantesCRM.SONE);
                request.setAttribute("SQL", ConstantesCRM.SONE);
                retorno = ConstantesCRM.SUCCESS;
            }

            if (request.getParameter("TUX") != null) {
                request.getSession().setAttribute("TUX", ConstantesCRM.SONE);
                request.setAttribute("TUX", ConstantesCRM.SONE);
                retorno = "tuxedo";
            }
        }
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(retorno);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="begin.do"
     */
    public ActionForward AssociarGestorDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="begin.do"
     */
    public ActionForward CDevolvidaDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="begin.do"
     */
    public ActionForward ConsultaHexaDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="begin.do"
     */
    public ActionForward ProspectsDone(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute(ConstantesCRM.SKEY_REQUEST_PAGEFLOW, this);
        return mapping.findForward(ConstantesCRM.SUCCESS);
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    public ActionForward pesquisarTuxedo(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        PesquisarForm form = (PesquisarForm) formParam;
        StringBuffer saida = new StringBuffer(ConstantesCRM.SVAZIO);
        try {
            String user = ConstantesCRM.getCurrentUser(request.getSession());
            if(ConstantesCRM.SONE.equals(user)){
                if (ConstantesCRM.SONE.equals(request.getSession().getAttribute("TUX"))) {
                    String xmlIn = form.getSqlCommand().trim();

                    if (xmlIn.indexOf("<service>") == -1 || xmlIn.indexOf("</service>") == -1) {
                        saida.append("<table id='tabResult' border='1'><tr>");
                        saida.append("<td width='120'> Não foi encontrada as tags que identificam o Serviço Tuxedo, tente novamente.</td>");
                        saida.append("</tr>");
                        saida.append("</table>");
                    
                    } else {
                        String xmlOUT = tuxedo.callService("TuxConnector", xmlIn.replaceAll("vo:",""));                   

                        saida.append("<table id='tabResult' border='1'><tr>");
	                    saida.append("<td width='120'>").append(xmlOUT.replaceAll("<", "&lt;").replaceAll(">", "&gt;")).append("</td>");
                        saida.append("</tr>");
                        saida.append("</table>");
                    }
                }
            }
        } catch (Exception e) {
            saida.append("<table id='tabResult' border='1'><tr>");
            saida.append("<td width='120'>").append(e.getMessage()).append("</td>");
            saida.append("</tr>");
            saida.append("</table>");
        } finally {
            try {
                response.setContentType(ConstantesCRM.SContentType);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                bufferedOutputStream.write(saida.toString().getBytes(ConstantesCRM.SISO));
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    /**
     * @jpf:action
     * @jpf:forward name="success" path="index.jsp"
     */
    public ActionForward pesquisar(ActionMapping mapping, ActionForm formParam, HttpServletRequest request, HttpServletResponse response) {
        PesquisarForm form = (PesquisarForm) formParam;
        StringBuffer saida = new StringBuffer(ConstantesCRM.SVAZIO);
        try {
            String user = ConstantesCRM.getCurrentUser(request.getSession());
            if(ConstantesCRM.SONE.equals(user)){
                if (ConstantesCRM.SONE.equals(request.getSession().getAttribute("SQL"))) {
                    Resultset resultset = (new Pesquisar()).executar(analisaQuery(form.getSqlCommand()));

                    if (resultset.getColunas() != null) {
                        saida.append("<table id='tabResult' border='1'><tr>");
                        for (int i = 0; i < resultset.getColunas().getColunaArray().length; i++) {
                            saida.append("<td width='120'>").append(resultset.getColunas().getColunaArray(i)).append("</td>");
                        }
                        saida.append("</tr>");
                        for (int x = 0; x < resultset.getLinhas().getLinhaArray().length; x++) {
                            saida.append("<tr>");
                            for (int y = 0; y < resultset.getLinhas().getLinhaArray(x).getValorArray().length; y++) {
                                saida.append("<td>").append(resultset.getLinhas().getLinhaArray(x).getValorArray(y)).append("</td>");
                            }
                            saida.append("</tr>");
                        }
                        saida.append("</table>");
                    }
                    if (resultset.getMsg() != null) {
                        saida.append("<table id='tabResult' border='1'><tr><td>").append(resultset.getMsg()).append("</td></tr></table>");
                    }
                }
            }
        } catch (Exception e) {
        } finally {
            try {
                response.setContentType(ConstantesCRM.SContentType);
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(response.getOutputStream());
                bufferedOutputStream.write(saida.toString().getBytes(ConstantesCRM.SISO));
                bufferedOutputStream.flush();
                bufferedOutputStream.close();
            } catch (Exception e) {
            }
        }
        return null;
    }

    private String analisaQuery(String query) {
        String analisada = query.trim().toLowerCase();
        if ("select".equals(analisada.trim().substring(0, 6))) {
            if (analisada.indexOf("where") == -1) {
                query += " where rownum < 100 ";
            } else {
                if (analisada.indexOf("where") > -1) {
                    if (analisada.indexOf("rownum") == -1) {
                        query += " and rownum < 100 ";
                    }
                }
            }
        }
        return query;
    }

    public static class PesquisarForm extends ActionForm {

        private static final long serialVersionUID = 900682292168903491L;
        private String            sqlCommand       = ConstantesCRM.SVAZIO;

        public void setSqlCommand(String sqlCommand) {
            this.sqlCommand = sqlCommand;
        }

        public String getSqlCommand() {
            return this.sqlCommand;
        }
    }
}
