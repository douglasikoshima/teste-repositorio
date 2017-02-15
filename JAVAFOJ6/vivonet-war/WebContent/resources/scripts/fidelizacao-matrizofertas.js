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


function selCombos(){
    for(i=0;i < document.forms[0].ofertasDisp.options.length; i++){
        document.forms[0].ofertasDisp.options[i].selected = "true"
    }
    for(i=0;i < document.forms[0].ofertasReal.options.length; i++){
        document.forms[0].ofertasReal.options[i].selected = "true"
    }
    for(i=0;i < document.forms[0].ofertasAceita.options.length; i++){
        document.forms[0].ofertasAceita.options[i].selected = "true"
    }
}

function proxima(obj){
    if(document.forms[0].ofertasAceita.options.length < 1)
    {
            alert("Selecione uma oferta!");
            return false;
    }else if(verificaTipoEncerramento() == false){

        selCombos();
        top.frameApp.mostrar_div();
        document.forms[0].target = "";
        document.forms[0].action = "redirectMatrizOferta.do";
        document.forms[0].submit();

    }else{
        alert("Não é possível continuar, para finalizar a retenção, clique no botão \"Reter\".");
        return false;
    }
}


function verificaTipoEncerramento(){
 if(ofertas[document.forms[0].ofertasAceita.options[0].value][0].indexOf("PT") != -1 || ofertas[document.forms[0].ofertasAceita.options[0].value][0].indexOf("AG") != -1 || ofertas[document.forms[0].ofertasAceita.options[0].value][0].indexOf("NA") != -1){
    //return ofertas[document.forms[0].ofertasAceita.options[0].value][1];
    return "3";
 }
    return false;
}


function cancela(acao, obj){
    if(document.forms[0].ofertasAceita.options.length == "1" && acao!='agendar' ){
        msg = acao == 'cancelar' ?
                            "Não é possível cancelar linha " :
                            "A opção \"Vai Pensar\" não está disponível ";
        alert(msg + "se houver oferta aceita.\nSe deseja efetuar essa ação, remova a oferta aceita da lista.");
        return false;
    }

    if (acao == 'cancelar') {

        if(document.forms[0].ofertasReal.options.length <= 0){
            alert("Não é possível cancelar linha sem realizar ofertas.");
            return false;
        }
        if (cancelarLinha() == 6) {
            var inLinhasAssociadas = false;
            if (cancelarLinhasAssociadas() == 6) {
                inLinhasAssociadas = true;
            }
            selCombos();
            document.forms[0].action ="reterCMO.do?acao=" + acao + "&inLinhasAssociadas=" + inLinhasAssociadas;
            document.forms[0].elements["tipoEncerramento"].value = "2";
        } else {
            return false;
        }

	} else if(acao == 'vaipensar') {
        selCombos();
        document.forms[0].action ="reterCMO.do?acao="+acao;
        document.forms[0].elements["tipoEncerramento"].value="5";

	}else if(acao == 'agendar'){
        selCombos();
        parent.parent.mostrar_div();
        parent.dvNrProcesso.style.display = '';
        document.forms[0].action="getDadosAgendarContato.do";
		document.forms[0].target = "ifrmNrProcesso";
        document.forms[0].submit();
        return true;
	}

    parent.parent.mostrar_div();
    document.forms[0].target = "";
	document.forms[0].submit();

}


function reter(){
    if(document.forms[0].ofertasAceita.options.length <= 0){
        alert("Não é possivel finalizar retenção sem oferta aceita.");
        return false;
    }
    if(verificaTipoEncerramento() == false){
        alert("Não é possível finalizar retenção para esse tipo de oferta aceita.\nSelecione outra oferta.");
        return false;
    }

    selCombos();

    document.forms[0].sgOferta.value = ofertas[document.forms[0].ofertasAceita.options[0].value][0];

    parent.parent.mostrar_div();
    document.forms[0].target = "";
    document.forms[0].action = "reterCMO.do";
    document.forms[0].elements["tipoEncerramento"].value=verificaTipoEncerramento();
    document.forms[0].submit();

}