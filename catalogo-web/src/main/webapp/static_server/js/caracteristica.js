var $jq = jQuery.noConflict();

function create() {
	document.getElementById('caracteristicaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/create.do#contentForm";
	document.getElementById('caracteristicaForm').submit();
}

function editar(id){
	document.caracteristicaForm('idCaracteristica').value = id;
	document.getElementById('caracteristicaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/edit.do#contentForm";
	document.getElementById('caracteristicaForm').submit();
}

function clearPage() {
	document.location.href = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/CaracteristicaAction.do";
}
	
function search() {
	if (validarFormSearch()) {
		document.getElementById('caracteristicaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/search.do";
		document.getElementById('caracteristicaForm').submit();
	}
}

function validarFormSearch() {
	var camposErro = "";
	var retorno = true;
	
	if (
		(document.caracteristicaForm('inDisponivelSearch')[0].checked == false 
			&& document.caracteristicaForm('inDisponivelSearch')[1].checked == false)
			&& (document.caracteristicaForm('inSimuladorSearch')[0].checked == false 
			&& document.caracteristicaForm('inSimuladorSearch')[1].checked == false)
			&& (document.caracteristicaForm('indComparavelSearch')[0].checked == false 
			&& document.caracteristicaForm('indComparavelSearch')[1].checked == false)
			&& (document.caracteristicaForm('indExibivelSearch')[0].checked == false 
			&& document.caracteristicaForm('indExibivelSearch')[1].checked == false)
			&& document.caracteristicaForm('idGrupoCaracteristicaSearch').value == ""
			&& document.caracteristicaForm('nmCaracteristicaSearch').value == ""
		) {
		camposErro += "Favor preencher pelo menos um parâmetro da Pesquisa.<br />";
	}
	
	if (document.caracteristicaForm('nmCaracteristicaSearch').value != "" && document.caracteristicaForm('nmCaracteristicaSearch').value.length < 3) {
		camposErro += "Favor informar pelo menos 3 caracteres para o campo Característica.<br />";
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
	
	if (document.caracteristicaForm('nmCaracteristicaCadastro').value == '') {
		camposErro += "Nome da Característica";
	}
	
	if (document.caracteristicaForm('inDisponivelCadastro')[0].checked == false && document.caracteristicaForm('inDisponivelCadastro')[1].checked == false) {
		if (camposErro != "") {
			camposErro += ", ";	
		}
		camposErro += "Disponível";
	}
	
	if (document.caracteristicaForm('inSimuladorCadastro')[0].checked == false && document.caracteristicaForm('inSimuladorCadastro')[1].checked == false) {
		if (camposErro != "") {
			camposErro += ", ";	
		}
		camposErro += "Filtro";
	}
	
	if (camposErro != "") {
		generateContentErrorForm("Favor preencher os campos: " + camposErro);
		retorno = false;
	}
	
	return retorno;
}

function cancel() {
	document.getElementById('caracteristicaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/search.do";
	document.getElementById('caracteristicaForm').submit();
}

function save() {
	if (validarForm()) {
		document.getElementById('caracteristicaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/save.do";
		document.getElementById('caracteristicaForm').submit();
	} 
}

function remove(id) {
	if (confirm("Tem certeza que deseja excluir essa Característica?")) {
		document.caracteristicaForm('idCaracteristica').value = id;
		document.getElementById('caracteristicaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/remove.do";
		document.getElementById('caracteristicaForm').submit();
	}
}

function abrirPopUpValores(href){ 
	if (document.getElementById('idCaracteristica').value == "") {
		if (confirm("É necessário gravar a característica, deseja gravar agora?")) {
			saveCaracteristicaByValores();
		}
	} else {
		abrirPopup1(href);
	}
	return false;
}

function saveCaracteristicaByValores() {
	if (validarForm()) {
		document.getElementById('caracteristicaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/caracteristica/saveCaracteristicaByValores.do";
		document.getElementById('caracteristicaForm').submit();
		return true;
	} else {
		return false;
	}
}