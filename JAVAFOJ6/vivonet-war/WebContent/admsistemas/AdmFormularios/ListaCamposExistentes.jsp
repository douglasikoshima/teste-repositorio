<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<script language="javascript" type="text/javascript">
    <logic:notEmpty name="msgError" scope="request">
    alert('<bean:write name="msgError" scope="request"/>');
    </logic:notEmpty>
</script>

<bean:define id="lista" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="camposDinamicosForm.listaCamposExistentes" type="java.util.ArrayList"/>

<vivo:tbTable selectable="true" layoutWidth="740" layoutHeight="200" tableWidth="740" styleId="tableTitle" sortable="true">
    <vivo:tbHeader>
        <vivo:tbHeaderColumn align="left" width="90%" type="string">Campos existentes</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
        <vivo:tbHeaderColumn align="left" width="5%" type="">&nbsp;</vivo:tbHeaderColumn>
    </vivo:tbHeader>
    <vivo:tbRows>
        <logic:iterate id="campo" name="lista" indexId="idx">
            <tr>
                <td width="90%"><bean:write name="campo" property="nmCampo"/></td>
                <td width="5%" align="center">
                    <logic:equal name="campo" property="apCampo" value="SELECT"><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" onclick="alterar('<bean:write name="campo" property="idCampo"/>')"></logic:equal>
                    <logic:equal name="campo" property="apCampo" value="SELECTMULTI"><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" onclick="alterar('<bean:write name="campo" property="idCampo"/>')"></logic:equal>
                    <logic:equal name="campo" property="apCampo" value="LABEL"><img src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" onclick="alterar('<bean:write name="campo" property="idCampo"/>')"></logic:equal>
                </td>
                <td width="5%" align="center"><img src="/FrontOfficeWeb/resources/images/bt_icon_excluir.gif" onclick="verificaExcluirForm('<bean:write name="campo" property="idCampo"/>')"></td>
            </tr>
        </logic:iterate>
    </vivo:tbRows>
</vivo:tbTable>