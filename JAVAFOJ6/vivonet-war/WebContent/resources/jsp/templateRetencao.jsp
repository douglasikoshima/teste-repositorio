<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<html>
    <head>
        <netui:base/>
        <title><netui-template:attribute  name="title" defaultValue="FrontOffice - Fidelização e Rentenção"/></title>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
        <netui-template:includeSection name="headerSection">
        </netui-template:includeSection>
    </head>
    <body bgcolor="#ffffff" text="#000000" topmargin="0" leftmargin="2">
        <netui-template:includeSection name="bodySection">
        </netui-template:includeSection>
    </body>
</html>
