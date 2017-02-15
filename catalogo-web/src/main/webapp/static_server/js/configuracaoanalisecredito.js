var $jq = jQuery.noConflict();
var blAlterado = false;
$jq('.sortable a').live("click",function() {
    acao($jq(this).attr('href'));
    return false;
});
$jq('.pagelinks a').live("click", function() {
    acao($jq(this).attr('href'));
    return false;
});
$jq(function() {
	$jq( "#accordion" ).accordion({
		autoHeight: false,
		navigation: true,
		collapsible: true,
		active: false
	});
});
$jq(function() {
	$jq( "#sortable1, #sortable2" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, cursor: 'pointer' 
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable1, #sortable3" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable1, #sortable4" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable1, #sortable5" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});

$jq(function() {
	$jq( "#sortable1, #sortable6" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable1, #sortable7" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable1, #sortable8" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable1, #sortable9" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable11, #sortable12" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, cursor: 'pointer' 
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable11, #sortable13" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable11, #sortable14" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable11, #sortable15" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable11, #sortable16" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable11, #sortable17" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable11, #sortable18" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable11, #sortable19" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable21, #sortable22" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, cursor: 'pointer' 
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable21, #sortable23" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable21, #sortable24" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable21, #sortable25" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable21, #sortable26" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable21, #sortable27" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable21, #sortable28" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable21, #sortable29" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable31, #sortable32" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, cursor: 'pointer' 
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable31, #sortable33" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable31, #sortable34" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable31, #sortable35" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable31, #sortable36" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable31, #sortable37" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable31, #sortable38" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable31, #sortable39" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable41, #sortable42" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, cursor: 'pointer' 
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable41, #sortable43" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable41, #sortable44" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});

$jq(function() {
	$jq( "#sortable41, #sortable45" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable41, #sortable46" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable41, #sortable47" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable41, #sortable48" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#sortable41, #sortable49" ).sortable({
		connectWith: ".connectedSortable"
		, placeholder: "ui-state-highlight"
		, items: "li:not(.ui-state-disabled)"
	}).disableSelection();
});
$jq(function() {
	$jq( "#tabs" ).tabs();
});

function cancelarAnaliseCredito(){
	document.location.href = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/search.do";
}

function cancelar(){
	if (confirm("Deseja voltar para a tela de Pesquisa Análise de Crédito? Ao voltar todas as informações que não foram salvas serão perdidas.")) {
		document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/search.do";
		document.getElementById('acaoForm').submit();
	}
}

/*
function save() {
	
	var tpClassificacao = jQuery("#idCategoriaScore option:selected").attr('id');
	
	saveUserOptions();
	saveUserOptionsRemove();
	
	$jq("#listaItemCategoriaScore option").each(function() {
	    $jq('#itensPesquisados').append('<option value="' + $(this).value +'">' + $(this).value + '</option>');
	});
	
	
	if (validarForm()) {
		jQuery('#tpClassificacao').val(tpClassificacao);
		$jq("#configuracoes option").prop('selected', true);
		$jq("#configuracoesRemove option").prop('selected', true);
		$jq("#itensPesquisados option").prop('selected', true);
		document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/save.do";
		document.getElementById('acaoForm').submit();
	}

}
*/

function save2() {
	var tpClassificacao = jQuery("#idCategoriaScore option:selected").attr('id');
	
	saveUserOptions2();
	saveUserOptionsRemove();
	
	$jq("#listaItemCategoriaScore option").each(function() {
	    $jq('#itensPesquisados').append('<option value="' + $(this).value +'">' + $(this).value + '</option>');
	});				
	
	if (validarForm()) {
		jQuery('#tpClassificacao').val(tpClassificacao);
		$jq("#configuracoes option").prop('selected', true);
		$jq("#configuracoesRemove option").prop('selected', true);
		$jq("#itensPesquisados option").prop('selected', true);
		
		jQuery.ajax({
				type: "POST",
				url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/save2.do',
				data: jQuery("#acaoForm").serialize(),
				success: function(ret) {
					blAlterado = false;
					jQuery('#idCabecalhoAnaliseCredito').val($jq.parseJSON(ret)['idCabecalhoAnaliseCredito']);
					jQuery('#inDisponivel').val($jq.parseJSON(ret)['inDisponivel']);
					alert($jq.parseJSON(ret)['mensagem']);
				}
			});
		
	}

}			

function validarForm() {
	var camposErro = "";
	var retorno = true;
	
	if (document.acaoForm("nome").value == "" ) {
		if (camposErro != "") {
			camposErro += ", ";	
		}
		camposErro += "Nome";
	}
	
	if (document.acaoForm("idCanalAtendimento").value == "" ) {
		if (camposErro != "") {
			camposErro += ", ";	
		}
		camposErro += "Canal Atendimento";
	}

	if (document.acaoForm("idCategoriaScore").value == "" ) {
		if (camposErro != "") {
			camposErro += ", ";	
		}
		camposErro += "Categoria";
	}
	
	if (camposErro != "") {
		generateContentError("Favor preencher os campos: " + camposErro);
		retorno = false;
	}
	
	return retorno;
}

getUnableSortColumns = function() {
	var a = [];
	var qtColunas = $jq("#example thead tr:first th").length;
	if (qtColunas > 0) {
		for (var i = 0; ; i++) {
			a.push(i+3);
			if (i == qtColunas - 2) {
				break;
			}
		}
	} 
	return a;
}

function pesquisar() {

	if (validarPesquisa() && validarForm()) {
			showSpinner();
			/*
			var arReg = [];
			$jq('#divRegionaisSearch input').each(function(i,e){
				if(e.checked) {
					var regional = new Object();
					regional.idRegional = e.value;
					regional.nmRegional = e.name;
					arReg.push(regional);
				}
			})
			var regionaisJson = new Object();
			regionaisJson.listaRegionais = arReg;
			var regionaisJsonString = JSON.stringify(regionaisJson);
			*/
			jQuery.ajax({
				type: "POST",
				url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/searchProduto.do',
				data: {
					"nomeSearch" : jQuery('#nomeSearch').val(),
					"precoDeSearch" : jQuery('#precoDeSearch').val(),
					"precoAteSearch" : jQuery('#precoAteSearch').val(),
					"idCategoriaSearch" : jQuery('#idCategoriaScore').val(),
					"tpClassificacao" : jQuery('#tpClassificacao').val(),
					"idCategoriaSearch" : jQuery('#idCategoriaScore').val(),
					"idCanalAtendimento" : jQuery('#idCanalAtendimento').val(),
					"idCabecalhoAnaliseCredito" : jQuery('#idCabecalhoAnaliseCredito').val(),
					"idCabecalhoAnaliseCreditoCopia" : jQuery('#idCabecalhoAnaliseCreditoCopia').val(),
					"idAnaliseCreditoSearch" : jQuery('#idAnaliseCreditoSearch').val(),
					"regionaisJsonSearch" : getRegionaisJSONString()
				},
				success: function(ret) {
					mostrarDiv('#resultado_pesquisa');
					hideSpinner();
					$jq("#resultado_pesquisa").html(ret);
					$jq("input[type=checkbox]").change(function() {
					   blAlterado = true;
					});
					mostrarDiv('#divBotoes');
					$jq("#example").dataTablesCaller({
						unableSort: getUnableSortColumns(),
						initialColumnSort : 0,
						paginationType: 'full_numbers'
					});
					$jq("#example").bind('page', function () {
					   if (blAlterado && validarForm() && confirm('As alterações realizadas na configuração não foram salvas! Deseja salvar?'))
						   save2();
					} );
					$jq("input.selectAll").click(function(){
						$jq(this).parents('tr').find(':checkbox').prop('checked', this.checked);
					});
				},
				error: function(errorData) {
					hideSpinner();
				}
			});
	} 
	
}

validarPesquisa = function () {
	if (jQuery('#nomeSearch').val() != "" || jQuery('#precoDeSearch').val() != "" || jQuery('#precoAteSearch').val() != "" || jQuery('#idAnaliseCreditoSearch').val() != "") {
		if (jQuery('#precoDeSearch').val() != "" && jQuery('#precoAteSearch').val() != '') {
			var de = parseFloat(jQuery('#precoDeSearch').val());
			var ate = parseFloat(jQuery('#precoAteSearch').val());
			if (ate < de) {
				alert("O intervalo de valores da pesquisa foi informado incorretamente.");
				return false;
			}
		}
		//valida checkbox regional
		var regChecked = false;
		$jq('#divRegionaisSearch input').each(function(i,e){
			if(e.checked) {
				regChecked = true;
			}
		})
		if(!regChecked){
			alert("Favor checar pelo menos uma Regional");
			return false;
		}
	} else {
		alert("É obrigatório o preenchimento de pelo menos um parâmetro de pesquisa.");
		return false;
	}
	
	return true;
}

function mostrarDiv(id) {
	$jq(id).show();
	var i = jQuery("#idCategoriaScore option:selected").attr('id');
	jQuery('#tpClassificacao').val(i);
}

function limparPesquisa() {
	$jq("#resultado_pesquisa").empty();
	$jq("#nomeSearch").val('');
	$jq("#precoDeSearch").val('');
	$jq("#precoAteSearch").val('');
	$jq("#idAnaliseCreditoSearch option[value='']").attr("selected","selected");
}

showCategoria = function() {
	var dsc = '';
	var i = jQuery("#idCategoriaScore option:selected").attr('id');
	if (i == '1') {
		dsc = 'Plano';
	} else if (i == '2') {
		dsc = 'Serviço';
	} else {
		dsc = 'Oferta Complementar';
	}
	jQuery('#dscCategoriaName').html("<span class='lblLogin'>" + jQuery("#idCategoriaScore option:selected").attr('title') + " : " + dsc + " </span>");
} 

saveUserOptions = function() {

	$jq('#configuracoes option').remove();

	var tpClassificacao = jQuery("#idCategoriaScore option:selected").attr('id');
	
	$jq("input[type=checkbox]", Application.dataTables[Application.dataTables.length-1].fnGetNodes()).each(function () { 
		if ($(this).id != '' && $(this).name != '') {
		    if ( !(tpClassificacao == 2 || tpClassificacao == 4) && $(this).checked) {
		    	$jq("#configuracoes").append('<option value="' + $(this).id + '|' + $(this).name + '">' + $(this).id + '|' + $(this).name + '</option>');
				} else if ((tpClassificacao == 2 || tpClassificacao == 4) && !$(this).checked) {   
					$jq("#configuracoes").append('<option value="' + $(this).id + '|' + $(this).name + '">' + $(this).id + '|' + $(this).name + '</option>');
				}
		    }
	});      	
}

saveUserOptions2 = function() {

	$jq('#configuracoes option').remove();

	var tpClassificacao = jQuery("#idCategoriaScore option:selected").attr('id');
	
	$jq("#example input[type=checkbox]").each(function () { 
		if ($(this).id != '' && $(this).name != '') {
		    if (tpClassificacao != 2 && $(this).checked) {
		    	$jq("#configuracoes").append('<option value="' + $(this).id + '|' + $(this).name + '">' + $(this).id + '|' + $(this).name + '</option>');
				} else if (tpClassificacao == 2 && !$(this).checked) {   
					$jq("#configuracoes").append('<option value="' + $(this).id + '|' + $(this).name + '">' + $(this).id + '|' + $(this).name + '</option>')
				}
		    }
	});
}

saveUserOptionsRemove = function() {

    $jq('#configuracoesRemove option').remove();
	var tpCanalAtendimento = jQuery("#idCanalAtendimento option:selected").attr('id');
	var tpClassificacao = jQuery("#idCategoriaScore option:selected").attr('id');
	                             
    if(tpCanalAtendimento == 1 || tpCanalAtendimento == 2 || tpCanalAtendimento == 23611 || tpCanalAtendimento == 26 || tpCanalAtendimento == 1262){
	  if (tpClassificacao != 4){		  				
	    $jq("input[type=checkbox]", Application.dataTables[Application.dataTables.length-1].fnGetNodes()).each(function () { 
		if ($(this).id != '' && $(this).name != '') {
		    if (!$(this).checked) {
		    	$jq("#configuracoesRemove").append('<option value="' + $(this).id + '|' + $(this).name + '">' + $(this).id + '|' + $(this).name + '</option>');
		    	}
		      }
	  });
    }
  }			
}

function controlarListaCategoria() {
	limparPesquisa();
	
	// Regional Ã© Habilitado somente para os Canais Loja Autorizada, Call Center, LOJAS, Dealers - Revenda e TELEVENDAS.
    // Para ClassificaÃ§Ã£o SERVIÃ‡O FIXA nÃ£o habilita a regional, mesmo sendo os canais habilitados. 
    //Inicio  
	var tpClassificacao = jQuery("#idCategoriaScore option:selected").attr('id');
	var tpCanalAtendimento = jQuery("#idCanalAtendimento option:selected").attr('id');

    if(tpCanalAtendimento == 1 || tpCanalAtendimento == 2 || tpCanalAtendimento == 23611 || 
       tpCanalAtendimento == 26 || tpCanalAtendimento == 1262){
	  if (tpClassificacao != 4){
	    $jq('#regionalCheck').show();
	  }else{
	      $jq('#regionalCheck').hide();
	  }
	}else{
	  $jq('#regionalCheck').hide();
	}
	//Fim 
	
	if (jQuery('#idCategoriaScore').val() != '') {
		mostrarDiv('#pesquisa');
		showCategoria();
	} else {
		$jq('#pesquisa').hide();
	}
}

function controlarRegional(){
   $jq('#regionalCheck').hide();
   $jq("#idCategoriaScore option[value='']").attr("selected","selected");
}

function create() {
	showSpinner();
	document.getElementById('configuracaoAnaliseCreditoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/create.do";
	document.getElementById('configuracaoAnaliseCreditoForm').submit();
}

function remover(id, canal, status, nome){
	if (canal != '2') {
		if(confirm("Tem certeza que deseja excluir a Análise de Crédito \""+nome+"\" ?")){
			document.getElementById("idCabecalhoAnaliseCredito").value = id;
			document.getElementById('configuracaoAnaliseCreditoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/remove.do";
			document.getElementById('configuracaoAnaliseCreditoForm').submit();
		}
	} else {
		if (status != 'S') {
			if(confirm("Tem certeza que deseja excluir a Análise de Crédito \""+nome+"\" ?")){
				document.getElementById("idCabecalhoAnaliseCredito").value = id;
				document.getElementById('configuracaoAnaliseCreditoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/remove.do";
				document.getElementById('configuracaoAnaliseCreditoForm').submit();
			}
		} else {
			generateContentError("Não é possível excluir configuração ativa de Lojas!");
		}
	}
}

function criarCopia(id) {
	if(confirm("Tem certeza que deseja Copiar essa Análise de Crédito?")){
		document.getElementById("idCabecalhoAnaliseCredito").value = id;
		document.getElementById('configuracaoAnaliseCreditoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/copiar.do";
		document.getElementById('configuracaoAnaliseCreditoForm').submit();
	}
}

function ativarDesativar(id, canal, status, nome) {
	
	var msg = "";

	if(status == 'N') {
		msg = "Tem certeza que deseja Ativar a Análise de Crédito \""+nome+"\" ?";
	} else if(status == 'S') {
		msg = "Tem certeza que deseja Desativar a Análise de Crédito \""+nome+"\" ?";
	} else {
		msg = "Tem certeza que deseja Ativar/Desativar a Análise de Crédito \""+nome+"\" ?";
	}
	if (canal != '2') {
		if(confirm(msg)){
			document.getElementById("idCabecalhoAnaliseCredito").value = id;
			document.getElementById('configuracaoAnaliseCreditoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/ativarDesativar.do?status=" + status;
			document.getElementById('configuracaoAnaliseCreditoForm').submit();
		}
	} else {
		if (status != 'S') {
			if(confirm(msg)){
				document.getElementById("idCabecalhoAnaliseCredito").value = id;
				document.getElementById('configuracaoAnaliseCreditoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/ativarDesativar.do?status=" + status;
			document.getElementById('configuracaoAnaliseCreditoForm').submit();
			}
		} else {
			generateContentError("Não é possível desativar esta configuração!");
		}
	}
}

function edit(id) {
	document.getElementById("idCabecalhoAnaliseCredito").value = id;
	document.getElementById('configuracaoAnaliseCreditoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/edit.do";
	document.getElementById('configuracaoAnaliseCreditoForm').submit();
}

function acao(acao){
	/*
    var local = acao.split("?")[0].substr(acao.lastIndexOf("/")+1);
	var local = acao.split("?")[0].substr(acao.lastIndexOf(",")+1);

    var caminho = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/order.do?" + acao.split("?")[1];
	var caminho = acao.split("?")[0].substr(acao.lastIndexOf("/")+1);
	caminho = caminho.slice(0,caminho.lastIndexOf(","))+"?"+acao.split("?")[1];
	*/
	var caminho = acao;
	/*
    $jq.ajax({
        type: "GET",
        data: {
        	"element}' : local
        },
        url: caminho,
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById(local).innerHTML = data;
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
    */
    var salvarConfigScore = false;
    if(validarForm() && confirm('As altera\xE7\xF5es realizadas na configura\xE7\xE3o n\xE3o foram salvas! Deseja salvar?')){
		montarFormAtributosParaGravacao();
		salvarConfigScore = true;
    }
	jQuery.ajax({
		type: "GET",
		url: acao,
		data: {
			"nomeSearch" : jQuery('#nomeSearch').val(),
			"precoDeSearch" : jQuery('#precoDeSearch').val(),
			"precoAteSearch" : jQuery('#precoAteSearch').val(),
			"idCategoriaSearch" : jQuery('#idCategoriaScore').val(),
			"tpClassificacao" : jQuery('#tpClassificacao').val(),
			"idCategoriaSearch" : jQuery('#idCategoriaScore').val(),
			"idCanalAtendimento" : jQuery('#idCanalAtendimento').val(),
			"idCabecalhoAnaliseCredito" : jQuery('#idCabecalhoAnaliseCredito').val(),
			"idCabecalhoAnaliseCreditoCopia" : jQuery('#idCabecalhoAnaliseCreditoCopia').val(),
			"idAnaliseCreditoSearch" : jQuery('#idAnaliseCreditoSearch').val(),
			"idCabecalhoAnaliseCredito" : jQuery('#idCabecalhoAnaliseCredito').val(),
			"nome" : jQuery('#nome').val(),
			"inDisponivel" : jQuery('#inDisponivel').val(),
			"configuracoesJson" : jQuery('#configuracoesJson').val(),
			"regionaisJsonSearch" : getRegionaisJSONString(),
			"salvarConfigScore" : salvarConfigScore
		},
		success: function(data) {
			mostrarDiv('#resultado_pesquisa');
			hideSpinner();
			$jq("#resultado_pesquisa").html(data);
		},
		error: function(errorData) {
			hideSpinner();
		}
	});
}

function getRegionaisJSONString(){
	var arReg = [];
	var regionaisJson = new Object();
	if(eCanalConfiguravelPorRegional()){
		$jq('#divRegionaisSearch input').each(function(i,e){
			if(e.checked) {
				var regional = new Object();
				regional.idRegional = e.value;
				regional.nmRegional = e.name;
				arReg.push(regional);
			}
		})
	} else {
		var regional = new Object();
		regional.idRegional = 0;
		regional.nmRegional = "";
		arReg.push(regional);
	}
	regionaisJson.listaRegionais = arReg;
	return JSON.stringify(regionaisJson);
}

function eCanalConfiguravelPorRegional(){
	var cnsCfgPorRegional = $jq.parseJSON($jq("#canaisConfiguraveisPorRegionalJSON").attr("value"));
	var idCanalAtndForm = parseInt($jq("#idCanalAtendimento").val(),10);
	return ($jq.inArray(idCanalAtndForm, cnsCfgPorRegional) != -1);
}

function montarFormAtributosParaGravacao(){
	var tpClassificacao = jQuery("#idCategoriaScore option:selected").attr('id');
	var keysSelecionadas = new Array();
	var keysPesquisadas = new Array();
	var jsonObj = new Object();
	if($jq("#tpClassificacao").attr("value") == 2){
		$jq(".clConfigScore:not([checked])").each(function(i,e){
			keysSelecionadas.push(e.id);
		});
	} else {
		$jq(".clConfigScore[checked]").each(function(i,e){
			keysSelecionadas.push(e.id);
		});
	}
	jsonObj.cfgScoreKeysAGravar = keysSelecionadas;
	$jq(".clConfigScore").each(function(i,e){
		keysPesquisadas.push(e.id);
	});
	jsonObj.cfgScoreKeysARemover = keysPesquisadas;
	$jq("#configuracoesJson").attr("value",JSON.stringify(jsonObj));

	$jq('#tpClassificacao').val(tpClassificacao);
	$jq("#configuracoes option").prop('selected', true);
	$jq("#configuracoesRemove option").prop('selected', true);
	$jq("#itensPesquisados option").prop('selected', true);
}

function save(){
	if (validarForm()) {
		showSpinner();
		montarFormAtributosParaGravacao();
		document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analisecredito/configuracao/save.do";
		document.getElementById('acaoForm').submit();
	}
}
	
function checkAll(obj) {
	$jq(obj).parent().parent().find(".clConfigScore").each(
		function(i,elem){
			if(obj.checked){
				elem.checked = true;
			} else {
				elem.checked = false;
			}
	});
}

function atualizarSelecionarTodos(){
	$jq(".clConfigScoreCheckAll").each(
		function(i,elem1){
			var linhaChecada = true;
			var j2 = "";
			$jq(elem1).parent().parent().find(".clConfigScore").each(
				function(j,elem2){
					if(!elem2.checked){
						linhaChecada = false;
					}
			});
			elem1.checked = linhaChecada;
	});
}