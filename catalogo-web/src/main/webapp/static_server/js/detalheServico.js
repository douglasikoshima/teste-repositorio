var $jq = jQuery.noConflict();

function msgDisponibilidade(obj) {
	var confirma = confirm("Todas as informações configuradas para as politícas serão perdidas!");
	if (confirma) {
		document.getElementById('alterarPoliticas').value = 'S';
	} else {
		document.getElementById('alterarPoliticas').value = 'N';
	}
}

function preencher(obj) {
	var value = obj.value;
	$jq.ajax({
		type: "GET",
		url: "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/detalheservicofixa/listaCategoriaServico.do",
		data: {"idFamiliaForm" : value},
		context: document.body,
		success: function(data){
			document.getElementById('categoriaList').innerHTML = data;
		},
		error: function(data, error){
			alert(data.status);
			alert(error);
		}
	});
}

$jq(function() {
	$jq("#grupos").accordion({
		autoHeight: false,
		navigation: true,
		collapsible: true,
	
		active: false
	});	 
	$jq('#detail').load(function () {
        if ($jq("#detail > iframe").size() == 0) {
            $jq('#detail').append($('.popupIF'));
        }
        this.style.height = this.contentWindow.document.body.offsetHeight + 'px';
        this.style.width = '100%';
	});	
});

$jq(function() {
	$jq( "#dialog" ).dialog({
		autoOpen: false,
		 width: 450,
		 height: 300,
		show: {
			effect: "blind",
			duration: 1000
			
		}
	});
	$jq( "#openerAtributosPolitica" ).click(function() {
		$jq( "#dialog" ).dialog( "open" );
	});
});

function init(){
	if(document.getElementById('alterarServicoSearch').value == "nao"){
		disableAll();
	}
	if (document.getElementById('idCategoriaForm').value != "") {
		document.getElementById('idCategoria').value = document.getElementById('idCategoriaForm').value;
	}
	if (document.getElementById('idFamiliaForm').value != "") {
		document.getElementById('idFamilia').value = document.getElementById('idFamiliaForm').value;
	}
}

function disableAll(){
	$jq("#nmComercialCatalogo").attr("disabled",true);			
	$jq("#inPreFactibilidade").attr("disabled",true);
	$jq("#inPosFactibilidade").attr("disabled",true);
	$jq("#cdSiscom").attr("disabled",true);
	$jq("#inAreaConcorrenciaDesconto").attr("disabled",true);
	$jq("#inPlanoMinutoDesconto").attr("disabled",true);
	$jq("#inAreaConcorrenciaFinanciamento").attr("disabled",true);
	$jq("#inPlanoMinutoFinanciamento").attr("disabled",true);
	$jq("#botao_gravar_form").hide();
	
	document.getElementById("idFamilia").disabled=true;
	document.getElementById("idCategoria").disabled=true;
}

function search() {		
	document.getElementById('detalheServicoFixaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/detalheservicofixa/search.do";
	document.getElementById('detalheServicoFixaForm').submit();			
}

function save() {
	if(validarForm()){
		$jq("#inDisponivelDetalheServico").attr("disabled",false);
		document.getElementById("idCategoria").disabled=false;
		document.getElementById('idCategoriaForm').value = document.getElementById('idCategoria').value;
				
		document.getElementById('detalheServicoFixaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/detalheservicofixa/save.do";
		document.getElementById('detalheServicoFixaForm').submit();				
	} else {
		generateContentErrorForm("N&atilde;o foi poss&iacute;vel alterar o Servi&ccedil;o. Todos os campos obrigat&oacute;rios devem ser preenchidos.");
	}
}

function validarForm() {
	if (document.getElementById("nmComercialCatalogo").value == ""
		|| document.getElementById("idCategoria").value == "") {
		return false;
	}
	return true;
}