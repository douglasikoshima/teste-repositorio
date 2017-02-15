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
             property="detalhesProcessoForm"
             type="workflow.AtendimentoFila.Portabilidade.formBeans.DetalhesProcessoForm" />

<div id="clienteWrapper">
    <logic:iterate id="gestorGerenteContaForm"
                   name="Form"
                   property="gestorGerenteContaForm"
                   indexId="c"
                   type="workflow.AtendimentoFila.Portabilidade.formBeans.GestorGerenteContaForm">
        <fieldset class="gestorGerente">
            <legend>
                <%= (c.intValue() == 0) ? "Gestor" : "Gerente" %> de Contas
            </legend>
            <logic:present name="gestorGerenteContaForm">
                <table width="100%">
                    <tr>
                        <td><strong>Nome</strong></td>
                        <td>
                            <bean:write name="gestorGerenteContaForm" property="primeiroNome" />
                            <bean:write name="gestorGerenteContaForm" property="nomeMeio" />
                            <bean:write name="gestorGerenteContaForm" property="sobrenome" />
                        </td>
                        <td><strong>CPF</strong></td>
                        <td colspan="2"><bean:write name="gestorGerenteContaForm" property="nrCPF" /></td>
                    </tr>
                    <tr>
                        <td><strong>DDD/Celular</strong></td>
                        <td><bean:write name="gestorGerenteContaForm" property="nrTelefone" /></td>
                        <td><strong>Telefone de Contato</strong></td>
                        <td colspan="2"><bean:write name="gestorGerenteContaForm" property="nrTelefoneContato" /></td>
                    </tr>
                    <tr>
                        <td><strong>E-mail</strong></td>
                        <td colspan="4"><bean:write name="gestorGerenteContaForm" property="dsEmail" /></td>
                    </tr>
                    <tr>
                        <td><strong>Última alteração</strong></td>
                        <td><bean:write name="gestorGerenteContaForm" property="dtAlteracao" /></td>
                        <td><strong>Login responsável</strong></td>
                        <td><bean:write name="gestorGerenteContaForm" property="dsLoginResponsavel" /></td>
                    </tr>
                </table>
                <acesso:controlHiddenItem nomeIdentificador="worp_gst_alterar">
                <img src="<%=request.getContextPath()%>/resources/images/bt_alterar_nrml.gif"
                             border="0"
                             style="cursor:pointer;float:right;margin:4px 10px 10px 0;"
                             onmouseup="inGestorGerente='<%=gestorGerenteContaForm.isGestor() ? "gestor" : "gerente" %>';incluirAlterarGestorGerente(<bean:write name="gestorGerenteContaForm" property="idGestorGerente" format="#" />)" />
                </acesso:controlHiddenItem>
            </logic:present>
            <logic:notPresent name="gestorGerenteContaForm">
            <acesso:controlHiddenItem nomeIdentificador="worp_gst_incluir">
            <img src="<%=request.getContextPath()%>/resources/images/bt_incluir_nrml.gif"
                             border="0"
                             style="cursor:pointer;float:right;margin:4px 10px 10 px 0"
                             onmouseup="inGestorGerente='<%= (c.intValue() == 0) ? "gestor" : "gerente" %>';incluirAlterarGestorGerente()" />
            </acesso:controlHiddenItem>
            </logic:notPresent>
        </fieldset>
    </logic:iterate>
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
</style>