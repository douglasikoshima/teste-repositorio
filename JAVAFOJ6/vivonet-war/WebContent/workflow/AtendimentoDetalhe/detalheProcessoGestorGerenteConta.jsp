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
             property="atendimentoDetalheForm"
             type="workflow.AtendimentoDetalhe.AtendimentoDetalheController.AtendimentoDetalheForm" />

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
                <acesso:controlHiddenItem nomeIdentificador="wor_gst_alterar">
                <img src="<%=request.getContextPath()%>/resources/images/bt_alterar_nrml.gif"
                             border="0"
                             style="cursor:pointer;float:right;margin:4px 10px 10px 0;"
                             onmouseup="inGestorGerente='<%=gestorGerenteContaForm.isGestor() ? "gestor" : "gerente" %>';incluirAlterarGestorGerente(<bean:write name="gestorGerenteContaForm" property="idGestorGerente" format="#" />)" />
                </acesso:controlHiddenItem>
            </logic:present>
            <logic:notPresent name="gestorGerenteContaForm">
            <acesso:controlHiddenItem nomeIdentificador="wor_gst_incluir">
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
<script type="text/javascript">
    incluirAlterarGestorGerente = function(idGestorGerente) {
        var params = $H();
        params.set('operacao', 'getInclusaoAlteracaoForm');
        params.set('inGestorGerente', inGestorGerente);
        params.set('idGestorGerente', idGestorGerente);
        var verbo, nome, title;
        if (idGestorGerente) {
            verbo = 'Alterar';
        } else {
            verbo = 'Incluir';
        }
        title = verbo + ' ' + inGestorGerente + ' de conta';
        window.top.frameApp.createNewPopUp('incluirAlterarGestorGerente', title, 700, 130, null, '/FrontOfficeWeb/workflow/AtendimentoDetalhe/getGestorGerenteConta.do', true, params);
    }
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

    salvarInclusaoAlteracaoGestorGerente = function() {
        if (validarInclusaoAlteracaoGestorGerente()) {
            new Ajax.Request('/FrontOfficeWeb/workflow/AtendimentoDetalhe/setGestorGerenteConta.do', {
                method: 'post',
                parameters: $('gestorGerenteContaForm').serialize(),
                asynchronous: false,
                onCreate: function() {
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                }, onSuccess: function(transport) {
                    var dom = parseXml(transport.responseText);
                    var jsonString = xml2json(dom, '');
                    var jsonObj = jsonString.evalJSON();
                    if (jsonObj.msg.retorno == 'true') {
                        alert('Gerente incluído com sucesso');
                        $('incluirAlterarGestorGerente').remove();
                        var params = $H();
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                        new Ajax.Updater('containerIncluirAlterarGestorGerente', '/FrontOfficeWeb/workflow/AtendimentoDetalhe/getGestorGerenteConta.do', {
                            evalScripts: true,
                            parameters: params,
                            method: 'get'
                        });
                    } else {
                        alert('Ocorreu um problema durante gravação dos dados.\nCodigo: '+jsonObj.msg.cdErro+'\nDescrição: '+jsonObj.msg.msErro);
                    }
                }, onComplete: function(transport) {
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                }
            });
        }
    }

    verificaNumeroVivo = function(nrLinha) {
        var isVivo = false;
        var params = $H();
        params.set('nrLinha', nrLinha);
        new Ajax.Request('/FrontOfficeWeb/workflow/AtendimentoDetalhe/verificaNumeroVivo.do', {
            method: 'post',
            parameters: params,
            asynchronous: false,
            onSuccess: function(transport) {
                var dom = parseXml(transport.responseText);
                var jsonString = xml2json(dom, '');
                var jsonObj = jsonString.evalJSON();
                if (jsonObj.msg.retorno != 'OK') {
                    isVivo = false;
                }else{
                    isVivo = true;
                }
            }
        });
        return isVivo;
    }

    validarInclusaoAlteracaoGestorGerente = function() {
        if ($F('primeiroNome').blank() || $F('sobrenome').blank()) {
            alert('Por favor, digite o nome completo do ' + inGestorGerente);
            return false;
        } else if (!validaCPF($F('nrCPF'))) {
            alert('Por favor, digite um número válido de CPF.')
            return false;
        } else if (!validaEmail($F('dsEmail'))) {
            alert('Por favor, digite um endereço de e-mail válido.')
            return false;
        } else if ($F('nrTelefone').length < 13) {
            alert('Por favor, digite um número de celular válido.');
            return false;
        } else if ( !verificaNumeroVivo( $F('nrTelefone') ) ) {
            alert('Por favor, digite um número de celular válido.');
            return false;
        } else if ($F('nrTelefoneContato').length < 13) {
            alert('Por favor, digite um telefone de contato válido.');
            return false;
        }
        return true;
    }

</script>