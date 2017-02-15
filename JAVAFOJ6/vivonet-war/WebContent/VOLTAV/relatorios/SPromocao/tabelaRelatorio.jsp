<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="relatorioForm" type="VOLTAV.relatorios.SPromocao.SPromocaoController.RelatorioForm"/>

<vivo:tbTable selectable="true" styleId="tbRelBlindagem" layoutHeight="300" layoutWidth="740" tableWidth="740">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn width="25%" type="String" align="center">Data Ativação</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn width="25%" type="String">Regional</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn width="25%" type="String">Tipo de Linha</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn width="25%" type="String" align="center">Total Ativações c/ Sucesso</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <logic:iterate id="itLinha" name="Form" property="resultset.linhas.linhaArray">
        <vivo:tbRow key="linha1">
            <logic:iterate id="valor" name="itLinha" property="valorArray">
                <vivo:tbRowColumn><bean:write name="valor"/></vivo:tbRowColumn>
            </logic:iterate>
        </vivo:tbRow>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>
