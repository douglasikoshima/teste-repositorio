<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="trackingForm"
             type="cliente.TelaInicial.TelaInicialController.TrackingForm"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/messages.js"></script>
    <script language="javascript">
        onload = function() {
            if (window.top.frameApp.dvAnimarAguarde != null) window.top.frameApp.stopAnimation();
        }
        changeMask = function(tipoDocumento) {
            $('nrDocumento').value = '';
            if (tipoDocumento == '') {
                $('nrDocumento').readOnly = true;
            } else {
                $('nrDocumento').readOnly = false;
                if (tipoDocumento == 'CPF') {
                    $('nrDocumento').maxLength = '14';
                    $('nrDocumento').onkeyup = function() {
                        checaCPF($('nrDocumento'));
                    }
                } else if (tipoDocumento == 'CNPJ') {
                    $('nrDocumento').maxLength = '18';
                    $('nrDocumento').onkeyup = function() {
                        checaCNPJ($('nrDocumento'));
                    }
                }
                $('nrDocumento').focus();
            }
        }
        submitForm = function() {
            if ($('dsTipoDocumento').selectedIndex == 0) {
                alert(Messages.SL_DOCUMENTO_TIPO);
                return false;
            } else if ($('dsTipoDocumento').selectedIndex == 1
                    && !validaCNPJ($F('nrDocumento'))) {
                alert(Messages.DG_CNPJ_NUMERO_VALIDO);
                return false;
            } else if ($('dsTipoDocumento').selectedIndex == 2
                    && !validaCPF($F('nrDocumento'))) {
                alert(Messages.DG_CPF_NUMERO_VALIDO);
                return false;
            }
            $('formInputDocumento').submit();
        }
    </script>
</head>
<body>
<form id="formInputDocumento" method="post" action="trackingLista.do">
<table style="margin:10px;">
    <tr>
        <td>
            Tipo de documento:
            <html:select name="Form" property="dsTipoDocumento" styleId="dsTipoDocumento" onchange="changeMask(this.value)" style="margin-right:30px">
                <option value="">-- Selecione --</option>
                <option value="CNPJ">CNPJ</option>
                <option value="CPF">CPF</option>
            </html:select>
        </td>
        <td>
            Número:
            <html:text name="Form" property="nrDoc" styleId="nrDocumento" readonly="true" />
        </td>
        <td>
            <img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" style="cursor:pointer" onmouseup="submitForm()" />
        </td>
    </tr>
</table>
</form>
</body>
</html>