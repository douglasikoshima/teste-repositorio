<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%if(session.getAttribute(ConstantesCRM.USUARIO_LOGIN) == null){
	System.out.println("Redirecting:: "+request.getContextPath()+"/begin.do");
    response.sendRedirect(request.getContextPath()+"/begin.do");
}%>
<!-- teste -->
<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Bem-Vindo"/>
    <netui-template:setAttribute name="modulo" value="Bem-Vindo"/>
    <netui-template:section name="headerSection">
    	<script>
    	</script>
    </netui-template:section>
    <netui-template:section name="bodySection">
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="resources/menus/MenuPrincipal.jsp" />
        <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="3"></div>
                
        <!--APLICACAO-->
        <!-- <vivo:quadro id="qdMain" height="478" width="790" label="Bem-Vindo"> -->
            <table width="790" height="478" border="0" cellpadding="0" cellspacing="0">
                <tr>                    
                    <td width="80%" valign="middle" align="center">
                        <table width="658" height="268" cellspacing="0" cellpadding="0" align="center" background="<%=request.getContextPath()%>/resources/images/arte_principal.jpg">
                            <tr>
                                <td height="35" id="seguranca_topo" colspan="2">
                                    Este sistema e todas as informa&ccedil;&otilde;es aqui contidas s&atilde;o de propriedade da Vivo
                                </td>
                            </tr>
                            <tr><td height="75" colspan="2"></td></tr>
                            <tr>
                                <td id="seguranca_mensagem" width="480" nowrap>
                                    Apenas usu&aacute;rios formalmente autorizados podem acessar e utilizar o sistema.<br>
                                    Usu&aacute;rios n&atilde;o autorizados n&atilde;o devem acessar o sistema mesmo que possam faz&ecirc;-lo.<br>
                                    &Eacute; responsabilidade do usu&aacute;rio conhecer seu perfil de acesso e, mesmo que o sistema 
                                    permita, n&atilde;o executar fun&ccedil;&otilde;es ou acessar dados fora da necessidade 
                                    estrita de sua fun&ccedil;&atilde;o e das autoriza&ccedil;&otilde;es formalmente concedidas.<br>
                                    Todos os acessos poder&atilde;o ser monitorados e auditados, e seus usu&aacute;rios 
                                    responsabilizados em toda a extens&atilde;o da lei por acessos indevidos.<br><br>
                                    
                                    <b>
                                        Em caso de d&uacute;vida entre em contato com <b>Divis&atilde;o de Seguran&ccedil;a da Informa&ccedil;&atilde;o</b>
                                    </b>
                                    
                                </td>
                                <td width="178" nowrap></td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
    <!--      </vivo:quadro> -->
  <html:form action="begin" method="post">
  <script for="window" event="onload">
        <%if(request.getSession().getAttribute("numeroGrupo")!=null){%>
            var numeroGrupo = '<%=request.getSession().getAttribute("numeroGrupo").toString()%>';
            document.forms[0].action="<%=request.getContextPath()%>/cliente/PrePago/begin.do?pesquisa=0&numeroGrupo="+numeroGrupo;
            document.forms[0].method = "POST";
            document.forms[0].submit();
         <%}%>
  </script>
  </html:form>
  </netui-template:section>
</netui-template:template>
