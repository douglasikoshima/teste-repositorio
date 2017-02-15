<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="prePagoForm"
             type="cliente.PrePago.PrePagoController.PrePagoForm" />

<html>
<head>
    <script type="text/javascript">

    </script>
</head>
<body>

<%
int index = Integer.parseInt(request.getParameter("index"));
String inTipoPessoa = request.getParameter("inTipoPessoa");
%>

<bean:define id="endereco"
             name="form"
             property='<%="clientesPesquisados." + inTipoPessoa + "Array[" + index + "].dadosEnderecoArray[0]"%>' />

<table cellpadding="3" border="0">
    <tr>
        <td><strong>Tipo de Endereço:</strong></td>
        <td><bean:write name="endereco" property="dsTipoEndereco" /></td>
    </tr>
    <tr>
        <td><strong>Tipo Logradouro:</strong></td>
        <td><bean:write name="endereco" property="nmTipoLogradouro" /></td>
        <td><strong>Título do Logradouro:</strong></td>
        <td colspan="3"><bean:write name="endereco" property="nmTitLogradouro" /></td>
    </tr>
    <tr>
        <td><strong>Logradouro:</strong></td>
        <td colspan="3"><bean:write name="endereco" property="nmLogradouro" /></td>
        <td><strong>Número:</strong> <bean:write name="endereco" property="nrLogradouro" /></td>
        <td><strong>Complemento:</strong> <bean:write name="endereco" property="nmComplemento" /></td>
    </tr>
    <tr>
        <td><strong>Bairro:</strong></td>
        <td colspan="3"><bean:write name="endereco" property="nmBairro" /></td>
        <td><strong>Município:</strong> <bean:write name="endereco" property="nmMunicipio" /></td>
        <td><strong>CEP:</strong> <bean:write name="endereco" property="nrCEP" /></td>
    </tr>
    <tr>
        <td><strong>Estado (UF):</strong></td>
        <td><bean:write name="endereco" property="sgUF" /></td>
        <td colspan="3"><strong>País:</strong> <bean:write name="endereco" property="nmPais" /></td>
    </tr>
</table>

</body>
</html>