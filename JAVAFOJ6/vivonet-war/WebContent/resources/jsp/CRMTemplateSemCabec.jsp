<!--
Módulo.....: Configuração Ambiente Web
Caso de Uso: Template Lay-Out
-->
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<html>
    <head>
        <netui:base/>
        <title><netui-template:attribute name="title"/></title>
        <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">        
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
        <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/controleEventos.js"></script>
        <netui-template:includeSection name="headerSection"></netui-template:includeSection>
    </head>
    <body style="margin-left:5px;" topmargin="0" bgcolor="#ffffff" scroll="no" background="<%=request.getContextPath()%>/resources/images/bg_teste.gif">
        <!--Template->APLICACAO-->
        <netui-template:includeSection name="bodySection"></netui-template:includeSection>
        <!--
        Template->RODAPE
        <vivo:footer height="23" width="768"></vivo:footer>
        -->
    </body>
</html>
