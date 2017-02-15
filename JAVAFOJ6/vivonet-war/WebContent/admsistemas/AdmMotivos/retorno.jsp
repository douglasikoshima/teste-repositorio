<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<bean:define id="motivoForm" name="motivoForm" scope="request" />
<html>
<script>
    parent.parent.screen_unblock();
    <logic:notEmpty name="motivoForm" property="erroMsg">
        msg = "Erros:\n<bean:write name="motivoForm" property="erroMsg"/>";
        alert(msg);
        parent.document.forms[0].reset();
    </logic:notEmpty>
    <logic:empty name="motivoForm" property="erroMsg">
        parent.fechar();
    </logic:empty>
</script>
</html>