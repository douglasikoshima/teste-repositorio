var proximaOperacao            = 'fidelizacaoIntencaoCancelamento';
var operacaoAtual              = '';
var flVoltar                   = false;
var maxHeight                  = 462;
var flProcessosAgrupados       = false;
var flAparelhoSelecionado      = false;
var hashTiposMatriz            = $H();
var flProcAgrupadoSelecionado  = false;
var flProcAgrupadosAllSelected = false;
var dsOrigem                   = '';
var inGestorGerente            = '';

function controleTeclasMaximizacaoMinimizacao(event) {
    var keyCode = event.keyCode;
    var element = event.element();
    if (element.tagName != 'SELECT' && element.tagName != 'INPUT' ) {
        // Sinal (-)
        if (keyCode == 109 || keyCode == 189) {
            $('imgWorkspaceControl').src = '/FrontOfficeWeb/resources/images/bt_small_maximize.gif';
            $('tbLinhasAssociadas').hide();
        } else
        // Sinal (+)
        if (keyCode == 107 || keyCode == 187) {
            $('imgWorkspaceControl').src = '/FrontOfficeWeb/resources/images/bt_small_minimize.gif';
            $('tbLinhasAssociadas').show();
        }
        AdjustHeight();
    }
}

onload = function() {
    abaSelected(abasDetalheProcesso, btx01);
    CarregaAba('detalheProcessoCliente');
    if ($('tbLinhasAssociadas')) {
        Event.observe(document, 'keyup', controleTeclasMaximizacaoMinimizacao);
    }
    AdjustHeight();
}

CarregaAba = function(destino, subDestino) {
    var container = 'contentAbas';
    if (destino == 'detalheHistoricoRetencao' || destino == 'fluxoRetencao') {
        container = 'containerRetencao';
    } else if (destino == 'detalheProcessoGestorGerente') {
        container = 'contentAbasCliente';
        abaSelected($('abasDetalheProcessoCliente'), $('abaGestorGerente'));
    } else if (destino == 'detalheProcessoSolicitante') {
        container = 'contentAbasCliente';
        abaSelected($('abasDetalheProcessoCliente'), $('abaSolicitante'));
    }
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
    var params = $H();
    params.set('destino', destino);
    new Ajax.Updater(container, 'getDetalheProcesso.do', {
        method: 'post',
        parameters: params,
        evalScripts: true,
        onSuccess: function() {
            if (destino == 'detalheProcessoCliente' && dsOrigem == 'NOVAOFERTA') {
                abaSelected($('abasDetalheProcesso'), btx03);
                CarregaAba('detalheProcessoRetencao');
            } else if (destino == 'detalheProcessoRetencao' && !subDestino && dsOrigem != 'NOVAOFERTA') {
                CarregaAba('detalheHistoricoRetencao');
            } else if (destino == 'detalheProcessoRetencao' && subDestino) {
                CarregaAba('fluxoRetencao');
            } else {
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            }
        }, onComplete: function() {
            if (destino == 'detalheProcessoRetencao' && dsOrigem == 'NOVAOFERTA') {
                abaSelected($('abasDetalheRetencao'), $('abaRetencao'));
                proximaOperacao = 'fidelizacaoIntencaoCancelamento';
                operacaoAtual = '';
                FidelizacaoFlowController();
            }
            AdjustHeight();
        }, onFailure: function(e) {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
            createErrorPopUp('erroPortabilidade', e.statusText, stackTrace, false);
        }

    });
}

AdjustHeight = function() {
    // Averiguação de que não se encontra na tela de erro (session.jsp).
    if (!$('imgAutenticacao')) {
        var paddingHeight = 10;
        $('contentAbas').setStyle({
            height: (maxHeight - paddingHeight -
                     $('divNavigationButtons').getHeight() -
                     $('abasDetalheProcesso').getHeight() -
                     $('detalheProtocolo').getHeight()) + 'px'
        });
        if (isSelected('btx01')) {
            if ($('contentAbasCliente')) {
                $('contentAbasCliente').setStyle({
                    height: $('contentAbas').getHeight()-40 + 'px'
                });
            }
        }
        if (isSelected('btx02')) {
            if ($('tbProcessoHistorico_div')) {
                $('tbProcessoHistorico_div').setStyle({
                    height: $('contentAbas').getHeight()-120 + 'px'
                });
            }
        }
        if (isSelected('btx03')) {
            if ($('containerRetencao')) {
                $('containerRetencao').setStyle({
                    height: $('contentAbas').getHeight()-65 + 'px'
                });
            }
            if ($('tbHistoricoRetencao_div')) {
                $('tbHistoricoRetencao_div').setStyle({
                    height: $('containerRetencao').getHeight()-190 + 'px'
                });
            }
            if ($('fidelizacaoWrapper')) {
                if (isSelected('abaRetencao') && $('divMatrizOfertas')) {
                    $('fidelizacaoWrapper').setStyle({
                        height: $('containerRetencao').getHeight()-90 + 'px'
                    });
                    if ($('divMatrizOfertas')) {
                        $('body_divMatrizOfertas').setStyle({
                            height: $('containerRetencao').getHeight()-120 + 'px'
                        });
                    }
                    var height = ($('tbLinhasAssociadas') && $('tbLinhasAssociadas').visible()) ? 84 : 175;
                    $('listaOfertasDisponiveis').setStyle({height: height + 'px'});
                    $('listaOfertasRealizadas').setStyle({height: height + 'px'});
                    $('listaOfertasAceitas').setStyle({height: height + 'px'});
                } else if (isSelected('abaRetencao') && $('divAdequacaoPlanos')) {
                    $('fidelizacaoWrapper').setStyle({
                        height: $('containerRetencao').getHeight()-90 + 'px'
                    });
                    $('body_divAdequacaoPlanos').setStyle({
                        height: $('containerRetencao').getHeight()-120 + 'px'
                    });
                } else if (isSelected('abaRetencao') && $('divAparelhos')) {
                    $('fidelizacaoWrapper').setStyle({
                        height: $('containerRetencao').getHeight()-90 + 'px'
                    });
                    $('body_divAparelhos').setStyle({
                        height: $('containerRetencao').getHeight()-120 + 'px'
                    });
                    $('tbListaAparelhos_div').setStyle({
                        height: $('containerRetencao').getHeight()-220 + 'px'
                    });
                } else if (isSelected('abaRetencao') && $('divObservacao')) {
                    $('fidelizacaoWrapper').setStyle({
                        height: $('containerRetencao').getHeight()-30 + 'px'
                    });
                } else if (isSelected('abaRetencao') && $('divDadosAparelho')) {
                    $('fidelizacaoWrapper').setStyle({
                        height: $('containerRetencao').getHeight()-90 + 'px'
                    });
                } else if (isSelected('abaRetencao') && $('divPontos')) {
                    $('fidelizacaoWrapper').setStyle({
                        height: $('containerRetencao').getHeight()-90 + 'px'
                    });
                    $('body_divPontos').setStyle({
                        height: $('containerRetencao').getHeight()-120 + 'px'
                    });
                } else if (isSelected('abaRetencao') && $('divBonus')) {
                    $('fidelizacaoWrapper').setStyle({
                        height: $('containerRetencao').getHeight()-90 + 'px'
                    });
                    $('body_divBonus').setStyle({
                        height: $('containerRetencao').getHeight()-120 + 'px'
                    });
                } else if (isSelected('abaRetencao')) {
                    $('fidelizacaoWrapper').setStyle({
                        height: $('containerRetencao').getHeight()-110 + 'px'
                    });
                } else {
                    $('fidelizacaoWrapper').setStyle({
                        height: $('containerRetencao').getHeight()-30 + 'px'
                    });
                }
            }
        }
    }
}

incluirAlterarGestorGerente = function(idGestorGerente) {
    var params = $H();
    params.set('inGestorGerente', inGestorGerente);
    params.set('idGestorGerente', idGestorGerente);
    var verbo, nome, title;
    if (idGestorGerente) {
        verbo = 'Alterar';
    } else {
        verbo = 'Incluir';
    }
    title = verbo + ' ' + inGestorGerente + ' de conta';
    window.top.frameApp.createNewPopUp('incluirAlterarGestorGerente', title, 700, 130, null,'incluirAlterarGestorGerente.do', true, params);
}

verificaNumeroVivo = function(nrLinha) {
    var isVivo = false;
    var params = $H();
    params.set('nrLinha', nrLinha);
    new Ajax.Request('verificaNumeroVivo.do', {
        method: 'post',
        parameters: params,
        asynchronous: false,
        onSuccess: function(transport) {
            var dom = parseXml(transport.responseText);
            var jsonString = xml2json(dom, '');
            var jsonObj = jsonString.evalJSON();
            if (jsonObj.msg.retorno != 'OK') {
                isVivo = false;
            }else{
                isVivo = true;
            }
        }
    });
    return isVivo;
}

salvarInclusaoAlteracaoGestorGerente = function() {
    if (validarInclusaoAlteracaoGestorGerente()) {
        new Ajax.Request('salvarGestorGerente.do', {
            method: 'post',
            parameters: $('fGerenteGestor').serialize(),
            asynchronous: false,
            evalScripts: true,
            onCreate: function() {
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
            }, onSuccess: function(transport) {
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                flVoltar = false;
                var dom = parseXml(transport.responseText);
                var jsonString = xml2json(dom, '');
                var jsonObj = jsonString.evalJSON();
                if (jsonObj.msg.retorno == 'true') {
                    var params = $H();
                    alert('Gerente incluído com sucesso');
                    $('incluirAlterarGestorGerente').remove();
                    CarregaAba('detalheProcessoGestorGerente');
                } else {
                    alert('Ocorreu um problema durante gravação dos dados.\nCodigo: '+jsonObj.msg.cdErro+'\nDescrição: '+jsonObj.msg.msErro);
                }
            }, onComplete: function(transport) {
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            }
        });
    }
}


cancelar = function(){
    $('incluirAlterarGestorGerente').remove();
}

validarInclusaoAlteracaoGestorGerente = function() {
    if ($F('primeiroNome').blank() || $F('sobrenome').blank()) {
        alert('Por favor, digite o nome completo do ' + inGestorGerente);
        return false;
    } else if (!validaCPF($F('nrCPF'))) {
        alert('Por favor, digite um número válido de CPF.')
        return false;
    } else if (!validaEmail($F('dsEmail'))) {
        alert('Por favor, digite um endereço de e-mail válido.')
        return false;
    } else if ($F('nrTelefone').length < 13) {
        alert('Por favor, digite um número de celular válido.');
        return false;
    } else if ( !verificaNumeroVivo( $F('nrTelefone') ) ) {
        alert('Por favor, digite um número de celular válido.');
        return false;
    } else if ($F('nrTelefoneContato').length < 13) {
        alert('Por favor, digite um telefone de contato válido.');
        return false;
    }
    return true;
}

WorkspaceControl = function() {
    if ($('tbLinhasAssociadas')) {
        if ($('tbLinhasAssociadas').visible()) {
            $('imgWorkspaceControl').src = '/FrontOfficeWeb/resources/images/bt_small_maximize.gif';
            $('tbLinhasAssociadas').hide();
        } else {
            $('imgWorkspaceControl').src = '/FrontOfficeWeb/resources/images/bt_small_minimize.gif';
            $('tbLinhasAssociadas').show();
        }
        AdjustHeight();
    }
}

getComentarioProcesso = function(obj) {
    var uniqueNumber = new Date().getTime();
    var newID = 'linhaHistoricoProcesso' + uniqueNumber;
    obj.id = newID;
    $('processosComentarios').update($(newID).select('[class="classIdAtendimento"]')[0].value);
}