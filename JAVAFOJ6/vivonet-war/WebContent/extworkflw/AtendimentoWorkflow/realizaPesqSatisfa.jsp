<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<% String idAtendimento = (String) request.getAttribute("idAtendimento"); %>
<% String tamanho = (String) request.getAttribute("tamanho"); %>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    </head>
    <body onload="chamaPesquisa();">        
        <script>
            <!--
            function chamaPesquisa(){
                top.frameApp.abrePopModal("Pesquisa Satisfação","<%=request.getContextPath()%>/extworkflw/AtendimentoWorkflow/pesquisaSatisfacao.do?idAtendimento=<%=idAtendimento%>&tamanho=<%=tamanho%>");
                parent.bt05.innerText = 'Pesquisa Satisfação';
            }
            if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
            -->
        </script>        
    </body>
</html>
