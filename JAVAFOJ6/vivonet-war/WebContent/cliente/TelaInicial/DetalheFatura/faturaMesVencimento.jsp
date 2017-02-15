<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="lupaFaturamentoPos"
             type="cliente.TelaInicial.DetalheFatura.DetalheFaturaController.LupaFaturamentoPosForm" />

<html>
<head>
    <title>Lista de meses de vencimento disponíveis</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" language="javascript">
        onload = function() {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }
        pesquisar = function() {
            if ($F('faturaMesVencimento').blank()) {
                alert('Por favor, selecione o mês de vencimento desejado.');
            } else {
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                top.frameApp.$('divFaturaDetalhadaImagem').show();
                var f = document.forms[0];
                f.action = "consultarImagemFatura.do?dataVencimento=" + $F('faturaMesVencimento');
                f.submit();
            }
        }
    </script>
</head>
<body style="background:#ededed">
<form id="formFatura" name="formFatura" action="consultarImagemFatura.do" method="post" target="iframeFaturaDetalhadaImagem">
<table width="100%" height="100%">
    <tr>
        <td height="100%" width="100%" align="center" valign="middle">
            <p><strong>Mês de vencimento:</strong>
            <select name="faturaMesVencimento" id="faturaMesVencimento" style="margin-left:10px;">
                <option value="">-- Selecione --</option>
                <logic:iterate id="listaVencimentos" name="Form" property="faturaVencimentosDisponiveis" type="java.lang.String">
                <option value="<bean:write name="listaVencimentos" />"><%=listaVencimentos.substring(3)%></option>
                </logic:iterate>
            </select></p>
            <p>
                <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" border="0" onmouseup="pesquisar()" style="cursor:pointer" />
            </p>
        </td>
    </tr>
</table>
</form>
<vivo:alert atributo="msgErro" scope="request" />
</body>
</html>