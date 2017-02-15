zsa="top.frameApp.mostrar_div();"
zso="top.frameApp.oculta_div();"
corBack="document.body.style.backgroundColor = '#ededed';"

//Uso em index.jsp
function desabilitaSubmit()      {aba.desabilitaAbas();}
function habilitaSubmit()        {aba.habilitaAbas();}
function selecionaAbaAparelho() {aba.selecionaAba(document.getElementById(abaAparelho), false);}
function selecionaAbaDestino() {aba.selecionaAba(document.getElementById(abaDestino), false);}
function selecionaAbaMensagens() {aba.selecionaAba(document.getElementById(abaMensagens), false);}
function selecionaAbaIntencao() {aba.selecionaAba(document.getElementById(abaIntencao), false);}
function selecionaAbaOferta() {aba.selecionaAba(document.getElementById(abaOferta), false);}
function selecionaAbaBonus() {aba.selecionaAba(document.getElementById(abaBonus), false);}

//Uso em manterBonus.jsp
function enviarPesquisa(obj){
        eval(zsa);
        document.forms[0].action = obj.href;
        document.forms[0].submit();
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
            
    function preValidaKey(){    
        if (window.event.keyCode == 13){               
            window.event.keyCode = 0;                   
            return false;
        }
    }

    function incluirBonus(obj){    
        if(document.actionManterBonusForm.idRegional.value == ""){
            alert("Favor selecionar uma Regional!");
        }else if(document.actionManterBonusForm.elements["{actionForm.descricao}"].value == ""){
            alert("Favor preencher o campo Bônus!");
        }else if(document.forms[0].ofertaBonusSelecionada.value==""){
            alert("Favor selecionar um Tipo Bônus!");
        }else if(document.actionManterBonusForm.elements["{actionForm.validade}"].value == ""){
            alert("Favor preencher o campo Validade(em dias)!");
        }else if(document.actionManterBonusForm.elements["{actionForm.validade}"].value == "0" || document.actionManterBonusForm.elements["{actionForm.validade}"].value == "00" || document.actionManterBonusForm.elements["{actionForm.validade}"].value == "000"){
            alert("O campo Validade(em dias) não pode ter valor 0!");
        }else{
            
            enviarPesquisa(obj);
           
        }
    }
  
function excluir(obj){
    if( confirm('Deseja realmente excluir este registro?') ){   
        enviarPesquisa(obj);
    }
}

function pesquisarBonus(obj){
    mensagem = verificarCombos()
    if(mensagem != ""){
        alert(mensagem);
        return false;
    }
    
    enviarPesquisa(obj);
}

function verificarCombos(){

    mensagem = "";    
    if(document.forms[0].idRegional.value==""){ mensagem = "uma Regional"; }
    if(document.forms[0].ofertaBonusSelecionada.value==""){ mensagem+= mensagem != "" ? " e um Tipo Bônus" : "um Tipo Bônus"; }
    if(mensagem != ""){ return "Selecione " + mensagem + "!"; }
    
    return "";
}


//Uso em manterDefDestino.jsp
    function excluirDefDestino(obj){
        if (confirm('Deseja realmente excluir este registro?')){
            valor = obj.href.split("?");
            valor[1]?action = document.listaDefDestinoForm.action + "?" + valor[1]:action = document.listaDefDestinoForm.action;
            anchor_submit_form("Netui_Form_0",action);
        }
    }
    function incluirDefDestino(obj){
        if(document.listaDefDestinoForm.elements["{actionForm.descricao}"].value == ""){
            alert("Favor preencher o Destino Previsto!");
        }else{
            valor = obj.href.split("?");
            valor[1]?action = document.listaDefDestinoForm.action + "?" + valor[1]:action = document.listaDefDestinoForm.action;
            anchor_submit_form("Netui_Form_0",action);
        }
    }

//Uso em manterPlanos.jsp
function excluirPlanos(){
    if (confirm('Deseja realmente excluir este registro?')){
        top.frameApp.mostrar_div();
        return true;
    }else{return false};
}


function validarPlanos(obj){
 var error_message = "";

            if (document[getNetuiTagName("planosForm",this)][getNetuiTagName("plano",this)].value =="" || document[getNetuiTagName("planosForm",this)][getNetuiTagName("plano",this)].value ==null) error_message += "- Plano Necessario \n";
            if(error_message) 
            {
                alert("Preencha os campos:\n" + error_message);
                return false; 
            }else{            
            enviarPesquisa(obj);
            }
}
