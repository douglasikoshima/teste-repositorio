<%@ page language="java" contentType="text/html;charset=UTF-8"%>

<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>

<html>

<bean:define id="cadastroTerminalForm"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="cadastroTerminalForm"/>

<head>

<script language="javascript">
    function retornar() {
<logic:equal name="cadastroTerminalForm" property="operacao" value="A">
        parent.listar();
</logic:equal>
<logic:equal name="cadastroTerminalForm" property="operacao" value="I">
        parent.limpar();
</logic:equal>
    }
</script>

</head>

<body onload="retornar();">

<vivo:alert atributo="msgError" scope="request"/>

</body>

</html>