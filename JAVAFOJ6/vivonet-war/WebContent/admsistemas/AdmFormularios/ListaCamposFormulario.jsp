<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="listaCamposFormulario" type="br.com.vivo.fo.cliente.vo.ResultsetDocument.Resultset"/>

<vivo:tbTable selectable="true" layoutWidth="450" layoutHeight="100" tableWidth="450" styleId="tableTitle" sortable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="left" width="15%" type="string">Obrigatório</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="35%" type="string">Tipo</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="40%" type="string">Item</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="10%" type="">&nbsp;</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <logic:iterate id="it" name="Form" property="linhas.linhaArray" indexId="idx">
            <vivo:tbRow key="linha1">
                <vivo:tbRowColumn><input type="checkbox" id="<bean:write name="it" property="valorArray[0]"/>" class="radio" onclick="mudarObrigatoriedade('<bean:write name="it" property="valorArray[0]"/>')"  <bean:write name="it" property="valorArray[4]"/>/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="it" property="valorArray[6]"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="it" property="valorArray[2]"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" onclick="excluirCampo('<bean:write name="it" property="valorArray[0]"/>','<%=idx%>')" style="cursor:pointer;"></vivo:tbRowColumn>
            </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>
