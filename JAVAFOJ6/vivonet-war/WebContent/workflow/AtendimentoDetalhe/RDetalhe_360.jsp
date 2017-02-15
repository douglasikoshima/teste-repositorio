<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<!DOCTYPE html>

<%@ page import="br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>

<bean:define id="form" scope="request" name="rWFDetalheForm"/>
<bean:define id="atdVO" name="form" property="atendimentoVO"/>
<bean:define id="idAtd" name="atdVO" property="idAtendimento"/>
<bean:define id="inCRI" name="form" property="inCRI"/>
<bean:define id="inRC" name="form" property="inRC"/>
<bean:define id="fl" name="form" property="fila"/>
<bean:define id="inResp" name="atdVO" property="inResponsavelAbertura"/>

<html>
<head>
    <style>
        a:link {
            color: #000000;
            text-decoration: none;
        }
        a:visited {
            color: #FFFFFF;
            text-decoration: none;
        }
        a:hover {
            color: #FFFFFF;
        }
    </style>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/RWFDetalle.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/RWFSolic.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/xml2json.js"></script>
    <script language="javascript">
        <!--
            var opSel=false;
            var at=true;
            var sol=true;
            var niv=true;
            var ae=true;
            var abaValue=0;
            var limparObjetos="";
            var idAtd='<%=idAtd%>';
            var inCRI='<%=inCRI%>';
            var inRC='<%=inRC%>';
            var fl='<%=fl%>';
            var up = true;
            var inGestorGerente;
            var arrayComentarios = new Array();
            var arrayIdsAgrupamentos = new Array();

            function submitPesquisar(){
                $('mascara').show();
                var inMenu = 'N';
                <%if(!"N".equals(request.getParameter("inMenu"))){%>
                inMenu = 'S';
                <%}%>
                document.forms["0"].method = 'POST';
                document.forms["0"].action = 'begin.do?idAtendimento=<%=idAtd%>&fila='+fl+'&inMenu='+inMenu;
                document.forms["0"].target = '';
                document.forms["0"].submit();
            }

            function resizeFrameDetalhe(){
                var divDocs;
                if(up){
                    //div's superiores
                    document.getElementById('idDiv1').style.display="none";
                    document.getElementById('idDiv2').style.display="none";
                    //parte referente a operações
                    document.getElementById('tableTitle_div').style.height="278";
                    document.getElementById('qdMainAbas_div').style.height="422";
                    document.getElementById('tableAcoes').style.height="365";
                    //frame de retorno das operações
                    document.getElementById('dvRetornoOperacao').style.height="440";
                    document.getElementById('dvRetornoOperacao').style.top="5";
                    //seta tamenho de frame ifrmAbas, quando a página encerrarBKO estiver carregada
                    if(document.getElementById('ifrmAbas')!=null){
                        document.getElementById('ifrmAbas').style.height="352";
                        if(ifrmAbas.document.getElementById('dvMain')!=null){
                            ifrmAbas.document.getElementById('tbMain').height="352";
                            ifrmAbas.document.getElementById('dvMain').style.height="345";
                        }
                        if(ifrmAbas.document.getElementById('ifrmDocumentoAssociadoPesquisarTodos')!=null){
                            ifrmAbas.alteraSize();
                        }
                        if(ifrmAbas.document.getElementById('divTestes')!=null){
                            ifrmAbas.alteraSize();
                        }
                    }
                    //imagem da lupa
                    up = false;
                    document.getElementById('qdMain_tdSessao').innerHTML='<img name=\"lupaaba\" align=\"left\" title=\"Click para minimizar as operações do workflow!\" src=\"../../resources/images/bt_lupa_aba_down.gif\" onclick=\"resizeFrameDetalhe();\" style=\"cursor:pointer\">';
                }else{
                    //div's superiores
                    document.getElementById('idDiv1').style.display="";
                    document.getElementById('idDiv2').style.display="";
                    //parte referente a operações
                    document.getElementById('tableTitle_div').style.height="80";
                    document.getElementById('qdMainAbas_div').style.height="222";
                    document.getElementById('tableAcoes').style.height="167";
                    //frame de retorno das operações
                    document.getElementById('dvRetornoOperacao').style.height="245";
                    document.getElementById('dvRetornoOperacao').style.top="200";
                    //seta tamenho de frame ifrmAbas, quando a página encerrarBKO estiver carregada
                    if(document.getElementById('ifrmAbas')!=null){
                        document.getElementById('ifrmAbas').style.height="153";
                        if(ifrmAbas.document.getElementById('dvMain')!=null){
                            ifrmAbas.document.getElementById('tbMain').height="153";
                            ifrmAbas.document.getElementById('dvMain').style.height="147";
                        }
                        if(ifrmAbas.document.getElementById('ifrmDocumentoAssociadoPesquisarTodos')!=null){
                            ifrmAbas.alteraSizeMin();
                        }
                        if(ifrmAbas.document.getElementById('divTestes')!=null){
                            ifrmAbas.alteraSizeMin();
                        }
                    }
                    //imagem da lupa
                    up = true;
                    document.getElementById('qdMain_tdSessao').innerHTML='<img name=\"lupaaba\" align=\"left\" title=\"Click para maximizar as operações do workflow!\" src=\"../../resources/images/bt_lupa_aba.gif\" onclick=\"resizeFrameDetalhe();\" style=\"cursor:pointer\">';
                }
            }

            // Array p/ controle de ativa/desativa elementos dos forms
            frmsState = null;
            //desativa todos os combos
            function desativar_combos() {
                if (frmsState == null) {
                    forms = document.forms;
                    frmsState = new Array(forms.length);
                    for (i=0;i<forms.length;i++) {
                        el = forms[i].elements;
                        elState = new Array(el.length);
                        frmsState[i] = elState;
                        for(j=0;j<el.length;j++){
                            elState[j]=el[j].disabled;
                            el[j].disabled=true;
                        }
                    }
                }
                return;
            }

            //ativa todos o s combos
            function ativar_combos() {
                if (frmsState != null) {
                    forms = document.forms;
                    for (i=0;i<forms.length;i++) {
                        el = forms[i].elements;
                        elState = frmsState[i];
                        frmsState[i] = elState;
                        for(j=0;j<el.length;j++){
                            el[j].disabled=elState[j];
                        }
                    }
                }
                frmsState = null;
                return;
            }

            function showIfr(a) {
                d=eval("d"+a);f=eval("ifr"+a);
                cod=f.document.body.innerHTML;
                exp1=/src\=\"RDUser\.do/gi
                exp2=/src\=\"RDProcesso\.do/gi
                exp3=/src\=\"RDContato\.do/gi
                cod=cod.replace(exp1,"s rc=\"RDUser.do");
                cod=cod.replace(exp2,"s rc=\"RDProcesso.do");
                cod=cod.replace(exp3,"s rc=\"RDContato.do");
                d.innerHTML=cod;
                d.style.display='';
                eval(zso);
                //if (a != 'NProc')
                    //DoResizeHeaderTableVivo();
            }

            function showDiv(a,h) { document.getElementById("d"+a).innerHTML=h; }

            function showIfrInf(a) {
                d=eval("d"+a);
                f=eval("ifr"+a);
                dv=eval("dv"+a);
                d.innerHTML=f.document.body.innerHTML;
                dv.style.display='';
                eval(zso);
                DoResizeHeaderTableVivo();
            }

            abaOper = function() {
                if ($('idOperacao').selectedIndex == 0) {
                    alert("Por favor, selecione uma operação.");
                }
            }

            abaHist = function() {
                $('idOperacao').selectedIndex = 0;
                $('nmOperacao').innerText = 'SELECIONAR OPERAÇÃO';
                $('container_historico_operacoes').update('');
                submitPesquisarHist();
            }

            abaDetalhes = function() {

            }

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
                            $('mascara').hide();
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

            getOperacao = function(o) {

                var fila = '/FrontOfficeWeb/workflow/AtendimentoFila/MeuCliente/begin.do';
                var dsOperacao = getSelectedItemText(o).toUpperCase();
                var action = '';

                if (o.selectedIndex == 0) {
                    $('nmOperacao').innerText = 'SELECIONAR OPERAÇÃO';
                    $('container_historico_operacoes').update('');
                    submitPesquisarHist();
                } else {
                    $('nmOperacao').innerText = dsOperacao;
                }

                var params = $H();
                params.set('fila', fila);
                params.set('inCRI', 0);
                params.set('idAtendimento', '<%=idAtd%>');
                params.set('idAtividade', o.value);

                if (dsOperacao == 'CONCLUIR ANALISE') {
                    params.set('idTabelaMotivo', 3);
                    action = 'concluirBegin';
                } else if (dsOperacao == 'TRATAR E DEVOLVER PARA RETORNO') {
                    params.set('idTabelaMotivo', 2);
                    action = 'devolverBegin';
                } else if (dsOperacao == 'ENCERRAR BKO') {
                    params.set('docTec', 0);
                    params.set('idTabelaMotivo', 2);
                    action = 'encerramentoBegin';
                } else if (dsOperacao == 'INSERIR COMENTARIO') {
                    params.set('idTabelaMotivo', 1);
                    action = 'inserirComentario';
                } else if (dsOperacao == 'FINALIZAR ANALISE') {
                    params.set('idTabelaMotivo', 1);
                    action = 'finalizarBegin';
                } else if (dsOperacao == 'DOCUMENTACAO TECNICA') {
                    action = 'documentoAssociadoBegin';
                } else if (dsOperacao == 'CANCELAR') {
                    action = 'cancelarBegin';
                } else if (dsOperacao == 'FECHAR') {
                    params.set('idTabelaMotivo', 4);
                    action = 'fechamentoBegin';
                } else if (dsOperacao == 'INSERIR INSISTENCIA') {
                    params.set('qtInsistencia', $F('qtInsistencia'));
                    action = 'insistenciaBegin';
                }

                if (o.selectedIndex > 0) {

                    var operacao = new Ajax.Updater('container_historico_operacoes',
                            '/FrontOfficeWeb/workflow/AtendimentoWorkflow/' + action + '.do?' + params.toQueryString(), {
                        method: 'post',
                        evalScripts: true,
                        parameters: params,
                        onComplete: function() {
                            $('mascara').hide();
                        }, onCreate: function() {
                            $('mascara').show();
                        }, on503: function(e) {
                            $('mascara').hide();
                        }, onException: function(e) {
                            $('mascara').hide();
                        }
                    });
                }
            }

            onload = function() {
                submitPesquisarHist();
            }

            function submitPesquisarHist() {

                var params = $H();
                params.set('idAtd', '<%=idAtd%>');
                params.set('inCRI', '<%=inCRI%>');
                params.set('inRC', '<%=inRC%>');

                var pesquisa = new Ajax.Updater('container_historico_operacoes', 'RDHistorico.do?' + params.toQueryString(), {
                    method: 'post',
                    evalScripts: true,
                    parameters: params,
                    onComplete: function() {
                        $('mascara').hide();
                    }, onComplete: function() {
                        $('mascara').hide();
                    }, onCreate: function() {
                        $('mascara').show();
                    }, on503: function(e) {
                        $('mascara').hide();
                    }, onException: function(e) {
                        $('mascara').hide();
                    }
                });
            }

            // Estados e Subestados
            <bean:define id="estadosVO" name="form" property="estadoVO.WFEstadoVOArray"/>

            var arrCombos = new Array();
            <logic:iterate id="estadosVO" name="form" property="estadoVO.WFEstadoVOArray" indexId="idxEstados">
            var combo = document.createElement("<SELECT NAME='subEstadoSel'>");
            combo.idEstado = '<bean:write name="estadosVO" property="idEstado"/>';
            <logic:iterate id="subEstadosVO" name="estadosVO" property="WFSubEstadoVOArray" indexId="idxSubEstados">
                var opt = document.createElement("OPTION");
                opt.value = '<bean:write name="subEstadosVO" property="idSubEstado"/>';
                opt.text='<bean:write name="subEstadosVO" property="dsSubEstado"/>';
                combo.add(opt);
            </logic:iterate>
            arrCombos[arrCombos.length] = combo;
            </logic:iterate>

            loadSubEstados = function(id) {
                if (id==-1) {
                    tdSubEstados.innerHTML = "<select name='subEstadoSel' style='width:250px'></select>";
                    return;
                }
                for (var i=0; i<arrCombos.length; i++){
                    if (arrCombos[i].idEstado == id) {
                        tdSubEstados.innerHTML=arrCombos[i].outerHTML;
                        tdSubEstados.children[0].name='subEstadoSel';
                        tdSubEstados.children[0].style.width='250px';
                        break;
                    }
                }
            }

            submitAplicar = function(action) {

                if ($('motivoSel') && $('motivoSel').selectedIndex <= 0) {
                    alert("Por favor, selecione um motivo.");
                    return;
                }

                if (!action) {
                    action = 'listaVoltarGravar';
                }

                var params = $H();
                params.set('comentario', $F('comentario'));

                if (action == 'inserirComentarioGravar') {
                    params.set('atendimentoWorkflowVO.atendimentoWorkflowComumVO.dsObservacao', $F('comentario'));
                } else if (action == 'cancelarGravar') {

                    alert(arrayIdsAgrupamentos);
                    alert(arrayIdsAgrupamentos.length);

                    for (var i = 0; i < arrayIdsAgrupamentos.length; i++) {
                        if (arrayIdsAgrupamentos[i].split(',', 1) == $('motivoSel').value) {
                            $('idAgrupamentoEstadoTpfuturo').value = arrayIdsAgrupamentos[i].substring(arrayIdsAgrupamentos[i].indexOf(",")+1);
                        }
                    }
                    params.set('idAgrupamentoEstadoTpfuturo', $F('idAgrupamentoEstadoTpfuturo'));
                }

                if ($('motivoSel')) {
                    params.set('motivoSel', $F('motivoSel'));
                }
                params.set('idAtividade', $('idOperacao').value);
                params.set('idAtendimento', '<%=idAtd%>');
                params.set('fila', '<%=fl%>');

                var pesquisa = new Ajax.Updater('container_historico_operacoes',
                        '/FrontOfficeWeb/workflow/AtendimentoWorkflow/' + action + '.do', {
                    method: 'post',
                    evalScripts: true,
                    parameters: params,
                    onSuccess: function() {
                        $('mascara').hide();
                    }, onComplete: function() {
                        $('mascara').hide();
                    }, onCreate: function() {
                        $('mascara').show();
                    }, on503: function(e) {
                        $('mascara').hide();
                    }, onException: function(e) {
                        $('mascara').hide();
                    }
                });

            }

        -->
    </script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/resources/css/vivo_360.css" />
</head>
<body id="vivo_360">

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

<div id="vivo306_body" style="">
    <h1>Detalhes do Processo Meu Cliente</h1>

    <div class="vivo360_body" style="height:485px;">

        <ul class="tab_list">
            <li><a href="javascript:abaDetalhes()"><span>Detalhes</span></a></li>
            <!--li><a href="../AtendimentoWorkflow/abaDetalhe.do?idAtd=<%=idAtd%>&acao=RDArvoreAtendimento.do" target="ifrAtend"><span>Atendimento</span></a></li>
            <li><a href="../AtendimentoWorkflow/abaDetalhe.do?idAtd=<%=idAtd%>'+'&inResp=<%=inResp%>&acao=RDSolicitante.do" target="ifrSolic"><span>Solicitante</span></a></li>
            <li><a href="../AtendimentoWorkflow/abaDetalhe.do?idAtd=<%=idAtd%>&acao=RDNivelProcesso.do" target="ifrNProc"><span>Nível do Processo</span></a></li>
            <li><a href="../AtendimentoWorkflow/abaDetalhe.do?idAtd=<%=idAtd%>&acao=RDLinhasAssoc.do" target="ifrLAssoc"><span>Linhas Associadas</span></a></li-->
        </ul>
        <div class="tab_content_body" style="height:120px;margin-bottom:5px;">

            <table width="98%">
                <tr valign="top">
                    <td width="">Alerta:</td>
                    <td width="">
                        <iframe id="ifrAlertas" src="RDAlerta.do?idAtd=<%=idAtd%>" height="0" width="0" frameborder="0"></iframe>
                        <div id="dAlertas" style="display:none"><a href="RDAlerta.do"></a></div>
                    </td>
                    <td>Estado:</td>
                                        <td><html:text name="atdVO" property="dsEstado" readonly="true" style="width:160px;"/></td>
                </tr>
                <tr valign="top">
                    <td>Fechamento Previsto:</td>
                                        <td><html:text name="atdVO" property="dtParaFechamento" readonly="true" style="width:110px;"/></td>
                    <td>Subestado:</td>
                                        <td><html:text name="atdVO" property="dsSubEstado" readonly="true" style="width:160px;"/></td>
                </tr>
                <tr valign="top">
                    <td>Processo:</td>
                                        <td><html:text name="atdVO" property="nrProtocolo" readonly="true" style="width:160px;"/></td>
                    <td>Data de Abertura:</td>
                                        <td><html:text name="atdVO" property="dtAbertura" readonly="true" style="width:110px;"/></td>
                </tr>
                <tr valign="top">
                    <logic:notEmpty name="atdVO" property="idAtendimentoOrigem">
                    <%if(request.getParameter("qtd")==null || "".equals(request.getParameter("qtd")) ){%>
                    <td>Link de Origem:</td>
                    <td onclick="exibeLinkOrigem('<bean:write name="atdVO" property="idAtendimentoOrigem"/>');">Clique aqui</td>
			        <%}else{%>
			        <td colspan="2"></td>
			        <%}%>
                    </logic:notEmpty>
                    <logic:empty name="atdVO" property="idAtendimentoOrigem">
                    <td colspan="2"></td>
                    </logic:empty>
                    <td>Data de Fechamento:</td>
                    <td>
                        <html:text name="atdVO" property="dtFechamento" readonly="true" style="width:110px;"/>
                        <html:hidden name="atdVO" property="qtInsistencia" styleId="qtInsistencia" />
                    </td>
                </tr>
            </table>

        </div>

        <div class="legendas" style="padding-top:6px;">
            <label for="idOperacao">Operações:</label>
            <select id="idOperacao" onchange="getOperacao(this)">
                <option value="">-- Selecione --</option>
                <logic:iterate name="form" id="acaoVO" property="acaoVO" type="br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO">
                    <% if (acaoVO.getDsAtividade().equalsIgnoreCase("CANCELAR")
                            || acaoVO.getDsAtividade().equalsIgnoreCase("TRATAR E DEVOLVER PARA RETORNO")
                            || acaoVO.getDsAtividade().equalsIgnoreCase("CONCLUIR ANALISE")
                            || acaoVO.getDsAtividade().equalsIgnoreCase("FECHAR")
                            || acaoVO.getDsAtividade().equalsIgnoreCase("FINALIZAR ANALISE")
                            || acaoVO.getDsAtividade().equalsIgnoreCase("INSERIR COMENTARIO")
                            || acaoVO.getDsAtividade().equalsIgnoreCase("INSERIR INSISTENCIA")) { %>
                        <option value="<bean:write name="acaoVO" property="idAtividade"/>">
                            <bean:write name="acaoVO" property="dsAtividade"/>
                        </option>
                    <% } %>
                </logic:iterate>
            </select>
        </div>

        <ul class="tab_list">
            <li><a href="javascript:abaHist()"><span>Histórico</span></a></li>
            <li><a href="javascript:abaOper()"><span id="nmOperacao">Selecionar Operação</span></a></li>
        </ul>
        <div class="tab_content_body" style="height:230px;margin-bottom:5px;">

            <form name="h">
                <table width="780"
                       cellpadding="0"
                       cellspacing="0"
                       id="table_hist">
                    <tr>
                        <td colspan="5" valign="top">
                            <div id="container_historico_operacoes" style="width:780px;height:210px;"></div>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <input type="button" class="input_small" value="Voltar" onmouseup="voltar()" />
    </div>
</div>

<vivo:quadroFlutuante id="dvConPesq" idIframe="ifrmConPesq" width="300" height="100" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" onclick="voltar();"/>
<vivo:quadroFlutuante id="qdDocTec" idIframe="ifrmDocTec" label="Documentos Técnicos" spacesLeft="10" spacesTop="70" url="<%=request.getContextPath()%>" display="none" height="200" width="780"/>
<vivo:quadroFlutuante id="dvAtendimento2" idIframe="iframeAtendimento" width="800" height="600" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Atendimento"/>
<vivo:quadroFlutuante id="dvComentAcao" idIframe="ifrmComentAcao" width="300" height="150" spacesTop="225" spacesLeft="250" scroll="auto" display="none" url="<%=request.getContextPath()%>"/>
<vivo:quadroFlutuante id="dvPesqForm" idIframe="ifrmPesqForm" width="270" height="200" spacesTop="185" spacesLeft="250" display="none" url="<%=request.getContextPath()%>" src="" onclick="ifrmAbas.ativar_combos();"/>

<logic:notPresent parameter="qtd">
    <logic:iterate id="qtdPai" indexId="qtd" name="form" property="qtdProcessosPai">
        <vivo:quadroFlutuante id='<%="dvDetalheOrigem"+qtd%>' idIframe='<%="ifrmDetalheOrigem"+qtd%>' width="800" height="575" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Detalhe Origem"/>
    </logic:iterate>
</logic:notPresent>

<script type="text/javascript">
<!--
    <logic:present parameter="fila">

        voltar = function() {
            extra_vars = '';
            filtro = top.frameCTI.filtro;

            if (filtro.telaOrigem == 1) {
                if (filtro.psqAv != null) {
                    for (i = 0; i < filtro.psqAv.length; i++) {
                        extra_vars+='&psqIdCampo='+filtro.psqAv[i].idCampo;
                        extra_vars+='&psqNmCampo='+filtro.psqAv[i].nmCampo;
                        extra_vars+='&psqTpComparacao='+filtro.psqAv[i].tpComparacao;
                        extra_vars+='&psqDsComparacao='+filtro.psqAv[i].dsComparacao;
                        extra_vars+='&psqValor='+filtro.psqAv[i].valor;
                        extra_vars+='&psqIdFormularioCampoValor='+filtro.psqAv[i].idFormularioCampoValor;
                    }
                }
            } else if (filtro.telaOrigem == 3) {
                extra_vars =
                "&login="+filtro.massa.login+
                "&dtSuspeitoInicio="+filtro.massa.dtSuspeitoInicio+
                "&dtSuspeitoFim="+filtro.massa.dtSuspeitoFim+
                "&textoContato="+filtro.massa.textoContato+
                "&idContato="+filtro.massa.idContato;

            } else if (filtro.telaOrigem == 'FILA_MEU_CLIENTE') {
                extra_vars = '&' + filtro.queryString;
            }
            extra_vars += '&ACESSO_EXTERNO=true';
            eval(zsa);
            window.location.href = fl + '?voltar=1' + extra_vars;
        }
    </logic:present>

    function atendimentoProcesso() {
        var nrLinha="";
        var pesquisa="";
        var tipoAtendimento="";
        var inResponsavelAbertura='<bean:write name="atdVO" property="inResponsavelAbertura"/>';
        <logic:equal name="atdVO" property="inResponsavelAbertura" value="2">
            <logic:equal name="atdVO" property="linhaPesquisa" value="">
                pesquisa='conta';
                nrLinha='<bean:write name="atdVO" property="contaPesquisa"/>';
            </logic:equal>
            <logic:notEqual name="atdVO" property="linhaPesquisa" value="">
                nrLinha='<bean:write name="atdVO" property="linhaPesquisa"/>';
            </logic:notEqual>
        </logic:equal>
        <logic:equal name="atdVO" property="inResponsavelAbertura" value="1">
            <logic:equal name="atdVO" property="linhaPesquisa" value="">
                pesquisa='conta';
                nrLinha='<bean:write name="atdVO" property="contaPesquisa"/>';
            </logic:equal>
            <logic:notEqual name="atdVO" property="linhaPesquisa" value="">
                nrLinha='<bean:write name="atdVO" property="linhaPesquisa"/>';
            </logic:notEqual>
        </logic:equal>

        if( nrLinha == "" ){
            pesquisa = 'conta';
            tipoAtendimento = 'prospect';
            nrLinha='<bean:write name="atdVO" property="idPessoa"/>';
        }
        top.frameApp.location.href="telaInicial.do?tipoAtendimento="+tipoAtendimento+"&nrLinha="+nrLinha+"&pesquisa="+pesquisa+"&relacionamento="+inResponsavelAbertura+"&framesMostrados=dados&fila="+fl+"&idAtendimento=<%=idAtd%>";
    }

    <logic:notEmpty name="atdVO" property="idAtendimentoOrigem">
    function exibeLinkOrigem(a){
        var qtd = '<%=request.getParameter("qtd")!=null?request.getParameter("qtd"):"-1"%>';
        if(qtd!='null'){
            qtd++;
        }else{
            qtd=0;
        }
        fdo = "ifrmDetalheOrigem"+qtd;
        ddo = "dvDetalheOrigem"+qtd;
        if($(fdo)){
            eval(zsa);
            $(fdo).src="begin.do?idAtendimento="+a+"&inMenu=N&fila="+fl+"&qtd="+qtd;
            $(ddo).style.display='';
        }/* else{
            top.frameApp.ifrmAtendimento.document.getElementById(fdo).src="begin.do?idAtendimento="+a+"&inMenu=N&fila="+fl+"&qtd="+qtd;
            top.frameApp.ifrmAtendimento.document.getElementById(ddo).style.display='';
        } */
    }
    </logic:notEmpty>

-->
</script>
<div id="mascara"></div>
</body>
</html>