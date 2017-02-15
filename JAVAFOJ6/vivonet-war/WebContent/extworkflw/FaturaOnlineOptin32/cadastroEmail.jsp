<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>

<%
ParametrosVO parametros = ((ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS));
 %>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="faturaOnlineForm"
             type="extworkflw.FaturaOnlineOptin32.FaturaOnlineOptin32Controller.FaturaOnlineForm" />

<bean:define id="ComunicacaoVO"
             name="Form"
             property="cadastroEmailPF"
             type="br.com.vivo.fo.cliente.vo.ComunicacaoVODocument.ComunicacaoVO" />

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css" />
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" language="javascript">
        onload = function() {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }
        salvarEmail = function() {
            var dsTipoComunicacao = $('idTipoComunicacao').options[$('idTipoComunicacao').selectedIndex].text;
            if ($('idTipoComunicacao').selectedIndex == 0) {
                alert('Favor selecionar um tipo de e-mail.')
            } else if (!validaEmail($F('dsContato'))) {
                alert('Por favor, digite um e-mail válido.')
            } else {
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                $('formContaOnline').action = '/FrontOfficeWeb/extworkflw/FaturaOnlineOptin32/manageCadastroEmail.do?dsTipoComunicacao=' + dsTipoComunicacao;
                $('formContaOnline').submit();
                top.frameApp.$('incluirEmailPF').remove();
            }
        }
    </script>
</head>
<body id="bodyContaOnline">
<style type="text/css">
        body {
            background:#ededed;
            margin:20px;
        }
        #divTermoAtivacao {
            width:490px;
            padding:5px;
        }
        #formContaOnline {
            padding:5px;
        }
        form select {
            margin-left:3px;
        }
        form input {
            margin:0;
            padding:0;
            width:200px;
        }
    </style>
<form id="formContaOnline" method="post" target="ifrmAbas">
    <div id="divTermoAtivacao">
        <table width="100%" cellspacing="0" cellpadding="0">
            <tr>
                <td width="30%">Tipo de e-mail</td>
                <td width="70%">
                    <html:select name="Form" property="idTipoComunicacao" styleId="idTipoComunicacao">
                        <option value="">-- Selecione --</option>
                        <html:optionsCollection name="Form"
                                                property="listaTiposComunicacao"
                                                label="dsTipoComunicacao"
                                                value="idTipoComunicacao" />
                    </html:select>
                </td>
            </tr>
            <tr>
                <td>E-mail</td>
                <td>
                    <html:text name="Form" property="cadastroEmailPF.dsContato" styleId="dsContato" />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="right">
                    <img height="13" src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" style="cursor:pointer" onmouseup="salvarEmail()" />
                </td>
            </tr>
        </table>
    </div>
</form>
</body>
</html>