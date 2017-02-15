<%@page import="br.com.vivo.fo.constantes.ConstantesCRM"%>
<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ page import="br.com.vivo.fo.cliente.vo.ParametrosVODocument.ParametrosVO"%>
<%@ page import="br.com.vivo.fo.workflow.vo.WFAcaoVODocument.WFAcaoVO"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>

<acesso:controlInitEnv/>

<bean:define id="Form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="registrarContatoForm" />

<head>
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/frontoffice.css">
    <link rel="STYLESHEET" type="text/css" href="<%=request.getContextPath()%>/resources/css/esacStyle.css">
    <script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/frameweb.js" ></script>
	<script language="javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
	<script language="JavaScript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js" ></script>
    <script type="text/javascript" language="javascript">

    var botoesAcoes;
    var inCelular = false;
    var nrCelular;
    var inCelAlready = false;
    var url = '';
    var clickAba = true;                    // Variável de controle do clique nas abas
    var flagTamanhoFrame;                   // Controla qual o tamanho do frame
                                            // 1 - o frame está exibindo apenas a árvore
                                            // 2 - o frame está exibindo a árvore e o registro de atendimento
                                            // 3 - o frame está exibindo apenas o registro de atendimento
    var flagClickArvore = false;            // Controla se a pessoa clicou ou não na árvore;
    var flagProcessosCorrentes = false;     // Controla se há processos correntes;

    if (typeof(parent.mostrar_div) != "undefined") {
        parent.mostrar_div();
    }

    function setFlagClickArvore(){
        flagClickArvore = true;
    }

    function resetaAtributos(){
        flagClickArvore=false;
    }

    function redimensionaProcessosCorrentes(){
		/*if(flagProcessosCorrentes && !flagClickArvore){
        	if(flagTamanhoFrame == 1){
                ifrmAbas.document.all['ifrmListaProcessos'].style.width = '419px';
                ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_header'].style.width = '400px';
                ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_body'].style.width = '384px';
                ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_header'].parentElement.style.width = '384px';
                ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_body'].parentElement.style.width = '400px';

        	}else if(flagTamanhoFrame == 2){
	            ifrmAbas.document.all['ifrmListaProcessos'].style.width = '431px';
	            ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_header'].style.width = '416px';
	            ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_body'].style.width = '400px';
	            ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_header'].parentElement.style.width = '400px';
	            ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_body'].parentElement.style.width = '416px';

        	}else if(flagTamanhoFrame == 3){
	            ifrmAbas.document.all['ifrmListaProcessos'].style.width = '729px';
	            ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_header'].style.width = '714px';
	            ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_body'].style.width = '698px';
	            ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_header'].parentElement.style.width = '698px';
	            ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_body'].parentElement.style.width = '714px';
            }

        } else if(flagClickArvore){
            if (typeof(ifrmAbas.document.all['ifrmListaProcessos']) != "undefined"){
                if(flagTamanhoFrame == 1){
                    ifrmAbas.document.all['ifrmListaProcessos'].style.width = '419px';
                    ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_header'].style.width = '400px';
                    ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_body'].style.width = '384px';
                    ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_header'].parentElement.style.width = '384px';
                    ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_body'].parentElement.style.width = '400px';

                }else if(flagTamanhoFrame == 2){
	                ifrmAbas.document.all['ifrmListaProcessos'].style.width = '431px';
	                ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_header'].style.width = '416px';
	                ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_body'].style.width = '400px';
	                ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_header'].parentElement.style.width = '400px';
	                ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_body'].parentElement.style.width = '416px';

                }else if(flagTamanhoFrame == 3){
	                ifrmAbas.document.all['ifrmListaProcessos'].style.width = '729px';
	                ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_header'].style.width = '714px';
	                ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_body'].style.width = '698px';
	                ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_header'].parentElement.style.width = '698px';
	                ifrmAbas.ifrmListaProcessos.document.all['dvListaProcessos_body'].parentElement.style.width = '714px';
				}
            }
        }*/
    }

    function atualizaURL(urlContato){
        if(urlContato != ''){
            url = urlContato;
            document.all['tdBotaoURL'].style.visibility = "visible";
        } else {
            document.all['tdBotaoURL'].style.visibility = "hidden";
        }
    }

    function mostraURL(){
        window.open("http://"+url,'URL','');
    }

    function fechaInsistencia(){
        redimensionaFrames('botaoDirContato');
        ifrmAbas.document.location.replace("about:blank");

    }

    function atualizaTipoComunicacao(){
        ifrmAbas.ativar_combos();
        ifrmAbas.obtemTipoComunicacaoIFrame();
    }

    function controlaProcessosCorrentes(){
        redimensionaFrames('botaoMeioArvore');
        if (typeof(document.all['abaPCorrentes']) != "undefined")
            document.all['abaPCorrentes'].click();
        document.all['abaContato'].style.cursor = "";
        document.all['abaFormulario'].style.cursor = "";
        document.all['ifrmAbas'].src = "/FrontOfficeWeb/workflow/RegistrarContato/atendimentosCorrentes.do";
        flagProcessosCorrentes = true;
    }

    function exibeBotoes(){
        for(i=0;i<botoesAcoes.length-1;i++)
            botoesAcoes[i].style.display = "";
        //document.all['ifrmAbas'].style.height = "336px";
    }

    function escondeBotoes(){
        document.all['tdBotoesAcao'].innerHTML = "";
    }

    function redimensionaFrames(botao) {
        if(botao == 'botaoEsqArvore'){
            dvArvore.style.width = "1px";
            dvContato.style.width = "750px";
            dvContato.style.overflow = "auto";
            document.all['ifrmAbas'].style.width = "730px";
            
            if (typeof(ifrmAbas.document.all['ifrmServicos']) != "undefined"){
                ifrmAbas.document.all['ifrmServicos'].style.width = "700px";
                ifrmAbas.document.all['observacaoAtendimento'].style.width = "630px";
            
            }else{
                if (typeof(ifrmAbas.document.all['observacaoAtendimento']) != "undefined"){
                    ifrmAbas.document.all['observacaoAtendimento'].style.width = "450px";
                    if (ifrmAbas.document.getElementById('tdObservacao') != null){
                        ifrmAbas.document.getElementById('tdObservacao').width = 175;
                        ifrmAbas.document.getElementById('tdAreaObservacao').width = 480;
                    }
                }
            }
            
            document.all['botaoMeioArvore'].style.display = "";
            <logic:empty name="Form" property="abreProcessoReaRei">
            document.all['botaoMeioContato'].style.display = "";
            </logic:empty>
            flagTamanhoFrame = 3;
            redimensionaProcessosCorrentes()
        }

        if(botao == 'botaoMeioArvore' || botao == 'botaoMeioContato'){
            dvArvore.style.width = "321px";
            dvContato.style.width = "440px";
            dvContato.style.overflow = "auto";
            ifrmArvore.document.all['divArvore'].style.width = "301px";
            document.all['ifrmAbas'].style.width = "435px";
            if (typeof(ifrmAbas.document.all['ifrmServicos']) != "undefined"){
                ifrmAbas.document.all['ifrmServicos'].style.width = "405px";
                ifrmAbas.document.all['observacaoAtendimento'].style.width = "310px";
            
            }else{
                if (typeof(ifrmAbas.document.all['observacaoAtendimento']) != "undefined"){
                    ifrmAbas.document.all['observacaoAtendimento'].style.width = ifrmAbas.tamanhoAreaObservacao;
                }
            }
            
            document.all['ifrmArvore'].style.width = "320px";
            document.all['botaoMeioArvore'].style.display = "none";
            <logic:empty name="Form" property="abreProcessoReaRei">
            document.all['botaoMeioContato'].style.display = "none";
            </logic:empty>
            
            flagTamanhoFrame = 2;
            redimensionaProcessosCorrentes();
        }

        if(botao == 'botaoDirContato'){
            <logic:empty name="Form" property="idAtendimento">
            dvArvore.style.width = "766px";
            </logic:empty>
            <logic:notEmpty name="Form" property="idAtendimento">
            dvArvore.style.width = "754px";
            </logic:notEmpty>
            
            dvContato.style.width = "1px";
            dvContato.style.overflow = "hidden";
            ifrmArvore.document.all['divArvore'].style.width = "764px";
            
            document.all['ifrmArvore'].style.width = "790px";
            document.all['tdContato'].style.width = "1px";
            document.all['botaoMeioArvore'].style.display = "";
            <logic:empty name="Form" property="abreProcessoReaRei">
            document.all['botaoMeioContato'].style.display = "";
            </logic:empty>
            
            flagTamanhoFrame = 1;
            redimensionaProcessosCorrentes();
        }

    }

    function mostrarAba(abaPai, abaFilho, aba) {
        if(aba == "formulario"){
            //Caso não permita o click retorna a operação
            if( ! clickAba ) return;
            if( ! document.ifrmAbas['divContato'] ) return;

            document.ifrmAbas['divContato'].style.display           = "none";
            <logic:empty name="Form" property="abreProcessoReaRei">
            document.ifrmAbas['divProcessoCorrentes'].style.display = "none";
            </logic:empty>
            document.ifrmAbas['divQuest'].style.display             = "";
        
        }else if(aba == "contato"){
            //Caso não permita o click retorna a operação
            if( ! clickAba ) return;
            if( ! document.ifrmAbas['divQuest'] ) return;

            document.ifrmAbas['divQuest'].style.display             = "none";
            <logic:empty name="Form" property="abreProcessoReaRei">
            document.ifrmAbas['divProcessoCorrentes'].style.display = "none";
            </logic:empty>
            document.ifrmAbas['divContato'].style.display           = "";

            //Força seleção inicial da opçao "CELULAR"
            selectTipoRetorno = ifrmAbas.document.forms[0].tipoComunicacaoSelecionada;
            for (i=0; i < selectTipoRetorno.length; i++) {
                if (selectTipoRetorno.options[i].text == "CELULAR") {
                    //inCelAlready = true;
                    selectTipoRetorno.selectedIndex = i;
                    ifrmAbas.obtemComunicacaoIFrame(selectTipoRetorno, ifrmAbas.document.forms[0].comunicacaoDisponivel);
                }
            }

            //Se não houver opção "Celular" busca número da Tela de Atendimento
            //idRelacionamento 6 -> Prospect
            if (inCelular == false && inCelAlready == false
                && top.frameApp.$('ti_comboPesquisa')
                && top.frameApp.$('ti_comboPesquisa').value != "naoCliente"
                && top.frameApp.idTipoRelacionamento != 6 ) {
                <%
                ParametrosVO parametros = (ParametrosVO) request.getSession().getAttribute(ConstantesCRM.SPARAMETROS);
                if (parametros != null) {
                %>
                    nrCelular = formatCelular("<%= parametros.getNrLinha() %>");

                    for (i=0; i < selectTipoRetorno.length; i++) {
                        if (selectTipoRetorno.options[i].text == "CELULAR") {
                            inCelAlready = true;
                        }
                    }
                    if (inCelAlready == false) {
                        selectTipoRetorno.options[selectTipoRetorno.options.length] = new Option("CELULAR", "41");
                        selectTipoRetorno.selectedIndex = selectTipoRetorno.options.length-1;
                    } else {
                        inCelAlready = false;
                    }

                    var aOptComponentsParent = ifrmAbas.document.forms["registrarContatoForm"].elements["comunicacaoDisponivel"];
                    var aOptComponentsParentSelec = ifrmAbas.document.forms["registrarContatoForm"].elements["comunicacaoSelecionada"];

                    while(aOptComponentsParent.options.length != 0)
                        aOptComponentsParent.options.remove(0);

                    var inCreateCell = true;
                    for (i=0; i<aOptComponentsParentSelec.length; i++) {
                        if (aOptComponentsParentSelec.options[i].value == nrCelular) {
                            inCreateCell = false;
                        }
                    }

                    if (inCreateCell) {
                        aOptComponentsParentSelec.options[0] = new Option(nrCelular, nrCelular);
                    }

                <%
                }
                %>
            }
            var constroi = true;
            for(x=0;x<selectTipoRetorno.options.length;x++){
                if(selectTipoRetorno.options[x].value=="0"){
                    constroi = false;
                }
            }
            /*if(constroi){
                selectTipoRetorno.options[selectTipoRetorno.options.length] = new Option("Sem Retorno", "0");
            }*/

        }
        else if(aba == "pCorrentes"){
            if( ! document.ifrmAbas['divQuest'] ) return;

            document.ifrmAbas['divQuest'].style.display             = "none";
            document.ifrmAbas['divContato'].style.display           = "none";
            document.ifrmAbas['divProcessoCorrentes'].style.display = "";
        }

        //Seleciona a aba
        abaSelected(abaPai, abaFilho);
    }

    function obterArvoreBaixa() {
        retorno = ifrmAbas.obterArvoreBaixa();
        if(!retorno)
            return false;
        dvAtendimento.style.display = '';
    }

    function fecharProcesso(idBaixa, idBaixaMensagem, observacaoFechamento){

        //Liga animação
        if( top.dvAnimarAguarde != null ) top.startAnimation();

        ifrmAbas.document.all['idBaixa'].value = idBaixa;
        ifrmAbas.document.all['idBaixaMensagem'].value = idBaixaMensagem;
        ifrmAbas.document.all['observacaoFechamento'].value = observacaoFechamento;
        retorno = ifrmAbas.fecInmediato();

        if(!retorno)
            return false;

        //desarma a seleção das abas de formulário e contato
        clickAba = false;

        if (typeof(document.all['abaPCorrentes']) != "undefined")
            document.all['abaPCorrentes'].click();

        document.all['abaContato'].style.cursor = "";
        document.all['abaFormulario'].style.cursor = "";

        //dvNumProc.style.display = '';

        escondeBotoes();
    }

    encaminharProcesso = function() {

        retorno = ifrmAbas.encaminharProcesso();

        if(!retorno)
            return false;

        //desarma a seleção das abas de formulário e contato
        clickAba = false;

        if (typeof(document.all['abaPCorrentes']) != "undefined")
            document.all['abaPCorrentes'].click();

        document.all['abaContato'].style.cursor = "";
        document.all['abaFormulario'].style.cursor = "";

        //dvNumProc.style.display = '';

        escondeBotoes();
    };

    function fecInmediato() {

        //Liga animação
        if( top.dvAnimarAguarde != null ) top.startAnimation();

        retorno = ifrmAbas.fecInmediato();

        if(!retorno)
            return false;

        //desarma a seleção das abas de formulário e contato
        clickAba = false;

        if (typeof(document.all['abaPCorrentes']) != "undefined")
            document.all['abaPCorrentes'].click();

        document.all['abaContato'].style.cursor = "";
        document.all['abaFormulario'].style.cursor = "";

        //dvNumProc.style.display = '';

        escondeBotoes();
    }

    function regContato() {

        //Liga animação
        if( top.dvAnimarAguarde != null ) top.startAnimation();

        retorno = ifrmAbas.regContato();

        if(!retorno)
            return false;

        //desarma a seleção das abas de formulário e contato
        clickAba = false;

        if (typeof(document.all['abaPCorrentes']) != "undefined")
            document.all['abaPCorrentes'].click();

        document.all['abaContato'].style.cursor = "";
        document.all['abaFormulario'].style.cursor = "";

        //dvNumProc.style.display = '';

        escondeBotoes();
    }

    function refresh(idPessoa){
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        document.forms[0].target ='ifrmLupa';
        document.forms[0].action ='/FrontOfficeWeb/workflow/RegistrarContato/loadContato.do?reload=OK&&idPessoaCliente='+idPessoa;
        document.forms[0].submit();
    }

    function registrarContatoDone() {
        top.frameApp.location.href='registrarContatoDone.do';
    }

    function formatCelular(numCel){
        if (numCel.length < 10) {
            numCel = "("+numCel.substring(0,2)+")"+numCel.substring(2,5)+"-"+numCel.substring(5,9);
        
        } else {
            numCel = formatPhoneNumber(numCel);
        }
        return numCel;
    }

    onload = function() {
        <acesso:controlHiddenItem nomeIdentificador="wor_af_verpagina">

        document.all['tdBotoes'].style.height = "27px";
        document.all['ifrmAbas'].style.height = "310px";

        // Limpa os objetos de seleção
        abaFormulario.style.cursor = '';
        abaContato.style.cursor = '';

        <logic:empty name="Form" property="abreProcessoReaRei">
        abaPCorrentes.style.cursor = '';
        </logic:empty>

        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();

        if (typeof(parent.oculta_div) != "undefined") {
            parent.oculta_div();
        }

        <% if(session.getAttribute("cbOperacao")!=null && session.getAttribute("cbOperacao").equals("15")) {
            session.removeAttribute("cbOperacao"); %>
        	redimensionaFrames('botaoEsqArvore');

        <% } else if(session.getAttribute("cbOperacao")!=null && session.getAttribute("cbOperacao").equals("12")) {
            session.removeAttribute("cbOperacao"); %>
        	redimensionaFrames('botaoEsqArvore');
        <% } else { %>
        	redimensionaFrames('botaoDirContato');
        <% } %>
        </acesso:controlHiddenItem>
    };
    </script>
</head>
<body leftmargin="0" topmargin="0">

<acesso:controlHiddenItem nomeIdentificador="wor_af_verpagina">
    <form id="registrarContatoForm" name="registrarContatoForm" method="post">
        <html:hidden name="Form" property="idBaixa"/>
        <html:hidden name="Form" property="idBaixaMensagem"/>
        <html:hidden name="Form" property="observacaoFechamento"/>
        <html:hidden name="Form" property="idGrupoAbertura"/>
        <html:hidden name="Form" property="idSegmentacao"/>
        <html:hidden name="Form" property="idTipoCarteira"/>
        <html:hidden name="Form" property="idTipoLinha"/>
        <html:hidden name="Form" property="idUfOperadora"/>
        <html:hidden name="Form" property="inResponsavelAbertura"/>
        <html:hidden name="Form" property="nomeContato"/>
        <html:hidden name="Form" property="idChamadaTelefonica"/>
        <html:hidden name="Form" property="telContato"/>
        <html:hidden name="Form" property="nrLinha"/>
        <html:hidden name="Form" property="nrConta"/>
        <html:hidden name="Form" property="idConta"/>
        <html:hidden name="Form" property="contaSelecionada"/>
        <html:hidden name="Form" property="idLinha"/>
        <html:hidden name="Form" property="linhaSelecionada"/>
        <html:hidden name="Form" property="idClienteDePara"/>
        <html:hidden name="Form" property="telContatoFrm"/>
        <html:hidden name="Form" property="idUsuarioDePara"/>
        <html:hidden name="Form" property="idPessoa"/>
        <html:hidden name="Form" property="idDiscador"/>
        <html:hidden name="Form" property="inTipoPessoa"/>
        <html:hidden name="Form" property="idAtendimento"/>
        <html:hidden name="Form" property="idLinhaAtendimento"/>
        <html:hidden name="Form" property="descricaoCompleta"/>
        <html:hidden name="Form" property="idAtendimentoSituacao"/>
        <html:hidden name="Form" property="idTipoReaberturaProcesso"/>
        <html:hidden name="Form" property="qtDias"/>
        <html:hidden name="Form" property="nmTipo"/>
        <html:hidden name="Form" property="idFase"/>
        <html:hidden name="Form" property="abreProcessoReaRei"/>
        <html:hidden name="Form" property="nomeContatoAlterado"/>
        <html:hidden name="Form" property="carregaLinha"/>
        <html:hidden name="Form" property="nrProtocolo"/>
    </form>
    <table id="table_body" width="100%" height="100%" cellpadding="0" cellspacing="0" bgcolor="#F5F5F5">
        <tr>
            <td id="tdArvore">
            <SPAN id="dvArvore" style="overflow:hidden;width:330px;height:378px;">
                <table width="100%" cellpadding="0" cellspacing="0">
                    <tr>
                        <td valign="top" class="BorderTable1" width="330">
                            <iframe id="ifrmArvore" name="ifrmArvore" width="330px" height="349px" frameborder=0 scrolling="auto" src="/FrontOfficeWeb/workflow/RegistrarContato/arvoreContato.do"></iframe>
                            <table width="100%" align="center">
                                <tr>
                                    <td id="tdBotaoURL" style="visibility:hidden">
                                    <acesso:controlHiddenItem nomeIdentificador="wor_af_acessonivelcontato">
                                        <vivo:botao id="btNavegarURL" width="250px" height="10px" value="Acesso a Informação do Nível de Contato" styleClass="btTemplate" onclick="mostraURL();" />
                                    </acesso:controlHiddenItem>
                                    </td>
                                    <td>
                                        <span id="botaoEsqArvore" style="overflow:hidden" onclick="redimensionaFrames('botaoEsqArvore')">
                                            <img src="<%=request.getContextPath()%>/resources/images/bt_abre_left.gif" alt="Clique para visualizar apenas o Registro do Atendimento" style="cursor:pointer;">
                                        </span>
                                        <span id="botaoMeioArvore" style="overflow:hidden" onclick="redimensionaFrames('botaoMeioArvore')">
                                            <img src="<%=request.getContextPath()%>/resources/images/bt_abre_centro.gif" alt="Clique para visualizar o Registro do Atendimento e a Árvore de Contato" style="cursor:pointer;">
                                        </span>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </SPAN>
            </td>
            <td id="tdContato"> <!-- Inicio aba Contato  -->
            <SPAN id="dvContato" style="overflow:hidden;width:400px;height:378px;">
                <table width="100%" cellpadding="0" cellspacing="0">
                    <tr>
                        <td valign="top" class="BorderTable1" width="100%">
                            <table cellpadding="0" cellspacing="0" bgcolor="#F5F5F5">
                                <tr id="abasRegContato">
                                    <td valign="top">
                                        <logic:notEmpty name="Form" property="abreProcessoReaRei">
                                            <vivo:abaGrupo id="btAba" width="730px" height="10px" styleClass="abatexto">
                                                <acesso:controlHiddenItem nomeIdentificador="wor_af_abaformularioprocesso"><vivo:abaItem id="abaFormulario"  onclick="mostrarAba(btAba, abaFormulario, 'formulario');" value="Formulário de Processo"/></acesso:controlHiddenItem>
                                                <acesso:controlHiddenItem nomeIdentificador="wor_af_abacontato"><vivo:abaItem id="abaContato" width="50%" onclick="mostrarAba(btAba, abaContato, 'contato');" value="Contato"/></acesso:controlHiddenItem>
                                           </vivo:abaGrupo>
                                        </logic:notEmpty>
                                        <logic:empty name="Form" property="abreProcessoReaRei">
                                            <vivo:abaGrupo id="btAba" width="430px" height="10px" styleClass="abatexto">
                                                <acesso:controlHiddenItem nomeIdentificador="wor_af_abaformularioprocesso"><vivo:abaItem id="abaFormulario"  onclick="mostrarAba(btAba, abaFormulario, 'formulario');" value="Formulário de Processo"/></acesso:controlHiddenItem>
                                                <acesso:controlHiddenItem nomeIdentificador="wor_af_abacontato"><vivo:abaItem id="abaContato" onclick="mostrarAba(btAba, abaContato, 'contato');" value="Contato"/></acesso:controlHiddenItem>
                                                <acesso:controlHiddenItem nomeIdentificador="wor_af_abaprocessocorrente"><vivo:abaItem id="abaPCorrentes" onclick="mostrarAba(btAba, abaPCorrentes, 'pCorrentes');"  value="Processos Correntes"/></acesso:controlHiddenItem>
                                           </vivo:abaGrupo>
                                        </logic:empty>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top" class="BorderTable1">
                                        <iframe id="ifrmAbas" name="ifrmAbas" width="483px" height="342px" frameborder=0 scrolling="auto" src="about:blank"></iframe>
                                    </td>
                                </tr>
                                <tr>
                                    <td valign="top" class="BorderTable1" id="tdBotoes">
                                        <table align="center" width="100%">
                                            <tr>
                                                <td>
                                                 <logic:empty name="Form" property="abreProcessoReaRei">
                                                    <SPAN id="botaoMeioContato" style="overflow:hidden" onclick="redimensionaFrames('botaoMeioArvore')">
                                                        <img src="<%=request.getContextPath()%>/resources/images/bt_abre_centro.gif" alt="Clique para visualizar o Registro do Atendimento e a Árvore de Contato" style="cursor:pointer;">
                                                    </SPAN>
                                                    <SPAN id="botaoDirContato" style="overflow:hidden" onclick="redimensionaFrames('botaoDirContato')">
                                                        <img src="<%=request.getContextPath()%>/resources/images/bt_abre_right.gif" alt="Clique para visualizar apenas a Árvore de Contato" style="cursor:pointer;">
                                                    </SPAN>
                                                  </logic:empty>
                                                </td>

                                                <td id="tdBotoesAcao" align="center" height="25px">


                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
            </SPAN>
            </td><!-- Fim aba Contato  -->
        </tr>
    </table>

    <!--Quadro Flutuante que exibe a pesquisa do formulário-->
    <vivo:quadroFlutuante id="dvPesqForm" idIframe="ifrmPesqForm" width="270" height="200" spacesTop="5" spacesLeft="250" display="none" url="<%=request.getContextPath()%>"  src="/FrontOfficeWeb/workflow/RegistrarContato/pesquisaFormulario.do" onclick="ifrmAbas.ativar_combos();"/>
    <iframe id="divPesquisaForm" src="javascript:false;" scrolling="no" frameborder="0" style="position:absolute; top:0px; left:0px; display:none;"></iframe>

    <!--Quadro Flutuante que exibe a Árvore de baixa-->
    <vivo:quadroFlutuante id="dvAtendimento" scroll="auto" idIframe="ifrmAtendimento" width="600" height="330" spacesTop="0" spacesLeft="100" display="none" url="<%=request.getContextPath()%>" onclick="ifrmAtendimento.fechar();"/>

    <!--Quadro Flutuante que exibe o número do processo-->
    <vivo:quadroFlutuante id="dvNumProc" idIframe="ifrmNumProc" width="200" height="150" spacesTop="70" spacesLeft="300" display="none" url="<%=request.getContextPath()%>" onclick="ifrmNumProc.fechar();"/>

    <!--Quadro Flutuante da lupa Customer Profile-->
    <vivo:quadroFlutuante id="dvLupa" idIframe="ifrmLupa" width="761" height="352" spacesTop="0" spacesLeft="0" display="none" url="<%=request.getContextPath()%>" onclick="atualizaTipoComunicacao();"/>

</acesso:controlHiddenItem>
</body>
