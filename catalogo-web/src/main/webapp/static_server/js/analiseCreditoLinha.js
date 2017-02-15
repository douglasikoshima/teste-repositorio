/* Script para tela An�lise de Cr�dito Linha */
/* �ltima Altera��o: 17/12/2013				 */


var $jq = jQuery.noConflict();

$jq(document).ready(function() {
    carregaMenu('mn_parametrizacao_fixa_analise_credito_linha');
    /* ler tooltip quando tiver title no documento */
    $jq("#resultAnaliseCreditoLinha").tooltip();

    /*Interceptadores da a��o Ordenar - Para n�o ocorrer o submit e quebrar os componentes*/
    $jq(".sortable a").live("click", function() {
        acao($jq(this).attr('href'));
        return false;
    })
    $jq(".pagelinks a").live("click", function() {
        acao($jq(this).attr('href'));
        return false;
    })
});

function carregandoDados() {
    return "<img src='/catalogo/static_server/img/carregando.gif'>";
}

function removeForm(data){
	var data1 = data.replace('<form name="analiseCreditoLinhaForm" id="analiseScore" method="post" action="/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analiseCreditoLinha/pesquisar.do">','');
	var data2 = data1.replace('</form>','');
	return data2
}

/* Script para mostrar a div de ERRO */
function mostrarErro(erro) {
    document.getElementById('divErros').style.display = "block";
    document.getElementById('divErros').scrollIntoView();
    document.getElementById('conteudo_divErros').innerHTML = erro;
}

function mostrarSucesso(msg) {
    document.getElementById('conteudo_success').style.display = "block";
	document.getElementById('conteudo_success').innerHTML = msg;
    document.getElementById('conteudo_success').scrollIntoView();
}

/* validar campos da pesquisa quando n�o tiver nenhum campo preenchido */
function validarCampos() {
    var msg = "Favor preencher ao menos um par&#226;metro da pesquisa.";
    if (($jq.trim($jq('#txtOferta').val()) != "")
    	|| ($jq('#comboServicoLinha').val() != 0)
    	|| ($jq('#comboScore').val() != 0)){
        msg = "ok";
    }
    return msg;
}

/* Habilitar Resultado das Ofertas */
function pesquisar() {
    cleanMessages();
    var msg_validarCampos = validarCampos(); //retorna mensagem de erro ou sucesso.
    if (msg_validarCampos == "ok" && !validarAlterado()) {
        document.getElementById("carregarDados").innerHTML = carregandoDados();
        bloquearCampos();

        $jq('html, body').animate({scrollTop: $jq("#resultAnaliseCreditoLinha").css('display', 'block').offset().top}, 2000);
        var URL = '/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analiseCreditoLinha/pesquisar.do';
        var nomeOferta = $jq('#txtOferta').val();
        var idServico = $jq('#comboServicoLinha').val();
        var score = $jq('#comboScore').val();
        var element = 'resultAnaliseCreditoLinha'; //id do elemento onde ser� anexado a tabela de resultado
        alterado = false;
        $jq.ajax({
            type: "GET",
            url: URL,
            data: {
                "dsOfertafixa" : nomeOferta,
                "idServico" : idServico,
                "score" : score
            },
            context: document.body,
            success: function(data) {
                desbloquearCampos();
                $jq("#tabelaAnaliseCreditoLinha").css('display', 'block')
                document.getElementById(element).innerHTML = removeForm(data);
                document.getElementById("carregarDados").innerHTML = "";
                if ($jq(data).find('#error').attr('id')) {
                    mostrarErro($jq(data).find('#error').attr('value'));
                } else if ($jq(data).find('#success').attr('id')) {
                    mostrarSucesso($jq(data).find('#success').attr('value'));
                    document.getElementById(element).innerHTML = removeForm(data);
                }
            },
            error: function(data, error) {
                mostrarErro(data.responseText);
            }
        });
    } else if (msg_validarCampos != 'ok') {
        mostrarErro(msg_validarCampos);
    }
}

/* Habilitar e Desablitiar os checkboxs */
$jq('#todos').live("click", function() {
    /* Descobre quais os checkboxs pertencentes ao "pai" */
    var check = $jq(this).parent().parent().children().children();
    /* Se o #todos for igual a checado */
    if ($jq(this).attr("checked")) {
        $jq(check).each(
			function() {
			    $jq(this).attr("checked", true);
			}
        );
    } else {
        $jq(check).each(
	        function() {
	            $jq(this).attr("checked", false);
	        }
        );
    }
});

$jq('.checkboxScore').live("click", function(){
    var value = $jq(this).val().split("|")[0];
    var all = true;
    $jq("." + value).each(
        function() {
            if (!$jq(this).attr("checked") && $jq(this).attr("id") != "todos") {
                all = false;
            }
        }
    );
    $jq("." + value).each(
        function() {
            if ($jq(this).attr("id") == "todos") {
                $jq(this).attr("checked", all);
            }
        }
    );

});

/* Fun��o para gravar */
function gravarScore() {
    cleanMessages();
    var nomeOferta = $jq('#txtOferta').val();
    var idServicoLinha = $jq('#comboServicoLinha').val();
    var score = $jq('#comboScore').val();
    var scoresListAdicionar = "";
    var scoresListRemover = ''; //armazena os valores separados por '|' e ';' sendo na respectiva ordem idOfertafixaCreditoScore e cdCor
    /* Valida todos os checkboxes se est�o checked e concatena na var scores */
    
    $jq('[name="checkboxScore"][vinculado="true"]:not([checked="checked"])').each(function() {
        scoresListRemover = scoresListRemover + $jq(this).val();
    });

    $jq('[name="checkboxScore"][vinculado="false"][checked="checked"]').each(function() {
        scoresListAdicionar = scoresListAdicionar + $jq(this).val();
    });

    var element = 'resultAnaliseCreditoLinha'; //id do elemento onde ser� anexado a tabela de resultado
    var URL = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analiseCreditoLinha/gravar.do";
    alterado = false;
    $jq.ajax({
        type: "GET",
        url: URL,
        data: {
            "scoresListAdicionar" : scoresListAdicionar,
            "scoresListRemover" : scoresListRemover,
            "dsOfertafixa" : nomeOferta,
            "idServico" : idServicoLinha,
            "score" : score
        },
        context: document.body,
        success: function(data) {
            document.getElementById(element).innerHTML = removeForm(data);
            if ($jq(data).find('#error').attr('id')) {
                mostrarErro($jq(data).find('#error').attr('value'));
            } else if ($jq(data).find('#success').attr('id')) {
                mostrarSucesso($jq(data).find('#success').attr('value'));
                document.getElementById(element).innerHTML = removeForm(data);
            }
        },
        error: function(data, error) {
            mostrarErro(data.responseText);
        }
    });
}
/* fun��o para limpar as mensagens de sucesso e de Erro */
function cleanMessages() {
    document.getElementById('divErros').style.display = "none";
    document.getElementById('conteudo_divErros').innerHTML = "";
    document.getElementById('conteudo_success').style.display = "none";
    document.getElementById('conteudo_success').innerHTML = "";
}

function bloquearCampos() {
    $jq(".botao .btNavegacao74 ").attr('disabled', 'true')
}
function desbloquearCampos() {
    $jq(".botao .btNavegacao74 ").removeAttr('disabled')
}

var alterado = false; // var para controle dos checkboxes caso alterado seu status ap�s load da p�gina.

$jq('.checkbox').live("change", function() {
    if (($jq(this).attr("id") == 'checkboxScore' 
        && $jq(this).val().split(";")[0].split("|")[2] == "true" 
        && !$jq(this).attr('checked') 
        && !confirm("Essa oferta possui prioriza\xe7\xe3o para esse score, deseja realmente remover a configura\xe7\xe3o?"))
      || 
      ($jq(this).attr("id") == 'todos' 
        && $jq(this).val() == "true" 
        && !$jq(this).attr('checked') 
        && !confirm("Essa oferta possui prioriza\xe7\xe3o para algum score, deseja realmente remover as configura\xe7\xf5es?"))) {
        $jq(this).attr("checked","checked");
    }else {
        alterado = true;
    }   
});

function validarAlterado() {
    if (alterado) {
        alterado = !confirm('Todas as informa\xe7\xf5es configuradas ser\xe3o perdidas, Deseja prosseguir sem gravar?');
    }
    return alterado;
}
function acao(href) {
    if (alterado) {
        if (validarAlterado()) {
            return false;
        } else {
            ordenar(href);
        }
        alterado = false;
    } else {
        ordenar(href);
    }
    //var caminho = "/catalogo/br/com/vivo/catalogoPRS/pageflows/param/analiseCreditoLinha/ordernar.do?" + acao.split("?")[1];
}

/*Ajax para ordenar a tabela*/
function ordenar(href) {
    $jq.ajax({
        type: "GET",
        url: href,
        context: document.body,
        success: function(data) {
            if ($jq('#analiseCreditoLinhaTO').attr('id')) {
                $jq('#resTabela').html($jq(data).html());
            }
        },
        error: function(data, error) {
            mostrarSucesso(data.responseText);
        }
    });
}

function limparCampos() {
    $jq('select').val(0);
    $jq("input:text").val("");
}
