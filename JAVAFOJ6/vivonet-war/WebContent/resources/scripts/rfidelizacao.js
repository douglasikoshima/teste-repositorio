zsa="top.frameApp.mostrar_div();"
zso="top.frameApp.oculta_div();"
zsaPP="parent.parent.mostrar_div();"
zsoPP="parent.parent.oculta_div();"
corBack="document.body.style.backgroundColor = '#ededed';"

function cancela(acao, obj){
 document.forms[0].action="filtroFimRetencao.do?acao="+acao;
 eval(zsaPP);
 document.forms[0].target ="";
 document.forms[0].submit();
}

//Usado em realizarRetencao.jsp
   function abre(){
		valor = null;
        if(document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"].length){
			for(i=0; i < document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"].length;i++){
	            if(document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"][i].checked){
					valor = document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"][i].value;
				}
			}
        }else{
			if(document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"].checked){
				valor = document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"].value;
			}
		}
		if (valor != null){
            abreGeral(valor);
		}else{
			alert("Favor selecionar uma Linha!");
		}
    }

    
    function abreGeral(parametro)
	{
			
				dvNrProcesso.style.display = '';
				document.forms["frmHistorico"].target = "ifrmNrProcesso";
				document.forms["frmHistorico"].action = "ActionObtemHistorico.do?idLinha=" + parametro;
				eval(zsaPP);
				document.forms["frmHistorico"].submit();
	}
    
       

function pesquisarRealizarRet(obj){
  if(document.actionRetencaoForm.elements["{actionForm.linhaPesquisa}"].value.length < 13 && 
(document.actionRetencaoForm.elements["{actionForm.linhaPesquisa}"].value != "")){
    alert('Número da Linha inválido, favor Corrigir!')
  }else{
    document.forms[0].action = obj.href;
    eval(zsaPP);
	document.forms[0].submit();   
  }
}

 function cancelaRealizarRet(acao, obj){    
 		valida = false;
        if(document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"].length){
			for(i=0; i < document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"].length;i++){
	            if(document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"][i].checked){
					valida = true;
				}
			}
        }else{
			if(document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"].checked){
                valida = true;
			}
		}
		if (valida == true){
               document.forms[0].action="/FrontOfficeWeb/fidelizacao/BuscaDetalheLinha.do";
               eval(zsaPP);
               document.forms[0].submit();
		}else{
			alert("Favor selecionar uma Linha!");
		}
 }
                      
function proximaRealizarRet(obj){

		valida = false;
        var strLineaSeleccionada=null;
        var strIdLineaSeleccionada=null;
        if(document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"].length){
			
            for(i=0; i < document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"].length;i++){
	            if(document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"][i].checked){
					
					valida = true;
                    break;
				}
			}
        }else{
        
			if(document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"].checked)
			{
			   valida = true;
			}
		}
        
		if (valida)
		{
				
                var strName = "document.forms[0].elements['"+getNetuiTagName("strLinhaSel")+"']";
                eval(strName).value=strLineaSeleccionada;
                strName = "document.forms[0].elements['"+getNetuiTagName("linhaSelecionada")+"']";
                eval(strName).value = strIdLineaSeleccionada;
                
				
				
                document.forms[0].action = obj.href;                               
                eval(zsaPP);
				document.forms[0].submit();               
                
                
                return true;
		}else{
			alert("Favor selecionar uma Linha!");
            return false;
		}
}  

function finalizaRealizarRet()
{
		 document.forms[0].target = "frmRetencao";
		 document.forms[0].action="sair.do";
		 document.forms[0].submit();
}
  

//Usado em dadosCliente.jsp
 function agendar()
 {
  dvNrProcesso.style.display = '';
  document.forms["frmAgenda"].target = "ifrmNrProcesso";
  document.forms["frmAgenda"].action = "AgendaContato.do?acao=agendarContato";
  parent.mostrar_div();
  document.forms["frmAgenda"].submit();
 }

 function agendarMatriz()
  {
  dvNrProcesso.style.display = '';
  document.forms["frmAgenda"].target = "ifrmNrProcesso";
  document.forms["frmAgenda"].action = "ConsultaMatrizOferta/addForm.do?acao=agendar";
  parent.mostrar_div();
  document.forms["frmAgenda"].submit();
  }

//Usado em AgendaContato.jsp		
	dia = new Date();
	mes = dia.getMonth() + 1;
	hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();
	var hojeMinutos = dia.getHours().toString() + ":" + dia.getMinutes().toString()

	function salvarAgenda(obj){
		if(document.forms[0].elements["{actionForm.nomeContato}"].value == ""){
			alert("Favor preencher o campo Contato!");
		}else if(document.forms[0].elements["{actionForm.telefoneContato}"].value == ""){
			alert("Favor preencher o campo Telefone de Contato!");
		}else if(document.forms[0].elements["{actionForm.data}"].value == ""){
			alert("Favor selecionar uma Data!");
		}else if(!validaDataFinalEx(hoje,document.forms[0].elements["{actionForm.data}"].value, hojeMinutos, document.forms[0].elements["wlw-select_key:{actionForm.horas}"].value + ":" + document.forms[0].elements["wlw-select_key:{actionForm.minutos}"].value)){                
			alert('Data / hora menor que a atual, favor corrigir!');
		}else{
			valor = obj.href.split("?");
			valor[1]?action = document.forms[0].action + "?" + valor[1]:action = document.forms[0].action;
			document.forms[0].action = action;
			document.forms[0].target="frmQuestionario";
			eval(zsaPP);
			document.forms[0].submit();
			parent.voltar();
		}
	}
    
//Usado em destinoPrevisto.jsp
            function cancelaDestino(acao, obj){    
                document.forms[0].action="/FrontOfficeWeb/fidelizacao/ConsultaMatrizOferta/filtroFimRetencao.do?matrizOferta=nao&acao="+acao;
                eval(zsaPP);
				document.forms[0].submit();
            }
            
            function proximaDestino(obj){
            valida = false;                            

                if (document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}']){
                    if(document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'].length)
                    {
                        for(i=0; i < document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'].length; i++){
                            if(document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'][i].checked == true){
                                valida = true;
                            }
                        }
                    }
                    else
                    {
                        valida = (document.showMatrizOfertaForm.elements['wlw-radio_button_group_key:{actionForm.idDestinoPrevisto}'].checked == true);                        
                    }
                }
                if (valida == false){
                    alert("Favor selecionar uma resposta!");
                }else{
					eval(zsaPP);
                    document.showMatrizOfertaForm.action = obj.href;
                    document.showMatrizOfertaForm.submit();
                }
            }

//Usado em intencaoCanelamento.jsp
            function cancelaIntencao(acao, obj){    
                document.forms[0].action="/FrontOfficeWeb/fidelizacao/ConsultaMatrizOferta/filtroFimRetencao.do?matrizOferta=nao&acao="+acao+"&mensagem=sim";
                eval(zsaPP);
				document.forms[0].submit();
            }
            
            function proximaIntencao(obj){
            valida = false;
                if (document.pesquisaDestinoPrevistoForm.elements['wlw-radio_button_group_key:{actionForm.intencaoSelecionada}']){
                    for(i=0; i < document.pesquisaDestinoPrevistoForm.elements['wlw-radio_button_group_key:{actionForm.intencaoSelecionada}'].length; i++){
                        if(document.pesquisaDestinoPrevistoForm.elements['wlw-radio_button_group_key:{actionForm.intencaoSelecionada}'][i].checked == true){
                            valida = true;
                            break;
                        }
                    }
                }
                if (valida == false){
                    alert("Favor selecionar uma resposta!");
                }else{
					eval(zsaPP);
                    document.pesquisaDestinoPrevistoForm.action = obj.href;
                    document.pesquisaDestinoPrevistoForm.submit();
                }
            }

//Uso em ConsultaMatrizOferta\consultaMatrizOferta.jsp
function move(objOrigem, objDestino){
        for (i=0; i<objOrigem.options.length; i++){
          if (objOrigem.options[i].selected){
              objDestino.options[objDestino.options.length] = new Option(objOrigem.options[i].text,objOrigem.options[i].value);
              objOrigem.options[i] = null;
              i--;
          }
        }   
    }

function moveUm(objOrigem, objDestino){
        for (i=0; i<objOrigem.options.length; i++){
          if (objOrigem.options[i].selected && objDestino.options.length < 1){
              objDestino.options[0] = new Option(objOrigem.options[i].text,objOrigem.options[i].value);
              objOrigem.options[i] = null;
              i--;
          }
        }
    }


function proximaMatrizOf(obj){
    for(i=0;i < document.forms[0].ofertasReal.options.length; i++){
        document.forms[0].ofertasReal.options[i].selected = "true"
    }
    for(i=0;i < document.forms[0].ofertasAceita.options.length; i++){
        document.forms[0].ofertasAceita.options[i].selected = "true"
    }
 				
    if(document.forms[0].ofertasAceita.options.length == "1"){ 
        eval(zsaPP);
		document.forms[0].target = "";
	    document.forms[0].submit();
    }else{
        alert("Selecione uma oferta!");
    }            
}

function cancelaMatrizOf(acao, obj){
   // priscilla paula - 08/12/2004
    if(document.forms[0].ofertasAceita.options.length == "1"){ 
        msg = acao == 'cancelar' ? 
                            "Não é possível cancelar linha " :
              acao == 'reter'    ?
                            "Não é possível \"reter por argumentação\" " :
                            "A opção \"Vai Pensar\" não está disponível ";
        
        alert(msg + "se houver oferta aceita.\nSe deseja efetuar essa ação, remova a oferta aceita da lista.");
        return false;
    }
    // fim priscilla paula
            
    for(i=0;i < document.forms[0].ofertasReal.options.length; i++){
        document.forms[0].ofertasReal.options[i].selected = "true"
    }
    for(i=0;i < document.forms[0].ofertasAceita.options.length; i++){
        document.forms[0].ofertasAceita.options[i].selected = "true"
    }
    
    document.forms[0].action="filtroFimRetencao.do?acao="+acao;
    
    if(document.forms[0].ofertasReal.options.length == 0 && document.forms[0].ofertasAceita.options.length == 0){
	   document.forms[0].action="filtroFimRetencao.do?acao="+acao;
    }
	
	eval(zsaPP);
	document.forms[0].target = "";
	
	if(acao == 'agendar')
	{
		parent.dvNrProcesso.style.display = '';
		document.forms[0].target = "ifrmNrProcesso";        
	}	
	
	document.forms[0].submit();
	
}

//Uso em ConsultaMatrizOferta\Bonus.jsp
    function carregarValidade()
	{				
				document.forms[0].action = "CarregarFimVigencia.do";                  
				eval(zsaPP);
				document.consultaMatrizOfertaForm.target = "";
                document.forms[0].submit();    
	}
    
	function buscaValidade(iBonus)
	{
		
		if (iBonus > 0)
		{
			var validade = document.forms[0].validadeSelecionada[iBonus].text;
			document.forms[0].validade.value = validade;
		}else{document.forms[0].validade.value = "";}	
		
	}
	
	function liberaData(obj)
	{
		
		//if(document[getNetuiTagName("bonusForm",this)][getNetuiTagName("inExcecao",this)].checked)
        document.forms[0].dtFim.value = "";
        if(document.forms[0].elements["inExcecao"].checked)
		{
			document.getElementById("calendar2").style.visibility = "visible";
		}
		else
		{
			document.getElementById("calendar2").style.visibility = "hidden";
		}	
	}

    function carregarData()
	{
			buscaValidade(document.forms[0].bonusSelecionado.selectedIndex);
			if (document.forms[0].validade.value == "")
			{
				alert("Favor selecionar um dos Bônus Cadastrados!");
			}else if(document.forms[0].dtInicio.value == "")
			{
				alert("Favor preencher data inicial.");
			}else if(!verificarDataVigencia()){
                return false;
            }else	
			{	
			
				data = document.forms[0].dtInicio.value.split("/");
				data[0] == "08"?data[0] = "8":0;data[1] == "09"?data[1] = "9":0;data[1] == "08"?data[1] = "8":0;data[1] == "09"?data[1] = "9":0;
				data[0] = parseInt(data[0]);data[1] = parseInt(data[1]);data[2] = parseInt(data[2]);
				for (i = 0; i < parseInt(document.forms[0].validade.value); i++){
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
				document.forms[0].dtFim.value = data[0] + "/" + data[1] + "/" + data[2];
			}	
	}
     
function reter(obj)
{
	var dia  = new Date();
	var mes  = dia.getMonth() + 1;
    var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

    if(document.consultaMatrizOfertaForm.bonusSelecionado.value == ""){                    
        alert("Favor selecionar um dos Bônus Cadastrados!");
    }else if(document.forms[0].elements["dtInicio"].value == ""){
        alert("Favor selecionar o Início de Vigência!");
    }else if(!validaDataFinal(hoje, document.forms[0].elements["dtInicio"].value)){         
		alert("Data início vigência menor que a data atual, favor corrigir!");                                                                                                                       
	}else if(document.forms[0].elements["dtFim"].value == ""){
        alert("Favor clicar em Calcular!");
    }else if(!validaDataFinal(document.forms[0].elements["dtInicio"].value, document.forms[0].elements["dtFim"].value)){
         alert("Data fim da vigencia menor que a data início, favor corrigir!");     
	}else{
    
        if(!document.forms[0].elements["inExcecao"].checked){carregarData();};
        
        document.consultaMatrizOfertaForm.target = "";
        document.consultaMatrizOfertaForm.action="CadastrarBonus.do?acao=ok";
		eval(zsaPP);
        document.consultaMatrizOfertaForm.submit();
        
    }
}
//Uso em ConsultaMatrizOferta\mensagemEncerramento.jsp
function finalizaMsgEnce()
	{
		 document.forms[0].target = "frmRetencao";
		 document.forms[0].action="MsgEncerramento.do?acao=adicionarObs";
		 eval(zsaPP);
		 document.forms[0].submit();
	}
    

//Uso em ConsultaMatrizOferta\MigracaoPrePago.jsp
function cancelaMigraPre(acao, obj){    
    document.forms[0].action="filtroFimRetencao.do?acao="+acao;
	eval(zsaPP);
	document.forms[1].target = "";
    document.forms[0].submit();
}

function liberaDataMigraPre(obj)
{
		
		if(document.forms[1].inExcecao.checked)
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


function buscaValidadeMigraPre(iBonus)
{
	if (iBonus >=0)
	{
		var validade = document.forms[1].idValidadeSelecionada[iBonus].text;
		document.forms[1].validade.value = validade;
		var valorCredito = document.forms[1].idValorSelecionado[iBonus].text;
		document.forms[1].disValor.value = valorCredito;
		Formatar(valorCredito, document.forms[1].name, 'disValor', 'moeda');
	}	
		
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

function carregarDataMigraPre()
{
		buscaValidadeMigraPre(document.forms[1].migracaoSelecionada.selectedIndex);
		if (document.forms[1].validade.value == "")
			{
				alert("Favor escolher um plano para migração");
			}else if(document.forms[1].dtInicio.value == "")
			{
				alert("Favor preencher data inicial");
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
   
    
function reterMigraPre(obj){
	var dia  = new Date();
    var mes  = dia.getMonth() + 1;
    var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

		
    if(document.forms[1].elements["nrNovoPre"].value == ""){
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
       
       if(!document.forms[1].elements["inExcecao"].checked){
          carregarDataMigraPre();
       ;}       
        
       document.forms[1].valor.value = document.forms[1].disValor.value;
       document.forms[1].action="MigracaoPrePago.do?acao=ok";
	   eval(zsaPP);
	   document.forms[1].target = "";
	   document.forms[1].submit();
	
    }
}

//Uso em ConsultaMatrizOferta\Pontos.jsp
inteiro = new RegExp("[0-9]");
function checaInteiro(obj){
  valor = obj.value;
  for(i=0;i<valor.length;i++){
    if(!inteiro.test(valor.charAt(i))){
      valor = valor.substring(0,i) + valor.substring(i+1,valor.length)
      i = 0;
    }
  }
  obj.value = valor;
}
   
     function abreSimuladorPontos()
	{
			
				//dvVaiPensar.style.display = '';
				document.forms["frmSelecao"].target = "ifrmSimuladorPontos";
				document.forms["frmSelecao"].action = "http://sac.celular.telerj.net.br/pontos/login";
				document.forms["frmSelecao"].submit();               
		
	}
    
function reterPontos(obj){
    if(document.consultaMatrizOfertaForm.elements["{actionForm.quantidade}"].value == ""){
        alert("Favor preencher o campo Quantidade!");
    }else{
        document.consultaMatrizOfertaForm.action="CadastrarPontos.do?acao=ok";
		eval(zsaPP);
		document.consultaMatrizOfertaForm.target = "";
        document.consultaMatrizOfertaForm.submit();
    }
}

//Uso em ConsultaMatrizOferta\SuspensaoTemp.jsp
function carregarDataSuspTemp(){
	
	var dia  = new Date();
    var mes  = dia.getMonth() + 1;
    var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();
	 
	if(document.forms[0].elements["data"].value == "")
	{
		alert("Favor preencher data inicial");
	}
	else if(validaDataFinal(hoje, document.forms[0].elements["data"].value)){
		document.forms[0].dtInicio.value = document.forms[0].data.value
		somaDiasData(document.forms[0].dtInicio,document.forms[0].dtFim,90)        
    }
	else
	{ 
		 alert("Data menor que a data atual, favor corrigir!");  	
	}	
}

function checaExcecaoSuspTemp(obj){
	if(obj.checked){
		document.forms[0].elements["dtFim"].readOnly = false;
	}else{
		document.forms[0].elements["dtFim"].readOnly = true;
		carregarDataSuspTemp();
	}
}
function reterSuspTemp()
{
	var dia  = new Date();
    var mes  = dia.getMonth() + 1;
    var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

				
   
	if(document.forms[0].elements["data"].value == ""){
		alert("Favor selecionar uma Data!");
	}else if(!validaDataFinal(hoje, document.forms[0].elements["data"].value)){         
        alert("Data menor que a data atual, favor corrigir!");                                                                                                                       
	}else if(document.forms[0].elements["dtInicio"].value == ""){
		alert("Favor clicar em Calcular!");
	}else if(document.forms[0].elements["dtFim"].value == ""){
		alert("Favor preencher a Data de Término!");
	}else if(!validaData(document.forms[0].elements["dtFim"].value)){
		alert("Data inválida, favor corrigir!");
	}else if(!validaDataFinal(document.forms[0].elements["dtInicio"].value,document.forms[0].elements["dtFim"].value)){
		alert("Data de Término menor que Data de Inicio, favor corrigir!");
	}else{
		document.forms[0].action = "SuspensaoTemporaria.do?acao=ok";                  
		eval(zsaPP);
		document.forms[0].target = "";
		document.forms[0].submit();              
	}
}

//Uso em ConsultaMatrizOferta\ofertaAparelho.jsp
function mostraMeioPagamento(){        
        
        if (document.meioPagamento.meioPagamento.options[document.meioPagamento.meioPagamento.selectedIndex].value == 1) {            
            document.all["divCartaoCredito"].style.visibility = "visible";
            document.all["divDebitoConta"].style.visibility = "hidden";
        }else{
            
            if (document.meioPagamento.meioPagamento.options[document.meioPagamento.meioPagamento.selectedIndex].value == 2) {
                document.all["divCartaoCredito"].style.visibility = "hidden";
                document.all["divDebitoConta"].style.visibility = "visible";
        }else{
                document.all["divCartaoCredito"].style.visibility = "hidden";
                document.all["divDebitoConta"].style.visibility = "hidden";
            }
        }
                
    }
    function abreManualAparelho()
	{
				document.forms["frmManual"].target = "ifrmManualAparelho";
				document.forms["frmManual"].action = "http://www.vivo.com.br";
				document.forms["frmManual"].submit();   
	}
    
    function mostraTodosAparelhos()
	{                      
        if(document.consultaMatrizOfertaForm.elements["wlw-checkbox_key:{actionForm.inExcecao}"].checked){
                document.forms[0].action = "showTodosAparelhos.do?acao=0";                
        }else{
                document.forms[0].action = "showTodosAparelhos.do";                
        }
                eval(zsaPP);
				document.forms[0].target = "";
				document.forms[0].submit();   
            
	}
    
    function carregarPercDesconto(obj)
    {    
        for (i=0; i < document.all.length; i++){
            if (obj == document.all[i]){
                objId = document.all[i].value;
                i = i + 4;
                objIndex = document.all[i].value;
            }
        }
        if(document.consultaMatrizOfertaForm.elements["wlw-checkbox_key:{actionForm.inExcecao}"].checked){
            objAcao = "0"
        }else{
            objAcao = null
        }
        if(objAcao != null){
        document.forms[0].action = "showPercDescontoParcelas.do?index="+objId+"&acao="+objAcao+"&id="+objIndex;
        }else{
        document.forms[0].action = "showPercDescontoParcelas.do?index="+objId+"&id="+objIndex;
        }
		eval(zsaPP);
		document.forms[0].target = "";
        document.forms[0].submit();   
    }
    
    function abreUrl(obj){
		
        for (i=0; i < document.all.length; i++){
            if (obj == document.all[i]){
                i++;
                url = document.all[i].value;
            }
        }
		if ((url != null)&&(url != ""))
		{
			if (url.indexOf("http://") < 0)
			{
				url = "http://" + url;
			}
	
			window.open(url,"janela");
		}	
    
    }
valorInicio = null;
function calculaPerc(obj){
    destino = document.consultaMatrizOfertaForm.elements['{actionForm.vlDesconto}'];
	
    if(valorInicio == null){
        valorInicio = destino.value.replace(",",".")
        valorInicio = parseInt(valorInicio*100)
    }
    if(obj.value != "netui_null"){
        perc = parseInt(obj.options[obj.selectedIndex].text);
        calculo = parseInt((valorInicio*perc)/100);
        calculo = valorInicio-calculo;
        calculo = calculo.toString();
        calculo = calculo.substring(0,calculo.length - 2) + "," + calculo.substring(calculo.length - 2,calculo.length);
        destino.value = calculo;
        calculaParc();
    }else{
        valorInicio = valorInicio.toString();
        destino.value = valorInicio.substring(0,valorInicio.length - 2) + "," + valorInicio.substring(valorInicio.length - 2,valorInicio.length);
		for(i=0;i<document.consultaMatrizOfertaForm.elements['parcelaSelecionada'].options.length;i++){
			if(document.consultaMatrizOfertaForm.elements['parcelaSelecionada'].options[i].value ==""){
				document.consultaMatrizOfertaForm.elements['parcelaSelecionada'].options[i].selected = true;
            }
        }
        document.consultaMatrizOfertaForm.elements['{actionForm.vlParcela}'].value = "";
        valorInicio = null;
    }
}
function calculaParc(){
    origem = document.consultaMatrizOfertaForm.elements['{actionForm.vlDesconto}'];
    destino = document.consultaMatrizOfertaForm.elements['{actionForm.vlParcela}'];
	if(document.consultaMatrizOfertaForm.elements['parcelaSelecionada'].options[document.consultaMatrizOfertaForm.elements['parcelaSelecionada'].selectedIndex].value !=""){
		numParcelas = parseInt(document.consultaMatrizOfertaForm.elements['parcelaSelecionada'].options[document.consultaMatrizOfertaForm.elements['parcelaSelecionada'].selectedIndex].text);
        valor = origem.value.replace(",",".");
        valor = parseInt(valor*100);
        calculo = parseInt(valor/numParcelas);
        calculo = calculo.toString();
        calculo = calculo.substring(0,calculo.length - 2) + "," + calculo.substring(calculo.length - 2,calculo.length);
        destino.value = calculo;
    }else{
        destino.value = "";
    }
}

function mdown(){
  obj = event.srcElement;
  if(obj.type == "radio"){
    carregarPercDesconto(obj);
  }
}

function checaSubmit(){
    erro = true;
    if(document.forms[0].elements['wlw-radio_button_group_key:{actionForm.aparelhosArray}'] && document.forms[0].elements['wlw-radio_button_group_key:{actionForm.aparelhosArray}'].length){
        for(i=0;i<document.forms[0].elements['wlw-radio_button_group_key:{actionForm.aparelhosArray}'].length;i++){
            if(document.forms[0].elements['wlw-radio_button_group_key:{actionForm.aparelhosArray}'][i].checked == true){
                erro = false;
            }
        }
    }else if(document.forms[0].elements['wlw-radio_button_group_key:{actionForm.aparelhosArray}']){
            if(document.forms[0].elements['wlw-radio_button_group_key:{actionForm.aparelhosArray}'].checked == true){
                erro = false;
            }
    }    
    if(erro){
        alert('Favor Selecionar um Aparelho')
        
   }else{        
        if(document.consultaMatrizOfertaForm.elements['descontoSelecionado'].selectedIndex <=0){
            alert('Favor Selecionar o Percentual de Desconto')
        }else if(document.consultaMatrizOfertaForm.elements['parcelaSelecionada'].selectedIndex <=0){
            alert('Favor Selecionar o Número da Parcela')    
        }    
        else{
            document.forms[0].action = "AbreDadosAparelho.do?acao=proxima";
			eval(zsaPP);
			document.forms[0].target = "";
            document.forms[0].submit();
        }
    }
    
}
function carregarPercDesconto(obj)
    {    
        for (i=0; i < document.all.length; i++){
            if (obj == document.all[i]){
                objId = document.all[i].value;
                i = i + 4;
                objIndex = document.all[i].value;
            }
        }
        if(document.consultaMatrizOfertaForm.elements["wlw-checkbox_key:{actionForm.inExcecao}"].checked){
            objAcao = "0"
        }else{
            objAcao = null
        }
        if(objAcao != null){
        document.forms[0].action = "showPercDescontoParcelas.do?index="+objId+"&acao="+objAcao+"&id="+objIndex;
        }else{
        document.forms[0].action = "showPercDescontoParcelas.do?index="+objId+"&id="+objIndex;
        }
		eval(zsaPP);
		document.forms[0].target = "";
        document.forms[0].submit();   
    }

//Uso em ConsultaMatrizOferta\dadosEntregaAparelho.jsp
  function msgEncerramento(valor)
	{
          
            dvObs.style.display = '';
	        document.forms[0].target = "ifrmObservacao";           
	        document.forms[0].action = "MsgEncerramento.do?id="+valor;                  
            eval(zsaPP);
			document.forms[0].submit();              
              
	}

function reterDadosEntrAp(obj)
{
	var dia  = new Date();
	var mes  = dia.getMonth() + 1;
    var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();
    
	if(document.ofertaAparelhoForm.elements["{actionForm.modelo}"].value == ""){
        alert("Favor preencher o campo Modelo!");
    }else if(document.ofertaAparelhoForm.elements["{actionForm.cor}"].value == ""){
        alert("Favor preencher o campo Cor!");
    }else if(document.ofertaAparelhoForm.elements["{actionForm.preco}"].value == ""){
        alert("Favor preencher o campo Preço!");
    }else if(document.ofertaAparelhoForm.idEntrega[0].checked == false && document.ofertaAparelhoForm.idEntrega[1].checked == false){
        alert("Favor selecionar Delivery ou Loja!");
    }else if(document.ofertaAparelhoForm.idEntrega[0].checked && document.ofertaAparelhoForm.elements["rua"].value == ""){
        alert("Favor preencher o campo Rua!");
    }else if(document.ofertaAparelhoForm.idEntrega[0].checked && document.ofertaAparelhoForm.elements["numero"].value == ""){
        alert("Favor preencher o campo Número!");
    }else if(document.ofertaAparelhoForm.idEntrega[0].checked && document.ofertaAparelhoForm.elements["bairro"].value == ""){
        alert("Favor preencher o campo Bairro!");
    }else if(document.ofertaAparelhoForm.idEntrega[0].checked && document.ofertaAparelhoForm.elements["cidade"].value == ""){
        alert("Favor preencher o campo Cidade!");
	}else if(document.ofertaAparelhoForm.idEntrega[0].checked && document.ofertaAparelhoForm.elements["cep"].value == ""){
        alert("Favor preencher o campo CEP!");
    }else if(document.ofertaAparelhoForm.elements["{actionForm.nmContato}"].value == "" && document.ofertaAparelhoForm.elements["{actionForm.rgContato}"].value == "" && document.ofertaAparelhoForm.elements["{actionForm.telContato}"].value == "" && document.ofertaAparelhoForm.elements["dtRetirada"].value == ""){
        alert("Favor preencher Autorização de Entrega para Terceiros \nou Agendar para Retirar em Loja!");
    }else if(document.ofertaAparelhoForm.idEntrega[0].checked && document.ofertaAparelhoForm.elements["{actionForm.telContato}"].value != "" && document.ofertaAparelhoForm.elements["{actionForm.telContato}"].value.length < 13){
		  alert("Telefone contato inválido");
	}else if(document.ofertaAparelhoForm.idEntrega[1].checked && !validaDataFinal(hoje, document.ofertaAparelhoForm.elements["dtRetirada"].value)){
		  alert("Data de retirada do aparelho maior que data atual. Por favor corrigir!");
	}else{
        valor = obj.href.split("?");
        valor[1]?action = document.ofertaAparelhoForm.action + "?" + valor[1]:action = document.ofertaAparelhoForm.action;
        document.ofertaAparelhoForm.target ="";
		document.ofertaAparelhoForm.action = "AbreDadosAparelho.do?acao=ok";
        eval(zsaPP);
		document.ofertaAparelhoForm.submit();
    }
    
}
 function pesquisaEndereco()
 {            
            document.forms[0].action="pesquisaEndereco.do";
            dvPesqEndereco.style.display = '';
            document.forms[0].target="ifrmPesqEndereco";
            eval(zsaPP);
			document.forms[0].submit();
 }
 
 //Uso em ConsultaMatrizOferta\pesquisaEndereco.jsp
 function pesquisarEnderecos(){               
        document.getElementById("cep").value = limpaInteiro(document.getElementById("cep").value);        
        if(document.getElementById("cep").value==""){        
            if((trim(document.getElementById('cidade').value)=="") || (document.getElementById('uf').value=="")){                
                alert('Quando não houve nº de CEP, favor preencher Município e UF!');
                return false;
            }else{
                document.forms[0].action="buscaEnderecoCD.do";
                document.forms[0].target="frmResultadoPesquisa"
                document.forms[0].submit();
            }
        }else{
            document.forms[0].action="buscaEnderecoCD.do";
            document.forms[0].target="frmResultadoPesquisa"
			eval(zsa);
            document.forms[0].submit();
        }
    }
    
//Uso em ConsultaMatrizOferta\listaEnderecos.jsp
function get_radio_value(){                
                if(document.forms[0].enderecoSelecionado){
                    if(document.forms[0].enderecoSelecionado.length){
                        for (var i=0; i < document.forms[0].enderecoSelecionado.length; i++){                   
                           if (document.forms[0].enderecoSelecionado[i].checked)
                              {
                              var rad_val = document.forms[0].enderecoSelecionado[i].value;
                              return rad_val;
                              }
                        }
                    }else{
                        if (document.forms[0].enderecoSelecionado.checked){
                            var rad_val = document.forms[0].enderecoSelecionado.value;
                            return rad_val;
                        }
                    }
                }else{
                    return false;
                }
            }            
                                    
            function selEndereco(idArray){                
                if(idArray){
					document.forms[0].action="buscaEnderecoCD.do?selecionado=TRUE&idArrayEndereco="+idArray;
                    document.forms[0].target="frmQuestionario";
					eval(zsa);
					document.forms[0].submit();                
                    top.frameApp.$('divPopupTI').hide();
                }else{
                    alert('Execute uma pesquisa e/ou selecione um endereço!');
                    return false;
                }
            }
            
            
 function verificarDataVigencia(){
    var dia  = new Date();
	var mes  = dia.getMonth() + 1;
    var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

    if(!validaDataFinal(hoje, document.forms[0].elements["dtInicio"].value)){         
		alert("Data início vigência menor que a data atual, favor corrigir!");
        return false;
    }else{
        return true;
    }
}