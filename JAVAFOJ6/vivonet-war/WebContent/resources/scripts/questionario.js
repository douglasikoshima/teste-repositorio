function dataAniversario_salvar() {
    objForm = document.forms.salvaAgendaAniversarioForm;
    if(trim(objForm.dataAniversario.value) != "" && !validaData(objForm.dataAniversario.value)) {                
        alert("Data inv�lida!");
        objForm.dataAniversario.focus();
    } else {
        top.frameApp.mostrar_div();
        objForm.submit();
    }
}

function agendaAniversario() {
    document.getElementById("dvAgendaAniversario").style.display = "block";
    document.getElementById("ifrmAgendaAniversario").src = "/FrontOfficeWeb/questionario/dataAniversario.do";
} 

function questionarioCmpAtendimento_fecharDataAniversario() {
    document.getElementById("dvAgendaAniversario").style.display = "none";
    top.frameApp.oculta_div();
}

function questionarioCmpAtendimento_terminarClick() {
    if(confirm("Confirma o t�rmino do questionario?\nEst� op��o n�o permite altera��o das perguntas!")) {
        top.frameApp.mostrar_div();        
        document.iniciaQuestionarioForm.action = "/FrontOfficeWeb/questionario/finalizarQuestionario.do";
        document.iniciaQuestionarioForm.submit();
    }
}


function questionario_proxima() {
    var valida = false;    
    var objForm =  document.iniciaQuestionarioForm;
    var inObrigatoria = objForm.inObrigatoria.value;

//    if( inObrigatoria == "0" )
//        inObrigatoria = "";

    if ( inObrigatoria ) {
	    //Valida��o obrigatoria
	    if (objForm.indiceResposta) {
		if( objForm.indiceResposta.length ) {
		    for(i=0; i < objForm.indiceResposta.length; i++){
			if( objForm.indiceResposta[i].checked == true) {
			    valida = true;
			}
		    }
		} else {
		    // No caso de existir apenas uma op��o
		    valida = objForm.indiceResposta.checked;
		}
	    }
	    if (objForm.textoResposta) {
		var tmp = new String(objForm.textoResposta.value);
		var reg = new RegExp("[\n\r]", "g");
		tmp = tmp.replace(reg, "");                   
		if(trim(tmp) != "") {
		    valida = true;
		}
	    }
    } else {
	//Valida��o N�o obrigatoria
	valida = true;
    }
    if ( !valida ) {
        if ( objForm.indiceResposta ) {
            alert("Favor selecionar uma resposta!"); 
        } else {
            alert("Favor preencher o campo de Resposta!");
            objForm.textoResposta.focus();
        }
    } else {
        objForm.submit();
        top.frameApp.mostrar_div();
    }
}