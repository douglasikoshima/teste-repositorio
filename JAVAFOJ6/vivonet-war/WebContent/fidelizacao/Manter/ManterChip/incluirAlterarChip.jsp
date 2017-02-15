<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-databinding.tld" prefix="netui-data"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<bean:define id="Form"     name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterChipFormBean.fidelizacaoManutChipVO"/>
<bean:define id="ListaDDD" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="manterChipFormBean.listaDDD"/>

<link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
<script language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js" ></script>

<script language="javascript" type="text/javascript">
    gravar = function(){
        f = document.forms[0];
        if (f.idChip.value == "") {
            if (validarForm(f)) incluirChip();
        } else if (f.idChip.value != "") {
            if (validarForm(f)){
                alterarChip(f.idChip.value);
            }
        }
    }

    validarForm = function(f) {
        var erro = false;
        if (f.idDDD.value == "" || f.cdChipAvulsoSAP.value == "" || f.cdChipPreProgSAP.value == ""){
            erro = true;
        }
        if (erro) {
            alert("O preenchimento de todos os campos é obrigatório.");
            return false;
        } else {
            //Retirada da Validação pois nao existe especificação requerendo validação de codigos.
            //if (validarCodigos(f)){
                return true;
            //} else {
            //    return false;
            //}
        }
    }

    validarCodigos = function(f){
        var nrDDD;
        for (i = 0; i < f.idDDD.length; i++){
            if (f.idDDD.options[i].selected == true) {
                nrDDD = f.idDDD.options[i].text;
                break;
            }
        }
        //Retirada da validação do codigo nos dois ultimos digitos verificando o ddd
        //    || f.cdChipAvulsoSAP.value.substring(f.cdChipAvulsoSAP.value.length-2,f.cdChipAvulsoSAP.value.length) != nrDDD
        //    || f.cdChipPreProgSAP.value.substring(f.cdChipPreProgSAP.value.length-2,f.cdChipPreProgSAP.value.length) != nrDDD) {
        if (f.cdChipAvulsoSAP.value.charAt(1).toUpperCase() != "G" || f.cdChipPreProgSAP.value.charAt(1).toUpperCase() != "G"){
            alert("Código SAP inválido.");
            return false;
        } else {
            f.cdChipAvulsoSAP.value  = f.cdChipAvulsoSAP.value.toUpperCase();
            f.cdChipPreProgSAP.value = f.cdChipPreProgSAP.value.toUpperCase();
            return true;
        }
    }

    incluirChip = function() {
       f = document.forms[0];
       f.action = "incluirChip.do";
       f.target = "frmIncluirAlterarChip";
       f.submit();
    }

    alterarChip = function(idChip) {
        f = document.forms[0];
        f.action = "alterarChip.do";
        f.target = "frmIncluirAlterarChip";
        f.submit();
    }

    msgOk = function() {
        window.top.frameApp.selecionaAbaChip();
        //document.getElementById("divIncluirAlterarChip").style.display = "block";
        window.top.frameApp.ti_frameAbas.location.href = "/FrontOfficeWeb/fidelizacao/Manter/ManterChip/begin.do";
    }
</script>

<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    <!--
        top.frameApp.oculta_div();
        document.body.style.backgroundColor = "#e2e2e3";
    -->
</SCRIPT>

<form action="incluirChip.do" onSubmit="return false;" method="post">

<html:hidden name="Form" property="idChip" />

<table cellpadding="0" cellspacing="3" style="margin:5px;">
    <tr>
        <td>DDD:</td>
        <td style="padding-left:3px;">
            <logic:notEqual name="Form" property="idChip" value="">
            <html:select name="Form" property="idDDD" styleId="ddd" disabled="true">
                <option value="0"><bean:write name="Form" property="nrDDD" /></option>
            </html:select>
            </logic:notEqual>

            <logic:equal name="Form" property="idChip" value="">
            <html:select name="Form" property="idDDD" styleId="ddd">
                <html:option value="">-- Selecione --</html:option>
                <html:options collection="ListaDDD" property="id" labelProperty="descricao"/>
            </html:select>
            </logic:equal>
        </td>
    </tr>
    <tr>
        <td>Chip Avulso C&oacute;digo SAP:</td>
        <td>
            <html:text name="Form" property="cdChipAvulsoSAP" style="text-transform:uppercase;" />
        </td>
    </tr>
    <tr>
        <td>Chip Pr&eacute;-Prog C&oacute;digo SAP:</td>
        <td>
            <html:text name="Form" property="cdChipPreProgSAP" style="text-transform:uppercase;" />
        </td>
    </tr>
    <tr>
        <td colspan="2" align="right">
            <img  style="border:none;"
                                src="/FrontOfficeWeb/resources/images/bt_cancelar_nrml.gif"
                                rolloverImage="/FrontOfficeWeb/resources/images/bt_cancelar_over.gif"
                                onClick="parent.divIncluirAlterarChip.style.display='none';"/>&nbsp;
            <img  style="border:none;"
                                src="/FrontOfficeWeb/resources/images/bt_gravar_nrml.gif"
                                rolloverImage="/FrontOfficeWeb/resources/images/bt_gravar_over.gif"
                                onMouseUp="gravar();"/>
        </td>
    </tr>
</table>

<vivo:alert atributo="msgErro" scope="request" />
<vivo:alert atributo="msgOk" scope="request" afterFunction="msgOk()" />

</form>