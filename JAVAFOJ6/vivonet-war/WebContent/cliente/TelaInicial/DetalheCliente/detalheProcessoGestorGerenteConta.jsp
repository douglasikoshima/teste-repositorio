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
             property="abaContato"
             type="cliente.TelaInicial.DetalheCliente.DetalheClienteController.AbaContato" />
<html>
<head>
    <title>Vivo Net</title>
    <link rel="STYLESHEET" type="text/css" href="/FrontOfficeWeb/resources/css/frontoffice.css">
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
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

        var inGestorGerente;

        incluirAlterarGestorGerente = function(idGestorGerente) {
            idGestorGerente = idGestorGerente ? idGestorGerente : 0;
            var params = $H();
            params.set('operacao', 'getInclusaoAlteracaoForm');
            params.set('inGestorGerente', inGestorGerente);
            params.set('idGestorGerente', idGestorGerente);
            var verbo, nome, title;
            if (idGestorGerente != 0) {
                verbo = 'Alterar';
            } else {
                verbo = 'Incluir';
            }
            title = verbo + ' ' + inGestorGerente + ' de conta';
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
            top.frameApp.createNewPopUp('dvIncluirAlterarGestorGerente', title, 700, 135, null, 'DetalheCliente/getGestorGerenteConta.do?' + params.toQueryString(), true, null);
        }

        salvarInclusaoAlteracaoGestorGerente = function() {
            if (validarInclusaoAlteracaoGestorGerente()) {
                new Ajax.Request('setGestorGerenteConta.do', {
                    method: 'post',
                    parameters: top.frameApp.$('gestorGerenteContaForm').serialize(),
                    asynchronous: false,
                    onCreate: function() {
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                    }, onSuccess: function(transport) {
                        var dom = parseXml(transport.responseText);
                        var jsonString = xml2json(dom, '');
                        var jsonObj = jsonString.evalJSON();
                        if (jsonObj.msg.retorno == 'true') {
                            var msgAlerta = jsonObj.msg.msErro;
                            if (msgAlerta != null && msgAlerta != '') {
                                alert(msgAlerta);   
                            }
                            alert('Gerente incluído com sucesso');
                            top.frameApp.$('dvIncluirAlterarGestorGerente').remove();
                            var params = $H();
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
                            parent.document.getElementById("fraSubAbas").src = 'getGestorGerenteConta.do';
                            CarregaAba('btx03');
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
            new Ajax.Request('verificaNumeroVivo.do', {
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
            if (top.frameApp.$F('primeiroNome').blank() || top.frameApp.$F('sobrenome').blank()) {
                alert('Por favor, digite o nome completo do ' + inGestorGerente);
                return false;
            } else if (!top.frameApp.validaCPF(top.frameApp.$F('nrCPF'))) {
                alert('Por favor, digite um número válido de CPF.')
                return false;
            } else if (!top.frameApp.validaEmail(top.frameApp.$F('dsEmail'))) {
                alert('Por favor, digite um endereço de e-mail válido.')
                return false;
            } else if (!top.frameApp.$F('dataNascimento').blank() && !top.frameApp.validaData(top.frameApp.$F('dataNascimento'))) {
                alert('Por favor, digite uma data de nascimento válida.')
                return false;
            } else if (top.frameApp.$F('nrTelefone').length < 13) {
                alert('Por favor, entre com um número de celular válido.');
                return false;
            } else if (top.frameApp.$F('nrTelefoneContato').length < 13) {
                alert('Por favor, entre com um número de telefone fixo.');
                return false;
            }
            return true;
        }
        
        
        checaCelular = function(str) {
           str = removeMascara(str);
           if ( !(  (str.length == 9)  || (str.length == 10)  ) ||  
                  (str.charAt(0) != "2") ||
                  (   (str.charAt(1) != "1") && (str.charAt(1) != "2") && (str.charAt(1) != "4") && (str.charAt(1) != "7") && (str.charAt(1) != "8")  ) ||
                  (str.charAt(2) != "9" ) ||
                  (   (parseInt(str.charAt(3)) < 6) || (parseInt(str.charAt(3)) > 9)  )  
            )
            
            {	return false
            }
            return true		
        }
        
        checaTelefone = function(str) {   
            str = removeMascara(str);
            if (  	 !(  (str.length == 9)  || (str.length == 10)  ) ||  
                  (str.charAt(0) != "2") ||
                  (   (str.charAt(1) != "1") && (str.charAt(1) != "2") && (str.charAt(1) != "4") && (str.charAt(1) != "7") && (str.charAt(1) != "8")  )
            )
            
            {	return false
            }
            return true		
        }
        
        /*
         * Função para remoção de caracteres especiais de números de telefone
         */
        function  removeMascara(telefone){
            var strCheck = '0123456789';
            var aux = '';
            var len = telefone.length;
            for(i = 0; i < len; i++){
                if (strCheck.indexOf(telefone.charAt(i))!=-1){
                    aux += telefone.charAt(i);
                }
            }
            return aux;
        }

        cancelarInclusaoAlteracaoGestorGerente = function(){
            $('dvIncluirAlterarGestorGerente').remove();
        }

        onload = function() {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }

    </script>
</head>
<body>
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
                        <td><strong>Telefone Contato Vivo 1</strong></td>
                        <td><bean:write name="gestorGerenteContaForm" property="nrTelefone" /></td>
                        <td><strong>Telefone Contato Vivo 2</strong></td>
                        <td colspan="2"><bean:write name="gestorGerenteContaForm" property="nrTelefoneContato" /></td>
                    </tr>
                    <tr>
                        <td><strong>E-mail</strong></td>
                        <td><bean:write name="gestorGerenteContaForm" property="dsEmail" /></td>
                        <td><strong>Data de nascimento</strong></td>
                        <td><bean:write name="gestorGerenteContaForm" property="dataNascimento" /></td>
                    </tr>
                    <tr>
                        <td><strong>Última alteração</strong></td>
                        <td><bean:write name="gestorGerenteContaForm" property="dtAlteracao" /></td>
                        <td><strong>Login responsável</strong></td>
                        <td><bean:write name="gestorGerenteContaForm" property="dsLoginResponsavel" /></td>
                    </tr>
                    <tr>
                        <td><strong>Consultor de Relacionamento 1</strong></td>
                        <td><bean:write name="gestorGerenteContaForm" property="consultoRelacionamento1" /></td>
                        <td><strong>Consultor de Relacionamento 2</strong></td>
                        <td><bean:write name="gestorGerenteContaForm" property="consultoRelacionamento2" /></td>
                    </tr>
                </table>
                <acesso:controlHiddenItem nomeIdentificador="cli_gst_alterar">
                <img src="<%=request.getContextPath()%>/resources/images/bt_alterar_nrml.gif"
                     border="0"
                     style="cursor:pointer;float:right;margin:4px 10px 10px 0;"
                     onmouseup="inGestorGerente='<%=gestorGerenteContaForm.isGestor() ? "gestor" : "gerente" %>';incluirAlterarGestorGerente(<bean:write name="gestorGerenteContaForm" property="idGestorGerente" format="#" />)" />
                </acesso:controlHiddenItem>
            </logic:present>
            <logic:notPresent name="gestorGerenteContaForm">
            <acesso:controlHiddenItem nomeIdentificador="cli_gst_incluir">
            <img src="<%=request.getContextPath()%>/resources/images/bt_incluir_nrml.gif"
                 border="0"
                 style="cursor:pointer;float:right;margin:4px 10px 10 px 0"
                 onmouseup="inGestorGerente='<%= (c.intValue() == 0) ? "gestor" : "gerente" %>';incluirAlterarGestorGerente()" />
            </acesso:controlHiddenItem>
            </logic:notPresent>
        </fieldset>
    </logic:iterate>
</div>
</body>
</html>