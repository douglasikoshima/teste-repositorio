<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="simuladorForm"/>

<html>
<vivo:tbTable selectable="true" layoutWidth="763" layoutHeight="195" tableWidth="763" styleId="tbResultadoPesquisa" sortable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Carteira</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Sigla</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="50%" type="string">Nome</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="20%" type="string">CNPJ</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="center" width="10%" type="string">Data</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows scroll="false">
        <logic:iterate id="it" name="Form" property="listaTemplates.linhas.linhaArray" indexId="idx">
            <vivo:tbRow key="linha">
                <vivo:tbRowColumn><bean:write name="it" property="valorArray[0]"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="it" property="valorArray[1]"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="it" property="valorArray[2]"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="it" property="valorArray[3]"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="it" property="valorArray[4]"/></vivo:tbRowColumn>
            </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>
</html>