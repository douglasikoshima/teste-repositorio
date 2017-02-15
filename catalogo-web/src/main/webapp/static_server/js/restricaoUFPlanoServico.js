var $jq = jQuery.noConflict();
			
$jq(document).ready(function() {
	$jq("#planoTable").dataTablesCaller({
		unableSort: [3],
		itensPerPage: 20,
		paginationType: 'full_numbers'
	});
	$jq(".selectJqPlugin").multiselect({
		noneSelectedText: '-- Selecione --',
		header: "",
		height:"100px",
		selectedList: 4
	}).multiselectfilter({
		label:"",
		placeholder: "Busca"
	});
});

function validarForm() {
	if ( $jq.trim($jq("#txtCodigo").val()) == "" && $jq.trim($jq("#txtNome").val()) == "" ) {
		generateContentError("É obrigatório informar o(s) campo(s) Nome Técnico ou Nome Comercial.");
		
		return false;					
	} else {
		if ( $jq.trim($jq("#txtCodigo").val()) != "" && $jq.trim($jq("#txtCodigo").val()).length < 3 ) {
			generateContentError("É obrigatório informar, no mínimo, 3 caracteres no campo Nome Técnico.");
			
			return false;
		}
		
		if ( $jq.trim($jq("#txtNome").val()) != "" && $jq.trim($jq("#txtNome").val()).length < 3 ) {
			generateContentError("É obrigatório informar, no mínimo, 3 caracteres no campo Nome Comercial.");
			
			return false;
		}
		
		return true;
	}
}

function search() {
	if (validarForm()) {
		document.getElementById('restricaoPlanoServicoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/restricaouf/search.do";
		document.getElementById('restricaoPlanoServicoForm').submit();
	}
}

function begin() {
	document.getElementById('restricaoPlanoServicoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/restricaouf/RestricaoUFPlanoServicoAction.do";
	document.getElementById('restricaoPlanoServicoForm').submit();
}

function save() {

	$jq('#restricoesUF > option').remove();
	$jq('#semRestricoesUF > option').remove();
	
	$jq(".ui-multiselect-menu input[type=checkbox]").each(function (index, element) {
		if (element.checked) {
			$jq("#restricoesUF").append('<option value="' + element.name.split('_')[1] + '|' + element.value + '">' + element.name.split('_')[1] + '|' + element.value + '</option>');
		} else {
			$jq("#semRestricoesUF").append('<option value="' + element.name.split('_')[1] + '">' + element.name.split('_')[1] + '</option>');
		}
	});
	
	$jq("#restricoesUF > option").each(function (index, element) {
		element.selected = true;
	});
	
	$jq("#semRestricoesUF > option").each(function (index, element) {
		element.selected = true;
	});

	document.getElementById('restricaoPlanoServicoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/restricaouf/save.do";
	document.getElementById('restricaoPlanoServicoForm').submit();
	
}