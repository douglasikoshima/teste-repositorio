var $jq = jQuery.noConflict();

function mostrarErro(msg){
    document.getElementById('divErros').style.display = "block";
    document.getElementById('divErros').scrollIntoView();
	document.getElementById('conteudo_divErros').innerHTML = msg;
}

function mostrarSucesso(msg) {
    document.getElementById('conteudo_success').style.display = "block";
	document.getElementById('conteudo_success').innerHTML = msg;
	document.getElementById('conteudo_success').scrollIntoView();
}
    
/*** Seleciona todos os itens do select ********/
function seleciona_todos(obj, true_false)
    {
        arquivo = document.getElementById(obj);
        tamanho=arquivo.length;
        for (var cont=0; cont<tamanho; cont++ )
        {arquivo.options[cont].selected = true_false;}
        return !$jq('#select1 option:selected').remove().appendTo('#select2');  
    }
    
/*** Seleciona todos os itens do select ********/
function deseleciona_todos(obj, true_false)
    {
        arquivo = document.getElementById(obj);
        tamanho=arquivo.length;
        for (var cont=0; cont<tamanho; cont++ )
        {arquivo.options[cont].selected = true_false;}
        return !$jq('#select2 option:selected').remove().appendTo('#select1');  
    }
    
/*** Reordenar os itens do Select ********/ 
function ReorderFields(actionType, listBoxCtrl) {
    if (actionType == 'up') {ListBoxMoveUp(listBoxCtrl);}
    else if (actionType == 'down') {ListBoxMoveDown(listBoxCtrl);}
}
/*** Reordenar os itens do Select para baixo ********/
function ListBoxMoveDown(ctrl) {
    var selectedDx = $jq("#" + ctrl).find("option:selected");
    var next = selectedDx.last().next();
    selectedDx.each(function() {$jq(this).insertAfter(next);});
    $jq("#" + ctrl).focus();
}
/*** Reordenar os itens do Select para cima ********/
function ListBoxMoveUp(ctrl) {
    var selectedDx = $jq("#" + ctrl).find("option:selected");
    var prev = selectedDx.first().prev();
    selectedDx.each(function() {$jq(this).insertBefore(prev);});
    $jq("#" + ctrl).focus();
}

/*** Adiciona os itens do select 1 para o select 2 ********/
function adicionar() {
    $jq('#select1 option:selected').remove().appendTo('#select2');  
}  
/*** Adiciona os itens do select 2 para o select 1 ********/
function remover() {  
    $jq('#select2 option:selected').remove().appendTo('#select1');  
}

function pesquisarOferta(){
    cleanMessages();
    document.getElementById('pesquisaOfertas').innerHTML = carregandoDados();
    var idAnaliseCredito = document.getElementById('idAnaliseCredito').value;
    var idEps = document.getElementById('idEps').value;    
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analiseCreditoLinha/pesquisarOferta.do',
        data: {
            "idAnaliseCredito" : idAnaliseCredito,
            "idEps" : idEps
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
                mostrarErro($jq(data)[i].value);
                document.getElementById('pesquisaOfertas').innerHTML = "";
            } else {
                document.getElementById('pesquisaOfertas').innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            mostrarErro(data.responseText);
            document.getElementById('pesquisaOfertas').innerHTML = "";
        }  
    });
}

function carregandoDados(){
    return "<img src='/catalogo/static_server/img/carregando.gif'>";
}

function cleanMessages() {
    document.getElementById('divErros').style.display = "none";
    document.getElementById('conteudo_divErros').innerHTML = "";
    document.getElementById('conteudo_success').style.display = "none";
    document.getElementById('conteudo_success').innerHTML = "";
}

function gravarPriorizacao(){
    var idAnaliseCredito = document.getElementById("idAnaliseCredito").value;
    var idOfertafixaCreditoScoreSelected = obterOfertafixaCreditoScoreSelected();
    
    var selectedAgrup = obterAgrupadorSelected();
    
    cleanMessages();
    document.getElementById('pesquisaOfertas').innerHTML = carregandoDados();
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analiseCreditoLinha/gravarPriorizacao.do',
        data: {
            "idAnaliseCredito" : idAnaliseCredito,
            "idOfertafixaCreditoScoreSelected" : idOfertafixaCreditoScoreSelected,
            "idEps" : selectedAgrup
        },
        context: document.body,
        success: function(data){
            var data1 = removeForm(data);
            var i = $jq(data1).length - 1; 
            if($jq(data1)[i].id == "error" && $jq(data1)[i].value != ""){
                mostrarErro($jq(data1)[i].value);
                document.getElementById('pesquisaOfertas').innerHTML = "";
            } else if ($jq(data1)[i].id == "success" && $jq(data1)[i].value != "") {
                mostrarSucesso($jq(data1)[i].value);
                document.getElementById('pesquisaOfertas').innerHTML = "";
            }
        },
        error: function(data, error){
            mostrarErro(data.responseText);
            document.getElementById('pesquisaOfertas').innerHTML = "";
        }  
    });
}

function removeForm(data){
	var data1 = data.replace('<form name="analiseCreditoPriorisarForm" id="acaoForm" method="post" action="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analiseCreditoLinha/pesquisarOferta.do">','');
	var data2 = data1.replace('</form>','');
	return data2
}

function obterOfertafixaCreditoScoreSelected(){
    var ofertafixaCreditoScoreSelected = "";
    $jq("#select2 option").each(function(){
        ofertafixaCreditoScoreSelected += $jq(this).val() + "|";
    });
    return ofertafixaCreditoScoreSelected.slice(0,ofertafixaCreditoScoreSelected.lastIndexOf('|'));
}

function obterAgrupadorSelected(){
    
    var comboIdEps = document.getElementById('idEps');    
    var agrupadorSelectedIndex = comboIdEps.selectedIndex;
    
    var valor = comboIdEps.options[agrupadorSelectedIndex].value;
    
    return valor;
}
