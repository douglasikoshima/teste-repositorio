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
             property="consultorRelacionamentoForm"
             type="cliente.ConsultorRelacionamento.ConsultorRelacionamentoController.ConsultorRelacionamentoForm" />
<html>
    <head>
        <link href="<%=request.getContextPath()%>/resources/css/frontoffice.css" type="text/css" rel="stylesheet"/>
        <script type="text/javascript" language="JavaScript" src="/FrontOfficeWeb/resources/scripts/frameweb.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/validacao.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/vivoval.js"></script>
        <script type="text/javascript" language="javascript" src="/FrontOfficeWeb/resources/scripts/prototype.js"></script>
        <script type="text/javascript" src="/FrontOfficeWeb/resources/scripts/xtooltip.js"></script>
        <script type="text/javascript">

            transfereSelecaoLista = function(listaOrigem, listaDestino) {
                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                var i;
                var c = 0;
                var arrayTransferidos = new Array();
                var inVerificaDuplicidade = true;

                if (listaOrigem.selectedIndex < 0) {
                    alert('Selecione um item para transferir.');
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    return;
                }

                if (!loadConsultorSelecionado()) {
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    return;
                }

                for(i = 0; i < listaOrigem.options.length; i++) {
                    if (listaOrigem.options[i].selected) {
                        if (inVerificaDuplicidade) {
                            if (!valueExistsInSelect(listaDestino, listaOrigem.options[i].value)) {
                                listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                                arrayTransferidos[c] = i;
                                c++;
                            } else {
                                alert("A empresa " + listaOrigem.options[i].text  + " CNPJ: " + listaOrigem.options[i].value + " já foi selecionada.");
                            }
                        } else {
                            if (!valueExistsInSelect(listaDestino, listaOrigem.options[i].value)) {
                                listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                            }
                            arrayTransferidos[c] = i;
                            c++;
                        }
                    }
                }
                for (var j = arrayTransferidos.length-1; j >= 0; j--) {
                    listaOrigem.options[arrayTransferidos[j]] = null;
                }
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            }

            removerSelecaoLista = function(listaOrigem, listaDestino) {
                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                var i;
                var c = 0;
                var arrayTransferidos = new Array();
                var inVerificaDuplicidade = true;

                if (listaOrigem.selectedIndex < 0) {
                    alert('Selecione um item para transferir.');
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    return;
                }

                if (!loadConsultorSelecionado()) {
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    return;
                }

                for(i = 0; i < listaOrigem.options.length; i++) {
                    if (listaOrigem.options[i].selected) {
                        listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                    }
                }

                for(i = listaOrigem.options.length-1; i>=0; i--) {
                    if(listaOrigem.options[i].selected) {
                        listaOrigem.options[i] = null;
                    }
                }
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            }

            transfereSelecaoListaTotal = function(listaOrigem, listaDestino) {
                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                var i;
                var c = 0;
                var arrayTransferidos = new Array();
                var avisarDuplicidade = false;
                var inVerificaDuplicidade = true;
                if (!loadConsultorSelecionado()) {
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    return;
                }
                for(i = 0; i < listaOrigem.options.length; i++) {
                    if (inVerificaDuplicidade) {
                        if (!valueExistsInSelect(listaDestino, listaOrigem.options[i].value)) {
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                            arrayTransferidos[c] = i;
                            c++;
                        } else {
                            avisarDuplicidade = true;
                        }
                    } else {
                        if (!valueExistsInSelect(listaDestino, listaOrigem.options[i].value)) {
                            listaDestino.options[listaDestino.options.length] = new Option(listaOrigem.options[i].text, listaOrigem.options[i].value);
                        }
                        arrayTransferidos[c] = i;
                        c++;
                    }
                }
                for (var j = arrayTransferidos.length-1; j >= 0; j--) {
                    listaOrigem.options[arrayTransferidos[j]] = null;
                }
                alert("Alguns clientes duplicados não serão selecionados");
                loadConsultorSelecionado();
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            }

            getPesquisa = function(tpOperacao) {
                var bSubmit = true;
                if (tpOperacao == 'pesquisarConsultorVO') {
                    if ($F('dsLogin').blank() &&
                        $F('nmConsultor').blank() &&
                        $F('nrCPF').blank()) {
                        bSubmit = false;
                         alert('Selecione um filtro para a pesquisa.');
                    } else if (!$F('nrCPF').blank() && !validaCPF($F('nrCPF'))) {
                        alert('Digite um número de CPF válido.');
                        window.setTimeout(function(){$('nrCPF').focus();$('nrCPF').select()},200);
                        bSubmit = false;
                    }
                    limparTela();
                } else if (tpOperacao == 'pesquisarClienteVO') {
                    if ($F('nmRazaoSocial').blank() &&
                        $F('nrCNPJ').blank()) {
                        bSubmit = false;
                         alert('Selecione um filtro para a pesquisa.');
                    } else if (!$F('nrCNPJ').blank() && !validaCNPJ($F('nrCNPJ'))) {
                        alert('Digite um número de CNPJ válido.');
                        window.setTimeout(function(){$('nrCNPJ').focus();$('nrCNPJ').select()},200);
                        bSubmit = false;
                    }
                } else if (tpOperacao == 'pesquisarClienteVOPorIDConsultor') {
                    if ($('idConsultor').selectedIndex == 0) {
                        for (var i = $('listaContasSelecionadas').options.length-1; i >= 0; i--) {
                            $('listaContasSelecionadas').options[i] = null;
                        }
                        bSubmit = false;
                    }
                } else if (tpOperacao = 'pesquisarConsultorVOPorIDCliente') {
                    bSubmit = false;
                }
                if (bSubmit) {
                    limparTipoRelacionamentoConsultor(tpOperacao);
                    var container = 'container_' + tpOperacao;
                    new Ajax.Updater({ success : container}, 'getPesquisa.do', {
                        method: 'post',
                        evalScripts: true,
                        parameters: {
                            'tpOperacao': tpOperacao,
                            'idConsultor': $F('idConsultor'),
                            'inAssociado': $F('inAssociado'),
                            'consultorRelacionamento.consultorVO.dsLogin': $F('dsLogin').strip(),
                            'consultorRelacionamento.consultorVO.nmConsultor': $F('nmConsultor').strip(),
                            'consultorRelacionamento.consultorVO.nrCPF': $F('nrCPF').gsub('[^0-9]',''),
                            'consultorRelacionamento.clienteVO.nmRazaoSocial': $F('nmRazaoSocial').strip(),
                            'consultorRelacionamento.clienteVO.nrCNPJ': $F('nrCNPJ').gsub('[^0-9]','')
                        },
                        onComplete: function() {
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                            criticarListaVazia(tpOperacao);
                        },
                        onCreate: function() {
                            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                        }, on406: function(e) {
                            window.top.frameApp.createErrorPopUp('erroConsultor', e.statusText, e.responseText, false);
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        }
                    });
                }
            }

            loadTipoRelacionamento = function(lista) {

                 if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                 var idConsultor = lista.options[lista.selectedIndex].id;
                 if (document.getElementById("consultorRelacionamento_" + idConsultor)) {
                    document.getElementById("consultorRelacionamento_" + idConsultor).checked = true;
                    $('idRelacionamentoOriginal').value = idConsultor;
                 }
                 if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();

            }

            loadConsultorSelecionado = function() {
                var listaConsultores = document.getElementById("idConsultor");
                var consultorText = listaConsultores.options[listaConsultores.selectedIndex].text;
                var consultorValue = listaConsultores.options[listaConsultores.selectedIndex].value;
                 if (consultorValue == "") {
                    alert("Por favor, selecione um consultor válido.");
                    return false;
                 } else {
                    document.getElementById("nomeConsultorRelacionamento").value = consultorText;
                    document.getElementById("idConsultorRelacionamentoGravar").value = consultorValue;
                    return true;
                }

            }

            limparTipoRelacionamentoConsultor = function (tpOperacao) {
                if ((tpOperacao == 'pesquisarConsultorVO') || (tpOperacao = 'pesquisarClienteVOPorIDConsultor')) {
                    document.getElementById("consultorRelacionamento_R").checked = false;
                    document.getElementById("consultorRelacionamento_R2").checked = false;
                }
            }

            limparTipoRelacionamentoCliente = function (lista) {
                if(lista.options.length == 0) {
                    document.getElementById("clienteRelacionamento_R").checked = false;
                    document.getElementById("clienteRelacionamento_R2").checked = false;
                    document.getElementById("idConsultorRelacionamentoGravar").value = "";
                    document.getElementById("nomeConsultorRelacionamento").value = "-- Traz o nome do Consultor associado";
                }
            }

            limparTela = function () {
                document.getElementById("consultorRelacionamento_R").checked = false;
                document.getElementById("consultorRelacionamento_R2").checked = false;

                for (var i = $('listaContasSelecionadas').options.length-1; i >= 0; i--) {
                    $('listaContasSelecionadas').options[i] = null;
                }

                for (var i = $('listaCnpjSelecionados').options.length-1; i >= 0; i--) {
                    $('listaCnpjSelecionados').options[i] = null;
                }
                document.getElementById("clienteRelacionamento_R").checked = false;
                document.getElementById("clienteRelacionamento_R2").checked = false;

                document.getElementById("idConsultorRelacionamentoGravar").value = "";
                document.getElementById("nomeConsultorRelacionamento").value = "-- Traz o nome do Consultor associado";

            }


            excluirConsultorRelacionamento = function() {

                if ($('idConsultor').selectedIndex == 0){
                    alert('Por favor, selecione um consultor.');
                } else if ($('listaContasSelecionadas').selectedIndex < 0) {
                    alert('Ao menos um cliente deve ser selecionado.')
                } else if (confirm("Confirma a exclusão do Consultor de Relacionamento " + $('idConsultor').options[$('idConsultor').selectedIndex].text  + " do(s) cliente(s) selecionado(s)?")){
                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    $('consultorRelacionamentoForm').tpOperacao.value = "excluir";
                    $('consultorRelacionamentoForm').action = "setGestorRelacionamento.do";
                    $('consultorRelacionamentoForm').target = "ifraSubmit";
                    $('consultorRelacionamentoForm').submit();

                }
            }

            submitAlterar = function() {

                if ($('idConsultor').selectedIndex == 0){
                    alert('Por favor, selecione um consultor.');
                } else if ($('listaContasSelecionadas').selectedIndex < 0) {
                    alert('Ao menos um cliente deve ser selecionado.')
                } else if (!document.forms[0].nivelConsultorAlterar[0].checked && !document.forms[0].nivelConsultorAlterar[1].checked) {
                    alert('Por favor selecionar o nível de relacionamento.');
                } else if (validarAlteracaoContultor()){
                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    $('consultorRelacionamentoForm').tpOperacao.value = "alterar";
                    $('consultorRelacionamentoForm').action = "setGestorRelacionamento.do";
                    $('consultorRelacionamentoForm').target = "ifraSubmit";
                    $('consultorRelacionamentoForm').submit();
                }
            }

            submitGravar = function() {
                if ($('idConsultorRelacionamentoGravar').value == ""){
                    alert('Por favor, selecione um consultor.');
                } else if ($('listaCnpjSelecionados').options.length == 0) {
                    alert('Ao menos uma conta deve ser selecionada.')
                } else if (!document.forms[0].nivelConsultorGravar[0].checked && !document.forms[0].nivelConsultorGravar[1].checked) {
                    alert('Por favor selecionar o nível de relacionamento.');
                } else {
                    for (i = 0; i < $('listaCnpjSelecionados').options.length; i++) {
                        $('listaCnpjSelecionados').options[i].selected = true;
                    }
                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    $('consultorRelacionamentoForm').tpOperacao.value = "gravar";
                    $('consultorRelacionamentoForm').action = "gravarGestorRelacionamento.do";
                    $('consultorRelacionamentoForm').target = "ifraSubmit";
                    $('consultorRelacionamentoForm').submit();
                }
            }

            criticarListaVazia = function(tpOperacao) {
                if (tpOperacao == 'pesquisarClienteVO'  && $('listaContasDisponiveis').options.length == 0) {
                    if (!$F('nmRazaoSocial').blank()) {
                        alert("A razão social digitada é inválida ou o cliente pesquisado já possui consultores relacionados.");
                    } else if (!$F('nrCNPJ').blank()) {
                        alert("O número do CNPJ digitado é inválido ou o cliente pesquisado já possui consultores relacionados.");
                    }
                }
            }

            validarAlteracaoContultor = function () {

                if (document.forms[0].nivelConsultorAlterar[0].checked && $('idRelacionamentoOriginal').value =='R') {
                    alert("O consultor de relacionamento atual já é um Consultor de Relacionamento 1");
                    return false;
                } else if (document.forms[0].nivelConsultorAlterar[1].checked && $('idRelacionamentoOriginal').value =='R2') {
                    alert("O consultor de relacionamento atual já é um Consultor de Relacionamento 2");
                    return false;
                }

                if (document.forms[0].nivelConsultorAlterar[0].checked && $('idRelacionamentoOriginal').value =='R2') {
                    alert("Você esta alterando o nível de consultor de relacionamento atual, desta forma o cliente não possui mais Consultor de Relacionamento 2");
                    return true;
                } else if (document.forms[0].nivelConsultorAlterar[1].checked && $('idRelacionamentoOriginal').value =='R') {
                    alert("Você esta alterando o nível de consultor de relacionamento atual, desta forma o cliente não possui mais Consultor de Relacionamento 1");
                    return true;
                }

                return true;
            }

            recarregarTela = function() {
                 if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    $('consultorRelacionamentoForm').action = "loadConsultorCliente.do";
                    $('consultorRelacionamentoForm').target = "";
                    $('consultorRelacionamentoForm').submit();
            }

            pararTela = function() {
                 if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            }


            onload = function () {
                 if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            }
        </script>
        <style type="text/css">
            .radioCheckbox {border:none;background:none;margin:0;padding:0;}
            .select1 {width:320px}
            .select2 {width:200px}
            .select3 {width:320px}
            #table_quadroConsultor {
                margin:5px;
            }
            #table_quadroCliente {
                margin-left:5px;
            }
            #quadroConsultor_div, #quadroCliente_div {
                background:#ededed;
            }
            .divFiltros {
                margin:3px;
                padding:3px;
                width:100%;
                border:1px solid #ccc;
                background:#e3ecf3;
            }
        </style>
    </head>
    <body>
        <form name="consultorRelacionamentoForm" id="consultorRelacionamentoForm" action="getPesquisa.do">
        <html:hidden name="Form" property="tpOperacao" styleId="tpOperacao" />
        <html:hidden name="Form" property="paginaOrigem" styleId="paginaOrigem" value="consultor"/>
        <html:hidden name="Form" property="inAssociado" styleId="inAssociado" value="0"/>
        <input type="hidden" name="idRelacionamentoOriginal" id="idRelacionamentoOriginal" value=""/>
        <vivo:quadro width="760" height="190" id="quadroConsultor" label="Consultor">
            <div id="filtrosConsultor" class="divFiltros">
                <table width="100%">
                    <tr>
                        <td nowrap style="padding-right:20px;">Login FrontOffice <html:text name="Form" property="consultorRelacionamento.consultorVO.dsLogin" styleId="dsLogin" /></td>
                        <td nowrap style="padding-right:20px;">Nome <html:text name="Form" property="consultorRelacionamento.consultorVO.nmConsultor" styleId="nmConsultor" /></td>
                        <td nowrap>CPF <html:text name="Form" property="consultorRelacionamento.consultorVO.nrCPF" styleId="nrCPF" onkeyup="checaCPF(this)" onblur="checaCPF(this)" maxlength="14" style="width:90px;" /></td>
                        <td align="right" width="100%">
                            <a href="javascript:getPesquisa('pesquisarConsultorVO')"><img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" border="0" /></a>
                        </td>
                    </tr>
                </table>
            </div>
            <table>
                <tr>
                    <td colspan="9">Consultores disponíveis</td>
                </tr>
                <tr>
                    <td colspan="4" width="320" id="container_pesquisarConsultorVO" valign="top">
                        <html:select name="Form" property="idConsultor" styleId="idConsultor" styleClass="select3">
                            <option value="">-- Realize uma pesquisa a partir dos filtros acima --</option>
                            <logic:iterate id="iterConsultores" name="Form" property="consultoresDisponiveis.IDValorRelacionamentoVOArray">
                                <option value="<bean:write name="iterConsultores" property="id" />"><bean:write name="iterConsultores" property="valor" /></option>
                            </logic:iterate>
                        </html:select>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" align="center">Clientes Selecionados</td>
                    <td colspan="3">&nbsp;</td>
                    <td colspan="3" align="center">Nível de Relacionamento</td>
                </tr>
                <tr>
                    <td colspan="3" width="320" id="container_pesquisarClienteVOPorIDConsultor" valign="top">
                            <html:select name="Form" property="listaContasSelecionadas" styleId="listaContasSelecionadas" multiple="true" styleClass="select1" size="5">
                                <logic:iterate id="iterContas" name="Form" property="contasSelecionadas.IDValorRelacionamentoVOArray">
                                    <option value="<bean:write name="iterContas" property="id" />"><bean:write name="iterContas" property="valor" /></option>
                                </logic:iterate>
                            </html:select>
                    </td>
                    <td colspan="3" align="center"><a href="javascript:excluirConsultorRelacionamento();"><img vspace="5" id="imgRightRegionais" src="<%=request.getContextPath()%>/resources/images/bt_x_nrml.gif" style="clear:both;" border="0" /></a></td>
                    <td colspan="3" align="center">
                        <table>
                        <tr>
                            <td><html:radio name="Form" property="nivelConsultorAlterar" styleId="consultorRelacionamento_R" value="R" styleClass="radio" style="border=none"/></td>
                            <td>Consultor de Relacionamento 1</td>
                        </tr>
                        <tr>
                            <td><html:radio name="Form" property="nivelConsultorAlterar" styleId="consultorRelacionamento_R2" value="R2" styleClass="radio" style="border=none"/></td>
                            <td>Consultor de Relacionamento 2</td>
                        </tr>
                        <tr>
                            <td colspan="2" align="right"><a href="javascript:submitAlterar()"><img style="margin:5px 5px 0 0;border:none;" src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" /></a></td>
                        </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
        <vivo:quadro width="760" height="180" id="quadroCliente" label="Cliente">
            <div id="filtrosCliente" class="divFiltros">
                <table width="100%">
                    <tr>
                        <td nowrap style="padding-right:20px;">CNPJ&nbsp;&nbsp;<html:text name="Form" property="consultorRelacionamento.clienteVO.nrCNPJ" styleId="nrCNPJ" onkeyup="checaCNPJ(this)" onblur="checaCNPJ(this)" maxlength="18" style="width:110px;" /></td>
                        <td nowrap>Razão Social&nbsp;&nbsp;<html:text name="Form" property="consultorRelacionamento.clienteVO.nmRazaoSocial" styleId="nmRazaoSocial" onkeyup="" onblur="" /></td>
                        <td align="right" width="100%">
                            <a href="javascript:getPesquisa('pesquisarClienteVO')"><img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" border="0" /></a>
                        </td>
                    </tr>
                </table>
            </div>
            <table>
                <tr>
                    <td align="center">Clientes disponíveis</td>
                    <td></td>
                    <td align="center">Clientes selecionadas</td>
                </tr>
                <tr>
                    <td width="220" id="container_pesquisarClienteVO" valign="top">
                        <html:select name="Form" property="listaContasDisponiveis" styleId="listaContasDisponiveis" multiple="true" styleClass="select2" size="8">
                            <logic:iterate id="iterContas" name="Form" property="contasSelecionadas.IDValorRelacionamentoVOArray">
                                <option value="<bean:write name="iterContas" property="id" />"><bean:write name="iterContas" property="valor" /></option>
                            </logic:iterate>
                        </html:select>
                    </td>
                    <td align="center" valign="middle">
                        <table>
                         <tr><td><a href="javascript:transfereSelecaoLista($('listaContasDisponiveis'),$('listaCnpjSelecionados'));"><img vspace="5" id="imgRightRegionais" src="<%=request.getContextPath()%>/resources/images/bt_rightaln_nrml.gif" style="clear:both;" border="0" /></a></td></tr>
                         <tr><td><a href="javascript:transfereSelecaoListaTotal($('listaContasDisponiveis'),$('listaCnpjSelecionados'));"><img vspace="5" id="imgRightRegionais" src="<%=request.getContextPath()%>/resources/images/bt_right_nrml.gif" style="clear:both;" border="0" /></a></td></tr>
                         <tr><td><a href="javascript:removerSelecaoLista($('listaCnpjSelecionados'),$('listaContasDisponiveis'));limparTipoRelacionamentoCliente($('listaCnpjSelecionados'));"><img vspace="5" id="imgLeftRegionais" src="<%=request.getContextPath()%>/resources/images/bt_leftaln_nrml.gif" style="clear:both;margin-bottom:5px;" border="0" /></a></td></tr>
                        </table>
                    </td>
                    <td id="" width="220" valign="top" style="padding-left:10px;">
                        <html:select name="Form" property="listaCnpjSelecionados" styleId="listaCnpjSelecionados" multiple="true" styleClass="select2" size="8">
                        </html:select>
                    </td>
                    <td colspan="3" align="center">
                        <table border="0">
                        <tr>
                            <td colspan="2" align="left">Consultor de Relacionamento:</td>
                        </tr>
                        <tr>
                            <td colspan="2" align="left">
                                <input type="text" name="nomeConsultorRelacionamento" value="-- Traz o nome do Consultor associado" style="width:190px;">
                                <html:hidden name="Form" property="idConsultorRelacionamentoGravar" styleId="idConsultorRelacionamentoGravar" />
                            </td>
                        </tr>
                         <tr>
                            <td colspan="2" align="left">Nível de Relacionamento:</td>
                        </tr>
                        <tr>
                            <td><html:radio name="Form" property="nivelConsultorGravar" styleId="clienteRelacionamento_R" value="R" styleClass="radio" style="border=none"/></td>
                            <td>Consultor de Relacionamento 1</td>
                        </tr>
                        <tr>
                            <td><html:radio name="Form" property="nivelConsultorGravar" styleId="clienteRelacionamento_R2" value="R2" styleClass="radio" style="border=none"/></td>
                            <td>Consultor de Relacionamento 2</td>
                        </tr>
                        </table>
                    </td>
                </tr>
            </table>
        </vivo:quadro>
        <div style="float:left;">
            <a onclick="top.window.location.href='/FrontOfficeWeb/index.jsp'"><img style="margin:5px 0 0 5px;border:none;" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" /></a>
        </div>
        <div style="float:right">
            <a href="javascript:submitGravar()"><img style="margin:5px 5px 0 0;border:none;" src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" /></a>
        </div>
        </form>
        <iframe id="ifraSubmit" name="ifraSubmit" style="visibility:hidden;" width="0px" height="0px"></iframe>
        <vivo:alert atributo="msgRetorno" scope="request" />
    </body>
</html>