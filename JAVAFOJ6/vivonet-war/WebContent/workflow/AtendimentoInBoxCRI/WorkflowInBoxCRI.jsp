<%@ page language="java" contentType="text/html;charset=ISO-8859-1"%>
<%@ taglib uri="netui-tags-html.tld" prefix="netui"%>
<%@ taglib uri="netui-tags-template.tld" prefix="netui-template"%>
<%@ taglib uri="/WEB-INF/vivo-crm.tld" prefix="vivo" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/consorcioctrlaccess.tld" prefix="acesso"%>
<acesso:controlInitEnv/>
   
<bean:define id="form" name="com.bea.wlw.netui.pageflow.PageFlowUtils_currentPageFlow" property="atendimentoInBoxCRIForm" />

<netui-template:template templatePage="./../../resources/jsp/CRMTemplate.jsp">
    <netui-template:setAttribute name="title" value="Workflow"/>
    <netui-template:setAttribute name="modulo" value="Processos In-Box CRI"/>
    <netui-template:section name="headerSection">
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/validacao.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/vivoval.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/formatPhoneNumber.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/resources/scripts/prototype.js"></script>
<script type="text/javascript" src="../../resources/scripts/calendar.js"></script>
<script type="text/javascript" src="../../resources/scripts/RWFUser.js"></script>
        <script type="text/javascript">
    
    //Controle da tread de refresh automatico
    var treadRefresh;
    var currentTab;
    var currentMlSeconds;
    
    var abaSelecionada = 1;
    var abaPesquisaSelecionada = 0;
    var inFechadoCancelado=-1;
    var optDataSel=0;
    var inboxNotaUra=1;
    
    function restartRefresh() {
        startRefresh(abaSelecionada,currentMlSeconds);
    }
    
    function stopRefresh() {
        //Limpa a tread se necessario
        window.clearInterval(treadRefresh);
    }
    
    //Controle da tread do refresh automático parametrizável
    function startRefresh(idTab,mlSecondsRefresh) {
        stopRefresh();
        //Inicializa o refresh automático
        treadRefresh = window.setInterval("exibicaoAbaInBox("+idTab+")", mlSecondsRefresh );
        abaSelecionada = idTab;
        currentMlSeconds = mlSecondsRefresh;
    }

    var frmsState = null;
    
    function ajustaTela(){
        tbTrat =  fraAbas.document.all["tbResultado_body"];
        if (tbTrat != null) {
            tbTrat.parentElement.style.height = (up ? (dvMin)+"px" : (dvMax)+"px");
        }
        document.getElementById("fraAbas").style.height = (up ? (dvMin+55)+"px" :  (dvMax+55)+"px");
    }
    
    function exibicaoAbaPesquisas(tipo){
        divPesquisaDoc.style.display='none';  
        divPesquisa.style.display='none';
        divPesquisaUra.style.display='none';
        abaPesquisaSelecionada = tipo;
        if(tipo==1) {
            dvMin=174;
            abaSelected(btAbaPesquisas, btPesquisa);  
            divEstadoAtendente.style.display='none';
            divPesquisa.style.display='';
            if(abaSelecionada==6) {      
                divPesquisaDoc.style.display='';  
                divPesquisaUra.style.display='none';
                divPesquisa.style.display='none';
                dvMin=279;
            } else if(abaSelecionada==5) {
                divPesquisaUra.style.display='';
                divPesquisaDoc.style.display='none';  
                divPesquisa.style.display='none';
                dvMin=181;
            } else if(abaSelecionada==4) {
                document.getElementById('trPrazo').style.display='none';
                document.getElementById('trEstado').style.display='none';
                document.getElementById('tdOptGrpSel').style.display='none';
                document.getElementById('tdFechamento').style.display='none';
                dvMin=230;
            } else {
                divPesquisaDoc.style.display='none';  
                divPesquisaUra.style.display='none';
                divPesquisa.style.display='';
            } 
        } else {
            if(isCarregadoCTI()) {
                dvMin=216;
                if (abaSelecionada==5) {
                    dvMin=185;
                }
            } else {            
                dvMin=266;
                if (abaSelecionada==5) {
                    dvMin=235;
                }
            }
            abaSelected(btAbaPesquisas, btCTIAtendente);  
            divEstadoAtendente.style.display='';
            divPesquisa.style.display='none';
        }
        ajustaTela();
    }
    
    function exibicaoAbaInBox(tipo){
        var pagina = '';
        abaSelecionada = tipo;
        document.forms[0]['inAbaPesquisa'].value=abaSelecionada;
        //mostra todos os filtros        
        document.getElementById('divPesquisaDoc').style.display='none';  
        document.getElementById('divPesquisaUra').style.display='none';  
        document.getElementById('trPrazo').style.display='';
        document.getElementById('trEstado').style.display='';
        document.getElementById('tdOptGrpSel').style.display='';
        document.getElementById('tdFechamento').style.display='';
        if (tipo==5) {
            divLegendas.style.display="";
            dvMax=284;
        } else {
            divLegendas.style.display="none";
            dvMax=315;
        }
        if(tipo==1){
            abaSelected(btAbaInBox, btTratamento);
            pagina = 'pesquisarProcessos.do';
        } else if(tipo==2){    
            abaSelected(btAbaInBox, btPausa);
            pagina = 'pesquisarProcessos.do';
        } else if(tipo==3){
            abaSelected(btAbaInBox, btEncaminhados);
            pagina = 'pesquisarProcessos.do';    
        } else if(tipo==4){
            abaSelected(btAbaInBox, btMensagens);
            pagina = 'pesquisarMensagens.do';    
        } else if(tipo==5){
            abaSelected(btAbaInBox, btNotas);     
            pagina = 'notaUra.do';    
        } else if(tipo==6){
            abaSelected(btAbaInBox, btAdquirir);
            //Used to keep last search made
            if (document.forms[0].valorPesquisa.value!="") document.forms[0].inPesquisa.value = 1;
            inPesquisaValue = document.forms[0].inPesquisa.value;
            pagina = 'pesquisarProcessosAdq.do?inPesquisa='+inPesquisaValue;
        }
        exibicaoAbaPesquisas(abaPesquisaSelecionada);
        if(top.frameApp.dvAnimarAguarde!=null){
            top.frameApp.startAnimation();
        }
        //Monta o path seleção
        document.forms["0"].target = "fraAbas";
        document.forms["0"].action = pagina;
        document.forms["0"].submit();            
    }

    function submitDisponivel(disp){
        if(top.frameApp.dvAnimarAguarde!=null){
            top.frameApp.startAnimation();
        }
        document.forms[0]['disp'].value = disp;
        //Monta o path seleção
        document.forms["0"].target = "frameEscondido";
        document.forms["0"].action = "analistaDisponivel.do";
        document.forms["0"].submit();
    }
    
    function arvoreContato(){
        stopRefresh();
        document.getElementById("ifrmArvore").src="obterArvoreContato.do";
    }
    
    var controleDetalhe = true;
    function submitAlerta(idAtd) {
    
        stopRefresh();
        desativar_combos();
        controleDetalhe = false;
        if( top.frameApp.dvAnimarAguarde != null ){
            top.frameApp.startAnimation();
        }
        dvAlerta.style.display='';
        document.getElementById("ifrmAlerta").src="alerta.do?idAtendimento="+idAtd;
    }
    
    function validaFormulario(){
        f = document.forms["0"];
        if (f==null) {
            return true;
        }
        statusSel = f.elements["estadoSel"].value;
        dtFecIni = f.elements["dtFechamentoInicio"].value;
        dtFecFim = f.elements["dtFechamentoFim"].value;
        //caso seja limpar
        if( optDataSel==-1 && inFechadoCancelado==-1 ) {
            return true;
        }
        if (inFechadoCancelado==1) {
            if (optDataSel==1) {
                alert("Não utilize Data Fechamento para pesquisar\nprocessos no estado Aberto/Em Tratamento/Em Retorno!");
                return false;
            }
                }else if(inFechadoCancelado==0) {
            if (optDataSel!=1) {
                alert("Para pesquisa de processos Cancelados ou Fechados,\npreencha os campos de Data de Fechamento!");
                return false;
            }
        }
        if (optDataSel==1) {
            if (dtFecIni == "" || dtFecFim == "") {
                alert("Para pesquisa de processos Cancelados ou Fechados\né necessário preencher Data Início e Fim!");
                return false;
            }
        }
        //faz verificaçao de datas
        if(!verificaDatas()) {
            return false;
        }
        if(abaSelecionada==1||abaSelecionada==2||abaSelecionada==3){
            documento = document.forms[0]['documento'];
            if(document.forms[0]['optDocSel'][0].checked){
            //CPF
                checaCPF(documento);
                if(documento.value == ""){
                    return true;
                }else if(!validaCPF(documento.value)){
                    alert("CPF invalido, favor corrigir!");
                    return false;
                }
            }
            else{
                //CNPJ
                checaCNPJ(documento);
                if(documento.value == ""){
                    return true;
                }else if(!validaCNPJ(documento.value)){
                    alert("CNPJ invalido, favor corrigir!");
                    return false;
                }
            }
        }
        if(abaSelecionada==6){
            obj = document.forms[0]['valorPesquisa'];
            if(obj.form.tipoPesquisa.selectedIndex!=0){
                tipo = obj.form.tipoPesquisa[obj.form.tipoPesquisa.selectedIndex].value;
                switch(tipo){
                    case '1':			//Celular
                        if(obj.value==""){
                            alert('Linha vazia, favor corrigir!');
                            return false;
                        }
                    break;
                    case  '3': 		//Nome do Cliente
                        if(obj.value!=""){
                            alert('Nome vazio, favor corrigir!');
                            return false;
                        }
                    break;
                    case '2': 		//Conta
                        if(obj.value==""){
                            alert('Conta vazia, favor corrigir!');
                            return false;
                        }
                    break;
                    case 'CPF': 		//CPF
                        if(!validaCPF(obj.value)){
                            alert('CPF incorreto, favor corrigir!');
                            return false;
                        }
                    break;
                    
                    case 'CNPJ': 		//CNPJ
                        if(!validaCNPJ(obj.value)){
                            alert('CNPJ incorreto, favor corrigir!');
                            return false;
                        }
                    break;
                }
            }   
        }
        return true;
    }
    
    function verificaDatas() {
        f = document.forms["0"];  
        dtFecIni=f.elements["dtFechamentoInicio"].value;
        dtFecFim=f.elements["dtFechamentoFim"].value;
        dtAbeIni=f.elements["dtAberturaInicio"].value;
        dtAbeFim=f.elements["dtAberturaFim"].value;
        if(dtAbeIni.length > 0) {
                    if(!validaData(dtAbeIni)){
                alert('Data de abertura inicial deve estar no formato DD/MM/AAAA.');
                return false;
            }
        }
        if(dtAbeFim.length > 0) {
            if(!validaData(dtAbeFim)) {
                alert('Data de abertura final deve estar no formato DD/MM/AAAA.');
                return false;
            }
        }
        if(dtFecIni.length > 0) {
            if(!validaData(dtFecIni)) {
                alert('Data de fechamento inicial deve estar no formato DD/MM/AAAA.');
                return false;
            }
        }
        if(dtFecFim.length > 0) {
            if(!validaData(dtFecFim)) {
                alert('Data de fechamento final deve estar no formato DD/MM/AAAA.');
                return false;
            }
        }
        if (dtFecFim.length>0 && dtFecIni.length>0) {
            if (!validaDataFinal(dtFecIni,dtFecFim)) {
                alert("Data final de Fechamento deve ser posterior a Data incial!");
                return false;
            }
            if (!valida1mes(f.elements["dtFechamentoInicio"],f.elements["dtFechamentoFim"])) {
                alert("Intervalo de Datas Fechamento maior que 30 dias!\n");
                return false;
            }
        }
        if (dtAbeFim.length>0 && dtAbeIni.length>0) {
            if (!validaDataFinal(dtAbeIni, dtAbeFim)) {
                alert("Data final de Abertura deve ser posterior a Data inicial!");
                return false;
            }
            if (!valida1mes(f.elements["dtAberturaInicio"],f.elements["dtAberturaFim"])) {
                alert("Intervalo de Datas Abertura maior que 30 dias!\n");
                return false;
            }
        }
        return true;
    }    
    
    function valida1mes(objInicial,objFinal){
        objSomaDias = document.all["somaDias"];
        somaDiasData(objInicial,objSomaDias,30);
        resposta = validaDataFinal(objFinal.value,objSomaDias.value);
        return resposta;
    }
    
    function submitPesquisar(){
        //se pesquisa por processos fechados mostrar na aba encaminhados
        //if(document.forms[0]['optGrpSel'][1].checked){
        //    abaSelecionada = 3;
        //}
        if(validaFormulario()){
            document.forms[0]['inAbaPesquisa'].value=abaSelecionada;
            exibicaoAbaInBox(abaSelecionada);
                }else{
                    return false;
        }
        }
        
    function submitPesquisarAdquiridos(){
        //se pesquisa por processos fechados mostrar na aba encaminhados
        //if(document.forms[0]['optGrpSel'][1].checked){
        //    abaSelecionada = 3;
        //}
        if(validaFormulario()){
            if (document.forms[0].valorPesquisa.value!="" && document.forms[0].tipoPesquisa.value==""){
                alert('Selecione um tipo de pesquisa.');
                return false;
            }else if (document.forms[0].tipoPesquisa.value!="" && document.forms[0].valorPesquisa.value==""){
                if (document.forms[0].tipoPesquisa.value == 'RAZAO' || document.forms[0].tipoPesquisa.value == 'NOME') {      
                    pesquisaNome();
                    return false;
                }else{
                    alert('Digite um valor para a pesquisa.');
                    return false;
                }
            }
            document.forms[0]['inAbaPesquisa'].value=abaSelecionada;
            exibicaoAbaInBox(abaSelecionada);
                }else{
            return false;
        }
    }
    
    function submitPesquisarAdq(){
        obj = document.forms[0]['valorPesquisa'];
        if(obj.form.tipoPesquisa.selectedIndex!=0){
            tipo = obj.form.tipoPesquisa[obj.form.tipoPesquisa.selectedIndex].value;
            switch(tipo){
                case '1':			//Celular
                    formatPhoneNumberObj(obj);
                break;
                case  '3': 		//Nome do Cliente
                    if(!validaNome(obj)){
                        alert('Nome vazio!');
                        return false;
                    }
                break;
                case '2': 		//Conta
                    checaInteiro(obj);
                break;
                case 'CPF': 		//CPF
                    if(!validaCPF(obj)){
                        alert('CPF incorreto!');
                        return false;
                    }
                break;
                case 'CNPJ': 		//CNPJ
                    if(!validaCNPJ(obj)){
                        alert('CNPJ incorreto!');
                        return false;
                    }
                break;
            }
        }   
        submitPesquisar();     
    }
    
    function selecionaMotivo(){
    /*
        if(document.forms[0]['acaoSel'].selectedIndex==2){
            document.forms[0]['motivoSel'].disabled=false;   
                }else{
            document.forms[0]['motivoSel'].selectedIndex=0;
            document.forms[0]['motivoSel'].disabled=true;     
        }
        */
    }
    
    function linhaSel( linha ) {
        if(!controleDetalhe)  {
            controleDetalhe=true;return;
        }
                var idAtd = linha.cells(1).childNodes[0].childNodes[1].value;
        fila = escape("/FrontOfficeWeb/workflow/AtendimentoInBoxCRI/begin.do");    
        top.frameApp.location="../AtendimentoDetalhe/begin.do?idAtendimento=" + idAtd + "&fila=" + fila + "&inCRI=1";
    }
    
    function limparPesquisa(origem) { 
        f = document.forms[0]; 
        //Captura valores da Pesquisa de Processos Adquiridos da Fila
        filaTipoPesquisa  = f.tipoPesquisa.value;
        filaValorPesquisa = f.valorPesquisa.value;
        if (origem=="INBOX"){
            f.reset();
            f.elements["subEstadoSel"].value='-1';
            inFechadoCancelado=-1;
            optDataSel=0;
            f.tipoPesquisa.value = filaTipoPesquisa;
            f.valorPesquisa.value = filaValorPesquisa;
        }else if (origem=="FILA"){
            f.tipoPesquisa.value = "";
            f.valorPesquisa.value = "";
        }
        submitPesquisar();
    }
    
    function habilitaData(valor) {
        f = document.forms[0];   
        if(valor == 0) {
            f.elements["dtFechamentoInicio"].value = "";
            f.elements["dtFechamentoFim"].value = "";
            f.elements["dtFechamentoInicio"].disabled = true;
            f.elements["dtFechamentoFim"].disabled = true;
            f.elements["dtAberturaInicio"].disabled = false;
            f.elements["dtAberturaFim"].disabled = false;
            f.elements["imgCalendDtFecIni"].style.display = 'none';
            f.elements["imgCalendDtFecFim"].style.display = 'none';
            f.elements["imgCalendDtAbIni"].style.display = '';
            f.elements["imgCalendDtAbFim"].style.display = '';
                }else{
            f.elements["dtAberturaInicio"].value = "";
            f.elements["dtAberturaFim"].value = "";
            f.elements["dtFechamentoInicio"].disabled = false;
            f.elements["dtFechamentoFim"].disabled = false;
            f.elements["dtAberturaInicio"].disabled = true;
            f.elements["dtAberturaFim"].disabled = true;
            f.elements["imgCalendDtAbIni"].style.display = 'none';
            f.elements["imgCalendDtAbFim"].style.display = 'none';
            f.elements["imgCalendDtFecIni"].style.display = '';
            f.elements["imgCalendDtFecFim"].style.display = '';
        }
        optDataSel=valor;
    }
    
    function detNota(idAtendimento){
        if(!controleDetalhe)  {
            controleDetalhe=true;
            return;
        }
        stopRefresh();
        document.forms[0]['idAtendimentoNota'].value = idAtendimento;
        dvDetalheNota.style.display = '';
        document.forms[0].action     = '/FrontOfficeWeb/workflow/NotasURA/lerNotasURADetalhe.do';
        document.forms[0].target     = 'ifrmDetalheNota';
        document.forms[0].submit();
    }
    
    function alteraNota(idAtendimentoNota){
        if(!controleDetalhe)  {
            controleDetalhe=true;
            return;
        }
        stopRefresh();
        document.forms[0]['idAtendimentoNota'].value = idAtendimentoNota;
        dvEditarNota.style.display = '';
        document.forms[0].action     = '/FrontOfficeWeb/workflow/NotasURA/editarNotaURA.do';
        document.forms[0].target     = 'ifrmAlteraNota';
        document.forms[0].submit();    
    }
    
    function limpaDoc(){
        document.forms[0]['documento'].value='';
    }
    
    function limpaTP(){
        document.forms[0]['valorPesquisa'].value='';
    }
    
    function pesquisaNome(){
        if (document.forms[0].tipoPesquisa.value == 'RAZAO' || document.forms[0].tipoPesquisa.value == 'NOME') {      
            stopRefresh();
            dvPesquisaNome.style.display = '';            
            document.forms[0].action     = 'irPesquisaNome.do';
            document.forms[0].target     = 'ifrmPesqNome';
            document.forms[0].submit();       
        }              
     }  
    
    function formataCampo(obj){
        if(obj.form.tipoPesquisa.selectedIndex==0){
            return;
        }
        qtdeCaracteres = 0;
        tipo = obj.form.tipoPesquisa[obj.form.tipoPesquisa.selectedIndex].value;
        switch(tipo){
            case '':           // Celular
                qtdeCaracteres = 25;
            break;
            case '1':           // Celular
                maskPhoneNumberObj(obj);
                qtdeCaracteres = 11;
            break;
            case '2':           // Conta
                checaInteiro(obj);
                qtdeCaracteres = 100;
            break;
            case  'NOME': 		// Nome do Cliente
                qtdeCaracteres = 0;
            break;
            case 'CPF': 		// CPF
                checaCPF(obj);
                qtdeCaracteres = 14;
            break;
            case 'CNPJ': 		// CNPJ
                checaCNPJ(obj);
                qtdeCaracteres = 18;
            break;
            case 'RNE': 		// RNE
                checaInteiro(obj);
                qtdeCaracteres = 25;
            break; 
            case 'PASS': 		// Passaporte
                qtdeCaracteres = 25;
            break;
            case 'IE':          // IE
                checaInteiro(obj);
                qtdeCaracteres = 25;
            break;
            case 'RG':          // RG
            case 'CN':          // Certidao de Nascimento
            case 'IM':          // IM
                checaInteiro(obj);
                qtdeCaracteres = 25;
            break;
        }
        obj.maxLength = qtdeCaracteres;
    }
  
    function validaDataOnBlur(data){
        if(data.value == '')
            return false;
        if(!validaData(data.value)){
            data.value = '';
            data.focus();
            alert("Data inválida");
        }
    }
    
    function escolheMascara(obj){    
        if(document.forms[0]['optDocSel'][0].checked){
            //CPF
            checaCPF(obj);
            qtdeCaracteres = 14;
                }else{
            //CNPJ
            checaCNPJ(obj);
            qtdeCaracteres = 18;        
        }
        obj.maxLength = qtdeCaracteres;    
    }
    
    /*
    * Javascript para lay-out da tela
    *
    */
    //Controle max min
    var up = true;
    dvMax=315;
    dvMin=266;
    dvMinOld=dvMin;
    
    function resizeMaxMin() {
        tbConfiguracoes.style.display=(up ? "none" : "");
        tbTrat =  fraAbas.document.all["tbResultado_body"];
        if (tbTrat != null) {
            tbTrat.parentElement.style.height = (up ? (dvMax)+"px" : (dvMin)+"px");
        }        
        document.getElementById("fraAbas").style.height = (up ? (dvMax+55)+"px" : (dvMin+55)+"px");
        document.getElementById("btMaxMin").src = "../../resources/images/" + (up ? "bt_lupa_aba_down.gif" : "bt_lupa_aba.gif");
        up = !up;
    }
    
            function isCarregadoCTI(){
                if(top.frameCTI.isCarregadoOCX()){
                    if(top.frameCTI.isAgenteLogado()){
                return true;
            }
        }
        return false;
    }

    /*
    * Fim Javascript para lay-out da tela
    */
    function desativar_combos() {
        if (frmsState == null) {
            forms=document.forms;
            frmsState=new Array(forms.length);
            for (i=0;i<forms.length;i++) {
                el=forms[i].elements;
                elState=new Array(el.length);
                frmsState[i]=elState;
                for(j=0;j<el.length;j++){elState[j]=el[j].disabled;el[j].disabled=true;}
            }
        }
        return;
    }

    function ativar_combos() {
        if (frmsState != null) {
            forms=document.forms;
            for (i=0;i<forms.length;i++) {
                el=forms[i].elements;
                elState=frmsState[i];
                frmsState[i]=elState;
                for(j=0;j<el.length;j++){el[j].disabled=elState[j];}
            }
        }
        frmsState = null;
        return;
    }
    
    function testaEnter() {
        if (window.event.keyCode==13) {
            return false;
        } else {
            return true;
        }
    }
    
    function resetar_valores() {
        f=document.forms["formInboxCRI"];
        f.reset();
        limpaTP();
        return;
    }
</script>
<SCRIPT FOR=window EVENT=onload LANGUAGE="JScript">
    exibicaoAbaPesquisas(0);
    submitPesquisar();
</SCRIPT>
    </netui-template:section>
    <netui-template:section name="bodySection">
    <acesso:controlHiddenItem nomeIdentificador="icri_icri_verpagina">
        <!--APLICAÇÃO->MENU-->
        <jsp:include page="/resources/menus/MenuPrincipal.jsp" />
        <!--APLICACAO-->
        <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
            <vivo:sessao id="qdMain" height="470" width="790" label="Vivo Net >> Workflow" operacoes="Processos In-Box CRI" scroll="no">
                <form id="formInboxCRI" target="" name="formInboxCRI" onkeypress="return testaEnter();">
                    <html:hidden property="idContato" name="form"></html:hidden>
                    <html:hidden property="inDisponivelWF" name="form"></html:hidden>
                    <html:hidden property="inAbaPesquisa" name="form"></html:hidden>
                    <html:hidden property="inCliqueAba" name="form"></html:hidden>
                    <input type="hidden" name="tipoDocumento">
                    <input type="hidden" name="somaDias">
                    <input type="hidden" name="disp">
                    <input type="hidden" name="idPessoa">
                    <input type="hidden" name="inPesquisa" value="0">
                    <input type="hidden" name="idAtendimentoNota">
                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
                    <table width="100%" cellpadding="0" cellspacing="0" border="0">
                        <tr>
                            <td>
                                <vivo:abaGrupo id="btAbaPesquisas" width="250px" height="10px" styleClass="abatexto">
                                    <vivo:abaItem id="btCTIAtendente" onclick="exibicaoAbaPesquisas(0);" value="Estado Atendente" select="S"/>
                                    <vivo:abaItem id="btPesquisa" onclick="exibicaoAbaPesquisas(1);" value="Pesquisa"/>
                                </vivo:abaGrupo>
                            </td>
                            <td>
                                <img id="btMaxMin" name="btMaxMin" title="Click para minimizar/maximizar os parâmetros de pesquisa" src="<%=request.getContextPath()%>/resources/images/bt_lupa_aba.gif" style="cursor:pointer" onclick="resizeMaxMin();">
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" id="tbConfiguracoes">
                                <div id="divEstadoAtendente">
                                    <table class="tbl_bggray" width="780" cellspacing="2" cellpadding="3" border="0">
                                        <tr>
                                            <td height="40" width="30px">Login:</td>
                                            <td width="160px"><bean:write name="form" property="nmLoginUsuario"/></td>
                                            <td>Status:</td>
                                            <td>
                                                <bean:define id="inDisponivelWF" name="form" property="inDisponivelWF"/>
                                                <table cellspacing="1" cellpadding="1" border="0">
                                                    <tr>
                                                        <td>
                                                        <!--acesso:controlHiddenItem nomeIdentificador="wor_wb_disponivel"-->
                                                        <img id="btDisponivel" style='border:none' onClick="submitDisponivel('1');" src='<%="/FrontOfficeWeb/resources/images/"+(inDisponivelWF.equals("1") ? "bt_disponivel_disponivel.gif" : "bt_disponivel_indisponivel.gif")%>' />
                                                        <!--/acesso:controlHiddenItem-->
                                                        </td>
                                                        <td>
                                                        <!--acesso:controlHiddenItem nomeIdentificador="wor_wb_indisponivel"-->
                                                        <img id="btIndisponivel" style='border:none' onClick="submitDisponivel('0');" src='<%="/FrontOfficeWeb/resources/images/"+(inDisponivelWF.equals("0") ? "bt_indisponivel_disponivel.gif" : "bt_indisponivel_indisponive.gif")%>' />
                                                        <!--/acesso:controlHiddenItem-->
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                        <tr>
                                        <td id="tdBarraCTI" align="right" colspan="4">
                                            <table class="tbl_bggray" width="765" cellspacing="2" cellpadding="3" border="0">
                                                <tr>
                                                    <td height="40" width="15%">Campanha:</td>
                                                    <td width="40%">
                                                        <bean:define id="retorno" name="form" property="retornoWFCTIVO"/>
                                                        <html:select name="form" property="retorno" title="numCampanhaSel" style="width=150px" onchange="trocarCampanha(this.value)">
                                                            <html:options collection="retorno" property="idRetornoWFCTI" labelProperty="dsRetornoWFCTI" />
                                                        </html:select></td>
                                                    <td width="45%" align="right"><img id="imgDisponibilidade" src="../../resources/images/icon_atend_disponivel.gif" onclick="mudaDisponibilidade()" style='cursor:pointer' border="0"></td>
                                                </tr>
                                            </table>
                                        </td>
                                        </tr>
                                        <script>
                                            mostraBarraCTI();
                                        </script>  
                                    </table>
                                </div>
                                <div id="divPesquisaUra" style="display:none;">
                                    <table class="tbl_bggray" width="780" cellspacing="2" cellpadding="3" border="0">
                                        <tr>
                                            <td>
                                                <table border="0">
                                                    <tr>
                                                        <td>Cliente Contatado</td>
                                                        <td>
                                                            <html:radio name="form" value="0" property="optCliCon" styleClass="radio">Não</html:radio>                                                            
                                                            <html:radio name="form" value="1" property="optCliCon" styleClass="radio">Sim</html:radio>
                                                            <html:radio name="form" value="-1" property="optCliCon" styleClass="radio">Ver Todos</html:radio>                                                            
                                                        </td>
                                                        <td align="left">Açao de Retorno</td>
                                                        <td>
                                                            <bean:define id="wFAcaoVO" name="form" property="acoesVO" />
                                                            <html:select name="form" property="acaoSel" title="acaoSel" style="width=250px" onchange="selecionaMotivo();">
                                                                <html:option value="" key="acaoSel">&nbsp;</html:option>
                                                                <html:options collection="wFAcaoVO" property="idAtividade" labelProperty="dsAtividade"/> 
                                                            </html:select>
                                                        </td>
                                                    </tr>     
                                                    <tr>
                                                        <td>Cliente Transferido</td>
                                                        <td>
                                                            <html:radio name="form" value="0" property="optCliTra" styleClass="radio">Não</html:radio>
                                                            <html:radio name="form" value="1" property="optCliTra" styleClass="radio">Sim</html:radio>
                                                            <html:radio name="form" value="-1" property="optCliTra" styleClass="radio">Ver Todos</html:radio>
                                                        </td>
                                                        <td align="left">Motivo</td>
                                                        <td>
                                                            <bean:define id="wFMotivosVO" name="form" property="motivosVO" />
                                                            <html:select name="form" property="motivoSel" title="motivoSel" style="width=250px" disabled="false">
                                                                <html:option value="" key="motivoSel">&nbsp;</html:option>
                                                                <html:options collection="wFMotivosVO" property="idMotivo" labelProperty="dsMotivo"/> 
                                                            </html:select>
                                                        </td>
                                                    </tr>   
                                                    <tr>
                                                        <td>Abertura de</td>
                                                        <td align="left">
                                                            <html:text property="dataIni" name="form" size="10" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtAbIniUra" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dataIni', '%d/%m/%Y');">&nbsp;Até&nbsp;
                                                            <html:text property="dataFim" name="form" size="10" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtAbFimUra" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dataFim', '%d/%m/%Y');">
                                                        </td>
                                                    </tr> 
                                                    <tr>
                                                        <td colspan="4" align="right">     
                                                        <acesso:controlHiddenItem nomeIdentificador="icri_icri_pesquisar">
                                                            <%--<img id="btPesquisar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="border:none" onClick="submitPesquisar();"/>--%>
                                                            <img id="btPesquisar" src="../../resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif" value="Pesquisar" style="border:none" onClick="document.forms[0].inCliqueAba.value=0;submitPesquisar(); return false;"/>
                                                        </acesso:controlHiddenItem>
                                                            <%--<img src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'" id="btLimpar" style="border:none" onClick="limparPesquisa();"/>--%>
                                                            <img id="btLimpar" src="../../resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" value="Limpar" style="border:none" onClick="limparPesquisa('INBOX');"/>
                                                        </td>                                           
                                                    </tr>
                                                </table>   
                                            </td>                                           
                                        </tr>
                                    </table>
                                </div>
                                <div id="divPesquisaDoc" style="display:none;">
                                    <table class="tbl_bggray" width="780" cellspacing="5" cellpadding="3" border="0">
                                        <tr valign="middle">
                                            <td width=220> 
                                                Pesquisar por:
                                                <select name="tipoPesquisa" id="tipoPesquisa"  style="z-index:1;" onChange="limpaTP(); pesquisaNome();">
                                                    <option label="" value="">&nbsp;</option>
                                                    <option label="celular" value="1">N&uacute;mero da linha</option>
                                                    <option label="conta" value="2">N&uacute;mero da Conta</option>
                                                    <option label="nome" value="NOME">Nome do Cliente</option>
                                                    <option label="CPF" value="CPF">CPF</option>
                                                    <option label="CNPJ" value="CNPJ">CNPJ</option>
                                                    <option label="RNE" value="RNE">RNE</option>
                                                    <option label="Passaporte" value="PASS">Passaporte</option>
                                                    <option label="IE" value="IE">Inscri&ccedil;&atilde;o Estadual</option>
                                                    <option label="razao" value="RAZAO">Razão Social</option>
                                                    <option label="CN" value="CN">Certid&atilde;o de Nascimento</option>
                                                    <option label="RG" value="RG">RG</option>
                                                    <option label="IM" value="IM">Inscri&ccedil;&atilde;o Municipal</option>
                                                </select>
                                            </td>
                                            <td align="left" width="255">
                                                <html:text property="valorPesquisa" style="width=250px" name="form" onkeyup="formataCampo(this);"/>
                                            </td>                                                    
                                            <td align="left">
                                                <%--
                                                <img id="btPesquisar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="border:none" onClick="submitPesquisar();"/>
                                                <img src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'" id="btLimpar" style="border:none" onClick="limparPesquisa();"/>
                                                --%>
                                                <img id="btPesquisar" src="../../resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif" value="Pesquisar" style="border:none" onClick="document.forms[0].inPesquisa.value=1;document.forms[0].inCliqueAba.value=0;submitPesquisarAdquiridos(); return false;"/>
                                                <img id="btLimpar" src="../../resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" value="Limpar" style="border:none" onClick="limparPesquisa('FILA');"/>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                                <div id="divPesquisa" style="display:none;">
                                    <table class="tbl_bggray" width="780" cellspacing="2" cellpadding="3" border="0">
                                        <tr id="trPrazo">
                                            <td>Prazo</td>
                                            <td>
                                                <bean:define id="alertasVO" name="form" property="alertasVO" />
                                                <html:select name="form" property="prazoSel" title="prazoSel" style="width=250px">
                                                    <html:option value="" key="prazoSel">&nbsp;</html:option>
                                                    <html:options collection="alertasVO" property="idAlerta" labelProperty="dsAlerta"/> 
                                                </html:select>
                                            </td>
                                            <td>
                                                <html:radio name="form" value="CPF" property="optDocSel" onclick="limpaDoc();" styleClass="radio">CPF</html:radio>
                                                <html:radio name="form" value="CNPJ" property="optDocSel" onclick="limpaDoc();" styleClass="radio">CNPJ</html:radio>
                                            </td>
                                            <td width="15%">
                                                <html:text property="documento" name="form" onkeydown="escolheMascara(this);" onkeyup="escolheMascara(this);"/>
                                            </td>
                                        </tr>
                                        <tr id="trEstado">
                                            <td width="10%">Estado</td>
                                            <td width="40%">
                                                <bean:define id="estadosVO" name="form" property="estadosVO.WFEstadoVOArray" />
                                                <html:select name="form" property="estadoSel" title="estadoSel" style="width=250px" onchange="loadSubEstados(this.value);">
                                                    <html:option value="" key="estadoSel">&nbsp;</html:option>
                                                    <html:options collection="estadosVO" property="idEstado" labelProperty="dsEstado" /> 
                                                </html:select>
                                            </td>
                                            <td>&nbsp;&nbsp;Sub-Estado</td>
                                            <td id="tdSubEstados">
                                                <select name="subEstadoSel" style="width=250px" title="subEstadoSel">
                                                    <option value="">&nbsp;</option>
                                                </select>
                                            </td> 
                                            <script>
                                                inFechadoCancelado=-1;
                                                var arrCombos=new Array();
                                                <logic:iterate id="estadosVO" name="form" property="estadosVO.WFEstadoVOArray" indexId="idxEstados">
                                                    var combo = document.createElement("<SELECT NAME='subEstadoSel'>");
                                                    combo.idEstado='<bean:write name="estadosVO" property="idEstado"/>';
                                                    combo.inFechadoCancelado='<bean:write name="estadosVO" property="inFiltro"/>';
                                                    <logic:iterate id="subEstadosVO" name="estadosVO" property="WFSubEstadoVOArray" indexId="idxSubEstados">
                                                        var opt = document.createElement("OPTION");
                                                        opt.value='<bean:write name="subEstadosVO" property="idSubEstado"/>';
                                                        opt.text='<bean:write name="subEstadosVO" property="dsSubEstado"/>';
                                                        combo.add(opt);
                                                    </logic:iterate>
                                                    arrCombos[arrCombos.length]=combo;
                                                </logic:iterate>
                                                function loadSubEstados(id) { 
                                                    if(id == ""){
                                                        tdSubEstados.innerHTML="<select name='subEstadoSel' style='width:250px'></select>";
                                                        inFechadoCancelado=-1;
                                                        return;
                                                    } 
                                                    for (var i=0;i<arrCombos.length;i++) {
                                                        if (arrCombos[i].idEstado == id) {
                                                            tdSubEstados.innerHTML=arrCombos[i].outerHTML;
                                                            tdSubEstados.children[0].name='subEstadoSel';
                                                            tdSubEstados.children[0].style.width='250px';
                                                            inFechadoCancelado=arrCombos[i].inFechadoCancelado;
                                                            break;
                                                        }
                                                    } 
                                                }
                                            </script>
                                        </tr>
                                        <tr>
                                            <td>Protocolo</td>
                                            <td width="43%"><html:text property="nrProtocolo" name="form" maxlength="30" style="width:100px" onkeyup="Formatar(this.value, this.form.name, this.name, 'numero');" onchange="Formatar(this.value, this.form.name, this.name, 'numero');" /></td>
                                            <td>
                                                <html:radio name="form" value="0" property="optGrpSel" styleClass="radio" onclick="habilitaData(this.value);">Abertura de</html:radio>
                                            </td>
                                            <td width="30%" align="left">
                                                <html:text property="dtAberturaInicio" name="form" size="10" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtAbIni" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtAberturaInicio', '%d/%m/%Y');">&nbsp;Até&nbsp;
                                                <html:text property="dtAberturaFim" name="form" size="10" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtAbFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtAberturaFim', '%d/%m/%Y');">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td>Linha</td>
                                            <td><html:text property="nrLinha" name="form" maxlength="11" style="width:100px" onblur="formatPhoneNumberObj(this)"/></td>
                                            <td id="tdOptGrpSel">
                                                <html:radio name="form" value="1" property="optGrpSel" styleClass="radio" onclick="habilitaData(this.value);">Fechamento de</html:radio>
                                            </td>
                                            <td id="tdFechamento">
                                                <html:text property="dtFechamentoInicio" name="form" size="10" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtFecIni" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtFechamentoInicio', '%d/%m/%Y');">&nbsp;Até&nbsp;
                                                <html:text property="dtFechamentoFim" name="form" size="10" maxlength="10" onblur="validaDataOnBlur(this);" onkeyup ="Formatar(this.value, this.form.name, this.name, 'data');" onchange="Formatar(this.value, this.form.name, this.name, 'data');"/><img id="imgCalendDtFecFim" src="<%=request.getContextPath()%>\resources\images\calendario.gif" style="cursor:pointer;" title="Calendário" onclick="return showCalendar('dtFechamentoFim', '%d/%m/%Y');">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td width="12%">Contato</td>
                                            <td colspan="3">
                                                <table width="100%" cellspacing="0" cellpadding="0" border="0">
                                                    <tr>
                                                        <td width="310"><html:text property="textoContato" name="form" maxlength="255" style="width:300px" readonly="true"/></td>
                                                        <td width="30"><img id="imgLupa" src="<%=request.getContextPath()%>/resources/images/lupa_bg_transp.gif" onclick="arvoreContato()" style='cursor:pointer' border="0"></td>
                                                        <td width="110">
                                                            <%--<img id="btPesquisar" src="/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif'" style="border:none" onClick="submitPesquisar();"/>--%>
                                                            <img id="btPesquisar" src="../../resources/images/bt_pesquisar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_pesquisar_over.gif" value="Pesquisar" style="border:none" onClick="document.forms[0].inCliqueAba.value=0;submitPesquisar(); return false;"/>                                                            
                                                        </td>
                                                        <td>
                                                            <%--<img src="/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif" onMouseOut="this.src='/FrontOfficeWeb/resources/images/bt_limpar_nrml.gif'" onMouseOver="this.src='/FrontOfficeWeb/resources/images/bt_limpar_over.gif'" id="btLimpar" style="border:none" onClick="limparPesquisa();"/>--%>
                                                            <img id="btLimpar" src="../../resources/images/bt_limpar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_limpar_over.gif" value="Limpar" style="border:none" onClick="limparPesquisa('INBOX');"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </div>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2">
                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
                                <!-- Pesquisa -->                                
                                <vivo:abaGrupo id="btAbaInBox" width="700px" height="10px" styleClass="abatexto" >
                                    <vivo:abaItem id="btTratamento" onclick="document.forms[0].inCliqueAba.value=1;exibicaoAbaInBox(1);" value="Meus Processos" select="S"/>
                                    <vivo:abaItem id="btPausa" onclick="document.forms[0].inCliqueAba.value=1;exibicaoAbaInBox(2);" value="Processos Em Pausa"/>
                                    <vivo:abaItem id="btEncaminhados" onclick="document.forms[0].inCliqueAba.value=1;exibicaoAbaInBox(3);" value="Processos Encaminhados"/>
                                    <vivo:abaItem id="btMensagens" onclick="document.forms[0].inCliqueAba.value=1;exibicaoAbaInBox(4);" value="Mensagens"/>
                                    <vivo:abaItem id="btNotas" onclick="document.forms[0].inCliqueAba.value=1;exibicaoAbaInBox(5);" value="Notas URA"/>
                                    <vivo:abaItem id="btAdquirir" onclick="document.forms[0].inCliqueAba.value=1;exibicaoAbaInBox(6);" value="Adquirir Processos da Fila"/>
                                </vivo:abaGrupo> 
                                <div id="divProcessos">
                                    <iframe id="fraAbas" name="fraAbas" src="" width="785" height="320" frameborder="0" scrolling="no"></iframe>
                                </div>
                                <div id="divLegendas" style="display:none;" >
                                    <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="3"></div>
                                    <table style="background-color:#ededed;border:1px solid #adadad;" width="780" align="left">
                                        <tr>
                                            <td width="100"><b>Legendas:</b></td>
                                            <td width="150"><img align="middle" src="/FrontOfficeWeb/resources/images/bt_icon_alterar.gif" border="0">&nbsp;Editar Notas</td>
                                            <td width="150"><img align="middle" src="/FrontOfficeWeb/resources/images/bt_icon_propried.gif" border="0">&nbsp;Editar Parâmetros</td>
                                        </tr>
                                    </table>                                
                                </div>
                            </td>
                        </tr>
                        <tr  valign="bottom">
                            <td colspan="2">
                                <div><img src="/FrontOfficeWeb/resources/images/transp.gif" border="0" width="1" height="5"></div>
                                <vivo:quadro id="qdOperacoes" height="35" width="780" label="Ação" scroll="N">
                                <table border="0" width="100%">
                                <tr>
                                    <td width="50%" align="left"><img hspace="10" vspace="6" id="btVoltar" src="/FrontOfficeWeb/resources/images/bt_voltar_nrml.gif" rolloverImage="/FrontOfficeWeb/resources/images/bt_voltar_over.gif" onClick="window.top.location='/FrontOfficeWeb/index.jsp'; return false" style="border:none;"/></td>                                    
                                </tr>
                                </table>
                                </vivo:quadro>  
                            </td>
                        </tr>
                    </table>                    
                </form>
                <script>
                    if(isCarregadoCTI()){
                        dvMin=216;
                        document.getElementById("fraAbas").style.height = ((dvMin+55)+"px");
                    }
                </script>
            </vivo:sessao>
            <iframe scrolling="yes" style="visibility:hidden;" name="iframeUsuario" height="1px" width="1px"></iframe>
            <iframe scrolling="yes" style="visibility:hidden;" name="frameEscondido" id="frameEscondido" height="1px" width="1px"></iframe>
            <vivo:quadroFlutuante id="dvPesquisaNome" idIframe="ifrmPesqNome"           width="580" height="80"  spacesTop="65" spacesLeft="75" display="none" url="<%=request.getContextPath()%>" label="Pesquisa de Clientes" scroll="no" onclick="resetar_valores();ativar_combos();restartRefresh();"/>
            <vivo:quadroFlutuante id="dvArvore" idIframe="ifrmArvore" width="720" height="220" spacesTop="190" spacesLeft="40" display="none" url="<%=request.getContextPath()%>" label="Árvore de Contato" scroll="auto" onclick="ativar_combos();restartRefresh();"/>
            <vivo:quadroFlutuante id="dvResultados"   idIframe="ifrmPesqNomeResultados" width="750" height="390" spacesTop="0"  spacesLeft="8"  display="none" url="<%=request.getContextPath()%>" label="Pesquisa de Clientes" scroll="no" onclick="ativar_combos();restartRefresh();resetar_valores();"/>
            <vivo:quadroFlutuante id="dvAlerta" idIframe="ifrmAlerta" width="443" height="220" spacesTop="210" spacesLeft="175" display="none" url="<%=request.getContextPath()%>" label="Alertas" onclick="ativar_combos();restartRefresh();"/>    
            <vivo:quadroFlutuante id="dvDetalheNota" idIframe="ifrmDetalheNota" width="610" height="425" spacesTop="110" spacesLeft="75" display="none" url="<%=request.getContextPath()%>" label="Detalhe Notas" onclick="ifrmDetalheNota.fecharInbox();ativar_combos();restartRefresh();"/>
            <vivo:quadroFlutuante id="dvEditarNota" idIframe="ifrmAlteraNota" width="610" height="425" spacesTop="110" spacesLeft="75" display="none" url="<%=request.getContextPath()%>" label="Editar Notas" onclick="ifrmAlteraNota.fecharInbox();ativar_combos();restartRefresh();"/>        
        </acesso:controlHiddenItem>
    </netui-template:section>
</netui-template:template>
