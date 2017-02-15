<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>

<bean:define id="campo"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="camposDinamicosForm.campoFormulario"
             type="admsistemas.AdmFormularios.Campo"/>

<bean:define id="lista"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="camposDinamicosForm.listaDominio"
             type="java.util.ArrayList"/>

<head>
    <title>Vivo Net</title>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">

    <style type="text/css">
        #clienteWrapper {
            margin:3px;
        }
        #clienteWrapper img {
            margin-right:2px;
        }
        .gestorGerente strong {
            color:#000;
        }
        .gestorGerente td {
            color:#1865c5;
        }
    </style>
    <script type="text/javascript">
      onload = function() {
           if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
      }
    </script>
</head>

<div id="clienteWrapper">
 <logic:notPresent name="errorMsg" scope="request">
    <form method="post" action="" id="camposFormAlt" name="camposFormAlt" enctype="multipart/form-data" style="margin:0px;">
    <input type="hidden" name="tpCampoAlt" id="tpCampoAlt" value="<bean:write name="campo" property="apCampo" />" />
    <input type="hidden" name="idCampoAlt" id="idCampoAlt" value="<bean:write name="campo" property="idCampo" />" />

    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
    <table border="0" width="95%" align="center" cellpadding="2" cellspacing="2" bgcolor="#ededed" >
        <tr>
            <td width="100"><b>Nome:</b></td>
            <td width="161"><bean:write name="campo" property="nmCampo" /></td>
            <td width="74">&nbsp;</td>
            <td width="410" rowspan="5" valign="bottom">
                <div id="lstCampos">
                    <select name="vlCampoComboAlt" size="5" id="vlCampoComboAlt" style="width:160px;" multiple onmouseover="ativarToolTip(this, 1);">
                    </select>
                </div>
            </td>
        </tr>
        <tr>
            <td><b>Nome de apresentação:</b></td>
            <td width="161"><bean:write name="campo" property="lbCampo" /></td>
            <td width="74">&nbsp;</td>
        </tr>
        <tr>
            <td><b>Tipo de Campo:</b></td>
            <td><bean:write name="campo" property="tpCampo" /></td>
            <td align="center">
                <div id="btAdd">
                    <img src="/FrontOfficeWeb/resources/images/bt_rightaln_nrml.gif" onclick="adicionarItemAlt()" style="cursor:pointer;"/>
                </div>
            </td>
        </tr>
        <tr id="lnValor">
            <td>
                <div id="dvLblValor">
                <b>Valor:</b>
                </div>&nbsp;
            </td>
            <td>
                <div id="dvVlCampo">
                    <input type="text" name="vlCampoAlt" id="vlCampoAlt" value="" style="width:150px;">
                </div>
            </td>
            <td align="center">&nbsp;</td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td>&nbsp;</td>
            <td align="center">
                <div id="btRem">
                    <img src="/FrontOfficeWeb/resources/images/bt_x_nrml.gif" onclick="removerItemAlt()" style="cursor:pointer;"/>
                </div>
            </td>
        </tr>
        <tr>
            <td colspan="3">&nbsp;</td>
            <td align="right"><img src="/FrontOfficeWeb/resources/images/bt_incluir_nrml.gif" onclick="incluirCampoAlt()" style="cursor:pointer;"/></td>
        </tr>
    </table>
    </form>
    </logic:notPresent>
</div>
<script type="text/javascript">

    $('clienteWrapper').setStyle({
        backgroundColor : '#ededed'
    });

    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

      function loadDominios() {
        var destino = $('vlCampoComboAlt');

        <logic:iterate id="dominio" name="lista">
            var option = document.createElement('option');

            option.text  = '<bean:write name="dominio" property="vlDominio" />';
            option.value = '<bean:write name="dominio" property="vlDominio" />';

            destino.add(option);
        </logic:iterate>
      }

      loadDominios();
</script>