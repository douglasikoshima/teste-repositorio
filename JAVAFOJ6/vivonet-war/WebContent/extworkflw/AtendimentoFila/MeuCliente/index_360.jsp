<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="filaMeuClienteFiltrosForm" type="extworkflw.AtendimentoFila.MeuCliente.formBeans.FilaMeuClienteFiltrosForm" />

<html>
<head>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/vivo_360.css" />
    <link rel="stylesheet" type="text/css" href="css/meuCliente360.css" />
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/calendar.css" />
    <link rel="stylesheet" type="text/css" href="css/filaMeuCliente.css" />
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/calendar.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js" ></script>
    <script type="text/javascript" language="javaScript" src="<%=request.getContextPath()%>/resources/scripts/xtooltip.js" ></script>
    <script type="text/javascript" language="javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/jquery-1.3.2.min.js"></script>
	<script type="text/javascript">
	jQuery.noConflict();
    jQuery().ready(function(){
    	jQuery('#mascara').css('height', jQuery(document).height()).hide();
        jQuery('#filtros').click(function() {
			jQuery(this).next().toggle();
			var elemHeight = jQuery(this).next().is(':visible') ? '235px' : '395px';
			jQuery('#resultado').css({
				'height' : elemHeight
			})
			return false;
		}).next();

        if (!$F('dtFechamentoInicio').blank() &&
            !$F('dtFechamentoFim').blank()) {
            $('inPeriodoFechamento').checked = true;

        } else if (!$F('dtAberturaInicio').blank() &&
            !$F('dtAberturaFim').blank()) {
            $('inPeriodoAbertura').checked = true;

        } else {
            $('inPeriodoAbertura').checked = true;
        }

        formatTelNo($('nrLinha'));
        <logic:equal name="Form" property="inVoltar" value="true">
            if ($F('nrProtocolo').strip() != '') managePermissaoEdicao($('nrProtocolo'));
            else if ($F('nrLinha').strip() != '') managePermissaoEdicao($('nrLinha'));
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
        $('mascara').hide();
        </logic:notEqual>

	});

    var THIS_PAGE = 'MEU_CLIENTE';
    var flTratamento = false;
    var pesquisa = null;
    var controleDetalhe = true;
    var listaProcessosSelecionados;

    var subestadosArray = new Array();
    <logic:iterate indexId="i" id="listaEstados" name="Form" property="listaEstadosSubestados.WFEstadoVOArray">
		<%-- subestadosArray[<%=i%>] = new Array(); --%>
	    subestadosArray[<bean:write name="i"/>] = new Array();
        <logic:iterate indexId="c" id="listaSubestados" name="listaEstados" property="WFSubEstadoVOArray">
		<%-- subestadosArray[<%=i%>][<%=c%>] = "<bean:write name="listaSubestados" property="idSubEstado" />|<bean:write name="listaSubestados" property="dsSubEstado" />"; --%>
        subestadosArray[<bean:write name="i"/>][<bean:write name="c"/>] = "<bean:write name="listaSubestados" property="idSubEstado" />|<bean:write name="listaSubestados" property="dsSubEstado" />";
        </logic:iterate>
    </logic:iterate>

    validarPesquisa = function() {
        var f = document.forms[0];
        if ($F('nrLinha').strip() != '' && $F('nrLinha').length != 13) {
            alert("Por favor, digite um número de linha válido.");
            return false;
        } else if ($('idEstado').options[$('idEstado').selectedIndex].text.toUpperCase() == 'FECHADO'
                && (f.inPeriodo[1].checked == false || !validarPeriodosAberturaFechamento(false))) {
            alert('Para pesquisa de processos fechados é necessário o preenchimento do período de fechamento.');
            return false;
        } else if (f.inPeriodo[0].checked == true || f.inPeriodo[1].checked == true) {
            return validarPeriodosAberturaFechamento(true);
        } else {
            return true;
        }
        return true;
    };

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
    };

    getAlerta = function(idAtendimento) {
        createNewPopUp('divListaAlertas', 'Alertas', 445, 245, null, 'getAlerta.do?idAtendimento=' + idAtendimento, true);
    };

    getFormParameters = function() {

        var params = new Hash();

        params.set('idRegional', $F('idRegional'));
        params.set('idEstado', $F('idEstado'));
        params.set('idGrupo', $F('idGrupo'));
        params.set('idSubestado', $F('idSubestado'));
        params.set('idUsuario', $F('idUsuario'));
        params.set('inPeriodo', ($('inPeriodoAbertura').checked) ? "ABERTURA" :
                                    (($('inPeriodoFechamento').checked) ? "FECHAMENTO" : ""));
        params.set('filtrosPesquisa.dtAberturaInicio', $F('dtAberturaInicio'));
        params.set('filtrosPesquisa.dtAberturaFim', $F('dtAberturaFim'));
        params.set('filtrosPesquisa.dtFechamentoInicio', $F('dtFechamentoInicio'));
        params.set('filtrosPesquisa.dtFechamentoFim', $F('dtFechamentoFim'));
        params.set('filtrosPesquisa.nrProtocolo', $F('nrProtocolo'));
        params.set('filtrosPesquisa.idAtendimento', $F('idAtendimento'));
        params.set('filtrosPesquisa.nrLinha', $F('nrLinha'));
        params.set('filtrosPesquisa.inFilaAbertos', $F('inFilaAbertos'));
        params.set('filtrosPesquisa.idContato', $F('idContato'));
        params.set('filtrosPesquisa.inPesquisaFullMC', $('inPesquisaFullMC').checked ? 1 : 0);

        return params;
    };

    submitForm = function(inGetTotal) {
        var f = document.forms[0];
        if (validarPesquisa()) {
            var params = getFormParameters();
            if (inGetTotal) {
                params.set('filtrosPesquisa.inTotalRegistros', 1);
            }
            if (!inGetTotal) {
                if (pesquisa) {
                    pesquisa.stop();
                }
                top.frameCTI.filtro.queryString = params.toQueryString();
                pesquisa = new Ajax.PeriodicalUpdater('resultadoPesquisa', 'pesquisarProcessos.do', {
                    method: 'post',
                    evalScripts: true,
                    frequency: 300,
                    parameters: params,
                    onSuccess: function() {
                        $('mascara').hide();
                    }, onComplete: function() {
                        $('qtdeTotalRegistros').update('');
                        $('mascara').hide();
                    }, onCreate: function() {
                        $('mascara').show();
                    }, on503: function(e) {
                        alert('aaa')
                        createErrorPopUp('erroPortabilidade', e.statusText, e.responseText, false);
                        $('mascara').hide();
                    }
                });
            } else {
                new Ajax.Request('pesquisarProcessos.do', {
                    method: 'post',
                    parameters: params,
                    onSuccess: function(transport) {
                        $('qtdeTotalRegistros').update(transport.responseText);
                        $('mascara').hide();
                    }, onCreate: function() {
                        $('mascara').show();
                    }, on503: function(e) {
                        createErrorPopUp('erroPortabilidade', e.statusText, e.responseText, false);
                        $('mascara').hide();
                    }
                });
            }
        }
    };

    getUsuariosByIdGrupo = function(idGrupo) {

        clearSelectList($('idUsuario'));
        addValueToSelectList('-- Selecione --', '', $('idUsuario'));

        if (idGrupo != '') {
            var params = $H();
            params.set('idGrupoSelecionado', idGrupo);

            new Ajax.Request('/FrontOfficeWeb/extworkflw/AtendimentoFila/MeuCliente/getUsuariosByIdGrupo.do?' + params.toQueryString(), {
                method: 'post',
                onSuccess: function(e) {
                    if (e.responseText != '') {
                        var dom = parseXml(e.responseText);
                        var jsonString = xml2json(dom, '');
                        var jsonObj = jsonString.evalJSON();

                        if (jsonObj.UsuarioVIVO && jsonObj.UsuarioVIVO.length == undefined) {
                            addValueToSelectList(jsonObj.UsuarioVIVO.nmLoginUsuario, jsonObj.UsuarioVIVO.idPessoaUsuario, $('idUsuario'));

                        } else if (jsonObj.AtendimentoInformacaoVO
                                && jsonObj.AtendimentoInformacaoVO.UsuarioVIVO
                                && jsonObj.AtendimentoInformacaoVO.UsuarioVIVO.length == undefined) {

                            addValueToSelectList(jsonObj.AtendimentoInformacaoVO.UsuarioVIVO.nmLoginUsuario, jsonObj.AtendimentoInformacaoVO.UsuarioVIVO.idPessoaUsuario, $('idUsuario'));

                        } else if (jsonObj.AtendimentoInformacaoVO.UsuarioVIVO && jsonObj.AtendimentoInformacaoVO.UsuarioVIVO.length > 0) {
                            var usuarios = jsonObj.AtendimentoInformacaoVO.UsuarioVIVO;
                            for (var i = 0; i < usuarios.length; i++) {
                                addValueToSelectList(usuarios[i].nmLoginUsuario, usuarios[i].idPessoaUsuario, $('idUsuario'));
                            }
                        }
                    }

                }, onCreate: function() {
                    $('mascara').show();
                }, onComplete: function() {
                    $('mascara').hide();
                }, onException: function(e) {
                }, on406: function(e) {
                    window.top.frameApp.createErrorPopUp('erroAlterarNumeroSMS', e.statusText, e.responseText, false);
                    $('mascara').hide();
                }
            });

        }

    };

    getQuantidadeTotalRegistros = function() {
        submitForm(true);
    };

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
        $('dsContato').readOnly = true;
        $('inPesquisaFullMC').checked = false;
    };

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
    };

    getDetalheProcesso = function(obj) {

        if (!controleDetalhe) {
            controleDetalhe = true;
            return;
        }

        if (controleDetalhe && (!$('divListaAlertas') || $('divListaAlertas').style.display == 'none')) {

            obj.id = 'linhaAtendimento';

            var params = new Hash();
            params.set('idAtendimento', $('linhaAtendimento').select('[class="classIdAtendimento"]')[0].value);
            params.set('fila', escape("/FrontOfficeWeb/extworkflw/AtendimentoFila/MeuCliente/begin.do"));
            params.set('inCRI', 0);
            params.set('inRC', 0);
            params.set('inMeuCliente', 1);

            top.frameCTI.filtro.telaOrigem = 'FILA_MEU_CLIENTE';

            $('mascara').show();
            top.frameApp.location = '/FrontOfficeWeb/extworkflw/AtendimentoDetalhe/begin.do?' + params.toQueryString();

        }
    };

    managePermissaoEdicao = function(obj) {
        var arrayElements = $('formPesquisaProcessos').getElements();
        var arrayCalendarios;
        if (!flTratamento || obj.value.length == 0) {
            for (var i = 0; i < arrayElements.length; i++) {
                if (arrayElements[i].hasClassName('camposPermissao')) {
                    if (arrayElements[i].id != obj.id && obj.value.strip() != "") {
                        if (arrayElements[i].tagName == 'INPUT') {
                            if (arrayElements[i].hasClassName('data')) {
                                if (obj.id == 'nrProtocolo') {
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
    };

    showArvoreContato = function() {
        var f = document.forms[0];
        f.action = "obterArvoreContato.do";
        dvArvore.style.display = '';
        f.target = "ifrmArvore";
        f.submit();
    };

    encaminhar = function() {

        var f = document.forms[0];

        if (checkProcessosSelecionados()) {

            $('dvEncaminhar').show();

            getProcessosSelecionados();
            selectAllOptions($('indexesProcessosSelecionados'));

            var params = getFormParameters();
            top.frameCTI.filtro.queryString = params.toQueryString();
            top.frameCTI.filtro.telaOrigem = 'FILA_MEU_CLIENTE';

            f.action = 'encaminharSuspeito.do?operacao=encaminhar';
            f.method = 'post';
            f.target = "ifrmEncaminhar";
            f.submit();

        } else {
            alert('Nenhum atendimento selecionado!');
        }
    };

    suspeito = function() {

        var f = document.forms[0];

        if (checkProcessosSelecionados()) {

            $('dvSuspeito').show();

            getProcessosSelecionados();
            selectAllOptions($('indexesProcessosSelecionados'));

            var params = getFormParameters();
            top.frameCTI.filtro.queryString = params.toQueryString();
            top.frameCTI.filtro.telaOrigem = 'FILA_MEU_CLIENTE';

            f.action = 'encaminharSuspeito.do?operacao=suspeito';
            f.method = 'post';
            f.target = "ifrmSuspeito";
            f.submit();

        } else {
            alert('Nenhum atendimento selecionado!');
        }
    };

    getProcessosSelecionados = function() {
        var myObject = {};
        var checkBoxes = $('resultadoPesquisa').select('[class="selecao_processos"]');
        clearSelectList($('indexesProcessosSelecionados'));
        checkBoxes.each(function(item) {
            if (item.checked) {
                addValueToSelectList('', item.value, $('indexesProcessosSelecionados'));
            }
        }, myObject);
    };

    checkProcessosSelecionados = function() {
        var checkBoxes = $('resultadoPesquisa').select('[class="selecao_processos"]');
        var processosSelecionados = false;
        for (var i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].checked) {
                processosSelecionados = true;
                break;
            }
        }
        return processosSelecionados;
    };

    manageBloqueiaFormulario = function(element) {

            if (element.checked) {

                $('idRegional').selectedIndex = 0;
                $('idEstado').selectedIndex = 0;
                $('idGrupo').selectedIndex = 0;
                $('idSubestado').selectedIndex = 0;
                $('idUsuario').selectedIndex = 0;
                $('nrProtocolo').value = '';
                $('nrLinha').value = '';
                $('inPeriodoAbertura').checked = true;
                $('dtAberturaInicio').value = '';
                $('dtAberturaFim').value = '';
                $('inPeriodoFechamento').checked = false;
                $('dtFechamentoInicio').value = '';
                $('dtFechamentoFim').value = '';
                $('idAtendimento').value = '';
                $('dsContato').value = '';
                $('idContato').value = '';

                $('idRegional').disabled = true;
                $('idEstado').disabled = true;
                $('idGrupo').disabled = true;
                $('idSubestado').disabled = true;
                $('idUsuario').disabled = true;
                $('nrProtocolo').disabled = true;
                $('nrLinha').disabled = true;
                $('inPeriodoAbertura').disabled = true;
                $('dtAberturaInicio').disabled = true;
                $('dtAberturaFim').disabled = true;
                $('inPeriodoFechamento').disabled = true;
                $('dtFechamentoInicio').disabled = true;
                $('dtFechamentoFim').disabled = true;
                $('idAtendimento').disabled = true;
                $('dsContato').disabled = true;
                $('idContato').disabled = true;

                $('imgLupa').setStyle({
                    'visibility' : 'hidden'
                });

            } else {

                $('idRegional').disabled = false;
                $('idEstado').disabled = false;
                $('idGrupo').disabled = false;
                $('idSubestado').disabled = false;
                $('idUsuario').disabled = false;
                $('nrProtocolo').disabled = false;
                $('nrLinha').disabled = false;
                $('inPeriodoAbertura').disabled = false;
                $('dtAberturaInicio').disabled = false;
                $('dtAberturaFim').disabled = false;
                $('inPeriodoFechamento').disabled = false;
                $('dtFechamentoInicio').disabled = false;
                $('dtFechamentoFim').disabled = false;
                $('idAtendimento').disabled = false;
                $('dsContato').disabled = false;
                $('idContato').disabled = false;

                $('imgLupa').setStyle({
                    'visibility':'visible'
                });
            }
        };

	</script>
    <style type="text/css">
    </style>
</head>
<body id="vivo_360">
    <html:form styleId="formPesquisaProcessos" action="/extworkflw/AtendimentoFila/MeuCliente/pesquisarProcessos.do">
    <html:hidden name="Form" property="filtrosPesquisa.inFilaAbertos" styleId="inFilaAbertos" />
    <html:hidden name="Form" property="filtrosPesquisa.idContato" styleId="idContato" />
    <div id="vivo306_header">
        <div id="vivo306_header_logo">
            <img src="<%=request.getContextPath()%>/resources/images/vivo360_logo.gif" alt="Vivo 360" width="119" height="47" />
        </div>
        <div id="vivo306_header_login_info">

        </div>
        <div id="vivo306_header_sair">
            <a href="<%=request.getContextPath()%>/logout.do"><img src="<%=request.getContextPath()%>/resources/images/vivo360_top_sair.gif" alt="Vivo 360" width="42" height="22" border="0" /></a>
        </div>
    </div>
    <div id="vivo306_body">
        <h1>Fila de Processos Meu Cliente</h1>

        <div id="main">

            <div class="basic" id="list1a">

                <a id="filtros" class="table_title clickable">Filtros de Pesquisa</a>
                <div class="item table_body" style="height:160px;">

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
                            <td style="padding-left:15px">
                                Estado
                            </td>
                            <td>
                                <html:select name="Form"
                                             property="idEstado"
                                             styleId="idEstado"
                                             styleClass="rightSelects camposPermissao"
                                             onchange="getListaSubestados(this)">
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
                            <td>
                                Grupo
                            </td>
                            <td>
                                <html:select name="Form"
                                             property="idGrupo"
                                             styleId="idGrupo"
                                             title="Grupo"
                                             onchange="getUsuariosByIdGrupo(this.value);"
                                             styleClass="camposPermissao">
                                    <html:option value="">-- Selecione --</html:option>
                                    <logic:iterate id="listaGrupos" name="Form" property="filtrosListas.WFGrupoVOArray" type="br.com.vivo.fo.workflow.vo.WFGrupoVODocument.WFGrupoVO">
                                    <option value="<bean:write name="listaGrupos" property="idGrupo" />"
                                        <% if (Form.getIdGrupo().equals(listaGrupos.getIdGrupo())) { %> selected="selected" <% } %>>
                                        <bean:write name="listaGrupos" property="dsGrupo" />
                                    </option>
                                    </logic:iterate>
                                </html:select>
                            </td>
                            <td style="padding-left:15px">Subestado</td>
                            <td>
                                <html:select name="Form"
                                             property="idSubestado"
                                             styleId="idSubestado"
                                             styleClass="rightSelects camposPermissao">
                                    <option value="">-- Selecione --</option>
                                </html:select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                Usuário
                            </td>
                            <td>
                                <html:select name="Form"
                                             property="idUsuario"
                                             styleId="idUsuario"
                                             styleClass="camposPermissao">
                                    <html:option value="">-- Selecione --</html:option>
                                    <logic:iterate id="listaUsuarios" name="Form" property="listaUsuarios" type="br.com.vivo.fo.usuario.vo.UsuarioVIVODocument.UsuarioVIVO">
                                    <option value="<bean:write name="listaUsuarios" property="idPessoaUsuario" />"
                                        <% if (Form.getIdGrupo().equals(listaUsuarios.getIdPessoaUsuario())) { %> selected="selected" <% } %>>
                                        <bean:write name="listaUsuarios" property="nmLoginUsuario" />
                                    </option>
                                    </logic:iterate>
                                </html:select>
                            </td>
                            <td>
                                <html:radio name="Form"
                                            property="inPeriodo"
                                            styleId="inPeriodoAbertura"
                                            value="ABERTURA"
                                            styleClass="radioCheckbox"
                                            onclick="" />
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
                            <td>Protocolo</td>
                            <td>
                                <html:text name="Form"
                                           property="filtrosPesquisa.nrProtocolo"
                                           styleId="nrProtocolo"
                                           styleClass="protocolo camposPermissao"
                                           maxlength="31"
                                           onkeyup="checaInteiro(this);managePermissaoEdicao(this)"
                                           onblur="flTratamento=false;checaInteiro(this);managePermissaoEdicao(this)" />
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                Linha <html:text name="Form"
                                                 property="filtrosPesquisa.nrLinha"
                                                 styleId="nrLinha"
                                                 styleClass="protocolo camposPermissao"
                                                 onkeyup="managePermissaoEdicao(this);formatTelNo(this)"
                                                 onblur="flTratamento=false;formatTelNo(this)"
                                                 maxlength="13" />
                            </td>
                            <td>
                                <html:radio name="Form"
                                            property="inPeriodo"
                                            styleId="inPeriodoFechamento"
                                            value="FECHAMENTO"
                                            styleClass="radioCheckbox" />
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
                            <td>Processo</td>
                            <td>
                                <html:text name="Form"
                                           property="filtrosPesquisa.idAtendimento"
                                           styleId="idAtendimento"
                                           styleClass="protocolo"
                                           maxlength="15"
                                           onkeyup="checaInteiro(this);"
                                           onblur="checaInteiro(this)" />
                                &nbsp;&nbsp;&nbsp;&nbsp;
                                <label for="inPesquisaFullMC">Meus processos</label>
                                <html:checkbox name="Form"
                                               property="filtrosPesquisa.inPesquisaFullMC"
                                               styleId="inPesquisaFullMC"
                                               style="background:none;border:none"
                                               onclick="manageBloqueiaFormulario(this)" />
                            </td>
                            <td colspan="2" style="padding-left:14px;">
                                Contato
                                <input type="text" id="dsContato" maxlength="255" style="width:300px" readonly="true" />
                                <img id="imgLupa"
                                     align="bottom"
                                     src="<%=request.getContextPath()%>/resources/images/magnifier.png"
                                     onmouseup="showArvoreContato();return false;"
                                     style="cursor:pointer;border:none;" />
                            </td>
                        </tr>
                        <tr>
                            <td colspan="4" align="right">
                                <div class="button_separator"></div>
                                <input type="button" class="input_small" value="Limpar" onmouseup="limparCampos()" />
                                <input type="button" class="input_small" value="Pesquisar" onmouseup="submitForm(false)" />
                            </td>
                        </tr>
                    </table>
                </div>

                <div class="legendas">
                    <table class="tbl_bggray"
                           width="100%"
                           cellspacing="0"
                           cellpadding="0"
                           border="0">
                        <tr>
                            <td width="104" align="center" height="24" nowrap>Atualização: <span style="color:#1865c5">5 min</span></td>
                            <td width="115" align="center">Vermelho: <span id="lblVermelho"></span></td>
                            <td width="110" align="center">Amarelo: <span id="lblAmarelo"></span></td>
                            <td width="95"  align="center">Normal: <span id="lblNormal"></span></td>
                            <td width="155" align="center">Reg. Retornados: <span id="total"></span>
                            </td>
                            <td width="141" align="right" valign="middle">Reg. Encontrados:
                                <img id="btGetQuantidadeTotal" style="display:none;cursor:pointer;" align="middle" title="Exibir quantidade de processos existentes de acordo com os filtros selecionados." src="bt_getprocessos.gif" border="0" onclick="getQuantidadeTotalRegistros()">
                            </td>
                            <td width="60" align="left"><span id="qtdeTotalRegistros" style="color:#1865c5;font-weight:bold;"></span></td>
                        </tr>
                    </table>
                </div>
                <a class="table_title unclickable">Processos Encontrados</a>
                <div id="resultado" class="item table_body" style="height:220px;">
                    <div id="resultadoPesquisa"></div>
                </div>

            </div>
        </div>

    </div>

    <vivo:quadroFlutuante id="dvArvore"     idIframe="ifrmArvore"     width="720" height="220" spacesTop="190" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="Árvore de Contato" scroll="auto" onclick="ativar_combos();"/>
    <vivo:quadroFlutuante id="dvEncaminhar" idIframe="ifrmEncaminhar" width="780" height="290" spacesTop="190" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Encaminhar" scroll="no" onclick="ifrmEncaminhar.fechar()"/>
    <vivo:quadroFlutuante id="dvSuspeito"   idIframe="ifrmSuspeito"   width="780" height="260" spacesTop="190" spacesLeft="10" display="none" url="<%=request.getContextPath()%>" label="Suspeito"   onclick="ifrmSuspeito.fechar()"/>
    <iframe scrolling="yes" style="visibility:hidden;" name="iframeUsuario" height="1px" width="1px"></iframe>
    </html:form>

    <div id="mascara"></div>

</body>
</html>