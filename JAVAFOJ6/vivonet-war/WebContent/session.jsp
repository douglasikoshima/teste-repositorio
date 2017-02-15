<%@ page language="java" contentType="text/html;charset=UTF-8" session="false"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.log.Logger"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<script type="text/javascript" language="javascript">
if (top.location.href.indexOf("session.jsp") == -1) {
    top.location.href = "<%=request.getContextPath()%>/session.jsp";
}
</script>
<%!
private transient Logger log = new Logger("start");
%>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="" name="modulo"></netui-template:setAttribute>
    <netui-template:section name="headerSection">
        <script type="text/javascript" language="javascript">
            function closeWindow(op) {
                if (op == '1') {
                    alert('SESSÃO EXPIRADA!\nEsta página não pode ser atualizada, seu navegador será fechado.');
                }
                top.location.href = "begin.do";
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <table width="790" height="470" border="0">
            <tr>
                <td align="center" valign="middle" ><img id="imgAutenticacao" src="<%=request.getContextPath()%>/resources/images/bk_autenticacao.jpg"></td>
            </tr>
            <tr>
                <td align="center" style="font-size:16px;">
                    <b>"Tempo de inatividade expirado. &Eacute; necess&aacute;ria a realiza&ccedil;&atilde;o de um novo login."</b>
                </td>
            </tr>
            <tr>
                <td height="20"></td>
            </tr>
            <tr>
                <td valign="middle" align="center">
                    &Eacute; necess&aacute;rio realizar o <img hspace="10" src="<%=request.getContextPath()%>/resources/images/bt_login_nrml.gif" style="border:1px;" onclick="closeWindow('0')"/> para retornar à aplicação.
                </td>
            </tr>
            <tr>
                <td height="30"></td>
            </tr>
        </table>
        <%
        try{
            log.debug("Session.jsp:Session IdValid -> "+request.isRequestedSessionIdValid());
            log.debug("Session.jsp:Session Id      -> "+request.getSession().getId());
            log.debug("Session.jsp:IP Address      -> "+request.getRemoteAddr());
            log.debug("Session.jsp:IP X-Forward-For-> "+request.getHeader("X-Forwarded-For"));
            log.debug("Session.jsp:USUARIO_LOGIN   -> "+request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN));
            request.getSession().invalidate();
        }catch(Exception ex){}
        %>
    </netui-template:section>
</netui-template:template>