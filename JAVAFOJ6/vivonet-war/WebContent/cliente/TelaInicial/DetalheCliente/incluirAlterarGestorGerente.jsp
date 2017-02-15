<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<acesso:controlInitEnv/>

<bean:define id="Form"
             name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow"
             property="gestorGerenteContaForm"
             type="workflow.AtendimentoFila.Portabilidade.formBeans.GestorGerenteContaForm" />
<script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>

<div id="clienteWrapper">
    <form id="gestorGerenteContaForm">
    <html:hidden name="Form" property="gerente" styleId="gerente" />
    <html:hidden name="Form" property="gestor" styleId="gestor" />
    <html:hidden name="Form" property="idGestorGerente" styleId="idGestorGerente" />
    <html:hidden name="Form" property="tpOperacao" styleId="tpOperacao"/>
    <html:hidden name="Form" property="tpRelacao" styleId="tpRelacao"/>
    <html:hidden name="Form" property="idContact" styleId="idContact"/>
    <html:hidden name="Form" property="mensagemEndereco" styleId="mensagemEndereco"/>
    <html:hidden name="Form" property="mensagemComplemento" styleId="mensagemComplemento"/>
    <table id="tableGestorGerente" width="100%" border="0">
        <tr>
            <td><strong>Nome:</strong></td>
            <td><html:text name="Form" property="primeiroNome" styleId="primeiroNome" maxlength="50"/></td>
            <td><strong>Nome do meio:</strong></td>
            <td><html:text name="Form" property="nomeMeio" styleId="nomeMeio" maxlength="100"/></td>
            <td><strong>Sobrenome:</strong></td>
            <td><html:text name="Form" property="sobrenome" styleId="sobrenome" maxlength="50"/></td>
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
            <td><html:text name="Form" property="dsEmail" styleId="dsEmail" maxlength="255"/></td>
            <td><strong>Data de nascimento:</strong></td>
            <td>
                <html:text name="Form"
                           property="dataNascimento"
                           styleId="dataNascimento"
                           style="width:65px;"
                           maxlength="10"
                           onblur="checaData(this)"
                           onkeyup="checaData(this);" />
        </tr>
        <tr>
            <td colspan="6">
                <table border="0" cellpadding="0" cellspacing="0" width="90%">
                <tr>
                    <td colspan="2"><strong>Tel. Contato com a Vivo 1:</strong></td>
                    <td>
                        <html:text name="Form"
                                   property="nrTelefone"
                                   styleId="nrTelefone"
                                   onblur="formatPhoneNumberObj(this);"
                                   maxlength="11" />
                    </td>
                    <td colspan="2"><strong>Tel. Contato com a Vivo 2:</strong></td>
                    <td>
                        <html:text name="Form"
                                   property="nrTelefoneContato"
                                   styleId="nrTelefoneContato"
                                   onblur="formatPhoneNumberObj(this);"
                                   maxlength="11" />
                    </td>
               </tr>
               </table>
            </td>
        </tr>
        <tr>
            <td align="right" colspan="6" style="padding-top:19px">
                <img src="<%=request.getContextPath()%>/resources/images/bt_cancelar_nrml.gif"
                     border="0"
                     style="cursor:pointer"
                     onclick="$('dvIncluirAlterarGestorGerente').remove();" />
                <img src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif"
                     border="0"
                     style="cursor:pointer;margin-right:15px;"
                     onmouseup="iframePopupTI.fraSubAbas.salvarInclusaoAlteracaoGestorGerente();" />
            </td>
        </tr>
    </table>
    </form>
</div>

<style type="text/css">
    #clienteWrapper {
        margin:5px 5px 0 5px;
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
    $('containerdvIncluirAlterarGestorGerente').setStyle({
        backgroundColor : '#ededed'
    });
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
    <logic:notEmpty name="Form" property="mensagemEndereco">
        <logic:equal name="Form" property="mensagemEndereco" value="true">
            <logic:empty name="Form" property="primeiroNome">
                alert("Esta conta não possui um endereço associado no Vivonet.\nPara a inclusão de gestores é necessário que exista um endereço associado a conta.\nPor favor, solicite a correção, via chamado, para o Atlys.");
            </logic:empty>
            <logic:notEmpty name="Form" property="primeiroNome">
                alert("Esta conta não possui um endereço associado no Vivonet.\nPara a alteração de gestores é necessário que exista um endereço associado a conta.\nPor favor, solicite a correção, via chamado, para o Atlys.");
            </logic:notEmpty>
        </logic:equal>
    </logic:notEmpty>
    <logic:notEmpty name="Form" property="mensagemComplemento">
        <logic:equal name="Form" property="mensagemComplemento" value="true">
            alert("O campo complemento do endereço desta conta ultrapassou o limite de 50 caracteres permitido para o cadastro do gestor.\nPor favor, solicite a correção, via chamado, para o Atlys.");
        </logic:equal>
    </logic:notEmpty>
</script>