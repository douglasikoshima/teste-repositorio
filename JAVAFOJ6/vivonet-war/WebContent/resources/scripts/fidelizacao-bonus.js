function reterBonus(obj)
{
	var dia  = new Date();
	var mes  = dia.getMonth() + 1;
    var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

    if(document.consultaMatrizOfertaForm.bonusSelecionado.value == "0"){                    
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
    
        if((document.forms[0].elements["inExcecao"]) && !document.forms[0].elements["inExcecao"].checked){carregarData();};
        
        document.consultaMatrizOfertaForm.target = "";
        document.consultaMatrizOfertaForm.action="reterBonus.do";
		eval(zsaPP);
        document.consultaMatrizOfertaForm.submit();
        
    }
}
function cancela(acao, obj)
{  
    document.forms[0].elements["tipoEncerramento"].value="5";
    document.forms[0].action="filtroFimRetencao.do?acao="+acao;
	parent.parent.mostrar_div();
	document.forms[0].target = "";
    document.forms[0].submit();
}
function agendar()
{
	parent.dvNrProcesso.style.display = '';
	document.forms[0].target = "ifrmNrProcesso";
	document.forms[0].action = "getDadosAgendarContato.do";
	parent.parent.mostrar_div();
	document.forms[0].submit();
	
}