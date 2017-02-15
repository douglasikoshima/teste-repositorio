<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.workflow.vo.RWFAtendimentoVODocument.RWFAtendimentoVO"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<bean:define id="form" scope="request" name="rWFDetalheForm"/>
<bean:define id="atdVO" name="form" property="atendimentoVO"/>
<bean:define id="idAtd" name="atdVO" property="idAtendimento"/>
<bean:define id="inCRI" name="form" property="inCRI"/>
<bean:define id="inRC" name="form" property="inRC"/>
<bean:define id="inMeuCliente" name="form" property="meuCliente"/>
<bean:define id="fl" name="form" property="fila"/>
<bean:define id="inResp" name="atdVO" property="inResponsavelAbertura"/>
<acesso:controlInitEnv/>
<%
String templatePagina = "./../../resources/jsp/CRMTemplateDet.jsp";
    if ( !"N".equals(request.getParameter("inMenu")) ) {
        templatePagina = "./../../resources/jsp/CRMTemplate.jsp";
}%>
<netui-template:template templatePage="<%=templatePagina%>">
<netui-template:setAttribute name="title" value="Workflow"/>
<netui-template:setAttribute name="modulo" value="Detalhe do Processo Workflow"/>
<netui-template:section name="headerSection">
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
            var inMeuCliente = <%=inMeuCliente%>;

            var fl='<%=fl%>';
            var up = true;
            var inGestorGerente;

            function submitPesquisar(){
                //Liga animação
                if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
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

            function abaOper() {
                if (opSel) {
                    abaSelected(btAba, bt05);
                    dAH.style.display='none';
                    dAC.style.display='';
                    eval(zsa)
                    window.frames["ifrOper"].abaDinamica();
                    DoResizeHeaderTableVivo();
                } else {
                    alert("Selecione uma Operação!");
                }
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

            /*cancelar = function(){
                $('incluirAlterarGestorGerente').remove();
            }*/

        -->
    </script>
</netui-template:section>
<netui-template:section name="bodySection">
<logic:notEqual parameter="inMenu" value="N"><jsp:include page="/resources/menus/MenuPrincipal.jsp"/></logic:notEqual>
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
<vivo:sessao id="qdMain" height="473" width="790" label="Vivo Net >> Workflow" operacoes="Detalhes do Processo" scroll="no">
<form>
<div id="idDiv1"><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
<div id="idDiv2">
<table border='0' cellpadding='0' cellspacing='0' width='778' height='10' background='/FrontOfficeWeb/resources/images/aba_bkg_off.gif' class='abatexto'>
<tr id='btAbaInfo' valign='top'>
    <td width='9'><img id='AbaLeft_btInfo01' src='/FrontOfficeWeb/resources/images/aba_left.gif' width='9' height='16'></td>
    <td id='btInfo01' background='/FrontOfficeWeb/resources/images/aba_bkg.gif' class='abaSelected' height='16'><a onclick="abaDetalhes();">Detalhes</a></td>
    <td width='9'><img id='AbaRight_btInfo01' src='/FrontOfficeWeb/resources/images/aba_right.gif' width='9' height='16'></td>

    <td width='9'><img id='AbaLeft_btInfo02' src='/FrontOfficeWeb/resources/images/aba_left_off.gif' width='9' height='16'></td>
    <td id='btInfo02' style='cursor:pointer;' class='abaUnselected' height='16'><a href="RDArvoreAtendimento.do" target="ifrAtend" onclick="this.href=this.href+'?idAtd=<%=idAtd%>&acao=RDArvoreAtendimento.do';if (at) eval(zsa);return abaAtendimento();">Atendimento</a></td>
    <td width='9'><img id='AbaRight_btInfo02' src='/FrontOfficeWeb/resources/images/aba_right_off.gif' width='9' height='16'></td>

    <td width='9'><img id='AbaLeft_btInfo03' src='/FrontOfficeWeb/resources/images/aba_left_off.gif' width='9' height='16'></td>
    <td id='btInfo03' style='cursor:pointer;' class='abaUnselected' height='16'><a href="../AtendimentoWorkflow/abaDetalhe.do" onclick="this.href=this.href+'?idAtd=<%=idAtd%>'+'&inResp=<%=inResp%>&acao=RDSolicitante.do';if (sol) eval(zsa);return abaSolicitante();" target="ifrSolic">Solicitante</a></td>
    <td width='9'><img id='AbaRight_btInfo03' src='/FrontOfficeWeb/resources/images/aba_right_off.gif' width='9' height='16'></td>

    <td width='9'><img id='AbaLeft_btInfo04' src='/FrontOfficeWeb/resources/images/aba_left_off.gif' width='9' height='16'></td>
    <td id='btInfo04' style='cursor:pointer;' class='abaUnselected' height='16'><a href="../AtendimentoWorkflow/abaDetalhe.do" onclick="this.href=this.href+'?idAtd=<%=idAtd%>&acao=RDNivelProcesso.do';if (niv) eval(zsa);return abaNivelProc();" target="ifrNProc">N&iacute;vel do Processo</a></td>
    <td width='9'><img id='AbaRight_btInfo04' src='/FrontOfficeWeb/resources/images/aba_right_off.gif' width='9' height='16'></td>

    <td width='9'><img id='AbaLeft_btInfo05' src='/FrontOfficeWeb/resources/images/aba_left_off.gif' width='9' height='16'></td>
    <td id='btInfo05' style='cursor:pointer;' class='abaUnselected' height='16'><a href="../AtendimentoWorkflow/abaDetalhe.do" onclick="this.href=this.href+'?idAtd=<%=idAtd%>&acao=RDLinhasAssoc.do';if (niv) eval(zsa);return abaLinhasAssoc();" target="ifrLAssoc">Linhas Associadas</a></td>
    <td width='9'><img id='AbaRight_btInfo05' src='/FrontOfficeWeb/resources/images/aba_right_off.gif' width='9' height='16'></td>
</tr>
</table>
<table width="778" height="100" cellpadding="0" cellspacing="0" style="background-color:#ededed;border-left:1px solid #adadad;border-right:1px solid #adadad;border-bottom:1px solid #adadad;">
 <tr>
  <td valign="top">
<div id="dDet">
<table width="98%" align="right">
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
	        <html:hidden name="atdVO" property="qtInsistencia"/>
        </td>
    </tr>
</table>
</div>
<%-- Aba de Informacao : Atendimento --%>
<iframe id="ifrAtend" name="ifrAtend" src="" height="0" width="0" frameborder="0"></iframe>
<div id="dAtend" style="display:none;padding-left:20px;padding-top:10px;"></div>
<%-- Aba de Informacao : Solicitante --%>
<iframe id="ifrSolic" name="ifrSolic" src="" height="0" width="0" frameborder="0"></iframe>
<div id="dSolic" style="display:none;"></div>
<%-- Aba de Informacao : Nivel do Processo --%>
<iframe id="ifrNProc" name="ifrNProc" src="" height="0" width="0" frameborder="0"></iframe>
<div id="dNProc" style="display:none;"></div>
                <%-- Aba de Informacao : Linhas Associadas --%>
                <iframe id="ifrLAssoc" name="ifrLAssoc" src="" height="0" width="0" frameborder="0"></iframe>
                <div id="dLAssoc" style="display:none;"></div>
  </td>
 </tr>
</table>
</div>
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
<div id="dvRetornoOperacao" style="visibility:hidden; position:absolute; left:0; top:200px; width:778px; height:245px; z-index:0">
    <iframe id="iframeRetornoOperacao" name="iframeRetornoOperacao" src="<%--AguardeAcao.jsp--%>" width="100%" height="100%" ></iframe>
</div>
<%
String scriptLupa = "";
if ( !"N".equals(request.getParameter("inMenu")) ) {
    scriptLupa = "atendimentoProcesso()";
}%>
<div id="dvOperacoes" style="">
<vivo:quadro id="qdMainAbas" width="778" height="300" label="Operações" scroll="NO" onclick="<%=scriptLupa%>">
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="3"></div>
<acesso:controlHiddenItem nomeIdentificador="wf_detproc_operacoes">
<table width="768" style="background-color:#ffffff;border:1px solid #adadad;">
<tr>
    <td width="70">&nbsp;Operações:<input type="hidden" name="inMirrorRC" id="inMirrorRC" value="<%=request.getParameter("inMirrorRC")%>"></td>
    <td width="698">
        <iframe id="ifrOper" src="RDOperacion.do?idAtd=<%=idAtd%>&inCRI=<%=inCRI%>&inRC=<%=inRC%>&inMirrorRC=<%=request.getParameter("inMirrorRC")%>" height="0" width="0" frameborder="0"></iframe>
        <div id="dOper" style="display:none;"><a href="RDOperacion.do"></a></div>
    </td>
</tr>
</table>
</acesso:controlHiddenItem>
<div><img src="/FrontOfficeWeb/resources/images/transp.gif" width="1" height="5"></div>
<table width="768" cellpadding="0" cellspacing="0" border="0">
    <tr>
        <td valign="top">
            <table border='0' cellpadding='0' cellspacing='0' width='300' height='10' background='/FrontOfficeWeb/resources/images/aba_bkg_off.gif' class='abatexto'>
            <tr id='btAba' valign='top'>
                <td width='9'><img id='AbaLeft_bt01' src='/FrontOfficeWeb/resources/images/aba_left.gif' width='9' height='16'></td>
                <td id='bt01' background='/FrontOfficeWeb/resources/images/aba_bkg.gif' class='abaSelected' height='16'><a onclick="abaHist();">Histórico</a></td>
                <td width='9'><img id='AbaRight_bt01' src='/FrontOfficeWeb/resources/images/aba_right.gif' width='9' height='16'></td>

                <acesso:controlHiddenItem nomeIdentificador="wf_detproc_operacoes">
                <td width='9'><img id='AbaLeft_bt05' src='/FrontOfficeWeb/resources/images/aba_left_off.gif' width='9' height='16'></td>
                <td id='bt05' style='cursor:pointer;' class='abaUnselected' height='16' onclick="abaOper();">Selecionar Operação</td>
                <td width='9'><img id='AbaRight_bt05' src='/FrontOfficeWeb/resources/images/aba_right_off.gif' width='9' height='16'></td>
                </acesso:controlHiddenItem>

                <logic:notEmpty name="atdVO" property="idAtendimentoBaixaHistorico">
                <% if (request.getParameter("inMirrorRC")==null || "0".equals(request.getParameter("inMirrorRC"))){ %>
                <td width='9'><img id='AbaLeft_btEnc' src='/FrontOfficeWeb/resources/images/aba_left_off.gif' width='9' height='16'></td>
                <td id='btEnc' style='cursor:pointer;' class='abaUnselected' height='16'><a href="RDEncerramento.do" onclick="this.href=this.href+'?idAtd=<%=idAtd%>+&inResp=<%=inResp%>';if (ae) eval(zsa);return abaEnc();" target="ifrEnc">Encerramento BKO</a></td>
                <td width='9'><img id='AbaRight_btEnc' src='/FrontOfficeWeb/resources/images/aba_right_off.gif' width='9' height='16'></td>
                <% } %>
                </logic:notEmpty>
            </tr>
            </table>
        </td>
    </tr>
</table>
<div id="dvHist" style="vertical-align:top;">
<form name="h">
<table width="768" height="240" cellpadding="0" cellspacing="5" style="border:1px solid #adadad;background-color:#ededed;" id="table_hist">
    <tr>
                    <%--jsp:include page="LoadEstados.do" flush="true"--%>
        <bean:define id="estadosVO" name="form" property="estadoVO.WFEstadoVOArray"/>
        <td valign="top">Estado:</td>
        <td valign="top">
                        <html:select name="form" property="estadoVO" title="estadoSel" style="width=240px" onchange="loadSubEstados(this.value);">
                <html:option value="-1">&nbsp;</html:option>
                <html:options collection="estadosVO" property="idEstado" labelProperty="dsEstado" />
            </html:select>
        </td>
                    <td valign="top" nowrap>Sub-Estado:</td>
        <td id="tdSubEstados" valign="top">
                        <select name="subEstadoSel" style="width=240px" title="subEstadoSel">
                <option value="-1">&nbsp;</option>
            </select>
        </td>
        <script>
            var arrCombos=new Array();
            <logic:iterate id="estadosVO" name="form" property="estadoVO.WFEstadoVOArray" indexId="idxEstados">
            var combo=document.createElement("<SELECT NAME='subEstadoSel'>");
            combo.idEstado='<bean:write name="estadosVO" property="idEstado"/>';
            <logic:iterate id="subEstadosVO" name="estadosVO" property="WFSubEstadoVOArray" indexId="idxSubEstados">
                        var opt=document.createElement("OPTION");
                        opt.value = '<bean:write name="subEstadosVO" property="idSubEstado"/>';
            opt.text='<bean:write name="subEstadosVO" property="dsSubEstado"/>';
            combo.add(opt);
            </logic:iterate>
            arrCombos[arrCombos.length]=combo;
            </logic:iterate>
            function loadSubEstados(id) {
                if(id==-1){
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
        </script>
        <td valign="top">
            <img class="button"
                 action="RDHistorico"
                 onclick="submitPesquisarHist(); return false"
                 style="border:none;"
                 src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" />
        </td>
    </tr>
    <tr>
        <td colspan="5" valign="top" class="BorderTable1">
            <iframe name="ifrHist" id="ifrHist" src="RDHistorico.do?idAtd=<%=idAtd%>&inCRI=<%=inCRI%>&inRC=<%=inRC%>" height="0" width="0" frameborder="0"></iframe>
            <div id="dHist"><a href="RDHistorico.do"></a></div>
        </td>
    </tr>
</table>
</form>
</div>
<div id="divbodyacoe" style="display:none;">
    <table id="tableAcoes" width="768" height="240" cellpadding="0" cellspacing="5" style="border:1px solid #adadad;background-color:#ededed;">
        <tr>
            <td valign="top" class="BorderTable1" width="768" height="100%">
                <iframe id="ifrmAbas" name="ifrmAbas" width="755" height="226" frameborder="0" scrolling="auto" src=""></iframe>
            </td>
        </tr>
    </table>
</div>
<logic:notEmpty name="atdVO" property="idAtendimentoBaixaHistorico">
<div id="dvEnc" style="display:none;">
<table width="768" height="167" cellpadding="0" cellspacing="5" style="border:1px solid #adadad;background-color:#ededed;">
<tr>
    <td valign="top" class="BorderTable1" width="768" height="100%">
        <iframe id="ifrEnc" name="ifrEnc" src="" height="0" width="0" frameborder="0"></iframe>
        <div id="dEnc"></div>
    </td>
</tr>
</table>
</div>
</logic:notEmpty>
</vivo:quadro>
</div>

<div id="divPopupTI" style="z-index:998;position:absolute;top:0;left:0;width:150%;height:150%;background-image:url(/FrontOfficeWeb/resources/images/window_bg.gif);display:none;">
    <div id="wrapper_divPopupTI" style="width:600px;height:400px;margin:100px 0 0 100px;">
        <div id="header_divPopupTI" style="width:600px;height:21px;background-image:url(/FrontOfficeWeb/resources/images/window_topbg.gif);background-color:#0066cb;">
            <span id="title_divPopupTI" style="float:left;padding:3px 0 0 3px;;color:#fff;font-weight:bold;">Atendimento</span>
            <span id="upperRightButton_divPopupTI" style="float:right;">
                <img id="imgClose_divPopupTI" hspace="3" src="/FrontOfficeWeb/resources/images/window_fechar.gif" onmouseup="divPopupTI.style.display='none';cleanContent()" style="cursor:pointer;">
            </span>
        </div>
        <div id="wrapper_iframePopupTI" style="width:600px;background-color:#0066cb;">
            <iframe width="600" height="395" id="iframePopupTI" name="iframePopupTI" frameborder="0" scrolling="no"></iframe>
        </div>
    </div>
    <!--[if lte IE 6.5]><iframe style="display:none;display/**/:block;position:absolute;top:0;left:0;z-index:-1;filter:mask();width:3000px;height:3000px;"></iframe><![endif]-->
</div>

<logic:notEqual parameter="inMenu" value="N">
    <% if ((request.getParameter("inMirrorRC")!=null && request.getParameter("inMirrorRC").equals(ConstantesCRM.SONE)) || ConstantesCRM.STWO.equals(request.getSession().getAttribute("inRC"))){ %>
        <img onClick="voltarAdquirir(); return false" src="../../resources/images/bt_voltar_nrml.gif" style="border:none;" vspace="5"/>
    <% }else{ %>
        <img onClick="voltar(); return false" src="../../resources/images/bt_voltar_nrml.gif" style="border:none;" vspace="5"/>
    <% } %>
</logic:notEqual>
</form>
</vivo:sessao>
<%--Quadro Flutuante--%>
<vivo:quadroFlutuante id="dvConPesq" idIframe="ifrmConPesq" width="300" height="100" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" onclick="voltar();"/>
<vivo:quadroFlutuante id="qdDocTec" idIframe="ifrmDocTec" label="Documentos Técnicos" spacesLeft="10" spacesTop="70" url="<%=request.getContextPath()%>" display="none" height="200" width="780"/>
<vivo:quadroFlutuante id="dvAtendimento2" idIframe="iframeAtendimento" width="800" height="600" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Atendimento"/>
<vivo:quadroFlutuante id="dvComentAcao" idIframe="ifrmComentAcao" width="300" height="150" spacesTop="225" spacesLeft="250" scroll="auto" display="none" url="<%=request.getContextPath()%>"/>
    <vivo:quadroFlutuante id="dvPesqForm" idIframe="ifrmPesqForm" width="270" height="200" spacesTop="185" spacesLeft="250" display="none" url="<%=request.getContextPath()%>" src="" onclick="ifrmAbas.ativar_combos();"/>
<!--logic:notEqual parameter="inMenu" value="N"-->
<logic:notPresent parameter="qtd">
    <logic:iterate id="qtdPai" indexId="qtd" name="form" property="qtdProcessosPai">
        <vivo:quadroFlutuante id='<%="dvDetalheOrigem"+qtd%>' idIframe='<%="ifrmDetalheOrigem"+qtd%>' width="800" height="575" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" label="Detalhe Origem"/>
    </logic:iterate>
</logic:notPresent>
<!--/logic:notEqual-->
<script language="javascript">
<!--
<logic:present parameter="fila">

    voltarAdquirir = function() {
        top.frameApp.location.href="/FrontOfficeWeb/workflow/AtendimentoInBoxResposta/begin.do?retornoAdquirir=1";
    }

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

        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ){
            top.frameApp.startAnimation();
        }
        if( nrLinha == "" ){
            pesquisa = 'conta';
            tipoAtendimento = 'prospect';
            nrLinha='<bean:write name="atdVO" property="idPessoa"/>';
        }
        top.frameApp.location.href="telaInicial.do?tipoAtendimento="+tipoAtendimento+"&nrLinha="+nrLinha+"&pesquisa="+pesquisa+"&relacionamento="+inResponsavelAbertura+"&framesMostrados=dados&fila="+fl+"&idAtendimento=<%=idAtd%>";
    }

    function showPopupTI(title, width, height, top, action) {
        if( window.top.frameApp.dvAnimarAguarde != null ) window.top.frameApp.startAnimation();
        if ($('divPopupTI').style.display != "none") {
            $('divPopupTI').style.display = "none;";
        }
        //controlCombos();
        $('divPopupTI').style.display = "block";
        $('title_divPopupTI').innerHTML = title;
        $('iframePopupTI').width = width;
        $('wrapper_iframePopupTI').style.width = width + "px";
        $('header_divPopupTI').style.width = width + "px";
        $('iframePopupTI').height = height-5;
        $('iframePopupTI').src = action;
        $('wrapper_divPopupTI').style.marginLeft = (800-width)/2 + "px";
        if (top != null) {
            $('wrapper_divPopupTI').style.marginTop = (top-21) + "px";
        } else {
            $('wrapper_divPopupTI').style.marginTop = (600-height)/2 + "px";
        }
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
    dAA=document.getElementById('dAtend');
    dAD=document.getElementById('dDet');
    dAS=document.getElementById('dSolic');
    dAN=document.getElementById('dNProc');
    dLA=document.getElementById('dLAssoc');
    dAH=document.getElementById('dvHist');
    dAC=document.getElementById('divbodyacoe');
    dAE=document.getElementById('dvEnc');
    i=document.getElementById("ifrmAbas");

    function submitPesquisarHist() {
        d=dAH.document.forms[0];
        e=d.estadoVO.options[d.estadoVO.selectedIndex].value;
        try{
            s=d.subEstadoSel.options[d.subEstadoSel.selectedIndex].value;
        }catch(ex){
            s='-1';
        }
        eval(zsa);
        document.getElementById("ifrHist").src="RDHistorico.do?idAtd=<%=idAtd%>&est="+e+"&subest="+s;
    }
    eval(zso);
    //seta imagem para ampliar parte das operaçoes
    document.getElementById('qdMain_tdSessao').innerHTML='<img name=\"lupaaba\" align=\"left\" title=\"Click para maximizar as operações do workflow!\" src=\"../../resources/images/bt_lupa_aba.gif\" onclick=\"resizeFrameDetalhe();\" style=\"cursor:pointer\">';
    document.getElementById('qdMain_tdSessao').style.width='35px';
    -->
</script>
</netui-template:section>
</netui-template:template>