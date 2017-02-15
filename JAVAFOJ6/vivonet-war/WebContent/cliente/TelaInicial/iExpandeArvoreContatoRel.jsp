<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
   
<bean:define id="scriptArvore"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relacionamentoForm.scriptArvore" />

<netui-template:template templatePage="/resources/jsp/templateUsuarioArvore.jsp">
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">
        <table id="tableArvore" width="100%" border="0" cellspacing="1" cellpadding="2" class="tbl_bgBlue" align="center">
            <tr>
                <td class="tbl_titulo">�rvore de contatos</td>
            </tr>
            <tr>
                <td>
                <div id="divArvore" style="top: 0px; left: 0px; width:322px; height: 332px; padding: 1px; overflow: auto;">
                    <script>
                      <%=scriptArvore%>    
                        //Fim anima��o
                        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
                    </script>
                </div>
                </td>
            </tr>
        </table>
    </netui-template:section>
</netui-template:template>
     
