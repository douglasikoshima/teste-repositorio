<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<acesso:controlInitEnv/>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="filaPortabilidadeFiltrosForm"/>

<html>
<head>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
    <script language="javascript" type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
</head>
<body bottommargin="0" leftmargin="0" rightmargin="0" topmargin="0">
<vivo:tbTable layoutWidth="417" layoutHeight="180" tableWidth="417" styleId="tableTitle3" sortable="false" onRowClick="return false;">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="left" width="100%" type="string">Descrição</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <logic:iterate id="alertaVO" name="Form" property="listaAlertas" indexId="idx">
            <bean:define name="alertaVO" property="nmIcone" id="nmIcone"/>
            <%
            StringBuffer sbPathIcone = new StringBuffer();
            sbPathIcone.append("/FrontOfficeWeb/resources/images/")
                       .append(nmIcone.toString());
            String idClassRow = "";
            %>
            <bean:define name="alertaVO" property="nmCor" id="nmCor"/>
            <logic:notEmpty name="alertaVO" property="nmCor"><%idClassRow=nmCor.toString();%></logic:notEmpty>
            <vivo:tbRow key="linha" idClass='<%= idClassRow %>'>
                <vivo:tbRowColumn>
                    <bean:write name="alertaVO" property="dsAlerta"/>
                </vivo:tbRowColumn>
            </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>
<script type="javascript" type="text/javascript">
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
</script>
</body>
</html>