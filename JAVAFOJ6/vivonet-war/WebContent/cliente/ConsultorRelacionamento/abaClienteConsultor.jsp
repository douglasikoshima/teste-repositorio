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
    <script type="text/javascript" language="javascript">

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
            } else if (tpOperacao == 'pesquisarConsultorVOPorIDCliente') {
                if ($('listaContasDisponiveis').selectedIndex < 0) {
                   bSubmit = false;
                } else {
                    $('nrDocumentoSelecionado').value = $('listaContasDisponiveis').options[$('listaContasDisponiveis').selectedIndex].value;
                }
            }
            if (bSubmit) {
                limparTipoRelacionamentoCliente(tpOperacao);
                var container = 'container_' + tpOperacao;
                new Ajax.Updater({ success : container}, 'getPesquisa.do', {
                    method: 'post',
                    evalScripts: true,
                    parameters: {
                        'tpOperacao': tpOperacao,
                        'idConsultor': $F('idConsultor'),
                        'inAssociado': $F('inAssociado'),
                        'nrDocumentoSelecionado': $F('nrDocumentoSelecionado'),
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
                    }, on503: function(e) {
                        window.top.frameApp.createErrorPopUp('erroConsultor', e.statusText, e.responseText, false);
                        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    }
                });
            }
        }

        loadTipoRelacionamento = function(lista) {

             if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();

             if (lista.selectedIndex >= 0) {
                 var idConsultor = lista.options[lista.selectedIndex].id;
                 if (idConsultor != "" && $('clienteRelacionamento_' + idConsultor)) {
                    $('clienteRelacionamento_' + idConsultor).checked = true;
                 }
             }

            if (lista.id == "listaConsultoresRelacionamento1") {
                $('listaConsultoresRelacionamento2').selectedIndex  = -1;
            } else {
                $('listaConsultoresRelacionamento1').selectedIndex  = -1;
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
                document.getElementById("idConsultorRelacionamento").value = consultorValue;
                return true;
            }

        }

        limparTipoRelacionamentoCliente = function (tpOperacao) {
            if (tpOperacao == 'pesquisarClienteVO') {
                for (var i = $('listaConsultoresRelacionamento1').options.length-1; i >= 0; i--) {
                    $('listaConsultoresRelacionamento1').options[i] = null;
                }
                for (var i = $('listaConsultoresRelacionamento2').options.length-1; i >= 0; i--) {
                    $('listaConsultoresRelacionamento2').options[i] = null;
                }
                document.getElementById("clienteRelacionamento_R").checked = false;
                document.getElementById("clienteRelacionamento_R2").checked = false;
            }


        }

         criticarListaVazia = function(tpOperacao) {
             if (tpOperacao == 'pesquisarClienteVO'  && $('listaContasDisponiveis').options.length == 0) {
                    if (!$F('nmRazaoSocial').blank()) {
                        alert("Digite uma Razão Social válida.");
                    } else if (!$F('nrCNPJ').blank()) {
                        alert("Digite um número de CNPJ válido.");
                    }
             }
         }

        excluirConsultorRelacionamento = function() {

            var nomeConsultorExcluir = "";

            if ($('listaConsultoresRelacionamento1').selectedIndex >= 0 &&  $('listaConsultoresRelacionamento1').value != '') {
                nomeConsultorExcluir = $('listaConsultoresRelacionamento1').options[$('listaConsultoresRelacionamento1').selectedIndex].text;
            } else if ($('listaConsultoresRelacionamento2').selectedIndex >= 0 &&  $('listaConsultoresRelacionamento2').value != '') {
                nomeConsultorExcluir = $('listaConsultoresRelacionamento2').options[$('listaConsultoresRelacionamento2').selectedIndex].text;
            }

            if ($('listaContasDisponiveis').selectedIndex < 0){
                alert('Por favor, selecione uma empresa.');
            } else if (($('listaConsultoresRelacionamento1').selectedIndex < 0 || $('listaConsultoresRelacionamento1').value == '') && ($('listaConsultoresRelacionamento2').selectedIndex < 0 || $('listaConsultoresRelacionamento2').value == '')) {
                alert('Por favor, selecionar um consultor .');
           } else if (confirm("Confirma a exclusão do Consultor de Relacionamento " + nomeConsultorExcluir  + " do(s) cliente(s) selecionado(s)?")){
                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                $('consultorRelacionamentoForm').tpOperacao.value = "excluir";
                $('consultorRelacionamentoForm').action = "setGestorRelacionamento.do";
                $('consultorRelacionamentoForm').target = "ifraSubmit";
                $('consultorRelacionamentoForm').submit();
            }


        }


        submitAlterar = function() {

                if ($('listaContasDisponiveis').selectedIndex < 0){
                     alert('Por favor, selecione uma empresa.');
                } else if (($('listaConsultoresRelacionamento1').selectedIndex < 0 || $('listaConsultoresRelacionamento1').value == '') && ($('listaConsultoresRelacionamento2').selectedIndex < 0 || $('listaConsultoresRelacionamento2').value == '')) {
                    alert('Por favor selecionar um consultor.');
                } else if (!document.forms[0].nivelConsultorAlterar[0].checked && !document.forms[0].nivelConsultorAlterar[1].checked) {
                    alert('Por favor selecionar o nível de relacionamento.');
                } else if (validarAlteracaoContultor()) {
                    if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                    $('consultorRelacionamentoForm').tpOperacao.value = "alterar";
                    $('consultorRelacionamentoForm').action = "setGestorRelacionamento.do";
                    $('consultorRelacionamentoForm').target = "ifraSubmit";
                    $('consultorRelacionamentoForm').submit();
                }
            }

        submitGravar = function() {

            if ($('idConsultor').selectedIndex == 0){
                alert('Por favor, selecione um consultor.');
            } else if ($('nrDocumentoSelecionado').value == "") {
                alert('Ao menos uma conta deve ser selecionada.')
            } else if (!document.forms[0].nivelConsultorGravar[0].checked && !document.forms[0].nivelConsultorGravar[1].checked) {
                 alert('Por favor selecionar o nível de relacionamento.');
            } else if (validarGravarConsultorEscolhido()){
                if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                $('consultorRelacionamentoForm').tpOperacao.value = "gravar";
                $('consultorRelacionamentoForm').action = "gravarClienteGestorRelacionamento.do";
                $('consultorRelacionamentoForm').target = "ifraSubmit";
                $('consultorRelacionamentoForm').submit();
            }


        }

        validarGravarConsultorEscolhido = function () {

            /* Valida se o consultor já etá associado a empresa */
            if ($('idConsultor').selectedIndex >= 0 && $('idConsultor').value != '') {
                if ($('listaConsultoresRelacionamento1').options.length > 0 && $('idConsultor').value == $('listaConsultoresRelacionamento1').options[0].value) {
                    alert("O consultor selecionado já está associado ao cliente " + $('listaContasDisponiveis').options[$('listaContasDisponiveis').selectedIndex].text + " com o nível 1.");
                    return false;
                } else if ($('listaConsultoresRelacionamento2').options.length > 0 && $('idConsultor').value == $('listaConsultoresRelacionamento2').options[0].value) {
                    alert("O consultor selecionado já está associado ao cliente " + $('listaContasDisponiveis').options[$('listaContasDisponiveis').selectedIndex].text + " com o nível 2.");
                    return false;
                }
            }

            /* Valida se o nível de relacionamento já está preenchido */
            if (document.forms[0].nivelConsultorGravar[0].checked && $('listaConsultoresRelacionamento1').options.length > 0 &&  $('listaConsultoresRelacionamento1').options[0].value != '') {
                alert("Cliente já possui Consultor de Relacionamento 1");
                return false;
            } else if (document.forms[0].nivelConsultorGravar[1].checked && $('listaConsultoresRelacionamento2').options.length > 0 &&  $('listaConsultoresRelacionamento2').options[0].value != '') {
                alert("Cliente já possui Consultor de Relacionamento 2");
                return false;
            }
            return true;

        }

        validarAlteracaoContultor = function () {

            /* Verifica se alteração do nivel relacionamento de destino não é o mesmo de origem */
            if (document.forms[0].nivelConsultorAlterar[0].checked && $('listaConsultoresRelacionamento1').selectedIndex >=0) {
                alert("O consultor de relacionamento atual já é um Consultor de Relacionamento 1");
                return false;
            } else if (document.forms[0].nivelConsultorAlterar[1].checked && $('listaConsultoresRelacionamento2').selectedIndex >=0) {
                alert("O consultor de relacionamento atual já é um Consultor de Relacionamento 2");
                return false;
            }

            /* Verifica se alteração do nivel relacionamento selecionado já não está preenchido  */
            if (document.forms[0].nivelConsultorAlterar[0].checked && $('listaConsultoresRelacionamento1').options.length > 0 && $('listaConsultoresRelacionamento1').options[0].value != '') {
                alert("O cliente já possui Consultor de Relacionamento 1.");
                return false;
            } else if (document.forms[0].nivelConsultorAlterar[1].checked && $('listaConsultoresRelacionamento2').options.length > 0 && $('listaConsultoresRelacionamento2').options[0].value != '') {
                alert("O cliente já possui Consultor de Relacionamento 2.");
                return false;
            }

            /* Emite alerta de alteração do nivel relacionamento */
            if (document.forms[0].nivelConsultorAlterar[0].checked && $('listaConsultoresRelacionamento1').options.length > 0 && $('listaConsultoresRelacionamento1').options[0].value == '') {
                alert("Você esta alterando o nível de consultor de relacionamento atual, desta forma o cliente não possui mais Consultor de Relacionamento 2");
                return true;
            } else if (document.forms[0].nivelConsultorAlterar[1].checked && $('listaConsultoresRelacionamento2').options.length > 0 && $('listaConsultoresRelacionamento2').options[0].value == '') {
                alert("Você esta alterando o nível de consultor de relacionamento atual, desta forma o cliente não possui mais Consultor de Relacionamento 1");
                return true;
            }

            return true;
        }

        recarregarTela = function() {
             if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                $('consultorRelacionamentoForm').action = "loadClienteConsultor.do";
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
        .select2 {width:320px}
        .select3 {width:320px}
        .selectOne {
            width:155px;
            border:1px solid black;
        }
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

        <form name="consultorRelacionamentoForm" id="consultorRelacionamentoForm" action="getPesquisa.do">
        <html:hidden name="Form" property="tpOperacao" styleId="tpOperacao" />
        <html:hidden name="Form" property="paginaOrigem" styleId="paginaOrigem" value="cliente"/>
        <html:hidden name="Form" property="inAssociado" styleId="inAssociado" value="1"/>
        <html:hidden name="Form" property="nrDocumentoSelecionado" styleId="nrDocumentoSelecionado"/>
        <html:hidden name="Form" property="idConsultorRelacionamentoGravar" styleId="idConsultorRelacionamentoGravar" />
        <vivo:quadro width="760" height="220" id="quadroCliente" label="Cliente">
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
            <table border="0" width="100%">
                <tr>
                    <td align="center">Clientes selecionadas</td>
                    <td>&nbsp;</td>
                    <td>
                        <table border="0" width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="320" valign="top" style="padding-left:10px;" align="center">
                                Consultor de Relacionamento 1
                            </td>
                            <td align="center" valign="middle">&nbsp;</td>
                            <td width="320" valign="top" style="padding-left:10px;" align="center">
                                Consultor de Relacionamento 2
                            </td>
                         </tr>
                         </table>
                    </td>
                    <td align="center"></td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td width="320" id="container_pesquisarClienteVO" valign="top" rowspan="2">
                        <html:select name="Form" property="listaContasDisponiveis" styleId="listaContasDisponiveis" multiple="true" styleClass="select2" size="8" onmouseover="ativarToolTip(this,1)">
                            <logic:iterate id="iterContas" name="Form" property="contasSelecionadas.IDValorRelacionamentoVOArray">
                                <option value="<bean:write name="iterContas" property="id" />"><bean:write name="iterContas" property="valor" /></option>
                            </logic:iterate>
                        </html:select>
                    </td>
                    <td align="center" valign="middle">&nbsp;</td>
                    <td>
                    <div id="container_pesquisarConsultorVOPorIDCliente">
                        <table border="0" width="100%" cellpadding="0" cellspacing="0">
                        <tr>
                           <td width="320" valign="top" style="padding-left:10px;">
                                <html:select name="Form"
                                             property="listaConsultoresRelacionamento1"
                                             styleId="listaConsultoresRelacionamento1"
                                             multiple="true"
                                             styleClass="selectOne"
                                             size="1">
                                </html:select>
                            </td>
                            <td align="center" valign="middle">&nbsp;</td>
                            <td width="320" valign="top" style="padding-left:10px;">
                                <html:select name="Form"
                                             property="listaConsultoresRelacionamento2"
                                             styleId="listaConsultoresRelacionamento2"
                                             multiple="true"
                                             styleClass="selectOne"
                                             size="1">
                                </html:select>
                            </td>
                         </tr>
                         </table>
                     </div>
                    </td>
                    <td colspan="3" align="center" valign="top"><a href="javascript:excluirConsultorRelacionamento();"><img vspace="5" id="imgRightRegionais" src="<%=request.getContextPath()%>/resources/images/bt_x_nrml.gif" style="clear:both;" border="0" /></a></td>
                </tr>
                <tr>
                    <td align="center">&nbsp;</td>
                    <td align="center">
                        <table border="0">
                        <tr>
                            <td colspan="2" align="left">Nível de Relacionamento:</td>
                        </tr>
                        <tr>
                            <td><html:radio name="Form" property="nivelConsultorAlterar" styleId="clienteRelacionamento_R" value="R" styleClass="radio" style="border=none"/></td>
                            <td>Consultor de Relacionamento 1</td>
                        </tr>
                        <tr>
                            <td><html:radio name="Form" property="nivelConsultorAlterar" styleId="clienteRelacionamento_R2" value="R2" styleClass="radio" style="border=none"/></td>
                            <td>Consultor de Relacionamento 2</td>
                        </tr>
                        </table>
                    </td>
                    <td align="center" valign="middle">&nbsp;</td>
                    <td align="center" valign="middle">&nbsp;</td>
                    <td align="right" valign="bottom"><a href="javascript:submitAlterar()"><img  src="<%=request.getContextPath()%>/resources/images/bt_gravar_nrml.gif" /></a></td>
                 </tr>
            </table>
        </vivo:quadro>
        <vivo:quadro width="760" height="150" id="quadroConsultor" label="Consultor">
            <div id="filtrosConsultor" class="divFiltros">
                <table width="100%" border="0">
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
            <table border="0" width="80%">
                <tr>
                    <td width="30">&nbsp;</td>
                    <td colspan="3" width="320">Consultores disponíveis</td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td width="30">&nbsp;</td>
                    <td colspan="3" width="300" id="container_pesquisarConsultorVO" valign="top">
                        <html:select name="Form" property="idConsultor" styleId="idConsultor" styleClass="select3" >
                            <option value="">-- Realize uma pesquisa a partir dos filtros acima --</option>
                            <logic:iterate id="iterConsultores" name="Form" property="consultoresDisponiveis.IDValorRelacionamentoVOArray">
                                <option value="<bean:write name="iterConsultores" property="id" />"><bean:write name="iterConsultores" property="valor" /></option>
                            </logic:iterate>
                        </html:select>
                    </td>
                    <td align="center">&nbsp;</td>
                    <td align="center">Nível de Relacionamento</td>
                </tr>
                <tr>
                    <td width="30">&nbsp;</td>
                    <td colspan="3" width="300"  valign="top">&nbsp;</td>
                    <td align="center">&nbsp;</td>
                    <td colspan="3" align="left">
                        <table>
                        <tr>
                            <td><html:radio name="Form" property="nivelConsultorGravar" styleId="consultorRelacionamento_R" value="R" styleClass="radio" style="border=none"/></td>
                            <td>Consultor de Relacionamento 1</td>
                        </tr>
                        <tr>
                            <td><html:radio name="Form" property="nivelConsultorGravar" styleId="consultorRelacionamento_R2" value="R2" styleClass="radio" style="border=none"/></td>
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