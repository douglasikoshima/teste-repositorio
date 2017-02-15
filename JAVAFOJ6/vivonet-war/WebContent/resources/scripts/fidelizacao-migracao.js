function cancela(acao, obj){    
    //alert(document.forms[1].name);
    
    document.forms[1].elements["tipoEncerramento"].value="5";
    document.forms[1].action="filtroFimRetencao.do?acao="+acao;
	parent.parent.mostrar_div();
	document.forms[1].target = "";
    document.forms[1].submit();
}

function liberaData(obj)
{
		
		if((document.forms[1].inExcecao) && document.forms[1].inExcecao.checked)
		{
			document.getElementById("calendar2").style.visibility = "visible";
			document.forms[1].disValor.disabled = false;
		}
		else
		{
			document.getElementById("calendar2").style.visibility = "hidden";
			document.forms[1].disValor.disabled = true;
		}	
}

function buscaValidade(iBonus)
{
	if (iBonus > 0)
	{
		var validade = document.forms[1].idValidadeSelecionada[iBonus].text;
		document.forms[1].validade.value = validade;
		var valorCredito = document.forms[1].idValorSelecionado[iBonus].text;
		document.forms[1].disValor.value = valorCredito;
		Formatar(valorCredito, document.forms[1].name, 'disValor', 'moeda');
	}else{document.forms[1].validade.value = "";}	
		
}




function Formatar(fsValor, foForm, foNome, fsFormato) { 

	var piLoop; 
	var psPalavra	= '';
	var psResposta	= ''; 
	var piTamanho	= 0; 
	var pParte_i	= '';
	var pParte_f	= '';
	var intDecimais = 2;
	
	intDecimais 	= 2;


	for(piLoop = 0; piLoop <= (fsValor.length-1); piLoop++) {
		switch (fsFormato) {
		  case 'telefone': 
			if (fsValor.charAt(piLoop) >= '0' && fsValor.charAt(piLoop) <= '9')
			  psPalavra = psPalavra + fsValor.charAt(piLoop); 
			break; 
		  case 'moeda': 
			if (fsValor.charAt(piLoop) != ',' && fsValor.charAt(piLoop) >= '0' && fsValor.charAt(piLoop) <= '9')
			  psPalavra = psPalavra + fsValor.charAt(piLoop); 
			  break; 
		} 
	}
	      	
	for(piLoop=0; piLoop <= (psPalavra.length-1); piLoop++) {
		switch (fsFormato) {
			case 'telefone': 
				psResposta = psResposta + psPalavra.charAt(piLoop); 
				piTamanho = 13; 
				break; 
			case 'moeda': 
				if (psPalavra.length < 14){
					psResposta = psResposta + psPalavra.charAt(piLoop); 
					piTamanho = 14; 
				}else{
					psResposta = psPalavra;
				}
				break; 
		} 
	}
	
	//--- Formata Moeda
	if (fsFormato == 'moeda' && psResposta.length > intDecimais){
		for(piLoop=intDecimais; piLoop < (psResposta.length); piLoop++){
			pParte_i = psResposta.substring(0, psResposta.length- piLoop );
			pParte_f = psResposta.substring(psResposta.length-piLoop, psResposta.length);
			if (piLoop == intDecimais)
			{
				psResposta = pParte_i + ',' + pParte_f ;
			}
			else
			{
				psResposta = pParte_i + '.' + pParte_f ;
			}
			piLoop = piLoop + 3;
		}
	}
	//--- Fim Formata Moeda
	
	//--- Formata DDD Telefone
	if (fsFormato == 'telefone') {
		if(psResposta.length > 9){
			psResposta = psResposta.substring(0, 10);
			pParte_g = psResposta.substring(0, psResposta.length - 8 );
			pParte_i = psResposta.substring(2, psResposta.length - 4 );
			pParte_f = psResposta.substring(psResposta.length - 4, psResposta.length);
			psResposta = '(' + pParte_g + ')' + pParte_i + '-' + pParte_f;

		} else {
			psResposta = psResposta.substring(0, 9);
			pParte_i = psResposta.substring(0, psResposta.length - 4 );
			pParte_f = psResposta.substring(psResposta.length - 4, psResposta.length);
			psResposta = pParte_i + '-' + pParte_f ;
		}
	}
	//--- Fim Formata DDD Telefone

	if (fsFormato == 'moeda'){
		document.forms[foForm].elements[foNome].value = psResposta.substring(0, psResposta.length);
	}else{
		document.forms[foForm].elements[foNome].value = psResposta.substring(0, piTamanho);
	}

	
}

function carregarData()
{
		buscaValidade(document.forms[1].migracaoSelecionada.selectedIndex);
        var dia  = new Date();
        var mes  = dia.getMonth() + 1;
        var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

		if (document.forms[1].validade.value == "")
			{
				alert("Favor selecionar um tipo de Migração");
			}else if(document.forms[1].dtInicio.value == "")
			{
				alert("Favor preencher data inicial");
			}else if(!validaDataFinal(hoje, document.forms[1].elements["dtInicio"].value)){         
                alert("Data início vigência menor que a data atual, favor corrigir!");
                return false;
            }
			else	
			{	
			
				data = document.forms[1].dtInicio.value.split("/");
				data[0] == "08"?data[0] = "8":0;data[1] == "09"?data[1] = "9":0;data[1] == "08"?data[1] = "8":0;data[1] == "09"?data[1] = "9":0;
				data[0] = parseInt(data[0]);data[1] = parseInt(data[1]);data[2] = parseInt(data[2]);
				for (i = 0; i < parseInt(document.forms[1].validade.value); i++){
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
				document.forms[1].dtFim.value = data[0] + "/" + data[1] + "/" + data[2];
			}
		
}
   
    
function reter(obj){
	var dia  = new Date();
    var mes  = dia.getMonth() + 1;
    var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

    if(document.forms[1].elements['migracaoSelecionada'].value=="0"){
        alert("Favor selecionar um tipo de Migração.");
    }else if(document.forms[1].elements["nrNovoPre"].value == ""){
        alert("Favor preencher o campo Novo Número Pré Pago!");
    }else if(document.forms[1].elements["nrNovoPre"].value.length < 13){
        alert("Novo Número Pré Pago inválido, favor corrigir!");
    }else if(document.forms[1].elements["disValor"].value == ""){
        alert("Favor preencher o campo Saldo Pré Pago!");
    }else if(document.forms[1].elements["dtFim"].value == ""){
        alert("Favor selecionar uma Validade do Crédito!");
    }else if(!validaDataFinal(hoje, document.forms[1].elements["dtFim"].value)){
		alert("Validade menor que a data atual, favor corrigir!");  
	}else{
       if((document.forms[1].elements["inExcecao"]) && !document.forms[1].elements["inExcecao"].checked){carregarData();}        
       document.forms[1].valor.value = document.forms[1].disValor.value;
       document.forms[1].action="reterMigracao.do?acao=ok";
	   parent.parent.mostrar_div();
	   document.forms[1].target = "";
	   document.forms[1].submit();
	
    }
}


function agendar()
{
	parent.dvNrProcesso.style.display = '';
	document.forms[1].target = "ifrmNrProcesso";
	document.forms[1].action = "getDadosAgendarContato.do";
	parent.parent.mostrar_div();
	document.forms[1].submit();
	
}
