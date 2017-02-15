var $jq = jQuery.noConflict();

var tpVigencia; //var global usado na valida��o da a��o gravar.

$jq(document).ready(function() {
    /* Somente numeros com a classe onNumber */
    


    cleanMessages();
    /* Carrega janelas de calendarios */
    $jq("#dataVigenteInicioNew,#dataVigenteFimNew").datepicker();
    
    $jq("#dataVigenteInicio,#dataVigenteFim").datepicker({
         minDate: 0
    });
    
    /*Interceptadores da a��o Ordenar - Para n�o ocorrer o submit e quebrar os componentes*/
    $jq(".sortable a").live("click",function() {
        ordenar($jq(this).attr('href'));
        return false;
    })
    $jq(".pagelinks a").live("click", function() {
        ordenar($jq(this).attr('href'));
        return false;
    })
});

function novo() {
    $jq.ajax({
        type: "GET",
        url: "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosadicionais/novo.do",
        context: document.body,
        success: function(data){
            document.getElementById("servicoAdicional").innerHTML = removeForm(data);
        },
        error: function(data, error){
            mostrarErro(data.responseText);
        }  
    });
}
function visualizar(id) {
    $jq.ajax({
        type: "GET",
        url: "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosadicionais/load.do",
        data: {
            "idServicosAdicionais" : id
        },
        context: document.body,
        success: function(data){
            document.getElementById("servicoAdicional").innerHTML = removeForm(data);
        },
        error: function(data, error){
            mostrarErro(data.responseText);
        }  
    });
}

function mostrarErro(erro) {
    document.getElementById('divErros').style.display = "block";
    document.getElementById('divErros').scrollIntoView();
    document.getElementById('conteudo_divErros').innerHTML = erro;
}
function SomenteNumero(e){
    var tecla=(window.event)?event.keyCode:e.which;   
    if((tecla>47 && tecla<58)) return true;
    else{
        if (tecla==8 || tecla==0) return true;
    else  return false;
    }
}
/* CARREGAR LISTAS */
//id = valor selecionado na tela. (zero) para pesquisa sem id.
//element = URL a ser chamado (M�todo do controller).
function carregarList(id, URL, element){
    var URL = URL+'.do';
    $jq.ajax({
        type: "GET",
        url: URL,
        data: {
            "id" : id
        },
        context: document.body,
        success: function(data){
            document.getElementById(element).innerHTML = removeForm(data);
        },
        error: function(data, error){
            mostrarErro(data.responseText);
        }  
    });
}

function removeForm(data){
	var data1 = data.replace('<form name="servicosAdicionaisForm" id="acaoForm" method="post" action="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosadicionais/search.do">','');
	var data2 = data1.replace('</form>','');
	return data2
}

function carregarNomeServicoList(){
    
    var idTipoServico = document.getElementById('comboTipoServico').value;
    $jq('#comboNomeServico').attr('disabled','disabled');
    if(idTipoServico == 0) {
        limparCampos();
        $jq('#comboNomeServico').attr('disabled','disabled');
        $jq('#comboNomeSolicitacao').attr('disabled','disabled');
    } else{
        
        
        
        $jq('#comboNomeSolicitacao').val(0);
        $jq('#comboNomeSolicitacao').attr('disabled','disabled');
        $jq('#comboNomeServico').html('<option>CARREGANDO<\option>')
        //$jq('#comboNomeServico').removeAttr('disabled');
        carregarList(idTipoServico, "carregarNomeServico", "divComboNomeServico");
    }
}

function carregarSolicitacaoList(){
    var idServicoBox = document.getElementById('comboNomeServico').value.split("|")[0];
    var cdServico = document.getElementById('comboNomeServico').value.split("|")[1];
    document.getElementById("idServico").value = cdServico;
    if(idServicoBox == 0) {
        $jq('#comboNomeSolicitacao').attr('disabled','disabled');
        $jq('#comboNomeSolicitacao').val(0);
        $jq('#idServico').val("");
        $jq('#codSolicitacao').val("");
        $jq('#dataVigenteInicio').val("");
        $jq('#dataVigenteFim').val("");
        $jq('#idTempoVigencia').val("");
    } else{
        $jq('#comboNomeSolicitacao').html('<option>CARREGANDO<\option>')
        $jq('#comboNomeSolicitacao').removeAttr('disabled');
        carregarList(idServicoBox, "carregarSolicitacao", "divComboNomeSolicitacao");
    }
}

function carregarTempoVigencia(){
    var cdSolicitacaoComercial = document.getElementById('comboNomeSolicitacao').value.split("|")[1];
    var tempoVigencia = document.getElementById('comboNomeSolicitacao').value.split("|")[2];
    tpVigencia = tempoVigencia; //var usado na valida��o da a��o gravar.
    
    if(cdSolicitacaoComercial == 0) {
        $jq('#codSolicitacao').val("");
        $jq('#dataVigenteInicio').val("");
        $jq('#dataVigenteFim').val("");
        $jq('#idTempoVigencia').val("");
        
        $jq('#dataVigenteInicio').attr('disabled','disabled');
        $jq('#dataVigenteFim').attr('disabled','disabled');
        $jq('#idTempoVigencia').attr('disabled','disabled');
    } else{
        $jq('#codSolicitacao').val(cdSolicitacaoComercial);
        $jq('#idTempoVigencia').val(tempoVigencia);
        $jq('#dataVigenteInicio').val("");
        $jq('#dataVigenteFim').val("");
        
        $jq('#dataVigenteInicio').removeAttr('disabled');
        $jq('#dataVigenteFim').removeAttr('disabled');
        $jq('#idTempoVigencia').removeAttr('disabled');
    }
}

function validarPeriodo(strIni, strFim) {
    var dateIni = new Date(strIni.replace(/(\d{2})\/(\d{2})\/(\d{4})/,'$3/$2/$1'));
    var dateFim = new Date(strFim.replace(/(\d{2})\/(\d{2})\/(\d{4})/,'$3/$2/$1'));
    if (dateIni > dateFim) {
        return 0;
    }
    return 1;
}
function validarData(strDate) {
    
    var arrayDate = strDate.split('/');
    if (arrayDate.length != 3) {
        return 0;        
    }
    if(arrayDate[0] < 1 || arrayDate[0] > 31) {
        return 0;
    }
    if(arrayDate[1] < 1 || arrayDate[1] > 12) {
        return 0;
    }
    if(arrayDate[2] < 1 ) {
        return 0;
    }
    var date = new Date(strDate.replace(/(\d{2})\/(\d{2})\/(\d{4})/,'$3/$2/$1'));
    if (date == 'NaN') {
        return 0;
    }
    return 1;
}

function validarCampos(){
    
    if($jq('#comboTipoServico').val() == 0){ //divMsgInfo
        return "Favor preencher os par\xe2metros obrigat\xf3rios.";
    } 
    if($jq('#comboNomeServico').val() == 0){
        return "Favor preencher os par\xe2metros obrigat\xf3rios.";
    }
    if($jq('#comboNomeSolicitacao').val() == 0){
        return "Favor preencher os par\xe2metros obrigat\xf3rios.";
    }
    var dtInicio = $jq('#dataVigenteInicio').val();
    var dtFim = $jq('#dataVigenteFim').val();
    if(dtInicio){
        if(validarData(dtInicio)!= 1){
            return "Data inv\xe1lida.";
        }
    }else{
        return "Favor preencher os par\xe2metros obrigat\xf3rios.";
    }
    if(dtFim){
        if(validarData(dtFim)!= 1){
            return "Data inv\xe1lida.";
        }
    }
    if(dtInicio && dtFim){
        if(validarPeriodo(dtInicio, dtFim) != 1){
            return "Data inv\xe1lida.";
        }
    }
    var boxTempoVigencia = $jq('#idTempoVigencia').val();
    if($jq.trim(boxTempoVigencia) == ""){
          return "Favor preencher os par\xe2metros obrigat\xf3rios.";
    }
    if(boxTempoVigencia > tpVigencia){
        return "Tempo de vig\xeancia superior ao prazo definido pelo Legado!";
    }
    return "ok";
}

function excluirServicosAdicionais(id) {
    cleanMessages();
 if (confirm("Deseja realmente excluir esse Servi\xe7o Adicional ? ")) {
     var URL = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosadicionais/ecluir.do"; //chamada do metodo no controller.
    var element = "tableServicosAdicionais"; //id da div onde ser� inserido a tabela de resultado.
    var idServicosAdicionais = id;
    $jq('#botaoExcluir').css('display', 'block');
    $jq('#botao_gravar').css('display', 'block');
    $jq.ajax({
        type: "GET",
        url: "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosadicionais/excluir.do",
        data: {
             "id" : id
        },
        context: document.body,
        success: function(data){
            var i = $jq(data).length - 1;
            if($jq(data).find('#error').attr('id')){
                mostrarErro($jq(data).find('#error').attr('value'));
            }else if($jq(data).find('#success').attr('id')) {
                mostrarSucesso($jq(data).find('#success').attr('value'));
                document.getElementById(element).innerHTML = removeForm(data);
            }
        },
        error: function(data, error){
            mostrarErro($jq(data).find('#error').attr('value'));
        }  
    });
   }
    
}

function mostrarSucesso(msg) {
    document.getElementById('conteudo_success').style.display = "block";
	document.getElementById('conteudo_success').innerHTML = msg;
	document.getElementById('conteudo_success').scrollIntoView();
}
function gravarServicosAdicionais() {
    cleanMessages();
    var msg_validarCampos = validarCampos(); //retorna mensagem de erro ou sucesso.
    if(msg_validarCampos == "ok"){
        var URL = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosadicionais/gravar.do"; //chamada do metodo no controller.
        var element = 'tableServicosAdicionais'; //id da div onde ser� inserido a tabela de resultado.
        var idSolicitacaoComercial = document.getElementById('comboNomeSolicitacao').value.split("|")[0];
        //var nomeSolicitacaoComercial;
        $jq('#botaoExcluir').css('display', 'block');
        $jq('#botao_gravar').css('display', 'block');
        $jq.ajax({
            type: "GET",
            url: "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/servicosadicionais/gravar.do",
            data: {
                "idSolicitacaoComercial" : idSolicitacaoComercial, 
                "dtInicio" : document.getElementById('dataVigenteInicio').value,
                "dtFim" : document.getElementById('dataVigenteFim').value,
                "tempoVigencia" : document.getElementById('idTempoVigencia').value
            },
            context: document.body,
            success: function(data){                
                var i = $jq(data).length - 1;
                
                if($jq(data).find('#error').attr('id')){
                    mostrarErro($jq(data).find('#error').attr('value'));
                }else if($jq(data).find('#success').attr('id')) {
                    mostrarSucesso($jq(data).find('#success').attr('value'));
                    document.getElementById(element).innerHTML = removeForm(data);
                }
                limparCampos();
                bloquearCampos();
            },
            error: function(data, error){
                mostrarErro(data.responseText);
            }  
        });
    
    }//fim if validarCampos
    else{
    	mostrarErro(msg_validarCampos);
    }
}



/*Ajax para ordenar a tabela*/
function ordenar(url) {
    $jq.ajax({
        type: "GET",
        url: url,
        context: document.body,
        success: function(data){
            if($jq(data).find('error').attr('id')){
            	mostrarErro($jq(data).html());
            } else if($jq('#displayServicosAdicionais').attr('id')) {
                $jq('#tableServicosAdicionais').html($jq(data).html());
			}
        },
        error: function(data, error){
        	mostrarErro(data.responseText);
        }  
    });
}

function limparCampos(){
    $jq('select').val(0);
    $jq( "input:text" ).val("");
    $jq( "input:text" ).val("");
}

function cleanMessages(){    
    document.getElementById('divErros').style.display = "none";
    document.getElementById('conteudo_divErros').innerHTML = "";
    document.getElementById('conteudo_success').style.display = "none";
    document.getElementById('conteudo_success').innerHTML = "";
}


function bloquearCampos(){
    $jq('#comboNomeServico').attr('disabled','disabled');
    $jq('#comboNomeSolicitacao').attr('disabled','disabled');
    $jq('#idTempoVigencia').attr('disabled','disabled');
}
