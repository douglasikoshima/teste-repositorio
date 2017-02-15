<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<bean:define id="relatorioForm" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm"/>
<netui-template:template templatePage="/resources/jsp/templateUsuarioArvore.jsp">
    <netui-template:section name="bodySection">
        <script>
            <%=(String)request.getAttribute("arvore")%>
            <%request.setAttribute("arvore",(String)request.getAttribute("arvore"));%>
        </script>
        <script language="javascript" for="window" event="onload">
            if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        </script>
    </netui-template:section>
</netui-template:template>