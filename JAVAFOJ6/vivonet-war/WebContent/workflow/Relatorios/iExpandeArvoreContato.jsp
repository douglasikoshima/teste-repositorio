<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
   
<%--<acesso:controlInitEnv/>--%>
<bean:define id="scriptArvore"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm.scriptArvore" />

<netui-template:template templatePage="/resources/jsp/templateUsuarioArvore.jsp">
    <netui-template:section name="bodySection">
    <%--<acesso:controlHiddenItem nomeIdentificador="wor_ieacfm_verpagina">--%>
        <table id="tableArvore" width="100%" border="0" cellspacing="1" cellpadding="2" class="tbl_bgBlue" align="center">
            <tr>
                <td class="tbl_titulo">Árvore de contatos</td>
            </tr>
            <tr>
                <td>
                <div id="divArvore" style="top: 0px; left: 0px; width:322px; height: 332px; padding: 1px; overflow: auto;">
                        <script>
                          <%=scriptArvore%>
                        //Liga animação
                        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();                          
                        </script>
                </div>
                </td>
            </tr>
        </table>
   
    <%--</acesso:controlHiddenItem>--%>
    </netui-template:section>
</netui-template:template>
     
