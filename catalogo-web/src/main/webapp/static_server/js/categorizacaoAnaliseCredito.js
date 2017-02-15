var $jq = jQuery.noConflict();

function clearPage() {
	document.location.href = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/categorizacao/CategorizacaoAnaliseCreditoAction.do";
}

function search() {
	if (validarFormSearch()) {
		document.getElementById('categorizacaoAnaliseCreditoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/categorizacao/search.do";
		document.getElementById('categorizacaoAnaliseCreditoForm').submit();
	}
}

function validarFormSearch() {
	var camposErro = "";
	var retorno = true;
	
	if (document.getElementById('optPesquisa').value == 'O') {
		if (document.getElementById('nome').value == '' && document.getElementById('idCategoriaScoreSearch').value == '') {
			camposErro += "Favor preencher pelo menos um parâmetro da Pesquisa.";
		}
	} else {
		if (document.getElementById('nome').value == '' && document.getElementById('idCategoriaScoreSearch').value == '' && document.getElementById('idPlataformas').value == '') {
			camposErro += "Favor preencher pelo menos um parâmetro da Pesquisa.";
		}
	}
	
	if (document.all('nome').value != "" && document.all('nome').value.length < 3) {
		camposErro += "Favor informar pelo menos 3 caracteres para o campo Nome.<br />";
	}
	
	if (camposErro != "") {
		generateContentError(camposErro);
		retorno = false;
	}
	
	return retorno;
}

function init(){
	
}

function validarForm() {
	var camposErro = "";
	var retorno = true;
	
	if (document.all('idCategoriaScore').value == "") {
		camposErro += "Deve selecionar uma categoria.<br />";
	} else {
		if (!verificarCheckBoxes()) {
			camposErro += "Favor selecionar ao menos um registro.<br />";
		}
	}
	
	if (camposErro != "") {
		generateContentError(camposErro);
		retorno = false;
	}
	
	return retorno;
}

function save() {
	if (validarForm()) {
		document.getElementById('categorizacaoAnaliseCreditoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/categorizacao/save.do";
		document.getElementById('categorizacaoAnaliseCreditoForm').submit();
	}
}

function desassociar() {
	if (validarFormDesassociar()) {
		document.getElementById('categorizacaoAnaliseCreditoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/categorizacao/desassociar.do";
		document.getElementById('categorizacaoAnaliseCreditoForm').submit();
	}
}

function validarFormDesassociar() {
	var camposErro = "";
	var retorno = true;

	if (!verificarCheckBoxes()) {
		camposErro += "Favor selecionar ao menos um registro.<br />";
	}
	
	if (camposErro != "") {
		generateContentError(camposErro);
		retorno = false;
	}
	
	return retorno;
}

function checkAll(button) {
	 function checkAll(button) {
	 	var form = $(button.form);
	 	var inputs = form.getInputs('checkbox');
	 	inputs.each(
		 	function (elem) {
		 		elem.checked = true;
	 		}
	 	);         
 	} 
}

function setAllCheckBoxes(FormName, FieldName, CheckValue) {
	
	FormName = "categorizacaoAnaliseCreditoForm";
	FieldName = "checkRecord";
	
	
	if (document.categorizacaoAnaliseCreditoForm("checkAll").checked == false) {
		CheckValue = false;
	} else {
		CheckValue = true;
	}
	
	if(!document.forms[FormName])
		return;
	var objCheckBoxes = document.forms[FormName].elements[FieldName];
	if(!objCheckBoxes)
		return;
	var countCheckBoxes = objCheckBoxes.length;
	if(!countCheckBoxes)
		objCheckBoxes.checked = CheckValue;
	else
		// set the check value for all check boxes
		for(var i = 0; i < countCheckBoxes; i++)
				objCheckBoxes[i].checked = CheckValue;
}

function verificarCheckBoxes() {
	
	FormName = "categorizacaoAnaliseCreditoForm";
	FieldName = "checkRecord";
	var isCheck = false;
	
	if(!document.forms[FormName])
		return;
	var objCheckBoxes = document.forms[FormName].elements[FieldName];
	if(!objCheckBoxes)
		return;
	var countCheckBoxes = objCheckBoxes.length;
	if(!countCheckBoxes) {
		
		if (objCheckBoxes.checked == true){
			isCheck = true;
		}
		
	} else {
		// set the check value for all check boxes
		for(var i = 0; i < countCheckBoxes; i++) {
			if (objCheckBoxes[i].checked == true){
				isCheck = true;
			}
		}
	}
	
	return isCheck;
}

/*function esconderPlataforma(tpPesquisa) {
	if (tpPesquisa == 'O' || tpPesquisa == 'F') {
		document.getElementById('idPlataformas').value = '';
		document.getElementById('contentPlataforma').style.display = "none";
	} else {
		document.getElementById('contentPlataforma').style.display = "inline";
	}
}*/

function begin() {
	document.getElementById('categorizacaoAnaliseCreditoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/categorizacao/CategorizacaoAnaliseCreditoAction.do";
	document.getElementById('categorizacaoAnaliseCreditoForm').submit();
}
