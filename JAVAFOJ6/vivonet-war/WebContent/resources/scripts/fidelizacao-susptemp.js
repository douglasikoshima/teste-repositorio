function cancela(acao, obj){
    document.forms[0].elements["tipoEncerramento"].value="5";
    document.forms[0].action="filtroFimRetencao.do?acao="+acao;
	parent.parent.mostrar_div();
	document.forms[0].target = "";
    document.forms[0].submit();
}


function checaExcecao(obj){
	if(obj.checked){
		document.forms[0].elements["dtFim"].readOnly = false;
	}else{
		document.forms[0].elements["dtFim"].readOnly = true;
		carregarData();
	}
}


function reter(){
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
		parent.parent.mostrar_div();
		document.forms[0].target = "";
        document.forms[0].action = "reterSuspensaoTemporaria.do";
        document.forms[0].submit();              
	}
}

function agendar()
{
	parent.dvNrProcesso.style.display = '';
	document.forms[0].target = "ifrmNrProcesso";
	document.forms[0].action = "getDadosAgendarContato.do";
	parent.parent.mostrar_div();
	document.forms[0].submit();
}