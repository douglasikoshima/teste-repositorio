<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<bean:define name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="detalheForm" id="DetalheForm"/>

<html>

<head>
</head>

<body>
<SCRIPT LANGUAGE="JavaScript">
<!--
    parent.window.status = "<%=request.getAttribute("status")%>"; 
    parent.window.mensagem = "<bean:write filter="false" name="DetalheForm" property="msgErro"/>";
    if(parent.window.status == 2)
        setTimeout("parent.window.reloadPage()",6000);
    else
        parent.window.reloadPage();
//-->
</SCRIPT>
<%="status="+request.getAttribute("status")+";"%> 

</body>

</html>