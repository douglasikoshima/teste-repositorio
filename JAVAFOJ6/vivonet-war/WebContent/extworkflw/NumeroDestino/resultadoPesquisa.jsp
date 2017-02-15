<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="consultarNumeroForm"
             type="extworkflw.NumeroDestino.ConsultarNumeroDestinoController.ConsultarNumeroForm" />

<logic:equal name="Form" property="inErro" value="false">

    <vivo:tbTable selectable="true" layoutWidth="660" layoutHeight="200" tableWidth="660" styleId="tbResultadoPesquisa" sortable="true">
        <vivo:tbHeader>
            <vivo:tbHeaderColumn align="center" width="33%" type="string">Telefone</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="34%" type="string">Data</vivo:tbHeaderColumn>
            <vivo:tbHeaderColumn align="center" width="33%" type="string">Status</vivo:tbHeaderColumn>
        </vivo:tbHeader>
        <vivo:tbRows scroll="true">
            <logic:iterate id="listaHistorico" name="Form" property="historicoLinha" indexId="idx">
                <vivo:tbRow key="linha">
                    <vivo:tbRowColumn>
                        <bean:write name="listaHistorico" property="nrLinha" />
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <bean:write name="listaHistorico" property="data" />
                    </vivo:tbRowColumn>
                    <vivo:tbRowColumn>
                        <bean:write name="listaHistorico" property="dsStatus"/>
                    </vivo:tbRowColumn>
                </vivo:tbRow>
            </logic:iterate>
        </vivo:tbRows>
    </vivo:tbTable>

</logic:equal>

<logic:equal name="Form" property="inErro" value="true">

    <div style="width:100%;height:100%;margin-top:30px;">
        <bean:write name="Form" property="msgRetorno" />
    </div>

</logic:equal>