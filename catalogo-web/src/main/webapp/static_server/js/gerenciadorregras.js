var $jq = jQuery.noConflict();

$jq(document).ready(
	function() {
		$jq("#divSucess").prependTo("div [id='mensagem']");
		$jq("#divErrosMidle").prependTo("div [id='mensagem']");
		$jq("#mensagemLoadSearch").hide();
	}
);

$jq("#gerenciadorRegrasConfiguracaoTO tbody tr td a").live("click",function() {
	$jq(".conteudo_box_top_center").html("")
	var nomeServico =($jq(this).html())
	$jq(".conteudo_box_top_center").html("Configuracao  "   + nomeServico)
})

$jq("#tabelaDocumentos table td input").live("click",function() {
	$jq("#abaDocumentoChanged").attr("value","true");
})

function init(){}

function abrirAba(idAbaOrigem, idAbaDestino) {
	var ref;
	if(idAbaDestino == "abaFiltro1"){
		ref = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/gerenciadorregras/filtro1/GerenciadorRegrasConfiguracaoFiltro1Action.do";
	} else if(idAbaDestino == "abaFiltro2"){
		ref = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/gerenciadorregras/filtro2/GerenciadorRegrasConfiguracaoFiltro2Action.do";
	} else if(idAbaDestino == "abaDocumentos"){
		ref = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/gerenciadorregras/documentos/GerenciadorRegrasConfiguracaoDocumentosAction.do";
	}
	
	if(idAbaOrigem == "abaFiltro1") {
		var grc = $jq("#gerenciadorRegrasConfiguracaoTOJSONNew").attr("value");
		if(grc != null && grc != ""){
			if(confirm("Todas as informa\xe7\xf5s configuradas ser\xe3 perdidas, Deseja prosseguir sem gravar?")){
				document.location.href = ref;
			}
		} else {
			document.location.href = ref;
		}
	} else if(idAbaOrigem == "abaFiltro2") {
	var grc = $jq("#gerenciadorRegrasConfiguracaoTOJSONNew").attr("value");
		if(grc != null && grc != ""){
			if(confirm("Todas as informa\xe7\xf5s configuradas ser\xe3 perdidas, Deseja prosseguir sem gravar?")){
				document.location.href = ref;
			}
		} else {
			document.location.href = ref;
		}
	} else if(idAbaOrigem == "abaDocumentos") {
		var aDoc = $jq("#abaDocumentoChanged").attr("value");
		if(aDoc != ""){
			if(confirm("Todas as informa\xe7\xf5s configuradas ser\xe3 perdidas, Deseja prosseguir sem gravar?")){
				document.location.href = ref;
			}
		} else {
			document.location.href = ref;
		}
	}
}

function carregarSelects(gerenciadorRegrasConfiguracaoTO){
	$jq("#edicaoIndicadores").css("display","block");
	$jq("#gerenciadorRegrasConfiguracaoTOJSONNew").attr("value",JSON.stringify($jq(gerenciadorRegrasConfiguracaoTO)[0],null,null));
	$jq(gerenciadorRegrasConfiguracaoTO)[0].idIndicadorComercialJSONArray.each(
		function (e,i){
			idRegraPrioridadeAlta = $jq(gerenciadorRegrasConfiguracaoTO)[0].indicadorComercialRegraMapJSON[e].idRegraPrioridadeAlta;
			nmIndicadorComercial = $jq(gerenciadorRegrasConfiguracaoTO)[0].indicadorComercialRegraMapJSON[e].nmIndicadorComercial;
			expr = "$jq(\"select[id='"+nmIndicadorComercial+"']\")[0].value = "+idRegraPrioridadeAlta ;
			eval("("+expr+")");
		}
	);
	
}

function pesquisarIndicadoresDocumento(){
	var s = $jq("#idIndicadorComercial").attr("value");
	if(s != null && s != "" && s != 0){
		$jq("#mensagemLoadSearch").show();
		document.getElementById('actionForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/gerenciadorregras/documentos/pesquisar.do";
		document.getElementById('actionForm').submit();
	} else {
		$jq("#selectDocumentos").hide();
	}
}

function pesquisarIndicadores(idAbaDestino){
	$jq("#mensagemLoadSearch").show();
	
	if(idAbaDestino == "abaFiltro1"){
		document.getElementById('actionForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/gerenciadorregras/filtro1/pesquisar.do";
		document.getElementById('actionForm').submit();
	} else if(idAbaDestino == "abaFiltro2"){
		document.getElementById('actionForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/gerenciadorregras/filtro2/pesquisar.do";
		document.getElementById('actionForm').submit();		
	}
}

function salvarIndicadores(idAbaDestino){
	if(!validarCheckBoxes()) {
		$jq('#divSucess').css('display','none');
		generateContentErrorForm("Favor preencher os par&acirc;metros obrigat&oacute;rios.");
		document.getElementById('divErros').scrollIntoView();
	} else {
		grJSON = $jq.parseJSON($jq("#gerenciadorRegrasConfiguracaoTOJSONNew").attr("value"));
		
		$jq.parseJSON( $jq(grJSON)[0].idIndicadorComercialJSONArray).each(
			function(e,i) {
				nmIndicadorComercial = $jq(grJSON)[0].indicadorComercialRegraMapJSON[e].nmIndicadorComercial;
				idRegraPrioridadeAltaSelect = $jq("select[id='"+nmIndicadorComercial+"']")[0].value;
				dsRegraAltaSelect = $jq("select[id='"+nmIndicadorComercial+"'] > option[value='"+idRegraPrioridadeAltaSelect+"']")[0].innerText;
				$jq(grJSON)[0].indicadorComercialRegraMapJSON[e].idRegraPrioridadeAlta = idRegraPrioridadeAltaSelect;
				$jq(grJSON)[0].indicadorComercialRegraMapJSON[e].dsRegraAlta = dsRegraAltaSelect;
			}
		);
		$jq("#gerenciadorRegrasConfiguracaoTOJSONNew").attr("value",JSON.stringify(grJSON));

		if(idAbaDestino == "abaFiltro1"){
			document.getElementById('actionForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/gerenciadorregras/filtro1/salvar.do";
			document.getElementById('actionForm').submit();
		} else if(idAbaDestino == "abaFiltro2"){
			document.getElementById('actionForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/gerenciadorregras/filtro2/salvar.do";
			document.getElementById('actionForm').submit();		
		}
	}
}

function salvarIndicadoresDocumento(){
	if(validarSelecaoIndicador()){
		document.getElementById('actionForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/gerenciadorregras/documentos/salvar.do";
		document.getElementById('actionForm').submit();
	} else {
		$jq('#divSucess').css('display','none');
		generateContentErrorForm("Favor preencher os par&acirc;metros obrigat&oacute;rios.");
		document.getElementById('divErros').scrollIntoView();
	}
}

function validarCheckBoxes(){
	v = true;
	$jq("#edicaoIndicadores select").each(
		function(i,e) {
			if(e.value == 0)
				v = false;
		}
	);
	return v;
}

function validarSelecaoServico(){
	v = true;
	if($jq("#gerenciadorRegrasConfiguracaoTOJSONNew").attr("value") == ""){
		v = false;
	}
	return v;
}

function validarSelecaoIndicador(){
	v = true;
	id = $jq("#idIndicadorComercial").attr("value");
	if(id == 0){
		v = false;
	}
	return v;
}