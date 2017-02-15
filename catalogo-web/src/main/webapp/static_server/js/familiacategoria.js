var $jq = jQuery.noConflict();

function removeFamilia(id) {
	hideMessages();
    if (confirm("Deseja realmente excluir essa família?")) {
		document.getElementById('idEditado').value = id;
		document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/familiacategoria/removeFamilia.do#acaoForm";
		document.getElementById('acaoForm').submit();
	}
}

function removeCategoria(id) {
	hideMessages();
    if (confirm("Deseja realmente excluir essa categoria?")) {
		document.getElementById('idEditado').value = id;
		document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/familiacategoria/removeCategoria.do#acaoForm";
		document.getElementById('acaoForm').submit();
	}
}

function changeStatus(id, statusAtual, action) {
	hideMessages();
    var msg = "";
	if (statusAtual == "true") {
		document.getElementById('statusAtual').value = "ativo";
		msg = "Tem certeza que deseja Desativar essa Configuração?";
	} else {
		document.getElementById('statusAtual').value = "inativo";
		msg = "Tem certeza que deseja Ativar essa Configuração?";
	}
	if (confirm(msg)) {
		document.getElementById('idEditado').value = id;
		document.getElementById('acaoForm').action = action; 
		document.getElementById('acaoForm').submit();
	}
}

function mostrarErro(msg) {
	document.getElementById('divErros').style.display = "block";
   	document.getElementById('conteudo_divErros').innerHTML = msg;
}

function search() {
	document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/familiacategoria/search.do#acaoForm";
	document.getElementById('acaoForm').submit();
}
function showDetails(idFamilia){
	document.getElementById('idFamilia').value = idFamilia;
	document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/familiacategoria/findCategorias.do#acaoForm";
	document.getElementById('acaoForm').submit();
}

function edit(id) {
    document.getElementById('idEditado').value = id;
	$jq('html, body').animate({scrollTop: $jq("#edit").css('display','block').offset().top}, 2000);	
	hideMessages();
}

function editFamilia(id, nome) {
	edit(id);
	document.getElementById('nomeFamiliaNew').value = nome;
	$jq('#editFamilia').css('display','block');
	$jq('#editCategoria').css('display','none');
	$jq('#botao_gravar_familia').css('display','block');
	$jq('#botao_gravar_categoria').css('display','none');
	if (id == '') {
		$jq("#resultadoFamilia").css('display','none');
	}
	$jq("#verCategoriasFamilia").css('display','none');
}

function editCategoria(id, idFamilia, descCateg, descFamilia) {
	edit(id);
	document.getElementById('idFamilia').value = idFamilia;
	document.getElementById('descricaoCategoriaNew').value = descCateg;
	document.getElementById('textDescFamilia').value = descFamilia;
	$jq('#editFamilia').css('display','none');
	$jq('#editCategoria').css('display','block');
	$jq('#botao_gravar_familia').css('display','none');
	$jq('#botao_gravar_categoria').css('display','block');
}

function reccordCategoria() {
	hideMessages();
	if (document.getElementById('descricaoCategoriaNew').value == '') {
		if(document.getElementById('idEditado').value == '') {
			document.getElementById('flashCategoria').innerHTML = 'Não foi possível criar a Categoria. Todos os campos obrigatórios devem ser preenchidos';
			$jq('#flashCategoria').css('display','block');
	    } else {
			document.getElementById('flashCategoria').innerHTML = 'Não foi possível alterar a Categoria. Todos os campos obrigatórios devem ser preenchidos.';
			$jq('#flashCategoria').css('display','block');
	    }
		return;
	}
	document.getElementById("textDescFamilia").disabled = false;
	document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/familiacategoria/reccordCategoria.do#acaoForm";
	document.getElementById('acaoForm').submit();
}

function reccordFamilia() {
	hideMessages();
    if (document.getElementById('nomeFamiliaNew').value == '') {
		if(document.getElementById('idEditado').value == '') {
			document.getElementById('flashFamilia').innerHTML = 'Não foi possível criar a Família. Todos os campos obrigatórios devem ser preenchidos';
			$jq('#flashFamilia').css('display','block');
	    } else {
			document.getElementById('flashFamilia').innerHTML = 'Não foi possível alterar a Família. Todos os campos obrigatórios devem ser preenchidos.';
			$jq('#flashFamilia').css('display','block');
	    }
		return;
	}					
	document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/familiacategoria/reccordFamilia.do#acaoForm";
	document.getElementById('acaoForm').submit();
}

$jq(document).ready(function() { 
	$jq('#pesquisaTipo input:radio').click(function() {
	    $jq('#edit').css('display','none');
	    $jq('#verCategoriasFamilia').css('display','none');
		hideMessages();
	    $jq('#divBotoes').css('display','block');
	    if ($jq(this).val() === 'familia') {
			showFamilia();
	    } else if ($jq(this).val() === 'categoria') {
			showCategoria();
		}
	})
});

function showFamilia(){
	document.getElementById('codigoFamilia').value = "";
    document.getElementById('nomeFamilia').value = "";
    document.getElementById('origemFamilia').value = "";
    document.getElementById('disponibilidadeFamilia').value = "sim";
    $jq('#botao_novo_form').css('display','block');
	$jq('#pesquisa_familia').css('display','block');
	$jq('#pesquisa_categoria').css('display','none');
	$jq('#resultadoCategoria').css('display','none');
}

function showCategoria() {
    document.getElementById('codigoCategoria').value = "";
	document.getElementById('descricaoCategoria').value = "";
	document.getElementById('idFamiliaCategoria').value = "";
	document.getElementById('origemCategoria').value = "";
	document.getElementById('disponibilidadeCategoria').value = "sim";
    $jq('#botao_novo_form').css('display','none');
	$jq('#pesquisa_familia').css('display','none');
	$jq('#pesquisa_categoria').css('display','block');
	$jq('#resultadoFamilia').css('display','none');
}

function hideMessages() {
    $jq('#divErros').css('display','none');
    $jq('#divSucess').css('display','none');
	$jq('#flashFamilia').css('display','none');
	$jq('#flashCategoria').css('display','none');
}