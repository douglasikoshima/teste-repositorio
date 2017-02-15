var $jq = jQuery.noConflict();

$jq(document).ready(function(){
	$jq("#mensagemLoadSearch").hide();
	$jq("#idOfertaSAPList").multiselect({
		height: 300,
		minWidth: 500,
		checkAllText: "Todos",
		uncheckAllText: "Nenhum",
		noneSelectedText: '-- Selecione --',
		selectedText: "# selecionados",
		selectedList: 11
	}).multiselectfilter({
		label:"",
		placeholder: "Busca"
	});
	$jq("#idOrganizacaoVendasList").multiselect({
		height: 300,
		minWidth: 500,
		checkAllText: "Todos",
		uncheckAllText: "Nenhum",
		noneSelectedText: '-- Selecione --',
		selectedText: "# selecionados",
		selectedList: 8
	}).multiselectfilter({
		label:"",
		placeholder: "Busca"
	});
	
    $jq("#dtPeriodoVigencia").datepicker();
});

function search() {
	if(!validarCamposObrigatorios()){
		generateContentErrorForm("Favor preencher o(s) campo(s) obrigat&oacute;rio(s)");
	} else {
		$jq("#mensagemLoadSearch").show();
		$jq("#resultado_pesquisa").hide();
		document.getElementById("produtoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/produto/search.do";
		document.getElementById("produtoForm").submit();
	}
}

function checkPrecos() {
	setAllCheckBoxes("acaoForm", "idMatrizOfertaItemPrecoCheck", "", "checkAll");
}

function removePrecoList() {
	if(confirm("Deseja realmente excluir o(s) registro(s) exibido(s)? Os dados removidos n\xe3o poder\xe3o ser recuperados.")){
		document.getElementById("produtoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/produto/removePrecoList.do";
		document.getElementById("produtoForm").submit();
	}
}

function exportar() {
	document.getElementById("produtoForm").action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/matrizOferta/produto/exportar.do";
	document.getElementById("produtoForm").submit();
}

function validarCamposObrigatorios() {
	var retorno = true;
	
	if(document.produtoForm("cdProduto").value == ""){
		retorno = false;
	}
	if(document.produtoForm("idCanalAtendimento").value == ""){
		retorno = false;
	}
	if(!($jq("'.checkElemOfertaSAP' input[aria-selected=true]").size() > 0
			&& $jq("'.checkElemOrganizacaoVendas' input[aria-selected=true]").size() > 0
	)){
		retorno = false;
	}
	return retorno;
}