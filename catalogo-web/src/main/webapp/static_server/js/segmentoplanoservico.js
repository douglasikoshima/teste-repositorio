var $jq = jQuery.noConflict();

$jq(document).ready(
	function() {
		$jq("#divSucess").prependTo("div [id='message']");
		$jq("#divErrosMidle").prependTo("div [id='message']");
		$jq("#messageLoading").hide();
		hideSpinner();
	}
);

function init(){}

function openPageFixa(obj) {
	if(obj.id == "idmovel"){
		document.getElementById('segmentoPlanoServicoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/configuracaosubsegmento/openPageFixa.do";
		document.getElementById('segmentoPlanoServicoForm').submit();
	}
}

function openPageMovel(obj) {
	if(obj.id == "idfixa"){
		document.getElementById('segmentoPlanoServicoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/configuracaosubsegmento/openPageMovel.do";
		document.getElementById('segmentoPlanoServicoForm').submit();
	}
}

function searchPlano(form){
	if(validateFormPlanoSearch(form)){
		document.getElementById('segmentoPlanoServicoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/configuracaosubsegmento/searchPlano.do";
		document.getElementById('segmentoPlanoServicoForm').submit();
		showSpinner();
	} else {
		$jq('#divSucess').css('display','none');
		generateContentErrorForm("Favor preencher os par&acirc;metros obrigat&oacute;rios.");
	}
}

function savePlanoSegmento(form){
	if(validateFormPlanoSave(form)){
		document.getElementById('segmentoPlanoServicoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/configuracaosubsegmento/savePlanoSegmento.do";
		document.getElementById('segmentoPlanoServicoForm').submit();
		showSpinner();
	} else {
		$jq('#divSucess').css('display','none');
		generateContentErrorForm("Favor preencher os par&acirc;metros obrigat&oacute;rios.");
	}
}

function disassociatePlanoSegmento(form){
	document.getElementById('segmentoPlanoServicoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/configuracaosubsegmento/disassociatePlanoSegmento.do";
	document.getElementById('segmentoPlanoServicoForm').submit();
	showSpinner();
}

function searchServicoFixa(form){
	if(validateFormServicoSearch(form)){
		document.getElementById('segmentoPlanoServicoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/configuracaosubsegmento/searchServicoFixa.do";
		document.getElementById('segmentoPlanoServicoForm').submit();
		showSpinner();
	} else {
		$jq('#divSucess').css('display','none');
		generateContentErrorForm("Favor preencher os par&acirc;metros obrigat&oacute;rios.");
	}
}

function saveServicoSegmento(form){
	if(validateFormServicoSave(form)){
		document.getElementById('segmentoPlanoServicoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/configuracaosubsegmento/saveServicoSegmento.do";
		document.getElementById('segmentoPlanoServicoForm').submit();
		showSpinner();
	} else {
		$jq('#divSucess').css('display','none');
		generateContentErrorForm("Favor preencher os par&acirc;metros obrigat&oacute;rios.");
	}
}

function disassociateServicoSegmento(form){
	document.getElementById('segmentoPlanoServicoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/configuracaosubsegmento/disassociateServicoSegmento.do";
	document.getElementById('segmentoPlanoServicoForm').submit();
	showSpinner();
}
			
function validateFormPlanoSearch(form) {
	result = true;
	if(document.segmentoPlanoServicoForm("idPlataforma").value == 0){
		result = false;
	}
	return result;
}

function validateFormPlanoSave(form) {
	result = true;
	if(document.segmentoPlanoServicoForm("idSegmentoNew").value == 0){
		result = false;
	}
	return result;
}

function validateFormServicoSearch(form) {
	result = true;
	if(document.segmentoPlanoServicoForm("idTipoServico").value == 0){
		result = false;
	}
	return result;
}

function validateFormServicoSave(form) {
	result = true;
	if(document.segmentoPlanoServicoForm("idSegmentoNew").value == 0){
		result = false;
	}
	return result;
}

function showCfgTable() {
	$jq('#enabledCfgSegmento').attr('value',true);
	$jq("#tableCfgSegmento").show();
	$jq("#tableSegmento").hide();
}

function hideCfgTable() {
	$jq('#enabledCfgSegmento').attr('value',false);
	$jq("#tableCfgSegmento").hide();
	$jq("#tableSegmento").show();
}

function changePage() {
	document.getElementById('segmentoPlanoServicoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/planoServico/configuracaosubsegmento/changePage.do";
	document.getElementById('segmentoPlanoServicoForm').submit();
}

function checkAllPlanoSegmento(){
	$jq("#tableCfgSegmento input[id^='idPlanoAssociado_']").each(function(i,e){e.checked = true});
}

function checkAllPlanoSegmento(){
	var checkAll = false;
	if($jq("#tableCfgSegmento input[id='checkAll']")[0].checked){
		checkAll = true;
	}
	$jq("#tableCfgSegmento input[id^='idPlanoAssociado_']").each(function(i,e){
		e.checked = checkAll;
	});
}

function checkAllServicoSegmento(){
	var checkAll = false;
	if($jq("#tableCfgSegmento input[id='checkAll']")[0].checked){
		checkAll = true;
	}
	$jq("#tableCfgSegmento input[id^='idServicoAssociado_']").each(function(i,e){
		e.checked = checkAll;
	});
}


/*
function searchPlano(form){
	alert(1);
	document.planoForm.action = "searchPlano.do";
	document.planoForm.submit();
}

/*
function searchPlano(form){

	alert(0);
	$jq.ajax({
		url:"searchPlano.do",
		data: {
			'{actionForm.cdPlano}':segmentoPlanoForm.cdPlano.value
			,'{actionForm.nmPlano}':segmentoPlanoForm.nmPlano.value
			,'{actionForm.idPlataforma}':segmentoPlanoForm.idPlataforma.value
			,'{actionForm.idCategoria}':segmentoPlanoForm.idCategoria.value
			,'{actionForm.inTitular}':segmentoPlanoForm.inTitular.value
			,'{actionForm.idSegmento}':segmentoPlanoForm.idSegmento.value
		},
		success: function(res){
			$jq('#divPlanoSearch')[0].innerHTML = $jq(res)[0].innerHTML;
		}
	});
}

function savePlano(form){

	alert("gravar");
	$jq.ajax({
		url:"searchPlano.do",
		data: {
			'{actionForm.idSegmento}':segmentoPlanoGravacaoForm.idSegmento.value
			,'{actionForm.inInfancia}':segmentoPlanoGravacaoForm.inInfancia.value
		}
	});
}
*/