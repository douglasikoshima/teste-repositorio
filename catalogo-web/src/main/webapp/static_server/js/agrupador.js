var $jq = jQuery.noConflict();

$jq(document).ready(function(){
	$jq("#mensagemLoadSearch").css('display','none');
});

function init(){}

function alternarInDisponivel(id,inDisp) {
	var str = "";
	if(inDisp == "S") str = "desativar";
	else  str = "ativar";

	if (confirm("Tem certeza que deseja "+str+" essa Configura\xe7\xe3o?")) {
		document.getElementById('idCanalVendaAlternarIndisponivel').value = id;
		document.getElementById('agrupadorForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/agrupador/alternarInDisponivel.do";
		document.getElementById('agrupadorForm').submit();
	}
}

function criarNovo() {
	document.getElementById('idEps').value = null;
	document.getElementById('nmEps').value = "";
	$jq('#cadastroAgrupador').show();
	$jq('#cadastroAgrupador').find('.conteudo_box_top_center')[0].innerHTML = 'Novo';
}

function editar(id,nm) {
	$jq('#cadastroAgrupador').show();
	$jq('#cadastroAgrupador').find('.conteudo_box_top_center')[0].innerHTML = 'Editar';
	document.getElementById('idEps').value = id;
	document.getElementById('nmEps').value = nm;
}

function alterar(id) {
	document.getElementById('idEps').value = id;
	document.getElementById('agrupadorForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/agrupador/salvar.do";
	document.getElementById('agrupadorForm').submit();
}

function pesquisar() {
	document.getElementById('agrupadorForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/agrupador/pesquisar.do";
	document.getElementById('agrupadorForm').submit();
}

function remover(id) {
	if(confirm("Deseja realmente excluir esse agrupador?")){
		document.getElementById('idEps').value = id;
		document.getElementById('agrupadorForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/agrupador/remover.do";
		document.getElementById('agrupadorForm').submit();
	}
}

function salvar() {
	if(validarForm()){
		document.getElementById('agrupadorForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/agrupador/salvar.do";
		document.getElementById('agrupadorForm').submit();
	} else {
		$jq('#divSucess').css('display','none');
		generateContentErrorForm("Favor preencher os par&acirc;metros obrigat&oacute;rios");
	}
}

function validarForm() {
	var retorno = true;
	
	if (document.getElementById('nmEps').value == "") {
		retorno = false;
	}

	return retorno;
}

function validarAssociacaoCanalVendaForm() {
	var r = true;
	if($jq('#idEps').attr('value') == "")
		r = false;
	return r;
}