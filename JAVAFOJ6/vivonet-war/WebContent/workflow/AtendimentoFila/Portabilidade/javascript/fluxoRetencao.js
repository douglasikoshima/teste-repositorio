getSaldoPontos = function() {
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
    var params = $H();
    params.set('destino', 'saltoTotalPontos');
    new Ajax.Request('getDetalheProcesso.do', {
        method: 'post',
        parameters: params,
        onSuccess: function(transport) {
            $('qtPontos').update(transport.responseText);
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }
    });
}

selectHistorico = function(obj) {
    for(i = 0; i < $("tbHistoricoRetencao_body").rows.length; i++){
        if ($('tbHistoricoRetencao_body').rows[i].cells[4].all[0] == obj ||
            $('tbHistoricoRetencao_body').rows[i].cells[4].all[1] == obj) {
            $('tbHistoricoRetencao_body').rows[i].style.backgroundColor="#cecece";
        } else {
            $('tbHistoricoRetencao_body').rows[i].style.backgroundColor="#f5f5f5";
        }
    }
}

getDetalheHistoricoRetencao = function(idRetencao) {
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
    var params = $H();
    params.set('destino', 'detalhamentoHistoricoRetencao');
    new Ajax.Updater('containerDetalheHistoricoRetencao', 'getDetalheProcesso.do?idRetencao=' + idRetencao, {
        method: 'post',
        parameters: params,
        evalScripts: true,
        onComplete: function() {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }
    });
}

isPossibleToContinue = function() {
    if ($('listaOfertasAceitas') && $('listaOfertasAceitas').options.length > 0) {
        var sgOfertaAceita = hashTiposMatriz.get($('listaOfertasAceitas').options[0].value);
        if (sgOfertaAceita.toUpperCase() == 'MIG'
            || sgOfertaAceita.toUpperCase() == 'AG'
            || sgOfertaAceita.toUpperCase() == 'SP') {
            alert('Não é possivel continuar. Para finalizar a retenção clique no botão Reter.');
            return false;
        }
    }
    return true;
}

selectListasOfertas = function() {
    if ($('listaOfertasDisponiveis')) {
		for (var i = 0; i < $('listaOfertasDisponiveis').options.length; i++) {
	        $('listaOfertasDisponiveis').options[i].selected = true;
	    }
	}
	if ($('listaOfertasRealizadas')) {
	    for (var i = 0; i < $('listaOfertasRealizadas').options.length; i++) {
	        $('listaOfertasRealizadas').options[i].selected = true;
	    }
	}
	if ($('listaOfertasAceitas')) {
		for (i = 0; i < $('listaOfertasAceitas').options.length; i++) {
	        $('listaOfertasAceitas').options[i].selected = true;
	    }
	}
}

FidelizacaoFlowController = function(inInicioRetencao) {
    var container = 'containerRetencao';
    if (isPossibleToContinue()) {
        if (validateFields()) {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
            var params = $H();
            params.set('inInicioRetencao', false);
            params.set('destino', proximaOperacao);
            if (inInicioRetencao) {
                params.set('inInicioRetencao', true);
            }
            if (proximaOperacao == 'fidelizacaoDestinoPrevisto') {
                params.set('idIntencaoSelecionada', $F('idIntencaoSelecionada'));
            } else if (proximaOperacao == 'fidelizacaoMatrizOfertas') {
                params.set('idDestinoSelecionado', $F('idDestinoSelecionado'));
            } else if (proximaOperacao == 'fidelizacaoRedirecionamento') {
                selectListasOfertas();
                params.set('ofertasDisponiveis', $F('listaOfertasDisponiveis'));
                params.set('ofertasRealizadas', $F('listaOfertasRealizadas'));
                params.set('ofertasAceitas', $F('listaOfertasAceitas'));
            } else if (proximaOperacao == 'finalizacao') {
                params.set('operacaoAtual', operacaoAtual);
            } else if (proximaOperacao == 'fidelizacaoAparelhosFinalizacao') {
                params.set('operacaoAtual', operacaoAtual);
                params.set('dsTipoPagamento', $('idTipoPagamentoAparelho').options[$('idTipoPagamentoAparelho').selectedIndex].text);
                params.set('aparelhoSelecionado.idMatrizAparelho', $F('idMatrizAparelho'));
                params.set('aparelhoSelecionado.vlPercentualDesconto', $F('vlPercentualDesconto'));

                // COMODATO DE APARELHO
                if ($('prazoVigencia')) {
                    params.set('aparelhoSelecionado.vlPrecoAbs', $F('vlCalcularDesconto'));
                } else {
                    params.set('aparelhoSelecionado.vlPrecoAbs', $F('vlPrecoAbs'));
                }

                params.set('aparelhoSelecionado.nrParcelas', $F('nrParcelas'));
                params.set('aparelhoSelecionado.vlParcela', $F('vlParcela'));
                params.set('aparelhoSelecionado.idTipoPagamentoAparelho', $F('idTipoPagamentoAparelho'));
                params.set('aparelhoSelecionado.nmModeloAparelho', $F('nmModeloAparelho'));
                params.set('aparelhoSelecionado.nmCor', $F('nmCor'));
                if ($('prazoVigencia')) {
                    params.set('aparelhoSelecionado.przVigencia', $F('prazoVigencia'));
                }
                params.set('aparelhoSelecionado.cdSapAparelho', $F('cdSapAparelho'));
                params.set('aparelhoSelecionado.idAparelhoCor', $F('idAparelhoCor'));
                params.set('inExcecao', $('inExcecao').checked ? true : false);
            }
            if (flVoltar) {
                params.set('flVoltar', true);
            }
            new Ajax.Updater(container, 'fidelizacaoCore.do', {
                method: 'post',
                parameters: params,
                evalScripts: true,
                onSuccess: function() {
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    flVoltar = false;
                }, onComplete: function() {
                    AdjustHeight();
                }, onFailure: function(e) {
                    var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                    createErrorPopUp('erroPortabilidade', e.statusText, stackTrace, false);
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                }
            });
        }
    }
}

validacaoEntregaAparelho = function(dsTipoEntrega) {
    if (dsTipoEntrega == 'DELIVERY') {
        $('containerDivEntregaAparelho').show();
        $('containerDivAutorizacaoEntrega').show();
        $('containerDivAgendamento').hide();
    } else if (dsTipoEntrega == 'LOJA') {
        $('containerDivAgendamento').show();
        $('containerDivEntregaAparelho').hide();
        $('containerDivAutorizacaoEntrega').hide();
    }
}

FidelizacaoReter = function() {
    var container = 'containerRetencao';
    if (validateFields()) {
        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
        var params = $H();
        params.set('destino', operacaoAtual);
        params.set('inMesmaOferta', false);
        if (operacaoAtual == 'fidelizacaoAdequacaoPlanos') {
            params.set('planoSelecionado.id', $F('indexPlanoSelecionado'));
            params.set('inExcecao', $('inExcecao').checked ? true : false);
        } else if (operacaoAtual == 'fidelizacaoBonus') {
            params.set('operacaoAtual', operacaoAtual);
            params.set('inExcecao', $('inExcecao').checked ? true : false);
            params.set('bonusSelecionado.idBonus', $F('indexBonusSelecionado'));
            params.set('dtInicioVigencia', $F('dtInicioVigencia'));
            params.set('dtFimVigencia', $F('dtFimVigencia'));
        } else if (operacaoAtual == 'fidelizacaoPontos') {
            params.set('operacaoAtual', operacaoAtual);
            params.set('inExcecao', $('inExcecao').checked ? true : false);
            params.set('dadosPontos.qtPontos', $F('qtPontosInput'));
        } else if (operacaoAtual == 'fidelizacaoAparelhosFinalizacao') {
            params.set('operacaoAtual', operacaoAtual);
            params.set('inExcecao', $F('inExcecao') == 'true' ? true : false);
            if ($('radioDelivery').checked) {
                params.set('enderecoEntrega.inTipoEntrega', 2);
                params.set('enderecoEntrega.dsEndereco', $F('dsEndereco'));
                params.set('enderecoEntrega.nrEndereco', $F('nrEndereco'));
                params.set('enderecoEntrega.dsComplemento', $F('dsComplemento'));
                params.set('enderecoEntrega.dsBairro', $F('dsBairro'));
                params.set('enderecoEntrega.dsCidade', $F('dsCidade'));
                params.set('enderecoEntrega.dsUF', $F('dsUF'));
                params.set('enderecoEntrega.dsCEP', $F('dsCEP'));
                params.set('aparelhoSelecionado.nmTerceiro', $F('nmTerceiro'));
                params.set('aparelhoSelecionado.dsDocumentoTerceiro', $F('dsDocumentoTerceiro'));
                params.set('aparelhoSelecionado.nrContatoTelefone', $F('nrContatoTelefone'));
            } else if ($('radioLoja').checked) {
                params.set('enderecoEntrega.inTipoEntrega', 1);
                params.set('aparelhoSelecionado.nmLoja', $F('nmLoja'));
                params.set('aparelhoSelecionado.qtdEstoqueLoja', $F('qtdEstoqueLoja'));
                params.set('aparelhoSelecionado.dtRetirada', $F('dtRetirada'));
            }
        } else if (operacaoAtual == 'fidelizacaoMatrizOfertas') {
            var sgOfertaAceita = hashTiposMatriz.get($('listaOfertasAceitas').options[0].value);
            if (sgOfertaAceita == 'AG') {
                operacaoAtual = 'fidelizacaoArgumentacao';
            } else if (sgOfertaAceita == 'MIG') {
                operacaoAtual = 'fidelizacaoMigracao';
            } else if (sgOfertaAceita == 'SP') {
                operacaoAtual = 'fidelizacaoSuspensaoTemporaria';
            } else {
                alert('Não é possível finalizar a retenção para este tipo de oferta aceita.\nPor favor, selecione outra oferta.');
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                return false;
            }
            params.set('destino', operacaoAtual);
            selectListasOfertas();
            params.set('ofertasDisponiveis', $F('listaOfertasDisponiveis'));
            params.set('ofertasRealizadas', $F('listaOfertasRealizadas'));
            params.set('ofertasAceitas', $F('listaOfertasAceitas'));

        }
        new Ajax.Updater(container, 'fidelizacaoRetencao.do', {
            method: 'post',
            parameters: params,
            evalScripts: true,
            onSuccess: function() {
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                flVoltar = false;
            }, onComplete: function() {
                AdjustHeight();
            }, onFailure: function(e) {
                var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                createErrorPopUp('erroPortabilidade', e.statusText, stackTrace, false);
                if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
            }
        });
    }
}

setVaiPensarNaoRetido = function(operacao) {

    var vaiPensarExtra = !$('listaOfertasDisponiveis') ? "?vaiPensarExtra=true" : "";
    if (operacao == 'fidelizacaoVaiPensar') {
        if ($('listaOfertasAceitas') && $('listaOfertasAceitas').options.length == 1) {
			alert('A opção \'Vai Pensar\' não está disponível para este tipo de oferta aceita.\nCaso deseje efetuar esta ação, remova a oferta da lista de ofertas aceitas.');
            return false;
        }
    } else if (operacao == 'fidelizacaoNaoRetido') {
        if ($('listaOfertasAceitas').options.length == 1) {
            alert('A opção \'Não Retido\' não está disponível se houver oferta aceita.\nCaso deseje efetuar essa ação, remova a oferta da lista.');
            return false;
        } else if ($('listaOfertasRealizadas').options.length == 0) {
            alert('Não é possível realizar a operação \'Não Retido\' sem a realização de ofertas.');
            return false;
        }
    }

    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
    selectListasOfertas();

    var params = $H();
    params.set('destino', operacao);
	params.set('inExcecao', ($('inExcecao') && $('inExcecao').checked) ? true : false);
    params.set('ofertasDisponiveis', $('listaOfertasDisponiveis') ? $F('listaOfertasDisponiveis') : null);
    params.set('ofertasRealizadas', $('listaOfertasRealizadas') ? $F('listaOfertasRealizadas') : null);
    params.set('ofertasAceitas', $('listaOfertasAceitas') ? $F('listaOfertasAceitas') : null);

    // Variável 'vaiPensarExtra' foi inserida para validação quando o botão "Vai Pensar" encontra-se em telas após a Matriz de Ofertas,
	// nas quais não existe as select lists com ofertas disponíveis, realizadas e aceitas.
	new Ajax.Request('fidelizacaoRetencao.do' + vaiPensarExtra, {
        method: 'post',
        parameters: params,
        evalScripts: true,
        onSuccess: function(transport) {
            $('formPesquisaProcessos').action = "begin.do?voltar=false";
            $('formPesquisaProcessos').submit();
        }, onFailure: function(e) {
            var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
            createErrorPopUp('erroPortabilidade', e.statusText, stackTrace, false);
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }
    });
}

carregarDataVigenciaBonus = function() {
    var dia  = new Date();
	var mes  = dia.getMonth() + 1;
    var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

    if ($F('indexBonusSelecionado') == '') {
        alert('Por favor, selecione um dos bônus cadastrados.');
    } else if ($F('dtInicioVigencia').strip() == '') {
        alert('Por favor, preencha a data de início da vigência.')
    } else if (!validaDataFinal(hoje, $F('dtInicioVigencia'))) {
        alert("Data de início da vigência não pode ser menor que a data atual.");
    } else {

        var data = $F('dtInicioVigencia').strip().split('/');
        data[0] == "08"?data[0] = "8":0;data[1] == "09"?data[1] = "9":0;data[1] == "08"?data[1] = "8":0;data[1] == "09"?data[1] = "9":0;
        data[0] = parseInt(data[0]);data[1] = parseInt(data[1]);data[2] = parseInt(data[2]);
        for (i = 0; i < parseInt(qtDiasValidadeBonus); i++){
            bissexto = (data[2] % 4 == 0)?1:0;
            if (data[1] == "1" || data[1] == "3" || data[1] == "5" || data[1] == "7" || data[1] == "8" || data[1] == "10" || data[1] == "12" ){
                if(data[0] < 31){
                    data[0]++;
                } else {
                    data[0] = 1;
                    if (data[1] < 12){
                        data[1]++;
                    }else{
                        data[1] = 1;
                        data[2]++;
                    }
                }
            }else if (data[1] == "4" || data[1] == "6" || data[1] == "9" || data[1] == "11"){
                if(data[0] < 30){
                    data[0]++;
                } else {
                    data[0] = 1;
                    if (data[1] < 12){
                        data[1]++;
                    }else{
                        data[1] = 1;
                        data[2]++;
                    }
                }
            }else{
                if (bissexto){
                    if(data[0] < 29){
                        data[0]++;
                    } else {
                        data[0] = 1;
                        if (data[1] < 12){
                            data[1]++;
                        }else{
                            data[1] = 1;
                            data[2]++;
                        }
                    }
                }else{
                    if(data[0] < 28){
                        data[0]++;
                    } else {
                        data[0] = 1;
                        if (data[1] < 12){
                            data[1]++;
                        }else{
                            data[1] = 1;
                            data[2]++;
                        }
                    }
                }
            }
        }
        data[0] = data[0].toString();
        data[1] = data[1].toString();
        data[0].length == 1?data[0] = "0" + data[0]:0;
        data[1].length == 1?data[1] = "0" + data[1]:0;
        $('dtFimVigencia').value = data[0] + "/" + data[1] + "/" + data[2];
    }
}

getListaAparelhos = function() {
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
    flAparelhoSelecionado = false;
    new Ajax.Updater('divListaAparelhos', 'getListaAparelhos.do', {
        method: 'post',
        evalScripts: true,
        onSuccess: function() {
            $('vlPrecoAbs').value = '';
            $('vlParcela').value = '';
            while ($('vlPercentualDesconto').options.length > 0)
                $('vlPercentualDesconto').options[0] = null;
            $('vlPercentualDesconto').options[0] = new Option('','');

            while ($('nrParcelas').options.length > 0)
                $('nrParcelas').options[0] = null;
            $('nrParcelas').options[0] = new Option('','');

            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }, onComplete: function() {
            AdjustHeight();
        }, onFailure: function(e) {
            var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
            createErrorPopUp('erroPortabilidade', e.statusText, stackTrace, false);
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }
    });
}

finalizarAtualizacaoMensagem = function() {
	$('formPesquisaProcessos').action = 'atualizaMensagem.do';
	$('formPesquisaProcessos').submit();
}

selecionarProcessoAgrupado = function() {
    var container = 'containerRetencao';
    var linhasAssociadas = $A();
    if (flProcAgrupadoSelecionado) {

        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
        var checkBoxes = $('divSelecaoProcessosAgrupados').select('[class="checkbox"]');
        checkBoxes.each(function(item) {
            if (item.checked) {
                this[this.length] = item.value;
            }
        }, linhasAssociadas);

        var params = $H();
        if (flNovaOfertaProcessosAgrupados) {
            params.set('flNovaOfertaProcessosAgrupados', 'true');
            params.set('nrLinha', linhasAssociadas[0]);
            $('formPesquisaProcessos').action = 'fidelizacaoRetencao.do?' + params.toQueryString();
            $('formPesquisaProcessos').submit();

        } else {

            params.set('inMesmaOferta', true);
            params.set('destino', proximaOperacao);
            params.set('nrLinhasMesmaOferta', linhasAssociadas);
            if ($('dsObservacao')) {
                params.set('mensagemEncerramentoVO.dsObservacao', $F('dsObservacao'));
            }
            new Ajax.Updater(container, 'fidelizacaoRetencao.do', {
                method: 'post',
                parameters: params,
                evalScripts: true,
                onSuccess: function() {
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                    flVoltar = false;
                }, onComplete: function() {
                    AdjustHeight();
                    $('divSelecaoProcessosAgrupados').remove();
                }, onFailure: function(e) {
                    var stackTrace = e.getHeader("stackTrace") ? e.getHeader("stackTrace") : "";
                    createErrorPopUp('erroPortabilidade', e.statusText, stackTrace, false);
                    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
                }
            });
        }
    } else {
        if (flNovaOfertaProcessosAgrupados) {
            alert('Por favor, selecione uma linha para aplicação de nova oferta.');
        } else {
            alert('Por favor, selecione ao menos uma linha para aplicação da oferta.');
        }
    }
}

verificaMeioPagamento = function() {

    // Tratamento feito apenas quando não tratar-se de oferta de Aparelho em comodato
    if (!$('prazoVigencia')) {

        // Doação
        if ($F('idTipoPagamentoAparelho') == '3' || $F('idTipoPagamentoAparelho') == '4') {

            $('vlPrecoAbs').value = '0';
            $('vlPrecoAbs').disabled = true;
            $('vlParcela').value = '0';
            $('vlParcela').disabled = true;

            $('nrParcelas').selectedIndex = 0;
            $('nrParcelas').options[0].text = '0';
            $('nrParcelas').disabled = true;

            $('vlPercentualDesconto').selectedIndex = 0;
            $('vlPercentualDesconto').options[0].text = '100';
            $('vlPercentualDesconto').disabled = true;

        } else {

            if ($('vlPercentualDesconto').options.length > 0) {
                if ($('vlPercentualDesconto').options[0].text == '100') {

                    $('vlPercentualDesconto').selectedIndex = 0;
                    $('vlPercentualDesconto').options[0].text = '';

                    $('nrParcelas').selectedIndex = 0;
                    $('nrParcelas').options[0].text = '';

                    $('vlParcela').value = '';
                    $('vlPrecoAbs').value = $F('vlCalcularDesconto');
                }
            }
            $('vlPrecoAbs').disabled = false;
            $('vlParcela').disabled = false;
            $('nrParcelas').disabled = false;
            $('vlPercentualDesconto').disabled = false;
        }
    }
}

getListaDescontosParcelas = function(indexAparelho) {
    if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
    $('idTipoPagamentoAparelho').selectedIndex = 0;
    verificaMeioPagamento();
    new Ajax.Request('getListaDescontosParcelas.do', {
        method: 'get',
        parameters: {
            indexAparelho: indexAparelho
        },
        onSuccess: function(transport) {

            var dom = parseXml(transport.responseText);
            var jsonString = xml2json(dom,'');
            var jsonObject = jsonString.evalJSON();

            $('idMatrizAparelho').value = jsonObject.xmlObject.idMatrizAparelho;
            $('nmModeloAparelho').value = jsonObject.xmlObject.nmModeloAparelho;
            $('nmCor').value = jsonObject.xmlObject.nmCor;
            $('cdSapAparelho').value = jsonObject.xmlObject.cdSapAparelho;
            $('idAparelhoCor').value = jsonObject.xmlObject.idAparelhoCor;
            $('vlCalcularDesconto').value = jsonObject.xmlObject.vlCalcularDesconto;

            // APARELHO EM COMODATO
            if ($('prazoVigencia')) {

                $('vlPrecoAbs').value = '';
                $('vlPrecoAbs').readonly = true;
                $('vlParcela').value = '';
                $('vlParcela').readonly = true;
                $('nrParcelas').disabled = true;
                $('vlPercentualDesconto').disabled = true;

            } else {

                $('vlPrecoAbs').value = '';
                $('vlParcela').value = '';

                while ($('vlPercentualDesconto').options.length > 0)
                    $('vlPercentualDesconto').options[0] = null;
                $('vlPercentualDesconto').options[0] = new Option('','');

                while ($('nrParcelas').options.length > 0)
                    $('nrParcelas').options[0] = null;
                $('nrParcelas').options[0] = new Option('','');

                for (var i = 0; i < jsonObject.xmlObject.listaDescontos.xmlFragment.itemListaVO.length; i++) {
                    $('vlPercentualDesconto').options[i+1] = new Option(
                        jsonObject.xmlObject.listaDescontos.xmlFragment.itemListaVO[i].descricao,
                        jsonObject.xmlObject.listaDescontos.xmlFragment.itemListaVO[i].id
                    );
                }

                if (jsonObject.xmlObject.listaParcelas.xmlFragment.itemListaVO.length == undefined) {
                    $('nrParcelas').options[1] = new Option(
                        jsonObject.xmlObject.listaParcelas.xmlFragment.itemListaVO.descricao,
                        jsonObject.xmlObject.listaParcelas.xmlFragment.itemListaVO.id
                    );
                } else {
                    for (i = 0; i < jsonObject.xmlObject.listaParcelas.xmlFragment.itemListaVO.length; i++) {
                        $('nrParcelas').options[i+1] = new Option(
                            jsonObject.xmlObject.listaParcelas.xmlFragment.itemListaVO[i].descricao,
                            jsonObject.xmlObject.listaParcelas.xmlFragment.itemListaVO[i].id
                        );
                    }
                }

            }

            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        },
        onException: function(e) {
            alert(e);
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.stopAnimation();
        }
    });
}

calculaPercentualDesconto = function(obj) {

    var destino = $('vlCalcularDesconto');
    var valorInicio;

    if (valorInicio == undefined) {
        valorInicio = destino.value.replace(".","").replace(",",".")
        valorInicio = parseInt(valorInicio*100)
    }

     if (obj.selectedIndex == 0) {
        valorInicio = valorInicio.toString();
        $('vlPrecoAbs').value = valorInicio.substring(0, valorInicio.length - 2) + "," + valorInicio.substring(valorInicio.length - 2,valorInicio.length);
        calculaParcelas();

    } else if (obj.value != '') {
        perc = parseInt(obj.options[obj.selectedIndex].text);
        calculo = parseInt((valorInicio*perc)/100);
        calculo = valorInicio-calculo;
        calculo = calculo.toString();
        calculo = calculo.substring(0, calculo.length - 2) + "," + calculo.substring(calculo.length - 2, calculo.length);

        if(calculo.length > 6){
            calculo = calculo.substring(0, calculo.length - 6) + "." + calculo.substring(calculo.length - 6, calculo.length);
        }

        destino = $('vlPrecoAbs');
        destino.value = calculo;

        calculaParcelas();

    } else {

        valorInicio = valorInicio.toString();
        valorInicio = valorInicio.substring(0, valorInicio.length - 2) + "," + valorInicio.substring(valorInicio.length - 2, valorInicio.length);
        if (valorInicio.length > 6) {
            valorInicio = valorInicio.substring(0, valorInicio.length - 6) + "." + valorInicio.substring(valorInicio.length - 6, valorInicio.length);
        }

        destino = $('vlPrecoAbs');
        destino.value = valorInicio;

        for (var i = 0; i < $('nrParcelas').options.length; i++){
            if ($F('nrParcelas') == '') {
                $('nrParcelas').options[i].selected = true;
            }
        }
        $('vlParcela').value = '';
        valorInicio = null;
    }
}

calculaParcelas = function() {
    if ($F('vlPercentualDesconto') == '100') {
        $('vlPrecoAbs').value = '0';
        $('vlParcela').value = '0';
        $('nrParcelas').selectedIndex = 0;
        $('nrParcelas').value = 0;
        $('nrParcelas').options[0].text = '0';
    } else {
        origem  = $('vlPrecoAbs');
        destino = $('vlParcela');
        if ($('nrParcelas').selectedIndex != 0) {
            numParcelas = parseInt($('nrParcelas').options[$('nrParcelas').selectedIndex].value);
            if (numParcelas == 0) numParcelas = 1;
            valor = origem.value.replace(".","").replace(",",".");
            valor = parseInt(valor*100);
            calculo = parseInt(valor/numParcelas);
            calculo = calculo.toString();
            calculo = calculo.substring(0, calculo.length - 2) + "," + calculo.substring(calculo.length - 2, calculo.length);
            if (calculo.length > 6) {
                calculo = calculo.substring(0, calculo.length - 6) + "." + calculo.substring(calculo.length - 6, calculo.length);
            }
            destino.value = calculo;
        } else {
            destino.value = "";
        }
    }
}

function parseXml(xml) {
   var dom = null;
   if (window.DOMParser) {
      try {
         dom = (new DOMParser()).parseFromString(xml, "text/xml");
      }
      catch (e) { dom = null; }
   }
   else if (window.ActiveXObject) {
      try {
         dom = new ActiveXObject('Microsoft.XMLDOM');
         dom.async = false;
         if (!dom.loadXML(xml)) // parse error ..

            window.alert(dom.parseError.reason + dom.parseError.srcText);
      }
      catch (e) { dom = null; }
   }
   else
      alert("cannot parse xml string!");
   return dom;
}

FidelizacaoAcaoFinal = function() {
    if (flProcessosAgrupados) {
        if (confirm('Deseja aplicar a mesma oferta realizada para outras linhas que pertencem ao mesmo cliente?')) {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
            createNewPopUp('divSelecaoProcessosAgrupados', 'Processos agrupados', 600, 245, null, 'selecaoProcessosAgrupados.jsp', true);
        } else {
            if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
            if (confirm('Deseja aplicar nova oferta para outra linha que pertence ao mesmo cliente?')) {
                flNovaOfertaProcessosAgrupados = true;
                createNewPopUp('divSelecaoProcessosAgrupados', 'Processos agrupados', 600, 245, null, 'selecaoProcessosAgrupados.jsp?flNovaOfertaProcessosAgrupados=true', true);
            } else {
                if ($('dsObservacao') && !$F('dsObservacao').blank()) {
                    finalizarAtualizacaoMensagem();
                } else {
                    $('formPesquisaProcessos').action = 'begin.do?voltar=false';
                    $('formPesquisaProcessos').submit();
                }
            }
        }
    } else {
        if (window.top.frameApp.$('dvAnimarAguarde')) window.top.frameApp.startAnimation();
		if ($('dsObservacao') && !$F('dsObservacao').blank()) {
			finalizarAtualizacaoMensagem();
		} else {
			$('formPesquisaProcessos').action = 'begin.do?voltar=false';
	        $('formPesquisaProcessos').submit();
		}
    }
}

validateFields = function() {
    if (flVoltar) {
        return true;
    }
    var booleano = true;
    if (proximaOperacao == 'fidelizacaoDestinoPrevisto') {
        var arrayIntencoes = $('fidelizacaoWrapper').select('[class="radio intencoesSelecionadas"]');
        booleano = false;
        for (var i = 0; i < arrayIntencoes.length; i++) {
            if (arrayIntencoes[i].checked == true) {
                return true;
            }
        }
        alert('Por favor, selecione uma intenção de cancelamento.')
    } else if (proximaOperacao == 'fidelizacaoMatrizOfertas') {
        var arrayDestinos = $('fidelizacaoWrapper').select('[class="radio destinosSelecionados"]');
        booleano = false;
        for (var i = 0; i < arrayDestinos.length; i++) {
            if (arrayDestinos[i].checked == true) {
                return true;
            }
        }
        alert('Por favor, selecione um destino previsto.');
    } else if (proximaOperacao == 'fidelizacaoRedirecionamento') {
        if ($('listaOfertasAceitas').options.length == 0) {
            booleano = false;
            alert('Por favor, selecione uma oferta.');
        }
    } else if (operacaoAtual == 'fidelizacaoAdequacaoPlanos') {
        if ($F('indexPlanoSelecionado') == '') {
            booleano = false;
            alert('Por favor, selecione um novo plano.');
        }
    } else if (operacaoAtual == 'fidelizacaoBonus') {
        if ($F('indexBonusSelecionado') == '') {
            booleano = false;
            alert('Por favor, selecione um dos bônus cadastrados.');
        } else if ($F('dtInicioVigencia').strip() == '') {
            booleano = false;
            alert('Por favor, preencha a data de início da vigência.')
        } else if ($F('dtFimVigencia').strip() == '') {
            booleano = false;
            alert('Por favor, clique no botão Calcular para preenchimento do campo \'Fim de Vigência\'.')
        }
    } else if (operacaoAtual == 'fidelizacaoPontos') {
        if ($F('qtPontosInput').blank()) {
            booleano = false;
            alert('Por favor, preencha o campo Quantidade.');
        }
    } else if (operacaoAtual == 'fidelizacaoAparelhos') {

        if (!flAparelhoSelecionado) {
            booleano = false;
            alert('Por favor, selecione um aparelho.');
            return booleano;
        }
        var aFields = new Array();
        if ($F('idTipoPagamentoAparelho').blank())
            aFields[aFields.length] = $('idTipoPagamentoAparelho').readAttribute('title');

        var dsTipoPagamento = $('idTipoPagamentoAparelho') ? ($('idTipoPagamentoAparelho').options[$('idTipoPagamentoAparelho').options.selectedIndex].text).strip() : "";

        if (dsTipoPagamento != 'Doação') {
            if (!$('prazoVigencia')) {
                if ($F('vlPercentualDesconto').blank())
                    aFields[aFields.length] = $('vlPercentualDesconto').readAttribute('title');
                if ($F('nrParcelas').blank())
                    aFields[aFields.length] = $('nrParcelas').readAttribute('title');
            } else {
                if ($F('prazoVigencia').blank())
                    aFields[aFields.length] = $('prazoVigencia').readAttribute('title');
            }
        }
        if (aFields.length > 0) {
            booleano = false;
            formMessage(aFields);
        }
    } else if (operacaoAtual == 'fidelizacaoAparelhosFinalizacao') {
        if (!$('radioDelivery').checked && !$('radioLoja').checked) {
            alert('Por favor, selecione Delivery ou Loja.');
            return false;
        }
        if ($('radioDelivery').checked) {

            var aFields = new Array();

            if ($F('dsEndereco').blank())
                aFields[aFields.length] = $('dsEndereco').readAttribute('title');
            if ($F('nrEndereco').blank())
                aFields[aFields.length] = $('nrEndereco').readAttribute('title');
            if ($F('dsBairro').blank())
                aFields[aFields.length] = $('dsBairro').readAttribute('title');
            if ($F('dsCidade').blank())
                aFields[aFields.length] = $('dsCidade').readAttribute('title');
            if ($F('dsUF').blank())
                aFields[aFields.length] = $('dsUF').readAttribute('title');
            if ($F('dsCEP').blank())
                aFields[aFields.length] = $('dsCEP').readAttribute('title');
            if ($F('dsDocumentoTerceiro').blank())
                aFields[aFields.length] = $('dsDocumentoTerceiro').readAttribute('title');
            if ($F('nrContatoTelefone').blank())
                aFields[aFields.length] = $('nrContatoTelefone').readAttribute('title');
            if (aFields.length > 0) {
                formMessage(aFields);
                return false;
            }
        } else if ($('radioLoja').checked) {

            var aFields = new Array();
            var dia  = new Date();
            var mes  = dia.getMonth() + 1;
            var hoje = dia.getDate().toString() + "/" + mes.toString() + "/" + dia.getYear().toString();

            if ($F('nmLoja').blank())
                aFields[aFields.length] = $('nmLoja').readAttribute('title');
            if ($F('qtdEstoqueLoja').blank())
                aFields[aFields.length] = $('qtdEstoqueLoja').readAttribute('title');
            if ($F('dtRetirada').blank()) {
                aFields[aFields.length] = $('dtRetirada').readAttribute('title');
            } else if (!validaDataFinal(hoje, $F('dtRetirada'))) {
                aFields[aFields.length] = 'Data de retirada do aparelho é menor que a data atual.';
            }
            if (aFields.length > 0) {
                formMessage(aFields);
                return false;
            }
        }
    }
    return booleano;
}

checkProcessosAgrupados = function() {
    var checkBoxes = $('divSelecaoProcessosAgrupados').select('[class="checkbox"]');
    if (!flProcAgrupadosAllSelected) {
        $('labelSelAll').update('Selecionar nenhum');
        for (var i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i].checked = true;
        }
        flProcAgrupadosAllSelected = true;
    } else {
        $('labelSelAll').update('Selecionar todos');
        for (var i = 0; i < checkBoxes.length; i++) {
            checkBoxes[i].checked = false;
        }
        flProcAgrupadosAllSelected = false;
    }
}

selecionaProcessosAgrupados = function(obj) {
    if (obj.checked) {
        flProcAgrupadoSelecionado = true;
    } else {
        flProcAgrupadoSelecionado = false;
    }
}