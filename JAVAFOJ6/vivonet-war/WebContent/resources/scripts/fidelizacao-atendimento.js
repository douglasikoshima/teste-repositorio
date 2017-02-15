function pesquisar(obj){
  if(document.actionRetencaoForm.elements["linhaPesquisa"].value.length < 13 &&
    (document.actionRetencaoForm.elements["linhaPesquisa"].value != "")){
        alert('Número da Linha inválido, favor Corrigir!')
  }else{
        document.forms[0].target = "";
        document.forms[0].action = obj.href + "?pesquisa=true";
        parent.parent.mostrar_div();
        document.forms[0].method = "POST";
        document.forms[0].submit();
  }
}


function validarAbrirHistorico(){
    valor = validarSelecaoLinha();
    if(valor != ""){
        top.frameApp.document.getElementById("idAnime").style.display = "block";
        abrirListaHistorico(valor);
    }else{
        alert("Por favor selecionar uma Linha!");
    }
}

function abrirListaHistorico(parametro) {
    document.forms[0].target = "ifrmNrProcesso";
    document.forms[0].action = "getListaHistorico.do?idLinha=" + parametro;
    document.forms[0].method = "POST";
    parent.parent.mostrar_div();
    dvNrProcesso.style.display = '';
    document.forms[0].submit();
    //dv_dvNrProcesso.innerText = sMsg;
}



function cancela(acao, obj){
    if (validarSelecaoLinha()!=""){
        document.forms[0]["linhaSelecionada"].value = validarSelecaoLinha();
        parent.parent.mostrar_div();
        document.forms["formLigacaoIndevida"].method = "POST";
        document.forms["formLigacaoIndevida"].submit();
        //dvCancelarAtd.style.display = '';
        /*
        document.forms[0].action="/FrontOfficeWeb/fidelizacao/BuscaDetalheLinha.do";
        //document.forms[0].action="/FrontOfficeWeb/fidelizacao/ConsultaMatrizOferta/filtroFimRetencao.do?matrizOferta=nao&acao="+acao;
        parent.parent.mostrar_div();
        document.forms[0].submit();
        */
    }else{
        alert("Favor selecionar uma Linha!");
    }
 }



function proxima(obj) {
    if (validarSelecaoLinha()!="") {
        document.forms[0]["linhaSelecionada"].value = validarSelecaoLinha();
        document.forms[0].target = "frmQuestionario";
        document.forms[0].action = obj.href;
        parent.parent.mostrar_div();
        document.forms[0].method = "POST";
        document.forms[0].submit();
        return true;
    } else {
        alert("Favor selecionar uma Linha!");
        return false;
    }
}

function validarSelecaoLinha(){
    indexLinha = "";
    var objRadio = document.forms[0].elements["wlw-radio_button_group_key:{actionForm.linhaSelecionada}"];
    if(objRadio.length){
        for(i=0; i < objRadio.length;i++){
            if(objRadio[i].checked){
                indexLinha = objRadio[i].value;
                break;
            }
        }
    }else{
        if(objRadio.checked){
            indexLinha = objRadio.value;
        }
    }

    return indexLinha;
}

function cancelarAtd(){
    parent.parent.mostrar_div();
    dvCancelarAtd.style.display='none';
    document.forms[0].target="";
    document.forms[0].action="cancelarAtendimento.do";
    document.forms[0].method = "POST";
    document.forms[0].submit();
}