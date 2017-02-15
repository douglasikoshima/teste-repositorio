function cancela(acao, obj)
{  
    document.forms[1].elements["tipoEncerramento"].value="5";
    document.forms[1].action="filtroFimRetencao.do?acao="+acao;
	parent.parent.mostrar_div();
	document.forms[1].target = "";
    document.forms[1].submit();
}


function reter(obj)
{
    if(document.consultaMatrizOfertaForm.elements["wlw-select_key:{pageFlow.listaPlanos}"].value == "")
	{
        alert("Favor selecionar um Novo Plano!");
    }
	else
	{
	   document.consultaMatrizOfertaForm.target = "";
       document.consultaMatrizOfertaForm.action="reterAdequacaoPlano.do?acao=ok";
	   parent.parent.mostrar_div();
	   document.consultaMatrizOfertaForm.submit();
    }
}

function agendar()
{
	parent.dvNrProcesso.style.display = '';
	document.consultaMatrizOfertaForm.target = "ifrmNrProcesso";
	document.consultaMatrizOfertaForm.action = "getDadosAgendarContato.do";
	parent.parent.mostrar_div();
	document.consultaMatrizOfertaForm.submit();
	
}

function enviarForm(obj){
    if(document.forms[1].elements['planoSelecionado'].value==0){
        alert("Por favor selecionar um Novo Plano.");
        return false;
    }
    parent.parent.mostrar_div();
    document.forms[1].target = "";
    document.forms[1].action = "reterAdequacaoPlano.do?acao=ok";
    document.forms[1].submit();
}