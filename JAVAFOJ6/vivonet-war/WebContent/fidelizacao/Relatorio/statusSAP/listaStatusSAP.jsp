<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="statusSAPForm"
             type="fidelizacao.Relatorio.statusSAP.StatusSAPController.StatusSAPForm" />

<html>
    <head>
        <title>
            Lista de Parâmetros
        </title>
    </head>
    <body>
        <vivo:tbTable selectable="true" onRowClick="" layoutWidth="750" tableWidth="749" layoutHeight="390" styleId="tableTitle" sortable="true" >
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="10%" type="string">Usuário</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="10%" type="string">Número do Pedido</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="50%" type="string">Observação</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="20%" type="string">Última Alteração</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="10%" type="string">XML IN</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                    <logic:iterate id="statusSAP" name="Form" property="listaStatusSAP" indexId="idx" type="br.com.vivo.fo.dbclasses.RetencaoStatusSAP">
                        <%
                        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        String dtUltimaAlteracao = df.format(new java.sql.Date(statusSAP.getDtUltimaAlteracao().getTime()));
                        %>
                        <vivo:tbRow key="Linha">
                            <vivo:tbRowColumn><bean:write name="statusSAP" property="nmLoginUsuario"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="statusSAP" property="nrPedido" format="#" /></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="statusSAP" property="dsObservacao" /></vivo:tbRowColumn>
                            <vivo:tbRowColumn>
                                <%=dtUltimaAlteracao%>
                            </vivo:tbRowColumn>
                            <vivo:tbRowColumn><img src="<%=request.getContextPath()%>/resources/images/bt_icon_xml2.gif" class="button" onmouseup="showXML(<%=idx%>)" /></vivo:tbRowColumn>
                        </vivo:tbRow>
                    </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
    </body>
</html>