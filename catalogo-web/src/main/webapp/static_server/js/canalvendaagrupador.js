var $jq = jQuery.noConflict();

$jq(document).ready(function(){
	$jq("#mensagemLoadSearch").hide();
});

function init(){}

function openFrame(obj) {
	$jq("#mensagemLoadSearch").show();
	$jq("#iframe").css('display','none');
	document.getElementById("iframe").src = obj.value;
}

function openAgrupador(){
	document.getElementById("agrupadorframe").src = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/agrupador/AgrupadorAction.do";
	$jq("#agrupadorframe").css('display','block');
	$jq("#canalvendaframe").css('display','none');
}

function sizeFrame(frame) {

	$jq("#mensagemLoadSearch").css('display','none');
	if (frame.src == "") {
		return;
	}
	$jq("#iframe").css('display','block');
	if(frame.contentDocument) {
		frame.height = frame.contentDocument.documentElement.scrollHeight+200; //FF 3.0.11, Opera 9.63, and Chrome
	} else {
		frame.height = frame.contentWindow.document.body.scrollHeight+200; //IE6, IE7 and Chrome
	}
}
