//--- Para validar os campos basta adicionar o onkeyup("checaXXXX(this)")
oldObj = "";
oldValor = "";
inteiro = new RegExp("[0-9]");
decimal = new RegExp("[\,]");
arroba = new RegExp("[@]");
ponto = new RegExp("[\.]");
hexa = new RegExp("[0-9a-fA-F]");
minutos = new RegExp("\:");
semCaracterEspeciais = new RegExp("[\"!@#$%&*{}^~´`?/><()=+;?,.\\|]");



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

function checaInteiro(obj){
  valor = obj.value;
  for(i=0;i<valor.length;i++){
    if(!inteiro.test(valor.charAt(i))){
      valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
      i = -1;
    }
  }
  obj.value = valor;
}

function checaHexa(obj){
  valor = obj.value;
  for(i=0;i<valor.length;i++){
    if(!hexa.test(valor.charAt(i))){
      valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
      i = -1;
    }
  }
  obj.value = valor;
}
function checaReal(obj){
  valorDecimal = "";
  valor = obj.value;
  setValor = valor;
  if(decimal.test(valor)){
    valorSplit = valor.split(",");
    valor = valorSplit[0];
    valorDecimal = valorSplit[1];
    virgula = true;
  }else{
    virgula = false;
  }
  if(setValor != oldValor || oldObj != obj){
    for(i=0;i<valor.length;i++){
      if(!inteiro.test(valor.charAt(i))){
        valor = valor.substring(0,i) + valor.substring(i+1,valor.length)
        if(valor.length == 1){
          !inteiro.test(valor)?valor = "":0;
        }
        i = -1;
      }
    }
    for(i=0;i<valorDecimal.length;i++){
      if(!inteiro.test(valorDecimal.charAt(i))){
        valorDecimal = valorDecimal.substring(0,i) + valorDecimal.substring(i+1,valorDecimal.length)
        if(valorDecimal.length == 1){
          !inteiro.test(valorDecimal)?valorDecimal = "":0;
        }
        i = -1;
      }
    }
    if(valor.length < 1){
      valor = ""
    }
    if(virgula){
      valor == ""?valor = "0":0;
      valor = valor + "," + valorDecimal.substring(0,2);
    }
    oldValor = valor;
    oldObj = obj;
    obj.value = valor;
  }
}

function checaCEP(obj){
  valor = obj.value;
  if(valor != oldValor || oldObj != obj){
    for(i=0;i<valor.length;i++){
      if(!inteiro.test(valor.charAt(i))){
        valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
        if(valor.length == 1){
          !inteiro.test(valor)?valor = "":0;
        }
        i = -1;
      }
    }
    if(valor.length < 1){
      valor = "";
    }else if(valor.length > 5 && valor.length < 9){
      valor = valor.substring(0,5) + "-" +  valor.substring(5,valor.length);
    }else if(valor.length > 8){
      valor = valor.substring(0,5) + "-" +  valor.substring(5,8);
    }
    obj.value = valor;
    oldValor = valor;
    oldObj = obj;
  }
}
/*
function checaTelefone(obj){
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
    }else if(valor.length < 3){
      valor = "(" + valor.substring(0,2);
    }else if(valor.length < 7){
      valor = "(" + valor.substring(0,2) + ")" + valor.substring(2,6);
    }else{
      valor = "(" + valor.substring(0,2) + ")" + valor.substring(2,6) + "-" + valor.substring(6,10);
    }
    obj.value = valor;
    oldValor = valor;
    oldObj = obj;
  }
}
*/
/*
 * Função para formataçao de telefones considerando telefones com 7 e 8 digitos.
 */
function checaTelefoneNew(obj) {
    valor = obj.value;
    var tecla = (window.Event) ? event.which : event.keyCode;
    numeroSemFormat = valor.replace(/\D/g,"");
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
* Função para formatação de telefones considerando números com 7 e 8 dígitos.
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

function checaTelefoneRural(obj){
  valor = obj.value;
  if(valor != oldValor || oldObj != obj){
    obj.value = valor;
    oldValor = valor;
    oldObj = obj;
  }
}


function checaHora(obj){
  valorMinutos = "";
  valor = obj.value;
  setValor = valor;
  if(minutos.test(valor)){
    valorSplit = valor.split(":");
    valor = valorSplit[0];
    valorMinutos = valorSplit[1];
    doispontos = true;
  }else{
    doispontos = false;
  }
  if(setValor != oldValor || oldObj != obj){
    for(i=0;i<valor.length;i++){
      if(!inteiro.test(valor.charAt(i))){
        valor = valor.substring(0,i) + valor.substring(i+1,valor.length)
        if(valor.length == 1){
          !inteiro.test(valor)?valor = "":0;
        }
        i = -1;
      }
    }
    for(i=0;i<valorMinutos.length;i++){
      if(!inteiro.test(valorMinutos.charAt(i))){
        valorMinutos = valorMinutos.substring(0,i) + valorMinutos.substring(i+1,valorMinutos.length)
        if(valorMinutos.length == 1){
          !inteiro.test(valorMinutos)?valorMinutos = "":0;
        }
        i = -1;
      }
    }
    if(valor.length < 1){
      valor = ""
    }else if(valor.length > 2 && !doispontos){
      valor = valor.substring(0,2) + ":" + valor.substring(2,valor.length);
		}
    if(doispontos){
      valor == ""?valor = "0":0;
      valor = valor + ":" + valorMinutos.substring(0,2);
    }
    oldValor = valor;
    oldObj = obj;
    obj.value = valor;
  }
}

function checaData(obj){
  valor = obj.value;
  if(valor != oldValor || oldObj != obj){
    for(i=0;i<valor.length;i++){
      if(!inteiro.test(valor.charAt(i))){
        valor = valor.substring(0,i) + valor.substring(i+1,valor.length)
        i = -1;
      }
    }
    if(valor.length < 1){
      valor = ""
    }else if(valor.length > 2 && valor.length < 5){
      valor = valor.substring(0,2) + "/" + valor.substring(2,4)
    }else if(valor.length > 4){
      valor = valor.substring(0,2) + "/" + valor.substring(2,4) + "/" + valor.substring(4,8)
    }
    obj.value = valor;
    oldValor = valor;
    oldObj = obj;
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

function getMaskCPF(nrCPF){nrCPF=trim(nrCPF);if(nrCPF.length!=11)return nrCPF;else return nrCPF.substring(0,3)+"."+nrCPF.substring(3,6)+"."+nrCPF.substring(6,9)+"-"+nrCPF.substring(9,nrCPF.length);}
function getMaskCNPJ(nrCNPJ){nrCNPJ=trim(nrCNPJ);if(nrCNPJ.length!=15)return nrCNPJ;else return nrCNPJ.substring(0,3)+"."+nrCNPJ.substring(3,6)+"."+nrCNPJ.substring(6,9)+"/"+nrCNPJ.substring(9,13)+"-"+nrCNPJ.substring(13,nrCNPJ.length);}

function getMaskTelefone(nrTelefone) {

    nrTelefone = obj.value;
    var tecla = (window.Event) ? event.which : event.keyCode;
    numeroSemFormat = removeMascara(nrTelefone);
    var strCheck = '()-';
    key = nrTelefone.charAt(nrTelefone.length-1);
    if (nrTelefone.length!=7 && nrTelefone.length!=3 && nrTelefone.length!=1){
        nrTelefone = formatTelefoneNew(numeroSemFormat);
    }
    //Para apagar os simbolos "()-" ao clicar no backspace
    if (tecla==8){ //Backspace
        if (strCheck.indexOf(key) != -1){
            nrTelefone = nrTelefone.substring(0,nrTelefone.length-1)
        }
    }else{
        for(i=0;i<nrTelefone.length;i++){
            if(!inteiro.test(nrTelefone.charAt(i))){
                nrTelefone = nrTelefone.substring(0,i) + nrTelefone.substring(i+1,nrTelefone.length);
                i = -1;
            }
        }
    }
	return nrTelefone;
}

function formatTelNo(telNoObj) {
    if (telNoObj.value == "") return;
    var phone = new String (telNoObj.value);
    phone = phone.substring(0,13);
    if (phone.match (".[0-9]{3}.[0-9]{3}-[0-9]{4}") == null) {
        if (phone.match (".[0-9]{2}.[0-9]{3}-[0-9]{4}|" + ".[0-9].[0-9]{3}-[0-9]{4}|" +
            ".[0-9]{3}.[0-9]{2}-[0-9]{4}|" + ".[0-9]{3}.[0-9]-[0-9]{4}") == null) {
            var phoneNumeric = phoneChar = "", i;
            for (i=0;i<phone.length;i++) {
                phoneChar = phone.substr (i,1);
                if (!isNaN (phoneChar) && (phoneChar != " ")) phoneNumeric = phoneNumeric + phoneChar;
            }
            phone = "";
            for (i=0;i<phoneNumeric.length;i++) {
                if (i == 0) phone = phone + "(";
                if (i == 2) phone = phone + ")";
                if (i == 6) phone = phone + "-";
                phone = phone + phoneNumeric.substr (i,1)
            }
        }
    } else {
        phone = "(" + phone.substring (1,4) + ") " + phone.substring (5,8) + "-" + phone.substring(9,13);
    }
    if (phone != telNoObj.value) telNoObj.value = phone;
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

function checaCPFCNPJ(obj){
	if(obj.value.length < 15){
		checaCPF(obj);
	}else{
		checaCNPJ(obj);
	}
}

function checaTextarea(obj, tamanho){
  obj.value.length>tamanho?obj.value = obj.value.substr(0,tamanho):0;
}

isValidIPAddress = function(ipaddr) {
   var re = /^\d{1,3}\.\d{1,3}\.\d{1,3}\.\d{1,3}$/;
   if (re.test(ipaddr)) {
      var parts = ipaddr.split(".");
      if (parseInt(parseFloat(parts[0])) == 0) { return false; }
      for (var i=0; i<parts.length; i++) {
         if (parseInt(parseFloat(parts[i])) > 255) { return false; }
      }
      return true;
   } else {
      return false;
   }
}

isValidURL = function (s) {
	var regexp = /(ftp|http|https):\/\/(\w+:{0,1}\w*@)?(\S+)(:[0-9]+)?(\/|\/([\w#!:.?+=&%@!\-\/]))?/
	return regexp.test(s);
}

function validaCPF(valor){
  cpf = limpaInteiro(valor);
  if (cpf.length != 11 || cpf == "00000000000" || cpf == "11111111111" || cpf == "22222222222" || cpf == "33333333333" || cpf == "44444444444" || cpf == "55555555555" || cpf == "66666666666" || cpf == "77777777777" || cpf == "88888888888" || cpf == "99999999999"){
    return false;
  }
  soma = 0;
  for (i=0; i < 9; i ++){
    soma += parseInt(cpf.charAt(i)) * (10 - i);
  }
  resto = 11 - (soma % 11);
  if (resto == 10 || resto == 11){
    resto = 0;
  }
  if (resto != parseInt(cpf.charAt(9))){
    return false;
  }
  soma = 0;
  for (i = 0; i < 10; i ++){
    soma += parseInt(cpf.charAt(i)) * (11 - i);
  }
  resto = 11 - (soma % 11);
  if (resto == 10 || resto == 11){
    resto = 0;
  }
  if (resto != parseInt(cpf.charAt(10))){
    return false;
  }else{
    return true;
  }
}

function validaCNPJ(valor){
  cnpj = limpaInteiro(valor);
  if (cnpj.length != 14 || cnpj == "00000000000000" || cnpj == "11111111111111" || cnpj == "22222222222222" || cnpj == "33333333333333" || cnpj == "44444444444444" || cnpj == "55555555555555" || cnpj == "66666666666666" || cnpj == "77777777777777" || cnpj == "88888888888888" || cnpj == "99999999999999"){
    return false;
  }
  soma = 0;
  j = 5;
  for (i=0; i < 12; i ++){
    soma += parseInt(cnpj.charAt(i)) * (j);
    j--;
    if (j == 1){
      j = 9;
    }
  }
  resto = 11 - (soma % 11);
  if (resto == 10 || resto == 11){
    resto = 0;
  }
  if (resto != parseInt(cnpj.charAt(12))){
    return false;
  }
  for (i = 0; i < 13; i ++){
    if (i==4){
      soma -= parseInt(cnpj.charAt(i)) * 7;
    }else if(i==12){
      soma += parseInt(cnpj.charAt(i)) * 2;
    }else{
      soma += parseInt(cnpj.charAt(i));
    }
  }
  resto = 11 - (soma % 11);
  if (resto == 10 || resto == 11){
    resto = 0;
  }
  if (resto != parseInt(cnpj.charAt(13))){
    return false;
  }else{
    return true;
  }
}

/**
 * Funcao utilizada para validar se uma data é real ou não. Considera
 * número do mês, quantidades de dias em um mês (30 ou 31) e também
 * anos bissextos.
 * @param: input field
 * @return: boolean
 **/
function validaData(vl) {
    var RegExPattern = /^((((0?[1-9]|[12]\d|3[01])[\.\-\/](0?[13578]|1[02])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|[12]\d|30)[\.\-\/](0?[13456789]|1[012])[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|((0?[1-9]|1\d|2[0-8])[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?\d{2}))|(29[\.\-\/]0?2[\.\-\/]((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00)))|(((0[1-9]|[12]\d|3[01])(0[13578]|1[02])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|[12]\d|30)(0[13456789]|1[012])((1[6-9]|[2-9]\d)?\d{2}))|((0[1-9]|1\d|2[0-8])02((1[6-9]|[2-9]\d)?\d{2}))|(2902((1[6-9]|[2-9]\d)?(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00)|00))))$/;
    var errorMessage = 'Please enter valid date as month, day, and four digit year.\nYou may use a slash, hyphen or period to separate the values.\nThe date must be a real date. 30/2/2000 would not be accepted.\nFormay dd/mm/yyyy.';

    if( vl.length != 10 ) {
        return false;
    }

    if ((vl.match(RegExPattern)) && (vl != '')) {
        return true;
    } else {
        return false;
    }
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

/*
function validaDataFinalEx(dataInicial, dataFinal, horaInicial, horaFinal) {

Formato dos dados:
dataInicial  = dd/mm/yyyy
dataFinal    = dd/mm/yyyy
horasInicial = hh:mm
horaFinal    = hh:mm
*/
function validaDataFinalEx(dataInicial,dataFinal, horaInicial, horaFinal) {
	if (dataInicial == "" || dataFinal == ""){
		return false;
	}else{


		splitDataInicial = dataInicial.split('/');
        splitDataInicial[0] == "08"?splitDataInicial[0] = "8":0;splitDataInicial[0] == "09"?splitDataInicial[0] = "9":0;splitDataInicial[1] == "08"?splitDataInicial[1] = "8":0;splitDataInicial[1] == "09"?splitDataInicial[1] = "9":0;
		splitDataFinal = dataFinal.split('/');
        splitDataFinal[0] == "08"?splitDataFinal[0] = "8":0;splitDataFinal[0] == "09"?splitDataFinal[0] = "9":0;splitDataFinal[1] == "08"?splitDataFinal[1] = "8":0;splitDataFinal[1] == "09"?splitDataFinal[1] = "9":0;

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
                else if(parseInt(splitDataFinal[0]) == parseInt(splitDataInicial[0]))
                {

                   splitHoraInicial = horaInicial.split(':');
                   splitHoraFinal = horaFinal.split(':');

                   //Se hora final foi maior que inicial
                   if(parseInt(splitHoraFinal[0]) < parseInt(splitHoraInicial[0]))
                   {
                        return false;
                   }
                   //Se a hora for igual e os minutos forem menores
                   else if((parseInt(splitHoraFinal[0]) == parseInt(splitHoraInicial[0])) && (parseInt(splitHoraFinal[1]) < parseInt(splitHoraInicial[1])))
                   {
                        return false;
                   }
                }
			}
		}
		return true;
	}
}

function validaEmail(valor){
  if((!(arroba.test(valor)) || !(ponto.test(valor))) || valor.length < 5){
    return false;
  }else if ((valor.charAt(0) == "@") || (valor.charAt(0) == ".") || (valor.charAt(valor.length - 1) == ".") || (valor.charAt(valor.length - 1) == "@")){
    return false;
  }else if (ponto.test(valor.charAt(valor.indexOf('@') - 1)) || ponto.test(valor.charAt(valor.indexOf('@') + 1))){
    return false;
  }else if (arroba.test(valor.charAt(valor.indexOf('@') - 1)) || arroba.test(valor.charAt(valor.indexOf('@') + 1))){
    return false;
  }else if (ponto.test(valor.charAt(valor.indexOf('.') - 1)) || ponto.test(valor.charAt(valor.indexOf('.') + 1))){
    return false;
  }else if (arroba.test(valor.charAt(valor.indexOf('.') - 1)) || arroba.test(valor.charAt(valor.indexOf('.') + 1))){
    return false;
  }else{
    return true;
  }
}

function validaNome(valor){
  for(i=0;i<valor.length;i++){
    if(valor.charAt(i) == " "){
      i++
      if(valor.charAt(i) != ""){
        return true;
      }else{
        return false;
      }
    }
  }
}

function validaHora(hora) {
  valor = hora.split(':');
  valorMinutos = valor[1];
	valor[0] == "08"?valor[0] = "8":0;valor[0] == "09"?valor[0] = "9":0;valor[1] == "08"?valor[1] = "8":0;valor[1] == "09"?valor[1] = "9":0;
  valor[0] = parseInt(valor[0]);valor[1] = parseInt(valor[1]);
  if (valorMinutos && valorMinutos.length < 2){
    return false;
  }else if (valor[0] < 24 && valor[1] < 60){
    return true;
  }
  return false;
}


function limpaInteiro(valor){
  for(i=0;i<valor.length;i++){
    if(!inteiro.test(valor.charAt(i))){
      valor = valor.substring(0,i) + valor.substring(i+1,valor.length)
      i = -1;
    }
  }
  return valor;
}

function trim(valor) {
   charInicio = valor.substring(0, 1);
   while (charInicio == " ") {
      valor = valor.substring(1, valor.length);
      charInicio = valor.substring(0, 1);
   }
   charFim = valor.substring(valor.length-1, valor.length);
   while (charFim == " ") {
      valor = valor.substring(0, valor.length-1);
      charFim = valor.substring(valor.length-1, valor.length);
   }
   while (valor.indexOf("  ") != -1) {
      valor = valor.substring(0, valor.indexOf("  ")) + valor.substring(valor.indexOf("  ")+1, valor.length);
   }
   return valor;
}

function somaDiasData(objOrigem,objDestino,dias){
    if (objOrigem.value != ""){
        data = objOrigem.value.split("/");
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
        objDestino.value = data[0] + "/" + data[1] + "/" + data[2];
    }else{
        alert("Favor selecionar uma Data!")
    }
}

//--- Para o funcionamento do script basta adicionar no botao onClick="moveXXX(campo de origem,campo de destino)"
//--- Para o funcionamento do script com doubleclick basta adicionar no select onDblClick="moveXXX(campo de origem,campo de destino)"
function move(objOrigem, objDestino){
  for (i=0; i<objOrigem.options.length; i++){
    if (objOrigem.options[i].selected){
      objDestino.options[objDestino.options.length] = new Option(objOrigem.options[i].text,objOrigem.options[i].value);
      objOrigem.options[i] = null;
      i--;
    }
  }
}

function moveTodos(objOrigem, objDestino){
  lenghtOrigem = objOrigem.options.length;
  lenghtDestino = objDestino.options.length;
  todosText = new Array();
  todosValue = new Array();
  for (i=0; i<objOrigem.options.length; i++){
    todosText[i] = objOrigem.options[i].text;
    todosValue[i] = objOrigem.options[i].value;
  }
  for (i=0; i<objDestino.options.length; i++){
    todosText[objOrigem.options.length + i] = objDestino.options[i].text;
    todosValue[objOrigem.options.length + i] = objDestino.options[i].value;
  }
  for (i=lenghtOrigem; i>=0; i--){
    objOrigem.options[i] = null;
  }
  for (i=lenghtDestino; i>=0; i--){
    objDestino.options[i] = null;
  }
  for (i=0; i<todosText.length; i++){
    objDestino.options[i] = new Option(todosText[i],todosValue[i]);
  }
}
function moveFixo(objOrigem, objDestino){
  erro = false;
  for (i=0; i<objOrigem.options.length; i++){
    if (objOrigem.options[i].selected){
      for (j=0; j<objDestino.options.length; j++){
        if (objOrigem.options[i].text == objDestino.options[j].text){
          alert("Item já selecionado!")
          erro = true;
        }
      }
      !erro?objDestino.options[objDestino.options.length] = new Option(objOrigem.options[i].text,objOrigem.options[i].value):0;
    }
  }
}

function deleteFixo(obj){
  for (i=0; i<obj.options.length; i++){
    if (obj.options[i].selected){
      obj.options[i] = null;
    }
  }
}

function moveFixoTodos(objOrigem, objDestino){
  lenghtOrigem = objOrigem.options.length;
  lenghtDestino = objDestino.options.length;
  todosText = new Array();
  todosValue = new Array();
  for (i=0; i<objOrigem.options.length; i++){
    todosText[i] = objOrigem.options[i].text;
    todosValue[i] = objOrigem.options[i].value;
  }
  for (i=lenghtDestino; i>=0; i--){
    objDestino.options[i] = null;
  }
  for (i=0; i<todosText.length; i++){
    objDestino.options[i] = new Option(todosText[i],todosValue[i]);
  }
}
function deleteFixoTodos(obj){
  lenghtObj = obj.options.length;
  for (i=lenghtObj; i>=0; i--){
    obj.options[i] = null;
  }
}

function Format(value,format) {
    value = value.replace(/\D/g,"");
    var result="";
    if(format.length < value.length)
        value = value.substring(0,format.length -1);;
    for(i=0,j=0;(i<format.length)&&(j<value.length);i++)
    {
        var ch = format.charAt(i);
        if(ch == '#')
        {
            result += value.charAt(j++);
            continue;
        }
        result += ch;
    }
    return result;
}

function onlyNumbers(valor) {
    for (i=0; i <valor.length; i++) {
        if (!inteiro.test(valor.charAt(i))) {
            valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
            i = -1;
        }
    }
    return valor;
}

/*
 * Função utilizada para remover um determinado objeto <tr>
 * de uma tabela de dados.
 * @tableId ID da tabela-pai
 * @trIndex índice do <tr> dentro da tabela
 * @Necessária API do framework prototype.js
 */
function removeTRByIndex(tableId, trIndex) {
    var i = 0;
    var j = 0;
    var poRow = $(tableId).tBodies[0];
    for (i=0; i < poRow.childNodes.length; i++) {
        if (poRow.childNodes[i].tagName == "TR") {
            if (j==trIndex) {
                poRow.childNodes[i].id = "trToDelete";
                removeElementById("trToDelete");
                break;
            }
            j++;
        }
        break;
    }
}

/*
 * Função utilizada para remover um determinado objeto
 * a partir de seu id.
 * @obj Objeto a ser removido
 * @Necessária API do framework prototype.js
 */
function removeElementById(obj) {
    if ($(obj).parentNode && $(obj).parentNode.removeChild($(obj))) {
        try {
            $(obj).parentNode.removeChild($(obj));
        } catch(e){}
    }
}

/*
 * Função utilizada para remover um determinado objeto
  * @obj Objeto a ser removido
 * @Necessária API do framework prototype.js
 */
function removeElement(obj) {
    if (obj.parentNode && obj.parentNode.removeChild(obj)) {
        try {
            obj.parentNode.removeChild(obj);
        } catch(e){}
    }
}

function limitaQtdeCaracteres(obj, qtdeCaracteres) {
    if (obj.value.length > qtdeCaracteres) {
        obj.value = obj.value.substring(0,qtdeCaracteres);
    }
}

formMessage = function(arrayMessages){
    if(arrayMessages && arrayMessages.length>0){
        var s = (arrayMessages.length>1)?"s":"";
        var msg = "ATENÇÃO: O preenchimento do"+s+" seguinte"+s+" campo"+s+" é obrigatório: \n\n";
        for(i=0;i<arrayMessages.length;i++){
            msg += arrayMessages[i];
            if(i<arrayMessages.length-2)msg += ", ";
            else if(i<arrayMessages.length-1)msg += " e ";
        }
        msg += ".";
        alert(msg);
    }
}

validaDataInput = function(data){
    if(data.value == '')
        return false;
    if(!validate_date(data.value)){
        data.value = '';
        data.focus();
        alert("Data inválida");
    }
}

valueExistsInSelect = function(obj, optionValue) {
    for (var i = 0; i < obj.options.length; i++) {
        if (obj.options[i].value == optionValue) {
            return true;
        }
    }
    return false;
}

diasDecorridos = function(dt1, dt2) {

    var minuto = 60000;
    var dia = minuto * 60 * 24;
    var horarioVerao = 0;

    dt1.setHours(0);
    dt1.setMinutes(0);
    dt1.setSeconds(0);
    dt2.setHours(0);
    dt2.setMinutes(0);
    dt2.setSeconds(0);

    /*var fh1 = dt1.getTimezoneOffset();
    var fh2 = dt2.getTimezoneOffset();

    if (dt2 > dt1) {
        horarioVerao = (fh2 - fh1) * minuto;
    } else {
        horarioVerao = (fh1 - fh2) * minuto;
    }*/

    var dif = Math.abs(dt2.getTime() - dt1.getTime()) - horarioVerao;
    return Math.ceil(dif / dia);
}