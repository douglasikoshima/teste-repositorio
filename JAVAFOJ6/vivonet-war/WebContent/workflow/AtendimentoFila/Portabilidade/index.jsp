<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filaPortabilidadeFiltrosForm" type="workflow.AtendimentoFila.Portabilidade.formBeans.FilaPortabilidadeFiltrosForm" />

<netui-template:template templatePage="/resources/jsp/CRMTemplate.jsp">
<netui-template:setAttribute name="title" value="FrontOffice - Portabilidade" />
<netui-template:setAttribute name="modulo" value="Fila de Processos de Portabilidade" />
<netui-template:section name="headerSection">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css" />
    <link rel="stylesheet" type="text/css" href="css/filaPortabilidade.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script> 
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
	<script type="text/javascript" language="javascript">

        var flTratamento = false;
        var pesquisa = null;

        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
        var subestadosArray = new Array();
        <logic:iterate indexId="i" id="listaEstados" name="Form" property="listaEstadosSubestados.WFEstadoVOArray">
        //<bean:write name="listaEstados" property="dsEstado" />
			<%-- subestadosArray[<%=i%>] = new Array(); --%>
        	subestadosArray[<bean:write name="i"/>] = new Array();
            <logic:iterate indexId="c" id="listaSubestados" name="listaEstados" property="WFSubEstadoVOArray">
			<%-- subestadosArray[<%=i%>][<%=c%>] = "<bean:write name="listaSubestados" property="idSubEstado" />|<bean:write name="listaSubestados" property="dsSubEstado" />"; --%>
            subestadosArray[<bean:write name="i"/>][<bean:write name="c"/>] = "<bean:write name="listaSubestados" property="idSubEstado" />|<bean:write name="listaSubestados" property="dsSubEstado" />";
            </logic:iterate>
        </logic:iterate>

		onload = function() {
            $('inPeriodoAbertura').checked = true;
            formatTelNo($('nrLinha'));
            <logic:equal name="Form" property="inVoltar" value="true">
                if ($F('nrProtocolo').strip() != '') managePermissaoEdicao($('nrProtocolo'));
                else if ($F('nrLinha').strip() != '') managePermissaoEdicao($('nrLinha'));
                else if ($F('nrProtocoloPortabilidade').strip() != '') managePermissaoEdicao($('nrProtocoloPortabilidade'));
                else if ($F('nrConta').strip() != '') managePermissaoEdicao($('nrConta'));
                <logic:equal name="Form" property="inPeriodo" value="ABERTURA">
                $('inPeriodoAbertura').checked = true;
                </logic:equal>
                <logic:equal name="Form" property="inPeriodo" value="FECHAMENTO">
                $('inPeriodoFechamento').checked = true;
                </logic:equal>
                <logic:notEqual name="Form" property="idEstado" value="">
                getListaSubestados($('idEstado'));
                </logic:notEqual>
                submitForm();
            </logic:equal>
            <logic:notEqual name="Form" property="inVoltar" value="true">
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            </logic:notEqual>
		}

        validarPesquisa = function() {
            var f = document.forms[0];
            /*if ($('idRegional').disabled == false && $F('idRegional') == "" && $F('nrProtocolo').strip() == "") {
                alert("É necessária a escolha de uma regional ou um número de protocolo para realizar a pesquisa.");
                return false;
            } else */
            if ($F('nrLinha').strip() != '' && $F('nrLinha').length < 13) {
                alert("Por favor, digite um número de linha válido.");
                return false;
            } else if ($('idEstado').options[$('idEstado').selectedIndex].text.toUpperCase() == 'FECHADO' && (f.inPeriodo[1].checked == false || !validarPeriodosAberturaFechamento(false))) {
                alert('Para pesquisa de processos fechados é necessário o preenchimento do período de fechamento.');
                return false;
            } else if (f.inPeriodo[0].checked == true || f.inPeriodo[1].checked == true) {
                return validarPeriodosAberturaFechamento(true);
            } else {
                return true;
            }
            return true;
        }

        validarPeriodosAberturaFechamento = function(showMessages) {
            var f = document.forms[0];
            if (f.inPeriodo[0].checked == true && 
                ($F('dtAberturaInicio').strip() == "" || $F('dtAberturaFim').strip() == "")
                && ($F('dtAberturaInicio').strip() != "" && $F('dtAberturaFim').strip() != "")) {
                if (showMessages) {
                    alert("Por favor, preencha o período de abertura.");
                }
                return false;
            } else if (f.inPeriodo[0].checked == true 
                       && ($F('dtAberturaInicio').strip() != "" && $F('dtAberturaFim').strip() != "")
                       && !validaDataFinal($F('dtAberturaInicio'), $F('dtAberturaFim'))) {
                if (showMessages) {
                    alert("Data de abertura final é menor que a data inicial.");
                }
                return false;
            } else if (f.inPeriodo[1].checked == true && ($F('dtFechamentoInicio').strip() == "" || $F('dtFechamentoFim').strip() == "")) {
                if (showMessages) {
                    alert("Por favor, preencha o período de fechamento.");
                }
                return false;
            } else if (f.inPeriodo[1].checked == true && !validaDataFinal($F('dtFechamentoInicio'), $F('dtFechamentoFim'))) {
                if (showMessages) {
                    alert("Data de fechamento final é menor que a data inicial.");
                }
                return false;
            } else {
                if (f.inPeriodo[0].checked == true) {
                    $('inFilaAbertos').value = "1";
                } else {
                    $('inFilaAbertos').value = "0";
                }
                return true;
            }
        }

        getAlerta = function(idAtendimento) {
            createNewPopUp('divListaAlertas', 'Alertas', 445, 245, null, 'getAlerta.do?idAtendimento=' + idAtendimento, true);
        }

        submitForm = function(inGetTotal) {
            var f = document.forms[0];
            if (validarPesquisa()) {
                if (!inGetTotal) {
                    if (pesquisa) {
                        pesquisa.stop();
                    }
                    pesquisa = new Ajax.PeriodicalUpdater('resultadoPesquisa', 'pesquisarProcessos.do', {
                        method: 'post',
                        evalScripts: true,
                        frequency: 300,
                        parameters: {
                            idCarteira: $F('idCarteira'),
                            idSegmento: $F('idSegmento'),
                            idEstado: $F('idEstado'),
                            idSubestado: $F('idSubestado'),
                            idTipoAlerta: $F('idTipoAlerta'),
                            idTipoLinha: $F('idTipoLinha'),
                            idRegional: $F('idRegional'),
                            idTipoPessoa: $F('idTipoPessoa'),
                            inPeriodo: ($('inPeriodoAbertura').checked) ? "ABERTURA" : 
                                (($('inPeriodoFechamento').checked) ? "FECHAMENTO" : ""),
                            'filtrosPesquisa.dtAberturaInicio': $F('dtAberturaInicio'),
                            'filtrosPesquisa.dtAberturaFim': $F('dtAberturaFim'),
                            'filtrosPesquisa.dtFechamentoInicio': $F('dtFechamentoInicio'),
                            'filtrosPesquisa.dtFechamentoFim': $F('dtFechamentoFim'),
                            'filtrosPesquisa.nrProtocolo': $F('nrProtocolo'),
                            'filtrosPesquisa.nrProtocoloPortabilidade': $F('nrProtocoloPortabilidade'),
                            'filtrosPesquisa.nrLinha': $F('nrLinha'),
                            'filtrosPesquisa.inFilaAbertos': $F('inFilaAbertos'),
                            'filtrosPesquisa.nrConta': $F('nrConta')
                        }, onComplete: function() {
                            $('qtdeTotalRegistros').update('');
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        }, onCreate: function() {
                            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                        }, on503: function(e) {
                            createErrorPopUp('erroPortabilidade', e.statusText, e.responseText, false);
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        }
                    });
                } else {
                    new Ajax.Request('pesquisarProcessos.do', {
                        method: 'post',
                        parameters: {
                            idCarteira: $F('idCarteira'),
                            idSegmento: $F('idSegmento'),
                            idEstado: $F('idEstado'),
                            idSubestado: $F('idSubestado'),
                            idTipoAlerta: $F('idTipoAlerta'),
                            idTipoLinha: $F('idTipoLinha'),
                            idRegional: $F('idRegional'),
                            inPeriodo: ($('inPeriodoAbertura').checked) ? "ABERTURA" : 
                                (($('inPeriodoFechamento').checked) ? "FECHAMENTO" : ""),
                            'filtrosPesquisa.dtAberturaInicio': $F('dtAberturaInicio'),
                            'filtrosPesquisa.dtAberturaFim': $F('dtAberturaFim'),
                            'filtrosPesquisa.dtFechamentoInicio': $F('dtFechamentoInicio'),
                            'filtrosPesquisa.dtFechamentoFim': $F('dtFechamentoFim'),
                            'filtrosPesquisa.nrProtocolo': $F('nrProtocolo'),
                            'filtrosPesquisa.nrProtocoloPortabilidade': $F('nrProtocoloPortabilidade'),
                            'filtrosPesquisa.nrLinha': $F('nrLinha'),
                            'filtrosPesquisa.nrConta': $F('nrConta'),
                            'filtrosPesquisa.inFilaAbertos': $F('inFilaAbertos'),
                            'filtrosPesquisa.inTotalRegistros': 1
                        }, onSuccess: function(transport) {
                            $('qtdeTotalRegistros').update(transport.responseText);
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        }, onCreate: function() {
                            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
                        }, on503: function(e) {
                            createErrorPopUp('erroPortabilidade', e.statusText, e.responseText, false);
                            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                        }
                    });
                }
            }
        }

        getQuantidadeTotalRegistros = function() {
            submitForm(true);
        }

        limparCampos = function() {
            var selects = $('formPesquisaProcessos').select('select');
            var inputs  = $('formPesquisaProcessos').select('input[type="text"]');
            var radios  = $('formPesquisaProcessos').select('input[type="radio"]');
            $('formPesquisaProcessos').select('input[type="radio"]')[0].checked = true;
            for (var i = 0; i < selects.length; i++) {
                selects[i].disabled = false;
                selects[i].selectedIndex = 0;
            }
            for (var i = 0; i < radios.length; i++) {
                radios[i].disabled = false;
            }
            radios[0].checked = true;
            for (i = 0; i < inputs.length; i++) {
                inputs[i].readOnly = false;
                inputs[i].value = "";
            }
            for (i = $('idSubestado').options.length-1; i>=1; i--) $('idSubestado').options[i] = null;
            var arrayCalendarios = $('formPesquisaProcessos').select('[class="calendario"]');
            for (var j = 0; j < arrayCalendarios.length; j++) {
                arrayCalendarios[j].setStyle({
                    visibility : 'visible'
                });
            }
            if (pesquisa) {
                pesquisa.stop();
            }
        }

        getListaSubestados = function(obj) {
            var idxEstado = obj.selectedIndex;
            var blSelected = false;
            var idSubestadoSelecionado = '<bean:write name="Form" property="idSubestado" />';
            for (var i = $('idSubestado').options.length-1; i>=0; i--)
                    $('idSubestado').options[i] = null;
                $('idSubestado').options[0] = new Option("-- Selecione --", "");
            if (idxEstado != 0) {
                idxEstado--;
                for (i = 0; i < subestadosArray[idxEstado].length; i++) {
                    blSelected = false;
                    if (idSubestadoSelecionado == idSubestado) {
                        blSelected = true;
                    }
                    var idSubestado = subestadosArray[idxEstado][i].split("|")[0];
                    var dsSubestado = subestadosArray[idxEstado][i].split("|")[1];
                    $('idSubestado').options[$('idSubestado').options.length] = new Option(dsSubestado, idSubestado, false, blSelected);
                }
            }
        }

        getDetalheProcesso = function(obj) {
            obj.id = 'linhaAtendimento';
            var params = new Hash();
            params.set('indexAtendimentoVO', $('linhaAtendimento').select('[class="classIdAtendimento"]')[0].value);
            $('formPesquisaProcessos').action = "getDetalheProcesso.do?" + params.toQueryString();
            if (window.top.frameApp.$('dvAnimarAguarde')) top.frameApp.startAnimation();
            $('formPesquisaProcessos').submit();
        }

        managePermissaoEdicao = function(obj) {
            var arrayElements = $('formPesquisaProcessos').getElements();
            var arrayCalendarios;
            if (!flTratamento || obj.value.length == 0) {
                for (var i = 0; i < arrayElements.length; i++) {
                    if (arrayElements[i].hasClassName('camposPermissao')) {
                        if (arrayElements[i].id != obj.id && obj.value.strip() != "") {
                            if (arrayElements[i].tagName == 'INPUT') {
                                if (arrayElements[i].hasClassName('data')) {
                                    if (obj.id == 'nrProtocoloPortabilidade' || obj.id == 'nrProtocolo') {
                                        arrayElements[i].value = '';
                                        arrayElements[i].readOnly = true;
                                        $('inPeriodoAbertura').checked = true;
                                        $('inPeriodoAbertura').disabled = true;
                                        $('inPeriodoFechamento').disabled = true;
                                        arrayCalendarios = $('formPesquisaProcessos').select('[class="calendario"]');
                                        for (var j = 0; j < arrayCalendarios.length; j++) {
                                            arrayCalendarios[j].setStyle({
                                                visibility : 'hidden'
                                            });
                                        }
                                    }
                                } else {
                                    arrayElements[i].value = '';
                                    arrayElements[i].readOnly = true;
                                }
                            } else if (arrayElements[i].tagName == 'SELECT') {
                                arrayElements[i].selectedIndex = 0;
                                arrayElements[i].disabled = true;
                            }
                        } else {
                            if (arrayElements[i].tagName == 'INPUT') {
                                arrayElements[i].readOnly = false;
                            } else if (arrayElements[i].tagName == 'SELECT') {
                                arrayElements[i].selectedIndex = 0;
                                arrayElements[i].disabled = false;
                            }
                        }
                    }
                }
                flTratamento = true;
                if (pesquisa) {
                    pesquisa.stop();
                }
            }
            if (obj.value.strip() == "") {
                arrayCalendarios = $('formPesquisaProcessos').select('[class="calendario"]');
                for (var j = 0; j < arrayCalendarios.length; j++) {
                    arrayCalendarios[j].setStyle({
                        visibility : 'visible'
                    });
                }
                $('inPeriodoAbertura').disabled = false;
                $('inPeriodoFechamento').disabled = false;
                if (pesquisa) {
                    pesquisa.stop();
                }
            }
        }

        validateFechamento = function(bl) {
            if (bl) {
                $('idTipoAlerta').disabled = true;
            } else {
                $('idTipoAlerta').disabled = false;
            }
        }

    </script>
    <style type="text/css">
        .radioCheckbox {border:none;background:none;margin:0;padding:0;}
        select {margin-left:3px}
        #tablePesquisa td {
            padding-left:5px;
        }
        #tablePesquisa input.protocolo {
            width:80px;
        }
        #tablePesquisa input.data {
            width:70px;
        }
        #resultadoPesquisa {
            margin-top:6px;
            display:block;
            width:780px;
            height:263px;
            background:#f7f9fa;
            overflow:hidden;
        }
    </style>
</netui-template:section>
<netui-template:section name="bodySection">
    <jsp:include page="/resources/menus/MenuPrincipal.jsp"/>
    <div><img src="<%=request.getContextPath()%>/resources/images/transp.gif" border="0" width="1" height="5"></div>
    <vivo:sessao id="bodyRelatoriosTracking" height="470" width="790" label="Workflow" operacoes="Fila de Processos de Portabilidade" scroll="N">
        <html:form styleId="formPesquisaProcessos" action="/workflow/AtendimentoFila/Portabilidade/pesquisarProcessos.do">
        <html:hidden name="Form" property="filtrosPesquisa.inFilaAbertos" styleId="inFilaAbertos" />
        <table id="tablePesquisa" width="100%" class="tbl_bggray" align="center">
            <tr>
                <td>
                    Regional
                </td>
                <td>
                    <html:select name="Form" property="idRegional" styleId="idRegional" styleClass="camposPermissao">
                        <option value="">-- Selecione --</option>
                        <logic:iterate id="listaRegionais" name="Form" property="filtrosListas.WFRegionalVOArray" type="br.com.vivo.fo.workflow.vo.WFRegionalVODocument.WFRegionalVO">
                        <option value="<bean:write name="listaRegionais" property="idRegional" />"
                            <% if (Form.getIdRegional().equals(listaRegionais.getIdRegional())) { %> selected="selected" <% } %>>
                            <bean:write name="listaRegionais" property="dsRegional" />
                        </option>
                        </logic:iterate>
                    </html:select>
                </td>
                <td>
                    <html:radio name="Form" property="inPeriodo" styleId="inPeriodoAbertura" value="ABERTURA" styleClass="radioCheckbox" onclick="validateFechamento(false)" />
                    Abertura de
                </td>
                <td>
                    <html:text name="Form"
                               property="filtrosPesquisa.dtAberturaInicio"
                               styleId="dtAberturaInicio"
                               size="10"
                               maxlength="10"
                               onblur="validaDataInput(this)"
                               onkeyup="Formatar(this.value, this.form.name, this.name, 'data');$('inPeriodoAbertura').checked = true"
                               styleClass="data camposPermissao" />
                    <img class="calendario" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('filtrosPesquisa.dtAberturaInicio', '%d/%m/%Y');">
                    até 
                    <html:text name="Form"
                               property="filtrosPesquisa.dtAberturaFim"
                               styleId="dtAberturaFim"
                               size="10"
                               maxlength="10"
                               onblur="validaDataInput(this)"
                               onkeyup="Formatar(this.value, this.form.name, this.name, 'data');$('inPeriodoAbertura').checked = true"
                               styleClass="data camposPermissao" />
                    <img class="calendario" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('filtrosPesquisa.dtAberturaFim', '%d/%m/%Y');">
                </td>
            </tr>
            <tr>
                <td>Protocolo Atendimento</td>
                <td>
                    <html:text name="Form"
                               property="filtrosPesquisa.nrProtocolo"
                               styleId="nrProtocolo"
                               styleClass="protocolo camposPermissao"
                               maxlength="31"
                               onkeyup="checaInteiro(this);managePermissaoEdicao(this)"
                               onblur="flTratamento=false;checaInteiro(this)" />
                    &nbsp;&nbsp;
                    Linha <html:text name="Form"
                                     property="filtrosPesquisa.nrLinha"
                                     styleId="nrLinha"
                                     styleClass="protocolo camposPermissao"
                                     onkeyup="managePermissaoEdicao(this)"
                                     onblur="flTratamento=false;formatPhoneNumberObj(this)"
                                     maxlength="11"
                                     style="width: 85px;" />
                </td>
                <td>
                    <html:radio name="Form" property="inPeriodo" styleId="inPeriodoFechamento" value="FECHAMENTO" styleClass="radioCheckbox" onclick="validateFechamento(true)" />
                    Fechamento de
                </td>
                <td>
                    <html:text name="Form"
                               property="filtrosPesquisa.dtFechamentoInicio"
                               styleId="dtFechamentoInicio"
                               size="10"
                               maxlength="10"
                               onblur="validaDataInput(this)"
                               onkeyup="Formatar(this.value, this.form.name, this.name, 'data');$('inPeriodoFechamento').checked = true"
                               styleClass="data camposPermissao" />
                    <img class="calendario" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('filtrosPesquisa.dtFechamentoInicio', '%d/%m/%Y');">
                    até 
                    <html:text name="Form" 
                               property="filtrosPesquisa.dtFechamentoFim"
                               styleId="dtFechamentoFim"
                               size="10"
                               maxlength="10"
                               onblur="validaDataInput(this);"
                               onkeyup="Formatar(this.value, this.form.name, this.name, 'data');$('inPeriodoFechamento').checked = true"
                               styleClass="data camposPermissao" />
                    <img class="calendario" src="<%=request.getContextPath()%>/resources/images/calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('filtrosPesquisa.dtFechamentoFim', '%d/%m/%Y');">
                </td>
            </tr>
            <tr>
                <td>Protocolo Portabilidade</td>
                <td>
                    <html:text name="Form"
                               property="filtrosPesquisa.nrProtocoloPortabilidade"
                               styleId="nrProtocoloPortabilidade"
                               styleClass="protocolo camposPermissao"
                               maxlength="15"
                               onkeyup="checaInteiro(this);managePermissaoEdicao(this)"
                               onblur="flTratamento=false;checaInteiro(this)" />
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    Conta <html:text name="Form"
                                     property="filtrosPesquisa.nrConta"
                                     styleId="nrConta"
                                     styleClass="protocolo camposPermissao"
                                     maxlength="100" 
                                     style="width:76px"
                                     onkeyup="checaInteiro(this);managePermissaoEdicao(this)"
                                     onblur="flTratamento=false;checaInteiro(this)" />
                </td>
                <td style="padding-left:15px">
                    Estado
                </td>
                <td>
                    <html:select name="Form" property="idEstado" styleId="idEstado" styleClass="rightSelects camposPermissao" onchange="getListaSubestados(this)">
                        <option value="">-- Selecione --</option>
                        <logic:iterate id="listaEstados" name="Form" property="listaEstadosSubestados.WFEstadoVOArray" type="br.com.vivo.fo.workflow.vo.WFEstadoVODocument.WFEstadoVO">
                        <option value="<bean:write name="listaEstados" property="idEstado" />"
                            <% if (Form.getIdEstado().equals(listaEstados.getIdEstado())) { %> selected="selected" <% } %>>
                            <bean:write name="listaEstados" property="dsEstado" />
                        </option>
                        </logic:iterate>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td>Carteira</td>
                <td>
                    <html:select name="Form" property="idCarteira" styleId="idCarteira" styleClass="camposPermissao">
                        <option value="">-- Selecione --</option>
                        <logic:iterate id="listaCarteiras" name="Form" property="filtrosListas.carterizacaoVOArray" type="br.com.vivo.fo.admsistemas.vo.CarterizacaoVODocument.CarterizacaoVO">
                        <option value="<bean:write name="listaCarteiras" property="idTipoCarteira" format="#" />"
                            <% if (Form.getIdCarteira().equals(String.valueOf(listaCarteiras.getIdTipoCarteira()))) { %> selected="selected" <% } %>>
                            <bean:write name="listaCarteiras" property="descricao" />
                        </option>
                        </logic:iterate>
                    </html:select>
                </td>
                <td style="padding-left:15px">Subestado</td>
                <td>
                    <html:select name="Form" property="idSubestado" styleId="idSubestado" styleClass="rightSelects camposPermissao">
                        <option value="">-- Selecione --</option>
                    </html:select>
                </td>
            </tr>
            <tr>
                <td>Segmento</td>
                <td>
                    <html:select name="Form" property="idSegmento" styleId="idSegmento" styleClass="camposPermissao">
                        <option value="">-- Selecione --</option>
                        <logic:iterate id="listaSegmentacoes" name="Form" property="filtrosListas.segmentacaoVOArray" type="br.com.vivo.fo.admsistemas.vo.SegmentacaoVODocument.SegmentacaoVO">
                        <option value="<bean:write name="listaSegmentacoes" property="idSegmentacao" format="#" />"
                            <% if (Form.getIdSegmento().equals(String.valueOf(listaSegmentacoes.getIdSegmentacao()))) { %> selected="selected" <% } %>>
                            <bean:write name="listaSegmentacoes" property="descricao" />
                        </option>
                        </logic:iterate>
                    </html:select>
                </td>
                <td style="padding-left:15px">Tipo de alerta</td>
                <td>
                    <html:select name="Form" property="idTipoAlerta" styleId="idTipoAlerta" styleClass="rightSelects camposPermissao">
                        <option value="">-- Selecione --</option>
                        <logic:iterate id="listaAlertas" name="Form" property="filtrosListas.alertaVOArray" type="br.com.vivo.fo.workflow.vo.AlertaVODocument.AlertaVO">
                        <option value="<bean:write name="listaAlertas" property="idAlerta" format="#" />"
                            <% if (Form.getIdTipoAlerta().equals(String.valueOf(listaAlertas.getIdAlerta()))) { %> selected="selected" <% } %>>
                            <bean:write name="listaAlertas" property="dsAlerta" />
                        </option>
                        </logic:iterate>
                    </html:select>
                </td>
            </tr>
            <tr>
                <!--td>Tipo de Pessoa</td>
                <td>
                    < html:select name="Form" property="idTipoPessoa" styleId="idTipoPessoa" styleClass="camposPermissao">
                        < option value="">-- Selecione --</option>
                        < logic : iterate id="listaTiposPessoa" name="Form" property="filtrosListas.tipoPessoaVOArray" type="br.com.vivo.fo.fidelizacao.vo.TipoPessoaVODocument.TipoPessoaVO">
                        < option value="< bean:write name="listaTiposPessoa" property="idtipopessoaArray[0]" format="#" />"
                            < % if (Form .getIdTipoPessoa().equals( String. valueOf( listaTiposPessoa . getIdtipopessoaArray (0)))) { %> selected="selected" < % } % > >
                            < bean : write name="listaTiposPessoa" property="dstipopessoaArray[0]" />
                        < /option>
                        < /logic:iterate>
                    < /html:select>
                </td-->
                <td>Tipo de Linha</td>
                <td>
                    <html:select name="Form" property="idTipoLinha" styleId="idTipoLinha" styleClass="camposPermissao">
                        <option value="">-- Selecione --</option>
                        <logic:iterate id="listaTiposLinha" name="Form" property="filtrosListas.classificacaoTipoLinhaVOArray" type="br.com.vivo.fo.cliente.vo.ClassificacaoTipoLinhaVODocument.ClassificacaoTipoLinhaVO">
                        <option value="<bean:write name="listaTiposLinha" property="idClassificacaoTipoLinhaVO" format="#" />"
                            <% if (Form.getIdTipoLinha().equals(listaTiposLinha.getIdClassificacaoTipoLinhaVO())) { %> selected="selected" <% } %>>
                            <bean:write name="listaTiposLinha" property="dsClassificacaoTipoLinhaVO" />
                        </option>
                        </logic:iterate>
                    </html:select>
                </td>
                <td colspan="2" align="right">
                    <input type="hidden" id="idTipoPessoa" value="" />
                    <a href="javascript:void(0)" onmouseup="limparCampos()"><img src="<%=request.getContextPath()%>/resources/images/bt_limpar_nrml.gif" border="0" /></a>
                    <a href="javascript:void(0)" onmouseup="submitForm()"><img src="<%=request.getContextPath()%>/resources/images/bt_pesquisar_nrml.gif" border="0" style="margin-right:4px" /></a>
                </td>
            </tr>
        </table>

        <table class="tbl_bggray" width="780" cellspacing="0" cellpadding="0" border="0" height="24" style="margin-top:6px">
            <tr>
                <td width="104" align="center" height="24">Atualização: <span style="color:#1865c5">5 min</span></td>
                <td width="115" align="center">Vermelho: <span id="lblVermelho"></span></td>
                <td width="115" align="center">Laranja: <span id="lblLaranja"></span></td>
                <td width="110" align="center">Amarelo: <span id="lblAmarelo"></span></td>
                <td width="95"  align="center">Normal: <span id="lblNormal"></span></td>
                <td width="155" align="center">Reg. Retornados: <span id="total"></span>
                </td>
                <td width="141" align="right" valign="middle">Reg. Encontrados:
                    <img id="btGetQuantidadeTotal" style="display:none;cursor:pointer;" align="middle" title="Exibir quantidade de processos existentes de acordo com os filtros selecionados." src="/FrontOfficeWeb/resources/images/bt_getprocessos.gif" border="0" onclick="getQuantidadeTotalRegistros()">
                </td>
                <td width="60" align="left">&nbsp;<span id="qtdeTotalRegistros" style="color:#1865c5;font-weight:bold;"></span></td>
            </tr>
        </table>

        <div id="resultadoPesquisa"></div>

        <img style="margin:5px 0 0 0;border:none;cursor:pointer" src="<%=request.getContextPath()%>/resources/images/bt_voltar_nrml.gif" onmouseup="window.top.location.href='/FrontOfficeWeb/index.jsp'" />

        </html:form>
    </vivo:sessao>
    <vivo:businessLog requestAttribute="debugMessage" inResetWindow="true" inAjaxCall="false" />
</netui-template:section>
</netui-template:template>