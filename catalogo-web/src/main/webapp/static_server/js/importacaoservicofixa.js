var $jq = jQuery.noConflict();
function esconderMsgs() {
	document.getElementById('msg_obrigatorio').style.display = "none";
	document.getElementById('selecionar_tipo').style.display = "none";
	document.getElementById('divErros').style.display = "none";
}

function obterModelo() {
	esconderMsgs();
	if (document.getElementById('idTipoImportacao').value == 0) {
		document.getElementById('selecionar_tipo').style.display = "block";
		return;
	}
	document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/obterModelo.do";
	document.getElementById('acaoForm').submit();					
}

function importar() {			
	esconderMsgs();
	if (document.getElementById("idTipoImportacao").value == 0 || document.getElementById("fileImport").value == "") {
		document.getElementById('msg_obrigatorio').style.display = "block";
		return;
	}
	
	var fileArray = document.getElementById('fileImport').value.split('\\');
	var fileName = fileArray[fileArray.length-1];
	fileArray = fileName.split('.');
	var exten = fileArray[fileArray.length-1].toUpperCase();
	
	if (exten != "CSV") {
		document.getElementById('conteudo_divErros').innerHTML = "Tipo de Arquivo Inv&aacute;lido!";
		document.getElementById('divErros').style.display = "block";
		return;				
	}
	
	document.getElementById('divErros').style.display = "none";
	if (confirm("Deseja importar o Arquivo \"" + fileName + "\" ?")) {
		document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaoservicofixa/importar.do";
		document.getElementById('acaoForm').submit();
	}
}

function init(){	
	if (document.getElementById('erro').value != "") {
		document.getElementById('conteudo_divErros').innerHTML = document.getElementById('erro').value;
		document.getElementById('divErros').style.display = "block";
	}
}