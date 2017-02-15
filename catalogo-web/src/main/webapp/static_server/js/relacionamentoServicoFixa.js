var $jq = jQuery.noConflict();

function novo() {
	$jq("#pesquisaNovo").css('display','block');
	$jq("#resultadoPesquisa").css('display','none');
	document.getElementById("idTipoRelacionamento").disabled = false;
	$jq("#msg_success").css('display','none');
	$jq("#msg_error").css('display','none');
	parent.sizeFrame('relationship');
}
function searchServico() {
    if (document.getElementById('idTipoRelacionamento').value == "0") {
		document.getElementById('msg_aomenosum').style.display = "none";
		document.getElementById('msg_obrigatorio').style.display = "block";
    } else if (document.getElementById('cdPS').value == "" 
    		&& document.getElementById('cdServico').value == "" 
    		&& document.getElementById('nomeDoServicoOrigem').value == "" 
    		&& document.getElementById('nmComercial').value == ""
    		&& document.getElementById('idTipoServico').value == ""){
		document.getElementById('msg_obrigatorio').style.display = "none";
		document.getElementById('msg_aomenosum').style.display = "block";
    } else {
    	document.getElementById("idTipoRelacionamento").disabled = false;
    	document.getElementById('relacionamentoServicoFixaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/relacionamento/searchServico.do";
		document.getElementById('relacionamentoServicoFixaForm').submit();
	}			
}
function changeStatus(id, status){
	var msg = "";
	if (status) {
	    msg = "Tem certeza que deseja desativar essa Configuração?";
	} else {
		msg = "Tem certeza que deseja ativar essa Configuração?";
	}
	if (confirm(msg)) {
		document.getElementById('idRelacionamento').value = id;
    	document.getElementById('relacionamentoServicoFixaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/relacionamento/changeStatus.do";
		document.getElementById('relacionamentoServicoFixaForm').submit();
	}
}
function remove(id){
	if (confirm('Deseja realmente excluir essa configuração?')) {
		document.getElementById('idRelacionamento').value = id;
    	document.getElementById('relacionamentoServicoFixaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/relacionamento/remove.do";
		document.getElementById('relacionamentoServicoFixaForm').submit();
	}
}
function gravarRelacionamento(){
	document.getElementById("idTipoRelacionamento").disabled = false;
	if ($jq(' :checkbox:checked').length > 0) {
		document.getElementById('relacionamentoServicoFixaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/relacionamento/gravarRelacionamento.do";
		document.getElementById('relacionamentoServicoFixaForm').submit();
	} else {
		document.getElementById('msg_selecionar').style.display = "block";
       	document.getElementById('msg_selecionar').innerHTML = "Favor selecionar ao menos um registro";				
	}
}
function init(){
	if (document.getElementById('idServicoTeste').value != ""){
    	document.getElementById('idServicoSearch').value = document.getElementById('idServicoTeste').value;
		document.getElementById('relacionamentoServicoFixaForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/relacionamento/search.do";
		document.getElementById('relacionamentoServicoFixaForm').submit();		
	}
}