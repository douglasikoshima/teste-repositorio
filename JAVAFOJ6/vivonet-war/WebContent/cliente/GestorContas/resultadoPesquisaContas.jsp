<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="gestorContasForm" type="cliente.GestorContas.GestorContasController.GestorContasForm"/>

<html:select name="Form" property="listaContasDisponiveis" styleId="listaContasDisponiveis" multiple="true">
    <logic:iterate id="itContas" name="Form" property="contasDisponiveis.itArray">
        <option value="<bean:write name="itContas" property="id"/>"><bean:write name="itContas" property="ds"/></option>
    </logic:iterate>
</html:select>
