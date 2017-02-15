<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="gestorGerenteContaForm"
             type="workflow.AtendimentoFila.Portabilidade.formBeans.GestorGerenteContaForm" />

<div id="clienteWrapper">
    <form name="fGerenteGestor" id="fGerenteGestor" action="" method="POST" style="margin:0px;" onsubmit="return false;">
        <html:hidden name="Form" property="gerente" styleId="gerente" />
        <html:hidden name="Form" property="gestor" styleId="gestor" />
        <html:hidden name="Form" property="idGestorGerente" styleId="idGestorGerente"/>
        <html:hidden name="Form" property="tpOperacao" styleId="tpOperacao"/>
        <html:hidden name="Form" property="tpRelacao" styleId="tpRelacao"/>
        <html:hidden name="Form" property="idContact" styleId="idContact"/>
        <table id="tableGestorGerente" width="100%">
            <tr>
                <td><strong>Nome:</strong></td>
                <td><html:text name="Form" property="primeiroNome" styleId="primeiroNome" maxlength="50" /></td>
                <td><strong>Nome do meio:</strong></td>
                <td><html:text name="Form" property="nomeMeio" styleId="nomeMeio" maxlength="100" /></td>
                <td><strong>Sobrenome:</strong></td>
                <td><html:text name="Form" property="sobrenome" styleId="sobrenome" maxlength="50" /></td>
            </tr>
            <tr>
                <td><strong>CPF:</strong></td>
                <td>
                    <html:text name="Form"
                               property="nrCPF"
                               styleId="nrCPF"
                               maxlength="14"
                               onkeyup="checaCPF(this)"
                               onblur="checaCPF(this)" />
                </td>
                <td><strong>E-mail:</strong></td>
                <td colspan="3"><html:text name="Form" property="dsEmail" styleId="dsEmail" maxlength="255" /></td>
            </tr>
            <tr>
                <td><strong>Celular:</strong></td>
                <td>
                    <html:text name="Form"
                               property="nrTelefone"
                               styleId="nrTelefone"
                               onkeyup="maskPhoneNumberObj(this)"
                               maxlength="14" />
                </td>
                <td><strong>Tel. Contato:</strong></td>
                <td colspan="3">
                    <html:text name="Form"
                               property="nrTelefoneContato"
                               styleId="nrTelefoneContato"
                               onkeyup="maskPhoneNumberObj(this)"
                               maxlength="14" />
                </td>
            </tr>
            <tr>
                <td align="right" colspan="6" style="padding-top:19px">
                    <img src="<%=request.getContextPath()%>/resources/images/bt_cancelar_nrml.gif"
                         border="0"
                         style="cursor:pointer"
                         onmouseup="$('incluirAlterarGestorGerente').remove();" />
                    <img src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif"
                         border="0"
                         style="cursor:pointer;margin-right:15px;"
                         onmouseup="salvarInclusaoAlteracaoGestorGerente()" />
                </td>
            </tr>
        </table>
    </form>
</div>

<style type="text/css">
    #clienteWrapper {
        margin:5px;
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
    #tableGestorGerente input {
        width:120px;
    }
</style>
<script type="text/javascript">
    $('containerIncluirAlterarGestorGerente').setStyle({
        backgroundColor : '#ededed'
    });
</script>