var $jq = jQuery.noConflict();

function importar() {
	
	var fileStr = document.getElementById('fileImport').value;
	var fileArray = new Array();
	fileArray = fileStr.split('\\');

	if (validarForm()) {
		if (confirm("Deseja importar o Arquivo \"" + fileArray[fileArray.length-1] + "\" ?")) {
			document.getElementById("contentCriticas").style.display = "none";
			document.getElementById('importacaoTipoMatrizContratoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaotipomatriz/importar.do";
			document.getElementById('importacaoTipoMatrizContratoForm').submit();
		}
	}
}

function exportar() {
	document.getElementById('importacaoTipoMatrizContratoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaotipomatriz/exportar.do";
	document.getElementById('importacaoTipoMatrizContratoForm').submit();
	
}

function validar(){
	document.getElementById('importacaoTipoMatrizContratoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaotipomatriz/validar.do";
	document.getElementById('importacaoTipoMatrizContratoForm').submit();
}

function liberar() {
	if (confirm("Deseja liberar a exportação de  arquivo para trabalho de outro usuário?")) {
		document.getElementById('importacaoTipoMatrizContratoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaotipomatriz/liberar.do";
		document.getElementById('importacaoTipoMatrizContratoForm').submit();
	}
}

function clearPage() {
	document.location.href = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaotipomatriz/ImportacaoTipoMatrizContratoAction.do";
}	

function init(){

}

function validarForm() {
	var camposErro = "";
	var retorno = true;
	
	if (document.getElementById('fileImport').value == "") {
		camposErro += "Para a importação é necessário a seleção de um arquivo";
	}
	
	if (camposErro != "") {
		generateContentError(camposErro);
		retorno = false;
	}
	
	return retorno;
}

function producao() {
	if (document.getElementById('isCriticas').value == "S") {
		if(confirm("O arquivo importado apresenta críticas. Deseja disponibilizar os dados para uso no Sistema mesmo assim?")) {
			document.getElementById('importacaoTipoMatrizContratoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaotipomatriz/liberarProducao.do";
			document.getElementById('importacaoTipoMatrizContratoForm').submit();	
		}
	} else {
		document.getElementById('importacaoTipoMatrizContratoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaotipomatriz/liberarProducao.do";
		document.getElementById('importacaoTipoMatrizContratoForm').submit();
	}
}

function exportarCriticas() {
	document.getElementById('importacaoTipoMatrizContratoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaotipomatriz/exportarCriticas.do";
	document.getElementById('importacaoTipoMatrizContratoForm').submit();
}

function liberarExport() {
	document.getElementById('importacaoTipoMatrizContratoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/importacaotipomatriz/liberarExport.do";
	document.getElementById('importacaoTipoMatrizContratoForm').submit();
}