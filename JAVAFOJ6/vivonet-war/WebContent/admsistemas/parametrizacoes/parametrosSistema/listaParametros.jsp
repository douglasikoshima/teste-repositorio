<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="parametrosForm"
             type="admsistemas.parametrizacoes.parametrosSistema.ParametrosSistemaController.ParametrosForm"/>

<html>
    <head>
        <title>
            Lista de Parâmetros
        </title>
    </head>
    <body>
        <vivo:tbTable selectable="true" onRowClick="" layoutWidth="750" layoutHeight="290" tableWidth="749" styleId="tableTitle" sortable="true" >
            <vivo:tbHeader>
                <vivo:tbHeaderColumn align="left" width="25%" type="string">Código do Parâmetro</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="30%" type="string">Descrição do Parâmetro</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="left" width="25%" type="string">Valor do Parâmetro</vivo:tbHeaderColumn>
                <vivo:tbHeaderColumn align="center" width="15%" type="string">Última Alteração</vivo:tbHeaderColumn>
            </vivo:tbHeader>
            <vivo:tbRows>
                    <logic:iterate id="parametro" name="Form" property="listaParametros" indexId="idx" type="br.com.vivo.fo.dbclasses.ApoioParametro">
                        <%
                        java.text.SimpleDateFormat df = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                        String dtUltimaAlteracao = df.format(new java.sql.Date(parametro.getDtUltimaAlteracao().getTime()));
                        %>
                        <vivo:tbRow key="Linha" onRowClick='<%="editItem(this," + parametro.getIdParametro() + ")"%>'>
                            <vivo:tbRowColumn><bean:write name="parametro" property="cdParametro"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="parametro" property="dsParametro"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn><bean:write name="parametro" property="dsValorParametro"/></vivo:tbRowColumn>
                            <vivo:tbRowColumn>
                                <%=dtUltimaAlteracao%>
                            </vivo:tbRowColumn>
                        </vivo:tbRow>
                    </logic:iterate>
            </vivo:tbRows>
        </vivo:tbTable>
    </body>
</html>