//Função para formatação genérica de campos
function Formatar(fsValor, foForm, foNome, fsFormato) { 
	var piLoop; 
	var psPalavra	= '';
	var psResposta	= ''; 
	var piTamanho	= 0; 
	var pParte_i	= '';
	var pParte_f	= '';
	var intDecimais = 2;
	intDecimais 	= 2;

	if (window.event.keyCode != 9 && window.event.keyCode != 16){
		if (fsFormato == "cgc") fsFormato = "cnpj";
		if (window.event.keyCode != 37 && window.event.keyCode != 39 && window.event.keyCode != 46) { 
			for(piLoop = 0; piLoop <= (fsValor.length-1); piLoop++) {
				switch (fsFormato) {
		          case 'texto':
                    return;
                    break;
		          case 'data': 
		            if (fsValor.charAt(piLoop) != '/' && fsValor.charAt(piLoop) >= '0' && fsValor.charAt(piLoop) <= '9')
		              psPalavra = psPalavra + fsValor.charAt(piLoop); 
		            break; 
		          case 'hora': 
		            if (fsValor.charAt(piLoop) != ':'  && fsValor.charAt(piLoop) >= '0' && fsValor.charAt(piLoop) <= '9') 
		              psPalavra = psPalavra + fsValor.charAt(piLoop); 
		            break; 
		          case 'telefone': 
					if (fsValor.charAt(piLoop) >= '0' && fsValor.charAt(piLoop) <= '9')
					  psPalavra = psPalavra + fsValor.charAt(piLoop); 
		            break; 
                    
		          case 'telefone_rural': 
					if (fsValor.charAt(piLoop) >= '0' && fsValor.charAt(piLoop) <= '9')
					  psPalavra = psPalavra + fsValor.charAt(piLoop); 
		            break; 
		          case 'moeda': 
		            if (fsValor.charAt(piLoop) != ',' && fsValor.charAt(piLoop) >= '0' && fsValor.charAt(piLoop) <= '9')
		              psPalavra = psPalavra + fsValor.charAt(piLoop); 
			          break; 
		          case 'cpf': 
		            if (fsValor.charAt(piLoop) != '.' && fsValor.charAt(piLoop) != '-' && fsValor.charAt(piLoop) >= '0' && fsValor.charAt(piLoop) <= '9')
		              psPalavra = psPalavra + fsValor.charAt(piLoop); 
		            break; 
		          case 'cnpj': 
		            if (fsValor.charAt(piLoop) >= '0' && fsValor.charAt(piLoop) <= '9')
		              psPalavra = psPalavra + fsValor.charAt(piLoop); 
		            break; 
		          case 'cnpj_filial': 
		            if (fsValor.charAt(piLoop) >= '0' && fsValor.charAt(piLoop) <= '9')
		              psPalavra = psPalavra + fsValor.charAt(piLoop); 
		            break; 
		          case 'cep': 
		            if (fsValor.charAt(piLoop) != '-' && fsValor.charAt(piLoop) >= '0' && fsValor.charAt(piLoop) <= '9')
		              psPalavra = psPalavra + fsValor.charAt(piLoop); 
		            break; 
		          case 'numero': 
		            if (fsValor.charAt(piLoop) >= '0' && fsValor.charAt(piLoop) <= '9'){
		              psPalavra = psPalavra + fsValor.charAt(piLoop); 
		            }
		
		            if (fsValor.charAt(piLoop) == '-' && psPalavra == ''){
		              psPalavra = psPalavra + fsValor.charAt(piLoop); 
		            }
		              
		            break; 
		        } 
	      	}
	      	
	      	for(piLoop=0; piLoop <= (psPalavra.length-1); piLoop++) {
				switch (fsFormato) {
					case 'data': 
						if (piLoop == 2) psResposta = psResposta + '/' + psPalavra.charAt(piLoop); 
						if (piLoop == 4) psResposta = psResposta + '/' + psPalavra.charAt(piLoop); 
						if (piLoop != 2 && piLoop != 4) psResposta = psResposta + psPalavra.charAt(piLoop); 
						piTamanho = 10; 
		        		break; 
					case 'hora': 
						if (piLoop == 2) 
							psResposta = psResposta + ':' + psPalavra.charAt(piLoop) 
						else 
							psResposta = psResposta + psPalavra.charAt(piLoop); 
							piTamanho = 5; 
						break; 
	          		case 'telefone': 
						psResposta = psResposta + psPalavra.charAt(piLoop); 
						piTamanho = 14; 
						break; 
                        
	          		case 'telefone_rural': 
						psResposta = psResposta + psPalavra.charAt(piLoop); 
						piTamanho = 14;
						break; 
	          		case 'moeda': 
						if (psPalavra.length < 14){
							psResposta = psResposta + psPalavra.charAt(piLoop); 
							piTamanho = 14; 
						}else{
							psResposta = psPalavra;
						}
						break; 
					case 'cpf': 
						if (psPalavra.length < 14){
							psResposta = psResposta + psPalavra.charAt(piLoop); 
							piTamanho = 14; 
						}else{
							psResposta = psPalavra;
						}
						break; 
	          		case 'cnpj': 
						psResposta = psResposta + psPalavra.charAt(piLoop); 
						piTamanho = 19; 
						break; 
		          	case 'cnpj_filial':
						psResposta = psResposta + psPalavra.charAt(piLoop); 
						piTamanho = 7; 
						break; 
	          		case 'cep': 
	            		if (piLoop == 5) psResposta = psResposta + '-' + psPalavra.charAt(piLoop); 
	            		if (piLoop != 5) psResposta = psResposta + psPalavra.charAt(piLoop); 
			            piTamanho = 9;
			            break; 
	          		case 'numero': 
			            psResposta = psResposta + psPalavra.charAt(piLoop); 
			            piTamanho = 50;
			            break; 
		        } 
	      	}
	
			//--- Formata Moeda
			if (fsFormato == 'moeda' && psResposta.length > intDecimais){
				for(piLoop=intDecimais; piLoop < (psResposta.length); piLoop++){
					pParte_i = psResposta.substring(0, psResposta.length- piLoop );
					pParte_f = psResposta.substring(psResposta.length-piLoop, psResposta.length);
					if (piLoop == intDecimais){
						psResposta = pParte_i + ',' + pParte_f ;
					}else{
						psResposta = pParte_i + '.' + pParte_f ;
					}
					piLoop = piLoop + 3;
				}
			}
			//--- Fim Formata Moeda
	
			//--- Formata CNPJ
			if (fsFormato == 'cnpj' && psResposta.length > 6){
				//psResposta = psResposta.substring(0, 19);
				pParte_i = psResposta.substring(0, psResposta.length - 2 );
				pParte_f = psResposta.substring(psResposta.length - 2, psResposta.length);
				psResposta = pParte_i + '-' + pParte_f ;
				pParte_i = psResposta.substring(0, psResposta.length - 7 );
				pParte_f = psResposta.substring(psResposta.length - 7, psResposta.length);
				psResposta = pParte_i + '/' + pParte_f ;
				for(piLoop=11; piLoop < (psResposta.length); piLoop++){
					pParte_i = psResposta.substring(0, psResposta.length- piLoop );
					pParte_f = psResposta.substring(psResposta.length-piLoop, psResposta.length);
					psResposta = pParte_i + '.' + pParte_f ;
					piLoop = piLoop + 3;
				}
			}
			//--- Fim Formata CNPJ
			
			//--- Formata CNPJ_FILIAL
			if (fsFormato == 'cnpj_filial' && psResposta.length > 4){
				psResposta = psResposta.substring(0, 7);
				pParte_i = psResposta.substring(0, psResposta.length - 2 );
				pParte_f = psResposta.substring(psResposta.length - 2, psResposta.length);
				psResposta = pParte_i + '-' + pParte_f ;
			}
			//--- Fim Formata CNPJ_FILIAL

			//--- Formata DDD Telefone
			if (fsFormato == 'telefone') {
				if(psResposta.length == 10){ //Linhas com 8 digitos
					psResposta = psResposta.substring(0, 10);
					pParte_g = psResposta.substring(0, psResposta.length - 8 );
					pParte_i = psResposta.substring(2, psResposta.length - 4 );
					pParte_f = psResposta.substring(psResposta.length - 4, psResposta.length);
					psResposta = '(' + pParte_g + ')' + pParte_i + '-' + pParte_f;

				}else if(psResposta.length == 11){ //Linhas com 9 digitos
					psResposta = psResposta.substring(0, 11);
					pParte_g = psResposta.substring(0, psResposta.length - 9 );
					pParte_i = psResposta.substring(2, psResposta.length - 4 );
					pParte_f = psResposta.substring(psResposta.length - 4, psResposta.length);
					psResposta = '(' + pParte_g + ')' + pParte_i + '-' + pParte_f;

				} else {
					if(psResposta.length == 8){
					psResposta = psResposta.substring(0, 9);
					}else{
						psResposta = psResposta.substring(0, 10);
					}
					pParte_i = psResposta.substring(0, psResposta.length - 4 );
					pParte_f = psResposta.substring(psResposta.length - 4, psResposta.length);
					psResposta = pParte_i + '-' + pParte_f ;
				}
			}
			//--- Fim Formata DDD Telefone

			//--- Formata Telefone de 7 ou 8 digitos
			if (fsFormato == 'telefone_rural') {
                if (psResposta.length == 9) {
					pParte_g = psResposta.substring(0, 2);
					pParte_i = psResposta.substring(2, 5);
					pParte_f = psResposta.substring(5, 9);
					x = '(' + pParte_g + ')' + pParte_i + '-' + pParte_f;
				} else if (psResposta.length > 9){
					psResposta = psResposta.substring(0, 10);
					pParte_g = psResposta.substring(0, psResposta.length - 8 );
					pParte_i = psResposta.substring(2, psResposta.length - 4 );
					pParte_f = psResposta.substring(psResposta.length - 4, psResposta.length);
					x = '(' + pParte_g + ')' + pParte_i + '-' + pParte_f;                    
                } else {
					psResposta = psResposta.substring(0, 9);
					pParte_i = psResposta.substring(0, psResposta.length - 4 );
					pParte_f = psResposta.substring(psResposta.length - 4, psResposta.length);
					x = pParte_i + '-' + pParte_f ;
				}
                psResposta = x;
			}
			//--- Fim Formata DDD Telefone

			//--- Formata CPF
			if (fsFormato == 'cpf' && psResposta.length > 2){
				psResposta = psResposta.substring(0, 11);
				pParte_i = psResposta.substring(0, psResposta.length - 2 );
				pParte_f = psResposta.substring(psResposta.length - 2, psResposta.length);
				psResposta = pParte_i + '-' + pParte_f ;
				
				for(piLoop=6; piLoop < (psResposta.length); piLoop++){
					pParte_i = psResposta.substring(0, psResposta.length- piLoop );
					pParte_f = psResposta.substring(psResposta.length-piLoop, psResposta.length);
					psResposta = pParte_i + '.' + pParte_f ;
					piLoop = piLoop + 3;
				}
			}
			//--- Fim Formata CPF
	
			if (fsFormato == 'moeda'){
				document.forms[foForm].elements[foNome].value = psResposta.substring(0, psResposta.length);
			}else{
				document.forms[foForm].elements[foNome].value = psResposta.substring(0, piTamanho);
			}
		} 
	}
}

function dataBrParaUSS( mData ) {
	return mData.substr(6,4) + "/" + mData.substr(3,2) + "/" + mData.substr(0,2);
}

function limpa_cnpj(cnpj){
	var sretorno;
	sretorno = cnpj;
	sretorno = troca(sretorno,".","");
	sretorno = troca(sretorno,"-","");
	sretorno = troca(sretorno,"/","");
	sretorno = troca(sretorno," ","");
	return sretorno;
}

function limpa_cpf(cpf){
	var sretorno;
	sretorno = cpf;
	sretorno = troca(sretorno,".","");
	sretorno = troca(sretorno,"-","");
	sretorno = troca(sretorno,"/","");
	sretorno = troca(sretorno," ","");
	return sretorno;
}

function VerificaCNPJ(valor) {
	valor= limpa_cnpj(valor);
	primeiro=valor.substr(1,1);
	falso=true;
	size=valor.length;
	if (size!=14){
		return false;
	}
	size--;
	for (i=2; i<size-1; ++i){
		proximo=(valor.substr(i,1));
		if (primeiro!=proximo) {
			falso=false
		}
	}
	
	if (falso){
		return;
	}
	
   	if(modulo(valor.substring(0,valor.length - 2)) + "" + modulo(valor.substring(0,valor.length - 1)) !=valor.substring(valor.length - 2,valor.length)) {
   		return false;
   	}
   	return true
}

function VerificaCPF(valor) {
	valor= limpa_cpf(valor);
	
	primeiro=valor.substr(1,1);
	falso=true;
	size=valor.length;
	if (size!=11){
		return false;
	}
	size--;
	for (i=2; i<size-1; ++i){
		proximo=(valor.substr(i,1));
		if (primeiro!=proximo) {
			falso=false
		}
	}
	if (falso){
		return false;
	}
   	if(moduloCPF(valor.substring(0,valor.length - 2)) + "" + moduloCPF(valor.substring(0,valor.length - 1)) != valor.substring(valor.length - 2,valor.length)) {
   		return false;
   	}
   	return true
}

function modulo(str) {
   	soma=0;
   	ind=2;
   	for(pos=str.length-1;pos>-1;pos=pos-1) {
   		soma = soma + (parseInt(str.charAt(pos)) * ind);
   		ind++;
   		if(str.length>11) {
   			if(ind>9) ind=2;
   		}
	}
   	resto = soma - (Math.floor(soma / 11) * 11);
   	if(resto < 2) {
    	return 0
   	}
   	else {
   		return 11 - resto
   	}
}



function troca(stexto,sprocura,stroca){
	for (c=0;c<stexto.length;c++)
	{
		stexto = stexto.replace(sprocura,stroca)
	}
	return stexto;
}

function moduloCPF(str) {
   	soma=0;
   	ind=2;
   	for(pos=str.length-1;pos>-1;pos=pos-1) {
   		soma = soma + (parseInt(str.charAt(pos)) * ind);
   		ind++;
   		if(str.length>11) {
   			if(ind>9) ind=2;
   		}
	}
   	resto = soma - (Math.floor(soma / 11) * 11);
   	if(resto < 2) {
    	return 0
   	}
   	else {
   		return 11 - resto
   	}
}


function VerificaEmail(email) {
	if (!email) return false;
	var  BadChars = "*|,\":<>[]{}`\';()&$#% ";
	var  GoodChars = "@.";   
	for (var i = 0; i < email.length; i++) {
		if (BadChars.indexOf(email.charAt(i)) != -1) {
			return false;
		}
	}
	for (var i = 0; i < GoodChars.length; i++) {
		if (email.indexOf(GoodChars.charAt(i)) == -1) {
			return false;
		}
	}
 	if (email.indexOf ('@.',0) != -1 || email.indexOf ('.@',0) != -1) {
		return false;
	}
   return true;
}

function validate_date(sdata) {
	day2 = sdata.substr(0,2);
	month2 = sdata.substr(3,2);
	year2 = sdata.substr(6,4);
	
	if ((sdata.substr(2,1) != "/") || (sdata.substr(5,1) != "/"))
	{
		day2 = "";
		month2 = "";
		year2 = "";
	}
	
	var DayArray = new Array(31,28,31,30,31,30,31,31,30,31,30,31);
	var MonthArray = new Array("01","02","03","04","05","06","07","08","09","10","11","12");
	var inpDate = day2 + month2 + year2;
	var filter=/^[0-9]{2}[0-9]{2}[0-9]{4}$/;
	
	//Check ddmmyyyy date supplied
	if (! filter.test(inpDate))
	  {
	  return false;
	  }
	/* Check Valid Month */
	filter=/01|02|03|04|05|06|07|08|09|10|11|12/;
	if (! filter.test(month2))
	  {
	  return false;
	  }
	/* Check For Leap Year */
	var N = Number(year2);
	if ( ( N%4==0 && N%100 !=0 ) || ( N%400==0 ) )
	  	{
	   DayArray[1]=29;
	  	}
	  	
	/* Check for valid days for month */
	for(var ctr=0; ctr<=11; ctr++) {
	   if (MonthArray[ctr]==month2) {
	      if (day2<= DayArray[ctr] && day2 >0 ) {
	        inpDate = day2 + '/' + month2 + '/' + year2;
	        return true;
	      } else {
	        return false;
	      }
	   }
	}
}

function validaHora(horaValue){
    if(horaValue == '')
        return false;
    
    var timePat = /^(\d{1,2}):(\d{2})(:(\d{2}))?(\s?(AM|am|PM|pm))?$/;

    var matchArray = horaValue.match(timePat);
    if (matchArray == null) {                    
        alert("Este não é um formato de hora válido.");
        return false;
    }
    hour = matchArray[1];
    minute = matchArray[2];
    
    if (hour < 0  || hour > 23) {                    
        alert("Hora deve estar entre 0 e 23.");
        return false;
    }

    if (minute < 0 || minute > 59) {                    
        alert ("Minutos devem estar entre 0 e 59.");
        return false;
    }

    return true;
}

function Ir_All(p1,p2){
	for(i=0;i< document.frm.length;i++){
		e=document.frm.elements[i];
		if (e.name == p1){
			cmb1 = document.frm.elements[i];
		}
		if (e.name == p2){
			cmb2 = document.frm.elements[i];
		}
	}
	for(i=0 ; i <= cmb1.options.length-1; i++){
		cmb1.options[i].selected=true;
	}
	Ir_1(cmb1,cmb2);
}

function Voltar_All(p1,p2){
	for(i=0;i< document.frm.length;i++){
		e=document.frm.elements[i];
		if (e.name == p1){
			cmb1 = document.frm.elements[i];
		}
		if (e.name == p2){
			cmb2 = document.frm.elements[i];
		}
	}
	for(i=0 ; i <= cmb2.options.length-1; i++){
		cmb2.options[i].selected=true;
	}
	Voltar_1(cmb1,cmb2);
}

function Ir_1(p1,p2){
	for(i=0;i< document.frm.length;i++){
		e=document.frm.elements[i];
		if (e.name == p1){
			cmb1 = document.frm.elements[i];
		}
		if (e.name == p2){
			cmb2 = document.frm.elements[i];
		}
	}
	if (cmb1.selectedIndex == -1){
		return		
	}

	var vParam
	var tParam
	var iTempParam
	var vTempParam
	var tTempParam
	var iParam
	var i
	var iAdd
	
	while (cmb1.selectedIndex != -1){
		iAdd = 0;
		
		if (cmb1.options.length >= 1){
			vParam = cmb1.options[cmb1.selectedIndex].value;
			tParam = cmb1.options[cmb1.selectedIndex].text;
			iParam = cmb1.options[cmb1.selectedIndex].id;
			//Limpa Combo Temporario
			while (document.frm.cmb.options.length != 0) {
				document.frm.cmb.remove(0);	
			}
			//Carrega Combo Temporario
			for (i = 0;  i < cmb2.options.length ;  i++) {
				vTempParam = cmb2.options[i].value;
				tTempParam = cmb2.options[i].text;
				iTempParam = cmb2.options[i].id;
				document.frm.cmb.options[document.frm.cmb.options.length]=new Option(tTempParam,vTempParam);
				document.frm.cmb.options[document.frm.cmb.options.length - 1].id = iTempParam;
			}
			//Limpa Combo
			while (cmb2.options.length != 0) {
				cmb2.remove(0);	
			}
			//Carrega Combo 
			if (document.frm.cmb.options.length == 0){
				vTempParam = vParam;
				tTempParam = vParam;
				iTempParam = vParam;
				cmb2.options[cmb2.options.length]=new Option(tParam,vParam);
				cmb2.options[cmb2.options.length-1].id = iParam;
				cmb1.options[cmb1.selectedIndex] = null;
				iAdd = 1;
			}else{
				if ( (iParam == 1) || (iParam < document.frm.cmb.options[0].id)) {
					vTempParam = vParam;
					tTempParam = vParam;
					iTempParam = vParam;
					cmb2.options[cmb2.options.length]=new Option(tParam,vParam);
					cmb2.options[cmb2.options.length-1].id = iParam;
					cmb1.options[cmb1.selectedIndex] = null;
					iAdd = 1;
				}
			}
			vTempParam = 0;
			tTempParam = 0;
			iTempParam = 0;

			for (i = 0;  i < document.frm.cmb.options.length ;  i++) {
				vTempParam = document.frm.cmb.options[i].value;
				tTempParam = document.frm.cmb.options[i].text;
				iTempParam = document.frm.cmb.options[i].id;
				if ((iParam < iTempParam) && (iAdd == 0)){
					cmb2.options[cmb2.options.length]= new Option(tParam,vParam);
					cmb2.options[cmb2.options.length - 1].id = iParam;
					cmb1.options[cmb1.selectedIndex] = null;
					iAdd = 1;
				}


				cmb2.options[cmb2.options.length]=new Option(tTempParam,vTempParam);
				cmb2.options[cmb2.options.length - 1].id = iTempParam;
				if ((iParam == eval(iTempParam) + 1) && (iAdd == 0)) {
					vTempParam = vParam;
					tTempParam = vParam;
					iTempParam = vParam;
					cmb2.options[cmb2.options.length]=new Option(tParam,vParam);
					cmb2.options[cmb2.options.length - 1].id = iParam;
					cmb1.options[cmb1.selectedIndex] = null;
					iAdd = 1;
				}
			}
			if (iAdd == 0){
				cmb2.options[cmb2.options.length]=new Option(tParam,vParam);
				cmb2.options[cmb2.options.length - 1].id = iParam;
				cmb1.options[cmb1.selectedIndex] = null;
				iAdd = 1;
			}
		}
	}
}

function Voltar_1(p1,p2){

	for(i=0;i< document.frm.length;i++)
	{
		e=document.frm.elements[i];
		if (e.name == p1){
			cmb1 = document.frm.elements[i];
		}
		if (e.name == p2){
			cmb2 = document.frm.elements[i];
		}
	}

	if (cmb2.selectedIndex == -1){
		return		
	}
	var vParam
	var tParam
	var iTempParam
	var vTempParam
	var tTempParam
	var iParam
	var i
	var iAdd
	
	while (cmb2.selectedIndex != -1){
		iAdd = 0;
		if (cmb2.options.length >= 1){
			vParam = cmb2.options[cmb2.selectedIndex].value;
			tParam = cmb2.options[cmb2.selectedIndex].text;
			iParam = cmb2.options[cmb2.selectedIndex].id;
			//Limpa Combo Temporario
			while (document.frm.cmb.options.length != 0) {
				document.frm.cmb.remove(0);	
			}
			//Carrega Combo Temporario
			for (i = 0;  i < cmb1.options.length ;  i++) {
				vTempParam = cmb1.options[i].value;
				tTempParam = cmb1.options[i].text;
				iTempParam = cmb1.options[i].id;
				document.frm.cmb.options[document.frm.cmb.options.length]=new Option(tTempParam,vTempParam);
				document.frm.cmb.options[document.frm.cmb.options.length - 1].id = iTempParam;
			}
			//Limpa Combo
			while (cmb1.options.length != 0) {
				cmb1.remove(0);	
			}
			//Carrega Combo 
			if (document.frm.cmb.options.length == 0){
				vTempParam = vParam;
				tTempParam = vParam;
				iTempParam = vParam;
				cmb1.options[cmb1.options.length]=new Option(tParam,vParam);
				cmb1.options[cmb1.options.length-1].id = iParam;
				cmb2.options[cmb2.selectedIndex] = null;
				iAdd = 1;
			}else{
				if ( (iParam == 1) || (iParam < document.frm.cmb.options[0].id)) {
					vTempParam = vParam;
					tTempParam = vParam;
					iTempParam = vParam;
					cmb1.options[cmb1.options.length]=new Option(tParam,vParam);
					cmb1.options[cmb1.options.length-1].id = iParam;
					cmb2.options[cmb2.selectedIndex] = null;
					iAdd = 1;
				}
			}
			vTempParam = 0;
			tTempParam = 0;
			iTempParam = 0;

			for (i = 0;  i < document.frm.cmb.options.length ;  i++) {
				vTempParam = document.frm.cmb.options[i].value;
				tTempParam = document.frm.cmb.options[i].text;
				iTempParam = document.frm.cmb.options[i].id;
				if ((iParam < iTempParam) && (iAdd == 0)){
					cmb1.options[cmb1.options.length]= new Option(tParam,vParam);
					cmb1.options[cmb1.options.length - 1].id = iParam;
					cmb2.options[cmb2.selectedIndex] = null;
					iAdd = 1;
				}

				cmb1.options[cmb1.options.length]=new Option(tTempParam,vTempParam);
				cmb1.options[cmb1.options.length - 1].id = iTempParam;
				if ((iParam == eval(iTempParam) + 1) && (iAdd == 0)) {
					vTempParam = vParam;
					tTempParam = vParam;
					iTempParam = vParam;
					cmb1.options[cmb1.options.length]=new Option(tParam,vParam);
					cmb1.options[cmb1.options.length - 1].id = iParam;
					cmb2.options[cmb2.selectedIndex] = null;
					iAdd = 1;
				}
			}
			if (iAdd == 0){
				cmb1.options[cmb1.options.length]=new Option(tParam,vParam);
				cmb1.options[cmb1.options.length - 1].id = iParam;
				cmb2.options[cmb2.selectedIndex] = null;
				iAdd = 1;
			}
		}
	}
}

//FIM