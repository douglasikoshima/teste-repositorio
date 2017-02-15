var isNav4, isNav, isIE;
if(parseInt(navigator.appVersion.charAt(0)) >= 4){
    isNav = (navigator.appName=="Netscape") ? true : false;
    isIE = (navigator.appName.indexOf("Microsoft") != -1) ? true : false;
}

if(navigator.appName=="Netscape"){
	isNav4 = (parseInt(navigator.appVersion.charAt(0))==4);
}

var codTeclaKeyDown;

function exibirValorFormatado(e) {
    var obj,tecla;
    //verificando se o que foi digitado é um número
    if(!soNumero(e)){
        return false;
    }
    obj   = (isNav) ? e.target : event.srcElement;
    codTecla = (isNav) ? e.which : event.keyCode;
    switch(codTeclaKeyDown){
        case 8:
            obj.value = formatarValor(obj.value.substring(0,obj.value.length-1));
            break;
        case 9:
            return true;
            break;
        case 46:
            obj.value = formatarValor(obj.value.substring(0,obj.value.length-1));
            break;
        default :
            if((codTecla>47)&&(codTecla<58)) {
                if(obj.maxLength>obj.value.length){
                    obj.value = formatarValor(obj.value + String.fromCharCode(codTecla));
                }
            }
    }
    return false;
}

function capturaCodTecla(e){
	codTeclaKeyDown = (isNav) ? e.which : event.keyCode;
	if (isIE) {
		switch (codTeclaKeyDown) {
			case 8:
				event.srcElement.value = formatarValor(event.srcElement.value.substring(0,event.srcElement.value.length-1));
				return false;
                break;
			case 46:
				event.srcElement.value = formatarValor(event.srcElement.value.substring(0,event.srcElement.value.length-1));
				return false;
                break;
		}
	}
}

function formatarValor(str){
    var decimal = '';
    var inteiro = '';
    var i       = 0;
    var count   = 0;
    STR = new String(str);
    STR = tirarZerosEsquerda(STR);
    if(STR.length == 1){
        inteiro  = '0';
        decimal = '0' + STR;
    }else{
        if(STR.length == 2){
            inteiro  = '0';
            decimal = STR;
        }else{
            decimal = STR.substring(STR.length-2, STR.length);
            i=3;
            count=0;
            while(i<=STR.length){
                if(count==3){
                    inteiro = '.' + inteiro;
                    count = 0;
                }
                inteiro = STR.charAt(STR.length-i) + inteiro;
                count++;
                i++;
            }
        }
    }
    if(inteiro == ''){
        inteiro = '0';
    }
    if(decimal == ''){
        decimal = '00';
    }
    return inteiro+','+decimal;
}

function tirarZerosEsquerda(STR){
    var sAux = '';
    STR = new String(STR);
    var i = 0;
    while(i < STR.length ){
		if((STR.charAt(i)!='.') && (STR.charAt(i)!=',')){
			sAux += STR.charAt(i);
		}
		i++
	}
    STR = new String(sAux);
    sAux = '';
    i = 0;
    while (i < STR.length ){
        if(STR.charAt(i) != '0'){
            sAux = STR.substring(i,STR.length)
            i = STR.length;
        }
        i++;
    }
    return  sAux;
}

function soNumero(e) {
	var keyNumber = (isIE) ? event.keyCode : e.which;
	if (((keyNumber<48)||(keyNumber>57)) && (keyNumber!=13) && (keyNumber!="0") && (keyNumber!=8)) {
		if (isIE) {
			event.keyCode=0
		}
		return false;
	}
	return true;
}