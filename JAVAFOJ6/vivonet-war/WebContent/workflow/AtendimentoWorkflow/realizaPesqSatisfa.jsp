<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
<% String idAtendimento = (String) request.getAttribute("idAtendimento"); %>
<% String tamanho = (String) request.getAttribute("tamanho"); %>
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    </head>
    <body onload="chamaPesquisa();">
        <acesso:controlHiddenItem nomeIdentificador="wor_rpessto_verpagina">
        <script>
            <!--
            function chamaPesquisa(){
                top.frameApp.abrePopModal("Pesquisa Satisfação","<%=request.getContextPath()%>/workflow/AtendimentoWorkflow/pesquisaSatisfacao.do?idAtendimento=<%=idAtendimento%>&tamanho=<%=tamanho%>");
                parent.bt05.innerText = 'Pesquisa Satisfação';
            }
            if(top.frameApp.dvAnimarAguarde != null) top.frameApp.stopAnimation();
            -->
        </script>
        </acesso:controlHiddenItem>
    </body>
</html>
