zsa="top.frameApp.mostrar_div();"
zso="top.frameApp.oculta_div();"
corBack="document.body.style.backgroundColor = '#ededed';"

//Uso em index.jsp
function desabilitaSubmit()      {aba.desabilitaAbas();}
function habilitaSubmit()        {aba.habilitaAbas();}
function selecionaAbaAparelho()  {aba.selecionaAba(document.getElementById(abaAparelho), false);}
function selecionaAbaOfertas()   {aba.selecionaAba(document.getElementById(abaOfertas), false);}
function selecionaAbaMensagens() {aba.selecionaAba(document.getElementById(abaMensagens), false);}
function selecionaAbaBonus()     {aba.selecionaAba(document.getElementById(abaBonus), false);}
function selecionaScript()       {aba.selecionaAba(document.getElementById(abaScript), false);}
function selectionaAbaMigracao() {aba.selecionaAba(document.getElementById(abaMigracao), false);}

function fecharFrame() 
 {
 document.getElementById("dvNrProcesso").style.display = "none";
 document.getElementById("dvNrProcesso").src = "about:blank";
 }
 
//Uso em ConsultaMatrizAparelho.jsp
function abreGeral(parametro)
{
  parametro = parametro.split("X")  
  param = parametro[parametro.length - 1];
  fSel = document.forms["frmSelecao"];
  
  top.frameApp.dvNrProcesso.style.display = '';
  fSel.target = "ifrmNrProcesso";
  fSel.action = "/FrontOfficeWeb/fidelizacao/Configurar/ConfigurarMatrizAparelho/showDadosAparelho.do?id=" + param;
  fSel.submit();
  
 } 
 function expandirNo(idPai){
  if (!treeAparelhos.getSelected().isAddEnabled()) 
  {  return; }
  
  eval(zsa);
  document.forms[0].target = "iFrame";
  document.forms[0].action = "montarArvore.do?idPai=" + idPai;
  document.forms[0].submit();
 }
 
 //Uso em matrizAparelhso.jsp
 function LimpaSelecao(lista) {
    for(var i = 0; i < lista.options.length; i++) {
        if(lista.options[i].selected) {
            lista.options[i] = null;
            i = -1;
        }
    }
}
function VerificaDuplicidadeLista(valor, lista) {
    for(var i = 0; i < lista.options.length; i++) {
        if(lista.options[i].text == valor) {
            return(true);
        }
    }
    return(false);
}
    
function buscaOfertas(){
    document.forms["matrizOfertaForm"].action = "ActionMatrizOferta.do?acao=pesquisarOfertas";
    document.forms["matrizOfertaForm"].submit();
}

function salvarMatrizAp(obj){

    for(i=0; i < document.forms[0].aparelhosSelecionados.options.length; i++){
        document.forms[0].aparelhosSelecionados.options[i].selected = true;
    }
    if(document.forms[0].elements["regionalSel"].value == ""){
        alert("Favor selecionar uma Regional!");
    }else if(document.forms[0].elements["tipoClienteSel"].value == ""){
        alert("Favor selecionar um Tipo de Cliente!");
    }else if(document.forms[0].elements["segmentacaoSel"].value == ""){
        alert("Favor selecionar uma Seguimentação!");
    }else{
    
        //valor = obj.href.split("?");
        ///valor[1]?action = document.actionMatrizAparelhoForm.action + "?" + valor[1]:action = document.actionMatrizAparelhoForm.action;
        //anchor_submit_form("Netui_Form_0",action);
        document.forms[0].action ="ActionMatrizAparelho.do?acao=salvar";
        document.forms[0].submit();
    }
}

function matrizAparelho() {
    dvAparelho.style.display = '';
    document.forms[0].target = "ifrmAparelho";
    document.forms[0].action="montarArvore.do?acao=matrizAparelho";
    document.forms[0].submit();
}

function actionMatrizAparelhoCon(){
    eval(zsa);
    document.forms[0].action = "ActionMatrizAparelho.do?acao=consultar";    
    document.forms[0].submit();

}
function cerrar(){
    dvAparelho.style.display = 'none';    
    document.forms[0].target = '';
    document.forms[0].method = "POST";
    document.forms[0].action = "VuelveConsMatriz.do";    
    document.forms[0].submit();    
}

//Uso em dadosAparelho.jsp
    function salvarDadosAp(){
                
                var inicio =0;
                var fim =0;
                
            if( (document.showDadosAparelhoForm.parcelasSelecionadas.options.length == 0) || (document.showDadosAparelhoForm.descontosSelecionados.options.length == 0)){
            
                alert('Por favor, selecionar valores de Parcela e de Desconto!')
                return false;
            
            }else{            
            
                    for(i=0; i < document.showDadosAparelhoForm.elements.length; i++){
                    
                        if(document.showDadosAparelhoForm.elements['inicio'] == document.showDadosAparelhoForm.elements[i]){
                            inicio = i;
                        }
                        if(document.showDadosAparelhoForm.elements['fim'] == document.showDadosAparelhoForm.elements[i]){
                            fim = i;
                        }
                    }
              
                    
                          document.showDadosAparelhoForm.arrayCodigoSAP.options[0] = new Option(document.showDadosAparelhoForm.elements[inicio+1].value,document.showDadosAparelhoForm.elements[inicio+1].value);                            
                          document.showDadosAparelhoForm.arrayPreco.options[0] = new Option(document.showDadosAparelhoForm.elements[inicio+2].value,document.showDadosAparelhoForm.elements[inicio+2].value);                          
                          document.showDadosAparelhoForm.arrayQtdeEstoque.options[0] = new Option(document.showDadosAparelhoForm.elements[inicio+3].value,document.showDadosAparelhoForm.elements[inicio+3].value);
                    
                    
                    
                    if (document.showDadosAparelhoForm.arrayPreco.options){
                        for(i=0; i < document.showDadosAparelhoForm.arrayPreco.options.length; i++){
                            document.showDadosAparelhoForm.arrayPreco.options[i].selected = true
                        }
                    }
                    if (document.showDadosAparelhoForm.arrayCodigoSAP.options){
                        for(i=0; i < document.showDadosAparelhoForm.arrayCodigoSAP.options.length; i++){
                            document.showDadosAparelhoForm.arrayCodigoSAP.options[i].selected = true
                        }
                    }
                    if (document.showDadosAparelhoForm.arrayQtdeEstoque.options){
                        for(i=0; i < document.showDadosAparelhoForm.arrayQtdeEstoque.options.length; i++){
                            document.showDadosAparelhoForm.arrayQtdeEstoque.options[i].selected = true
                        }
                    }
                    if (document.showDadosAparelhoForm.parcelasSelecionadas.options){
                        for(i=0; i < document.showDadosAparelhoForm.parcelasSelecionadas.options.length; i++){
                            document.showDadosAparelhoForm.parcelasSelecionadas.options[i].selected = true
                        }
                    }
                    if (document.showDadosAparelhoForm.descontosSelecionados.options){
                        for(i=0; i < document.showDadosAparelhoForm.descontosSelecionados.options.length; i++){
                            document.showDadosAparelhoForm.descontosSelecionados.options[i].selected = true
                        }
                    }
                    erro = false;
                    for(i=inicio+1; i < fim; i++){
                          if(document.showDadosAparelhoForm.elements[i].value == ""){
                            erro = true;
                          }
                    }
                    if(erro){
                        alert("Favor nâo deixar campos em braco!")
                    }else{
                        document.showDadosAparelhoForm.action = "showDadosAparelho.do?acao=salvar";
                        document.showDadosAparelhoForm.submit();
                    }
                }
                             
        }                
                
        semCaracterEspeciais = new RegExp("[0-9a-zA-z%]");

        function validacionStrEspecial(obj){
          valor = obj.value;
          for(i=0;i<valor.length;i++){
            if(!semCaracterEspeciais.test(valor.charAt(i))){
              valor = valor.substring(0,i) + valor.substring(i+1,valor.length);
              i = -1;
            }
          }
          obj.value = valor;
        }
        
//Uso em configurarMatrizOferta.jsp
function salvarMatrizOf(obj){
    if(document.matrizOfertaForm.regionalSel.value == ""){
        alert("Favor selecionar uma Regional!");
    }else if(document.matrizOfertaForm.tipoClienteSel.value == ""){
        alert("Favor selecionar um Tipo de Cliente!");
    }else if(document.matrizOfertaForm.segmentacaoSel.value == ""){
        alert("Favor selecionar uma Seguimentação!");
    }else if(document.matrizOfertaForm.intencaoSel.value == ""){
        alert("Favor selecionar uma Intenção de Cancelamento!");
    }else if(document.matrizOfertaForm.destinoSel.value == "netui_null"){
        alert("Favor selecionar um Destino Previsto!");
    }else{
        for(i=0; i < document.matrizOfertaForm.ofertasSelecionadasSel.length; i++){
            document.matrizOfertaForm.ofertasSelecionadasSel.options[i].selected = true;
        }
        document.matrizOfertaForm.action = "ActionMatrizOferta.do?acao=salvar";
        document.matrizOfertaForm.submit();
    }
}
function buscaOfertas(){
    eval(zsa);
    document.forms["matrizOfertaForm"].action = "ActionMatrizOferta.do?acao=pesquisarOfertas";
    document.forms["matrizOfertaForm"].submit();
}
function buscaDestino(){
    eval(zsa);
    document.matrizOfertaForm.action = "ActionMatrizOferta.do?acao=pesquisarDestino";
    document.matrizOfertaForm.submit();
}
function matrizOferta() {
    divMatriz = top.frameApp.document.getElementById('dvMatOfertas');
    divMatriz.style.display = '';
    top.frameApp.ifrmMatOfertas.location.href = '/FrontOfficeWeb/fidelizacao/Configurar/ConfigurarMatrizOferta/showMatrizOferta.do?acao=matrizOferta';
}
function limpaMatrizOf(){
    for(i=0;i<document.matrizOfertaForm.intencaoSel.options.length;i++){
        if(document.matrizOfertaForm.intencaoSel.options[i].value == ""){
            document.matrizOfertaForm.intencaoSel.options[i].selected = true;
        }
    }
    deleteFixoTodos(document.matrizOfertaForm.destinoSel);
    deleteFixoTodos(document.matrizOfertaForm.ofertasSelecionadasSel);
    deleteFixoTodos(document.matrizOfertaForm.ofertaDispSel);
}

//Uso em matrizScript.jsp
function salvarMatrizScript(){
    if(document.actionMatrizScriptForm.idRegional.value == ""){
        alert("Favor selecionar uma Regional!");
    }else if(document.actionMatrizScriptForm.idIntencao.value == ""){
        alert("Favor selecionar uma Intenções Possíveis!");
    }else{
    
        eval(zsa);
        for(var i = 0; i < document.actionMatrizScriptForm.destinoSelecionadoUtil.options.length; i++){
            document.actionMatrizScriptForm.destinoSelecionadoUtil.options[i].selected = true;
        }
        for(var i = 0; i < document.actionMatrizScriptForm.destinoSelecionadoDisp.options.length; i++){
            document.actionMatrizScriptForm.destinoSelecionadoDisp.options[i].selected = true;
        }
        document.actionMatrizScriptForm.action = document.actionMatrizScriptForm.action + "?acao=salvar";
        document.actionMatrizScriptForm.submit();
    }
}
		
function buscaDestMatrizScript(){
    eval(zsa);
    document.forms[0].action = "ActionMatrizScript.do?acao=pesquisar";
    document.forms[0].submit();
}
function limpaMatrizScript(){
    for(i=0;i<document.actionMatrizScriptForm.idIntencao.options.length;i++){
        if(document.actionMatrizScriptForm.idIntencao.options[i].value == ""){
            document.actionMatrizScriptForm.idIntencao.options[i].selected = true;
        }
    }
    moveTodos(actionMatrizScriptForm.destinoSelecionadoUtil, actionMatrizScriptForm.destinoSelecionadoDisp);
}

//Uso em configurarMensagem.jsp
function excluirMsg(obj){
    if (confirm('Deseja realmente excluir este registro?')){
        valor = obj.href.split("?");
        valor[1]?action = document.listaMsgEncerramentoForm.action + "?" + valor[1]:action = document.listaMsgEncerramentoForm.action;
        anchor_submit_form("Netui_Form_0",action);
        document.listaMsgEncerramentoForm.submit();
    }
}

function incluirMsg(obj){
    if(document.listaMsgEncerramentoForm.regionalSelecionado.value == ""){
        alert("Favor selecionar uma Regional!");
    }else if(document.listaMsgEncerramentoForm.acaoRetencaoSelecionado.value == ""){
        alert("Favor selecionar uma Ação de Retençao!");
    }else if(document.listaMsgEncerramentoForm.elements["{actionForm.descricao}"].value == ""){
        alert("Favor preencher o campo Descrição!");
    }else{
    
    eval(zsa);    
    valor = obj.href.split("?");
    valor[1]?action = document.listaMsgEncerramentoForm.action + "?" + valor[1]:action = document.listaMsgEncerramentoForm.action;
    anchor_submit_form("Netui_Form_0",action);    
    enviarPesquisa(obj);
    }
}
                 
function limparMsg(){
    document.getElementById('tableTitle_body').style.display="none"
}                        

//Uso em ConfigurarMigracao.jsp
function excluirMigracao(obj){
    if (confirm('Deseja realmente excluir este registro?')){
        enviarPesquisa(obj);
    }
}

function incluirMigracao(obj){
    
    document.listaMigracaoForm.elements["{actionForm.descricao}"].value = trim(document.listaMigracaoForm.elements["{actionForm.descricao}"].value);
    document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value = document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value.replace(".","").replace(".","");
    
    valor = document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value.split(",");
    if(valor[1]){bonus = valor[0]}else{bonus = valor}
    if(document.listaMigracaoForm.idRegional.value == ""){
        alert("Favor selecionar uma Regional!");
    }else if(document.listaMigracaoForm.idTipoPessoa.value == ""){
        alert("Favor selecionar um Tipo de Cliente!");
    }else if(document.listaMigracaoForm.elements["{actionForm.descricao}"].value == ""){
        alert("Favor preencher o campo Descrição!");
    }else if(document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value == ""){
        alert("Favor preencher o campo Bônus(em Reais)!");
    }else if(document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value != "" && (parseInt(valor) > 9999)){
        alert("Campo Bônus(em Reais) inválido, favor corrigir!");
    }else if(document.listaMigracaoForm.elements["{actionForm.validade}"].value == ""){
        alert("Favor preencher o campo Validade do Crédito(em dias)!");
    }else{
			  if(!decimal.test(document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value)){
					document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value = document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value + "00"
				}else{
					valorSplit = document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value.split(",");
					if(valorSplit[1].length < 1){
						document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value = document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value + "00"
					}else if(valorSplit[1].length < 2){
						document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value = document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value + "0"
					}
				}
				document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value = document.listaMigracaoForm.elements["{actionForm.vlBonus}"].value.replace(",","")
        
        enviarPesquisa(obj);
    }
}

function listaMigracao(obj){    
    valor = obj.href.split("?");
    valor[1]?action = document.listaMigracaoForm.action + "?" + valor[1]:action = document.listaMigracaoForm.action;
    anchor_submit_form("Netui_Form_0",action);
}


function enviarPesquisa(obj){
        eval(zsa);
        document.forms[0].action = obj.href;
        document.forms[0].submit();
}

 
 function abreDetaOferExcecao(obj){
        valor = document.all[obj.sourceIndex + 1].value;        
        document.getElementById('dvDetalhe').style.display = '';
        document.forms[0].target = "ifrmDetalhe";           
        document.forms[0].action = "showDetalheOferta.do?acao=ok&id="+valor;            
        document.forms[0].submit();              
	}

function salvarOferExcecao(obj){
    eval(zsa);
    valor = document.forms[0].elements['{pageFlow.arrayOferta[0].idOfertaAceita}'].value;
    document.forms[0].action ="OfertaExcecao.do?acao=ok&id="+valor;
    document.forms[0].submit();         
}    
    
    
 function cerrarOferExcecao(){
    document.forms[0].target = '';
    document.forms[0].method = "POST";
    document.forms[0].action = "OfertaExcecao";    
    document.getElementById('dvDetalhe').style.display = 'none'; 
 }
 
 
 // ppaula - 06/12/2004
 
 function checaVLBonus(obj){
  valorDecimal = "";
  valor = obj.value.replace(".","");
  valor = obj.value.replace(",","");
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
      
      if(valor.length >= 3){
        contador = 0;
        valorMilhar = "";
        for(i=valor.length; i >= 0; i --){
            if((contador == 3 || contador == 6) && i > 0){
               valorMilhar = "." + valor.charAt(i) + valorMilhar;   
            }else{
                valorMilhar = valor.charAt(i) + valorMilhar;
            }
            contador ++;
        }
        valor = valorMilhar;
      }
     
      valor = valor + "," + valorDecimal.substring(0,2);
    }else{
        if(valor.length >= 3){
        contador = 0;
        contadorD = 0;
        valorMilhar = "";
        for(i=valor.length; i >= 0; i --){
            if(contadorD == 2 && i > 0){
               valorMilhar = "," + valor.charAt(i) + valorMilhar;   
            }else if(contador == 5 && i > 0){
               valorMilhar = "." + valor.charAt(i) + valorMilhar;   
            }else{
                valorMilhar = valor.charAt(i) + valorMilhar;
            }
            contador ++;
            contadorD ++;
        }
        valor = valorMilhar;
      }
    
    
    }
    oldValor = valor;
    oldObj = obj;
    
  }
  obj.value = valor;
}
 
 
 
 
 // fim ppaula