<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="parametrizacaoBannerForm"
             type="VOLE.ParametrizacaoBanner.formBeans.ParametrizacaoBannerForm" />

<html>
<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript">
        <% if (request.getAttribute("erro") != null) {%>
        alert('<%=request.getAttribute("erro")%>');
        <% } else { %>
        alert('Desassociação realizada com sucesso.');
        parent.pesquisar();
        <% } %>
    </script>
</head>
<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
    <% if (request.getAttribute("detalhesErro") != null) {%>
    <!-- <%=request.getAttribute("detalhesErro")%> -->
    <% } %>
</body>
</html>