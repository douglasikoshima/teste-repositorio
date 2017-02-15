oldObj = "";
oldValor = "";
inteiro = new RegExp("[0-9]");
alphaNumeric_StrEspecias = new RegExp("[0-9a-zA-Z\._-]");
alphaNumeric = new RegExp("[0-9a-zA-Z]");
var objSomaDias;
frmsState = null;

var indicativoDownload=true;
var dadosArquivoPaginacao= new Array();
var dadosArquivo= new Array();

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

function checaTelefoneNew(obj){

    valor = obj.value;

    if(valor.length>13){

        valor=valor.substring(0,13);
        obj.value=valor;
    }

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




function validaDataOnBlur(data){
    if(data.value == '')
        return false;
    if(!validaData(data.value)){
        data.value = '';
        data.focus();
        alert("Data inválida");
    }
}

function validaData(dataValidar) {
    var dataOriginal=dataValidar;
    try {
        if(dataValidar==undefined || typeof(dataValidar)!='string') {
            return false;
        }
        dataValidar=replaceAll(dataValidar,'/','');
        if(isNaN(dataValidar)) {
            return false;
        }
        if(dataValidar.length != 8) {
            return false;
        }
        dia=dataOriginal.substring(0, dataOriginal.indexOf('/'));
        mes=dataOriginal.substring(dataOriginal.indexOf('/') + 1, dataOriginal.lastIndexOf('/'));
        ano=dataOriginal.substring(dataOriginal.lastIndexOf('/') + 1, dataOriginal.length);
        if((dia<1 || dia>31) || (mes<1 || mes>12) || (ano<1000)) {
            return false;
        }
        if(mes==2 && dia>29) {
            return false;
        }
        if((mes==4 || mes==6 || mes==9 || mes==11) && dia>30) {
            return false;
        }
        return true;
    } catch(ex) {
        alert('Erro na execução da funçao javascript validaData(dataValidar)!');
    }
}

function replaceAll(t, e, s) {
    var ret = t;
    do { ret = ret.replace(e, s);
    } while(ret.indexOf(e)!=-1);
    return ret;
}

function verificaDatas() {


    dtFecIni=document.forms[0].dtInicial.value;
    dtFecFim=document.forms[0].dtFinal.value;

    if(dtFecIni!="" && dtFecFim!=""){
        if(dtFecIni.length>0) {
            if(!validaData(dtFecIni)) {
                alert('Data de fechamento inicial deve estar no formato DD/MM/AAAA.');
                return false;
            }
        }
        if(dtFecFim.length>0) {
            if(!validaData(dtFecFim)) {
                alert('Data de fechamento final deve estar no formato DD/MM/AAAA.');
                return false;
            }
        }
        if (dtFecFim.length>0 && dtFecIni.length>0) {
            if (!validaDataFinal(dtFecIni,dtFecFim)) {
                alert("Data final não pode ser menor que a data inicial!");
                return false;
            }

            if (!valida1mes(dtFecIni,dtFecFim)) {
                alert("Período de datas não pode ser superior a 30 dias.\n");
                return false;
            }
        }
        return true;
    }
}

function valida1mes(objInicial,objFinal){

    objSomaDias=somaDiasData(objInicial,objSomaDias,30);
    resposta = validaDataFinal(objFinal,objSomaDias);
    return resposta;
}

function somaDiasData(objOrigem,objDestino,dias){
    if (objOrigem != ""){
        data = objOrigem.split("/");
        data[0] == "08"?data[0] = "8":0;data[0] == "09"?data[0] = "9":0;data[1] == "08"?data[1] = "8":0;data[1] == "09"?data[1] = "9":0;
        data[0] = parseInt(data[0]);data[1] = parseInt(data[1]);data[2] = parseInt(data[2]);
        for (i = 0; i < dias; i++){
            bissexto = (data[2] % 4 == 0)?1:0;
            if (data[1] == "1" || data[1] == "3" || data[1] == "5" || data[1] == "7" || data[1] == "8" || data[1] == "10" || data[1] == "12" ){
                if(data[0] < 31){
                    data[0]++;
                } else {
                    data[0] = 1;
                    if (data[1] < 12){
                        data[1]++;
                    }else{
                        data[1] = 1;
                        data[2]++;
                    }
                }
            }else if (data[1] == "4" || data[1] == "6" || data[1] == "9" || data[1] == "11"){
                if(data[0] < 30){
                    data[0]++;
                } else {
                    data[0] = 1;
                    if (data[1] < 12){
                        data[1]++;
                    }else{
                        data[1] = 1;
                        data[2]++;
                    }
                }
            }else{
                if (bissexto){
                    if(data[0] < 29){
                        data[0]++;
                    } else {
                        data[0] = 1;
                        if (data[1] < 12){
                            data[1]++;
                        }else{
                            data[1] = 1;
                            data[2]++;
                        }
                    }
                }else{
                    if(data[0] < 28){
                        data[0]++;
                    } else {
                        data[0] = 1;
                        if (data[1] < 12){
                            data[1]++;
                        }else{
                            data[1] = 1;
                            data[2]++;
                        }
                    }
                }
            }
            //alert(data[0] + "/" + data[1] + "/" + data[2])
        }
        data[0] = data[0].toString();
        data[1] = data[1].toString();
        data[0].length == 1?data[0] = "0" + data[0]:0;
        data[1].length == 1?data[1] = "0" + data[1]:0;
        objDestino = data[0] + "/" + data[1] + "/" + data[2];
    }else{
        alert("Favor selecionar uma Data!")
    }
    return objDestino;
}


function validaDataFinal(dataInicial,dataFinal) {
    if (dataInicial == "" || dataFinal == ""){
        return false;
    }else{
        splitDataInicial = dataInicial.split('/');
        splitDataInicial[0] == "08"?splitDataInicial[0] = "8":0;
        splitDataInicial[0] == "09"?splitDataInicial[0] = "9":0;
        splitDataInicial[1] == "08"?splitDataInicial[1] = "8":0;
        splitDataInicial[1] == "09"?splitDataInicial[1] = "9":0;
        splitDataFinal = dataFinal.split('/');
        splitDataFinal[0] == "08"?splitDataFinal[0] = "8":0;
        splitDataFinal[0] == "09"?splitDataFinal[0] = "9":0;
        splitDataFinal[1] == "08"?splitDataFinal[1] = "8":0;
        splitDataFinal[1] == "09"?splitDataFinal[1] = "9":0;
        if (parseInt(splitDataFinal[2]) < parseInt(splitDataInicial[2])){
            return false;
        }
        if (parseInt(splitDataFinal[2]) == parseInt(splitDataInicial[2])) {
            if(parseInt(splitDataFinal[1]) < parseInt(splitDataInicial[1])) {
                return false;
            }
            if(parseInt(splitDataFinal[1]) == parseInt(splitDataInicial[1])) {
                if(parseInt(splitDataFinal[0]) < parseInt(splitDataInicial[0])) {
                    return false;
                }
            }
        }
        return true;
    }
}



// Válida envio de dados.
function validaPesquisa()
{
    var form = document.forms[0];
    var contador=0;
    if(form.dtInicial.value!='' && form.dtFinal.value!=''){
        contador=contador+1;
    }
    if(form.login.value!=''){
        contador=contador+1;
    }
    if(form.imei.value!=''){
        contador=contador+1;
    }

    if(contador>1){
        alert("Somente é permitido o preenchimento de um dos seguintes campos:Período(Inicial e Final) ou IMEI ou Login")
        return false;
    }
    if(form.dtInicial.value==''
        && form.dtFinal.value==''
        && form.login.value==''
        && form.imei.value==''
    ){
        alert('É obrigatório o preenchimento de um dos seguintes campos:Periodo(Inicial e Final) ou Login ou IMEI para realizar a pesquisa');
        return false;
    }

    if(form.dtInicial.value!='' && form.dtFinal.value==''){
        alert('É obrigatório o preenchimento da período final para realizar a pesquisa');
        return false;
    }
    if(form.dtInicial.value=='' && form.dtFinal.value!=''){
        alert('É obrigatório o preenchimento da período inicial para realizar a pesquisa');
        return false;
    }
    if(form.dtInicial.value!='' && form.dtFinal.value!=''){
        if(!verificaDatas()){
            return false;
        }
    }
    if(form.imei.value!=''){
    var imei = form.imei.value;
        if(imei.length<15){
            alert('IMEI inválido!');
            return false;
        }
    }
    if(form.opcional.value!='0'){
        if(form.opcional.value=='CPF'){
            if(!validaCPF(form.nrDoc.value))
            {
                alert('CPF inválido.');
                form.nrDoc.focus();
                return false;
            }
        }else if(form.opcional.value == 'CNPJ'){
            if(!validaCNPJ(form.nrDoc.value))
            {
                alert('CNPJ inválido.');
                form.nrDoc.focus();
                return false;
             }
        }else if(form.opcional.value == 'RG'){
            if(form.nrDoc.value == '')
            {
                alert('RG é um campo obrigatório.');
                form.nrDoc.focus();
                return false;
            }
        }else if(form.opcional.value=='LINHA'){
            if(form.nrDoc.value == '')
            {
                alert('LINHA é necessária ser preenchida.');
                form.nrDoc.focus();
                return false;
            }

        }
    }
    return true;
}





// Envia dados para pesquisa.
function pesquisa()
{
    if(validaPesquisa())
    {
        document.forms[0].target = '';
        document.forms[0].action = 'pesquisarSIMLocks.do';
        mostrar_div();
        document.forms[0].submit();

    }else
    {
        return false;
    }

}

// Envia dados para pesquisa.
function pesquisaCampo(op)
{
    if(op.keyCode == 13)
    {
        if(validaPesquisa())
        {
            document.forms[0].target = '';
            document.forms[0].action = 'pesquisaUsuarios.do';
            mostrar_div();
            document.forms[0].submit();  return false;

        }else
        {
            return false;
        }

    }

}

function checaRG(obj){
    valor = obj.value;
    
    for(i=0;i<valor.length;i++){
      if(!inteiro.test(valor.charAt(i))){
        valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
        i = -1;
      }
    }
}

function checaCPF(obj){
    valor = obj.value;
    if(valor != oldValor || oldObj != obj){
    for(i=0;i<valor.length;i++){
      if(!inteiro.test(valor.charAt(i))){
        valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
        i = -1;
      }
    }
    if(valor.length < 1){
      valor = "";
    }else if(valor.length > 3 && valor.length < 7){
      valor = valor.substring(0,3) + "." + valor.substring(3,valor.length);
    }else if(valor.length > 6 && valor.length < 10){
      valor = valor.substring(0,3) + "." + valor.substring(3,6) + "." + valor.substring(6,valor.length);
    }else if(valor.length > 9 && valor.length < 12){
      valor = valor.substring(0,3) + "." + valor.substring(3,6) + "." + valor.substring(6,9) + "-" + valor.substring(9,valor.length);
    }else if(valor.length > 11){
      valor = valor.substring(0,3) + "." + valor.substring(3,6) + "." + valor.substring(6,9) + "-" + valor.substring(9,11);
    }
    obj.value = valor;
    oldValor = valor;
    oldObj = obj;
    }
}


function checaCNPJ(obj){
    valor = obj.value;
    if(valor != oldValor || oldObj != obj){
    for(i=0;i<valor.length;i++){
      if(!inteiro.test(valor.charAt(i))){
        valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
        i = -1;
      }
    }
    if(valor.length < 1){
      valor = "";
    }else if(valor.length > 2 && valor.length < 6){
      valor = valor.substring(0,2) + "." + valor.substring(2,valor.length);
    }else if(valor.length > 5 && valor.length < 9){
      valor = valor.substring(0,2) + "." + valor.substring(2,5) + "." + valor.substring(5,valor.length);
    }else if(valor.length > 8 && valor.length < 13){
      valor = valor.substring(0,2) + "." + valor.substring(2,5) + "." + valor.substring(5,8) + "/" + valor.substring(8,valor.length);
    }else if(valor.length > 12 && valor.length < 15){
      valor = valor.substring(0,2) + "." + valor.substring(2,5) + "." + valor.substring(5,8) + "/" + valor.substring(8,12) + "-" + valor.substring(12,valor.length);
    }else if(valor.length > 14){
      valor = valor.substring(0,2) + "." + valor.substring(2,5) + "." + valor.substring(5,8) + "/" + valor.substring(8,12) + "-" + valor.substring(12,14);
    }
    obj.value = valor;
    oldValor = valor;
    oldObj = obj;
    }
}

function checaStrEspecial(obj){
    valor = obj.value;
    for(i=0;i<valor.length;i++){
    if(semCaracterEspeciais.test(valor.charAt(i))){
      valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
      i = -1;
    }
    }
    obj.value = valor;
}

function mostraCampo()
{
    var campoDoc = document.getElementById("divCampo");

    if(document.forms[0].opcional.selectedIndex > 0)
        campoDoc.style.display = 'inline';
    else
    {
        campoDoc.style.display = 'none';
        campoDoc.value == '';
    }
}

function validaIMEI(obj){
    valor= obj.value;
    for(i=0;i<valor.length;i++){
        if(!inteiro.test(valor.charAt(i))){
            valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
            i = -1;
        }
    }
    obj.value=valor;
}

function validaCampoDoc(obj)
{
    var form = document.forms[0];

    if(form.opcional.value=='LINHA'){
        checaTelefoneNew(obj);
    }

    // Se CPF.
    if(form.opcional.value == 'CPF')
    {
        checaCPF(obj);
    }
    // Se CNPF.
    else if(form.opcional.value == 'CNPJ')
    {
        checaCNPJ(obj);
    }
    else if(form.opcional.value == 'RG')
    {
        //checaRG(obj);
        
        if(obj.value.length > 12)
        {
            obj.value = obj.value.substring(0,12);
            return false;
        }else
            checaStrEspecial(obj);
    }
    return false;
}

function checaCaracteresLogin(obj){
    valor = obj.value;
    for(i=0;i<valor.length;i++){
        if(!alphaNumeric_StrEspecias.test(valor.charAt(i))){
            valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
            alert('Caracteres permitidos para pesquisa do Login são:\n de \"a\" até \"z\" maiúsculas ou minúsculas,números e os caracteres especiais ponto,underline e hífen(\".\","\_"\ e \"-\")');
            i = -1;
        }
    }
    obj.focus();
    obj.value = valor;
}



function verificaLimpeza(){
    if(document.forms[0].dtInicial.value=='' 
        && document.forms[0].dtFinal.value==''
        && document.forms[0].login.value==''
        && document.forms[0].imei.value==''
        && document.forms[0].opcional.selectedIndex == 0){
        alert("É necessário o preenchimento dos filtros");
        return false;    
    }else{
        return true;
    }

}


function pesquisarPagina(id) {

    //if(verificaLimpeza()){
        document.forms[0].method = "POST";
        document.forms[0].target = '';
        document.forms[0].nrPag.value=id;
        document.forms[0].action = 'getPagina.do?dtIni='+dadosArquivoPaginacao[0]
                                    +'&dtFim='+dadosArquivoPaginacao[1]
                                    +'&nrImei=' + dadosArquivoPaginacao[2]
                                    +'&log=' + dadosArquivoPaginacao[3]
                                    +'&opc=' + dadosArquivoPaginacao[4]
                                    +'&doc=' + dadosArquivoPaginacao[5]+'\'';
        //document.forms[0].action = 'getPagina.do;
        mostrar_div();
        document.forms[0].submit();
    //}else{
        //return false;
    //} 
    
    
}
function pesquisaAnterior(){
    //if(verificaLimpeza()){
        document.forms[0].method = "POST";
        document.forms[0].nrPag.value=parseInt(document.forms[0].nrPag.value)-parseInt("1");
        document.forms[0].target = '';
        document.forms[0].action = 'getPagina.do?dtIni='+dadosArquivoPaginacao[0]
                                    +'&dtFim='+dadosArquivoPaginacao[1]
                                    +'&nrImei=' + dadosArquivoPaginacao[2]
                                    +'&log=' + dadosArquivoPaginacao[3]
                                    +'&opc=' + dadosArquivoPaginacao[4]
                                    +'&doc=' + dadosArquivoPaginacao[5]+'\'';
        mostrar_div();
        document.forms[0].submit(); 
    //}else{
        //return false;
    //}
}
function pesquisaProxima(){
    //if(verificaLimpeza()){
        document.forms[0].method = "POST";
        document.forms[0].nrPag.value=parseInt(document.forms[0].nrPag.value)+parseInt("1");
        document.forms[0].target = '';
        document.forms[0].action = 'getPagina.do?dtIni='+dadosArquivoPaginacao[0]
                                    +'&dtFim='+dadosArquivoPaginacao[1]
                                    +'&nrImei=' + dadosArquivoPaginacao[2]
                                    +'&log=' + dadosArquivoPaginacao[3]
                                    +'&opc=' + dadosArquivoPaginacao[4]
                                    +'&doc=' + dadosArquivoPaginacao[5]+'\'';
        mostrar_div();
        document.forms[0].submit(); 
    //}else{
        //return false;
    //}
}

function download(){
    //if(verificaLimpeza()){
    
        document.forms[0].method = "POST";
        document.forms[0].target = 'ifrmDownload';
        document.forms[0].action = "gerarArquivoParametros.do?dtIni="+dadosArquivo[0]
                                    +"&dtFim="+dadosArquivo[1]
                                    +"&nrImei=" + dadosArquivo[2]
                                    +"&log=" + dadosArquivo[3]
                                    +"&opc=" + dadosArquivo[4]
                                    +"&doc=" + dadosArquivo[5];
        mostrar_div();
        document.forms[0].submit();
    //}else{
        //return false;
    //}
}