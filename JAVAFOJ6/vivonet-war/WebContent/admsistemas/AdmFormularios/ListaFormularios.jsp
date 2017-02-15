<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="camposDinamicosForm" type="admsistemas.AdmFormularios.AdmFormulariosController.CamposDinamicosForm"/>

<vivo:tbTable selectable="true" layoutWidth="740" layoutHeight="200" tableWidth="740" styleId="tableTitle" sortable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="left" width="42%" type="string">Nome do Formulário</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="43%" type="string">Funcionalidade associada</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <logic:iterate id="it" name="Form" property="listaFormularios.linhas.linhaArray" indexId="idx">
            <vivo:tbRow key="linha1">
                <vivo:tbRowColumn><bean:write name="it" property="valorArray[1]"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><bean:write name="it" property="valorArray[2]"/></vivo:tbRowColumn>
                <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" onclick="showForm('<bean:write name="it" property="valorArray[0]"/>');" style="cursor:pointer;"></vivo:tbRowColumn>
                <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_duplicar.gif" title="Duplicar formulário"   onclick="duplicarForm('<bean:write name="it" property="valorArray[0]"/>');" style="cursor:pointer;"></vivo:tbRowColumn>
                <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" onclick="alterarForm('<bean:write name="it" property="valorArray[0]"/>');" style="cursor:pointer;"></vivo:tbRowColumn>
                <vivo:tbRowColumn><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" onclick="verificaExcluirForm('<bean:write name="it" property="valorArray[0]"/>');" style="cursor:pointer;"></vivo:tbRowColumn>
            </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>
