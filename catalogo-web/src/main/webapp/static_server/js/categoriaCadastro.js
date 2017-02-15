var $jq = jQuery.noConflict();

function clearPage() {
	document.getElementById('idClassificacaoCategoriaScoreSearch').disabled = false;
	document.getElementById('categoriaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/clearPage.do";
	document.getElementById('categoriaForm').submit();
}

function clearSearch() {
	document.getElementById('categoriaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/clearSearch.do";
	document.getElementById('categoriaForm').submit();
}

function search() {
	document.getElementById('categoriaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/search.do";
	document.getElementById('categoriaForm').submit();
}

function save() {
	document.getElementById('idClassificacaoCategoriaScore').disabled = false;
	document.getElementById('categoriaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/save.do";
	document.getElementById('categoriaForm').submit();

}

function remove(id,possuiAssociacaoPlano,possuiAssociacaoOfertaServico,possuiAssociacaoServico, possuiAssociacaoServicoFixa) {
	document.getElementById('idCategoriaScore').value = id;
	if(possuiAssociacaoPlano){
		if (confirm("Essa categoria é associada a um plano. Ao excluí-la os planos perderão a configuração. Tem certeza que deseja excluir?")) {
			document.getElementById('categoriaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/remove.do";
			document.getElementById('categoriaForm').submit();
		}
	}
	else
	if(possuiAssociacaoOfertaServico){
		if (confirm("Essa categoria é associada a uma oferta. Ao excluí-la os ofertas perderão a configuração. Tem certeza que deseja excluir?")) {
			document.getElementById('categoriaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/remove.do";
			document.getElementById('categoriaForm').submit();
		}
	}
	else
	if(possuiAssociacaoServico){
		if (confirm("Essa categoria é associada a um serviço. Ao excluí-la os serviços perderão a configuração. Tem certeza que deseja excluir?")) {
			document.getElementById('categoriaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/remove.do";
			document.getElementById('categoriaForm').submit();
		}
	}
	else
	if(possuiAssociacaoServicoFixa){
		if (confirm("Essa categoria é associada a um serviço da fixa. Ao excluí-la os serviços fixa perderão a configuração. Tem certeza que deseja excluir?")) {
			document.getElementById('categoriaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/remove.do";
			document.getElementById('categoriaForm').submit();
		}
	}				
	else {
		if (confirm("Deseja realmente excluir essa categoria?")) {
			document.getElementById('categoriaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/remove.do";
			document.getElementById('categoriaForm').submit();
		}
	}
}

function possuiCaracterInvalido() {

	return false;
}

function editar(idCategoria){
	document.getElementById("createEditCadastro").innerHTML = carregandoDados();
	$jq("#createEditCadastro").css('display','block').offset().top;
	document.getElementById("createEditCadastro").scrollIntoView();
	document.getElementById('idCategoriaScore').value = idCategoria;
    $jq.ajax({
        type: "GET",
        url: "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/edit.do",
        data: {
            "idCategoriaScore" : document.getElementById('idCategoriaScore').value,
            "idClassificacaoCategoriaScoreSearch" : document.getElementById('idClassificacaoCategoriaScoreSearch').value,
            "nmCategoriaScoreSearch" : document.getElementById('nmCategoriaScoreSearch').value
        },
        context: document.body,
        success: function(data){
			var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
                showError($jq(data)[i].value);
                document.getElementById("createEditCadastro").innerHTML = ""
            } else {
                document.getElementById("createEditCadastro").innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
            document.getElementById("createEditCadastro").innerHTML = "";
        }  
    });
}

function create(){
	document.getElementById("createEditCadastro").innerHTML = carregandoDados();
	$jq("#createEditCadastro").css('display','block').offset().top;
	document.getElementById("createEditCadastro").scrollIntoView();
    $jq.ajax({
        type: "GET",
        url: "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/create.do",
        data: {
            "idClassificacaoCategoriaScoreSearch" : document.getElementById('idClassificacaoCategoriaScoreSearch').value,
            "nmCategoriaScoreSearch" : document.getElementById('nmCategoriaScoreSearch').value
        },
        context: document.body,
        success: function(data){
			var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
                showError($jq(data)[i].value);
                document.getElementById("createEditCadastro").innerHTML = ""
            } else {
                document.getElementById("createEditCadastro").innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
            document.getElementById("createEditCadastro").innerHTML = "";
        }  
    });
}

function removeForm(data){
	var data1 = data.replace('<form name="categoriaScoreForm" id="categoriaForm" method="post" action="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/categoria/search.do">','');
	var data2 = data1.replace('</form>','');
	return data2
}

function carregandoDados(){
    return "<img src='/catalogo/static_server/img/carregando.gif'>";
}
