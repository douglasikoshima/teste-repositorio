/**
 * @fileOverview Biblioteca Teclado
 * @author Gilberto Holms
 * @date 24/04/2008
 */


/**
 * Realiza filtragem alfanumerica em campos de input (evento onkeypress)
 * @example onkeypress="return mascaraAlfaNumerica(event, 1,0,1,0,0,1,1);"
 * @param {Object} evento Objeto event
 * @param {Boolean} hasNumbers Flag de Numero "0-9"
 * @param {Boolean} hasVirgula Flag de Virgula ","
 * @param {Boolean} hasPonto Flag de Ponto "."
 * @param {Boolean} hasHifen Flag de Hifen "-"
 * @param {Boolean} hasBarra Flag de Barra "/"
 * @param {Boolean} hasUnderline Flag de Underline "_"
 * @param {Boolean} hasArroba Flag de Arroba "@"
 * @return {Boolean} Retorna true se passar no filtro, false caso contrario
 */
function mascaraAlfaNumerica(evento, hasNumbers, hasVirgula, hasPonto, hasHifen, hasBarra, hasUnderline, hasArroba) {
	var tecla = new Number();
    if(window.event) {
		tecla = evento.keyCode; 
	} 
	else if(evento.which) {
		tecla = evento.which; 
	} else {
		return true;
	}
	
	if(tecla == 8)
		return true;

	if ( (tecla >= 65  && tecla <= 90)   || 
		 (tecla >= 97  && tecla <= 122)  || 
		 (tecla >= 192 && tecla <= 221)  || 
		 (tecla >= 224 && tecla <= 246)  || 
		 (tecla >= 249 && tecla <= 255) ) {
		return true;
	}
	else {
		if ( (tecla == 44 && hasVirgula) || 
			 (tecla == 45 && hasHifen) || 
			 (tecla == 46 && hasPonto) || 
			 (tecla == 47 && hasBarra) || 
			 (tecla == 64 && hasArroba) || 
			 (tecla == 95 && hasUnderline) || 
			 ( (tecla >= 48 && tecla <= 57) && hasNumbers ) ) {
			return true;
		}
		return false;
	}
}


/**
 * Realiza filtragem numerica em campos de input (evento onkeypress).
 * @example onkeypress="return mascaraNumerica(event, 1,1,0,0);"
 * @param {Object} evento Objeto event
 * @param {Boolean} hasVirgula Flag de Virgula ","
 * @param {Boolean} hasPonto Flag de Ponto "."
 * @param {Boolean} hasHifen Flag de Hifen "-"
 * @param {Boolean} hasBarra Flag de Barra "/"
 * @return {Boolean} Retorna true se passar no filtro, false caso contrario
 */
function mascaraNumerica(evento, hasVirgula, hasPonto, hasHifen, hasBarra) {
	var tecla = new Number();
    if(window.event) {
		tecla = evento.keyCode; 
	} 
	else if(evento.which) {
		tecla = evento.which; 
	} else {
		return true;
	}
	
	if(tecla == 8)
		return true;
	
	var algarismos = '0123456789';

	if ( tecla == 36 || tecla == 40 || tecla == 41 || tecla == 42 || 
		 tecla == 43 || tecla == 63 || tecla == 91 || tecla == 92 || 
		 tecla == 94 || tecla == 93 || tecla == 124 ) {
		return false;
	}
	else {
	 	if ( (tecla == 44 && hasVirgula) || 
	 		 (tecla == 45 && hasHifen) || 
	 		 (tecla == 46 && hasPonto) || 
	 		 (tecla == 47 && hasBarra) ) {
			return true;
		}
	 	else {
			if ( !(algarismos.search( String.fromCharCode (tecla) ) != -1) || tecla == 46 ) {
				return false;
			}
			else {
				return true;
			}
	 	}
	}
}

function proibeTecla(evento, tecla){
	var teclaApertada = new Number();
    if(window.event) {
		teclaApertada = evento.keyCode; 
	} 
	else if(evento.which) {
		teclaApertada = evento.which; 
	} else {
		return true;
	}

	if(teclaApertada == tecla){
		return false
	}
	
	return true;
}

function semPontoVirgula(evento){
	return proibeTecla(evento, 59);
}

function semVirgula(evento){
	return proibeTecla(evento, 44);
}

/*
Função descontinuada, foram encontrados erros na mesma, para formatação monetária utilizar a função mascara(this, valor).
Erros Encontrados:
	- A função permite o "ctrl + v" de valores Alphanuméricos
	- A Função não respeita o maxlenght do campo.
*/
function formatar_moeda(campo, separador_milhar, separador_decimal, tecla) {
	var sep = 0;
	var key = '';
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789';
	var aux = aux2 = '';
	var whichCode = (window.Event) ? tecla.keyCode : tecla.which;
	
	if (whichCode == 13) return true;
	if (whichCode == 8) return true;
	
	key = String.fromCharCode(whichCode);
	if (strCheck.indexOf(key) == -1) return false;
	len = campo.value.length;
	for(i = 0; i < len; i++)
	if ((campo.value.charAt(i) != '0') && (campo.value.charAt(i) != separador_decimal)) break;
 	aux = '';
	for(; i < len; i++)
	if (strCheck.indexOf(campo.value.charAt(i))!=-1)
		aux += campo.value.charAt(i);
	 	aux += key;
	 	len = aux.length;
 		if(len == 0) campo.value = '';
 		if(len == 1) campo.value = '0'+ separador_decimal + '0' + aux;
 		if(len == 2) campo.value = '0'+ separador_decimal + aux;

		if(len > 2) {
			aux2 = '';
 		for (j = 0, i = len - 3; i >= 0; i--) {
				if (j == 3) {
					aux2 += separador_milhar;
					j = 0;
				}
			aux2 += aux.charAt(i);
				j++;
			}
			
			campo.value = '';
			len2 = aux2.length;
			for (i = len2 - 1; i >= 0; i--)
			campo.value += aux2.charAt(i);
			campo.value += separador_decimal + aux.substr(len - 2, len);
		}
	 return false;
 }
 
 function formatar_moeda_negativa(campo, separador_milhar, separador_decimal, tecla) {
	var sep = 0;
	var key = '';
	var i = j = 0;
	var len = len2 = 0;
	var strCheck = '0123456789-';
	var aux = aux2 = '';
	var whichCode = (window.Event) ? tecla.keyCode : tecla.which;

	if (whichCode == 13) return true;
	if (whichCode == 8) return true;

	key = String.fromCharCode(whichCode);
	if(key == '-') {
		if(campo.value.length != 0) {
			return false;
		}
	}
	if (strCheck.indexOf(key) == -1) return false;
	len = campo.value.length;
	for(i = 0; i < len; i++)
	if ((campo.value.charAt(i) != '0') && (campo.value.charAt(i) != separador_decimal)) break;
 	aux = '';
	for(; i < len; i++)
	if (strCheck.indexOf(campo.value.charAt(i))!=-1)
		aux += campo.value.charAt(i);
	 	aux += key;
	 	len = aux.length;
 		if(len == 0) campo.value = '';
 		if(len == 1) campo.value = '0'+ separador_decimal + '0' + aux;
 		if(len == 2) campo.value = '0'+ separador_decimal + aux;

		if(len > 2) {
			aux2 = '';
 		for (j = 0, i = len - 3; i >= 0; i--) {
				if (j == 3) {
					aux2 += separador_milhar;
					j = 0;
				}
			aux2 += aux.charAt(i);
				j++;
			}
			
			campo.value = '';
			len2 = aux2.length;
			for (i = len2 - 1; i >= 0; i--)
			campo.value += aux2.charAt(i);
			campo.value += separador_decimal + aux.substr(len - 2, len);
		}
	 return false;
 }


 
 /*Função Pai de Mascaras*/
function mascara(o,f){
    v_obj=o
    v_fun=f
    setTimeout("execmascara()",1)
}

/*Função que Executa os objetos*/
function execmascara(){
    v_obj.value=v_fun(v_obj.value)
}

/*Função que Determina as expressões regulares dos objetos*/
function leech(v){
    v=v.replace(/o/gi,"0")
    v=v.replace(/i/gi,"1")
    v=v.replace(/z/gi,"2")
    v=v.replace(/e/gi,"3")
    v=v.replace(/a/gi,"4")
    v=v.replace(/s/gi,"5")
    v=v.replace(/t/gi,"7")
    return v
}

/*Função que permite apenas numeros*/
function integer(v){
    return v.replace(/\D/g,"")
}

/*Função que padroniza telefone (11) 4184-1241*/
function telefone(v){
    v=v.replace(/\D/g,"")                 
    v=v.replace(/^(\d\d)(\d)/g,"($1) $2") 
    v=v.replace(/(\d{4})(\d)/,"$1-$2")    
    return v
}

/*Função que padroniza telefone (11) 41841241*/
function telefoneCall(v){
    v=v.replace(/\D/g,"")                 
    v=v.replace(/^(\d\d)(\d)/g,"($1) $2")    
    return v
}

/*Função que padroniza CPF*/
function cpf(v){
    v=v.replace(/\D/g,"")                    
    v=v.replace(/(\d{3})(\d)/,"$1.$2")       
    v=v.replace(/(\d{3})(\d)/,"$1.$2")       
                                             
    v=v.replace(/(\d{3})(\d{1,2})$/,"$1-$2") 
    return v
}

/*Função que padroniza CEP*/
function cep(v){
    v=v.replace(/D/g,"")                
    v=v.replace(/^(\d{5})(\d)/,"$1-$2") 
    return v
}

/*Função que padroniza CNPJ*/
function cnpj(v){
    v=v.replace(/\D/g,"")                   
    v=v.replace(/^(\d{2})(\d)/,"$1.$2")     
    v=v.replace(/^(\d{2})\.(\d{3})(\d)/,"$1.$2.$3") 
    v=v.replace(/\.(\d{3})(\d)/,".$1/$2")           
    v=v.replace(/(\d{4})(\d)/,"$1-$2")              
    return v
}

/*Função que permite apenas numeros Romanos*/
function romanos(v){
    v=v.toUpperCase()             
    v=v.replace(/[^IVXLCDM]/g,"") 
    
    while(v.replace(/^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$/,"")!="")
        v=v.replace(/.$/,"")
    return v
}

/*Função que padroniza o Site*/
function site(v){
    v=v.replace(/^http:\/\/?/,"")
    dominio=v
    caminho=""
    if(v.indexOf("/")>-1)
        dominio=v.split("/")[0]
        caminho=v.replace(/[^\/]*/,"")
        dominio=dominio.replace(/[^\w\.\+-:@]/g,"")
        caminho=caminho.replace(/[^\w\d\+-@:\?&=%\(\)\.]/g,"")
        caminho=caminho.replace(/([\?&])=/,"$1")
    if(caminho!="")dominio=dominio.replace(/\.+$/,"")
        v="http://"+dominio+caminho
    return v
}

/*Função que padroniza DATA*/
function data(v){
    v=v.replace(/\D/g,"") 
    v=v.replace(/(\d{2})(\d)/,"$1/$2") 
    v=v.replace(/(\d{2})(\d)/,"$1/$2") 
    return v
}

/*Função que padroniza DATA*/
function hora(v){
    v=v.replace(/\D/g,"") 
    v=v.replace(/(\d{2})(\d)/,"$1:$2")  
    return v
}

/*Função que padroniza valor monétario*/
function valor(v){
    v=v.replace(/\D/g,"") //Remove tudo o que não é dígito
    v=v.replace(/^([0-9]{3}\.?){3}-[0-9]{2}$/,"$1.$2");
    v=v.replace(/(\d)(\d{2})$/,"$1,$2") //Coloca ponto antes dos 2 últimos digitos
    v=v.replace(/(\d{3})(\d)/g,"$1.$2")
    return v
}

/*Função que padroniza Area*/
function area(v){
    v=v.replace(/\D/g,"") 
    v=v.replace(/(\d)(\d{2})$/,"$1.$2") 
    return v
}