var $jq = jQuery.noConflict();

$jq(document).ready(function(){
	$jq("#mensagemLoadSearch").hide();
});

function init(){}

function alternarInDisponivel(id,inDisp) {
	var str = "";
	if(inDisp == "S") str = "desativar";
	else  str = "ativar";

	if (confirm("Tem certeza que deseja "+str+" essa Configura\xe7\xe3o?")) {
		document.getElementById("idCanalVendaAlternarIndisponivel").value = id;
		document.getElementById('canalVendaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/canalvenda/alternarInDisponivel.do";
		document.getElementById('canalVendaForm').submit();
	}
}

function alternarTabelas(){
	$jq('#tabelaGruposComerciais').toggle();
	$jq('#tabelaAgrupadores').toggle();
	$jq('#tabelaAgrupadores').find('.pagelinks').hide();
}

function exibirTabelaAgrupador(){
	$jq('#tabelaGruposComerciais').hide();
	$jq('#tabelaAgrupadores').show();
	$jq('#tabelaAgrupadoresAtiva').attr('value',true);
	$jq('#tabelaAgrupadores').find('.pagelinks').hide();
}

function exibirPesquisa(){
	$jq('#tabelaGruposComerciais').show();
	$jq('#tabelaAgrupadores').hide();
	$jq('#tabelaAgrupadoresAtiva').attr('value',false);
}

function associarAgrupador() {
	if(!validarAssociacaoCanalVendaForm()) {
		$jq('#divSucess').css('display','none');
		generateContentErrorForm("Favor selecionar um agrupador");
	} else if(!validarCanalCheckboxes()) {
		$jq('#divSucess').css('display','none');
		generateContentErrorForm("Favor selecionar ao menos um registro");
	} else {
		document.getElementById('canalVendaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/canalvenda/associarAgrupador.do";
		document.getElementById('canalVendaForm').submit();
	}
}

function confirmarTrocaDePagina() {
	if (confirm("Tem certeza que deseja trocar de p\xe1gina? Todas as configura\xe7\xf5es ser\xe3o perdidas.")) {
		document.getElementById('canalVendaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/canalvenda/trocarPagina.do";
		document.getElementById('canalVendaForm').submit();
	}
}

function criarNovo() {
	document.getElementById('canalVendaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/canalvenda/criarNovo.do";
	document.getElementById('canalVendaForm').submit();
}

function desassociarAgrupador() {
	if(validarCanalCheckboxes()) {
		document.getElementById('canalVendaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/canalvenda/desassociarAgrupador.do";
		document.getElementById('canalVendaForm').submit();
	} else {
		$jq('#divSucess').css('display','none');
		generateContentErrorForm("Favor selecionar ao menos um registro");
	}
}

function editar(id) {
	document.getElementById("idCanalVenda").value = id;
	document.getElementById('canalVendaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/canalvenda/editar.do";
	document.getElementById('canalVendaForm').submit();
}

function pesquisar() {
	$jq("#mensagemLoadSearch").show();
	document.getElementById('canalVendaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/canalvenda/pesquisar.do";
	document.getElementById('canalVendaForm').submit();
}

function remover(id) {
	if(confirm("Deseja realmente excluir esse grupo?")){
		document.getElementById('idCanalVenda').value = id;
		document.getElementById('canalVendaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/canalvenda/remover.do";
		document.getElementById('canalVendaForm').submit();
	}
}

function salvar() {
	if(validarForm()){
		document.getElementById('canalVendaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/canalvendaagrupador/canalvenda/salvar.do";
		document.getElementById('canalVendaForm').submit();
	} else {
	  	if(document.getElementById("idCanalVenda").value == ""){
	  		$jq('#divSucess').css('display','none');
			generateContentErrorForm("N&atilde;o foi poss&iacute;vel criar o Grupo Comercial. Todos os campos obrigat&oacute;rios devem ser preenchidos.");
		} else {
			$jq('#divSucess').css('display','none');
			generateContentErrorForm("N&atilde;o foi poss&iacute;vel alterar o Grupo Comercial. Todos os campos obrigat&oacute;rios devem ser preenchidos.");						
		}
	}
}

function validarForm() {
	var retorno = true;
	
	if (document.getElementById("nmCanalVenda").value == "") {
		retorno = false;
	}

	return retorno;
}

function validarCanalCheckboxes() {
	var r = false;
	$jq('.CanalCheckBoxElem').each(function(i,e) {
		if(e.checked)
			r = true;
	});
	return r;
}

function validarAssociacaoCanalVendaForm() {
	var r = true;
	if($jq('#idEps').attr('value') == "")
		r = false;
	return r;
}