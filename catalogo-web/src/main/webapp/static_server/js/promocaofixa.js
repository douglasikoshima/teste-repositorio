var $jq = jQuery.noConflict();

var alterado = false;

$jq('.changeable').live("change", function(){
    alterado = true;
});

$jq('.btNavegacao74').live("click", function(){
    alterado = false;
});
/* Chamar as Abas quando carregar o documento */
$jq(document).ready(function(){
    /* Carrega a fun��o das abas */
    /* Monta tooltip dos icones de Status */
    $jq(".iconativo,.iconinativo,.iconwait").tooltip();
    /* Monta o jquery para preencher as datas */
    $jq("#dataVigenteInicio,#dataVigenteFim").datepicker();
});

function mostrarErro(erro) {
    document.getElementById('divErros').style.display = "block";
    document.getElementById('conteudo_divErros').innerHTML = erro;
}


$jq('.sortable a').live("click",function() {
    acao($jq(this).attr('href'));
    return false;
})
$jq('.pagelinks a').live("click", function() {
    acao($jq(this).attr('href'));
    return false;
})

function carregarFormularioAlteracao(idPromocao, disabled){
    $jq('html, body').animate({scrollTop: $jq(".detalharOfertas,#detalhesOfertas").css('display','block').offset().top}, 2000);    
    selectAba(document.getElementById('aba1Link'));
    document.getElementById("disabled").value = disabled;
      
    document.getElementById("detalhesOfertas").innerHTML = carregandoDados();
    cleanMessages();
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/carregarFormularioAlteracao.do',
        data: {
            "idPromocao" : idPromocao,
            "disabled" : disabled
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if(idPromocao == "") {
                $jq('#pesquisaOfertas').css('display','none');
            } else {
                $jq('#pesquisaOfertas').css('display','block');
            }
            var data1 = removeForm(data);
            if($jq(data1)[i].id == "error" && $jq(data1)[i].value != ""){
                document.getElementById('divErros').style.display = "block";
                document.getElementById('conteudo_divErros').innerHTML = $jq(data1)[i].value;
                document.getElementById("detalhesOfertas").innerHTML = "";
            } else {
                var codigoPromocao = $jq(data1)[0].value;
                if (codigoPromocao != "") {
                    document.getElementById('exibirCodigoPromocao').innerHTML = "C&oacute;digo da Oferta Banda Larga: " + codigoPromocao;
                }
                document.getElementById("detalhesOfertas").innerHTML = data1;
                var inStatusConfiguracao = $jq(data1)[2].value;
                if (inStatusConfiguracao == "D") {
                    document.getElementById("iconPendenteConfiguracao").style.display = "block";
                } else {
                    document.getElementById("iconPendenteConfiguracao").style.display = "none";
                }
                $jq("#dtInicioNew,#dtFimNew").datepicker();
            }
        },
        error: function(data, error){
            document.getElementById('divErros').style.display = "block";
            document.getElementById('conteudo_divErros').innerHTML = data.responseText;
            document.getElementById("detalhesOfertas").innerHTML = "";
        }  
    });
}

function excluirOferta(idPromocao) {
    $jq('#divSucess').css("display","none");
    $jq('#conteudo_success').css("display","none");
    if (confirm("Deseja realmente excluir essa oferta?")) {
    $jq.ajax({
        type: "GET",
        url: "remove.do",
        data: {
            "codigo" : document.getElementById('codigo').value, 
            "nome" : document.getElementById('nome').value,
            "nomeServico" : document.getElementById('nomeServico').value,
            "status" : document.getElementById('status').value,
            "dataVigenteInicio" : document.getElementById('dataVigenteInicio').value,
            "dataVigenteFim" : document.getElementById('dataVigenteFim').value,
            "idPromocao" : idPromocao
        },
        context: document.body,
        success: function(data){
            document.getElementById('pesquisaOfertas').innerHTML = removeForm(data);
            document.getElementById("conteudo_success").innerHTML = "Oferta removida com Sucesso";
            $jq('#conteudo_success').css("display","block");
            $jq('#pesquisaServicos,#resultadoOfertas,#detalhesServicos,.detalharOfertas,#detalhesOfertas').css('display','none');
        },
        error: function(data, error){
            mostrarErro(data.responseText);
        }  
    });
    }
}

$jq(".adicionarCampo").live("click",function(){ 
    if (document.getElementById('idServico').value == "" || document.getElementById('idSolicitacaoComercial').value == "" || document.getElementById('validadeDesconto').value == ""){
        document.getElementById('erro_aba2').innerHTML = "Preencher campos obrigat&oacute;rios"
        $jq("#box_erro_aba2").css("display","block");
        return false;
    } 
    if (isNaN(document.getElementById('validadeDesconto').value) || document.getElementById('validadeDesconto').value.indexOf(".") >= 0 || document.getElementById('validadeDesconto').value.indexOf("-") >= 0){
        document.getElementById('erro_aba2').innerHTML = "Validade do Desconto Inv&aacute;lida!"
        $jq("#box_erro_aba2").css("display","block");
        return false;
    }
    if(Number(document.getElementById('pzMaximoVigenciaATIS').value) < Number(document.getElementById('validadeDesconto').value)) {
        document.getElementById('erro_aba2').innerHTML = "Validade do Desconto superior ao prazo definido pelo Legado!"
        $jq("#box_erro_aba2").css("display","block");
        return false;
    }
    alterado = true;
    $jq("#box_erro_aba2").css("display","none");
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/addItensComposicao.do',
        data: {
            "idPromocao" : document.getElementById('idPromocao').value,
            "idServico" : document.getElementById('idServico').value,
            "idSolicitacao" : document.getElementById('idSolicitacaoComercial').value,
            "pzValidadePromocao" : document.getElementById('validadeDesconto').value,
            "nmServico" : document.getElementById('nmServico').value,
            "nmServicoDesconto" : document.getElementById('nmServicoDesconto').value,
            "nmSolicitacao" : document.getElementById('nmSolicitacaoComercial').value
        },
        context: document.body,
        success: function(data){
        	var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	mostrarErro($jq(data)[i].value);
            } else {
	            document.getElementById('displayComposicaoPromocaoTOList').innerHTML = removeForm(data);
	            document.getElementById('validadeDesconto').value = "";
	            document.getElementById('idTipoServico').value = "";
	            carregarServicoList();
            }
        },
        error: function(data, error){
            mostrarErro(data.responseText);
        }
    });
}); 

/* Habilitar e Desablitiar os checkboxs */
$jq('#todos').live("click",function() {
    /* Descobre quais os checkboxs pertencentes ao "pai" */
    var check= $jq(this).next().children().children()
    /* Se o #todos for igual a checado */
    if ($jq(this).attr("checked")){
      $jq(check).each(
         function(){
            $jq(this).attr("checked", true);
         }
      );
   }else{
      $jq(check).each(
         function(){
            $jq(this).attr("checked", false);
         }
      );
   }
});

$jq('#checkRecord').live("click",function() {
	var pai = $jq(this).parent().parent().prev();
	var filhos = $jq(pai).next().children().children();
	/* Se pelo menos um filho do conjunto nao esta marcado */
	var checked = true;
	$jq(filhos).each(
		function(){
			if(!$jq(this).attr("checked"))
				checked = false;
		}
	);
	if(checked)
		$jq(pai).attr("checked",true);
	else
		$jq(pai).attr("checked",false);
});

function abrirItens(idPromocao, disabled){
    $jq("#clone").css("display", "none");
    document.getElementById('itemComposicao').innerHTML = carregandoDados();
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/itensComposicao.do',
        data: {
            "idPromocao" : idPromocao,
            "disabled" : disabled
        },
        context: document.body,
        success: function(data){
            document.getElementById('itemComposicao').innerHTML = removeForm(data);
            $jq('.data').each(function(){
                $jq(this).attr("value","");
            });
        },
        error: function(data, error){
            mostrarErro(data.responseText);
        }
    });
}

/*
function carregarCdSolicitacao() {
    document.getElementById('pzMaximoVigenciaATIS.value = document.getElementById('idCdSolicitacaoComercial.value.split('|')[2];
    document.getElementById('cdSolicitacaoComercial.value = document.getElementById('idCdSolicitacaoComercial.value.split('|')[1];
    document.getElementById('idSolicitacaoComercial.value = document.getElementById('idCdSolicitacaoComercial.value.split('|')[0];
    document.getElementById('nmSolicitacaoComercial.value = document.getElementById('idCdSolicitacaoComercial.options[document.getElementById('idCdSolicitacaoComercial.selectedIndex].text;    
}
*/
function carregarCdSolicitacao() {
	if(document.getElementById('idCdSolicitacaoComercial').selectedIndex == 0){
	    document.getElementById('pzMaximoVigenciaATIS').value = "";
	    document.getElementById('cdSolicitacaoComercial').value = "";
	    document.getElementById('idSolicitacaoComercial').value = "";
	    document.getElementById('nmSolicitacaoComercial').value = "";
	} else {
	    document.getElementById('pzMaximoVigenciaATIS').value = document.getElementById('idCdSolicitacaoComercial').value.split('|')[2];
	    document.getElementById('cdSolicitacaoComercial').value = document.getElementById('idCdSolicitacaoComercial').value.split('|')[1];
	    document.getElementById('idSolicitacaoComercial').value = document.getElementById('idCdSolicitacaoComercial').value.split('|')[0];
	    document.getElementById('nmSolicitacaoComercial').value = document.getElementById('idCdSolicitacaoComercial').options[document.getElementById('idCdSolicitacaoComercial').selectedIndex].text;
	}
}

function carregarSolicitacaoList() {
    document.getElementById('idCdSolicitacaoComercial').value = '';
    document.getElementById('idCdSolicitacaoComercial').disabled = 'true';
    document.getElementById('cdSolicitacaoComercial').value = '';
    
    if (document.getElementById('idCdServicoDesconto').value == '') {
        document.getElementById('cdServicoDesconto').value = '';
        return false;
    }
    
    document.getElementById('cdServicoDesconto').value = document.getElementById('idCdServicoDesconto').value.split('|')[1];
    var idServicoDesconto = document.getElementById('idCdServicoDesconto').value.split('|')[0];
    document.getElementById('idServicoDesconto').value = idServicoDesconto;
    document.getElementById('nmServicoDesconto').value = document.getElementById('idCdServicoDesconto').options[document.getElementById('idCdServicoDesconto').selectedIndex].text;
    document.getElementById('idCdSolicitacaoComercial').options[0].text = 'CARREGANDO';
    
    carregarList(idServicoDesconto, "solicitacaoComercialList");
}

function carregarServicoDescontoList() {
    document.getElementById('idCdServicoDesconto').value = '';
    carregarSolicitacaoList();
    document.getElementById('idCdServicoDesconto').disabled = 'true';
    document.getElementById('cdServicoDesconto').value = '';
    
    if (document.getElementById('idCdServico').value == '') {
        document.getElementById('cdServico').value = '';
        return false;
    } 
    
    document.getElementById('cdServico').value = document.getElementById('idCdServico').value.split('|')[1];
    var idServico = document.getElementById('idCdServico').value.split('|')[0];
    document.getElementById('idServico').value = idServico;
    document.getElementById('nmServico').value = document.getElementById('idCdServico').options[document.getElementById('idCdServico').selectedIndex].text;
    document.getElementById('idCdServicoDesconto').options[0].text = 'CARREGANDO';
    carregarList(idServico, "servicoDescontoList");
}
function carregarServicoList() {
    document.getElementById('idCdServico').value = '';
    carregarServicoDescontoList();
    document.getElementById('idCdServico').disabled = "true";
    document.getElementById('cdServico').value = '';
    document.getElementById('idServico').value = '';
    document.getElementById('nmServico').value = '';

    var idTipoServico = document.getElementById('idTipoServico').value;
    if(idTipoServico == '') {
		$jq("#box_erro_aba2").css("display","none");
		return false;
    }
    document.getElementById('idCdServico').options[0].text = 'CARREGANDO';
    carregarList(idTipoServico, "servicoList");
}

function carregarList(id, element){
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/carregarCombo.do',
        data: {
            "id" : id,
            "element" : element
        },
        context: document.body,
        success: function(data){
            document.getElementById(element).innerHTML = removeForm(data);
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
		        document.getElementById('erro_aba2').innerHTML = $jq(data)[i].value;
		        $jq("#box_erro_aba2").css("display","block");
            } else {
            	$jq("#box_erro_aba2").css("display","none");
            }
        },
        error: function(data, error){
            mostrarErro(data.responseText);
        }  
    });
}

function remove(idComposicao,idServico,idSolicitacao){
    alterado = true;
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/removeItensComposicao.do',
        data: {
            "idPromocao" : document.getElementById('idPromocao').value,
            "idServico" : idServico,
            "idSolicitacao" : idSolicitacao,
            "idComposicao" : idComposicao
        },
        context: document.body,
        success: function(data){
            document.getElementById('itemComposicao').innerHTML = removeForm(data);
        },
        error: function(data, error){
            mostrarErro(data.responseText);
        }
    });
}
function gravarItens() {
    ocultarErro();
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/gravarItensComposicao.do',
        data: {
            "idPromocao" : document.getElementById('idPromocao').value
        },
        context: document.body,
        success: function(data){
            document.getElementById('itemComposicao').innerHTML = removeForm(data);
        },
        error: function(data, error){
            mostrarErro(data.responseText);
        }
    });
}
function ocultarErro() {
    $jq("#box_erro_aba2").css("display","none");
    document.getElementById('conteudo_success').style.display = "none";
    document.getElementById('divErros').style.display = "none";
}
function abrirDisponibilidade(idPromocao, disabled) {
    document.getElementById('disponibilidade').innerHTML = carregandoDados();
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/carregarDisponibilidade.do',
        data: {
            "idPromocao" : idPromocao,
            "disabled" : disabled
        },
        context: document.body,
        success: function(data){
            document.getElementById('disponibilidade').innerHTML = removeForm(data);
            /*
            $jq("#treeListaTipos").treeview({
                collapsed: true,
                animated: "fast",
                unique: true
            });
            */
        },
        error: function(data, error){
            mostrarErro(data.responseText);
        }
    });
}
function gravarDisponibilidade() {

    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/gravarDisponibilidade.do',
        data: {
            "idPromocao" : document.getElementById('idPromocao').value
        },
        context: document.body,
        success: function(data){
            document.getElementById('conteudo_success').innerHTML = 'Dados gravados com Sucesso';
            document.getElementById('gravarCanalVendaBarra').style.display = "none";
            document.getElementById('gravarCanalVenda').style.display = "none";
            $jq('#conteudo_success').css('display','block');
        },
        error: function(data, error){
            mostrarErro(data.responseText);
        }
    });  
}

function carregandoDados(){
    return "<img src='/catalogo/static_server/img/carregando.gif'>";
}

function cleanMessages(){    
    document.getElementById('divErros').style.display = "none";
    document.getElementById('conteudo_divErros').innerHTML = "";
    document.getElementById('conteudo_success').style.display = "none";
    document.getElementById('conteudo_success').innerHTML = "";
}

function abrirAba(obj) {
    selectAba(obj);
    var idValue = "";
    if (document.getElementById("idPromocao") != null) {
        idValue = document.getElementById("idPromocao").value;
    }
    var disabled = "";
    if (document.getElementById("disabled").value != "") {
        disabled = "true";
    }    
    if (obj.id == "aba1Link") {
        carregarFormularioAlteracao(idValue, disabled);
    } else if (obj.id == "aba2Link") {
        if (idValue == "") {
            document.getElementById('itemComposicao').innerHTML = "";
        } else {
            abrirItens(idValue, disabled);
        }
    } else if (obj.id == "aba3Link") {
        if (idValue == "") {
            document.getElementById('disponibilidade').innerHTML = "";
        } else {
            abrirDisponibilidade(idValue, disabled);
        }    
    }
}

function selectAba(obj) {
    cleanMessages();
    if(alterado) {
        if(!confirm('Todas as informa\xe7\xf5es configuradas ser\xe3o perdidas, Deseja prosseguir sem gravar?')) {
            return false;
        }
        alterado = false;
    }
    $jq('span#abas a').removeClass('selected');
    // Acrescenta uma classe CSS marcando visualmente a aba como selecionada 
    $jq(obj).addClass('selected');
    // Oculta todas as abas abertas
    $jq('ul#conteudos li').hide();
    // Exibe a aba que foi clicada 
    $jq('ul#conteudos ' + $jq(obj).attr('href')).show();
    return false;
} 

function pesquisar(){
    var caminho = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/search.do";
    $jq('.detalharOfertas').css('display','none');
    document.getElementById("exibirCodigoPromocao").innerHTML = "";
    document.getElementById("iconPendenteConfiguracao").style.display = "none";
    carregarConsulta(caminho,"");
}

function carregarConsulta(caminho, reload){
    if (reload != "reload") {
        cleanMessages();
    }
    document.getElementById("pesquisaOfertas").innerHTML = carregandoDados();
    $jq('html, body').animate({scrollTop: $jq("#pesquisaOfertas").css('display','block').offset().top}, 2000);
    $jq.ajax({
        type: "GET",
        url: caminho,
        data: {
            "codigo" : document.getElementById('codigo').value,
            "nome" : document.getElementById('nome').value,
            "nomeServico" : document.getElementById('nomeServico').value,
            "status" : document.getElementById('status').value,
            "dataVigenteInicio" : document.getElementById('dataVigenteInicio').value,
            "dataVigenteFim" : document.getElementById('dataVigenteFim').value,
            "pendenteConfiguracao" : document.getElementById('pendenteConfiguracao').value,
            "reload" : reload
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
                document.getElementById('divErros').style.display = "block";
                document.getElementById('conteudo_divErros').innerHTML = $jq(data)[i].value;
                document.getElementById("pesquisaOfertas").innerHTML = ""
            } else {
                document.getElementById("pesquisaOfertas").innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            document.getElementById('divErros').style.display = "block";
            document.getElementById('conteudo_divErros').innerHTML = data.responseText;
            document.getElementById("pesquisaOfertas").innerHTML = ""
        }  
    });
}

function removeForm(data){
	var data1 = data.replace('<form name="promocaoFixaForm" method="post" action="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/search.do">','');
	var data2 = data1.replace('</form>','');
	return data2
}

function gravar(){
    cleanMessages();
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/gravar.do',
        data: {
            "idPromocao" : document.getElementById('idPromocao').value,
            "codigo" : document.getElementById('cdPromocaoNew').value,
            "nome" : document.getElementById('nmPromocaoNew').value,
            "dataVigenteInicio" : document.getElementById('dtInicioNew').value,
            "dataVigenteFim" : document.getElementById('dtFimNew').value
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
                document.getElementById('divErros').style.display = "block";
                document.getElementById('conteudo_divErros').innerHTML = $jq(data)[i].value;
            } else if($jq(data)[i].id == "success" && $jq(data)[i].value != "") {
                document.getElementById('conteudo_success').style.display = "block";
                document.getElementById('conteudo_success').innerHTML = $jq(data)[i].value;
                $jq('.detalharOfertas').css('display','none');
                document.getElementById("exibirCodigoPromocao").innerHTML = "";                
                document.getElementById("iconPendenteConfiguracao").style.display = "none";
                if (document.getElementById('idPromocao').value != "") {
                    carregarConsulta("/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/orderPromocao.do", "reload");
                }
            }
        },
        error: function(data, error){
            document.getElementById('divErros').style.display = "block";
            document.getElementById('conteudo_divErros').innerHTML = data.responseText;
        }  
    });
}

function carregarFormularioCanalVenda() {
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/carregarFormularioCanalVenda.do',
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById('pesquisarCanalVenda').innerHTML = removeForm(data);
                document.getElementById("botaoCarregarFormularioCanalVenda").disabled=true;
                document.getElementById("botao_novo_canalvenda").disabled=true;
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}

function pesquisarCanalVenda() {
    var idEps = document.getElementById("idEps").value;
    var cdCanalVenda = document.getElementById("cdCanalVenda").value;
    $jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/pesquisarCanalVenda.do',
        data: {
            "idEps" : idEps,
            "cdCanalVenda" : cdCanalVenda
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById('adicionarCanalVenda').innerHTML = removeForm(data);
			    var checked = $jq(".ckbCanalVenda").size() == $jq(".ckbCanalVenda:checked").size();
		        $jq('.ckbCanalVendaTodos').attr("checked",checked);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}

function adicionarCanalVenda() {
    copiarDadosFormulario();
    var canalVendaChecked = "";
    $jq(".ckbCanalVenda:checked").each(function(){
        canalVendaChecked += $jq(this).val() + "|";
    });
    canalVendaChecked = canalVendaChecked.slice(0,canalVendaChecked.lastIndexOf('|'));
    var canalVendaUnchecked = "";
    $jq(".ckbCanalVenda[checked!='checked']").each(function(){
        canalVendaUnchecked += $jq(this).val() + "|";
    });
    canalVendaUnchecked = canalVendaUnchecked.slice(0,canalVendaUnchecked.lastIndexOf('|'));
    $jq(".ckbCanalVenda[checked!='checked']")
	$jq.ajax({
        type: "GET",
        url: '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/adicionarNovoCanalVenda.do',
        data: {
            "idEps" : document.getElementById("idEps2").value,
            "canalVendaChecked" : canalVendaChecked,
            "canalVendaUnchecked" : canalVendaUnchecked,
            "operacao" : "adicionarCanalVenda"
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById("conteudoDisponibilidadePromocaoFixaTOList").innerHTML = removeForm(data);
                document.getElementById("displaySearchCanalVendaTOList").innerHTML = "";
                document.getElementById("displaySearchCanalVendaTOList").style.display = "none";
                document.getElementById("botaoAtualizarCanalVenda").style.display = "none";
                document.getElementById("botaoCarregarFormularioCanalVenda").disabled=true;
                document.getElementById("botao_novo_canalvenda").disabled=true;
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}

function copiarDadosFormulario(){

    var canalVendaChecked = "";
	    if ($jq('.ckbCanalVendaTodos:checked').length == $jq('.ckbCanalVendaTodos').length) {
	        $jq('.ckbCanalVendaTodos').attr('checked',false);
	    }
	    $jq(".ckbCanalVenda:checked").each(function(){
	        canalVendaChecked += $jq(this).val() + "|";
	    });
	    canalVendaChecked = canalVendaChecked.slice(0,canalVendaChecked.lastIndexOf('|'));
	    $jq("#canalVendaCheckedAba").attr("value", canalVendaChecked);

}

$jq('.ckbCanalVendaTodos').live("click",function() {
    /* Se o #todos for igual a checado */
    if ($jq(this).attr("checked")){
        $jq(".ckbCanalVenda").each(function(){
          if (!$jq(this).attr("disabled")) {
            $jq(this).attr("checked", true);
          }
        });
    } else {
        $jq(".ckbCanalVenda").each(function(){$jq(this).attr("checked", false);});
    }
});
/*
$jq('.sortable a').live("click",function() {
    acao($jq(this).attr('href'));
    return false;
})
$jq('.pagelinks a').live("click", function() {
    acao($jq(this).attr('href'));
    return false;
})
*/
function acao(acao){
    var local = acao.split("?")[0].substr(acao.lastIndexOf("/")+1);
	var local = acao.split("?")[0].substr(acao.lastIndexOf(",")+1);
    
    var canalVendaChecked = "";
    $jq(".ckbCanalVenda:checked").each(function(){
        canalVendaChecked += $jq(this).val() + "|";
    });
    canalVendaChecked = canalVendaChecked.slice(0,canalVendaChecked.lastIndexOf('|'));
    
	var idEps2 = "";
	if(document.getElementById("idEps2") != null){
		idEps2 = document.getElementById("idEps2").value;
	}
	idEps2 = document.getElementById("idEps2") != null ? document.getElementById("idEps2").value : null;
	/*
    var caminho = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/orderDisponibilidade.do?" + acao.split("?")[1];
    $jq.ajax({
        type: "GET",
        data: {
        	"element" : local,
        	"idEps" : idEps2,
            "canalVendaChecked" : canalVendaChecked
        },
        url: caminho,
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById(local).innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
    */
    /*
    var caminho = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/orderItem.do?" + acao.split("?")[1];
    $jq.ajax({
        type: "GET",
        data: {
        	"element" : local
        },
        url: caminho,
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById(local).innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
    */
    /*
    var caminho = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/orderItem.do?" + acao.split("?")[1];
    $jq.ajax({
        type: "GET",
        data: {
        	"element" : local
        },
        url: caminho,
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById(local).innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
    */
    var caminho = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/promocaofixa/orderPromocao.do?" + acao.split("?")[1];
	var caminho = acao.split("?")[0].substr(acao.lastIndexOf("/")+1);
	caminho = caminho.slice(0,caminho.lastIndexOf(","))+"?"+acao.split("?")[1];

    $jq.ajax({
        type: "GET",
        data: {
        	"element" : local
        },
        url: caminho,
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data)[i].id == "error" && $jq(data)[i].value != ""){
            	showError($jq(data)[i].value);
            } else {
                document.getElementById(local).innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            showError(data.responseText);
        }  
    });
}