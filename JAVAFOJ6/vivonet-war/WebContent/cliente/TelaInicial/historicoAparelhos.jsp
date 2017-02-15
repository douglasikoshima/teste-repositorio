<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="java.text.SimpleDateFormat"%>
<%@ page import="java.util.Calendar"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="FormFrameLupaLinha" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm" type="cliente.TelaInicial.TelaInicialController.LupaLinhaForm"/>
<bean:define id="DadosLupaLinha"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm.formDadosLupaLinha"/>
<bean:define id="FormDetalheLinha"   name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="lupaLinhaForm.formDetalheLinhaVO"/>

<%!
private String convertCalendarToString(String format, Calendar calendar) {
    SimpleDateFormat sdf = new SimpleDateFormat( format );
    return sdf.format(calendar.getTime());
}
%>

<html>
<head>
    <script type="text/javascript">
        onload = function() {
            parent.parent.oculta_div();
            document.body.style.backgroundColor = '#ededed';
            <% if (request.getAttribute("erroAPI") != null) { %>
            alert('<%=request.getAttribute("erroAPI")%>');
            <% } %>
        }
    </script>
    <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
</head>
<body>
<!-- <%=request.getAttribute("erroInfoLog")!=null?request.getAttribute("erroInfoLog"):""%> -->
<vivo:tbTable selectable="true" layoutWidth="737" layoutHeight="138" tableWidth="737" styleId="historicoAparelhos">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="30%" type="string">Data de Associação</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="30%" type="string">Fabricante</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="40%" type="string">Aparelho</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <logic:iterate id="item" name="FormFrameLupaLinha" property="historicoAparelho" type="com.indracompany.ws.aparelhoservice.to.AparelhoLinha">
            <vivo:tbRow key="linha1">
                <vivo:tbRowColumn>
                    <strong><%=convertCalendarToString("dd/MM/yyyy", item.getDataInicioVigencia()) %></strong>
                </vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="item" property="produto.modeloProduto.fabricante.nome"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><strong><bean:write name="item" property="produto.modeloProduto.nome"/></strong></vivo:tbRowColumn>
            </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>
</body>
</html>