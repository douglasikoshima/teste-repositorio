<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>

<bean:define id="scriptArvore"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="filaMeuClienteFiltrosForm.scriptArvore" />

<netui-template:template templatePage="/resources/jsp/templateUsuarioArvore.jsp">
    <netui-template:section name="headerSection"> </netui-template:section>
    <netui-template:section name="bodySection">
        <table id="tableArvore" width="100%" border="0" cellspacing="1" cellpadding="2" class="tbl_bgBlue" align="center">
            <tr><td class="tbl_titulo">�rvore de contatos</td></tr>
            <tr>
                <td>
                    <div id="divArvore" style="top: 0px; left: 0px; width:322px; height: 332px; padding: 1px; overflow: auto;">
                        <script>
                            <%=scriptArvore%>
                            if( top.frameApp.dvAnimarAguarde!=null) top.frameApp.stopAnimation();
                        </script>
                    </div>
                </td>
            </tr>
        </table>
    </netui-template:section>
</netui-template:template>
