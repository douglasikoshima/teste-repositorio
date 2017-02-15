<%@ page language="java" contentType="text/html;charset=UTF-8" session="false"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page import="br.com.vivo.fo.log.Logger"%>
<%@ page import="org.apache.commons.lang.StringEscapeUtils"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%!
private transient Logger log = new Logger("start");
%>
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute value="" name="modulo"></netui-template:setAttribute>
    <netui-template:section name="headerSection">
        <script for=window event=onload>
            if(parent.document.getElementById("idAnime_PERFIL") != null ){
                parent.document.getElementById("idAnime_PERFIL").style.display="none";
            }
            if(window.top.location.href.indexOf("acessoNegado.jsp") == -1){
            <%try{
                if(request.getAttribute("statusText")!=null){%>
                    window.top.location.href = "<%=request.getContextPath()%>/acessoNegado.jsp?statusText=" + "<%=(String)request.getAttribute("statusText")%>";
                <%}else{%>
                    window.top.location.href = "<%=request.getContextPath()%>/acessoNegado.jsp?statusText=" + "<%=request.getParameter("statusText")%>";
                <%}%>
            <%}catch(Exception e){}%>
            }
        </script>
        <script language="javascript">
            function closeWindow() {
                window.top.location.href="begin.do";
            }
        </script>
    </netui-template:section>
    <netui-template:section name="bodySection">
    </head>
        <table width="790" height="470" border="0">
            <tr>
                <td align="center" valign="middle"><img src="<%=request.getContextPath()%>/resources/images/bk_autenticacao.jpg"></td>
            </tr>
            <tr>
                <td align="center" style="font-size: 16px;"><b>
                    <%if(request.getAttribute("statusText")!=null){%>
                        <%=(String)request.getAttribute("statusText")%>
                    <%}else if(request.getSession().getAttribute("statusText")!=null){%>
                        <%=(String)request.getSession().getAttribute("statusText")%>
                    <%}else{%>
                        <%=request.getParameter("statusText")%>
                    <%}%>
                </b></td>
            </tr>
            <tr>
                <td height="20"></td>
            </tr>
            <tr>
                <td valign="middle" align="center">
                	&Eacute; necess&aacute;rio realizar o 
                	<img id="btnFechar" hspace="10" src="<%=request.getContextPath()%>/resources/images/bt_login_nrml.gif" onmouseout="this.src='<%=request.getContextPath()%>/resources/images/bt_login_nrml.gif'" onmouseover="this.src='<%=request.getContextPath()%>/resources/images/bt_login_over.gif'" style="border:1px;" onclick="closeWindow();"/> para retornar à aplicação.
                </td>
            </tr>
            <tr>
                <td height="30"></td>
            </tr>
        </table>
        <script language="javascript">
            document.getElementById("btnFechar").src = '<%=request.getContextPath()%>/resources/images/bt_login_nrml.gif';
        </script>
        <%
        try{
            log.debug("acessoNegado.jsp:Session IdValid -> "+request.isRequestedSessionIdValid());
            log.debug("acessoNegado.jsp:Session Id      -> "+request.getSession().getId());
            log.debug("acessoNegado.jsp:IP Address      -> "+request.getRemoteAddr());
            log.debug("acessoNegado.jsp:IP X-Forward-For-> "+request.getHeader("X-Forwarded-For"));
            log.debug("acessoNegado.jsp:USUARIO_LOGIN   -> "+request.getSession().getAttribute(ConstantesCRM.USUARIO_LOGIN));
            request.getSession().invalidate();
        }catch(Exception ex){}
        %>
    </netui-template:section>
</netui-template:template>
