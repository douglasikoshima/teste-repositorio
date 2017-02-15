<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<!DOCTYPE html>

<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm"/>
<bean:define id="wFAcaoRetornoVO" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoForm.WFAcaoRetornoVO"/>

<html>
<head>
</head>
<body>
<table border="0" width="100%">
    <tr>
        <td align="center">
            <font size=3><b>
            <logic:match value="S" name="wFAcaoRetornoVO" property="acaoExecucao">Opera&ccedil;&atilde;o Conclu&iacute;da com Sucesso!</logic:match>
            <logic:match value="N" name="wFAcaoRetornoVO" property="acaoExecucao">Opera&ccedil;&atilde;o n&atilde;o Conclu&iacute;da!</logic:match>
            </b></font>
        </td>
    </tr>
    <tr>
        <td align="center">
            <strong>Mensagem:</strong> <bean:write name="wFAcaoRetornoVO" property="mensagem"/>
        </td>
    </tr>
</table>
<br>
<br>
<table border="0" width="100%">
    <tr>
        <td align="center">
            <input type="button" class="input_small" value="Fechar" onmouseup="fechar()" />
        </td>
    </tr>
</table>
<script type="text/javascript">
fechar = function() {
    filtro = top.frameCTI.filtro;
    var extra_vars = '&' + filtro.queryString;
    extra_vars += '&ACESSO_EXTERNO=true';
    window.location.href = '<bean:write name="form" property="fila"/>?voltar=1' + extra_vars;
}
</script>
</body>
</html>