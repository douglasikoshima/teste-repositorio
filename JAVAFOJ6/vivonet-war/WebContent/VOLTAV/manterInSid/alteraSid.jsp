<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>

<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>

<head>

<title>Alterar SID</title>

<link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">        
<script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>
<script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/tratamento.js" ></script>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>

<script language="javaScript">
<!--
    function gravar() {
        if (document.frmAlteraSid.nrSid.value == '') {
            alert('Você deve informar um número valido.');
            return;
        }
        if (confirm('Deseja realmente aplicar este SID a todas as funcionalidades selecionadas?')) {
            parent.divAlteraSid.style.display = 'none';
            parent.gravar(document.frmAlteraSid.nrSid.value);
        }
    }

    function validarNumero() {
        if (window.event.keyCode < 48 || window.event.keyCode > 57)
            window.event.keyCode = 0;
    }
// -->
</script>

</head>

<body>
    <acesso:controlHiddenItem nomeIdentificador="in_sid_alt">
    <script>
        document.body.style.backgroundColor = '#ededed';
        //parent.parent.mostrar_div();
    </script>     

    <SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
        //parent.parent.oculta_div();
    </script>

<form name="frmAlteraSid">
    <table cellspacing="0" cellpadding="0" height="60">
        <tr> 
            <td style="text-indent:6px;">
                <table width="97%" border="0" cellspacing="1" cellpadding="1" align="center">
                    <tr><td height="4">&nbsp;</td></tr>
                    <tr>
                        <td width="175" class="tblEstatica_campoNome">
                            <netui:label value="Número do SID: " styleClass="tblEstatica_campoNome"/>
                        </td>
                        <td width="" align="left">
                            <input type="text" name="nrSid" size="20" class="input" maxlength="20" onkeypress="validarNumero();"/>
                        </td>
                    </tr>
                </table>

            </td>
        </tr>             
        <tr><td height="10"></td></tr>
        <tr>
            <td align="center" width="780" style="padding-right:20px;">
            <acesso:controlHiddenItem nomeIdentificador="gravar_in_sid">
                <img id="btIncluir" onClick="gravar(); return false" src="/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_salvar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_salvar_over.gif'" style="border:none;cursor:hand"/>
            </acesso:controlHiddenItem>
            </td>
        </tr>
    </table>
</form>

<script language="javascript">
    document.frmAlteraSid.nrSid.focus();
</script>

       </acesso:controlHiddenItem>
</body>
