function cancela(acao, obj){
    document.forms[1].elements["tipoEncerramento"].value="5";
    document.forms[1].action="filtroFimRetencao.do?acao="+acao;
    parent.parent.mostrar_div();
    document.forms[1].target = "";
    document.forms[1].submit();
}
     
   
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
    

function reter(obj){
    if(document.consultaMatrizOfertaForm.elements["{actionForm.quantidade}"].value == ""){
        alert("Favor preencher o campo Quantidade!");
    }else{        
		parent.parent.mostrar_div();
		document.consultaMatrizOfertaForm.target = "";
        document.consultaMatrizOfertaForm.action="reterPontos.do";
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