var $jq = jQuery.noConflict();

function init() {
	if (document.getElementById('idServico').value != "") {
	    openFrame('detail');
	}
}

function search() {
    if (document.getElementById('idSistema').value == 0) {
		document.getElementById('divErros').style.display = "block";
       	document.getElementById('conteudo_divErros').innerHTML = "Favor preencher os campos obrigatórios";
    } else {
    	document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/search.do#acaoForm";
    	document.getElementById('acaoForm').submit();
	}
}
function sizeFrame(frameId) {
	var frame = document.getElementById(frameId);
	if(frame.contentDocument) {
		frame.height = frame.contentDocument.documentElement.scrollHeight+150; //FF 3.0.11, Opera 9.63, and Chrome
	} else {
		frame.height = frame.contentWindow.document.body.scrollHeight+150; //IE6, IE7 and Chrome
	}
}

function changeStatus(id, statusAtivo) {
    var msg = "";
    if (statusAtivo) {
    	msg = "Tem certeza que deseja Desativar essa Configuração?";
    } else {
    	msg = "Tem certeza que deseja Ativar essa Configuração?";
    }
    if(confirm(msg)) {
    	document.getElementById('idServico').value = id;
    	
    	document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/changeStatus.do#acaoForm";
    	document.getElementById('acaoForm').submit();
    }
}

function change(id, cdServicoSelecionado, nmTipoServico) {
	document.getElementById('alterar').value = "sim";
    goId(id, cdServicoSelecionado, nmTipoServico);
}

function view(id, cdServicoSelecionado, nmTipoServico) {
	document.getElementById('alterar').value = "nao";
    goId(id, cdServicoSelecionado, nmTipoServico);
}

function goId(id, cdServicoSelecionado, nmTipoServico) {
	document.getElementById('idServico').value = id;
	document.getElementById('cdServicoSelecionado').value = cdServicoSelecionado;
	document.getElementById('nmTipoServico').value = nmTipoServico;
	
	document.getElementById('acaoForm').action = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/addIdServico.do";
	document.getElementById('acaoForm').submit();
}    		

function openFrame(frame){
	if (document.getElementById('idServico').value == null || document.getElementById('idServico').value == "") {
		alert("Selecione um Serviço no resultado da pesquisa");
		return;
	} 
    if (frame == "detail") {
	    openDetail();
    } else if (frame == "relationship") {
        openRelationship();
	} else if (frame == "commSolic") {
		openCommSolic();
	}
}

function openDetail(){
	document.getElementById("detail").src = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/detalheservicofixa/DetalheServicoFixaAction.do";
	$jq("#detail").css('display','block');
	$jq("#relationship").css('display','none');
	$jq("#commSolic").css('display','none');
}

function openRelationship(){
	document.getElementById("relationship").src = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicofixa/relacionamento/RelacionamentoServicoFixaAction.do";
	$jq("#detail").css('display','none');
	$jq("#relationship").css('display','block');
	$jq("#commSolic").css('display','none');
}

function openCommSolic(){
	document.getElementById("commSolic").src = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosolicitacaocomercial/ServicoSolicitacaoComercialAction.do";
	$jq("#relationship").css('display','none');
	$jq("#detail").css('display','none');
	$jq("#commSolic").css('display','block');
}    		

/* Chamar as Abas quando carregar o documento */
$jq(document).ready(function() {
    /* Carrega a função das abas */
    $.abasSimples();
    $jq("#resultadoPesquisaEncargos").tooltip({
        track: true
    });
});    

/* Montar accordion */
$jq(function() {
    $jq("#grupos").accordion({active:3,heightStyle:"content"});
});

/* Montar as Abas no detalhe do Serviço */
$.abasSimples = function () {
    /* Guarda IDs dos elementos */
    var abas = 'span#abas';
    var conteudos = 'ul#conteudos';
    /* Oculta todas as abas */
    $jq(conteudos + ' li').hide();
    /* Exibe a primeira aba */
    $jq(conteudos + ' li:first-child').show();
    /* Quando uma aba for clicada */
    $jq(abas + ' a').click(function() {
         /* Remove todas as classes de todas as abas */
         $jq(abas + ' a').removeClass('selected');
         /* Acrescenta uma classe CSS marcando visualmente a aba como selecionada */
         $jq(this).addClass('selected');
         /* Oculta todas as abas abertas */
         $jq(conteudos + ' li').hide();
         /* Exibe a aba que foi clicada */
         $jq(conteudos +  ' ' + $jq(this).attr('href')).show();
         return false;
    }); 
};