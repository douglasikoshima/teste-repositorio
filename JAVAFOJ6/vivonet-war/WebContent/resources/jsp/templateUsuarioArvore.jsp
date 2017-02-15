<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<html>
    <head>
        <netui:base />
        <title><netui-template:attribute name="FrontOffice - Gestão do Usuário"/></title>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <link href="<%=request.getContextPath()%>/resources/css/xtree.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xtree.js"></script>
        <netui-template:includeSection name="headerSection"></netui-template:includeSection>
    </head>
    <body topmargin="0" leftmargin="2">
        <netui-template:includeSection name="bodySection"></netui-template:includeSection>
    </body>
</html>
