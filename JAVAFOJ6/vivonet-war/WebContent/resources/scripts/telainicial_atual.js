/* ------------------ VERIFICA SE O NÚMERO DO CELULAR É VIVO ------------------ */

function validaPesquisa(){
    tipo = document.forms[0].pesquisa.selectedIndex;
    obj = document.getElementById('valorPesquisa');
    switch(tipo){
        case 0://Celular
            checaTelefoneNew(obj);
            if(obj.value == ""){
                alert("Favor preencher uma Linha!");
            }else if(obj.value.length < 12){
                alert("Linha invalida, favor corrigir!");
            }else{
                abrePesquisa();
            }
            break;
        case 1: //Nome do Cliente
            if(obj.value == ""){
                alert("Favor preencher um Nome!");
            }else if(!validaNome(obj.value)){
                alert("Favor preencher Nome e Sobrenome!");
            }else{
                abrePesquisa();
            }
            break;
        case 2://Conta
            checaInteiro(obj);
            if(obj.value == ""){
                alert("Favor preencher uma Conta!");
            }else{
                abrePesquisa();
            }
            break;
        case 3://CPF
            checaCPF(obj);
            if(obj.value == ""){
                alert("Favor preencher um CPF!");
            }else if(!validaCPF(obj.value)){
                alert("CPF invalido, favor corrigir!");
            }else{
                abrePesquisa();
            }
            break;
        case 4://CNPJ
            checaCNPJ(obj);
            if(obj.value == ""){
                alert("Favor preencher um CNPJ!");
            }else if(!validaCNPJ(obj.value)){
                alert("CNPJ invalido, favor corrigir!");
            }else{
                abrePesquisa();
            }
            break;
        case 5://RNE
            if(obj.value == ""){
                alert("Favor preencher um RNE!");
            }else{
                abrePesquisa();
            }
            break;
        case 6://Passaporte
            if(obj.value == ""){
                alert("Favor preencher um Passaporte!");
            }else{
                abrePesquisa();
            }
            break;
        case 7://IE
            if(obj.value == ""){
                alert("Favor preencher uma IE!");
            }else{
                abrePesquisa();
            }
            break;
        case 9://Certidao de Nasciemento
            if(obj.value == ""){
                alert("Favor preencher uma Certidão de Nascimento!");
            }else{
                abrePesquisa();
            }
            break;
        case 10://RG
            if(obj.value == ""){
                alert("Favor preencher um RG!");
            }else{
                abrePesquisa();
            }
            break;
        case 11://IM
            if(obj.value == ""){
                alert("Favor preencher uma IM!");
            }else{
                abrePesquisa();
            }
            break;
        case 12://Prospect workflow
            checaInteiro(obj);
            abrePesquisa();
            break;
    }
}

/*
* Função para remoçao de caracteres especiais de números de telefone
*/
function  removeMascara(telefone){
    var strCheck = '0123456789';
    var len = i = aux = '';
    len = telefone.length;
    for(i = 0; i < len; i++){
        if (strCheck.indexOf(telefone.charAt(i))!=-1){
            aux += telefone.charAt(i);
        }
    }
    return aux;
}

/*
* Função para formataçao de telefones considerando telefones com 7 e 8 digitos.
*/
function checaTelefoneNew(obj){
    valor = obj.value;
    var tecla = (window.Event) ? event.which : event.keyCode;
    numeroSemFormat = removeMascara(valor);
    var strCheck = '()-';
    key = valor.charAt(valor.length-1);
    if (valor.length!=7 && valor.length!=3 && valor.length!=1){
        obj.value = formatTelefoneNew(numeroSemFormat);
    }
    //Para apagar os simbolos "()-" ao clicar no backspace
    if (tecla==8){ //Backspace
        if (strCheck.indexOf(key) != -1){
            obj.value = valor.substring(0,valor.length-1)
        }
    }else{
        for(i=0;i<valor.length;i++){
            if(!inteiro.test(valor.charAt(i))){
                valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
                i = -1;
            }
        }
    }
}

/*
* Função para formataçao de telefones considerando telefones com 7 e 8 digitos.
*/
function formatTelefoneNew(telefone){ //Recebe só numeros
    var aux = len = '';
    len = telefone.length;
    if(len<=9){
        tmp = 5;
    }else{
        tmp = 6;
    }
    aux = '';
    for(i = 0; i < len; i++){
        if(i==0){
            aux = '(';
        }
        aux += telefone.charAt(i);
        if(i+1==2){
            aux += ')';
        }
        if(i+1==tmp){
            aux += '-';
        }
    }
    return aux;
}

/**
*   funcao responsável por setar valores nos objetos dos frames qdo executada uma pesquisa
*/
function setValoresPesquisa(valor){
    try{
        if(document.getElementById('valorPesquisa').value==''){
            document.getElementById('valorPesquisa').value=valor;
        }
        if(document.getElementById('bt07')){
            document.getElementById('bt07').innerHTML = "";
        }
        document.getElementById('areaDetalhe').style.display  = 'none';
        document.getElementById('dvFrameDados').style.display = 'none';
        document.getElementById("fraAbas").src  = "";
        document.getElementById('frmDados').src = "";
        idRelacionamento    = "";
        idChamadaTelefonica = "";
        frameURA.document.forms["validarClienteForm"].numeroConsultado.value = valor;
        frameURA.document.forms["validarClienteForm"].idPos.selectedIndex    = 0;
        frameURA.document.forms["validarClienteForm"].tipoRelacionamento.selectedIndex = 0;
    }catch(e){
        //alert('Privilégios insuficientes para a exibição correta da página!');
    }
}

function abrePesquisa(){
    pesquisa = document.forms[0].pesquisa.value;
    valor    = document.getElementById('valorPesquisa').value;
    if(pesquisa != "nome" && pesquisa != "Pas" && pesquisa != "RNE"){
        for(i=0;i<valor.length;i++){
            if(!inteiro.test(valor.charAt(i))){
                valor = valor.substring(0,i) + valor.substring(i+1,valor.length)
                i = 0;
            }
        }
    }
    if(pesquisa == "celular"){
        limpaDados();
        var xmlhtttp = new ActiveXObject("microsoft.xmlhttp");
        xmlhtttp.open("GET","carregaTelaInicialXml.do?valor="+valor,false);
        xmlhtttp.setRequestHeader("Content-Type","text/xml");
        xmlhtttp.setRequestHeader("chartset","ISO-8859-1");
        xmlhtttp.send();

        if(xmlhtttp.status >= 400) {
            var strError = "Error status    : " + xmlhtttp.status;
            strError    += "\nError readyState: " + xmlhtttp.readyState;
            strError    += "\nStatus   text   : " + xmlhtttp.statusText;
            strError    += "\nResponse text   : \n" + xmlhtttp.responseText;
            alert(strError);
        }

        /*
        *   Somente mostrar dados se idPessoaCliente != ''
        */
        oXml = new ActiveXObject("microsoft.xmldom");
        var str = xmlhtttp.responseText;
        var re  = new RegExp ('&', 'gi') ;
        str     = str.replace(re,"&amp;");
        oXml.async=false;
        if(!oXml.loadXML(str)){
            var strError =   "Error code   : " + oXml.parseError.errorCode;
            strError    += "\nError Reason : " + oXml.parseError.reason;
            strError    += "\nError status : " + xmlhtttp.status;
            strError    += "\nError readySt: " + xmlhtttp.readyState;
            strError    += "\nStatus   text: " + xmlhtttp.statusText;
            strError    += "\nError line   : " + oXml.parseError.line;
            strError    += "\nError linepos: " + oXml.parseError.linepos;
            strError    += "\nError srcText: " + oXml.parseError.srcText;
            alert(strError);
            location.href="/FrontOfficeWeb/session.jsp";
        }else{
            if(oXml.selectSingleNode("forward")!=null){
                window.location="/FrontOfficeWeb" + oXml.selectSingleNode("/forward").text;
            }else if(oXml.selectSingleNode("/salida/@tipo").text!='erro'){

                var idPessoaCliente = oXml.selectSingleNode("/salida/parametros/idPessoaCliente").text;
                var inBloqueado     = oXml.selectSingleNode("/salida/linha/inBloqueado").text;
                var idTipoLinha     = oXml.selectSingleNode("/salida/parametros/idTipoLinha").text;
                //somente carrega se existir idPessoaCliente
                if(idPessoaCliente!=''){
                    try{
                        if(inBloqueado==1){
                            document.getElementById('valorPesquisa').focus();
                            dvFrameDados.style.display ='none';
                            areaDetalhe.style.display  ='none';
                            alert('DDD '+valor.substring(0,2)+' bloqueado temporariamente. Não é possível seguir com o atendimento.');
                        }else{
                            frmDados.carregarDados(str);
                            frmDados.ifrLinha.carregarDados(str);
                        }
                    }catch(e){
                        //alert('Privilégios insuficientes para a exibição correta da página!');
                    }
                    /*if(document.getElementById('bt03')){
                        abaOcultar(btAba, bt03, true);
                    }
                    if(document.getElementById('bt05')){
                        abaOcultar(btAba, bt05, true);
                    }*/
                    if(document.getElementById('bt07')){
                        abaOcultar(btAba, bt07, true);
                    }
                    CarregaAba('linha');
                    pesquisaEfetuada = true;
                    if (inBloqueado != 1) {
                        mostraDados();
                        if(document.getElementById('bt09')){
                            //Exibe Aba Dados Chip apenas para linhas POSC (idTipoLinha=5) e PRÉC (idTipoLinha=6)
                            if(idTipoLinha==6 || idTipoLinha==5){
                                abaOcultar(btAba, bt09, false);
                            }else{
                                abaOcultar(btAba, bt09, true);
                            }
                        }
                        if(document.getElementById('bt10')){
                            //Exibe Aba Dados Chip apenas para linhas POSC (idTipoLinha=5) e PRÉC (idTipoLinha=6)
                            if(idTipoLinha==5 || idTipoLinha==6){
                                abaOcultar(btAba, bt10, false);
                            }else{
                                abaOcultar(btAba, bt10, true);
                            }
                        }
                    }else{
                        frmDados.limparDados();
                        dvFrameDados.style.display ='none';
                        areaDetalhe.style.display  ='none';
                        document.getElementById('valorPesquisa').value='';
                        pesquisaEfetuada    = false;
                        idChamadaTelefonica = '';
                        idRelacionamento    = '';
                    }
                    //caso pesquisa seja feita por barra CTI
                    //e tenha senha validada, chama serviço de idPos
                    if(frameURA.senhaValidadaCTI){
                        //seta variável para false para garantir consistencia
                        frameURA.senhaValidadaCTI = false;
                        var xmlhtttp = new ActiveXObject("microsoft.xmlhttp");
                        xmlhtttp.open("POST","idPosAction.do?valor="+valor+"&tipoRelacionamento="+idRelacionamento+"&idPos=1&idChamadaTelefonica="+idChamadaTelefonica,false);
                        xmlhtttp.send();
                        oXml = new ActiveXObject("microsoft.xmldom");
                        oXml.async=false;
                        if(!oXml.loadXML(xmlhtttp.responseText)){
                            alert("Error on load of document");
                            alert(oXml.parseError.reason);
                            alert(oXml.parseError.line);
                        }
                        var tipoRelacionamiento = oXml.selectSingleNode("/salida/parametros/tipoRelacionamiento").text;
                        var idPessoaCliente     = oXml.selectSingleNode("/salida/parametros/idPessoaCliente").text;
                        var idProspect          = oXml.selectSingleNode("/salida/parametros/idProspect").text;
                        var inCorporativo       = oXml.selectSingleNode("/salida/parametros/inCorporativo").text;
                        var inCorrespondencia   = oXml.selectSingleNode("/salida/parametros/inCorrespondencia").text;
                        var inTipoPessoa        = oXml.selectSingleNode("/salida/parametros/inTipoPessoa").text;
                        var nrLinha             = oXml.selectSingleNode("/salida/parametros/nrLinha").text;
                        //lupas
                        try{
                            frmDados.bindLupaCliente(tipoRelacionamiento, idPessoaCliente);
                            frmDados.bindLupaCarterizacaoSegmentacao(tipoRelacionamiento, idProspect, inCorporativo);
                            frmDados.ifrLinha.bindLupaLinhaUsuario(tipoRelacionamiento, idProspect);
                            frmDados.frmFatura.bindLupaFatura(tipoRelacionamiento, idProspect);
                        }catch(e){
                            //alert('Privilégios insuficientes para a exibição correta da página!');
                        }
                        //abas
                        //abaOcultar(btAba, bt03, false);
                        /*if((nrLinha!='')&&(inTipoPessoa=='PF')){
                            abaOcultar(btAba, bt05, false);
                        }
                        else{
                            abaOcultar(btAba, bt05, true);
                        }*/
                        colorCorrespondencia(tipoRelacionamiento, inCorrespondencia);
                        top.frameApp.identificado = true;
                    }

                    //---****---//
                    //Faz a verificação de linha pesquisada para atualizar automaticamente o IDPOS
                    if(pesquisaEfetuada && idPosAuto){
                        frameURA.document.forms["validarClienteForm"].tipoRelacionamento.value=2;
                        frameURA.document.forms["validarClienteForm"].idPos.value = 1;
                        frameURA.guardaIds();
                        frameURA.submitIdPos();
                        idPosAuto = false;
                    }
                    //---****---//

                }else{
                    alert('Linha não cadastrada!');
                    document.getElementById('valorPesquisa').focus();
                }
            }else{
                var strError =   "Error code   : " + oXml.parseError.errorCode;
                strError    += "\nError Reason : " + oXml.parseError.reason;
                strError    += "\nError status : " + xmlhtttp.status;
                strError    += "\nError readySt: " + xmlhtttp.readyState;
                strError    += "\nStatus   text: " + xmlhtttp.statusText;
                strError    += "\nError line   : " + oXml.parseError.line;
                strError    += "\nError linepos: " + oXml.parseError.linepos;
                strError    += "\nError srcText: " + oXml.parseError.srcText
                alert(strError);
            }
        }
    }else{
        escreveTitulo('Pesquisa Cliente');
        if(document.getElementById('bt09')){
            abaOcultar(btAba, bt09, true);
        }
        document.getElementById("detalhe").style.visibility = 'visible';
        document.getElementById("bg").style.visibility      = 'visible';
        if(pesquisa=='CPF'|| pesquisa=='CNPJ'){
            document.getElementById('valorPesquisa').value = valor;
        }
        // Caso tenha vindo do workflow - lupa de detalhe
        if( document.forms["0"].pesquisa.selectedIndex=='12' ){
            document.forms[0].action='pesquisaCliente.do?tipoLista=linhas&clienteSelecionado='+valor+'&idPessoa='+valor;
        }else{
            document.forms[0].action='pesquisaCliente.do?tipoLista=clientes&valor='+valor+'&pesquisa='+pesquisa;
        }
        document.forms[0].target='frameDetalhe';
        controlCombos();
        document.forms[0].method = "POST";
        document.forms[0].submit();
        //document.getElementById('frameDetalhe').src = pagina;
        //abraLupa('Pesquisa Cliente','pesquisaCliente.do?valor='+valor+'&pesquisa='+pesquisa+'&tipoLista=clientes');
    }
}

/**
*  funcao responsável por carregar os dados da linha na tela quando
*  recebe ligaçao de CTI
*/
function pesquisaLinhaCTI(){
    alert('aqui');
    var xmlhtttp = new ActiveXObject("microsoft.xmlhttp");
    xmlhtttp.open("GET","carregaTelaInicialXml.do?valor="+valor,false);
    xmlhtttp.setRequestHeader("Content-Type","text/xml");
    xmlhtttp.setRequestHeader("chartset","ISO-8859-1");
    xmlhtttp.send();
    /*
    *   Somente mostrar dados se idPessoaCliente != ''
    */
    oXml = new ActiveXObject("microsoft.xmldom");
    var str = xmlhtttp.responseText;
    var re = new RegExp ('&', 'gi');
    str =str.replace(re,"&amp;");
    oXml.async=false;
    if(!oXml.loadXML(str)){
        alert("Error on load of document\n"+oXml.parseError.reason+" on "+oXml.parseError.line);
    }else{
        if(oXml.selectSingleNode("/salida/@tipo").text!='erro'){
            var idPessoaCliente = oXml.selectSingleNode("/salida/parametros/idPessoaCliente").text;
            //somente carrega se existir idPessoaCliente
            if(idPessoaCliente!=''){
                try{
                    frmDados.carregarDados(str);
                    frmDados.ifrLinha.carregarDados(str);
                }catch(e){
                    //alert('Privilégios insuficientes para a exibição correta da página!');
                }
                CarregaAba('linha');
                pesquisaEfetuada = true;
                mostraDados();
            }else{
                alert('Linha não cadastrada!');
                document.getElementById('valorPesquisa').focus();
            }
        }else{
            alert(oXml.selectSingleNode("/salida/erro").text);
        }
    }
}

function controlCombos(){
    for(i=0; i < document.frames.length; i++){
        var  array = document.frames[i].document.getElementsByTagName("SELECT");
        miolo(array);
    }
    internalCombos(document.getElementsByTagName("SELECT"));
}

function internalCombos(array){
    for(j=0; j < array.length; j++){
        if(array[j].style.display=='none')
            array[j].style.display='';
        else
            array[j].style.display='none';
    }
}

function ajustaTamanho(obj){
    if(obj.readyState=="complete"){
        div = document.getElementById('detalhe');
        altura  = window.frames.frameDetalhe.document.body.scrollHeight + 16;
        largura = window.frames.frameDetalhe.document.body.scrollWidth + 16;
        if(altura > 589) altura = 589;
        if(largura > 789) largura = 789;
        div.style.width = largura;
        div.style.height = altura;
        div.style.top = (600 - altura) / 2;
        div.style.left = (800 - largura) / 2;
    }
}

function TamanhoIFrame(){
    var dyniframe = document.getElementById("fraAbas");
    altura = dyniframe.height;
    if(altura==172){
        dyniframe.height = 220;
    }else if(altura==220){
        dyniframe.height = 172;
    }
}

/* function VerificaClique(){
    var o = event.srcElement;
    if(o.id=="hdr_button") TamanhoIFrame();
}*/

function CarregaAba(nome){
    var pagina = "";
    mostrar_div();
    switch(nome){
        case "linha":
            if(document.getElementById('bt01')){
                abaSelected(btAba, bt01);
            }
            document.getElementById("dvFraAbas").style.display="none";
            document.getElementById("dvFraAbasRelacionamento").style.display="block";
            //variável para controle de radio na aba relacionamento
            fraAbasRelacionamento.setaRadio = true;
            fraAbasRelacionamento.limparSemFoco();
            fraAbasRelacionamento.pesquisarRelacionamento();
            return;
            break;
        case "bt01":
            abaSelected(btAba, bt01);
            //variável para controle de radio na aba relacionamento
            fraAbasRelacionamento.setaRadio = true;
            pagina = "carregaRelacionamento.do";
            break;
        case "bt02":
             //verifica se existe chamada Telefonica
            if(!identificado){
                alert('Para poder registrar um atendimento\ndeve-se identificar a ligação!');
                oculta_div();
                return;
            }else{
                abaSelected(btAba, bt02);
                pagina = "/FrontOfficeWeb/workflow/RegistrarContato/begin.do";
            }
            break;
        case "bt03":
            if(!identificado || (idRelacionamento!='1')&&(idRelacionamento!='2')){
                alert('É necessário identificar a ligação para acessar essa aba!');
                oculta_div();
                return;
            }else{
                abaSelected(btAba, bt03);
                pagina = "/FrontOfficeWeb/cliente/TelaInicial/getHistoricoServicos.do";
                //pagina = "/FrontOfficeWeb/cliente/TelaInicial/loadAbaServico.do";
            }
            break;
        case "bt04":
            pagina = "/FrontOfficeWeb/campanha/HistoricoCampanha/begin.do";
            break;
        case "bt05":
            if(idRelacionamento!=''){
                if((idRelacionamento!='1')&&(idRelacionamento!='2')){
                    alert('Um Não Cliente / Prospect não tem permissoes para acessar essa aba!');
                    oculta_div();
                    return;
                }else{
                    abaSelected(btAba, bt05);
                    pagina = "/FrontOfficeWeb/senha/historicoSenha/begin.do";
                    if (!telaCarregadaFila){
                        if (up)
                            resizeFrameDetalhe();
                    }
                }
            }else{
                alert('É necessário identificar a ligação para acessar essa aba!');
                oculta_div();
                return;
            }
            break;
        case "bt06":
            pagina = "/FrontOfficeWeb/fidelizacao/begin.do";
            break;
        case "bt07":
             pagina = "../CDevolvida/telaInicial/begin.do?start=true";
            break;
        case "bt08":
            pagina = "/FrontOfficeWeb/campanha/ExecutarCampanha/telaInicial.do";
            break;
        case "bt09":
            if(!identificado || (idRelacionamento!='1')&&(idRelacionamento!='2')){
                alert('É necessário identificar a ligação para acessar essa aba!');
                oculta_div();
                return;
            }else{
                abaSelected(btAba, bt09);
                pagina = "/FrontOfficeWeb/cliente/TelaInicial/getDadosChip.do";
            }
            break;
        case "bt10":
            if(!identificado){
                alert('É necessário identificar a ligação para acessar essa aba!');
                oculta_div();
                return;
            }else{
                abaSelected(btAba, bt10);
                pagina = "/FrontOfficeWeb/cliente/TelaInicial/desbloqueioAparelhoGsm.do";
            }
            break;
    }
    document.getElementById("dvFraAbas").style.display="block";
    document.getElementById("dvFraAbasRelacionamento").style.display="none";
    document.getElementById("fraAbas").src = pagina;
}

function resizeRetencao(){
    var dynfrm = document.getElementById("fraAbas");
    var dyndiv = document.getElementById("areaDetalhe");
    var objRef = document.getElementById("imgRef");
    var objDow = document.getElementById("imgRefDown");
    dyndiv.style.backgroundColor = '#FFFFFF';
    objRef.style.position = 'absolute';
    objDow.style.position = 'absolute';
    var novaTop   = objRef.offsetTop;
    var oldposdiv = objDow.offsetTop;
    objRef.style.position = '';
    objDow.style.position = '';
    if(up){
        dyndiv.style.position = 'absolute';
        dyndiv.style.top = novaTop;
        dyndiv.style.left= 5;
        oldtamfrm = dynfrm.height;
        oldlardiv = dyndiv.style.width;
        dynfrm.height = parseInt(oldtamfrm) + parseInt(oldposdiv) - parseInt(novaTop);
        dyndiv.style.height = dynfrm.height;
        up = false;
    } else {
        return false;
    }
}

function resizeFrameDetalhe(){
    var dynfrmR = document.getElementById("fraAbasRelacionamento");
    var oldtamfrmR = 0;
    var dynfrm = document.getElementById("fraAbas");
    var dyndiv = document.getElementById("areaDetalhe");
    var objRef = document.getElementById("imgRef");
    var objDow = document.getElementById("imgRefDown");
    dyndiv.style.backgroundColor = '#FFFFFF';
    objRef.style.position = 'absolute';
    objDow.style.position = 'absolute';
    var novaTop   = objRef.offsetTop;
    var oldposdiv = objDow.offsetTop;
    objRef.style.position = '';
    objDow.style.position = '';
    if(up){
        dyndiv.style.position = 'absolute';
        dyndiv.style.top = novaTop;
        dyndiv.style.left= 5;
        oldtamfrmR = dynfrmR.height;
        oldtamfrm = dynfrm.height;
        oldlardiv = dyndiv.style.width;
        dynfrmR.height = parseInt(oldtamfrmR) + parseInt(oldposdiv) - parseInt(novaTop);
        dynfrm.height = parseInt(oldtamfrm) + parseInt(oldposdiv) - parseInt(novaTop);
        dyndiv.style.height = dynfrmR.height;
        dyndiv.style.height = dynfrm.height;
        up = false;
        document['lupaaba'].src='../../resources/images/bt_lupa_aba_down.gif';
    }else{
        dyndiv.style.backgroundColor = '';
        dyndiv.style.left= 5;
        dyndiv.style.top = oldposdiv;
        dynfrm.height = oldtamfrm;
        dyndiv.style.height = oldtamfrmR;
        dyndiv.style.position = '';
        dyndiv.style.width = oldlardiv;
        dynfrmR.height = dynfrm.height;
        up = true;
        document['lupaaba'].src='../../resources/images/bt_lupa_aba.gif';
    }
}

//document.onclick = VerificaClique;
//Workflow
var limparObjetos = "";
function atendimentoProcesso() {
    try{
        dvAtendimento.style.display = '';
        document.forms["frmSelecao"].target = "ifrmAtendimento";
        document.forms["frmSelecao"].action = "/FrontOfficeWeb/workflow/detalheProcesso/begin.do";
        document.forms["frmSelecao"].method = "POST";
        document.forms["frmSelecao"].submit();
        //Processa a limpeza do cabeçalho, menu e rodapé
        limparObjetos = window.setInterval( 'atendimentoProcessoClearCabecMenu()', 1000 );
    }catch(e){
        //alert('Privilégios insuficientes para a exibição correta da página!');
    }
}

function atendimentoProcessoClearCabecMenu() {
    try {
        //Limpa a apresentação do header
        ifrmAtendimento.hdMinPrincipal.style.display = "none";
        ifrmAtendimento.hdMaxPrincipal.style.display = "none";
        //Limpa a apresentação do menu
        ifrmAtendimento.mnMin.style.display = "none";
        ifrmAtendimento.mnMax.style.display = "none";
        //Limpa a pesquisa + ura
        ifrmAtendimento.tbPesquisa.style.display = "none";
        ifrmAtendimento.dvFrameURA.style.display = "none";
        window.clearInterval(limparObjetos);
        limparObjetos = "";
    }catch(e){
        //alert('Privilégios insuficientes para a exibição correta da página!');
    }
}

function txtBoxFormat(objForm, strField, sMask, evtKeyPress){
    var i, nCount, sValue, fldLen, mskLen,bolMask, sCod, nTecla;
    if(document.all) { // Internet Explorer
        nTecla = evtKeyPress.keyCode;
    }else if(document.layers) { // Nestcape
        nTecla = evtKeyPress.which;
    }
    sValue = objForm[strField].value;
    // Limpa todos os caracteres de formatação que já estiverem no campo.
    sValue = sValue.toString().replace( "-", "" );
    sValue = sValue.toString().replace( "-", "" );
    sValue = sValue.toString().replace( ".", "" );
    sValue = sValue.toString().replace( ".", "" );
    sValue = sValue.toString().replace( "/", "" );
    sValue = sValue.toString().replace( "/", "" );
    sValue = sValue.toString().replace( "(", "" );
    sValue = sValue.toString().replace( "(", "" );
    sValue = sValue.toString().replace( ")", "" );
    sValue = sValue.toString().replace( ")", "" );
    sValue = sValue.toString().replace( " ", "" );
    sValue = sValue.toString().replace( " ", "" );
    fldLen = sValue.length;
    mskLen = sMask.length;
    i = 0;
    nCount = 0;
    sCod = "";
    mskLen = fldLen;
    while(i <= mskLen){
        bolMask = ((sMask.charAt(i) == "-") || (sMask.charAt(i) == ".") || (sMask.charAt(i) == "/"))
        bolMask = bolMask || ((sMask.charAt(i) == "(") || (sMask.charAt(i) == ")") || (sMask.charAt(i) == " "))
        if(bolMask){
            sCod += sMask.charAt(i);
            mskLen++;
        }else{
            sCod += sValue.charAt(nCount);
            nCount++;
        }
        i++;
    }
    objForm[strField].value = sCod;
    if(nTecla != 8){ //backspace
        if(sMask.charAt(i-1) == "9"){ // apenas números...
            return ((nTecla > 47) && (nTecla < 58)); //números de 0 a 9
        }else{// qualquer caracter...
            return true;
        }
    }else{
        return true;
    }
}

function escolheMascara(obj){
    tipo = obj.form.pesquisa.selectedIndex;
    switch(tipo){
        case 0:         //Celular
            checaTelefoneNew(obj);
            qtdeCaracteres = 13;
            break;
        case 1:         //Nome do Cliente
            qtdeCaracteres = 255;
            break;
        case 2:         //Conta
            checaInteiro(obj);
            qtdeCaracteres = 100;
            break;
        case 3:         //CPF
            checaCPF(obj);
            qtdeCaracteres = 14;
            break;
        case 4:         //CNPJ
            checaCNPJ(obj);
            qtdeCaracteres = 18;
            break;
        case 5:         //RNE
            checaInteiro(obj);
            qtdeCaracteres = 25;
        case 6:         //Passaporte
            qtdeCaracteres = 25;
            break;
        case 7:         //IE
            checaInteiro(obj);
            qtdeCaracteres = 25;
        case 9:         //RG
            qtdeCaracteres = 25; //enough?
        case 10:        //IM
            checaInteiro(obj);
            qtdeCaracteres = 25; //enough?
            break;
    }
    obj.maxLength = qtdeCaracteres;
}

/*
* Troca o grupo do usuário logado
*/
function trocarGrupo(idGrupo){
    frameURA.document.forms[0].method = "POST";
    frameURA.document.forms[0].action = "carregaGrupo.do?idGrupo="+idGrupo;
    frameURA.document.forms[0].target = "frameEscondido";
    frameURA.document.forms[0].method = "POST";
    frameURA.document.forms[0].submit();
}

/*
* Efetua o tratamento ao receber uma chamada do CTI
*/
function receberChamada(GrupOrigCh, NumOrigCh, NumLinCliUra, CliAut, CliIdent, IndTitular, NavCliURA, GrupDest, TipProc, NumProc, ObsAtend, IndRedirTimeoutURA) {
    //Liga animação
    if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
    //verifica se irá carregar nova ligaçao
    if(idChamadaTelefonica == ''){
        var strAction = "carregaParametrosCTI.do?";
        strAction += "idUra=" + GrupOrigCh;
        strAction += "&";
        strAction += "idCallCenter=" + GrupDest;
        strAction += "&";
        strAction += "inSenhaValidada=";
        strAction += (CliAut == 'S');
        strAction += "&";
        strAction += "nrLinha=" + NumLinCliUra;
        strAction += "&";
        strAction += "nrTelefone=" + NumOrigCh;
        strAction += "&";
        strAction += "observacao=" + ObsAtend;
        strAction += "&";
        strAction += "timeoutURA=" + IndRedirTimeoutURA;
        strAction += "&";
        strAction += "navegacaoURA=" + NavCliURA;
        strAction += "&";
        strAction += "tipProc=" + TipProc;
        strAction += "&";
        strAction += "numProc=" + NumProc;
        strAction += "&";
        strAction += "idTipoRelacionamento=";
        switch(IndTitular){
            case 'C':
                strAction += '2';
                break;
            case 'U':
                strAction += '1';
                break;
            case 'N':
                strAction += '6';
                break;
        }
        //alert(strAction);
         document.forms[0].method = "POST";
        document.forms[0].target = "frameEscondido";
        //document.forms[0].target = "frameURA";
        document.forms[0].action = strAction;
        document.forms[0].submit();
    }else{
        var strAction = "carregarLigacao.do?";
        strAction += "idUra=" + GrupOrigCh;
        strAction += "&";
        strAction += "idCallCenter=" + GrupDest;
        strAction += "&";
        strAction += "inSenhaValidada=";
        strAction += (CliAut == 'S');
        strAction += "&";
        strAction += "nrLinha=" + NumLinCliUra;
        strAction += "&";
        strAction += "nrTelefone=" + NumOrigCh;
        strAction += "&";
        strAction += "observacao=" + ObsAtend;
        strAction += "&";
        strAction += "timeoutURA=" + IndRedirTimeoutURA;
        strAction += "&";
        strAction += "navegacaoURA=" + NavCliURA;
        strAction += "&";
        strAction += "tipProc=" + TipProc;
        strAction += "&";
        strAction += "numProc=" + NumProc;
        strAction += "&";
        strAction += "idTipoRelacionamento=";
        switch(IndTitular){
            case 'C':
                strAction += '2';
                break;
            case 'U':
                strAction += '1';
                break;
            case 'N':
                strAction += '6';
                break;
        }
        document.forms[0].method = "POST";
        document.forms[0].action = strAction;
        document.forms[0].target = "frameEscondido";
        document.forms[0].submit();
    }
}

/*
* Efetua algum tratamento quando a ligação for finalizada.
*/
function semLigacao(){
    frameURA.document.getElementById('numCampanhaSel').disabled = false;
    frameURA.document.getElementById('imgTransferir').style.display = 'none';
    frameURA.document.getElementById('dvImgIniciarChamado').style.display = 'none';
    document.getElementById('grupoSel').disabled = false;
    alert('Ligação Finalizada');
}

function pesquisaNome(){
    limpaDados();
    top.frameApp.idChamadaTelefonica='';
    if(document.forms[0].pesquisa.value == 'nome' || document.forms[0].pesquisa.value == 'razao') {
        var tipo = document.forms[0].pesquisa.value;
        dvPesqNome.style.display    = '';
        controlCombos();
        document.forms[0].action="irPesquisaNome.do?tipo="+tipo;
        document.forms[0].target="ifrmPesqNome";
        document.forms[0].method = "POST";
        document.forms[0].submit();
        mostrar_div();
    }else if(document.forms[0].pesquisa.value == 'naoCliente'){
        var xmlhtttp = new ActiveXObject("microsoft.xmlhttp");
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.startAnimation();
        xmlhtttp.open("GET","carregaNaoClienteXml.do",false);
        xmlhtttp.send();
        //Liga animação
        if( top.frameApp.dvAnimarAguarde != null ) top.frameApp.stopAnimation();
        try{
            frmDados.carregarDados(xmlhtttp.responseText);
            frmDados.ifrLinha.carregarDados(xmlhtttp.responseText);
        }catch(e){
            //alert('Privilégios insuficientes para a exibição correta da página!');
        }
        if(document.getElementById('bt01')){
            CarregaAba('bt01');
        }
        /*if(document.getElementById('bt03')){
            abaOcultar(btAba, bt03, true);
        }
        if(document.getElementById('bt05')){
            abaOcultar(btAba, bt05, true);
        }*/
        if(document.getElementById('bt07')){
            abaOcultar(btAba, bt07, true);
        }
        pesquisaEfetuada = true;
        mostraDados();
        frameURA.iniciarChamado();
    }else{
        return false;
    }
}

function limpaDados(){
    pesquisaEfetuada = false;
    identificado = false;
    if(top.frameApp.frameURA.ligacaoCTI==false){
        //alert(idChamadaTelefonica);
        // idChamadaTelefonica = '';
        idRelacionamento = '';
    }
    if(top.frameApp.frameURA.document.forms["validarClienteForm"].idPos!=null){
        top.frameApp.frameURA.document.forms["validarClienteForm"].idPos.selectedIndex = 0;
        top.frameApp.frameURA.document.forms["validarClienteForm"].tipoRelacionamento.selectedIndex = 0;
    }
    //frameURA.document.forms["validarClienteForm"]["nomeContato"].value='';
    try{
        frmDados.limparDados();
        frmDados.frmPrePago.limparDados();
        frmDados.frmFatura.limparDados();
        dvFrameDados.style.display='none';
        areaDetalhe.style.display='none';
    }catch(e){
        //alert('Privilégios insuficientes para a exibição correta da página!');
    }
}

function mostraDados(){
    dvFrameDados.style.display='block';
    areaDetalhe.style.display='block';
}

function selecionaEndereco(idArray){
    if(idArray){
        //top.frameApp.frameDetalhe.fraSubAbas.ifrmEndereco.location.href = ''; --> Caminho absoluto do iframe de Pesquisa
        ifrmPesqEndereco.document.forms[0].action = '/FrontOfficeWeb/cliente/TelaInicial/DetalheCliente/pesquisaEndereco.do?pagina=nao&&idArray='+idArray;
        ifrmPesqEndereco.document.forms[0].target = 'ifrmEndereco';
        mostrar_div();
        ifrmPesqEndereco.document.forms[0].method = "POST";
        ifrmPesqEndereco.document.forms[0].submit();
        dvPesqEndereco.style.display = 'none';
    }else{
        alert('Execute uma pesquisa e/ou selecione um endereço!');
        return false;
    }
}

function selecionaEndUser(idArray){
    if(idArray){
        //top.frameApp.frameDetalhe.fraSubAbas.ifrmEndereco.location.href = ''; --> Caminho absoluto do iframe de Pesquisa
        ifrmPesqEndereco.document.forms[0].action = '/FrontOfficeWeb/cliente/TelaInicial/DetalheUsuario/pesquisaEndereco.do?pagina=nao&&idArray='+idArray;
        ifrmPesqEndereco.document.forms[0].target = 'ifrmEndereco';
        mostrar_div();
        ifrmPesqEndereco.document.forms[0].method = "POST";
        ifrmPesqEndereco.document.forms[0].submit();
        dvPesqEndereco.style.display = 'none';
    }else{
        alert('Execute uma pesquisa e/ou selecione um endereço!');
        return false;
    }
}

function mostraIdPos(){
    frameURA.dvSenhaValidadaTrue.style.display='none';
    frameURA.dvSenhaValidadaFalse.style.display='';
    idPosAuto = true;
}

function preValidaKey(){
    if(window.event.keyCode == 13){
        window.event.keyCode = 0;
        mostraIdPos();
        validaPesquisa()
        return false;
    }
}

function abaOcultar(abas, abaDestion, estado) {
    var tdElement;
    var imgElementLeft;
    var imgElementRight;
    for( x = 1; x < abas.cells.length; x+=3 ) {
        tdElement       = abas.cells(x);
        imgElementLeft  = document.getElementById("AbaLeft_"  + tdElement.id);
        imgElementRight = document.getElementById("AbaRight_" + tdElement.id);
        try{
            if( tdElement.id == abaDestion.id) {
                if(estado){
                    abas.cells(x).style.display='none';
                    imgElementLeft.parentElement.style.display='none';
                    imgElementRight.parentElement.style.display='none';
                }else{
                    abas.cells(x).style.display='block';
                    imgElementLeft.parentElement.style.display='block';
                    imgElementRight.parentElement.style.display='block';
                }
                break;
            }
        }catch(e){
            break;
        }
    }
}

function colorCorrespondencia(tipoRelacionamento, inCorrespondencia){
    //alert('colorCorrespondencia\n'+tipoRelacionamento+'-'+inCorrespondencia);
    if(document.getElementById('bt07')){
        abaOcultar(btAba, bt07, false);
        bt07.onclick=null;
        if(tipoRelacionamento!=7){
            if(inCorrespondencia==1){
                bt07.onclick = marcar2;
                strInner = "<span id=\'titCDevolvida\' style=\'color=#FF0000;font-weight:bold;\'>C.Devolvida</span>"
            }else{
                strInner = "C.Devolvida"
                bt07.onclick = marcar;
            }
            bt07.innerHTML = strInner;
        }
    }else{
        return false;
    }
}

function marcar(){
    if(document.getElementById('bt07')){
        abaSelected(btAba, bt07);
        CarregaAba(bt07.id);
    }else{
        return false;
    }
}

function marcar2(){
    marcar();
    if (document.getElementById('bt07')){
        document.getElementById('bt07').innerHTML = 'Corresp. Devolvida';
        document.getElementById('bt07').style.color = '#ffffff';
    }else{
        document.getElementById('titCDevolvida').style.color = '#ffffff';
    }
}

function guardaIds(){
    var idRelacionamentoTroca = top.frameApp.frameURA.document.getElementById('tipoRelacionamento').value;
    var idPosTroca            = top.frameApp.frameURA.document.getElementById('idPos').value;
    var idGrupoTroca          = top.frameApp.document.getElementById('grupoSel').value;
    top.frameApp.iframeRefreshCombos.location.href = "/FrontOfficeWeb/cliente/TelaInicial/refreshCombos.do?idRelacionamentoTroca="+idRelacionamentoTroca+"&idPosTroca="+idPosTroca+"&idGrupoTroca="+idGrupoTroca;
}